// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedBoUPlusLamVanBen.java
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
public class SeedBoUPlusLamVanBen {

    @Bean
    CommandLineRunner seedBoUPlusLamVanBenRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBoUPlusLamVanBen] start");

            RestaurantDoc bouu = restaurantBoUPlusLamVanBen();
            repo.findBySlugAndActiveTrue(bouu.getSlug())
                .ifPresent(existing -> bouu.setId(existing.getId()));
            repo.save(bouu);
            log.info(">>> upsert: {}", bouu.getSlug());

            log.info(">>> [SeedBoUPlusLamVanBen] done");
        };
    }

    // =====================================================================
    //                 BÒ Ú PLUS - LÂM VĂN BỀN (QUẬN 7, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantBoUPlusLamVanBen() {
        String slug = "bo-u-plus-lam-van-ben";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/so-186---200-lam-van-ben--p--tan-quy--q--7-107410512502106716192956985.webp",
                of("Không gian Bò Ú Plus - Lâm Văn Bền", "Bo U Plus - Lam Van Ben space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-1-normal-2268951359466.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-2-normal-2268951459467.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-3-normal-2268951559468.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-4-normal-2268951759469.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-5-normal-2268951859470.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-6-normal-2268951959471.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-7-normal-2268952059472.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-8-normal-2268952159473.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-9-normal-2268952259474.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-10-normal-2268952359475.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-bo-u-plus-lam-van-ben-11-normal-2268952459476.webp",
                of("", ""), false, 11)
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
                .name(of("Nướng", "BBQ / Grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet & Gọi món Nướng Bò", "Beef BBQ Buffet & A La Carte"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (14:00 - 24:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    14, 0, 24, 0),
            openingHour(DayOfWeek.TUESDAY,   14, 0, 24, 0),
            openingHour(DayOfWeek.WEDNESDAY, 14, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  14, 0, 24, 0),
            openingHour(DayOfWeek.FRIDAY,    14, 0, 24, 0),
            openingHour(DayOfWeek.SATURDAY,  14, 0, 24, 0),
            openingHour(DayOfWeek.SUNDAY,    14, 0, 24, 0)
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
            suitable("GATHERING",     of("Họp mặt cuối tuần", "Weekend get-together"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",            of("Ấm cúng", "Cozy")),
            ambience("MODERN",          of("Hiện đại", "Modern")),
            ambience("VIBRANT",         of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
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
            signatureDishL(of("Buffet nướng bò Bò Ú Plus", "Bo U Plus beef BBQ buffet")),
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly")),
            signatureDishL(of("Bò Wagyu thượng hạng", "Premium Wagyu beef")),
            signatureDishL(of("Lưỡi bò nướng", "Grilled beef tongue")),
            signatureDishL(of("Dẻ sườn bò nướng", "Grilled beef short rib")),
            signatureDishL(of("Lẩu kim chi Hàn Quốc", "Korean kimchi hotpot")),
            signatureDishL(of("Sườn heo nướng mật ong", "Honey grilled pork rib")),
            signatureDishL(of("Hải sản nướng tổng hợp", "Assorted grilled seafood")),
            signatureDishL(of("Cơm trộn kiểu Nhật", "Japanese-style mixed rice bowl")),
            signatureDishL(of("Nước chấm pha chế đặc trưng", "Signature dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-19.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/B%C3%B2%20%C3%9A%20Plus/Menu-Bo-U-Plus-20.jpg",
                of("", ""), false, 19)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet nướng bò", "Beef BBQ buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Tiêu Chuẩn", "Standard Buffet"))
                        .description(of("Buffet nướng bò tiêu chuẩn: ba chỉ bò, dẻ sườn, lưỡi bò, hải sản và rau nướng kèm nước chấm đặc trưng.", "Standard beef BBQ buffet: beef belly, short rib, tongue, seafood and grilled vegetables with signature dipping sauce."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Cao Cấp", "Premium Buffet"))
                        .description(of("Buffet nướng bò cao cấp: thêm bò Wagyu, sườn non Mỹ, hải sản tươi và lẩu kim chi Hàn Quốc.", "Premium beef BBQ buffet: adds Wagyu beef, US short rib, fresh seafood and Korean kimchi hotpot."))
                        .priceFrom(new BigDecimal("249000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "wagyu"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng trên đường Lâm Văn Bền", "Car parking near the restaurant on Lam Van Ben street"))
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
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "BUFFET_SERVICE",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BIRTHDAY_DECOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Bò Ú Plus - Lâm Văn Bền", "About Bo U Plus - Lam Van Ben")),
            paragraph(ContentSection.OVERVIEW,
                of("Bò Ú Plus - Lâm Văn Bền là nhà hàng buffet và gọi món nướng bò phong cách Nhật Bản nằm tại khu vực Quận 7, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi tiếng với thực đơn thịt bò đa dạng, từ ba chỉ, dẻ sườn đến bò Wagyu thượng hạng, mang đến trải nghiệm nướng tươi ngon ngay tại bàn.",
                "Bo U Plus - Lam Van Ben is a Japanese-style beef BBQ buffet and a la carte restaurant located in District 7, Ho Chi Minh City. " +
                "It is well known for its diverse beef selection, from belly and short rib to premium Wagyu, offering a fresh grill-at-your-table experience.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, hiện đại và sôi động, được bố trí khoa học với sức chứa lên đến khoảng 180 khách. " +
                "Bò Ú Plus có thêm phòng riêng phù hợp cho các nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern and vibrant space, well arranged with a capacity of around 180 guests. " +
                "Bo U Plus also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Bò Ú Plus là buffet nướng bò với nhiều phần thịt tươi chọn lọc, kết hợp hải sản, lẩu kim chi Hàn Quốc và các món ăn kèm kiểu Nhật. " +
                "Thực khách có thể chọn suất buffet tiêu chuẩn hoặc cao cấp với bò Wagyu, mức giá khoảng 150.000 - 250.000đ/người, vừa túi tiền mà vẫn no nê.",
                "Bo U Plus's highlight is its beef BBQ buffet with many selected fresh cuts, paired with seafood, Korean kimchi hotpot and Japanese-style side dishes. " +
                "Diners can choose the standard or premium buffet with Wagyu, priced around 150,000 - 250,000 VND/person, affordable yet satisfying.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phục vụ nhanh nhẹn, Bò Ú Plus - Lâm Văn Bền phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy atmosphere and attentive service, Bo U Plus - Lam Van Ben is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Số 186 - 200 Lâm Văn Bền, P. Tân Quy, Q. 7, gần khu Crescent Mall và đại lộ Nguyễn Văn Linh, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và khu Phú Mỹ Hưng.",
                "The restaurant is located at No. 186 - 200 Lam Van Ben, Tan Quy Ward, District 7, near Crescent Mall and Nguyen Van Linh Avenue, " +
                "convenient to reach from central Ho Chi Minh City and the Phu My Hung area.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm buổi tối. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak evening hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.721200, 10.748800})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho một số khung giờ cao điểm.", "A 5% service charge applies during certain peak hours."))
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
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
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
            .districtName(of("Q. 7", "District 7"))
            .wardName(of("P. Tân Quy", "Tan Quy Ward"))
            .addressLine(of("Số 186 - 200 Lâm Văn Bền, P. Tân Quy, Q. 7, TP. Hồ Chí Minh", "No. 186 - 200 Lam Van Ben, Tan Quy Ward, District 7, Ho Chi Minh City"))
            .location(new double[]{106.721200, 10.748800})
            .name(of("Bò Ú Plus - Lâm Văn Bền", "Bo U Plus - Lam Van Ben"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet & gọi món nướng bò phong cách Nhật Bản, giá hợp lý tại Q. 7, TP. Hồ Chí Minh.", "Japanese-style beef BBQ buffet & a la carte, value-for-money in District 7, Ho Chi Minh City."))
            .description(of("Bò Ú Plus - Lâm Văn Bền là nhà hàng nướng bò phục vụ cả buffet và gọi món tại Số 186 - 200 Lâm Văn Bền, Q. 7, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến thực đơn thịt bò đa dạng từ ba chỉ, dẻ sườn đến bò Wagyu thượng hạng, không gian rộng rãi hiện đại, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Bo U Plus - Lam Van Ben is a beef BBQ restaurant serving both buffet and a la carte at No. 186 - 200 Lam Van Ben, District 7, Ho Chi Minh City. " +
                "It offers a diverse beef selection from belly and short rib to premium Wagyu in a spacious, modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@bouuplus.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-bo-u-plus-lam-van-ben")
            .facebookPage("https://facebook.com/bouuplus")
            .bookingHotline("(+84) 0931 006 005")
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
