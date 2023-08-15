package org.learn.java.juc.communication.producer_consumer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Clerk {
    private int productNum = 10;
    private final int MAX = 25;
    private final int MIN = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    void add(String threadName) {
        lock.lock();
        try {
            if (productNum >= MAX) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ++productNum;
                System.out.println(threadName + ": add to " + productNum);
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    void remove(String threadName) {
        lock.lock();
        try {
            while (productNum <= MIN) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            --productNum;
            System.out.println(threadName + ": minus to " + productNum);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test() throws InterruptedException {
        Clerk clerk = new Clerk();
        Consumer consumer1 = new Consumer("consumer1", clerk);
        Consumer consumer2 = new Consumer("consumer2", clerk);
        Producer producer1 = new Producer("producer1", clerk);
        Producer producer2 = new Producer("producer2", clerk);

        consumer1.start();
        consumer2.start();
        producer1.start();
        producer2.start();

        consumer1.join();
        consumer2.join();
        producer1.join();
        producer2.join();
    }
}

