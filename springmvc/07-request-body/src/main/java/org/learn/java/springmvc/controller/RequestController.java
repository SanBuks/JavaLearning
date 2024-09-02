package org.learn.java.springmvc.controller;

import org.learn.java.springmvc.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
@RequestMapping
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    // #1. 获取 request-body string
    @PostMapping("/request-body")
    public String RequestBody(@RequestBody String user) throws UnsupportedEncodingException {
        logger.info(URLDecoder.decode(user, "UTF-8"));
        return "target";
    }

    // #2. 获取 request-entity entity
    @PostMapping("/request-entity")
    public String RequestEntity(RequestEntity<String> entity) throws UnsupportedEncodingException {
        logger.info(String.valueOf(entity.getHeaders()));
        String body = entity.getBody() == null ? "" : URLDecoder.decode(entity.getBody(), "UTF-8");
        logger.info(body);
        return "target";
    }

    // #3. ServletApi 写 response-body
    @GetMapping("/servlet-response-body")
    public void ServletResponseBody(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello");
    }

    // #4. @ResponseBody 写 response-body
    @GetMapping("/response-body")
    @ResponseBody
    public String ResponseBody() {
        return "hello";
    }

    // #4. HttpMessageConverter response-body
    @GetMapping("/response-json")
    @ResponseBody
    public User ResponseJson() {
        return new User("user", "123", 1, 3, "xxx", new ArrayList<>());
    }

    // #5. HttpMessageConverter response-body
    @PostMapping("/response-ajax")
    @ResponseBody
    public User ResponseAjax(String username, String password) {
        return new User(username, password, 1, 3, "xxx", new ArrayList<>());
    }
}