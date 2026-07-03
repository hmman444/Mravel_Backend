// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedReskiHotpotTenLua.java
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
public class SeedReskiHotpotTenLua {

    @Bean
    CommandLineRunner seedReskiHotpotTenLuaRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedReskiHotpotTenLua] start");

            RestaurantDoc reski = restaurantReskiHotpotTenLua();
            repo.findBySlugAndActiveTrue(reski.getSlug())
                .ifPresent(existing -> reski.setId(existing.getId()));
            repo.save(reski);
            log.info(">>> upsert: {}", reski.getSlug());

            log.info(">>> [SeedReskiHotpotTenLua] done");
        };
    }

    // =====================================================================
    //                 RESKI HOTPOT - TÊN LỬA (BÌNH TÂN, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantReskiHotpotTenLua() {
        String slug = "reski-hotpot-ten-lua";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-1-normal-2663267668224.webp",
                of("Không gian Reski Hotpot - Tên Lửa", "Reski Hotpot - Ten Lua space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-2-normal-2663267768225.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-3-normal-2663267868226.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-4-normal-2663267968227.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-5-normal-2663268068228.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-6-normal-2663268168229.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-10-normal-2663332668232.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-11-normal-2663332768233.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-13-normal-2663332468230.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-15-normal-2663332568231.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-16-normal-2663332868234.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-17-normal-2663333568235.webp",
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
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_HOTPOT")
                .name(of("Buffet & Gọi món Lẩu", "Hotpot Buffet & A La Carte"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 22:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 30)
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
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu Reski đặc biệt", "Reski signature hotpot")),
            signatureDishL(of("Lẩu Tomyum chua cay", "Spicy Tomyum hotpot")),
            signatureDishL(of("Buffet lẩu bò tươi", "Fresh beef hotpot buffet")),
            signatureDishL(of("Thịt bò Mỹ nhúng lẩu", "US beef slices for hotpot")),
            signatureDishL(of("Hải sản tươi sống", "Fresh seafood platter")),
            signatureDishL(of("Bạch tuộc nhúng lẩu", "Octopus for hotpot")),
            signatureDishL(of("Viên thả lẩu tổng hợp", "Assorted hotpot balls")),
            signatureDishL(of("Rau nhúng lẩu tươi", "Fresh hotpot vegetables")),
            signatureDishL(of("Mì & bún nhúng lẩu", "Noodles for hotpot")),
            signatureDishL(of("Nước chấm pha chế đặc trưng", "Signature dipping sauce"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0001.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0002.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0003.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0004.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0005.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0006.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0007.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0008.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/LAM/reski/0009.jpg",
                of("", ""), false, 8)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet lẩu", "Hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất Buffet Trưa T2-T6", "Weekday Lunch Buffet (Mon-Fri)"))
                        .description(of("Buffet lẩu trưa các ngày trong tuần: Lẩu Reski + Tomyum, nhúng thịt bò, hải sản, viên thả và rau tươi.", "Weekday lunch hotpot buffet: Reski + Tomyum broth, beef, seafood, balls and fresh vegetables."))
                        .priceFrom(new BigDecimal("279000"))
                        .priceTo(new BigDecimal("279000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "lunch"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất Buffet T2-CN", "Daily Buffet (Mon-Sun)"))
                        .description(of("Buffet lẩu cả tuần: Lẩu Reski + Tomyum, nhúng thịt bò, hải sản tươi, viên thả tổng hợp và rau nhúng.", "Daily hotpot buffet: Reski + Tomyum broth, beef, fresh seafood, assorted balls and vegetables."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("349000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot"))
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
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BUFFET_SERVICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Reski Hotpot - Tên Lửa", "About Reski Hotpot - Ten Lua")),
            paragraph(ContentSection.OVERVIEW,
                of("Reski Hotpot - Tên Lửa là điểm hẹn lẩu tươi ngon, giá hợp lý tại khu vực Bình Tân, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ cả hình thức buffet và gọi món, mang đến cho thực khách cơ hội trải nghiệm trọn vị " +
                "những nồi lẩu Reski đặc trưng cùng nguyên liệu tươi mới mỗi ngày.",
                "Reski Hotpot - Ten Lua is a destination for fresh, value-for-money hotpot in Binh Tan District, Ho Chi Minh City. " +
                "The restaurant serves both buffet and a la carte, giving diners the chance to fully enjoy " +
                "the signature Reski broths together with ingredients kept fresh every day.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, hiện đại và sôi động, được bố trí khoa học với sức chứa lên đến khoảng 150 khách. " +
                "Reski Hotpot có thêm phòng riêng phù hợp cho các nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern and vibrant space, well arranged with a capacity of around 150 guests. " +
                "Reski Hotpot also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Reski là hai loại nước lẩu Reski và Tomyum chua cay, ăn kèm thịt bò tươi, hải sản, " +
                "bạch tuộc, viên thả tổng hợp và rau nhúng tươi mới. Thực khách có thể chọn suất buffet trưa T2-T6 chỉ 279K " +
                "hoặc buffet cả tuần T2-CN 349K, vừa túi tiền mà vẫn no nê.",
                "Reski's highlight is its two broths - the signature Reski and the spicy Tomyum - paired with fresh beef, seafood, " +
                "octopus, assorted balls and fresh vegetables. Diners can choose the weekday lunch buffet at just 279K, " +
                "or the all-week buffet at 349K, affordable yet satisfying.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phục vụ nhanh nhẹn, Reski Hotpot - Tên Lửa phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy atmosphere and attentive service, Reski Hotpot - Ten Lua is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Số 228 Tên Lửa, P. Bình Trị Đông B, Q. Bình Tân, gần khu Aeon Mall Bình Tân và đại lộ Võ Văn Kiệt, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at No. 228 Ten Lua, Binh Tri Dong B Ward, Binh Tan District, near Aeon Mall Binh Tan and Vo Van Kiet Avenue, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.612069, 10.74823})
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
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(12)
                .maxPeople(30)
                .totalTables(2)
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
            .districtName(of("Q. Bình Tân", "Binh Tan District"))
            .wardName(of("P. Bình Trị Đông B", "Binh Tri Dong B Ward"))
            .addressLine(of("Số 228 Tên Lửa, P. Bình Trị Đông B, Q. Bình Tân, TP. Hồ Chí Minh", "No. 228 Ten Lua, Binh Tri Dong B Ward, Binh Tan District, Ho Chi Minh City"))
            .location(new double[]{106.612069, 10.74823})
            .name(of("Reski Hotpot - Tên Lửa", "Reski Hotpot - Ten Lua"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet & gọi món lẩu tươi ngon, giá hợp lý tại Bình Tân, TP. Hồ Chí Minh.", "Fresh, value-for-money hotpot buffet & a la carte in Binh Tan, Ho Chi Minh City."))
            .description(of("Reski Hotpot - Tên Lửa là nhà hàng lẩu phục vụ cả buffet và gọi món tại Số 228 Tên Lửa, Q. Bình Tân, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trọn vị những nồi lẩu Reski và Tomyum đặc trưng, nguyên liệu tươi mới, không gian rộng rãi hiện đại, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Reski Hotpot - Ten Lua is a hotpot restaurant serving both buffet and a la carte at No. 228 Ten Lua, Binh Tan District, Ho Chi Minh City. " +
                "It delivers the full flavor of its signature Reski and Tomyum broths with fresh ingredients in a spacious, modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("1900xxxxxx")
            .email("reservation@reskihotpot.vn")
            .website("https://pasgo.vn/nha-hang/reski-hotpot---ten-lua-5657")
            .facebookPage("https://facebook.com/reskihotpot")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("220000"))
            .maxPricePerPerson(new BigDecimal("280000"))
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
