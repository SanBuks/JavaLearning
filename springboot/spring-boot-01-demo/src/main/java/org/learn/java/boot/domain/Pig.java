package org.learn.java.boot.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// bean + properties 方式
@Data
@Component("pig")
@ConfigurationProperties(prefix = "pig")
public class Pig {
    private Long id;
    private String name;
}
