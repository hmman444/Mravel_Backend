// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedLauNuongMiniCandy.java
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
public class SeedLauNuongMiniCandy {

    @Bean
    CommandLineRunner seedLauNuongMiniCandyRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauNuongMiniCandy] start");

            RestaurantDoc candy = restaurantLauNuongMiniCandy();
            repo.findBySlugAndActiveTrue(candy.getSlug())
                .ifPresent(existing -> candy.setId(existing.getId()));
            repo.save(candy);
            log.info(">>> upsert: {}", candy.getSlug());

            log.info(">>> [SeedLauNuongMiniCandy] done");
        };
    }

    // =====================================================================
    //         LẨU NƯỚNG MINI CANDY - NGUYỄN VĂN ĐẬU (BÌNH THẠNH, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantLauNuongMiniCandy() {
        String slug = "lau-nuong-mini-candy-nguyen-van-dau";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-1-normal-2219003654578.webp",
                of("Không gian Lẩu Nướng Mini Candy - Nguyễn Văn Đậu", "Lau Nuong Mini Candy - Nguyen Van Dau space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-2-normal-2219003754579.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-3-normal-2219003854580.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-4-normal-2219003954581.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-5-normal-2219004054582.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-6-normal-2219004154583.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-7-normal-2219004254584.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-8-normal-2219004454585.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-9-normal-2219004554586.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-10-normal-2219004654587.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-11-normal-2219004754588.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-mini-candy-nguyen-van-dau-12-normal-2219004854589.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("GRILL")
                .name(of("Nướng", "Grill / BBQ"))
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
                .build()
        );

        // ---------- Giờ mở cửa (T2-T6: 14:00-22:00, T7 & CN: 10:00-22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    14, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   14, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 14, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  14, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    14, 0, 22, 0),
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
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("STUDENT_GROUP", of("Nhóm sinh viên", "Student group")),
            suitable("AFTER_WORK",    of("Gặp gỡ sau giờ làm", "After-work hangout"))
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
            .totalCapacity(120)
            .maxGroupSize(24)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(24)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu Thái chua cay Mini Candy", "Mini Candy spicy Thai hotpot")),
            signatureDishL(of("Lẩu nấm thanh đạm", "Light mushroom hotpot")),
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef slab")),
            signatureDishL(of("Ba chỉ bò nướng ướp đặc biệt", "Marinated grilled beef belly")),
            signatureDishL(of("Sườn non nướng mật ong", "Honey grilled pork ribs")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Tôm nướng phô mai", "Grilled cheese prawns")),
            signatureDishL(of("Nấm kim châm cuộn ba chỉ", "Enoki mushroom in pork belly rolls")),
            signatureDishL(of("Rau củ nhúng lẩu tươi", "Fresh vegetables for hotpot")),
            signatureDishL(of("Combo nướng lẩu Mini Candy", "Mini Candy grill & hotpot combo"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/minicandyt3/lau-nuong-mini-candy-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/minicandyt3/lau-nuong-mini-candy-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/minicandyt3/lau-nuong-mini-candy-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/minicandyt3/lau-nuong-mini-candy-4.jpg",
                of("", ""), false, 3)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILL")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ba chỉ bò nướng", "Grilled beef belly"))
                        .description(of("Ba chỉ bò ướp gia vị đặc trưng, nướng trên bếp than tại bàn.", "Beef belly marinated in signature spices, grilled on tabletop charcoal."))
                        .priceFrom(new BigDecimal("89000"))
                        .priceTo(new BigDecimal("99000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Sườn non nướng mật ong", "Honey grilled pork ribs"))
                        .description(of("Sườn non ướp mật ong nướng vàng đều, thơm ngọt.", "Pork ribs marinated in honey, grilled to a sweet golden finish."))
                        .priceFrom(new BigDecimal("109000"))
                        .priceTo(new BigDecimal("129000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "pork"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Món lẩu", "Hotpot dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu Thái chua cay", "Spicy Thai hotpot"))
                        .description(of("Nước lẩu Thái chua cay đậm đà, ăn kèm hải sản, thịt và rau tươi.", "Rich spicy Thai broth served with seafood, meat and fresh vegetables."))
                        .priceFrom(new BigDecimal("159000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "thai"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Đỗ xe ô tô dọc đường Nguyễn Văn Đậu", "Car parking along Nguyen Van Dau street"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên trông xe máy miễn phí cho khách.", "Free motorbike valet for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "OUTDOOR_TABLE",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "TABLETOP_GRILL",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "BIRTHDAY_CAKE",
            "VISA_MASTER",
            "VAT_INVOICE",
            "TAKEAWAY"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Lẩu Nướng Mini Candy - Nguyễn Văn Đậu", "About Lau Nuong Mini Candy - Nguyen Van Dau")),
            paragraph(ContentSection.OVERVIEW,
                of("Lẩu Nướng Mini Candy - Nguyễn Văn Đậu là điểm hẹn lẩu nướng quen thuộc của giới trẻ và các gia đình tại Q. Bình Thạnh, TP. Hồ Chí Minh. " +
                "Nhà hàng kết hợp trọn vẹn hai trải nghiệm nướng than tại bàn và lẩu nóng hổi, với thực đơn phong phú, giá bình dân chỉ từ khoảng 120.000 - 180.000đ/người.",
                "Lau Nuong Mini Candy - Nguyen Van Dau is a familiar grill-and-hotpot spot for young diners and families in Binh Thanh District, Ho Chi Minh City. " +
                "It combines tabletop charcoal grilling with steaming hotpot in a rich, budget-friendly menu of around 120,000 - 180,000 VND per person.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian quán mộc mạc, ấm cúng và sôi động, vừa có khu vực trong nhà mát mẻ vừa có bàn ngoài trời thoáng đãng, sức chứa khoảng 120 khách. " +
                "Quán bố trí thêm phòng riêng nhỏ phù hợp cho các nhóm bạn, sinh nhật hay liên hoan công ty.",
                "The restaurant has a rustic, cozy and lively atmosphere, with both cool indoor seating and airy outdoor tables for around 120 guests. " +
                "It also offers small private rooms suited to groups of friends, birthdays or office gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Mini Candy là các món nướng ướp đậm đà như bò Mỹ nướng tảng, ba chỉ bò, sườn non mật ong, bạch tuộc sa tế và tôm nướng phô mai, " +
                "ăn kèm những nồi lẩu Thái chua cay hoặc lẩu nấm thanh đạm. Tất cả đều dùng nguyên liệu tươi, phục vụ ngay tại bàn để giữ trọn hương vị.",
                "Mini Candy's highlights are its boldly marinated grilled dishes such as US beef slab, beef belly, honey pork ribs, satay octopus and cheese prawns, " +
                "paired with spicy Thai hotpot or a light mushroom broth. Everything uses fresh ingredients, served right at your table to keep the full flavor.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí trẻ trung, vui nhộn, Mini Candy phù hợp cho các buổi tụ tập bạn bè, ăn gia đình, hẹn hò, sinh nhật hay gặp gỡ sau giờ làm. " +
                "Đây cũng là lựa chọn quen thuộc của nhóm sinh viên và dân văn phòng quanh khu Bình Thạnh.",
                "With a youthful, fun vibe, Mini Candy is perfect for friends gatherings, family meals, dates, birthdays or after-work meetups. " +
                "It is also a go-to choice for students and office workers around Binh Thanh.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán trang bị điều hòa, wifi, ghế trẻ em, bếp nướng tại bàn, khu bàn ngoài trời, phòng riêng và hỗ trợ trang trí sinh nhật. " +
                "Có giữ xe máy miễn phí, nhận đặt mang về và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant offers air conditioning, wifi, kids chairs, tabletop grills, outdoor tables, private rooms and birthday decoration support. " +
                "Free motorbike parking, takeaway orders and VAT invoices are available on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 273 đường Nguyễn Văn Đậu, Phường 11, Quận Bình Thạnh, nằm trên trục đường lớn dễ tìm, " +
                "thuận tiện di chuyển từ khu Phú Nhuận, Gò Vấp và trung tâm TP. Hồ Chí Minh.",
                "The restaurant is located at 273 Nguyen Van Dau Street, Ward 11, Binh Thanh District, on a major, easy-to-find road, " +
                "convenient to reach from Phu Nhuan, Go Vap and central Ho Chi Minh City.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, nhất là vào buổi tối cuối tuần. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system, especially on weekend evenings. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.689600, 10.807600})
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
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 8 or more guests or private room reservations."))
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
                .totalTables(6)
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
                .totalTables(14)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-24")
                .name(of("Phòng riêng 24 khách", "Private room for 24 guests"))
                .seats(24)
                .minPeople(10)
                .maxPeople(24)
                .totalTables(2)
                .depositPrice(new BigDecimal("700000"))
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
            .districtName(of("Q. Bình Thạnh", "Binh Thanh District"))
            .wardName(of("Phường 11", "Ward 11"))
            .addressLine(of("273 đường Nguyễn Văn Đậu, Phường 11, Quận Bình Thạnh, TP. Hồ Chí Minh", "273 Nguyen Van Dau Street, Ward 11, Binh Thanh District, Ho Chi Minh City"))
            .location(new double[]{106.689600, 10.807600})
            .name(of("Lẩu Nướng Mini Candy - Nguyễn Văn Đậu", "Lau Nuong Mini Candy - Nguyen Van Dau"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu nướng bình dân, đa dạng món, không gian sôi động tại Bình Thạnh, TP. Hồ Chí Minh.", "Affordable grill & hotpot with a lively space in Binh Thanh, Ho Chi Minh City."))
            .description(of("Lẩu Nướng Mini Candy - Nguyễn Văn Đậu là nhà hàng lẩu nướng tại 273 đường Nguyễn Văn Đậu, Q. Bình Thạnh, TP. Hồ Chí Minh. " +
                "Quán kết hợp món nướng than tại bàn cùng các nồi lẩu Thái chua cay, lẩu nấm thanh đạm, nguyên liệu tươi mới, giá bình dân, " +
                "không gian trẻ trung phù hợp cho tụ tập bạn bè, ăn gia đình và liên hoan.",
                "Lau Nuong Mini Candy - Nguyen Van Dau is a grill-and-hotpot restaurant at 273 Nguyen Van Dau Street, Binh Thanh District, Ho Chi Minh City. " +
                "It pairs tabletop charcoal grilling with spicy Thai and light mushroom hotpots, using fresh ingredients at friendly prices, " +
                "in a youthful space ideal for friends gatherings, family meals and parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@minicandy.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-lau-nuong-mini-candy-nguyen-van-dau")
            .facebookPage("https://facebook.com/launuongminicandy")
            .bookingHotline("(+84) 0931 006 005")
            .minPricePerPerson(new BigDecimal("120000"))
            .maxPricePerPerson(new BigDecimal("180000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
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
