package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpResponse;
import com.ihaveu.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by ZBK on 2018-03-25.
 * 原生请求结果接入
 */

public class OriginHttpResponse extends AbstractHttpResponse{

    private HttpURLConnection mConnection;

    public OriginHttpResponse(HttpURLConnection mConnection) {
        this.mConnection = mConnection;
    }

    @Override
    public HttpStatus getStatus() {
        try {
            return  HttpStatus.getValue(mConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getStatusMsg() {
        try {
            return mConnection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected InputStream getBodyInternal() throws IOException {
        return  mConnection.getInputStream();
    }


    @Override
    protected void closeInternal() throws IOException {
        mConnection.disconnect();
    }

    @Override
    public HttpHeader getHeaders() {

        HttpHeader header = new HttpHeader();
        for(Map.Entry<String,List<String> > entrymap: mConnection.getHeaderFields().entrySet()){
            header.set(entrymap.getKey(),entrymap.getValue().get(0));
        }

        return header;
    }
}
