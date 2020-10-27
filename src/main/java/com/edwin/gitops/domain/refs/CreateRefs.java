package com.edwin.gitops.domain.refs;


public class CreateRefs {
    private String ref;
    private String sha;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
