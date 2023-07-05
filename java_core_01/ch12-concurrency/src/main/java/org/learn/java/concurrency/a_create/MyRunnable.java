package org.learn.java.concurrency.a_create;

public class MyRunnable implements Runnable {
    // 1. 内部: 数据 与 run 方法分离
    private final int data = 3;

    @Override
    public void run() {
        for (int i = data; i < 100; ++i) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}

class MyRunnableTest {
    public static void main(String[] args) {
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread thread1 = new Thread(myRunnable1, "001");
        // 2. 外部 myRunnable1 可以复用, 以对象为单位进行数据共享
        Thread thread11 = new Thread(myRunnable1, "011");
        Thread thread2 = new Thread(myRunnable2, "002");

        thread1.start();
        thread11.start();
        thread2.start();
    }
}
