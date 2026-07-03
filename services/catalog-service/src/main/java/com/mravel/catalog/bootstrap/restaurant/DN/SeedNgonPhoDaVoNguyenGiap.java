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
public class SeedNgonPhoDaVoNguyenGiap {

    @Bean
    CommandLineRunner seedNgonPhoDaVoNguyenGiapRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNgonPhoDaVoNguyenGiap] start");
            RestaurantDoc doc = restaurantNgonPhoDa();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedNgonPhoDaVoNguyenGiap] done");
        };
    }

    // =====================================================================
    //   NGON PHO DA - Homey Authentic Vietnamese Cuisine - Vo Nguyen Giap
    //   300 Vo Nguyen Giap, Phuong Ngu Hanh Son, Da Nang
    // =====================================================================

    private static RestaurantDoc restaurantNgonPhoDa() {
        String slug = "ngon-pho-da-vo-nguyen-giap-da-nang";

        // ---------- Gallery ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-1-normal-4241793673336.webp",
                of("Không gian nhà hàng Ngon Phố Đà view biển Mỹ An", "Ngon Pho Da restaurant interior with My An beach view"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-2-normal-4241793773337.webp",
                of("Khu vực ăn uống tầng 1 Ngon Phố Đà", "Ground floor dining area at Ngon Pho Da"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-3-normal-4241793873338.webp",
                of("Không gian sang trọng, ấm cúng tại Ngon Phố Đà", "Elegant and cozy atmosphere at Ngon Pho Da"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-4-normal-4241793973339.webp",
                of("Bàn ăn nhìn ra biển tại Ngon Phố Đà", "Dining table with sea view at Ngon Pho Da"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-5-normal-4241794073340.webp",
                of("Khu vực ngoài trời Ngon Phố Đà", "Outdoor dining area at Ngon Pho Da"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-6-normal-4241794173341.webp",
                of("Không gian tầng 2 Ngon Phố Đà view biển", "Second floor with beach view at Ngon Pho Da"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-8-normal-4241794373342.webp",
                of("Các món đặc sản miền Trung tại Ngon Phố Đà", "Central Vietnamese specialties at Ngon Pho Da"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-9-normal-4241794473344.webp",
                of("Gỏi cuốn tôm tươi đặc sắc", "Fresh shrimp spring rolls specialty"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-10-normal-4241794573345.webp",
                of("Không gian bàn tiệc gia đình Ngon Phố Đà", "Family banquet setup at Ngon Pho Da"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-11-normal-4241794673570.webp",
                of("Khu vực tiếp khách đặc biệt", "Special guest reception area"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-12-normal-4241794773571.webp",
                of("Không gian ẩm thực miền Trung truyền thống", "Traditional Central Vietnamese dining space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-13-normal-4241794873572.webp",
                of("Món ăn miền Trung phong phú tại Ngon Phố Đà", "Diverse Central Vietnamese dishes at Ngon Pho Da"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-14-normal-4241794973573.webp",
                of("Bàn tiệc sinh nhật và kỷ niệm", "Birthday and anniversary banquet table"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-da-15-normal-4241795073574.webp",
                of("View biển Mỹ An từ nhà hàng Ngon Phố Đà", "My An beach view from Ngon Pho Da restaurant"), false, 13)
        );

        // ---------- Menu images ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/food/ngonphoda-menu-food-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-1.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-2.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-3.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-4.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-5.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-6.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-7.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-8.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-9.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-10.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-11.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-12.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-13.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-14.jpg",
                of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-15.jpg",
                of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/thuytrang/ngonphoda/drink/ngonphoda-menu-drink-16.jpg",
                of("", ""), false, 29)
        );

        // ---------- Cuisines ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt Nam", "Vietnamese cuisine"))
                .region("SOUTHEAST_ASIA")
                .build(),
            CuisineTag.builder()
                .code("CENTRAL_VIETNAMESE")
                .name(of("Ẩm thực miền Trung", "Central Vietnamese cuisine"))
                .region("CENTRAL_VIETNAM")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản biển tươi", "Fresh seafood"))
                .region("COASTAL")
                .build()
        );

        // ---------- Opening hours (10:00 - 22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
        );

        // ---------- Suitable for ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",          of("Ăn gia đình", "Family dining")),
            suitable("BIRTHDAY",        of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("BUSINESS_MEAL",   of("Tiếp khách doanh nghiệp", "Business entertaining")),
            suitable("COUPLE",          of("Hẹn hò lãng mạn view biển", "Romantic date with beach view")),
            suitable("ANNIVERSARY",     of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("COMPANY_PARTY",   of("Tiệc công ty", "Company party")),
            suitable("FRIENDS",         of("Tụ tập bạn bè", "Friends gathering")),
            suitable("GROUP_MEETING",   of("Họp nhóm ăn trưa", "Group lunch meeting")),
            suitable("OFFICE_LUNCH",    of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_EVENT",   of("Sự kiện doanh nghiệp quy mô lớn", "Large corporate event"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("TRADITIONAL",  of("Truyền thống, đậm chất miền Trung", "Traditional Central Vietnamese style")),
            ambience("ELEGANT",      of("Sang trọng, tinh tế", "Elegant and refined")),
            ambience("COZY",         of("Ấm cúng, gần gũi", "Warm and cozy")),
            ambience("ROMANTIC",     of("Lãng mạn view biển Mỹ An", "Romantic My An beach view"))
        );

        // ---------- Capacity ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(260)
            .maxGroupSize(80)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(60)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Signature dishes ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Gỏi cuốn tôm tươi", "Fresh shrimp spring rolls")),
            signatureDishL(of("Chả giò Ngon Phố Đà đặc biệt", "Ngon Pho Da signature fried spring rolls")),
            signatureDishL(of("Nem cua bể đặc sản miền Trung", "Central Vietnamese sea crab fried rolls")),
            signatureDishL(of("Ram cá thu tam giác giòn rụm", "Crispy mackerel triangle rolls")),
            signatureDishL(of("Mì Quảng tôm thịt truyền thống", "Traditional Quang noodles with shrimp and pork")),
            signatureDishL(of("Bánh xèo miền Trung giòn tan", "Crispy Central Vietnamese sizzling crepe")),
            signatureDishL(of("Cá thu nướng muối ớt", "Grilled mackerel with chili salt")),
            signatureDishL(of("Canh chua cá biển tươi", "Fresh sea fish sour soup")),
            signatureDishL(of("Ốc hương xào sả ớt", "Stir-fried scallops with lemongrass and chili")),
            signatureDishL(of("Lẩu hải sản thập cẩm view biển", "Mixed seafood hotpot with beach view"))
        );

        // ---------- Menu sections ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("APPETIZER")
                .name(of("Khai vị & Gỏi cuốn", "Appetizers & Spring Rolls"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Gỏi cuốn tôm tươi (5 cuốn)", "Fresh shrimp spring rolls (5 pcs)"))
                        .description(of("Gỏi cuốn tôm tươi bọc rau sống và bún, chấm tương hoisin đậu phộng.", "Fresh shrimp spring rolls with vegetables and vermicelli, served with hoisin peanut dipping sauce."))
                        .priceFrom(new BigDecimal("45000"))
                        .priceTo(new BigDecimal("65000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("appetizer", "signature", "vietnamese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Nem cua bể đặc sản (5 cái)", "Sea crab fried rolls (5 pcs)"))
                        .description(of("Nem cua bể chiên giòn, nhân cua biển tươi, ăn kèm rau sống và nước chấm chua ngọt.", "Crispy sea crab fried rolls, filled with fresh sea crab, served with fresh vegetables and sweet sour dipping sauce."))
                        .priceFrom(new BigDecimal("65000"))
                        .priceTo(new BigDecimal("85000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("appetizer", "signature", "seafood"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món chính miền Trung", "Central Vietnamese main courses"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Mì Quảng tôm thịt", "Quang noodles with shrimp and pork"))
                        .description(of("Mì Quảng truyền thống với tôm tươi, thịt heo, trứng cút, chan nước lèo đậm đà.", "Traditional Quang noodles with fresh shrimp, pork, quail eggs and rich broth."))
                        .priceFrom(new BigDecimal("70000"))
                        .priceTo(new BigDecimal("90000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("main", "noodle", "central-vietnamese"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cá thu nướng muối ớt", "Grilled mackerel with chili salt"))
                        .description(of("Cá thu tươi nướng than hoa, ướp muối ớt đặc trưng miền Trung, ăn kèm rau sống và bánh tráng.", "Fresh mackerel grilled over charcoal, marinated in Central Vietnamese chili salt, served with fresh vegetables and rice paper."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("main", "grilled", "seafood", "signature"))
                        .build()
                ))
                .build()
        );

        // ---------- Parking ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi gửi xe ô tô trước nhà hàng, có bảo vệ hướng dẫn", "Car parking in front of the restaurant, supervised by security"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi gửi xe máy tại nhà hàng, có bảo vệ hướng dẫn", "Motorbike parking at the restaurant, supervised by security"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe máy và ô tô. Có bảo vệ trực 24/7.", "Free parking for both motorbikes and cars. Security on duty 24/7."))
            .build();

        // ---------- Amenities ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "BEACH_VIEW",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "SET_LUNCH",
            "EVENT_DECOR",
            "SOUND_LIGHT",
            "ELEVATOR_ACCESS"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Ngon Phố Đà", "About Ngon Pho Da")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Đà - Homey Authentic Vietnamese Cuisine là nhà hàng ẩm thực Việt mang đậm phong vị miền Trung, " +
                   "tọa lạc tại số 300 đường Võ Nguyên Giáp, Phường Ngũ Hành Sơn, TP. Đà Nẵng. " +
                   "Nhà hàng sở hữu vị trí đắc địa ngay cạnh biển Mỹ An — một trong những bãi biển đẹp nhất Đà Nẵng, " +
                   "mang đến không gian ăn uống vừa sang trọng vừa ấm cúng, gần gũi với thiên nhiên. " +
                   "Với hơn 260 chỗ ngồi trải dài trên 2 tầng, Ngon Phố Đà là điểm đến lý tưởng cho mọi dịp — " +
                   "từ bữa cơm gia đình, tiệc sinh nhật đến các sự kiện doanh nghiệp quy mô lớn.",
                   "Ngon Pho Da - Homey Authentic Vietnamese Cuisine is a restaurant specializing in Central Vietnamese cuisine, " +
                   "located at 300 Vo Nguyen Giap Street, Ngu Hanh Son Ward, Da Nang. " +
                   "The restaurant boasts a prime location right next to My An Beach — one of Da Nang's most beautiful beaches, " +
                   "offering a dining space that is both elegant and cozy, close to nature. " +
                   "With over 260 seats spread across 2 floors, Ngon Pho Da is the ideal destination for any occasion — " +
                   "from family meals, birthday parties to large corporate events.")),
            heading(ContentSection.STORY, of("View Biển Mỹ An — Điểm Nhấn Độc Đáo", "My An Beach View — A Unique Highlight")),
            paragraph(ContentSection.STORY,
                of("Điều làm nên sức hút riêng biệt của Ngon Phố Đà chính là tầm nhìn trực tiếp ra biển Mỹ An xanh mướt. " +
                   "Từ khu vực ăn uống tầng 2 và ban công ngoài trời, thực khách có thể chiêm ngưỡng bãi biển cát trắng, " +
                   "sóng biển êm đềm và bầu trời trong xanh của Đà Nẵng. " +
                   "Buổi sáng sớm hay lúc hoàng hôn đều mang lại trải nghiệm khác biệt — " +
                   "ánh nắng vàng phản chiếu trên mặt biển tạo nên khung cảnh lãng mạn, " +
                   "khiến mỗi bữa ăn trở thành một kỷ niệm đáng nhớ trong chuyến du lịch Đà Nẵng.",
                   "What makes Ngon Pho Da uniquely attractive is its direct view of the turquoise My An Beach. " +
                   "From the second-floor dining area and outdoor balcony, guests can admire white sandy beaches, " +
                   "gentle waves, and the clear blue skies of Da Nang. " +
                   "Whether at dawn or sunset, each time of day offers a different experience — " +
                   "the golden sunlight reflecting on the sea surface creates a romantic scene, " +
                   "making every meal a memorable moment during a Da Nang trip.")),
            heading(ContentSection.STORY, of("Không Gian & Sức Chứa", "Space & Capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng được thiết kế theo phong cách truyền thống miền Trung kết hợp nội thất hiện đại, " +
                   "tạo không gian vừa sang trọng vừa ấm áp. Tầng 1 phù hợp cho bữa ăn gia đình và nhóm nhỏ, " +
                   "tầng 2 rộng rãi với hệ thống phòng riêng (sức chứa đến 60 khách/phòng) thích hợp tổ chức tiệc " +
                   "sinh nhật, kỷ niệm hoặc họp mặt doanh nghiệp. " +
                   "Khu vực ngoài trời ban công view biển là lựa chọn được ưa thích nhất, " +
                   "đặc biệt vào buổi chiều và tối khi gió biển thổi nhẹ và mặt trời lặn tuyệt đẹp. " +
                   "Tổng sức chứa 260 khách, bãi đỗ xe miễn phí cho cả ô tô và xe máy.",
                   "The restaurant is designed in a traditional Central Vietnamese style combined with modern interiors, " +
                   "creating a space that is both luxurious and warm. The ground floor is suitable for family meals and small groups, " +
                   "while the spacious second floor features private rooms (capacity up to 60 guests/room) " +
                   "ideal for organizing birthday parties, anniversaries, or corporate gatherings. " +
                   "The outdoor balcony with beach views is the most popular choice, " +
                   "especially in the afternoon and evening when the sea breeze blows gently and sunsets are spectacular. " +
                   "Total capacity of 260 guests, with free parking for both cars and motorbikes.")),
            heading(ContentSection.STORY, of("Thực Đơn & Món Đặc Sắc", "Menu & Signature Dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Ngon Phố Đà tập trung vào tinh hoa ẩm thực miền Trung với nguyên liệu hải sản tươi sống " +
                   "được lấy trực tiếp từ biển Đà Nẵng mỗi ngày. Nổi bật nhất là bộ tứ khai vị đặc trưng: " +
                   "gỏi cuốn tôm tươi, chả giò Ngon Phố Đà, nem cua bể và ram cá thu tam giác — " +
                   "bốn món được thực khách đánh giá cao nhất và đặt nhiều nhất. " +
                   "Món chính đa dạng từ mì Quảng truyền thống, bánh xèo miền Trung giòn rụm, " +
                   "cá thu nướng muối ớt đến các món lẩu hải sản thập cẩm phong phú. " +
                   "Mức giá trung bình từ 100.000 – 200.000đ/người, phù hợp cho cả bữa ăn thường ngày lẫn tiệc đặc biệt.",
                   "Ngon Pho Da's menu focuses on Central Vietnamese culinary excellence with fresh seafood " +
                   "sourced directly from Da Nang's waters every day. Most notable is the signature appetizer quartet: " +
                   "fresh shrimp spring rolls, Ngon Pho Da fried rolls, sea crab rolls, and mackerel triangle rolls — " +
                   "four dishes most praised and ordered by guests. " +
                   "Main courses are diverse, from traditional Quang noodles, crispy Central Vietnamese sizzling crepes, " +
                   "chili-salt grilled mackerel to rich mixed seafood hotpots. " +
                   "Average price range from 100,000 – 200,000 VND/person, suitable for both everyday meals and special occasions.")),
            heading(ContentSection.STORY, of("Phù Hợp Dịp Nào", "Suitable Occasions")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Đà là lựa chọn hoàn hảo cho các gia đình du lịch Đà Nẵng muốn thưởng thức ẩm thực miền Trung " +
                   "đích thực trong không gian view biển. Các cặp đôi sẽ yêu thích bàn ngoài trời lãng mạn nhìn ra biển Mỹ An, " +
                   "đặc biệt vào buổi tối khi ánh đèn lung linh. Nhà hàng cũng là điểm tổ chức tiệc sinh nhật, " +
                   "kỷ niệm hoặc họp mặt đồng nghiệp lý tưởng với không gian riêng tư tầng 2. " +
                   "Các đoàn khách công ty, đối tác doanh nghiệp sẽ được tiếp đón chu đáo trong phòng riêng " +
                   "với dịch vụ chuyên nghiệp và thực đơn đa dạng đáp ứng mọi yêu cầu.",
                   "Ngon Pho Da is the perfect choice for families visiting Da Nang who want to enjoy authentic Central Vietnamese cuisine " +
                   "in a beach-view setting. Couples will love the romantic outdoor tables overlooking My An Beach, " +
                   "especially in the evening with shimmering lights. The restaurant is also an ideal venue for birthday parties, " +
                   "anniversaries, or colleague gatherings with private spaces on the second floor. " +
                   "Corporate groups and business partners will be well-received in private rooms " +
                   "with professional service and a diverse menu to meet all requirements.")),
            heading(ContentSection.STORY, of("Dịch Vụ & Tiện Ích Nổi Bật", "Services & Highlights")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Đà cung cấp đầy đủ dịch vụ hiện đại: WiFi miễn phí tốc độ cao, điều hòa không khí toàn bộ " +
                   "khu vực trong nhà, hệ thống âm thanh ánh sáng phục vụ sự kiện. " +
                   "Nhà hàng có ghế trẻ em phù hợp gia đình đi cùng em bé, trang trí bàn theo yêu cầu cho tiệc sinh nhật " +
                   "và kỷ niệm. Chấp nhận thanh toán thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu. " +
                   "Đội ngũ nhân viên được đào tạo chuyên nghiệp, phục vụ tận tình và am hiểu thực đơn miền Trung " +
                   "để tư vấn món phù hợp cho từng khẩu vị và dịp ăn uống.",
                   "Ngon Pho Da provides full modern services: free high-speed WiFi, air conditioning throughout the indoor area, " +
                   "sound and lighting systems for events. " +
                   "The restaurant has high chairs for families with young children, table decorations on request " +
                   "for birthday and anniversary parties. Visa/Master card payments accepted and VAT invoices available on request. " +
                   "The professional staff is well-trained, attentive, and knowledgeable about Central Vietnamese cuisine " +
                   "to advise suitable dishes for every taste and dining occasion.")),
            heading(ContentSection.STORY, of("Vị Trí & Di Chuyển", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Đà tọa lạc tại số 300 đường Võ Nguyên Giáp — tuyến đường ven biển nổi tiếng của Đà Nẵng, " +
                   "ngay trước biển Mỹ An thuộc Phường Ngũ Hành Sơn. " +
                   "Cách Cầu Rồng khoảng 8km về phía Nam, cách sân bay Đà Nẵng 7km và cách Phố cổ Hội An 15km. " +
                   "Vị trí thuận lợi trên tuyến đường ven biển giúp du khách dễ dàng di chuyển bằng taxi, " +
                   "xe máy hoặc ứng dụng đặt xe (Grab, Be). Bãi đỗ xe rộng rãi miễn phí ngay tại nhà hàng, " +
                   "không lo vấn đề gửi xe khi đến ăn tối hay tổ chức sự kiện.",
                   "Ngon Pho Da is located at 300 Vo Nguyen Giap Street — Da Nang's famous coastal road, " +
                   "right in front of My An Beach in Ngu Hanh Son Ward. " +
                   "Approximately 8km south of Dragon Bridge, 7km from Da Nang Airport, and 15km from Hoi An Ancient Town. " +
                   "The convenient location on the coastal road allows tourists to easily travel by taxi, " +
                   "motorbike, or ride-hailing apps (Grab, Be). Spacious free parking is available right at the restaurant, " +
                   "no worries about parking when dining or hosting events.")),
            heading(ContentSection.STORY, of("Chính Sách Đặt Bàn & Đặt Cọc", "Booking & Deposit Policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước tối thiểu 30 phút. Đối với đoàn khách đông (từ 10 người) " +
                   "có đặt món trước, yêu cầu đặt cọc 30–50% tùy trường hợp cụ thể. " +
                   "Nhà hàng giữ chỗ tối đa 15 phút sau giờ đặt; sau đó chỗ có thể nhường cho khách khác. " +
                   "Ưu đãi không áp dụng vào các ngày lễ, Tết Âm lịch và dịp đặc biệt trong năm. " +
                   "Hóa đơn VAT được xuất theo yêu cầu. Để đặt bàn VIP hoặc phòng riêng, " +
                   "vui lòng liên hệ trước ít nhất 24 giờ để nhà hàng chuẩn bị chu đáo nhất.",
                   "The restaurant accepts reservations at least 30 minutes in advance. For large groups (10 or more guests) " +
                   "with pre-ordered dishes, a deposit of 30–50% is required depending on the specific case. " +
                   "Tables are held for a maximum of 15 minutes after the reserved time; after that, the table may be given to walk-in guests. " +
                   "Promotions do not apply on public holidays, Lunar New Year, or special occasions. " +
                   "VAT invoices are issued on request. For VIP table or private room bookings, " +
                   "please contact at least 24 hours in advance for the best preparation.")),
            mapBlock(ContentSection.STORY, new double[]{108.248569, 16.049430})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1 Tết Dương lịch", "No promotions on January 1 (New Year)")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày Valentine", "No promotions on Valentine's Day")),
            blackoutGregorian(3,  8,  of("Không áp dụng ưu đãi ngày 8/3", "No promotions on International Women's Day")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "No promotions on April 30")),
            blackoutGregorian(5,  1,  of("Không áp dụng ưu đãi ngày Quốc tế Lao động 1/5", "No promotions on International Labor Day")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày Quốc khánh 2/9", "No promotions on National Day")),
            blackoutGregorian(11, 20, of("Không áp dụng ưu đãi ngày 20/11", "No promotions on Vietnamese Teachers' Day")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh 24/12", "No promotions on Christmas Eve")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa 31/12", "No promotions on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho đoàn khách từ 10 người, đặt cọc 30–50% tuỳ trường hợp.",
                             "Applies to groups of 10+, deposit 30–50% depending on case."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt bàn trực tuyến qua hệ thống, không áp dụng ngày lễ.",
                                 "Discount for online table reservations; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi có thể thay đổi theo thời điểm; liên hệ nhà hàng để biết thêm chi tiết.",
                              "Promotions may vary by period; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho đoàn tiệc và sự kiện.", "5% service charge for banquet and event groups."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không cho phép mang thức ăn từ ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không cho phép mang đồ uống từ ngoài vào.", "Outside drinks are not permitted."))
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
                .id("tb-4-standard")
                .name(of("Bàn thường 4 khách", "Standard table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường khu trong nhà hoặc ngoài trời, phù hợp gia đình nhỏ hoặc nhóm bạn.", "Standard indoor or outdoor table, suitable for small families or friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-family")
                .name(of("Bàn gia đình 8 khách", "Family table for 8"))
                .seats(8)
                .minPeople(4)
                .maxPeople(8)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn phù hợp tiệc gia đình, nhóm bạn bè tụ họp.", "Large table for family parties and friend gatherings."))
                .build(),
            TableType.builder()
                .id("tb-private-room")
                .name(of("Phòng riêng VIP tầng 2", "VIP private room on 2nd floor"))
                .seats(20)
                .minPeople(10)
                .maxPeople(60)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng tầng 2 view biển, phù hợp tiệc công ty, sinh nhật, kỷ niệm và họp doanh nghiệp. Sức chứa linh hoạt 10–60 khách.", "Second-floor private room with beach view, suitable for corporate parties, birthdays, anniversaries and business meetings. Flexible capacity 10–60 guests."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(30)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(60)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Ngũ Hành Sơn", "Ngu Hanh Son District"))
            .wardName(of("P. Ngũ Hành Sơn", "Ngu Hanh Son Ward"))
            .addressLine(of("300 Đường Võ Nguyên Giáp, Phường Ngũ Hành Sơn, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                            "300 Vo Nguyen Giap Street, Ngu Hanh Son Ward, Ngu Hanh Son District, Da Nang"))
            .location(new double[]{108.248569, 16.049430})
            .name(of("Ngon Phố Đà - Homey Authentic Vietnamese Cuisine", "Ngon Pho Da - Homey Authentic Vietnamese Cuisine"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nha hang am thuc Viet mien Trung 2 tang view bien My An, suc chua 260 khach, dac san goi cuon tom tuoi va ca thu nuong.",
                                 "Two-floor Central Vietnamese restaurant with My An beach view, 260 capacity, famous for fresh shrimp spring rolls and grilled mackerel."))
            .description(of("Ngon Pho Da - Homey Authentic Vietnamese Cuisine tai 300 Vo Nguyen Giap, Phuong Ngu Hanh Son, Da Nang. " +
                            "Nha hang 2 tang suc chua 260 khach, view bien My An truc tiep, chuyen am thuc mien Trung dac sac: " +
                            "goi cuon tom tuoi, nem cua be, mi Quang, ca thu nuong muoi ot. Gia 100.000-200.000d/nguoi. Bai do xe mien phi.",
                            "Ngon Pho Da - Homey Authentic Vietnamese Cuisine at 300 Vo Nguyen Giap, Ngu Hanh Son Ward, Da Nang. " +
                            "Two-floor restaurant with 260 capacity, direct My An beach view, specializing in Central Vietnamese cuisine: " +
                            "fresh shrimp spring rolls, sea crab rolls, Quang noodles, chili-salt grilled mackerel. Price 100,000-200,000 VND/person. Free parking."))
            .phone("0236382300")
            .website("https://pasgo.vn/nha-hang/ngon-pho-da-homey-authentic-vietnamese-cuisine-vo-nguyen-giap-5821")
            .bookingHotline("0236382300")
            .minPricePerPerson(new BigDecimal("100000"))
            .maxPricePerPerson(new BigDecimal("200000"))
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
