package com.mravel.catalog.bootstrap.restaurant.DN;

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
public class SeedComNieuNhaDo2 {

    @Bean
    CommandLineRunner seedComNieuNhaDo2Runner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedComNieuNhaDo2] start");

            RestaurantDoc doc = restaurantComNieuNhaDo2();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedComNieuNhaDo2] done");
        };
    }

    // =====================================================================
    //       CƠM NIÊU NHÀ ĐỎ 2 — 86 NGUYỄN TRI PHƯƠNG, THANH KHÊ, ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantComNieuNhaDo2() {
        String slug = "com-nieu-nha-do-2-nguyen-tri-phuong";

        // ---------- Gallery ảnh nhà hàng (12 ảnh) ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-1-normal-264316323020.webp",
                of("Không gian Cơm Niêu Nhà Đỏ 2 Nguyễn Tri Phương", "Com Nieu Nha Do 2 restaurant space"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-2-normal-264316623021.webp",
                of("Khu vực ăn uống chính", "Main dining area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-3-normal-264317023022.webp",
                of("Không gian ấm cúng phong cách nhà đỏ", "Warm red-house style dining space"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-4-normal-264317123023.webp",
                of("Cơm niêu đất truyền thống", "Traditional clay pot rice"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-5-normal-264317223024.webp",
                of("Khu vực bàn ăn gia đình", "Family dining tables"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-6-normal-264317423025.webp",
                of("Các món ăn dân dã đặc trưng", "Homestyle Vietnamese dishes"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-7-normal-264317823026.webp",
                of("Cơm niêu vàng ruộm hấp dẫn", "Golden appetizing clay pot rice"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-8-normal-264317923027.webp",
                of("Không gian ban ngày thoáng sáng", "Bright daytime dining area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-9-normal-264318223028.webp",
                of("Bàn ăn nhóm bạn bè và gia đình", "Group dining for friends and family"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-10-normal-264318623029.webp",
                of("Trang trí nội thất đậm chất quê hương", "Rustic countryside interior decor"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-11-normal-264318723030.webp",
                of("Món cá thu kho niêu đặc sắc", "Specialty braised mackerel in clay pot"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/com-nieu-nha-do-2-nguyen-tri-phuong-slide-12-normal-264318823031.webp",
                of("Toàn cảnh nhà hàng Cơm Niêu Nhà Đỏ 2", "Com Nieu Nha Do 2 restaurant overview"), false, 11)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("CLAY_POT_RICE")
                .name(of("Cơm niêu đất", "Clay pot rice"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Món Việt truyền thống", "Traditional Vietnamese"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOMESTYLE")
                .name(of("Món ăn dân dã", "Homestyle Vietnamese cooking"))
                .region("CENTRAL")
                .build()
        );

        // ---------- Giờ mở cửa (buổi trưa 09:00-14:00 & buổi tối 16:30-21:00, cả tuần) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    9, 0,  14, 0),
            openingHour(DayOfWeek.MONDAY,    16, 30, 21, 0),
            openingHour(DayOfWeek.TUESDAY,   9, 0,  14, 0),
            openingHour(DayOfWeek.TUESDAY,   16, 30, 21, 0),
            openingHour(DayOfWeek.WEDNESDAY, 9, 0,  14, 0),
            openingHour(DayOfWeek.WEDNESDAY, 16, 30, 21, 0),
            openingHour(DayOfWeek.THURSDAY,  9, 0,  14, 0),
            openingHour(DayOfWeek.THURSDAY,  16, 30, 21, 0),
            openingHour(DayOfWeek.FRIDAY,    9, 0,  14, 0),
            openingHour(DayOfWeek.FRIDAY,    16, 30, 21, 0),
            openingHour(DayOfWeek.SATURDAY,  9, 0,  14, 0),
            openingHour(DayOfWeek.SATURDAY,  16, 30, 21, 0),
            openingHour(DayOfWeek.SUNDAY,    9, 0,  14, 0),
            openingHour(DayOfWeek.SUNDAY,    16, 30, 21, 0)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",        of("Ăn gia đình", "Family dining")),
            suitable("FRIENDS",       of("Tụ tập bạn bè", "Friends gathering")),
            suitable("OFFICE_LUNCH",  of("Ăn trưa văn phòng", "Office lunch")),
            suitable("COUPLE",        of("Hẹn hò", "Date")),
            suitable("BIRTHDAY",      of("Tiệc sinh nhật nhỏ", "Small birthday party")),
            suitable("GROUP_MEETING", of("Họp mặt nhóm", "Group meeting")),
            suitable("COMPANY_PARTY", of("Liên hoan văn phòng", "Office party")),
            suitable("ANNIVERSARY",   of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("BUSINESS_MEAL", of("Tiếp đối tác bình dân", "Casual business meal")),
            suitable("SOLO",          of("Ăn một mình", "Solo dining"))
        );

        // ---------- Không gian / ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("RUSTIC",      of("Mộc mạc dân dã", "Rustic and homey")),
            ambience("COZY",        of("Ấm cúng", "Cozy")),
            ambience("TRADITIONAL", of("Truyền thống Việt Nam", "Traditional Vietnamese")),
            ambience("VIBRANT",     of("Sôi động buổi trưa", "Lively at lunch"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(20)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(false)
            .build();

        // ---------- Món đặc sắc (10 món) ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDish(of("Cơm niêu đất truyền thống", "Traditional clay pot rice"),
                of("Cơm nấu trong niêu đất, vỏ cơm cháy vàng giòn thơm, bên trong dẻo mềm đặc trưng của miền Trung.",
                   "Rice cooked in clay pot with a crispy golden crust and soft fragrant interior, a Central Vietnamese specialty."),
                new BigDecimal("35000")),
            signatureDish(of("Cá thu kho niêu", "Braised mackerel in clay pot"),
                of("Cá thu tươi kho đậm đà với tiêu, ớt và nước mắm nguyên chất, nấu trong niêu đất chắc thịt ngọt bùi.",
                   "Fresh mackerel braised with pepper, chili and pure fish sauce in clay pot, firm and richly flavored."),
                new BigDecimal("55000")),
            signatureDish(of("Thịt kho trứng cút niêu", "Pork braised with quail eggs in clay pot"),
                of("Thịt ba chỉ kho tàu cùng trứng cút trong niêu đất, màu cánh gián bóng đẹp, vị ngọt đậm đà.",
                   "Pork belly braised with quail eggs in clay pot, glossy brown color, sweet and savory flavor."),
                new BigDecimal("45000")),
            signatureDish(of("Canh chua cá diếc", "Sour soup with crucian carp"),
                of("Canh chua đặc trưng miền Trung với cá diếc tươi, me, cà chua và rau thơm, vị chua thanh dịu.",
                   "Central Vietnamese sour soup with fresh crucian carp, tamarind, tomato and herbs, mildly tangy."),
                new BigDecimal("40000")),
            signatureDish(of("Rau muống xào tỏi", "Stir-fried morning glory with garlic"),
                of("Rau muống tươi xào tỏi phi thơm, giòn ngon đậm vị mắm miền Trung.",
                   "Fresh morning glory stir-fried with aromatic garlic, crispy and seasoned with Central Vietnamese fish sauce."),
                new BigDecimal("25000")),
            signatureDish(of("Đậu phụ nhồi thịt sốt cà chua", "Tofu stuffed with pork in tomato sauce"),
                of("Đậu phụ trắng nhồi thịt băm ướp gia vị, chiên vàng rồi sốt cà chua tươi thơm ngon.",
                   "White tofu stuffed with seasoned minced pork, fried golden then simmered in fresh tomato sauce."),
                new BigDecimal("35000")),
            signatureDish(of("Gà luộc mắm gừng", "Boiled chicken with ginger dipping sauce"),
                of("Gà ta luộc chuẩn vàng óng, da giòn thịt ngọt chấm mắm gừng đặc trưng Đà Nẵng.",
                   "Free-range chicken boiled to perfect golden skin, tender sweet meat served with Danang-style ginger fish sauce."),
                new BigDecimal("65000")),
            signatureDish(of("Cá bống kho tiêu", "Goby fish braised with pepper"),
                of("Cá bống đồng kho khô với tiêu xanh và đường phèn, vị cay thơm đặc sắc ăn cùng cơm niêu.",
                   "Dried braised goby fish with green pepper and rock sugar, aromatic spicy flavor perfect with clay pot rice."),
                new BigDecimal("50000")),
            signatureDish(of("Mực xào sa tế", "Squid stir-fried with sa te sauce"),
                of("Mực tươi xào sa tế đặc vị miền Trung, cay thơm béo ngậy, ăn kèm cơm niêu rất hợp.",
                   "Fresh squid stir-fried in Central Vietnamese sa te sauce, spicy aromatic and rich, excellent with clay pot rice."),
                new BigDecimal("60000")),
            signatureDish(of("Tráng miệng chè đậu xanh nước dừa", "Mung bean dessert soup with coconut milk"),
                of("Chè đậu xanh nấu nước dừa tươi, vị ngọt thanh mát lạnh, kết thúc bữa cơm nhà đỏ thêm trọn vẹn.",
                   "Mung bean dessert soup cooked in fresh coconut milk, sweetly refreshing, the perfect meal finale."),
                new BigDecimal("15000"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("COM_NIEU")
                .name(of("Cơm niêu đất & món kho", "Clay pot rice & braised dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Cơm niêu đất", "Clay pot rice"))
                        .description(of("Cơm nấu niêu đất, vỏ giòn thơm đặc trưng.", "Clay pot rice with crispy fragrant crust."))
                        .priceFrom(new BigDecimal("30000"))
                        .priceTo(new BigDecimal("40000"))
                        .unit(of("niêu", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("rice", "clay-pot"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Cá thu kho niêu", "Braised mackerel in clay pot"))
                        .description(of("Cá thu kho đậm đà trong niêu đất.", "Mackerel braised in clay pot."))
                        .priceFrom(new BigDecimal("50000"))
                        .priceTo(new BigDecimal("65000"))
                        .unit(of("niêu", "pot"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("fish", "braised", "clay-pot"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("MON_XAO")
                .name(of("Món xào & món ăn kèm", "Stir-fry & side dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Rau muống xào tỏi", "Stir-fried morning glory"))
                        .description(of("Rau muống giòn xào tỏi thơm.", "Crispy morning glory stir-fried with garlic."))
                        .priceFrom(new BigDecimal("20000"))
                        .priceTo(new BigDecimal("30000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("vegetable", "stir-fry"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Mực xào sa tế", "Squid stir-fried with sa te"))
                        .description(of("Mực tươi xào sa tế cay thơm miền Trung.", "Fresh squid in spicy Central Vietnamese sa te."))
                        .priceFrom(new BigDecimal("55000"))
                        .priceTo(new BigDecimal("70000"))
                        .unit(of("đĩa", "plate"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("seafood", "stir-fry", "spicy"))
                        .build()
                ))
                .build()
        );

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "WIFI",
            "KIDS_CHAIR",
            "VAT_INVOICE",
            "VISA_MASTER",
            "SET_LUNCH",
            "OUTDOOR_TABLE",
            "SMOKING_AREA",
            "TAKEAWAY",
            "VEGETARIAN_MENU",
            "HALAL_AVAILABLE",
            "CASH_ONLY_OPTION",
            "FAMILY_FRIENDLY"
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(false)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Vỉa hè và bãi trống trước nhà hàng trên đường Nguyễn Tri Phương",
                                         "Sidewalk and open space in front of restaurant on Nguyen Tri Phuong street"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Khu vực trung tâm quận Thanh Khê, có thể đậu xe trên đường hoặc các bãi trống lân cận.",
                      "Central Thanh Khe district area, street parking and nearby open lots available."))
            .build();

        // ---------- Content STORY (7 cặp) ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY,
                of("Cơm Niêu Nhà Đỏ 2 — Dư vị quê hương nồng nàn, ấm áp",
                   "Com Nieu Nha Do 2 — Warm and nostalgic hometown flavors")),
            paragraph(ContentSection.STORY,
                of("Cơm Niêu Nhà Đỏ 2 là điểm ẩm thực bình dân nức tiếng trên đường Nguyễn Tri Phương, quận Thanh Khê, " +
                   "thành phố Đà Nẵng. Với phong cách phục vụ mộc mạc, chân thật và thực đơn xoay quanh cơm niêu đất " +
                   "truyền thống cùng các món ăn dân dã miền Trung, nhà hàng đã trở thành bữa ăn quen thuộc không thể thiếu " +
                   "của người dân địa phương và du khách tìm kiếm hương vị thuần Việt.",
                   "Com Nieu Nha Do 2 is a beloved local eatery on Nguyen Tri Phuong Street, Thanh Khe District, Da Nang City. " +
                   "With its honest rustic service style and a menu centered on traditional clay pot rice and Central Vietnamese " +
                   "homestyle dishes, the restaurant has become an irreplaceable meal destination for locals and visitors " +
                   "seeking authentic Vietnamese flavors.")),
            heading(ContentSection.STORY,
                of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng mang phong cách nhà đỏ ấm áp, trang trí giản dị với gam màu đỏ đặc trưng kết hợp vật liệu " +
                   "gỗ mộc mạc tạo cảm giác gần gũi như bữa cơm nhà. Sức chứa khoảng 150 khách trải dài từ khu vực " +
                   "phòng máy lạnh đến không gian thông thoáng, phù hợp cho nhóm gia đình và bạn bè từ 2-20 người.",
                   "The restaurant features a warm red-house style, simply decorated with the signature red color scheme " +
                   "and rustic wooden elements that evoke the feeling of a home-cooked meal. With capacity for about 150 guests " +
                   "across air-conditioned rooms and open areas, it suits families and friend groups of 2 to 20 people.")),
            heading(ContentSection.STORY,
                of("Thực đơn & món đặc sắc", "Menu & signature dishes")),
            paragraph(ContentSection.STORY,
                of("Trái tim của thực đơn là cơm niêu đất — mỗi niêu cơm đều được nấu đúng lửa cho lớp cơm cháy giòn " +
                   "vàng thơm lừng, phần cơm bên trong dẻo mềm. Kèm theo là các món kho niêu đặc trưng: cá thu kho tiêu, " +
                   "thịt kho trứng cút, cá bống kho tiêu — tất cả đều được kho trong niêu đất giữ nguyên vị đậm đà tự nhiên. " +
                   "Các món xào, rau, canh chua cá diếc miền Trung hoàn thiện thêm cho mâm cơm thuần Việt đầy ắp hương vị.",
                   "The heart of the menu is clay pot rice — each pot is cooked over precise heat to produce a crispy golden " +
                   "crust with a soft fragrant interior. Complemented by signature braised clay pot dishes: mackerel with pepper, " +
                   "pork braised with quail eggs, goby fish with pepper — all braised in clay pots to preserve natural rich flavors. " +
                   "Stir-fries, vegetables, and Central Vietnamese sour fish soup complete a truly Vietnamese feast.")),
            heading(ContentSection.STORY,
                of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Với mức giá bình dân 60.000 – 150.000đ/người và thực đơn phong phú, Cơm Niêu Nhà Đỏ 2 là lựa chọn " +
                   "hoàn hảo cho bữa trưa văn phòng, ăn gia đình cuối tuần, tụ tập bạn bè hay du khách muốn thưởng thức " +
                   "ẩm thực địa phương chân thực. Nhà hàng mở hai ca — trưa và tối — phục vụ linh hoạt mọi nhu cầu.",
                   "With affordable pricing of 60,000–150,000 VND per person and a varied menu, Com Nieu Nha Do 2 is the " +
                   "perfect choice for office lunches, weekend family meals, friends gatherings, or visitors wanting to " +
                   "experience authentic local cuisine. The restaurant operates two shifts — lunch and dinner — flexibly " +
                   "serving all dining needs.")),
            heading(ContentSection.STORY,
                of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng phục vụ thoải mái với không khí điều hòa mát mẻ, hỗ trợ thanh toán thẻ và xuất hóa đơn VAT " +
                   "theo yêu cầu. Thực đơn có các lựa chọn cho người ăn chay và đồ ăn mang về. Phục vụ bữa ăn cho trẻ em " +
                   "với bàn ghế chuyên dụng và thực đơn phù hợp. Đặt bàn trước được khuyến khích vào giờ cao điểm trưa " +
                   "để đảm bảo có chỗ ngồi.",
                   "The restaurant offers comfortable air-conditioned dining, card payment support and VAT invoices on request. " +
                   "The menu includes vegetarian options and takeaway service. Child-friendly with dedicated seating and " +
                   "suitable menu choices. Advance booking is recommended during peak lunch hours to secure seating.")),
            heading(ContentSection.STORY,
                of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Tọa lạc tại 86 Nguyễn Tri Phương, quận Thanh Khê — một trong những tuyến đường ẩm thực sầm uất nhất " +
                   "Đà Nẵng. Cách trung tâm thành phố (cầu Rồng) khoảng 4km, dễ dàng tiếp cận bằng xe máy hay taxi từ " +
                   "mọi khu vực của thành phố. Đường Nguyễn Tri Phương là tuyến đường lớn, thuận tiện đi lại vào cả giờ trưa " +
                   "và tối.",
                   "Located at 86 Nguyen Tri Phuong, Thanh Khe District — one of Da Nang's liveliest food streets. " +
                   "About 4km from the city center (Dragon Bridge), easily accessible by motorbike or taxi from all city areas. " +
                   "Nguyen Tri Phuong is a major road, convenient to navigate at both lunch and dinner hours.")),
            heading(ContentSection.STORY,
                of("Chính sách đặt bàn & lý do nên chọn", "Booking policy & why choose us")),
            paragraph(ContentSection.STORY,
                of("Cơm Niêu Nhà Đỏ 2 không yêu cầu đặt cọc cho nhóm nhỏ dưới 10 người. Nhóm đông từ 10 người trở lên " +
                   "được khuyến nghị đặt trước ít nhất 1 tiếng để chuẩn bị chỗ ngồi. Hóa đơn VAT xuất theo yêu cầu. " +
                   "Lý do nên chọn: giá cả thực sự bình dân, hương vị cơm niêu đất chính thống Đà Nẵng, phục vụ thân thiện " +
                   "và không gian ấm cúng như bữa cơm nhà.",
                   "Com Nieu Nha Do 2 does not require deposits for groups under 10 people. Groups of 10 or more are " +
                   "recommended to book at least 1 hour in advance to prepare seating. VAT invoices issued on request. " +
                   "Why choose us: genuinely affordable pricing, authentic Da Nang clay pot rice flavors, friendly service " +
                   "and a cozy atmosphere as warm as a home-cooked meal.")),
            mapBlock(ContentSection.STORY, new double[]{108.203885, 16.062325})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1 Tết Dương lịch", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(5, 1,  of("Không áp dụng ưu đãi ngày Quốc tế Lao động", "Promotion not applicable on May 1")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh 2/9", "Promotion not applicable on Sep 2")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi ngày 31/12", "Promotion not applicable on Dec 31"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(false)
            .depositMinGuests(10)
            .depositAmount(BigDecimal.ZERO)
            .depositCurrencyCode("VND")
            .depositNotes(of("Không yêu cầu đặt cọc cho nhóm dưới 10 người. Nhóm đông nên đặt trước ít nhất 1 tiếng.",
                             "No deposit required for groups under 10. Larger groups should book at least 1 hour ahead."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi cho khách đặt bàn qua hệ thống, không áp dụng vào ngày lễ.",
                                 "Promotion for online bookings; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Chi tiết ưu đãi thay đổi theo thời điểm, vui lòng liên hệ nhà hàng.",
                              "Promotion details vary by time; please contact the restaurant."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(60)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(false)
            .invoiceNotes(of("Hóa đơn VAT xuất theo yêu cầu, thông báo trước khi đặt bàn.",
                             "VAT invoices issued on request; notify when booking."))
            .serviceChargePercent(BigDecimal.ZERO)
            .serviceChargeNotes(of("Không áp dụng phụ phí dịch vụ.", "No service charge applied."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ bên ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không được mang đồ uống từ bên ngoài vào.", "Outside drinks are not permitted."))
            .outsideDrinkFees(List.of())
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
            .partnerId("11")
            .partnerType(PartnerType.INTERNAL_ADMIN)
            .build();

        ModerationInfo moderation = ModerationInfo.builder()
            .status(RestaurantStatus.APPROVED)
            .rejectionReason(null)
            .blockedReason(null)
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Table types (2 loại bàn) ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-4-std")
                .name(of("Bàn thường 4 người", "Standard table for 4"))
                .seats(4)
                .minPeople(1)
                .maxPeople(4)
                .totalTables(20)
                .depositPrice(BigDecimal.ZERO)
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(60)
                .allowedDurationsMinutes(List.of(60, 90))
                .note(of("Bàn tiêu chuẩn khu vực chính.", "Standard table in main dining area."))
                .build(),
            TableType.builder()
                .id("tb-8-grp")
                .name(of("Bàn nhóm 8 người", "Group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(10)
                .totalTables(8)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn lớn cho nhóm gia đình và bạn bè.", "Large table for family and friend groups."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90))
            .defaultDurationMinutes(60)
            .minBookingLeadTimeMinutes(60)
            .graceArrivalMinutes(10)
            .freeCancelMinutes(30)
            .pendingPaymentExpireMinutes(20)
            .depositOnly(false)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build RestaurantDoc ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Thanh Khê", "Thanh Khe District"))
            .wardName(of("Phường Tam Thuận", "Tam Thuan Ward"))
            .addressLine(of("86 Nguyễn Tri Phương, Q. Thanh Khê, TP. Đà Nẵng",
                            "86 Nguyen Tri Phuong Street, Thanh Khe District, Da Nang City"))
            .location(new double[]{108.203885, 16.062325})
            .name(of("Cơm Niêu Nhà Đỏ 2", "Com Nieu Nha Do 2"))
            .slug(slug)
            .restaurantType(RestaurantType.GOI_MON)
            .cuisines(cuisines)
            .shortDescription(of("Cơm niêu đất truyền thống & mâm cơm dân dã miền Trung giữa lòng quận Thanh Khê, Đà Nẵng.",
                                 "Traditional clay pot rice & Central Vietnamese homestyle meals in the heart of Thanh Khe, Da Nang."))
            .description(of("Cơm Niêu Nhà Đỏ 2 là nhà hàng bình dân nổi tiếng trên đường Nguyễn Tri Phương, quận Thanh Khê, " +
                            "Đà Nẵng, chuyên phục vụ cơm niêu đất truyền thống và các món ăn dân dã miền Trung. " +
                            "Với phong cách nhà đỏ ấm cúng, mức giá bình dân và hương vị thuần Việt chân thật, " +
                            "nhà hàng là điểm đến quen thuộc của người dân địa phương và du khách muốn trải nghiệm " +
                            "ẩm thực đích thực của vùng đất Đà Nẵng.",
                            "Com Nieu Nha Do 2 is a beloved local eatery on Nguyen Tri Phuong Street, Thanh Khe District, Da Nang, " +
                            "specializing in traditional clay pot rice and Central Vietnamese homestyle dishes. " +
                            "With its warm red-house style, affordable pricing and authentic Vietnamese flavors, " +
                            "it is a familiar dining spot for locals and visitors seeking a genuine taste of Da Nang cuisine."))
            .phone("0623251082")
            .bookingHotline("0623251082")
            .minPricePerPerson(new BigDecimal("60000"))
            .maxPricePerPerson(new BigDecimal("150000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
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
