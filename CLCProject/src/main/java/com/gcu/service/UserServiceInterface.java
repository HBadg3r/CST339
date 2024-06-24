package com.gcu.service;

import com.gcu.model.UserModel;

public interface UserServiceInterface {

	void register(UserModel model);
	
	void init();
	
	void destroy();
}
