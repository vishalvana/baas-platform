package com.vishal.baas_platform.repository;

import com.vishal.baas_platform.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ApiUsageLogRepository
        extends JpaRepository<ApiUsageLog, UUID> {

    long countByProjectId(UUID projectId);

    @Query("""
    SELECT COUNT(a)
    FROM ApiUsageLog a
    WHERE a.projectId = :projectId
    AND a.createdAt >= CURRENT_DATE
""")
    long countRequestsToday(UUID projectId);
}