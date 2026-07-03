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
public class SeedBuffetHaiSanMrMoc {

    @Bean
    CommandLineRunner seedBuffetHaiSanMrMocRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedBuffetHaiSanMrMoc] start");
            RestaurantDoc doc = restaurantBuffetHaiSanMrMoc();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedBuffetHaiSanMrMoc] done");
        };
    }

    // =====================================================================
    //   BUFFET HẢI SẢN MR MỘC - ĐÀ NẴNG (78 Võ Nguyên Giáp, Mân Thái, Sơn Trà)
    // =====================================================================

    private static RestaurantDoc restaurantBuffetHaiSanMrMoc() {
        String slug = "buffet-hai-san-mr-moc-da-nang";

        // ---------- Gallery ảnh ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-1-normal-4236428073256.webp",
                of("Không gian rộng lớn Buffet Hải Sản Mr Mộc Đà Nẵng sức chứa 500 khách", "Grand interior of Mr Moc Seafood Buffet Da Nang, capacity 500 guests"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-2-normal-4236428173257.webp",
                of("Khu vực lẩu nướng đặc sắc tại Mr Mộc", "Hotpot and grill area at Mr Moc"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-3-normal-4236428273258.webp",
                of("Quầy hải sản tươi sống đa dạng", "Fresh seafood station"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-4-normal-4236428373259.webp",
                of("Không gian ăn uống rộng thoáng tại Mr Mộc", "Spacious dining area at Mr Moc"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-5-normal-4236428473260.webp",
                of("Bàn buffet hải sản lẩu nướng Mr Mộc", "Seafood hotpot buffet table at Mr Moc"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-6-normal-4236428573261.webp",
                of("Không gian tiệc lớn tại Mr Mộc Đà Nẵng", "Large banquet space at Mr Moc Da Nang"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-7-normal-4236896273291.webp",
                of("Khu vực nướng BBQ tại Mr Mộc", "BBQ grill area at Mr Moc"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-8-normal-4236896373292.webp",
                of("Quầy buffet phong phú hơn 100 món", "Buffet counter with over 100 dishes"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-9-normal-4236896473293.webp",
                of("Hải sản tươi sống tại Mr Mộc", "Live seafood selection at Mr Moc"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-10-normal-4236897173294.webp",
                of("Không gian ngồi ngoài trời Mr Mộc", "Outdoor seating area at Mr Moc"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-11-normal-4236897273295.webp",
                of("Khu vực tiệc VIP tại Mr Mộc", "VIP banquet area at Mr Moc"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/buffet-hai-san-mr-moc-da-nang-12-normal-4236898073296.webp",
                of("Toàn cảnh nhà hàng Buffet Hải Sản Mr Mộc Đà Nẵng", "Overview of Mr Moc Seafood Buffet restaurant Da Nang"), false, 11)
        );

        // ---------- Ảnh menu ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/09.%20Mr%20Mộc/menu-buffet-hai-san-mr-moc-da-nang-1.jpg",
                of("Menu buffet hải sản lẩu nướng Mr Mộc", "Mr Moc seafood hotpot buffet menu"), false, 0)
        );

        // ---------- Ẩm thực ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("VIETNAMESE")
                .name(of("Ẩm thực Việt Nam", "Vietnamese cuisine"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("SEAFOOD")
                .name(of("Hải sản tươi sống", "Fresh seafood"))
                .region("CENTRAL")
                .build(),
            CuisineTag.builder()
                .code("HOTPOT_GRILL")
                .name(of("Lẩu nướng BBQ", "Hotpot & BBQ grill"))
                .region("ASIA")
                .build()
        );

        // ---------- Giờ mở cửa ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.TUESDAY,   10, 0, 22, 0),
            openingHour(DayOfWeek.WEDNESDAY, 10, 0, 22, 0),
            openingHour(DayOfWeek.THURSDAY,  10, 0, 22, 0),
            openingHour(DayOfWeek.FRIDAY,    10, 0, 22, 0),
            openingHour(DayOfWeek.SATURDAY,  10, 0, 22, 30),
            openingHour(DayOfWeek.SUNDAY,    10, 0, 22, 30)
        );

        // ---------- Phù hợp ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FAMILY",         of("Tiệc gia đình đông người", "Large family gathering")),
            suitable("COMPANY_PARTY",  of("Tiệc liên hoan công ty", "Company party")),
            suitable("FRIENDS",        of("Tụ tập bạn bè nhóm lớn", "Large friend group gathering")),
            suitable("BIRTHDAY",       of("Tổ chức sinh nhật nhóm đông", "Group birthday celebration")),
            suitable("GROUP_MEETING",  of("Họp nhóm và hội nghị", "Group meeting and conference")),
            suitable("COMPANY_EVENT",  of("Sự kiện doanh nghiệp lớn", "Large corporate event")),
            suitable("ANNIVERSARY",    of("Kỷ niệm đặc biệt", "Special anniversary")),
            suitable("OFFICE_LUNCH",   of("Ăn trưa văn phòng nhóm", "Office group lunch")),
            suitable("COUPLE",         of("Hẹn hò ăn buffet hải sản", "Seafood buffet date")),
            suitable("TEAM_BUILDING",  of("Team building và tiệc ngoài trời", "Team building and outdoor party"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",  of("Sôi động, nhộn nhịp", "Vibrant and lively")),
            ambience("RUSTIC",   of("Dân dã, bình dị đậm chất biển", "Rustic coastal charm")),
            ambience("MODERN",   of("Hiện đại với không gian lớn", "Modern with large open space")),
            ambience("COZY",     of("Thoải mái, thân thiện", "Comfortable and friendly"))
        );

        // ---------- Sức chứa ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(500)
            .maxGroupSize(200)
            .hasPrivateRooms(true)
            .privateRoomCount(5)
            .maxPrivateRoomCapacity(50)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Món đặc sắc ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Tôm hùm nướng muối ớt", "Grilled lobster with salt and chili")),
            signatureDishL(of("Ghẹ hấp bia tươi", "Fresh beer-steamed crab")),
            signatureDishL(of("Bạch tuộc nướng sa tế", "Grilled octopus with satay sauce")),
            signatureDishL(of("Sò điệp nướng mỡ hành", "Grilled scallops with scallion oil")),
            signatureDishL(of("Mực lá nướng than hoa", "Charcoal-grilled squid")),
            signatureDishL(of("Lẩu hải sản thập cẩm", "Mixed seafood hotpot")),
            signatureDishL(of("Cua hoàng đế rang muối", "Salt-roasted king crab")),
            signatureDishL(of("Hàu nướng phô mai kiểu Pháp", "French-style cheese-grilled oysters")),
            signatureDishL(of("Bia tươi không giới hạn kèm buffet", "Unlimited fresh beer with buffet")),
            signatureDishL(of("Lẩu Thái chua cay hải sản", "Spicy Thai seafood hotpot"))
        );

        // ---------- Menu cấu trúc ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("BUFFET_MAIN")
                .name(of("Suất Buffet Hải Sản Lẩu & Nướng", "Seafood Hotpot & Grill Buffet"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Suất buffet hải sản lẩu & nướng", "Seafood hotpot & grill buffet"))
                        .description(of("Hơn 100 món hải sản tươi sống, lẩu, nướng BBQ, bia tươi không giới hạn.", "Over 100 fresh seafood, hotpot, BBQ grill, unlimited fresh beer."))
                        .priceFrom(new BigDecimal("339000"))
                        .priceTo(new BigDecimal("339000"))
                        .unit(of("người", "person"))
                        .combo(false)
                        .buffetItem(true)
                        .tags(List.of("buffet", "seafood", "hotpot", "grill"))
                        .build()
                ))
                .build()
        );

        // ---------- Đỗ xe ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bãi đỗ xe rộng trước nhà hàng trên đường Võ Nguyên Giáp", "Large parking lot in front of the restaurant on Vo Nguyen Giap Street"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bãi xe máy ngay trước cổng nhà hàng", "Motorbike parking right at the restaurant entrance"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Miễn phí gửi xe ô tô và xe máy cho khách dùng buffet.", "Free car and motorbike parking for buffet guests."))
            .build();

        // ---------- Tiện ích ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "AIR_CONDITIONING",
            "OUTDOOR_TABLE",
            "WIFI",
            "KIDS_CHAIR",
            "SMOKING_AREA",
            "SOUND_LIGHT",
            "MC_SERVICE",
            "EVENT_DECOR",
            "PRIVATE_ROOM",
            "BEER_ON_TAP",
            "SEA_VIEW",
            "LARGE_GROUP_TABLE"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Giới thiệu Buffet Hải Sản Mr Mộc Đà Nẵng", "About Mr Moc Seafood Buffet Da Nang")),
            paragraph(ContentSection.STORY,
                of("Buffet Hải Sản Mr Mộc là một trong những nhà hàng buffet hải sản lớn và nổi tiếng nhất tại Đà Nẵng, " +
                   "tọa lạc ngay trên đường Võ Nguyên Giáp — tuyến đường biển đẹp nhất thành phố tại phường Mân Thái, quận Sơn Trà. " +
                   "Với sức chứa lên đến 500 khách, Mr Mộc là điểm đến lý tưởng cho mọi nhóm từ gia đình đến doanh nghiệp, " +
                   "từ tiệc sinh nhật nhỏ đến hội nghị quy mô lớn. Nơi đây chuyên phục vụ suất buffet hải sản lẩu & nướng " +
                   "với hơn 100 món đặc sắc, giá chỉ từ 339.000đ/người.",
                   "Mr Moc Seafood Buffet is one of the largest and most renowned seafood buffet restaurants in Da Nang, " +
                   "located right on Vo Nguyen Giap Street — the city's most scenic coastal road in Man Thai Ward, Son Tra District. " +
                   "With a capacity of up to 500 guests, Mr Moc is the ideal destination for any group from families to corporations, " +
                   "from small birthday parties to large-scale conferences. Specializing in seafood hotpot & grill buffets " +
                   "with over 100 remarkable dishes, starting at just 339,000 VND per person.")),
            heading(ContentSection.STORY, of("Không gian & sức chứa 500 khách", "Space & 500-guest capacity")),
            paragraph(ContentSection.STORY,
                of("Mr Mộc sở hữu không gian ăn uống rộng lớn bậc nhất Đà Nẵng với diện tích hàng nghìn mét vuông, " +
                   "được bố trí gọn gàng gồm khu vực trong nhà có điều hòa, khu vực ngoài trời gần biển thoáng mát " +
                   "và 5 phòng riêng VIP sức chứa mỗi phòng đến 50 khách. " +
                   "Hệ thống bàn ghế hiện đại, bếp lẩu và vỉ nướng than hoa riêng từng bàn tạo nên trải nghiệm ăn uống cá nhân hóa. " +
                   "Không gian đủ rộng để tổ chức tiệc cưới, liên hoan công ty và các sự kiện lớn ngoài trời.",
                   "Mr Moc boasts one of Da Nang's largest dining spaces spanning thousands of square meters, " +
                   "neatly arranged with an air-conditioned indoor area, a breezy outdoor area near the beach, " +
                   "and 5 VIP private rooms each accommodating up to 50 guests. " +
                   "Modern tables with individual hotpot burners and charcoal grills create a personalized dining experience. " +
                   "The space is large enough to host weddings, company parties, and major outdoor events.")),
            heading(ContentSection.STORY, of("Thực đơn buffet 100+ món hải sản lẩu nướng", "100+ dish seafood hotpot & grill buffet menu")),
            paragraph(ContentSection.STORY,
                of("Thực đơn buffet của Mr Mộc là điểm tự hào lớn nhất với hơn 100 món phong phú được phục vụ liên tục. " +
                   "Hải sản tươi sống gồm tôm hùm, ghẹ, bạch tuộc, mực, sò điệp, hàu, cá biển tươi được chế biến theo nhiều phong cách " +
                   "— nướng than hoa, hấp, sốt bơ tỏi, rang muối ớt, phô mai. " +
                   "Bên cạnh đó là các món lẩu Thái chua cay, lẩu hải sản thập cẩm, lẩu nấm, cùng vô số món ăn kèm " +
                   "như rau, nấm, đậu hũ, bún, mì và bia tươi không giới hạn. " +
                   "Thực phẩm được nhập mới mỗi ngày, đảm bảo tươi ngon và an toàn vệ sinh thực phẩm.",
                   "Mr Moc's buffet menu is the restaurant's greatest pride, featuring over 100 continuously replenished dishes. " +
                   "Fresh seafood includes lobster, crab, octopus, squid, scallops, oysters, and fresh fish prepared in various styles " +
                   "— charcoal-grilled, steamed, garlic butter, salt-chili roasted, and cheese-topped. " +
                   "Also featured are spicy Thai hotpot, mixed seafood hotpot, mushroom hotpot, along with countless side items " +
                   "such as vegetables, mushrooms, tofu, rice noodles, egg noodles, and unlimited draft beer. " +
                   "Ingredients are sourced fresh daily, ensuring quality and food safety.")),
            heading(ContentSection.STORY, of("Phù hợp mọi dịp — từ gia đình đến doanh nghiệp", "Perfect for all occasions — family to corporate")),
            paragraph(ContentSection.STORY,
                of("Nhờ quy mô lớn và dịch vụ chuyên nghiệp, Buffet Hải Sản Mr Mộc đáp ứng được mọi nhu cầu tổ chức: " +
                   "tiệc sinh nhật nhóm đông từ 20-200 khách, liên hoan cuối năm công ty, team building gắn kết nhân viên, " +
                   "họp mặt gia đình dịp lễ Tết, tiệc tốt nghiệp hay đám hỏi đơn giản. " +
                   "Nhà hàng có đội ngũ MC, dịch vụ trang trí sự kiện và hệ thống âm thanh ánh sáng chuyên nghiệp " +
                   "hỗ trợ tổ chức tiệc theo yêu cầu. Khách có thể đặt toàn bộ khu vực hoặc phòng riêng tùy nhu cầu.",
                   "Thanks to its scale and professional service, Mr Moc Seafood Buffet meets every event need: " +
                   "group birthday parties from 20-200 guests, year-end company dinners, employee team building, " +
                   "family holiday gatherings, graduation parties, or simple engagement celebrations. " +
                   "The restaurant features an MC team, event decoration services, and a professional sound and lighting system " +
                   "to support customized parties. Guests may reserve the entire venue or individual private rooms as needed.")),
            heading(ContentSection.STORY, of("Vị trí đắc địa trên đường biển Võ Nguyên Giáp", "Prime location on Vo Nguyen Giap coastal road")),
            paragraph(ContentSection.STORY,
                of("Nhà hàng nằm tại số 78 đường Võ Nguyên Giáp, phường Mân Thái, quận Sơn Trà — " +
                   "con đường ven biển nổi tiếng nối trung tâm Đà Nẵng với bán đảo Sơn Trà. " +
                   "Vị trí gần biển Mỹ Khê, cách sân bay quốc tế Đà Nẵng khoảng 6km và cách trung tâm thành phố 5km. " +
                   "Đây là khu vực tập trung nhiều nhà hàng hải sản nổi tiếng của Đà Nẵng, " +
                   "thuận tiện cho du khách lưu trú tại các khách sạn dọc bãi biển.",
                   "The restaurant is located at 78 Vo Nguyen Giap Street, Man Thai Ward, Son Tra District — " +
                   "the famous coastal road connecting Da Nang's city center to Son Tra Peninsula. " +
                   "Near My Khe Beach, approximately 6km from Da Nang International Airport and 5km from the city center. " +
                   "This area concentrates many of Da Nang's famous seafood restaurants, " +
                   "convenient for tourists staying at beach hotels.")),
            heading(ContentSection.STORY, of("Dịch vụ & tiện ích nổi bật", "Services & notable amenities")),
            paragraph(ContentSection.STORY,
                of("Mr Mộc cung cấp đầy đủ tiện ích: bãi đỗ xe ô tô và xe máy miễn phí rộng rãi, " +
                   "WiFi tốc độ cao miễn phí, ghế trẻ em phù hợp gia đình có con nhỏ. " +
                   "Khu hút thuốc riêng biệt, hệ thống điều hòa không khí toàn bộ khu trong nhà. " +
                   "Dịch vụ MC và trang trí bàn tiệc theo chủ đề (sinh nhật, hội nghị, liên hoan) theo yêu cầu. " +
                   "Phòng riêng VIP có hệ thống âm thanh độc lập và máy chiếu hỗ trợ hội họp và trình chiếu.",
                   "Mr Moc provides full amenities: spacious free car and motorbike parking, " +
                   "high-speed free WiFi, children's chairs suitable for families with young kids. " +
                   "Separate smoking area, full air conditioning in all indoor sections. " +
                   "MC services and themed table decoration (birthday, conference, party) available on request. " +
                   "VIP private rooms feature independent sound systems and projectors for meetings and presentations.")),
            heading(ContentSection.STORY, of("Chính sách đặt bàn & đặt cọc", "Booking & deposit policy")),
            paragraph(ContentSection.STORY,
                of("Nhóm từ 20 khách trở lên cần đặt trước ít nhất 4 tiếng và đặt cọc xác nhận. " +
                   "Mức cọc 200.000đ–500.000đ tùy quy mô nhóm, hoàn lại nếu hủy trước 24 giờ. " +
                   "Nhà hàng giữ chỗ tối đa 20 phút sau giờ đặt. " +
                   "Giá buffet tính theo đầu người, trẻ em dưới 1m được miễn phí, trẻ 1m–1,4m tính 50% giá người lớn. " +
                   "Xuất hóa đơn VAT theo yêu cầu cho đoàn doanh nghiệp.",
                   "Groups of 20 or more need to book at least 4 hours in advance and confirm with a deposit. " +
                   "Deposit amount 200,000-500,000 VND depending on group size, refundable with cancellation at least 24 hours in advance. " +
                   "The restaurant holds tables for up to 20 minutes after the reserved time. " +
                   "Buffet price is per person; children under 1m dine free, children 1m-1.4m pay 50% of adult price. " +
                   "VAT invoices available on request for corporate groups.")),
            heading(ContentSection.STORY, of("Lý do nên chọn Mr Mộc cho tiệc nhóm lớn tại Đà Nẵng", "Why choose Mr Moc for your large group event in Da Nang")),
            paragraph(ContentSection.STORY,
                of("Mr Mộc là lựa chọn số 1 khi cần tổ chức ăn uống quy mô lớn tại Đà Nẵng: " +
                   "sức chứa 500 khách, thực đơn 100+ món hải sản tươi ngon với mức giá 339.000đ/người cực kỳ hợp lý, " +
                   "bia tươi không giới hạn, bãi đỗ xe miễn phí và đội ngũ phục vụ chuyên nghiệp. " +
                   "Gần biển Mỹ Khê, dễ dàng kết hợp du lịch và ăn uống trong cùng một chuyến đi. " +
                   "Không nơi nào ở Đà Nẵng có thể cung cấp trải nghiệm hải sản buffet đa dạng và thoải mái như Mr Mộc.",
                   "Mr Moc is the top choice for large-scale dining events in Da Nang: " +
                   "500-guest capacity, 100+ fresh seafood dishes at the extremely reasonable price of 339,000 VND per person, " +
                   "unlimited draft beer, free parking, and a professional service team. " +
                   "Near My Khe Beach, easily combined with sightseeing in one trip. " +
                   "Nowhere in Da Nang can provide such a diverse and relaxed seafood buffet experience as Mr Moc.")),
            mapBlock(ContentSection.STORY, new double[]{108.247822, 16.082831})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1, 1,  of("Không áp dụng ưu đãi ngày 1/1", "Promotion not applicable on Jan 1")),
            blackoutGregorian(4, 30, of("Không áp dụng ưu đãi 30/4", "Promotion not applicable on Apr 30")),
            blackoutGregorian(9, 2,  of("Không áp dụng ưu đãi Quốc khánh", "Promotion not applicable on National Day")),
            blackoutGregorian(12, 31, of("Không áp dụng ưu đãi đêm Giao thừa", "Promotion not applicable on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(20)
            .depositAmount(new BigDecimal("300000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Áp dụng cho đoàn từ 20 khách trở lên, hoàn cọc nếu hủy trước 24 giờ.", "Applies to groups of 20+; refundable with 24-hour cancellation notice."))
            .hasPromotion(false)
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(240)
            .maxHoldTimeMinutes(20)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuất VAT cho đoàn doanh nghiệp theo yêu cầu.", "VAT invoices issued for corporate groups on request."))
            .serviceChargePercent(new BigDecimal("0"))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Không được mang đồ ăn từ ngoài vào.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Không nhận đồ uống mang ngoài, nhà hàng phục vụ bia tươi không giới hạn.", "Outside drinks not accepted; unlimited draft beer is included in buffet."))
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
                .id("tb-6-standard")
                .name(of("Bàn tiêu chuẩn 6 người", "Standard table for 6"))
                .seats(6)
                .minPeople(2)
                .maxPeople(6)
                .totalTables(40)
                .depositPrice(new BigDecimal("200000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Bàn buffet tiêu chuẩn 6 người, trong nhà có điều hòa.", "Standard buffet table for 6 in air-conditioned area."))
                .build(),
            TableType.builder()
                .id("tb-10-group")
                .name(of("Bàn nhóm lớn 10 người", "Large group table for 10"))
                .seats(10)
                .minPeople(6)
                .maxPeople(10)
                .totalTables(20)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120))
                .note(of("Bàn lớn dành cho nhóm 6-10 người, trong nhà hoặc ngoài trời.", "Large table for groups of 6-10, indoor or outdoor."))
                .build(),
            TableType.builder()
                .id("tb-vip-private")
                .name(of("Phòng riêng VIP (tối đa 50 khách)", "VIP private room (up to 50 guests)"))
                .seats(50)
                .minPeople(20)
                .maxPeople(50)
                .totalTables(5)
                .depositPrice(new BigDecimal("1000000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(true)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("Phòng riêng VIP sức chứa tối đa 50 khách, có hệ thống âm thanh và máy chiếu.", "VIP private room up to 50 guests with sound system and projector."))
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
            .districtName(of("Quận Sơn Trà", "Son Tra District"))
            .wardName(of("P. Mân Thái", "Man Thai Ward"))
            .addressLine(of("Số 78 Đường Võ Nguyên Giáp, P. Mân Thái, Q. Sơn Trà, TP. Đà Nẵng",
                           "78 Vo Nguyen Giap Street, Man Thai Ward, Son Tra District, Da Nang"))
            .location(new double[]{108.247822, 16.082831})
            .name(of("Buffet Hải Sản Mr Mộc Đà Nẵng", "Mr Moc Seafood Buffet Da Nang"))
            .slug(slug)
            .restaurantType(RestaurantType.BUFFET)
            .cuisines(cuisines)
            .shortDescription(of("Buffet hải sản lẩu & nướng 100+ món, sức chứa 500 khách, bia tươi không giới hạn, giá 339k/người tại Đà Nẵng.",
                                 "100+ dish seafood hotpot & grill buffet, 500-guest capacity, unlimited draft beer, 339k/person in Da Nang."))
            .description(of("Buffet Hải Sản Mr Mộc — nhà hàng buffet hải sản lẩu nướng quy mô lớn nhất Đà Nẵng tại 78 Võ Nguyên Giáp, " +
                            "phường Mân Thái, quận Sơn Trà. Sức chứa 500 khách, 100+ món hải sản tươi ngon, bia tươi không giới hạn. " +
                            "5 phòng riêng VIP, bãi đỗ xe miễn phí, dịch vụ MC và trang trí sự kiện. Giá 339.000đ/người.",
                            "Mr Moc Seafood Buffet — Da Nang's largest seafood hotpot & grill buffet at 78 Vo Nguyen Giap, " +
                            "Man Thai Ward, Son Tra District. Capacity 500 guests, 100+ fresh seafood dishes, unlimited draft beer. " +
                            "5 VIP private rooms, free parking, MC and event decoration services. Price 339,000 VND/person."))
            .phone("0828311228")
            .bookingHotline("0828311228")
            .minPricePerPerson(new BigDecimal("339000"))
            .maxPricePerPerson(new BigDecimal("339000"))
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
