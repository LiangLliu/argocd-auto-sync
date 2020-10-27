package com.edwin.gitops.domain.refs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Refs {

    private String ref;

    @JsonProperty(value = "node_id")
    private String nodeId;
    private String url;

    private Object object;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
