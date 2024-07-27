/**
 * This interface defines the data access methods for a generic data type T.
 * It provides basic CRUD (Create, Read, Update, Delete) operations.
 * 
 * @author Easten, Chance, Nathan, Kamren
 * @version 1.0
 * @since 1.0
 * @param <T> The type of data being accessed.
 */
package com.gcu.data;

import java.util.List;

/**
 * Marks this interface as a data access interface.
 * 
 * @see DataAccessInterface
 */
public interface DataAccessInterface<T> {

    /**
     * Retrieves all records of type T from the data source.
     * 
     * @return A list of all records of type T.
     */
    public List<T> findAll();

    /**
     * Retrieves a single record of type T from the data source by its ID.
     * 
     * @param ID The ID of the record to retrieve.
     * @return The record of type T with the specified ID, or null if not found.
     */
    public T findById(int ID);

    /**
     * Creates a new record of type T in the data source.
     * 
     * @param t The record to create.
     * @return True if the record was created successfully, false otherwise.
     */
    public boolean create(T t);

    /**
     * Updates an existing record of type T in the data source.
     * 
     * @param t The record to update.
     * @return True if the record was updated successfully, false otherwise.
     */
    public boolean update(T t);

    /**
     * Deletes a record of type T from the data source.
     * 
     * @param t The record to delete.
     * @return True if the record was deleted successfully, false otherwise.
     */
    public boolean delete(T t);
}