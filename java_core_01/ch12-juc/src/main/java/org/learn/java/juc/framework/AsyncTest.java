package org.learn.java.juc.framework;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTest {
    @Test
    public void asyncVoid() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
        });
        voidFuture.get();
    }

    @Test
    public void asyncReturn() {
        CompletableFuture<Integer> integerFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1024;
        });
        integerFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println(ex.getMessage());
            }
            System.out.println("result = " + result);
        });
    }
}
