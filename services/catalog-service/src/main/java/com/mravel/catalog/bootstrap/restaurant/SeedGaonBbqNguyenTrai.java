// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedGaonBbqNguyenTrai.java
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
public class SeedGaonBbqNguyenTrai {

    @Bean
    CommandLineRunner seedGaonBbqNguyenTraiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGaonBbqNguyenTrai] start");

            RestaurantDoc gaon = restaurantGaonBbqNguyenTrai();
            repo.findBySlugAndActiveTrue(gaon.getSlug())
                .ifPresent(existing -> gaon.setId(existing.getId()));
            repo.save(gaon);
            log.info(">>> upsert: {}", gaon.getSlug());

            log.info(">>> [SeedGaonBbqNguyenTrai] done");
        };
    }

    // =====================================================================
    //          GAON BBQ NGUYỄN TRÃI - THỊT NƯỚNG HÀN QUỐC (Q1, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantGaonBbqNguyenTrai() {
        String slug = "gaon-bbq-nguyen-trai";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-1-normal-4241368473552.webp",
                of("Không gian GAON BBQ Nguyễn Trãi", "GAON BBQ Nguyen Trai space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-2-normal-4241368573553.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-3-normal-4241368673554.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-4-normal-4241368773555.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-5-normal-4241368873556.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-6-normal-4241368973557.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-7-normal-4241369073558.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-8-normal-4241369173559.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-9-normal-4241369273560.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-10-normal-4241369373561.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-11-normal-4241369873562.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-12-normal-4241370173563.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-13-normal-4241370273564.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-14-normal-4241370373565.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-15-normal-4241370473566.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-16-normal-4241370573567.webp",
                of("", ""), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-17-normal-4241370673568.webp",
                of("", ""), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gaon-bbq-18-normal-4241370773569.webp",
                of("", ""), false, 17)
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
                .name(of("Thịt nướng", "Barbecue"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:30 - 23:59, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 30, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   11, 30, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 11, 30, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  11, 30, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    11, 30, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  11, 30, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    11, 30, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
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
            ambience("ELEGANT", of("Tinh tế", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(160)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket")),
            signatureDishL(of("Sườn bò non nướng (Galbi)", "Grilled beef short ribs (Galbi)")),
            signatureDishL(of("Ba chỉ heo nướng Samgyeopsal", "Grilled pork belly Samgyeopsal")),
            signatureDishL(of("Bò Wagyu nướng than", "Charcoal-grilled Wagyu beef")),
            signatureDishL(of("Lưỡi bò nướng", "Grilled beef tongue")),
            signatureDishL(of("Canh kim chi Hàn Quốc", "Korean kimchi stew")),
            signatureDishL(of("Lẩu quân đội Budae Jjigae", "Army stew Budae Jjigae")),
            signatureDishL(of("Cơm trộn Bibimbap", "Bibimbap mixed rice")),
            signatureDishL(of("Mì lạnh Hàn Quốc", "Korean cold noodles")),
            signatureDishL(of("Bánh xèo hải sản Hàn Quốc", "Korean seafood pancake"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-0.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-1.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-2.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-3.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-4.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-5.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-6.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-7.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-8.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-9.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-10.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-11.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-12.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-13.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-14.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-15.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-16.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-17.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-18.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-19.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-20.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-21.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-22.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-23.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-24.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/21.%20GAON%20BBQ/menu-gaon-25.jpg",
                of("", ""), false, 25)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BBQ")
                .name(of("Thịt nướng", "Barbecue"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ba chỉ bò Mỹ nướng", "Grilled US beef brisket"))
                        .description(of("Ba chỉ bò Mỹ thái lát nướng than tại bàn, ăn kèm rau cuốn và nước sốt Hàn Quốc.", "Sliced US beef brisket grilled over charcoal at the table, served with wraps and Korean sauce."))
                        .priceFrom(new BigDecimal("189000"))
                        .priceTo(new BigDecimal("189000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "beef", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Ba chỉ heo nướng Samgyeopsal", "Grilled pork belly Samgyeopsal"))
                        .description(of("Ba chỉ heo nướng kiểu Hàn, ăn kèm kim chi, tỏi nướng và lá mè tươi.", "Korean-style grilled pork belly served with kimchi, grilled garlic and fresh perilla leaves."))
                        .priceFrom(new BigDecimal("159000"))
                        .priceTo(new BigDecimal("159000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "pork", "korean"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Nguyễn Trãi và bãi lân cận", "Car parking on Nguyen Trai street and nearby lots"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ trông xe máy miễn phí; ô tô gửi bãi lân cận có phí.", "Free motorbike valet assistance; cars use nearby paid parking lots."))
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
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "PROJECTOR",
            "GRILL_AT_TABLE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu GAON BBQ Nguyễn Trãi", "About GAON BBQ Nguyen Trai")),
            paragraph(ContentSection.OVERVIEW,
                of("GAON BBQ Nguyễn Trãi là nhà hàng thịt nướng Hàn Quốc chuẩn vị tọa lạc ngay trung tâm Quận 1, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ theo hình thức gọi món với thực đơn phong phú các loại thịt bò, heo nướng cùng món ăn Hàn truyền thống, " +
                "mang đến trải nghiệm ẩm thực xứ Hàn đậm đà ngay tại Sài Gòn.",
                "GAON BBQ Nguyen Trai is an authentic Korean barbecue restaurant located right in the heart of District 1, Ho Chi Minh City. " +
                "Served a la carte with a rich menu of grilled beef, pork and traditional Korean dishes, " +
                "it brings a deep, genuine Korean dining experience to Saigon.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế hiện đại, ấm cúng theo phong cách Hàn Quốc với sức chứa khoảng 160 khách. " +
                "GAON BBQ có phòng riêng kín đáo, phù hợp cho các nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant features a modern, cozy Korean-style design with a capacity of around 160 guests. " +
                "GAON BBQ offers private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của GAON là các loại thịt bò Mỹ, sườn non Galbi, ba chỉ heo Samgyeopsal và bò Wagyu nướng than tại bàn. " +
                "Bên cạnh thịt nướng, thực khách còn được thưởng thức canh kim chi, lẩu quân đội Budae Jjigae, cơm trộn Bibimbap và mì lạnh Hàn Quốc.",
                "GAON's highlights are US beef, Galbi short ribs, Samgyeopsal pork belly and Wagyu beef grilled over charcoal at the table. " +
                "Beyond the barbecue, diners can enjoy kimchi stew, Budae Jjigae army stew, Bibimbap and Korean cold noodles.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí sôi động và phục vụ chu đáo, GAON BBQ Nguyễn Trãi phù hợp cho tụ tập bạn bè, bữa ăn gia đình, " +
                "liên hoan văn phòng, sinh nhật, hẹn hò hay các buổi tiếp khách.",
                "With a vibrant atmosphere and attentive service, GAON BBQ Nguyen Trai is ideal for friends gatherings, family meals, " +
                "office parties, birthdays, dates or business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng, máy chiếu và bếp nướng ngay tại bàn. " +
                "Hỗ trợ trang trí, MC cho tiệc và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems, a projector and table-top grills. " +
                "Decoration / MC support for events is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 206 Bis Nguyễn Trãi, P. Bến Thành, Quận 1, ngay khu trung tâm sầm uất gần chợ Bến Thành và phố đi bộ, " +
                "thuận tiện di chuyển bằng ô tô, xe máy hoặc các tuyến giao thông công cộng.",
                "The restaurant is located at 206 Bis Nguyen Trai, Ben Thanh Ward, District 1, in the bustling city center near Ben Thanh Market and the walking street, " +
                "easily reached by car, motorbike or public transport.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.692000, 10.769800})
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
            .depositAmount(new BigDecimal("500000"))
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
            .serviceChargeNotes(of("Có thể áp dụng phí phục vụ 5% cho nhóm đông hoặc phòng riêng.", "A 5% service charge may apply for large groups or private rooms."))
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
                .depositPrice(new BigDecimal("150000"))
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
                .depositPrice(new BigDecimal("300000"))
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
                .minPeople(15)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
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
            .wardName(of("P. Bến Thành", "Ben Thanh Ward"))
            .addressLine(of("206 Bis Nguyễn Trãi, P. Bến Thành, Quận 1, TP. Hồ Chí Minh", "206 Bis Nguyen Trai, Ben Thanh Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.692000, 10.769800})
            .name(of("GAON BBQ Nguyễn Trãi", "GAON BBQ Nguyen Trai"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng thịt nướng Hàn Quốc chuẩn vị giữa trung tâm Quận 1, TP. Hồ Chí Minh.", "Authentic Korean barbecue restaurant in the heart of District 1, Ho Chi Minh City."))
            .description(of("GAON BBQ Nguyễn Trãi là nhà hàng thịt nướng Hàn Quốc phục vụ gọi món tại 206 Bis Nguyễn Trãi, Quận 1, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến thực đơn phong phú thịt bò Mỹ, sườn non Galbi, ba chỉ heo Samgyeopsal cùng các món Hàn truyền thống, " +
                "không gian hiện đại ấm cúng, phù hợp cho nhiều dịp từ tụ tập bạn bè đến liên hoan công ty.",
                "GAON BBQ Nguyen Trai is an a la carte Korean barbecue restaurant at 206 Bis Nguyen Trai, District 1, Ho Chi Minh City. " +
                "It offers a rich menu of US beef, Galbi short ribs, Samgyeopsal pork belly and traditional Korean dishes in a modern, cozy space, " +
                "suitable for friends gatherings and company parties."))
            .phone("1900xxxxxx")
            .email("reservation@gaonbbq.vn")
            .website("https://pasgo.vn/nha-hang/gaon-bbq-nguyen-trai")
            .facebookPage("https://facebook.com/gaonbbq")
            .bookingHotline("1900xxxxxx")
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
