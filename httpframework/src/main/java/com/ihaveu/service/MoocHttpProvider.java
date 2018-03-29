package com.ihaveu.service;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.service.convert.Convert;
import com.ihaveu.service.convert.JsonConvert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class MoocHttpProvider {

    private static final String ENCODING = "utf-8";

    private static WorkStation workStation = new WorkStation();

    private static final List<Convert> sConverts = new ArrayList<>();

    static {
        sConverts.add(new JsonConvert());
    }

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

    public static void helloWorld(String url, Map<String, String> value, MoocResponse response) {
        MoocRequest request = new MoocRequest();

        WarpperResponse warpperResponse = new WarpperResponse(response,sConverts);

        request.setUrl(url);
        request.setMethod(HttpMethod.POST);
        request.setData(encodeParam(value));
        request.setResponse(warpperResponse);
        workStation.add(request);
    }
}
