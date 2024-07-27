/**
 * Provides registration-related functionality.
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
 * RegistrationService is a Spring-based service class that provides registration-related functionality.
 * It uses a DataAccessInterface to interact with the data storage system.
 */
public class RegistrationService implements RegistrationServiceInterface {

    /**
     * Data access object for user data.
     */
    @Autowired
    private DataAccessInterface<UserModel> usersDataService;

    /**
     * Registers a new user in the system.
     * 
     * @param model The user to register.
     * @param loginService The login service to use for authentication.
     */
    @Override
    public void register(UserModel model, LoginServiceInterface loginService) {
        // Create the user in the data storage system
        usersDataService.create(model);
        System.out.println("Registering user " + model.toString());
    }

    /**
     * Authenticates a user by checking if their username or email is already in use.
     * 
     * @param user The user to authenticate.
     * @param loginService The login service to use for authentication.
     * @return True if the user is authenticated, false otherwise.
     */
    @Override
    public boolean authenticate(UserModel user, LoginServiceInterface loginService) {
        // Get all existing users from the data storage system
        List<UserModel> users = usersDataService.findAll();

        // Check if the username or email is already in use
        for (UserModel currentUser : users) {
            if (currentUser.getUserName().equals(user.getUserName()) || currentUser.getEmail().equals(user.getEmail())) {
                // If the username or email is already in use, return false
                return false;
            }
        }
        // If the username and email are not in use, return true
        return true;
    }

    /**
     * Initializes the RegistrationService.
     */
    @Override
    public void init() {
        System.out.println("Initialize Registration Service");
    }

    /**
     * Destroys the RegistrationService.
     */
    @Override
    public void destroy() {
        System.out.println("Destroy Registration Service");
    }
}