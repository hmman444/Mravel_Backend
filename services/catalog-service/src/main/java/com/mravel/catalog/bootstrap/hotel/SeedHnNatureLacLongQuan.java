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
public class SeedHnNatureLacLongQuan {

        @Bean
        CommandLineRunner seedHnNatureLacLongQuanRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHnNatureLacLongQuan] start");
                        HotelDoc doc = hotelNatureLacLongQuan();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHnNatureLacLongQuan] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHnNatureLacLongQuan] done");
                };
        }

        // =====================================================================
        // NATURE HOTEL - LAC LONG QUAN (Tay Ho, Ha Noi)
        // (source: Traveloka hotelId 9000007484559; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelNatureLacLongQuan() {
                String slug = "nature-hotel-lac-long-quan";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-2da6141561d80b107e3b7b6e95467b28.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-06ce7cda107d2025ebe70aaefcdb1987.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680048173-84a16630236e6a490195c03382a2461b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-9562d7de787f190e84dc04f6cb260070.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-bc3d2b5604e3de6c2d3ddf7fd1ba34d4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-213a56f6369474d8253a7237989b2d1a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-8b6f9c46481bd09021d49f3421e83fc2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e726948aa4d453ca13e45f8eb36b1bd4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e1f7eddc3f9f9f7900153cfd3f53ba5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-0fb1f275785bd0543b0050b3a011360f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-4692784517796e506575e975dd4106b3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 105.8095587, 21.0629814 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Nature Hotel - Lac Long Quan", "About Nature Hotel - Lac Long Quan")),

                paragraph(ContentSection.STORY,
                                of("Nature Hotel - Lac Long Quan là khách sạn 4 sao tọa lạc tại số 533 đường Lạc Long Quân, phường Bưởi, quận Tây Hồ, Hà Nội. Nằm sát Hồ Tây thơ mộng, khách sạn mang đến không gian nghỉ dưỡng yên tĩnh, gần gũi thiên nhiên nhưng vẫn thuận tiện di chuyển vào trung tâm phố cổ. Với 60 phòng trải trên 10 tầng cùng dịch vụ chuẩn 4 sao, đây là lựa chọn lý tưởng cho cả khách du lịch lẫn khách công tác.", "Nature Hotel - Lac Long Quan is a 4-star hotel located at 533 Lac Long Quan Street, Buoi Ward, Tay Ho District, Hanoi. Set right next to the poetic West Lake, the hotel offers a quiet, nature-friendly retreat while remaining convenient for trips into the Old Quarter. With 60 rooms across 10 floors and 4-star service, it is an ideal choice for both leisure and business travelers.")),

                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Family rộng rãi cho gia đình. Mỗi phòng được trang bị máy lạnh, minibar, két an toàn, máy sấy tóc, phòng tắm vòi sen riêng cùng Wi-Fi miễn phí tốc độ cao, đảm bảo sự thoải mái cho mọi kỳ lưu trú.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to spacious Family rooms. Each room is equipped with air conditioning, a minibar, a safe, a hair dryer, a private shower bathroom and high-speed free Wi-Fi, ensuring comfort for every stay.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng phong phú tại bàn cùng thực đơn món Việt và món Âu cho bữa trưa và tối. Du khách có thể thưởng thức đồ uống và cà phê trong không gian ấm cúng, hoặc gọi dịch vụ phòng để tận hưởng bữa ăn ngay tại phòng nghỉ.", "The hotel's restaurant serves a rich breakfast at the table along with Vietnamese and European à la carte menus for lunch and dinner. Guests can enjoy drinks and coffee in a cozy setting, or order room service to dine right in their room.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp lễ tân 24 giờ, thang máy, dịch vụ dọn phòng, giặt ủi, giữ hành lý, thu đổi ngoại tệ và hỗ trợ đặt tour. Dịch vụ nhận và trả phòng cấp tốc cùng đưa đón sân bay theo yêu cầu giúp hành trình của du khách thêm thuận tiện.", "The hotel provides a 24-hour front desk, an elevator, housekeeping, laundry, luggage storage, currency exchange and tour booking assistance. Express check-in/check-out and airport transfer on request make travelers' journeys more convenient.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách dễ dàng dạo bộ ven Hồ Tây, ghé Chùa Trấn Quốc cổ kính hay khám phá làng hoa Nhật Tân. Các điểm đến nổi tiếng như Lăng Chủ tịch Hồ Chí Minh, Hoàng thành Thăng Long, Văn Miếu - Quốc Tử Giám và phố cổ Hà Nội đều nằm trong bán kính di chuyển ngắn.", "From the hotel, guests can easily stroll along West Lake, visit the ancient Tran Quoc Pagoda or explore the Nhat Tan flower village. Famous destinations such as the Ho Chi Minh Mausoleum, the Imperial Citadel of Thang Long, the Temple of Literature and Hanoi's Old Quarter are all within a short ride.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cách trung tâm phố cổ khoảng 6 km và sân bay quốc tế Nội Bài khoảng 25 km. Du khách có thể sử dụng taxi, xe công nghệ hoặc dịch vụ đưa đón sân bay của khách sạn để di chuyển thuận tiện đến các điểm tham quan trong thành phố.", "The hotel is about 6 km from the Old Quarter center and around 25 km from Noi Bai International Airport. Guests can use taxis, ride-hailing services or the hotel's airport transfer to conveniently reach attractions around the city.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Khu vực Tây Hồ nổi tiếng với không gian xanh mát, nhiều quán cà phê view hồ, nhà hàng và phố ẩm thực sôi động về đêm. Đây cũng là nơi tập trung nhiều ngôi chùa, đền cổ và làng nghề truyền thống, lý tưởng cho những ai muốn tìm hiểu văn hóa Hà Nội.", "The Tay Ho area is renowned for its lush green space, lakeside cafes, restaurants and a lively night dining street. It is also home to many ancient pagodas, temples and traditional craft villages, ideal for those wishing to explore Hanoi's culture.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Nature Hotel - Lac Long Quan", "Why choose Nature Hotel - Lac Long Quan")),

                paragraph(ContentSection.STORY,
                                of("Chọn Nature Hotel - Lac Long Quan, du khách tận hưởng sự cân bằng giữa không gian yên tĩnh bên Hồ Tây và sự tiện lợi của một khách sạn 4 sao đầy đủ dịch vụ. Vị trí gần các điểm tham quan biểu tượng cùng đội ngũ nhân viên thân thiện hứa hẹn một kỳ lưu trú đáng nhớ tại Hà Nội.", "Choosing Nature Hotel - Lac Long Quan lets travelers enjoy a balance between the quiet space by West Lake and the convenience of a fully serviced 4-star hotel. Its proximity to iconic attractions and friendly staff promise a memorable stay in Hanoi.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Nhân viên xách hành lý", "Bellboy service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("welcome_drink", of("Thức uống chào mừng miễn phí", "Complimentary welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-hn-nature-llq-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 22 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 22 sqm, 1 double bed, with full basic amenities."))
                        .description(of("Phòng Standard 22 m², 1 giường đôi, đầy đủ tiện nghi cơ bản.", "Standard room 22 sqm, 1 double bed, with full basic amenities."))
                        .areaSqm(22.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(18)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e1f7eddc3f9f9f7900153cfd3f53ba5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0)))
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
                                        .id("rt-hn-nature-llq-standard-double-prepaid-nonref")
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
                                        .id("rt-hn-nature-llq-standard-double-breakfast-flex")
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
                        .id("rt-hn-nature-llq-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 26 m², 2 giường đơn, phù hợp cho bạn bè hoặc đồng nghiệp.", "Superior room 26 sqm, 2 single beds, ideal for friends or colleagues."))
                        .description(of("Phòng Superior 26 m², 2 giường đơn, phù hợp cho bạn bè hoặc đồng nghiệp.", "Superior room 26 sqm, 2 single beds, ideal for friends or colleagues."))
                        .areaSqm(26.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(16)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e1f7eddc3f9f9f7900153cfd3f53ba5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0)))
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
                                        .id("rt-hn-nature-llq-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
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
                                        .id("rt-hn-nature-llq-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
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
                        .id("rt-hn-nature-llq-deluxe-queen-lakeview")
                        .name(of("Deluxe Queen Lake View", "Deluxe Queen Lake View"))
                        .shortDescription(of("Phòng Deluxe 30 m² hướng hồ, 1 giường Queen, có khu vực tiếp khách.", "Deluxe room 30 sqm with lake view, 1 queen bed and a seating area."))
                        .description(of("Phòng Deluxe 30 m² hướng hồ, 1 giường Queen, có khu vực tiếp khách.", "Deluxe room 30 sqm with lake view, 1 queen bed and a seating area."))
                        .areaSqm(30.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(16)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e1f7eddc3f9f9f7900153cfd3f53ba5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-4692784517796e506575e975dd4106b3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SEATING_AREA",
                                "BALCONY"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-hn-nature-llq-deluxe-queen-lakeview-prepaid-nonref")
                                        .name(of("Deluxe Queen Lake View - Không gồm bữa sáng", "Deluxe Queen Lake View - Room only"))
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
                                        .id("rt-hn-nature-llq-deluxe-queen-lakeview-breakfast-flex")
                                        .name(of("Deluxe Queen Lake View - Bao gồm bữa sáng", "Deluxe Queen Lake View - Breakfast included"))
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
                        .id("rt-hn-nature-llq-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Family 36 m², 1 giường đôi và 1 giường đơn, cho 3 khách.", "Family room 36 sqm, 1 double and 1 single bed, for 3 guests."))
                        .description(of("Phòng Family 36 m², 1 giường đôi và 1 giường đơn, cho 3 khách.", "Family room 36 sqm, 1 double and 1 single bed, for 3 guests."))
                        .areaSqm(36.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường đôi và 1 giường đơn", "1 double bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-e1f7eddc3f9f9f7900153cfd3f53ba5d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20090749-4692784517796e506575e975dd4106b3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SEATING_AREA",
                                "BALCONY"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-hn-nature-llq-family-room-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("2100000"))
                                        .referencePricePerNight(new BigDecimal("3000000"))
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
                                        .id("rt-hn-nature-llq-family-room-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("2415000"))
                                        .referencePricePerNight(new BigDecimal("3450000"))
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
                nearby("chua-tran-quoc", of("Chùa Trấn Quốc", "Tran Quoc Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("ho-tay", of("Hồ Tây", "West Lake"), of("Địa điểm lân cận", "Nearby places"), 800.0),
                nearby("hoang-thanh-thang-long", of("Hoàng thành Thăng Long", "Imperial Citadel of Thang Long"), of("Địa điểm lân cận", "Nearby places"), 5000.0),
                nearby("lang-chu-tich-ho-chi-minh", of("Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum"), of("Địa điểm lân cận", "Nearby places"), 5200.0),
                nearby("chua-mot-cot", of("Chùa Một Cột", "One Pillar Pagoda"), of("Địa điểm lân cận", "Nearby places"), 5400.0),
                nearby("bao-tang-dan-toc-hoc", of("Bảo tàng Dân tộc học", "Museum of Ethnology"), of("Khác", "Other"), 4000.0),
                nearby("van-mieu-quoc-tu-giam", of("Văn Miếu - Quốc Tử Giám", "Temple of Literature"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("cau-long-bien", of("Cầu Long Biên", "Long Bien Bridge"), of("Khác", "Other"), 6500.0),
                nearby("ho-hoan-kiem", of("Hồ Hoàn Kiếm", "Hoan Kiem Lake"), of("Địa điểm lân cận", "Nearby places"), 6800.0),
                nearby("pho-co-ha-noi", of("Phố cổ Hà Nội", "Hanoi Old Quarter"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("cho-dong-xuan", of("Chợ Đồng Xuân", "Dong Xuan Market"), of("Trung tâm mua sắm", "Shopping hub"), 6200.0),
                nearby("nha-tho-lon", of("Nhà thờ Lớn", "St Joseph Cathedral"), of("Địa điểm lân cận", "Nearby places"), 7000.0),
                nearby("pho-di-bo-ho-guom", of("Phố đi bộ Hồ Gươm", "Hoan Kiem Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 6900.0),
                nearby("nha-hat-lon-ha-noi", of("Nhà hát Lớn Hà Nội", "Hanoi Opera House"), of("Địa điểm lân cận", "Nearby places"), 7500.0),
                nearby("san-bay-noi-bai", of("Sân bay quốc tế Nội Bài", "Noi Bai International Airport"), of("Trung tâm giao thông", "Transport hub"), 25000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, lễ tân 24h, máy lạnh, thang máy, WiFi miễn phí, đưa đón sân bay", "Restaurant, 24-hour front desk, air conditioning, elevator, free WiFi, airport transfer"))
                                .distanceToCityCenterKm(6.0)
                                .popularAreaSummary(of("Hồ Tây, Chùa Trấn Quốc, Phố cổ Hà Nội", "West Lake, Tran Quoc Pagoda, Hanoi Old Quarter"))
                                .totalRooms(60)
                                .totalFloors(10)
                                .otherHighlightFacilities(of("Dịch vụ giặt ủi, giữ hành lý, thu đổi ngoại tệ, hỗ trợ đặt tour, nhận/trả phòng cấp tốc.", "Laundry, luggage storage, currency exchange, tour assistance, express check-in/check-out."))
                                .interestingPlacesSummary(of("Hồ Tây, Hoàng thành Thăng Long, Văn Miếu - Quốc Tử Giám", "West Lake, Imperial Citadel of Thang Long, Temple of Literature"))
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
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay Nội Bài theo yêu cầu (thu phí).", "The hotel offers Noi Bai airport transfer on request (surcharge)."))
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
                faq(of("Những tiện ích tại Nature Hotel - Lac Long Quan?", "What amenities does Nature Hotel - Lac Long Quan offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, thang máy, WiFi miễn phí, dịch vụ giặt ủi và đưa đón sân bay (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, elevator, free WiFi, laundry and airport transfer (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Nature Hotel - Lac Long Quan?", "What are the check-in and check-out times at Nature Hotel - Lac Long Quan?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Nature Hotel - Lac Long Quan có phục vụ ăn sáng không?", "Does Nature Hotel - Lac Long Quan serve breakfast?"),
                        of("Có, khách sạn có phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Nature Hotel - Lac Long Quan đạt tiêu chuẩn mấy sao?", "What star rating does Nature Hotel - Lac Long Quan have?"),
                        of("Nature Hotel - Lac Long Quan là khách sạn đạt tiêu chuẩn 4.0 sao.", "Nature Hotel - Lac Long Quan is a 4.0-star hotel.")),
                faq(of("Nature Hotel - Lac Long Quan có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does Nature Hotel - Lac Long Quan have?"),
                        of("Khách sạn có tổng cộng 10 tầng với 60 phòng.", "The hotel has a total of 10 floors with 60 rooms.")),
                faq(of("Nature Hotel - Lac Long Quan có hỗ trợ nhận phòng sớm không?", "Does Nature Hotel - Lac Long Quan support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Nature Hotel - Lac Long Quan có cung cấp dịch vụ trả phòng trễ không?", "Does Nature Hotel - Lac Long Quan offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Nature Hotel - Lac Long Quan có dịch vụ đưa đón sân bay không?", "Does Nature Hotel - Lac Long Quan offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ trực tiếp để biết thêm chi tiết.", "Yes, the hotel offers airport transfer on request. Please contact the hotel directly for more details.")),
                faq(of("Nature Hotel - Lac Long Quan có mức giá là bao nhiêu?", "How much does Nature Hotel - Lac Long Quan cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 1.000.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 1,000,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có gần Hồ Tây không?", "Is the hotel close to West Lake?"),
                        of("Có, khách sạn nằm ngay trên đường Lạc Long Quân ven Hồ Tây, thuận tiện dạo bộ và ngắm cảnh hồ.", "Yes, the hotel is located right on Lac Long Quan Street by West Lake, convenient for strolling and enjoying lake views.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ha-noi")
                                .cityName(of("Hà Nội", "Hanoi"))
                                .districtName(of("Tây Hồ", "Tay Ho"))
                                .wardName(of("Bưởi", "Buoi"))
                                .addressLine(of("Số 533 đường Lạc Long Quân, phường Bưởi, quận Tây Hồ, Hà Nội, Việt Nam, 120000", "533 Lac Long Quan Street, Buoi Ward, Tay Ho District, Ha Noi, Vietnam, 120000"))
                                .location(new double[] { 105.8095587, 21.0629814 })
                                .name(of("Nature Hotel - Lac Long Quan", "Nature Hotel - Lac Long Quan"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao bên Hồ Tây, quận Tây Hồ, Hà Nội.", "4-star hotel by West Lake, Tay Ho District, Hanoi."))
                                .description(of("Nature Hotel - Lac Long Quan là khách sạn 4 sao ven Hồ Tây với 60 phòng, nhà hàng, lễ tân 24h và dịch vụ đưa đón sân bay, thuận tiện khám phá phố cổ và các điểm tham quan biểu tượng của Hà Nội.", "Nature Hotel - Lac Long Quan is a 4-star hotel by West Lake with 60 rooms, a restaurant, a 24-hour front desk and airport transfer, convenient for exploring the Old Quarter and Hanoi's iconic attractions."))
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
