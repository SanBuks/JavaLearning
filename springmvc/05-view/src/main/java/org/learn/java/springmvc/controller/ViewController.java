package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    // #1. ThymeleafView 转发到页面对象
    @GetMapping(value = "/thymeleaf")
    public String Thymeleaf() {
        return "target";
    }

    // #2. InternalView 转发
    @GetMapping(value = "/forward")
    public String ModelAndViewRequestDomain() {
        return "forward:/view/thymeleaf";
    }

    // #3. RedirectView 重定向
    @GetMapping(value = "/redirect")
    public String ModelRequestDomain() {
        return "redirect:/view/thymeleaf";
    }
}