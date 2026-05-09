package com.vishal.baas_platform.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}