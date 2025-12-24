package com.mravel.catalog.model.doc;

import java.math.BigDecimal;
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

import com.mravel.catalog.model.doc.HotelDoc.HotelStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "hotels")
@CompoundIndexes({
        // Tìm theo destination + active
        @CompoundIndex(name = "hotel_active_destination_idx", def = "{'active':1,'destinationSlug':1}"),
        // Search theo giá & rating (minNightlyPrice là giá aggregate từ ratePlans)
        @CompoundIndex(name = "hotel_price_rating_idx", def = "{'active':1,'minNightlyPrice':1,'avgRating':-1}"),
        // Lọc theo trạng thái kiểm duyệt + destination
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

    @Field
    @Indexed
    @Builder.Default
    private Boolean active = true;

    // soft delete
    private Instant deletedAt;

    // ---------- Liên kết & vị trí ----------

    /** Destination cha – slug trong collection places (PlaceKind.DESTINATION) */
    @Indexed
    private String destinationSlug;

    /** Ví dụ: "Hà Nội", "Đà Nẵng" – để search text đơn giản */
    @Indexed
    private String cityName;

    private String districtName;
    private String wardName;
    private String addressLine;

    /** Tọa độ [longitude, latitude] */
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "hotel_loc_2dsphere_idx")
    private double[] location;

    // ---------- Thông tin cơ bản ----------

    @Indexed
    private String name;

    @Indexed(unique = true)
    private String slug; // dùng cho URL & xem chi tiết

    /** Loại hình: khách sạn, homestay, hostel, villa... */
    private HotelType hotelType;

    /** Số sao 1–5 (hoặc 0 nếu không xếp hạng) */
    private Integer starRating;

    private String shortDescription; // đoạn ngắn trên list
    private String description; // phần mô tả dài (overview / modal)

    private String phone;
    private String email;
    private String website;

    /** Giờ nhận / trả phòng chuẩn (để hiển thị) */
    private LocalTime defaultCheckInTime; // vd. 14:00
    private LocalTime defaultCheckOutTime; // vd. 12:00

    /**
     * Có hỗ trợ check-in online không (để show badge "Đã có Check-in Trực Tuyến")
     */
    @Builder.Default
    private Boolean hasOnlineCheckin = false;

    // ---------- Rating & review aggregate ----------

    @Builder.Default
    private Double avgRating = 0.0; // ví dụ 9.1
    @Builder.Default
    private Integer reviewsCount = 0; // 174 đánh giá
    private String ratingLabel; // "Xuất sắc", "Rất tốt"...

    // ---------- Giá tổng quan của khách sạn (DENORMALIZED) ----------

    /**
     * Giá tối thiểu / đêm trong tất cả roomTypes.ratePlans.
     * CHỈ dùng để sort/filter nhanh trên list – source of truth vẫn là
     * pricePerNight
     * trong từng RatePlan.
     */
    private BigDecimal minNightlyPrice;

    /** Tiền tệ – mặc định "VND" */
    private String currencyCode;

    /**
     * Giá gốc tham chiếu (trước khuyến mãi) – aggregate từ các ratePlans.
     * Dùng để hiển thị giá gạch ngang + tính % giảm trên list.
     */
    private BigDecimal referenceNightlyPrice;

    /**
     * Phần trăm giảm giá so với referenceNightlyPrice (0–100).
     * Optional – nếu null thì FE có thể tự tính lại từ referenceNightlyPrice &
     * minNightlyPrice.
     */
    private Integer discountPercent;

    /**
     * Cấu hình thuế & phí mặc định cho khách sạn (có thể override ở từng RatePlan)
     */
    private TaxAndFeeConfig taxConfig;

    /**
     * Cấu hình booking cơ bản cho khách sạn:
     * - Cho phép FULL / DEPOSIT
     * - % cọc
     * - Số phút free-cancel sau khi đặt
     */
    private BookingConfig bookingConfig;

    /**
     * Các flag filter tổng hợp từ roomTypes.ratePlans
     * (miễn phí huỷ, thanh toán tại KS, bao gồm bữa sáng...)
     */
    private HotelFilterFacets filterFacets;

    // ---------- Ảnh & nội dung ----------

    /** Gallery chính của khách sạn */
    private List<Image> images;

    /**
     * Bài giới thiệu dài, có thể chia nhiều section:
     * OVERVIEW (giới thiệu chung), STORY (lịch sử, kiến trúc, điểm nổi bật)...
     */
    private List<ContentBlock> content;

    // ---------- Tiện ích, phòng, lân cận ----------

    /** Tiện ích ở cấp khách sạn: lễ tân 24h, thang máy, wifi... */
    private List<String> amenityCodes;

    /** Các loại phòng của khách sạn */
    private List<RoomType> roomTypes;

    /** Các điểm lân cận (có thể mapping từ PlaceDoc/POI) */
    private List<NearbyPlace> nearbyPlaces;

    // ---------- Chính sách, thông tin chung, FAQ, review chi tiết ----------

    /** Chính sách & ghi chú liên quan */
    private HotelPolicy policy;

    /** Thông tin chung: số phòng, số tầng, khoảng cách tới trung tâm... */
    private GeneralInfo generalInfo;

    /** Câu hỏi thường gặp (FAQ) */
    private List<FaqItem> faqs;

    /** Thống kê review chi tiết theo hạng mục & keyword */
    private ReviewStats reviewStats;

    // ---------- Đăng tải & kiểm duyệt ----------

    /** Thông tin đối tác / người đăng khách sạn */
    private PublisherInfo publisher;

    /** Trạng thái kiểm duyệt, khóa, báo cáo… */
    private ModerationInfo moderation;

    // =====================================================================
    // SUBDOCUMENTS
    // =====================================================================

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

    /** Khối nội dung giống PlaceDoc.ContentBlock (để render bài viết) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContentBlock {
        public enum BlockType {
            HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
        }

        /** Section logic để biết block này thuộc phần nào của trang detail */
        public enum ContentSection {
            OVERVIEW, // dùng cho modal "Giới thiệu / Xem thêm"
            STORY, // bài viết dài cuối trang (lịch sử, kiến trúc, gợi ý trải nghiệm...)
            OTHER
        }

        private BlockType type;
        private ContentSection section;
        private String text; // HEADING / PARAGRAPH / QUOTE / INFOBOX
        private Image image; // cho IMAGE
        private List<Image> gallery; // cho GALLERY
        private double[] mapLocation; // cho MAP (lon, lat) nếu khác location chính
    }

    /** Thông tin các điểm lân cận (block "Xung quanh khách sạn có gì") */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NearbyPlace {
        private String placeSlug; // tham chiếu sang PlaceDoc (POI) nếu có
        private String name;
        private String category; // "Địa điểm lân cận", "Trung tâm giao thông", "Giải trí", "Khác"
        private Double distanceMeters; // khoảng cách ước tính (chim bay)
    }

    /** Loại phòng trong khách sạn */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RoomType {
        private String id; // random UUID string
        private String name; // "Standard Studio"
        private String shortDescription; // mô tả ngắn
        private String description; // mô tả chi tiết (optional)

        private Double areaSqm; // diện tích, ví dụ 22.0 m²
        private BedType bedType; // QUEEN, TWIN, KING... (dùng cho filter)
        private Integer bedsCount; // số giường (dùng cho filter)

        /**
         * Mô tả layout giường cho UI, vd: "1 giường King và 2 giường đơn".
         * Dùng để render text đẹp (không ảnh hưởng logic filter).
         */
        private String bedLayoutDescription;

        /**
         * Nếu muốn mô tả chi tiết hơn layout giường (1 King + 2 Single...).
         * Không bắt buộc phải dùng ngay, nhưng mở sẵn để sau này cần thì seed vào.
         */
        private List<BedOption> bedOptions;

        /** Sức chứa */
        private Integer maxAdults;
        private Integer maxChildren; // optional
        private Integer maxGuests; // tổng tối đa – dùng filter search

        /** Tổng số phòng loại này (inventory tĩnh) */
        private Integer totalRooms;

        /** Ảnh riêng cho room type */
        private List<Image> images;

        /** Tiện nghi riêng của phòng: nhà bếp mini, vòi tắm đứng... */
        private List<String> amenityCodes;

        /** Các gói giá / rate plan cho loại phòng này */
        private List<RatePlan> ratePlans;
    }

    /** Mô tả 1 loại giường + số lượng trong phòng (option) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BedOption {
        private BedType type;
        private Integer count;
    }

    /** Gói giá cho 1 loại phòng (room only, có bữa sáng, thanh toán tại KS...) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RatePlan {
        private String id;
        private String name;
        private BoardType boardType;
        private PaymentType paymentType;
        private Boolean refundable;
        private String cancellationPolicy;

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

        private String promoLabel;
        private Boolean showLowAvailability;

    }

    /** Cấu hình thuế/phí mặc định cho khách sạn */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TaxAndFeeConfig {
        /** VAT (%) mặc định, vd 8–10%. */
        private BigDecimal defaultVatPercent;

        /** Phí dịch vụ (%) mặc định, nếu có. */
        private BigDecimal defaultServiceFeePercent;

        /**
         * Nếu true, UI sẽ ưu tiên hiển thị giá "chưa bao gồm thuế và phí"
         * giống Traveloka (và show note bên dưới).
         */
        @Builder.Default
        private Boolean showPricePreTax = true;
    }

    /**
     * Cấu hình booking cơ bản của khách sạn (hình thức thanh toán, cọc,
     * free-cancel)
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingConfig {

        /**
         * Có cho phép user thanh toán toàn bộ (FULL) không.
         * Mặc định: true.
         */
        @Builder.Default
        private Boolean allowFullPayment = true;

        /**
         * Có cho phép user chọn hình thức đặt cọc (DEPOSIT) không.
         * Nếu false -> chỉ sử dụng FULL.
         * Mặc định: true.
         */
        @Builder.Default
        private Boolean allowDeposit = true;

        /**
         * % cọc trên tổng giá trị booking (0–100).
         * Ví dụ: 30 nghĩa là cọc 30% tổng tiền phòng.
         * Nếu null -> dùng default hệ thống (ví dụ 30% ở booking-service).
         */
        private BigDecimal depositPercent;

        /**
         * Số phút đầu sau khi tạo booking cho phép hủy và được hoàn 100%
         * số tiền đã thanh toán (áp dụng cho cả FULL/DEPOSIT).
         * Ví dụ: 30 => quy tắc “hủy trong 30 phút đầu được hoàn tiền”.
         * Nếu null -> dùng default hệ thống.
         */
        private Integer freeCancelMinutes;
    }

    /** Giảm giá theo số đêm lưu trú (Length-of-stay discount) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LengthOfStayDiscount {
        private Integer minNights; // số đêm tối thiểu
        private Integer maxNights; // có thể null = không giới hạn
        private Integer discountPercent; // giảm bao nhiêu % so với pricePerNight
    }

    /**
     * Các flag filter tổng hợp từ roomTypes.ratePlans.
     * Được tính lại mỗi khi thay đổi ratePlans.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HotelFilterFacets {
        /** Có ít nhất 1 rate plan refundable (miễn phí huỷ) */
        private Boolean hasFreeCancellation;

        /** Có ít nhất 1 rate plan paymentType = PAY_AT_HOTEL */
        private Boolean hasPayAtHotel;

        /** Có ít nhất 1 rate plan có bữa sáng (BREAKFAST_INCLUDED, HALF_BOARD, ...) */
        private Boolean hasBreakfastIncluded;
    }

    /** Chính sách & ghi chú liên quan đến khách sạn */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HotelPolicy {
        /** Giờ nhận phòng / trả phòng chuẩn (dùng cho cả block policy và info) */
        private LocalTime checkInFrom; // ví dụ 14:00
        private LocalTime checkOutUntil; // ví dụ 12:00

        /**
         * Các mục policy chi tiết: giấy tờ bắt buộc, hướng dẫn nhận phòng,
         * hút thuốc, thú cưng, đưa đón sân bay, tuổi tối thiểu...
         */
        private List<PolicyItem> items;
    }

    /** Một block policy cụ thể (dùng để render nhiều section khác nhau) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PolicyItem {
        private PolicySection section;
        private String title; // vd: "Giấy Tờ Bắt Buộc"
        private String content; // nội dung chi tiết (đa ngôn ngữ xử lý bên ngoài)
    }

    /** Thông tin chung (block "Thông tin chung") */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GeneralInfo {
        /** Tóm tắt tiện ích chính: "Máy lạnh, Lễ tân 24h, Thang máy, WiFi" */
        private String mainFacilitiesSummary;

        /** Khoảng cách đến trung tâm thành phố (km) */
        private Double distanceToCityCenterKm;

        /** Tóm tắt điểm đến phổ biến gần đây */
        private String popularAreaSummary;

        /** Tổng số phòng & số tầng của khách sạn */
        private Integer totalRooms;
        private Integer totalFloors;

        /** Những tiện nghi nổi bật khác */
        private String otherHighlightFacilities;

        /** Những địa điểm thú vị gần đó (text) */
        private String interestingPlacesSummary;
    }

    /** Câu hỏi thường gặp (FAQ) – mỗi item gồm cả câu hỏi & câu trả lời */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FaqItem {
        private String question;
        private String answer;
    }

    /** Thống kê review chi tiết (theo hạng mục & keyword) */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewStats {
        /** Điểm từng hạng mục */
        private Double cleanlinessScore; // Vệ sinh
        private Double roomAmenitiesScore; // Tiện nghi phòng
        private Double foodScore; // Đồ ăn
        private Double locationScore; // Vị trí
        private Double serviceScore; // Dịch vụ & tiện ích

        /**
         * Các keyword người dùng hay nhắc (khoảng cách, wifi, nhân viên thân thiện...)
         */
        private List<ReviewKeywordStat> keywords;
    }

    /** Thống kê keyword review: label + số lần được nhắc */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewKeywordStat {
        private String code; // "distance_to_center", "friendly_staff", "wifi"...
        private String label; // "Khoảng cách đến trung tâm", "Nhân viên thân thiện"...
        private Integer count; // ví dụ 39, 33...
    }

    // ---------- Đối tác đăng tải ----------

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

        /** Loại đối tác: chủ khách sạn, đại lý, nội bộ… */
        private PartnerType partnerType;

        /** Thời điểm đối tác tạo lần đầu */
        private Instant createdAt;

        /** Lần cuối đối tác chỉnh sửa nội dung hotel (trước khi admin duyệt) */
        private Instant lastUpdatedAt;
    }

    // ---------- Kiểm duyệt / báo cáo ----------

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ModerationInfo {
        /** Trạng thái hiện tại của hotel */
        private HotelStatus status; // DRAFT / PENDING_REVIEW / APPROVED / REJECTED / BLOCKED

        /** Lý do reject (admin nhập) */
        private String rejectionReason;

        /** Lý do block/khóa (quá nhiều báo cáo, nội dung sai phạm...) */
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
    // ENUMS
    // =====================================================================

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
        ROOM_ONLY, // Không gồm bữa sáng
        BREAKFAST_INCLUDED, // Có bữa sáng
        HALF_BOARD, // Sáng + 1 bữa
        FULL_BOARD, // 3 bữa
        ALL_INCLUSIVE
    }

    public enum PaymentType {
        PAY_AT_HOTEL,
        PREPAID
    }

    /** Loại đối tác đăng tải */
    public enum PartnerType {
        HOTEL_OWNER,
        TRAVEL_AGENCY,
        INTERNAL_ADMIN,
        OTHER
    }

    /** Trạng thái kiểm duyệt */
    public enum HotelStatus {
        DRAFT, // Đối tác tạo nhưng chưa gửi duyệt
        PENDING_REVIEW, // Đã gửi, chờ admin duyệt
        APPROVED, // Đã duyệt – hiển thị trên web
        REJECTED, // Bị từ chối – có lý do
        BLOCKED // Từng được hiển thị nhưng đã bị khóa
    }

    /** Section của từng policy item để FE render từng block riêng */
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