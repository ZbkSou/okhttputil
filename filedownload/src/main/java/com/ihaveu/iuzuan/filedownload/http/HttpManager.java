package com.ihaveu.iuzuan.filedownload.http;

import android.content.Context;

import com.ihaveu.iuzuan.filedownload.file.FileStorageManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * User: bkzhou
 * Date: 2018/1/22
 * Description:
 */
public class HttpManager {
    public static final int NETWORK_CODE = 1;
    public static final int NETWORK_ERROR_CODE = 2;
    public static final int CONTENT_LENGTH_ERROR_CODE = 2;
    private Context mContext;


    private static final HttpManager sManager = new HttpManager();

    private OkHttpClient mClient = new OkHttpClient();

    public static HttpManager getInstance() {
        return sManager;
    }

    private HttpManager() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * 同步请求
     *
     * @param url
     * @return
     */
    public Response syncRequestByRange(String url, long start, long end) {
        Request request = new Request.Builder().url(url)
            .addHeader("Range", "bytes=" + start + "-" + end)
            .build();
        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步请求
     *
     * @param url
     * @param callback
     */
    public void asyncRequest(final String url, final DownloadCallback callback) {
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && callback != null) {
                    callback.fail(NETWORK_CODE, "请求失败");
                }
                File file = FileStorageManager.getInstance().getFileByName(url);
                byte[] buffer = new byte[1024 * 500];
                int len;
                FileOutputStream fileOut = new FileOutputStream(file);
                InputStream inSteam = response.body().byteStream();
                while ((len = inSteam.read(buffer, 0, buffer.length)) != -1) {
                    fileOut.write(buffer, 0, len);
                    fileOut.flush();
                }
                callback.success(file);
            }
        });
    }


    public void asyncRequest(final String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(callback);
    }

}
