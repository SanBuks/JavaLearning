package com.learn.spring.aop.example;

import org.junit.jupiter.api.Test;

public class TestCalculator {

    @Test
    void testAspect(){
        Calculator calc = new Calculator();
        CalculatorStaticProxy staticProxy = new CalculatorStaticProxy(calc);
        staticProxy.add(1, 2);
    }

    @Test
    void testDynamicProxyCalculator(){
        CalculatorDynamicProxyFactory dynamicProxyFactory = new CalculatorDynamicProxyFactory(new Calculator());
        // 注意动态代理需要接口实现, 转换时通过 Interface 接受对象
        ICalculator calc = (ICalculator) dynamicProxyFactory.getProxy();
        calc.add(1, 2);
    }
}
