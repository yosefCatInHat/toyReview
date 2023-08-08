package com.example.toysproject.dataBaseUsers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Role entities.
 * This interface provides methods to interact with the underlying database for the Role entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a Role entity by its name, ignoring case considerations.
     *
     * @param name The name of the role to be fetched.
     * @return An Optional<Role> that contains the role if found, or empty if not.
     */
    Optional<Role> findByNameIgnoreCase(String name);
}
