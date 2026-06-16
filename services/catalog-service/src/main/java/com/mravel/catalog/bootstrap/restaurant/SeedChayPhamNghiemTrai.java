// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedChayPhamNghiemTrai.java
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
public class SeedChayPhamNghiemTrai {

    @Bean
    CommandLineRunner seedChayPhamNghiemTraiRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedChayPhamNghiemTrai] start");

            RestaurantDoc doc = restaurantChayPhamNghiemTrai();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedChayPhamNghiemTrai] done");
        };
    }

    // =====================================================================
    //        CHAY PHẠM NGHIÊM TRAI - BONGENSAI - NGUYỄN ĐĂNG GIAI (Q.2)
    // =====================================================================

    private static RestaurantDoc restaurantChayPhamNghiemTrai() {
        String slug = "chay-pham-nghiem-trai";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-1-normal-2282294461016.webp",
                of("Không gian nhà hàng chay Phạm Nghiêm Trai", "Pham Nghiem Trai vegetarian restaurant space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-2-normal-2282594861024.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-3-normal-2282220161015.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-4-normal-2282594661017.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-5-normal-2282594461013.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-6-normal-2282220461018.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-7-normal-2282220561019.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-8-normal-2282220661020.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trang-9-normal-2282221361021.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trai-10-normal-2282221661022.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trai-11-normal-2282221761023.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-pham-nghiem-trai-12-normal-2282594761014.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VEGETARIAN")
                .name(of("Món chay", "Vegetarian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("SOUTH")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (9:00 - 22:00 cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    9, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   9, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 9, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  9, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    9, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  9, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    9, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("VEGETARIAN_DAY",of("Ngày ăn chay - rằm, mùng một", "Vegetarian days - full moon & first lunar day"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",     of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",  of("Trang nhã", "Elegant")),
            ambience("RUSTIC",   of("Mộc mạc, gần gũi thiên nhiên", "Rustic, close to nature")),
            ambience("ZEN",      of("Thiền tịnh, thanh tịnh", "Zen and serene"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu nấm chay thập cẩm", "Assorted vegetarian mushroom hotpot")),
            signatureDishL(of("Gỏi cuốn chay", "Vegetarian fresh spring rolls")),
            signatureDishL(of("Cơm lá sen hạt sen", "Lotus leaf rice with lotus seeds")),
            signatureDishL(of("Đậu hũ kho tộ chay", "Vegetarian braised tofu in clay pot")),
            signatureDishL(of("Nấm đùi gà nướng sa tế", "Grilled king oyster mushroom with satay")),
            signatureDishL(of("Canh chua chay", "Vegetarian sweet and sour soup")),
            signatureDishL(of("Chả giò chay", "Vegetarian fried spring rolls")),
            signatureDishL(of("Rau củ kho quẹt", "Vegetables with caramel dipping sauce")),
            signatureDishL(of("Mì xào chay rau nấm", "Stir-fried noodles with vegetables and mushrooms")),
            signatureDishL(of("Chè hạt sen long nhãn", "Lotus seed and longan sweet soup"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-pham-nghiem-trai/menu-nghi%C3%AAm-trai-7.jpg",
                of("", ""), false, 6)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("MAIN_COURSE")
                .name(of("Món chính", "Main course"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu nấm chay thập cẩm", "Assorted vegetarian mushroom hotpot"))
                        .description(of("Lẩu nấm chay với nhiều loại nấm, rau củ tươi và đậu hũ, nước dùng thanh ngọt.", "Vegetarian mushroom hotpot with assorted mushrooms, fresh vegetables and tofu in a clear, sweet broth."))
                        .priceFrom(new BigDecimal("220000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("vegetarian", "hotpot", "mushroom"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cơm lá sen hạt sen", "Lotus leaf rice with lotus seeds"))
                        .description(of("Cơm hấp lá sen cùng hạt sen, nấm và rau củ, hương thơm thanh nhẹ.", "Rice steamed in lotus leaf with lotus seeds, mushrooms and vegetables, with a light fragrant aroma."))
                        .priceFrom(new BigDecimal("90000"))
                        .priceTo(new BigDecimal("130000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("vegetarian", "rice", "lotus"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Nguyễn Đăng Giai", "Car parking on Nguyen Dang Giai street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy tại nhà hàng", "Motorbike parking at the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hướng dẫn đỗ xe, miễn phí cho khách.", "Staff on hand to guide parking, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "EVENT_DECOR",
            "SET_LUNCH",
            "VAT_INVOICE",
            "VISA_MASTER",
            "BIRTHDAY_GIFT"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Chay Phạm Nghiêm Trai", "About Chay Pham Nghiem Trai")),
            paragraph(ContentSection.OVERVIEW,
                of("Chay Phạm Nghiêm Trai (Bongensai) tại 49 Nguyễn Đăng Giai, P. Thảo Điền, Quận 2 là một trong những địa chỉ ẩm thực chay được yêu thích tại TP. Hồ Chí Minh. " +
                "Nhà hàng theo đuổi phong cách ẩm thực chay thanh tịnh, kết hợp tinh hoa món Việt và hương vị Đông Nam Á, mang đến những bữa ăn vừa ngon miệng vừa tốt cho sức khỏe.",
                "Chay Pham Nghiem Trai (Bongensai) at 49 Nguyen Dang Giai St., Thao Dien Ward, District 2 is one of the most beloved vegetarian dining addresses in Ho Chi Minh City. " +
                "The restaurant pursues a serene vegetarian cuisine style, blending the essence of Vietnamese dishes with Southeast Asian flavors to offer meals that are both delicious and healthy.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế mộc mạc, gần gũi thiên nhiên với nhiều cây xanh, ánh sáng dịu nhẹ tạo cảm giác thanh tịnh, thư thái. " +
                "Nhà hàng có sức chứa khoảng 150 khách, bao gồm khu vực ngồi ngoài trời và các phòng riêng phù hợp cho nhóm bạn, gia đình hay tiệc công ty.",
                "The restaurant space is designed in a rustic style, close to nature with abundant greenery and soft lighting that creates a serene, relaxing feel. " +
                "It can accommodate around 150 guests, including outdoor seating and private rooms suitable for groups of friends, families or company parties.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn chay phong phú với lẩu nấm thập cẩm, gỏi cuốn chay, cơm lá sen hạt sen, đậu hũ kho tộ, nấm nướng sa tế và nhiều món rau củ chế biến tinh tế. " +
                "Mỗi món ăn được trau chuốt từ nguyên liệu tươi sạch, hạn chế dầu mỡ, giữ trọn hương vị tự nhiên của rau củ và nấm.",
                "The vegetarian menu is rich with assorted mushroom hotpot, fresh spring rolls, lotus leaf rice with lotus seeds, braised tofu in clay pot, satay grilled mushrooms and many delicately prepared vegetable dishes. " +
                "Each dish is crafted from fresh, clean ingredients, with limited oil, preserving the natural flavors of vegetables and mushrooms.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng phù hợp cho bữa ăn gia đình, tụ tập bạn bè, hẹn hò, liên hoan văn phòng và đặc biệt là những ngày ăn chay như rằm, mùng một. " +
                "Đây cũng là lựa chọn lý tưởng cho khách muốn tìm một bữa ăn nhẹ nhàng, lành mạnh giữa nhịp sống bận rộn của thành phố.",
                "The restaurant suits family meals, friends gatherings, dates, office parties and especially vegetarian days such as the full moon and first lunar day. " +
                "It is also an ideal choice for guests seeking a light, healthy meal amid the busy pace of city life.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng có phòng riêng, hệ thống âm thanh ánh sáng, máy chiếu và dịch vụ trang trí, MC phục vụ cho các buổi tiệc, sự kiện. " +
                "Khách được phục vụ chu đáo cùng chỗ để xe ô tô, xe máy miễn phí, wifi và máy lạnh trong toàn bộ khu vực.",
                "The restaurant offers private rooms, a sound and lighting system, a projector and decoration plus MC services for parties and events. " +
                "Guests enjoy attentive service along with free car and motorbike parking, wifi and air conditioning throughout the venue.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & access")),
            paragraph(ContentSection.OVERVIEW,
                of("Toạ lạc trên đường Nguyễn Đăng Giai, khu Thảo Điền - Quận 2, nhà hàng nằm ở khu vực yên tĩnh nhưng thuận tiện di chuyển, " +
                "chỉ vài phút từ các tuyến đường lớn và trung tâm Thảo Điền. Thực khách dễ dàng tìm đến bằng ô tô, xe máy hay các ứng dụng gọi xe.",
                "Located on Nguyen Dang Giai street in the Thao Dien area of District 2, the restaurant sits in a quiet yet convenient location, " +
                "just a few minutes from major roads and the heart of Thao Dien. Diners can easily reach it by car, motorbike or ride-hailing apps.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.7330, 10.8060})
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
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc yêu cầu phòng riêng/trang trí.", "Applies to bookings of 6 or more guests or requests for a private room/decoration."))
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
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% áp dụng cho tiệc/phòng riêng.", "A 5% service charge applies to parties/private rooms."))
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
                .minPeople(1)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn 8 VIP", "VIP Table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(4)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Vị trí đẹp, không gian riêng tư hơn.", "Great location, more private space."))
                .build(),

            TableType.builder()
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(12)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("800000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc công ty và sự kiện.", "Private room, suitable for company parties and events."))
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
            .districtName(of("Quận 2", "District 2"))
            .wardName(of("P. Thảo Điền", "Thao Dien Ward"))
            .addressLine(of("49 Nguyễn Đăng Giai, P. Thảo Điền, Quận 2, TP. Hồ Chí Minh", "49 Nguyen Dang Giai St., Thao Dien Ward, District 2, Ho Chi Minh City"))
            .location(new double[]{106.7330, 10.8060})
            .name(of("Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai", "Chay Pham Nghiem Trai - Nguyen Dang Giai"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng chay thanh tịnh, ẩm thực Việt - Đông Nam Á tại Thảo Điền, Quận 2.", "Serene vegetarian restaurant with Vietnamese - Southeast Asian cuisine in Thao Dien, District 2."))
            .description(of("Chay Phạm Nghiêm Trai (Bongensai) là nhà hàng chay tại 49 Nguyễn Đăng Giai, P. Thảo Điền, Quận 2, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến thực đơn chay phong phú đậm chất Việt và Đông Nam Á, không gian mộc mạc gần gũi thiên nhiên, " +
                "phù hợp cho bữa ăn gia đình, tụ tập bạn bè và các dịp ăn chay đặc biệt.",
                "Chay Pham Nghiem Trai (Bongensai) is a vegetarian restaurant at 49 Nguyen Dang Giai St., Thao Dien Ward, District 2, Ho Chi Minh City. " +
                "It offers a rich vegetarian menu with Vietnamese and Southeast Asian flavors in a rustic space close to nature, " +
                "suitable for family meals, friends gatherings and special vegetarian occasions."))
            .phone("(+84) 0931 006 005")
            .email("reservation@phamnghiemtrai.com")
            .website("https://example.com/chay-pham-nghiem-trai")
            .facebookPage("https://facebook.com/chayphamnghiemtrai")
            .bookingHotline("(+84) 0931 006 005")
            .minPricePerPerson(new BigDecimal("130000"))
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
