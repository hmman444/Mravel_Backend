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
public class SeedThaiMarketBinhMinh5 {

    @Bean
    CommandLineRunner seedThaiMarketBinhMinh5Runner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedThaiMarketBinhMinh5] start");
            RestaurantDoc doc = restaurantThaiMarketBinhMinh5();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedThaiMarketBinhMinh5] done");
        };
    }

    // =====================================================================
    //      THAI MARKET BÌNH MINH 5 (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantThaiMarketBinhMinh5() {
        String slug = "thai-market-binh-minh-5-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-5-normal-2698732469699.webp",
                of("Không gian Thai Market Bình Minh 5", "Thai Market Binh Minh 5 dining interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-1-normal-2698713369695.webp",
                of("Khu vực bàn ăn kiểu Thái", "Thai-style dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-2-normal-2698713469696.webp",
                of("Không gian tươi sáng hiện đại", "Bright and modern ambiance"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-3-normal-2698717769697.webp",
                of("Trang trí đặc trưng văn hóa Thái Lan", "Distinctive Thai cultural decor"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-4-normal-2698717869698.webp",
                of("Khu vực bếp mở nhộn nhịp", "Bustling open kitchen area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-6-normal-2698727369700.webp",
                of("Bàn ngồi thoải mái, ánh sáng tự nhiên", "Comfortable seating with natural light"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-side-7-normal-2698727469701.webp",
                of("Không gian ăn tối lãng mạn", "Romantic evening dining space"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-binh-minh-5-side-8-normal-2698728869703.webp",
                of("Khu vực bàn nhóm đông người", "Group dining area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-binh-minh-5-side-9-normal-2698729569704.webp",
                of("Món Tom Yum Kung nóng hổi", "Hot and sour Tom Yum Kung soup"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-binh-minh-5-side-10-normal-2698731669707.webp",
                of("Pad Thai sợi gạo thơm ngon", "Fragrant Pad Thai rice noodles"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-binh-minh-5-side-11-normal-2698731769708.webp",
                of("Cà ri xanh Thái đậm đà", "Rich Thai green curry"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market-binh-minh-5-side-12-normal-2698732369709.webp",
                of("Mặt tiền Thai Market Bình Minh 5", "Thai Market Binh Minh 5 facade"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/Thai%20Market/thai-market-binh-minh-5-anh-1.jpg",
                of("Ẩm thực Thái phong phú tại Bình Minh 5", "Rich Thai cuisine at Binh Minh 5"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/Thai%20Market/thai-market-binh-minh-5-anh-2.jpg",
                of("Không gian mới tại chi nhánh Bình Minh 5", "New space at Binh Minh 5 branch"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2024/Gi%E1%BB%9Bi%20thi%E1%BB%87u%20NH/Thai%20Market/thai-market-binh-minh-5-anh-3.jpg",
                of("Món ăn Thái tươi ngon hấp dẫn", "Fresh and appealing Thai dishes"), false, 14)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0001.jpg",
                of("Menu Thai Market Đà Nẵng trang 1", "Thai Market Da Nang menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0002.jpg",
                of("Menu Thai Market Đà Nẵng trang 2", "Thai Market Da Nang menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0003.jpg",
                of("Menu Thai Market Đà Nẵng trang 3", "Thai Market Da Nang menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0004.jpg",
                of("Menu Thai Market Đà Nẵng trang 4", "Thai Market Da Nang menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0005.jpg",
                of("Menu Thai Market Đà Nẵng trang 5", "Thai Market Da Nang menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0006.jpg",
                of("Menu Thai Market Đà Nẵng trang 6", "Thai Market Da Nang menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0007.jpg",
                of("Menu Thai Market Đà Nẵng trang 7", "Thai Market Da Nang menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0008.jpg",
                of("Menu Thai Market Đà Nẵng trang 8", "Thai Market Da Nang menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0009.jpg",
                of("Menu Thai Market Đà Nẵng trang 9", "Thai Market Da Nang menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0010.jpg",
                of("Menu Thai Market Đà Nẵng trang 10", "Thai Market Da Nang menu page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0011.jpg",
                of("Menu Thai Market Đà Nẵng trang 11", "Thai Market Da Nang menu page 11"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0012.jpg",
                of("Menu Thai Market Đà Nẵng trang 12", "Thai Market Da Nang menu page 12"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0013.jpg",
                of("Menu Thai Market Đà Nẵng trang 13", "Thai Market Da Nang menu page 13"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0014.jpg",
                of("Menu Thai Market Đà Nẵng trang 14", "Thai Market Da Nang menu page 14"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0015.jpg",
                of("Menu Thai Market Đà Nẵng trang 15", "Thai Market Da Nang menu page 15"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/Thai%20Market/Menu%20Thai%20Market%20%C4%90%C3%A0%20N%E1%BA%B5ng_page-0016.jpg",
                of("Menu Thai Market Đà Nẵng trang 16 — Đồ uống", "Thai Market Da Nang menu page 16 — Beverages"), false, 15)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("THAI")
                .name(of("Thái Lan", "Thai"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực châu Á kết hợp", "Asian fusion"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
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
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("COMPANY_PARTY", of("Liên hoan nhỏ", "Small company party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm", "Group meeting")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động, nhộn nhịp kiểu chợ Thái", "Vibrant Thai market-style atmosphere")),
            ambience("MODERN",   of("Hiện đại, trẻ trung", "Modern and youthful")),
            ambience("COZY",     of("Thoải mái, ấm cúng", "Comfortable and cozy")),
            ambience("ELEGANT",  of("Thanh lịch với nét Thái Lan", "Elegant Thai-inspired refinement"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(20)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tom Yum Kung nước dùng chua cay", "Tom Yum Kung Hot & Sour Soup"),
                of("Lẩu Tom Yum tôm cỡ lớn nấu cùng sả, lá chanh kaffir, galangal và nước cốt dừa béo ngậy — " +
                   "chua cay mặn ngọt hòa quyện hoàn hảo. Đặc trưng nhất của ẩm thực Thái Lan.",
                   "Large shrimp Tom Yum hotpot cooked with lemongrass, kaffir lime leaves, galangal and rich " +
                   "coconut milk — a perfect harmony of sour, spicy, salty and sweet. The ultimate Thai culinary icon."),
                new BigDecimal("220000")),
            signatureDish(of("Pad Thai tôm sợi gạo xào", "Pad Thai Stir-fried Rice Noodles with Shrimp"),
                of("Sợi gạo xào cùng tôm tươi, giá đỗ, hành lá, trứng và đậu phộng rang trong sốt pad thai " +
                   "ngọt chua đặc trưng — vắt chanh và rau thơm ăn kèm bên.",
                   "Rice noodles stir-fried with fresh shrimp, bean sprouts, green onion, egg and roasted " +
                   "peanuts in signature sweet-sour pad thai sauce — served with lime wedge and fresh herbs."),
                new BigDecimal("145000")),
            signatureDish(of("Cà ri xanh Thái gà nước cốt dừa", "Thai Green Curry with Coconut Milk and Chicken"),
                of("Gà tươi om cùng paste cà ri xanh và nước cốt dừa béo ngậy, thêm cà tím Thái và lá húng quế " +
                   "Thái thơm phức. Ăn kèm cơm trắng dẻo.",
                   "Fresh chicken simmered in green curry paste and rich coconut milk, with Thai eggplant and " +
                   "fragrant Thai basil. Served with fluffy steamed rice."),
                new BigDecimal("165000")),
            signatureDish(of("Som Tum đu đủ xanh truyền thống", "Traditional Green Papaya Som Tum Salad"),
                of("Gỏi đu đủ xanh bào sợi trộn cùng tôm khô, cà chua bi, đậu đũa, tỏi, ớt, đường thốt nốt " +
                   "và nước mắm Thái — giòn mát, chua cay ngọt quyến rũ.",
                   "Shredded green papaya salad mixed with dried shrimp, cherry tomatoes, long beans, garlic, " +
                   "chili, palm sugar and Thai fish sauce — crunchy, cool and seductively sour-spicy-sweet."),
                new BigDecimal("95000")),
            signatureDish(of("Cánh gà chiên nước mắm Thái", "Thai Fish Sauce Fried Chicken Wings"),
                of("Cánh gà chiên vàng giòn rụm tẩm sốt nước mắm Thái ngọt mặn, hành tím phi vàng và ớt sấy — " +
                   "không thể cưỡng lại một khi đã thử.",
                   "Crispy golden fried chicken wings glazed in sweet-salty Thai fish sauce with fried shallots " +
                   "and dried chili — impossible to resist once tried."),
                new BigDecimal("130000")),
            signatureDish(of("Cơm chiên dứa Thái kiểu hoàng gia", "Thai Royal Pineapple Fried Rice"),
                of("Cơm chiên trong vỏ dứa tươi với tôm, raisins, điều rang, trứng, nghệ và gia vị Thái — " +
                   "trình bày đẹp mắt trong quả dứa bổ đôi độc đáo.",
                   "Fried rice in fresh pineapple shell with shrimp, raisins, cashews, egg, turmeric and Thai " +
                   "spices — beautifully presented in a unique halved pineapple."),
                new BigDecimal("160000")),
            signatureDish(of("Massaman curry bò hầm", "Beef Massaman Curry Stew"),
                of("Thịt bò hầm mềm tan trong cà ri Massaman đậm vị cùng khoai tây, hành tím, đậu phộng rang " +
                   "và nước cốt dừa — hương vị Hồi giáo phía nam Thái Lan đặc trưng.",
                   "Melt-soft beef braised in rich Massaman curry with potatoes, shallots, roasted peanuts and " +
                   "coconut milk — distinctive southern Thai Muslim-influenced flavor."),
                new BigDecimal("185000")),
            signatureDish(of("Mango sticky rice tráng miệng", "Mango Sticky Rice Dessert"),
                of("Xôi nếp nấu nước cốt dừa béo ngậy ăn kèm xoài Thái chín mật, rưới nước cốt dừa đặc và " +
                   "vừng rang — tráng miệng quốc dân của Thái Lan.",
                   "Sticky rice cooked in rich coconut milk served with ripe honey mango, drizzled with thick " +
                   "coconut cream and toasted sesame — Thailand's most beloved national dessert."),
                new BigDecimal("70000")),
            signatureDish(of("Tôm hấp sả kiểu Thái", "Thai-style Steamed Lemongrass Shrimp"),
                of("Tôm sú tươi hấp cùng sả và lá chanh, chấm nước chấm chua cay đặc biệt pha từ nước mắm, " +
                   "chanh, tỏi và ớt băm — tươi ngon và thanh mát.",
                   "Fresh tiger prawns steamed with lemongrass and lime leaves, dipped in special sour-spicy " +
                   "sauce made from fish sauce, lime, garlic and minced chili — fresh and refreshing."),
                new BigDecimal("200000")),
            signatureDish(of("Lẩu Thái hai ngăn đặc biệt", "Special Two-Compartment Thai Hotpot"),
                of("Nồi lẩu hai ngăn: một ngăn Tom Yum chua cay và một ngăn nước dùng gà thanh ngọt, " +
                   "phục vụ kèm hải sản tươi, thịt bò Mỹ và rau củ Thái đặc sắc.",
                   "Two-compartment hotpot: one side Tom Yum sour-spicy and one side clear sweet chicken broth, " +
                   "served with fresh seafood, US beef and distinctive Thai vegetables."),
                new BigDecimal("350000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("LAU_THAI")
                .name(of("Lẩu Thái & súp", "Thai Hotpot & Soups"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tom Yum Kung (2 người)", "Tom Yum Kung (2 pax)"))
                        .description(of("Lẩu Tom Yum tôm tươi cỡ lớn, chua cay đặc trưng Thái.",
                                       "Large fresh shrimp Tom Yum hotpot, distinctively Thai sour-spicy."))
                        .priceFrom(new BigDecimal("220000"))
                        .priceTo(new BigDecimal("280000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "Thai", "seafood"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu Thái 2 ngăn đặc biệt", "Special two-compartment Thai hotpot"))
                        .description(of("Tom Yum + nước dùng gà, kèm hải sản và thịt bò Mỹ.",
                                       "Tom Yum + chicken broth, with seafood and US beef."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("450000"))
                        .unit(of("nồi", "pot"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "Thai", "combo"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MON_CHINH_THAI")
                .name(of("Món chính Thái Lan", "Thai Main Dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Pad Thai tôm", "Pad Thai with shrimp"))
                        .description(of("Sợi gạo xào tôm, giá đỗ, đậu phộng rang, trứng, sốt pad thai.",
                                       "Rice noodles stir-fried with shrimp, bean sprouts, peanuts, egg, pad thai sauce."))
                        .priceFrom(new BigDecimal("145000"))
                        .priceTo(new BigDecimal("165000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("noodles", "Thai", "stir-fry"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cà ri xanh Thái gà", "Thai green curry chicken"))
                        .description(of("Gà om cà ri xanh nước cốt dừa, cà tím Thái, lá húng quế.",
                                       "Chicken in green coconut curry, Thai eggplant, Thai basil."))
                        .priceFrom(new BigDecimal("165000"))
                        .priceTo(new BigDecimal("185000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("curry", "Thai", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Không có bãi đỗ ô tô riêng; đỗ xe trên đường Bình Minh 5",
                                   "No private car parking; street parking available on Binh Minh 5"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy trước cửa nhà hàng", "Motorbike parking in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng nằm tại khu Bình Thuận, Hải Châu; gần các tuyến đường lớn, thuận tiện di chuyển.",
                      "Restaurant in Binh Thuan area, Hai Chau District; near major roads, conveniently accessible."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "FREE_MOTORBIKE_PARKING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE_SERVICE",
            "SET_LUNCH",
            "SMOKING_AREA",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "TAKEAWAY",
            "ONLINE_ORDER"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Thai Market Bình Minh 5 — Ẩm thực Thái Lan chính thống tại Hải Châu",
                   "Thai Market Binh Minh 5 — Authentic Thai Cuisine in Hai Chau")),
            paragraph(ContentSection.STORY,
                of("Thai Market Bình Minh 5 tọa lạc tại 43 Bình Minh 5, Phường Bình Thuận, Quận Hải Châu, " +
                   "TP. Đà Nẵng — là chi nhánh mới nhất của thương hiệu Thai Market nổi tiếng tại Đà Nẵng. " +
                   "Với slogan \"Ẩm thực Thái Lan đích thực\", nhà hàng mang đến những công thức nấu ăn Thái " +
                   "chuẩn vị từ đầu bếp có kinh nghiệm, sử dụng nguyên liệu nhập khẩu và gia vị Thái chính hãng.",
                   "Thai Market Binh Minh 5 is located at 43 Binh Minh 5, Binh Thuan Ward, Hai Chau District, " +
                   "Da Nang City — the newest branch of the popular Thai Market brand in Da Nang. With the " +
                   "slogan 'Authentic Thai Cuisine,' the restaurant delivers authentic Thai recipes from " +
                   "experienced chefs using imported ingredients and genuine Thai spices.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được thiết kế theo phong cách Thai Market đặc trưng — màu sắc tươi sáng gợi lên " +
                   "hình ảnh chợ đêm Thái Lan, bàn ghế hiện đại thoải mái và ánh đèn vàng ấm áp. Sức chứa " +
                   "khoảng 150 khách với khu vực ăn chính và 2 phòng riêng sức chứa tối đa 20 khách/phòng. " +
                   "Không gian phù hợp cho hẹn hò đôi, nhóm gia đình và tiệc công ty quy mô vừa.",
                   "The restaurant is designed in the distinctive Thai Market style — bright colors evoking " +
                   "Thai night market imagery, modern comfortable seating and warm golden lighting. Capacity " +
                   "approximately 150 guests with main dining area and 2 private rooms each holding up to 20 " +
                   "guests. Space suits romantic dates, family groups and medium-scale company events.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Thai Market phong phú hơn 80 món bao gồm đầy đủ tinh hoa ẩm thực Thái: Lẩu Tom " +
                   "Yum Kung chua cay nổi tiếng; Pad Thai tôm sợi gạo xào; cà ri xanh/đỏ/vàng nước cốt dừa; " +
                   "Som Tum đu đủ xanh giòn mát; Cánh gà chiên nước mắm Thái; Cơm chiên dứa kiểu hoàng gia; " +
                   "Massaman curry bò h��m và Mango sticky rice tráng miệng. Giá từ 200.000 – 350.000 đồng/người.",
                   "Thai Market's menu features over 80 dishes covering the full essence of Thai cuisine: the " +
                   "famous Tom Yum Kung sour-spicy hotpot; Pad Thai stir-fried rice noodles; green/red/yellow " +
                   "coconut curry; crunchy cool green papaya Som Tum; Thai fish sauce fried chicken wings; " +
                   "royal pineapple fried rice; braised beef Massaman curry and Mango sticky rice dessert. " +
                   "Price 200,000 – 350,000 VND per person.")),
            heading(ContentSection.STORY,
                of("Nguyên liệu nhập khẩu — chuẩn vị Thái Lan",
                   "Imported ingredients — guaranteed authentic Thai flavor")),
            paragraph(ContentSection.STORY,
                of("Thai Market cam kết sử dụng các nguyên liệu và gia vị Thái nhập khẩu chính hãng: paste cà " +
                   "ri xanh/đỏ/vàng từ Thái Lan, nước cốt dừa Aroy-D, nước mắm Thái Tiparos, galangal, lá " +
                   "kaffir lime tươi, sả và ớt chim Thái. Tôm sú và hải sản tươi mỗi ngày từ chợ đầu mối Đà " +
                   "Nẵng. Bếp trưởng nhiều năm kinh nghiệm với ẩm thực Thái Lan đảm bảo chuẩn vị như ăn tại " +
                   "Bangkok.",
                   "Thai Market commits to using genuine imported Thai ingredients and spices: green/red/yellow " +
                   "curry paste from Thailand, Aroy-D coconut milk, Tiparos Thai fish sauce, galangal, fresh " +
                   "kaffir lime leaves, lemongrass and Thai bird chili. Fresh tiger prawns and seafood daily " +
                   "from Da Nang wholesale market. Executive chef with years of Thai cuisine experience ensures " +
                   "authentic flavor as if dining in Bangkok.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Thai Market Bình Minh 5 là lựa chọn lý tưởng cho những buổi hẹn hò lãng mạn với không gian " +
                   "ấm áp và thực đơn đa dạng. Cũng phù hợp cho gia đình có trẻ nhỏ (thực đơn nhiều món không " +
                   "cay), nhóm bạn thích khám phá ẩm thực nước ngoài, tiệc sinh nhật nhỏ trong phòng riêng và " +
                   "bữa trưa công sở. Khách du lịch nước ngoài yêu thích ẩm thực Thái Lan cũng sẽ tìm thấy " +
                   "hương vị quen thuộc tại đây.",
                   "Thai Market Binh Minh 5 is the ideal choice for romantic dates with warm ambiance and " +
                   "diverse menu. Also suitable for families with young children (many non-spicy options), " +
                   "friend groups exploring international cuisine, small birthday parties in private rooms and " +
                   "office lunch. Foreign tourists who love Thai cuisine will find familiar flavors here.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích",
                   "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Thai Market Bình Minh 5 cung cấp dịch vụ đặt bàn trực tuyến và giao hàng tận nơi. Set " +
                   "lunch đặc biệt từ 10:00 – 14:00 với mức giá ưu đãi. Phòng riêng có thể trang trí sinh nhật " +
                   "theo yêu cầu với dịch vụ bánh kem. Điều hòa mát lạnh toàn bộ, wifi miễn phí, thanh toán " +
                   "Visa/Master và xuất hóa đơn VAT.",
                   "Thai Market Binh Minh 5 offers online table booking and home delivery. Special set lunch " +
                   "from 10:00 – 14:00 at discounted rates. Private rooms can be decorated for birthdays on " +
                   "request with cake service. Full air conditioning, free WiFi, Visa/Master payments accepted " +
                   "and VAT invoices available.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tại 43 Bình Minh 5, Phường Bình Thuận, Quận Hải Châu — khu vực trung tâm Đà Nẵng, " +
                   "gần Vincom Plaza và các khách sạn lớn. Cách cầu Rồng khoảng 2 km. Thuận tiện di chuyển " +
                   "bằng taxi, Grab. Bãi đỗ xe máy miễn phí trước cửa.",
                   "Restaurant at 43 Binh Minh 5, Binh Thuan Ward, Hai Chau District — central Da Nang area, " +
                   "near Vincom Plaza and major hotels. About 2 km from Dragon Bridge. Conveniently accessible " +
                   "by taxi, Grab. Free motorbike parking in front.")),
            heading(ContentSection.STORY,
                of("Thương hiệu Thai Market tại Đà Nẵng",
                   "Thai Market brand in Da Nang")),
            paragraph(ContentSection.STORY,
                of("Thai Market là chuỗi nhà hàng Thái Lan uy tín với nhiều chi nhánh tại Đà Nẵng (Nguyễn Văn " +
                   "Thoại, Trần Quốc Toản, Yên Bái...). Chi nhánh Bình Minh 5 là địa điểm mới nhất, được đầu " +
                   "tư không gian hiện đại và thực đơn cập nhật liên tục. Thương hiệu đã được hơn 10.000 thực " +
                   "khách Đà Nẵng tin tưởng lựa chọn qua các năm hoạt động.",
                   "Thai Market is a reputable Thai restaurant chain with multiple branches in Da Nang (Nguyen " +
                   "Van Thoai, Tran Quoc Toan, Yen Bai...). The Binh Minh 5 branch is the newest location, " +
                   "with modern space investment and continuously updated menu. The brand has been trusted by " +
                   "over 10,000 Da Nang diners over the years.")),
            mapBlock(ContentSection.STORY, new double[]{108.2228232, 16.0554643})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1",  "Promotions not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotions not applicable on Feb 14")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotions not applicable on Apr 30")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9",  "Promotions not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm giao thừa", "Promotions not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho đặt bàn thông thường.", "No deposit for regular table bookings."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi đặt bàn qua PasGo; set lunch giảm giá.",
                                 "Promotions via PasGo booking; discounted set lunch."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi theo từng thời điểm.", "Promotion details vary by period."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice issued on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không thu phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không cho mang thức ăn từ ngoài vào (trừ bánh sinh nhật).",
                                  "Outside food not permitted (except birthday cakes)."))
            .outsideDrinkPolicy(of("Không cho mang đồ uống từ ngoài vào.", "Outside beverages not permitted."))
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
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Loại bàn ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-2-std")
                .name(of("Bàn đôi 2 ghế", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(15)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi lý tưởng cho hẹn hò.", "Ideal table for two."))
                .build(),
            TableType.builder()
                .id("tb-4-grp")
                .name(of("Bàn nhóm 4 ghế", "Group table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nhóm nhỏ cho gia đình và bạn bè.", "Small group table for families and friends."))
                .build(),
            TableType.builder()
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(8)
                .maxPeople(20)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng phù hợp sinh nhật và tiệc nhỏ.",
                         "Private room suitable for birthdays and small parties."))
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
            .wardName(of("P. Bình Thuận", "Binh Thuan Ward"))
            .addressLine(of("43 Bình Minh 5, P. Bình Thuận, Q. Hải Châu, TP. Đà Nẵng",
                            "43 Binh Minh 5, Binh Thuan Ward, Hai Chau District, Da Nang City"))
            .location(new double[]{108.2228232, 16.0554643})
            .name(of("Thai Market Bình Minh 5", "Thai Market Binh Minh 5"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Ẩm thực Thái Lan chính thống tại Hải Châu — Tom Yum, Pad Thai, cà ri xanh và Mango Sticky Rice.",
                                 "Authentic Thai cuisine in Hai Chau — Tom Yum, Pad Thai, green curry and Mango Sticky Rice."))
            .description(of("Thai Market Bình Minh 5 tại 43 Bình Minh 5, Q. Hải Châu, TP. Đà Nẵng phục vụ hơn 80 món Thái chuẩn vị " +
                            "với nguyên liệu nhập khẩu. Không gian 150 khách, có phòng riêng. Mở cửa 10:00 – 22:30 hàng ngày. " +
                            "Giá từ 200.000 – 350.000 đồng/người.",
                            "Thai Market Binh Minh 5 at 43 Binh Minh 5, Hai Chau District, Da Nang serves over 80 authentic Thai dishes " +
                            "with imported ingredients. Capacity 150 guests with private rooms. Open daily 10:00 – 22:30. " +
                            "Price 200,000 – 350,000 VND per person."))
            .phone("1900 6006")
            .bookingHotline("1900 6006")
            .website("https://pasgo.vn/nha-hang/thai-market-binh-minh-5")
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
