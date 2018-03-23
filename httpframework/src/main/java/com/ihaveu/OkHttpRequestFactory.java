package com.ihaveu;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpRequest;

import java.net.URI;

/**
 * User: bkzhou
 * Date: 2018/3/23
 * Description:
 */
public class OkHttpRequestFactory implements HttpRequestFactory {


    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method, byte[] data) {
        return null;
    }
}
