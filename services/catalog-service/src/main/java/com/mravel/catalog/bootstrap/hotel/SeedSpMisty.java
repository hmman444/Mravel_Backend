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
public class SeedSpMisty {

        @Bean
        CommandLineRunner seedSpMistyRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedSpMisty] start");
                        HotelDoc doc = hotelMisty();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedSpMisty] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedSpMisty] done");
                };
        }

        // =====================================================================
        // MISTY HOSTEL SAPA (source: Traveloka hotelId 9000000609450; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelMisty() {
                String slug = "misty-hostel-sapa";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-4f85a5b03dd4bb9b682aa8cdfd5ee904.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-1a9b13d36d227d22a7a437b039b30729.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-4bc06b479f27c8643b535640dbb1702a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-447a3600cba836cf5f5e74b2093604f3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-cdcc1df7c7ba6d325878ca20b6f54013.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-22e8c005679ad8396524412339d12cf2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-e9377909aa0bf268238b298957609559.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-a6eb0d03b1792798ef9f3498dbf0a9d6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-5b096ad4248f6396a87e9dbb4cec8e5e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-cf215775d4b8f5d89be29ce8f0bebf55.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-37b910ad3d69ec534e78496976cb2e7c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-a77b624e94da8c43a9a85ce5a88d0337.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-e64235df89b8f68660ead7c4acb78bfa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-857ef3d3a2d094e8431472f31a8115c3.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-a2a64be3efbc7ec83cf39679231f60c5.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-24602bbf40c0ce7fefb543ceb087a394.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-7eff993eec79eb06060bea48c32e6c16.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-241084f775af8ee8d3e8485afd92391d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-f66d959b5aa3f45e50a800aa5465d284.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-ecf6b35ae6b1e44384fd11f62fe16a0a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-60f36dc9b371bf3727b9ad35e593e22c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-20ff2bcf19ed78713bb5227426375f19.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-fd136d835dd1f36a25a755d5a366db18.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-0113b4b3847973e1c2cdd886734ee534.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-9f76ca8994f5e28b545ad61bd05894b7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-195fbf73e6b3f8dcb891347d544995b8.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-a6990864ce0736bfac6dd033c6246dcb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 103.8574018, 22.3453849 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Misty Hostel Sapa", "About Misty Hostel Sapa")),

                                paragraph(ContentSection.STORY,
                                                of("Misty Hostel Sapa là homestay 2 sao ấm cúng nằm ngay trung tâm thị xã Sa Pa, trên đường Điện Biên Phủ, tỉnh Lào Cai. Với 8 phòng nghỉ trải trên 3 tầng, không khí mát lành quanh năm của vùng núi Tây Bắc cùng phong cách thân thiện, đây là điểm dừng chân lý tưởng cho khách du lịch bụi, các cặp đôi và nhóm bạn muốn khám phá Sa Pa với chi phí hợp lý.", "Misty Hostel Sapa is a cozy 2-star homestay right in the center of Sa Pa town, on Dien Bien Phu Street, Lao Cai Province. With 8 rooms across 3 floors, the year-round cool mountain air of the Northwest highlands and a friendly atmosphere, it is an ideal base for backpackers, couples and groups of friends who want to explore Sa Pa on a reasonable budget.")),

                                heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room categories")),

                                paragraph(ContentSection.STORY,
                                                of("Misty Hostel Sapa cung cấp đa dạng lựa chọn lưu trú: từ giường tầng trong phòng dorm tiết kiệm dành cho khách du lịch bụi, đến phòng Standard, Superior, phòng Gia đình và phòng hướng núi ấm áp. Mỗi phòng đều có máy sưởi, nước nóng, Wi-Fi miễn phí và chăn dày dặn để bạn ngủ ngon trong tiết trời se lạnh của Sa Pa.", "Misty Hostel Sapa offers a variety of stays: from budget dorm bunk beds for backpackers to warm Standard, Superior, Family and mountain-view rooms. Every room has a heater, hot water, free Wi-Fi and thick blankets so you sleep soundly in Sa Pa's chilly climate.")),

                                heading(ContentSection.STORY, of("Ẩm thực & đồ uống", "Food & drink")),

                                paragraph(ContentSection.STORY,
                                                of("Khu vực quầy bar và cà phê ấm cúng phục vụ đồ uống nóng, trà gừng và cà phê suốt cả ngày — nơi lý tưởng để sưởi ấm và trò chuyện cùng những vị khách phương xa. Quanh homestay là vô số quán ăn địa phương với các món đặc sản núi rừng như lẩu cá hồi, thắng cố, đồ nướng và rau cải mèo.", "The cozy bar and cafe area serves hot drinks, ginger tea and coffee throughout the day - a perfect spot to warm up and chat with fellow travelers. Around the homestay are countless local eateries serving mountain specialties such as salmon hotpot, thang co, grilled dishes and highland greens.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                                paragraph(ContentSection.STORY,
                                                of("Homestay có lễ tân 24 giờ, dịch vụ hỗ trợ đặt tour trekking và xe máy, giữ hành lý, đưa đón theo yêu cầu và cửa hàng quà lưu niệm. Sân thượng/sân hiên là nơi tuyệt vời để ngắm mây mù bao phủ thị trấn vào sáng sớm. Đội ngũ nhân viên nhiệt tình luôn sẵn sàng gợi ý lịch trình khám phá Sa Pa.", "The homestay offers a 24-hour front desk, trekking-tour and motorbike booking assistance, luggage storage, transfer on request and a gift shop. The terrace is a wonderful place to watch the morning mist roll over the town. The enthusiastic staff are always ready to suggest itineraries to explore Sa Pa.")),

                                heading(ContentSection.STORY, of("Vị trí, cảnh núi & ruộng bậc thang", "Location, mountains & terraced fields")),

                                paragraph(ContentSection.STORY,
                                                of("Nằm ngay trung tâm Sa Pa, homestay chỉ cách Nhà thờ đá, Quảng trường và Chợ Sa Pa vài phút đi bộ. Từ đây bạn dễ dàng chiêm ngưỡng dãy Hoàng Liên Sơn hùng vĩ, những thửa ruộng bậc thang Mường Hoa và đỉnh Fansipan — nóc nhà Đông Dương — ẩn hiện trong sương mù đặc trưng của xứ sở mù sương.", "Located right in the heart of Sa Pa, the homestay is just a few minutes' walk from the Stone Church, the Square and Sa Pa Market. From here you can easily admire the majestic Hoang Lien Son range, the terraced fields of Muong Hoa and Fansipan Peak - the roof of Indochina - appearing through the characteristic mist of this misty land.")),

                                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                                paragraph(ContentSection.STORY,
                                                of("Sa Pa cách Hà Nội khoảng 320 km, thuận tiện đi bằng xe khách giường nằm hoặc tàu hỏa tới Lào Cai rồi nối chuyến lên thị xã. Từ trung tâm, bạn có thể thuê xe máy ngay tại homestay để khám phá các bản làng, hoặc đi cáp treo lên đỉnh Fansipan chỉ trong khoảng 15 phút.", "Sa Pa is about 320 km from Hanoi, conveniently reached by sleeper bus or by train to Lao Cai and then onward to the town. From the center you can rent a motorbike right at the homestay to explore the villages, or take the cable car to Fansipan Peak in about 15 minutes.")),

                                heading(ContentSection.STORY, of("Khu vực xung quanh & bản làng", "Surroundings & ethnic villages")),

                                paragraph(ContentSection.STORY,
                                                of("Vùng quanh Sa Pa là nơi sinh sống của đồng bào H'Mông, Dao đỏ, Tày với những bản làng giàu bản sắc như Cát Cát, Tả Van, Tả Phìn. Du khách có thể trekking qua thung lũng Mường Hoa, ghé chợ phiên vùng cao, tắm lá thuốc của người Dao và tìm hiểu nghề thổ cẩm truyền thống.", "The area around Sa Pa is home to the H'Mong, Red Dao and Tay people, with culturally rich villages such as Cat Cat, Ta Van and Ta Phin. Visitors can trek through the Muong Hoa Valley, visit highland markets, enjoy a Dao herbal bath and learn about traditional brocade weaving.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Misty Hostel Sapa", "Why choose Misty Hostel Sapa")),

                                paragraph(ContentSection.STORY,
                                                of("Chọn Misty Hostel Sapa, bạn có vị trí trung tâm thuận tiện, không gian ấm cúng giữa tiết trời mát lạnh, mức giá phải chăng và sự hỗ trợ tận tình để khám phá Sa Pa. Đây là lựa chọn lý tưởng cho những ai muốn trải nghiệm vùng núi Tây Bắc một cách gần gũi và tiết kiệm.", "Choosing Misty Hostel Sapa gives you a convenient central location, a cozy space amid the cool weather, affordable rates and attentive support to explore Sa Pa. It is an ideal choice for those who want to experience the Northwest mountains in a close and budget-friendly way.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("air_conditioning", of("Điều hòa", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, true),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe", "Car/motorbike rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("free_motorbike_parking", of("Đậu xe máy miễn phí", "Free motorbike parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("garden", of("Vườn", "Garden"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-misty-dorm-bed")
                        .name(of("Giường tầng phòng tập thể", "Dorm Bunk Bed"))
                        .shortDescription(of("Giường tầng trong phòng dorm chung, tiết kiệm cho khách du lịch bụi.", "Bunk bed in a shared dorm, budget-friendly for backpackers."))
                        .description(of("Giường tầng trong phòng dorm chung, tiết kiệm cho khách du lịch bụi. Trang bị tiện nghi cơ bản, ấm áp giữa tiết trời Sa Pa.", "Bunk bed in a shared dorm, budget-friendly for backpackers. Equipped with basic amenities, warm in Sa Pa's climate."))
                        .areaSqm(16.0)
                        .bedType(BedType.BUNK)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường tầng", "1 bunk bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.BUNK).count(1).build()))
                        .maxAdults(1)
                        .maxChildren(0)
                        .maxGuests(1)
                        .totalRooms(3)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-4f85a5b03dd4bb9b682aa8cdfd5ee904.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0)))
                        .amenityCodes(List.of(
                                "WIFI_FREE",
                                "AIR_CONDITIONING",
                                "HOT_WATER",
                                "SHOWER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "FAN",
                                "BLACKOUT_CURTAINS",
                                "HAIR_DRYER",
                                "DESK"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-misty-dorm-bed-prepaid-nonref")
                                        .name(of("Giường tầng phòng tập thể - Không gồm bữa sáng", "Dorm Bunk Bed - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("250000"))
                                        .referencePricePerNight(new BigDecimal("357143"))
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
                                        .id("rt-misty-dorm-bed-breakfast-flex")
                                        .name(of("Giường tầng phòng tập thể - Bao gồm bữa sáng", "Dorm Bunk Bed - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("287500"))
                                        .referencePricePerNight(new BigDecimal("410714"))
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
                        .id("rt-misty-standard-double")
                        .name(of("Phòng Standard Giường Đôi", "Standard Double Room"))
                        .shortDescription(of("Phòng Standard ấm cúng 18 m², 1 giường đôi, có máy sưởi.", "Cozy 18 sqm Standard room, 1 double bed, with heater."))
                        .description(of("Phòng Standard ấm cúng 18 m², 1 giường đôi, có máy sưởi. Trang bị tiện nghi cơ bản, ấm áp giữa tiết trời Sa Pa.", "Cozy 18 sqm Standard room, 1 double bed, with heater. Equipped with basic amenities, warm in Sa Pa's climate."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(2)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-1a9b13d36d227d22a7a437b039b30729.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-447a3600cba836cf5f5e74b2093604f3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BLACKOUT_CURTAINS",
                                "MINIBAR"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-misty-standard-double-prepaid-nonref")
                                        .name(of("Phòng Standard Giường Đôi - Không gồm bữa sáng", "Standard Double Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("420000"))
                                        .referencePricePerNight(new BigDecimal("600000"))
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
                                        .id("rt-misty-standard-double-breakfast-flex")
                                        .name(of("Phòng Standard Giường Đôi - Bao gồm bữa sáng", "Standard Double Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("483000"))
                                        .referencePricePerNight(new BigDecimal("690000"))
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
                        .id("rt-misty-superior-twin")
                        .name(of("Phòng Superior 2 Giường Đơn", "Superior Twin Room"))
                        .shortDescription(of("Phòng Superior 20 m², 2 giường đơn, phù hợp nhóm bạn.", "Superior 20 sqm room, 2 single beds, ideal for friends."))
                        .description(of("Phòng Superior 20 m², 2 giường đơn, phù hợp nhóm bạn. Trang bị tiện nghi cơ bản, ấm áp giữa tiết trời Sa Pa.", "Superior 20 sqm room, 2 single beds, ideal for friends. Equipped with basic amenities, warm in Sa Pa's climate."))
                        .areaSqm(20.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(2)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-22e8c005679ad8396524412339d12cf2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-e9377909aa0bf268238b298957609559.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BLACKOUT_CURTAINS",
                                "MINIBAR"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-misty-superior-twin-prepaid-nonref")
                                        .name(of("Phòng Superior 2 Giường Đơn - Không gồm bữa sáng", "Superior Twin Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("520000"))
                                        .referencePricePerNight(new BigDecimal("742857"))
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
                                        .id("rt-misty-superior-twin-breakfast-flex")
                                        .name(of("Phòng Superior 2 Giường Đơn - Bao gồm bữa sáng", "Superior Twin Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("598000"))
                                        .referencePricePerNight(new BigDecimal("854286"))
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
                        .id("rt-misty-deluxe-mountain-view")
                        .name(of("Phòng Deluxe Hướng Núi", "Deluxe Mountain View Room"))
                        .shortDescription(of("Phòng Deluxe 24 m² hướng núi, 1 giường Queen, có ban công.", "24 sqm Deluxe mountain-view room, 1 queen bed, with balcony."))
                        .description(of("Phòng Deluxe 24 m² hướng núi, 1 giường Queen, có ban công. Trang bị tiện nghi cơ bản, ấm áp giữa tiết trời Sa Pa.", "24 sqm Deluxe mountain-view room, 1 queen bed, with balcony. Equipped with basic amenities, warm in Sa Pa's climate."))
                        .areaSqm(24.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(1)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-a6eb0d03b1792798ef9f3498dbf0a9d6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-857ef3d3a2d094e8431472f31a8115c3.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-24602bbf40c0ce7fefb543ceb087a394.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
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
                                        .id("rt-misty-deluxe-mountain-view-prepaid-nonref")
                                        .name(of("Phòng Deluxe Hướng Núi - Không gồm bữa sáng", "Deluxe Mountain View Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("680000"))
                                        .referencePricePerNight(new BigDecimal("971429"))
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
                                        .id("rt-misty-deluxe-mountain-view-breakfast-flex")
                                        .name(of("Phòng Deluxe Hướng Núi - Bao gồm bữa sáng", "Deluxe Mountain View Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("782000"))
                                        .referencePricePerNight(new BigDecimal("1117143"))
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
                        .id("rt-misty-family")
                        .name(of("Phòng Gia Đình", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 30 m², 1 giường đôi + 1 giường đơn, cho 3 khách.", "30 sqm Family room, 1 double + 1 single bed, for 3 guests."))
                        .description(of("Phòng Gia đình 30 m², 1 giường đôi + 1 giường đơn, cho 3 khách. Trang bị tiện nghi cơ bản, ấm áp giữa tiết trời Sa Pa.", "30 sqm Family room, 1 double + 1 single bed, for 3 guests. Equipped with basic amenities, warm in Sa Pa's climate."))
                        .areaSqm(30.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường đôi và 1 giường đơn", "1 double bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(1)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-24602bbf40c0ce7fefb543ceb087a394.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-7eff993eec79eb06060bea48c32e6c16.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20033799-241084f775af8ee8d3e8485afd92391d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "HOT_WATER",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BLACKOUT_CURTAINS",
                                "MINIBAR",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-misty-family-prepaid-nonref")
                                        .name(of("Phòng Gia Đình - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("820000"))
                                        .referencePricePerNight(new BigDecimal("1171429"))
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
                                        .id("rt-misty-family-breakfast-flex")
                                        .name(of("Phòng Gia Đình - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("943000"))
                                        .referencePricePerNight(new BigDecimal("1347143"))
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
                nearby("nha-tho-da-sa-pa", of("Nhà thờ đá Sa Pa", "Sapa Stone Church"), of("Địa điểm lân cận", "Nearby places"), 300.0),
                nearby("quang-truong-sa-pa", of("Quảng trường Sa Pa", "Sapa Square"), of("Địa điểm lân cận", "Nearby places"), 350.0),
                nearby("ho-sa-pa", of("Hồ Sa Pa", "Sapa Lake"), of("Địa điểm lân cận", "Nearby places"), 500.0),
                nearby("cho-sa-pa", of("Chợ Sa Pa", "Sapa Market"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("nui-ham-rong", of("Núi Hàm Rồng", "Ham Rong Mountain"), of("Địa điểm lân cận", "Nearby places"), 900.0),
                nearby("ban-cat-cat", of("Bản Cát Cát", "Cat Cat Village"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thung-lung-muong-hoa", of("Thung lũng Mường Hoa", "Muong Hoa Valley"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("ban-ta-van", of("Bản Tả Van", "Ta Van Village"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("ban-ta-phin", of("Bản Tả Phìn", "Ta Phin Village"), of("Địa điểm lân cận", "Nearby places"), 11000.0),
                nearby("thac-bac-sa-pa", of("Thác Bạc", "Silver Waterfall"), of("Địa điểm lân cận", "Nearby places"), 12000.0),
                nearby("deo-o-quy-ho", of("Đèo Ô Quy Hồ", "O Quy Ho Pass"), of("Khác", "Other"), 13000.0),
                nearby("cong-troi-o-quy-ho", of("Cổng trời Ô Quy Hồ", "Heaven Gate"), of("Khác", "Other"), 12500.0),
                nearby("cap-treo-fansipan", of("Cáp treo Fansipan", "Fansipan Cable Car"), of("Trung tâm giải trí", "Entertainment hub"), 2000.0),
                nearby("sun-world-fansipan-legend", of("Sun World Fansipan Legend", "Sun World Fansipan Legend"), of("Trung tâm giải trí", "Entertainment hub"), 2200.0),
                nearby("dinh-fansipan", of("Đỉnh Fansipan", "Fansipan Peak"), of("Khác", "Other"), 7000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Lễ tân 24h, WiFi miễn phí, máy sưởi, quầy cà phê, đưa đón, hỗ trợ đặt tour", "24-hour front desk, free WiFi, heater, coffee bar, transfer, tour assistance"))
                                .distanceToCityCenterKm(0.3)
                                .popularAreaSummary(of("Nhà thờ đá Sa Pa, Chợ Sa Pa, Quảng trường Sa Pa", "Sapa Stone Church, Sapa Market, Sapa Square"))
                                .totalRooms(8)
                                .totalFloors(3)
                                .otherHighlightFacilities(of("Sân thượng ngắm núi, quầy cà phê, cho thuê xe máy, hỗ trợ trekking, giữ hành lý.", "Mountain-view terrace, coffee bar, motorbike rental, trekking support, luggage storage."))
                                .interestingPlacesSummary(of("Bản Cát Cát, Thung lũng Mường Hoa, Cáp treo Fansipan", "Cat Cat Village, Muong Hoa Valley, Fansipan Cable Car"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
                                                                .title(of("Giấy tờ yêu cầu", "Required documents"))
                                                                .content(of("Vui lòng xuất trình CCCD/hộ chiếu hợp lệ khi nhận phòng.", "Please present a valid ID card/passport at check-in."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhận phòng sớm", "Early check-in"))
                                                                .content(of("Hỗ trợ nhận phòng sớm tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in is subject to availability; please contact in advance."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.SMOKING)
                                                                .title(of("Chính sách hút thuốc", "Smoking policy"))
                                                                .content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.AIRPORT_TRANSFER)
                                                                .title(of("Đưa đón", "Transfer"))
                                                                .content(of("Homestay cung cấp dịch vụ đưa đón theo yêu cầu (thu phí); vui lòng liên hệ trước.", "The homestay offers transfer on request (surcharge); please contact in advance."))
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
                faq(of("Những tiện ích tại Misty Hostel Sapa?", "What amenities does Misty Hostel Sapa offer?"),
                        of("Các tiện ích bao gồm lễ tân 24 giờ, WiFi miễn phí, máy sưởi, điều hòa, dịch vụ hỗ trợ đặt tour, đưa đón sân bay, giữ hành lý và cửa hàng quà tặng (một số dịch vụ có thể thu thêm phí).", "Amenities include a 24-hour front desk, free WiFi, heater, air conditioning, tour booking assistance, airport transfer, luggage storage and a gift shop (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Misty Hostel Sapa?", "What are the check-in and check-out times at Misty Hostel Sapa?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Misty Hostel Sapa đạt tiêu chuẩn mấy sao?", "What star rating does Misty Hostel Sapa have?"),
                        of("Misty Hostel Sapa là homestay đạt tiêu chuẩn 2.0 sao.", "Misty Hostel Sapa is a 2.0-star homestay.")),
                faq(of("Misty Hostel Sapa có bao nhiêu tầng?", "How many floors does Misty Hostel Sapa have?"),
                        of("Misty Hostel Sapa có tổng cộng 3 tầng.", "Misty Hostel Sapa has a total of 3 floors.")),
                faq(of("Tổng số phòng tại Misty Hostel Sapa là bao nhiêu?", "How many rooms does Misty Hostel Sapa have?"),
                        of("Tổng số phòng tại Misty Hostel Sapa là 8 phòng.", "Misty Hostel Sapa has a total of 8 rooms.")),
                faq(of("Misty Hostel Sapa có chỗ đậu xe không?", "Does Misty Hostel Sapa have parking?"),
                        of("Có, Misty Hostel Sapa có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với homestay để xác nhận thông tin chi tiết.", "Yes, Misty Hostel Sapa provides parking. Please contact the homestay directly to confirm details.")),
                faq(of("Misty Hostel Sapa có dịch vụ đưa đón sân bay không?", "Does Misty Hostel Sapa offer airport transfer?"),
                        of("Có, Misty Hostel Sapa cung cấp dịch vụ đưa đón theo yêu cầu (thu phí). Vui lòng liên hệ trước để được sắp xếp.", "Yes, Misty Hostel Sapa offers transfer on request (surcharge). Please contact in advance to arrange.")),
                faq(of("Misty Hostel Sapa có phục vụ ăn sáng không?", "Does Misty Hostel Sapa serve breakfast?"),
                        of("Có, bạn có thể chọn loại giá phòng bao gồm bữa sáng; nếu không có thể trả thêm phí tại quầy.", "Yes, you can choose a rate that includes breakfast; otherwise an additional fee may apply at the counter.")),
                faq(of("Misty Hostel Sapa có hỗ trợ đặt tour trekking không?", "Does Misty Hostel Sapa help book trekking tours?"),
                        of("Có, homestay hỗ trợ đặt tour trekking tới các bản làng, thuê xe máy và tư vấn lịch trình khám phá Sa Pa.", "Yes, the homestay assists with booking trekking tours to the villages, motorbike rental and itinerary advice for exploring Sa Pa.")),
                faq(of("Mức giá phòng tại Misty Hostel Sapa là bao nhiêu?", "How much does a room at Misty Hostel Sapa cost?"),
                        of("Giường dorm có giá từ khoảng 250.000 VND mỗi đêm, phòng riêng từ khoảng 420.000 VND. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Dorm beds start from around 250,000 VND per night, private rooms from around 420,000 VND. Prices may vary depending on the date and promotions.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("sa-pa")
                                .cityName(of("Sa Pa", "Sa Pa"))
                                .districtName(of("Sa Pa", "Sa Pa"))
                                .wardName(of("Trung tâm Sa Pa", "Sa Pa Town"))
                                .addressLine(of("Ngõ 233, đường Điện Biên Phủ, Trung tâm Sa Pa, Sa Pa, Tỉnh Lào Cai, Việt Nam, 330000", "233 Dien Bien Phu Street, Sa Pa Town, Sa Pa District, Lao Cai Province, Vietnam, 33000"))
                                .location(new double[] { 103.8574018, 22.3453849 })
                                .name(of("Misty Hostel Sapa", "Misty Hostel Sapa"))
                                .slug(slug)
                                .hotelType(HotelType.HOSTEL)
                                .starRating(2)
                                .shortDescription(of("Homestay 2 sao ấm cúng ngay trung tâm Sa Pa, gần Nhà thờ đá và chợ.", "Cozy 2-star homestay in central Sa Pa, near the Stone Church and market."))
                                .description(of("Misty Hostel Sapa là homestay 2 sao nằm ngay trung tâm thị xã Sa Pa với phòng dorm và phòng riêng ấm cúng, máy sưởi, quầy cà phê, hỗ trợ đặt tour trekking và đưa đón — lựa chọn tiết kiệm cho hành trình khám phá vùng núi Tây Bắc.", "Misty Hostel Sapa is a 2-star homestay in the heart of Sa Pa town with cozy dorm and private rooms, heaters, a coffee bar, trekking-tour and transfer support - a budget choice for exploring the Northwest mountains."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("250000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("357143"))
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
