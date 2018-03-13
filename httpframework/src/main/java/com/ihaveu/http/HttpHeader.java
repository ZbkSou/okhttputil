package com.ihaveu.http;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: bkzhou
 * Date: 2018/3/9
 * Description:http 请求头
 */
public class HttpHeader implements NameValueMap<String,String>{


    public final static String ACCEPT="Accept";
    public final static String PRAGMA="Pragma";
    public  final static String USER_AGENT = "User_Agent";
    public  final static String PROXY_CONNECTION = "Proxy_Connection";
    public final static String ACCEPT_ENCODING = "Accept-Encoding";
    public final static String  CACHE_CONTROL = "Cache-Control";

    private Map<String ,String > mMap = new HashMap<>();

    public String getAccept(){
        return get(ACCEPT);
    }
    public void setAccept(String value){
        set(ACCEPT,value);
    }

    public  String getPragma() {
        return get(PRAGMA);
    }

    public void setPragma(String value){
        set(PRAGMA,value);

    }
    public  String getProxyConnection() {
        return get(USER_AGENT);
    }

    public void setProxyConnection(String value){
        set(USER_AGENT,value);
    }


    public  String getUserAgent() {
        return get(PROXY_CONNECTION);
    }

    public void setUserAgent(String value){
        set(PROXY_CONNECTION,value);
    }

    public  String getAcceptEncodeing() {
        return get(ACCEPT_ENCODING);
    }

    public void setAcceptEncodeing(String value){
        set(ACCEPT_ENCODING,value);
    }

    public  String getCacheControl() {
        return get(CACHE_CONTROL);
    }

    public void setCacheControl(String value){
        set(CACHE_CONTROL,value);
    }
//////////////////////////////
    @Override
    public String get(String key) {
        return mMap.get(key);
    }

    @Override
    public void set(String key, String Value) {
        mMap.put(key,Value);
    }

    @Override
    public void setAll(Map<String, String> map) {
        mMap.putAll(map);
    }




    @Override
    public int size() {
        return mMap.size();
    }

    @Override
    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return mMap.containsValue(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return mMap.containsValue(o);
    }

    @Override
    public String get(Object o) {
        return mMap.get(o);
    }

    @Override
    public String put(String s, String s2) {
        return mMap.put(s,s2);
    }

    @Override
    public String remove(Object o) {
        return mMap.remove(o);
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ? extends String> map) {
        mMap.putAll(map);
    }

    @Override
    public void clear() {
        mMap.clear();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return mMap.keySet();
    }

    @NonNull
    @Override
    public Collection<String> values() {
        return mMap.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return mMap.entrySet();
    }


}
