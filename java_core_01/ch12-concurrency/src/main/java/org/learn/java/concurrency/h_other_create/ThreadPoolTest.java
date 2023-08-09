package org.learn.java.concurrency.h_other_create;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class NumCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; ++i) {
            sum += i;
        }
        return sum;
    }
}

public class ThreadPoolTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        // 设定大小
        threadPoolExecutor.setMaximumPoolSize(100);
        FutureTask<Integer> ft = new FutureTask<>(new NumCallable());
        // 执行 runnable
        threadPoolExecutor.execute(ft);
        threadPoolExecutor.submit(new NumCallable());
        // 阻塞获取结果
        System.out.println(ft.get());
    }
}