package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.RegistrationModel;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        //model.addAttribute("loginModel", new LoginModel());
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        //model.addAttribute("registrationModel", new RegistrationModel());
        return "register";
    }
}
