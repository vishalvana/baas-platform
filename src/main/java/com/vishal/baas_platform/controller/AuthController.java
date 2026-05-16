package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.auth.AuthResponse;
import com.vishal.baas_platform.dto.auth.LoginRequest;
import com.vishal.baas_platform.dto.auth.SignupRequest;
import com.vishal.baas_platform.service.AuthService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(
            @Valid @RequestBody SignupRequest request
    ) {

        String response = authService.signup(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Signup successful")
                .data(response)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        AuthResponse response = authService.login(request);

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .build();
    }
}