package com.vishal.baas_platform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "data_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID projectId;

    @Column(nullable = false)
    private String collectionName;

    @Column(columnDefinition = "jsonb")
    @org.hibernate.annotations.JdbcTypeCode(
            org.hibernate.type.SqlTypes.JSON
    )
    private Map<String, Object> data;

    private LocalDateTime createdAt;
}