package com.learn.spring.i18n;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest {
    @Test
    public void test() {
        ResourceBundle bundle1 = ResourceBundle.getBundle("messages", new Locale("zh", "CN"));
        System.out.println(bundle1.getString("test"));

        ResourceBundle bundle2 = ResourceBundle.getBundle("messages", new Locale("en", "GB"));
        System.out.println(bundle2.getString("test"));

        ResourceBundle bundle3 = ResourceBundle.getBundle("messages", new Locale("zh", "CN"));
        System.out.println(bundle3.getString("nofound"));
    }
}
