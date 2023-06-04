package com.learn.spring.aop.example;
// [横切关注点]： 同一类分散的业务问题, 现在关注的是 业务前后的日志打印

// [切面]: 封装通知方法的类
public class CalculatorStaticProxy implements ICalculator {
    // [代理对象]
    private final Calculator calculator;

    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    // [环绕通知]
    @Override
    public int add(int lhs, int rhs) {
        // [前置通知] [连接点]处于业务之前
        PreBusiness(lhs, rhs);
        // 核心业务
        int ans = -1;
        try{
            ans = calculator.add(lhs, rhs);
            // [返回通知] [连接点]处于业务结束
            System.out.println("success!");
        } catch (Exception exception) {
            // [异常通知] [连接点]处于业务异常
            System.out.println("error");
        } finally {
            // [后置通知] [连接点]处于业务之后
            System.out.println("lhs + rhs = " + ans);
        }
        return ans;
    }

    // 通过找到函数即可找到连接点则 这个函数可称为[切入点]
    // [切入点] 即是 寻找连接点的方式
    void PreBusiness(int lhs, int rhs) {
        System.out.println("(lhs, rhs): " + lhs + ", " + rhs);
    }

    @Override
    public int sub(int lhs, int rhs) {
        System.out.println("(lhs, rhs): " + lhs + ", " + rhs);
        int ans = calculator.sub(lhs, rhs);
        System.out.println("lhs - rhs = " + ans);
        return ans;
    }
}