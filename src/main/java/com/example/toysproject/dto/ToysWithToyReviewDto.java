package com.example.toysproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Data Transfer Object (DTO) representing details of a toy
 * along with its associated reviews.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToysWithToyReviewDto {

    // Unique identifier for the toy
    private Long id;

    // Name of the toy
    @NotNull
    private String toyName;

    // Description about the toy
    @NotNull
    private String toyDescription;

    // toyDate
    @NotNull
    private String toyDate;

    // Image related to the toy
    @NotNull
    private String toyImage;

    // Collection of reviews associated with the toy
    @Builder.Default  // When using a builder, it will create a new hashset for reviews
    private Set<ToyReviewDto> toyReview = new HashSet<>();
}
