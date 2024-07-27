/**
 * This class implements the data access interface for UserModel.
 * It provides basic CRUD (Create, Read, Update, Delete) operations for users.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.UserModel;

/**
 * Marks this class as a Spring Service.
 * 
 * @see Service
 */
@Service
public class UserDataService implements DataAccessInterface<UserModel> {

    /**
     * The data source for the database connection.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * The JDBC template object for executing SQL queries.
     */
    private JdbcTemplate jdbcTemplateObject;

    /**
     * Non-Default constructor for constructor injection.
     * 
     * @param dataSource The data source for the database connection.
     */
    public UserDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all users.
     */
    @Override
    public List<UserModel> findAll() {
        // SQL query to select all users
        String sql = "SELECT * FROM users";

        // Initialize an empty list to store the users
        List<UserModel> users = new ArrayList<UserModel>();

        try {
            // Execute the SQL query and store the results in a SqlRowSet
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);

            // Iterate over the results and create a UserModel for each row
            while (srs.next()) {
                users.add(new UserModel(
                        srs.getString("first_name"),
                        srs.getString("last_name"),
                        srs.getString("email"),
                        srs.getString("phone_number"),
                        srs.getString("user_name"),
                        srs.getString("pass_word")));
            }
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return the list of users
        return users;
    }

    /**
     * Retrieves a user by its ID from the database.
     * 
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     */
    @Override
    public UserModel findById(int id) {
        // Assuming userName is the unique identifier
        // This method is not implemented as it is not clear what the ID refers to
        // If it refers to the user_name, then it should be a String parameter
        return null;
    }

    /**
     * Creates a new user in the database.
     * 
     * @param user The user to create.
     * @return True if the user was created successfully, false otherwise.
     */
    @Override
    public boolean create(UserModel user) {
        // SQL query to insert a new user
        String sql = "INSERT INTO users (first_name, last_name, email, phone_number, user_name, pass_word) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Execute the SQL query with the user parameters and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, 
                    user.getFirstName(), 
                    user.getLastName(), 
                    user.getEmail(), 
                    user.getPhoneNumber(), 
                    user.getUserName(), 
                    user.getPassWord());

            // Return true if the user was created successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the user was not created successfully
        return false;
    }

    /**
     * Updates an existing user in the database.
     * 
     * @param user The user to update.
     * @return True if the user was updated successfully, false otherwise.
     */
    @Override
    public boolean update(UserModel user) {
        // SQL query to update an existing user
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, pass_word = ? WHERE user_name = ?";

        try {
            // Execute the SQL query with the user parameters and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, 
                    user.getFirstName(), 
                    user.getLastName(), 
                    user.getEmail(), 
                    user.getPhoneNumber(), 
                    user.getPassWord(), 
                    user.getUserName());
            // Return true if the user was updated successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the user was not updated successfully
        return false;
    }

    /**
     * Deletes a user from the database.
     * 
     * @param user The user to delete.
     * @return True if the user was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(UserModel user) {
        // SQL query to delete a user
        String sql = "DELETE FROM users WHERE user_name = ?";

        try {
            // Execute the SQL query with the user parameter and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, user.getUserName());

            // Return true if the user was deleted successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the user was not deleted successfully
        return false;
    }
}