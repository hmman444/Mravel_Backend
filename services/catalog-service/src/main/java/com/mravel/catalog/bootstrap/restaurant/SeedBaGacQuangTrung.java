// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedBaGacQuangTrung.java
package com.mravel.catalog.bootstrap.restaurant;

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
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.vi;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedBaGacQuangTrung {

    @Bean
    CommandLineRunner seedBaGacQuangTrungRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBaGacQuangTrung] start");

            RestaurantDoc baGac = restaurantBaGacQuangTrung();
            repo.findBySlugAndActiveTrue(baGac.getSlug())
                .ifPresent(existing -> baGac.setId(existing.getId()));
            repo.save(baGac);
            log.info(">>> upsert: {}", baGac.getSlug());

            log.info(">>> [SeedBaGacQuangTrung] done");
        };
    }

    // =====================================================================
    //          BA GÁC - VIETNAMESE GRILL & BEER - QUANG TRUNG (GÒ VẤP, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantBaGacQuangTrung() {
        String slug = "ba-gac-quang-trung";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-1-normal-2272100750530.webp",
                of("Không gian Ba Gác - Quang Trung", "Ba Gac - Quang Trung space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-2-normal-2336010850531.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-3-normal-2272100950532.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-4-normal-2336010750533.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-5-normal-2272101750534.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-6-normal-2272103850535.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-7-normal-2272103950536.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-8-normal-2272104650537.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-9-normal-2272103050538.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-10-normal-2336006650539.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-11-normal-2272104850540.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-ba-gac-quang-trung-12-normal-2272140159714.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/so-1-quang-trung--p--10--q--go-vap-108267143931765106678712650499.webp",
                of("", ""), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("GRILL")
                .name(of("Đồ nướng", "Grilled"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt", "Vietnamese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BEER")
                .name(of("Bia & đồ uống", "Beer & drinks"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (16:00 - 23:59, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    16, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   16, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 16, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  16, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    16, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  16, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    16, 0, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("AFTER_WORK",    of("Lai rai sau giờ làm", "After-work drinks"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động", "Vibrant")),
            ambience("RUSTIC",   of("Mộc mạc", "Rustic")),
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("MODERN",   of("Hiện đại", "Modern"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò nướng tảng Ba Gác", "Ba Gac grilled beef slab")),
            signatureDishL(of("Sườn bò nướng tiêu đen", "Grilled beef ribs with black pepper")),
            signatureDishL(of("Gà nướng muối ớt", "Grilled chicken with chili salt")),
            signatureDishL(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Tôm nướng muối ớt xanh", "Grilled prawns with green chili salt")),
            signatureDishL(of("Lòng nướng thập cẩm", "Assorted grilled offal")),
            signatureDishL(of("Cá lóc nướng trui", "Whole grilled snakehead fish")),
            signatureDishL(of("Nầm bò nướng", "Grilled beef brisket")),
            signatureDishL(of("Khoai môn lệ phố chiên", "Crispy fried taro fries"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menubagacquangtrung/1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menubagacquangtrung/2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menubagacquangtrung/3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/1.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/2.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/3.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/5.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/6.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/7.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/8.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/9.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/10.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/11.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/12.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/13.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/14.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/16.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/17.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/18.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/menuchuoibagac/19.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/monmoibagac/1.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/monmoibagac/2.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/monmoibagac/3.jpg",
                of("", ""), false, 22)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILL")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò nướng tảng Ba Gác", "Ba Gac grilled beef slab"))
                        .description(of("Tảng bò mềm ướp đậm đà nướng trên than hồng, ăn kèm muối tiêu chanh và rau sống.", "Tender beef slab marinated and char-grilled, served with lime-pepper salt and fresh herbs."))
                        .priceFrom(new BigDecimal("189000"))
                        .priceTo(new BigDecimal("189000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "beef", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil"))
                        .description(of("Hàu tươi nướng mỡ hành phủ đậu phộng rang, béo ngậy thơm lừng.", "Fresh oysters grilled with scallion oil and roasted peanuts, rich and fragrant."))
                        .priceFrom(new BigDecimal("99000"))
                        .priceTo(new BigDecimal("99000"))
                        .unit(of("phần (6 con)", "portion (6 pcs)"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grill", "seafood"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BEER")
                .name(of("Bia & đồ uống", "Beer & drinks"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tháp bia tươi 3 lít", "Fresh beer tower 3L"))
                        .description(of("Tháp bia tươi mát lạnh, lý tưởng cho nhóm bạn lai rai cùng đồ nướng.", "Chilled fresh beer tower, ideal for groups enjoying grilled food."))
                        .priceFrom(new BigDecimal("260000"))
                        .priceTo(new BigDecimal("260000"))
                        .unit(of("tháp 3 lít", "3L tower"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beer", "drink"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc đường Quang Trung và bãi gần nhà hàng", "Car parking along Quang Trung street and nearby lots"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên trông xe, miễn phí cho khách.", "Parking attendant on site, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "PROJECTOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "KIDS_CHAIR",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Ba Gác - Quang Trung", "About Ba Gac - Quang Trung")),
            paragraph(ContentSection.OVERVIEW,
                of("Ba Gác - Vietnamese Grill & Beer là chuỗi nhà hàng nướng và bia đậm chất bụi đời, mồi ngon bia chất. " +
                "Chi nhánh Quang Trung tọa lạc tại Số 1 Quang Trung, P. 10, Q. Gò Vấp, TP. Hồ Chí Minh, là điểm hẹn quen thuộc " +
                "của giới trẻ và dân văn phòng mỗi khi muốn lai rai cùng bạn bè sau giờ làm.",
                "Ba Gac - Vietnamese Grill & Beer is a rustic, street-style grill and beer chain with great food and great beer. " +
                "The Quang Trung branch is located at No. 1 Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City, a favorite spot " +
                "for young people and office workers looking to unwind with friends after work.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng được thiết kế mộc mạc, sôi động pha chút phá cách, kết hợp khu vực trong nhà có điều hòa và khu ngoài trời thoáng đãng, " +
                "tổng sức chứa lên tới khoảng 200 khách. Ba Gác có thêm phòng riêng và khu vực bàn lớn phù hợp cho các nhóm đông và tiệc liên hoan.",
                "The restaurant is designed in a rustic, vibrant and slightly edgy style, combining air-conditioned indoor areas with an airy outdoor space, " +
                "with a total capacity of around 200 guests. Ba Gac also offers private rooms and large-table areas suitable for big groups and parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn Ba Gác tập trung vào các món nướng than hoa trứ danh: bò nướng tảng, sườn bò tiêu đen, gà nướng muối ớt, " +
                "hàu nướng mỡ hành, bạch tuộc nướng sa tế cùng nhiều loại hải sản tươi. Tất cả đều hợp vị bên những tháp bia tươi mát lạnh.",
                "Ba Gac's menu centers on its famous charcoal-grilled dishes: grilled beef slab, black-pepper beef ribs, chili-salt grilled chicken, " +
                "oysters with scallion oil, satay octopus and a variety of fresh seafood - all best paired with chilled fresh beer towers.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí náo nhiệt và phong cách phục vụ nhiệt tình, Ba Gác - Quang Trung là lựa chọn lý tưởng cho buổi tụ tập bạn bè, " +
                "liên hoan văn phòng, sinh nhật, tiệc công ty hay đơn giản là lai rai vài chai bia sau ngày dài.",
                "With a lively atmosphere and enthusiastic service, Ba Gac - Quang Trung is ideal for friends gatherings, " +
                "office parties, birthdays, company events, or simply unwinding over a few beers after a long day.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, khu bàn ngoài trời, phòng riêng, hệ thống âm thanh ánh sáng cùng dịch vụ trang trí và MC cho tiệc. " +
                "Có chỗ đỗ ô tô, xe máy miễn phí và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, outdoor seating, private rooms, sound & light systems and " +
                "decoration / MC services for parties. Free car and motorbike parking is available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm ngay mặt đường Quang Trung, Phường 10, Quận Gò Vấp - một trong những trục đường sầm uất bậc nhất khu vực, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Phú Nhuận, Bình Thạnh, Tân Bình.",
                "The restaurant sits right on Quang Trung street, Ward 10, Go Vap District - one of the busiest avenues in the area, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts such as Phu Nhuan, Binh Thanh and Tan Binh.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào buổi tối cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekend evenings and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.678712650499, 10.8267143931765})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("150000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 150.000đ/chai.", "Wine corkage fee: 150,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 8 or more guests or private room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt chỗ qua hệ thống, không áp dụng vào các ngày lễ, tết và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays, Tet and special occasions."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho nhóm đông và phòng riêng.", "A 5% service charge applies to large groups and private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí theo loại đồ uống.", "Outside drinks are allowed, with a corkage fee depending on the type of drink."))
            .outsideDrinkFees(outsideDrinkFees)
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

        // ---------- Table types (bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp nhóm bạn nhỏ, cặp đôi.", "Suitable for small groups and couples."))
                .build(),

            TableType.builder()
                .id("tb-8")
                .name(of("Bàn 8", "Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp nhóm bạn đông, liên hoan văn phòng.", "Suitable for larger groups and office parties."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(2)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông và liên hoan công ty.", "Private room, suitable for large groups and company parties."))
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
            .destinationSlug("ho-chi-minh-city")
            .cityName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
            .districtName(of("Q. Gò Vấp", "Go Vap District"))
            .wardName(of("Phường 10", "Ward 10"))
            .addressLine(of("Số 1 Quang Trung, Phường 10, Q. Gò Vấp, TP. Hồ Chí Minh", "No. 1 Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City"))
            .location(new double[]{106.678712650499, 10.8267143931765})
            .name(of("Ba Gác - Vietnamese Grill & Beer - Quang Trung", "Ba Gac - Vietnamese Grill & Beer - Quang Trung"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng & bia đậm chất bụi đời, mồi ngon bia chất tại Gò Vấp, TP. Hồ Chí Minh.", "Rustic grill & beer restaurant with great food and great beer in Go Vap, Ho Chi Minh City."))
            .description(of("Ba Gác - Vietnamese Grill & Beer - Quang Trung là nhà hàng nướng và bia tọa lạc tại Số 1 Quang Trung, P. 10, Q. Gò Vấp, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi tiếng với các món nướng than hoa đậm đà cùng tháp bia tươi mát lạnh, không gian sôi động mộc mạc, " +
                "phù hợp cho tụ tập bạn bè, liên hoan văn phòng và những buổi lai rai sau giờ làm.",
                "Ba Gac - Vietnamese Grill & Beer - Quang Trung is a grill and beer restaurant located at No. 1 Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City. " +
                "It is famous for its flavorful charcoal-grilled dishes and chilled fresh beer towers in a vibrant, rustic space, " +
                "perfect for friends gatherings, office parties and after-work drinks."))
            .phone("0826714393")
            .email("reservation@bagac.vn")
            .website("https://pasgo.vn/nha-hang/ba-gac-vietnamese-grill-and-beer-quang-trung")
            .facebookPage("https://facebook.com/bagacgrillbeer")
            .bookingHotline("0826714393")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("300000"))
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
