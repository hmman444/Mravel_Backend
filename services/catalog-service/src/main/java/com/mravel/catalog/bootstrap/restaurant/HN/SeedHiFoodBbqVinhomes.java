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
public class SeedHiFoodBbqVinhomes {

    @Bean
    CommandLineRunner seedHiFoodBbqVinhomesRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedHiFoodBbqVinhomes] start");

            RestaurantDoc doc = restaurantHiFoodBbqVinhomes();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedHiFoodBbqVinhomes] done");
        };
    }

    // =====================================================================
    //     HI-FOOD BBQ - VINHOMES SMART CITY (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantHiFoodBbqVinhomes() {
        String slug = "hi-food-bbq-vinhomes-smart-city";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-1-normal-4200953872219.webp",
                of("Không gian ngoài trời Hi-Food BBQ bên Biển Hồ Vinhomes Smart City", "Hi-Food BBQ outdoor space by the lake at Vinhomes Smart City"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-2-normal-4200953972220.webp",
                of("Khu vực BBQ ngoài trời về đêm lung linh ánh đèn", "Outdoor BBQ area lit up at night"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-3-normal-4200954072221.webp",
                of("Bàn nướng than hoa trên sân cỏ xanh mát", "Charcoal grill table on lush green lawn"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-4-normal-4200954172222.webp",
                of("Nhóm bạn thư giãn nướng BBQ ngoài trời", "Friends relaxing with outdoor BBQ"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-5-normal-4200954272223.webp",
                of("Góc chill bên hồ, thảm cỏ xanh và bếp nướng", "Chill corner by the lake with green lawn and grill"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-6-normal-4200954572224.webp",
                of("Ba chỉ heo và bò nướng trên bếp than hoa", "Pork belly and beef sizzling on charcoal grill"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-7-normal-4200954872225.webp",
                of("Hàu tươi nướng mỡ hành thơm phức", "Fresh oysters grilled with fragrant scallion oil"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-8-normal-4200955072226.webp",
                of("Tôm sú nướng muối ớt tươi ngon", "Fresh salt-chili grilled tiger prawns"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-9-normal-4200955372227.webp",
                of("Gia đình sum vầy quây quần bên bếp BBQ", "Family gathering around the BBQ grill"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-10-normal-4200955572228.webp",
                of("Không gian picnic ngoài trời giữa lòng thành phố", "Outdoor picnic atmosphere in the heart of the city"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-11-normal-4200955672229.webp",
                of("Bàn tiệc sinh nhật ngoài trời trang trí đặc biệt", "Outdoor birthday party table with special decorations"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-12-normal-4200955772230.webp",
                of("Thảm cỏ và ánh đèn lung linh tạo không khí chill", "Green lawn and fairy lights creating a chill atmosphere"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-13-normal-4200955872231.webp",
                of("View hồ xanh mát từ khu vực BBQ", "Lake view from the BBQ area"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-14-normal-4200955972232.webp",
                of("Nhân viên phục vụ tận tình hỗ trợ nướng tại bàn", "Staff providing attentive table-side grilling assistance"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-15-normal-4201064772253.webp",
                of("Sườn non tẩm sốt đặc biệt nướng vàng đều", "Special-marinated pork ribs grilled to golden perfection"), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-16-normal-4201065072254.webp",
                of("Bò cuốn nấm kim châm nướng trên than hoa", "Beef rolls with enoki mushroom on charcoal"), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-17-normal-4201065272255.webp",
                of("Mực ống và bạch tuộc nướng sa tế", "Squid and octopus grilled with satay"), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-18-normal-4201065372256.webp",
                of("Team building nhỏ tại Hi-Food BBQ ngoài trời", "Small team building event at Hi-Food BBQ outdoors"), false, 17),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-19-normal-4201065472257.webp",
                of("Toàn cảnh khu BBQ ban ngày với cỏ xanh và hồ nước", "Daytime panorama of BBQ area with green lawn and lake"), false, 18),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-20-normal-4201065572258.webp",
                of("Trẻ em vui chơi bên khu vui chơi ngoài trời", "Children playing at the outdoor play area"), false, 19),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-21-normal-4201065672259.webp",
                of("Bữa tiệc liên hoan công ty dưới ánh đèn LED ngoài trời", "Company party under outdoor LED lights"), false, 20),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hi-food-bbq-22-normal-4201065772260.webp",
                of("Hi-Food BBQ – điểm hẹn lý tưởng bên Biển Hồ Vinhomes", "Hi-Food BBQ – the ideal outdoor destination by Vinhomes lake"), false, 21)
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
                .build(),
            CuisineTag.builder()
                .code("OUTDOOR_BBQ")
                .name(of("BBQ ngoài trời", "Outdoor BBQ"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa (2 ca: trưa + tối, cả 7 ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.MONDAY,    18, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 14, 0),
            openingHour(DayOfWeek.TUESDAY,   18, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 14, 0),
            openingHour(DayOfWeek.WEDNESDAY, 18, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 14, 0),
            openingHour(DayOfWeek.THURSDAY,  18, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.FRIDAY,    18, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 14, 0),
            openingHour(DayOfWeek.SATURDAY,  18, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 14, 0),
            openingHour(DayOfWeek.SUNDAY,    18, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",         of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",        of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",         of("Hẹn hò", "Date")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_PARTY",  of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING",  of("Họp nhóm", "Group meeting")),
            suitable("COMPANY_EVENT",  of("Tiệc công ty", "Company event")),
            suitable("OUTDOOR_DINING", of("Ăn ngoài trời", "Outdoor dining")),
            suitable("TEAM_BUILDING",  of("Team building", "Team building")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("OUTDOOR",  of("Ngoài trời", "Outdoor")),
            ambience("ROMANTIC", of("Lãng mạn bên hồ", "Romantic lakeside")),
            ambience("VIBRANT",  of("Sôi động", "Vibrant")),
            ambience("COZY",     of("Ấm cúng, thư giãn", "Cozy and relaxing"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Ba chỉ heo ướp BBQ", "BBQ-marinated pork belly"),
                of("Ba chỉ heo ướp sốt BBQ đặc trưng, nướng than hoa vàng đều, da giòn thơm.", "Pork belly marinated in signature BBQ sauce, evenly grilled on charcoal until golden and crispy."),
                new BigDecimal("90000")),
            signatureDish(of("Bò cuốn nấm kim châm", "Beef rolls with enoki mushroom"),
                of("Thịt bò chỉ mỏng mềm cuộn nấm kim châm, nướng than thấm vị, chấm tương tiêu chanh.", "Thinly sliced tender beef wrapped around enoki mushroom, charcoal-grilled to perfection, served with pepper-lime dipping sauce."),
                new BigDecimal("110000")),
            signatureDish(of("Sườn non tẩm sốt đặc biệt", "Pork ribs with special marinade"),
                of("Sườn non ướp sốt đặc chế của nhà hàng, nướng than vàng đều, ngọt thơm hấp dẫn.", "Pork ribs marinated in the restaurant's special sauce, evenly charcoal-grilled to a sweet, fragrant finish."),
                new BigDecimal("130000")),
            signatureDish(of("Tôm sú nướng mọi", "Charcoal-grilled tiger prawns"),
                of("Tôm sú tươi to nướng than nguyên con, chấm muối tiêu chanh, ngọt giòn tự nhiên.", "Large fresh tiger prawns grilled whole on charcoal, served with salt-pepper-lime, naturally sweet and crispy."),
                new BigDecimal("150000")),
            signatureDish(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"),
                of("Hàu tươi to nướng chín, chan mỡ hành phi thơm và đậu phộng rang vàng.", "Large fresh oysters grilled and topped with fragrant scallion oil and roasted peanuts."),
                new BigDecimal("35000")),
            signatureDish(of("Mực ống nướng sa tế", "Grilled squid with satay"),
                of("Mực ống tươi nguyên con nướng than, phết sốt sa tế cay nồng, kèm rau sống.", "Fresh whole squid grilled on charcoal, brushed with spicy satay sauce, served with fresh vegetables."),
                new BigDecimal("100000")),
            signatureDish(of("Nầm bò nướng than", "Grilled beef udder"),
                of("Nầm bò non mềm dai, ướp gia vị nhẹ, nướng than hoa đến khi giòn vàng thơm.", "Tender and chewy young beef udder, lightly seasoned and charcoal-grilled until golden and crispy."),
                new BigDecimal("95000")),
            signatureDish(of("Chân gà nướng mật ong", "Honey-glazed grilled chicken feet"),
                of("Chân gà ướp mật ong và gia vị, nướng than giòn da, ngọt thơm đặc trưng.", "Chicken feet marinated in honey and spices, charcoal-grilled until skin is crispy, with a distinctly sweet aroma."),
                new BigDecimal("60000")),
            signatureDish(of("Dồi lợn nướng than hoa", "Charcoal-grilled pork sausage"),
                of("Dồi lợn tự làm nhồi thịt và gia vị, nướng than hoa vàng ruộm, béo ngậy đặc trưng.", "Homemade pork sausage stuffed with seasoned meat, charcoal-grilled to a deep golden colour, rich and flavourful."),
                new BigDecimal("70000")),
            signatureDish(of("Bạch tuộc nướng sốt cay", "Grilled octopus with spicy sauce"),
                of("Bạch tuộc tươi nướng than hoa, phết sốt cay đặc biệt, thơm dai và hấp dẫn.", "Fresh octopus grilled on charcoal, brushed with a special spicy sauce, aromatic and chewy."),
                new BigDecimal("120000"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hi%20food%20bbq/menu%20hi%20food%20bbq.jpg",
                of("Bảng giá Hi-Food BBQ trang 1", "Hi-Food BBQ price menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20hi%20food/menu%20hi%20food%20bbq%202.jpg",
                of("Bảng giá Hi-Food BBQ trang 2", "Hi-Food BBQ price menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20hi%20food/menu%20hi%20food%20bbq%201.jpg",
                of("Bảng giá Hi-Food BBQ trang 3", "Hi-Food BBQ price menu page 3"), false, 2)
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BBQ_MEAT")
                .name(of("Thịt & nội tạng nướng", "Grilled Meat & Offal"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ba chỉ heo ướp BBQ", "BBQ-marinated pork belly"))
                        .description(of("Ba chỉ ướp sốt BBQ đặc trưng, nướng than hoa.", "Pork belly in signature BBQ marinade, charcoal-grilled."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bò cuốn nấm kim châm", "Beef rolls with enoki mushroom"))
                        .description(of("Thịt bò chỉ cuộn nấm kim châm, chấm tương tiêu chanh.", "Sliced beef wrapped with enoki mushroom, with pepper-lime dip."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("110000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "bbq"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Sườn non tẩm sốt đặc biệt", "Pork ribs with special marinade"))
                        .description(of("Sườn non ướp sốt đặc chế, nướng than vàng thơm.", "Pork ribs in the restaurant's special marinade, golden charcoal-grilled."))
                        .priceFrom(new BigDecimal("130000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork", "ribs", "bbq"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BBQ_SEAFOOD")
                .name(of("Hải sản nướng", "Grilled Seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tôm sú nướng mọi", "Charcoal-grilled tiger prawns"))
                        .description(of("Tôm sú tươi nướng than, chấm muối tiêu chanh.", "Fresh tiger prawns on charcoal, served with salt-pepper-lime."))
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
                        .description(of("Mực ống tươi nướng than, sốt sa tế cay thơm.", "Fresh squid charcoal-grilled with spicy satay sauce."))
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
            .carParkingLocation(of("Hầm gửi xe gần Nhà hàng tại khu đô thị Vinhomes Smart City", "Basement parking near the restaurant in Vinhomes Smart City complex"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("10000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe gần Nhà hàng tại khu đô thị Vinhomes Smart City", "Basement parking near the restaurant in Vinhomes Smart City complex"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Xe máy và ô tô gửi tại hầm gửi xe khu đô thị, có bảo vệ trông giữ. Mức phí phụ thuộc đơn vị trông giữ.", "Motorbikes and cars park in the complex basement with security. Fee depends on the parking operator."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "OUTDOOR_TABLE",
            "KIDS_PLAY_AREA",
            "EVENT_DECOR",
            "MC_SERVICE",
            "BIRTHDAY_CAKE",
            "SOUND_LIGHT",
            "CAR_PARKING",
            "MOTORBIKE_PARKING",
            "WIFI",
            "MOMO_ZALOPAY",
            "VISA_MASTER",
            "RAIN_COVER",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "LED_SCREEN"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Tổng quan Hi-Food BBQ - Vinhomes Smart City", "Hi-Food BBQ - Vinhomes Smart City overview")),
            paragraph(ContentSection.OVERVIEW,
                of("Hi-Food BBQ tọa lạc tại Công viên trung tâm biển hồ Vinhomes Smart City, phường Tây Mỗ, quận Nam Từ Liêm, Hà Nội. " +
                "Nhà hàng mang đến trải nghiệm BBQ ngoài trời trọn vẹn giữa không gian xanh mát bên hồ, " +
                "nơi tiếng than hoa nổ lốp đốp, hương thịt nướng thơm lừng hòa cùng làn gió hồ tạo nên bầu không khí chill hiếm có giữa lòng đô thị.",
                "Hi-Food BBQ is located at the central lakeside park of Vinhomes Smart City, Tay Mo Ward, Nam Tu Liem District, Hanoi. " +
                "The restaurant offers a complete outdoor BBQ experience surrounded by green spaces by the lake, " +
                "where the crackle of charcoal, the aroma of grilled meat, and the lake breeze create a rare chill atmosphere in the heart of the city.")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nổi bật của Hi-Food BBQ là mô hình phục vụ trọn gói A-Z: từ bếp nướng, bàn ghế, thảm picnic, " +
                "bát đũa đến setup và dọn dẹp sau tiệc đều được nhà hàng chuẩn bị chu đáo. " +
                "Thực khách chỉ cần mang theo tâm trạng vui vẻ, mọi thứ còn lại đã có Hi-Food lo.",
                "The highlight of Hi-Food BBQ is its all-inclusive A-Z service model: from grill stations, tables and chairs, picnic mats, " +
                "utensils to party setup and post-party cleanup — all handled attentively by the restaurant. " +
                "Guests simply bring their good mood; Hi-Food takes care of everything else.")),
            heading(ContentSection.STORY, of("Câu chuyện Hi-Food BBQ - BBQ ngoài trời trọn gói đầu tiên tại Hà Nội", "Hi-Food BBQ story – Hanoi's first all-inclusive outdoor BBQ")),
            paragraph(ContentSection.STORY,
                of("Hi-Food BBQ ra đời với sứ mệnh biến mỗi buổi tụ họp thành kỷ niệm khó quên. " +
                "Người sáng lập nhận ra rằng người Hà Nội yêu thích không khí nướng BBQ ngoài trời nhưng lại thiếu một địa điểm thực sự " +
                "tiện lợi, đẹp và chuyên nghiệp ngay trong nội thành. Vinhomes Smart City với biển hồ rộng lớn và không gian xanh mát " +
                "chính là nơi lý tưởng để hiện thực hóa ý tưởng đó.",
                "Hi-Food BBQ was born with a mission to turn every gathering into an unforgettable memory. " +
                "The founders recognised that Hanoians love outdoor BBQ but lacked a truly convenient, beautiful, and professional venue within the city. " +
                "Vinhomes Smart City, with its vast lake and green spaces, was the ideal location to bring that vision to life.")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng đầu tư bài bản vào chất lượng nguyên liệu: thịt tươi chọn lọc mỗi ngày từ các trang trại uy tín, " +
                "hải sản sống nhập về buổi sáng, rau củ sạch hữu cơ đảm bảo vệ sinh an toàn thực phẩm. " +
                "Mỗi phần thịt nướng đều được ướp theo công thức riêng của nhà hàng, đảm bảo hương vị đậm đà tự nhiên.",
                "The restaurant invests seriously in ingredient quality: fresh meat selected daily from reputable farms, " +
                "live seafood delivered every morning, and organic clean vegetables ensuring food safety. " +
                "Every portion is marinated according to the restaurant's own recipe, guaranteeing a naturally rich flavour.")),
            heading(ContentSection.STORY, of("Không gian biển hồ - thiên đường chill giữa lòng đô thị", "Lakeside space – a chill paradise in the urban heart")),
            paragraph(ContentSection.STORY,
                of("Vị trí đắc địa ngay bên Biển Hồ Vinhomes Smart City mang lại cho Hi-Food BBQ một lợi thế không đối thủ: " +
                "view hồ xanh mát, bầu trời rộng và ánh hoàng hôn phản chiếu mặt nước tạo nên khung cảnh đẹp đến mê hồn. " +
                "Buổi tối, ánh đèn LED lung linh dọc khu BBQ biến nơi đây thành một 'thành phố ánh sáng' thu nhỏ không kém phần lãng mạn.",
                "The prime location right by Vinhomes Smart City's Bien Ho lake gives Hi-Food BBQ an unrivalled advantage: " +
                "a cool lake view, open sky, and sunset reflected on the water create a mesmerising scene. " +
                "In the evening, the twinkling LED lights along the BBQ area transform it into a miniature 'city of lights', no less romantic.")),
            paragraph(ContentSection.STORY,
                of("Vào mùa hè, tán cây xanh tỏa bóng mát tự nhiên, thảm cỏ rộng rãi tạo cảm giác đang picnic đúng nghĩa ngay trong thành phố. " +
                "Nhà hàng còn trang bị bạt che mưa nắng hiện đại, đảm bảo bữa tiệc diễn ra trọn vẹn trong mọi điều kiện thời tiết.",
                "In summer, the green trees provide natural shade while the wide lawn creates the feel of a genuine city picnic. " +
                "The restaurant is also equipped with modern rain and sun shelters to ensure the party goes ahead seamlessly in any weather.")),
            heading(ContentSection.STORY, of("Thực đơn nướng đa dạng – chinh phục mọi khẩu vị", "Diverse BBQ menu – satisfying every palate")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Hi-Food BBQ xoay quanh triết lý giữ trọn vị tươi ngon tự nhiên qua ngọn lửa than hoa. " +
                "Từ ba chỉ heo ướp BBQ, bò cuốn nấm kim châm, sườn non tẩm sốt đặc biệt đến tôm sú nướng mọi, hàu mỡ hành, mực sa tế - " +
                "tất cả đều được ướp nhẹ nhàng để tôn vinh nguyên liệu gốc, nướng đến khi đạt độ chín hoàn hảo.",
                "Hi-Food BBQ's menu revolves around the philosophy of preserving the natural freshness of ingredients through charcoal fire. " +
                "From BBQ pork belly, beef rolls with enoki mushroom, specially marinated pork ribs to tiger prawns, oysters, and satay squid — " +
                "all are lightly seasoned to honour the original ingredients and grilled to perfect doneness.")),
            paragraph(ContentSection.STORY,
                of("Nước chấm đặc trưng của nhà hàng gồm muối tiêu chanh truyền thống, tương tiêu đen kiểu Nhật và sốt BBQ mật ong đặc chế " +
                "là kết quả của hàng tháng nghiên cứu và thử nghiệm, tạo nên sự khác biệt rõ rệt so với các quán nướng thông thường.",
                "The restaurant's signature dipping sauces — traditional salt-pepper-lime, Japanese-style black pepper sauce, and special honey BBQ sauce — " +
                "are the result of months of research and testing, creating a clear distinction from ordinary BBQ joints.")),
            heading(ContentSection.STORY, of("Dịch vụ đặc biệt – sinh nhật, team building và sự kiện ngoài trời", "Special services – birthdays, team building, and outdoor events")),
            paragraph(ContentSection.STORY,
                of("Hi-Food BBQ là một trong số ít nhà hàng nướng BBQ tại Hà Nội cung cấp dịch vụ tổ chức sinh nhật ngoài trời trọn gói, " +
                "bao gồm trang trí bàn tiệc, bánh sinh nhật, MC dẫn chương trình chuyên nghiệp và khu vui chơi trẻ em. " +
                "Không gian rộng rãi sức chứa lên đến 200 khách phù hợp cho cả nhóm nhỏ lẫn sự kiện quy mô vừa.",
                "Hi-Food BBQ is one of the few BBQ restaurants in Hanoi offering all-inclusive outdoor birthday party services, " +
                "including table decoration, birthday cake, professional MC, and a children's play area. " +
                "The spacious venue with a capacity of up to 200 guests suits both small groups and medium-sized events.")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cũng thường xuyên đón tiếp các nhóm team building nhỏ của các công ty trong khu đô thị Vinhomes, " +
                "mang đến không gian thoải mái để đồng nghiệp gắn kết với nhau bên bếp lửa ngoài trời. " +
                "Đặt bàn qua hệ thống để nhận ưu đãi và đảm bảo có chỗ ngồi đẹp bên hồ vào các khung giờ cao điểm cuối tuần.",
                "The restaurant also regularly hosts small team-building groups from companies within the Vinhomes complex, " +
                "providing a comfortable space for colleagues to bond around the outdoor grill. " +
                "Book via the system to receive promotions and secure prime lakeside seating during peak weekend hours.")),
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
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc yêu cầu trang trí đặc biệt. Liên hệ nhà hàng để biết mức phí cụ thể.", "Applies to bookings of 8 or more guests or special decoration requests. Contact the restaurant for specific deposit amounts."))
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
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng không xuất hóa đơn VAT hoặc hóa đơn trực tiếp.", "The restaurant does not issue VAT invoices or direct invoices."))
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
                .id("tb-4-lawn")
                .name(of("Bàn 4 trên sân cỏ", "Lawn table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn ngoài trời trên thảm cỏ view hồ, phù hợp gia đình và nhóm bạn nhỏ.", "Outdoor lawn table with lake view, suitable for families and small friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-lake")
                .name(of("Bàn 8 view hồ", "Lake-view table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn view trực tiếp ra Biển Hồ, ưu tiên phục vụ, phù hợp nhóm bạn và gia đình.", "Large table with direct lake view, priority service, suitable for friend groups and families."))
                .build(),
            TableType.builder()
                .id("tb-20-event")
                .name(of("Khu vực sự kiện 20+ khách", "Event area for 20+ guests"))
                .seats(20)
                .minPeople(10)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Khu vực riêng ngoài trời cho sự kiện, sinh nhật, team building. Bao gồm dịch vụ trang trí và MC.", "Dedicated outdoor area for events, birthdays, and team building. Includes decoration service and MC."))
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
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Nam Từ Liêm", "Nam Tu Liem District"))
            .wardName(of("P. Tây Mỗ", "Tay Mo Ward"))
            .addressLine(of("Công viên trung tâm biển hồ Vinhomes Smart City, P. Tây Mỗ, Q. Nam Từ Liêm, Hà Nội", "Central lakeside park, Vinhomes Smart City, Tay Mo Ward, Nam Tu Liem District, Hanoi"))
            .location(new double[]{105.7469, 21.0098})
            .name(of("Hi-Food BBQ - Vinhomes Smart City", "Hi-Food BBQ - Vinhomes Smart City"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng BBQ ngoài trời trọn gói bên Biển Hồ Vinhomes Smart City – điểm chill lý tưởng giữa lòng Hà Nội.", "All-inclusive outdoor BBQ restaurant by Vinhomes Smart City lake – the ideal chill spot in the heart of Hanoi."))
            .description(of(
                "Hi-Food BBQ - Vinhomes Smart City là nhà hàng nướng BBQ ngoài trời hiếm hoi tại Hà Nội với mô hình phục vụ trọn gói A-Z. " +
                "Tọa lạc bên Công viên trung tâm biển hồ Vinhomes Smart City, phường Tây Mỗ, quận Nam Từ Liêm, nhà hàng mang đến " +
                "trải nghiệm BBQ đích thực giữa không gian xanh mát bên hồ – từ thịt tươi, hải sản sống đến setup tiệc sinh nhật, " +
                "team building và sự kiện ngoài trời. Mức giá 150.000–250.000đ/người, phù hợp cho gia đình, bạn bè, cặp đôi và công ty.",
                "Hi-Food BBQ - Vinhomes Smart City is one of Hanoi's rare outdoor BBQ restaurants with a full A-Z all-inclusive service model. " +
                "Located at the central lakeside park of Vinhomes Smart City, Tay Mo Ward, Nam Tu Liem District, the restaurant offers an authentic " +
                "BBQ experience surrounded by lush greenery by the lake – from fresh meat and live seafood to birthday party setups, " +
                "team building and outdoor events. Priced at 150,000–250,000 VND per person, suitable for families, friends, couples and companies."))
            .phone("0931006005")
            .email(null)
            .website("https://pasgo.vn/nha-hang/hi-food-bbq-vinhomes-smart-city-4481")
            .facebookPage(null)
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("250000"))
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
