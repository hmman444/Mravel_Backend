// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedYakiYakiYoTayThanh.java
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
public class SeedYakiYakiYoTayThanh {

    @Bean
    CommandLineRunner seedYakiYakiYoTayThanhRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedYakiYakiYoTayThanh] start");

            RestaurantDoc yaki = restaurantYakiYakiYoTayThanh();
            repo.findBySlugAndActiveTrue(yaki.getSlug())
                .ifPresent(existing -> yaki.setId(existing.getId()));
            repo.save(yaki);
            log.info(">>> upsert: {}", yaki.getSlug());

            log.info(">>> [SeedYakiYakiYoTayThanh] done");
        };
    }

    // =====================================================================
    //          YAKI YAKI YO - BUFFET NƯỚNG LẨU BĂNG CHUYỀN (TÂN PHÚ, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantYakiYakiYoTayThanh() {
        String slug = "yaki-yaki-yo-tay-thanh";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-1-normal-2254075256545.webp",
                of("Không gian Yaki Yaki Yo - Tây Thạnh", "Yaki Yaki Yo - Tay Thanh space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-2-normal-2254075356546.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-3-normal-2254075456547.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-4-normal-2254075556548.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-5-normal-2254075756549.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-6-normal-2254075856550.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-7-normal-2254076056551.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-8-normal-2254082456552.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-9-normal-2254076356553.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-10-normal-2254076456554.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-11-normal-2254076556555.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-yaki-yaki-yo-tay-thanh-12-normal-2254076656556.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "BBQ / Grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_JAPANESE")
                .name(of("Hàn - Nhật", "Korean - Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET")
                .name(of("Buffet băng chuyền", "Conveyor belt buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (T2-T6: 11:00-14:00 & 16:00-22:00; T7-CN: 11:00-22:00) ----------
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
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Buffet nướng lẩu băng chuyền", "Conveyor belt BBQ & hotpot buffet")),
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Hàn", "Korean-style grilled pork belly")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Tôm sú nướng", "Grilled tiger prawns")),
            signatureDishL(of("Lẩu Tomyum chua cay", "Spicy Tomyum hotpot")),
            signatureDishL(of("Lẩu nấm thanh đạm", "Light mushroom hotpot")),
            signatureDishL(of("Xúc xích & viên thả băng chuyền", "Conveyor sausages & assorted balls")),
            signatureDishL(of("Nấm & rau tươi nhúng lẩu", "Fresh mushrooms & vegetables for hotpot")),
            signatureDishL(of("Kem & tráng miệng tự chọn", "Self-serve ice cream & desserts"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-1.png",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-2.png",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-3.png",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-4.png",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-5.png",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Yakiyo/nha-hang-yaki-yakiyo-menu-6.png",
                of("", ""), false, 5)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet nướng lẩu băng chuyền", "Conveyor belt BBQ & hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet băng chuyền tiêu chuẩn", "Standard conveyor belt buffet"))
                        .description(of("Buffet nướng lẩu băng chuyền không giới hạn: thịt bò, heo, hải sản, viên thả, rau nấm tươi và tráng miệng.", "Unlimited conveyor belt BBQ & hotpot buffet: beef, pork, seafood, balls, fresh vegetables, mushrooms and dessert."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet băng chuyền cao cấp", "Premium conveyor belt buffet"))
                        .description(of("Buffet băng chuyền cao cấp bổ sung các món bò Mỹ, hải sản tươi và món nướng đặc sắc.", "Premium conveyor belt buffet with extra US beef, fresh seafood and special grilled items."))
                        .priceFrom(new BigDecimal("219000"))
                        .priceTo(new BigDecimal("219000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "premium"))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Yaki Yaki Yo - Tây Thạnh", "About Yaki Yaki Yo - Tay Thanh")),
            paragraph(ContentSection.OVERVIEW,
                of("Yaki Yaki Yo - Tây Thạnh là nhà hàng buffet nướng lẩu băng chuyền độc đáo tại Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Tới đây, thực khách được trải nghiệm bữa tiệc nướng lẩu buffet trên băng chuyền tự động, tự do chọn món " +
                "ngay tại bàn với hơn trăm món Hàn - Nhật hấp dẫn, mới lạ.",
                "Yaki Yaki Yo - Tay Thanh is a unique conveyor belt BBQ & hotpot buffet restaurant in Tan Phu District, Ho Chi Minh City. " +
                "Here, diners enjoy a grill and hotpot buffet served on an automatic conveyor belt, freely picking dishes " +
                "right at the table from over a hundred attractive Korean - Japanese items.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, hiện đại và sôi động với hệ thống băng chuyền chạy quanh các dãy bàn, sức chứa lên đến khoảng 180 khách. " +
                "Nhà hàng có thêm phòng riêng phù hợp cho các nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern and vibrant space with a conveyor belt running around the table rows, " +
                "with a capacity of around 180 guests. It also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Yaki Yaki Yo là buffet nướng lẩu băng chuyền với ba chỉ bò Mỹ, ba chỉ heo kiểu Hàn, " +
                "bạch tuộc nướng sa tế, tôm sú, lẩu Tomyum chua cay và lẩu nấm thanh đạm. Thực khách có thể chọn suất buffet " +
                "tiêu chuẩn 199K hoặc suất cao cấp 219K, ăn không giới hạn mà vẫn rất hợp túi tiền.",
                "Yaki Yaki Yo's highlight is its conveyor belt BBQ & hotpot buffet with US beef belly, Korean-style pork belly, " +
                "grilled octopus with satay, tiger prawns, spicy Tomyum hotpot and light mushroom hotpot. Diners can choose the standard buffet at 199K " +
                "or the premium buffet at 219K - unlimited and very affordable.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và hình thức băng chuyền vui mắt, Yaki Yaki Yo - Tây Thạnh phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a vibrant atmosphere and a fun conveyor belt format, Yaki Yaki Yo - Tay Thanh is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Số 165 đường Tây Thạnh, P. Tây Thạnh, Q. Tân Phú, gần khu công nghiệp Tân Bình và sân bay Tân Sơn Nhất, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận.",
                "The restaurant is located at No. 165 Tay Thanh Street, Tay Thanh Ward, Tan Phu District, near Tan Binh Industrial Park and Tan Son Nhat Airport, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.62394295386, 10.8144591952844})
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
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(40)
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
            .districtName(of("Q. Tân Phú", "Tan Phu District"))
            .wardName(of("P. Tây Thạnh", "Tay Thanh Ward"))
            .addressLine(of("Số 165 đường Tây Thạnh, P. Tây Thạnh, Q. Tân Phú, TP. Hồ Chí Minh", "No. 165 Tay Thanh Street, Tay Thanh Ward, Tan Phu District, Ho Chi Minh City"))
            .location(new double[]{106.62394295386, 10.8144591952844})
            .name(of("Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh", "Yaki Yaki Yo - Conveyor Belt BBQ & Hotpot Buffet - Tay Thanh"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng lẩu băng chuyền độc đáo, giá hợp lý tại Tân Phú, TP. Hồ Chí Minh.", "Unique conveyor belt BBQ & hotpot buffet at a great value in Tan Phu, Ho Chi Minh City."))
            .description(of("Yaki Yaki Yo - Tây Thạnh là nhà hàng buffet nướng lẩu băng chuyền tại Số 165 đường Tây Thạnh, Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trải nghiệm tự chọn món Hàn - Nhật ngay trên băng chuyền, nguyên liệu tươi mới, không gian rộng rãi hiện đại, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Yaki Yaki Yo - Tay Thanh is a conveyor belt BBQ & hotpot buffet restaurant at No. 165 Tay Thanh Street, Tan Phu District, Ho Chi Minh City. " +
                "It offers a self-serve Korean - Japanese experience right on the conveyor belt with fresh ingredients in a spacious, modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@yakiyakiyo.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-yaki-yaki-yo-buffet-nuong-lau-bang-chuyen-tay-thanh")
            .facebookPage("https://facebook.com/yakiyakiyo")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("199000"))
            .maxPricePerPerson(new BigDecimal("219000"))
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
