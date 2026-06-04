package com.vishal.baas_platform.repository;

import com.vishal.baas_platform.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository
        extends JpaRepository<AppUser, UUID> {
    long countByProjectIdIn(List<UUID> projectIds);
    long countByProjectId(UUID projectId);

    Optional<AppUser> findByProjectIdAndEmail(
            UUID projectId,
            String email
    );

    boolean existsByProjectIdAndEmail(
            UUID projectId,
            String email
    );
}