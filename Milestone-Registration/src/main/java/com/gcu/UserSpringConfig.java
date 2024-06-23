package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gcu")
public class UserSpringConfig {

	@Bean(initMethod="init", destroyMethod="destroy")
	public UserInterface userService() {
		return new UserService();
	}
}

