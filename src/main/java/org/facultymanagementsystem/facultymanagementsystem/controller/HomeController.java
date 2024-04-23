package org.facultymanagementsystem.facultymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String homePage(Model model){
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
}
