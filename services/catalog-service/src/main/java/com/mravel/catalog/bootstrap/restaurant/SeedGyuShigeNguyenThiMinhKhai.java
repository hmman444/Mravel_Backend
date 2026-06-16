// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedGyuShigeNguyenThiMinhKhai.java
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
public class SeedGyuShigeNguyenThiMinhKhai {

    @Bean
    CommandLineRunner seedGyuShigeNguyenThiMinhKhaiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGyuShigeNguyenThiMinhKhai] start");

            RestaurantDoc gyu = restaurantGyuShigeNguyenThiMinhKhai();
            repo.findBySlugAndActiveTrue(gyu.getSlug())
                .ifPresent(existing -> gyu.setId(existing.getId()));
            repo.save(gyu);
            log.info(">>> upsert: {}", gyu.getSlug());

            log.info(">>> [SeedGyuShigeNguyenThiMinhKhai] done");
        };
    }

    // =====================================================================
    //        GYU SHIGE NGƯU PHỒN - NGUYỄN THỊ MINH KHAI (Q.1, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantGyuShigeNguyenThiMinhKhai() {
        String slug = "gyu-shige-nguyen-thi-minh-khai";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-1-normal-472384629465.webp",
                of("Không gian Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai", "Gyu Shige Nguu Phon - Nguyen Thi Minh Khai space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-2-normal-472385129466.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-3-normal-472385629467.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-4-normal-472386329468.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-5-normal-472388229469.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-6-normal-472388529470.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-7-normal-472389229471.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-8-normal-472390329472.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-9-normal-472391429473.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-10-normal-474115729474.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-11-normal-474116029475.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gyu-shige-nguu-phon-nguyen-thi-minh-khai-13-normal-474116229476.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/so-183a-nguyen-thi-minh-khai--p--pham-ngu-lao--q--1-10770053106686339.webp",
                of("", ""), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("YAKINIKU")
                .name(of("Nướng Yakiniku", "Yakiniku BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BBQ")
                .name(of("Nướng", "Grill / BBQ"))
                .region("ASIA")
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
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("MODERN",   of("Hiện đại", "Modern")),
            ambience("VIBRANT",  of("Sôi động", "Vibrant")),
            ambience("ELEGANT",  of("Tinh tế kiểu Nhật", "Japanese-style refined"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò Ngưu Phồn nướng đặc biệt", "Signature Gyu beef yakiniku")),
            signatureDishL(of("Lưỡi bò nướng (Gyu Tan)", "Grilled beef tongue (Gyu Tan)")),
            signatureDishL(of("Ba chỉ bò nướng kiểu Nhật", "Japanese-style grilled beef brisket")),
            signatureDishL(of("Bò Wagyu thượng hạng", "Premium Wagyu beef slices")),
            signatureDishL(of("Sườn bò nướng sốt đặc trưng", "Grilled beef short rib in signature sauce")),
            signatureDishL(of("Set nướng bò tiêu chuẩn", "Standard beef BBQ set")),
            signatureDishL(of("Nội tạng bò nướng tổng hợp", "Assorted grilled beef offal")),
            signatureDishL(of("Salad rau củ kiểu Nhật", "Japanese-style fresh salad")),
            signatureDishL(of("Canh miso / canh rong biển", "Miso / seaweed soup")),
            signatureDishL(of("Cơm trắng & kim chi ăn kèm", "Steamed rice & kimchi sides"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoigyushiget4/gyu-shige-18.jpg",
                of("", ""), false, 17)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BEEF_BBQ")
                .name(of("Bò nướng Yakiniku", "Beef yakiniku"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set nướng bò tiêu chuẩn", "Standard beef BBQ set"))
                        .description(of("Set nướng gồm các phần bò chọn lọc: ba chỉ, sườn, lưỡi bò, ăn kèm rau và sốt đặc trưng.", "BBQ set of selected beef cuts: brisket, short rib, tongue, served with vegetables and signature sauce."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("259000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("bbq", "beef", "set"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bò Wagyu thượng hạng", "Premium Wagyu slices"))
                        .description(of("Lát bò Wagyu vân mỡ đẹp, nướng nhanh trên bếp, mềm và ngọt vị.", "Beautifully marbled Wagyu slices, quick-grilled, tender and sweet."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("phần", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "wagyu"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô dọc tuyến đường và bãi lân cận", "Car parking along the street and nearby lots"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy gần nhà hàng", "Motorbike parking near the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hướng dẫn đỗ xe máy miễn phí; ô tô gửi bãi lân cận.", "Staff assist with free motorbike parking; cars use nearby lots."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE",
            "ENGLISH_MENU",
            "SMOKELESS_GRILL"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai", "About Gyu Shige Nguu Phon - Nguyen Thi Minh Khai")),
            paragraph(ContentSection.OVERVIEW,
                of("Gyu Shige Ngưu Phồn là thương hiệu nướng bò Yakiniku chuẩn Nhật Bản, và chi nhánh tại 183A Nguyễn Thị Minh Khai, Q.1, TP. Hồ Chí Minh " +
                "mang đến cho thực khách trải nghiệm thịt bò nướng tươi ngon trên bếp riêng từng bàn. Mỗi phần thịt được tuyển chọn kỹ lưỡng, " +
                "thái lát đẹp mắt và phục vụ cùng các loại sốt đặc trưng của Nhật.",
                "Gyu Shige Nguu Phon is a Japanese-style Yakiniku beef BBQ brand, and the branch at 183A Nguyen Thi Minh Khai, District 1, Ho Chi Minh City " +
                "brings diners a fresh grilled-beef experience on a tabletop grill. Each cut is carefully selected, " +
                "beautifully sliced and served with signature Japanese sauces.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng được thiết kế hiện đại pha nét Nhật ấm cúng, bàn nướng bố trí thông thoáng với bếp khử khói, sức chứa khoảng 120 khách. " +
                "Có thêm phòng riêng phù hợp cho nhóm bạn, gia đình hay tiệc công ty cần không gian kín đáo.",
                "The restaurant features a modern design with cozy Japanese touches, well-ventilated grill tables with smokeless burners, and a capacity of around 120 guests. " +
                "Private rooms are also available for groups of friends, families or company parties needing a more secluded space.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Gyu Shige là các phần bò nướng cao cấp: lưỡi bò Gyu Tan, ba chỉ bò, sườn bò và những lát Wagyu vân mỡ đẹp. " +
                "Thực khách có thể chọn set nướng tiêu chuẩn hoặc gọi món theo sở thích, ăn kèm salad, canh miso, kim chi và cơm trắng.",
                "Gyu Shige's highlights are premium beef cuts: Gyu Tan beef tongue, brisket, short rib and beautifully marbled Wagyu slices. " +
                "Diners can choose a standard BBQ set or order a la carte, paired with salad, miso soup, kimchi and steamed rice.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, phục vụ chu đáo và vị trí trung tâm Q.1, Gyu Shige phù hợp cho bữa ăn gia đình, hẹn hò, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay những buổi tiếp khách thân mật.",
                "With a cozy atmosphere, attentive service and a central District 1 location, Gyu Shige suits family meals, dates, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, bếp nướng khử khói, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có menu tiếng Anh, nhận thanh toán thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, smokeless grills, sound & light systems and " +
                "decoration / MC support for events. English menu, Visa/Master card payment and VAT invoices on request are available.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Số 183A Nguyễn Thị Minh Khai, P. Phạm Ngũ Lão, Q.1, ngay khu trung tâm, gần công viên 23/9 và phố Tây Bùi Viện, " +
                "thuận tiện di chuyển từ mọi quận của TP. Hồ Chí Minh.",
                "The restaurant is located at No. 183A Nguyen Thi Minh Khai, Pham Ngu Lao Ward, District 1, in the city center, near September 23 Park and Bui Vien walking street, " +
                "convenient to reach from any district of Ho Chi Minh City.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.686339, 10.770053})
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
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 6 or more guests or private room reservations."))
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
            .serviceChargeNotes(of("Có thể áp dụng phí phục vụ 5% với nhóm đông hoặc phòng riêng.", "A 5% service charge may apply for large groups or private rooms."))
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
                .id("tb-2")
                .name(of("Bàn 2", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(8)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp cặp đôi, nhóm nhỏ.", "Suitable for couples and small groups."))
                .build(),

            TableType.builder()
                .id("tb-4")
                .name(of("Bàn 4", "Table for 4"))
                .seats(4)
                .minPeople(3)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-25")
                .name(of("Phòng riêng 25 khách", "Private room for 25 guests"))
                .seats(25)
                .minPeople(12)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông/liên hoan công ty.", "Private room, suitable for large groups and company parties."))
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
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ho-chi-minh-city")
            .cityName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
            .districtName(of("Quận 1", "District 1"))
            .wardName(of("P. Phạm Ngũ Lão", "Pham Ngu Lao Ward"))
            .addressLine(of("Số 183A Nguyễn Thị Minh Khai, P. Phạm Ngũ Lão, Q. 1, TP. Hồ Chí Minh", "No. 183A Nguyen Thi Minh Khai, Pham Ngu Lao Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.686339, 10.770053})
            .name(of("Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai", "Gyu Shige Nguu Phon - Nguyen Thi Minh Khai"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nướng bò Yakiniku chuẩn Nhật tại trung tâm Q.1, TP. Hồ Chí Minh.", "Japanese-style Yakiniku beef BBQ in central District 1, Ho Chi Minh City."))
            .description(of("Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai là nhà hàng nướng bò Yakiniku chuẩn Nhật tại Số 183A Nguyễn Thị Minh Khai, Q.1, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ các phần bò tuyển chọn nướng trên bếp riêng từng bàn cùng sốt đặc trưng, không gian hiện đại ấm cúng, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, hẹn hò đến liên hoan công ty.",
                "Gyu Shige Nguu Phon - Nguyen Thi Minh Khai is a Japanese-style Yakiniku beef BBQ restaurant at No. 183A Nguyen Thi Minh Khai, District 1, Ho Chi Minh City. " +
                "It serves selected beef cuts grilled on a tabletop grill with signature sauces, in a modern and cozy space, " +
                "suitable for family meals, dates and company parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@gyushige.vn")
            .website("https://pasgo.vn/nha-hang/gyu-shige-nguu-phon-nguyen-thi-minh-khai")
            .facebookPage("https://facebook.com/gyushige")
            .bookingHotline("(+84) 0931 006 005")
            .minPricePerPerson(new BigDecimal("200000"))
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
