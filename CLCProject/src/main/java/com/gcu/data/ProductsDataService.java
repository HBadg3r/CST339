/**
 * This class implements the data access interface for ProductModel.
 * It provides basic CRUD (Create, Read, Update, Delete) operations for products.
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

import com.gcu.model.ProductModel;

/**
 * Marks this class as a Spring Service.
 * 
 * @see Service
 */
@Service
public class ProductsDataService implements DataAccessInterface<ProductModel> {

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
    public ProductsDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return A list of all products.
     */
    @Override
    public List<ProductModel> findAll() {
        // SQL query to select all products
        String sql = "SELECT * FROM products";

        // Initialize an empty list to store the products
        List<ProductModel> products = new ArrayList<ProductModel>();

        try {
            // Execute the SQL query and store the results in a SqlRowSet
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);

            // Iterate over the results and create a ProductModel for each row
            while (srs.next()) {
                products.add(new ProductModel(srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getString("category")));
            }
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return the list of products
        return products;
    }

    /**
     * Retrieves a product by its ID from the database.
     * 
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    @Override
    public ProductModel findById(int id) {
        // SQL query to select a product by its ID
        String sql = "SELECT * FROM products WHERE id = ?";

        // Initialize a null product to store the result
        ProductModel product = null;

        try {
            // Execute the SQL query with the ID parameter and store the result in a SqlRowSet
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);

            // If a result is found, create a ProductModel and store it in the product variable
            if (srs.next()) {
                product = new ProductModel(srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getString("category"));
            }
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return the product
        return product;
    }

    /**
     * Creates a new product in the database.
     * 
     * @param product The product to create.
     * @return True if the product was created successfully, false otherwise.
     */
    @Override
    public boolean create(ProductModel product) {
        // SQL query to insert a new product
        String sql = "INSERT INTO products (name, description, price, category) VALUES (?, ?, ?, ?)";

        try {
            // Execute the SQL query with the product parameters and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, 
                    product.getName(), 
                    product.getDescription(), 
                    product.getPrice(), 
                    product.getCategory());

            // Return true if the product was created successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the product was not created successfully
        return false;
    }
    
    /**
     * Updates an existing product in the database.
     * 
     * @param product The product to update.
     * @return True if the product was updated successfully, false otherwise.
     */
    @Override
    public boolean update(ProductModel product) {
        // SQL query to update an existing product
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, category = ? WHERE id = ?";

        try {
            // Execute the SQL query with the product parameters and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, 
                    product.getName(), 
                    product.getDescription(), 
                    product.getPrice(), 
                    product.getCategory(),
                    product.getId());

            // Return true if the product was updated successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the product was not updated successfully
        return false;
    }

    /**
     * Deletes a product from the database.
     * 
     * @param product The product to delete.
     * @return True if the product was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(ProductModel product) {
        // SQL query to delete a product
        String sql = "DELETE FROM products WHERE id = ?";

        try {
            // Execute the SQL query with the product ID parameter and store the result in a variable
            int rows = jdbcTemplateObject.update(sql, product.getId());

            // Return true if the product was deleted successfully (i.e., one row was affected)
            return rows == 1;
        } catch (Exception e) {
            // Print any exceptions that occur during execution
            e.printStackTrace();
        }

        // Return false if an exception occurred or the product was not deleted successfully
        return false;
    }
}
