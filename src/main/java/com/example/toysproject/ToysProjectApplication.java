package com.example.toysproject;

import com.example.toysproject.config.ToyJWTConfig;
import com.example.toysproject.dataBaseUsers.Role;
import com.example.toysproject.dataBaseUsers.RoleRepository;
import com.example.toysproject.dataBaseUsers.User;
import com.example.toysproject.dataBaseUsers.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(ToyJWTConfig.class)
public class ToysProjectApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ToysProjectApplication.class, args);
    }
    @Override
    public void run(String... args) {
        if (userRepository.findAll().size() == 0) {
            var role_user = Role.builder().name("ROLE_USER").build();
            var role_admin = Role.builder().name("ROLE_ADMIN").build();
            var roleUserSaved = roleRepository.save(role_user);
            var roleAdminSaved = roleRepository.save(role_admin);
            var admin = User.builder().username("admin").roles(Set.of(roleAdminSaved)).email("admin@gmail.com").password(passwordEncoder.encode("admin")).build();
            var me = User.builder().username("yosef").roles(Set.of(roleAdminSaved)).email("yosef@outlook.com").password(passwordEncoder.encode("yosef")).build();
            var user = User.builder().username("user").roles(Set.of(roleUserSaved)).email("user@gmail.com").password(passwordEncoder.encode("user")).build();
            userRepository.saveAll(Set.of(admin, me, user));
        }
    }

}


