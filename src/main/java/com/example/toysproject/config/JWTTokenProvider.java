package com.example.toysproject.config;

import com.example.toysproject.error.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Provides functionality for JWT (JSON Web Token) generation, validation, and extraction.
 */
@Component
public class JWTTokenProvider {

    // Duration after which the token expires.
    @Value("${toy.expiration}")
    private String expires;

    // Secret key used for signing the JWT.
    @Value("${toy.secret}")
    private String secret;

    // Decoded secret key.
    private static Key mSecret;

    /**
     * Initializes the decoded secret key.
     */
    @PostConstruct
    private void init() {
        mSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * Generates a JWT for a given username.
     *
     * @param username - The username for which the JWT is generated.
     * @return A JWT string.
     */
    public String generateTokenForUsername(String username) {
        var currentDate = new Date();
        var expiresDate = new Date(currentDate.getTime() + Long.parseLong(expires));

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiresDate)
                .setIssuedAt(currentDate)
                .signWith(mSecret)
                .compact();
    }

    /**
     * Validates the provided JWT.
     *
     * @param jwt - The JWT to be validated.
     * @return true if the JWT is valid, otherwise throws BadRequestException.
     */
    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(mSecret)
                    .build()
                    .parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            throw new BadRequestException("Token", "Expired");
        } catch (MalformedJwtException e) {
            throw new BadRequestException("Token", "Invalid");
        } catch (JwtException e) {
            throw new BadRequestException("Token", "Exception: " + e.getMessage());
        }
        return true;
    }

    /**
     * Extracts the username from the given JWT.
     *
     * @param jwt - The JWT from which the username is extracted.
     * @return The extracted username.
     */
    public String getUsernameFromToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(mSecret)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
