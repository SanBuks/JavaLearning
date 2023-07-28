package org.learn.java.concurrency.a_create;

import org.junit.jupiter.api.Test;

public class WhichRun {

    // 1. b1, b2 的线程执行体调用 a1, a2 的 普通方法, 可视为 '普通代理'
    @Test
    public void SimpleProxy() throws InterruptedException {
        A a1 = new A();
        A a2 = new A();

        B b1 = new B(a1);
        B b2 = new B(a2);

        b1.start();
        b2.start();

        b1.join();
        b2.join();
    }

    // 2. c1, c2 将 a1, a2 对象注入到父类 Thread 中, 完成代理
    @Test
    public void ThreadProxy() {
        A a1 = new A();
        A a2 = new A();

        C c1 = new C(a1);
        C c2 = new C(a2);

        c1.start();
        c2.start();
    }
}

class A extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(getName() +  " - " + i + " - 线程A的run()...");
        }
    }
}

class B extends Thread {
    private final A a;

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println("线程B的run()...");
        a.run();
    }
}

class C extends Thread {
    private A a;

    public C(A a) {
        super(a);
    }

    // 3. 取消注释则 根据重写原则优先调用 对象重写的 run 方法
//    @Override
//    public void run() {
//        System.out.println("线程C的run()...");
//    }
}
