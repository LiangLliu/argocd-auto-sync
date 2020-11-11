//package com.edwin.gitops.client;
//
//import com.edwin.gitops.domain.branch.Content;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.*;
//import java.time.Instant;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ContentClientTest {
//
//    private static final String authorization = "your token";
//    private static final String baseUrl = "your repo";
//
//    @Autowired
//    private ContentClient contentClient ;
//
//    @Test
//    public void should_get_repo_readme_when_given_github_url_and_token() {
//
//        Content content = contentClient.getRepoByReadme(baseUrl, authorization);
//        System.out.println(content.getEncoding());
//        System.out.println(content.getContent());
//        assertEquals(content.getName(), "README.md");
//    }
//
//    @Test
//    public void should_get_file_by_path_when_given_github_url_and_token_and_path() {
//        String path = "/README.md";
//        Content content = contentClient.getContentFileByPath(baseUrl, authorization, path);
//        assertEquals(content.getName(), "README.md");
//    }
//
//    @Test
//    public void should_create_file_when_given_github_url_and_token_and_path_and_modify_file() throws IOException {
//
//        String repoFilepath = "/values-dev.yaml";
//
//        Content content = contentClient.createFileByContent(baseUrl, authorization, repoFilepath);
//
//        assertEquals(content.getName(), "values-dev.yaml");
//
//
//    }
//
//    @Test
//    public void should_update_file_when_given_github_url_and_token_and_path_and_modify_content() throws IOException, InterruptedException {
//
//        String repoFilepath = "/values-dev.yaml";
//        String replaceContent = "test_version_" + Instant.now();
//        Content content = contentClient.updateContent(baseUrl, authorization, repoFilepath, replaceContent);
//        assertEquals(content.getName(), "values-dev.yaml");
//
//    }
//
//
//}