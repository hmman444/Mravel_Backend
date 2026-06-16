// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedLauGaOtHiem109NguyenThienThuat.java
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
public class SeedLauGaOtHiem109NguyenThienThuat {

    @Bean
    CommandLineRunner seedLauGaOtHiem109NguyenThienThuatRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauGaOtHiem109NguyenThienThuat] start");

            RestaurantDoc doc = restaurantLauGaOtHiem109NguyenThienThuat();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauGaOtHiem109NguyenThienThuat] done");
        };
    }

    // =====================================================================
    //          LẨU GÀ ỚT HIỂM 109 - NGUYỄN THIỆN THUẬT (Q.3, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantLauGaOtHiem109NguyenThienThuat() {
        String slug = "lau-ga-ot-hiem-109-nguyen-thien-thuat";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-1-normal-2263286557878.webp",
                of("Không gian Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật", "Lau Ga Ot Hiem 109 - Nguyen Thien Thuat space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-2-normal-2263286757879.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-3-normal-2263286957880.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-4-normal-2263287157881.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-5-normal-2263287357882.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-6-normal-2263287557883.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-7-normal-2263287757884.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-9-normal-2263287957885.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-10-normal-2263288157886.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-nguyen-thien-thuat-1-normal-2263288557888.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-nguyen-thien-thuat-2-normal-2263288657889.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-nguyen-thien-thuat-3-normal-2263288357887.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/109-6-nguyen-thien-thuat--p--2--q--3-1076738291066777229.webp",
                of("", ""), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTHEAST_ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("SOUTHEAST_ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
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
            ambience("COZY",            of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",          of("Mộc mạc", "Rustic")),
            ambience("VIBRANT",         of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(25)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu gà ớt hiểm", "Bird's eye chili chicken hotpot")),
            signatureDishL(of("Lẩu gà lá é", "Chicken hotpot with basil leaves")),
            signatureDishL(of("Gà ta thả vườn nhúng lẩu", "Free-range chicken for hotpot")),
            signatureDishL(of("Ớt hiểm xanh đặc trưng", "Signature green bird's eye chili")),
            signatureDishL(of("Nấm tươi nhúng lẩu", "Fresh mushrooms for hotpot")),
            signatureDishL(of("Rau rừng nhúng lẩu", "Wild greens for hotpot")),
            signatureDishL(of("Mì trứng nhúng lẩu", "Egg noodles for hotpot")),
            signatureDishL(of("Bún tươi nhúng lẩu", "Fresh rice vermicelli for hotpot")),
            signatureDishL(of("Gà nướng muối ớt", "Grilled chicken with salt & chili")),
            signatureDishL(of("Cháo gà ớt hiểm", "Chicken chili congee"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Lau-Ga-Ot-Hiem-10/11/2023/menu-lẩu-gà-ớt-hiểm-5.jpg",
                of("", ""), false, 4)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu gà", "Chicken hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu gà ớt hiểm (nồi nhỏ)", "Bird's eye chili chicken hotpot (small)"))
                        .description(of("Lẩu gà ta thả vườn nấu cùng ớt hiểm xanh đặc trưng, ăn kèm nấm, rau và bún/mì.", "Free-range chicken hotpot with signature green bird's eye chili, served with mushrooms, vegetables and noodles."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("239000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu gà ớt hiểm (nồi lớn)", "Bird's eye chili chicken hotpot (large)"))
                        .description(of("Nồi lẩu gà lớn cho nhóm 4-6 khách, đậm vị cay nồng đặc trưng.", "Large chicken hotpot for 4-6 guests, rich in signature spicy flavor."))
                        .priceFrom(new BigDecimal("359000"))
                        .priceTo(new BigDecimal("359000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Đỗ xe ô tô dọc tuyến đường lân cận", "Car parking along nearby streets"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy gần nhà hàng", "Motorbike parking near the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng nằm trong hẻm, có chỗ giữ xe máy thuận tiện.", "The restaurant is in an alley with convenient motorbike parking."))
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
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BIRTHDAY_SERVICE",
            "GROUP_BOOKING"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật", "About Lau Ga Ot Hiem 109 - Nguyen Thien Thuat")),
            paragraph(ContentSection.OVERVIEW,
                of("Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật là quán lẩu gà ngon nổi tiếng tại Quận 3, TP. Hồ Chí Minh. " +
                "Thương hiệu được nhiều thực khách Sài Gòn yêu thích nhờ nồi lẩu gà ta thả vườn nấu cùng ớt hiểm xanh đặc trưng, " +
                "vị cay nồng ấm bụng, nước dùng ngọt thanh và nguyên liệu tươi mới mỗi ngày.",
                "Lau Ga Ot Hiem 109 - Nguyen Thien Thuat is a famous chicken hotpot eatery in District 3, Ho Chi Minh City. " +
                "The brand is loved by many Saigon diners for its free-range chicken hotpot cooked with signature green bird's eye chili, " +
                "a warming spicy flavor, a clear sweet broth and fresh ingredients prepared every day.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian quán giản dị, mộc mạc và bình dân, mỗi lầu có ô cửa lớn đón ánh sáng tự nhiên và lưu thông không khí thoáng đãng. " +
                "Quán có sức chứa khoảng 120 khách với cách bố trí phù hợp cho cả nhóm nhỏ lẫn đoàn khách đông, có phòng riêng cho nhóm cần không gian kín đáo.",
                "The space is simple, rustic and unpretentious; each floor has large windows that welcome natural light and keep the air airy. " +
                "The restaurant seats around 120 guests, arranged for both small parties and large groups, with private rooms for those who need a more secluded space.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Linh hồn của quán là nồi lẩu gà ớt hiểm trứ danh - gà ta dai ngọt nhúng trong nước dùng cay nồng từ ớt hiểm xanh, " +
                "ăn kèm nấm tươi, rau rừng, mì trứng và bún tươi. Ngoài ra còn có lẩu gà lá é, gà nướng muối ớt và cháo gà ớt hiểm cho thực khách lựa chọn.",
                "The heart of the eatery is its famous bird's eye chili chicken hotpot - chewy, sweet free-range chicken dipped in a spicy broth from green bird's eye chili, " +
                "served with fresh mushrooms, wild greens, egg noodles and rice vermicelli. There are also basil-leaf chicken hotpot, grilled chicken with salt & chili, and chicken chili congee.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng cùng món lẩu cay nóng hợp khẩu vị, quán phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật, đặc biệt là vào những ngày Sài Gòn trở gió.",
                "With a cozy atmosphere and an appetizing hot, spicy hotpot, the eatery is ideal for family meals, friends gatherings, " +
                "office parties, birthdays or casual business meals, especially on Saigon's breezy days.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán trang bị điều hòa, wifi, ghế trẻ em, phòng riêng cùng hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho các buổi tiệc nhỏ. " +
                "Có chỗ giữ xe máy thuận tiện và xuất hóa đơn VAT theo yêu cầu của khách.",
                "The eatery is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and decoration / MC support for small parties. " +
                "Convenient motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán tọa lạc tại 109/6 Nguyễn Thiện Thuật, Phường 2, Quận 3, gần Ngã 6 Cộng Hòa. " +
                "Quán nằm trong hẻm nhưng không khó tìm, thuận tiện di chuyển từ trung tâm Quận 1, Quận 3 và các quận lân cận.",
                "The eatery is located at 109/6 Nguyen Thien Thuat, Ward 2, District 3, near Cong Hoa Roundabout. " +
                "It sits in an alley but is easy to find, convenient to reach from central District 1, District 3 and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm buổi tối. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của quán.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak evening hours. " +
                "For large groups or private room bookings, a deposit may be required according to the eatery's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6777229, 10.7673829})
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
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ quán.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Quán có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
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
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(15)
                .depositPrice(new BigDecimal("150000"))
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
                .minPeople(12)
                .maxPeople(25)
                .totalTables(2)
                .depositPrice(new BigDecimal("800000"))
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
            .districtName(of("Q. 3", "District 3"))
            .wardName(of("P. 2", "Ward 2"))
            .addressLine(of("109/6 Nguyễn Thiện Thuật, Phường 2, Quận 3, TP. Hồ Chí Minh", "109/6 Nguyen Thien Thuat, Ward 2, District 3, Ho Chi Minh City"))
            .location(new double[]{106.6777229, 10.7673829})
            .name(of("Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật", "Lau Ga Ot Hiem 109 - Nguyen Thien Thuat"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu gà ớt hiểm ta thả vườn cay nồng, mộc mạc bình dân tại Quận 3, TP. Hồ Chí Minh.", "Spicy free-range bird's eye chili chicken hotpot, rustic and affordable in District 3, Ho Chi Minh City."))
            .description(of("Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật là quán lẩu gà ngon nổi tiếng tại 109/6 Nguyễn Thiện Thuật, Quận 3, TP. Hồ Chí Minh. " +
                "Quán hấp dẫn thực khách với nồi lẩu gà ta thả vườn nấu cùng ớt hiểm xanh đặc trưng, nước dùng ngọt thanh, cay nồng ấm bụng, " +
                "không gian giản dị mộc mạc, phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Lau Ga Ot Hiem 109 - Nguyen Thien Thuat is a famous chicken hotpot eatery at 109/6 Nguyen Thien Thuat, District 3, Ho Chi Minh City. " +
                "It draws diners with free-range chicken hotpot cooked with signature green bird's eye chili, a clear sweet yet spicy warming broth, " +
                "in a simple rustic space, suitable for family meals, friends gatherings and company parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@laugaothiem109.vn")
            .website("https://pasgo.vn/nha-hang/lau-ga-ot-hiem-109-nguyen-thien-thuat")
            .facebookPage("https://facebook.com/laugaothiem109")
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
