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
public class SeedCountryBbqBeerTranBachDang {

    @Bean
    CommandLineRunner seedCountryBbqBeerTranBachDangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedCountryBbqBeerTranBachDang] start");

            RestaurantDoc doc = restaurantCountryBbqBeerTranBachDang();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedCountryBbqBeerTranBachDang] done");
        };
    }

    // =====================================================================
    //  COUNTRY BBQ & BEER - TRẦN BẠCH ĐẰNG - ĐÀ NẴNG
    // =====================================================================

    private static RestaurantDoc restaurantCountryBbqBeerTranBachDang() {
        String slug = "country-bbq-beer-tran-bach-dang-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-1-normal-2710885070324.webp",
                of("Không gian sân vườn ngoài trời Country BBQ & Beer Trần Bạch Đằng", "Outdoor garden space at Country BBQ & Beer Tran Bach Dang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-2-normal-2710885170325.webp",
                of("Khu vực BBQ ngoài trời sôi động", "Vibrant outdoor BBQ area"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-3-normal-2710885270326.webp",
                of("Bàn tiệc BBQ nhóm lớn", "Large group BBQ dining table"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-4-normal-2710885370327.webp",
                of("Không gian trẻ trung, náo nhiệt Country BBQ", "Youthful and lively Country BBQ atmosphere"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-5-normal-2710885470328.webp",
                of("Thực đơn BBQ Mỹ đặc sắc", "Distinctive American BBQ menu"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-6-normal-2710885570329.webp",
                of("Sườn nướng và beer thủ công", "Grilled ribs and craft beer"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-7-normal-2710885670330.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-8-normal-2710885770331.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-9-normal-2710885870332.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-10-normal-2710885970333.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-11-normal-2710886070334.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-12-normal-2710886170335.webp",
                of("", ""), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-13-normal-2710886270336.webp",
                of("", ""), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/slide-country-bbq-14-normal-2710886370337.webp",
                of("", ""), false, 13)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%201.jpg",
                of("Menu Country BBQ & Beer trang 1", "Country BBQ & Beer menu page 1"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%202.jpg",
                of("Menu Country BBQ & Beer trang 2", "Country BBQ & Beer menu page 2"), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%203.jpg",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%204.jpg",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%205.jpg",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/menu%20Country%20BBQ%20%20Beer/menu%20Country%20BBQ%20%20Beer%206.jpg",
                of("", ""), false, 5)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("AMERICAN_BBQ")
                .name(of("BBQ Mỹ truyền thống", "Traditional American BBQ"))
                .region("NORTH_AMERICA")
                .build(),
            CuisineTag.builder()
                .code("WESTERN")
                .name(of("Ẩm thực Âu - Mỹ", "Western cuisine"))
                .region("GLOBAL")
                .build(),
            CuisineTag.builder()
                .code("CRAFT_BEER")
                .name(of("Bia thủ công & craft drinks", "Craft beer & drinks"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 23, 59)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",        of("Tụ tập bạn bè nhậu BBQ", "Friends BBQ hangout")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật vui nhộn", "Fun birthday celebration")),
            suitable("GROUP_MEETING",  of("Họp nhóm, liên hoan sôi động", "Group meeting and lively celebration")),
            suitable("COMPANY_PARTY",  of("Tiệc tất niên, liên hoan công ty", "Year-end party, company gathering")),
            suitable("FAMILY",         of("Ăn gia đình cuối tuần", "Weekend family dining")),
            suitable("COUPLE",         of("Hẹn hò bình dị không gian thoáng", "Casual outdoor date")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa nhóm đồng nghiệp", "Colleagues lunch outing")),
            suitable("COMPANY_EVENT",  of("Sự kiện, team building ngoài trời", "Outdoor corporate event and team building")),
            suitable("ANNIVERSARY",    of("Kỷ niệm không gian thoải mái", "Relaxed anniversary dinner")),
            suitable("GROUP_TRAVEL",   of("Du khách theo nhóm tìm BBQ ngon Đà Nẵng", "Group travelers seeking Da Nang BBQ"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi nổi, náo nhiệt", "Vibrant and lively")),
            ambience("RUSTIC",   of("Phong cách mộc mạc kiểu Tây", "Rustic Western-style")),
            ambience("OUTDOOR",  of("Thoáng mát ngoài trời", "Open-air outdoor"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(150)
            .maxGroupSize(50)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Dallas Platter — mâm BBQ hỗn hợp kiểu Texas", "Dallas Platter — Texas-style mixed BBQ platter")),
            signatureDishL(of("Double Smashed Cheese Burger", "Double Smashed Cheese Burger")),
            signatureDishL(of("Houston Platter — mâm thịt khói hỗn hợp", "Houston Platter — mixed smoked meat platter")),
            signatureDishL(of("Beef Brisket — ức bò hun khói chậm 12 tiếng", "Beef Brisket — slow-smoked for 12 hours")),
            signatureDishL(of("ST. Louis Ribs — sườn heo nướng sốt BBQ cổ điển", "ST. Louis Ribs — classic BBQ-glazed pork ribs")),
            signatureDishL(of("Venison Pastrami — thịt nai muối hun khói", "Venison Pastrami — cured smoked venison")),
            signatureDishL(of("Philly Cheese Steak — bánh mì bò pho mát kiểu Philadelphia", "Philly Cheese Steak — Philadelphia-style steak sandwich")),
            signatureDishL(of("Pulled Pork Sandwich — bánh mì thịt heo xé sợi", "Pulled Pork Sandwich — shredded pork sandwich")),
            signatureDishL(of("Smoked Sausage — xúc xích hun khói gia vị Mỹ", "Smoked Sausage — American-spiced smoked sausage")),
            signatureDishL(of("Country Cobb Salad — salad rau tươi kiểu nông trại", "Country Cobb Salad — fresh farm-style salad"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BBQ_PLATTERS")
                .name(of("Mâm BBQ Mỹ đặc sắc", "American BBQ Platters"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Dallas Platter", "Dallas Platter"))
                        .description(of("Mâm BBQ Texas gồm brisket, sườn, xúc xích và khoai chiên.", "Texas BBQ platter with brisket, ribs, sausage and fries."))
                        .priceFrom(new BigDecimal("250000"))
                        .priceTo(new BigDecimal("350000"))
                        .unit(of("mâm", "platter"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("bbq", "signature", "sharing"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Beef Brisket 200g", "Beef Brisket 200g"))
                        .description(of("Ức bò hun khói chậm lửa nhỏ 12 tiếng, mềm tan chảy.", "Slow-smoked beef brisket over 12 hours, tender and melt-in-mouth."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("220000"))
                        .unit(of("phần", "portion"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("brisket", "signature"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi xe gần nhà hàng trên đường Trần Bạch Đằng", "Parking lot near restaurant on Tran Bach Dang street"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("10000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa nhà hàng, có bảo vệ", "In front of restaurant, with security guard"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Xe máy gửi miễn phí trước cửa. Ô tô gửi tại bãi xe gần đó có phí theo đơn vị trông giữ xe.",
                      "Free motorbike parking in front. Car parking at nearby lot with fee charged by parking operator."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "WIFI",
            "VISA_MASTER",
            "AIR_CONDITIONING",
            "LIVE_MUSIC",
            "SMOKING_AREA",
            "KIDS_CHAIR",
            "EVENT_DECOR",
            "VAT_INVOICE",
            "SOUND_LIGHT",
            "PROJECTOR",
            "BEER_GARDEN",
            "CRAFT_BEER_TAP"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Country BBQ & Beer Trần Bạch Đằng", "About Country BBQ & Beer Tran Bach Dang")),
            paragraph(ContentSection.STORY,
                of("Country BBQ & Beer Trần Bạch Đằng là điểm đến BBQ phong cách Mỹ nổi bật nhất khu vực Ngũ Hành Sơn, Đà Nẵng. " +
                   "Tọa lạc trên đường Trần Bạch Đằng, nhà hàng mang đến không gian sân vườn ngoài trời thoáng đãng với sức chứa 150 khách, " +
                   "là nơi lý tưởng để tận hưởng những buổi tối BBQ sôi động cùng bia lạnh bên bờ biển Đà Nẵng. " +
                   "Country BBQ & Beer là sự kết hợp giữa phong cách nướng thịt hun khói truyền thống Mỹ với không khí náo nhiệt, vui nhộn của một beer garden đích thực.",
                   "Country BBQ & Beer Tran Bach Dang is the most prominent American-style BBQ destination in the Ngu Hanh Son area of Da Nang. " +
                   "Located on Tran Bach Dang Street, the restaurant offers an open-air garden space with a capacity of 150 guests, " +
                   "making it ideal for lively BBQ evenings with cold beer by the Da Nang coast. " +
                   "Country BBQ & Beer combines traditional American slow-smoked BBQ with the vibrant, fun atmosphere of a genuine beer garden.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng sở hữu không gian sân vườn rộng rãi theo phong cách Western mộc mạc, với bàn gỗ dài, đèn dây Edison ấm cúng " +
                   "và khu vực ngoài trời thoáng đãng đặc biệt phù hợp cho nhóm đông. Sức chứa 150 khách được chia thành khu vực ngồi thông thường " +
                   "và khu bàn dài dành cho tiệc nhóm. Không gian sôi nổi, trẻ trung với âm nhạc sôi động, đặc biệt thú vị vào các tối cuối tuần " +
                   "khi nhà hàng đón nhóm du khách tìm kiếm trải nghiệm BBQ Mỹ chính hiệu tại Đà Nẵng.",
                   "The restaurant features a spacious Western-rustic garden space with long wooden tables, warm Edison string lights, " +
                   "and an open-air area especially suitable for large groups. The 150-person capacity is divided between standard seating " +
                   "and long communal tables for group parties. The youthful, vibrant ambiance with lively music is especially popular on weekend evenings " +
                   "when the restaurant welcomes travelers seeking authentic American BBQ in Da Nang.")),
            heading(ContentSection.STORY, of("Thực đơn BBQ Mỹ đặc sắc", "Signature American BBQ menu")),
            paragraph(ContentSection.STORY,
                of("Điểm nhấn lớn nhất của Country BBQ & Beer chính là thực đơn BBQ hun khói theo phong cách miền Nam nước Mỹ. " +
                   "Beef Brisket được hun khói chậm trong 12 tiếng với củi gỗ sồi, tạo nên lớp bark đặc trưng bên ngoài và thịt mềm tan bên trong. " +
                   "ST. Louis Ribs nướng sốt BBQ cổ điển — sườn heo đậm đà nhất nhì Đà Nẵng. Dallas Platter và Houston Platter là 2 mâm BBQ hỗn hợp " +
                   "dành cho nhóm ăn chia nhau, gồm brisket, sườn, xúc xích hun khói và khoai chiên. Burger kiểu Smashed Cheese cũng là lựa chọn phổ biến " +
                   "với lớp pho mát béo tan chảy trên patty thịt bò Mỹ.",
                   "Country BBQ & Beer's greatest highlight is its Southern-style American smoked BBQ menu. " +
                   "Beef Brisket is slow-smoked for 12 hours over oak wood, creating a distinctive bark on the outside with tender, juicy meat inside. " +
                   "ST. Louis Ribs with classic BBQ sauce — arguably the most flavorful pork ribs in Da Nang. Dallas Platter and Houston Platter are two mixed BBQ sharing platters " +
                   "for group dining, featuring brisket, ribs, smoked sausage, and fries. The Smashed Cheese Burger is also popular, " +
                   "with rich melted cheese over an American beef patty.")),
            heading(ContentSection.STORY, of("Beer garden & đồ uống", "Beer garden & beverages")),
            paragraph(ContentSection.STORY,
                of("Không thể thiếu phần 'Beer' trong tên gọi Country BBQ & Beer — nhà hàng cung cấp đa dạng bia lon, bia chai nhập khẩu " +
                   "cùng các loại bia thủ công (craft beer) theo mùa, cocktail và mocktail tươi mát. " +
                   "Bộ đôi sườn nướng + bia lạnh tại đây được nhiều thực khách đánh giá là 'combo ngon nhất Đà Nẵng'. " +
                   "Đặc biệt vào các tối thứ 6, thứ 7, nhà hàng tổ chức happy hour với mức giá ưu đãi cho bia từ 16:00-19:00.",
                   "The 'Beer' in Country BBQ & Beer is no afterthought — the restaurant offers a wide range of imported bottled and canned beers " +
                   "along with seasonal craft beers, cocktails and refreshing mocktails. " +
                   "The grilled ribs + cold beer combination here is widely reviewed as the 'best combo in Da Nang' by diners. " +
                   "Notably on Friday and Saturday evenings, the restaurant hosts happy hour with discounted beer prices from 16:00-19:00.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("Country BBQ & Beer là lựa chọn hoàn hảo cho nhóm bạn bè muốn có buổi nhậu BBQ vui nhộn, các tiệc sinh nhật trẻ trung " +
                   "hay liên hoan công ty theo phong cách casual. Với không gian ngoài trời rộng rãi, nhà hàng đặc biệt thích hợp cho các đoàn du lịch " +
                   "từ 20-50 người muốn trải nghiệm BBQ Mỹ theo nhóm. Gia đình có trẻ nhỏ cũng hoàn toàn phù hợp — không gian thoáng, trẻ em có thể " +
                   "chạy nhảy tự do trong khu sân vườn.",
                   "Country BBQ & Beer is the perfect choice for groups of friends wanting a fun BBQ outing, youthful birthday parties, " +
                   "or casual-style company gatherings. With its spacious outdoor area, the restaurant is especially suitable for travel groups " +
                   "of 20-50 people looking for a group American BBQ experience. Families with young children are also welcome — " +
                   "the open space allows children to move around freely in the garden.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích", "Services & amenities")),
            paragraph(ContentSection.STORY,
                of("Country BBQ & Beer cung cấp WiFi miễn phí, hệ thống âm thanh ánh sáng phục vụ sự kiện và tiệc nhóm. " +
                   "Nhà hàng có thể trang trí theo yêu cầu cho tiệc sinh nhật, tổ chức sự kiện ngoài trời và team building. " +
                   "Chấp nhận thanh toán bằng thẻ Visa/Master và xuất hóa đơn VAT theo yêu cầu. " +
                   "Bãi gửi xe máy miễn phí ngay trước cửa; ô tô gửi tại bãi xe gần đó.",
                   "Country BBQ & Beer provides free WiFi, sound and lighting systems for events and group parties. " +
                   "The restaurant can set up decorations for birthday parties, outdoor events, and team building activities on request. " +
                   "Visa/Master card payments accepted; VAT invoices available on request. " +
                   "Free motorbike parking in front; car parking at a nearby lot.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm trên đường Trần Bạch Đằng, quận Ngũ Hành Sơn, Đà Nẵng — khu vực sầm uất gần bãi biển Non Nước nổi tiếng. " +
                   "Cách trung tâm Đà Nẵng khoảng 8km về phía Nam, cách bãi biển Non Nước chỉ 3 phút đi bộ. " +
                   "Rất dễ tìm — nằm ngay trục đường chính Trần Bạch Đằng chạy dọc bờ biển, gần khu du lịch Ngũ Hành Sơn. " +
                   "Di chuyển bằng taxi, xe máy hoặc Grab rất thuận tiện từ bãi biển Mỹ Khê (15 phút).",
                   "The restaurant is located on Tran Bach Dang Street, Ngu Hanh Son District, Da Nang — a bustling area near the famous Non Nuoc Beach. " +
                   "About 8km south of Da Nang's center, just a 3-minute walk from Non Nuoc Beach. " +
                   "Very easy to find — right on the main Tran Bach Dang coastal road, near the Ngu Hanh Son tourist area. " +
                   "Convenient by taxi, motorbike, or Grab from My Khe Beach (15 minutes).")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước ít nhất 2 tiếng. Với nhóm từ 15 khách trở lên cần đặt trước 1 ngày và đặt cọc. " +
                   "Hủy trước 24 giờ được hoàn cọc đầy đủ. Nhà hàng giữ chỗ 15 phút sau giờ đặt. " +
                   "Không được mang đồ ăn và thức uống từ ngoài vào — nhà hàng có menu đồ uống đa dạng.",
                   "The restaurant accepts reservations at least 2 hours in advance. Groups of 15 or more need to book 1 day ahead with a deposit. " +
                   "Full refund for cancellations more than 24 hours in advance. Tables held for 15 minutes after reserved time. " +
                   "Outside food and beverages are not allowed — the restaurant has a full drink menu.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Country BBQ & Beer", "Why choose Country BBQ & Beer")),
            paragraph(ContentSection.STORY,
                of("Country BBQ & Beer Trần Bạch Đằng là một trong số ít nhà hàng BBQ Mỹ chính hiệu tại Đà Nẵng với kỹ thuật hun khói đích thực. " +
                   "Không gian ngoài trời rộng rãi, giá cả hợp lý (200,000đ/người), view gần biển và không khí sôi động " +
                   "khiến đây trở thành điểm đến BBQ được yêu thích nhất cho cả khách nội địa lẫn khách quốc tế ghé thăm Đà Nẵng.",
                   "Country BBQ & Beer Tran Bach Dang is one of the few authentic American BBQ restaurants in Da Nang with genuine smoking techniques. " +
                   "Spacious outdoor setting, reasonable prices (200,000 VND/person), near-beach views, and vibrant atmosphere " +
                   "make this the most popular BBQ destination for both domestic and international visitors to Da Nang.")),
            mapBlock(ContentSection.STORY, new double[]{108.2476, 16.0080})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi ngày Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(15)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 15 khách trở lên, đặt trước ít nhất 1 ngày.",
                             "Applies to bookings of 15 or more guests, at least 1 day in advance."))
            .hasPromotion(true)
            .promotionSummary(of("Happy hour bia 16:00-19:00 thứ 6, thứ 7. Ưu đãi 10% khi đặt bàn online.",
                                 "Beer happy hour 16:00-19:00 Fri & Sat. 10% discount for online bookings."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi không áp dụng ngày lễ và cuối tuần đặc biệt.", "Promotions not applicable on public holidays and special weekends."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% cho nhóm sự kiện và đặt tiệc.", "5% service charge for event groups and banquet bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Không nhận đồ uống ngoài, nhà hàng có menu đồ uống đa dạng.", "Outside drinks not accepted; full beverage menu available."))
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
                .id("tb-4-standard")
                .name(of("Bàn 4 người ngoài trời", "Outdoor table for 4"))
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
                .note(of("Bàn 4 người khu ngoài trời, thoáng mát.", "Outdoor table for 4, open-air."))
                .build(),
            TableType.builder()
                .id("tb-8-group")
                .name(of("Bàn dài nhóm 8 người", "Long group table for 8"))
                .seats(8)
                .minPeople(5)
                .maxPeople(8)
                .totalTables(10)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn gỗ dài phù hợp nhóm bạn bè hoặc gia đình lớn.", "Long wooden table for friend groups or large families."))
                .build(),
            TableType.builder()
                .id("tb-20-party")
                .name(of("Khu tiệc nhóm lớn từ 15-30 khách", "Large party area for 15-30 guests"))
                .seats(30)
                .minPeople(15)
                .maxPeople(30)
                .totalTables(3)
                .depositPrice(new BigDecimal("500000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Khu riêng dành cho tiệc nhóm lớn, liên hoan, sinh nhật.", "Dedicated area for large group parties, celebrations, birthdays."))
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
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Ngũ Hành Sơn", "Ngu Hanh Son District"))
            .wardName(of("P. Mỹ An", "My An Ward"))
            .addressLine(of("Đường Trần Bạch Đằng, P. Mỹ An, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                            "Tran Bach Dang Street, My An Ward, Ngu Hanh Son District, Da Nang"))
            .location(new double[]{108.2476, 16.0080})
            .name(of("Country BBQ & Beer Trần Bạch Đằng", "Country BBQ & Beer Tran Bach Dang"))
            .slug(slug)
            .restaurantType(RestaurantType.BBQ)
            .cuisines(cuisines)
            .shortDescription(of("Nhà hàng BBQ Mỹ hun khói truyền thống, beer garden ngoài trời rộng rãi tại Ngũ Hành Sơn, Đà Nẵng.",
                                 "Traditional American smoked BBQ restaurant with spacious outdoor beer garden in Ngu Hanh Son, Da Nang."))
            .description(of("Country BBQ & Beer Trần Bạch Đằng — thiên đường BBQ hun khói kiểu miền Nam nước Mỹ với sức chứa 150 khách. " +
                            "Nổi bật với Beef Brisket hun khói 12 tiếng, ST. Louis Ribs sốt BBQ, Dallas Platter và beer garden sôi động. " +
                            "Không gian ngoài trời thoáng đãng, giá cả hợp lý ~200,000đ/người. Phù hợp nhóm bạn bè, tiệc sinh nhật, liên hoan.",
                            "Country BBQ & Beer Tran Bach Dang — a Southern American BBQ paradise with a capacity of 150 guests. " +
                            "Famous for 12-hour slow-smoked Beef Brisket, BBQ ST. Louis Ribs, Dallas Platter, and a vibrant beer garden. " +
                            "Spacious outdoor setting, affordable at ~200,000 VND/person. Ideal for friend groups, birthday parties, and gatherings."))
            .phone("0885270326")
            .website("https://pasgo.vn/nha-hang/country-bbq-and-beer-tran-bach-dang-5698")
            .bookingHotline("0885270326")
            .minPricePerPerson(new BigDecimal("150000"))
            .maxPricePerPerson(new BigDecimal("300000"))
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
