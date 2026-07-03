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
public class SeedMalaBuffetDuongLang {

    @Bean
    CommandLineRunner seedMalaBuffetDuongLangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedMalaBuffetDuongLang] start");

            RestaurantDoc doc = restaurantMalaBuffetDuongLang();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedMalaBuffetDuongLang] done");
        };
    }

    // =====================================================================
    //        NHA HANG MALA BUFFET - DUONG LANG (HA NOI)
    // =====================================================================

    private static RestaurantDoc restaurantMalaBuffetDuongLang() {
        String slug = "mala-buffet-duong-lang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-1-normal-4203981972424.webp",
                of("Không gian MALA buffet lẩu Đài Loan - Đường Láng", "MALA Taiwanese hotpot buffet space - Duong Lang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-2-normal-4203982072425.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-3-normal-4203982172426.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-4-normal-4203982272427.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-5-normal-4203982372428.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-6-normal-4203982472429.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-7-normal-4203982572430.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-8-normal-4203982672431.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-9-normal-4203982772432.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-10-normal-4203982872433.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-11-normal-4203982972434.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/mala-duong-lang-12-normal-4203983072435.webp",
                of("", ""), false, 11)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-1.png",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-2.png",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-3.png",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-4.png",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-5.png",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-6.png",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-7.png",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-8.png",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/15.%20MALA/menu-mala-10.png",
                of("", ""), false, 8)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("TAIWANESE_HOTPOT")
                .name(of("Lẩu Đài Loan", "Taiwanese Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("CHINESE")
                .name(of("Trung Hoa", "Chinese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_BUFFET")
                .name(of("Buffet Á", "Asian Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 21, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 21, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 21, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 21, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 0,  22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0,  22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",         of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",        of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY",  of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING",  of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT",  of("Tiệc công ty", "Company event")),
            suitable("COUPLE",         of("Hẹn hò", "Date")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa nhóm", "Group lunch")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách đối tác", "Business entertainment"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",     of("Hiện đại", "Modern")),
            ambience("VIBRANT",    of("Sôi động", "Vibrant")),
            ambience("COZY",       of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",    of("Sang trọng phong cách Hoa", "Elegant Chinese-inspired"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu MALA cay đặc trưng Đài Loan", "Taiwanese signature MALA spicy hotpot broth")),
            signatureDishL(of("Lẩu nấm đặc biệt vị thanh", "Special mushroom broth hotpot")),
            signatureDishL(of("Lẩu tom yum thái chua cay", "Thai spicy-sour tom yum broth")),
            signatureDishL(of("Thịt bò Mỹ thái lát mỏng nhúng lẩu", "US beef thinly sliced for hotpot")),
            signatureDishL(of("Hải sản tổng hợp tươi sống", "Fresh mixed seafood platter")),
            signatureDishL(of("Nấm cao cấp đặc biệt (kim châm, nấm truffle)", "Premium mushrooms (enoki, truffle)")),
            signatureDishL(of("Đậu phụ nhúng cay kiểu Tứ Xuyên", "Sichuan-style spicy tofu for dipping")),
            signatureDishL(of("Mỳ Udon tươi nhúng lẩu", "Fresh Udon noodles for hotpot")),
            signatureDishL(of("Sủi cảo nhân tôm thịt chiên giòn", "Crispy fried shrimp and pork dumplings")),
            signatureDishL(of("Trân châu sốt caramel tráng miệng", "Caramel sauce tapioca pearl dessert"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_LAU")
                .name(of("Buffet lẩu Đài Loan", "Taiwanese hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet lẩu 199K", "Hotpot buffet 199K"))
                        .description(of("Buffet 99+ món nhúng thượng hạng, chọn 1 nước lẩu. Áp dụng cả tuần.",
                                       "99+ premium dipping items buffet, choose 1 broth. Available all week."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet lẩu 299K", "Hotpot buffet 299K"))
                        .description(of("Buffet 99+ món nhúng cao cấp hơn, chọn 2 nước lẩu. Áp dụng cả tuần.",
                                       "99+ premium dipping items buffet, choose 2 broths. Available all week."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet lẩu 389K", "Hotpot buffet 389K"))
                        .description(of("Buffet cao cấp nhất: 99+ món nhúng thượng hạng, 5 loại nước lẩu tùy chọn, bổ sung hải sản cao cấp.",
                                       "Top-tier buffet: 99+ premium dipping items, 5 broth options, plus premium seafood add-ons."))
                        .priceFrom(new BigDecimal("389000"))
                        .priceTo(new BigDecimal("389000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "luxury", "seafood"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Không có bãi đỗ riêng, có thể đỗ lòng đường Đường Láng", "No dedicated lot; street parking available on Duong Lang"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe phía trước tòa nhà", "Front of building parking area"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy có bãi xe riêng miễn phí, ô tô gửi lòng đường hoặc bãi gần.", "Free motorbike parking available; cars use street or nearby lots."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "PRIVATE_ROOM",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "FREE_MOTORBIKE_PARKING",
            "KIDS_CHAIR",
            "PROJECTOR",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "MC_SERVICE",
            "SET_LUNCH",
            "ELEVATOR_ACCESS",
            "LOCKER_STORAGE"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu MALA buffet - Đường Láng", "About MALA buffet - Duong Lang")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng MALA buffet tọa lạc tại tầng 2, số 1152 đường Láng, phường Láng (quận Đống Đa cũ), Hà Nội. " +
                   "MALA mang đến trải nghiệm lẩu Đài Loan đích thực với phong cách Trung Hoa hiện đại, sang trọng. " +
                   "Buffet phục vụ hơn 99 món nhúng thượng hạng cùng 5 loại nước lẩu hấp dẫn, từ MALA cay đặc trưng đến lẩu nấm thanh ngọt.",
                   "MALA buffet restaurant is located on the 2nd floor at No. 1152 Duong Lang, Lang Ward (old Dong Da District), Hanoi. " +
                   "MALA brings an authentic Taiwanese hotpot experience with a modern, elegant Chinese-inspired style. " +
                   "The buffet serves over 99 premium dipping items with 5 enticing broth options, from the signature spicy MALA to a light and sweet mushroom broth.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng MALA được thiết kế theo phong cách Trung Hoa hiện đại với nội thất tối màu sang trọng, " +
                   "đèn chiếu sáng ấm áp và hệ thống hút khói thông minh trên mỗi bàn. Sức chứa 180 khách với khu vực ăn mở thoáng rộng " +
                   "và 2 phòng riêng tư phù hợp cho nhóm từ 15 đến 30 khách. Toàn bộ không gian máy lạnh mát mẻ, thoải mái cả mùa hè lẫn đông.",
                   "MALA's dining space is designed in a modern Chinese style with dark luxurious interiors, warm lighting, " +
                   "and a smart ventilation system above each table. Capacity of 180 guests with a spacious open dining area " +
                   "and 2 private rooms suitable for groups of 15 to 30 guests. Fully air-conditioned for comfortable dining in both summer and winter.")),
            heading(ContentSection.STORY, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn buffet MALA gồm hơn 99 nguyên liệu nhúng chọn lọc: thịt bò Mỹ thái lát mỏng, hải sản tươi sống (tôm, mực, ghẹ), " +
                   "các loại nấm cao cấp (kim châm, nấm sò, nấm truffle), đậu phụ, mỳ tươi, sủi cảo và nhiều loại rau củ. " +
                   "Đặc biệt, 5 loại nước lẩu bao gồm MALA cay Đài Loan, lẩu nấm, tom yum, lẩu chua cay và lẩu thanh vị giúp thực khách " +
                   "tha hồ kết hợp theo khẩu vị riêng.",
                   "MALA's buffet menu includes over 99 select dipping ingredients: thinly sliced US beef, fresh seafood (shrimp, squid, crab), " +
                   "premium mushrooms (enoki, oyster, truffle), tofu, fresh noodles, dumplings, and various vegetables. " +
                   "Notably, 5 broth options including the signature Taiwanese MALA spicy, mushroom, tom yum, spicy-sour, and clear broth " +
                   "let diners freely mix and match to their personal taste.")),
            heading(ContentSection.STORY, of("Gói buffet & giá", "Buffet packages & pricing")),
            paragraph(ContentSection.STORY,
                of("MALA cung cấp 3 gói buffet linh hoạt: Gói 199K (1 nước lẩu + 99+ món nhúng), gói 299K (2 nước lẩu, nâng cấp nguyên liệu), " +
                   "và gói cao cấp 389K (5 nước lẩu, bổ sung hải sản và nguyên liệu premium). Áp dụng cả tuần, phù hợp cho bữa trưa " +
                   "và bữa tối. Trẻ em dưới 1m miễn phí, từ 1m-1m4 giảm 50%.",
                   "MALA offers 3 flexible buffet packages: 199K package (1 broth + 99+ items), 299K (2 broths, upgraded ingredients), " +
                   "and premium 389K (5 broths, seafood additions and premium ingredients). Available all week, suitable for both " +
                   "lunch and dinner. Children under 1m dine free; 1m-1.4m at 50% off.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian rộng rãi, phong cách hiện đại và mức giá phải chăng từ 199K/người, MALA là lựa chọn lý tưởng " +
                   "cho ăn gia đình, tụ tập bạn bè, liên hoan văn phòng và tổ chức sinh nhật. Phòng riêng sức chứa 30 khách " +
                   "phù hợp cho tiệc nhóm và họp mặt doanh nghiệp quy mô nhỏ.",
                   "With a spacious setting, modern style, and affordable pricing from 199K/person, MALA is the ideal choice " +
                   "for family meals, friend gatherings, office parties, and birthday celebrations. The private room with 30-person capacity " +
                   "is suitable for group parties and small-scale corporate gatherings.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Hệ thống hút khói riêng trên từng bàn giúp không gian luôn thông thoáng dù nấu lẩu cả buổi. " +
                   "WiFi miễn phí, máy chiếu và âm thanh ánh sáng hỗ trợ tổ chức sự kiện, sinh nhật. " +
                   "Đội ngũ phục vụ nhiệt tình, thường xuyên tiếp lẩu và bổ sung nguyên liệu không giới hạn theo đúng tiêu chuẩn buffet. " +
                   "Chấp nhận thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                   "Individual smoke ventilation above each table keeps the air fresh throughout your hotpot session. " +
                   "Free WiFi, projector, and sound-light system support events and birthday parties. " +
                   "Enthusiastic service staff regularly refill your hotpot and top up unlimited ingredients as per buffet standards. " +
                   "Visa/Master card accepted and VAT invoices issued on request.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("MALA buffet nằm tại số 1152 đường Láng, phường Láng (khu vực Đống Đa cũ), Hà Nội. " +
                   "Vị trí nằm trên trục đường Láng sầm uất, gần cầu vượt đường Láng - Nguyễn Chí Thanh. " +
                   "Cách ga Láng (metro Cát Linh - Hà Đông) khoảng 1km. Dễ dàng di chuyển bằng taxi, Grab hoặc xe máy.",
                   "MALA buffet is located at No. 1152 Duong Lang, Lang Ward (old Dong Da area), Hanoi. " +
                   "Situated on the bustling Duong Lang thoroughfare, near the Duong Lang - Nguyen Chi Thanh overpass. " +
                   "About 1km from Lang station (Cat Linh - Ha Dong metro line). Easily accessible by taxi, Grab, or motorbike.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước từ 1 tiếng qua hệ thống, có ưu đãi 10% tổng hóa đơn khi đặt qua PasGo. " +
                   "Đặt cọc áp dụng cho nhóm từ 10 khách trở lên hoặc phòng riêng. Chính sách hủy miễn phí trước 30 phút. " +
                   "Không mang đồ ăn từ ngoài vào; đồ uống riêng có phụ phí corkage.",
                   "The restaurant accepts reservations at least 1 hour in advance through the system, with a 10% total bill discount when booking via PasGo. " +
                   "Deposits apply for groups of 10 or more guests or private rooms. Free cancellation policy up to 30 minutes before arrival. " +
                   "No outside food allowed; outside drinks subject to corkage fee.")),
            mapBlock(ContentSection.STORY, new double[]{105.8269, 21.0188})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("BEER")
                .feeAmount(new BigDecimal("100000"))
                .currencyCode("VND")
                .note(of("Phụ phí mang bia ngoài vào: 100.000đ/thùng.", "Outside beer corkage fee: 100,000 VND/case."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho nhóm từ 10 khách hoặc đặt phòng riêng.", "Applies to groups of 10 or more guests or private room bookings."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm 10% tổng hóa đơn khi đặt bàn qua hệ thống. Không áp dụng các ngày lễ.",
                               "10% discount on total bill when booking via the system. Not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi áp dụng cả tuần, không kết hợp với các khuyến mãi khác.", "Promotion valid all week; cannot be combined with other offers."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoice issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho phòng riêng.", "5% service charge applies to private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại.", "Outside drinks are allowed with a corkage fee depending on type."))
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
                .id("tb-4-std")
                .name(of("Bàn 4 người", "Table for 4"))
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
                .note(of("Bàn tiêu chuẩn khu vực mở, có hệ thống hút khói riêng.", "Standard table in open area with individual smoke ventilation."))
                .build(),
            TableType.builder()
                .id("tb-8-group")
                .name(of("Bàn 8 người", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn nhóm lớn, phù hợp tụ tập gia đình hoặc bạn bè.", "Large group table, suitable for family or friends gatherings."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng cách âm, đầy đủ thiết bị, phù hợp liên hoan và sự kiện nhóm.", "Soundproof private room with full equipment, suitable for parties and group events."))
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

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Đống Đa", "Dong Da District"))
            .wardName(of("Phường Láng", "Lang Ward"))
            .addressLine(of("Tầng 2, Số 1152 đường Láng, Phường Láng, Quận Đống Đa, Hà Nội",
                           "2nd Floor, No. 1152 Duong Lang, Lang Ward, Dong Da District, Hanoi"))
            .location(new double[]{105.8269, 21.0188})
            .name(of("Nhà hàng MALA buffet - Đường Láng", "MALA Buffet Restaurant - Duong Lang"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu Đài Loan 99+ món nhúng, 5 loại nước lẩu. Tầng 2, số 1152 Đường Láng, Đống Đa.",
                                 "Taiwanese hotpot buffet with 99+ dipping items, 5 broth options. 2nd floor, No. 1152 Duong Lang, Dong Da."))
            .description(of("Nhà hàng MALA buffet tọa lạc tại tầng 2, số 1152 đường Láng, phường Láng (quận Đống Đa), Hà Nội. " +
                            "Chuyên phục vụ buffet lẩu Đài Loan với hơn 99 món nhúng thượng hạng và 5 loại nước lẩu đặc sắc. " +
                            "Không gian phong cách Trung Hoa hiện đại, sang trọng, phù hợp cho mọi buổi tụ họp gia đình, bạn bè và liên hoan công ty.",
                            "MALA Buffet Restaurant is located on the 2nd floor at No. 1152 Duong Lang, Lang Ward, Dong Da District, Hanoi. " +
                            "Specializing in Taiwanese hotpot buffet with over 99 premium dipping items and 5 signature broth options. " +
                            "Modern Chinese-inspired elegant setting, perfect for all family gatherings, friend meetups, and office parties."))
            .phone("0931006005")
            .email("reservation@mala-restaurant.vn")
            .website("https://pasgo.vn/nha-hang/mala-duong-lang-5778")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("199000"))
            .maxPricePerPerson(new BigDecimal("389000"))
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
