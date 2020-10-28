package com.tangjianbo.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 测试tryLock方法
 * @author 18482
 */
public class TestReentrantLock {
    public static void main(String[] args) {

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("tryLock-pool-%d").build();
        ExecutorService service = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024),
                factory, new ThreadPoolExecutor.AbortPolicy());
        Lock lock = new ReentrantLock();
        service.execute(() -> {
            System.out.println("上锁成功！");
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("释放锁成功！");
            }
        });
        service.execute(() -> {
            long count = 0L;
            while (true) {
                boolean getLock = lock.tryLock();
                //  System.out.println(getLock);
                count++;
                if (getLock) {
                    break;
                }
            }
            System.out.println("拿锁的次数:" + count + "线程2执行完毕！");
        });
        service.shutdown();

    //没有使用线程池的方法
/*        Lock lock = new ReentrantLock();
        new Thread(() -> {
            System.out.println("上锁成功！");
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("释放锁成功！");
            }
            System.out.println("");
        }, "线程1").start();

        new Thread(() -> {
            long count = 0L;
            while (true) {
                boolean getLock = lock.tryLock();
                //  System.out.println(getLock);
                count++;
                if (getLock) {
                    break;
                }
            }
            System.out.println("拿锁的次数:" + count + "线程2执行完毕！");
        }, "线程2").start();*/
    }
}
