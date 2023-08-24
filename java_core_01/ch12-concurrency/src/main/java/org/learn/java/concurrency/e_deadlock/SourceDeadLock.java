package org.learn.java.concurrency.e_deadlock;

public class SourceDeadLock {
    public static void main(String [] args) {
        // 对资源竞争类型的死锁
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        Thread t1 = new Thread(()->{
            synchronized (s1) {
                s1.append("a");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (s2) {
                    s2.append("1");
                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (s2) {
                s2.append("2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (s1) {
                    s1.append("b");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s1);
        System.out.println(s2);
    }

}


