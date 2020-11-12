package com.edwin.gitops.utils;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

public class HttpUtil {
    public static HttpHeaders getHeaders(String authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, "token " + authorization);
        return headers;
    }


    public static HttpGet getHttpGet(String url, String authorization, Map<String, String> param) {
        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpGet.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        httpGet.addHeader(new BasicHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));

        return httpGet;
    }
}
