package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping
public class InterceptorController {
    @GetMapping("/interceptor")
    public String Test() {
        return "target";
    }
}