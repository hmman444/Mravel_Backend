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
public class SeedLauBoTrieuChauPhanChuTrinh {

    @Bean
    CommandLineRunner seedLauBoTrieuChauPhanChuTrinhRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauBoTrieuChauPhanChuTrinh] start");

            RestaurantDoc doc = restaurantLauBoTrieuChauPhanChuTrinh();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauBoTrieuChauPhanChuTrinh] done");
        };
    }

    // =====================================================================
    //      LẨU BÒ TRIỀU CHÂU SỐ 1 PHAN CHU TRINH - HOÀN KIẾM, HÀ NỘI
    // =====================================================================

    private static RestaurantDoc restaurantLauBoTrieuChauPhanChuTrinh() {
        String slug = "lau-bo-trieu-chau-phan-chu-trinh";

        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-18-normal-4238125873392.webp",
                of("Không gian Lẩu Bò Triều Châu Phan Chu Trinh", "Lau Bo Trieu Chau Phan Chu Trinh space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-2-normal-4238126573376.webp",
                of("Khu vực bàn ăn", "Dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-1-normal-4238126673375.webp",
                of("Nồi lẩu bò đặc trưng", "Signature beef hotpot"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-4-normal-4238126773378.webp",
                of("Nguyên liệu bò tươi", "Fresh beef ingredients"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-3-normal-4238118173377.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-5-normal-4238118373379.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-6-normal-4238118473380.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-7-normal-4238118573381.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-8-normal-4238118673382.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-9-normal-4238118773383.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-10-normal-4238118873384.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-11-normal-4238118973385.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-12-normal-4238119073386.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-13-normal-4238119173387.webp",
                of("", ""), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-14-normal-4238119873388.webp",
                of("", ""), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-15-normal-4238119973389.webp",
                of("", ""), false, 15),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-16-normal-4238120073390.webp",
                of("", ""), false, 16),
            img("https://pasgo.vn/Upload/anh-chi-tiet/lau-bo-trieu-chau-pct-17-normal-4238120173391.webp",
                of("", ""), false, 17)
        );

        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/11.%20L%E1%BA%A9u%20B%C3%B2%20Tri%E1%BB%81u%20Ch%C3%A2u/lau-bo-trieu-chau-menu-1.jpg",
                of("Menu Lẩu Bò Triều Châu trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/11.%20L%E1%BA%A9u%20B%C3%B2%20Tri%E1%BB%81u%20Ch%C3%A2u/lau-bo-trieu-chau-menu-2.jpg",
                of("Menu Lẩu Bò Triều Châu trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/11.%20L%E1%BA%A9u%20B%C3%B2%20Tri%E1%BB%81u%20Ch%C3%A2u/lau-bo-trieu-chau-menu-3.jpg",
                of("Menu Lẩu Bò Triều Châu trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/11.%20L%E1%BA%A9u%20B%C3%B2%20Tri%E1%BB%81u%20Ch%C3%A2u/lau-bo-trieu-chau-menu-4.jpg",
                of("Menu Lẩu Bò Triều Châu trang 4", "Menu page 4"), false, 3)
        );

        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("CHINESE_CHAOZHOU")
                .name(of("Ẩm thực Triều Châu", "Chaozhou cuisine"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT")
                .name(of("Lẩu", "Hotpot"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BEEF_SPECIALTY")
                .name(of("Chuyên bò tươi", "Beef specialty"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("GRILLED")
                .name(of("Món nướng", "Grilled dishes"))
                .region("ASIA")
                .build()
        );

        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 0)
        );

        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Bữa ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật", "Birthday party")),
            suitable("COMPANY_EVENT", of("Sự kiện công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách", "Business meal")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa công sở", "Office lunch"))
        );

        List<AmbienceTag> ambience = List.of(
            ambience("TRADITIONAL",   of("Truyền thống Triều Châu", "Traditional Chaozhou")),
            ambience("COZY",          of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",       of("Sôi động", "Vibrant")),
            ambience("RUSTIC",        of("Mộc mạc", "Rustic"))
        );

        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(50)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(false)
            .build();

        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu bò Triều Châu truyền thống", "Traditional Chaozhou beef hotpot")),
            signatureDishL(of("Bò tươi thái mỏng nhúng lẩu", "Thinly sliced fresh beef for hotpot")),
            signatureDishL(of("Bò viên thủ công", "Handmade beef meatballs")),
            signatureDishL(of("Gân bò hầm mềm", "Braised beef tendons")),
            signatureDishL(of("Sườn bò nướng muối ớt", "Salt and chili grilled beef ribs")),
            signatureDishL(of("Lưỡi bò nướng sa tế", "Grilled beef tongue with satay")),
            signatureDishL(of("Đuôi bò hầm thuốc bắc", "Beef tail braised with Chinese herbs")),
            signatureDishL(of("Tim bò xào tỏi", "Stir-fried beef heart with garlic")),
            signatureDishL(of("Nước dùng xương bò hầm 8 tiếng", "8-hour simmered beef bone broth")),
            signatureDishL(of("Bún bò xương ống", "Rice noodle with bone marrow beef"))
        );

        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("LAU")
                .name(of("Lẩu bò", "Beef hotpot"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Lẩu bò Triều Châu đặc biệt (2 người)", "Special Chaozhou beef hotpot (2 persons)"))
                        .description(of("Nồi lẩu nước dùng xương bò hầm kỹ, kèm bò thái mỏng, bò viên, rau tươi và bún.", "Bone broth hotpot with thinly sliced beef, beef balls, fresh vegetables and noodles."))
                        .priceFrom(new BigDecimal("280000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "beef", "chaozhou"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Lẩu bò đặc biệt (4 người)", "Special beef hotpot (4 persons)"))
                        .description(of("Phần lẩu dành cho 4 người, nước dùng đậm đà, nhiều loại bò tươi và thịt kèm.", "Hotpot for 4, rich broth, various fresh beef cuts and accompaniments."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("700000"))
                        .unit(of("nồi", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("hotpot", "beef", "group"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("NUONG")
                .name(of("Món nướng", "Grilled dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Sườn bò nướng muối ớt", "Salt and chili grilled beef ribs"))
                        .description(of("Sườn bò tươi nướng trực tiếp trên than hoa, ướp gia vị muối ớt đặc trưng.", "Fresh beef ribs grilled over charcoal, seasoned with signature salt and chili."))
                        .priceFrom(new BigDecimal("120000"))
                        .priceTo(new BigDecimal("180000"))
                        .unit(of("phần", "serving"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("grilled", "beef", "ribs"))
                        .build()
                ))
                .build()
        );

        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi giữ xe gần nhà hàng trên phố Phan Chu Trinh", "Nearby parking lot on Phan Chu Trinh street"))
            .motorbikeParkingFeeType(ParkingFeeType.PAID)
            .motorbikeParkingFeeAmount(new BigDecimal("5000"))
            .notes(of("Khu phố cổ Hoàn Kiếm, xe máy gửi bãi gần. Ô tô đỗ tại bãi công cộng phố lân cận.", "Old Quarter Hoan Kiem area, motorbikes park at nearby lot. Cars use nearby public parking."))
            .build();

        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "PRIVATE_ROOM",
            "WIFI",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "EVENT_DECOR",
            "BIRTHDAY_CAKE",
            "VAT_INVOICE",
            "VISA_MASTER",
            "KIDS_CHAIR",
            "SET_LUNCH",
            "TAKEAWAY",
            "LIVE_COOKING"
        );

        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of(
                "Giới thiệu Lẩu Bò Triều Châu Phan Chu Trinh",
                "About Lau Bo Trieu Chau Phan Chu Trinh")),
            paragraph(ContentSection.STORY, of(
                "Lẩu Bò Triều Châu Số 1 Phan Chu Trinh là nhà hàng chuyên ẩm thực Triều Châu (Trung Hoa) lâu đời tại Hà Nội, " +
                "tọa lạc ngay trung tâm phố Phan Chu Trinh, phường Cửa Nam, quận Hoàn Kiếm. Nhà hàng nổi tiếng với những nồi lẩu bò " +
                "nấu từ xương bò hầm kỹ 8 tiếng cùng thịt bò tươi đặt hàng trực tiếp từ lò mổ mỗi ngày.",
                "Lau Bo Trieu Chau No. 1 Phan Chu Trinh is a long-established Chaozhou (Chinese) cuisine restaurant in Hanoi, " +
                "located in the heart of Phan Chu Trinh street, Cua Nam ward, Hoan Kiem district. The restaurant is renowned for " +
                "its beef hotpots made from 8-hour simmered beef bone broth and fresh beef sourced directly from the slaughterhouse daily.")),
            heading(ContentSection.STORY, of(
                "Không gian & sức chứa",
                "Space & capacity")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng sở hữu không gian ẩm thực rộng rãi với sức chứa lên đến 300 thực khách, bao gồm khu vực sảnh chính " +
                "máy lạnh thoải mái và 5 phòng riêng biệt phù hợp cho các nhóm từ 10 đến 50 người. Phong cách trang trí mang đậm " +
                "nét văn hóa Triều Châu với các họa tiết truyền thống, tạo không khí đặc trưng và ấn tượng ngay từ lần đầu ghé thăm.",
                "The restaurant boasts a spacious dining area with a capacity of up to 300 guests, including a comfortable air-conditioned " +
                "main hall and 5 private rooms accommodating groups of 10 to 50 people. The decor carries strong Chaozhou cultural elements " +
                "with traditional motifs, creating a distinctive and memorable atmosphere from the first visit.")),
            heading(ContentSection.STORY, of(
                "Thực đơn & món đặc sắc",
                "Menu & signature dishes")),
            paragraph(ContentSection.STORY, of(
                "Điểm nổi bật của Lẩu Bò Triều Châu là thực đơn phong phú xoay quanh thịt bò tươi được chế biến đa dạng. " +
                "Nước dùng lẩu được hầm từ xương bò nguyên chất trong 8 tiếng, ngọt tự nhiên không cần bột ngọt. " +
                "Các phần thịt bò thái lát mỏng, bò viên thủ công, gân bò hầm mềm và nội tạng bò tươi sạch là những món khách " +
                "quen không thể bỏ qua. Ngoài lẩu, nhà hàng còn phục vụ các món nướng trên than hoa như sườn bò, lưỡi bò, tim bò " +
                "ướp gia vị Triều Châu đặc trưng.",
                "The highlight of Lau Bo Trieu Chau is the diverse menu centered around fresh beef. The hotpot broth is made from " +
                "pure beef bones simmered for 8 hours, naturally sweet without MSG. Thinly sliced beef, handmade beef balls, braised " +
                "tendons and fresh offal are favorites that regulars cannot miss. Besides hotpot, the restaurant also serves charcoal-grilled " +
                "dishes such as beef ribs, beef tongue, and beef heart marinated in signature Chaozhou spices.")),
            heading(ContentSection.STORY, of(
                "Phù hợp dịp nào",
                "Suitable occasions")),
            paragraph(ContentSection.STORY, of(
                "Với mức giá 250.000 – 500.000đ/người, Lẩu Bò Triều Châu phù hợp cho nhiều dịp: từ bữa ăn tối gia đình, tụ tập " +
                "bạn bè đến liên hoan văn phòng, tổ chức sinh nhật hay tiếp đón khách hàng. Hệ thống phòng riêng với âm thanh ánh " +
                "sáng và dịch vụ MC cho phép tổ chức các sự kiện vừa và nhỏ ngay tại nhà hàng.",
                "With a price range of 250,000 – 500,000 VND/person, Lau Bo Trieu Chau is suitable for many occasions: from family " +
                "dinners and friends gatherings to office parties, birthday celebrations or business entertainment. The private room system " +
                "with sound, lighting and MC service allows organizing small to medium events right at the restaurant.")),
            heading(ContentSection.STORY, of(
                "Dịch vụ & tiện ích nổi bật",
                "Notable services & amenities")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng cung cấp đầy đủ dịch vụ từ đặt tiệc sinh nhật (kèm bánh kem), trang trí sự kiện, chiếu phim/trình chiếu " +
                "đến hóa đơn VAT theo yêu cầu và thanh toán đa dạng. Đội ngũ phục vụ chuyên nghiệp, thông thạo tiếng Anh cơ bản, " +
                "sẵn sàng hỗ trợ thực khách nước ngoài trong khu vực phố cổ Hoàn Kiếm.",
                "The restaurant offers full services from birthday party arrangements (with birthday cake), event decoration, " +
                "projector/presentation to VAT invoices on request and versatile payment options. The professional service team with " +
                "basic English is ready to assist foreign guests in the Hoan Kiem Old Quarter area.")),
            heading(ContentSection.STORY, of(
                "Vị trí & di chuyển",
                "Location & getting there")),
            paragraph(ContentSection.STORY, of(
                "Nằm tại số 33 Phan Chu Trinh, phường Cửa Nam, quận Hoàn Kiếm, nhà hàng chỉ cách Hồ Hoàn Kiếm khoảng 500m và " +
                "Nhà hát Lớn Hà Nội khoảng 300m. Vị trí đắc địa giữa trung tâm thành phố, dễ dàng di chuyển bằng taxi, Grab " +
                "hoặc đi bộ từ các khách sạn khu phố cổ.",
                "Located at No. 33 Phan Chu Trinh, Cua Nam ward, Hoan Kiem district, the restaurant is only about 500m from Hoan Kiem Lake " +
                "and 300m from the Hanoi Opera House. The prime location in the city center is easily accessible by taxi, Grab " +
                "or on foot from hotels in the Old Quarter.")),
            heading(ContentSection.STORY, of(
                "Chính sách đặt bàn & lý do nên chọn",
                "Booking policy & why choose us")),
            paragraph(ContentSection.STORY, of(
                "Nhà hàng nhận đặt bàn trước qua điện thoại hoặc hệ thống online, có chính sách đặt cọc rõ ràng cho nhóm đông và " +
                "yêu cầu tổ chức tiệc. Bằng công thức nước dùng gia truyền Triều Châu và nguyên liệu bò tươi mỗi ngày, Lẩu Bò Triều " +
                "Châu Phan Chu Trinh xứng đáng là lựa chọn hàng đầu cho những ai muốn thưởng thức lẩu bò chính thống tại Hà Nội.",
                "The restaurant accepts advance bookings by phone or online system, with clear deposit policies for large groups and " +
                "party arrangements. With the family-recipe Chaozhou broth and daily fresh beef, Lau Bo Trieu Chau Phan Chu Trinh " +
                "deserves to be the top choice for those who want to enjoy authentic beef hotpot in Hanoi.")),
            mapBlock(ContentSection.STORY, new double[]{105.8635, 21.0031})
        );

        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "No promotion on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "No promotion on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "No promotion on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "No promotion on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "No promotion on Sep 2")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi ngày 31/12", "No promotion on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("500000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách hoặc yêu cầu đặt phòng riêng.", "Applies to bookings of 8 or more guests or private room requests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% khi đặt qua hệ thống, không áp dụng ngày lễ tết.", "10% discount when booking online, not applicable on holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi liên hệ nhà hàng.", "Please contact the restaurant for promotion details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoice available on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ phí dịch vụ 5% áp dụng tại phòng riêng.", "5% service charge in private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang đồ uống (rượu, bia) từ ngoài vào, có phụ phí.", "Outside drinks allowed with corkage fee."))
            .outsideDrinkFees(List.of(
                OutsideDrinkFee.builder()
                    .drinkType("WINE")
                    .feeAmount(new BigDecimal("150000"))
                    .currencyCode("VND")
                    .note(of("Phụ phí rượu vang 150.000đ/chai.", "Wine corkage fee 150,000 VND/bottle."))
                    .build()
            ))
            .build();

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

        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 ghế", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn phù hợp gia đình nhỏ hoặc nhóm bạn.", "Suitable for small families or friend groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(4)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP khu yên tĩnh, ưu tiên phục vụ.", "VIP table in quiet area, priority service."))
                .build(),
            TableType.builder()
                .id("pr-50")
                .name(of("Phòng riêng tối đa 50 khách", "Private room up to 50 guests"))
                .seats(50)
                .minPeople(15)
                .maxPeople(50)
                .totalTables(5)
                .depositPrice(new BigDecimal("2000000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng đầy đủ âm thanh ánh sáng, phù hợp tiệc công ty và sinh nhật.", "Private room with full sound and lighting, ideal for company parties and birthdays."))
                .build()
        );

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

        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Hoàn Kiếm", "Hoan Kiem District"))
            .wardName(of("Phường Cửa Nam", "Cua Nam Ward"))
            .addressLine(of("Số 33 Phan Chu Trinh, Phường Cửa Nam, Quận Hoàn Kiếm, Hà Nội", "No. 33 Phan Chu Trinh, Cua Nam Ward, Hoan Kiem District, Hanoi"))
            .location(new double[]{105.8635, 21.0031})
            .name(of("Lẩu Bò Triều Châu Số 1 Phan Chu Trinh", "Lau Bo Trieu Chau No. 1 Phan Chu Trinh"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Chuyên lẩu bò Triều Châu nước dùng xương hầm 8 tiếng và bò tươi thái hàng ngày, giữa lòng Hoàn Kiếm.", "Specialty Chaozhou beef hotpot with 8-hour bone broth and daily fresh beef, in the heart of Hoan Kiem."))
            .description(of(
                "Lẩu Bò Triều Châu Số 1 Phan Chu Trinh là địa chỉ ẩm thực Triều Châu uy tín tại Hà Nội với công thức nước dùng " +
                "gia truyền hầm xương bò 8 tiếng và thịt bò tươi sạch nhập mỗi ngày. Sức chứa 300 khách với 5 phòng riêng " +
                "đủ tiện nghi phục vụ từ bữa cơm gia đình đến tiệc liên hoan công ty.",
                "Lau Bo Trieu Chau No. 1 Phan Chu Trinh is a reputable Chaozhou cuisine address in Hanoi with a family-recipe " +
                "8-hour beef bone broth and daily fresh beef. Seating 300 guests across 5 private rooms, it serves " +
                "everything from family meals to company banquets."))
            .phone("0931006005")
            .email("reservation@laubotrieuchau.vn")
            .website("https://pasgo.vn/nha-hang/lau-bo-trieu-chau-so-1-phan-chu-trinh")
            .bookingHotline("0931006005")
            .minPricePerPerson(new BigDecimal("250000"))
            .maxPricePerPerson(new BigDecimal("500000"))
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
