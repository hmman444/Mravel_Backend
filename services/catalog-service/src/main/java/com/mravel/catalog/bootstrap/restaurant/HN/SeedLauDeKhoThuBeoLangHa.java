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
public class SeedLauDeKhoThuBeoLangHa {

    @Bean
    CommandLineRunner seedLauDeKhoThuBeoLangHaRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauDeKhoThuBeoLangHa] start");

            RestaurantDoc doc = restaurantLauDeKhoThuBeoLangHa();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauDeKhoThuBeoLangHa] done");
        };
    }

    // =====================================================================
    //    NHÀ HÀNG LẨU DÊ KHÔ THU BÉO - LÁNG HẠ (Ba Đình, Hà Nội)
    // =====================================================================

    private static RestaurantDoc restaurantLauDeKhoThuBeoLangHa() {
        String slug = "lau-de-kho-thu-beo-lang-ha";

        // ---------- Gallery ảnh không gian ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-1-normal-1777242248695.webp",
                of("Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ", "Lau De Kho Thu Beo - Lang Ha restaurant"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-2-normal-1777242348696.webp",
                of("Không gian phố cổ Hà Nội", "Hanoi old quarter inspired space"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-3-normal-1777242448697.webp",
                of("Khu vực bàn ngoài trời", "Outdoor dining area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-4-normal-1777242548698.webp",
                of("Bàn ghế gỗ mộc mạc", "Rustic wooden furniture"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-5-normal-1777242648699.webp",
                of("Tranh vẽ phố Hà Nội trên tường", "Hanoi street art on walls"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-6-normal-1777242848700.webp",
                of("Món lẩu dê khô đặc sắc", "Signature dry goat hotpot"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-7-normal-1777242948701.webp",
                of("Dê tái chanh cuốn rau", "Lemon-marinated goat wrapped in vegetables"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-8-normal-1777243048702.webp",
                of("Dê xào lăn thơm lừng", "Fragrant stir-fried goat"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-9-normal-1777243148703.webp",
                of("Không gian ấm cúng buổi tối", "Cozy evening atmosphere"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-10-normal-1777243248704.webp",
                of("Bàn ngoài trời mát mẻ", "Cool outdoor table"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-11-normal-1777243348705.webp",
                of("Khu vực phòng riêng", "Private dining area"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-de-kho-thu-beo-lang-ha-12-normal-1777243448706.webp",
                of("Toàn cảnh nhà hàng Láng Hạ", "Full view of Lang Ha restaurant"), false, 11)
        );

        // ---------- Ảnh menu/bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-1.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-2.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-3.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-4.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 4", "Menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-5.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 5", "Menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-6.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 6", "Menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-7.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 7", "Menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-8.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 8", "Menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/L%E1%BA%A8U%20D%C3%8A%20KH%C3%94%20THU%20B%C3%89O/menu-lau-de-kho-thu-beo-9.jpg",
                of("Menu Lẩu Dê Khô Thu Béo trang 9", "Menu page 9"), false, 8)
        );

        // ---------- Cuisine ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("GOAT_SPECIALTY")
                .name(of("Đặc sản thịt dê", "Goat meat specialty"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt", "Vietnamese cuisine"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE_HOTPOT")
                .name(of("Lẩu Việt Nam", "Vietnamese Hotpot"))
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
            openingHour(DayOfWeek.SATURDAY,  10, 0, 21, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 21, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ họp bạn bè", "Friends gathering")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("COUPLE",        of("Hẹn hò", "Couple date")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm", "Group meet-up")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật nhỏ", "Small birthday party")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special occasion")),
            suitable("OUTDOOR_DINING",of("Ăn ngoài trời", "Outdoor dining"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",    of("Mộc mạc phố cổ Hà Nội", "Rustic Hanoi old-quarter style")),
            ambience("COZY",      of("Ấm cúng, gần gũi", "Cozy and intimate")),
            ambience("VIBRANT",   of("Sôi động, thân tình", "Vibrant and friendly")),
            ambience("ROMANTIC",  of("Lãng mạn, thơ mộng", "Romantic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(90)
            .maxGroupSize(20)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(10)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu dê khô", "Dry goat hotpot")),
            signatureDishL(of("Dê tái chanh", "Lemon-marinated goat")),
            signatureDishL(of("Dê xào lăn", "Stir-fried goat with lemongrass")),
            signatureDishL(of("Dê xông hơi", "Steamed goat")),
            signatureDishL(of("Cà dê chần ngải cứu", "Goat braised with mugwort")),
            signatureDishL(of("Dê hấp xả", "Lemongrass-steamed goat")),
            signatureDishL(of("Dê chao tỏi", "Garlic-fried goat")),
            signatureDishL(of("Dê nướng", "Grilled goat")),
            signatureDishL(of("Dồi dê chiên", "Fried goat sausage")),
            signatureDishL(of("Dê nhúng mẻ", "Goat dipped in fermented rice sauce"))
        );

        // ---------- Menu section ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("DE_TUOI")
                .name(of("Các món dê tươi Ninh Bình", "Fresh Ninh Binh goat dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu dê khô", "Dry goat hotpot"))
                        .description(of("Thịt dê thái quân cờ tẩm 25 loại gia vị thuốc bắc, kết hợp khoai lang, củ cải, cà rốt – hương vị đặc trưng của nhà hàng.",
                            "Goat meat cubed and marinated with 25 types of herbal spices, combined with sweet potato, radish and carrot – the restaurant's signature flavour."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("300000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("goat", "hotpot", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Dê tái chanh", "Lemon-marinated goat"))
                        .description(of("Thịt dê non thái mỏng tái chanh, cuốn cùng rau củ và bánh tráng, chấm tương gừng ớt cay.",
                            "Thinly sliced young goat marinated in lemon juice, wrapped in vegetables and rice paper, dipped in spicy ginger sauce."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("goat", "cold", "wrap"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Dê xào lăn", "Stir-fried goat with lemongrass"))
                        .description(of("Dê tươi xào lăn cùng sả ớt, hành tây và rau thơm, thơm lừng đặc trưng.",
                            "Fresh goat stir-fried with lemongrass, chilli, onion and herbs, fragrant and flavourful."))
                        .priceFrom(new BigDecimal("160000"))
                        .priceTo(new BigDecimal("220000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("goat", "stir-fry"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Tòa nhà bên cạnh nhà hàng", "Building next to the restaurant"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng", "In front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Ô tô gửi tại tòa nhà bên cạnh (phí theo đơn vị trông giữ xe); xe máy miễn phí trước cửa.",
                "Car parking at the adjacent building (fee as per parking operator); free motorbike parking in front."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "SMOKING_AREA",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "KIDS_PLAY_AREA",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "KARAOKE",
            "LED_SCREEN",
            "VISA_MASTER",
            "FREE_MOTORBIKE_PARKING",
            "EVENT_DECOR"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Lẩu Dê Khô Thu Béo – Điểm hẹn của giới sành dê tại Láng Hạ, Ba Đình",
                   "Lau De Kho Thu Beo – A favourite destination for goat enthusiasts at Lang Ha, Ba Dinh")),
            paragraph(ContentSection.STORY,
                of("Nằm tại Số 02, Ngõ 36, Phố Láng Hạ, Phường Thành Công, Quận Ba Đình, Hà Nội, " +
                   "Nhà hàng Lẩu Dê Khô Thu Béo là chi nhánh thứ hai của chuỗi nhà hàng chuyên về dê nổi tiếng Hà Nội. " +
                   "Được thiết kế theo phong cách phố cổ Hà Nội mộc mạc, giản dị, nhà hàng thu hút đông đảo thực khách " +
                   "yêu thích các món ăn chế biến từ dê tươi tuyển chọn từ Ninh Bình.",
                   "Located at No. 02, Alley 36, Lang Ha Street, Thanh Cong Ward, Ba Dinh District, Hanoi, " +
                   "Lau De Kho Thu Beo restaurant is the second branch of Hanoi's renowned goat specialty chain. " +
                   "Designed in the rustic Hanoi old-quarter style, the restaurant attracts a loyal following of diners " +
                   "who appreciate dishes made from fresh goat sourced from Ninh Binh province.")),
            heading(ContentSection.STORY,
                of("Không gian phố cổ Hà Nội – Thân tình và gần gũi",
                   "Hanoi old-quarter ambience – Warm and intimate")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng mang đậm hơi thở của những con phố cổ Hà Nội. " +
                   "Bàn ghế gỗ mộc mạc, những bức tranh vẽ lại hình ảnh phố phường và con người Hà Nội trên tường, " +
                   "cùng vài bức ảnh làm điểm nhấn – giản dị nhưng khiến mọi cuộc gặp gỡ trở nên thân tình, ấm áp. " +
                   "Sức chứa khoảng 90 khách với khu vực bàn ngoài trời mát mẻ và phòng riêng yên tĩnh cho nhóm nhỏ.",
                   "The restaurant's interior evokes the spirit of Hanoi's old-quarter streets. " +
                   "Rustic wooden furniture, hand-painted murals of Hanoi streetscapes and people, " +
                   "and a few accent photographs – simple yet creating a warm, intimate gathering space. " +
                   "With a capacity of about 90 guests, the venue offers breezy outdoor tables and a private room for smaller groups.")),
            heading(ContentSection.STORY,
                of("Lẩu Dê Khô – Món \"Best Seller\" với 25 loại gia vị thuốc bắc",
                   "Dry Goat Hotpot – The bestseller made with 25 herbal spices")),
            paragraph(ContentSection.STORY,
                of("Món được mọi thực khách nhắc đến đầu tiên khi ghé Lẩu Dê Khô Thu Béo chính là lẩu dê khô. " +
                   "Đây không phải lẩu nước thông thường – nồi lẩu chỉ hơi \"sền sệt\" bởi nước ngọt tự nhiên từ " +
                   "những miếng thịt dê thái quân cờ, được tẩm ướp cùng 25 loại gia vị thuốc bắc tinh chọn, " +
                   "kết hợp vị ngọt tự nhiên của khoai lang, củ cải, cà rốt – mang đến hương vị thơm ngon, lạ miệng và bổ dưỡng.",
                   "The first dish every visitor mentions is the dry goat hotpot. " +
                   "This is not an ordinary broth hotpot – the pot is only slightly thickened by the natural juices from " +
                   "diced goat meat marinated with 25 carefully selected herbal spices, " +
                   "combined with the natural sweetness of sweet potato, radish and carrot – delivering a fragrant, unique and nutritious flavour.")),
            heading(ContentSection.STORY,
                of("Thực đơn đa dạng từ dê tươi Ninh Bình",
                   "A diverse menu of fresh Ninh Binh goat dishes")),
            paragraph(ContentSection.STORY,
                of("Ngoài lẩu dê khô, nhà hàng còn phục vụ phong phú các món dê như dê tái chanh – cuốn cùng rau củ và bánh tráng, " +
                   "chấm tương gừng ớt cay; dê xào lăn thơm lừng; dê xông hơi; cà dê chần ngải cứu; dê hấp xả; " +
                   "dê chao tỏi; dê nướng; dồi dê chiên; dê nhúng mẻ... " +
                   "Tất cả nguyên liệu được nhập từ dê tươi Ninh Bình – vùng dê núi nổi tiếng miền Bắc, " +
                   "đảm bảo độ tươi ngon và hương vị đặc trưng không thể lẫn.",
                   "Beyond the dry hotpot, the menu features a rich selection of goat dishes: lemon-marinated goat wrapped in vegetables and rice paper " +
                   "dipped in spicy ginger sauce; fragrant stir-fried goat with lemongrass; steamed goat; " +
                   "goat braised with mugwort; lemongrass-steamed goat; garlic-fried goat; grilled goat; " +
                   "fried goat sausage; and goat dipped in fermented rice sauce. " +
                   "All ingredients use fresh goat sourced from Ninh Binh province – the North's most celebrated mountain-goat region – " +
                   "guaranteeing freshness and an unmistakeable flavour.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian ấm cúng và thực đơn đặc sắc, Lẩu Dê Khô Thu Béo là lựa chọn lý tưởng cho tụ họp bạn bè, " +
                   "ăn gia đình cuối tuần, hẹn hò, gặp mặt đồng nghiệp hay kỷ niệm nhỏ. " +
                   "Nhà hàng cũng nhận đặt bàn cho các buổi liên hoan văn phòng nhóm vừa (dưới 20 người) với phòng riêng yên tĩnh.",
                   "With a cosy ambience and a distinctive menu, Lau De Kho Thu Beo is the ideal choice for friends gatherings, " +
                   "weekend family meals, couple dates, colleague meet-ups or small celebrations. " +
                   "The restaurant also accommodates mid-sized office parties (under 20 people) in its quiet private room.")),
            heading(ContentSection.STORY,
                of("Dịch vụ và tiện ích",
                   "Services and amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị wifi miễn phí, điều hòa, hệ thống âm thanh ánh sáng, máy chiếu, màn LED và dịch vụ MC cho các sự kiện. " +
                   "Khu vực bàn ngoài trời thoáng mát và phòng riêng phù hợp cho nhóm muốn không gian riêng tư. " +
                   "Nhà hàng chấp nhận thanh toán Visa/Master card, hỗ trợ hóa đơn theo yêu cầu.",
                   "The restaurant provides free wifi, air conditioning, a sound and lighting system, projector, LED screen and MC service for events. " +
                   "Outdoor seating and a private room cater to groups wanting more privacy. " +
                   "Visa/Mastercard payments are accepted and invoices issued on request.")),
            heading(ContentSection.STORY,
                of("Vị trí và chính sách",
                   "Location and policy")),
            paragraph(ContentSection.STORY,
                of("Nằm tại Ngõ 36 Láng Hạ, Quận Ba Đình, nhà hàng dễ tìm và thuận tiện di chuyển từ trung tâm Hà Nội. " +
                   "Xe máy miễn phí đỗ ngay trước cửa; ô tô gửi tại tòa nhà bên cạnh. " +
                   "Nhà hàng không tính phí dịch vụ, không yêu cầu đặt cọc thông thường và cho phép mang thức ăn/đồ uống từ ngoài vào miễn phí. " +
                   "Khuyến nghị đặt bàn trước ít nhất 30 phút để đảm bảo chỗ ngồi.",
                   "Located in Alley 36 Lang Ha, Ba Dinh District, the restaurant is easy to find and conveniently accessible from central Hanoi. " +
                   "Free motorbike parking right outside; car parking in the adjacent building. " +
                   "No service charge, no standard deposit required, and outside food/drinks are allowed free of charge. " +
                   "It is recommended to book at least 30 minutes in advance to secure a seat.")),
            mapBlock(ContentSection.STORY, new double[]{105.8173, 21.0275})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(null)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc. Vui lòng đặt bàn trước ít nhất 30 phút.", "No deposit required. Please book at least 30 minutes in advance."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng vào các ngày lễ.", "Promotions applicable on public holidays."))
            .promotionMaxDiscountPercent(5)
            .promotionNote(of("Chi tiết ưu đãi cập nhật theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(10)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(false)
            .vatPercent(BigDecimal.ZERO)
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng không xuất hóa đơn VAT hoặc hóa đơn tài chính.", "The restaurant does not issue VAT or financial invoices."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không tính phí phục vụ.", "No service charge."))
            .allowOutsideFood(true)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Được mang thức ăn từ ngoài vào, miễn phí.", "Outside food is allowed, free of charge."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, miễn phí.", "Outside drinks are allowed, free of charge."))
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

        // ---------- TableTypes ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-ldkb")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường trong nhà, máy lạnh thoáng mát.", "Standard indoor air-conditioned table."))
                .build(),
            TableType.builder()
                .id("tb-6-outdoor-ldkb")
                .name(of("Bàn ngoài trời 6 người", "Outdoor table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(3)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn ngoài trời thoáng mát, phù hợp nhóm bạn.", "Outdoor table, breezy, suitable for friend groups."))
                .build(),
            TableType.builder()
                .id("pr-10-ldkb")
                .name(of("Phòng riêng 10 khách", "Private room for 10 guests"))
                .seats(10)
                .minPeople(6)
                .maxPeople(10)
                .totalTables(2)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng yên tĩnh, phù hợp nhóm bạn/đồng nghiệp.", "Quiet private room, suitable for friend or colleague groups."))
                .build()
        );

        // ---------- BookingConfig ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(30)
            .graceArrivalMinutes(10)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(20)
            .depositOnly(false)
            .maxTablesPerBooking(3)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Ba Đình", "Ba Dinh District"))
            .wardName(of("P. Thành Công", "Thanh Cong Ward"))
            .addressLine(of("Số 02, Ngõ 36, Phố Láng Hạ, P. Thành Công, Q. Ba Đình, Hà Nội",
                            "No. 02, Alley 36, Lang Ha Street, Thanh Cong Ward, Ba Dinh District, Hanoi"))
            .location(new double[]{105.8173, 21.0275})
            .name(of("Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ", "Lau De Kho Thu Beo Restaurant - Lang Ha"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Chuyên lẩu dê khô & các món dê tươi Ninh Bình – không gian phố cổ Hà Nội mộc mạc, thân tình.",
                                 "Dry goat hotpot & fresh Ninh Binh goat specialties – rustic Hanoi old-quarter atmosphere."))
            .description(of("Nhà hàng Lẩu Dê Khô Thu Béo chi nhánh Láng Hạ là địa chỉ chuyên về dê tươi nhập từ Ninh Bình. " +
                "Nổi tiếng với lẩu dê khô tẩm 25 loại gia vị thuốc bắc độc đáo, cùng thực đơn đa dạng từ dê tái chanh, " +
                "dê xào lăn đến dê hấp xả. Không gian phong cách phố cổ Hà Nội ấm cúng, sức chứa 90 khách, " +
                "có phòng riêng và bàn ngoài trời. Giá 200.000 - 300.000đ/người.",
                "Lau De Kho Thu Beo Lang Ha branch specialises in fresh goat sourced from Ninh Binh province. " +
                "Famous for its dry goat hotpot marinated with 25 unique herbal spices, alongside a diverse menu " +
                "of lemon-marinated goat, stir-fried goat, lemongrass-steamed goat and more. " +
                "Rustic Hanoi old-quarter ambience with 90-guest capacity, private room and outdoor seating. " +
                "200,000 - 300,000 VND/person."))
            .phone("0931006005")
            .website("https://pasgo.vn/nha-hang/lau-de-kho-thu-beo-lang-ha")
            .bookingHotline("0931006005")
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
