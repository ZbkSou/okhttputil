package com.ihaveu.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public interface HttpRequest {

    HttpHeader getHeaders();
    OutputStream getBody();

    HttpResponse execute() throws IOException;
}
