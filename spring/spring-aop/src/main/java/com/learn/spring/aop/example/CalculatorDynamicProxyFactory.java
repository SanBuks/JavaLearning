package com.learn.spring.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorDynamicProxyFactory {
    // 需要代理的实体类
    private final Object target;
    public CalculatorDynamicProxyFactory(Object target) {
        this.target = target;
    }

    Object getProxy() {
        ClassLoader classLoader = target.getClass().getClassLoader();  // 获取类加载器
        Class<?>[] classInterface = target.getClass().getInterfaces(); // 获取类接口
        // 设置代理接口类
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("[dynamic proxy]: method name = " + method.getName());
                Object result = method.invoke(target, args);
                System.out.println("[dynamic proxy]: result = " + result);
                return result;
            }
        };
        // 返回代理实体的对象
        return Proxy.newProxyInstance(classLoader, classInterface, invocationHandler);
    }
}
