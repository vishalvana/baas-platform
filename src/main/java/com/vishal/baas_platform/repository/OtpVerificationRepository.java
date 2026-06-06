package com.vishal.baas_platform.repository;

import com.vishal.baas_platform.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpVerificationRepository
        extends JpaRepository<OtpVerification, UUID> {

    Optional<OtpVerification> findTopByEmailOrderByCreatedAtDesc(
            String email
    );
}