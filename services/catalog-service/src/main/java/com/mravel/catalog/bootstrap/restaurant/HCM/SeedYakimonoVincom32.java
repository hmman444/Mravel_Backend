// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedYakimonoVincom32.java
package com.mravel.catalog.bootstrap.restaurant.HCM;

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
public class SeedYakimonoVincom32 {

    @Bean
    CommandLineRunner seedYakimonoVincom32Runner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedYakimonoVincom32] start");

            RestaurantDoc yakimono = restaurantYakimonoVincom32();
            repo.findBySlugAndActiveTrue(yakimono.getSlug())
                .ifPresent(existing -> yakimono.setId(existing.getId()));
            repo.save(yakimono);
            log.info(">>> upsert: {}", yakimono.getSlug());

            log.info(">>> [SeedYakimonoVincom32] done");
        };
    }

    // =====================================================================
    //            YAKIMONO - VINCOM PLAZA 3/2 (QUẬN 10, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantYakimonoVincom32() {
        String slug = "yakimono-vincom-plaza-3-2";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-1-normal-4215987172848.webp",
                of("Không gian Yakimono - Vincom Plaza 3/2", "Yakimono - Vincom Plaza 3/2 space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-2-normal-4215987372849.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-3-normal-4215987472850.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-4-normal-4215987572851.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-5-normal-4215987672852.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-6-normal-4215987772853.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-7-normal-4215987872854.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-8-normal-4215987972855.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-9-normal-4215988072856.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-10-normal-4215988172857.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-yakimono-3-2-11-normal-4215988272858.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lo-l3-09-10--tang-l3-vincom-plaza-3-2--so-3-3c-duong-3-2--p--vuon-lai-177611221066087073.webp",
                of("Vị trí tại Vincom Plaza 3/2", "Location at Vincom Plaza 3/2"), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("JAPANESE")
                .name(of("Nhật Bản", "Japanese"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("KOREAN_BBQ")
                .name(of("Nướng Hàn Quốc", "Korean BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BBQ_HOTPOT_BUFFET")
                .name(of("Buffet Nướng & Lẩu", "BBQ & Hotpot Buffet"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (10:00 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
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
            ambience("MODERN",  of("Hiện đại", "Modern")),
            ambience("COZY",    of("Ấm cúng", "Cozy")),
            ambience("VIBRANT", of("Sôi động", "Vibrant")),
            ambience("FAMILY_FRIENDLY", of("Thân thiện gia đình", "Family friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Bò Mỹ nướng tảng", "Grilled US beef block")),
            signatureDishL(of("Ba chỉ bò Wagyu nướng", "Grilled Wagyu beef belly")),
            signatureDishL(of("Sườn non nướng BBQ", "BBQ grilled short ribs")),
            signatureDishL(of("Ba chỉ heo nướng kiểu Hàn", "Korean-style grilled pork belly")),
            signatureDishL(of("Lẩu Tomyum chua cay", "Spicy Tomyum hotpot")),
            signatureDishL(of("Lẩu Kimchi Hàn Quốc", "Korean Kimchi hotpot")),
            signatureDishL(of("Hải sản tươi nướng & nhúng lẩu", "Fresh seafood for grill & hotpot")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Tôm sú nướng phô mai", "Grilled prawns with cheese")),
            signatureDishL(of("Nấm & rau nhúng lẩu tươi", "Fresh mushrooms & vegetables for hotpot"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0001.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0002.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0003.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0004.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0005.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0006.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0007.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/Mai%20T%C3%A2m/Yakimono/0008.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-quay-line.jpg",
                of("Menu quầy line", "Buffet line menu"), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-1.jpg",
                of("Suất buffet 239K", "239K buffet set"), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-2.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-3.jpg",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-239k-4.jpg",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-1.jpg",
                of("Suất buffet 299K", "299K buffet set"), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-2.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-3.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-4.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-5.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-6.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-299k-7.jpg",
                of("", ""), false, 19),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-1.jpg",
                of("Suất buffet 349K", "349K buffet set"), false, 20),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-2.jpg",
                of("", ""), false, 21),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-3.jpg",
                of("", ""), false, 22),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-4.jpg",
                of("", ""), false, 23),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-5.jpg",
                of("", ""), false, 24),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-6.jpg",
                of("", ""), false, 25),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-7.jpg",
                of("", ""), false, 26),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-8.jpg",
                of("", ""), false, 27),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-9.jpg",
                of("", ""), false, 28),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-10.jpg",
                of("", ""), false, 29),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-11.jpg",
                of("", ""), false, 30),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-12.jpg",
                of("", ""), false, 31),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-13.jpg",
                of("", ""), false, 32),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/duonglong/yakimono/menu-suat-buffet-349k-14.jpg",
                of("", ""), false, 33)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet nướng lẩu", "BBQ hotpot buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất Buffet Trưa T2-T6", "Weekday Lunch Buffet (Mon-Fri)"))
                        .description(of("Buffet nướng trưa các ngày trong tuần: thịt bò, heo, gà, hải sản, rau và lẩu chọn lựa.", "Weekday lunch BBQ buffet: beef, pork, chicken, seafood, vegetables and a choice of hotpot."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("239000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "lunch"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất Buffet 299K T2-CN", "299K Daily Buffet (Mon-Sun)"))
                        .description(of("Buffet nướng lẩu cả tuần: đa dạng thịt bò, heo, hải sản, viên thả và lẩu Tomyum/Kimchi.", "Daily BBQ hotpot buffet: assorted beef, pork, seafood, balls and Tomyum/Kimchi hotpot."))
                        .priceFrom(new BigDecimal("299000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "hotpot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Suất Buffet 349K T2-CN", "349K Daily Buffet (Mon-Sun)"))
                        .description(of("Buffet cao cấp với thịt bò Mỹ, Wagyu, hải sản phong phú và lẩu đặc trưng.", "Premium buffet with US beef, Wagyu, abundant seafood and signature hotpot."))
                        .priceFrom(new BigDecimal("349000"))
                        .priceTo(new BigDecimal("349000"))
                        .unit(of("suất", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "bbq", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm gửi xe ô tô tại Vincom Plaza 3/2", "Car park at Vincom Plaza 3/2"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm gửi xe máy tại Vincom Plaza 3/2", "Motorbike park at Vincom Plaza 3/2"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Gửi xe tại hầm trung tâm thương mại Vincom Plaza 3/2.", "Parking available at Vincom Plaza 3/2 mall basement."))
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
            "SET_LUNCH",
            "SMOKING_AREA",
            "BUFFET_SERVICE",
            "BBQ_GRILL",
            "VISA_MASTER",
            "VAT_INVOICE",
            "MALL_LOCATION"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Yakimono - Vincom Plaza 3/2", "About Yakimono - Vincom Plaza 3/2")),
            paragraph(ContentSection.OVERVIEW,
                of("Yakimono - Vincom Plaza 3/2 là nhà hàng buffet nướng lẩu phong cách Nhật - Hàn nằm ngay trong trung tâm thương mại Vincom Plaza 3/2, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi bật với menu buffet đa dạng từ 239K đến 479K, mang đến trải nghiệm nướng và lẩu trọn vị cho mọi thực khách.",
                "Yakimono - Vincom Plaza 3/2 is a Japanese-Korean style BBQ hotpot buffet restaurant located right inside Vincom Plaza 3/2 mall, Ho Chi Minh City. " +
                "It stands out with diverse buffet sets from 239K to 479K, delivering a full grill and hotpot experience for every diner.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng hiện đại, ấm cúng và sôi động, được bố trí khoa học với sức chứa lên đến khoảng 200 khách. " +
                "Yakimono có thêm các phòng riêng phù hợp cho nhóm đông, tiệc gia đình hay liên hoan công ty.",
                "The restaurant offers a modern, cozy and vibrant space, well arranged with a capacity of around 200 guests. " +
                "Yakimono also has private rooms suitable for large groups, family parties or company gatherings.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Yakimono là buffet nướng kết hợp lẩu với thịt bò Mỹ, Wagyu, ba chỉ heo kiểu Hàn, hải sản tươi và các nồi lẩu Tomyum, Kimchi. " +
                "Thực khách có thể chọn suất 239K cho buffet trưa, 299K hoặc 349K cho buffet cả tuần, vừa túi tiền mà vẫn no nê.",
                "Yakimono's highlight is its BBQ buffet combined with hotpot featuring US beef, Wagyu, Korean-style pork belly, fresh seafood and Tomyum, Kimchi broths. " +
                "Diners can choose the 239K weekday lunch buffet, or the 299K and 349K all-week buffets, affordable yet satisfying.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí hiện đại và phục vụ tận tình, Yakimono - Vincom Plaza 3/2 phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật hay các buổi tiếp khách thân mật.",
                "With a modern atmosphere and attentive service, Yakimono - Vincom Plaza 3/2 is ideal for family meals, " +
                "friends gatherings, office parties, birthdays or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Bếp nướng tại bàn, có khu hút thuốc và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, sound & light systems and decoration / MC support for events. " +
                "Tabletop grills, a smoking area are available, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại Lô L3-09-10, Tầng L3 Vincom Plaza 3/2, Số 3-3C đường 3/2, P. Vườn Lài, Q. 10, " +
                "thuận tiện di chuyển và có hầm gửi xe ngay tại trung tâm thương mại.",
                "The restaurant is located at Lot L3-09-10, Level L3 Vincom Plaza 3/2, No. 3-3C 3/2 Street, Vuon Lai Ward, District 10, " +
                "easy to reach with parking right at the mall.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.654000, 10.770000})
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho hóa đơn.", "A 5% service charge applies to the bill."))
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
                .id("pr-30")
                .name(of("Phòng riêng 30 khách", "Private room for 30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
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
            .districtName(of("Q. 10", "District 10"))
            .wardName(of("P. Vườn Lài", "Vuon Lai Ward"))
            .addressLine(of("Lô L3-09-10, Tầng L3 Vincom Plaza 3/2, Số 3-3C đường 3/2, P. Vườn Lài, Q. 10, TP. Hồ Chí Minh", "Lot L3-09-10, Level L3 Vincom Plaza 3/2, No. 3-3C 3/2 Street, Vuon Lai Ward, District 10, Ho Chi Minh City"))
            .location(new double[]{106.654000, 10.770000})
            .name(of("Yakimono - Vincom Plaza 3/2", "Yakimono - Vincom Plaza 3/2"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet nướng lẩu phong cách Nhật - Hàn tại Vincom Plaza 3/2, TP. Hồ Chí Minh.", "Japanese-Korean style BBQ hotpot buffet at Vincom Plaza 3/2, Ho Chi Minh City."))
            .description(of("Yakimono - Vincom Plaza 3/2 là nhà hàng buffet nướng lẩu phong cách Nhật - Hàn tại Tầng L3 Vincom Plaza 3/2, Số 3-3C đường 3/2, Q. 10, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trải nghiệm nướng và lẩu trọn vị với thịt bò Mỹ, Wagyu, hải sản tươi và nhiều lựa chọn lẩu, không gian hiện đại, " +
                "phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Yakimono - Vincom Plaza 3/2 is a Japanese-Korean style BBQ hotpot buffet restaurant at Level L3 Vincom Plaza 3/2, No. 3-3C 3/2 Street, District 10, Ho Chi Minh City. " +
                "It delivers a full grill and hotpot experience with US beef, Wagyu, fresh seafood and various hotpot options in a modern space, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("0931006005")
            .email("reservation@yakimono.vn")
            .website("https://pasgo.vn/nha-hang/yakimono-vincom-plaza-3-2")
            .facebookPage("https://facebook.com/yakimono.vn")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("500000"))
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
