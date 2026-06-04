package com.vishal.baas_platform.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectStatsResponse {

    private long totalCollections;

    private long totalDocuments;

    private long totalAppUsers;
}