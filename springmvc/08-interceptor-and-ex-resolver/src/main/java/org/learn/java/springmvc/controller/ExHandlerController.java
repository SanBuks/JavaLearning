package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExHandlerController.class);

    @ExceptionHandler(value= RuntimeException.class)
    public String ex(Exception ex, Model model) {
        logger.error("ex message: " + ex.getMessage());
        model.addAttribute("ex", ex.getMessage());
        return "error";
    }
}
