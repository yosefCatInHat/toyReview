package com.example.toysproject.dto.SignUpDto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the response after user registration.
 */
@Data
@Builder
public class UserRegistrationResponseDto {

    // The registered user's email
    private String email;

    // The registered user's username
    private String username;

    // Message providing the status or result of the registration
    private String massage;
}
