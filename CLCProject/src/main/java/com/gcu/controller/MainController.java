package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.RegistrationModel;
import com.gcu.service.LoginService;

import jakarta.validation.Valid;

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
        return "login";
    }
    
    @PostMapping("/login")
	public String login(@ModelAttribute(name="LoginForm") LoginService loginForm, Model model)
	{
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if("admin".equals(username) && "admin".equals(password))
		{
			return "home";
		}
		
		model.addAttribute("invalidLogIn",true);
		model.addAttribute("loggedIn", true);
		model.addAttribute("name", username);
		return "index";
	}
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        return "redirect:/register?success";
    }
}
