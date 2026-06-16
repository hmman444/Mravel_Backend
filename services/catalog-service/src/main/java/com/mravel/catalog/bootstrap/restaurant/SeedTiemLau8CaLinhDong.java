// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedTiemLau8CaLinhDong.java
package com.mravel.catalog.bootstrap.restaurant;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
import com.mravel.catalog.model.doc.RestaurantDoc.SignatureDish;
import com.mravel.catalog.model.doc.RestaurantDoc.SuitableFor;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.vi;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedTiemLau8CaLinhDong {

    @Bean
    CommandLineRunner seedTiemLau8CaLinhDongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedTiemLau8CaLinhDong] start");

            RestaurantDoc doc = restaurantTiemLau8CaLinhDong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedTiemLau8CaLinhDong] done");
        };
    }

    // =====================================================================
    //              TIỆM LẨU 8 CÁ - LINH ĐÔNG (THỦ ĐỨC, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantTiemLau8CaLinhDong() {
        String slug = "tiem-lau-8-ca-linh-dong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-1-normal-2330884964489.webp",
                of("Không gian Tiệm Lẩu 8 Cá - Linh Đông", "Tiem Lau 8 Ca - Linh Dong space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-2-normal-2330885064490.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-3-normal-2330885164491.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-4-normal-2330885264492.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-5-normal-2330885364493.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-6-normal-2330885464494.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-7-normal-2330885564495.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-8-normal-2330885664496.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-9-normal-2330886164497.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-10-normal-2330887064498.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-11-normal-2330887164499.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tiem-lau-8-ca-12-normal-2330887264500.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("FISH_HOTPOT")
                .name(of("Lẩu cá", "Fish hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build()
        );

        // ---------- Giờ mở cửa (15:00 - 22:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    15, 0, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   15, 0, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 15, 0, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  15, 0, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    15, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  15, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    15, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",  of("Mộc mạc miền sông nước", "Rustic riverside")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu 8 Cá đặc biệt", "8-fish signature hotpot")),
            signatureDishL(of("Lẩu cá kèo lá giang", "Goby fish & la giang leaf hotpot")),
            signatureDishL(of("Lẩu cá tầm măng chua", "Sturgeon & sour bamboo hotpot")),
            signatureDishL(of("Lẩu cá basa", "Basa fish hotpot")),
            signatureDishL(of("Lẩu cá diêu hồng", "Red tilapia hotpot")),
            signatureDishL(of("Cá lăng nướng muối ớt", "Grilled mekong catfish with chili salt")),
            signatureDishL(of("Cá chép giòn chiên xù", "Crispy fried carp")),
            signatureDishL(of("Rau nhúng lẩu miền Tây", "Western-style hotpot vegetables")),
            signatureDishL(of("Bún & mì nhúng lẩu", "Noodles for hotpot")),
            signatureDishL(of("Nước chấm cá đặc trưng", "Signature fish dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/lau8ca/lau-8--ca-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/lau8ca/lau-8--ca-2.jpg",
                of("", ""), false, 1)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu cá", "Fish hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu 8 Cá đặc biệt", "8-fish signature hotpot"))
                        .description(of("Nồi lẩu tổng hợp 8 loại cá tươi sông nước miền Tây, nước lẩu chua thanh, ăn kèm rau nhúng và bún.", "A combined hotpot of 8 fresh Mekong river fish in a tangy broth, served with vegetables and noodles."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("nồi", "pot"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "fish"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu cá tầm măng chua", "Sturgeon & sour bamboo hotpot"))
                        .description(of("Lẩu cá tầm tươi nấu cùng măng chua đặc trưng, vị thanh mát, ăn kèm rau nhúng tươi.", "Fresh sturgeon hotpot with sour bamboo shoots, refreshing flavor served with fresh vegetables."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("449000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "fish"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng", "Car parking near the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn, miễn phí cho khách.", "Security staff on hand, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "OUTDOOR_TABLE",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Tiệm Lẩu 8 Cá - Linh Đông", "About Tiem Lau 8 Ca - Linh Dong")),
            paragraph(ContentSection.OVERVIEW,
                of("Tiệm Lẩu 8 Cá - Linh Đông là địa chỉ quen thuộc của tín đồ lẩu cá miền Tây tại khu vực Thủ Đức, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ theo hình thức gọi món, mang đến những nồi lẩu cá tươi sông nước đậm đà, chua thanh, " +
                "cùng nguyên liệu tươi mới được tuyển chọn mỗi ngày.",
                "Tiem Lau 8 Ca - Linh Dong is a familiar address for Mekong-style fish hotpot lovers in Thu Duc, Ho Chi Minh City. " +
                "The restaurant serves a la carte, offering rich and tangy fresh river-fish hotpots " +
                "with carefully selected fresh ingredients every day.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, mang nét mộc mạc miền sông nước, có cả khu vực ngoài trời thoáng đãng với sức chứa lên đến khoảng 180 khách. " +
                "Tiệm Lẩu 8 Cá còn có phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, rustic riverside space, including airy outdoor seating, with a capacity of around 180 guests. " +
                "Tiem Lau 8 Ca also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của tiệm là nồi lẩu tổng hợp 8 loại cá tươi cùng các loại lẩu cá kèo lá giang, cá tầm măng chua, cá basa, cá diêu hồng. " +
                "Ngoài ra còn có cá lăng nướng muối ớt, cá chép giòn chiên xù, ăn kèm rau nhúng miền Tây và nước chấm cá đặc trưng, " +
                "mức giá khoảng 300.000đ/người.",
                "The highlight is the combined 8-fish hotpot, along with goby & la giang leaf, sturgeon & sour bamboo, basa and red tilapia hotpots. " +
                "There are also grilled catfish with chili salt and crispy fried carp, served with Western-style vegetables and a signature fish dipping sauce, " +
                "at around 300,000 VND per person.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phong vị miền Tây dân dã, Tiệm Lẩu 8 Cá - Linh Đông phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy atmosphere and rustic Mekong flavors, Tiem Lau 8 Ca - Linh Dong is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, outdoor seating, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 28 Linh Đông, P. Linh Đông, Q. Thủ Đức, gần khu vực Phạm Văn Đồng và cầu Bình Lợi, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at 28 Linh Dong, Linh Dong Ward, Thu Duc District, near Pham Van Dong and Binh Loi Bridge, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.745427274631, 10.8443506407042})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 150.000đ/chai.", "Wine corkage fee: 150,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 6 or more guests or private room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
            .serviceChargePercent(null)
            .serviceChargeNotes(of("Không thu phí phục vụ riêng.", "No separate service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.", "Outside drinks are allowed, with a corkage fee depending on the type of drink."))
            .outsideDrinkFees(outsideDrinkFees)
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
                .name(of("Bàn 2", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(8)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp cặp đôi, nhóm nhỏ.", "Suitable for couples and small groups."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(16)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông/liên hoan công ty.", "Private room, suitable for large groups and company parties."))
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
            .destinationSlug("ho-chi-minh-city")
            .cityName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
            .districtName(of("Q. Thủ Đức", "Thu Duc District"))
            .wardName(of("P. Linh Đông", "Linh Dong Ward"))
            .addressLine(of("28 Linh Đông, P. Linh Đông, Q. Thủ Đức, TP. Hồ Chí Minh", "28 Linh Dong, Linh Dong Ward, Thu Duc District, Ho Chi Minh City"))
            .location(new double[]{106.745427274631, 10.8443506407042})
            .name(of("Tiệm Lẩu 8 Cá - Linh Đông", "Tiem Lau 8 Ca - Linh Dong"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu cá tươi sông nước miền Tây, gọi món, giá hợp lý tại Thủ Đức, TP. Hồ Chí Minh.", "Fresh Mekong river-fish hotpot, a la carte, value-for-money in Thu Duc, Ho Chi Minh City."))
            .description(of("Tiệm Lẩu 8 Cá - Linh Đông là nhà hàng lẩu cá phục vụ gọi món tại 28 Linh Đông, Q. Thủ Đức, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trọn vị những nồi lẩu cá tươi sông nước miền Tây, nguyên liệu tươi mới, không gian rộng rãi mộc mạc, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Tiem Lau 8 Ca - Linh Dong is a fish-hotpot restaurant serving a la carte at 28 Linh Dong, Thu Duc District, Ho Chi Minh City. " +
                "It delivers the full flavor of fresh Mekong river-fish hotpots with fresh ingredients in a spacious, rustic space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("1900xxxxxx")
            .email("reservation@tiemlau8ca.vn")
            .website("https://pasgo.vn/nha-hang/tiem-lau-8-ca-linh-dong")
            .facebookPage("https://facebook.com/tiemlau8ca")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("350000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.MODERATE)
            .priceBucket(PriceBucket.FROM_250K_TO_350K)
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
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}
