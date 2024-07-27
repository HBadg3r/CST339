/**
 * A Spring configuration class that defines and initializes various services.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.service.*;

/**
 * Marks this class as a source of bean definitions.
 * 
 * @see Configuration
 */
@Configuration
public class SpringConfig {

    /**
     * Creates and initializes a ProductService instance.
     * 
     * @return A ProductService instance.
     */
    @Bean(name = "menuService", initMethod = "init", destroyMethod = "destroy")
    public ProductServiceInterface MenuService() {
        // Create a new ProductService instance and return it.
        return new ProductService();
    }

    /**
     * Creates and initializes a RegistrationService instance.
     * 
     * @return A RegistrationService instance.
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public RegistrationServiceInterface userService() {
        // Create a new RegistrationService instance and return it.
        return new RegistrationService();
    }

    /**
     * Creates and initializes a LoginService instance.
     * 
     * @return A LoginService instance.
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LoginServiceInterface loginService() {
        // Create a new LoginService instance and return it.
        return new LoginService();
    }
}