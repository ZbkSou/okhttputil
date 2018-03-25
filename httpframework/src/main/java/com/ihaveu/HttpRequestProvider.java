package com.ihaveu;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpRequest;
import com.ihaveu.util.Utils;

import java.io.IOException;
import java.net.URI;


/**
 * Created by ZBK on 2018-03-25.
 * 根据不同的条件创建不同的http Request
 */

public class HttpRequestProvider {

    private static boolean OKHTTP_REQUEST = Utils.isExist("com.squareup.okhttp.OkHttpClient",HttpRequestProvider.class.getClassLoader());

    private HttpRequestFactory httpRequestFactory;

    public HttpRequestFactory getHttpRequestFactory() {
        return httpRequestFactory;
    }
    public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory = httpRequestFactory;
    }


    public HttpRequestProvider(){
        if(OKHTTP_REQUEST){
            httpRequestFactory = new OkHttpRequestFactory();
        }else {
            httpRequestFactory = new OriginHttpRequestFactory();
        }
    }

    public HttpRequest getHttpRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return httpRequestFactory.createHttpRequest(uri,httpMethod);
    }
}
