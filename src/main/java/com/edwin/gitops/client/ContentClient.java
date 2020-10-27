package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.content.Content;
import com.edwin.gitops.utils.ContentUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ContentClient {

    private final String CONTENT_URL;
    private final String UPDATE_BY_MESSAGE;
    private final String DEFAULT_BRANCH;

    private final RestTemplate restTemplate;

    private final String ACCESS_TOKEN_PARA;

    public ContentClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.CONTENT_URL = gitOpsProperties.getContent().getContentUrl();
        this.UPDATE_BY_MESSAGE = gitOpsProperties.getContent().getUpdateContentMessage();
        this.DEFAULT_BRANCH = gitOpsProperties.getDefaultBranch();
        this.restTemplate = restTemplate;
        this.ACCESS_TOKEN_PARA = gitOpsProperties.getAccessTokenPara();
    }


    public Content getContentFileByPath(String baseUrl, String authorization, String repoFilepath) {
        String url = baseUrl + CONTENT_URL + repoFilepath + ACCESS_TOKEN_PARA + authorization;
        ResponseEntity<Content> contentResponseEntity = restTemplate.getForEntity(url, Content.class);
        return contentResponseEntity.getBody();
    }

    public Content updateContent(String baseUrl, String authorization, String repoFilepath, String branchName,
                                 Map<String, String> replaceMap) {

        Content content = getContentFileByPath(baseUrl, authorization, repoFilepath);
        System.out.println("file_SHA:  " + content.getSha() + " \n");

        String data = ContentUtil.replaceData(content, replaceMap);

        String contentBase64 = Base64.getEncoder().encodeToString(data.getBytes());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("message", UPDATE_BY_MESSAGE);
        paramMap.put("content", contentBase64);
        paramMap.put("sha", content.getSha());
        paramMap.put("branch", StringUtils.isEmpty(branchName) ? DEFAULT_BRANCH : branchName);

        String url = baseUrl + CONTENT_URL + "/" + repoFilepath + ACCESS_TOKEN_PARA + authorization;

        restTemplate.put(url, paramMap);

        return getContentFileByPath(baseUrl, authorization, repoFilepath);
    }

}
