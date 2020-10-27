package com.edwin.gitops.config.properties;


public class ContentProperties {
    private String target = "tag: ";
    private String contentUrl = "/contents";
    private String updateContentMessage = "update file, modify tag";

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getUpdateContentMessage() {
        return updateContentMessage;
    }

    public void setUpdateContentMessage(String updateContentMessage) {
        this.updateContentMessage = updateContentMessage;
    }
}
