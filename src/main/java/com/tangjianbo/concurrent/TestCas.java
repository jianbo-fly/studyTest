package com.tangjianbo.concurrent;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 18482
 */
public class TestCas {
    public static void main(String[] args) {
        int s = 16;
        System.out.println(s << 1);

        AtomicReference<Double> reference = new AtomicReference();
        ConcurrentHashMap map = new ConcurrentHashMap<>();
        //Lock lock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Integer a = atomicInteger.incrementAndGet();
        System.out.println(a);
        int[] ints = {1,2,3};
     /*   for (int[] re =ints;;){
            System.out.println(Arrays.asList(re));
        }
*/
    }
}
