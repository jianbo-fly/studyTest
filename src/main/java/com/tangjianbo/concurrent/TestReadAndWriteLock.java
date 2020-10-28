package com.tangjianbo.concurrent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读写锁测试
 *
 * @author 18482
 */
public class TestReadAndWriteLock {
    private static int VALUE;

    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReadLock readLock = reentrantReadWriteLock.readLock();
        WriteLock writeLock = reentrantReadWriteLock.writeLock();
        Runnable readLockTask = () -> ReadWriteTask.readLock(readLock);
        Runnable writeLockTask = () -> ReadWriteTask.writeLock(writeLock, 1);
        Thread[] readThreadArrays = new Thread[10];
        Thread[] writeThreadArrays = new Thread[2];
        for (int i = 0; i < 10; i++) {
          readThreadArrays[i] = new Thread(readLockTask, "读线程" + i);
           // readThreadArrays[i] = new MyThread(readLockTask,"线程"+i);
        }
        for (int i = 0; i < 2; i++) {
           writeThreadArrays[i] = new Thread(writeLockTask, "写线程" + i);
         //   writeThreadArrays[i] = new MyThread(writeLockTask, "写线程" + i);
        }
        Arrays.asList(readThreadArrays).forEach(Thread::start);
        Arrays.asList(writeThreadArrays).forEach(Thread::start);
        Arrays.asList(readThreadArrays).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Arrays.asList(writeThreadArrays).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("最后输出的值为： " + VALUE);

    }

    static class ReadWriteTask {
        public static void readLock(Lock lock) {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("读操作完毕");
            }
        }

        public static void writeLock(Lock lock, int v) {
            lock.lock();
            try {
                VALUE = VALUE + v;
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"-->>写操作完毕！");
            }
        }
    }



}

