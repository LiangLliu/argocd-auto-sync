package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.pulls.PullRequest;
import com.edwin.gitops.utils.HttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

/**
 * https://docs.github.com/en/free-pro-team@latest/rest/reference/pulls#create-a-pull-request
 */
public class PullRequestClient {

    private final String PR_BRANCH_NAME;

    private final RestTemplate restTemplate;

    private static final String PULL_URL = "/pulls";

    private final String DEFAULT_BASE_BRANCH;
    private final String MERGE_COMMIT_MESSAGE;

    public PullRequestClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        this.DEFAULT_BASE_BRANCH = gitOpsProperties.getDefaultBranch();
        this.MERGE_COMMIT_MESSAGE = "merge-message,time by " + Instant.now();
        this.PR_BRANCH_NAME = gitOpsProperties.getNewBranchName();
    }


    public PullRequest createPullRequest(String baseUrl, String authorization) {

        String url = baseUrl + PULL_URL;
        String CREATE_PULL_REQUEST_TITLE = "create-pull-request";

        JsonObject payload = new JsonObject();
        payload.add("title", new JsonPrimitive(CREATE_PULL_REQUEST_TITLE));
        payload.add("head", new JsonPrimitive(PR_BRANCH_NAME));
        payload.add("base", new JsonPrimitive(DEFAULT_BASE_BRANCH));

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        ResponseEntity<PullRequest> pullRequestResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(payload.toString(), headers),
                PullRequest.class);
        return pullRequestResponseEntity.getBody();

    }

    public void mergePullRequestById(String baseUrl, String authorization, Integer id) {


        String url = baseUrl + PULL_URL + "/" + id + "/merge";

        JsonObject payload = new JsonObject();
        payload.add("commit_message", new JsonPrimitive(MERGE_COMMIT_MESSAGE + Instant.now()));

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(payload.toString(), headers),
                Void.class);
    }

    public void createAndMergePullRequest(String baseUrl, String authorization) {
        PullRequest pullRequest = createPullRequest(baseUrl, authorization);
        mergePullRequestById(baseUrl, authorization, pullRequest.getNumber());
    }

}
