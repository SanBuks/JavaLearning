package org.learn.java.juc.communication.d_lock;

//3 新增普通的hello方法，是先打短信还是hello
//    ------getHello
//    ------sendSMS
// 同一个对象， SMS 加锁， Email普通函数不会根据锁而等待，直接执行

public class Problem03 {
    public static void main(String[] args) throws Exception {
        Resource s = new Resource();

        new Thread(s::synSMSWait, "AA").start();
        Thread.sleep(100);
        new Thread(s::Hello, "BB").start();
    }
}
