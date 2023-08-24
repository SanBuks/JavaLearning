package org.learn.java.juc.communication.d_lock;

//02: 停4秒在短信方法内，先打印短信还是邮件
//    ------sendSMS
//    ------sendEmail
// 锁对象顺序

public class Problem02 {
    public static void main(String[] args) throws Exception {
        Resource s = new Resource();

        new Thread(s::synSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(s::synEmail, "BB").start();
    }
}
