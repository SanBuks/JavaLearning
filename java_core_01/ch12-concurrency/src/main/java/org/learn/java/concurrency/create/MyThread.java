package org.learn.java.concurrency.create;

import org.junit.jupiter.api.Test;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            System.out.println(getName() + "-" + i);
//            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}

class MyThreadTest {
    @Test
    public void test() {
        MyThread myThread1 = new MyThread("001");
        MyThread myThread2 = new MyThread("002");

        // 多线程运行
        myThread1.start();
        myThread2.start();

        // 单线程
        myThread1.run();
        myThread2.run();
    }
}
