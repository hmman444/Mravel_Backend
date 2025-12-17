package com.mravel.catalog.dto.amenity;

import java.util.List;

import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;

import lombok.Data;

@Data
public class AmenityUpsertRequest {
    private String code;
    private String name;
    private AmenityScope scope;
    private AmenityGroup group;
    private AmenitySection section;
    private String icon;
    private String description;
    private List<String> aliases;
    private Integer sortOrder;
    private Boolean active;
}