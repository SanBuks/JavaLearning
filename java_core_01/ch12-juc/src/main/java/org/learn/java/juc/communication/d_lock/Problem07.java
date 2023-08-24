package org.learn.java.juc.communication.d_lock;

//7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
//    ------sendEmail
//    ------sendSMS
// 一个类对象锁, 一个对象锁不是同一把锁

public class Problem07 {
    public static void main(String[] args) throws Exception {
        Resource s = new Resource();
        new Thread(Resource::staticSynSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(s::synEmail, "BB").start();
    }
}
