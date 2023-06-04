package com.learn.spring.aop.example;

public class Calculator implements ICalculator {
    // 模拟核心业务
    @Override
    public int add(int lhs, int rhs) {
        return lhs + rhs;
    }

    @Override
    public int sub(int lhs, int rhs) {
        return lhs - rhs;
    }
}
