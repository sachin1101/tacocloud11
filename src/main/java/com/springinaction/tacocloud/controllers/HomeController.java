package com.springinaction.tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller  -- Replaced with @Configuration Class - WebConfig and added the home.html view to the url '/'
public class HomeController {

    @GetMapping({"","/","/home"})
    public String home(){
        return "home";
    }
}
