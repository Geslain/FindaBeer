package com.example.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController{

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "test";
    }

}