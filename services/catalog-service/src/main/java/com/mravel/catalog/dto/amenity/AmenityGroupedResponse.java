package com.mravel.catalog.dto.amenity;

import java.util.List;

import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmenityGroupedResponse {
    private AmenityScope scope;
    private List<GroupNode> groups;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupNode {
        private AmenityGroup group;
        private List<SectionNode> sections;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SectionNode {
        private AmenitySection section;
        private List<ItemNode> items;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemNode {
        private String code;
        private String name;
        private String icon;
        private Integer sortOrder;
        private boolean active;
    }
}