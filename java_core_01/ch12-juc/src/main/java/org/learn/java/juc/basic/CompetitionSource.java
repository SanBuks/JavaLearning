package org.learn.java.juc.basic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CompetitionSource {
    int ticket = 300;
    final Lock reentrantLock = new ReentrantLock();

    public synchronized void saleSyn() {
        if (ticket > 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": " + ticket);
            --ticket;
        }
    }

    public void saleLock() {
        if (reentrantLock.tryLock()) {
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
                reentrantLock.unlock();
            }
        }
    }

    @Test
    public void testSyn() throws InterruptedException {
        CompetitionSource source = new CompetitionSource();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 60; ++i) {
                source.saleSyn();
            }
        }, "aa");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 60; ++i) {
                source.saleSyn();
            }
        }, "bb");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    @Test
    public void testLock() throws InterruptedException {
        CompetitionSource source = new CompetitionSource();
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

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
