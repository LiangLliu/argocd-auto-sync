package com.edwin.gitops.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.util.Map;

public class HttpClientUtil {

    private static final CloseableHttpClient client = HttpClients.createDefault();

    private static final Gson gson = new Gson();

    private static final String CHARSET = "utf-8";


    public static <T> T get(String url, String authorization, Class<T> valueType) throws Exception {
        return get(url, authorization, null, valueType);
    }

    public static <T> T get(String url, String authorization, Map<String, String> param, Class<T> valueType) throws IOException {
        HttpGet httpGet = HttpUtil.getHttpGet(url, authorization, param);
        HttpEntity entity = client.execute(httpGet).getEntity();
        String content = EntityUtils.toString(entity, CHARSET);
        return gson.fromJson(content, valueType);
    }


    public static <T> T post(String url, String authorization, Map<String, String> params, Class<T> valueType) throws IOException {
        HttpPost httpPost = HttpUtil.getHttpPost(url, authorization, params);
        HttpEntity entity = client.execute(httpPost).getEntity();
        String content = EntityUtils.toString(entity, CHARSET);
        return gson.fromJson(content, valueType);
    }

    public static <T> T post(String url, String authorization, String jsonParams, Class<T> valueType) throws IOException {
        HttpPost httpPost = HttpUtil.getHttpPost(url, authorization, jsonParams);
        HttpEntity entity = client.execute(httpPost).getEntity();
        String content = EntityUtils.toString(entity, CHARSET);
        return gson.fromJson(content, valueType);
    }


    public static void delete(String url, String authorization) throws IOException {
        HttpDelete httpDelete = HttpUtil.getHttpDelete(url, authorization);
        client.execute(httpDelete);
    }

    public static <T> T put(String url, String authorization, String jsonParams, Class<T> valueType) throws IOException {
        HttpPut httpPut = HttpUtil.getHttpPut(url, authorization, jsonParams);
        HttpEntity entity = client.execute(httpPut).getEntity();
        String content = EntityUtils.toString(entity, CHARSET);
        return gson.fromJson(content, valueType);
    }
}
