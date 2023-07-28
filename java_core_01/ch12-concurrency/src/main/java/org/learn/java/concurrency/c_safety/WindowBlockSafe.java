package org.learn.java.concurrency.c_safety;

class WindowBlockLock implements Runnable {
    private int ticketNum = 100;                     // Runnable
 // private static int ticketNum = 100;              // Thread

    private final Object obj = new Object();         // Runnable
    private final static Object sobj = new Object(); // Thread

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            synchronized (this) {                  // Runnable
//            synchronized(WindowBlockLock.class) {  // Thread
            synchronized (obj) {                     // Thread 需要 sobj
                if (ticketNum > 0) {
                    try {
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

public class WindowBlockSafe {
    public static void main(String[] args) {
        WindowBlockLock a = new WindowBlockLock();

        Thread t1 = new Thread(a, "01");
        Thread t2 = new Thread(a, "02");
        Thread t3 = new Thread(a, "03");

        t1.start();
        t2.start();
        t3.start();
    }
}
