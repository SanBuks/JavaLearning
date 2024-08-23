package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// #1. 注解标识控制器
@Controller
public class HelloWorldController {

    // #2. 请求 ---------------> 控制器 ----------------> xxx.html/vue
    //          RequestMapping         XXXViewResolver
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/target")
    public String target() {
        return "target";
    }
}
