package com.mravel.review.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAspectDefinitionDTO {
    private Integer id;
    private String code;
    private String labelVi;
    private String labelEn;
    private String icon;
    private Integer sortOrder;
}
