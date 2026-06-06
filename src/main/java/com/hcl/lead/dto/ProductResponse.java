package com.hcl.lead.dto;

import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String productCode,
    String name,
    Double price,
    Integer quantity,
    String stockStatus,     // Computed business property
    LocalDateTime generatedAt
) {}