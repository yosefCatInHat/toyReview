package com.example.toysproject.dataBaseUsers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

/**
 * Represents a user's role in the system.
 * This entity is used to manage different permissions and access levels.
 */
@Entity                   // Indicates that this is a JPA entity.
@Getter                   // Lombok annotation to generate getter methods.
@Setter                   // Lombok annotation to generate setter methods.
@ToString                 // Lombok annotation to generate toString method.
@Builder                  // Lombok annotation to provide a builder pattern for object creation.
@AllArgsConstructor     // Lombok annotation to generate a constructor with all arguments.
@NoArgsConstructor      // Lombok annotation to generate a no-argument constructor.
public class Role {

    @Id                   // Specifies the primary key.
    @GeneratedValue       // Indicates that the ID should be generated.
    private Long id;

    @NonNull              // Lombok annotation to ensure this field is not null.
    @Size(min = 2)        // Validation constraint indicating size must be at least 2 characters.
    private String name;  // Represents role names e.g. ADMIN/USER/LIBRARIAN/WRITER/REPORTER.

    /**
     * Many-to-Many association to represent the user-role relationship.
     * Uses an intermediate table "user_roles" to map users to roles.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
