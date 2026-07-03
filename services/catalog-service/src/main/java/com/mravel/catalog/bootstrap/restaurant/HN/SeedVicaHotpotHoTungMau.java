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
public class SeedVicaHotpotHoTungMau {

    @Bean
    CommandLineRunner seedVicaHotpotHoTungMauRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedVicaHotpotHoTungMau] start");

            RestaurantDoc doc = restaurantVicaHotpotHoTungMau();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedVicaHotpotHoTungMau] done");
        };
    }

    // =====================================================================
    //        BUFFET LẨU VICA HOTPOT - HỒ TÙNG MẬU (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantVicaHotpotHoTungMau() {
        String slug = "vica-hotpot-ho-tung-mau";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-1-normal-4204822972549.webp",
                of("Không gian Vica Hotpot Hồ Tùng Mậu", "Vica Hotpot Ho Tung Mau interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-2-normal-4204822872550.webp",
                of("Không gian ăn uống Vica Hotpot", "Vica Hotpot dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-3-normal-4204822172551.webp",
                of("Khu vực bàn buffet lẩu Vica", "Vica hotpot buffet table area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-4-normal-4204822072552.webp",
                of("Món nhúng lẩu tươi ngon", "Fresh hotpot ingredients"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-5-normal-4204821972553.webp",
                of("Không gian hiện đại Vica Hotpot", "Modern interior of Vica Hotpot"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/vica-hotpot-6-normal-4204821872554.webp",
                of("Trang trí nội thất trẻ trung", "Trendy interior decoration"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-1-normal-2260537757103.webp",
                of("Khu vực buffet topping đa dạng", "Diverse topping buffet area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-2-normal-2260537857104.webp",
                of("Nồi lẩu 2 ngăn đặc trưng Vica", "Vica signature 2-section hotpot"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-3-normal-2260537957105.webp",
                of("Không gian đông khách buổi tối", "Busy evening dining space"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-4-normal-2260538057106.webp",
                of("Bàn lẩu được set sẵn chỉnh chu", "Neatly arranged hotpot table"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-5-normal-2260538157107.webp",
                of("Khu vực lẩu nhóm lớn", "Large group hotpot area"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-hotpot-king-dom-ho-tung-mau-6-normal-2260538257108.webp",
                of("Topping buffet phong phú, tươi ngon", "Rich fresh buffet toppings"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Vicahotpot/New/menu-vica-hotpot-11.jpg",
                of("", ""), false, 10)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực Á đa quốc gia", "Asian Fusion"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("THAI")
                .name(of("Thái Lan", "Thai"))
                .region("SOUTHEAST_ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm đông", "Large group meeting")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa nhóm", "Group lunch")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",   of("Hiện đại, trẻ trung", "Modern and trendy")),
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively")),
            ambience("COZY",     of("Ấm cúng, thoải mái", "Cozy and comfortable")),
            ambience("CASUAL",   of("Thân thiện, bình dân", "Casual and friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(50)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Nồi lẩu 2 ngăn Thái - Gà nấm thảo mộc", "2-section Thai & herbal chicken hotpot")),
            signatureDishL(of("Buffet topping hơn 36+ loại tươi ngon", "Buffet of 36+ fresh toppings")),
            signatureDishL(of("Thịt bò nhúng lẩu mỏng tang", "Thinly sliced beef for hotpot")),
            signatureDishL(of("Hải sản tươi: tôm, mực, ngao", "Fresh seafood: shrimp, squid, clams")),
            signatureDishL(of("Nấm đặc sản hỗn hợp", "Mixed specialty mushrooms")),
            signatureDishL(of("Rau xanh tươi sạch đa dạng", "Diverse fresh clean vegetables")),
            signatureDishL(of("Mì sợi và bún tươi nhúng lẩu", "Fresh noodles and vermicelli")),
            signatureDishL(of("Lẩu Thái chua cay đậm đà", "Authentic spicy-sour Thai hotpot")),
            signatureDishL(of("Lẩu gà nấm thảo mộc thanh ngọt", "Herbal chicken mushroom hotpot")),
            signatureDishL(of("Kem tươi tráng miệng đa vị", "Fresh ice cream dessert assortment"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_HOTPOT")
                .name(of("Buffet lẩu không giới hạn", "Unlimited hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet lẩu người lớn", "Adult hotpot buffet"))
                        .description(of("Bao gồm nồi lẩu 2 ngăn và buffet hơn 36 topping tươi không giới hạn.", "Includes 2-section hotpot and unlimited buffet of 36+ fresh toppings."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "hotpot", "unlimited"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet lẩu trẻ em (cao dưới 1m2)", "Children hotpot buffet (under 1.2m)"))
                        .description(of("Suất buffet lẩu dành cho trẻ em cao dưới 1m2.", "Hotpot buffet for children under 1.2m tall."))
                        .priceFrom(new BigDecimal("99000"))
                        .priceTo(new BigDecimal("129000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "kids"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe công cộng gần nhà hàng", "Public parking near the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cổng và khu vực ngõ 75", "In front of the entrance and alley 75"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy và ô tô đều miễn phí. Bảo vệ trực 24/24.", "Free parking for both motorbike and car. 24/7 security."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "FREE_MOTORBIKE_PARKING",
            "FREE_CAR_PARKING",
            "VISA_MASTER",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE_ALLOWED",
            "GROUP_BOOKING",
            "OUTDOOR_HOTPOT",
            "SELF_SERVE_TOPPING_BAR",
            "INDIVIDUAL_POT",
            "ICE_CREAM_DESSERT"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of(
                "Tổng quan Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu",
                "Overview: Vica Hotpot Buffet - Ho Tung Mau")),
            paragraph(ContentSection.STORY,
                of("Vica Hotpot Hồ Tùng Mậu là điểm đến buffet lẩu hấp dẫn tọa lạc tại Số 1, Ngõ 75 Hồ Tùng Mậu, Quận Cầu Giấy, Hà Nội. " +
                   "Nhà hàng nhanh chóng trở thành lựa chọn yêu thích của giới văn phòng và giới trẻ Hà Nội nhờ mô hình buffet lẩu độc đáo: " +
                   "nồi lẩu 2 ngăn kết hợp Lẩu Thái chua cay và Lẩu Gà Nấm Thảo Mộc thanh ngọt, cùng buffet hơn 36 loại topping tươi ngon không giới hạn.",
                   "Vica Hotpot Ho Tung Mau is an attractive hotpot buffet destination located at No. 1, Alley 75 Ho Tung Mau, Cau Giay District, Hanoi. " +
                   "The restaurant quickly became a favorite among office workers and young diners in Hanoi thanks to its unique hotpot buffet concept: " +
                   "a 2-section pot combining spicy-sour Thai hotpot and sweet herbal chicken mushroom hotpot, with unlimited buffet of 36+ fresh toppings.")),

            heading(ContentSection.STORY, of(
                "Không gian & sức chứa",
                "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian rộng lớn với sức chứa lên đến 300 khách, thiết kế theo phong cách hiện đại, trẻ trung đầy cá tính " +
                   "nhưng không kém phần sang trọng. Toàn bộ khu vực được trang bị hệ thống điều hòa nhiệt độ, ánh sáng tinh tế và âm nhạc phù hợp " +
                   "tạo nên bầu không khí sôi động, phù hợp cho các buổi tiệc nhóm và liên hoan.",
                   "The restaurant boasts a spacious area with a capacity of up to 300 guests, designed in a modern, trendy, and characterful style " +
                   "that is no less elegant. The entire area is equipped with air conditioning, tasteful lighting, and appropriate music, " +
                   "creating a vibrant atmosphere perfect for group parties and celebrations.")),

            heading(ContentSection.STORY, of(
                "Thực đơn & điểm nhấn buffet lẩu",
                "Menu & hotpot buffet highlights")),
            paragraph(ContentSection.STORY,
                of("Điểm đặc trưng nhất của Vica Hotpot là nồi lẩu 2 ngăn phục vụ 2 vị lẩu đặc trưng trong cùng 1 nồi: " +
                   "Lẩu Thái chua cay đậm đà với nước dùng thơm lừng vị sả, chanh và ớt; và Lẩu Gà Nấm Thảo Mộc thanh ngọt tự nhiên. " +
                   "Quầy buffet topping cung cấp hơn 36 loại nguyên liệu tươi ngon bao gồm thịt bò, hải sản (tôm, mực, ngao), " +
                   "nấm đặc sản, rau xanh sạch và các loại mì, bún, thả vào lẩu tùy thích không giới hạn.",
                   "The most distinctive feature of Vica Hotpot is the 2-section pot serving 2 signature hotpot flavors in one pot: " +
                   "bold spicy-sour Thai hotpot with broth fragrant with lemongrass, lime and chili; and naturally sweet herbal chicken mushroom hotpot. " +
                   "The topping buffet counter provides 36+ fresh ingredients including beef, seafood (shrimp, squid, clams), " +
                   "specialty mushrooms, clean fresh vegetables, and various noodles and vermicelli for unlimited dipping.")),

            heading(ContentSection.STORY, of(
                "Phù hợp dịp nào",
                "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá buffet chỉ từ 199.000đ/người cho người lớn, Vica Hotpot là lựa chọn lý tưởng cho mọi dịp: " +
                   "từ bữa ăn gia đình ấm cúng, buổi tụ tập bạn bè cuối tuần, đến tiệc liên hoan công ty và sinh nhật. " +
                   "Không gian rộng thoáng, phù hợp đặt bàn cho cả nhóm nhỏ đến đoàn đông 50 người, " +
                   "đặc biệt thích hợp cho các nhóm văn phòng tìm không gian thư giãn sau giờ làm.",
                   "With buffet prices starting from just 199,000 VND/person for adults, Vica Hotpot is the ideal choice for all occasions: " +
                   "from warm family dinners to weekend friend gatherings, company parties, and birthday celebrations. " +
                   "The spacious area accommodates both small groups and large parties of up to 50 people, " +
                   "especially suited for office groups looking for a relaxing space after work.")),

            heading(ContentSection.STORY, of(
                "Dịch vụ & tiện ích nổi bật",
                "Outstanding services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp đầy đủ các tiện ích hiện đại: hệ thống WiFi tốc độ cao, điều hòa nhiệt độ toàn bộ khu vực, " +
                   "ghế dành cho trẻ em, hỗ trợ đặt tiệc sinh nhật và sự kiện kèm trang trí theo yêu cầu. " +
                   "Thanh toán hỗ trợ cả thẻ Visa/Mastercard và tiền mặt. " +
                   "Nhân viên phục vụ nhiệt tình, chuyên nghiệp, sẵn sàng hỗ trợ thực khách trong suốt bữa ăn.",
                   "The restaurant provides all modern amenities: high-speed WiFi, full air conditioning throughout the area, " +
                   "children's chairs, birthday party and event planning support with custom decorations. " +
                   "Payment accepts both Visa/Mastercard and cash. " +
                   "Enthusiastic, professional staff are ready to assist diners throughout the meal.")),

            heading(ContentSection.STORY, of(
                "Vị trí & di chuyển",
                "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Vica Hotpot tọa lạc tại Số 1, Ngõ 75 Hồ Tùng Mậu, Quận Cầu Giấy - khu vực trung tâm thương mại sầm uất " +
                   "của Hà Nội với nhiều tòa văn phòng và khu chung cư cao cấp. " +
                   "Vị trí dễ tìm, gần các tuyến xe buýt và tuyến đường lớn Hồ Tùng Mậu, " +
                   "bãi đỗ xe miễn phí cho cả ô tô và xe máy, rất thuận tiện cho thực khách di chuyển bằng phương tiện cá nhân.",
                   "Vica Hotpot is located at No. 1, Alley 75 Ho Tung Mau, Cau Giay District — a bustling commercial area " +
                   "of Hanoi with many office buildings and high-end apartment complexes. " +
                   "The location is easy to find, close to bus routes and the main Ho Tung Mau road, " +
                   "with free parking for both cars and motorbikes, very convenient for guests traveling by personal vehicles.")),

            heading(ContentSection.STORY, of(
                "Chính sách đặt bàn & ưu đãi",
                "Booking policy & promotions")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trực tuyến với chính sách giữ chỗ linh hoạt, không cần đặt cọc cho nhóm dưới 20 người. " +
                   "Khách đặt chỗ qua hệ thống PasGo thường xuyên nhận được ưu đãi hấp dẫn theo từng thời điểm. " +
                   "Nhà hàng tính phụ phí khi mang đồ ăn, thức uống từ ngoài vào. " +
                   "Hóa đơn dịch vụ 5% áp dụng cho toàn bộ hóa đơn, không xuất VAT theo yêu cầu riêng.",
                   "The restaurant accepts online reservations with a flexible booking policy; no deposit required for groups under 20 people. " +
                   "Guests booking via the PasGo system regularly receive attractive offers depending on the time. " +
                   "The restaurant charges a corkage fee for outside food and drinks brought in. " +
                   "A 5% service charge applies to all bills; VAT invoice is not issued separately.")),

            heading(ContentSection.STORY, of(
                "Lý do nên chọn Vica Hotpot",
                "Why choose Vica Hotpot")),
            paragraph(ContentSection.STORY,
                of("Vica Hotpot Hồ Tùng Mậu mang đến trải nghiệm buffet lẩu đa dạng, giá cả phải chăng trong không gian hiện đại và sôi động. " +
                   "Nồi lẩu 2 ngăn độc đáo với hơn 36 topping tươi không giới hạn là điểm cộng lớn so với các đối thủ trong khu vực Cầu Giấy. " +
                   "Đây là lựa chọn hàng đầu cho những ai muốn tận hưởng bữa tiệc lẩu no nê, ngon miệng mà không lo tốn kém.",
                   "Vica Hotpot Ho Tung Mau delivers a diverse, affordable hotpot buffet experience in a modern and vibrant setting. " +
                   "The unique 2-section hotpot with 36+ unlimited fresh toppings is a major advantage over competitors in the Cau Giay area. " +
                   "This is the top choice for those who want to enjoy a satisfying, delicious hotpot feast without worrying about the cost.")),

            mapBlock(ContentSection.STORY, new double[]{105.7764, 21.0368})
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
                .feeAmount(new BigDecimal("50000"))
                .currencyCode("VND")
                .note(of("Phụ phí bia mang ngoài: 50.000đ/lon.", "Outside beer corkage fee: 50,000 VND/can."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(20)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 20 khách trở lên.", "Applies to bookings of 20 or more guests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt chỗ qua PasGo, không áp dụng ngày lễ và dịp đặc biệt.", "Promotion for guests booking via PasGo; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(20)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(false)
            .vatPercent(null)
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Nhà hàng chỉ xuất hóa đơn trực tiếp.", "The restaurant issues direct invoices only."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% trên toàn bộ hóa đơn.", "A 5% service charge applies to all bills."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí.", "Outside drinks are allowed with a corkage fee."))
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
                .name(of("Bàn tiêu chuẩn 4 người", "Standard table for 4"))
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
                .note(of("Bàn tiêu chuẩn, phù hợp gia đình và nhóm bạn nhỏ.", "Standard table for families and small friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-grp")
                .name(of("Bàn nhóm 8 người", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(15)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phù hợp nhóm văn phòng và nhóm đông.", "Suitable for office groups and larger parties."))
                .build(),
            TableType.builder()
                .id("tb-vip-10")
                .name(of("Bàn VIP 10 người", "VIP table for 10"))
                .seats(10)
                .minPeople(8)
                .maxPeople(10)
                .totalTables(5)
                .depositPrice(new BigDecimal("700000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Bàn VIP ưu tiên, khu vực riêng biệt hơn, phục vụ ưu tiên.", "VIP priority table in a more secluded area with priority service."))
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
            .wardName(of("P. Mai Dịch", "Mai Dich Ward"))
            .addressLine(of("Số 1, Ngõ 75 Hồ Tùng Mậu, Q. Cầu Giấy, Hà Nội", "No. 1, Alley 75 Ho Tung Mau, Cau Giay District, Hanoi"))
            .location(new double[]{105.7764, 21.0368})
            .name(of("Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu", "Vica Hotpot Buffet - Ho Tung Mau"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu 2 ngăn độc đáo với 36+ topping tươi không giới hạn tại Cầu Giấy, Hà Nội.", "Unique 2-section hotpot buffet with 36+ unlimited fresh toppings in Cau Giay, Hanoi."))
            .description(of("Vica Hotpot Hồ Tùng Mậu là nhà hàng buffet lẩu nổi tiếng tại Quận Cầu Giấy, Hà Nội. " +
                "Với mô hình nồi lẩu 2 ngăn phục vụ đồng thời Lẩu Thái chua cay và Lẩu Gà Nấm Thảo Mộc, " +
                "cùng buffet hơn 36 loại topping tươi ngon không giới hạn, nhà hàng thu hút đông đảo " +
                "giới văn phòng và giới trẻ với không gian hiện đại, giá cả phải chăng từ 199.000đ/người.",
                "Vica Hotpot Ho Tung Mau is a famous hotpot buffet restaurant in Cau Giay District, Hanoi. " +
                "With its unique 2-section hotpot model serving both spicy-sour Thai hotpot and herbal chicken mushroom hotpot simultaneously, " +
                "along with unlimited buffet of 36+ fresh toppings, the restaurant attracts a large crowd of " +
                "office workers and young diners with its modern space and affordable prices starting from 199,000 VND/person."))
            .phone("0482297254")
            .email("info@vicahotpot.vn")
            .website("https://pasgo.vn/nha-hang/vica-hotpot-ho-tung-mau-4940")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("199000"))
            .maxPricePerPerson(new BigDecimal("249000"))
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
