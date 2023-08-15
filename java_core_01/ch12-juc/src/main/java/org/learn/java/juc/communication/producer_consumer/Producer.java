package org.learn.java.juc.communication.producer_consumer;

public class Producer extends Thread {
    Clerk clerk;

    public Producer(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.add(getName());
        }
    }
}
