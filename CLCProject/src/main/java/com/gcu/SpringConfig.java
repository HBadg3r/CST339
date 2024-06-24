package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.service.*;

@Configuration
public class SpringConfig {

	@Bean(name="menuService", initMethod="init", destroyMethod="destroy")
	public MenuServiceInterface MenuService() {
		return new MenuService();
	}
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public UserServiceInterface userService() {
		return new UserService();
	}
}
