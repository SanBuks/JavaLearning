package com.learn.spring.resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.UrlResource;

public class UrlResourceTest {

    @Test
    public void testURL() {
        String urlPath = "https://www.baidu.com";
        try {
            UrlResource url = new UrlResource(urlPath);
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());
            System.out.println(url.getInputStream().read());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testFile() {
        String urlPath = "file:D:/Project/LEARNING/JavaLearning/spring/example_file.txt";
        try {
            UrlResource url = new UrlResource(urlPath);
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());
            System.out.println(url.getInputStream().read());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
