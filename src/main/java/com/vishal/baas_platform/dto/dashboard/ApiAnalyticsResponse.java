package com.vishal.baas_platform.dto.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiAnalyticsResponse {

    private long totalRequests;

    private long requestsToday;

    private double averageResponseTime;
}