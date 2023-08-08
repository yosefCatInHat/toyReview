package com.example.toysproject.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a review associated with a specific toy.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class ToyReview {

    // Unique identifier for the review
    @Id
    @GeneratedValue
    private Long id;

    // Name or title of the review
    private String name;

    // Detailed content or body of the review
    private String body;

    // Associated toy with this review
    @ToString.Exclude  // Excludes the associated toy details in toString to prevent recursive loop
    @ManyToOne // Associating with the toy entity (Many reviews can belong to one toy)
    @JoinColumn(name = "toys_id", nullable = false) // Foreign key column in the database
    private Toys toys;
}
