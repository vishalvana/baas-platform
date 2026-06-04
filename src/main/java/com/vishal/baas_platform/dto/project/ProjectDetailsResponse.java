package com.vishal.baas_platform.dto.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProjectDetailsResponse {

    private UUID id;

    private String name;

    private String apiKey;

    private LocalDateTime createdAt;
}