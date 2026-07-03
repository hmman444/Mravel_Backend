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
import com.mravel.catalog.model.doc.RestaurantDoc.CapacityInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.RestaurantDoc.CuisineTag;
import com.mravel.catalog.model.doc.RestaurantDoc.Image;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuSection;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuItem;
import com.mravel.catalog.model.doc.RestaurantDoc.ModerationInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.OpeningHour;
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
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedPhoBienNguyenTatThanh {

    @Bean
    CommandLineRunner seedPhoBienNguyenTatThanhRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedPhoBienNguyenTatThanh] start");

            RestaurantDoc doc = restaurantPhoBienNguyenTatThanh();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedPhoBienNguyenTatThanh] done");
        };
    }

    // =====================================================================
    //       NHÀ HÀNG PHỐ BIỂN - NGUYỄN TẤT THÀNH (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantPhoBienNguyenTatThanh() {
        String slug = "nha-hang-pho-bien-nguyen-tat-thanh";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-1-normal-466710429278.webp",
                of("Không gian Phố Biển Nguyễn Tất Thành", "Pho Bien Nguyen Tat Thanh restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-2-normal-466711629279.webp",
                of("Khu vực ăn tầng 1", "Dining area floor 1"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-3-normal-466711829280.webp",
                of("Không gian sang trọng bên trong", "Elegant interior dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-4-normal-466712229281.webp",
                of("Bàn tiệc hải sản tươi sống", "Fresh seafood banquet table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-5-normal-466713529282.webp",
                of("Khu vực bàn tầng 2", "2nd floor dining area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-6-normal-466715429283.webp",
                of("Không gian tầng 3 sức chứa lớn", "3rd floor large-capacity space"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-7-normal-466716729284.webp",
                of("Ánh đèn lung linh về đêm", "Shimmering lights at night"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-8-normal-466717429285.webp",
                of("Bể hải sản sống tươi ngay tại nhà hàng", "Live seafood tanks at the restaurant"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-9-normal-2141517629286.webp",
                of("Không gian bàn tiệc gia đình", "Family banquet space"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-10-normal-2141517729287.webp",
                of("Hải sản tươi sống đa dạng", "Diverse fresh seafood selection"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-11-normal-2141517829288.webp",
                of("Không gian ngoài trời thoáng mát", "Open-air outdoor dining area"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-pho-bien-nguyen-tat-thanh-12-normal-2141517929289.webp",
                of("Lối vào nhà hàng Phố Biển", "Pho Bien restaurant entrance"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-1.jpg",
                of("Thực đơn hải sản Phố Biển trang 1", "Pho Bien seafood menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-2.jpg",
                of("Thực đơn hải sản Phố Biển trang 2", "Pho Bien seafood menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-3.jpg",
                of("Thực đơn hải sản Phố Biển trang 3", "Pho Bien seafood menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-4.jpg",
                of("Thực đơn hải sản Phố Biển trang 4", "Pho Bien seafood menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-5.jpg",
                of("Thực đơn hải sản Phố Biển trang 5", "Pho Bien seafood menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-6.jpg",
                of("Thực đơn hải sản Phố Biển trang 6", "Pho Bien seafood menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-7.jpg",
                of("Thực đơn hải sản Phố Biển trang 7", "Pho Bien seafood menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-8.jpg",
                of("Thực đơn hải sản Phố Biển trang 8", "Pho Bien seafood menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/tinh/nh-pho-bien-nguyen-tat-thanh-dn/nha-hang-pho-bien-nguyen-tat-thanh-9.jpg",
                of("Thực đơn hải sản Phố Biển trang 9", "Pho Bien seafood menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/vuvu/phobienntt/nha-hang-pho-bien-nguyen-tat-thanh-khong-gian-1.jpg",
                of("Không gian nhà hàng Phố Biển 1", "Pho Bien restaurant ambience 1"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/vuvu/phobienntt/nha-hang-pho-bien-nguyen-tat-thanh-khong-gian-2.jpg",
                of("Không gian nhà hàng Phố Biển 2", "Pho Bien restaurant ambience 2"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/vuvu/phobienntt/nha-hang-pho-bien-nguyen-tat-thanh-khong-gian-3.jpg",
                of("Không gian nhà hàng Phố Biển 3", "Pho Bien restaurant ambience 3"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/vuvu/phobienntt/nha-hang-pho-bien-nguyen-tat-thanh-khong-gian-4.jpg",
                of("Không gian nhà hàng Phố Biển 4", "Pho Bien restaurant ambience 4"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/vuvu/phobienntt/nha-hang-pho-bien-nguyen-tat-thanh-khong-gian-5.jpg",
                of("Không gian nhà hàng Phố Biển 5", "Pho Bien restaurant ambience 5"), false, 13)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản tươi sống", "Fresh seafood"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("CENTRAL_VIETNAMESE")
                .name(of("Đặc sản miền Trung", "Central Vietnamese specialty"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa (08:00 - 22:00 mỗi ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    8, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   8, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 8, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  8, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    8, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  8, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    8, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm đông", "Large group meeting")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa tập thể", "Group lunch")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively")),
            ambience("MODERN",   of("Hiện đại, sang trọng", "Modern and luxurious")),
            ambience("COZY",     of("Ấm cúng, thoải mái", "Cozy and comfortable")),
            ambience("ELEGANT",  of("Đẳng cấp, thanh lịch", "Elegant and refined"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(570)
            .maxGroupSize(100)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(60)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tôm hùm hấp gừng", "Steamed Lobster with Ginger"),
                of("Tôm hùm tươi sống hấp gừng sả, giữ trọn vị ngọt của biển, thịt chắc mẩy, thơm lừng.",
                   "Fresh live lobster steamed with ginger and lemongrass, preserving the natural sweetness of the sea with firm, aromatic flesh."),
                new BigDecimal("650000")),
            signatureDish(of("Cua hoàng đế rang muối", "Salt-and-Pepper King Crab"),
                of("Cua hoàng đế tươi béo ngậy, rang muối ớt đặc trưng của Phố Biển, ăn kèm bánh mì nóng hổi.",
                   "Fat and fresh king crab stir-fried with Pho Bien's signature salt and chili, served with hot bread."),
                new BigDecimal("550000")),
            signatureDish(of("Mực nhảy nướng muối ớt", "Grilled Jumping Squid with Chili Salt"),
                of("Mực tươi sống còn nhảy, nướng than hoa với muối ớt xanh, chấm mắm gừng đặc biệt.",
                   "Fresh live squid grilled on charcoal with green chili salt, served with special ginger fish sauce."),
                new BigDecimal("180000")),
            signatureDish(of("Ghẹ xanh hấp bia", "Beer-Steamed Blue Crab"),
                of("Ghẹ xanh Đà Nẵng béo chắc, hấp bia tươi, thịt ngọt thanh, chấm muối tiêu chanh.",
                   "Plump Da Nang blue crab steamed with draft beer, sweet and delicate meat, served with salt, pepper and lime dip."),
                new BigDecimal("220000")),
            signatureDish(of("Cá mú hấp Hong Kong", "Hong Kong-Style Steamed Grouper"),
                of("Cá mú tươi hấp kiểu Hong Kong với xì dầu và gừng thái sợi, giữ nguyên vị ngọt tự nhiên của cá.",
                   "Fresh grouper steamed Hong Kong-style with soy sauce and julienned ginger, preserving the fish's natural sweetness."),
                new BigDecimal("280000")),
            signatureDish(of("Ốc hương xào bơ tỏi", "Butter-Garlic Stir-fried Baby Snails"),
                of("Ốc hương Đà Nẵng tươi ngon xào bơ tỏi thơm phức, ăn kèm rau thơm và bánh mì.",
                   "Fresh Da Nang baby snails stir-fried in fragrant butter and garlic, served with fresh herbs and bread."),
                new BigDecimal("120000")),
            signatureDish(of("Cá chình hầm tiêu xanh", "Green Pepper Braised Eel"),
                of("Cá chình biển tươi hầm cùng tiêu xanh Phú Quốc, nước dùng sánh đậm, thịt cá dai mềm vừa phải.",
                   "Fresh sea eel braised with Phu Quoc green pepper, rich and thick broth, eel meat perfectly tender."),
                new BigDecimal("230000")),
            signatureDish(of("Tôm càng xanh nướng tỏi bơ", "Butter-Garlic Grilled Freshwater Prawns"),
                of("Tôm càng xanh tươi to mập nướng bơ tỏi thơm ngào ngạt, thịt ngọt giòn, chấm muối chanh đặc biệt.",
                   "Large plump freshwater prawns grilled with fragrant butter and garlic, sweet crispy flesh, served with special salt-lime dip."),
                new BigDecimal("250000")),
            signatureDish(of("Sò huyết xào tỏi", "Garlic Stir-fried Blood Cockles"),
                of("Sò huyết tươi nguyên con xào tỏi phi vàng, căng bóng màu đỏ, ăn kèm bánh tráng nướng.",
                   "Fresh whole blood cockles stir-fried with golden garlic, plump and glossy red, served with grilled rice paper."),
                new BigDecimal("95000")),
            signatureDish(of("Lẩu hải sản thập cẩm", "Mixed Seafood Hotpot"),
                of("Nồi lẩu hải sản gồm tôm, mực, cua, ngao, cá... nấu nước dùng xương hầm ngọt thanh, ăn kèm rau và bún.",
                   "Seafood hotpot including shrimp, squid, crab, clams, fish... simmered in sweet bone broth, served with vegetables and rice noodles."),
                new BigDecimal("350000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SEAFOOD_MAIN")
                .name(of("Hải sản đặc biệt", "Special Seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tôm hùm hấp (500g)", "Steamed Lobster (500g)"))
                        .description(of("Tôm hùm tươi sống hấp gừng sả hoặc nướng muối ớt.",
                                       "Fresh live lobster steamed with ginger & lemongrass or grilled with chili salt."))
                        .priceFrom(new BigDecimal("580000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "lobster"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Mực nhảy nướng (300g)", "Grilled Jumping Squid (300g)"))
                        .description(of("Mực tươi sống nướng than hoa muối ớt xanh, hương vị đặc trưng miền Trung.",
                                       "Fresh live squid grilled with green chili salt, a signature Central Vietnamese flavor."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("220000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "squid", "grilled"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HOTPOT_SOUP")
                .name(of("Lẩu & canh hải sản", "Seafood Hotpot & Soup"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu hải sản thập cẩm (2-4 người)", "Mixed Seafood Hotpot (2-4 pax)"))
                        .description(of("Nồi lẩu đầy đủ tôm, mực, cua, cá, ngao... ăn kèm bún và rau.",
                                       "Full seafood hotpot with shrimp, squid, crab, fish, clams... served with noodles and vegetables."))
                        .priceFrom(new BigDecimal("320000"))
                        .priceTo(new BigDecimal("420000"))
                        .unit(of("nồi", "pot"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood", "group"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước nhà hàng và trên đường Nguyễn Tất Thành", "Parking in front of restaurant and along Nguyen Tat Thanh street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy ngay cổng nhà hàng", "Motorbike parking at the restaurant gate"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe rộng rãi miễn phí ngay tại nhà hàng.", "Spacious free parking available right at the restaurant."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "OUTDOOR_TABLE",
            "KARAOKE",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "PRIVATE_ROOM",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SOUND_LIGHT"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Nhà hàng hải sản cao cấp bên bờ biển Đà Nẵng",
                   "Premium seafood restaurant by the Da Nang coastline")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Phố Biển – Nguyễn Tất Thành tọa lạc tại số 231 Nguyễn Tất Thành, Phường Thanh Bình, " +
                   "Quận Thanh Khê, TP. Đà Nẵng — ngay sát mặt biển, là một trong những nhà hàng hải sản được ưa chuộng " +
                   "nhất tại thành phố biển xinh đẹp này. Với vị trí đắc địa và thực đơn hải sản phong phú, Phố Biển " +
                   "là điểm đến lý tưởng cho những ai muốn tận hưởng bữa tiệc biển đúng nghĩa giữa lòng Đà Nẵng.",
                   "Pho Bien Restaurant – Nguyen Tat Thanh is located at 231 Nguyen Tat Thanh, Thanh Binh Ward, " +
                   "Thanh Khe District, Da Nang City — right by the sea, making it one of the most popular seafood " +
                   "restaurants in this beautiful coastal city. With its prime location and rich seafood menu, " +
                   "Pho Bien is the ideal destination for those who want to enjoy a truly authentic seaside feast in Da Nang.")),
            heading(ContentSection.STORY,
                of("Không gian 3 tầng — sức chứa đến 570 khách",
                   "Three-floor space — capacity up to 570 guests")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng trải rộng trên 3 tầng lầu với sức chứa lên tới 570 khách, thiết kế sang " +
                   "trọng và hiện đại nhưng vẫn giữ được nét ấm cúng của một nhà hàng gia đình. Tầng 1 đặt khu bể " +
                   "hải sản sống và quầy tiếp nhận; tầng 2 và tầng 3 bố trí các phòng tiệc khép kín và khu vực ăn " +
                   "ngoài trời thoáng mát. Nhà hàng còn có hệ thống phòng riêng với sức chứa từ 20 đến 60 khách, " +
                   "lý tưởng cho các buổi tiệc gia đình hoặc gặp mặt đối tác.",
                   "The restaurant spans three floors with a capacity of up to 570 guests, decorated in a luxurious " +
                   "and modern style while retaining the warmth of a family restaurant. The ground floor features " +
                   "live seafood tanks and the reception desk; the 2nd and 3rd floors house enclosed banquet rooms " +
                   "and an open-air outdoor dining area. Private rooms accommodating 20 to 60 guests are ideal for " +
                   "family celebrations or partner gatherings.")),
            heading(ContentSection.STORY,
                of("Nguồn hải sản tươi sống — nhập thẳng từ vựa tàu cá",
                   "Live seafood sourced directly from fishing fleets")),
            paragraph(ContentSection.STORY,
                of("Điểm tạo nên sự khác biệt của Phố Biển là cam kết sử dụng 100% nguyên liệu tươi sống, được " +
                   "nhập trực tiếp từ các vựa tàu cá biển ngay trong ngày. Thực khách có thể tự tay lựa chọn hải sản " +
                   "từ các bể nuôi đặt ngay tại tầng 1 — tôm hùm, cua hoàng đế, cá mú, mực nhảy, ghẹ xanh, ốc hương " +
                   "— đảm bảo độ tươi ngon và an toàn thực phẩm tuyệt đối. Đội ngũ bếp trưởng giàu kinh nghiệm chế " +
                   "biến theo yêu cầu với hàng chục cách nấu khác nhau.",
                   "What sets Pho Bien apart is its commitment to using 100% fresh ingredients sourced directly from " +
                   "fishing fleets on the same day. Guests can personally select seafood from the live tanks on the " +
                   "ground floor — lobster, king crab, grouper, jumping squid, blue crab, baby snails — ensuring " +
                   "absolute freshness and food safety. Experienced head chefs prepare dishes to order using dozens " +
                   "of different cooking methods.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Phố Biển đa dạng với hàng trăm món hải sản được chế biến theo nhiều phong cách khác nhau: " +
                   "hấp, nướng, xào, chiên, hầm, lẩu... Những món nổi bật không thể bỏ qua gồm tôm hùm hấp gừng, " +
                   "cua hoàng đế rang muối, mực nhảy nướng muối ớt, cá mú hấp Hong Kong và lẩu hải sản thập cẩm. " +
                   "Mức giá dao động từ 80.000 đến 150.000 đồng/người, phù hợp với mọi đối tượng thực khách.",
                   "Pho Bien's menu features hundreds of seafood dishes prepared in many different styles: steaming, " +
                   "grilling, stir-frying, deep-frying, braising, hotpot... Must-try signature dishes include ginger " +
                   "steamed lobster, salt-and-pepper king crab, grilled jumping squid with chili salt, Hong Kong-style " +
                   "steamed grouper and mixed seafood hotpot. Prices range from 80,000 to 150,000 VND per person, " +
                   "suitable for all types of diners.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với quy mô 3 tầng linh hoạt và phòng riêng khép kín, Phố Biển Nguyễn Tất Thành phù hợp cho mọi " +
                   "dịp: ăn gia đình cuối tuần, tụ tập bạn bè, tiệc sinh nhật, liên hoan công ty, tiếp khách đối tác " +
                   "hay hẹn hò lãng mạn. Hệ thống máy chiếu, âm thanh ánh sáng và karaoke giúp các buổi tiệc thêm " +
                   "sôi động và đáng nhớ.",
                   "With its flexible three-floor layout and enclosed private rooms, Pho Bien Nguyen Tat Thanh is " +
                   "perfect for all occasions: weekend family dining, friends gatherings, birthday parties, company " +
                   "parties, partner entertainment or romantic dates. The projector, sound-light system and karaoke " +
                   "make celebrations even more lively and memorable.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật",
                   "Services & notable amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị ghế trẻ em, khu vực bàn ngoài trời thoáng mát và chỗ hút thuốc riêng biệt. " +
                   "Đội ngũ phục vụ chuyên nghiệp, nhiệt tình và am hiểu thực đơn, sẵn sàng tư vấn và phối hợp " +
                   "chế biến theo yêu cầu. Nhà hàng nhận đặt tiệc theo yêu cầu với dịch vụ trang trí sự kiện, " +
                   "âm thanh ánh sáng và karaoke chuyên nghiệp. Chấp nhận thanh toán Visa/Master và xuất hóa đơn VAT.",
                   "The restaurant provides children's chairs, an open-air outdoor terrace and a separate smoking area. " +
                   "Professional, enthusiastic staff well-versed in the menu are ready to advise and accommodate " +
                   "custom preparations. The restaurant accepts event catering orders with professional event " +
                   "decoration, sound-light and karaoke services. Accepts Visa/Master and issues VAT invoices.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Số 231 Nguyễn Tất Thành nằm trên tuyến đường ven biển chạy dọc bờ biển Đà Nẵng, thuộc Quận " +
                   "Thanh Khê — cách trung tâm thành phố khoảng 5 km. Đây là tuyến đường thông thoáng, dễ di chuyển " +
                   "từ cầu Thuận Phước, sân bay hoặc khu du lịch Mỹ Khê. Bãi đỗ xe ô tô và xe máy miễn phí rộng rãi " +
                   "ngay trước cổng nhà hàng.",
                   "231 Nguyen Tat Thanh is on the coastal road running along Da Nang's beach, in Thanh Khe District — " +
                   "about 5 km from the city center. This is a smooth, easily accessible route from Thuan Phuoc Bridge, " +
                   "the airport or My Khe beach resort area. Spacious free parking for both cars and motorbikes is " +
                   "available right outside the restaurant gate.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                   "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Phố Biển Nguyễn Tất Thành nhận đặt bàn trước qua điện thoại và hệ thống trực tuyến. " +
                   "Đối với tiệc lớn từ 10 người trở lên, khuyến khích đặt trước ít nhất 1 ngày để chuẩn bị nguyên " +
                   "liệu hải sản theo yêu cầu. Mức giá bình dân từ 80.000 – 150.000 đồng/người với chất lượng hải " +
                   "sản đáng tin cậy là lý do hàng nghìn thực khách chọn Phố Biển mỗi tuần.",
                   "Pho Bien Nguyen Tat Thanh accepts reservations in advance by phone and online booking system. " +
                   "For large parties of 10 or more, booking at least 1 day in advance is recommended to prepare " +
                   "seafood ingredients as requested. Affordable prices of 80,000 – 150,000 VND per person with " +
                   "reliable seafood quality are why thousands of diners choose Pho Bien every week.")),
            mapBlock(ContentSection.STORY, new double[]{108.1924, 16.0618})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Nhà hàng chưa yêu cầu đặt cọc với đặt bàn thông thường; tiệc lớn liên hệ trực tiếp.",
                             "No deposit required for regular reservations; contact directly for large banquets."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt bàn qua hệ thống PasGo; không áp dụng ngày lễ.",
                                 "Promotion for guests booking via PasGo; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy từng thời điểm.", "Promotion details vary by period."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng xuất hóa đơn VAT theo yêu cầu.", "VAT invoice issued on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không có phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào.", "Outside drinks are not permitted."))
            .outsideDrinkFees(List.of())
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
                .id("tb-4-std")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(40)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn, phù hợp gia đình và nhóm nhỏ.", "Standard table for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-grp")
                .name(of("Bàn nhóm 8 ghế", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nhóm lớn, phù hợp tiệc gia đình và bạn bè.", "Large group table for family or friends dining."))
                .build(),
            TableType.builder()
                .id("pr-40")
                .name(of("Phòng tiệc riêng 40 khách", "Private banquet room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(5)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng tiệc riêng với máy chiếu và karaoke, phù hợp liên hoan công ty, tiệc sinh nhật.",
                         "Private banquet room with projector and karaoke, ideal for corporate parties and birthdays."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(60)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(60)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(false)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Thanh Khê", "Thanh Khe District"))
            .wardName(of("P. Thanh Bình", "Thanh Binh Ward"))
            .addressLine(of("Số 231 Nguyễn Tất Thành, P. Thanh Bình, Q. Thanh Khê, TP. Đà Nẵng",
                            "231 Nguyen Tat Thanh, Thanh Binh Ward, Thanh Khe District, Da Nang City"))
            .location(new double[]{108.1924, 16.0618})
            .name(of("Phố Biển - Nguyễn Tất Thành", "Pho Bien Seafood Restaurant - Nguyen Tat Thanh"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng hải sản tươi sống 3 tầng ngay bờ biển Đà Nẵng — sức chứa 570 khách, giá bình dân.",
                                 "3-floor live seafood restaurant right on Da Nang's coast — 570-guest capacity, affordable prices."))
            .description(of("Nhà hàng Phố Biển – Nguyễn Tất Thành tọa lạc tại 231 Nguyễn Tất Thành, Q. Thanh Khê, TP. Đà Nẵng, " +
                            "ngay sát mặt biển với không gian 3 tầng sức chứa 570 khách. Hải sản tươi sống nhập trực tiếp từ " +
                            "vựa tàu cá, chế biến đa dạng theo yêu cầu. Giá bình dân từ 80.000 – 150.000 đồng/người. Phù hợp " +
                            "cho gia đình, bạn bè, tiệc công ty và các sự kiện lớn.",
                            "Pho Bien Restaurant – Nguyen Tat Thanh is located at 231 Nguyen Tat Thanh, Thanh Khe District, " +
                            "Da Nang City, right by the sea with a 3-floor, 570-guest capacity space. Live seafood sourced " +
                            "directly from fishing fleets, prepared in diverse styles on request. Affordable prices from " +
                            "80,000 – 150,000 VND per person. Ideal for families, friends, corporate parties and large events."))
            .phone("0931006005")
            .bookingHotline("0931006005")
            .website("https://pasgo.vn/nha-hang/nha-hang-pho-bien-nguyen-tat-thanh-2791")
            .minPricePerPerson(new BigDecimal("80000"))
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
