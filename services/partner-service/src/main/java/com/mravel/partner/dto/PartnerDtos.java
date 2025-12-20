package com.mravel.partner.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class PartnerDtos {

    // =========================================================
    //                         ENUMS
    // =========================================================

    /**
     * Enum trạng thái dịch vụ tối thiểu theo scope bạn chốt.
     * - PENDING: chờ admin duyệt (tạo mới hoặc chỉnh sửa)
     * - ACTIVE: đang hoạt động
     * - REJECTED: admin từ chối
     * - PARTNER_PAUSED: partner tự khóa
     * - ADMIN_BLOCKED: admin khóa
     */
    public enum ServiceStatus {
        PENDING,
        ACTIVE,
        REJECTED,
        PARTNER_PAUSED,
        ADMIN_BLOCKED
    }

    public enum PendingReason {
        CREATE,
        UPDATE
    }

    // =========================================================
    //                     COMMON REQUESTS
    // =========================================================

    public record RejectReasonReq(@NotBlank String reason) {}
    public record BlockReasonReq(@NotBlank String reason) {}
    public record UnlockRequestReq(@NotBlank String reason) {}

    // =========================================================
    //                      HOTEL – REQUESTS
    // =========================================================

    /**
     * DTO create/update hotel gửi từ Partner UI vào partner-service.
     * partner-service sẽ tự gắn ownerId từ JWT và forward sang catalog-service.
     *
     * Bạn có thể rút gọn field theo UI bạn đang có.
     */
    public record UpsertHotelReq(
            @NotBlank String name,
            @NotBlank String slug,
            String shortDescription,
            String description,

            String phone,
            String email,
            String website,

            String addressLine,
            String provinceCode,
            String provinceName,
            String districtCode,
            String districtName,
            String wardCode,
            String wardName,

            Double latitude,
            Double longitude,

            BigDecimal minPrice,
            BigDecimal maxPrice,

            List<ImageReq> images


    ) {}

    public record ImageReq(
            @NotBlank String url,
            String caption,
            Boolean cover,
            Integer sortOrder
    ) {}

    // =========================================================
    //                    RESTAURANT – REQUESTS
    // =========================================================

    public record UpsertRestaurantReq(
            @NotBlank String name,
            @NotBlank String slug,
            String shortDescription,
            String description,

            String phone,
            String email,
            String website,

            String addressLine,
            String provinceCode,
            String provinceName,
            String districtCode,
            String districtName,
            String wardCode,
            String wardName,

            Double latitude,
            Double longitude,

            BigDecimal minPrice,
            BigDecimal maxPrice,

            List<ImageReq> images
    ) {}

    // =========================================================
    //                      SERVICE RESPONSES
    // =========================================================

    /**
     * Response chung cho service list/detail.
     * (partner-service chỉ forward; response thực tế nên lấy từ catalog-service)
     */
    public record PartnerServiceSummary(
            String id,
            String name,
            String slug,
            ServiceStatus status,
            PendingReason pendingReason,
            String rejectionReason,
            Boolean active,
            Instant createdAt,
            Instant updatedAt
    ) {}

    // =========================================================
    //                     BOOKING – REQUESTS
    // =========================================================

    public record CancelBookingReq(@NotBlank String reason) {}

    // =========================================================
    //                    BOOKING – RESPONSES
    // =========================================================

    public enum BookingType { HOTEL, RESTAURANT }

    public record PartnerBookingSummary(
            BookingType type,
            String bookingCode,
            String serviceId,
            String serviceName,
            String serviceSlug,
            String customerName,
            String customerPhone,
            String customerEmail,
            LocalDate dateFrom,
            LocalDate dateTo,
            String status,
            String paymentStatus,
            BigDecimal amountPaid,
            Instant createdAt
    ) {}

    // =========================================================
    //                      STATS – RESPONSES
    // =========================================================

    public record StatusCount(String status, Long count) {}

    public record PartnerBookingStatsResponse(
            List<StatusCount> bookingStatusCounts,
            List<StatusCount> paymentStatusCounts
    ) {}

    public record PartnerRevenueResponse(
            BigDecimal hotelRevenue,
            BigDecimal restaurantRevenue,
            BigDecimal totalRevenue
    ) {}
}