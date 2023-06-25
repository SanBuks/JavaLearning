package com.learn.spring.validation.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(value="com.learn.spring.validation.annotation")
public class Config {

    @Bean
    public LocalValidatorFactoryBean factoryBean() {
        return new LocalValidatorFactoryBean();
    }
}
