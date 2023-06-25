package com.learn.spring.resource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceDITest {
    @Test
    public void test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("resource.xml");
        ResourceDIBean bean = (ResourceDIBean) app.getBean("resource");
        Resource resource = bean.getResource();
        System.out.println(resource.getDescription());
    }
}
