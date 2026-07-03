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
public class SeedFuRongHuaDinhTienHoang {

    @Bean
    CommandLineRunner seedFuRongHuaDinhTienHoangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedFuRongHuaDinhTienHoang] start");

            RestaurantDoc doc = restaurantFuRongHuaDinhTienHoang();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedFuRongHuaDinhTienHoang] done");
        };
    }

    // =====================================================================
    //        FU RONG HUA ĐINH TIÊN HOÀNG (HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantFuRongHuaDinhTienHoang() {
        String slug = "fu-rong-hua-dinh-tien-hoang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-1-normal-2316847763676.webp",
                of("Không gian sang trọng Fu Rong Hua Đinh Tiên Hoàng", "Fu Rong Hua Dinh Tien Hoang elegant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-2-normal-2316847863677.webp",
                of("Phòng ăn chính phong cách Hong Kong", "Main dining room in Hong Kong style"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-3-normal-2316847963678.webp",
                of("Khu vực buffet dim sum phong phú", "Rich dim sum buffet area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-4-normal-2316848063679.webp",
                of("Bàn tiệc được set sẵn sang trọng", "Elegantly set banquet table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-5-normal-2316848163680.webp",
                of("Không gian tầng trên nhà hàng Fu Rong Hua", "Upper floor dining space of Fu Rong Hua"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-6-normal-2316848263681.webp",
                of("Món ăn Quảng Đông đặc sắc", "Signature Cantonese dishes"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-7-normal-2316848363682.webp",
                of("Hà cảo và dim sum chuẩn vị Hong Kong", "Authentic Hong Kong-style dim sum and ha cao"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-8-normal-2316849063683.webp",
                of("Vịt quay Bắc Kinh đặc trưng", "Signature Peking duck"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-9-normal-2316849163684.webp",
                of("Phòng riêng VIP trang trọng", "Elegant VIP private room"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-10-normal-2316849263685.webp",
                of("Không gian buffet cuối tuần đông đúc", "Busy weekend buffet atmosphere"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-11-normal-2316849763686.webp",
                of("Bộ sưu tập món Á - Trung đặc sắc", "Signature Asian-Chinese dish collection"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-fu-rong-hua-dinh-tien-hoang-12-normal-2316850063687.webp",
                of("Toàn cảnh không gian rộng rãi Fu Rong Hua", "Panoramic view of the spacious Fu Rong Hua dining area"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá (lấy hết) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Buffet/furonghua-dth-bf-18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-1.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-2.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-3.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-4.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-5.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-6.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-7.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-8.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-9.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/FuronghuaDTH/Alacarte/furonghua-dth-alacarte-menu-10.jpg",
                of("", ""), false, 27)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("CANTONESE")
                .name(of("Quảng Đông - Hong Kong", "Cantonese - Hong Kong"))
                .region("CHINA")
                .build(),
            CuisineTag.builder()
                .code("CHINESE")
                .name(of("Trung Hoa", "Chinese"))
                .region("CHINA")
                .build(),
            CuisineTag.builder()
                .code("DIM_SUM")
                .name(of("Dimsum & Điểm tâm", "Dim Sum & Yum Cha"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_CHINESE")
                .name(of("Buffet Trung Hoa", "Chinese Buffet"))
                .region("CHINA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,     7, 0, 23, 30),
            openingHour(DayOfWeek.TUESDAY,    7, 0, 23, 30),
            openingHour(DayOfWeek.WEDNESDAY,  7, 0, 23, 30),
            openingHour(DayOfWeek.THURSDAY,   7, 0, 23, 30),
            openingHour(DayOfWeek.FRIDAY,     7, 0, 23, 30),
            openingHour(DayOfWeek.SATURDAY,   7, 0, 23, 30),
            openingHour(DayOfWeek.SUNDAY,     7, 0, 23, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",         of("Bữa ăn gia đình", "Family dining")),
            suitable("FRIENDS",        of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY",  of("Tiệc liên hoan công ty", "Company party")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách đối tác", "Business meal")),
            suitable("ANNIVERSARY",    of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("BIRTHDAY",       of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("GROUP_MEETING",  of("Họp nhóm", "Group meeting")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp", "Corporate event")),
            suitable("COUPLE",         of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("LUXURIOUS",  of("Sang trọng, đẳng cấp", "Luxurious and upscale")),
            ambience("ELEGANT",    of("Tinh tế, thanh lịch", "Elegant and refined")),
            ambience("TRADITIONAL",of("Mang đậm văn hóa Hoa", "Rich Chinese cultural ambience")),
            ambience("FORMAL",     of("Trang trọng, phù hợp tiệc lớn", "Formal, suitable for grand banquets"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(80)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Há cảo tôm tươi", "Fresh shrimp ha cao (steamed dumpling)")),
            signatureDishL(of("Vịt quay Bắc Kinh", "Peking roasted duck")),
            signatureDishL(of("Chân gà hấp tàu xì", "Steamed chicken feet with black bean sauce")),
            signatureDishL(of("Bánh bao kim sa", "Golden salted egg yolk steamed bun")),
            signatureDishL(of("Chả giò hải sản Fu Rong Hua", "Fu Rong Hua seafood spring rolls")),
            signatureDishL(of("Canh đuôi bò tiềm thuốc Bắc", "Braised oxtail with herbal Chinese medicine soup")),
            signatureDishL(of("Ba ba hầm kiểu Quảng Đông", "Cantonese-style braised soft-shell turtle")),
            signatureDishL(of("Hủ tíu xào bò khô", "Stir-fried rice noodles with dry beef")),
            signatureDishL(of("Mì sủi cảo tôm", "Shrimp wonton noodle soup")),
            signatureDishL(of("Mì vịt tiềm Quảng Đông", "Cantonese braised duck noodle"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_DIMSUM")
                .name(of("Buffet Dimsum & Quảng Đông", "Dim Sum & Cantonese Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Dimsum sáng & trưa", "Morning & lunch dim sum buffet"))
                        .description(of("Bao gồm hơn 50 món dim sum Quảng Đông chính thống phục vụ theo băng chuyền hoặc gọi trực tiếp.", "Includes 50+ authentic Cantonese dim sum items served via trolley or direct order."))
                        .priceFrom(new BigDecimal("300000"))
                        .priceTo(new BigDecimal("400000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "dimsum", "cantonese"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("A_LA_CARTE_CHINESE")
                .name(of("Gọi món Trung Hoa", "Chinese A La Carte"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Vịt quay Bắc Kinh (nửa con)", "Peking duck (half)"))
                        .description(of("Vịt quay vàng ruộm, da giòn, phục vụ kèm bánh mì và hành.", "Golden roasted duck with crispy skin, served with pancakes and spring onions."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("600000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("duck", "peking", "specialty"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Há cảo tôm tươi (4 cái)", "Fresh shrimp ha cao (4 pieces)"))
                        .description(of("Há cảo bọc tôm tươi nguyên con, vỏ mỏng dai, hấp đúng chuẩn vị Hong Kong.", "Whole fresh shrimp ha cao with thin elastic wrapper, steamed to authentic Hong Kong standards."))
                        .priceFrom(new BigDecimal("85000"))
                        .priceTo(new BigDecimal("95000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("dimsum", "shrimp", "steamed"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bảo vệ hướng dẫn đỗ xe gần nhà hàng", "Security staff guides parking near the restaurant"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("73 Cầu Gỗ (miễn phí)", "73 Cau Go (free)"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi tại 73 Cầu Gỗ miễn phí. Ô tô có bảo vệ hướng dẫn, phí phụ thuộc đơn vị trông giữ.", "Free motorbike parking at 73 Cau Go. Car parking guided by security, fee depends on the parking operator."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "WIFI",
            "AIR_CONDITIONING",
            "PROJECTOR",
            "SOUND_LIGHT",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "FREE_MOTORBIKE_PARKING",
            "EVENT_DECOR",
            "MC_SERVICE",
            "VISA_MASTER",
            "VAT_INVOICE_DIRECT",
            "BIRTHDAY_CAKE_ALLOWED",
            "GROUP_BOOKING",
            "DIM_SUM_CART_SERVICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of(
                "Tổng quan Fu Rong Hua Đinh Tiên Hoàng",
                "Overview: Fu Rong Hua Dinh Tien Hoang")),
            paragraph(ContentSection.STORY,
                of("Fu Rong Hua Đinh Tiên Hoàng là nhà hàng ẩm thực Trung Hoa phong cách Hong Kong - Quảng Đông danh tiếng " +
                   "tọa lạc tại số 09 Đinh Tiên Hoàng, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội. " +
                   "Với hơn 200 món ăn chuẩn vị Hong Kong do đội ngũ bếp trưởng người gốc Hoa chính thống chế biến, " +
                   "đây là địa chỉ không thể bỏ qua cho những ai yêu thích nền ẩm thực Trung Hoa đích thực tại Hà Nội.",
                   "Fu Rong Hua Dinh Tien Hoang is a prestigious Hong Kong-Cantonese style Chinese restaurant " +
                   "located at No. 09 Dinh Tien Hoang, Hang Trong Ward, Hoan Kiem District, Hanoi. " +
                   "With over 200 authentic Hong Kong-style dishes prepared by a team of ethnic Chinese head chefs, " +
                   "this is a must-visit address for lovers of authentic Chinese cuisine in Hanoi.")),

            heading(ContentSection.STORY, of(
                "Không gian & sức chứa",
                "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Fu Rong Hua Đinh Tiên Hoàng sở hữu không gian sang trọng, trang trọng mang đậm văn hóa Trung Hoa " +
                   "với sức chứa lên đến 250 thực khách. Khu vực phòng ăn chính rộng rãi được thiết kế với bàn tròn truyền thống, " +
                   "thích hợp cho tiệc gia đình và tiếp khách. Đặc biệt, nhà hàng có 5 phòng riêng VIP với sức chứa từ 10-30 khách " +
                   "mỗi phòng, lý tưởng cho các buổi họp kinh doanh kín và tiệc quan trọng.",
                   "Fu Rong Hua Dinh Tien Hoang boasts a luxurious, formal space steeped in Chinese cultural aesthetics " +
                   "with a capacity of up to 250 guests. The spacious main dining area is designed with traditional round tables, " +
                   "suitable for family banquets and business entertainment. Notably, the restaurant has 5 VIP private rooms " +
                   "with a capacity of 10-30 guests each, ideal for confidential business meetings and important banquets.")),

            heading(ContentSection.STORY, of(
                "Thực đơn & món đặc sắc",
                "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Fu Rong Hua trải dài hơn 200 món Quảng Đông - Hong Kong chính thống, " +
                   "từ các món dim sum trứ danh như há cảo tôm tươi, chân gà hấp tàu xì, bánh bao kim sa, " +
                   "đến các món đại tiệc hảo hạng như vịt quay Bắc Kinh, baba hầm kiểu Quảng Đông, " +
                   "canh đuôi bò tiềm thuốc Bắc và nhiều món hải sản cao cấp được chế biến theo công thức truyền thống Trung Hoa. " +
                   "Khung giờ buffet dimsum sáng và trưa luôn đông khách nhờ sự phong phú và chất lượng ổn định.",
                   "Fu Rong Hua's menu spans 200+ authentic Cantonese-Hong Kong dishes, " +
                   "from the famous dim sum items such as fresh shrimp ha cao, steamed chicken feet with black bean sauce, " +
                   "and golden salted egg yolk buns, to premium banquet dishes such as Peking duck, " +
                   "Cantonese-style braised soft-shell turtle, braised oxtail with herbal Chinese medicine soup, " +
                   "and many high-end seafood dishes prepared according to traditional Chinese recipes. " +
                   "The morning and lunch dim sum buffet is always packed thanks to the variety and consistent quality.")),

            heading(ContentSection.STORY, of(
                "Phù hợp dịp nào",
                "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với không gian sang trọng và thực đơn phong phú, Fu Rong Hua Đinh Tiên Hoàng phù hợp cho " +
                   "mọi dịp từ bữa ăn gia đình cuối tuần, tiệc sinh nhật, kỷ niệm, đến tiệc công ty và tiếp khách đối tác. " +
                   "Hệ thống phòng riêng VIP đặc biệt thích hợp cho các cuộc họp bí mật, ký kết hợp đồng " +
                   "và các bữa tiệc quan trọng cần sự riêng tư và đẳng cấp.",
                   "With its luxurious setting and rich menu, Fu Rong Hua Dinh Tien Hoang is suitable for " +
                   "all occasions from weekend family meals, birthday parties and anniversaries, to company banquets and business entertainment. " +
                   "The VIP private room system is especially appropriate for confidential meetings, contract signings, " +
                   "and important banquets requiring privacy and prestige.")),

            heading(ContentSection.STORY, of(
                "Dịch vụ & tiện ích",
                "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Fu Rong Hua Đinh Tiên Hoàng cung cấp dịch vụ tiệc toàn diện: từ trang trí theo chủ đề theo yêu cầu, " +
                   "dịch vụ MC chuyên nghiệp cho các sự kiện, hệ thống âm thanh ánh sáng hiện đại " +
                   "đến dịch vụ dim sum băng chuyền đặc trưng phong cách Hong Kong. " +
                   "Nhà hàng hỗ trợ đặt tiệc theo bộ thực đơn cố định với giá trọn gói hoặc gọi món tùy ý. " +
                   "Đặc biệt phù hợp cho khách hàng văn phòng, gia đình và đoàn doanh nghiệp.",
                   "Fu Rong Hua Dinh Tien Hoang provides comprehensive banquet services: from themed decoration by request, " +
                   "professional MC services for events, modern audio-visual systems " +
                   "to the signature Hong Kong-style dim sum trolley service. " +
                   "The restaurant supports set menu banquet bookings at package prices or a la carte ordering. " +
                   "Especially suitable for office clients, families, and corporate delegations.")),

            heading(ContentSection.STORY, of(
                "Vị trí & di chuyển",
                "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc ngay tại số 09 Đinh Tiên Hoàng, Phường Hàng Trống, Quận Hoàn Kiếm — trái tim của Thủ đô Hà Nội, " +
                   "Fu Rong Hua chỉ cách Hồ Hoàn Kiếm vài trăm mét và gần phố cổ Hà Nội. " +
                   "Vị trí trung tâm thuận tiện đi lại bằng mọi phương tiện: taxi, xe máy hay đi bộ từ khu phố cổ. " +
                   "Xe máy gửi miễn phí tại 73 Cầu Gỗ, ô tô có bảo vệ hướng dẫn đỗ xe.",
                   "Located at No. 09 Dinh Tien Hoang, Hang Trong Ward, Hoan Kiem District — the heart of Hanoi, " +
                   "Fu Rong Hua is just a few hundred meters from Hoan Kiem Lake and near the Hanoi Old Quarter. " +
                   "The central location is convenient by all means of transport: taxi, motorbike, or walking from the old quarter. " +
                   "Free motorbike parking at 73 Cau Go; car parking guided by security staff.")),

            heading(ContentSection.STORY, of(
                "Chính sách đặt bàn & lý do nên chọn",
                "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước 24/7 qua điện thoại và hệ thống trực tuyến. " +
                   "Nhóm từ 20 người trở lên cần đặt cọc trước để đảm bảo chỗ. " +
                   "Nhà hàng chỉ xuất hóa đơn trực tiếp và luôn thu phí dịch vụ 5%. " +
                   "Không cho mang đồ ăn, thức uống từ ngoài vào. " +
                   "Với đội ngũ bếp người gốc Hoa, hơn 200 món chuẩn vị, không gian trang trọng tại trung tâm Hoàn Kiếm, " +
                   "Fu Rong Hua là lựa chọn hàng đầu khi muốn trải nghiệm ẩm thực Trung Hoa đích thực tại Hà Nội.",
                   "The restaurant accepts advance bookings 24/7 by phone and online system. " +
                   "Groups of 20 or more need to place a deposit in advance to secure their spot. " +
                   "The restaurant issues direct invoices only and always charges a 5% service fee. " +
                   "Outside food and drinks are not permitted. " +
                   "With an ethnic Chinese kitchen team, 200+ authentic dishes, and a formal setting in central Hoan Kiem, " +
                   "Fu Rong Hua is the top choice for experiencing genuine Chinese cuisine in Hanoi.")),

            mapBlock(ContentSection.STORY, new double[]{105.8635121, 21.0030946})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(20)
            .depositAmount(new BigDecimal("1000000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 20 khách trở lên. Đặt cọc qua chuyển khoản hoặc trực tiếp.", "Applies to bookings of 20 or more guests. Deposit via bank transfer or in person."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt chỗ qua PasGo, không áp dụng đồng thời với ưu đãi khác.", "Promotion for guests booking via PasGo; not applicable with other simultaneous promotions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng để biết thêm.", "Promotion details vary by time; please contact the restaurant for more information."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(false)
            .vatPercent(null)
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Nhà hàng chỉ xuất hóa đơn trực tiếp theo yêu cầu.", "The restaurant issues direct invoices upon request only."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho toàn bộ hóa đơn.", "A 5% service charge applies to all bills."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn, đồ uống từ ngoài vào nhà hàng.", "Outside food and drinks are not allowed inside the restaurant."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ ngoài vào nhà hàng.", "Outside drinks are not allowed inside the restaurant."))
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-6-std")
                .name(of("Bàn tròn tiêu chuẩn 6 người", "Standard round table for 6"))
                .seats(6)
                .minPeople(2)
                .maxPeople(6)
                .totalTables(20)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tròn truyền thống, phù hợp gia đình và nhóm nhỏ.", "Traditional round table for families and small groups."))
                .build(),
            TableType.builder()
                .id("tb-10-grp")
                .name(of("Bàn đại tiệc 10 người", "Banquet table for 10"))
                .seats(10)
                .minPeople(6)
                .maxPeople(10)
                .totalTables(10)
                .depositPrice(new BigDecimal("700000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn đại tiệc tròn lớn, phù hợp nhóm đông và liên hoan.", "Large round banquet table for larger groups and celebrations."))
                .build(),
            TableType.builder()
                .id("pr-vip-30")
                .name(of("Phòng VIP 10-30 khách", "VIP private room 10-30 guests"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(5)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng VIP riêng biệt, phù hợp tiệc quan trọng và họp kinh doanh.", "Exclusive VIP private room for important banquets and business meetings."))
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
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hoàn Kiếm", "Hoan Kiem District"))
            .wardName(of("P. Hàng Trống", "Hang Trong Ward"))
            .addressLine(of("Số 09 Đinh Tiên Hoàng, P. Hàng Trống, Q. Hoàn Kiếm, Hà Nội", "No. 09 Dinh Tien Hoang, Hang Trong Ward, Hoan Kiem District, Hanoi"))
            .location(new double[]{105.8635121, 21.0030946})
            .name(of("Fu Rong Hua Đinh Tiên Hoàng", "Fu Rong Hua Dinh Tien Hoang"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng Buffet Trung Hoa phong cách Hong Kong - Quảng Đông với hơn 200 món chuẩn vị tại trung tâm Hoàn Kiếm.", "Hong Kong-Cantonese Chinese buffet restaurant with 200+ authentic dishes in central Hoan Kiem."))
            .description(of("Fu Rong Hua Đinh Tiên Hoàng là nhà hàng ẩm thực Trung Hoa phong cách Hong Kong - Quảng Đông danh tiếng tại Hà Nội, " +
                "tọa lạc tại số 09 Đinh Tiên Hoàng, Quận Hoàn Kiếm. " +
                "Với hơn 200 món ăn chuẩn vị do bếp trưởng người gốc Hoa chính thống chế biến, " +
                "không gian sang trọng và hệ thống phòng riêng VIP, đây là lựa chọn hàng đầu " +
                "cho các bữa tiệc gia đình, kinh doanh và sự kiện quan trọng tại trung tâm Hà Nội.",
                "Fu Rong Hua Dinh Tien Hoang is a prestigious Hong Kong-Cantonese Chinese restaurant in Hanoi, " +
                "located at No. 09 Dinh Tien Hoang, Hoan Kiem District. " +
                "With 200+ authentic dishes prepared by ethnic Chinese head chefs, " +
                "a luxurious setting and VIP private room system, this is the top choice " +
                "for family banquets, business entertainment, and important events in central Hanoi."))
            .phone("0961538460")
            .email("info@furonghua.vn")
            .website("https://pasgo.vn/nha-hang/fu-rong-hua-dinh-tien-hoang-5415")
            .bookingHotline("0961538460")
            .minPricePerPerson(new BigDecimal("300000"))
            .maxPricePerPerson(new BigDecimal("400000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
            .priceBucket(PriceBucket.FROM_350K_TO_500K)
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
