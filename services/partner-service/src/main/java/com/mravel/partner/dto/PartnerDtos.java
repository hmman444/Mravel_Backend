package com.mravel.partner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class PartnerDtos {

    // =========================================================
    //                         ENUMS
    // =========================================================

    public enum ServiceStatus {
        PENDING,
        ACTIVE,
        REJECTED,
        PARTNER_PAUSED,
        ADMIN_BLOCKED
    }

    public enum PendingReason { CREATE, UPDATE }

    // =========================================================
    //                     COMMON REQUESTS
    // =========================================================

    public record RejectReasonReq(@NotBlank String reason) {}
    public record BlockReasonReq(@NotBlank String reason) {}
    public record UnlockRequestReq(@NotBlank String reason) {}

    // =========================================================
    //                 WRAPPERS (OPTIONAL)
    // =========================================================

    public record UpsertWrapper<T>(
            @NotNull Long partnerId,
            @NotNull PendingReason pendingReason,
            @NotNull T payload
    ) {}

    public record UnlockRequestWrapper(
            @NotNull Long partnerId,
            @NotBlank String reason
    ) {}

    // =========================================================
    //                      HOTEL – REQUESTS (FULL)
    // =========================================================

    public record ImageReq(
            @NotBlank String url,
            String caption,
            Boolean cover,
            Integer sortOrder
    ) {}

    public record UpsertHotelReq(
            // --- link & location ---
            @NotBlank String destinationSlug,
            String cityName,
            String districtName,
            String wardName,
            String addressLine,
            Double latitude,
            Double longitude,

            // --- basic ---
            @NotBlank String name,
            String slug,                 // optional: nếu null -> backend gen
            String hotelType,            // HOTEL/HOMESTAY/...
            Integer starRating,
            String shortDescription,
            String description,
            String phone,
            String email,
            String website,

            // --- checkin/out flags ---
            String defaultCheckInTime,   // "14:00"
            String defaultCheckOutTime,  // "12:00"
            Boolean hasOnlineCheckin,

            // --- media/content ---
            List<ImageReq> images,
            List<UpsertContentBlockReq> content,

            // --- amenities ---
            List<String> amenityCodes,   // scope HOTEL

            // --- rooms ---
            List<UpsertRoomTypeReq> roomTypes,

            // --- nearby/policy/info/faq ---
            List<UpsertNearbyPlaceReq> nearbyPlaces,
            UpsertHotelPolicyReq policy,
            UpsertGeneralInfoReq generalInfo,
            List<UpsertFaqReq> faqs,

            // --- configs ---
            UpsertTaxConfigReq taxConfig,
            UpsertBookingConfigReq bookingConfig
    ) {}

    // ===== Content blocks =====
    public record UpsertContentBlockReq(
            String type,        // HEADING/PARAGRAPH/IMAGE/GALLERY/QUOTE/DIVIDER/INFOBOX/MAP
            String section,     // OVERVIEW/STORY/OTHER
            String text,
            ImageReq image,         // for IMAGE
            List<ImageReq> gallery, // for GALLERY
            Double mapLat,          // for MAP
            Double mapLon,          // for MAP
            Integer sortOrder
    ) {}

    // ===== Nearby =====
    public record UpsertNearbyPlaceReq(
            String placeSlug,
            @NotBlank String name,
            String category,
            Double distanceMeters
    ) {}

    // ===== Policy =====
    public record UpsertHotelPolicyReq(
            String checkInFrom,     // "14:00"
            String checkOutUntil,   // "12:00"
            List<UpsertPolicyItemReq> items
    ) {}

    public record UpsertPolicyItemReq(
            String section,         // PolicySection enum name
            String title,
            String content
    ) {}

    // ===== General info =====
    public record UpsertGeneralInfoReq(
            String mainFacilitiesSummary,
            Double distanceToCityCenterKm,
            String popularAreaSummary,
            Integer totalRooms,
            Integer totalFloors,
            String otherHighlightFacilities,
            String interestingPlacesSummary
    ) {}

    // ===== FAQ =====
    public record UpsertFaqReq(
            @NotBlank String question,
            @NotBlank String answer
    ) {}

    // ===== Tax config =====
    public record UpsertTaxConfigReq(
            BigDecimal defaultVatPercent,
            BigDecimal defaultServiceFeePercent,
            Boolean showPricePreTax
    ) {}

    // ===== Booking config =====
    public record UpsertBookingConfigReq(
            Boolean allowFullPayment,
            Boolean allowDeposit,
            BigDecimal depositPercent,
            Integer freeCancelMinutes
    ) {}

    // ===== Room types =====
    public record UpsertRoomTypeReq(
            String id,                 // null => backend gen
            @NotBlank String name,
            String shortDescription,
            String description,

            Double areaSqm,

            String bedType,
            Integer bedsCount,

            String bedLayoutDescription,
            List<UpsertBedOptionReq> bedOptions,

            Integer maxAdults,
            Integer maxChildren,
            Integer maxGuests,
            Integer totalRooms,

            List<ImageReq> images,
            List<String> amenityCodes,
            List<UpsertRatePlanReq> ratePlans
    ) {}

    public record UpsertBedOptionReq(
            String type,
            Integer count
    ) {}

    // ===== Rate plans =====
    public record UpsertRatePlanReq(
            String id,
            @NotBlank String name,

            String boardType,
            String paymentType,
            Boolean refundable,
            String cancellationPolicy,

            BigDecimal pricePerNight,
            BigDecimal referencePricePerNight,
            Integer discountPercent,

            BigDecimal taxPercent,
            BigDecimal serviceFeePercent,
            Boolean priceIncludesTax,
            Boolean priceIncludesServiceFee,

            List<UpsertLengthOfStayDiscountReq> lengthOfStayDiscounts,

            String promoLabel,
            Boolean showLowAvailability
    ) {}

    public record UpsertLengthOfStayDiscountReq(
            Integer minNights,
            Integer maxNights,
            Integer discountPercent
    ) {}

    // =========================================================
    //                      SERVICE RESPONSES (giữ như bạn có)
    // =========================================================

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
    //                     BOOKING/STATS (giữ như bạn có)
    // =========================================================

    public record CancelBookingReq(@NotBlank String reason) {}

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

    // =========================================================
    //                   RESTAURANT – REQUESTS
    // =========================================================

    public record UpsertRestaurantReq(
            @NotBlank String name,
            String slug,

            // link & location
            @NotBlank String destinationSlug,
            String cityName,
            String districtName,
            String wardName,
            String addressLine,
            Double latitude,
            Double longitude,

            // basic
            String shortDescription,
            String description,
            String phone,
            String email,
            String website,

            BigDecimal minPrice,
            BigDecimal maxPrice,

            List<ImageReq> images,

            // extras
            List<String> amenityCodes,
            List<UpsertTableTypeReq> tableTypes,
            UpsertRestaurantBookingConfigReq bookingConfig
    ) {}

    public record UpsertTableTypeReq(
            String id,
            @NotBlank String name,
            Integer seats,
            Integer minPeople,
            Integer maxPeople,
            Integer totalTables,
            BigDecimal depositPrice,
            String currencyCode,
            Boolean vip,
            Boolean privateRoom,
            List<Integer> allowedDurationsMinutes,
            Integer defaultDurationMinutes,
            String note
    ) {}

    public record UpsertRestaurantBookingConfigReq(
            Integer slotMinutes,
            List<Integer> allowedDurationsMinutes,
            Integer defaultDurationMinutes,
            Integer minBookingLeadTimeMinutes,
            Integer graceArrivalMinutes,
            Integer freeCancelMinutes,
            Integer pendingPaymentExpireMinutes,
            Boolean depositOnly,
            Integer maxTablesPerBooking
    ) {}
}