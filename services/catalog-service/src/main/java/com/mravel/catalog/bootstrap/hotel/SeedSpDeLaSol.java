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
public class SeedSpDeLaSol {

        @Bean
        CommandLineRunner seedSpDeLaSolRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedSpDeLaSol] start");
                        HotelDoc doc = hotelDeLaSol();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedSpDeLaSol] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedSpDeLaSol] done");
                };
        }

        // =====================================================================
        // DELASOL SAPA HOTEL (source: Traveloka hotelId 1000000536612; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelDeLaSol() {
                String slug = "delasol-sapa-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-c03f865a32258b10ed436afb73cbfe1c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-47b9a0a2ea04a3bbea619e32762e987a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-87f7fbf6bbbc6c784dca7f8a73528729.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng chức năng", "Functional hall", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-dfcb74772df3a706e7c2341d0cbc8ba9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-076510a74105e860a8eb4c0f06378f72.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-08344dd0750c0c529fa19fab1e51f482.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-9962dbd19ab2be1c3b0813d6a694103a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-78d72f1f6ad16cb185bb9ad04310044a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-5d41bf56216e54d975f43a08de75b6b9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-16f3a71d03405c2d2861e2ec7e047eaa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan núi", "Mountain view", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-0cd3f91c6daad439b71ddfbcf7b3ef15.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-c67a6ea867a062a1078e984dbff27ad1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-f718b220dcfb27ba08c72856daaa68a1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-87406bf5e7aea394afee55421c9175f8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-f0c15d58bde365dd01f68ea242393338.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-427755d1baf59e41cc8425431da3e45b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-c0708fe23759f929f96c64b9370e74a8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-ea6665c325413e32a82151e3ea7fbd0a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-f2daa4789ed0bd0cda82baed9373263c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-a039db1ff8f22036c1cfec85c5e592d9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-3594dd53676dc5d545f8b070e33388be.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-55cd1e3dda2afdabcf219f6c0d0e4ba8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-6d9273d88248ab16de759e2e126e80d8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-0fad5cf192f304880b0a63200d503806.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-dce7e0878b62cb6d66197e0b61be7bd2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-3a63df71fc8ec67434a4d7484206b60b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-5c794ebeb20fc52105ca556ceddaa931.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-43eec3eca266de6b3ed21e722deb7662.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-3e7df64321a148e9a570185d6ee28efc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10025991-af8091684c5030a6813050b8ff7db3ec.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 29));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 103.84352259999999, 22.332362 }),
                                heading(ContentSection.STORY, of("Giới thiệu chung về DeLaSol Sapa Hotel", "About DeLaSol Sapa Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("DeLaSol Sapa Hotel là khách sạn 4 sao tọa lạc tại số 16 Mường Hoa, ngay trung tâm thị xã Sa Pa, tỉnh Lào Cai. Với 65 phòng nghỉ trải trên 8 tầng giữa vùng núi Tây Bắc mát mẻ quanh năm, khách sạn là điểm dừng chân lý tưởng để khám phá Fansipan, ruộng bậc thang Mường Hoa và các bản làng dân tộc đặc sắc.", "DeLaSol Sapa Hotel is a 4-star hotel located at 16 Muong Hoa, right in the center of Sa Pa Township, Lao Cai Province. With 65 rooms across 8 floors amid the cool northwestern mountains, the hotel is an ideal base for exploring Fansipan, the Muong Hoa terraced fields and distinctive ethnic villages.")),
                                heading(ContentSection.STORY, of("Phòng nghỉ tiện nghi", "Comfortable rooms")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến Family và Deluxe hướng núi. Mỗi phòng được trang bị máy lạnh, máy sưởi cho mùa đông Sa Pa, minibar, bàn làm việc, máy sấy tóc, phòng tắm riêng với vòi sen nước nóng và Wi-Fi miễn phí, mang lại sự ấm cúng giữa tiết trời se lạnh.", "The hotel offers a variety of room categories from Standard, Superior, Deluxe to Family and Mountain View Deluxe. Each room is equipped with air conditioning, a heater for Sa Pa winters, a minibar, a work desk, a hair dryer, a private bathroom with a hot-water shower and free Wi-Fi, bringing warmth amid the chilly climate.")),
                                heading(ContentSection.STORY, of("Ẩm thực vùng cao", "Highland dining")),
                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng của khách sạn phục vụ bữa sáng buffet cùng thực đơn Á - Âu và các món đặc sản Tây Bắc như lẩu cá hồi, thịt lợn cắp nách và rau rừng. Du khách có thể thưởng thức cà phê, đồ uống nóng tại quầy bar ấm cúng nhìn ra phố núi.", "The hotel restaurant serves a breakfast buffet along with Asian-European menus and northwestern specialties such as salmon hotpot, free-range pork and wild vegetables. Guests can enjoy coffee and hot drinks at the cozy bar overlooking the mountain town.")),
                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn có lễ tân 24 giờ, dịch vụ phòng, giữ hành lý, giặt ủi, thu đổi ngoại tệ, nhận và trả phòng cấp tốc, cùng đội ngũ nhân viên đa ngôn ngữ. Quầy hỗ trợ tour giúp du khách dễ dàng đặt các chuyến trekking, tham quan bản làng và chinh phục Fansipan.", "The hotel provides a 24-hour front desk, room service, luggage storage, laundry, currency exchange, express check-in and check-out, and a multilingual staff. The tour desk helps guests easily book trekking, village tours and Fansipan ascents.")),
                                heading(ContentSection.STORY, of("Vị trí & cảnh quan núi rừng", "Location & mountain scenery")),
                                paragraph(ContentSection.STORY,
                                                of("Nằm trên đường Mường Hoa ngay trung tâm Sa Pa, khách sạn chỉ cách Nhà thờ đá, quảng trường và chợ Sa Pa vài phút đi bộ. Từ đây du khách dễ dàng ngắm nhìn dãy Hoàng Liên Sơn, thung lũng Mường Hoa với ruộng bậc thang trải dài và đỉnh Fansipan hùng vĩ.", "Set on Muong Hoa street right in central Sa Pa, the hotel is only a few minutes' walk from the Stone Church, the square and Sa Pa market. From here guests can easily admire the Hoang Lien Son range, the Muong Hoa valley with sprawling terraced fields and the majestic Fansipan peak.")),
                                heading(ContentSection.STORY, of("Di chuyển thuận tiện", "Convenient transport")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí) và hỗ trợ đặt xe. Ga cáp treo Fansipan và bến xe Sa Pa nằm trong khoảng cách di chuyển ngắn, thuận tiện cho hành trình khám phá vùng cao.", "The hotel offers airport transfer (surcharge) and car booking assistance. The Fansipan cable car station and Sa Pa bus station are a short ride away, convenient for highland exploration.")),
                                heading(ContentSection.STORY, of("Khu vực xung quanh & bản làng", "Surroundings & ethnic villages")),
                                paragraph(ContentSection.STORY,
                                                of("Xung quanh khách sạn là những bản làng nổi tiếng như Cát Cát, Tả Van, Tả Phìn, nơi du khách có thể tìm hiểu văn hóa người H'Mông, Dao Đỏ, thưởng thức thổ cẩm và tắm lá thuốc truyền thống. Thác Bạc và đèo Ô Quy Hồ cũng là những điểm đến không thể bỏ lỡ.", "Around the hotel are famous villages such as Cat Cat, Ta Van and Ta Phin, where guests can learn about H'Mong and Red Dao culture, enjoy brocade and traditional herbal baths. Silver Waterfall and O Quy Ho Pass are also must-visit destinations.")),
                                heading(ContentSection.STORY, of("Vì sao nên chọn DeLaSol Sapa Hotel", "Why choose DeLaSol Sapa Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("Với vị trí trung tâm, dịch vụ 4 sao, phòng nghỉ ấm cúng và cảnh quan núi rừng tuyệt đẹp, DeLaSol Sapa Hotel là lựa chọn lý tưởng cho cả gia đình, cặp đôi và nhóm bạn muốn tận hưởng kỳ nghỉ trọn vẹn tại thị xã sương mù Sa Pa.", "With a central location, 4-star service, cozy rooms and stunning mountain scenery, DeLaSol Sapa Hotel is an ideal choice for families, couples and groups of friends seeking a complete holiday in the misty town of Sa Pa.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Quầy hỗ trợ đặt tour", "Tour desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-delasol-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard Double 24 m², 1 giường đôi.", "Standard Double room 24 sqm, 1 double bed."))
                        .description(of("Phòng Standard Double rộng 24 m². Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Standard Double room of 24 sqm. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(24.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(13)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-78d72f1f6ad16cb185bb9ad04310044a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-3e7df64321a148e9a570185d6ee28efc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-delasol-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1000000"))
                                        .referencePricePerNight(new BigDecimal("1428571"))
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
                                        .id("rt-delasol-standard-double-payhotel-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1150000"))
                                        .referencePricePerNight(new BigDecimal("1642857"))
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
                        .id("rt-delasol-superior-double")
                        .name(of("Superior Double", "Superior Double"))
                        .shortDescription(of("Phòng Superior Double 28 m², 1 giường đôi.", "Superior Double room 28 sqm, 1 double bed."))
                        .description(of("Phòng Superior Double rộng 28 m². Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Superior Double room of 28 sqm. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(28.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(13)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-3e7df64321a148e9a570185d6ee28efc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-f2e229785a19ac5dcdb8028537ae2b77.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-delasol-superior-double-prepaid-nonref")
                                        .name(of("Superior Double - Không gồm bữa sáng", "Superior Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1250000"))
                                        .referencePricePerNight(new BigDecimal("1785714"))
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
                                        .id("rt-delasol-superior-double-payhotel-flex")
                                        .name(of("Superior Double - Bao gồm bữa sáng", "Superior Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1437500"))
                                        .referencePricePerNight(new BigDecimal("2053571"))
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
                        .id("rt-delasol-deluxe-mountain-view")
                        .name(of("Deluxe Mountain View", "Deluxe Mountain View"))
                        .shortDescription(of("Phòng Deluxe Mountain View 32 m², 1 giường cỡ queen.", "Deluxe Mountain View room 32 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe Mountain View rộng 32 m². Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Deluxe Mountain View room of 32 sqm. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(32.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(13)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-f2e229785a19ac5dcdb8028537ae2b77.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-11e400f78d0e98aa32535da0da610510.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-delasol-deluxe-mountain-view-prepaid-nonref")
                                        .name(of("Deluxe Mountain View - Không gồm bữa sáng", "Deluxe Mountain View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1600000"))
                                        .referencePricePerNight(new BigDecimal("2285714"))
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
                                        .id("rt-delasol-deluxe-mountain-view-payhotel-flex")
                                        .name(of("Deluxe Mountain View - Bao gồm bữa sáng", "Deluxe Mountain View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1840000"))
                                        .referencePricePerNight(new BigDecimal("2628571"))
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
                        .id("rt-delasol-deluxe-twin")
                        .name(of("Deluxe Twin", "Deluxe Twin"))
                        .shortDescription(of("Phòng Deluxe Twin 32 m², 2 giường đơn.", "Deluxe Twin room 32 sqm, 2 single beds."))
                        .description(of("Phòng Deluxe Twin rộng 32 m². Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Deluxe Twin room of 32 sqm. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(32.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(13)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-11e400f78d0e98aa32535da0da610510.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-e58b0f5a4642416bb12235b398b850f4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-delasol-deluxe-twin-prepaid-nonref")
                                        .name(of("Deluxe Twin - Không gồm bữa sáng", "Deluxe Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1600000"))
                                        .referencePricePerNight(new BigDecimal("2285714"))
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
                                        .id("rt-delasol-deluxe-twin-payhotel-flex")
                                        .name(of("Deluxe Twin - Không gồm bữa sáng (linh hoạt)", "Deluxe Twin - Room only (flexible)"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1840000"))
                                        .referencePricePerNight(new BigDecimal("2628571"))
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
                        .id("rt-delasol-family-suite")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Family Suite 45 m², 1 giường queen và 1 giường đơn.", "Family Suite room 45 sqm, 1 queen bed and 1 single bed."))
                        .description(of("Phòng Family Suite rộng 45 m². Trang bị đầy đủ tiện nghi, ấm cúng cho kỳ nghỉ tại Sa Pa.", "Family Suite room of 45 sqm. Fully equipped and cozy for a stay in Sa Pa."))
                        .areaSqm(45.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(13)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-e58b0f5a4642416bb12235b398b850f4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-d261d6cecefb78566844fe092061310f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10025991-78d72f1f6ad16cb185bb9ad04310044a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-delasol-family-suite-prepaid-nonref")
                                        .name(of("Family Suite - Không gồm bữa sáng", "Family Suite - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("2200000"))
                                        .referencePricePerNight(new BigDecimal("3142857"))
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
                                        .id("rt-delasol-family-suite-payhotel-flex")
                                        .name(of("Family Suite - Bao gồm bữa sáng", "Family Suite - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2530000"))
                                        .referencePricePerNight(new BigDecimal("3614286"))
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
                nearby("nha-tho-da-sa-pa", of("Nhà thờ đá Sa Pa", "Sapa Stone Church"), of("Địa điểm lân cận", "Nearby places"), 350.0),
                nearby("quang-truong-sa-pa", of("Quảng trường Sa Pa", "Sapa Square"), of("Địa điểm lân cận", "Nearby places"), 300.0),
                nearby("ho-sa-pa", of("Hồ Sa Pa", "Sapa Lake"), of("Địa điểm lân cận", "Nearby places"), 500.0),
                nearby("cho-sa-pa", of("Chợ Sa Pa", "Sapa Market"), of("Địa điểm lân cận", "Nearby places"), 450.0),
                nearby("nui-ham-rong", of("Núi Hàm Rồng", "Ham Rong Mountain"), of("Khác", "Other"), 900.0),
                nearby("ban-cat-cat", of("Bản Cát Cát", "Cat Cat Village"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thung-lung-muong-hoa", of("Thung lũng Mường Hoa", "Muong Hoa Valley"), of("Khác", "Other"), 5000.0),
                nearby("ban-ta-van", of("Bản Tả Van", "Ta Van Village"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("ban-ta-phin", of("Bản Tả Phìn", "Ta Phin Village"), of("Địa điểm lân cận", "Nearby places"), 11000.0),
                nearby("thac-bac", of("Thác Bạc", "Silver Waterfall"), of("Khác", "Other"), 12000.0),
                nearby("deo-o-quy-ho", of("Đèo Ô Quy Hồ", "O Quy Ho Pass"), of("Khác", "Other"), 14000.0),
                nearby("cap-treo-fansipan", of("Cáp treo Fansipan", "Fansipan Cable Car"), of("Trung tâm giải trí", "Entertainment hub"), 2000.0),
                nearby("dinh-fansipan", of("Đỉnh Fansipan", "Fansipan Peak"), of("Khác", "Other"), 6000.0),
                nearby("sun-world-fansipan-legend", of("Sun World Fansipan Legend", "Sun World Fansipan Legend"), of("Trung tâm giải trí", "Entertainment hub"), 2200.0),
                nearby("cong-troi-o-quy-ho", of("Cổng trời Ô Quy Hồ", "Heaven Gate"), of("Khác", "Other"), 14500.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, quầy bar, máy sưởi, lễ tân 24h, chỗ đậu xe, WiFi miễn phí", "Restaurant, bar, heater, 24-hour front desk, parking, free WiFi"))
                                .distanceToCityCenterKm(0.3)
                                .popularAreaSummary(of("Trung tâm Sa Pa, Nhà thờ đá, Chợ Sa Pa", "Sa Pa center, Stone Church, Sa Pa Market"))
                                .totalRooms(65)
                                .totalFloors(8)
                                .otherHighlightFacilities(of("Nhà hàng đặc sản Tây Bắc, quầy hỗ trợ tour, đưa đón sân bay, giặt ủi.", "Northwestern specialty restaurant, tour desk, airport transfer, laundry."))
                                .interestingPlacesSummary(of("Nhà thờ đá Sa Pa, Bản Cát Cát, Cáp treo Fansipan", "Sapa Stone Church, Cat Cat Village, Fansipan Cable Car"))
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
                                                PolicyItem.builder().section(PolicySection.AIRPORT_TRANSFER)
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí).", "The hotel offers airport transfer on request (surcharge)."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.SMOKING)
                                                                .title(of("Chính sách hút thuốc", "Smoking policy"))
                                                                .content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.PETS)
                                                                .title(of("Vật nuôi", "Pets"))
                                                                .content(of("Không cho phép mang theo vật nuôi.", "Pets are not allowed."))
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
                faq(of("Những tiện ích tại DeLaSol Sapa Hotel?", "What amenities does DeLaSol Sapa Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, máy sưởi, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, heater, restaurant, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của DeLaSol Sapa Hotel?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("DeLaSol Sapa Hotel có phục vụ ăn sáng không?", "Does DeLaSol Sapa Hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a room rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn DeLaSol Sapa Hotel đạt tiêu chuẩn mấy sao?", "What star rating does DeLaSol Sapa Hotel have?"),
                        of("DeLaSol Sapa Hotel là khách sạn đạt tiêu chuẩn 4 sao.", "DeLaSol Sapa Hotel is a 4-star hotel.")),
                faq(of("DeLaSol Sapa Hotel có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does DeLaSol Sapa Hotel have?"),
                        of("Khách sạn có tổng cộng 8 tầng với 65 phòng.", "The hotel has a total of 8 floors with 65 rooms.")),
                faq(of("DeLaSol Sapa Hotel có chỗ đậu xe không?", "Does DeLaSol Sapa Hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm the details.")),
                faq(of("DeLaSol Sapa Hotel có dịch vụ đưa đón sân bay không?", "Does DeLaSol Sapa Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Vui lòng liên hệ trực tiếp để biết thêm chi tiết.", "Yes, the hotel offers airport transfer on request (surcharge). Please contact directly for more details.")),
                faq(of("Khách sạn có gần trung tâm Sa Pa không?", "Is the hotel near the center of Sa Pa?"),
                        of("Khách sạn nằm ngay trung tâm thị xã Sa Pa trên đường Mường Hoa, chỉ vài phút đi bộ tới Nhà thờ đá, quảng trường và chợ Sa Pa.", "The hotel is right in the center of Sa Pa Township on Muong Hoa street, just a few minutes' walk from the Stone Church, the square and Sa Pa market.")),
                faq(of("Khách sạn có hỗ trợ đặt tour trekking và bản làng không?", "Does the hotel help book trekking and village tours?"),
                        of("Có, quầy hỗ trợ tour của khách sạn giúp du khách đặt các chuyến trekking, tham quan bản Cát Cát, Tả Van và chinh phục Fansipan.", "Yes, the hotel tour desk helps guests book trekking trips, visits to Cat Cat and Ta Van villages, and Fansipan ascents.")),
                faq(of("Phòng nghỉ có máy sưởi cho mùa đông không?", "Do rooms have a heater for winter?"),
                        of("Có, các phòng đều được trang bị máy sưởi và máy lạnh, mang lại sự ấm áp trong tiết trời lạnh của Sa Pa.", "Yes, all rooms are equipped with a heater and air conditioning, providing warmth in the cold Sa Pa climate.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("sa-pa")
                                .cityName(of("Sa Pa", "Sa Pa"))
                                .districtName(of("Sa Pa", "Sa Pa"))
                                .wardName(of("Trung tâm Sa Pa", "Sa Pa Township"))
                                .addressLine(of("16 Mường Hoa, Trung tâm Sa Pa, Sa Pa, Tỉnh Lào Cai, Việt Nam, 330000", "16 Muong Hoa, Sa Pa Township, Sa Pa District, Lao Cai Province, Vietnam, 330000"))
                                .location(new double[] { 103.84352259999999, 22.332362 })
                                .name(of("DeLaSol Sapa Hotel", "DeLaSol Sapa Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao tại trung tâm Sa Pa, gần Fansipan và ruộng bậc thang Mường Hoa.", "4-star hotel in central Sa Pa, near Fansipan and the Muong Hoa terraced fields."))
                                .description(of("DeLaSol Sapa Hotel là khách sạn 4 sao tọa lạc tại trung tâm thị xã Sa Pa với 65 phòng nghỉ ấm cúng, nhà hàng đặc sản Tây Bắc và dịch vụ hỗ trợ khám phá vùng cao.", "DeLaSol Sapa Hotel is a 4-star hotel in central Sa Pa with 65 cozy rooms, a northwestern specialty restaurant and services to support highland exploration."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("1000000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1428571"))
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
