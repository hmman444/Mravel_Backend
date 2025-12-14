package com.mravel.plan.dto;

import com.mravel.common.response.UserProfileResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSearchResponse {
    private PageResponse<PlanFeedItem> plans;
    private List<UserProfileResponse> users;
    private String query;
}
