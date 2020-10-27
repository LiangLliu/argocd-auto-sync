package com.edwin.gitops.config.properties;


import java.time.Instant;

public class PullsProperties {
    private String pullsUrl = "/pulls";
    private String createPullRequestTitle = "create-pull-request";
    private String updatePullRequestTitle = "update-pull-request";
    private String mergeCommitMessage = "merge-message,time by " + Instant.now();

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getCreatePullRequestTitle() {
        return createPullRequestTitle;
    }

    public void setCreatePullRequestTitle(String createPullRequestTitle) {
        this.createPullRequestTitle = createPullRequestTitle;
    }

    public String getUpdatePullRequestTitle() {
        return updatePullRequestTitle;
    }

    public void setUpdatePullRequestTitle(String updatePullRequestTitle) {
        this.updatePullRequestTitle = updatePullRequestTitle;
    }

    public String getMergeCommitMessage() {
        return mergeCommitMessage;
    }

    public void setMergeCommitMessage(String mergeCommitMessage) {
        this.mergeCommitMessage = mergeCommitMessage;
    }
}
