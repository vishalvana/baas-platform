package com.vishal.baas_platform.filter;

import com.vishal.baas_platform.entity.ApiUsageLog;
import com.vishal.baas_platform.repository.ApiUsageLogRepository;
import com.vishal.baas_platform.repository.ProjectRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApiUsageTrackingFilter
        extends OncePerRequestFilter {

    private final ApiUsageLogRepository logRepository;
    private final ProjectRepository projectRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(request, response);

        String apiKey =
                request.getHeader("x-api-key");

        if (apiKey == null) {
            return;
        }

        projectRepository.findByApiKey(apiKey)
                .ifPresent(project -> {

                    ApiUsageLog log =
                            ApiUsageLog.builder()
                                    .projectId(project.getId())
                                    .endpoint(request.getRequestURI())
                                    .method(request.getMethod())
                                    .statusCode(response.getStatus())
                                    .responseTimeMs(
                                            System.currentTimeMillis()
                                                    - startTime
                                    )
                                    .createdAt(LocalDateTime.now())
                                    .build();

                    logRepository.save(log);
                });
    }
}
