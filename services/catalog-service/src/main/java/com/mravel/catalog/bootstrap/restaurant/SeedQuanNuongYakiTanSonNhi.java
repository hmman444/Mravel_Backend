// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedQuanNuongYakiTanSonNhi.java
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
public class SeedQuanNuongYakiTanSonNhi {

    @Bean
    CommandLineRunner seedQuanNuongYakiTanSonNhiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedQuanNuongYakiTanSonNhi] start");

            RestaurantDoc yaki = restaurantQuanNuongYakiTanSonNhi();
            repo.findBySlugAndActiveTrue(yaki.getSlug())
                .ifPresent(existing -> yaki.setId(existing.getId()));
            repo.save(yaki);
            log.info(">>> upsert: {}", yaki.getSlug());

            log.info(">>> [SeedQuanNuongYakiTanSonNhi] done");
        };
    }

    // =====================================================================
    //              QUÁN NƯỚNG YAKI - TÂN SƠN NHÌ (TÂN PHÚ, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantQuanNuongYakiTanSonNhi() {
        String slug = "quan-nuong-yaki-tan-son-nhi";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-1-normal-124812512348.webp",
                of("Không gian Quán Nướng Yaki - Tân Sơn Nhì", "Quan Nuong Yaki - Tan Son Nhi space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-2-normal-124812612349.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-3-normal-124812712350.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-4-normal-124812812351.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-5-normal-124812912352.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-6-normal-124813012353.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-7-normal-124813112354.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-8-normal-124813212355.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-9-normal-124813312356.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-10-normal-124813412357.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-11-normal-124813512358.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/quan-nuong-yaki-tan-son-nhi-slide-12-normal-124813612359.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Đồ nướng", "Grilled / BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet nướng & lẩu", "Grill & Hotpot Buffet"))
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
            ambience("COZY",            of("Ấm cúng", "Cozy")),
            ambience("MODERN",          of("Hiện đại", "Modern")),
            ambience("VIBRANT",         of("Sôi động", "Vibrant")),
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
            signatureDishL(of("Buffet nướng & lẩu Yaki", "Yaki grill & hotpot buffet")),
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef block")),
            signatureDishL(of("Ba chỉ bò nướng kiểu Nhật", "Japanese-style grilled beef brisket")),
            signatureDishL(of("Ba chỉ heo nướng ướp đặc trưng", "Marinated grilled pork belly")),
            signatureDishL(of("Sườn non nướng BBQ", "BBQ grilled pork ribs")),
            signatureDishL(of("Hải sản nướng tổng hợp", "Assorted grilled seafood")),
            signatureDishL(of("Tôm nướng phô mai", "Grilled cheese prawns")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Lẩu Thái chua cay", "Spicy Thai hotpot")),
            signatureDishL(of("Rau & nấm nhúng lẩu tươi", "Fresh vegetables & mushrooms for hotpot"))
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
                .code("BUFFET")
                .name(of("Buffet nướng & lẩu", "Grill & hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet nướng & lẩu tiêu chuẩn", "Standard grill & hotpot buffet"))
                        .description(of("Buffet nướng kiểu Nhật kết hợp lẩu: bò Mỹ, ba chỉ heo, hải sản, rau nấm và nước lẩu tùy chọn.", "Japanese-style grill buffet combined with hotpot: US beef, pork belly, seafood, vegetables, mushrooms and choice of broth."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet nướng & lẩu cao cấp", "Premium grill & hotpot buffet"))
                        .description(of("Buffet nâng cao thêm bò Mỹ nướng tảng, hải sản cao cấp, tôm phô mai và bạch tuộc sa tế.", "Upgraded buffet with grilled US beef block, premium seafood, cheese prawns and satay octopus."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("250000"))
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
            .carParkingLocation(of("Đỗ xe ô tô trên đường Tân Sơn Nhì gần nhà hàng", "Car parking on Tan Son Nhi street near the restaurant"))
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
            "BBQ_GRILL_TABLE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Quán Nướng Yaki - Tân Sơn Nhì", "About Quan Nuong Yaki - Tan Son Nhi")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán Nướng Yaki - Tân Sơn Nhì là địa chỉ buffet nướng & lẩu quen thuộc của tín đồ ẩm thực khu vực Tân Phú, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ cả hình thức buffet và gọi món, mang phong cách nướng kiểu Nhật kết hợp lẩu, giúp thực khách thoải mái " +
                "lựa chọn theo nhu cầu và túi tiền.",
                "Quan Nuong Yaki - Tan Son Nhi is a familiar grill & hotpot buffet spot for food lovers in Tan Phu District, Ho Chi Minh City. " +
                "The restaurant serves both buffet and a la carte in a Japanese-style grill combined with hotpot, letting diners freely " +
                "choose to suit their needs and budget.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian quán rộng rãi, hiện đại và sôi động, mỗi bàn được trang bị bếp nướng riêng cùng hệ thống hút khói, " +
                "sức chứa lên đến khoảng 160 khách. Quán có thêm phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern and vibrant space, with each table fitted with its own grill and smoke extraction, " +
                "and a capacity of around 160 guests. It also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Yaki là buffet nướng kiểu Nhật với bò Mỹ, ba chỉ heo ướp đặc trưng, sườn non BBQ, hải sản nướng " +
                "và nồi lẩu Thái chua cay ăn kèm. Mức giá phổ biến khoảng 180.000 - 250.000đ/người, hợp lý cho một bữa nướng no nê.",
                "Yaki's highlight is its Japanese-style grill buffet with US beef, marinated pork belly, BBQ ribs, grilled seafood " +
                "and a spicy Thai hotpot to go with it. Typical price is around 180,000 - 250,000 VND/person, affordable for a hearty grill meal.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, sôi động và phục vụ nhanh nhẹn, Quán Nướng Yaki - Tân Sơn Nhì phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a cozy, vibrant atmosphere and attentive service, Quan Nuong Yaki - Tan Son Nhi is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bàn nướng chuyên dụng, hệ thống âm thanh ánh sáng và hỗ trợ " +
                "trang trí, MC cho tiệc. Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, dedicated grill tables, sound & light systems " +
                "and decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 375 Tân Sơn Nhì, P. Tân Thành, Q. Tân Phú, nằm trên trục đường lớn đông dân cư, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Tân Bình, Bình Tân.",
                "The restaurant is located at 375 Tan Son Nhi, Tan Thanh Ward, Tan Phu District, on a busy main road, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts such as Tan Binh and Binh Tan.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.628938, 10.794024})
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
            .invoiceNotes(of("Buffet chưa bao gồm VAT; nhà hàng xuất hóa đơn VAT theo yêu cầu.", "Buffet price excludes VAT; the restaurant issues VAT invoices on request."))
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
            .districtName(of("Q. Tân Phú", "Tan Phu District"))
            .wardName(of("P. Tân Thành", "Tan Thanh Ward"))
            .addressLine(of("375 Tân Sơn Nhì, P. Tân Thành, Q. Tân Phú, TP. Hồ Chí Minh", "375 Tan Son Nhi, Tan Thanh Ward, Tan Phu District, Ho Chi Minh City"))
            .location(new double[]{106.628938, 10.794024})
            .name(of("Quán Nướng Yaki - Tân Sơn Nhì", "Quan Nuong Yaki - Tan Son Nhi"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng & lẩu kiểu Nhật, gọi món giá hợp lý tại Tân Phú, TP. Hồ Chí Minh.", "Japanese-style grill & hotpot buffet, value a la carte in Tan Phu, Ho Chi Minh City."))
            .description(of("Quán Nướng Yaki - Tân Sơn Nhì là nhà hàng buffet nướng & lẩu kết hợp gọi món tại 375 Tân Sơn Nhì, Q. Tân Phú, TP. Hồ Chí Minh. " +
                "Quán mang phong cách nướng kiểu Nhật với bò Mỹ, ba chỉ heo, hải sản và lẩu Thái chua cay, nguyên liệu tươi mới, không gian rộng rãi hiện đại, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Quan Nuong Yaki - Tan Son Nhi is a grill & hotpot buffet restaurant with a la carte at 375 Tan Son Nhi, Tan Phu District, Ho Chi Minh City. " +
                "It offers a Japanese-style grill with US beef, pork belly, seafood and spicy Thai hotpot, fresh ingredients in a spacious, modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("1900xxxxxx")
            .email("reservation@quannuongyaki.vn")
            .website("https://pasgo.vn/nha-hang/quan-nuong-yaki---tan-son-nhi")
            .facebookPage("https://facebook.com/quannuongyaki")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("180000"))
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
