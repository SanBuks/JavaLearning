package org.learn.java.juc.communication.d_lock;

//4 现在有两部手机，先打印短信还是邮件
//    ------sendEmail
//    ------sendSMS
// 两个对象， 锁的对象不一样， 根据时间判断

public class Problem04 {
    public static void main(String[] args) throws Exception {
        Resource s1 = new Resource();
        Resource s2 = new Resource();

        new Thread(s1::synSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(s2::synEmail, "BB").start();
    }
}
