package com.tangjianbo.concurrent;

/**
 * 测试ThreadLocal（）；
 *
 * @author 18482
 */
public class TestThreadLocal {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            threadLocal.set("123");
            System.out.println(threadLocal.get());
        }).start();
        new Thread(() -> {
            System.out.println(threadLocal.get());
        }).start();
    }
}
