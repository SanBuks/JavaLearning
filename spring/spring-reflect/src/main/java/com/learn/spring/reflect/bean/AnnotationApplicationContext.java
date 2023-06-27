package com.learn.spring.reflect.bean;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {

    private Map<Class, Object> beanFactory = new HashMap<>();

    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    public AnnotationApplicationContext(String basePackage) {
    }

    public static List<String> getClassPath(String basePackage) throws Exception {
        basePackage = basePackage.replaceAll("\\.", "/");
        var urls = Thread.currentThread().getContextClassLoader().getResources(basePackage);
        List<String> filePaths = new ArrayList<>();
        while (urls.hasMoreElements()) {
            var url = urls.nextElement();
            var filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
            filePaths.add(filePath);
        }
        return filePaths;
    }
}
