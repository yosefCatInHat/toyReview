package com.example.toysproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for JWT properties sourced from application properties.
 *
 * <p>
 * Properties are prefixed with "blog" in the application properties.
 * </p>
 */
@Configuration
@ConfigurationProperties(prefix = "blog")
public class ToyJWTConfig {

    // Secret key for JWT signing and verification.
    private String secret;

    // Expiration time for the JWT token.
    private Long expiration;

    /**
     * Default constructor.
     */
    public ToyJWTConfig() {}

    /**
     * Parameterized constructor.
     *
     * @param secret The secret key for JWT.
     * @param expiration The expiration time for JWT.
     */
    public ToyJWTConfig(String secret, Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    /**
     * Retrieves the JWT secret key.
     *
     * @return The JWT secret key.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Sets the JWT secret key.
     *
     * @param secret The JWT secret key to set.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Retrieves the JWT token expiration time.
     *
     * @return The JWT token expiration time.
     */
    public Long getExpiration() {
        return expiration;
    }

    /**
     * Sets the JWT token expiration time.
     *
     * @param expiration The JWT token expiration time to set.
     */
    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
