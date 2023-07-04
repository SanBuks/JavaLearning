package org.learn.java.concurrency.create;

import org.junit.jupiter.api.Test;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(getName() + "-" + i);
//            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}

class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("001");
        MyThread myThread2 = new MyThread("002");

        // 多线程运行
        myThread1.start();
        myThread2.start();

        // 主线程 单线程顺序调用两个普通函数
        myThread1.run();
        myThread2.run();
    }
}
