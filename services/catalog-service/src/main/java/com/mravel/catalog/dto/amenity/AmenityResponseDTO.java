package com.mravel.catalog.dto.amenity;

import java.time.Instant;
import java.util.List;

import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;

public record AmenityResponseDTO(
        String id,
        String code,
        String name,
        AmenityScope scope,
        AmenityGroup group,
        AmenitySection section,
        String icon,
        String description,
        List<String> aliases,
        Integer sortOrder,
        boolean active,
        Instant createdAt,
        Instant updatedAt) {
}
