package com.learn.spring.aop.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value="com.learn.spring.aop.annotation")
@EnableAspectJAutoProxy
public class AopConfig {

}
