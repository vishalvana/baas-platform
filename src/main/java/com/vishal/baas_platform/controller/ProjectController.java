package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.project.ProjectRequest;
import com.vishal.baas_platform.dto.project.ProjectResponse;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.service.ProjectService;
import com.vishal.baas_platform.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}