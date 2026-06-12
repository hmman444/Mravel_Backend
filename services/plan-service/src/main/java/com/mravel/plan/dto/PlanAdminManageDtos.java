package com.mravel.plan.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO cho màn "Quản lý lịch trình" của admin: tìm kiếm/liệt kê toàn bộ plan
 * (kể cả PRIVATE/FRIENDS), xem chi tiết kèm báo cáo trước khi gỡ, gỡ và bật lại.
 */
public class PlanAdminManageDtos {

    /** Một dòng trong bảng quản lý (thông tin cơ bản + số liệu tổng hợp). */
    public record AdminPlanRow(
            Long id,
            String title,
            Long authorId,
            String authorName,
            String authorAvatar,
            String visibility,
            String status,
            Instant createdAt,
            LocalDate startDate,
            LocalDate endDate,
            Integer days,
            long views,
            long reactions,
            long comments,
            long members,
            long pendingReports,
            boolean adminLocked,
            Instant takedownAt,
            String takedownReason) {
    }

    /** Thành viên của plan (kèm tên hiển thị). */
    public record AdminPlanMember(
            Long userId,
            String name,
            String avatar,
            String role) {
    }

    /** Báo cáo của plan (kèm tên người báo cáo). */
    public record AdminReportItem(
            Long id,
            String reason,
            String detail,
            String status,
            Long reporterId,
            String reporterName,
            Instant createdAt,
            Instant resolvedAt,
            String actionTaken) {
    }

    /** Chi tiết đầy đủ để xem trước khi gỡ/bật lại. */
    public record AdminPlanDetail(
            Long id,
            String title,
            String description,
            Long authorId,
            String authorName,
            String authorAvatar,
            String visibility,
            String status,
            Instant createdAt,
            LocalDate startDate,
            LocalDate endDate,
            Integer days,
            long views,
            long reactions,
            long comments,
            List<String> destinations,
            List<String> images,
            String thumbnail,
            String budgetCurrency,
            Long budgetTotal,
            Long budgetPerPerson,
            Long totalEstimatedCost,
            Long totalActualCost,
            boolean adminLocked,
            Long takedownBy,
            Instant takedownAt,
            String takedownReason,
            List<AdminPlanMember> members,
            List<AdminReportItem> reports) {
    }
}
