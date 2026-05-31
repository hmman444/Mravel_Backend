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

    // Gợi ý để khớp dịch vụ với card trong plan (slug + tên); dùng cho gate "đã trải nghiệm".
    private String targetSlug;
    private String targetName;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String content;

    @Valid
    private List<AspectCommentInput> aspects;
}
