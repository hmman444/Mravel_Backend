// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedYuhuaLeVanSy.java
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
public class SeedYuhuaLeVanSy {

    @Bean
    CommandLineRunner seedYuhuaLeVanSyRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedYuhuaLeVanSy] start");

            RestaurantDoc yuhua = restaurantYuhuaLeVanSy();
            repo.findBySlugAndActiveTrue(yuhua.getSlug())
                .ifPresent(existing -> yuhua.setId(existing.getId()));
            repo.save(yuhua);
            log.info(">>> upsert: {}", yuhua.getSlug());

            log.info(">>> [SeedYuhuaLeVanSy] done");
        };
    }

    // =====================================================================
    //         YUHUA - TAIWANESE HOTPOT PARADISE - LÊ VĂN SỸ (TÂN BÌNH, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantYuhuaLeVanSy() {
        String slug = "yuhua-le-van-sy";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-1-normal-2151357651948.webp",
                of("Không gian nhà hàng Yuhua - Lê Văn Sỹ", "Yuhua - Le Van Sy restaurant space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-2-normal-2151357751949.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-3-normal-2151358151950.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-4-normal-2151358351951.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-5-normal-2151358551952.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-6-normal-2151358751953.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-7-normal-2151358851954.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-8-normal-2151358951955.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-9-normal-2151359051956.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-10-normal-2151359151957.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-11-normal-2151359251958.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy-12-normal-2151359351959.webp",
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
                .code("TAIWANESE")
                .name(of("Đài Loan", "Taiwanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_HOTPOT")
                .name(of("Buffet Lẩu", "Hotpot Buffet"))
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
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(160)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu Đài Loan 6 vị nước lẩu", "Taiwanese hotpot with 6 broths")),
            signatureDishL(of("Lẩu cay Tứ Xuyên mala", "Sichuan mala spicy broth")),
            signatureDishL(of("Lẩu nấm thanh đạm", "Light mushroom broth")),
            signatureDishL(of("Thịt bò Mỹ nhúng lẩu", "US beef slices for hotpot")),
            signatureDishL(of("Ba chỉ bò Mỹ cuộn", "Rolled US beef belly")),
            signatureDishL(of("Hải sản tươi sống tổng hợp", "Assorted fresh seafood")),
            signatureDishL(of("Tôm tươi nhúng lẩu", "Fresh shrimp for hotpot")),
            signatureDishL(of("Viên thả lẩu Đài Loan", "Taiwanese hotpot balls")),
            signatureDishL(of("Rau nhúng tươi tổng hợp", "Assorted fresh vegetables")),
            signatureDishL(of("Sốt chấm tự pha kiểu Đài", "Taiwanese-style dipping sauce bar"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-2.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-3.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-4.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-5.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-6.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-7.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-8.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-9.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-10.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-11.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yuhua/LVS/yuhua-lvs-menu-12.jpg",
                of("", ""), false, 10)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet lẩu Đài Loan", "Taiwanese hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Trưa T2-T6", "Weekday Lunch Buffet (Mon-Fri)"))
                        .description(of("Buffet lẩu Đài Loan trưa các ngày trong tuần: 6 vị nước lẩu, gần 70 món nhúng gồm thịt bò, hải sản, viên thả và rau tươi.", "Weekday lunch Taiwanese hotpot buffet: 6 broths, nearly 70 items including beef, seafood, balls and fresh vegetables."))
                        .priceFrom(new BigDecimal("219000"))
                        .priceTo(new BigDecimal("219000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "lunch"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Tối T2-T6 & cả ngày T7-CN", "Weekday Dinner & Weekend Buffet"))
                        .description(of("Buffet lẩu Đài Loan tối T2-T6 và cả ngày cuối tuần: đầy đủ 6 vị nước lẩu cùng menu nhúng phong phú.", "Taiwanese hotpot buffet for weekday dinner and all-day weekend: full 6 broths with a rich selection of items."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("239000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet cao cấp cả ngày T7-CN", "Premium All-day Weekend Buffet"))
                        .description(of("Buffet lẩu Đài Loan cao cấp cuối tuần với thực đơn nâng cấp: thêm hải sản và thịt bò cao cấp.", "Premium weekend Taiwanese hotpot buffet with an upgraded menu including premium seafood and beef."))
                        .priceFrom(new BigDecimal("329000"))
                        .priceTo(new BigDecimal("329000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc đường Lê Văn Sỹ", "Car parking along Le Van Sy street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ giữ xe, miễn phí cho khách.", "Staff assist with parking, free of charge for guests."))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu nhà hàng Yuhua - Lê Văn Sỹ", "About Yuhua - Le Van Sy")),
            paragraph(ContentSection.OVERVIEW,
                of("Yuhua - Taiwanese Hotpot Paradise tại 291B Lê Văn Sỹ, Q. Tân Bình là thiên đường lẩu Đài Loan giữa lòng TP. Hồ Chí Minh. " +
                "Nhà hàng theo hình thức buffet với 6 vị nước lẩu đặc trưng và gần 70 món nhúng, mang đến cho thực khách trải nghiệm " +
                "ẩm thực Đài Loan trọn vẹn, tươi ngon mỗi ngày.",
                "Yuhua - Taiwanese Hotpot Paradise at 291B Le Van Sy, Tan Binh District is a Taiwanese hotpot paradise in the heart of Ho Chi Minh City. " +
                "Following a buffet format with 6 signature broths and nearly 70 hotpot items, the restaurant offers diners a complete " +
                "Taiwanese dining experience, fresh and delicious every day.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian Yuhua được thiết kế hiện đại, ấm cúng và sôi động, lấy cảm hứng từ phong cách Đài Loan. " +
                "Nhà hàng có sức chứa khoảng 160 khách cùng các phòng riêng tiện nghi, phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "Yuhua's space is designed in a modern, cozy and vibrant style inspired by Taiwanese aesthetics. " +
                "The restaurant seats around 160 guests with comfortable private rooms, suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Yuhua là 6 vị nước lẩu từ thanh đạm nấm, chua cay đến cay tê mala Tứ Xuyên, ăn kèm thịt bò Mỹ, hải sản tươi, " +
                "tôm, viên thả và rau nhúng. Thực khách chọn buffet trưa T2-T6 chỉ 219K, buffet tối T2-T6 và cuối tuần 239K, " +
                "hoặc buffet cao cấp cuối tuần 329K.",
                "Yuhua's highlight is its 6 broths ranging from light mushroom and hot-and-sour to Sichuan mala, paired with US beef, fresh seafood, " +
                "shrimp, balls and fresh vegetables. Diners can choose the weekday lunch buffet at just 219K, the weekday dinner and weekend buffet at 239K, " +
                "or the premium weekend buffet at 329K.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng và phục vụ chu đáo, Yuhua phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật, hẹn hò hay các buổi tiếp khách thân mật.",
                "With a cozy atmosphere and attentive service, Yuhua is ideal for family meals, friends gatherings, " +
                "office parties, birthdays, dates or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, chấp nhận thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, Visa/Master cards are accepted, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 291B Lê Văn Sỹ, Phường 1, Quận Tân Bình, ngay trục đường Lê Văn Sỹ sầm uất, " +
                "thuận tiện di chuyển từ Quận 3, Phú Nhuận và trung tâm TP. Hồ Chí Minh.",
                "The restaurant is located at 291B Le Van Sy, Ward 1, Tan Binh District, right on the bustling Le Van Sy street, " +
                "convenient to reach from District 3, Phu Nhuan and central Ho Chi Minh City.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.667552524644, 10.794829678378})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho một số khung giờ cao điểm.", "A 5% service charge may apply during certain peak hours."))
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
                .totalTables(16)
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
            .districtName(of("Q. Tân Bình", "Tan Binh District"))
            .wardName(of("Phường 1", "Ward 1"))
            .addressLine(of("291B Lê Văn Sỹ, Phường 1, Quận Tân Bình, TP. Hồ Chí Minh", "291B Le Van Sy, Ward 1, Tan Binh District, Ho Chi Minh City"))
            .location(new double[]{106.667552524644, 10.794829678378})
            .name(of("Nhà hàng Yuhua - Lê Văn Sỹ", "Yuhua - Le Van Sy"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu Đài Loan 6 vị nước lẩu, gần 70 món tại Lê Văn Sỹ, Q. Tân Bình, TP. Hồ Chí Minh.", "Taiwanese hotpot buffet with 6 broths and nearly 70 items on Le Van Sy, Tan Binh, Ho Chi Minh City."))
            .description(of("Nhà hàng Yuhua - Taiwanese Hotpot Paradise tại 291B Lê Văn Sỹ, Q. Tân Bình, TP. Hồ Chí Minh là thiên đường buffet lẩu Đài Loan. " +
                "Với 6 vị nước lẩu đặc trưng và gần 70 món nhúng từ thịt bò, hải sản tươi đến viên thả và rau xanh, không gian hiện đại ấm cúng, " +
                "Yuhua phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Yuhua - Taiwanese Hotpot Paradise at 291B Le Van Sy, Tan Binh District, Ho Chi Minh City is a Taiwanese hotpot buffet paradise. " +
                "With 6 signature broths and nearly 70 hotpot items from beef and fresh seafood to balls and vegetables in a modern, cozy space, " +
                "Yuhua suits many occasions from family meals and friends gatherings to company parties."))
            .phone("0818773574")
            .email("reservation@yuhuahotpot.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-yuhua-taiwanese-hotpot-paradise-le-van-sy")
            .facebookPage("https://facebook.com/yuhuahotpot")
            .bookingHotline("0818773574")
            .minPricePerPerson(new BigDecimal("219000"))
            .maxPricePerPerson(new BigDecimal("329000"))
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
