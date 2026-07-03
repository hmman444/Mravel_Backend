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
public class SeedButcherGrilledHoangKeViem {

    @Bean
    CommandLineRunner seedButcherGrilledHoangKeViemRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedButcherGrilledHoangKeViem] start");
            RestaurantDoc doc = restaurantButcherGrilledHoangKeViem();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedButcherGrilledHoangKeViem] done");
        };
    }

    // =====================================================================
    //   BUTCHER GRILLED - HOÀNG KẾ VIÊM (121 Hoàng Kế Viêm, Bắc Mỹ Phú, Ngũ Hành Sơn)
    // =====================================================================

    private static RestaurantDoc restaurantButcherGrilledHoangKeViem() {
        String slug = "butcher-grilled-hoang-ke-viem-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-1-normal-2711082170339.webp",
                of("Không gian nướng than hoa Butcher Grilled Hoàng Kế Viêm", "Charcoal grill space at Butcher Grilled Hoang Ke Viem"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-2-normal-2711082270340.webp",
                of("Quầy thịt tươi chất lượng tại Butcher Grilled", "Fresh quality meat counter at Butcher Grilled"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-3-normal-2711082370341.webp",
                of("Bàn nướng than hoa đặc trưng Butcher Grilled", "Signature charcoal grill table at Butcher Grilled"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-4-normal-2711082470342.webp",
                of("Không gian ăn uống hiện đại Butcher Grilled", "Modern dining atmosphere at Butcher Grilled"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-5-normal-2711082570343.webp",
                of("Thịt bò Mỹ và hải sản tươi ngon tại Butcher Grilled", "US beef and fresh seafood at Butcher Grilled"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-6-normal-2711082670344.webp",
                of("Khu vực ngoài trời thoáng mát Butcher Grilled", "Breezy outdoor area at Butcher Grilled"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-7-normal-2711082770345.webp",
                of("Không gian nhà hàng Butcher Grilled buổi tối", "Butcher Grilled restaurant at night"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-8-normal-2711082870346.webp",
                of("Món nướng đặc sắc tại Butcher Grilled", "Signature grilled dishes at Butcher Grilled"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-9-normal-2711082970347.webp",
                of("Thực đơn phong phú tại Butcher Grilled Hoàng Kế Viêm", "Diverse menu at Butcher Grilled Hoang Ke Viem"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-10-normal-2711083070348.webp",
                of("Khu vực bàn đôi lãng mạn tại Butcher Grilled", "Romantic couple seating at Butcher Grilled"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-11-normal-2711083170349.webp",
                of("Không gian trong nhà Butcher Grilled Ngũ Hành Sơn", "Indoor space at Butcher Grilled Ngu Hanh Son"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-12-normal-2711083270350.webp",
                of("Toàn cảnh nhà hàng Butcher Grilled Hoàng Kế Viêm", "Overview of Butcher Grilled Hoang Ke Viem"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-grilled-13-normal-2711083370351.webp",
                of("Khu vực tiếp đón và bar tại Butcher Grilled", "Reception and bar area at Butcher Grilled"), false, 12)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%201.jpg",
                of("Menu thực đơn Butcher Grilled trang 1", "Butcher Grilled menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%202.jpg",
                of("Menu thực đơn Butcher Grilled trang 2", "Butcher Grilled menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%203.jpg",
                of("Menu thực đơn Butcher Grilled trang 3", "Butcher Grilled menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%204.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%205.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%206.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%207.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%208.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%209.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2010.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2011.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2012.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2013.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2014.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2015.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2016.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%2017.jpg",
                of("", ""), false, 16)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("AMERICAN")
                .name(of("Ẩm thực Mỹ", "American cuisine"))
                .region("NORTH_AMERICA")
                .build(),
            CuisineTag.builder()
                .code("STEAKHOUSE")
                .name(of("Steakhouse - Bò nướng than hoa", "Steakhouse - Charcoal grilled beef"))
                .region("GLOBAL")
                .build(),
            CuisineTag.builder()
                .code("GRILLED_MEAT")
                .name(of("Thịt nướng kiểu Tây", "Western-style grilled meat"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("COUPLE",         of("Hẹn hò thưởng thức bò nướng", "Romantic steak date")),
            suitable("FRIENDS",        of("Tụ tập bạn bè nướng BBQ", "BBQ grill with friends")),
            suitable("FAMILY",         of("Bữa ăn gia đình cuối tuần", "Family weekend dinner")),
            suitable("BIRTHDAY",       of("Tiệc sinh nhật nhỏ và lớn", "Birthday celebration small or large")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách kinh doanh thân thiện", "Casual business dining")),
            suitable("COMPANY_PARTY",  of("Liên hoan tổ/nhóm nhỏ", "Small team or department party")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary dinner")),
            suitable("GROUP_MEETING",  of("Họp nhóm và ăn trưa làm việc", "Work group meeting and lunch")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng", "Office lunch")),
            suitable("TEAM_BUILDING",  of("Team building ăn nướng", "BBQ team building"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",   of("Mộc mạc phong cách nhà hàng thịt Mỹ", "Rustic American steakhouse style")),
            ambience("VIBRANT",  of("Sôi động, náo nhiệt", "Vibrant and lively")),
            ambience("MODERN",   of("Hiện đại, thoáng sáng", "Modern and bright")),
            ambience("COZY",     of("Ấm cúng, gần gũi", "Cozy and inviting"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò bít tết Mỹ nướng than hoa 300g", "300g US beef charcoal grilled steak")),
            signatureDishL(of("Sườn bò nướng sốt BBQ đặc biệt", "BBQ sauce grilled beef ribs")),
            signatureDishL(of("Ức gà nướng thảo mộc Địa Trung Hải", "Mediterranean herb grilled chicken breast")),
            signatureDishL(of("Đùi cừu nướng sốt bạc hà", "Mint-sauced grilled lamb leg")),
            signatureDishL(of("Mực nướng bơ tỏi", "Garlic butter grilled squid")),
            signatureDishL(of("Tôm sú nướng muối ớt", "Salt-chili grilled tiger prawns")),
            signatureDishL(of("Sườn heo nướng sốt Mật ong", "Honey-glazed grilled pork ribs")),
            signatureDishL(of("Xúc xích bò Đức nướng than", "Charcoal-grilled German beef sausage")),
            signatureDishL(of("Cánh gà nướng sốt Teriyaki", "Teriyaki-glazed grilled chicken wings")),
            signatureDishL(of("Combo Butcher 2 người — bò + hải sản + thức uống", "Butcher combo for 2 — beef + seafood + drinks"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("GRILLED_BEEF")
                .name(of("Bò nướng than hoa", "Charcoal grilled beef"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Bò bít tết Mỹ 300g nướng than hoa", "300g US charcoal grilled beef steak"))
                        .description(of("Bò Mỹ chọn lọc, nướng than hoa, ăn kèm khoai tây chiên và salad rau xanh.", "Select US beef, charcoal-grilled, served with fries and green salad."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("steak", "beef", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo Butcher Grilled 2 người", "Butcher Grilled combo for 2"))
                        .description(of("Bít tết bò, sườn heo, tôm nướng, khoai tây chiên, salad và thức uống.", "Beef steak, pork ribs, grilled prawns, fries, salad and drinks."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("550000"))
                        .unit(of("combo 2 người", "combo for 2"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "couple", "popular"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước nhà hàng trên đường Hoàng Kế Viêm", "Parking in front of the restaurant on Hoang Ke Viem Street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Khu vực để xe máy bên hông nhà hàng", "Motorbike parking on the side of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe cho khách đến ăn tại nhà hàng.", "Free parking for all dining guests."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "WIFI",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "PRIVATE_ROOM",
            "VISA_MASTER",
            "VAT_INVOICE",
            "CHARCOAL_GRILL_TABLE",
            "SMOKING_AREA",
            "LIVE_MUSIC"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Butcher Grilled Hoàng Kế Viêm", "About Butcher Grilled Hoang Ke Viem")),
            paragraph(ContentSection.STORY,
                of("Butcher Grilled Hoàng Kế Viêm là nhà hàng chuyên nướng than hoa phong cách Mỹ " +
                   "tọa lạc tại số 121 đường Hoàng Kế Viêm, phường Bắc Mỹ Phú, quận Ngũ Hành Sơn, Đà Nẵng. " +
                   "Thuộc thương hiệu Butcher — chuỗi nhà hàng bít tết & BBQ nổi tiếng tại Đà Nẵng — " +
                   "Butcher Grilled mang đến trải nghiệm nướng than hoa đích thực với nguyên liệu thịt tươi được chọn lọc kỹ càng. " +
                   "Không gian ấm cúng, phong cách mộc mạc của nhà hàng thịt Mỹ cùng phục vụ chuyên nghiệp " +
                   "là điểm nhấn khiến Butcher Grilled trở thành địa chỉ ưa thích của cả khách nội địa lẫn du khách nước ngoài.",
                   "Butcher Grilled Hoang Ke Viem is an American-style charcoal grill restaurant " +
                   "located at 121 Hoang Ke Viem Street, Bac My Phu Ward, Ngu Hanh Son District, Da Nang. " +
                   "Part of the Butcher brand — a well-known steak & BBQ chain in Da Nang — " +
                   "Butcher Grilled delivers an authentic charcoal grilling experience with carefully selected fresh cuts. " +
                   "The cozy, rustic American steakhouse ambiance paired with professional service " +
                   "makes Butcher Grilled a favorite for both local diners and international visitors.")),
            heading(ContentSection.STORY, of("Không gian & thiết kế nội thất", "Space & interior design")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Butcher Grilled được thiết kế theo phong cách nhà hàng thịt Mỹ hiện đại — " +
                   "kết hợp gỗ tự nhiên, ánh đèn vàng ấm và không gian mở thoáng. " +
                   "Sức chứa 150 khách gồm khu vực trong nhà điều hòa với các bàn than hoa cá nhân, " +
                   "khu vực ngoài trời thoáng mát và 2 phòng riêng sức chứa tới 30 khách. " +
                   "Mỗi bàn đều được trang bị bếp than hoa chuyên dụng, ống hút khói cá nhân — " +
                   "cho phép thực khách tự tay nướng trực tiếp tại bàn mà không lo khói bụi.",
                   "Butcher Grilled is designed in a modern American steakhouse style — " +
                   "combining natural wood, warm golden lighting, and an open-concept layout. " +
                   "With a capacity of 150 guests, it features an air-conditioned indoor area with individual charcoal grills, " +
                   "a breezy outdoor section, and 2 private rooms accommodating up to 30 guests each. " +
                   "Every table is equipped with a specialized charcoal grill and individual smoke extractor — " +
                   "allowing guests to grill directly at the table without worrying about smoke.")),
            heading(ContentSection.STORY, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Butcher Grilled tập trung vào thịt nướng than hoa chất lượng cao với nguồn gốc rõ ràng. " +
                   "Bò bít tết Mỹ là ngôi sao của menu — từ thăn nội (tenderloin), thăn ngoại (striploin) " +
                   "đến sườn T-bone và bò Wagyu nhập khẩu. Ngoài bò, thực đơn còn có đùi cừu Úc nướng thảo mộc, " +
                   "sườn heo sốt mật ong, gà ta nướng ngũ vị và các món hải sản tươi như tôm sú, mực, cá hồi. " +
                   "Combo 2 người và 4 người là lựa chọn tiết kiệm nhất, bao gồm thịt chọn, hải sản và thức uống.",
                   "Butcher Grilled's menu focuses on high-quality charcoal-grilled meats with traceable origins. " +
                   "US beef steak is the menu star — from tenderloin and striploin " +
                   "to T-bone and imported Wagyu beef. Beyond beef, the menu features herb-grilled Australian lamb leg, " +
                   "honey-glazed pork ribs, five-spice grilled free-range chicken, and fresh seafood like tiger prawns, squid, and salmon. " +
                   "Combo for 2 and 4 are the best value options, including selected meats, seafood, and drinks.")),
            heading(ContentSection.STORY, of("Trải nghiệm nướng than hoa tại bàn", "At-table charcoal grill experience")),
            paragraph(ContentSection.STORY,
                of("Điểm khác biệt lớn nhất của Butcher Grilled là trải nghiệm tự nướng trên bếp than hoa trực tiếp tại bàn. " +
                   "Thực phẩm được chuẩn bị sẵn với gia vị đặc biệt, thực khách tự tay điều chỉnh độ chín theo khẩu vị. " +
                   "Nhân viên hướng dẫn cách nướng đúng kỹ thuật nếu được yêu cầu — đặc biệt hữu ích cho khách lần đầu. " +
                   "Hệ thống hút khói riêng từng bàn đảm bảo không khí luôn trong lành, " +
                   "cho phép tận hưởng bữa ăn mà không cần lo lắng về quần áo bị ám khói.",
                   "The biggest differentiator of Butcher Grilled is the at-table self-grilling experience over live charcoal. " +
                   "Food is pre-seasoned with special spices; guests personally adjust doneness to their preference. " +
                   "Staff guide proper grilling technique upon request — especially helpful for first-timers. " +
                   "Individual smoke exhaust systems at each table keep the air fresh, " +
                   "letting guests enjoy their meal without worrying about smoky clothes.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Butcher Grilled Hoàng Kế Viêm phù hợp cho mọi dịp: hẹn hò cặp đôi muốn thưởng thức bít tết lãng mạn, " +
                   "nhóm bạn tụ tập ăn BBQ sôi động, gia đình ăn tối cuối tuần, " +
                   "hay tiệc sinh nhật và kỷ niệm nhỏ trong không gian phòng riêng ấm cúng. " +
                   "Đặc biệt thích hợp cho du khách nước ngoài muốn trải nghiệm phong cách nướng than hoa tại bàn " +
                   "— một nét ẩm thực độc đáo của Đông Nam Á kết hợp hương vị Tây.",
                   "Butcher Grilled Hoang Ke Viem is perfect for any occasion: couples seeking a romantic steak dinner, " +
                   "friend groups enjoying a lively BBQ session, family weekend dinners, " +
                   "or small birthday and anniversary celebrations in cozy private room settings. " +
                   "Particularly suitable for international tourists wanting to experience at-table charcoal grilling — " +
                   "a distinctive Southeast Asian dining style fused with Western flavors.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển tại Ngũ Hành Sơn", "Location & getting there in Ngu Hanh Son")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm tại 121 Hoàng Kế Viêm, quận Ngũ Hành Sơn — khu vực nổi tiếng với " +
                   "danh thắng Ngũ Hành Sơn (Marble Mountains), bãi biển Non Nước và làng đá mỹ nghệ. " +
                   "Cách trung tâm Đà Nẵng khoảng 8km về phía Nam, cách Hội An 20km về phía Bắc. " +
                   "Vị trí thuận tiện cho du khách đang tham quan Ngũ Hành Sơn dừng chân ăn tối " +
                   "hoặc cho cư dân khu vực dân cư Bắc Mỹ An – Bắc Mỹ Phú đông đúc.",
                   "The restaurant is at 121 Hoang Ke Viem, Ngu Hanh Son District — an area famous for " +
                   "the Marble Mountains, Non Nuoc Beach, and the stone carving village. " +
                   "About 8km south of Da Nang's city center and 20km north of Hoi An. " +
                   "Conveniently located for tourists visiting the Marble Mountains to stop for dinner, " +
                   "or for residents of the densely populated Bac My An - Bac My Phu areas.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Butcher Grilled cung cấp đầy đủ tiện ích: WiFi miễn phí, bãi đỗ xe miễn phí ô tô và xe máy, " +
                   "ghế trẻ em, khu hút thuốc riêng. Chấp nhận Visa/Master, xuất hóa đơn VAT. " +
                   "Dịch vụ trang trí bàn tiệc sinh nhật và kỷ niệm theo yêu cầu. " +
                   "Phòng riêng có hệ thống âm thanh và màn chiếu phục vụ sự kiện nhỏ và họp nhóm.",
                   "Butcher Grilled provides full amenities: free WiFi, free car and motorbike parking, " +
                   "children's chairs, and a separate smoking area. Visa/Master accepted, VAT invoices available. " +
                   "Birthday and anniversary table decoration services available on request. " +
                   "Private rooms feature sound systems and projection screens for small events and group meetings.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & lý do nên chọn Butcher Grilled", "Booking policy & why choose Butcher Grilled")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước ít nhất 2 tiếng, đặt cọc cho nhóm từ 10 khách trở lên. " +
                   "Phòng riêng cần đặt trước ít nhất 24 giờ và đặt cọc 300.000đ. " +
                   "Butcher Grilled nổi bật nhờ chất lượng thịt nhất quán, kỹ thuật nướng than hoa bài bản " +
                   "và không gian thoải mái phù hợp cả ăn nhẹ và tiệc lớn. " +
                   "Với giá tầm 200.000–500.000đ/người, đây là lựa chọn xứng đáng cho một bữa ăn đáng nhớ tại Đà Nẵng.",
                   "Book at least 2 hours in advance; deposits required for groups of 10 or more. " +
                   "Private rooms need 24-hour advance booking and a 300,000 VND deposit. " +
                   "Butcher Grilled stands out for consistent meat quality, skilled charcoal grilling, " +
                   "and a comfortable space suitable for both light meals and large celebrations. " +
                   "At 200,000–500,000 VND per person, it is a worthwhile choice for a memorable meal in Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.247003, 16.049875})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,   of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14,  of("Không áp dụng ưu đãi Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30,  of("Không áp dụng ưu đãi 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,   of("Không áp dụng ưu đãi Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho nhóm từ 10 khách, phòng riêng 300.000đ. Hoàn cọc hủy trước 24 giờ.", "Applies to groups of 10+, private room 300,000 VND. Refundable with 24-hour cancellation."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm 10% cho khách đặt bàn qua hệ thống, không áp dụng ngày lễ.", "10% off for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi có thể thay đổi, liên hệ nhà hàng để biết thêm chi tiết.", "Promotions may vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% cho tiệc sự kiện và đặt phòng riêng.", "5% service charge for event parties and private room bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang đồ ăn từ ngoài vào nhà hàng.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không nhận đồ uống mang ngoài.", "Outside drinks not accepted."))
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
                .id("tb-2-indoor")
                .name(of("Bàn đôi trong nhà có điều hòa", "Indoor table for 2 with A/C"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(20)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi khu trong nhà, phù hợp cặp đôi và ăn trưa văn phòng.", "Indoor table for 2, suitable for couples and office lunch."))
                .build(),
            TableType.builder()
                .id("tb-4-standard")
                .name(of("Bàn 4 người tiêu chuẩn", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn 4 người tiêu chuẩn, trong nhà hoặc ngoài trời.", "Standard table for 4, indoor or outdoor."))
                .build(),
            TableType.builder()
                .id("tb-private-room")
                .name(of("Phòng riêng VIP (tối đa 30 khách)", "VIP private room (up to 30 guests)"))
                .seats(30)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng VIP sức chứa tối đa 30 khách, có âm thanh và màn chiếu.", "VIP private room up to 30 guests with sound system and projector."))
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

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Ngũ Hành Sơn", "Ngu Hanh Son District"))
            .wardName(of("P. Bắc Mỹ Phú", "Bac My Phu Ward"))
            .addressLine(of("Số 121 Đường Hoàng Kế Viêm, P. Bắc Mỹ Phú, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                           "121 Hoang Ke Viem Street, Bac My Phu Ward, Ngu Hanh Son District, Da Nang"))
            .location(new double[]{108.247003, 16.049875})
            .name(of("Butcher Grilled Hoàng Kế Viêm", "Butcher Grilled Hoang Ke Viem"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng nướng than hoa phong cách Mỹ tại Ngũ Hành Sơn — bít tết, hải sản, tự nướng tại bàn.",
                                 "American-style charcoal grill restaurant in Ngu Hanh Son — steak, seafood, grill-at-table experience."))
            .description(of("Butcher Grilled Hoàng Kế Viêm — nhà hàng nướng than hoa phong cách Mỹ tại 121 Hoàng Kế Viêm, " +
                            "Q. Ngũ Hành Sơn, Đà Nẵng. Chuyên bò bít tết Mỹ, sườn, hải sản nướng than hoa tại bàn. " +
                            "Sức chứa 150 khách, 2 phòng riêng, bãi đỗ xe miễn phí. Giá 200.000–500.000đ/người.",
                            "Butcher Grilled Hoang Ke Viem — American-style charcoal grill restaurant at 121 Hoang Ke Viem, " +
                            "Ngu Hanh Son District, Da Nang. Specializing in US beef steak, ribs, and seafood charcoal-grilled at table. " +
                            "Capacity 150 guests, 2 private rooms, free parking. Price 200,000–500,000 VND/person."))
            .phone("0694395690")
            .bookingHotline("0694395690")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("500000"))
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
