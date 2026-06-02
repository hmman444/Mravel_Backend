package com.mravel.plan.dto;

import java.util.List;

/** DTO thống kê plan/post cho dashboard admin. */
public class PlanAdminStatsDtos {

    public record Overview(
            long totalPlans,
            long publicPlans,
            long newPlans7d,
            long newPlans30d,
            long totalViews,
            long totalReactions,
            long totalComments,
            long takenDownPlans,
            long pendingReports) {
    }

    public record Distribution(String key, long count) {
    }

    public record TimePoint(String date, long count) {
    }

    public record TopPlan(Long id, String title, long views, Long authorId) {
    }

    public record StatsResponse(
            Overview overview,
            List<Distribution> byStatus,
            List<Distribution> byVisibility,
            List<TimePoint> newPlansByDay,
            List<TopPlan> topPlans) {
    }
}
