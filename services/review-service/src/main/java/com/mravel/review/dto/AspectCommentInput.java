package com.mravel.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AspectCommentInput {

    @NotBlank
    private String aspectCode;

    @NotBlank
    private String comment;
}
