package com.mravel.review.dto;

import com.mravel.review.model.TargetType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {

    @NotBlank
    private String targetId;

    @NotNull
    private TargetType targetType;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String content;

    @Valid
    private List<AspectCommentInput> aspects;
}
