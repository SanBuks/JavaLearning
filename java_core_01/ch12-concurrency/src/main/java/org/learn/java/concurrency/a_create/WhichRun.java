package org.learn.java.concurrency.a_create;

public class WhichRun {

    // SimpleProxy
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        B b1 = new B(a1);
        B b2 = new B(a2);
        b1.start();
        b2.start();
    }

    // ThreadProxy
//    public static void main(String[] args) {
//        A a1 = new A();
//        A a2 = new A();
//        C c1 = new C(a1);
//        C c2 = new C(a2);
//        c1.start();
//        c2.start();
//    }
}

class A extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
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

    // 取消注释则 对象的 run 掩盖了代理对象的 run
//    @Override
//    public void run() {
//        System.out.println("线程B的run()...");
//    }
}
