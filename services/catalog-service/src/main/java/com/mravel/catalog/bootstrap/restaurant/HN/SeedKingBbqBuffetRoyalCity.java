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
public class SeedKingBbqBuffetRoyalCity {

    @Bean
    CommandLineRunner seedKingBbqBuffetRoyalCityRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedKingBbqBuffetRoyalCity] start");
            RestaurantDoc doc = restaurantKingBbqBuffetRoyalCity();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedKingBbqBuffetRoyalCity] done");
        };
    }

    // =====================================================================
    //       KING BBQ BUFFET ROYAL CITY - R4-40, TẦNG B2, TTTM ROYAL CITY
    // =====================================================================

    private static RestaurantDoc restaurantKingBbqBuffetRoyalCity() {
        String slug = "king-bbq-buffet-royal-city";

        // ---------- Gallery ảnh nhà hàng (lấy URL normal, loại bỏ -300-) ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-1-normal-1105728740402.webp",
                of("King BBQ Buffet Royal City - Không gian nướng BBQ Hàn Quốc", "King BBQ Buffet Royal City - Korean BBQ dining space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-2-normal-1105728840403.webp",
                of("Khu vực bàn nướng hiện đại", "Modern BBQ table area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-3-normal-1105728940404.webp",
                of("Không gian sang trọng tại Royal City", "Elegant space at Royal City"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-4-normal-1105729140405.webp",
                of("Khu vực ăn uống rộng rãi", "Spacious dining area"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-5-normal-1105729240406.webp",
                of("Bàn nướng chuẩn vị Hàn", "Authentic Korean BBQ table"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-6-normal-1105729440407.webp",
                of("Quầy buffet phong phú", "Abundant buffet counter"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-7-normal-1105729940408.webp",
                of("Thực đơn nướng đa dạng", "Diverse BBQ menu"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-8-normal-1105730040409.webp",
                of("Không gian nội thất xứ Kim Chi", "Korean-style interior"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-9-normal-1105731540410.webp",
                of("Khu vực tiệc tùng", "Event & party area"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-10-normal-1105732040411.webp",
                of("Món nướng thơm ngon", "Aromatic grilled dishes"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-11-normal-1105732140412.webp",
                of("Chi tiết trang trí nhà hàng", "Restaurant decoration details"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-king-bbq-buffet-royal-city-12-normal-1105732440413.webp",
                of("Toàn cảnh khu vực ăn uống", "Full view of dining area"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/kingbbqbuffetroyalcity/nha-hang-king-bbq-buffet-royal-city-1.jpg",
                of("King BBQ - Góc chụp thực tế", "King BBQ - Actual photo"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/kingbbqbuffetroyalcity/nha-hang-king-bbq-buffet-royal-city-2.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/kingbbqbuffetroyalcity/nha-hang-king-bbq-buffet-royal-city-3.jpg",
                of("", ""), false, 14)
        );

        // ---------- Menu images (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-1.jpg",
                of("Bảng giá King BBQ Royal City trang 1", "King BBQ Royal City price list page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/King%20BBQ%20HN/Nha-hang-King_BBQ_HN-13.jpg",
                of("Bảng giá King BBQ Royal City trang 13", "King BBQ Royal City price list page 13"), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN_BBQ")
                .name(of("Nướng Hàn Quốc", "Korean BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_KOREAN")
                .name(of("Buffet Hàn Quốc", "Korean Buffet"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:45 - 22:00 mỗi ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 45, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 45, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 45, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 45, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 45, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 45, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 45, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại", "Modern")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("LUXURIOUS", of("Sang trọng", "Luxurious")),
            ambience("COZY",      of("Ấm cúng", "Cozy"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Thịt bò Wagyu nướng", "Grilled Wagyu beef")),
            signatureDishL(of("Sườn non bò nướng tảng", "Grilled beef short ribs")),
            signatureDishL(of("Thịt ba chỉ Hàn Quốc (Samgyeopsal)", "Korean pork belly (Samgyeopsal)")),
            signatureDishL(of("Gà ướp sốt gochujang nướng", "Gochujang marinated grilled chicken")),
            signatureDishL(of("Hải sản tươi nướng tại bàn", "Fresh seafood grilled at table")),
            signatureDishL(of("Kim chi tự làm chuẩn vị Hàn", "Authentic homemade kimchi")),
            signatureDishL(of("Canh miso Hàn Quốc", "Korean miso soup")),
            signatureDishL(of("Tokbokki cay ngọt", "Sweet and spicy tteokbokki")),
            signatureDishL(of("Bánh gạo Hàn Quốc nướng", "Grilled Korean rice cakes")),
            signatureDishL(of("Kem tráng miệng kiểu Hàn", "Korean-style dessert ice cream"))
        );

        // ---------- Menu sections ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_NUONG")
                .name(of("Buffet Nướng", "BBQ Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Nướng T2-T6", "Weekday BBQ Buffet (Mon-Fri)"))
                        .description(of("Buffet nướng không giới hạn thịt bò, thịt heo, gà, hải sản và rau củ chuẩn vị Hàn Quốc.",
                            "Unlimited BBQ buffet with beef, pork, chicken, seafood and vegetables in authentic Korean style."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("bbq", "buffet", "weekday"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Nướng + Lẩu T2-T6", "Weekday BBQ + Hotpot Buffet (Mon-Fri)"))
                        .description(of("Buffet nướng kết hợp lẩu không giới hạn ngày thường.",
                            "Unlimited BBQ combined with hotpot buffet on weekdays."))
                        .priceFrom(new BigDecimal("399000"))
                        .priceTo(new BigDecimal("399000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("bbq", "hotpot", "buffet", "weekday"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Nướng + Lẩu T7-CN & Lễ", "Weekend & Holiday BBQ + Hotpot Buffet"))
                        .description(of("Buffet nướng kết hợp lẩu không giới hạn vào cuối tuần và ngày lễ.",
                            "Unlimited BBQ and hotpot buffet on weekends and public holidays."))
                        .priceFrom(new BigDecimal("449000"))
                        .priceTo(new BigDecimal("449000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("bbq", "hotpot", "buffet", "weekend"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe tòa nhà TTTM Royal City (tầng B1, B2)", "Royal City mall basement parking (B1, B2 floors)"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy tầng B2 TTTM Royal City", "Royal City B2 motorbike parking"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Khách hàng mua sắm/ăn uống tại Royal City được miễn/giảm phí đỗ xe theo quy định của trung tâm thương mại.",
                "Shoppers and diners at Royal City may receive parking fee discounts per mall policy."))
            .build();

        // ---------- Amenity codes ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "KIDS_CHAIR",
            "PAID_CAR_PARKING",
            "PAID_MOTORBIKE_PARKING",
            "SET_LUNCH",
            "BIRTHDAY_DECOR",
            "SMOKING_AREA"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Tổng quan King BBQ Buffet Royal City", "King BBQ Buffet Royal City - Overview")),
            paragraph(ContentSection.STORY,
                of("King BBQ Buffet Royal City tọa lạc tại R4-40, tầng B2, Trung tâm thương mại Royal City, số 72A Nguyễn Trãi, " +
                   "quận Thanh Xuân, Hà Nội. Đây là một trong những chuỗi nhà hàng buffet nướng Hàn Quốc uy tín hàng đầu tại Việt Nam, " +
                   "nổi bật với thực đơn phong phú, không gian sang trọng mang đậm chất xứ Kim Chi giữa lòng trung tâm thương mại lớn nhất thủ đô.",
                   "King BBQ Buffet Royal City is located at R4-40, B2 floor of Royal City Shopping Mall, 72A Nguyen Trai, " +
                   "Thanh Xuan district, Hanoi. It is one of Vietnam's premier Korean BBQ buffet chains, " +
                   "renowned for its rich menu, luxurious ambience deeply rooted in Korean culture, situated in the capital's largest shopping mall.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian rộng lớn với sức chứa lên đến 300 thực khách, được bố trí khoa học với hệ thống " +
                   "bàn nướng hiện đại tích hợp máy hút khói, đảm bảo trải nghiệm nướng BBQ thoải mái mà không lo ám khói. " +
                   "3 phòng VIP riêng biệt có thể đáp ứng từng nhóm khách lên đến 50 người, lý tưởng cho tiệc công ty hay sự kiện đặc biệt.",
                   "The restaurant boasts a large space accommodating up to 300 guests, thoughtfully arranged with state-of-the-art BBQ tables " +
                   "featuring built-in smoke extractors for a comfortable, smoke-free grilling experience. " +
                   "Three separate VIP rooms can host groups of up to 50 guests, ideal for company parties or special events.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("King BBQ tự hào với thực đơn buffet không giới hạn gồm hàng chục loại thịt cao cấp như Wagyu, sườn non bò, " +
                   "ba chỉ Hàn Quốc (Samgyeopsal), gà ướp gochujang cùng hải sản tươi sống. Bên cạnh đó là các món ăn kèm truyền thống " +
                   "Hàn Quốc như kim chi, canh miso, tokbokki, bánh gạo nướng và các loại nước sốt pha chế theo công thức bí truyền.",
                   "King BBQ prides itself on an unlimited buffet menu featuring dozens of premium meats such as Wagyu beef, short ribs, " +
                   "Korean pork belly (Samgyeopsal), gochujang marinated chicken and fresh seafood. Complemented by traditional Korean side dishes " +
                   "including kimchi, miso soup, tteokbokki, grilled rice cakes and sauces crafted from secret recipes.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá từ 299.000đ - 449.000đ/người, King BBQ Buffet Royal City phù hợp cho mọi dịp tụ họp: bữa ăn gia đình cuối tuần, " +
                   "tiệc sinh nhật, liên hoan văn phòng, gặp gỡ bạn bè hay tiếp đãi khách hàng. Vị trí trong lòng trung tâm thương mại " +
                   "Royal City giúp thực khách kết hợp mua sắm và ăn uống thuận tiện trong một chuyến đi.",
                   "With prices from 299,000 to 449,000 VND/person, King BBQ Buffet Royal City suits every gathering: weekend family meals, " +
                   "birthday parties, office parties, friends meetups or business entertainment. Its location inside Royal City Shopping Mall " +
                   "allows guests to conveniently combine shopping and dining in one trip.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật", "Services & key amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị hệ thống âm thanh ánh sáng chuyên nghiệp, màn chiếu và dịch vụ MC phục vụ các sự kiện doanh nghiệp. " +
                   "Đội ngũ nhân viên được đào tạo theo chuẩn dịch vụ Hàn Quốc: phục vụ tận bàn, thay than, bổ sung nguyên liệu liên tục. " +
                   "Nhà hàng hỗ trợ thanh toán thẻ Visa/Mastercard và xuất hóa đơn VAT theo yêu cầu.",
                   "The restaurant is equipped with professional sound and lighting systems, projection screens and MC services for corporate events. " +
                   "Staff are trained to Korean service standards: table-side service, charcoal replacement, and continuous ingredient replenishment. " +
                   "The restaurant accepts Visa/Mastercard payments and issues VAT invoices on request.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại tầng B2, TTTM Royal City, 72A Nguyễn Trãi, quận Thanh Xuân, nhà hàng nằm ngay cạnh ga tàu điện ngầm " +
                   "Nguyễn Trãi (metro số 2 đang xây dựng). Thực khách có thể đến bằng xe cá nhân gửi tại bãi đỗ xe Royal City, " +
                   "hoặc bắt xe buýt, grab tới cổng B Royal City trên đường Nguyễn Trãi.",
                   "Located on B2 floor of Royal City Mall, 72A Nguyen Trai, Thanh Xuan district, the restaurant is near the upcoming " +
                   "Nguyen Trai metro station (metro line 2). Guests can arrive by private vehicle and park at Royal City's parking facility, " +
                   "or take a bus/grab to Royal City Gate B on Nguyen Trai street.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn King BBQ", "Booking policy & why choose King BBQ")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước qua hệ thống giúp giữ chỗ trong khung giờ mong muốn, đặc biệt vào cuối tuần và ngày lễ luôn đông khách. " +
                   "Nhà hàng áp dụng chính sách đặt cọc rõ ràng cho nhóm đông, có nhiều gói khuyến mãi hấp dẫn cho khách đặt trực tuyến. " +
                   "King BBQ là lựa chọn lý tưởng khi bạn muốn thưởng thức buffet nướng Hàn Quốc đẳng cấp ngay trong trung tâm thương mại hiện đại nhất Hà Nội.",
                   "Advance online booking helps secure your preferred time slot, especially on busy weekends and public holidays. " +
                   "The restaurant has a clear deposit policy for large groups and offers attractive promotions for online reservations. " +
                   "King BBQ is the ideal choice when you want to enjoy premium Korean BBQ buffet in Hanoi's most modern shopping mall.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 300.000đ/chai.", "Wine corkage fee: 300,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("500000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang ngoài: 500.000đ/chai.", "Spirits corkage fee: 500,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc đặt phòng VIP.",
                "Applies to bookings of 8 or more guests or VIP room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi dành cho khách đặt bàn online, không áp dụng vào ngày lễ và cuối tuần theo quy định.",
                "Promotions apply to online bookings; not applicable on public holidays and weekends as specified."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng kiểm tra khi đặt bàn.",
                "Promotion details vary; please check when booking."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng xuất hóa đơn VAT theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phí phục vụ 5% áp dụng cho phòng VIP.", "5% service charge applies to VIP rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không cho phép mang thức ăn từ bên ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Cho phép mang đồ uống từ ngoài vào, có phụ phí theo loại.", "Outside drinks allowed with corkage fee by type."))
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
                .id("tb-4-standard")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(30)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nướng tiêu chuẩn, phù hợp nhóm nhỏ.", "Standard BBQ table, suitable for small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP vị trí đẹp trong nhà hàng, ưu tiên phục vụ.", "VIP table with prime location, priority service."))
                .build(),
            TableType.builder()
                .id("pr-50-vip")
                .name(of("Phòng riêng VIP 50 khách", "VIP private room for 50 guests"))
                .seats(50)
                .minPeople(20)
                .maxPeople(50)
                .totalTables(3)
                .depositPrice(new BigDecimal("3000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng cao cấp, phù hợp tiệc công ty và sự kiện đặc biệt.", "Premium private room, suitable for company parties and special events."))
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
            .districtName(of("Quận Thanh Xuân", "Thanh Xuan District"))
            .wardName(of("P. Thanh Xuân Trung", "Thanh Xuan Trung Ward"))
            .addressLine(of("R4-40, Tầng B2, TTTM Royal City, 72A Nguyễn Trãi, Q. Thanh Xuân, Hà Nội",
                "R4-40, B2 Floor, Royal City Shopping Mall, 72A Nguyen Trai, Thanh Xuan District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("King BBQ Buffet Royal City", "King BBQ Buffet Royal City"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng Hàn Quốc không giới hạn chuẩn vị xứ Kim Chi trong lòng TTTM Royal City, Hà Nội.",
                "Unlimited authentic Korean BBQ buffet inside Royal City Shopping Mall, Hanoi."))
            .description(of("King BBQ Buffet Royal City là chuỗi nhà hàng buffet nướng Hàn Quốc hàng đầu, tọa lạc tại tầng B2 " +
                "Trung tâm thương mại Royal City, 72A Nguyễn Trãi, quận Thanh Xuân, Hà Nội. " +
                "Nổi bật với thực đơn phong phú gồm thịt cao cấp, hải sản tươi và các món ăn kèm truyền thống Hàn Quốc, " +
                "nhà hàng mang đến trải nghiệm buffet nướng chuẩn vị Kim Chi giữa không gian sang trọng, hiện đại.",
                "King BBQ Buffet Royal City is a leading Korean BBQ buffet chain, located on B2 floor of Royal City Shopping Mall, " +
                "72A Nguyen Trai, Thanh Xuan district, Hanoi. " +
                "Renowned for its rich menu of premium meats, fresh seafood and traditional Korean side dishes, " +
                "the restaurant delivers an authentic Korean BBQ buffet experience in a luxurious, modern setting."))
            .phone("19006005")
            .email("reservation@kingbbq.vn")
            .website("https://pasgo.vn/nha-hang/king-bbq-buffet-royal-city-2108")
            .facebookPage("https://facebook.com/kingbbqvietnam")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("450000"))
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
