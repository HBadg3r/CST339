package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserModel {

	
	@NotNull(message="First Name is a required field")
	@Size(min=1, max=40, message="First Name must be between 1 and 40 characters")
	private String firstName;
	
	@NotNull(message="Last Name is a required field")
	@Size(min=1, max=40, message="Last Name must be between 1 and 40 characters")
	private String lastName;
	
	@NotNull(message="Email is a required field")
	@Email(message="Invalid Email format")
	private String email;
	
	@NotNull(message="Phone Number is a required field")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be 10 digits")
	private String phoneNumber;
	
	@NotNull(message="Username is a required field")
	@Size(min=1, max=40, message="Username must be between 1 and 40 characters")
	private String userName;
	
	@NotNull(message="Password is a required field")
	@Size(min=1, max=40, message="Password must be between 1 and 40 characters")
	private String passWord;
	
	public UserModel() {
		
	}
	
	public UserModel(String first, String last, String email, String number, String username, String password) {
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.phoneNumber = number;
		this.userName = username;
		this.passWord = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
