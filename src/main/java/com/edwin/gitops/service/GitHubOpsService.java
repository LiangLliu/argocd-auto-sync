package com.edwin.gitops.service;

import com.edwin.gitops.client.BranchClient;
import com.edwin.gitops.domain.ParaObject;

import java.io.IOException;
import java.util.Map;

public class GitHubOpsService {


    private final BranchClient branchClient;

    public GitHubOpsService( BranchClient branchClient) {

        this.branchClient = branchClient;
    }


    public void updateDeploymentTag(String baseUrl, String authorization, String filePath, Map<String, String> replaceMap) throws IOException {

        branchClient.updateDeployment(baseUrl, authorization, filePath, replaceMap);
    }

    public void updateDeploymentTag(ParaObject paraObject) throws IOException {

        updateDeploymentTag(paraObject.getUrl(), paraObject.getToken(),
                paraObject.getFilePath(), paraObject.getReplaceMap());
    }
}
