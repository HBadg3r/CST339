/**
 * Provides a contract for product-related functionality.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.service;

import java.util.List;

import com.gcu.model.ProductModel;

/**
 * ProductServiceInterface defines the methods that must be implemented by any class that provides product-related functionality.
 */
public interface ProductServiceInterface {

    /**
     * Test method to verify the ProductService is working correctly.
     */
    public void test();
    
    /**
     * Returns a list of all products in the system.
     * 
     * @return A list of all products in the system.
     */
    public List<ProductModel> getProductItems();
    
    /**
     * Creates a new product in the system.
     * 
     * @param product The product to create.
     */
    public void updateProducts(ProductModel product);
    
    /**
     * Updates an existing product in the system.
     * 
     * @param product The product to update.
     */
    public void update(ProductModel product);
    
    /**
     * Deletes a product from the system.
     * 
     * @param product The product to delete.
     */
    public void delete(ProductModel product);
    
    /**
     * Initializes the ProductService.
     */
    public void init();
    
    /**
     * Destroys the ProductService.
     */
    public void destroy();
}