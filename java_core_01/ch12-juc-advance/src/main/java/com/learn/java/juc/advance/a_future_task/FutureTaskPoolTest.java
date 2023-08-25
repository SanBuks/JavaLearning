package com.learn.java.juc.advance.a_future_task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

// FutureTask 测试性能优化
public class FutureTaskPoolTest {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> t1 = new FutureTask<>(()->{
            try { TimeUnit.MILLISECONDS.sleep(300); } catch(Exception ex) { ex.printStackTrace(); }
            return "Task 1 over";
        });
        FutureTask<String> t2 = new FutureTask<>(()->{
            try { TimeUnit.MILLISECONDS.sleep(300); } catch(Exception ex) { ex.printStackTrace(); }
            return "Task 2 over";
        });
        FutureTask<String> t3 = new FutureTask<>(()->{
            try { TimeUnit.MILLISECONDS.sleep(500); } catch(Exception ex) { ex.printStackTrace(); }
            return "Task 3 over";
        });

        threadPool.submit(t1);
        threadPool.submit(t2);
        threadPool.submit(t3);

        System.out.println(t1.get());
        System.out.println(t2.get());
        System.out.println(t3.get());

        threadPool.shutdown();

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
