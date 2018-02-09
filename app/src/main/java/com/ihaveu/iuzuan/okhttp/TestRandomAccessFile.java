package com.ihaveu.iuzuan.okhttp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * User: bkzhou
 * Date: 2018/2/6
 * Description:
 */
public class TestRandomAccessFile {
//    public static void main(String[] args) {
//        RandomAccessFile rf = null;
//        try {
//            rf = new RandomAccessFile("/Users/iuzuan/Documents/documents/rtest.txt", "rw");
//            rf.seek(4 * 8);
//            for (int i = 4; i < 10; i++) {
//                //写入基本类型double数据
//                rf.writeDouble(i * 1.414);
//            }
//            rf.close();
//            rf = new RandomAccessFile("/Users/iuzuan/Documents/documents/rtest.txt", "rw");
//            for (int i = 0; i < 10; i++) {
//                System.out.println("Value " + i + ": " + rf.readDouble());
//            }
//            //直接将文件指针移到第5个double数据后面
//
//
//            rf.seek(5 * 8);
//            //覆盖第6个double数据
//            rf.writeDouble(47.0001);
//            rf.close();
//            rf = new RandomAccessFile("/Users/iuzuan/Documents/documents/rtest.txt", "r");
//            for (int i = 0; i < 10; i++) {
//                System.out.println("Value " + i + ": " + rf.readDouble());
//            }
//            rf.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile raf = new RandomAccessFile("/Users/iuzuan/Documents/documents/rtest.txt", "rw");
        raf.setLength(1024*1024); // 预分配 1M 的文件空间
        raf.close();

        // 所要写入的文件内容
        String s1 = "第一个字符串";
        String s2 = "第二个字符串";
        String s3 = "第三个字符串";
        String s4 = "第四个字符串";
        String s5 = "第五个字符串";

        // 利用多线程同时写入一个文件
        new FileWriteThread(1024*1,s1.getBytes()).start(); // 从文件的1024字节之后开始写入数据
        new FileWriteThread(1024*2,s2.getBytes()).start(); // 从文件的2048字节之后开始写入数据
        new FileWriteThread(1024*3,s3.getBytes()).start(); // 从文件的3072字节之后开始写入数据
        new FileWriteThread(1024*4,s4.getBytes()).start(); // 从文件的4096字节之后开始写入数据
        new FileWriteThread(1024*5,s5.getBytes()).start(); // 从文件的5120字节之后开始写入数据
    }

    // 利用线程在文件的指定位置写入指定数据
    static class FileWriteThread extends Thread{
        private int skip;
        private byte[] content;

        public FileWriteThread(int skip,byte[] content){
            this.skip = skip;
            this.content = content;
        }

        public void run(){
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile("/Users/iuzuan/Documents/documents/rtest.txt", "rw");
                raf.seek(skip);
                raf.write(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    raf.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
