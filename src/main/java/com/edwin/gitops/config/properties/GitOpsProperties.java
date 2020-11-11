package com.edwin.gitops.config.properties;

import com.edwin.gitops.utils.RandomUtil;

public class GitOpsProperties {

    private final String newBranchName = "branch-" + RandomUtil.generate();

    private String defaultBranch = "master";

    public String getNewBranchName() {
        return newBranchName;
    }


    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

}
