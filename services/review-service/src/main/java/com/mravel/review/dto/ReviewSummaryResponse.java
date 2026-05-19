package com.mravel.review.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryResponse {
    private Double averageRating;
    private Long totalReviews;
    private Map<Integer, Long> ratingDistribution;
    private List<PopularAspectDTO> popularAspects;
}
