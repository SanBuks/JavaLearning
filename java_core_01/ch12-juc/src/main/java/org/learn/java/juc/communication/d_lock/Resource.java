package org.learn.java.juc.communication.d_lock;

import java.util.concurrent.TimeUnit;

public class Resource {
    public static synchronized void staticSynSMS() {
        System.out.println("------sendSMS");
    }

    public static synchronized void staticSynSMSWait() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------sendSMS");
    }
    public synchronized void synSMS() {
        System.out.println("------sendSMS");
    }

    public synchronized void synSMSWait() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------sendSMS");
    }

    public void SMS() {
        System.out.println("------sendSMS");
    }

    public static synchronized void staticSynEmail() {
        System.out.println("------sendEmail");
    }

    public synchronized void synEmail() {
        System.out.println("------sendEmail");
    }

    public synchronized void Email() {
        System.out.println("------sendEmail");
    }

    public void Hello() {
        System.out.println("------getHello");
    }
}
