package com.ihaveu.iuzuan.filedownload.file;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * User: bkzhou
 * Date: 2018/1/19
 * Description:文件管理
 */
public class FileStorageManager {
    private static final FileStorageManager sManager = new FileStorageManager();
    private Context mContext;

    public static FileStorageManager getInstance() {
        return sManager;
    }

    private FileStorageManager() {

    }

    public void init(Context context) {
        mContext = context;
    }


    public File getFileByName(String url) {
        File parent;
        //判断内置 sd 卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            parent = mContext.getExternalCacheDir();
        } else {
            parent = mContext.getCacheDir();
        }
        String fileNam = url;
        File file = new File(parent, fileNam);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
