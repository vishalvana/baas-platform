package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.auth.SignupRequest;
import com.vishal.baas_platform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }
}