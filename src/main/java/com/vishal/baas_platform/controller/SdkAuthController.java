package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.auth.AuthResponse;
import com.vishal.baas_platform.dto.sdk.AppUserLoginRequest;
import com.vishal.baas_platform.dto.sdk.AppUserSignupRequest;
import com.vishal.baas_platform.service.SdkAuthService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sdk/auth")
@RequiredArgsConstructor
public class SdkAuthController {

    private final SdkAuthService sdkAuthService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(
            @RequestHeader("x-api-key") String apiKey,
            @Valid @RequestBody AppUserSignupRequest request
    ) {

        String response =
                sdkAuthService.signup(apiKey, request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Signup successful")
                .data(response)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @RequestHeader("x-api-key") String apiKey,
            @Valid @RequestBody AppUserLoginRequest request
    ) {

        AuthResponse response =
                sdkAuthService.login(apiKey, request);

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .build();
    }
}