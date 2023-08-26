package com.learn.java.juc.advance.b_completable_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StageTest {

    @Test
    public void thenApplyTest() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            return 1;
        }, pool).thenApply((r) -> {
            System.out.println("222");
            return r + 1;
        }).thenApply((r) -> {
            System.out.println("333");
            return r + 1;
        }).whenComplete((r, e) -> {
            if (e == null) {
                System.out.println(r);
            }
        }).exceptionally((e)->{
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        pool.shutdown();
    }



}
