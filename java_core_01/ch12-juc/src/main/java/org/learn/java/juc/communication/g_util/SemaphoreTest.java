package org.learn.java.juc.communication.g_util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    public void test() {
        Semaphore semaphore = new Semaphore(3, true);
        List<Thread> executors = new ArrayList<>();
        for (int i = 1; i <= 7; ++i) {
            Thread t = new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始抢");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了");
                    TimeUnit.SECONDS.sleep(4);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 离开了");
                    semaphore.release();
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
