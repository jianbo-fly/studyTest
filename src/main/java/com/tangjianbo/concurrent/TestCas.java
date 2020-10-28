package com.tangjianbo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 18482
 */
public class TestCas {
    public static void main(String[] args) {
        AtomicReference<Double> reference = new AtomicReference();
        //Lock lock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Integer a = atomicInteger.incrementAndGet();
        System.out.println(a);
    }
}
