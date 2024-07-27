/**
 * Provides a contract for login functionality.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.service;

import java.util.List;

import com.gcu.model.UserModel;

/**
 * LoginServiceInterface defines the methods that must be implemented by any class that provides login functionality.
 */
public interface LoginServiceInterface {

    /**
     * Authenticates a user based on their username and password.
     * 
     * @param username Username to authenticate.
     * @param password Password to authenticate.
     * @return True if authentication is successful, false otherwise.
     */
    boolean authenticate(String username, String password);

    /**
     * Adds a new user to the system.
     * 
     * @param user The user to add.
     */
    void addUser(UserModel user);
    
    /**
     * Returns a list of all users in the system.
     * 
     * @return A list of all users in the system.
     */
    List<UserModel> getUsers();
    
    /**
     * Initializes the login service.
     */
    void init();
	
	/**
	 * Destroys the login service.
	 */
	void destroy();
}