// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedLauGaOtHiem109HoangDieu2.java
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
public class SeedLauGaOtHiem109HoangDieu2 {

    @Bean
    CommandLineRunner seedLauGaOtHiem109HoangDieu2Runner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauGaOtHiem109HoangDieu2] start");

            RestaurantDoc r = restaurantLauGaOtHiem109HoangDieu2();
            repo.findBySlugAndActiveTrue(r.getSlug())
                .ifPresent(existing -> r.setId(existing.getId()));
            repo.save(r);
            log.info(">>> upsert: {}", r.getSlug());

            log.info(">>> [SeedLauGaOtHiem109HoangDieu2] done");
        };
    }

    // =====================================================================
    //        LẨU GÀ ỚT HIỂM 109 - HOÀNG DIỆU 2 (THỦ ĐỨC, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantLauGaOtHiem109HoangDieu2() {
        String slug = "lau-ga-ot-hiem-109-hoang-dieu-2";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-1-normal-2263273057830.webp",
                of("Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2", "Lau Ga Ot Hiem 109 - Hoang Dieu 2"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-2-normal-2263273357831.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-3-normal-2263273457832.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-4-normal-2263273657833.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-5-normal-2263273757834.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-6-normal-2263273957835.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-7-normal-2263274057836.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-9-normal-2263274357837.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-ga-ot-hiem-10-normal-2263274457838.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-dieu-1-normal-2263275157839.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-dieu-2-normal-2263275257840.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-dieu-3-normal-2263275457841.webp",
                of("", ""), false, 11)
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
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("CHICKEN_HOTPOT")
                .name(of("Lẩu gà ớt hiểm", "Chili chicken hotpot"))
                .region("ASIA")
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
            ambience("VIBRANT",         of("Sôi động", "Vibrant")),
            ambience("RUSTIC",          of("Mộc mạc", "Rustic")),
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
            signatureDishL(of("Lẩu gà ớt hiểm 109 đặc biệt", "109 signature chili chicken hotpot")),
            signatureDishL(of("Gà ta thả vườn", "Free-range chicken")),
            signatureDishL(of("Lẩu gà lá é", "Chicken hotpot with basil")),
            signatureDishL(of("Gà ướp sả ớt nướng", "Grilled lemongrass chili chicken")),
            signatureDishL(of("Nấm rừng nhúng lẩu", "Wild mushrooms for hotpot")),
            signatureDishL(of("Rau nhúng lẩu tươi", "Fresh hotpot vegetables")),
            signatureDishL(of("Mì trứng nhúng lẩu", "Egg noodles for hotpot")),
            signatureDishL(of("Cháo gà ớt hiểm", "Chili chicken porridge")),
            signatureDishL(of("Gỏi gà bắp cải", "Chicken & cabbage salad")),
            signatureDishL(of("Nước chấm ớt hiểm đặc trưng", "Signature chili dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/laugaothiem/lau-ga-ot-hiem-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/laugaothiem/lau-ga-ot-hiem-2.jpg",
                of("", ""), false, 1)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu gà", "Chicken hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu gà ớt hiểm (nồi vừa)", "Chili chicken hotpot (medium pot)"))
                        .description(of("Nồi lẩu gà ta thả vườn nấu ớt hiểm cay nồng, kèm rau nhúng và mì.", "Free-range chicken hotpot cooked with spicy bird's eye chili, served with vegetables and noodles."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("259000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu gà lá é (nồi lớn)", "Chicken hotpot with basil (large pot)"))
                        .description(of("Lẩu gà lá é đậm vị, phù hợp nhóm 4-6 khách, ăn kèm rau và bún tươi.", "Rich basil chicken hotpot for groups of 4-6, served with vegetables and fresh noodles."))
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
            .hasCarParking(true)
            .carParkingLocation(of("Chỗ đỗ ô tô trên đường Hoàng Diệu 2", "Car parking along Hoang Dieu 2 street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hướng dẫn đỗ xe, miễn phí cho khách.", "Staff assist with parking, free of charge for guests."))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2", "About Lau Ga Ot Hiem 109 - Hoang Dieu 2")),
            paragraph(ContentSection.OVERVIEW,
                of("Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 là một chi nhánh trong chuỗi nhà hàng lẩu gà nổi tiếng, tọa lạc ngay tại " +
                "190 Hoàng Diệu 2, P. Linh Chiểu, TP. Thủ Đức, TP. Hồ Chí Minh. Nhà hàng chuyên phục vụ món lẩu gà ớt hiểm trứ danh " +
                "với gà ta thả vườn tươi ngon, nước lẩu cay nồng đặc trưng làm nên thương hiệu.",
                "Lau Ga Ot Hiem 109 - Hoang Dieu 2 is a branch of the famous chicken hotpot chain, located right at " +
                "190 Hoang Dieu 2, Linh Chieu Ward, Thu Duc City, Ho Chi Minh City. The restaurant specializes in its signature " +
                "chili chicken hotpot with fresh free-range chicken and the distinctive spicy broth that built its name.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Đây là chi nhánh có sức chứa lớn nhất trong hệ thống, với không gian rộng rãi lên đến khoảng 200 khách, " +
                "bố trí cả khu vực trong nhà và bàn ngoài trời thoáng đãng. Nhà hàng có thêm phòng riêng phục vụ các nhóm đông, " +
                "tiệc gia đình hay liên hoan công ty.",
                "This is the largest-capacity branch in the chain, with a spacious setting for around 200 guests, " +
                "featuring both indoor seating and airy outdoor tables. The restaurant also offers private rooms for large groups, " +
                "family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Linh hồn của nhà hàng là nồi lẩu gà ớt hiểm cay nồng nấu từ gà ta thả vườn, ăn kèm nấm rừng, rau tươi và mì trứng. " +
                "Bên cạnh đó còn có lẩu gà lá é, gà ướp sả ớt nướng, gỏi gà bắp cải và cháo gà ớt hiểm để thực khách đổi vị.",
                "The soul of the restaurant is its spicy chili chicken hotpot made from free-range chicken, served with wild mushrooms, " +
                "fresh vegetables and egg noodles. There is also basil chicken hotpot, grilled lemongrass chili chicken, chicken cabbage salad " +
                "and chili chicken porridge for variety.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, sôi động và phục vụ chu đáo, nhà hàng phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật ở khu vực Thủ Đức.",
                "With a cozy, vibrant atmosphere and attentive service, the restaurant is ideal for family meals, friends gatherings, " +
                "office parties, birthdays or casual business meals in the Thu Duc area.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bàn ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, outdoor tables, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm trên đường Hoàng Diệu 2, P. Linh Chiểu, TP. Thủ Đức, gần nhiều trường đại học, cao đẳng lớn của khu vực, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant sits on Hoang Dieu 2 street, Linh Chieu Ward, Thu Duc City, near many major universities and colleges of the area, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.7677871, 10.8547809})
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
                .feeAmount(new BigDecimal("250000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 250.000đ/chai.", "Spirits corkage fee: 250,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("120000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 120.000đ/chai.", "Wine corkage fee: 120,000 VND/bottle."))
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
                .totalTables(20)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8")
                .name(of("Bàn 8 ngoài trời", "Outdoor table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn ngoài trời thoáng mát, phù hợp nhóm đông.", "Airy outdoor table, suitable for larger groups."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(20)
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
            .districtName(of("TP. Thủ Đức", "Thu Duc City"))
            .wardName(of("P. Linh Chiểu", "Linh Chieu Ward"))
            .addressLine(of("190 Hoàng Diệu 2, P. Linh Chiểu, TP. Thủ Đức, TP. Hồ Chí Minh", "190 Hoang Dieu 2, Linh Chieu Ward, Thu Duc City, Ho Chi Minh City"))
            .location(new double[]{106.7677871, 10.8547809})
            .name(of("Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2", "Lau Ga Ot Hiem 109 - Hoang Dieu 2"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu gà ớt hiểm trứ danh, gà ta thả vườn tươi ngon tại Hoàng Diệu 2, TP. Thủ Đức.", "Famous chili chicken hotpot with fresh free-range chicken on Hoang Dieu 2, Thu Duc City."))
            .description(of("Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 là chi nhánh có sức chứa lớn nhất của chuỗi lẩu gà ớt hiểm, tại 190 Hoàng Diệu 2, " +
                "P. Linh Chiểu, TP. Thủ Đức, TP. Hồ Chí Minh. Nhà hàng chuyên món lẩu gà ớt hiểm cay nồng từ gà ta thả vườn, " +
                "không gian rộng rãi cả trong nhà lẫn ngoài trời, phù hợp nhiều dịp từ ăn gia đình đến liên hoan công ty.",
                "Lau Ga Ot Hiem 109 - Hoang Dieu 2 is the largest-capacity branch of the chili chicken hotpot chain, at 190 Hoang Dieu 2, " +
                "Linh Chieu Ward, Thu Duc City, Ho Chi Minh City. It specializes in spicy chili chicken hotpot made from free-range chicken, " +
                "with a spacious indoor and outdoor setting, suitable for occasions from family meals to company parties."))
            .phone("0931006005")
            .email("reservation@laugaothiem109.vn")
            .website("https://pasgo.vn/nha-hang/lau-ga-ot-hiem-109-hoang-dieu-2")
            .facebookPage("https://facebook.com/laugaothiem109")
            .bookingHotline("0931006005")
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
