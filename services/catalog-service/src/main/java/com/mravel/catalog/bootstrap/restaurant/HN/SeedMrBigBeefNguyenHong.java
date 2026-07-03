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
public class SeedMrBigBeefNguyenHong {

    @Bean
    CommandLineRunner seedMrBigBeefNguyenHongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedMrBigBeefNguyenHong] start");

            RestaurantDoc doc = restaurantMrBigBeefNguyenHong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedMrBigBeefNguyenHong] done");
        };
    }

    // =====================================================================
    //         MR.BIG BEEF BÒ NƯỚNG TẢNG - NGUYÊN HỒNG (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantMrBigBeefNguyenHong() {
        String slug = "mr-big-beef-bo-nuong-tang-nguyen-hong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-1-normal-130452714461.webp",
                of("Không gian Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng", "Mr.Big Beef Grilled Beef - Nguyen Hong interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-slide-normal-130531414462.webp",
                of("Không gian nhà hàng", "Restaurant space"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bo-nuong-tang-nguyen-hong-slide-3-normal-130507414463.webp",
                of("Khu vực bàn nướng", "Grill table area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-4-normal-130453014464.webp",
                of("Không gian tầng 1", "1st floor dining area"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-5-normal-130453114465.webp",
                of("Không gian tầng 2", "2nd floor dining area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-6-normal-130453214466.webp",
                of("Bàn VIP phòng riêng", "VIP private room table"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-7-normal-130453314467.webp",
                of("Khu vực bếp nướng than hoa", "Charcoal grill area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-8-normal-130453414468.webp",
                of("Thịt bò Mỹ nướng tảng đặc sắc", "Signature American stone-grilled beef"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-9-normal-130453514469.webp",
                of("Không gian phong cách Viễn Tây", "Wild West themed dining space"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-10-normal-130453614470.webp",
                of("Quầy buffet phong phú", "Rich buffet counter"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-11-normal-130453714471.webp",
                of("Khu vực bàn ngoài trời", "Outdoor terrace seating"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-big-beef-bo-nuong-tang-nguyen-hong-slide-12-normal-130453814472.webp",
                of("Ánh sáng nhà hàng về đêm", "Restaurant ambience at night"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-1.jpg",
                of("Menu bảng giá trang 1", "Menu price list page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-2.jpg",
                of("Menu bảng giá trang 2", "Menu price list page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-3.jpg",
                of("Menu bảng giá trang 3", "Menu price list page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-4.jpg",
                of("Menu bảng giá trang 4", "Menu price list page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-5.jpg",
                of("Menu bảng giá trang 5", "Menu price list page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-6.jpg",
                of("Menu bảng giá trang 6", "Menu price list page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-7.jpg",
                of("Menu bảng giá trang 7", "Menu price list page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-Mr-BigBeef/menu-mr-bigbeef-8.jpg",
                of("Menu bảng giá trang 8", "Menu price list page 8"), false, 7)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("AMERICAN_BBQ")
                .name(of("Bò nướng kiểu Mỹ", "American-style grilled beef"))
                .region("AMERICA")
                .build(),
            CuisineTag.builder()
                .code("BBQ_GRILL")
                .name(of("Nướng than hoa", "Charcoal grill"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa (T2-CN: 10:30-13:30 và 17:30-21:30) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 21, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 21, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 21, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",   of("Phong cách Viễn Tây", "Wild West rustic style")),
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively")),
            ambience("MODERN",   of("Hiện đại thoáng đáng", "Modern and spacious")),
            ambience("ROMANTIC", of("Lãng mạn ánh nến", "Romantic candlelit atmosphere"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(230)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Thăn Hoàng Đế nướng tảng", "Emperor Tenderloin Stone-Grilled"),
                of("Thăn bò Mỹ hảo hạng nướng nguyên tảng trên bếp than hoa, chín vừa tới, mềm tan, đậm đà hương vị.",
                   "Premium US beef tenderloin stone-grilled on charcoal, perfectly cooked, tender and richly flavored."),
                new BigDecimal("350000")),
            signatureDish(of("Thăn lõi Choice nướng tảng", "Choice Striploin Stone-Grilled"),
                of("Thịt bò Mỹ hạng Choice, vân mỡ trắng đều, nướng tảng giữ nguyên dưỡng chất, thơm giòn phần viền mỡ.",
                   "US Choice grade striploin with fine marbling, stone-grilled to retain nutrients with crispy fat edges."),
                new BigDecimal("290000")),
            signatureDish(of("Sườn non rút xương nướng", "Boneless Short Rib Grilled"),
                of("Sườn non bò Mỹ rút xương, ướp gia vị đặc biệt, nướng than hoa cho đến khi vàng đều, mềm ngọt.",
                   "US boneless short rib marinated in special seasoning, grilled over charcoal until golden and tender."),
                new BigDecimal("270000")),
            signatureDish(of("Ba chỉ bò Mỹ nướng", "US Beef Brisket Grilled"),
                of("Ba chỉ bò Mỹ tươi, nướng trực tiếp trên than hoa, lớp mỡ chảy tạo hương thơm quyến rũ.",
                   "Fresh US brisket grilled directly on charcoal, the rendered fat creating an irresistible aroma."),
                new BigDecimal("220000")),
            signatureDish(of("Lẩu bò Thái chua cay", "Spicy Thai Beef Hotpot"),
                of("Lẩu nước dùng chua cay kiểu Thái Lan nấu cùng thịt bò Mỹ, nấm và rau tươi, ăn kèm bún tươi.",
                   "Sour and spicy Thai-style broth simmered with US beef, mushrooms and fresh vegetables, served with rice noodles."),
                new BigDecimal("350000")),
            signatureDish(of("Lẩu cháo chim bồ câu", "Pigeon Congee Hotpot"),
                of("Nồi lẩu cháo thịt chim bồ câu hầm mềm, nước ngọt thanh, bổ dưỡng cho cả nhóm.",
                   "Pigeon meat congee hotpot, slow-simmered until tender with a clear savory broth, nourishing for the whole group."),
                new BigDecimal("320000")),
            signatureDish(of("Lẩu bò ba chỉ Hàn Quốc", "Korean Beef Brisket Hotpot"),
                of("Lẩu nước dùng phong cách Hàn Quốc kết hợp ba chỉ bò béo ngậy, rau sống và kim chi ăn kèm.",
                   "Korean-style broth hotpot with fatty beef brisket, fresh vegetables and kimchi on the side."),
                new BigDecimal("360000")),
            signatureDish(of("Salad hoa quả kiểu Mỹ", "American Fruit Salad"),
                of("Hỗn hợp hoa quả tươi mát, sốt mè rang đặc trưng, làm mát khẩu vị giữa các món nướng đậm đà.",
                   "Fresh mixed fruit salad with signature toasted sesame dressing, refreshing between hearty grilled courses."),
                new BigDecimal("90000")),
            signatureDish(of("Salad rong biển kiểu Nhật", "Japanese Seaweed Salad"),
                of("Rong biển tươi trộn sốt miso nhẹ, thêm mè rang và vài lát dưa leo giòn mát.",
                   "Fresh seaweed tossed in light miso dressing with toasted sesame and crisp cucumber slices."),
                new BigDecimal("80000")),
            signatureDish(of("Bún gạo chiên giòn sốt bò băm", "Crispy Rice Noodle with Minced Beef Sauce"),
                of("Sợi bún gạo chiên giòn vàng ươm, rưới sốt bò băm đặc sệt, ăn kèm rau thơm và ớt tươi.",
                   "Golden crispy fried rice noodles topped with thick minced beef sauce, served with fresh herbs and chili."),
                new BigDecimal("95000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet Nướng", "BBQ Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Nướng Lucky Luke's Shot 1 (buổi trưa T2-T5)", "BBQ Buffet Lucky Luke's Shot 1 (Weekday Lunch)"))
                        .description(of("Buffet nướng không giới hạn thịt bò Mỹ và các loại thịt hảo hạng, phục vụ buổi trưa thứ 2 đến thứ 5.",
                                       "Unlimited grilled US beef and premium meats buffet, served weekday lunches Monday to Thursday."))
                        .priceFrom(new BigDecimal("390000"))
                        .priceTo(new BigDecimal("390000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "beef"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Nướng Lucky Luke's Shot 1 (buổi tối T2-T5 & T6-CN)", "BBQ Buffet Lucky Luke's Shot 1 (Evening & Weekend)"))
                        .description(of("Buffet nướng không giới hạn thịt bò Mỹ, phục vụ buổi tối thứ 2-5 và cả ngày thứ 6-CN.",
                                       "Unlimited US beef BBQ buffet served weekday evenings and all day Friday to Sunday."))
                        .priceFrom(new BigDecimal("430000"))
                        .priceTo(new BigDecimal("430000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "beef"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_BBQ_HOTPOT")
                .name(of("Buffet Nướng Lẩu", "BBQ & Hotpot Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Nướng Lẩu Lucky Hoàng Đế (buổi trưa T2-T5)", "Emperor Lucky BBQ Hotpot Buffet (Weekday Lunch)"))
                        .description(of("Kết hợp buffet nướng bò Mỹ và lẩu thịt bò đa dạng, suất hoàng đế cao cấp nhất, phục vụ buổi trưa.",
                                       "Combined US beef BBQ and diverse beef hotpot buffet, the premium Emperor tier, served at lunch."))
                        .priceFrom(new BigDecimal("490000"))
                        .priceTo(new BigDecimal("490000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "beef", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Nướng Lẩu Lucky Hoàng Đế (buổi tối T2-T5 & T6-CN)", "Emperor Lucky BBQ Hotpot Buffet (Evening & Weekend)"))
                        .description(of("Suất Hoàng Đế cao cấp nhất gồm nướng bò Mỹ + lẩu, phục vụ buổi tối thứ 2-5 và cả ngày cuối tuần.",
                                       "The premium Emperor tier combining US beef BBQ and hotpot, served weekday evenings and all day weekends."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("550000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "beef", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước nhà hàng và trong ngõ", "Parking in front of and around the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy trước cửa và khu vực lân cận", "Motorbike parking in front and nearby areas"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng có chỗ đỗ ô tô miễn phí; xe máy gửi tự do trước cửa.", "Free car parking available; free motorbike parking in front of restaurant."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "WIFI",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "OUTDOOR_TABLE",
            "KIDS_PLAY_AREA",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "KARAOKE",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "LED_SCREEN",
            "LIVE_SPORT_TV"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Nhà hàng bò nướng tảng kiểu Mỹ đầu tiên tại Hà Nội",
                   "Hanoi's first American-style stone-grilled beef restaurant")),
            paragraph(ContentSection.STORY,
                of("Mr.Big Beef Bò Nướng Tảng – Nguyên Hồng là chi nhánh tọa lạc tại số 70 Nguyên Hồng, quận Đống Đa, Hà Nội, " +
                   "thuộc chuỗi nhà hàng bò Mỹ nướng tảng đầu tiên xuất hiện tại Thủ đô. Với phong cách ẩm thực mang hơi thở của " +
                   "miền Viễn Tây nước Mỹ hoang dã, Mr.Big Beef mang đến trải nghiệm ẩm thực độc đáo kết hợp giữa buffet nướng, " +
                   "lẩu và gọi món theo phong cách Mỹ.",
                   "Mr.Big Beef Grilled Beef – Nguyen Hong is a branch at 70 Nguyen Hong Street, Dong Da District, Hanoi, " +
                   "part of the first American stone-grilled beef restaurant chain in the capital. " +
                   "With a culinary style inspired by the wild American West, Mr.Big Beef delivers a unique dining experience " +
                   "combining BBQ buffet, hotpot and a la carte in an American style.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trải rộng trên 2 tầng với sức chứa lên đến 230 khách, bố trí gọn gàng và khoa học gồm khu vực " +
                   "trong nhà máy lạnh mát mẻ và khu vực bàn ngoài trời thoáng đãng. Ba phòng riêng sang trọng phù hợp " +
                   "cho các buổi tiệc nhỏ, họp nhóm hoặc gặp gỡ đối tác cần không gian riêng tư.",
                   "The restaurant spans two floors with a capacity of up to 230 guests, neatly arranged with a cool " +
                   "air-conditioned indoor area and an open-air outdoor terrace. Three elegant private rooms are ideal " +
                   "for small parties, group meetings or partner encounters requiring privacy.")),
            paragraph(ContentSection.STORY,
                of("Phong cách trang trí mang đậm chất Viễn Tây Mỹ với ánh đèn vàng ấm áp, đồ gỗ thô mộc và những " +
                   "bức tranh nghệ thuật gợi cảm giác của miền đất bao la nước Mỹ. Không gian vừa hoang dã vừa lãng mạn " +
                   "khiến thực khách cảm giác như đang trải nghiệm bữa ăn tối tại một trang trại bò hạng sang ở bang Texas.",
                   "The Wild West American decor with warm golden lighting, rough-hewn wooden furniture and artistic murals " +
                   "evoke the wide-open expanses of America. The space blends rustic and romantic elements, making diners feel " +
                   "as though they're enjoying dinner at a premium Texas cattle ranch.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Điểm nhấn thực đơn là những tảng thịt bò Mỹ hảo hạng nướng trực tiếp trên bếp than hoa — từ thăn Hoàng Đế " +
                   "mềm tan đến sườn non rút xương giòn viền — không tẩm ướp để giữ trọn vị ngọt nguyên bản của bò Mỹ. " +
                   "Bên cạnh đó, các suất Buffet Lucky Luke's Shot và Lucky Hoàng Đế kết hợp nướng-lẩu đa dạng với mức giá " +
                   "từ 390.000 đến 550.000 đồng/người tùy buổi.",
                   "The menu highlight is premium US beef stone-grilled directly on charcoal — from the melt-in-your-mouth Emperor " +
                   "Tenderloin to crispy-edged boneless short ribs — unmasked so the natural sweetness of the beef shines through. " +
                   "Additionally, Lucky Luke's Shot and Lucky Emperor BBQ-Hotpot Buffet combos offer a diverse feast " +
                   "from 390,000 to 550,000 VND per person depending on session.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian 2 tầng linh hoạt và phòng riêng, Mr.Big Beef Nguyên Hồng phù hợp cho mọi dịp: ăn gia đình " +
                   "cuối tuần, tụ tập bạn bè sinh nhật, liên hoan văn phòng, tiếp khách đối tác hoặc hẹn hò lãng mạn. " +
                   "Hệ thống âm thanh ánh sáng, MC và trang trí sự kiện đặc biệt giúp nhà hàng tổ chức tốt những buổi tiệc " +
                   "quy mô vừa đến lớn.",
                   "With its flexible two-floor layout and private rooms, Mr.Big Beef Nguyen Hong suits all occasions: " +
                   "weekend family dining, birthday friend gatherings, office parties, partner entertainment or romantic dates. " +
                   "The sound-and-light system, MC services and special event decoration allow the restaurant to host " +
                   "medium to large-scale celebrations with ease.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật",
                   "Services & notable amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị đầy đủ ghế trẻ em và khu vui chơi nhỏ dành cho các bé, đảm bảo gia đình có trẻ nhỏ " +
                   "thoải mái tận hưởng bữa ăn. Hệ thống máy chiếu, màn LED, âm thanh ánh sáng chuyên nghiệp và dịch vụ MC " +
                   "giúp các sự kiện sinh nhật, tiệc công ty diễn ra trọn vẹn ngay tại nhà hàng. Nhà hàng chấp nhận thanh " +
                   "toán Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                   "The restaurant is fully equipped with children's chairs and a small play area so families with young children " +
                   "can dine comfortably. Professional projector, LED screen, sound-and-light systems and MC services make " +
                   "birthday parties and corporate events unforgettable. The restaurant accepts Visa/Master and issues " +
                   "VAT invoices on request.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại 70 Nguyên Hồng, quận Đống Đa — một trong những tuyến phố ẩm thực nổi tiếng của Hà Nội — " +
                   "nhà hàng nằm gần các trường đại học lớn, khu văn phòng Láng Hạ và rất tiện di chuyển từ trung tâm " +
                   "thành phố. Bãi đỗ xe ô tô và xe máy miễn phí ngay trước cửa, thuận tiện cho cả khách đi xe cá nhân.",
                   "Located at 70 Nguyen Hong, Dong Da District — one of Hanoi's famous dining streets — the restaurant is " +
                   "close to major universities, the Lang Ha office district and is easily accessible from the city center. " +
                   "Free car and motorbike parking right outside makes it convenient for guests arriving by private vehicle.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                   "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Mr.Big Beef Nguyên Hồng nhận đặt bàn trước cho cả buffet lẫn gọi món. Đối với buffet, nhà hàng yêu cầu " +
                   "tối thiểu 4 người lớn; thời gian giữ chỗ tối đa 30 phút. Giá buffet chưa bao gồm VAT 10% — nhà hàng luôn " +
                   "thu phụ thu này. Trẻ em cao dưới 1m được miễn phí, từ 1m trở lên tính giá người lớn. Đặt bàn qua hệ " +
                   "thống để đảm bảo chỗ ngồi và nhận thêm ưu đãi hấp dẫn.",
                   "Mr.Big Beef Nguyen Hong accepts advance reservations for both buffet and a la carte. For buffet, a minimum " +
                   "of 4 adults is required; tables are held for up to 30 minutes. Buffet prices exclude 10% VAT which is " +
                   "always charged. Children under 1m tall dine free; those 1m and above are charged adult price. Book " +
                   "through the system to secure your seat and enjoy exclusive promotions.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3,  7,  of("Không áp dụng ưu đãi ngày 7/3", "Promotion not applicable on Mar 7")),
            blackoutGregorian(3,  8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4,  29, of("Không áp dụng ưu đãi ngày 29/4", "Promotion not applicable on Apr 29")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(10, 20, of("Không áp dụng ưu đãi ngày 20/10", "Promotion not applicable on Oct 20")),
            blackoutGregorian(12, 22, of("Không áp dụng ưu đãi ngày 22/12", "Promotion not applicable on Dec 22")),
            blackoutGregorian(12, 23, of("Không áp dụng ưu đãi ngày 23/12", "Promotion not applicable on Dec 23")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang vào: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("350000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang vào: 350.000đ/chai.", "Spirits corkage fee: 350,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Nhà hàng chưa công bố chính sách đặt cọc; vui lòng liên hệ trực tiếp để biết chi tiết.",
                             "Deposit policy not yet published; please contact the restaurant directly for details."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ và dịp đặc biệt.",
                                 "Promotion applies to guests booking via the system; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi tùy thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(4)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng luôn thu 10% VAT; không xuất hóa đơn trực tiếp.",
                             "The restaurant always charges 10% VAT; direct invoices are not issued."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không có phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại.", "Outside drinks allowed with a corkage fee depending on type."))
            .outsideDrinkFees(outsideDrinkFees)
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
                .id("tb-4-std")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn, phù hợp gia đình và nhóm bạn nhỏ.", "Standard table, suitable for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Bàn VIP ưu tiên phục vụ, phù hợp nhóm đông và dịp đặc biệt.", "VIP table with priority service, suitable for larger groups and special occasions."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng cách âm, phù hợp tiệc công ty, sinh nhật và sự kiện đặc biệt.", "Soundproofed private room, ideal for corporate parties, birthdays and special events."))
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
            .wardName(of("P. Láng Hạ", "Lang Ha Ward"))
            .addressLine(of("70 Nguyên Hồng, Q. Đống Đa, Hà Nội", "70 Nguyen Hong, Dong Da District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng", "Mr.Big Beef Stone-Grilled Beef - Nguyen Hong"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Chuỗi bò nướng tảng kiểu Mỹ đầu tiên tại Hà Nội — buffet nướng lẩu phong cách Viễn Tây tại Đống Đa.",
                                 "Hanoi's first American stone-grilled beef chain — Wild West BBQ hotpot buffet in Dong Da."))
            .description(of("Mr.Big Beef Bò Nướng Tảng – Nguyên Hồng là chi nhánh thuộc chuỗi nhà hàng bò Mỹ nướng tảng đầu tiên " +
                            "tại Hà Nội, tọa lạc tại 70 Nguyên Hồng, quận Đống Đa. Nhà hàng mang phong cách Viễn Tây nước Mỹ " +
                            "với sức chứa 230 khách trên 2 tầng, phục vụ buffet nướng, buffet nướng lẩu và gọi món theo phong " +
                            "cách Mỹ với thịt bò nhập khẩu hảo hạng. Phù hợp cho gia đình, bạn bè, tiệc công ty và các sự kiện đặc biệt.",
                            "Mr.Big Beef Stone-Grilled Beef – Nguyen Hong is a branch of Hanoi's first American stone-grilled beef " +
                            "restaurant chain, located at 70 Nguyen Hong, Dong Da District. The restaurant carries a Wild West American " +
                            "style with a 230-guest capacity across two floors, serving BBQ buffet, BBQ hotpot buffet and a la carte " +
                            "using premium imported US beef. Ideal for families, friends, corporate parties and special events."))
            .phone("0931006005")
            .bookingHotline("0931006005")
            .website("https://pasgo.vn/nha-hang/mr-big-beef-bo-nuong-tang-nguyen-hong")
            .minPricePerPerson(new BigDecimal("390000"))
            .maxPricePerPerson(new BigDecimal("600000"))
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
