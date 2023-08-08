package com.example.toysproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing the 'Toys' table in the database.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "toys",
        uniqueConstraints = {@UniqueConstraint(columnNames = "toyName")})
public class Toys {

    // Unique identifier for the toy
    @Id
    @GeneratedValue
    private Long id;

    // Name of the toy. Ensured to be unique via constraints.
    @NotNull
    private String toyName;

    // Description of the toy
    @NotNull
    private String toyDescription;

    // toyUserId
    @NotNull
    private String toyDate;

    // Image reference/link for the toy
    @NotNull
    private String toyImage;

    // Associated reviews for the toy. Any removal of a toy will also remove its associated reviews.
    @ToString.Exclude
    @OneToMany(mappedBy = "toys",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ToyReview> toyReviews = new HashSet<>();
}
