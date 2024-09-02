package org.learn.java.springmvc.controller;

import org.learn.java.springmvc.dao.EmployeeDAO;
import org.learn.java.springmvc.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class EmpolyeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmpolyeeController.class);
    private EmployeeDAO employeeDAO;

    public EmpolyeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employee")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("employee_list");
        mav.addObject("employeeList", employeeDAO.getAll());
        return mav;
    }

    @GetMapping("/employee/{id:\\d+}")
    public ModelAndView get(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("employee_update");
        Employee employee = employeeDAO.get(id);
        mav.addObject("employee", employee);
        return mav;
    }

    @DeleteMapping("/employee/{id:\\d+}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDAO.delete(id);
        return "redirect:/employee";
    }

    @PostMapping("/employee")
    public String add(Employee employee ) {
        employeeDAO.save(employee);
        return "redirect:/employee";
    }

    @PutMapping("/employee")
    public String update(Employee employee ) {
        employeeDAO.save(employee);
        return "redirect:/employee";
    }
}