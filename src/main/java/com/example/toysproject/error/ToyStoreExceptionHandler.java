package com.example.toysproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for the Toy Store application.
 * This class centralizes the logic for handling exceptions and transforming them into appropriate HTTP responses.
 */
@ControllerAdvice
public class ToyStoreExceptionHandler {

    /**
     * Handles exceptions when a requested resource is not found.
     *
     * @param exc     the ResourceNotFound exception
     * @param request the current web request
     * @return a ProblemDetail object with details about the exception
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFound exc, WebRequest request) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exc.getMessage());
        problemDetails.setProperty("timestamp", LocalDateTime.now());
        problemDetails.setProperty("field-name", exc.getFieldName());
        problemDetails.setProperty("field-value", exc.getFieldValue());
        return problemDetails;
    }

    /**
     * Handles exceptions caused by invalid method arguments, typically from validation errors.
     *
     * @param exc     the MethodArgumentNotValidException exception
     * @param request the current web request
     * @return a ProblemDetail object with details about the exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exc, WebRequest request) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        problemDetails.setProperty("timestamp", LocalDateTime.now());

        for (FieldError fieldError : exc.getFieldErrors()) {
            problemDetails.setProperty("Field name: " + fieldError.getField(), fieldError.getDefaultMessage());
            problemDetails.setProperty("rejected value for " + fieldError.getField(), fieldError.getRejectedValue());
        }

        return problemDetails;
    }

    /**
     * General exception handler for unspecified exceptions.
     * This is intended to catch all exceptions that are not caught by other specific handlers.
     *
     * @param exc     the caught exception
     * @param request the current web request
     * @return a ProblemDetail object with details about the exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exc, WebRequest request) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
        problemDetails.setProperty("timestamp", LocalDateTime.now());
        return problemDetails;
    }
}
