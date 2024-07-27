package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	/*
	 * THE FOLLOWING CONTROLLER IS FOR THE HOME PAGE
	 */
	@GetMapping("/")
	public String home(Model model) {
	    model.addAttribute("title", "Home");
	    return "index";
	}


	/*
	 * THE FOLLOWING CONTORLLER IS FOR THE MENU PAGE
	 */
	
	@GetMapping("/menu")
	public String menu(Model model) {		
	    model.addAttribute("title", "Menu");
	    model.addAttribute("menuItems", productService.getProductItems());
	    return "menu";
	}

	/*
	 * THE FOLLWOING CONTROLLER IS FOR THE ADMIN PAGE
	 */
	
	@GetMapping("/admin")
	public String admin(Model model) {
		
	    ProductModel product = new ProductModel();
	    BindingResult bindingResult = new BeanPropertyBindingResult(product, "product");
	    model.addAttribute("product", product);
	    model.addAttribute("org.springframework.validation.BindingResult.product", bindingResult);
	    return "product";

	}

	/*
	 * THE FOLLOWING CONTORLLERS AID LOGIN
	 */
	
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
	
	
	/*
	 * THE FOLLOWING CONTROLLERS AID REGISTRATION
	 */
	
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
	
	/*
	 * THE FOLLOWING CONTROLLERS AID PRODUCT MANAGEMENT
	 */
	
	//PRODUCT PAGE
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		ProductModel product = new ProductModel();
		model.addAttribute("product", product);
		model.addAttribute("title", "Add Product");
		
		return "addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
	    if (result.hasErrors()) {
	        return "addProduct"; // Return to the same page if there are errors
	    }
	    // Add the product to the database or perform any other business logic
	    productService.updateProducts(product);
	    return "redirect:/displayProduct"; // Redirect to the products page after successful addition
	}
	
	//VIEW PRODUCT PAGE
	@GetMapping("/displayProduct")
	public String displayProduct(Model model) {
		List<ProductModel> products = new ArrayList<ProductModel>();
		products = productService.getProductItems();		
		model.addAttribute("title", "View Products");
		model.addAttribute("products", products);
		return "displayProduct";
	}
	
	//UPDATE PRODUCT PAGE
	@GetMapping("/updateProduct")
	public String updateProduct(Model model) {
		List<ProductModel> products = new ArrayList<ProductModel>();
		products = productService.getProductItems();
		ProductModel product = new ProductModel();
		
		model.addAttribute("product", product);
		model.addAttribute("title", "Update Product");
		model.addAttribute("products", products);
		
		return "updateProduct";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
	    if (result.hasErrors()) {
	        return "updateProduct"; // Return to the same page if there are errors
	    }
	    // Add the product to the database or perform any other business logic
	    productService.update(product);
	    return "redirect:/displayProduct"; // Redirect to the products page after successful addition
	}
	
	//DELETE PRODUCT PAGE
	@GetMapping("/deleteProduct")
	public String deleteProduct(Model model) {
		List<ProductModel> products = new ArrayList<ProductModel>();
		products = productService.getProductItems();
		ProductModel product = new ProductModel();
		
		model.addAttribute("product", product);
		model.addAttribute("title", "Delete Product");
		model.addAttribute("products", products);
		
		return "deleteProduct";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
	    if (result.hasErrors()) {
	        return "deleteProduct"; // Return to the same page if there are errors
	    }
	    // Add the product to the database or perform any other business logic
	    productService.delete(product);
	    return "redirect:/displayProduct"; // Redirect to the products page after successful addition
	}
}