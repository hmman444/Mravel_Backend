package com.mravel.plan.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.plan.dto.PlanAdminManageDtos;
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

    /**
     * Tìm kiếm/liệt kê TOÀN BỘ lịch trình cho admin (mọi visibility, kể cả đã gỡ).
     * Bộ lọc tùy chọn: q (tiêu đề/mô tả), visibility, status, locked (đã gỡ?).
     */
    @GetMapping("/list")
    public ApiResponse<Page<PlanAdminManageDtos.AdminPlanRow>> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String visibility,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean locked,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {
        return ApiResponse.success("OK",
                planService.adminSearchPlans(q, visibility, status, locked, page, size, sort));
    }

    /** Chi tiết một lịch trình + danh sách báo cáo (xem trước khi gỡ/bật lại). */
    @GetMapping("/plans/{id}")
    public ApiResponse<PlanAdminManageDtos.AdminPlanDetail> detail(@PathVariable Long id) {
        return ApiResponse.success("OK", planService.adminPlanDetail(id));
    }

    /** GỠ bài: ép PRIVATE + khóa (có thể bật lại). */
    @PatchMapping("/plans/{id}/takedown")
    public ApiResponse<Void> takedown(
            @PathVariable Long id,
            @RequestParam(required = false) String reason) {
        planService.takedownPlan(id, currentUser.getId(),
                reason != null ? reason : "Vi phạm tiêu chuẩn cộng đồng");
        return ApiResponse.success("Đã gỡ lịch trình", null);
    }

    /** BẬT LẠI bài đã gỡ: mở khóa (giữ nguyên PRIVATE). */
    @PatchMapping("/plans/{id}/restore")
    public ApiResponse<Void> restore(@PathVariable Long id) {
        planService.restorePlan(id, currentUser.getId());
        return ApiResponse.success("Đã bật lại lịch trình", null);
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
