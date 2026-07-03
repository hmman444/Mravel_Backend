// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedHotpotDongQue3QuangTrung.java
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
public class SeedHotpotDongQue3QuangTrung {

    @Bean
    CommandLineRunner seedHotpotDongQue3QuangTrungRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedHotpotDongQue3QuangTrung] start");

            RestaurantDoc doc = restaurantHotpotDongQue3QuangTrung();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedHotpotDongQue3QuangTrung] done");
        };
    }

    // =====================================================================
    //        HOTPOT ĐỒNG QUÊ 3 PREMIUM - QUANG TRUNG (GÒ VẤP, HCM)
    // =====================================================================

    private static RestaurantDoc restaurantHotpotDongQue3QuangTrung() {
        String slug = "hotpot-dong-que-3-premium-quang-trung";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-1-normal-2301980462718.webp",
                of("Không gian Hotpot Đồng Quê 3 Premium", "Hotpot Dong Que 3 Premium space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-2-normal-2301980562719.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-3-normal-2301980662720.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-4-normal-2301981162721.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-5-normal-2301981462722.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-6-normal-2301981562723.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-7-normal-2301982262724.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-8-normal-2301982362725.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-9-normal-2301982462726.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-10-normal-2301982562727.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-11-normal-2301982662728.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-hotpot-dong-que-12-normal-2301982762729.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/640-quang-trung--p--11--q--go-vap-108360955643448106660372990371.webp",
                of("Mặt tiền 640 Quang Trung, Gò Vấp", "Storefront at 640 Quang Trung, Go Vap"), false, 12)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Việt Nam", "Vietnamese"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (14:30 - 22:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    14, 30, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   14, 30, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 14, 30, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  14, 30, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    14, 30, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  14, 30, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    14, 30, 22, 0)
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
            ambience("RUSTIC",  of("Mộc mạc đồng quê", "Rustic countryside")),
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
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu riêu cua đồng quê", "Countryside field crab hotpot")),
            signatureDishL(of("Lẩu gà lá é", "Chicken hotpot with basil leaves")),
            signatureDishL(of("Lẩu Thái chua cay", "Spicy Thai Tomyum hotpot")),
            signatureDishL(of("Bò tươi nhúng lẩu", "Fresh beef slices for hotpot")),
            signatureDishL(of("Hải sản tươi sống", "Fresh seafood platter")),
            signatureDishL(of("Ếch đồng om lẩu", "Field frog hotpot")),
            signatureDishL(of("Cá lăng nhúng lẩu", "Catfish for hotpot")),
            signatureDishL(of("Rau đồng quê nhúng lẩu", "Countryside vegetables for hotpot")),
            signatureDishL(of("Viên thả lẩu tổng hợp", "Assorted hotpot balls")),
            signatureDishL(of("Mì & bún nhúng lẩu", "Noodles for hotpot"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-1.jpg",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-2.jpg",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-3.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-4.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-5.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-6.jpg",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-7.jpg",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-8.jpg",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-9.jpg",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-10.jpg",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-11.jpg",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/huy/Menu-hotpot-dongque-3-prenium/menu-moi/menu-hotpot-đồng-quê-12.jpg",
                of("", ""), false, 11)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu đặc trưng", "Signature hotpots"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu riêu cua đồng quê", "Countryside field crab hotpot"))
                        .description(of("Nồi lẩu riêu cua đồng đậm vị, ăn kèm bò tươi, đậu hũ, rau đồng quê và bún tươi.", "Rich field crab broth served with fresh beef, tofu, countryside vegetables and fresh vermicelli."))
                        .priceFrom(new BigDecimal("259000"))
                        .priceTo(new BigDecimal("359000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "crab"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu gà lá é", "Chicken hotpot with basil leaves"))
                        .description(of("Lẩu gà ta nấu lá é thơm nồng đặc trưng, ăn kèm nấm tươi, rau và bún.", "Free-range chicken hotpot cooked with fragrant basil leaves, served with fresh mushrooms, vegetables and vermicelli."))
                        .priceFrom(new BigDecimal("289000"))
                        .priceTo(new BigDecimal("389000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "chicken"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe ô tô gần nhà hàng trên đường Quang Trung", "Car parking near the restaurant on Quang Trung street"))
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
            "KIDS_CHAIR",
            "PRIVATE_ROOM",
            "OUTDOOR_TABLE",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "SET_LUNCH",
            "VISA_MASTER",
            "VAT_INVOICE"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Hotpot Đồng Quê 3 Premium - Quang Trung", "About Hotpot Dong Que 3 Premium - Quang Trung")),
            paragraph(ContentSection.OVERVIEW,
                of("Hotpot Đồng Quê 3 Premium - Quang Trung là nhà hàng lẩu mang đậm hương vị đồng quê Việt Nam tại Quận Gò Vấp, TP. Hồ Chí Minh. " +
                "Nhà hàng nổi tiếng với những nồi lẩu nấu theo công thức truyền thống, nguyên liệu tươi mới mỗi ngày, " +
                "phục vụ theo hình thức gọi món đa dạng, phù hợp cho cả gia đình và nhóm đông.",
                "Hotpot Dong Que 3 Premium - Quang Trung is a hotpot restaurant rich in Vietnamese countryside flavors in Go Vap District, Ho Chi Minh City. " +
                "It is famous for hotpots cooked to traditional recipes with ingredients kept fresh every day, " +
                "served a la carte with a diverse menu, suitable for families and large groups alike.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng mang phong cách mộc mạc đồng quê pha nét hiện đại, thoáng đãng với khu vực trong nhà và ngoài trời. " +
                "Tổng sức chứa lên đến khoảng 200 khách cùng 3 phòng riêng, lý tưởng cho tiệc nhóm đông, liên hoan và sự kiện gia đình.",
                "The restaurant features a rustic countryside style blended with modern touches, airy with both indoor and outdoor areas. " +
                "Total capacity reaches around 200 guests with 3 private rooms, ideal for large group parties, gatherings and family events.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Điểm nhấn của nhà hàng là các nồi lẩu đậm đà như lẩu riêu cua đồng quê, lẩu gà lá é và lẩu Thái chua cay, " +
                "ăn kèm bò tươi, hải sản, ếch đồng, cá lăng cùng rau đồng quê tươi mới. Mức giá phổ biến chỉ khoảng 100.000 - 200.000đ/người, " +
                "vừa túi tiền mà vẫn trọn vị.",
                "The highlights are rich hotpots such as field crab hotpot, chicken hotpot with basil leaves and spicy Thai Tomyum, " +
                "paired with fresh beef, seafood, field frog, catfish and fresh countryside vegetables. The typical price is only around 100,000 - 200,000 VND/person, " +
                "affordable yet full of flavor.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Với không khí ấm cúng, sôi động và phục vụ chu đáo, Hotpot Đồng Quê 3 Premium phù hợp cho bữa ăn gia đình, " +
                "tụ tập bạn bè, liên hoan văn phòng, sinh nhật, tiệc công ty hay các buổi tiếp khách thân mật.",
                "With a cozy, vibrant atmosphere and attentive service, Hotpot Dong Que 3 Premium is ideal for family meals, " +
                "friends gatherings, office parties, birthdays, company events or casual business meals.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng trang bị điều hòa, wifi, ghế trẻ em, phòng riêng, khu vực ngoài trời, hệ thống âm thanh ánh sáng và hỗ trợ trang trí, MC cho tiệc. " +
                "Có chỗ đỗ ô tô và xe máy miễn phí, chấp nhận thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant is equipped with air conditioning, wifi, kids chairs, private rooms, an outdoor area, sound & light systems and " +
                "decoration / MC support for events. Free car and motorbike parking is available, Visa/Master cards are accepted, and VAT invoices are issued on request.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng tọa lạc tại 640 Quang Trung, Phường 11, Quận Gò Vấp, nằm trên trục đường Quang Trung sầm uất, " +
                "thuận tiện di chuyển từ trung tâm TP. Hồ Chí Minh và các quận lân cận như Tân Bình, Phú Nhuận.",
                "The restaurant is located at 640 Quang Trung, Ward 11, Go Vap District, on the bustling Quang Trung street, " +
                "convenient to reach from central Ho Chi Minh City and neighboring districts such as Tan Binh and Phu Nhuan.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Quý khách nên đặt bàn trước qua hệ thống để được giữ chỗ, đặc biệt vào cuối tuần và giờ cao điểm. " +
                "Với nhóm đông hoặc đặt phòng riêng có thể cần đặt cọc trước theo chính sách của nhà hàng.",
                "Guests are advised to book in advance through the system to reserve a table, especially on weekends and peak hours. " +
                "For large groups or private room bookings, a deposit may be required according to the restaurant's policy.")),
            mapBlock(ContentSection.OVERVIEW, new double[]{106.660372990371, 10.8360955643448})
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
            .serviceChargeNotes(of("Phụ phí phục vụ 5% áp dụng cho phòng riêng và tiệc.", "5% service charge applies to private rooms and parties."))
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
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình nhỏ, nhóm bạn.", "Suitable for small families and groups of friends."))
                .build(),

            TableType.builder()
                .id("tb-vip-8")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn VIP rộng rãi cho nhóm bạn, gia đình đông.", "Spacious VIP table for groups of friends and large families."))
                .build(),

            TableType.builder()
                .id("pr-40")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(20)
                .maxPeople(40)
                .totalTables(3)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông, liên hoan công ty.", "Private room, suitable for large group parties and company events."))
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
            .wardName(of("Phường 11", "Ward 11"))
            .addressLine(of("640 Quang Trung, Phường 11, Quận Gò Vấp, TP. Hồ Chí Minh", "640 Quang Trung, Ward 11, Go Vap District, Ho Chi Minh City"))
            .location(new double[]{106.660372990371, 10.8360955643448})
            .name(of("Hotpot Đồng Quê 3 Premium - Quang Trung", "Hotpot Dong Que 3 Premium - Quang Trung"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu đồng quê đậm vị, nguyên liệu tươi, giá hợp lý tại Gò Vấp, TP. Hồ Chí Minh.", "Rich countryside hotpot with fresh ingredients at good value in Go Vap, Ho Chi Minh City."))
            .description(of("Hotpot Đồng Quê 3 Premium - Quang Trung là nhà hàng lẩu gọi món tại 640 Quang Trung, Q. Gò Vấp, TP. Hồ Chí Minh. " +
                "Nhà hàng mang đến trọn vị những nồi lẩu đồng quê như lẩu riêu cua, lẩu gà lá é và lẩu Thái chua cay, nguyên liệu tươi mới, " +
                "không gian mộc mạc rộng rãi, phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến liên hoan công ty.",
                "Hotpot Dong Que 3 Premium - Quang Trung is an a la carte hotpot restaurant at 640 Quang Trung, Go Vap District, Ho Chi Minh City. " +
                "It delivers the full flavor of countryside hotpots such as field crab, chicken with basil leaves and spicy Thai Tomyum with fresh ingredients in a rustic, spacious setting, " +
                "suitable for family meals, friends gatherings and company parties."))
            .phone("(+84) 0931 006 005")
            .email("reservation@hotpotdongque.vn")
            .website("https://pasgo.vn/nha-hang/hotpot-dong-que-3-premium-quang-trung")
            .facebookPage("https://facebook.com/hotpotdongque")
            .bookingHotline("(+84) 0931 006 005")
            .minPricePerPerson(new BigDecimal("100000"))
            .maxPricePerPerson(new BigDecimal("200000"))
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
