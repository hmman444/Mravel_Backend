package com.mravel.booking.dto;

import java.math.BigDecimal;
import java.util.List;

/** DTO thống kê booking cho admin dashboard. Mọi doanh thu là VND thô (BigDecimal). */
public class AdminBookingStatsDtos {

    public record StatusCount(String status, long count) {
    }

    /** Doanh thu theo bucket (VND thô), tách hotel/restaurant. */
    public record RevenuePoint(String label, BigDecimal hotel, BigDecimal restaurant) {
    }

    /** Dịch vụ bán chạy: serviceId trùng id catalog (hotelId/restaurantId). */
    public record TopService(String serviceId, String type, String name, long orders, BigDecimal revenue) {
    }

    public record BookingStats(
            long bookingsToday,
            long bookings7d,
            BigDecimal revenueTodayVnd,
            BigDecimal revenue7dVnd,
            double cancelRate7dPct,
            double refundRate7dPct,
            double paymentFail7dPct,
            long failedPayments7d,
            List<StatusCount> bookingStatus,
            List<RevenuePoint> revenueSeries,
            List<TopService> topServices) {
    }
}
