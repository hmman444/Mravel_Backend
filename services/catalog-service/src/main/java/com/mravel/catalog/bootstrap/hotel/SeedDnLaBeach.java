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
public class SeedDnLaBeach {

        @Bean
        CommandLineRunner seedDnLaBeachRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedDnLaBeach] start");
                        HotelDoc doc = hotelLaBeach();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedDnLaBeach] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedDnLaBeach] done");
                };
        }

        // =====================================================================
        // LA BEACH HOTEL (source: Traveloka hotelId 3000020001392; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelLaBeach() {
                String slug = "la-beach-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-9d41562895815e3ea951e31bb5ad45e8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-543c661916530c540d7dfde08a52df2b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-31dbfa3741d2dd0edcf10fa1f81c0f7b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-a6ae65a5578ff9a71e6a373e705b3cbc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-b8ff07cf0b7d5b50fc9a7f9d5f71df09.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-0de7a9cbb09c644ca825287eea17bb08.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-452ece01835570a71325cb332138fa31.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-59b1261c1452ceec190bac65e507c259.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-e6e565a29dbf9f3373715028529d2714.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-a3b2ee9a8ef753b1f5fba248606d3c57.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-17d408e46052ba3d532142209297476e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-f4be9b7b4a79be5f5217705c0406fd3b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-7e21a8b9bbe7af34166117c4f85017a4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-aade3c04acf2a76edb506b4e3249a1d2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-91c42f2d06cf8bcca6386b7dd6f8437a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 14));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.24223289999998, 16.074482 }),

                                heading(ContentSection.STORY, of("Giới thiệu chung về La Beach Hotel", "About La Beach Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("La Beach Hotel là khách sạn 3 sao tọa lạc tại số 22 Lê Bình, phường Phước Mỹ, quận Sơn Trà, Đà Nẵng – chỉ vài phút đi bộ tới bãi biển Mỹ Khê nổi tiếng. Với 31 phòng trải trên 9 tầng cùng dịch vụ thân thiện, khách sạn là lựa chọn lý tưởng cho cả khách du lịch nghỉ dưỡng lẫn khách công tác mong muốn một chốn lưu trú tiện nghi, gần biển.", "La Beach Hotel is a 3-star hotel located at 22 Le Binh, Phuoc My Ward, Son Tra District, Da Nang – just a few minutes' walk from the famous My Khe Beach. With 31 rooms across 9 floors and friendly service, the hotel is an ideal choice for both leisure travelers and business guests seeking a comfortable stay near the sea.")),

                                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & in-room amenities")),
                                paragraph(ContentSection.STORY,
                                                of("Khách sạn cung cấp các hạng phòng từ Standard, Superior đến Deluxe và Family, một số phòng có ban công nhìn ra thành phố hoặc hướng biển. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, phòng tắm vòi sen, nước nóng và Wi-Fi miễn phí, đáp ứng nhu cầu nghỉ ngơi thoải mái.", "The hotel offers room categories from Standard and Superior to Deluxe and Family, with some rooms featuring a balcony overlooking the city or the sea. Each room is equipped with air conditioning, a minibar, a work desk, a hair dryer, a shower bathroom, hot water and free Wi-Fi for a comfortable stay.")),

                                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                                paragraph(ContentSection.STORY,
                                                of("Nhà hàng của khách sạn phục vụ bữa sáng, bữa trưa và bữa tối với thực đơn món Việt và món Âu, không gian thoáng mát có máy lạnh. Du khách cũng có thể bắt đầu ngày mới với bữa sáng tươm tất trước khi khám phá biển Mỹ Khê và bán đảo Sơn Trà.", "The hotel restaurant serves breakfast, lunch and dinner with a menu of Vietnamese and European dishes in an airy, air-conditioned setting. Guests can start the day with a hearty breakfast before exploring My Khe Beach and the Son Tra Peninsula.")),

                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                                paragraph(ContentSection.STORY,
                                                of("La Beach Hotel có lễ tân 24 giờ, dịch vụ phòng, dịch vụ giặt ủi, giữ hành lý, thang máy, bãi đậu xe an ninh và sân thượng/sân hiên để thư giãn. Đội ngũ nhân viên đa ngôn ngữ sẵn sàng hỗ trợ đặt tour, thuê xe và đưa đón sân bay theo yêu cầu.", "La Beach Hotel features a 24-hour front desk, room service, laundry service, luggage storage, an elevator, secure parking and a terrace for relaxing. The multilingual staff is ready to assist with tour booking, car rental and airport transfer on request.")),

                                heading(ContentSection.STORY, of("Vị trí & biển Mỹ Khê", "Location & My Khe Beach")),
                                paragraph(ContentSection.STORY,
                                                of("Nằm trong khu Phước Mỹ sầm uất của quận Sơn Trà, khách sạn chỉ cách bãi biển Mỹ Khê vài trăm mét và thuận tiện di chuyển tới cầu Rồng, sông Hàn cùng các nhà hàng hải sản nổi tiếng của Đà Nẵng. Đây là điểm xuất phát lý tưởng cho mọi hành trình khám phá thành phố biển.", "Set in the bustling Phuoc My area of Son Tra District, the hotel is just a few hundred meters from My Khe Beach and offers easy access to Dragon Bridge, the Han River and Da Nang's famous seafood restaurants. It is an ideal base for exploring the coastal city.")),

                                heading(ContentSection.STORY, of("Di chuyển & giao thông", "Getting around")),
                                paragraph(ContentSection.STORY,
                                                of("Sân bay quốc tế Đà Nẵng cách khách sạn khoảng 15 phút lái xe, rất thuận tiện cho việc đến và đi. Khu vực xung quanh có nhiều taxi, xe công nghệ và dịch vụ thuê xe máy, giúp du khách dễ dàng khám phá bán đảo Sơn Trà, Ngũ Hành Sơn hay Bà Nà Hills.", "Da Nang International Airport is about a 15-minute drive from the hotel, very convenient for arrivals and departures. The surrounding area has plenty of taxis, ride-hailing services and motorbike rentals, making it easy to explore the Son Tra Peninsula, the Marble Mountains or Ba Na Hills.")),

                                heading(ContentSection.STORY, of("Khu vực xung quanh", "The neighborhood")),
                                paragraph(ContentSection.STORY,
                                                of("Phước Mỹ là khu phố ven biển nhộn nhịp với hàng loạt quán cà phê, nhà hàng hải sản, cửa hàng tiện lợi và siêu thị trong tầm đi bộ. Buổi tối, du khách có thể dạo bộ dọc bãi biển Mỹ Khê hoặc thưởng thức không khí sôi động của các quán bar gần đó.", "Phuoc My is a lively beachfront neighborhood with numerous cafes, seafood restaurants, convenience stores and supermarkets within walking distance. In the evening, guests can stroll along My Khe Beach or enjoy the vibrant atmosphere of nearby bars.")),

                                heading(ContentSection.STORY, of("Vì sao nên chọn La Beach Hotel", "Why choose La Beach Hotel")),
                                paragraph(ContentSection.STORY,
                                                of("Chọn La Beach Hotel, du khách có được vị trí đắc địa gần biển Mỹ Khê, phòng nghỉ tiện nghi với mức giá hợp lý và dịch vụ tận tâm theo chuẩn 3 sao. Đây là lựa chọn cân bằng giữa sự thoải mái, tiện lợi và chi phí cho kỳ nghỉ tại thành phố biển Đà Nẵng.", "Choosing La Beach Hotel gives travelers a prime location near My Khe Beach, comfortable rooms at a reasonable price and attentive 3-star service. It is a well-balanced choice of comfort, convenience and value for a holiday in the coastal city of Da Nang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe", "Car rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-labeach-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard Double 20 m², 1 giường đôi.", "Standard Double room 20 sqm, 1 double bed."))
                        .description(of("Phòng Standard Double rộng 20 m² với 1 giường đôi, trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái gần biển Mỹ Khê.", "Standard Double room of 20 sqm with 1 double bed, fully equipped for a comfortable stay near My Khe Beach."))
                        .areaSqm(20.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-0de7a9cbb09c644ca825287eea17bb08.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0)))
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
                                        .id("rt-labeach-standard-double-prepaid-nonref")
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
                                        .id("rt-labeach-standard-double-breakfast-flex")
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
                        .id("rt-labeach-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior Twin 24 m², 2 giường đơn.", "Superior Twin room 24 sqm, 2 single beds."))
                        .description(of("Phòng Superior Twin rộng 24 m² với 2 giường đơn, trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái gần biển Mỹ Khê.", "Superior Twin room of 24 sqm with 2 single beds, fully equipped for a comfortable stay near My Khe Beach."))
                        .areaSqm(24.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(9)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-452ece01835570a71325cb332138fa31.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-59b1261c1452ceec190bac65e507c259.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-labeach-superior-twin-prepaid-nonref")
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
                                        .id("rt-labeach-superior-twin-breakfast-flex")
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
                        .id("rt-labeach-deluxe-city-view")
                        .name(of("Deluxe City View", "Deluxe City View"))
                        .shortDescription(of("Phòng Deluxe City View 28 m², 1 giường cỡ Queen.", "Deluxe City View room 28 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe City View rộng 28 m² với 1 giường cỡ Queen, trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái gần biển Mỹ Khê.", "Deluxe City View room of 28 sqm with 1 queen bed, fully equipped for a comfortable stay near My Khe Beach."))
                        .areaSqm(28.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-e6e565a29dbf9f3373715028529d2714.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-a3b2ee9a8ef753b1f5fba248606d3c57.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-labeach-deluxe-city-view-prepaid-nonref")
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
                                        .id("rt-labeach-deluxe-city-view-breakfast-flex")
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
                        .id("rt-labeach-family")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Family Room 34 m², 1 giường đôi và 1 giường đơn.", "Family Room room 34 sqm, 1 double bed and 1 single bed."))
                        .description(of("Phòng Family Room rộng 34 m² với 1 giường đôi và 1 giường đơn, trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái gần biển Mỹ Khê.", "Family Room room of 34 sqm with 1 double bed and 1 single bed, fully equipped for a comfortable stay near My Khe Beach."))
                        .areaSqm(34.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường đôi và 1 giường đơn", "1 double bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(4)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-0de7a9cbb09c644ca825287eea17bb08.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-452ece01835570a71325cb332138fa31.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20001392-59b1261c1452ceec190bac65e507c259.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-labeach-family-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
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
                                        .id("rt-labeach-family-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
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
                        .build());

                List<NearbyPlace> nearby = List.of(
                nearby("bai-bien-my-khe", of("Bãi biển Mỹ Khê", "My Khe Beach"), of("Bãi biển", "Beach"), 400.0),
                nearby("bai-bien-my-an", of("Bãi biển Mỹ An", "My An Beach"), of("Bãi biển", "Beach"), 1800.0),
                nearby("ban-dao-son-tra", of("Bán đảo Sơn Trà", "Son Tra Peninsula"), of("Địa điểm lân cận", "Nearby places"), 7000.0),
                nearby("chua-linh-ung", of("Chùa Linh Ứng", "Linh Ung Pagoda"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("cau-rong", of("Cầu Rồng", "Dragon Bridge"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("cau-song-han", of("Cầu sông Hàn", "Han River Bridge"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
                nearby("cau-tinh-yeu", of("Cầu Tình Yêu", "Love Bridge"), of("Địa điểm lân cận", "Nearby places"), 2800.0),
                nearby("cho-han", of("Chợ Hàn", "Han Market"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("cho-con", of("Chợ Cồn", "Con Market"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("bao-tang-dieu-khac-cham", of("Bảo tàng Điêu khắc Chăm", "Cham Sculpture Museum"), of("Khác", "Other"), 3200.0),
                nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
                nearby("cong-vien-chau-a", of("Công viên Châu Á (Asia Park)", "Sun World Asia Park"), of("Trung tâm giải trí", "Entertainment hub"), 5500.0),
                nearby("dinh-ban-co", of("Đỉnh Bàn Cờ", "Ban Co Peak"), of("Khác", "Other"), 11000.0),
                nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 6000.0),
                nearby("ba-na-hills", of("Khu du lịch Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 30000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy, WiFi miễn phí", "Restaurant, air conditioning, 24-hour front desk, parking, elevator, free WiFi"))
                                .distanceToCityCenterKm(3.0)
                                .popularAreaSummary(of("Bãi biển Mỹ Khê, Bán đảo Sơn Trà, Cầu Rồng", "My Khe Beach, Son Tra Peninsula, Dragon Bridge"))
                                .totalRooms(31)
                                .totalFloors(9)
                                .otherHighlightFacilities(of("Dịch vụ phòng, giặt ủi, giữ hành lý, đưa đón sân bay, hỗ trợ đặt tour.", "Room service, laundry, luggage storage, airport transfer, tour booking assistance."))
                                .interestingPlacesSummary(of("Bãi biển Mỹ Khê, Cầu Rồng, Bán đảo Sơn Trà", "My Khe Beach, Dragon Bridge, Son Tra Peninsula"))
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
                                                                .title(of("Đậu xe", "Parking"))
                                                                .content(of("Khách sạn có chỗ đậu xe an ninh; vui lòng liên hệ để biết thêm chi tiết.", "The hotel offers secure parking; please contact for more details."))
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
                faq(of("Những tiện ích tại La Beach Hotel?", "What amenities does La Beach Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy, dịch vụ phòng và WiFi miễn phí (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a restaurant, a 24-hour front desk, parking, an elevator, room service and free WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của La Beach Hotel?", "What are the check-in and check-out times at La Beach Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("La Beach Hotel có phục vụ ăn sáng không?", "Does La Beach Hotel serve breakfast?"),
                        of("Có, khách sạn có phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không, có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn La Beach Hotel đạt tiêu chuẩn mấy sao?", "What star rating does La Beach Hotel have?"),
                        of("La Beach Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "La Beach Hotel is a 3-star hotel.")),
                faq(of("La Beach Hotel có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does La Beach Hotel have?"),
                        of("Khách sạn có tổng cộng 9 tầng với 31 phòng.", "The hotel has a total of 9 floors with 31 rooms.")),
                faq(of("La Beach Hotel có chỗ đậu xe không?", "Does La Beach Hotel have parking?"),
                        of("Có, khách sạn có chỗ đậu xe an ninh. Vui lòng liên hệ trực tiếp với khách sạn để biết thêm chi tiết.", "Yes, the hotel has secure parking. Please contact the hotel directly for more details.")),
                faq(of("La Beach Hotel có hỗ trợ nhận phòng sớm/trả phòng muộn không?", "Does La Beach Hotel support early check-in / late check-out?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm và trả phòng muộn tùy tình trạng phòng trống; vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel supports early check-in and late check-out subject to availability; please contact in advance to arrange.")),
                faq(of("La Beach Hotel có dịch vụ đưa đón sân bay không?", "Does La Beach Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí) theo yêu cầu; sân bay Đà Nẵng cách khoảng 15 phút lái xe.", "Yes, the hotel offers airport transfer (surcharge) on request; Da Nang Airport is about a 15-minute drive away.")),
                faq(of("La Beach Hotel có gần biển không?", "Is La Beach Hotel close to the beach?"),
                        of("Có, khách sạn chỉ cách bãi biển Mỹ Khê vài phút đi bộ, rất thuận tiện để tắm biển và dạo chơi ven biển.", "Yes, the hotel is just a few minutes' walk from My Khe Beach, very convenient for swimming and seaside strolls.")),
                faq(of("La Beach Hotel có mức giá là bao nhiêu?", "How much does La Beach Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("da-nang")
                                .cityName(of("Đà Nẵng", "Da Nang"))
                                .districtName(of("Sơn Trà", "Son Tra"))
                                .wardName(of("Phước Mỹ", "Phuoc My"))
                                .addressLine(of("22 Lê Bình, Phước Mỹ, Sơn Trà, Đà Nẵng, Việt Nam, 550000", "22 Le Binh, Phuoc My Ward, Son Tra District, Da Nang, Vietnam, 550000"))
                                .location(new double[] { 108.24223289999998, 16.074482 })
                                .name(of("La Beach Hotel", "La Beach Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao tại Sơn Trà, Đà Nẵng, vài phút tới biển Mỹ Khê.", "3-star hotel in Son Tra, Da Nang, minutes from My Khe Beach."))
                                .description(of("La Beach Hotel là khách sạn 3 sao tại số 22 Lê Bình, Phước Mỹ, Sơn Trà, Đà Nẵng – gần bãi biển Mỹ Khê, với phòng nghỉ tiện nghi, nhà hàng, lễ tân 24h và dịch vụ thân thiện.", "La Beach Hotel is a 3-star hotel at 22 Le Binh, Phuoc My, Son Tra, Da Nang – near My Khe Beach, with comfortable rooms, a restaurant, a 24-hour front desk and friendly service."))
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
