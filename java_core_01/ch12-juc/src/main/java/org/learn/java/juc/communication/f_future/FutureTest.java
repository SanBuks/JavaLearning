package org.learn.java.juc.communication.f_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    @Test
    public void test() throws Exception {
        FutureTask<Integer> task = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            int sum = 0;
            for (int i = 0; i < 1000; ++i) {
                sum += i;
            }
            return sum;
        });

        Thread t = new Thread(task, "aaa");
        t.start();
        t.join();
        if (!task.isDone()) {
            System.out.println("wait...");
        }
        Integer result = task.get();
        System.out.println(result);
    }
}
