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
public class SeedHoiAnWyndham {

        @Bean
        CommandLineRunner seedHoiAnWyndhamRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHoiAnWyndham] start");
                        HotelDoc doc = hotelWyndham();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHoiAnWyndham] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHoiAnWyndham] done");
                };
        }

        // =====================================================================
        // WYNDHAM HOI AN ROYAL BEACHFRONT RESORT & VILLAS
        // (source: Traveloka hotelId 9000001207182; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelWyndham() {
                String slug = "wyndham-hoi-an-royal-beachfront-resort-villas";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-8b98bc34babb3986047e732f3398cf4e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-dba8ecfc752d793dd5242a63cc73b469.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-efc1092521613b4bd2424505a7bf6db7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-2a480d0643d89c656351afc762466686.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-aa3de5d66a31582b44c774f9d20dcc41.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-4a3b725d698bf50953b60374de93c0b3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-b2ed469955118b8e37a126cac057bb8b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-4ef25915039d439208e93ead78d0153f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-bab8878521d0fb2d5ba84a5105378571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar / Cafe / Lounge", "Bar, cafe & lounge", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-9505acf4b02c52674c3937b24c15cc7c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-fd5ddb0ce2247d896b5fbf95f6b95622.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-366b461f4401550005838a2cf38a6155.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-13ea22d15d1ec5aff8628eadc0ffd6ae.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-26cb428f02f50064116abe5c5a853a4d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-738acdbd26cb4e89fcb8215c556b50be.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-c19e0cc19fdb0b0add3b91145d7a66a0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-b088f86407ca7299ca24e480252599eb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-4b62f7589ec3d0548f27d00339abdd9d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-db6d02f454daa699dfae9b3281d93426.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-67a02bbbfd8e8af3781014e686363925.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-0e330c647aced80f5cfe1c4b8a8fec91.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-f67a524b1664f41e11bb860a99acfb58.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-19d5c61394d8612384de69c4f0bfaa06.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-915cc09d89ab75a011d2c766701dc90a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar / Cafe / Lounge", "Bar, cafe & lounge", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-0bb3a6185289051e732ccf79a59f9611.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-19ce06925bf7e8d71deee5885c610df0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 25));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.32729096681659, 15.922438422219777 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Wyndham Hoi An Royal Beachfront Resort & Villas", "About Wyndham Hoi An Royal Beachfront Resort & Villas")),
                                paragraph(ContentSection.STORY,
                                                of("Wyndham Hoi An Royal Beachfront Resort & Villas là khu nghỉ dưỡng 5 sao bên bờ biển Hà My, Xã Điện Dương, nằm giữa Hội An và Đà Nẵng. Với 565 phòng và villa trải trên 11 tầng cùng đường bờ biển riêng dài thơ mộng, đây là điểm đến lý tưởng cho kỳ nghỉ biển sang trọng, các cặp đôi hưởng tuần trăng mật và gia đình muốn tận hưởng dịch vụ đẳng cấp quốc tế.",
                                                                "Wyndham Hoi An Royal Beachfront Resort & Villas is a 5-star resort on Ha My Beach, Dien Duong Ward, sitting between Hoi An and Da Nang. With 565 rooms and villas across 11 floors and a long private beachfront, it is an ideal destination for a luxurious beach holiday, honeymooning couples and families seeking international-class service.")),

                                heading(ContentSection.STORY, of("Phòng nghỉ & villa hướng biển", "Ocean-view rooms & villas")),
                                paragraph(ContentSection.STORY,
                                                of("Resort cung cấp đa dạng hạng phòng từ Deluxe, Family đến Suite và Pool Villa, phần lớn đều hướng biển hoặc hướng hồ bơi. Mỗi phòng được trang bị máy lạnh, minibar, bồn tắm, áo choàng tắm, két an toàn, ban công riêng và Wi-Fi miễn phí. Các villa rộng rãi còn có hồ bơi riêng và không gian sống biệt lập, phù hợp cho gia đình và nhóm bạn.",
                                                                "The resort offers a wide range of room categories from Deluxe and Family to Suite and Pool Villa, most facing the ocean or the pool. Each room comes with air conditioning, a minibar, a bathtub, a bathrobe, a safe, a private balcony and free Wi-Fi. The spacious villas also feature a private pool and secluded living space, ideal for families and groups.")),

                                heading(ContentSection.STORY, of("Ẩm thực đa dạng", "Diverse dining")),
                                paragraph(ContentSection.STORY,
                                                of("Hệ thống nhà hàng và quầy bar phục vụ ẩm thực Á – Âu phong phú, từ bữa sáng buffet thịnh soạn đến thực đơn gọi món cho bữa trưa và bữa tối. Du khách có thể nhâm nhi đồ uống tại quầy bar bên hồ bơi, quầy bar bên bãi biển hoặc thưởng thức cà phê tại sảnh trong không gian sang trọng nhìn ra biển.",
                                                                "The resort's restaurants and bars serve a rich variety of Asian and European cuisine, from a hearty breakfast buffet to à la carte menus for lunch and dinner. Guests can enjoy a drink at the poolside bar or beach bar, or savor coffee in the lobby amid an elegant setting overlooking the sea.")),

                                heading(ContentSection.STORY, of("Hồ bơi, bãi biển riêng & thể thao biển", "Pools, private beach & water sports")),
                                paragraph(ContentSection.STORY,
                                                of("Điểm nhấn của resort là hồ bơi ngoài trời rộng lớn cùng hồ bơi trẻ em và khu ghế tắm nắng. Bãi biển riêng với cát trắng mịn, dù che và ghế dài là nơi lý tưởng để thư giãn; du khách yêu vận động có thể trải nghiệm các môn thể thao dưới nước ngay tại bãi.",
                                                                "A highlight of the resort is its large outdoor pool along with a children pool and sun-lounger area. The private white-sand beach with umbrellas and loungers is perfect for relaxing; active guests can enjoy water sports right on the beach.")),

                                heading(ContentSection.STORY, of("Spa, chăm sóc sức khỏe & giải trí", "Spa, wellness & entertainment")),
                                paragraph(ContentSection.STORY,
                                                of("Khu spa cao cấp với các liệu trình massage, xông hơi khô và ướt giúp du khách thư giãn trọn vẹn. Resort còn có trung tâm thể dục, câu lạc bộ đêm và khu vui chơi để làm phong phú thêm trải nghiệm nghỉ dưỡng cả ngày lẫn đêm.",
                                                                "The premium spa offers massage therapies, dry and steam saunas for complete relaxation. The resort also features a fitness center, a nightclub and recreation areas to enrich the holiday experience day and night.")),

                                heading(ContentSection.STORY, of("Tiện ích cho gia đình", "Family-friendly facilities")),
                                paragraph(ContentSection.STORY,
                                                of("Gia đình có trẻ nhỏ sẽ hài lòng với câu lạc bộ thiếu nhi, khu vui chơi trẻ em và hồ bơi dành riêng cho bé. Dịch vụ giữ trẻ (thu phí) cùng các hạng phòng Family và villa rộng rãi giúp cả nhà có kỳ nghỉ thoải mái, an toàn.",
                                                                "Families with children will appreciate the kids club, children play area and a dedicated children pool. Babysitting (surcharge) along with spacious Family rooms and villas help the whole family enjoy a comfortable, safe holiday.")),

                                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),
                                paragraph(ContentSection.STORY,
                                                of("Từ resort, du khách chỉ mất khoảng 15–20 phút lái xe để đến Phố cổ Hội An – Di sản Văn hóa Thế giới, hoặc khám phá rừng dừa Bảy Mẫu, làng rau Trà Quế và làng gốm Thanh Hà. Các bãi biển An Bàng, Cửa Đại cùng những điểm đến nổi tiếng của Đà Nẵng như Ngũ Hành Sơn, Bà Nà Hills đều nằm trong tầm di chuyển thuận tiện.",
                                                                "From the resort, it takes only about 15–20 minutes by car to reach Hoi An Ancient Town – a World Cultural Heritage site – or to explore the Bay Mau Coconut Forest, Tra Que Vegetable Village and Thanh Ha Pottery Village. An Bang and Cua Dai beaches, along with Da Nang's famous attractions such as the Marble Mountains and Ba Na Hills, are all within convenient reach.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Wyndham Hoi An Royal Beachfront Resort & Villas", "Why choose Wyndham Hoi An Royal Beachfront Resort & Villas")),
                                paragraph(ContentSection.STORY,
                                                of("Chọn Wyndham Hoi An Royal Beachfront Resort & Villas, du khách tận hưởng kỳ nghỉ biển trọn vẹn với bãi biển riêng, hệ thống hồ bơi, spa, ẩm thực đa dạng và dịch vụ 5 sao chuẩn thương hiệu quốc tế Wyndham. Vị trí giữa Hội An và Đà Nẵng còn giúp việc khám phá miền Trung trở nên dễ dàng – một lựa chọn lý tưởng cho cả nghỉ dưỡng lẫn khám phá.",
                                                                "Choosing Wyndham Hoi An Royal Beachfront Resort & Villas lets travelers enjoy a complete beach holiday with a private beach, a pool complex, a spa, diverse dining and 5-star service under the international Wyndham brand. Its location between Hoi An and Da Nang also makes exploring central Vietnam easy – an ideal choice for both relaxation and discovery.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi ngoài trời", "Outdoor pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("spa", of("Spa", "Spa"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("massage", of("Dịch vụ massage", "Massage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("garden", of("Vườn hoa", "Garden"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("babysitting", of("Dịch vụ giữ trẻ (thu phí)", "Babysitting (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-wyndham-deluxe-king-ocean-view")
                        .name(of("Deluxe King Ocean View", "Deluxe King Ocean View"))
                        .shortDescription(of("Phòng Deluxe 45 m² hướng biển, 1 giường King.", "Deluxe room 45 sqm with ocean view, 1 king bed."))
                        .description(of("Phòng Deluxe 45 m² hướng biển, 1 giường King. Trang bị đầy đủ tiện nghi cao cấp cho kỳ nghỉ thư giãn.", "Deluxe room 45 sqm with ocean view, 1 king bed. Fully equipped with premium amenities for a relaxing stay."))
                        .areaSqm(45.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-c1c2148b910165d94d166f10049e5856.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67870247-575ad90e79a7fb8bbb4f8690a83155f8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "BATHTUB",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "BATHROBE",
                                "HAIR_DRYER",
                                "DESK",
                                "SAFE",
                                "HOT_WATER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BALCONY",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-wyndham-deluxe-king-ocean-view-prepaid-nonref")
                                        .name(of("Deluxe King Ocean View - Không gồm bữa sáng", "Deluxe King Ocean View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1850000"))
                                        .referencePricePerNight(new BigDecimal("2642857"))
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
                                        .id("rt-wyndham-deluxe-king-ocean-view-breakfast-flex")
                                        .name(of("Deluxe King Ocean View - Bao gồm bữa sáng", "Deluxe King Ocean View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2127500"))
                                        .referencePricePerNight(new BigDecimal("3039286"))
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
                        .id("rt-wyndham-deluxe-twin-ocean-view")
                        .name(of("Deluxe Twin Ocean View", "Deluxe Twin Ocean View"))
                        .shortDescription(of("Phòng Deluxe 45 m² hướng biển, 2 giường đơn.", "Deluxe room 45 sqm with ocean view, 2 single beds."))
                        .description(of("Phòng Deluxe 45 m² hướng biển, 2 giường đơn. Trang bị đầy đủ tiện nghi cao cấp cho kỳ nghỉ thư giãn.", "Deluxe room 45 sqm with ocean view, 2 single beds. Fully equipped with premium amenities for a relaxing stay."))
                        .areaSqm(45.0)
                        .bedType(BedType.SINGLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-c7470085fca8ddfb1b93e2b607a93260.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-091720d5b59034569e3d51d08ff38d59.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "BATHTUB",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "BATHROBE",
                                "HAIR_DRYER",
                                "DESK",
                                "SAFE",
                                "HOT_WATER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BALCONY",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-wyndham-deluxe-twin-ocean-view-prepaid-nonref")
                                        .name(of("Deluxe Twin Ocean View - Không gồm bữa sáng", "Deluxe Twin Ocean View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1850000"))
                                        .referencePricePerNight(new BigDecimal("2642857"))
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
                                        .id("rt-wyndham-deluxe-twin-ocean-view-breakfast-flex")
                                        .name(of("Deluxe Twin Ocean View - Bao gồm bữa sáng", "Deluxe Twin Ocean View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2127500"))
                                        .referencePricePerNight(new BigDecimal("3039286"))
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
                        .id("rt-wyndham-grand-deluxe-family")
                        .name(of("Grand Deluxe Family", "Grand Deluxe Family"))
                        .shortDescription(of("Phòng Family 60 m², 1 giường King + 1 giường đơn, cho 3 khách.", "Family room 60 sqm, 1 king + 1 single bed, for 3 guests."))
                        .description(of("Phòng Family 60 m², 1 giường King + 1 giường đơn, cho 3 khách. Trang bị đầy đủ tiện nghi cao cấp cho kỳ nghỉ thư giãn.", "Family room 60 sqm, 1 king + 1 single bed, for 3 guests. Fully equipped with premium amenities for a relaxing stay."))
                        .areaSqm(60.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-36fbfecefb27b4c933952c52fb257140.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-8eb4f0be2fbec7ad7bf24d0523ac7950.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "BATHTUB",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "BATHROBE",
                                "HAIR_DRYER",
                                "DESK",
                                "SAFE",
                                "HOT_WATER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BALCONY",
                                "SEATING_AREA",
                                "TERRACE",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-wyndham-grand-deluxe-family-prepaid-nonref")
                                        .name(of("Grand Deluxe Family - Không gồm bữa sáng", "Grand Deluxe Family - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("2600000"))
                                        .referencePricePerNight(new BigDecimal("3714286"))
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
                                        .id("rt-wyndham-grand-deluxe-family-breakfast-flex")
                                        .name(of("Grand Deluxe Family - Bao gồm bữa sáng", "Grand Deluxe Family - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2990000"))
                                        .referencePricePerNight(new BigDecimal("4271429"))
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
                        .id("rt-wyndham-one-bedroom-suite-ocean-view")
                        .name(of("One-Bedroom Suite Ocean View", "One-Bedroom Suite Ocean View"))
                        .shortDescription(of("Suite 75 m² hướng biển, phòng khách riêng, 1 giường King.", "75 sqm ocean-view suite with separate living room, 1 king bed."))
                        .description(of("Suite 75 m² hướng biển, phòng khách riêng, 1 giường King. Trang bị đầy đủ tiện nghi cao cấp cho kỳ nghỉ thư giãn.", "75 sqm ocean-view suite with separate living room, 1 king bed. Fully equipped with premium amenities for a relaxing stay."))
                        .areaSqm(75.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-53ed6b324cc22f0831ff12d1422086a5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-13726b61fcc910e43b8b20b24b7f87b8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "BATHTUB",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "BATHROBE",
                                "HAIR_DRYER",
                                "DESK",
                                "SAFE",
                                "HOT_WATER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BALCONY",
                                "SEATING_AREA",
                                "TERRACE",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-wyndham-one-bedroom-suite-ocean-view-prepaid-nonref")
                                        .name(of("One-Bedroom Suite Ocean View - Không gồm bữa sáng", "One-Bedroom Suite Ocean View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("3500000"))
                                        .referencePricePerNight(new BigDecimal("5000000"))
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
                                        .id("rt-wyndham-one-bedroom-suite-ocean-view-breakfast-flex")
                                        .name(of("One-Bedroom Suite Ocean View - Bao gồm bữa sáng", "One-Bedroom Suite Ocean View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("4025000"))
                                        .referencePricePerNight(new BigDecimal("5750000"))
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
                        .id("rt-wyndham-two-bedroom-pool-villa")
                        .name(of("Two-Bedroom Pool Villa", "Two-Bedroom Pool Villa"))
                        .shortDescription(of("Villa 2 phòng ngủ 120 m² có hồ bơi riêng, cho 4 khách.", "120 sqm two-bedroom villa with private pool, for 4 guests."))
                        .description(of("Villa 2 phòng ngủ 120 m² có hồ bơi riêng, cho 4 khách. Trang bị đầy đủ tiện nghi cao cấp cho kỳ nghỉ thư giãn.", "120 sqm two-bedroom villa with private pool, for 4 guests. Fully equipped with premium amenities for a relaxing stay."))
                        .areaSqm(120.0)
                        .bedType(BedType.KING)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường cỡ King", "2 king beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(2).build()))
                        .maxAdults(4)
                        .maxChildren(0)
                        .maxGuests(4)
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-79dfd7037be5bb1b5a026e44e9ed7265.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20070205-b4a0b2bf8dfb1d0589c2bacbbbdb0336.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "BATHTUB",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "BATHROBE",
                                "HAIR_DRYER",
                                "DESK",
                                "SAFE",
                                "HOT_WATER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BALCONY",
                                "SEATING_AREA",
                                "TERRACE",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-wyndham-two-bedroom-pool-villa-prepaid-nonref")
                                        .name(of("Two-Bedroom Pool Villa - Không gồm bữa sáng", "Two-Bedroom Pool Villa - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("6500000"))
                                        .referencePricePerNight(new BigDecimal("9285714"))
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
                                        .id("rt-wyndham-two-bedroom-pool-villa-breakfast-flex")
                                        .name(of("Two-Bedroom Pool Villa - Bao gồm bữa sáng", "Two-Bedroom Pool Villa - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("7475000"))
                                        .referencePricePerNight(new BigDecimal("10678571"))
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
                nearby("bai-bien-ha-my", of("Bãi biển Hà My", "Ha My Beach"), of("Bãi biển", "Beach"), 150.0),
                nearby("bai-bien-an-bang", of("Bãi biển An Bàng", "An Bang Beach"), of("Bãi biển", "Beach"), 3500.0),
                nearby("bai-bien-cua-dai", of("Bãi biển Cửa Đại", "Cua Dai Beach"), of("Bãi biển", "Beach"), 6500.0),
                nearby("rung-dua-bay-mau", of("Rừng dừa Bảy Mẫu", "Bay Mau Coconut Forest"), of("Địa điểm lân cận", "Nearby places"), 7000.0),
                nearby("lang-rau-tra-que", of("Làng rau Trà Quế", "Tra Que Vegetable Village"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("pho-co-hoi-an", of("Phố cổ Hội An", "Hoi An Ancient Town"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Địa điểm lân cận", "Nearby places"), 9200.0),
                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Địa điểm lân cận", "Nearby places"), 9100.0),
                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Địa điểm lân cận", "Nearby places"), 9300.0),
                nearby("lang-gom-thanh-ha", of("Làng gốm Thanh Hà", "Thanh Ha Pottery Village"), of("Địa điểm lân cận", "Nearby places"), 12000.0),
                nearby("ky-uc-hoi-an", of("Ký ức Hội An", "Hoi An Memories Show"), of("Trung tâm giải trí", "Entertainment hub"), 9500.0),
                nearby("vinwonders-nam-hoi-an", of("VinWonders Nam Hội An", "VinWonders Nam Hoi An"), of("Trung tâm giải trí", "Entertainment hub"), 16000.0),
                nearby("bao-tang-van-hoa-sa-huynh", of("Bảo tàng Văn hóa Sa Huỳnh", "Sa Huynh Culture Museum"), of("Khác", "Other"), 9100.0),
                nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Khác", "Other"), 13000.0),
                nearby("bai-bien-my-khe", of("Bãi biển Mỹ Khê (Đà Nẵng)", "My Khe Beach (Da Nang)"), of("Bãi biển", "Beach"), 20000.0),
                nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 27000.0),
                nearby("cau-rong-da-nang", of("Cầu Rồng Đà Nẵng", "Dragon Bridge Da Nang"), of("Khác", "Other"), 25000.0),
                nearby("ba-na-hills", of("Khu du lịch Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 45000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Bãi biển riêng, hồ bơi, spa, nhà hàng, máy lạnh, WiFi miễn phí", "Private beach, pool, spa, restaurant, air conditioning, free WiFi"))
                                .distanceToCityCenterKm(9.0)
                                .popularAreaSummary(of("Bãi biển Hà My, Phố cổ Hội An, Bãi biển An Bàng", "Ha My Beach, Hoi An Ancient Town, An Bang Beach"))
                                .totalRooms(565)
                                .totalFloors(11)
                                .otherHighlightFacilities(of("Spa, trung tâm thể dục, câu lạc bộ thiếu nhi, đưa đón sân bay, thể thao dưới nước.", "Spa, fitness center, kids club, airport transfer, water sports."))
                                .interestingPlacesSummary(of("Phố cổ Hội An, Rừng dừa Bảy Mẫu, VinWonders Nam Hội An", "Hoi An Ancient Town, Bay Mau Coconut Forest, VinWonders Nam Hoi An"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(15, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 15:00. Giờ trả phòng: trước 12:00.", "Check-in: from 15:00. Check-out: before 12:00."))
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
                                                                .content(of("Resort thân thiện với gia đình: có câu lạc bộ thiếu nhi, hồ bơi trẻ em và dịch vụ giữ trẻ (thu phí).", "The resort is family-friendly with a kids club, children pool and babysitting (surcharge)."))
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
                faq(of("Những tiện ích tại Wyndham Hoi An Royal Beachfront Resort & Villas?", "What amenities does Wyndham Hoi An Royal Beachfront Resort & Villas offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, hồ bơi, spa, bãi biển riêng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, swimming pool, spa, private beach, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Wyndham Hoi An Royal Beachfront Resort & Villas có mức giá là bao nhiêu?", "How much does Wyndham Hoi An Royal Beachfront Resort & Villas cost?"),
                        of("Phòng tại resort có giá từ khoảng 1.850.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the resort start from around 1,850,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Thời gian nhận phòng và trả phòng?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 15:00 và trả phòng trước 12:00.", "Check-in is from 15:00 and check-out is before 12:00.")),
                faq(of("Resort có phục vụ ăn sáng không?", "Does the resort serve breakfast?"),
                        of("Có, resort phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the resort serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khu nghỉ dưỡng đạt tiêu chuẩn mấy sao?", "What star rating does the resort have?"),
                        of("Wyndham Hoi An Royal Beachfront Resort & Villas là khu nghỉ dưỡng đạt tiêu chuẩn 5.0 sao.", "Wyndham Hoi An Royal Beachfront Resort & Villas is a 5.0-star resort.")),
                faq(of("Resort có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does the resort have?"),
                        of("Resort có tổng cộng 11 tầng với 565 phòng và villa.", "The resort has a total of 11 floors with 565 rooms and villas.")),
                faq(of("Resort có hồ bơi không?", "Does the resort have a pool?"),
                        of("Có, resort có hồ bơi ngoài trời, hồ bơi trẻ em và quầy bar bên hồ bơi.", "Yes, the resort has an outdoor pool, a children pool and a poolside bar.")),
                faq(of("Resort có bãi biển riêng không?", "Does the resort have a private beach?"),
                        of("Có, resort nằm ngay bãi biển Hà My với bãi biển riêng, ghế tắm nắng và các môn thể thao dưới nước.", "Yes, the resort is right on Ha My Beach with a private beach, sun loungers and water sports.")),
                faq(of("Resort có hỗ trợ nhận phòng sớm không?", "Does the resort support early check-in?"),
                        of("Có, resort hỗ trợ nhận phòng sớm tùy tình trạng phòng trống; vui lòng liên hệ trước để được sắp xếp.", "Yes, the resort supports early check-in subject to availability; please contact in advance to arrange.")),
                faq(of("Resort có đưa đón sân bay không?", "Does the resort offer airport transfer?"),
                        of("Có, resort cung cấp dịch vụ đưa đón sân bay (thu phí) và đưa đón trong khu vực; sân bay Đà Nẵng cách khoảng 30 phút lái xe.", "Yes, the resort offers airport transfer (surcharge) and area shuttle; Da Nang Airport is about a 30-minute drive away.")),
                faq(of("Resort có phù hợp cho gia đình có trẻ em không?", "Is the resort suitable for families with children?"),
                        of("Rất phù hợp: resort có câu lạc bộ thiếu nhi, khu vui chơi trẻ em, hồ bơi trẻ em và các hạng phòng Family/Villa rộng rãi.", "Very suitable: the resort has a kids club, a children play area, a children pool and spacious Family/Villa room types.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName(of("Hội An", "Hoi An"))
                                .districtName(of("Điện Bàn", "Dien Ban"))
                                .wardName(of("Điện Dương", "Dien Duong"))
                                .addressLine(of("Biển Hà My, Xã Điện Dương, Thị xã Điện Bàn, Tỉnh Quảng Nam, Việt Nam, 560000", "Ha My Beach, Dien Duong Ward, Dien Ban Town, Quang Nam Province, Vietnam, 560000"))
                                .location(new double[] { 108.32729096681659, 15.922438422219777 })
                                .name(of("Wyndham Hoi An Royal Beachfront Resort & Villas", "Wyndham Hoi An Royal Beachfront Resort & Villas"))
                                .slug(slug)
                                .hotelType(HotelType.RESORT)
                                .starRating(5)
                                .shortDescription(of("Khu nghỉ dưỡng 5 sao bên bãi biển Hà My, giữa Hội An và Đà Nẵng.", "5-star beachfront resort on Ha My Beach, between Hoi An and Da Nang."))
                                .description(of("Wyndham Hoi An Royal Beachfront Resort & Villas mang đến kỳ nghỉ biển sang trọng với bãi biển riêng, hệ thống hồ bơi, spa, nhà hàng và villa có hồ bơi riêng, đạt chuẩn dịch vụ 5 sao của thương hiệu Wyndham.", "Wyndham Hoi An Royal Beachfront Resort & Villas offers a luxurious beach holiday with a private beach, a pool complex, a spa, restaurants and pool villas, delivering 5-star service under the Wyndham brand."))
                                .defaultCheckInTime(LocalTime.of(15, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("1850000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("2642857"))
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
