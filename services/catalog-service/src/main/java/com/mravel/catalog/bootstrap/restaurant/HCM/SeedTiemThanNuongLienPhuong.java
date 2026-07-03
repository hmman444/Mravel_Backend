// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedTiemThanNuongLienPhuong.java
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
public class SeedTiemThanNuongLienPhuong {

    @Bean
    CommandLineRunner seedTiemThanNuongLienPhuongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedTiemThanNuongLienPhuong] start");

            RestaurantDoc doc = restaurantTiemThanNuongLienPhuong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedTiemThanNuongLienPhuong] done");
        };
    }

    // =====================================================================
    //            TIỆM THÂN NƯỚNG - LIÊN PHƯỜNG (QUẬN 9, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantTiemThanNuongLienPhuong() {
        String slug = "tiem-than-nuong-lien-phuong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-1-normal-2298773262324.webp",
                of("Không gian Tiệm Thân Nướng - Liên Phường", "Tiem Than Nuong - Lien Phuong space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-2-normal-2298773362325.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-3-normal-2298774062326.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-4-normal-2298774262327.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-5-normal-2298774362328.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-6-normal-2298775462330.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-7-normal-2298774862329.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-8-normal-2298775362331.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-9-normal-2298573862332.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-9-normal-2298775762335.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-10-normal-2298573962333.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-tiem-than-nuong-11-normal-2298574062334.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Đồ nướng", "Grilled & BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt", "Vietnamese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",  of("Mộc mạc", "Rustic")),
            ambience("MODERN",  of("Hiện đại", "Modern"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef slab")),
            signatureDishL(of("Sườn bò nướng", "Grilled beef ribs")),
            signatureDishL(of("Ba chỉ bò nướng", "Grilled beef brisket")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Tôm sú nướng muối ớt", "Grilled tiger prawns with chili salt")),
            signatureDishL(of("Hàu nướng phô mai", "Grilled oysters with cheese")),
            signatureDishL(of("Sò huyết nướng mỡ hành", "Grilled blood cockles with scallion oil")),
            signatureDishL(of("Nầm bò nướng", "Grilled beef udder")),
            signatureDishL(of("Lòng bò nướng", "Grilled beef offal")),
            signatureDishL(of("Rau củ nướng thập cẩm", "Assorted grilled vegetables"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/menu-tiem-than-nuong/menu-thân-nướng-17.jpg",
                of("", ""), false, 16)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BBQ")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò Mỹ nướng tảng", "Grilled US beef slab"))
                        .description(of("Tảng bò Mỹ tươi ướp đậm đà, nướng trên than hồng tại bàn.", "Fresh US beef slab, well marinated and grilled over charcoal at the table."))
                        .priceFrom(new BigDecimal("189000"))
                        .priceTo(new BigDecimal("189000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo nướng 2-3 người", "BBQ Combo for 2-3"))
                        .description(of("Combo nướng gồm bò, hải sản, rau củ và nước chấm, phù hợp nhóm 2-3 người.", "BBQ combo with beef, seafood, vegetables and dipping sauce, ideal for 2-3 people."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("450000"))
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
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "VISA_MASTER",
            "VAT_INVOICE",
            "CHARCOAL_GRILL"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Tiệm Thân Nướng - Liên Phường", "About Tiem Than Nuong - Lien Phuong")),
            paragraph(ContentSection.OVERVIEW,
                of("Tiệm Thân Nướng - Liên Phường là quán nướng ngon, giá hợp lý nằm trên đường Liên Phường, P. Phước Long B, Quận 9, TP. Hồ Chí Minh. " +
                "Nổi tiếng với các món bò tươi, hải sản và đồ nướng nướng than hồng tại bàn, quán mang đến không khí ăn uống sôi động, " +
                "rất hợp cho những buổi tụ tập bạn bè và liên hoan cuối tuần.",
                "Tiem Than Nuong - Lien Phuong is a tasty, value-for-money grill house on Lien Phuong Street, Phuoc Long B Ward, District 9, Ho Chi Minh City. " +
                "Famous for fresh beef, seafood and charcoal-grilled dishes cooked right at your table, it offers a lively dining atmosphere " +
                "perfect for friends gatherings and weekend parties.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán có không gian rộng rãi gồm cả khu trong nhà thoáng mát và khu nướng ngoài trời sôi động, sức chứa lên tới khoảng 180 khách. " +
                "Bên cạnh khu bàn chung, quán còn bố trí phòng riêng cho nhóm đông, phù hợp tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious layout with both an airy indoor area and a lively outdoor grill zone, with a capacity of around 180 guests. " +
                "Besides communal seating, it also has private rooms for large groups, suitable for family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn đa dạng với điểm nhấn là bò Mỹ nướng tảng, sườn bò, ba chỉ bò cùng các món hải sản nướng như bạch tuộc sa tế, " +
                "tôm sú muối ớt, hàu phô mai và sò huyết mỡ hành. Mọi món đều được ướp đậm đà và nướng tại bàn, giữ trọn độ tươi nóng.",
                "The menu is diverse, highlighted by grilled US beef slab, beef ribs and brisket, along with grilled seafood such as satay octopus, " +
                "chili-salt prawns, cheese oysters and scallion-oil cockles. Every dish is richly marinated and grilled at the table to keep it fresh and hot.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí trẻ trung và mức giá khoảng 200.000 - 250.000đ/người, Tiệm Thân Nướng phù hợp cho tụ tập bạn bè, ăn gia đình, " +
                "liên hoan văn phòng, sinh nhật hay những buổi hẹn hò thân mật.",
                "With a youthful vibe and a price of around 200,000 - 250,000 VND per person, Tiem Than Nuong is ideal for friends gatherings, family meals, " +
                "office parties, birthdays or casual dates.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán trang bị điều hòa, wifi, khu nướng ngoài trời, phòng riêng, ghế trẻ em, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, an outdoor grill area, private rooms, kids chairs, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Quán tọa lạc tại 125 Liên Phường, P. Phước Long B, Quận 9, TP. Hồ Chí Minh, gần khu dân cư Liên Phường và cao tốc Long Thành - Dầu Giây, " +
                "thuận tiện di chuyển từ khu Đông và trung tâm thành phố.",
                "The restaurant is located at 125 Lien Phuong, Phuoc Long B Ward, District 9, Ho Chi Minh City, near the Lien Phuong residential area and the Long Thanh - Dau Giay Expressway, " +
                "convenient to reach from the eastern area and city center.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.788382, 10.806783})
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
                .id("pr-25")
                .name(of("Phòng riêng 25 khách", "Private room for 25 guests"))
                .seats(25)
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
            .districtName(of("Quận 9", "District 9"))
            .wardName(of("P. Phước Long B", "Phuoc Long B Ward"))
            .addressLine(of("125 Liên Phường, P. Phước Long B, Quận 9, TP. Hồ Chí Minh", "125 Lien Phuong, Phuoc Long B Ward, District 9, Ho Chi Minh City"))
            .location(new double[]{106.788382, 10.806783})
            .name(of("Tiệm Thân Nướng - Liên Phường", "Tiem Than Nuong - Lien Phuong"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Quán nướng bò & hải sản ngon, giá hợp lý tại Liên Phường, Quận 9, TP. Hồ Chí Minh.", "Tasty beef & seafood grill house at good prices on Lien Phuong, District 9, Ho Chi Minh City."))
            .description(of("Tiệm Thân Nướng - Liên Phường là quán nướng tại 125 Liên Phường, P. Phước Long B, Quận 9, TP. Hồ Chí Minh, " +
                "chuyên các món bò tươi, hải sản và đồ nướng than hồng nướng tại bàn. Không gian rộng rãi với khu nướng ngoài trời sôi động, " +
                "phù hợp cho nhiều dịp từ tụ tập bạn bè, ăn gia đình đến liên hoan công ty.",
                "Tiem Than Nuong - Lien Phuong is a grill house at 125 Lien Phuong, Phuoc Long B Ward, District 9, Ho Chi Minh City, " +
                "specializing in fresh beef, seafood and charcoal-grilled dishes cooked at the table. With a spacious space and a lively outdoor grill zone, " +
                "it suits many occasions from friends gatherings and family meals to company parties."))
            .phone("1900xxxxxx")
            .email("reservation@tiemthannuong.vn")
            .website("https://pasgo.vn/nha-hang/tiem-than-nuong-lien-phuong")
            .facebookPage("https://facebook.com/tiemthannuong")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("200000"))
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
