package com.betty.practice.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Betty
 * @date 2021年07月14日
 */
public class T {

    public volatile static int ticket = 100;

    static final Object o = "1";

    static class Ticket implements Runnable {
        @Override
        public void run() {
            while (ticket >= 0) {
                synchronized (o) {
                    if (ticket > 0) {
                        System.out.println("第" + (100- ticket) + "张票已卖出");
                    } else {
                        System.out.println("票已买完！！");
                    }
                    ticket --;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.submit(new Ticket());
        }

    }

}
