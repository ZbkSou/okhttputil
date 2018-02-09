package com.ihaveu.iuzuan.okhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: bkzhou
 * Date: 2018/2/7
 * Description:
 */
public class ThreadPoolTest {

    static class MyRunnbale implements Runnable {
        private volatile boolean flag = true;

        @Override
        public void run() {
            while (flag && !Thread.interrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("running");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
       /* final ArrayBlockingQueue queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60,
            TimeUnit.MILLISECONDS,queue);
        for (int i = 0; i < 15; i++) {
            final int index=i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("index ="+index+" queue.size ="+queue.size());
                }});
        }*/
        MyRunnbale run = new MyRunnbale();
        Thread thread = new Thread(run);
        thread.start();
        Thread.sleep(1000);
        run.flag = false;
        thread.interrupt();
    }


}
