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
public class SeedNtSunCity {

        @Bean
        CommandLineRunner seedNtSunCityRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedNtSunCity] start");
                        HotelDoc doc = hotelSunCity();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedNtSunCity] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedNtSunCity] done");
                };
        }

        // =====================================================================
        // SUN CITY HOTEL NHA TRANG
        // (source: Traveloka hotelId 1000000430754; phong va gia duoc dung them)
        // =====================================================================
        private static HotelDoc hotelSunCity() {
                String slug = "sun-city-hotel-nha-trang";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-0d58c33b40873bc533cea68d2197b751.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2781x1854-FIT_AND_TRIM-6cc53a9a5dcaf9faff5e0e71405edbb3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2679x4018-FIT_AND_TRIM-2ecf3bcf7cd917889429413c53b572ba.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Dịch vụ", "Hotel services", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-3350x2234-FIT_AND_TRIM-540c1a11e7521a8f530843137d262d77.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Dịch vụ", "Hotel services", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-3459x2306-FIT_AND_TRIM-6f58687af519bd67ff37281698b8f514.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-de1b451d5e306f0e10bf3703a0d960e8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-8f5a96ebfd7c1120c13b124631f85dda.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-a1c4f052584c615bb8aaad3b863bf209.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-740f1704158f9bd59dc50c325af11ca4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2969x2017-FIT_AND_TRIM-bcfaed1d25707a9d94516269125491ce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-e67f48c48c16688338feecd254639dc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-3476x2317-FIT_AND_TRIM-6143edf34553ba04a9164059da06e294.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-bc4ba6bd20f4f8d818669bbe2c31f251.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-6ef480f9fdde75a5271db04a72c71c4f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-225a74bcecd94e1e102ee1f30d3e06de.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-ab99a6c88d5398a92aded9d18686674c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-b267f94ac6a1ce0caf925fcccbf61e4e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-39880a0a193348e032b7a87b211ee2c7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-69fc547e2484cb03519f0a1a23ba3209.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-f4481f3ec80b0b83ace4b7387e1a0979.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-602345bee9023874c8653646237726b8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2593x1962-FIT_AND_TRIM-30bef579a6edf52c9ddc4a7563facf6b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2969x2017-FIT_AND_TRIM-7995af2cb788ce1a86a949cabbb18dcc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-cc5f68748f69df8325b699757c9d707c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-7dcf3f937503a6237b4ea337764cf1ec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-f1bd8017ab1f8f2700618b2cf65c2aa3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-afa7d9aef63751754fc4023ba4c82222.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-9a9aecaa78b53bbdb51872f21043ca7d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-e12b3c2cea78fd8aa58d9512da34af14.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-65ff03e722befa9d64d3d124a27572f9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 29));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 109.19616129999997, 12.2379852 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Sun City Hotel Nha Trang", "About Sun City Hotel Nha Trang")),
                paragraph(ContentSection.STORY,
                        of("Sun City Hotel Nha Trang là khách sạn 3 sao tọa lạc tại số 18 Tôn Đản, phường Lộc Thọ, ngay trung tâm thành phố biển Nha Trang. Với 16 tầng và 89 phòng nghỉ tiện nghi, khách sạn là lựa chọn lý tưởng cho du khách muốn lưu trú gần biển, gần chợ và các điểm tham quan nổi tiếng với mức giá hợp lý.", "Sun City Hotel Nha Trang is a 3-star hotel located at 18 Ton Dan, Loc Tho Ward, right in the center of the coastal city of Nha Trang. With 16 floors and 89 comfortable rooms, the hotel is an ideal choice for travelers who want to stay near the beach, the market and famous attractions at a reasonable price.")),

                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),
                paragraph(ContentSection.STORY,
                        of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến Deluxe Sea View và phòng Gia đình. Mỗi phòng đều được trang bị máy lạnh, TV truyền hình cáp, minibar, két an toàn, bàn làm việc, phòng tắm vòi sen với máy sấy tóc và Wi-Fi miễn phí, mang lại sự thoải mái cho cả khách du lịch lẫn công tác.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to Deluxe Sea View and Family rooms. Each room is equipped with air conditioning, cable TV, a minibar, a safe, a work desk, a shower bathroom with a hair dryer and free Wi-Fi, bringing comfort to both leisure and business travelers.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                paragraph(ContentSection.STORY,
                        of("Nhà hàng của khách sạn phục vụ bữa sáng buffet đa dạng cùng các món Á - Âu cho bữa trưa và bữa tối. Quầy cà phê tại sảnh là nơi lý tưởng để thư giãn, nhâm nhi đồ uống và ngắm nhịp sống nhộn nhịp của phố biển Nha Trang.", "The hotel's restaurant serves a varied buffet breakfast along with Asian and European dishes for lunch and dinner. The lobby coffee corner is the perfect place to relax, enjoy a drink and watch the bustling life of coastal Nha Trang.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                paragraph(ContentSection.STORY,
                        of("Sun City Hotel Nha Trang sở hữu hồ bơi, lễ tân 24 giờ, thang máy, dịch vụ dọn phòng và dịch vụ giặt ủi. Khách lưu trú còn được hỗ trợ đưa đón sân bay, cho thuê xe hơi và xe đạp, thu đổi ngoại tệ, giữ hành lý và đội ngũ nhân viên đa ngôn ngữ tận tình.", "Sun City Hotel Nha Trang features a swimming pool, a 24-hour front desk, an elevator, housekeeping and laundry service. Guests also benefit from airport transfer, car and bicycle rental, currency exchange, luggage storage and an attentive multilingual staff.")),

                heading(ContentSection.STORY, of("Vị trí & biển", "Location & the beach")),
                paragraph(ContentSection.STORY,
                        of("Khách sạn chỉ cách Bãi biển Nha Trang vài phút đi bộ, thuận tiện cho du khách tắm biển và dạo bộ dọc bờ cát. Nằm ngay trung tâm phường Lộc Thọ, khách sạn gần Quảng trường 2 Tháng 4, Tháp Trầm Hương và nhiều nhà hàng, quán cà phê sầm uất.", "The hotel is just a few minutes' walk from Nha Trang Beach, convenient for swimming and strolling along the sandy shore. Located in the heart of Loc Tho Ward, the hotel is close to April 2 Square, Tram Huong Tower and many lively restaurants and cafes.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
                paragraph(ContentSection.STORY,
                        of("Từ Sân bay quốc tế Cam Ranh, du khách mất khoảng 40 phút lái xe để đến khách sạn. Khách sạn hỗ trợ dịch vụ đưa đón sân bay (thu phí), cho thuê xe hơi và xe đạp, giúp việc khám phá Nha Trang và vùng phụ cận trở nên dễ dàng.", "From Cam Ranh International Airport, it takes about a 40-minute drive to reach the hotel. The hotel offers airport transfer (surcharge), car and bicycle rental, making it easy to explore Nha Trang and its surroundings.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),
                paragraph(ContentSection.STORY,
                        of("Xung quanh khách sạn là khu vực trung tâm sôi động với Chợ Đầm, Nhà thờ Núi, Chùa Long Sơn và nhiều điểm mua sắm, ẩm thực địa phương. Các điểm tham quan nổi tiếng như Tháp Bà Ponagar, Hòn Chồng và VinWonders Nha Trang đều nằm trong tầm di chuyển thuận tiện.", "Around the hotel is a vibrant downtown area with Dam Market, Nha Trang Cathedral, Long Son Pagoda and many shopping and local dining spots. Famous attractions such as Po Nagar Towers, Hon Chong Promontory and VinWonders Nha Trang are all within convenient reach.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Sun City Hotel Nha Trang", "Why choose Sun City Hotel Nha Trang")),
                paragraph(ContentSection.STORY,
                        of("Chọn Sun City Hotel Nha Trang, du khách có được vị trí trung tâm gần biển, phòng nghỉ tiện nghi, hồ bơi thư giãn và dịch vụ chu đáo với mức giá phải chăng. Đây là điểm dừng chân lý tưởng cho cả gia đình, cặp đôi lẫn khách công tác khi đến với thành phố biển Nha Trang.", "Choosing Sun City Hotel Nha Trang gives travelers a central location near the beach, comfortable rooms, a relaxing pool and thoughtful service at an affordable price. It is an ideal stop for families, couples and business travelers visiting the coastal city of Nha Trang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi", "Swimming pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("coffee_shop", of("Cà phê/trà tại sảnh", "Coffee/tea in lobby"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ dọn phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng sớm", "Early check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng muộn", "Late check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-ntsuncity-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 22 m², 1 giường đôi.", "Standard room 22 sqm, 1 double bed."))
                        .description(of("Phòng Standard 22 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Standard room 22 sqm, 1 double bed. Fully equipped for a comfortable stay."))
                        .areaSqm(22.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(24)
                        .images(List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-a1c4f052584c615bb8aaad3b863bf209.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-740f1704158f9bd59dc50c325af11ca4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntsuncity-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
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
                                        .id("rt-ntsuncity-standard-double-pay-flex")
                                        .name(of("Standard Double - Thanh toán tại khách sạn", "Standard Double - Pay at hotel"))
                                        .boardType(BoardType.ROOM_ONLY)
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
                                        .promoLabel(of("Thanh toán tại khách sạn", "Pay at hotel"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntsuncity-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 26 m², 2 giường đơn.", "Superior room 26 sqm, 2 single beds."))
                        .description(of("Phòng Superior 26 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior room 26 sqm, 2 single beds. Fully equipped for a comfortable stay."))
                        .areaSqm(26.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(22)
                        .images(List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2969x2017-FIT_AND_TRIM-bcfaed1d25707a9d94516269125491ce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-e67f48c48c16688338feecd254639dc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntsuncity-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("780000"))
                                        .referencePricePerNight(new BigDecimal("1114286"))
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
                                        .id("rt-ntsuncity-superior-twin-pay-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("897000"))
                                        .referencePricePerNight(new BigDecimal("1281429"))
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
                        .id("rt-ntsuncity-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe 30 m², 1 giường Queen.", "Deluxe room 30 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe 30 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 30 sqm, 1 queen bed. Fully equipped for a comfortable stay."))
                        .areaSqm(30.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(20)
                        .images(List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-bc4ba6bd20f4f8d818669bbe2c31f251.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-6ef480f9fdde75a5271db04a72c71c4f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntsuncity-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
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
                                        .id("rt-ntsuncity-deluxe-queen-pay-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
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
                        .id("rt-ntsuncity-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 32 m² hướng biển, 1 giường King, có ban công.", "Deluxe room 32 sqm with sea view, 1 king bed, balcony."))
                        .description(of("Phòng Deluxe 32 m² hướng biển, 1 giường King, có ban công. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 32 sqm with sea view, 1 king bed, balcony. Fully equipped for a comfortable stay."))
                        .areaSqm(32.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(15)
                        .images(List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-69fc547e2484cb03519f0a1a23ba3209.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2593x1962-FIT_AND_TRIM-30bef579a6edf52c9ddc4a7563facf6b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE",
                                "BLACKOUT_CURTAINS",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntsuncity-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
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
                                        .id("rt-ntsuncity-deluxe-sea-view-pay-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
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
                        .id("rt-ntsuncity-family")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 38 m², 1 giường King + 1 giường đơn, cho 3 khách.", "Family room 38 sqm, 1 king + 1 single bed, for 3 guests."))
                        .description(of("Phòng Gia đình 38 m², 1 giường King + 1 giường đơn, cho 3 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family room 38 sqm, 1 king + 1 single bed, for 3 guests. Fully equipped for a comfortable stay."))
                        .areaSqm(38.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(8)
                        .images(List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-2969x2017-FIT_AND_TRIM-7995af2cb788ce1a86a949cabbb18dcc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025418-cc5f68748f69df8325b699757c9d707c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE",
                                "BLACKOUT_CURTAINS",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-ntsuncity-family-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1350000"))
                                        .referencePricePerNight(new BigDecimal("1928571"))
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
                                        .id("rt-ntsuncity-family-pay-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1552500"))
                                        .referencePricePerNight(new BigDecimal("2217857"))
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
                nearby("bai-bien-nha-trang", of("Bãi biển Nha Trang", "Nha Trang Beach"), of("Bãi biển", "Beach"), 350.0),
                nearby("quang-truong-2-thang-4", of("Quảng trường 2 Tháng 4", "April 2 Square"), of("Địa điểm lân cận", "Nearby places"), 900.0),
                nearby("thap-tram-huong", of("Tháp Trầm Hương", "Tram Huong Tower"), of("Địa điểm lân cận", "Nearby places"), 800.0),
                nearby("cho-dam", of("Chợ Đầm", "Dam Market"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("nha-tho-nui", of("Nhà thờ Núi (Nhà thờ Chánh tòa)", "Nha Trang Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1700.0),
                nearby("cong-vien-phu-dong", of("Công viên Phù Đổng", "Phu Dong Park"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("chua-long-son", of("Chùa Long Sơn", "Long Son Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("vien-hai-duong-hoc", of("Viện Hải dương học", "National Oceanographic Museum"), of("Địa điểm lân cận", "Nearby places"), 5500.0),
                nearby("thap-ba-ponagar", of("Tháp Bà Ponagar", "Po Nagar Towers"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("hon-chong", of("Hòn Chồng", "Hon Chong Promontory"), of("Địa điểm lân cận", "Nearby places"), 4200.0),
                nearby("vinwonders-nha-trang", of("VinWonders Nha Trang", "VinWonders Nha Trang"), of("Trung tâm giải trí", "Entertainment hub"), 9000.0),
                nearby("hon-tam", of("Hòn Tằm", "Hon Tam Island"), of("Bãi biển", "Beach"), 8000.0),
                nearby("i-resort-suoi-khoang-nong", of("I-resort suối khoáng nóng", "I-Resort Hot Spring"), of("Trung tâm giải trí", "Entertainment hub"), 6500.0),
                nearby("bai-dai", of("Bãi Dài", "Bai Dai Beach"), of("Bãi biển", "Beach"), 25000.0),
                nearby("san-bay-cam-ranh", of("Sân bay quốc tế Cam Ranh", "Cam Ranh International Airport"), of("Trung tâm giao thông", "Transport hub"), 32000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Hồ bơi, nhà hàng, lễ tân 24h, máy lạnh, WiFi miễn phí", "Swimming pool, restaurant, 24-hour front desk, air conditioning, free WiFi"))
                                .distanceToCityCenterKm(0.5)
                                .popularAreaSummary(of("Bãi biển Nha Trang, Chợ Đầm, Tháp Trầm Hương", "Nha Trang Beach, Dam Market, Tram Huong Tower"))
                                .totalRooms(89)
                                .totalFloors(16)
                                .otherHighlightFacilities(of("Hồ bơi, đưa đón sân bay, cho thuê xe đạp, dịch vụ giặt ủi, giữ hành lý.", "Swimming pool, airport transfer, bicycle hire, laundry service, luggage storage."))
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
                                                                .title(of("Nhận phòng sớm & trả phòng muộn", "Early check-in & late check-out"))
                                                                .content(of("Hỗ trợ nhận phòng sớm và trả phòng muộn tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in and late check-out are subject to availability; please contact in advance."))
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
                                                                .content(of("Khách sạn thân thiện với gia đình, có phòng Gia đình rộng rãi cho 3 khách.", "The hotel is family-friendly with spacious Family rooms for 3 guests."))
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
                faq(of("Những tiện ích tại Sun City Hotel Nha Trang?", "What amenities does Sun City Hotel Nha Trang offer?"),
                        of("Các tiện ích bao gồm máy lạnh, hồ bơi, nhà hàng, lễ tân 24h, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a swimming pool, a restaurant, a 24-hour front desk, an elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Sun City Hotel Nha Trang?", "What are the check-in and check-out times at Sun City Hotel Nha Trang?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Sun City Hotel Nha Trang có phục vụ ăn sáng không?", "Does Sun City Hotel Nha Trang serve breakfast?"),
                        of("Có, khách sạn có phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Sun City Hotel Nha Trang đạt tiêu chuẩn mấy sao?", "What star rating does Sun City Hotel Nha Trang have?"),
                        of("Sun City Hotel Nha Trang là khách sạn đạt tiêu chuẩn 3 sao.", "Sun City Hotel Nha Trang is a 3-star hotel.")),
                faq(of("Sun City Hotel Nha Trang có bao nhiêu tầng?", "How many floors does Sun City Hotel Nha Trang have?"),
                        of("Khách sạn có tổng cộng 16 tầng.", "The hotel has a total of 16 floors.")),
                faq(of("Tổng số phòng tại Sun City Hotel Nha Trang là bao nhiêu?", "How many rooms does Sun City Hotel Nha Trang have?"),
                        of("Khách sạn có tổng cộng 89 phòng.", "The hotel has a total of 89 rooms.")),
                faq(of("Sun City Hotel Nha Trang có hồ bơi không?", "Does Sun City Hotel Nha Trang have a pool?"),
                        of("Có, khách sạn có hồ bơi phục vụ khách lưu trú.", "Yes, the hotel has a swimming pool for guests.")),
                faq(of("Sun City Hotel Nha Trang có hỗ trợ nhận phòng sớm không?", "Does Sun City Hotel Nha Trang support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact directly to arrange.")),
                faq(of("Sun City Hotel Nha Trang có dịch vụ trả phòng trễ không?", "Does Sun City Hotel Nha Trang offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel offers late check-out subject to availability. Please contact directly to arrange.")),
                faq(of("Sun City Hotel Nha Trang có cách bãi biển bao xa?", "How far is Sun City Hotel Nha Trang from the beach?"),
                        of("Khách sạn chỉ cách Bãi biển Nha Trang vài phút đi bộ, rất thuận tiện cho việc tắm biển và dạo bộ.", "The hotel is just a few minutes' walk from Nha Trang Beach, very convenient for swimming and strolling.")),
                faq(of("Sun City Hotel Nha Trang có dịch vụ đưa đón sân bay không?", "Does Sun City Hotel Nha Trang offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí). Sân bay quốc tế Cam Ranh cách khoảng 40 phút lái xe.", "Yes, the hotel offers airport transfer (surcharge). Cam Ranh International Airport is about a 40-minute drive away.")),
                faq(of("Sun City Hotel Nha Trang có mức giá là bao nhiêu?", "How much does Sun City Hotel Nha Trang cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 650.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 650,000 VND per night. Prices may vary depending on the date and promotions.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("nha-trang")
                                .cityName(of("Nha Trang", "Nha Trang"))
                                .districtName(of("Nha Trang", "Nha Trang"))
                                .wardName(of("Lộc Thọ", "Loc Tho"))
                                .addressLine(of("18 Tôn Đản, Lộc Thọ, Nha Trang, Khánh Hòa, Việt Nam, 652154", "18 Ton Dan, Loc Tho Ward, Nha Trang, Khanh Hoa Province, Vietnam, 652154"))
                                .location(new double[] { 109.19616129999997, 12.2379852 })
                                .name(of("Sun City Hotel Nha Trang", "Sun City Hotel Nha Trang"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao ngay trung tâm Nha Trang, gần biển và Chợ Đầm.", "3-star hotel in central Nha Trang, near the beach and Dam Market."))
                                .description(of("Sun City Hotel Nha Trang là khách sạn 3 sao tại 18 Tôn Đản, phường Lộc Thọ, với 89 phòng, hồ bơi, nhà hàng và dịch vụ chu đáo, chỉ vài phút đi bộ tới biển Nha Trang.", "Sun City Hotel Nha Trang is a 3-star hotel at 18 Ton Dan, Loc Tho Ward, with 89 rooms, a swimming pool, a restaurant and attentive service, just a few minutes' walk from Nha Trang Beach."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
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
