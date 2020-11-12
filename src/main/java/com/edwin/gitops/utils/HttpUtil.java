package com.edwin.gitops.utils;

import com.edwin.gitops.utils.props2yaml.PropertyTree;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public static HttpPost getHttpPost(String url, String authorization, Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpPost.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        httpPost.addHeader(new BasicHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));


        List<NameValuePair> nvps = new ArrayList<>();

        params.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        return httpPost;
    }

    public static HttpPost getHttpPost(String url, String authorization, String jsonParams) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpPost.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        httpPost.addHeader(new BasicHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));


        httpPost.setEntity(new StringEntity(jsonParams));

        return httpPost;
    }
}
