package com.tangjianbo.concurrent;

/**
 * 测试Object 对象的wait和notify 方法
 * todo:调用奇数线程打印奇数，调用偶数线程打印偶数
 *
 * @author 18482
 */
public class TestWaitNotify {
    private int number = 0;
    private Object object = new Object();
    private static final Integer TEN = 10;

    /**
     * 打印奇数线程
     */
    public void odd() {
        synchronized (object) {
            while (number < TEN) {
                if (number % 2 == 1) {
                    System.out.println("奇数线程打印->" + number);
                    number++;
                    object.notifyAll(); //唤醒偶数线程打印
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 打印偶数线程
     */
    public void even() {
        synchronized (object) {
            while (number < TEN) {
                if (number % 2 == 0) {
                    System.out.println("偶数线程打印->" + number);
                    number++;
                    object.notifyAll();//唤醒奇数线程打印
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final TestWaitNotify testWaitNotify = new TestWaitNotify();
        new Thread(testWaitNotify::odd).start();
        new Thread(testWaitNotify::even).start();
    }
}
