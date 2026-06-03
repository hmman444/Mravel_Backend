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
public class SeedHoiAnNatureResort {

        @Bean
        CommandLineRunner seedHoiAnNatureResortRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHoiAnNatureResort] start");
                        HotelDoc doc = hotelNatureResort();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHoiAnNatureResort] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHoiAnNatureResort] done");
                };
        }

        // =====================================================================
        // NATURE RESORT HOI AN  (source: Traveloka, hotelId 9000005501765)
        // =====================================================================
        private static HotelDoc hotelNatureResort() {
                String slug = "nature-resort-hoi-an";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-1c4134d95a36afb8562086c2bf489e32.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d7393521442ca2fd2f69a99d3f3b388f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất / Toà nhà", "Exterior / Building", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-54021f39bd4968f777df3285959c7922.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất / Toà nhà", "Exterior / Building", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-4dc0b4222d052fb545a5ced7eb9674c6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất / Toà nhà", "Exterior / Building", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-94b3c728e42310ed5a9f5a4f921888fe.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-80e2bf536b704ae7817595e63d58033c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất / Toà nhà", "Exterior / Building", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-9e0857a2cee00181eb4e862bd1e69c5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-320f6a1aa8a9c6cb5e17159ed71e1677.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar / Cafe / Lounge", "Bar, cafe & lounge", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-bd71cfa403f67820f9aaf0e21ea272e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-c7ff60fcf921da5049dd1a25792b56e1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất / Toà nhà", "Exterior / Building", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-7b13486a754ed57c9475811d48ad0f7e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Dịch vụ khách sạn", "Hotel services", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-255ca38caab95cb64ab128fce42e0442.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-84ca161bda66178da5259c62a2538662.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-26662e73867550ef947e25bda4d07182.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-6c345cd52be0814eb49a3082a7e4f7e4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu giải trí", "Entertainment facility", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-e1b8abe001b396caea897f816486cb8c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu giải trí", "Entertainment facility", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-b16e32cdd640cc8549062111a74b9a7c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-587ef33bb699af6add52d87898d5f8f9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-4d56e44e4a4ea5819f9579a4876d68c0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-944e122c9f303b6ed7298f5ba5a0dba3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-4481a4edc9e82e5a204e10979492efec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-b6c673a76ef00c75e0de979aabe18e50.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-05ab30c847fb5e1523bf0ca4d20c5f39.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-832146d7cf5b0863760e9b4687537012.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực resort", "Resort area", false, 23));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.3192271, 15.8966904 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Nature Resort Hoi An", "About Nature Resort Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Nature Resort Hoi An là khu nghỉ dưỡng đạt tiêu chuẩn 4 sao, tọa lạc yên bình tại Cẩm Hà, Hội An, Tỉnh Quảng Nam. Nằm giữa khung cảnh làng quê xanh mát với những khu vườn thoáng đãng và lối đi rợp bóng cây, resort mang đến không gian nghỉ dưỡng tách biệt khỏi sự ồn ào nhưng vẫn chỉ cách phố cổ Hội An vài phút di chuyển. Đây là lựa chọn lý tưởng cho các cặp đôi, gia đình và nhóm bạn muốn tận hưởng một kỳ nghỉ thư thái, hòa mình vào thiên nhiên.",
                                                                "Nature Resort Hoi An is a 4-star resort peacefully located in Cam Ha, Hoi An, Quang Nam Province. Set amid green countryside scenery with airy gardens and tree-lined pathways, the resort offers a retreat away from the noise while remaining just minutes from Hoi An Ancient Town. It is an ideal choice for couples, families and groups of friends looking to enjoy a relaxing getaway close to nature.")),

                                heading(ContentSection.STORY, of("Các loại phòng tại Nature Resort Hoi An", "Room types at Nature Resort Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Resort cung cấp đa dạng hạng phòng từ Standard, Deluxe đến Family, đáp ứng nhu cầu của cả khách lẻ lẫn gia đình đông người. Hầu hết các phòng đều hướng ra sân vườn xanh mát, được bài trí ấm cúng và trang bị máy lạnh, WiFi miễn phí, vòi tắm đứng cùng nước nóng. Các phòng Family rộng rãi còn có thêm khu vực chờ, phù hợp cho gia đình có trẻ nhỏ nghỉ ngơi sau ngày dài khám phá.",
                                                                "The resort offers a variety of room categories from Standard and Deluxe to Family, catering to both solo travelers and larger families. Most rooms face the lush courtyard, are cozily furnished and come with air conditioning, free WiFi, a standing shower and hot water. The spacious Family rooms also feature a seating area, perfect for families with children to relax after a long day of exploring.")),

                                heading(ContentSection.STORY, of("Ẩm thực tại Nature Resort Hoi An", "Dining at Nature Resort Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng trong khuôn viên phục vụ các món ăn địa phương đặc trưng của Hội An và Quảng Nam như cao lầu, mì Quảng, cùng nhiều lựa chọn món Á – Âu. Quầy bar và khu lounge là nơi lý tưởng để nhâm nhi đồ uống, thư giãn vào buổi tối trong không gian gần gũi với thiên nhiên. Du khách cũng có thể bắt đầu ngày mới với bữa sáng nhẹ nhàng giữa khung cảnh sân vườn yên tĩnh.",
                                                                "The on-site restaurant serves signature local dishes of Hoi An and Quang Nam such as cao lau and mi Quang, along with a range of Asian and European options. The bar and lounge are ideal spots to enjoy a drink and unwind in the evening, surrounded by nature. Guests can also start their day with a relaxed breakfast amid the quiet garden setting.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ nổi bật", "Standout facilities & services")),
                                paragraph(ContentSection.STORY,
                                                of("Resort sở hữu khuôn viên sân vườn rộng rãi cùng các khu sinh hoạt chung thoáng đãng để du khách thư giãn. Lễ tân phục vụ 24 giờ luôn sẵn sàng hỗ trợ nhận/trả phòng và giải đáp thông tin du lịch. Ngoài ra còn có bãi đậu xe rộng, dịch vụ dọn phòng hằng ngày, WiFi miễn phí tại khu vực công cộng và hỗ trợ đặt tour, thuê xe để du khách dễ dàng khám phá Hội An.",
                                                                "The resort features expansive gardens and airy common areas for guests to relax. A 24-hour front desk is always on hand to assist with check-in/check-out and travel information. There is also ample parking, daily housekeeping, free WiFi in public areas, and support for tour booking and vehicle rental so guests can easily explore Hoi An.")),

                                heading(ContentSection.STORY, of("Điểm tham quan gần Nature Resort Hoi An", "Attractions near Nature Resort Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Từ resort, du khách dễ dàng ghé thăm các điểm tâm linh và văn hóa lâu đời của Cẩm Hà như Chùa Phước Lâm, Chùa Chúc Thánh và Ancient House. Phố cổ Hội An – Di sản Văn hóa Thế giới với những dãy nhà cổ kính, đèn lồng rực rỡ và ẩm thực phong phú – chỉ cách đó một quãng ngắn. Làng rau Trà Quế nổi tiếng và các bãi biển đẹp của Hội An cũng nằm trong tầm di chuyển thuận tiện.",
                                                                "From the resort, guests can easily visit the long-standing spiritual and cultural sites of Cam Ha such as Phuoc Lam Pagoda, Chuc Thanh Pagoda and Ancient House. Hoi An Ancient Town – a World Cultural Heritage site with its old houses, vibrant lanterns and rich cuisine – is just a short distance away. The famous Tra Que Vegetable Village and Hoi An's beautiful beaches are also within convenient reach.")),

                                heading(ContentSection.STORY, of("Di chuyển & giao thông", "Getting around & transport")),
                                paragraph(ContentSection.STORY,
                                                of("Nature Resort Hoi An nằm cách trung tâm phố cổ khoảng 2,5 km, thuận tiện di chuyển bằng xe đạp, xe máy hoặc taxi. Resort hỗ trợ thuê xe đạp và xe máy để du khách tự do dạo quanh làng quê Cẩm Hà yên bình. Sân bay quốc tế Đà Nẵng cách resort khoảng 40 phút lái xe, có thể sắp xếp dịch vụ đưa đón theo yêu cầu.",
                                                                "Nature Resort Hoi An is about 2.5 km from the heart of the ancient town, easily reached by bicycle, motorbike or taxi. The resort offers bicycle and motorbike rental so guests can freely roam the peaceful Cam Ha countryside. Da Nang International Airport is around a 40-minute drive away, and transfer services can be arranged on request.")),

                                heading(ContentSection.STORY, of("Khu vực xung quanh resort", "The area around the resort")),
                                paragraph(ContentSection.STORY,
                                                of("Cẩm Hà là vùng ngoại ô bình yên của Hội An, nổi tiếng với những cánh đồng rau xanh mướt, vườn cây và nhịp sống chậm rãi đậm chất làng quê. Buổi sáng, du khách có thể đạp xe qua các con đường nhỏ, ghé thăm làng rau hữu cơ; buổi chiều thư giãn trong khuôn viên resort rồi tối đến hòa vào không khí lễ hội đèn lồng của phố cổ. Sự kết hợp giữa thiên nhiên và văn hóa khiến khu vực này đặc biệt cuốn hút.",
                                                                "Cam Ha is a tranquil suburb of Hoi An, known for its verdant vegetable fields, orchards and a slow, rural pace of life. In the morning, guests can cycle along small lanes and visit the organic vegetable village; in the afternoon relax within the resort grounds, then in the evening immerse themselves in the lantern-lit atmosphere of the ancient town. The blend of nature and culture makes this area especially appealing.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Nature Resort Hoi An", "Why choose Nature Resort Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Chọn Nature Resort Hoi An, du khách có được sự cân bằng hiếm có giữa không gian nghỉ dưỡng yên tĩnh, gần gũi thiên nhiên và vị trí thuận tiện để khám phá phố cổ. Với phòng ốc thoải mái, dịch vụ chu đáo, ẩm thực địa phương hấp dẫn cùng mức giá hợp lý cho tiêu chuẩn 4 sao, đây là điểm dừng chân lý tưởng cho cả kỳ nghỉ ngắn ngày lẫn dài ngày tại Hội An.",
                                                                "Choosing Nature Resort Hoi An gives travelers a rare balance between a quiet, nature-immersed retreat and a convenient base for exploring the ancient town. With comfortable rooms, attentive service, appealing local cuisine and reasonable rates for a 4-star standard, it is an ideal stay for both short and long getaways in Hoi An.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM,
                        AmenitySection.BASIC_FACILITIES, true),
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET,
                        AmenitySection.INTERNET, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.BASIC_FACILITIES, false),
                amenity("room_service_limited", of("Dịch vụ phòng (giới hạn)", "Room service (limited)"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.FOOD_AND_DRINK, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.FOOD_AND_DRINK, false),
                amenity("garden", of("Sân vườn", "Garden"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.OTHER, false),
                amenity("outdoor_pool", of("Hồ bơi ngoài trời", "Outdoor pool"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.ENTERTAINMENT, true),
                amenity("spa", of("Spa", "Spa"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.ENTERTAINMENT, false),
                amenity("massage", of("Dịch vụ massage", "Massage"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Quán cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.FOOD_AND_DRINK, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Dịch vụ cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Dịch vụ thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.BASIC_FACILITIES, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.OTHER, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.FOOD_AND_DRINK, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE,
                        AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA,
                        AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-nature-standard-double-courtyard-view")
                        .name(of("Standard Double Courtyard View", "Standard Double Courtyard View"))
                        .shortDescription(of("Phòng Standard Double hướng sân vườn", "Standard Double room with courtyard view"))
                        .description(of("Phòng Standard Double hướng sân vườn, phù hợp tối đa 2 khách.", "Standard Double room with courtyard view, suitable for up to 2 guests."))
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-b8715d394588d0af447941b9f2b2637e.jpeg?tr=q-40,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-5e84ad3997b3aa58f2b99103e11a0571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d73f1e04c51542abbbfed9101fcfc1bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-nature-standard-double-courtyard-view-prepaid-nonref")
                                        .name(of("Standard Double Courtyard View - Không gồm bữa sáng", "Standard Double Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("460668"))
                                        .referencePricePerNight(new BigDecimal("658097"))
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
                                        .id("rt-nature-standard-double-courtyard-view-payathotel-flex")
                                        .name(of("Standard Double Courtyard View - Không gồm bữa sáng", "Standard Double Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("506735"))
                                        .referencePricePerNight(new BigDecimal("723907"))
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
                        .id("rt-nature-deluxe-double-courtyard-view")
                        .name(of("Deluxe Double Courtyard View", "Deluxe Double Courtyard View"))
                        .shortDescription(of("Phòng Deluxe Double hướng sân vườn", "Deluxe Double room with courtyard view"))
                        .description(of("Phòng Deluxe Double hướng sân vườn, phù hợp tối đa 2 khách.", "Deluxe Double room with courtyard view, suitable for up to 2 guests."))
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
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-aca46849c491fdddbfe89a511ee43fb0.jpeg?tr=q-40,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-5e84ad3997b3aa58f2b99103e11a0571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d73f1e04c51542abbbfed9101fcfc1bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-nature-deluxe-double-courtyard-view-prepaid-nonref")
                                        .name(of("Deluxe Double Courtyard View - Không gồm bữa sáng", "Deluxe Double Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("560668"))
                                        .referencePricePerNight(new BigDecimal("800954"))
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
                                        .id("rt-nature-deluxe-double-courtyard-view-payathotel-flex")
                                        .name(of("Deluxe Double Courtyard View - Không gồm bữa sáng", "Deluxe Double Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("616735"))
                                        .referencePricePerNight(new BigDecimal("881050"))
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
                        .id("rt-nature-deluxe-twin-courtyard-view")
                        .name(of("Deluxe Twin Courtyard View", "Deluxe Twin Courtyard View"))
                        .shortDescription(of("Phòng Deluxe Twin hướng sân vườn", "Deluxe Twin room with courtyard view"))
                        .description(of("Phòng Deluxe Twin hướng sân vườn, phù hợp tối đa 2 khách.", "Deluxe Twin room with courtyard view, suitable for up to 2 guests."))
                        .bedType(BedType.SINGLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-7c59d07c6748e7d546bc3cb79cff8311.jpeg?tr=q-40,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-5e84ad3997b3aa58f2b99103e11a0571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d73f1e04c51542abbbfed9101fcfc1bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-nature-deluxe-twin-courtyard-view-prepaid-nonref")
                                        .name(of("Deluxe Twin Courtyard View - Không gồm bữa sáng", "Deluxe Twin Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("545668"))
                                        .referencePricePerNight(new BigDecimal("779526"))
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
                                        .id("rt-nature-deluxe-twin-courtyard-view-payathotel-flex")
                                        .name(of("Deluxe Twin Courtyard View - Không gồm bữa sáng", "Deluxe Twin Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("600235"))
                                        .referencePricePerNight(new BigDecimal("857478"))
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
                        .id("rt-nature-family-triple-courtyard-view")
                        .name(of("Family Triple Courtyard View", "Family Triple Courtyard View"))
                        .shortDescription(of("Phòng Family Triple hướng sân vườn", "Family Triple room with courtyard view"))
                        .description(of("Phòng Family Triple hướng sân vườn, phù hợp tối đa 3 khách.", "Family Triple room with courtyard view, suitable for up to 3 guests."))
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-11f8b4f121f14eab6ce3f77d020f6c73.jpeg?tr=q-40,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-5e84ad3997b3aa58f2b99103e11a0571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d73f1e04c51542abbbfed9101fcfc1bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "SEATING_AREA",
                                "HOT_WATER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BALCONY"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-nature-family-triple-courtyard-view-prepaid-nonref")
                                        .name(of("Family Triple Courtyard View - Không gồm bữa sáng", "Family Triple Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("750668"))
                                        .referencePricePerNight(new BigDecimal("1072383"))
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
                                        .id("rt-nature-family-triple-courtyard-view-payathotel-flex")
                                        .name(of("Family Triple Courtyard View - Không gồm bữa sáng", "Family Triple Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("825735"))
                                        .referencePricePerNight(new BigDecimal("1179621"))
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
                        .id("rt-nature-family-quadruple-courtyard-view")
                        .name(of("Family Quadruple Courtyard View", "Family Quadruple Courtyard View"))
                        .shortDescription(of("Phòng Family Quadruple hướng sân vườn", "Family Quadruple room with courtyard view"))
                        .description(of("Phòng Family Quadruple hướng sân vườn, phù hợp tối đa 4 khách.", "Family Quadruple room with courtyard view, suitable for up to 4 guests."))
                        .bedType(BedType.KING)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường cỡ King", "2 king beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(2).build()))
                        .maxAdults(4)
                        .maxChildren(0)
                        .maxGuests(4)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-6f7a9e2fe93f84130113646136bd4f9d.jpeg?tr=q-40,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-5e84ad3997b3aa58f2b99103e11a0571.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20084508-d73f1e04c51542abbbfed9101fcfc1bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
                        .amenityCodes(List.of(
                                "SHOWER",
                                "SEATING_AREA",
                                "HOT_WATER",
                                "AIR_CONDITIONING",
                                "WIFI_FREE",
                                "MINIBAR",
                                "DESK",
                                "HAIR_DRYER",
                                "PRIVATE_BATHROOM",
                                "BATH_AMENITIES",
                                "SLIPPERS",
                                "BOTTLED_WATER",
                                "COFFEE_TEA_MAKER",
                                "BALCONY"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-nature-family-quadruple-courtyard-view-prepaid-nonref")
                                        .name(of("Family Quadruple Courtyard View - Không gồm bữa sáng", "Family Quadruple Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("950668"))
                                        .referencePricePerNight(new BigDecimal("1358097"))
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
                                        .id("rt-nature-family-quadruple-courtyard-view-payathotel-flex")
                                        .name(of("Family Quadruple Courtyard View - Không gồm bữa sáng", "Family Quadruple Courtyard View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1045735"))
                                        .referencePricePerNight(new BigDecimal("1493907"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Thanh toán tại khách sạn", "Pay at hotel"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build());

                List<NearbyPlace> nearby = List.of(
                nearby("chua-phuoc-lam", of("Chùa Phước Lâm", "Phuoc Lam Pagoda"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("chua-chuc-thanh", of("Chùa Chúc Thánh", "Chuc Thanh Pagoda"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
                nearby("ancient-house-hoi-an", of("Ancient House", "Ancient House"), of("Địa điểm lân cận", "Nearby places"), 800.0),
                nearby("nghia-trang-nhan-dan-hoi-an", of("Nghĩa trang nhân dân TP. Hội An", "Hoi An City People's Cemetery"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("dinh-cam-pho", of("Đình Cẩm Phô", "Cam Pho Communal House"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("lang-rau-tra-que", of("Làng rau Trà Quế", "Tra Que Vegetable Village"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("pho-co-hoi-an", of("Phố cổ Hội An", "Hoi An Ancient Town"), of("Địa điểm lân cận", "Nearby places"), 2600.0),
                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Địa điểm lân cận", "Nearby places"), 2700.0),
                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Địa điểm lân cận", "Nearby places"), 2800.0),
                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Địa điểm lân cận", "Nearby places"), 2750.0),
                nearby("nha-tan-ky", of("Nhà cổ Tân Kỳ", "Tan Ky Old House"), of("Địa điểm lân cận", "Nearby places"), 2650.0),
                nearby("nha-co-phung-hung", of("Nhà cổ Phùng Hưng", "Phung Hung Old House"), of("Địa điểm lân cận", "Nearby places"), 2680.0),
                nearby("bao-tang-van-hoa-sa-huynh", of("Bảo tàng Văn hóa Sa Huỳnh", "Sa Huynh Culture Museum"), of("Địa điểm lân cận", "Nearby places"), 2620.0),
                nearby("rung-dua-bay-mau", of("Rừng dừa Bảy Mẫu", "Bay Mau Coconut Forest"), of("Trung tâm giải trí", "Entertainment hub"), 4500.0),
                nearby("bai-bien-an-bang", of("Bãi biển An Bàng", "An Bang Beach"), of("Bãi biển", "Beach"), 4200.0),
                nearby("bai-bien-cua-dai", of("Bãi biển Cửa Đại", "Cua Dai Beach"), of("Bãi biển", "Beach"), 5500.0),
                nearby("ky-uc-hoi-an", of("Ký ức Hội An", "Hoi An Memories Show"), of("Trung tâm giải trí", "Entertainment hub"), 3500.0),
                nearby("chua-hai-tang", of("Chùa Hải Tạng", "Hai Tang Pagoda"), of("Khác", "Other"), 3000.0),
                nearby("quan-cong-mieu", of("Quan Công Miếu", "Quan Cong Temple"), of("Khác", "Other"), 2900.0),
                nearby("bao-tang-gom-su", of("Bảo tàng gốm sứ mậu dịch Hội An", "Hoi An Museum of Trade Ceramics"), of("Khác", "Other"), 2850.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, WiFi miễn phí, Bãi đậu xe, Lễ tân 24h", "Air conditioning, free WiFi, parking, 24-hour front desk"))
                                .distanceToCityCenterKm(2.5)
                                .popularAreaSummary(of("Chùa Phước Lâm, Chùa Chúc Thánh, Ancient House", "Phuoc Lam Pagoda, Chuc Thanh Pagoda, Ancient House"))
                                .totalRooms(78)
                                .otherHighlightFacilities(of("Nhà hàng, quầy bar, dịch vụ dọn phòng.", "Restaurant, bar, room service."))
                                .interestingPlacesSummary(of("Phố cổ Hội An, Chùa Phước Lâm, Đình Cẩm Phô", "Hoi An Ancient Town, Phuoc Lam Pagoda, Cam Pho Communal House"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder()
                                                                .section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                                                                .title(of("Hướng dẫn nhận phòng chung", "General check-in guide"))
                                                                .content(of("Vui lòng liên hệ trước nếu bạn cần hỗ trợ nhận phòng muộn hoặc có yêu cầu đặc biệt.", "Please contact us in advance if you need late check-in support or have special requests."))
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
                                .hasBreakfastIncluded(false)
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
                faq(of("Những tiện ích tại Nature Resort Hoi An?", "What amenities does Nature Resort Hoi An offer?"),
                        of("Những tiện ích tại Nature Resort Hoi An bao gồm Máy lạnh, Lễ tân 24h, Chỗ đậu xe, WiFi (một số dịch vụ có thể yêu cầu trả thêm phí).", "Amenities at Nature Resort Hoi An include air conditioning, 24-hour front desk, parking and WiFi (some services may require an additional fee).")),
                faq(of("Nature Resort Hoi An có mức giá là bao nhiêu?", "How much does Nature Resort Hoi An cost?"),
                        of("Phòng tại Nature Resort Hoi An có giá từ khoảng 460.668 VND. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at Nature Resort Hoi An start from around 460,668 VND. Prices may vary depending on the date and promotions.")),
                faq(of("Thời gian nhận phòng và trả phòng của Nature Resort Hoi An?", "What are the check-in and check-out times at Nature Resort Hoi An?"),
                        of("Thời gian nhận phòng tại Nature Resort Hoi An là từ 14:00 và trả phòng trước 12:00.", "Check-in at Nature Resort Hoi An is from 14:00 and check-out is before 12:00.")),
                faq(of("Khu nghỉ dưỡng Nature Resort Hoi An đạt tiêu chuẩn mấy sao?", "What star rating does Nature Resort Hoi An have?"),
                        of("Nature Resort Hoi An là khu nghỉ dưỡng đạt tiêu chuẩn 4.0 sao.", "Nature Resort Hoi An is a 4.0-star resort.")),
                faq(of("Tổng số phòng hiện có tại Nature Resort Hoi An là bao nhiêu?", "How many rooms does Nature Resort Hoi An have in total?"),
                        of("Tổng số phòng tại Nature Resort Hoi An là 78 phòng.", "Nature Resort Hoi An has a total of 78 rooms.")),
                faq(of("Những địa điểm nổi bật nào nằm gần Nature Resort Hoi An?", "What notable places are near Nature Resort Hoi An?"),
                        of("Một số địa điểm nổi bật gần Nature Resort Hoi An gồm Chùa Phước Lâm, Chùa Chúc Thánh, Ancient House, Nghĩa trang nhân dân TP. Hội An và Đình Cẩm Phô.", "Some notable places near Nature Resort Hoi An include Phuoc Lam Pagoda, Chuc Thanh Pagoda, Ancient House, Hoi An City People's Cemetery and Cam Pho Communal House.")),
                faq(of("Những loại phòng đang khả dụng tại Nature Resort Hoi An?", "What room types are available at Nature Resort Hoi An?"),
                        of("Các loại phòng tại Nature Resort Hoi An gồm Standard Double Courtyard View, Deluxe Double Courtyard View, Deluxe Twin Courtyard View, Family Triple Courtyard View và Family Quadruple Courtyard View.", "The available room types at Nature Resort Hoi An are Standard Double Courtyard View, Deluxe Double Courtyard View, Deluxe Twin Courtyard View, Family Triple Courtyard View and Family Quadruple Courtyard View.")),
                faq(of("Phòng tại Nature Resort Hoi An được trang bị những tiện nghi gì?", "What amenities are the rooms at Nature Resort Hoi An equipped with?"),
                        of("Hầu hết các phòng tại Nature Resort Hoi An được trang bị vòi tắm đứng, máy lạnh, WiFi miễn phí, khu vực chờ và nước nóng. Tiện nghi có thể khác nhau tùy loại phòng.", "Most rooms at Nature Resort Hoi An are equipped with a standing shower, air conditioning, free WiFi, a seating area and hot water. Amenities may vary by room type.")),
                faq(of("Mỗi phòng tại Nature Resort Hoi An có thể lưu trú tối đa bao nhiêu khách?", "What is the maximum number of guests per room at Nature Resort Hoi An?"),
                        of("Nature Resort Hoi An có thể chứa tối đa 4 khách trong một phòng, tùy thuộc vào loại phòng bạn đặt.", "Nature Resort Hoi An can accommodate up to 4 guests per room, depending on the room type you book.")),
                faq(of("Nature Resort Hoi An có hồ bơi không?", "Does Nature Resort Hoi An have a pool?"),
                        of("Có, Nature Resort Hoi An có hồ bơi ngoài trời cùng khu vực sân vườn để du khách thư giãn.", "Yes, Nature Resort Hoi An has an outdoor pool and garden areas for guests to relax.")),
                faq(of("Nature Resort Hoi An có dịch vụ đưa đón sân bay không?", "Does Nature Resort Hoi An offer airport transfer?"),
                        of("Có, resort hỗ trợ dịch vụ đưa đón sân bay (có thu phí) và cho thuê xe đạp/xe máy; vui lòng liên hệ trước để được sắp xếp.", "Yes, the resort offers airport transfer (surcharge) as well as bicycle/motorbike rental; please contact in advance to arrange.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName(of("Hội An", "Hoi An"))
                                .districtName(of("Cẩm Hà", "Cam Ha"))
                                .wardName(null)
                                .addressLine(of("Block A, Trảng Kèo 6, Cẩm Hà, Hội An, Tỉnh Quảng Nam, Việt Nam, 51300", "Block A, Trang Keo 6, Cam Ha, Hoi An, Quang Nam Province, Vietnam, 51300"))
                                .location(new double[] { 108.3192271, 15.8966904 })
                                .name(of("Nature Resort Hoi An", "Nature Resort Hoi An"))
                                .slug(slug)
                                .hotelType(HotelType.RESORT)
                                .starRating(4)
                                .shortDescription(of("Khu nghỉ dưỡng 4 sao yên bình tại Cẩm Hà, Hội An, gần phố cổ.", "Peaceful 4-star resort in Cam Ha, Hoi An, near the ancient town."))
                                .description(of("Nature Resort Hoi An mang đến không gian nghỉ dưỡng xanh mát với phòng hướng sân vườn, nhà hàng, quầy bar và lễ tân 24 giờ. Vị trí thuận tiện để khám phá phố cổ Hội An và các điểm tham quan lân cận.", "Nature Resort Hoi An offers a green, relaxing escape with courtyard-view rooms, a restaurant, a bar and a 24-hour front desk. Its location is convenient for exploring Hoi An Ancient Town and nearby attractions."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("460668"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("658097"))
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
