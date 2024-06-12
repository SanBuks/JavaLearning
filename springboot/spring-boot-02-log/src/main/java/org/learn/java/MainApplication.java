package org.learn.java;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.learn.java")
//@Slf4j
@Log4j2
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        for (int i = 0; i < 1000; ++i) log.info("xxxxxxxxxxxxxxxxx");
    }
}
