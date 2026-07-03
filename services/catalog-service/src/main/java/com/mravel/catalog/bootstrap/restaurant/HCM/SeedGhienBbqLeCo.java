// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedGhienBbqLeCo.java
package com.mravel.catalog.bootstrap.restaurant.HCM;

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
public class SeedGhienBbqLeCo {

    @Bean
    CommandLineRunner seedGhienBbqLeCoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGhienBbqLeCo] start");

            RestaurantDoc ghien = restaurantGhienBbqLeCo();
            repo.findBySlugAndActiveTrue(ghien.getSlug())
                .ifPresent(existing -> ghien.setId(existing.getId()));
            repo.save(ghien);
            log.info(">>> upsert: {}", ghien.getSlug());

            log.info(">>> [SeedGhienBbqLeCo] done");
        };
    }

    // =====================================================================
    //            GHIỀN BBQ NƯỚNG & LẨU - LÊ CƠ (BÌNH TÂN, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantGhienBbqLeCo() {
        String slug = "ghien-bbq-nuong-lau-le-co";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ghien-bbq-le-co-1-normal-2314651363401.webp",
                of("Không gian Ghiền BBQ Nướng & Lẩu - Lê Cơ", "Ghien BBQ & Hotpot - Le Co space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ghien-bbq-le-co-3-normal-2314652263403.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ghien-bbq-le-co-5-normal-2314652363402.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ghien-bbq-le-co-6-normal-2314651463404.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-5-normal-2314144763405.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-6-normal-2314144863406.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-8-normal-2314145063408.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-9-normal-2314145163409.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-10-normal-2314145263410.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-11-normal-2314145363411.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ghien-bbq-nuong-va-lau-le-co-12-normal-2314145463412.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/200-le-co--p--an-lac--q--binh-tan-1072646370721710661292507128.webp",
                of("Mặt tiền nhà hàng tại 200 Lê Cơ", "Storefront at 200 Le Co"), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "Barbecue"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (13:00 - 23:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    13, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   13, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 13, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  13, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    13, 0, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  13, 0, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    13, 0, 23, 0)
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
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(160)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly")),
            signatureDishL(of("Bò cuộn nấm kim châm", "Beef rolls with enoki")),
            signatureDishL(of("Sườn non nướng BBQ", "BBQ grilled pork ribs")),
            signatureDishL(of("Nầm bò nướng sa tế", "Grilled beef brisket with sate")),
            signatureDishL(of("Bạch tuộc nướng", "Grilled octopus")),
            signatureDishL(of("Tôm sú nướng muối ớt", "Grilled tiger prawns with chili salt")),
            signatureDishL(of("Lẩu Thái chua cay", "Spicy Thai hotpot")),
            signatureDishL(of("Lẩu nấm thập cẩm", "Mixed mushroom hotpot")),
            signatureDishL(of("Mực nướng sa tế", "Grilled squid with sate")),
            signatureDishL(of("Combo nướng & lẩu cho nhóm", "Grill & hotpot combo for groups"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/ghiền%20bbq/menu-ghien-bbq-1.jpg",
                of("Bảng giá Ghiền BBQ Nướng & Lẩu - Lê Cơ", "Ghien BBQ & Hotpot - Le Co price board"), false, 0)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILL")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly"))
                        .description(of("Ba chỉ bò Mỹ thái lát, ướp đậm đà, nướng tại bàn.", "Sliced US beef belly, well marinated, grilled at the table."))
                        .priceFrom(new BigDecimal("129000"))
                        .priceTo(new BigDecimal("129000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Sườn non nướng BBQ", "BBQ grilled pork ribs"))
                        .description(of("Sườn non ướp sốt BBQ, nướng vàng thơm.", "Pork ribs marinated in BBQ sauce, grilled golden and fragrant."))
                        .priceFrom(new BigDecimal("139000"))
                        .priceTo(new BigDecimal("139000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "pork"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Món lẩu", "Hotpot dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu Thái chua cay", "Spicy Thai hotpot"))
                        .description(of("Nước lẩu Thái chua cay đậm đà, ăn kèm hải sản và rau tươi.", "Rich spicy Thai broth, served with seafood and fresh vegetables."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "thai"))
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
            .notes(of("Có nhân viên hướng dẫn đỗ xe, miễn phí cho khách.", "Parking staff on hand, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "OUTDOOR_TABLE",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BBQ_AT_TABLE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Ghiền BBQ Nướng & Lẩu - Lê Cơ", "About Ghien BBQ & Hotpot - Le Co")),
            paragraph(ContentSection.OVERVIEW,
                of("Ghiền BBQ Nướng & Lẩu - Lê Cơ là điểm hẹn ẩm thực nướng và lẩu chuẩn vị Đông Nam Á tại khu vực Bình Tân, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ gọi món đa dạng với nguyên liệu tươi mới mỗi ngày, mang đến cho thực khách trải nghiệm vừa nướng tại bàn vừa thưởng thức những nồi lẩu nóng hổi đậm đà.",
                "Ghien BBQ & Hotpot - Le Co is a destination for Southeast Asian style grill and hotpot in Binh Tan District, Ho Chi Minh City. " +
                "The restaurant serves a diverse a la carte menu with ingredients kept fresh every day, giving diners both at-table grilling and rich, piping-hot hotpots.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, hiện đại và sôi động, được bố trí khoa học với sức chứa lên đến khoảng 160 khách. " +
                "Ghiền BBQ có thêm phòng riêng và khu vực ngoài trời, phù hợp cho các nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern and vibrant space, well arranged with a capacity of around 160 guests. " +
                "Ghien BBQ also has private rooms and an outdoor area, suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Ghiền BBQ là các món nướng tại bàn như ba chỉ bò Mỹ, sườn non BBQ, nầm bò sa tế, bạch tuộc và tôm sú nướng, " +
                "ăn kèm những nồi lẩu Thái chua cay hoặc lẩu nấm thập cẩm. Thực đơn phong phú, hợp túi tiền với mức chi tiêu khoảng 150.000 - 200.000đ/người.",
                "Ghien BBQ's highlights are at-table grilled dishes such as US beef belly, BBQ pork ribs, sate beef brisket, octopus and tiger prawns, " +
                "paired with spicy Thai or mixed mushroom hotpots. The menu is rich and affordable, with spending of around 150,000 - 200,000 VND per person.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và phục vụ nhanh nhẹn, Ghiền BBQ Nướng & Lẩu - Lê Cơ phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a vibrant atmosphere and attentive service, Ghien BBQ & Hotpot - Le Co is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bếp nướng tại bàn, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, at-table grills, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 200 Lê Cơ, P. An Lạc, Q. Bình Tân, gần khu Aeon Mall Bình Tân và đại lộ Võ Văn Kiệt, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at 200 Le Co, An Lac Ward, Binh Tan District, near Aeon Mall Binh Tan and Vo Van Kiet Avenue, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.61292507128, 10.726463707217})
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
            .depositAmount(new BigDecimal("200000"))
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
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% cho nhóm đông và phòng riêng.", "5% service charge applies to large groups and private rooms."))
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
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(2)
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
            .districtName(of("Q. Bình Tân", "Binh Tan District"))
            .wardName(of("P. An Lạc", "An Lac Ward"))
            .addressLine(of("200 Lê Cơ, P. An Lạc, Q. Bình Tân, TP. Hồ Chí Minh", "200 Le Co, An Lac Ward, Binh Tan District, Ho Chi Minh City"))
            .location(new double[]{106.61292507128, 10.726463707217})
            .name(of("Ghiền BBQ Nướng & Lẩu - Lê Cơ", "Ghien BBQ & Hotpot - Le Co"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nướng & lẩu chuẩn vị Đông Nam Á, giá hợp lý tại Bình Tân, TP. Hồ Chí Minh.", "Southeast Asian style grill & hotpot, value for money in Binh Tan, Ho Chi Minh City."))
            .description(of("Ghiền BBQ Nướng & Lẩu - Lê Cơ là nhà hàng nướng và lẩu tại 200 Lê Cơ, Q. Bình Tân, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến các món nướng tại bàn và những nồi lẩu nóng hổi đậm đà, nguyên liệu tươi mới, không gian rộng rãi sôi động, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Ghien BBQ & Hotpot - Le Co is a grill and hotpot restaurant at 200 Le Co, Binh Tan District, Ho Chi Minh City. " +
                "It offers at-table grilled dishes and rich, piping-hot hotpots with fresh ingredients in a spacious, vibrant space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@ghienbbq.vn")
            .website("https://pasgo.vn/nha-hang/ghien-bbq-nuong-va-lau-le-co")
            .facebookPage("https://facebook.com/ghienbbq")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("200000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.MODERATE)
            .priceBucket(PriceBucket.FROM_150K_TO_250K)
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
