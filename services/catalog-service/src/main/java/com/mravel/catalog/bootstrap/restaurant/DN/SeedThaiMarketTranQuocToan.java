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
public class SeedThaiMarketTranQuocToan {

    @Bean
    CommandLineRunner seedThaiMarketTranQuocToanRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedThaiMarketTranQuocToan] start");
            RestaurantDoc doc = restaurantThaiMarketTranQuocToan();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedThaiMarketTranQuocToan] done");
        };
    }

    // =====================================================================
    //       THAI MARKET - 04 TRẦN QUỐC TOẢN (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantThaiMarketTranQuocToan() {
        String slug = "thai-market-tran-quoc-toan-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-1-normal-2698685224273.webp",
                of("Không gian Thai Market Trần Quốc Toản", "Thai Market Tran Quoc Toan restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-2-normal-2698685324274.webp",
                of("Khu vực bàn ăn tầng trệt", "Ground floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-3-normal-2698686624275.webp",
                of("Không gian ẩm thực Thái trang nhã", "Elegant Thai dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-4-normal-2698686924276.webp",
                of("Trang trí nội thất đậm chất Thái", "Thai-inspired interior decoration"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-5-normal-2698687724277.webp",
                of("Bàn ăn được bài trí tinh tế", "Elegantly arranged dining tables"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-6-normal-2698688424278.webp",
                of("Không gian sáng thoáng ban ngày", "Bright and airy daytime ambience"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-7-normal-2698688524279.webp",
                of("Khu vực bàn nhóm lớn", "Large group dining area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-tran-quoc-toan-side-8-normal-2698689924281.webp",
                of("Phòng riêng khép kín tại Thai Market Trần Quốc Toản", "Enclosed private dining room"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-tran-quoc-toan-side-9-normal-2698690024282.webp",
                of("Không gian tiệc đặc biệt", "Special banquet area"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-tran-quoc-toan-side-10-normal-2698690724283.webp",
                of("Ánh đèn vàng lãng mạn buổi tối", "Romantic golden lighting in the evening"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-tran-quoc-toan-side-13-normal-2698691569692.webp",
                of("Không gian thoáng đãng toàn cảnh", "Panoramic open dining space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-tran-quoc-toan-side-12-normal-2698695669693.webp",
                of("Khu vực bàn đôi lãng mạn", "Romantic couple seating area"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-1.jpg",
                of("Nhà hàng Thai Market Trần Quốc Toản nhìn từ ngoài", "Thai Market Tran Quoc Toan exterior view"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-2.jpg",
                of("Không gian nội thất sang trọng", "Luxurious interior ambience"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-3.jpg",
                of("Không gian bàn tiệc gia đình", "Family banquet space"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-4.jpg",
                of("Khu vực VIP tầng trên", "VIP area on upper floor"), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-5.jpg",
                of("Trang trí đặc trưng Đông Nam Á", "Signature Southeast Asian decoration"), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/Sáng%20%20Mkt/2024/Giới%20thiệu%20NH/Thai%20Market/thai-market-tran-quoc-toan-6.jpg",
                of("Toàn cảnh không gian nhà hàng", "Full panoramic view of the restaurant"), false, 17)
        );

        // ---------- Ảnh menu / bảng giá (16 trang) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0001.jpg",
                of("Menu Thai Market Đà Nẵng trang 1", "Thai Market Da Nang menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0002.jpg",
                of("Menu Thai Market Đà Nẵng trang 2", "Thai Market Da Nang menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0003.jpg",
                of("Menu Thai Market Đà Nẵng trang 3", "Thai Market Da Nang menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0004.jpg",
                of("Menu Thai Market Đà Nẵng trang 4", "Thai Market Da Nang menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0005.jpg",
                of("Menu Thai Market Đà Nẵng trang 5", "Thai Market Da Nang menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0006.jpg",
                of("Menu Thai Market Đà Nẵng trang 6", "Thai Market Da Nang menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0007.jpg",
                of("Menu Thai Market Đà Nẵng trang 7", "Thai Market Da Nang menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0008.jpg",
                of("Menu Thai Market Đà Nẵng trang 8", "Thai Market Da Nang menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0009.jpg",
                of("Menu Thai Market Đà Nẵng trang 9", "Thai Market Da Nang menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0010.jpg",
                of("Menu Thai Market Đà Nẵng trang 10", "Thai Market Da Nang menu page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0011.jpg",
                of("Menu Thai Market Đà Nẵng trang 11", "Thai Market Da Nang menu page 11"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0012.jpg",
                of("Menu Thai Market Đà Nẵng trang 12", "Thai Market Da Nang menu page 12"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0013.jpg",
                of("Menu Thai Market Đà Nẵng trang 13", "Thai Market Da Nang menu page 13"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0014.jpg",
                of("Menu Thai Market Đà Nẵng trang 14", "Thai Market Da Nang menu page 14"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0015.jpg",
                of("Menu Thai Market Đà Nẵng trang 15", "Thai Market Da Nang menu page 15"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20Đà%20Nẵng_page-0016.jpg",
                of("Menu Thai Market Đà Nẵng trang 16", "Thai Market Da Nang menu page 16"), false, 15)
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
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",        of("Hẹn hò đôi lứa", "Romantic date")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("COMPANY_EVENT", of("Sự kiện doanh nghiệp", "Corporate event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business entertainment")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm đông", "Large group gathering")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa tập thể", "Group office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",   of("Thanh lịch, tinh tế", "Elegant and refined")),
            ambience("MODERN",    of("Hiện đại, trẻ trung", "Modern and trendy")),
            ambience("ROMANTIC",  of("Lãng mạn, ấm áp", "Romantic and warm")),
            ambience("VIBRANT",   of("Sôi động về cuối tuần", "Vibrant especially on weekends"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(60)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tom Yum Hải sản", "Seafood Tom Yum"),
                of("Súp chua cay hải sản thập cẩm với tôm, mực, nghêu và nấm tươi, đậm vị chua cay đặc trưng xứ Thái.",
                   "Mixed seafood spicy-sour soup with shrimp, squid, clams and fresh mushrooms, bold with classic Thai flavors."),
                new BigDecimal("150000")),
            signatureDish(of("Pad See Ew", "Pad See Ew (Stir-fried Wide Noodles)"),
                of("Bánh phở xào với thịt bò, cải xanh và trứng trong nước sốt đặc trưng sẫm màu, đậm vị béo ngọt.",
                   "Wide rice noodles stir-fried with beef, Chinese broccoli and egg in rich dark sauce, savory and slightly sweet."),
                new BigDecimal("100000")),
            signatureDish(of("Cà ri đỏ tôm", "Red Curry with Shrimp"),
                of("Tôm tươi béo nấu cà ri đỏ nước cốt dừa sánh đặc, ăn kèm cơm trắng hoặc bánh mì dip.",
                   "Plump fresh shrimp in thick red coconut curry, served with steamed rice or bread for dipping."),
                new BigDecimal("125000")),
            signatureDish(of("Gỏi đu đủ xanh (Som Tum)", "Green Papaya Salad (Som Tum)"),
                of("Đu đủ xanh bào sợi trộn đậu đũa, cà chua bi, đậu phộng rang và nước mắm Thái chua ngọt, ăn kèm tôm khô.",
                   "Shredded green papaya mixed with green beans, cherry tomatoes, roasted peanuts and sweet-sour Thai fish sauce, with dried shrimp."),
                new BigDecimal("80000")),
            signatureDish(of("Thịt bò xào lá húng quế Thái", "Thai Basil Beef Stir-fry"),
                of("Thịt bò thái lát mỏng xào nhanh với lá húng quế Thái, ớt tươi và sốt oyster, thơm phức ăn kèm cơm trắng.",
                   "Thinly-sliced beef flash stir-fried with Thai basil, fresh chili and oyster sauce, aromatic over steamed rice."),
                new BigDecimal("110000")),
            signatureDish(of("Massaman Curry cừu", "Massaman Lamb Curry"),
                of("Thịt cừu hầm mềm trong cà ri Massaman béo ngậy với khoai tây, hành tây và hạt điều, phong cách hoàng gia Thái.",
                   "Tender slow-braised lamb in rich Massaman curry with potatoes, onions and cashews, in royal Thai style."),
                new BigDecimal("145000")),
            signatureDish(of("Satay gà xiên nướng", "Chicken Satay Skewers"),
                of("Thịt gà ướp nghệ và sả nướng trên than, giòn ngoài mềm trong, chấm nước sốt đậu phộng đặc trưng.",
                   "Turmeric and lemongrass-marinated chicken grilled over charcoal, crispy outside tender inside, dipped in signature peanut sauce."),
                new BigDecimal("85000")),
            signatureDish(of("Xôi xoài tráng miệng", "Mango Sticky Rice Dessert"),
                of("Xôi nếp thơm dẻo chan nước cốt dừa ngọt mặn, ăn kèm xoài chín mọng — tráng miệng Thái quốc dân.",
                   "Fragrant sticky rice topped with sweet-salty coconut cream alongside juicy ripe mango — Thailand's beloved dessert."),
                new BigDecimal("60000")),
            signatureDish(of("Lẩu Thái đỏ thập cẩm", "Mixed Thai Red Hotpot"),
                of("Lẩu Thái đỏ cay nồng với tôm, mực, bạch tuộc, rau và bún, nước dùng sôi sùng sục thơm lừng.",
                   "Spicy red Thai hotpot with shrimp, squid, octopus, vegetables and noodles in a rich boiling broth."),
                new BigDecimal("280000")),
            signatureDish(of("Bánh mì chiên bơ tỏi Thái", "Thai Garlic Butter Fried Bread"),
                of("Bánh mì Thái chiên giòn với bơ tỏi thơm phức, ăn kèm súp Tom Yum hoặc cà ri — khởi đầu hoàn hảo cho bữa ăn.",
                   "Crispy Thai fried bread with fragrant garlic butter, paired with Tom Yum soup or curry — a perfect meal starter."),
                new BigDecimal("45000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("THAI_CURRY")
                .name(of("Cà ri Thái", "Thai Curries"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cà ri xanh gà (Green Curry)", "Green Curry Chicken"))
                        .description(of("Gà nấu cà ri xanh nước cốt dừa với rau củ tươi.",
                                       "Chicken in green coconut curry with fresh vegetables."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "curry", "chicken"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Massaman Curry bò", "Massaman Beef Curry"))
                        .description(of("Thịt bò hầm mềm trong cà ri Massaman béo ngậy với khoai tây.",
                                       "Tender braised beef in rich Massaman curry with potatoes."))
                        .priceFrom(new BigDecimal("130000"))
                        .priceTo(new BigDecimal("160000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "curry", "beef"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("THAI_NOODLE_RICE")
                .name(of("Mì & cơm Thái", "Thai Noodles & Rice"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Pad Thai tôm thịt", "Pad Thai with Shrimp & Pork"))
                        .description(of("Mì gạo xào tôm và thịt heo, giá đỗ, trứng và đậu phộng rang.",
                                       "Rice noodles with shrimp, pork, bean sprouts, egg and crushed peanuts."))
                        .priceFrom(new BigDecimal("100000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "noodle", "classic"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cơm chiên Thái hoàng gia", "Royal Thai Fried Rice"))
                        .description(of("Cơm chiên với tôm, trứng, cà chua và rau thơm kiểu hoàng gia Thái.",
                                       "Fried rice with shrimp, egg, tomatoes and herbs in royal Thai style."))
                        .priceFrom(new BigDecimal("95000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("thai", "rice", "fried"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe trên phố Trần Quốc Toản và các đường lân cận", "Street parking on Tran Quoc Toan and nearby streets"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy ngay cổng nhà hàng số 04 Trần Quốc Toản", "Motorbike parking at the restaurant gate, 04 Tran Quoc Toan"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe miễn phí dành cho khách đến dùng bữa tại nhà hàng.", "Free parking provided for dining guests."))
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
            "MC_SERVICE",
            "SET_LUNCH",
            "SMOKING_AREA"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Thai Market Trần Quốc Toản — Ẩm thực Thái chính thống ngay trung tâm phố cổ Đà Nẵng",
                   "Thai Market Tran Quoc Toan — Authentic Thai cuisine in Da Nang's historic city center")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại số 04 Trần Quốc Toản, Phường Hải Châu 1, Quận Hải Châu, TP. Đà Nẵng, " +
                   "Thai Market Trần Quốc Toản là một trong những chi nhánh nổi bật nhất của chuỗi nhà hàng " +
                   "Thai Market — thương hiệu ẩm thực Thái Lan đầu tiên và uy tín nhất tại Đà Nẵng. Nhà hàng " +
                   "được xây dựng với triết lý mang hương vị Thái chính thống đến gần hơn với thực khách " +
                   "Việt Nam, sử dụng gia vị và nguyên liệu nhập khẩu trực tiếp từ Thái Lan.",
                   "Located at 04 Tran Quoc Toan Street, Hai Chau 1 Ward, Hai Chau District, Da Nang City, " +
                   "Thai Market Tran Quoc Toan is one of the most prominent branches of the Thai Market " +
                   "restaurant chain — Da Nang's first and most reputable Thai cuisine brand. The restaurant " +
                   "is built on a philosophy of bringing authentic Thai flavors closer to Vietnamese diners, " +
                   "using spices and ingredients imported directly from Thailand.")),
            heading(ContentSection.STORY,
                of("Không gian 2 tầng sang trọng — Sức chứa 250 khách",
                   "Luxurious 2-floor space — Capacity for 250 guests")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng trải rộng trên 2 tầng với sức chứa khoảng 250 khách. Tầng 1 gồm " +
                   "khu vực bàn thông thoáng với ánh sáng tự nhiên và góc check-in đặc trưng. Tầng 2 có " +
                   "4 phòng riêng khép kín sức chứa 20–40 khách, trang bị máy chiếu và hệ thống âm thanh " +
                   "ánh sáng cao cấp. Nội thất kết hợp gỗ tự nhiên, vải thổ cẩm Thái và ánh đèn vàng ấm " +
                   "tạo nên không khí vừa sang trọng vừa gần gũi đặc trưng của xứ Thái.",
                   "The restaurant spans 2 floors with a capacity of approximately 250 guests. The ground " +
                   "floor features an open dining area with natural lighting and signature photo corners. " +
                   "The upper floor has 4 enclosed private rooms seating 20–40 guests, equipped with " +
                   "projectors and premium audio-visual systems. The interior blends natural wood, Thai " +
                   "brocade fabric and warm golden lighting to create an ambience that is both luxurious " +
                   "and inviting, characteristic of Thailand.")),
            heading(ContentSection.STORY,
                of("Thực đơn phong phú — Hàng trăm món Thái chính thống",
                   "Extensive menu — Hundreds of authentic Thai dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Thai Market Trần Quốc Toản gồm hàng trăm món chia thành nhiều phần: khai vị " +
                   "(nem cuốn Thái, Satay xiên nướng), súp (Tom Yum, Tom Kha), salad (Som Tum), món chính " +
                   "(Pad Thai, Pad See Ew, cơm chiên hoàng gia), cà ri (xanh, đỏ, vàng, Massaman, Penang), " +
                   "hải sản, lẩu Thái và tráng miệng (Mango Sticky Rice, chè Thái). Giá trung bình từ " +
                   "250.000 – 400.000đ/người, xứng đáng với chất lượng ẩm thực được kiểm soát nghiêm ngặt.",
                   "Thai Market Tran Quoc Toan's menu features hundreds of dishes across multiple categories: " +
                   "appetizers (Thai spring rolls, Satay skewers), soups (Tom Yum, Tom Kha), salads " +
                   "(Som Tum), mains (Pad Thai, Pad See Ew, royal fried rice), curries (green, red, yellow, " +
                   "Massaman, Penang), seafood, Thai hotpot and desserts (Mango Sticky Rice, Thai desserts). " +
                   "Average spend of 250,000 – 400,000 VND per person, well justified by the strictly " +
                   "quality-controlled Thai cuisine.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho mọi dịp quan trọng",
                   "Suitable for all important occasions")),
            paragraph(ContentSection.STORY,
                of("Thai Market Trần Quốc Toản là lựa chọn hoàn hảo cho những bữa tối lãng mạn đôi lứa, " +
                   "kỷ niệm ngày cưới, tiệc sinh nhật ấm cúng hay các buổi liên hoan văn phòng sôi động. " +
                   "Đặc biệt, các phòng tiệc riêng tầng 2 có thể phục vụ hội nghị nhỏ, thuyết trình đối tác " +
                   "hoặc tiệc khách hàng với dịch vụ chuyên nghiệp. Nhà hàng cũng nhận tổ chức tiệc đặt trước " +
                   "với thực đơn và trang trí theo yêu cầu.",
                   "Thai Market Tran Quoc Toan is the perfect choice for romantic couple evenings, wedding " +
                   "anniversaries, warm birthday parties or lively office celebrations. The private banquet " +
                   "rooms on the upper floor can accommodate small meetings, partner presentations or client " +
                   "dinners with professional service. The restaurant also accepts advance party bookings with " +
                   "custom menus and decorations.")),
            heading(ContentSection.STORY,
                of("Dịch vụ đặt tiệc trọn gói — Sự kiện doanh nghiệp",
                   "Full-service event packages — Corporate events")),
            paragraph(ContentSection.STORY,
                of("Thai Market Trần Quốc Toản cung cấp dịch vụ tổ chức tiệc trọn gói cho doanh nghiệp và " +
                   "cá nhân: set menu từ 350.000đ/người, trang trí theo chủ đề (hoa tươi, bóng bay, banner), " +
                   "MC chuyên nghiệp, nhân viên phục vụ riêng và chụp ảnh lưu niệm. Dịch vụ âm thanh ánh " +
                   "sáng hỗ trợ cho hội nghị, ra mắt sản phẩm hoặc tiệc gala nhỏ. Liên hệ trước ít nhất " +
                   "3 ngày để chuẩn bị chu đáo.",
                   "Thai Market Tran Quoc Toan provides complete event packages for businesses and individuals: " +
                   "set menus from 350,000 VND per person, themed decorations (fresh flowers, balloons, " +
                   "banners), professional MC, dedicated service staff and souvenir photography. " +
                   "Audio-visual support for conferences, product launches or small gala dinners. " +
                   "Contact at least 3 days in advance for thorough preparation.")),
            heading(ContentSection.STORY,
                of("Vị trí trung tâm — Dễ dàng kết hợp tham quan Đà Nẵng",
                   "Central location — Easy to combine with Da Nang sightseeing")),
            paragraph(ContentSection.STORY,
                of("Số 04 Trần Quốc Toản tọa lạc ngay trung tâm Quận Hải Châu — cách Cầu Rồng khoảng 800m " +
                   "và cách bờ biển Mỹ Khê khoảng 3km. Thuận tiện kết hợp sau khi tham quan Cầu Rồng, " +
                   "Cầu Tình Yêu hoặc dọc bờ sông Hàn. Tuyến đường Trần Quốc Toản thông thoáng, bãi đỗ xe " +
                   "máy miễn phí và đỗ xe ô tô dọc đường. Gần nhiều khách sạn trung tâm và văn phòng công ty.",
                   "04 Tran Quoc Toan is located right in the center of Hai Chau District — approximately " +
                   "800m from Dragon Bridge and 3km from My Khe Beach. Convenient to combine with visits to " +
                   "Dragon Bridge, Love Bridge or Han River promenade. Tran Quoc Toan road is open and " +
                   "accessible with free motorbike parking and street parking for cars. Close to many central " +
                   "hotels and corporate offices.")),
            heading(ContentSection.STORY,
                of("Chính sách & lý do nên chọn Thai Market Trần Quốc Toản",
                   "Policy & why choose Thai Market Tran Quoc Toan")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước qua điện thoại và PasGo. Tiệc từ 10 người cần đặt cọc " +
                   "trước để đảm bảo chỗ. Chấp nhận thanh toán đa dạng: tiền mặt, chuyển khoản, " +
                   "Visa/Master/ATM. Xuất hóa đơn VAT theo yêu cầu. Thai Market Trần Quốc Toản tự hào là " +
                   "nhà hàng Thái đầu tiên tại Đà Nẵng sở hữu thực đơn đầy đủ nhất, đội ngũ đầu bếp được " +
                   "đào tạo tại Thái Lan và cam kết trải nghiệm ẩm thực Thái chân thực nhất.",
                   "The restaurant accepts advance reservations by phone and PasGo. Parties of 10 or more " +
                   "require a deposit to secure seating. Multiple payment options: cash, bank transfer, " +
                   "Visa/Master/ATM. VAT invoice issued on request. Thai Market Tran Quoc Toan is proud to " +
                   "be Da Nang's first Thai restaurant with the most complete menu, a culinary team trained " +
                   "in Thailand, and a commitment to the most authentic Thai dining experience.")),
            mapBlock(ContentSection.STORY, new double[]{108.2199, 16.0674})
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
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc 300.000đ/bàn cho tiệc từ 10 người. Hoàn cọc khi hủy trước 2 giờ so với giờ đặt bàn.",
                             "300,000 VND/table deposit for parties of 10+. Refunded if cancelled 2 hours before reservation time."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi khi đặt bàn qua PasGo và các kênh hợp tác; không áp dụng ngày lễ tết.",
                                 "Promotions available when booking via PasGo and partner channels; not applicable on public holidays."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Ưu đãi thay đổi theo từng thời điểm; liên hệ nhà hàng để biết thêm.", "Promotions vary by period; contact restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT được xuất theo yêu cầu trước khi khách thanh toán.", "VAT invoice issued on request before payment."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không tính phí phục vụ riêng.", "No separate service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào.", "Outside beverages are not permitted."))
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
                .totalTables(30)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn phù hợp nhóm nhỏ và gia đình.", "Standard table for small groups and families."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn VIP khu vực ưu tiên với dịch vụ nâng cao.", "VIP table in priority zone with enhanced service."))
                .build(),
            TableType.builder()
                .id("pr-40")
                .name(of("Phòng tiệc riêng 40 khách", "Private banquet room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(4)
                .depositPrice(new BigDecimal("600000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 180))
                .note(of("Phòng riêng khép kín tầng 2 với máy chiếu, âm thanh ánh sáng, phù hợp tiệc lớn và sự kiện.",
                         "Enclosed private room on 2nd floor with projector and audio-visual, suitable for large parties and events."))
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
            .wardName(of("P. Hải Châu 1", "Hai Chau 1 Ward"))
            .addressLine(of("Số 04 Trần Quốc Toản, P. Hải Châu 1, Q. Hải Châu, TP. Đà Nẵng",
                            "04 Tran Quoc Toan Street, Hai Chau 1 Ward, Hai Chau District, Da Nang City"))
            .location(new double[]{108.2199, 16.0674})
            .name(of("Thai Market - Trần Quốc Toản", "Thai Market - Tran Quoc Toan"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng ẩm thực Thái Lan đầu tiên tại Đà Nẵng — thực đơn phong phú với 100+ món Thái chính thống, gia vị nhập khẩu.",
                                 "Da Nang's first Thai restaurant — extensive menu with 100+ authentic Thai dishes, imported spices."))
            .description(of("Thai Market Trần Quốc Toản tại 04 Trần Quốc Toản, Hải Châu, Đà Nẵng — chuỗi nhà hàng Thái uy tín nhất " +
                            "thành phố, sức chứa 250 khách, 4 phòng riêng. Giá trung bình 250.000–400.000đ/người. Mở cửa 10:00 – 22:30.",
                            "Thai Market Tran Quoc Toan at 04 Tran Quoc Toan, Hai Chau, Da Nang — the city's most reputable Thai chain, " +
                            "seats 250 guests, 4 private rooms. Average spend 250,000–400,000 VND/person. Open daily 10:00 – 22:30."))
            .phone("0660851082")
            .bookingHotline("0660851082")
            .website("https://pasgo.vn/nha-hang/thai-market-tran-quoc-toan-2420")
            .minPricePerPerson(new BigDecimal("250000"))
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
