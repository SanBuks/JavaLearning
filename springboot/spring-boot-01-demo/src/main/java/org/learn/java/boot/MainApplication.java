package org.learn.java.boot;

import lombok.extern.log4j.Log4j2;
import org.learn.java.boot.domain.Complex;
import org.learn.java.boot.domain.Sheep;
import org.learn.java.boot.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 默认扫包为主程序所在包(org.learn.java.boot)下所有路径
//@SpringBootApplication(scanBasePackages = "org.learn.java")
// 直接指定扫描的路径
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("org.learn.java")
// 开启属性自动绑定
public class MainApplication {
    public static void main(String[] args) {
        // 获取 ioc 容器
        var ioc = SpringApplication.run(MainApplication.class, args);
        // 获取所有 ioc 容器中对象名
        String[] names = ioc.getBeanDefinitionNames();
        // 获取对应类型实例
        var bean1 = ioc.getBean("user1", User.class);
        var bean2 = ioc.getBean("user1", User.class);
        System.out.println(bean1 == bean2);
        var ex = ioc.getBean(Exception.class);
        System.out.println(ex.getLocalizedMessage());
        // 条件注解获取 bean
        var beanCondition = ioc.getBean("user2");
        System.out.println(beanCondition);
        // 属性绑定获取 bean
        var pigBean = ioc.getBean("pig");
        System.out.println(pigBean);
        // EnableConfigurationProperties 属性绑定
        var sheepBean = ioc.getBean(Sheep.class);
        System.out.println(sheepBean);
        // yaml 语法
        var complexBean = ioc.getBean(Complex.class);
        System.out.println(complexBean);
    }
}
