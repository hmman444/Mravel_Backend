// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedBuffetChayVienDong.java
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
public class SeedBuffetChayVienDong {

    @Bean
    CommandLineRunner seedBuffetChayVienDongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBuffetChayVienDong] start");

            RestaurantDoc doc = restaurantBuffetChayVienDong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedBuffetChayVienDong] done");
        };
    }

    // =====================================================================
    //          BUFFET CHAY - KHÁCH SẠN VIỄN ĐÔNG (Q1, TP.HCM)
    // =====================================================================

    private static RestaurantDoc restaurantBuffetChayVienDong() {
        String slug = "buffet-chay-vien-dong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-1-normal-2372114766451.webp",
                of("Không gian Buffet Chay - Khách Sạn Viễn Đông", "Buffet Chay - Vien Dong Hotel space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-2-normal-2372114866452.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-3-normal-2372114966453.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-4-normal-2372115166454.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-5-normal-4218684766455.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-7-normal-4218685068161.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-15-normal-2372114566449.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-chay-khach-san-vien-dong-16-normal-2372114666450.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-1-normal-4218686372989.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-2-normal-4218686572990.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-3-normal-4218686472991.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-4-normal-4218686272992.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-5-normal-4218686172993.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-6-normal-4218686072994.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-7-normal-4218685872995.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-8-normal-4218685972996.webp",
                of("", ""), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-9-normal-4218685672997.webp",
                of("", ""), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-10-normal-4218685572998.webp",
                of("", ""), false, 17),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-11-normal-4218685472999.webp",
                of("", ""), false, 18),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-12-normal-4218685373000.webp",
                of("", ""), false, 19),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffetchayviendong-slide-13-normal-4218685273001.webp",
                of("", ""), false, 20)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VEGETARIAN")
                .name(of("Món chay", "Vegetarian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_CHAY")
                .name(of("Buffet chay", "Vegetarian buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (trưa 11:30-13:30 & tối 18:00-20:30) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 30, 20, 30),
            openingHour(DayOfWeek.TUESDAY,   11, 30, 20, 30),
            openingHour(DayOfWeek.WEDNESDAY, 11, 30, 20, 30),
            openingHour(DayOfWeek.THURSDAY,  11, 30, 20, 30),
            openingHour(DayOfWeek.FRIDAY,    11, 30, 20, 30),
            openingHour(DayOfWeek.SATURDAY,  11, 30, 20, 30),
            openingHour(DayOfWeek.SUNDAY,    11, 30, 20, 30)
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
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("VEGETARIAN_DAY",of("Ngày ăn chay, rằm, mùng một", "Vegetarian days, full moon")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",  of("Trang nhã", "Elegant")),
            ambience("PEACEFUL", of("Thanh tịnh", "Peaceful")),
            ambience("MODERN",   of("Hiện đại", "Modern"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu chay nấm thập cẩm", "Assorted mushroom vegetarian hotpot")),
            signatureDishL(of("Gỏi cuốn chay", "Vegetarian fresh spring rolls")),
            signatureDishL(of("Nem chay rán", "Fried vegetarian spring rolls")),
            signatureDishL(of("Đậu hũ sốt nấm", "Tofu with mushroom sauce")),
            signatureDishL(of("Cơm chiên hạt sen", "Lotus seed fried rice")),
            signatureDishL(of("Súp rong biển chay", "Vegetarian seaweed soup")),
            signatureDishL(of("Rau củ xào thập cẩm", "Stir-fried assorted vegetables")),
            signatureDishL(of("Chả giò chay", "Vegetarian crispy rolls")),
            signatureDishL(of("Bánh bao chay", "Vegetarian steamed buns")),
            signatureDishL(of("Chè dưỡng nhan", "Snow lotus sweet soup"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Buffettudo/T526/bf-chay-vien-dong-menu-7.jpg",
                of("", ""), false, 6)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_CHAY")
                .name(of("Buffet chay", "Vegetarian buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet chay người lớn", "Adult vegetarian buffet"))
                        .description(of("Buffet chay tự chọn với hơn 50 món rau củ, đậu hũ, nấm, lẩu và tráng miệng.", "Self-serve vegetarian buffet with over 50 dishes of vegetables, tofu, mushrooms, hotpot and desserts."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "vegetarian", "adult"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet chay trẻ em", "Children vegetarian buffet"))
                        .description(of("Suất buffet chay dành cho trẻ em (cao 1.0m - 1.3m), giá ưu đãi.", "Vegetarian buffet for children (1.0m - 1.3m tall) at a discounted price."))
                        .priceFrom(new BigDecimal("99000"))
                        .priceTo(new BigDecimal("99000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "vegetarian", "kids"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe khách sạn Viễn Đông", "Vien Dong Hotel parking lot"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe khách sạn", "Hotel basement parking"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ trông xe, ô tô đỗ tại lòng đường lân cận có thu phí.", "Security staff on hand; cars park on nearby streets for a fee."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "MC_SERVICE",
            "FREE_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "PRIVATE_ROOM",
            "LIVE_MUSIC",
            "VEGETARIAN_ONLY"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Buffet Chay - Khách Sạn Viễn Đông", "About Buffet Chay - Vien Dong Hotel")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng Buffet Chay - Khách Sạn Viễn Đông tọa lạc tại 275A Phạm Ngũ Lão, Phường Bến Thành, Quận 1, " +
                "ngay trung tâm Sài Gòn. Đây là địa chỉ quen thuộc của những thực khách yêu thích ẩm thực chay thanh đạm, " +
                "đặc biệt vào các ngày rằm, mùng một và mùa Vu Lan báo hiếu.",
                "Buffet Chay - Vien Dong Hotel is located at 275A Pham Ngu Lao, Ben Thanh Ward, District 1, " +
                "right in the heart of Saigon. It is a familiar spot for diners who love light vegetarian cuisine, " +
                "especially on full-moon days, the first of the lunar month, and the Vu Lan filial-piety season.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, trang nhã và thanh tịnh, sức chứa lên đến 150 khách cùng 2 phòng riêng " +
                "phục vụ tối đa 40 khách. Bố trí bàn ghế hợp lý, máy lạnh mát mẻ, phù hợp cho cả bữa ăn gia đình lẫn tiệc nhóm đông người.",
                "The restaurant offers a spacious, elegant and peaceful setting, with a capacity of up to 150 guests and 2 private rooms " +
                "for up to 40 guests. Well-arranged tables and cool air conditioning suit both family meals and large group parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Buffet chay tự chọn với hơn 50 món được chế biến hoàn toàn từ rau củ, nấm, đậu hũ và các nguyên liệu chay. " +
                "Thực khách có thể thưởng thức lẩu chay nấm thập cẩm, gỏi cuốn chay, nem chay rán, cơm chiên hạt sen cùng đa dạng món tráng miệng và chè.",
                "A self-serve vegetarian buffet with over 50 dishes made entirely from vegetables, mushrooms, tofu and plant-based ingredients. " +
                "Guests can enjoy assorted mushroom hotpot, fresh spring rolls, fried vegetarian rolls, lotus seed fried rice and a variety of desserts and sweet soups.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng lý tưởng cho các bữa ăn chay gia đình, tụ tập bạn bè, liên hoan công ty, sinh nhật hay tiếp khách. " +
                "Đặc biệt đông khách vào ngày rằm, mùng một và mùa Vu Lan, vì vậy quý khách nên đặt bàn trước để được phục vụ tốt nhất.",
                "The restaurant is ideal for vegetarian family meals, friends gatherings, company parties, birthdays or business meals. " +
                "It is especially busy on full-moon days, the first of the lunar month and the Vu Lan season, so guests should book in advance for the best service.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng có phòng riêng, hệ thống âm thanh ánh sáng, máy chiếu, dịch vụ MC và trang trí cho tiệc, " +
                "ghế em bé, máy lạnh, wifi miễn phí, hỗ trợ thanh toán thẻ và xuất hóa đơn VAT.",
                "The restaurant offers private rooms, sound and lighting systems, a projector, MC and event decoration services, " +
                "baby chairs, air conditioning, free wifi, card payment support and VAT invoicing.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nằm trong khu phố Tây Phạm Ngũ Lão, nhà hàng rất thuận tiện di chuyển, cách chợ Bến Thành và phố đi bộ Nguyễn Huệ chỉ vài phút. " +
                "Khu vực có nhiều bãi gửi xe, dễ dàng cho cả khách đi xe máy lẫn ô tô.",
                "Located in the Pham Ngu Lao backpacker area, the restaurant is very convenient to reach, just minutes from Ben Thanh Market and Nguyen Hue walking street. " +
                "The area has plenty of parking, easy for both motorbike and car travelers.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6920, 10.7686})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 150.000đ/chai.", "Outside wine corkage fee: 150,000 VND/bottle."))
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
            .vatPercent(new BigDecimal("8"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho đoàn đông và phòng riêng.", "5% service charge applies to large groups and private rooms."))
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
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn 8 VIP", "VIP Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(5)
                .depositPrice(new BigDecimal("300000"))
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
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc công ty và tiếp khách.", "Private room, suitable for company parties and business meals."))
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
            .districtName(of("Quận 1", "District 1"))
            .wardName(of("Phường Bến Thành", "Ben Thanh Ward"))
            .addressLine(of("275A Phạm Ngũ Lão, Phường Bến Thành, Quận 1, TP. Hồ Chí Minh", "275A Pham Ngu Lao, Ben Thanh Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.6920, 10.7686})
            .name(of("Buffet Chay - Khách Sạn Viễn Đông", "Buffet Chay - Vien Dong Hotel"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet chay hơn 50 món tại Khách Sạn Viễn Đông, trung tâm Quận 1.", "Vegetarian buffet with 50+ dishes at Vien Dong Hotel, central District 1."))
            .description(of("Buffet Chay - Khách Sạn Viễn Đông là nhà hàng buffet chay nổi tiếng tại trung tâm Quận 1, TP. Hồ Chí Minh. " +
                "Thực đơn buffet hơn 50 món chay đa dạng từ rau củ, nấm, đậu hũ đến lẩu và tráng miệng, " +
                "không gian thanh tịnh, trang nhã, phù hợp cho bữa ăn gia đình, tiệc nhóm và đặc biệt vào mùa Vu Lan.",
                "Buffet Chay - Vien Dong Hotel is a well-known vegetarian buffet restaurant in central District 1, Ho Chi Minh City. " +
                "Its buffet menu features 50+ vegetarian dishes from vegetables, mushrooms and tofu to hotpot and desserts, " +
                "in a peaceful, elegant setting suitable for family meals, group parties and especially the Vu Lan season."))
            .phone("0931006005")
            .email("reservation@buffetchayviendong.com")
            .website("https://example.com/buffet-chay-vien-dong")
            .facebookPage("https://facebook.com/buffetchayviendong")
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
