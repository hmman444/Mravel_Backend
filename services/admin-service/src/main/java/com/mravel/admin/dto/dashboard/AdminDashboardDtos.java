// src/main/java/com/mravel/admin/dto/dashboard/AdminDashboardDtos.java
package com.mravel.admin.dto.dashboard;

import java.math.BigDecimal;
import java.util.List;

public class AdminDashboardDtos {

    public record DashboardResponse(
            String range, // today|weekly|monthly|yearly
            Overview overview,
            List<AlertItem> alerts,
            List<ActionQueueItem> actionQueue,
            List<RevenuePoint> revenueSeries, // đơn vị: triệu VND (y chang MOCK)
            List<BookingStatusItem> bookingStatus,
            List<TopServiceItem> topServices,
            List<TopPartnerItem> topPartners,
            SystemHealth systemHealth) {
    }

    public record Overview(
            long totalUsers,
            long activeUsers7d,
            long partners,
            long servicesActive,
            long servicesPendingApproval,
            long bookingsToday,
            long bookings7d,
            BigDecimal revenueTodayVnd,
            BigDecimal revenue7dVnd,
            double cancelRate7dPct,
            double refundRate7dPct,
            double paymentFail7dPct) {
    }

    public record AlertItem(String id, String level, String title, String desc) {
    }

    public record ActionQueueItem(String id, String title, long count, String severity, String hint) {
    }

    public record RevenuePoint(String label, long total, long hotel, long restaurant) {
        // total/hotel/restaurant: TRIỆU VND
    }

    public record BookingStatusItem(String key, String label, long count) {
    }

    public record TopServiceItem(String name, String type, long orders, long revenueM) {
    }

    public record TopPartnerItem(String name, long services, long revenueM) {
    }

    public record SystemHealth(
            double uptimePct,
            long apiP95ms,
            double errorRatePct,
            long kafkaLag,
            long failedJobs,
            String lastIncident) {
    }
}
