package com.ihaveu.http;

import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/3/9
 * Description:利用 map实现http 请求头
 */
public interface NameValueMap<S, S1> extends Map<String,String> {

    String get (String key);

    void set(String key,String Value);

    void  setAll(Map<String,String>map);
}
