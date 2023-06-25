package com.learn.spring.resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testURL() {
        String httpUrlPath = "https://www.baidu.com";
        String fileUrlPath = "file:D:/Project/LEARNING/JavaLearning/spring/example_file.txt";
        urlResourceTest(httpUrlPath);
        System.out.println();
        urlResourceTest(fileUrlPath);
        System.out.println();
        classResourceTest("example_file.txt");
        System.out.println();
        fileResourceTest("D:/Project/LEARNING/JavaLearning/spring/example_file.txt");
    }


    public static void urlResourceTest(String path) {
        try {
            UrlResource url = new UrlResource(path);
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());
            System.out.println(url.getInputStream().read());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void classResourceTest(String path) {
        try {
            ClassPathResource url = new ClassPathResource(path);
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());
            InputStream in = url.getInputStream();
            byte[] kB = new byte[1024];
            while (in.read(kB) != -1) {
                System.out.println(new String(kB));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void fileResourceTest(String path) {
        try {
            FileSystemResource url = new FileSystemResource(path);
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());
            InputStream in = url.getInputStream();
            byte [] kB = new byte[1024];
            while (in.read(kB) != -1) {
                System.out.println(new String(kB));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
