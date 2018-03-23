package com.ihaveu.iuzuan.httpframework;

import com.ihaveu.OkHttpRequest;
import com.ihaveu.http.HttpMethod;
import com.ihaveu.http.HttpResponse;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
//
//        OkHttpClient client = new OkHttpClient();
//        OkHttpRequest request = new OkHttpRequest(client,"http://www.imooc.com", HttpMethod.GET);
//        HttpResponse response = request.execute();
//        String content = null;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
//        while ((content = reader.readLine())!=null){
//            System.out.print(content);
//        }
//        response.close();



        OkHttpClient client = new OkHttpClient();
        OkHttpRequest request = new OkHttpRequest(client,"http://www.imooc.com", HttpMethod.POST);
        request.getBody().write("username=weeew&usage=22".getBytes());
        HttpResponse response = request.execute();
        String content = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
        while ((content = reader.readLine())!=null){
            System.out.print(content);
        }
        response.close();

    }
}