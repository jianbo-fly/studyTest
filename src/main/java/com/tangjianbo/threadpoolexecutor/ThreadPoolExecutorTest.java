package com.tangjianbo.threadpoolexecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 使用线程池  测试CountDownLatch
 *
 * @author 18482
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Task task = new Task(countDownLatch);
        Task task2 = new Task(countDownLatch);
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("countDownLatch-pool-%d").build();
        ExecutorService service = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024),
                factory, new ThreadPoolExecutor.AbortPolicy());

        service.execute(task);
        service.execute(task2);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println("结束！");
    }

    static class Task implements Runnable {
        private final CountDownLatch countDownLatch;

        Task(CountDownLatch latch) {
            this.countDownLatch = latch;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName());
                System.out.println("睡眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                // System.out.println(countDownLatch.getCount());
            }
        }
    }
}
