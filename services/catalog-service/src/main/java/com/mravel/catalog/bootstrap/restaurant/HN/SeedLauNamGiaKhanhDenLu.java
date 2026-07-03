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
public class SeedLauNamGiaKhanhDenLu {

    @Bean
    CommandLineRunner seedLauNamGiaKhanhDenLuRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauNamGiaKhanhDenLu] start");

            RestaurantDoc doc = restaurantLauNamGiaKhanhDenLu();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauNamGiaKhanhDenLu] done");
        };
    }

    // =====================================================================
    //         LẨU NẤM GIA KHÁNH - ĐỀN LỪ (HOÀNG MAI, HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantLauNamGiaKhanhDenLu() {
        String slug = "lau-nam-gia-khanh-den-lu";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-1-normal-2252194156324.webp",
                of("Không gian Lẩu Nấm Gia Khánh - Đền Lừ", "Lau Nam Gia Khanh Den Lu space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-2-normal-2252194356325.webp",
                of("Không gian nhà hàng", "Restaurant interior"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-3-normal-2252194456326.webp",
                of("Khu vực bàn ăn", "Dining area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-4-normal-2252194556327.webp",
                of("Nồi lẩu nấm đặc trưng", "Signature mushroom hotpot"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-5-normal-2252194856328.webp",
                of("Nguyên liệu nấm tươi", "Fresh mushroom ingredients"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-6-normal-2252194956329.webp",
                of("Bàn tiệc nhóm", "Group dining table"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-7-normal-2252195156330.webp",
                of("Phòng riêng sang trọng", "Elegant private room"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-8-normal-2252195256331.webp",
                of("Khu vực trang trí sự kiện", "Event decoration area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-9-normal-2252195456332.webp",
                of("Bàn ăn gia đình", "Family dining table"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-10-normal-2252195556333.webp",
                of("Không gian ấm cúng", "Cozy interior"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-11-normal-2252195756334.webp",
                of("Khu vực lễ tân", "Reception area"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-gia-khanh-den-lu-12-normal-2252195856335.webp",
                of("Toàn cảnh nhà hàng", "Full restaurant view"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-1.jpg",
                of("Thực đơn trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-2.jpg",
                of("Thực đơn trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-3.jpg",
                of("Thực đơn trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-4.jpg",
                of("Thực đơn trang 4", "Menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-5.jpg",
                of("Thực đơn trang 5", "Menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-6.jpg",
                of("Thực đơn trang 6", "Menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-7.jpg",
                of("Thực đơn trang 7", "Menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-combo-8.jpg",
                of("Combo bảng giá 8", "Combo price list 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/launamgiakhanhdenlu/nha-hang-lau-nam-gia-khanh-den-lu-menu-combo-9.jpg",
                of("Combo bảng giá 9", "Combo price list 9"), false, 8)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_HOTPOT")
                .name(of("Lẩu Việt Nam", "Vietnamese Hotpot"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("MUSHROOM_CUISINE")
                .name(of("Ẩm thực nấm", "Mushroom Cuisine"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 21, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 21, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 21, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 21, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 21, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 21, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 21, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("RUSTIC",  of("Mộc mạc", "Rustic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu nấm thập cẩm", "Mixed mushroom hotpot")),
            signatureDishL(of("Lẩu nấm bò Úc", "Mushroom hotpot with Australian beef")),
            signatureDishL(of("Nấm bụng dê tươi", "Fresh goat belly mushroom")),
            signatureDishL(of("Nấm kim châm nhúng lẩu", "Enoki mushroom in hotpot")),
            signatureDishL(of("Bò Mỹ thái lát", "Sliced American beef")),
            signatureDishL(of("Tôm sú tươi nhúng", "Fresh tiger prawns")),
            signatureDishL(of("Nem nấm giòn rụm", "Crispy mushroom spring rolls")),
            signatureDishL(of("Nấm xào tổng hợp đậm đà", "Savory mixed sautéed mushrooms")),
            signatureDishL(of("Gà thả vườn nhúng lẩu", "Free-range chicken in hotpot")),
            signatureDishL(of("Mì trứng tươi ăn kèm", "Fresh egg noodles")),
            signatureDishL(of("Rau tươi theo mùa", "Seasonal fresh vegetables"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT_SET")
                .name(of("Set Lẩu Nấm", "Mushroom Hotpot Set"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set lẩu nấm thập cẩm 2 người", "Mixed mushroom hotpot set for 2"))
                        .description(of("Nước dùng hầm xương, nấm thập cẩm, bò Mỹ, rau sạch, mì.", "Bone broth, mixed mushrooms, US beef, fresh vegetables, noodles."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "mushroom", "set"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set lẩu nấm bò Úc 2 người", "Mushroom hotpot with Australian beef for 2"))
                        .description(of("Nước dùng nấm, bò Úc thái mỏng, nấm bụng dê, rau xanh.", "Mushroom broth, thinly sliced Australian beef, goat belly mushroom, greens."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("300000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "beef", "premium"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("SIDE_DISHES")
                .name(of("Món ăn kèm", "Side Dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Nem nấm giòn rụm", "Crispy mushroom spring rolls"))
                        .description(of("Nem chiên giòn nhân nấm thơm, chấm nước mắm chua ngọt.", "Crispy fried spring rolls with fragrant mushroom filling, served with sweet-sour fish sauce."))
                        .priceFrom(new BigDecimal("55000"))
                        .priceTo(new BigDecimal("75000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("side", "mushroom", "fried"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe khu đô thị Đền Lừ", "Parking lot at Den Lu urban area"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe trước cửa nhà hàng", "Parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy đỗ miễn phí trước cửa, ô tô gửi tại bãi xe khu đô thị gần đó.", "Free motorbike parking in front; cars use the nearby urban area parking lot."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "PRIVATE_ROOM",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "FREE_MOTORBIKE_PARKING",
            "WIFI",
            "VAT_INVOICE",
            "MC_SERVICE",
            "PROJECTOR",
            "SOUND_LIGHT",
            "BIRTHDAY_CAKE_SERVICE",
            "GROUP_BOOKING",
            "TAKEAWAY"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Tổng quan Lẩu Nấm Gia Khánh - Đền Lừ", "Lau Nam Gia Khanh Den Lu Overview")),
            paragraph(ContentSection.STORY,
                of("Lẩu Nấm Gia Khánh - Đền Lừ là một trong những chi nhánh của thương hiệu lẩu nấm nổi tiếng tại Hà Thành, " +
                "tọa lạc tại Ô 66 Lô 7 Đền Lừ 2, Phường Hoàng Văn Thụ, Quận Hoàng Mai. " +
                "Nhà hàng nổi bật với hương vị lẩu nấm đậm đà, sử dụng các loại nấm tự nhiên chọn lọc kết hợp cùng thịt bò Úc, gà, tôm cá tươi mỗi ngày. " +
                "Nước dùng được hầm từ xương ống gia truyền, nổi vị của nấm tự nhiên mang đến bữa ăn thơm ngon và bổ dưỡng.",
                "Lau Nam Gia Khanh Den Lu is one of the well-known mushroom hotpot restaurant branches in Hanoi, " +
                "located at O 66 Lo 7 Den Lu 2, Hoang Van Thu Ward, Hoang Mai District. " +
                "The restaurant stands out for its rich mushroom hotpot broth, using carefully selected natural mushrooms combined with fresh Australian beef, chicken, shrimp, and fish. " +
                "The broth is slow-simmered from bone marrow following a traditional recipe, highlighting the natural flavor of mushrooms for a delicious and nutritious meal.")),
            heading(ContentSection.STORY, of("Không gian & Sức chứa", "Space & Capacity")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng được thiết kế theo phong cách hiện đại, ấm cúng với sức chứa lên đến 200 thực khách. " +
                "Hệ thống 3 phòng riêng biệt phù hợp cho các nhóm khách từ 20-50 người, lý tưởng cho các buổi tiệc công ty, sinh nhật và sự kiện doanh nghiệp. " +
                "Nội thất được bố trí gọn gàng với ánh sáng ấm áp, tạo cảm giác thoải mái và thân thiện cho mọi bữa ăn.",
                "The restaurant interior is designed in a modern yet cozy style with a capacity of up to 200 guests. " +
                "Three separate private rooms accommodate groups of 20-50 people, ideal for company parties, birthdays, and corporate events. " +
                "The furniture is neatly arranged with warm lighting, creating a comfortable and welcoming atmosphere for every meal.")),
            heading(ContentSection.STORY, of("Thực đơn & Món đặc sắc", "Menu & Signature Dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn tại Lẩu Nấm Gia Khánh Đền Lừ xoay quanh chủ đạo là nấm thiên nhiên với nhiều loại phong phú: " +
                "nấm bụng dê, nấm kim châm, nấm hương, nấm đùi gà, nấm rơm... kết hợp cùng các nguyên liệu nhúng lẩu cao cấp như bò Úc, tôm sú, gà thả vườn. " +
                "Đặc biệt, nước dùng được chế biến từ nấm hầm cùng xương ống, cho vị ngọt thanh tự nhiên mà không cần thêm bột ngọt hay hóa chất.",
                "The menu at Lau Nam Gia Khanh Den Lu centers around natural mushrooms in rich variety: goat belly mushroom, enoki, shiitake, king oyster, straw mushroom... " +
                "combined with premium hotpot ingredients such as Australian beef, tiger prawns, and free-range chicken. " +
                "Notably, the broth is prepared from mushrooms slow-simmered with bone marrow, yielding a naturally sweet and clean flavor without MSG or additives.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable Occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá 200.000 – 300.000đ/người và không gian linh hoạt, nhà hàng phù hợp với nhiều dịp khác nhau: " +
                "từ bữa cơm gia đình cuối tuần, tụ tập bạn bè, đến liên hoan văn phòng hay tiệc sinh nhật. " +
                "Phòng riêng có sức chứa lớn đáp ứng tốt cho các nhóm doanh nghiệp tổ chức tiệc nội bộ hoặc tiếp khách đối tác.",
                "With a price range of 200,000–300,000 VND/person and flexible space, the restaurant suits many occasions: " +
                "from weekend family dinners and friend gatherings to office parties and birthday celebrations. " +
                "Large-capacity private rooms cater well to corporate groups holding internal parties or entertaining business partners.")),
            heading(ContentSection.STORY, of("Dịch vụ & Tiện ích", "Services & Amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp đầy đủ dịch vụ đặt tiệc trọn gói: trang trí sự kiện theo yêu cầu, dịch vụ MC dẫn chương trình, " +
                "hệ thống âm thanh ánh sáng chuyên nghiệp, máy chiếu phục vụ hội họp. " +
                "Ghế trẻ em được trang bị sẵn, điều hòa toàn bộ không gian, wifi miễn phí và hỗ trợ xuất hóa đơn VAT cho khách doanh nghiệp.",
                "The restaurant provides full event catering services: customized decoration, MC service, professional sound and lighting, and projector for meetings. " +
                "Children's chairs are available, full air-conditioning throughout, free wifi, and VAT invoices issued for corporate clients.")),
            heading(ContentSection.STORY, of("Vị trí & Di chuyển", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm tại Ô 66 Lô 7 Đền Lừ 2, Phường Hoàng Văn Thụ, Quận Hoàng Mai – một trong những quận đông dân cư nhất Hà Nội. " +
                "Vị trí thuận lợi gần các khu đô thị lớn như Linh Đàm, Đền Lừ, dễ dàng di chuyển bằng xe máy, ô tô hoặc xe buýt từ các quận trung tâm. " +
                "Bãi đỗ xe máy miễn phí trước cửa và bãi ô tô gần đó, thuận tiện cho mọi đối tượng thực khách.",
                "The restaurant is located at O 66 Lo 7 Den Lu 2, Hoang Van Thu Ward, Hoang Mai District – one of Hanoi's most densely populated districts. " +
                "Conveniently situated near major urban areas such as Linh Dam and Den Lu, easily accessible by motorbike, car, or bus from central districts. " +
                "Free motorbike parking in front and a nearby car park make it convenient for all customers.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn", "Booking Policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước qua điện thoại hoặc hệ thống online, khuyến khích đặt trước ít nhất 1 giờ cho nhóm đông. " +
                "Các nhóm từ 10 người trở lên cần đặt cọc để giữ chỗ phòng riêng. " +
                "Nhà hàng hỗ trợ tổ chức sự kiện đặc biệt như sinh nhật, kỷ niệm với nhiều gói trang trí linh hoạt.",
                "The restaurant accepts advance reservations by phone or online booking system, with bookings recommended at least 1 hour ahead for large groups. " +
                "Groups of 10 or more are required to pay a deposit to secure private room bookings. " +
                "The restaurant also supports organizing special events such as birthdays and anniversaries with various decoration packages."))
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày Tết Dương lịch", "Promotion not applicable on New Year's Day")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking phòng riêng từ 10 khách trở lên.", "Applies to private room bookings of 10 or more guests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi giảm giá khi đặt bàn qua hệ thống, không áp dụng ngày lễ tết.", "Discount promotion when booking via system; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% dịch vụ áp dụng cho phòng riêng.", "5% service charge applies to private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không cho phép mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không cho phép mang đồ uống từ ngoài vào.", "Outside drinks are not allowed."))
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
                .id("tb-4-gk")
                .name(of("Bàn 4 người", "Table for 4"))
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
                .note(of("Bàn thường, phù hợp nhóm nhỏ gia đình/bạn bè.", "Standard table for small family or friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-gk")
                .name(of("Bàn 8 người", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP cho nhóm 5-8 người, ưu tiên phục vụ.", "VIP table for groups of 5-8, priority service."))
                .build(),
            TableType.builder()
                .id("pr-30-gk")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(50)
                .totalTables(3)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng cho tiệc nội bộ, sinh nhật, sự kiện công ty.", "Private room for internal parties, birthdays, and corporate events."))
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
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hoàng Mai", "Hoang Mai District"))
            .wardName(of("P. Hoàng Văn Thụ", "Hoang Van Thu Ward"))
            .addressLine(of("Ô 66 Lô 7 Đền Lừ 2, P. Hoàng Văn Thụ, Q. Hoàng Mai, Hà Nội", "O 66 Lo 7 Den Lu 2, Hoang Van Thu Ward, Hoang Mai District, Hanoi"))
            .location(new double[]{105.8635121, 20.98552292378})
            .name(of("Lẩu Nấm Gia Khánh - Đền Lừ", "Lau Nam Gia Khanh - Den Lu"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu nấm thiên nhiên đậm đà, bổ dưỡng – thương hiệu nổi tiếng Hà Thành tại Quận Hoàng Mai.", "Natural mushroom hotpot – rich, nutritious, and famous in Hanoi, located in Hoang Mai District."))
            .description(of("Lẩu Nấm Gia Khánh - Đền Lừ là chi nhánh thuộc hệ thống nhà hàng lẩu nấm nổi tiếng tại Hà Nội, " +
                "chuyên phục vụ các loại lẩu nấm thiên nhiên kết hợp thịt bò Úc, gà, hải sản tươi ngon. " +
                "Nước dùng hầm từ xương ống truyền thống, nổi vị nấm tự nhiên, không bột ngọt, phù hợp cho cả gia đình và nhóm bạn.",
                "Lau Nam Gia Khanh Den Lu is a branch of the famous mushroom hotpot restaurant chain in Hanoi, " +
                "specializing in natural mushroom hotpot combined with Australian beef, chicken, and fresh seafood. " +
                "The broth is traditionally simmered from bone marrow, featuring natural mushroom flavor without MSG, suitable for families and friend groups."))
            .phone("19006005")
            .email("reservation@launamgiakhanh.vn")
            .website("https://pasgo.vn/nha-hang/lau-nam-gia-khanh-den-lu-4876")
            .facebookPage("https://facebook.com/launamgiakhanh")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("300000"))
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
