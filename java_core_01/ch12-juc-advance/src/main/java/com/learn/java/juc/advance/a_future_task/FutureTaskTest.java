package com.learn.java.juc.advance.a_future_task;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable");
        return "MyCallable!";
    }
}

// 使用 FutureTask (Future) 的例子
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        MyCallable callable = new MyCallable();
        FutureTask<String> task = new FutureTask<>(callable);
        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());
    }
}
