package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// #1. 嵌套路由
@RequestMapping("/nest")
public class NestController {

    // #2. 路由组
    @RequestMapping(value={"/target1", "target2"})
    public String target() {
        return "target";
    }
}
