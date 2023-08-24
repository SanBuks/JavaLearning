package org.learn.java.juc.communication.d_lock;

//5 两个静态同步方法，1部手机，先打印短信还是邮件
//    ------sendSMS
//    ------sendEmail
//6 两个静态同步方法，2部手机，先打印短信还是邮件
//    ------sendSMS
//    ------sendEmail
// 锁加载类对象上，按锁同步顺序

public class Problem0506 {
    public static void main(String[] args) throws Exception {
        new Thread(Resource::staticSynSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(Resource::staticSynEmail, "BB").start();
    }
}
