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
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedShachaNiuLuongDinhCua {

    @Bean
    CommandLineRunner seedShachaNiuLuongDinhCuaRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedShachaNiuLuongDinhCua] start");

            RestaurantDoc doc = restaurantShachaNiuLuongDinhCua();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedShachaNiuLuongDinhCua] done");
        };
    }

    // =====================================================================
    //     SHACHA NIÚ LƯƠNG ĐỊNH CỦA - BUFFET LẨU BÒ TƯƠI TRIỀU CHÂU (HN)
    // =====================================================================

    private static RestaurantDoc restaurantShachaNiuLuongDinhCua() {
        String slug = "shacha-niu-luong-dinh-cua";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-1-normal-4221002073040.webp",
                of("Không gian SHACHA NIÚ Lương Định Của", "SHACHA NIU Luong Dinh Cua interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-2-normal-4221002173041.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-3-normal-4221002273042.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-4-normal-4221002373043.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-5-normal-4221002473044.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-6-normal-4221002573045.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-7-normal-4221002673046.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-8-normal-4221002773047.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-9-normal-4221002873048.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-10-normal-4221002973049.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-11-normal-4221003073050.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/shacha-niu-luong-dinh-cua-12-normal-4221003173051.webp",
                of("", ""), false, 11)
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Shachaniu/shachaniu-buffet-1.jpg",
                of("Bảng giá buffet SHACHA NIÚ", "SHACHA NIU buffet menu"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Shachaniu/shachaniu-buffet-2.jpg",
                of("Bảng giá buffet SHACHA NIÚ", "SHACHA NIU buffet menu"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Shachaniu/Combonew/shacha-niu-menu-combo-1.jpg",
                of("Menu combo SHACHA NIÚ", "SHACHA NIU combo menu"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Shachaniu/Combonew/shacha-niu-menu-combo-2.jpg",
                of("Menu combo SHACHA NIÚ", "SHACHA NIU combo menu"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Shachaniu/Combonew/shacha-niu-menu-combo-3.jpg",
                of("Menu combo SHACHA NIÚ", "SHACHA NIU combo menu"), false, 4)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("TEOCHEW_BEEF_HOTPOT")
                .name(of("Lẩu bò tươi Triều Châu", "Teochew Fresh Beef Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("CHINESE")
                .name(of("Trung Hoa", "Chinese"))
                .region("ASIA")
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

        // ---------- Giờ mở cửa (11:00-14:00 & 18:00-22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm đông", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa nhóm", "Team lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại trẻ trung", "Modern & youthful")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("ELEGANT",   of("Tinh tế đẳng cấp", "Elegant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu bò tươi Triều Châu", "Teochew fresh beef hotpot")),
            signatureDishL(of("Bò Mỹ thái lát mỏng", "Thinly sliced US beef")),
            signatureDishL(of("Ba ba hầm thuốc bắc", "Turtle braised with Chinese herbs")),
            signatureDishL(of("Hải sản tươi gọi món", "Fresh seafood a la carte")),
            signatureDishL(of("Tôm hùm nướng bơ tỏi", "Garlic butter grilled lobster")),
            signatureDishL(of("Bò wagyu nhúng lẩu", "Wagyu beef shabu-shabu")),
            signatureDishL(of("Hàu sữa nướng sa tế", "Satay grilled oysters")),
            signatureDishL(of("Mực ống tươi nướng muối ớt", "Fresh squid grilled with salt and chili")),
            signatureDishL(of("Bò viên handmade", "Handmade beef balls")),
            signatureDishL(of("Rau củ nhúng lẩu đặc sắc", "Specialty hotpot vegetables and mushrooms"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_WEEKDAY")
                .name(of("Buffet Niú Khách (T2-T5 & Trưa T6)", "Niú Buffet - Weekdays"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Niú Khách 398K", "Niú Buffet 398K"))
                        .description(of(
                            "Buffet bò tươi Triều Châu không giới hạn, áp dụng từ thứ 2 đến thứ 5 và bữa trưa thứ 6. Quầy hải sản tươi phục vụ gọi món theo nhu cầu.",
                            "Unlimited Teochew fresh beef buffet, available Monday to Thursday and Friday lunch. Fresh seafood counter for a la carte orders."))
                        .priceFrom(new BigDecimal("398000"))
                        .priceTo(new BigDecimal("398000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "beef", "teochew"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_WEEKEND")
                .name(of("Buffet Niú Khách (Tối T6 & T7-CN)", "Niú Buffet - Weekends"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Niú Khách 428K", "Niú Buffet 428K"))
                        .description(of(
                            "Buffet bò tươi Triều Châu không giới hạn, áp dụng tối thứ 6 và cả ngày thứ 7 - Chủ nhật.",
                            "Unlimited Teochew fresh beef buffet, available Friday evening and all day Saturday-Sunday."))
                        .priceFrom(new BigDecimal("428000"))
                        .priceTo(new BigDecimal("428000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "beef", "weekend"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Niú Tụ 516K", "Niú Premium Buffet 516K"))
                        .description(of(
                            "Trọn gói buffet cao cấp gồm bò tươi Triều Châu, ba ba và hải sản tươi, áp dụng cả tuần.",
                            "Premium all-week buffet package including Teochew fresh beef, turtle and fresh seafood."))
                        .priceFrom(new BigDecimal("516000"))
                        .priceTo(new BigDecimal("516000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "beef", "seafood", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe quanh khu vực Lương Định Của", "Parking area around Luong Dinh Cua street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Sân để xe máy tại nhà hàng", "Motorbike parking at restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy miễn phí tại sân nhà hàng; ô tô gửi bãi gần đó, có phí.",
                      "Free motorbike parking on-site; cars use nearby paid parking."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "PRIVATE_ROOM",
            "KARAOKE",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "WIFI",
            "VAT_INVOICE",
            "EVENT_DECOR",
            "MC_SERVICE",
            "SOUND_LIGHT",
            "BIRTHDAY_CAKE_SERVICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Tổng quan SHACHA NIÚ Lương Định Của",
                   "Overview of SHACHA NIU Luong Dinh Cua")),
            paragraph(ContentSection.STORY,
                of("SHACHA NIÚ Lương Định Của là nhà hàng buffet lẩu bò tươi Triều Châu nổi tiếng tại Hà Nội, tọa lạc tại số 26 Lương Định Của, phường Kim Liên, quận Đống Đa. " +
                   "Mang tinh hoa ẩm thực Triều Châu đến với thực khách Hà Nội, nhà hàng chinh phục thực khách bằng nồi lẩu nước dùng trong vắt, thơm ngọt từ xương hầm kết hợp với thịt bò tươi " +
                   "thái lát mỏng nhúng chín tức thì, giữ nguyên vị ngọt tự nhiên.",
                   "SHACHA NIU Luong Dinh Cua is a renowned Teochew fresh beef hotpot buffet restaurant in Hanoi, located at No. 26 Luong Dinh Cua, Kim Lien Ward, Dong Da District. " +
                   "Bringing the essence of Teochew cuisine to Hanoi diners, the restaurant captivates guests with a crystal-clear, naturally sweet broth made from slow-simmered bones, " +
                   "paired with freshly sliced beef that cooks in seconds to preserve its natural tenderness.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được thiết kế theo phong cách hiện đại, trẻ trung với sức chứa lên đến 200 thực khách. " +
                   "Không gian được phân chia thành khu vực đại sảnh và 3 phòng riêng biệt phù hợp cho nhóm từ 10 đến 30 người, " +
                   "lý tưởng cho các buổi tiệc công ty, sinh nhật hay tiếp khách quan trọng. Hệ thống điều hòa mát lạnh cùng ánh đèn ấm áp " +
                   "tạo nên không khí vừa ấm cúng vừa sang trọng, giúp thực khách thư giãn trọn vẹn trong bữa ăn.",
                   "The restaurant is designed in a modern, youthful style with a capacity of up to 200 guests. " +
                   "The space is divided into a main dining hall and 3 private rooms suitable for groups of 10 to 30 people, " +
                   "ideal for company parties, birthday celebrations or important business meetings. A well-maintained air conditioning system " +
                   "and warm ambient lighting create an atmosphere that is both cozy and sophisticated.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Điểm nhấn của SHACHA NIÚ là nồi lẩu bò tươi Triều Châu với nước dùng được chế biến theo công thức bí truyền, " +
                   "hầm từ xương bò, hành tây và gia vị đặc trưng trong nhiều giờ đồng hồ. Thực khách được thưởng thức đa dạng các loại thịt bò " +
                   "cao cấp như bò Mỹ, bò Wagyu thái lát mỏng cùng các loại bò viên handmade thơm ngon. " +
                   "Gói Buffet Niú Tụ 516K còn bổ sung thêm ba ba và hải sản tươi sống, đưa bữa tiệc lên một tầm cao mới.",
                   "The highlight of SHACHA NIU is the Teochew fresh beef hotpot with a broth crafted from a secret recipe, " +
                   "slow-simmered from beef bones, onions and signature spices for hours. Guests enjoy a variety of premium beef cuts " +
                   "such as US beef and Wagyu, thinly sliced, along with handmade beef balls. " +
                   "The Niú Tụ Buffet at 516K also adds turtle and fresh seafood, elevating the feast to a whole new level.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá buffet từ 398.000đ đến 516.000đ/người, SHACHA NIÚ phù hợp cho nhiều dịp khác nhau: " +
                   "từ bữa ăn gia đình cuối tuần ấm áp, tụ tập bạn bè sau giờ làm, liên hoan công ty, " +
                   "đến tiệc sinh nhật, kỷ niệm hay tiếp khách đối tác. " +
                   "Phòng riêng có thể đặt trước giúp nhóm khách có không gian riêng tư thoải mái mà không bị làm phiền.",
                   "With buffet prices ranging from 398,000 to 516,000 VND/person, SHACHA NIU is suitable for many occasions: " +
                   "from warm family dinners on weekends, after-work hangouts with friends, office parties, " +
                   "to birthday celebrations, anniversaries or business client entertainment. " +
                   "Private rooms can be reserved in advance, giving groups their own comfortable and undisturbed space.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật",
                   "Services & key amenities")),
            paragraph(ContentSection.STORY,
                of("SHACHA NIÚ trang bị đầy đủ tiện nghi phục vụ sự kiện: màn chiếu & máy chiếu, hệ thống âm thanh ánh sáng chuyên nghiệp, " +
                   "dịch vụ MC theo yêu cầu và trang trí tiệc theo chủ đề. Nhà hàng chấp nhận thanh toán Visa/Master card, " +
                   "xuất hóa đơn VAT theo yêu cầu doanh nghiệp. Ghế trẻ em được trang bị sẵn, " +
                   "khu vực hút thuốc riêng biệt giúp mọi thực khách đều có trải nghiệm thoải mái.",
                   "SHACHA NIU is fully equipped for events: projection screen and projector, professional sound and lighting system, " +
                   "MC service on request and themed party decoration. The restaurant accepts Visa/Master card payments, " +
                   "issues VAT invoices for corporate clients. High chairs for children are available, " +
                   "and a separate smoking area ensures all guests enjoy a comfortable experience.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tọa lạc tại số 26 Lương Định Của, phường Kim Liên, quận Đống Đa — nằm trong khu vực trung tâm Hà Nội, " +
                   "cách Hồ Hoàn Kiếm khoảng 3km và thuận tiện di chuyển từ các tuyến xe buýt Giải Phóng, Đại Cồ Việt. " +
                   "Khu vực Lương Định Của gần Đại học Kinh tế Quốc dân và bệnh viện Bạch Mai, " +
                   "rất tiện cho cả nhân viên văn phòng lẫn khách du lịch ghé thăm.",
                   "The restaurant is located at No. 26 Luong Dinh Cua, Kim Lien Ward, Dong Da District — in central Hanoi, " +
                   "about 3km from Hoan Kiem Lake and easily accessible from Giai Phong and Dai Co Viet bus routes. " +
                   "The Luong Dinh Cua area is close to the National Economics University and Bach Mai Hospital, " +
                   "convenient for office workers and tourists alike.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                   "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước qua hệ thống để nhận ưu đãi và giữ chỗ đảm bảo, đặc biệt vào cuối tuần và dịp lễ. " +
                   "Nhà hàng yêu cầu đặt cọc cho nhóm từ 6 người trở lên hoặc có yêu cầu phòng riêng và trang trí tiệc. " +
                   "SHACHA NIÚ tự hào mang đến trải nghiệm buffet lẩu bò tươi Triều Châu chuẩn vị, " +
                   "không gian sang trọng với giá cả hợp lý — điểm đến lý tưởng cho mọi dịp ăn uống tại Hà Nội.",
                   "Book in advance through our system to receive exclusive offers and guaranteed seating, especially on weekends and holidays. " +
                   "The restaurant requires a deposit for groups of 6 or more, or when requesting private rooms and party decoration. " +
                   "SHACHA NIU takes pride in delivering an authentic Teochew fresh beef hotpot buffet experience, " +
                   "with elegant surroundings at reasonable prices — an ideal dining destination for all occasions in Hanoi.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3,  8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 150.000đ/chai.", "Outside wine corkage fee: 150,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang ngoài: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of(
                "Áp dụng cho booking từ 6 khách trở lên hoặc yêu cầu phòng riêng/trang trí đặc biệt.",
                "Applies to bookings of 6 or more guests or requests for private rooms and special decoration."))
            .hasPromotion(true)
            .promotionSummary(of(
                "Ưu đãi khi đặt bàn qua hệ thống, không áp dụng vào ngày lễ và dịp đặc biệt.",
                "Promotions for bookings made through the system; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of(
                "Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng liên hệ nhà hàng.",
                "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho khu phòng riêng.", "5% service charge applies in private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không mang thức ăn từ ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Cho phép mang đồ uống vào, có phụ phí theo loại.", "Outside drinks allowed with corkage fee."))
            .outsideDrinkFees(outsideDrinkFees)
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
                .id("tb-4")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ hoặc nhóm bạn.", "Suitable for small families or friend groups."))
                .build(),

            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 người", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP tại khu trung tâm, ưu tiên phục vụ.", "VIP table in the center section, priority service."))
                .build(),

            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 150))
                .note(of("Phòng riêng đầy đủ thiết bị, phù hợp tiệc công ty và sự kiện.", "Private room with full facilities, suitable for company parties and events."))
                .build()
        );

        // ---------- Booking config ----------
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

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Đống Đa", "Dong Da District"))
            .wardName(of("P. Kim Liên", "Kim Lien Ward"))
            .addressLine(of(
                "Số 26 Lương Định Của, P. Kim Liên, Q. Đống Đa, Hà Nội",
                "No. 26 Luong Dinh Cua, Kim Lien Ward, Dong Da District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("SHACHA NIÚ Lương Định Của", "SHACHA NIU Luong Dinh Cua"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of(
                "Buffet lẩu bò tươi Triều Châu, ba ba & hải sản tươi tại Đống Đa, Hà Nội.",
                "Teochew fresh beef hotpot, turtle and seafood buffet in Dong Da, Hanoi."))
            .description(of(
                "SHACHA NIÚ Lương Định Của là nhà hàng buffet lẩu bò tươi Triều Châu nổi bật tại Hà Nội với nước dùng hầm xương trong vắt, " +
                "thịt bò nhập khẩu thái mỏng cùng ba ba và hải sản tươi sống. Không gian hiện đại, ấm cúng với phòng riêng phù hợp " +
                "cho gia đình, nhóm bạn và tiệc công ty.",
                "SHACHA NIU Luong Dinh Cua is a prominent Teochew fresh beef hotpot buffet restaurant in Hanoi featuring crystal-clear " +
                "bone broth, thinly sliced imported beef, turtle and fresh seafood. Modern and cozy interior with private rooms " +
                "suitable for families, friend groups and corporate events."))
            .phone("0243 5779666")
            .email("reservation@shachaniu.vn")
            .website("https://pasgo.vn/nha-hang/shacha-niu-luong-dinh-cua-5111")
            .facebookPage("https://facebook.com/shachaniu")
            .bookingHotline("0243 5779666")
            .minPricePerPerson(new BigDecimal("160000"))
            .maxPricePerPerson(new BigDecimal("516000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
            .priceBucket(PriceBucket.FROM_350K_TO_500K)
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
