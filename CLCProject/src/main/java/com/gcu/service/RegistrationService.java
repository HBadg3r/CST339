package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;

public class RegistrationService implements RegistrationServiceInterface {

    @Autowired
    private DataAccessInterface<UserModel> usersDataService;

    @Override
    public void register(UserModel model, LoginServiceInterface loginService) {
    	usersDataService.create(model);
        System.out.println("Registering user " + model.toString());
    }

    @Override
    public boolean authenticate(UserModel user, LoginServiceInterface loginService) {
        List<UserModel> users = usersDataService.findAll();

        for (UserModel currentUser : users) {
            if (currentUser.getUserName().equals(user.getUserName()) || currentUser.getEmail().equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void init() {
        System.out.println("Initialize Registration Service");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Registration Service");
    }
}
