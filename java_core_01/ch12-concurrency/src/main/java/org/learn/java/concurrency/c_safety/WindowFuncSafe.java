package org.learn.java.concurrency.c_safety;

public class WindowFuncSafe implements Runnable {
    private int ticketNum = 100;

    @Override
    public void run() {
        while (sell());
    }

    // Runnable 方式下 默认锁为 this
    // Thread   方式下 默认锁为 this, 需要是 static 方法, this 指向 Clazz.class
    private synchronized boolean sell() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ticketNum > 0) {
            try {
                // 多个线程都进入阻塞后出现错误, 有效状态与实际状态不同步
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

class WindowFuncSafeTest {
    public static void main(String[] args) {
        WindowSafe a = new WindowSafe();

        Thread t1 = new Thread(a, "01");
        Thread t2 = new Thread(a, "02");
        Thread t3 = new Thread(a, "03");

        t1.start();
        t2.start();
        t3.start();
    }
}
