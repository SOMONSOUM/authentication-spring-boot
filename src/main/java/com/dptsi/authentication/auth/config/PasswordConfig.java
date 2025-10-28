package com.dptsi.authentication.auth.config;

import com.dptsi.authentication.user.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 256, 1);
    }

    public boolean passwordMatches(String password, Optional<User> encodedPassword) {
        return passwordEncoder().matches(password, encodedPassword.get().getPassword());
    }
}