package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpRequest;
import com.ihaveu.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public abstract class AbstractHttpRequest implements HttpRequest{

    private static  final String GZIP = "gzip";
    private HttpHeader mHeader = new HttpHeader();

    private ZipOutputStream mZip;

    private boolean executed;

    @Override
    public HttpHeader getHeaders() {
        return mHeader;
    }

    @Override
    public HttpResponse execute() throws IOException {
        if(mZip!=null){
            mZip.close();
        }
        HttpResponse response = executeInternal(mHeader);
        executed = true;
        return response;
    }

    /**
     * 执行网络请求
     * @return
     */
    protected abstract HttpResponse executeInternal(HttpHeader header);

    @Override
    public OutputStream getBody() {
        OutputStream body = getBodyOutputStream();
        if(isGzip()){
            return getGzipOutStream(body);
        }
        return body;
    }

    private boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();
        if(GZIP.equals(contentEncoding)){
            return true;
        }
        return false;
    }

    /**
     * 获取
     * @return
     */
    protected abstract OutputStream getBodyOutputStream();

    /**
     * 返回 zip 输出流
     * @param body
     * @return
     */
    private OutputStream getGzipOutStream(OutputStream body) {
        if(this.mZip ==null){
            //使用GZIPOutputStream进行GZIP压缩：
            this.mZip = new ZipOutputStream(body);
        }
        return  mZip;
    }



}
