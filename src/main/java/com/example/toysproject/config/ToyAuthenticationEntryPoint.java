package com.example.toysproject.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Custom AuthenticationEntryPoint implementation for handling authentication failures.
 *
 * <p>
 * This class is triggered when a user tries to access a secured REST resource without supplying
 * any credentials. It returns an HTTP 401 Unauthorized error code and a clear message.
 * </p>
 *
 * <p>
 * Typically invoked by ExceptionTranslationFilter to initiate the authentication process.
 * </p>
 *
 * @author BlogProject
 */
public class ToyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Handle the authentication failure and return a 401 Unauthorized error.
     *
     * @param request The request that resulted in an AuthenticationException.
     * @param response The response.
     * @param authException The exception that was thrown to reject the authentication.
     * @throws IOException in case of I/O errors.
     * @throws ServletException in case of Servlet errors.
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized. Please refer to API docs and login.");
    }
}
