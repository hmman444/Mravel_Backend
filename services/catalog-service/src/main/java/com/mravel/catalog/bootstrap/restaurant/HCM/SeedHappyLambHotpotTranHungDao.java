// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedHappyLambHotpotTranHungDao.java
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
public class SeedHappyLambHotpotTranHungDao {

    @Bean
    CommandLineRunner seedHappyLambHotpotTranHungDaoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedHappyLambHotpotTranHungDao] start");

            RestaurantDoc happyLamb = restaurantHappyLambHotpotTranHungDao();
            repo.findBySlugAndActiveTrue(happyLamb.getSlug())
                .ifPresent(existing -> happyLamb.setId(existing.getId()));
            repo.save(happyLamb);
            log.info(">>> upsert: {}", happyLamb.getSlug());

            log.info(">>> [SeedHappyLambHotpotTranHungDao] done");
        };
    }

    // =====================================================================
    //          HAPPY LAMB HOTPOT - TRẦN HƯNG ĐẠO (QUẬN 1, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantHappyLambHotpotTranHungDao() {
        String slug = "happy-lamb-hotpot-tran-hung-dao";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-1-normal-2253906656532.webp",
                of("Không gian Happy Lamb Hotpot - Trần Hưng Đạo", "Happy Lamb Hotpot - Tran Hung Dao space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-2-normal-2253906756533.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-3-normal-2253906856534.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-4-normal-2253906956535.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-5-normal-2253907056536.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-6-normal-2253907156537.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-7-normal-2253907256538.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-8-normal-2253907356539.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-9-normal-2253907456540.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-10-normal-2253907556541.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-11-normal-2253907656542.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-happy-lamb-hotpot-tran-hung-dao-12-normal-2253907956543.webp",
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
                .code("MONGOLIAN")
                .name(of("Lẩu Mông Cổ", "Mongolian hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("LAMB")
                .name(of("Thịt cừu", "Lamb"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 02:00 hôm sau, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 2, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 2, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 2, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 2, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 2, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 2, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 2, 0)
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
            suitable("DINNER",        of("Bữa tối", "Dinner"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("ELEGANT", of("Sang trọng", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu cừu Mông Cổ đặc biệt", "Special Mongolian lamb hotpot")),
            signatureDishL(of("Thịt cừu tươi thượng hạng", "Premium fresh lamb slices")),
            signatureDishL(of("Lẩu nấm dược liệu", "Medicinal mushroom hotpot")),
            signatureDishL(of("Bò Mỹ nhúng lẩu", "US beef for hotpot")),
            signatureDishL(of("Hải sản tươi sống", "Fresh seafood platter")),
            signatureDishL(of("Cuộn thịt cừu cuốn rau", "Lamb and vegetable rolls")),
            signatureDishL(of("Viên thả lẩu tổng hợp", "Assorted hotpot balls")),
            signatureDishL(of("Rau nhúng lẩu tươi", "Fresh hotpot vegetables")),
            signatureDishL(of("Mì & bánh bao thủ công", "Handmade noodles & buns")),
            signatureDishL(of("Nước chấm mè đặc trưng", "Signature sesame dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/happylambhotpot/nha-hang-happy-lamb-hotpot-tran-hung-dao-menu-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/happylambhotpot/nha-hang-happy-lamb-hotpot-tran-hung-dao-menu-2.jpg",
                of("", ""), false, 1)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu cừu Mông Cổ", "Mongolian lamb hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set lẩu cừu 2 người", "Lamb hotpot set for 2"))
                        .description(of("Set lẩu cừu Mông Cổ cho 2 người: nước lẩu nấm dược liệu, thịt cừu tươi, viên thả, rau và mì.", "Mongolian lamb hotpot set for 2: medicinal mushroom broth, fresh lamb, balls, vegetables and noodles."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("550000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "lamb", "set"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set lẩu cừu 4 người", "Lamb hotpot set for 4"))
                        .description(of("Set lẩu cừu Mông Cổ cho 4 người: nước lẩu đôi, thịt cừu và bò tươi, hải sản, viên thả, rau và mì.", "Mongolian lamb hotpot set for 4: twin broth, fresh lamb and beef, seafood, balls, vegetables and noodles."))
                        .priceFrom(new BigDecimal("1100000"))
                        .priceTo(new BigDecimal("1100000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "lamb", "set"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô lề đường Trần Hưng Đạo và bãi gần nhà hàng", "Street parking on Tran Hung Dao and lots near the restaurant"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ giữ xe; xe máy miễn phí.", "Parking staff on hand; motorbike parking is free."))
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
            "FREE_MOTORBIKE_PARKING",
            "CAR_PARKING",
            "SMOKING_AREA",
            "VISA_MASTER",
            "VAT_INVOICE",
            "OUTDOOR_TABLE",
            "LATE_NIGHT_SERVICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Happy Lamb Hotpot - Trần Hưng Đạo", "About Happy Lamb Hotpot - Tran Hung Dao")),
            paragraph(ContentSection.OVERVIEW,
                of("Happy Lamb Hotpot - Trần Hưng Đạo là thương hiệu lẩu cừu Mông Cổ nổi tiếng, mang đến cho thực khách Sài Gòn " +
                "trải nghiệm lẩu chuẩn vị với thịt cừu tươi thượng hạng cùng nước lẩu nấm dược liệu thanh ngọt. " +
                "Tọa lạc ngay trung tâm Quận 1, nhà hàng là điểm hẹn lý tưởng cho những bữa tiệc ấm cúng bên gia đình và bạn bè.",
                "Happy Lamb Hotpot - Tran Hung Dao is a renowned Mongolian lamb hotpot brand, bringing Saigon diners an authentic " +
                "hotpot experience with premium fresh lamb and a delicate medicinal mushroom broth. " +
                "Located right in the heart of District 1, the restaurant is an ideal venue for cozy gatherings with family and friends.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng được thiết kế hiện đại, sang trọng nhưng vẫn ấm cúng, sức chứa lên đến khoảng 180 khách. " +
                "Bên cạnh khu vực chung, Happy Lamb Hotpot còn bố trí nhiều phòng riêng tiện nghi, phù hợp cho nhóm đông, " +
                "tiệc gia đình hay liên hoan công ty cần không gian riêng tư.",
                "The restaurant is designed in a modern, elegant yet cozy style with a capacity of around 180 guests. " +
                "Besides the main dining area, Happy Lamb Hotpot offers several well-equipped private rooms, ideal for large groups, " +
                "family parties or company gatherings that need privacy.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Happy Lamb Hotpot là thịt cừu tươi thái lát thượng hạng, nhúng trong nước lẩu nấm dược liệu đặc trưng. " +
                "Thực khách còn có thể thưởng thức bò Mỹ, hải sản tươi sống, viên thả tự làm, bánh bao thủ công cùng nước chấm mè " +
                "pha chế riêng tạo nên hương vị khó quên.",
                "The highlight of Happy Lamb Hotpot is its premium fresh-sliced lamb dipped in a signature medicinal mushroom broth. " +
                "Diners can also enjoy US beef, fresh seafood, house-made balls, handmade buns and a special sesame dipping sauce " +
                "for an unforgettable taste.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không gian sang trọng và phục vụ chu đáo, Happy Lamb Hotpot - Trần Hưng Đạo phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật, kỷ niệm hay những buổi tiếp khách quan trọng.",
                "With an elegant space and attentive service, Happy Lamb Hotpot - Tran Hung Dao is perfect for family meals, " +
                "friends gatherings, office parties, birthdays, anniversaries or important business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ giữ xe máy miễn phí, hỗ trợ đỗ ô tô và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Free motorbike parking, car parking assistance and VAT invoices on request are available.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 329 Trần Hưng Đạo, P. Cô Giang, Q. 1, ngay trung tâm TP. Hồ Chí Minh, " +
                "gần chợ Bến Thành và phố Tây Bùi Viện, rất thuận tiện cho khách di chuyển từ các quận lân cận.",
                "The restaurant is located at 329 Tran Hung Dao, Co Giang Ward, District 1, right in central Ho Chi Minh City, " +
                "near Ben Thanh Market and Bui Vien walking street, very convenient to reach from neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào buổi tối cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekend evenings and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.690251670958, 10.7618411523787})
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
            .depositAmount(new BigDecimal("500000"))
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
            .serviceChargeNotes(of("Phí phục vụ 5% áp dụng cho phòng riêng và nhóm đông.", "5% service charge applies to private rooms and large groups."))
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
                .depositPrice(new BigDecimal("200000"))
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
                .depositPrice(new BigDecimal("400000"))
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
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(true)
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
            .districtName(of("Q. 1", "District 1"))
            .wardName(of("P. Cô Giang", "Co Giang Ward"))
            .addressLine(of("329 Trần Hưng Đạo, P. Cô Giang, Q. 1, TP. Hồ Chí Minh", "329 Tran Hung Dao, Co Giang Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.690251670958, 10.7618411523787})
            .name(of("Happy Lamb Hotpot - Trần Hưng Đạo", "Happy Lamb Hotpot - Tran Hung Dao"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu cừu Mông Cổ chuẩn vị, thịt cừu tươi thượng hạng giữa trung tâm Quận 1, TP. Hồ Chí Minh.", "Authentic Mongolian lamb hotpot with premium fresh lamb in central District 1, Ho Chi Minh City."))
            .description(of("Happy Lamb Hotpot - Trần Hưng Đạo là nhà hàng lẩu cừu Mông Cổ tọa lạc tại 329 Trần Hưng Đạo, Q. 1, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi bật với thịt cừu tươi thượng hạng, nước lẩu nấm dược liệu thanh ngọt, không gian hiện đại sang trọng, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Happy Lamb Hotpot - Tran Hung Dao is a Mongolian lamb hotpot restaurant located at 329 Tran Hung Dao, District 1, Ho Chi Minh City. " +
                "It stands out with premium fresh lamb, a delicate medicinal mushroom broth and a modern, elegant space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@happylambhotpot.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-happy-lamb-hotpot-tran-hung-dao")
            .facebookPage("https://facebook.com/happylambhotpot")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("500000"))
            .maxPricePerPerson(new BigDecimal("600000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
            .priceBucket(PriceBucket.ABOVE_500K)
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
