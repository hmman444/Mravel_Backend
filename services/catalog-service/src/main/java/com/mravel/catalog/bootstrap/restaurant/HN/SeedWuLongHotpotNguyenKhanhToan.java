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
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedWuLongHotpotNguyenKhanhToan {

    @Bean
    CommandLineRunner seedWuLongHotpotRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedWuLongHotpotNguyenKhanhToan] start");

            RestaurantDoc doc = restaurantWuLongHotpot();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedWuLongHotpotNguyenKhanhToan] done");
        };
    }

    // =====================================================================
    //        WU LONG HOTPOT BUFFET - 126 NGUYỄN KHÁNH TOÀN (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantWuLongHotpot() {
        String slug = "wu-long-hotpot-nguyen-khanh-toan";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-1-normal-2249635455929.webp",
                of("Không gian WuLong Hotpot - 126 Nguyễn Khánh Toàn", "WuLong Hotpot interior – 126 Nguyen Khanh Toan"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-2-normal-2249635555930.webp",
                of("Khu vực bàn lẩu WuLong Hotpot", "Hotpot table area at WuLong"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-3-normal-2249635655931.webp",
                of("Nội thất phong cách Đài Loan tại WuLong", "Taiwanese-style décor at WuLong Hotpot"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-4-normal-2249635755932.webp",
                of("Buffet lẩu phong phú tại WuLong", "Rich hotpot buffet spread at WuLong"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-5-normal-2249635855933.webp",
                of("Quầy buffet tươi sống WuLong Hotpot", "Fresh ingredients buffet counter at WuLong"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-6-normal-2249635955934.webp",
                of("Nồi lẩu đặc trưng phong cách Đài Loan", "Signature Taiwanese-style hotpot pot"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-7-normal-2249636155935.webp",
                of("Không gian phòng riêng WuLong Hotpot", "Private room at WuLong Hotpot"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-8-normal-2249636255936.webp",
                of("Khu vực ngồi rộng rãi và thoáng mát", "Spacious and airy seating area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-9-normal-2249636455937.webp",
                of("Các loại nước lẩu đặc trưng WuLong", "WuLong signature hotpot broths"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-10-normal-2249636555938.webp",
                of("Topping hải sản tươi tại quầy buffet", "Fresh seafood toppings at the buffet counter"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wu-long-nguyen-khanh-toan-11-normal-2249636655939.webp",
                of("Tráng miệng và đồ uống kèm buffet WuLong", "Desserts and drinks included in WuLong buffet"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/wulongnguyenkhanhtoan/nha-hang-wu-long-nguyen-khanh-toan-1.jpg",
                of("Góc chụp thực tế không gian WuLong Hotpot", "Real-shot interior of WuLong Hotpot"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/wulong/2003/wulong-buffet-menu-203-1.jpg",
                of("Thực đơn buffet WuLong Hotpot - trang 1", "WuLong Hotpot buffet menu – page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/wulong/2003/wulong-buffet-menu-203-2.jpg",
                of("Thực đơn buffet WuLong Hotpot - trang 2", "WuLong Hotpot buffet menu – page 2"), false, 1)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("TAIWANESE_HOTPOT")
                .name(of("Lẩu Đài Loan", "Taiwanese Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_HOTPOT")
                .name(of("Buffet lẩu", "Hotpot Buffet"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        // Trưa 11:30-14:30, Tối 18:00-22:30 (hai ca)
        // Dùng 7 dòng: mỗi ngày mở theo ca trưa (đại diện bằng 11:30-14:30)
        // Lưu ý: OpeningHour theo cơ chế 1 dòng/ngày → dùng ca mở rộng hơn hoặc ca tối
        // Dùng ca bao quát: 11:30 - 22:30
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 30, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   11, 30, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 11, 30, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  11, 30, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    11, 30, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  11, 30, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    11, 30, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("GROUP_MEETING", of("Họp nhóm đông người", "Group meeting")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("ELEGANT",   of("Thanh lịch", "Elegant"))
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
            signatureDishL(of("Lẩu nước cốt nấm Đài Loan", "Taiwanese mushroom broth hotpot")),
            signatureDishL(of("Lẩu sa tế cay đặc trưng", "Signature spicy satay hotpot")),
            signatureDishL(of("Lẩu miso Nhật pha Đài", "Japanese-Taiwanese miso hotpot")),
            signatureDishL(of("Tôm hùm đất nướng/nhúng lẩu", "Crayfish grilled or hotpot dipped")),
            signatureDishL(of("Thịt bò Mỹ thái lát mỏng", "Thinly sliced US beef")),
            signatureDishL(of("Sò điệp tươi nhúng lẩu", "Fresh scallops hotpot dipped")),
            signatureDishL(of("Nấm kim châm & nấm đông cô", "Enoki mushrooms & shiitake")),
            signatureDishL(of("Bánh cá Đài Loan hấp dẫn", "Taiwanese fish cake")),
            signatureDishL(of("Kem tươi & bánh ngọt tráng miệng", "Fresh ice cream & pastry desserts")),
            signatureDishL(of("Đồ uống không giới hạn kèm buffet", "Unlimited drinks included in buffet"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_LUNCH")
                .name(of("Buffet lẩu buổi trưa", "Lunch hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất buffet Như Ý buổi trưa", "Standard lunch buffet"))
                        .description(of("Buffet lẩu không giới hạn buổi trưa, áp dụng cả tuần 11h30–14h30.",
                            "Unlimited hotpot buffet for lunch, available all week 11:30–14:30."))
                        .priceFrom(new BigDecimal("289000"))
                        .priceTo(new BigDecimal("289000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "lunch"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất buffet Thượng Hạng buổi trưa", "Premium lunch buffet"))
                        .description(of("Buffet lẩu cao cấp không giới hạn buổi trưa, bao gồm hải sản tươi và thịt bò Mỹ.",
                            "Premium unlimited hotpot buffet for lunch with fresh seafood and US beef."))
                        .priceFrom(new BigDecimal("359000"))
                        .priceTo(new BigDecimal("359000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "premium", "hotpot", "lunch"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_DINNER")
                .name(of("Buffet lẩu buổi tối", "Dinner hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất buffet Như Ý buổi tối", "Standard dinner buffet"))
                        .description(of("Buffet lẩu không giới hạn buổi tối, áp dụng cả tuần 17h00–22h30.",
                            "Unlimited hotpot buffet for dinner, available all week 17:00–22:30."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "dinner"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất buffet Thượng Hạng buổi tối", "Premium dinner buffet"))
                        .description(of("Buffet lẩu cao cấp không giới hạn buổi tối, đầy đủ hải sản và thịt bò Mỹ nhập khẩu.",
                            "Premium unlimited dinner hotpot buffet with seafood and imported US beef."))
                        .priceFrom(new BigDecimal("389000"))
                        .priceTo(new BigDecimal("389000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "premium", "hotpot", "dinner"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe dọc phố Nguyễn Khánh Toàn và khu vực lân cận", "Street parking along Nguyen Khanh Toan and nearby area"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng", "In front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi miễn phí trước cửa; ô tô đỗ dọc đường hoặc bãi trả phí gần đó.",
                "Free motorbike parking in front; cars use paid street parking or a nearby lot."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "WIFI",
            "EVENT_DECOR",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "DIRECT_INVOICE",
            "FREE_MOTORBIKE_PARKING",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "SET_LUNCH",
            "BIRTHDAY_CAKE",
            "PHOTO_AREA"
        );

        // ---------- Content dài (STORY) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Giới thiệu WuLong Hotpot – Buffet Lẩu Đài Loan tại Nguyễn Khánh Toàn",
                    "About WuLong Hotpot – Taiwanese Hotpot Buffet on Nguyen Khanh Toan")),
            paragraph(ContentSection.STORY,
                of("WuLong Hotpot tọa lạc tại số 126 Nguyễn Khánh Toàn, phường Quan Hoa, quận Cầu Giấy, Hà Nội — " +
                    "nơi hội tụ của phong cách ẩm thực lẩu Đài Loan chính thống với hệ thống buffet đa dạng nguyên liệu tươi sống. " +
                    "Nhà hàng phục vụ hai ca mỗi ngày: buổi trưa từ 11h30 đến 14h30 và buổi tối từ 18h00 đến 22h30, " +
                    "đáp ứng nhu cầu thực khách trong cả tuần.",
                    "WuLong Hotpot is located at 126 Nguyen Khanh Toan, Quan Hoa Ward, Cau Giay District, Hanoi — " +
                    "a destination for authentic Taiwanese-style hotpot with an extensive fresh-ingredient buffet system. " +
                    "The restaurant operates two sessions daily: lunch from 11:30 to 14:30 and dinner from 18:00 to 22:30, " +
                    "serving guests every day of the week.")),
            heading(ContentSection.STORY,
                of("Không gian & Sức chứa",
                    "Space & Capacity")),
            paragraph(ContentSection.STORY,
                of("Không gian WuLong Hotpot được thiết kế theo phong cách hiện đại pha chút hơi thở Á Đông, " +
                    "với tông màu trung tính ấm áp, ánh đèn vàng và các ô bàn cá nhân được bố trí rộng rãi. " +
                    "Sức chứa lên tới 200 khách, cùng 3 phòng riêng phù hợp cho nhóm từ 10 đến 30 người " +
                    "muốn tổ chức tiệc sinh nhật, liên hoan hay họp nhóm trong không gian riêng tư.",
                    "WuLong Hotpot's interior blends modern design with warm East Asian accents — " +
                    "neutral warm tones, amber lighting, and generously spaced individual hot-pot tables. " +
                    "The restaurant seats up to 200 guests, with 3 private rooms accommodating groups of 10 to 30 " +
                    "for birthday parties, celebrations or private group meetings.")),
            heading(ContentSection.STORY,
                of("Phong cách ẩm thực: Lẩu Đài Loan chính thống",
                    "Culinary Style: Authentic Taiwanese Hotpot")),
            paragraph(ContentSection.STORY,
                of("Điểm khác biệt của WuLong nằm ở hệ thống nước lẩu đặc trưng Đài Loan — từ nước cốt nấm thanh ngọt, " +
                    "lẩu sa tế cay nồng đến lẩu miso Nhật-Đài hòa quyện. " +
                    "Nguyên liệu nhúng phong phú bao gồm thịt bò Mỹ thái lát mỏng, tôm hùm đất, sò điệp, " +
                    "các loại nấm tươi nhập khẩu và đa dạng rau xanh, tất cả được bày sẵn tại quầy buffet tự chọn không giới hạn.",
                    "WuLong's edge lies in its signature Taiwanese hotpot broths — from the clear-sweet mushroom stock and " +
                    "fiery satay broth to the Japanese-Taiwanese miso fusion. " +
                    "The dipping ingredients include thinly sliced US beef, crayfish, fresh scallops, " +
                    "imported mushroom varieties and a wide selection of greens, all available at the unlimited self-serve buffet counter.")),
            heading(ContentSection.STORY,
                of("Thực đơn buffet & Mức giá",
                    "Buffet Menu & Pricing")),
            paragraph(ContentSection.STORY,
                of("WuLong cung cấp hai gói buffet: Như Ý (289K trưa / 299K tối) và Thượng Hạng (359K trưa / 389K tối). " +
                    "Gói Thượng Hạng bổ sung hải sản cao cấp, thịt bò Mỹ và đồ uống không giới hạn. " +
                    "Mức giá dao động từ 289.000đ đến 389.000đ/người — hợp lý so với chất lượng nguyên liệu và trải nghiệm ẩm thực Đài Loan chuẩn vị.",
                    "WuLong offers two buffet tiers: Standard 'Nhu Y' (289K lunch / 299K dinner) and Premium 'Thuong Hang' (359K lunch / 389K dinner). " +
                    "The Premium tier adds high-end seafood, US beef and unlimited beverages. " +
                    "Prices range from 289,000 to 389,000 VND/person — excellent value for the ingredient quality and authentic Taiwanese dining experience.")),
            heading(ContentSection.STORY,
                of("Phù hợp với dịp nào",
                    "Suitable Occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian ấm cúng và phòng riêng sang trọng, WuLong Hotpot phù hợp cho nhiều dịp: " +
                    "bữa ăn gia đình cuối tuần, tiệc sinh nhật, liên hoan văn phòng, tổng kết dự án hay đơn giản là " +
                    "buổi hẹn hò ấm áp trong tiết trời Hà Nội se lạnh. " +
                    "Hệ thống phòng riêng cũng là lựa chọn lý tưởng cho các buổi họp nhóm doanh nghiệp cần không gian yên tĩnh.",
                    "With its cozy space and elegant private rooms, WuLong Hotpot suits a wide range of occasions: " +
                    "weekend family meals, birthday celebrations, office parties, project wrap-ups or simply " +
                    "a warm date night on a cool Hanoi evening. " +
                    "The private room system also makes it an ideal choice for business group meetings needing a quiet setting.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & Tiện ích",
                    "Services & Amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị đầy đủ wifi miễn phí, điều hòa toàn bộ khu vực, ghế dành riêng cho trẻ em và hỗ trợ " +
                    "trang trí sự kiện theo yêu cầu (sinh nhật, kỷ niệm, tiệc công ty). " +
                    "Đội ngũ nhân viên chuyên nghiệp hỗ trợ dịch vụ MC, âm thanh ánh sáng và có thể in hóa đơn VAT " +
                    "hoặc hóa đơn trực tiếp cho doanh nghiệp.",
                    "The restaurant is fully equipped with free WiFi, air-conditioning throughout, children's high chairs, " +
                    "and event decoration support on request (birthdays, anniversaries, company parties). " +
                    "The professional team provides MC services, sound and lighting setup, and can issue VAT invoices " +
                    "or direct invoices for corporate clients.")),
            heading(ContentSection.STORY,
                of("Vị trí & Di chuyển",
                    "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("126 Nguyễn Khánh Toàn nằm tại phường Quan Hoa, quận Cầu Giấy — khu vực tập trung đông sinh viên, " +
                    "văn phòng và khu dân cư cao cấp phía tây Hà Nội. " +
                    "Cách Hồ Tây khoảng 10 phút và Hồ Hoàn Kiếm khoảng 20 phút di chuyển. " +
                    "Thuận tiện đến bằng xe máy, taxi hay xe buýt tuyến 27, 33, 45 qua đường Nguyễn Khánh Toàn.",
                    "126 Nguyen Khanh Toan is in Quan Hoa Ward, Cau Giay District — a hub for students, offices and " +
                    "upscale residential areas in western Hanoi. " +
                    "About 10 minutes from West Lake and 20 minutes from Hoan Kiem Lake. " +
                    "Easily reached by motorbike, taxi or bus lines 27, 33, 45 along Nguyen Khanh Toan street.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & Lý do nên chọn WuLong",
                    "Booking Policy & Why Choose WuLong")),
            paragraph(ContentSection.STORY,
                of("WuLong Hotpot nhận đặt bàn trước với chính sách đặt cọc linh hoạt cho nhóm đông và sự kiện đặc biệt. " +
                    "Nhà hàng phát hành hóa đơn VAT và hóa đơn trực tiếp theo yêu cầu. " +
                    "Đặt bàn qua hệ thống MRAVEL để nhận ưu đãi sớm, xác nhận chỗ ngồi ngay lập tức " +
                    "và trải nghiệm buffet lẩu Đài Loan chuẩn vị ngay giữa lòng Hà Nội.",
                    "WuLong Hotpot accepts advance bookings with a flexible deposit policy for large groups and special events. " +
                    "The restaurant issues VAT invoices and direct invoices on request. " +
                    "Book via MRAVEL to receive early-bird offers, instant seat confirmation " +
                    "and an authentic Taiwanese hotpot buffet experience right in the heart of Hanoi.")),
            mapBlock(ContentSection.STORY, new double[]{105.8003, 21.0382})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "No promotion on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "No promotion on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc yêu cầu trang trí đặc biệt.",
                "Applies to bookings of 8 or more guests or requests for special decoration."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng khi đặt qua hệ thống; không áp dụng vào ngày lễ, tết.",
                "Promotion applies when booking via the system; not applicable on public holidays and Tet."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi theo từng thời điểm, vui lòng liên hệ nhà hàng.",
                "Promotion details vary by period; please contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT và hóa đơn trực tiếp theo yêu cầu.",
                "VAT invoices and direct invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% phí phục vụ áp dụng cho khu vực phòng riêng.",
                "A 5% service charge applies to private room bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.",
                "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài, áp dụng phụ phí theo loại.",
                "Outside drinks are allowed with a corkage fee by type."))
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
                .name(of("Bàn 4 người", "Table for 4"))
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
                .note(of("Phù hợp cặp đôi và nhóm nhỏ gia đình.", "Suitable for couples and small family groups."))
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
                .note(of("Bàn VIP không gian rộng, ưu tiên phục vụ.", "Spacious VIP table with priority service."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng có màn chiếu, âm thanh, phù hợp tiệc công ty và sự kiện.", "Private room with projector and sound system, ideal for company events."))
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
            .districtName(of("Quận Cầu Giấy", "Cau Giay District"))
            .wardName(of("P. Quan Hoa", "Quan Hoa Ward"))
            .addressLine(of("126 Nguyễn Khánh Toàn, P. Quan Hoa, Q. Cầu Giấy, Hà Nội",
                "126 Nguyen Khanh Toan, Quan Hoa Ward, Cau Giay District, Hanoi"))
            .location(new double[]{105.8003, 21.0382})
            .name(of("WuLong Hotpot - Nguyễn Khánh Toàn", "WuLong Hotpot - Nguyen Khanh Toan"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu Đài Loan không giới hạn hai ca trưa – tối ngay trên phố Nguyễn Khánh Toàn, Cầu Giấy.",
                "All-you-can-eat Taiwanese hotpot buffet with lunch and dinner sessions on Nguyen Khanh Toan street, Cau Giay."))
            .description(of("WuLong Hotpot tại 126 Nguyễn Khánh Toàn là nhà hàng buffet lẩu Đài Loan phong cách hiện đại " +
                "tọa lạc ngay trung tâm quận Cầu Giấy, Hà Nội. " +
                "Phục vụ hai ca trưa (11h30–14h30) và tối (18h00–22h30) mỗi ngày với mức giá từ 289.000đ/người. " +
                "Nổi bật với các loại nước lẩu Đài Loan chính thống, quầy buffet nguyên liệu tươi sống đa dạng " +
                "và hệ thống phòng riêng phù hợp cho tiệc, sự kiện và gặp gỡ đối tác.",
                "WuLong Hotpot at 126 Nguyen Khanh Toan is a modern Taiwanese hotpot buffet restaurant " +
                "in the heart of Cau Giay District, Hanoi. " +
                "Serving two sessions daily — lunch (11:30–14:30) and dinner (18:00–22:30) — starting from 289,000 VND/person. " +
                "Known for authentic Taiwanese hotpot broths, a rich fresh-ingredient buffet counter " +
                "and private rooms perfect for parties, events and business gatherings."))
            .phone("0931006005")
            .email("reservation@wulonghotpot.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-wu-long-nguyen-khanh-toan-5929")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("289000"))
            .maxPricePerPerson(new BigDecimal("389000"))
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
