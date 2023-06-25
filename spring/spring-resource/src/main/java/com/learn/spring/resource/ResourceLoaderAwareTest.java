package com.learn.spring.resource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceLoaderAwareTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("resource-loader-aware-bean.xml");
        ResourceLoaderAwareBean bean = (ResourceLoaderAwareBean) ctx.getBean("rlab");
        System.out.println(bean.getResourceLoader() == ctx);
    }
}
