// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedGalbiHousePhanXichLong.java
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
public class SeedGalbiHousePhanXichLong {

    @Bean
    CommandLineRunner seedGalbiHousePhanXichLongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGalbiHousePhanXichLong] start");

            RestaurantDoc galbi = restaurantGalbiHousePhanXichLong();
            repo.findBySlugAndActiveTrue(galbi.getSlug())
                .ifPresent(existing -> galbi.setId(existing.getId()));
            repo.save(galbi);
            log.info(">>> upsert: {}", galbi.getSlug());

            log.info(">>> [SeedGalbiHousePhanXichLong] done");
        };
    }

    // =====================================================================
    //          GALBI HOUSE - PHAN XÍCH LONG (PHÚ NHUẬN, HCM)
    //          Buffet nướng lẩu Hàn Quốc
    // =====================================================================

    private static RestaurantDoc restaurantGalbiHousePhanXichLong() {
        String slug = "galbi-house-phan-xich-long";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-1-normal-2181671752656.webp",
                of("Không gian Galbi House Phan Xích Long", "Galbi House Phan Xich Long space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-2-normal-2181671852657.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-3-normal-2181671952658.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-4-normal-2181672052659.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-5-normal-2181672152660.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-6-normal-2181672252661.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-7-normal-2181672352662.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-8-normal-2181672452663.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-9-normal-2181672552664.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-10-normal-2181672652665.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-11-normal-2181672752666.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-galbi-house-phan-xich-long-12-normal-2181672852667.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN")
                .name(of("Hàn Quốc", "Korean"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET")
                .name(of("Buffet nướng lẩu", "Grill & Hotpot Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 22:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 22, 30)
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
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("ELEGANT", of("Tinh tế", "Elegant"))
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
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket")),
            signatureDishL(of("Sườn bò Galbi tẩm ướp", "Marinated Galbi beef short ribs")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Hàn", "Korean-style grilled pork belly")),
            signatureDishL(of("Bò Mỹ cuộn nấm kim châm", "US beef wrapped enoki mushroom")),
            signatureDishL(of("Lẩu kim chi Hàn Quốc", "Korean kimchi hotpot")),
            signatureDishL(of("Lẩu nấm thanh đạm", "Light mushroom hotpot")),
            signatureDishL(of("Hải sản nướng tổng hợp", "Assorted grilled seafood")),
            signatureDishL(of("Tokbokki sốt cay", "Spicy tteokbokki")),
            signatureDishL(of("Cơm trộn Bibimbap", "Bibimbap mixed rice")),
            signatureDishL(of("Kim chi & banchan ăn kèm", "Kimchi & assorted banchan"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(1).jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(2).jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(3).jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(4).jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(5).jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(6).jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(7).jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/galbi/menu%20(8).jpg",
                of("", ""), false, 7)
        );

        // ---------- Menu cấu trúc (các suất buffet) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet nướng lẩu Hàn Quốc", "Korean grill & hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Ưa Chọn T2-CN", "Popular Buffet (Mon-Sun)"))
                        .description(of("Suất buffet nướng lẩu Hàn Quốc cơ bản, áp dụng cả tuần với đa dạng thịt nướng, lẩu và món ăn kèm.", "Standard Korean grill & hotpot buffet, available all week with a variety of grilled meats, hotpot and side dishes."))
                        .priceFrom(new BigDecimal("279000"))
                        .priceTo(new BigDecimal("279000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "korean", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Đặc Biệt T2-CN", "Special Buffet (Mon-Sun)"))
                        .description(of("Suất buffet đặc biệt với thêm các loại thịt bò Mỹ, hải sản và món Hàn cao cấp hơn.", "Special buffet with additional US beef, seafood and premium Korean dishes."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("349000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "korean", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Thượng Hạng T2-CN", "Premium Buffet (Mon-Sun)"))
                        .description(of("Suất buffet thượng hạng với đầy đủ thực đơn nướng lẩu Hàn Quốc, bò Mỹ và hải sản tươi sống.", "Premium buffet with the full Korean grill & hotpot menu, US beef and fresh seafood."))
                        .priceFrom(new BigDecimal("399000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "korean", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Phan Xích Long và khu vực lân cận", "Car parking on Phan Xich Long street and nearby"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hướng dẫn đỗ xe, miễn phí cho khách.", "Parking assistance available, free of charge for guests."))
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
            "BUFFET_SERVICE",
            "SMOKELESS_GRILL",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Galbi House Phan Xích Long", "About Galbi House Phan Xich Long")),
            paragraph(ContentSection.OVERVIEW,
                of("Galbi House Phan Xích Long là nhà hàng buffet nướng lẩu theo phong cách Hàn Quốc tọa lạc ngay trên tuyến phố ẩm thực sầm uất Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trải nghiệm buffet đậm chất xứ sở kim chi với đa dạng thịt nướng, các loại lẩu và món ăn kèm đặc trưng.",
                "Galbi House Phan Xich Long is a Korean-style grill and hotpot buffet restaurant located right on the bustling Phan Xich Long food street, Phu Nhuan District, Ho Chi Minh City. " +
                "It offers an authentic taste of Korea with a wide variety of grilled meats, hotpots and signature side dishes.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế hiện đại, ấm cúng và trang nhã, sức chứa lên đến khoảng 180 khách. " +
                "Mỗi bàn được trang bị bếp nướng có hệ thống ống hút khói riêng, giúp bữa tiệc nướng luôn sạch sẽ, thoáng mát. Galbi House còn có phòng riêng phù hợp cho nhóm đông và tiệc công ty.",
                "The restaurant is designed in a modern, cozy and elegant style with a capacity of around 180 guests. " +
                "Each table is fitted with a grill and its own smoke extraction system, keeping the grilling experience clean and airy. Galbi House also has private rooms suitable for large groups and company parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Galbi House là các loại thịt bò Mỹ, sườn bò Galbi tẩm ướp, ba chỉ heo nướng kiểu Hàn cùng lẩu kim chi và lẩu nấm. " +
                "Thực khách có thể chọn các suất buffet T2-CN từ 279K (Ưa Chọn), 349K (Đặc Biệt) đến 399K (Thượng Hạng), kèm theo nhiều món ăn nhẹ và banchan đặc trưng Hàn Quốc.",
                "Galbi House's highlights are US beef, marinated Galbi short ribs, Korean-style grilled pork belly, kimchi hotpot and mushroom hotpot. " +
                "Diners can choose from the Mon-Sun buffet tiers at 279K (Popular), 349K (Special) and 399K (Premium), served with many light dishes and signature Korean banchan.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và phục vụ chu đáo, Galbi House Phan Xích Long phù hợp cho bữa ăn gia đình, tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật.",
                "With a vibrant atmosphere and attentive service, Galbi House Phan Xich Long is ideal for family meals, friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bếp nướng không khói, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, smokeless grills, sound & light systems and decoration / MC support for events. " +
                "Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 215 Phan Xích Long, P. 2, Q. Phú Nhuận, ngay khu phố ẩm thực nổi tiếng, thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at 215 Phan Xich Long, Ward 2, Phu Nhuan District, right in the famous food street area, convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.686717317998, 10.7987811754937})
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
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% trên tổng hóa đơn.", "A 5% service charge applies to the total bill."))
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
                .totalTables(18)
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
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
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
            .wardName(of("P. 2", "Ward 2"))
            .addressLine(of("215 Phan Xích Long, P. 2, Q. Phú Nhuận, TP. Hồ Chí Minh", "215 Phan Xich Long, Ward 2, Phu Nhuan District, Ho Chi Minh City"))
            .location(new double[]{106.686717317998, 10.7987811754937})
            .name(of("Galbi House Phan Xích Long", "Galbi House Phan Xich Long"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng lẩu Hàn Quốc tại phố Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh.", "Korean grill & hotpot buffet on Phan Xich Long street, Phu Nhuan District, Ho Chi Minh City."))
            .description(of("Galbi House Phan Xích Long là nhà hàng buffet nướng lẩu theo phong cách Hàn Quốc tại 215 Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ đa dạng thịt bò Mỹ, sườn Galbi tẩm ướp, ba chỉ heo nướng cùng lẩu kim chi, lẩu nấm và nhiều món ăn kèm đặc trưng, " +
                "không gian hiện đại với bếp nướng không khói, phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Galbi House Phan Xich Long is a Korean-style grill and hotpot buffet restaurant at 215 Phan Xich Long, Phu Nhuan District, Ho Chi Minh City. " +
                "It serves a variety of US beef, marinated Galbi short ribs, grilled pork belly along with kimchi hotpot, mushroom hotpot and many signature side dishes, " +
                "in a modern space with smokeless grills, suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@galbihouse.vn")
            .website("https://pasgo.vn/nha-hang/buffet-lau-nuong-galbi-house-phan-xich-long")
            .facebookPage("https://facebook.com/galbihouse")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("279000"))
            .maxPricePerPerson(new BigDecimal("399000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.MODERATE)
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
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}
