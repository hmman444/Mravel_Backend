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
public class SeedYakimonoGoThangLong {

    @Bean
    CommandLineRunner seedYakimonoGoThangLongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedYakimonoGoThangLong] start");

            RestaurantDoc doc = restaurantYakimonoGoThangLong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedYakimonoGoThangLong] done");
        };
    }

    // =====================================================================
    //   BUFFET NƯỚNG YAKIMONO - GO! THĂNG LONG (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantYakimonoGoThangLong() {
        String slug = "buffet-nuong-yakimono-go-thang-long";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-1-normal-4214358472677.webp",
                of("Không gian Yakimono - GO! Thăng Long", "Yakimono - GO! Thang Long interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-2-normal-4214358672678.webp",
                of("Khu vực nướng tại bàn Yakimono", "Table BBQ grilling area at Yakimono"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-3-normal-4214358772679.webp",
                of("Thực đơn buffet nướng Nhật phong phú", "Diverse Japanese BBQ buffet menu"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-4-normal-4214358872680.webp",
                of("Bàn nướng lẩu kết hợp tại Yakimono", "BBQ hotpot combo table at Yakimono"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-5-normal-4214358972681.webp",
                of("Không gian hiện đại trẻ trung Yakimono", "Modern youthful ambiance at Yakimono"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-6-normal-4214359072682.webp",
                of("Quầy buffet nguyên liệu tươi phong phú", "Fresh ingredients buffet counter"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-7-normal-4214359172683.webp",
                of("Nguyên liệu thịt bò Mỹ cao cấp", "Premium US beef ingredients"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-8-normal-4214359272684.webp",
                of("Hải sản tươi tại quầy Yakimono", "Fresh seafood at Yakimono counter"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-go-thang-long-9-normal-4214359372685.webp",
                of("Khu vực phòng riêng Yakimono Go Thăng Long", "Private dining area at Yakimono"), false, 8),
            img("https://pasgo.vn/Upload/anh-diem-den/yakimono-go--thang-long-300-421439425788.webp",
                of("Toàn cảnh nhà hàng Yakimono trong siêu thị GO!", "Full view of Yakimono inside GO! supermarket"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/gian-hang-so-1s11--sieu-thi-go--thang-long--so-222-tran-duy-hung--p--yen-hoa-210074192105793188.webp",
                of("Biển hiệu gian hàng 1S11 Yakimono tại GO! Thăng Long", "Yakimono store sign at GO! Thang Long"), false, 10),
            img("https://pasgo.vn/Upload/anh-mat-hang/suat-buffet-nuong-godzdilla-t2--t6--gia-239k-300-421551483790.webp",
                of("Suất buffet nướng Godzdilla 239K", "Godzdilla BBQ buffet 239K"), false, 11)
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-quay-line.jpg",
                of("Bảng giá quầy line Yakimono", "Yakimono line counter menu"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-1.jpg",
                of("Menu suất buffet 239K trang 1", "Buffet menu 239K page 1"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-2.jpg",
                of("Menu suất buffet 239K trang 2", "Buffet menu 239K page 2"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-3.jpg",
                of("Menu suất buffet 239K trang 3", "Buffet menu 239K page 3"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-4.jpg",
                of("Menu suất buffet 239K trang 4", "Buffet menu 239K page 4"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-1.jpg",
                of("Menu suất buffet Tanuki 299K trang 1", "Tanuki buffet menu 299K page 1"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-2.jpg",
                of("Menu suất buffet Tanuki 299K trang 2", "Tanuki buffet menu 299K page 2"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0001.jpg",
                of("Ảnh thực đơn Yakimono 1", "Yakimono menu photo 1"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0002.jpg",
                of("Ảnh thực đơn Yakimono 2", "Yakimono menu photo 2"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0003.jpg",
                of("Ảnh thực đơn Yakimono 3", "Yakimono menu photo 3"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0004.jpg",
                of("Ảnh thực đơn Yakimono 4", "Yakimono menu photo 4"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0005.jpg",
                of("Ảnh thực đơn Yakimono 5", "Yakimono menu photo 5"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0006.jpg",
                of("Ảnh thực đơn Yakimono 6", "Yakimono menu photo 6"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0007.jpg",
                of("Ảnh thực đơn Yakimono 7", "Yakimono menu photo 7"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0008.jpg",
                of("Ảnh thực đơn Yakimono 8", "Yakimono menu photo 8"), false, 14)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE_BBQ")
                .name(of("Nướng Nhật Bản", "Japanese BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("JAPANESE_HOTPOT")
                .name(of("Lẩu Nhật Bản", "Japanese Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET")
                .name(of("Buffet", "Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("KIDS_FRIENDLY", of("Thân thiện trẻ em", "Kid-friendly"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",   of("Hiện đại", "Modern")),
            ambience("VIBRANT",  of("Trẻ trung sôi động", "Youthful and vibrant")),
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("CASUAL",   of("Thoải mái casual", "Casual and relaxed"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Ba chỉ bò Mỹ cuộn nấm", "US beef belly rolled with mushroom"),
                of("Thịt ba chỉ bò Mỹ mềm mại cuộn cùng nấm kim châm nướng thơm lừng trên bếp than hoa.",
                   "Tender US beef belly rolled with enoki mushrooms, grilled to perfection over charcoal."),
                new BigDecimal("239000")),
            signatureDish(of("Dẻ sườn bò Mỹ nướng Yakimono", "Yakimono-style US beef short ribs"),
                of("Sườn bò Mỹ tẩm ướp sốt Yakimono đặc trưng, nướng giòn cạnh, giữ nguyên vị ngọt thơm của thịt.",
                   "US beef short ribs marinated in signature Yakimono sauce, grilled with a crispy crust and naturally sweet flavor."),
                new BigDecimal("299000")),
            signatureDish(of("Thịt vai bò Úc nướng sốt Nhật", "Australian shoulder beef with Japanese sauce"),
                of("Miếng thịt vai bò Úc thái mỏng, ngấm sốt umami Nhật Bản, chín đều trên vỉ than hồng.",
                   "Thinly sliced Australian shoulder beef marinated in Japanese umami sauce, evenly grilled over hot coals."),
                new BigDecimal("299000")),
            signatureDish(of("Bánh xèo Nhật Bản (Okonomiyaki)", "Japanese savory pancake (Okonomiyaki)"),
                of("Bánh xèo kiểu Nhật phủ mực khô, sốt mayonnaise Nhật và bột cá bào vàng ươm.",
                   "Japanese-style savory pancake topped with dried squid, Japanese mayo, and golden bonito flakes."),
                new BigDecimal("239000")),
            signatureDish(of("Bạch tuộc nướng sốt ớt xanh", "Grilled octopus with green chili sauce"),
                of("Bạch tuộc tươi nướng trực tiếp trên bếp, chan sốt ớt xanh chua cay đặc trưng của Yakimono.",
                   "Fresh octopus grilled directly on the grill, topped with Yakimono's signature tangy green chili sauce."),
                new BigDecimal("239000")),
            signatureDish(of("Lẩu Shabu-Shabu nước dùng bò", "Beef broth Shabu-Shabu hotpot"),
                of("Nước dùng bò hầm xương đậm vị, dùng kèm rau xanh tươi và các loại thịt thái mỏng nhúng lẩu.",
                   "Rich beef bone broth hotpot served with fresh greens and thinly sliced meats for dipping."),
                new BigDecimal("299000")),
            signatureDish(of("Mực nướng sa tế tỏi", "Grilled squid with satay and garlic"),
                of("Mực ống tươi tẩm sa tế và tỏi phi thơm, nướng giòn da bên ngoài, giữ độ dai ngọt bên trong.",
                   "Fresh whole squid marinated with satay and fried garlic, crispy skin outside with tender sweet flesh inside."),
                new BigDecimal("239000")),
            signatureDish(of("Tôm sú nướng bơ tỏi", "Grilled tiger prawns with garlic butter"),
                of("Tôm sú tươi bơm bơ tỏi nướng đến khi vỏ hồng đều, thịt ngọt chắc không tanh.",
                   "Fresh tiger prawns brushed with garlic butter and grilled until the shell turns evenly pink, with sweet firm flesh."),
                new BigDecimal("299000")),
            signatureDish(of("Đùi ếch nướng sa tế", "Grilled frog legs with satay"),
                of("Đùi ếch tẩm sa tế cay nhẹ, nướng giòn ngoài mềm trong, thích hợp nhâm nhi cùng nước dùng lẩu.",
                   "Frog legs marinated in mildly spicy satay, crispy outside and soft inside, great alongside the hotpot broth."),
                new BigDecimal("239000")),
            signatureDish(of("Kem tráng miệng & trái cây tươi", "Ice cream dessert & fresh fruit"),
                of("Buffet kết thúc ngọt ngào với quầy tráng miệng gồm kem Nhật Bản và trái cây tươi theo mùa.",
                   "Finish the buffet sweetly with a dessert counter featuring Japanese ice cream and seasonal fresh fruits."),
                new BigDecimal("239000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_GODZDILLA")
                .name(of("Suất Buffet Nướng Godzdilla (T2–T6)", "Godzdilla BBQ Buffet (Mon–Fri)"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất Buffet Nướng Godzdilla T2–T6", "Godzdilla BBQ Buffet Mon–Fri"))
                        .description(of("Buffet nướng không giới hạn hơn 50 món nguyên liệu tươi, gồm thịt bò Mỹ, hải sản, rau nấm và nước dùng lẩu cơ bản.",
                            "Unlimited BBQ buffet with 50+ fresh ingredients including US beef, seafood, vegetables, mushrooms and basic hotpot broth."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("239000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "weekday"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_TANUKI")
                .name(of("Suất Buffet Nướng Lẩu Tanuki (T2–CN)", "Tanuki BBQ Hotpot Buffet (Mon–Sun)"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất Buffet Nướng Lẩu Tanuki T2–CN", "Tanuki BBQ Hotpot Buffet Mon–Sun"))
                        .description(of("Buffet nướng lẩu kết hợp với hơn 80 món, thêm nước dùng lẩu cao cấp, thịt bò Mỹ cao hơn 1 bậc.",
                            "BBQ-hotpot combo buffet with 80+ items, premium hotpot broth and higher-grade US beef."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất Buffet Nướng Lẩu Neko T2–CN", "Neko BBQ Hotpot Buffet Mon–Sun"))
                        .description(of("Nâng cấp từ Tanuki, thêm hải sản cao cấp và thịt bò Úc, nước dùng Dashi chuẩn vị Nhật.",
                            "Upgraded from Tanuki, with premium seafood, Australian beef, and authentic Japanese Dashi broth."))
                        .priceFrom(new BigDecimal("369000"))
                        .priceTo(new BigDecimal("369000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất Buffet Nướng Lẩu Takara T2–CN", "Takara BBQ Hotpot Buffet Mon–Sun"))
                        .description(of("Suất cao cấp nhất tại Yakimono, đầy đủ hải sản nhập khẩu, bò Wagyu Mỹ và các loại nước dùng Nhật đặc sắc.",
                            "The premium-most tier at Yakimono, with imported seafood, US Wagyu beef and a selection of special Japanese broths."))
                        .priceFrom(new BigDecimal("479000"))
                        .priceTo(new BigDecimal("479000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot", "wagyu", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi gửi xe siêu thị GO! Thăng Long", "GO! Thang Long supermarket parking lot"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("10000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi gửi xe siêu thị GO! Thăng Long", "GO! Thang Long supermarket parking lot"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Mức phí phụ thuộc vào đơn vị trông giữ xe của siêu thị GO! Thăng Long.",
                "Parking fee is subject to the parking operator of GO! Thang Long supermarket."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "FREE_MOTORBIKE_PARKING",
            "FREE_CAR_PARKING",
            "PRIVATE_ROOM",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "LED_SCREEN",
            "KIDS_AREA",
            "SMOKING_AREA",
            "OUTDOOR_TABLE",
            "VISA_MASTER",
            "MOMO_ZALOPAY",
            "VAT_INVOICE",
            "DIRECT_INVOICE",
            "KIDS_CHAIR"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Yakimono – Triết lý ẩm thực Nhật Bản bình dân tại GO! Thăng Long",
                   "Yakimono – Accessible Japanese Dining Philosophy at GO! Thang Long")),
            paragraph(ContentSection.STORY,
                of("Yakimono - GO! Thăng Long tọa lạc tại gian hàng 1S11 trong siêu thị GO! Thăng Long, " +
                   "số 222 Trần Duy Hưng, phường Yên Hòa, quận Cầu Giấy, Hà Nội. " +
                   "Là một phần của hệ thống nhà hàng Yakimono chuyên buffet nướng lẩu Nhật Bản, " +
                   "thương hiệu hoạt động với tiêu chí 'Quyền được ăn đồ Nhật ngon, giá rẻ, đảm bảo sức khỏe'.",
                   "Yakimono - GO! Thang Long is located at unit 1S11 inside GO! Thang Long supermarket, " +
                   "222 Tran Duy Hung, Yen Hoa Ward, Cau Giay District, Hanoi. " +
                   "Part of the Yakimono restaurant chain specializing in Japanese BBQ hotpot buffet, " +
                   "the brand operates under the motto 'The right to eat quality Japanese food at affordable prices, with health in mind'.")),
            paragraph(ContentSection.STORY,
                of("Vị trí nằm trong siêu thị GO! là một lợi thế lớn — khách hàng có thể kết hợp mua sắm " +
                   "và thưởng thức bữa ăn Nhật Bản chuẩn vị ngay tại trung tâm quận Cầu Giấy. " +
                   "Nhà hàng phục vụ liên tục từ 10:00 đến 22:00 mỗi ngày, không nghỉ lễ.",
                   "Being inside GO! supermarket is a distinct advantage — guests can combine shopping " +
                   "and enjoying an authentic Japanese meal right in the heart of Cau Giay District. " +
                   "The restaurant serves continuously from 10:00 to 22:00 every day, including holidays.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian hiện đại, trẻ trung với sức chứa khoảng 150 khách, " +
                   "bố trí theo kiểu bàn nướng cá nhân cho từng bàn ăn — mỗi bàn đều được trang bị bếp nướng " +
                   "than hoa hoặc bếp điện tiêu chuẩn Nhật Bản. Hệ thống hút khói hiện đại giúp không gian " +
                   "luôn thoáng đãng, không gây khó chịu trong suốt bữa ăn.",
                   "The restaurant features a modern, youthful space with a capacity of around 150 guests, " +
                   "arranged with individual grills at each table — every table is equipped with a charcoal or " +
                   "standard Japanese electric grill. An advanced ventilation system keeps the air fresh " +
                   "and comfortable throughout the meal.")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng có 2 phòng riêng với sức chứa tối đa 30 khách mỗi phòng, phù hợp cho " +
                   "nhóm công ty, liên hoan gia đình hoặc tổ chức sự kiện nhỏ cần không gian riêng tư. " +
                   "Trang thiết bị máy chiếu, màn LED và hệ thống âm thanh ánh sáng được hỗ trợ đầy đủ.",
                   "The restaurant has 2 private rooms with a maximum capacity of 30 guests each, suitable for " +
                   "company groups, family gatherings or small events requiring privacy. " +
                   "Projectors, LED screens and audio-visual systems are fully equipped.")),
            heading(ContentSection.STORY,
                of("Thực đơn & các suất buffet đặc sắc", "Menu & signature buffet tiers")),
            paragraph(ContentSection.STORY,
                of("Yakimono cung cấp 4 suất buffet với mức giá linh hoạt từ 239.000đ đến 479.000đ/người, " +
                   "phù hợp với nhiều đối tượng thực khách. Suất Godzdilla 239K (Thứ 2–6) là lựa chọn bình dân " +
                   "với hơn 50 nguyên liệu nướng tươi. Suất Tanuki 299K nâng cấp thêm nước dùng lẩu cao cấp " +
                   "và thêm nhóm thịt bò Mỹ chất lượng cao.",
                   "Yakimono offers 4 buffet tiers with flexible pricing from 239,000 to 479,000 VND/person, " +
                   "catering to a wide range of diners. The Godzdilla tier at 239K (Mon–Fri) is the budget-friendly " +
                   "choice with 50+ fresh grilling ingredients. The Tanuki tier at 299K upgrades to premium hotpot broth " +
                   "and higher-quality US beef selections.")),
            paragraph(ContentSection.STORY,
                of("Suất Neko 369K tiếp tục nâng cấp với hải sản cao cấp, thịt bò Úc và nước dùng Dashi " +
                   "chuẩn vị Nhật. Suất Takara 479K là đỉnh cao của trải nghiệm Yakimono — hải sản nhập khẩu, " +
                   "bò Wagyu Mỹ và loạt nước dùng Nhật đặc sắc. Tất cả các suất đều bao gồm rau củ tươi " +
                   "và phần tráng miệng với kem Nhật cùng trái cây theo mùa.",
                   "The Neko tier at 369K further upgrades to premium seafood, Australian beef and authentic " +
                   "Japanese Dashi broth. The Takara tier at 479K represents the peak Yakimono experience — " +
                   "imported seafood, US Wagyu beef and a selection of special Japanese broths. All tiers include " +
                   "fresh vegetables and a dessert station with Japanese ice cream and seasonal fruits.")),
            heading(ContentSection.STORY,
                of("Các món đặc sắc không thể bỏ qua", "Must-try signature dishes")),
            paragraph(ContentSection.STORY,
                of("Ba chỉ bò Mỹ cuộn nấm kim châm, dẻ sườn bò Mỹ tẩm sốt Yakimono đặc trưng, " +
                   "bánh xèo Nhật Bản (Okonomiyaki) phủ bột cá bào, bạch tuộc nướng sốt ớt xanh và " +
                   "tôm sú nướng bơ tỏi là những món thực khách thường gọi đầu tiên khi đến Yakimono. " +
                   "Các công thức tẩm ướp được nghiên cứu kỹ lưỡng để giữ đúng hương vị Nhật Bản.",
                   "US beef belly rolled with enoki mushrooms, Yakimono-sauced US short ribs, " +
                   "Okonomiyaki topped with bonito flakes, green-chili grilled octopus and garlic-butter tiger prawns " +
                   "are the dishes guests reach for first at Yakimono. " +
                   "Each marinade recipe is carefully crafted to preserve authentic Japanese flavors.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá khởi điểm 239.000đ/người và không gian thoải mái trong khuôn viên siêu thị, " +
                   "Yakimono phù hợp với nhiều dịp: tụ tập bạn bè, bữa ăn gia đình cuối tuần, liên hoan văn phòng, " +
                   "sinh nhật hay hẹn hò. Các phòng riêng đáp ứng tốt nhóm công ty 10–30 người " +
                   "cần tổ chức sự kiện riêng tư.",
                   "With a starting price of 239,000 VND/person and a comfortable setting inside a supermarket, " +
                   "Yakimono suits many occasions: friends' hangouts, weekend family dinners, office parties, " +
                   "birthday celebrations or dates. The private rooms accommodate company groups of 10–30 people " +
                   "needing a private event space.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Siêu thị GO! Thăng Long tọa lạc tại số 222 Trần Duy Hưng, một trong những trục đường " +
                   "thương mại sầm uất nhất quận Cầu Giấy. Từ đây chỉ 5–10 phút di chuyển đến Big C Cầu Giấy, " +
                   "Keangnam Hanoi Landmark và các văn phòng tòa nhà khu Mỹ Đình – Cầu Giấy. " +
                   "Có bãi đỗ xe máy và ô tô rộng rãi của siêu thị ngay tầng hầm.",
                   "GO! Thang Long supermarket is located at 222 Tran Duy Hung, one of the busiest commercial " +
                   "streets in Cau Giay District. From here it is only 5–10 minutes to Big C Cau Giay, " +
                   "Keangnam Hanoi Landmark and the office buildings of the My Dinh – Cau Giay area. " +
                   "The supermarket's spacious motorbike and car parking is available in the basement.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & ưu đãi", "Booking policy & promotions")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng không yêu cầu đặt cọc, đặt chỗ tối thiểu từ 2 người lớn trở lên. " +
                   "Thời gian giữ chỗ tối đa 15 phút kể từ giờ đặt. " +
                   "Có xuất hóa đơn VAT theo yêu cầu, không thu phí phục vụ. " +
                   "Không được mang thức ăn và đồ uống từ ngoài vào nhà hàng.",
                   "No deposit required; minimum booking from 2 adults. " +
                   "Maximum table hold time is 15 minutes from the reserved time. " +
                   "VAT invoices are available upon request; no service charge. " +
                   "Outside food and beverages are not permitted inside the restaurant.")),
            mapBlock(ContentSection.STORY, new double[]{105.7945, 21.0035})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3,  7,  of("Không áp dụng ưu đãi ngày 7/3", "Promotion not applicable on Mar 7")),
            blackoutGregorian(3,  8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9,  1,  of("Không áp dụng ưu đãi ngày 1/9", "Promotion not applicable on Sep 1")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(11, 20, of("Không áp dụng ưu đãi ngày 20/11", "Promotion not applicable on Nov 20")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 25, of("Không áp dụng ưu đãi ngày 25/12", "Promotion not applicable on Dec 25")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc.", "No deposit required."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng khi đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ và dịp đặc biệt.",
                "Promotions apply when booking through the system; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi không áp dụng đồng thời với các chương trình khuyến mãi khác tại nhà hàng.",
                "Promotions cannot be combined with other ongoing promotions at the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(2)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT và hóa đơn trực tiếp theo yêu cầu.",
                "The restaurant issues both VAT invoices and direct invoices upon request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Nhà hàng không thu phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ ngoài vào nhà hàng.", "Outside drinks are not permitted."))
            .outsideDrinkFees(List.of())
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
            .partnerId("11")
            .partnerType(PartnerType.INTERNAL_ADMIN)
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
                .id("tb-4")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn nướng tiêu chuẩn, phù hợp gia đình nhỏ và nhóm bạn.",
                    "Standard grill table, suitable for small families and friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
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
                .note(of("Bàn lớn ưu tiên phục vụ, phù hợp nhóm đông.", "Large priority-service table, suitable for bigger groups."))
                .build(),
            TableType.builder()
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 150))
                .note(of("Phòng riêng có máy chiếu, âm thanh ánh sáng, phù hợp tiệc công ty và sự kiện.",
                    "Private room with projector, audio-visual system, suitable for company parties and events."))
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
            .depositOnly(false)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Cầu Giấy", "Cau Giay District"))
            .wardName(of("P. Yên Hòa", "Yen Hoa Ward"))
            .addressLine(of("Gian hàng 1S11, Siêu thị GO! Thăng Long, 222 Trần Duy Hưng, P. Yên Hòa, Q. Cầu Giấy, Hà Nội",
                "Unit 1S11, GO! Thang Long Supermarket, 222 Tran Duy Hung, Yen Hoa Ward, Cau Giay District, Hanoi"))
            .location(new double[]{105.7945, 21.0035})
            .name(of("Buffet Nướng Yakimono - GO! Thăng Long", "Yakimono BBQ Buffet - GO! Thang Long"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng lẩu Nhật Bản đa suất từ 239K–479K/người ngay trong siêu thị GO! Thăng Long, Cầu Giấy.",
                "Multi-tier Japanese BBQ hotpot buffet from 239K–479K/person inside GO! Thang Long supermarket, Cau Giay."))
            .description(of("Yakimono - GO! Thăng Long là nhà hàng buffet nướng lẩu Nhật Bản tọa lạc trong siêu thị GO! Thăng Long, " +
                "số 222 Trần Duy Hưng, quận Cầu Giấy, Hà Nội. Với triết lý 'Quyền được ăn đồ Nhật ngon, giá rẻ, đảm bảo sức khỏe', " +
                "nhà hàng cung cấp 4 suất buffet từ 239.000đ đến 479.000đ/người với hàng trăm nguyên liệu tươi mỗi ngày, " +
                "bao gồm thịt bò Mỹ/Úc, hải sản nhập khẩu, rau củ và nước dùng Nhật đặc sắc.",
                "Yakimono - GO! Thang Long is a Japanese BBQ hotpot buffet restaurant located inside GO! Thang Long supermarket, " +
                "222 Tran Duy Hung, Cau Giay District, Hanoi. With the philosophy 'The right to eat quality Japanese food at affordable prices', " +
                "the restaurant offers 4 buffet tiers from 239,000 to 479,000 VND/person with hundreds of fresh ingredients daily, " +
                "including US/Australian beef, imported seafood, fresh vegetables and special Japanese broths."))
            .phone("0931006005")
            .bookingHotline("0931006005")
            .website("https://pasgo.vn/nha-hang/yakimono-go-thang-long-4215")
            .minPricePerPerson(new BigDecimal("239000"))
            .maxPricePerPerson(new BigDecimal("479000"))
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
