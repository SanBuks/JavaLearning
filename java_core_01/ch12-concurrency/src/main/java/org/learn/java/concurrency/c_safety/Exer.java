package org.learn.java.concurrency.c_safety;

// 问题: 银行有一个账户。 有三个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。

// 一个账户 对象 管理存款资源(存款, 存款操作竞争资源需要加锁)
// 三个储户 共同拥有一个账户

public class Exer {
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
    private int count = 3;
    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.deposit(1000);
        }
    }
}