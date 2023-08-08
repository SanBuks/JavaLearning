package org.learn.java.concurrency.f_lock;

import java.util.concurrent.locks.ReentrantLock;

class Window implements Runnable {
    private int ticketNum = 100;
    // 这里是 static 还是 non-static 看 runnable 的用法
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticketNum > 0) {
                    try {
                        Thread.sleep(10);
                        Thread.yield();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
                    --ticketNum;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

public class WindowLock {
    public static void main(String[] args) {
        Window a = new Window();

        Thread t1 = new Thread(a, "01");
        Thread t2 = new Thread(a, "02");
        Thread t3 = new Thread(a, "03");

        t1.start();
        t2.start();
        t3.start();
    }
}
