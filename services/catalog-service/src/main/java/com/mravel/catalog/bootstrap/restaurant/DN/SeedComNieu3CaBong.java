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
public class SeedComNieu3CaBong {

    @Bean
    CommandLineRunner seedComNieu3CaBongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedComNieu3CaBong] start");

            RestaurantDoc doc = restaurantComNieu3CaBong();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedComNieu3CaBong] done");
        };
    }

    // =====================================================================
    //    CƠM NIÊU 3 CÁ BỐNG — 112 NGUYỄN TRI PHƯƠNG, THANH KHÊ, ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantComNieu3CaBong() {
        String slug = "com-nieu-3-ca-bong-nguyen-tri-phuong";

        // ---------- Gallery ảnh nhà hàng (12 ảnh) ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-1-normal-503616729882.webp",
                of("Không gian Cơm Niêu 3 Cá Bống Nguyễn Tri Phương", "Com Nieu 3 Ca Bong restaurant space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-2-normal-503617429883.webp",
                of("Khu vực ăn uống chính ấm cúng", "Warm main dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-3-normal-503617729884.webp",
                of("Bàn ăn gia đình truyền thống", "Traditional family dining tables"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-4-normal-503617829885.webp",
                of("Cơm niêu đất đặc trưng", "Signature clay pot rice"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-5-normal-503618129886.webp",
                of("Mâm cơm dân dã đầy đủ", "Complete homestyle Vietnamese spread"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-6-normal-503619229887.webp",
                of("Cá bống kho tiêu đặc sản", "Specialty braised goby fish"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-7-normal-503619929888.webp",
                of("Không gian thoáng đãng ban ngày", "Airy daytime dining space"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-8-normal-503620429889.webp",
                of("Nhóm bạn bè thưởng thức bữa cơm niêu", "Friends enjoying clay pot rice meal"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-9-normal-503620829890.webp",
                of("Trang trí nội thất mộc mạc gần gũi", "Rustic and homey interior decoration"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-10-normal-503621329891.webp",
                of("Món canh chua cá đặc trưng miền Trung", "Central Vietnamese sour fish soup"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-11-normal-503622229892.webp",
                of("Bàn ăn nhóm đông ấm cúng", "Cozy large group dining table"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-com-nieu-3-ca-bong-nguyen-tri-phuong-12-normal-503622729893.webp",
                of("Toàn cảnh nhà hàng Cơm Niêu 3 Cá Bống", "Com Nieu 3 Ca Bong restaurant overview"), false, 11)
        );

        // ---------- Ảnh menu (14 ảnh bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/comnieu3cabongg/14.jpg",
                of("", ""), false, 13)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("CLAY_POT_RICE")
                .name(of("Cơm niêu đất", "Clay pot rice"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt truyền thống", "Traditional Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("BRAISED_FISH")
                .name(of("Cá kho đặc sản", "Specialty braised fish"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa (10:30 - 21:30, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 21, 30),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 21, 30),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 21, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 21, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 21, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COUPLE",        of("Hẹn hò bình dân", "Casual date")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm", "Group gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan nhỏ", "Small company party")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("SOLO",          of("Ăn một mình", "Solo dining")),
            suitable("TOURIST",       of("Du khách muốn thử ẩm thực địa phương", "Tourist experiencing local cuisine"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",      of("Mộc mạc bình dân", "Rustic and unpretentious")),
            ambience("COZY",        of("Ấm cúng như nhà", "Cozy as home")),
            ambience("TRADITIONAL", of("Truyền thống Việt Nam", "Traditional Vietnamese")),
            ambience("VIBRANT",     of("Sôi động, nhộn nhịp", "Lively and bustling"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(20)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Cơm niêu 3 cá bống đặc trưng", "Com Nieu 3 Ca Bong signature clay pot"),
                of("Niêu cơm nấu đất truyền thống ăn kèm 3 con cá bống kho tiêu, hương vị thuần Việt đặc sắc không nơi nào có.",
                   "Traditional clay pot rice paired with 3 braised goby fish in pepper, a uniquely Vietnamese flavor found nowhere else."),
                new BigDecimal("65000")),
            signatureDish(of("Cá bống kho tiêu xanh", "Goby fish braised with green pepper"),
                of("Cá bống đồng kho khô với tiêu xanh hạt to, vị cay thơm và ngọt tự nhiên của cá, ăn kèm cơm niêu rất vừa miệng.",
                   "River goby braised dry with large green peppercorns, the natural sweetness and spicy aroma pair perfectly with clay pot rice."),
                new BigDecimal("55000")),
            signatureDish(of("Cá kho tộ sả ớt", "Braised fish with lemongrass and chili in clay pot"),
                of("Cá tươi kho tộ với sả băm, ớt đỏ và nước mắm nguyên chất, màu vàng sậm bóng đẹp, đậm đà thấm vị.",
                   "Fresh fish braised with minced lemongrass, red chili and pure fish sauce, deep golden glaze, intensely flavorful."),
                new BigDecimal("50000")),
            signatureDish(of("Thịt heo kho tiêu đen", "Pork braised with black pepper"),
                of("Thịt ba chỉ hoặc nạc vai kho với tiêu đen Phú Quốc, thơm nồng, mềm tan, đậm đà hấp dẫn.",
                   "Pork belly or shoulder braised with Phu Quoc black pepper, intensely aromatic, tender and savory."),
                new BigDecimal("45000")),
            signatureDish(of("Canh chua cá tràu miền Trung", "Central Vietnamese sour soup with snakehead fish"),
                of("Canh chua nấu cá tràu tươi với cà chua, dứa, đậu bắp và rau thơm theo công thức miền Trung.",
                   "Sour soup with fresh snakehead fish, tomato, pineapple, okra and herbs following Central Vietnamese tradition."),
                new BigDecimal("50000")),
            signatureDish(of("Rau lang luộc mắm cá", "Boiled sweet potato leaves with fish sauce"),
                of("Rau lang non luộc vừa chín tới, chấm mắm cá cơm đặc sản Đà Nẵng, thanh mát và đưa cơm.",
                   "Tender sweet potato leaves lightly boiled, served with Da Nang specialty anchovy fish sauce, refreshing and meal-enhancing."),
                new BigDecimal("20000")),
            signatureDish(of("Đậu hũ chiên mắm tỏi", "Fried tofu with garlic fish sauce"),
                of("Đậu hũ trắng chiên giòn bên ngoài, chấm mắm tỏi ớt đậm đà, ăn kèm cơm niêu rất vừa miệng.",
                   "Crispy fried white tofu served with savory garlic chili fish sauce, an excellent companion to clay pot rice."),
                new BigDecimal("25000")),
            signatureDish(of("Cá chiên sả ớt", "Fried fish with lemongrass and chili"),
                of("Cá tươi chiên vàng giòn, áo lớp sả ớt thơm nồng, ăn nóng cùng cơm niêu vô cùng ngon.",
                   "Freshly fried golden crispy fish coated in fragrant lemongrass and chili, best enjoyed hot with clay pot rice."),
                new BigDecimal("55000")),
            signatureDish(of("Trứng đúc thịt", "Steamed egg with minced pork"),
                of("Trứng gà hấp cùng thịt heo băm và nấm hương, mềm mịn thơm bùi, là món quen thuộc trong mâm cơm Việt.",
                   "Steamed egg with minced pork and shiitake mushrooms, silky smooth and fragrant — a beloved Vietnamese staple."),
                new BigDecimal("35000")),
            signatureDish(of("Chè đậu đỏ nước dừa", "Red bean dessert with coconut milk"),
                of("Chè đậu đỏ nấu nước dừa béo ngậy, vị ngọt dịu mát lạnh tráng miệng kết thúc bữa cơm niêu trọn vẹn.",
                   "Red bean dessert in rich coconut milk, cool and gently sweet, the perfect finale to a clay pot rice meal."),
                new BigDecimal("15000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("COM_NIEU_SET")
                .name(of("Set cơm niêu & cá bống", "Clay pot rice & goby fish sets"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set cơm niêu 3 cá bống", "Clay pot rice with 3 goby fish"))
                        .description(of("1 niêu cơm + 3 cá bống kho tiêu.", "1 clay pot rice + 3 braised pepper goby fish."))
                        .priceFrom(new BigDecimal("60000"))
                        .priceTo(new BigDecimal("75000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("rice", "fish", "set", "clay-pot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cơm niêu đất đơn", "Solo clay pot rice"))
                        .description(of("1 niêu cơm truyền thống không kèm món.", "Single traditional clay pot rice without side dish."))
                        .priceFrom(new BigDecimal("30000"))
                        .priceTo(new BigDecimal("40000"))
                        .unit(of("niêu", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("rice", "clay-pot"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MON_KHO")
                .name(of("Món kho niêu đặc sản", "Specialty braised clay pot dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cá bống kho tiêu", "Braised goby fish with pepper"))
                        .description(of("Cá bống kho khô với tiêu xanh thơm.", "Dry braised goby with fragrant green pepper."))
                        .priceFrom(new BigDecimal("50000"))
                        .priceTo(new BigDecimal("60000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("fish", "braised", "pepper"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Thịt heo kho tiêu đen", "Pork braised with black pepper"))
                        .description(of("Thịt ba chỉ kho tiêu Phú Quốc đậm đà.", "Pork belly braised with Phu Quoc black pepper."))
                        .priceFrom(new BigDecimal("40000"))
                        .priceTo(new BigDecimal("55000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("pork", "braised", "pepper"))
                        .build()
                ))
                .build()
        );

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SET_LUNCH",
            "TAKEAWAY",
            "VEGETARIAN_MENU",
            "FAMILY_FRIENDLY",
            "CASH_ONLY_OPTION",
            "OUTDOOR_TABLE",
            "SMOKING_AREA",
            "HALAL_AVAILABLE"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè trước nhà hàng trên đường Nguyễn Tri Phương, P. Chính Gián",
                                         "Sidewalk in front of restaurant on Nguyen Tri Phuong Street, Chinh Gian Ward"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Khu vực đường Nguyễn Tri Phương có nhiều chỗ đậu xe máy, dễ tìm chỗ vào giờ không cao điểm.",
                      "Nguyen Tri Phuong Street has ample motorbike parking, easy to find a spot during off-peak hours."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Cơm Niêu 3 Cá Bống — Hương vị truyền thống Việt hòa quyện với nét mộc mạc gần gũi",
                   "Com Nieu 3 Ca Bong — Traditional Vietnamese flavors blended with rustic warmth")),
            paragraph(ContentSection.STORY,
                of("Cơm Niêu 3 Cá Bống Nguyễn Tri Phương là điểm ẩm thực bình dân được lòng người dân Đà Nẵng nhiều năm qua, " +
                   "tọa lạc tại số 112 Nguyễn Tri Phương, phường Chính Gián, quận Thanh Khê. Nhà hàng gắn với hình ảnh " +
                   "mâm cơm gia đình thuần Việt — cơm niêu đất nóng hổi, cá bống kho tiêu đậm đà, canh chua cá trong vắt — " +
                   "mang lại trải nghiệm ẩm thực chân thực, gần gũi như bữa cơm nhà.",
                   "Com Nieu 3 Ca Bong on Nguyen Tri Phuong Street has been a beloved local eatery among Da Nang residents for many years, " +
                   "located at 112 Nguyen Tri Phuong, Chinh Gian Ward, Thanh Khe District. The restaurant is known for its " +
                   "quintessentially Vietnamese family meal image — steaming clay pot rice, richly braised pepper goby fish, " +
                   "clear sour fish soup — delivering an authentic dining experience as warm as a home-cooked meal.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Không gian nhà hàng được thiết kế đơn giản, thân thiện với gam màu ấm, bàn ghế gỗ mộc mạc và " +
                   "ánh đèn vàng tạo cảm giác ấm cúng như bữa cơm quê. Sức chứa khoảng 120 khách với các bàn nhỏ " +
                   "cho gia đình 2-4 người và bàn lớn cho nhóm lên đến 20 người. Điều hòa nhiệt độ đảm bảo thoải mái " +
                   "cả trong những ngày hè oi ả của Đà Nẵng.",
                   "The restaurant features a simple, friendly design with warm tones, rustic wooden tables and chairs, " +
                   "and warm lighting that creates a cozy countryside ambience. With capacity for about 120 guests, " +
                   "the layout includes small tables for families of 2-4 and larger tables for groups up to 20. " +
                   "Air conditioning ensures comfort even during Da Nang's hottest summer days.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thương hiệu '3 Cá Bống' xuất phát từ set ăn đặc trưng: 1 niêu cơm đất kèm 3 con cá bống kho tiêu xanh " +
                   "đậm đà — sự kết hợp hài hòa giữa cơm dẻo thơm và vị cay bùi của cá bống. Ngoài ra, thực đơn còn có " +
                   "cá kho tộ sả ớt, thịt heo kho tiêu đen, canh chua cá tràu, rau lang luộc mắm cá và nhiều món ăn " +
                   "kèm phong phú khác, tất cả đều được chế biến tươi hàng ngày.",
                   "The '3 Ca Bong' brand name originates from the signature set: 1 clay pot rice paired with 3 braised " +
                   "green pepper goby fish — a harmonious combination of fragrant sticky rice and the rich spicy goby flavor. " +
                   "The menu also features lemongrass-chili braised fish in clay pot, black pepper braised pork, " +
                   "Central Vietnamese sour snakehead soup, boiled sweet potato leaves with fish sauce, and many other " +
                   "side dishes, all freshly prepared daily.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá hợp lý 100.000 – 150.000đ/người và không khí bình dân thân thiện, Cơm Niêu 3 Cá Bống " +
                   "là nơi lý tưởng cho bữa trưa văn phòng nhanh gọn, ăn tối gia đình, tụ tập bạn bè hay du khách muốn " +
                   "trải nghiệm ẩm thực dân dã đích thực của Đà Nẵng. Nhà hàng mở cửa liên tục từ 10:30 đến 21:30, " +
                   "phục vụ từ bữa trưa đến bữa tối không gián đoạn.",
                   "With reasonable pricing of 100,000–150,000 VND per person and a friendly casual atmosphere, " +
                   "Com Nieu 3 Ca Bong is ideal for quick office lunches, family dinners, friend gatherings, or tourists " +
                   "seeking a genuine taste of everyday Da Nang cuisine. The restaurant operates continuously from " +
                   "10:30 to 21:30, serving lunch through dinner without interruption.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp dịch vụ thanh toán thẻ, xuất hóa đơn VAT, đồ ăn mang về và có thực đơn chay cho " +
                   "thực khách có nhu cầu đặc biệt. Bàn ghế cho trẻ em sẵn có. Wi-Fi miễn phí. Đặt bàn qua hệ thống " +
                   "trực tuyến giúp đảm bảo có chỗ ngồi vào giờ cao điểm trưa và tối.",
                   "The restaurant supports card payment, VAT invoices, takeaway orders and has a vegetarian menu for guests " +
                   "with special dietary needs. Children's chairs available. Free Wi-Fi. Online reservation ensures secured " +
                   "seating during peak lunch and dinner hours.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại 112 Nguyễn Tri Phương, phường Chính Gián, quận Thanh Khê — cách nhà hàng Cơm Niêu Nhà Đỏ 2 " +
                   "chỉ vài trăm mét, đây là tuyến đường ẩm thực cơm niêu đặc trưng của Đà Nẵng. Cách trung tâm thành phố " +
                   "khoảng 4km, thuận tiện đi bằng xe máy, taxi hay grab từ mọi khu vực thành phố.",
                   "Located at 112 Nguyen Tri Phuong, Chinh Gian Ward, Thanh Khe District — just a few hundred meters from " +
                   "Com Nieu Nha Do 2 restaurant, this stretch is Da Nang's signature clay pot rice dining street. " +
                   "About 4km from the city center, conveniently accessible by motorbike, taxi or ride-hailing from anywhere in the city.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn", "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Không yêu cầu đặt cọc cho nhóm dưới 10 người. Nhóm lớn nên đặt trước 1 tiếng để đảm bảo bàn rộng. " +
                   "Hóa đơn VAT xuất theo yêu cầu. Lý do nên chọn Cơm Niêu 3 Cá Bống: thương hiệu lâu năm được tin tưởng, " +
                   "nguyên liệu tươi chọn lọc mỗi ngày, công thức kho cá bống đặc trưng không đâu có, và giá cả rất phải chăng " +
                   "cho một bữa cơm Việt trọn vẹn.",
                   "No deposit required for groups under 10 people. Larger groups should book 1 hour ahead to secure a wide table. " +
                   "VAT invoices on request. Why choose Com Nieu 3 Ca Bong: a long-established trusted brand, fresh daily " +
                   "ingredients, a unique signature braised goby fish recipe found nowhere else, and very reasonable pricing " +
                   "for a complete Vietnamese meal.")),
            mapBlock(ContentSection.STORY, new double[]{108.204332, 16.061489})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("BEER")
                .feeAmount(new BigDecimal("20000"))
                .currencyCode("VND")
                .note(of("Phụ phí bia mang ngoài vào: 20.000đ/lon/chai.", "Beer corkage fee: 20,000 VND/can/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(10)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc. Nhóm đông đặt trước ít nhất 1 tiếng.",
                             "No deposit required. Large groups should book at least 1 hour ahead."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi khi đặt bàn qua hệ thống, không áp dụng vào ngày lễ.",
                                 "Promotion for system bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi, vui lòng liên hệ nhà hàng.",
                              "Promotion details vary; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không áp dụng phụ phí dịch vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food not permitted."))
            .outsideDrinkPolicy(of("Được phép mang đồ uống vào với phụ phí nhỏ theo loại.", "Outside drinks allowed with a small corkage fee."))
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

        // ---------- Table types (2 loại bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(18)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(60)
                .allowedDurationsMinutes(List.of(60, 90))
                .note(of("Bàn tiêu chuẩn khu vực chính.", "Standard table in main dining area."))
                .build(),
            TableType.builder()
                .id("tb-10-grp")
                .name(of("Bàn nhóm 10 người", "Group table for 10"))
                .seats(10)
                .minPeople(6)
                .maxPeople(12)
                .totalTables(6)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn cho gia đình và nhóm đông.", "Large table for families and large groups."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90))
            .defaultDurationMinutes(60)
            .minBookingLeadTimeMinutes(60)
            .graceArrivalMinutes(10)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(20)
            .depositOnly(false)
            .maxTablesPerBooking(3)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Thanh Khê", "Thanh Khe District"))
            .wardName(of("Phường Chính Gián", "Chinh Gian Ward"))
            .addressLine(of("112 Nguyễn Tri Phương, P. Chính Gián, Q. Thanh Khê, TP. Đà Nẵng",
                            "112 Nguyen Tri Phuong Street, Chinh Gian Ward, Thanh Khe District, Da Nang City"))
            .location(new double[]{108.204332, 16.061489})
            .name(of("Cơm Niêu 3 Cá Bống Nguyễn Tri Phương", "Com Nieu 3 Ca Bong Nguyen Tri Phuong"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Cơm niêu đất kèm cá bống kho tiêu — mâm cơm thuần Việt ấm cúng trên phố ẩm thực Nguyễn Tri Phương, Đà Nẵng.",
                                 "Clay pot rice with braised pepper goby fish — a warm authentic Vietnamese family spread on Da Nang's Nguyen Tri Phuong food street."))
            .description(of("Cơm Niêu 3 Cá Bống Nguyễn Tri Phương là nhà hàng dân dã lâu năm tại 112 Nguyễn Tri Phương, " +
                            "phường Chính Gián, quận Thanh Khê, Đà Nẵng. Nổi tiếng với set cơm niêu đất kèm 3 cá bống kho tiêu " +
                            "đặc trưng, nhà hàng mang đến trải nghiệm mâm cơm gia đình Việt truyền thống với hương vị " +
                            "chân thực và giá cả bình dân.",
                            "Com Nieu 3 Ca Bong on Nguyen Tri Phuong Street is a long-established local restaurant at 112 Nguyen Tri Phuong, " +
                            "Chinh Gian Ward, Thanh Khe District, Da Nang. Famous for its signature clay pot rice with 3 braised " +
                            "pepper goby fish, the restaurant delivers a traditional Vietnamese family dining experience " +
                            "with authentic flavors and very affordable pricing."))
            .phone("0361922988")
            .bookingHotline("0361922988")
            .minPricePerPerson(new BigDecimal("100000"))
            .maxPricePerPerson(new BigDecimal("150000"))
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
