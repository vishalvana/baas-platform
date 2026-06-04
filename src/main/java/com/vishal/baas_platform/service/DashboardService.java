package com.vishal.baas_platform.service;

import com.vishal.baas_platform.dto.dashboard.DashboardResponse;
import com.vishal.baas_platform.dto.project.ProjectResponse;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.entity.User;
import com.vishal.baas_platform.repository.AppUserRepository;
import com.vishal.baas_platform.repository.DataRecordRepository;
import com.vishal.baas_platform.repository.ProjectRepository;
import com.vishal.baas_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AppUserRepository appUserRepository;
    private final DataRecordRepository dataRecordRepository;

    public DashboardResponse getDashboard() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow();

        List<Project> projects =
                projectRepository.findByOwner(user);

        List<UUID> projectIds = projects.stream()
                .map(Project::getId)
                .toList();

        long totalProjects =
                projects.size();

        long totalDocuments =
                projectIds.isEmpty()
                        ? 0
                        : dataRecordRepository
                        .countByProjectIdIn(projectIds);

        long totalCollections =
                projectIds.isEmpty()
                        ? 0
                        : dataRecordRepository
                        .countDistinctCollections(projectIds);

        long totalAppUsers =
                projectIds.isEmpty()
                        ? 0
                        : appUserRepository
                        .countByProjectIdIn(projectIds);

        List<ProjectResponse> recentProjects =
                projects.stream()
                        .limit(5)
                        .map(project ->
                                ProjectResponse.builder()
                                        .id(project.getId())
                                        .name(project.getName())
                                        .apiKey(project.getApiKey())
                                        .createdAt(project.getCreatedAt())
                                        .build()
                        )
                        .toList();

        return DashboardResponse.builder()
                .totalProjects(totalProjects)
                .totalCollections(totalCollections)
                .totalDocuments(totalDocuments)
                .totalAppUsers(totalAppUsers)
                .recentProjects(recentProjects)
                .build();
    }
}