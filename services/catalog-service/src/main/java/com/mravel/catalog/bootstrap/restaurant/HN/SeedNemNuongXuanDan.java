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
public class SeedNemNuongXuanDan {

    @Bean
    CommandLineRunner seedNemNuongXuanDanRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNemNuongXuanDan] start");

            RestaurantDoc doc = restaurantNemNuongXuanDan();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedNemNuongXuanDan] done");
        };
    }

    // =====================================================================
    //         NHÀ HÀNG NEM NƯỚNG XUÂN DẦN - PHỦ DOÃN (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantNemNuongXuanDan() {
        String slug = "nem-nuong-xuan-dan-phu-doan";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-1-normal-703997034838.webp",
                of("Không gian Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn", "Nem Nuong Xuan Dan - Phu Doan restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-2-normal-703997134839.webp",
                of("Không gian tầng 1", "Ground floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-3-normal-703997234840.webp",
                of("Không gian tầng 2", "Second floor dining area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-4-normal-703997334841.webp",
                of("Bàn ăn và không gian mộc mạc", "Rustic dining table and decor"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-5-normal-703997934842.webp",
                of("Nem nướng đặc sản Ninh Hòa", "Ninh Hoa specialty nem nuong"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-6-normal-703998034843.webp",
                of("Lẩu và nem nướng cùng bàn", "Hotpot and nem nuong on the table"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-7-normal-703998134844.webp",
                of("Các món khai vị", "Appetizer dishes"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-8-normal-703998234845.webp",
                of("Mâm nem nướng đầy đủ", "Full nem nuong platter"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-9-normal-703998734846.webp",
                of("Góc nhà hàng ấm cúng", "Cozy corner of the restaurant"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-10-normal-703998834847.webp",
                of("Thực khách thưởng thức bữa ăn", "Guests enjoying their meal"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-11-normal-703998934848.webp",
                of("Khu vực bếp nướng", "Grilling area"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-nem-nuong-xuan-dan-phu-doan-12-normal-703999034849.webp",
                of("Toàn cảnh nhà hàng nhìn từ trên", "Restaurant overview from above"), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE_GRILLED")
                .name(of("Nướng Việt Nam", "Vietnamese Grilled"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("VIETNAM")
                .build()
        );

        // ---------- Giờ mở cửa (T2-CN: buổi trưa 10:00-14:30, tối 16:00-22:00) ----------
        // Ghi nhận ca tối (primary session) theo cấu trúc OpeningHour
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
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("QUICK_MEAL",    of("Bữa ăn nhanh thường ngày", "Quick everyday meal")),
            suitable("TOURIST",       of("Khách du lịch khám phá ẩm thực Hà Nội", "Tourists exploring Hanoi cuisine")),
            suitable("SOLO",          of("Ăn một mình", "Solo dining"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",      of("Mộc mạc dân dã", "Rustic and homely")),
            ambience("COZY",        of("Ấm cúng", "Cozy")),
            ambience("TRADITIONAL", of("Truyền thống Việt Nam", "Traditional Vietnamese")),
            ambience("CASUAL",      of("Thân mật, bình dân", "Casual and laid-back"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(30)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Nem nướng Ninh Hòa", "Ninh Hoa grilled pork roll"),
                of("Nem nướng đặc sản miền Trung, thịt heo xay nướng thơm trên than hoa, cuốn bánh tráng cùng rau sống.", "Central Vietnamese specialty grilled pork rolls, served with rice paper and fresh vegetables."),
                new BigDecimal("80000")),
            signatureDish(of("Lẩu nem nướng", "Nem nuong hotpot"),
                of("Nồi lẩu dùng kèm nem nướng tươi, rau sống và bún, nước dùng đậm đà hương vị truyền thống.", "Hotpot served with fresh grilled pork rolls, vegetables and vermicelli in a flavorful traditional broth."),
                new BigDecimal("120000")),
            signatureDish(of("Bún nem nướng", "Nem nuong vermicelli bowl"),
                of("Bún tươi thả trong nước dùng trong vắt, xếp nem nướng và rau thơm bên trên.", "Fresh vermicelli in a clear broth topped with grilled pork rolls and fragrant herbs."),
                new BigDecimal("60000")),
            signatureDish(of("Nem nướng cuốn bánh tráng", "Rice paper nem nuong rolls"),
                of("Bánh tráng mỏng cuốn cùng nem nướng, rau xà lách, cà rốt bào sợi và chấm mắm tỏi ớt.", "Thin rice paper rolls with nem nuong, lettuce, shredded carrot served with chili garlic fish sauce."),
                new BigDecimal("75000")),
            signatureDish(of("Lẩu mắm miền Tây", "Southern-style fermented fish hotpot"),
                of("Lẩu mắm đậm đà chua cay với các loại cá, thịt heo quay và rau đặc trưng miền Tây.", "Rich and spicy fermented fish hotpot with fish, roast pork and Southern vegetables."),
                new BigDecimal("150000")),
            signatureDish(of("Lẩu thái hải sản", "Thai-style seafood hotpot"),
                of("Lẩu chua cay kiểu Thái với tôm, mực, nghêu và nấm, nước dùng rất đậm đà.", "Spicy Thai-style hotpot with shrimp, squid, clams and mushrooms in a rich broth."),
                new BigDecimal("160000")),
            signatureDish(of("Nem nướng chấm tương", "Nem nuong with hoisin sauce"),
                of("Nem nướng thơm phức chấm cùng tương đen sền sệt pha ớt, đặc trưng khẩu vị Ninh Hòa.", "Aromatic grilled pork rolls dipped in thick hoisin chili sauce, a Ninh Hoa signature."),
                new BigDecimal("80000")),
            signatureDish(of("Đĩa rau sống cuốn", "Fresh vegetable platter"),
                of("Đĩa rau sống đa dạng gồm xà lách, húng quế, tía tô, giá đỗ, chuối chát dùng kèm nem nướng.", "Mixed fresh vegetable platter with lettuce, basil, perilla, bean sprouts and green banana served with nem nuong."),
                new BigDecimal("30000")),
            signatureDish(of("Lẩu gà sả ớt", "Lemongrass chili chicken hotpot"),
                of("Lẩu gà ta hầm sả ớt thơm nức, nước dùng trong vàng óng, ăn kèm bún và rau sống.", "Free-range chicken simmered with lemongrass and chili, golden clear broth served with noodles and fresh vegetables."),
                new BigDecimal("140000")),
            signatureDish(of("Chả giò chiên giòn", "Crispy fried spring rolls"),
                of("Chả giò nhân thịt heo, miến, mộc nhĩ chiên giòn đều, ăn kèm nước chấm chua ngọt.", "Crispy pork, glass noodle and wood-ear mushroom spring rolls served with sweet-and-sour dipping sauce."),
                new BigDecimal("55000"))
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/01.jpg",
                of("Thực đơn nem nướng 1", "Nem nuong menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/02.jpg",
                of("Thực đơn nem nướng 2", "Nem nuong menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/03.jpg",
                of("Thực đơn lẩu", "Hotpot menu page"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/004.jpg",
                of("Thực đơn gọi món", "A la carte menu"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/05.jpg",
                of("Bảng giá món nướng", "Grilled items price list"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/06.jpg",
                of("Bảng giá đồ uống", "Beverage price list"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/07.jpg",
                of("Thực đơn tráng miệng", "Dessert menu"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/08.jpg",
                of("Thực đơn set ăn trưa", "Lunch set menu"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/09.jpg",
                of("Thực đơn đặc sản miền Trung", "Central Vietnamese specialty menu"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/10.jpg",
                of("Thực đơn combo nhóm", "Group combo menu"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/11.jpg",
                of("Bảng giá cập nhật", "Updated price list"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/linhchi/nemnuongxuandan/12.jpg",
                of("Thực đơn tổng hợp", "Full menu overview"), false, 11)
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("NEM_NUONG")
                .name(of("Nem nướng đặc sản", "Specialty Nem Nuong"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Nem nướng Ninh Hòa (phần)", "Ninh Hoa nem nuong portion"))
                        .description(of("Nem nướng đặc sản Ninh Hòa, ăn kèm bánh tráng và rau sống.", "Ninh Hoa specialty nem nuong served with rice paper and fresh vegetables."))
                        .priceFrom(new BigDecimal("70000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("nem-nuong", "grilled", "central-vietnam"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo nem nướng + bún", "Nem nuong + vermicelli combo"))
                        .description(of("Combo gồm nem nướng và bún tươi, thích hợp cho bữa trưa nhanh.", "Combo includes nem nuong and fresh vermicelli, ideal for a quick lunch."))
                        .priceFrom(new BigDecimal("60000"))
                        .priceTo(new BigDecimal("80000"))
                        .unit(of("suất", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "nem-nuong", "bun"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("LAU")
                .name(of("Các loại lẩu", "Hotpot varieties"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu nem nướng", "Nem nuong hotpot"))
                        .description(of("Nồi lẩu đặc trưng dùng kèm nem nướng tươi và rau sống.", "Signature hotpot served with fresh nem nuong and vegetables."))
                        .priceFrom(new BigDecimal("110000"))
                        .priceTo(new BigDecimal("140000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "nem-nuong"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu thái hải sản", "Thai-style seafood hotpot"))
                        .description(of("Lẩu chua cay kiểu Thái với hải sản tươi.", "Spicy Thai-style hotpot with fresh seafood."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood", "thai"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu gà sả ớt", "Lemongrass chili chicken hotpot"))
                        .description(of("Lẩu gà ta với sả và ớt, nước dùng trong, vị đậm đà.", "Free-range chicken hotpot with lemongrass and chili, clear flavorful broth."))
                        .priceFrom(new BigDecimal("130000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi xe cách nhà hàng 30m", "Parking lot 30m from the restaurant"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng hoặc bãi xe bên cạnh", "In front of or beside the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy đỗ trước cửa hoặc bãi xe bên cạnh miễn phí; ô tô gửi bãi xe cách 30m (phí theo giờ).",
                "Free motorbike parking in front or beside; cars use a paid lot 30m away (hourly rate)."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "FREE_MOTORBIKE_PARKING",
            "CASH_ONLY",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "TAKEAWAY",
            "OUTDOOR_TABLE",
            "SET_LUNCH",
            "VAT_INVOICE",
            "WHEELCHAIR_ACCESS",
            "FAMILY_FRIENDLY",
            "NEAR_OLD_QUARTER"
        );

        // ---------- Content STORY (6-8 cặp heading + paragraph) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Thương hiệu nem nướng lâu năm giữa lòng Hà Nội", "A long-standing nem nuong brand in the heart of Hanoi")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Nem Nướng Xuân Dần tọa lạc tại số 2B Phủ Doãn, phường Hàng Bông, quận Hoàn Kiếm — ngay trung tâm Hà Nội, " +
                "chỉ vài bước chân từ Hồ Hoàn Kiếm và phố cổ. Đây là một trong những địa chỉ nem nướng Ninh Hòa uy tín nhất Thủ đô, " +
                "thu hút cả thực khách địa phương lẫn du khách muốn thưởng thức đặc sản miền Trung ngay giữa phố cổ.",
                "Nem Nuong Xuan Dan restaurant is located at No. 2B Phu Doan, Hang Bong Ward, Hoan Kiem District — right in central Hanoi, " +
                "just steps from Hoan Kiem Lake and the Old Quarter. It is one of the most reputable Ninh Hoa nem nuong addresses in the capital, " +
                "attracting both locals and visitors seeking authentic Central Vietnamese street food in the heart of the old city.")),

            heading(ContentSection.STORY,
                of("Không gian mộc mạc, dân dã mang hồn Việt", "A rustic, homely space with a Vietnamese soul")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng mang kiến trúc mộc mạc, dân dã với hai tầng rộng rãi, sức chứa lên đến 120 thực khách. " +
                "Bàn ghế gỗ giản dị, tường gạch trần và ánh đèn vàng ấm tạo nên bầu không khí gần gũi, thân thiện. " +
                "Dù không có phòng riêng, cách bố trí hợp lý giúp mỗi bàn đều có không gian riêng tư vừa đủ để trò chuyện thoải mái.",
                "The restaurant features a rustic, simple two-story layout with a capacity of up to 120 guests. " +
                "Wooden furniture, exposed brick walls and warm amber lighting create a welcoming, homey atmosphere. " +
                "Though there are no private rooms, the thoughtful arrangement gives each table its own comfortable semi-private space for easy conversation.")),

            heading(ContentSection.STORY,
                of("Nem nướng Ninh Hòa — đặc sản miền Trung chuẩn vị", "Ninh Hoa nem nuong — authentic Central Vietnamese specialty")),
            paragraph(ContentSection.STORY,
                of("Nem nướng Xuân Dần được chế biến theo công thức truyền thống của người Ninh Hòa (Khánh Hòa): " +
                "thịt heo xay nhuyễn, pha trộn cùng gia vị đặc trưng rồi xiên que nướng trên than hoa đến khi vàng đều, thơm phức. " +
                "Thực khách tự tay cuốn nem nướng cùng bánh tráng, rau sống, dưa leo và chấm nước tương đặc — trải nghiệm ăn uống vừa ngon vừa vui.",
                "Xuan Dan's nem nuong is prepared following the traditional Ninh Hoa (Khanh Hoa province) recipe: " +
                "finely ground pork seasoned with signature spices, skewered and grilled over charcoal until evenly golden and fragrant. " +
                "Diners roll the nem nuong themselves with rice paper, fresh vegetables, cucumber and thick hoisin sauce — a delicious and interactive dining experience.")),

            heading(ContentSection.STORY,
                of("Đa dạng lẩu — điểm nhấn cho bữa ăn nhóm", "Diverse hotpot offerings — the highlight for group dining")),
            paragraph(ContentSection.STORY,
                of("Bên cạnh nem nướng, nhà hàng còn nổi bật với các loại lẩu phong phú: lẩu nem nướng, lẩu thái hải sản chua cay, " +
                "lẩu gà sả ớt và lẩu mắm miền Tây đậm đà. Mỗi nồi lẩu đều sử dụng nguyên liệu tươi mới và gia vị chọn lọc, " +
                "phù hợp cho nhóm bạn, gia đình muốn ngồi lâu ăn uống ấm cúng.",
                "Besides nem nuong, the restaurant also excels at a variety of hotpot: nem nuong hotpot, spicy Thai seafood hotpot, " +
                "lemongrass chili chicken hotpot and rich Southern fermented fish hotpot. Each pot uses fresh ingredients and carefully selected spices, " +
                "perfect for groups of friends or families who want a long, cozy meal together.")),

            heading(ContentSection.STORY,
                of("Mức giá bình dân, chất lượng thực sự", "Affordable prices, genuine quality")),
            paragraph(ContentSection.STORY,
                of("Với mức giá trung bình chỉ từ 100.000 – 150.000 đ/người, Nem Nướng Xuân Dần là lựa chọn lý tưởng " +
                "cho những bữa ăn thường ngày, gặp gỡ bạn bè hay liên hoan nhỏ mà không lo ngân sách. " +
                "Giá trị thực sự nằm ở chất lượng nguyên liệu tươi ngon và hương vị đặc trưng đã được giữ vững qua nhiều năm.",
                "At an average price of just 100,000 – 150,000 VND/person, Nem Nuong Xuan Dan is the ideal choice " +
                "for everyday meals, catching up with friends or small celebrations without budget concerns. " +
                "The real value lies in the quality of fresh ingredients and the distinctive flavors consistently maintained over many years.")),

            heading(ContentSection.STORY,
                of("Vị trí thuận tiện — trung tâm phố cổ Hà Nội", "Convenient location — heart of Hanoi's Old Quarter")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm ngay trên phố Phủ Doãn, cách Hồ Hoàn Kiếm chưa đầy 5 phút đi bộ và kề cận với phố Hàng Bông, " +
                "Nhà thờ Lớn Hà Nội. Vị trí này khiến Nem Nướng Xuân Dần trở thành điểm dừng chân lý tưởng sau khi dạo phố cổ " +
                "hay tham quan các danh thắng quanh Hồ Gươm.",
                "The restaurant sits right on Phu Doan Street, less than a 5-minute walk from Hoan Kiem Lake and adjacent to Hang Bong Street " +
                "and Hanoi Cathedral. This prime location makes Nem Nuong Xuan Dan an ideal stop after strolling through the Old Quarter " +
                "or visiting sights around the Sword Lake area.")),

            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên ghé thăm", "Booking policy & why you should visit")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng mở cửa hai ca mỗi ngày: buổi trưa 10:00–14:30 và buổi tối 16:00–22:00, phục vụ cả tuần kể cả cuối tuần. " +
                "Vào giờ cao điểm tối cuối tuần, khách nên đặt bàn trước để tránh chờ đợi. " +
                "Hóa đơn xuất trực tiếp (thanh toán thêm 5% tổng hóa đơn nếu lấy hóa đơn VAT). Đặt bàn qua hệ thống để nhận ưu đãi.",
                "The restaurant opens two sessions daily: lunch 10:00–14:30 and dinner 16:00–22:00, every day including weekends. " +
                "During peak weekend dinner hours, reservations are recommended to avoid waiting. " +
                "Direct invoices available (add 5% surcharge for VAT invoice). Book through the system to enjoy promotional offers.")),

            mapBlock(ContentSection.STORY, new double[]{105.8497, 21.0299})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(null)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc.", "No deposit required."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt bàn qua hệ thống, không áp dụng ngày lễ tết.", "Promotion for guests booking via the system; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy thời điểm, liên hệ nhà hàng để biết thêm.", "Promotion details vary; contact the restaurant for more info."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(10)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("5"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Nhà hàng chỉ xuất hóa đơn trực tiếp; phụ thu 5% tổng hóa đơn nếu lấy hóa đơn VAT.", "Direct invoice only; 5% surcharge applied on total bill for VAT invoice."))
            .serviceChargePercent(null)
            .serviceChargeNotes(of("Không thu phí dịch vụ.", "No service charge."))
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
            .partnerName("MRAVEL Internal Admin")
            .partnerEmail("nlau7559@gmail.com")
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
                .id("tb-2")
                .name(of("Bàn 2", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(10)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(60)
                .allowedDurationsMinutes(List.of(60, 90))
                .note(of("Bàn nhỏ phù hợp cặp đôi hoặc ăn một mình.", "Small table for couples or solo dining."))
                .build(),
            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(75)
                .allowedDurationsMinutes(List.of(60, 75, 90))
                .note(of("Phù hợp gia đình nhỏ hoặc nhóm bạn.", "Suitable for small families or friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8")
                .name(of("Bàn 8", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn cho nhóm đông hoặc liên hoan nhỏ.", "Large table for bigger groups or small gatherings."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 75, 90, 120))
            .defaultDurationMinutes(75)
            .minBookingLeadTimeMinutes(30)
            .graceArrivalMinutes(10)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(15)
            .depositOnly(false)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hoàn Kiếm", "Hoan Kiem District"))
            .wardName(of("P. Hàng Bông", "Hang Bong Ward"))
            .addressLine(of("Số 2B Phủ Doãn, P. Hàng Bông, Q. Hoàn Kiếm, Hà Nội",
                "No. 2B Phu Doan, Hang Bong Ward, Hoan Kiem District, Hanoi"))
            .location(new double[]{105.8497, 21.0299})
            .name(of("Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn", "Nem Nuong Xuan Dan Restaurant - Phu Doan"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nem nướng Ninh Hòa & lẩu đa dạng ngay trung tâm phố cổ Hà Nội, mức giá bình dân 100-150K/người.",
                "Ninh Hoa nem nuong & diverse hotpot in the heart of Hanoi's Old Quarter, budget-friendly at 100-150K/person."))
            .description(of("Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn là một trong những địa chỉ nem nướng Ninh Hòa uy tín nhất tại Hà Nội, " +
                "tọa lạc ngay 2B Phủ Doãn, phường Hàng Bông, quận Hoàn Kiếm. Không gian mộc mạc 2 tầng với sức chứa 120 người, " +
                "chuyên phục vụ nem nướng đặc sản miền Trung và các loại lẩu đa dạng. " +
                "Mức giá bình dân 100.000–150.000đ/người, phù hợp mọi dịp từ bữa trưa văn phòng đến tụ họp bạn bè.",
                "Nem Nuong Xuan Dan - Phu Doan is one of Hanoi's most reputable Ninh Hoa nem nuong addresses, " +
                "located at 2B Phu Doan, Hang Bong Ward, Hoan Kiem District. A rustic two-story space seating 120 guests, " +
                "specialising in Central Vietnamese nem nuong and a wide variety of hotpots. " +
                "Budget-friendly at 100,000–150,000 VND/person, suitable for office lunches, friend gatherings and family meals alike."))
            .phone("0931006005")
            .email(null)
            .website("https://pasgo.vn/nha-hang/nem-nuong-xuan-dan-phu-doan")
            .facebookPage(null)
            .bookingHotline("0931006005")
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
