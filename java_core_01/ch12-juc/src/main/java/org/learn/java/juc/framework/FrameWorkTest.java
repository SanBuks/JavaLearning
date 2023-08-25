package org.learn.java.juc.framework;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class CalcTask extends RecursiveTask<Integer> {

    private final int NUM = 10;
    private final int min;
    private final int max;
    private int result = 0;

    public CalcTask(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer compute() {
        if (max - min < NUM)  {
            for (int i = min; i <= max; ++i) {
                result += i;
            }
        } else {
            int mid = min + (max - min) / 2;
            CalcTask left = new CalcTask(min, mid - 1);
            CalcTask right = new CalcTask(mid, max);
            left.fork();
            right.fork();
            result = left.join() + right.join();
        }
        return result;
    }
}

public class FrameWorkTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CalcTask task = new CalcTask(1, 100);
        ForkJoinPool fp = new ForkJoinPool();
        ForkJoinTask<Integer> ftask = fp.submit(task);
        Integer result = ftask.get();
        System.out.println(result);
        fp.shutdown();
    }
}
