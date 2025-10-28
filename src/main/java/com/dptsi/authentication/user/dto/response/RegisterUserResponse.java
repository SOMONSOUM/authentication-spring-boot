package com.dptsi.authentication.user.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterUserResponse {
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RegisterUserResponse(String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
