package com.example.toysproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the OpenAPI 3.0 specification.
 * It defines the basic information and security schemes for the API documentation.
 */
@Configuration
@OpenAPIDefinition(
        // Provides metadata about the API, like its title.
        info = @Info(title = "My Toy Store")
)
@SecurityScheme(
        // Name of the security scheme.
        name = "Bearer Authentication",

        // Indicates the type of security scheme.
        type = SecuritySchemeType.HTTP,

        // Expected format for the security bearer token.
        bearerFormat = "JWT",

        // HTTP authentication scheme.
        scheme = "bearer"
)
public class OpenApi3Config {
    // This class remains empty. It's used to hold the annotations for OpenAPI configuration.
}
