package com.example.toysproject.service;

import com.example.toysproject.dto.ToyDto;
import com.example.toysproject.dto.ToyPageDto;

import java.util.List;

/**
 * ToyService is an interface that defines the contract for managing toys in the application.
 */
public interface ToyService {

    /**
     * Create a new toy.
     *
     * @param dto The ToyDto containing the details of the toy to be created.
     * @return The ToyDto of the newly created toy.
     */
    ToyDto createToy(ToyDto dto);

    /**
     * Get a list of all toys in the system.
     *
     * @return A list of ToyDto representing all the toys.
     */
    List<ToyDto> getAllToys();

    /**
     * Get a specific toy by its ID.
     *
     * @param id The ID of the toy to be retrieved.
     * @return The ToyDto representing the toy with the specified ID.
     */
    ToyDto getToyById(long id);

    /**
     * Update an existing toy with new information.
     *
     * @param id  The ID of the toy to be updated.
     * @param dto The ToyDto containing the updated information.
     * @return The ToyDto representing the updated toy.
     */
    ToyDto updateToyById(long id, ToyDto dto);

    /**
     * Delete a toy by its ID.
     *
     * @param id The ID of the toy to be deleted.
     */
    void deleteToyById(long id);

    /**
     * Get a paginated list of toys with sorting options.
     *
     * @param pageNo   The page number of the result.
     * @param pageSize The number of items per page.
     * @param sortBy   The field to sort the results by.
     * @param sortDir  The sorting direction (ASC or DESC).
     * @return A ToyPageDto representing the paginated list of toys.
     */
    ToyPageDto getAllToys(int pageNo, int pageSize, String sortBy, String sortDir);
}
