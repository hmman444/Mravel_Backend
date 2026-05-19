// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedHoiAnRestaurants.java
package com.mravel.catalog.bootstrap.restaurant;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.doc.RestaurantDoc.AmbienceTag;
import com.mravel.catalog.model.doc.RestaurantDoc.BlackoutDateRule;
import com.mravel.catalog.model.doc.RestaurantDoc.CapacityInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.RestaurantDoc.CuisineTag;
import com.mravel.catalog.model.doc.RestaurantDoc.Image;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuItem;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuSection;
import com.mravel.catalog.model.doc.RestaurantDoc.ModerationInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.OpeningHour;
import com.mravel.catalog.model.doc.RestaurantDoc.OutsideDrinkFee;
import com.mravel.catalog.model.doc.RestaurantDoc.ParkingFeeType;
import com.mravel.catalog.model.doc.RestaurantDoc.ParkingInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.PartnerType;
import com.mravel.catalog.model.doc.RestaurantDoc.PriceBucket;
import com.mravel.catalog.model.doc.RestaurantDoc.PriceLevel;
import com.mravel.catalog.model.doc.RestaurantDoc.PublisherInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantPolicy;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantStatus;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantType;
import com.mravel.catalog.model.doc.RestaurantDoc.ReviewStats;
import com.mravel.catalog.model.doc.RestaurantDoc.SignatureDish;
import com.mravel.catalog.model.doc.RestaurantDoc.SuitableFor;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;

@Configuration
public class SeedHoiAnRestaurants {

    @Bean
    CommandLineRunner seedHoiAnRestaurantsRunner(RestaurantDocRepository repo) {
        return args -> {
            System.out.println(">>> [SeedHoiAnRestaurants] start");

            RestaurantDoc redBean = restaurantRedBeanHoiAn();
            repo.findBySlugAndActiveTrue(redBean.getSlug())
                .ifPresent(existing -> redBean.setId(existing.getId()));
            repo.save(redBean);
            System.out.println(">>> upsert: " + redBean.getSlug());

            RestaurantDoc temple = restaurantTheTempleHoiAn();
            repo.findBySlugAndActiveTrue(temple.getSlug())
                .ifPresent(existing -> temple.setId(existing.getId()));
            repo.save(temple);
            System.out.println(">>> upsert: " + temple.getSlug());

            System.out.println(">>> [SeedHoiAnRestaurants] done");
        };
    }

    // =====================================================================
    //                       HOI AN - HÙNG VƯƠNG
    // =====================================================================

