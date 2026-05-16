package com.mravel.review.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopularAspectDTO {
    private String code;
    private String labelVi;
    private String labelEn;
    private long mentionCount;
}
