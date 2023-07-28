package org.learn.java.concurrency.g_communication;

import org.junit.jupiter.api.Test;

class PrintNumber implements Runnable {
    private int num = 100;

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

                // 如果不及时退出会出现一个线程永远处于等待状态不会唤醒
                if (num <= 0) break;

                try {
                    // 进入等待状态, 释放同步监视器
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
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
