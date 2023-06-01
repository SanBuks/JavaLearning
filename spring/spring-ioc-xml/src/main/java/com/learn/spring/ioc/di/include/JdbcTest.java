package com.learn.spring.ioc.di.include;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTest {

    @Test
    public void testJdbc() {
        ApplicationContext context = new ClassPathXmlApplicationContext("include.xml");
        DruidDataSource data_source = context.getBean("druid_data_source", DruidDataSource.class);

        System.out.println(data_source.getUsername());
    }

}
