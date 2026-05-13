package com.vishal.baas_platform.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {

    @NotBlank
    private String name;
}