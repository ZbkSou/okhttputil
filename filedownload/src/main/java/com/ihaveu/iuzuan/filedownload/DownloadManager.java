package com.ihaveu.iuzuan.filedownload;

import android.support.annotation.NonNull;

import com.ihaveu.iuzuan.filedownload.http.DownloadCallback;
import com.ihaveu.iuzuan.filedownload.http.HttpManager;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: bkzhou
 * Date: 2018/1/23
 * Description:
 */
public class DownloadManager {

    private final static int MAX_THREAD = 2;


    private HashSet<DownloadTask> mHashSet = new HashSet<>();
    //线程池
    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 60, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
        private AtomicInteger mIntegert = new AtomicInteger();

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable, "download thread #" + mIntegert.getAndIncrement());
            return thread;
        }
    });

    private static class DownloadManagerInstance {
        private static final DownloadManager manager = new DownloadManager();
    }

    public static DownloadManager getInstance() {
        return DownloadManagerInstance.manager;
    }

    private DownloadManager() {

    }


    public void download(final String url, final DownloadCallback callback) {

        DownloadTask task = new DownloadTask(url,callback);
        if(mHashSet.contains(task)){
            callback.fail();

        }

        HttpManager.getInstance().asyncRequest(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful() && callback != null) {
                    callback.fail(HttpManager.NETWORK_ERROR_CODE, "网络问题");
                    return;
                }
                long length = response.body().contentLength();
                if (length == -1) {
                    callback.fail(HttpManager.CONTENT_LENGTH_ERROR_CODE, "contentLength -1");
                    return;
                }
                processDownload(url,length,callback);
            }


        });
    }

    private void processDownload(String url, long length, DownloadCallback callback) {
        long threadDownloadSize = length /MAX_THREAD;
        for (int i = 0; i < MAX_THREAD; i++) {
            long startSize = i*threadDownloadSize;
            long endSize = (i+1)*threadDownloadSize-1;
            sThreadPool.execute(new DownloadRunnable(startSize,endSize,url,callback));
        }
    }
}
