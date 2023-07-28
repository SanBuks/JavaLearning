package org.learn.java.concurrency.c_safety;

class Window implements Runnable {
    private int ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                // 错误点1: 多个线程判断正确后进入阻塞, 更新不及时, 发生票不够问题
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + " - " + ticketNum);
                // 错误点2: 打印后未及时减票, 更新不及时, 发生重票问题
                --ticketNum;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class WindowProblem {
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
