// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedBuffetKingBbqVincomThuDuc.java
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
public class SeedBuffetKingBbqVincomThuDuc {

    @Bean
    CommandLineRunner seedBuffetKingBbqVincomThuDucRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBuffetKingBbqVincomThuDuc] start");

            RestaurantDoc doc = restaurantBuffetKingBbqVincomThuDuc();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedBuffetKingBbqVincomThuDuc] done");
        };
    }

    // =====================================================================
    //            BUFFET KING BBQ - VINCOM THỦ ĐỨC (HCM)
    // =====================================================================

    private static RestaurantDoc restaurantBuffetKingBbqVincomThuDuc() {
        String slug = "buffet-king-bbq-vincom-thu-duc";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-1-normal-369808527618.webp",
                of("Không gian Buffet King BBQ Vincom Thủ Đức", "Buffet King BBQ Vincom Thu Duc space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-2-normal-369809527619.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-3-normal-369810427620.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-4-normal-369810627621.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-5-normal-369810727622.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-6-normal-369810927623.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-7-normal-369811227624.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-8-normal-369811927625.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-9-normal-369812927626.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-10-normal-369814227627.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-11-normal-369814327628.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/king-bbq-buffet-vincom-thu-duc-12-normal-369814627629.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN")
                .name(of("Hàn Quốc", "Korean"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet nướng", "BBQ Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
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
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket")),
            signatureDishL(of("Sườn bò non nướng BBQ", "Grilled BBQ beef short ribs")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Hàn", "Korean-style grilled pork belly")),
            signatureDishL(of("Bò Kobe áp chảo", "Pan-seared Kobe beef")),
            signatureDishL(of("Lưỡi bò nướng", "Grilled beef tongue")),
            signatureDishL(of("Tôm sú nướng", "Grilled tiger prawns")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Lẩu kim chi Hàn Quốc", "Korean kimchi hotpot")),
            signatureDishL(of("Cơm trộn Bibimbap", "Bibimbap mixed rice")),
            signatureDishL(of("Tokbokki cay Hàn Quốc", "Korean spicy tteokbokki"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-9.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-10.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-11.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-12.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-13.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-14.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-15.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-16.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-17.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/kingbbqvincomthuduc/king-bbq-buffet-vincom-thu-duc-18.jpg",
                of("", ""), false, 16)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet nướng & lẩu", "BBQ & hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet King BBQ tiêu chuẩn", "King BBQ Standard Buffet"))
                        .description(of("Buffet nướng lẩu kiểu Hàn Quốc: thịt bò, heo, hải sản, kim chi, banchan và món tráng miệng không giới hạn.", "Korean-style BBQ and hotpot buffet: beef, pork, seafood, kimchi, banchan and unlimited desserts."))
                        .priceFrom(new BigDecimal("369000"))
                        .priceTo(new BigDecimal("369000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet King BBQ cao cấp", "King BBQ Premium Buffet"))
                        .description(of("Buffet cao cấp bổ sung bò Kobe, sườn non, hải sản tươi và các món nướng đặc biệt.", "Premium buffet adding Kobe beef, short ribs, fresh seafood and special grilled dishes."))
                        .priceFrom(new BigDecimal("459000"))
                        .priceTo(new BigDecimal("459000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm gửi xe ô tô của TTTM Vincom Thủ Đức", "Car parking at Vincom Thu Duc mall basement"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe máy của TTTM Vincom Thủ Đức", "Motorbike parking at Vincom Thu Duc mall basement"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Gửi xe tại hầm trung tâm thương mại theo biểu phí của Vincom.", "Parking is at the mall basement following Vincom's fee schedule."))
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
            "CAR_PARKING",
            "MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "BUFFET_SERVICE",
            "VISA_MASTER",
            "VAT_INVOICE",
            "ELEVATOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Buffet King BBQ Vincom Thủ Đức", "About Buffet King BBQ Vincom Thu Duc")),
            paragraph(ContentSection.OVERVIEW,
                of("Buffet King BBQ Vincom Thủ Đức là nhà hàng buffet nướng lẩu phong cách Hàn Quốc nằm trong Trung tâm thương mại Vincom Thủ Đức, TP. Hồ Chí Minh. " +
                "Thuộc thương hiệu King BBQ nổi tiếng, nhà hàng mang đến trải nghiệm nướng tại bàn cùng thực đơn phong phú với thịt bò, heo, hải sản và các món ăn kèm chuẩn vị Hàn.",
                "Buffet King BBQ Vincom Thu Duc is a Korean-style BBQ and hotpot buffet restaurant located inside Vincom Thu Duc Shopping Mall, Ho Chi Minh City. " +
                "Part of the well-known King BBQ brand, it offers a table-grill experience with a rich menu of beef, pork, seafood and authentic Korean side dishes.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng rộng rãi, hiện đại với phong cách Hàn Quốc trẻ trung, sức chứa lên đến khoảng 200 khách. " +
                "Nhà hàng có thêm phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a spacious, modern space with a youthful Korean style and a capacity of around 200 guests. " +
                "It also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của King BBQ là buffet nướng tại bàn với hơn trăm món: ba chỉ bò Mỹ, sườn bò non, ba chỉ heo, bò Kobe, hải sản tươi cùng các món lẩu kim chi, banchan và tráng miệng không giới hạn. " +
                "Nước sốt ướp đậm đà chuẩn vị Hàn Quốc là điều khiến thực khách quay lại.",
                "King BBQ's highlight is its table-grill buffet with over a hundred items: US beef brisket, beef short ribs, pork belly, Kobe beef and fresh seafood, alongside kimchi hotpot, banchan and unlimited desserts. " +
                "The rich, authentic Korean marinades keep diners coming back.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và phục vụ chu đáo, Buffet King BBQ Vincom Thủ Đức phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách.",
                "With a vibrant atmosphere and attentive service, Buffet King BBQ Vincom Thu Duc is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Nằm trong trung tâm thương mại nên có thang máy, hầm gửi xe và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and decoration / MC support for events. " +
                "Located inside a shopping mall, it has elevators, a parking basement and issues VAT invoices on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Gian hàng 10-10A, Tầng 3, TTTM Vincom Thủ Đức, 216 Võ Văn Ngân, P. Bình Thọ, Q. Thủ Đức, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và khu vực Thủ Đức lân cận.",
                "The restaurant is located at Unit 10-10A, 3rd Floor, Vincom Thu Duc Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District, " +
                "convenient to reach from central Ho Chi Minh City and the surrounding Thu Duc area.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.765243, 10.850347})
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
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
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
            .districtName(of("Q. Thủ Đức", "Thu Duc District"))
            .wardName(of("P. Bình Thọ", "Binh Tho Ward"))
            .addressLine(of("Gian hàng 10-10A, Tầng 3, TTTM Vincom Thủ Đức, 216 Võ Văn Ngân, P. Bình Thọ, Q. Thủ Đức, TP. Hồ Chí Minh", "Unit 10-10A, 3rd Floor, Vincom Thu Duc Mall, 216 Vo Van Ngan, Binh Tho Ward, Thu Duc District, Ho Chi Minh City"))
            .location(new double[]{106.765243, 10.850347})
            .name(of("Buffet King BBQ Vincom Thủ Đức", "Buffet King BBQ Vincom Thu Duc"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng lẩu kiểu Hàn Quốc King BBQ tại Vincom Thủ Đức, TP. Hồ Chí Minh.", "Korean-style King BBQ buffet at Vincom Thu Duc, Ho Chi Minh City."))
            .description(of("Buffet King BBQ Vincom Thủ Đức là nhà hàng buffet nướng lẩu phong cách Hàn Quốc thuộc thương hiệu King BBQ, " +
                "tọa lạc tại Tầng 3 TTTM Vincom Thủ Đức, 216 Võ Văn Ngân, Q. Thủ Đức, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trải nghiệm nướng tại bàn với thực đơn phong phú gồm thịt bò, heo, hải sản và các món ăn kèm chuẩn vị Hàn, " +
                "không gian rộng rãi hiện đại, phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Buffet King BBQ Vincom Thu Duc is a Korean-style BBQ and hotpot buffet restaurant of the King BBQ brand, " +
                "located on the 3rd floor of Vincom Thu Duc Mall, 216 Vo Van Ngan, Thu Duc District, Ho Chi Minh City. " +
                "It offers a table-grill experience with a rich menu of beef, pork, seafood and authentic Korean side dishes in a spacious, modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("1900xxxxxx")
            .email("reservation@kingbbq.vn")
            .website("https://pasgo.vn/nha-hang/buffet-king-bbq-vincom-thu-duc")
            .facebookPage("https://facebook.com/kingbbq.vn")
            .bookingHotline("1900xxxxxx")
            .minPricePerPerson(new BigDecimal("369000"))
            .maxPricePerPerson(new BigDecimal("459000"))
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
