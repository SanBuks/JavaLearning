package org.learn.java.concurrency.b_func;

public class ThreadFunc {

    public static void main(String[] args) throws InterruptedException {
        A a = new A("A");
        B b = new B("B");

        System.out.println(a.getPriority());
        System.out.println(b.getPriority());

        a.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(Thread.MIN_PRIORITY);

        a.setB(b);

        b.start(); // b 需要先执行, 才能及时阻塞到
        a.start();

        a.join();
        b.join();
    }
}

class A extends Thread {
    // 需要等待的线程
    private Thread b;

    A(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                b.join();  // a 阻塞 直到 b 结束
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(b.isAlive());
    }

    public void setB(Thread b) {
        this.b = b;
    }
}

class B extends Thread {
    B(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
