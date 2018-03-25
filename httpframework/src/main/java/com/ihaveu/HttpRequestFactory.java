package com.ihaveu;

import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpRequest;

import java.io.IOException;
import java.net.URI;

/**
 * User: bkzhou
 * Date: 2018/3/23
 * Description:工厂
 */
public interface HttpRequestFactory {
    HttpRequest createHttpRequest(URI uri, HttpMethod method)throws IOException;

}
