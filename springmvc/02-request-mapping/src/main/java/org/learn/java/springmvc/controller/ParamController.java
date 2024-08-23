package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class ParamController {
    // #1. 参数(判断)匹配
    @GetMapping( value = "/get", params = {"id=1", "password!=123456"})
    public String GetTarget() {
        return "target";
    }
}
