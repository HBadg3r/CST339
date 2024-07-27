/**
 * A Spring MVC controller that handles various requests for the application.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
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

/**
 * Marks this class as a controller where every method returns a view.
 * 
 * @see Controller
 */
@Controller
public class MainController {

    /**
     * The product service instance, injected by Spring.
     */
    @Autowired
    private ProductServiceInterface productService;

    /**
     * The registration service instance, injected by Spring.
     */
    @Autowired
    private RegistrationServiceInterface registerService;

    /**
     * The login service instance, injected by Spring.
     */
    @Autowired
    private LoginServiceInterface loginService;

    /**
     * Handles GET requests to the home page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/")
    public String home(Model model) {
        // Set the title attribute.
        model.addAttribute("title", "Home");
        return "index";
    }

    /**
     * Handles GET requests to the menu page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/menu")
    public String menu(Model model) {
        // Set the title attribute.
        model.addAttribute("title", "Menu");
        // Set the menu items attribute.
        model.addAttribute("menuItems", productService.getProductItems());
        return "menu";
    }

    /**
     * Handles GET requests to the admin page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        // Create a new product model.
        ProductModel product = new ProductModel();
        // Create a new binding result.
        BindingResult bindingResult = new BeanPropertyBindingResult(product, "product");
        // Set the product attribute.
        model.addAttribute("product", product);
        // Set the binding result attribute.
        model.addAttribute("org.springframework.validation.BindingResult.product", bindingResult);
        return "product";
    }

    /**
     * Handles GET requests to the login page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/login")
    public String login(Model model) {
        // Set the title attribute.
        model.addAttribute("title", "Login");
        return "login";
    }

    /**
     * Handles POST requests to the login page.
     * 
     * @param loginForm The login form object.
     * @param model The model object.
     * @return The view name.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute(name = "LoginForm") LoginService loginForm, Model model) {
        // Get the username and password from the login form.
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        // Check if the user is authenticated.
        if (loginService.authenticate(username, password)) {
            // Check if the user is an admin.
            if ("admin".equals(username) && "admin".equals(password)) {
                // Set the admin attribute.
                model.addAttribute("admin", true);
                // Set the name attribute.
                model.addAttribute("name", username);
                return "index";
            }
            // Set the name attribute.
            model.addAttribute("name", username);
        } else {
            // Set the error attribute.
            model.addAttribute("error", "User does not exist!");
            return "login";
        }

        return "index";
    }

    /**
     * Handles GET requests to the registration page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Create a new user model.
        model.addAttribute("user", new UserModel());
        return "register";
    }
    
    /**
     * Handles POST requests to the registration page.
     * 
     * @param user The user object.
     * @param bindingResult The binding result object.
     * @param model The model object.
     * @return The view name.
     */
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
    
    /**
     * Handles GET requests to the add product page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        ProductModel product = new ProductModel();
        model.addAttribute("product", product);
        model.addAttribute("title", "Add Product");
        
        return "addProduct";
    }
    
    /**
     * Handles POST requests to the add product page.
     * 
     * @param product The product object.
     * @param result The binding result object.
     * @return The view name.
     */
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
        if (result.hasErrors()) {
            return "addProduct"; // Return to the same page if there are errors
        }
        // Add the product to the database or perform any other business logic
        productService.updateProducts(product);
        return "redirect:/displayProduct"; // Redirect to the products page after successful addition
    }
    
    /**
     * Handles GET requests to the display product page.
     * 
     * @param model The model object.
     * @return The view name.
     */
    @GetMapping("/displayProduct")
    public String displayProduct(Model model) {
        List<ProductModel> products = new ArrayList<ProductModel>();
        products = productService.getProductItems();		
        model.addAttribute("title", "View Products");
        model.addAttribute("products", products);
        return "displayProduct";
    }
    
    /**
     * Handles GET requests to the update product page.
     * 
     * @param model The model object.
     * @return The view name.
     */
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
    
    /**
     * Handles POST requests to the update product page.
     * 
     * @param product The product object.
     * @param result The binding result object.
     * @return The view name.
     */
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
        if (result.hasErrors()) {
            return "updateProduct"; // Return to the same page if there are errors
        }
        // Add the product to the database or perform any other business logic
        productService.update(product);
        return "redirect:/displayProduct"; // Redirect to the products page after successful addition
    }
    
    /**
     * Handles GET requests to the delete product page.
     * 
     * @param model The model object.
     * @return The view name.
     */
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
    
    /**
     * Handles POST requests to the delete product page.
     * 
     * @param product The product object.
     * @param result The binding result object.
     * @return The view name.
     */
    @PostMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute("product") ProductModel product, BindingResult result) {
        if (result.hasErrors()) {
            return "deleteProduct"; // Return to the same page if there are errors
        }
        // Add the product to the database or perform any other business logic
        productService.delete(product);
        return "redirect:/displayProduct"; // Redirect to the products page after successful deletion
    }
}