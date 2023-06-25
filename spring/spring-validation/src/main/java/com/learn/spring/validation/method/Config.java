package com.learn.spring.validation.method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan(value="com.learn.spring.validation.method")
public class Config {

    @Bean
    public MethodValidationPostProcessor factoryBean() {
        return new MethodValidationPostProcessor();
    }
}
