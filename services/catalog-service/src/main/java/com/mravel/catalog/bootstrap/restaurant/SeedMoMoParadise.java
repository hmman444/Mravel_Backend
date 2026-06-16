// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedMoMoParadise.java
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
public class SeedMoMoParadise {

    @Bean
    CommandLineRunner seedMoMoParadiseRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedMoMoParadise] start");

            RestaurantDoc momo = restaurantMoMoParadiseNguyenThiMinhKhai();
            repo.findBySlugAndActiveTrue(momo.getSlug())
                .ifPresent(existing -> momo.setId(existing.getId()));
            repo.save(momo);
            log.info(">>> upsert: {}", momo.getSlug());

            log.info(">>> [SeedMoMoParadise] done");
        };
    }

    // =====================================================================
    //          MO MO PARADISE - NGUYỄN THỊ MINH KHAI (TP.HCM)
    // =====================================================================

    private static RestaurantDoc restaurantMoMoParadiseNguyenThiMinhKhai() {
        String slug = "mo-mo-paradise-nguyen-thi-minh-khai";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-1-normal-1742620530249.webp",
                of("Không gian Mo Mo Paradise - Nguyễn Thị Minh Khai", "Mo Mo Paradise - Nguyen Thi Minh Khai space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-2-normal-1742620630250.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-3-normal-1742620830251.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-4-normal-1742620930252.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-5-normal-1742621130253.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-6-normal-1742621230254.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-7-normal-1742621330255.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-8-normal-1742621430256.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-9-normal-1742621530257.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-10-normal-1742621630258.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-11-normal-1742621730259.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-mo-mo-paradise-nguyen-thi-minh-khai-12-normal-1742621830260.webp",
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
                .code("HOTPOT")
                .name(of("Lẩu Nhật (Shabu Shabu & Sukiyaki)", "Japanese Hotpot (Shabu Shabu & Sukiyaki)"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 11:00 - 22:00) ----------
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
            ambience("ELEGANT", of("Trang nhã", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(20)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(20)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu Shabu Shabu", "Shabu Shabu Hotpot")),
            signatureDishL(of("Lẩu Sukiyaki", "Sukiyaki Hotpot")),
            signatureDishL(of("Bò Mỹ thái lát", "Sliced American beef")),
            signatureDishL(of("Bò Wagyu thượng hạng", "Premium Wagyu beef")),
            signatureDishL(of("Thịt heo Kurobuta", "Kurobuta pork")),
            signatureDishL(of("Set rau nấm tươi Nhật Bản", "Japanese fresh vegetable & mushroom set")),
            signatureDishL(of("Đậu phụ non kiểu Nhật", "Japanese silken tofu")),
            signatureDishL(of("Nước sốt mè Goma đặc trưng", "Signature Goma sesame sauce")),
            signatureDishL(of("Mì Udon tươi", "Fresh Udon noodles")),
            signatureDishL(of("Set buffet lẩu Nhật không giới hạn", "Unlimited Japanese hotpot buffet set"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/mo%20mo%20paradise/menu-mo-mo-6.jpg",
                of("", ""), false, 5)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_SET")
                .name(of("Set buffet lẩu Nhật", "Japanese hotpot buffet sets"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Shabu Shabu tiêu chuẩn", "Standard Shabu Shabu buffet"))
                        .description(of("Buffet lẩu Nhật không giới hạn với thịt bò, heo, rau nấm và nước dùng đặc trưng.", "Unlimited Japanese hotpot buffet with beef, pork, vegetables, mushrooms and signature broth."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "shabu", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Wagyu cao cấp", "Premium Wagyu buffet"))
                        .description(of("Buffet lẩu Nhật cao cấp với bò Wagyu thượng hạng và nguyên liệu tuyển chọn.", "Premium Japanese hotpot buffet with top-grade Wagyu beef and selected ingredients."))
                        .priceFrom(new BigDecimal("459000"))
                        .priceTo(new BigDecimal("499000"))
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
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng trên đường Nguyễn Thị Minh Khai", "Car parking near the restaurant on Nguyen Thi Minh Khai street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại tòa nhà", "Motorbike parking at the building"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ giữ xe, xe máy miễn phí, ô tô gửi bãi lân cận.", "Parking staff available; motorbikes free, cars use a nearby lot."))
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
            "BIRTHDAY_DECOR",
            "FREE_MOTORBIKE_PARKING",
            "CAR_PARKING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "SET_LUNCH",
            "ALL_YOU_CAN_EAT",
            "GROUP_BOOKING"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Tóm tắt Mo Mo Paradise - Nguyễn Thị Minh Khai", "Mo Mo Paradise - Nguyen Thi Minh Khai overview")),
            paragraph(ContentSection.OVERVIEW,
                of("Mo Mo Paradise - Nguyễn Thị Minh Khai là thiên đường lẩu Nhật Bản giữa lòng Quận 3, TP. Hồ Chí Minh. " +
                "Thương hiệu nổi tiếng với hai loại lẩu trứ danh Shabu Shabu và Sukiyaki, mang đến trải nghiệm buffet " +
                "lẩu Nhật chuẩn vị cùng nguồn nguyên liệu tươi ngon và nước dùng được ninh kỳ công.",
                "Mo Mo Paradise - Nguyen Thi Minh Khai is a Japanese hotpot paradise in the heart of District 3, Ho Chi Minh City. " +
                "The brand is famous for its two iconic hotpots, Shabu Shabu and Sukiyaki, offering an authentic Japanese " +
                "hotpot buffet experience with fresh ingredients and carefully simmered broths.")),
            paragraph(ContentSection.OVERVIEW,
                of("Tọa lạc tại số 96 Nguyễn Thị Minh Khai, nhà hàng có vị trí trung tâm, dễ tìm, phù hợp cho cả những bữa ăn " +
                "gia đình ấm cúng lẫn các buổi tụ họp bạn bè, liên hoan công ty sôi động.",
                "Located at 96 Nguyen Thi Minh Khai, the restaurant enjoys a central, easy-to-find location, suitable for both " +
                "cozy family meals and lively gatherings with friends or company parties.")),

            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian Mo Mo Paradise được thiết kế hiện đại, ấm cúng theo phong cách Nhật Bản, với hệ thống bàn bếp từ " +
                "tiện lợi cho từng nhóm khách. Nhà hàng có sức chứa lớn cùng phòng riêng phục vụ các nhóm đông và sự kiện.",
                "Mo Mo Paradise features a modern, cozy Japanese-style space with induction cooktops at each table for convenience. " +
                "The restaurant has a large capacity along with private rooms serving big groups and events.")),

            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Mo Mo Paradise là buffet lẩu Nhật không giới hạn: thực khách tự do lựa chọn các loại thịt bò Mỹ, " +
                "bò Wagyu, heo Kurobuta thái lát mỏng, cùng set rau nấm tươi, đậu phụ non và mì Udon. Nước sốt mè Goma đặc trưng " +
                "là linh hồn tạo nên hương vị khó quên.",
                "The highlight of Mo Mo Paradise is the unlimited Japanese hotpot buffet: diners freely choose American beef, " +
                "Wagyu beef and thinly sliced Kurobuta pork, along with fresh vegetable and mushroom sets, silken tofu and Udon noodles. " +
                "The signature Goma sesame sauce is the soul behind an unforgettable flavor.")),

            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best for which occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với hình thức buffet lẩu sum vầy quanh nồi nước dùng nóng hổi, Mo Mo Paradise đặc biệt phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, sinh nhật, liên hoan và tiệc công ty. Không gian linh hoạt đáp ứng cả nhóm nhỏ lẫn đoàn khách đông.",
                "With its hotpot buffet format gathered around a steaming broth, Mo Mo Paradise is especially suitable for family meals, " +
                "friends gatherings, birthdays, parties and company events. The flexible space accommodates both small groups and large parties.")),

            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, hệ thống âm thanh ánh sáng và hỗ trợ trang trí sinh nhật, " +
                "tiệc nhóm. Có phòng riêng cho đoàn khách, xuất hóa đơn VAT và chấp nhận thanh toán thẻ Visa/Master.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, a sound and light system and supports birthday " +
                "and group party decoration. Private rooms are available for groups, with VAT invoices and Visa/Master card payment accepted.")),

            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm trên trục đường Nguyễn Thị Minh Khai, Phường 6, Quận 3 - khu vực trung tâm TP. Hồ Chí Minh, " +
                "thuận tiện di chuyển bằng ô tô, xe máy hay taxi công nghệ, gần nhiều điểm tham quan và khách sạn.",
                "The restaurant is on Nguyen Thi Minh Khai street, Ward 6, District 3 - a central area of Ho Chi Minh City, " +
                "convenient to reach by car, motorbike or ride-hailing, close to many attractions and hotels.")),

            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. Đặt cọc áp dụng với nhóm đông " +
                "hoặc yêu cầu trang trí đặc biệt; nhà hàng hỗ trợ xác nhận nhanh và linh hoạt đổi giờ.",
                "Guests are advised to book in advance to reserve a table, especially on weekends and peak hours. A deposit applies to " +
                "large groups or special decoration requests; the restaurant offers quick confirmation and flexible time changes.")),

            mapBlock(ContentSection.OVERVIEW, new double[]{106.6900, 10.7780})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 400.000đ/chai.", "Spirits corkage fee: 400,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc yêu cầu phòng riêng/trang trí đặc biệt.", "Applies to bookings of 6 or more guests or requests for a private room/special decoration."))
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
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu, không xuất hóa đơn trực tiếp.", "The restaurant issues VAT invoices on request; direct invoices are not issued."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí phục vụ 5% áp dụng cho nhóm đông và phòng riêng.", "A 5% service charge applies to large groups and private rooms."))
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
                .note(of("Phù hợp cặp đôi.", "Suitable for couples."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(14)
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
                .minPeople(10)
                .maxPeople(20)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm/liên hoan công ty.", "Private room, suitable for group parties and company events."))
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
            .districtName(of("Quận 3", "District 3"))
            .wardName(of("Phường 6", "Ward 6"))
            .addressLine(of("96 Nguyễn Thị Minh Khai, Phường 6, Quận 3, TP. Hồ Chí Minh", "96 Nguyen Thi Minh Khai, Ward 6, District 3, Ho Chi Minh City"))
            .location(new double[]{106.6900, 10.7780})
            .name(of("Mo Mo Paradise - Nguyễn Thị Minh Khai", "Mo Mo Paradise - Nguyen Thi Minh Khai"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Thiên đường buffet lẩu Nhật Bản (Shabu Shabu & Sukiyaki) tại trung tâm Quận 3, TP. Hồ Chí Minh.", "A Japanese hotpot buffet paradise (Shabu Shabu & Sukiyaki) in central District 3, Ho Chi Minh City."))
            .description(of("Mo Mo Paradise - Nguyễn Thị Minh Khai là nhà hàng buffet lẩu Nhật Bản nổi tiếng, " +
                "chuyên hai loại lẩu Shabu Shabu và Sukiyaki chuẩn vị. " +
                "Nằm tại số 96 Nguyễn Thị Minh Khai, Quận 3, TP. Hồ Chí Minh, nhà hàng mang đến trải nghiệm ẩm thực Nhật Bản " +
                "tươi ngon, không gian hiện đại ấm cúng, phù hợp cho gia đình, bạn bè và tiệc công ty.",
                "Mo Mo Paradise - Nguyen Thi Minh Khai is a renowned Japanese hotpot buffet restaurant, " +
                "specializing in two authentic hotpots, Shabu Shabu and Sukiyaki. " +
                "Located at 96 Nguyen Thi Minh Khai, District 3, Ho Chi Minh City, the restaurant offers a fresh, delicious Japanese " +
                "dining experience in a modern, cozy space, suitable for families, friends and company parties."))
            .phone("0931006005")
            .email("reservation@momoparadise.vn")
            .website("https://example.com/mo-mo-paradise")
            .facebookPage("https://facebook.com/momoparadisevietnam")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("300000"))
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
