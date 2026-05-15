package com.vishal.baas_platform.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishal.baas_platform.entity.DataRecord;
import com.vishal.baas_platform.repository.DataRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRecordRepository dataRecordRepository;

   // private final ObjectMapper objectMapper;

    public DataRecord createRecord(
            UUID projectId,
            String collectionName,
            Map<String, Object> requestBody
    ) {

        DataRecord record = DataRecord.builder()
                .projectId(projectId)
                .collectionName(collectionName)
                .data(requestBody)
                .createdAt(LocalDateTime.now())
                .build();

        return dataRecordRepository.save(record);
    }

    public List<DataRecord> getRecords(
            UUID projectId,
            String collectionName
    ) {

        return dataRecordRepository
                .findByProjectIdAndCollectionName(
                        projectId,
                        collectionName
                );
    }
}