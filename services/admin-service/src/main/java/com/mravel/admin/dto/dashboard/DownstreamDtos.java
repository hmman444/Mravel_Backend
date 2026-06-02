package com.mravel.admin.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO ánh xạ payload từ các service downstream (booking/catalog/auth) khi tổng hợp dashboard.
 * Đặt ignoreUnknown để chịu được thay đổi nhỏ ở phía downstream.
 */
public class DownstreamDtos {

    // ===== booking-service: /api/booking/admin/dashboard =====
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BookingStatusCount(String status, long count) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BookingRevenuePoint(String label, BigDecimal hotel, BigDecimal restaurant) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BookingTopService(String serviceId, String type, String name, long orders, BigDecimal revenue) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BookingStats(
            long bookingsToday,
            long bookings7d,
            BigDecimal revenueTodayVnd,
            BigDecimal revenue7dVnd,
            double cancelRate7dPct,
            double refundRate7dPct,
            double paymentFail7dPct,
            long failedPayments7d,
            List<BookingStatusCount> bookingStatus,
            List<BookingRevenuePoint> revenueSeries,
            List<BookingTopService> topServices) {
    }

    // ===== catalog-service: /api/catalog/admin/dashboard =====
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CatalogSummary(long hotelActive, long hotelPending, long restaurantActive, long restaurantPending) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PartnerRef(String serviceId, String type, String partnerId, String partnerName) {
    }

    // ===== auth-service: /api/auth/users/stats =====
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserStats(long totalUsers, long totalPartners, long totalRegularUsers, long totalAdmins) {
    }
}
