package com.mravel.catalog.dto.hotel;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenitySection;

public class HotelDtos {

        // ================== SUMMARY (LIST / SEARCH RESULT) ==================

        /**
         * Dùng cho trang list / search khách sạn.
         * Mục tiêu: đủ data vẽ card giống Traveloka.
         */
        public record HotelSummaryDTO(
                        String id,
                        String name,
                        String slug,
                        Boolean active,
                        Integer starRating,
                        String hotelType, // enum HotelType -> String
                        String destinationSlug,
                        String cityName,
                        String districtName,
                        String wardName,
                        String addressLine,
                        Double latitude,
                        Double longitude,

                        // Rating tổng
                        Double avgRating,
                        Integer reviewsCount,
                        String ratingLabel,

                        // Giá & tiền tệ
                        BigDecimal minNightlyPrice,
                        String currencyCode,

                        // Giá gốc & % giảm (dùng cho list)
                        BigDecimal referenceNightlyPrice,
                        Integer discountPercent,

                        // Tóm tắt tiện ích & vị trí
                        String mainFacilitiesSummary, // từ GeneralInfo
                        Double distanceToCityCenterKm,

                        // Ảnh cover cho card
                        String coverImageUrl,

                        // Một vài tag highlight (tuỳ backend tính)
                        List<String> highlightTags // ví dụ: ["Gần trung tâm", "Bao gồm bữa sáng"]
        ) {
        }

        // ================== DETAIL (TRANG CHI TIẾT) ==================

        /**
         * Dùng cho trang chi tiết khách sạn.
         * Chứa đầy đủ thông tin hotel + room list + review + policy.
         */
        public record HotelDetailDTO(
                        String id,
                        String name,
                        String slug,
                        Boolean active,
                        Integer starRating,
                        String hotelType,

                        String shortDescription,
                        String description,

                        // Liên kết & địa chỉ
                        String destinationSlug,
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

                        // Check-in/out
                        LocalTime defaultCheckInTime,
                        LocalTime defaultCheckOutTime,

                        // Rating tổng
                        Double avgRating,
                        Integer reviewsCount,
                        String ratingLabel,

                        // Giá & tiền tệ
                        BigDecimal minNightlyPrice,
                        String currencyCode,
                        BigDecimal referenceNightlyPrice,
                        Integer discountPercent,

                        // Chính sách, info chung
                        HotelPolicyDTO policy,
                        GeneralInfoDTO generalInfo,

                        // Tiện ích & xung quanh
                        List<AmenityDTO> amenities,
                        List<NearbyPlaceDTO> nearbyPlaces,

                        // Review chi tiết + keyword
                        ReviewStatsDTO reviewStats,

                        // FAQ
                        List<FaqDTO> faqs,

                        // Ảnh & content dài
                        List<ImageDTO> images,
                        List<ContentBlockDTO> content,

                        // Danh sách loại phòng với rate plan
                        List<RoomTypeDTO> roomTypes,

                        // Thông tin đối tác & moderation (nếu bạn muốn show ở admin UI)
                        PublisherDTO publisher,
                        ModerationDTO moderation,

                        BookingConfigDTO bookingConfig) {
        }

        public record BookingConfigDTO(
                        Boolean allowFullPayment,
                        Boolean allowDeposit,
                        BigDecimal depositPercent,
                        Integer freeCancelMinutes) {
        }

        // ================== SUB-DTO CHO CÁC PHẦN CON ==================

        // Ảnh dùng chung
        public record ImageDTO(
                        String url,
                        String caption,
                        boolean cover,
                        Integer sortOrder) {
        }

        // Content block dùng render bài giới thiệu chi tiết khách sạn
        public record ContentBlockDTO(
                        BlockType type,
                        String text,
                        ImageDTO image,
                        List<ImageDTO> gallery,
                        Double mapLat,
                        Double mapLon) {
                public enum BlockType {
                        HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
                }
        }

        // Tiện ích (có group để group theo section)
        public record AmenityDTO(
                        String code,
                        String name,
                        AmenityGroup group,
                        AmenitySection section,
                        String icon) {
        }

        // Điểm lân cận
        public record NearbyPlaceDTO(
                        String placeSlug,
                        String name,
                        String category, // "Địa điểm lân cận", "Trung tâm giao thông"...
                        Double distanceMeters) {
        }

        // Chính sách
        public record HotelPolicyDTO(
                        LocalTime checkInFrom,
                        LocalTime checkOutUntil,
                        List<PolicyItemDTO> items) {
        }

        public record PolicyItemDTO(
                        String section, // PolicySection -> String
                        String title,
                        String content) {
        }

        // Thông tin chung
        public record GeneralInfoDTO(
                        String mainFacilitiesSummary,
                        Double distanceToCityCenterKm,
                        String popularAreaSummary,
                        Integer totalRooms,
                        Integer totalFloors,
                        String otherHighlightFacilities,
                        String interestingPlacesSummary) {
        }

        // FAQ
        public record FaqDTO(
                        String question,
                        String answer) {
        }

        // Review chi tiết
        public record ReviewStatsDTO(
                        Double cleanlinessScore,
                        Double roomAmenitiesScore,
                        Double foodScore,
                        Double locationScore,
                        Double serviceScore,
                        List<ReviewKeywordDTO> keywords) {
        }

        public record ReviewKeywordDTO(
                        String code,
                        String label,
                        Integer count) {
        }

        // ================== ROOM & RATE PLAN ==================

        public record RoomTypeDTO(
                        String id,
                        String name,
                        String shortDescription,
                        String description,
                        Double areaSqm,
                        String bedType, // BedType -> String
                        Integer bedsCount,

                        Integer maxAdults,
                        Integer maxChildren,
                        Integer maxGuests,

                        Integer totalRooms,

                        List<ImageDTO> images,
                        List<AmenityDTO> amenities,
                        List<RatePlanDTO> ratePlans) {
        }

        public record RatePlanDTO(
                        String id,
                        String name,
                        String boardType, // BoardType -> String
                        String paymentType, // PaymentType -> String
                        Boolean refundable,
                        String cancellationPolicy,

                        // Giá cơ bản
                        BigDecimal pricePerNight,

                        // Giá gốc dùng cho gạch ngang + tính “Tiết kiệm …”
                        BigDecimal referencePricePerNight,
                        Integer discountPercent,

                        // Thuế / phí để FE tính mode EXCL_TAX / INCL_TAX
                        BigDecimal taxPercent,
                        BigDecimal serviceFeePercent,
                        Boolean priceIncludesTax,
                        Boolean priceIncludesServiceFee,

                        // Label khuyến mãi + flag cảnh báo
                        String promoLabel,
                        Boolean showLowAvailability) {
        }

        // ================== PUBLISHER & MODERATION ==================

        public record PublisherDTO(
                        String partnerId,
                        String partnerName,
                        String partnerEmail,
                        String partnerType, // PartnerType -> String
                        String createdAt, // ISO string cho dễ parse bên FE
                        String lastUpdatedAt) {
        }

        public record ModerationDTO(
                        String status, // HotelStatus -> String
                        String rejectionReason,
                        String blockedReason,
                        Integer reportCount,
                        String lastActionByAdminId,
                        String lastActionAt // ISO string
        ) {
        }
}