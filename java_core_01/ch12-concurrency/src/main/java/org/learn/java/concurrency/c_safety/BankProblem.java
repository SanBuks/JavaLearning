package org.learn.java.concurrency.c_safety;

public class BankProblem {
    public static void main(String[] args) {
        Account account = new Account();

        Customer customer1 = new Customer(account);
        Customer customer2 = new Customer(account);
        Customer customer3 = new Customer(account);

        Thread thread1 = new Thread(customer1, "01");
        Thread thread2 = new Thread(customer2, "02");
        Thread thread3 = new Thread(customer3, "03");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Account {
    private double money = 0;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public synchronized void deposit(double money) {
        this.money += money;
        System.out.println(Thread.currentThread().getName() + " - " + this.money);
    }
}

class Customer implements Runnable {
    private Account account;
    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.deposit(1000);
        }
    }
}