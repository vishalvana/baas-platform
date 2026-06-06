package com.vishal.baas_platform.service;

import com.vishal.baas_platform.dto.email.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(
            SendEmailRequest request
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(request.getTo());

        message.setSubject(
                request.getSubject()
        );

        message.setText(
                request.getBody()
        );

        mailSender.send(message);
    }
}