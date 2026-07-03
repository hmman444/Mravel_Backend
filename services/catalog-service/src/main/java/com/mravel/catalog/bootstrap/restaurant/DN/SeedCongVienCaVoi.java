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
public class SeedCongVienCaVoi {

    @Bean
    CommandLineRunner seedCongVienCaVoiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedCongVienCaVoi] start");
            RestaurantDoc doc = restaurantCongVienCaVoi();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedCongVienCaVoi] done");
        };
    }

    // =====================================================================
    //  CÔNG VIÊN – NHÀ HÀNG CÁ VOI — VÕ NGUYÊN GIÁP, PHƯỜNG MÂN THÁI, SƠN TRÀ
    // =====================================================================

    private static RestaurantDoc restaurantCongVienCaVoi() {
        String slug = "cong-vien-nha-hang-ca-voi-vo-nguyen-giap";

        // ---------- Gallery (18 ảnh không gian) ----------
        List<Image> images = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-1.jpg",
                of("Toàn cảnh Công Viên – Nhà Hàng Cá Voi Võ Nguyên Giáp", "Overview of Cong Vien Ca Voi Restaurant on Vo Nguyen Giap"), true, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-2.jpg",
                of("Không gian ăn hải sản rộng rãi ngoài trời", "Spacious outdoor seafood dining area"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-3.jpg",
                of("Khu vực bể cá tươi sống đa dạng", "Diverse live seafood tank area"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-4.jpg",
                of("Khu ăn uống ngoài trời thoáng mát ven biển", "Airy beachside outdoor dining zone"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-5.jpg",
                of("Bàn tiệc hải sản cho nhóm đông người", "Large group seafood banquet table"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-6.jpg",
                of("Không gian trong nhà mát mẻ có điều hòa", "Cool air-conditioned indoor dining space"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-7.jpg",
                of("Quầy hải sản tươi sống phong phú đa dạng", "Rich variety of fresh seafood at the display counter"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-8.jpg",
                of("Lẩu hải sản đặc sắc chuẩn vị miền Trung", "Signature Central Vietnamese seafood hotpot"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-9.JPG",
                of("Tôm hùm nướng phô mai đặc sản nhà hàng", "Restaurant's signature cheese-grilled lobster"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-10.JPG",
                of("Ghẹ xanh rang muối đặc trưng bờ biển Đà Nẵng", "Signature Da Nang salted blue crab"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-11.JPG",
                of("Mực nướng than hoa sa tế thơm phức", "Fragrant charcoal grilled squid with sa te"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-12.JPG",
                of("Phòng riêng sang trọng phục vụ tiệc sự kiện", "Elegant private room for event dining"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-13.jpg",
                of("Không gian đêm lung linh ánh đèn bờ biển", "Sparkling evening atmosphere by the sea"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-14.JPG",
                of("Bếp mở chế biến hải sản tươi tại chỗ", "Open kitchen preparing fresh seafood on-site"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-15.JPG",
                of("Cá mú tươi hấp sốt xì dầu gừng hành", "Fresh grouper steamed with soy sauce, ginger and scallion"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-16.jpg",
                of("Sân vườn nhà hàng trước mặt biển Mỹ Khê", "Restaurant garden facing My Khe beach"), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-17.JPG",
                of("Không gian tổng thể nhà hàng ban ngày", "Full restaurant view in daytime"), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/huyen/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-18.JPG",
                of("Khu tiếp đón và quầy trưng bày hải sản", "Welcome area and seafood display counter"), false, 17)
        );

        // ---------- Menu images (15 ảnh bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-2.jpg",
                of("Bảng giá hải sản tươi", "Fresh seafood price list"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-3.jpg",
                of("Menu tôm hùm và ghẹ", "Lobster and crab menu"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-4.jpg",
                of("Menu mực bạch tuộc", "Squid and octopus menu"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-5.jpg",
                of("Menu lẩu hải sản", "Seafood hotpot menu"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-6.jpg",
                of("Menu cá nướng và hấp", "Grilled and steamed fish menu"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-7.jpg",
                of("Menu combo gia đình", "Family combo menu"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-8.jpg",
                of("Menu rau và món phụ", "Vegetables and side dishes menu"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-9.jpg",
                of("Menu đồ uống và tráng miệng", "Beverages and desserts menu"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-10.jpg",
                of("Bảng giá cập nhật tháng hiện tại", "Current month updated price list"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-11.jpg",
                of("Menu nướng hải sản than hoa", "Charcoal grilled seafood menu"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-12.jpg",
                of("Menu ốc các loại", "Various shellfish menu"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-13.jpg",
                of("Menu cơm và súp hải sản", "Seafood rice and soup menu"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-14.jpg",
                of("Menu set tiệc VIP", "VIP banquet set menu"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-15.jpg",
                of("Menu combo nhóm 5-8 người", "Group combo menu for 5-8 people"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vuvu/MENU/congviencavoi/cong-vien-nha-hang-ca-voi-vo-nguyen-giap-16.jpg",
                of("Thực đơn đặc biệt theo mùa", "Special seasonal menu"), false, 14)
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
                .code("HOTPOT")
                .name(of("Lẩu hải sản", "Seafood hotpot"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa (09:00 - 22:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    9, 0, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   9, 0, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 9, 0, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  9, 0, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    9, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  9, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    9, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình đông người", "Large family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè hải sản", "Seafood friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan công ty", "Company party")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm lớn", "Large group gathering")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật hải sản", "Seafood birthday celebration")),
            suitable("COMPANY_EVENT", of("Sự kiện doanh nghiệp", "Corporate event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách hải sản cao cấp", "Premium seafood business dining")),
            suitable("TOURIST",       of("Du khách muốn thưởng thức hải sản Đà Nẵng", "Tourists experiencing Da Nang seafood")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt bên biển", "Special anniversary by the sea")),
            suitable("COUPLE",        of("Hẹn hò lãng mạn biển đêm", "Romantic seaside evening date"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",   of("Sôi động nhộn nhịp kiểu chợ biển", "Lively bustling seafood market style")),
            ambience("RUSTIC",    of("Mộc mạc phong cách biển miền Trung", "Rustic Central Vietnamese coastal style")),
            ambience("COZY",      of("Ấm cúng thoải mái cạnh bờ biển", "Cozy and comfortable by the sea")),
            ambience("ROMANTIC",  of("Lãng mạn dưới ánh đèn buổi tối", "Romantic under evening lights"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(350)
            .maxGroupSize(60)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese"),
                of("Tôm hùm tươi nướng than hoa phủ phô mai tan chảy, vị béo ngậy và ngọt tự nhiên của tôm hùm hòa quyện.",
                   "Fresh lobster charcoal grilled with melted cheese, rich creamy flavor perfectly blended with the natural sweetness of lobster."),
                new BigDecimal("500000")),
            signatureDish(of("Ghẹ xanh rang muối sả ớt", "Blue crab stir-fried with salt, lemongrass and chili"),
                of("Ghẹ xanh tươi rang muối sả ớt đặc trưng Đà Nẵng, thịt chắc ngọt, vỏ giòn hấp dẫn.",
                   "Fresh blue crab in Da Nang signature salt-lemongrass-chili style, firm sweet meat with irresistibly crispy shell."),
                new BigDecimal("200000")),
            signatureDish(of("Mực ống nướng sa tế", "Squid grilled with sa te sauce"),
                of("Mực ống tươi to nướng than hoa phủ sa tế cay thơm đặc vị miền Trung, ăn kèm bánh mì nướng.",
                   "Large fresh squid charcoal grilled with spicy aromatic Central Vietnamese sa te, served with toasted bread."),
                new BigDecimal("130000")),
            signatureDish(of("Cá mú hấp gừng hành sốt xì dầu", "Grouper steamed with ginger, scallion and soy sauce"),
                of("Cá mú tươi nguyên con hấp chín tới với gừng hành sốt xì dầu nhẹ, thịt trắng ngần ngọt mềm.",
                   "Whole fresh grouper perfectly steamed with ginger and scallion in light soy sauce, white tender and naturally sweet."),
                new BigDecimal("280000")),
            signatureDish(of("Lẩu hải sản tổng hợp Đà Nẵng", "Da Nang mixed seafood hotpot"),
                of("Nồi lẩu đầy ắp tôm, mực, nghêu, cua, cá trong nước dùng chua cay đặc trưng miền Trung.",
                   "Hotpot brimming with shrimp, squid, clams, crab and fish in signature Central Vietnamese spicy-sour broth."),
                new BigDecimal("320000")),
            signatureDish(of("Bạch tuộc nướng muối ớt", "Octopus grilled with salt and chili"),
                of("Bạch tuộc tươi nướng than hoa tẩm muối ớt đặc biệt, dai giòn thơm, chấm muối tiêu chanh tuyệt vời.",
                   "Fresh octopus charcoal grilled with special salt and chili seasoning, chewy crispy aromatic, great with salt-pepper-lime dip."),
                new BigDecimal("160000")),
            signatureDish(of("Nghêu xào sả ớt", "Clams stir-fried with lemongrass and chili"),
                of("Nghêu biển tươi xào sả ớt tươi thơm, nước sốt đậm đà thấm vào từng con nghêu căng mọng.",
                   "Fresh sea clams stir-fried with lemongrass and fresh chili, rich sauce penetrating each plump clam."),
                new BigDecimal("90000")),
            signatureDish(of("Cua rang me", "Crab stir-fried with tamarind"),
                of("Cua biển tươi rang với sốt me nguyên chất, vị chua ngọt cân bằng hài hòa, thịt cua chắc béo.",
                   "Fresh sea crab in pure tamarind sauce, perfectly balanced sweet-sour taste, firm and creamy crabmeat."),
                new BigDecimal("220000")),
            signatureDish(of("Tôm sú nướng muối sả", "Giant tiger prawns grilled with salt and lemongrass"),
                of("Tôm sú to tươi nướng than hoa muối sả, vỏ hồng giòn, thịt ngọt thơm đậm vị biển.",
                   "Large fresh tiger prawns charcoal grilled with salt and lemongrass, crispy pink shell, sweet aromatic seafood-rich meat."),
                new BigDecimal("130000")),
            signatureDish(of("Cơm chiên hải sản Đà Nẵng", "Da Nang seafood fried rice"),
                of("Cơm chiên với tôm, mực, cua tươi Đà Nẵng, hành, trứng và gia vị đặc trưng địa phương, hạt cơm bung giòn.",
                   "Fried rice with fresh Da Nang shrimp, squid, crab, onions, eggs and local spices, with perfectly crispy puffed grains."),
                new BigDecimal("90000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HAI_SAN_TUOI")
                .name(of("Hải sản tươi sống chế biến", "Fresh seafood dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tôm hùm nướng phô mai", "Grilled lobster with cheese"))
                        .description(of("Tôm hùm tươi nướng than hoa phô mai.", "Fresh lobster charcoal grilled with cheese."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("600000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("lobster", "grill", "cheese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Ghẹ xanh rang muối sả ớt", "Blue crab with salt lemongrass chili"))
                        .description(of("Ghẹ xanh tươi rang muối sả ớt.", "Fresh blue crab in salt lemongrass chili."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("240000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("crab", "stir-fry", "lemongrass"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("LAU_NUONG")
                .name(of("Lẩu & nướng hải sản", "Seafood hotpot & grill"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu hải sản tổng hợp", "Mixed seafood hotpot"))
                        .description(of("Lẩu hải sản đầy đặn nước dùng chua cay.", "Generous seafood hotpot with sour-spicy broth."))
                        .priceFrom(new BigDecimal("280000"))
                        .priceTo(new BigDecimal("380000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood", "central"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Mực ống nướng sa tế", "Squid grilled with sa te"))
                        .description(of("Mực ống tươi nướng than sa tế cay thơm.", "Fresh squid charcoal grilled with sa te."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("squid", "grill", "sa-te"))
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
            "SMOKING_AREA",
            "LIVE_SEAFOOD_TANK"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước nhà hàng trên đường Võ Nguyên Giáp", "Parking lot in front of the restaurant on Vo Nguyen Giap street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè rộng rãi trước nhà hàng và bãi xe riêng", "Wide sidewalk and dedicated motorbike lot in front"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Đường Võ Nguyên Giáp ven biển rộng rãi, dễ dàng đỗ xe ô tô và xe máy.",
                      "Vo Nguyen Giap beachside road is spacious with easy car and motorbike parking."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Công Viên – Nhà Hàng Cá Voi — Hải sản tươi sống ngay bờ biển Mỹ Khê",
                   "Cong Vien Ca Voi Restaurant — Fresh seafood right on My Khe Beach")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc ngay trên đường Võ Nguyên Giáp, đoạn giao với Vương Thừa Vũ thuộc phường Mân Thái, quận Sơn Trà — " +
                   "một trong những tuyến đường ven biển đẹp nhất Đà Nẵng — Công Viên – Nhà Hàng Cá Voi là địa chỉ hải sản " +
                   "tươi sống lâu năm và được nhiều thực khách tin yêu. Nhà hàng nằm cách bãi biển Mỹ Khê chỉ vài bước chân, " +
                   "mang đến không gian ẩm thực biển đích thực với gió lành và tiếng sóng biển nhẹ nhàng.",
                   "Located on Vo Nguyen Giap Street at the intersection with Vuong Thua Vu in Man Thai Ward, Son Tra District — " +
                   "one of Da Nang's most beautiful coastal roads — Cong Vien Ca Voi Restaurant is a long-standing fresh seafood " +
                   "destination beloved by diners. Just a few steps from My Khe Beach, the restaurant offers an authentic coastal " +
                   "dining experience with sea breezes and the gentle sound of waves.")),
            heading(ContentSection.STORY,
                of("Không gian & Sức chứa", "Space & Capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng có sức chứa lên đến 350 khách với khu vực ngoài trời thoáng mát, phòng điều hòa trong nhà và " +
                   "4 phòng riêng có thể phục vụ nhóm đến 50 người. Thiết kế mang phong cách dân gian ven biển, với các bể cá " +
                   "sống rộng lớn trưng bày đầy đủ các loại hải sản tươi nhập hằng ngày từ ngư dân địa phương và cảng cá Thuận Phước.",
                   "The restaurant accommodates up to 350 guests across breezy outdoor areas, air-conditioned indoor sections and " +
                   "4 private rooms each seating up to 50 people. The coastal folk-style design features large live seafood tanks " +
                   "displaying a full variety of fresh seafood sourced daily from local fishermen and Thuan Phuoc fishing port.")),
            heading(ContentSection.STORY,
                of("Thực đơn & Món đặc sắc", "Menu & Signature Dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn phong phú với hơn 80 món, từ tôm hùm nướng phô mai đặc sản đến ghẹ xanh rang muối sả ớt, " +
                   "mực ống nướng sa tế, cá mú hấp gừng hành, bạch tuộc nướng muối ớt và lẩu hải sản tổng hợp nước dùng " +
                   "chua cay đặc trưng miền Trung. Mỗi ngày nhà hàng nhập hải sản 2 lần để đảm bảo độ tươi sống tuyệt đối.",
                   "The extensive menu features over 80 dishes, from signature cheese-grilled lobster to blue crab in salt-lemongrass-chili, " +
                   "squid grilled with sa te, grouper steamed with ginger and scallion, octopus grilled with salt and chili, " +
                   "and the signature mixed seafood hotpot in Central Vietnamese spicy-sour broth. Seafood is restocked twice daily " +
                   "to guarantee absolute freshness.")),
            heading(ContentSection.STORY,
                of("Phù hợp cho dịp nào?", "Ideal Occasions")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng phù hợp cho mọi dịp: từ bữa ăn gia đình cuối tuần, tiệc sinh nhật, liên hoan công ty đến tiếp " +
                   "khách đối tác và tiệc sự kiện. Đặc biệt, du khách đến Đà Nẵng nhất định phải thử một lần thưởng thức " +
                   "hải sản tươi sống tại đây với khung cảnh biển tuyệt đẹp ngay bên cạnh.",
                   "The restaurant suits every occasion: weekend family meals, birthday parties, company dinners, business entertainment " +
                   "and event banquets. In particular, visitors to Da Nang should try at least one fresh seafood meal here, " +
                   "with a stunning beachside setting right alongside.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & Tiện ích nổi bật", "Services & Key Amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị đầy đủ: máy chiếu, hệ thống âm thanh ánh sáng, dịch vụ MC và trang trí theo yêu cầu " +
                   "cho các sự kiện đặc biệt. Có ghế trẻ em, khu hút thuốc riêng và hỗ trợ xuất hóa đơn VAT theo yêu cầu. " +
                   "Đội ngũ phục vụ chuyên nghiệp, nhiệt tình, sẵn sàng tư vấn món và hỗ trợ khách chọn hải sản trực tiếp " +
                   "từ bể cá sống.",
                   "The restaurant is fully equipped with projector, sound and lighting systems, MC service and custom decoration " +
                   "for special events. Children's chairs, a dedicated smoking area and VAT invoice issuance are all available. " +
                   "Professional and enthusiastic staff are ready to recommend dishes and help guests select seafood directly " +
                   "from the live tanks.")),
            heading(ContentSection.STORY,
                of("Vị trí & Di chuyển", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm trên đường Võ Nguyên Giáp – tuyến đường ven biển chính nối trung tâm thành phố Đà Nẵng với " +
                   "bán đảo Sơn Trà. Cách cầu Rồng khoảng 5 phút lái xe, dễ dàng tiếp cận bằng ô tô, xe máy hoặc xe điện " +
                   "ven biển. Bãi đỗ xe ô tô và xe máy rộng rãi ngay trước cổng, hoàn toàn miễn phí.",
                   "The restaurant is on Vo Nguyen Giap Street — the main coastal road connecting Da Nang city centre to the " +
                   "Son Tra peninsula. About 5 minutes by car from Dragon Bridge, easily accessible by car, motorbike or the " +
                   "beachside electric vehicle. Wide free car and motorbike parking is available right at the entrance.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & Đặt cọc", "Booking & Deposit Policy")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước được khuyến khích cho nhóm từ 10 khách trở lên, đặc biệt vào cuối tuần và mùa du lịch hè. " +
                   "Nhóm từ 15 người yêu cầu đặt cọc. Phòng riêng cần đặt trước ít nhất 24 giờ. Nhà hàng hỗ trợ tổ chức " +
                   "sự kiện trọn gói bao gồm menu đặt sẵn, trang trí và dịch vụ MC.",
                   "Advance booking is recommended for groups of 10 or more, especially on weekends and summer tourist season. " +
                   "Groups of 15 or more require a deposit. Private rooms must be booked at least 24 hours in advance. " +
                   "The restaurant offers full event planning including set menus, decoration and MC services."))
        );

        // ---------- Policy ----------
        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(15)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc áp dụng cho nhóm từ 15 khách trở lên hoặc khi đặt phòng riêng.",
                             "Deposit required for groups of 15 or more, or when booking private rooms."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi khi đặt bàn qua hệ thống, không áp dụng vào các ngày lễ.",
                                 "Promotion for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng liên hệ nhà hàng.",
                              "Promotion details vary; please contact the restaurant for current offers."))
            .blackoutRules(List.of(
                blackoutGregorian(1, 1, of("Tết Dương Lịch — nhà hàng đóng cửa", "New Year's Day — restaurant closed")),
                blackoutGregorian(4, 30, of("Ngày 30/4 — đông khách, cần đặt trước", "April 30 — high demand, pre-booking required"))
            ))
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu, thông báo trước khi thanh toán.",
                             "VAT invoices on request; notify before paying."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí 5% cho khu vực phòng riêng và sự kiện đặc biệt.",
                                   "5% service charge applies for private rooms and special events."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Được phép mang đồ uống từ bên ngoài với phụ phí corkage.", "Outside drinks allowed with corkage fee."))
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
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("cv-tb-4")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(5)
                .totalTables(30)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn khu vực ngoài trời hoặc trong nhà.", "Standard table in outdoor or indoor area."))
                .build(),
            TableType.builder()
                .id("cv-tb-10-vip")
                .name(of("Bàn VIP 10 người", "VIP table for 10"))
                .seats(10)
                .minPeople(6)
                .maxPeople(12)
                .totalTables(12)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP khu vực bán riêng tư, phục vụ ưu tiên.", "VIP table in semi-private area with priority service."))
                .build(),
            TableType.builder()
                .id("cv-pr-50")
                .name(of("Phòng riêng 50 khách", "Private room for 50 guests"))
                .seats(50)
                .minPeople(20)
                .maxPeople(50)
                .totalTables(4)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đủ tiện nghi: máy chiếu, âm thanh ánh sáng, trang trí theo yêu cầu.",
                         "Fully equipped private room: projector, sound and lighting, decoration on request."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(120)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(60)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Sơn Trà", "Son Tra District"))
            .wardName(of("Phường Mân Thái", "Man Thai Ward"))
            .addressLine(of("Võ Nguyên Giáp – Vương Thừa Vũ, P. Mân Thái, Q. Sơn Trà, TP. Đà Nẵng",
                            "Vo Nguyen Giap – Vuong Thua Vu, Man Thai Ward, Son Tra District, Da Nang City"))
            .location(new double[]{108.246938, 16.079996})
            .name(of("Công Viên – Nhà Hàng Cá Voi", "Cong Vien Ca Voi Seafood Restaurant"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Hải sản tươi sống đa dạng ngay bờ biển Mỹ Khê — Võ Nguyên Giáp, không gian rộng lớn, phòng riêng đầy đủ tiện nghi.",
                                 "Diverse fresh seafood right on My Khe Beach — Vo Nguyen Giap, spacious setting with fully equipped private rooms."))
            .description(of("Công Viên – Nhà Hàng Cá Voi tọa lạc trên đường Võ Nguyên Giáp, phường Mân Thái, quận Sơn Trà — " +
                            "cung cấp hải sản tươi sống nhập trực tiếp từ ngư dân và cảng cá hằng ngày. Với sức chứa 350 khách " +
                            "và 4 phòng riêng, nhà hàng là lựa chọn hoàn hảo cho mọi dịp ẩm thực biển tại Đà Nẵng.",
                            "Cong Vien Ca Voi Restaurant on Vo Nguyen Giap Street, Man Thai Ward, Son Tra District — " +
                            "sources fresh seafood daily from local fishermen and the fishing port. With capacity for 350 guests " +
                            "and 4 private rooms, it is the perfect choice for any coastal dining occasion in Da Nang."))
            .phone("0799961082")
            .bookingHotline("0799961082")
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
            .menuSections(menuSections)
            .parking(parking)
            .amenityCodes(amenityCodes)
            .images(images)
            .menuImages(menuImages)
            .content(content)
            .policy(policy)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}
