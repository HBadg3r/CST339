package com.gcu.service;

import com.gcu.model.UserModel;

public interface RegistrationServiceInterface {

	void register(UserModel user, LoginServiceInterface loginService);
		
	boolean authenticate(UserModel user, LoginServiceInterface loginService);
	
	void init();
	
	void destroy();
}
