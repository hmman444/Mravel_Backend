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
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedLauNamGiaKhanhMoLao {

    @Bean
    CommandLineRunner seedLauNamGiaKhanhMoLaoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauNamGiaKhanhMoLao] start");

            RestaurantDoc doc = restaurantLauNamGiaKhanhMoLao();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauNamGiaKhanhMoLao] done");
        };
    }

    // =====================================================================
    //          LẨU NẤM GIA KHÁNH MỘ LAO - HÀ ĐÔNG, HÀ NỘI
    // =====================================================================

    private static RestaurantDoc restaurantLauNamGiaKhanhMoLao() {
        String slug = "lau-nam-gia-khanh-mo-lao";

        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-1-normal-2253040056441.webp",
                of("Không gian Lẩu Nấm Gia Khánh Mộ Lao", "Lau Nam Gia Khanh Mo Lao space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-2-normal-2253040256442.webp",
                of("Khu vực bàn ăn hiện đại", "Modern dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-3-normal-2253040356443.webp",
                of("Các loại nấm tươi đặc sắc", "Specialty fresh mushrooms"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-4-normal-2253040456444.webp",
                of("Nồi lẩu nấm đặc trưng", "Signature mushroom hotpot"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-5-normal-2253040556445.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-6-normal-2253040656446.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-7-normal-2253040756447.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-8-normal-2253040856448.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-nam-gia-khanh-1-normal-2261115457304.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-nam-gia-khanh-2-normal-2261115657305.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-nam-gia-khanh-3-normal-2261115757306.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-lau-nam-gia-khanh-4-normal-2261115857307.webp",
                of("", ""), false, 11)
        );

        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-1.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-2.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-3.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-4.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 4", "Menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-5.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 5", "Menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-6.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 6", "Menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-7.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 7", "Menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-8.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 8", "Menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhnguyenvanloc/nha-hang-lau-nam-gia-khanh-nguyen-van-loc-menu-9.jpg",
                of("Menu Lẩu Nấm Gia Khánh trang 9", "Menu page 9"), false, 8)
        );

        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("MUSHROOM_HOTPOT")
                .name(of("Lẩu nấm", "Mushroom hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VEGETARIAN_FRIENDLY")
                .name(of("Thân thiện với chay", "Vegetarian-friendly"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HEALTHY")
                .name(of("Ăn uống lành mạnh", "Healthy cuisine"))
                .region("ASIA")
                .build()
        );

        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
        );

        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Bữa ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("VEGETARIAN",    of("Thực khách ăn chay", "Vegetarian guests")),
            suitable("HEALTH_CONSCIOUS", of("Ăn uống lành mạnh", "Health-conscious dining")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa công sở", "Office lunch"))
        );

        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",   of("Hiện đại", "Modern")),
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",  of("Sang trọng nhẹ nhàng", "Elegant")),
            ambience("NATURAL",  of("Gần gũi thiên nhiên", "Natural"))
        );

        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu nấm thập cẩm Gia Khánh", "Gia Khanh assorted mushroom hotpot")),
            signatureDishL(of("Nấm Linh Chi hầm gà", "Lingzhi mushroom and chicken soup")),
            signatureDishL(of("Nấm Đông Cô xào thịt bò", "Shiitake mushroom stir-fried with beef")),
            signatureDishL(of("Nấm Kim Châm cuộn thịt ba chỉ", "Enoki mushroom wrapped with pork belly")),
            signatureDishL(of("Nấm Bào Ngư hấp gừng hành", "Oyster mushroom steamed with ginger and scallion")),
            signatureDishL(of("Nấm Vân Chi nấu canh", "Coriolus mushroom soup")),
            signatureDishL(of("Combo lẩu nấm chay đặc biệt", "Special vegetarian mushroom hotpot combo")),
            signatureDishL(of("Nước dùng nấm thanh đạm không dầu mỡ", "Clear mushroom broth without oil")),
            signatureDishL(of("Đậu hũ chiên sốt nấm", "Fried tofu with mushroom sauce")),
            signatureDishL(of("Rau củ tươi nhúng lẩu", "Fresh vegetables for hotpot"))
        );

        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("COMBO_LAU")
                .name(of("Combo lẩu nấm", "Mushroom hotpot combos"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Combo lẩu nấm 557k (2 người)", "Mushroom hotpot combo 557k (2 persons)"))
                        .description(of("Nồi lẩu nấm thập cẩm, kèm thịt bò/gà, đậu hũ, rau củ tươi và bún.", "Assorted mushroom hotpot with beef/chicken, tofu, fresh vegetables and noodles."))
                        .priceFrom(new BigDecimal("557000"))
                        .priceTo(new BigDecimal("557000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "hotpot", "mushroom"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo lẩu nấm 1.055k (4 người)", "Mushroom hotpot combo 1055k (4 persons)"))
                        .description(of("Combo đầy đủ cho 4 người, nấm thập cẩm cao cấp, kèm nhiều nguyên liệu phong phú.", "Full combo for 4 persons with premium assorted mushrooms and rich ingredients."))
                        .priceFrom(new BigDecimal("1055000"))
                        .priceTo(new BigDecimal("1055000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "hotpot", "mushroom", "group"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("SET_LAU")
                .name(of("Set lẩu nấm", "Mushroom hotpot sets"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set lẩu nấm 740k", "Mushroom hotpot set 740k"))
                        .description(of("Set đặc biệt gồm nấm cao cấp, thịt bò mỹ, hải sản và rau tươi.", "Special set with premium mushrooms, US beef, seafood and fresh vegetables."))
                        .priceFrom(new BigDecimal("740000"))
                        .priceTo(new BigDecimal("740000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "premium", "mushroom"))
                        .build()
                ))
                .build()
        );

        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe Hoàng Thành Villas và đường nội khu", "Hoang Thanh Villas parking and internal road"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe trong khu Hoàng Thành Villas", "Parking within Hoang Thanh Villas compound"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Khu đô thị Hoàng Thành Villas có bãi đỗ xe rộng rãi, miễn phí cho khách nhà hàng.", "Hoang Thanh Villas complex has spacious free parking for restaurant guests."))
            .build();

        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "PRIVATE_ROOM",
            "WIFI",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE",
            "VAT_INVOICE",
            "VISA_MASTER",
            "KIDS_CHAIR",
            "VEGETARIAN_MENU",
            "TAKEAWAY"
        );

        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of(
                "Giới thiệu Lẩu Nấm Gia Khánh Mộ Lao",
                "About Lau Nam Gia Khanh Mo Lao")),
            paragraph(ContentSection.STORY, of(
                "Lẩu Nấm Gia Khánh Mộ Lao là nhà hàng chuyên lẩu nấm và các món từ nấm uy tín tại Hà Đông, Hà Nội, " +
                "tọa lạc trong khu đô thị Hoàng Thành Villas, phường Mộ Lao. Nhà hàng nổi bật với triết lý ẩm thực " +
                "lành mạnh — sử dụng các loại nấm tươi nguyên chất, không chất bảo quản, phù hợp cho cả người ăn chay " +
                "lẫn thực khách ưa thích món ăn thanh đạm.",
                "Lau Nam Gia Khanh Mo Lao is a reputable mushroom hotpot restaurant in Ha Dong, Hanoi, located in the " +
                "Hoang Thanh Villas urban area, Mo Lao ward. The restaurant stands out with its healthy cuisine philosophy — " +
                "using pure fresh mushrooms without preservatives, suitable for vegetarians and health-conscious diners alike.")),
            heading(ContentSection.STORY, of(
                "Không gian & sức chứa",
                "Space & capacity")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng sở hữu không gian hiện đại, sạch sẽ với sức chứa 200 thực khách. Hệ thống 4 phòng riêng " +
                "với đầy đủ âm thanh ánh sáng đáp ứng các nhóm từ 10 đến 40 khách. Nội thất được thiết kế theo phong " +
                "cách minimalist gần gũi thiên nhiên, tạo cảm giác thư giãn và dễ chịu suốt bữa ăn.",
                "The restaurant has a modern, clean space with capacity for 200 guests. Four private rooms with full sound " +
                "and lighting accommodate groups from 10 to 40 guests. The interior is designed in a nature-inspired minimalist " +
                "style, creating a relaxing and comfortable atmosphere throughout the meal.")),
            heading(ContentSection.STORY, of(
                "Thực đơn & món đặc sắc",
                "Menu & signature dishes")),
            paragraph(ContentSection.STORY, of(
                "Thực đơn Gia Khánh phong phú với hơn 20 loại nấm khác nhau: Linh Chi, Đông Cô, Kim Châm, Bào Ngư, " +
                "Nấm Vân Chi, Nấm Hương, Nấm Truffle... Nước dùng lẩu được nấu từ xương gà và nấm hầm tự nhiên, " +
                "ngọt thanh và tốt cho sức khỏe. Khách có thể chọn lẩu chay hoàn toàn hoặc lẩu kết hợp thịt bò, gà, " +
                "hải sản tùy sở thích. Các combo và set được thiết kế sẵn để tiện đặt cho nhóm.",
                "The Gia Khanh menu is rich with over 20 different types of mushrooms: Lingzhi, Shiitake, Enoki, Oyster, " +
                "Coriolus, Mushroom, Truffle... The hotpot broth is made from chicken bones and naturally simmered mushrooms, " +
                "sweet and healthy. Guests can choose a fully vegetarian hotpot or combined with beef, chicken, or seafood " +
                "as preferred. Pre-designed combos and sets make group ordering convenient.")),
            heading(ContentSection.STORY, of(
                "Ẩm thực lành mạnh & phù hợp nhiều đối tượng",
                "Healthy cuisine & suitable for many groups")),
            paragraph(ContentSection.STORY, of(
                "Điểm khác biệt lớn nhất của Lẩu Nấm Gia Khánh là cam kết nguyên liệu sạch và không dùng chất phụ gia. " +
                "Nhà hàng phù hợp đặc biệt cho người ăn kiêng, người cao tuổi cần ăn uống thanh đạm, người ăn chay " +
                "và cả gia đình có trẻ nhỏ. Không gian yên tĩnh thoải mái cũng phù hợp cho các buổi họp nhóm, " +
                "tiếp khách đối tác hay tổ chức sinh nhật nhẹ nhàng.",
                "The biggest differentiator of Lau Nam Gia Khanh is its commitment to clean ingredients without additives. " +
                "The restaurant is especially suitable for dieters, the elderly who prefer light meals, vegetarians and " +
                "families with young children. The quiet and comfortable space also suits group meetings, business " +
                "entertainment or low-key birthday celebrations.")),
            heading(ContentSection.STORY, of(
                "Dịch vụ & tiện ích",
                "Services & amenities")),
            paragraph(ContentSection.STORY, of(
                "Ngoài thực đơn phong phú, Gia Khánh cung cấp dịch vụ đặt tiệc trọn gói cho sinh nhật và liên hoan, " +
                "kèm trang trí, bánh kem và MC theo yêu cầu. Bãi đỗ xe rộng rãi miễn phí trong khu Hoàng Thành Villas " +
                "là lợi thế lớn so với các nhà hàng khu vực nội đô.",
                "In addition to a diverse menu, Gia Khanh provides complete party packages for birthdays and celebrations, " +
                "with decoration, birthday cake and MC on request. The spacious free parking within Hoang Thanh Villas " +
                "is a significant advantage over inner-city restaurants.")),
            heading(ContentSection.STORY, of(
                "Vị trí & di chuyển",
                "Location & getting there")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng nằm tại BT01-1 Hoàng Thành Villas, phường Mộ Lao, quận Hà Đông, cách trung tâm Hà Nội khoảng 10km. " +
                "Dễ dàng di chuyển từ nội đô qua đường Lê Văn Lương hoặc Tố Hữu. Khu đô thị yên tĩnh, có bãi đỗ xe " +
                "rộng rãi — lý tưởng cho khách đi ô tô từ khu vực Hà Đông, Thanh Xuân và các quận lân cận.",
                "The restaurant is located at BT01-1 Hoang Thanh Villas, Mo Lao ward, Ha Dong district, about 10km from " +
                "Hanoi city center. Easy access from the inner city via Le Van Luong or To Huu road. A quiet residential area " +
                "with spacious parking — ideal for guests driving from Ha Dong, Thanh Xuan and nearby districts.")),
            heading(ContentSection.STORY, of(
                "Chính sách đặt bàn & lý do nên chọn",
                "Booking policy & why choose us")),
            paragraph(ContentSection.STORY, of(
                "Gia Khánh nhận đặt bàn qua điện thoại và hệ thống online, với chính sách đặt cọc linh hoạt. " +
                "Cam kết nguyên liệu sạch, thực đơn đa dạng thân thiện chay — mặn, bãi đỗ xe miễn phí và không gian " +
                "riêng tư là những lý do khiến Lẩu Nấm Gia Khánh Mộ Lao trở thành điểm đến ẩm thực lý tưởng cho " +
                "gia đình và các nhóm tại khu vực Hà Đông.",
                "Gia Khanh accepts bookings by phone and online, with flexible deposit policies. The commitment to clean " +
                "ingredients, diverse vegetarian-friendly menu, free parking and private dining space are the reasons " +
                "that make Lau Nam Gia Khanh Mo Lao an ideal culinary destination for families and groups in the Ha Dong area.")),
            mapBlock(ContentSection.STORY, new double[]{105.7847, 20.9796})
        );

        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng khi đặt phòng riêng hoặc nhóm từ 6 khách.", "Applies to private room bookings or groups of 6 or more."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% khi đặt qua hệ thống, không áp dụng ngày lễ.", "10% discount when booking online, not applicable on holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Liên hệ nhà hàng để biết ưu đãi hiện tại.", "Contact the restaurant for current promotions."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí 5% áp dụng tại phòng riêng.", "5% service charge in private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không mang đồ uống từ ngoài vào.", "Outside drinks are not allowed."))
            .outsideDrinkFees(List.of())
            .build();

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

        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình và nhóm nhỏ.", "Suitable for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-6-vip")
                .name(of("Bàn VIP 6 ghế", "VIP table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP khu yên tĩnh hơn, phục vụ ưu tiên.", "VIP table in quieter area, priority service."))
                .build(),
            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng tối đa 40 khách", "Private room up to 40 guests"))
                .seats(40)
                .minPeople(10)
                .maxPeople(40)
                .totalTables(4)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đủ tiện nghi, phù hợp liên hoan và tiệc sinh nhật.", "Fully equipped private room for parties and birthdays."))
                .build()
        );

        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(60)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(4)
            .build();

        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hà Đông", "Ha Dong District"))
            .wardName(of("Phường Mộ Lao", "Mo Lao Ward"))
            .addressLine(of("BT01-1 Hoàng Thành Villas, Phường Mộ Lao, Quận Hà Đông, Hà Nội", "BT01-1 Hoang Thanh Villas, Mo Lao Ward, Ha Dong District, Hanoi"))
            .location(new double[]{105.7847, 20.9796})
            .name(of("Lẩu Nấm Gia Khánh Mộ Lao", "Lau Nam Gia Khanh Mo Lao"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Chuyên lẩu nấm sạch, đa dạng thực đơn chay và mặn, nằm trong khu đô thị Hoàng Thành Villas Hà Đông.", "Specialty healthy mushroom hotpot, diverse veg and non-veg menu, in Hoang Thanh Villas urban area Ha Dong."))
            .description(of(
                "Lẩu Nấm Gia Khánh Mộ Lao là địa chỉ chuyên lẩu nấm lành mạnh tại Hà Đông, Hà Nội, nổi tiếng với " +
                "thực đơn hơn 20 loại nấm tươi nguyên chất không chất bảo quản. Phù hợp cho người ăn chay, ăn kiêng " +
                "và các gia đình muốn bữa ăn thanh đạm, bổ dưỡng. Bãi đỗ xe miễn phí rộng rãi trong khu Hoàng Thành Villas.",
                "Lau Nam Gia Khanh Mo Lao is a healthy mushroom hotpot specialty in Ha Dong, Hanoi, renowned for its " +
                "menu of over 20 types of pure fresh mushrooms without preservatives. Ideal for vegetarians, dieters " +
                "and families seeking light, nutritious meals. Free spacious parking within the Hoang Thanh Villas complex."))
            .phone("0931006005")
            .email("reservation@launamgiakhanh.vn")
            .website("https://pasgo.vn/nha-hang/lau-nam-gia-khanh-mo-lao")
            .bookingHotline("0931006005")
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
