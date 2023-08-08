package com.example.toysproject.repository;

import com.example.toysproject.entity.Toys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for performing CRUD operations on Toys entities.
 * It provides methods for interacting with the database related to Toys.
 */
@Repository
public interface ToyRepository extends JpaRepository<Toys, Long> {
}
