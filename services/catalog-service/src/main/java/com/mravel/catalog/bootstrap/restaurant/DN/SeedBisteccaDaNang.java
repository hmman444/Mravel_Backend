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
public class SeedBisteccaDaNang {

    @Bean
    CommandLineRunner seedBisteccaDaNangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBisteccaDaNang] start");

            RestaurantDoc doc = restaurantBisteccaDaNang();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedBisteccaDaNang] done");
        };
    }

    // =====================================================================
    //        BISTECCA - DA NANG (Tang 7 New Orient Hotel)
    // =====================================================================

    private static RestaurantDoc restaurantBisteccaDaNang() {
        String slug = "bistecca-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-1-normal-2260921057204.webp",
                of("Không gian nhà hàng Bistecca tầng 7 New Orient Đà Nẵng", "Bistecca restaurant interior on the 7th floor of New Orient Da Nang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-2-normal-2260921157205.webp",
                of("Khu vực phòng ăn sang trọng Bistecca", "Elegant dining area at Bistecca"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-3-normal-2260921257206.webp",
                of("Không gian ăn uống châu Âu tại Bistecca Đà Nẵng", "European-style dining space at Bistecca Da Nang"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-4-normal-2260921357207.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-5-normal-2260921457208.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-6-normal-2260921557209.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-7-normal-2260921657210.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-8-normal-2260921757211.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-9-normal-2260921857212.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-11-normal-2260922057214.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-bistecca-12-normal-2260922157215.webp",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-1.jpg",
                of("View sông Hàn từ tầng 7 Bistecca Đà Nẵng", "Han River view from Bistecca Da Nang floor 7"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-2.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-3.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-4.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-5.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-6.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-7.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-8.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-9.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Doitac/BISTECCADANANG/nha-hang-bistecca-da-nang-10.jpg",
                of("", ""), false, 20)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-19.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-20.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-21.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-22.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-23.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-24.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-25.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-26.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-27.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-28.jpg",
                of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-29.jpg",
                of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-30.jpg",
                of("", ""), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-31.jpg",
                of("", ""), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-32.jpg",
                of("", ""), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-33.jpg",
                of("", ""), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-34.jpg",
                of("", ""), false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-35.jpg",
                of("", ""), false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-36.jpg",
                of("", ""), false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-37.jpg",
                of("", ""), false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-38.jpg",
                of("", ""), false, 37),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-39.jpg",
                of("", ""), false, 38),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-40.jpg",
                of("", ""), false, 39),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-41.jpg",
                of("", ""), false, 40),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Phong%20Hi%E1%BA%BFu/Menu%20nh%C3%A0%20h%C3%A0ng%20Bistecca/Menu-nha-hang-bistecca-42.jpg",
                of("", ""), false, 41)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("EUROPEAN")
                .name(of("Ẩm thực Châu Âu", "European cuisine"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("AMERICAN")
                .name(of("Ẩm thực Mỹ", "American cuisine"))
                .region("NORTH_AMERICA")
                .build(),
            CuisineTag.builder()
                .code("STEAKHOUSE")
                .name(of("Steakhouse - Bò nướng Âu", "Steakhouse"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    12, 0, 21, 30),
            openingHour(DayOfWeek.TUESDAY,   12, 0, 21, 30),
            openingHour(DayOfWeek.WEDNESDAY, 12, 0, 21, 30),
            openingHour(DayOfWeek.THURSDAY,  12, 0, 21, 30),
            openingHour(DayOfWeek.FRIDAY,    12, 0, 21, 30),
            openingHour(DayOfWeek.SATURDAY,  12, 0, 21, 30),
            openingHour(DayOfWeek.SUNDAY,    12, 0, 21, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("COUPLE",         of("Hẹn hò lãng mạn với view sông Hàn", "Romantic date with Han River view")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách doanh nghiệp", "Business meal")),
            suitable("FAMILY",         of("Ăn gia đình", "Family dining")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("FRIENDS",        of("Gặp gỡ bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY",  of("Tiệc công ty", "Company party")),
            suitable("GROUP_MEETING",  of("Họp nhóm", "Group meeting")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp", "Corporate event"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ROMANTIC",   of("Lãng mạn view sông Hàn", "Romantic Han River view")),
            ambience("ELEGANT",    of("Sang trọng", "Elegant")),
            ambience("MODERN",     of("Hiện đại", "Modern")),
            ambience("COZY",       of("Ấm cúng", "Cozy"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(40)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Beefsteak bò Mỹ nướng than hoa", "American-style charcoal-grilled beefsteak")),
            signatureDishL(of("Sườn bò nướng sốt BBQ đặc biệt", "Beef ribs with special BBQ sauce")),
            signatureDishL(of("Tôm hùm nướng bơ tỏi", "Grilled lobster with garlic butter")),
            signatureDishL(of("Cá hồi áp chảo sốt demi-glace", "Pan-seared salmon with demi-glace")),
            signatureDishL(of("Gà nướng thảo mộc kiểu Địa Trung Hải", "Mediterranean herb-roasted chicken")),
            signatureDishL(of("Pasta carbonara trứng muối truffle", "Carbonara pasta with salted egg and truffle")),
            signatureDishL(of("Súp kem nấm truffle trắng Ý", "Italian white truffle cream mushroom soup")),
            signatureDishL(of("Khai vị salad Niçoise", "Niçoise salad appetizer")),
            signatureDishL(of("Bánh mì nướng bơ tỏi pho mát Parmesan", "Garlic butter toast with Parmesan cheese")),
            signatureDishL(of("Tráng miệng tiramisu Ý truyền thống", "Traditional Italian tiramisu dessert"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món chính Âu - Mỹ", "European-American main courses"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set menu 2 người - Bistecca Special", "Set menu for 2 - Bistecca Special"))
                        .description(of("Khai vị, soup, món chính beefsteak/hải sản, tráng miệng và thức uống.", "Appetizer, soup, beefsteak/seafood main, dessert and drinks."))
                        .priceFrom(new BigDecimal("500000"))
                        .priceTo(new BigDecimal("900000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "couple", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Beefsteak bò Mỹ 250g", "US beef steak 250g"))
                        .description(of("Bò Mỹ chọn lọc nướng than hoa, ăn kèm khoai tây chiên và salad.", "Premium US beef charcoal-grilled, served with fries and salad."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("380000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("steak", "signature"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi xe ngoài khách sạn New Orient Đà Nẵng", "Parking outside New Orient Hotel Da Nang"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Tầng hầm khách sạn New Orient", "New Orient Hotel basement"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe máy tầng hầm và ô tô trước khách sạn New Orient.", "Free motorbike parking in hotel basement and car parking outside New Orient Hotel."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "CITY_VIEW",
            "RIVER_VIEW",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "ELEVATOR_ACCESS",
            "EVENT_DECOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "HOTEL_RESTAURANT"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Bistecca Đà Nẵng", "About Bistecca Da Nang")),
            paragraph(ContentSection.STORY,
                of("Bistecca là nhà hàng ẩm thực Âu - Mỹ sang trọng, tọa lạc tại tầng 7 của khách sạn cao cấp New Orient Đà Nẵng, " +
                   "số 20 đường Đống Đa, phường Thuận Phước, quận Hải Châu, thành phố Đà Nẵng. " +
                   "Với hơn 5 năm hoạt động, Bistecca đã khẳng định vị thế là một trong những nhà hàng Âu nổi bật nhất " +
                   "tại Đà Nẵng, nơi các món ăn được chế biến theo phong cách Mỹ bởi đội ngũ đầu bếp chuyên nghiệp.",
                   "Bistecca is an elegant European-American restaurant located on the 7th floor of the upscale New Orient Hotel Da Nang, " +
                   "at 20 Dong Da Street, Thuan Phuoc Ward, Hai Chau District, Da Nang. " +
                   "With over 5 years of operation, Bistecca has established itself as one of the most prominent European restaurants " +
                   "in Da Nang, where dishes are prepared in American style by a team of professional chefs.")),
            heading(ContentSection.STORY, of("View panorama tầng 7 - Điểm nhấn độc đáo", "7th-floor panoramic view – a unique highlight")),
            paragraph(ContentSection.STORY,
                of("Nằm ở tầng 7 với không gian mở nhìn ra toàn cảnh thành phố Đà Nẵng, Bistecca mang đến tầm nhìn tuyệt đẹp " +
                   "ra dòng sông Hàn thơ mộng, cảng Tiên Sa lung linh về đêm và những cây cầu đặc trưng như cầu Rồng, cầu Trần Thị Lý. " +
                   "Khu vực ngoài trời với bàn ăn view mở là lựa chọn lý tưởng cho các buổi tối hẹn hò hay kỷ niệm đặc biệt " +
                   "khi thành phố lên đèn, tạo nên bầu không khí siêu lãng mạn và sang trọng.",
                   "Positioned on the 7th floor with an open-air space overlooking all of Da Nang city, Bistecca offers breathtaking views " +
                   "of the poetic Han River, the sparkling Tien Sa Port, and iconic bridges like the Dragon Bridge and Tran Thi Ly Bridge. " +
                   "The outdoor dining area with open views is perfect for romantic evenings or special celebrations " +
                   "as the city lights up, creating a supremely romantic and elegant atmosphere.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng Bistecca sở hữu không gian ăn uống sang trọng với sức chứa lên tới 120 khách, " +
                   "được chia thành 2 khu vực chính: khu vực trong nhà thiết kế phong cách châu Âu với bàn ghế sofa cao cấp, " +
                   "hệ thống đèn chiếu sáng vàng ấm cúng, tranh ảnh trang trí tinh tế; và khu vực ngoài trời thoáng đãng " +
                   "với view hướng ra thành phố và sông Hàn. Ban ngày tận dụng ánh sáng tự nhiên, buổi tối lung linh với ánh đèn thành phố.",
                   "Bistecca restaurant boasts a luxurious dining space with a capacity of up to 120 guests, " +
                   "divided into 2 main areas: the indoor area designed in European style with premium sofa chairs, " +
                   "warm golden lighting, and tasteful artwork; and the open-air outdoor area " +
                   "with views towards the city and Han River. Natural light by day, shimmering city lights by night.")),
            heading(ContentSection.STORY, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Bistecca tập trung vào các món bò nướng Mỹ và ẩm thực Châu Âu với nguyên liệu nhập khẩu chọn lọc. " +
                   "Nổi bật nhất là beefsteak bò Mỹ nướng than hoa, sườn bò sốt BBQ đặc biệt, tôm hùm nướng bơ tỏi " +
                   "và các món pasta kiểu Ý truyền thống. Set menu 2 người là lựa chọn phổ biến cho các cặp đôi " +
                   "với mức giá từ 500.000đ bao gồm khai vị, món chính, tráng miệng và thức uống.",
                   "Bistecca's menu focuses on American-style grilled beef and European cuisine with select imported ingredients. " +
                   "Most notable are the charcoal-grilled US beefsteak, special BBQ beef ribs, garlic butter grilled lobster, " +
                   "and traditional Italian-style pasta. The set menu for 2 is popular among couples " +
                   "starting from 500,000 VND including appetizer, main course, dessert, and drinks.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Bistecca Đà Nẵng là lựa chọn hoàn hảo cho các cặp đôi muốn có buổi tối hẹn hò lãng mạn với view thành phố, " +
                   "các gia đình du lịch muốn thưởng thức bữa ăn Âu đặc sắc, các nhóm bạn bè tụ họp " +
                   "hay đối tác doanh nghiệp muốn có không gian tiếp khách sang trọng. " +
                   "Nhà hàng cũng phù hợp tổ chức tiệc sinh nhật, kỷ niệm và các sự kiện nhỏ trong không gian tầng 7 thoáng đãng.",
                   "Bistecca Da Nang is the perfect choice for couples seeking a romantic evening with city views, " +
                   "traveling families wanting to enjoy distinctive European cuisine, friend gatherings, " +
                   "or business partners needing an elegant entertainment space. " +
                   "The restaurant is also well-suited for birthday parties, anniversaries, and small events in the airy 7th-floor setting.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích nổi bật", "Services & highlights")),
            paragraph(ContentSection.STORY,
                of("Bistecca cung cấp dịch vụ đặt bàn ưu tiên, trang trí bàn theo yêu cầu cho sinh nhật và kỷ niệm. " +
                   "WiFi miễn phí, hệ thống âm thanh nhạc nền nhẹ nhàng tạo không khí thư giãn. " +
                   "Thang máy trực tiếp lên tầng 7 thuận tiện. Chấp nhận thanh toán Visa/Master và xuất hóa đơn VAT. " +
                   "Đặc biệt, bãi đỗ xe hoàn toàn miễn phí cho cả ô tô và xe máy — một điểm cộng hiếm có tại trung tâm Đà Nẵng.",
                   "Bistecca offers priority table booking, customized table decorations for birthdays and anniversaries. " +
                   "Free WiFi and a soft background music system create a relaxing atmosphere. " +
                   "A direct elevator to the 7th floor provides convenience. Visa/Master payments accepted and VAT invoices available. " +
                   "Notably, parking is completely free for both cars and motorbikes — a rare perk in central Da Nang.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Bistecca nằm tại số 20 đường Đống Đa, thuộc quận Hải Châu — trung tâm thương mại của Đà Nẵng. " +
                   "Cách biển Mỹ Khê khoảng 4km, cách cầu Rồng 2km và cách sân bay quốc tế Đà Nẵng khoảng 5km. " +
                   "Du khách có thể di chuyển bằng taxi, xe máy hoặc xe công nghệ (Grab, Be) rất thuận tiện. " +
                   "Tòa nhà khách sạn New Orient nằm gần bờ sông Hàn, dễ nhận biết từ xa.",
                   "Bistecca is located at 20 Dong Da Street, Hai Chau District — Da Nang's commercial center. " +
                   "Approximately 4km from My Khe Beach, 2km from Dragon Bridge, and about 5km from Da Nang International Airport. " +
                   "Guests can travel conveniently by taxi, motorbike, or ride-hailing apps (Grab, Be). " +
                   "The New Orient Hotel building is near the Han River bank, easily recognizable from a distance.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước ít nhất 2 tiếng. Đặt cọc áp dụng cho nhóm từ 10 khách trở lên. " +
                   "Hủy trước 24 giờ được hoàn cọc đầy đủ. Nhà hàng giữ chỗ tối đa 15 phút sau giờ đặt, " +
                   "sau đó chỗ sẽ được nhường cho khách khác. Hóa đơn VAT xuất theo yêu cầu.",
                   "The restaurant accepts reservations at least 2 hours in advance. Deposits apply for groups of 10 or more guests. " +
                   "Cancellations more than 24 hours in advance receive a full refund. The restaurant holds tables for up to 15 minutes " +
                   "after the reserved time, after which the table may be given to walk-in guests. VAT invoices available on request.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Bistecca Đà Nẵng", "Why choose Bistecca Da Nang")),
            paragraph(ContentSection.STORY,
                of("Bistecca là sự kết hợp hoàn hảo giữa ẩm thực Âu - Mỹ đích thực, không gian sang trọng trên cao " +
                   "và tầm nhìn panorama độc đáo ra sông Hàn và toàn cảnh Đà Nẵng. " +
                   "Với giá tầm trung từ 250.000đ/người, đây là lựa chọn đáng giá cho mọi dịp — " +
                   "từ bữa ăn gia đình đến tiệc sinh nhật hay buổi tối hẹn hò đáng nhớ trong chuyến du lịch Đà Nẵng.",
                   "Bistecca is the perfect combination of authentic European-American cuisine, elevated luxury ambiance, " +
                   "and a unique panoramic view of the Han River and all of Da Nang. " +
                   "At a mid-range price from 250,000 VND/person, it is a worthwhile choice for any occasion — " +
                   "from family meals to birthday parties or a memorable evening during a Da Nang trip.")),
            mapBlock(ContentSection.STORY, new double[]{108.221234, 16.082689})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 10 khách trở lên.", "Applies to bookings of 10 or more guests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% cho khách đặt bàn qua hệ thống, không áp dụng ngày lễ.", "10% discount for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi có thể thay đổi theo thời điểm, liên hệ nhà hàng để biết thêm.", "Promotions may vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho nhóm sự kiện và đặt tiệc.", "5% service charge applies to event groups and banquet bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn hoặc đồ uống từ ngoài vào.", "Outside food and drinks are not allowed."))
            .outsideDrinkPolicy(of("Không nhận đồ uống ngoài, nhà hàng có thực đơn đồ uống đa dạng.", "Outside drinks not accepted; restaurant has a full beverage menu."))
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
                .id("tb-2-standard")
                .name(of("Bàn đôi trong nhà", "Indoor table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi khu trong nhà, phù hợp cặp đôi và ăn trưa văn phòng.", "Indoor table for 2, suitable for couples and office lunch."))
                .build(),
            TableType.builder()
                .id("tb-4-standard")
                .name(of("Bàn 4 khách", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn 4 khách tiêu chuẩn, trong nhà hoặc ngoài trời.", "Standard table for 4, indoor or outdoor."))
                .build(),
            TableType.builder()
                .id("tb-8-outdoor-view")
                .name(of("Bàn ngoài trời view sông Hàn", "Outdoor table with Han River view"))
                .seats(8)
                .minPeople(4)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn ngoài trời tầng 7 với view panorama sông Hàn và thành phố, rất thơ mộng vào buổi tối.", "7th-floor outdoor table with panoramic Han River and city view, especially romantic at night."))
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
            .districtName(of("Quận Hải Châu", "Hai Chau District"))
            .wardName(of("P. Thuận Phước", "Thuan Phuoc Ward"))
            .addressLine(of("Tầng 7, Khách Sạn New Orient, Số 20 Đường Đống Đa, P. Thuận Phước, Q. Hải Châu, TP. Đà Nẵng",
                           "7th Floor, New Orient Hotel, 20 Dong Da Street, Thuan Phuoc Ward, Hai Chau District, Da Nang"))
            .location(new double[]{108.221234, 16.082689})
            .name(of("Bistecca Đà Nẵng", "Bistecca Da Nang"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng Âu-Mỹ sang trọng tầng 7 New Orient Đà Nẵng với view panorama sông Hàn tuyệt đẹp.",
                                 "Elegant European-American restaurant on the 7th floor of New Orient Hotel Da Nang with stunning Han River panoramic view."))
            .description(of("Bistecca Đà Nẵng — nhà hàng ẩm thực Âu-Mỹ hơn 5 năm khẳng định vị thế tại tầng 7 khách sạn New Orient, " +
                            "số 20 Đống Đa, quận Hải Châu. Nổi bật với view panorama sông Hàn, cầu Rồng và toàn cảnh thành phố, " +
                            "cùng thực đơn beefsteak bò Mỹ, hải sản và pasta Ý đích thực. Bãi đỗ xe miễn phí, sức chứa 120 khách.",
                            "Bistecca Da Nang — European-American restaurant with over 5 years on the 7th floor of New Orient Hotel, " +
                            "20 Dong Da Street, Hai Chau District. Notable for panoramic Han River, Dragon Bridge, and city views, " +
                            "with a menu of US beefsteak, seafood, and authentic Italian pasta. Free parking, capacity 120 guests."))
            .phone("0901234567")
            .website("https://pasgo.vn/nha-hang/bistecca-da-nang-5788")
            .bookingHotline("0901234567")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("500000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.EXPENSIVE)
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
