//package com.edwin.gitops.controller;
//
//import com.edwin.gitops.service.UpdateTagRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.Instant;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class GitHubOpsControllerTest {
//
//    private static final String authorization = "your token";
//    private static final String baseUrl = "your repo";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    public void should_update_file_text_when_given_github_url_and_token() throws Exception {
//
//        String url = "/gitops";
//
//        String replaceString = "test_" + getClass() + Instant.now();
//
//        UpdateTagRequest updateTagRequest = UpdateTagRequest.builder()
//                .deploymentPath(baseUrl)
//                .token(authorization)
//                .imageTag(replaceString)
//                .build();
//
//        mockMvc.perform(put(url)
//                .content(objectMapper.writeValueAsString(updateTagRequest))
//                .contentType("application/json;charset=UTF-8")
//                .characterEncoding("UTF-8"))
//                .andExpect(status().isOk());
//    }
//
//}