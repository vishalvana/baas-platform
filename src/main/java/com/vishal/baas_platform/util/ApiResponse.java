package com.vishal.baas_platform.util;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private int status;

    private LocalDateTime timestamp;

    private T data;

    private Object meta;
}