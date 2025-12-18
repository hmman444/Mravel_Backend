package com.mravel.admin.dto.amenity;

import java.util.List;
import com.mravel.admin.enums.amenity.AmenityGroup;
import com.mravel.admin.enums.amenity.AmenityScope;
import com.mravel.admin.enums.amenity.AmenitySection;

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
