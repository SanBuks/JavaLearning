package com.learn.spring;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    private final Logger logger = LoggerFactory.getLogger(User.class);

    @Test
    public void testUser() {
        // doc4j 加载解析 xml, 获取 id, class 属性值
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        // 反射创建对象, 放入 bean 容器 即 DefaultListableBeanFactory 中 Map<String, BeanDefinition> beanDefinitionMap;
        // BeanDefinition 包含 bean 的信息
        User user = (User) app.getBean("user");
        System.out.println(user);
        user.print();
    }

    // 模拟反射过程
    @Test
    public void testReflection() throws Exception {
        // 加载解析 xml, 获取 id, class 属性值
        Class<?> clazz = Class.forName("com.learn.spring.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        user.print();
    }

    @Test
    public void testLog4j() {
        logger.debug("hello log4j!");
    }
}
