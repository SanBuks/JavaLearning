package org.learn.java.juc.communication.g_util;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String [] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i =0; i < 6; ++i) {
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 离开");
                latch.countDown();
            }, Integer.toString(i));
            t.start();
        }
        latch.await();
        System.out.println("关门!");
    }
}
