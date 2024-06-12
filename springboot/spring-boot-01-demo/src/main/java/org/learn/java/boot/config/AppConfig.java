package org.learn.java.boot.config;

import org.learn.java.boot.domain.Complex;
import org.learn.java.boot.domain.Sheep;
import org.learn.java.boot.domain.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

// Bean 配置类
@SpringBootConfiguration
// Import 快速注入实例
@Import(Exception.class)
// 开启自动创建实例并绑定
@EnableConfigurationProperties({Sheep.class})
public class AppConfig {

    // User 对象 名为(BUser) 默认单实例
    // Scope 指定为原型
    @Scope("prototype")
    @Bean(name="user1")
    public User user1() {
        var user = new User();
        user.setId("123");
        user.setName("John");
        return user;
    }

    @ConditionalOnClass(Exception.class)
    @Bean(name="user2")
    public User user2() {
        var user = new User();
        user.setId("012");
        user.setName("Jame");
        return user;
    }
}
