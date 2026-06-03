// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedHoiAnRestaurants.java
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
import com.mravel.catalog.model.doc.RestaurantDoc.ReviewStats;
import com.mravel.catalog.model.doc.RestaurantDoc.SignatureDish;
import com.mravel.catalog.model.doc.RestaurantDoc.SuitableFor;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.vi;
import static com.mravel.catalog.utils.restaurant.Localized.of;

@Configuration
@Profile("seed")
public class SeedHoiAnRestaurants {

    @Bean
    CommandLineRunner seedHoiAnRestaurantsRunner(RestaurantDocRepository repo) {
        return args -> {
            System.out.println(">>> [SeedHoiAnRestaurants] start");

            RestaurantDoc redBean = restaurantRedBeanHoiAn();
            repo.findBySlugAndActiveTrue(redBean.getSlug())
                .ifPresent(existing -> redBean.setId(existing.getId()));
            repo.save(redBean);
            System.out.println(">>> upsert: " + redBean.getSlug());

            RestaurantDoc temple = restaurantTheTempleHoiAn();
            repo.findBySlugAndActiveTrue(temple.getSlug())
                .ifPresent(existing -> temple.setId(existing.getId()));
            repo.save(temple);
            System.out.println(">>> upsert: " + temple.getSlug());

            System.out.println(">>> [SeedHoiAnRestaurants] done");
        };
    }

    // =====================================================================
    //                       HOI AN - HÙNG VƯƠNG
    // =====================================================================

