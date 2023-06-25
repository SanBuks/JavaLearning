package com.learn.spring.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Date;
import java.util.Locale;

public class I18NSpringTest {

    @Test
    public void test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        Object[] objs = new Object[]{"param1", new Date().toString()};
        System.out.println(app.getMessage("www", objs, Locale.CHINA));
        System.out.println(app.getMessage("www", objs, Locale.UK));
    }
}
