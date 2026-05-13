package com.vishal.baas_platform.service;

import com.vishal.baas_platform.dto.project.ProjectRequest;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.entity.User;
import com.vishal.baas_platform.repository.ProjectRepository;
import com.vishal.baas_platform.repository.UserRepository;
import com.vishal.baas_platform.util.ApiKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(ProjectRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Project project = Project.builder()
                .name(request.getName())
                .apiKey(ApiKeyGenerator.generateApiKey())
                .owner(user)
                .createdAt(LocalDateTime.now())
                .build();

        return projectRepository.save(project);
    }

    public List<Project> getMyProjects() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return projectRepository.findByOwner(user);
    }
}