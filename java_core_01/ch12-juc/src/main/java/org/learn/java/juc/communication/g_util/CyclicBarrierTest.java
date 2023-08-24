package org.learn.java.juc.communication.g_util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    @Test
    public void test() {

        CyclicBarrier barrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤！");
        });

        List<Thread> executors = new ArrayList<>();

        for (int i = 1; i <= 7; ++i) {
            Thread t = new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, Integer.toString(i));
            t.start();
            executors.add(t);
        }

        for (Thread t : executors) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
