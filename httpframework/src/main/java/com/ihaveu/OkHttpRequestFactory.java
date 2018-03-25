package com.ihaveu;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpRequest;
import com.squareup.okhttp.OkHttpClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * User: bkzhou
 * Date: 2018/3/23
 * Description:
 */
public class OkHttpRequestFactory implements HttpRequestFactory {


    private OkHttpClient mClient;

    public OkHttpRequestFactory() {
        this.mClient = new OkHttpClient();
    }
    public OkHttpRequestFactory(OkHttpClient client) {
        this.mClient = client;
    }

    public void setReadTimeOut(int readTimeOut) {

        mClient.setReadTimeout(readTimeOut, TimeUnit.MILLISECONDS);
    }

    public void setWriteTimeOut(int writeTimeOut) {
        mClient.setWriteTimeout(writeTimeOut, TimeUnit.MILLISECONDS);
    }

    public void setConnectionTime(int connectionTime){
         mClient.setConnectTimeout(connectionTime,TimeUnit.MILLISECONDS);

    }

    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method) {
        return new OkHttpRequest(mClient, uri.toString(), method);
    }
}
