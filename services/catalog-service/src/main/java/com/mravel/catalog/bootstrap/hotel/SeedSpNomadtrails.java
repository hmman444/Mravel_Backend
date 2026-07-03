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
public class SeedSpNomadtrails {

        @Bean
        CommandLineRunner seedSpNomadtrailsRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedSpNomadtrails] start");
                        HotelDoc doc = hotelNomadtrails();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedSpNomadtrails] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedSpNomadtrails] done");
                };
        }

        // =====================================================================
        // NOMADTRAILS BOUTIQUE HOTEL (Sa Pa, Lao Cai)
        // (source: Traveloka hotelId 3000020007994; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelNomadtrails() {
                String slug = "nomadtrails-boutique-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-80d4f6f760fa59796cf90260c3b76d59.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-2d2894cb9e12397a62e0d2dc6f0adc7c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-a7de1e5f71ddb8b2eddb10634eab98c8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-882452293e7e138ef0d713bc881f024d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-bc2f7fb80eb672b0a30ae3cdff505469.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-89718c044060654cd297de0b5cc43b22.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-eaf5a0f208804ba792028604c3ed8430.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-4fe35e8bfa33d9c012f968af02cfce06.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-ae087ad2c3cfffae4c8b552843068627.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-a03f9a1beece254929d8f7f445ed9446.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-8c72a5460984abf45c8c085d060a0d0d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-0c98e29a1f4c53c82785736f3963747d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-cfba46525a6cf42977c229abf2249271.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-c0ee3b31f2feab48bfd7d911398dc443.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-f02ef9ce943983424bc107bfaa6de77c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-5f06193ba4cbe43d8b5def044efe18b7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-295ce15a2e47a532369df112c6d65c20.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-358b59d596e466be23f5c0129f223aec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-394d757f10ceaeeeb2964bbff1d5adea.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-06f31104b1ab0080b95f7f87d64c0cab.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-eea6f703a1cd80ed68d7ce278e016e55.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-3484fa01aa7b41cd06b62e523a4ca159.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-1cb8fcc49d444b650fc7ddb528016bda.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-3bce12112d38a2e546b317f2a3ddfee6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-fed4a3ae82c0f2f08c4a92c7cd45d705.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-8a2105bfec5568d9e8377c8fb36cb610.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-639275e8124d49e98dee8993a0f24bcc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-0cef6ff50e15594f74cd622829a43e5c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-c757e7be5ee9d36c9c0d915b0ad9022f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-6911c14beeedf4d5c43a7b5787c08f0f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 29));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 103.84148060000007, 22.333392 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Nomadtrails Boutique Hotel", "About Nomadtrails Boutique Hotel")),

                                paragraph(ContentSection.STORY,
                                                of("Nomadtrails Boutique Hotel là khách sạn 3 sao tọa lạc tại số 15 Tuệ Tĩnh, ngay trung tâm thị xã Sa Pa, tỉnh Lào Cai. Nằm giữa vùng núi Tây Bắc với khí hậu mát mẻ quanh năm, khách sạn là điểm dừng chân lý tưởng để khám phá Fansipan, ruộng bậc thang Mường Hoa và các bản làng dân tộc đặc sắc. Với 30 phòng nghỉ ấm cúng trải trên 6 tầng, Nomadtrails mang đến trải nghiệm lưu trú gần gũi, tiện nghi cho cả du khách trong nước và quốc tế.",
                                                                "Nomadtrails Boutique Hotel is a 3-star hotel located at 15 Tue Tinh, right in the center of Sa Pa town, Lao Cai Province. Set amid the Northwestern mountains with a cool climate year-round, the hotel is an ideal base to explore Fansipan, the Muong Hoa terraced fields and the distinctive ethnic villages. With 30 cozy rooms across 6 floors, Nomadtrails offers a warm, comfortable stay for both domestic and international travelers.")),

                                heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),

                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình và phòng hướng núi. Mỗi phòng được trang bị máy lạnh, nước nóng, minibar, bàn làm việc, máy sấy tóc và Wi-Fi miễn phí. Nội thất ấm áp cùng chăn đệm dày giúp du khách thoải mái nghỉ ngơi trong tiết trời se lạnh đặc trưng của Sa Pa.",
                                                                "The hotel offers a range of room categories from Standard, Superior and Deluxe to Family and Mountain View rooms. Each room is equipped with air conditioning, hot water, a minibar, a work desk, a hair dryer and free Wi-Fi. Warm interiors with thick bedding help guests rest comfortably in Sa Pa's characteristic chilly weather.")),

                                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng của khách sạn phục vụ bữa sáng cùng thực đơn các món Việt và đặc sản vùng cao như cá hồi, lợn cắp nách, rau rừng và rượu táo mèo. Quầy cà phê ấm cúng là nơi lý tưởng để nhâm nhi đồ uống nóng và ngắm nhìn không gian phố núi.",
                                                                "The hotel restaurant serves breakfast along with Vietnamese dishes and highland specialties such as salmon, free-range pork, wild vegetables and apple wine. The cozy coffee corner is a perfect spot to enjoy a hot drink while admiring the mountain-town scenery.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                                paragraph(ContentSection.STORY,
                                                of("Nomadtrails Boutique Hotel có lễ tân 24 giờ, thang máy, dịch vụ phòng 24 giờ, dịch vụ văn phòng, phòng giữ đồ và Wi-Fi miễn phí tại khu vực chung. Khách sạn còn hỗ trợ đặt tour, cho thuê xe đạp và dịch vụ đưa đón sân bay theo yêu cầu, giúp chuyến đi khám phá Sa Pa thêm thuận tiện.",
                                                                "Nomadtrails Boutique Hotel features a 24-hour front desk, an elevator, 24-hour room service, a business center, luggage storage and free Wi-Fi in public areas. The hotel also assists with tour booking, bicycle rental and airport transfer on request, making your Sa Pa exploration more convenient.")),

                                heading(ContentSection.STORY, of("Vị trí & cảnh núi", "Location & mountain views")),

                                paragraph(ContentSection.STORY,
                                                of("Từ khách sạn, du khách chỉ mất vài phút đi bộ đến Nhà thờ đá Sa Pa, Quảng trường Sa Pa và Chợ Sa Pa. Tầm nhìn hướng ra dãy Hoàng Liên Sơn cùng đỉnh Fansipan hùng vĩ, ruộng bậc thang Mường Hoa uốn lượn tạo nên khung cảnh thơ mộng đặc trưng của vùng cao Tây Bắc.",
                                                                "From the hotel, it takes just a few minutes on foot to reach the Sa Pa Stone Church, Sa Pa Square and Sa Pa Market. Views stretch toward the Hoang Lien Son range and the majestic Fansipan peak, while the winding Muong Hoa terraced fields create the poetic scenery typical of the Northwest highlands.")),

                                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                                paragraph(ContentSection.STORY,
                                                of("Khách sạn nằm tại trung tâm thị xã nên rất thuận tiện cho việc di chuyển. Du khách có thể đi bộ quanh khu trung tâm, thuê xe đạp hoặc xe máy để khám phá vùng phụ cận, hoặc sử dụng cáp treo Fansipan để chinh phục nóc nhà Đông Dương. Bến xe và các điểm bắt xe đưa đón đều ở gần.",
                                                                "Located in the town center, the hotel is very convenient for getting around. Guests can stroll the central area, rent a bicycle or motorbike to explore the surroundings, or take the Fansipan cable car to conquer the roof of Indochina. The bus station and shuttle pick-up points are all nearby.")),

                                heading(ContentSection.STORY, of("Khu vực xung quanh & bản làng", "Surroundings & ethnic villages")),

                                paragraph(ContentSection.STORY,
                                                of("Sa Pa nổi tiếng với các bản làng dân tộc như Cát Cát, Tả Van, Tả Phìn, nơi du khách có thể tìm hiểu văn hóa người H'Mông, Dao đỏ và trải nghiệm dệt thổ cẩm, tắm lá thuốc. Thác Bạc, Đèo Ô Quy Hồ và Cổng trời cũng là những điểm dừng chân ấn tượng trên hành trình khám phá.",
                                                                "Sa Pa is famous for its ethnic villages such as Cat Cat, Ta Van and Ta Phin, where travelers can learn about the H'Mong and Red Dao cultures and experience brocade weaving and herbal baths. Silver Waterfall, O Quy Ho Pass and Heaven Gate are also striking stops along the way.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Nomadtrails Boutique Hotel", "Why choose Nomadtrails Boutique Hotel")),

                                paragraph(ContentSection.STORY,
                                                of("Chọn Nomadtrails Boutique Hotel, du khách có một điểm lưu trú ấm cúng ngay trung tâm Sa Pa, giá hợp lý, dịch vụ thân thiện và vị trí thuận tiện để khám phá vẻ đẹp núi rừng Tây Bắc. Đây là lựa chọn lý tưởng cho cặp đôi, gia đình và nhóm bạn yêu thích thiên nhiên và văn hóa vùng cao.",
                                                                "Choosing Nomadtrails Boutique Hotel gives travelers a cozy stay right in the center of Sa Pa, with reasonable prices, friendly service and a convenient location to explore the beauty of the Northwest mountains. It is an ideal choice for couples, families and groups of friends who love highland nature and culture.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ phòng 24 giờ", "24-hour room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Phòng giữ đồ", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("free_motorbike_parking", of("Bãi đậu xe máy miễn phí", "Free motorbike parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
                amenity("free_car_parking", of("Bãi đậu ô tô miễn phí", "Free car parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe", "Car rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("terrace", of("Sân hiên", "Terrace"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng nhanh", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-spnomadtrails-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi.", "Standard room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Standard room 18 sqm, 1 double bed. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-80d4f6f760fa59796cf90260c3b76d59.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-a7de1e5f71ddb8b2eddb10634eab98c8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-spnomadtrails-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("700000"))
                                        .referencePricePerNight(new BigDecimal("1000000"))
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
                                        .id("rt-spnomadtrails-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("805000"))
                                        .referencePricePerNight(new BigDecimal("1150000"))
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
                        .id("rt-spnomadtrails-superior-double")
                        .name(of("Superior Double", "Superior Double"))
                        .shortDescription(of("Phòng Superior 22 m², 1 giường Queen.", "Superior room 22 sqm, 1 queen bed."))
                        .description(of("Phòng Superior 22 m², 1 giường Queen. Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Superior room 22 sqm, 1 queen bed. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(22.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-a7de1e5f71ddb8b2eddb10634eab98c8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-bc2f7fb80eb672b0a30ae3cdff505469.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-spnomadtrails-superior-double-prepaid-nonref")
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
                                        .id("rt-spnomadtrails-superior-double-breakfast-flex")
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
                        .id("rt-spnomadtrails-deluxe-mountain-view")
                        .name(of("Deluxe Mountain View", "Deluxe Mountain View"))
                        .shortDescription(of("Phòng Deluxe 26 m² hướng núi, 1 giường King.", "Deluxe room 26 sqm with mountain view, 1 king bed."))
                        .description(of("Phòng Deluxe 26 m² hướng núi, 1 giường King. Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Deluxe room 26 sqm with mountain view, 1 king bed. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(26.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-bc2f7fb80eb672b0a30ae3cdff505469.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-4fe35e8bfa33d9c012f968af02cfce06.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-spnomadtrails-deluxe-mountain-view-prepaid-nonref")
                                        .name(of("Deluxe Mountain View - Không gồm bữa sáng", "Deluxe Mountain View - Room only"))
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
                                        .id("rt-spnomadtrails-deluxe-mountain-view-breakfast-flex")
                                        .name(of("Deluxe Mountain View - Bao gồm bữa sáng", "Deluxe Mountain View - Breakfast included"))
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
                        .id("rt-spnomadtrails-deluxe-twin")
                        .name(of("Deluxe Twin", "Deluxe Twin"))
                        .shortDescription(of("Phòng Deluxe 24 m², 2 giường đơn.", "Deluxe room 24 sqm, 2 single beds."))
                        .description(of("Phòng Deluxe 24 m², 2 giường đơn. Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Deluxe room 24 sqm, 2 single beds. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(24.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-4fe35e8bfa33d9c012f968af02cfce06.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-ae087ad2c3cfffae4c8b552843068627.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-spnomadtrails-deluxe-twin-prepaid-nonref")
                                        .name(of("Deluxe Twin - Không gồm bữa sáng", "Deluxe Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1100000"))
                                        .referencePricePerNight(new BigDecimal("1571429"))
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
                                        .id("rt-spnomadtrails-deluxe-twin-breakfast-flex")
                                        .name(of("Deluxe Twin - Bao gồm bữa sáng", "Deluxe Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1265000"))
                                        .referencePricePerNight(new BigDecimal("1807143"))
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
                        .id("rt-spnomadtrails-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 34 m², 1 giường King + 1 giường đơn, cho 3 khách.", "Family room 34 sqm, 1 king + 1 single bed, for 3 guests."))
                        .description(of("Phòng Gia đình 34 m², 1 giường King + 1 giường đơn, cho 3 khách. Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Family room 34 sqm, 1 king + 1 single bed, for 3 guests. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(34.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-ae087ad2c3cfffae4c8b552843068627.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20007994-a03f9a1beece254929d8f7f445ed9446.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-spnomadtrails-family-room-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1500000"))
                                        .referencePricePerNight(new BigDecimal("2142857"))
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
                                        .id("rt-spnomadtrails-family-room-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1725000"))
                                        .referencePricePerNight(new BigDecimal("2464286"))
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
                nearby("nha-tho-da-sa-pa", of("Nhà thờ đá Sa Pa", "Sa Pa Stone Church"), of("Địa điểm lân cận", "Nearby places"), 300.0),
                nearby("quang-truong-sa-pa", of("Quảng trường Sa Pa", "Sa Pa Square"), of("Địa điểm lân cận", "Nearby places"), 350.0),
                nearby("cho-sa-pa", of("Chợ Sa Pa", "Sa Pa Market"), of("Địa điểm lân cận", "Nearby places"), 450.0),
                nearby("ho-sa-pa", of("Hồ Sa Pa", "Sa Pa Lake"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("nui-ham-rong", of("Núi Hàm Rồng", "Ham Rong Mountain"), of("Địa điểm lân cận", "Nearby places"), 800.0),
                nearby("ban-cat-cat", of("Bản Cát Cát", "Cat Cat Village"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thung-lung-muong-hoa", of("Thung lũng Mường Hoa", "Muong Hoa Valley"), of("Địa điểm lân cận", "Nearby places"), 5000.0),
                nearby("ban-ta-van", of("Bản Tả Van", "Ta Van Village"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("ban-ta-phin", of("Bản Tả Phìn", "Ta Phin Village"), of("Địa điểm lân cận", "Nearby places"), 11000.0),
                nearby("thac-bac", of("Thác Bạc", "Silver Waterfall"), of("Địa điểm lân cận", "Nearby places"), 12000.0),
                nearby("deo-o-quy-ho", of("Đèo Ô Quy Hồ", "O Quy Ho Pass"), of("Khác", "Other"), 11500.0),
                nearby("cap-treo-fansipan", of("Cáp treo Fansipan", "Fansipan Cable Car"), of("Trung tâm giải trí", "Entertainment hub"), 2800.0),
                nearby("dinh-fansipan", of("Đỉnh Fansipan", "Fansipan Peak"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("sun-world-fansipan-legend", of("Sun World Fansipan Legend", "Sun World Fansipan Legend"), of("Trung tâm giải trí", "Entertainment hub"), 3000.0),
                nearby("cong-troi-o-quy-ho", of("Cổng trời Ô Quy Hồ", "Heaven Gate O Quy Ho"), of("Khác", "Other"), 11800.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, lễ tân 24h, thang máy, máy lạnh, WiFi miễn phí, đưa đón sân bay", "Restaurant, 24-hour front desk, elevator, air conditioning, free WiFi, airport transfer"))
                                .distanceToCityCenterKm(0.3)
                                .popularAreaSummary(of("Trung tâm Sa Pa, Nhà thờ đá, Chợ Sa Pa", "Sa Pa center, Stone Church, Sa Pa Market"))
                                .totalRooms(30)
                                .totalFloors(6)
                                .otherHighlightFacilities(of("Cho thuê xe đạp, dịch vụ hỗ trợ đặt tour, đưa đón sân bay, dịch vụ phòng 24 giờ.", "Bicycle rental, tour booking assistance, airport transfer, 24-hour room service."))
                                .interestingPlacesSummary(of("Cáp treo Fansipan, Bản Cát Cát, Thung lũng Mường Hoa", "Fansipan Cable Car, Cat Cat Village, Muong Hoa Valley"))
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
                                                PolicyItem.builder().section(PolicySection.AIRPORT_TRANSFER)
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí).", "The hotel offers airport transfer on request (surcharge)."))
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
                faq(of("Những tiện ích tại Nomadtrails Boutique Hotel?", "What amenities does Nomadtrails Boutique Hotel offer?"),
                        of("Các tiện ích bao gồm nhà hàng, lễ tân 24h, thang máy, dịch vụ phòng, WiFi, cho thuê xe đạp và đưa đón sân bay (một số dịch vụ có thể thu thêm phí).", "Amenities include a restaurant, 24-hour front desk, elevator, room service, WiFi, bicycle rental and airport transfer (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Nomadtrails Boutique Hotel có phục vụ ăn sáng không?", "Does Nomadtrails Boutique Hotel serve breakfast?"),
                        of("Có, khách sạn có phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn đạt tiêu chuẩn mấy sao?", "What star rating does the hotel have?"),
                        of("Nomadtrails Boutique Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Nomadtrails Boutique Hotel is a 3-star hotel.")),
                faq(of("Khách sạn có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does the hotel have?"),
                        of("Khách sạn có tổng cộng 6 tầng với 30 phòng.", "The hotel has a total of 6 floors with 30 rooms.")),
                faq(of("Nomadtrails Boutique Hotel có chỗ đậu xe không?", "Does Nomadtrails Boutique Hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
                faq(of("Khách sạn có dịch vụ đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel offers airport transfer on request (surcharge). Please contact in advance to arrange.")),
                faq(of("Khách sạn có cho thuê xe đạp không?", "Does the hotel offer bicycle rental?"),
                        of("Có, khách sạn có dịch vụ cho thuê xe đạp để du khách thuận tiện khám phá khu vực Sa Pa.", "Yes, the hotel offers bicycle rental for guests to conveniently explore the Sa Pa area.")),
                faq(of("Khách sạn có gần trung tâm Sa Pa không?", "Is the hotel close to the center of Sa Pa?"),
                        of("Có, khách sạn nằm ngay trung tâm thị xã Sa Pa, chỉ vài phút đi bộ đến Nhà thờ đá, Quảng trường và Chợ Sa Pa.", "Yes, the hotel is right in the center of Sa Pa town, just a few minutes' walk from the Stone Church, the Square and Sa Pa Market.")),
                faq(of("Khách sạn có hỗ trợ đặt tour không?", "Does the hotel assist with tour bookings?"),
                        of("Có, khách sạn hỗ trợ đặt tour tham quan các điểm như Fansipan, bản Cát Cát, Tả Van và thung lũng Mường Hoa.", "Yes, the hotel assists with booking tours to sites such as Fansipan, Cat Cat Village, Ta Van and the Muong Hoa Valley.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("sa-pa")
                                .cityName(of("Sa Pa", "Sa Pa"))
                                .districtName(of("Trung tâm Sa Pa", "Sa Pa Township"))
                                .wardName(of("Trung tâm Sa Pa", "Sa Pa Township"))
                                .addressLine(of("15 Tuệ Tĩnh, Trung tâm Sa Pa, Sa Pa, Tỉnh Lào Cai, Việt Nam, 330000", "15 Tue Tinh, Sa Pa Township, Sa Pa District, Lao Cai Province, Vietnam, 330000"))
                                .location(new double[] { 103.84148060000007, 22.333392 })
                                .name(of("Nomadtrails Boutique Hotel", "Nomadtrails Boutique Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao ngay trung tâm thị xã Sa Pa, gần Fansipan và ruộng bậc thang Mường Hoa.", "3-star hotel in the center of Sa Pa town, near Fansipan and the Muong Hoa terraced fields."))
                                .description(of("Nomadtrails Boutique Hotel là khách sạn 3 sao ấm cúng tại trung tâm Sa Pa, mang đến phòng nghỉ tiện nghi, nhà hàng, dịch vụ đặt tour, cho thuê xe đạp và đưa đón sân bay – điểm dừng chân lý tưởng để khám phá vùng núi Tây Bắc.", "Nomadtrails Boutique Hotel is a cozy 3-star hotel in central Sa Pa, offering comfortable rooms, a restaurant, tour booking, bicycle rental and airport transfer - an ideal base to explore the Northwest mountains."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("700000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1000000"))
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
