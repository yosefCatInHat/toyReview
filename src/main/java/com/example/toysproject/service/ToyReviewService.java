package com.example.toysproject.service;

import com.example.toysproject.dto.ToyReviewDto;

import java.util.List;

/**
 * ToyReviewService is an interface that defines the contract for managing toy reviews.
 * It declares several methods to perform CRUD operations related to toy reviews.
 */
public interface ToyReviewService {

    /**
     * Create a new toy review for a specific toy.
     *
     * @param toyId The ID of the toy for which the review is being created.
     * @param dto   The ToyReviewDto containing the details of the review.
     * @return The created ToyReviewDto.
     */
    ToyReviewDto createToyReview(Long toyId, ToyReviewDto dto);

    /**
     * Retrieve all toy reviews associated with a specific toy.
     *
     * @param toyId The ID of the toy for which to fetch reviews.
     * @return A list of ToyReviewDto representing the toy reviews.
     */
    List<ToyReviewDto> findToyReviewByToyId(long toyId);

    /**
     * Retrieve a specific toy review by its ID.
     *
     * @param toyReviewId The ID of the toy review to retrieve.
     * @return The ToyReviewDto representing the toy review.
     */
    ToyReviewDto findToyReviewById(long toyReviewId);

    /**
     * Update an existing toy review with new information.
     *
     * @param id             The ID of the toy review to update.
     * @param toyReviewDto   The updated ToyReviewDto containing new review details.
     * @return The updated ToyReviewDto.
     */
    ToyReviewDto updateToyReviewById(long id, ToyReviewDto toyReviewDto);

    /**
     * Delete a specific toy review by its ID.
     *
     * @param id The ID of the toy review to delete.
     * @return The ToyReviewDto representing the deleted toy review.
     */
    ToyReviewDto deleteToyReviewById(long id);
}
