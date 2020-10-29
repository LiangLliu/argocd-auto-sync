package com.edwin.gitops.config.properties;

import com.edwin.gitops.utils.RandomUtil;

public class GitOpsProperties {

    private static final String accessTokenPara = "?access_token=";

    private String newBranchName = "branch-" + RandomUtil.generate();

    private String defaultBranch = "master";

    public String getAccessTokenPara() {
        return accessTokenPara;
    }

    public String getNewBranchName() {
        return newBranchName;
    }

    public void setNewBranchName(String newBranchName) {
        this.newBranchName = newBranchName;
    }


    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

}
