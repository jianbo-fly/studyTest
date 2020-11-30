package com.tangjianbo.concurrent;

import java.util.concurrent.TimeUnit;

public class TestDirtRead {

    private String name;
    private double meoney;

    private synchronized void setMeoney(String name, double meoney) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.meoney = meoney;
    }

    private synchronized double getMeoney(String name) {
        return meoney;
    }

    public static void main(String[] args) {
        TestDirtRead d = new TestDirtRead();
        Thread s = new Thread(() -> {
            d.setMeoney("11", 100L);
        });
        s.start();
    /*    try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        System.out.println(Thread.currentThread().getName() + d.getMeoney("11"));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + d.getMeoney("11"));
    }
}
