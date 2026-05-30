package com.mravel.catalog.model.doc;

import java.math.BigDecimal;
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

@Document(collection = "hotels")
@CompoundIndexes({
        @CompoundIndex(name = "hotel_active_destination_idx", def = "{'active':1,'destinationSlug':1}"),
        @CompoundIndex(name = "hotel_price_rating_idx", def = "{'active':1,'minNightlyPrice':1,'avgRating':-1}"),
        @CompoundIndex(name = "hotel_status_destination_idx", def = "{'active':1,'moderation.status':1,'destinationSlug':1}")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDoc {

    @Id
    private String id;

    @Builder.Default
    private Integer favoriteCount = 0;

    @Field
    @Indexed
    @Builder.Default
    private Boolean active = true;

    private Instant deletedAt;

    /** Destination cha – slug trong collection places (PlaceKind.DESTINATION) */
    @Indexed
    private String destinationSlug;

    /** Tên thành phố — localized */
    private Map<String, String> cityName;

    private Map<String, String> districtName;
    private Map<String, String> wardName;
    private Map<String, String> addressLine;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "hotel_loc_2dsphere_idx")
    private double[] location;

    @Indexed
    private Map<String, String> name;

    @Indexed(unique = true)
    private String slug;

    private HotelType hotelType;

    private Integer starRating;

    private Map<String, String> shortDescription;
    private Map<String, String> description;

    private String phone;
    private String email;
    private String website;

    private LocalTime defaultCheckInTime;
    private LocalTime defaultCheckOutTime;

    @Builder.Default
    private Boolean hasOnlineCheckin = false;

    @Builder.Default
    private Double avgRating = 0.0;
    @Builder.Default
    private Integer reviewsCount = 0;
    private Map<String, String> ratingLabel;

    private BigDecimal minNightlyPrice;
    private String currencyCode;
    private BigDecimal referenceNightlyPrice;
    private Integer discountPercent;

    private TaxAndFeeConfig taxConfig;
    private BookingConfig bookingConfig;
    private HotelFilterFacets filterFacets;

    private List<Image> images;
    private List<ContentBlock> content;

    private List<String> amenityCodes;
    private List<RoomType> roomTypes;
    private List<NearbyPlace> nearbyPlaces;

    private HotelPolicy policy;
    private GeneralInfo generalInfo;
    private List<FaqItem> faqs;
    private ReviewStats reviewStats;

    private PublisherInfo publisher;
    private ModerationInfo moderation;

    //
    // SUBDOCUMENTS
    //

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
    public static class NearbyPlace {
        private String placeSlug;
        private Map<String, String> name;
        private Map<String, String> category;
        private Double distanceMeters;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RoomType {
        private String id;
        private Map<String, String> name;
        private Map<String, String> shortDescription;
        private Map<String, String> description;

        private Double areaSqm;
        private BedType bedType;
        private Integer bedsCount;

        private Map<String, String> bedLayoutDescription;

        private List<BedOption> bedOptions;

        private Integer maxAdults;
        private Integer maxChildren;
        private Integer maxGuests;

        private Integer totalRooms;

        private List<Image> images;
        private List<String> amenityCodes;
        private List<RatePlan> ratePlans;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BedOption {
        private BedType type;
        private Integer count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RatePlan {
        private String id;
        private Map<String, String> name;
        private BoardType boardType;
        private PaymentType paymentType;
        private Boolean refundable;
        private Map<String, String> cancellationPolicy;

        private BigDecimal pricePerNight;
        private BigDecimal referencePricePerNight;
        private Integer discountPercent;

        private BigDecimal taxPercent;
        private BigDecimal serviceFeePercent;
        @Builder.Default
        private Boolean priceIncludesTax = false;
        @Builder.Default
        private Boolean priceIncludesServiceFee = false;

        private List<LengthOfStayDiscount> lengthOfStayDiscounts;

        private Map<String, String> promoLabel;
        private Boolean showLowAvailability;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TaxAndFeeConfig {
        private BigDecimal defaultVatPercent;
        private BigDecimal defaultServiceFeePercent;
        @Builder.Default
        private Boolean showPricePreTax = true;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingConfig {
        @Builder.Default
        private Boolean allowFullPayment = true;
        @Builder.Default
        private Boolean allowDeposit = true;
        private BigDecimal depositPercent;
        private Integer freeCancelMinutes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LengthOfStayDiscount {
        private Integer minNights;
        private Integer maxNights;
        private Integer discountPercent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HotelFilterFacets {
        private Boolean hasFreeCancellation;
        private Boolean hasPayAtHotel;
        private Boolean hasBreakfastIncluded;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HotelPolicy {
        private LocalTime checkInFrom;
        private LocalTime checkOutUntil;
        private List<PolicyItem> items;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PolicyItem {
        private PolicySection section;
        private Map<String, String> title;
        private Map<String, String> content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GeneralInfo {
        private Map<String, String> mainFacilitiesSummary;
        private Double distanceToCityCenterKm;
        private Map<String, String> popularAreaSummary;
        private Integer totalRooms;
        private Integer totalFloors;
        private Map<String, String> otherHighlightFacilities;
        private Map<String, String> interestingPlacesSummary;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FaqItem {
        private Map<String, String> question;
        private Map<String, String> answer;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewStats {
        private Double cleanlinessScore;
        private Double roomAmenitiesScore;
        private Double foodScore;
        private Double locationScore;
        private Double serviceScore;
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
        private HotelStatus status;
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

    public enum HotelType {
        HOTEL,
        HOMESTAY,
        HOSTEL,
        APARTMENT,
        VILLA,
        RESORT,
        GUEST_HOUSE,
        OTHER
    }

    public enum BedType {
        SINGLE,
        DOUBLE,
        TWIN,
        QUEEN,
        KING,
        BUNK,
        MULTIPLE
    }

    public enum BoardType {
        ROOM_ONLY,
        BREAKFAST_INCLUDED,
        HALF_BOARD,
        FULL_BOARD,
        ALL_INCLUSIVE
    }

    public enum PaymentType {
        PAY_AT_HOTEL,
        PREPAID
    }

    public enum PartnerType {
        HOTEL_OWNER,
        TRAVEL_AGENCY,
        INTERNAL_ADMIN,
        OTHER
    }

    public enum HotelStatus {
        DRAFT,
        PENDING_REVIEW,
        APPROVED,
        REJECTED,
        BLOCKED
    }

    public enum PolicySection {
        CHECKIN_CHECKOUT,
        REQUIRED_DOCUMENTS,
        GENERAL_CHECKIN_GUIDE,
        MIN_AGE,
        EARLY_CHECKIN,
        LATE_CHECKOUT,
        SMOKING,
        PETS,
        AIRPORT_TRANSFER,
        OTHER
    }
}
