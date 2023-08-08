package com.example.toysproject.dataBaseUsers;

import com.example.toysproject.entity.Toys;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * Entity representing a User in the system.
 * This entity contains user-specific details like username, email, password and associated roles.
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_USER_NAME", columnNames = {"username"}),
                @UniqueConstraint(name = "UQ_EMAIL", columnNames = {"email"})
        }
)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long Id;

    @NonNull
    @Size(min = 2)
    private String username;

    @NonNull
    @Email
    private String email;

    @NonNull
    @Size(min = 5)
    private String password; // Encrypted password




    /**
     * Many-to-Many association between User and Role entities.
     * Represents the roles associated with a particular user.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
