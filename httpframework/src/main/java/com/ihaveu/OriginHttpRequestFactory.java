package com.ihaveu;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

/**
 * Created by ZBK on 2018-03-25.
 */

public class OriginHttpRequestFactory implements HttpRequestFactory {
    private HttpURLConnection mConnection;

    public OriginHttpRequestFactory() {

    }

    public void setReadTimeOut(int readTimeOut) {
        mConnection.setReadTimeout(readTimeOut);
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        mConnection.setConnectTimeout(connectionTimeOut);
    }


    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException {

        mConnection = (HttpURLConnection) uri.toURL().openConnection();

        return new OriginHttpRequest(mConnection,method, uri.toString());
    }
}
