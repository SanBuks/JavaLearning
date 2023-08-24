package org.learn.java.juc.communication.b_custom;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FlagCommunication {
    @Test
    public void test() {
        SharedResource resource = new SharedResource();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                try {
                    resource.print5(i + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "aa");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                try {
                    resource.print10(i + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "bb");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                try {
                    resource.print15(i + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "cc");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SharedResource {
    // 1: AA 2: BB 3: CC
    private int flag = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition con1 = lock.newCondition();
    private final Condition con2 = lock.newCondition();
    private final Condition con3 = lock.newCondition();


    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                con1.await();
            }
            for (int i= 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + " : " + loop + ":" + (i + 1));
            }
            flag = 2;
            con2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                con2.await();
            }
            for (int i= 0; i < 10; ++i) {
                System.out.println(Thread.currentThread().getName() + " : " + loop + ":" + (i + 1));
            }
            flag = 3;
            con3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                con3.await();
            }
            for (int i= 0; i < 15; ++i) {
                System.out.println(Thread.currentThread().getName() + " : " + loop + ":" + (i + 1));
            }
            flag = 1;
            con1.signal();
        } finally {
            lock.unlock();
        }
    }
}

