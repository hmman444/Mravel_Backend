package com.mravel.catalog.bootstrap.hotel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenitySection;
import com.mravel.catalog.model.doc.HotelDoc.BedOption;
import com.mravel.catalog.model.doc.HotelDoc.BedType;
import com.mravel.catalog.model.doc.HotelDoc.BoardType;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.HotelDoc.FaqItem;
import com.mravel.catalog.model.doc.HotelDoc.GeneralInfo;
import com.mravel.catalog.model.doc.HotelDoc.HotelFilterFacets;
import com.mravel.catalog.model.doc.HotelDoc.HotelPolicy;
import com.mravel.catalog.model.doc.HotelDoc.HotelStatus;
import com.mravel.catalog.model.doc.HotelDoc.HotelType;
import com.mravel.catalog.model.doc.HotelDoc.Image;
import com.mravel.catalog.model.doc.HotelDoc.ModerationInfo;
import com.mravel.catalog.model.doc.HotelDoc.NearbyPlace;
import com.mravel.catalog.model.doc.HotelDoc.PaymentType;
import com.mravel.catalog.model.doc.HotelDoc.PolicyItem;
import com.mravel.catalog.model.doc.HotelDoc.PolicySection;
import com.mravel.catalog.model.doc.HotelDoc.PartnerType;
import com.mravel.catalog.model.doc.HotelDoc.PublisherInfo;
import com.mravel.catalog.model.doc.HotelDoc.RatePlan;
import com.mravel.catalog.model.doc.HotelDoc.RoomType;
import com.mravel.catalog.model.doc.HotelDoc.TaxAndFeeConfig;
import com.mravel.catalog.repository.HotelDocRepository;

import static com.mravel.catalog.utils.hotel.HotelContentBlocks.*;
import static com.mravel.catalog.utils.hotel.HotelImages.*;
import static com.mravel.catalog.utils.hotel.HotelSeedHelpers.*;
import static com.mravel.catalog.utils.hotel.Localized.of;

@Configuration
@Profile("seed")
public class SeedNtBrilliantBay {

