package org.learn.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class IntController {
    @GetMapping("/interceptor")
    public String Test() {
        return "target";
    }
}