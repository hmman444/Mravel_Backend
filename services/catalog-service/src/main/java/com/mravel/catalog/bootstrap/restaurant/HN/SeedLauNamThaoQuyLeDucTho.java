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
public class SeedLauNamThaoQuyLeDucTho {

    @Bean
    CommandLineRunner seedLauNamThaoQuyLeDucThoRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauNamThaoQuyLeDucTho] start");

            RestaurantDoc doc = restaurantLauNamThaoQuyLeDucTho();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedLauNamThaoQuyLeDucTho] done");
        };
    }

    // =====================================================================
    //       LẨU NẤM THẢO QUÝ - LÊ ĐỨC THỌ (NAM TỪ LIÊM, HÀ NỘI)
    // =====================================================================

    private static RestaurantDoc restaurantLauNamThaoQuyLeDucTho() {
        String slug = "lau-nam-thao-quy-le-duc-tho";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-1-normal-130811914961.webp",
                of("Không gian Lẩu Nấm Thảo Quý - Lê Đức Thọ", "Lau Nam Thao Quy Le Duc Tho space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-2-normal-130812014962.webp",
                of("Nội thất gỗ ấm cúng", "Warm wooden interior"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-3-normal-130812114963.webp",
                of("Khu vực bàn ăn chính", "Main dining area"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-4-normal-130812214964.webp",
                of("Phòng riêng hiện đại", "Modern private room"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-5-normal-130812314965.webp",
                of("Nồi lẩu nấm quý", "Premium mushroom hotpot"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-6-normal-130812414966.webp",
                of("Nguyên liệu nấm quý hiếm", "Rare precious mushroom ingredients"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-7-normal-130812514967.webp",
                of("Bàn tiệc nhóm lớn", "Large group dining table"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-8-normal-130812614968.webp",
                of("Khu vực trang trí", "Decorated area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-9-normal-130812714969.webp",
                of("Không gian bên trong nhà hàng", "Restaurant interior"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-10-normal-130812814970.webp",
                of("Bàn ăn gần cửa sổ", "Window-side dining table"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-11-normal-130812914971.webp",
                of("Không gian sang trọng", "Elegant space"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nam-thao-quy-le-duc-tho-slide-12-normal-130813014972.webp",
                of("Toàn cảnh nhà hàng", "Full restaurant view"), false, 11)
        );

        // ---------- Ảnh menu / bảng giá ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/1.jpg",
                of("Thực đơn trang 1", "Menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/2.jpg",
                of("Thực đơn trang 2", "Menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/3.jpg",
                of("Thực đơn trang 3", "Menu page 3"), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/4.jpg",
                of("Thực đơn trang 4", "Menu page 4"), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/5.jpg",
                of("Thực đơn trang 5", "Menu page 5"), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/6.jpg",
                of("Thực đơn trang 6", "Menu page 6"), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/7.jpg",
                of("Thực đơn trang 7", "Menu page 7"), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/launamthaoquy/8.jpg",
                of("Thực đơn trang 8", "Menu page 8"), false, 7)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("PREMIUM_MUSHROOM_HOTPOT")
                .name(of("Lẩu Nấm Quý", "Premium Mushroom Hotpot"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE_HOTPOT")
                .name(of("Lẩu Việt Nam", "Vietnamese Hotpot"))
                .region("NORTH")
                .build(),
            CuisineTag.builder()
                .code("HEALTH_CUISINE")
                .name(of("Ẩm thực dưỡng sinh", "Health Cuisine"))
                .region("NORTH")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    16, 0, 21, 30),
            openingHour(DayOfWeek.TUESDAY,   16, 0, 21, 30),
            openingHour(DayOfWeek.WEDNESDAY, 16, 0, 21, 30),
            openingHour(DayOfWeek.THURSDAY,  16, 0, 21, 30),
            openingHour(DayOfWeek.FRIDAY,    16, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 22, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 22, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("COUPLE",        of("Hẹn hò", "Date night")),
            suitable("BIRTHDAY",      of("Tổ chức sinh nhật", "Birthday party")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("COMPANY_EVENT", of("Tiệc công ty", "Company event")),
            suitable("BUSINESS_MEAL", of("Tiếp khách đối tác", "Business meal")),
            suitable("HEALTH_DINING", of("Ăn uống dưỡng sinh", "Health-conscious dining")),
            suitable("GROUP_MEETING", of("Họp nhóm", "Group meeting"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("COZY",     of("Ấm cúng, dung dị", "Cozy and homely")),
            ambience("RUSTIC",   of("Mộc mạc chất gỗ", "Rustic wooden style")),
            ambience("ELEGANT",  of("Nhẹ nhàng tinh tế", "Light and refined")),
            ambience("INTIMATE", of("Ấm áp thân mật", "Warm and intimate"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(120)
            .maxGroupSize(30)
            .hasPrivateRooms(true)
            .privateRoomCount(3)
            .maxPrivateRoomCapacity(30)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu nấm bụng dê", "Goat belly mushroom hotpot")),
            signatureDishL(of("Nấm tùng nhung Nhật Bản", "Japanese pine mushroom")),
            signatureDishL(of("Nấm vuốt hổ đen", "Black tiger claw mushroom")),
            signatureDishL(of("Nấm gan bò sữa", "Milk-fed beef liver mushroom")),
            signatureDishL(of("Hoa đông trùng hạ thảo", "Cordyceps flower")),
            signatureDishL(of("Nấm kê tùng hiếm", "Rare kê tùng mushroom")),
            signatureDishL(of("Bò Úc thái lát mỏng", "Thinly sliced Australian beef")),
            signatureDishL(of("Gà ác nhúng lẩu", "Black chicken in hotpot")),
            signatureDishL(of("Ba ba núi chế biến", "Mountain softshell turtle")),
            signatureDishL(of("Nước dùng xương ống hầm 8 tiếng", "8-hour bone marrow broth"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("PREMIUM_SET")
                .name(of("Set Lẩu Nấm Quý", "Premium Mushroom Hotpot Set"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set lẩu nấm quý 2 người", "Premium mushroom hotpot set for 2"))
                        .description(of("Nồi nước dùng xương ống + nấm quý thượng hạng + bò Úc + rau sạch + mì trứng.", "Bone broth + premium rare mushrooms + Australian beef + fresh greens + egg noodles."))
                        .priceFrom(new BigDecimal("200000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("premium", "mushroom", "hotpot", "set"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Set lẩu nấm dưỡng sinh đặc biệt", "Special health mushroom hotpot set"))
                        .description(of("Đông trùng hạ thảo + nấm bụng dê + gà ác + thuốc bắc theo lý thuyết âm dương.", "Cordyceps + goat belly mushroom + black chicken + traditional herbal medicine based on yin-yang theory."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("premium", "health", "herbal", "set"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("ADDITIONAL")
                .name(of("Nguyên liệu thêm", "Additional Ingredients"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Thêm bò Úc 100g", "Add Australian beef 100g"))
                        .description(of("Thịt bò Úc tươi thái lát mỏng, nhúng lẩu.", "Fresh Australian beef thinly sliced for hotpot."))
                        .priceFrom(new BigDecimal("80000"))
                        .priceTo(new BigDecimal("100000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("beef", "extra"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe gần nhà hàng trên phố Lê Đức Thọ", "Parking lot near the restaurant on Le Duc Tho street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè trước cửa nhà hàng", "Sidewalk parking in front of the restaurant"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy đỗ miễn phí trước cửa, ô tô gửi tại bãi xe trên phố Lê Đức Thọ.", "Free motorbike parking in front; cars use the parking lot on Le Duc Tho street."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "SMOKING_AREA",
            "PRIVATE_ROOM",
            "AIR_CONDITIONING",
            "EVENT_DECOR",
            "VISA_MASTER",
            "FREE_MOTORBIKE_PARKING",
            "WIFI",
            "VAT_INVOICE",
            "KIDS_CHAIR",
            "SOUND_LIGHT",
            "GROUP_BOOKING",
            "HERBAL_MENU",
            "BIRTHDAY_CAKE_SERVICE",
            "TAKEAWAY"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Tổng quan Lẩu Nấm Thảo Quý - Lê Đức Thọ", "Lau Nam Thao Quy Le Duc Tho Overview")),
            paragraph(ContentSection.STORY,
                of("Lẩu Nấm Thảo Quý - Lê Đức Thọ là nhà hàng chuyên lẩu nấm quý, tọa lạc tại Số 12 Lê Đức Thọ, Phường Mỹ Đình 2, Quận Nam Từ Liêm, Hà Nội. " +
                "Nhà hàng nổi tiếng với thực đơn đặc biệt lấy cảm hứng từ lý thuyết âm dương điều hòa, " +
                "sử dụng những loại nấm quý hiếm kết hợp cùng thảo dược thiên nhiên để tạo nên hương vị lẩu độc đáo, vừa ngon vừa bổ dưỡng cho sức khỏe.",
                "Lau Nam Thao Quy Le Duc Tho is a premium mushroom hotpot restaurant located at No. 12 Le Duc Tho, My Dinh 2 Ward, Nam Tu Liem District, Hanoi. " +
                "The restaurant is renowned for its special menu inspired by yin-yang harmony theory, " +
                "using rare mushroom varieties combined with natural medicinal herbs to create a uniquely flavored hotpot that is both delicious and highly nutritious.")),
            heading(ContentSection.STORY, of("Không gian & Sức chứa", "Space & Capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng gây ấn tượng với ngoại thất đỏ tươi nổi bật trên phố Lê Đức Thọ, nhưng bước vào bên trong là một không gian hoàn toàn đối lập: " +
                "dung dị, nhẹ nhàng với chất liệu gỗ làm chủ đạo, tạo cảm giác ấm áp, thân mật. " +
                "Nhà hàng sức chứa khoảng 120 khách, với 3 phòng riêng hiện đại đáp ứng nhóm từ 15-30 người, " +
                "lý tưởng cho các buổi họp mặt gia đình, bạn bè hay tiệc công ty nhỏ.",
                "The restaurant makes an impression with its bright red exterior on Le Duc Tho street, but stepping inside reveals a completely contrasting space: " +
                "simple and gentle with wood as the dominant material, creating a warm and intimate atmosphere. " +
                "The restaurant accommodates about 120 guests, with 3 modern private rooms for groups of 15-30 people, " +
                "ideal for family reunions, friend gatherings, or small corporate parties.")),
            heading(ContentSection.STORY, of("Thực đơn & Nấm Quý Hiếm", "Menu & Precious Mushrooms")),
            paragraph(ContentSection.STORY,
                of("Điểm đặc trưng của Lẩu Nấm Thảo Quý là thực đơn tuy không quá đa dạng nhưng cực kỳ chất lượng. " +
                "Chỉ một nồi nước dùng xương ống lợn, xương gà hầm theo công thức riêng, cùng các loại nấm quý như: " +
                "nấm bụng dê, nấm tùng nhung Nhật Bản, nấm vuốt hổ đen, nấm gan bò sữa, hoa đông trùng hạ thảo, nấm kê tùng. " +
                "Tất cả được chắt lọc theo bài thuốc cổ truyền, rừng biển hòa hợp, tạo nên vị lẩu ngọt thanh, bổ âm, thải độc tự nhiên.",
                "The hallmark of Lau Nam Thao Quy is a menu that, while not overly diverse, is exceptionally high in quality. " +
                "Just one pot of broth made from pork and chicken bones simmered by a proprietary recipe, alongside precious mushrooms such as: " +
                "goat belly mushroom, Japanese pine mushroom, black tiger claw mushroom, milk-fed beef liver mushroom, cordyceps flower, kê tùng mushroom. " +
                "Everything is carefully selected following traditional medicinal formulas harmonizing mountain and sea, creating a hotpot that is sweetly clean, nourishing, and naturally detoxifying.")),
            heading(ContentSection.STORY, of("Giá trị dinh dưỡng & Sức khỏe", "Nutritional Value & Health Benefits")),
            paragraph(ContentSection.STORY,
                of("Ẩm thực tại Lẩu Nấm Thảo Quý không chỉ để thưởng thức mà còn mang giá trị dưỡng sinh. " +
                "Các loại nấm quý được chọn lọc kỹ càng giàu protein, khoáng chất, chất chống oxy hóa, hỗ trợ tăng cường miễn dịch. " +
                "Đặc biệt, hoa đông trùng hạ thảo và nấm tùng nhung là những thành phần cao cấp, thường dùng trong y học cổ truyền để bổ thận, tráng dương, kháng ung thư.",
                "The cuisine at Lau Nam Thao Quy is not only for enjoyment but also carries health-preserving value. " +
                "The carefully selected precious mushrooms are rich in protein, minerals, and antioxidants, supporting immune system enhancement. " +
                "Notably, cordyceps flower and pine mushroom are premium ingredients commonly used in traditional medicine to nourish kidneys, invigorate yang, and provide anti-cancer benefits.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable Occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá 200.000 – 250.000đ/người, nhà hàng phù hợp với nhiều đối tượng thực khách Hà Thành. " +
                "Từ bữa cơm gia đình ấm áp cuối tuần, buổi hẹn hò lãng mạn, đến tụ tập bạn bè hay tổ chức tiệc sinh nhật nhỏ. " +
                "Đặc biệt, các gia đình quan tâm đến sức khỏe hay những người muốn thưởng thức ẩm thực dưỡng sinh đặc biệt sẽ rất phù hợp.",
                "With a price range of 200,000–250,000 VND/person, the restaurant suits many types of Hanoi diners. " +
                "From a warm family weekend dinner and a romantic date to a friend gathering or small birthday party. " +
                "In particular, health-conscious families or those seeking special nutritional cuisine will find it especially fitting.")),
            heading(ContentSection.STORY, of("Vị trí & Di chuyển", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng tọa lạc tại Số 12 Lê Đức Thọ, Phường Mỹ Đình 2, Quận Nam Từ Liêm – gần khu vực Mỹ Đình, sân vận động quốc gia và các khu đô thị lớn phía Tây Hà Nội. " +
                "Vị trí thuận tiện di chuyển từ trung tâm Hà Nội theo đường Phạm Hùng hoặc đường Lê Quang Đạo, " +
                "dễ nhận biết bởi ngoại thất đỏ tươi nổi bật trên đoạn phố.",
                "The restaurant is located at No. 12 Le Duc Tho, My Dinh 2 Ward, Nam Tu Liem District – near My Dinh area, the National Stadium, and major urban areas in western Hanoi. " +
                "Convenient to reach from central Hanoi via Pham Hung or Le Quang Dao streets, " +
                "easily recognizable by its bright red exterior on the street.")),
            heading(ContentSection.STORY, of("Dịch vụ & Chính sách đặt bàn", "Services & Booking Policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước qua điện thoại và hệ thống online. " +
                "Phòng riêng cần đặt trước ít nhất 2 giờ và có thể yêu cầu đặt cọc với nhóm từ 10 người trở lên. " +
                "Nhà hàng hỗ trợ trang trí sinh nhật theo yêu cầu, chấp nhận thanh toán Visa/Mastercard và xuất hóa đơn VAT cho doanh nghiệp.",
                "The restaurant accepts advance bookings by phone and online system. " +
                "Private rooms require booking at least 2 hours in advance and may require a deposit for groups of 10 or more. " +
                "The restaurant supports birthday decoration on request, accepts Visa/Mastercard payment, and issues VAT invoices for businesses."))
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày Tết Dương lịch", "Promotion not applicable on New Year's Day")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 24,of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
            blackoutGregorian(12, 31,of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(10)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking phòng riêng từ 10 khách trở lên.", "Applies to private room bookings of 10 or more guests."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi giảm giá khi đặt bàn qua hệ thống, không áp dụng ngày lễ tết.", "Discount promotion when booking via system; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi tùy thời điểm, vui lòng liên hệ nhà hàng.", "Promotion details vary; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% dịch vụ áp dụng cho phòng riêng.", "5% service charge applies to private rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không cho phép mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không cho phép mang đồ uống từ ngoài vào.", "Outside drinks are not allowed."))
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
                .id("tb-4-tq")
                .name(of("Bàn 4 người", "Table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(15)
                .depositPrice(new BigDecimal("0"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn thường, phù hợp cặp đôi/gia đình nhỏ.", "Standard table for couples or small families."))
                .build(),
            TableType.builder()
                .id("tb-6-tq")
                .name(of("Bàn 6 người", "Table for 6"))
                .seats(6)
                .minPeople(4)
                .maxPeople(6)
                .totalTables(8)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP cho nhóm bạn hoặc gia đình 4-6 người.", "VIP table for friend groups or families of 4-6."))
                .build(),
            TableType.builder()
                .id("pr-20-tq")
                .name(of("Phòng riêng 20 khách", "Private room for 20 guests"))
                .seats(20)
                .minPeople(10)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("800000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120, 180))
                .note(of("Phòng riêng hiện đại, điều hòa riêng, phù hợp tiệc nhỏ và họp.", "Modern private room with individual AC, suitable for small parties and meetings."))
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
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Nam Từ Liêm", "Nam Tu Liem District"))
            .wardName(of("P. Mỹ Đình 2", "My Dinh 2 Ward"))
            .addressLine(of("Số 12 Lê Đức Thọ, P. Mỹ Đình 2, Q. Nam Từ Liêm, Hà Nội", "No. 12 Le Duc Tho, My Dinh 2 Ward, Nam Tu Liem District, Hanoi"))
            .location(new double[]{105.769631, 21.031795})
            .name(of("Lẩu Nấm Thảo Quý - Lê Đức Thọ", "Lau Nam Thao Quy - Le Duc Tho"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Lẩu nấm quý hiếm dưỡng sinh – công thức âm dương cổ truyền, ngon miệng bổ dưỡng tại Mỹ Đình.", "Premium health-boosting mushroom hotpot – traditional yin-yang formula, delicious and nutritious at My Dinh."))
            .description(of("Lẩu Nấm Thảo Quý - Lê Đức Thọ là nhà hàng chuyên lẩu nấm quý dưỡng sinh tại Số 12 Lê Đức Thọ, Nam Từ Liêm, Hà Nội. " +
                "Nổi bật với thực đơn sử dụng các loại nấm hiếm quý như đông trùng hạ thảo, nấm bụng dê, nấm tùng nhung, " +
                "kết hợp theo lý thuyết âm dương y học cổ truyền, tạo nên nồi lẩu bổ dưỡng, tốt cho sức khỏe.",
                "Lau Nam Thao Quy Le Duc Tho is a premium health-boosting mushroom hotpot restaurant at No. 12 Le Duc Tho, Nam Tu Liem, Hanoi. " +
                "Outstanding for its menu using rare mushrooms such as cordyceps, goat belly mushroom, pine mushroom, " +
                "combined according to traditional medicine yin-yang theory, creating a nutritious hotpot beneficial to health."))
            .phone("19006005")
            .email("reservation@launamthaoquy.vn")
            .website("https://pasgo.vn/nha-hang/lau-nam-thao-quy-le-duc-tho-1739")
            .facebookPage("https://facebook.com/launamthaoquy")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("200000"))
            .maxPricePerPerson(new BigDecimal("250000"))
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
