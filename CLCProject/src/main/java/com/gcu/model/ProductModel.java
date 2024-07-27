/**
 * Represents a product with its properties and behaviors.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 */
package com.gcu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * ProductModel is a Java class that encapsulates the properties and behaviors of a product.
 * It includes fields for product ID, name, description, price, and category, along with their respective getter and setter methods.
 */
public class ProductModel {

    /**
     * Unique identifier for the product.
     */
    @NotNull(message = "ID is required")
    @Min(value = 1, message = "ID must be a positive integer")
    private int id;

    /**
     * Name of the product.
     */
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    /**
     * Description of the product.
     */
    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 200, message = "Description must be between 2 and 200 characters")
    private String description;

    /**
     * Price of the product.
     */
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a non-negative number")
    private double price;

    /**
     * Category of the product.
     */
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;

    /**
     * Constructs a new ProductModel object with the specified properties.
     * 
     * @param id          Unique identifier for the product.
     * @param name        Name of the product.
     * @param description Description of the product.
     * @param price       Price of the product.
     * @param category    Category of the product.
     */
    public ProductModel(int id, String name, String description, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    /**
     * Default constructor for ProductModel.
     */
    public ProductModel() {
        // No-arg constructor for bean creation
    }

    // Getters and Setters

    /**
     * Returns the unique identifier for the product.
     * 
     * @return Product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the product.
     * 
     * @param id Product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the product.
     * 
     * @return Product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name Product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the product.
     * 
     * @return Product description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     * 
     * @param description Product description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of the product.
     * 
     * @return Product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price Product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the category of the product.
     * 
     * @return Product category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     * 
     * @param category Product category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    // toString method for easy printing

    /**
     * Returns a string representation of the ProductModel object.
     * 
     * @return String representation of the ProductModel object.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}