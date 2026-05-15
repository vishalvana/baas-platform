package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.project.ProjectRequest;
import com.vishal.baas_platform.dto.project.ProjectResponse;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.service.ProjectService;
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
    public ProjectResponse createProject(
            @Valid @RequestBody ProjectRequest request
    ) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectResponse> getProjects() {
        return projectService.getMyProjects();
    }
}