package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpStatus;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: bkzhou
 * Date: 2018/3/15
 * Description:okhttp对AbstractHttpResponse实现
 */
public class OkHttpResponse extends AbstractHttpResponse {

    //okhttp的 Response
    private Response mResponse;

    private HttpHeader mHeaders;

    public OkHttpResponse(Response mResponse) {
        this.mResponse = mResponse;
    }


    @Override
    public HttpHeader getHeaders() {
        if(mHeaders == null){
            mHeaders = new HttpHeader();
        }
        //把mResponse的 head 值复制给mHeaders
        for (String name : mResponse.headers().names()) {
            mHeaders.set(name,mResponse.headers().get(name));
        }
        return mHeaders;
    }


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getValue(mResponse.code());
    }

    @Override
    public String getStatusMsg() {
        return mResponse.message();
    }

    @Override
    protected void closeInternal() throws IOException {

        mResponse.body().close();
    }

    @Override
    protected InputStream getBodyInternal() {
        return mResponse.body().byteStream();
    }
}
