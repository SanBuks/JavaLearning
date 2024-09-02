package org.learn.java.springmvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// #0. Web 工程初始化类 替代 web.xml
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // #1. 指定 Spring 配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringConfig.class};
    }
    // #2. 指定 SpringMVC 配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    // #3. 指定 DispatcherServlet 映射路径 url-pattern
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // #4. 指定过滤器
    @Override
    protected Filter[] getServletFilters() {
        // 解决 Post 乱码
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        // 处理 Restful Put Get 方法
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}
