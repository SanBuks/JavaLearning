package com.myapp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;


public class AppMain {
    private static final Logger logger = Logger.getLogger(AppMain.class);

    public static void main(String [] args) {
        System.setProperty("spring.profiles.active", "dev");
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        for (String str : context.getBeanDefinitionNames()) {
            logger.info(str);
        }
        DataSource source = (DataSource) context.getBean("embeddedDataSource");
        if (source != null)
            logger.info("not null");
        else
            logger.info("null");
    }
}
