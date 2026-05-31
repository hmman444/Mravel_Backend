package com.mravel.plan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.ReviewEligibilityService;

import lombok.RequiredArgsConstructor;

/**
 * Endpoint nội bộ phục vụ review-service: kiểm tra user (từ token) đã trải nghiệm
 * dịch vụ catalog chưa. Chỉ ĐỌC, không đụng luồng ghi/board hiện có.
 */
@RestController
@RequestMapping("/api/plans/internal")
@RequiredArgsConstructor
public class PlanExperienceController {

    private final ReviewEligibilityService eligibilityService;
    private final CurrentUserService currentUser;

    @GetMapping("/experienced")
    public ApiResponse<Map<String, Object>> experienced(
            @RequestParam String targetType,
            @RequestParam String targetId,
            @RequestParam(required = false) String slug,
            @RequestParam(required = false) String name) {

        ReviewEligibilityService.ExperienceResult r =
                eligibilityService.check(currentUser.getId(), targetId, slug, name);

        Map<String, Object> data = new HashMap<>();
        data.put("eligible", r.eligible());
        data.put("lastExperiencedDate", r.lastExperiencedDate());
        return ApiResponse.success("OK", data);
    }
}
