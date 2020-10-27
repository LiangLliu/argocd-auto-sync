//package com.edwin.gitops.client;
//
//import com.edwin.gitops.domain.refs.Refs;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//

//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class RefsClientTest {
//
//    private static final String authorization = "your token";
//    private static final String baseUrl = "your repo";
//
//    @Autowired
//    private RefsClient refsClient;
//
//    @Test
//    public void should_get_master_branch_refs_when_given_github_url_and_token() {
//        Refs masterRefs = refsClient.getMasterRefs(baseUrl, authorization);
//        String ref = "refs/heads/main";
//        assertEquals(masterRefs.getRef(), ref);
//    }
//
//    @Test
//    public void should_get_master_all_branch_refs_when_given_github_url_and_token() {
//        List<Refs> allMasterRefs = refsClient.getAllRefs(baseUrl, authorization);
//        assertNotNull(allMasterRefs);
//    }
//
//    @Test
//    public void should_create_new_branch_when_given_github_url_and_token() {
//        refsClient.createRefsByMasterBranch(baseUrl, authorization);
//    }
//
//    @Test
//    public void should_delete_new_branch_when_given_github_url_and_token() {
//        refsClient.deleteRefs(baseUrl, authorization);
//    }
//}