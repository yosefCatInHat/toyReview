package com.example.toysproject.dataBaseUsers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity CRUD operations and custom queries.
 * This interface extends Spring Data JPA's JpaRepository to provide CRUD operations for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user based on a case-insensitive match for either username or email.
     *
     * @param username the username to search for.
     * @param email    the email to search for.
     * @return an Optional containing the matched User or empty if not found.
     */
    Optional<User> findUserByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);

    /**
     * Find a user based on a case-insensitive match for username.
     *
     * @param username the username to search for.
     * @return an Optional containing the matched User or empty if not found.
     */
    Optional<User> findUserByUsernameIgnoreCase(String username);

    /**
     * Find a user based on a case-insensitive match for email.
     *
     * @param email the email to search for.
     * @return an Optional containing the matched User or empty if not found.
     */
    Optional<User> findUserByEmailIgnoreCase(String email);

    /**
     * Check if a user exists based on a case-insensitive match for username.
     *
     * @param username the username to check.
     * @return true if the user exists, otherwise false.
     */
    Boolean existsUserByUsernameIgnoreCase(String username);

    /**
     * Check if a user exists based on a case-insensitive match for email.
     *
     * @param email the email to check.
     * @return true if the user exists, otherwise false.
     */
    Boolean existsUserByEmailIgnoreCase(String email);
}
