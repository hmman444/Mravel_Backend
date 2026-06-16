// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedNgo8VoVanTan.java
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
public class SeedNgo8VoVanTan {

    @Bean
    CommandLineRunner seedNgo8VoVanTanRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNgo8VoVanTan] start");

            RestaurantDoc ngo8 = restaurantNgo8VoVanTan();
            repo.findBySlugAndActiveTrue(ngo8.getSlug())
                .ifPresent(existing -> ngo8.setId(existing.getId()));
            repo.save(ngo8);
            log.info(">>> upsert: {}", ngo8.getSlug());

            log.info(">>> [SeedNgo8VoVanTan] done");
        };
    }

    // =====================================================================
    //                 NGÕ 8 VÕ VĂN TẦN (QUẬN 3, HCM) - THAI FUSION
    // =====================================================================

    private static RestaurantDoc restaurantNgo8VoVanTan() {
        String slug = "ngo-8-vo-van-tan";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-2-normal-2332969364707.webp",
                of("Không gian Ngõ 8 Võ Văn Tần", "Ngo 8 Vo Van Tan space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-2-normal-2332969464708.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-3-normal-2332969564709.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-4-normal-2332969664710.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-5-normal-2332969764711.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-6-normal-2332969864712.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-7-normal-2332969964713.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-8-normal-2332970064714.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-9-normal-2332970164715.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-10-normal-2332970264716.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-11-normal-2332970364717.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-12-normal-2332970464718.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-ngo-8-vo-van-tan-13-normal-2332970564719.webp",
                of("", ""), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("THAI")
                .name(of("Món Thái", "Thai"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("FUSION")
                .name(of("Fusion", "Fusion"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (T2-T6 10:30-23:30, T7-CN 15:00-23:30) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 23, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 23, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 23, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 23, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 23, 30),
            openingHour(DayOfWeek.SATURDAY,  15, 0,  23, 30),
            openingHour(DayOfWeek.SUNDAY,    15, 0,  23, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("ROMANTIC",  of("Lãng mạn", "Romantic"))
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
            signatureDishL(of("Lẩu Thái Tomyum chua cay", "Spicy Thai Tomyum hotpot")),
            signatureDishL(of("Cá lóc nướng kiểu Thái", "Thai-style grilled snakehead fish")),
            signatureDishL(of("Gỏi xoài tôm khô", "Green mango salad with dried shrimp")),
            signatureDishL(of("Pad Thái xào tôm", "Pad Thai with shrimp")),
            signatureDishL(of("Cánh gà chiên nước mắm", "Fish sauce fried chicken wings")),
            signatureDishL(of("Tôm hấp nước dừa", "Coconut steamed prawns")),
            signatureDishL(of("Cơm chiên Thái dứa", "Thai pineapple fried rice")),
            signatureDishL(of("Mực nướng sa tế", "Grilled squid with satay")),
            signatureDishL(of("Salad bò trộn chua cay", "Spicy Thai beef salad")),
            signatureDishL(of("Chè Thái thập cẩm", "Mixed Thai sweet dessert"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoingo8/6.jpg",
                of("", ""), false, 5)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SIGNATURE")
                .name(of("Món đặc sắc", "Signature dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu Thái Tomyum (2-3 người)", "Thai Tomyum hotpot (2-3 people)"))
                        .description(of("Nồi lẩu Thái chua cay đậm vị, ăn kèm tôm, mực, thịt bò và rau nhúng tươi.", "Rich spicy Thai hotpot served with prawns, squid, beef and fresh vegetables."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("259000"))
                        .unit(of("nồi", "pot"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "thai", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo Thái 2 người", "Thai combo for 2"))
                        .description(of("Combo gồm gỏi xoài, pad Thái, cánh gà chiên nước mắm và chè Thái tráng miệng.", "Combo of mango salad, pad Thai, fish-sauce chicken wings and Thai dessert."))
                        .priceFrom(new BigDecimal("329000"))
                        .priceTo(new BigDecimal("329000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "thai"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc đường Võ Văn Tần và bãi lân cận", "Car parking along Vo Van Tan and nearby lots"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hướng dẫn đỗ xe.", "Parking attendants on hand."))
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
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BIRTHDAY_DECOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Ngõ 8 Võ Văn Tần", "About Ngo 8 Vo Van Tan")),
            paragraph(ContentSection.OVERVIEW,
                of("Ngõ 8 Võ Văn Tần là nhà hàng ẩm thực Thái - Đông Nam Á theo phong cách fusion, tọa lạc ngay trung tâm Quận 3, TP. Hồ Chí Minh. " +
                "Với thực đơn gọi món phong phú và mức giá hợp lý, nhà hàng là điểm hẹn quen thuộc cho những ai yêu thích hương vị chua cay đặc trưng của xứ Thái.",
                "Ngo 8 Vo Van Tan is a Thai - Southeast Asian fusion restaurant located right in the heart of District 3, Ho Chi Minh City. " +
                "With a diverse a la carte menu at reasonable prices, it is a familiar spot for those who love the signature sour-and-spicy flavors of Thailand.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng được thiết kế ấm cúng, hiện đại pha chút lãng mạn với cả khu vực trong nhà và ngoài trời, sức chứa khoảng 120 khách. " +
                "Ngõ 8 có thêm phòng riêng phù hợp cho nhóm bạn, tiệc gia đình hoặc liên hoan công ty.",
                "The restaurant is designed to be cozy, modern with a romantic touch, offering both indoor and outdoor areas with a capacity of around 120 guests. " +
                "Ngo 8 also has private rooms suitable for groups of friends, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Ngõ 8 là lẩu Thái Tomyum chua cay, cá lóc nướng kiểu Thái, gỏi xoài tôm khô, pad Thái và cánh gà chiên nước mắm. " +
                "Mức giá trung bình 100.000 - 200.000đ/người, vừa túi tiền cho cả bữa ăn nhóm.",
                "Ngo 8's highlights include the spicy Thai Tomyum hotpot, Thai-style grilled snakehead fish, green mango salad, pad Thai and fish-sauce chicken wings. " +
                "With an average price of 100,000 - 200,000 VND per person, it is affordable even for group dining.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Không khí sôi động và phục vụ thân thiện khiến Ngõ 8 Võ Văn Tần phù hợp cho tụ tập bạn bè, hẹn hò, bữa ăn gia đình, " +
                "liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật.",
                "Its vibrant atmosphere and friendly service make Ngo 8 Vo Van Tan ideal for friends gatherings, dates, family meals, " +
                "office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ MC, trang trí cho tiệc sinh nhật. " +
                "Có chỗ giữ xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, an outdoor area, sound & light systems and MC / decoration support for birthday parties. " +
                "Free motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm tại 57 Võ Văn Tần, P. 6, Q. 3, TP. Hồ Chí Minh, gần Hồ Con Rùa và khu trung tâm Quận 3, " +
                "thuận tiện di chuyển từ Quận 1 và các quận lân cận.",
                "The restaurant is located at 57 Vo Van Tan, Ward 6, District 3, Ho Chi Minh City, near Turtle Lake and central District 3, " +
                "convenient to reach from District 1 and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.690000, 10.776500})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho nhóm đông và phòng riêng.", "A 5% service charge applies to large groups and private rooms."))
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
                .id("tb-6")
                .name(of("Bàn 6", "Table for 6"))
                .seats(6)
                .minPeople(4)
                .maxPeople(6)
                .totalTables(12)
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
                .minPeople(15)
                .maxPeople(25)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
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
            .districtName(of("Q. 3", "District 3"))
            .wardName(of("P. 6", "Ward 6"))
            .addressLine(of("57 Võ Văn Tần, P. 6, Q. 3, TP. Hồ Chí Minh", "57 Vo Van Tan, Ward 6, District 3, Ho Chi Minh City"))
            .location(new double[]{106.690000, 10.776500})
            .name(of("Ngõ 8 Võ Văn Tần", "Ngo 8 Vo Van Tan"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Ẩm thực Thái - Đông Nam Á fusion, giá hợp lý tại Quận 3, TP. Hồ Chí Minh.", "Thai - Southeast Asian fusion cuisine at reasonable prices in District 3, Ho Chi Minh City."))
            .description(of("Ngõ 8 Võ Văn Tần là nhà hàng gọi món ẩm thực Thái - Đông Nam Á theo phong cách fusion tại 57 Võ Văn Tần, Q. 3, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi bật với lẩu Thái Tomyum, cá lóc nướng, gỏi xoài và pad Thái, không gian ấm cúng hiện đại có cả khu ngoài trời, " +
                "phù hợp cho tụ tập bạn bè, hẹn hò, ăn gia đình đến liên hoan công ty.",
                "Ngo 8 Vo Van Tan is a Thai - Southeast Asian fusion a la carte restaurant at 57 Vo Van Tan, District 3, Ho Chi Minh City. " +
                "It stands out with Thai Tomyum hotpot, grilled snakehead fish, mango salad and pad Thai, in a cozy modern space with an outdoor area, " +
                "suitable for friends gatherings, dates, family meals and company parties."))
            .phone("0931006005")
            .email("reservation@ngo8.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-ngo-8-vo-van-tan")
            .facebookPage("https://facebook.com/ngo8vovantan")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("100000"))
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
