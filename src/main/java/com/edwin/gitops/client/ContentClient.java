package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.content.Content;
import com.edwin.gitops.utils.ContentUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ContentClient {

    private static final String CONTENT_URL = "/contents";
    private static final String UPDATE_BY_MESSAGE = "update file, modify tag";
    private final String NEW_BRANCH;

    private final RestTemplate restTemplate;

    private final String ACCESS_TOKEN_PARA;

    public ContentClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {

        this.NEW_BRANCH = gitOpsProperties.getNewBranchName();
        this.restTemplate = restTemplate;
        this.ACCESS_TOKEN_PARA = gitOpsProperties.getAccessTokenPara();
    }


    public Content getContentFileByPath(String baseUrl, String authorization, String repoFilepath) {
        String url = getURL(baseUrl, authorization, repoFilepath);
        ResponseEntity<Content> contentResponseEntity = restTemplate.getForEntity(url, Content.class);
        return contentResponseEntity.getBody();
    }

    public void updateContent(String baseUrl, String authorization, String repoFilepath,
                              Map<String, String> replaceMap) {

        Content content = getContentFileByPath(baseUrl, authorization, repoFilepath);

        String data = ContentUtil.replaceData(content, replaceMap);

        String contentBase64 = Base64.getEncoder().encodeToString(data.getBytes());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("message", UPDATE_BY_MESSAGE);
        paramMap.put("content", contentBase64);
        paramMap.put("sha", content.getSha());
        paramMap.put("branch", NEW_BRANCH);

        String url = baseUrl + CONTENT_URL + "/" + repoFilepath + ACCESS_TOKEN_PARA + authorization;
        restTemplate.put(url, paramMap);

        getContentFileByPath(baseUrl, authorization, repoFilepath);
    }

    private String getURL(String baseUrl, String authorization, String repoFilepath) {

        return baseUrl +
                CONTENT_URL +
                repoFilepath +
                ACCESS_TOKEN_PARA +
                authorization;
    }

}