    private static RestaurantDoc restaurantRedBeanHoiAn() {
        String slug = "red-bean-hoi-an-hung-vuong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-1-normal-2706684530014.webp",
                "Không gian Red Bean Hoi An - Hùng Vương", true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-2-normal-2706684769957.webp",
                "", false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-3-normal-2706684869958.webp",
                "", false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-4-normal-2706684969959.webp",
                "", false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-5-normal-2706685069960.webp",
                "", false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-6-normal-2706685169961.webp",
                "", false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-7-normal-2706685269962.webp",
                "", false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-8-normal-2706685369963.webp",
                "", false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-9-normal-2706685469964.webp",
                "", false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-10-normal-2706685569965.webp",
                "", false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-11-normal-2706685669966.webp",
                "", false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-12-normal-2706685769967.webp",
                "", false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name("Việt Nam")
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name("Châu Á")
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_VIET_ASIAN")
                .name("Buffet & Gọi món Việt - Á")
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 11:00 - 22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        "Ăn gia đình"),
            suitable("FRIENDS",       "Tụ tập bạn bè"),
            suitable("COMPANY_PARTY", "Liên hoan văn phòng"),
            suitable("GROUP_MEETING", "Họp nhóm"),
            suitable("BIRTHDAY",      "Tổ chức sinh nhật"),
            suitable("COMPANY_EVENT", "Tiệc công ty"),
            suitable("BUSINESS_MEAL", "Tiếp khách"),
            suitable("COUPLE",        "Hẹn hò"),
            suitable("ANNIVERSARY",   "Kỷ niệm ngày đặc biệt"),
            suitable("OFFICE_LUNCH",  "Ăn trưa văn phòng")
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",   "Trang nhã"),
            ambience("COZY",      "Ấm cúng"),
            ambience("LUXURIOUS", "Sang trọng")
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(20)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(20)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish("Gỏi tôm ngó sen"),
            signatureDish("Hải sản nướng xiên"),
            signatureDish("Cá chẽm hấp"),
            signatureDish("Cơm gà Hội An"),
            signatureDish("Gà nướng ống tre"),
            signatureDish("Tôm xào sốt me"),
            signatureDish("Cá nướng lá chuối"),
            signatureDish("Tam hữu"),
            signatureDish("Súp gà Hội An")
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-1.jpg",
                "", false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-2.jpg",
                "", false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-3.jpg",
                "", false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-4.jpg",
                "", false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-5.jpg",
                "", false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-6.jpg",
                "", false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-7.jpg",
                "", false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-8.jpg",
                "", false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-9.jpg",
                "", false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-10.jpg",
                "", false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-11.jpg",
                "", false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-12.jpg",
                "", false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-13.jpg",
                "", false, 12)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name("Món chính")
                .items(List.of(
                    MenuItem.builder()
                        .name("Cơm gà Hội An")
                        .description("Cơm gà đặc sản Hội An, dùng kèm rau thơm và nước mắm chua ngọt.")
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit("phần")
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("chicken", "rice", "hoi-an-special"))
                        .build(),
                    MenuItem.builder()
                        .name("Hải sản nướng xiên")
                        .description("Các loại hải sản tươi nướng xiên, ướp gia vị đậm đà.")
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit("phần")
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grill"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation("Bãi đỗ xe tại nhà hàng")
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation("Bãi đỗ xe tại nhà hàng")
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes("Có bảo vệ hướng dẫn, miễn phí cho khách.")
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "MC_SERVICE",
            "FREE_CAR_PARKING",
            "OUTDOOR_TABLE",
            "FREE_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI"    
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, "Tóm tắt Red Bean Hoi An - Hùng Vương"),
            paragraph(ContentSection.OVERVIEW,
                "Nhà hàng Red Bean Hoi An là điểm đến lý tưởng cho những ai yêu thích ẩm thực Việt - Á. " +
                "Nằm trong khuôn viên La Siesta Hoi An Resort & Spa, nhà hàng mang đến trải nghiệm ẩm thực đa dạng " +
                "với các món ăn truyền thống Việt Nam và châu Á, được chế biến tinh tế và sáng tạo."),
            paragraph(ContentSection.OVERVIEW,
                "Không gian của Red Bean được thiết kế theo phong cách Á Đông truyền thống, " +
                "kết hợp với những nét hiện đại, tạo nên một bầu không khí ấm cúng và sang trọng, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty hay tiếp khách đối tác."),
            heading(ContentSection.OVERVIEW, "Điểm đặc trưng"),
            paragraph(ContentSection.OVERVIEW,
                "Red Bean Hoi An thu hút thực khách bởi thực đơn phong phú các món Việt - Á, " +
                "trong đó có nhiều đặc sản Hội An được biến tấu sáng tạo nhưng vẫn giữ trọn hương vị truyền thống."),
            paragraph(ContentSection.OVERVIEW,
                "Không gian ấm cúng, trang nhã cùng chất lượng phục vụ chuyên nghiệp " +
                "giúp Red Bean trở thành lựa chọn yêu thích của nhiều gia đình, nhóm bạn và khách nghỉ dưỡng tại La Siesta Hoi An Resort & Spa."),
            mapBlock(ContentSection.OVERVIEW, new double[]{108.3175, 15.8795})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  "Không áp dụng ưu đãi ngày 1/1"),
            blackoutGregorian(2, 14, "Không áp dụng ưu đãi ngày 14/2"),
            blackoutGregorian(3, 8,  "Không áp dụng ưu đãi ngày 8/3"),
            blackoutGregorian(4, 30, "Không áp dụng ưu đãi ngày 30/4"),
            blackoutGregorian(5, 1,  "Không áp dụng ưu đãi ngày 1/5"),
            blackoutGregorian(6, 1,  "Không áp dụng ưu đãi ngày 1/6"),
            blackoutGregorian(9, 2,  "Không áp dụng ưu đãi ngày 2/9")
            // Các rule âm lịch đặc biệt có thể thêm sau nếu cần
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note("Phụ phí rượu mạnh: 400.000đ/chai.")
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note("Phụ phí rượu vang: 200.000đ/chai.")
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(5)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes("Áp dụng cho booking từ 5 khách trở lên hoặc yêu cầu trang trí đặc biệt.")
            .hasPromotion(true)
            .promotionSummary("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.")
            .promotionMaxDiscountPercent(20)
            .promotionNote("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.")
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes("Nhà hàng có xuất hóa đơn VAT theo yêu cầu, không xuất hóa đơn trực tiếp.")
            .serviceChargePercent(null)
            .serviceChargeNotes("Không thu phí phục vụ riêng.")
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy("Không được mang thức ăn từ ngoài vào.")
            .outsideDrinkPolicy("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.")
            .outsideDrinkFees(outsideDrinkFees)
            .build();

        // ---------- Review stats ----------
        ReviewStats reviewStats = ReviewStats.builder()
            .foodScore(4.7)
            .spaceScore(4.6)
            .serviceScore(4.8)
            .priceScore(4.3)
            .locationScore(4.5)
            .keywords(List.of(
                keyword("good_food", "Món ăn ngon", 120),
                keyword("friendly_staff", "Nhân viên thân thiện", 80),
                keyword("nice_space", "Không gian đẹp", 60)
            ))
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
                                .partnerId("11")
                                .partnerName("Ông Trùm Bất Động Sản")
                                .partnerEmail("22110377@student.hcmute.edu.vn")
                                .partnerType(PartnerType.RESTAURANT_OWNER)
                                .createdAt(now)
                                .lastUpdatedAt(now)
                                .build();

        ModerationInfo moderation = ModerationInfo.builder()
            .status(RestaurantStatus.APPROVED)
            .rejectionReason(null)
            .blockedReason(null)
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();
        
        // ---------- Table types (bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-2")
                .name("Bàn 2")
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(10)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note("Phù hợp cặp đôi, nhóm nhỏ.")
                .build(),

            TableType.builder()
                .id("tb-4")
                .name("Bàn 4")
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note("Phù hợp gia đình/nhóm bạn.")
                .build(),

            TableType.builder()
                .id("tb-6-vip")
                .name("Bàn 6 VIP")
                .seats(6)
                .minPeople(5)
                .maxPeople(6)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note("Vị trí đẹp, ưu tiên phục vụ.")
                .build(),

            TableType.builder()
                .id("pr-10")
                .name("Phòng riêng 10 khách")
                .seats(10)
                .minPeople(8)
                .maxPeople(12)
                .totalTables(2) // coi như 2 phòng riêng
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note("Phòng riêng, phù hợp tiếp khách/tiệc nhỏ.")
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(60)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("hoi-an")
            .parentPlaceSlug("la-siesta-hoi-an-resort-spa")
            .cityName("Hội An")
            .districtName("Tp. Hội An")
            .wardName("P. Thanh Hà")
            .addressLine("Tầng trệt, La Siesta Hội An Resort & Spa, Số 132, Đ. Hùng Vương, P. Thanh Hà, Tp. Hội An")
            .location(new double[]{108.3175, 15.8795})
            .name("Red Bean Hoi An - Hùng Vương")
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription("Buffet & gọi món Việt - Á trong khuôn viên La Siesta Hoi An Resort & Spa.")
            .description(
                "Red Bean Hoi An - Hùng Vương là nhà hàng chuyên ẩm thực Việt - Á, " +
                "nằm tại tầng trệt La Siesta Hoi An Resort & Spa. " +
                "Nhà hàng mang đến trải nghiệm ẩm thực đa dạng với các món đặc sản Hội An " +
                "và nhiều món ăn châu Á được chế biến tinh tế, sáng tạo, phù hợp cho nhiều dịp khác nhau."
            )
            .phone("1900xxxxxx")
            .email("reservation@redbeanhoian.com")
            .website("https://example.com/red-bean-hoi-an")
            .facebookPage("https://facebook.com/redbeanhoian")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("350000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.MODERATE)
            .priceBucket(PriceBucket.FROM_150K_TO_250K) // hoặc FROM_250K_TO_350K tùy bạn map
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
            .menuImages(menuImages)
            .menuSections(menuSections)
            .parking(parking)
            .amenityCodes(amenityCodes)
            .images(images)
            .content(content)
            .policy(policy)
            .avgRating(4.7)
            .reviewsCount(150)
            .ratingLabel("Rất tốt")
            .reviewStats(reviewStats)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }

    // =====================================================================
    //                 HOI AN - THE TEMPLE RESTAURANT & LOUNGE
    // =====================================================================

    private static RestaurantDoc restaurantTheTempleHoiAn() {
        String slug = "the-temple-restaurant-lounge-hung-vuong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-1-normal-2706734969988.webp",
                "", true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-2-normal-2706735269989.webp",
                "", false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-3-normal-2706735169990.webp",
                "", false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-4-normal-2706735369991.webp",
                "", false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-5-normal-2706735469992.webp",
                "", false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-6-normal-2706735569993.webp",
                "", false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-7-normal-2706735669994.webp",
                "", false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-8-normal-2706735769995.webp",
                "", false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-9-normal-2706735869996.webp",
                "", false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-10-normal-2706735969997.webp",
                "", false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-11-normal-2706736069998.webp",
                "", false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-12-normal-2706736169999.webp",
                "", false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("EUROPEAN")
                .name("Âu")
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("FRENCH")
                .name("Pháp")
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("ITALIAN")
                .name("Ý")
                .region("EUROPE")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 11:00 - 22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        "Ăn gia đình"),
            suitable("FRIENDS",       "Tụ tập bạn bè"),
            suitable("GROUP_MEETING", "Họp nhóm"),
            suitable("BIRTHDAY",      "Tổ chức sinh nhật"),
            suitable("COMPANY_EVENT", "Tiệc công ty"),
            suitable("BUSINESS_MEAL", "Tiếp khách"),
            suitable("COUPLE",        "Hẹn hò"),
            suitable("ANNIVERSARY",   "Kỷ niệm ngày đặc biệt")
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("LUXURIOUS", "Sang trọng"),
            ambience("COZY",      "Ấm cúng"),
            ambience("ROMANTIC",  "Lãng mạn")
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(70)
            .maxGroupSize(15)
            .hasPrivateRooms(false)      // res này không có "Phòng riêng"
            .privateRoomCount(null)
            .maxPrivateRoomCapacity(null)
            .hasOutdoorSeating(true)     // có "Bàn ngoài trời"
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish("Grilled Seafood Platter"),
            signatureDish("Yellowfin Tuna Steak"),
            signatureDish("Smoked Salmon Tartar"),
            signatureDish("Crab with Jalapeno Sweet corn Soup"),
            signatureDish("Slow cooked Lamb Shank Pappadelle"),
            signatureDish("Grilled Beef tenderloin with Truffle Mashed Potato")
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-1.jpg",
                "", false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-2.jpg",
                "", false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-3.jpg",
                "", false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-4.jpg",
                "", false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-5.jpg",
                "", false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-6.jpg",
                "", false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-7.jpg",
                "", false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-8.jpg",
                "", false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-9.jpg",
                "", false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-10.jpg",
                "", false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-11.jpg",
                "", false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-12.jpg",
                "", false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-13.jpg",
                "", false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-14.jpg",
                "", false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-15.jpg",
                "", false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-16.jpg",
                "", false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-17.jpg",
                "", false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-18.jpg",
                "", false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-19.jpg",
                "", false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-20.jpg",
                "", false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-21.jpg",
                "", false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-22.jpg",
                "", false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-23.jpg",
                "", false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-24.jpg",
                "", false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-25.jpg",
                "", false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-26.jpg",
                "", false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-27.jpg",
                "", false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-28.jpg",
                "", false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-29.jpg",
                "", false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-30.jpg",
                "", false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-31.jpg",
                "", false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-32.jpg",
                "", false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-33.jpg",
                "", false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-34.jpg",
                "", false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-35.jpg",
                "", false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-36.jpg",
                "", false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-37.jpg",
                "", false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-38.jpg",
                "", false, 37)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name("Món chính")
                .items(List.of(
                    MenuItem.builder()
                        .name("Grilled Beef tenderloin with Truffle Mashed Potato")
                        .description("Thịt thăn bò nướng dùng kèm khoai tây nghiền truffle, rau củ và sốt đặc biệt.")
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit("phần")
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "steak", "european"))
                        .build(),
                    MenuItem.builder()
                        .name("Grilled Seafood Platter")
                        .description("Tháp hải sản nướng tổng hợp với tôm, mực, cá, sò điệp...")
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit("phần")
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grill", "sharing"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation("Bãi đỗ xe tại nhà hàng")
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation("Bãi đỗ xe tại nhà hàng")
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes("Có bảo vệ hướng dẫn, miễn phí cho khách.")
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        // Chỉ giữ các tiện ích đang "màu xanh" (có) bên PasGo
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "MC_SERVICE",
            "FREE_CAR_PARKING",
            "OUTDOOR_TABLE",
            "FREE_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            // ===== OVERVIEW – tóm tắt trên cao =====
            heading(ContentSection.OVERVIEW, "Tóm tắt The Temple Restaurant & Lounge - Hùng Vương"),
            paragraph(ContentSection.OVERVIEW,
                "The Temple Restaurant & Lounge là điểm hẹn ẩm thực món Âu giữa lòng phố cổ Hội An. " +
                "Thực đơn phong phú với các món ăn được chế biến từ nguyên liệu tuyển chọn, " +
                "phù hợp cho những bữa tiệc gia đình, họp mặt bạn bè hay tiếp khách đối tác trong không gian sang trọng, lãng mạn."),
            paragraph(ContentSection.OVERVIEW,
                "Nhà hàng nằm tại tầng trệt La Siesta Hoi An Resort & Spa, chỉ cách phố cổ Hội An khoảng 10 phút đi bộ, " +
                "rất thuận tiện cho du khách kết hợp dùng bữa và tham quan."),
            mapBlock(ContentSection.OVERVIEW, new double[]{108.3175, 15.8795}),

            // ===== STORY – bài giới thiệu dài, có text + ảnh xen kẽ =====

            // 1) Heading + 2 đoạn mô tả ẩm thực
            heading(ContentSection.STORY, "The Temple Restaurant & Lounge – Chạm vào tinh hoa ẩm thực châu Âu giữa lòng Hội An"),
            paragraph(ContentSection.STORY,
                "Đến với The Temple Restaurant & Lounge Hùng Vương, bạn sẽ được chiêu đãi thực đơn món Âu vô cùng hấp dẫn. " +
                "Thực đơn của nhà hàng là sự kết hợp hài hòa giữa hương vị Pháp tinh tế, sự mộc mạc của Ý và nét đặc trưng " +
                "của ẩm thực châu Âu, tất cả đều được chế biến từ những nguyên liệu tươi ngon nhất dưới bàn tay tài hoa " +
                "của đội ngũ bếp trưởng giàu kinh nghiệm. Dù là tháp hải sản tươi sống, sườn cừu nướng mềm thơm hay đĩa thịt " +
                "tổng hợp đậm đà, mỗi món ăn đều là một tác phẩm nghệ thuật, khơi gợi mọi giác quan."),
            paragraph(ContentSection.STORY,
                "Các món ăn đặc sắc tại The Temple Restaurant & Lounge Hùng Vương nhất định nên thử như: " +
                "Grilled Seafood Platter, Yellowfin Tuna Steak, Smoked Salmon Tartar, " +
                "Crab with Jalapeno Sweet corn Soup, Slow cooked Lamb Shank Pappadelle..."),

            // 2) Ảnh không có title (caption để null hoặc chuỗi rỗng)
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-6.jpg",
                    null,
                    false,
                    0
                )
            ),

            // 3) Heading + đoạn mô tả không gian
            heading(ContentSection.STORY, "Không gian nhà hàng sang trọng theo lối kiến trúc châu Âu"),
            paragraph(ContentSection.STORY,
                "Nhà hàng The Temple Restaurant & Lounge ấn tượng với không gian hiện đại, sang trọng theo lối kiến trúc " +
                "của những căn biệt thự kiểu Âu. Thiết kế của nhà hàng pha trộn với những đường nét Á Đông tinh tế hứa hẹn " +
                "sẽ là một trong những điểm đến hấp dẫn tại thành phố Hội An dành cho những ai đam mê khám phá ẩm thực " +
                "các nước trên thế giới."),

            // 4) Ảnh có title: Không gian sang trọng, đậm chất châu Âu
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-2.jpg",
                    "Không gian sang trọng, đậm chất châu Âu",
                    false,
                    1
                )
            ),

            // 5) Đoạn mô tả 2 không gian + ảnh ngoài trời
            paragraph(ContentSection.STORY,
                "The Temple Restaurant được chia thành 2 không gian. Nếu không gian ngoài trời sở hữu nét đẹp hiện đại " +
                "thì không gian bên trong lại lấy lòng thực khách theo một cách khác biệt qua chất liệu gỗ truyền thống " +
                "làm chủ đạo."),
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-4.jpg",
                    "Khu vực ngoài trời thoáng đãng",
                    false,
                    2
                )
            ),

            // 6) Đoạn về mức giá + lời mời xem thêm hình
            paragraph(ContentSection.STORY,
                "Với mức giá 250.000 đến 650.000 vnđ/người, nhà hàng phù hợp để tổ chức các sự kiện đặc biệt, " +
                "đón tiếp các đoàn khách du lịch hay họp mặt gia đình..."),
            paragraph(ContentSection.STORY,
                "Mời bạn khám phá thêm không gian và món ăn của The Temple Restaurant & Lounge qua những bức hình dưới đây!"),

            // 7) Một loạt ảnh cuối – gom thành GALLERY
            gallery(
                ContentSection.STORY,
                List.of(
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-1.jpg",
                        "Quầy pha chế đồ uống tại nhà hàng",
                        false,
                        3
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-3.jpg",
                        "The Temple Restaurant Hùng Vương phù hợp cho buổi tiệc hẹn hò lãng mạn, tiếp đối tác...",
                        false,
                        4
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-8.jpg",
                        "Thưởng thức buffet sáng và các món Âu ngon trong khuôn viên ngoài trời của nhà hàng",
                        false,
                        5
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-9.jpg",
                        "Thực đơn món Âu đa dạng và phong phú",
                        false,
                        6
                    )
                )
            ),

            // 8) Đoạn kết + phần địa chỉ
            paragraph(ContentSection.STORY,
                "The Temple Restaurant & Lounge nằm ở vị trí thuận lợi, chỉ cách phố cổ Hội An 10 phút đi bộ. " +
                "Mời bạn ghé nhà hàng The Temple Restaurant Hùng Vương để trải nghiệm không gian sang cùng những món Âu ngon. " +
                "Đừng quên đặt bàn PasGo để được giữ chỗ miễn phí và nhận ưu đãi hấp dẫn nhé!"),
            paragraph(ContentSection.STORY,
                "Để đặt bàn PasGo, vui lòng tải App PasGo (phiên bản Android/IOS). " +
                "Hoặc gọi tổng đài 1900.6005 (1.000đ/phút) để nhận tư vấn đặt bàn nhanh nhất!"),
            heading(ContentSection.STORY, "Địa chỉ nhà hàng The Temple Restaurant & Lounge"),
            paragraph(ContentSection.STORY,
                "Tầng trệt, La Siesta Hội An Resort & Spa, số 132, Đường Hùng Vương, Phường Thanh Hà, " +
                "Tp. Hội An, Tỉnh Quảng Nam.")
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  "Không áp dụng ưu đãi ngày 1/1"),
            blackoutGregorian(2, 14, "Không áp dụng ưu đãi ngày 14/2"),
            blackoutGregorian(3, 8,  "Không áp dụng ưu đãi ngày 8/3"),
            blackoutGregorian(4, 30, "Không áp dụng ưu đãi ngày 30/4"),
            blackoutGregorian(5, 1,  "Không áp dụng ưu đãi ngày 1/5"),
            blackoutGregorian(6, 1,  "Không áp dụng ưu đãi ngày 1/6"),
            blackoutGregorian(9, 1,  "Không áp dụng ưu đãi ngày 1/9"),
            blackoutGregorian(9, 2,  "Không áp dụng ưu đãi ngày 2/9"),
            blackoutGregorian(10, 20,"Không áp dụng ưu đãi ngày 20/10"),
            blackoutGregorian(12, 24,"Không áp dụng ưu đãi ngày 24/12"),
            blackoutGregorian(12, 25,"Không áp dụng ưu đãi ngày 25/12"),
            blackoutGregorian(12, 31,"Không áp dụng ưu đãi ngày 31/12")
            // Các ngày âm lịch (Giỗ tổ, Trung thu, Tết Nguyên Đán) có thể bổ sung thêm rule LUNAR_DATE sau
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note("Phụ phí rượu vang: 400.000đ/chai 750ml.")
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("800000"))
                .currencyCode("VND")
                .note("Phụ phí rượu mạnh, champagne: 800.000đ/chai 750ml.")
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(5)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes("Áp dụng cho booking từ 5 khách trở lên hoặc yêu cầu trang trí đặc biệt.")
            .hasPromotion(true)
            .promotionSummary("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.")
            .promotionMaxDiscountPercent(20)
            .promotionNote("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.")
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null) // Không quy định số khách tối thiểu
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes("Nhà hàng có xuất hóa đơn VAT theo yêu cầu, không xuất hóa đơn trực tiếp.")
            .serviceChargePercent(null)
            .serviceChargeNotes("Không thu phí phục vụ riêng.")
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy("Không được mang thức ăn từ ngoài vào.")
            .outsideDrinkPolicy("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.")
            .outsideDrinkFees(outsideDrinkFees)
            .build();

        // ---------- Review stats ----------
        ReviewStats reviewStats = ReviewStats.builder()
            .foodScore(4.8)
            .spaceScore(4.7)
            .serviceScore(4.7)
            .priceScore(4.2)
            .locationScore(4.6)
            .keywords(List.of(
                keyword("good_food", "Món ăn ngon", 90),
                keyword("romantic_space", "Không gian lãng mạn", 60),
                keyword("friendly_staff", "Nhân viên thân thiện", 55)
            ))
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
                                .partnerId("11")
                                .partnerName("Ông Trùm Bất Động Sản")
                                .partnerEmail("22110377@student.hcmute.edu.vn")
                                .partnerType(PartnerType.RESTAURANT_OWNER)
                                .createdAt(now)
                                .lastUpdatedAt(now)
                                .build();

        ModerationInfo moderation = ModerationInfo.builder()
            .status(RestaurantStatus.APPROVED)
            .rejectionReason(null)
            .blockedReason(null)
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Table types (bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-2-romantic")
                .name("Bàn 2 (Romantic)")
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(8)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note("Bàn đôi không gian lãng mạn.")
                .build(),

            TableType.builder()
                .id("tb-4")
                .name("Bàn 4")
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(10)
                .depositPrice(new BigDecimal("250000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note("Phù hợp nhóm bạn/gia đình.")
                .build(),

            TableType.builder()
                .id("tb-4-outdoor")
                .name("Bàn 4 (Ngoài trời)")
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(6)
                .depositPrice(new BigDecimal("250000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note("Khu vực thoáng đãng, ưu tiên buổi tối.")
                .build(),

            TableType.builder()
                .id("tb-6-vip")
                .name("Bàn 6 VIP")
                .seats(6)
                .minPeople(5)
                .maxPeople(6)
                .totalTables(2)
                .depositPrice(new BigDecimal("600000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note("Vị trí đẹp, phù hợp dịp đặc biệt.")
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(90)      // res Âu thường cần chuẩn bị kỹ hơn
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(25)    // có thể chặt hơn chút
            .depositOnly(true)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("hoi-an")
            .parentPlaceSlug("la-siesta-hoi-an-resort-spa")
            .cityName("Hội An")
            .districtName("Tp. Hội An")
            .wardName("P. Thanh Hà")
            .addressLine("Tầng trệt, La Siesta Hội An Resort & Spa, Số 132 Hùng Vương, P. Thanh Hà, Tp. Hội An")
            .location(new double[]{108.3175, 15.8795})
            .name("The Temple Restaurant & Lounge - Hùng Vương")
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription("Nhà hàng món Âu sang trọng trong khuôn viên La Siesta Hoi An Resort & Spa, phù hợp cho những buổi tiệc và dịp đặc biệt.")
            .description(
                "The Temple Restaurant & Lounge - Hùng Vương là nhà hàng chuyên ẩm thực Âu giữa lòng Hội An, " +
                "sở hữu không gian sang trọng, lãng mạn theo phong cách biệt thự châu Âu. " +
                "Thực đơn đa dạng với các món Âu được chế biến tinh tế, rất phù hợp cho những bữa ăn gia đình, " +
                "họp mặt bạn bè, tiệc công ty hay các dịp kỷ niệm đặc biệt."
            )
            .phone("1900xxxxxx")
            .email("reservation@thetemplehoian.com")
            .website("https://example.com/the-temple-hoi-an")
            .facebookPage("https://facebook.com/thetemplehoian")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("650000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
            // Lấy khoảng giữa ~450k => map vào bucket 350k-500k
            .priceBucket(PriceBucket.FROM_350K_TO_500K)
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
            .menuImages(menuImages)
            .menuSections(menuSections)
            .parking(parking)
            .amenityCodes(amenityCodes)
            .images(images)
            .content(content)
            .policy(policy)
            .avgRating(4.6)
            .reviewsCount(95)
            .ratingLabel("Rất tốt")
            .reviewStats(reviewStats)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}