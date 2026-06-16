// src/main/java/com/mravel/catalog/bootstrap/restaurant/SeedReskiHotpotNguyenThiThap.java
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
public class SeedReskiHotpotNguyenThiThap {

    @Bean
    CommandLineRunner seedReskiHotpotNguyenThiThapRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedReskiHotpotNguyenThiThap] start");

            RestaurantDoc reski = restaurantReskiHotpotNguyenThiThap();
            repo.findBySlugAndActiveTrue(reski.getSlug())
                .ifPresent(existing -> reski.setId(existing.getId()));
            repo.save(reski);
            log.info(">>> upsert: {}", reski.getSlug());

            log.info(">>> [SeedReskiHotpotNguyenThiThap] done");
        };
    }

    // =====================================================================
    //              RESKI HOTPOT - NGUYỄN THỊ THẬP (Q.7, TP.HCM)
    // =====================================================================

    private static RestaurantDoc restaurantReskiHotpotNguyenThiThap() {
        String slug = "reski-hotpot-nguyen-thi-thap";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-1-normal-2663349268237.webp",
                of("Không gian Reski Hotpot - Nguyễn Thị Thập", "Reski Hotpot - Nguyen Thi Thap space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-2-normal-2663349368238.webp",
                of("", ""), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-3-normal-2663349468239.webp",
                of("", ""), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-4-normal-2663349668241.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-5-normal-2663349768242.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ntt-slide-6-normal-2663349568240.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-8-normal-2663350868245.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-12-normal-2663350968246.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-13-normal-2663351068247.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-14-normal-2663351168248.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-15-normal-2663350768244.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/reski-hotpot-ten-lua-18-normal-2663351268249.webp",
                of("", ""), false, 11)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("SOUTHEAST_ASIAN")
                .name(of("Đông Nam Á", "Southeast Asian"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_HOTPOT")
                .name(of("Buffet & Gọi món lẩu", "Hotpot Buffet & A La Carte"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00 - 23:59, hằng ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 23, 59)
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
            .totalCapacity(150)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu Thái chua cay", "Spicy Thai tom yum hotpot")),
            signatureDishL(of("Lẩu nấm thảo mộc", "Herbal mushroom hotpot")),
            signatureDishL(of("Lẩu hải sản tươi", "Fresh seafood hotpot")),
            signatureDishL(of("Bò Mỹ thượng hạng nhúng lẩu", "Premium US beef for hotpot")),
            signatureDishL(of("Hải sản tươi sống nhúng lẩu", "Fresh live seafood for hotpot")),
            signatureDishL(of("Viên thả lẩu thủ công", "Handmade hotpot balls")),
            signatureDishL(of("Đậu hũ non Tứ Xuyên", "Sichuan silken tofu")),
            signatureDishL(of("Mì khoai lang nhúng lẩu", "Sweet potato glass noodles for hotpot")),
            signatureDishL(of("Rau theo mùa", "Seasonal vegetables platter")),
            signatureDishL(of("Trà tắc giải nhiệt", "Iced kumquat tea"))
        );

        // ---------- Ảnh menu (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-1.png",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-2.png",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-3.png",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-4.png",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-5.png",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-6.png",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-7.png",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-8.png",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-9.png",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-10.png",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-11.png",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-12.png",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-13.png",
                of("", ""), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-14.png",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-15.png",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-16.png",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-17.png",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-18.png",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tranphuong/reskihotpot-tenlua/reski-hot-pot-ten-lua-menu-19.png",
                of("", ""), false, 18)
        );

        // ---------- Menu cấu trúc (demo) ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu Thái chua cay", "Spicy Thai tom yum hotpot"))
                        .description(of("Nước lẩu Thái chua cay đậm vị sả, riềng, lá chanh, ăn kèm hải sản và rau tươi.", "Spicy and sour Thai broth rich in lemongrass, galangal and kaffir lime, served with seafood and fresh vegetables."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "thai", "spicy"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu nấm thảo mộc", "Herbal mushroom hotpot"))
                        .description(of("Nước lẩu ninh từ nhiều loại nấm và thảo mộc, thanh ngọt tự nhiên.", "Broth simmered from assorted mushrooms and herbs, naturally sweet and light."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("279000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "mushroom", "herbal"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_COMBO")
                .name(of("Combo Buffet", "Buffet Combo"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet lẩu bò & hải sản", "Beef & seafood hotpot buffet"))
                        .description(of("Buffet lẩu không giới hạn với bò, hải sản, viên thả, rau và mì các loại.", "Unlimited hotpot buffet with beef, seafood, balls, vegetables and assorted noodles."))
                        .priceFrom(new BigDecimal("249000"))
                        .priceTo(new BigDecimal("299000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("buffet", "beef", "seafood"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đỗ xe ô tô trên đường Nguyễn Thị Thập", "Car parking on Nguyen Thi Thap street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe máy trước nhà hàng", "Motorbike parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Có nhân viên hỗ trợ giữ xe, miễn phí cho khách.", "Staff assist with parking, free of charge for guests."))
            .build();

        // ---------- Tiện ích (amenity codes) ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "SMOKING_AREA",
            "VISA_MASTER",
            "VAT_INVOICE",
            "SET_LUNCH",
            "INDUCTION_HOTPOT_STOVE",
            "TAKEAWAY"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.OVERVIEW, of("Giới thiệu Reski Hotpot - Nguyễn Thị Thập", "About Reski Hotpot - Nguyen Thi Thap")),
            paragraph(ContentSection.OVERVIEW,
                of("Reski Hotpot - Nguyễn Thị Thập là nhà hàng chuyên buffet và gọi món lẩu châu Á, " +
                "tọa lạc tại số 436 Nguyễn Thị Thập, P. Tân Quy, Quận 7, TP. Hồ Chí Minh. " +
                "Với hương vị tươi ngon và đa dạng lựa chọn, Reski Hotpot là điểm hẹn quen thuộc của thực khách yêu thích món lẩu tại khu vực Quận 7.",
                "Reski Hotpot - Nguyen Thi Thap is a restaurant specializing in Asian hotpot buffet and a la carte, " +
                "located at 436 Nguyen Thi Thap St., Tan Quy Ward, District 7, Ho Chi Minh City. " +
                "With fresh flavors and diverse choices, Reski Hotpot is a familiar gathering spot for hotpot lovers in the District 7 area.")),
            heading(ContentSection.OVERVIEW, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.OVERVIEW,
                of("Không gian nhà hàng được thiết kế hiện đại, thoáng đãng với sức chứa lên đến 150 khách, " +
                "bố trí nhiều khu bàn linh hoạt cùng phòng riêng phù hợp cho các nhóm đông và tiệc liên hoan. " +
                "Bàn lẩu trang bị bếp từ âm bàn an toàn, sạch sẽ, mang đến trải nghiệm thưởng thức tiện nghi.",
                "The restaurant is designed in a modern, airy style with a capacity of up to 150 guests, " +
                "featuring flexible seating areas and private rooms suitable for large groups and parties. " +
                "Hotpot tables are equipped with safe, clean built-in induction stoves for a comfortable dining experience.")),
            heading(ContentSection.OVERVIEW, of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.OVERVIEW,
                of("Thực đơn tại Reski Hotpot xoay quanh các loại nước lẩu phong phú như lẩu Thái chua cay, lẩu nấm thảo mộc, lẩu hải sản tươi. " +
                "Đồ nhúng đa dạng từ bò Mỹ thượng hạng, hải sản tươi sống, viên thả thủ công đến rau theo mùa và các loại mì, " +
                "đáp ứng cả khách chọn buffet không giới hạn lẫn khách gọi món theo sở thích.",
                "The menu at Reski Hotpot revolves around a rich variety of broths such as spicy Thai tom yum, herbal mushroom and fresh seafood hotpot. " +
                "Dipping items range from premium US beef, fresh live seafood and handmade balls to seasonal vegetables and assorted noodles, " +
                "catering to both unlimited buffet diners and a la carte guests.")),
            heading(ContentSection.OVERVIEW, of("Phù hợp dịp nào", "Best occasions")),
            paragraph(ContentSection.OVERVIEW,
                of("Reski Hotpot phù hợp cho bữa ăn gia đình ấm cúng, tụ tập bạn bè cuối tuần, liên hoan văn phòng, sinh nhật hay tiệc công ty. " +
                "Không khí sôi động cùng những nồi lẩu nóng hổi luôn tạo nên những buổi quây quần vui vẻ và trọn vẹn.",
                "Reski Hotpot is suitable for cozy family meals, weekend friends gatherings, office parties, birthdays or company events. " +
                "The vibrant atmosphere together with sizzling hotpots always makes for cheerful and complete get-togethers.")),
            heading(ContentSection.OVERVIEW, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.OVERVIEW,
                of("Nhà hàng phục vụ chu đáo với điều hòa mát mẻ, wifi miễn phí, ghế cho trẻ em, phòng riêng, hệ thống âm thanh ánh sáng và hỗ trợ MC, trang trí cho các bữa tiệc. " +
                "Khách hàng được hỗ trợ giữ xe miễn phí và có thể xuất hóa đơn VAT theo yêu cầu.",
                "The restaurant offers attentive service with cool air conditioning, free wifi, kids chairs, private rooms, sound and lighting systems plus MC and decoration support for parties. " +
                "Guests enjoy free parking assistance and can request VAT invoices.")),
            heading(ContentSection.OVERVIEW, of("Vị trí & di chuyển", "Location & directions")),
            paragraph(ContentSection.OVERVIEW,
                of("Nằm trên trục đường Nguyễn Thị Thập sầm uất tại Quận 7, nhà hàng thuận tiện di chuyển từ Phú Mỹ Hưng, " +
                "khu trung tâm Quận 7 và các quận lân cận. Mặt tiền dễ tìm, có chỗ đỗ xe rộng rãi cho cả ô tô và xe máy.",
                "Located on the bustling Nguyen Thi Thap street in District 7, the restaurant is convenient to reach from Phu My Hung, " +
                "the District 7 center and neighboring districts. The street-front location is easy to find with ample parking for both cars and motorbikes.")),
            heading(ContentSection.OVERVIEW, of("Chính sách đặt bàn", "Booking policy")),
            paragraph(ContentSection.OVERVIEW,
                of("Khách nên đặt bàn trước, đặc biệt vào cuối tuần và các dịp lễ để được phục vụ tốt nhất. " +
                "Đối với nhóm đông hoặc phòng riêng, nhà hàng có thể yêu cầu đặt cọc giữ chỗ; ưu đãi không áp dụng vào một số ngày lễ, tết đặc biệt.",
                "Guests are advised to book in advance, especially on weekends and holidays for the best service. " +
                "For large groups or private rooms, a deposit may be required to hold the reservation; promotions are not applicable on certain holidays and Tet."))
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(3, 8,  of("Không áp dụng ưu đãi ngày 8/3", "Promotion not applicable on Mar 8")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(10, 20, of("Không áp dụng ưu đãi ngày 20/10", "Promotion not applicable on Oct 20")),
            blackoutGregorian(12, 25, of("Không áp dụng ưu đãi ngày 25/12", "Promotion not applicable on Dec 25"))
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
            .vatPercent(new BigDecimal("8"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng có xuất hóa đơn VAT theo yêu cầu.", "The restaurant issues VAT invoices on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu phí phục vụ 5% cho nhóm đông và phòng riêng.", "5% service charge applies to large groups and private rooms."))
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
                .totalTables(15)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Phù hợp gia đình/nhóm bạn.", "Suitable for families and groups of friends."))
                .build(),

            TableType.builder()
                .id("pr-20")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(12)
                .maxPeople(30)
                .totalTables(2)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng, phù hợp tiệc nhóm đông và liên hoan.", "Private room, suitable for large group parties and gatherings."))
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
            .districtName(of("Quận 7", "District 7"))
            .wardName(of("P. Tân Quy", "Tan Quy Ward"))
            .addressLine(of("Số 436 Nguyễn Thị Thập, P. Tân Quy, Q. 7, TP. Hồ Chí Minh", "No. 436 Nguyen Thi Thap St., Tan Quy Ward, District 7, Ho Chi Minh City"))
            .location(new double[]{106.7152, 10.7426})
            .name(of("Reski Hotpot - Nguyễn Thị Thập", "Reski Hotpot - Nguyen Thi Thap"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet & gọi món lẩu châu Á tươi ngon tại Quận 7, TP. HCM.", "Fresh Asian hotpot buffet & a la carte in District 7, Ho Chi Minh City."))
            .description(of("Reski Hotpot - Nguyễn Thị Thập là nhà hàng buffet và gọi món lẩu châu Á tại số 436 Nguyễn Thị Thập, " +
                "P. Tân Quy, Quận 7, TP. Hồ Chí Minh. Nhà hàng nổi bật với các loại nước lẩu phong phú, đồ nhúng tươi ngon, " +
                "không gian hiện đại và phù hợp cho nhiều dịp từ ăn gia đình, tụ tập bạn bè đến tiệc công ty.",
                "Reski Hotpot - Nguyen Thi Thap is an Asian hotpot buffet and a la carte restaurant at 436 Nguyen Thi Thap St., " +
                "Tan Quy Ward, District 7, Ho Chi Minh City. The restaurant stands out with a rich variety of broths, fresh dipping items, " +
                "a modern space and is suitable for many occasions from family meals and friends gatherings to company parties."))
            .phone("0931006005")
            .email("reservation@reskihotpot.vn")
            .website("https://example.com/reski-hotpot-nguyen-thi-thap")
            .facebookPage("https://facebook.com/reskihotpot")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("220000"))
            .maxPricePerPerson(new BigDecimal("280000"))
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
