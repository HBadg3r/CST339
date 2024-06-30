package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.UserModel;

@Service
public class UserDataService implements DataAccessInterface<UserModel> {
    
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    /**
     * Non-Default constructor for constructor injection
     */
    public UserDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    /**
     * CRUD: finder to return all entries
     */
    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> users = new ArrayList<UserModel>();
        try {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while (srs.next()) {
                users.add(new UserModel(
                        srs.getString("first_Name"),
                        srs.getString("last_name"),
                        srs.getString("email"),
                        srs.getString("phone_number"),
                        srs.getString("user_name"),
                        srs.getString("pass_word")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * CRUD: finder to return a specific item
     */
    @Override
    public UserModel findById(int id) {
        // Assuming userName is the unique identifier
        return null;
    }

    /**
     * CRUD: create an entity
     */
    @Override
    public boolean create(UserModel user) {
        String sql = "INSERT INTO users (first_name, last_name, email, phone_number, user_name, pass_word) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            // Execute SQL Insert
            int rows = jdbcTemplateObject.update(sql, 
                    user.getFirstName(), 
                    user.getLastName(), 
                    user.getEmail(), 
                    user.getPhoneNumber(), 
                    user.getUserName(), 
                    user.getPassWord());
            
            // Return result of Insert
            return rows == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * CRUD: update an entity
     */
    @Override
    public boolean update(UserModel user) {
        // Implement update functionality if needed
        return false;
    }
    
    /**
     * CRUD: delete an entity
     */
    @Override
    public boolean delete(UserModel user) {
        // Implement delete functionality if needed
        return false;
    }
}
