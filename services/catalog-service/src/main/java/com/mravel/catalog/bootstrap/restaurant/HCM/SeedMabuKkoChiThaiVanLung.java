// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedMabuKkoChiThaiVanLung.java
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
public class SeedMabuKkoChiThaiVanLung {

    @Bean
    CommandLineRunner seedMabuKkoChiThaiVanLungRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedMabuKkoChiThaiVanLung] start");

            RestaurantDoc mabu = restaurantMabuKkoChiThaiVanLung();
            repo.findBySlugAndActiveTrue(mabu.getSlug())
                .ifPresent(existing -> mabu.setId(existing.getId()));
            repo.save(mabu);
            log.info(">>> upsert: {}", mabu.getSlug());

            log.info(">>> [SeedMabuKkoChiThaiVanLung] done");
        };
    }

    // =====================================================================
    //            MABU-KKO CHI - THÁI VĂN LUNG (QUẬN 1, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantMabuKkoChiThaiVanLung() {
        String slug = "mabu-kko-chi-thai-van-lung";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-1-normal-2599101868089.webp",
                of("Không gian Mabu-KKO Chi - Thái Văn Lung", "Mabu-KKO Chi - Thai Van Lung space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-4-normal-2599101968090.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-5-normal-2599102068091.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-6-normal-2599100968080.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-7-normal-2599101068081.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-8-normal-2599101168082.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-9-normal-2599101268083.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-10-normal-2599101368084.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-11-normal-2599101468085.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-12-normal-2599101568086.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-13-normal-2599101668087.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nh-mabu-kko-chi-quan1-14-normal-2599101768088.webp",
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
                .code("YAKINIKU")
                .name(of("Nướng Yakiniku", "Yakiniku grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_BBQ")
                .name(of("Nướng BBQ Hàn Quốc", "Korean BBQ"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (16:00 - 23:59, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    16, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   16, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 16, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  16, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    16, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  16, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    16, 0, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("AFTER_WORK",    of("Tan làm gặp gỡ", "After-work meetup"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("ELEGANT", of("Tinh tế", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(24)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(24)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Hàn", "Korean-style grilled pork belly")),
            signatureDishL(of("Lưỡi bò nướng Yakiniku", "Yakiniku grilled beef tongue")),
            signatureDishL(of("Bò Wagyu thái lát nướng", "Sliced Wagyu beef for grilling")),
            signatureDishL(of("Sườn bò nướng sốt đặc biệt", "Grilled beef short ribs in special sauce")),
            signatureDishL(of("Nầm bò nướng", "Grilled beef brisket cartilage")),
            signatureDishL(of("Tôm sú nướng phô mai", "Grilled tiger prawn with cheese")),
            signatureDishL(of("Bạch tuộc nướng cay Hàn Quốc", "Korean spicy grilled octopus")),
            signatureDishL(of("Canh kim chi đậu hũ", "Kimchi tofu stew")),
            signatureDishL(of("Cơm trộn Bibimbap", "Bibimbap mixed rice"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-0.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-1.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-2.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-3.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-4.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/mabukkochi-quan1/menu-MABUKKOCHI-hình%20ảnh-5.jpg",
                of("", ""), false, 5)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("COMBO")
                .name(of("Combo nướng", "Grill combos"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Combo Nướng 2 người", "Grill Combo for 2"))
                        .description(of("Combo nướng cho 2 khách: ba chỉ bò Mỹ, ba chỉ heo Hàn, lưỡi bò, kèm rau và kim chi ăn kèm.", "Grill combo for 2: US beef brisket, Korean pork belly, beef tongue, served with vegetables and kimchi."))
                        .priceFrom(new BigDecimal("399000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "bbq", "grill"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo Nướng 4 người", "Grill Combo for 4"))
                        .description(of("Combo nướng cho 4 khách: bò Wagyu, sườn bò, nầm bò, tôm sú phô mai, bạch tuộc cay và món phụ ăn kèm.", "Grill combo for 4: Wagyu beef, beef short ribs, brisket cartilage, cheese prawn, spicy octopus and side dishes."))
                        .priceFrom(new BigDecimal("799000"))
                        .priceTo(new BigDecimal("799000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "bbq", "grill"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên phố Thái Văn Lung, có nhân viên hỗ trợ.", "Car parking on Thai Van Lung street, with staff support."))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy gần nhà hàng.", "Motorbike parking near the restaurant."))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Khu vực trung tâm Quận 1, ưu tiên gửi xe khu lân cận.", "Central District 1 area, nearby parking lots recommended."))
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
            "VISA_MASTER",
            "VAT_INVOICE",
            "BBQ_GRILL_TABLE",
            "ENGLISH_MENU",
            "BIRTHDAY_SERVICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Mabu-KKO Chi - Thái Văn Lung", "About Mabu-KKO Chi - Thai Van Lung")),
            paragraph(ContentSection.OVERVIEW,
                of("Mabu-KKO Chi - Thái Văn Lung là nhà hàng nướng phong cách Yakiniku Nhật Bản kết hợp BBQ Hàn Quốc, " +
                "tọa lạc ngay con phố ẩm thực sầm uất Thái Văn Lung, Quận 1, TP. Hồ Chí Minh. Nhà hàng phục vụ gọi món với " +
                "nguồn thịt bò, heo và hải sản tươi chọn lọc, mang đến trải nghiệm nướng đậm chất Đông Á ngay tại trung tâm thành phố.",
                "Mabu-KKO Chi - Thai Van Lung is a Japanese Yakiniku grill blended with Korean BBQ, located on the lively " +
                "Thai Van Lung food street in District 1, Ho Chi Minh City. The restaurant serves a la carte with selected " +
                "fresh beef, pork and seafood, delivering an authentic East Asian grill experience in the heart of the city.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng hiện đại, ấm cúng và sôi động, mỗi bàn được trang bị bếp nướng riêng giúp thực khách tự tay nướng món. " +
                "Sức chứa khoảng 120 khách, có thêm phòng riêng phù hợp cho nhóm bạn, tiệc gia đình hay buổi gặp mặt đối tác.",
                "The restaurant offers a modern, cozy and vibrant space, with each table fitted with its own grill so diners can cook at their seat. " +
                "It can host around 120 guests and has private rooms suitable for groups of friends, family parties or partner meetings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Mabu-KKO Chi là các phần thịt nướng tươi: ba chỉ bò Mỹ, ba chỉ heo kiểu Hàn, lưỡi bò, bò Wagyu thái lát, " +
                "sườn bò sốt đặc biệt cùng hải sản như tôm sú phô mai và bạch tuộc cay. Các món được ăn kèm kim chi, rau tươi và nước sốt pha chế riêng.",
                "Mabu-KKO Chi's highlight is its fresh grilled cuts: US beef brisket, Korean-style pork belly, beef tongue, sliced Wagyu, " +
                "short ribs in special sauce, alongside seafood such as cheese prawn and spicy octopus. Dishes come with kimchi, fresh vegetables and house dipping sauces.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với vị trí trung tâm và không khí sôi động, Mabu-KKO Chi - Thái Văn Lung phù hợp cho buổi tụ tập bạn bè sau giờ làm, hẹn hò, " +
                "ăn gia đình, liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật.",
                "With a central location and lively atmosphere, Mabu-KKO Chi - Thai Van Lung is ideal for after-work gatherings, dates, " +
                "family meals, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, bàn nướng riêng, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng cùng hỗ trợ trang trí và MC cho tiệc. " +
                "Có menu tiếng Anh, nhận thanh toán thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, individual grill tables, kids chairs, private rooms, sound & light systems " +
                "and decoration / MC support for events. English menu is available, Visa/Master cards are accepted and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm tại 8A/D5 Thái Văn Lung, P. Bến Nghé, Q. 1, ngay khu phố Tây và phố ẩm thực Nhật - Hàn của Sài Gòn, " +
                "gần Nhà thờ Đức Bà và phố đi bộ Nguyễn Huệ, rất thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh.",
                "The restaurant is located at 8A/D5 Thai Van Lung, Ben Nghe Ward, District 1, right in the Western quarter and Japanese - Korean food street of Saigon, " +
                "near Notre-Dame Cathedral and Nguyen Hue walking street, very convenient to reach from central Ho Chi Minh City.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào buổi tối cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekend evenings and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.7047323, 10.7793143})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% trên tổng hóa đơn.", "A 5% service charge applies to the total bill."))
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
                .name(of("Bàn nướng 2", "Grill table for 2"))
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
                .note(of("Bàn nướng riêng, phù hợp cặp đôi.", "Individual grill table, suitable for couples."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn nướng 4", "Grill table for 4"))
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
                .id("pr-24")
                .name(of("Phòng riêng 24 khách", "Private room for 24 guests"))
                .seats(24)
                .minPeople(10)
                .maxPeople(24)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
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
            .districtName(of("Quận 1", "District 1"))
            .wardName(of("P. Bến Nghé", "Ben Nghe Ward"))
            .addressLine(of("8A/D5 Thái Văn Lung, P. Bến Nghé, Q. 1, TP. Hồ Chí Minh", "8A/D5 Thai Van Lung, Ben Nghe Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.7047323, 10.7793143})
            .name(of("Mabu-KKO Chi - Thái Văn Lung", "Mabu-KKO Chi - Thai Van Lung"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nướng Yakiniku Nhật Bản & BBQ Hàn Quốc giữa trung tâm Quận 1, TP. Hồ Chí Minh.", "Japanese Yakiniku & Korean BBQ grill in central District 1, Ho Chi Minh City."))
            .description(of("Mabu-KKO Chi - Thái Văn Lung là nhà hàng nướng phong cách Yakiniku Nhật Bản kết hợp BBQ Hàn Quốc tại 8A/D5 Thái Văn Lung, Q. 1, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến các phần thịt bò, heo và hải sản tươi chọn lọc, bếp nướng riêng từng bàn, không gian hiện đại sôi động, " +
                "phù hợp cho nhiều dịp từ hẹn hò, tụ tập bạn bè đến liên hoan công ty.",
                "Mabu-KKO Chi - Thai Van Lung is a Japanese Yakiniku grill blended with Korean BBQ at 8A/D5 Thai Van Lung, District 1, Ho Chi Minh City. " +
                "It offers selected fresh beef, pork and seafood, individual grills at each table, in a modern and vibrant space, " +
                "suitable for dates, friends gatherings and company parties."))
            .phone("19006005")
            .email("reservation@mabukkochi.vn")
            .website("https://pasgo.vn/nha-hang/mabu-kko-chi-thai-van-lung-5650")
            .facebookPage("https://facebook.com/mabukkochi")
            .bookingHotline("19006005")
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
