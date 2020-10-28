package com.tangjianbo.concurrent;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试condition的await 和 signal 方法
 *
 * @author 18482
 */
public class TestAwaitSignal {
    private static final Integer TEN = 10;
    private Integer number = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    /**
     * 奇数线程打印
     */
    public void odd() {
        while (number < TEN) {
            reentrantLock.lock();
            try {
                if (number % 2 == 1) {
                    //业务
                    System.out.println("奇数线程打印->" + number);
                    number++;
                    condition.signalAll();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /**
     * 偶数线程打印
     */
    public void even() {
        while (number < TEN) {
            reentrantLock.lock();
            try {
                if (number % 2 == 0) {
                    System.out.println("偶数线程打印->" + number);
                    number++;
                    condition.signalAll();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /**
     * 测试结果
     *
     * @param args
     */
    public static void main(String[] args) {
        final TestAwaitSignal awaitSignal = new TestAwaitSignal();
        new Thread(awaitSignal::odd).start();
        new Thread(awaitSignal::even).start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Integer.MAX_VALUE);
        System.out.println(2 << 30);
    }
}
