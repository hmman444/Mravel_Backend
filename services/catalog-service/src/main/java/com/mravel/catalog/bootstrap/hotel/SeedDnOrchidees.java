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
public class SeedDnOrchidees {

        @Bean
        CommandLineRunner seedDnOrchideesRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedDnOrchidees] start");
                        HotelDoc doc = hotelOrchidees();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedDnOrchidees] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedDnOrchidees] done");
                };
        }

        // =====================================================================
        // ORCHIDE'ES OCEAN HOTEL (source: Traveloka hotelId 3000020011694; rooms fabricated)
        // =====================================================================
        private static HotelDoc hotelOrchidees() {
                String slug = "orchide-es-ocean-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-4c950f9b429acbf55d3751e720e401db.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-6482025b6e5855ae41e99d9eb42bfa13.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-ff190759d3d74f55d86e287c1cb7c70e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-f03e3c5c44970f1fda4098fc7f3b981c.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-79e8277519bea7e9dfd51a2bc57f53c3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-3e95951ce43d658c9f262e91ea8859f0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-d90b03afe2145b52a1d63acf58008d4d.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-52b071c88943fb91fdbf72f15de34503.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-88ffb258023e314474b4270429d62330.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-830da4382ea516c9c15259c061ed56f6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-bb04977d8493798efb6bd19109bbb8a8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-8fca2543d3250898c7eedc511aedafb7.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-ffb6ac384f9ef089b98e498b76088595.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-d424efe4aa3f61084f7b0335f70cac6c.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-ba0147b90da3508e5d973d0122adc1cc.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-7bb337ba6b2b9d36a8beb679df562d52.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-67ceb65281e6982e1725c82fd6d57f0a.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-969e07f46577478aa621b1b0d68ee5df.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-b00b5a59e6ffd22c11dedc9be02c3ddc.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-9e220db2b0ba0dc47b87b28177194030.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-5d41ad70e3acfa5163c15f96d698dd5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-c28805ab9e1148a5b991d95178d66c57.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-a309ae8e8142c05df8a318426c73a0b5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-b73d5da0f13400bdcdb9584e2e4bbc7f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-1b3402b7e4cc32cc0d2131e5b41d6601.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Hồ bơi", "Swimming pool", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-84548d56e474656350c8a7f874d0ff79.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-206281dd563fe87af172ba8d4fe04017.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-5096ac357500e4e09bbc89c5517677cb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-25d197b96c847d0cf4485993be1e5488.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-6d9b30cb46ebed7aa29719f900df28ca.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 29));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.24424557387829, 16.068819500272898 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về Orchide'es Ocean Hotel", "About Orchide'es Ocean Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("Orchide'es Ocean Hotel là khách sạn 3 sao tọa lạc tại số 9 Dương Đình Nghệ, phường Phước Mỹ, quận Sơn Trà, Đà Nẵng. Chỉ cách bãi biển Mỹ Khê vài bước chân, khách sạn 5 tầng với 50 phòng mang đến không gian nghỉ ngơi gọn gàng, hiện đại cùng mức giá hợp lý, lý tưởng cho cả khách du lịch lẫn khách công tác.", "Orchide'es Ocean Hotel is a 3-star hotel located at 9 Duong Dinh Nghe Street, Phuoc My Ward, Son Tra District, Da Nang. Just steps from My Khe Beach, this 5-storey hotel with 50 rooms offers a tidy, modern stay at a reasonable price, ideal for both leisure and business travelers.")),
                                heading(ContentSection.STORY, of("Các hạng phòng & tiện nghi", "Room types & amenities")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình và phòng hướng biển. Mỗi phòng đều được trang bị máy lạnh, minibar, két an toàn, bàn làm việc, phòng tắm vòi sen với đồ dùng cá nhân, nước uống đóng chai và Wi-Fi miễn phí, đảm bảo sự tiện nghi cho mọi nhu cầu lưu trú.", "The hotel offers a range of room categories from Standard, Superior and Deluxe to Family and sea-view rooms. Every room is equipped with air conditioning, a minibar, a safe, a work desk, a shower bathroom with toiletries, bottled water and free Wi-Fi, ensuring comfort for every type of stay.")),
                                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng của khách sạn phục vụ bữa sáng mỗi ngày cùng thực đơn món Việt và món Âu cho bữa trưa, bữa tối. Quầy bar là nơi lý tưởng để thư giãn với đồ uống sau một ngày khám phá thành phố biển, mang đến trải nghiệm ẩm thực gần gũi và ấm cúng.", "The hotel's restaurant serves daily breakfast along with Vietnamese and European menus for lunch and dinner. The bar is an ideal spot to unwind with a drink after a day exploring the coastal city, offering a friendly and cozy dining experience.")),
                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                                paragraph(ContentSection.STORY,
                                                of("Với lễ tân 24 giờ, dịch vụ giặt ủi, giữ hành lý, thang máy, két an toàn và bảo vệ 24/24, Orchide'es Ocean Hotel đáp ứng trọn vẹn nhu cầu của du khách. Khách sạn còn hỗ trợ đưa đón sân bay, cho thuê xe và đặt tour để chuyến đi thêm thuận tiện.", "With a 24-hour front desk, laundry service, luggage storage, an elevator, a safe and 24/7 security, Orchide'es Ocean Hotel fully meets travelers' needs. The hotel also assists with airport transfers, car hire and tour booking to make trips more convenient.")),
                                heading(ContentSection.STORY, of("Vị trí & biển Mỹ Khê", "Location & My Khe Beach")),
                                paragraph(ContentSection.STORY,
                                                of("Nằm tại trung tâm quận Sơn Trà, khách sạn chỉ cách bãi biển Mỹ Khê – một trong những bãi biển đẹp nhất hành tinh – khoảng vài trăm mét. Du khách dễ dàng tản bộ ra biển tắm sớm, ngắm bình minh hoặc thưởng thức hải sản tươi ngon dọc bờ biển.", "Located in the heart of Son Tra District, the hotel is only a few hundred meters from My Khe Beach – one of the most beautiful beaches on the planet. Guests can easily stroll to the beach for an early swim, watch the sunrise or enjoy fresh seafood along the shore.")),
                                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cách Sân bay quốc tế Đà Nẵng khoảng 6 km, thuận tiện đưa đón. Các điểm tham quan nổi tiếng như Cầu Rồng, Cầu sông Hàn, Ngũ Hành Sơn và bán đảo Sơn Trà đều nằm trong bán kính di chuyển ngắn bằng taxi hoặc xe máy.", "The hotel is about 6 km from Da Nang International Airport, making transfers easy. Famous attractions such as the Dragon Bridge, Han River Bridge, Marble Mountains and Son Tra Peninsula are all within a short ride by taxi or motorbike.")),
                                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),
                                paragraph(ContentSection.STORY,
                                                of("Khu vực Phước Mỹ – Sơn Trà sôi động với nhiều nhà hàng hải sản, quán cà phê, cửa hàng tiện lợi và chợ địa phương. Du khách có thể dễ dàng tìm thấy ATM, siêu thị và các dịch vụ thiết yếu chỉ trong vài phút đi bộ từ khách sạn.", "The Phuoc My – Son Tra area is lively with many seafood restaurants, coffee shops, convenience stores and local markets. Guests can easily find ATMs, supermarkets and essential services within a few minutes' walk from the hotel.")),
                                heading(ContentSection.STORY, of("Vì sao nên chọn Orchide'es Ocean Hotel", "Why choose Orchide'es Ocean Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("Chọn Orchide'es Ocean Hotel, du khách có được vị trí sát biển Mỹ Khê, phòng ốc sạch sẽ tiện nghi, dịch vụ thân thiện và mức giá phải chăng cho khách sạn 3 sao. Đây là lựa chọn cân bằng hoàn hảo giữa chi phí và trải nghiệm cho kỳ nghỉ biển tại Đà Nẵng.", "Choosing Orchide'es Ocean Hotel gives travelers a location right by My Khe Beach, clean and comfortable rooms, friendly service and an affordable price for a 3-star hotel. It is a perfect balance of cost and experience for a beach holiday in Da Nang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM", "ATM"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-orchidees-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 18 sqm, 1 double bed, with all basic amenities."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 18 sqm, 1 double bed, with all basic amenities."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(15)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-6482025b6e5855ae41e99d9eb42bfa13.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-79e8277519bea7e9dfd51a2bc57f53c3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-orchidees-standard-double-prepaid-nonref")
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
                                        .id("rt-orchidees-standard-double-breakfast-flex")
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
                        .id("rt-orchidees-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 22 m², 2 giường đơn, không gian thoáng đãng.", "Superior room 22 sqm, 2 single beds, with an airy space."))
                        .description(of("Phòng Superior 22 m², 2 giường đơn, không gian thoáng đãng.", "Superior room 22 sqm, 2 single beds, with an airy space."))
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-3e95951ce43d658c9f262e91ea8859f0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-d90b03afe2145b52a1d63acf58008d4d.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SAFE"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-orchidees-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
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
                                        .id("rt-orchidees-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
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
                        .id("rt-orchidees-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen, có ban công.", "Deluxe room 26 sqm, 1 queen bed, with a balcony."))
                        .description(of("Phòng Deluxe 26 m², 1 giường Queen, có ban công.", "Deluxe room 26 sqm, 1 queen bed, with a balcony."))
                        .areaSqm(26.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-52b071c88943fb91fdbf72f15de34503.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-88ffb258023e314474b4270429d62330.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-orchidees-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1050000"))
                                        .referencePricePerNight(new BigDecimal("1500000"))
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
                                        .id("rt-orchidees-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1207500"))
                                        .referencePricePerNight(new BigDecimal("1725000"))
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
                        .id("rt-orchidees-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 28 m² hướng biển, 1 giường King, ban công riêng.", "Deluxe room 28 sqm with sea view, 1 king bed, private balcony."))
                        .description(of("Phòng Deluxe 28 m² hướng biển, 1 giường King, ban công riêng.", "Deluxe room 28 sqm with sea view, 1 king bed, private balcony."))
                        .areaSqm(28.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20011694-830da4382ea516c9c15259c061ed56f6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-8fca2543d3250898c7eedc511aedafb7.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-orchidees-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
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
                                        .id("rt-orchidees-deluxe-sea-view-breakfast-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
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
                        .id("rt-orchidees-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 34 m², 1 giường Queen + 1 giường đơn, cho 3 khách.", "Family room 34 sqm, 1 queen + 1 single bed, for 3 guests."))
                        .description(of("Phòng Gia đình 34 m², 1 giường Queen + 1 giường đơn, cho 3 khách.", "Family room 34 sqm, 1 queen + 1 single bed, for 3 guests."))
                        .areaSqm(34.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(0)
                        .maxGuests(3)
                        .totalRooms(5)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-67ceb65281e6982e1725c82fd6d57f0a.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011694-969e07f46577478aa621b1b0d68ee5df.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-orchidees-family-room-prepaid-nonref")
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
                                        .id("rt-orchidees-family-room-breakfast-flex")
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
                nearby("bai-bien-my-khe", of("Bãi biển Mỹ Khê", "My Khe Beach"), of("Bãi biển", "Beach"), 400.0),
                nearby("bai-bien-my-an", of("Bãi biển Mỹ An", "My An Beach"), of("Bãi biển", "Beach"), 1500.0),
                nearby("ban-dao-son-tra", of("Bán đảo Sơn Trà", "Son Tra Peninsula"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("chua-linh-ung", of("Chùa Linh Ứng", "Linh Ung Pagoda"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("dinh-ban-co", of("Đỉnh Bàn Cờ", "Ban Co Peak"), of("Địa điểm lân cận", "Nearby places"), 12000.0),
                nearby("cau-rong", of("Cầu Rồng", "Dragon Bridge"), of("Khác", "Other"), 3500.0),
                nearby("cau-tinh-yeu", of("Cầu Tình Yêu", "Love Bridge"), of("Khác", "Other"), 3200.0),
                nearby("cau-song-han", of("Cầu sông Hàn", "Han River Bridge"), of("Khác", "Other"), 4000.0),
                nearby("cho-han", of("Chợ Hàn", "Han Market"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("cho-con", of("Chợ Cồn", "Con Market"), of("Địa điểm lân cận", "Nearby places"), 5500.0),
                nearby("bao-tang-dieu-khac-cham", of("Bảo tàng Điêu khắc Chăm", "Cham Sculpture Museum"), of("Khác", "Other"), 4200.0),
                nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Địa điểm lân cận", "Nearby places"), 7000.0),
                nearby("cong-vien-chau-a", of("Công viên Châu Á (Asia Park)", "Sun World Asia Park"), of("Trung tâm giải trí", "Entertainment hub"), 6500.0),
                nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 6000.0),
                nearby("ba-na-hills", of("Khu du lịch Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 30000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Gần biển Mỹ Khê, nhà hàng, quầy bar, máy lạnh, WiFi miễn phí", "Near My Khe Beach, restaurant, bar, air conditioning, free WiFi"))
                                .distanceToCityCenterKm(4.0)
                                .popularAreaSummary(of("Bãi biển Mỹ Khê, Bán đảo Sơn Trà, Cầu Rồng", "My Khe Beach, Son Tra Peninsula, Dragon Bridge"))
                                .totalRooms(50)
                                .totalFloors(5)
                                .otherHighlightFacilities(of("Lễ tân 24h, giặt ủi, giữ hành lý, đưa đón sân bay, cho thuê xe.", "24-hour front desk, laundry, luggage storage, airport transfer, car hire."))
                                .interestingPlacesSummary(of("Bãi biển Mỹ Khê, Ngũ Hành Sơn, Bán đảo Sơn Trà", "My Khe Beach, Marble Mountains, Son Tra Peninsula"))
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
                                                                .title(of("Trẻ em & gia đình", "Children & family"))
                                                                .content(of("Khách sạn có phòng Gia đình phù hợp cho gia đình có trẻ nhỏ.", "The hotel offers Family rooms suitable for families with children."))
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
                faq(of("Những tiện ích tại Orchide'es Ocean Hotel?", "What amenities does Orchide'es Ocean Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, quầy bar, lễ tân 24h, thang máy, bãi đậu xe, két an toàn và WiFi miễn phí (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, bar, 24-hour front desk, elevator, parking, safe and free WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Orchide'es Ocean Hotel?", "What are the check-in and check-out times at Orchide'es Ocean Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Orchide'es Ocean Hotel có phục vụ ăn sáng không?", "Does Orchide'es Ocean Hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Orchide'es Ocean Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Orchide'es Ocean Hotel have?"),
                        of("Orchide'es Ocean Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Orchide'es Ocean Hotel is a 3-star hotel.")),
                faq(of("Orchide'es Ocean Hotel có bao nhiêu tầng lầu?", "How many floors does Orchide'es Ocean Hotel have?"),
                        of("Orchide'es Ocean Hotel có tổng cộng 5 tầng.", "Orchide'es Ocean Hotel has a total of 5 floors.")),
                faq(of("Tổng số phòng hiện có tại Orchide'es Ocean Hotel là bao nhiêu?", "How many rooms does Orchide'es Ocean Hotel have?"),
                        of("Tổng số phòng tại Orchide'es Ocean Hotel là 50 phòng.", "Orchide'es Ocean Hotel has a total of 50 rooms.")),
                faq(of("Orchide'es Ocean Hotel có mức giá là bao nhiêu?", "How much does Orchide'es Ocean Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có gần biển không?", "Is the hotel close to the beach?"),
                        of("Có, khách sạn nằm tại quận Sơn Trà, chỉ cách bãi biển Mỹ Khê khoảng vài trăm mét, rất thuận tiện để đi tắm biển.", "Yes, the hotel is in Son Tra District, only a few hundred meters from My Khe Beach, very convenient for going to the beach.")),
                faq(of("Khách sạn có hỗ trợ đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí); Sân bay quốc tế Đà Nẵng cách khoảng 6 km.", "Yes, the hotel offers airport transfer (surcharge); Da Nang International Airport is about 6 km away.")),
                faq(of("Khách sạn có chỗ đậu xe không?", "Does the hotel have parking?"),
                        of("Có, khách sạn có bãi đậu xe cho khách lưu trú.", "Yes, the hotel has parking for staying guests.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("da-nang")
                                .cityName(of("Đà Nẵng", "Da Nang"))
                                .districtName(of("Sơn Trà", "Son Tra"))
                                .wardName(of("Phước Mỹ", "Phuoc My"))
                                .addressLine(of("9 Dương Đình Nghệ, Phước Mỹ, Sơn Trà, Đà Nẵng, Việt Nam, 550000", "9 Duong Dinh Nghe, Phuoc My Ward, Son Tra District, Da Nang, Vietnam, 550000"))
                                .location(new double[] { 108.24424557387829, 16.068819500272898 })
                                .name(of("Orchide'es Ocean Hotel", "Orchide'es Ocean Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao gần biển Mỹ Khê, quận Sơn Trà, Đà Nẵng.", "3-star hotel near My Khe Beach, Son Tra District, Da Nang."))
                                .description(of("Orchide'es Ocean Hotel là khách sạn 3 sao tại quận Sơn Trà, chỉ cách bãi biển Mỹ Khê vài bước chân, với 50 phòng tiện nghi, nhà hàng, quầy bar và dịch vụ thân thiện ở mức giá hợp lý.", "Orchide'es Ocean Hotel is a 3-star hotel in Son Tra District, just steps from My Khe Beach, offering 50 comfortable rooms, a restaurant, a bar and friendly service at a reasonable price."))
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
