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
public class SeedButcherSteakBbqLeQuangDao {

    @Bean
    CommandLineRunner seedButcherSteakBbqLeQuangDaoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedButcherSteakBbqLeQuangDao] start");
            RestaurantDoc doc = restaurantButcherSteakBbqLeQuangDao();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedButcherSteakBbqLeQuangDao] done");
        };
    }

    // =====================================================================
    //   BUTCHER STEAK & BBQ - LÊ QUANG ĐẠO (64 Lê Quang Đạo, Bắc Mỹ Phú, Ngũ Hành Sơn)
    // =====================================================================

    private static RestaurantDoc restaurantButcherSteakBbqLeQuangDao() {
        String slug = "butcher-steak-bbq-le-quang-dao-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-1-normal-2726209670445.webp",
                of("Không gian nhà hàng Butcher Steak & BBQ Lê Quang Đạo", "Butcher Steak & BBQ Le Quang Dao restaurant interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-2-normal-2726210870446.webp",
                of("Khu vực bàn nướng BBQ tại Butcher Steak", "BBQ grill table area at Butcher Steak"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-3-normal-2726212670447.webp",
                of("Không gian ăn uống sang trọng Butcher Steak & BBQ", "Elegant dining space at Butcher Steak & BBQ"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-4-normal-2726214270448.webp",
                of("Bàn ăn sang trọng tại Butcher Steak Đà Nẵng", "Elegant dining table at Butcher Steak Da Nang"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-5-normal-2726215670449.webp",
                of("Thịt bò cao cấp và hải sản tươi ngon tại Butcher Steak", "Premium beef and fresh seafood at Butcher Steak"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-6-normal-2726218370450.webp",
                of("Khu vực ngoài trời thoáng mát Butcher Steak & BBQ", "Outdoor breezy area at Butcher Steak & BBQ"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-7-normal-2726219170451.webp",
                of("Không gian tiệc tại Butcher Steak Lê Quang Đạo", "Event space at Butcher Steak Le Quang Dao"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-8-normal-2726220470452.webp",
                of("Món đặc sắc bít tết cao cấp tại Butcher Steak", "Signature premium steak at Butcher Steak"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-9-normal-2726221570453.webp",
                of("Khu vực bar và tiếp đón Butcher Steak", "Bar and reception area at Butcher Steak"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-10-normal-2726224170454.webp",
                of("Toàn cảnh nhà hàng Butcher Steak & BBQ Lê Quang Đạo", "Full view of Butcher Steak & BBQ Le Quang Dao"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-and-bbq-8-normal-2726204870041.webp",
                of("Không gian phòng VIP tại Butcher Steak & BBQ", "VIP room at Butcher Steak & BBQ"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-and-bbq-9-normal-2726205770042.webp",
                of("Bàn ăn phòng riêng Butcher Steak & BBQ", "Private room dining table at Butcher Steak & BBQ"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-and-bbq-10-normal-2726206670043.webp",
                of("Không gian nướng BBQ trong nhà tại Butcher Steak", "Indoor BBQ grill space at Butcher Steak"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-and-bbq-11-normal-2726207470044.webp",
                of("Khu vực ngồi ngoài trời Butcher Steak & BBQ", "Outdoor seating at Butcher Steak & BBQ"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-butcher-steak-and-bbq-12-normal-2726208070045.webp",
                of("Đêm tại Butcher Steak & BBQ Lê Quang Đạo Đà Nẵng", "Evening at Butcher Steak & BBQ Le Quang Dao Da Nang"), false, 14)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%201.jpg",
                of("Menu thực đơn Butcher Steak & BBQ trang 1", "Butcher Steak & BBQ menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%202.jpg",
                of("Menu thực đơn Butcher Steak & BBQ trang 2", "Butcher Steak & BBQ menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Butcher%20Steak%20%20BBQ/menu%20Butcher%20Steak%20%20BBQ%203.jpg",
                of("Menu thực đơn Butcher Steak & BBQ trang 3", "Butcher Steak & BBQ menu page 3"), false, 2),
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
                .name(of("Steakhouse - Bít tết cao cấp", "Premium steakhouse"))
                .region("GLOBAL")
                .build(),
            CuisineTag.builder()
                .code("BBQ_GRILL")
                .name(of("BBQ nướng than hoa", "Charcoal BBQ grill"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("COUPLE",         of("Hẹn hò bít tết lãng mạn", "Romantic steak dinner date")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary celebration")),
            suitable("BIRTHDAY",       of("Tiệc sinh nhật ấm cúng", "Intimate birthday celebration")),
            suitable("BUSINESS_MEAL",  of("Bữa ăn kinh doanh sang trọng", "Elegant business dining")),
            suitable("FRIENDS",        of("Tụ tập bạn bè ăn BBQ", "BBQ gathering with friends")),
            suitable("FAMILY",         of("Bữa ăn gia đình đặc biệt", "Special family dinner")),
            suitable("COMPANY_PARTY",  of("Liên hoan nhóm nhỏ", "Small group party")),
            suitable("COMPANY_EVENT",  of("Sự kiện công ty VIP", "VIP corporate event")),
            suitable("GROUP_MEETING",  of("Họp nhóm và ăn tối làm việc", "Work group dinner meeting")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng cao cấp", "Premium office lunch"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",    of("Sang trọng, tinh tế", "Elegant and refined")),
            ambience("ROMANTIC",   of("Lãng mạn cho cặp đôi", "Romantic for couples")),
            ambience("MODERN",     of("Hiện đại, phong cách", "Modern and stylish")),
            ambience("COZY",       of("Ấm cúng, thoải mái", "Cozy and comfortable")),
            ambience("VIBRANT",    of("Sôi động buổi tối cuối tuần", "Vibrant on weekend evenings"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(60)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Wagyu A5 bít tết nướng than hoa", "Wagyu A5 charcoal-grilled steak")),
            signatureDishL(of("Thăn nội bò Mỹ (Tenderloin) 250g", "US beef tenderloin 250g")),
            signatureDishL(of("Sườn T-bone bò Úc nướng lửa than", "Australian T-bone beef charcoal-grilled")),
            signatureDishL(of("Sườn bò BBQ sốt mật ong tỏi đen", "Beef ribs BBQ with black garlic honey sauce")),
            signatureDishL(of("Đùi cừu Úc nướng thảo mộc hương chanh", "Australian lamb leg with lemon herb rub")),
            signatureDishL(of("Tôm hùm nướng bơ tỏi pho mát", "Lobster grilled with garlic butter and cheese")),
            signatureDishL(of("Cá hồi áp chảo sốt demi-glace", "Pan-seared salmon with demi-glace sauce")),
            signatureDishL(of("Set menu VIP 2 người — Butcher Signature", "VIP set menu for 2 — Butcher Signature")),
            signatureDishL(of("Sườn heo Baby Back nướng than với sốt BBQ Mỹ", "Baby back pork ribs with American BBQ sauce")),
            signatureDishL(of("Xúc xích bò Wagyu nướng và khoai tây kiểu Tây", "Wagyu beef sausage with Western-style potatoes"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("PREMIUM_STEAK")
                .name(of("Bít tết cao cấp", "Premium steaks"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Wagyu A5 bít tết 200g", "Wagyu A5 steak 200g"))
                        .description(of("Bò Wagyu A5 nhập khẩu, nướng than hoa theo yêu cầu, ăn kèm nước sốt đặc biệt.", "Imported Wagyu A5, charcoal-grilled to order, served with special sauce."))
                        .priceFrom(new BigDecimal("600000"))
                        .priceTo(new BigDecimal("900000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("wagyu", "premium", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set menu VIP 2 người — Butcher Signature", "VIP set menu for 2 — Butcher Signature"))
                        .description(of("Soup, khai vị, bít tết chọn, hải sản nướng, tráng miệng và thức uống.", "Soup, appetizer, choice steak, grilled seafood, dessert and drinks."))
                        .priceFrom(new BigDecimal("800000"))
                        .priceTo(new BigDecimal("1200000"))
                        .unit(of("set 2 người", "set for 2"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "vip", "couple", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô trước nhà hàng trên đường Lê Quang Đạo", "Car parking in front of the restaurant on Le Quang Dao Street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Khu vực xe máy bên cạnh nhà hàng", "Motorbike area beside the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe cho tất cả khách dùng bữa.", "Free parking for all dining guests."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "WIFI",
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "CHARCOAL_GRILL_TABLE",
            "PROJECTOR",
            "SMOKING_AREA",
            "LIVE_MUSIC"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Butcher Steak & BBQ Lê Quang Đạo", "About Butcher Steak & BBQ Le Quang Dao")),
            paragraph(ContentSection.STORY,
                of("Butcher Steak & BBQ Lê Quang Đạo là flagship restaurant của thương hiệu Butcher tại Đà Nẵng, " +
                   "tọa lạc tại số 64 đường Lê Quang Đạo, phường Bắc Mỹ Phú, quận Ngũ Hành Sơn. " +
                   "Đây là cơ sở cao cấp hơn của chuỗi Butcher, được thiết kế theo phong cách nhà hàng bít tết " +
                   "hạng sang với không gian rộng thoáng, ánh đèn ấm sang trọng và đầu bếp chuyên nghiệp về thịt cao cấp. " +
                   "Butcher Steak & BBQ đã xây dựng được danh tiếng là địa chỉ bít tết bò Wagyu và Mỹ " +
                   "hàng đầu khu vực Ngũ Hành Sơn — Bắc Mỹ Phú.",
                   "Butcher Steak & BBQ Le Quang Dao is the flagship restaurant of the Butcher brand in Da Nang, " +
                   "located at 64 Le Quang Dao Street, Bac My Phu Ward, Ngu Hanh Son District. " +
                   "This is the premium tier of the Butcher chain, designed in an upscale steakhouse style " +
                   "with spacious interiors, warm luxury lighting, and professional chefs specializing in premium cuts. " +
                   "Butcher Steak & BBQ has built a reputation as the top Wagyu and US beef steak destination " +
                   "in the Ngu Hanh Son — Bac My Phu area.")),
            heading(ContentSection.STORY, of("Không gian & thiết kế sang trọng", "Luxurious space & interior design")),
            paragraph(ContentSection.STORY,
                of("Không gian Butcher Steak & BBQ được đầu tư bài bản theo phong cách steakhouse cao cấp: " +
                   "sàn gỗ tự nhiên, tường gạch mộc mạc, hệ thống đèn chiếu vàng ấm cúng tạo bầu không khí thân mật sang trọng. " +
                   "Sức chứa 180 khách gồm khu vực main dining rộng thoáng, khu bar với đầy đủ cocktail và rượu vang nhập, " +
                   "khu ngoài trời thoáng đãng và 3 phòng riêng VIP sức chứa tối đa 40 khách mỗi phòng. " +
                   "Mỗi bàn trang bị bếp nướng than hoa riêng với hệ thống hút khói âm trần, " +
                   "đảm bảo tận hưởng trọn vẹn trải nghiệm nướng tại bàn trong không gian trong lành.",
                   "Butcher Steak & BBQ's interior is professionally designed in a premium steakhouse style: " +
                   "natural wood floors, rustic brick walls, warm golden lighting creating an intimate yet elegant atmosphere. " +
                   "Capacity of 180 guests includes a spacious main dining area, a full bar with cocktails and imported wine, " +
                   "an outdoor terrace, and 3 VIP private rooms accommodating up to 40 guests each. " +
                   "Each table is equipped with its own charcoal grill and ceiling-mounted smoke extractor, " +
                   "ensuring a smoke-free at-table grilling experience in pristine air.")),
            heading(ContentSection.STORY, of("Thực đơn cao cấp — bò Wagyu, bò Mỹ, hải sản nhập khẩu", "Premium menu — Wagyu, US beef, imported seafood")),
            paragraph(ContentSection.STORY,
                of("Thực đơn tại Butcher Steak & BBQ được xây dựng dành riêng cho người yêu thịt bò cao cấp. " +
                   "Điểm nhấn là bò Wagyu A5 nhập khẩu Nhật, bò Angus Mỹ, bò Úc Black Angus và thăn nội tenderloin nguyên khối. " +
                   "Mỗi loại thịt được phục vụ với sốt đặc trưng riêng — từ sốt tiêu đen kiểu Pháp, sốt demi-glace, " +
                   "sốt truffle đến sốt BBQ mật ong tỏi đen đặc chế. " +
                   "Ngoài thịt bò, thực đơn còn có đùi cừu Úc, tôm hùm nướng, cá hồi Na Uy và đặc biệt là " +
                   "set menu VIP 2 người Butcher Signature — kết hợp hoàn hảo giữa bít tết và hải sản cao cấp.",
                   "Butcher Steak & BBQ's menu is built specifically for premium beef lovers. " +
                   "Highlights include imported Japanese Wagyu A5, US Angus beef, Australian Black Angus, and whole-cut tenderloin. " +
                   "Each cut is served with its own signature sauce — from French-style black pepper, demi-glace, " +
                   "truffle sauce to house-special black garlic honey BBQ sauce. " +
                   "Beyond beef, the menu includes Australian lamb leg, grilled lobster, Norwegian salmon, and especially " +
                   "the Butcher Signature VIP set menu for 2 — a perfect combination of premium steak and seafood.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Butcher Steak & BBQ là lựa chọn lý tưởng khi cần một bữa tối ấn tượng tại Đà Nẵng: " +
                   "hẹn hò cặp đôi với bít tết Wagyu và rượu vang lãng mạn, " +
                   "kỷ niệm sinh nhật hay ngày cưới đáng nhớ trong phòng riêng VIP, " +
                   "tiếp khách doanh nghiệp quan trọng trong không gian sang trọng chuyên nghiệp, " +
                   "hay tiệc cuối năm nhóm nhỏ đặc biệt. " +
                   "Nhà hàng cũng là địa chỉ ưa thích của du khách nước ngoài " +
                   "muốn thưởng thức bít tết chất lượng cao tại Đà Nẵng với mức giá hợp lý hơn các thành phố lớn.",
                   "Butcher Steak & BBQ is the ideal choice when you need an impressive dinner in Da Nang: " +
                   "a romantic couple's date with Wagyu steak and wine, " +
                   "a memorable birthday or wedding anniversary in a VIP private room, " +
                   "hosting important business clients in a professional luxury setting, " +
                   "or a special year-end small group party. " +
                   "The restaurant is also a favorite among international tourists " +
                   "wanting high-quality steak in Da Nang at prices more reasonable than major cities.")),
            heading(ContentSection.STORY, of("Vị trí & khu vực xung quanh", "Location & surrounding area")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tại số 64 Lê Quang Đạo, phường Bắc Mỹ Phú, quận Ngũ Hành Sơn — " +
                   "khu vực cao cấp của Đà Nẵng gần bãi biển Non Nước và các resort 5 sao như Hyatt Regency, " +
                   "Vinpearl Da Nang Resort. Cách trung tâm thành phố khoảng 9km về phía Nam, " +
                   "cách bãi biển Mỹ Khê 3km và danh thắng Ngũ Hành Sơn 2km. " +
                   "Đây là lựa chọn hoàn hảo cho du khách đang lưu trú tại các resort Bắc Mỹ An — Non Nước " +
                   "muốn có bữa tối cao cấp mà không cần vào trung tâm thành phố.",
                   "The restaurant is at 64 Le Quang Dao Street, Bac My Phu Ward, Ngu Hanh Son District — " +
                   "a premium area of Da Nang near Non Nuoc Beach and 5-star resorts like Hyatt Regency " +
                   "and Vinpearl Da Nang Resort. About 9km south of the city center, " +
                   "3km from My Khe Beach, and 2km from the Marble Mountains. " +
                   "A perfect choice for tourists staying at Bac My An — Non Nuoc resorts " +
                   "who want an upscale dinner without traveling to the city center.")),
            heading(ContentSection.STORY, of("Dịch vụ cao cấp & tiện ích đặc biệt", "Premium services & special amenities")),
            paragraph(ContentSection.STORY,
                of("Butcher Steak & BBQ cung cấp dịch vụ cao cấp: WiFi miễn phí tốc độ cao, " +
                   "bãi đỗ xe miễn phí cho ô tô và xe máy, ghế trẻ em. " +
                   "Bar đầy đủ với cocktail, rượu vang nhập, bia thủ công. " +
                   "Phòng riêng VIP với hệ thống âm thanh độc lập, máy chiếu HD và trang trí theo chủ đề. " +
                   "Chấp nhận Visa/Master, xuất hóa đơn VAT đầy đủ. " +
                   "Dịch vụ MC và trang trí tiệc cho sinh nhật, kỷ niệm và sự kiện doanh nghiệp.",
                   "Butcher Steak & BBQ provides premium services: high-speed free WiFi, " +
                   "free parking for cars and motorbikes, children's chairs. " +
                   "Full bar with cocktails, imported wines, and craft beer. " +
                   "VIP private rooms with independent sound systems, HD projectors, and themed decoration. " +
                   "Visa/Master accepted, full VAT invoice issuance. " +
                   "MC services and party decoration for birthdays, anniversaries, and corporate events.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước ít nhất 2 tiếng để đảm bảo chỗ ngồi tốt nhất. " +
                   "Đặt cọc bắt buộc từ nhóm 8 khách trở lên. Phòng riêng VIP cần đặt trước tối thiểu 48 giờ " +
                   "và đặt cọc 500.000đ. Hủy trước 24 giờ được hoàn cọc đầy đủ. " +
                   "Giá set menu VIP 2 người từ 800.000đ, đặt riêng từng món từ 200.000đ/phần. " +
                   "Phụ thu 5% dịch vụ cho nhóm tiệc sự kiện và phòng riêng.",
                   "Book at least 2 hours in advance to secure the best seats. " +
                   "Deposits required for groups of 8 or more. VIP private rooms require at least 48-hour advance booking " +
                   "and a 500,000 VND deposit. Cancellations 24+ hours in advance receive a full refund. " +
                   "VIP set menu for 2 from 800,000 VND; individual dishes from 200,000 VND per portion. " +
                   "5% service charge applies to event party groups and private room bookings.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Butcher Steak & BBQ Lê Quang Đạo", "Why choose Butcher Steak & BBQ Le Quang Dao")),
            paragraph(ContentSection.STORY,
                of("Butcher Steak & BBQ Lê Quang Đạo là địa chỉ bít tết bò Wagyu và BBQ cao cấp " +
                   "số 1 tại khu vực Ngũ Hành Sơn — Bắc Mỹ Phú, Đà Nẵng. " +
                   "Kết hợp hoàn hảo giữa thịt cao cấp nguồn gốc rõ ràng, kỹ thuật nướng than hoa bài bản, " +
                   "không gian sang trọng phù hợp mọi dịp đặc biệt và mức giá hợp lý. " +
                   "Thuận tiện cho cả du khách tại các resort Non Nước và cư dân Bắc Mỹ An cao cấp. " +
                   "Đây là nơi để tạo ra những bữa ăn đáng nhớ cho chuyến du lịch Đà Nẵng.",
                   "Butcher Steak & BBQ Le Quang Dao is the #1 premium Wagyu steak and BBQ destination " +
                   "in the Ngu Hanh Son — Bac My Phu area of Da Nang. " +
                   "A perfect combination of traceable premium cuts, skilled charcoal grilling technique, " +
                   "elegant space suited to every special occasion, and reasonable pricing. " +
                   "Convenient for both tourists at Non Nuoc resorts and upscale Bac My An residents. " +
                   "This is the place to create memorable dining experiences on your Da Nang trip.")),
            mapBlock(ContentSection.STORY, new double[]{108.245226, 16.050838})
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
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng từ nhóm 8 khách, phòng riêng VIP 500.000đ. Hoàn cọc hủy trước 24 giờ.", "Applies from groups of 8; VIP private room 500,000 VND. Refundable with 24-hour cancellation."))
            .hasPromotion(true)
            .promotionSummary(of("Giảm 10% cho khách đặt bàn qua hệ thống, không áp dụng ngày lễ.", "10% off for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi có thể thay đổi, liên hệ nhà hàng để biết thêm.", "Promotions may vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT đầy đủ theo yêu cầu.", "Full VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% cho tiệc sự kiện và phòng riêng VIP.", "5% service charge for event parties and VIP private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang đồ ăn từ ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không nhận đồ uống mang ngoài, nhà hàng có wine list và cocktail đầy đủ.", "Outside drinks not accepted; full wine list and cocktails available."))
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
                .id("tb-2-indoor-steak")
                .name(of("Bàn đôi trong nhà — phong cách steakhouse", "Indoor couple table — steakhouse style"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi trong nhà, ánh đèn ấm cúng, phù hợp cặp đôi hẹn hò.", "Indoor couple table with warm lighting, perfect for romantic dates."))
                .build(),
            TableType.builder()
                .id("tb-6-group")
                .name(of("Bàn nhóm 6 người", "Group table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(18)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn nhóm 6 người trong nhà hoặc ngoài trời thoáng.", "Group table for 6, indoor or outdoor."))
                .build(),
            TableType.builder()
                .id("tb-vip-private-steak")
                .name(of("Phòng riêng VIP (tối đa 40 khách)", "VIP private room (up to 40 guests)"))
                .seats(40)
                .minPeople(10)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng VIP tối đa 40 khách, có âm thanh, máy chiếu HD và trang trí theo yêu cầu.", "VIP private room up to 40 guests with sound system, HD projector, and custom decoration."))
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
            .addressLine(of("Số 64 Đường Lê Quang Đạo, P. Bắc Mỹ Phú, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                           "64 Le Quang Dao Street, Bac My Phu Ward, Ngu Hanh Son District, Da Nang"))
            .location(new double[]{108.245226, 16.050838})
            .name(of("Butcher Steak & BBQ Lê Quang Đạo", "Butcher Steak & BBQ Le Quang Dao"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng bít tết Wagyu & BBQ cao cấp tại Ngũ Hành Sơn Đà Nẵng — nướng than hoa tại bàn, phòng riêng VIP.",
                                 "Premium Wagyu steak & BBQ restaurant in Ngu Hanh Son Da Nang — at-table charcoal grill, VIP private rooms."))
            .description(of("Butcher Steak & BBQ Lê Quang Đạo — flagship nhà hàng bít tết cao cấp của chuỗi Butcher tại Đà Nẵng, " +
                            "số 64 Lê Quang Đạo, Q. Ngũ Hành Sơn. Chuyên Wagyu A5, bò Mỹ/Úc, hải sản nướng than hoa tại bàn. " +
                            "Sức chứa 180 khách, 3 phòng riêng VIP, bar đầy đủ. Giá 200.000–900.000đ/người.",
                            "Butcher Steak & BBQ Le Quang Dao — flagship premium steakhouse of the Butcher chain in Da Nang, " +
                            "64 Le Quang Dao Street, Ngu Hanh Son District. Specializing in Wagyu A5, US/Australian beef, seafood charcoal-grilled at table. " +
                            "Capacity 180 guests, 3 VIP private rooms, full bar. Price 200,000–900,000 VND/person."))
            .phone("0694395690")
            .bookingHotline("0694395690")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("900000"))
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
