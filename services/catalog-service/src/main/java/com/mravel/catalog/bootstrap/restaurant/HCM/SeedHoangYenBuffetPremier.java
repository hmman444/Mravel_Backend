// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedHoangYenBuffetPremier.java
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
public class SeedHoangYenBuffetPremier {

    @Bean
    CommandLineRunner seedHoangYenBuffetPremierRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedHoangYenBuffetPremier] start");

            RestaurantDoc doc = restaurantHoangYenBuffetPremier();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedHoangYenBuffetPremier] done");
        };
    }

    // =====================================================================
    //            HOÀNG YẾN BUFFET PREMIER - NGUYỄN ĐÌNH CHIỂU (HCM)
    // =====================================================================

    private static RestaurantDoc restaurantHoangYenBuffetPremier() {
        String slug = "hoang-yen-buffet-premier-nguyen-dinh-chieu";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-1-normal-2599687010688.webp",
                of("Không gian Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu", "Hoang Yen Buffet Premier - Nguyen Dinh Chieu space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-2-normal-2599683810686.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-3-normal-2599683910687.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-4-normal-2599687110689.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-5-normal-2599687210690.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-6-normal-2599687310691.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-7-normal-2599687410692.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-8-normal-2599687510693.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-9-normal-2599687610694.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-10-normal-2599687710695.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-11-normal-2599687810696.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-12-normal-2599687910697.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-13-normal-2599688068104.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-14-normal-2599688168105.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-15-normal-2599688268106.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hoang-yen-buffet-primer-nguyen-dinh-chieu-16-normal-2599688368107.webp",
                of("", ""), false, 15)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("EUROPEAN")
                .name(of("Âu", "European"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_A_AU")
                .name(of("Buffet Á - Âu", "Asian - European Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 10:00 - 22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
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
            ambience("ELEGANT",   of("Trang nhã", "Elegant")),
            ambience("LUXURIOUS", of("Sang trọng", "Luxurious")),
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Hải sản tươi sống buffet", "Fresh seafood buffet")),
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef")),
            signatureDishL(of("Cá hồi Na Uy sashimi", "Norwegian salmon sashimi")),
            signatureDishL(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese")),
            signatureDishL(of("Sushi & sashimi", "Sushi & sashimi")),
            signatureDishL(of("Dimsum hấp", "Steamed dim sum")),
            signatureDishL(of("Lẩu Thái hải sản", "Thai seafood hotpot")),
            signatureDishL(of("Hàu sống Pháp", "French fresh oysters")),
            signatureDishL(of("Bánh ngọt & tráng miệng", "Pastries & desserts")),
            signatureDishL(of("Kem Ý các vị", "Assorted Italian gelato"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        // Trang này không nhúng ảnh bảng giá CDN riêng -> để rỗng.
        List<Image> menuImages = List.of();

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet Á - Âu", "Asian - European buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Premier người lớn", "Premier buffet - adult"))
                        .description(of("Buffet hơn 200 món Á - Âu, hải sản tươi sống, sushi, lẩu, nướng và tráng miệng.", "Buffet of over 200 Asian - European dishes, fresh seafood, sushi, hotpot, grill and desserts."))
                        .priceFrom(new BigDecimal("295000"))
                        .priceTo(new BigDecimal("515000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "seafood", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Premier trẻ em", "Premier buffet - child"))
                        .description(of("Áp dụng cho trẻ em theo chiều cao, suất buffet đầy đủ các món.", "Applies to children by height, full buffet portion."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("260000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "kids"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng trên đường Nguyễn Đình Chiểu", "Car park near the restaurant on Nguyen Dinh Chieu street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe máy tại tòa nhà", "Motorbike parking in the building basement"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn, gửi xe máy miễn phí, ô tô tính phí theo lượt.", "Security staff on hand; free motorbike parking, car parking charged per turn."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "MC_SERVICE",
            "FREE_MOTORBIKE_PARKING",
            "CAR_PARKING",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "PRIVATE_ROOM",
            "BIRTHDAY_GIFT",
            "ELEVATOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu", "About Hoang Yen Buffet Premier - Nguyen Dinh Chieu")),
            paragraph(ContentSection.OVERVIEW,
                of("Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu là nhà hàng buffet cao cấp thuộc hệ thống Hoàng Yến Group, " +
                "tọa lạc ngay trung tâm Quận 3, TP. Hồ Chí Minh. Nhà hàng nổi tiếng với thực đơn buffet Á - Âu phong phú " +
                "hơn 200 món, từ hải sản tươi sống, sushi, lẩu, món nướng đến các món tráng miệng tinh tế.",
                "Hoang Yen Buffet Premier - Nguyen Dinh Chieu is a premium buffet restaurant of the Hoang Yen Group, " +
                "located in the heart of District 3, Ho Chi Minh City. The restaurant is renowned for its rich Asian - European " +
                "buffet menu of over 200 dishes, from fresh seafood, sushi, hotpot and grilled items to refined desserts.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế hiện đại, sang trọng với sức chứa lớn lên đến hơn 250 khách, " +
                "có phòng riêng phục vụ tiệc và các sự kiện công ty. Bố trí bàn ghế rộng rãi cùng quầy buffet bắt mắt " +
                "tạo cảm giác thoải mái cho cả gia đình lẫn các nhóm khách đông người.",
                "The restaurant is designed in a modern, luxurious style with a large capacity of over 250 guests, " +
                "featuring private rooms for parties and corporate events. Spacious table arrangements and eye-catching " +
                "buffet stations create a comfortable atmosphere for both families and large groups.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Quầy buffet trải dài với hải sản tươi sống như tôm hùm, hàu Pháp, cá hồi Na Uy, cùng các món bò Mỹ nướng tảng, " +
                "sushi - sashimi, dimsum, lẩu Thái hải sản và khu tráng miệng với bánh ngọt, kem Ý đa dạng hương vị. " +
                "Nguyên liệu được tuyển chọn kỹ và chế biến liên tục để đảm bảo độ tươi ngon.",
                "The buffet line stretches with fresh seafood such as lobster, French oysters, Norwegian salmon, alongside grilled US beef, " +
                "sushi - sashimi, dim sum, Thai seafood hotpot and a dessert corner with pastries and assorted Italian gelato. " +
                "Ingredients are carefully selected and continuously prepared to ensure freshness.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best for which occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không gian rộng và thực đơn đa dạng, Hoàng Yến Buffet Premier phù hợp cho ăn gia đình, tụ tập bạn bè, " +
                "sinh nhật, tiệc công ty, liên hoan cuối năm hay tiếp khách đối tác. Nhà hàng nhận đặt bàn theo nhóm và " +
                "hỗ trợ trang trí cho các dịp đặc biệt.",
                "With its spacious setting and diverse menu, Hoang Yen Buffet Premier is ideal for family meals, friends gatherings, " +
                "birthdays, company parties, year-end celebrations or business meals. The restaurant accepts group bookings and " +
                "supports decoration for special occasions.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị máy chiếu, hệ thống âm thanh - ánh sáng, dịch vụ MC, ghế trẻ em, máy lạnh toàn bộ khu vực, " +
                "wifi miễn phí, xuất hóa đơn VAT và thanh toán thẻ Visa/Master. Có hỗ trợ trang trí tiệc và quà sinh nhật theo yêu cầu.",
                "The restaurant is equipped with a projector, sound and lighting system, MC service, kids chairs, full air conditioning, " +
                "free wifi, VAT invoicing and Visa/Master card payment. Party decoration and birthday gifts are available on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm tại 174A Nguyễn Đình Chiểu, Phường 6, Quận 3 - vị trí trung tâm, dễ dàng di chuyển từ Quận 1 và " +
                "các quận lân cận. Khu vực có chỗ gửi xe máy và bãi đỗ ô tô gần nhà hàng, thuận tiện cho khách đi xe riêng.",
                "The restaurant is located at 174A Nguyen Dinh Chieu, Ward 6, District 3 - a central location, easily reachable from " +
                "District 1 and neighboring districts. The area offers motorbike parking and a nearby car park, convenient for guests with their own vehicles.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.684370686111, 10.7722954656321})
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
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 200.000đ/chai.", "Outside wine corkage fee: 200,000 VND/bottle."))
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
            .serviceChargeNotes(of("Phụ phí phục vụ 5% áp dụng cho nhóm lớn và phòng riêng.", "5% service charge applies to large groups and private rooms."))
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
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn 8 VIP", "VIP Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(6)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Vị trí đẹp, ưu tiên phục vụ.", "Great location, priority service."))
                .build(),

            TableType.builder()
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(4)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(150)
                .allowedDurationsMinutes(List.of(120, 150))
                .note(of("Phòng riêng cho tiệc công ty, sinh nhật, tiếp khách.", "Private room for company parties, birthdays and business meals."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(90, 120, 150))
            .defaultDurationMinutes(120)
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
            .districtName(of("Quận 3", "District 3"))
            .wardName(of("Phường 6", "Ward 6"))
            .addressLine(of("174A Nguyễn Đình Chiểu, Phường 6, Quận 3, TP. Hồ Chí Minh", "174A Nguyen Dinh Chieu, Ward 6, District 3, Ho Chi Minh City"))
            .location(new double[]{106.684370686111, 10.7722954656321})
            .name(of("Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu", "Hoang Yen Buffet Premier - Nguyen Dinh Chieu"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet Á - Âu cao cấp hơn 200 món, hải sản tươi sống ngay trung tâm Quận 3.", "Premium Asian - European buffet of over 200 dishes with fresh seafood in the heart of District 3."))
            .description(of("Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu là nhà hàng buffet cao cấp thuộc Hoàng Yến Group, " +
                "phục vụ thực đơn buffet Á - Âu hơn 200 món với hải sản tươi sống, sushi, lẩu, món nướng và tráng miệng. " +
                "Không gian hiện đại, sang trọng, sức chứa lớn cùng phòng riêng phục vụ tiệc, phù hợp cho nhiều dịp tại trung tâm TP. Hồ Chí Minh.",
                "Hoang Yen Buffet Premier - Nguyen Dinh Chieu is a premium buffet restaurant of the Hoang Yen Group, " +
                "serving an Asian - European buffet of over 200 dishes with fresh seafood, sushi, hotpot, grilled items and desserts. " +
                "The modern, luxurious space with large capacity and private rooms suits a variety of occasions in central Ho Chi Minh City."))
            .phone("1900299268")
            .email("reservation@hoangyengroup.com")
            .website("https://hoangyengroup.com")
            .facebookPage("https://facebook.com/hoangyengroup")
            .bookingHotline("1900299268")
            .minPricePerPerson(new BigDecimal("295000"))
            .maxPricePerPerson(new BigDecimal("515000"))
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
