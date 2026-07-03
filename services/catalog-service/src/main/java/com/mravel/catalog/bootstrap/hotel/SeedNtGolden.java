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
public class SeedNtGolden {

        @Bean
        CommandLineRunner seedNtGoldenRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedNtGolden] start");
                        HotelDoc doc = hotelNtGolden();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedNtGolden] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedNtGolden] done");
                };
        }

        // =====================================================================
        // GOLDEN HOTEL NHA TRANG
        // (source: Traveloka hotelId 20000183; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelNtGolden() {
                String slug = "golden-hotel-nha-trang";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-8c951a9368be49662c50a6c22517ed7d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-d1a596bd90604ab36b9f59b709154107.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-6c9a0b75d474f638d9bfef127dc268ff.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-e3458ebb773a1f51f229eaa8d107ca2e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-aac40ef778a0df9ff2344af7ea1a276c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-c507a7df43501c05609463a531cdd440.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-b9b400d848e74c0df951afaf7a550454.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-7c0f72942623afeb7e86716c1ea5e4be.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-26d13d339f9f2f2e9fbb727a5c4cd047.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-6e96dd487e1186aac72dccda7c50e82b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-215d80fea89333aa9cbd1f975fb33c78.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-e603578b60236d0af07b7eb4ebcfc747.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-080c72fa037d5f18ca64e3455dd30939.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-cab4863edcc92c4093c6470a7939711f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-f1c423967241e9b7ab483962a0fa0270.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-33d75132054ef66d20d73d2a1189c2e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-74453e523272a768a948f23cc8da1d26.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-12ae21dd63e05dc77a155a1bb7981e29.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-0ec20fd967683f944f6123f152befbc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-bfe757e55822e6adc9c4efef4bf4de81.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-0b91cf47ac71b6478789f5063d653271.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-1d12d2659336217b9878864edd4a41b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-1839520fd8560f34af86a61b7f91324f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-4d22529ec3281ab1a32728060067a16e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-2b87167cb536ca8dea94ddc67fd7e37a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-29801d8116633aa1eb8fe967128fc1d1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-e3b79c665a0d25390996fc44e6249f74.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20000183-992d9b0b2734b3def46ee44eb53db701.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 27));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 109.1955308, 12.23513 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Golden Hotel Nha Trang", "About Golden Hotel Nha Trang")),

                paragraph(ContentSection.STORY,
                                of("Golden Hotel Nha Trang là khách sạn 3 sao toạ lạc tại 136 Hùng Vương, phường Lộc Thọ – khu trung tâm sầm uất bậc nhất của thành phố biển Nha Trang. Với 42 phòng trải trên 14 tầng, khách sạn mang đến chỗ nghỉ tiện nghi, sạch sẽ, chỉ cách bãi biển Nha Trang vài phút đi bộ – lựa chọn lý tưởng cho cả du khách nghỉ dưỡng lẫn khách công tác.", "Golden Hotel Nha Trang is a 3-star hotel at 136 Hung Vuong, Loc Tho Ward – the bustling heart of the seaside city of Nha Trang. With 42 rooms across 14 floors, the hotel offers comfortable, clean accommodation just a few minutes' walk from Nha Trang Beach – an ideal choice for both leisure travelers and business guests.")),

                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior đến Deluxe và phòng Gia đình, một số phòng có view thành phố hoặc hướng biển. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, két an toàn, phòng tắm vòi sen và Wi-Fi miễn phí.", "The hotel offers a range of room categories from Standard, Superior to Deluxe and Family rooms, with some rooms enjoying city or sea views. Each room is equipped with air conditioning, a minibar, a work desk, a hair dryer, a safe, a shower and free Wi-Fi.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng buột và thực đơn Á – Âu phong phú, cùng tiệm cà phê ấm cúng cho những giây phút thư giãn. Du khách cũng dễ dàng khám phá ẩm thực đường phố Nha Trang và các quán hải sản nổi tiếng ngay gần khách sạn.", "The hotel restaurant serves a buffet breakfast and a rich Asian-European menu, along with a cozy coffee shop for relaxing moments. Guests can also easily explore Nha Trang street food and the famous seafood eateries right near the hotel.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Golden Hotel Nha Trang phục vụ lễ tân 24 giờ, dịch vụ phòng, giặt ủi, giữ hành lý, thu đổi ngoại tệ, đưa đón sân bay (thu phí) và hỗ trợ đặt tour. Đội ngũ nhân viên đa ngôn ngữ thân thiện sẵn sàng hỗ trợ du khách trong suốt kỳ nghỉ.", "Golden Hotel Nha Trang provides a 24-hour front desk, room service, laundry, luggage storage, currency exchange, airport transfer (surcharge) and tour booking assistance. The friendly multilingual staff are ready to assist guests throughout their stay.")),

                heading(ContentSection.STORY, of("Vị trí & bãi biển", "Location & beach")),

                paragraph(ContentSection.STORY,
                                of("Nằm trên trục đường Hùng Vương sôi động, khách sạn chỉ cách bãi biển Nha Trang khoảng vài trăm mét, thuận tiện cho việc tắm biển, dạo bộ ven biển và tận hưởng không khí biển. Quảng trường 2 Tháng 4, Tháp Trầm Hương và các trung tâm mua sắm đều trong tầm đi bộ.", "Set on the lively Hung Vuong street, the hotel is only a few hundred meters from Nha Trang Beach, making swimming, seaside strolls and enjoying the sea breeze convenient. April 2 Square, Tram Huong Tower and shopping centers are all within walking distance.")),

                heading(ContentSection.STORY, of("Điểm tham quan lân cận", "Nearby attractions")),

                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách dễ dàng ghé thăm Tháp Bà Ponagar, Chợ Đầm, Nhà thờ Núi, chùa Long Sơn, Hòn Chồng và Viện Hải dương học. Các điểm vui chơi nổi tiếng như VinWonders Nha Trang và Hòn Tằm cũng nằm trong tầm di chuyển thuận tiện.", "From the hotel, guests can easily visit Po Nagar Towers, Dam Market, Nha Trang Cathedral, Long Son Pagoda, Hon Chong Promontory and the National Oceanographic Museum. Famous attractions such as VinWonders Nha Trang and Hon Tam Island are also within convenient reach.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                paragraph(ContentSection.STORY,
                                of("Sân bay quốc tế Cam Ranh cách khách sạn khoảng 30–35 km (khoảng 40–45 phút lái xe). Khách sạn hỗ trợ đặt xe đưa đón sân bay, thuê xe máy và tư vấn lộ trình tham quan thuận tiện cho du khách.", "Cam Ranh International Airport is about 30–35 km from the hotel (roughly a 40–45 minute drive). The hotel can arrange airport transfers, motorbike rentals and convenient itinerary advice for guests.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Golden Hotel Nha Trang", "Why choose Golden Hotel Nha Trang")),

                paragraph(ContentSection.STORY,
                                of("Với vị trí trung tâm sát biển, giá phòng hợp lý, dịch vụ chu đáo và đầy đủ tiện nghi, Golden Hotel Nha Trang là lựa chọn đáng giá cho kỳ nghỉ biển tiết kiệm mà vẫn thoải mái ngay giữa lòng thành phố Nha Trang.", "With a central beachfront location, reasonable rates, attentive service and full amenities, Golden Hotel Nha Trang is a worthwhile choice for a budget-friendly yet comfortable beach holiday right in the heart of Nha Trang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe (thu phí)", "Parking (surcharge)"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ phòng (có giới hạn thời gian)", "Room service (limited hours)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-ntgolden-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 20 m², 1 giường đôi, tiện nghi cơ bản.", "Standard room 20 sqm, 1 double bed, basic amenities."))
                        .description(of("Phòng Standard 20 m², 1 giường đôi, tiện nghi cơ bản. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Standard room 20 sqm, 1 double bed, basic amenities. Fully equipped with amenities for a comfortable stay."))
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-215d80fea89333aa9cbd1f975fb33c78.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-080c72fa037d5f18ca64e3455dd30939.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntgolden-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Kh\u00f4ng \u0111\u01b0\u1ee3c ho\u00e0n ti\u1ec1n.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("650000"))
                                        .referencePricePerNight(new BigDecimal("928571"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gi\u00e1 \u01b0u \u0111\u00e3i", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntgolden-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("\u00c1p d\u1ee5ng ch\u00ednh s\u00e1ch h\u1ee7y ph\u00f2ng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("747500"))
                                        .referencePricePerNight(new BigDecimal("1067857"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("G\u1ed3m b\u1eefa s\u00e1ng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntgolden-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 24 m², 2 giường đơn, view thành phố.", "Superior room 24 sqm, 2 single beds, city view."))
                        .description(of("Phòng Superior 24 m², 2 giường đơn, view thành phố. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior room 24 sqm, 2 single beds, city view. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(24.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-cab4863edcc92c4093c6470a7939711f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-f1c423967241e9b7ab483962a0fa0270.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntgolden-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Kh\u00f4ng \u0111\u01b0\u1ee3c ho\u00e0n ti\u1ec1n.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("780000"))
                                        .referencePricePerNight(new BigDecimal("1114286"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gi\u00e1 \u01b0u \u0111\u00e3i", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntgolden-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("\u00c1p d\u1ee5ng ch\u00ednh s\u00e1ch h\u1ee7y ph\u00f2ng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("897000"))
                                        .referencePricePerNight(new BigDecimal("1281429"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("G\u1ed3m b\u1eefa s\u00e1ng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntgolden-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 28 m², 1 giường Queen, hướng biển, có ban công.", "Deluxe room 28 sqm, 1 queen bed, sea view, with balcony."))
                        .description(of("Phòng Deluxe 28 m², 1 giường Queen, hướng biển, có ban công. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 28 sqm, 1 queen bed, sea view, with balcony. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(28.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-33d75132054ef66d20d73d2a1189c2e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-74453e523272a768a948f23cc8da1d26.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntgolden-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Kh\u00f4ng \u0111\u01b0\u1ee3c ho\u00e0n ti\u1ec1n.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("980000"))
                                        .referencePricePerNight(new BigDecimal("1400000"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gi\u00e1 \u01b0u \u0111\u00e3i", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntgolden-deluxe-sea-view-breakfast-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("\u00c1p d\u1ee5ng ch\u00ednh s\u00e1ch h\u1ee7y ph\u00f2ng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1127000"))
                                        .referencePricePerNight(new BigDecimal("1610000"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("G\u1ed3m b\u1eefa s\u00e1ng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-ntgolden-family-suite")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Gia đình 36 m², 1 giường Queen + 1 giường đơn, cho 4 khách.", "Family room 36 sqm, 1 queen + 1 single bed, for 4 guests."))
                        .description(of("Phòng Gia đình 36 m², 1 giường Queen + 1 giường đơn, cho 4 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family room 36 sqm, 1 queen + 1 single bed, for 4 guests. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(36.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-12ae21dd63e05dc77a155a1bb7981e29.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20000183-0ec20fd967683f944f6123f152befbc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-ntgolden-family-suite-prepaid-nonref")
                                        .name(of("Family Suite - Không gồm bữa sáng", "Family Suite - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Kh\u00f4ng \u0111\u01b0\u1ee3c ho\u00e0n ti\u1ec1n.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1350000"))
                                        .referencePricePerNight(new BigDecimal("1928571"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Gi\u00e1 \u01b0u \u0111\u00e3i", "Special price"))
                                        .showLowAvailability(true)
                                        .build(),
                                RatePlan.builder()
                                        .id("rt-ntgolden-family-suite-breakfast-flex")
                                        .name(of("Family Suite - Bao gồm bữa sáng", "Family Suite - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("\u00c1p d\u1ee5ng ch\u00ednh s\u00e1ch h\u1ee7y ph\u00f2ng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1552500"))
                                        .referencePricePerNight(new BigDecimal("2217857"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("G\u1ed3m b\u1eefa s\u00e1ng", "Breakfast included"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build());

                List<NearbyPlace> nearby = List.of(
                nearby("bai-bien-nha-trang", of("Bãi biển Nha Trang", "Nha Trang Beach"), of("Bãi biển", "Beach"), 300.0),
                nearby("thap-tram-huong", of("Tháp Trầm Hương", "Tram Huong Tower"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("quang-truong-2-thang-4", of("Quảng trường 2 Tháng 4", "April 2 Square"), of("Địa điểm lân cận", "Nearby places"), 700.0),
                nearby("cong-vien-phu-dong", of("Công viên Phù Đổng", "Phu Dong Park"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("nha-tho-nui-nha-trang", of("Nhà thờ Núi (Nhà thờ Chánh tòa)", "Nha Trang Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("cho-dam", of("Chợ Đầm", "Dam Market"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("chua-long-son", of("Chùa Long Sơn", "Long Son Pagoda"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
                nearby("vien-hai-duong-hoc", of("Viện Hải dương học", "National Oceanographic Museum"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("thap-ba-ponagar", of("Tháp Bà Ponagar", "Po Nagar Towers"), of("Địa điểm lân cận", "Nearby places"), 4000.0),
                nearby("hon-chong", of("Hòn Chồng", "Hon Chong Promontory"), of("Địa điểm lân cận", "Nearby places"), 4800.0),
                nearby("i-resort-suoi-khoang-nong", of("I-resort suối khoáng nóng", "I-Resort Hot Spring"), of("Trung tâm giải trí", "Entertainment hub"), 6500.0),
                nearby("hon-tam", of("Hòn Tằm", "Hon Tam Island"), of("Khác", "Other"), 8000.0),
                nearby("vinwonders-nha-trang", of("VinWonders Nha Trang", "VinWonders Nha Trang"), of("Trung tâm giải trí", "Entertainment hub"), 11000.0),
                nearby("bai-dai", of("Bãi Dài", "Bai Dai Beach"), of("Bãi biển", "Beach"), 25000.0),
                nearby("san-bay-cam-ranh", of("Sân bay quốc tế Cam Ranh", "Cam Ranh International Airport"), of("Trung tâm giao thông", "Transport hub"), 32000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nha hang, le tan 24h, may lanh, WiFi mien phi, cho dau xe", "Restaurant, 24-hour front desk, air conditioning, free WiFi, parking"))
                                .distanceToCityCenterKm(0.5)
                                .popularAreaSummary(of("Bai bien Nha Trang, Thap Tram Huong, Quang truong 2 Thang 4", "Nha Trang Beach, Tram Huong Tower, April 2 Square"))
                                .totalRooms(42)
                                .totalFloors(14)
                                .otherHighlightFacilities(of("Dua don san bay, ho tro dat tour, giat ui, thu doi ngoai te.", "Airport transfer, tour assistance, laundry, currency exchange."))
                                .interestingPlacesSummary(of("Bai bien Nha Trang, Thap Ba Ponagar, VinWonders Nha Trang", "Nha Trang Beach, Po Nagar Towers, VinWonders Nha Trang"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thoi gian nhan phong/tra phong", "Check-in/check-out time"))
                                                                .content(of("Gio nhan phong: tu 14:00. Gio tra phong: truoc 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhan phong som", "Early check-in"))
                                                                .content(of("Ho tro nhan phong som tuy tinh trang phong trong, vui long lien he truoc.", "Early check-in is subject to availability; please contact in advance."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
                                                                .title(of("Giay to yeu cau", "Required documents"))
                                                                .content(of("Vui long xuat trinh CCCD/ho chieu hop le khi nhan phong.", "Please present a valid ID card/passport at check-in."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.SMOKING)
                                                                .title(of("Chinh sach hut thuoc", "Smoking policy"))
                                                                .content(of("Phong khong hut thuoc; co khu vuc hut thuoc rieng.", "Non-smoking rooms; a designated smoking area is available."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.OTHER)
                                                                .title(of("Bua sang", "Breakfast"))
                                                                .content(of("Khach san phuc vu bua sang; vui long chon loai gia phong bao gom bua sang.", "The hotel serves breakfast; please select a rate that includes breakfast."))
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
                                .partnerName("Ong Trum Bat Dong San")
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
                faq(of("Những tiện ích tại Golden Hotel Nha Trang?", "What amenities does Golden Hotel Nha Trang offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy, WiFi và nhiều dịch vụ khác (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, parking, elevator, WiFi and many other services (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Golden Hotel Nha Trang?", "What are the check-in and check-out times at Golden Hotel Nha Trang?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Golden Hotel Nha Trang có phục vụ ăn sáng không?", "Does Golden Hotel Nha Trang serve breakfast?"),
                        of("Có, khách sạn có dịch vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel offers breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Golden Hotel Nha Trang đạt tiêu chuẩn mấy sao?", "What star rating does Golden Hotel Nha Trang have?"),
                        of("Golden Hotel Nha Trang là khách sạn đạt tiêu chuẩn 3.0 sao.", "Golden Hotel Nha Trang is a 3.0-star hotel.")),
                faq(of("Golden Hotel Nha Trang có bao nhiêu tầng?", "How many floors does Golden Hotel Nha Trang have?"),
                        of("Golden Hotel Nha Trang có tổng cộng 14 tầng.", "Golden Hotel Nha Trang has a total of 14 floors.")),
                faq(of("Tổng số phòng tại Golden Hotel Nha Trang là bao nhiêu?", "How many rooms does Golden Hotel Nha Trang have?"),
                        of("Tổng số phòng tại Golden Hotel Nha Trang là 42 phòng.", "Golden Hotel Nha Trang has a total of 42 rooms.")),
                faq(of("Golden Hotel Nha Trang có mức giá là bao nhiêu?", "How much does Golden Hotel Nha Trang cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 650.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 650,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có cách bãi biển bao xa?", "How far is the hotel from the beach?"),
                        of("Khách sạn nằm trên đường Hùng Vương, chỉ cách bãi biển Nha Trang vài trăm mét đi bộ.", "The hotel is on Hung Vuong street, only a few hundred meters' walk from Nha Trang Beach.")),
                faq(of("Khách sạn có đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí). Sân bay quốc tế Cam Ranh cách khoảng 30–35 km.", "Yes, the hotel offers airport transfer (surcharge). Cam Ranh International Airport is about 30–35 km away.")),
                faq(of("Khách sạn có chỗ đậu xe không?", "Does the hotel have parking?"),
                        of("Có, khách sạn có chỗ đậu xe (có thể thu phí). Vui lòng liên hệ lễ tân để được hỗ trợ.", "Yes, the hotel has parking (may be subject to a fee). Please contact the front desk for assistance.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("nha-trang")
                                .cityName(of("Nha Trang", "Nha Trang"))
                                .districtName(of("Loc Tho", "Loc Tho"))
                                .wardName(of("Loc Tho", "Loc Tho"))
                                .addressLine(of("136 Hung Vuong, phuong Loc Tho, Nha Trang, Khanh Hoa, Viet Nam, 652079", "136 Hung Vuong Street, Loc Tho Ward, Nha Trang, Khanh Hoa Province, Vietnam, 652079"))
                                .location(new double[] { 109.1955308, 12.23513 })
                                .name(of("Golden Hotel Nha Trang", "Golden Hotel Nha Trang"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khach san 3 sao trung tam Nha Trang, sat bai bien tren duong Hung Vuong.", "3-star hotel in central Nha Trang, near the beach on Hung Vuong street."))
                                .description(of("Golden Hotel Nha Trang la khach san 3 sao toa lac tai 136 Hung Vuong, chi vai phut di bo den bai bien Nha Trang, mang den cho nghi tien nghi voi nha hang, le tan 24h va day du dich vu.", "Golden Hotel Nha Trang is a 3-star hotel at 136 Hung Vuong, just a few minutes' walk from Nha Trang Beach, offering comfortable accommodation with a restaurant, 24-hour front desk and full services."))
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
