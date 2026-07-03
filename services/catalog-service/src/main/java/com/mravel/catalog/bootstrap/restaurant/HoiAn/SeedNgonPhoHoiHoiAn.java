package com.mravel.catalog.bootstrap.restaurant.HoiAn;

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
public class SeedNgonPhoHoiHoiAn {

    @Bean
    CommandLineRunner seedNgonPhoHoiHoiAnRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedNgonPhoHoiHoiAn] start");

            RestaurantDoc doc = restaurantNgonPhoHoiHoiAn();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedNgonPhoHoiHoiAn] done");
        };
    }

    // =====================================================================
    //  NGON PHỐ HỘI - AUTHENTIC VIETNAMESE CUISINE - HỘI AN
    // =====================================================================

    private static RestaurantDoc restaurantNgonPhoHoiHoiAn() {
        String slug = "ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-1-normal-4213910872660.webp",
                of("Không gian nhà hàng Ngon Phố Hội truyền thống Hội An", "Traditional Ngon Pho Hoi restaurant interior in Hoi An"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-2-normal-4213910972661.webp",
                of("Khu vực ăn uống tầng 1 trang nhã", "Elegant first-floor dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-3-normal-4213911072662.webp",
                of("Không gian ấm cúng mang nét đặc trưng phố cổ Hội An", "Cozy atmosphere with Hoi An old quarter character"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-4-normal-4213911172663.webp",
                of("Tầng 2 không gian ăn uống rộng rãi", "Spacious second floor dining space"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-5-normal-4213911272664.webp",
                of("Các món ẩm thực đặc sản Hội An", "Hoi An specialty dishes"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-6-normal-4213911372665.webp",
                of("Mẹt đặc sản Hội An đầy đủ món", "Complete Hoi An specialty platter"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-7-normal-4213911472666.webp",
                of("Cao Lầu và Mỳ Quảng Hội An chính hiệu", "Authentic Cao Lau and My Quang Hoi An"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-8-normal-4213911572667.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-9-normal-4213911672668.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-10-normal-4213911772669.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-11-normal-4213911872670.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/ngon-pho-hoi-12-normal-4213911972671.webp",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-1.jpg",
                of("Ngon Phố Hội view từ bên ngoài phố Hội An", "Ngon Pho Hoi exterior view on Hoi An street"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-2.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-3.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-4.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-5.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-6.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-7.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/S%C3%A1ng%20%20Mkt/2025/2.%20Gian%20h%C3%A0ng/06.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-pho-hoi-8.jpg",
                of("", ""), false, 19)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-1.jpg",
                of("Menu Ngon Phố Hội trang 1", "Ngon Pho Hoi menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/05.%20Ngon%20Ph%E1%BB%91%20H%E1%BB%99i/ngon-hoi-pho-menu-15.jpg",
                of("", ""), false, 14)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt Nam chính hiệu", "Authentic Vietnamese cuisine"))
                .region("VIETNAM")
                .build(),
            CuisineTag.builder()
                .code("CENTRAL_VIETNAMESE")
                .name(of("Ẩm thực miền Trung — đặc sản Hội An", "Central Vietnamese cuisine — Hoi An specialties"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOI_AN_SPECIALTY")
                .name(of("Đặc sản phố cổ Hội An", "Hoi An ancient town specialties"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    6, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   6, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 6, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  6, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    6, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  6, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    6, 30, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",         of("Ăn gia đình trải nghiệm đặc sản Hội An", "Family dining with Hoi An specialty experience")),
            suitable("COMPANY_PARTY",  of("Tiệc công ty tại không gian Hội An truyền thống", "Company party in traditional Hoi An setting")),
            suitable("GROUP_MEETING",  of("Họp nhóm, gặp gỡ đối tác", "Group meeting and partner gathering")),
            suitable("FRIENDS",        of("Tụ tập bạn bè thưởng thức đặc sản miền Trung", "Friends gathering for Central Vietnamese specialties")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật không gian phố cổ", "Birthday celebration in ancient town ambiance")),
            suitable("COMPANY_EVENT",  of("Sự kiện công ty và tiệc hội nghị", "Corporate events and conference banquet")),
            suitable("COUPLE",         of("Hẹn hò lãng mạn phong cách Hội An", "Romantic Hoi An-style date")),
            suitable("ANNIVERSARY",    of("Kỷ niệm đặc biệt tại nhà hàng đặc sản", "Special anniversary at specialty restaurant")),
            suitable("GROUP_TRAVEL",   of("Đoàn du khách khám phá ẩm thực phố cổ Hội An", "Tourist groups exploring Hoi An ancient town cuisine")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa nhóm với thực đơn đặc sản", "Group lunch with specialty menu"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("TRADITIONAL", of("Không gian truyền thống phố cổ Hội An", "Traditional Hoi An ancient town atmosphere")),
            ambience("COZY",        of("Ấm cúng, thân mật", "Cozy and intimate")),
            ambience("RUSTIC",      of("Mộc mạc, gần gũi đậm chất miền Trung", "Rustic, close-to-nature Central Vietnam style"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(100)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(100)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Mỳ Quảng — mỳ sợi vàng nước lèo đậm đà kiểu Quảng Nam", "My Quang — turmeric noodles in rich Quang Nam-style broth")),
            signatureDishL(of("Cao Lầu — mỳ đặc trưng Hội An với thịt xá xíu và rau sống", "Cao Lau — Hoi An's signature noodle with char siu pork and herbs")),
            signatureDishL(of("Cơm Gà Hội An — cơm vàng nghệ thịt gà ta xé sợi", "Hoi An Chicken Rice — turmeric rice with hand-shredded free-range chicken")),
            signatureDishL(of("Bò Nướng Ngũ Vị — bò nướng tỏi sả ngũ vị thơm lừng", "Five-Spice Grilled Beef — fragrant lemongrass and five-spice beef")),
            signatureDishL(of("Gỏi Bưởi Tôm Tơ — gỏi bưởi tôm đặc trưng miền Trung", "Pomelo and Silk Shrimp Salad — Central Vietnamese specialty salad")),
            signatureDishL(of("Mẹt Đặc Sản Hội An — mẹt gỗ gồm nhiều món đặc sản địa phương", "Hoi An Specialty Wooden Platter — assorted local specialty dishes")),
            signatureDishL(of("Bánh Xèo Hội An — bánh xèo nhân tôm thịt giòn rụm", "Hoi An Sizzling Pancake — crispy crepe with shrimp and pork filling")),
            signatureDishL(of("Hoành Thánh Chiên — hoành thánh chiên giòn sốt chua ngọt đặc trưng", "Fried Wonton — crispy wonton with signature sweet-and-sour sauce")),
            signatureDishL(of("Tôm Chua Hội An — tôm muối chua lên men truyền thống", "Hoi An Sour Shrimp — traditionally fermented pickled shrimp")),
            signatureDishL(of("Chè Hội An — chè ngọt mát nhiều loại đặc sản", "Hoi An Sweet Dessert Soup — assorted specialty sweet soups"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOI_AN_SPECIALTIES")
                .name(of("Đặc sản Hội An chính hiệu", "Authentic Hoi An Specialties"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Mẹt Đặc Sản Hội An (2-3 người)", "Hoi An Specialty Platter (2-3 persons)"))
                        .description(of("Mẹt gỗ gồm Cao Lầu, Cơm Gà, Bánh Xèo, Hoành Thánh và các món ăn kèm đặc trưng.", "Wooden platter with Cao Lau, Chicken Rice, Sizzling Pancake, Wonton and signature side dishes."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("380000"))
                        .unit(of("mẹt", "platter"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "specialty", "sharing", "hoi-an"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cao Lầu", "Cao Lau"))
                        .description(of("Mỳ đặc trưng Hội An với sợi mỳ vàng dai, thịt xá xíu thái lát, rau sống tươi và nước lèo cô đặc.", "Hoi An's signature noodles with chewy yellow noodles, sliced char siu pork, fresh herbs, and rich broth."))
                        .priceFrom(new BigDecimal("65000"))
                        .priceTo(new BigDecimal("85000"))
                        .unit(of("tô", "bowl"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("cao-lau", "signature", "hoi-an"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi gửi xe có bảo vệ gần nhà hàng", "Guarded parking lot near restaurant"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi gửi xe có bảo vệ gần nhà hàng", "Guarded parking lot near restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy miễn phí, ô tô phí 30,000đ/lần. Bãi xe có bảo vệ hướng dẫn.",
                      "Free motorbike parking, car parking 30,000 VND per visit. Guarded parking with security attendant."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "PRIVATE_ROOM",
            "BALLROOM",
            "ELEVATOR_ACCESS",
            "KIDS_CHAIR",
            "EVENT_DECOR",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "SET_LUNCH"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Ngon Phố Hội — Ẩm thực Việt chính hiệu", "About Ngon Pho Hoi — Authentic Vietnamese Cuisine")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Hội là nhà hàng ẩm thực Việt Nam chính hiệu tọa lạc tại số 21 La Hối, Phường Hội An — " +
                   "trái tim của đô thị cổ Hội An được UNESCO công nhận là Di sản Văn hóa Thế giới. " +
                   "Với sức chứa lên tới 300 khách trên 2 tầng và 2 phòng chức năng lớn, " +
                   "Ngon Phố Hội là điểm đến ẩm thực cộng đồng lớn nhất trong khu vực nội ô Hội An, " +
                   "phục vụ từ bữa sáng sớm 6:30 cho đến khuya 22:00 mỗi ngày trong tuần.",
                   "Ngon Pho Hoi is an authentic Vietnamese restaurant located at 21 La Hoi Street, Hoi An Ward — " +
                   "at the heart of the Hoi An Ancient Town, a UNESCO World Heritage Site. " +
                   "With a capacity of up to 300 guests across 2 floors and 2 large function rooms, " +
                   "Ngon Pho Hoi is the largest community dining destination in Hoi An's inner city, " +
                   "serving from early breakfast at 6:30 AM until late at 10:00 PM every day of the week.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa đa tầng", "Multi-floor space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trải rộng trên 2 tầng với tổng 300 chỗ ngồi: Tầng 1 rộng thoáng phục vụ 150-200 khách " +
                   "với không khí ấm áp kiểu nhà phố Hội An xưa — trần cao, đèn lồng truyền thống, bàn ghế gỗ mộc mạc. " +
                   "Tầng 2 yên tĩnh hơn, phù hợp nhóm gia đình và tiệc nhỏ, sức chứa 100 khách. " +
                   "Nổi bật là Ballroom rộng lớn sức chứa 80-100 khách dành cho tiệc cưới, hội nghị và sự kiện công ty, " +
                   "cùng VIP Room riêng 10 khách trang nhã dành cho bữa ăn đặc biệt.",
                   "The restaurant spans 2 floors with a total of 300 seats: the spacious Ground Floor serves 150-200 guests " +
                   "with the warm ambiance of a classic Hoi An townhouse — high ceilings, traditional lanterns, rustic wooden furniture. " +
                   "The quieter Second Floor suits family groups and small parties, accommodating 100 guests. " +
                   "A highlight is the large Ballroom seating 80-100 guests for weddings, conferences, and corporate events, " +
                   "alongside an elegant VIP Room for 10 guests for intimate special-occasion dining.")),
            heading(ContentSection.STORY, of("Thực đơn đặc sản Hội An — Mỳ Quảng, Cao Lầu & hơn thế nữa", "Hoi An specialty menu — My Quang, Cao Lau & more")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Ngon Phố Hội là hành trình khám phá toàn bộ tinh hoa ẩm thực Hội An và miền Trung Việt Nam. " +
                   "Mỳ Quảng — biểu tượng ẩm thực Quảng Nam — được nấu với nước lèo đậm đà từ xương heo, " +
                   "mỳ sợi vàng nghệ tươi và nhân tôm thịt thơm ngon. Cao Lầu — món ăn độc nhất Hội An — " +
                   "với sợi mỳ đặc biệt chỉ làm được từ nước giếng Bá Lễ và lửa củi tro, " +
                   "ăn kèm thịt xá xíu và rau sống đặc trưng. Cơm Gà Hội An cơm vàng nghệ mềm dẻo, " +
                   "gà ta xé sợi ướp gia vị đặc trưng — tinh tế và đậm đà khó quên.",
                   "Ngon Pho Hoi's menu is a journey through the finest culinary heritage of Hoi An and Central Vietnam. " +
                   "My Quang — Quang Nam's culinary icon — is simmered in a rich pork bone broth " +
                   "with fresh turmeric yellow noodles and fragrant shrimp-pork toppings. Cao Lau — Hoi An's uniquely local dish — " +
                   "features special noodles that can only be made with water from Ba Le Well and wood-ash fire, " +
                   "served with char siu pork and signature fresh herbs. Hoi An Chicken Rice features soft turmeric rice " +
                   "with hand-shredded free-range chicken marinated in distinctive local spices — subtle and unforgettably flavorful.")),
            heading(ContentSection.STORY, of("Mẹt đặc sản — trải nghiệm ẩm thực Hội An đầy đủ nhất", "Specialty platter — the most complete Hoi An culinary experience")),
            paragraph(ContentSection.STORY,
                of("Mẹt Đặc Sản Hội An là lựa chọn hoàn hảo cho những ai muốn thưởng thức nhiều món trong một lần. " +
                   "Mẹt gỗ đặc trưng được bày biện đẹp mắt với Cao Lầu, Cơm Gà, Bánh Xèo giòn rụm, " +
                   "Hoành Thánh chiên sốt chua ngọt, Gỏi Bưởi Tôm Tơ và các loại rau sống đặc trưng miền Trung. " +
                   "Ngoài ra, Bò Nướng Ngũ Vị thơm lừng sả tỏi và Tôm Chua lên men truyền thống cũng là những điểm nhấn " +
                   "không thể bỏ qua khi đến Ngon Phố Hội. Phần tráng miệng Chè Hội An với nhiều loại chè đặc sản ngọt mát " +
                   "là dấu chấm hết hoàn hảo cho bữa ăn.",
                   "The Hoi An Specialty Platter is the perfect choice for those wanting to experience multiple dishes in one sitting. " +
                   "The distinctive wooden platter is beautifully arranged with Cao Lau, Chicken Rice, crispy Sizzling Pancake, " +
                   "Fried Wonton with sweet-and-sour sauce, Pomelo and Silk Shrimp Salad, and Central Vietnamese fresh herbs. " +
                   "Five-Spice Grilled Beef fragrant with lemongrass and garlic, and traditionally fermented Sour Shrimp " +
                   "are also unmissable highlights when visiting Ngon Pho Hoi. " +
                   "The dessert course of Hoi An Sweet Soup with various specialty sweet soups is the perfect sweet ending to the meal.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Hội phục vụ đa dạng nhu cầu: từ bữa sáng phở/bún, bữa trưa cơm nhà đến tiệc cưới quy mô lớn trong Ballroom. " +
                   "Đặc biệt phù hợp cho đoàn du khách muốn khám phá ẩm thực chính hiệu khu phố cổ, " +
                   "các gia đình sum họp dịp lễ Tết, tiệc công ty năm mới và họp mặt đồng nghiệp. " +
                   "VIP Room trang nhã dành cho bữa ăn gặp gỡ đối tác, kỷ niệm hoặc tiệc sinh nhật riêng tư, ấm cúng.",
                   "Ngon Pho Hoi serves diverse needs: from morning pho/noodles, daily lunch to large-scale wedding banquets in the Ballroom. " +
                   "Especially well-suited for tourist groups exploring authentic old quarter cuisine, " +
                   "families reuniting during holidays and festivals, corporate New Year parties, and colleague gatherings. " +
                   "The elegant VIP Room is ideal for partner meetings, anniversaries, or intimate private birthday celebrations.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Hội cung cấp dịch vụ đặt tiệc trọn gói với lựa chọn set menu theo số lượng khách. " +
                   "Ballroom có máy chiếu, hệ thống âm thanh chuyên nghiệp và dịch vụ MC theo yêu cầu. " +
                   "Trang trí bàn tiệc theo chủ đề: hoa tươi, đèn lồng Hội An, phong cách truyền thống. " +
                   "WiFi miễn phí, thang máy thuận tiện cho khách cao tuổi và sự kiện. " +
                   "Xuất hóa đơn VAT theo yêu cầu. Ghế trẻ em sẵn có.",
                   "Ngon Pho Hoi provides complete banquet packages with set menu options by guest count. " +
                   "The Ballroom has a projector, professional sound system, and MC service on request. " +
                   "Themed table decorations available: fresh flowers, Hoi An lanterns, traditional style. " +
                   "Free WiFi, elevator for elderly guests and events. " +
                   "VAT invoices available on request. Children's chairs available.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Hội nằm tại số 21 La Hối, ngay trung tâm Phường Hội An — " +
                   "cách Chùa Cầu (Cầu Nhật Bản) lịch sử chỉ 500m, cách bãi biển An Bàng 4km, cách bãi biển Cửa Đại 3km. " +
                   "Du khách từ khu phố cổ Hội An có thể đi bộ hoặc thuê xe đạp đến trong vài phút. " +
                   "Từ Đà Nẵng di chuyển khoảng 30 phút bằng taxi hoặc xe máy. " +
                   "Bãi xe gần nhà hàng có bảo vệ hướng dẫn, xe máy miễn phí.",
                   "Ngon Pho Hoi is located at 21 La Hoi Street, in the heart of Hoi An Ward — " +
                   "just 500m from the historic Japanese Covered Bridge, 4km from An Bang Beach, 3km from Cua Dai Beach. " +
                   "Guests from Hoi An Ancient Town can walk or rent bicycles to arrive within minutes. " +
                   "From Da Nang, approximately 30 minutes by taxi or motorbike. " +
                   "Guarded parking near the restaurant; free motorbike parking.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt tiệc", "Booking & banquet policy")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn thường nhận trước ít nhất 2 tiếng. Đặt tiệc Ballroom cần đặt trước ít nhất 3 ngày. " +
                   "Đặt cọc 30% tổng bill khi đặt tiệc nhóm từ 30 người. " +
                   "Hủy tiệc trước 72 giờ được hoàn cọc đầy đủ; hủy dưới 72 giờ mất 50% cọc. " +
                   "Phí trông xe ô tô 30,000đ/lần; xe máy miễn phí.",
                   "Regular bookings accepted at least 2 hours in advance. Ballroom banquet bookings must be made at least 3 days ahead. " +
                   "30% deposit required for group banquets of 30 or more. " +
                   "Full refund for cancellations more than 72 hours before event; 50% forfeited within 72 hours. " +
                   "Car parking fee 30,000 VND per visit; motorbike parking free.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Ngon Phố Hội", "Why choose Ngon Pho Hoi")),
            paragraph(ContentSection.STORY,
                of("Ngon Phố Hội mang đến trải nghiệm ẩm thực Hội An chính hiệu nhất — nơi mọi món ăn từ Cao Lầu, Mỳ Quảng " +
                   "đến Cơm Gà đều được chế biến theo công thức truyền thống từ nguyên liệu địa phương. " +
                   "Với quy mô lớn (300 khách), giá cả hợp lý (~250,000đ/người), phục vụ cả ngày từ sáng sớm, " +
                   "nhà hàng là lựa chọn đáng tin cậy cho mọi nhóm du khách, gia đình và doanh nghiệp ghé thăm Hội An.",
                   "Ngon Pho Hoi offers the most authentic Hoi An culinary experience — where every dish from Cao Lau and My Quang " +
                   "to Chicken Rice is prepared following traditional recipes with local ingredients. " +
                   "With its large scale (300 guests), affordable prices (~250,000 VND/person), all-day service from early morning, " +
                   "the restaurant is a trusted choice for all tourist groups, families, and businesses visiting Hoi An.")),
            mapBlock(ContentSection.STORY, new double[]{108.3237, 15.8796})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(1, 15, of("Không áp dụng ưu đãi ngày Rằm tháng Giêng", "Promotion not applicable on the 15th of the first lunar month")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(30)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Đặt cọc 30% tổng bill cho tiệc nhóm từ 30 khách. Ballroom cần đặt trước ít nhất 3 ngày.",
                             "30% deposit for group banquets of 30+. Ballroom must be booked at least 3 days in advance."))
            .hasPromotion(true)
            .promotionSummary(of("Set menu ưu đãi cho nhóm 10+ khách. Giảm 10% cho đoàn du lịch qua app.",
                                 "Group set menu discounts for 10+ guests. 10% off for tour groups via app."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi không áp dụng dịp lễ Tết cao điểm.", "Promotions not applicable during peak holiday seasons."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Hóa đơn VAT theo yêu cầu trước thanh toán.", "VAT invoices on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% cho tiệc cưới và sự kiện lớn.", "5% service charge for weddings and large events."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không mang thức ăn từ ngoài vào, trừ bánh sinh nhật.", "Outside food not allowed except birthday cakes."))
            .outsideDrinkPolicy(of("Không nhận đồ uống từ ngoài vào.", "Outside drinks are not allowed."))
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
                .id("tb-4-floor1")
                .name(of("Bàn 4 người tầng 1", "Ground floor table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(30)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn tầng 1 không gian truyền thống phố Hội.", "Standard table on ground floor with traditional Hoi An ambiance."))
                .build(),
            TableType.builder()
                .id("tb-10-vip-room")
                .name(of("Phòng VIP 10 người", "VIP room for 10"))
                .seats(10)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(1)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng VIP trang nhã, yên tĩnh, phù hợp gặp gỡ đối tác và kỷ niệm.", "Elegant, quiet VIP room suitable for partner meetings and anniversaries."))
                .build(),
            TableType.builder()
                .id("tb-100-ballroom")
                .name(of("Ballroom 80-100 khách", "Ballroom for 80-100 guests"))
                .seats(100)
                .minPeople(30)
                .maxPeople(100)
                .totalTables(1)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(180)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Ballroom lớn cho tiệc cưới, hội nghị, sự kiện công ty. Máy chiếu, âm thanh, MC, trang trí theo yêu cầu.", "Large ballroom for weddings, conferences, corporate events. Projector, sound, MC, custom decorations available."))
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
            .destinationSlug("hoi-an")
            .cityName(of("Hội An", "Hoi An"))
            .districtName(of("Phường Hội An", "Hoi An Ward"))
            .wardName(of("Phường Hội An", "Hoi An Ward"))
            .addressLine(of("Số 21 La Hối, Phường Hội An, TP. Đà Nẵng",
                            "21 La Hoi Street, Hoi An Ward, Da Nang City"))
            .location(new double[]{108.3237, 15.8796})
            .name(of("Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An", "Ngon Pho Hoi - Authentic Vietnamese Cuisine Hoi An"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng ẩm thực Việt chính hiệu phố cổ Hội An — Cao Lầu, Mỳ Quảng, Cơm Gà, sức chứa 300 khách với Ballroom lớn.",
                                 "Authentic Vietnamese restaurant in Hoi An Ancient Town — Cao Lau, My Quang, Chicken Rice, 300-guest capacity with large Ballroom."))
            .description(of("Ngon Phố Hội — nhà hàng đặc sản Hội An chính hiệu tại 21 La Hối, sức chứa 300 khách, mở cửa 6:30-22:00. " +
                            "Nổi tiếng với Cao Lầu, Mỳ Quảng, Cơm Gà Hội An, Mẹt Đặc Sản và Bánh Xèo giòn rụm. " +
                            "2 phòng chức năng lớn: Ballroom 100 khách và VIP Room 10 khách. Phù hợp đoàn du lịch, tiệc cưới và sự kiện.",
                            "Ngon Pho Hoi — authentic Hoi An specialty restaurant at 21 La Hoi, 300 guests capacity, open 6:30 AM-10 PM. " +
                            "Famous for Cao Lau, My Quang, Hoi An Chicken Rice, Specialty Platter, and crispy Sizzling Pancake. " +
                            "2 large function rooms: Ballroom for 100 and VIP Room for 10. Ideal for tour groups, weddings, and events."))
            .phone("0235 2220021")
            .website("https://pasgo.vn/nha-hang/ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an-5791")
            .bookingHotline("0235 2220021")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("400000"))
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
