package com.tangjianbo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 18482
 */
public class TestAqs {

    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程1->加锁成功！");
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程1 释放锁成功！");
            }

        }, "t1").start();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程2->加锁成功！");
            } finally {
                lock.unlock();
                System.out.println("线程2 释放锁成功！");
            }

        }, "t2").start();
    }

    /**
     * 独占锁，同步器类
     */
    static class MySyn extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = -2496859875492430358L;

        /**
         * 拿锁,失败进入等待队列，并自己打断  selfInterrupt();
         *
         * @param arg 传入的状态
         * @return 是否获取锁成功
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁，释放成功后，唤醒在等待对列的线程
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(arg);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private static MySyn syn = new MySyn();

    /**
     * 自定义锁
     */
    static class MyLock implements Lock {

        @Override
        public void lock() {
            syn.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
             syn.acquireInterruptibly(1);
        }

        //拿锁，尝试一次
        @Override
        public boolean tryLock() {
            return syn.tryAcquire(1);
        }

        //拿锁,带超时
        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return syn.tryAcquireNanos(1, unit.toNanos(time));
        }

        //释放锁
        @Override
        public void unlock() {
            syn.release(0);
        }

        //创建条件变量
        @Override
        public Condition newCondition() {
            return syn.newCondition();
        }
    }
}