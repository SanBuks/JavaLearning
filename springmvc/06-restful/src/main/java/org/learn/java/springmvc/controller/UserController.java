package org.learn.java.springmvc.controller;

import org.learn.java.springmvc.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/index")
    public String UserIndex() {
        return "user";
    }

    @GetMapping("/user/{id}")
    public String UserGet(@PathVariable Integer id) {
        logger.info("user get method called! {}", id);
        return "target";
    }

    @PostMapping("/user")
    public String UserPost(User user) {
        logger.info("user post method called! {}", user);
        return "target";
    }

    @PutMapping("/user")
    public String UserPut(User user) {
        logger.info("user put method called! {}", user);
        return "target";
    }

    @DeleteMapping("/user/{id}")
    public String UserDelete(@PathVariable Integer id) {
        logger.info("user delete method called! {}", id);
        return "target";
    }
}