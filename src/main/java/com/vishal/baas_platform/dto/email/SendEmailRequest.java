package com.vishal.baas_platform.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendEmailRequest {

    @Email
    private String to;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}