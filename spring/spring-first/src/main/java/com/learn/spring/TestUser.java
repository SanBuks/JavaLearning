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
        // 1. doc4j 加载解析 xml, 获取 id, class 属性值, 生成 Map<String, BeanDefinition> beanDefinitionMap;
        // 2. 通过 BeanDefinitionReader 将 BeanDefinition 加载到 IOC 容器, 可以进行额外修改
        // 3. IOC 中: BeanFactory + Reflection 实例化对象
        // 4. IOC 中: 初始化后生成对象
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) app.getBean("user");
        System.out.println(user);
        user.print();
    }

    // 反射过程
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
