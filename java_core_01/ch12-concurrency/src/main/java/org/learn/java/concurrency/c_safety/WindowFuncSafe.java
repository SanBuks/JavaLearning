package org.learn.java.concurrency.c_safety;

class WindowFuncLock implements Runnable {
    private int ticketNum = 1000;

    @Override
    public void run() {
        while (sell());
    }

    // Runnable 方式下 用普通方法, 默认锁为 obj
    // Thread   方式下 用static方法, 默认锁为 Clazz.class
    private synchronized boolean sell() {
        if (ticketNum > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
            --ticketNum;
            return true;
        } else {
            return false;
        }
    }
}

public class WindowFuncSafe {
    public static void main(String[] args) {
        WindowFuncLock a = new WindowFuncLock();

        Thread t1 = new Thread(a, "01");
        Thread t2 = new Thread(a, "02");
        Thread t3 = new Thread(a, "03");

        t1.start();
        t2.start();
        t3.start();
    }
}
