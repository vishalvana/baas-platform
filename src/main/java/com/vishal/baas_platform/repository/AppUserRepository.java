package com.vishal.baas_platform.repository;

import com.vishal.baas_platform.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository
        extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByProjectIdAndEmail(
            UUID projectId,
            String email
    );

    boolean existsByProjectIdAndEmail(
            UUID projectId,
            String email
    );
}