package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/context-domain")
public class ContextDomainController {

    private static final Logger logger = LoggerFactory.getLogger(ContextDomainController.class);

    // #1. 通过 HttpSession 获取 context
    @GetMapping(value = "/httpsession")
    public String ContextDomain(HttpSession session) {
        ServletContext context = session.getServletContext();
        context.setAttribute("contextDomain", "context-domain");
        return "target";
    }

}