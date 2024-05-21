package com.eventeasy.EventEasy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class UserController {

    @GetMapping("/signing")
    public ModelAndView signing() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("log-page");
        return modelAndView;
    }

    @GetMapping("/auth/registration")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reg-page");
        return modelAndView;
    }
}
