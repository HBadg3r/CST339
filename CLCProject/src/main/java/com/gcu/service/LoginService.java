/**
 * Provides login functionality for the application.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;

/**
 * LoginService is a Spring-based service class that provides login functionality for the application.
 * It uses a DataAccessInterface to interact with the data storage system.
 */
public class LoginService implements LoginServiceInterface {

    /**
     * Data access object for user data.
     */
    @Autowired
    private DataAccessInterface<UserModel> loginDataService;

    /**
     * Username for the current user.
     */
    private String username;

    /**
     * Password for the current user.
     */
    private String password;

    /**
     * Default constructor for LoginService.
     */
    public LoginService() {
        // No-arg constructor for bean creation
    }

    /**
     * Authenticates a user based on their username and password.
     * 
     * @param username Username to authenticate.
     * @param password Password to authenticate.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // Retrieve all users from the data storage system
        List<UserModel> users = loginDataService.findAll();
        
        // Loop through the list of users to find a match
        for (UserModel user : users) {
            // Check if the username and password match
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
                return true; // Authentication successful
            }
        }
        
        return false; // Authentication failed
    }

    /**
     * Temporary method to add a user to the list of users. This method will be removed once a database is implemented.
     * 
     * @param user The user to add.
     */
    public void addUser(UserModel user) {
        loginDataService.create(user);
    }

    /**
     * Returns the list of users from the data storage system.
     * 
     * @return The list of users from the data storage system.
     */
    public List<UserModel> getUsers() {
        return loginDataService.findAll();
    }

    /**
     * Returns the username for the current user.
     * 
     * @return The username for the current user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the current user.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for the current user.
     * 
     * @return The password for the current user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the current user.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Initializes the login service.
     */
    @Override
    public void init() {
        System.out.println("Initialize Login Service");
    }

    /**
     * Destroys the login service.
     */
    @Override
    public void destroy() {
        System.out.println("Destroy Login Service");
    }
}