// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedTanHaiVan.java
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
public class SeedTanHaiVan {

    @Bean
    CommandLineRunner seedTanHaiVanRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedTanHaiVan] start");

            RestaurantDoc thv = restaurantTanHaiVanPhanVanTri();
            repo.findBySlugAndActiveTrue(thv.getSlug())
                .ifPresent(existing -> thv.setId(existing.getId()));
            repo.save(thv);
            log.info(">>> upsert: {}", thv.getSlug());

            log.info(">>> [SeedTanHaiVan] done");
        };
    }

    // =====================================================================
    //            TÂN HẢI VÂN - PHAN VĂN TRỊ (GÒ VẤP, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantTanHaiVanPhanVanTri() {
        String slug = "tan-hai-van-phan-van-tri";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-1-normal-145815418239.webp",
                of("Không gian Tân Hải Vân - Phan Văn Trị", "Tan Hai Van - Phan Van Tri space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-2-normal-145815518240.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-3-normal-145815618241.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-4-normal-145815718242.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-5-normal-145815818243.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-6-normal-145815918244.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-7-normal-145816018245.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-8-normal-145816718246.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-9-normal-145816218247.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-10-normal-145816318248.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-11-normal-145816418249.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/tan-hai-van-phan-van-tri-anh-slide-12-normal-145816518250.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("CHINESE")
                .name(of("Trung Hoa", "Chinese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN_EUROPEAN")
                .name(of("Gọi món Á - Âu", "Asian - European a la carte"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (06:00 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    6, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   6, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 6, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  6, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    6, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  6, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    6, 0, 22, 0)
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
            suitable("WEDDING_PARTY", of("Tiệc cưới hỏi", "Wedding party"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("LUXURIOUS", of("Sang trọng", "Luxurious")),
            ambience("ELEGANT",   of("Trang nhã", "Elegant")),
            ambience("SPACIOUS",  of("Rộng rãi", "Spacious")),
            ambience("TRADITIONAL", of("Truyền thống Á Đông", "Traditional Asian"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(350)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(25)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Tôm hùm hấp", "Steamed lobster")),
            signatureDishL(of("Cua hoàng đế rang muối", "Salt-roasted king crab")),
            signatureDishL(of("Cá song hấp xì dầu", "Steamed grouper with soy sauce")),
            signatureDishL(of("Bào ngư sốt dầu hào", "Abalone in oyster sauce")),
            signatureDishL(of("Tôm sú nướng phô mai", "Grilled tiger prawn with cheese")),
            signatureDishL(of("Mực một nắng nướng", "Grilled sun-dried squid")),
            signatureDishL(of("Vịt quay Bắc Kinh", "Peking roast duck")),
            signatureDishL(of("Cơm chiên hải sản", "Seafood fried rice")),
            signatureDishL(of("Súp vi cá hải sản", "Seafood shark fin soup")),
            signatureDishL(of("Lẩu hải sản chua cay", "Spicy and sour seafood hotpot"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-12.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-13.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-14.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-15.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-16.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-17.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-18.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-19.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-20.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-21.jpg",
                of("", ""), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-22.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-23.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-24.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-25.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-26.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-27.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-28.jpg",
                of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-29.jpg",
                of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-30.jpg",
                of("", ""), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-31.jpg",
                of("", ""), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-32.jpg",
                of("", ""), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-33.jpg",
                of("", ""), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-34.jpg",
                of("", ""), false, 33),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-35.jpg",
                of("", ""), false, 34),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-36.jpg",
                of("", ""), false, 35),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-37.jpg",
                of("", ""), false, 36),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-38.jpg",
                of("", ""), false, 37),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-39.jpg",
                of("", ""), false, 38),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-40.jpg",
                of("", ""), false, 39),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/chuoitanhaivant9/chuoi-nha-hang-tan-hai-van-41.jpg",
                of("", ""), false, 40)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("SEAFOOD")
                .name(of("Hải sản tươi sống", "Fresh seafood"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Tôm hùm hấp", "Steamed lobster"))
                        .description(of("Tôm hùm tươi sống hấp giữ trọn vị ngọt, chấm muối ớt xanh.", "Live lobster steamed to preserve its natural sweetness, served with green chili salt."))
                        .priceFrom(new BigDecimal("950000"))
                        .priceTo(new BigDecimal("1500000"))
                        .unit(of("kg", "kg"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "lobster", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cua hoàng đế rang muối", "Salt-roasted king crab"))
                        .description(of("Cua hoàng đế chắc thịt rang muối Hồng Kông thơm lừng.", "Meaty king crab roasted with fragrant Hong Kong-style salt."))
                        .priceFrom(new BigDecimal("1200000"))
                        .priceTo(new BigDecimal("2000000"))
                        .unit(of("kg", "kg"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "crab", "signature"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("CHINESE")
                .name(of("Món Trung Hoa", "Chinese dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Vịt quay Bắc Kinh", "Peking roast duck"))
                        .description(of("Vịt quay da giòn vàng óng, cuốn bánh tráng cùng dưa leo và sốt hoisin.", "Roast duck with golden crispy skin, wrapped in pancakes with cucumber and hoisin sauce."))
                        .priceFrom(new BigDecimal("450000"))
                        .priceTo(new BigDecimal("650000"))
                        .unit(of("con", "whole"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("chinese", "duck"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Bào ngư sốt dầu hào", "Abalone in oyster sauce"))
                        .description(of("Bào ngư hầm mềm chan sốt dầu hào đậm đà, món tiến vua Trung Hoa.", "Tender braised abalone in rich oyster sauce, a classic Chinese delicacy."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("550000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("chinese", "abalone", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ ô tô trước nhà hàng", "Car parking in front of the restaurant"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn, miễn phí cho khách.", "Security staff on hand, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "PROJECTOR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "WEDDING_SERVICE",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Tân Hải Vân - Phan Văn Trị", "About Tan Hai Van - Phan Van Tri")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng Tân Hải Vân - Phan Văn Trị là một trong những địa chỉ ẩm thực Trung Hoa và hải sản tươi sống " +
                "nổi tiếng bậc nhất khu vực Gò Vấp, TP. Hồ Chí Minh. Với bề dày kinh nghiệm trong nghề, Tân Hải Vân mang đến " +
                "những món ăn được chế biến công phu theo đúng tinh hoa ẩm thực Hoa, kết hợp cùng nguồn hải sản tươi sống tuyển chọn mỗi ngày.",
                "Tan Hai Van - Phan Van Tri is one of the most renowned destinations for Chinese cuisine and live seafood " +
                "in the Go Vap area of Ho Chi Minh City. With years of experience, Tan Hai Van offers elaborately prepared dishes " +
                "true to the essence of Chinese gastronomy, paired with carefully selected fresh seafood each day.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Tọa lạc tại số 503 Phan Văn Trị, nhà hàng sở hữu không gian rộng rãi, sang trọng với sức chứa lên đến 350 khách. " +
                "Hệ thống phòng riêng (loại 12 khách và 25 khách mỗi phòng) được trang bị đầy đủ, sẵn sàng phục vụ các buổi tiệc gia đình, " +
                "liên hoan công ty hay tiếp khách quan trọng trong không gian riêng tư, lịch sự.",
                "Located at 503 Phan Van Tri, the restaurant features a spacious, luxurious setting with a capacity of up to 350 guests. " +
                "Its private rooms (for 12 and 25 guests each) are fully equipped, ready to serve family parties, " +
                "company gatherings or important business meals in a private, refined atmosphere.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn Tân Hải Vân phong phú với hàng trăm món hải sản tươi sống và đặc sản Trung Hoa: tôm hùm hấp, cua hoàng đế rang muối, " +
                "cá song hấp xì dầu, bào ngư sốt dầu hào, vịt quay Bắc Kinh, súp vi cá... Bên cạnh đó còn có nhiều món gọi món Á - Âu và lẩu hải sản, " +
                "đáp ứng đa dạng khẩu vị của thực khách.",
                "Tan Hai Van's menu is rich with hundreds of live seafood and Chinese specialties: steamed lobster, salt-roasted king crab, " +
                "steamed grouper with soy sauce, abalone in oyster sauce, Peking roast duck, shark fin soup... It also offers many Asian - European " +
                "a la carte dishes and seafood hotpots, catering to a wide range of tastes.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không gian sang trọng và sức chứa lớn, Tân Hải Vân - Phan Văn Trị là lựa chọn lý tưởng cho tiệc cưới hỏi, tiệc công ty, " +
                "liên hoan, sinh nhật, họp mặt gia đình hay những buổi tiếp khách đối tác trang trọng.",
                "With its luxurious space and large capacity, Tan Hai Van - Phan Van Tri is an ideal choice for weddings, company parties, " +
                "celebrations, birthdays, family reunions or formal business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị đầy đủ điều hòa, wifi, phòng riêng, ghế trẻ em, máy chiếu, hệ thống âm thanh ánh sáng cùng dịch vụ MC, " +
                "trang trí và tổ chức tiệc cưới chuyên nghiệp. Có bãi đỗ ô tô và xe máy miễn phí, hỗ trợ thanh toán thẻ và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is fully equipped with air conditioning, wifi, private rooms, kids chairs, a projector, sound & light systems, " +
                "plus professional MC, decoration and wedding planning services. Free car and motorbike parking, card payment and VAT invoices are available on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nằm trên đường Phan Văn Trị, Phường 5, Quận Gò Vấp - một trục đường lớn, dễ tìm và thuận tiện di chuyển từ trung tâm " +
                "TP. Hồ Chí Minh cũng như các quận lân cận như Bình Thạnh, Phú Nhuận.",
                "The restaurant is on Phan Van Tri Street, Ward 5, Go Vap District - a major thoroughfare that is easy to find and convenient to reach " +
                "from central Ho Chi Minh City as well as neighboring districts such as Binh Thanh and Phu Nhuan.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và dịp lễ tết. " +
                "Với nhóm đông, đặt phòng riêng hoặc tiệc cưới, nhà hàng có thể yêu cầu đặt cọc trước theo chính sách.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and holidays. " +
                "For large groups, private room or wedding bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.687500, 10.831500})
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
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 400.000đ/chai.", "Spirits corkage fee: 400,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 10 khách trở lên, đặt phòng riêng hoặc tiệc cưới.", "Applies to bookings of 10 or more guests, private room or wedding reservations."))
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho tiệc và phòng riêng.", "A 5% service charge applies to parties and private rooms."))
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
                .id("tb-6")
                .name(of("Bàn 6", "Table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(20)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-12")
                .name(of("Phòng riêng 12 khách", "Private room for 12 guests"))
                .seats(12)
                .minPeople(8)
                .maxPeople(12)
                .totalTables(2)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Phòng riêng, phù hợp tiếp khách/tiệc gia đình.", "Private room, suitable for business meals and family parties."))
                .build(),

            TableType.builder()
                .id("pr-25")
                .name(of("Phòng riêng 25 khách", "Private room for 25 guests"))
                .seats(25)
                .minPeople(15)
                .maxPeople(25)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng lớn, phù hợp tiệc công ty/liên hoan đông người.", "Large private room, suitable for company parties and large gatherings."))
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
            .districtName(of("Q. Gò Vấp", "Go Vap District"))
            .wardName(of("Phường 5", "Ward 5"))
            .addressLine(of("Số 503 Phan Văn Trị, Phường 5, Quận Gò Vấp, TP. Hồ Chí Minh", "No. 503 Phan Van Tri, Ward 5, Go Vap District, Ho Chi Minh City"))
            .location(new double[]{106.687500, 10.831500})
            .name(of("Tân Hải Vân - Phan Văn Trị", "Tan Hai Van - Phan Van Tri"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Hải sản tươi sống & ẩm thực Trung Hoa, gọi món Á - Âu tại Gò Vấp, TP. Hồ Chí Minh.", "Live seafood & Chinese cuisine, Asian - European a la carte in Go Vap, Ho Chi Minh City."))
            .description(of("Tân Hải Vân - Phan Văn Trị là nhà hàng chuyên hải sản tươi sống và ẩm thực Trung Hoa tại Số 503 Phan Văn Trị, Q. Gò Vấp, TP. Hồ Chí Minh. " +
                "Không gian rộng rãi, sang trọng, sức chứa lên đến 350 khách cùng hệ thống phòng riêng, mang đến những món ăn được chế biến công phu " +
                "theo đúng tinh hoa ẩm thực Hoa, phù hợp cho tiệc cưới, tiệc công ty, liên hoan và tiếp khách.",
                "Tan Hai Van - Phan Van Tri is a restaurant specializing in live seafood and Chinese cuisine at No. 503 Phan Van Tri, Go Vap District, Ho Chi Minh City. " +
                "With a spacious, luxurious setting, a capacity of up to 350 guests and private rooms, it serves elaborately prepared dishes true to the essence " +
                "of Chinese gastronomy, ideal for weddings, company parties, celebrations and business meals."))
            .phone("1900xxxxxx")
            .email("reservation@tanhaivan.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-tan-hai-van-phan-van-tri")
            .facebookPage("https://facebook.com/tanhaivanrestaurant")
            .bookingHotline("1900xxxxxx")
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
