package com.mravel.review.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String content;

    @Valid
    private List<AspectCommentInput> aspects;
}
