package com.example.toysproject.controller;

import com.example.toysproject.config.JWTTokenProvider;
import com.example.toysproject.dto.SignUpDto.SignInDto;
import com.example.toysproject.dto.SignUpDto.SignupDto;
import com.example.toysproject.dto.SignUpDto.UserRegistrationResponseDto;
import com.example.toysproject.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    // Dependencies injected via constructor
    private final CustomUserDetailsService userService;
    private final AuthenticationManager authManager;
    private final JWTTokenProvider tokenProvider;

    /**
     * Endpoint to sign in a user and return a JWT token.
     */
    @Operation(summary = "Sign in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User sign in successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Sign in not found",
                    content = @Content)
    })
    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        var authentication = authManager.authenticate(authToken);
        var jwt = tokenProvider.generateTokenForUsername(dto.getUsername());
        return ResponseEntity.ok(Map.of("message", "Sign in successful", "token", jwt));
    }

    /**
     * Endpoint to sign up a new user.
     */
    @Operation(summary = "Sign up a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User sign up successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignupDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Sign up not found",
                    content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody @Valid SignupDto dto) {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }
}
