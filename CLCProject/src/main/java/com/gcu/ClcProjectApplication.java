/**
 * The main application class for the CLC Project.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Enables auto-configuration of the Spring Boot application.
 * 
 * @see SpringApplication
 */
@SpringBootApplication
/**
 * Specifies the package to scan for Spring components.
 * 
 * @see ComponentScan
 */
@ComponentScan({"com.gcu"})
public class ClcProjectApplication {

    /**
     * The main entry point of the application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application.
        SpringApplication.run(ClcProjectApplication.class, args);
    }

}