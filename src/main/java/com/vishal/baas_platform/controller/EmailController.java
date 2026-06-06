package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.email.SendEmailRequest;
import com.vishal.baas_platform.service.EmailService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ApiResponse<String> sendEmail(
            @Valid
            @RequestBody
            SendEmailRequest request
    ) {

        emailService.sendEmail(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Email sent successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data("Email sent")
                .meta(null)
                .build();
    }
}