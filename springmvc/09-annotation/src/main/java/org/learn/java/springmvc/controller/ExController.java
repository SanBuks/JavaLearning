package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ExController {
    @GetMapping("/ex")
    public String Test() {
        throw new RuntimeException("测试异常处理");
    }
}