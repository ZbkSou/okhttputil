package com.ihaveu.iuzuan.okhttp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ihaveu.iuzuan.filedownload.db.DownloadHelper;
import com.ihaveu.iuzuan.filedownload.file.FileStorageManager;
import com.ihaveu.iuzuan.filedownload.http.HttpManager;

/**
 * User: bkzhou
 * Date: 2018/1/22
 * Description:
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(this);
        HttpManager.getInstance().init(this);
        Stetho.initializeWithDefaults(this);
        DownloadHelper.getInstance().init(this);
    }
}
