package com.example.toysproject.dto.SignUpDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the input data for user sign-in operations.
 * This DTO is used to capture the username and password from a client during the sign-in process.
 */
@Data
@Builder
public class SignInDto {

    /**
     * Username of the user.
     * This field is mandatory and must be at least 2 characters long.
     */
    @NotNull
    @Size(min = 2)
    private String username;

    /**
     * Password of the user.
     * This field is mandatory and must be at least 2 characters long.
     */
    @NotNull
    @Size(min = 2)
    private String password;
}
