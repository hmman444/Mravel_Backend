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
public class SeedNhaHangCayDua1 {

    @Bean
    CommandLineRunner seedNhaHangCayDua1Runner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNhaHangCayDua1] start");

            RestaurantDoc doc = restaurantNhaHangCayDua1();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedNhaHangCayDua1] done");
        };
    }

    // =====================================================================
    //    NHÀ HÀNG CÂY DỪA 1 — 413 NGUYỄN TẤT THÀNH, TAM THUẬN, THANH KHÊ
    // =====================================================================

    private static RestaurantDoc restaurantNhaHangCayDua1() {
        String slug = "nha-hang-cay-dua-1-nguyen-tat-thanh";

        // ---------- Gallery ảnh nhà hàng (12 ảnh) ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-1-normal-159705120148.webp",
                of("Không gian Nhà hàng Cây Dừa 1 Nguyễn Tất Thành", "Cay Dua 1 restaurant on Nguyen Tat Thanh street"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-2-normal-159705220149.webp",
                of("Khu vực ăn hải sản tươi sống", "Fresh seafood dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-3-normal-159705320150.webp",
                of("Không gian ngoài trời ven biển phong cách cây dừa", "Outdoor beachside coconut tree style space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-4-normal-159705420151.webp",
                of("Bàn ăn nhóm đông gia đình", "Large family group dining tables"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-5-normal-159705520152.webp",
                of("Các món hải sản tươi đặc sắc", "Signature fresh seafood dishes"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-6-normal-159705620153.webp",
                of("Khu nướng hải sản ngoài trời", "Outdoor seafood grilling station"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-7-normal-159705720154.webp",
                of("Lẩu hải sản đặc trưng miền Trung", "Signature Central Vietnamese seafood hotpot"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-8-normal-159705820155.webp",
                of("Không gian ban tối lung linh ánh đèn", "Romantic evening atmosphere with warm lighting"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-9-normal-159705920156.webp",
                of("Tôm hùm và hải sản cao cấp", "Lobster and premium seafood"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-10-normal-159706020157.webp",
                of("Không gian bờ biển Đà Nẵng đặc sắc", "Da Nang seaside style dining space"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-11-normal-159706120158.webp",
                of("Khu vực tiếp đón và quầy bar", "Reception area and bar counter"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-cay-dua-1-nguyen-tat-thanh-slide-12-normal-159706220159.webp",
                of("Toàn cảnh Nhà hàng Cây Dừa 1", "Cay Dua 1 restaurant full overview"), false, 11)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản tươi sống", "Fresh seafood"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt đặc sản miền Trung", "Central Vietnamese specialties"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT_BBQ")
                .name(of("Lẩu & nướng hải sản", "Seafood hotpot & BBQ"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 23:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan công ty", "Company party")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm lớn", "Large group gathering")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("COMPANY_EVENT", of("Tiệc sự kiện doanh nghiệp", "Corporate event")),
            suitable("BUSINESS_MEAL", of("Tiếp đối tác hải sản", "Seafood business dining")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("TOURIST",       of("Du khách muốn thử hải sản Đà Nẵng", "Tourist experiencing Da Nang seafood"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",   of("Sôi động nhộn nhịp", "Lively and bustling")),
            ambience("COZY",      of("Ấm cúng gần gũi", "Cozy and welcoming")),
            ambience("RUSTIC",    of("Mộc mạc phong cách dừa biển", "Rustic coastal coconut style")),
            ambience("ROMANTIC",  of("Lãng mạn về đêm", "Romantic in the evening"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese"),
                of("Tôm hùm tươi nướng phủ phô mai tan chảy, vị béo ngậy đậm đà kết hợp với vị ngọt tự nhiên của tôm hùm.",
                   "Fresh lobster grilled with melted cheese, rich and creamy perfectly paired with lobster's natural sweetness."),
                new BigDecimal("450000")),
            signatureDish(of("Ghẹ rang muối sả ớt", "Crab stir-fried with salt, lemongrass and chili"),
                of("Ghẹ tươi rang muối sả ớt đặc trưng Đà Nẵng, thịt ghẹ chắc ngọt, vỏ giòn thơm hấp dẫn.",
                   "Fresh crab stir-fried in Da Nang's signature salt, lemongrass and chili style, firm sweet crabmeat with crispy aromatic shell."),
                new BigDecimal("180000")),
            signatureDish(of("Mực nướng sa tế", "Squid grilled with sa te sauce"),
                of("Mực ống tươi nướng than hoa phủ sa tế đặc vị miền Trung, cay thơm béo bùi giòn ngon.",
                   "Fresh squid grilled over charcoal with Central Vietnamese sa te, spicy aromatic and deliciously crispy."),
                new BigDecimal("120000")),
            signatureDish(of("Cua rang me", "Crab stir-fried with tamarind"),
                of("Cua biển tươi rang với sốt me nguyên chất, vị chua ngọt cân bằng hài hòa, thịt cua chắc béo.",
                   "Fresh sea crab stir-fried in pure tamarind sauce, perfectly balanced sweet and sour, firm creamy crabmeat."),
                new BigDecimal("200000")),
            signatureDish(of("Lẩu hải sản Đà Nẵng", "Da Nang seafood hotpot"),
                of("Nồi lẩu đầy ắp hải sản tươi — tôm, mực, nghêu, cua, cá — trong nước dùng chua cay đặc trưng miền Trung.",
                   "Hotpot brimming with fresh seafood — shrimp, squid, clams, crab, fish — in Central Vietnamese style spicy sour broth."),
                new BigDecimal("280000")),
            signatureDish(of("Cá mú hấp gừng hành", "Steamed grouper with ginger and scallion"),
                of("Cá mú tươi hấp gừng hành sốt xì dầu nhẹ, thịt cá trắng ngần ngọt mềm đặc trưng.",
                   "Fresh grouper steamed with ginger and scallion in light soy sauce, white tender and naturally sweet fish."),
                new BigDecimal("250000")),
            signatureDish(of("Nghêu xào sả ớt", "Clams stir-fried with lemongrass and chili"),
                of("Nghêu biển xào sả ớt tươi thơm, nước sốt đậm đà thấm vào từng con nghêu căng mọng.",
                   "Sea clams stir-fried with fresh lemongrass and chili, the rich sauce penetrating each plump clam."),
                new BigDecimal("80000")),
            signatureDish(of("Bạch tuộc nướng muối ớt", "Octopus grilled with salt and chili"),
                of("Bạch tuộc tươi nướng than hoa tẩm muối ớt, dai giòn thơm bùi, chấm muối tiêu chanh tuyệt vời.",
                   "Fresh octopus charcoal grilled with salt and chili seasoning, chewy crispy aromatic, excellent with salt-pepper-lime dip."),
                new BigDecimal("150000")),
            signatureDish(of("Tôm sú nướng muối sả", "Giant tiger prawns grilled with salt and lemongrass"),
                of("Tôm sú to tươi nướng muối sả, da tôm hồng giòn, thịt tôm ngọt thơm đậm vị biển.",
                   "Large fresh tiger prawns grilled with salt and lemongrass, crispy pink shell, sweet aromatic seafood-rich prawn meat."),
                new BigDecimal("120000")),
            signatureDish(of("Cơm chiên hải sản Đà Nẵng", "Da Nang seafood fried rice"),
                of("Cơm chiên với các loại hải sản tươi Đà Nẵng, hành, trứng và gia vị đặc trưng địa phương, hạt cơm bung giòn.",
                   "Fried rice with fresh Da Nang seafood, onions, eggs and local spices, with crispy puffed rice grains."),
                new BigDecimal("85000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HAI_SAN_NUONG")
                .name(of("Hải sản nướng than hoa", "Charcoal grilled seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Mực nướng sa tế", "Squid grilled with sa te"))
                        .description(of("Mực ống tươi nướng than sa tế cay thơm.", "Fresh squid charcoal grilled with sa te."))
                        .priceFrom(new BigDecimal("100000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("squid", "grill", "sa-te"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Tôm sú nướng muối sả", "Tiger prawns grilled with salt lemongrass"))
                        .description(of("Tôm sú to tươi nướng than hoa muối sả.", "Large tiger prawns charcoal grilled with salt lemongrass."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("prawn", "grill", "lemongrass"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HAI_SAN_XAO")
                .name(of("Hải sản xào & lẩu", "Stir-fried seafood & hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Ghẹ rang muối sả ớt", "Crab with salt lemongrass chili"))
                        .description(of("Ghẹ tươi rang muối sả ớt đặc trưng.", "Fresh crab in signature salt lemongrass chili."))
                        .priceFrom(new BigDecimal("160000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("crab", "stir-fry", "lemongrass"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu hải sản Đà Nẵng", "Da Nang seafood hotpot"))
                        .description(of("Lẩu hải sản tươi nước dùng chua cay miền Trung.", "Fresh seafood hotpot in Central Vietnamese sour-spicy broth."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood", "central"))
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
            "OUTDOOR_TABLE",
            "WIFI",
            "KIDS_CHAIR",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SMOKING_AREA"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước nhà hàng trên đường Nguyễn Tất Thành", "Parking lot in front of restaurant on Nguyen Tat Thanh street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè và bãi đỗ xe máy rộng trước nhà hàng", "Sidewalk and large motorbike lot in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Đường Nguyễn Tất Thành ven biển rộng rãi, đỗ xe ô tô và xe máy thoải mái.",
                      "Nguyen Tat Thanh seaside road is spacious with ample car and motorbike parking."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Nhà hàng Cây Dừa 1 — Hải sản tươi sống ven biển Đà Nẵng",
                   "Cay Dua 1 Restaurant — Fresh seafood by the sea in Da Nang")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Cây Dừa 1 Nguyễn Tất Thành là điểm đến ẩm thực hải sản nổi tiếng tọa lạc tại 413 Nguyễn Tất Thành, " +
                   "phường Tam Thuận, quận Thanh Khê, Đà Nẵng — tuyến đường ven biển sầm uất bậc nhất của thành phố. " +
                   "Với phong cách trang trí lấy cảm hứng từ hình ảnh cây dừa nhiệt đới, nhà hàng mang lại không khí " +
                   "biển cả mộc mạc, gần gũi, là điểm đến yêu thích cho bữa ăn hải sản tươi sống của cả gia đình địa phương " +
                   "lẫn du khách.",
                   "Cay Dua 1 Restaurant on Nguyen Tat Thanh Street is a well-known seafood destination at 413 Nguyen Tat Thanh, " +
                   "Tam Thuan Ward, Thanh Khe District, Da Nang — the city's liveliest seaside road. " +
                   "With decor inspired by tropical coconut palms, the restaurant evokes a rustic seaside atmosphere, " +
                   "making it a favorite dining spot for fresh seafood among both local families and visitors.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian rộng lớn sức chứa 250 khách với khu vực trong nhà điều hòa mát mẻ, " +
                   "khu vực ngoài trời thoáng đãng lộng gió biển và 3 phòng riêng sức chứa tối đa 40 khách mỗi phòng. " +
                   "Thiết kế theo phong cách nhiệt đới với cây dừa, đèn tre nứa và gam màu nâu vàng tạo cảm giác nghỉ dưỡng " +
                   "thư thái ngay giữa lòng thành phố biển.",
                   "The restaurant has a large capacity of 250 guests with air-conditioned indoor areas, breezy outdoor terraces " +
                   "facing the sea, and 3 private rooms each accommodating up to 40 guests. " +
                   "Tropical-style design with coconut palms, bamboo lanterns and warm brown-gold tones create a relaxing " +
                   "resort ambiance right in the heart of the coastal city.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Cây Dừa 1 đặc trưng bởi hải sản tươi sống đa dạng: từ tôm hùm nướng phô mai cao cấp, ghẹ rang " +
                   "muối sả ớt, mực nướng sa tế, cua rang me đến bạch tuộc nướng muối ớt và nghêu xào sả ớt. " +
                   "Lẩu hải sản Đà Nẵng với nước dùng chua cay đặc trưng miền Trung là món được đặt nhiều nhất. " +
                   "Ngoài hải sản, thực đơn còn có các món nướng, món Việt truyền thống và cơm chiên hải sản đặc sắc.",
                   "Cay Dua 1's menu is characterized by diverse fresh seafood: from premium grilled cheese lobster, " +
                   "salt-lemongrass-chili crab, sa te squid, tamarind crab, to salt-chili grilled octopus and lemongrass clams. " +
                   "Da Nang seafood hotpot with Central Vietnamese sour-spicy broth is the most ordered dish. " +
                   "Beyond seafood, the menu includes grilled items, traditional Vietnamese dishes and signature seafood fried rice.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Không gian rộng rãi với phòng riêng đủ sức chứa nhóm lớn, Nhà hàng Cây Dừa 1 là lựa chọn hoàn hảo cho " +
                   "tiệc công ty, liên hoan nhóm, sinh nhật và kỷ niệm đặc biệt. Mức giá 80.000 – 150.000đ/người (trung bình) " +
                   "cùng hải sản tươi chất lượng cao làm hài lòng cả thực khách bình dân lẫn khách hàng có nhu cầu cao cấp. " +
                   "Du khách đến Đà Nẵng sẽ có bữa hải sản khó quên ngay cạnh bờ biển.",
                   "With spacious premises including private rooms that fit large groups, Cay Dua 1 is the perfect choice for " +
                   "company parties, group gatherings, birthdays and special anniversaries. " +
                   "Pricing at 80,000–150,000 VND per person (average) with high-quality fresh seafood satisfies both budget " +
                   "and discerning diners. Visitors to Da Nang will enjoy an unforgettable seafood meal by the sea.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật", "Services & key amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp đầy đủ dịch vụ: MC chuyên nghiệp, trang trí sự kiện theo yêu cầu, âm thanh ánh sáng, " +
                   "máy chiếu cho phòng riêng, bàn ghế trẻ em, hóa đơn VAT và thanh toán thẻ Visa/Master. " +
                   "Bãi đỗ xe ô tô và xe máy rộng rãi miễn phí ngay trước nhà hàng. " +
                   "Khu vực ngoài trời với gió biển tự nhiên là điểm cộng lớn mà nhiều nhà hàng trong phố không có.",
                   "The restaurant provides comprehensive services: professional MC, custom event decoration, sound and lighting, " +
                   "projectors for private rooms, children's chairs, VAT invoices and Visa/Master card payment. " +
                   "Free spacious car and motorbike parking right in front. " +
                   "The outdoor area with natural sea breeze is a significant advantage most city-center restaurants cannot offer.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại 413 Nguyễn Tất Thành, phường Tam Thuận, quận Thanh Khê — đây là tuyến đường ven biển nổi tiếng " +
                   "chạy dọc bờ Tây thành phố Đà Nẵng. Cách sân bay quốc tế Đà Nẵng khoảng 3km, cách trung tâm thành phố " +
                   "khoảng 3.5km. Giao thông thuận tiện từ mọi khu vực, đặc biệt là từ khu du lịch Mỹ Khê, Bắc Mỹ An và " +
                   "trung tâm thành phố.",
                   "Located at 413 Nguyen Tat Thanh, Tam Thuan Ward, Thanh Khe District — this is the famous seaside road " +
                   "running along Da Nang's western coastline. About 3km from Da Nang International Airport and 3.5km from " +
                   "the city center. Conveniently accessible from all areas, especially from My Khe Beach, Bac My An " +
                   "tourist zone and the city center.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn", "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước với đặt cọc áp dụng cho nhóm từ 15 người trở lên hoặc sử dụng phòng riêng. " +
                   "Hóa đơn VAT xuất theo yêu cầu; phí dịch vụ 5% áp dụng cho phòng riêng. " +
                   "Lý do nên chọn Cây Dừa 1: hải sản tươi sống trực tiếp từ biển Đà Nẵng, không gian ven biển thoáng mát, " +
                   "đội ngũ phục vụ thân thiện chuyên nghiệp và thực đơn đa dạng từ bình dân đến cao cấp.",
                   "The restaurant accepts advance bookings with deposits required for groups of 15 or more or private room usage. " +
                   "VAT invoices issued on request; 5% service charge applies for private rooms. " +
                   "Why choose Cay Dua 1: fresh seafood directly sourced from Da Nang's sea, breezy seaside setting, " +
                   "friendly professional service team and a diverse menu ranging from budget to premium options.")),
            mapBlock(ContentSection.STORY, new double[]{108.207568, 16.076503})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1 Tết Dương lịch", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine 14/2", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày Quốc tế Lao động 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
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
                .feeAmount(new BigDecimal("250000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang ngoài vào: 250.000đ/chai.", "Spirits corkage fee: 250,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(15)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc áp dụng cho nhóm từ 15 khách trở lên hoặc khi sử dụng phòng riêng.",
                             "Deposit required for groups of 15 or more, or when booking private rooms."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt bàn qua hệ thống, không áp dụng vào các ngày lễ.",
                                 "Promotion applies to online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng liên hệ nhà hàng.",
                              "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu, thông báo trước khi thanh toán.",
                             "VAT invoices issued on request; notify before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí dịch vụ 5% áp dụng cho khu vực phòng riêng và sự kiện.",
                                   "A 5% service charge applies for private rooms and events."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Được phép mang đồ uống từ bên ngoài vào với phụ phí corkage.", "Outside drinks allowed with corkage fee."))
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
                .totalTables(25)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn khu vực chính, trong nhà hoặc ngoài trời.", "Standard table in main area, indoor or outdoor."))
                .build(),
            TableType.builder()
                .id("tb-10-vip")
                .name(of("Bàn VIP 10 người", "VIP table for 10"))
                .seats(10)
                .minPeople(6)
                .maxPeople(12)
                .totalTables(10)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP khu vực riêng biệt, phục vụ ưu tiên.", "VIP table in semi-private area with priority service."))
                .build(),
            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đầy đủ tiện nghi: máy chiếu, âm thanh ánh sáng, trang trí theo yêu cầu.",
                         "Fully equipped private room: projector, sound and lighting, custom decoration available."))
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
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Thanh Khê", "Thanh Khe District"))
            .wardName(of("Phường Tam Thuận", "Tam Thuan Ward"))
            .addressLine(of("413 Nguyễn Tất Thành, P. Tam Thuận, Q. Thanh Khê, TP. Đà Nẵng",
                            "413 Nguyen Tat Thanh Street, Tam Thuan Ward, Thanh Khe District, Da Nang City"))
            .location(new double[]{108.207568, 16.076503})
            .name(of("Nhà hàng Cây Dừa 1 Nguyễn Tất Thành", "Cay Dua 1 Restaurant Nguyen Tat Thanh"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Hải sản tươi sống đa dạng & lẩu nướng miền Trung ven biển Nguyễn Tất Thành, phong cách cây dừa nhiệt đới.",
                                 "Diverse fresh seafood & Central Vietnamese hotpot-BBQ by the sea on Nguyen Tat Thanh, tropical coconut palm style."))
            .description(of("Nhà hàng Cây Dừa 1 Nguyễn Tất Thành là điểm đến hải sản ven biển nổi tiếng tại 413 Nguyễn Tất Thành, " +
                            "phường Tam Thuận, quận Thanh Khê, Đà Nẵng. Chuyên phục vụ hải sản tươi sống từ tôm hùm, ghẹ, mực, " +
                            "bạch tuộc đến lẩu hải sản và nướng than hoa, nhà hàng mang đến trải nghiệm ẩm thực biển đích thực " +
                            "với không gian rộng rãi và phong cách cây dừa nhiệt đới đặc trưng.",
                            "Cay Dua 1 Restaurant on Nguyen Tat Thanh Street is a renowned seaside seafood destination at 413 Nguyen Tat Thanh, " +
                            "Tam Thuan Ward, Thanh Khe District, Da Nang. Specializing in fresh seafood from lobster, crab, squid, " +
                            "octopus to seafood hotpot and charcoal grilling, the restaurant delivers an authentic seaside dining experience " +
                            "with spacious premises and a distinctive tropical coconut palm style."))
            .phone("0765031082")
            .bookingHotline("0765031082")
            .minPricePerPerson(new BigDecimal("80000"))
            .maxPricePerPerson(new BigDecimal("150000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
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