        @Bean
        CommandLineRunner seedNtBrilliantBayRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedNtBrilliantBay] start");
                        HotelDoc doc = hotelBrilliantBay();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedNtBrilliantBay] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedNtBrilliantBay] done");
                };
        }

        // =====================================================================
        // BRILLIANT BAY NHA TRANG HOTEL
        // (source: Traveloka hotelId 9000005585794; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelBrilliantBay() {
                String slug = "brilliant-bay-nha-trang-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-1062524413d0e6450582c37bde2241ab.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680035407-0290e23c89c0e91a8fcab8c1873d7dcf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-f7460b3005d25078b46ab1a06e771af8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-e616a604dcc14d2b23a29e3b0da206ec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-77bdebf58a298ae13d519e97ca43f1ee.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-4aa973693e2ece844ffb0f38e36e0b39.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-91618abb94b05b11eca0752b306a6cbf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-3f05f3bf110af18f064b44e2c6f76882.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-09588cc4769071ae6f693ce56a1ab707.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-17bdbd3c70c84a58aa75c32e33072c1e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-0f6dc5af28992c31dff38297b922f3e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-63ef7be0f7e2717cf3de66c592feed86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-d3efeb152611537bcfdaaea5eb0616ce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-dbfaad5b10e064267510464d967cb3fc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 13));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 109.1987009, 12.2271742 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Brilliant Bay Nha Trang Hotel", "About Brilliant Bay Nha Trang Hotel")),
                paragraph(ContentSection.STORY,
                                of("Brilliant Bay Nha Trang Hotel là khách sạn 4 sao tọa lạc tại 96B/15 Trần Phú, phường Lộc Thọ, trung tâm thành phố biển Nha Trang. Với khoảng 80 phòng nghỉ hiện đại, hồ bơi ngoài trời và vị trí chỉ vài bước chân tới bãi biển, khách sạn là lựa chọn lý tưởng cho cả khách du lịch nghỉ dưỡng lẫn công tác.", "Brilliant Bay Nha Trang Hotel is a 4-star hotel located at 96B/15 Tran Phu, Loc Tho Ward, in the heart of the coastal city of Nha Trang. With around 80 modern rooms, an outdoor swimming pool and a location just steps from the beach, the hotel is an ideal choice for both leisure and business travelers.")),

                heading(ContentSection.STORY, of("Phòng nghỉ tiện nghi", "Comfortable rooms")),
                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp nhiều hạng phòng từ Superior, Deluxe đến Family và phòng hướng biển, phù hợp cho cặp đôi, gia đình và nhóm bạn. Mỗi phòng được trang bị máy lạnh, minibar, két an toàn, máy sấy tóc và Wi-Fi miễn phí, mang đến không gian nghỉ ngơi thoải mái sau một ngày khám phá thành phố biển.", "The hotel offers a range of room categories from Superior and Deluxe to Family and sea-view rooms, suitable for couples, families and groups. Each room comes with air conditioning, a minibar, a safe, a hair dryer and free Wi-Fi, providing a comfortable space to rest after a day exploring the coastal city.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng đa dạng cùng các món Á – Âu cho bữa trưa và bữa tối. Quầy cà phê và khu vực thư giãn là nơi lý tưởng để nhâm nhi đồ uống, ngắm nhìn nhịp sống sôi động của Nha Trang.", "The hotel restaurant serves a varied breakfast along with Asian and European dishes for lunch and dinner. The coffee corner and lounge area are perfect for enjoying a drink while watching the lively pace of Nha Trang.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                paragraph(ContentSection.STORY,
                                of("Du khách có thể thư giãn tại hồ bơi ngoài trời, sử dụng dịch vụ lễ tân 24 giờ, giặt ủi, giữ hành lý, đổi ngoại tệ và đưa đón sân bay theo yêu cầu. Khách sạn còn có thang máy, máy ATM, cửa hàng tiện lợi và dịch vụ nhận phòng cấp tốc giúp kỳ nghỉ thêm thuận tiện.", "Guests can relax at the outdoor pool and make use of the 24-hour front desk, laundry, luggage storage, currency exchange and airport transfer on request. The hotel also features an elevator, an ATM, a convenience shop and express check-in to make the stay more convenient.")),

                heading(ContentSection.STORY, of("Vị trí & bãi biển", "Location & the beach")),
                paragraph(ContentSection.STORY,
                                of("Nằm trên trục đường Trần Phú nổi tiếng, khách sạn chỉ cách bãi biển Nha Trang vài phút đi bộ. Du khách dễ dàng tản bộ dọc bờ biển, tắm nắng hoặc tham gia các hoạt động thể thao dưới nước ngay gần khách sạn.", "Set on the famous Tran Phu Boulevard, the hotel is just a few minutes' walk from Nha Trang Beach. Guests can easily stroll along the shore, sunbathe or join water sports activities right near the hotel.")),

                heading(ContentSection.STORY, of("Điểm tham quan lân cận", "Nearby attractions")),
                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách thuận tiện ghé thăm Tháp Bà Ponagar, Chợ Đầm, Nhà thờ Núi, Chùa Long Sơn và Viện Hải dương học. VinWonders Nha Trang, Hòn Chồng và các đảo nổi tiếng cũng nằm trong tầm di chuyển ngắn.", "From the hotel, travelers can conveniently visit Po Nagar Towers, Dam Market, Nha Trang Cathedral, Long Son Pagoda and the National Oceanographic Museum. VinWonders Nha Trang, Hon Chong and famous islands are also within a short trip.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
                paragraph(ContentSection.STORY,
                                of("Sân bay quốc tế Cam Ranh cách khách sạn khoảng 35 km, mất chừng 45 phút lái xe. Khách sạn hỗ trợ đặt dịch vụ đưa đón sân bay, taxi và cho thuê xe; khu vực trung tâm cũng thuận tiện đi bộ tới nhiều nhà hàng, quán cà phê và điểm mua sắm.", "Cam Ranh International Airport is about 35 km from the hotel, roughly a 45-minute drive. The hotel can arrange airport transfers, taxis and car rental; the central area is also walkable to many restaurants, cafes and shopping spots.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Brilliant Bay Nha Trang Hotel", "Why choose Brilliant Bay Nha Trang Hotel")),
                paragraph(ContentSection.STORY,
                                of("Với vị trí trung tâm gần biển, hồ bơi ngoài trời, dịch vụ 4 sao và mức giá hợp lý, Brilliant Bay Nha Trang Hotel mang đến trải nghiệm nghỉ dưỡng tiện nghi giữa lòng thành phố biển. Đây là điểm dừng chân lý tưởng để khám phá trọn vẹn Nha Trang.", "With its central location near the beach, an outdoor pool, 4-star service and reasonable rates, Brilliant Bay Nha Trang Hotel offers a comfortable stay in the heart of the coastal city. It is an ideal base to fully explore Nha Trang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi ngoài trời", "Outdoor pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ dọn phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-ntbb-superior-double")
                        .name(of("Superior Double", "Superior Double"))
                        .shortDescription(of("Phòng Superior 24 m², 1 giường đôi.", "Superior room 24 sqm, 1 double bed."))
                        .description(of("Phòng Superior 24 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior room 24 sqm, 1 double bed. Fully equipped for a comfortable stay."))
                        .areaSqm(24.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(25)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-e616a604dcc14d2b23a29e3b0da206ec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-4aa973693e2ece844ffb0f38e36e0b39.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntbb-superior-double-prepaid-nonref")
                                        .name(of("Superior Double - Không gồm bữa sáng", "Superior Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("900000"))
                                        .referencePricePerNight(new BigDecimal("1285714"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Giá ưu đãi", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntbb-superior-double-breakfast-flex")
                                        .name(of("Superior Double - Bao gồm bữa sáng", "Superior Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1035000"))
                                        .referencePricePerNight(new BigDecimal("1478571"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gồm bữa sáng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntbb-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 24 m², 2 giường đơn.", "Superior room 24 sqm, 2 single beds."))
                        .description(of("Phòng Superior 24 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior room 24 sqm, 2 single beds. Fully equipped for a comfortable stay."))
                        .areaSqm(24.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-0f6dc5af28992c31dff38297b922f3e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-63ef7be0f7e2717cf3de66c592feed86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntbb-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("950000"))
                                        .referencePricePerNight(new BigDecimal("1357143"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Giá ưu đãi", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntbb-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1092500"))
                                        .referencePricePerNight(new BigDecimal("1560714"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gồm bữa sáng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntbb-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 30 m² hướng biển, 1 giường Queen, có ban công.", "Deluxe room 30 sqm with sea view, 1 queen bed, balcony."))
                        .description(of("Phòng Deluxe 30 m² hướng biển, 1 giường Queen, có ban công. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 30 sqm with sea view, 1 queen bed, balcony. Fully equipped for a comfortable stay."))
                        .areaSqm(30.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-e616a604dcc14d2b23a29e3b0da206ec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-4aa973693e2ece844ffb0f38e36e0b39.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntbb-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1300000"))
                                        .referencePricePerNight(new BigDecimal("1857143"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Giá ưu đãi", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntbb-deluxe-sea-view-breakfast-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1495000"))
                                        .referencePricePerNight(new BigDecimal("2135714"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gồm bữa sáng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntbb-family-suite")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Family 40 m², 1 giường King + 1 giường đơn, cho 3 khách.", "Family room 40 sqm, 1 king + 1 single bed, for 3 guests."))
                        .description(of("Phòng Family 40 m², 1 giường King + 1 giường đơn, cho 3 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family room 40 sqm, 1 king + 1 single bed, for 3 guests. Fully equipped for a comfortable stay."))
                        .areaSqm(40.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-0f6dc5af28992c31dff38297b922f3e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20085583-63ef7be0f7e2717cf3de66c592feed86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntbb-family-suite-prepaid-nonref")
                                        .name(of("Family Suite - Không gồm bữa sáng", "Family Suite - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1800000"))
                                        .referencePricePerNight(new BigDecimal("2571429"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Giá ưu đãi", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntbb-family-suite-breakfast-flex")
                                        .name(of("Family Suite - Bao gồm bữa sáng", "Family Suite - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2070000"))
                                        .referencePricePerNight(new BigDecimal("2957143"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gồm bữa sáng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build());

                List<NearbyPlace> nearby = List.of(
                nearby("bai-bien-nha-trang", of("Bãi biển Nha Trang", "Nha Trang Beach"), of("Bãi biển", "Beach"), 300.0),
                nearby("thap-ba-ponagar", of("Tháp Bà Ponagar", "Po Nagar Towers"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("cho-dam", of("Chợ Đầm", "Dam Market"), of("Địa điểm lân cận", "Nearby places"), 2200.0),
                nearby("nha-tho-nui", of("Nhà thờ Núi (Nhà thờ Chánh tòa)", "Nha Trang Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("hon-chong", of("Hòn Chồng", "Hon Chong Promontory"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("vinwonders-nha-trang", of("VinWonders Nha Trang", "VinWonders Nha Trang"), of("Trung tâm giải trí", "Entertainment hub"), 10000.0),
                nearby("vien-hai-duong-hoc", of("Viện Hải dương học", "National Oceanographic Museum"), of("Địa điểm lân cận", "Nearby places"), 5500.0),
                nearby("chua-long-son", of("Chùa Long Sơn", "Long Son Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thap-tram-huong", of("Tháp Trầm Hương", "Tram Huong Tower"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("cong-vien-phu-dong", of("Công viên Phù Đổng", "Phu Dong Park"), of("Địa điểm lân cận", "Nearby places"), 900.0),
                nearby("quang-truong-2-thang-4", of("Quảng trường 2 Tháng 4", "April 2 Square"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
                nearby("hon-tam", of("Hòn Tằm", "Hon Tam Island"), of("Bãi biển", "Beach"), 8000.0),
                nearby("bai-dai", of("Bãi Dài", "Bai Dai Beach"), of("Bãi biển", "Beach"), 28000.0),
                nearby("i-resort-suoi-khoang-nong", of("I-resort suối khoáng nóng", "I-Resort Hot Spring"), of("Trung tâm giải trí", "Entertainment hub"), 6000.0),
                nearby("san-bay-cam-ranh", of("Sân bay quốc tế Cam Ranh", "Cam Ranh Airport"), of("Trung tâm giao thông", "Transport hub"), 33000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Hồ bơi ngoài trời, nhà hàng, máy lạnh, lễ tân 24h, WiFi miễn phí", "Outdoor pool, restaurant, air conditioning, 24-hour front desk, free WiFi"))
                                .distanceToCityCenterKm(1.0)
                                .popularAreaSummary(of("Bãi biển Nha Trang, Trần Phú, Chợ Đầm", "Nha Trang Beach, Tran Phu, Dam Market"))
                                .totalRooms(80)
                                .totalFloors(12)
                                .otherHighlightFacilities(of("Hồ bơi ngoài trời, đưa đón sân bay, giặt ủi, đổi ngoại tệ, nhận phòng cấp tốc.", "Outdoor pool, airport transfer, laundry, currency exchange, express check-in."))
                                .interestingPlacesSummary(of("Bãi biển Nha Trang, Tháp Bà Ponagar, VinWonders Nha Trang", "Nha Trang Beach, Po Nagar Towers, VinWonders Nha Trang"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhận phòng sớm", "Early check-in"))
                                                                .content(of("Hỗ trợ nhận phòng sớm tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in is subject to availability; please contact in advance."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
                                                                .title(of("Giấy tờ yêu cầu", "Required documents"))
                                                                .content(of("Vui lòng xuất trình CCCD/hộ chiếu hợp lệ khi nhận phòng.", "Please present a valid ID card/passport at check-in."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.SMOKING)
                                                                .title(of("Chính sách hút thuốc", "Smoking policy"))
                                                                .content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.OTHER)
                                                                .title(of("Trẻ em & gia đình", "Children & family"))
                                                                .content(of("Khách sạn thân thiện với gia đình: có hạng phòng Family rộng rãi phù hợp cho trẻ em.", "The hotel is family-friendly with spacious Family rooms suitable for children."))
                                                                .build()))
                                .build();

                TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
                                .defaultVatPercent(new BigDecimal("8"))
                                .defaultServiceFeePercent(new BigDecimal("5"))
                                .showPricePreTax(true)
                                .build();

                HotelFilterFacets filterFacets = HotelFilterFacets.builder()
                                .hasFreeCancellation(true)
                                .hasPayAtHotel(true)
                                .hasBreakfastIncluded(true)
                                .build();

                Instant now = Instant.parse("2024-01-01T00:00:00Z");
                PublisherInfo publisher = PublisherInfo.builder()
                                .partnerId("11")
                                .partnerName("Ông Trùm Bất Động Sản")
                                .partnerEmail("22110377@student.hcmute.edu.vn")
                                .partnerType(PartnerType.HOTEL_OWNER)
                                .createdAt(now)
                                .lastUpdatedAt(now)
                                .build();
                ModerationInfo moderation = ModerationInfo.builder()
                                .status(HotelStatus.APPROVED)
                                .reportCount(0)
                                .lastActionByAdminId("system-seed")
                                .lastActionAt(now)
                                .build();

                List<FaqItem> faqs = List.of(
                faq(of("Những tiện ích tại Brilliant Bay Nha Trang Hotel?", "What amenities does Brilliant Bay Nha Trang Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, hồ bơi ngoài trời, lễ tân 24h, thang máy và WiFi miễn phí (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, outdoor pool, 24-hour front desk, elevator and free WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Brilliant Bay Nha Trang Hotel?", "What are the check-in and check-out times at Brilliant Bay Nha Trang Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Khách sạn Brilliant Bay Nha Trang Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Brilliant Bay Nha Trang Hotel have?"),
                        of("Brilliant Bay Nha Trang Hotel là khách sạn đạt tiêu chuẩn 4 sao.", "Brilliant Bay Nha Trang Hotel is a 4-star hotel.")),
                faq(of("Tổng số phòng hiện có tại Brilliant Bay Nha Trang Hotel là bao nhiêu?", "How many rooms does Brilliant Bay Nha Trang Hotel have?"),
                        of("Tổng số phòng tại Brilliant Bay Nha Trang Hotel là khoảng 80 phòng.", "Brilliant Bay Nha Trang Hotel has a total of around 80 rooms.")),
                faq(of("Brilliant Bay Nha Trang Hotel có hồ bơi không?", "Does Brilliant Bay Nha Trang Hotel have a pool?"),
                        of("Có, Brilliant Bay Nha Trang Hotel có hồ bơi ngoài trời phục vụ khách lưu trú.", "Yes, Brilliant Bay Nha Trang Hotel has an outdoor pool for guests.")),
                faq(of("Brilliant Bay Nha Trang Hotel có dịch vụ đưa đón sân bay không?", "Does Brilliant Bay Nha Trang Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí) theo yêu cầu. Vui lòng liên hệ trực tiếp với khách sạn để biết thêm chi tiết.", "Yes, the hotel offers airport transfer (surcharge) on request. Please contact the hotel directly for more details.")),
                faq(of("Brilliant Bay Nha Trang Hotel có mức giá là bao nhiêu?", "How much does Brilliant Bay Nha Trang Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 900.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 900,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có phục vụ bữa sáng không?", "Does the hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn có cách bãi biển bao xa?", "How far is the hotel from the beach?"),
                        of("Khách sạn nằm trên đường Trần Phú, chỉ vài phút đi bộ là tới bãi biển Nha Trang.", "The hotel is on Tran Phu Street, just a few minutes' walk from Nha Trang Beach.")),
                faq(of("Khách sạn có hỗ trợ nhận phòng sớm không?", "Does the hotel support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy tình trạng phòng trống; vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel supports early check-in subject to availability; please contact in advance to arrange.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("nha-trang")
                                .cityName(of("Nha Trang", "Nha Trang"))
                                .districtName(of("Nha Trang", "Nha Trang"))
                                .wardName(of("Lộc Thọ", "Loc Tho"))
                                .addressLine(of("96B/15 Trần Phú, Lộc Thọ, Nha Trang, Khánh Hòa, Việt Nam, 650000", "96B/15 Tran Phu, Loc Tho Ward, Nha Trang, Khanh Hoa Province, Vietnam, 650000"))
                                .location(new double[] { 109.1987009, 12.2271742 })
                                .name(of("Brilliant Bay Nha Trang Hotel", "Brilliant Bay Nha Trang Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao trung tâm Nha Trang, gần bãi biển Trần Phú, có hồ bơi ngoài trời.", "4-star hotel in central Nha Trang near Tran Phu beach, with an outdoor pool."))
                                .description(of("Brilliant Bay Nha Trang Hotel là khách sạn 4 sao tọa lạc trên đường Trần Phú, phường Lộc Thọ, chỉ vài bước chân tới bãi biển Nha Trang. Khách sạn có hồ bơi ngoài trời, nhà hàng, lễ tân 24h và dịch vụ đưa đón sân bay.", "Brilliant Bay Nha Trang Hotel is a 4-star hotel on Tran Phu Street, Loc Tho Ward, just steps from Nha Trang Beach. It features an outdoor pool, a restaurant, a 24-hour front desk and airport transfer service."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("900000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1285714"))
                                .discountPercent(30)
                                .taxConfig(taxConfig)
                                .filterFacets(filterFacets)
                                .images(images)
                                .content(content)
                                .amenityCodes(hotelAmenities.stream()
                                                .map(AmenityCatalogDoc::getCode)
                                                .filter(Objects::nonNull)
                                                .map(String::trim)
                                                .map(s -> s.toUpperCase(Locale.ROOT))
                                                .distinct()
                                                .toList())
                                .roomTypes(roomTypes)
                                .nearbyPlaces(nearby)
                                .policy(policy)
                                .generalInfo(generalInfo)
                                .faqs(faqs)
                                .publisher(publisher)
                                .moderation(moderation)
                                .build();
        }
}
