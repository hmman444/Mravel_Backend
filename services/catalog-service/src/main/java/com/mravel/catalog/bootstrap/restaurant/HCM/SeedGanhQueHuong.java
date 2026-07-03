// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedGanhQueHuong.java
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
public class SeedGanhQueHuong {

    @Bean
    CommandLineRunner seedGanhQueHuongRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedGanhQueHuong] start");

            RestaurantDoc ganh = restaurantGanhQueHuong();
            repo.findBySlugAndActiveTrue(ganh.getSlug())
                .ifPresent(existing -> ganh.setId(existing.getId()));
            repo.save(ganh);
            log.info(">>> upsert: {}", ganh.getSlug());

            log.info(">>> [SeedGanhQueHuong] done");
        };
    }

    // =====================================================================
    //            BUFFET GÁNH QUÊ HƯƠNG - PHẠM NGŨ LÃO (TP.HCM)
    // =====================================================================

    private static RestaurantDoc restaurantGanhQueHuong() {
        String slug = "buffet-ganh-que-huong";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-1-normal-2177437152263.webp",
                of("Không gian Buffet Gánh Quê Hương - Phạm Ngũ Lão", "Buffet Ganh Que Huong - Pham Ngu Lao space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-2-normal-2177437452264.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-3-normal-2177437552265.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-4-normal-2177437652266.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-5-normal-2177437852267.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-6-normal-2177437952268.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-7-normal-2177438152269.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-8-normal-2177438252270.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-9-normal-2177438452271.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-10-normal-2364400452272.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-11-normal-2177438752273.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-liberty-level-9-mon-viet-hai-san-12-normal-2177438952274.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("BUFFET_ASIAN_SEAFOOD")
                .name(of("Buffet Á & Hải sản", "Asian & Seafood Buffet"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản", "Seafood"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (thứ 2 -> CN: 11:00 - 22:00) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
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
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("RUSTIC",    of("Mộc mạc quê hương", "Rustic countryside")),
            ambience("MODERN",    of("Hiện đại", "Modern"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(250)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(4)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Hải sản nướng tại bàn", "Table-grilled seafood")),
            signatureDishL(of("Tôm sú nướng muối ớt", "Salt and chili grilled tiger prawns")),
            signatureDishL(of("Hàu nướng mỡ hành", "Grilled oysters with scallion oil")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay")),
            signatureDishL(of("Lẩu hải sản chua cay", "Spicy and sour seafood hotpot")),
            signatureDishL(of("Gỏi cuốn tôm thịt", "Fresh shrimp and pork spring rolls")),
            signatureDishL(of("Cá lóc nướng trui", "Grilled snakehead fish")),
            signatureDishL(of("Sò huyết rang me", "Blood cockles in tamarind sauce")),
            signatureDishL(of("Gà nướng lu", "Roasted chicken")),
            signatureDishL(of("Chè quê tráng miệng", "Vietnamese countryside sweet soup dessert"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/New%20folder%20(17)/menu%20(1).jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/New%20folder%20(17)/menu%20(2).jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/New%20folder%20(17)/menu%20(3).jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/2%20T%C3%AD%20Qu%C3%A1n/New%20folder%20(17)/menu%20(4).jpg",
                of("", ""), false, 3)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET")
                .name(of("Buffet Á & Hải sản", "Asian & Seafood Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet hải sản nướng người lớn", "Adult grilled seafood buffet"))
                        .description(of("Buffet không giới hạn hải sản nướng, món Á và món Việt, tự chọn tại quầy.", "Unlimited grilled seafood, Asian and Vietnamese buffet, self-served at the counter."))
                        .priceFrom(new BigDecimal("399000"))
                        .priceTo(new BigDecimal("499000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "seafood", "grill"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Buffet trẻ em", "Children buffet"))
                        .description(of("Suất buffet dành cho trẻ em cao từ 1m đến 1m4.", "Buffet portion for children from 1m to 1.4m tall."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("249000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "kids"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("A_LA_CARTE")
                .name(of("Gọi món", "A la carte"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu hải sản chua cay", "Spicy and sour seafood hotpot"))
                        .description(of("Nồi lẩu hải sản đậm đà, dùng kèm bún và rau tươi.", "Rich seafood hotpot served with vermicelli and fresh vegetables."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("550000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "seafood"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Hầm để xe của tòa nhà", "Building basement parking"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("30000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Hầm để xe của tòa nhà", "Building basement parking"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có bảo vệ hướng dẫn; xe máy miễn phí, ô tô gửi hầm tòa nhà.", "Security staff on hand; free motorbike parking, cars use the building basement."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "PROJECTOR",
            "SOUND_LIGHT",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "MC_SERVICE",
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "PRIVATE_ROOM",
            "SET_LUNCH",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "WIFI",
            "CITY_VIEW"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Tổng quan Buffet Gánh Quê Hương - Phạm Ngũ Lão", "Buffet Ganh Que Huong - Pham Ngu Lao overview")),
            paragraph(ContentSection.OVERVIEW,
                of("Buffet Gánh Quê Hương tọa lạc tại lầu 9, số 265 Phạm Ngũ Lão, ngay trung tâm Quận 1, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến một thiên đường ẩm thực với phong cách đa dạng gồm nhiều món Á - Việt - Hải sản nướng, " +
                "phục vụ theo cả hình thức buffet và gọi món để thực khách thoải mái lựa chọn.",
                "Buffet Ganh Que Huong is located on the 9th floor at No. 265 Pham Ngu Lao, right in the heart of District 1, Ho Chi Minh City. " +
                "The restaurant offers a culinary paradise with a diverse style of Asian - Vietnamese - grilled seafood dishes, " +
                "served as both buffet and a la carte so diners can choose freely.")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của Gánh Quê Hương là thiên đường hải sản nướng ngoài trời hấp dẫn ở tầng cao, " +
                "vừa thưởng thức món ngon vừa ngắm trọn nhịp sống sôi động của khu phố Tây Bùi Viện - Phạm Ngũ Lão về đêm.",
                "The highlight of Ganh Que Huong is its appealing rooftop outdoor grilled-seafood paradise, " +
                "where you can enjoy delicious food while taking in the vibrant nightlife of the Bui Vien - Pham Ngu Lao backpacker area.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng sở hữu không gian rộng rãi với sức chứa lên đến 250 khách, bố trí gọn gàng gồm khu vực trong nhà " +
                "máy lạnh mát mẻ và khu vực sân thượng ngoài trời thoáng đãng. Hệ thống 4 phòng riêng phù hợp cho các nhóm khách " +
                "cần không gian riêng tư khi tổ chức tiệc, sinh nhật hay tiếp khách.",
                "The restaurant boasts a spacious setting with a capacity of up to 250 guests, neatly arranged with a cool air-conditioned " +
                "indoor area and an airy open-air rooftop area. Four private rooms suit groups needing privacy for parties, " +
                "birthdays or business meals.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn buffet phong phú với các loại hải sản tươi mới mỗi ngày như tôm sú, hàu, bạch tuộc, sò huyết... " +
                "được chế biến theo nhiều cách: nướng tại bàn, lẩu chua cay, rang me. Bên cạnh đó là loạt món Việt đậm đà " +
                "hương vị quê hương như gỏi cuốn, cá lóc nướng trui, gà nướng lu cùng các món tráng miệng dân dã.",
                "The buffet menu is rich with fresh seafood every day such as tiger prawns, oysters, octopus, blood cockles... " +
                "prepared in many ways: grilled at the table, spicy-sour hotpot, tamarind stir-fry. Alongside are flavorful Vietnamese dishes " +
                "redolent of the homeland such as fresh spring rolls, grilled snakehead fish, roasted chicken and rustic desserts.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với mức giá khoảng 200.000 - 500.000đ/người, Gánh Quê Hương phù hợp cho bữa ăn gia đình, tụ tập bạn bè, " +
                "liên hoan công ty, sinh nhật hay tiếp khách. Không gian linh hoạt và phòng riêng giúp nhà hàng đáp ứng tốt " +
                "cả nhóm nhỏ lẫn đoàn đông.",
                "With a price range of about 200,000 - 500,000 VND/person, Ganh Que Huong is suitable for family meals, friends gatherings, " +
                "company parties, birthdays or business meals. The flexible space and private rooms allow the restaurant to serve " +
                "both small groups and large parties well.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.OVERVIEW,
                of("Nằm tại lầu 9 số 265 Phạm Ngũ Lão, P. Phạm Ngũ Lão, Quận 1, nhà hàng chỉ cách chợ Bến Thành và phố đi bộ Bùi Viện " +
                "vài phút di chuyển, rất thuận tiện cho cả khách địa phương lẫn du khách quốc tế đang lưu trú quanh khu phố Tây.",
                "Located on the 9th floor at No. 265 Pham Ngu Lao, Pham Ngu Lao Ward, District 1, the restaurant is just minutes from Ben Thanh Market " +
                "and the Bui Vien walking street, very convenient for both locals and international travelers staying around the backpacker area.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn & lý do nên chọn", "Booking policy & why choose us")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng nhận đặt bàn trước với chính sách đặt cọc rõ ràng cho nhóm đông và yêu cầu trang trí đặc biệt, " +
                "có xuất hóa đơn VAT theo yêu cầu. Đặt bàn qua hệ thống để giữ chỗ miễn phí và nhận nhiều ưu đãi hấp dẫn cho " +
                "trải nghiệm buffet hải sản tại trung tâm Sài Gòn.",
                "The restaurant accepts advance bookings with a clear deposit policy for large groups and special decoration requests, " +
                "and issues VAT invoices on request. Book through the system to reserve your spot for free and enjoy attractive offers for " +
                "a seafood buffet experience in the heart of Saigon.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.6917, 10.7670})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(6, 1,  of("Không áp dụng ưu đãi ngày 1/6", "Promotion not applicable on Jun 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("400000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh: 400.000đ/chai.", "Spirits corkage fee: 400,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc yêu cầu trang trí đặc biệt.", "Applies to bookings of 6 or more guests or requests for special decoration."))
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho khu vực phòng riêng.", "A 5% service charge applies to private room areas."))
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
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8-outdoor")
                .name(of("Bàn 8 sân thượng", "Rooftop table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("400000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn ngoài trời view thành phố, ưu tiên phục vụ.", "Outdoor table with city view, priority service."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(4)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc công ty/sự kiện.", "Private room, suitable for company parties and events."))
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
            .addressLine(of("Lầu 9, Số 265 Phạm Ngũ Lão, P. Phạm Ngũ Lão, Q. 1, TP. Hồ Chí Minh", "9th floor, No. 265 Pham Ngu Lao, Pham Ngu Lao Ward, District 1, Ho Chi Minh City"))
            .location(new double[]{106.6917, 10.7670})
            .name(of("Buffet Gánh Quê Hương - Phạm Ngũ Lão", "Buffet Ganh Que Huong - Pham Ngu Lao"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet & gọi món Á - Việt - Hải sản nướng sân thượng giữa trung tâm Quận 1.", "Asian - Vietnamese - grilled seafood rooftop buffet & a la carte in the heart of District 1."))
            .description(of("Buffet Gánh Quê Hương - Phạm Ngũ Lão là thiên đường ẩm thực Á - Việt - Hải sản nướng " +
                "tọa lạc tại lầu 9 số 265 Phạm Ngũ Lão, Quận 1, TP. Hồ Chí Minh. " +
                "Nhà hàng phục vụ cả buffet lẫn gọi món với hải sản tươi mới mỗi ngày, không gian sân thượng thoáng đãng " +
                "cùng nhiều phòng riêng, phù hợp cho mọi dịp từ gia đình, bạn bè đến tiệc công ty.",
                "Buffet Ganh Que Huong - Pham Ngu Lao is an Asian - Vietnamese - grilled seafood culinary paradise " +
                "located on the 9th floor at No. 265 Pham Ngu Lao, District 1, Ho Chi Minh City. " +
                "The restaurant serves both buffet and a la carte with fresh seafood daily, an airy rooftop space " +
                "and several private rooms, suitable for all occasions from family and friends to company parties."))
            .phone("19006005")
            .email("reservation@ganhquehuong.vn")
            .website("https://pasgo.vn/nha-hang/nha-hang-buffet-ganh-que-huong-2199")
            .facebookPage("https://facebook.com/ganhquehuong")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("200000"))
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
