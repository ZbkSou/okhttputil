package com.ihaveu.service;

import com.ihaveu.http.HttpRequest;
import com.ihaveu.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class HttpRunnable implements  Runnable{
    private HttpRequest mHttpRequest;
    private MoocRequest moocRequest;
    private WorkStation mWorkStation;
    public HttpRunnable(HttpRequest HttpRequest, MoocRequest moocRequest,WorkStation workStation) {
        this.mHttpRequest = HttpRequest;
        this.moocRequest = moocRequest;
        this.mWorkStation = workStation;
    }

    @Override
    public void run() {
        try {
            mHttpRequest.getBody().write(moocRequest.getData());
            HttpResponse response = mHttpRequest.execute();
            String contentType = response.getHeaders().getContentType();
            moocRequest.setContentType(contentType);

            if(response.getStatus().isSuccess()){
                if(moocRequest.getResponse()!=null){
                    moocRequest.getResponse().sucess(moocRequest,new String(getData(response)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            mWorkStation.finish(moocRequest);
        }
    }
    public byte[] getData(HttpResponse response ){
        ByteArrayOutputStream outputStream   = new ByteArrayOutputStream();
        int len ;
        byte[] data = new byte[512];
        try {
            while ((len = response.getBody().read(data))!=-1){
                outputStream.write(data,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
