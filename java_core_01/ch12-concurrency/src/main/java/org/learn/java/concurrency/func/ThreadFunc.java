package org.learn.java.concurrency.func;


import org.junit.jupiter.api.Test;

public class ThreadFunc {


//    // Sleep
//    public static void main(String [] args) {

    @Test
    public void sleepTest() throws InterruptedException {
        A a = new A();
        B b = new B();

        Thread thread1 = new Thread(a, "A");
        Thread thread2 = new Thread(b, "B");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class A implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}

class B implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
