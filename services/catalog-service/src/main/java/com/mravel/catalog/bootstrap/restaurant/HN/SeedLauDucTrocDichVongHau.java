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
public class SeedLauDucTrocDichVongHau {

    @Bean
    CommandLineRunner seedLauDucTrocDichVongHauRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauDucTrocDichVongHau] start");

            RestaurantDoc doc = restaurantLauDucTrocDichVongHau();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauDucTrocDichVongHau] done");
        };
    }

    // =====================================================================
    //    LẨU ĐỨC TRỌC - DỊCH VỌNG HẬU (Cầu Giấy, Hà Nội)
    // =====================================================================

    private static RestaurantDoc restaurantLauDucTrocDichVongHau() {
        String slug = "lau-duc-troc-dich-vong-hau";

        // ---------- Gallery ảnh không gian ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-1-normal-130383114331.webp",
                of("Không gian Lẩu Đức Trọc Dịch Vọng Hậu", "Lau Duc Troc Dich Vong Hau interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-2-normal-130383214332.webp",
                of("Không gian tầng 2 Lẩu Đức Trọc", "Lau Duc Troc - 2nd floor space"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-3-normal-130383314333.webp",
                of("Khu vực ăn đông khách", "Dining area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-4-normal-130383414334.webp",
                of("Bàn lẩu đang được phục vụ", "Hotpot table in service"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-5-normal-130383514335.webp",
                of("Nội thất hiện đại, trẻ trung", "Modern and youthful interior"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-6-normal-130383614336.webp",
                of("Ánh đèn vàng ấm cúng", "Warm golden lighting"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-7-normal-130383714337.webp",
                of("Khu vực bàn ghế rộng rãi", "Spacious seating area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-8-normal-130383814338.webp",
                of("Không gian tầng 3 Lẩu Đức Trọc", "Lau Duc Troc - 3rd floor"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-9-normal-130383914339.webp",
                of("Hoa đào điểm xuyết không gian", "Peach blossom decoration"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-10-normal-130384014340.webp",
                of("Khu vực đặt bàn nhóm đông", "Large group dining area"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-11-normal-130384114341.webp",
                of("Không gian tầng 4 Lẩu Đức Trọc", "Lau Duc Troc - 4th floor"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-duc-troc-dich-vong-hau-slide-12-normal-130384214342.webp",
                of("Toàn cảnh nhà hàng 4 tầng", "Full view of 4-floor restaurant"), false, 11)
        );

        // ---------- Ảnh menu/bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-1.jpg",
                of("Menu Lẩu Đức Trọc trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-2.jpg",
                of("Menu Lẩu Đức Trọc trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-3.jpg",
                of("Menu Lẩu Đức Trọc trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-4.jpg",
                of("Menu Lẩu Đức Trọc trang 4", "Menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-5.jpg",
                of("Menu Lẩu Đức Trọc trang 5", "Menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-6.jpg",
                of("Menu Lẩu Đức Trọc trang 6", "Menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-7.jpg",
                of("Menu Lẩu Đức Trọc trang 7", "Menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-8.jpg",
                of("Menu Lẩu Đức Trọc trang 8", "Menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-9.jpg",
                of("Menu Lẩu Đức Trọc trang 9", "Menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-10.jpg",
                of("Menu Lẩu Đức Trọc trang 10", "Menu page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-11.jpg",
                of("Menu Lẩu Đức Trọc trang 11", "Menu page 11"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-12.jpg",
                of("Menu Lẩu Đức Trọc trang 12", "Menu page 12"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-13.jpg",
                of("Menu Lẩu Đức Trọc trang 13", "Menu page 13"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-14.jpg",
                of("Menu Lẩu Đức Trọc trang 14", "Menu page 14"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/L%E1%BA%A9u%20%C4%90%E1%BB%A9c%20Tr%E1%BB%8Dc/Menu-Lau-Duc-Troc-15.jpg",
                of("Menu Lẩu Đức Trọc trang 15", "Menu page 15"), false, 14)
        );

        // ---------- Cuisine ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_HOTPOT")
                .name(of("Lẩu Việt Nam", "Vietnamese Hotpot"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt", "Vietnamese cuisine"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD_HOTPOT")
                .name(of("Lẩu & Hải sản", "Hotpot & Seafood"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa (2 ca: 10:30-13:30 và 17:00-22:00) ----------
        // Dùng ca tối vì là ca chính, hệ thống đặt bàn ưu tiên 1 OpeningHour/ngày
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 30, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COUPLE",        of("Hẹn hò", "Couple date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special occasion"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("MODERN",    of("Hiện đại, trẻ trung", "Modern and youthful")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",    of("Nét truyền thống", "Traditional touch"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(700)
            .maxGroupSize(100)
            .hasPrivateRooms(true)
            .privateRoomCount(6)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu mỹ vị", "My Vi hotpot")),
            signatureDishL(of("Lẩu ếch", "Frog hotpot")),
            signatureDishL(of("Lẩu Thái chua cay", "Thai spicy sour hotpot")),
            signatureDishL(of("Lẩu riêu cua", "Crab tomato hotpot")),
            signatureDishL(of("Lẩu cháo chim", "Bird porridge hotpot")),
            signatureDishL(of("Bò lúc lắc", "Shaking beef")),
            signatureDishL(of("Cá lăng trộn", "Catfish salad")),
            signatureDishL(of("Cá lăng nướng", "Grilled catfish")),
            signatureDishL(of("Mực nướng sa tế", "Grilled squid with satay")),
            signatureDishL(of("Chân gà quái thú", "Monster chicken feet"))
        );

        // ---------- Menu section ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("LAU")
                .name(of("Các loại lẩu", "Hotpot varieties"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu mỹ vị", "My Vi hotpot"))
                        .description(of("Nồi lẩu đặc biệt với hương vị đậm đà từ bí quyết gia truyền.", "Special hotpot with rich flavour from a family recipe."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("200000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu ếch", "Frog hotpot"))
                        .description(of("Ếch tươi chế biến cùng nước dùng đặc trưng, thơm ngon.", "Fresh frog cooked in signature broth, fragrant and delicious."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "frog"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MON_AN_VIET")
                .name(of("Món ăn Việt", "Vietnamese dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò lúc lắc", "Shaking beef"))
                        .description(of("Bò phi lê xào lúc lắc cùng tiêu và rau thơm, ăn kèm cơm trắng.", "Fillet beef stir-fried with pepper and herbs, served with steamed rice."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "stir-fry"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe gần nhà hàng hoặc lòng đường", "Nearby car park or roadside"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng", "In front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Ô tô đỗ miễn phí khu vực lân cận; xe máy trước cửa miễn phí.", "Free car parking nearby; free motorbike parking in front of the restaurant."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "KIDS_CHAIR",
            "KIDS_PLAY_AREA",
            "SMOKING_AREA",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "SET_LUNCH",
            "KARAOKE",
            "VISA_MASTER",
            "VAT_INVOICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "LED_SCREEN"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Lẩu Đức Trọc Dịch Vọng Hậu – Hội tụ tinh hoa ẩm thực lẩu trăm miền",
                   "Lau Duc Troc Dich Vong Hau – A convergence of Vietnam's finest hotpot flavours")),
            paragraph(ContentSection.STORY,
                of("Nằm tại số 100 phố Dịch Vọng Hậu, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Hà Nội, Lẩu Đức Trọc là một trong những chi nhánh " +
                   "của chuỗi quán lẩu nổi danh bậc nhất khu vực Tây Hà Nội. Với tòa biệt thự bốn tầng rộng rãi, " +
                   "nhà hàng là địa chỉ quen thuộc của hàng ngàn thực khách yêu ẩm thực lẩu mỗi ngày.",
                   "Located at No. 100 Dich Vong Hau Street, Dich Vong Hau Ward, Cau Giay District, Hanoi, Lau Duc Troc is one of the most " +
                   "celebrated branches of a renowned hotpot restaurant chain in western Hanoi. Housed in a spacious four-storey villa, " +
                   "it welcomes thousands of hotpot enthusiasts every day.")),
            heading(ContentSection.STORY,
                of("Không gian rộng rãi, sức chứa 700 khách trên 4 tầng",
                   "Spacious venue with a 700-guest capacity across 4 floors")),
            paragraph(ContentSection.STORY,
                of("Lẩu Đức Trọc Dịch Vọng Hậu sở hữu không gian hiện đại, trẻ trung với sức chứa lên đến 700 khách trải dài trên bốn tầng. " +
                   "Mỗi tầng được bố trí bàn ghế rộng rãi, thích hợp cho cả nhóm nhỏ lẫn đoàn đông. " +
                   "Hệ thống đèn vàng ấm cúng kết hợp nội thất gỗ và hoa đào điểm xuyết tạo nên không gian vừa hiện đại vừa có chút hoài cổ Hà Thành.",
                   "Lau Duc Troc Dich Vong Hau features a modern, youthful ambience with a total capacity of 700 guests spread across four floors. " +
                   "Each floor is generously laid out to accommodate both small groups and large parties. " +
                   "Warm golden lighting paired with wooden furnishings and peach blossom accents creates a space that blends modernity with a touch of Hanoi nostalgia.")),
            heading(ContentSection.STORY,
                of("Thực đơn phong phú – Lẩu đặc sắc từ mọi miền",
                   "Rich menu – Signature hotpots from every region")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Lẩu Đức Trọc đa dạng với hàng chục loại lẩu độc đáo: lẩu mỹ vị, lẩu ếch, lẩu Thái chua cay, lẩu riêu cua, lẩu cháo chim... " +
                   "Mỗi nồi lẩu đều được chế biến từ nguyên liệu tươi ngon nhập từ các nhà cung cấp uy tín tại Hà Nội, " +
                   "bởi đội ngũ đầu bếp chuyên nghiệp với kinh nghiệm lâu năm. Bên cạnh lẩu, nhà hàng còn phục vụ nhiều món nhậu hấp dẫn " +
                   "như bò lúc lắc, cá lăng trộn, cá lăng nướng, mực nướng sa tế và các món ếch, gà đặc sắc.",
                   "The Lau Duc Troc menu boasts dozens of distinctive hotpot varieties: My Vi hotpot, frog hotpot, Thai spicy-sour hotpot, crab tomato hotpot, " +
                   "bird porridge hotpot, and more. Every pot is crafted from fresh ingredients sourced from reputable Hanoi suppliers by a team of experienced chefs. " +
                   "Alongside hotpots, the restaurant also serves popular à la carte dishes such as shaking beef, catfish salad, grilled catfish, " +
                   "satay-grilled squid, and signature frog and chicken preparations.")),
            heading(ContentSection.STORY,
                of("Phù hợp đa dạng dịp ăn uống",
                   "Perfect for all dining occasions")),
            paragraph(ContentSection.STORY,
                of("Với sức chứa lớn và hệ thống phòng riêng, Lẩu Đức Trọc là lựa chọn lý tưởng cho bữa ăn gia đình, " +
                   "sinh nhật, liên hoan văn phòng, họp nhóm hay tiệc công ty. " +
                   "Mức giá từ 150.000 - 250.000đ/người phù hợp với đa số thực khách, " +
                   "đặc biệt thu hút dân công sở, sinh viên và các nhóm bạn bè tụ tập.",
                   "With a large capacity and dedicated private rooms, Lau Duc Troc is the ideal choice for family meals, " +
                   "birthday celebrations, office parties, group meetings and company events. " +
                   "Prices ranging from 150,000 - 250,000 VND/person appeal to a broad audience, " +
                   "especially office workers, students and friend groups.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật",
                   "Outstanding services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trang bị đầy đủ điều hòa, wifi miễn phí, hệ thống âm thanh ánh sáng, máy chiếu, màn LED và dịch vụ MC " +
                   "– sẵn sàng hỗ trợ tổ chức sinh nhật, sự kiện công ty, liên hoan có chương trình. " +
                   "Ghế trẻ em và khu vui chơi trẻ em cũng được bố trí, phù hợp cho cả gia đình có con nhỏ.",
                   "The restaurant is fully equipped with air conditioning, free wifi, a sound and lighting system, projector, LED screen and MC service " +
                   "– ready to support birthday parties, company events and organised dinners. " +
                   "Children's chairs and a play area are also available, making it family-friendly.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Lẩu Đức Trọc nằm trên phố Dịch Vọng Hậu thuộc Quận Cầu Giấy – một trong những tuyến phố ẩm thực sôi động nhất Hà Nội. " +
                   "Nhà hàng cách Đại học Quốc gia Hà Nội khoảng 1km, dễ dàng di chuyển bằng xe máy, taxi hay xe bus. " +
                   "Bãi đỗ xe ô tô và xe máy miễn phí ngay khu vực cửa hàng.",
                   "Lau Duc Troc is situated on Dich Vong Hau Street in Cau Giay District – one of Hanoi's most vibrant food streets. " +
                   "It is about 1 km from Vietnam National University Hanoi, easily reached by motorbike, taxi or bus. " +
                   "Free car and motorbike parking is available right at the restaurant.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & ưu đãi",
                   "Booking policy & promotions")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng áp dụng ưu đãi giảm 5% menu khi đi từ 1-20 người và giảm 8% khi đi từ 21 người trở lên, " +
                   "kèm tặng bánh sinh nhật cho đoàn từ 10 người lớn. " +
                   "Đặt bàn qua hệ thống trước 11h trưa hoặc 18h tối để được giữ chỗ và nhận ưu đãi tốt nhất. " +
                   "Khuyến nghị đặt trước ít nhất 30 phút, đặc biệt vào cuối tuần và dịp lễ.",
                   "The restaurant offers a 5% discount on the menu bill for groups of 1-20 people and 8% for 21+ guests, " +
                   "with a complimentary birthday cake for groups of 10 adults or more. " +
                   "Book via the system before 11am or 6pm to secure your table and enjoy the best promotions. " +
                   "It is recommended to book at least 30 minutes in advance, especially on weekends and public holidays.")),
            mapBlock(ContentSection.STORY, new double[]{105.7913, 21.0300})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "No promotion on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "No promotion on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(null)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho đặt bàn thông thường.", "No deposit required for standard table bookings."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm 5% menu cho nhóm 1-20 người; giảm 8% cho nhóm từ 21 người.", "5% menu discount for groups of 1-20; 8% for groups of 21+."))
            .promotionMaxDiscountPercent(8)
            .promotionNote(of("Áp dụng khi đặt bàn trước 11h trưa hoặc 18h tối. Không áp dụng ngày lễ lớn.", "Valid when booking before 11am or 6pm. Not applicable on major public holidays."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(10)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice available on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không tính phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ ngoài vào.", "Outside drinks are not allowed."))
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
                .id("tb-4-ldt")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(40)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường khu vực trong nhà, máy lạnh.", "Standard indoor air-conditioned table."))
                .build(),
            TableType.builder()
                .id("tb-8-ldt")
                .name(of("Bàn 8 người", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn cho nhóm đông, phù hợp gia đình và nhóm bạn.", "Large table for bigger groups, suitable for families and friend groups."))
                .build(),
            TableType.builder()
                .id("pr-50-ldt")
                .name(of("Phòng riêng 50 khách", "Private room for 50 guests"))
                .seats(50)
                .minPeople(20)
                .maxPeople(50)
                .totalTables(6)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng yên tĩnh, phù hợp tiệc công ty và sự kiện.", "Private quiet room suitable for company parties and events."))
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
            .depositOnly(true)
            .maxTablesPerBooking(6)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Cầu Giấy", "Cau Giay District"))
            .wardName(of("P. Dịch Vọng Hậu", "Dich Vong Hau Ward"))
            .addressLine(of("Số 100 Dịch Vọng Hậu, P. Dịch Vọng Hậu, Q. Cầu Giấy, Hà Nội",
                            "No. 100 Dich Vong Hau Street, Dich Vong Hau Ward, Cau Giay District, Hanoi"))
            .location(new double[]{105.7913, 21.0300})
            .name(of("Lẩu Đức Trọc - Dịch Vọng Hậu", "Lau Duc Troc - Dich Vong Hau"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Chuỗi lẩu nổi danh Hà Nội – 4 tầng, 700 khách, đa dạng lẩu đặc sắc từ mọi miền.",
                                 "Hanoi's renowned hotpot chain – 4 floors, 700 guests, diverse signature hotpots from across Vietnam."))
            .description(of("Lẩu Đức Trọc - Dịch Vọng Hậu là chi nhánh của chuỗi quán lẩu nổi tiếng tại Cầu Giấy, Hà Nội. " +
                "Tòa biệt thự 4 tầng sức chứa 700 khách với đầy đủ phòng riêng, hệ thống âm thanh ánh sáng và tiện ích hiện đại. " +
                "Thực đơn đa dạng từ lẩu mỹ vị, lẩu ếch, lẩu Thái đến các món nhậu đặc sắc với mức giá 150.000 - 250.000đ/người.",
                "Lau Duc Troc - Dich Vong Hau is a branch of the famous hotpot chain in Cau Giay, Hanoi. " +
                "A 4-storey villa with capacity for 700 guests, complete with private rooms, sound & lighting system and modern amenities. " +
                "A diverse menu from My Vi hotpot, frog hotpot, Thai hotpot to signature snacks at 150,000 - 250,000 VND/person."))
            .phone("0931006005")
            .website("https://pasgo.vn/nha-hang/lau-duc-troc-dich-vong-hau")
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
