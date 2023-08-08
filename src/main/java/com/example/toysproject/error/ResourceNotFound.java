package com.example.toysproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling resource not found scenarios.
 * When this exception is thrown, it automatically returns a HTTP 404 NOT FOUND status.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFound extends RuntimeException {

    // Name of the resource that couldn't be found, e.g., "User" or "Toy"
    private final String resourceName;

    // Field or attribute name of the resource, e.g., "id" or "username"
    private final String fieldName;

    // The value of the field that was being searched for
    private final String fieldValue;

    /**
     * Constructs a new exception with a message indicating the resource, field, and value that was not found.
     *
     * @param resourceName The name of the resource causing the exception.
     * @param fieldName    The field/attribute name of the resource.
     * @param fieldValue   The value of the field that was being searched for.
     */
    public ResourceNotFound(String resourceName, String fieldName, Object fieldValue) {
        super("%s not found with %s:%s".formatted(resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue.toString();
    }
}
