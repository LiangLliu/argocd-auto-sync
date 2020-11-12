package com.edwin.gitops.domain;

import java.util.Map;

public class ParaObject {
    private String url;
    private String token;
    private String filePath;
    private Map<String, String> replaceMap;
    private String defaultBaseBranch;
    private String newBranchName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, String> getReplaceMap() {
        return replaceMap;
    }

    public void setReplaceMap(Map<String, String> replaceMap) {
        this.replaceMap = replaceMap;
    }

    public String getDefaultBaseBranch() {
        return defaultBaseBranch;
    }

    public void setDefaultBaseBranch(String defaultBaseBranch) {
        this.defaultBaseBranch = defaultBaseBranch;
    }


    public String getNewBranchName() {
        return newBranchName;
    }

    public void setNewBranchName(String newBranchName) {
        this.newBranchName = newBranchName;
    }

    public String getContentUrl() {
        return "/contents";
    }

    public String getPullUrl() {
        return "/pulls";
    }

    public String getRefsHeadUrl() {
        return "/git/refs/heads";
    }

    public String getCreatePullRequestTitle() {

        StringBuilder sb = new StringBuilder();
        sb.append("Update ").append(filePath).append(" ");
        replaceMap.forEach((key, value) -> sb.append(key).append(" to ").append(value).append(" "));
        return sb.toString();
    }


}
