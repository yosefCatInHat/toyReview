package com.example.toysproject.config;

// Importing Spring's configuration and bean annotations
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Importing Spring Security's password encoder classes
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AppConfig class is a configuration class that provides application-specific beans.
 */
@Configuration
public class AppConfig {

    /**
     * Provides a password encoder bean to be used throughout the application.
     *
     * The 'createDelegatingPasswordEncoder' factory method creates a delegating password
     * encoder that supports multiple encoding formats. It's recommended for modern applications
     * to handle encoding in a more flexible way.
     *
     * @return PasswordEncoder - An instance of PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
