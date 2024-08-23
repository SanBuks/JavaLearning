package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/method")
public class MethodController {

    // #1. 指定 Get 方式
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String GetTarget() {
        return "target";
    }

    // #2. 指定 Post 方式
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String PostTarget() {
        return "target";
    }

    // #3. 不指定方式 则都匹配
//    @RequestMapping(value = {"/post", "/get"})
//    public String AllTarget() {
//        return "target";
//    }

    // #4. RequestMapping 派生类指定
    @GetMapping("/getByGetMapping")
    public String GetByMappingTarget() {
        return "target";
    }
}
