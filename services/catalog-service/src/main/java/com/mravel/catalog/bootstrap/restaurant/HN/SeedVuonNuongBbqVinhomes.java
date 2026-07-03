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
public class SeedVuonNuongBbqVinhomes {

    @Bean
    CommandLineRunner seedVuonNuongBbqVinhomesRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedVuonNuongBbqVinhomes] start");

            RestaurantDoc doc = restaurantVuonNuongBbqVinhomes();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedVuonNuongBbqVinhomes] done");
        };
    }

    // =====================================================================
    //     VƯỜN NƯỚNG BBQ - S101 VINHOMES SMART CITY (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantVuonNuongBbqVinhomes() {
        String slug = "vuon-nuong-bbq-vinhomes-smart-city";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-0-png-normal-2590596867773.webp",
                of("Không gian Vườn Nướng BBQ Vinhomes Smart City", "Vuon Nuong BBQ Vinhomes Smart City outdoor space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-1-png-normal-2590597767782.webp",
                of("Khu nướng ngoài trời bên hồ", "Outdoor BBQ area by the lake"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-2-png-normal-2590597867783.webp",
                of("Bàn nướng than hoa giữa vườn", "Charcoal grill tables in the garden"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-3-png-normal-2590597967784.webp",
                of("Không gian xanh mát, thoáng đãng", "Green and airy outdoor ambience"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-4-png-normal-2590598067785.webp",
                of("Khu vực ngồi dưới tán cây", "Seating area under the trees"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-5-png-normal-2590596667771.webp",
                of("Thực khách thưởng thức BBQ ngoài trời", "Guests enjoying outdoor BBQ"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-6-png-normal-2590596767772.webp",
                of("Các món nướng đặc sắc trên bếp than", "Signature grilled dishes on charcoal"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-7-png-normal-2590596567770.webp",
                of("Bò nướng tảng thơm ngon", "Sizzling beef BBQ"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-8-png-normal-2590597267777.webp",
                of("Hải sản tươi nướng tại bàn", "Fresh seafood grilled at the table"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-9-png-normal-2590597367778.webp",
                of("Hàu nướng mỡ hành thơm phức", "Grilled oysters with scallion oil"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-10-png-normal-2590597467779.webp",
                of("Tôm sú nướng muối ớt", "Salt and chili grilled tiger prawns"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-11-png-normal-2590597567780.webp",
                of("Không gian buổi tối lung linh ánh đèn", "Romantic evening ambience with lights"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-12-png-normal-2590597667781.webp",
                of("Góc chụp ảnh view hồ điều hòa", "Lakeside photo spot"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-13-png-normal-2590596967774.webp",
                of("Quầy thực phẩm tươi sống phong phú", "Fresh food counter"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-14-png-normal-2590597167776.webp",
                of("Đội ngũ phục vụ chuyên nghiệp", "Professional service staff"), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-vuon-nuong-bbq-vinhomesmartcity-15-png-normal-2590597067775.webp",
                of("Toàn cảnh Vườn Nướng BBQ về đêm", "Panoramic view of BBQ Garden at night"), false, 15)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_BBQ")
                .name(of("Nướng Việt Nam", "Vietnamese BBQ"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BEEF_BBQ")
                .name(of("Bò nướng", "Beef BBQ"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (2 ca, cả 7 ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.MONDAY,    17, 30, 21, 40),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 14, 0),
            openingHour(DayOfWeek.TUESDAY,   17, 30, 21, 40),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 14, 0),
            openingHour(DayOfWeek.WEDNESDAY, 17, 30, 21, 40),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 14, 0),
            openingHour(DayOfWeek.THURSDAY,  17, 30, 21, 40),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.FRIDAY,    17, 30, 21, 40),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 14, 0),
            openingHour(DayOfWeek.SATURDAY,  17, 30, 21, 40),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.SUNDAY,    17, 30, 21, 40)
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
            suitable("OUTDOOR_DINING",of("Ăn ngoài trời", "Outdoor dining"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("OUTDOOR",   of("Ngoài trời", "Outdoor")),
            ambience("ROMANTIC",  of("Lãng mạn bên hồ", "Romantic lakeside")),
            ambience("COZY",      of("Ấm cúng, thư giãn", "Cozy and relaxing")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tôm sú nướng muối ớt", "Salt and chili grilled tiger prawns"),
                of("Tôm sú tươi nướng than hoa, thấm đậm vị muối ớt đặc trưng.", "Fresh tiger prawns grilled on charcoal with signature salt and chili seasoning."),
                new BigDecimal("120000")),
            signatureDish(of("Nầm nướng sate", "Grilled beef udder with satay"),
                of("Nầm bò non, mềm, nướng than thấm sate cay, giòn ngoài mềm trong.", "Tender beef udder grilled on charcoal with spicy satay, crispy outside, soft inside."),
                new BigDecimal("95000")),
            signatureDish(of("Bò chỉ bò cuốn nấm", "Beef rolls with mushroom"),
                of("Thịt bò chỉ mềm mịn cuộn cùng nấm nướng, chấm tương tiêu chanh.", "Tender beef strips rolled with grilled mushrooms, served with pepper-lime dipping sauce."),
                new BigDecimal("110000")),
            signatureDish(of("Ba chỉ heo Hàn", "Korean-style pork belly"),
                of("Ba chỉ heo ướp kiểu Hàn, nướng than hoa vàng đều, kèm kim chi và tương ớt.", "Korean-marinated pork belly grilled to golden perfection, served with kimchi and chili sauce."),
                new BigDecimal("90000")),
            signatureDish(of("Cánh gà BBQ", "BBQ chicken wings"),
                of("Cánh gà ướp sốt BBQ đặc biệt, nướng chín vàng, da giòn thơm lừng.", "Chicken wings marinated in special BBQ sauce, grilled until golden and crispy."),
                new BigDecimal("80000")),
            signatureDish(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"),
                of("Hàu tươi to nướng chín, chan mỡ hành phi thơm và đậu phộng rang.", "Large fresh oysters grilled with fragrant scallion oil and roasted peanuts."),
                new BigDecimal("35000")),
            signatureDish(of("Mề heo nướng than", "Grilled pork gizzard"),
                of("Mề heo giòn dai nướng than hoa, ăn kèm muối tiêu chanh.", "Crispy and chewy pork gizzard grilled on charcoal, served with salt, pepper, and lime."),
                new BigDecimal("75000")),
            signatureDish(of("Mực ống nướng sa tế", "Grilled squid with satay"),
                of("Mực ống tươi nướng than, sốt sa tế cay thơm, kèm rau sống.", "Fresh squid grilled on charcoal with spicy satay sauce, served with fresh vegetables."),
                new BigDecimal("100000")),
            signatureDish(of("Sườn non nướng mật ong", "Honey-glazed pork ribs"),
                of("Sườn non ướp mật ong và gia vị, nướng than vàng đều, ngọt thơm.", "Pork ribs marinated in honey and spices, evenly grilled on charcoal for a sweet, fragrant finish."),
                new BigDecimal("130000")),
            signatureDish(of("Lòng non nướng tiêu xanh", "Grilled intestines with green pepper"),
                of("Lòng non sạch, nướng than kèm tiêu xanh tươi, giòn thơm đặc trưng.", "Clean pork intestines grilled on charcoal with fresh green pepper, crispy and aromatic."),
                new BigDecimal("70000"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/V%C6%B0%E1%BB%9Dn%20n%C6%B0%E1%BB%9Bng%20BBQ/nha-hang-vuon-nuong-bbq-vinhome-smart-city-1.jpg",
                of("Bảng giá món nướng 1", "Price menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/V%C6%B0%E1%BB%9Dn%20n%C6%B0%E1%BB%9Bng%20BBQ/nha-hang-vuon-nuong-bbq-vinhome-smart-city-2.jpg",
                of("Bảng giá món nướng 2", "Price menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thanhdao/V%C6%B0%E1%BB%9Dn%20n%C6%B0%E1%BB%9Bng%20BBQ/nha-hang-vuon-nuong-bbq-vinhome-smart-city-3.jpg",
                of("Bảng giá món nướng 3", "Price menu page 3"), false, 2)
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BBQ_MEAT")
                .name(of("Thịt & nội tạng nướng", "Grilled Meat & Offal"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò chỉ bò cuốn nấm", "Beef rolls with mushroom"))
                        .description(of("Thịt bò chỉ mềm cuộn nấm nướng, chấm tương tiêu chanh.", "Tender beef strips rolled with grilled mushroom."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("110000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Ba chỉ heo Hàn", "Korean pork belly"))
                        .description(of("Ba chỉ ướp kiểu Hàn nướng than, kèm kim chi.", "Korean-marinated pork belly grilled on charcoal."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork", "bbq", "korean"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Nầm nướng sate", "Grilled beef udder with satay"))
                        .description(of("Nầm bò non nướng than, thấm sate cay thơm.", "Young beef udder grilled on charcoal with spicy satay."))
                        .priceFrom(new BigDecimal("95000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "bbq"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BBQ_SEAFOOD")
                .name(of("Hải sản nướng", "Grilled Seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tôm sú nướng muối ớt", "Salt and chili grilled tiger prawns"))
                        .description(of("Tôm sú tươi nướng than, vị muối ớt đặc trưng.", "Fresh tiger prawns on charcoal with salt and chili."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "prawn", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"))
                        .description(of("Hàu tươi to nướng, chan mỡ hành phi thơm.", "Large fresh oysters grilled with fragrant scallion oil."))
                        .priceFrom(new BigDecimal("35000"))
                        .priceTo(new BigDecimal("35000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "oyster", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Mực ống nướng sa tế", "Grilled squid with satay"))
                        .description(of("Mực ống tươi nướng than, sốt sa tế cay thơm.", "Fresh squid grilled on charcoal with spicy satay."))
                        .priceFrom(new BigDecimal("100000"))
                        .priceTo(new BigDecimal("120000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "squid", "bbq"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm gửi xe Vinhomes Smart City", "Vinhomes Smart City basement parking"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("5000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe Vinhomes Smart City", "Vinhomes Smart City basement parking"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Cả ô tô và xe máy gửi tại hầm gửi xe khu đô thị với mức phí 5.000đ/lượt.", "Both cars and motorbikes park in the residential complex basement at 5,000 VND per visit."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "CAR_PARKING",
            "OUTDOOR_TABLE",
            "EVENT_DECOR",
            "VISA_MASTER",
            "WIFI",
            "KIDS_PLAY_AREA",
            "MC_SERVICE",
            "FOOTBALL_TV",
            "MOTORBIKE_PARKING",
            "PRIVATE_ROOM",
            "KARAOKE",
            "MOMO_ZALOPAY",
            "AIR_CONDITIONING",
            "LED_SCREEN",
            "DIRECT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Tổng quan Vườn Nướng BBQ Vinhomes Smart City", "Vuon Nuong BBQ Vinhomes Smart City overview")),
            paragraph(ContentSection.OVERVIEW,
                of("Vườn Nướng BBQ - S101 Vinhomes Smart City tọa lạc ngay trong khu đô thị Vinhomes Smart City, " +
                "phường Tây Mỗ, quận Nam Từ Liêm, Hà Nội. Nhà hàng sở hữu không gian ngoài trời độc đáo bên hồ điều hòa, " +
                "mang đến trải nghiệm BBQ thư giãn giữa thiên nhiên xanh mát ngay trong lòng thành phố.",
                "Vuon Nuong BBQ - S101 Vinhomes Smart City is located within the Vinhomes Smart City urban area, " +
                "Tay Mo Ward, Nam Tu Liem District, Hanoi. The restaurant features a unique outdoor setting by the lake, " +
                "offering a relaxing BBQ experience surrounded by lush greenery in the heart of the city.")),
            paragraph(ContentSection.OVERVIEW,
                of("Khác với các nhà hàng thông thường, Vườn Nướng BBQ hướng đến cảm giác thoải mái, dễ chịu khi thực khách " +
                "được thưởng thức những món nướng than hoa thơm ngon ngay giữa khoảng xanh của khu đô thị. " +
                "Đây là điểm đến lý tưởng cho những buổi tụ họp gia đình và bạn bè sau giờ làm.",
                "Unlike conventional restaurants, Vuon Nuong BBQ focuses on comfort and ease as guests enjoy aromatic charcoal-grilled " +
                "dishes amidst the greenery of the residential complex. It is an ideal gathering spot for families and friends after work.")),
            heading(ContentSection.STORY, of("Hành trình từ vườn đến bếp", "From garden to grill")),
            paragraph(ContentSection.STORY,
                of("Vườn Nướng BBQ ra đời với ý tưởng đơn giản nhưng đầy chất thơ: mang lại cảm giác nướng trại ngoài trời " +
                "giữa lòng đô thị hiện đại. Người sáng lập muốn cư dân Vinhomes Smart City có một nơi để quên đi bộn bề công việc, " +
                "quây quần bên bếp lửa với những người thân yêu.",
                "Vuon Nuong BBQ was born from a simple yet poetic idea: recreating the outdoor campfire grilling experience " +
                "right in the heart of a modern city. The founder wanted Vinhomes Smart City residents to have a place " +
                "to leave their busy lives behind and gather around the grill with loved ones.")),
            paragraph(ContentSection.STORY,
                of("Nguyên liệu được chọn lọc kỹ lưỡng mỗi buổi sáng từ chợ đầu mối và các trang trại uy tín. " +
                "Hải sản tươi sống, thịt bò nhập khẩu chất lượng cao và rau sạch hữu cơ là những cam kết không thể thiếu " +
                "để đảm bảo mỗi suất nướng đều đạt chuẩn hương vị.",
                "Ingredients are carefully selected every morning from wholesale markets and reputable farms. " +
                "Fresh seafood, high-quality imported beef, and organic clean vegetables are non-negotiable commitments " +
                "to ensure every grilled serving meets the standard of flavor.")),
            heading(ContentSection.STORY, of("Không gian bên hồ - điểm nhấn khó quên", "Lakeside setting - an unforgettable highlight")),
            paragraph(ContentSection.STORY,
                of("Điểm đặc biệt nhất của Vườn Nướng BBQ chính là vị trí đắc địa ngay bên hồ điều hòa của Vinhomes Smart City. " +
                "Khi thời tiết se lạnh, ngồi nướng bên bếp than hoa với gió hồ lồng lộng và ánh đèn phản chiếu mặt nước " +
                "tạo nên một không gian cực kỳ lãng mạn và khó quên.",
                "The most special feature of Vuon Nuong BBQ is its prime location right by Vinhomes Smart City's regulation lake. " +
                "When the weather turns cool, sitting by the charcoal grill with the lake breeze and lights reflecting off the water " +
                "creates an incredibly romantic and unforgettable atmosphere.")),
            paragraph(ContentSection.STORY,
                of("Vào mùa hè, khu vực cây xanh tỏa bóng mát tự nhiên, tạo cảm giác như đang picnic ngay trong thành phố. " +
                "Tiếng than hoa nổ lốp đốp, mùi thịt nướng thoảng trong gió cùng tiếng cười nói rộn rã của thực khách " +
                "tạo nên bản giao hưởng hoàn hảo của một buổi tối Hà Nội.",
                "In summer, the lush trees provide natural shade, creating the feel of a city picnic. " +
                "The crackling of charcoal, the aroma of grilled meat drifting in the breeze, and the cheerful laughter of guests " +
                "compose the perfect symphony of a Hanoi evening.")),
            heading(ContentSection.STORY, of("Thực đơn - Tinh hoa nướng than hoa", "Menu - The art of charcoal grilling")),
            paragraph(ContentSection.STORY,
                of("Thực đơn của Vườn Nướng BBQ xoay quanh triết lý: giữ nguyên vị tươi ngon tự nhiên của nguyên liệu " +
                "qua ngọn lửa than hoa. Từ hải sản tươi sống như tôm sú, hàu, mực ống đến các phần thịt bò, ba chỉ heo Hàn, " +
                "nầm bò - tất cả đều được ướp nhẹ nhàng để tôn vị gốc.",
                "Vuon Nuong BBQ's menu revolves around one philosophy: preserving the natural freshness of ingredients " +
                "through charcoal fire. From fresh seafood like tiger prawns, oysters, and squid to beef cuts, Korean pork belly, " +
                "and beef udder - all are lightly marinated to enhance their original flavor.")),
            paragraph(ContentSection.STORY,
                of("Đặc biệt, nhà hàng tự pha chế các loại nước chấm đặc trưng: muối tiêu chanh, tương tiêu đen kiểu Nhật " +
                "và sốt BBQ mật ong đặc chế. Mỗi loại nước chấm là kết quả của hàng tháng nghiên cứu và thử nghiệm " +
                "để tìm ra công thức hoàn hảo nhất khi kết hợp với từng loại thực phẩm.",
                "Notably, the restaurant crafts its own signature dipping sauces: salt-pepper-lime, Japanese-style black pepper sauce, " +
                "and a special honey BBQ sauce. Each dipping sauce is the result of months of research and testing " +
                "to find the perfect formula for pairing with each type of food.")),
            heading(ContentSection.STORY, of("Dịch vụ & trải nghiệm", "Service & experience")),
            paragraph(ContentSection.STORY,
                of("Đội ngũ nhân viên Vườn Nướng BBQ được đào tạo bài bản về kỹ thuật nướng, luôn sẵn sàng hỗ trợ " +
                "khách hàng từ việc chọn nguyên liệu đến kiểm soát nhiệt độ bếp than. " +
                "Nhân viên sẽ trực tiếp nướng và lật thực phẩm khi cần, đảm bảo món ăn chín đều và đúng chuẩn.",
                "The staff at Vuon Nuong BBQ are thoroughly trained in grilling techniques, always ready to assist guests " +
                "from selecting ingredients to controlling charcoal heat. " +
                "Staff will directly grill and turn the food when needed, ensuring even cooking and perfect results.")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cũng thường xuyên tổ chức các buổi tiệc sinh nhật ngoài trời, sự kiện nhỏ cho cư dân khu đô thị " +
                "với trang trí đặc biệt và MC chuyên nghiệp. Sức chứa lên đến 150 khách cùng 2 phòng riêng " +
                "đảm bảo đáp ứng tốt cho cả nhóm nhỏ lẫn tiệc quy mô vừa.",
                "The restaurant regularly organizes outdoor birthday parties and small events for urban residents " +
                "with special decorations and professional MCs. Capacity of up to 150 guests along with 2 private rooms " +
                "ensures excellent service for both small groups and medium-sized gatherings.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Vườn Nướng BBQ", "Why choose Vuon Nuong BBQ")),
            paragraph(ContentSection.STORY,
                of("Với mức giá bình dân từ 250.000 - 300.000đ/người, Vườn Nướng BBQ mang lại trải nghiệm BBQ cao cấp " +
                "ngay trong khu đô thị hiện đại mà không cần phải ra xa trung tâm. Vị trí S101 Vinhomes Smart City " +
                "thuận tiện cho cư dân toàn khu và dễ dàng tiếp cận từ đường Đại lộ Thăng Long.",
                "With affordable prices of 250,000 - 300,000 VND per person, Vuon Nuong BBQ delivers a premium BBQ experience " +
                "right within the modern urban complex without having to travel far from the center. The S101 Vinhomes Smart City " +
                "location is convenient for all residents and easily accessible from Thang Long Boulevard.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{105.7469, 21.0098})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 25, of("Không áp dụng ưu đãi ngày 25/12", "Promotion not applicable on Dec 25")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc yêu cầu trang trí đặc biệt.", "Applies to bookings of 8 or more guests or special decoration requests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(false)
            .vatPercent(null)
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Nhà hàng xuất hóa đơn trực tiếp theo yêu cầu. Không xuất hóa đơn VAT.", "The restaurant issues direct invoices on request. VAT invoice not available."))
            .serviceChargePercent(null)
            .serviceChargeNotes(null)
            .allowOutsideFood(true)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Khách được mang thức ăn và đồ uống từ ngoài vào. Miễn phí.", "Guests may bring outside food and drinks. Free of charge."))
            .outsideDrinkPolicy(of("Khách được mang đồ uống từ ngoài vào. Miễn phí.", "Guests may bring outside drinks. Free of charge."))
            .outsideDrinkFees(List.of())
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-outdoor")
                .name(of("Bàn 4 ngoài trời", "Outdoor table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn ngoài trời view hồ, phù hợp gia đình và nhóm bạn nhỏ.", "Outdoor table with lake view, suitable for families and small friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-garden")
                .name(of("Bàn 8 trong vườn", "Garden table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn trong vườn cây, thích hợp nhóm gia đình hoặc tiệc nhỏ.", "Large garden table, suitable for family groups or small parties."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng phù hợp tiệc sinh nhật, liên hoan công ty nhỏ.", "Private room suitable for birthday parties and small company events."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(30)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(20)
            .depositOnly(true)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Nam Từ Liêm", "Nam Tu Liem District"))
            .wardName(of("P. Tây Mỗ", "Tay Mo Ward"))
            .addressLine(of("S101 Vinhomes Smartcity, P. Tây Mỗ, Q. Nam Từ Liêm, Hà Nội", "S101 Vinhomes Smart City, Tay Mo Ward, Nam Tu Liem District, Hanoi"))
            .location(new double[]{105.7469, 21.0098})
            .name(of("Vườn Nướng BBQ - Vinhomes Smart City", "Vuon Nuong BBQ - Vinhomes Smart City"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng than hoa ngoài trời bên hồ giữa khu đô thị Vinhomes Smart City, chuyên hải sản & bò nướng.", "Outdoor charcoal BBQ restaurant by the lake within Vinhomes Smart City, specializing in seafood and beef."))
            .description(of(
                "Vườn Nướng BBQ - S101 Vinhomes Smart City là nhà hàng gọi món nướng than hoa đặc sắc tọa lạc ngay trong " +
                "khu đô thị Vinhomes Smart City, phường Tây Mỗ, quận Nam Từ Liêm, Hà Nội. Nhà hàng sở hữu không gian " +
                "ngoài trời thoáng đãng bên hồ điều hòa, chuyên hải sản tươi và bò nướng chất lượng cao. " +
                "Sức chứa 150 khách, mức giá bình dân 250.000 - 300.000đ/người, phù hợp mọi dịp từ gia đình đến tiệc công ty.",
                "Vuon Nuong BBQ - S101 Vinhomes Smart City is a charcoal BBQ a-la-carte restaurant nestled within " +
                "Vinhomes Smart City, Tay Mo Ward, Nam Tu Liem District, Hanoi. The restaurant features a spacious " +
                "outdoor setting by the lake, specializing in fresh seafood and high-quality grilled beef. " +
                "With a capacity of 150 guests and affordable prices of 250,000 - 300,000 VND per person, " +
                "it suits all occasions from family dinners to company events."))
            .phone("0931006005")
            .email(null)
            .website("https://pasgo.vn/nha-hang/vuon-nuong-bbq-s101-vinhomes-smart-city-7770")
            .facebookPage(null)
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("250000"))
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
