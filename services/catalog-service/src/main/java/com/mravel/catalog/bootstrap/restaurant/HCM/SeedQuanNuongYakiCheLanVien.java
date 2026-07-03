// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedQuanNuongYakiCheLanVien.java
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
public class SeedQuanNuongYakiCheLanVien {

    @Bean
    CommandLineRunner seedQuanNuongYakiCheLanVienRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedQuanNuongYakiCheLanVien] start");

            RestaurantDoc yaki = restaurantQuanNuongYakiCheLanVien();
            repo.findBySlugAndActiveTrue(yaki.getSlug())
                .ifPresent(existing -> yaki.setId(existing.getId()));
            repo.save(yaki);
            log.info(">>> upsert: {}", yaki.getSlug());

            log.info(">>> [SeedQuanNuongYakiCheLanVien] done");
        };
    }

    // =====================================================================
    //            QUÁN NƯỚNG YAKI - CHẾ LAN VIÊN (TÂN PHÚ, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantQuanNuongYakiCheLanVien() {
        String slug = "quan-nuong-yaki-che-lan-vien";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-1-normal-124816912374.webp",
                of("Không gian Quán Nướng Yaki - Chế Lan Viên", "Quan Nuong Yaki - Che Lan Vien space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-2-normal-124817012375.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-3-normal-124817112376.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-4-normal-124817212377.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-5-normal-124817312378.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-6-normal-124817412379.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-7-normal-124817512380.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-8-normal-124817612381.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-9-normal-124817712382.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-10-normal-124817812383.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-11-normal-124817912384.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-che-lan-vien-slide-12-normal-124818012385.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Đồ nướng", "BBQ / Grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
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
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("RUSTIC",  of("Mộc mạc kiểu Nhật", "Japanese rustic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(25)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly")),
            signatureDishL(of("Bò Wagyu nướng", "Grilled Wagyu beef")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Nhật", "Japanese-style grilled pork belly")),
            signatureDishL(of("Lưỡi bò nướng", "Grilled beef tongue")),
            signatureDishL(of("Sườn bò nướng tảng", "Grilled beef short rib")),
            signatureDishL(of("Tôm sú nướng muối ớt", "Grilled tiger prawn with chili salt")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Nấm các loại nướng", "Assorted grilled mushrooms")),
            signatureDishL(of("Cơm trộn Nhật Bản", "Japanese mixed rice bowl")),
            signatureDishL(of("Salad rau trộn kiểu Yaki", "Yaki-style fresh salad"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/2.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/3.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/4.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/5.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/6.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/7.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/8.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/9.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/10.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/11.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/12.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/qnyaki/13.jpg",
                of("", ""), false, 11)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILL")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ba chỉ bò Mỹ nướng", "Grilled US beef belly"))
                        .description(of("Ba chỉ bò Mỹ thái lát mỏng, ướp gia vị kiểu Nhật, nướng trên bếp than tại bàn.", "Thinly sliced US beef belly, Japanese-style marinated, grilled at the table over charcoal."))
                        .priceFrom(new BigDecimal("159000"))
                        .priceTo(new BigDecimal("159000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo nướng cho 2 người", "Grill combo for 2"))
                        .description(of("Combo gồm các loại thịt bò, heo, hải sản và rau nướng, kèm nước chấm đặc trưng cho 2 khách.", "Combo of assorted beef, pork, seafood and grilled vegetables with signature dipping sauce for 2 guests."))
                        .priceFrom(new BigDecimal("459000"))
                        .priceTo(new BigDecimal("459000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("bbq", "combo"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Chế Lan Viên", "Car parking on Che Lan Vien street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại quán", "Motorbike parking at the restaurant"))
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
            heading(ContentSection.OVERVIEW, of("Giới thiệu Quán Nướng Yaki - Chế Lan Viên", "About Quan Nuong Yaki - Che Lan Vien")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán Nướng Yaki - Chế Lan Viên là địa chỉ chuyên gọi món nướng kiểu Nhật ngay tại Số 18 Chế Lan Viên, Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Quán mang đến trải nghiệm nướng than tại bàn với nguồn nguyên liệu tươi mới, thịt bò, heo, hải sản được tẩm ướp đậm đà theo phong cách Yakiniku.",
                "Quan Nuong Yaki - Che Lan Vien is a Japanese-style grill restaurant at No. 18 Che Lan Vien, Tan Phu District, Ho Chi Minh City. " +
                "It offers a tabletop charcoal grilling experience with fresh ingredients - beef, pork and seafood richly marinated in Yakiniku style.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian quán ấm cúng, mộc mạc pha nét hiện đại kiểu Nhật, có cả khu trong nhà và bàn ngoài trời thoáng đãng. " +
                "Sức chứa khoảng 120 khách cùng phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant has a cozy space blending Japanese rustic and modern touches, with both indoor seating and airy outdoor tables. " +
                "Capacity is around 120 guests with private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn nổi bật với ba chỉ bò Mỹ, bò Wagyu, ba chỉ heo, lưỡi bò, sườn bò nướng tảng cùng tôm sú, bạch tuộc và nấm nướng. " +
                "Tất cả được nướng trực tiếp tại bàn, ăn kèm nước chấm đặc trưng, cơm trộn và salad kiểu Yaki, mức giá khoảng 180.000 - 350.000đ/người.",
                "The menu highlights US beef belly, Wagyu beef, pork belly, beef tongue and grilled short rib alongside tiger prawn, octopus and mushrooms. " +
                "Everything is grilled right at the table, served with signature dipping sauce, mixed rice and Yaki-style salad, at around 180,000 - 350,000 VND/person.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và phục vụ chu đáo, Yaki - Chế Lan Viên phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật, hẹn hò hay các buổi tiếp khách thân mật.",
                "With a vibrant atmosphere and attentive service, Yaki - Che Lan Vien is ideal for family meals, " +
                "friends gatherings, office parties, birthdays, dates or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bàn ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, chấp nhận thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, outdoor tables, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, Visa/Master cards accepted and VAT invoices issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán tọa lạc tại Số 18 Chế Lan Viên, P. Tây Thạnh, Q. Tân Phú, gần khu công nghiệp Tân Bình và sân bay Tân Sơn Nhất, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Tân Bình, Bình Tân.",
                "The restaurant is located at No. 18 Che Lan Vien, Tay Thanh Ward, Tan Phu District, near Tan Binh Industrial Park and Tan Son Nhat Airport, " +
                "convenient to reach from central Ho Chi Minh City and nearby districts such as Tan Binh and Binh Tan.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của quán.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.632064, 10.812678})
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
                .maxPeople(25)
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
            .addressLine(of("Số 18 Chế Lan Viên, P. Tây Thạnh, Q. Tân Phú, TP. Hồ Chí Minh", "No. 18 Che Lan Vien, Tay Thanh Ward, Tan Phu District, Ho Chi Minh City"))
            .location(new double[]{106.632064, 10.812678})
            .name(of("Quán Nướng Yaki - Chế Lan Viên", "Quan Nuong Yaki - Che Lan Vien"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Quán nướng kiểu Nhật, gọi món nướng than tại bàn ở Tân Phú, TP. Hồ Chí Minh.", "Japanese-style grill, tabletop charcoal BBQ a la carte in Tan Phu, Ho Chi Minh City."))
            .description(of("Quán Nướng Yaki - Chế Lan Viên là quán nướng kiểu Nhật chuyên gọi món tại Số 18 Chế Lan Viên, Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Quán mang đến trải nghiệm nướng than tại bàn với thịt bò, heo, hải sản tươi tẩm ướp đậm đà theo phong cách Yakiniku, không gian ấm cúng pha nét Nhật, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Quan Nuong Yaki - Che Lan Vien is a Japanese-style grill restaurant serving a la carte at No. 18 Che Lan Vien, Tan Phu District, Ho Chi Minh City. " +
                "It offers a tabletop charcoal grilling experience with fresh beef, pork and seafood marinated in Yakiniku style, in a cozy Japanese-inspired space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("0961538460")
            .email("reservation@yakigrill.vn")
            .website("https://pasgo.vn/nha-hang/quan-nuong-yaki-che-lan-vien")
            .facebookPage("https://facebook.com/yakichelanvien")
            .bookingHotline("0961538460")
            .minPricePerPerson(new BigDecimal("180000"))
            .maxPricePerPerson(new BigDecimal("350000"))
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
