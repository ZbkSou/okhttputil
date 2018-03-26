package com.ihaveu.service;

import com.ihaveu.http.HttpMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class MoocHttpProvider {

    private static final String ENCODING = "utf-8";

    private static WorkStation workStation = new WorkStation();
    /**
     * 请求数据编码
     * @param value
     * @return
     */
    public static byte[] encodeParam(Map<String, String> value) {
        if (value == null || value.size() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        try {
            for (Map.Entry<String, String> entry : value.entrySet()) {

                buffer.append(URLEncoder.encode(entry.getKey(), ENCODING)).append("=")
                    .append(URLEncoder.encode(entry.getValue(),ENCODING));

                if(count!=value.size()-1){
                    buffer.append("&");
                }
                count++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buffer.toString().getBytes();
    }

    public static void helloWorld(String url, Map<String, String> value, MoocResponse<String> response) {
        MoocRequest request = new MoocRequest();
        request.setUrl(url);
        request.setMethod(HttpMethod.POST);
        request.setData(encodeParam(value));
        request.setResponse(response);
        workStation.add(request);
    }
}
