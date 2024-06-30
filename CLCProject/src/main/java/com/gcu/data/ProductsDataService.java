package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.ProductModel;

@Service
public class ProductsDataService implements DataAccessInterface<ProductModel> {
    
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    /**
     * Non-Default constructor for constructor injection
     */
    public ProductsDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    /**
     * CRUD: finder to return all entries
     */
    public List<ProductModel> findAll() {
        String sql = "SELECT * FROM products";
        List<ProductModel> products = new ArrayList<ProductModel>();
        try {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while (srs.next()) {
                products.add(new ProductModel(srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getString("category")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    /**
     * CRUD: finder to return a specific item
     */
    public ProductModel findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        ProductModel product = null;
        try {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
            if (srs.next()) {
                product = new ProductModel(srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getString("category"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * CRUD: create an entity
     */
    public boolean create(ProductModel product) {
        String sql = "INSERT INTO products (name, description, price, category) VALUES (?, ?, ?, ?)";
        
        try {
            // Execute SQL Insert
            int rows = jdbcTemplateObject.update(sql, 
                    product.getName(), 
                    product.getDescription(), 
                    product.getPrice(), 
                    product.getCategory());
            
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
    public boolean update(ProductModel product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, category = ? WHERE id = ?";
        
        try {
            // Execute SQL Update
            int rows = jdbcTemplateObject.update(sql, 
                    product.getName(), 
                    product.getDescription(), 
                    product.getPrice(), 
                    product.getCategory(),
                    product.getId());
            
            // Return result of Update
            return rows == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * CRUD: delete an entity
     */
    public boolean delete(ProductModel product) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try {
            // Execute SQL Delete
            int rows = jdbcTemplateObject.update(sql, product.getId());
            
            // Return result of Delete
            return rows == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
