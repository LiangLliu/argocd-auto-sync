package com.edwin.gitops.client;

import com.edwin.gitops.domain.ParaObject;
import com.edwin.gitops.domain.branch.Content;
import com.edwin.gitops.domain.branch.PullRequest;
import com.edwin.gitops.domain.branch.Branch;
import com.edwin.gitops.utils.ContentUtil;
import com.edwin.gitops.utils.HttpClientUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.time.Instant;

public class BranchClient {

    private final ParaObject paraObject;

    public BranchClient(ParaObject paraObject) {
        this.paraObject = paraObject;
    }

    private Branch getMasterBranch() throws Exception {

        return HttpClientUtil.get(paraObject.getMasterBranchUrl(),
                paraObject.getToken(),
                Branch.class);
    }


    private void createBranchByMaster() throws Exception {

        Branch masterBranch = getMasterBranch();


        JsonObject payload = new JsonObject();
        payload.add("ref", new JsonPrimitive("refs/heads/" + paraObject.getNewBranchName()));
        payload.add("sha", new JsonPrimitive(masterBranch.getObject().getSha()));

        HttpClientUtil.post(paraObject.getCreateBranchByMasterUrl(),
                paraObject.getToken(),
                payload.toString(),
                Branch.class);
    }


    private void deleteBranch() throws Exception {
        HttpClientUtil.delete(paraObject.getDeleteBranchUrl(), paraObject.getToken());
    }


    private void createAndMergePullRequest(String baseUrl, String authorization) throws Exception {

        String url = baseUrl + paraObject.getPullUrl();

        JsonObject payload = new JsonObject();
        payload.add("title", new JsonPrimitive(paraObject.getCreatePullRequestTitle()));
        payload.add("head", new JsonPrimitive(paraObject.getNewBranchName()));
        payload.add("base", new JsonPrimitive(paraObject.getDefaultBaseBranch()));

        PullRequest pullRequest = HttpClientUtil.post(url, authorization, payload.toString(), PullRequest.class);

        mergePullRequestById(baseUrl, authorization, pullRequest.getNumber());

    }

    private void mergePullRequestById(String baseUrl, String authorization, Integer id) throws Exception {

        String url = baseUrl + paraObject.getPullUrl() + "/" + id + "/merge";

        JsonObject payload = new JsonObject();
        payload.add("commit_message", new JsonPrimitive("merge-message,time by " + Instant.now()));

        HttpClientUtil.put(url, authorization, payload.toString(), Void.class);

    }

    public void updateDeploymentTag() throws Exception {
        createBranchByMaster();
        replaceTag();
        createAndMergePullRequest(paraObject.getBaseUrl(), paraObject.getToken());
        deleteBranch();
    }


    private void replaceTag() throws Exception {

        String url = paraObject.getUpdateDeploymentTagUrl();

        Content content = HttpClientUtil.get(url, paraObject.getToken(), Content.class);

        String contentBase64 = ContentUtil.replaceDataToBase64(content, paraObject.getReplaceMap());

        JsonObject payload = new JsonObject();
        payload.add("message", new JsonPrimitive("update file, modify tag"));
        payload.add("content", new JsonPrimitive(contentBase64));
        payload.add("sha", new JsonPrimitive(content.getSha()));
        payload.add("branch", new JsonPrimitive(paraObject.getNewBranchName()));

        HttpClientUtil.put(url, paraObject.getToken(), payload.toString(), Void.class);
    }


}
