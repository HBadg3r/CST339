package com.gcu.service;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.UserModel;

public class LoginService implements LoginServiceInterface
{
	// Currently the list of users is not in a database, but just a list hard-coded here.
	public List<UserModel> users = new ArrayList<UserModel>();
	private String username;
	private String password;
	
	public LoginService() {
		users.add(new UserModel("John", "Doe", "john.doe@example.com", "1234567890", "john.doe", "password"));
        users.add(new UserModel("Jane", "Smith", "jane.smith@example.com", "9876543210", "jane.smith", "password"));
        users.add(new UserModel("Admin", "User", "admin@example.com", "5555555555", "admin", "admin"));
        users.add(new UserModel("Alice", "Johnson", "alice.johnson@example.com", "1112223333", "alice.johnson", "password"));
        users.add(new UserModel("Bob", "Brown", "bob.brown@example.com", "4443332222", "bob.brown", "password"));
	}

	public boolean authenticate(String username, String password) {
		for (UserModel currentUser : users) {
			if (currentUser.getUserName().equals(username) && currentUser.getPassWord().equals(password)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Temporary method to add a user to the list of users. This method will be removed once a database is implemented.
	 * @param user The user to add
	 */
	public void addUser(UserModel user) {
		users.add(user);
	}
	
	/**
	 * Returns the hard-coded list of users
	 * @return the hard-coded list of users
	 */
	public List<UserModel> getUsers() {
		return users;
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
