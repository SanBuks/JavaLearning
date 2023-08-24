package org.learn.java.concurrency.g_communication;

import org.junit.jupiter.api.Test;

class PrintNumber implements Runnable {
    private int num = 20;

    @Override
    public void run() {
        synchronized(this) {
            while (num > 0) {
                // 唤醒等待状态中等待此监视器的线程, 从 wait() 地方执行,
                // 但是没有锁, 从而实现轮流交替打印
                notify();  // this.notify
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + " : " + num--);


                try {
                    // 进入等待状态, 释放同步监视器
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 退出循环后需要唤醒其他等待线程, 注意分叉逻辑及时唤醒其他线程
            notify();
        }
    }
}

public class AlternatePrint {
    @Test
    public void PrintTest() throws InterruptedException {
        PrintNumber p = new PrintNumber();

        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
