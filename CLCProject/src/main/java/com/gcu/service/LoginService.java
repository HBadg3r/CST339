package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;

public class LoginService implements LoginServiceInterface {

    @Autowired
    private DataAccessInterface<UserModel> loginDataService;

    private String username;
    private String password;

    public LoginService() {
    }

    public boolean authenticate(String username, String password) {
        // Retrieve all users
        List<UserModel> users = loginDataService.findAll();
        
        // Loop through the list of users
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
     * @param user The user to add
     */
    public void addUser(UserModel user) {
        loginDataService.create(user);
    }

    /**
     * Returns the list of users from the database
     * @return the list of users from the database
     */
    public List<UserModel> getUsers() {
        return loginDataService.findAll();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        System.out.println("Initialize Login Service");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Login Service");
    }
}
