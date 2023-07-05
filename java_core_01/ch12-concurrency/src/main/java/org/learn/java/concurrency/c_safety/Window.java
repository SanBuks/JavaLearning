package org.learn.java.concurrency.c_safety;

public class Window implements Runnable {
    private int ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                // 多个线程都进入阻塞后出现错误, 有效状态与实际状态不同步
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
            --ticketNum;
        }
    }
}

class WindowTest {
    public static void main(String[] args) {
        Window a = new Window();

        Thread t1 = new Thread(a, "01");
        Thread t2 = new Thread(a, "02");
        Thread t3 = new Thread(a, "03");

        t1.start();
        t2.start();
        t3.start();
    }
}
