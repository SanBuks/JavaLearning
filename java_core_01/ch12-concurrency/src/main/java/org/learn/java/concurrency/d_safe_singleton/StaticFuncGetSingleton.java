package org.learn.java.concurrency.d_safe_singleton;

// static-function-get 实现饿汉式单例
// 内部类只有在外部类被调用才加载, 产生实例, 不用加锁
public class StaticFuncGetSingleton {
    private StaticFuncGetSingleton() { }

    private static class InstanceHolder {
        private static StaticFuncGetSingleton singleton;
    }

    public static StaticFuncGetSingleton getSingleton() {
        return InstanceHolder.singleton;
    }

}
