package com.ihaveu.iuzuan.filedownload;

import com.ihaveu.iuzuan.filedownload.file.FileStorageManager;
import com.ihaveu.iuzuan.filedownload.http.DownloadCallback;
import com.ihaveu.iuzuan.filedownload.http.HttpManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * User: bkzhou
 * Date: 2018/1/23
 * Description:处理多线程下载
 */
public class DownloadRunnable implements Runnable{

    private long mStart ;
    private long mEnd ;
    private String mUrl ;
    private DownloadCallback mCallback;

    public DownloadRunnable(long mStart, long mEnd, String mUrl, DownloadCallback mCallback) {
        this.mStart = mStart;
        this.mEnd = mEnd;
        this.mUrl = mUrl;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {
       Response response =  HttpManager.getInstance().syncRequestByRange(mUrl,mStart,mEnd);
        if(response == null && mCallback!= null){
            mCallback.fail(HttpManager.NETWORK_ERROR_CODE,"网络问题");
            return;
        }
        File file  = FileStorageManager.getInstance().getFileByName(mUrl);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rwd");
            randomAccessFile.seek(mStart);
            byte[] buffer = new  byte[1024*500];
            int  len;
            InputStream inSteam = response.body().byteStream();
            while ((len = inSteam.read(buffer,0,buffer.length))!=-1){
                randomAccessFile.write(buffer,0,len);
            }
            mCallback.success(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
