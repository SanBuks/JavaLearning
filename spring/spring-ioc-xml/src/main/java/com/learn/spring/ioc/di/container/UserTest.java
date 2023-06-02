package com.learn.spring.ioc.di.container;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testArray() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-container.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void testList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-container.xml");
        Department dept = context.getBean("dept", Department.class);
        System.out.println(dept);
    }

    @Test
    public void testMap() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-container.xml");
        Association association = context.getBean("association", Association.class);
        System.out.println(association);
    }

    @Test
    public void testUtil() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-container.xml");
        Association association = context.getBean("association_container", Association.class);
        System.out.println(association);
    }

    @Test
    public void testP() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-container.xml");
        Association association = context.getBean("association_p", Association.class);
        System.out.println(association);
    }
}
