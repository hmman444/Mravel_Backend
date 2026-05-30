package com.mravel.catalog.model.doc;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "restaurants")
@CompoundIndexes({
        @CompoundIndex(name = "restaurant_active_destination_idx", def = "{'active':1,'destinationSlug':1}"),
        @CompoundIndex(name = "restaurant_price_rating_idx", def = "{'active':1,'priceBucket':1,'avgRating':-1}")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDoc {

    @Id
    private String id;

    @Builder.Default
    private Integer favoriteCount = 0;

    @Field
    @Indexed
    @Builder.Default
    private Boolean active = true;

    private Instant deletedAt;

    @Indexed
    private String destinationSlug;

    @Indexed
    private String parentPlaceSlug;

    private Map<String, String> cityName;
    private Map<String, String> districtName;
    private Map<String, String> wardName;
    private Map<String, String> addressLine;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "restaurant_loc_2dsphere_idx")
    private double[] location;

    @Indexed
    private Map<String, String> name;

    @Indexed(unique = true)
    private String slug;

    private RestaurantType restaurantType;

    private List<CuisineTag> cuisines;

    private Map<String, String> shortDescription;
    private Map<String, String> description;

    private String phone;
    private String email;
    private String website;
    private String facebookPage;
    private String bookingHotline;

    private BigDecimal minPricePerPerson;
    private BigDecimal maxPricePerPerson;

    private String currencyCode;

    private PriceLevel priceLevel;

    private PriceBucket priceBucket;

    private List<OpeningHour> openingHours;

    private List<SuitableFor> suitableFor;

    private List<AmbienceTag> ambience;

    private CapacityInfo capacity;

    private List<SignatureDish> signatureDishes;

    private List<Image> menuImages;

    private List<MenuSection> menuSections;

    private ParkingInfo parking;

    private List<String> amenityCodes;

    private List<Image> images;

    private List<ContentBlock> content;

    private RestaurantPolicy policy;

    @Builder.Default
    private Double avgRating = 0.0;

    @Builder.Default
    private Integer reviewsCount = 0;

    private Map<String, String> ratingLabel;

    private ReviewStats reviewStats;

    private PublisherInfo publisher;
    private ModerationInfo moderation;

    private List<TableType> tableTypes;

    private BookingConfig bookingConfig;

    //
    // SUBDOCUMENTS
    //

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TableType {
        private String id;
        private Map<String, String> name;
        private Integer seats;
        private Integer minPeople;
        private Integer maxPeople;
        private Integer totalTables;
        private BigDecimal depositPrice;
        private String currencyCode;
        @Builder.Default
        private Boolean vip = false;
        @Builder.Default
        private Boolean privateRoom = false;
        private List<Integer> allowedDurationsMinutes;
        private Integer defaultDurationMinutes;
        private Map<String, String> note;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingConfig {
        @Builder.Default
        private Integer slotMinutes = 30;
        @Builder.Default
        private List<Integer> allowedDurationsMinutes = List.of(60, 90, 120);
        @Builder.Default
        private Integer defaultDurationMinutes = 90;
        @Builder.Default
        private Integer minBookingLeadTimeMinutes = 60;
        @Builder.Default
        private Integer graceArrivalMinutes = 15;
        @Builder.Default
        private Integer freeCancelMinutes = 30;
        @Builder.Default
        private Integer pendingPaymentExpireMinutes = 30;
        @Builder.Default
        private Boolean depositOnly = true;
        @Builder.Default
        private Integer maxTablesPerBooking = 5;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private String url;
        private Map<String, String> caption;
        @Builder.Default
        private Boolean cover = false;
        @Builder.Default
        private Integer sortOrder = 0;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CuisineTag {
        private String code;
        private Map<String, String> name;
        private String region;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OpeningHour {
        private DayOfWeek dayOfWeek;
        private LocalTime openTime;
        private LocalTime closeTime;
        @Builder.Default
        private Boolean open24h = false;
        @Builder.Default
        private Boolean closed = false;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SuitableFor {
        private String code;
        private Map<String, String> label;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AmbienceTag {
        private String code;
        private Map<String, String> label;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CapacityInfo {
        private Integer totalCapacity;
        private Integer maxGroupSize;
        @Builder.Default
        private Boolean hasPrivateRooms = false;
        private Integer privateRoomCount;
        private Integer maxPrivateRoomCapacity;
        @Builder.Default
        private Boolean hasOutdoorSeating = false;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignatureDish {
        private Map<String, String> name;
        private Map<String, String> description;
        private BigDecimal estimatedPrice;
        @Builder.Default
        private Boolean highlight = true;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuSection {
        private String code;
        private Map<String, String> name;
        private List<MenuItem> items;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuItem {
        private Map<String, String> name;
        private Map<String, String> description;
        private BigDecimal priceFrom;
        private BigDecimal priceTo;
        private Map<String, String> unit;
        @Builder.Default
        private Boolean combo = false;
        @Builder.Default
        private Boolean buffetItem = false;
        private List<String> tags;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ParkingInfo {
        @Builder.Default
        private Boolean hasCarParking = false;
        private Map<String, String> carParkingLocation;
        private ParkingFeeType carParkingFeeType;
        private BigDecimal carParkingFeeAmount;

        @Builder.Default
        private Boolean hasMotorbikeParking = false;
        private Map<String, String> motorbikeParkingLocation;
        private ParkingFeeType motorbikeParkingFeeType;
        private BigDecimal motorbikeParkingFeeAmount;

        private Map<String, String> notes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContentBlock {
        public enum BlockType {
            HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
        }

        public enum ContentSection {
            OVERVIEW,
            STORY,
            OTHER
        }

        private BlockType type;
        private ContentSection section;
        private Map<String, String> text;
        private Image image;
        private List<Image> gallery;
        private double[] mapLocation;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RestaurantPolicy {

        @Builder.Default
        private Boolean depositRequired = false;
        private Integer depositMinGuests;
        private BigDecimal depositAmount;
        private String depositCurrencyCode;
        private Map<String, String> depositNotes;

        @Builder.Default
        private Boolean hasPromotion = false;
        private Map<String, String> promotionSummary;
        private Integer promotionMaxDiscountPercent;
        private Map<String, String> promotionNote;

        private List<BlackoutDateRule> blackoutRules;

        private Integer minBookingLeadTimeMinutes;
        private Integer maxHoldTimeMinutes;
        private Integer minGuestsPerBooking;

        @Builder.Default
        private Boolean vatInvoiceAvailable = false;
        private BigDecimal vatPercent;

        @Builder.Default
        private Boolean directInvoiceAvailable = false;
        private Map<String, String> invoiceNotes;

        private BigDecimal serviceChargePercent;
        private Map<String, String> serviceChargeNotes;

        @Builder.Default
        private Boolean allowOutsideFood = false;
        @Builder.Default
        private Boolean allowOutsideDrink = false;

        private Map<String, String> outsideFoodPolicy;
        private Map<String, String> outsideDrinkPolicy;

        private List<OutsideDrinkFee> outsideDrinkFees;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BlackoutDateRule {
        private BlackoutDateType dateType;
        private Integer month;
        private Integer day;
        private String fromDate;
        private String toDate;
        private Integer lunarMonth;
        private Integer lunarDay;
        private Map<String, String> description;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OutsideDrinkFee {
        private String drinkType;
        private BigDecimal feeAmount;
        private String currencyCode;
        private Map<String, String> note;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewStats {
        private Double foodScore;
        private Double spaceScore;
        private Double serviceScore;
        private Double priceScore;
        private Double locationScore;
        private List<ReviewKeywordStat> keywords;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewKeywordStat {
        private String code;
        private Map<String, String> label;
        private Integer count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PublisherInfo {
        @Indexed
        private String partnerId;
        private String partnerName;
        private String partnerEmail;
        private PartnerType partnerType;
        private Instant createdAt;
        private Instant lastUpdatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ModerationInfo {
        private RestaurantStatus status;
        private String rejectionReason;
        private String blockedReason;
        @Builder.Default
        private Integer reportCount = 0;
        private String lastActionByAdminId;
        private Instant lastActionAt;
        private Instant unlockRequestedAt;
        private String unlockRequestReason;
        private String pendingReason;

        public String getLastActionByAdminId() {
            return lastActionByAdminId;
        }

        public void setLastActionByAdminId(String lastActionByAdminId) {
            this.lastActionByAdminId = lastActionByAdminId;
        }

        public Instant getLastActionAt() {
            return lastActionAt;
        }

        public void setLastActionAt(Instant lastActionAt) {
            this.lastActionAt = lastActionAt;
        }
    }

    //
    // ENUMS
    //

    public enum RestaurantType {
        BUFFET,
        GOI_MON,
        BUFFET_VA_GOI_MON,
        BAR,
        CAFE,
        BBQ,
        LOUNGE,
        OTHER
    }

    public enum PriceLevel {
        CHEAP,
        MODERATE,
        EXPENSIVE,
        LUXURY
    }

    public enum PriceBucket {
        UNDER_150K,
        FROM_150K_TO_250K,
        FROM_250K_TO_350K,
        FROM_350K_TO_500K,
        ABOVE_500K,
        UNKNOWN
    }

    public enum ParkingFeeType {
        FREE,
        PAID,
        UNKNOWN
    }

    public enum BlackoutDateType {
        GREGORIAN_DATE,
        DATE_RANGE,
        LUNAR_DATE
    }

    public enum PartnerType {
        RESTAURANT_OWNER,
        TRAVEL_AGENCY,
        INTERNAL_ADMIN,
        OTHER
    }

    public enum RestaurantStatus {
        DRAFT,
        PENDING_REVIEW,
        APPROVED,
        REJECTED,
        BLOCKED
    }
}
