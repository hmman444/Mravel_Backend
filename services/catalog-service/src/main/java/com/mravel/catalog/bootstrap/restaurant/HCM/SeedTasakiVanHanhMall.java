// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedTasakiVanHanhMall.java
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
public class SeedTasakiVanHanhMall {

    @Bean
    CommandLineRunner seedTasakiVanHanhMallRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedTasakiVanHanhMall] start");

            RestaurantDoc tasaki = restaurantTasakiVanHanhMall();
            repo.findBySlugAndActiveTrue(tasaki.getSlug())
                .ifPresent(existing -> tasaki.setId(existing.getId()));
            repo.save(tasaki);
            log.info(">>> upsert: {}", tasaki.getSlug());

            log.info(">>> [SeedTasakiVanHanhMall] done");
        };
    }

    // =====================================================================
    //                 TASAKI BBQ - VẠN HẠNH MALL (Q.10, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantTasakiVanHanhMall() {
        String slug = "tasaki-van-hanh-mall";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-1-normal-329682825918.webp",
                of("Không gian Tasaki BBQ Vạn Hạnh Mall", "Tasaki BBQ Van Hanh Mall space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-2-normal-329683125919.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-3-normal-329684225920.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-4-normal-329684325921.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-5-normal-329684725922.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-6-normal-329685225923.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-7-normal-329685725924.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-8-normal-329686125925.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-slide-1up-normal-373788325926.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-slide-2up-normal-373788425927.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-slide-3up-normal-373788525928.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-tasaki-bbq-van-hanh-mall-slide-4up-normal-373788725929.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/van-hanh-mall--tang-6--so-11-duong-su-van-hanh--p--12--q--10-10770638106669891.webp",
                of("Vị trí Tasaki BBQ tại Vạn Hạnh Mall", "Tasaki BBQ location at Van Hanh Mall"), false, 12)
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
                .name(of("Nướng BBQ", "BBQ Grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (T2 -> CN: 10:00 - 22:00) ----------
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
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("ELEGANT", of("Trang nhã", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly")),
            signatureDishL(of("Bò Wagyu nướng", "Grilled Wagyu beef")),
            signatureDishL(of("Lưỡi bò nướng", "Grilled beef tongue")),
            signatureDishL(of("Ba chỉ heo nướng sốt Tasaki", "Grilled pork belly with Tasaki sauce")),
            signatureDishL(of("Tôm sú nướng", "Grilled tiger prawn")),
            signatureDishL(of("Bạch tuộc nướng", "Grilled octopus")),
            signatureDishL(of("Nấm các loại nướng", "Assorted grilled mushrooms")),
            signatureDishL(of("Cơm trộn Bibimbap", "Bibimbap mixed rice")),
            signatureDishL(of("Canh kim chi", "Kimchi soup")),
            signatureDishL(of("Chân gà nướng sốt Hàn", "Grilled chicken feet with Korean sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-1.png", of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-2.png", of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-3.png", of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-4.png", of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-5.png", of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-6.png", of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-7.png", of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-8.png", of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-9.png", of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-10.png", of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-11.png", of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-12.png", of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-13.png", of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-14.png", of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-15.png", of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-16.png", of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-17.png", of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-18.png", of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-19.png", of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-20.png", of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-21.png", of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-22.png", of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-23.png", of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-24.png", of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-25.png", of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-26.png", of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-27.png", of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-28.png", of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-29.png", of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/TasakiBBQ/tasaki-mn-menu-30.png", of("", ""), false, 29)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet nướng", "BBQ Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet nướng tiêu chuẩn", "Standard BBQ buffet"))
                        .description(of("Buffet nướng các loại thịt bò, heo, gà, hải sản, rau củ kèm nước chấm đặc trưng kiểu Nhật.", "BBQ buffet with assorted beef, pork, chicken, seafood and vegetables, served with signature Japanese-style sauces."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet nướng cao cấp (Wagyu)", "Premium BBQ buffet (Wagyu)"))
                        .description(of("Buffet nướng nâng cấp với bò Wagyu, hải sản tươi và nhiều món đặc sắc.", "Upgraded BBQ buffet featuring Wagyu beef, fresh seafood and many premium items."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "wagyu", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm đỗ xe Vạn Hạnh Mall", "Van Hanh Mall basement parking"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm đỗ xe Vạn Hạnh Mall", "Van Hanh Mall basement parking"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Đỗ xe tại hầm trung tâm thương mại Vạn Hạnh Mall, thu phí theo quy định của TTTM.", "Parking at Van Hanh Mall basement; fees apply per the mall's regulations."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE",
            "SET_LUNCH",
            "CAR_PARKING",
            "MOTORBIKE_PARKING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "SMOKE_EXTRACTOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Tasaki BBQ Vạn Hạnh Mall", "About Tasaki BBQ Van Hanh Mall")),
            paragraph(ContentSection.OVERVIEW,
                of("Tasaki BBQ Vạn Hạnh Mall là nhà hàng nướng phong cách Nhật Bản tọa lạc ngay trong trung tâm thương mại Vạn Hạnh Mall, Quận 10, TP. Hồ Chí Minh. " +
                "Với thực đơn buffet và gọi món đa dạng cùng nguyên liệu tươi ngon, Tasaki BBQ là điểm hẹn quen thuộc cho những bữa tiệc nướng sôi động giữa lòng thành phố.",
                "Tasaki BBQ Van Hanh Mall is a Japanese-style grill restaurant located right inside Van Hanh Mall, District 10, Ho Chi Minh City. " +
                "With a diverse buffet and a la carte menu and fresh ingredients, Tasaki BBQ is a familiar gathering spot for lively grill parties in the heart of the city.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng được thiết kế hiện đại, sạch sẽ với hệ thống hút khói tại bàn giúp không gian luôn thoáng đãng. " +
                "Sức chứa lên tới 150 khách cùng các phòng riêng linh hoạt, phù hợp cho cả nhóm nhỏ lẫn những buổi tiệc đông người.",
                "The restaurant is designed in a modern, clean style with table-top smoke extractors that keep the space airy. " +
                "With a capacity of up to 150 guests and flexible private rooms, it suits both small groups and large parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn Tasaki BBQ nổi bật với ba chỉ bò Mỹ, bò Wagyu, lưỡi bò, ba chỉ heo ướp sốt đặc trưng, cùng nhiều loại hải sản và rau củ tươi. " +
                "Các loại nước chấm và sốt ướp kiểu Nhật là điểm nhấn tạo nên hương vị riêng cho từng món nướng.",
                "Tasaki BBQ's menu features US beef belly, Wagyu beef, beef tongue, marinated pork belly, along with assorted seafood and fresh vegetables. " +
                "Japanese-style dipping sauces and marinades are the highlight that gives each grilled item its distinctive flavor.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best for which occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian sôi động và dễ chịu của Tasaki BBQ thích hợp cho tụ họp bạn bè, ăn uống cùng gia đình, liên hoan văn phòng, sinh nhật hay các buổi tiệc công ty. " +
                "Nhà hàng cũng hỗ trợ trang trí và dịch vụ MC cho những dịp đặc biệt.",
                "Tasaki BBQ's lively yet comfortable atmosphere is ideal for gatherings with friends, family meals, office parties, birthdays and company events. " +
                "The restaurant also offers decoration and MC services for special occasions.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm tại tầng 6 Vạn Hạnh Mall, số 11 đường Sư Vạn Hạnh, Phường 12, Quận 10 - vị trí trung tâm, dễ tìm và thuận tiện di chuyển. " +
                "Khách có thể gửi xe ngay tại hầm của trung tâm thương mại và kết hợp mua sắm, giải trí.",
                "The restaurant is on the 6th floor of Van Hanh Mall, No. 11 Su Van Hanh St., Ward 12, District 10 - a central, easy-to-find and convenient location. " +
                "Guests can park right in the mall's basement and combine dining with shopping and entertainment.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Khách nên đặt bàn trước để được phục vụ tốt nhất, đặc biệt vào cuối tuần và các dịp lễ. " +
                "Nhà hàng có thể yêu cầu đặt cọc với các đoàn đông khách hoặc yêu cầu trang trí riêng, và xuất hóa đơn VAT theo yêu cầu.",
                "Guests should book in advance for the best service, especially on weekends and holidays. " +
                "The restaurant may require a deposit for large groups or special decoration requests, and issues VAT invoices on request.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.669891, 10.770638})
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
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc yêu cầu trang trí đặc biệt.", "Applies to bookings of 8 or more guests or requests for special decoration."))
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho một số khung giờ/dịp.", "A 5% service charge applies to certain time slots/occasions."))
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
                .id("tb-6-vip")
                .name(of("Bàn 6 VIP", "VIP Table for 6"))
                .seats(6)
                .minPeople(5)
                .maxPeople(6)
                .totalTables(4)
                .depositPrice(new BigDecimal("400000"))
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
                .maxPeople(30)
                .totalTables(4)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc công ty/sinh nhật.", "Private room, suitable for company parties and birthdays."))
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
            .districtName(of("Quận 10", "District 10"))
            .wardName(of("Phường 12", "Ward 12"))
            .addressLine(of("Tầng 6, Vạn Hạnh Mall, Số 11 đường Sư Vạn Hạnh, P. 12, Q. 10, TP. Hồ Chí Minh", "6th floor, Van Hanh Mall, No. 11 Su Van Hanh St., Ward 12, District 10, Ho Chi Minh City"))
            .location(new double[]{106.669891, 10.770638})
            .name(of("Tasaki BBQ - Vạn Hạnh Mall", "Tasaki BBQ - Van Hanh Mall"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng phong cách Nhật Bản tại Vạn Hạnh Mall, Quận 10 - buffet & gọi món, nguyên liệu tươi ngon.", "Japanese-style grill restaurant at Van Hanh Mall, District 10 - buffet & a la carte with fresh ingredients."))
            .description(of("Tasaki BBQ - Vạn Hạnh Mall là nhà hàng nướng phong cách Nhật Bản nằm tại tầng 6 trung tâm thương mại Vạn Hạnh Mall, Quận 10, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ buffet và gọi món với đa dạng các loại thịt bò, heo, gà, hải sản tươi cùng hệ thống nướng tại bàn, " +
                "mang đến trải nghiệm ẩm thực sôi động, phù hợp cho gia đình, bạn bè và các buổi liên hoan.",
                "Tasaki BBQ - Van Hanh Mall is a Japanese-style grill restaurant located on the 6th floor of Van Hanh Mall, District 10, Ho Chi Minh City. " +
                "It serves buffet and a la carte options with a wide variety of beef, pork, chicken and fresh seafood grilled at the table, " +
                "delivering a lively dining experience well suited for families, friends and gatherings."))
            .phone("0931006005")
            .email("reservation@tasakibbq.vn")
            .website("https://example.com/tasaki-van-hanh-mall")
            .facebookPage("https://facebook.com/tasakibbq")
            .bookingHotline("0931006005")
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
