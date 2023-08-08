package com.example.toysproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object (DTO) representing a toy review.
 * This DTO provides details about the reviewer's name and the content of the review.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ToyReviewDto {

    // Unique identifier for the toy review
    private Long id;

    // Name of the reviewer
    @NotNull
    @NotEmpty(message = "Name must not be empty")
    private String name;

    // Content of the review
    @NotNull
    @Size(min = 2, message = "body must contain at least 2 characters")
    private String body;
}
