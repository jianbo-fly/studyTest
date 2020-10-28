package com.tangjianbo.concurrent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 比较两个cas的同步方法和synchronized的速度
 *
 * @author 18482
 */
public class TestLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();
    static int loopCount = 1000;
    static int addCount = 100000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        Object lock = new Object();
        for (int i = 0; i < loopCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < addCount; j++) {
                    synchronized (lock) {
                        count2++;
                    }
                }
            });
        }
        for (Thread thread : threads) thread.start();
        Long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.join();
        System.out.println(count2 + "  使用synchronized的时间为：" + (System.currentTimeMillis() - start));

        for (int i = 0; i < loopCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < addCount; j++) {
                    count1.incrementAndGet();
                }
            });
        }
        for (Thread thread : threads) thread.start();
        start = System.currentTimeMillis();
        for (Thread thread : threads) thread.join();
        System.out.println(count2 + "  使用AtomicLong的时间为：" + (System.currentTimeMillis() - start));

        for (int i = 0; i < loopCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < addCount; j++) {
                    count3.increment();
                }
            });
        }
        for (Thread thread : threads) thread.start();
        start = System.currentTimeMillis();
        for (Thread thread : threads) thread.join();
        System.out.println(count3 + "  使用LongAdder的时间为：" + (System.currentTimeMillis() - start));

    }
}
