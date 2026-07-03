package com.mravel.catalog.bootstrap.restaurant.DN;

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
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;
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
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedSushiWorldPhanBoiChau {

    @Bean
    CommandLineRunner seedSushiWorldPhanBoiChauRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedSushiWorldPhanBoiChau] start");

            RestaurantDoc doc = restaurantSushiWorldPhanBoiChau();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedSushiWorldPhanBoiChau] done");
        };
    }

    // =====================================================================
    //        SUSHI WORLD - PHAN BOI CHAU (DA NANG)
    // =====================================================================

    private static RestaurantDoc restaurantSushiWorldPhanBoiChau() {
        String slug = "sushi-world-phan-boi-chau-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-1-normal-2218161554509.webp",
                of("Không gian Zen hiện đại Sushi World Phan Bội Châu Đà Nẵng", "Modern Zen interior of Sushi World Phan Boi Chau Da Nang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-2-normal-2218161654510.webp",
                of("Khu vực phòng ăn tầng 1 Sushi World", "Sushi World ground floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-3-normal-2218161754511.webp",
                of("Không gian tầng 2 Sushi World Đà Nẵng", "Sushi World Da Nang 2nd floor space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-4-normal-2218161854512.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-5-normal-2218161954513.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-6-normal-2218162054514.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-7-normal-2218162154515.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-8-normal-2218162254516.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-9-normal-2218162554517.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-10-normal-2218162654518.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-11-normal-2218162754519.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-sushi-world-phan-boi-chau-12-normal-2218162854520.webp",
                of("", ""), false, 11)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/19.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/20.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/21.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/22.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/23.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/24.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/25.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/26.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoisushiworld/27.jpg",
                of("", ""), false, 26)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Ẩm thực Nhật Bản", "Japanese cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SUSHI_SASHIMI")
                .name(of("Sushi & Sashimi", "Sushi & Sashimi"))
                .region("JAPAN")
                .build(),
            CuisineTag.builder()
                .code("JAPANESE_BBQ")
                .name(of("Nướng Nhật Bản", "Japanese BBQ"))
                .region("JAPAN")
                .build()
        );

        // ---------- Giờ mở cửa ----------
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
            suitable("FAMILY",         of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",        of("Gặp gỡ bạn bè", "Friends gathering")),
            suitable("COUPLE",         of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("COMPANY_PARTY",  of("Tiệc công ty", "Company party")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách doanh nghiệp", "Business meal")),
            suitable("GROUP_MEETING",  of("Họp nhóm", "Group meeting")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp", "Corporate event"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",     of("Hiện đại", "Modern")),
            ambience("COZY",       of("Ấm cúng theo phong cách Zen", "Cozy Zen style")),
            ambience("ELEGANT",    of("Thanh lịch", "Elegant")),
            ambience("VIBRANT",    of("Sôi động", "Vibrant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Sashimi cá hồi tươi nguyên miếng", "Fresh salmon sashimi")),
            signatureDishL(of("Sushi cuộn California roll đặc biệt", "Special California roll sushi")),
            signatureDishL(of("Sushi nigiri tôm hùm", "Lobster nigiri sushi")),
            signatureDishL(of("Nướng bò Wagyu Nhật kiểu Yakiniku", "Japanese Wagyu beef Yakiniku")),
            signatureDishL(of("Lẩu Shabu-Shabu bò Nhật", "Japanese beef Shabu-Shabu hotpot")),
            signatureDishL(of("Sashimi cá ngừ đại dương tươi", "Fresh tuna sashimi")),
            signatureDishL(of("Maki cuộn rong biển Nhật truyền thống", "Traditional Japanese seaweed maki roll")),
            signatureDishL(of("Đậu hũ chiên sốt Ponzu kiểu Nhật", "Japanese fried tofu with Ponzu sauce")),
            signatureDishL(of("Soup Miso truyền thống", "Traditional Miso soup")),
            signatureDishL(of("Nướng bạch tuộc kiểu Nhật Takoyaki", "Japanese Takoyaki octopus balls"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SUSHI_SASHIMI")
                .name(of("Sushi & Sashimi", "Sushi & Sashimi"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set Sashimi 5 loại cá", "5-fish Sashimi set"))
                        .description(of("Sashimi tổng hợp 5 loại cá tươi: cá hồi, cá ngừ, tôm, mực, bạch tuộc.", "Assorted sashimi of 5 fresh fish: salmon, tuna, shrimp, squid, octopus."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("sashimi", "premium", "fresh"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo Sushi 12 miếng", "Sushi combo 12 pieces"))
                        .description(of("Combo 12 miếng sushi đa dạng: nigiri, maki, temaki theo lựa chọn.", "12-piece sushi combo: nigiri, maki, temaki of your choice."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("280000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("sushi", "combo"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("YAKINIKU_HOTPOT")
                .name(of("Nướng & Lẩu Nhật", "Japanese BBQ & Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set Yakiniku bò Wagyu", "Wagyu beef Yakiniku set"))
                        .description(of("Thịt bò Wagyu Nhật Bản 200g nướng tại bàn, kèm rau và sốt đặc biệt.", "200g Japanese Wagyu beef grilled at table, with vegetables and special sauce."))
                        .priceFrom(new BigDecimal("380000"))
                        .priceTo(new BigDecimal("480000"))
                        .unit(of("set", "set"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("wagyu", "yakiniku", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Khu vực trên đường Phan Bội Châu và các tuyến đường lân cận", "On Phan Boi Chau Street and nearby roads"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng Sushi World", "In front of Sushi World restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe máy, ô tô giữ theo giờ tại đường Phan Bội Châu.", "Free motorbike parking; car parking charged by hour on Phan Boi Chau Street."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "EVENT_DECOR",
            "SOUND_LIGHT",
            "SET_LUNCH",
            "JAPANESE_TABLEWARE",
            "SAKE_MENU",
            "OUTDOOR_TABLE",
            "BIRTHDAY_DECORATION"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Sushi World - Phan Bội Châu, Đà Nẵng", "About Sushi World - Phan Boi Chau, Da Nang")),
            paragraph(ContentSection.STORY,
                of("Sushi World Phan Bội Châu là một trong những nhà hàng Nhật Bản được yêu mến nhất tại Đà Nẵng, " +
                   "tọa lạc tại số 10 Phan Bội Châu, quận Hải Châu. Với hơn 5 năm phục vụ thực khách, " +
                   "Sushi World đã xây dựng được danh tiếng vững chắc nhờ thực đơn phong phú, nguyên liệu tươi ngon " +
                   "và không gian thiết kế chuẩn Zen Nhật Bản hiện đại, là điểm đến lý tưởng cho những ai muốn " +
                   "trải nghiệm ẩm thực xứ sở hoa anh đào ngay tại trung tâm thành phố biển.",
                   "Sushi World Phan Boi Chau is one of the most beloved Japanese restaurants in Da Nang, " +
                   "located at 10 Phan Boi Chau Street, Hai Chau District. With over 5 years of serving guests, " +
                   "Sushi World has built a solid reputation for its rich menu, fresh ingredients, " +
                   "and a modern Japanese Zen-designed space, making it the ideal destination for those wanting to " +
                   "experience the cuisine of the Land of the Rising Sun in the heart of the coastal city.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Sushi World Phan Bội Châu sở hữu không gian rộng rãi 2 tầng với tổng sức chứa lên tới 200 khách. " +
                   "Toàn bộ nhà hàng được thiết kế theo phong cách Zen Nhật Bản hiện đại — đèn gỗ thả trần, " +
                   "vách ngăn tre nứa, tone màu trầm ấm tạo cảm giác sang trọng và thư thái. " +
                   "Nhà hàng có 2 phòng riêng tư sức chứa đến 30 khách, lý tưởng cho tiệc nhóm, " +
                   "sinh nhật và các buổi họp doanh nghiệp cần không gian riêng tư.",
                   "Sushi World Phan Boi Chau offers a spacious 2-floor space with a total capacity of up to 200 guests. " +
                   "The entire restaurant is designed in modern Japanese Zen style — hanging wooden lanterns, " +
                   "bamboo partition walls, and warm, deep tones creating a luxurious and tranquil atmosphere. " +
                   "The restaurant has 2 private rooms accommodating up to 30 guests, ideal for group parties, " +
                   "birthdays, and business meetings requiring privacy.")),
            heading(ContentSection.STORY, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Sushi World phong phú và đa dạng, bao phủ toàn diện ẩm thực Nhật Bản: " +
                   "từ Sushi, Sashimi (cá hồi, cá ngừ, tôm hùm) đến Yakiniku nướng tại bàn, Shabu-Shabu lẩu bò Nhật, " +
                   "Takoyaki bạch tuộc và soup Miso truyền thống. Nguyên liệu được chọn lọc kỹ càng và đầu bếp " +
                   "được đào tạo chuyên nghiệp tự tin mang đến bữa ăn chất lượng chuẩn Nhật. " +
                   "Thực đơn đa dạng theo tiệc, phù hợp với từng loại yêu cầu của thực khách.",
                   "Sushi World's menu is rich and diverse, covering all aspects of Japanese cuisine: " +
                   "from Sushi, Sashimi (salmon, tuna, lobster) to Yakiniku table-grilled BBQ, Japanese beef Shabu-Shabu hotpot, " +
                   "Takoyaki octopus balls, and traditional Miso soup. Ingredients are carefully selected and " +
                   "professionally trained chefs confidently deliver Japanese-quality meals. " +
                   "The menu adapts to various party formats, matching every diner's request.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Sushi World Phan Bội Châu phù hợp với mọi dịp từ bữa ăn gia đình, hẹn hò, sinh nhật đến tiệc công ty " +
                   "và đón tiếp đối tác. Phòng riêng tư có sức chứa linh hoạt đáp ứng tốt các nhóm 10-30 người. " +
                   "Set lunch phục vụ giờ trưa văn phòng, còn tối cuối tuần thường đông khách du lịch muốn " +
                   "thưởng thức ẩm thực Nhật xác thực trong không gian đẹp.",
                   "Sushi World Phan Boi Chau suits all occasions from family meals, dates, birthdays, to company parties " +
                   "and partner entertainment. Flexible-capacity private rooms accommodate groups of 10-30 people well. " +
                   "Set lunch serves the office noon crowd, while weekend evenings attract many tourists wanting " +
                   "authentic Japanese cuisine in a beautiful setting.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích nổi bật", "Services & highlights")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp dịch vụ đặt bàn ưu tiên và đặt tiệc trọn gói theo yêu cầu. " +
                   "Trang trí sinh nhật, sự kiện theo chủ đề Nhật Bản, dịch vụ âm thanh ánh sáng, " +
                   "WiFi miễn phí và ghế cao cho trẻ em. Thực đơn rượu sake và đồ uống Nhật đa dạng. " +
                   "Set lunch hợp lý cho bữa trưa văn phòng; combo dinner phong phú cho buổi tối.",
                   "The restaurant provides priority table booking and full-package catering on request. " +
                   "Japanese-themed birthday and event decoration, audio-visual service, " +
                   "free WiFi, and high chairs for children are available. " +
                   "A diverse sake and Japanese beverage menu is offered. " +
                   "Affordable set lunches for office noon; rich dinner combos for evenings.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Sushi World nằm tại số 10 Phan Bội Châu, quận Hải Châu — tuyến phố ẩm thực sầm uất của Đà Nẵng. " +
                   "Cách cầu Rồng khoảng 500m, cách bờ sông Hàn 200m và gần trục đường lớn 2 tháng 9. " +
                   "Di chuyển bằng taxi hoặc xe công nghệ (Grab) từ mọi điểm trong thành phố rất thuận tiện. " +
                   "Xe máy đỗ miễn phí ngay trước nhà hàng.",
                   "Sushi World is at 10 Phan Boi Chau Street, Hai Chau District — Da Nang's lively food street. " +
                   "About 500m from Dragon Bridge, 200m from the Han River bank, and near the main 2 Thang 9 road. " +
                   "Getting there by taxi or ride-hailing (Grab) from anywhere in the city is very convenient. " +
                   "Free motorbike parking directly in front of the restaurant.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt trước ít nhất 2 tiếng cho bàn thường và 4 tiếng cho phòng riêng. " +
                   "Đặt cọc áp dụng cho nhóm từ 10 khách trở lên hoặc đặt phòng riêng. " +
                   "Hủy trước 4 tiếng được hoàn cọc đầy đủ. Phụ thu VAT 10% theo quy định.",
                   "The restaurant accepts reservations at least 2 hours in advance for regular tables and 4 hours for private rooms. " +
                   "Deposits apply for groups of 10 or more or private room bookings. " +
                   "Cancellations more than 4 hours in advance receive a full refund. VAT 10% applies as regulated.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Sushi World Phan Bội Châu", "Why choose Sushi World Phan Boi Chau")),
            paragraph(ContentSection.STORY,
                of("Sushi World mang đến trải nghiệm ẩm thực Nhật Bản đích thực với đội ngũ đầu bếp chuyên nghiệp, " +
                   "nguyên liệu tươi ngon và không gian Zen đẹp mắt ngay tại trung tâm Đà Nẵng. " +
                   "Với sức chứa 200 khách (2 tầng), phòng riêng tư và thực đơn đa dạng từ Sushi, Sashimi đến nướng và lẩu Nhật, " +
                   "đây là điểm ẩm thực Nhật đáng tin cậy cho cả gia đình, nhóm bạn lẫn đoàn doanh nghiệp tại Đà Nẵng.",
                   "Sushi World delivers authentic Japanese culinary experiences with professional chefs, " +
                   "fresh ingredients, and a beautiful Zen interior right in central Da Nang. " +
                   "With a 200-guest capacity (2 floors), private rooms, and a diverse menu from Sushi, Sashimi to Japanese BBQ and hotpot, " +
                   "it is the go-to Japanese dining destination for families, friend groups, and corporate parties in Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.221380, 16.080924})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("SAKE")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí sake mang ngoài: 300.000đ/chai.", "Sake corkage fee: 300,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 10 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 10+ guests or private room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% cho khách đặt qua hệ thống, không áp dụng ngày lễ.", "10% discount for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi thay đổi theo thời điểm, liên hệ nhà hàng để biết chi tiết.", "Promotions vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho phòng riêng và tiệc nhóm.", "5% service charge for private rooms and group parties."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài, có phụ phí corkage.", "Outside drinks allowed with corkage fee."))
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-standard")
                .name(of("Bàn 4 khách tiêu chuẩn", "Standard table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn phù hợp gia đình và nhóm nhỏ.", "Standard table suitable for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-group")
                .name(of("Bàn nhóm 8 khách", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn phù hợp nhóm bạn và gia đình đông người.", "Large table for friend groups and big families."))
                .build(),
            TableType.builder()
                .id("pr-30-private")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng theo phong cách Nhật, đầy đủ âm thanh ánh sáng, phù hợp tiệc nhóm và sinh nhật.", "Japanese-style private room with full audio-visual, suitable for group parties and birthdays."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(120)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(240)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Hải Châu", "Hai Chau District"))
            .wardName(of("P. Hải Châu I", "Hai Chau I Ward"))
            .addressLine(of("Số 10 Phan Bội Châu, Q. Hải Châu, TP. Đà Nẵng",
                           "10 Phan Boi Chau Street, Hai Chau District, Da Nang"))
            .location(new double[]{108.221380, 16.080924})
            .name(of("Sushi World - Phan Bội Châu", "Sushi World - Phan Boi Chau"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng Nhật Bản 2 tầng sức chứa 200 khách, không gian Zen, Sushi-Sashimi-Yakiniku chuẩn vị tại Phan Bội Châu, Đà Nẵng.",
                                 "Japanese restaurant on 2 floors, 200-guest capacity, Zen interior, authentic Sushi-Sashimi-Yakiniku on Phan Boi Chau, Da Nang."))
            .description(of("Sushi World Phan Bội Châu — nhà hàng Nhật Bản hơn 5 năm hoạt động tại số 10 Phan Bội Châu, quận Hải Châu, Đà Nẵng. " +
                            "Không gian 2 tầng sức chứa 200 khách, thiết kế Zen Nhật hiện đại. Thực đơn đa dạng: Sushi, Sashimi, Yakiniku, Shabu-Shabu. " +
                            "Có phòng riêng, thực đơn sake, set lunch hợp lý.",
                            "Sushi World Phan Boi Chau — Japanese restaurant 5+ years at 10 Phan Boi Chau, Hai Chau, Da Nang. " +
                            "2-floor space, 200-guest capacity, modern Japanese Zen interior. Diverse menu: Sushi, Sashimi, Yakiniku, Shabu-Shabu. " +
                            "Private rooms, sake menu, affordable set lunch."))
            .phone("0961538460")
            .website("https://pasgo.vn/nha-hang/sushi-world-phan-boi-chau-3412")
            .bookingHotline("0961538460")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("500000"))
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
