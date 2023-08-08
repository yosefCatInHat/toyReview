package com.example.toysproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle bad request scenarios.
 * When this exception is thrown, it automatically returns a HTTP 400 BAD REQUEST status.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {

    // Resource causing the exception, e.g., "username" or "email"
    private final String resourceName;

    /**
     * Constructs a new exception with a default message.
     *
     * @param resourceName The name of the resource causing the exception.
     */
    public BadRequestException(String resourceName) {
        super("%s Must Be Valid".formatted(resourceName));
        this.resourceName = resourceName;
    }

    /**
     * Constructs a new exception with a specified detail message.
     *
     * @param resourceName The name of the resource causing the exception.
     * @param message      The detail message.
     */
    public BadRequestException(String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }
}
