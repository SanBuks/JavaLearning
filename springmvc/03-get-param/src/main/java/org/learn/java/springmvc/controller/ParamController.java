package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/param")
public class ParamController {

    private static final Logger logger = LoggerFactory.getLogger(ParamController.class);

    // #1. Servlet API
    @GetMapping( value = "/servletapi")
    public String Servlet(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        // 创建 session 获取 JSESSIONID cookie
        HttpSession session = request.getSession();

        logger.info("id: {} name: {}", id, name);
        return "target";
    }

    // #2. RequestParam: origin-name
    @GetMapping( value = "/origin-name")
    public String OriginName(String name, String [] hobby) {
        logger.info("name: {} hobby: {}", name, Arrays.asList(hobby));
        return "target";
    }

    // #3. RequestParam: default-value
    //     指定参数名
    //     指定默认数值
    //     指定是否必带参数
    @GetMapping( value = "/default-value")
    public String DefaultValue(@RequestParam(value="username", defaultValue = "hi", required = false) String name,
                              String [] hobby) {
        logger.info("name: {} hobby: {}", name, Arrays.asList(hobby));
        return "target";
    }

    // #4. RequestHeader
    @GetMapping( value = "/header-value")
    public String HeaderGet(@RequestHeader(value="host", defaultValue = "host", required = false) String host) {
        logger.info("header host: {}", host);
        return "target";
    }

    // #5. RequestCookie
    @GetMapping( value = "/cookie-value")
    public String CookieGet(@CookieValue(value="JSESSIONID", defaultValue = "cookie", required = false) String cookie) {
        logger.info("cookie: {}", cookie);
        return "target";
    }

    // #6. Pojo Post
    @PostMapping(value = "/pojo-post")
    public String PojoPost(User user) {
        logger.info("user: {}", user);
        return "target";
    }

    // #7. Pojo Get
    @GetMapping(value = "/pojo-get")
    public String PojoGet(User user) {
        logger.info("user: {}", user);
        return "target";
    }
}
