package com.edwin.gitops.service;

import com.edwin.gitops.client.ContentClient;
import com.edwin.gitops.client.PullRequestClient;
import com.edwin.gitops.client.BranchClient;
import com.edwin.gitops.domain.ParaObject;

import java.io.IOException;
import java.util.Map;

public class GitHubOpsService {

    private final PullRequestClient pullRequestClient;
    private final ContentClient contentClient;
    private final BranchClient branchClient;

    public GitHubOpsService(PullRequestClient pullRequestClient, ContentClient contentClient, BranchClient branchClient) {
        this.pullRequestClient = pullRequestClient;
        this.contentClient = contentClient;
        this.branchClient = branchClient;
    }


    public void updateDeploymentTag(String baseUrl, String authorization, String filePath, Map<String, String> replaceMap) throws IOException {

        branchClient.createBranchByMaster(baseUrl, authorization);

        contentClient.updateContent(baseUrl, authorization, filePath, replaceMap);
        pullRequestClient.createAndMergePullRequest(baseUrl, authorization);
        branchClient.deleteBranch(baseUrl, authorization);
    }

    public void updateDeploymentTag(ParaObject paraObject) throws IOException {

        updateDeploymentTag(paraObject.getUrl(), paraObject.getToken(),
                paraObject.getFilePath(), paraObject.getReplaceMap());
    }
}
