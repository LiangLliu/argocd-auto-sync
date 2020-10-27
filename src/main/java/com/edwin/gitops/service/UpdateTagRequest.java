package com.edwin.gitops.service;

public class UpdateTagRequest {
    private String deploymentPath = "";
    private String token  = "";
    private String imageTag = "66666.5";

    public String getDeploymentPath() {
        return deploymentPath;
    }

    public void setDeploymentPath(String deploymentPath) {
        this.deploymentPath = deploymentPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }
}
