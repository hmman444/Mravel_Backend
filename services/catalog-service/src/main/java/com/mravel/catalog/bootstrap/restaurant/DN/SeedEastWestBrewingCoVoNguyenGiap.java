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
public class SeedEastWestBrewingCoVoNguyenGiap {

    @Bean
    CommandLineRunner seedEastWestBrewingCoVoNguyenGiapRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedEastWestBrewingCoVoNguyenGiap] start");

            RestaurantDoc doc = restaurantEastWestBrewingCoVoNguyenGiap();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedEastWestBrewingCoVoNguyenGiap] done");
        };
    }

    // =====================================================================
    //  EAST WEST BREWING CO. - VÕ NGUYÊN GIÁP - ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantEastWestBrewingCoVoNguyenGiap() {
        String slug = "east-west-brewing-co-vo-nguyen-giap-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-0-normal-2246546755462.webp",
                of("Không gian East West Brewing Co. Võ Nguyên Giáp Đà Nẵng", "East West Brewing Co. interior on Vo Nguyen Giap Da Nang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-1-normal-2246546855463.webp",
                of("Khu vực uống bia thủ công nội địa East West", "East West craft beer drinking area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-2-normal-2246546955464.webp",
                of("Không gian trong nhà hiện đại thoáng rộng", "Modern and spacious indoor space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-3-normal-2246547255465.webp",
                of("Sân ngoài trời East West Brewing Đà Nẵng", "East West Brewing outdoor terrace Da Nang"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-4-normal-2246547455466.webp",
                of("Hệ thống bồn ủ bia thủ công trưng bày", "Craft beer fermentation tanks on display"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-5-normal-2246547855467.webp",
                of("Món Á-Âu đặc sắc tại East West Brewing", "Distinctive Asian-Western dishes at East West Brewing"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-11-normal-2246548055468.webp",
                of("Phòng riêng East West Brewing Đà Nẵng", "Private room at East West Brewing Da Nang"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-6-normal-2246548255469.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-7-normal-2246548455470.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-8-normal-2246548555471.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-9-normal-2246548755472.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-east-west-brewing-vo-nguyen-giap-10-normal-2246548855473.webp",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-1.jpg",
                of("East West Brewing Co. không gian nhìn từ ngoài", "East West Brewing Co. exterior view"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-2.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-3.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-4.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-5.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-6.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-7.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-8.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-9.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/eastwestbrewingco/Nha-hang-east-west-brewing-vo-nguyen-giap-10.jpg",
                of("", ""), false, 21)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-1.jpg",
                of("Menu East West Brewing Co. trang 1", "East West Brewing Co. menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/East%20West%20Brewing%20Co/Menu-East-West-Brewing-Co-15.jpg",
                of("", ""), false, 14)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("CRAFT_BEER")
                .name(of("Bia thủ công nội địa East West", "East West domestic craft beer"))
                .region("VIETNAM")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực Á-Âu sáng tạo", "Creative Asian-Western fusion cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("WESTERN")
                .name(of("Ẩm thực Tây", "Western cuisine"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 23, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",        of("Tụ tập bạn bè thưởng thức craft beer", "Friends gathering for craft beer")),
            suitable("COMPANY_PARTY",  of("Liên hoan, tiệc công ty", "Company party and celebration")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật theo phong cách brewery", "Brewery-style birthday party")),
            suitable("FAMILY",         of("Ăn uống gia đình thoải mái", "Relaxed family dining")),
            suitable("COUPLE",         of("Hẹn hò tối tại brewery view bờ biển", "Evening date at beachside brewery")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách doanh nghiệp không gian thoáng", "Business meal in spacious setting")),
            suitable("GROUP_MEETING",  of("Họp nhóm, gặp gỡ đối tác", "Group meeting and partner gathering")),
            suitable("COMPANY_EVENT",  of("Sự kiện công ty, team building", "Corporate event and team building")),
            suitable("GROUP_TRAVEL",   of("Nhóm du khách trải nghiệm bia thủ công Việt", "Travel groups experiencing Vietnamese craft beer")),
            suitable("ANNIVERSARY",    of("Kỷ niệm đặc biệt tại brewery đẳng cấp", "Special anniversary at upscale brewery"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",   of("Hiện đại, công nghiệp", "Modern and industrial")),
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively")),
            ambience("CASUAL",   of("Thân thiện, thoải mái", "Friendly and casual")),
            ambience("COZY",     of("Ấm cúng khu trong nhà", "Cozy indoor area"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(80)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Salad Cá Ngừ Bơ Tươi — salad bơ avocado tươi cùng cá ngừ", "Fresh Avocado Tuna Salad")),
            signatureDishL(of("Ribeye Steak — bò Úc nướng sốt bơ thảo mộc", "Ribeye Steak — Australian beef with herb butter")),
            signatureDishL(of("Hải Sản Cajun — tôm mực nướng sốt Cajun cay nồng", "Cajun Seafood — spicy Cajun grilled shrimp and squid")),
            signatureDishL(of("Sụn Gà Xốc Muối Tôm — giòn giòn, đậm vị", "Chicken Cartilage with Shrimp Salt — crispy and flavorful")),
            signatureDishL(of("Mỳ Ý Kem Bia — pasta kem với sốt bia East West độc đáo", "Beer Cream Pasta — creamy pasta with East West beer sauce")),
            signatureDishL(of("Hoành Thánh Vịt — chiên giòn sốt tương đen", "Duck Wonton — crispy fried with black bean sauce")),
            signatureDishL(of("Cơm Chiên Hải Sản — cơm chiên kiểu Á với tôm, mực, sò điệp", "Seafood Fried Rice — Asian-style with shrimp, squid, scallops")),
            signatureDishL(of("Xúc Xích Homemade East West — xúc xích tự làm, nướng trên lửa than", "East West Homemade Sausage — house-made grilled over charcoal")),
            signatureDishL(of("Bia Tươi East West IPA — bia thủ công vị đắng nhẹ, hương hoa quả", "East West Fresh IPA — craft beer with mild bitterness and fruity aroma")),
            signatureDishL(of("Bia Tươi East West Lager — bia lager tươi mát, thơm malt đặc trưng", "East West Fresh Lager — refreshing lager with distinctive malt aroma"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("CRAFT_BEERS")
                .name(of("Bia thủ công East West tươi tại chỗ", "East West on-tap craft beers"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("East West IPA 500ml", "East West IPA 500ml"))
                        .description(of("Bia India Pale Ale thủ công, vị đắng nhẹ hương hoa quả nhiệt đới, ủ tại chỗ.", "On-site brewed India Pale Ale with mild bitterness and tropical fruit aroma."))
                        .priceFrom(new BigDecimal("80000"))
                        .priceTo(new BigDecimal("100000"))
                        .unit(of("ly", "glass"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("craft-beer", "ipa", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("East West Lager 500ml", "East West Lager 500ml"))
                        .description(of("Bia lager thủ công mát lạnh, thơm malt nhẹ nhàng, phù hợp khi kết hợp với đồ ăn.", "Refreshing craft lager with gentle malt aroma, pairs well with food."))
                        .priceFrom(new BigDecimal("70000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("ly", "glass"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("craft-beer", "lager"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi xe gần nhà hàng trên đường Võ Nguyên Giáp, miễn phí", "Free parking lot near restaurant on Vo Nguyen Giap Street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe gần nhà hàng, miễn phí", "Free parking near restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi cả ô tô và xe máy tại bãi gần nhà hàng trên trục đường Võ Nguyên Giáp.",
                      "Free car and motorbike parking at lot near restaurant on Vo Nguyen Giap road."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "AIR_CONDITIONING",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "LIVE_MUSIC",
            "EVENT_DECOR",
            "KIDS_CHAIR",
            "CRAFT_BEER_TAP",
            "BEER_BREWING_DISPLAY",
            "PROJECTOR"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu East West Brewing Co. Võ Nguyên Giáp", "About East West Brewing Co. Vo Nguyen Giap")),
            paragraph(ContentSection.STORY,
                of("East West Brewing Co. là thương hiệu bia thủ công tiên phong tại Việt Nam, ra đời từ năm 2014 tại TP. Hồ Chí Minh " +
                   "với triết lý kết hợp hài hòa giữa văn hóa ẩm thực phương Đông và kỹ thuật ủ bia phương Tây. " +
                   "Chi nhánh Đà Nẵng trên đường Võ Nguyên Giáp — con đường biển sầm uất nhất thành phố — " +
                   "mang đến không gian brewery hiện đại với hệ thống bồn ủ bia trưng bày ngay trong nhà hàng, " +
                   "sức chứa 300 khách và 2 phòng riêng sang trọng dành cho tiệc nhóm và sự kiện.",
                   "East West Brewing Co. is Vietnam's pioneering craft beer brand, founded in 2014 in Ho Chi Minh City " +
                   "with the philosophy of harmoniously blending Eastern culinary culture with Western brewing techniques. " +
                   "The Da Nang branch on Vo Nguyen Giap Street — the city's busiest coastal road — " +
                   "offers a modern brewery setting with fermentation tanks on display inside the restaurant, " +
                   "a capacity of 300 guests, and 2 elegant private rooms for group parties and events.")),
            heading(ContentSection.STORY, of("Xưởng bia thủ công — điểm nhấn độc đáo", "On-site craft brewery — a unique highlight")),
            paragraph(ContentSection.STORY,
                of("Điểm đặc biệt nhất của East West Brewing Co. Đà Nẵng chính là hệ thống xưởng ủ bia (microbrewery) ngay tại chỗ, " +
                   "nơi thực khách có thể nhìn thấy toàn bộ quy trình ủ bia thủ công từ bồn lên men đến hệ thống giải nhiệt. " +
                   "Bia được phục vụ tươi trực tiếp từ tap — không qua đóng chai, không qua lưu kho — đảm bảo hương vị tươi nhất. " +
                   "Các dòng bia nội địa thủ công như IPA, Lager, Stout và Wheat Beer thay đổi theo mùa, " +
                   "tạo nên trải nghiệm thưởng thức bia độc đáo khó tìm thấy ở bất kỳ đâu khác tại Đà Nẵng.",
                   "East West Brewing Co. Da Nang's most distinctive feature is its on-site microbrewery, " +
                   "where diners can observe the entire craft beer brewing process from fermentation tanks to cooling systems. " +
                   "Beer is served fresh directly from the tap — no bottling, no warehousing — ensuring the freshest possible flavor. " +
                   "Seasonal craft beer varieties including IPA, Lager, Stout, and Wheat Beer change throughout the year, " +
                   "creating a unique beer tasting experience hard to find anywhere else in Da Nang.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("East West Brewing Co. Võ Nguyên Giáp có tổng sức chứa 300 khách, chia thành 3 khu vực: " +
                   "khu trong nhà thiết kế phong cách công nghiệp hiện đại sức chứa 150 khách, " +
                   "sân ngoài trời thoáng mát nhìn ra đường Võ Nguyên Giáp sức chứa 100 khách, " +
                   "và 2 phòng riêng (Phòng VIP 30 khách và phòng họp 15 khách) — lý tưởng cho tiệc công ty và sự kiện riêng tư. " +
                   "Toàn bộ không gian được thiết kế theo phong cách brewery hiện đại với bồn ủ bia trưng bày làm điểm nhấn trang trí.",
                   "East West Brewing Co. Vo Nguyen Giap has a total capacity of 300 guests across 3 areas: " +
                   "the modern industrial-style indoor section for 150 guests, " +
                   "a breezy outdoor terrace overlooking Vo Nguyen Giap Street for 100 guests, " +
                   "and 2 private rooms (VIP Room for 30 guests and meeting room for 15) — ideal for corporate parties and private events. " +
                   "The entire space is designed in a modern brewery style with fermentation tanks as decorative centerpieces.")),
            heading(ContentSection.STORY, of("Thực đơn Á-Âu sáng tạo", "Creative Asian-Western fusion menu")),
            paragraph(ContentSection.STORY,
                of("Ngoài bia thủ công, East West Brewing Co. nổi tiếng với thực đơn Á-Âu sáng tạo phù hợp để nhâm nhi cùng bia. " +
                   "Salad Cá Ngừ Bơ Tươi, Ribeye Steak bò Úc, Hải Sản Cajun cay nồng, Mỳ Ý Kem Bia với sốt bia East West độc quyền " +
                   "hay Hoành Thánh Vịt chiên giòn là những món được thực khách đặt nhiều nhất. " +
                   "Đặc biệt, Xúc Xích Homemade East West được làm thủ công tại bếp và nướng trên lửa than — " +
                   "kết hợp hoàn hảo với cốc bia IPA tươi mới khai thác.",
                   "Beyond craft beer, East West Brewing Co. is renowned for its creative Asian-Western fusion menu designed to pair with beer. " +
                   "Fresh Avocado Tuna Salad, Australian Ribeye Steak, spicy Cajun Seafood, Beer Cream Pasta with East West's exclusive beer sauce, " +
                   "and crispy Duck Wonton are among the most-ordered dishes. " +
                   "Especially notable is East West's Homemade Sausage, handcrafted in-house and grilled over charcoal — " +
                   "a perfect pairing with a freshly tapped IPA.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("East West Brewing Co. Đà Nẵng là điểm đến lý tưởng cho nhóm bạn bè muốn thưởng thức bia thủ công chất lượng, " +
                   "các cặp đôi tìm kiếm không gian brewery độc đáo, các đoàn công tác muốn tiếp khách tại không gian hiện đại " +
                   "hay khách du lịch muốn trải nghiệm một phần văn hóa bia thủ công đang phát triển mạnh tại Đà Nẵng. " +
                   "2 phòng riêng lý tưởng cho tiệc sinh nhật nhóm nhỏ, họp nhóm và sự kiện công ty.",
                   "East West Brewing Co. Da Nang is the ideal destination for friend groups wanting quality craft beer, " +
                   "couples looking for a unique brewery setting, business parties seeking modern entertainment venues, " +
                   "or travelers wanting to experience Da Nang's growing craft beer culture. " +
                   "2 private rooms are ideal for small group birthday parties, group meetings, and corporate events.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("WiFi miễn phí tốc độ cao, hệ thống âm thanh ánh sáng chuyên nghiệp phục vụ sự kiện. " +
                   "Phòng riêng có thể tổ chức tiệc tối đa 30 khách với dịch vụ MC, trang trí theo yêu cầu. " +
                   "Màn chiếu và máy chiếu phục vụ họp nhóm và trình chiếu. " +
                   "Chấp nhận Visa/Master, xuất hóa đơn VAT. Miễn phí bãi đỗ xe ô tô và xe máy gần nhà hàng.",
                   "High-speed free WiFi, professional sound and lighting system for events. " +
                   "Private rooms can host up to 30 guests with MC service and customized decorations. " +
                   "Projector and screen for group meetings and presentations. " +
                   "Visa/Master accepted, VAT invoices available. Free car and motorbike parking nearby.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("East West Brewing Co. tọa lạc trên đường Võ Nguyên Giáp — con đường ven biển chạy dọc bờ đông Đà Nẵng, " +
                   "thuộc quận Sơn Trà, cách bãi biển Mỹ Khê chỉ 200m. Vị trí cực kỳ thuận lợi cho du khách ở khu vực bãi biển " +
                   "hay trung tâm thành phố (cách trung tâm ~4km). Taxi, Grab và xe máy đều tiếp cận dễ dàng.",
                   "East West Brewing Co. is located on Vo Nguyen Giap Street — the coastal road running along Da Nang's eastern shore " +
                   "in Son Tra District, just 200m from My Khe Beach. Extremely convenient for guests staying in the beach area " +
                   "or city center (about 4km away). Easily accessible by taxi, Grab, and motorbike.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & phòng riêng", "Booking & private room policy")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn thường nhận trước ít nhất 2 tiếng. Phòng riêng cần đặt trước ít nhất 1 ngày và đặt cọc 30%. " +
                   "Hủy phòng riêng trước 48 giờ được hoàn cọc; dưới 48 giờ mất cọc. " +
                   "Happy hour bia thủ công mỗi ngày 14:00-19:00 giảm giá 20% cho tất cả dòng bia tươi. " +
                   "Không nhận thức uống từ ngoài vào.",
                   "Regular table bookings accepted at least 2 hours in advance. Private rooms must be booked 1 day ahead with 30% deposit. " +
                   "Full refund for cancellations more than 48 hours before; deposit forfeited within 48 hours. " +
                   "Craft beer happy hour daily 14:00-19:00 with 20% discount on all fresh draft beers. " +
                   "Outside beverages are not permitted.")),
            mapBlock(ContentSection.STORY, new double[]{108.2461, 16.0680})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng happy hour ngày 1/1", "Happy hour not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi Valentine", "Promotions not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotions not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi Quốc khánh", "Promotions not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotions not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(15)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng khi đặt phòng riêng hoặc nhóm từ 15 khách. Đặt cọc 30% tổng bill dự kiến.",
                             "Applies to private room bookings or groups of 15+. 30% deposit of estimated total bill."))
            .hasPromotion(true)
            .promotionSummary(of("Happy hour bia tươi 14:00-19:00 hàng ngày giảm 20%. Ưu đãi 10% khi đặt bàn online.",
                                 "Daily beer happy hour 14:00-19:00 with 20% off. 10% discount for online table bookings."))
            .promotionMaxDiscountPercent(20)
            .promotionNote(of("Happy hour không áp dụng ngày lễ tết.", "Happy hour not applicable on public holidays."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu trước thanh toán.", "VAT invoices on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho nhóm sự kiện và phòng riêng.", "5% service charge for events and private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không nhận đồ uống ngoài — nhà hàng có hơn 10 loại bia thủ công tươi.", "Outside drinks not allowed — restaurant has 10+ fresh craft beers on tap."))
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-indoor")
                .name(of("Bàn trong nhà 4 người", "Indoor table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn 4 người khu trong nhà, gần bồn ủ bia thủ công.", "Indoor table for 4, near craft beer fermentation tanks."))
                .build(),
            TableType.builder()
                .id("tb-6-outdoor")
                .name(of("Bàn ngoài trời view đường biển 6 người", "Outdoor beachside-view table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn ngoài trời view đường Võ Nguyên Giáp ven biển, thoáng mát.", "Outdoor table with Vo Nguyen Giap coastal road view, breezy."))
                .build(),
            TableType.builder()
                .id("tb-30-private-vip")
                .name(of("Phòng VIP riêng 10-30 khách", "VIP private room 10-30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng VIP, máy chiếu, âm thanh, trang trí theo yêu cầu. Phù hợp tiệc công ty và sự kiện nhóm.", "VIP private room with projector, sound system, custom decoration. Ideal for corporate parties and group events."))
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

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Sơn Trà", "Son Tra District"))
            .wardName(of("P. Thọ Quang", "Tho Quang Ward"))
            .addressLine(of("Võ Nguyên Giáp, P. Thọ Quang, Q. Sơn Trà, TP. Đà Nẵng",
                            "Vo Nguyen Giap Street, Tho Quang Ward, Son Tra District, Da Nang"))
            .location(new double[]{108.2461, 16.0680})
            .name(of("East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng", "East West Brewing Co. - Vo Nguyen Giap Da Nang"))
            .slug(slug)
            .restaurantType(RestaurantType.BAR)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng bia thủ công tiên phong Việt Nam với xưởng ủ tại chỗ, thực đơn Á-Âu sáng tạo trên đường biển Võ Nguyên Giáp Đà Nẵng.",
                                 "Vietnam's pioneering craft brewery with on-site fermentation, creative Asian-Western menu on Da Nang's coastal Vo Nguyen Giap Street."))
            .description(of("East West Brewing Co. Võ Nguyên Giáp — chi nhánh Đà Nẵng của thương hiệu bia thủ công hàng đầu VN thành lập 2014. " +
                            "Sức chứa 300 khách, 2 phòng riêng, hệ thống bồn ủ bia trưng bày. Thực đơn Á-Âu sáng tạo: Ribeye Steak, Hải Sản Cajun, Mỳ Ý Kem Bia. " +
                            "Happy hour hàng ngày 14:00-19:00. Bãi xe miễn phí, cách bãi biển Mỹ Khê 200m.",
                            "East West Brewing Co. Vo Nguyen Giap — Da Nang branch of Vietnam's leading craft beer brand founded in 2014. " +
                            "300-guest capacity, 2 private rooms, fermentation tanks on display. Creative Asian-Western menu: Ribeye Steak, Cajun Seafood, Beer Cream Pasta. " +
                            "Daily happy hour 14:00-19:00. Free parking, 200m from My Khe Beach."))
            .phone("0236 3835100")
            .website("https://pasgo.vn/nha-hang/east-west-brewing-co--vo-nguyen-giap-4806")
            .bookingHotline("0236 3835100")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("500000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
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