    private static RestaurantDoc restaurantRedBeanHoiAn() {
        String slug = "red-bean-hoi-an-hung-vuong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-1-normal-2706684530014.webp",
                of("Không gian Red Bean Hoi An - Hùng Vương", "Red Bean Hoi An - Hung Vuong space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-2-normal-2706684769957.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-3-normal-2706684869958.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-4-normal-2706684969959.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-5-normal-2706685069960.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-6-normal-2706685169961.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-7-normal-2706685269962.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-8-normal-2706685369963.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-9-normal-2706685469964.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-10-normal-2706685569965.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-11-normal-2706685669966.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/red-bean-hoi-an-hung-vuong-12-normal-2706685769967.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_VIET_ASIAN")
                .name(of("Buffet & Gọi món Việt - Á", "Vietnamese - Asian Buffet & A La Carte"))
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
            ambience("ELEGANT",   of("Trang nhã", "Elegant")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("LUXURIOUS", of("Sang trọng", "Luxurious"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(20)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(20)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Gỏi tôm ngó sen", "Shrimp and lotus stem salad")),
            signatureDishL(of("Hải sản nướng xiên", "Grilled seafood skewers")),
            signatureDishL(of("Cá chẽm hấp", "Steamed sea bass")),
            signatureDishL(of("Cơm gà Hội An", "Hoi An chicken rice")),
            signatureDishL(of("Gà nướng ống tre", "Bamboo tube grilled chicken")),
            signatureDishL(of("Tôm xào sốt me", "Stir-fried shrimp with tamarind sauce")),
            signatureDishL(of("Cá nướng lá chuối", "Banana leaf grilled fish")),
            signatureDishL(of("Tam hữu", "Tam Huu (three friends roll)")),
            signatureDishL(of("Súp gà Hội An", "Hoi An chicken soup"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Red%20Bean%20-%20H%E1%BB%99i%20An/red-bean-hoi-an-menu-13.jpg",
                of("", ""), false, 12)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món chính", "Main course"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cơm gà Hội An", "Hoi An chicken rice"))
                        .description(of("Cơm gà đặc sản Hội An, dùng kèm rau thơm và nước mắm chua ngọt.", "Hoi An specialty chicken rice, served with herbs and sweet and sour fish sauce."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("chicken", "rice", "hoi-an-special"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Hải sản nướng xiên", "Grilled seafood skewers"))
                        .description(of("Các loại hải sản tươi nướng xiên, ướp gia vị đậm đà.", "Assorted fresh seafood skewers, marinated with rich spices."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grill"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe tại nhà hàng", "Parking at the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi đỗ xe tại nhà hàng", "Parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn, miễn phí cho khách.", "Security staff on hand, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "MC_SERVICE",
            "FREE_CAR_PARKING",
            "OUTDOOR_TABLE",
            "FREE_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Tóm tắt Red Bean Hoi An - Hùng Vương", "Red Bean Hoi An - Hung Vuong overview")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng Red Bean Hoi An là điểm đến lý tưởng cho những ai yêu thích ẩm thực Việt - Á. " +
                "Nằm trong khuôn viên La Siesta Hoi An Resort & Spa, nhà hàng mang đến trải nghiệm ẩm thực đa dạng " +
                "với các món ăn truyền thống Việt Nam và châu Á, được chế biến tinh tế và sáng tạo.",
                "Red Bean Hoi An is an ideal destination for lovers of Vietnamese - Asian cuisine. " +
                "Located within La Siesta Hoi An Resort & Spa, the restaurant offers a diverse culinary experience " +
                "with traditional Vietnamese and Asian dishes, prepared with finesse and creativity.")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian của Red Bean được thiết kế theo phong cách Á Đông truyền thống, " +
                "kết hợp với những nét hiện đại, tạo nên một bầu không khí ấm cúng và sang trọng, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty hay tiếp khách đối tác.",
                "Red Bean's space is designed in a traditional Asian style, " +
                "blended with modern touches, creating a cozy and luxurious atmosphere, " +
                "suitable for many occasions from family meals and friends gatherings to company parties or business meals.")),
            heading(ContentSection.OVERVIEW, of("Điểm đặc trưng", "Highlights")),
            paragraph(ContentSection.OVERVIEW,
                of("Red Bean Hoi An thu hút thực khách bởi thực đơn phong phú các món Việt - Á, " +
                "trong đó có nhiều đặc sản Hội An được biến tấu sáng tạo nhưng vẫn giữ trọn hương vị truyền thống.",
                "Red Bean Hoi An attracts diners with a rich menu of Vietnamese - Asian dishes, " +
                "including many Hoi An specialties creatively reinterpreted while fully preserving their traditional flavors.")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian ấm cúng, trang nhã cùng chất lượng phục vụ chuyên nghiệp " +
                "giúp Red Bean trở thành lựa chọn yêu thích của nhiều gia đình, nhóm bạn và khách nghỉ dưỡng tại La Siesta Hoi An Resort & Spa.",
                "The cozy, elegant space together with professional service quality " +
                "make Red Bean a favorite choice for many families, groups of friends and resort guests at La Siesta Hoi An Resort & Spa.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{108.3175, 15.8795})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(6, 1,  of("Không áp dụng ưu đãi ngày 1/6", "Promotion not applicable on Jun 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2"))
            // Các rule âm lịch đặc biệt có thể thêm sau nếu cần
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 400.000đ/chai.", "Spirits corkage fee: 400,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(5)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 5 khách trở lên hoặc yêu cầu trang trí đặc biệt.", "Applies to bookings of 5 or more guests or requests for special decoration."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(20)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu, không xuất hóa đơn trực tiếp.", "The restaurant issues VAT invoices on request; direct invoices are not issued."))
            .serviceChargePercent(null)
            .serviceChargeNotes(of("Không thu phí phục vụ riêng.", "No separate service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.", "Outside drinks are allowed, with a corkage fee depending on the type of drink."))
            .outsideDrinkFees(outsideDrinkFees)
            .build();

        // ---------- Review stats ----------
        ReviewStats reviewStats = ReviewStats.builder()
            .foodScore(4.7)
            .spaceScore(4.6)
            .serviceScore(4.8)
            .priceScore(4.3)
            .locationScore(4.5)
            .keywords(List.of(
                keyword("good_food", of("Món ăn ngon", "Delicious food"), 120),
                keyword("friendly_staff", of("Nhân viên thân thiện", "Friendly staff"), 80),
                keyword("nice_space", of("Không gian đẹp", "Nice space"), 60)
            ))
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
                .totalTables(10)
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
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-6-vip")
                .name(of("Bàn 6 VIP", "VIP Table for 6"))
                .seats(6)
                .minPeople(5)
                .maxPeople(6)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Vị trí đẹp, ưu tiên phục vụ.", "Great location, priority service."))
                .build(),

            TableType.builder()
                .id("pr-10")
                .name(of("Phòng riêng 10 khách", "Private room for 10 guests"))
                .seats(10)
                .minPeople(8)
                .maxPeople(12)
                .totalTables(2) // coi như 2 phòng riêng
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiếp khách/tiệc nhỏ.", "Private room, suitable for business meals and small parties."))
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
            .destinationSlug("hoi-an")
            .parentPlaceSlug("la-siesta-hoi-an-resort-spa")
            .cityName(of("Hội An", "Hoi An"))
            .districtName(of("Tp. Hội An", "Hoi An City"))
            .wardName(of("P. Thanh Hà", "Thanh Ha Ward"))
            .addressLine(of("Tầng trệt, La Siesta Hội An Resort & Spa, Số 132, Đ. Hùng Vương, P. Thanh Hà, Tp. Hội An", "Ground floor, La Siesta Hoi An Resort & Spa, No. 132 Hung Vuong St., Thanh Ha Ward, Hoi An City"))
            .location(new double[]{108.3175, 15.8795})
            .name(of("Red Bean Hoi An - Hùng Vương", "Red Bean Hoi An - Hung Vuong"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet & gọi món Việt - Á trong khuôn viên La Siesta Hoi An Resort & Spa.", "Vietnamese - Asian buffet & a la carte within La Siesta Hoi An Resort & Spa."))
            .description(of("Red Bean Hoi An - Hùng Vương là nhà hàng chuyên ẩm thực Việt - Á, " +
                "nằm tại tầng trệt La Siesta Hoi An Resort & Spa. " +
                "Nhà hàng mang đến trải nghiệm ẩm thực đa dạng với các món đặc sản Hội An " +
                "và nhiều món ăn châu Á được chế biến tinh tế, sáng tạo, phù hợp cho nhiều dịp khác nhau.",
                "Red Bean Hoi An - Hung Vuong is a Vietnamese - Asian cuisine restaurant, " +
                "located on the ground floor of La Siesta Hoi An Resort & Spa. " +
                "The restaurant offers a diverse culinary experience with Hoi An specialties " +
                "and many Asian dishes prepared with finesse and creativity, suitable for a variety of occasions."))
            .phone("1900xxxxxx")
            .email("reservation@redbeanhoian.com")
            .website("https://example.com/red-bean-hoi-an")
            .facebookPage("https://facebook.com/redbeanhoian")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("350000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.MODERATE)
            .priceBucket(PriceBucket.FROM_150K_TO_250K) // hoặc FROM_250K_TO_350K tùy bạn map
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
            .avgRating(4.7)
            .reviewsCount(150)
            .ratingLabel(of("Rất tốt", "Very good"))
            .reviewStats(reviewStats)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }

    // =====================================================================
    //                 HOI AN - THE TEMPLE RESTAURANT & LOUNGE
    // =====================================================================

    private static RestaurantDoc restaurantTheTempleHoiAn() {
        String slug = "the-temple-restaurant-lounge-hung-vuong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-1-normal-2706734969988.webp",
                of("", ""), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-2-normal-2706735269989.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-3-normal-2706735169990.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-4-normal-2706735369991.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-5-normal-2706735469992.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-6-normal-2706735569993.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-7-normal-2706735669994.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-8-normal-2706735769995.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-9-normal-2706735869996.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-10-normal-2706735969997.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-11-normal-2706736069998.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-temple-restaurant-lounge-hoi-an-12-normal-2706736169999.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("EUROPEAN")
                .name(of("Âu", "European"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("FRENCH")
                .name(of("Pháp", "French"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("ITALIAN")
                .name(of("Ý", "Italian"))
                .region("EUROPE")
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
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("LUXURIOUS", of("Sang trọng", "Luxurious")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("ROMANTIC",  of("Lãng mạn", "Romantic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(70)
            .maxGroupSize(15)
            .hasPrivateRooms(false)      // res này không có "Phòng riêng"
            .privateRoomCount(null)
            .maxPrivateRoomCapacity(null)
            .hasOutdoorSeating(true)     // có "Bàn ngoài trời"
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Grilled Seafood Platter", "Grilled Seafood Platter")),
            signatureDishL(of("Yellowfin Tuna Steak", "Yellowfin Tuna Steak")),
            signatureDishL(of("Smoked Salmon Tartar", "Smoked Salmon Tartar")),
            signatureDishL(of("Crab with Jalapeno Sweet corn Soup", "Crab with Jalapeno Sweet corn Soup")),
            signatureDishL(of("Slow cooked Lamb Shank Pappadelle", "Slow cooked Lamb Shank Pappadelle")),
            signatureDishL(of("Grilled Beef tenderloin with Truffle Mashed Potato", "Grilled Beef tenderloin with Truffle Mashed Potato"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-19.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-20.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-21.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-22.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-23.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-24.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-25.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-26.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-27.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-28.jpg",
                of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-29.jpg",
                of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-30.jpg",
                of("", ""), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-31.jpg",
                of("", ""), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-32.jpg",
                of("", ""), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-33.jpg",
                of("", ""), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-34.jpg",
                of("", ""), false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-35.jpg",
                of("", ""), false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-36.jpg",
                of("", ""), false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-37.jpg",
                of("", ""), false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/1.%20The%20Temple%20Restaurant%20&%20Lounge%20-%20H%C3%B9ng%20V%C6%B0%C6%A1ng/menu-the-temple-restaurant-lounge-38.jpg",
                of("", ""), false, 37)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món chính", "Main course"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Grilled Beef tenderloin with Truffle Mashed Potato", "Grilled Beef tenderloin with Truffle Mashed Potato"))
                        .description(of("Thịt thăn bò nướng dùng kèm khoai tây nghiền truffle, rau củ và sốt đặc biệt.", "Grilled beef tenderloin served with truffle mashed potato, vegetables and a special sauce."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "steak", "european"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Grilled Seafood Platter", "Grilled Seafood Platter"))
                        .description(of("Tháp hải sản nướng tổng hợp với tôm, mực, cá, sò điệp...", "Assorted grilled seafood platter with shrimp, squid, fish, scallops..."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit(of("phần", "serving"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grill", "sharing"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe tại nhà hàng", "Parking at the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi đỗ xe tại nhà hàng", "Parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn, miễn phí cho khách.", "Security staff on hand, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        // Chỉ giữ các tiện ích đang "màu xanh" (có) bên PasGo
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "MC_SERVICE",
            "FREE_CAR_PARKING",
            "OUTDOOR_TABLE",
            "FREE_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            // ===== OVERVIEW – tóm tắt trên cao =====
            heading(ContentSection.OVERVIEW, of("Tóm tắt The Temple Restaurant & Lounge - Hùng Vương", "The Temple Restaurant & Lounge - Hung Vuong overview")),
            paragraph(ContentSection.OVERVIEW,
                of("The Temple Restaurant & Lounge là điểm hẹn ẩm thực món Âu giữa lòng phố cổ Hội An. " +
                "Thực đơn phong phú với các món ăn được chế biến từ nguyên liệu tuyển chọn, " +
                "phù hợp cho những bữa tiệc gia đình, họp mặt bạn bè hay tiếp khách đối tác trong không gian sang trọng, lãng mạn.",
                "The Temple Restaurant & Lounge is a European cuisine rendezvous in the heart of Hoi An ancient town. " +
                "Its rich menu features dishes prepared from carefully selected ingredients, " +
                "suitable for family parties, gatherings with friends or business meals in a luxurious, romantic setting.")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm tại tầng trệt La Siesta Hoi An Resort & Spa, chỉ cách phố cổ Hội An khoảng 10 phút đi bộ, " +
                "rất thuận tiện cho du khách kết hợp dùng bữa và tham quan.",
                "The restaurant is located on the ground floor of La Siesta Hoi An Resort & Spa, only about a 10-minute walk from Hoi An ancient town, " +
                "very convenient for visitors to combine dining and sightseeing.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{108.3175, 15.8795}),

            // ===== STORY – bài giới thiệu dài, có text + ảnh xen kẽ =====

            // 1) Heading + 2 đoạn mô tả ẩm thực
            heading(ContentSection.STORY, of("The Temple Restaurant & Lounge – Chạm vào tinh hoa ẩm thực châu Âu giữa lòng Hội An", "The Temple Restaurant & Lounge - Touch the essence of European cuisine in the heart of Hoi An")),
            paragraph(ContentSection.STORY,
                of("Đến với The Temple Restaurant & Lounge Hùng Vương, bạn sẽ được chiêu đãi thực đơn món Âu vô cùng hấp dẫn. " +
                "Thực đơn của nhà hàng là sự kết hợp hài hòa giữa hương vị Pháp tinh tế, sự mộc mạc của Ý và nét đặc trưng " +
                "của ẩm thực châu Âu, tất cả đều được chế biến từ những nguyên liệu tươi ngon nhất dưới bàn tay tài hoa " +
                "của đội ngũ bếp trưởng giàu kinh nghiệm. Dù là tháp hải sản tươi sống, sườn cừu nướng mềm thơm hay đĩa thịt " +
                "tổng hợp đậm đà, mỗi món ăn đều là một tác phẩm nghệ thuật, khơi gợi mọi giác quan.",
                "At The Temple Restaurant & Lounge Hung Vuong, you will be treated to an utterly enticing European menu. " +
                "The menu is a harmonious blend of refined French flavors, Italian rusticity and the distinctive character " +
                "of European cuisine, all prepared from the freshest ingredients under the skilled hands " +
                "of an experienced head chef team. Whether it is a fresh seafood platter, tender fragrant grilled lamb shank or a rich " +
                "mixed meat plate, each dish is a work of art that awakens every sense.")),
            paragraph(ContentSection.STORY,
                of("Các món ăn đặc sắc tại The Temple Restaurant & Lounge Hùng Vương nhất định nên thử như: " +
                "Grilled Seafood Platter, Yellowfin Tuna Steak, Smoked Salmon Tartar, " +
                "Crab with Jalapeno Sweet corn Soup, Slow cooked Lamb Shank Pappadelle...",
                "Signature dishes you should not miss at The Temple Restaurant & Lounge Hung Vuong include: " +
                "Grilled Seafood Platter, Yellowfin Tuna Steak, Smoked Salmon Tartar, " +
                "Crab with Jalapeno Sweet corn Soup, Slow cooked Lamb Shank Pappadelle...")),

            // 2) Ảnh không có title (caption để null hoặc chuỗi rỗng)
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-6.jpg",
                    (String) null,
                    false,
                    0
                )
            ),

            // 3) Heading + đoạn mô tả không gian
            heading(ContentSection.STORY, of("Không gian nhà hàng sang trọng theo lối kiến trúc châu Âu", "A luxurious restaurant space in European architectural style")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng The Temple Restaurant & Lounge ấn tượng với không gian hiện đại, sang trọng theo lối kiến trúc " +
                "của những căn biệt thự kiểu Âu. Thiết kế của nhà hàng pha trộn với những đường nét Á Đông tinh tế hứa hẹn " +
                "sẽ là một trong những điểm đến hấp dẫn tại thành phố Hội An dành cho những ai đam mê khám phá ẩm thực " +
                "các nước trên thế giới.",
                "The Temple Restaurant & Lounge impresses with a modern, luxurious space in the architectural style " +
                "of European villas. The restaurant's design, blended with refined Asian touches, promises " +
                "to be one of the appealing destinations in Hoi An city for those passionate about exploring the cuisines " +
                "of countries around the world.")),

            // 4) Ảnh có title: Không gian sang trọng, đậm chất châu Âu
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-2.jpg",
                    of("Không gian sang trọng, đậm chất châu Âu", "A luxurious, distinctly European space"),
                    false,
                    1
                )
            ),

            // 5) Đoạn mô tả 2 không gian + ảnh ngoài trời
            paragraph(ContentSection.STORY,
                of("The Temple Restaurant được chia thành 2 không gian. Nếu không gian ngoài trời sở hữu nét đẹp hiện đại " +
                "thì không gian bên trong lại lấy lòng thực khách theo một cách khác biệt qua chất liệu gỗ truyền thống " +
                "làm chủ đạo.",
                "The Temple Restaurant is divided into 2 spaces. While the outdoor area boasts a modern beauty, " +
                "the indoor space wins diners over in a different way through the use of traditional wood " +
                "as the main material.")),
            imageBlock(
                ContentSection.STORY,
                img(
                    "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-4.jpg",
                    of("Khu vực ngoài trời thoáng đãng", "Spacious outdoor area"),
                    false,
                    2
                )
            ),

            // 6) Đoạn về mức giá + lời mời xem thêm hình
            paragraph(ContentSection.STORY,
                of("Với mức giá 250.000 đến 650.000 vnđ/người, nhà hàng phù hợp để tổ chức các sự kiện đặc biệt, " +
                "đón tiếp các đoàn khách du lịch hay họp mặt gia đình...",
                "With a price range of 250,000 to 650,000 VND/person, the restaurant is suitable for hosting special events, " +
                "welcoming tour groups or family gatherings...")),
            paragraph(ContentSection.STORY,
                of("Mời bạn khám phá thêm không gian và món ăn của The Temple Restaurant & Lounge qua những bức hình dưới đây!",
                "Discover more of The Temple Restaurant & Lounge's space and dishes through the photos below!")),

            // 7) Một loạt ảnh cuối – gom thành GALLERY
            gallery(
                ContentSection.STORY,
                List.of(
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-1.jpg",
                        of("Quầy pha chế đồ uống tại nhà hàng", "The drinks bar at the restaurant"),
                        false,
                        3
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-3.jpg",
                        of("The Temple Restaurant Hùng Vương phù hợp cho buổi tiệc hẹn hò lãng mạn, tiếp đối tác...", "The Temple Restaurant Hung Vuong is suitable for romantic dates, business meetings..."),
                        false,
                        4
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-8.jpg",
                        of("Thưởng thức buffet sáng và các món Âu ngon trong khuôn viên ngoài trời của nhà hàng", "Enjoy breakfast buffet and delicious European dishes in the restaurant's outdoor area"),
                        false,
                        5
                    ),
                    img(
                        "https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/The%20Temple%20Restaurant%20&%20Lounge%20-H%C3%B9ng%20v%C6%B0%C6%A1ng/the-temple-restaurant-lounge-9.jpg",
                        of("Thực đơn món Âu đa dạng và phong phú", "A diverse and rich European menu"),
                        false,
                        6
                    )
                )
            ),

            // 8) Đoạn kết + phần địa chỉ
            paragraph(ContentSection.STORY,
                of("The Temple Restaurant & Lounge nằm ở vị trí thuận lợi, chỉ cách phố cổ Hội An 10 phút đi bộ. " +
                "Mời bạn ghé nhà hàng The Temple Restaurant Hùng Vương để trải nghiệm không gian sang cùng những món Âu ngon. " +
                "Đừng quên đặt bàn PasGo để được giữ chỗ miễn phí và nhận ưu đãi hấp dẫn nhé!",
                "The Temple Restaurant & Lounge enjoys a convenient location, only a 10-minute walk from Hoi An ancient town. " +
                "Visit The Temple Restaurant Hung Vuong to experience the luxurious space and delicious European dishes. " +
                "Do not forget to book a table on PasGo to reserve your spot for free and receive attractive offers!")),
            paragraph(ContentSection.STORY,
                of("Để đặt bàn PasGo, vui lòng tải App PasGo (phiên bản Android/IOS). " +
                "Hoặc gọi tổng đài 1900.6005 (1.000đ/phút) để nhận tư vấn đặt bàn nhanh nhất!",
                "To book a table on PasGo, please download the PasGo App (Android/IOS version). " +
                "Or call the hotline 1900.6005 (1,000 VND/minute) for the fastest booking support!")),
            heading(ContentSection.STORY, of("Địa chỉ nhà hàng The Temple Restaurant & Lounge", "The Temple Restaurant & Lounge address")),
            paragraph(ContentSection.STORY,
                of("Tầng trệt, La Siesta Hội An Resort & Spa, số 132, Đường Hùng Vương, Phường Thanh Hà, " +
                "Tp. Hội An, Tỉnh Quảng Nam.",
                "Ground floor, La Siesta Hoi An Resort & Spa, No. 132 Hung Vuong Street, Thanh Ha Ward, " +
                "Hoi An City, Quang Nam Province."))
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(6, 1,  of("Không áp dụng ưu đãi ngày 1/6", "Promotion not applicable on Jun 1")),
            blackoutGregorian(9, 1,  of("Không áp dụng ưu đãi ngày 1/9", "Promotion not applicable on Sep 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(10, 20,of("Không áp dụng ưu đãi ngày 20/10", "Promotion not applicable on Oct 20")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 25,of("Không áp dụng ưu đãi ngày 25/12", "Promotion not applicable on Dec 25")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
            // Các ngày âm lịch (Giỗ tổ, Trung thu, Tết Nguyên Đán) có thể bổ sung thêm rule LUNAR_DATE sau
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 400.000đ/chai 750ml.", "Wine corkage fee: 400,000 VND/750ml bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("800000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh, champagne: 800.000đ/chai 750ml.", "Spirits and champagne corkage fee: 800,000 VND/750ml bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(5)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 5 khách trở lên hoặc yêu cầu trang trí đặc biệt.", "Applies to bookings of 5 or more guests or requests for special decoration."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(20)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null) // Không quy định số khách tối thiểu
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu, không xuất hóa đơn trực tiếp.", "The restaurant issues VAT invoices on request; direct invoices are not issued."))
            .serviceChargePercent(null)
            .serviceChargeNotes(of("Không thu phí phục vụ riêng.", "No separate service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.", "Outside drinks are allowed, with a corkage fee depending on the type of drink."))
            .outsideDrinkFees(outsideDrinkFees)
            .build();

        // ---------- Review stats ----------
        ReviewStats reviewStats = ReviewStats.builder()
            .foodScore(4.8)
            .spaceScore(4.7)
            .serviceScore(4.7)
            .priceScore(4.2)
            .locationScore(4.6)
            .keywords(List.of(
                keyword("good_food", of("Món ăn ngon", "Delicious food"), 90),
                keyword("romantic_space", of("Không gian lãng mạn", "Romantic space"), 60),
                keyword("friendly_staff", of("Nhân viên thân thiện", "Friendly staff"), 55)
            ))
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
                .id("tb-2-romantic")
                .name(of("Bàn 2 (Romantic)", "Table for 2 (Romantic)"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(8)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi không gian lãng mạn.", "A table for two in a romantic setting."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(10)
                .depositPrice(new BigDecimal("250000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp nhóm bạn/gia đình.", "Suitable for groups of friends and families."))
                .build(),

            TableType.builder()
                .id("tb-4-outdoor")
                .name(of("Bàn 4 (Ngoài trời)", "Table for 4 (Outdoor)"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(6)
                .depositPrice(new BigDecimal("250000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Khu vực thoáng đãng, ưu tiên buổi tối.", "Spacious area, preferred for evenings."))
                .build(),

            TableType.builder()
                .id("tb-6-vip")
                .name(of("Bàn 6 VIP", "VIP Table for 6"))
                .seats(6)
                .minPeople(5)
                .maxPeople(6)
                .totalTables(2)
                .depositPrice(new BigDecimal("600000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Vị trí đẹp, phù hợp dịp đặc biệt.", "Great location, suitable for special occasions."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(90)      // res Âu thường cần chuẩn bị kỹ hơn
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(25)    // có thể chặt hơn chút
            .depositOnly(true)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("hoi-an")
            .parentPlaceSlug("la-siesta-hoi-an-resort-spa")
            .cityName(of("Hội An", "Hoi An"))
            .districtName(of("Tp. Hội An", "Hoi An City"))
            .wardName(of("P. Thanh Hà", "Thanh Ha Ward"))
            .addressLine(of("Tầng trệt, La Siesta Hội An Resort & Spa, Số 132 Hùng Vương, P. Thanh Hà, Tp. Hội An", "Ground floor, La Siesta Hoi An Resort & Spa, No. 132 Hung Vuong St., Thanh Ha Ward, Hoi An City"))
            .location(new double[]{108.3175, 15.8795})
            .name(of("The Temple Restaurant & Lounge - Hùng Vương", "The Temple Restaurant & Lounge - Hung Vuong"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng món Âu sang trọng trong khuôn viên La Siesta Hoi An Resort & Spa, phù hợp cho những buổi tiệc và dịp đặc biệt.", "A luxurious European restaurant within La Siesta Hoi An Resort & Spa, suitable for parties and special occasions."))
            .description(of("The Temple Restaurant & Lounge - Hùng Vương là nhà hàng chuyên ẩm thực Âu giữa lòng Hội An, " +
                "sở hữu không gian sang trọng, lãng mạn theo phong cách biệt thự châu Âu. " +
                "Thực đơn đa dạng với các món Âu được chế biến tinh tế, rất phù hợp cho những bữa ăn gia đình, " +
                "họp mặt bạn bè, tiệc công ty hay các dịp kỷ niệm đặc biệt.",
                "The Temple Restaurant & Lounge - Hung Vuong is a European cuisine restaurant in the heart of Hoi An, " +
                "featuring a luxurious, romantic space in the style of a European villa. " +
                "The diverse menu of finely prepared European dishes is well suited for family meals, " +
                "gatherings with friends, company parties or special anniversaries."))
            .phone("1900xxxxxx")
            .email("reservation@thetemplehoian.com")
            .website("https://example.com/the-temple-hoi-an")
            .facebookPage("https://facebook.com/thetemplehoian")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("650000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
            // Lấy khoảng giữa ~450k => map vào bucket 350k-500k
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
            .avgRating(4.6)
            .reviewsCount(95)
            .ratingLabel(of("Rất tốt", "Very good"))
            .reviewStats(reviewStats)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}