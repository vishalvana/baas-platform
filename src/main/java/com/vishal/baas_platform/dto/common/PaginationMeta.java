package com.vishal.baas_platform.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationMeta {

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;
}