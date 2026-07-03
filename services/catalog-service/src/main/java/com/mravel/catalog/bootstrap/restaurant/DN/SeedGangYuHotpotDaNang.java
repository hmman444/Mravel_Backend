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
public class SeedGangYuHotpotDaNang {

    @Bean
    CommandLineRunner seedGangYuHotpotDaNangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGangYuHotpotDaNang] start");
            RestaurantDoc doc = restaurantGangYuHotpot();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedGangYuHotpotDaNang] done");
        };
    }

    // =====================================================================
    //  NHÀ HÀNG GANG YU HOTPOT — 87 YÊN BÁI, QUẬN HẢI CHÂU, ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantGangYuHotpot() {
        String slug = "gang-yu-hotpot-yen-bai-da-nang";

        // ---------- Gallery (16 ảnh không gian) ----------
        List<Image> images = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-1.jpg",
                of("Toàn cảnh Nhà hàng Gang Yu Hotpot đường Yên Bái", "Overview of Gang Yu Hotpot Restaurant on Yen Bai street"), true, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-2.jpg",
                of("Không gian tầng 1 phong cách Hồng Kông hiện đại", "Floor 1 in modern Hong Kong style"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-3.jpg",
                of("Khu vực bàn ăn tầng 2 ấm cúng riêng tư", "Cozy semi-private dining area on floor 2"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-4.jpg",
                of("Phòng riêng tầng 3 cao cấp sang trọng", "Premium private room on floor 3"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-5.jpg",
                of("Nồi lẩu Hồng Kông 6 vị nước dùng đặc sắc", "Hong Kong hotpot with 6 signature broth flavors"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-6.jpg",
                of("Khay nguyên liệu nhúng lẩu phong phú hơn 50 món", "Over 50 fresh ingredients tray for hotpot"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-7.jpg",
                of("Thịt bò Wagyu và hải sản cao cấp", "Wagyu beef and premium seafood"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-8.jpg",
                of("Quầy sốt chấm đa dạng tự phục vụ", "Diverse self-service dipping sauce bar"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-9.jpg",
                of("Nước dùng Mala cay thơm kiểu Tứ Xuyên", "Aromatic spicy Mala broth Sichuan style"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-10.jpg",
                of("Đèn trang trí phong cách Hồng Kông cổ điển", "Classic Hong Kong style decorative lighting"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-11.jpg",
                of("Khu vực bếp mở và quầy trưng bày nguyên liệu", "Open kitchen and fresh ingredients display counter"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-12.jpg",
                of("Không gian nhà hàng lung linh về đêm", "Sparkling evening restaurant atmosphere"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-13.jpg",
                of("Bàn nhúng lẩu cá chua cay nước dùng Thái", "Thai sour-spicy fish hotpot broth table"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-14.jpg",
                of("Thực đơn buffet phong phú nguyên liệu cao cấp", "Premium buffet menu with abundant fresh ingredients"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-15.jpg",
                of("Không gian tổng thể nhà hàng 3 tầng hiện đại", "Overall 3-floor modern restaurant view"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/ganggyhotpot/nha-hang-gang-yu-hotpot-duong-yen-bai-16.jpg",
                of("Khu tiếp đón và lối vào nhà hàng Gang Yu", "Gang Yu restaurant reception and entrance"), false, 15)
        );

        // ---------- Menu images (12 ảnh bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menugangyuhotpot/1.jpg",
                of("Bảng giá buffet lẩu Gang Yu", "Gang Yu hotpot buffet price list"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menugangyuhotpot/2.jpg",
                of("Thực đơn nguyên liệu nhúng lẩu", "Hotpot ingredients menu"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/3.jpg",
                of("Menu thịt bò và hải sản cao cấp", "Premium beef and seafood menu"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/4.jpg",
                of("Menu rau củ và đậu phụ", "Vegetables and tofu menu"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/5.jpg",
                of("Menu nước dùng 6 vị", "6-flavor broth menu"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/6.jpg",
                of("Bảng giá set combo gia đình", "Family combo set price"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/7.jpg",
                of("Menu sốt chấm và gia vị", "Dipping sauce and seasoning menu"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/8.jpg",
                of("Bảng giá đồ uống và tráng miệng", "Beverages and desserts price list"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/9.jpg",
                of("Menu set lunch buffet trưa", "Lunch buffet set menu"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/10.jpg",
                of("Bảng giá phòng riêng và đặt tiệc", "Private room and banquet price list"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/11.jpg",
                of("Menu mì, bánh và món ăn kèm", "Noodles, dumplings and side dishes menu"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangyuhtotpotyb/12.jpg",
                of("Thực đơn cập nhật mới nhất", "Latest updated full menu"), false, 11)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu buffet Hồng Kông", "Hong Kong style hotpot buffet"))
                .region("CANTONESE")
                .build(),
            CuisineTag.builder()
                .code("CANTONESE")
                .name(of("Ẩm thực Quảng Đông", "Cantonese cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực Á Đông kết hợp", "Asian fusion cuisine"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:30-14:00 & 17:00-23:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình lẩu buffet", "Family hotpot buffet dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè lẩu vui vẻ", "Fun hotpot gathering with friends")),
            suitable("COMPANY_PARTY", of("Liên hoan công ty buffet lẩu", "Company party hotpot buffet")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm đông người", "Large group gathering")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật lẩu buffet", "Hotpot buffet birthday party")),
            suitable("COUPLE",        of("Hẹn hò lẩu Hồng Kông lãng mạn", "Romantic Hong Kong hotpot date")),
            suitable("TOURIST",       of("Du khách muốn thử lẩu kiểu Hồng Kông", "Tourists trying Hong Kong style hotpot")),
            suitable("COMPANY_EVENT", of("Sự kiện doanh nghiệp", "Corporate event")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt trong phòng riêng", "Special anniversary in private room")),
            suitable("OFFICE_LUNCH",  of("Bữa trưa văn phòng set lunch", "Office set lunch"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại phong cách Hồng Kông", "Modern Hong Kong style")),
            ambience("VIBRANT",   of("Sôi động nhộn nhịp nhà hàng lẩu", "Lively bustling hotpot restaurant")),
            ambience("ELEGANT",   of("Thanh lịch sang trọng", "Elegant and refined")),
            ambience("COZY",      of("Ấm cúng thoải mái", "Cozy and comfortable"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(280)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Lẩu Mala cay thơm đặc biệt", "Special aromatic Mala spicy hotpot"),
                of("Nước dùng Mala cay nồng đặc trưng Tứ Xuyên pha theo công thức riêng của Gang Yu, " +
                   "thơm hạt tiêu Tứ Xuyên, cay xốc quyến rũ không thể cưỡng lại.",
                   "Gang Yu's signature Sichuan Mala spicy broth with their own recipe, " +
                   "aromatic with Sichuan peppercorns, irresistibly tingling and spicy."),
                new BigDecimal("199000")),
            signatureDish(of("Lẩu hầm xương bò trắng thanh đạm", "Light bone broth hotpot"),
                of("Nước dùng hầm xương bò trong veo qua 8 giờ, vị ngọt tự nhiên thanh đạm, " +
                   "phù hợp nhúng mọi loại nguyên liệu và tôn thêm hương vị tươi ngon.",
                   "Crystal-clear beef bone broth simmered for 8 hours, naturally sweet and light, " +
                   "perfect for dipping all ingredients and enhancing their fresh flavors."),
                new BigDecimal("179000")),
            signatureDish(of("Lẩu chua cay Thái kiểu Hồng Kông", "Hong Kong-style Thai spicy-sour hotpot"),
                of("Nước dùng Tom Yum phong cách Hồng Kông kết hợp sả, riềng, ớt, cà chua và lá chanh, " +
                   "chua cay thơm lừng, rất hợp với hải sản và rau củ.",
                   "Hong Kong-style Tom Yum broth with lemongrass, galangal, chili, tomato and kaffir lime leaves, " +
                   "tangy spicy and aromatic, excellent with seafood and vegetables."),
                new BigDecimal("189000")),
            signatureDish(of("Thịt bò Wagyu A5 nhúng lẩu", "A5 Wagyu beef for hotpot"),
                of("Thịt bò Wagyu A5 nhập khẩu thái mỏng đặc biệt, nhúng vào nước dùng nóng vài giây, " +
                   "vị béo ngậy tan chảy trong miệng — đỉnh cao của buffet lẩu.",
                   "Imported A5 Wagyu beef thinly sliced, dipped briefly in hot broth for a few seconds, " +
                   "melt-in-your-mouth rich fat and flavor — the pinnacle of hotpot buffet."),
                new BigDecimal("0")),
            signatureDish(of("Đĩa hải sản tổng hợp cao cấp", "Premium mixed seafood platter"),
                of("Tôm sú, mực ống, nghêu, sò điệp, cá thu tươi — đĩa hải sản cao cấp nhúng lẩu " +
                   "tươi ngon giữ được vị ngọt tự nhiên của từng loại.",
                   "Tiger prawns, squid, clams, scallops, fresh mackerel — premium seafood platter " +
                   "for hotpot, preserving the natural sweetness of each variety."),
                new BigDecimal("0")),
            signatureDish(of("Sốt chấm Gang Yu tự pha", "Gang Yu signature house dipping sauce"),
                of("Quầy sốt chấm tự phục vụ với hơn 15 loại gia vị — mè rang, tỏi, hành, tương sa tế, " +
                   "dầu hào, trứng sống — tạo ra hương vị chấm độc đáo riêng của mỗi thực khách.",
                   "Self-service dipping sauce bar with over 15 condiments — toasted sesame, garlic, spring onion, " +
                   "sa te sauce, oyster sauce, raw egg — each diner creates their own unique flavor."),
                new BigDecimal("0")),
            signatureDish(of("Nấm kim châm và nấm enoki nhúng lẩu", "Enoki and golden needle mushrooms for hotpot"),
                of("Các loại nấm cao cấp nhập khẩu: nấm kim châm, nấm enoki, nấm nấm bào ngư — " +
                   "giòn ngọt tự nhiên, hấp thụ hoàn hảo vị nước dùng thơm ngon.",
                   "Premium imported mushrooms: enoki, golden needle, oyster mushrooms — " +
                   "naturally crunchy and sweet, perfectly absorbing the aromatic broth."),
                new BigDecimal("0")),
            signatureDish(of("Đậu phụ non nhúng lẩu Hồng Kông", "Silken tofu Hong Kong hotpot style"),
                of("Đậu phụ non Nhật Bản mềm mịn như kem, chỉ cần nhúng nhanh vào nước dùng thơm ngon " +
                   "là đã thấm vị, tan chảy trong miệng đặc biệt với sốt tương đặc.",
                   "Silky smooth Japanese silken tofu, just a quick dip in the fragrant broth " +
                   "absorbs flavor instantly, melting on the palate, especially with thick sauce."),
                new BigDecimal("0")),
            signatureDish(of("Mì tươi và sủi cảo Hồng Kông", "Fresh noodles and Hong Kong dim sum dumplings"),
                of("Mì tươi kéo tay và sủi cảo nhân thịt tôm tự làm theo công thức Hồng Kông, " +
                   "nhúng lẩu mềm dai hoàn hảo, điểm kết thúc bữa ăn truyền thống.",
                   "Hand-pulled fresh noodles and homemade shrimp-pork wonton dumplings by Hong Kong recipe, " +
                   "perfectly soft and chewy in hotpot, the traditional way to finish the meal."),
                new BigDecimal("0")),
            signatureDish(of("Kem tráng miệng và trái cây buffet", "Dessert ice cream and fruit buffet"),
                of("Kết thúc bữa ăn bằng kem tươi nhiều vị, trái cây theo mùa và các loại thạch dessert " +
                   "phong phú trong khu vực tráng miệng riêng biệt sang trọng.",
                   "End the meal with various fresh ice cream flavors, seasonal fruits and diverse " +
                   "dessert jellies in a dedicated premium dessert station."),
                new BigDecimal("0"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("NUOC_DUNG")
                .name(of("Nước dùng lẩu 6 vị", "6-flavor hotpot broth"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu Mala cay đặc biệt", "Special Mala spicy broth"))
                        .description(of("Nước dùng Mala Tứ Xuyên đặc trưng Gang Yu.", "Gang Yu's signature Sichuan Mala broth."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(true)
                        .tags(List.of("mala", "spicy", "sichuan", "buffet"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu xương bò trắng thanh đạm", "Light beef bone broth"))
                        .description(of("Xương bò hầm 8 tiếng trong veo vị ngọt tự nhiên.", "Beef bone simmered 8 hours, crystal clear naturally sweet."))
                        .priceFrom(new BigDecimal("179000"))
                        .priceTo(new BigDecimal("229000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(true)
                        .tags(List.of("bone-broth", "light", "buffet"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("NGUYEN_LIEU")
                .name(of("Nguyên liệu nhúng cao cấp", "Premium hotpot ingredients"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Thịt bò Wagyu A5", "A5 Wagyu beef"))
                        .description(of("Wagyu A5 nhập khẩu thái mỏng buffet.", "Imported A5 Wagyu thinly sliced for buffet."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("người/buffet", "person/buffet"))
                        .combo(false)
                        .buffetItem(true)
                        .tags(List.of("wagyu", "beef", "premium", "buffet"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Hải sản tươi tổng hợp", "Mixed fresh seafood"))
                        .description(of("Tôm, mực, nghêu, sò điệp tươi buffet.", "Fresh shrimp, squid, clams, scallops for buffet."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("người/buffet", "person/buffet"))
                        .combo(false)
                        .buffetItem(true)
                        .tags(List.of("seafood", "shrimp", "scallop", "buffet"))
                        .build()
                ))
                .build()
        );

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "PAID_CAR_PARKING",
            "PRIVATE_ROOM",
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SELF_SERVICE_SAUCE_BAR",
            "SET_LUNCH",
            "BUFFET_STYLE"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe gần nhà hàng trên đường Yên Bái", "Nearby parking lot on Yen Bai street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cổng nhà hàng, vỉa hè Yên Bái", "In front of the restaurant on Yen Bai sidewalk"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Đường Yên Bái trung tâm Hải Châu, xe máy đỗ tự do trước cổng. Ô tô có bãi trả phí gần đó.",
                      "Yen Bai street in central Hai Chau District; motorbikes park free at entrance, paid car parking nearby."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Gang Yu Hotpot — Lẩu Hồng Kông chuẩn vị tại trung tâm Đà Nẵng",
                   "Gang Yu Hotpot — Authentic Hong Kong Style Hotpot in Da Nang City Centre")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại số 87 đường Yên Bái, quận Hải Châu — khu vực sầm uất bậc nhất trung tâm Đà Nẵng — " +
                   "Gang Yu Hotpot mang phong cách ẩm thực lẩu Hồng Kông chính thống đến với thực khách Đà Nẵng. " +
                   "Nhà hàng thiết kế 3 tầng hiện đại với 3 không gian trải nghiệm khác nhau, từ phòng ăn chung " +
                   "rộng thoáng đến các phòng riêng cao cấp, tất cả được chăm chút kỹ lưỡng để mang lại sự thoải " +
                   "mái và đẳng cấp cho mỗi bữa ăn.",
                   "Located at 87 Yen Bai Street, Hai Chau District — Da Nang's most bustling city centre — " +
                   "Gang Yu Hotpot brings authentic Hong Kong style hotpot dining to Da Nang. " +
                   "The 3-floor modern restaurant offers 3 different dining experiences, from open communal dining " +
                   "halls to premium private rooms, all meticulously designed to provide comfort and refinement " +
                   "for every meal.")),
            heading(ContentSection.STORY,
                of("6 Vị Nước Dùng Đặc Sắc — Linh Hồn Của Gang Yu", "6 Signature Broth Flavors — The Soul of Gang Yu")),
            paragraph(ContentSection.STORY,
                of("Điểm nhấn của Gang Yu Hotpot chính là 6 vị nước dùng độc đáo được nghiên cứu và phát triển " +
                   "riêng: Mala cay thơm Tứ Xuyên, xương bò trong veo, chua cay Thái kiểu Hồng Kông, nước dùng " +
                   "nấm thanh đạm, súp cua kem béo và nước dùng miso Nhật Bản. Mỗi nồi lẩu đều được ninh trong " +
                   "nhiều giờ với nguyên liệu tự nhiên, không dùng hương liệu nhân tạo.",
                   "The highlight of Gang Yu Hotpot is its 6 uniquely developed signature broths: " +
                   "aromatic Sichuan Mala spicy, crystal-clear beef bone, Hong Kong-style Thai spicy-sour, " +
                   "light mushroom broth, creamy crab soup and Japanese miso broth. Each hotpot is simmered " +
                   "for hours with natural ingredients, using no artificial flavorings.")),
            heading(ContentSection.STORY,
                of("50+ Nguyên Liệu Nhúng Tươi Ngon Cao Cấp", "50+ Fresh Premium Hotpot Ingredients")),
            paragraph(ContentSection.STORY,
                of("Thực đơn buffet Gang Yu cung cấp hơn 50 loại nguyên liệu cao cấp được tuyển chọn mỗi ngày: " +
                   "thịt bò Wagyu A5, thịt bò Mỹ thái mỏng, hải sản tươi (tôm sú, mực, nghêu, sò điệp), các " +
                   "loại nấm nhập khẩu, rau xanh hữu cơ, đậu phụ non, mì tươi và sủi cảo tự làm. Quầy sốt chấm " +
                   "tự phục vụ với hơn 15 loại gia vị giúp mỗi người tạo ra hương vị chấm riêng độc đáo.",
                   "Gang Yu's buffet menu provides over 50 daily-selected premium ingredients: " +
                   "A5 Wagyu beef, thinly sliced US beef, fresh seafood (tiger prawns, squid, clams, scallops), " +
                   "imported mushroom varieties, organic greens, silken tofu, fresh noodles and homemade wontons. " +
                   "A self-service dipping sauce bar with over 15 condiments lets each diner create their own " +
                   "unique flavor combination.")),
            heading(ContentSection.STORY,
                of("Không gian 3 tầng — 3 Trải nghiệm Riêng Biệt", "3 Floors — 3 Distinct Dining Experiences")),
            paragraph(ContentSection.STORY,
                of("Tầng 1 là khu vực ăn chung rộng mở phong cách Hồng Kông cổ điển với ánh đèn vàng ấm, " +
                   "phù hợp cho bữa ăn gia đình và bạn bè. Tầng 2 có các khu vực ngăn cách nửa riêng tư, " +
                   "thích hợp cho các cuộc gặp nhóm nhỏ. Tầng 3 bố trí 5 phòng riêng sang trọng có thể chứa " +
                   "10-30 khách, lý tưởng cho tiệc sinh nhật, kỷ niệm và tiếp khách doanh nghiệp.",
                   "Floor 1 is an open communal dining area in classic Hong Kong style with warm golden lighting, " +
                   "perfect for family and friend gatherings. Floor 2 features semi-private partitioned areas " +
                   "ideal for small group meetings. Floor 3 has 5 premium private rooms accommodating 10-30 guests, " +
                   "ideal for birthday parties, anniversaries and corporate entertainment.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho mọi dịp — từ bữa trưa văn phòng đến tiệc sự kiện",
                   "Suitable for Every Occasion — From Office Lunch to Event Banquet")),
            paragraph(ContentSection.STORY,
                of("Gang Yu phục vụ set lunch buổi trưa (10:30-14:00) với mức giá ưu đãi và thực đơn đơn giản " +
                   "hơn, phù hợp cho bữa ăn công sở. Buổi tối (17:00-23:00) là thời điểm lý tưởng cho tiệc " +
                   "nhóm đông, liên hoan công ty hoặc hẹn hò lãng mạn. Phòng riêng tầng 3 được trang bị máy " +
                   "chiếu, âm thanh ánh sáng và hỗ trợ trang trí theo yêu cầu.",
                   "Gang Yu offers a set lunch (10:30-14:00) at a preferential price with a simpler menu, " +
                   "suitable for office dining. Evening service (17:00-23:00) is ideal for large group dinners, " +
                   "company parties or romantic dates. Floor 3 private rooms are equipped with projector, " +
                   "sound and lighting systems, with custom decoration support.")),
            heading(ContentSection.STORY,
                of("Vị trí & Di chuyển", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm ngay tại số 87 Yên Bái, quận Hải Châu — trung tâm thành phố Đà Nẵng. " +
                   "Cách cầu Rồng khoảng 2 phút lái xe, cạnh nhiều khách sạn và trung tâm mua sắm lớn. " +
                   "Xe máy đỗ miễn phí trước cổng, ô tô có bãi trả phí gần đó trên đường Yên Bái.",
                   "The restaurant is at 87 Yen Bai Street, Hai Chau District — Da Nang city centre. " +
                   "About 2 minutes by car from Dragon Bridge, next to major hotels and shopping centres. " +
                   "Free motorbike parking at entrance; paid car parking available nearby on Yen Bai Street.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & Ưu đãi", "Booking Policy & Promotions")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trực tuyến hoặc qua hotline để nhận ưu đãi giảm giá khi đặt qua hệ thống. " +
                   "Nhóm từ 10 người yêu cầu đặt trước, phòng riêng cần đặt trước ít nhất 24 giờ. " +
                   "Hỗ trợ xuất hóa đơn VAT theo yêu cầu. Trẻ em dưới 1m miễn phí, từ 1m đến 1m3 " +
                   "tính 50% giá vé buffet.",
                   "Book online or via hotline for discounts on system bookings. Groups of 10 or more " +
                   "require advance booking; private rooms need at least 24 hours advance notice. " +
                   "VAT invoice issuance available on request. Children under 1m free; 1m to 1.3m " +
                   "charged 50% of the buffet price."))
        );

        // ---------- Policy ----------
        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc áp dụng cho nhóm từ 10 khách trở lên hoặc khi đặt phòng riêng.",
                             "Deposit required for groups of 10 or more, or when booking private rooms."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm giá khi đặt bàn qua hệ thống online. Trẻ dưới 1m miễn phí.",
                                 "Discount for online system bookings. Children under 1m dine free."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Không áp dụng vào ngày lễ Tết. Chi tiết liên hệ nhà hàng.",
                              "Not applicable on public holidays. Contact restaurant for details."))
            .blackoutRules(List.of(
                blackoutGregorian(1, 1, of("Tết Dương Lịch — nghỉ", "New Year's Day — closed")),
                blackoutGregorian(12, 31, of("Đêm Giao Thừa — giá đặc biệt", "New Year's Eve — special pricing"))
            ))
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(15)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu trước khi thanh toán.",
                             "VAT invoice on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí dịch vụ 5% áp dụng cho phòng riêng.",
                                   "5% service charge for private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào.", "Outside drinks not permitted."))
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
                .id("gy-tb-4")
                .name(of("Bàn buffet 4 người", "Buffet table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(5)
                .totalTables(30)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn tầng 1 hoặc tầng 2.", "Standard table on floor 1 or 2."))
                .build(),
            TableType.builder()
                .id("gy-tb-8-vip")
                .name(of("Bàn VIP 8 người tầng 2", "VIP table for 8, floor 2"))
                .seats(8)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(10)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Khu vực bán riêng tư tầng 2, phục vụ ưu tiên.", "Semi-private area on floor 2, priority service."))
                .build(),
            TableType.builder()
                .id("gy-pr-30")
                .name(of("Phòng riêng 30 khách tầng 3", "Private room for 30, floor 3"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(5)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng tầng 3 có máy chiếu, âm thanh, trang trí theo yêu cầu.",
                         "Floor 3 private room with projector, sound system and custom decoration."))
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
            .wardName(of("Phường Thuận Phước", "Thuan Phuoc Ward"))
            .addressLine(of("87 Yên Bái, Q. Hải Châu, TP. Đà Nẵng",
                            "87 Yen Bai Street, Hai Chau District, Da Nang City"))
            .location(new double[]{108.222446, 16.064333})
            .name(of("Nhà hàng Gang Yu Hotpot Đà Nẵng", "Gang Yu Hotpot Restaurant Da Nang"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu Hồng Kông 6 vị nước dùng đặc sắc, 50+ nguyên liệu tươi cao cấp — 3 tầng hiện đại trung tâm Đà Nẵng.",
                                 "Hong Kong hotpot buffet with 6 signature broths, 50+ premium fresh ingredients — 3 modern floors in Da Nang city centre."))
            .description(of("Gang Yu Hotpot tại 87 Yên Bái, Hải Châu mang phong cách lẩu Hồng Kông đích thực với 6 vị nước dùng " +
                            "đặc sắc và hơn 50 nguyên liệu tươi cao cấp. Nhà hàng 3 tầng hiện đại sức chứa 280 khách, " +
                            "5 phòng riêng, phục vụ đầy đủ từ set lunch trưa đến buffet lẩu tối.",
                            "Gang Yu Hotpot at 87 Yen Bai, Hai Chau brings authentic Hong Kong hotpot style with 6 signature " +
                            "broths and over 50 premium fresh ingredients. The 3-floor modern restaurant seats 280 guests, " +
                            "5 private rooms, serving from set lunch to full evening hotpot buffet."))
            .phone("0643331082")
            .bookingHotline("0643331082")
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
            .menuSections(menuSections)
            .parking(parking)
            .amenityCodes(amenityCodes)
            .images(images)
            .menuImages(menuImages)
            .content(content)
            .policy(policy)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}
