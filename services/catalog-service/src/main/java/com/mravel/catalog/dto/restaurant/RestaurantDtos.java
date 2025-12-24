package com.mravel.catalog.dto.restaurant;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import com.mravel.catalog.dto.hotel.HotelDtos.AmenityDTO;

public class RestaurantDtos {

        // ================== SUMMARY (LIST / SEARCH RESULT) ==================

        /**
         * Dùng cho trang list / search nhà hàng.
         * Mục tiêu: đủ data vẽ card giống PasGo.
         */
        public record RestaurantSummaryDTO(
                        String id,
                        String name,
                        String slug,
                        Boolean active,
                        String restaurantType, // enum -> String

                        String destinationSlug,
                        String cityName,
                        String districtName,
                        String wardName,
                        String addressLine,
                        Double latitude,
                        Double longitude,

                        // Ẩm thực chính
                        List<String> cuisineNames, // ["Việt", "Á", "Hải sản"]
                        List<String> ambienceLabels, // ["Trang nhã", "Ấm cúng"]
                        Integer totalCapacity, // sức chứa

                        // Rating tổng
                        Double avgRating,
                        Integer reviewsCount,
                        String ratingLabel,

                        // Giá
                        BigDecimal minPricePerPerson,
                        BigDecimal maxPricePerPerson,
                        String currencyCode,
                        String priceLevel, // CHEAP / MODERATE / ...
                        String priceBucket, // UNDER_150K / ...

                        // Ảnh cover cho card
                        String coverImageUrl,

                        // Một vài tag highlight (tuỳ backend tính)
                        List<String> highlightTags // ví dụ: ["Phù hợp gia đình", "Có phòng riêng"]
        ) {
        }

        // ================== DETAIL (TRANG CHI TIẾT) ==================

        /**
         * Dùng cho trang chi tiết nhà hàng.
         * Chứa đầy đủ thông tin restaurant + menu + policy + tiện ích.
         */
        public record RestaurantDetailDTO(
                        String id,
                        String name,
                        String slug,
                        Boolean active,
                        String restaurantType,

                        // Ẩm thực
                        List<CuisineTagDTO> cuisines,

                        String shortDescription,
                        String description,

                        // Liên kết & địa chỉ
                        String destinationSlug,
                        String parentPlaceSlug,
                        String cityName,
                        String districtName,
                        String wardName,
                        String addressLine,
                        Double latitude,
                        Double longitude,

                        // Liên hệ
                        String phone,
                        String email,
                        String website,
                        String facebookPage,
                        String bookingHotline,

                        // Giá
                        BigDecimal minPricePerPerson,
                        BigDecimal maxPricePerPerson,
                        String currencyCode,
                        String priceLevel,
                        String priceBucket,

                        // Giờ hoạt động
                        List<OpeningHourDTO> openingHours,

                        // Phù hợp / không gian / sức chứa
                        List<SuitableForDTO> suitableFor,
                        List<AmbienceTagDTO> ambience,
                        CapacityInfoDTO capacity,

                        // Món đặc sắc & menu
                        List<SignatureDishDTO> signatureDishes,
                        List<ImageDTO> images,
                        List<ImageDTO> menuImages,
                        List<ContentBlockDTO> content,
                        List<MenuSectionDTO> menuSections,

                        // Đỗ xe
                        ParkingInfoDTO parking,

                        // Tiện ích (FE sẽ map code -> label, icon)
                        List<AmenityDTO> amenities,

                        // Policy
                        RestaurantPolicyDTO policy,

                        // Rating & review
                        Double avgRating,
                        Integer reviewsCount,
                        String ratingLabel,
                        ReviewStatsDTO reviewStats,

                        // Đối tác & moderation (dùng cho admin UI)
                        PublisherDTO publisher,
                        ModerationDTO moderation,

                        List<TableTypeDTO> tableTypes,
                        RestaurantBookingConfigDTO bookingConfig) {
        }

        public record TableTypeDTO(
                        String id,
                        String name,
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
                        String note) {
        }

        public record RestaurantBookingConfigDTO(
                        Integer slotMinutes,
                        List<Integer> allowedDurationsMinutes,
                        Integer defaultDurationMinutes,
                        Integer minBookingLeadTimeMinutes,
                        Integer graceArrivalMinutes,
                        Integer freeCancelMinutes,
                        Integer pendingPaymentExpireMinutes,
                        Boolean depositOnly,
                        Integer maxTablesPerBooking) {
        }

        // ================== SUB-DTOS ==================

