package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.PlanAdminStatsDtos;
import com.mravel.plan.model.PlanReport;
import com.mravel.plan.security.CurrentUserService;
import com.mravel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint kiểm duyệt cho ADMIN (admin-service forward JWT role=ADMIN).
 * Bảo vệ bởi SecurityConfig: /api/plans/internal/admin/** -> hasRole('ADMIN').
 */
@RestController
@RequestMapping("/api/plans/internal/admin")
@RequiredArgsConstructor
public class PlanAdminController {

    private final PlanService planService;
    private final CurrentUserService currentUser;

    /** Thống kê tổng hợp plan/post (overview + phân bố + time-series + top). */
    @GetMapping("/stats")
    public ApiResponse<PlanAdminStatsDtos.StatsResponse> stats(
            @RequestParam(defaultValue = "30") int days) {
        return ApiResponse.success("OK", planService.getAdminStats(days));
    }

    /** GỠ bài: ép PRIVATE + khóa vĩnh viễn (không thể bật lại). */
    @PatchMapping("/plans/{id}/takedown")
    public ApiResponse<Void> takedown(
            @PathVariable Long id,
            @RequestParam(required = false) String reason) {
        planService.takedownPlan(id, currentUser.getId(),
                reason != null ? reason : "Vi phạm tiêu chuẩn cộng đồng");
        return ApiResponse.success("Đã gỡ bài (ép PRIVATE + khóa vĩnh viễn)", null);
    }

    /** Hàng đợi report (lọc theo status: PENDING/REVIEWING/RESOLVED/DISMISSED). */
    @GetMapping("/reports")
    public ApiResponse<Page<PlanReport>> listReports(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success("OK", planService.listReports(status, page, size));
    }

    /** Xử lý report; action=TAKEDOWN sẽ gỡ luôn bài bị báo cáo. */
    @PostMapping("/reports/{id}/resolve")
    public ApiResponse<Void> resolveReport(
            @PathVariable Long id,
            @RequestParam(defaultValue = "NONE") String action,
            @RequestParam(required = false) String reason) {
        planService.resolveReport(id, currentUser.getId(), action, reason);
        return ApiResponse.success("Đã xử lý báo cáo", null);
    }
}
