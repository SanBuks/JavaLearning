package com.learn.spring.ioc.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value={"com.learn.spring.ioc.annotation.config"})
public class Config {
}