        // Ảnh dùng chung
        public record ImageDTO(
                        String url,
                        String caption,
                        boolean cover,
                        Integer sortOrder) {
        }

        // Ẩm thực
        public record CuisineTagDTO(
                        String code,
                        String name,
                        String region) {
        }

        // Giờ mở cửa
        public record OpeningHourDTO(
                        DayOfWeek dayOfWeek,
                        LocalTime openTime,
                        LocalTime closeTime,
                        boolean open24h,
                        boolean closed) {
        }

        // SuitableFor
        public record SuitableForDTO(
                        String code,
                        String label) {
        }

        // Ambience
        public record AmbienceTagDTO(
                        String code,
                        String label) {
        }

        // Sức chứa
        public record CapacityInfoDTO(
                        Integer totalCapacity,
                        Integer maxGroupSize,
                        boolean hasPrivateRooms,
                        Integer privateRoomCount,
                        Integer maxPrivateRoomCapacity,
                        boolean hasOutdoorSeating) {
        }

        // Món đặc sắc
        public record SignatureDishDTO(
                        String name,
                        String description,
                        BigDecimal estimatedPrice,
                        boolean highlight) {
        }

        // Content block
        public record ContentBlockDTO(
                        BlockType type,
                        String section, // OVERVIEW / STORY / OTHER
                        String text,
                        ImageDTO image,
                        List<ImageDTO> gallery,
                        Double mapLat,
                        Double mapLon) {
                public enum BlockType {
                        HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
                }
        }

        // Menu
        public record MenuSectionDTO(
                        String code,
                        String name,
                        List<MenuItemDTO> items) {
        }

        public record MenuItemDTO(
                        String name,
                        String description,
                        BigDecimal priceFrom,
                        BigDecimal priceTo,
                        String unit,
                        boolean combo,
                        boolean buffetItem,
                        List<String> tags) {
        }

        // Parking
        public record ParkingInfoDTO(
                        boolean hasCarParking,
                        String carParkingLocation,
                        String carParkingFeeType,
                        BigDecimal carParkingFeeAmount,
                        boolean hasMotorbikeParking,
                        String motorbikeParkingLocation,
                        String motorbikeParkingFeeType,
                        BigDecimal motorbikeParkingFeeAmount,
                        String notes) {
        }

        // Policy
        public record RestaurantPolicyDTO(
                        boolean depositRequired,
                        Integer depositMinGuests,
                        BigDecimal depositAmount,
                        String depositCurrencyCode,
                        String depositNotes,

                        boolean hasPromotion,
                        String promotionSummary,
                        Integer promotionMaxDiscountPercent,
                        String promotionNote,
                        List<BlackoutDateRuleDTO> blackoutRules,

                        Integer minBookingLeadTimeMinutes,
                        Integer maxHoldTimeMinutes,
                        Integer minGuestsPerBooking,

                        boolean vatInvoiceAvailable,
                        BigDecimal vatPercent,
                        boolean directInvoiceAvailable,
                        String invoiceNotes,

                        BigDecimal serviceChargePercent,
                        String serviceChargeNotes,

                        boolean allowOutsideFood,
                        boolean allowOutsideDrink,
                        String outsideFoodPolicy,
                        String outsideDrinkPolicy,
                        List<OutsideDrinkFeeDTO> outsideDrinkFees) {
        }

        public record BlackoutDateRuleDTO(
                        String dateType, // GREGORIAN_DATE / DATE_RANGE / LUNAR_DATE
                        Integer month,
                        Integer day,
                        String fromDate,
                        String toDate,
                        Integer lunarMonth,
                        Integer lunarDay,
                        String description) {
        }

        public record OutsideDrinkFeeDTO(
                        String drinkType,
                        BigDecimal feeAmount,
                        String currencyCode,
                        String note) {
        }

        // Review
        public record ReviewStatsDTO(
                        Double foodScore,
                        Double spaceScore,
                        Double serviceScore,
                        Double priceScore,
                        Double locationScore,
                        List<ReviewKeywordDTO> keywords) {
        }

        public record ReviewKeywordDTO(
                        String code,
                        String label,
                        Integer count) {
        }

        // Publisher & moderation

        public record PublisherDTO(
                        String partnerId,
                        String partnerName,
                        String partnerEmail,
                        String partnerType, // enum -> String
                        String createdAt, // ISO string
                        String lastUpdatedAt) {
        }

        public record ModerationDTO(
                        String status, // enum -> String
                        String rejectionReason,
                        String blockedReason,
                        Integer reportCount,
                        String lastActionByAdminId,
                        String lastActionAt // ISO string
        ) {
        }
}