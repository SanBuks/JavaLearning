package com.learn.spring.ioc.di.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class UserPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3.初始化之前输出");
        System.out.println(beanName + "" + "初始化之前输出" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.初始化之后输出");
        System.out.println(beanName + "" + "初始化之后输出" + bean);
        return bean;
    }
}
