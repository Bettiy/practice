package com.betty.practice.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Betty
 * @date 2021年07月14日
 */
public class TestThreadPoolExecutor {

    static class T implements Runnable {
        @Override
        public void run() {
            System.out.println(java.lang.Thread.currentThread().getName() + "\tdo submit");
        }
    }

    public static void main(String[] args) {
        //创建只有一个线程的定时线程任务的线程池
        ExecutorService es5 = Executors.newCachedThreadPool();
        System.out.println("时间：" + System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            es5.submit(new T());
        }
    }

    public void betty() {
        //创建使用单个线程的线程池
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            es1.submit(() -> System.out.println(java.lang.Thread.currentThread().getName() + "正在执行任务"));
        }
        //创建使用固定线程数的线程池
        ExecutorService es2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            es2.submit(() -> System.out.println(java.lang.Thread.currentThread().getName() + "正在执行任务"));
        }
        //创建一个会根据需要创建新线程的线程池
        ExecutorService es3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            es3.submit(() -> System.out.println(java.lang.Thread.currentThread().getName() + "正在执行任务"));
        }
        //创建拥有固定线程数量的定时线程任务的线程池
        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(2);
        System.out.println("时间：" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            es4.schedule(() -> System.out.println("时间：" + System.currentTimeMillis() + "--" + java.lang.Thread.currentThread().getName() + "正在执行任务"), 3, TimeUnit.SECONDS);
        }
        //创建只有一个线程的定时线程任务的线程池
        ScheduledExecutorService es5 = Executors.newSingleThreadScheduledExecutor();
        System.out.println("时间：" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            es5.schedule(() -> System.out.println("时间：" + System.currentTimeMillis() + "--" + java.lang.Thread.currentThread().getName() + "正在执行任务"), 3, TimeUnit.SECONDS);
        }
    }

}
