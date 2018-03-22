package com.ihaveu.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * User: bkzhou
 * Date: 2018/3/16
 * Description:
 */
public interface HttpRequest {

    HttpHeader getHeaders();
    OutputStream getBody();

    URI getUri();
    HttpMethod getMethod();
    HttpResponse execute() throws IOException;
}
