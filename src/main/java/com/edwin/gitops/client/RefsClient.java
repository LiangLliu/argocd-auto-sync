package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.refs.Refs;
import com.edwin.gitops.utils.HttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

public class RefsClient {
    private final String NEW_BRANCH_NAME;

    private final RestTemplate restTemplate;

    private final String GIT_REFS_HEAD_URL = "/git/refs/heads";

    private final String DEFAULT_BASE_BRANCH;

    public RefsClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.NEW_BRANCH_NAME = gitOpsProperties.getNewBranchName();
        this.DEFAULT_BASE_BRANCH = gitOpsProperties.getDefaultBranch();
    }


    public String getMasterBranchSHA(String baseUrl, String authorization) {
        Refs masterRefs = getMasterRefs(baseUrl, authorization);
        return masterRefs.getObject().getSha();
    }


    public Refs getMasterRefs(String baseUrl, String authorization) {


        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + DEFAULT_BASE_BRANCH;

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        ResponseEntity<Refs> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                Refs.class);

        return responseEntity.getBody();
    }

    public void createRefs(String baseUrl, String authorization, String masterBranchSHA) {

        String url = baseUrl + "/git/refs";

        JsonObject payload = new JsonObject();
        payload.add("ref", new JsonPrimitive("refs/heads/" + NEW_BRANCH_NAME));
        payload.add("sha", new JsonPrimitive(masterBranchSHA));

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        ResponseEntity<Refs> responseEntity = restTemplate.exchange(url
                , HttpMethod.POST,
                new HttpEntity<>(payload.toString(), headers),
                Refs.class);

        responseEntity.getBody();
    }


    public void createRefsByMasterBranch(String baseUrl, String authorization) {
        String masterBranchSHA = getMasterBranchSHA(baseUrl, authorization);
        createRefs(baseUrl, authorization, masterBranchSHA);
    }

    public void deleteRefs(String baseUrl, String authorization) {
        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + NEW_BRANCH_NAME;

        HttpHeaders headers = HttpUtil.getHeaders(authorization);

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<String>(headers),
                Void.class);
    }


}
