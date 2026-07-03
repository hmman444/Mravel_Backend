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
import com.mravel.catalog.model.doc.RestaurantDoc.CapacityInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.RestaurantDoc.CuisineTag;
import com.mravel.catalog.model.doc.RestaurantDoc.Image;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuSection;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuItem;
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
public class SeedQueenPalaceKaraokeDaNang {

    @Bean
    CommandLineRunner seedQueenPalaceKaraokeDaNangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedQueenPalaceKaraokeDaNang] start");

            RestaurantDoc doc = restaurantQueenPalaceKaraokeDaNang();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedQueenPalaceKaraokeDaNang] done");
        };
    }

    // =====================================================================
    //         QUEEN PALACE KARAOKE - ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantQueenPalaceKaraokeDaNang() {
        String slug = "queen-palace-karaoke-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-15-normal-4186489772035.webp",
                of("Không gian Queen Palace Karaoke Đà Nẵng", "Queen Palace Karaoke Da Nang interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-14-normal-4186490472034.webp",
                of("Phòng karaoke sang trọng", "Luxury karaoke room"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-13-normal-4186491672033.webp",
                of("Phòng hát cách âm hiện đại", "Modern soundproof karaoke room"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-6-normal-4186491772026.webp",
                of("Phòng VIP Queen Palace", "Queen Palace VIP room"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-2-normal-4186491872022.webp",
                of("Phòng karaoke nhỏ cho nhóm bạn", "Small karaoke room for friend groups"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-7-normal-4186493372027.webp",
                of("Khu vực sảnh tiếp đón", "Reception and lobby area"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-16-normal-4186495472036.webp",
                of("Phòng Queen lớn cho nhóm đông", "Large Queen room for big groups"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-11-normal-4186495872031.webp",
                of("Nội thất sang trọng phong cách hoàng gia", "Luxurious royal-style interior"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-12-normal-4186495572032.webp",
                of("Hệ thống âm thanh ánh sáng chuyên nghiệp", "Professional sound and lighting system"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-1-normal-4186496172021.webp",
                of("Phòng karaoke tầng 1", "1st floor karaoke room"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-9-normal-4186496572029.webp",
                of("Phòng karaoke tầng 2", "2nd floor karaoke room"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-3-normal-4186116472023.webp",
                of("Phòng karaoke tầng 3", "3rd floor karaoke room"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-4-normal-4186117172024.webp",
                of("Phòng karaoke tầng 4", "4th floor karaoke room"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-5-normal-4186117272025.webp",
                of("Màn hình LED siêu nét", "Ultra-clear LED screen"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-8-normal-4186118772028.webp",
                of("Phòng karaoke sang trọng bậc nhất", "Premier luxury karaoke room"), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/queen-palace-10-normal-4186118972030.webp",
                of("Không gian tổng thể Queen Palace", "Queen Palace overall space"), false, 15)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/13.%20Queen%20Place/1.png",
                of("Bảng giá Queen Palace trang 1", "Queen Palace price list page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/13.%20Queen%20Place/2.png",
                of("Bảng giá Queen Palace trang 2", "Queen Palace price list page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/13.%20Queen%20Place/3.png",
                of("Bảng giá Queen Palace trang 3", "Queen Palace price list page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/13.%20Queen%20Place/4.png",
                of("Bảng giá Queen Palace trang 4", "Queen Palace price list page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/13.%20Queen%20Place/5.png",
                of("Bảng giá Queen Palace trang 5", "Queen Palace price list page 5"), false, 4)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("DRINKS_COCKTAIL")
                .name(of("Đồ uống & cocktail", "Drinks & cocktails"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SNACK_FRUIT")
                .name(of("Đồ nhắm & trái cây", "Snacks & fruits"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (12:00 - 23:59 mỗi ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    12, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   12, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 12, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  12, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    12, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  12, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    12, 0, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm giải trí", "Group entertainment")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("COUPLE",        of("Hẹn hò riêng tư", "Private date")),
            suitable("OFFICE_LUNCH",  of("Liên hoan sau giờ làm", "After-work party")),
            suitable("FAMILY",        of("Tụ họp gia đình", "Family gathering")),
            suitable("BUSINESS_MEAL", of("Tiếp khách giải trí", "Entertainment for guests"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",   of("Sôi động, bùng nổ", "Vibrant and energetic")),
            ambience("LUXURIOUS", of("Sang trọng phong cách hoàng gia", "Luxurious royal style")),
            ambience("MODERN",    of("Hiện đại, đẳng cấp", "Modern and high-class")),
            ambience("ROMANTIC",  of("Lãng mạn, riêng tư", "Romantic and private"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(500)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(33)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (dịch vụ / đồ ăn kèm) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Trái cây thập cẩm Queen size", "Queen-size mixed fruit platter"),
                of("Đĩa trái cây tươi thập cẩm thơm ngon, được bày biện đẹp mắt, phù hợp nhâm nhi trong giờ hát.",
                   "Fresh mixed seasonal fruit platter, elegantly arranged — perfect for nibbling during karaoke sessions."),
                new BigDecimal("120000")),
            signatureDish(of("Bia Tiger lon (thùng 24 lon)", "Tiger Beer cans (24-can case)"),
                of("Bia Tiger lon lạnh ngon, phục vụ trọn gói cho cả nhóm với giá ưu đãi khi đặt theo thùng.",
                   "Cold Tiger beer cans, full-package service for the entire group with preferential pricing per case."),
                new BigDecimal("480000")),
            signatureDish(of("Cocktail trái cây đặc biệt", "Special fruit cocktail"),
                of("Cocktail pha chế từ trái cây tươi, rượu nhẹ và soda, màu sắc bắt mắt, vị chua ngọt dễ uống.",
                   "Cocktail crafted from fresh fruits, light spirits and soda, visually stunning with a pleasant sweet-sour taste."),
                new BigDecimal("90000")),
            signatureDish(of("Đồ nhắm khô mực nướng", "Grilled dried squid snack"),
                of("Mực khô nướng than hoa thơm lừng, xé sợi giòn dai, chấm tương ớt — món nhắm không thể thiếu.",
                   "Fragrant charcoal-grilled dried squid, shredded into chewy strips, dipped in chili sauce — an essential snack."),
                new BigDecimal("80000")),
            signatureDish(of("Lạc rang muối", "Salted roasted peanuts"),
                of("Lạc rang muối giòn bùi, đóng gói đẹp, nhâm nhi thoải mái suốt buổi hát.",
                   "Crispy and nutty salted roasted peanuts, neatly packaged, perfect for snacking throughout the session."),
                new BigDecimal("40000")),
            signatureDish(of("Rượu vang đỏ nhập khẩu", "Imported red wine"),
                of("Rượu vang đỏ nhập khẩu từ Chile và Pháp, được lưu trữ ở nhiệt độ phù hợp, phục vụ nguyên chai.",
                   "Imported red wine from Chile and France, stored at optimal temperature, served by the bottle."),
                new BigDecimal("450000")),
            signatureDish(of("Kem tươi thập cẩm", "Mixed fresh ice cream"),
                of("Tổng hợp các vị kem tươi ngon mát lạnh: socola, vani, dâu, matcha, xoài — làm dịu giọng sau khi hát.",
                   "Assorted fresh ice cream flavors: chocolate, vanilla, strawberry, matcha, mango — perfect for cooling the throat after singing."),
                new BigDecimal("70000")),
            signatureDish(of("Nước ngọt các loại (két 24 lon)", "Assorted soft drinks (24-can case)"),
                of("Két nước ngọt đa dạng bao gồm Coca, Sprite, 7-Up, nước tăng lực, phục vụ cả nhóm tiết kiệm.",
                   "Assorted soft drink case including Coke, Sprite, 7-Up, energy drinks — economical group service."),
                new BigDecimal("250000")),
            signatureDish(of("Snack khoai tây chiên giòn", "Crispy potato chips"),
                of("Khoai tây chiên giòn rụm theo nhiều vị: bơ tỏi, phô mai, BBQ — ăn kèm bia cực kỳ đã.",
                   "Crispy potato chips in multiple flavors: butter garlic, cheese, BBQ — perfectly pairs with beer."),
                new BigDecimal("50000")),
            signatureDish(of("Set nhắm VIP tổng hợp", "VIP assorted snack set"),
                of("Set đồ nhắm VIP gồm: mực khô, hạt mixed, trái cây tươi, xúc xích nướng và một số món nhẹ khác.",
                   "VIP snack set including: dried squid, mixed nuts, fresh fruit, grilled sausage and other light bites."),
                new BigDecimal("280000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("DRINKS")
                .name(of("Đồ uống", "Beverages"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bia Tiger lon", "Tiger Beer can"))
                        .description(of("Bia Tiger lạnh, phục vụ lẻ hoặc cả thùng.",
                                       "Cold Tiger beer, served individually or by the case."))
                        .priceFrom(new BigDecimal("25000"))
                        .priceTo(new BigDecimal("480000"))
                        .unit(of("lon / thùng", "can / case"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beer", "drinks"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Rượu vang đỏ nhập khẩu", "Imported red wine"))
                        .description(of("Rượu vang đỏ Chile và Pháp, phục vụ nguyên chai.",
                                       "Chilean and French red wine, served by the bottle."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("850000"))
                        .unit(of("chai", "bottle"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("wine", "drinks", "vip"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("SNACKS_FRUITS")
                .name(of("Đồ nhắm & trái cây", "Snacks & Fruits"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Trái cây thập cẩm", "Mixed seasonal fruit"))
                        .description(of("Đĩa trái cây tươi đa dạng theo mùa, bày biện đẹp mắt.",
                                       "Diverse fresh seasonal fruit platter, elegantly arranged."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("220000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("fruit", "snack"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set nhắm VIP", "VIP snack set"))
                        .description(of("Set tổng hợp mực khô, hạt mixed, trái cây tươi và xúc xích nướng.",
                                       "Assorted set of dried squid, mixed nuts, fresh fruit and grilled sausage."))
                        .priceFrom(new BigDecimal("280000"))
                        .priceTo(new BigDecimal("280000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("snack", "vip", "combo"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Ngay tại nhà hàng", "At the venue"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Ngay tại nhà hàng", "At the venue"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe ô tô và xe máy miễn phí ngay tại Queen Palace.", "Free car and motorbike parking available right at Queen Palace."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "KARAOKE",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "PROJECTOR",
            "LED_SCREEN",
            "SMOKING_AREA",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "DIRECT_INVOICE",
            "WIFI",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SOUNDPROOF_ROOM"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Queen Palace Karaoke — Thiên đường giải trí âm nhạc tại Đà Nẵng",
                   "Queen Palace Karaoke — Entertainment paradise in Da Nang")),
            paragraph(ContentSection.STORY,
                of("Queen Palace Karaoke tọa lạc tại số 179 Xô Viết Nghệ Tĩnh, Phường Cẩm Lệ, TP. Đà Nẵng — " +
                   "một trong những địa điểm karaoke sang trọng và được ưa chuộng nhất tại thành phố. Với tòa nhà " +
                   "7 tầng gồm 33 phòng hát, Queen Palace mang đến trải nghiệm âm nhạc bùng nổ trong không gian " +
                   "đẳng cấp, cách âm tuyệt đối, phù hợp cho mọi nhóm từ 2 đến 30 người.",
                   "Queen Palace Karaoke is located at 179 Xo Viet Nghe Tinh, Cam Le Ward, Da Nang City — " +
                   "one of the most luxurious and popular karaoke venues in the city. With a 7-floor building " +
                   "featuring 33 singing rooms, Queen Palace delivers an explosive musical experience in a " +
                   "world-class, perfectly soundproofed environment suitable for groups of 2 to 30.")),
            heading(ContentSection.STORY,
                of("Không gian & phòng hát đẳng cấp",
                   "Luxurious space & singing rooms")),
            paragraph(ContentSection.STORY,
                of("Queen Palace Karaoke sở hữu 33 phòng hát phân bổ trên 7 tầng với các quy mô khác nhau — từ " +
                   "phòng mini ấm cúng cho 2–6 người đến phòng VIP lớn sức chứa 20–30 khách. Mỗi phòng được " +
                   "trang bị hệ thống âm thanh cao cấp, màn hình LED siêu nét, micro không dây chất lượng studio " +
                   "và nội thất theo phong cách hoàng cung sang trọng với ghế sofa êm ái, ánh đèn đẹp mắt. " +
                   "Sức chứa toàn bộ khu vực lên đến 500 khách.",
                   "Queen Palace Karaoke boasts 33 singing rooms across 7 floors in various sizes — from cozy mini " +
                   "rooms for 2–6 people to large VIP suites accommodating 20–30 guests. Each room features a " +
                   "premium audio system, ultra-clear LED screen, studio-quality wireless microphones and royal-style " +
                   "luxurious furnishings with plush sofas and beautiful ambient lighting. Total venue capacity " +
                   "reaches 500 guests.")),
            paragraph(ContentSection.STORY,
                of("Hệ thống cách âm chuyên nghiệp đảm bảo âm thanh không lọt ra ngoài, mang đến sự riêng tư " +
                   "tuyệt đối cho từng phòng. Công nghệ âm thanh hiện đại với mixer chuyên nghiệp, loa đa chiều " +
                   "và đèn laser sân khấu tạo nên bầu không khí như một sân khấu âm nhạc thu nhỏ ngay trong phòng.",
                   "Professional soundproofing ensures audio stays within each room, delivering absolute privacy. " +
                   "Modern audio technology featuring professional mixers, surround-sound speakers and stage laser " +
                   "lights creates an atmosphere like a miniature concert stage right in the room.")),
            heading(ContentSection.STORY,
                of("Thực đơn đồ ăn & đồ uống",
                   "Food & beverage menu")),
            paragraph(ContentSection.STORY,
                of("Bên cạnh dịch vụ karaoke, Queen Palace phục vụ đa dạng đồ ăn nhẹ và đồ uống tại phòng: " +
                   "trái cây thập cẩm, đồ nhắm khô mực, lạc rang, snack khoai tây các vị, bia Tiger, nước ngọt " +
                   "và cocktail trái cây đặc biệt. Thực đơn được thiết kế phù hợp để thưởng thức trong khi hát " +
                   "mà không làm gián đoạn trải nghiệm âm nhạc. Giá đồ ăn đồ uống từ 40.000 – 480.000 đồng/món.",
                   "In addition to karaoke services, Queen Palace serves a variety of light food and beverages " +
                   "in-room: mixed seasonal fruit, dried squid snacks, roasted peanuts, flavored potato chips, " +
                   "Tiger beer, soft drinks and special fruit cocktails. The menu is designed to enjoy while singing " +
                   "without disrupting the musical experience. Food and beverage prices range from 40,000 – 480,000 VND per item.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào",
                   "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Queen Palace Karaoke phù hợp cho tất cả các dịp tụ họp: bữa tiệc sau giờ làm của đồng nghiệp, " +
                   "tổ chức sinh nhật, kỷ niệm bạn bè, liên hoan văn phòng, tiếp khách đối tác giải trí hay " +
                   "đơn giản là một buổi tối vui vẻ giữa cặp đôi. Phòng karaoke riêng biệt giúp mỗi nhóm có " +
                   "không gian hoàn toàn riêng tư, thoải mái thể hiện bản thân.",
                   "Queen Palace Karaoke is perfect for all gathering occasions: after-work colleague parties, " +
                   "birthday celebrations, friends' anniversaries, office parties, partner entertainment " +
                   "or simply a fun evening for couples. Separate private rooms give each group completely " +
                   "private space to comfortably express themselves.")),
            heading(ContentSection.STORY,
                of("Ưu đãi độc quyền PasGo",
                   "Exclusive PasGo promotions")),
            paragraph(ContentSection.STORY,
                of("Khách đặt qua hệ thống PasGo được giảm 10% tổng giờ hát/bill, áp dụng quanh năm kể cả ngày " +
                   "lễ tết. Ngoài ra, khách hàng đến đúng ngày sinh nhật được tặng 1 gấu bông Queen size 35cm — " +
                   "một món quà bất ngờ đáng yêu. Hãy đặt bàn trước ít nhất 15 phút để đảm bảo có phòng ưng ý.",
                   "Guests booking via PasGo receive 10% off total singing time/bill, applicable year-round including " +
                   "public holidays. Additionally, guests visiting on their exact birthday receive a free Queen-size " +
                   "35cm teddy bear — a charming surprise gift. Book at least 15 minutes in advance to secure your " +
                   "preferred room.")),
            heading(ContentSection.STORY,
                of("Chính sách & quy định",
                   "Policies & regulations")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng yêu cầu đặt cọc trước khi vào phòng — vui lòng liên hệ trực tiếp để biết chi tiết. " +
                   "Không được mang thức ăn từ bên ngoài vào; đồ uống từ ngoài được phép mang vào nhưng có tính " +
                   "phí (corkage fee). Thời gian giữ phòng tối đa 30 phút sau giờ đặt. Nhà hàng xuất hóa đơn " +
                   "trực tiếp (không có hóa đơn VAT). Không thu phí phục vụ.",
                   "The venue requires a deposit before entering the room — please contact directly for details. " +
                   "Outside food is not permitted; outside drinks are allowed but subject to a corkage fee. " +
                   "Rooms are held for a maximum of 30 minutes after the reservation time. The venue issues direct " +
                   "invoices (no VAT invoice). No service charge applies.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Số 179 Xô Viết Nghệ Tĩnh nằm ở Quận Cẩm Lệ, phía Nam trung tâm thành phố Đà Nẵng, dễ dàng " +
                   "di chuyển từ khu vực sân bay, cầu Rồng và các điểm du lịch chính. Bãi đỗ xe ô tô và xe máy " +
                   "miễn phí ngay tại địa điểm. Queen Palace là điểm đến giải trí lý tưởng sau một ngày khám phá " +
                   "thành phố biển Đà Nẵng.",
                   "179 Xo Viet Nghe Tinh is located in Cam Le District, south of Da Nang city center, easily " +
                   "accessible from the airport area, Dragon Bridge and major tourist attractions. Free parking " +
                   "for both cars and motorbikes available right at the venue. Queen Palace is the ideal " +
                   "entertainment destination after a day exploring the coastal city of Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.2098, 16.0263})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang vào: 150.000đ/chai.", "Wine corkage fee: 150,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("250000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang vào: 250.000đ/chai.", "Spirits corkage fee: 250,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(null)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Yêu cầu đặt cọc trước khi vào phòng; vui lòng liên hệ nhà hàng để biết chi tiết.",
                             "Deposit required before entering the room; please contact the venue for details."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm 10% giờ hát/bill cho khách đặt qua PasGo; tặng gấu bông sinh nhật.",
                                 "10% off singing time/bill for PasGo bookings; free birthday teddy bear gift."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi áp dụng quanh năm kể cả ngày lễ.", "Promotion applies year-round including holidays."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(15)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(false)
            .vatPercent(BigDecimal.ZERO)
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn trực tiếp; không xuất hóa đơn VAT.", "Direct invoice available; VAT invoice not issued."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không có phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống vào, có tính phụ phí tùy loại.", "Outside drinks allowed with a corkage fee by type."))
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

        // ---------- Loại bàn (phòng karaoke) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("kr-mini-6")
                .name(of("Phòng Mini (2-6 người)", "Mini Room (2-6 guests)"))
                .seats(6)
                .minPeople(2)
                .maxPeople(6)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(60, 90, 120, 180))
                .note(of("Phòng nhỏ ấm cúng cho cặp đôi và nhóm bạn thân.", "Cozy small room for couples and close friend groups."))
                .build(),
            TableType.builder()
                .id("kr-std-12")
                .name(of("Phòng Standard (6-12 người)", "Standard Room (6-12 guests)"))
                .seats(12)
                .minPeople(6)
                .maxPeople(12)
                .totalTables(13)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 180))
                .note(of("Phòng tiêu chuẩn rộng rãi, phù hợp nhóm trung bình.", "Spacious standard room for medium-sized groups."))
                .build(),
            TableType.builder()
                .id("kr-vip-30")
                .name(of("Phòng VIP (12-30 người)", "VIP Room (12-30 guests)"))
                .seats(30)
                .minPeople(12)
                .maxPeople(30)
                .totalTables(8)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(180)
                .allowedDurationsMinutes(List.of(120, 180, 240))
                .note(of("Phòng VIP lớn sang trọng, phù hợp liên hoan công ty và tiệc lớn.", "Large luxury VIP room for corporate parties and big celebrations."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120, 180, 240))
            .defaultDurationMinutes(120)
            .minBookingLeadTimeMinutes(15)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(15)
            .depositOnly(true)
            .maxTablesPerBooking(3)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Q. Cẩm Lệ", "Cam Le District"))
            .wardName(of("P. Cẩm Lệ", "Cam Le Ward"))
            .addressLine(of("Số 179 Xô Viết Nghệ Tĩnh, P. Cẩm Lệ, TP. Đà Nẵng",
                            "179 Xo Viet Nghe Tinh, Cam Le Ward, Da Nang City"))
            .location(new double[]{108.2098, 16.0263})
            .name(of("Queen Palace Karaoke - Đà Nẵng", "Queen Palace Karaoke - Da Nang"))
            .slug(slug)
            .restaurantType(RestaurantType.LOUNGE)
            .cuisines(cuisines)
            .shortDescription(of("Karaoke 7 tầng sang trọng tại Đà Nẵng — 33 phòng cách âm chuẩn, âm thanh ánh sáng đỉnh cao, 500 khách.",
                                 "7-floor luxury karaoke in Da Nang — 33 soundproof rooms, top-tier sound & lighting, 500-guest capacity."))
            .description(of("Queen Palace Karaoke tại 179 Xô Viết Nghệ Tĩnh, Q. Cẩm Lệ, TP. Đà Nẵng là tổ hợp karaoke giải trí " +
                            "7 tầng sang trọng với 33 phòng hát cách âm tuyệt đối, sức chứa 500 khách. Không gian hiện đại phong " +
                            "cách hoàng gia, hệ thống âm thanh ánh sáng đỉnh cao. Giá từ 300.000 – 400.000 đồng/người. Phù hợp " +
                            "tụ tập bạn bè, sinh nhật, liên hoan văn phòng và mọi dịp giải trí.",
                            "Queen Palace Karaoke at 179 Xo Viet Nghe Tinh, Cam Le District, Da Nang is a 7-floor luxury " +
                            "entertainment complex with 33 perfectly soundproofed singing rooms, 500-guest capacity. Modern " +
                            "royal-style ambiance, top-tier sound and lighting systems. Prices from 300,000 – 400,000 VND per person. " +
                            "Perfect for friend gatherings, birthdays, office parties and all entertainment occasions."))
            .phone("0931006005")
            .bookingHotline("0931006005")
            .website("https://pasgo.vn/nha-hang/queen-palace-karaoke-da-nang-5764")
            .minPricePerPerson(new BigDecimal("300000"))
            .maxPricePerPerson(new BigDecimal("400000"))
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
