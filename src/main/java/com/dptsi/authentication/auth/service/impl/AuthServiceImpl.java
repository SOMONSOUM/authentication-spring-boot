package com.dptsi.authentication.auth.service.impl;

import com.dptsi.authentication.auth.config.PasswordConfig;
import com.dptsi.authentication.auth.dto.request.LoginRequest;
import com.dptsi.authentication.auth.dto.response.LoginResponse;
import com.dptsi.authentication.auth.exception.EmailDoesNotExistException;
import com.dptsi.authentication.auth.exception.InvalidPasswordException;
import com.dptsi.authentication.auth.security.JwtService;
import com.dptsi.authentication.auth.service.AuthService;
import com.dptsi.authentication.user.repository.UserRepository;

public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;


    public AuthServiceImpl(JwtService jwtService, PasswordConfig passwordConfig, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if(!userRepository.existsByEmail(request.getEmail())) {
            throw new EmailDoesNotExistException("User with email " + request.getEmail() + " does not exist");
        }

        if(!passwordConfig.passwordMatches(request.getPassword(), userRepository.findByEmail(request.getEmail()))) {
            throw new InvalidPasswordException("Invalid password");
        }

        var user = userRepository.findByEmail(request.getEmail());

        String accessToken = jwtService.generateAccessToken(user.get().getId().toString());
        String refreshToken = jwtService.generateRefreshToken(user.get().getId().toString());
        return new LoginResponse(accessToken, refreshToken);
    }
}
