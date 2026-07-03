// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedCocoGrillNamKyKhoiNghia.java
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
public class SeedCocoGrillNamKyKhoiNghia {

    @Bean
    CommandLineRunner seedCocoGrillNamKyKhoiNghiaRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedCocoGrillNamKyKhoiNghia] start");

            RestaurantDoc coco = restaurantCocoGrillNamKyKhoiNghia();
            repo.findBySlugAndActiveTrue(coco.getSlug())
                .ifPresent(existing -> coco.setId(existing.getId()));
            repo.save(coco);
            log.info(">>> upsert: {}", coco.getSlug());

            log.info(">>> [SeedCocoGrillNamKyKhoiNghia] done");
        };
    }

    // =====================================================================
    //          COCO GRILL SAIGON - NAM KỲ KHỞI NGHĨA (Q.3, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantCocoGrillNamKyKhoiNghia() {
        String slug = "coco-grill-nam-ky-khoi-nghia";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-1-normal-2579370067271.webp",
                of("Không gian CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa", "CoCo Grill Saigon - Nam Ky Khoi Nghia space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-2-normal-2578694367244.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-3-normal-2579369667267.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-4-normal-2579369767268.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-5-normal-2579370767272.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-6-normal-2579370967274.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-7-normal-2579371067275.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-8-normal-2672258967277.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-9-normal-2672258267278.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-10-normal-2579372767280.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-11-normal-2672258467281.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-12-normal-2672257367282.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-13-normal-2579373067283.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-14-normal-2672257467284.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-15-normal-2579374267285.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-16-normal-2579369867269.webp",
                of("", ""), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-17-normal-2672251167270.webp",
                of("", ""), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-18-normal-2579370867273.webp",
                of("", ""), false, 17),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-19-normal-2579371167276.webp",
                of("", ""), false, 18),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-20-normal-2672257967279.webp",
                of("", ""), false, 19),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-21-normal-2672258367286.webp",
                of("", ""), false, 20),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-22-normal-2579376467287.webp",
                of("", ""), false, 21),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-coco-23-normal-2579377367288.webp",
                of("", ""), false, 22)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "BBQ / Grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("STEAKHOUSE")
                .name(of("Bít tết / Steak", "Steakhouse"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_EUROPEAN")
                .name(of("Á - Âu", "Asian - European"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (18:00 - 23:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    18, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   18, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 18, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  18, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    18, 0, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  18, 0, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    18, 0, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("PROPOSAL",      of("Cầu hôn lãng mạn", "Romantic proposal"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",   of("Sang trọng", "Elegant")),
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("ROMANTIC",  of("Lãng mạn", "Romantic")),
            ambience("LUXURIOUS", of("Đẳng cấp", "Luxurious")),
            ambience("COZY",      of("Ấm cúng", "Cozy"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef block")),
            signatureDishL(of("Sườn bò nướng BBQ", "BBQ grilled beef ribs")),
            signatureDishL(of("Steak thăn nội bò Mỹ", "US beef tenderloin steak")),
            signatureDishL(of("Ba chỉ bò nướng Hàn Quốc", "Korean-style grilled beef brisket")),
            signatureDishL(of("Hải sản nướng tổng hợp", "Mixed grilled seafood platter")),
            signatureDishL(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Salad cá hồi", "Salmon salad")),
            signatureDishL(of("Foie gras áp chảo", "Pan-seared foie gras")),
            signatureDishL(of("Cơm chiên hải sản", "Seafood fried rice"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/Coco%20Grill/COCO%20A%20lA%20CARTE%20MENU_page-0001.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/Coco%20Grill/COCO%20A%20lA%20CARTE%20MENU_page-0002.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/Coco%20Grill/COCO%20A%20lA%20CARTE%20MENU_page-0003.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/Coco%20Grill/COCO%20A%20lA%20CARTE%20MENU_page-0004.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/Coco%20Grill/COCO%20A%20lA%20CARTE%20MENU_page-0005.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Cocogrill/set-menu-lu-hanh-1.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Cocogrill/set-menu-lu-hanh-2.jpg",
                of("", ""), false, 6)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILL")
                .name(of("Món nướng đặc trưng", "Signature grill"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò Mỹ nướng tảng", "Grilled US beef block"))
                        .description(of("Tảng thịt bò Mỹ tươi chọn lọc, nướng trên than hồng, ăn kèm sốt đặc trưng.", "Selected fresh US beef block grilled over charcoal, served with signature sauce."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "beef", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese"))
                        .description(of("Tôm hùm tươi nướng phô mai béo ngậy, một trong những món hải sản được ưa thích nhất.", "Fresh lobster grilled with creamy cheese, one of the most loved seafood dishes."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("850000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "seafood", "lobster"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("SET_MENU")
                .name(of("Set menu", "Set menu"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set menu CoCo Grill", "CoCo Grill set menu"))
                        .description(of("Set menu Á - Âu kết hợp món nướng, hải sản và tráng miệng, phù hợp nhóm và dịp đặc biệt.", "Asian - European set menu combining grill, seafood and dessert, ideal for groups and special occasions."))
                        .priceFrom(new BigDecimal("600000"))
                        .priceTo(new BigDecimal("800000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set-menu", "combo"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng trên đường Nam Kỳ Khởi Nghĩa", "Car parking near the restaurant on Nam Ky Khoi Nghia street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ trông xe, miễn phí cho khách.", "Valet support available, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "OUTDOOR_TABLE",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "EVENT_DECOR",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa", "About CoCo Grill Saigon - Nam Ky Khoi Nghia")),
            paragraph(ContentSection.OVERVIEW,
                of("CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa là nhà hàng nướng phong cách Á - Âu tọa lạc ngay trung tâm Quận 3, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi tiếng với các món bò Mỹ, steak, hải sản nướng cao cấp cùng không gian sang trọng, lãng mạn, " +
                "là điểm đến lý tưởng cho những bữa tiệc tinh tế và đáng nhớ.",
                "CoCo Grill Saigon - Nam Ky Khoi Nghia is an Asian - European grill restaurant located right in the heart of District 3, Ho Chi Minh City. " +
                "It is renowned for premium US beef, steaks and grilled seafood, paired with an elegant, romantic space, " +
                "an ideal destination for refined and memorable dining occasions.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế hiện đại và sang trọng, bố trí ấm cúng với sức chứa lên đến khoảng 180 khách. " +
                "CoCo Grill có thêm khu vực ngoài trời thoáng đãng và các phòng riêng kín đáo, phù hợp cho cả bữa ăn thân mật lẫn tiệc nhóm đông.",
                "The restaurant is designed in a modern, luxurious style and arranged cozily, with a capacity of around 180 guests. " +
                "CoCo Grill also offers an airy outdoor area and private rooms, suitable for both intimate meals and large group parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn CoCo Grill xoay quanh các món nướng cao cấp: bò Mỹ nướng tảng, sườn bò BBQ, steak thăn nội, " +
                "cùng hải sản tươi như tôm hùm nướng phô mai, bạch tuộc nướng sa tế. Bên cạnh đó là các món Á - Âu tinh tế như salad cá hồi, foie gras áp chảo.",
                "CoCo Grill's menu centers on premium grilled dishes: grilled US beef blocks, BBQ beef ribs, tenderloin steak, " +
                "along with fresh seafood such as cheese-grilled lobster and satay grilled octopus. It also features refined Asian - European dishes like salmon salad and pan-seared foie gras.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không gian lãng mạn và phục vụ chu đáo, CoCo Grill là lựa chọn hoàn hảo cho buổi hẹn hò, kỷ niệm, cầu hôn, " +
                "tiếp khách quan trọng, cũng như các bữa tiệc gia đình, sinh nhật hay liên hoan công ty.",
                "With its romantic space and attentive service, CoCo Grill is a perfect choice for dates, anniversaries, proposals, " +
                "important business meals, as well as family parties, birthdays and company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng, máy chiếu, " +
                "hỗ trợ trang trí và MC cho sự kiện. Có chỗ đỗ ô tô và xe máy miễn phí, chấp nhận thẻ và xuất hóa đơn VAT.",
                "The restaurant is equipped with air conditioning, wifi, private rooms, an outdoor area, sound & light systems, a projector, " +
                "plus decoration and MC support for events. Free car and motorbike parking is available, cards are accepted and VAT invoices are issued.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 143 Nam Kỳ Khởi Nghĩa, P. 6, Q. 3, ngay trung tâm thành phố, gần nhiều tuyến đường lớn, " +
                "thuận tiện di chuyển từ Quận 1 và các quận lân cận.",
                "The restaurant is located at 143 Nam Ky Khoi Nghia, Ward 6, District 3, right in the city center, near many major roads, " +
                "convenient to reach from District 1 and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm buổi tối. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and evening peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6932883, 10.78068})
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
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
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
            .promotionMaxDiscountPercent(10)
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
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp cặp đôi, hẹn hò.", "Suitable for couples and dates."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("300000"))
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
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng sang trọng, phù hợp tiệc nhóm đông/liên hoan công ty.", "Luxurious private room, suitable for large groups and company parties."))
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
            .addressLine(of("143 Nam Kỳ Khởi Nghĩa, P. 6, Q. 3, TP. Hồ Chí Minh", "143 Nam Ky Khoi Nghia, Ward 6, District 3, Ho Chi Minh City"))
            .location(new double[]{106.6932883, 10.78068})
            .name(of("CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa", "CoCo Grill Saigon - Nam Ky Khoi Nghia"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng Á - Âu cao cấp với bò Mỹ, steak và hải sản tại Q.3, TP. Hồ Chí Minh.", "Premium Asian - European grill with US beef, steak and seafood in District 3, Ho Chi Minh City."))
            .description(of("CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa là nhà hàng nướng phong cách Á - Âu tại 143 Nam Kỳ Khởi Nghĩa, Q. 3, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi bật với bò Mỹ nướng tảng, steak, hải sản nướng cao cấp trong không gian sang trọng, lãng mạn, " +
                "phù hợp cho hẹn hò, kỷ niệm, tiếp khách cũng như tiệc gia đình và công ty.",
                "CoCo Grill Saigon - Nam Ky Khoi Nghia is an Asian - European grill restaurant at 143 Nam Ky Khoi Nghia, District 3, Ho Chi Minh City. " +
                "It stands out with grilled US beef blocks, steaks and premium grilled seafood in an elegant, romantic space, " +
                "suitable for dates, anniversaries, business meals as well as family and company parties."))
            .phone("0805330563")
            .email("reservation@cocogrill.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-coco-grill-saigon-nam-ky-khoi-nghia")
            .facebookPage("https://facebook.com/cocogrillsaigon")
            .bookingHotline("0805330563")
            .minPricePerPerson(new BigDecimal("600000"))
            .maxPricePerPerson(new BigDecimal("800000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.LUXURY)
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
