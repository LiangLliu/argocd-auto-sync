package com.edwin.gitops.domain.common;


import java.time.Instant;

public class Commit {
    private String id;
    private String tree_id;
    private String message;
    private Instant timestamp;

    private Account author;
    private Account committer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTree_id() {
        return tree_id;
    }

    public void setTree_id(String tree_id) {
        this.tree_id = tree_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Account getCommitter() {
        return committer;
    }

    public void setCommitter(Account committer) {
        this.committer = committer;
    }
}
