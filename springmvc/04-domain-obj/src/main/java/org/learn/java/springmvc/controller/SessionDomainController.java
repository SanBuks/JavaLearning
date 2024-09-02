package org.learn.java.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/session-domain")
public class SessionDomainController {

    private static final Logger logger = LoggerFactory.getLogger(SessionDomainController.class);

    // #1. HttpSession 获取 session 对象
    @GetMapping(value = "/httpsession")
    public String SessionDomain(HttpSession session) {
        session.setAttribute("sessionDomain", "session-domain");
        return "target";
    }


}