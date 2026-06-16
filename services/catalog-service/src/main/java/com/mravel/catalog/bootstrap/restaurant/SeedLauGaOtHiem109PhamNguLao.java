// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedLauGaOtHiem109PhamNguLao.java
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
public class SeedLauGaOtHiem109PhamNguLao {

    @Bean
    CommandLineRunner seedLauGaOtHiem109PhamNguLaoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauGaOtHiem109PhamNguLao] start");

            RestaurantDoc r = restaurantLauGaOtHiem109PhamNguLao();
            repo.findBySlugAndActiveTrue(r.getSlug())
                .ifPresent(existing -> r.setId(existing.getId()));
            repo.save(r);
            log.info(">>> upsert: {}", r.getSlug());

            log.info(">>> [SeedLauGaOtHiem109PhamNguLao] done");
        };
    }

    // =====================================================================
    //            LẨU GÀ ỚT HIỂM 109 - PHẠM NGŨ LÃO (GÒ VẤP, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantLauGaOtHiem109PhamNguLao() {
        String slug = "lau-ga-ot-hiem-109-pham-ngu-lao";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-1-normal-2263280657854.webp",
                of("Không gian Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão", "Lau Ga Ot Hiem 109 - Pham Ngu Lao space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-2-normal-2263280857855.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-3-normal-2263281057856.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-4-normal-2263281157857.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-5-normal-2263281357858.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-6-normal-2263281557859.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-7-normal-2263281657860.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-9-normal-2263281857861.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-10-normal-2263282057862.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-ngu-lao-normal-2263282357864.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-ngu-lao-2-normal-2263282257863.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-ngu-lao-3-normal-2263282557865.webp",
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
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 22:00, cả tuần) ----------
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
            ambience("COZY",            of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",          of("Mộc mạc đường phố", "Rustic street style")),
            ambience("VIBRANT",         of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(25)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu gà ớt hiểm", "Bird's eye chili chicken hotpot")),
            signatureDishL(of("Lẩu gà lá giang", "Chicken & giang leaf hotpot")),
            signatureDishL(of("Gà ta thả vườn", "Free-range chicken")),
            signatureDishL(of("Gà hấp hành", "Steamed chicken with scallions")),
            signatureDishL(of("Cháo gà ớt hiểm", "Chicken & chili congee")),
            signatureDishL(of("Lòng mề gà xào", "Stir-fried chicken giblets")),
            signatureDishL(of("Nấm tươi thập cẩm", "Assorted fresh mushrooms")),
            signatureDishL(of("Rau nhúng lẩu tươi", "Fresh hotpot vegetables")),
            signatureDishL(of("Mì & bún nhúng lẩu", "Noodles for hotpot")),
            signatureDishL(of("Nước chấm ớt hiểm đặc trưng", "Signature bird's eye chili dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-5.jpg",
                of("", ""), false, 4)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu gà", "Chicken hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu gà ớt hiểm (nồi vừa)", "Bird's eye chili chicken hotpot (medium)"))
                        .description(of("Nồi lẩu gà ta nấu cùng ớt hiểm cay nồng, nước dùng đậm đà, ăn kèm rau, nấm và bún tươi.", "Free-range chicken hotpot cooked with fiery bird's eye chili, rich broth, served with vegetables, mushrooms and fresh noodles."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("259000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken", "spicy"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu gà lá giang (nồi vừa)", "Chicken & giang leaf hotpot (medium)"))
                        .description(of("Lẩu gà nấu lá giang chua thanh, thanh mát, phù hợp khẩu vị cả gia đình.", "Chicken hotpot with sour giang leaf, light and refreshing, suitable for the whole family."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("239000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc đường Phạm Ngũ Lão", "Car parking along Pham Ngu Lao street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ trông giữ xe miễn phí.", "Staff assist with parking, free of charge."))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão", "About Lau Ga Ot Hiem 109 - Pham Ngu Lao")),
            paragraph(ContentSection.OVERVIEW,
                of("Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão là điểm hẹn quen thuộc cho tín đồ mê lẩu gà tại Quận Gò Vấp, TP. Hồ Chí Minh. " +
                "Thương hiệu nổi tiếng với nồi lẩu gà ta thả vườn nấu cùng ớt hiểm cay nồng, nước dùng đậm đà, nguyên liệu tươi mới mỗi ngày, " +
                "mang đến trải nghiệm ẩm thực dân dã mà khó quên.",
                "Lau Ga Ot Hiem 109 - Pham Ngu Lao is a familiar destination for chicken hotpot lovers in Go Vap District, Ho Chi Minh City. " +
                "The brand is famous for its free-range chicken hotpot cooked with fiery bird's eye chili, a rich broth and fresh ingredients every day, " +
                "delivering a rustic yet unforgettable dining experience.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng mang phong cách đường phố mộc mạc với cả khu vực trong nhà và bàn vỉa hè thoáng đãng, sức chứa khoảng 120 khách. " +
                "Có thêm phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant features a rustic street-food style with both indoor seating and airy sidewalk tables, with a capacity of around 120 guests. " +
                "It also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Ngôi sao của thực đơn là lẩu gà ớt hiểm trứ danh và lẩu gà lá giang chua thanh, ăn kèm gà ta thả vườn, lòng mề, nấm tươi thập cẩm, " +
                "rau nhúng và bún tươi. Ngoài ra còn có gà hấp hành, cháo gà ớt hiểm và các món gà chế biến đa dạng.",
                "The stars of the menu are the renowned bird's eye chili chicken hotpot and the tangy giang leaf chicken hotpot, served with free-range chicken, " +
                "giblets, assorted fresh mushrooms, vegetables and fresh noodles. There are also steamed chicken with scallions, chicken & chili congee and many chicken dishes.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, sôi động và phục vụ nhanh nhẹn, nhà hàng phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy, vibrant atmosphere and attentive service, the restaurant is ideal for family meals, friends gatherings, " +
                "office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bàn vỉa hè ngoài trời và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, outdoor sidewalk tables and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 109 Phạm Ngũ Lão, Phường 3, Quận Gò Vấp, cách trung tâm thương mại Go! Gò Vấp khoảng 500m và gần trường Đại học Công nghiệp, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at 109 Pham Ngu Lao, Ward 3, Go Vap District, about 500m from Go! Go Vap shopping mall and near the Industrial University, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6799957, 10.8215072})
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
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-25")
                .name(of("Phòng riêng 25 khách", "Private room for 25 guests"))
                .seats(25)
                .minPeople(12)
                .maxPeople(25)
                .totalTables(2)
                .depositPrice(new BigDecimal("800000"))
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
            .districtName(of("Q. Gò Vấp", "Go Vap District"))
            .wardName(of("Phường 3", "Ward 3"))
            .addressLine(of("109 Phạm Ngũ Lão, Phường 3, Q. Gò Vấp, TP. Hồ Chí Minh", "109 Pham Ngu Lao, Ward 3, Go Vap District, Ho Chi Minh City"))
            .location(new double[]{106.6799957, 10.8215072})
            .name(of("Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão", "Lau Ga Ot Hiem 109 - Pham Ngu Lao"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu gà ớt hiểm trứ danh, gà ta thả vườn tươi ngon tại Gò Vấp, TP. Hồ Chí Minh.", "Renowned bird's eye chili chicken hotpot with fresh free-range chicken in Go Vap, Ho Chi Minh City."))
            .description(of("Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão là nhà hàng chuyên lẩu gà tại 109 Phạm Ngũ Lão, Q. Gò Vấp, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi tiếng với nồi lẩu gà ta thả vườn nấu ớt hiểm cay nồng và lẩu gà lá giang chua thanh, nguyên liệu tươi mới, " +
                "không gian đường phố mộc mạc, phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Lau Ga Ot Hiem 109 - Pham Ngu Lao is a chicken hotpot restaurant at 109 Pham Ngu Lao, Go Vap District, Ho Chi Minh City. " +
                "It is famous for its free-range chicken hotpot cooked with fiery bird's eye chili and the tangy giang leaf chicken hotpot, with fresh ingredients " +
                "in a rustic street-food space, suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@laugaothiem109.vn")
            .website("https://pasgo.vn/nha-hang/lau-ga-ot-hiem-109-pham-ngu-lao")
            .facebookPage("https://facebook.com/laugaothiem109")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("250000"))
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
