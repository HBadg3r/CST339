package com.gcu.service;

import java.util.List;

import com.gcu.model.UserModel;

public interface LoginServiceInterface {

    boolean authenticate(String username, String password);

    void addUser(UserModel user);
    
    List<UserModel> getUsers();
    
    void init();
	
	void destroy();
}
