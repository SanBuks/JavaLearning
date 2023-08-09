package org.learn.java.concurrency.h_other_create;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; ++i) {
            sum += i;
        }
        return sum;
    }
}

public class CallableTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread th = new Thread(futureTask);
        th.start();

        System.out.println(futureTask.get());  // 主线程存在阻塞
    }
}
