package org.learn.java.concurrency.c_safety;

public class WindowSafe implements Runnable {
    private int ticketNum = 100;
    private final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            synchronized (this) {            // Runnable 可以
//            synchronized(WindowSafe.class) { // Thread 需要 static obj
            synchronized (obj) {
                if (ticketNum > 0) {
                    try {
                        // 多个线程都进入阻塞后出现错误, 有效状态与实际状态不同步
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
                    --ticketNum;
                } else {
                    break;
                }
            }
        }
    }
}

class WindowSafeTest {
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
