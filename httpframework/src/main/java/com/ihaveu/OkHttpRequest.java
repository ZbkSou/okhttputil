package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpResponse;
import com.squareup.okhttp.OkHttpClient;

import java.io.OutputStream;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public class OkHttpRequest extends BufferHttpRequest{
    private OkHttpClient mClient;

    private HttpMethod mHethod;
    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) {
        return null;
    }

    @Override
    protected OutputStream getBodyOutputStream() {
        return null;
    }
}
