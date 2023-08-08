package com.example.toysproject.repository;

import com.example.toysproject.dto.ToyReviewDto;
import com.example.toysproject.entity.ToyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for performing CRUD operations on ToyReview entities.
 * It provides methods for interacting with the database related to ToyReview.
 */
@Repository
public interface ToyReviewRepository extends JpaRepository<ToyReview, Long> {

    /**
     * Find all ToyReviews associated with a specific Toy identified by its ID.
     *
     * @param toyId The ID of the Toy to find ToyReviews for.
     * @return A list of ToyReviews associated with the specified Toy.
     */
    List<ToyReview> findToyReviewByToysId(long toyId);

    /**
     * Find a specific ToyReview identified by its ID.
     *
     * @param toyReviewId The ID of the ToyReview to find.
     * @return The ToyReview with the specified ID, or null if not found.
     */
    ToyReviewDto findToyReviewById(long toyReviewId);
}
