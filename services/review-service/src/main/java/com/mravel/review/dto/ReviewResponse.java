package com.mravel.review.dto;

import com.mravel.review.model.TargetType;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private Long userId;
    private String userFullname;
    private String userAvatar;
    private String targetId;
    private TargetType targetType;
    private Integer rating;
    private String content;
    private List<AspectCommentResponse> aspects;
    private Instant createdAt;
    private Instant updatedAt;
}
