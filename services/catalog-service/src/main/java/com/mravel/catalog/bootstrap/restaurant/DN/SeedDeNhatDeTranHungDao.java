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
public class SeedDeNhatDeTranHungDao {

    @Bean
    CommandLineRunner seedDeNhatDeTranHungDaoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedDeNhatDeTranHungDao] start");
            RestaurantDoc doc = restaurantDeNhatDeTranHungDao();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedDeNhatDeTranHungDao] done");
        };
    }

    // =====================================================================
    //      NHÀ HÀNG ĐỆ NHẤT DÊ - TRẦN HƯNG ĐẠO (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantDeNhatDeTranHungDao() {
        String slug = "de-nhat-de-tran-hung-dao-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-1-normal-1169111942133.webp",
                of("Không gian Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo", "De Nhat De Restaurant - Tran Hung Dao interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-2-normal-1169112342134.webp",
                of("Khu vực bàn ăn thoáng mát", "Airy dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-3-normal-1169112942135.webp",
                of("Không gian xanh mát gần gũi thiên nhiên", "Nature-inspired green dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-4-normal-1169113142136.webp",
                of("Lẩu dê thơm ngon nóng hổi", "Fragrant hot goat hotpot"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-5-normal-1169113642137.webp",
                of("Bàn tiệc nhóm đông người", "Large group banquet table"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-6-normal-1169113842138.webp",
                of("Dê nướng than hoa đặc sắc", "Special charcoal-grilled goat"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-7-normal-1169115042139.webp",
                of("Không gian ngoài trời thoáng đãng", "Spacious outdoor seating area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-8-normal-1169115242140.webp",
                of("Mâm cỗ dê tổng hợp đặc biệt", "Special mixed goat platter"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-9-normal-1169115442141.webp",
                of("Trang trí nội thất mộc mạc ấm cúng", "Rustic warm interior decoration"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-10-normal-1169115642142.webp",
                of("Khu vực VIP riêng biệt", "Separate VIP area"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-11-normal-1169115942143.webp",
                of("Tiết canh dê tươi ngon truyền thống", "Traditional fresh goat blood pudding"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-de-nhat-de-tran-hung-dao-12-normal-1169116242144.webp",
                of("Mặt tiền nhà hàng Đệ Nhất Dê", "De Nhat De restaurant facade"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/1.jpg",
                of("Menu Đệ Nhất Dê trang 1", "De Nhat De menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/2.jpg",
                of("Menu Đệ Nhất Dê trang 2", "De Nhat De menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/3.jpg",
                of("Menu Đệ Nhất Dê trang 3", "De Nhat De menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/4.jpg",
                of("Menu Đệ Nhất Dê trang 4", "De Nhat De menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/5.jpg",
                of("Menu Đệ Nhất Dê trang 5", "De Nhat De menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/6.jpg",
                of("Menu Đệ Nhất Dê trang 6", "De Nhat De menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/7.jpg",
                of("Menu Đệ Nhất Dê trang 7", "De Nhat De menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/8.jpg",
                of("Menu Đệ Nhất Dê trang 8", "De Nhat De menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/9.jpg",
                of("Menu Đệ Nhất Dê trang 9", "De Nhat De menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/10.jpg",
                of("Menu Đệ Nhất Dê trang 10", "De Nhat De menu page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/11.jpg",
                of("Menu Đệ Nhất Dê trang 11 — Đặc sản", "De Nhat De menu page 11 — Specialties"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/12.jpg",
                of("Menu Đệ Nhất Dê trang 12 — Đồ uống", "De Nhat De menu page 12 — Beverages"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/denhatde/13.jpg",
                of("Menu Đệ Nhất Dê trang 13 — Combo", "De Nhat De menu page 13 — Combo sets"), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("GOAT_SPECIALTY")
                .name(of("Đặc sản thịt dê", "Goat meat specialty"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    9, 0, 21, 0),
            openingHour(DayOfWeek.TUESDAY,   9, 0, 21, 0),
            openingHour(DayOfWeek.WEDNESDAY, 9, 0, 21, 0),
            openingHour(DayOfWeek.THURSDAY,  9, 0, 21, 0),
            openingHour(DayOfWeek.FRIDAY,    9, 0, 21, 0),
            openingHour(DayOfWeek.SATURDAY,  9, 0, 21, 0),
            openingHour(DayOfWeek.SUNDAY,    9, 0, 21, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Tiệc liên hoan", "Company party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm đông", "Large group gathering")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("COMPANY_EVENT", of("Sự kiện tổ chức", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business entertainment")),
            suitable("ANNIVERSARY",   of("Kỷ niệm & liên hoan", "Anniversary & celebration")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",   of("Mộc mạc, gần gũi thiên nhiên", "Rustic, nature-inspired")),
            ambience("VIBRANT",  of("Náo nhiệt, sôi động", "Vibrant and lively")),
            ambience("COZY",     of("Ấm cúng, thoải mái", "Cozy and comfortable")),
            ambience("MODERN",   of("Hiện đại pha truyền thống", "Modern blended with tradition"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Lẩu dê tứ bảo", "Goat Four-Treasure Hotpot"),
                of("Nồi lẩu dê kết hợp bốn phần ngon nhất: thịt dê tươi, xương dê ninh, chân dê và nội tạng " +
                   "dê trong nước dùng đậm đà từ sả, gừng và gia vị đặc biệt. Ăn kèm bún tươi và rau sống.",
                   "Goat hotpot combining the four best parts: fresh goat meat, braised goat bones, goat trotter " +
                   "and goat offal in a rich broth of lemongrass, ginger and special spices. Served with fresh " +
                   "rice vermicelli and herbs."),
                new BigDecimal("350000")),
            signatureDish(of("Dê nướng than hoa nguyên con", "Whole Charcoal-Grilled Goat"),
                of("Dê non được tẩm ướp gia vị bí truyền rồi nướng than hoa chậm trong nhiều giờ, da vàng " +
                   "giòn rụm, thịt bên trong mềm ngọt và thơm. Phục vụ theo phần hoặc nguyên con theo đặt trước.",
                   "Young goat marinated in secret spice blend then slow charcoal-grilled for hours until the " +
                   "skin turns golden crispy while the meat inside remains tender and fragrant. Served by portion " +
                   "or whole by advance order."),
                new BigDecimal("450000")),
            signatureDish(of("Tái dê chanh sả", "Goat Tartare with Lemongrass and Lime"),
                of("Thịt dê tươi thái mỏng ngâm chanh tươi và sả, trộn cùng gừng, ớt, rau thơm và vừng rang. " +
                   "Món khai vị nổi tiếng giòn mát, thơm lừng đặc trưng của nhà hàng.",
                   "Fresh goat meat thinly sliced and marinated in fresh lime and lemongrass, mixed with ginger, " +
                   "chili, fresh herbs and toasted sesame. The restaurant's signature crispy and fragrant appetizer."),
                new BigDecimal("180000")),
            signatureDish(of("Cháo dê hầm gừng", "Goat Congee Braised with Ginger"),
                of("Cháo trắng nấu từ gạo lứt hầm cùng thịt dê bằm nhỏ và gừng tươi giã dập, nêm nước mắm " +
                   "ngon, thêm hành lá và tiêu vỡ. Món bổ dưỡng nóng ấm, thích hợp mọi lứa tuổi.",
                   "White rice congee cooked with minced goat meat and freshly pounded ginger, seasoned with " +
                   "quality fish sauce, topped with green onion and cracked pepper. Nourishing warm dish " +
                   "suitable for all ages."),
                new BigDecimal("90000")),
            signatureDish(of("Dê xào lăn", "Stir-fried Goat Méat"),
                of("Thịt dê non xào với sả ớt và mắm ruốc Huế, thêm nước dừa và lá cà ri tươi cho hương vị " +
                   "đặc trưng miền Trung. Thịt mềm đậm vị, ăn kèm cơm trắng hoặc bánh mì.",
                   "Young goat meat stir-fried with lemongrass, chili and Hue shrimp paste, with coconut water " +
                   "and fresh curry leaves for a distinctive Central Vietnamese flavor. Tender flavorful meat " +
                   "served with steamed rice or baguette."),
                new BigDecimal("160000")),
            signatureDish(of("Tiết canh dê tươi", "Fresh Goat Blood Pudding"),
                of("Tiết canh dê tươi truyền thống pha chế theo công thức gia truyền, thêm lạc rang và rau thơm " +
                   "tươi. Món đặc sản mang đậm bản sắc ẩm thực Việt Nam, chỉ phục vụ buổi sáng và trưa.",
                   "Traditional fresh goat blood pudding prepared by family recipe, with roasted peanuts and fresh " +
                   "herbs. A signature Vietnamese dish served only during morning and lunch hours."),
                new BigDecimal("70000")),
            signatureDish(of("Nướng thịt dê xiên que", "Skewered Goat Meat Grill"),
                of("Thịt dê tươi xiên que tẩm ướp sả gừng nướng than hoa, ăn kèm tương đen và dưa leo tươi. " +
                   "Món nhậu được yêu thích nhất khi uống bia hoặc rượu nhỏ trong bữa liên hoan.",
                   "Fresh goat meat on skewers marinated in lemongrass and ginger then charcoal-grilled, served " +
                   "with black bean sauce and fresh cucumber. Most popular drinking snack for beer or rice wine " +
                   "during parties."),
                new BigDecimal("120000")),
            signatureDish(of("Súp dê bổ dưỡng", "Nourishing Goat Soup"),
                of("Xương dê ninh kỹ cùng hà thủ ô, kỷ tử và các vị thuốc bắc trong nhiều giờ thành nước dùng " +
                   "vàng trong bổ dưỡng, thêm thịt dê mềm tan và nấm đông cô.",
                   "Goat bones slowly simmered with fo-ti, goji berries and Chinese medicinal herbs for hours " +
                   "into a clear golden nourishing broth, served with tender melt-in-mouth goat meat and " +
                   "shiitake mushrooms."),
                new BigDecimal("130000")),
            signatureDish(of("Mì dê gân nước dùng hầm", "Goat Tendon Noodle Soup"),
                of("Tô mì sợi to với gân dê hầm mềm nhuyễn, thịt dê thái mỏng và nước dùng trong từ xương hầm, " +
                   "rắc hành phi và ngò rí thơm phức.",
                   "Large noodle bowl with melt-soft braised goat tendon, thinly sliced goat meat and clear " +
                   "bone-simmered broth, topped with fried shallots and fragrant cilantro."),
                new BigDecimal("110000")),
            signatureDish(of("Dê om thạch lựu", "Goat Braised with Pomegranate Sauce"),
                of("Thịt dê om cùng nước cốt thạch lựu và gia vị đặc biệt cho màu đỏ đẹp mắt và vị ngọt chua " +
                   "hài hòa, thịt mềm dai và hương thơm quyến rũ độc đáo.",
                   "Goat meat braised with pomegranate juice and special spices for beautiful red color and " +
                   "balanced sweet-sour flavor, with tender chewy meat and uniquely captivating aroma."),
                new BigDecimal("200000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("LAU_DE")
                .name(of("Lẩu dê", "Goat Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu dê tứ bảo (2-3 người)", "Goat Four-Treasure Hotpot (2-3 pax)"))
                        .description(of("Nồi lẩu dê 4 phần ngon nhất, ăn kèm bún và rau sống.",
                                       "Hotpot with 4 best goat parts, served with noodles and fresh herbs."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("450000"))
                        .unit(of("nồi", "pot"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "goat", "Vietnamese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu dê thường (1-2 người)", "Regular Goat Hotpot (1-2 pax)"))
                        .description(of("Lẩu dê cỡ nhỏ cho 1-2 người, thịt dê tươi và nước dùng đặc biệt.",
                                       "Small goat hotpot for 1-2 persons, fresh goat meat and special broth."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "goat", "Vietnamese"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("DE_NUONG")
                .name(of("Dê nướng & món chính", "Grilled Goat & Mains"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Dê nướng than hoa (theo phần)", "Charcoal-grilled goat (by portion)"))
                        .description(of("Thịt dê nướng than hoa vàng giòn, thơm lừng, ăn kèm muối sả ớt.",
                                       "Golden crispy charcoal-grilled goat meat, fragrant, with salt-lemongrass-chili."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("300000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grilled", "goat", "Vietnamese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Dê xào lăn sả ớt", "Stir-fried lemongrass chili goat"))
                        .description(of("Thịt dê xào cùng sả, ớt, nước dừa và lá cà ri. Đậm đà, thơm.",
                                       "Goat stir-fried with lemongrass, chili, coconut water and curry leaves. Rich and fragrant."))
                        .priceFrom(new BigDecimal("160000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("stir-fry", "goat", "Vietnamese"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .carParkingLocation(of("Không có bãi đỗ ô tô riêng; khách đỗ dọc đường Trần Hưng Đạo",
                                   "No private car parking; street parking available on Tran Hung Dao"))
            .carParkingFeeType(ParkingFeeType.UNKNOWN)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy trước và bên hông nhà hàng", "Motorbike parking in front and beside the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng nằm trên đường Trần Hưng Đạo, Quận Sơn Trà; xe máy gửi miễn phí.",
                      "Restaurant on Tran Hung Dao street, Son Tra District; free motorbike parking."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "FREE_MOTORBIKE_PARKING",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE_SERVICE",
            "MC_SERVICE",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SMOKING_AREA",
            "KIDS_CHAIR",
            "SET_LUNCH",
            "SOUND_LIGHT"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Đệ Nhất Dê Trần Hưng Đạo — Thiên đường thịt dê giữa Đà Nẵng",
                   "De Nhat De Tran Hung Dao — Goat Meat Paradise in Da Nang")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Đệ Nhất Dê tọa lạc tại 237 Trần Hưng Đạo, Phường An Hải Bắc, Quận Sơn Trà, " +
                   "TP. Đà Nẵng — một địa chỉ ẩm thực chuyên về các món từ dê được nhiều thực khách biết đến " +
                   "và yêu thích. \"Đệ Nhất\" là danh hiệu tự hào gắn với chất lượng thịt dê tươi sạch và " +
                   "phong phú về cách chế biến, từ lẩu, nướng, hấp đến gỏi và tiết canh — tất cả đều mang " +
                   "hương vị đặc sắc của ẩm thực miền Trung Việt Nam.",
                   "De Nhat De Restaurant is located at 237 Tran Hung Dao Street, An Hai Bac Ward, Son Tra " +
                   "District, Da Nang City — a culinary destination specializing in goat dishes beloved by many " +
                   "diners. 'De Nhat' (Number One) is a proud title tied to fresh clean goat meat quality and " +
                   "rich preparation variety — hotpot, grilling, steaming, salad and blood pudding — all " +
                   "carrying the distinctive flavors of Central Vietnamese cuisine.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng có không gian rộng lớn, thoáng mát với sức chứa khoảng 200 khách, gồm khu vực " +
                   "bàn thường ngoài trời và trong nhà, cùng 3 phòng riêng sức chứa đến 30 khách/phòng phù hợp " +
                   "cho các buổi tiệc gia đình, tiệc doanh nghiệp và sinh nhật. Không gian được trang trí theo " +
                   "phong cách mộc mạc, gần gũi với thiên nhiên — sử dụng tre, gỗ và cây xanh tạo cảm giác " +
                   "trong lành và dân dã đặc trưng của nhà hàng đặc sản Việt.",
                   "The restaurant has spacious, airy premises with capacity of approximately 200 guests, " +
                   "including outdoor and indoor dining areas plus 3 private rooms each accommodating up to 30 " +
                   "guests — suitable for family banquets, corporate events and birthday parties. The space is " +
                   "decorated in a rustic nature-inspired style using bamboo, wood and greenery that creates a " +
                   "fresh and homely feel characteristic of Vietnamese specialty restaurants.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                   "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Đệ Nhất Dê phong phú và đa dạng với hơn 30 món từ dê. Nổi bật nhất là Lẩu dê " +
                   "tứ bảo — nồi lẩu hội tụ bốn phần ngon nhất của con dê trong nước dùng đậm đà từ sả gừng; " +
                   "Dê nướng than hoa vàng giòn thơm lừng; Tái dê chanh sả mát lạnh; Dê xào lăn sả ớt đặc " +
                   "trưng miền Trung. Mỗi món đều sử dụng thịt dê tươi nhập trực tiếp từ các trang trại uy tín " +
                   "và được chế biến theo công thức gia truyền. Giá trung bình 150.000 – 200.000 đồng/người.",
                   "De Nhat De's menu is rich and diverse with over 30 goat dishes. Most notable are the Goat " +
                   "Four-Treasure Hotpot — a pot combining the four best parts in a rich lemongrass-ginger broth; " +
                   "Golden crispy charcoal-grilled goat; Cool lime-lemongrass goat tartare; and the signature " +
                   "Central Vietnamese lemongrass-chili stir-fried goat. Every dish uses fresh goat sourced " +
                   "directly from reputable farms and prepared by family recipes. Average price 150,000 – " +
                   "200,000 VND per person.")),
            heading(ContentSection.STORY,
                of("Nguồn gốc thịt dê sạch",
                   "Clean goat meat sourcing")),
            paragraph(ContentSection.STORY,
                of("Điểm khác biệt làm nên thương hiệu Đệ Nhất Dê chính là cam kết về chất lượng thịt dê. " +
                   "Nhà hàng chỉ sử dụng dê núi đá tươi — giống dê địa phương miền Trung ăn cỏ núi tự nhiên, " +
                   "thịt thơm ngọt và ít mỡ hơn dê nuôi công nghiệp. Dê được nhập mỗi ngày, không đông lạnh, " +
                   "đảm bảo độ tươi ngon tối ưu. Khách hàng có thể yêu cầu xem dê trước khi chế biến.",
                   "What sets De Nhat De apart is its commitment to goat meat quality. The restaurant only uses " +
                   "fresh rock goat — a local Central Vietnamese breed that grazes on natural mountain grass, " +
                   "yielding more fragrant and leaner meat than factory-farmed goat. Goats are sourced daily, " +
                   "never frozen, ensuring optimal freshness. Customers can request to see the goat before " +
                   "processing.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Đệ Nhất Dê phù hợp đặc biệt cho các buổi tụ tập gia đình đông người, liên hoan công ty " +
                   "và tiệc sinh nhật nhờ không gian rộng và phòng riêng. Cũng lý tưởng cho nhóm bạn nhậu buổi " +
                   "chiều tối, với các món nhậu đặc sắc như dê nướng xiên que và tiết canh. Khách Tây yêu thích " +
                   "ẩm thực Việt Nam đặc sản sẽ có trải nghiệm thú vị và khác lạ tại đây.",
                   "De Nhat De is especially suitable for large family gatherings, company parties and birthday " +
                   "celebrations thanks to spacious areas and private rooms. Also ideal for friend groups in " +
                   "the afternoon-evening, with popular drinking dishes like grilled goat skewers and blood " +
                   "pudding. Western guests interested in authentic Vietnamese specialty cuisine will find a " +
                   "unique and interesting experience here.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích",
                   "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng có đội ngũ phục vụ nhiệt tình, chuyên nghiệp. Dịch vụ đặt tiệc riêng có thể trang " +
                   "trí phòng theo yêu cầu (sinh nhật, thôi nôi, tiệc tất niên) kèm MC và âm thanh ánh sáng. " +
                   "Điều hòa toàn bộ khu vực trong nhà; bàn ngoài trời thoáng mát về tối. Wifi miễn phí, thanh " +
                   "toán Visa/Master, xuất hóa đơn VAT. Set lunch đặc biệt từ 11:00 – 14:00.",
                   "The restaurant has an enthusiastic and professional service team. Private banquet service " +
                   "includes room decoration on request (birthdays, baby full-moon, year-end parties) with MC " +
                   "and sound-light systems. Full air conditioning in indoor areas; outdoor tables are breezy " +
                   "in the evening. Free WiFi, Visa/Master accepted, VAT invoices available. Special set lunch " +
                   "11:00 – 14:00.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm tại 237 Trần Hưng Đạo, Phường An Hải Bắc, Quận Sơn Trà — cách bãi biển Mỹ " +
                   "Khê khoảng 1,5 km và cách trung tâm thành phố (cầu Rồng) khoảng 3 km. Thuận tiện di chuyển " +
                   "bằng taxi, Grab hoặc xe máy. Bãi đỗ xe máy miễn phí trước và bên hông nhà hàng; xe ô tô " +
                   "có thể đỗ dọc đường Trần Hưng Đạo.",
                   "The restaurant is at 237 Tran Hung Dao, An Hai Bac Ward, Son Tra District — about 1.5 km " +
                   "from My Khe Beach and about 3 km from the city center (Dragon Bridge). Conveniently " +
                   "accessible by taxi, Grab or motorbike. Free motorbike parking in front and beside the " +
                   "restaurant; cars can park along Tran Hung Dao Street.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                   "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Đệ Nhất Dê nhận đặt bàn trước qua điện thoại và hệ thống PasGo. Phòng riêng cần đặt trước " +
                   "ít nhất 2 tiếng và có yêu cầu tối thiểu phần ăn. Không thu phí phục vụ. Với thịt dê tươi " +
                   "chất lượng cao, thực đơn đa dạng hơn 30 món và không gian rộng rãi phù hợp mọi quy mô tiệc, " +
                   "Đệ Nhất Dê xứng đáng là lựa chọn số một cho những người yêu thịt dê tại Đà Nẵng.",
                   "De Nhat De accepts reservations by phone and PasGo. Private rooms require at least 2 hours " +
                   "advance booking and minimum food order. No service charge. With high-quality fresh goat " +
                   "meat, diverse menu of over 30 dishes and spacious premises suitable for any party size, " +
                   "De Nhat De deserves to be the first choice for goat meat lovers in Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.228966, 16.076938})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày Tết dương lịch", "Promotions not applicable on New Year's Day")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4",          "Promotions not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5",           "Promotions not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày Quốc Khánh",    "Promotions not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm giao thừa",      "Promotions not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho đặt bàn thông thường; tiệc lớn > 20 người liên hệ trực tiếp.",
                             "No deposit for regular bookings; parties over 20 guests contact directly."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi đặt bàn qua PasGo; set lunch trưa giá tốt.",
                                 "Promotions via PasGo booking; discounted lunch sets."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo từng thời điểm.", "Promotion details vary by period."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice issued on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không thu phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ ngoài vào.", "Outside drinks not permitted."))
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
                .minPeople(1)
                .maxPeople(4)
                .totalTables(30)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường trong nhà và ngoài trời.", "Standard indoor and outdoor table."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(4)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Bàn VIP khu vực ưu tiên, có mái che.", "Priority VIP area with canopy."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng yên tĩnh cho tiệc lớn, có thể trang trí theo yêu cầu.",
                         "Quiet private room for large parties, custom decoration available."))
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
            .wardName(of("P. An Hải Bắc", "An Hai Bac Ward"))
            .addressLine(of("237 Trần Hưng Đạo, P. An Hải Bắc, Q. Sơn Trà, TP. Đà Nẵng",
                            "237 Tran Hung Dao, An Hai Bac Ward, Son Tra District, Da Nang City"))
            .location(new double[]{108.228966, 16.076938})
            .name(of("Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo", "De Nhat De Goat Restaurant - Tran Hung Dao"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Thiên đường món dê tươi tại Sơn Trà — lẩu dê tứ bảo, dê nướng than hoa, tái dê chanh sả.",
                                 "Goat meat paradise in Son Tra — four-treasure hotpot, charcoal-grilled goat, lime-lemongrass goat tartare."))
            .description(of("Nhà hàng Đệ Nhất Dê tại 237 Trần Hưng Đạo, Q. Sơn Trà, TP. Đà Nẵng chuyên các món dê núi đá tươi " +
                            "theo công thức gia truyền: lẩu, nướng, xào lăn, tái, cháo và tiết canh. Không gian 200 khách, " +
                            "có phòng riêng và khu ngoài trời. Giá từ 150.000 – 200.000 đồng/người. Mở cửa 09:00 – 21:00 hàng ngày.",
                            "De Nhat De Restaurant at 237 Tran Hung Dao, Son Tra District, Da Nang specializes in fresh rock goat " +
                            "dishes by family recipe: hotpot, grilling, stir-fry, tartare, congee and blood pudding. Capacity 200 " +
                            "guests with private rooms and outdoor area. Price 150,000 – 200,000 VND/person. Open daily 09:00 – 21:00."))
            .phone("0905123456")
            .bookingHotline("0905123456")
            .website("https://pasgo.vn/nha-hang/de-nhat-de-tran-hung-dao")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("200000"))
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
