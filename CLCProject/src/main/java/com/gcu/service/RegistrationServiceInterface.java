/**
 * Provides a contract for registration-related functionality.
 * 
 * @author [Your Name]
 * @version 1.0
 */
package com.gcu.service;

import com.gcu.model.UserModel;

/**
 * RegistrationServiceInterface defines the methods that must be implemented by any class that provides registration-related functionality.
 */
public interface RegistrationServiceInterface {

    /**
     * Registers a new user in the system.
     * 
     * @param user The user to register.
     * @param loginService The login service to use for authentication.
     */
    void register(UserModel user, LoginServiceInterface loginService);
    
    /**
     * Authenticates a user by checking if their username or email is already in use.
     * 
     * @param user The user to authenticate.
     * @param loginService The login service to use for authentication.
     * @return True if the user is authenticated, false otherwise.
     */
    boolean authenticate(UserModel user, LoginServiceInterface loginService);
    
    /**
     * Initializes the RegistrationService.
     */
    void init();
    
    /**
     * Destroys the RegistrationService.
     */
    void destroy();
}