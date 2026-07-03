package com.mravel.catalog.bootstrap.restaurant.HN;

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
public class SeedNhangNuongDaiCoViet {

    @Bean
    CommandLineRunner seedNhangNuongDaiCoVietRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNhangNuongDaiCoViet] start");

            RestaurantDoc doc = restaurantNhangNuongDaiCoViet();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(e -> doc.setId(e.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedNhangNuongDaiCoViet] done");
        };
    }

    // =====================================================================
    //        NHÀ HÀNG NHẮNG NƯỚNG - ĐẠI CỒ VIỆT (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantNhangNuongDaiCoViet() {
        String slug = "nhang-nuong-dai-co-viet";

        // ---------- Gallery ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-1-normal-1383835644617.webp",
                of("Không gian Nhắng Nướng - Đại Cồ Việt", "Nhang Nuong - Dai Co Viet restaurant space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-2-normal-1383836344618.webp",
                of("Khu vực nướng tại bàn", "Table-grilling area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-3-normal-1383836444619.webp",
                of("Không gian trong nhà", "Indoor dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-4-normal-1383836544620.webp",
                of("Bàn ăn nhóm bạn bè", "Group dining table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-5-normal-1383836644621.webp",
                of("Khu vực lẩu nướng", "Hotpot and BBQ area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-6-normal-1374922544622.webp",
                of("Không gian tầng 1", "First floor space"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-7-normal-1374922744623.webp",
                of("Không gian tầng 2", "Second floor space"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-8-normal-1383837144624.webp",
                of("Bàn vỉa hè đặc sản Hà Nội", "Pavement dining - Hanoi style"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-9-normal-1374923744625.webp",
                of("Phòng riêng nhà hàng", "Private dining room"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-10-normal-1374923844626.webp",
                of("Món nướng đặc sắc", "Signature grilled dishes"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-11-normal-1374923944627.webp",
                of("Lẩu đồng quê thơm ngon", "Rustic-style hotpot"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nhang-nuong-dai-co-viet-12-normal-1374924144628.webp",
                of("Bữa tiệc cuối tuần cùng gia đình", "Weekend family feast"), false, 11)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/nh%E1%BA%AFng%20n%C6%B0%E1%BB%9Bng/menu-nhang-nuong-dai-co-viet-1.jpg",
                of("Bảng giá Nhắng Nướng - Đại Cồ Việt", "Nhang Nuong - Dai Co Viet price menu"), false, 0)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_GRILL")
                .name(of("Nướng Việt Nam", "Vietnamese Grill"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt", "Vietnamese"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (T2-CN: 10:30-13:30 và 17:00-22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 13, 30),
            openingHour(DayOfWeek.MONDAY,    17,  0, 22,  0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 13, 30),
            openingHour(DayOfWeek.TUESDAY,   17,  0, 22,  0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 13, 30),
            openingHour(DayOfWeek.WEDNESDAY, 17,  0, 22,  0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 13, 30),
            openingHour(DayOfWeek.THURSDAY,  17,  0, 22,  0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 13, 30),
            openingHour(DayOfWeek.FRIDAY,    17,  0, 22,  0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 13, 30),
            openingHour(DayOfWeek.SATURDAY,  17,  0, 22,  0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 13, 30),
            openingHour(DayOfWeek.SUNDAY,    17,  0, 22,  0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("COMPANY_EVENT", of("Tiệc cuối năm công ty", "Company year-end party"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("CASUAL",    of("Bình dân, thân thiện", "Casual and friendly")),
            ambience("VIBRANT",   of("Sôi động náo nhiệt", "Vibrant and lively")),
            ambience("AUTHENTIC", of("Đậm chất phố cổ Hà Nội", "Authentic Hanoi old quarter vibe")),
            ambience("COZY",      of("Ấm cúng", "Cozy"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(45)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(45)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Bò sốt vừng cay", "Spicy sesame-marinated beef"), null, new BigDecimal("90000")),
            signatureDish(of("Nầm nướng cari", "Curry-marinated grilled beef udder"), null, new BigDecimal("85000")),
            signatureDish(of("Bạch tuộc sa tế", "Grilled octopus with satay sauce"), null, new BigDecimal("95000")),
            signatureDish(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"), null, new BigDecimal("90000")),
            signatureDish(of("Ba chỉ tẩm hành", "Grilled pork belly with scallion"), null, new BigDecimal("80000")),
            signatureDish(of("Chân gà chiên mắm cháy tỏi", "Crispy chicken feet in caramelised fish sauce and garlic"), null, new BigDecimal("75000")),
            signatureDish(of("Lẩu riêu cua bắp bò sườn sụn", "Crab-paste hotpot with beef brisket and cartilage"), null, new BigDecimal("150000")),
            signatureDish(of("Lẩu ếch măng chua", "Frog hotpot with sour bamboo shoots"), null, new BigDecimal("140000")),
            signatureDish(of("Tôm nướng muối ớt", "Salt-and-chili grilled shrimp"), null, new BigDecimal("110000")),
            signatureDish(of("Mực nướng tương mè", "Grilled squid with sesame sauce"), null, new BigDecimal("100000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("NUONG")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò sốt vừng cay", "Spicy sesame-marinated beef"))
                        .description(of("Thịt bò tươi ướp sốt vừng cay đặc trưng, nướng thơm giòn tại bàn.", "Fresh beef marinated in signature spicy sesame sauce, grilled at the table."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "grill", "spicy"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Nầm nướng cari", "Curry-grilled beef udder"))
                        .description(of("Nầm bò mềm dai, ướp cari thơm, nướng vàng đều trên bếp than hoa.", "Tender beef udder marinated in aromatic curry, evenly grilled over charcoal."))
                        .priceFrom(new BigDecimal("85000"))
                        .priceTo(new BigDecimal("85000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "grill", "curry"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"))
                        .description(of("Hàu tươi nướng chín mềm, rưới mỡ hành thơm lừng, ăn kèm đậu phộng rang.", "Fresh oysters grilled until tender, drizzled with fragrant scallion oil, served with roasted peanuts."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "oyster", "grill"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bạch tuộc sa tế", "Grilled octopus with satay"))
                        .description(of("Bạch tuộc tươi nướng cùng sốt sa tế đậm đà, thơm cay quyến rũ.", "Fresh octopus grilled with rich, aromatic satay sauce."))
                        .priceFrom(new BigDecimal("95000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grill", "spicy"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("LAU")
                .name(of("Món lẩu", "Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu riêu cua bắp bò sườn sụn", "Crab-paste hotpot with beef brisket"))
                        .description(of("Nồi lẩu riêu cua đặc trưng miền Bắc, thêm bắp bò và sườn sụn giòn sần sật.", "A quintessential Northern-style crab-paste hotpot, enriched with beef brisket and crunchy cartilage ribs."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "crab", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu ếch măng chua", "Frog hotpot with sour bamboo shoots"))
                        .description(of("Lẩu ếch đậm đà chua thanh từ măng, thịt ếch chắc ngọt và giàu đạm.", "Flavorful frog hotpot with tangy sour bamboo shoots; frog meat is firm, sweet and protein-rich."))
                        .priceFrom(new BigDecimal("140000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "frog", "bamboo"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Không có bãi đỗ ô tô riêng, khách gửi xe ngoài đường hoặc bãi gần đó", "No dedicated car park; street parking or nearby lots available"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè và bãi xe trước nhà hàng", "Pavement and lot in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi miễn phí tại vỉa hè trước nhà hàng, ô tô linh hoạt tự tìm bãi xung quanh.", "Free motorbike parking on the pavement in front; cars should find nearby street or lot parking."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "WIFI",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "BIRTHDAY_CAKE_ALLOWED"
        );

        // ---------- Content STORY ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Nhắng Nướng - Đại Cồ Việt: Hương vị nướng lẩu 'Since 2006' giữa lòng Hà Nội",
                   "Nhang Nuong - Dai Co Viet: Grilled & Hotpot Flavours 'Since 2006' in the Heart of Hanoi")),
            paragraph(ContentSection.STORY,
                of("Nhắng Nướng tọa lạc tại số 81 Đại Cồ Việt, phường Lê Đại Hành, quận Hai Bà Trưng, Hà Nội — ngay " +
                   "trên con phố tấp nập quen thuộc của dân sành ẩm thực Thủ đô. Nhà hàng hoạt động từ năm 2006, " +
                   "gần hai thập kỷ giữ vững phong độ với công thức đơn giản: nướng ngon, lẩu đậm, giá bình dân.",
                   "Nhang Nuong is situated at 81 Dai Co Viet Street, Le Dai Hanh Ward, Hai Ba Trung District, Hanoi — " +
                   "on a busy street well known to food-savvy locals. In operation since 2006, the restaurant has maintained " +
                   "its reputation for nearly two decades with a simple formula: great grills, rich hotpots, and affordable prices.")),

            heading(ContentSection.STORY,
                of("Không gian 2 tầng rộng thoáng — từ vỉa hè phố đến phòng riêng yên tĩnh",
                   "Two-floor open space — from pavement dining to quiet private rooms")),
            paragraph(ContentSection.STORY,
                of("Nhắng Nướng sở hữu không gian 2 tầng với sức chứa tối đa 300 khách. Tầng 1 mở ra vỉa hè theo đúng " +
                   "phong cách \"nhậu phố Hà Nội\" — ngồi bệt, gió mát, bia lạnh và bếp than hoa đặt ngay trên bàn. " +
                   "Tầng 2 thoáng mát hơn với hệ thống điều hoà và bố trí bàn ghế lịch sự hơn cho các buổi liên hoan nhóm.",
                   "Nhang Nuong spans two floors with a maximum capacity of 300 guests. The ground floor opens onto the pavement " +
                   "in true Hanoi street-dining style — low stools, cool breeze, cold beer and a charcoal grill right on the table. " +
                   "The second floor is more comfortable with air conditioning and tidier seating for group gatherings.")),

            heading(ContentSection.STORY,
                of("2 phòng riêng — lý tưởng cho tiệc sinh nhật và họp mặt",
                   "Two private rooms — ideal for birthday parties and private gatherings")),
            paragraph(ContentSection.STORY,
                of("Dành cho những nhóm cần không gian riêng tư, Nhắng Nướng có 2 phòng riêng, sức chứa từ 20 đến 45 khách mỗi phòng. " +
                   "Phòng riêng được trang bị hệ thống âm thanh, đèn điều chỉnh và có thể trang trí theo yêu cầu cho sinh nhật, " +
                   "kỷ niệm hay tiệc cuối năm công ty.",
                   "For groups requiring a more private setting, Nhang Nuong offers two private rooms accommodating 20 to 45 guests each. " +
                   "Each room features sound systems, adjustable lighting and can be decorated on request for birthdays, anniversaries or company year-end parties.")),

            heading(ContentSection.STORY,
                of("Thực đơn phong phú — nướng đa dạng, lẩu đậm vị đồng quê",
                   "Rich menu — diverse grills and rustic-flavoured hotpots")),
            paragraph(ContentSection.STORY,
                of("Thực đơn của Nhắng Nướng xoay quanh hai trụ cột: nướng tại bàn và lẩu đồng quê. Phía nướng nổi bật với " +
                   "bò sốt vừng cay, nầm nướng cari, bạch tuộc sa tế và hàu nướng mỡ hành — tất cả đều được ướp theo công thức " +
                   "gia truyền rồi nướng trực tiếp trên bếp than hoa ngay tại bàn thực khách.",
                   "Nhang Nuong's menu revolves around two pillars: table-top grilling and rustic hotpots. Stand-out grilled items include " +
                   "spicy-sesame beef, curry-grilled beef udder, satay octopus and scallion-oil oysters — all marinated to house recipes " +
                   "and cooked directly over a charcoal grill right at the table.")),
            paragraph(ContentSection.STORY,
                of("Phía lẩu không kém phần hấp dẫn với lẩu riêu cua bắp bò sườn sụn — chua thanh đặc trưng Bắc Bộ; " +
                   "và lẩu ếch măng chua độc đáo, ít thấy ở nơi khác. Kèm theo là chân gà chiên mắm cháy tỏi giòn rụm " +
                   "và các món hải sản theo mùa tươi mới mỗi ngày.",
                   "The hotpot side is equally compelling: Northern-style crab-paste hotpot with beef brisket and cartilage ribs — " +
                   "delightfully tangy; and a distinctive frog-and-sour-bamboo hotpot rarely found elsewhere. Completing the spread " +
                   "are crispy caramelised-garlic chicken feet and fresh seasonal seafood updated daily.")),

            heading(ContentSection.STORY,
                of("Mức giá bình dân — chỉ từ 120.000đ/người",
                   "Affordable pricing — from just 120,000 VND per person")),
            paragraph(ContentSection.STORY,
                of("Với mức giá dao động từ 120.000 đến 150.000 VNĐ/người, Nhắng Nướng là một trong những điểm ăn ngon " +
                   "giá trị nhất khu vực Hai Bà Trưng. Giá cả bình dân không đồng nghĩa với chất lượng kém — ngược lại, " +
                   "nhà hàng luôn giữ nguyên liệu tươi và khẩu phần hào phóng để xứng đáng với sự tin tưởng của thực khách gần 20 năm.",
                   "With prices ranging from 120,000 to 150,000 VND per person, Nhang Nuong is one of the best-value dining spots " +
                   "in the Hai Ba Trung area. Affordable pricing does not mean compromised quality — on the contrary, " +
                   "the restaurant has consistently maintained fresh ingredients and generous portions to justify nearly 20 years of diner loyalty.")),

            heading(ContentSection.STORY,
                of("Vị trí thuận tiện — trung tâm quận Hai Bà Trưng",
                   "Convenient location — in the heart of Hai Ba Trung District")),
            paragraph(ContentSection.STORY,
                of("Số 81 Đại Cồ Việt nằm trên trục đường lớn kết nối quận Hai Bà Trưng với khu vực Bách Khoa và Lê Duẩn, " +
                   "dễ dàng di chuyển bằng xe máy hoặc taxi từ hầu hết các quận trung tâm Hà Nội. " +
                   "Bãi xe máy miễn phí ngay trước cửa là điểm cộng lớn cho thực khách ghé thăm buổi tối.",
                   "No. 81 Dai Co Viet sits on a major artery connecting Hai Ba Trung District with the Bach Khoa and Le Duan areas, " +
                   "easily accessible by motorbike or taxi from most central Hanoi districts. " +
                   "Free motorbike parking right at the entrance is a big plus for evening diners."))
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3,  8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(6,  1,  of("Không áp dụng ưu đãi ngày 1/6", "Promotion not applicable on Jun 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(10, 20, of("Không áp dụng ưu đãi ngày 20/10", "Promotion not applicable on Oct 20")),
            blackoutGregorian(11, 20, of("Không áp dụng ưu đãi ngày 20/11", "Promotion not applicable on Nov 20")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 25, of("Không áp dụng ưu đãi ngày 25/12", "Promotion not applicable on Dec 25")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("100000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 100.000đ/chai.", "Wine corkage fee: 100,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 200.000đ/chai.", "Spirits corkage fee: 200,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho đặt bàn thông thường.", "No deposit required for regular bookings."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không thu phụ phí dịch vụ.", "No service charge applied."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.", "Outside drinks are allowed with a corkage fee depending on the type of drink."))
            .outsideDrinkFees(outsideDrinkFees)
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
            .partnerId("11")
            .partnerType(PartnerType.INTERNAL_ADMIN)
            .build();

        ModerationInfo moderation = ModerationInfo.builder()
            .status(RestaurantStatus.APPROVED)
            .rejectionReason(null)
            .blockedReason(null)
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Loại bàn ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4 người", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường phù hợp cho nhóm 2-4 người.", "Standard table for groups of 2 to 4."))
                .build(),
            TableType.builder()
                .id("tb-8")
                .name(of("Bàn 8 người", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn phù hợp cho nhóm bạn bè hoặc gia đình.", "Large table suitable for friend groups or families."))
                .build(),
            TableType.builder()
                .id("pr-45")
                .name(of("Phòng riêng 20-45 khách", "Private room for 20–45 guests"))
                .seats(45)
                .minPeople(20)
                .maxPeople(45)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng yên tĩnh, trang bị âm thanh ánh sáng, trang trí theo yêu cầu.", "Quiet private room with sound and lighting, decorable on request."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(30)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(20)
            .depositOnly(false)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hai Bà Trưng", "Hai Ba Trung District"))
            .wardName(of("P. Lê Đại Hành", "Le Dai Hanh Ward"))
            .addressLine(of("Số 81 Đại Cồ Việt, P. Lê Đại Hành, Q. Hai Bà Trưng, Hà Nội",
                            "No. 81 Dai Co Viet Street, Le Dai Hanh Ward, Hai Ba Trung District, Hanoi"))
            .location(new double[]{105.8431, 21.0016})
            .name(of("Nhắng Nướng - Đại Cồ Việt", "Nhang Nuong - Dai Co Viet"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nướng lẩu bình dân 'since 2006' giữa trung tâm quận Hai Bà Trưng — đặc sản phố Hà Nội.",
                                 "Casual grill & hotpot 'since 2006' in the heart of Hai Ba Trung — a true Hanoi street-dining gem."))
            .description(of("Nhắng Nướng - Đại Cồ Việt là nhà hàng chuyên nướng & lẩu theo phong cách Việt bình dân, " +
                            "hoạt động từ năm 2006 tại số 81 Đại Cồ Việt, quận Hai Bà Trưng, Hà Nội. " +
                            "Không gian 2 tầng sức chứa 300 khách với bàn vỉa hè đặc chất phố cổ và 2 phòng riêng yên tĩnh. " +
                            "Mức giá 120.000-150.000đ/người phù hợp cho mọi dịp từ ăn trưa văn phòng, tụ tập bạn bè đến tiệc sinh nhật.",
                            "Nhang Nuong - Dai Co Viet is a restaurant specialising in casual Vietnamese-style grills and hotpots, " +
                            "in operation since 2006 at No. 81 Dai Co Viet, Hai Ba Trung District, Hanoi. " +
                            "The two-floor venue seats 300 guests with pavement tables in classic old-Hanoi style and two quiet private rooms. " +
                            "Priced at 120,000–150,000 VND per person, it suits everything from office lunches and friend gatherings to birthday parties."))
            .phone("0931006005")
            .email(null)
            .website("https://pasgo.vn/nha-hang/nha-hang-nhang-nuong-dai-co-viet-4617")
            .facebookPage(null)
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("120000"))
            .maxPricePerPerson(new BigDecimal("150000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
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
