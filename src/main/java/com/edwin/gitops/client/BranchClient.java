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
import java.util.Map;

public class BranchClient {

    private final ParaObject paraObject;

    public BranchClient(ParaObject paraObject) {
        this.paraObject = paraObject;
    }

    private Branch getMasterBranch(String baseUrl, String authorization) throws Exception {

        String url = baseUrl + paraObject.getRefsHeadUrl() + "/" + paraObject.getDefaultBaseBranch();

        try {
            return HttpClientUtil.get(url, authorization, Branch.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    private void createBranchByMaster(String baseUrl, String authorization) throws Exception {

        Branch masterBranch = getMasterBranch(baseUrl, authorization);

        String url = baseUrl + "/git/refs";

        JsonObject payload = new JsonObject();
        payload.add("ref", new JsonPrimitive("refs/heads/" + paraObject.getNewBranchName()));
        payload.add("sha", new JsonPrimitive(masterBranch.getObject().getSha()));

        HttpClientUtil.post(url, authorization, payload.toString(), Branch.class);

    }


    private void deleteBranch(String baseUrl, String authorization) throws Exception {
        String url = baseUrl + paraObject.getRefsHeadUrl() + "/" + paraObject.getNewBranchName();

        HttpClientUtil.delete(url, authorization);
    }

    private Content getContentFileByPath(String baseUrl, String authorization, String repoFilepath) throws Exception {

        String url = baseUrl + paraObject.getContentUrl() + "/" + repoFilepath;

        return HttpClientUtil.get(url, authorization, Content.class);
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

        updateDeployment(paraObject.getUrl(),
                paraObject.getToken(),
                paraObject.getFilePath(),
                paraObject.getReplaceMap());
    }

    private void updateDeployment(String baseUrl, String authorization, String filePath, Map<String, String> replaceMap) throws Exception {

        updateDeploymentTag(baseUrl, authorization, filePath, replaceMap);
        createAndMergePullRequest(baseUrl, authorization);
        deleteBranch(baseUrl, authorization);
    }


    private void updateDeploymentTag(String baseUrl, String authorization, String repoFilepath, Map<String, String> replaceMap) throws Exception {


        createBranchByMaster(baseUrl, authorization);

        Content content = getContentFileByPath(baseUrl, authorization, repoFilepath);

        String contentBase64 = ContentUtil.replaceDataToBase64(content, replaceMap);

        String url = baseUrl + paraObject.getContentUrl() + "/" + repoFilepath;

        JsonObject payload = new JsonObject();
        payload.add("message", new JsonPrimitive("update file, modify tag"));
        payload.add("content", new JsonPrimitive(contentBase64));
        payload.add("sha", new JsonPrimitive(content.getSha()));
        payload.add("branch", new JsonPrimitive(paraObject.getNewBranchName()));


        HttpClientUtil.put(url, authorization, payload.toString(), Void.class);
    }


}
