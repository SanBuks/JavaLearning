package org.learn.java.concurrency.g_communication.producer_consumer;

import org.junit.jupiter.api.Test;

public class Clerk {
    private int productNum = 10;
    public synchronized void addProduct(String name) {
        if (productNum >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {  // 增加 else 避免让多个唤醒线程产生错误情况
            ++productNum;
            System.out.println(name + "生产了 第" + productNum + "个 产品!");
            notifyAll();
        }
    }
    public synchronized void removeProduct(String name) {
        if (productNum <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(name + "消费了 第" + productNum + "个 产品!");
            --productNum;
            notifyAll();
        }
    }

    @Test
    public void test() {
        Clerk clerk = new Clerk();
        Consumer consumer1 = new Consumer("consumer1", clerk);
        Consumer consumer2 = new Consumer("consumer2", clerk);
        Producer producer = new Producer("producer", clerk);

        consumer1.start();
        consumer2.start();
        producer.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

