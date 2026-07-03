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
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedLauNuongGangBukTruongChinh {

    @Bean
    CommandLineRunner seedLauNuongGangBukTruongChinhRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedLauNuongGangBukTruongChinh] start");
            RestaurantDoc doc = restaurantLauNuongGangBukTruongChinh();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedLauNuongGangBukTruongChinh] done");
        };
    }

    // =====================================================================
    //    LẨU NƯỚNG GANGBUK - TRƯỜNG CHINH (383 Trường Chinh, Thanh Xuân)
    // =====================================================================

    private static RestaurantDoc restaurantLauNuongGangBukTruongChinh() {
        String slug = "lau-nuong-gangbuk-truong-chinh";

        // ---------- Gallery ảnh nhà hàng ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-1-normal-2184161052189.webp",
                of("Lẩu Nướng GangBuk - Không gian kiến trúc Hội An đặc trưng", "GangBuk Hot Pot & BBQ - Hoi An-style architecture"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-2-normal-2184160852190.webp",
                of("Khu vực lẩu nướng rộng rãi", "Spacious hotpot and BBQ area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-3-normal-2184160652191.webp",
                of("Trang trí nội thất phong cách Hội An", "Hoi An-style interior decor"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-4-normal-2184160552192.webp",
                of("Bàn lẩu nướng chuẩn Hàn", "Authentic Korean hotpot and BBQ table"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-5-normal-2184160452193.webp",
                of("Quầy thực phẩm buffet phong phú", "Rich buffet food counter"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-6-normal-2184160252194.webp",
                of("Không gian ăn uống ấm cúng", "Cozy dining space"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-7-normal-2184160152195.webp",
                of("Khu vực tiệc nhóm", "Group party area"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-lau-nuong-gangbuk-tran-dai-nghia-8-normal-2184158352196.webp",
                of("Chi tiết trang trí đèn lồng Hội An", "Hoi An lantern decoration detail"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gangbuk-truong-chinh-slide-4-normal-2184160052781.webp",
                of("GangBuk Trường Chinh - Không gian thoáng rộng", "GangBuk Truong Chinh - Open spacious space"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gangbuk-truong-chinh-slide-1-normal-2184159952782.webp",
                of("Slide ảnh GangBuk Trường Chinh 1", "GangBuk Truong Chinh slide 1"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gangbuk-truong-chinh-slide-2-normal-2184161152783.webp",
                of("Slide ảnh GangBuk Trường Chinh 2", "GangBuk Truong Chinh slide 2"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-gangbuk-truong-chinh-slide-3-normal-2184161452784.webp",
                of("Slide ảnh GangBuk Trường Chinh 3", "GangBuk Truong Chinh slide 3"), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh.jpg",
                of("GangBuk Trường Chinh - Ảnh thực tế", "GangBuk Truong Chinh - Actual photo"), false, 12),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-1.jpg",
                of("", ""), false, 13),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-2.jpg",
                of("", ""), false, 14),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-3.jpg",
                of("", ""), false, 15),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-4.jpg",
                of("", ""), false, 16),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-5.jpg",
                of("", ""), false, 17),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-6.jpg",
                of("", ""), false, 18),
            img("https://cdn.pastaxi-manager.onepas.vn/content/uploads/articles/lanmkt/Gianhang/gangbuktruongchinh/nha-hang-gangbuk-truong-chinh-7.jpg",
                of("", ""), false, 19)
        );

        // ---------- Menu images (bảng giá) ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/tramy/gangbuk-buffet-menu-203.jpg",
                of("Bảng giá Buffet GangBuk Trường Chinh", "GangBuk Truong Chinh buffet price list"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/nguyenhuong/gangbukt3/gang-buk-3.jpg",
                of("Menu GangBuk - Trang giá món", "GangBuk menu - Dish price page"), false, 1)
        );

        // ---------- Tag ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("KOREAN_HOTPOT_BBQ")
                .name(of("Lẩu Nướng Hàn Quốc", "Korean Hotpot & BBQ"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("BUFFET_KOREAN")
                .name(of("Buffet Hàn Quốc", "Korean Buffet"))
                .region("ASIA")
                .build(),
            CuisineTag.builder()
                .code("ASIAN")
                .name(of("Châu Á", "Asian"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa (11:00-14:00 và 18:00-23:00 mỗi ngày) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    11, 0, 23, 0),
            openingHour(DayOfWeek.TUESDAY,   11, 0, 23, 0),
            openingHour(DayOfWeek.WEDNESDAY, 11, 0, 23, 0),
            openingHour(DayOfWeek.THURSDAY,  11, 0, 23, 0),
            openingHour(DayOfWeek.FRIDAY,    11, 0, 23, 0),
            openingHour(DayOfWeek.SATURDAY,  11, 0, 23, 0),
            openingHour(DayOfWeek.SUNDAY,    11, 0, 23, 0)
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
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("ANNIVERSARY",   of("Kỷ niệm ngày đặc biệt", "Special anniversary"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",    of("Mộc mạc phong cách Hội An", "Rustic Hoi An style")),
            ambience("COZY",      of("Ấm cúng", "Cozy")),
            ambience("VIBRANT",   of("Sôi động", "Vibrant")),
            ambience("MODERN",    of("Hiện đại", "Modern"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(200)
            .maxGroupSize(40)
            .hasPrivateRooms(true)
            .privateRoomCount(2)
            .maxPrivateRoomCapacity(40)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Lẩu thập cẩm Hàn Quốc", "Korean assorted hotpot")),
            signatureDishL(of("Thịt ba chỉ Hàn nướng (Samgyeopsal)", "Korean pork belly BBQ (Samgyeopsal)")),
            signatureDishL(of("Bò Mỹ nướng lửa than", "US beef grilled over charcoal")),
            signatureDishL(of("Hải sản tươi nướng tự chọn", "Fresh seafood BBQ self-select")),
            signatureDishL(of("Kim chi tự làm", "Homemade kimchi")),
            signatureDishL(of("Canh kimchi đậu hũ Hàn Quốc", "Korean kimchi tofu soup")),
            signatureDishL(of("Tokbokki sốt cay ngọt", "Spicy-sweet tteokbokki")),
            signatureDishL(of("Gà nướng ướp sa tế", "Satay marinated grilled chicken")),
            signatureDishL(of("Đùi bò nướng lá lốt", "Beef shank grilled with betel leaves")),
            signatureDishL(of("Kem mochi tráng miệng", "Mochi ice cream dessert"))
        );

        // ---------- Menu sections ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_LAU")
                .name(of("Buffet Lẩu", "Hotpot Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Lẩu", "Hotpot Buffet"))
                        .description(of("Buffet lẩu không giới hạn với đa dạng loại nước lẩu và nguyên liệu tươi.",
                            "Unlimited hotpot buffet with various broth options and fresh ingredients."))
                        .priceFrom(new BigDecimal("179000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("hotpot", "buffet"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("BUFFET_NUONG")
                .name(of("Buffet Nướng", "BBQ Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Buffet Nướng", "BBQ Buffet"))
                        .description(of("Buffet nướng không giới hạn thịt heo, bò, gà và hải sản.",
                            "Unlimited BBQ buffet with pork, beef, chicken and seafood."))
                        .priceFrom(new BigDecimal("199000"))
                        .priceTo(new BigDecimal("199000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("bbq", "buffet"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo Buffet Nướng + Lẩu", "Combo BBQ + Hotpot Buffet"))
                        .description(of("Kết hợp cả buffet nướng lẫn lẩu không giới hạn trong một combo tiết kiệm.",
                            "Combine unlimited BBQ buffet and hotpot in one economical combo."))
                        .priceFrom(new BigDecimal("239000"))
                        .priceTo(new BigDecimal("267000"))
                        .unit(of("người", "person"))
                        .combo(true)
                        .buffetItem(true)
                        .tags(List.of("bbq", "hotpot", "combo", "buffet"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe trước tòa nhà và các bãi đỗ xe công cộng lân cận", "Parking lot in front of the building and nearby public parking areas"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè trước nhà hàng và bãi xe máy lân cận", "Sidewalk in front of the restaurant and nearby motorbike parking"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi miễn phí tại khu vực trước nhà hàng; ô tô gửi các bãi gần 383 Trường Chinh.",
                "Free motorbike parking in front of the restaurant; cars park in nearby lots around 383 Truong Chinh."))
            .build();

        // ---------- Amenity codes ----------
        List<String> amenityCodes = List.of(
            "AIR_CONDITIONING",
            "WIFI",
            "PRIVATE_ROOM",
            "SOUND_LIGHT",
            "PROJECTOR",
            "MC_SERVICE",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "KIDS_CHAIR",
            "FREE_MOTORBIKE_PARKING",
            "BIRTHDAY_DECOR",
            "SMOKING_AREA",
            "SET_LUNCH",
            "KARAOKE_ROOM"
        );

        // ---------- Content dài ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Tổng quan Lẩu Nướng GangBuk - Trường Chinh", "GangBuk Hot Pot & BBQ Truong Chinh - Overview")),
            paragraph(ContentSection.STORY,
                of("Lẩu Nướng GangBuk - Trường Chinh tọa lạc tại số 383 Trường Chinh, phường Khương Trung, quận Thanh Xuân, Hà Nội. " +
                   "Nhà hàng thuộc chuỗi GangBuk nổi tiếng với phong cách kết hợp lẩu và nướng Hàn Quốc theo hình thức buffet all-in-one, " +
                   "mang lại trải nghiệm ẩm thực đa dạng với giá cả phải chăng ngay tại khu dân cư đông đúc quận Thanh Xuân.",
                   "GangBuk Hot Pot & BBQ Truong Chinh is located at 383 Truong Chinh, Khuong Trung ward, Thanh Xuan district, Hanoi. " +
                   "The restaurant belongs to the famous GangBuk chain, known for combining Korean hotpot and BBQ in an all-in-one buffet format, " +
                   "offering a diverse dining experience at affordable prices in the densely populated Thanh Xuan district.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("GangBuk Trường Chinh nổi bật với kiến trúc nội thất phong cách Hội An độc đáo - sự kết hợp hài hòa giữa " +
                   "vẻ đẹp truyền thống Việt Nam và phong cách ẩm thực Hàn Quốc hiện đại. Nhà hàng có sức chứa lên đến 200 khách " +
                   "với nhiều khu vực bàn lẩu nướng được bố trí khoa học. Hai phòng VIP riêng biệt phù hợp cho nhóm khách từ 15-40 người " +
                   "cần không gian riêng tư cho các bữa tiệc và sự kiện.",
                   "GangBuk Truong Chinh stands out with its unique Hoi An-style interior architecture - a harmonious blend of " +
                   "Vietnamese traditional beauty and modern Korean culinary style. The restaurant accommodates up to 200 guests " +
                   "with scientifically arranged hotpot and BBQ tables. Two separate VIP rooms suit groups of 15-40 people " +
                   "needing privacy for parties and events.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("GangBuk tự hào với thực đơn buffet đầy đặn bao gồm cả lẩu và nướng trong một gói. Các loại nước lẩu đa dạng " +
                   "từ lẩu thập cẩm, lẩu kim chi đến lẩu tomyum. Phần nướng nổi bật với ba chỉ Hàn (Samgyeopsal), bò Mỹ, gà ướp gia vị " +
                   "và hải sản tươi. Các món ăn kèm truyền thống Hàn như kim chi tự làm, canh kimchi, tokbokki cay ngọt được phục vụ " +
                   "không giới hạn trong suốt bữa ăn.",
                   "GangBuk prides itself on a full buffet menu combining both hotpot and BBQ in one package. Diverse broth options " +
                   "range from assorted hotpot, kimchi hotpot to tomyum. The BBQ highlights include Samgyeopsal (Korean pork belly), " +
                   "US beef, marinated chicken and fresh seafood. Traditional Korean side dishes such as homemade kimchi, kimchi soup " +
                   "and spicy-sweet tteokbokki are served unlimited throughout the meal.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá buffet từ 179.000đ - 267.000đ/người, GangBuk Trường Chinh là lựa chọn lý tưởng cho bữa ăn gia đình, " +
                   "tụ tập bạn bè, liên hoan văn phòng hay ăn trưa công ty. Giá cả phải chăng kết hợp với thực đơn phong phú khiến " +
                   "nhà hàng trở thành điểm đến ăn uống thường xuyên của cư dân quận Thanh Xuân và các khu vực lân cận.",
                   "With buffet prices from 179,000 to 267,000 VND/person, GangBuk Truong Chinh is an ideal choice for family meals, " +
                   "friends gatherings, office parties or company lunches. Affordable prices combined with a rich menu make the restaurant " +
                   "a regular dining destination for Thanh Xuan district residents and surrounding areas.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích nổi bật", "Services & key amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng cung cấp dịch vụ tổ chức tiệc sinh nhật, liên hoan với trang trí theo yêu cầu và hỗ trợ MC. " +
                   "Hệ thống âm thanh ánh sáng chuyên nghiệp phục vụ các sự kiện doanh nghiệp. Nhân viên được đào tạo nhiệt tình, " +
                   "phục vụ tận bàn, thay than và bổ sung nguyên liệu liên tục trong suốt bữa ăn. " +
                   "Nhà hàng có phòng karaoke riêng và phòng VIP cho các nhóm khách có nhu cầu đặc biệt.",
                   "The restaurant provides birthday party and event organizing services with custom decorations and MC support. " +
                   "Professional sound and lighting systems serve corporate events. Staff are trained to be enthusiastic, " +
                   "providing table-side service, charcoal replacement, and continuous ingredient replenishment throughout the meal. " +
                   "The restaurant has a dedicated karaoke room and VIP rooms for groups with special requirements.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại số 383 Trường Chinh, phường Khương Trung, quận Thanh Xuân, nhà hàng nằm trên trục đường Trường Chinh " +
                   "huyết mạch của Hà Nội, rất dễ dàng tiếp cận bằng phương tiện cá nhân hay phương tiện công cộng. " +
                   "Cách ga Hà Nội khoảng 5km, gần các khu chung cư lớn như Linh Đàm, Định Công và khu dân cư quận Đống Đa.",
                   "Located at 383 Truong Chinh, Khuong Trung ward, Thanh Xuan district, the restaurant sits on the key Truong Chinh artery of Hanoi, " +
                   "easily accessible by private vehicle or public transport. " +
                   "About 5km from Hanoi Station, close to major residential complexes like Linh Dam, Dinh Cong and Dong Da district.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn GangBuk", "Booking policy & why choose GangBuk")),
            paragraph(ContentSection.STORY,
                of("Đặt bàn trước giúp đảm bảo chỗ ngồi vào khung giờ mong muốn, đặc biệt trong khung giờ tối cuối tuần luôn kín bàn. " +
                   "GangBuk Trường Chinh áp dụng chính sách đặt cọc linh hoạt và nhiều gói ưu đãi cho khách đặt bàn online. " +
                   "Sự kết hợp độc đáo giữa lẩu và nướng trong một buffet với giá tầm trung là lý do GangBuk trở thành " +
                   "nhà hàng yêu thích của hàng nghìn thực khách tại Hà Nội.",
                   "Advance booking ensures seating at your preferred time, especially weekend evenings which are always fully booked. " +
                   "GangBuk Truong Chinh applies a flexible deposit policy and many promotional packages for online bookings. " +
                   "The unique combination of hotpot and BBQ in one mid-range buffet is why GangBuk has become " +
                   "a favorite restaurant for thousands of diners in Hanoi.")),
            mapBlock(ContentSection.STORY, new double[]{105.821078007301, 21.0026087062954})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày 14/2", "Promotion not applicable on Feb 14")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày 1/5", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi ngày 24/12", "Promotion not applicable on Dec 24")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(6)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 6 khách trở lên hoặc đặt phòng VIP.",
                "Applies to bookings of 6 or more guests or VIP room reservations."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi dành cho khách đặt bàn online, không áp dụng vào ngày lễ tết.",
                "Promotions apply to online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi theo từng thời điểm, vui lòng kiểm tra khi đặt bàn.",
                "Promotion details vary by time; please check when booking."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(20)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Nhà hàng xuất hóa đơn VAT theo yêu cầu.", "VAT invoices issued on request."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phí phục vụ 5% áp dụng cho phòng VIP.", "5% service charge applies to VIP rooms."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không cho phép mang thức ăn từ bên ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Cho phép mang đồ uống từ ngoài, có phụ phí theo loại.", "Outside drinks allowed with corkage fee by type."))
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
                .name(of("Bàn 4 ghế tiêu chuẩn", "Standard table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(25)
                .depositPrice(new BigDecimal("150000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lẩu nướng tiêu chuẩn phù hợp nhóm nhỏ.", "Standard hotpot/BBQ table suitable for small groups."))
                .build(),
            TableType.builder()
                .id("tb-8-vip")
                .name(of("Bàn VIP 8 ghế", "VIP table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn VIP vị trí đẹp, phục vụ ưu tiên.", "VIP table with prime location, priority service."))
                .build(),
            TableType.builder()
                .id("pr-40-private")
                .name(of("Phòng riêng 40 khách", "Private room for 40 guests"))
                .seats(40)
                .minPeople(15)
                .maxPeople(40)
                .totalTables(2)
                .depositPrice(new BigDecimal("1500000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(120))
                .note(of("Phòng riêng phù hợp tiệc sinh nhật, liên hoan công ty.", "Private room suitable for birthday parties and office parties."))
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
            .destinationSlug("ha-noi")
            .cityName(of("Hà Nội", "Hanoi"))
            .districtName(of("Quận Thanh Xuân", "Thanh Xuan District"))
            .wardName(of("P. Khương Trung", "Khuong Trung Ward"))
            .addressLine(of("383 Trường Chinh, P. Khương Trung, Q. Thanh Xuân, Hà Nội",
                "383 Truong Chinh, Khuong Trung Ward, Thanh Xuan District, Hanoi"))
            .location(new double[]{105.821078007301, 21.0026087062954})
            .name(of("Lẩu Nướng GangBuk - Trường Chinh", "GangBuk Hot Pot & BBQ - Truong Chinh"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET_VA_GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Buffet lẩu nướng Hàn Quốc giá tầm trung, không gian kiến trúc Hội An độc đáo tại 383 Trường Chinh.",
                "Mid-range Korean hotpot & BBQ buffet with unique Hoi An-style architecture at 383 Truong Chinh."))
            .description(of("Lẩu Nướng GangBuk - Trường Chinh là nhà hàng buffet lẩu và nướng Hàn Quốc nằm tại 383 Trường Chinh, " +
                "quận Thanh Xuân, Hà Nội. Nổi bật với kiến trúc Hội An độc đáo, thực đơn buffet đầy đặn kết hợp lẩu và nướng không giới hạn " +
                "với giá từ 179.000đ/người, nhà hàng là lựa chọn yêu thích cho bữa ăn gia đình, tụ tập bạn bè và tiệc công ty.",
                "GangBuk Hot Pot & BBQ Truong Chinh is a Korean hotpot and BBQ buffet restaurant at 383 Truong Chinh, " +
                "Thanh Xuan district, Hanoi. Known for its unique Hoi An architecture, rich buffet menu combining unlimited hotpot and BBQ " +
                "from 179,000 VND/person, the restaurant is a favorite choice for family meals, friends gatherings and office parties."))
            .phone("0329601783")
            .email("reservation@gangbuk.vn")
            .website("https://pasgo.vn/nha-hang/gangbuk-truong-chinh")
            .facebookPage("https://facebook.com/gangbukvietnam")
            .bookingHotline("19006005")
            .minPricePerPerson(new BigDecimal("179000"))
            .maxPricePerPerson(new BigDecimal("267000"))
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
