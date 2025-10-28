package com.dptsi.authentication.user.service;

import com.dptsi.authentication.user.dto.request.RegisterUserRequest;
import com.dptsi.authentication.user.model.User;

public interface UserService {
    User register(RegisterUserRequest request);

    User findByEmail(String email);
}
