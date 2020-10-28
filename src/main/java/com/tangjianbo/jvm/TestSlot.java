package com.tangjianbo.jvm;

/**
 * @author 18482
 */
public class TestSlot {
    public void test1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void test2() {
        int a = 0;
        {
            System.out.println(a);
            int b = 0;
        }
    }

    public void test3() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        int c = a + 1;
    }

    public void test4() {
        int a = 3;
        int b = 5;
        int c = a+b;
        System.out.println(c);
    }

}
