package org.learn.java.concurrency.d_more_safety;

public class DoubleCheckSingleton {
    private DoubleCheckSingleton() {}
    private static DoubleCheckSingleton instance = null;

    // 1. double-check-lock 实现饿汉式单例
    public DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    // 2. function-static-instance 实现饿汉式单例
//    private static final class InstanceHolder {
//        private static final DoubleCheckSingleton instance = new DoubleCheckSingleton();
//    }
//
//    public DoubleCheckSingleton getInstance() {
//        return InstanceHolder.instance;
//    }
}
