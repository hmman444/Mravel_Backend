// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedYakimonoPhanXichLong.java
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
public class SeedYakimonoPhanXichLong {

    @Bean
    CommandLineRunner seedYakimonoPhanXichLongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedYakimonoPhanXichLong] start");

            RestaurantDoc doc = restaurantYakimonoPhanXichLong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedYakimonoPhanXichLong] done");
        };
    }

    // =====================================================================
    //              YAKIMONO - PHAN XÍCH LONG (PHÚ NHUẬN, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantYakimonoPhanXichLong() {
        String slug = "yakimono-phan-xich-long";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-1-normal-4081234971873.webp",
                of("Không gian Yakimono - Phan Xích Long", "Yakimono - Phan Xich Long space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-2-normal-4081235471874.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-3-normal-4081236571875.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-4-normal-4081238671876.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-5-normal-4081310271877.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-6-normal-4081313671878.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-7-normal-4081243371879.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-pxl-8-normal-4081312571880.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/so-149-phan-xich-long--phuong-cau-kieu-107976551066893465.webp",
                of("149 Phan Xích Long, P. Cầu Kiệu", "149 Phan Xich Long, Cau Kieu Ward"), false, 8)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng kiểu Nhật", "Japanese BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("YAKINIKU")
                .name(of("Yakiniku & Yakimono", "Yakiniku & Yakimono"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 22, 0)
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
            ambience("MODERN",   of("Hiện đại", "Modern")),
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",  of("Tinh tế", "Elegant")),
            ambience("VIBRANT",  of("Sôi động", "Vibrant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(24)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(24)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò Wagyu nướng than", "Charcoal-grilled Wagyu beef")),
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket")),
            signatureDishL(of("Lưỡi bò nướng muối", "Grilled beef tongue with salt")),
            signatureDishL(of("Sườn bò nướng sốt Yakiniku", "Beef short rib with Yakiniku sauce")),
            signatureDishL(of("Ba chỉ heo nướng", "Grilled pork belly")),
            signatureDishL(of("Tôm sú nướng than", "Charcoal-grilled tiger prawn")),
            signatureDishL(of("Bạch tuộc nướng", "Grilled octopus")),
            signatureDishL(of("Sashimi cá hồi tươi", "Fresh salmon sashimi")),
            signatureDishL(of("Sushi tổng hợp", "Assorted sushi platter")),
            signatureDishL(of("Cơm trộn bò Yakimono", "Yakimono beef rice bowl"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%201.jpg", of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%202.jpg", of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%203.jpg", of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%204.jpg", of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%205.jpg", of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%206.jpg", of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%207.jpg", of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%208.jpg", of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%209.jpg", of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2010.jpg", of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2011.jpg", of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2012.jpg", of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2013.jpg", of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2014.jpg", of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2015.jpg", of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2016.jpg", of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2017.jpg", of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2018.jpg", of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2020.jpg", of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2021.jpg", of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2022.jpg", of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2023.jpg", of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2024.jpg", of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2025.jpg", of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2026.jpg", of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2027.jpg", of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20yakimono%20pxl/menu%20yakimono%2028.jpg", of("", ""), false, 26)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SET_MENU")
                .name(of("Set nướng Yakimono", "Yakimono grill sets"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set nướng 2 người", "Grill set for 2"))
                        .description(of("Set nướng cho 2 người: bò Mỹ, ba chỉ heo, hải sản, rau củ và cơm/canh ăn kèm.", "Grill set for 2: US beef, pork belly, seafood, vegetables with rice and soup."))
                        .priceFrom(new BigDecimal("499000"))
                        .priceTo(new BigDecimal("499000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "bbq", "couple"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set nướng 4 người", "Grill set for 4"))
                        .description(of("Set nướng cho 4 người: đa dạng bò, heo, hải sản nướng than cùng các món khai vị kiểu Nhật.", "Grill set for 4: assorted beef, pork and charcoal-grilled seafood with Japanese starters."))
                        .priceFrom(new BigDecimal("950000"))
                        .priceTo(new BigDecimal("950000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "bbq", "group"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc tuyến Phan Xích Long", "Car parking along Phan Xich Long street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ giữ xe cho khách.", "Staff assist guests with parking."))
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
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BBQ_TABLE_GRILL"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Yakimono - Phan Xích Long", "About Yakimono - Phan Xich Long")),
            paragraph(ContentSection.OVERVIEW,
                of("Yakimono - Phan Xích Long là nhà hàng nướng kiểu Nhật (yakiniku) tọa lạc trên tuyến phố ẩm thực sầm uất Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng mang phong cách Nhật Bản tinh tế, phục vụ gọi món với nguyên liệu tươi mới mỗi ngày, từ các loại thịt bò cao cấp đến hải sản và sashimi.",
                "Yakimono - Phan Xich Long is a Japanese-style grill (yakiniku) restaurant located on the bustling food street of Phan Xich Long, Phu Nhuan District, Ho Chi Minh City. " +
                "It brings a refined Japanese style, serving a la carte with fresh ingredients every day, from premium beef cuts to seafood and sashimi.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng hiện đại, ấm cúng và tinh tế theo phong cách Nhật, sức chứa khoảng 120 khách. " +
                "Yakimono có thêm phòng riêng phù hợp cho các nhóm khách, tiệc gia đình hay buổi tiếp đối tác cần sự riêng tư.",
                "The restaurant offers a modern, cozy and elegant Japanese-style space with a capacity of around 120 guests. " +
                "Yakimono also has private rooms suitable for groups, family parties or business meals that require privacy.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Yakimono là các loại thịt nướng than đặc trưng: bò Wagyu, ba chỉ bò Mỹ, lưỡi bò, sườn bò sốt Yakiniku cùng ba chỉ heo và hải sản tươi. " +
                "Thực khách còn có thể thưởng thức sashimi cá hồi, sushi tổng hợp và các set nướng dành cho nhóm 2-4 người.",
                "Yakimono's highlight is its signature charcoal-grilled meats: Wagyu beef, US beef brisket, beef tongue, short rib with Yakiniku sauce, along with pork belly and fresh seafood. " +
                "Diners can also enjoy salmon sashimi, assorted sushi and grill sets for groups of 2-4.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phục vụ chu đáo, Yakimono - Phan Xích Long phù hợp cho bữa ăn gia đình, hẹn hò, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách trang trọng.",
                "With a cozy atmosphere and attentive service, Yakimono - Phan Xich Long is ideal for family meals, dates, " +
                "friends gatherings, office parties, birthdays or formal business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Bàn nướng tại chỗ, có chỗ đỗ ô tô và xe máy, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Tables feature in-built grills, car and motorbike parking are available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 149 Phan Xích Long, P. Cầu Kiệu, Q. Phú Nhuận, nằm ngay trên tuyến phố ẩm thực nổi tiếng, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Q.1, Q.3, Bình Thạnh.",
                "The restaurant is located at 149 Phan Xich Long, Cau Kieu Ward, Phu Nhuan District, right on the famous food street, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts such as District 1, District 3 and Binh Thanh.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6893465, 10.797655})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho hóa đơn.", "A 5% service charge applies to the bill."))
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
                .id("pr-16")
                .name(of("Phòng riêng 16 khách", "Private room for 16 guests"))
                .seats(16)
                .minPeople(10)
                .maxPeople(24)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông/tiếp khách.", "Private room, suitable for large groups and business meals."))
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
            .wardName(of("P. Cầu Kiệu", "Cau Kieu Ward"))
            .addressLine(of("149 Phan Xích Long, P. Cầu Kiệu, Q. Phú Nhuận, TP. Hồ Chí Minh", "149 Phan Xich Long, Cau Kieu Ward, Phu Nhuan District, Ho Chi Minh City"))
            .location(new double[]{106.6893465, 10.797655})
            .name(of("Yakimono - Phan Xích Long", "Yakimono - Phan Xich Long"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng kiểu Nhật cao cấp trên phố Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh.", "Premium Japanese-style grill on Phan Xich Long street, Phu Nhuan District, Ho Chi Minh City."))
            .description(of("Yakimono - Phan Xích Long là nhà hàng nướng kiểu Nhật (yakiniku) tại 149 Phan Xích Long, Q. Phú Nhuận, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ gọi món với các loại thịt bò cao cấp, hải sản và sashimi tươi, không gian hiện đại tinh tế, " +
                "phù hợp cho nhiều dịp từ hẹn hò, ăn gia đình đến tiếp khách và liên hoan công ty.",
                "Yakimono - Phan Xich Long is a Japanese-style grill (yakiniku) restaurant at 149 Phan Xich Long, Phu Nhuan District, Ho Chi Minh City. " +
                "It serves a la carte with premium beef cuts, fresh seafood and sashimi in a modern, elegant space, " +
                "suitable for many occasions from dates and family meals to business meals and company parties."))
            .phone("0931006005")
            .email("reservation@yakimono.vn")
            .website("https://pasgo.vn/nha-hang/yakimono-phan-xich-long")
            .facebookPage("https://facebook.com/yakimono")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("500000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
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
