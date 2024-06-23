package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.gcu.form.LogInForm;

@Controller
public class LogInControl 
{
	@GetMapping("/login")
	public String getLoginForm()
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute(name="LoginForm") LogInForm loginForm, Model model)
	{
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if("admin".equals(username) && "admin".equals(password))
		{
			return "home";
		}
		
		model.addAttribute("invalidLogIn",true);
		return "login";
	}
}
