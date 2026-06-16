// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedTamRieuPhanXichLong.java
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
public class SeedTamRieuPhanXichLong {

    @Bean
    CommandLineRunner seedTamRieuPhanXichLongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedTamRieuPhanXichLong] start");

            RestaurantDoc tamRieu = restaurantTamRieuPhanXichLong();
            repo.findBySlugAndActiveTrue(tamRieu.getSlug())
                .ifPresent(existing -> tamRieu.setId(existing.getId()));
            repo.save(tamRieu);
            log.info(">>> upsert: {}", tamRieu.getSlug());

            log.info(">>> [SeedTamRieuPhanXichLong] done");
        };
    }

    // =====================================================================
    //               TÁM RIÊU - PHAN XÍCH LONG (PHÚ NHUẬN, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantTamRieuPhanXichLong() {
        String slug = "tam-rieu-phan-xich-long";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-1-normal-1701525547718.webp",
                of("Không gian Tám Riêu - Phan Xích Long", "Tam Rieu - Phan Xich Long space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-2-normal-1701525847719.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-3-normal-1701525947720.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-4-normal-1701526047721.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-5-normal-1701527047722.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-6-normal-1701528347724.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-7-normal-1701528647725.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-8-normal-1701528847726.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-9-normal-1701528947727.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-10-normal-1701529047728.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-11-normal-1701529247729.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tam-rieu-phan-xich-long-slide-12-normal-1701529447731.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt", "Vietnamese"))
                .region("VIETNAM")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE_COUNTRYSIDE")
                .name(of("Đặc sản đồng quê", "Vietnamese countryside specialties"))
                .region("VIETNAM")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu cua đồng", "Field crab hotpot"))
                .region("VIETNAM")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 21:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 21, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 21, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 21, 30)
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
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COUPLE",        of("Hẹn hò", "Date"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",  of("Mộc mạc đồng quê", "Rustic countryside")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu cua đồng Tám Riêu", "Tam Rieu field crab hotpot")),
            signatureDishL(of("Riêu cua đồng quê", "Countryside crab riêu")),
            signatureDishL(of("Cá kho tộ", "Braised fish in clay pot")),
            signatureDishL(of("Gà ta hấp lá chanh", "Steamed free-range chicken with lime leaves")),
            signatureDishL(of("Tôm rang me", "Tamarind glazed prawns")),
            signatureDishL(of("Rau muống xào tỏi", "Stir-fried water spinach with garlic")),
            signatureDishL(of("Đậu hũ chiên giòn", "Crispy fried tofu")),
            signatureDishL(of("Canh chua cá lóc", "Sour snakehead fish soup")),
            signatureDishL(of("Cà pháo mắm tôm", "Pickled eggplant with shrimp paste")),
            signatureDishL(of("Cơm cháy kho quẹt", "Crispy rice with kho quet dip"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/tamrieut4/tam-rieu-8.jpg",
                of("", ""), false, 7)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SIGNATURE")
                .name(of("Món đặc sắc", "Signature dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu cua đồng Tám Riêu", "Tam Rieu field crab hotpot"))
                        .description(of("Nồi lẩu cua đồng đậm vị quê, nước dùng ngọt thanh ăn kèm rau tươi, bún và đậu hũ.", "Field crab hotpot with a clear, sweet broth served with fresh vegetables, vermicelli and tofu."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cá kho tộ", "Braised fish in clay pot"))
                        .description(of("Cá kho tộ kiểu Nam Bộ, đậm đà, ăn cùng cơm trắng nóng hổi.", "Southern-style braised fish in clay pot, rich in flavor, served with hot steamed rice."))
                        .priceFrom(new BigDecimal("89000"))
                        .priceTo(new BigDecimal("129000"))
                        .unit(of("phần", "dish"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("vietnamese", "rice"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Phan Xích Long và bãi gần nhà hàng", "Car parking on Phan Xich Long street and lot near the restaurant"))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Tám Riêu - Phan Xích Long", "About Tam Rieu - Phan Xich Long")),
            paragraph(ContentSection.OVERVIEW,
                of("Tám Riêu - Phan Xích Long là nhà hàng ẩm thực Việt với menu món ăn đa dạng, khẩu vị đậm đà, đượm hương quê, " +
                "tọa lạc ngay tuyến phố ẩm thực sầm uất Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trải nghiệm những món ăn dân dã, quen thuộc được chế biến chỉn chu, hợp túi tiền.",
                "Tam Rieu - Phan Xich Long is a Vietnamese restaurant with a diverse menu, bold and rustic countryside flavors, " +
                "located right on the bustling Phan Xich Long food street, Phu Nhuan District, Ho Chi Minh City. " +
                "It offers homely, familiar dishes prepared with care and at affordable prices.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian quán rộng rãi, thoáng mát, mộc mạc đậm chất đồng quê với sức chứa lên đến khoảng 200 khách. " +
                "Tám Riêu có khu vực ngoài trời cùng các phòng riêng, phù hợp cho cả nhóm nhỏ lẫn các nhóm đông, tiệc gia đình và liên hoan.",
                "The restaurant offers a spacious, airy and rustic countryside space with a capacity of around 200 guests. " +
                "Tam Rieu has outdoor seating along with private rooms, suitable for both small parties and large groups, family gatherings and celebrations.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Tám Riêu là lẩu cua đồng và riêu cua quê hương cùng loạt món Việt quen thuộc như cá kho tộ, " +
                "gà ta hấp lá chanh, tôm rang me, canh chua cá lóc, cơm cháy kho quẹt. Mỗi món đều giữ trọn hương vị đậm đà, dân dã.",
                "Tam Rieu's highlight is its field crab hotpot and countryside crab riêu, alongside familiar Vietnamese dishes such as " +
                "braised fish in clay pot, steamed free-range chicken with lime leaves, tamarind prawns, sour fish soup and crispy rice with kho quet dip. " +
                "Each dish keeps its bold, homely flavor.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, sôi động và phục vụ tận tình, Tám Riêu - Phan Xích Long phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy, vibrant atmosphere and attentive service, Tam Rieu - Phan Xich Long is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, outdoor seating, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 235 Phan Xích Long, Phường 2, Q. Phú Nhuận, ngay trung tâm tuyến phố ẩm thực Phan Xích Long, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Bình Thạnh, Quận 1, Quận 3.",
                "The restaurant is located at 235 Phan Xich Long, Ward 2, Phu Nhuan District, right at the heart of the Phan Xich Long food street, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts such as Binh Thanh, District 1 and District 3.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.686137899762, 10.7990970075809})
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
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 200.000đ/chai.", "Spirits corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("100000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 100.000đ/chai.", "Wine corkage fee: 100,000 VND/bottle."))
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
                .id("tb-6")
                .name(of("Bàn 6", "Table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(20)
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
                .minPeople(20)
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
            .districtName(of("Q. Phú Nhuận", "Phu Nhuan District"))
            .wardName(of("Phường 2", "Ward 2"))
            .addressLine(of("235 Phan Xích Long, Phường 2, Q. Phú Nhuận, TP. Hồ Chí Minh", "235 Phan Xich Long, Ward 2, Phu Nhuan District, Ho Chi Minh City"))
            .location(new double[]{106.686137899762, 10.7990970075809})
            .name(of("Tám Riêu - Phan Xích Long", "Tam Rieu - Phan Xich Long"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Ẩm thực Việt đậm đà, đượm hương quê, giá hợp lý tại phố Phan Xích Long, Q. Phú Nhuận.", "Bold, rustic Vietnamese cuisine at affordable prices on Phan Xich Long street, Phu Nhuan District."))
            .description(of("Tám Riêu - Phan Xích Long là nhà hàng ẩm thực Việt với menu đa dạng, khẩu vị đậm đà, đượm hương quê tại 235 Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi bật với lẩu cua đồng, riêu cua quê hương cùng nhiều món Việt dân dã, không gian rộng rãi, thoáng mát, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Tam Rieu - Phan Xich Long is a Vietnamese restaurant with a diverse menu and bold, rustic countryside flavors at 235 Phan Xich Long, Phu Nhuan District, Ho Chi Minh City. " +
                "It stands out for its field crab hotpot and countryside crab riêu along with many homely Vietnamese dishes in a spacious, airy space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("19006005")
            .email("reservation@tamrieu.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-tam-rieu-phan-xich-long")
            .facebookPage("https://facebook.com/tamrieu")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("30000"))
            .maxPricePerPerson(new BigDecimal("150000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
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
