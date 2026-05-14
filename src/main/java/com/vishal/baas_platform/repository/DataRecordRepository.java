package com.vishal.baas_platform.repository;

import com.vishal.baas_platform.entity.DataRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DataRecordRepository
        extends JpaRepository<DataRecord, UUID> {

    List<DataRecord> findByProjectIdAndCollectionName(
            UUID projectId,
            String collectionName
    );
}