/**
 * Provides product-related functionality.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.ProductModel;

/**
 * ProductService is a Spring-based service class that provides product-related functionality.
 * It uses a DataAccessInterface to interact with the data storage system.
 */
public class ProductService implements ProductServiceInterface {

    /**
     * Data access object for product data.
     */
    @Autowired
    private DataAccessInterface<ProductModel> service;
    
    /**
     * Test method to verify the ProductService is working correctly.
     */
    @Override
    public void test() {
        System.out.println("Hello from the ProductService");
    }

    /**
     * Creates a new product in the system.
     * 
     * @param product The product to create.
     */
    @Override
    public void updateProducts(ProductModel product) {
        service.create(product);
    }
    
    /**
     * Updates an existing product in the system.
     * 
     * @param product The product to update.
     */
    @Override
    public void update(ProductModel product) {
        service.update(product);
    }

    /**
     * Deletes a product from the system.
     * 
     * @param product The product to delete.
     */
    @Override
    public void delete(ProductModel product) {
        service.delete(product);
    }
    
    /**
     * Returns a list of all products in the system.
     * 
     * @return A list of all products in the system.
     */
    @Override
    public List<ProductModel> getProductItems() {
        List<ProductModel> products = new ArrayList<ProductModel>();
        products = service.findAll();
        return products;
    }

    /**
     * Initializes the ProductService.
     */
    @Override
    public void init() {
        System.out.println("ProductService init");
    }

    /**
     * Destroys the ProductService.
     */
    @Override
    public void destroy() {
        System.out.println("ProductService destroy");
    }

    /**
     * Finds a product by its ID.
     * 
     * @param id The ID of the product to find.
     * @return The product with the specified ID, or null if not found.
     */
    public ProductModel findById(int id) {
        ProductModel product = service.findById(id);
        return product;
    }
}