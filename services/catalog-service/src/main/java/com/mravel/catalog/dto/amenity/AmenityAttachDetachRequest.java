package com.mravel.catalog.dto.amenity;

import java.util.List;
import lombok.Data;

@Data
public class AmenityAttachDetachRequest {
    private List<String> codes;
}