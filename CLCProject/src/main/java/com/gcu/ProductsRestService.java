/**
 * A RESTful service for managing a collection of products.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 */
package com.gcu;

import java.util.List;

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
public class ProductsRestService {

    /**
     * The product service instance, injected by Spring.
     */
    @Autowired
    private ProductService service;

    /**
     * Constructor for the products REST service.
     * 
     * @param service The product service instance.
     */
    public ProductsRestService(ProductService service) {
        // Initialize the service instance.
        this.service = service;
    }

    /**
     * Retrieves a list of all products.
     * 
     * @return A ResponseEntity containing the list of products, or an error status if not found.
     */
    public ResponseEntity<?> getProducts() {
        try {
            // Attempt to retrieve the list of products.
            List<ProductModel> products = service.getProductItems();
            if (products.isEmpty()) {
                // If the list is empty, return a 404 status.
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // If the list is not empty, return it with a 200 status.
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            // If an error occurs, return a 500 status.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}