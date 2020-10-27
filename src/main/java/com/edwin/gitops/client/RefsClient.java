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

    private static final String GIT_REFS_HEAD_URL = "/git/refs/heads";

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
        String url = getDefaultBranch(baseUrl, authorization);
        ResponseEntity<Refs> responseEntity = restTemplate.getForEntity(url, Refs.class);
        return responseEntity.getBody();
    }

    public Refs createRefs(String baseUrl, String authorization, String masterBranchSHA) {

        String url = baseUrl + "/git/refs" + ACCESS_TOKEN_PARA + authorization;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ref", "refs/heads/" + NEW_BRANCH_NAME);
        paramMap.put("sha", masterBranchSHA);

        return restTemplate.postForEntity(url, paramMap, Refs.class).getBody();
    }


    public void createRefsByMasterBranch(String baseUrl, String authorization) {
        String masterBranchSHA = getMasterBranchSHA(baseUrl, authorization);
        createRefs(baseUrl, authorization, masterBranchSHA);
    }

    public void deleteRefs(String baseUrl, String authorization) {
        String url = getNewBranchUrl(baseUrl, authorization);
        restTemplate.delete(url);
    }

    private String getNewBranchUrl(String baseUrl, String authorization) {
        return getBranch(baseUrl, authorization, NEW_BRANCH_NAME);
    }

    private String getDefaultBranch(String baseUrl, String authorization) {
        return getBranch(baseUrl, authorization, DEFAULT_BASE_BRANCH);
    }

    private String getBranch(String baseUrl, String authorization, String branchName) {
        return baseUrl +
                GIT_REFS_HEAD_URL +
                "/" +
                branchName +
                ACCESS_TOKEN_PARA +
                authorization;
    }


}
