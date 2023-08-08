package com.example.toysproject.service;

import com.example.toysproject.dataBaseUsers.Role;
import com.example.toysproject.dataBaseUsers.RoleRepository;
import com.example.toysproject.dataBaseUsers.UserRepository;
import com.example.toysproject.dto.SignUpDto.SignupDto;
import com.example.toysproject.dto.SignUpDto.UserRegistrationResponseDto;
import com.example.toysproject.error.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * CustomUserDetailsService is a service class responsible for handling user authentication and registration.
 * It implements the UserDetailsService interface provided by Spring Security, used for loading user-specific data
 * during the authentication process. Additionally, it provides a method for registering new users and validating their credentials.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        // Map our Roles to Spring Security Domain Roles.
        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
        // Spring security User:
        return new User(user.getUsername(), user.getPassword(), roles);
    }

    @Transactional
    public UserRegistrationResponseDto registerUser(SignupDto dto) {
        Role role = roleRepository.findByNameIgnoreCase("ROLE_USER").orElseThrow();
        var byEmail = userRepository.findUserByEmailIgnoreCase(dto.getEmail());
        var byUsername = userRepository.findUserByUsernameIgnoreCase(dto.getUsername());
        if (byEmail.isPresent()) {
            throw new BadRequestException("email", "email already exists");
        } else if (byUsername.isPresent()) {
            throw new BadRequestException("username", "username already exists");
        }
        var user = com.example.toysproject.dataBaseUsers.User
                .builder()
                .username(dto.getUsername().trim())
                .email(dto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(dto.getPassword().trim()))
                .roles(Set.of(role))
                .build();
        var saved = userRepository.save(user);

        return UserRegistrationResponseDto.builder()
                .email(saved.getEmail())
                .username(saved.getUsername())
                .massage("Sign up successfully")
                .build();
    }
}
