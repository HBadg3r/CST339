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
import com.gcu.model.UserModel;
import com.gcu.service.LoginService;
import com.gcu.service.LoginServiceInterface;
import com.gcu.service.ProductServiceInterface;
import com.gcu.service.RegistrationServiceInterface;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private ProductServiceInterface productService;
	
	@Autowired
    private RegistrationServiceInterface registerService;
	
	@Autowired
	private LoginServiceInterface loginService;
	
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
		
		productService.test();
		
	    model.addAttribute("title", "Menu");
	    model.addAttribute("menuItems", productService.getProductItems());
	    return "menu";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		
	    ProductModel product = new ProductModel();
	    BindingResult bindingResult = new BeanPropertyBindingResult(product, "product");
	    model.addAttribute("product", product);
	    model.addAttribute("org.springframework.validation.BindingResult.product", bindingResult);
	    return "product";

	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add Product");
		
		return "addProduct";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute(name="LoginForm") LoginService loginForm, Model model)
	{
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if (loginService.authenticate(username, password)) {
			if("admin".equals(username) && "admin".equals(password))
			{
				model.addAttribute("admin", true);
				model.addAttribute("name", username);
				return "index";
			}
			model.addAttribute("name", username);
		} else {
			model.addAttribute("error", "User does not exist!");
			return "login";
		}
		
		return "index";
	}

	@PostMapping("/addProduct")

	public String addProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model) {

	    if (bindingResult.hasErrors()) {
	        return "admin"; // return to the same page with errors
	    }
	    productService.updateProducts(product);
	    return "admin"; // redirect to the admin page after successful addition

	}


	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new UserModel());
	    return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "register";
	    }
	    
	    if (registerService.authenticate(user, loginService)) {
		    registerService.register(user, loginService);
	    } else {
	    	model.addAttribute("error", "User already exists!");
	    	return "register";
	    }
	    
	    return "redirect:/register?success";
	}
}