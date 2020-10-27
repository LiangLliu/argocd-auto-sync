package com.edwin.gitops.service;

import com.edwin.gitops.client.ContentClient;
import com.edwin.gitops.client.PullRequestClient;
import com.edwin.gitops.client.RefsClient;
import com.edwin.gitops.config.properties.GitOpsProperties;


import java.io.IOException;
import java.util.Map;

public class GitHubOpsService {

    private final PullRequestClient pullRequestClient;
    private final ContentClient contentClient;
    private final RefsClient refsClient;
    private final GitOpsProperties gitOpsProperties;

    public GitHubOpsService(PullRequestClient pullRequestClient, ContentClient contentClient, RefsClient refsClient, GitOpsProperties gitOpsProperties) {
        this.pullRequestClient = pullRequestClient;
        this.contentClient = contentClient;

        this.refsClient = refsClient;
        this.gitOpsProperties = gitOpsProperties;
    }



    public void updateDeploymentTag(UpdateTagRequest request, Map<String, String> replaceMap) throws IOException {
        String authorization = request.getToken();
        String baseUrl = request.getDeploymentPath();
        String filePath = gitOpsProperties.getPropertiesFilePath();
        String branchName = gitOpsProperties.getNewBranchName();

        updateDeploymentTag(baseUrl, authorization, branchName, filePath, replaceMap);
    }

    private void updateDeploymentTag(String baseUrl, String authorization, String branchName,
                                     String filePath, Map<String, String> replaceMap) throws IOException {

        refsClient.createRefsByMasterBranch(baseUrl, authorization);

        contentClient.updateContent(baseUrl, authorization, filePath, branchName, replaceMap);
        pullRequestClient.createAndMergePullRequest(baseUrl, authorization);
        refsClient.deleteRefs(baseUrl, authorization);
    }
}
