package com.ihaveu.iuzuan.okhttp;




import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: bkzhou
 * Date: 2018/2/1
 * Description:
 */
public class CacheHttp {
    public static void main(String args[]) throws IOException{
        int maxCacheSize = 20*1024*1024;
        Cache cache = new Cache(new File("/Users/iuzuan/Documents/documents"),maxCacheSize) ;
        OkHttpClient client = new OkHttpClient.Builder()
            .cache(cache)
            .build();
        Request request = new Request.Builder()
            .url("http://www.qq.com")
//            .cacheControl(new CacheControl.Builder().noCache().build())//不使用缓存
            .cacheControl(new CacheControl.Builder().maxStale(365, TimeUnit.DAYS).build())//一直使用缓存
            .build();
        Response response = client.newCall(request).execute();

        String body1 = response.body().string();
        System.out.println("net response "+ response.networkResponse());
        System.out.println("cache response "+ response.cacheResponse());

        System.out.println("------------------");
        Response response2 = client.newCall(request).execute();
        String body2 = response2.body().string();
        System.out.println("net response "+ response2.networkResponse());
        System.out.println("cache response "+ response2.cacheResponse());
    }
}
