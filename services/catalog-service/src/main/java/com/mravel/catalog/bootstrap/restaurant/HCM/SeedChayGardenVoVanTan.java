// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedChayGardenVoVanTan.java
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
public class SeedChayGardenVoVanTan {

    @Bean
    CommandLineRunner seedChayGardenVoVanTanRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedChayGardenVoVanTan] start");

            RestaurantDoc doc = restaurantChayGardenVoVanTan();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedChayGardenVoVanTan] done");
        };
    }

    // =====================================================================
    //              CHAY GARDEN - VÕ VĂN TẦN (Q.3, TP.HCM)
    // =====================================================================

    private static RestaurantDoc restaurantChayGardenVoVanTan() {
        String slug = "chay-garden-vo-van-tan";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-1-normal-2112667238577.webp",
                of("Không gian Chay Garden Võ Văn Tần", "Chay Garden Vo Van Tan space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-2-normal-2112667338578.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-3-normal-2112667438579.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-4-normal-2112667638580.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-5-normal-2112667738581.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-6-normal-2112667838582.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-7-normal-2112667938583.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-8-normal-2112668038584.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-9-normal-2112668138585.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-10-normal-2112668238586.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-11-normal-2112668538587.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-chay-garden-vo-van-tan-12-normal-2112668738588.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VEGETARIAN")
                .name(of("Món chay", "Vegetarian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 10:00 - 22:00) ----------
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
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("VEGETARIAN_DAY",of("Ăn chay ngày rằm", "Full-moon vegetarian day"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",   of("Mộc mạc hoài cổ", "Rustic and nostalgic")),
            ambience("ELEGANT",  of("Trang nhã", "Elegant")),
            ambience("ROMANTIC", of("Yên bình thư thái", "Peaceful and relaxing"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu nấm thiên nhiên", "Natural mushroom hotpot")),
            signatureDishL(of("Gỏi cuốn chay", "Vegetarian fresh spring rolls")),
            signatureDishL(of("Đậu hũ non sốt nấm", "Soft tofu with mushroom sauce")),
            signatureDishL(of("Cơm sen hạt sen", "Lotus seed rice")),
            signatureDishL(of("Nấm đùi gà nướng muối ớt", "Salt and chili grilled king oyster mushroom")),
            signatureDishL(of("Rau củ kho tộ", "Braised vegetables in clay pot")),
            signatureDishL(of("Canh chua chay", "Vegetarian sour soup")),
            signatureDishL(of("Chả giò chay", "Vegetarian fried spring rolls")),
            signatureDishL(of("Mì xào rau nấm", "Stir-fried noodles with vegetables and mushrooms")),
            signatureDishL(of("Chè hạt sen long nhãn", "Lotus seed and longan sweet soup"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/1.jpg", of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/2.jpg", of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/3.jpg", of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/4.jpg", of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/5.jpg", of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/6.jpg", of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/7.jpg", of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/8.jpg", of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/9.jpg", of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/10.jpg", of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/11.jpg", of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/12.jpg", of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/13.jpg", of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/14.jpg", of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/15.jpg", of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/16.jpg", of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/17.jpg", of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/18.jpg", of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/19.jpg", of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/20.jpg", of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/21.jpg", of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/22.jpg", of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/23.jpg", of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/24.jpg", of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/25.jpg", of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/26.jpg", of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/27.jpg", of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/28.jpg", of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/29.jpg", of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/30.jpg", of("", ""), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/31.jpg", of("", ""), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/32.jpg", of("", ""), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/33.jpg", of("", ""), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/34.jpg", of("", ""), false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/35.jpg", of("", ""), false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/36.jpg", of("", ""), false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/37.jpg", of("", ""), false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/38.jpg", of("", ""), false, 37),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/39.jpg", of("", ""), false, 38),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/40.jpg", of("", ""), false, 39),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/41.jpg", of("", ""), false, 40),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/htchaygarden/42.jpg", of("", ""), false, 41)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu chay", "Vegetarian hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu nấm thiên nhiên", "Natural mushroom hotpot"))
                        .description(of("Lẩu nấm thanh ngọt với nhiều loại nấm tươi và rau củ theo mùa.", "Sweet and clear mushroom hotpot with assorted fresh mushrooms and seasonal vegetables."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("390000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "mushroom", "vegetarian"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu Thái chay", "Vegetarian Thai hotpot"))
                        .description(of("Lẩu Thái chua cay theo phong vị Đông Nam Á, dùng kèm rau nấm.", "Spicy and sour Thai-style hotpot in Southeast Asian flavor, served with vegetables and mushrooms."))
                        .priceFrom(new BigDecimal("280000"))
                        .priceTo(new BigDecimal("420000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "thai", "vegetarian"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món gọi", "A la carte"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Đậu hũ non sốt nấm", "Soft tofu with mushroom sauce"))
                        .description(of("Đậu hũ non mềm mịn rưới sốt nấm đậm đà.", "Silky soft tofu topped with a rich mushroom sauce."))
                        .priceFrom(new BigDecimal("75000"))
                        .priceTo(new BigDecimal("110000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("tofu", "vegetarian"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Gỏi cuốn chay", "Vegetarian fresh spring rolls"))
                        .description(of("Gỏi cuốn rau củ tươi mát, dùng kèm tương chấm đặc trưng.", "Fresh vegetable spring rolls served with a signature dipping sauce."))
                        .priceFrom(new BigDecimal("60000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("spring-roll", "fresh", "vegetarian"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng", "Car park near the restaurant"))
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
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "MC_SERVICE",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "GARDEN_VIEW"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Chay Garden Võ Văn Tần", "About Chay Garden Vo Van Tan")),
            paragraph(ContentSection.OVERVIEW,
                of("Chay Garden Võ Văn Tần là nhà hàng chay tọa lạc tại số 52 Võ Văn Tần, phường Võ Thị Sáu, quận 3, TP. Hồ Chí Minh. " +
                "Nép mình trong một con hẻm thoáng đãng và yên tĩnh giữa lòng quận 3, nhà hàng mang triết lý ăn chay vì sức khỏe và thực dưỡng, " +
                "phù hợp cho cả thực khách ăn chay trường lẫn những ai muốn đổi vị bằng bữa ăn thanh đạm.",
                "Chay Garden Vo Van Tan is a vegetarian restaurant located at 52 Vo Van Tan Street, Vo Thi Sau Ward, District 3, Ho Chi Minh City. " +
                "Tucked away in an airy and quiet alley in the heart of District 3, the restaurant embraces a philosophy of eating vegetarian for health and macrobiotics, " +
                "suitable both for lifelong vegetarians and for anyone wishing to enjoy a light, wholesome meal.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Trên quy mô khoảng 600m2, Chay Garden đặc trưng với kiến trúc sân vườn nhẹ nhàng, không gian bên trong mang đậm dấu ấn hoài cổ. " +
                "Nhà hàng bố trí nhiều khu vực từ bàn trong nhà, bàn sân vườn ngoài trời đến các phòng riêng ấm cúng, " +
                "có thể phục vụ khoảng 250 khách cùng lúc và nhận các nhóm lớn đến 40 người.",
                "Spanning about 600 square meters, Chay Garden features gentle garden architecture with an interior that carries a distinctly nostalgic charm. " +
                "The restaurant offers many areas, from indoor tables and outdoor garden seating to cozy private rooms, " +
                "and can serve around 250 guests at once while accommodating large groups of up to 40 people.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn Chay Garden phong phú với các món gọi chay theo phong vị Việt Nam và Đông Nam Á, từ lẩu nấm thiên nhiên, đậu hũ non sốt nấm, " +
                "gỏi cuốn chay, rau củ kho tộ đến các món tráng miệng thanh mát. Nguyên liệu được lựa chọn kỹ, ưu tiên rau củ tươi và nấm tự nhiên.",
                "Chay Garden's menu is rich with vegetarian a la carte dishes in Vietnamese and Southeast Asian flavors, from natural mushroom hotpot, soft tofu with mushroom sauce, " +
                "fresh spring rolls and braised vegetables in clay pot to refreshing desserts. Ingredients are carefully selected, favoring fresh vegetables and natural mushrooms.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian xanh mát và yên tĩnh giúp Chay Garden trở thành lựa chọn lý tưởng cho bữa ăn gia đình, tụ họp bạn bè, tiếp khách nhẹ nhàng, " +
                "hẹn hò hay những ngày rằm, mùng một khi nhu cầu ăn chay tăng cao.",
                "Its green and tranquil setting makes Chay Garden an ideal choice for family meals, gatherings with friends, relaxed business meals, " +
                "dates, and the full-moon and first-of-the-month days when demand for vegetarian dining rises.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng có phòng riêng, hệ thống âm thanh - máy chiếu phục vụ tiệc và sự kiện, hỗ trợ trang trí, ghế trẻ em, máy lạnh, wifi và chỗ để xe miễn phí. " +
                "Đội ngũ nhân viên thân thiện, sẵn sàng tư vấn set menu cho các đoàn khách và sự kiện công ty.",
                "The restaurant provides private rooms, a sound system and projector for parties and events, decoration support, kids' chairs, air conditioning, wifi and free parking. " +
                "The friendly staff are ready to advise on set menus for tour groups and corporate events.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nằm ngay trung tâm quận 3, Chay Garden thuận tiện di chuyển từ khu vực Hồ Con Rùa, Nhà thờ Đức Bà và các tuyến đường lớn của TP. Hồ Chí Minh, " +
                "rất dễ kết hợp trong hành trình tham quan, mua sắm và ẩm thực tại trung tâm thành phố.",
                "Located right in the center of District 3, Chay Garden is convenient to reach from the Turtle Lake area, Notre-Dame Cathedral and major roads of Ho Chi Minh City, " +
                "making it easy to combine with a sightseeing, shopping and dining itinerary downtown.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6905, 10.7775})
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
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc yêu cầu phòng riêng/trang trí đặc biệt.", "Applies to bookings of 6 or more guests or requests for private rooms / special decoration."))
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho phòng riêng và tiệc.", "A 5% service charge applies to private rooms and parties."))
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
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8-garden")
                .name(of("Bàn sân vườn 8 khách", "Garden table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn ngoài sân vườn, không gian xanh mát.", "Outdoor garden table with a green, airy setting."))
                .build(),

            TableType.builder()
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(12)
                .maxPeople(40)
                .totalTables(4)
                .depositPrice(new BigDecimal("700000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng yên tĩnh, phù hợp tiệc và tiếp khách.", "Quiet private room, suitable for parties and business meals."))
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
            .wardName(of("P. Võ Thị Sáu", "Vo Thi Sau Ward"))
            .addressLine(of("Số 52 Võ Văn Tần, P. Võ Thị Sáu, Q. 3, TP. Hồ Chí Minh", "No. 52 Vo Van Tan St., Vo Thi Sau Ward, District 3, Ho Chi Minh City"))
            .location(new double[]{106.6905, 10.7775})
            .name(of("Chay Garden Võ Văn Tần", "Chay Garden Vo Van Tan"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng chay gọi món phong cách sân vườn tại Q.3, TP. Hồ Chí Minh.", "Garden-style vegetarian a la carte restaurant in District 3, Ho Chi Minh City."))
            .description(of("Chay Garden Võ Văn Tần là nhà hàng chay gọi món tại số 52 Võ Văn Tần, Q.3, TP. Hồ Chí Minh. " +
                "Trên quy mô khoảng 600m2 với kiến trúc sân vườn hoài cổ, nhà hàng phục vụ các món chay Việt - Đông Nam Á " +
                "thanh đạm, tốt cho sức khỏe, phù hợp cho bữa ăn gia đình, tụ họp bạn bè và các dịp đặc biệt.",
                "Chay Garden Vo Van Tan is a vegetarian a la carte restaurant at 52 Vo Van Tan Street, District 3, Ho Chi Minh City. " +
                "Spanning about 600 square meters with nostalgic garden architecture, the restaurant serves light, healthy Vietnamese - Southeast Asian vegetarian dishes, " +
                "ideal for family meals, gatherings with friends and special occasions."))
            .phone("(+84) 0931 006 005")
            .email("reservation@chaygarden.vn")
            .website("https://pasgo.vn/nha-hang/chay-garden-vo-van-tan")
            .facebookPage("https://facebook.com/chaygarden")
            .bookingHotline("(+84) 0931 006 005")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("400000"))
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
