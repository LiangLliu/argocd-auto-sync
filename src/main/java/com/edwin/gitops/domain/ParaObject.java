package com.edwin.gitops.domain;

import java.util.Map;

public class ParaObject {
    private String url;
    private String token;
    private String filePath;
    private Map<String, String> replaceMap;

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

    @Override
    public String toString() {
        return "ParaObject{" +
                "userAndRepo='" + url + '\'' +
                ", token='" + token + '\'' +
                ", filePath='" + filePath + '\'' +
                ", replaceMap=" + replaceMap +
                '}';
    }
}
