package com.ihaveu.service;

import android.support.annotation.NonNull;

import com.ihaveu.HttpRequestProvider;
import com.ihaveu.http.HttpRequest;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.TransformerFactory;

/**
 * User: bkzhou
 * Date: 2018/3/26
 * Description:
 */
public class WorkStation {
    private static  final int  MAX_REQUEST_SIZE = 60;
    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60,
        TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {

        private AtomicInteger index = new AtomicInteger();
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread  thread = new  Thread(runnable);
            thread.setName("http thread name is"+index);
            return thread;
        }
    });

    private Deque<MoocRequest> mRunning = new ArrayDeque<>();

    private Deque<MoocRequest> mCache= new ArrayDeque<>();


    private HttpRequestProvider mRequestProvider;



    public WorkStation(){
        mRequestProvider = new HttpRequestProvider();
    }

    public void  add(MoocRequest request){

        HttpRequest httpRequest = null;
        if(mRunning.size()>MAX_REQUEST_SIZE){
            mCache.add(request);
        }else {
            doHttpRequest( request);
        }
    }

    public void doHttpRequest(MoocRequest request){
        HttpRequest httpRequest = null;
        try {
            httpRequest = mRequestProvider.getHttpRequest(URI.create(request.getUrl()),request.getMethod());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sThreadPool.execute(new HttpRunnable(httpRequest,request,this));
    }
    public void finish(MoocRequest request){
        mRunning.remove(request);
        if(mRunning.size()>MAX_REQUEST_SIZE){
            return;
        }
        if(mCache.size()==0){
            return;
        }
        Iterator<MoocRequest> iterator =mCache.iterator();
        while (iterator.hasNext()){
            MoocRequest next = iterator.next();
            mRunning.add(next);
            iterator.remove();
            doHttpRequest( request);
        }
    }

}
