package com.ihaveu.iuzuan.filedownload.http;

import java.io.File;

/**
 * User: bkzhou
 * Date: 2018/1/22
 * Description:
 */
public interface DownloadCallback {
    void success(File file);

    void fail(int erroCode, String errorMessage);

    void progress(int progress);
}
