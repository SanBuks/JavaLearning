package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/path")
public class PathController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PathController.class);

    @GetMapping("/{id}/{name}")
    public String GetTarget(@PathVariable("id") String id, @PathVariable("name") String name) {
        LOGGER.info("id: {} name: {}", id, name);
        return "target";
    }
}
