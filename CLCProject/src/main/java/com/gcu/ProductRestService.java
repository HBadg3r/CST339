/**
 * A RESTful service for managing products.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

/**
 * Marks this class as a controller where every method returns a domain object instead of a view.
 * 
 * @see RestController
 */
@RestController
public class ProductRestService {

    /**
     * The product service instance, injected by Spring.
     */
    @Autowired
    private ProductService service;

    /**
     * Constructor for the product REST service.
     * 
     * @param service The product service instance.
     */
    public ProductRestService(ProductService service) {
        // No-op constructor, primarily used for testing purposes.
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param id The ID of the product to retrieve.
     * @return A ResponseEntity containing the product, or an error status if not found.
     */
    public ResponseEntity<?> getProductById(int id) {
        try {
            // Attempt to find the product by its ID.
            ProductModel product = service.findById(id);
            if (product == null) {
                // If the product is not found, return a 404 status.
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // If the product is found, return it with a 200 status.
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        } catch (Exception e) {
            // If an error occurs, return a 500 status.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}