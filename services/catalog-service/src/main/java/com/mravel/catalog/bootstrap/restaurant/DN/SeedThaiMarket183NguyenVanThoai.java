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
public class SeedThaiMarket183NguyenVanThoai {

    @Bean
    CommandLineRunner seedThaiMarket183NguyenVanThoaiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedThaiMarket183NguyenVanThoai] start");
            RestaurantDoc doc = restaurantThaiMarket183NguyenVanThoai();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedThaiMarket183NguyenVanThoai] done");
        };
    }

    // =====================================================================
    //      THAI MARKET RESTAURANT - 183 NGUYỄN VĂN THOẠI (ĐÀ NẴNG)
    // =====================================================================

    private static RestaurantDoc restaurantThaiMarket183NguyenVanThoai() {
        String slug = "thai-market-183-nguyen-van-thoai-da-nang";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-7-normal-2384877266938.webp",
                of("Không gian Thai Market 183 Nguyễn Văn Thoại", "Thai Market 183 Nguyen Van Thoai interior"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-1-normal-2384879366953.webp",
                of("Khu vực bàn ăn chính tầng trệt", "Main dining area ground floor"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-2-normal-2384879466954.webp",
                of("Trang trí nội thất phong cách Thái Lan", "Thai-style interior decoration"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-3-normal-2384879566955.webp",
                of("Bàn ăn với ánh đèn ấm áp", "Dining table with warm lighting"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-4-normal-2384879666956.webp",
                of("Khu vực lounge và bar nước Thái", "Lounge and Thai beverage bar area"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-5-normal-2384879766957.webp",
                of("Không gian chụp ảnh nghệ thuật Thái Lan", "Thai art photo-worthy space"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-6-normal-2384877366939.webp",
                of("Bàn tiệc VIP riêng tư", "Private VIP banquet table"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-8-normal-2384877466940.webp",
                of("Tom Yum Kung tươi ngon chuẩn vị Thái", "Fresh Tom Yum Kung authentic Thai flavor"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-9-normal-2384877566941.webp",
                of("Set menu Thái Lan đặc biệt", "Special Thai set menu"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-10-normal-2384877666942.webp",
                of("Cà ri đỏ Thái với tôm hùm", "Thai red curry with lobster"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-11-normal-2384877766943.webp",
                of("Không gian chiều tối lãng mạn", "Romantic late afternoon ambiance"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-12-normal-2384877866944.webp",
                of("Khu vực tầng 2 rộng rãi", "Spacious second floor area"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-13-normal-2384877966945.webp",
                of("Mặt tiền nhà hàng Thai Market 183", "Thai Market 183 restaurant facade"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-14-normal-2384878066946.webp",
                of("Cocktail và đồ uống nhiệt đới Thái Lan", "Thai tropical cocktails and beverages"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/thai-market--nguyen-van-thoai-15-normal-2384878166947.webp",
                of("Phòng riêng sang trọng có máy chiếu", "Luxurious private room with projector"), false, 14)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-1.jpg",
                of("Menu Thai Market 183 trang 1", "Thai Market 183 menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-2.jpg",
                of("Menu Thai Market 183 trang 2", "Thai Market 183 menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-3.jpg",
                of("Menu Thai Market 183 trang 3", "Thai Market 183 menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-4.jpg",
                of("Menu Thai Market 183 trang 4", "Thai Market 183 menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-5.jpg",
                of("Menu Thai Market 183 trang 5", "Thai Market 183 menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-6.jpg",
                of("Menu Thai Market 183 trang 6", "Thai Market 183 menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-7.jpg",
                of("Menu Thai Market 183 trang 7", "Thai Market 183 menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-8.jpg",
                of("Menu Thai Market 183 trang 8", "Thai Market 183 menu page 8"), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-9.jpg",
                of("Menu Thai Market 183 trang 9", "Thai Market 183 menu page 9"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-10.jpg",
                of("Menu Thai Market 183 trang 10", "Thai Market 183 menu page 10"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-11.jpg",
                of("Menu Thai Market 183 trang 11", "Thai Market 183 menu page 11"), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-12.jpg",
                of("Menu Thai Market 183 trang 12", "Thai Market 183 menu page 12"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-13.jpg",
                of("Menu Thai Market 183 trang 13", "Thai Market 183 menu page 13"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-14.jpg",
                of("Menu Thai Market 183 trang 14", "Thai Market 183 menu page 14"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-15.jpg",
                of("Menu Thai Market 183 trang 15", "Thai Market 183 menu page 15"), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-16.jpg",
                of("Menu Thai Market 183 trang 16", "Thai Market 183 menu page 16"), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-17.jpg",
                of("Menu Thai Market 183 trang 17", "Thai Market 183 menu page 17"), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-18.jpg",
                of("Menu Thai Market 183 trang 18", "Thai Market 183 menu page 18"), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-19.jpg",
                of("Menu Thai Market 183 trang 19", "Thai Market 183 menu page 19"), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-20.jpg",
                of("Menu Thai Market 183 trang 20 — Cocktail Thái", "Thai Market 183 menu page 20 — Thai cocktails"), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-21.jpg",
                of("Menu Thai Market 183 trang 21", "Thai Market 183 menu page 21"), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-22.jpg",
                of("Menu Thai Market 183 trang 22", "Thai Market 183 menu page 22"), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-23.jpg",
                of("Menu Thai Market 183 trang 23", "Thai Market 183 menu page 23"), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-24.jpg",
                of("Menu Thai Market 183 trang 24", "Thai Market 183 menu page 24"), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-25.jpg",
                of("Menu Thai Market 183 trang 25", "Thai Market 183 menu page 25"), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-26.jpg",
                of("Menu Thai Market 183 trang 26", "Thai Market 183 menu page 26"), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-27.jpg",
                of("Menu Thai Market 183 trang 27", "Thai Market 183 menu page 27"), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-28.jpg",
                of("Menu Thai Market 183 trang 28", "Thai Market 183 menu page 28"), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-29.jpg",
                of("Menu Thai Market 183 trang 29", "Thai Market 183 menu page 29"), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-30.jpg",
                of("Menu Thai Market 183 trang 30 — Đồ uống", "Thai Market 183 menu page 30 — Beverages"), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-31.jpg",
                of("Menu Thai Market 183 trang 31", "Thai Market 183 menu page 31"), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-32.jpg",
                of("Menu Thai Market 183 trang 32", "Thai Market 183 menu page 32"), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-33.jpg",
                of("Menu Thai Market 183 trang 33", "Thai Market 183 menu page 33"), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-34.jpg",
                of("Menu Thai Market 183 trang 34", "Thai Market 183 menu page 34"), false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-35.jpg",
                of("Menu Thai Market 183 trang 35", "Thai Market 183 menu page 35"), false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-36.jpg",
                of("Menu Thai Market 183 trang 36", "Thai Market 183 menu page 36"), false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-37.jpg",
                of("Menu Thai Market 183 trang 37", "Thai Market 183 menu page 37"), false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-38.jpg",
                of("Menu Thai Market 183 trang 38", "Thai Market 183 menu page 38"), false, 37),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-39.jpg",
                of("Menu Thai Market 183 trang 39", "Thai Market 183 menu page 39"), false, 38),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-40.jpg",
                of("Menu Thai Market 183 trang 40", "Thai Market 183 menu page 40"), false, 39),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-41.jpg",
                of("Menu Thai Market 183 trang 41", "Thai Market 183 menu page 41"), false, 40),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-42.jpg",
                of("Menu Thai Market 183 trang 42", "Thai Market 183 menu page 42"), false, 41),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-43.jpg",
                of("Menu Thai Market 183 trang 43", "Thai Market 183 menu page 43"), false, 42),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-44.jpg",
                of("Menu Thai Market 183 trang 44", "Thai Market 183 menu page 44"), false, 43),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-45.jpg",
                of("Menu Thai Market 183 trang 45", "Thai Market 183 menu page 45"), false, 44),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-46.jpg",
                of("Menu Thai Market 183 trang 46", "Thai Market 183 menu page 46"), false, 45),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-47.jpg",
                of("Menu Thai Market 183 trang 47", "Thai Market 183 menu page 47"), false, 46),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-48.jpg",
                of("Menu Thai Market 183 trang 48", "Thai Market 183 menu page 48"), false, 47),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-49.jpg",
                of("Menu Thai Market 183 trang 49", "Thai Market 183 menu page 49"), false, 48),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-50.jpg",
                of("Menu Thai Market 183 trang 50", "Thai Market 183 menu page 50"), false, 49),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-51.jpg",
                of("Menu Thai Market 183 trang 51", "Thai Market 183 menu page 51"), false, 50),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/hongson/TMK-1/nha-hang-thaimarket-52.jpg",
                of("Menu Thai Market 183 trang 52 — Tráng miệng", "Thai Market 183 menu page 52 — Desserts"), false, 51)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("THAI")
                .name(of("Thái Lan", "Thai"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực châu Á kết hợp", "Asian fusion"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
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
            suitable("COUPLE",        of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday celebration")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("COMPANY_PARTY", of("Liên hoan nội bộ", "Internal company party")),
            suitable("GROUP_MEETING", of("Gặp mặt nhóm", "Group meeting")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_EVENT", of("Hội nghị công ty", "Company conference"))
        );

        // ---------- Không gian ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động, năng lượng chợ Thái đêm", "Vibrant Thai night market energy")),
            ambience("ELEGANT",  of("Thanh lịch, tinh tế", "Elegant and refined")),
            ambience("ROMANTIC", of("Lãng mạn, phù hợp hẹn hò", "Romantic, great for dating")),
            ambience("MODERN",   of("Hiện đại, thiết kế đương đại", "Modern contemporary design")),
            ambience("COZY",     of("Ấm cúng, dễ chịu", "Cozy and comfortable"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(180)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Tom Yum Kung hải sản tươi", "Fresh Seafood Tom Yum Kung"),
                of("Lẩu Tom Yum kết hợp tôm sú, mực, ngao và nấm rơm trong nước dùng chua cay sả galangal — " +
                   "phiên bản đặc biệt với hải sản tươi của Đà Nẵng giúp tăng thêm vị ngọt đậm đà.",
                   "Tom Yum hotpot combining tiger prawns, squid, clams and straw mushrooms in a sour-spicy " +
                   "lemongrass-galangal broth — a special version with Da Nang's fresh seafood for extra " +
                   "sweet richness."),
                new BigDecimal("280000")),
            signatureDish(of("Set menu Hoàng Gia Thái 4 món", "Thai Royal 4-Course Set Menu"),
                of("Set menu 4 món đặc sắc gồm: Som Tum gỏi đu đủ; Tom Yum súp chua cay; Pad Thai xào tôm; " +
                   "và Mango Sticky Rice tráng miệng. Ăn kèm cơm trắng — thưởng thức đầy đủ tinh hoa Thái.",
                   "4-course signature set menu: Som Tum green papaya salad; Tom Yum sour-spicy soup; Pad Thai " +
                   "stir-fried shrimp noodles; and Mango Sticky Rice dessert. With steamed rice — a complete " +
                   "Thai culinary journey."),
                new BigDecimal("320000")),
            signatureDish(of("Cua rang muối kiểu Thái", "Thai Salt-Roasted Crab"),
                of("Cua biển tươi rang cùng muối, tỏi và ớt theo kiểu Thái Lan — vỏ giòn, thịt ngọt chắc và " +
                   "hương thơm đặc trưng của tỏi rang vàng. Món hải sản cao cấp được thực khách yêu thích nhất.",
                   "Fresh sea crab roasted with salt, garlic and chili Thai-style — crispy shell, firm sweet " +
                   "meat and fragrant toasted garlic aroma. The most popular premium seafood dish."),
                new BigDecimal("380000")),
            signatureDish(of("Phad Krapow Moo thịt heo xào húng quế", "Phad Krapow Moo — Holy Basil Stir-fried Pork"),
                of("Thịt heo bằm xào cùng tỏi, ớt chim Thái và lá húng quế thánh tươi ngát trong nước tương " +
                   "oyster đậm vị — món cơm bình dân nhưng cực kỳ đậm đà và phổ biến nhất tại Thái Lan.",
                   "Minced pork stir-fried with garlic, Thai bird chili and fresh holy basil in rich oyster " +
                   "sauce — a simple rice dish but extremely flavorful and the most popular in Thailand."),
                new BigDecimal("130000")),
            signatureDish(of("Tom Kha Gai súp gà nước cốt dừa", "Tom Kha Gai Coconut Milk Chicken Soup"),
                of("Súp gà nước cốt dừa béo ngậy với galangal, sả, lá kaffir lime và nấm rơm — chua nhẹ, " +
                   "thơm dừa và gà mềm tan. Phiên bản Tom Kha dịu hơn Tom Yum, phù hợp không ăn cay.",
                   "Rich coconut milk chicken soup with galangal, lemongrass, kaffir lime leaves and straw " +
                   "mushrooms — mildly sour, coconut-fragrant and tender chicken. Tom Kha version milder than " +
                   "Tom Yum, suitable for those who do not eat spicy."),
                new BigDecimal("155000")),
            signatureDish(of("Cà ri đỏ tôm hùm nước cốt dừa", "Red Curry Lobster in Coconut Milk"),
                of("Tôm hùm tươi nấu trong cà ri đỏ Thái với nước cốt dừa béo ngậy, thêm cà tím Thái và lá " +
                   "húng quế — món cao cấp nhất trong thực đơn, thường được gọi cho tiệc đặc biệt.",
                   "Fresh lobster cooked in Thai red curry with rich coconut milk, Thai eggplant and basil — " +
                   "the most premium dish on the menu, typically ordered for special celebrations."),
                new BigDecimal("550000")),
            signatureDish(of("Pad See Ew mì bề rộng xào nước tương", "Pad See Ew — Soy Sauce Stir-fried Wide Noodles"),
                of("Sợi mì gạo rộng xào cùng bông cải xanh, trứng và thịt bò trong nước tương đặc và oyster " +
                   "sauce — mì ngấm đều gia vị, hơi khói bếp đặc trưng và ngon đơn giản.",
                   "Wide rice noodles stir-fried with broccoli, egg and beef in dark soy sauce and oyster " +
                   "sauce — noodles evenly flavored, with characteristic wok hei smokiness, simply delicious."),
                new BigDecimal("135000")),
            signatureDish(of("Gỏi bơ bò nướng kiểu Thái", "Thai-style Grilled Beef and Avocado Salad"),
                of("Thịt bò Mỹ nướng rare thái mỏng trộn cùng bơ, ớt, tỏi, nước cốt chanh và lá húng quế " +
                   "Thái — sáng tạo fusion giữa nguyên liệu châu Âu và gia vị Thái Lan, tươi mát và đậm vị.",
                   "US beef grilled rare and thinly sliced mixed with avocado, chili, garlic, lime juice and " +
                   "Thai basil — a creative fusion of European ingredients and Thai spices, fresh and flavorful."),
                new BigDecimal("220000")),
            signatureDish(of("Khanom Krok bánh nhân dừa kiểu Thái", "Thai Coconut Milk Khanom Krok Pancakes"),
                of("Bánh tròn nhỏ nướng trong khuôn sắt truyền thống từ bột gạo và nước cốt dừa — vỏ ngoài " +
                   "giòn vàng, nhân bên trong dẻo thơm nước dừa. Tráng miệng đường phố Thái đặc sắc.",
                   "Small round cakes baked in traditional iron molds from rice flour and coconut milk — " +
                   "crispy golden exterior, chewy coconut-fragrant interior. Distinctive Thai street dessert."),
                new BigDecimal("60000")),
            signatureDish(of("Thai Iced Tea trà Thái đóng băng", "Thai Iced Tea"),
                of("Trà Thái pha đặc màu cam rực rỡ, ngọt thơm với sữa đặc và kem tươi — đồ uống biểu tượng " +
                   "của Thái Lan, mát lạnh và ngọt ngào làm dịu mọi cay nóng.",
                   "Strong orange-hued Thai tea, sweetly fragrant with condensed milk and fresh cream — " +
                   "Thailand's iconic beverage, cold and sweet to soothe any heat and spice."),
                new BigDecimal("55000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SET_MENU_THAI")
                .name(of("Set menu & tiệc", "Set menus & banquets"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set Hoàng Gia 4 món (2 người)", "Royal 4-course set (2 pax)"))
                        .description(of("Som Tum + Tom Yum + Pad Thai + Mango Sticky Rice.",
                                       "Som Tum + Tom Yum + Pad Thai + Mango Sticky Rice."))
                        .priceFrom(new BigDecimal("320000"))
                        .priceTo(new BigDecimal("380000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "Thai", "combo"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set tiệc nhóm 4-6 người", "Group banquet set 4-6 pax"))
                        .description(of("7 món Thái đặc sắc kèm cơm và tráng miệng, phục vụ nhóm.",
                                       "7 signature Thai dishes with rice and dessert, group serving."))
                        .priceFrom(new BigDecimal("800000"))
                        .priceTo(new BigDecimal("1200000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "Thai", "group"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("HAI_SAN_THAI")
                .name(of("Hải sản Thái", "Thai Seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tom Yum hải sản tươi", "Fresh seafood Tom Yum"))
                        .description(of("Tôm sú, mực, ngao trong lẩu Tom Yum chua cay đặc biệt.",
                                       "Tiger prawns, squid, clams in special sour-spicy Tom Yum hotpot."))
                        .priceFrom(new BigDecimal("280000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood", "Thai"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cua rang muối Thái", "Thai salt-roasted crab"))
                        .description(of("Cua biển tươi rang muối tỏi ớt Thái, vỏ giòn thịt ngọt.",
                                       "Fresh sea crab roasted with Thai salt, garlic and chili, crispy and sweet."))
                        .priceFrom(new BigDecimal("380000"))
                        .priceTo(new BigDecimal("500000"))
                        .unit(of("con", "piece"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("crab", "seafood", "Thai"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ ô tô bên cạnh tòa nhà, sức chứa hạn chế",
                                   "Car parking lot beside the building, limited capacity"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy trước cửa nhà hàng, rộng rãi", "Spacious motorbike parking in front of restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Nhà hàng nằm tại 183 Nguyễn Văn Thoại, Q. Sơn Trà; có bãi đỗ xe miễn phí.",
                      "Restaurant at 183 Nguyen Van Thoai, Son Tra District; free parking available."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "PROJECTOR",
            "SOUND_LIGHT",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE_SERVICE",
            "MC_SERVICE",
            "SET_LUNCH",
            "KIDS_CHAIR",
            "TAKEAWAY"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Thai Market 183 Nguyễn Văn Thoại — Hương vị Thái Lan đích thực tại Sơn Trà",
                   "Thai Market 183 Nguyen Van Thoai — Authentic Thai Flavors in Son Tra")),
            paragraph(ContentSection.STORY,
                of("Thai Market Restaurant 183 Nguyễn Văn Thoại tọa lạc tại số 183 Nguyễn Văn Thoại, Phường " +
                   "Hải Đông, Quận Sơn Trà, TP. Đà Nẵng — là một trong những chi nhánh Thai Market lâu đời và " +
                   "được yêu thích nhất tại Đà Nẵng. Mở cửa từ nhiều năm, nhà hàng đã trở thành điểm đến quen " +
                   "thuộc của người Đà Nẵng yêu ẩm thực Thái Lan và du khách quốc tế muốn tìm hương vị Thái " +
                   "chuẩn vị ngay tại Đà Nẵng.",
                   "Thai Market Restaurant at 183 Nguyen Van Thoai, Hai Dong Ward, Son Tra District, Da Nang " +
                   "City — one of the oldest and most beloved Thai Market branches in Da Nang. Open for many " +
                   "years, the restaurant has become a familiar destination for Da Nang Thai food lovers and " +
                   "international tourists seeking authentic Thai flavors right in Da Nang.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa",
                   "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng trải dài trên nhiều tầng với sức chứa khoảng 180 khách — khu vực ăn chính rộng " +
                   "rãi, thoáng đãng và 3 phòng riêng tối đa 25 khách/phòng được trang bị máy chiếu và âm " +
                   "thanh ánh sáng. Thiết kế kết hợp phong cách chợ đêm Thái Lan với nội thất hiện đại — màu " +
                   "sắc tươi sáng, đèn trang trí ấm áp và những biểu tượng văn hóa Thái Lan đặc trưng khắp " +
                   "không gian tạo nên bầu không khí vừa sôi động vừa lãng mạn.",
                   "The restaurant spans multiple floors with capacity of approximately 180 guests — spacious " +
                   "main dining area and 3 private rooms each holding up to 25 guests, equipped with projectors " +
                   "and sound-light systems. Design blends Thai night market style with modern interiors — " +
                   "bright colors, warm decorative lighting and distinctive Thai cultural symbols throughout " +
                   "create an atmosphere that is both vibrant and romantic.")),
            heading(ContentSection.STORY,
                of("Thực đơn phong phú hơn 100 món Thái",
                   "Rich menu with over 100 Thai dishes")),
            paragraph(ContentSection.STORY,
                of("Thai Market 183 tự hào với thực đơn hơn 100 món bao phủ toàn bộ phổ ẩm thực Thái: từ các " +
                   "món soup Tom Yum Kung và Tom Kha Gai kinh điển; các loại cà ri xanh/đỏ/vàng/massaman đậm " +
                   "đà; hải sản cua rang muối và tôm hùm cà ri đỏ cao cấp; mì Pad Thai và Pad See Ew xào " +
                   "nước tương; đến các món ăn đường phố Som Tum, Phad Krapow và bánh Khanom Krok truyền thống. " +
                   "Giá từ 250.000 – 350.000 đồng/người, set tiệc nhóm từ 800.000 đồng/nhóm 4-6 người.",
                   "Thai Market 183 is proud of its menu of over 100 dishes covering the full spectrum of Thai " +
                   "cuisine: classic soups Tom Yum Kung and Tom Kha Gai; various green/red/yellow/massaman " +
                   "rich curries; premium seafood salt-roasted crab and red curry lobster; Pad Thai and Pad " +
                   "See Ew soy sauce stir-fried noodles; to street food Som Tum, Phad Krapow and traditional " +
                   "Khanom Krok cakes. Price 250,000 – 350,000 VND per person, group set from 800,000 VND " +
                   "for groups of 4-6.")),
            heading(ContentSection.STORY,
                of("Hải sản tươi từ biển Đà Nẵng",
                   "Fresh seafood from Da Nang waters")),
            paragraph(ContentSection.STORY,
                of("Điểm nổi bật của Thai Market 183 so với các chi nhánh khác là sự kết hợp hoàn hảo giữa " +
                   "gia vị Thái nhập khẩu chính hãng và hải sản tươi sống từ biển Đà Nẵng. Tôm sú, cua biển, " +
                   "mực ống, bạch tuộc và ngao tươi được nhập hàng ngày từ chợ cá Mỹ Khê, đảm bảo tươi ngon " +
                   "tuyệt đối. Sự kết hợp này tạo nên những món Tom Yum và cà ri hải sản đặc biệt mà không " +
                   "tìm thấy ở nơi khác.",
                   "What distinguishes Thai Market 183 from other branches is the perfect combination of " +
                   "genuine imported Thai spices and fresh local seafood from Da Nang waters. Tiger prawns, " +
                   "sea crab, squid, octopus and fresh clams are sourced daily from My Khe fish market, " +
                   "ensuring absolute freshness. This combination creates special Tom Yum and seafood curries " +
                   "not found elsewhere.")),
            heading(ContentSection.STORY,
                of("Dịch vụ tiệc & sự kiện",
                   "Banquet & event services")),
            paragraph(ContentSection.STORY,
                of("Thai Market 183 là lựa chọn hàng đầu cho các sự kiện công ty, tiệc sinh nhật và kỷ niệm " +
                   "nhờ 3 phòng riêng được trang bị máy chiếu, âm thanh ánh sáng và dịch vụ MC chuyên nghiệp. " +
                   "Có thể trang trí phòng theo chủ đề riêng. Set tiệc linh hoạt từ 4 đến 40 khách với thực " +
                   "đơn cố định hoặc à la carte. Đặt tiệc trước tối thiểu 1 ngày.",
                   "Thai Market 183 is the top choice for company events, birthday parties and anniversaries " +
                   "thanks to 3 private rooms equipped with projectors, sound-light systems and professional " +
                   "MC service. Rooms can be decorated with custom themes. Flexible banquet sets from 4 to " +
                   "40 guests with fixed menu or à la carte. Minimum 1 day advance booking for events.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển",
                   "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tại 183 Nguyễn Văn Thoại, Phường Hải Đông, Quận Sơn Trà — cách biển Mỹ Khê " +
                   "khoảng 500m và cách cầu Rồng 2,5 km. Thuận tiện cho khách ở khu vực Sơn Trà và các khách " +
                   "sạn ven biển. Có bãi đỗ xe ô tô và xe máy miễn phí. Taxi và Grab dễ dàng đặt về từ đây.",
                   "Restaurant at 183 Nguyen Van Thoai, Hai Dong Ward, Son Tra District — about 500m from My " +
                   "Khe Beach and 2.5 km from Dragon Bridge. Convenient for guests in Son Tra area and " +
                   "beachfront hotels. Free car and motorbike parking available. Taxi and Grab easily booked " +
                   "from here.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn Thai Market 183",
                   "Booking policy & why choose Thai Market 183")),
            paragraph(ContentSection.STORY,
                of("Thai Market 183 nhận đặt bàn trực tuyến qua PasGo và điện thoại, mở cửa từ 10:30 đến 22:00 " +
                   "mỗi ngày. Set lunch đặc biệt từ thứ Hai đến thứ Sáu với giá ưu đãi. Không thu phí phục vụ. " +
                   "Với thực đơn phong phú hơn 100 món, hải sản tươi từ biển Đà Nẵng, không gian đẹp và dịch " +
                   "vụ sự kiện chuyên nghiệp — đây là lựa chọn hàng đầu cho những ai muốn trải nghiệm ẩm thực " +
                   "Thái Lan đích thực tại Đà Nẵng.",
                   "Thai Market 183 accepts online reservations via PasGo and by phone, open daily 10:30 – " +
                   "22:00. Special set lunch Monday to Friday at discounted rates. No service charge. With " +
                   "over 100 dishes, fresh Da Nang seafood, beautiful space and professional event services " +
                   "— this is the top choice for those wanting authentic Thai culinary experiences in Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.2426075, 16.0553269})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Không áp dụng ưu đãi ngày 1/1",  "Promotions not applicable on Jan 1")),
            blackoutGregorian(2,  14, of("Không áp dụng ưu đãi ngày 14/2", "Promotions not applicable on Feb 14")),
            blackoutGregorian(4,  30, of("Không áp dụng ưu đãi ngày 30/4", "Promotions not applicable on Apr 30")),
            blackoutGregorian(9,  2,  of("Không áp dụng ưu đãi ngày 2/9",  "Promotions not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm giao thừa", "Promotions not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(null)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho bàn thường; tiệc sự kiện đặc biệt cần đặt cọc trước.",
                             "No deposit for regular tables; special event bookings require advance deposit."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi đặt bàn qua PasGo; set lunch thứ 2–6.",
                                 "Promotions via PasGo; set lunch Mon–Fri."))
            .promotionMaxDiscountPercent(15)
            .promotionNote(of("Chi tiết ưu đãi theo từng thời điểm và chương trình.", "Promotion details vary by period and program."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(1)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice issued on request."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không thu phí phục vụ.", "No service charge."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không cho mang thức ăn từ ngoài vào (trừ bánh sinh nhật).",
                                  "Outside food not permitted (except birthday cakes)."))
            .outsideDrinkPolicy(of("Không cho mang đồ uống từ ngoài vào.", "Outside drinks not permitted."))
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
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Loại bàn ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-2-std")
                .name(of("Bàn đôi 2 ghế", "Table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(15)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi lý tưởng cho hẹn hò và bạn bè.", "Ideal table for two for dates and friends."))
                .build(),
            TableType.builder()
                .id("tb-6-vip")
                .name(of("Bàn VIP 6 ghế", "VIP table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Bàn VIP khu vực ưu tiên, tầng 2.", "Priority VIP table on second floor."))
                .build(),
            TableType.builder()
                .id("pr-25")
                .name(of("Phòng riêng 25 khách", "Private room for 25 guests"))
                .seats(25)
                .minPeople(10)
                .maxPeople(25)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng trang bị máy chiếu và âm thanh ánh sáng, phù hợp tiệc và sự kiện.",
                         "Private room with projector and sound-light, suitable for parties and events."))
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
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Sơn Trà", "Son Tra District"))
            .wardName(of("P. Hải Đông", "Hai Dong Ward"))
            .addressLine(of("183 Nguyễn Văn Thoại, P. Hải Đông, Q. Sơn Trà, TP. Đà Nẵng",
                            "183 Nguyen Van Thoai, Hai Dong Ward, Son Tra District, Da Nang City"))
            .location(new double[]{108.2426075, 16.0553269})
            .name(of("Thai Market Restaurant - 183 Nguyễn Văn Thoại", "Thai Market Restaurant - 183 Nguyen Van Thoai"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Ẩm thực Thái Lan chính thống tại Sơn Trà — hơn 100 món với hải sản tươi biển Đà Nẵng.",
                                 "Authentic Thai cuisine in Son Tra — over 100 dishes with fresh Da Nang seafood."))
            .description(of("Thai Market Restaurant tại 183 Nguyễn Văn Thoại, Q. Sơn Trà, TP. Đà Nẵng phục vụ hơn 100 món Thái " +
                            "chuẩn vị kết hợp hải sản tươi Đà Nẵng. Sức chứa 180 khách, 3 phòng riêng có máy chiếu. " +
                            "Mở cửa 10:30 – 22:00 hàng ngày. Giá 250.000 – 350.000 đồng/người.",
                            "Thai Market Restaurant at 183 Nguyen Van Thoai, Son Tra District, Da Nang serves over 100 authentic " +
                            "Thai dishes combined with fresh local seafood. Capacity 180 guests with 3 private rooms with projectors. " +
                            "Open daily 10:30 – 22:00. Price 250,000 – 350,000 VND per person."))
            .phone("1900 6006")
            .bookingHotline("1900 6006")
            .website("https://pasgo.vn/nha-hang/thai-market-restaurant-183-nguyen-van-thoai")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("350000"))
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
