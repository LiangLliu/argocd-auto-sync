package com.edwin.gitops.client;

import com.edwin.gitops.domain.common.Commit;
import com.edwin.gitops.domain.common.GitHubFile;
import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.pulls.PullRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

/**
 * https://docs.github.com/en/free-pro-team@latest/rest/reference/pulls#create-a-pull-request
 */
public class PullRequestClient {

    private final String PR_BRANCH_NAME;

    private final RestTemplate restTemplate;

    private final String ACCESS_TOKEN_PARA;

    private final String PULL_URL;
    private final String CREATE_PULL_REQUEST_TITLE;

    private final String DEFAULT_BASE_BRANCH;
    private final String MERGE_COMMIT_MESSAGE;

    public PullRequestClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.PULL_URL = gitOpsProperties.getPulls().getPullsUrl();
        this.CREATE_PULL_REQUEST_TITLE = gitOpsProperties.getPulls().getCreatePullRequestTitle();
        this.DEFAULT_BASE_BRANCH = gitOpsProperties.getDefaultBranch();
        this.MERGE_COMMIT_MESSAGE = gitOpsProperties.getPulls().getMergeCommitMessage();
        this.ACCESS_TOKEN_PARA = gitOpsProperties.getAccessTokenPara();
        this.PR_BRANCH_NAME = gitOpsProperties.getNewBranchName();
    }


    public PullRequest createPullRequest(String baseUrl, String authorization) {
        String url = baseUrl + PULL_URL + ACCESS_TOKEN_PARA + authorization;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", CREATE_PULL_REQUEST_TITLE);
        paramMap.put("head", PR_BRANCH_NAME);
        paramMap.put("base", DEFAULT_BASE_BRANCH);

        ResponseEntity<PullRequest> pullRequestResponseEntity = restTemplate.postForEntity(url, paramMap, PullRequest.class);
        return pullRequestResponseEntity.getBody();

    }

    public void mergePullRequestById(String baseUrl, String authorization, Integer id) {
        String url = baseUrl + PULL_URL + "/" + id + "/merge" + ACCESS_TOKEN_PARA + authorization;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("commit_message", MERGE_COMMIT_MESSAGE + Instant.now());

        restTemplate.put(url, paramMap);
    }


    public void createAndMergePullRequest(String baseUrl, String authorization) {
        PullRequest pullRequest = createPullRequest(baseUrl, authorization);
        mergePullRequestById(baseUrl, authorization, pullRequest.getNumber());
    }

}
