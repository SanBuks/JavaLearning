package org.learn.java.juc.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {
    @Test
    public void test() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);

        Thread t1 = new Thread(()->{

        }, "aa");
        Thread t2 = new Thread(()->{

        }, "bb");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
