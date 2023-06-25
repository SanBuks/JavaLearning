package com.learn.spring.resource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public class ResourceLoader {
    @Test
    public void test() {
//        ApplicationContext ctx = new FileSystemXmlApplicationContext();
//        // FileSystemResource
//        Resource resource = ctx.getResource("D:/Project/LEARNING/JavaLearning/spring/example_file.txt");

        // ClassPathResource
        ApplicationContext ctx = new ClassPathXmlApplicationContext();
        Resource resource = ctx.getResource("example_file.txt");
        System.out.println(resource.getDescription());
        try {
            InputStream in = resource.getInputStream();
            byte[] kB = new byte[1024];
            while (in.read(kB) != -1) {
                System.out.println(new String(kB));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
