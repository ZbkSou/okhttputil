package com.ihaveu.iuzuan.filedownload;

import android.support.annotation.NonNull;

import com.ihaveu.iuzuan.filedownload.db.DownloadEntity;
import com.ihaveu.iuzuan.filedownload.db.DownloadHelper;
import com.ihaveu.iuzuan.filedownload.file.FileStorageManager;
import com.ihaveu.iuzuan.filedownload.http.DownloadCallback;
import com.ihaveu.iuzuan.filedownload.http.HttpManager;
import com.ihaveu.iuzuan.filedownload.utils.Logger;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private List<DownloadEntity> mCache;

    //监控文件下载进度 线程池
    private static final ExecutorService SLOCAL_PROGRESS_POOL = Executors.newFixedThreadPool(1);

    //文件 长度
    private long mLength = 0;
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


    /**
     * 处理任务结束
     *
     * @param task
     */
    private void finish(DownloadTask task) {

        mHashSet.remove(task);
    }

    public void download(final String url, final DownloadCallback callback) {

        final DownloadTask task = new DownloadTask(url, callback);
        if (mHashSet.contains(task)) {
            callback.fail(HttpManager.TASK_RUNNING_ERROR_CODE, "任务已经执行了");
            return;
        }
        //将任务添加到 set
        mHashSet.add(task);
        mCache = DownloadHelper.getInstance().getAll(url);
        if (mCache == null || mCache.size() == 0) {
            //数据库中没有下载记录
            HttpManager.getInstance().asyncRequest(url, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    finish(task);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful() && callback != null) {
                        callback.fail(HttpManager.NETWORK_ERROR_CODE, "网络问题");
                        return;
                    }
                    mLength = response.body().contentLength();
                    if (mLength == -1) {
                        callback.fail(HttpManager.CONTENT_LENGTH_ERROR_CODE, "contentLength -1");
                        return;
                    }
                    processDownload(url, mLength, callback,mCache);
                    finish(task);
                }
            });

        }else {
            //// TODO: 2018/3/2  处理下载过的数据
            for (int  i =0 ; i<mCache.size();i++ ) {
                DownloadEntity entity = mCache.get(i);
                if(i==mCache.size()-1){
                    mLength = entity.getEnd_position()+1;
                }
                //startSize为开始数据加上已完成的进度
                long startSize = entity.getStart_position()+entity.getProgress_position();
                long endSize =  entity.getEnd_position();
                Logger.debug("nate","startSize:"+startSize+"  endSize:"+endSize);
                sThreadPool.execute(new DownloadRunnable(startSize, endSize, url, callback, entity));
            }
        }
        SLOCAL_PROGRESS_POOL.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(500);
                        File file = FileStorageManager.getInstance().getFileByName(url);
                        long fileSize = file.length();
                        int progress = (int)(fileSize*100.0/mLength);
                        if(progress>=100){
                            callback.progress(progress);
                            return;
                        }
                        callback.progress(progress);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void processDownload(String url, long length, DownloadCallback callback,List<DownloadEntity> cache) {
        long threadDownloadSize = length / MAX_THREAD;
        Logger.debug("nate","length："+length+"threadDownloadSize:"+threadDownloadSize);
        if (cache == null && cache.size() == 0) {
            mCache = new ArrayList<>();
        }
        for (int i = 0; i < MAX_THREAD; i++) {
            DownloadEntity entity = new DownloadEntity();
            //计算每个进程下载的长度
            long startSize = i * threadDownloadSize;
            long endSize = 0;
            if (i  == MAX_THREAD - 1) {
                endSize = length - 1;
            } else {
                endSize = (i + 1) * threadDownloadSize - 1;
            }
            Logger.debug("nate","startSize:"+startSize+"  endSize:"+endSize);
            //需要保存的下载信息
            entity.setDownload_url(url);
            entity.setStart_position(startSize);
            entity.setEnd_position(endSize);
            entity.setThread_id(i + 1);
            sThreadPool.execute(new DownloadRunnable(startSize, endSize, url, callback, entity));
        }
    }
}
