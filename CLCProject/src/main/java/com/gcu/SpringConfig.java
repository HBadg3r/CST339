package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.service.*;

@Configuration
public class SpringConfig {

	@Bean(name="menuService", initMethod="init", destroyMethod="destroy")
	public ProductServiceInterface MenuService() {
		return new ProductService();
	}
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public RegistrationServiceInterface userService() {
		return new RegistrationService();
	}
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public LoginServiceInterface loginService() {
		return new LoginService();
	}
	
}
