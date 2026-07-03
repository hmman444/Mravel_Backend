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
public class SeedAkataiyoMatTroiDoNguyenDu {

    @Bean
    CommandLineRunner seedAkataiyoMatTroiDoNguyenDuRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedAkataiyoMatTroiDoNguyenDu] start");

            RestaurantDoc doc = restaurantAkataiyoMatTroiDoNguyenDu();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedAkataiyoMatTroiDoNguyenDu] done");
        };
    }

    // =====================================================================
    //       NHÀ HÀNG AKATAIYO MẶT TRỜI ĐỎ - NGUYỄN DU (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantAkataiyoMatTroiDoNguyenDu() {
        String slug = "akataiyo-mat-troi-do-nguyen-du";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-1-normal-130839615013.webp",
                of("Không gian Akataiyo Mặt Trời Đỏ Nguyễn Du", "Akataiyo Red Sun Nguyen Du restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-2-normal-130839715014.webp",
                of("Khu vực ăn tầng trệt", "Ground floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-3-normal-130839815015.webp",
                of("Không gian Nhật Bản đặc trưng", "Authentic Japanese ambience"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-4-normal-130839915016.webp",
                of("Bàn ăn kiểu Nhật tinh tế", "Elegant Japanese-style dining table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-5-normal-130840015017.webp",
                of("Quầy bếp mở kiểu Nhật", "Japanese open kitchen counter"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-6-normal-130840115018.webp",
                of("Không gian yên tĩnh, ấm cúng", "Quiet and cozy atmosphere"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-7-normal-130840215019.webp",
                of("Trang trí nội thất phong cách Nhật", "Japanese-style interior decoration"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-8-normal-130840315020.webp",
                of("Món sashimi tươi ngon trình bày đẹp", "Beautifully presented fresh sashimi"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-9-normal-130840415021.webp",
                of("Set cơm bento đặc biệt", "Special bento rice set"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-10-normal-130840515022.webp",
                of("Lẩu Nhật Shabu-shabu nóng hổi", "Hot Japanese Shabu-shabu hotpot"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-11-normal-130840615023.webp",
                of("Không gian bàn ghế gỗ truyền thống", "Traditional wooden furniture dining space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-akataiyo-mat-troi-do-nguyen-du-slide-12-normal-130840715024.webp",
                of("Mặt tiền nhà hàng Akataiyo Nguyễn Du", "Akataiyo Nguyen Du restaurant facade"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/1.jpg",
                of("Menu Akataiyo trang 1 — Sushi & Sashimi", "Akataiyo menu page 1 — Sushi & Sashimi"), false, 0),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/2.jpg",
                of("Menu Akataiyo trang 2 — Set bento", "Akataiyo menu page 2 — Bento sets"), false, 1),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/3.jpg",
                of("Menu Akataiyo trang 3 — Lẩu Nhật", "Akataiyo menu page 3 — Japanese hotpot"), false, 2),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/4.jpg",
                of("Menu Akataiyo trang 4 — Mì ramen & udon", "Akataiyo menu page 4 — Ramen & udon noodles"), false, 3),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/5.jpg",
                of("Menu Akataiyo trang 5 — Đồ nướng Nhật", "Akataiyo menu page 5 — Japanese grills"), false, 4),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/6.jpg",
                of("Menu Akataiyo trang 6 — Tempura & món chiên", "Akataiyo menu page 6 — Tempura & fried dishes"), false, 5),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/7.jpg",
                of("Menu Akataiyo trang 7 — Đồ uống Nhật", "Akataiyo menu page 7 — Japanese beverages"), false, 6),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/8.jpg",
                of("Menu Akataiyo trang 8 — Tráng miệng", "Akataiyo menu page 8 — Desserts"), false, 7),
            img("https://pastaxi-manager.onepas.vn/Content/Uploads/Prices/huongnt/Akataiyo/9.jpg",
                of("Menu Akataiyo trang 9 — Set combo", "Akataiyo menu page 9 — Combo sets"), false, 8)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SUSHI_SASHIMI")
                .name(of("Sushi & Sashimi", "Sushi & Sashimi"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT_SHABU")
                .name(of("Lẩu Nhật Shabu-shabu", "Japanese Shabu-shabu hotpot"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 22:00 mỗi ngày, gồm cả 2 ca) ----------
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
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_PARTY", of("Liên hoan nhỏ", "Small office party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm", "Group meeting")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",  of("Thanh lịch, tinh tế kiểu Nhật", "Elegant Japanese-style refinement")),
            ambience("COZY",     of("Ấm cúng, yên tĩnh", "Cozy and quiet")),
            ambience("ROMANTIC", of("Lãng mạn, riêng tư", "Romantic and intimate")),
            ambience("MODERN",   of("Hiện đại pha truyền thống Nhật", "Modern blended with Japanese tradition"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(100)
            .maxGroupSize(20)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(15)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Sashimi tổng hợp 5 loại cá", "5-Fish Assorted Sashimi"),
                of("Đĩa sashimi tươi ngon gồm cá hồi, cá ngừ, bạch tuộc, tôm và cá cam, thái lát mỏng đều, " +
                   "trình bày đẹp mắt trên đá lạnh kèm wasabi và gừng ngâm.",
                   "Fresh sashimi platter of salmon, tuna, octopus, shrimp and yellowtail, evenly sliced thin, " +
                   "elegantly presented on crushed ice with wasabi and pickled ginger."),
                new BigDecimal("220000")),
            signatureDish(of("Cơm cuộn Akataiyo đặc biệt", "Special Akataiyo Maki Roll"),
                of("Cuộn cơm to với nhân cá hồi nướng, bơ, xốt Spicy Mayo và tempura tôm giòn, phủ ngoài " +
                   "bởi trứng cá màu cam bắt mắt.",
                   "Large maki roll with grilled salmon, avocado, Spicy Mayo and crispy shrimp tempura filling, " +
                   "topped with vibrant orange fish roe."),
                new BigDecimal("165000")),
            signatureDish(of("Lẩu Shabu-shabu thịt bò Wagyu", "Wagyu Beef Shabu-shabu Hotpot"),
                of("Nồi lẩu Shabu-shabu nước dùng dashi thanh ngọt với thịt bò Wagyu thái mỏng mềm tan, " +
                   "nấm linh chi, đậu phụ non và rau củ tươi xanh, chấm sốt sesame đặc biệt.",
                   "Shabu-shabu hotpot with clear dashi broth paired with paper-thin melt-in-your-mouth Wagyu beef, " +
                   "reishi mushrooms, silken tofu and fresh vegetables, served with special sesame dipping sauce."),
                new BigDecimal("320000")),
            signatureDish(of("Mì Ramen tonkotsu cổ điển", "Classic Tonkotsu Ramen"),
                of("Tô ramen nước dùng xương hầm 12 tiếng béo ngậy đậm vị, mì ramen dai mềm, thịt charsiu thái " +
                   "mỏng, trứng ajitsuke ngâm sốt đặc biệt, rong biển và hành lá.",
                   "Ramen bowl with 12-hour bone broth rich and deeply flavored, chewy ramen noodles, thinly sliced " +
                   "chashu pork, specially marinated soft-boiled ajitsuke egg, nori seaweed and green onion."),
                new BigDecimal("135000")),
            signatureDish(of("Set bento cơm cá hồi nướng", "Grilled Salmon Bento Set"),
                of("Set cơm bento gồm cá hồi nướng muối vàng đều, cơm Nhật trắng dẻo, canh miso, salad " +
                   "wakame và 3 món phụ theo mùa, đầy đủ dinh dưỡng trong một khay.",
                   "Bento set with evenly golden grilled salt salmon, fluffy Japanese rice, miso soup, wakame " +
                   "salad and 3 seasonal side dishes — a nutritionally complete one-tray meal."),
                new BigDecimal("150000")),
            signatureDish(of("Tôm tempura giòn rụm", "Crispy Shrimp Tempura"),
                of("6 con tôm sú cỡ lớn bọc bột tempura chiên giòn vàng óng, phục vụ với sốt tentsuyu " +
                   "và củ cải mài trắng mịn.",
                   "6 large tiger prawns coated in tempura batter, deep-fried to a gorgeous golden crisp, " +
                   "served with tentsuyu dipping sauce and finely grated daikon radish."),
                new BigDecimal("145000")),
            signatureDish(of("Takoyaki bạch tuộc nóng hổi", "Hot Octopus Takoyaki"),
                of("6 viên takoyaki tròn căng, nhân bạch tuộc thơm ngọt mềm, phủ mayo Nhật, sốt Okonomiyaki " +
                   "và katsuobushi bào rung rinh.",
                   "6 plump takoyaki balls with fragrant sweet tender octopus filling, topped with Japanese mayo, " +
                   "Okonomiyaki sauce and shimmering dancing katsuobushi flakes."),
                new BigDecimal("75000")),
            signatureDish(of("Gyoza chiên áp chảo", "Pan-Fried Gyoza Dumplings"),
                of("8 miếng gyoza nhân thịt heo và cải thảo chiên áp chảo giòn vàng một mặt, hấp mềm mặt kia, " +
                   "chấm giấm Ponzu pha nước tương và ớt băm.",
                   "8 pork and napa cabbage gyoza pan-fried golden and crispy on one side, steamed soft on the other, " +
                   "dipped in Ponzu vinegar mixed with soy sauce and minced chili."),
                new BigDecimal("85000")),
            signatureDish(of("Cơm chiên Hibachi rau củ", "Hibachi Vegetable Fried Rice"),
                of("Cơm chiên kiểu Hibachi trên vỉ sắt nóng với trứng, bơ, cà rốt, đậu Hà Lan và nấm, " +
                   "nêm nước tương Tamari — thơm lừng ngay khi dọn ra bàn.",
                   "Hibachi-style fried rice on hot iron griddle with egg, butter, carrots, peas and mushrooms, " +
                   "seasoned with Tamari soy sauce — fragrant the moment it arrives at the table."),
                new BigDecimal("95000")),
            signatureDish(of("Kem mochi matcha truyền thống", "Traditional Matcha Mochi Ice Cream"),
                of("3 viên mochi kem nhân matcha Uji thượng hạng bọc vỏ bánh dẻo mịn, lạnh mát tan chảy " +
                   "trong miệng — tráng miệng hoàn hảo cho bữa ăn Nhật.",
                   "3 mochi pieces filled with premium Uji matcha ice cream wrapped in smooth chewy mochi skin, " +
                   "cold and melting — the perfect Japanese meal dessert."),
                new BigDecimal("65000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SUSHI_SASHIMI")
                .name(of("Sushi & Sashimi", "Sushi & Sashimi"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Sashimi cá hồi (6 lát)", "Salmon sashimi (6 slices)"))
                        .description(of("Cá hồi Na Uy tươi thái lát mỏng, ăn kèm wasabi và gừng ngâm.",
                                       "Fresh Norwegian salmon thinly sliced, served with wasabi and pickled ginger."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("sashimi", "salmon", "japanese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set nigiri sushi 8 miếng", "8-piece nigiri sushi set"))
                        .description(of("8 miếng nigiri sushi với các loại cá tươi theo mùa trên cơm Nhật nặn tay.",
                                       "8-piece nigiri sushi with seasonal fresh fish on hand-pressed Japanese rice."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("sushi", "nigiri", "japanese"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("RAMEN_NOODLES")
                .name(of("Mì Ramen & Udon", "Ramen & Udon Noodles"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ramen Tonkotsu", "Tonkotsu Ramen"))
                        .description(of("Nước dùng xương hầm đặc, mì ramen dai, charsiu, trứng ajitsuke, nori.",
                                       "Rich bone broth, springy ramen noodles, chashu, ajitsuke egg, nori."))
                        .priceFrom(new BigDecimal("135000"))
                        .priceTo(new BigDecimal("155000"))
                        .unit(of("tô", "bowl"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("ramen", "noodles", "japanese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Udon xào bơ nấm", "Mushroom Butter Stir-fried Udon"))
                        .description(of("Sợi udon to dai xào bơ nấm tươi, tôm và rau cải ngọt theo kiểu Nhật.",
                                       "Thick chewy udon noodles stir-fried with butter, fresh mushrooms, shrimp and greens."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("udon", "noodles", "japanese"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Không có bãi đỗ ô tô riêng; khách có thể đỗ trên đường Nguyễn Du",
                                   "No private car parking; street parking available on Nguyen Du"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy ngay trước cửa nhà hàng", "Motorbike parking in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng nằm trong trung tâm Hải Châu; có thể gửi xe máy trước cửa miễn phí.",
                      "Restaurant is in central Hai Chau; free motorbike parking available in front."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "SMOKING_AREA",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "PRIVATE_ROOM",
            "FREE_MOTORBIKE_PARKING",
            "EVENT_DECOR",
            "SET_LUNCH",
            "BIRTHDAY_CAKE_SERVICE",
            "SAKE_BAR",
            "TATAMI_SEATING"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Akataiyo Mặt Trời Đỏ — Ẩm thực Nhật Bản chính thống giữa lòng Đà Nẵng",
                   "Akataiyo Red Sun — Authentic Japanese cuisine in the heart of Da Nang")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Akataiyo Mặt Trời Đỏ – Nguyễn Du tọa lạc tại số 54 Nguyễn Du, Quận Hải Châu, " +
                   "TP. Đà Nẵng — nằm ngay trung tâm thành phố, gần các điểm du lịch nổi tiếng như cầu Rồng và " +
                   "bờ sông Hàn. Với tên gọi \"Mặt Trời Đỏ\" (Akataiyo) gợi lên hình ảnh lá cờ Nhật Bản, nhà " +
                   "hàng mang đến trải nghiệm ẩm thực Nhật Bản chính thống từ nguyên liệu đến kỹ thuật chế biến " +
                   "và không gian trang trí.",
                   "Akataiyo Red Sun Restaurant – Nguyen Du is located at 54 Nguyen Du Street, Hai Chau District, " +
                   "Da Nang City — right in the heart of the city, near famous landmarks such as Dragon Bridge and " +
                   "the Han River embankment. Named 'Red Sun' (Akataiyo), evoking the Japanese flag, the restaurant " +
                   "delivers authentic Japanese culinary experiences from ingredients to cooking techniques and decor.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Akataiyo Nguyễn Du được thiết kế theo phong cách Nhật Bản truyền thống kết hợp hiện đại — " +
                   "gỗ tự nhiên ấm áp, đèn giấy washi mờ ảo, màu đỏ-trắng-đen hài hòa và những bức tranh " +
                   "thư pháp Kanji trang trọng. Sức chứa khoảng 100 khách phân bổ trên khu vực ăn chính và 2 " +
                   "phòng riêng nhỏ sức chứa tối đa 15 khách mỗi phòng — phù hợp cho các bữa ăn riêng tư nhỏ. " +
                   "Không gian yên tĩnh, được cách âm tốt, lý tưởng để thư giãn và thưởng thức bữa ăn chậm rãi.",
                   "Akataiyo Nguyen Du is designed in a traditional meets modern Japanese style — warm natural wood, " +
                   "soft washi paper lanterns, harmonious red-white-black color scheme and stately Kanji calligraphy " +
                   "artwork. Seating capacity is approximately 100 guests across the main dining area and 2 small " +
                   "private rooms each accommodating up to 15 — suitable for intimate small group meals. " +
                   "The quiet, well-sound-insulated space is ideal for unhurried relaxed dining.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Akataiyo phong phú với đầy đủ tinh hoa ẩm thực Nhật: sashimi cá hồi/cá ngừ tươi " +
                   "hàng ngày, cuộn maki sáng tạo, nigiri sushi truyền thống, lẩu Shabu-shabu bò Wagyu, ramen " +
                   "Tonkotsu nước dùng hầm 12 tiếng, udon xào bơ nấm, tempura tôm giòn, takoyaki bạch tuộc và " +
                   "set bento cá hồi nướng. Mức giá từ 200.000 – 250.000 đồng/người — hợp túi tiền với chất lượng " +
                   "nguyên liệu nhập khẩu đáng tin cậy.",
                   "Akataiyo's menu is rich with the full essence of Japanese cuisine: daily fresh salmon/tuna " +
                   "sashimi, creative maki rolls, traditional nigiri sushi, Wagyu beef Shabu-shabu hotpot, " +
                   "12-hour Tonkotsu ramen broth, mushroom butter udon, crispy shrimp tempura, octopus takoyaki " +
                   "and grilled salmon bento sets. Prices from 200,000 – 250,000 VND per person — affordable with " +
                   "reliable imported ingredient quality.")),
            heading(ContentSection.STORY,
                of("Nguyên liệu nhập khẩu — đảm bảo chuẩn vị Nhật",
                   "Imported ingredients — guaranteed authentic Japanese flavor")),
            paragraph(ContentSection.STORY,
                of("Akataiyo cam kết sử dụng nguyên liệu Nhật Bản nhập khẩu chính hãng cho các món chủ lực: " +
                   "cá hồi Na Uy đông lạnh âm 60°C đảm bảo an toàn ăn sống, cá ngừ đại dương đông lạnh, mì " +
                   "ramen sợi tươi nhập Nhật, nước tương Kikkoman, mirin, sake nấu ăn và wasabi thật (không " +
                   "phải mù tạt pha màu). Bếp trưởng nhiều năm kinh nghiệm đảm bảo từng món đạt chuẩn vị " +
                   "như ăn tại Tokyo hay Osaka.",
                   "Akataiyo commits to using genuine Japanese imported ingredients for core dishes: Norwegian " +
                   "salmon frozen at -60°C ensuring raw consumption safety, deep-sea tuna, fresh Japanese-imported " +
                   "ramen noodles, Kikkoman soy sauce, mirin, cooking sake and real wasabi (not dyed mustard). " +
                   "The head chef with years of experience ensures every dish achieves the authentic flavor " +
                   "standard as if dining in Tokyo or Osaka.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Akataiyo Nguyễn Du phù hợp đặc biệt cho những bữa hẹn hò lãng mạn nhờ không gian yên tĩnh " +
                   "và ấm cúng. Cũng lý tưởng cho ăn trưa văn phòng nhanh (set bento hoàn chỉnh trong 30 phút), " +
                   "tiếp khách đối tác Nhật Bản, tổ chức tiệc sinh nhật nhỏ với phòng riêng và dịch vụ bánh kem " +
                   "theo yêu cầu. Gia đình có trẻ nhỏ cũng được phục vụ tốt nhờ thực đơn đa dạng phù hợp " +
                   "mọi lứa tuổi.",
                   "Akataiyo Nguyen Du is especially suitable for romantic dates thanks to its quiet, intimate " +
                   "atmosphere. Also ideal for quick office lunches (complete bento set in 30 minutes), " +
                   "entertaining Japanese business partners, organizing small birthday parties with a private " +
                   "room and custom birthday cake service. Families with young children are also well-served " +
                   "thanks to a diverse menu suitable for all ages.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích",
                   "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng phục vụ set lunch đặc biệt vào buổi trưa (11:00 – 13:30) với mức giá ưu đãi hơn " +
                   "thực đơn gọi món thông thường. Dịch vụ đặt tiệc sinh nhật có thể trang trí phòng riêng và " +
                   "chuẩn bị bánh kem theo yêu cầu. Thanh toán Visa/Master được chấp nhận và có thể xuất hóa " +
                   "đơn VAT. Đội ngũ phục vụ nói được tiếng Anh cơ bản, hỗ trợ khách nước ngoài.",
                   "The restaurant serves a special set lunch during lunch hours (11:00 – 13:30) at more " +
                   "favorable prices than the regular a la carte menu. Birthday party service includes private " +
                   "room decoration and custom birthday cake preparation. Visa/Master payments accepted and " +
                   "VAT invoices available. Service staff with basic English communication skills to assist " +
                   "foreign guests.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Số 54 Nguyễn Du nằm ở Quận Hải Châu, trung tâm TP. Đà Nẵng — cách cầu Rồng khoảng 1 km, " +
                   "gần các khách sạn lớn và trung tâm thương mại Vincom. Thuận tiện di chuyển bằng taxi hoặc " +
                   "Grab từ mọi điểm trong thành phố. Khách có thể gửi xe máy miễn phí ngay trước cửa nhà hàng; " +
                   "xe ô tô có thể đỗ trên đường Nguyễn Du hoặc các bãi đỗ công cộng gần đó.",
                   "54 Nguyen Du is in Hai Chau District, central Da Nang — about 1 km from Dragon Bridge, " +
                   "near major hotels and Vincom Shopping Center. Conveniently accessible by taxi or Grab from " +
                   "anywhere in the city. Free motorbike parking available right in front of the restaurant; " +
                   "cars can park on Nguyen Du Street or nearby public parking areas.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                   "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Akataiyo nhận đặt bàn trước qua điện thoại và hệ thống PasGo. Phòng riêng nên đặt trước " +
                   "ít nhất 1 ngày để chuẩn bị trang trí theo yêu cầu. Không thu phí phục vụ và phí phòng riêng. " +
                   "Không cho phép mang thức ăn hoặc đồ uống từ ngoài vào (trừ bánh sinh nhật tự mang theo). " +
                   "Với vị trí trung tâm, thực đơn đa dạng chuẩn vị Nhật và giá cả phải chăng, Akataiyo là " +
                   "lựa chọn hàng đầu cho người yêu ẩm thực Nhật Bản tại Đà Nẵng.",
                   "Akataiyo accepts reservations by phone and through the PasGo system. Private rooms should be " +
                   "booked at least 1 day in advance for custom decoration preparation. No service charge and no " +
                   "private room fee. Outside food and beverages are not permitted (except self-brought birthday cakes). " +
                   "With its central location, diverse authentic Japanese menu and affordable prices, Akataiyo is " +
                   "the top choice for Japanese food lovers in Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.2200, 16.0672})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
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
            .depositNotes(of("Không yêu cầu đặt cọc với đặt bàn thông thường; tiệc đặc biệt liên hệ trực tiếp.",
                             "No deposit for regular reservations; contact directly for special event bookings."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi đặt bàn qua PasGo; set lunch giá ưu đãi buổi trưa.",
                                 "PasGo booking promotions; discounted set lunch prices."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi theo từng thời điểm.", "Promotion details vary by period."))
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
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào (trừ bánh sinh nhật).",
                                  "Outside food not allowed (except birthday cakes)."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ ngoài vào.", "Outside drinks are not permitted."))
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
                .id("tb-2-std")
                .name(of("Bàn đôi 2 ghế", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(10)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi lý tưởng cho hẹn hò và ăn trưa nhanh.", "Ideal table for two for dates and quick lunches."))
                .build(),
            TableType.builder()
                .id("tb-4-grp")
                .name(of("Bàn nhóm 4 ghế", "Group table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nhóm nhỏ, phù hợp gia đình và bạn bè.", "Small group table for families and friends."))
                .build(),
            TableType.builder()
                .id("pr-15")
                .name(of("Phòng riêng 15 khách", "Private room for 15 guests"))
                .seats(15)
                .minPeople(6)
                .maxPeople(15)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng yên tĩnh, phù hợp sinh nhật nhỏ và tiếp khách.",
                         "Quiet private room for small birthday parties and business entertainment."))
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
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Hải Châu", "Hai Chau District"))
            .wardName(of("P. Hải Châu I", "Hai Chau I Ward"))
            .addressLine(of("Số 54 Nguyễn Du, Q. Hải Châu, TP. Đà Nẵng",
                            "54 Nguyen Du, Hai Chau District, Da Nang City"))
            .location(new double[]{108.2200, 16.0672})
            .name(of("Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du", "Akataiyo Red Sun Japanese Restaurant - Nguyen Du"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Ẩm thực Nhật Bản chính thống tại trung tâm Đà Nẵng — sashimi tươi ngày, ramen tonkotsu, lẩu Wagyu.",
                                 "Authentic Japanese cuisine in central Da Nang — daily fresh sashimi, tonkotsu ramen, Wagyu hotpot."))
            .description(of("Nhà hàng Akataiyo Mặt Trời Đỏ tại 54 Nguyễn Du, Q. Hải Châu, TP. Đà Nẵng phục vụ ẩm thực Nhật Bản " +
                            "chính thống với nguyên liệu nhập khẩu: sashimi, sushi, ramen Tonkotsu, lẩu Shabu-shabu bò Wagyu, " +
                            "tempura và set bento. Không gian yên tĩnh phong cách Nhật, sức chứa 100 khách, có phòng riêng. " +
                            "Giá từ 200.000 – 250.000 đồng/người. Giờ phục vụ: 10:30 – 13:30 và 16:30 – 22:00 hàng ngày.",
                            "Akataiyo Red Sun Restaurant at 54 Nguyen Du, Hai Chau District, Da Nang serves authentic Japanese " +
                            "cuisine with imported ingredients: sashimi, sushi, Tonkotsu ramen, Wagyu beef Shabu-shabu hotpot, " +
                            "tempura and bento sets. Quiet Japanese-style ambiance, 100-guest capacity with private rooms. " +
                            "Prices from 200,000 – 250,000 VND per person. Service hours: 10:30 – 13:30 and 16:30 – 22:00 daily."))
            .phone("0931006005")
            .bookingHotline("0931006005")
            .website("https://pasgo.vn/nha-hang/nha-hang-akataiyo-mat-troi-do-nguyen-du-5013")
            .minPricePerPerson(new BigDecimal("200000"))
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
