package com.edwin.gitops.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {


    public static HttpGet getHttpGet(String url, String authorization, Map<String, String> param) {
        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        return httpGet;
    }


    public static HttpPost getHttpPost(String url, String authorization, Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpPost.addHeader(new BasicHeader("Accept", "application/json"));
        httpPost.addHeader(new BasicHeader("Content-type", "application/json"));

        List<NameValuePair> nvps = new ArrayList<>();

        params.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        return httpPost;
    }

    public static HttpPost getHttpPost(String url, String authorization, String jsonParams) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpPost.addHeader(new BasicHeader("Accept", "application/json"));
        httpPost.addHeader(new BasicHeader("Content-type", "application/json"));


        httpPost.setEntity(new StringEntity(jsonParams));

        return httpPost;
    }

    public static HttpDelete getHttpDelete(String url, String authorization) {
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpDelete.addHeader(new BasicHeader("Accept", "application/json"));
        httpDelete.addHeader(new BasicHeader("Content-type", "application/json"));
        return httpDelete;
    }

    public static HttpPut getHttpPut(String url, String authorization, String jsonParams) throws UnsupportedEncodingException {
        HttpPut httpPut = new HttpPut(url);

        httpPut.addHeader(new BasicHeader("Authorization", "token " + authorization));
        httpPut.addHeader(new BasicHeader("Accept", "application/json"));
        httpPut.addHeader(new BasicHeader("Content-type", "application/json"));

        httpPut.setEntity(new StringEntity(jsonParams));

        return httpPut;
    }
}
