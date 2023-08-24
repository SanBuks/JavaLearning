package org.learn.java.juc.communication.d_lock;

//01: syn 访问，先打印短信还是邮件
//    ------sendSMS
//    ------sendEmail
// 锁对象顺序

public class Problem01 {
    public static void main(String[] args) throws Exception {
        Resource s = new Resource();

        new Thread(s::synSMS, "AA").start();
        Thread.sleep(100);
        new Thread(s::synEmail, "BB").start();
    }
}
