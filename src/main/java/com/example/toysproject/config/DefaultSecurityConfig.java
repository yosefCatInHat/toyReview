package com.example.toysproject.config;

// Framework and Security imports
import com.example.toysproject.security.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// Java utility imports
import java.util.List;

/**
 * Configuration class responsible for setting up default security configurations.
 * This includes JWT-based authentication, CORS settings, and URL-based permissions.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class DefaultSecurityConfig {

    // Injected JWT authentication filter for request processing.
    private final JWTAuthenticationFilter filter;

    /**
     * Constructor to set up JWT authentication filter.
     *
     * @param filter - Filter responsible for JWT authentication processing.
     */
    public DefaultSecurityConfig(JWTAuthenticationFilter filter) {
        this.filter = filter;
    }

    /**
     * Bean declaration for the authentication manager, which is responsible
     * for authentication procedures in the application.
     *
     * @param configuration - Spring security authentication configuration.
     * @return The authentication manager from the provided configuration.
     * @throws Exception if unable to get the authentication manager.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings.
     *
     * This setup allows for the application to be accessed by client-side web applications
     * that might be running on different origins.
     *
     * @return CORS configuration source setup.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Sets up the main security configurations for the application.
     *
     * This includes setting up the filter chain, CSRF protection (which is disabled),
     * route-based authentication rules, and more.
     *
     * @param http - HttpSecurity object to configure.
     * @return Configured filter chain.
     * @throws Exception if configuration setup fails.
     */
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .addFilterBefore(filter, BasicAuthenticationFilter.class)
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/auth/**").permitAll();  // Open endpoints for authentication
                    auth.requestMatchers("/api/v1/**").authenticated();   // Secured endpoints requiring authentication
                    auth.anyRequest().permitAll();                        // Default: allow all other requests
                })
                .httpBasic(basic -> basic.authenticationEntryPoint(new ToyAuthenticationEntryPoint()))
                .build();
    }
}
