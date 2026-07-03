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
import com.mravel.catalog.model.doc.RestaurantDoc.OutsideDrinkFee;
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
public class SeedLauHoiCosmosGiangVo {

    @Bean
    CommandLineRunner seedLauHoiCosmosGiangVoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauHoiCosmosGiangVo] start");

            RestaurantDoc doc = restaurantLauHoiCosmosGiangVo();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauHoiCosmosGiangVo] done");
        };
    }

    // =====================================================================
    //      NHÀ HÀNG LẨU HƠI COSMOS GIẢNG VÕ - BA ĐÌNH, HÀ NỘI
    // =====================================================================

    private static RestaurantDoc restaurantLauHoiCosmosGiangVo() {
        String slug = "lau-hoi-cosmos-giang-vo";

        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-1-normal-1282858820070.webp",
                of("Không gian Lẩu Hơi Cosmos Giảng Võ", "Lau Hoi Cosmos Giang Vo space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-2-normal-1282859020071.webp",
                of("Khu vực bàn ăn hiện đại", "Modern dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-3-normal-1282859720072.webp",
                of("Nồi lẩu hơi đặc trưng", "Signature steam hotpot"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-4-normal-1282860020073.webp",
                of("Nguyên liệu tươi đặc sắc", "Fresh premium ingredients"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-5-normal-1282860720074.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-6-normal-1282861320075.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-7-normal-1282861520076.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-8-normal-1282862120077.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-slide-9-normal-159656320078.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-slide-10-normal-159656520079.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-slide-11-normal-159656620080.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-hoi-cosmos-giang-vo-slide-12-normal-159656720081.webp",
                of("", ""), false, 11)
        );

        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/1.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 1", "Cosmos price list page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/2.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 2", "Cosmos price list page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/3.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 3", "Cosmos price list page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/4.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 4", "Cosmos price list page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/5.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 5", "Cosmos price list page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/6.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 6", "Cosmos price list page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/7.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 7", "Cosmos price list page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/8.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 8", "Cosmos price list page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/9.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 9", "Cosmos price list page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/10.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 10", "Cosmos price list page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/lauhapfresh/11.jpg",
                of("Bảng giá Lẩu Hơi Cosmos trang 11", "Cosmos price list page 11"), false, 10)
        );

        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("STEAM_HOTPOT")
                .name(of("Lẩu hơi", "Steam hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build()
        );

        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 22, 0)
        );

        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Bữa ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa công sở", "Office lunch"))
        );

        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("ELEGANT",   of("Sang trọng", "Elegant")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant"))
        );

        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu hơi hải sản đặc biệt", "Special seafood steam hotpot")),
            signatureDishL(of("Lẩu hơi bò Mỹ cao cấp", "Premium US beef steam hotpot")),
            signatureDishL(of("Tôm hùm hấp hơi", "Steam-cooked lobster")),
            signatureDishL(of("Cua biển hấp bia", "Beer-steamed sea crab")),
            signatureDishL(of("Bào ngư hấp gừng hành", "Abalone steamed with ginger and scallion")),
            signatureDishL(of("Ghẹ xanh hấp sả", "Lemongrass steamed blue crab")),
            signatureDishL(of("Sò điệp hấp tỏi bơ", "Butter garlic steamed scallops")),
            signatureDishL(of("Bò bít tết nướng muối ớt", "Salt and chili grilled beef steak")),
            signatureDishL(of("Combo lẩu hơi đặc biệt Cosmos", "Cosmos special steam hotpot combo")),
            signatureDishL(of("Nước dùng lẩu hơi không dầu mỡ", "Oil-free steam hotpot broth"))
        );

        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("LAU_HOI")
                .name(of("Lẩu hơi đặc biệt", "Special steam hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu hơi hải sản (2 người)", "Seafood steam hotpot (2 persons)"))
                        .description(of("Nồi lẩu hơi hải sản tươi gồm tôm, mực, cua, kèm rau sạch và bún.", "Fresh seafood steam hotpot with shrimp, squid, crab, with clean vegetables and noodles."))
                        .priceFrom(new BigDecimal("320000"))
                        .priceTo(new BigDecimal("420000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("steam", "seafood", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu hơi bò Mỹ (2 người)", "US beef steam hotpot (2 persons)"))
                        .description(of("Bò Mỹ thái lát mỏng, hấp trực tiếp trong nồi hơi, chín đều giữ vị ngọt tự nhiên.", "Thinly sliced US beef, direct steam cooked, evenly done while preserving natural sweetness."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("450000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("steam", "beef", "hotpot"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HAI_SAN_HAP")
                .name(of("Hải sản hấp hơi", "Steamed seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cua biển hấp bia", "Beer-steamed sea crab"))
                        .description(of("Cua biển tươi hấp bia Hà Nội, thịt chắc ngọt, chấm muối chanh ớt.", "Fresh sea crab steamed with Hanoi beer, firm sweet meat, served with salt-lime-chili dip."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("600000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("steam", "crab", "seafood"))
                        .build()
                ))
                .build()
        );

        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe tòa nhà D2 Giảng Võ", "D2 Giang Vo building parking"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe tòa nhà D2 Giảng Võ", "D2 Giang Vo building parking area"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy miễn phí, ô tô gửi bãi tòa nhà D2.", "Free motorbike parking, cars use D2 building lot."))
            .build();

        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "PRIVATE_ROOM",
            "WIFI",
            "FREE_MOTORBIKE_PARKING",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE",
            "VAT_INVOICE",
            "VISA_MASTER",
            "KIDS_CHAIR",
            "LIVE_COOKING",
            "TAKEAWAY",
            "SET_LUNCH"
        );

        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of(
                "Giới thiệu Nhà Hàng Lẩu Hơi Cosmos Giảng Võ",
                "About Lau Hoi Cosmos Giang Vo Restaurant")),
            paragraph(ContentSection.STORY, of(
                "Nhà Hàng Lẩu Hơi Cosmos Giảng Võ là địa chỉ ẩm thực nổi tiếng tại Ba Đình, Hà Nội, chuyên phục vụ " +
                "các món lẩu hơi và hải sản hấp hơi theo phong cách hiện đại. Tọa lạc tại tòa nhà D2, Giảng Võ, " +
                "nhà hàng thu hút thực khách bởi công nghệ nấu hơi đặc biệt giúp giữ nguyên dưỡng chất và vị ngọt " +
                "tự nhiên của nguyên liệu mà không cần dầu mỡ.",
                "Lau Hoi Cosmos Giang Vo Restaurant is a renowned culinary destination in Ba Dinh, Hanoi, specializing " +
                "in modern steam hotpot and steamed seafood dishes. Located in the D2 building, Giang Vo, the restaurant " +
                "attracts diners with its special steam cooking technology that preserves all nutrients and the natural " +
                "sweetness of ingredients without oil.")),
            heading(ContentSection.STORY, of(
                "Không gian & sức chứa",
                "Space & capacity")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng sở hữu không gian sang trọng, hiện đại với sức chứa lên tới 150 thực khách trên một tầng, " +
                "bố trí theo phong cách open kitchen — thực khách có thể quan sát trực tiếp quá trình chế biến. " +
                "Hệ thống 3 phòng riêng biệt với âm thanh ánh sáng đầy đủ đáp ứng các sự kiện từ 10 đến 40 khách. " +
                "Không gian điều hòa mát mẻ, sạch sẽ cùng nội thất hiện đại tạo ấn tượng từ lần đầu đặt chân vào.",
                "The restaurant boasts a luxurious, modern space with capacity for 150 guests on one floor, arranged in " +
                "an open-kitchen style — diners can directly observe the cooking process. Three private rooms with full " +
                "sound and lighting accommodate events for 10 to 40 guests. A cool air-conditioned clean space with " +
                "modern furnishings creates an impression from the very first visit.")),
            heading(ContentSection.STORY, of(
                "Thực đơn & món đặc sắc",
                "Menu & signature dishes")),
            paragraph(ContentSection.STORY, of(
                "Điểm nổi bật của Cosmos là công nghệ lẩu hơi độc đáo: thay vì đun sôi trực tiếp trong nước, nguyên " +
                "liệu được hấp chín bằng hơi nước áp lực từ nồi nước dùng đặt bên dưới. Cách nấu này giúp hải sản, " +
                "thịt bò và rau củ chín đều mà không bị mất nước, giữ nguyên độ tươi ngon và dưỡng chất. Thực đơn " +
                "phong phú với tôm hùm, cua biển, bào ngư, sò điệp, bò Mỹ cao cấp cùng nhiều combo hấp dẫn.",
                "The highlight of Cosmos is its unique steam hotpot technology: instead of boiling directly in water, " +
                "ingredients are cooked by steam pressure from the broth pot placed below. This method keeps seafood, " +
                "beef and vegetables evenly cooked without water loss, preserving freshness and nutrients. The rich menu " +
                "features lobster, sea crab, abalone, scallops, premium US beef and many attractive combos.")),
            heading(ContentSection.STORY, of(
                "Phù hợp dịp nào",
                "Suitable occasions")),
            paragraph(ContentSection.STORY, of(
                "Với mức giá 200.000 – 300.000đ/người, Lẩu Hơi Cosmos phù hợp cho bữa trưa văn phòng, bữa tối gia đình, " +
                "họp nhóm bạn bè hoặc tiệc liên hoan công ty. Hệ thống 3 phòng riêng với đầy đủ thiết bị âm thanh ánh sáng " +
                "và dịch vụ MC giúp tổ chức sinh nhật và sự kiện chuyên nghiệp ngay tại nhà hàng.",
                "With a price range of 200,000 – 300,000 VND/person, Lau Hoi Cosmos suits office lunches, family dinners, " +
                "friend group gatherings or company parties. The 3 private rooms with full sound, lighting equipment " +
                "and MC service enable professional birthday and event organization right at the restaurant.")),
            heading(ContentSection.STORY, of(
                "Công nghệ lẩu hơi — điểm khác biệt",
                "Steam hotpot technology — the differentiator")),
            paragraph(ContentSection.STORY, of(
                "Lẩu hơi (steam hotpot) là hình thức nấu ăn lành mạnh đang được ưa chuộng tại Hà Nội và TP. Hồ Chí Minh. " +
                "Nước dùng được hầm kỹ từ xương gà và rau củ, hơi nước bốc lên qua lỗ nhỏ trong khay đựng thực phẩm, " +
                "chín thực phẩm mà không làm trộn lẫn nước dùng với nguyên liệu. Kết quả: hải sản và thịt giữ được " +
                "trọn vẹn hương vị nguyên bản, không bị đắng hay tanh.",
                "Steam hotpot is a healthy cooking style becoming popular in Hanoi and Ho Chi Minh City. The broth is " +
                "carefully simmered from chicken bones and vegetables; steam rises through small holes in the food tray, " +
                "cooking the food without mixing the broth with the ingredients. Result: seafood and meat retain their " +
                "full original flavor without any bitter or fishy taste.")),
            heading(ContentSection.STORY, of(
                "Vị trí & di chuyển",
                "Location & getting there")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng tọa lạc tại tòa nhà D2 Giảng Võ, quận Ba Đình, cách Lăng Bác khoảng 1km, Hồ Tây và đường " +
                "Thanh Niên chỉ 2km. Khu vực trung tâm hành chính Ba Đình tấp nập, dễ tiếp cận bằng taxi, Grab hoặc " +
                "xe máy. Có bãi đỗ ô tô tòa nhà và gửi xe máy miễn phí.",
                "The restaurant is located in the D2 Giang Vo building, Ba Dinh district, about 1km from Ho Chi Minh Mausoleum " +
                "and only 2km from West Lake and Thanh Nien street. The busy Ba Dinh administrative center area is easily " +
                "accessible by taxi, Grab or motorbike. Building car park and free motorbike parking available.")),
            heading(ContentSection.STORY, of(
                "Chính sách đặt bàn & lý do nên chọn",
                "Booking policy & why choose us")),
            paragraph(ContentSection.STORY, of(
                "Cosmos nhận đặt bàn qua điện thoại và hệ thống online, có chính sách đặt cọc linh hoạt cho nhóm đông. " +
                "Công nghệ lẩu hơi độc đáo, nguyên liệu hải sản tươi sống nhập hàng ngày và không gian sang trọng " +
                "giữa lòng Ba Đình là những lý do khiến Lẩu Hơi Cosmos Giảng Võ trở thành điểm đến ẩm thực được " +
                "yêu thích trong khu vực.",
                "Cosmos accepts bookings by phone and online, with flexible deposit policies for large groups. Unique steam " +
                "hotpot technology, daily fresh seafood ingredients and luxurious space in the heart of Ba Dinh are the " +
                "reasons that make Lau Hoi Cosmos Giang Vo a beloved culinary destination in the area.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "No promotion on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "No promotion on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("400000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách hoặc yêu cầu phòng riêng.", "Applies to bookings of 6 or more guests or private room requests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% khi đặt qua hệ thống, không áp dụng ngày lễ.", "10% discount when booking online, not applicable on holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Liên hệ nhà hàng để biết chi tiết ưu đãi.", "Contact the restaurant for promotion details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí 5% áp dụng tại phòng riêng và sự kiện đặt tiệc.", "5% service charge for private rooms and event bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang rượu từ ngoài vào, có phụ phí.", "Outside wine allowed with corkage fee."))
            .outsideDrinkFees(List.of(
                OutsideDrinkFee.builder()
                    .drinkType("WINE")
                    .feeAmount(new BigDecimal("200000"))
                    .currencyCode("VND")
                    .note(of("Phụ phí rượu vang 200.000đ/chai.", "Wine corkage fee 200,000 VND/bottle."))
                    .build(),
                OutsideDrinkFee.builder()
                    .drinkType("SPIRITS")
                    .feeAmount(new BigDecimal("350000"))
                    .currencyCode("VND")
                    .note(of("Phụ phí rượu mạnh 350.000đ/chai.", "Spirits corkage fee 350,000 VND/bottle."))
                    .build()
            ))
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
                .totalTables(22)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình và nhóm bạn nhỏ.", "Suitable for families and small friend groups."))
                .build(),
            TableType.builder()
                .id("tb-6-vip")
                .name(of("Bàn VIP 6 ghế", "VIP table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(10)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Khu VIP sang trọng, phục vụ ưu tiên.", "Luxurious VIP area, priority service."))
                .build(),
            TableType.builder()
                .id("pr-50")
                .name(of("Phòng riêng tối đa 50 khách", "Private room up to 50 guests"))
                .seats(50)
                .minPeople(15)
                .maxPeople(50)
                .totalTables(5)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đầy đủ âm thanh ánh sáng, phù hợp tiệc công ty và sinh nhật.", "Fully equipped private room for company parties and birthdays."))
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
            .maxTablesPerBooking(5)
            .build();

        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Ba Đình", "Ba Dinh District"))
            .wardName(of("Phường Giảng Võ", "Giang Vo Ward"))
            .addressLine(of("Tòa nhà D2 Giảng Võ, Phường Giảng Võ, Quận Ba Đình, Hà Nội", "D2 Giang Vo Building, Giang Vo Ward, Ba Dinh District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("Nhà Hàng Lẩu Hơi Cosmos Giảng Võ", "Lau Hoi Cosmos Giang Vo Restaurant"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Chuyên lẩu hơi và hải sản hấp công nghệ hiện đại, giữ trọn dưỡng chất tự nhiên, tại Ba Đình Hà Nội.", "Specialty steam hotpot and steamed seafood with modern technology, preserving full natural nutrients, in Ba Dinh Hanoi."))
            .description(of(
                "Nhà Hàng Lẩu Hơi Cosmos Giảng Võ là điểm đến ẩm thực nổi bật tại Ba Đình, Hà Nội, chuyên phục vụ " +
                "lẩu hơi và hải sản hấp theo công nghệ hiện đại giúp giữ nguyên dưỡng chất. Không gian sang trọng " +
                "150 khách với 3 phòng riêng, thực đơn hải sản cao cấp, phù hợp từ bữa trưa đến tiệc liên hoan.",
                "Lau Hoi Cosmos Giang Vo Restaurant is a standout culinary destination in Ba Dinh, Hanoi, specializing " +
                "in modern steam hotpot and steamed seafood that preserves all nutrients. A luxurious 150-seat space with " +
                "3 private rooms, premium seafood menu, suitable from lunch to banquet events."))
            .phone("0931006005")
            .email("reservation@lauhoicosmos.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-lau-hoi-cosmos-giang-vo")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("300000"))
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
