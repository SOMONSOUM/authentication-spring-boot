package com.dptsi.authentication.auth.service;

import com.dptsi.authentication.auth.dto.request.LoginRequest;
import com.dptsi.authentication.auth.dto.response.LoginResponse;

public interface AuthService {
    public LoginResponse login(LoginRequest request);
}
