package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.auth.SendOtpRequest;
import com.vishal.baas_platform.dto.auth.VerifyOtpRequest;
import com.vishal.baas_platform.service.OtpService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send-otp")
    public ApiResponse<String> sendOtp(
            @Valid @RequestBody SendOtpRequest request
    ) {

        otpService.sendOtp(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("OTP sent successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data("OTP sent")
                .build();
    }

    @PostMapping("/verify-otp")
    public ApiResponse<String> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request
    ) {

        otpService.verifyOtp(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("OTP verified successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data("Verified")
                .build();
    }
}