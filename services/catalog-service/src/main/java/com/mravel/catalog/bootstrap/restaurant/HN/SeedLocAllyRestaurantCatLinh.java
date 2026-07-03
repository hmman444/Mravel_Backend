package com.mravel.catalog.bootstrap.restaurant.HN;

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
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedLocAllyRestaurantCatLinh {

    @Bean
    CommandLineRunner seedLocAllyRestaurantCatLinhRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLocAllyRestaurantCatLinh] start");

            RestaurantDoc doc = restaurantLocAllyCatLinh();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLocAllyRestaurantCatLinh] done");
        };
    }

    // =====================================================================
    //        LOC-ALLY RESTAURANT - CAT LINH (HA NOI)
    // =====================================================================

    private static RestaurantDoc restaurantLocAllyCatLinh() {
        String slug = "loc-ally-restaurant-cat-linh";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-0-normal-4239497173472.webp",
                of("Không gian sang trọng Lộc-Ally Restaurant - Cát Linh", "Elegant interior of Loc-Ally Restaurant - Cat Linh"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-1-normal-4239497273473.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-3-normal-4239497373474.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-4-normal-4239497473475.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-5-normal-4239497573476.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-6-normal-4239497673477.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-7-normal-4239497773478.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-8-normal-4239497873479.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-9-normal-4239497973480.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-10-normal-4239498073481.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-11-normal-4239498173482.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-12-normal-4239498273483.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-13-normal-4239498373484.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-14-normal-4239498473485.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-15-normal-4239498573486.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-16-normal-4239498673487.webp",
                of("", ""), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-17-normal-4239498773488.webp",
                of("", ""), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-18-normal-4239498873489.webp",
                of("", ""), false, 17),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-19-normal-4239498973490.webp",
                of("", ""), false, 18),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-20-normal-4239499073491.webp",
                of("", ""), false, 19),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-21-normal-4239499173492.webp",
                of("", ""), false, 20),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-22-normal-4239499273493.webp",
                of("", ""), false, 21),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-23-normal-4239499373494.webp",
                of("", ""), false, 22),
            img("https://pasgo.vn/Upload/anh-chi-tiet/loc-ally-restaurant-24-normal-4239499473495.webp",
                of("", ""), false, 23)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-00.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-01.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-02.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-03.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-04.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-05.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-09.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-06.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-07.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-08.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/01.%20loc-ally/loc-ally-menu-10.jpg",
                of("", ""), false, 10)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("ASIAN_FUSION")
                .name(of("Ẩm thực Á - Âu", "Asian-European fusion"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("EUROPEAN")
                .name(of("Châu Âu", "European"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("INTERNATIONAL")
                .name(of("Quốc tế", "International"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
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
            suitable("BUSINESS_MEAL",  of("Tiếp khách doanh nghiệp", "Business meal")),
            suitable("COUPLE",         of("Hẹn hò lãng mạn", "Romantic date")),
            suitable("ANNIVERSARY",    of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("COMPANY_PARTY",  of("Tiệc công ty", "Company party")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp", "Corporate event")),
            suitable("FAMILY",         of("Ăn gia đình", "Family dining")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật", "Birthday celebration")),
            suitable("GROUP_MEETING",  of("Họp nhóm VIP", "VIP group meeting")),
            suitable("FRIENDS",        of("Gặp gỡ bạn bè", "Friends gathering")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng cao cấp", "Premium office lunch"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ELEGANT",    of("Sang trọng", "Elegant")),
            ambience("LUXURIOUS",  of("Xa hoa", "Luxurious")),
            ambience("MODERN",     of("Hiện đại", "Modern")),
            ambience("ROMANTIC",   of("Lãng mạn", "Romantic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Sườn bò Wagyu nướng than", "Wagyu beef ribs charcoal grilled")),
            signatureDishL(of("Tôm hùm sốt bơ tỏi", "Lobster in garlic butter sauce")),
            signatureDishL(of("Cá hồi fillet áp chảo sốt chanh dill", "Pan-seared salmon fillet with lemon dill sauce")),
            signatureDishL(of("Bò Beefsteak thăn nội Âu", "European-style tenderloin beefsteak")),
            signatureDishL(of("Gà nướng thảo mộc Provence", "Provencal herb-roasted chicken")),
            signatureDishL(of("Risotto nấm truffle Ý", "Italian truffle mushroom risotto")),
            signatureDishL(of("Soup kem bí đỏ kiểu Pháp", "French cream of pumpkin soup")),
            signatureDishL(of("Phở bò truyền thống Hà Nội", "Traditional Hanoi beef pho")),
            signatureDishL(of("Chả cá Lã Vọng kiểu mới", "Modern-style La Vong grilled fish cake")),
            signatureDishL(of("Bánh mì nướng bơ tỏi kiểu Á-Âu", "Asian-European garlic butter toasted bread"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("A_LA_CARTE")
                .name(of("Gọi món Á - Âu", "Asian-European A la carte"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set menu 2 người", "Set menu for 2"))
                        .description(of("Gồm khai vị, món chính Á-Âu, tráng miệng và thức uống theo mùa.", "Includes appetizer, Asian-European main course, dessert and seasonal drink."))
                        .priceFrom(new BigDecimal("700000"))
                        .priceTo(new BigDecimal("1200000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "premium"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet Grandezvous ẩm thực quốc tế", "Grandezvous international buffet"))
                        .description(of("Buffet quốc tế cao cấp phục vụ từ 18h-22h, hơn 80 món từ khắp châu lục.", "Premium international buffet served from 18:00-22:00 with over 80 dishes from across continents."))
                        .priceFrom(new BigDecimal("999000"))
                        .priceTo(new BigDecimal("999000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "international", "premium"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm để xe khách sạn Grand Mercure Hanoi", "Grand Mercure Hanoi hotel basement parking"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("40000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe trước khách sạn", "Hotel front parking area"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("15000"))
            .notes(of("Bãi đỗ xe khách sạn 5 sao có bảo vệ 24/7. Phí theo giờ.", "5-star hotel parking with 24/7 security. Hourly rate applies."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "PRIVATE_ROOM",
            "MC_SERVICE",
            "EVENT_DECOR",
            "HOTEL_PARKING",
            "MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "CITY_VIEW",
            "ELEVATOR_ACCESS",
            "MULTILINGUAL_STAFF",
            "LIVE_MUSIC"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Lộc-Ally Restaurant - Cát Linh", "About Loc-Ally Restaurant - Cat Linh")),
            paragraph(ContentSection.STORY,
                of("Lộc-Ally Restaurant tọa lạc tại tầng 2 của khách sạn Grand Mercure Hanoi 5 sao, số 9 Cát Linh, quận Ba Đình, Hà Nội. " +
                   "Với phong cách ẩm thực Á - Âu đặc sắc, nhà hàng là lựa chọn hàng đầu cho những buổi tiệc sang trọng, " +
                   "tiếp khách doanh nghiệp và các dịp đặc biệt của giới thượng lưu và khách du lịch quốc tế tại Thủ đô.",
                   "Loc-Ally Restaurant is located on the 2nd floor of the 5-star Grand Mercure Hanoi hotel at No. 9 Cat Linh, Ba Dinh District, Hanoi. " +
                   "With a distinctive Asian-European culinary style, the restaurant is the top choice for elegant banquets, " +
                   "business entertainment, and special occasions for the elite and international travelers in the capital.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian ăn uống sang trọng với sức chứa lên đến 200 khách, bao gồm khu vực phòng ăn mở " +
                   "trang trí tinh tế và 3 phòng riêng tư lý tưởng cho các nhóm từ 10 đến 50 khách. " +
                   "Toàn bộ không gian được điều hoà mát mẻ, ánh sáng ấm áp và thiết kế nội thất mang phong cách hiện đại pha trộn nét cổ điển Á Đông.",
                   "The restaurant boasts a luxurious dining space with a capacity of up to 200 guests, including an open dining area " +
                   "decorated with finesse and 3 private rooms ideal for groups of 10 to 50 guests. " +
                   "The entire space is air-conditioned with warm lighting and interior design in modern style blended with classic Asian accents.")),
            heading(ContentSection.STORY, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Thực đơn Lộc-Ally kết hợp hài hòa tinh hoa ẩm thực Á - Âu với các nguyên liệu nhập khẩu cao cấp. " +
                   "Nổi bật là sườn bò Wagyu nướng than, tôm hùm sốt bơ tỏi, cá hồi fillet áp chảo sốt chanh dill, " +
                   "cùng các món truyền thống Hà Nội như phở bò, chả cá Lã Vọng được tái hiện theo phong cách sáng tạo. " +
                   "Buffet Grandezvous quốc tế (999K/người) vào tối T7 là điểm nhấn được thực khách yêu thích nhất.",
                   "Loc-Ally's menu harmoniously blends the quintessence of Asian and European cuisine with premium imported ingredients. " +
                   "Standouts include Wagyu beef ribs charcoal-grilled, lobster in garlic butter sauce, pan-seared salmon with lemon dill sauce, " +
                   "alongside traditional Hanoi dishes such as beef pho and La Vong fish cake reinvented creatively. " +
                   "The Grandezvous international buffet (999K/person) on Saturday evenings is the most beloved highlight.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Lộc-Ally Restaurant là lựa chọn lý tưởng cho bữa ăn doanh nghiệp, tiệc kỷ niệm, hẹn hò lãng mạn và các sự kiện đặc biệt. " +
                   "Phòng riêng có sức chứa đa dạng phù hợp cả họp nhóm nhỏ lẫn tiệc công ty quy mô 50 khách. " +
                   "Đội ngũ phục vụ chuyên nghiệp, thông thạo tiếng Anh, sẵn sàng tư vấn thực đơn và trang trí theo yêu cầu riêng.",
                   "Loc-Ally Restaurant is the ideal choice for business meals, anniversary parties, romantic dates, and special events. " +
                   "Private rooms with varied capacity suit both small group meetings and company parties of up to 50 guests. " +
                   "The professional team is fluent in English and ready to advise on menus and customized decorations.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích nổi bật", "Services & highlights")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp hệ thống âm thanh ánh sáng chuyên nghiệp, dịch vụ MC chuyên nghiệp và trang trí sự kiện theo yêu cầu. " +
                   "Màn hình chiếu, WiFi tốc độ cao và thang máy tiện lợi đảm bảo mọi nhu cầu hội họp và tổ chức sự kiện. " +
                   "Nhạc live acoustic được biểu diễn vào các tối cuối tuần để thực khách thưởng thức ẩm thực trong bầu không khí thư giãn.",
                   "The restaurant provides a professional sound and lighting system, professional MC service, and custom event decoration. " +
                   "Projector, high-speed WiFi, and convenient elevator ensure all meeting and event needs. " +
                   "Live acoustic music is performed on weekend evenings for diners to enjoy cuisine in a relaxed atmosphere.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nằm tại số 9 Cát Linh, quận Ba Đình, nhà hàng ở vị trí cực kỳ thuận lợi: cách Hồ Hoàn Kiếm chỉ 10 phút đi xe, " +
                   "gần Văn Miếu - Quốc Tử Giám và trung tâm hội nghị quốc gia. Khách có thể đến bằng taxi, xe công nghệ hoặc tàu điện " +
                   "metro Cát Linh - Hà Đông (ga Cát Linh cách 500m).",
                   "Located at No. 9 Cat Linh, Ba Dinh District, the restaurant enjoys an extremely convenient position: " +
                   "just 10 minutes by car from Hoan Kiem Lake, near the Temple of Literature and the National Convention Center. " +
                   "Guests can arrive by taxi, ride-hailing app, or the Cat Linh - Ha Dong metro line (Cat Linh station 500m away).")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt trước ít nhất 2 tiếng cho bàn thường và 24 tiếng cho phòng riêng. " +
                   "Đặt cọc áp dụng cho nhóm từ 8 khách trở lên hoặc phòng riêng, mức cọc từ 500.000đ tùy quy mô. " +
                   "Hóa đơn VAT được xuất theo yêu cầu; phụ phí dịch vụ 5% áp dụng cho phòng riêng.",
                   "The restaurant accepts reservations at least 2 hours in advance for regular tables and 24 hours for private rooms. " +
                   "Deposits apply for groups of 8 or more guests or private rooms, starting from 500,000 VND depending on size. " +
                   "VAT invoices are issued on request; a 5% service charge applies to private rooms.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Lộc-Ally", "Why choose Loc-Ally")),
            paragraph(ContentSection.STORY,
                of("Lộc-Ally Restaurant mang đến trải nghiệm ẩm thực Á - Âu đỉnh cao trong không gian khách sạn 5 sao ngay trung tâm Hà Nội. " +
                   "Sự kết hợp hoàn hảo giữa nguyên liệu nhập khẩu chọn lọc, đầu bếp kinh nghiệm quốc tế và dịch vụ đẳng cấp " +
                   "tạo nên những bữa ăn không thể quên cho mọi dịp từ thường nhật đến tiệc tùng trọng đại.",
                   "Loc-Ally Restaurant delivers a top-tier Asian-European dining experience within a 5-star hotel setting in central Hanoi. " +
                   "The perfect combination of select imported ingredients, internationally experienced chefs, and world-class service " +
                   "creates unforgettable meals for every occasion from everyday dining to grand celebrations.")),
            mapBlock(ContentSection.STORY, new double[]{105.8437, 21.0287})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("500000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 500.000đ/chai.", "Wine corkage fee: 500,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("800000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 800.000đ/chai.", "Spirits corkage fee: 800,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc đặt phòng riêng.", "Applies to bookings of 8 or more guests or private room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi áp dụng cho khách đặt bàn qua hệ thống, không áp dụng vào các ngày lễ và dịp đặc biệt.", "Promotion applies to guests booking via the system; not applicable on public holidays and special occasions."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy theo thời điểm, liên hệ nhà hàng để biết thêm.", "Promotion details vary by time; contact the restaurant for more information."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(30)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu, vui lòng thông báo trước khi thanh toán.", "VAT invoices issued on request; please notify before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho khu vực phòng riêng và sự kiện đặc biệt.", "A 5% service charge applies to private room areas and special events."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống từ ngoài vào, có phụ phí corkage theo loại.", "Outside drinks are allowed with a corkage fee depending on type."))
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

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-standard")
                .name(of("Bàn 4 khách", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(new BigDecimal("350000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn tiêu chuẩn khu vực phòng ăn mở.", "Standard table in the open dining area."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 khách", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("700000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP vị trí đẹp, phục vụ ưu tiên.", "VIP table with prime location and priority service."))
                .build(),
            TableType.builder()
                .id("pr-50")
                .name(of("Phòng riêng 50 khách", "Private room for 50 guests"))
                .seats(50)
                .minPeople(15)
                .maxPeople(50)
                .totalTables(3)
                .depositPrice(new BigDecimal("3000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng sang trọng, đầy đủ âm thanh ánh sáng, phù hợp tiệc và sự kiện.", "Elegant private room with full audio-visual setup, suitable for parties and events."))
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
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Ba Đình", "Ba Dinh District"))
            .wardName(of("P. Ô Chợ Dừa", "O Cho Dua Ward"))
            .addressLine(of("Tầng 2, Khách Sạn Grand Mercure Hanoi, Số 9 Cát Linh, P. Ô Chợ Dừa, Q. Ba Đình, Hà Nội",
                           "2nd Floor, Grand Mercure Hanoi Hotel, No. 9 Cat Linh, O Cho Dua Ward, Ba Dinh District, Hanoi"))
            .location(new double[]{105.8437, 21.0287})
            .name(of("Lộc-Ally Restaurant - Cát Linh", "Loc-Ally Restaurant - Cat Linh"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng Á - Âu sang trọng tầng 2 Grand Mercure Hanoi, Số 9 Cát Linh, Ba Đình.",
                                 "Elegant Asian-European restaurant on the 2nd floor of Grand Mercure Hanoi, No. 9 Cat Linh, Ba Dinh."))
            .description(of("Lộc-Ally Restaurant tọa lạc tại tầng 2 khách sạn Grand Mercure Hanoi 5 sao, Số 9 Cát Linh, quận Ba Đình, Hà Nội. " +
                            "Nổi bật với ẩm thực Á - Âu đặc sắc, không gian sang trọng và dịch vụ đẳng cấp 5 sao, nhà hàng là điểm đến " +
                            "lý tưởng cho tiếp khách doanh nghiệp, hẹn hò lãng mạn và các dịp đặc biệt tại trung tâm Thủ đô.",
                            "Loc-Ally Restaurant is located on the 2nd floor of the 5-star Grand Mercure Hanoi hotel at No. 9 Cat Linh, " +
                            "Ba Dinh District, Hanoi. Known for its distinctive Asian-European cuisine, elegant setting, and 5-star service, " +
                            "the restaurant is the ideal destination for business entertainment, romantic dates, and special occasions in the heart of the capital."))
            .phone("0931006005")
            .email("reservation@locally-restaurant.vn")
            .website("https://pasgo.vn/nha-hang/loc-ally-restaurant-cat-linh-5490")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("350000"))
            .maxPricePerPerson(new BigDecimal("1000000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.LUXURY)
            .priceBucket(PriceBucket.ABOVE_500K)
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
