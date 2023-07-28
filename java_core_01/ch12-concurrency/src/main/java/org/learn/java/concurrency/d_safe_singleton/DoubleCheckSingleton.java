package org.learn.java.concurrency.d_safe_singleton;

public class DoubleCheckSingleton {
    private DoubleCheckSingleton() {}
    // 存在指令重排问题:
    // mem = allocate();        分配内存空间
    // instance = mem;          引用指向分配的内存
    // ctorSingleton(instance); 调用构造器
    // 对象定义需要增加 volatile 使得上述步骤在线程的工作存储区一次性完成, 然后复制到主存储区
    private static volatile DoubleCheckSingleton instance = null;

    // double-check-lock 实现饿汉式单例
    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
