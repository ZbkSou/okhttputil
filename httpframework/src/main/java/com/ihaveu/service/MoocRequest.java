package com.ihaveu.service;

import com.ihaveu.http.HttpMethod;

import java.util.concurrent.Delayed;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class MoocRequest  {
    private String mUrl;
    private HttpMethod mMethod;

    private byte[] mData;

    private MoocResponse mResponse;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public void setMethod(HttpMethod mMethod) {
        this.mMethod = mMethod;
    }

    public byte[] getData() {
        return mData;
    }

    public void setData(byte[] mData) {
        this.mData = mData;
    }

    public MoocResponse getResponse() {
        return mResponse;
    }

    public void setResponse(MoocResponse mResponse) {
        this.mResponse = mResponse;
    }

}
