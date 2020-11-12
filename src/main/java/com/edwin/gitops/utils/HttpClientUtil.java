package com.edwin.gitops.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

public class HttpClientUtil {

    private static final CloseableHttpClient client = HttpClients.createDefault();

    private static final Gson gson = new Gson();

    private static final String CHARSET = "utf-8";


    public static <T> T get(String url, String authorization, Class<T> valueType) throws Exception {
        return get(url, authorization, null, valueType);
    }

    public static <T> T get(String url, String authorization, Map<String, String> param, Class<T> valueType) throws Exception {
        HttpGet httpGet = HttpUtil.getHttpGet(url, authorization, param);
        HttpEntity entity = client.execute(httpGet).getEntity();
        String content = EntityUtils.toString(entity, CHARSET);
        return gson.fromJson(content, valueType);
    }


}
