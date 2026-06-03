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
public class SeedNtAaron {

        @Bean
        CommandLineRunner seedNtAaronRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedNtAaron] start");
                        HotelDoc doc = hotelAaron();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedNtAaron] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedNtAaron] done");
                };
        }

        // =====================================================================
        // AARON HOTEL (Nha Trang)
        // (source: Traveloka hotelId 20003582; phòng & nội dung được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelAaron() {
                String slug = "aaron-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-b3baa52324d6bede9b757895c1d6faab.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-9d807696b9752d2fdc273fe43f26dac5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-061947cac6242696e975b65df8916811.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-eb61a5478397ce4bec81ee9a399f8772.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-6e2a04bb25acf4fa1ae139a84aeae086.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-16a7a2fee261e551fa96c9b9fd11ded5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-2140007c54c59ab58e8e4b108fb2932d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-cc4a9614f5a37067733bcc0af20f52c9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-6be5cb97ccb3680f3b7b694ab988a445.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-98af8b6b256ca7f9f16f46d8507d6654.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cơ sở thể thao", "Sports facility", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-77c20688ddf292ef0fc129b40e2313c4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-997a12fd41c6207c503361e5a724f7fe.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-a915f5e0d5416dd5ccc529e778dceba9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-966623f36866df054a68b60d6dd8099c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-c177f231829030bb0af07adc0f18cfc5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cơ sở thể thao", "Sports facility", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-cc3eb387a628351c663132ca551e6bb2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-9c6ac502d487da62c3d8189fe911bd2f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-72303199e67f4443047ba898df4be2ff.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-affb8c5b7964f0744b9d6a32e88f5ea4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-1e7fbbd7320c205504d21564f2569c3d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-86added577b73000258498f0905325b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-2ec550bb99bf54235ee0bab18774c9d2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-ae0b84968eb2932347f3ec93120f1752.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-19575c9d40174541e67a26a2485f9de2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-11731e1e11ed20b178dc611d9984a749.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-e335302993c5062b3ed9241fa0f14d50.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-92d5350a9a02b9da9bc1e2cc23e5bfc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-ee033ed511587eed258e5a200f621949.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-e28992a8daaeea3288f1d1a88d312b62.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-6e755c42bfd6681f3d165425b024267c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 29));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 109.19661180000003, 12.2334616 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Aaron Hotel", "About Aaron Hotel")),

                                paragraph(ContentSection.STORY,
                                                of("Aaron Hotel là khách sạn 4 sao tọa lạc tại 6/6 Trần Quang Khải, phường Lộc Thọ, trung tâm thành phố biển Nha Trang. Chỉ cách bãi biển Nha Trang vài bước chân, khách sạn cao 21 tầng với 70 phòng nghỉ hiện đại, là lựa chọn lý tưởng cho cả khách du lịch nghỉ dưỡng lẫn khách công tác muốn tận hưởng không khí biển ngay giữa lòng phố.", "Aaron Hotel is a 4-star hotel located at 6/6 Tran Quang Khai, Loc Tho ward, in the heart of the coastal city of Nha Trang. Just a few steps from Nha Trang Beach, the 21-floor hotel offers 70 modern rooms, an ideal choice for both leisure travelers and business guests who want to enjoy the seaside atmosphere right in the city center.")),

                                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),

                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp đa dạng hạng phòng từ Superior, Deluxe đến phòng hướng biển và phòng gia đình, đáp ứng nhu cầu của nhiều nhóm khách. Mỗi phòng được trang bị máy lạnh, minibar, két an toàn, bàn làm việc, phòng tắm riêng với máy sấy tóc cùng Wi-Fi miễn phí, mang lại không gian nghỉ ngơi tiện nghi và thoải mái.", "The hotel offers a variety of room categories from Superior and Deluxe to sea-view and family rooms, catering to different groups of guests. Each room is equipped with air conditioning, a minibar, a safe, a work desk, a private bathroom with a hair dryer and free Wi-Fi, providing a comfortable and convenient stay.")),

                                heading(ContentSection.STORY, of("Ẩm thực đa dạng", "Diverse dining")),

                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng của khách sạn phục vụ bữa sáng buffet phong phú cùng thực đơn gọi món Á – Âu cho bữa trưa và bữa tối. Du khách có thể thư giãn tại quầy bar, quán cà phê và sảnh chờ, hoặc nhâm nhi đồ uống tại quầy bar bên hồ bơi với tầm nhìn thoáng đãng.", "The hotel's restaurant serves a rich breakfast buffet along with à la carte Asian and European menus for lunch and dinner. Guests can relax at the bar, café and lounge, or sip a drink at the poolside bar with an open view.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                                paragraph(ContentSection.STORY,
                                                of("Aaron Hotel sở hữu hồ bơi ngoài trời, trung tâm thể dục, dịch vụ spa và massage giúp du khách thư giãn sau ngày dài khám phá. Lễ tân 24 giờ, dịch vụ phòng, giặt ủi, giữ hành lý, thu đổi ngoại tệ và đội ngũ nhân viên đa ngôn ngữ luôn sẵn sàng hỗ trợ.", "Aaron Hotel features an outdoor pool, a fitness center, spa and massage services to help guests unwind after a long day of exploring. A 24-hour front desk, room service, laundry, luggage storage, currency exchange and a multilingual staff are always ready to assist.")),

                                heading(ContentSection.STORY, of("Vị trí & biển Nha Trang", "Location & Nha Trang beach")),

                                paragraph(ContentSection.STORY,
                                                of("Nằm trên đường Trần Quang Khải sầm uất, khách sạn chỉ cách bãi biển Nha Trang khoảng vài trăm mét, thuận tiện cho việc tắm biển, dạo bộ và thưởng thức hải sản. Khu vực xung quanh tập trung nhiều nhà hàng, quán bar, quán cà phê và cửa hàng lưu niệm sôi động cả ngày lẫn đêm.", "Located on the bustling Tran Quang Khai street, the hotel is only a few hundred meters from Nha Trang Beach, convenient for swimming, strolling and enjoying seafood. The surrounding area is full of restaurants, bars, cafés and souvenir shops, lively day and night.")),

                                heading(ContentSection.STORY, of("Di chuyển & giao thông", "Getting around & transport")),

                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu; Sân bay quốc tế Cam Ranh cách trung tâm khoảng 30–35 km. Du khách cũng dễ dàng thuê xe máy, xe đạp hoặc xe hơi để khám phá các điểm tham quan trong và ngoài thành phố.", "The hotel offers airport transfer on request; Cam Ranh International Airport is about 30–35 km from the center. Guests can also easily rent motorbikes, bicycles or cars to explore attractions in and around the city.")),

                                heading(ContentSection.STORY, of("Điểm tham quan lân cận", "Nearby attractions")),

                                paragraph(ContentSection.STORY,
                                                of("Từ Aaron Hotel, du khách dễ dàng ghé thăm Tháp Bà Ponagar, Chợ Đầm, Nhà thờ Núi, Hòn Chồng, Viện Hải dương học hay vui chơi tại VinWonders Nha Trang. Các điểm đến nổi tiếng như Bãi Dài, Hòn Tằm và suối khoáng nóng I-resort cũng nằm trong tầm di chuyển thuận tiện.", "From Aaron Hotel, guests can easily visit Po Nagar Towers, Dam Market, Nha Trang Cathedral, Hon Chong Promontory, the National Oceanographic Museum or enjoy VinWonders Nha Trang. Popular destinations such as Bai Dai Beach, Hon Tam Island and I-Resort hot spring are also within convenient reach.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn Aaron Hotel", "Why choose Aaron Hotel")),

                                paragraph(ContentSection.STORY,
                                                of("Với vị trí trung tâm sát biển, đầy đủ tiện nghi 4 sao, hồ bơi, nhà hàng, spa và dịch vụ tận tâm, Aaron Hotel là lựa chọn cân bằng giữa sự thuận tiện và chất lượng cho kỳ nghỉ tại Nha Trang. Mức giá hợp lý cùng đội ngũ thân thiện giúp du khách có trải nghiệm đáng nhớ.", "With a central beachfront location, full 4-star amenities, a pool, restaurant, spa and attentive service, Aaron Hotel offers a balance of convenience and quality for a Nha Trang holiday. Reasonable rates and a friendly team ensure a memorable stay.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi ngoài trời", "Outdoor pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("spa", of("Dịch vụ spa", "Spa"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, false),
                amenity("massage", of("Dịch vụ massage", "Massage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("babysitting", of("Dịch vụ giữ trẻ (thu phí)", "Babysitting (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-aaron-superior-double")
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
                        .totalRooms(20)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-b3baa52324d6bede9b757895c1d6faab.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-9d807696b9752d2fdc273fe43f26dac5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-aaron-superior-double-prepaid-nonref")
                                        .name(of("Superior Double - Không gồm bữa sáng", "Superior Double - Room only"))
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
                                        .id("rt-aaron-superior-double-breakfast-flex")
                                        .name(of("Superior Double - Bao gồm bữa sáng", "Superior Double - Breakfast included"))
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
                        .id("rt-aaron-superior-twin")
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
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-061947cac6242696e975b65df8916811.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-eb61a5478397ce4bec81ee9a399f8772.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-aaron-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("720000"))
                                        .referencePricePerNight(new BigDecimal("1028571"))
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
                                        .id("rt-aaron-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("828000"))
                                        .referencePricePerNight(new BigDecimal("1182857"))
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
                        .id("rt-aaron-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 30 m² hướng biển, 1 giường Queen.", "Deluxe room 30 sqm with sea view, 1 queen bed."))
                        .description(of("Phòng Deluxe 30 m² hướng biển, 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 30 sqm with sea view, 1 queen bed. Fully equipped for a comfortable stay."))
                        .areaSqm(30.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(18)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-6e2a04bb25acf4fa1ae139a84aeae086.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-16a7a2fee261e551fa96c9b9fd11ded5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-aaron-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("980000"))
                                        .referencePricePerNight(new BigDecimal("1400000"))
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
                                        .id("rt-aaron-deluxe-sea-view-breakfast-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1127000"))
                                        .referencePricePerNight(new BigDecimal("1610000"))
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
                        .id("rt-aaron-deluxe-city-view")
                        .name(of("Deluxe City View", "Deluxe City View"))
                        .shortDescription(of("Phòng Deluxe 28 m² hướng thành phố, 1 giường King.", "Deluxe room 28 sqm with city view, 1 king bed."))
                        .description(of("Phòng Deluxe 28 m² hướng thành phố, 1 giường King. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 28 sqm with city view, 1 king bed. Fully equipped for a comfortable stay."))
                        .areaSqm(28.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-cc4a9614f5a37067733bcc0af20f52c9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-1e7fbbd7320c205504d21564f2569c3d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN",
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-aaron-deluxe-city-view-prepaid-nonref")
                                        .name(of("Deluxe City View - Không gồm bữa sáng", "Deluxe City View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("920000"))
                                        .referencePricePerNight(new BigDecimal("1314286"))
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
                                        .id("rt-aaron-deluxe-city-view-breakfast-flex")
                                        .name(of("Deluxe City View - Bao gồm bữa sáng", "Deluxe City View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1058000"))
                                        .referencePricePerNight(new BigDecimal("1511429"))
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
                        .id("rt-aaron-family")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Family 42 m², 1 giường King + 1 giường đơn, cho 4 khách.", "Family room 42 sqm, 1 king + 1 single bed, for 4 guests."))
                        .description(of("Phòng Family 42 m², 1 giường King + 1 giường đơn, cho 4 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family room 42 sqm, 1 king + 1 single bed, for 4 guests. Fully equipped for a comfortable stay."))
                        .areaSqm(42.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-86added577b73000258498f0905325b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-ae0b84968eb2932347f3ec93120f1752.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20003582-19575c9d40174541e67a26a2485f9de2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                "FAN",
                                "BALCONY",
                                "SEATING_AREA",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-aaron-family-prepaid-nonref")
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
                                        .id("rt-aaron-family-breakfast-flex")
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
                nearby("bai-bien-nha-trang", of("Bãi biển Nha Trang", "Nha Trang Beach"), of("Bãi biển", "Beach"), 400.0),
                nearby("thap-ba-ponagar", of("Tháp Bà Ponagar", "Po Nagar Towers"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("cho-dam", of("Chợ Đầm", "Dam Market"), of("Địa điểm lân cận", "Nearby places"), 2200.0),
                nearby("nha-tho-nui-nha-trang", of("Nhà thờ Núi (Nhà thờ Chánh tòa)", "Nha Trang Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("hon-chong", of("Hòn Chồng", "Hon Chong Promontory"), of("Địa điểm lân cận", "Nearby places"), 4200.0),
                nearby("vinwonders-nha-trang", of("VinWonders Nha Trang", "VinWonders Nha Trang"), of("Trung tâm giải trí", "Entertainment hub"), 14000.0),
                nearby("vien-hai-duong-hoc", of("Viện Hải dương học", "National Oceanographic Museum"), of("Địa điểm lân cận", "Nearby places"), 5500.0),
                nearby("chua-long-son", of("Chùa Long Sơn", "Long Son Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("thap-tram-huong", of("Tháp Trầm Hương", "Tram Huong Tower"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("cong-vien-phu-dong", of("Công viên Phù Đổng", "Phu Dong Park"), of("Địa điểm lân cận", "Nearby places"), 800.0),
                nearby("hon-tam", of("Hòn Tằm", "Hon Tam Island"), of("Bãi biển", "Beach"), 9000.0),
                nearby("bai-dai-nha-trang", of("Bãi Dài", "Bai Dai Beach"), of("Bãi biển", "Beach"), 28000.0),
                nearby("i-resort-suoi-khoang-nong", of("I-resort suối khoáng nóng", "I-Resort Hot Spring"), of("Trung tâm giải trí", "Entertainment hub"), 6000.0),
                nearby("quang-truong-2-thang-4", of("Quảng trường 2 Tháng 4", "April 2 Square"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
                nearby("san-bay-cam-ranh", of("Sân bay quốc tế Cam Ranh", "Cam Ranh International Airport"), of("Trung tâm giao thông", "Transport hub"), 33000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Hồ bơi, nhà hàng, spa, máy lạnh, lễ tân 24h, WiFi miễn phí", "Pool, restaurant, spa, air conditioning, 24-hour front desk, free WiFi"))
                                .distanceToCityCenterKm(0.8)
                                .popularAreaSummary(of("Bãi biển Nha Trang, Chợ Đầm, Tháp Trầm Hương", "Nha Trang Beach, Dam Market, Tram Huong Tower"))
                                .totalRooms(70)
                                .totalFloors(21)
                                .otherHighlightFacilities(of("Hồ bơi, spa, massage, trung tâm thể dục, đưa đón sân bay, cho thuê xe.", "Pool, spa, massage, fitness center, airport transfer, vehicle rental."))
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
                                                                .content(of("Khách sạn thân thiện với gia đình: có phòng gia đình và dịch vụ giữ trẻ (thu phí).", "The hotel is family-friendly with family rooms and babysitting (surcharge)."))
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
                faq(of("Những tiện ích tại Aaron Hotel?", "What amenities does Aaron Hotel offer?"),
                        of("Các tiện ích tại Aaron Hotel bao gồm máy lạnh, nhà hàng, hồ bơi, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities at Aaron Hotel include air conditioning, restaurant, swimming pool, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Aaron Hotel?", "What are the check-in and check-out times at Aaron Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Aaron Hotel có phục vụ ăn sáng không?", "Does Aaron Hotel serve breakfast?"),
                        of("Có, Aaron Hotel có dịch vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, Aaron Hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Aaron Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Aaron Hotel have?"),
                        of("Aaron Hotel là khách sạn đạt tiêu chuẩn 4 sao.", "Aaron Hotel is a 4-star hotel.")),
                faq(of("Aaron Hotel có bao nhiêu tầng lầu?", "How many floors does Aaron Hotel have?"),
                        of("Aaron Hotel có tổng cộng 21 tầng.", "Aaron Hotel has a total of 21 floors.")),
                faq(of("Tổng số phòng hiện có tại Aaron Hotel là bao nhiêu?", "How many rooms does Aaron Hotel have?"),
                        of("Tổng số phòng tại Aaron Hotel là 70 phòng.", "Aaron Hotel has a total of 70 rooms.")),
                faq(of("Aaron Hotel có hồ bơi không?", "Does Aaron Hotel have a pool?"),
                        of("Có, Aaron Hotel có hồ bơi phục vụ khách lưu trú.", "Yes, Aaron Hotel has a swimming pool for guests.")),
                faq(of("Aaron Hotel có hỗ trợ nhận phòng sớm không?", "Does Aaron Hotel support early check-in?"),
                        of("Có, Aaron Hotel hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, Aaron Hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Aaron Hotel có cung cấp dịch vụ trả phòng trễ không?", "Does Aaron Hotel offer late check-out?"),
                        of("Có, Aaron Hotel hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, Aaron Hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Aaron Hotel có dịch vụ đưa đón sân bay không?", "Does Aaron Hotel offer airport transfer?"),
                        of("Có, Aaron Hotel cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Sân bay quốc tế Cam Ranh cách khoảng 30–35 km.", "Yes, Aaron Hotel offers airport transfer on request (surcharge). Cam Ranh International Airport is about 30–35 km away.")),
                faq(of("Aaron Hotel có gần bãi biển không?", "Is Aaron Hotel close to the beach?"),
                        of("Có, khách sạn nằm trên đường Trần Quang Khải, chỉ cách bãi biển Nha Trang vài trăm mét, rất thuận tiện để tắm biển và dạo bộ.", "Yes, the hotel is on Tran Quang Khai street, only a few hundred meters from Nha Trang Beach, very convenient for swimming and strolling.")),
                faq(of("Aaron Hotel có chỗ đậu xe không?", "Does Aaron Hotel have parking?"),
                        of("Có, khách sạn có khu vực đậu xe cho khách lưu trú (số lượng có hạn).", "Yes, the hotel has a parking area for guests (limited availability).")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("nha-trang")
                                .cityName(of("Nha Trang", "Nha Trang"))
                                .districtName(of("Nha Trang", "Nha Trang"))
                                .wardName(of("Lộc Thọ", "Loc Tho"))
                                .addressLine(of("6/6 Trần Quang Khải, Lộc Thọ, Nha Trang, Khánh Hòa, Việt Nam, 652153", "6/6 Tran Quang Khai, Loc Tho ward, Nha Trang, Khanh Hoa Province, Vietnam, 652153"))
                                .location(new double[] { 109.19661180000003, 12.2334616 })
                                .name(of("Aaron Hotel", "Aaron Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao trung tâm Nha Trang, sát biển, có hồ bơi và nhà hàng.", "4-star hotel in central Nha Trang, near the beach, with a pool and restaurant."))
                                .description(of("Aaron Hotel là khách sạn 4 sao tại trung tâm Nha Trang, cách bãi biển vài bước chân, với 70 phòng nghỉ tiện nghi, hồ bơi ngoài trời, nhà hàng, spa và dịch vụ tận tâm.", "Aaron Hotel is a 4-star hotel in central Nha Trang, just steps from the beach, with 70 comfortable rooms, an outdoor pool, a restaurant, a spa and attentive service."))
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
