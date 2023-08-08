package com.example.toysproject.dto.SignUpDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the input data for user registration.
 * This DTO captures the user's email, username, and password during the sign-up process.
 */
@Data
@Builder
public class SignupDto {

    /**
     * Email address of the user.
     * This field is mandatory and should be in a valid email format.
     */
    @Email
    @NotNull
    private String email;

    /**
     * Username chosen by the user.
     * This field is mandatory and must be at least 2 characters long.
     */
    @NotNull
    @Size(min = 2)
    private String username;

    /**
     * Password chosen by the user for authentication.
     * This field is mandatory and must be at least 2 characters long.
     */
    @NotNull
    @Size(min = 2)
    private String password;
}
