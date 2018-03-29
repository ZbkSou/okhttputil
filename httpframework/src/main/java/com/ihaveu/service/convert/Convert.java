package com.ihaveu.service.convert;

import com.ihaveu.http.HttpResponse;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public interface Convert {
    Object pares(HttpResponse response, Type type) throws  IOException;

    Object pares(String content,Type type)throws  IOException;

    boolean isCanParse(String contentType);
}
