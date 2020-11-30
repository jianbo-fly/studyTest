package com.tangjianbo.concurrent;

public class TestdeadLock {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().join();
        System.out.println("代码不会进入这里，上面死锁了！");
    }
}
