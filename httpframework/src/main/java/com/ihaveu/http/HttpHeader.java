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


    public final static String Accept="Accept";


    public final static String Pragma="Pragma";

    private Map<String ,String > mMap = new HashMap<>();

    public String getAccept(){
        return get(Accept);
    }
    public void setAccept(String value){
        set(Accept,value);
    }

    public  String getPragma() {
        return get(Pragma);
    }

    public void setPragma(String value){
        set(Pragma,value);
    }

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
