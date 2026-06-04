package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.dto.dashboard.DashboardResponse;
import com.vishal.baas_platform.service.DashboardService;
import com.vishal.baas_platform.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public ApiResponse<DashboardResponse> getDashboard() {

        DashboardResponse dashboard =
                dashboardService.getDashboard();

        return ApiResponse.<DashboardResponse>builder()
                .success(true)
                .message("Dashboard data fetched successfully")
                .status(200)
                .timestamp(LocalDateTime.now())
                .data(dashboard)
                .meta(null)
                .build();
    }
}