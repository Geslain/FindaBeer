package com.example.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Gess on 07/09/2016.
 */
@Controller
public class TestController{

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "test";
    }

}