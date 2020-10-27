package com.edwin.gitops.domain.pulls;

import com.edwin.gitops.domain.common.CommitPointer;
import com.edwin.gitops.domain.common.Team;
import com.edwin.gitops.domain.common.User;

import java.util.List;

public class PullRequest {

    private String url;

    protected User assignee;
    protected List<User> assignees;
    protected String state;
    protected int number;
    protected String closed_at;
    protected int comments;

    protected User user;
    protected String title, html_url;

    protected User closed_by;
    protected boolean locked;

    private long id;
    private String nodeId;
    private String createdAt;
    private String updatedAt;

    private String patch_url;
    private String diff_url;
    private String issue_url;

    private CommitPointer base;
    private String merged_at;
    private CommitPointer head;

    private User merged_by;
    private int review_comments;
    private int additions;
    private int commits;
    private boolean merged;
    private boolean maintainer_can_modify;
    boolean draft;
    private Boolean mergeable;
    private int deletions;
    private String mergeable_state;
    private int changed_files;
    private String merge_commit_sha;

    private List<User> requested_reviewers;
    private List<Team> requested_teams;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<User> assignees) {
        this.assignees = assignees;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public User getClosed_by() {
        return closed_by;
    }

    public void setClosed_by(User closed_by) {
        this.closed_by = closed_by;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public CommitPointer getBase() {
        return base;
    }

    public void setBase(CommitPointer base) {
        this.base = base;
    }

    public String getMerged_at() {
        return merged_at;
    }

    public void setMerged_at(String merged_at) {
        this.merged_at = merged_at;
    }

    public CommitPointer getHead() {
        return head;
    }

    public void setHead(CommitPointer head) {
        this.head = head;
    }

    public User getMerged_by() {
        return merged_by;
    }

    public void setMerged_by(User merged_by) {
        this.merged_by = merged_by;
    }

    public int getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(int review_comments) {
        this.review_comments = review_comments;
    }

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean isMaintainer_can_modify() {
        return maintainer_can_modify;
    }

    public void setMaintainer_can_modify(boolean maintainer_can_modify) {
        this.maintainer_can_modify = maintainer_can_modify;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public Boolean getMergeable() {
        return mergeable;
    }

    public void setMergeable(Boolean mergeable) {
        this.mergeable = mergeable;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public String getMergeable_state() {
        return mergeable_state;
    }

    public void setMergeable_state(String mergeable_state) {
        this.mergeable_state = mergeable_state;
    }

    public int getChanged_files() {
        return changed_files;
    }

    public void setChanged_files(int changed_files) {
        this.changed_files = changed_files;
    }

    public String getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public void setMerge_commit_sha(String merge_commit_sha) {
        this.merge_commit_sha = merge_commit_sha;
    }

    public List<User> getRequested_reviewers() {
        return requested_reviewers;
    }

    public void setRequested_reviewers(List<User> requested_reviewers) {
        this.requested_reviewers = requested_reviewers;
    }

    public List<Team> getRequested_teams() {
        return requested_teams;
    }

    public void setRequested_teams(List<Team> requested_teams) {
        this.requested_teams = requested_teams;
    }
}
