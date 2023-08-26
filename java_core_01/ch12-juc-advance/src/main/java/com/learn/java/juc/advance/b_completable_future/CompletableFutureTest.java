package com.learn.java.juc.advance.b_completable_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CompletableFutureTest {
    public static void main(String [] args) {}

    @Test
    public void NoReturnTask() throws ExecutionException, InterruptedException {
        // 自定义线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 创建无返回值的异步任务
        CompletableFuture<Void> noReturnTask = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : " + "running!");
        }, pool);
        System.out.println(noReturnTask.get());
        pool.shutdown();
    }

    @Test
    public void DoReturnTask() throws ExecutionException, InterruptedException {
        // 自定义线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 创建有返回值的异步任务
        CompletableFuture<String> doReturnTask = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : " + "running!");
            return "over!";
        }, pool);
        System.out.println(doReturnTask.get());
        pool.shutdown();
    }

    @Test
    public void CallBackTest() throws InterruptedException {
        // 自定义线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int num = 2;

        try {
            CompletableFuture<String> callBackTask = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                if (num == 1) {
                    return "over";
                } else {
                    throw new RuntimeException("num is not 1");
                }
            }, pool).whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println(result);
                } else {
                    System.out.println(ex.getMessage());
                }
            }).exceptionally((ex) -> {
                System.out.println(ex.getMessage());
                return "error";
            });

        } finally {
            pool.shutdown();
        }

        TimeUnit.SECONDS.sleep(10);
    }
}
