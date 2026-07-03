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
public class SeedThaiMarketYenBai {

    @Bean
    CommandLineRunner seedThaiMarketYenBaiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedThaiMarketYenBai] start");
            RestaurantDoc doc = restaurantThaiMarketYenBai();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedThaiMarketYenBai] done");
        };
    }

    // =====================================================================
    //       THAI MARKET RESTAURANT - 124 YÊN BÁI (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantThaiMarketYenBai() {
        String slug = "thai-market-yen-bai-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-1-normal-2384838566937.webp",
                of("Không gian Thai Market Yên Bái", "Thai Market Yen Bai restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-2-normal-2384838466936.webp",
                of("Khu vực bàn ăn tầng trệt", "Ground floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-3-normal-2384838366935.webp",
                of("Không gian ẩm thực Thái trang nhã", "Elegant Thai-style dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-4-normal-2384838266934.webp",
                of("Bàn ăn được bài trí tinh tế", "Elegantly arranged dining table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-5-normal-2384838166933.webp",
                of("Không gian ấm cúng ban đêm", "Cozy ambience at night"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-6-normal-2384838066932.webp",
                of("Khu vực bàn nhóm", "Group dining area"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-7-normal-2384835666931.webp",
                of("Nội thất mang phong cách Thái Lan", "Thai-style interior decor"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-8-normal-2384835566930.webp",
                of("Không gian sang trọng, hiện đại", "Luxurious and modern space"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-9-normal-2384835466929.webp",
                of("Bàn ăn VIP khu vực tầng trên", "VIP dining on upper floor"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-10-normal-2384835366928.webp",
                of("Góc chụp ảnh đặc trưng", "Signature photo corner"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-11-normal-2384835266927.webp",
                of("Không gian rộng rãi thoáng đãng", "Spacious and airy dining space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-12-normal-2384835166926.webp",
                of("Khu vực bàn tiệc đông người", "Banquet area for large groups"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-13-normal-2384835066925.webp",
                of("Trang trí nội thất mang hơi hướng Đông Nam Á", "Southeast Asian-inspired interior"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-14-normal-2384834966924.webp",
                of("Ánh đèn vàng ấm áp tạo không khí lãng mạn", "Warm golden lighting for romantic ambience"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-15-normal-2384834866923.webp",
                of("Phòng riêng có cửa khép kín", "Private room with closed doors"), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-16-normal-2384834766922.webp",
                of("Không gian tầng trên view đẹp", "Upper floor with pleasant view"), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-17-normal-2384834666921.webp",
                of("Bàn ăn được set-up chỉn chu", "Neatly set-up dining table"), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-thai-market-124-yen-bai-18-normal-2384834566920.webp",
                of("Toàn cảnh nhà hàng Thai Market Yên Bái", "Full view of Thai Market Yen Bai restaurant"), false, 17)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-1.jpg",
                of("Thực đơn Thai Market Yên Bái trang 1", "Thai Market Yen Bai menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-2.jpg",
                of("Thực đơn Thai Market Yên Bái trang 2", "Thai Market Yen Bai menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-3.jpg",
                of("Thực đơn Thai Market Yên Bái trang 3", "Thai Market Yen Bai menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-4.jpg",
                of("Thực đơn Thai Market Yên Bái trang 4", "Thai Market Yen Bai menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-5.jpg",
                of("Thực đơn Thai Market Yên Bái trang 5", "Thai Market Yen Bai menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-6.jpg",
                of("Thực đơn Thai Market Yên Bái trang 6", "Thai Market Yen Bai menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-7.jpg",
                of("Thực đơn Thai Market Yên Bái trang 7", "Thai Market Yen Bai menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-8.jpg",
                of("Thực đơn Thai Market Yên Bái trang 8", "Thai Market Yen Bai menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/Thai%20Market/nha-hang-thai-market-restaurant-124-yen-bai-9.jpg",
                of("Thực đơn Thai Market Yên Bái trang 9", "Thai Market Yen Bai menu page 9"), false, 8)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("THAI")
                .name(of("Ẩm thực Thái Lan", "Thai cuisine"))
                .region("SOUTHEAST_ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Ẩm thực châu Á", "Asian cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("SOUTHEAST_ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
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
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm đông", "Large group meeting")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa tập thể", "Group lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",  of("Thanh lịch, tinh tế", "Elegant and refined")),
            ambience("MODERN",   of("Hiện đại, trẻ trung", "Modern and trendy")),
            ambience("COZY",     of("Ấm cúng, dễ chịu", "Cozy and comfortable")),
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tom Yum Kung", "Tom Yum Kung (Spicy Shrimp Soup)"),
                of("Súp chua cay đặc trưng Thái Lan với tôm tươi, nấm, sả và lá chanh, đậm vị chua ngọt đặc trưng xứ Thái.",
                   "Thailand's signature spicy-sour soup with fresh shrimp, mushrooms, lemongrass and kaffir lime leaves, bursting with bold Thai flavors."),
                new BigDecimal("120000")),
            signatureDish(of("Pad Thai", "Pad Thai (Stir-fried Rice Noodles)"),
                of("Mì gạo xào kiểu Thái với tôm, đậu phụ, giá đỗ và trứng, rắc đậu phộng rang thơm, vắt chanh ăn kèm.",
                   "Thai-style stir-fried rice noodles with shrimp, tofu, bean sprouts and egg, topped with crushed roasted peanuts and a squeeze of lime."),
                new BigDecimal("95000")),
            signatureDish(of("Cà ri xanh gà", "Green Curry Chicken"),
                of("Cà ri xanh Thái đậm đà với thịt gà, rau củ thập cẩm, nước cốt dừa béo ngậy, ăn kèm cơm trắng dẻo.",
                   "Rich Thai green curry with chicken, mixed vegetables in creamy coconut milk, served with steamed jasmine rice."),
                new BigDecimal("110000")),
            signatureDish(of("Som Tum (Gỏi đu đủ)", "Som Tum (Papaya Salad)"),
                of("Gỏi đu đủ xanh sợi mảnh, trộn cà chua, đậu đũa, đậu phộng và nước mắm chua ngọt đặc trưng Thái.",
                   "Shredded green papaya salad with tomatoes, green beans, roasted peanuts and tangy Thai fish-sauce dressing."),
                new BigDecimal("75000")),
            signatureDish(of("Massaman Curry bò", "Massaman Beef Curry"),
                of("Cà ri Massaman hầm thịt bò mềm với khoai tây, hành tây, nước cốt dừa thơm và gia vị đặc biệt của Nam Thái.",
                   "Slow-braised Massaman curry with tender beef, potatoes, onions in fragrant coconut milk with southern Thai spice blend."),
                new BigDecimal("130000")),
            signatureDish(of("Gà chiên sốt Thái", "Thai Basil Chicken"),
                of("Thịt gà băm xào lá húng quế Thái, ớt tươi và sốt oyster, thơm lừng, đậm đà, ăn kèm cơm và trứng ốp.",
                   "Minced chicken stir-fried with Thai basil, fresh chilies and oyster sauce, aromatic and savory, served with rice and fried egg."),
                new BigDecimal("90000")),
            signatureDish(of("Tom Kha Gai", "Tom Kha Gai (Coconut Milk Chicken Soup)"),
                of("Canh gà nấu nước cốt dừa với riềng, sả, lá chanh và nấm rơm, hương vị thanh ngọt đặc trưng.",
                   "Chicken soup in coconut milk with galangal, lemongrass, kaffir lime leaves and straw mushrooms, delicately fragrant."),
                new BigDecimal("105000")),
            signatureDish(of("Satay thịt nướng", "Chicken Satay Skewers"),
                of("Thịt gà ướp nghệ và gia vị nướng xiên tre, ăn kèm nước chấm đậu phộng và dưa cải ngọt chua.",
                   "Turmeric-marinated chicken skewered and grilled on bamboo, served with peanut sauce and sweet-sour pickled vegetables."),
                new BigDecimal("80000")),
            signatureDish(of("Xôi xoài (Mango Sticky Rice)", "Mango Sticky Rice"),
                of("Xôi nếp dẻo ngọt rưới nước cốt dừa, ăn kèm xoài chín vàng ươm — món tráng miệng Thái nổi tiếng toàn cầu.",
                   "Sweet sticky rice drizzled with coconut cream, paired with ripe golden mango — Thailand's world-famous dessert."),
                new BigDecimal("55000")),
            signatureDish(of("Cơm chiên Thái", "Thai Fried Rice"),
                of("Cơm chiên kiểu Thái với trứng, hành lá, cà chua và nước mắm đặc trưng, thơm ngon đậm vị, ăn kèm dưa leo và chanh.",
                   "Thai-style fried rice with egg, spring onions, tomatoes and fish sauce, aromatic and flavorful, served with cucumber and lime."),
                new BigDecimal("85000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("THAI_SOUP")
                .name(of("Súp & lẩu Thái", "Thai Soups & Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tom Yum Kung (2 người)", "Tom Yum Kung (serves 2)"))
                        .description(of("Súp chua cay tôm tươi, nấm, sả, lá chanh Thái.",
                                       "Spicy-sour fresh shrimp soup with mushrooms, lemongrass and kaffir lime."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "soup", "spicy"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Tom Kha Gai (2 người)", "Tom Kha Gai (serves 2)"))
                        .description(of("Canh gà nước cốt dừa với riềng và lá chanh.",
                                       "Chicken coconut milk soup with galangal and kaffir lime."))
                        .priceFrom(new BigDecimal("105000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "soup", "coconut"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("THAI_MAIN")
                .name(of("Món chính Thái", "Thai Main Dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Pad Thai tôm", "Pad Thai with Shrimp"))
                        .description(of("Mì gạo xào tôm, giá đỗ, trứng và đậu phộng rang.",
                                       "Rice noodles stir-fried with shrimp, bean sprouts, egg and crushed peanuts."))
                        .priceFrom(new BigDecimal("95000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "noodle", "stir-fry"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cà ri xanh gà", "Green Curry Chicken"))
                        .description(of("Cà ri xanh nước cốt dừa với gà và rau củ, ăn kèm cơm.",
                                       "Green curry in coconut milk with chicken and vegetables, served with rice."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "curry", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe trên đường Yên Bái phía trước nhà hàng", "Street parking on Yen Bai road in front of restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy ngay cổng nhà hàng", "Motorbike parking right at the restaurant gate"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe miễn phí cho khách đến dùng bữa.", "Free parking for dining guests."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "PROJECTOR",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "OUTDOOR_TABLE",
            "MC_SERVICE",
            "SET_LUNCH"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Thai Market Yên Bái — Hành trình ẩm thực Thái giữa lòng Đà Nẵng",
                   "Thai Market Yen Bai — A Thai culinary journey in the heart of Da Nang")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại số 124 đường Yên Bái, Phường Phước Ninh, Quận Hải Châu, TP. Đà Nẵng, " +
                   "Thai Market Restaurant là chi nhánh thuộc chuỗi nhà hàng Thái nổi tiếng với sứ mệnh mang " +
                   "hương vị ẩm thực Thái Lan chính thống đến tận bàn ăn của thực khách Việt. Khu vực Hải Châu " +
                   "trung tâm tạo nên lợi thế vị trí đắc địa, dễ tiếp cận cho mọi đối tượng thực khách từ " +
                   "khách du lịch đến dân văn phòng và gia đình địa phương.",
                   "Located at 124 Yen Bai Street, Phuoc Ninh Ward, Hai Chau District, Da Nang City, " +
                   "Thai Market Restaurant is a branch of the renowned Thai restaurant chain with a mission " +
                   "to bring authentic Thai cuisine flavors right to Vietnamese diners' tables. The central " +
                   "Hai Chau location offers a prime, easily accessible position for all types of guests — " +
                   "tourists, office workers, and local families alike.")),
            heading(ContentSection.STORY,
                of("Không gian thiết kế mang hơi thở Đông Nam Á",
                   "Interior design with Southeast Asian character")),
            paragraph(ContentSection.STORY,
                of("Không gian Thai Market Yên Bái được thiết kế công phu theo phong cách Đông Nam Á hiện đại, " +
                   "kết hợp giữa gỗ tự nhiên, màu sắc ấm áp và ánh đèn vàng dịu nhẹ tạo nên bầu không khí " +
                   "lãng mạn và gần gũi. Nhà hàng có sức chứa khoảng 200 khách trải trên nhiều khu vực: phòng " +
                   "lớn chung, các bàn nhóm và 3 phòng riêng khép kín tối đa 30 khách, phù hợp cho tiệc sinh " +
                   "nhật, gặp mặt đối tác và sự kiện doanh nghiệp nhỏ.",
                   "Thai Market Yen Bai's interior is meticulously designed in a modern Southeast Asian style, " +
                   "blending natural wood, warm tones and soft golden lighting to create a romantic yet " +
                   "approachable ambience. The restaurant seats approximately 200 guests across multiple zones: " +
                   "a large communal hall, group tables and 3 private enclosed rooms accommodating up to 30 guests, " +
                   "ideal for birthday parties, partner meetings and small corporate events.")),
            heading(ContentSection.STORY,
                of("Thực đơn Thái chính thống — Đa dạng từ khai vị đến tráng miệng",
                   "Authentic Thai menu — From appetizers to desserts")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Thai Market bao gồm hàng chục món đặc trưng của ẩm thực xứ Thái: Tom Yum Kung " +
                   "với vị chua cay đậm đà đặc trưng; Pad Thai — mì gạo xào kinh điển; các loại cà ri xanh, " +
                   "đỏ, Massaman ngon nức tiếng; Som Tum gỏi đu đủ tươi ngon; Satay thịt nướng thơm lừng; " +
                   "và không thể thiếu món tráng miệng Mango Sticky Rice hấp dẫn. Tất cả đều sử dụng gia vị " +
                   "nhập khẩu từ Thái Lan để đảm bảo hương vị nguyên bản.",
                   "Thai Market's menu features dozens of signature Thai dishes: Tom Yum Kung with bold " +
                   "spicy-sour flavor; Pad Thai — the classic stir-fried noodle; renowned green, red and " +
                   "Massaman curries; fresh Som Tum papaya salad; fragrant Satay skewers; and the irresistible " +
                   "Mango Sticky Rice dessert. All dishes use spices imported from Thailand to guarantee " +
                   "authentic flavors.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho mọi dịp — Từ hẹn hò đến tiệc lớn",
                   "Suitable for every occasion — From dates to large banquets")),
            paragraph(ContentSection.STORY,
                of("Với không gian linh hoạt và phong phú, Thai Market Yên Bái là lựa chọn lý tưởng cho " +
                   "hẹn hò đôi lứa (ánh đèn ấm, không gian riêng tư), ăn tối gia đình cuối tuần, tiệc sinh " +
                   "nhật, liên hoan văn phòng hay gặp gỡ đối tác quan trọng. Đặc biệt các phòng riêng khép " +
                   "kín được trang bị máy chiếu và hệ thống âm thanh ánh sáng, thuận tiện cho buổi thuyết " +
                   "trình hoặc kỷ niệm đặc biệt.",
                   "With its flexible and diverse space, Thai Market Yen Bai is the ideal choice for romantic " +
                   "couple dates (warm lighting, private ambience), weekend family dinners, birthday parties, " +
                   "office parties or important partner meetings. The private enclosed rooms are equipped with " +
                   "a projector and sound-light system, convenient for presentations or special celebrations.")),
            heading(ContentSection.STORY,
                of("Dịch vụ tiệc & sự kiện theo yêu cầu",
                   "Custom event & banquet services")),
            paragraph(ContentSection.STORY,
                of("Thai Market Yên Bái nhận tổ chức tiệc theo yêu cầu với dịch vụ trang trí sự kiện chuyên " +
                   "nghiệp, hoa tươi, MC, băng rôn và thiết bị âm thanh ánh sáng. Đội ngũ phục vụ được đào " +
                   "tạo bài bản, am hiểu thực đơn Thái Lan và sẵn sàng tư vấn combo thực đơn theo ngân sách. " +
                   "Nhà hàng có thể phục vụ tiệc đứng (cocktail party) hoặc tiệc ngồi với set menu từ 200.000đ/người.",
                   "Thai Market Yen Bai offers custom event organization with professional event decoration, " +
                   "fresh flowers, MC services, banners and audio-visual equipment. Well-trained staff " +
                   "knowledgeable in Thai cuisine are ready to advise on menu combos by budget. The restaurant " +
                   "can host standing cocktail parties or seated dinners with set menus from 200,000 VND per person.")),
            heading(ContentSection.STORY,
                of("Vị trí & giao thông thuận tiện",
                   "Convenient location & transportation")),
            paragraph(ContentSection.STORY,
                of("Thai Market Yên Bái tọa lạc ngay trung tâm Quận Hải Châu — cách cầu Sông Hàn chỉ khoảng " +
                   "1 km, dễ dàng di chuyển từ mọi điểm trong nội thành Đà Nẵng. Tuyến đường Yên Bái thông " +
                   "thoáng, có chỗ đỗ xe ô tô dọc đường và bãi xe máy miễn phí ngay tại cổng. Gần các khách " +
                   "sạn trung tâm như Novotel, Mercure và khu phố tây An Thượng.",
                   "Thai Market Yen Bai is located right in the center of Hai Chau District — about 1 km from " +
                   "Han River Bridge, easily accessible from all points in Da Nang city. Yen Bai road is " +
                   "unobstructed with street parking and free motorbike parking at the gate. Close to central " +
                   "hotels such as Novotel, Mercure and the An Thuong expat quarter.")),
            heading(ContentSection.STORY,
                of("Tiện ích & chính sách đặt bàn",
                   "Amenities & booking policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được trang bị máy lạnh toàn bộ, WiFi miễn phí, ghế trẻ em và khu vực bàn ngoài " +
                   "trời nhỏ. Thanh toán linh hoạt qua tiền mặt, chuyển khoản, Visa/Master. Xuất hóa đơn VAT " +
                   "theo yêu cầu. Đặt bàn trước được ưu tiên phục vụ — đặt qua điện thoại hoặc hệ thống trực " +
                   "tuyến PasGo. Mức đặt cọc áp dụng với tiệc từ 10 người trở lên để đảm bảo chỗ.",
                   "The restaurant has full air conditioning, free WiFi, children's chairs and a small outdoor " +
                   "terrace. Flexible payment via cash, bank transfer, Visa/Master. VAT invoice issued on " +
                   "request. Advance reservations are given priority service — book by phone or via PasGo online " +
                   "system. Deposit applies for parties of 10 or more to guarantee seats.")),
            mapBlock(ContentSection.STORY, new double[]{108.2162, 16.0663})
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
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng đặt cọc 200.000đ/bàn với tiệc từ 10 người để đảm bảo chỗ. Hoàn cọc khi hủy trước 2 giờ.",
                             "Deposit of 200,000 VND/table required for parties of 10+ to secure booking. Refunded if cancelled 2 hours prior."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi hấp dẫn khi đặt bàn qua PasGo; không áp dụng ngày lễ.",
                                 "Attractive promotions when booking via PasGo; not applicable on public holidays."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi tùy từng thời điểm, liên hệ nhà hàng để biết thêm.", "Promotion details vary; contact restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoice issued on request before payment."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không có phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào nhà hàng.", "Outside food is not permitted inside the restaurant."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào nhà hàng.", "Outside beverages are not permitted inside the restaurant."))
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
                .note(of("Bàn tiêu chuẩn, phù hợp cho gia đình hoặc nhóm nhỏ.", "Standard table for families or small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn VIP khu vực ưu tiên, dịch vụ nâng cao.", "VIP table in premium zone with enhanced service."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 180))
                .note(of("Phòng riêng khép kín với máy chiếu và hệ thống âm thanh ánh sáng.", "Enclosed private room with projector and sound-light system."))
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
            .districtName(of("Quận Hải Châu", "Hai Chau District"))
            .wardName(of("P. Phước Ninh", "Phuoc Ninh Ward"))
            .addressLine(of("Số 124 Đường Yên Bái, P. Phước Ninh, Q. Hải Châu, TP. Đà Nẵng",
                            "124 Yen Bai Street, Phuoc Ninh Ward, Hai Chau District, Da Nang City"))
            .location(new double[]{108.2162, 16.0663})
            .name(of("Thai Market Restaurant - Yên Bái", "Thai Market Restaurant - Yen Bai"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng ẩm thực Thái Lan chính thống tại trung tâm Đà Nẵng — Tom Yum, Pad Thai, Cà ri Thái đậm vị.",
                                 "Authentic Thai cuisine restaurant in central Da Nang — Tom Yum, Pad Thai, Thai curries with bold flavors."))
            .description(of("Thai Market Restaurant Yên Bái tọa lạc tại 124 Yên Bái, Q. Hải Châu, TP. Đà Nẵng — mang ẩm thực " +
                            "Thái Lan chính thống với hương vị nguyên bản từ gia vị nhập khẩu. Sức chứa 200 khách, 3 phòng riêng. " +
                            "Phù hợp hẹn hò, tiệc gia đình, liên hoan công ty. Mở cửa 10:30 – 22:00 hàng ngày.",
                            "Thai Market Restaurant Yen Bai located at 124 Yen Bai, Hai Chau District, Da Nang — serving " +
                            "authentic Thai cuisine with original flavors from imported spices. Seats 200 guests, 3 private rooms. " +
                            "Ideal for dates, family dinners, corporate parties. Open daily 10:30 – 22:00."))
            .phone("0651268108")
            .bookingHotline("0651268108")
            .website("https://pasgo.vn/nha-hang/thai-market-restaurant-yen-bai-5615")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("350000"))
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
