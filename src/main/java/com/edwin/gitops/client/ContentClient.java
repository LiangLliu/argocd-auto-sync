package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.content.Content;
import com.edwin.gitops.utils.ContentUtil;
import com.edwin.gitops.utils.HttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

public class ContentClient {

    private static final String CONTENT_URL = "/contents";
    private static final String UPDATE_BY_MESSAGE = "update file, modify tag";
    private final String NEW_BRANCH;

    private final RestTemplate restTemplate;

    public ContentClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {

        this.NEW_BRANCH = gitOpsProperties.getNewBranchName();
        this.restTemplate = restTemplate;
    }

    public Content getContentFileByPath(String baseUrl, String authorization, String repoFilepath) {


        String url = baseUrl + CONTENT_URL + "/" + repoFilepath;

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        ResponseEntity<Content> contentResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                Content.class);
        return contentResponseEntity.getBody();
    }

    public void updateContent(String baseUrl, String authorization, String repoFilepath, Map<String, String> replaceMap) {

        Content content = getContentFileByPath(baseUrl, authorization, repoFilepath);

        String data = ContentUtil.replaceData(content, replaceMap);

        String contentBase64 = Base64.getEncoder().encodeToString(data.getBytes());

        String url = baseUrl + CONTENT_URL + "/" + repoFilepath;

        JsonObject payload = new JsonObject();
        payload.add("message", new JsonPrimitive(UPDATE_BY_MESSAGE));
        payload.add("content", new JsonPrimitive(contentBase64));
        payload.add("sha", new JsonPrimitive(content.getSha()));
        payload.add("branch", new JsonPrimitive(NEW_BRANCH));

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(payload.toString(), headers),
                Void.class);
    }

}
