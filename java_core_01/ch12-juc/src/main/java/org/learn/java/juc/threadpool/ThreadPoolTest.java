package org.learn.java.juc.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    @Test
    public void FixedPoolTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 1; i <= 5; ++i) {
                int finalI = i;
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理任务" + finalI);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成任务" + finalI);
                    latch.countDown();
                });
            }
        } finally {
            pool.shutdown();
        }

        latch.await();
    }

    @Test
    public void SinglePoolTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService pool = Executors.newSingleThreadExecutor();

        try {
            for (int i = 1; i <= 5; ++i) {
                int finalI = i;
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理任务" + finalI);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成任务" + finalI);
                    latch.countDown();
                });
            }
        } finally {
            pool.shutdown();
        }

        latch.await();
    }

    @Test
    public void CachePoolTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(20);
        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 20; ++i) {
                int finalI = i;
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理任务" + finalI);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " 完成任务" + finalI);
                    latch.countDown();
                });
            }
        } finally {
            pool.shutdown();
        }

        latch.await();
    }

    @Test
    public void CallerRunPolicyTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
                                                             new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();
    }
}
