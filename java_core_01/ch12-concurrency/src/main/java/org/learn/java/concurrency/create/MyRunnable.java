package org.learn.java.concurrency.create;

import org.junit.jupiter.api.Test;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}

class MyRunnableTest {
    @Test
    public void test() {
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2= new Thread(myRunnable2);

        thread1.start();
        thread2.start();
    }
}
