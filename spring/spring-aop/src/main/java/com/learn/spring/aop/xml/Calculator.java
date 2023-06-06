package com.learn.spring.aop.xml;

import org.springframework.stereotype.Component;

@Component
public class Calculator implements ICalculator {
    @Override
    public int add(int lhs, int rhs) {
        // int a = 1 / 0; // 前置/后置/异常
        return lhs + rhs; // 前置/后置/返回
    }

    @Override
    public int sub(int lhs, int rhs) {
//         int a = 1 / 0; // 前置/后置/异常
        return lhs - rhs;
    }
}
