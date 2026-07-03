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
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedAmThucHoangTinNguyenVanThoai {

    @Bean
    CommandLineRunner seedAmThucHoangTinNguyenVanThoaiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedAmThucHoangTinNguyenVanThoai] start");
            RestaurantDoc doc = restaurantAmThucHoangTin();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedAmThucHoangTinNguyenVanThoai] done");
        };
    }

    // =====================================================================
    //       ẨM THỰC HOÀNG TÍN - 62A NGUYỄN VĂN THOẠI (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantAmThucHoangTin() {
        String slug = "am-thuc-hoang-tin-nguyen-van-thoai-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-1-normal-2273233459859.webp",
                of("Không gian Ẩm Thực Hoàng Tín Nguyễn Văn Thoại", "Am Thuc Hoang Tin Nguyen Van Thoai restaurant"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-2-normal-2273233559860.webp",
                of("Khu vực bàn ăn rộng rãi", "Spacious dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-3-normal-2273233659861.webp",
                of("Không gian ấm cúng phong cách truyền thống", "Warm traditional-style ambience"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-4-normal-2273233759862.webp",
                of("Bàn tiệc gia đình được bài trí gọn gàng", "Neatly arranged family banquet table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-5-normal-2273233859863.webp",
                of("Không gian thoáng mát với nhiều cây xanh", "Airy space with lush greenery"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-6-normal-2273233959864.webp",
                of("Khu vực bàn nhóm đông", "Large group dining area"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-7-normal-2273234059865.webp",
                of("Không gian ngoài trời mát mẻ", "Cool outdoor dining space"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-8-normal-2273234159866.webp",
                of("Khu vực tiệc sinh nhật và sự kiện", "Birthday and event celebration area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-9-normal-2273234259867.webp",
                of("Góc chụp ảnh đặc trưng của nhà hàng", "Signature photo corner of the restaurant"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-10-normal-2273234359868.webp",
                of("Ánh đèn vàng ấm áp buổi tối", "Warm golden lighting in the evening"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-11-normal-2273234459869.webp",
                of("Phòng riêng khép kín cho tiệc nhỏ", "Enclosed private room for small gatherings"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hoang-tin-12-normal-2273234559870.webp",
                of("Toàn cảnh không gian Hoàng Tín", "Full panoramic view of Hoang Tin restaurant"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/62a-nguyen-van-thoai--p--bac-my-phu--q--ngu-hanh-son-160542845195387108239820402254.webp",
                of("Vị trí nhà hàng tại 62A Nguyễn Văn Thoại, Q. Ngũ Hành Sơn", "Restaurant location at 62A Nguyen Van Thoai, Ngu Hanh Son District"), false, 12)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Hoang-Tin/menu-hoàng-tín-2.jpg",
                of("Thực đơn Hoàng Tín trang 2", "Hoang Tin menu page 2"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Hoang-Tin/menu-hoàng-tín-3.jpg",
                of("Thực đơn Hoàng Tín trang 3", "Hoang Tin menu page 3"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Hoang-Tin/menu-hoàng-tín-4.jpg",
                of("Thực đơn Hoàng Tín trang 4", "Hoang Tin menu page 4"), false, 2)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt Nam", "Vietnamese cuisine"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("CENTRAL_VIETNAMESE")
                .name(of("Đặc sản miền Trung", "Central Vietnamese specialty"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản tươi sống", "Fresh seafood"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
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
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm đông", "Large group gathering")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách bình dân", "Casual business meal")),
            suitable("COUPLE",        of("Hẹn hò thân mật", "Casual date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm gia đình", "Family anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa tập thể", "Group office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",    of("Ấm cúng, gần gũi", "Cozy and homely")),
            ambience("RUSTIC",  of("Mộc mạc, truyền thống", "Rustic and traditional")),
            ambience("VIBRANT", of("Sôi động, náo nhiệt cuối tuần", "Vibrant on weekends")),
            ambience("MODERN",  of("Hiện đại, sạch sẽ", "Modern and clean"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Cá lóc nướng trui", "Grilled Snakehead Fish"),
                of("Cá lóc tươi nướng trui trực tiếp trên than hồng, không qua sơ chế, giữ nguyên vị ngọt tự nhiên, ăn với bánh tráng, rau sống và mắm nêm.",
                   "Fresh snakehead fish grilled whole over live charcoal without pre-processing, preserving natural sweetness, served with rice paper, fresh greens and fermented anchovy dip."),
                new BigDecimal("120000")),
            signatureDish(of("Mì Quảng đặc biệt", "Mi Quang Special"),
                of("Mì Quảng truyền thống miền Trung với nước nhân tôm thịt đậm đà, ít nước, ăn kèm bánh tráng nướng, rau sống và đậu phộng rang.",
                   "Traditional Central Vietnamese Mi Quang with flavorful shrimp-pork broth, served with roasted rice paper, fresh greens and roasted peanuts."),
                new BigDecimal("60000")),
            signatureDish(of("Bánh tráng cuốn thịt heo", "Rice Paper Rolls with Pork"),
                of("Thịt heo ba chỉ luộc chín thái mỏng, cuốn bánh tráng với rau thơm, dưa leo, khế và chấm mắm nêm — đặc sản Đà Nẵng.",
                   "Thinly-sliced boiled pork belly rolled in rice paper with fresh herbs, cucumber, starfruit and fermented anchovy dip — a Da Nang signature dish."),
                new BigDecimal("75000")),
            signatureDish(of("Hải sản nướng than hoa", "Charcoal-grilled Seafood Platter"),
                of("Tôm, mực, sò điệp tươi nướng than hoa với muối ớt xanh, bơ tỏi và phô mai, thơm lừng khi thưởng thức.",
                   "Fresh shrimp, squid and scallops grilled over charcoal with green chili salt, garlic butter and cheese, aromatic and flavorful."),
                new BigDecimal("200000")),
            signatureDish(of("Canh chua cá tươi", "Fresh Fish Sour Soup"),
                of("Canh chua cá tươi đặc trưng miền Trung với cà chua, thơm, giá đỗ và lá me non, vị chua ngọt thanh mát không thể quên.",
                   "Central Vietnamese-style fresh fish sour soup with tomatoes, pineapple, bean sprouts and young tamarind leaves, unforgettably refreshing sour-sweet flavor."),
                new BigDecimal("80000")),
            signatureDish(of("Bún bò Huế", "Hue Beef Noodle Soup"),
                of("Bún bò Huế chuẩn vị với nước dùng hầm xương đậm đà, chả cua và giò heo nấu chín mềm, ăn kèm rau thơm và mắm ruốc.",
                   "Authentic Hue beef noodle soup with rich bone-simmered broth, crab cake and tender braised pork leg, served with fresh herbs and shrimp paste."),
                new BigDecimal("65000")),
            signatureDish(of("Bánh căn hải sản", "Seafood Mini Pancakes"),
                of("Bánh căn miền Trung nhân tôm mực trứng cút, chiên giòn trong khuôn đất nung, chấm nước mắm chua ngọt hoặc nước lèo.",
                   "Central Vietnamese mini rice pancakes filled with shrimp, squid and quail egg, crispy-fried in clay molds, dipped in sweet-sour fish sauce or broth."),
                new BigDecimal("55000")),
            signatureDish(of("Lẩu hải sản chua cay", "Spicy-Sour Seafood Hotpot"),
                of("Lẩu hải sản chua cay đặc trưng miền Trung với tôm, mực, ngao, cá và rau, nước dùng me và cà chua đậm đà.",
                   "Central Vietnamese-style spicy-sour seafood hotpot with shrimp, squid, clams, fish and vegetables in a rich tamarind-tomato broth."),
                new BigDecimal("280000")),
            signatureDish(of("Gỏi cuốn tôm thịt", "Shrimp & Pork Fresh Spring Rolls"),
                of("Tôm sú luộc chín và thịt heo ba chỉ cuốn bánh tráng tươi với bún, rau sống và chấm tương hoisin pha lạc rang.",
                   "Cooked tiger shrimp and pork belly rolled in fresh rice paper with vermicelli, fresh greens, dipped in hoisin-peanut sauce."),
                new BigDecimal("65000")),
            signatureDish(of("Cơm âm phủ", "Com Am Phu (Underworld Rice)"),
                of("Cơm âm phủ Huế với nhiều topping phong phú: trứng tráng, thịt nướng, chả giò, rau cải, dưa chua và nước mắm pha đặc trưng.",
                   "Hue-style 'underworld rice' with diverse toppings: egg crepe, grilled meat, spring rolls, pickled vegetables and a signature fish sauce dressing."),
                new BigDecimal("70000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("CENTRAL_SPECIALTY")
                .name(of("Đặc sản miền Trung", "Central Vietnamese Specialties"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Mì Quảng tôm thịt", "Mi Quang with Shrimp & Pork"))
                        .description(of("Mì Quảng truyền thống với nhân tôm thịt, rau sống và bánh tráng nướng.",
                                       "Traditional Mi Quang with shrimp-pork filling, fresh greens and roasted rice cracker."))
                        .priceFrom(new BigDecimal("60000"))
                        .priceTo(new BigDecimal("80000"))
                        .unit(of("tô", "bowl"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("central", "noodle", "vietnamese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bánh tráng cuốn thịt heo", "Pork Rice Paper Rolls"))
                        .description(of("Thịt heo luộc cuốn bánh tráng với rau và mắm nêm — đặc sản Đà Nẵng.",
                                       "Boiled pork rolled in rice paper with greens and fermented dip — Da Nang specialty."))
                        .priceFrom(new BigDecimal("75000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("danang", "pork", "rice-paper"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("SEAFOOD_GRILL")
                .name(of("Hải sản nướng & lẩu", "Grilled Seafood & Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Mực nướng muối ớt (300g)", "Grilled Squid with Chili Salt (300g)"))
                        .description(of("Mực tươi nướng than hoa muối ớt xanh, thơm dai, ăn kèm mắm gừng.",
                                       "Fresh squid grilled over charcoal with green chili salt, fragrant and chewy, served with ginger fish sauce."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("160000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "grilled", "squid"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu hải sản chua cay (2-3 người)", "Spicy Seafood Hotpot (2-3 pax)"))
                        .description(of("Lẩu hải sản chua cay với tôm, mực, ngao và rau, ăn kèm bún tươi.",
                                       "Spicy-sour seafood hotpot with shrimp, squid, clams and vegetables, served with fresh noodles."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("320000"))
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
            .carParkingLocation(of("Đỗ xe trên đường Nguyễn Văn Thoại và các hẻm lân cận", "Street parking on Nguyen Van Thoai and nearby alleys"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy rộng ngay cổng nhà hàng 62A Nguyễn Văn Thoại", "Spacious motorbike parking right at the restaurant gate, 62A Nguyen Van Thoai"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe miễn phí rộng rãi, thuận tiện cho khách đến bằng ô tô và xe máy.", "Spacious free parking, convenient for guests arriving by car and motorbike."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "OUTDOOR_TABLE",
            "KIDS_CHAIR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "EVENT_DECOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "SMOKING_AREA",
            "SET_LUNCH"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Ẩm Thực Hoàng Tín — Hương vị miền Trung đích thực tại Ngũ Hành Sơn",
                   "Am Thuc Hoang Tin — Authentic Central Vietnamese flavors in Ngu Hanh Son")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại 62A Nguyễn Văn Thoại, Phường Bắc Mỹ Phú, Quận Ngũ Hành Sơn, TP. Đà Nẵng, " +
                   "Ẩm Thực Hoàng Tín là một trong những địa chỉ ăn uống quen thuộc của cư dân và du khách " +
                   "khu vực phía nam Đà Nẵng. Nhà hàng gây dựng uy tín bằng thực đơn phong phú, nguyên liệu " +
                   "tươi ngon nhập hàng ngày, giá cả bình dân và không gian ấm cúng gần gũi theo phong cách " +
                   "nhà hàng gia đình truyền thống.",
                   "Located at 62A Nguyen Van Thoai, Bac My Phu Ward, Ngu Hanh Son District, Da Nang City, " +
                   "Am Thuc Hoang Tin is one of the familiar dining spots for residents and visitors in " +
                   "southern Da Nang. The restaurant has built its reputation through a rich menu, fresh " +
                   "ingredients sourced daily, affordable prices and a warm, welcoming atmosphere in the " +
                   "style of a traditional family restaurant.")),
            heading(ContentSection.STORY,
                of("Không gian sân vườn thoáng đãng — Sức chứa 180 khách",
                   "Spacious garden-style space — Capacity for 180 guests")),
            paragraph(ContentSection.STORY,
                of("Không gian Hoàng Tín trải rộng với khu vực bàn trong nhà có máy lạnh thoáng mát và " +
                   "khu vực ngoài trời sân vườn xanh mát, lý tưởng cho những bữa tối ngắm trăng hoặc " +
                   "buổi tụ họp cuối tuần. Sức chứa khoảng 180 khách với 2 phòng riêng tối đa 30 khách, " +
                   "phù hợp tiệc sinh nhật gia đình hoặc liên hoan nhỏ. Nhà hàng có hệ thống đèn trang trí " +
                   "sự kiện và âm thanh nhẹ nhàng tạo nên không khí vui vẻ và đầm ấm.",
                   "Hoang Tin's space spans both an air-conditioned indoor dining area and a refreshing " +
                   "outdoor garden, ideal for moonlit dinners or weekend get-togethers. It accommodates " +
                   "approximately 180 guests with 2 private rooms seating up to 30 people, suitable for " +
                   "family birthday parties or small celebrations. The restaurant has an event lighting " +
                   "system and gentle background music creating a cheerful and warm atmosphere.")),
            heading(ContentSection.STORY,
                of("Đặc sản miền Trung — Hương vị chân thực từ đất Quảng",
                   "Central Vietnamese specialties — Authentic flavors from Quang Nam land")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Hoàng Tín nổi bật với các món đặc sản miền Trung được chế biến theo công thức " +
                   "gia truyền: Mì Quảng tôm thịt với nhân đậm đà; Bánh tráng cuốn thịt heo chấm mắm nêm — " +
                   "đặc sản huyền thoại của Đà Nẵng; Cá lóc nướng trui giữ nguyên vị ngọt tự nhiên; " +
                   "Bún bò Huế nước dùng hầm xương đậm vị; Bánh căn hải sản giòn rụm; và các món hải sản " +
                   "tươi sống nướng than hoa theo mùa.",
                   "Hoang Tin's menu stands out with Central Vietnamese specialties prepared with " +
                   "traditional family recipes: Mi Quang with rich shrimp-pork filling; Pork rice paper " +
                   "rolls dipped in fermented anchovy — Da Nang's legendary specialty; Whole grilled " +
                   "snakehead fish preserving natural sweetness; Richly bone-simmered Hue beef noodle soup; " +
                   "Crispy seafood mini pancakes; and seasonal fresh seafood grilled over charcoal.")),
            heading(ContentSection.STORY,
                of("Hải sản tươi sống — Nguồn cung từ cảng cá Thọ Quang",
                   "Live seafood — Sourced from Tho Quang fishing port")),
            paragraph(ContentSection.STORY,
                of("Một điểm mạnh của Ẩm Thực Hoàng Tín là nguồn hải sản tươi sống được nhập từ cảng cá " +
                   "Thọ Quang — chỉ cách nhà hàng khoảng 5km — đảm bảo độ tươi ngon mỗi ngày. Thực khách " +
                   "có thể thưởng thức tôm, mực, sò điệp, ngao, cua ghẹ và các loại cá tươi theo mùa với " +
                   "nhiều cách chế biến: nướng than hoa, hấp, xào và lẩu. Mức giá hải sản hợp lý so với " +
                   "chất lượng, phù hợp cho cả khách gia đình bình dân lẫn bữa tiệc đặc biệt.",
                   "One of Am Thuc Hoang Tin's strengths is its live seafood sourced from Tho Quang " +
                   "fishing port — just about 5km away — ensuring daily freshness. Guests can enjoy " +
                   "shrimp, squid, scallops, clams, crabs and seasonal fresh fish prepared in multiple " +
                   "styles: charcoal-grilled, steamed, stir-fried and hotpot. Seafood prices are " +
                   "reasonable for the quality, suitable for both everyday family meals and special occasions.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho mọi dịp gia đình và bạn bè",
                   "Perfect for family and friends gatherings")),
            paragraph(ContentSection.STORY,
                of("Với không gian rộng rãi và thực đơn đa dạng, Hoàng Tín là địa điểm lý tưởng cho bữa " +
                   "ăn gia đình cuối tuần, tụ họp bạn bè lâu ngày không gặp, tiệc sinh nhật ấm cúng hay " +
                   "liên hoan nhóm làm việc. Nhà hàng sẵn sàng nhận đặt tiệc theo yêu cầu với trang trí " +
                   "riêng và thực đơn theo ngân sách. Nhân viên phục vụ thân thiện, vui vẻ và nắm rõ " +
                   "thực đơn để tư vấn cho khách.",
                   "With its spacious setting and diverse menu, Hoang Tin is an ideal spot for weekend " +
                   "family dinners, reunions with old friends, warm birthday celebrations or team gatherings. " +
                   "The restaurant accepts custom event bookings with personalized decoration and menus by " +
                   "budget. Service staff are friendly, cheerful and knowledgeable about the menu to " +
                   "advise guests.")),
            heading(ContentSection.STORY,
                of("Vị trí thuận lợi gần bãi biển Mỹ Khê và Ngũ Hành Sơn",
                   "Convenient location near My Khe Beach and Marble Mountains")),
            paragraph(ContentSection.STORY,
                of("62A Nguyễn Văn Thoại nằm trong Quận Ngũ Hành Sơn — cách bãi biển Mỹ Khê khoảng 3km " +
                   "và Ngũ Hành Sơn (Marble Mountains) khoảng 4km. Đây là vị trí lý tưởng cho du khách " +
                   "sau khi tham quan các điểm du lịch khu vực phía nam Đà Nẵng muốn thưởng thức ẩm thực " +
                   "địa phương chính thống. Bãi đỗ xe rộng rãi miễn phí cho cả ô tô và xe máy. Đường " +
                   "Nguyễn Văn Thoại là trục giao thông chính kết nối trung tâm với bán đảo Sơn Trà.",
                   "62A Nguyen Van Thoai is in Ngu Hanh Son District — about 3km from My Khe Beach and " +
                   "4km from the Marble Mountains. This is an ideal location for visitors who, after " +
                   "touring southern Da Nang attractions, want to enjoy authentic local cuisine. " +
                   "Spacious free parking for both cars and motorbikes. Nguyen Van Thoai Road is the " +
                   "main artery connecting the city center with Son Tra Peninsula.")),
            heading(ContentSection.STORY,
                of("Tiện ích & chính sách phục vụ",
                   "Amenities & service policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được trang bị máy lạnh, WiFi miễn phí, ghế trẻ em và khu vực hút thuốc " +
                   "riêng biệt ngoài sân. Thanh toán linh hoạt: tiền mặt, chuyển khoản ngân hàng, " +
                   "Visa/Master. Xuất hóa đơn VAT theo yêu cầu. Đặt bàn trước qua điện thoại để đảm bảo " +
                   "chỗ, đặc biệt vào buổi tối cuối tuần và dịp lễ. Nhà hàng nhận đặt tiệc trọn gói " +
                   "với dịch vụ trang trí sinh nhật, âm thanh ánh sáng và dịch vụ MC cho sự kiện nhỏ.",
                   "The restaurant is equipped with air conditioning, free WiFi, children's chairs and " +
                   "a separate outdoor smoking area. Flexible payment: cash, bank transfer, Visa/Master. " +
                   "VAT invoice issued on request. Advance reservations by phone are recommended to " +
                   "guarantee seating, especially on weekend evenings and holidays. Full-service event " +
                   "packages available with birthday decoration, audio-visual and MC services for small events.")),
            mapBlock(ContentSection.STORY, new double[]{108.2531, 16.0309})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("150000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng đặt cọc 150.000đ/bàn với tiệc từ 10 người. Hoàn cọc nếu hủy trước 2 giờ.",
                             "Deposit of 150,000 VND/table required for parties of 10 or more. Refunded if cancelled 2 hours before reservation."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi khi đặt bàn qua PasGo; không áp dụng ngày lễ.",
                                 "Promotion available when booking via PasGo; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi tùy từng thời điểm; liên hệ nhà hàng để biết thêm.", "Promotion details vary; contact restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoice issued on request before payment."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không tính phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào nhà hàng.", "Outside food is not permitted inside the restaurant."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào nhà hàng.", "Outside beverages are not permitted."))
            .outsideDrinkFees(List.of())
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

        // ---------- Loại bàn ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn phù hợp gia đình và nhóm nhỏ.", "Standard table suitable for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-grp")
                .name(of("Bàn nhóm 8 ghế", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(12)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nhóm lớn cho gia đình đông hoặc bạn bè tụ tập.", "Large group table for extended families or friend gatherings."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 180))
                .note(of("Phòng riêng khép kín với âm thanh ánh sáng, phù hợp tiệc sinh nhật và liên hoan nhỏ.",
                         "Enclosed private room with audio-visual equipment, suitable for birthday parties and small celebrations."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(120)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(60)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Ngũ Hành Sơn", "Ngu Hanh Son District"))
            .wardName(of("P. Bắc Mỹ Phú", "Bac My Phu Ward"))
            .addressLine(of("62A Nguyễn Văn Thoại, P. Bắc Mỹ Phú, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                            "62A Nguyen Van Thoai Street, Bac My Phu Ward, Ngu Hanh Son District, Da Nang City"))
            .location(new double[]{108.2531, 16.0309})
            .name(of("Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại", "Am Thuc Hoang Tin - Nguyen Van Thoai"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng ẩm thực Việt Nam đặc sản miền Trung tại Ngũ Hành Sơn — Mì Quảng, bánh tráng cuốn, hải sản tươi giá bình dân.",
                                 "Central Vietnamese cuisine restaurant in Ngu Hanh Son — Mi Quang, rice paper rolls, fresh seafood at affordable prices."))
            .description(of("Ẩm Thực Hoàng Tín tại 62A Nguyễn Văn Thoại, Q. Ngũ Hành Sơn, Đà Nẵng — nhà hàng gia đình đặc sản miền Trung, " +
                            "sức chứa 180 khách, 2 phòng riêng, không gian sân vườn thoáng mát. Giá bình dân 150.000–250.000đ/người. " +
                            "Mở cửa 10:00 – 22:00 hàng ngày, gần biển Mỹ Khê và Ngũ Hành Sơn.",
                            "Am Thuc Hoang Tin at 62A Nguyen Van Thoai, Ngu Hanh Son District, Da Nang — family-style Central Vietnamese " +
                            "restaurant, seats 180 guests, 2 private rooms, airy garden space. Affordable 150,000–250,000 VND/person. " +
                            "Open daily 10:00 – 22:00, near My Khe Beach and Marble Mountains."))
            .phone("0542845195")
            .bookingHotline("0542845195")
            .website("https://pasgo.vn/nha-hang/am-thuc-hoang-tin-nguyen-van-thoai-5128")
            .minPricePerPerson(new BigDecimal("150000"))
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
