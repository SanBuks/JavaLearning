package org.learn.java.juc.communication.d_lock;

//8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
//    ------sendEmail
//    ------sendSMS
// 一个类对象锁, 一个对象锁不是同一把锁

public class Problem08 {
    public static void main(String[] args) throws Exception {
        Resource s1 = new Resource();
        Resource s2 = new Resource();
        new Thread(Resource::staticSynSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(s2::synEmail, "BB").start();
    }
}
