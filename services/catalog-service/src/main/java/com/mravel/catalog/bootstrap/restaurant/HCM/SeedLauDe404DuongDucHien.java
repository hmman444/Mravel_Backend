// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedLauDe404DuongDucHien.java
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
public class SeedLauDe404DuongDucHien {

    @Bean
    CommandLineRunner seedLauDe404DuongDucHienRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauDe404DuongDucHien] start");

            RestaurantDoc doc = restaurantLauDe404DuongDucHien();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauDe404DuongDucHien] done");
        };
    }

    // =====================================================================
    //          LẨU DÊ 404 - DƯƠNG ĐỨC HIỀN (TÂN PHÚ, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantLauDe404DuongDucHien() {
        String slug = "lau-de-404-duong-duc-hien";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-1-normal-2203910526202.webp",
                of("Không gian Lẩu Dê 404 - Dương Đức Hiền", "Lau De 404 - Duong Duc Hien space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-2-normal-2203910626203.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-3-normal-2203910426204.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-4-normal-2203910326205.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-5-normal-2203910226206.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-6-normal-2203910126207.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-7-normal-2203909926208.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-de-404-go-dau-slide-8-normal-2203909826209.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-slide-1-normal-2203909726210.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-slide-2-normal-2203909026211.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-slide-3-normal-2203909126212.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-slide-4-normal-2203909526213.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/so-37-duong-duc-hien--p--tay-thanh--q-tan-phu-108093646146689106626910960779.webp",
                of("", ""), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("GOAT")
                .name(of("Món dê", "Goat specialties"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 22:00, cả tuần) ----------
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
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",  of("Mộc mạc", "Rustic")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(160)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu dê tiềm thuốc bắc", "Goat hotpot with herbal broth")),
            signatureDishL(of("Dê nướng ngũ vị", "Five-spice grilled goat")),
            signatureDishL(of("Dê hấp tía tô", "Steamed goat with perilla")),
            signatureDishL(of("Dê xào lăn", "Stir-fried goat with lemongrass")),
            signatureDishL(of("Ngọc dương hầm thuốc bắc", "Goat tonic stew")),
            signatureDishL(of("Tiết canh dê", "Goat blood pudding")),
            signatureDishL(of("Dê tái chanh", "Rare goat with lime")),
            signatureDishL(of("Cháo dê", "Goat congee")),
            signatureDishL(of("Lẩu hải sản", "Seafood hotpot")),
            signatureDishL(of("Rau nhúng lẩu tươi", "Fresh hotpot vegetables"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-2.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-4.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-6.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-8.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-9.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-10.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-11.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-13.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/laudegodau/lau-de-404-go-dau-14.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/thanhmai/Lau-de-404-duong-duc-hien/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-1.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/thanhmai/Lau-de-404-duong-duc-hien/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-2.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/thanhmai/Lau-de-404-duong-duc-hien/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-3.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/thanhmai/Lau-de-404-duong-duc-hien/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-4.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/thanhmai/Lau-de-404-duong-duc-hien/nha-hang-lau-de-duong-duc-hien-phuong-tan-quy-tan-phu-5.jpg",
                of("", ""), false, 13)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GOAT")
                .name(of("Món dê", "Goat dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu dê tiềm thuốc bắc", "Goat hotpot with herbal broth"))
                        .description(of("Lẩu dê hầm thuốc bắc, ăn kèm rau tươi, đậu hũ và mì.", "Goat hotpot stewed with herbs, served with fresh vegetables, tofu and noodles."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "goat"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Dê nướng ngũ vị", "Five-spice grilled goat"))
                        .description(of("Thịt dê ướp ngũ vị, nướng trên than hồng thơm lừng.", "Goat marinated in five-spice, grilled over charcoal."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grilled", "goat"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng", "Car parking near the restaurant"))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Lẩu Dê 404 - Dương Đức Hiền", "About Lau De 404 - Duong Duc Hien")),
            paragraph(ContentSection.OVERVIEW,
                of("Lẩu Dê 404 - Dương Đức Hiền (trước đây là Gò Dầu) là địa chỉ quen thuộc của tín đồ mê món dê tại Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Với hơn một thập kỷ gắn bó cùng ẩm thực dê, nhà hàng phục vụ trọn vẹn một bữa tiệc thịt dê đậm đà, từ lẩu, nướng đến hấp, xào, " +
                "chế biến theo công thức gia truyền nhiều đời.",
                "Lau De 404 - Duong Duc Hien (formerly Go Dau) is a familiar address for goat-meat lovers in Tan Phu District, Ho Chi Minh City. " +
                "With more than a decade devoted to goat cuisine, the restaurant serves a complete goat feast - from hotpot and grills to steamed and stir-fried dishes, " +
                "prepared from a recipe passed down through generations.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng có không gian rộng rãi, mộc mạc và ấm cúng, sức chứa khoảng 160 khách với cả khu vực trong nhà lẫn sân vườn ngoài trời. " +
                "Lẩu Dê 404 bố trí 3 phòng riêng cho các nhóm đông, tiệc gia đình hoặc liên hoan công ty, đáp ứng đoàn lên tới 40 khách.",
                "The restaurant offers a spacious, rustic and cozy space with a capacity of around 160 guests, including both indoor and outdoor garden areas. " +
                "Lau De 404 has 3 private rooms for large groups, family parties or company gatherings, accommodating parties of up to 40 guests.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Ngôi sao của thực đơn là lẩu dê tiềm thuốc bắc nóng hổi, dê nướng ngũ vị thơm lừng và dê hấp tía tô thanh ngọt. " +
                "Bên cạnh đó là loạt món dê quen thuộc như dê xào lăn, ngọc dương hầm thuốc bắc, dê tái chanh, cháo dê cùng các món lẩu hải sản và rau nhúng tươi mới.",
                "The menu stars a piping-hot goat hotpot with herbal broth, fragrant five-spice grilled goat and sweet steamed goat with perilla. " +
                "Alongside are classic goat dishes such as stir-fried goat with lemongrass, goat tonic stew, rare goat with lime and goat congee, plus seafood hotpot and fresh dipping vegetables.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phục vụ chu đáo, Lẩu Dê 404 - Dương Đức Hiền phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật.",
                "With a cozy atmosphere and attentive service, Lau De 404 - Duong Duc Hien is ideal for family meals, friends gatherings, " +
                "office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, an outdoor area, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Số 37 Dương Đức Hiền, P. Tây Thạnh, Q. Tân Phú, gần khu công nghiệp Tân Bình và sân bay Tân Sơn Nhất, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at No. 37 Duong Duc Hien, Tay Thanh Ward, Tan Phu District, near Tan Binh Industrial Park and Tan Son Nhat Airport, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.626910960779, 10.8093646146689})
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
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8")
                .name(of("Bàn 8", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp nhóm đông.", "Suitable for larger groups."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(15)
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
            .districtName(of("Q. Tân Phú", "Tan Phu District"))
            .wardName(of("P. Tây Thạnh", "Tay Thanh Ward"))
            .addressLine(of("Số 37 Dương Đức Hiền, P. Tây Thạnh, Q. Tân Phú, TP. Hồ Chí Minh", "No. 37 Duong Duc Hien, Tay Thanh Ward, Tan Phu District, Ho Chi Minh City"))
            .location(new double[]{106.626910960779, 10.8093646146689})
            .name(of("Lẩu Dê 404 - Dương Đức Hiền", "Lau De 404 - Duong Duc Hien"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Bữa tiệc thịt dê ấn tượng - lẩu, nướng, hấp tại Q. Tân Phú, TP. Hồ Chí Minh.", "An impressive goat feast - hotpot, grills and steamed dishes in Tan Phu, Ho Chi Minh City."))
            .description(of("Lẩu Dê 404 - Dương Đức Hiền (trước đây là Gò Dầu) là nhà hàng chuyên món dê tại Số 37 Dương Đức Hiền, Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trọn vẹn một bữa tiệc thịt dê đậm đà từ lẩu dê tiềm thuốc bắc, dê nướng ngũ vị đến dê hấp tía tô, " +
                "không gian rộng rãi mộc mạc, phù hợp cho ăn gia đình, tụ tập bạn bè và liên hoan công ty.",
                "Lau De 404 - Duong Duc Hien (formerly Go Dau) is a goat specialty restaurant at No. 37 Duong Duc Hien, Tan Phu District, Ho Chi Minh City. " +
                "It delivers a complete and flavorful goat feast from herbal goat hotpot and five-spice grilled goat to steamed goat with perilla, " +
                "in a spacious rustic space suitable for family meals, friends gatherings and company parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@laude404.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-lau-de-404-duong-duc-hien")
            .facebookPage("https://facebook.com/laude404")
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
