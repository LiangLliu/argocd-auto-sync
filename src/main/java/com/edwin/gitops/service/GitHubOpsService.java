package com.edwin.gitops.service;

import com.edwin.gitops.client.ContentClient;
import com.edwin.gitops.client.PullRequestClient;
import com.edwin.gitops.client.RefsClient;

import java.io.IOException;
import java.util.Map;

public class GitHubOpsService {

    private final PullRequestClient pullRequestClient;
    private final ContentClient contentClient;
    private final RefsClient refsClient;

    public GitHubOpsService(PullRequestClient pullRequestClient, ContentClient contentClient, RefsClient refsClient) {
        this.pullRequestClient = pullRequestClient;
        this.contentClient = contentClient;
        this.refsClient = refsClient;
    }


    public void updateDeploymentTag(String baseUrl, String authorization, String filePath, Map<String, String> replaceMap) throws IOException {

        refsClient.createRefsByMasterBranch(baseUrl, authorization);

        contentClient.updateContent(baseUrl, authorization, filePath, replaceMap);
        pullRequestClient.createAndMergePullRequest(baseUrl, authorization);
        refsClient.deleteRefs(baseUrl, authorization);
    }
}
