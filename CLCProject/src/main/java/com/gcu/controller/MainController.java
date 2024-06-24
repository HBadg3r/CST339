package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.gcu.model.ProductModel;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;
import com.gcu.service.LoginService;
import com.gcu.service.MenuServiceInterface;
import com.gcu.service.UserServiceInterface;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private MenuServiceInterface menuService;
	
	@Autowired
    private UserServiceInterface userService;
	
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

	@GetMapping("/menu")
	public String menu(Model model) {
		
		menuService.test();
		
	    model.addAttribute("title", "Menu");
	    model.addAttribute("menuItems", menuService.getMenuItems());
	    return "menu";
	}

	@GetMapping("/admin")

	public String admin(Model model) {
		
	    ProductModel product = new ProductModel();
	    BindingResult bindingResult = new BeanPropertyBindingResult(product, "product");
	    model.addAttribute("product", product);
	    model.addAttribute("org.springframework.validation.BindingResult.product", bindingResult);
	    return "admin";

	}

	@PostMapping("/login")
	public String login(@ModelAttribute(name="LoginForm") LoginService loginForm, Model model)
	{
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if("admin".equals(username) && "admin".equals(password))
		{
			model.addAttribute("admin", true);
			model.addAttribute("name", username);
			return "index";
		}
		
		model.addAttribute("loggedIn", true);
		model.addAttribute("name", username);
		return "index";
	}

	@PostMapping("/addProduct")

	public String addProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model) {

	    if (bindingResult.hasErrors()) {
	        return "admin"; // return to the same page with errors
	    }
	    menuService.updateMenu(product);
	    return "admin"; // redirect to the admin page after successful addition

	}


	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new RegistrationModel());
	    return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "register";
	    }
	    
	    userService.register(user);
	    
	    return "redirect:/register?success";
	}
}