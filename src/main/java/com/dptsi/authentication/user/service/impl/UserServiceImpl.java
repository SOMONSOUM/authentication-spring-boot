package com.dptsi.authentication.user.service.impl;

import com.dptsi.authentication.user.dto.request.RegisterUserRequest;
import com.dptsi.authentication.user.exception.UserAlreadyExistsException;
import com.dptsi.authentication.user.model.User;
import com.dptsi.authentication.user.repository.UserRepository;
import com.dptsi.authentication.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 256, 1);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}