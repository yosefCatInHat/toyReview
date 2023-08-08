package com.example.toysproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object (DTO) for a Toy entity.
 * This DTO is designed to transfer toy details between the service and client layers.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToyDto {

    // Unique identifier of the toy
    private Long id;

    // Name of the toy with a minimum required length of 2 characters
    @NotNull
    @Size(min = 2, message = "Title must contain at least 2 characters")
    private String toyName;

    // Description of the toy with a minimum required length of 3 characters
    @NotNull
    @Size(min = 3, message = "Description must contain at least 3 characters")
    private String toyDescription;

    // The toyDate
    @NotNull
    private String toyDate;

    // Image representation of the toy
    @NotNull
    private String toyImage;
}
