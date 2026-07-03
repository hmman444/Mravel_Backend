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
public class SeedHcmSunshineAirport {

        @Bean
        CommandLineRunner seedHcmSunshineAirportRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHcmSunshineAirport] start");
                        HotelDoc doc = hotelSunshineAirport();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHcmSunshineAirport] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHcmSunshineAirport] done");
                };
        }

        // =====================================================================
        // SUNSHINE AIRPORT HOTEL (Tan Binh, Ho Chi Minh City)
        // (source: Traveloka hotelId 9000007008217; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelSunshineAirport() {
                String slug = "sunshine-airport-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-aaa48851a3823a87b76e55c718b0dbd8.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-641fc0f4098e0e842a702638daa8fe3b.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-466d4354e2955fc6eea9648b9bf9f835.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-4d6c63bc62bd64c7283fbd2bf1dbb84c.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-ce250e3b87a20ed7653608b7d0a98d78.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-c7f04e55dd2481aedcbbe82821dedf0c.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-afe549b1bd3e57a5daa613fc857a1953.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-7b1aac24aef139210103422f0af8564f.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-8779a46666f84f95ef0966da267786a0.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-08c2f7330568f55b80c15f052bd4dc6e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-db5a72dfc3348df213084f6b99cd68dc.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-6cc4be77d5347b226da8284c8dc1a16b.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-3a3c8e80b067b52497e1564f9bc166bc.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-6620ee89833d01aa5e4270a7db3ca5bc.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-08de7128b391b68b70de5643ec301f0f.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-289dc87659dbed4b9cc6e476123b2843.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-df2292a7926c1df27cf4fd9507ae449b.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-8407a174739e569afeceeebcfae0b92f.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-fe346e544e56a168dc4a83ce127dbef1.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-82494f030c19906d4ac01dfb9f26f3a1.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-b200556865ae07492778c0a8a4099a74.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-e45cac37aeca160e25d53c2a9d43cf37.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-b58dbf2b69323083c741053be20b7c87.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-7ae74ec7762297f1c30a3afb1734709e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-792ee5ebbe3f58df7875258d8704af62.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-b594d080770035058e636b3515922d4d.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-dad7801a09b08616f5b6195ae18a7bcb.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-686d902c635711ac79e8a2fddc4b639a.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-641b9444a665b6bfa08129d194256f3f.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-2fea5ec089af2652468d61fdd3a1eadf.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 29),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-a5ef122a5ad00ad1d3e32f85e778a36e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Không gian chung", "Common space", false, 30),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-bc72889cbc9c081d52025f0b7becfd20.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Sảnh", "Lobby", false, 31),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-09a7ab7bbd2353b13de80d895221c31c.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng tắm", "Bathroom", false, 32),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-52d99ad46be2de3a5640fe0b1c253bf0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 33),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-275685b0f4811a6eceb240af875c8bf5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 34),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-b131d2e422d34d59426dfb7112655004.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 35),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-21a806f223948245795b22456285a11f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 36),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-ab16daf90458d800388b98a45f84dd94.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 37));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 106.6684848, 10.8124501 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Sunshine Airport Hotel", "About Sunshine Airport Hotel")),

                                paragraph(ContentSection.STORY,
                                of("Sunshine Airport Hotel là khách sạn 4 sao tọa lạc tại 88A Yên Thế, Phường 2, Quận Tân Bình, chỉ cách Sân bay quốc tế Tân Sơn Nhất vài phút di chuyển. Với 42 phòng nghỉ trải trên 7 tầng, khách sạn là lựa chọn lý tưởng cho khách công tác, khách quá cảnh và du khách cần một nơi lưu trú thuận tiện, hiện đại ngay cửa ngõ thành phố.", "Sunshine Airport Hotel is a 4-star hotel located at 88A Yen The, Ward 2, Tan Binh District, just minutes from Tan Son Nhat International Airport. With 42 rooms across 7 floors, the hotel is an ideal choice for business travelers, transit guests and visitors seeking a convenient, modern stay right at the city gateway.")),

                                heading(ContentSection.STORY, of("Các hạng phòng nghỉ", "Room categories")),

                                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Family, đáp ứng nhu cầu của khách lẻ lẫn gia đình. Mỗi phòng đều được trang bị máy lạnh, tủ lạnh, bàn làm việc, phòng tắm vòi sen, máy sấy tóc, nước uống đóng chai và Wi-Fi miễn phí, mang đến không gian nghỉ ngơi thoải mái và yên tĩnh.", "The hotel offers a range of room categories from Standard, Superior and Deluxe to Family rooms, catering to both individual travelers and families. Every room is equipped with air conditioning, a refrigerator, a work desk, a shower bathroom, a hair dryer, bottled water and free Wi-Fi, providing a comfortable and quiet space to rest.")),

                                heading(ContentSection.STORY, of("Ẩm thực & bữa sáng", "Dining & breakfast")),

                                paragraph(ContentSection.STORY,
                                of("Khách sạn phục vụ bữa sáng đa dạng cùng thực đơn món Á – Âu, sẵn sàng phục vụ cả những chuyến bay sớm. Dịch vụ phòng 24 giờ và thức uống chào mừng miễn phí giúp du khách luôn được tiếp đón chu đáo trong suốt thời gian lưu trú.", "The hotel serves a varied breakfast along with Asian and European dishes, ready to accommodate even early flights. 24-hour room service and a complimentary welcome drink ensure guests are well looked after throughout their stay.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                                paragraph(ContentSection.STORY,
                                of("Sunshine Airport Hotel mang đến hệ thống tiện ích đầy đủ gồm lễ tân 24 giờ, thang máy, dịch vụ giặt ủi, giữ hành lý, đổi ngoại tệ, máy ATM và cửa hàng tiện lợi. Dịch vụ nhận và trả phòng cấp tốc đặc biệt hữu ích cho khách có lịch trình bay gấp.", "Sunshine Airport Hotel provides a full set of facilities including a 24-hour front desk, an elevator, laundry service, luggage storage, currency exchange, an ATM and a convenience shop. Express check-in and check-out are particularly useful for guests with tight flight schedules.")),

                                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                                paragraph(ContentSection.STORY,
                                of("Nằm tại trung tâm Quận Tân Bình, khách sạn thuận tiện kết nối tới các điểm tham quan nổi tiếng của Sài Gòn như Chợ Bến Thành, Nhà thờ Đức Bà, Dinh Độc Lập và phố đi bộ Nguyễn Huệ. Khu vực xung quanh có nhiều nhà hàng, quán cà phê và trung tâm mua sắm sầm uất.", "Set in the heart of Tan Binh District, the hotel offers convenient access to Saigon's famous attractions such as Ben Thanh Market, Notre-Dame Cathedral, Independence Palace and Nguyen Hue Walking Street. The surrounding area is full of restaurants, cafes and bustling shopping centers.")),

                                heading(ContentSection.STORY, of("Di chuyển & sân bay", "Getting around & the airport")),

                                paragraph(ContentSection.STORY,
                                of("Lợi thế lớn nhất của khách sạn là khoảng cách rất gần Sân bay quốc tế Tân Sơn Nhất, chỉ khoảng 2 km, kèm dịch vụ đưa đón sân bay (thu phí). Vị trí gần các trục đường lớn giúp du khách dễ dàng đặt taxi, xe công nghệ hoặc thuê xe để khám phá thành phố.", "The hotel's biggest advantage is its proximity to Tan Son Nhat International Airport, only about 2 km away, with airport transfer service available (surcharge). Its location near major roads makes it easy for guests to book taxis, ride-hailing cars or rent a vehicle to explore the city.")),

                                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                                paragraph(ContentSection.STORY,
                                of("Quận Tân Bình là khu vực năng động với khu phố ẩm thực, chợ địa phương và nhiều quán ăn đặc sản. Du khách có thể dạo bộ thưởng thức món ngon đường phố, mua sắm tại siêu thị gần đó hoặc nhanh chóng di chuyển vào trung tâm Quận 1 chỉ trong khoảng 20–30 phút.", "Tan Binh District is a vibrant area with food streets, local markets and many specialty eateries. Guests can stroll to enjoy street food, shop at nearby supermarkets, or quickly reach the District 1 center in about 20–30 minutes.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Sunshine Airport Hotel", "Why choose Sunshine Airport Hotel")),

                                paragraph(ContentSection.STORY,
                                of("Chọn Sunshine Airport Hotel, du khách có được sự kết hợp giữa vị trí gần sân bay, dịch vụ chu đáo 24 giờ và mức giá hợp lý cho khách sạn 4 sao. Đây là điểm dừng chân lý tưởng cho cả khách công tác lẫn du lịch khi cần sự tiện lợi và linh hoạt tại Sài Gòn.", "Choosing Sunshine Airport Hotel gives travelers a combination of an airport-close location, attentive 24-hour service and reasonable pricing for a 4-star hotel. It is an ideal stopover for both business and leisure guests who need convenience and flexibility in Saigon.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ phòng 24 giờ", "24-hour room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Dịch vụ nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("express_checkout", of("Dịch vụ trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("wifi", of("WiFi tại khu vực chung", "WiFi in public areas"), AmenityGroup.INTERNET, AmenitySection.INTERNET, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-sunshine-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard Double 20 m².", "Standard Double room 20 sqm."))
                        .description(of("Phòng Standard Double rộng 20 m². Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Standard Double room of 20 sqm. Fully equipped with modern amenities for a comfortable stay."))
                        .areaSqm(20.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-aaa48851a3823a87b76e55c718b0dbd8.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-641fc0f4098e0e842a702638daa8fe3b.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-sunshine-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
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
                                        .id("rt-sunshine-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
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
                        .id("rt-sunshine-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior Twin 24 m².", "Superior Twin room 24 sqm."))
                        .description(of("Phòng Superior Twin rộng 24 m². Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Superior Twin room of 24 sqm. Fully equipped with modern amenities for a comfortable stay."))
                        .areaSqm(24.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-466d4354e2955fc6eea9648b9bf9f835.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-4d6c63bc62bd64c7283fbd2bf1dbb84c.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-sunshine-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
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
                                        .id("rt-sunshine-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
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
                        .build(),
                RoomType.builder()
                        .id("rt-sunshine-deluxe-king")
                        .name(of("Deluxe King", "Deluxe King"))
                        .shortDescription(of("Phòng Deluxe King 28 m².", "Deluxe King room 28 sqm."))
                        .description(of("Phòng Deluxe King rộng 28 m². Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Deluxe King room of 28 sqm. Fully equipped with modern amenities for a comfortable stay."))
                        .areaSqm(28.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-ce250e3b87a20ed7653608b7d0a98d78.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-c7f04e55dd2481aedcbbe82821dedf0c.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SEATING_AREA",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-sunshine-deluxe-king-prepaid-nonref")
                                        .name(of("Deluxe King - Không gồm bữa sáng", "Deluxe King - Room only"))
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
                                        .id("rt-sunshine-deluxe-king-breakfast-flex")
                                        .name(of("Deluxe King - Bao gồm bữa sáng", "Deluxe King - Breakfast included"))
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
                        .build(),
                RoomType.builder()
                        .id("rt-sunshine-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Family Room 34 m².", "Family Room room 34 sqm."))
                        .description(of("Phòng Family Room rộng 34 m². Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Family Room room of 34 sqm. Fully equipped with modern amenities for a comfortable stay."))
                        .areaSqm(34.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-afe549b1bd3e57a5daa613fc857a1953.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090340-7b1aac24aef139210103422f0af8564f.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SEATING_AREA",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-sunshine-family-room-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("2050000"))
                                        .referencePricePerNight(new BigDecimal("2928571"))
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
                                        .id("rt-sunshine-family-room-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2357500"))
                                        .referencePricePerNight(new BigDecimal("3367857"))
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
                nearby("san-bay-tan-son-nhat", of("Sân bay Tân Sơn Nhất", "Tan Son Nhat Airport"), of("Trung tâm giao thông", "Transport hub"), 2000.0),
                nearby("cho-ben-thanh", of("Chợ Bến Thành", "Ben Thanh Market"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("nha-tho-duc-ba", of("Nhà thờ Đức Bà", "Notre-Dame Cathedral"), of("Địa điểm lân cận", "Nearby places"), 6800.0),
                nearby("buu-dien-thanh-pho", of("Bưu điện Thành phố", "Central Post Office"), of("Địa điểm lân cận", "Nearby places"), 6900.0),
                nearby("pho-di-bo-nguyen-hue", of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 7200.0),
                nearby("dinh-doc-lap", of("Dinh Độc Lập", "Independence Palace"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("bao-tang-chung-tich-chien-tranh", of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"), of("Khác", "Other"), 5500.0),
                nearby("toa-nha-bitexco", of("Tòa nhà Bitexco", "Bitexco Financial Tower"), of("Khác", "Other"), 7500.0),
                nearby("landmark-81", of("Landmark 81", "Landmark 81"), of("Khác", "Other"), 9000.0),
                nearby("pho-tay-bui-vien", of("Phố Tây Bùi Viện", "Bui Vien Street"), of("Trung tâm giải trí", "Entertainment hub"), 6700.0),
                nearby("thao-cam-vien", of("Thảo Cầm Viên", "Saigon Zoo"), of("Trung tâm giải trí", "Entertainment hub"), 7000.0),
                nearby("chua-ngoc-hoang", of("Chùa Ngọc Hoàng", "Jade Emperor Pagoda"), of("Địa điểm lân cận", "Nearby places"), 6300.0),
                nearby("cho-binh-tay", of("Chợ Bình Tây", "Binh Tay Market"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("cau-mong", of("Cầu Mống", "Mong Bridge"), of("Khác", "Other"), 7300.0),
                nearby("ho-con-rua", of("Hồ Con Rùa", "Turtle Lake"), of("Khác", "Other"), 6100.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Gần sân bay Tân Sơn Nhất, nhà hàng, lễ tân 24h, máy lạnh, WiFi miễn phí", "Near Tan Son Nhat Airport, restaurant, 24-hour front desk, air conditioning, free WiFi"))
                                .distanceToCityCenterKm(6.5)
                                .popularAreaSummary(of("Sân bay Tân Sơn Nhất, Quận Tân Bình, trung tâm Quận 1", "Tan Son Nhat Airport, Tan Binh District, District 1 center"))
                                .totalRooms(42)
                                .totalFloors(7)
                                .otherHighlightFacilities(of("Đưa đón sân bay, dịch vụ phòng 24h, giặt ủi, đổi ngoại tệ, cửa hàng tiện lợi.", "Airport transfer, 24-hour room service, laundry, currency exchange, convenience shop."))
                                .interestingPlacesSummary(of("Chợ Bến Thành, Nhà thờ Đức Bà, Dinh Độc Lập, phố đi bộ Nguyễn Huệ", "Ben Thanh Market, Notre-Dame Cathedral, Independence Palace, Nguyen Hue Walking Street"))
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
                                                                .title(of("Trả phòng trễ", "Late check-out"))
                                                                .content(of("Hỗ trợ trả phòng trễ tùy tình trạng phòng trống; vui lòng liên hệ trước để được sắp xếp.", "Late check-out is subject to availability; please contact in advance to arrange."))
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
                faq(of("Những tiện ích tại Sunshine Airport Hotel?", "What amenities does Sunshine Airport Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, lễ tân 24 giờ, chỗ đậu xe, thang máy, nhà hàng, dịch vụ phòng và WiFi miễn phí (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a 24-hour front desk, parking, an elevator, a restaurant, room service and free WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Sunshine Airport Hotel?", "What are the check-in and check-out times at Sunshine Airport Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Khách sạn Sunshine Airport Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Sunshine Airport Hotel have?"),
                        of("Sunshine Airport Hotel là khách sạn đạt tiêu chuẩn 4 sao.", "Sunshine Airport Hotel is a 4-star hotel.")),
                faq(of("Sunshine Airport Hotel có bao nhiêu tầng?", "How many floors does Sunshine Airport Hotel have?"),
                        of("Sunshine Airport Hotel có tổng cộng 7 tầng.", "Sunshine Airport Hotel has a total of 7 floors.")),
                faq(of("Tổng số phòng tại Sunshine Airport Hotel là bao nhiêu?", "How many rooms does Sunshine Airport Hotel have?"),
                        of("Tổng số phòng tại Sunshine Airport Hotel là 42 phòng.", "Sunshine Airport Hotel has a total of 42 rooms.")),
                faq(of("Sunshine Airport Hotel có chỗ đậu xe không?", "Does Sunshine Airport Hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm the details.")),
                faq(of("Sunshine Airport Hotel có hỗ trợ nhận phòng sớm không?", "Does Sunshine Airport Hotel support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Sunshine Airport Hotel có cung cấp dịch vụ trả phòng trễ không?", "Does Sunshine Airport Hotel offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Khách sạn có gần Sân bay Tân Sơn Nhất không?", "Is the hotel close to Tan Son Nhat Airport?"),
                        of("Có, khách sạn chỉ cách Sân bay quốc tế Tân Sơn Nhất khoảng 2 km và có dịch vụ đưa đón sân bay (thu phí).", "Yes, the hotel is only about 2 km from Tan Son Nhat International Airport and offers airport transfer service (surcharge).")),
                faq(of("Sunshine Airport Hotel có phục vụ bữa sáng không?", "Does Sunshine Airport Hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ho-chi-minh-city")
                                .cityName(of("Hồ Chí Minh", "Ho Chi Minh City"))
                                .districtName(of("Tân Bình", "Tan Binh"))
                                .wardName(of("Phường 2", "Ward 2"))
                                .addressLine(of("88A Yên Thế, Phường 2, Phường 2, Quận Tân Bình, Thành phố Hồ Chí Minh, Việt Nam, 700000", "88A Yên Thế, Phường 2, Ward 2, Tan Binh District, Ho Chi Minh City, Vietnam, 700000"))
                                .location(new double[] { 106.6684848, 10.8124501 })
                                .name(of("Sunshine Airport Hotel", "Sunshine Airport Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao tại Tân Bình, gần Sân bay Tân Sơn Nhất.", "4-star hotel in Tan Binh, near Tan Son Nhat Airport."))
                                .description(of("Sunshine Airport Hotel là khách sạn 4 sao tọa lạc tại Quận Tân Bình, chỉ cách Sân bay quốc tế Tân Sơn Nhất khoảng 2 km, với 42 phòng tiện nghi, lễ tân 24 giờ và dịch vụ đưa đón sân bay.", "Sunshine Airport Hotel is a 4-star hotel in Tan Binh District, only about 2 km from Tan Son Nhat International Airport, featuring 42 well-appointed rooms, a 24-hour front desk and airport transfer service."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("1100000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1571429"))
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
