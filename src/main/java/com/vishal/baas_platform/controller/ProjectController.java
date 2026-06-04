package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.project.ProjectDetailsResponse;
import com.vishal.baas_platform.dto.project.ProjectRequest;
import com.vishal.baas_platform.dto.project.ProjectResponse;
import com.vishal.baas_platform.dto.project.ProjectStatsResponse;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.service.ProjectService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ApiResponse<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request
    ) {

        ProjectResponse project =
                projectService.createProject(request);

        return ApiResponse.<ProjectResponse>builder()
                .success(true)
                .message("Project created successfully")
                .data(project)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProjectResponse>> getProjects() {

        List<ProjectResponse> projects =
                projectService.getMyProjects();

        return ApiResponse.<List<ProjectResponse>>builder()
                .success(true)
                .message("Projects fetched successfully")
                .data(projects)
                .build();
    }
    @GetMapping("/{projectId}")
    public ApiResponse<ProjectDetailsResponse> getProject(
            @PathVariable UUID projectId
    ) {

        ProjectDetailsResponse project =
                projectService.getProject(projectId);

        return ApiResponse
                .<ProjectDetailsResponse>builder()
                .success(true)
                .message("Project fetched successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data(project)
                .meta(null)
                .build();
    }
    @GetMapping("/{projectId}/stats")
    public ApiResponse<ProjectStatsResponse>
    getProjectStats(
            @PathVariable UUID projectId
    ) {

        ProjectStatsResponse stats =
                projectService.getProjectStats(projectId);

        return ApiResponse
                .<ProjectStatsResponse>builder()
                .success(true)
                .message("Project stats fetched successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data(stats)
                .meta(null)
                .build();
    }
}