package org.learn.java.concurrency.f_lock;

import java.util.concurrent.locks.ReentrantLock;

class Window implements Runnable {
    private int ticketNum = 100;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            while (ticketNum > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
                --ticketNum;
            }
        } finally {
            lock.unlock();
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
