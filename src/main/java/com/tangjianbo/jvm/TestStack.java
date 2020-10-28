package com.tangjianbo.jvm;

/**
 * @author 18482
 */
public class TestStack {
    private static final int A = 1;
    public void createMethod() {
        byte[] arr = new byte[1024 * 1024 * 500];
        int a;
        double b = 0L;
        //测试栈溢出
      /*  while (true) {
            createMethod();
        }*/
    }

    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        testStack.createMethod();
    }
}
