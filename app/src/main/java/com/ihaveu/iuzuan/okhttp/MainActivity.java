package com.ihaveu.iuzuan.okhttp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ihaveu.iuzuan.filedownload.DownloadManager;
import com.ihaveu.iuzuan.filedownload.file.FileStorageManager;
import com.ihaveu.iuzuan.filedownload.http.DownloadCallback;
import com.ihaveu.iuzuan.filedownload.http.HttpManager;
import com.ihaveu.iuzuan.filedownload.utils.Logger;

import java.io.File;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView);
        File file = FileStorageManager.getInstance().getFileByName("http://www.imooc.com");
        Logger.debug("MainActivity", "file path = " + file.getAbsolutePath());

//        HttpManager.getInstance().asyncRequest(
//            "https://img01.sogoucdn.com/app/a/4001/2f6ed00d-753c-4c39-935e-1dd22d465f83.jpg"
//            , new DownloadCallback() {
//                @Override
//                public void success(File file) {
//
//                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    });
//                    Logger.debug("MainActivity","success"+ file.getAbsolutePath());
//                }
//
//                @Override
//                public void fail(int erroCode, String errorMessage) {
//                    Logger.debug("MainActivity","fail"+ erroCode +errorMessage);
//                }
//
//                @Override
//                public void progress(int progress) {
//
//                }
//            });


        Logger.debug("MainActivity",new Date().getTime()+"");
        final String url = "https://raw.githubusercontent.com/getlantern/lantern-binaries/master/lantern-installer.apk";
        DownloadManager.getInstance().download(url, new DownloadCallback() {
            @Override
            public void success(File file) {
                if(count<1){
                    count++;
                    return;
                }
                installApk(file);
//                final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    });
                Logger.debug("MainActivity",new Date().getTime()+"");
                Logger.debug("MainActivity","success"+file.getAbsolutePath());
            }

            @Override
            public void fail(int erroCode, String errorMessage) {
                Logger.debug("MainActivity","erroCode"+erroCode+" "+errorMessage);
            }

            @Override
            public void progress(int progress) {

            }
        });

    }
    private void installApk(File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://"+file.getAbsoluteFile().toString()),"application/vnd.android.package-archive");

        MainActivity.this.startActivity(intent);

    }
}
