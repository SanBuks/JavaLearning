package org.learn.java.concurrency.e_deadlock;

import org.junit.jupiter.api.Test;

public class FuncDeadLock {

    @Test
    // 对同步函数竞争类型的死锁
    public void DeadLockTest() throws InterruptedException {
        A a = new A();
        B b = new B();

        Run1 run1 = new Run1(a, b);
        Run2 run2 = new Run2(a, b);

        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class A {
    public synchronized void foo(B b) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // 占用 A 的锁 申请 B 的锁
        b.last();
    }
    public synchronized void last() {
        System.out.println("A: last");
    }
}

class B {
    public synchronized void bar(A a) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // 占用 B 的锁 申请 A 的锁
        a.last();
    }
    public synchronized void last() {
        System.out.println("B: last");
    }
}

class Run1 implements Runnable {
    A a;
    B b;

    public Run1(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        b.bar(a);
    }
}

class Run2 implements Runnable {
    A a;
    B b;

    public Run2(A a, B b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public void run() {
        a.foo(b);
    }
}