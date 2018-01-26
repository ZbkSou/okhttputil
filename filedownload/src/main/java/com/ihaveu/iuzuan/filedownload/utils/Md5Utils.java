package com.ihaveu.iuzuan.filedownload.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: bkzhou
 * Date: 2018/1/22
 * Description:
 */

public class Md5Utils {
    public static void main(String args[]){
        System.out.println(generataCode("http://www.imooc.com"));
    }

    public static  String generataCode(String url  ){
//        if(TextUtils.isEmpty(url)){
//            return null;
//        }
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(url.getBytes());
            byte[] cipher = digest.digest();

            for (byte b : cipher) {
                String hexStr = Integer.toHexString(b&0xff);
                buffer.append(hexStr.length()==1?"0"+hexStr:hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
