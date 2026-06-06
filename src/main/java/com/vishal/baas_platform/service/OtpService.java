package com.vishal.baas_platform.service;

import com.vishal.baas_platform.dto.auth.SendOtpRequest;
import com.vishal.baas_platform.dto.auth.VerifyOtpRequest;
import com.vishal.baas_platform.entity.OtpVerification;
import com.vishal.baas_platform.exception.CustomException;
import com.vishal.baas_platform.repository.OtpVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpVerificationRepository otpRepository;
    private final EmailService emailService;

    public void sendOtp(SendOtpRequest request) {

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000)
        );

        OtpVerification verification =
                OtpVerification.builder()
                        .email(request.getEmail())
                        .otp(otp)
                        .verified(false)
                        .createdAt(LocalDateTime.now())
                        .expiresAt(
                                LocalDateTime.now().plusMinutes(5)
                        )
                        .build();

        otpRepository.save(verification);

        emailService.sendEmail(
                request.getEmail(),
                "BaaS Platform OTP Verification",
                "Your OTP is: " + otp
        );
    }

    public void verifyOtp(
            VerifyOtpRequest request
    ) {

        OtpVerification otp =
                otpRepository
                        .findTopByEmailOrderByCreatedAtDesc(
                                request.getEmail()
                        )
                        .orElseThrow(() ->
                                new CustomException(
                                        "OTP not found"
                                ));

        if (otp.isVerified()) {
            throw new CustomException(
                    "OTP already used"
            );
        }

        if (!otp.getOtp().equals(
                request.getOtp()
        )) {

            throw new CustomException(
                    "Invalid OTP"
            );
        }

        if (otp.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            throw new CustomException(
                    "OTP expired"
            );
        }

        otp.setVerified(true);

        otpRepository.save(otp);
    }
}