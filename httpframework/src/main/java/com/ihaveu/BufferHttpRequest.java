package com.ihaveu;

import com.ihaveu.http.HttpHeader;
import com.ihaveu.http.HttpResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public abstract class BufferHttpRequest extends AbstractHttpRequest{

    private ByteArrayOutputStream mByteArray = new ByteArrayOutputStream();

    protected OutputStream getBodyInternal(){
        return mByteArray;
    }



    protected HttpResponse executeInternal(HttpHeader header) {
        byte[] data  = mByteArray.toByteArray();
        return executeInternal(header,data);
    }

    protected abstract HttpResponse executeInternal(HttpHeader header, byte[] data);
}
