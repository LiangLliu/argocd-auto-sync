package com.edwin.gitops.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtil {
    public static HttpHeaders getHeaders(String authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, "token " + authorization);
        return headers;
    }
}
