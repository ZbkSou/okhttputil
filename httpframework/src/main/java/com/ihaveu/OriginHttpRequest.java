package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZBK on 2018-03-25.
 */

public class OriginHttpRequest extends BufferHttpRequest{

    private HttpURLConnection mConnection;

    private String mUrl;
    private HttpMethod method;
    public OriginHttpRequest(HttpURLConnection connection,HttpMethod method,String url){
        this.mUrl = url;
        this.method = method;
        this.mConnection = connection;
    }



    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        for(Map.Entry<String, String> entries : header.entrySet()){
            mConnection.addRequestProperty(entries.getKey(),entries.getValue());
        }
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod(method.name());
        mConnection.connect();
        if(data!=null&& data.length>0){
            OutputStream out = mConnection.getOutputStream();
            out.write(data,0,data.length);
        }
        OriginHttpResponse response = new OriginHttpResponse(mConnection);

        return response;
    }



    @Override
    public URI getUri() {
        return URI.create(mUrl);
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }
}
