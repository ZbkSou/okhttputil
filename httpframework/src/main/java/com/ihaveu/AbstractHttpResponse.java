package com.ihaveu;

import com.ihaveu.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * User: bkzhou
 * Date: 2018/3/15
 * Description: 用抽象类实现 httpResponse 为拓展AbstractHttpResponse铺垫
 * okhttp 已经做着这些事为什么还要重复做
 */
public abstract class AbstractHttpResponse implements HttpResponse {

    private static final String GZIP = "gzip";

    //保存 Gzip 流
    private InputStream mGzipInputStrem;

    /**
     * 返回是否gzip压缩格式
     *
     * @return
     */
    private boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();
        if (GZIP.equals(contentEncoding)) {
            return true;
        }
        return false;
    }


    /**
     * 关闭操作 关闭各种流
     */
    @Override
    public void close() throws IOException {
        if(mGzipInputStrem!=null){
            mGzipInputStrem.close();
        }
         closeInternal();

    }

    /**
     * 子类关闭操作
     */
    protected abstract void closeInternal() throws IOException;

    /**
     * 获得结果体
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getBody() throws IOException{
        InputStream body = getBodyInternal();
        if (isGzip()) {
            getBodyGzip(body);
        }
        return body;
    }



    /**
     * 获得结果体
     *
     * @return
     */
    protected abstract InputStream getBodyInternal() throws IOException;

    /**
     * 解压缩 Gzip
     * @param body
     * @return
     * @throws IOException
     */
    private InputStream getBodyGzip(InputStream body) throws IOException {
        if(this.mGzipInputStrem ==null){
            //使用GZIPInputStream进行GZIP解压缩
            this.mGzipInputStrem = new GZIPInputStream(body);
        }
        return mGzipInputStrem;
    }
}
