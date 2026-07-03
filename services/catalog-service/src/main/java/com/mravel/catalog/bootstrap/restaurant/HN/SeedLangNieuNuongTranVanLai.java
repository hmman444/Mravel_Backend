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
public class SeedLangNieuNuongTranVanLai {

    @Bean
    CommandLineRunner seedLangNieuNuongTranVanLaiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLangNieuNuongTranVanLai] start");

            RestaurantDoc doc = restaurantLangNieuNuongTranVanLai();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLangNieuNuongTranVanLai] done");
        };
    }

    // =====================================================================
    //         LÀNG NIÊU & NƯỚNG TRẦN VĂN LAI - MỸ ĐÌNH (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantLangNieuNuongTranVanLai() {
        String slug = "lang-nieu-nuong-tran-van-lai";

        // ---------- Gallery ảnh nhà hàng (12 ảnh) ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-1-normal-131410316072.webp",
                of("Không gian Làng Niêu & Nướng Trần Văn Lai", "Lang Nieu & Nuong Tran Van Lai space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-2-normal-131410416073.webp",
                of("Khu vực nướng tại bàn", "Grilling area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-3-normal-131410516074.webp",
                of("Không gian trong nhà hàng", "Restaurant interior"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-4-normal-131410616075.webp",
                of("Khu vực gia đình", "Family seating area"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-5-normal-131410716076.webp",
                of("Không gian rộng rãi", "Spacious dining area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-6-normal-131410816077.webp",
                of("Trang trí mộc mạc truyền thống", "Traditional rustic decor"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-7-normal-131410916078.webp",
                of("Bàn ăn nhóm đông", "Large group dining table"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-8-normal-131411216079.webp",
                of("Khu vực phòng riêng", "Private room area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-9-normal-131411416080.webp",
                of("Món nướng đặc sắc", "Signature grilled dishes"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-10-normal-131411516081.webp",
                of("Cơm niêu đất truyền thống", "Traditional clay pot rice"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-11-normal-131411716083.webp",
                of("Không gian sân vườn", "Garden dining space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lang-nieu-nuong-tran-van-lai-slide-12-normal-131411816084.webp",
                of("Toàn cảnh nhà hàng", "Restaurant overview"), false, 11)
        );

        // ---------- Ảnh menu (16 ảnh bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/lent/langnieunuong%20TVL/16.jpg",
                of("", ""), false, 15)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_BBQ")
                .name(of("Nướng Việt Nam", "Vietnamese BBQ"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("CLAY_POT_RICE")
                .name(of("Cơm niêu đất", "Clay pot rice"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt truyền thống", "Traditional Vietnamese"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 22:00 cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm đông", "Large group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp đối tác", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",    of("Mộc mạc làng quê", "Rustic village style")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("TRADITIONAL", of("Truyền thống Việt Nam", "Traditional Vietnamese"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (9 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Cơm niêu đất than hoa", "Charcoal clay pot rice"),
                of("Cơm được nấu trong niêu đất nung trên than hoa, vỏ ngoài giòn vàng, bên trong dẻo thơm đặc trưng.",
                   "Rice cooked in clay pots over charcoal, crispy golden exterior with fragrant tender interior."),
                new BigDecimal("59000")),
            signatureDish(of("Bò nướng lá lốt", "Beef wrapped in lolot leaves"),
                of("Thịt bò xay trộn gia vị gói lá lốt nướng than, thơm bùi đậm đà.",
                   "Minced spiced beef wrapped in lolot leaves, grilled over charcoal for rich aromatic flavor."),
                new BigDecimal("85000")),
            signatureDish(of("Sườn nướng mật ong", "Honey-glazed grilled ribs"),
                of("Sườn non tẩm ướp mật ong và gia vị, nướng than giòn bên ngoài, ngọt thơm bên trong.",
                   "Baby ribs marinated in honey and spices, charcoal-grilled to crispy outside with sweet aromatic inside."),
                new BigDecimal("120000")),
            signatureDish(of("Gà nướng muối ớt", "Salt and chili grilled chicken"),
                of("Gà nguyên con hoặc theo phần tẩm muối ớt nướng than, da giòn thịt mềm.",
                   "Whole or portion chicken marinated in salt and chili, charcoal-grilled with crispy skin and tender meat."),
                new BigDecimal("95000")),
            signatureDish(of("Lợn mán nướng", "Grilled mountain pork"),
                of("Thịt lợn mán từ vùng cao, thịt chắc và ngọt tự nhiên, nướng than hoa nghi ngút khói.",
                   "Mountain pig pork from highland, naturally firm and sweet, grilled over smoky charcoal."),
                new BigDecimal("110000")),
            signatureDish(of("Cá lăng nướng giấy bạc", "Grilled catfish in foil"),
                of("Cá lăng tươi ướp sả ớt gói giấy bạc nướng than, giữ trọn vị ngọt của cá.",
                   "Fresh catfish marinated with lemongrass and chili, foil-wrapped and charcoal-grilled to lock in natural sweetness."),
                new BigDecimal("150000")),
            signatureDish(of("Nem nướng Ninh Hòa", "Ninh Hoa grilled pork roll"),
                of("Nem nướng đặc sản cuốn bánh tráng, rau sống và chấm tương chao đặc trưng.",
                   "Specialty grilled pork rolls served with rice paper, fresh vegetables and signature fermented tofu dipping sauce."),
                new BigDecimal("65000")),
            signatureDish(of("Rau rừng luộc chấm mắm tép", "Boiled forest vegetables with shrimp paste"),
                of("Các loại rau rừng tươi luộc thanh mát, chấm mắm tép đặc sản Hà Nội.",
                   "Fresh forest greens lightly boiled, served with Hanoi specialty shrimp paste dipping sauce."),
                new BigDecimal("45000")),
            signatureDish(of("Canh chua cá lóc niêu đất", "Sour soup with snakehead fish in clay pot"),
                of("Canh chua đặc trưng miền Bắc nấu cùng cá lóc tươi trong niêu đất, chua thanh dịu.",
                   "Northern-style sour soup cooked with fresh snakehead fish in clay pot, mildly tangy and refreshing."),
                new BigDecimal("75000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("NUONG")
                .name(of("Đặc sản nướng than hoa", "Charcoal grilled specialties"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò nướng lá lốt", "Beef wrapped in lolot leaves"))
                        .description(of("Thịt bò xay gói lá lốt nướng than hoa.", "Minced beef in lolot leaves, charcoal grilled."))
                        .priceFrom(new BigDecimal("75000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "grill", "lolot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Sườn nướng mật ong", "Honey-glazed grilled ribs"))
                        .description(of("Sườn non tẩm mật ong nướng than giòn.", "Baby ribs with honey glaze, charcoal grilled."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork", "ribs", "grill"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("NIEU_DAT")
                .name(of("Cơm niêu đất & món kèm", "Clay pot rice & side dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cơm niêu đất than hoa", "Charcoal clay pot rice"))
                        .description(of("Cơm nấu niêu đất trên than hoa, vỏ giòn thơm.", "Clay pot rice on charcoal, crispy fragrant crust."))
                        .priceFrom(new BigDecimal("49000"))
                        .priceTo(new BigDecimal("69000"))
                        .unit(of("niêu", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("rice", "clay-pot", "charcoal"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Canh chua cá lóc niêu đất", "Sour soup with snakehead fish"))
                        .description(of("Canh chua Bắc nấu cá lóc trong niêu đất.", "Northern sour soup with snakehead fish in clay pot."))
                        .priceFrom(new BigDecimal("65000"))
                        .priceTo(new BigDecimal("85000"))
                        .unit(of("niêu", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("soup", "fish", "clay-pot"))
                        .build()
                ))
                .build()
        );

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "FREE_CAR_PARKING",
            "PRIVATE_ROOM",
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SET_LUNCH",
            "OUTDOOR_TABLE",
            "SMOKING_AREA"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trong khuôn viên KĐT Mỹ Đình Sông Đà", "Parking lot within My Dinh Song Da urban area"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi đỗ xe máy trước nhà hàng", "Motorbike parking in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Bãi đỗ xe rộng rãi miễn phí ngay trong khuôn viên khu đô thị, có bảo vệ trông xe.",
                      "Spacious free parking lot in the urban area complex, with security staff on duty."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Làng Niêu & Nướng Trần Văn Lai — Hương vị làng quê giữa lòng Mỹ Đình",
                   "Lang Nieu & Nuong Tran Van Lai — Village flavors in the heart of My Dinh")),
            paragraph(ContentSection.STORY,
                of("Làng Niêu & Nướng Trần Văn Lai là điểm đến ẩm thực mang hồn quê đặc sắc tọa lạc tại KĐT Mỹ Đình Sông Đà, " +
                   "phố Trần Văn Lại, quận Nam Từ Liêm, Hà Nội. Nhà hàng kết hợp tinh tế giữa hai thế giới ẩm thực: " +
                   "cơm niêu đất nấu than hoa truyền thống và nướng than hoa đặc sản, tạo nên trải nghiệm ẩm thực độc đáo " +
                   "mang đậm phong vị miền Bắc Việt Nam.",
                   "Lang Nieu & Nuong Tran Van Lai is a distinctive countryside-inspired dining destination located in My Dinh Song Da urban area, " +
                   "Tran Van Lai Street, Nam Tu Liem District, Hanoi. The restaurant elegantly combines two culinary worlds: " +
                   "traditional clay pot rice cooked over charcoal and specialty charcoal-grilled dishes, " +
                   "creating a unique dining experience deeply rooted in Northern Vietnamese cuisine.")),
            heading(ContentSection.STORY,
                of("Không gian mộc mạc & sức chứa", "Rustic space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được thiết kế theo phong cách làng quê Bắc Bộ với vật liệu gỗ tự nhiên, đèn lồng tre nứa và " +
                   "những bức tranh gốm sứ mang hình ảnh làng quê yên bình. Sức chứa lên đến 300 khách với 5 phòng riêng " +
                   "sang trọng sức chứa mỗi phòng tới 50 người, phù hợp cho tiệc công ty, sự kiện và tiếp đối tác.",
                   "The restaurant is designed in Northern Vietnamese countryside style with natural wood, bamboo lanterns and " +
                   "ceramic paintings depicting peaceful village scenes. With capacity for up to 300 guests and 5 private rooms " +
                   "each accommodating up to 50 people, it suits company parties, events and business entertainment.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Điểm nhấn của thực đơn là cơm niêu đất nấu trên than hoa thật sự — mỗi niêu cơm đều có lớp cơm cháy " +
                   "vàng giòn đặc trưng, thơm lừng quyến rũ. Bên cạnh đó là hệ thống nướng than hoa phong phú: bò nướng lá lốt, " +
                   "sườn nướng mật ong, gà muối ớt, lợn mán, cá lăng giấy bạc — tất cả đều được chế biến từ nguyên liệu tươi " +
                   "chọn lọc mỗi ngày.",
                   "The menu highlight is genuine charcoal clay pot rice — each pot features the characteristic crispy golden " +
                   "burnt rice crust with irresistibly fragrant aroma. Complemented by a rich charcoal grill selection: " +
                   "beef in lolot leaves, honey-glazed ribs, salt-chili chicken, mountain pork, foil-grilled catfish — " +
                   "all prepared from fresh, daily-selected ingredients.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian rộng rãi, phong cách mộc mạc ấm cúng và thực đơn phong phú, Làng Niêu & Nướng Trần Văn Lai " +
                   "là lựa chọn hoàn hảo cho bữa ăn gia đình cuối tuần, tụ tập bạn bè, tiệc sinh nhật và liên hoan công ty. " +
                   "Đặc biệt các phòng riêng có thể trang trí theo yêu cầu, lắp đặt máy chiếu và dàn âm thanh ánh sáng " +
                   "phục vụ hội họp và sự kiện doanh nghiệp.",
                   "With spacious surroundings, warm rustic style and a rich menu, Lang Nieu & Nuong Tran Van Lai is the perfect choice " +
                   "for weekend family meals, friends gatherings, birthday parties and company celebrations. " +
                   "Private rooms can be specially decorated on request, equipped with projectors and sound-light systems " +
                   "for corporate meetings and business events.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật", "Services & key amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp đầy đủ các dịch vụ: MC chuyên nghiệp, trang trí sự kiện theo yêu cầu, âm thanh ánh sáng, " +
                   "bàn ghế trẻ em, hóa đơn VAT và thanh toán thẻ Visa/Master. Bãi đỗ xe rộng rãi miễn phí trong khuôn viên " +
                   "khu đô thị Mỹ Đình Sông Đà là ưu điểm nổi bật so với nhiều nhà hàng trong khu vực.",
                   "The restaurant provides comprehensive services: professional MC, custom event decoration, sound and lighting systems, " +
                   "children's chairs, VAT invoices and Visa/Master card payment. " +
                   "Spacious free parking within the My Dinh Song Da urban complex is a standout advantage over many area restaurants.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại KĐT Mỹ Đình Sông Đà, số 4 TT4, phố Trần Văn Lại, quận Nam Từ Liêm, nhà hàng nằm trong " +
                   "khu đô thị văn minh, sầm uất của Hà Nội. Chỉ cách Trung tâm Hội nghị Quốc gia khoảng 2km, " +
                   "cách sân vận động Mỹ Đình 1.5km — rất thuận tiện di chuyển từ các tuyến đường lớn như Phạm Hùng, " +
                   "Lê Đức Thọ và đường vành đai 3.",
                   "Located in My Dinh Song Da urban area, No. 4 TT4, Tran Van Lai Street, Nam Tu Liem District, " +
                   "the restaurant sits in one of Hanoi's most vibrant and modern urban zones. " +
                   "Just 2km from the National Convention Center and 1.5km from My Dinh Stadium, " +
                   "it is conveniently accessible from major roads including Pham Hung, Le Duc Tho and Ring Road 3.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn", "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước với chính sách đặt cọc linh hoạt cho nhóm đông từ 10 người trở lên. " +
                   "Hóa đơn VAT được xuất theo yêu cầu; phí dịch vụ áp dụng cho phòng riêng. " +
                   "Đặt bàn qua hệ thống để nhận nhiều ưu đãi hấp dẫn và giữ chỗ trước cho các buổi cuối tuần và dịp lễ " +
                   "luôn đông khách.",
                   "The restaurant accepts advance bookings with a flexible deposit policy for groups of 10 or more. " +
                   "VAT invoices are issued on request; service charges apply for private rooms. " +
                   "Book via the system to receive attractive offers and secure your spot for busy weekend and holiday sessions.")),
            mapBlock(ContentSection.STORY, new double[]{105.7720, 21.0278})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1 Tết Dương lịch", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine 14/2", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày Quốc tế Lao động 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh 24/12", "Promotion not applicable on Dec 24")),
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
            .depositNotes(of("Đặt cọc áp dụng cho nhóm từ 10 khách trở lên hoặc có yêu cầu trang trí sự kiện.",
                             "Deposit applies for groups of 10 or more guests, or when special event decoration is requested."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt bàn qua hệ thống, không áp dụng vào các ngày lễ và dịp đặc biệt.",
                                 "Promotion applies to guests booking via the system; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng liên hệ nhà hàng để biết thêm.",
                              "Promotion details vary by time; please contact the restaurant for more information."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng xuất hóa đơn VAT theo yêu cầu, vui lòng thông báo trước.",
                             "The restaurant issues VAT invoices on request; please notify in advance."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí dịch vụ 5% áp dụng cho khu vực phòng riêng và sự kiện.",
                                   "A 5% service charge applies for private rooms and events."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào nhà hàng.",
                                  "Outside food is not permitted in the restaurant."))
            .outsideDrinkPolicy(of("Được phép mang đồ uống từ bên ngoài vào, có phụ phí corkage theo loại.",
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

        // ---------- Table types (3 loại bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(30)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn khu vực chính.", "Standard table in the main dining area."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 người", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP không gian riêng biệt, phục vụ ưu tiên.", "VIP table with semi-private space, priority service."))
                .build(),
            TableType.builder()
                .id("pr-50")
                .name(of("Phòng riêng 50 khách", "Private room for 50 guests"))
                .seats(50)
                .minPeople(20)
                .maxPeople(50)
                .totalTables(5)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đầy đủ tiện nghi, trang trí theo yêu cầu, có máy chiếu và âm thanh.",
                         "Fully equipped private room, custom decoration available, with projector and sound system."))
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
            .maxTablesPerBooking(6)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Nam Từ Liêm", "Nam Tu Liem District"))
            .wardName(of("KĐT Mỹ Đình Sông Đà", "My Dinh Song Da Urban Area"))
            .addressLine(of("Số 4, TT4, Phố Trần Văn Lại, KĐT Mỹ Đình Sông Đà, Q. Nam Từ Liêm, Hà Nội",
                            "No. 4, TT4, Tran Van Lai Street, My Dinh Song Da Urban Area, Nam Tu Liem District, Hanoi"))
            .location(new double[]{105.7720, 21.0278})
            .name(of("Làng Niêu & Nướng Trần Văn Lai", "Lang Nieu & Nuong Tran Van Lai"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Cơm niêu đất than hoa & nướng đặc sản phong cách làng quê Bắc Bộ giữa khu đô thị Mỹ Đình.",
                                 "Charcoal clay pot rice & specialty grill in Northern Vietnamese village style at My Dinh urban area."))
            .description(of("Làng Niêu & Nướng Trần Văn Lai là nhà hàng đặc sản nướng và cơm niêu đất truyền thống " +
                            "tọa lạc tại KĐT Mỹ Đình Sông Đà, quận Nam Từ Liêm, Hà Nội. " +
                            "Kết hợp tinh tế giữa cơm niêu đất nấu than hoa với hương vị giòn thơm đặc trưng " +
                            "và các món nướng than hoa đặc sản Bắc Bộ, nhà hàng mang lại trải nghiệm ẩm thực " +
                            "chân thực mộc mạc giữa lòng đô thị hiện đại.",
                            "Lang Nieu & Nuong Tran Van Lai is a traditional clay pot rice and specialty grill restaurant " +
                            "located in My Dinh Song Da urban area, Nam Tu Liem District, Hanoi. " +
                            "Combining the distinctive crispy-fragrant charcoal clay pot rice with specialty Northern Vietnamese " +
                            "charcoal-grilled dishes, the restaurant delivers an authentic rustic dining experience " +
                            "in the heart of the modern city."))
            .phone("0404835654")
            .website("https://pasgo.vn/nha-hang/lang-nieu-va-nuong-tran-van-lai")
            .bookingHotline("0404835654")
            .minPricePerPerson(new BigDecimal("90000"))
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
