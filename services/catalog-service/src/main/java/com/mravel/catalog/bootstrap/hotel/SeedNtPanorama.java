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
public class SeedNtPanorama {

        @Bean
        CommandLineRunner seedNtPanoramaRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedNtPanorama] start");
                        HotelDoc doc = hotelPanorama();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedNtPanorama] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedNtPanorama] done");
                };
        }

        // =====================================================================
        // PANORAMA NHA TRANG SANVILLA
        // (source: Traveloka hotelId 9000003314254; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelPanorama() {
                String slug = "panorama-nha-trang-sanvilla";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-0d64103d5387214cd7729f5d56b2e3e0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-119b5c9a97a9707f0a373b6b43804cd5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-0ef69e94bb6ccc65672d3196a559724a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-465cca3b976a769dacac1a5207879a5f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-bfd8ef2bae3fd0da72ac0543fe7ccaf7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cơ sở thể thao", "Sport facility", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-26e987287d1518dadefa1cbdc416d4ee.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-b5568fe7cef17ad5bab8a2b71c748c4f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-2d132360b216afcf64f031a1bad0f16c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-de7e5705c8f3da2dfd0d3b09579e6b3d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-f8abf4e882b53b706503386ff08903e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-1d23cab8560a6a521f5c444b8eb7bd3e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-c3a12072f4c55ecc75cc90a571f4a2ea.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-e63927b99f4aef924bcdb64beeda1a84.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20083240-328058731f206213dd26a751daf09c66.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20083240-6680fb6c7566b2eee3f0946286cdd1f2.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20083240-b74a90fd3111af002ddee5b59c517f14.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cơ sở thể thao", "Sport facility", false, 15));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 109.19539566314857, 12.238830539920958 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Panorama Nha Trang Sanvilla", "About Panorama Nha Trang Sanvilla")),

                paragraph(ContentSection.STORY,
                                of("Panorama Nha Trang Sanvilla là căn hộ khách sạn tiêu chuẩn 3 sao tọa lạc tại số 2 Nguyễn Thị Minh Khai, phường Lộc Thọ, trung tâm thành phố biển Nha Trang. Với vị trí chỉ vài bước chân ra Bãi biển Nha Trang, khu căn hộ là lựa chọn lý tưởng cho các cặp đôi, nhóm bạn và gia đình muốn tận hưởng kỳ nghỉ biển tiện nghi mà vẫn gần các điểm vui chơi, ẩm thực sầm uất.", "Panorama Nha Trang Sanvilla is a 3-star apartment hotel located at No. 2 Nguyen Thi Minh Khai, Loc Tho ward, in the heart of the beach city of Nha Trang. Just steps from Nha Trang Beach, the apartment is an ideal choice for couples, groups of friends and families seeking a comfortable beach holiday while staying close to lively entertainment and dining spots.")),

                heading(ContentSection.STORY, of("Các loại căn hộ & phòng nghỉ", "Apartment & room types")),

                paragraph(ContentSection.STORY,
                                of("Khu căn hộ cung cấp đa dạng hạng phòng từ Studio, căn hộ 1 phòng ngủ đến căn hộ 2 phòng ngủ, phù hợp cho cả khách lẻ lẫn gia đình. Các căn hộ hướng phố và hướng biển đều được trang bị máy lạnh, bếp nhỏ hoặc khu pha chế, minibar, máy sấy tóc, két an toàn và ban công riêng để khách thư giãn ngắm cảnh.", "The property offers a range of room categories from Studio and one-bedroom to two-bedroom apartments, suitable for both solo travelers and families. City-view and sea-view apartments come with air conditioning, a kitchenette or pantry, a minibar, a hair dryer, a safe and a private balcony for guests to relax and enjoy the view.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng tại tầng trệt phục vụ bữa sáng đa dạng món Việt và quốc tế, cùng thực đơn gọi món cho bữa trưa và tối. Khách có thể nhâm nhi cà phê, đồ uống mát lạnh tại quầy bar bên hồ bơi hoặc tiệm cà phê trong khuôn viên sau một ngày dạo biển.", "The ground-floor restaurant serves a varied breakfast of Vietnamese and international dishes, plus an à la carte menu for lunch and dinner. Guests can sip coffee or chilled drinks at the poolside bar or the on-site coffee shop after a day at the beach.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Khu căn hộ có hồ bơi, sân thượng, lễ tân 24 giờ, dịch vụ giặt ủi, giữ hành lý, thang máy và bảo vệ 24/7. Đội ngũ nhân viên đa ngôn ngữ luôn sẵn sàng hỗ trợ đặt tour, thuê xe và đưa đón sân bay (thu phí), giúp kỳ nghỉ của khách thêm trọn vẹn.", "The property features a swimming pool, a terrace, a 24-hour front desk, laundry service, luggage storage, an elevator and round-the-clock security. The multilingual staff are always ready to assist with tour bookings, car rental and airport transfer (surcharge) to make your stay complete.")),

                heading(ContentSection.STORY, of("Vị trí & biển", "Location & the beach")),

                paragraph(ContentSection.STORY,
                                of("Chỉ cách Bãi biển Nha Trang khoảng vài trăm mét, khu căn hộ cho phép khách dễ dàng tản bộ ra bờ cát trắng, tắm biển và đón bình minh trên vịnh Nha Trang. Khu vực Lộc Thọ cũng tập trung nhiều nhà hàng hải sản, quán bar và trung tâm mua sắm sôi động.", "Only a few hundred meters from Nha Trang Beach, the apartment lets guests easily stroll to the white sand, swim in the sea and catch sunrise over Nha Trang Bay. The Loc Tho area is also packed with seafood restaurants, bars and lively shopping centers.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                paragraph(ContentSection.STORY,
                                of("Từ khu căn hộ, khách mất khoảng 30–35 phút lái xe để đến Sân bay quốc tế Cam Ranh. Trung tâm thành phố, Chợ Đầm và ga Nha Trang đều nằm trong bán kính vài km, thuận tiện cho cả việc tham quan lẫn di chuyển đường dài. Dịch vụ thuê xe máy và taxi luôn sẵn sàng ngay tại sảnh.", "From the apartment, it takes about 30–35 minutes by car to reach Cam Ranh International Airport. The city center, Dam Market and Nha Trang Railway Station are all within a few kilometers, convenient for both sightseeing and long-distance travel. Motorbike rental and taxi services are readily available at the lobby.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Xung quanh khu căn hộ là hàng loạt điểm tham quan nổi tiếng của Nha Trang như Tháp Bà Ponagar, Nhà thờ Núi, Hòn Chồng, Chùa Long Sơn và Quảng trường 2 Tháng 4. Các khu vui chơi như VinWonders Nha Trang, Hòn Tằm hay suối khoáng nóng I-resort cũng chỉ cách một quãng ngắn.", "Around the apartment lie many famous Nha Trang attractions such as Po Nagar Towers, Nha Trang Cathedral, Hon Chong Promontory, Long Son Pagoda and April 2 Square. Entertainment areas like VinWonders Nha Trang, Hon Tam Island and I-Resort hot spring are also just a short distance away.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Panorama Nha Trang Sanvilla", "Why choose Panorama Nha Trang Sanvilla")),

                paragraph(ContentSection.STORY,
                                of("Chọn Panorama Nha Trang Sanvilla, khách có được vị trí trung tâm sát biển, không gian căn hộ rộng rãi với bếp riêng, hồ bơi và dịch vụ chu đáo với mức giá hợp lý. Đây là điểm dừng chân lý tưởng để vừa nghỉ dưỡng bên biển vừa khám phá thành phố Nha Trang sôi động.", "Choosing Panorama Nha Trang Sanvilla gives guests a central beachfront location, spacious apartments with a private kitchen, a swimming pool and attentive service at a reasonable price. It is an ideal base to both relax by the sea and explore the vibrant city of Nha Trang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi", "Swimming pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM / banking"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-ntpanorama-studio-city-view")
                        .name(of("Studio City View", "Studio City View"))
                        .shortDescription(of("Căn hộ Studio 28 m² hướng phố, 1 giường Queen, có khu pha chế.", "28 sqm studio with city view, 1 queen bed and a pantry."))
                        .description(of("Căn hộ Studio 28 m² hướng phố, 1 giường Queen, có khu pha chế. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "28 sqm studio with city view, 1 queen bed and a pantry. Fully equipped for a comfortable stay."))
                        .areaSqm(28.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-0d64103d5387214cd7729f5d56b2e3e0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-119b5c9a97a9707f0a373b6b43804cd5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntpanorama-studio-city-view-prepaid-nonref")
                                        .name(of("Studio City View - Không gồm bữa sáng", "Studio City View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("650000"))
                                        .referencePricePerNight(new BigDecimal("928571"))
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
                                        .id("rt-ntpanorama-studio-city-view-breakfast-flex")
                                        .name(of("Studio City View - Bao gồm bữa sáng", "Studio City View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("747500"))
                                        .referencePricePerNight(new BigDecimal("1067857"))
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
                        .id("rt-ntpanorama-studio-sea-view")
                        .name(of("Studio Sea View", "Studio Sea View"))
                        .shortDescription(of("Căn hộ Studio 30 m² hướng biển, 1 giường Queen, ban công riêng.", "30 sqm studio with sea view, 1 queen bed and a private balcony."))
                        .description(of("Căn hộ Studio 30 m² hướng biển, 1 giường Queen, ban công riêng. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "30 sqm studio with sea view, 1 queen bed and a private balcony. Fully equipped for a comfortable stay."))
                        .areaSqm(30.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-119b5c9a97a9707f0a373b6b43804cd5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-465cca3b976a769dacac1a5207879a5f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntpanorama-studio-sea-view-prepaid-nonref")
                                        .name(of("Studio Sea View - Không gồm bữa sáng", "Studio Sea View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("850000"))
                                        .referencePricePerNight(new BigDecimal("1214286"))
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
                                        .id("rt-ntpanorama-studio-sea-view-breakfast-flex")
                                        .name(of("Studio Sea View - Bao gồm bữa sáng", "Studio Sea View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("977500"))
                                        .referencePricePerNight(new BigDecimal("1396429"))
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
                        .id("rt-ntpanorama-one-bedroom-sea-view")
                        .name(of("One-Bedroom Apartment Sea View", "One-Bedroom Apartment Sea View"))
                        .shortDescription(of("Căn hộ 1 phòng ngủ 45 m² hướng biển, 1 giường King, phòng khách và bếp riêng.", "45 sqm one-bedroom sea-view apartment, 1 king bed with separate living room and kitchen."))
                        .description(of("Căn hộ 1 phòng ngủ 45 m² hướng biển, 1 giường King, phòng khách và bếp riêng. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "45 sqm one-bedroom sea-view apartment, 1 king bed with separate living room and kitchen. Fully equipped for a comfortable stay."))
                        .areaSqm(45.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-465cca3b976a769dacac1a5207879a5f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-2d132360b216afcf64f031a1bad0f16c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntpanorama-one-bedroom-sea-view-prepaid-nonref")
                                        .name(of("One-Bedroom Apartment Sea View - Không gồm bữa sáng", "One-Bedroom Apartment Sea View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1150000"))
                                        .referencePricePerNight(new BigDecimal("1642857"))
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
                                        .id("rt-ntpanorama-one-bedroom-sea-view-breakfast-flex")
                                        .name(of("One-Bedroom Apartment Sea View - Bao gồm bữa sáng", "One-Bedroom Apartment Sea View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1322500"))
                                        .referencePricePerNight(new BigDecimal("1889286"))
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
                        .id("rt-ntpanorama-two-bedroom-family")
                        .name(of("Two-Bedroom Family Apartment", "Two-Bedroom Family Apartment"))
                        .shortDescription(of("Căn hộ 2 phòng ngủ 65 m², 1 giường King và 2 giường đơn, phù hợp cho gia đình.", "65 sqm two-bedroom apartment, 1 king bed and 2 single beds, ideal for families."))
                        .description(of("Căn hộ 2 phòng ngủ 65 m², 1 giường King và 2 giường đơn, phù hợp cho gia đình. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "65 sqm two-bedroom apartment, 1 king bed and 2 single beds, ideal for families. Fully equipped for a comfortable stay."))
                        .areaSqm(65.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(3)
                        .bedLayoutDescription(of("1 giường King và 2 giường đơn", "1 king bed and 2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(4)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-2d132360b216afcf64f031a1bad0f16c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029194-c3a12072f4c55ecc75cc90a571f4a2ea.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntpanorama-two-bedroom-family-prepaid-nonref")
                                        .name(of("Two-Bedroom Family Apartment - Không gồm bữa sáng", "Two-Bedroom Family Apartment - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1650000"))
                                        .referencePricePerNight(new BigDecimal("2357143"))
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
                                        .id("rt-ntpanorama-two-bedroom-family-breakfast-flex")
                                        .name(of("Two-Bedroom Family Apartment - Bao gồm bữa sáng", "Two-Bedroom Family Apartment - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1897500"))
                                        .referencePricePerNight(new BigDecimal("2710714"))
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
                nearby("nha-tho-nui-nha-trang", of("Nhà thờ Núi (Nhà thờ Chánh tòa)", "Nha Trang Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("hon-chong", of("Hòn Chồng", "Hon Chong Promontory"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("vinwonders-nha-trang", of("VinWonders Nha Trang", "VinWonders Nha Trang"), of("Trung tâm giải trí", "Entertainment hub"), 12000.0),
                nearby("vien-hai-duong-hoc", of("Viện Hải dương học", "National Oceanographic Museum"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("chua-long-son", of("Chùa Long Sơn", "Long Son Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thap-tram-huong", of("Tháp Trầm Hương", "Tram Huong Tower"), of("Địa điểm lân cận", "Nearby places"), 900.0),
                nearby("cong-vien-phu-dong", of("Công viên Phù Đổng", "Phu Dong Park"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("hon-tam", of("Hòn Tằm", "Hon Tam Island"), of("Trung tâm giải trí", "Entertainment hub"), 7000.0),
                nearby("bai-dai-nha-trang", of("Bãi Dài", "Bai Dai Beach"), of("Bãi biển", "Beach"), 25000.0),
                nearby("i-resort-suoi-khoang-nong", of("I-resort suối khoáng nóng", "I-Resort Hot Spring"), of("Trung tâm giải trí", "Entertainment hub"), 5500.0),
                nearby("quang-truong-2-thang-4", of("Quảng trường 2 Tháng 4", "April 2 Square"), of("Địa điểm lân cận", "Nearby places"), 1000.0),
                nearby("san-bay-cam-ranh", of("Sân bay quốc tế Cam Ranh", "Cam Ranh International Airport"), of("Trung tâm giao thông", "Transport hub"), 32000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Hồ bơi, nhà hàng, máy lạnh, lễ tân 24h, WiFi miễn phí", "Pool, restaurant, air conditioning, 24-hour front desk, free WiFi"))
                                .distanceToCityCenterKm(1.0)
                                .popularAreaSummary(of("Bãi biển Nha Trang, Chợ Đầm, Tháp Trầm Hương", "Nha Trang Beach, Dam Market, Tram Huong Tower"))
                                .totalRooms(40)
                                .totalFloors(20)
                                .otherHighlightFacilities(of("Hồ bơi, quầy bar, đưa đón sân bay, cho thuê xe, dịch vụ giặt ủi.", "Pool, bar, airport transfer, car rental, laundry service."))
                                .interestingPlacesSummary(of("Bãi biển Nha Trang, Tháp Bà Ponagar, VinWonders Nha Trang", "Nha Trang Beach, Po Nagar Towers, VinWonders Nha Trang"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 30))
                                .checkOutUntil(LocalTime.of(11, 45))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 14:30. Giờ trả phòng: trước 11:45.", "Check-in: from 14:30. Check-out: before 11:45."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhận phòng sớm & trả phòng trễ", "Early check-in & late check-out"))
                                                                .content(of("Hỗ trợ nhận phòng sớm và trả phòng trễ tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in and late check-out are subject to availability; please contact in advance."))
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
                                                                .content(of("Khu căn hộ thân thiện với gia đình, có các hạng căn hộ rộng rãi phù hợp cho cả nhà.", "The property is family-friendly with spacious apartment types suitable for the whole family."))
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
                                .lastActionAt(Instant.now())
                                .build();

                List<FaqItem> faqs = List.of(
                faq(of("Những tiện ích tại Panorama Nha Trang Sanvilla?", "What amenities does Panorama Nha Trang Sanvilla offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, hồ bơi, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, swimming pool, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Panorama Nha Trang Sanvilla?", "What are the check-in and check-out times at Panorama Nha Trang Sanvilla?"),
                        of("Thời gian nhận phòng từ 14:30 và trả phòng trước 11:45.", "Check-in is from 14:30 and check-out is before 11:45.")),
                faq(of("Panorama Nha Trang Sanvilla có phục vụ ăn sáng không?", "Does Panorama Nha Trang Sanvilla serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Panorama Nha Trang Sanvilla đạt tiêu chuẩn mấy sao?", "What star rating does Panorama Nha Trang Sanvilla have?"),
                        of("Panorama Nha Trang Sanvilla là căn hộ khách sạn đạt tiêu chuẩn 3 sao.", "Panorama Nha Trang Sanvilla is a 3-star apartment hotel.")),
                faq(of("Panorama Nha Trang Sanvilla có bao nhiêu tầng?", "How many floors does Panorama Nha Trang Sanvilla have?"),
                        of("Khu căn hộ có tổng cộng 20 tầng.", "The property has a total of 20 floors.")),
                faq(of("Tổng số phòng hiện có tại Panorama Nha Trang Sanvilla là bao nhiêu?", "How many rooms does Panorama Nha Trang Sanvilla have in total?"),
                        of("Tổng số phòng tại khu căn hộ là 40 phòng.", "The property has a total of 40 rooms.")),
                faq(of("Panorama Nha Trang Sanvilla có chỗ đậu xe không?", "Does Panorama Nha Trang Sanvilla have parking?"),
                        of("Có, khu căn hộ có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp để xác nhận thông tin chi tiết.", "Yes, the property provides parking. Please contact directly to confirm the details.")),
                faq(of("Panorama Nha Trang Sanvilla có hỗ trợ nhận phòng sớm không?", "Does Panorama Nha Trang Sanvilla support early check-in?"),
                        of("Có, khu căn hộ hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trước để sắp xếp.", "Yes, early check-in is supported subject to availability. Please contact in advance to arrange.")),
                faq(of("Panorama Nha Trang Sanvilla có hỗ trợ trả phòng trễ không?", "Does Panorama Nha Trang Sanvilla support late check-out?"),
                        of("Có, khu căn hộ hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trước để sắp xếp.", "Yes, late check-out is supported subject to availability. Please contact in advance to arrange.")),
                faq(of("Panorama Nha Trang Sanvilla có hồ bơi không?", "Does Panorama Nha Trang Sanvilla have a pool?"),
                        of("Có, khu căn hộ có hồ bơi và quầy bar bên hồ bơi cho khách thư giãn.", "Yes, the property has a swimming pool and a poolside bar for guests to relax.")),
                faq(of("Panorama Nha Trang Sanvilla cách Bãi biển Nha Trang bao xa?", "How far is Panorama Nha Trang Sanvilla from Nha Trang Beach?"),
                        of("Khu căn hộ chỉ cách Bãi biển Nha Trang vài trăm mét, khách có thể đi bộ ra biển trong ít phút.", "The property is only a few hundred meters from Nha Trang Beach; guests can walk to the sea in a few minutes.")),
                faq(of("Panorama Nha Trang Sanvilla có đưa đón sân bay không?", "Does Panorama Nha Trang Sanvilla offer airport transfer?"),
                        of("Có, khu căn hộ cung cấp dịch vụ đưa đón sân bay (thu phí); Sân bay quốc tế Cam Ranh cách khoảng 30–35 phút lái xe.", "Yes, the property offers airport transfer (surcharge); Cam Ranh International Airport is about a 30–35 minute drive away.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("nha-trang")
                                .cityName(of("Nha Trang", "Nha Trang"))
                                .districtName(of("Lộc Thọ", "Loc Tho"))
                                .wardName(of("Lộc Thọ", "Loc Tho"))
                                .addressLine(of("Số 2 Nguyễn Thị Minh Khai, Phước Hòa, Lộc Thọ, Nha Trang, Khánh Hòa, Việt Nam, 57106", "No.2 Nguyen Thi Minh Khai , Phuoc Hoa, Nha Trang, Loc Tho ward, Nha Trang, Khanh Hoa Province, Vietnam, 57106"))
                                .location(new double[] { 109.19539566314857, 12.238830539920958 })
                                .name(of("Panorama Nha Trang Sanvilla", "Panorama Nha Trang Sanvilla"))
                                .slug(slug)
                                .hotelType(HotelType.APARTMENT)
                                .starRating(3)
                                .shortDescription(of("Căn hộ khách sạn 3 sao tại trung tâm Nha Trang, sát Bãi biển Nha Trang.", "3-star apartment hotel in central Nha Trang, steps from Nha Trang Beach."))
                                .description(of("Panorama Nha Trang Sanvilla là căn hộ khách sạn 3 sao tại phường Lộc Thọ, trung tâm Nha Trang, chỉ vài bước chân ra biển, với hồ bơi, nhà hàng và các căn hộ tiện nghi có bếp riêng.", "Panorama Nha Trang Sanvilla is a 3-star apartment hotel in Loc Tho ward, central Nha Trang, just steps from the beach, featuring a pool, a restaurant and comfortable apartments with a private kitchen."))
                                .defaultCheckInTime(LocalTime.of(14, 30))
                                .defaultCheckOutTime(LocalTime.of(11, 45))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("650000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("928571"))
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
