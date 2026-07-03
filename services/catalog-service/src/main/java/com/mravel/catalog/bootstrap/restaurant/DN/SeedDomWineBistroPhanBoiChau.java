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
public class SeedDomWineBistroPhanBoiChau {

    @Bean
    CommandLineRunner seedDomWineBistroPhanBoiChauRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedDomWineBistroPhanBoiChau] start");

            RestaurantDoc doc = restaurantDomWineBistroPhanBoiChau();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());

            log.info(">>> [SeedDomWineBistroPhanBoiChau] done");
        };
    }

    // =====================================================================
    //        DOM - THE WINE BISTRO - PHAN BOI CHAU (DA NANG)
    // =====================================================================

    private static RestaurantDoc restaurantDomWineBistroPhanBoiChau() {
        String slug = "dom-the-wine-bistro-phan-boi-chau";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-1-normal-733116835835.webp",
                of("Không gian DOM - The Wine Bistro phong cách châu Âu, Phan Bội Châu Đà Nẵng", "DOM - The Wine Bistro European-style interior, Phan Boi Chau Da Nang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-2-normal-733117135836.webp",
                of("Khu vực bar rượu vang DOM với gần 60 loại vang", "DOM wine bar area with nearly 60 wine varieties"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-3-normal-733118135837.webp",
                of("Khu vực ngoài trời có mái che DOM Wine Bistro", "DOM Wine Bistro covered outdoor terrace"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-4-normal-733118235838.webp",
                of("", ""), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-5-normal-733118535839.webp",
                of("", ""), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-6-normal-733118835840.webp",
                of("", ""), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-7-normal-733119435841.webp",
                of("", ""), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-8-normal-733120235842.webp",
                of("", ""), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-9-normal-733121335843.webp",
                of("", ""), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-10-normal-733121635844.webp",
                of("", ""), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-11-normal-733121735845.webp",
                of("", ""), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/nha-hang-dom-the-wine-bistro-phan-boi-chau-12-normal-733122135846.webp",
                of("", ""), false, 11)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vanth/dom/dom1.jpg",
                of("Thực đơn rượu vang DOM Wine Bistro", "DOM Wine Bistro wine menu"), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/vanth/dom/dom2.jpg",
                of("Thực đơn món ăn DOM - The Wine Bistro", "DOM - The Wine Bistro food menu"), false, 1)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("EUROPEAN")
                .name(of("Ẩm thực Châu Âu", "European cuisine"))
                .region("EUROPE")
                .build(),
            CuisineTag.builder()
                .code("FRENCH")
                .name(of("Ẩm thực Pháp", "French cuisine"))
                .region("FRANCE")
                .build(),
            CuisineTag.builder()
                .code("WINE_BAR")
                .name(of("Bar rượu vang", "Wine bar"))
                .region("GLOBAL")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    15, 0, 22, 30),
            openingHour(DayOfWeek.TUESDAY,   15, 0, 22, 30),
            openingHour(DayOfWeek.WEDNESDAY, 15, 0, 22, 30),
            openingHour(DayOfWeek.THURSDAY,  15, 0, 22, 30),
            openingHour(DayOfWeek.FRIDAY,    15, 0, 22, 30),
            openingHour(DayOfWeek.SATURDAY,  15, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    15, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("COUPLE",         of("Hẹn hò lãng mạn với rượu vang", "Romantic date with wine")),
            suitable("ANNIVERSARY",    of("Kỷ niệm lãng mạn", "Romantic anniversary")),
            suitable("FRIENDS",        of("Gặp gỡ bạn bè buổi tối", "Evening friends gathering")),
            suitable("BUSINESS_MEAL",  of("Tiếp khách doanh nghiệp thân mật", "Intimate business dinner")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật ấm cúng", "Cozy birthday celebration")),
            suitable("FAMILY",         of("Ăn tối gia đình", "Family dinner")),
            suitable("COMPANY_PARTY",  of("Tiệc công ty nhỏ", "Small company party")),
            suitable("OFFICE_LUNCH",   of("Thư giãn sau giờ làm", "After-work relaxation")),
            suitable("GROUP_MEETING",  of("Họp nhóm thân mật", "Intimate group meeting")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp nhỏ", "Small corporate event"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("ROMANTIC",   of("Lãng mạn", "Romantic")),
            ambience("COZY",       of("Ấm cúng", "Cozy")),
            ambience("ELEGANT",    of("Thanh lịch kiểu châu Âu", "European elegant")),
            ambience("MODERN",     of("Hiện đại", "Modern")),
            ambience("RUSTIC",     of("Mộc mạc sang trọng", "Rustic chic"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(78)
            .maxGroupSize(30)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Sườn cừu áp chảo sốt rượu đỏ", "Pan-seared lamb chops with red wine sauce")),
            signatureDishL(of("Phô mai nướng Camembert kèm mứt sung", "Baked Camembert cheese with fig jam")),
            signatureDishL(of("Cá hồi áp chảo sốt bơ chanh dill", "Pan-seared salmon with lemon dill butter")),
            signatureDishL(of("Ức vịt confit kiểu Pháp", "French duck leg confit")),
            signatureDishL(of("Bruschetta cà chua và pho mát tươi", "Tomato and fresh cheese bruschetta")),
            signatureDishL(of("Pasta truffle nấm với bơ thảo mộc", "Truffle mushroom pasta with herb butter")),
            signatureDishL(of("Board chia sẻ pho mát và thịt nguội", "Cheese and charcuterie sharing board")),
            signatureDishL(of("Soup hành tây kiểu Pháp French Onion", "French onion soup")),
            signatureDishL(of("Tiramisu rượu vang đặc biệt DOM", "DOM special wine tiramisu")),
            signatureDishL(of("Crème brûlée vani Madagascar", "Madagascar vanilla crème brûlée"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("WINE_SELECTION")
                .name(of("Danh sách rượu vang (gần 60 loại)", "Wine selection (nearly 60 varieties)"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Rượu vang đỏ Pháp ly", "French red wine by glass"))
                        .description(of("Vang đỏ Pháp tuyển chọn, phục vụ theo ly hoặc theo chai.", "Selected French red wine, served by glass or bottle."))
                        .priceFrom(new BigDecimal("79000"))
                        .priceTo(new BigDecimal("150000"))
                        .unit(of("ly", "glass"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("wine", "french", "signature"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Rượu vang trắng Ý chai", "Italian white wine by bottle"))
                        .description(of("Vang trắng Ý nhẹ nhàng, phù hợp với hải sản và phô mai.", "Light Italian white wine, pairs well with seafood and cheese."))
                        .priceFrom(new BigDecimal("350000"))
                        .priceTo(new BigDecimal("800000"))
                        .unit(of("chai", "bottle"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("wine", "italian", "white"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("EUROPEAN_FOOD")
                .name(of("Món ăn Châu Âu", "European dishes"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Set dinner 2 người + 1 chai vang", "Dinner set for 2 + 1 bottle of wine"))
                        .description(of("Khai vị, 2 món chính Âu và 1 chai vang chọn lọc, phù hợp cho cặp đôi tối lãng mạn.", "Appetizer, 2 European mains and 1 bottle of select wine, perfect for a romantic couple evening."))
                        .priceFrom(new BigDecimal("550000"))
                        .priceTo(new BigDecimal("900000"))
                        .unit(of("set", "set"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("set", "couple", "wine", "romantic"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Đường Phan Bội Châu và các tuyến đường xung quanh", "Phan Boi Chau Street and surrounding roads"))
            .carParkingFeeType(ParkingFeeType.PAID)
            .carParkingFeeAmount(new BigDecimal("20000"))
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Trước cửa và trong hẻm DOM Wine Bistro", "In front of and in the alley of DOM Wine Bistro"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe máy; ô tô giữ theo giờ tại đường Phan Bội Châu gần nhà hàng.", "Free motorbike parking; car parking charged by hour on Phan Boi Chau Street near the restaurant."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "AIR_CONDITIONING",
            "WIFI",
            "WINE_LIST",
            "BIRTHDAY_DECORATION",
            "EVENT_DECOR",
            "VISA_MASTER",
            "VAT_INVOICE",
            "LIVE_MUSIC",
            "ROMANTIC_SETUP",
            "CHEESE_BOARD",
            "COCKTAIL_MENU",
            "TAKEAWAY"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu DOM - The Wine Bistro, Phan Bội Châu", "About DOM - The Wine Bistro, Phan Boi Chau")),
            paragraph(ContentSection.STORY,
                of("DOM - The Wine Bistro là nhà hàng và bar rượu vang đặc trưng phong cách châu Âu, " +
                   "tọa lạc tại số 03 Phan Bội Châu, phường Thạch Thang, quận Hải Châu, TP. Đà Nẵng. " +
                   "Với thực đơn rượu vang đa dạng và độc đáo gồm gần 60 loại vang từ nhiều quốc gia — Pháp, Ý, Tây Ban Nha, Chile, Úc — " +
                   "DOM đã trở thành điểm đến yêu thích của những người yêu rượu vang và ẩm thực Âu tại thành phố biển.",
                   "DOM - The Wine Bistro is a European-style restaurant and wine bar " +
                   "located at 03 Phan Boi Chau Street, Thach Thang Ward, Hai Chau District, Da Nang. " +
                   "With a diverse and distinctive wine menu of nearly 60 varieties from many countries — France, Italy, Spain, Chile, Australia — " +
                   "DOM has become the favorite destination for wine lovers and European food enthusiasts in the coastal city.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa", "Space & capacity")),
            paragraph(ContentSection.STORY,
                of("DOM sở hữu không gian 2 tầng với sức chứa khoảng 70 khách trong nhà và thêm khu vực ngoài trời có mái che " +
                   "đủ chỗ cho 8 thực khách, tổng sức chứa khoảng 78 người. " +
                   "Không gian được thiết kế theo phong cách châu Âu hiện đại — vừa lịch sự, sang trọng và tinh tế: " +
                   "bàn ghế gỗ ấm áp, ánh đèn nến lung linh, tường gạch thô mộc đặc trưng bistro Paris. " +
                   "Khu vực ngoài trời có mái che thoáng mát là lựa chọn lý tưởng cho những buổi tối đầu mùa hay chiều thu se lạnh.",
                   "DOM has a 2-floor space accommodating about 70 indoor guests plus a covered outdoor terrace " +
                   "for 8 more guests, totaling around 78 people. " +
                   "The space is designed in a modern European style — refined, elegant, and tasteful: " +
                   "warm wooden furniture, flickering candlelight, exposed brick walls characteristic of a Paris bistro. " +
                   "The covered outdoor terrace is ideal for cool evenings or early-season nights.")),
            heading(ContentSection.STORY, of("Thực đơn rượu vang đặc sắc — gần 60 loại", "Exceptional wine menu — nearly 60 varieties")),
            paragraph(ContentSection.STORY,
                of("Điểm nổi bật nhất của DOM chính là thực đơn rượu vang phong phú với gần 60 loại vang đến từ các vùng sản xuất " +
                   "nổi tiếng thế giới: Bordeaux và Bourgogne (Pháp), Tuscany và Piedmont (Ý), Rioja (Tây Ban Nha), " +
                   "Mendoza (Argentina) và Barossa Valley (Úc). " +
                   "Khách có thể thưởng thức theo ly hoặc đặt chai — nhân viên được đào tạo chuyên sâu về rượu vang sẵn sàng tư vấn " +
                   "ghép đôi món ăn và rượu hợp lý nhất.",
                   "DOM's most prominent feature is its rich wine menu with nearly 60 varieties from world-famous producing regions: " +
                   "Bordeaux and Bourgogne (France), Tuscany and Piedmont (Italy), Rioja (Spain), " +
                   "Mendoza (Argentina), and Barossa Valley (Australia). " +
                   "Guests may enjoy by glass or bottle — staff trained in-depth in wine are ready to advise " +
                   "on the best food and wine pairings.")),
            heading(ContentSection.STORY, of("Thực đơn món ăn Âu", "European food menu")),
            paragraph(ContentSection.STORY,
                of("Bên cạnh rượu vang, DOM phục vụ thực đơn món ăn Âu phong phú lấy cảm hứng từ ẩm thực Pháp và Địa Trung Hải. " +
                   "Nổi bật là sườn cừu áp chảo sốt rượu đỏ, ức vịt confit kiểu Pháp, board chia sẻ phô mai và thịt nguội cao cấp, " +
                   "pasta truffle nấm thơm ngậy, soup hành tây kiểu Pháp và các món tráng miệng Pháp cổ điển như tiramisu và crème brûlée. " +
                   "Mức giá hợp lý từ 120.000 - 250.000đ/người giúp DOM trở thành lựa chọn thú vị cho mọi buổi tối.",
                   "Alongside wine, DOM serves a rich European food menu inspired by French and Mediterranean cuisine. " +
                   "Standouts include pan-seared lamb chops in red wine sauce, French duck confit, premium cheese and charcuterie sharing boards, " +
                   "truffle mushroom pasta, French onion soup, and classic French desserts like tiramisu and crème brûlée. " +
                   "Reasonable pricing from 120,000 - 250,000 VND/person makes DOM an appealing choice for any evening.")),
            heading(ContentSection.STORY, of("Phù hợp dịp nào", "Suitable occasions")),
            paragraph(ContentSection.STORY,
                of("DOM là điểm đến lý tưởng cho những buổi tối hẹn hò lãng mạn bên ly rượu vang, " +
                   "kỷ niệm ngày đặc biệt, sinh nhật ấm cúng theo phong cách châu Âu. " +
                   "Đây cũng là địa điểm được ưa chuộng cho bữa tối tiếp khách doanh nghiệp thân mật, " +
                   "các buổi tụ họp bạn bè sau giờ làm và tiệc nhỏ nhóm không quá 30 người. " +
                   "Không gian ấm cúng và thực đơn vang phong phú tạo nên bầu không khí thư giãn đặc trưng kiểu Âu.",
                   "DOM is the ideal destination for romantic evenings over a glass of wine, " +
                   "special day anniversaries, and cozy European-style birthday celebrations. " +
                   "It is also popular for intimate business dinners, after-work friend get-togethers, " +
                   "and small group parties of up to 30 people. " +
                   "The cozy space and extensive wine menu create a distinctly European relaxed atmosphere.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích nổi bật", "Services & highlights")),
            paragraph(ContentSection.STORY,
                of("DOM cung cấp dịch vụ trang trí sinh nhật và kỷ niệm theo yêu cầu, ghép đôi rượu-món bởi nhân viên chuyên nghiệp. " +
                   "Thỉnh thoảng có chương trình nhạc live acoustic vào cuối tuần, nâng cao trải nghiệm ẩm thực. " +
                   "WiFi miễn phí, thực đơn cocktail phong phú, nhận thanh toán Visa/Master và xuất hóa đơn VAT. " +
                   "Khu ngoài trời có mái che thoáng mát là điểm cộng đặc biệt cho những ai muốn tận hưởng " +
                   "không khí buổi tối Đà Nẵng trong không gian mở.",
                   "DOM offers birthday and anniversary decoration services on request, and food-wine pairing by professional staff. " +
                   "Occasional live acoustic music on weekends enhances the dining experience. " +
                   "Free WiFi, a rich cocktail menu, Visa/Master card payments, and VAT invoice issuance. " +
                   "The covered outdoor terrace is a special bonus for those wanting to enjoy " +
                   "Da Nang's evening atmosphere in an open-air setting.")),
            heading(ContentSection.STORY, of("Vị trí & di chuyển", "Location & getting there")),
            paragraph(ContentSection.STORY,
                of("DOM Wine Bistro nằm tại số 03 Phan Bội Châu, phường Thạch Thang, quận Hải Châu — " +
                   "ngay trong khu phố ẩm thực trung tâm Đà Nẵng, cách bờ sông Hàn chỉ vài trăm mét " +
                   "và gần trục đường chính 2 tháng 9. Cùng tuyến phố với Sushi World, " +
                   "di chuyển bằng taxi hoặc Grab rất tiện lợi từ mọi khu vực trong thành phố. " +
                   "Xe máy đỗ miễn phí trước cửa, ô tô gửi gần đó với phí hợp lý.",
                   "DOM Wine Bistro is at 03 Phan Boi Chau, Thach Thang Ward, Hai Chau District — " +
                   "right in Da Nang's central dining precinct, just a few hundred meters from the Han River bank " +
                   "and close to the main 2 Thang 9 road. On the same street as Sushi World, " +
                   "getting there by taxi or Grab is convenient from all city areas. " +
                   "Free motorbike parking in front; car parking nearby at reasonable rates.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nhận đặt bàn trước ít nhất 2 tiếng. Đặt cọc áp dụng cho nhóm từ 8 khách trở lên " +
                   "hoặc khi yêu cầu trang trí đặc biệt (sinh nhật, kỷ niệm). " +
                   "Hủy trước 3 tiếng được hoàn cọc đầy đủ. Phụ thu VAT 10% theo quy định. " +
                   "Rượu mang từ ngoài vào áp dụng phụ phí corkage; ưu tiên sử dụng thực đơn vang của nhà hàng.",
                   "The restaurant accepts reservations at least 2 hours in advance. Deposits apply for groups of 8 or more " +
                   "or when special decorations are requested (birthdays, anniversaries). " +
                   "Cancellations more than 3 hours in advance receive a full refund. VAT 10% applies as regulated. " +
                   "Outside wine brought in incurs a corkage fee; the restaurant's own wine menu is preferred.")),
            heading(ContentSection.STORY, of("Lý do nên chọn DOM - The Wine Bistro", "Why choose DOM - The Wine Bistro")),
            paragraph(ContentSection.STORY,
                of("DOM là địa điểm hiếm có tại Đà Nẵng: kết hợp thực đơn vang gần 60 loại chuẩn thế giới, " +
                   "ẩm thực Pháp - Âu tinh tế và không gian bistro lãng mạn trong một mức giá rất hợp lý 120.000-250.000đ/người. " +
                   "Dù bạn là người sành vang hay đang khám phá rượu vang lần đầu, " +
                   "đội ngũ DOM luôn sẵn sàng dẫn dắt bạn qua hành trình ẩm thực châu Âu đáng nhớ.",
                   "DOM is a rare find in Da Nang: combining a world-class wine list of nearly 60 varieties, " +
                   "refined French-European cuisine, and a romantic bistro ambiance at a very reasonable price of 120,000-250,000 VND/person. " +
                   "Whether you are a wine connoisseur or exploring wine for the first time, " +
                   "DOM's team is always ready to guide you through a memorable European culinary journey.")),
            mapBlock(ContentSection.STORY, new double[]{108.22144, 16.07961})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(2, 14, of("Không áp dụng ưu đãi ngày Valentine", "Promotion not applicable on Valentine's Day")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi ngày 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(12, 24, of("Không áp dụng ưu đãi đêm Giáng sinh", "Promotion not applicable on Christmas Eve")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        List<OutsideDrinkFee> outsideDrinkFees = List.of(
            OutsideDrinkFee.builder()
                .drinkType("WINE")
                .feeAmount(new BigDecimal("200000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu vang mang ngoài: 200.000đ/chai.", "Wine corkage fee: 200,000 VND/bottle."))
                .build(),
            OutsideDrinkFee.builder()
                .drinkType("SPIRITS")
                .feeAmount(new BigDecimal("300000"))
                .currencyCode("VND")
                .note(of("Phụ phí rượu mạnh mang ngoài: 300.000đ/chai.", "Spirits corkage fee: 300,000 VND/bottle."))
                .build()
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(8)
            .depositAmount(new BigDecimal("200000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho booking từ 8 khách trở lên hoặc khi yêu cầu trang trí đặc biệt.", "Applies to bookings of 8+ guests or when special decorations are requested."))
            .hasPromotion(true)
            .promotionSummary(of("Ưu đãi 10% cho khách đặt qua hệ thống, không áp dụng ngày lễ lớn.", "10% discount for online bookings; not applicable on major public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Ưu đãi có thể thay đổi, liên hệ nhà hàng để biết chi tiết.", "Promotions may vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(120)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất hóa đơn VAT theo yêu cầu trước khi thanh toán.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phụ thu 5% áp dụng cho tiệc nhóm và đặt sự kiện đặc biệt.", "5% service charge for group parties and special event bookings."))
            .allowOutsideFood(false)
            .allowOutsideDrink(true)
            .outsideFoodPolicy(of("Không được mang thức ăn từ ngoài vào.", "Outside food is not allowed."))
            .outsideDrinkPolicy(of("Được mang rượu từ ngoài vào, áp dụng phụ phí corkage theo loại. Khuyến khích dùng thực đơn vang của nhà hàng.", "Outside drinks allowed with corkage fee by type. Restaurant's own wine menu is recommended."))
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
                .id("tb-2-indoor")
                .name(of("Bàn đôi trong nhà", "Indoor table for 2"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(12)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn đôi trong nhà, lý tưởng cho cặp đôi và bạn bè thân.", "Indoor table for 2, ideal for couples and close friends."))
                .build(),
            TableType.builder()
                .id("tb-4-indoor")
                .name(of("Bàn 4 khách trong nhà", "Indoor table for 4"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(12)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn 4 khách tiêu chuẩn trong nhà, phù hợp gia đình nhỏ và nhóm bạn.", "Standard indoor table for 4, suitable for small families and friend groups."))
                .build(),
            TableType.builder()
                .id("tb-4-outdoor")
                .name(of("Bàn ngoài trời có mái che", "Covered outdoor table"))
                .seats(4)
                .minPeople(2)
                .maxPeople(4)
                .totalTables(2)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Khu vực ngoài trời có mái che thoáng mát, tối đa 8 khách (2 bàn liền), rất được yêu chuộng buổi tối.", "Covered outdoor terrace area, max 8 guests (2 adjacent tables), very popular in the evenings."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(120)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(180)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(4)
            .build();

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Hải Châu", "Hai Chau District"))
            .wardName(of("P. Thạch Thang", "Thach Thang Ward"))
            .addressLine(of("Số 03 Phan Bội Châu, P. Thạch Thang, Q. Hải Châu, TP. Đà Nẵng",
                           "03 Phan Boi Chau Street, Thach Thang Ward, Hai Chau District, Da Nang"))
            .location(new double[]{108.22144, 16.07961})
            .name(of("DOM - The Wine Bistro - Phan Bội Châu", "DOM - The Wine Bistro - Phan Boi Chau"))
            .slug(slug)
            .restaurantType(RestaurantType.LOUNGE)
            .cuisines(cuisines)
            .shortDescription(of("Wine bistro châu Âu lãng mạn với gần 60 loại rượu vang và thực đơn Pháp tại số 03 Phan Bội Châu, Đà Nẵng.",
                                 "Romantic European wine bistro with nearly 60 wine varieties and French-inspired menu at 03 Phan Boi Chau, Da Nang."))
            .description(of("DOM - The Wine Bistro tọa lạc tại số 03 Phan Bội Châu, quận Hải Châu, Đà Nẵng. " +
                            "Không gian bistro châu Âu 2 tầng sức chứa 70 khách + 8 chỗ ngoài trời mái che. " +
                            "Thực đơn vang gần 60 loại từ Pháp, Ý, Tây Ban Nha, Chile, Úc. Ẩm thực Pháp-Âu tinh tế. " +
                            "Giá hợp lý 120.000-250.000đ/người.",
                            "DOM - The Wine Bistro at 03 Phan Boi Chau, Hai Chau, Da Nang. " +
                            "2-floor European bistro with 70 indoor + 8 covered outdoor seats. " +
                            "Wine menu of nearly 60 varieties from France, Italy, Spain, Chile, Australia. Refined French-European cuisine. " +
                            "Reasonable price 120,000-250,000 VND/person."))
            .phone("0796110822")
            .website("https://pasgo.vn/nha-hang/nha-hang-dom-the-wine-bistro-phan-boi-chau-2491")
            .bookingHotline("0796110822")
            .minPricePerPerson(new BigDecimal("120000"))
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
