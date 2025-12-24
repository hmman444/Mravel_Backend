package com.mravel.catalog.model.doc;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

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
    // Tìm theo destination + active
    @CompoundIndex(
        name = "restaurant_active_destination_idx",
        def = "{'active':1,'destinationSlug':1}"
    ),
    // Search theo price bucket & rating
    @CompoundIndex(
        name = "restaurant_price_rating_idx",
        def = "{'active':1,'priceBucket':1,'avgRating':-1}"
    )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDoc {

    @Id
    private String id;

    @Field
    @Indexed
    @Builder.Default
    private Boolean active = true;

    private Instant deletedAt;

    // ---------- Liên kết & vị trí ----------

    /** Destination cha – slug trong collection places (PlaceKind.DESTINATION) */
    @Indexed
    private String destinationSlug;

    /**
     * Optional: tham chiếu tới 1 PlaceDoc khác (ví dụ: nhà hàng nằm trong hotel / POI cụ thể).
     * Có thể để null nếu chỉ gắn với destination.
     */
    @Indexed
    private String parentPlaceSlug;

    /** Ví dụ: "Hội An" – để search text đơn giản */
    @Indexed
    private String cityName;

    private String districtName;
    private String wardName;
    private String addressLine;

    /** Tọa độ [longitude, latitude] */
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "restaurant_loc_2dsphere_idx")
    private double[] location;

    // ---------- Thông tin cơ bản ----------

    @Indexed
    private String name;

    @Indexed(unique = true)
    private String slug; // dùng cho URL & xem chi tiết

    /** Loại hình phục vụ: Buffet, Gọi món, Bar, Cafe... */
    private RestaurantType restaurantType;

    /** Loại ẩm thực: Việt, Á, Hàn, Nhật, Bắc/Trung/Nam... */
    private List<CuisineTag> cuisines;

    private String shortDescription; // đoạn ngắn trên list
    private String description;      // mô tả dài

    // Liên hệ
    private String phone;
    private String email;
    private String website;
    private String facebookPage;
    private String bookingHotline;

    // ---------- Giá / khoảng giá ----------

    /** Khoảng giá / người (đồng bộ với filter PasGo) */
    private BigDecimal minPricePerPerson;
    private BigDecimal maxPricePerPerson;

    /** Tiền tệ – mặc định "VND" */
    private String currencyCode;

    /** Mức giá tổng quan: RẺ / TRUNG BÌNH / CAO */
    private PriceLevel priceLevel;

    /** Bucket giá để filter nhanh: <150k, 150-250k,... */
    private PriceBucket priceBucket;

    // ---------- Giờ hoạt động ----------

    /**
     * Giờ mở cửa theo từng ngày trong tuần.
     * Ví dụ:
     *  - Thứ hai: 11:00 - 22:00
     *  - ...
     */
    private List<OpeningHour> openingHours;

    // ---------- Phù hợp / Không gian / Sức chứa ----------

    /** Các trường hợp sử dụng: Ăn gia đình, Sinh nhật, Liên hoan,... */
    private List<SuitableFor> suitableFor;

    /** Không gian: ấm cúng, sang trọng, ngoài trời,... */
    private List<AmbienceTag> ambience;

    /** Thông tin sức chứa & phòng riêng */
    private CapacityInfo capacity;

    // ---------- Món đặc sắc & menu ----------

    /** Danh sách món đặc sắc (highlight) */
    private List<SignatureDish> signatureDishes;

    /** Ảnh menu (bảng giá) – giống PasGo hiển thị ảnh có giá bên trong */
    private List<Image> menuImages;

    /** Menu cấu trúc (optional, phục vụ tiểu luận & mở rộng sau này) */
    private List<MenuSection> menuSections;

    // ---------- Đỗ xe ----------

    private ParkingInfo parking;

    // ---------- Tiện ích (amenities) ----------

    /**
     * Danh sách code tiện ích mà nhà hàng CÓ.
     * FE/BE có master list tất cả tiện ích, khi render sẽ:
     *  - code có trong list -> tick xanh
     *  - code không có      -> X đỏ
     */
    private List<String> amenityCodes;

    // ---------- Ảnh & content dài ----------

    /** Gallery chính của nhà hàng */
    private List<Image> images;

    /**
     * Bài giới thiệu dài, có thể chia nhiều section:
     * OVERVIEW (giới thiệu chung), STORY (bài viết PR dài)...
     */
    private List<ContentBlock> content;

    // ---------- Policy & quy định đặt chỗ ----------

    private RestaurantPolicy policy;

    // ---------- Rating & review ----------

    @Builder.Default
    private Double avgRating = 0.0;

    @Builder.Default
    private Integer reviewsCount = 0;

    private String ratingLabel; // "Rất tốt", "Xuất sắc"...

    private ReviewStats reviewStats;

    // ---------- Đối tác & kiểm duyệt ----------

    private PublisherInfo publisher;
    private ModerationInfo moderation;

    private List<TableType> tableTypes;

    /** Cấu hình booking riêng cho nhà hàng (slot, duration, hủy/hoàn, pending expire...) */
    private BookingConfig bookingConfig;

    // =====================================================================
    //                              SUBDOCUMENTS
    // =====================================================================

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class TableType {
        private String id;                 // "tb-2", "tb-4-vip" ...
        private String name;               // "Bàn 2", "Bàn 4 VIP"
        private Integer seats;             // 2/4/6...
        private Integer minPeople;         // optional
        private Integer maxPeople;         // optional

        /** Tổng số bàn của loại này (tồn kho tĩnh) */
        private Integer totalTables;

        /** Tiền cọc cho 1 bàn loại này */
        private BigDecimal depositPrice;
        private String currencyCode;       // "VND"

        /** Loại bàn đặc biệt (để UI show badge) */
        @Builder.Default private Boolean vip = false;
        @Builder.Default private Boolean privateRoom = false;

        /** Nếu tableType này có duration riêng thì override */
        private List<Integer> allowedDurationsMinutes; // vd: [60, 90, 120]
        private Integer defaultDurationMinutes;        // vd: 90

        private String note;               // mô tả ngắn cho UI
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookingConfig {

        /** Booking slot size (phút). Ví dụ 15/30. Khuyến nghị 30 cho tiểu luận */
        @Builder.Default
        private Integer slotMinutes = 30;

        /** Danh sách duration user có thể chọn (nếu tableType không override) */
        @Builder.Default
        private List<Integer> allowedDurationsMinutes = List.of(60, 90, 120);

        /** Default duration nếu user không chọn */
        @Builder.Default
        private Integer defaultDurationMinutes = 90;

        /** Đặt trước tối thiểu bao nhiêu phút (lead time) */
        @Builder.Default
        private Integer minBookingLeadTimeMinutes = 60;

        /**
         * Cho phép khách đến trễ tối đa bao nhiêu phút so với reservationTime.
         * (khác với pending payment expire)
         */
        @Builder.Default
        private Integer graceArrivalMinutes = 15;

        /** Số phút đầu được hủy và hoàn 100% cọc */
        @Builder.Default
        private Integer freeCancelMinutes = 30;

        /** Pending payment tối đa bao nhiêu phút rồi auto-cancel */
        @Builder.Default
        private Integer pendingPaymentExpireMinutes = 30;

        /**
         * Rule cho booking res: luôn là đặt cọc (DEPOSIT only)
         * (để nhất quán với BookingBase.payOption)
         */
        @Builder.Default
        private Boolean depositOnly = true;

        /** Giới hạn số bàn tối đa/booking (để tránh spam giữ chỗ) */
        @Builder.Default
        private Integer maxTablesPerBooking = 5;
    }

    /** Ảnh dùng chung (giống HotelDoc.Image) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private String url;
        private String caption;
        @Builder.Default
        private Boolean cover = false;
        @Builder.Default
        private Integer sortOrder = 0;
    }

    /** Tag ẩm thực (cuisine) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CuisineTag {
        private String code;     // "VIETNAMESE", "ASIAN", "KOREAN", "NORTH",...
        private String name;     // "Việt", "Hàn Quốc", "Món Bắc",...
        private String region;   // "NORTH", "CENTRAL", "SOUTH", "ASIA", "WESTERN"... (optional)
    }

    /** Giờ mở cửa cho một ngày trong tuần */
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

    /** Trường hợp sử dụng (Ăn gia đình, Liên hoan, Sinh nhật,...) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SuitableFor {
        private String code;    // "FAMILY", "FRIENDS", "COMPANY_PARTY",...
        private String label;   // "Ăn gia đình", "Tụ tập bạn bè",...
    }

    /** Không gian / ambience (ấm cúng, sang trọng, ngoài trời,...) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AmbienceTag {
        private String code;    // "COZY", "ELEGANT", "OUTDOOR",...
        private String label;   // "Ấm cúng", "Sang trọng",...
    }

    /** Thông tin sức chứa & phòng riêng */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CapacityInfo {
        /** Tổng số khách tối đa nhà hàng có thể phục vụ */
        private Integer totalCapacity;

        /** Số khách tối đa cho 1 nhóm lớn (gợi ý) */
        private Integer maxGroupSize;

        /** Có phòng riêng không */
        @Builder.Default
        private Boolean hasPrivateRooms = false;

        private Integer privateRoomCount;
        private Integer maxPrivateRoomCapacity;

        /** Có khu vực ngoài trời / sân vườn không */
        @Builder.Default
        private Boolean hasOutdoorSeating = false;
    }

    /** Món đặc sắc */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignatureDish {
        private String name;
        private String description;
        private BigDecimal estimatedPrice;  // optional
        @Builder.Default
        private Boolean highlight = true;
    }

    /** 1 section menu: Khai vị, Món chính, Combo,... */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuSection {
        private String code;       // "STARTER", "MAIN_COURSE", "DESSERT", "DRINK", "BUFFET",...
        private String name;       // "Khai vị", "Món chính",...

        private List<MenuItem> items;
    }

    /** 1 món trong menu */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuItem {
        private String name;
        private String description;

        private BigDecimal priceFrom;
        private BigDecimal priceTo;

        private String unit;       // "phần", "combo 2 người",...

        @Builder.Default
        private Boolean combo = false;

        @Builder.Default
        private Boolean buffetItem = false;

        /** Tag như: "hải sản", "bò", "gà", "chay"... */
        private List<String> tags;
    }

    /** Thông tin đỗ xe */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ParkingInfo {
        // Ô tô
        @Builder.Default
        private Boolean hasCarParking = false;
        private String carParkingLocation;
        private ParkingFeeType carParkingFeeType;
        private BigDecimal carParkingFeeAmount;

        // Xe máy
        @Builder.Default
        private Boolean hasMotorbikeParking = false;
        private String motorbikeParkingLocation;
        private ParkingFeeType motorbikeParkingFeeType;
        private BigDecimal motorbikeParkingFeeAmount;

        private String notes;
    }

    /** Content block dài (giống HotelDoc.ContentBlock) */
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
            OVERVIEW,   // Giới thiệu chung
            STORY,      // Bài viết dài / PR
            OTHER
        }

        private BlockType type;
        private ContentSection section;
        private String text;          // HEADING / PARAGRAPH / QUOTE / INFOBOX
        private Image image;          // cho IMAGE
        private List<Image> gallery;  // cho GALLERY
        private double[] mapLocation; // cho MAP (lon, lat) nếu khác location chính
    }

    // ---------- Policy (quy định đặt chỗ, ưu đãi, hóa đơn, phí...) ----------

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RestaurantPolicy {

        // --- Đặt cọc ---
        @Builder.Default
        private Boolean depositRequired = false;
        private Integer depositMinGuests;
        private BigDecimal depositAmount;
        private String depositCurrencyCode;
        private String depositNotes;

        // --- Ưu đãi & ngày ngoại lệ ---
        @Builder.Default
        private Boolean hasPromotion = false;
        private String promotionSummary;
        private Integer promotionMaxDiscountPercent;
        private String promotionNote;

        private List<BlackoutDateRule> blackoutRules;

        // --- Thời gian đặt / giữ chỗ ---
        private Integer minBookingLeadTimeMinutes; // đặt trước tối thiểu, vd: 60'
        private Integer maxHoldTimeMinutes;        // giữ chỗ tối đa, vd: 20'
        private Integer minGuestsPerBooking;       // "Không quy định" -> null

        // --- Hóa đơn ---
        @Builder.Default
        private Boolean vatInvoiceAvailable = false;
        private BigDecimal vatPercent;

        @Builder.Default
        private Boolean directInvoiceAvailable = false;
        private String invoiceNotes;

        // --- Phí phục vụ ---
        private BigDecimal serviceChargePercent;
        private String serviceChargeNotes;

        // --- Mang đồ từ ngoài vào ---
        @Builder.Default
        private Boolean allowOutsideFood = false;
        @Builder.Default
        private Boolean allowOutsideDrink = false;

        private String outsideFoodPolicy;
        private String outsideDrinkPolicy;

        private List<OutsideDrinkFee> outsideDrinkFees;
    }

    /** Quy tắc ngày ngoại lệ (blackout) cho ưu đãi */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BlackoutDateRule {
        private BlackoutDateType dateType; // GREGORIAN_DATE / DATE_RANGE / LUNAR_DATE

        private Integer month;   // nếu là ngày cố định dương lịch
        private Integer day;

        private String fromDate; // ISO 8601: "2025-01-01"
        private String toDate;

        private Integer lunarMonth; // cho Âm lịch (nếu muốn dùng)
        private Integer lunarDay;

        private String description;
    }

    /** Phụ phí cho đồ uống mang từ ngoài vào */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OutsideDrinkFee {
        private String drinkType;      // "WINE", "SPIRITS", "BEER", "OTHER"
        private BigDecimal feeAmount;  // 400000, 200000,...
        private String currencyCode;   // "VND"
        private String note;
    }

    // ---------- Review stats ----------

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewStats {
        /** Điểm từng hạng mục */
        private Double foodScore;
        private Double spaceScore;
        private Double serviceScore;
        private Double priceScore;
        private Double locationScore;

        /** Các keyword người dùng hay nhắc */
        private List<ReviewKeywordStat> keywords;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewKeywordStat {
        private String code;   // "good_food", "friendly_staff", "family_friendly",...
        private String label;  // "Món ăn ngon", "Nhân viên thân thiện",...
        private Integer count; // số lần được nhắc
    }

    // ---------- Đối tác / publisher ----------

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PublisherInfo {
        /** Id user/partner bên user-service */
        @Indexed
        private String partnerId;

        /** Để hiển thị nhanh mà không cần call user-service */
        private String partnerName;
        private String partnerEmail;

        /** Loại đối tác: chủ nhà hàng, đại lý, nội bộ… */
        private PartnerType partnerType;

        /** Thời điểm đối tác tạo lần đầu */
        private Instant createdAt;

        /** Lần cuối đối tác chỉnh sửa nội dung restaurant */
        private Instant lastUpdatedAt;
    }

    // ---------- Kiểm duyệt / báo cáo ----------

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ModerationInfo {
        /** Trạng thái hiện tại của restaurant */
        private RestaurantStatus status; // DRAFT / PENDING_REVIEW / APPROVED / REJECTED / BLOCKED

        /** Lý do reject (admin nhập) */
        private String rejectionReason;

        /** Lý do block/khóa */
        private String blockedReason;

        /** Số lần report từ người dùng */
        @Builder.Default
        private Integer reportCount = 0;

        /** Admin xử lý lần gần nhất */
        private String lastActionByAdminId;
        private Instant lastActionAt;

        private Instant unlockRequestedAt;
        private String unlockRequestReason;

        private String pendingReason;
    }

    // =====================================================================
    //                                 ENUMS
    // =====================================================================

    /** Loại hình nhà hàng chính */
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

    /** Mức giá tổng quan */
    public enum PriceLevel {
        CHEAP,          // rẻ
        MODERATE,       // trung bình
        EXPENSIVE,      // cao
        LUXURY          // rất cao
    }

    /** Bucket giá theo khoảng để filter ("<150k", "150-250k"...) */
    public enum PriceBucket {
        UNDER_150K,
        FROM_150K_TO_250K,
        FROM_250K_TO_350K,
        FROM_350K_TO_500K,
        ABOVE_500K,
        UNKNOWN
    }

    /** Loại phí giữ xe */
    public enum ParkingFeeType {
        FREE,
        PAID,
        UNKNOWN
    }

    /** Loại quy tắc ngày ngoại lệ */
    public enum BlackoutDateType {
        GREGORIAN_DATE, // ngày dương lịch cố định
        DATE_RANGE,     // khoảng từ ngày - đến ngày
        LUNAR_DATE      // ngày âm lịch
    }

    /** Loại đối tác đăng tải */
    public enum PartnerType {
        RESTAURANT_OWNER,
        TRAVEL_AGENCY,
        INTERNAL_ADMIN,
        OTHER
    }

    /** Trạng thái kiểm duyệt của restaurant */
    public enum RestaurantStatus {
        DRAFT,
        PENDING_REVIEW,
        APPROVED,
        REJECTED,
        BLOCKED
    }
}