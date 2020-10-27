package com.edwin.gitops.config.properties;

public class RefsProperties {

    private String gitRefsHeadUrl = "/git/refs/heads";
    private String refsHeads = "refs/heads/";
    private String gitRefs = "/git/refs";

    public String getGitRefsHeadUrl() {
        return gitRefsHeadUrl;
    }

    public void setGitRefsHeadUrl(String gitRefsHeadUrl) {
        this.gitRefsHeadUrl = gitRefsHeadUrl;
    }

    public String getRefsHeads() {
        return refsHeads;
    }

    public void setRefsHeads(String refsHeads) {
        this.refsHeads = refsHeads;
    }

    public String getGitRefs() {
        return gitRefs;
    }

    public void setGitRefs(String gitRefs) {
        this.gitRefs = gitRefs;
    }
}
