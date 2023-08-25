package org.learn.java.juc.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CustomThreadPoolTest {

    @Test
    public void test() throws InterruptedException {
        final int num = 5;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                                                   Executors.defaultThreadFactory(),
                                                   new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch latch = new CountDownLatch(num);

        for (int i= 0; i < num; ++i) {
            executor.execute(()->{
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }
}
