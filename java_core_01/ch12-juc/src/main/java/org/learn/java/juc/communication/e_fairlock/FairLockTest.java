package org.learn.java.juc.communication.e_fairlock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {
    int ticket = 40;
//    final Lock lock = new ReentrantLock(true);
    final Lock lock = new ReentrantLock(false);

    public void saleLock() {
        lock.lock();
        try {
            if (ticket > 0) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": " + ticket);
                --ticket;
            }
        } finally {
            lock.unlock();
        }
    }


    @Test
    public void testLock() throws InterruptedException {
        FairLockTest source = new FairLockTest();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 40; ++i) {
                source.saleLock();
            }
        }, "aa");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 40; ++i) {
                source.saleLock();
            }
        }, "bb");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 40; ++i) {
                source.saleLock();
            }
        }, "cc");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
