package com.gcu.service;

import java.util.List;

import com.gcu.model.UserModel;

public class RegistrationService implements RegistrationServiceInterface {

	@Override
	public void register(UserModel model, LoginServiceInterface loginService) {
		loginService.addUser(model);
	}
	
	@Override
	public boolean authenticate(UserModel user, LoginServiceInterface loginService) {
		List<UserModel> users = loginService.getUsers();
		
		for (UserModel currentUser : users) {
			if (currentUser.getUserName().equals(user.getUserName()) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Initialize");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy");
	}



}
