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
public class SeedHcmPassionLuxAirport {

        @Bean
        CommandLineRunner seedHcmPassionLuxAirportRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHcmPassionLuxAirport] start");
                        HotelDoc doc = hotelPassionLux();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHcmPassionLuxAirport] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHcmPassionLuxAirport] done");
                };
        }

        // =====================================================================
        // THE PASSION LUX AIRPORT HOTEL APARTMENT - FREE AIRPORT TRANSFER
        // (source: Traveloka hotelId 9000005772217; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelPassionLux() {
                String slug = "the-passion-lux-airport-hotel-apartment-free-airport-transfer";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-b16403f9f7113e5877b562e8f26b0ce0.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-27194f4c204198f588574ffcf3e3d89b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-d157e50d0eb1fe3b0b97249d4466bf52.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-7655c9763cc60dbcb2692fe42fa1ae51.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3c223ec48618b736bc36628d0d1a5b07.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-1d07efd4eaa18492087643eece39b19a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-81c3b4819794ed33a45f93066a4996b6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-c1193eb84c54d07cccd574f47cde3e6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3c90435308e77fafaae649590c29345a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-823329b888b8e42abff2af11ac96da12.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-6169e81054a3a237b1729da5bb472ce5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3619a5aaf683f913ee5e83218632fd02.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-c7fc923e4dab7a7013cd20c2ba9343b1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-c541fc3f4d31ccbd827f08a73e0ef514.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-dd8c16722fd53070896804512f59261f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-1fb8591032c151c2bbc447aaf4573b71.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-f96afff51b659c9c525bae5e64959229.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-075560beb12022d1a61d406f33e61b5c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-0823790e3ae616f454be2f09d200fd0f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-0b6e71fc887f96c1a028cbbc5e7f5780.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-851ede2d269ccaebe73ec6f8e9e1ccd8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-10ad6b9dad50b2c2a401fbf835d9cdc7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3deddcfd405119e04a2dbae367f1a997.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-8d77cc3894dfabaa64cb949d7210710a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-fa531f6c286aa928d00ddfc5e7a36756.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-e38dd9b5cfaec03be5570181974c3787.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-995d0498b1bee1e4f578675c0392e6a4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-694963c11cf93498f2b1f4eb7dc30c64.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 27));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 106.6603219, 10.8006026 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về The Passion Lux Airport Hotel Apartment", "About The Passion Lux Airport Hotel Apartment")),

                paragraph(ContentSection.STORY,
                                of("The Passion Lux Airport Hotel Apartment - Free Airport Transfer là khách sạn căn hộ đạt chuẩn 3 sao tọa lạc trên đường Cộng Hòa, Phường 4, Quận Tân Bình, chỉ cách Sân bay Quốc tế Tân Sơn Nhất vài phút di chuyển. Với 48 phòng trải trên 6 tầng cùng dịch vụ đưa đón sân bay miễn phí, khách sạn là lựa chọn lý tưởng cho khách công tác, khách quá cảnh và du khách cần một điểm dừng chân tiện lợi tại trung tâm Thành phố Hồ Chí Minh.", "The Passion Lux Airport Hotel Apartment - Free Airport Transfer is a 3-star hotel apartment located on Cong Hoa Street, Ward 4, Tan Binh District, just minutes from Tan Son Nhat International Airport. With 48 rooms across 6 floors and a free airport shuttle, the hotel is an ideal choice for business travelers, transit guests and tourists looking for a convenient base in central Ho Chi Minh City.")),

                heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Family, đáp ứng nhu cầu của cả khách lẻ lẫn gia đình. Mỗi phòng đều được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, phòng tắm vòi sen riêng và Wi-Fi miễn phí, mang lại không gian nghỉ ngơi tiện nghi và yên tĩnh.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to Family rooms, catering to both solo travelers and families. Every room is equipped with air conditioning, a minibar, a work desk, a hair dryer, a private shower bathroom and free Wi-Fi, providing a comfortable and quiet space to rest.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn phục vụ bữa sáng và các bữa ăn nhẹ ngay tại chỗ, cùng dịch vụ phòng 24 giờ để khách có thể thưởng thức món ăn bất cứ lúc nào. Khu vực Tân Bình xung quanh khách sạn còn có nhiều nhà hàng, quán cà phê và quán ăn địa phương phong phú, thuận tiện cho việc khám phá ẩm thực Sài Gòn.", "The hotel serves breakfast and light meals on-site, along with 24-hour room service so guests can enjoy a meal at any time. The surrounding Tan Binh area offers a wealth of restaurants, cafes and local eateries, making it convenient to explore Saigon's cuisine.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn có lễ tân 24 giờ, dịch vụ giặt ủi, giữ hành lý, dịch vụ concierge và hỗ trợ đặt tour. Khách lưu trú còn được sử dụng bàn bi-da, máy ATM, cửa hàng tiện lợi và dịch vụ làm tóc ngay trong khuôn viên, đáp ứng trọn vẹn nhu cầu trong suốt kỳ nghỉ.", "The hotel features a 24-hour front desk, laundry service, luggage storage, concierge service and tour booking assistance. Guests can also enjoy a billiards table, an ATM, a convenience shop and a hair salon on-site, fully meeting their needs throughout the stay.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách dễ dàng di chuyển đến các điểm nổi tiếng của trung tâm Sài Gòn như Chợ Bến Thành, Nhà thờ Đức Bà, Phố đi bộ Nguyễn Huệ và Dinh Độc Lập trong khoảng 20-30 phút lái xe. Vị trí gần sân bay giúp việc kết nối tới các quận khác và các điểm du lịch trở nên thuận tiện.", "From the hotel, guests can easily reach famous attractions in central Saigon such as Ben Thanh Market, Notre-Dame Cathedral, Nguyen Hue Walking Street and Independence Palace within about 20-30 minutes by car. Its proximity to the airport makes connecting to other districts and tourist sites convenient.")),

                heading(ContentSection.STORY, of("Di chuyển & đưa đón sân bay", "Transport & airport transfer")),

                paragraph(ContentSection.STORY,
                                of("Điểm nổi bật của khách sạn là dịch vụ đưa đón sân bay miễn phí, giúp khách di chuyển dễ dàng giữa Sân bay Tân Sơn Nhất và khách sạn. Ngoài ra khách sạn còn hỗ trợ đặt taxi, cho thuê xe và cung cấp chỗ đậu xe cho khách lưu trú.", "A highlight of the hotel is its free airport shuttle, helping guests travel easily between Tan Son Nhat Airport and the hotel. The hotel also assists with taxi booking, car rental and provides parking for guests.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Quận Tân Bình là khu vực sầm uất với nhiều trung tâm thương mại, chợ, siêu thị và khu ẩm thực. Du khách có thể tản bộ khám phá nhịp sống địa phương, mua sắm hoặc thưởng thức các món ăn đường phố đặc trưng của Sài Gòn ngay gần khách sạn.", "Tan Binh District is a bustling area with many shopping centers, markets, supermarkets and food courts. Guests can stroll to explore local life, shop or enjoy Saigon's signature street food right near the hotel.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn The Passion Lux Airport", "Why choose The Passion Lux Airport")),

                paragraph(ContentSection.STORY,
                                of("Với vị trí đắc địa gần sân bay, dịch vụ đưa đón miễn phí, phòng ốc tiện nghi và mức giá hợp lý, The Passion Lux Airport Hotel Apartment là lựa chọn lý tưởng cho cả khách công tác lẫn du lịch. Sự kết hợp giữa tiện lợi, sạch sẽ và dịch vụ thân thiện mang lại trải nghiệm lưu trú đáng nhớ tại Thành phố Hồ Chí Minh.", "With a prime location near the airport, a free shuttle service, comfortable rooms and reasonable prices, The Passion Lux Airport Hotel Apartment is an ideal choice for both business and leisure travelers. The combination of convenience, cleanliness and friendly service delivers a memorable stay in Ho Chi Minh City.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe (thu phí)", "Parking (surcharge)"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay miễn phí", "Free airport transfer"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("room_service_limited", of("Dịch vụ phòng 24 giờ", "24-hour room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe", "Car rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng nhanh", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("express_checkout", of("Trả phòng nhanh", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("wifi", of("WiFi tại khu vực chung", "WiFi in public areas"), AmenityGroup.INTERNET, AmenitySection.INTERNET, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-passionlux-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 18 sqm, 1 double bed, with essential amenities."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 18 sqm, 1 double bed, with essential amenities."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(14)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3c90435308e77fafaae649590c29345a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0)))
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
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-passionlux-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("800000"))
                                        .referencePricePerNight(new BigDecimal("1142857"))
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
                                        .id("rt-passionlux-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("920000"))
                                        .referencePricePerNight(new BigDecimal("1314286"))
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
                        .id("rt-passionlux-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 22 m², 2 giường đơn, không gian rộng rãi hơn.", "Superior room 22 sqm, 2 single beds, with more spacious space."))
                        .description(of("Phòng Superior 22 m², 2 giường đơn, không gian rộng rãi hơn.", "Superior room 22 sqm, 2 single beds, with more spacious space."))
                        .areaSqm(22.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-823329b888b8e42abff2af11ac96da12.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-6169e81054a3a237b1729da5bb472ce5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-passionlux-superior-twin-prepaid-nonref")
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
                                        .id("rt-passionlux-superior-twin-breakfast-flex")
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
                        .id("rt-passionlux-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen, tiện nghi nâng cấp.", "Deluxe room 26 sqm, 1 queen bed, with upgraded amenities."))
                        .description(of("Phòng Deluxe 26 m², 1 giường Queen, tiện nghi nâng cấp.", "Deluxe room 26 sqm, 1 queen bed, with upgraded amenities."))
                        .areaSqm(26.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-6169e81054a3a237b1729da5bb472ce5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3619a5aaf683f913ee5e83218632fd02.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-passionlux-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
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
                                        .id("rt-passionlux-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
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
                        .id("rt-passionlux-family-suite")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Family 34 m², 1 giường Queen và 1 giường đơn, phù hợp cho gia đình.", "Family room 34 sqm, 1 queen and 1 single bed, suitable for families."))
                        .description(of("Phòng Family 34 m², 1 giường Queen và 1 giường đơn, phù hợp cho gia đình.", "Family room 34 sqm, 1 queen and 1 single bed, suitable for families."))
                        .areaSqm(34.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-3619a5aaf683f913ee5e83218632fd02.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10022596-c7fc923e4dab7a7013cd20c2ba9343b1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-passionlux-family-suite-prepaid-nonref")
                                        .name(of("Family Suite - Không gồm bữa sáng", "Family Suite - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1450000"))
                                        .referencePricePerNight(new BigDecimal("2071429"))
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
                                        .id("rt-passionlux-family-suite-breakfast-flex")
                                        .name(of("Family Suite - Bao gồm bữa sáng", "Family Suite - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1667500"))
                                        .referencePricePerNight(new BigDecimal("2382143"))
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
                nearby("san-bay-tan-son-nhat", of("Sân bay Tân Sơn Nhất", "Tan Son Nhat Airport"), of("Trung tâm giao thông", "Transport hub"), 1800.0),
                nearby("cho-ben-thanh", of("Chợ Bến Thành", "Ben Thanh Market"), of("Địa điểm lân cận", "Nearby places"), 7000.0),
                nearby("nha-tho-duc-ba", of("Nhà thờ Đức Bà", "Notre-Dame Cathedral"), of("Địa điểm lân cận", "Nearby places"), 7500.0),
                nearby("buu-dien-thanh-pho", of("Bưu điện Thành phố", "Central Post Office"), of("Địa điểm lân cận", "Nearby places"), 7600.0),
                nearby("pho-di-bo-nguyen-hue", of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("dinh-doc-lap", of("Dinh Độc Lập", "Independence Palace"), of("Địa điểm lân cận", "Nearby places"), 7200.0),
                nearby("bao-tang-chung-tich-chien-tranh", of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"), of("Khác", "Other"), 6800.0),
                nearby("toa-nha-bitexco", of("Tòa nhà Bitexco", "Bitexco Financial Tower"), of("Địa điểm lân cận", "Nearby places"), 8500.0),
                nearby("landmark-81", of("Landmark 81", "Landmark 81"), of("Địa điểm lân cận", "Nearby places"), 9500.0),
                nearby("pho-tay-bui-vien", of("Phố Tây Bùi Viện", "Bui Vien Street"), of("Trung tâm giải trí", "Entertainment hub"), 7800.0),
                nearby("thao-cam-vien", of("Thảo Cầm Viên", "Saigon Zoo"), of("Trung tâm giải trí", "Entertainment hub"), 8200.0),
                nearby("chua-ngoc-hoang", of("Chùa Ngọc Hoàng", "Jade Emperor Pagoda"), of("Khác", "Other"), 6500.0),
                nearby("cho-binh-tay", of("Chợ Bình Tây", "Binh Tay Market"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("cau-mong", of("Cầu Mống", "Mong Bridge"), of("Khác", "Other"), 8300.0),
                nearby("cong-vien-tao-dan", of("Công viên Tao Đàn", "Tao Dan Park"), of("Khác", "Other"), 6900.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, lễ tân 24h, chỗ đậu xe, đưa đón sân bay miễn phí, WiFi miễn phí", "Air conditioning, 24-hour front desk, parking, free airport transfer, free WiFi"))
                                .distanceToCityCenterKm(7.0)
                                .popularAreaSummary(of("Sân bay Tân Sơn Nhất, Chợ Bến Thành, Phố đi bộ Nguyễn Huệ", "Tan Son Nhat Airport, Ben Thanh Market, Nguyen Hue Walking Street"))
                                .totalRooms(48)
                                .totalFloors(6)
                                .otherHighlightFacilities(of("Đưa đón sân bay miễn phí, dịch vụ phòng 24h, bàn bi-da, ATM, cửa hàng tiện lợi.", "Free airport transfer, 24-hour room service, billiards, ATM, convenience shop."))
                                .interestingPlacesSummary(of("Chợ Bến Thành, Nhà thờ Đức Bà, Dinh Độc Lập", "Ben Thanh Market, Notre-Dame Cathedral, Independence Palace"))
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
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay miễn phí; vui lòng liên hệ trước để được sắp xếp.", "The hotel offers free airport transfer; please contact in advance to arrange."))
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
                faq(of("Những tiện ích tại The Passion Lux Airport Hotel Apartment?", "What amenities does The Passion Lux Airport Hotel Apartment offer?"),
                        of("Các tiện ích bao gồm máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi miễn phí, đưa đón sân bay miễn phí, dịch vụ phòng 24h và giặt ủi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a 24-hour front desk, parking, free WiFi, free airport transfer, 24-hour room service and laundry (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của khách sạn?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Khách sạn đạt tiêu chuẩn mấy sao?", "What star rating does the hotel have?"),
                        of("The Passion Lux Airport Hotel Apartment là khách sạn đạt tiêu chuẩn 3 sao.", "The Passion Lux Airport Hotel Apartment is a 3-star hotel.")),
                faq(of("Khách sạn có bao nhiêu tầng?", "How many floors does the hotel have?"),
                        of("Khách sạn có tổng cộng 6 tầng.", "The hotel has a total of 6 floors.")),
                faq(of("Tổng số phòng hiện có tại khách sạn là bao nhiêu?", "How many rooms does the hotel have in total?"),
                        of("Khách sạn có tổng cộng 48 phòng.", "The hotel has a total of 48 rooms.")),
                faq(of("Khách sạn có chỗ đậu xe không?", "Does the hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
                faq(of("Khách sạn có hỗ trợ nhận phòng sớm không?", "Does the hotel support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Khách sạn có cung cấp dịch vụ trả phòng trễ không?", "Does the hotel offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Khách sạn có dịch vụ đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay miễn phí giữa khách sạn và Sân bay Tân Sơn Nhất. Vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel offers free airport transfer between the hotel and Tan Son Nhat Airport. Please contact in advance to arrange.")),
                faq(of("Khách sạn có mức giá là bao nhiêu?", "How much does the hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 800.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 800,000 VND per night. Prices may vary depending on the date and promotions.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ho-chi-minh-city")
                                .cityName(of("Hồ Chí Minh", "Ho Chi Minh City"))
                                .districtName(of("Tân Bình", "Tan Binh"))
                                .wardName(of("Phường 4", "Ward 4"))
                                .addressLine(of("Số 3 Đường Cộng Hòa, Phường 4, Quận Tân Bình, Thành phố Hồ Chí Minh, Việt Nam, 700000", "3 Cong Hoa, Tan Binh District, Ho Chi Minh City, Vietnam, 700000"))
                                .location(new double[] { 106.6603219, 10.8006026 })
                                .name(of("The Passion Lux Airport Hotel Apartment - Free Airport Transfer", "The Passion Lux Airport Hotel Apartment - Free Airport Transfer"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn căn hộ 3 sao gần sân bay Tân Sơn Nhất, đưa đón sân bay miễn phí.", "3-star hotel apartment near Tan Son Nhat Airport with free airport transfer."))
                                .description(of("The Passion Lux Airport Hotel Apartment - Free Airport Transfer là khách sạn 3 sao tại Quận Tân Bình, TP. Hồ Chí Minh với phòng ốc tiện nghi, dịch vụ đưa đón sân bay miễn phí và vị trí thuận tiện gần sân bay Tân Sơn Nhất.", "The Passion Lux Airport Hotel Apartment - Free Airport Transfer is a 3-star hotel in Tan Binh District, Ho Chi Minh City with comfortable rooms, free airport transfer and a convenient location near Tan Son Nhat Airport."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("800000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1142857"))
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
