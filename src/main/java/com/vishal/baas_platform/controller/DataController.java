package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.entity.DataRecord;
import com.vishal.baas_platform.service.DataService;
import com.vishal.baas_platform.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @PostMapping("/{projectId}/{collection}")
    public ApiResponse<DataRecord> createRecord(
            @PathVariable UUID projectId,
            @PathVariable String collection,
            @RequestBody Map<String, Object> requestBody
    ) {

        DataRecord record = dataService.createRecord(
                projectId,
                collection,
                requestBody
        );

        return ApiResponse.<DataRecord>builder()
                .success(true)
                .message("Record created successfully")
                .data(record)
                .build();
    }

    @GetMapping("/{projectId}/{collection}")
    public ApiResponse<List<DataRecord>> getRecords(
            @PathVariable UUID projectId,
            @PathVariable String collection
    ) {

        List<DataRecord> records =
                dataService.getRecords(projectId, collection);

        return ApiResponse.<List<DataRecord>>builder()
                .success(true)
                .message("Records fetched successfully")
                .data(records)
                .build();
    }
}