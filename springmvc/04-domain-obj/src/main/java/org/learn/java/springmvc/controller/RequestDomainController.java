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
@RequestMapping("/request-domain")
public class RequestDomainController {

    private static final Logger logger = LoggerFactory.getLogger(RequestDomainController.class);

    // #1. Servlet API 返回是转发而非重定向, 因为浏览器无法重定向到 web-inf 下资源
    @GetMapping(value = "/servletapi")
    public String ServletRequestDomain(HttpServletRequest request) {
        request.setAttribute("requestDomain", "request-domain");
        return "target";
    }

    // #2. ModelAndView
    @GetMapping(value = "/modelandview")
    public ModelAndView ModelAndViewRequestDomain() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("requestDomain", "request-domain");
        mav.setViewName("target");
        return mav;
    }

    // #3. Model
    @GetMapping(value = "/model")
    public String ModelRequestDomain(Model model) {
        model.addAttribute("requestDomain", "request-domain");
        logger.info(model.getClass().getName());  // BindingAwareModelMap
        return "target";
    }

    // #4. Map
    @GetMapping(value = "/map")
    public String ModelAndViewRequestDomain(Map<String, Object> map) {
        logger.info(map.getClass().getName());  // BindingAwareModelMap
        map.put("requestDomain", "request-domain");
        return "target";
    }

    // #5. ModelMap
    @GetMapping(value = "/modelmap")
    public String ModelAndViewRequestDomain(ModelMap modelMap) {
        modelMap.put("requestDomain", "request-domain");
        logger.info(modelMap.getClass().getName());  // BindingAwareModelMap
        return "target";
    }
}