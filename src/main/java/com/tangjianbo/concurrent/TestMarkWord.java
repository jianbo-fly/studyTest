package com.tangjianbo.concurrent;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 18482
 */
public class TestMarkWord {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
