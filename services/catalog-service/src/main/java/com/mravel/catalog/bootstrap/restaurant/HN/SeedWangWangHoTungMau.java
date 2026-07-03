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
public class SeedWangWangHoTungMau {

    @Bean
    CommandLineRunner seedWangWangHoTungMauRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedWangWangHoTungMau] start");

            RestaurantDoc doc = restaurantWangWangHoTungMau();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedWangWangHoTungMau] done");
        };
    }

    // =====================================================================
    //         NHÀ HÀNG WANG WANG - HỒ TÙNG MẬU (HÀ NỘI)
    //         Buffet lẩu nướng Hàn Quốc - 30 Hồ Tùng Mậu, Q. Cầu Giấy
    // =====================================================================

    private static RestaurantDoc restaurantWangWangHoTungMau() {
        String slug = "nha-hang-wang-wang-ho-tung-mau";

        // ---------- Gallery ảnh không gian ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-1-normal-2169549252093.webp",
                of("Không gian nhà hàng Wang Wang - Hồ Tùng Mậu", "Wang Wang Restaurant - Ho Tung Mau space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-2-normal-2169549352094.webp",
                of("Khu vực nướng tại bàn Wang Wang", "Wang Wang table-side grilling area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-3-normal-2169549452095.webp",
                of("Không gian nội thất hiện đại Wang Wang", "Wang Wang modern interior"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-4-normal-2169549652096.webp",
                of("Bàn ăn buffet nướng lẩu Wang Wang", "Wang Wang BBQ hotpot buffet table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/chuoi-wang-wang-1-normal-2229185952097.webp",
                of("Chuỗi nhà hàng Wang Wang Hà Nội", "Wang Wang restaurant chain Hanoi"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/chuoi-wang-wang-2-normal-2229186052098.webp",
                of("Khu vực ăn uống rộng rãi Wang Wang", "Wang Wang spacious dining area"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-7-normal-2169549952099.webp",
                of("Bếp nướng tại bàn chuẩn Hàn Quốc", "Korean-style tabletop grill"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-8-normal-2169550052100.webp",
                of("Không gian tươi sáng Wang Wang", "Bright and cheerful Wang Wang ambiance"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-9-normal-2169550152101.webp",
                of("Khu vực bàn ghế gỗ thoải mái", "Comfortable wooden table seating"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-10-normal-2169550252102.webp",
                of("Hương vị đặc trưng xứ Kim Chi tại Wang Wang", "Authentic Korean flavor at Wang Wang"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-11-normal-2169550352103.webp",
                of("Không gian ăn nhóm đông tại Wang Wang", "Group dining space at Wang Wang"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-wang-wang-ho-tung-mau-12-normal-2169550452104.webp",
                of("Trang trí nhà hàng trẻ trung Wang Wang", "Youthful and vibrant restaurant decor"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/nguyenhuong/wangwanghtm/nha-hang-wang-wang-ho-tung-mau-1.jpg",
                of("Bữa tiệc nướng lẩu tại Wang Wang", "BBQ hotpot feast at Wang Wang"), false, 12)
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-1.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 1", "Wang Wang buffet menu 2026 page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-2.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 2", "Wang Wang buffet menu 2026 page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-3.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 3", "Wang Wang buffet menu 2026 page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-4.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 4", "Wang Wang buffet menu 2026 page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-5.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 5", "Wang Wang buffet menu 2026 page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/Wangwang/wangwang-menu-2026-6.jpg",
                of("Bảng giá buffet Wang Wang 2026 trang 6", "Wang Wang buffet menu 2026 page 6"), false, 5)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN_BBQ")
                .name(of("Nướng Hàn Quốc", "Korean BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_HOTPOT")
                .name(of("Lẩu Hàn Quốc", "Korean Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN")
                .name(of("Hàn Quốc", "Korean"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 23:59 tất cả các ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business entertainment")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("YOUTH_GROUP",   of("Nhóm bạn trẻ", "Young group outing"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động, trẻ trung", "Vibrant and youthful")),
            ambience("MODERN",   of("Hiện đại", "Modern")),
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("CASUAL",   of("Thân thiện, thoải mái", "Casual and friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(30)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Nạc vai bò Mỹ nướng", "US beef shoulder grilled"),
                of("Thịt bò Mỹ mềm mọng, thấm gia vị Hàn Quốc, nướng vàng thơm tại bàn.", "Tender US beef marinated in Korean spices, grilled golden at the table."),
                new BigDecimal("99000")),
            signatureDish(of("Lõi vai bò Úc nướng", "Australian beef chuck roll grilled"),
                of("Thịt bò Úc có vân mỡ đẹp, hương vị đậm đà, phù hợp nướng chín vừa.", "Australian beef with beautiful marbling, rich flavor, best grilled medium."),
                new BigDecimal("99000")),
            signatureDish(of("Dẻ sườn bò Mỹ rút xương", "US boneless short rib"),
                of("Miếng sườn bò dày, đậm thịt, tan chảy trong miệng khi nướng vừa tới.", "Thick, meaty short rib that melts in the mouth when grilled to perfection."),
                new BigDecimal("99000")),
            signatureDish(of("Ba chỉ bò Mỹ nướng", "US beef belly grilled"),
                of("Lớp mỡ và thịt xen kẽ chuẩn vị, nướng giòn rặt đặc trưng phong cách Hàn.", "Alternating fat and meat layers, grilled crispy in true Korean style."),
                new BigDecimal("99000")),
            signatureDish(of("Thịt heo đen nướng", "Black pork grilled"),
                of("Heo đen đặc sản, ít mỡ, thơm ngon khi nướng trên than hoa.", "Premium black pork, low fat, fragrant and delicious when grilled on charcoal."),
                new BigDecimal("99000")),
            signatureDish(of("Tôm sú nướng", "Grilled tiger prawns"),
                of("Tôm sú tươi ướp gia vị, nướng chín đều, thịt ngọt chắc.", "Fresh tiger prawns marinated and evenly grilled, sweet and firm flesh."),
                new BigDecimal("99000")),
            signatureDish(of("Mực trứng nướng", "Grilled egg cuttlefish"),
                of("Mực trứng béo ngậy, nướng vừa lửa cho da giòn, ruột mềm.", "Fatty egg cuttlefish, grilled over moderate heat for crispy skin and tender inside."),
                new BigDecimal("99000")),
            signatureDish(of("Bạch tuộc nướng tương sa tế", "Grilled octopus with satay"),
                of("Bạch tuộc tươi ướp sa tế đặc trưng Hàn Quốc, thơm cay hấp dẫn.", "Fresh octopus marinated in Korean-style satay, fragrant and spicy."),
                new BigDecimal("99000")),
            signatureDish(of("Lẩu kim chi truyền thống", "Traditional kimchi hotpot"),
                of("Nồi lẩu kim chi đậm đà chua cay, dùng kèm mì udon và rau xanh tươi.", "Rich and spicy kimchi hotpot served with udon noodles and fresh vegetables."),
                new BigDecimal("99000")),
            signatureDish(of("Set nướng tổng hợp Wang Wang", "Wang Wang mixed BBQ set"),
                of("Combo nướng gồm nhiều loại thịt bò, heo, hải sản, đủ cho 2-3 người thưởng thức.", "BBQ combo featuring beef, pork and seafood varieties, enough for 2-3 diners."),
                new BigDecimal("189000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_BBQ")
                .name(of("Buffet Nướng Lẩu Hàn Quốc", "Korean BBQ & Hotpot Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet nướng lẩu người lớn", "Adult BBQ hotpot buffet"))
                        .description(of(
                            "Buffet không giới hạn thịt bò Mỹ, bò Úc, hải sản, rau và các loại lẩu chuẩn Hàn Quốc.",
                            "Unlimited US & Australian beef, seafood, vegetables and authentic Korean hotpot buffet."))
                        .priceFrom(new BigDecimal("99000"))
                        .priceTo(new BigDecimal("189000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet trẻ em (dưới 1m2)", "Children buffet (under 1.2m)"))
                        .description(of(
                            "Phần buffet dành cho trẻ em cao dưới 1m2, được phục vụ tương đương người lớn.",
                            "Buffet for children under 1.2m tall, served with the same menu as adults."))
                        .priceFrom(new BigDecimal("50000"))
                        .priceTo(new BigDecimal("99000"))
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
            .carParkingLocation(of("Khu vực trước nhà hàng hoặc bãi đỗ gần đó", "In front of restaurant or nearby parking area"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi gửi xe máy trước nhà hàng", "Motorbike parking in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi miễn phí ngay trước cửa. Ô tô có thể gửi tại bãi đỗ xung quanh khu vực Hồ Tùng Mậu.",
                "Free motorbike parking right outside. Cars can park at nearby lots around Ho Tung Mau area."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "FREE_MOTORBIKE_PARKING",
            "PAID_CAR_PARKING",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "BBQ_VENTILATION",
            "PRIVATE_CHARCOAL_GRILL",
            "HOTPOT_INDUCTION",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "BIRTHDAY_CAKE_ALLOWED",
            "OUTDOOR_SEATING_NEARBY",
            "EVENT_DECOR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Tổng quan Nhà hàng Wang Wang - Hồ Tùng Mậu",
                    "Overview of Wang Wang Restaurant - Ho Tung Mau")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Wang Wang - Hồ Tùng Mậu tọa lạc tại số 30 Hồ Tùng Mậu, Phường Mai Dịch, Quận Cầu Giấy, Hà Nội — " +
                    "một địa chỉ ẩm thực Hàn Quốc nổi bật trong khu vực với mô hình buffet lẩu nướng chuẩn vị xứ Kim Chi. " +
                    "Nhà hàng phù hợp với mọi lứa tuổi, từ gia đình, bạn bè, đến các nhóm công ty muốn thưởng thức bữa ăn ngon ở mức giá hợp lý.",
                    "Wang Wang Restaurant at 30 Ho Tung Mau, Mai Dich Ward, Cau Giay District, Hanoi is a prominent Korean dining destination " +
                    "in the area, offering authentic Korean BBQ and hotpot buffet. The restaurant caters to all ages, " +
                    "from families and friends to corporate groups seeking a satisfying meal at a reasonable price.")),
            paragraph(ContentSection.STORY,
                of("Với mức giá buffet từ 99.000 đến 189.000 đồng/người, Wang Wang mang đến trải nghiệm ẩm thực Hàn Quốc phong phú " +
                    "mà không cần lo ngại về chi phí. Đây là lý do nhà hàng luôn đông khách, đặc biệt vào buổi tối và cuối tuần, " +
                    "khi thực khách muốn tìm một không gian ăn uống vui vẻ và sôi động.",
                    "With buffet prices ranging from 99,000 to 189,000 VND per person, Wang Wang delivers a rich Korean dining experience " +
                    "without breaking the bank. This is why the restaurant is always packed, especially in the evenings and on weekends, " +
                    "when diners seek a lively and enjoyable dining atmosphere.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                    "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng có sức chứa 120 khách với thiết kế nội thất hiện đại và trẻ trung. Thay vì những đường nét truyền thống " +
                    "với gam màu trầm ấm, Wang Wang chọn phong cách tươi sáng với bàn ghế gỗ, tường sơn màu tươi và nền gạch bắt mắt — " +
                    "tạo nên bầu không khí thoải mái, thân thiện và đầy năng lượng cho mọi bữa ăn.",
                    "The restaurant accommodates 120 guests with a modern and youthful interior design. Rather than traditional decor " +
                    "with muted tones, Wang Wang opts for a bright style featuring wooden furniture, vibrant wall colors and eye-catching " +
                    "tile flooring — creating a comfortable, friendly and energetic atmosphere for every meal.")),
            paragraph(ContentSection.STORY,
                of("Không gian được bố trí hợp lý với nhiều bàn nhóm lớn, phù hợp cho các đoàn từ 4 đến 20 người. " +
                    "Hệ thống hút khói hiện đại tại mỗi bàn nướng giúp không gian luôn thông thoáng, không bị ám mùi — " +
                    "một điểm cộng lớn so với nhiều nhà hàng nướng truyền thống.",
                    "The space is well-arranged with many large group tables, suitable for parties of 4 to 20 people. " +
                    "A modern ventilation system at each grill table keeps the air fresh and smoke-free — " +
                    "a major advantage over many traditional BBQ restaurants.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc",
                    "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn buffet Wang Wang phong phú với nguyên liệu chất lượng cao: Nạc vai bò Mỹ, Lõi vai bò Úc, " +
                    "Dẻ sườn bò Mỹ rút xương, Ba chỉ bò Mỹ, Thịt heo đen đặc sản, cùng hải sản tươi như Tôm sú, Mực trứng, Bạch tuộc. " +
                    "Bên cạnh đó, thực khách có thể thưởng thức các loại lẩu Hàn Quốc đậm đà như lẩu kim chi, lẩu nấm Hàn Quốc.",
                    "Wang Wang's buffet menu features premium ingredients: US beef shoulder, Australian beef chuck roll, " +
                    "US boneless short rib, US beef belly, specialty black pork, plus fresh seafood like tiger prawns, egg cuttlefish " +
                    "and octopus. Guests can also enjoy rich Korean hotpots such as kimchi hotpot and Korean mushroom hotpot.")),
            paragraph(ContentSection.STORY,
                of("Ngoài thịt và hải sản, thực đơn còn có các loại rau tươi, nấm, đậu phụ và bún/mì đa dạng. " +
                    "Nước chấm tự pha theo phong cách Hàn Quốc cùng các loại sốt ướp nguyên bản giúp từng món ăn " +
                    "đạt đỉnh hương vị đặc trưng xứ Kim Chi.",
                    "Beyond meat and seafood, the menu also offers fresh vegetables, mushrooms, tofu and a variety of noodles. " +
                    "Self-mixed Korean-style dipping sauces and authentic marinades help every dish achieve " +
                    "the peak of authentic Korean flavor.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                    "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá bình dân từ 99.000đ/người và không gian sức chứa 120 khách, Wang Wang Hồ Tùng Mậu phù hợp " +
                    "với nhiều dịp: từ bữa cơm gia đình cuối tuần, tụ tập nhóm bạn thân, sinh nhật nhóm nhỏ, " +
                    "đến liên hoan văn phòng và gặp mặt đối tác kinh doanh.",
                    "With affordable pricing from 99,000 VND/person and a capacity of 120 guests, Wang Wang Ho Tung Mau suits " +
                    "many occasions: weekend family dinners, friend group hangouts, small birthday celebrations, " +
                    "office parties and business partner gatherings.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích",
                    "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị hệ thống bếp nướng than hoa kết hợp bếp từ cảm ứng hiện đại, wifi miễn phí, " +
                    "điều hòa trung tâm và ghế trẻ em. Đội ngũ phục vụ nhiệt tình, sẵn sàng hỗ trợ trang trí sinh nhật và " +
                    "các yêu cầu đặc biệt theo nhóm.",
                    "The restaurant is equipped with charcoal grills and modern induction hotpots, free wifi, " +
                    "central air conditioning and children's high chairs. The enthusiastic service team is ready " +
                    "to assist with birthday decorations and special group requests.")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng xuất hóa đơn VAT theo yêu cầu và chấp nhận thanh toán qua thẻ Visa/Mastercard, " +
                    "tiện lợi cho các nhóm khách công ty. Gửi xe máy miễn phí ngay trước cửa.",
                    "The restaurant issues VAT invoices on request and accepts Visa/Mastercard payments, " +
                    "convenient for corporate groups. Free motorbike parking right outside the entrance.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                    "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tọa lạc tại số 30 Hồ Tùng Mậu, Phường Mai Dịch, Quận Cầu Giấy — " +
                    "nằm gần trục đường lớn Xuân Thủy - Cầu Giấy, dễ dàng di chuyển từ Mỹ Đình, " +
                    "Dịch Vọng, Trung Hòa và các khu dân cư lân cận. Cách ngã tư Hồ Tùng Mậu - Xuân Thủy khoảng 500 mét.",
                    "The restaurant is located at 30 Ho Tung Mau, Mai Dich Ward, Cau Giay District — " +
                    "near the major Xuan Thuy - Cau Giay corridor, easily accessible from My Dinh, Dich Vong, " +
                    "Trung Hoa and surrounding residential areas. About 500 meters from the Ho Tung Mau - Xuan Thuy intersection.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn",
                    "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Wang Wang nhận đặt bàn trước, có chính sách đặt cọc áp dụng cho nhóm đông từ 10 người trở lên. " +
                    "Đặt bàn qua hệ thống để nhận ưu đãi giảm giá và đảm bảo có chỗ vào giờ cao điểm. " +
                    "Nhà hàng không áp dụng ưu đãi vào các ngày lễ lớn.",
                    "Wang Wang accepts advance reservations with a deposit policy for large groups of 10 or more. " +
                    "Book through the system to receive discount promotions and secure your table during peak hours. " +
                    "Promotions do not apply on major public holidays.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,   of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14,  of("Không áp dụng ưu đãi ngày Valentine 14/2", "Promotion not applicable on Valentine's Day Feb 14")),
            blackoutGregorian(4, 30,  of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,   of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,   of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài vào: 150.000đ/chai.", "Wine corkage fee: 150,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang ngoài vào: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng đặt cọc cho nhóm từ 10 khách trở lên hoặc có yêu cầu đặc biệt.",
                "Deposit required for groups of 10 or more guests or special requests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi giảm giá khi đặt bàn qua hệ thống, không áp dụng ngày lễ lớn.",
                "Discount promotions when booking through the system, not applicable on major holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo từng thời điểm, liên hệ nhà hàng để biết thêm.",
                "Promotion details vary by period; contact the restaurant for more information."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu của khách.", "VAT invoice available upon request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho đoàn đặt tiệc từ 15 người trở lên.",
                "A 5% service charge applies to banquet bookings of 15 or more guests."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, phụ thu phí mở nút theo loại.",
                "Outside drinks are allowed with a corkage fee depending on the type."))
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

        // ---------- TableTypes ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tt-4")
                .name(of("Bàn 4 người", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn phù hợp cho nhóm nhỏ và gia đình.", "Standard table for small groups and families."))
                .build(),
            TableType.builder()
                .id("tt-8")
                .name(of("Bàn nhóm 8 người", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn phù hợp nhóm bạn và liên hoan nhỏ.", "Large table suitable for friend groups and small parties."))
                .build(),
            TableType.builder()
                .id("tt-20")
                .name(of("Khu đoàn 20 người", "Large party area for 20"))
                .seats(20)
                .minPeople(10)
                .maxPeople(20)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Khu vực dành cho đoàn đông, liên hoan văn phòng và tiệc nhóm.", "Area for large groups, office parties and group celebrations."))
                .build()
        );

        // ---------- BookingConfig ----------
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
            .wardName(of("Phường Mai Dịch", "Mai Dich Ward"))
            .addressLine(of("Số 30 Hồ Tùng Mậu, P. Mai Dịch, Q. Cầu Giấy, Hà Nội",
                "No. 30 Ho Tung Mau, Mai Dich Ward, Cau Giay District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("Nhà hàng Wang Wang - Hồ Tùng Mậu", "Wang Wang Restaurant - Ho Tung Mau"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu nướng Hàn Quốc bao ngon tại Cầu Giấy, giá từ 99K/người.",
                "Affordable Korean BBQ & hotpot buffet in Cau Giay, from 99K/person."))
            .description(of(
                "Nhà hàng Wang Wang - Hồ Tùng Mậu là điểm đến ẩm thực Hàn Quốc được yêu thích tại Quận Cầu Giấy, Hà Nội. " +
                "Với mô hình buffet lẩu nướng không giới hạn, thực đơn phong phú gồm bò Mỹ, bò Úc, hải sản tươi và các loại lẩu kim chi chuẩn vị xứ Kim Chi, " +
                "Wang Wang mang đến trải nghiệm ẩm thực sôi động và đáng nhớ với mức giá chỉ từ 99.000 - 189.000đ/người.",
                "Wang Wang Restaurant - Ho Tung Mau is a beloved Korean dining destination in Cau Giay District, Hanoi. " +
                "With an unlimited Korean BBQ and hotpot buffet model, a rich menu featuring US and Australian beef, fresh seafood " +
                "and authentic kimchi hotpot, Wang Wang delivers a vibrant and memorable dining experience at just 99,000 - 189,000 VND/person."))
            .phone("0931006005")
            .website("https://pasgo.vn/nha-hang/nha-hang-wang-wang-ho-tung-mau-4564")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("99000"))
            .maxPricePerPerson(new BigDecimal("189000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
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
