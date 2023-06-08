package com.learn.spring.jdbc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(value={"com.learn.spring.jdbc", "com.learn.spring.tx"})
@ImportResource("classpath:bean.xml")
public class Config {
}
