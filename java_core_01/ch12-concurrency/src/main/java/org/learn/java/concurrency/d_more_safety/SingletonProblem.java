package org.learn.java.concurrency.d_more_safety;

import org.junit.jupiter.api.Test;

public class SingletonProblem {
    private SingletonProblem() { }

    private static  SingletonProblem instance = null;

    public static /*synchronized*/ SingletonProblem getInstance() {
        if (instance == null) {
            // 饿汉式会产生线程问题, 可能产生两个不同实例
            instance = new SingletonProblem ();
        }
        return instance;
    }
}

class SingletonTest {
    static SingletonProblem s1 = null;
    static SingletonProblem s2 = null;

    @Test
    public void singletonTest() throws InterruptedException {
        var t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s1 = SingletonProblem.getInstance();
        });

        var t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s2 = SingletonProblem.getInstance();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}


