package com.mravel.catalog.dto.partner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PartnerCatalogDtos {

        public enum PendingReason {
                CREATE, UPDATE
        }

        public record UpsertWrapper<T>(
                        @NotNull Long partnerId,
                        @NotNull PendingReason pendingReason,
                        @NotNull T payload) {
        }

        public record UnlockRequestWrapper(
                        @NotNull Long partnerId,
                        @NotBlank String reason) {
        }

        // == COMMON ==
        public record ImageReq(
                        @NotBlank String url,
                        String caption,
                        Boolean cover,
                        Integer sortOrder) {
        }

        // == HOTEL (FULL) ==
        public record UpsertHotelReq(
                        // link & location
                        String destinationSlug,
                        String cityName,
                        String districtName,
                        String wardName,
                        String addressLine,
                        Double latitude,
                        Double longitude,

                        // basic
                        String name,
                        String slug, // optional: nếu null -> backend gen
                        String hotelType, // HOTEL/HOMESTAY/...
                        Integer starRating, // optional
                        String shortDescription,
                        String description,
                        String phone,
                        String email,
                        String website,

                        // checkin/out flags
                        String defaultCheckInTime, // "14:00"
                        String defaultCheckOutTime, // "12:00"
                        Boolean hasOnlineCheckin,

                        // media/content
                        List<ImageReq> images,
                        List<UpsertContentBlockReq> content,

                        // amenities
                        List<String> amenityCodes, // scope HOTEL

                        // rooms
                        List<UpsertRoomTypeReq> roomTypes,

                        // nearby/policy/info/faq
                        List<UpsertNearbyPlaceReq> nearbyPlaces,
                        UpsertHotelPolicyReq policy,
                        UpsertGeneralInfoReq generalInfo,
                        List<UpsertFaqReq> faqs,

                        // configs
                        UpsertTaxConfigReq taxConfig,
                        UpsertBookingConfigReq bookingConfig) {
        }

        // Content blocks
        public record UpsertContentBlockReq(
                        String type, // HEADING/PARAGRAPH/IMAGE/GALLERY/QUOTE/DIVIDER/INFOBOX/MAP
                        String section, // OVERVIEW/STORY/OTHER
                        String text,
                        ImageReq image, // for IMAGE
                        List<ImageReq> gallery, // for GALLERY
                        Double mapLat, // for MAP
                        Double mapLon, // for MAP
                        Integer sortOrder) {
        }

        // Nearby
        public record UpsertNearbyPlaceReq(
                        String placeSlug,
                        @NotBlank String name,
                        String category,
                        Double distanceMeters) {
        }

        // Policy
        public record UpsertHotelPolicyReq(
                        String checkInFrom, // "14:00"
                        String checkOutUntil, // "12:00"
                        List<UpsertPolicyItemReq> items) {
        }

        public record UpsertPolicyItemReq(
                        String section, // PolicySection enum name
                        String title,
                        String content) {
        }

        // General info
        public record UpsertGeneralInfoReq(
                        String mainFacilitiesSummary,
                        Double distanceToCityCenterKm,
                        String popularAreaSummary,
                        Integer totalRooms,
                        Integer totalFloors,
                        String otherHighlightFacilities,
                        String interestingPlacesSummary) {
        }

        // FAQ
        public record UpsertFaqReq(
                        @NotBlank String question,
                        @NotBlank String answer) {
        }

        // Tax config
        public record UpsertTaxConfigReq(
                        BigDecimal defaultVatPercent,
                        BigDecimal defaultServiceFeePercent,
                        Boolean showPricePreTax) {
        }

        // Booking config
        public record UpsertBookingConfigReq(
                        Boolean allowFullPayment,
                        Boolean allowDeposit,
                        BigDecimal depositPercent,
                        Integer freeCancelMinutes) {
        }

        // Room types
        public record UpsertRoomTypeReq(
                        String id, // null => backend gen
                        @NotBlank String name,
                        String shortDescription,
                        String description,

                        Double areaSqm,

                        String bedType, // SINGLE/DOUBLE/TWIN/QUEEN/KING/...
                        Integer bedsCount,

                        String bedLayoutDescription,
                        List<UpsertBedOptionReq> bedOptions,

                        Integer maxAdults,
                        Integer maxChildren,
                        Integer maxGuests,
                        Integer totalRooms,

                        List<ImageReq> images,
                        List<String> amenityCodes, // scope ROOM
                        List<UpsertRatePlanReq> ratePlans) {
        }

        public record UpsertBedOptionReq(
                        String type, // BedType
                        Integer count) {
        }

        // Rate plans
        public record UpsertRatePlanReq(
                        String id, // null => backend gen
                        @NotBlank String name,

                        String boardType, // ROOM_ONLY/BREAKFAST_INCLUDED/...
                        String paymentType, // PAY_AT_HOTEL/PREPAID
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
                        Boolean showLowAvailability) {
        }

        public record UpsertLengthOfStayDiscountReq(
                        Integer minNights,
                        Integer maxNights,
                        Integer discountPercent) {
        }

        // == RESTAURANT ==
        public record UpsertRestaurantReq(
                        @NotBlank String name,
                        String slug,

                        // location/link
                        String destinationSlug,
                        String parentPlaceSlug,

                        // thống nhất cityName, nhưng vẫn nhận provinceName nếu client cũ gửi lên
                        @JsonAlias({
                                        "provinceName" }) String cityName,

                        String districtName,
                        String wardName,
                        String addressLine,
                        Double latitude,
                        Double longitude,

                        // basic
                        String restaurantType, // BUFFET / GOI_MON / BBQ / BAR / CAFE / LOUNGE / OTHER
                        String shortDescription,
                        String description,

                        // contact
                        String phone,
                        String email,
                        String website,
                        String facebookPage,
                        String bookingHotline,

                        // pricing
                        BigDecimal minPrice,
                        BigDecimal maxPrice,
                        String currencyCode,
                        String priceLevel,  // CHEAP / MODERATE / EXPENSIVE / LUXURY
                        String priceBucket, // UNDER_150K / FROM_150K_TO_250K / ...

                        // tags + time
                        List<UpsertCuisineTagReq> cuisines,
                        List<UpsertOpeningHourReq> openingHours,
                        List<UpsertCodeNameReq> suitableFor,
                        List<UpsertCodeNameReq> ambience,
                        List<UpsertSignatureDishReq> signatureDishes,

                        // capacity / parking
                        UpsertCapacityInfoReq capacity,
                        UpsertParkingInfoReq parking,

                        // policy
                        UpsertRestaurantPolicyReq policy,

                        // media
                        List<ImageReq> images,
                        List<ImageReq> menuImages,
                        List<UpsertMenuSectionReq> menuSections,
                        List<UpsertContentBlockReq> content,

                        // extras
                        List<String> amenityCodes,
                        List<UpsertTableTypeReq> tableTypes,
                        UpsertRestaurantBookingConfigReq bookingConfig) {
        }

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
                        String note) {
        }

        public record UpsertRestaurantBookingConfigReq(
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

        // == RESTAURANT — SUB-RECORDS ==

        public record UpsertCuisineTagReq(
                        String code,
                        String name,
                        String region) {
        }

        public record UpsertOpeningHourReq(
                        String dayOfWeek, // MONDAY / TUESDAY / ...
                        String openTime,  // "HH:mm"
                        String closeTime, // "HH:mm"
                        Boolean open24h,
                        Boolean closed) {
        }

        public record UpsertCodeNameReq(
                        String code,
                        String name) {
        }

        public record UpsertSignatureDishReq(
                        @NotBlank String name,
                        String description,
                        BigDecimal estimatedPrice,
                        Boolean highlight) {
        }

        public record UpsertCapacityInfoReq(
                        Integer totalCapacity,
                        Integer maxGroupSize,
                        Boolean hasPrivateRooms,
                        Integer privateRoomCount,
                        Integer maxPrivateRoomCapacity,
                        Boolean hasOutdoorSeating) {
        }

        public record UpsertParkingInfoReq(
                        Boolean hasCarParking,
                        String carParkingLocation,
                        String carParkingFeeType, // FREE / PAID / UNKNOWN
                        BigDecimal carParkingFeeAmount,
                        Boolean hasMotorbikeParking,
                        String motorbikeParkingLocation,
                        String motorbikeParkingFeeType,
                        BigDecimal motorbikeParkingFeeAmount,
                        String notes) {
        }

        public record UpsertMenuSectionReq(
                        String code,
                        String name,
                        List<UpsertMenuItemReq> items) {
        }

        public record UpsertMenuItemReq(
                        @NotBlank String name,
                        String description,
                        BigDecimal priceFrom,
                        BigDecimal priceTo,
                        String unit,
                        Boolean combo,
                        Boolean buffetItem,
                        List<String> tags) {
        }

        public record UpsertRestaurantPolicyReq(
                        // Đặt cọc
                        Boolean depositRequired,
                        Integer depositMinGuests,
                        BigDecimal depositAmount,
                        String depositCurrencyCode,
                        String depositNotes,

                        // Ưu đãi & ngày ngoại lệ
                        Boolean hasPromotion,
                        String promotionSummary,
                        Integer promotionMaxDiscountPercent,
                        String promotionNote,
                        List<UpsertBlackoutDateRuleReq> blackoutRules,

                        // Thời gian đặt / giữ chỗ
                        Integer minBookingLeadTimeMinutes,
                        Integer maxHoldTimeMinutes,
                        Integer minGuestsPerBooking,

                        // Hóa đơn
                        Boolean vatInvoiceAvailable,
                        BigDecimal vatPercent,
                        Boolean directInvoiceAvailable,
                        String invoiceNotes,

                        // Phí phục vụ
                        BigDecimal serviceChargePercent,
                        String serviceChargeNotes,

                        // Mang đồ từ ngoài vào
                        Boolean allowOutsideFood,
                        Boolean allowOutsideDrink,
                        String outsideFoodPolicy,
                        String outsideDrinkPolicy,
                        List<UpsertOutsideDrinkFeeReq> outsideDrinkFees) {
        }

        public record UpsertBlackoutDateRuleReq(
                        String dateType, // GREGORIAN_DATE / DATE_RANGE / LUNAR_DATE
                        Integer month,
                        Integer day,
                        String fromDate, // ISO 8601
                        String toDate,
                        Integer lunarMonth,
                        Integer lunarDay,
                        String description) {
        }

        public record UpsertOutsideDrinkFeeReq(
                        String drinkType, // WINE / SPIRITS / BEER / OTHER
                        BigDecimal feeAmount,
                        String currencyCode,
                        String note) {
        }
}