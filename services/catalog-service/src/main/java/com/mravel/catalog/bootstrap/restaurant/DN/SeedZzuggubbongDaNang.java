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
public class SeedZzuggubbongDaNang {

    @Bean
    CommandLineRunner seedZzuggubbongDaNangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedZzuggubbongDaNang] start");
            RestaurantDoc doc = restaurantZzuggubbong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedZzuggubbongDaNang] done");
        };
    }

    // =====================================================================
    //  NHÀ HÀNG ZZUGGUBBONG — SỐ 04 NGUYỄN HỮU THÔNG, P. PHƯỚC MỸ, Q. SƠN TRÀ
    // =====================================================================

    private static RestaurantDoc restaurantZzuggubbong() {
        String slug = "zzuggubbong-nguyen-huu-thong-da-nang";

        // ---------- Gallery (14 ảnh không gian) ----------
        List<Image> images = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-1.jpg",
                of("Toàn cảnh Nhà hàng Zzuggubbong Nguyễn Hữu Thông", "Overview of Zzuggubbong Restaurant on Nguyen Huu Thong"), true, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-2.jpg",
                of("Không gian nội thất hiện đại ấm cúng kiểu Hàn", "Modern cozy Korean-style interior"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-3.jpg",
                of("Khu vực nướng than Hàn Quốc bàn từng người", "Korean individual charcoal grill table area"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-4.jpg",
                of("Mâm banchan đặc trưng Hàn Quốc phong phú", "Abundant authentic Korean banchan side dishes"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-5.jpg",
                of("Thịt nướng Hàn Quốc trên than hoa tỏa khói thơm", "Korean BBQ meat on charcoal with fragrant smoke"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-6.jpg",
                of("Kimchi tươi ngon tự làm theo công thức Hàn", "Fresh homemade kimchi by authentic Korean recipe"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-7.jpg",
                of("Set thịt nướng Hàn đặc sắc nhiều loại", "Signature Korean BBQ set with various meats"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-8.jpg",
                of("Bữa tiệc Hàn Quốc nhóm bạn bè vui vẻ", "Fun Korean feast for a group of friends"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-9.jpg",
                of("Ramen Hàn Quốc thơm ngon nước dùng đậm vị", "Fragrant Korean ramen with rich flavorful broth"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-10.jpg",
                of("Bibimbap và cơm trộn Hàn Quốc đặc sắc", "Signature Korean bibimbap and mixed rice"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-11.jpg",
                of("Không gian phòng ngăn riêng tư nhóm nhỏ", "Semi-private booth for small groups"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-12.jpg",
                of("Tokbokki và ăn vặt Hàn Quốc đặc sắc", "Tteokbokki and Korean snacks"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-13.jpg",
                of("Đầu bếp người Hàn chế biến trực tiếp tại bàn", "Korean chef preparing dishes directly at the table"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lent/zzuggubbong/nha-hang-zzuggubbong-nguyen-huu-thong-14.jpg",
                of("Không gian nhà hàng về đêm rực rỡ neon Hàn", "Vibrant Korean neon-lit restaurant at night"), false, 13)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN")
                .name(of("Ẩm thực Hàn Quốc", "Korean cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_BBQ")
                .name(of("Nướng than Hàn Quốc", "Korean charcoal BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_HOTPOT")
                .name(of("Lẩu Hàn Quốc", "Korean hotpot"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 23:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 23, 30),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 23, 30),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 23, 30),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 23, 30),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 23, 30),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 23, 30),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 23, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè bữa tiệc Hàn", "Korean feast with friends")),
            suitable("COUPLE",        of("Hẹn hò ẩm thực Hàn Quốc lãng mạn", "Romantic Korean dining date")),
            suitable("FAMILY",        of("Ăn gia đình món Hàn đa dạng", "Diverse Korean family dining")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật phong cách Hàn", "Korean-style birthday party")),
            suitable("TOURIST",       of("Du khách muốn thử ẩm thực Hàn Quốc", "Tourists exploring Korean cuisine")),
            suitable("COMPANY_PARTY", of("Liên hoan công ty buffet thịt nướng", "Company party BBQ style")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm bạn thân", "Close friends group gathering")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt trong không gian Hàn", "Special anniversary in Korean ambience")),
            suitable("OFFICE_LUNCH",  of("Bữa trưa văn phòng món Hàn", "Korean office lunch")),
            suitable("BUSINESS_MEAL", of("Gặp gỡ đối tác trong không gian ấm cúng", "Business meeting in cozy setting"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại phong cách Hàn Quốc đương đại", "Modern contemporary Korean style")),
            ambience("COZY",      of("Ấm cúng gần gũi kiểu nhà hàng Hàn", "Cozy and intimate Korean restaurant feel")),
            ambience("VIBRANT",   of("Sôi động tràn đầy năng lượng K-vibe", "Vibrant energetic K-vibe atmosphere")),
            ambience("ROMANTIC",  of("Lãng mạn ánh đèn nhẹ nhàng buổi tối", "Romantic soft lighting in the evening"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(20)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Thịt ba chỉ nướng than Zzuggubbong", "Zzuggubbong signature samgyeopsal"),
                of("Thịt ba chỉ heo Hàn Quốc dày cắt đặc biệt, nướng trên than hoa giòn rụm vàng ươm, " +
                   "ăn kèm kimchi, tương doenjang và lá rau sống theo truyền thống.",
                   "Specially thick-cut Korean pork belly grilled on charcoal until golden crispy, " +
                   "served traditionally with kimchi, doenjang paste and fresh lettuce wraps."),
                new BigDecimal("120000")),
            signatureDish(of("Bulgogi bò cuộn rau sống", "Beef bulgogi lettuce wraps"),
                of("Thịt bò Bulgogi ướp nước tương ngọt, lê và tỏi đặc trưng, nướng vàng đều tay, " +
                   "cuộn cùng lá xà lách, cơm và tương ớt gochujang truyền thống Hàn Quốc.",
                   "Bulgogi beef marinated in soy, pear and garlic, evenly grilled golden brown, " +
                   "wrapped in lettuce with rice and traditional gochujang sauce."),
                new BigDecimal("130000")),
            signatureDish(of("Kimchi jjigae đậu phụ cay nồng", "Spicy kimchi and tofu stew"),
                of("Canh kim chi nấu sôi bùng bùng với đậu phụ non, thịt heo và nấm, " +
                   "vị chua cay đậm đà làm ấm bụng — món canh quốc dân Hàn Quốc.",
                   "Bubbling kimchi stew with soft tofu, pork and mushrooms, " +
                   "deeply sour-spicy and warming — the quintessential Korean home stew."),
                new BigDecimal("85000")),
            signatureDish(of("Tteokbokki cay đặc sắc", "Signature spicy tteokbokki"),
                of("Bánh gạo tteokbokki dày mềm dẻo nấu trong sốt gochujang cay ngọt đặc sánh, " +
                   "thêm bánh cá odeng, trứng luộc và hành lá — snack Hàn Quốc số 1.",
                   "Thick chewy tteok rice cakes in rich sweet-spicy gochujang sauce, " +
                   "with fish cake odeng, boiled egg and spring onion — Korea's number 1 snack."),
                new BigDecimal("75000")),
            signatureDish(of("Bibimbap rau trộn đá nóng", "Hot stone bibimbap"),
                of("Cơm trộn Bibimbap trong bát đá nóng với thịt bò, trứng sống, rau namul ngũ sắc " +
                   "và tương ớt gochujang, trộn đều bốc khói thơm lừng.",
                   "Bibimbap in sizzling stone bowl with beef, raw egg, colorful namul vegetables " +
                   "and gochujang, stir it all together for fragrant steaming goodness."),
                new BigDecimal("95000")),
            signatureDish(of("Galbi sườn bò nướng than", "Charcoal grilled beef galbi ribs"),
                of("Sườn bò galbi ướp tương ngọt 24 giờ, nướng than hoa đến khi mềm ngọt và có vân " +
                   "cháy caramel đẹp mắt — món cao cấp trong thực đơn nướng Hàn.",
                   "Galbi beef ribs marinated for 24 hours in sweet soy, grilled over charcoal until " +
                   "tender with beautiful caramel char marks — the premium Korean BBQ choice."),
                new BigDecimal("145000")),
            signatureDish(of("Ramyeon Hàn Quốc nước dùng đậm vị", "Rich Korean ramyeon noodle soup"),
                of("Mì ramen Hàn Quốc nấu trong nước dùng hầm xương đậm vị, thêm thịt, kim chi, " +
                   "trứng onsen và rong biển — tô mì ấm lòng đặc trưng.",
                   "Korean ramyeon in rich bone broth, topped with meat, kimchi, " +
                   "onsen egg and seaweed — the ultimate comforting noodle bowl."),
                new BigDecimal("85000")),
            signatureDish(of("Pajeon bánh xèo hành lá hải sản", "Scallion seafood pancake pajeon"),
                of("Bánh xèo hành lá Pajeon giòn vàng hai mặt với mực, tôm và hành tây, " +
                   "chấm nước tương gừng chua ngọt — ăn vặt kinh điển Hàn Quốc.",
                   "Crispy golden pajeon scallion pancake with squid, shrimp and onion, " +
                   "dipped in sweet-sour ginger soy — Korea's classic snack dish."),
                new BigDecimal("80000")),
            signatureDish(of("Banchan mâm đồ ăn kèm 8 món", "8-dish banchan side dishes platter"),
                of("Mâm banchan 8 món ăn kèm truyền thống tự làm theo công thức Hàn Quốc: kimchi cải thảo, " +
                   "kimchi dưa leo, spinach namul, bean sprout namul, japchae, đậu đen kho, trứng cuộn và " +
                   "radish kimchi — đặc trưng văn hóa ẩm thực Hàn.",
                   "Traditional 8-dish homemade banchan: baechu kimchi, oi kimchi, spinach namul, " +
                   "bean sprout namul, japchae, braised black beans, rolled egg and radish kimchi — " +
                   "the essence of Korean food culture."),
                new BigDecimal("0")),
            signatureDish(of("Hotteok bánh ngọt Hàn Quốc tráng miệng", "Korean sweet hotteok dessert pancake"),
                of("Bánh hotteok nhân đường nâu mật ong và hạt dẻ, chiên giòn nóng hổi, " +
                   "vỏ ngoài vàng giòn nhân trong ngọt chảy — tráng miệng đường phố Hàn tuyệt vời.",
                   "Hotteok with brown sugar-honey-nut filling, fried hot and golden crispy outside, " +
                   "with sweet melting filling inside — a wonderful Korean street food dessert."),
                new BigDecimal("40000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("NUONG_HAN")
                .name(of("Thịt nướng Hàn Quốc", "Korean BBQ grilled meats"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Samgyeopsal ba chỉ heo", "Pork belly samgyeopsal"))
                        .description(of("Ba chỉ heo Hàn Quốc nướng than hoa.", "Korean pork belly charcoal grilled."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork-belly", "grill", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bulgogi bò nướng", "Grilled beef bulgogi"))
                        .description(of("Bò bulgogi ướp ngọt nướng than hoa.", "Sweet marinated beef bulgogi on charcoal."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "bulgogi", "grill"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MON_CHINH")
                .name(of("Món chính Hàn Quốc", "Korean main dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Kimchi jjigae canh kim chi", "Kimchi stew"))
                        .description(of("Canh kim chi đậu phụ thịt heo cay đậm.", "Spicy kimchi tofu pork stew."))
                        .priceFrom(new BigDecimal("75000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("tô", "bowl"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("kimchi", "stew", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bibimbap bát đá nóng", "Hot stone bibimbap"))
                        .description(of("Cơm trộn bibimbap bát đá nóng đầy đủ.", "Full hot stone bowl bibimbap."))
                        .priceFrom(new BigDecimal("85000"))
                        .priceTo(new BigDecimal("105000"))
                        .unit(of("bát", "bowl"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bibimbap", "rice", "stone-bowl"))
                        .build()
                ))
                .build()
        );

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "PRIVATE_ROOM",
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "KOREAN_CHEF",
            "INDIVIDUAL_GRILL",
            "OUTDOOR_HOOD",
            "BIRTHDAY_CAKE_ALLOWED",
            "LATE_NIGHT_OPEN"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè trước nhà hàng, đường Nguyễn Hữu Thông", "Sidewalk in front of the restaurant on Nguyen Huu Thong street"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Đỗ xe máy miễn phí trước cổng. Ô tô đỗ trên đường hoặc bãi gần khu vực Phước Mỹ.",
                      "Free motorbike parking at the entrance. Cars park on the street or nearby lots in the Phuoc My area."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Zzuggubbong — Bữa Tiệc Hàn Quốc Đích Thực Tại Sơn Trà, Đà Nẵng",
                   "Zzuggubbong — Authentic Korean Feast in Son Tra, Da Nang")),
            paragraph(ContentSection.STORY,
                of("Nằm tại số 04 Nguyễn Hữu Thông, phường Phước Mỹ, quận Sơn Trà — cách bờ biển Mỹ Khê chỉ " +
                   "vài trăm mét — Zzuggubbong là nhà hàng Hàn Quốc chính thống hiếm có tại Đà Nẵng, " +
                   "nơi mỗi món ăn được chế biến bởi chính đầu bếp người Hàn với nguyên liệu nhập khẩu " +
                   "trực tiếp và công thức gia truyền đích thực.",
                   "Located at 04 Nguyen Huu Thong Street, Phuoc My Ward, Son Tra District — just a few " +
                   "hundred metres from My Khe Beach — Zzuggubbong is one of Da Nang's rare authentic Korean " +
                   "restaurants, where every dish is prepared by Korean chefs using directly imported ingredients " +
                   "and genuine traditional recipes.")),
            heading(ContentSection.STORY,
                of("Đầu bếp người Hàn — Bảo chứng cho hương vị chính thống", "Korean Chef — Guarantee of Authentic Flavors")),
            paragraph(ContentSection.STORY,
                of("Điều làm Zzuggubbong khác biệt là đội bếp người Hàn Quốc trực tiếp chế biến tất cả " +
                   "các món theo công thức gốc — từ kimchi tự muối, sốt bulgogi ướp 24 giờ, đến nước dùng " +
                   "hầm xương đêm trước. Thực khách được thưởng thức đúng hương vị mà người Hàn Quốc ăn " +
                   "hàng ngày, không phải phiên bản đã Việt hóa.",
                   "What sets Zzuggubbong apart is its Korean kitchen team that prepares everything to original " +
                   "recipes — from self-fermented kimchi and 24-hour bulgogi marinade to bone broth simmered " +
                   "the night before. Diners enjoy the exact same flavors Koreans eat daily, not a Vietnamized version.")),
            heading(ContentSection.STORY,
                of("Thực đơn đa dạng — Từ nướng than đến canh jjigae", "Diverse Menu — From Charcoal Grill to Jjigae Stew")),
            paragraph(ContentSection.STORY,
                of("Thực đơn phong phú gồm 3 nhóm chính: thịt nướng than cá nhân (samgyeopsal, bulgogi, " +
                   "galbi), món chính (bibimbap bát đá nóng, kimchi jjigae, doenjang jjigae, ramyeon) " +
                   "và snack/ăn vặt (tteokbokki, pajeon, hotteok). Mâm banchan 8 món tự làm theo công thức " +
                   "Hàn được phục vụ miễn phí kèm mọi bữa ăn — đặc trưng văn hóa ẩm thực Hàn Quốc.",
                   "The rich menu covers 3 main groups: individual charcoal BBQ (samgyeopsal, bulgogi, galbi), " +
                   "main dishes (hot stone bibimbap, kimchi jjigae, doenjang jjigae, ramyeon) " +
                   "and snacks (tteokbokki, pajeon, hotteok). An 8-dish homemade banchan platter " +
                   "is served free with every meal — a hallmark of Korean dining culture.")),
            heading(ContentSection.STORY,
                of("Không gian hiện đại ấm cúng — K-Vibe tràn đầy", "Modern Cozy Space — Full of K-Vibe")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được thiết kế theo phong cách nhà hàng Hàn Quốc đương đại: ánh đèn vàng nhẹ nhàng, " +
                   "ghế gỗ ấm áp, bếp nướng than cá nhân đặt chìm tại mỗi bàn với hút khói hiệu quả. " +
                   "Âm nhạc K-pop nhẹ nhàng và trang trí theo phong cách Hàn tạo nên không khí đặc trưng " +
                   "K-vibe thu hút giới trẻ và cả gia đình.",
                   "The restaurant is designed in contemporary Korean restaurant style: warm soft lighting, " +
                   "wooden furniture, individual charcoal grills embedded at each table with effective ventilation. " +
                   "Gentle K-pop music and Korean-style decor create a distinctive K-vibe atmosphere " +
                   "appealing to young diners and families alike.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho mọi dịp — Từ hẹn hò đến tiệc nhóm", "Perfect for Every Occasion — From Dates to Group Parties")),
            paragraph(ContentSection.STORY,
                of("Zzuggubbong là lựa chọn lý tưởng cho hẹn hò đôi lứa trong không gian ấm cúng, tiệc sinh " +
                   "nhật, tụ tập bạn bè sau giờ làm hay ngay cả bữa ăn gia đình cuối tuần. Nhà hàng mở đến " +
                   "23:30 hàng ngày nên cũng rất phù hợp cho những buổi ăn tối muộn. Có 3 phòng riêng " +
                   "nhỏ xinh cho nhóm từ 8-20 người.",
                   "Zzuggubbong is the ideal choice for a cozy couple's date, birthday parties, after-work " +
                   "friend gatherings or even weekend family meals. Open until 23:30 daily, it is also " +
                   "excellent for late dinners. Three intimate private rooms accommodate groups of 8-20 people.")),
            heading(ContentSection.STORY,
                of("Vị trí & Di chuyển — Ngay gần biển Mỹ Khê", "Location & Getting There — Steps from My Khe Beach")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm tại số 04 Nguyễn Hữu Thông, phường Phước Mỹ, quận Sơn Trà. Cách bãi biển " +
                   "Mỹ Khê chỉ 300 mét, cách trung tâm cầu Rồng khoảng 10 phút lái xe. Dễ dàng tìm thấy " +
                   "và đỗ xe máy trực tiếp trước cổng nhà hàng, không mất phí.",
                   "The restaurant is at 04 Nguyen Huu Thong Street, Phuoc My Ward, Son Tra District. " +
                   "Just 300 metres from My Khe Beach and about 10 minutes by car from Dragon Bridge. " +
                   "Easy to find with free motorbike parking right at the entrance.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & Lưu ý", "Booking Policy & Notes")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước được khuyến khích vào cuối tuần và dịp lễ, đặc biệt cho nhóm từ 8 người " +
                   "trở lên. Phòng riêng cần đặt trước ít nhất 24 giờ. Nhà hàng cho phép mang bánh sinh nhật " +
                   "từ bên ngoài vào. Hỗ trợ xuất hóa đơn VAT theo yêu cầu.",
                   "Advance booking is recommended on weekends and holidays, especially for groups of 8 or more. " +
                   "Private rooms require at least 24 hours advance booking. Outside birthday cakes are welcome. " +
                   "VAT invoice issuance available on request."))
        );

        // ---------- Policy ----------
        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("150000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc áp dụng cho nhóm từ 10 khách trở lên hoặc khi đặt phòng riêng.",
                             "Deposit required for groups of 10 or more, or when booking private rooms."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi khi đặt bàn qua hệ thống online.", "Discount for online system bookings."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi liên hệ trực tiếp nhà hàng.", "Contact restaurant for promotion details."))
            .blackoutRules(List.of(
                blackoutGregorian(1, 1, of("Tết Dương Lịch — nhà hàng nghỉ", "New Year's Day — closed")),
                blackoutGregorian(9, 9, of("Lễ Chuseok — thực đơn đặc biệt", "Chuseok — special menu day"))
            ))
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu, thông báo trước khi thanh toán.",
                             "VAT invoice on request; notify before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí dịch vụ 5% áp dụng khi sử dụng phòng riêng.",
                                   "5% service charge applies for private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào, trừ bánh sinh nhật.",
                                  "Outside food not permitted, except birthday cakes."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào nhà hàng.", "Outside drinks not permitted."))
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("zzug-tb-4")
                .name(of("Bàn nướng 4 người", "BBQ table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(5)
                .totalTables(20)
                .depositPrice(new BigDecimal("80000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn có bếp nướng than cá nhân.", "Standard table with individual charcoal grill."))
                .build(),
            TableType.builder()
                .id("zzug-tb-8")
                .name(of("Bàn nhóm 8 người", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(5)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn khu vực chung, phù hợp nhóm bạn bè.", "Large table in main area, ideal for friend groups."))
                .build(),
            TableType.builder()
                .id("zzug-pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(8)
                .maxPeople(20)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 180))
                .note(of("Phòng riêng nhỏ xinh cho nhóm 8-20 người, âm nhạc và trang trí theo yêu cầu.",
                         "Intimate private room for 8-20 people, music and decoration on request."))
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
            .districtName(of("Quận Sơn Trà", "Son Tra District"))
            .wardName(of("Phường Phước Mỹ", "Phuoc My Ward"))
            .addressLine(of("Số 04 Nguyễn Hữu Thông, P. Phước Mỹ, Q. Sơn Trà, TP. Đà Nẵng",
                            "04 Nguyen Huu Thong Street, Phuoc My Ward, Son Tra District, Da Nang City"))
            .location(new double[]{108.241392, 16.070915})
            .name(of("Nhà hàng Zzuggubbong – Nguyễn Hữu Thông", "Zzuggubbong Korean Restaurant Nguyen Huu Thong"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng Hàn Quốc chính thống — đầu bếp người Hàn, thực đơn đa dạng, không gian ấm cúng ngay gần biển Mỹ Khê Sơn Trà.",
                                 "Authentic Korean restaurant — Korean chef, diverse menu, cozy atmosphere steps from My Khe Beach in Son Tra."))
            .description(of("Zzuggubbong tại số 04 Nguyễn Hữu Thông, phường Phước Mỹ, quận Sơn Trà — nhà hàng Hàn Quốc " +
                            "được chế biến bởi đầu bếp người Hàn với thực đơn đa dạng từ thịt nướng, jjigae đến bibimbap. " +
                            "Không gian hiện đại ấm cúng phong cách K-vibe, mở đến 23:30 mỗi ngày.",
                            "Zzuggubbong at 04 Nguyen Huu Thong Street, Phuoc My Ward, Son Tra District — a Korean restaurant " +
                            "prepared by Korean chefs with a diverse menu from BBQ and jjigae to bibimbap. " +
                            "Modern cozy K-vibe style interior, open until 23:30 every day."))
            .phone("0709151082")
            .bookingHotline("0709151082")
            .minPricePerPerson(new BigDecimal("100000"))
            .maxPricePerPerson(new BigDecimal("150000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
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
