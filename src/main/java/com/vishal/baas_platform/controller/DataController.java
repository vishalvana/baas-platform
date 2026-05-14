package com.vishal.baas_platform.controller;

import com.vishal.baas_platform.entity.DataRecord;
import com.vishal.baas_platform.service.DataService;
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
    public DataRecord createRecord(
            @PathVariable UUID projectId,
            @PathVariable String collection,
            @RequestBody Map<String, Object> requestBody
    ) {

        return dataService.createRecord(
                projectId,
                collection,
                requestBody
        );
    }

    @GetMapping("/{projectId}/{collection}")
    public List<DataRecord> getRecords(
            @PathVariable UUID projectId,
            @PathVariable String collection
    ) {

        return dataService.getRecords(
                projectId,
                collection
        );
    }
}