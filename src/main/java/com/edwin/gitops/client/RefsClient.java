package com.edwin.gitops.client;

import com.edwin.gitops.config.properties.GitOpsProperties;
import com.edwin.gitops.domain.refs.Refs;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RefsClient {
    private final String NEW_BRANCH_NAME;

    private final String ACCESS_TOKEN_PARA;

    private final RestTemplate restTemplate;

    private final String GIT_REFS_HEAD_URL = "/git/refs/heads";
    private final String REFS_HEADS = "refs/heads/";
    private final String GIT_REFS = "/git/refs";

    private final String DEFAULT_BASE_BRANCH;

    public RefsClient(GitOpsProperties gitOpsProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.ACCESS_TOKEN_PARA = gitOpsProperties.getAccessTokenPara();
        this.NEW_BRANCH_NAME = gitOpsProperties.getNewBranchName();
        this.DEFAULT_BASE_BRANCH = gitOpsProperties.getDefaultBranch();
    }


    public String getMasterBranchSHA(String baseUrl, String authorization) {
        Refs masterRefs = getMasterRefs(baseUrl, authorization);
        return masterRefs.getObject().getSha();
    }


    public Refs getMasterRefs(String baseUrl, String authorization) {
        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + DEFAULT_BASE_BRANCH + ACCESS_TOKEN_PARA + authorization;
        ResponseEntity<Refs> responseEntity = restTemplate.getForEntity(url, Refs.class);
        return responseEntity.getBody();
    }

    public Refs createRefs(String baseUrl, String authorization, String masterBranchSHA) {
        String url = baseUrl + GIT_REFS + ACCESS_TOKEN_PARA + authorization;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ref", REFS_HEADS + NEW_BRANCH_NAME);
        paramMap.put("sha", masterBranchSHA);
        return restTemplate.postForEntity(url, paramMap, Refs.class).getBody();
    }


    public Refs createRefsByMasterBranch(String baseUrl, String authorization) {
        String masterBranchSHA = getMasterBranchSHA(baseUrl, authorization);
        return createRefs(baseUrl, authorization, masterBranchSHA);
    }

    public void deleteRefs(String baseUrl, String authorization) {
        String url = baseUrl + GIT_REFS_HEAD_URL + "/" + NEW_BRANCH_NAME + ACCESS_TOKEN_PARA + authorization;
        restTemplate.delete(url);
    }


}
