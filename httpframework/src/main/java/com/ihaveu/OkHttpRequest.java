package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpResponse;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public class OkHttpRequest extends BufferHttpRequest {

    private OkHttpClient mClient;

    private String url;

    private HttpMethod mMethod;

    public OkHttpRequest(OkHttpClient mClient, String url, HttpMethod mMethod) {
        this.mClient = mClient;
        this.url = url;
        this.mMethod = mMethod;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        boolean isBody = mMethod==HttpMethod.POST;
        RequestBody requestBody = null;
        if(isBody){
            requestBody  = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),data);
        }
        Request.Builder  builder = new Request.Builder().url(url).method(mMethod.name() ,requestBody);

        for (Map.Entry<String,String>entry:header.entrySet()){
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        Response response = mClient.newCall(builder.build()).execute();
        return new OkHttpResponse(response);
    }


    @Override
    public URI getUri() {
        return URI.create(url);
    }

    @Override
    public HttpMethod getMethod() {
        return mMethod;
    }
}
