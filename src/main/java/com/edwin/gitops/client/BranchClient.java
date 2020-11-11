package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.refs.Branch;
import com.edwin.gitops.utils.HttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

public class BranchClient {
    private final String NEW_BRANCH_NAME;

    private final RestTemplate restTemplate;

    private final String GIT_REFS_HEAD_URL = "/git/refs/heads";

    private final String DEFAULT_BASE_BRANCH;

    public BranchClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.NEW_BRANCH_NAME = gitOpsProperties.getNewBranchName();
        this.DEFAULT_BASE_BRANCH = gitOpsProperties.getDefaultBranch();
    }


    public String getMasterBranchSHA(String baseUrl, String authorization) {
        Branch masterBranch = getMasterBranch(baseUrl, authorization);
        return masterBranch.getObject().getSha();
    }


    public Branch getMasterBranch(String baseUrl, String authorization) {


        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + DEFAULT_BASE_BRANCH;

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        ResponseEntity<Branch> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                Branch.class);

        return responseEntity.getBody();
    }

    public void createBranch(String baseUrl, String authorization, String masterBranchSHA) {

        String url = baseUrl + "/git/refs";

        JsonObject payload = new JsonObject();
        payload.add("ref", new JsonPrimitive("refs/heads/" + NEW_BRANCH_NAME));
        payload.add("sha", new JsonPrimitive(masterBranchSHA));

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        restTemplate.exchange(url
                , HttpMethod.POST,
                new HttpEntity<>(payload.toString(), headers),
                Branch.class);

    }


    public void createBranchByMaster(String baseUrl, String authorization) {
        String masterBranchSHA = getMasterBranchSHA(baseUrl, authorization);
        createBranch(baseUrl, authorization, masterBranchSHA);
    }

    public void deleteBranch(String baseUrl, String authorization) {
        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + NEW_BRANCH_NAME;

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<String>(headers),
                Void.class);
    }


}
