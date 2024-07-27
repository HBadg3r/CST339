/**
 * Represents a user with their properties and behaviors.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * UserModel is a Java class that encapsulates the properties and behaviors of a user.
 * It includes fields for first name, last name, email, phone number, username, and password, 
 * along with their respective getter and setter methods.
 */
public class UserModel {

    /**
     * First name of the user.
     */
    @NotNull(message = "First Name is a required field")
    @Size(min = 1, max = 40, message = "First Name must be between 1 and 40 characters")
    private String firstName;

    /**
     * Last name of the user.
     */
    @NotNull(message = "Last Name is a required field")
    @Size(min = 1, max = 40, message = "Last Name must be between 1 and 40 characters")
    private String lastName;

    /**
     * Email address of the user.
     */
    @NotNull(message = "Email is a required field")
    @Email(message = "Invalid Email format")
    private String email;

    /**
     * Phone number of the user.
     */
    @NotNull(message = "Phone Number is a required field")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be 10 digits")
    private String phoneNumber;

    /**
     * Username chosen by the user.
     */
    @NotNull(message = "Username is a required field")
    @Size(min = 1, max = 40, message = "Username must be between 1 and 40 characters")
    private String userName;

    /**
     * Password chosen by the user.
     */
    @NotNull(message = "Password is a required field")
    @Size(min = 1, max = 40, message = "Password must be between 1 and 40 characters")
    private String passWord;

    /**
     * Default constructor for UserModel.
     */
    public UserModel() {
        // No-arg constructor for bean creation
    }

    /**
     * Constructs a new UserModel object with the specified properties.
     * 
     * @param first   First name of the user.
     * @param last    Last name of the user.
     * @param email   Email address of the user.
     * @param number  Phone number of the user.
     * @param username Username chosen by the user.
     * @param password Password chosen by the user.
     */
    public UserModel(String first, String last, String email, String number, String username, String password) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.phoneNumber = number;
        this.userName = username;
        this.passWord = password;
    }

    // Getters and Setters

    /**
     * Returns the first name of the user.
     * 
     * @return First name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName First name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     * 
     * @return Last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName Last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the user.
     * 
     * @return Email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email Email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the user.
     * 
     * @return Phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phoneNumber Phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the username chosen by the user.
     * 
     * @return Username chosen by the user.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Sets the username chosen by the user.
     * 
     * @param userName Username chosen by the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password chosen by the user.
     * 
     * @return Password chosen by the user.
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the password chosen by the user.
     * 
     * @param passWord Password chosen by the user.
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}