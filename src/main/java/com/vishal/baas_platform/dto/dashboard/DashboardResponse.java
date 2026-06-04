package com.vishal.baas_platform.dto.dashboard;

import com.vishal.baas_platform.dto.project.ProjectResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {

    private long totalProjects;

    private long totalCollections;

    private long totalDocuments;

    private long totalAppUsers;

    private List<ProjectResponse> recentProjects;
}