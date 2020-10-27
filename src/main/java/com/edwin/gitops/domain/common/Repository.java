package com.edwin.gitops.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Repository {

    private String url;

    private long id;
    private String createdAt;
    private String updatedAt;

    private String nodeId, description, homepage, name, full_name;

    private String html_url;

    private boolean has_issues, has_wiki, fork, has_downloads, has_pages, archived, has_projects;

    private boolean allow_squash_merge;

    private boolean allow_merge_commit;

    private boolean allow_rebase_merge;

    private boolean delete_branch_on_merge;

    @JsonProperty("private")
    private boolean _private;

    private int forks_count, stargazers_count, watchers_count, size, open_issues_count, subscribers_count;

    private String pushed_at;

    private String default_branch, language;

    private Repository source, parent;

    private Boolean isTemplate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isHas_projects() {
        return has_projects;
    }

    public void setHas_projects(boolean has_projects) {
        this.has_projects = has_projects;
    }

    public boolean isAllow_squash_merge() {
        return allow_squash_merge;
    }

    public void setAllow_squash_merge(boolean allow_squash_merge) {
        this.allow_squash_merge = allow_squash_merge;
    }

    public boolean isAllow_merge_commit() {
        return allow_merge_commit;
    }

    public void setAllow_merge_commit(boolean allow_merge_commit) {
        this.allow_merge_commit = allow_merge_commit;
    }

    public boolean isAllow_rebase_merge() {
        return allow_rebase_merge;
    }

    public void setAllow_rebase_merge(boolean allow_rebase_merge) {
        this.allow_rebase_merge = allow_rebase_merge;
    }

    public boolean isDelete_branch_on_merge() {
        return delete_branch_on_merge;
    }

    public void setDelete_branch_on_merge(boolean delete_branch_on_merge) {
        this.delete_branch_on_merge = delete_branch_on_merge;
    }

    public boolean is_private() {
        return _private;
    }

    public void set_private(boolean _private) {
        this._private = _private;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public int getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(int subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Repository getSource() {
        return source;
    }

    public void setSource(Repository source) {
        this.source = source;
    }

    public Repository getParent() {
        return parent;
    }

    public void setParent(Repository parent) {
        this.parent = parent;
    }

    public Boolean getTemplate() {
        return isTemplate;
    }

    public void setTemplate(Boolean template) {
        isTemplate = template;
    }
}
