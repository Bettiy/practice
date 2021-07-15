package com.betty.practice.test;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Betty
 * @date 2021年07月15日
 */
public class Thread {

    public static volatile int num = 0;

    public static final Object o = 1;

    public static class T extends java.lang.Thread {
        @SneakyThrows
        public void run() {
            synchronized (Thread.class) {
                for (int i = 0; i < 100; i++) {
                    num++;
                }
                System.out.println(java.lang.Thread.currentThread().getName() + "\tnum = " + num);
                TimeUnit.MILLISECONDS.sleep(100);
            }

        }
    }

    public static void main(String[] args) {
        T[] t = new T[100];
        for (int i = 0; i < t.length; i++) {
            t[i] = new T();
        }
        for (int i = 0; i < 100; i++) {
            t[i].start();
        }
    }

}
