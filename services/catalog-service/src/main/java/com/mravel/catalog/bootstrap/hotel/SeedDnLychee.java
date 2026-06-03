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
public class SeedDnLychee {

        @Bean
        CommandLineRunner seedDnLycheeRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedDnLychee] start");
                        HotelDoc doc = hotelLychee();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedDnLychee] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedDnLychee] done");
                };
        }

        // =====================================================================
        // LYCHEE HOTEL (Da Nang, Thanh Khe)
        // (source: Traveloka hotelId 3000010026691; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelLychee() {
                String slug = "lychee-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-a9313a783d221aabbd11984eb0a6cb69.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-6165b8837040e7b97e2e7b2401becc7e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-b292fa7f3c0252f8d93cdceaffa945a3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-82c7d4455e8ccc857464866a7967195b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-85180b84cc34dc279c3bfff5355f0e67.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-655670d22e1195a0ab32724155ba4f62.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f53701466932ff235ca40d2e23ca840e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-b7544284528462d9cbbc67bc38f8233d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-61d84fe804c8a850b7365cd6d7ccaaba.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e8f64878545866f9d675aaa2a61dd425.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-da2394aa86d349722a4fa79f36af43df.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-6d3831ce9daa3e1a632f10c0ee654e30.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f7cffab3b94eb10f9de94a13e0020558.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-793baf3fe46635eefa429bf814403951.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 14));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 108.20684198642738, 16.075800871243924 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Lychee Hotel", "About Lychee Hotel")),

                paragraph(ContentSection.STORY,
                                of("Lychee Hotel là khách sạn 3 sao tọa lạc tại 435-437 Nguyễn Tất Thành, quận Thanh Khê, thành phố Đà Nẵng. Nằm ngay trên tuyến đường ven biển Nguyễn Tất Thành, khách sạn mang đến không gian nghỉ ngơi thoải mái, sạch sẽ với mức giá hợp lý, phù hợp cho cả khách du lịch lẫn khách công tác.", "Lychee Hotel is a 3-star hotel located at 435-437 Nguyen Tat Thanh Street, Thanh Khe District, Da Nang City. Sitting right on the coastal Nguyen Tat Thanh boulevard, the hotel offers a comfortable and clean stay at a reasonable price, ideal for both leisure and business travelers.")),

                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình. Mỗi phòng được trang bị máy lạnh, minibar, két an toàn, bàn làm việc, máy sấy tóc, phòng tắm vòi sen riêng, nước suối miễn phí và Wi-Fi tốc độ cao, đáp ứng tốt nhu cầu nghỉ ngơi của du khách.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to Family rooms. Each room is equipped with air conditioning, a minibar, a safe, a work desk, a hair dryer, a private shower bathroom, complimentary bottled water and high-speed Wi-Fi to meet guests' needs.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng đa dạng cùng các món ăn Á - Âu trong không gian thoáng đãng, có máy lạnh. Quầy bar và tiệm cà phê tại sảnh là nơi lý tưởng để du khách thư giãn, thưởng thức đồ uống sau một ngày khám phá thành phố biển.", "The hotel restaurant serves a varied breakfast along with Asian and European dishes in an airy, air-conditioned setting. The bar and lobby coffee shop are ideal spots to relax and enjoy a drink after a day exploring the coastal city.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Lychee Hotel có lễ tân 24 giờ, dịch vụ phòng, dịch vụ giặt ủi, giữ hành lý, thu đổi ngoại tệ, đưa đón sân bay (thu phí), cho thuê xe đạp và xe hơi. Khách sạn cũng hỗ trợ đặt tour, có thang máy, bãi đậu xe và đội ngũ nhân viên thân thiện, sẵn sàng phục vụ.", "Lychee Hotel features a 24-hour front desk, room service, laundry, luggage storage, currency exchange, airport transfer (surcharge), bicycle and car rental. The hotel also assists with tour bookings and offers an elevator, parking and a friendly, attentive staff.")),

                heading(ContentSection.STORY, of("Vị trí & bãi biển", "Location & the beach")),

                paragraph(ContentSection.STORY,
                                of("Nằm trên đường Nguyễn Tất Thành ven vịnh Đà Nẵng, khách sạn chỉ cách bãi biển vài bước chân, thuận tiện để dạo biển, ngắm hoàng hôn. Trung tâm thành phố, cầu Rồng và cầu sông Hàn đều nằm trong bán kính di chuyển ngắn.", "Located on Nguyen Tat Thanh Street along Da Nang Bay, the hotel is just steps from the beach, perfect for seaside strolls and sunset views. The city center, Dragon Bridge and Han River Bridge are all a short ride away.")),

                heading(ContentSection.STORY, of("Di chuyển & giao thông", "Getting around")),

                paragraph(ContentSection.STORY,
                                of("Sân bay quốc tế Đà Nẵng chỉ cách khách sạn khoảng 5-6 km, thuận tiện cho việc đón trả khách. Du khách có thể dễ dàng di chuyển bằng taxi, xe công nghệ hoặc thuê xe máy, xe đạp ngay tại khách sạn để khám phá thành phố.", "Da Nang International Airport is only about 5-6 km away, convenient for pick-up and drop-off. Guests can easily travel by taxi or ride-hailing, or rent a motorbike or bicycle right at the hotel to explore the city.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Khu vực Thanh Khê có nhiều quán ăn hải sản, chợ địa phương và cửa hàng tiện lợi. Từ khách sạn, du khách dễ dàng đến chợ Hàn, chợ Cồn, Bảo tàng Điêu khắc Chăm và các điểm vui chơi nổi tiếng của Đà Nẵng.", "The Thanh Khe area has many seafood eateries, local markets and convenience stores. From the hotel, guests can easily reach Han Market, Con Market, the Cham Sculpture Museum and Da Nang's popular attractions.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Lychee Hotel", "Why choose Lychee Hotel")),

                paragraph(ContentSection.STORY,
                                of("Với vị trí ven biển thuận tiện, phòng ốc sạch sẽ, đầy đủ tiện nghi cùng mức giá phải chăng và dịch vụ tận tình, Lychee Hotel là lựa chọn đáng cân nhắc cho kỳ nghỉ tại Đà Nẵng, dù bạn đi du lịch, công tác hay cùng gia đình.", "With a convenient coastal location, clean and well-equipped rooms, reasonable prices and attentive service, Lychee Hotel is a worthy choice for a stay in Da Nang, whether you travel for leisure, business or with family.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-lychee-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard Double 18 m², 1 giường đôi.", "Standard Double room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard Double 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Standard Double room 18 sqm, 1 double bed. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(18.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0)))
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
                                        .id("rt-lychee-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("600000"))
                                        .referencePricePerNight(new BigDecimal("857143"))
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
                                        .id("rt-lychee-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("690000"))
                                        .referencePricePerNight(new BigDecimal("985714"))
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
                        .id("rt-lychee-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior Twin 22 m², 2 giường đơn.", "Superior Twin room 22 sqm, 2 single beds."))
                        .description(of("Phòng Superior Twin 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior Twin room 22 sqm, 2 single beds. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(22.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f53701466932ff235ca40d2e23ca840e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lychee-superior-twin-prepaid-nonref")
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
                                        .id("rt-lychee-superior-twin-breakfast-flex")
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
                        .id("rt-lychee-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe Queen 26 m², 1 giường cỡ Queen.", "Deluxe Queen room 26 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe Queen 26 m², 1 giường cỡ Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe Queen room 26 sqm, 1 queen bed. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(26.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f53701466932ff235ca40d2e23ca840e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lychee-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("900000"))
                                        .referencePricePerNight(new BigDecimal("1285714"))
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
                                        .id("rt-lychee-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1035000"))
                                        .referencePricePerNight(new BigDecimal("1478571"))
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
                        .id("rt-lychee-deluxe-city-view")
                        .name(of("Deluxe City View", "Deluxe City View"))
                        .shortDescription(of("Phòng Deluxe City View 28 m², 1 giường cỡ King.", "Deluxe City View room 28 sqm, 1 king bed."))
                        .description(of("Phòng Deluxe City View 28 m², 1 giường cỡ King. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe City View room 28 sqm, 1 king bed. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(28.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f53701466932ff235ca40d2e23ca840e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lychee-deluxe-city-view-prepaid-nonref")
                                        .name(of("Deluxe City View - Không gồm bữa sáng", "Deluxe City View - Room only"))
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
                                        .id("rt-lychee-deluxe-city-view-breakfast-flex")
                                        .name(of("Deluxe City View - Bao gồm bữa sáng", "Deluxe City View - Breakfast included"))
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
                        .id("rt-lychee-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Family Room 36 m², 1 giường King và 1 giường đơn.", "Family Room room 36 sqm, 1 king bed and 1 single bed."))
                        .description(of("Phòng Family Room 36 m², 1 giường King và 1 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family Room room 36 sqm, 1 king bed and 1 single bed. Fully equipped with amenities for a comfortable stay."))
                        .areaSqm(36.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(5)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-e65ceb5619deda0cc7ea660522b3ea6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10026691-f53701466932ff235ca40d2e23ca840e.jpg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lychee-family-room-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1300000"))
                                        .referencePricePerNight(new BigDecimal("1857143"))
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
                                        .id("rt-lychee-family-room-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1495000"))
                                        .referencePricePerNight(new BigDecimal("2135714"))
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
                nearby("bai-bien-my-khe", of("Bãi biển Mỹ Khê", "My Khe Beach"), of("Bãi biển", "Beach"), 4500.0),
                nearby("bai-bien-my-an", of("Bãi biển Mỹ An", "My An Beach"), of("Bãi biển", "Beach"), 6000.0),
                nearby("cau-song-han", of("Cầu sông Hàn", "Han River Bridge"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("cau-rong-da-nang", of("Cầu Rồng", "Dragon Bridge"), of("Địa điểm lân cận", "Nearby places"), 4000.0),
                nearby("cau-tinh-yeu", of("Cầu Tình Yêu", "Love Bridge"), of("Địa điểm lân cận", "Nearby places"), 4200.0),
                nearby("cho-han", of("Chợ Hàn", "Han Market"), of("Địa điểm lân cận", "Nearby places"), 3800.0),
                nearby("cho-con", of("Chợ Cồn", "Con Market"), of("Địa điểm lân cận", "Nearby places"), 2800.0),
                nearby("bao-tang-dieu-khac-cham", of("Bảo tàng Điêu khắc Chăm", "Cham Sculpture Museum"), of("Khác", "Other"), 4300.0),
                nearby("cong-vien-chau-a", of("Công viên Châu Á (Asia Park)", "Sun World Asia Park"), of("Trung tâm giải trí", "Entertainment hub"), 5500.0),
                nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Khác", "Other"), 9000.0),
                nearby("ban-dao-son-tra", of("Bán đảo Sơn Trà", "Son Tra Peninsula"), of("Khác", "Other"), 8000.0),
                nearby("chua-linh-ung", of("Chùa Linh Ứng", "Linh Ung Pagoda"), of("Khác", "Other"), 9500.0),
                nearby("dinh-ban-co", of("Đỉnh Bàn Cờ", "Ban Co Peak"), of("Khác", "Other"), 11000.0),
                nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 5500.0),
                nearby("ba-na-hills", of("Khu du lịch Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 30000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi miễn phí", "Restaurant, air conditioning, 24-hour front desk, parking, free WiFi"))
                                .distanceToCityCenterKm(3.5)
                                .popularAreaSummary(of("Đường biển Nguyễn Tất Thành, Cầu sông Hàn, Chợ Cồn", "Nguyen Tat Thanh coastal road, Han River Bridge, Con Market"))
                                .totalRooms(45)
                                .totalFloors(10)
                                .otherHighlightFacilities(of("Quầy bar, tiệm cà phê, đưa đón sân bay, cho thuê xe đạp, dịch vụ giặt ủi.", "Bar, coffee shop, airport transfer, bicycle rental, laundry service."))
                                .interestingPlacesSummary(of("Bãi biển Mỹ Khê, Cầu Rồng, Chợ Hàn", "My Khe Beach, Dragon Bridge, Han Market"))
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
                                                                .title(of("Đưa đón & di chuyển", "Transfer & transport"))
                                                                .content(of("Khách sạn hỗ trợ đưa đón sân bay (thu phí) và cho thuê xe đạp, xe hơi theo yêu cầu.", "The hotel offers airport transfer (surcharge) and bicycle/car rental on request."))
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
                faq(of("Những tiện ích tại Lychee Hotel?", "What amenities does Lychee Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Lychee Hotel?", "What are the check-in and check-out times at Lychee Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Lychee Hotel có phục vụ ăn sáng không?", "Does Lychee Hotel serve breakfast?"),
                        of("Có, Lychee Hotel phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, Lychee Hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Lychee Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Lychee Hotel have?"),
                        of("Lychee Hotel là khách sạn đạt tiêu chuẩn 3.0 sao.", "Lychee Hotel is a 3.0-star hotel.")),
                faq(of("Lychee Hotel có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does Lychee Hotel have?"),
                        of("Lychee Hotel có tổng cộng 10 tầng với 45 phòng.", "Lychee Hotel has a total of 10 floors with 45 rooms.")),
                faq(of("Lychee Hotel có chỗ đậu xe không?", "Does Lychee Hotel have parking?"),
                        of("Có, Lychee Hotel có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận chi tiết.", "Yes, Lychee Hotel provides parking. Please contact the hotel directly to confirm details.")),
                faq(of("Lychee Hotel có hỗ trợ nhận phòng sớm không?", "Does Lychee Hotel support early check-in?"),
                        of("Có, Lychee Hotel hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trước để được sắp xếp.", "Yes, Lychee Hotel supports early check-in subject to availability. Please contact in advance to arrange.")),
                faq(of("Lychee Hotel có cung cấp dịch vụ trả phòng trễ không?", "Does Lychee Hotel offer late check-out?"),
                        of("Có, Lychee Hotel hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trước để được sắp xếp.", "Yes, Lychee Hotel supports late check-out subject to availability. Please contact in advance to arrange.")),
                faq(of("Lychee Hotel có dịch vụ đưa đón sân bay không?", "Does Lychee Hotel offer airport transfer?"),
                        of("Có, Lychee Hotel cung cấp dịch vụ đưa đón sân bay (thu phí) theo yêu cầu. Sân bay quốc tế Đà Nẵng cách khách sạn khoảng 5-6 km.", "Yes, Lychee Hotel offers airport transfer (surcharge) on request. Da Nang International Airport is about 5-6 km away.")),
                faq(of("Lychee Hotel có mức giá là bao nhiêu?", "How much does Lychee Hotel cost?"),
                        of("Phòng tại Lychee Hotel có giá từ khoảng 600.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at Lychee Hotel start from around 600,000 VND per night. Prices may vary depending on the date and promotions.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("da-nang")
                                .cityName(of("Đà Nẵng", "Da Nang"))
                                .districtName(of("Thanh Khê", "Thanh Khe"))
                                .addressLine(of("435-437 Nguyễn Tất Thành, Thanh Khê, Đà Nẵng, Thành phố Đà Nẵng, Việt Nam, 550000", "435-437 Nguyen Tat Thanh, Thanh Khe District, Da Nang, Da Nang City, Vietnam, 550000"))
                                .location(new double[] { 108.20684198642738, 16.075800871243924 })
                                .name(of("Lychee Hotel", "Lychee Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao trên đường biển Nguyễn Tất Thành, Thanh Khê, Đà Nẵng.", "3-star hotel on coastal Nguyen Tat Thanh Street, Thanh Khe, Da Nang."))
                                .description(of("Lychee Hotel là khách sạn 3 sao tại Thanh Khê, Đà Nẵng, gần biển với phòng ốc sạch sẽ, nhà hàng, quầy bar và dịch vụ tận tình, phù hợp cho cả du lịch và công tác.", "Lychee Hotel is a 3-star hotel in Thanh Khe, Da Nang, near the beach with clean rooms, a restaurant, a bar and attentive service, suitable for both leisure and business."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("600000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("857143"))
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
