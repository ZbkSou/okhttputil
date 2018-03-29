package com.ihaveu.service.convert;

import com.google.gson.Gson;
import com.ihaveu.http.HttpResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class JsonConvert implements Convert{
    private Gson gson = new Gson();
    private static  final String CONTENT_TYPE = "application/json;charset=UTF-8";
    @Override
    public Object pares(HttpResponse response, Type type) throws IOException {
        Reader reader = new InputStreamReader(response.getBody());
        return gson.fromJson(reader,type);
    }

    @Override
    public Object pares(String content, Type type) throws IOException {
        return gson.fromJson(content,type);
    }

    @Override
    public boolean isCanParse(String contentType) {
        return CONTENT_TYPE.equals(contentType);
    }
}
