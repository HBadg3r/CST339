package com.gcu.service;

import java.util.List;

import com.gcu.model.UserModel;

public class RegistrationService implements RegistrationServiceInterface {

	@Override
	public void register(UserModel model, LoginServiceInterface loginService) {
		loginService.addUser(model);
		System.out.println("Registering user " + model.toString());
	}
	
	@Override
	public boolean authenticate(UserModel user, LoginServiceInterface loginService) {
		List<UserModel> users = loginService.getUsers();
		
		for (UserModel currentUser : users) {
			if (currentUser.getUserName().equals(user.getUserName()) || currentUser.getEmail().equals(user.getEmail()) ) {
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
