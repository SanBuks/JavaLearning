package org.learn.java.concurrency.g_communication.producer_consumer;

public class Consumer extends Thread {
    Clerk clerk;

    public Consumer(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.removeProduct(getName());
        }
    }
}
