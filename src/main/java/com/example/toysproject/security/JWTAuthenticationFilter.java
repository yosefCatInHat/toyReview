package com.example.toysproject.security;

import com.example.toysproject.config.JWTTokenProvider;
import com.example.toysproject.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWTAuthenticationFilter is a custom Spring Security filter responsible for
 * intercepting incoming requests, extracting the JWT (JSON Web Token) from the request headers,
 * validating it, and setting the authenticated user in the Spring Security context if the token is valid.
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JWTTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Get JWT from the request
        var token = getJwtFromRequest(request);
        // Validate the token, only if it exists
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            // Get the username from the token
            var username = tokenProvider.getUsernameFromToken(token);
            // Load the user from the customUserDetailsService (DB)
            UserDetails user = userDetailsService.loadUserByUsername(username);
            var authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    null,
                    user.getAuthorities()
            );
            // No need to check the password
            // Set the user as authenticated with Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        //"Authorization": "Bearer eyJhbGciOVCJ9.eyJzdWIiOiIxJV_.adQssw5c"
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }
}
