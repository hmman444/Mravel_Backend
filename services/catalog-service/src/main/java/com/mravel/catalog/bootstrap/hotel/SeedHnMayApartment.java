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
public class SeedHnMayApartment {

        @Bean
        CommandLineRunner seedHnMayApartmentRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHnMayApartment] start");
                        HotelDoc doc = hotelMayApartment();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHnMayApartment] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHnMayApartment] done");
                };
        }

        // =====================================================================
        // MAY HOTEL & APARTMENT (Nam Tu Liem, Ha Noi)
        // (source: Traveloka hotelId 9000006924942; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelMayApartment() {
                String slug = "may-hotel-apartment";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-6fd59df054d9a394c1046de32b03921c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-a65ca297c6599c792111b861a7e7defb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-3b100b2c64e84922bf6bc7507bc7177f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-c4fe49642f110520b63f7c2cf043bfe9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-08670751989c3c8a007b6cbdb5b82a22.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-a9377a5949d137cf37d7e35bdb43b9b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-b7ba764316646ae5cc344ec7ae218ab2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-422b886db9308f9727ea19060a2c3c86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-78b1f59f948afb982d427f442cb2948b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-1e509a033ce1025b4d5476dfdd243a1d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-78f5bb4d72491bc846e659beeff29953.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-e09b2882bb0376d291fdcd5c0925e3b5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-be089d47b4a4038ffb655476046f2af3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 12));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 105.771791, 21.0206333 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về MAY Hotel & Apartment", "About MAY Hotel & Apartment")),

                paragraph(ContentSection.STORY,
                                of("MAY Hotel & Apartment là cơ sở lưu trú 2 sao tọa lạc tại phường Mỹ Đình 1, quận Nam Từ Liêm, Hà Nội. Với mô hình kết hợp khách sạn và căn hộ dịch vụ, nơi đây mang đến không gian nghỉ ngơi gọn gàng, tiện nghi cùng dịch vụ thân thiện, phù hợp cho cả khách công tác lẫn du khách khám phá thủ đô.", "MAY Hotel & Apartment is a 2-star property located in My Dinh 1 Ward, Nam Tu Liem District, Hanoi. Combining a hotel and serviced apartments, it offers tidy, comfortable accommodation and friendly service, ideal for both business travelers and tourists exploring the capital.")),

                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & in-room amenities")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp nhiều hạng phòng từ Standard, Superior, Deluxe đến căn hộ Studio và căn hộ 1 phòng ngủ. Mỗi phòng được trang bị máy lạnh, Wi-Fi miễn phí, minibar, bàn làm việc, máy sấy tóc, nước nóng và đồ dùng phòng tắm, một số căn hộ có thêm bếp nhỏ tiện cho lưu trú dài ngày.", "The hotel offers a range of room types from Standard, Superior and Deluxe to Studio and one-bedroom apartments. Each room comes with air conditioning, free Wi-Fi, a minibar, a work desk, a hair dryer, hot water and bath amenities; some apartments add a kitchenette convenient for long stays.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng trong khuôn viên phục vụ bữa sáng cùng thực đơn gọi món Á – Âu cho bữa trưa và bữa tối. Quầy cà phê và quán bar nhỏ là nơi lý tưởng để thư giãn sau một ngày làm việc hay tham quan, cùng đồ uống và món ăn nhẹ.", "The on-site restaurant serves breakfast along with an à la carte Asian and European menu for lunch and dinner. A coffee corner and a small bar make ideal spots to unwind after a day of work or sightseeing, with drinks and light bites.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("MAY Hotel & Apartment có lễ tân 24 giờ, dịch vụ nhận và trả phòng cấp tốc, dịch vụ giặt ủi, giữ hành lý, thang máy và bãi đậu xe. Khách có thể đặt dịch vụ thuê xe, thuê xe đạp và nhờ lễ tân hỗ trợ đặt tour khám phá Hà Nội.", "MAY Hotel & Apartment features a 24-hour front desk, express check-in and check-out, laundry service, luggage storage, an elevator and parking. Guests can arrange car or bicycle rental and ask the front desk for help booking tours around Hanoi.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                paragraph(ContentSection.STORY,
                                of("Nằm tại khu Mỹ Đình sầm uất, khách sạn thuận tiện di chuyển đến Sân vận động Quốc gia Mỹ Đình, Trung tâm Hội nghị Quốc gia và Bảo tàng Dân tộc học. Khu vực trung tâm như Hồ Hoàn Kiếm, Phố cổ và Văn Miếu cách khoảng 8–11 km.", "Set in the bustling My Dinh area, the hotel offers easy access to My Dinh National Stadium, the National Convention Center and the Museum of Ethnology. The city center such as Hoan Kiem Lake, the Old Quarter and the Temple of Literature is about 8–11 km away.")),

                heading(ContentSection.STORY, of("Di chuyển & giao thông", "Getting around & transport")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cách Sân bay Quốc tế Nội Bài khoảng 25 km, thuận tiện đặt xe đưa đón. Hệ thống xe buýt và các tuyến đường lớn quanh Mỹ Đình giúp việc di chuyển vào trung tâm hoặc tới các bến xe liên tỉnh trở nên dễ dàng.", "The hotel is about 25 km from Noi Bai International Airport, with convenient airport transfer. Bus routes and major roads around My Dinh make it easy to reach the city center or inter-provincial coach stations.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Xung quanh khách sạn là khu dân cư và thương mại nhộn nhịp với nhiều nhà hàng, quán cà phê, siêu thị và cửa hàng tiện lợi. Du khách dễ dàng tìm thấy ẩm thực địa phương cũng như các dịch vụ thiết yếu chỉ trong vài phút đi bộ.", "The hotel is surrounded by a lively residential and commercial area with many restaurants, cafes, supermarkets and convenience stores. Guests can easily find local cuisine and essential services just a few minutes' walk away.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn MAY Hotel & Apartment", "Why choose MAY Hotel & Apartment")),

                paragraph(ContentSection.STORY,
                                of("Với vị trí thuận tiện tại Nam Từ Liêm, phòng ốc sạch sẽ, dịch vụ chu đáo và mức giá hợp lý, MAY Hotel & Apartment là lựa chọn đáng cân nhắc cho cả chuyến công tác lẫn du lịch. Mô hình căn hộ dịch vụ còn phù hợp cho khách lưu trú dài ngày tại Hà Nội.", "With a convenient location in Nam Tu Liem, clean rooms, attentive service and reasonable prices, MAY Hotel & Apartment is a worthy choice for both business and leisure trips. Its serviced-apartment model also suits guests staying long-term in Hanoi.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe (thu phí)", "Parking (surcharge)"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Rooftop terrace"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-may-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Standard Double 18 m², 1 giường đôi.", "Standard Double 18 sqm, 1 double bed."))
                        .description(of("Standard Double 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Standard Double 18 sqm, 1 double bed. Fully equipped for a comfortable stay."))
                        .areaSqm(18.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-6fd59df054d9a394c1046de32b03921c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-a65ca297c6599c792111b861a7e7defb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-may-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("550000"))
                                        .referencePricePerNight(new BigDecimal("785714"))
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
                                        .id("rt-may-standard-double-pay-flex")
                                        .name(of("Standard Double - Thanh toán tại khách sạn", "Standard Double - Pay at hotel"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("632500"))
                                        .referencePricePerNight(new BigDecimal("903571"))
                                        .discountPercent(30)
                                        .taxPercent(new BigDecimal("8"))
                                        .serviceFeePercent(new BigDecimal("5"))
                                        .priceIncludesTax(false)
                                        .priceIncludesServiceFee(false)
                                        .lengthOfStayDiscounts(List.of())
                                        .promoLabel(of("Linh hoạt", "Flexible"))
                                        .showLowAvailability(false)
                                        .build()))
                        .build(),
                RoomType.builder()
                        .id("rt-may-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Superior Twin 20 m², 2 giường đơn.", "Superior Twin 20 sqm, 2 single bed."))
                        .description(of("Superior Twin 20 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior Twin 20 sqm, 2 single bed. Fully equipped for a comfortable stay."))
                        .areaSqm(20.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(16)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-3b100b2c64e84922bf6bc7507bc7177f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-c4fe49642f110520b63f7c2cf043bfe9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-may-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
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
                                        .id("rt-may-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
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
                        .id("rt-may-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Deluxe Queen 24 m², 1 giường Queen.", "Deluxe Queen 24 sqm, 1 queen bed."))
                        .description(of("Deluxe Queen 24 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe Queen 24 sqm, 1 queen bed. Fully equipped for a comfortable stay."))
                        .areaSqm(24.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-08670751989c3c8a007b6cbdb5b82a22.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-a9377a5949d137cf37d7e35bdb43b9b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-may-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
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
                                        .id("rt-may-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
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
                        .build(),
                RoomType.builder()
                        .id("rt-may-studio-apartment")
                        .name(of("Căn hộ Studio", "Studio Apartment"))
                        .shortDescription(of("Căn hộ Studio 28 m², 1 giường Queen.", "Studio Apartment 28 sqm, 1 queen bed."))
                        .description(of("Căn hộ Studio 28 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Studio Apartment 28 sqm, 1 queen bed. Fully equipped for a comfortable stay."))
                        .areaSqm(28.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-b7ba764316646ae5cc344ec7ae218ab2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-422b886db9308f9727ea19060a2c3c86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-may-studio-apartment-prepaid-nonref")
                                        .name(of("Căn hộ Studio - Không gồm bữa sáng", "Studio Apartment - Room only"))
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
                                        .id("rt-may-studio-apartment-breakfast-flex")
                                        .name(of("Căn hộ Studio - Bao gồm bữa sáng", "Studio Apartment - Breakfast included"))
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
                        .id("rt-may-one-bedroom-apartment")
                        .name(of("Căn hộ 1 phòng ngủ", "One-Bedroom Apartment"))
                        .shortDescription(of("Căn hộ 1 phòng ngủ 36 m², 1 giường King.", "One-Bedroom Apartment 36 sqm, 1 king bed."))
                        .description(of("Căn hộ 1 phòng ngủ 36 m², 1 giường King. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "One-Bedroom Apartment 36 sqm, 1 king bed. Fully equipped for a comfortable stay."))
                        .areaSqm(36.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-78b1f59f948afb982d427f442cb2948b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680029122-1e509a033ce1025b4d5476dfdd243a1d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BALCONY",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-may-one-bedroom-apartment-prepaid-nonref")
                                        .name(of("Căn hộ 1 phòng ngủ - Không gồm bữa sáng", "One-Bedroom Apartment - Room only"))
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
                                        .id("rt-may-one-bedroom-apartment-breakfast-flex")
                                        .name(of("Căn hộ 1 phòng ngủ - Bao gồm bữa sáng", "One-Bedroom Apartment - Breakfast included"))
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
                        .build());

                List<NearbyPlace> nearby = List.of(
                nearby("ho-hoan-kiem", of("Hồ Hoàn Kiếm", "Hoan Kiem Lake"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("pho-co-ha-noi", of("Phố cổ Hà Nội", "Hanoi Old Quarter"), of("Địa điểm lân cận", "Nearby places"), 9500.0),
                nearby("nha-tho-lon", of("Nhà thờ Lớn Hà Nội", "St Joseph Cathedral"), of("Địa điểm lân cận", "Nearby places"), 9200.0),
                nearby("van-mieu-quoc-tu-giam", of("Văn Miếu - Quốc Tử Giám", "Temple of Literature"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("lang-chu-tich-ho-chi-minh", of("Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum"), of("Địa điểm lân cận", "Nearby places"), 6800.0),
                nearby("chua-mot-cot", of("Chùa Một Cột", "One Pillar Pagoda"), of("Địa điểm lân cận", "Nearby places"), 6700.0),
                nearby("hoang-thanh-thang-long", of("Hoàng thành Thăng Long", "Imperial Citadel of Thang Long"), of("Địa điểm lân cận", "Nearby places"), 6900.0),
                nearby("nha-hat-lon-ha-noi", of("Nhà hát Lớn Hà Nội", "Hanoi Opera House"), of("Địa điểm lân cận", "Nearby places"), 10000.0),
                nearby("ho-tay", of("Hồ Tây", "West Lake"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("chua-tran-quoc", of("Chùa Trấn Quốc", "Tran Quoc Pagoda"), of("Địa điểm lân cận", "Nearby places"), 6200.0),
                nearby("cau-long-bien", of("Cầu Long Biên", "Long Bien Bridge"), of("Địa điểm lân cận", "Nearby places"), 10500.0),
                nearby("pho-di-bo-ho-guom", of("Phố đi bộ Hồ Gươm", "Hoan Kiem Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 9100.0),
                nearby("cho-dong-xuan", of("Chợ Đồng Xuân", "Dong Xuan Market"), of("Địa điểm lân cận", "Nearby places"), 10200.0),
                nearby("bao-tang-dan-toc-hoc", of("Bảo tàng Dân tộc học", "Vietnam Museum of Ethnology"), of("Khác", "Other"), 2500.0),
                nearby("san-bay-noi-bai", of("Sân bay Quốc tế Nội Bài", "Noi Bai International Airport"), of("Trung tâm giao thông", "Transport hub"), 25000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy, WiFi miễn phí", "Restaurant, air conditioning, 24-hour front desk, parking, elevator, free WiFi"))
                                .distanceToCityCenterKm(9.0)
                                .popularAreaSummary(of("Mỹ Đình, Bảo tàng Dân tộc học, Hồ Tây", "My Dinh, Museum of Ethnology, West Lake"))
                                .totalRooms(64)
                                .totalFloors(9)
                                .otherHighlightFacilities(of("Giặt ủi, giữ hành lý, cho thuê xe, đưa đón sân bay, hỗ trợ đặt tour.", "Laundry, luggage storage, car hire, airport transfer, tour booking assistance."))
                                .interestingPlacesSummary(of("Phố cổ Hà Nội, Văn Miếu - Quốc Tử Giám, Hồ Hoàn Kiếm", "Hanoi Old Quarter, Temple of Literature, Hoan Kiem Lake"))
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
                                                                .title(of("Lưu trú dài ngày", "Long stays"))
                                                                .content(of("Khu căn hộ phù hợp cho lưu trú dài ngày; liên hệ lễ tân để biết ưu đãi.", "The apartments suit long stays; contact the front desk for offers."))
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
                faq(of("Những tiện ích tại MAY Hotel & Apartment?", "What amenities does MAY Hotel & Apartment offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("MAY Hotel & Apartment có phục vụ ăn sáng không?", "Does MAY Hotel & Apartment serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a room type that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("MAY Hotel & Apartment đạt tiêu chuẩn mấy sao?", "What star rating does MAY Hotel & Apartment have?"),
                        of("MAY Hotel & Apartment là khách sạn đạt tiêu chuẩn 2 sao.", "MAY Hotel & Apartment is a 2-star hotel.")),
                faq(of("MAY Hotel & Apartment có hỗ trợ nhận phòng sớm không?", "Does MAY Hotel & Apartment support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("MAY Hotel & Apartment có cung cấp dịch vụ trả phòng trễ không?", "Does MAY Hotel & Apartment offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("MAY Hotel & Apartment có chỗ đậu xe không?", "Does MAY Hotel & Apartment have parking?"),
                        of("Có, khách sạn có bãi đậu xe (có thể thu phí). Vui lòng liên hệ lễ tân để biết thêm chi tiết.", "Yes, the hotel has parking (a surcharge may apply). Please contact the front desk for details.")),
                faq(of("Khách sạn cách trung tâm Hà Nội bao xa?", "How far is the hotel from Hanoi city center?"),
                        of("Khách sạn nằm tại Mỹ Đình, quận Nam Từ Liêm, cách khu vực Hồ Hoàn Kiếm khoảng 9 km.", "The hotel is located in My Dinh, Nam Tu Liem District, about 9 km from the Hoan Kiem Lake area.")),
                faq(of("Khách sạn cách sân bay Nội Bài bao xa?", "How far is the hotel from Noi Bai Airport?"),
                        of("Khách sạn cách Sân bay Quốc tế Nội Bài khoảng 25 km, thuận tiện đặt dịch vụ đưa đón.", "The hotel is about 25 km from Noi Bai International Airport, with convenient airport transfer available.")),
                faq(of("MAY Hotel & Apartment có căn hộ dịch vụ cho lưu trú dài ngày không?", "Does MAY Hotel & Apartment offer serviced apartments for long stays?"),
                        of("Có, khách sạn có các căn hộ Studio và căn hộ 1 phòng ngủ, một số có bếp nhỏ, phù hợp cho khách lưu trú dài ngày.", "Yes, the hotel offers Studio and one-bedroom apartments, some with a kitchenette, suitable for long-term guests.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ha-noi")
                                .cityName(of("Hà Nội", "Hanoi"))
                                .districtName(of("Nam Từ Liêm", "Nam Tu Liem"))
                                .wardName(of("Mỹ Đình 1", "My Dinh 1"))
                                .addressLine(of("Số 45, ngõ 322/95 đường Mỹ Đình, phường Mỹ Đình 1, quận Nam Từ Liêm, Mỹ Đình 1, Nam Từ Liêm, Hà Nội, Việt Nam, 100000", "45 Alley 322/95 My Dinh Road, My Dinh 1 Ward, Nam Tu Liem District, My Dinh 1 Ward, Nam Tu Liem District, Hanoi, Vietnam, 100000"))
                                .location(new double[] { 105.771791, 21.0206333 })
                                .name(of("MAY Hotel & Apartment", "MAY Hotel & Apartment"))
                                .slug(slug)
                                .hotelType(HotelType.APARTMENT)
                                .starRating(2)
                                .shortDescription(of("Khách sạn & căn hộ 2 sao tại Mỹ Đình, Nam Từ Liêm, Hà Nội.", "2-star hotel & apartment in My Dinh, Nam Tu Liem, Hanoi."))
                                .description(of("MAY Hotel & Apartment là cơ sở lưu trú 2 sao kết hợp khách sạn và căn hộ dịch vụ tại Nam Từ Liêm, Hà Nội, với phòng ốc sạch sẽ, nhà hàng, lễ tân 24h và WiFi miễn phí.", "MAY Hotel & Apartment is a 2-star property combining a hotel and serviced apartments in Nam Tu Liem, Hanoi, with clean rooms, a restaurant, a 24-hour front desk and free WiFi."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("550000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("785714"))
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
