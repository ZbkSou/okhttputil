package com.ihaveu.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: bkzhou
 * Date: 2018/3/14
 * Description:解析请求结果
 */
public interface HttpResponse extends Header,Cloneable{

    /**
     * 获得状态
     * @return
     */
    HttpStatus getStatus();

    /**
     * 获得状态信息
     * @return
     */
    String getStatusMsg();

    /**
     * 获得 body
     * @return
     * @throws IOException
     */
    InputStream getBody() throws IOException;

    /**
     * 关闭
     * @throws IOException
     */
    void close()throws IOException;

    /**
     * 获得长度
     * @return
     */
    long getContentLength();
}
