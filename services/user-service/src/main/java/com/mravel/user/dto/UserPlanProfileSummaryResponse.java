package com.mravel.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPlanProfileSummaryResponse {
    private long totalPlansVisible; // tổng plan viewer xem được
    private long totalPublicPlans;
    private long totalPhotos; // số ảnh đếm được

    private List<String> photoUrls; // tầm 6–9 url
    // Có thể thêm destinations nổi bật sau này
}