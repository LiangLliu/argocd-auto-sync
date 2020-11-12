package com.edwin.gitops.client;

import com.edwin.gitops.domain.ParaObject;
import com.edwin.gitops.domain.branch.Content;
import com.edwin.gitops.domain.branch.PullRequest;
import com.edwin.gitops.domain.branch.Branch;
import com.edwin.gitops.utils.ContentUtil;
import com.edwin.gitops.utils.HttpClientUtil;

import com.edwin.gitops.utils.RandomUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.time.Instant;
import java.util.Map;


public class BranchClient {

    private static final String NEW_BRANCH_NAME = "branch-" + RandomUtil.generate();
    private static final String DEFAULT_BASE_BRANCH = "master";

    private static final String CONTENT_URL = "/contents";
    private static final String GIT_REFS_HEAD_URL = "/git/refs/heads";
    private static final String PULL_URL = "/pulls";


    private static final String CREATE_PULL_REQUEST_TITLE = "create-pull-request";

    private final ParaObject paraObject;

    public BranchClient(ParaObject paraObject) {
        this.paraObject = paraObject;
    }


    private Branch getMasterBranch(String baseUrl, String authorization) throws Exception {

        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + DEFAULT_BASE_BRANCH;

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
        payload.add("ref", new JsonPrimitive("refs/heads/" + NEW_BRANCH_NAME));
        payload.add("sha", new JsonPrimitive(masterBranch.getObject().getSha()));

        HttpClientUtil.post(url, authorization, payload.toString(), Branch.class);

    }


    private void deleteBranch(String baseUrl, String authorization) throws Exception {
        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + NEW_BRANCH_NAME;

        HttpClientUtil.delete(url, authorization);
    }

    private Content getContentFileByPath(String baseUrl, String authorization, String repoFilepath) throws Exception {

        String url = baseUrl + CONTENT_URL + "/" + repoFilepath;

        return HttpClientUtil.get(url, authorization, Content.class);
    }


    private void createAndMergePullRequest(String baseUrl, String authorization) throws Exception {

        String url = baseUrl + PULL_URL;

        JsonObject payload = new JsonObject();
        payload.add("title", new JsonPrimitive(CREATE_PULL_REQUEST_TITLE));
        payload.add("head", new JsonPrimitive(NEW_BRANCH_NAME));
        payload.add("base", new JsonPrimitive(DEFAULT_BASE_BRANCH));

        PullRequest pullRequest = HttpClientUtil.post(url, authorization, payload.toString(), PullRequest.class);

        mergePullRequestById(baseUrl, authorization, pullRequest.getNumber());

    }

    private void mergePullRequestById(String baseUrl, String authorization, Integer id) throws Exception {

        String url = baseUrl + PULL_URL + "/" + id + "/merge";

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

        String url = baseUrl + CONTENT_URL + "/" + repoFilepath;

        JsonObject payload = new JsonObject();
        payload.add("message", new JsonPrimitive("update file, modify tag"));
        payload.add("content", new JsonPrimitive(contentBase64));
        payload.add("sha", new JsonPrimitive(content.getSha()));
        payload.add("branch", new JsonPrimitive(NEW_BRANCH_NAME));


        HttpClientUtil.put(url, authorization, payload.toString(), Void.class);
    }


}
