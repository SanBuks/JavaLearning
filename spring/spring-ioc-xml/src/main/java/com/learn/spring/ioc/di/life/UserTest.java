package com.learn.spring.ioc.di.life;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void lifeTest() {
        // 过程
        // 1.bean对象创建（调用无参构造器）
        // 2.给bean对象设置属性
        // 3.bean的后置处理器（初始化之前）
        // 4.bean对象初始化（需在配置bean时指定初始化方法）
        // 5.bean的后置处理器（初始化之后）
        // 6.bean对象就绪可以使用
        // 7.bean对象销毁（需在配置bean时指定销毁方法） 只有 singleton 模式会调用
        // 8.IOC容器关闭
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("life.xml");
        User user = context.getBean("user", User.class);
        System.out.println("6. bean 创建完成");
        context.close();
        System.out.println("8. bean 已销毁");
    }
}
