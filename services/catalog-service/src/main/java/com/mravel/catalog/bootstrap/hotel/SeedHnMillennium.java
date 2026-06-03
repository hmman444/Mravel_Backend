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
public class SeedHnMillennium {

        @Bean
        CommandLineRunner seedHnMillenniumRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHnMillennium] start");
                        HotelDoc doc = hotelMillennium();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHnMillennium] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHnMillennium] done");
                };
        }

        // =====================================================================
        // MILLENNIUM HANOI HOTEL
        // (source: Traveloka hotelId 1000001037181; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelMillennium() {
                String slug = "millennium-hanoi-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-5628a09639c9b86e9ada793c2e991bad.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-38cb1839068f2d3d0902027201552320.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Sảnh", "Lobby", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-ab60202fce464f75df9bb2ee7f82931c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Sảnh", "Lobby", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-f4cee6a7c23c3f2530fac462de5b35e4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-eac77a685dacf8066cb784e587d4704c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6621eaa09edf5d05634c59e1173ab71c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-95dbbd24d6582ce02455b0333ef0eb0b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-b65459b970e99b4cf608196ab3562779.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-0d44fc33ebd12a997604a416336ce17f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Sảnh", "Lobby", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-9625baa0b3dd986058fbf06cabc10ee4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-7f60a1fcd98444fc76c7b65c334fbbc2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-8c2bbfeccf0a352659b6339a01f6ed0c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6e699d671d88b8722f0890e56a1627b0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-217cc7d442824d832aff1cf44c5cf8e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6d2246b8e44c99e53aff2e14969b2dcd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-2448a691498ec2451effd560833eb1f1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6e36afbc176176e8ee1cde1363099b54.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-b5bcb9fac6893387875ccf3f988281cb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 17));

                List<ContentBlock> content = List.of(
                                mapBlock(ContentSection.OVERVIEW, new double[] { 105.84286944937537, 21.02809458055101 }),
                                heading(ContentSection.STORY, of("Giới thiệu chung về Millennium Hanoi Hotel", "About Millennium Hanoi Hotel")),
                                paragraph(ContentSection.STORY,
                                of("Millennium Hanoi Hotel là khách sạn 3 sao tọa lạc tại 246B Hàng Bông, phường Cửa Nam, quận Hoàn Kiếm, ngay trung tâm phố cổ Hà Nội. Với 30 phòng nghỉ trải trên 9 tầng, khách sạn mang đến không gian ấm cúng, sạch sẽ và thuận tiện cho cả khách du lịch lẫn khách công tác muốn khám phá thủ đô ngàn năm văn hiến.", "Millennium Hanoi Hotel is a 3-star hotel located at 246B Hang Bong, Cua Nam Ward, Hoan Kiem District, right in the heart of Hanoi's Old Quarter. With 30 rooms across 9 floors, the hotel offers a cozy, clean and convenient base for both leisure and business travelers exploring the thousand-year-old capital.")),
                                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),
                                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp các hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình, phù hợp cho nhiều nhu cầu lưu trú. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, két an toàn, phòng tắm vòi sen với nước nóng và Wi-Fi miễn phí, đảm bảo sự thoải mái trong suốt kỳ nghỉ.", "The hotel offers room categories ranging from Standard, Superior and Deluxe to Family rooms, suiting a variety of stays. Each room is equipped with air conditioning, a minibar, a work desk, a hair dryer, a safe, a shower bathroom with hot water and free Wi-Fi, ensuring comfort throughout your stay.")),
                                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng với thực đơn gọi món và món tự chọn, mang đậm hương vị Việt Nam và quốc tế. Quầy cà phê/trà tại sảnh là nơi lý tưởng để khách thư giãn, trong khi vô số quán ăn, quán cà phê đặc trưng của phố cổ chỉ cách vài bước chân.", "The hotel restaurant serves breakfast with both à la carte and buffet options, featuring Vietnamese and international flavors. The lobby coffee and tea corner is a perfect spot to relax, while countless eateries and signature Old Quarter cafes are just steps away.")),
                                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                                paragraph(ContentSection.STORY,
                                of("Millennium Hanoi Hotel có lễ tân 24 giờ, thang máy, dịch vụ giặt ủi, giữ hành lý, thu đổi ngoại tệ, cho thuê xe đạp và xe hơi, dịch vụ đưa đón sân bay (thu phí) cùng quầy hỗ trợ đặt tour. Đội ngũ nhân viên thân thiện, đa ngôn ngữ luôn sẵn sàng hỗ trợ khách 24/7.", "Millennium Hanoi Hotel features a 24-hour front desk, an elevator, laundry service, luggage storage, currency exchange, bicycle and car rental, airport transfer (surcharge) and a tour desk. The friendly, multilingual staff is ready to assist guests around the clock.")),
                                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),
                                paragraph(ContentSection.STORY,
                                of("Nằm trong khu phố cổ Hoàn Kiếm, khách sạn chỉ cách Hồ Hoàn Kiếm, Nhà thờ Lớn và chợ Đồng Xuân vài phút đi bộ. Văn Miếu - Quốc Tử Giám, Hoàng thành Thăng Long, Lăng Chủ tịch Hồ Chí Minh và Nhà hát Lớn Hà Nội đều nằm trong bán kính di chuyển thuận tiện.", "Set in the Hoan Kiem Old Quarter, the hotel is just minutes' walk from Hoan Kiem Lake, St Joseph's Cathedral and Dong Xuan Market. The Temple of Literature, Imperial Citadel of Thang Long, Ho Chi Minh Mausoleum and Hanoi Opera House are all within easy reach.")),
                                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
                                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách dễ dàng tản bộ khám phá khu phố cổ hoặc đi xe đạp, taxi đến các điểm tham quan. Sân bay quốc tế Nội Bài cách khoảng 25 km, mất chừng 40-50 phút lái xe; khách sạn hỗ trợ đặt dịch vụ đưa đón sân bay theo yêu cầu.", "From the hotel, guests can easily stroll through the Old Quarter or take a bicycle or taxi to attractions. Noi Bai International Airport is about 25 km away, roughly a 40-50 minute drive; the hotel can arrange airport transfer on request.")),
                                heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),
                                paragraph(ContentSection.STORY,
                                of("Phố Hàng Bông và các con phố lân cận nổi tiếng với hàng quán ăn uống, cửa hàng lưu niệm, tiệm may và phố đi bộ Hồ Gươm cuối tuần. Khu vực sôi động cả ngày lẫn đêm, mang đến trải nghiệm văn hóa và mua sắm đậm chất Hà Nội xưa.", "Hang Bong Street and its surroundings are famous for eateries, souvenir shops, tailors and the weekend Hoan Kiem Walking Street. The area is lively day and night, offering a culture and shopping experience rich in old-Hanoi charm.")),
                                heading(ContentSection.STORY, of("Vì sao nên chọn Millennium Hanoi Hotel", "Why choose Millennium Hanoi Hotel")),
                                paragraph(ContentSection.STORY,
                                of("Chọn Millennium Hanoi Hotel, du khách có được vị trí trung tâm phố cổ tuyệt vời, phòng nghỉ tiện nghi với mức giá hợp lý cùng dịch vụ chu đáo. Đây là lựa chọn lý tưởng cho những ai muốn vừa khám phá di sản, ẩm thực Hà Nội, vừa thuận tiện di chuyển và mua sắm.", "Choosing Millennium Hanoi Hotel gives travelers an excellent Old Quarter location, comfortable rooms at reasonable rates and attentive service. It is an ideal choice for those who want to explore Hanoi's heritage and cuisine while staying close to shopping and transport.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("coffee_shop", of("Cà phê/trà tại sảnh", "Coffee/tea in lobby"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("babysitting", of("Dịch vụ giữ trẻ (thu phí)", "Babysitting (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-millennium-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi.", "Standard room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa trung tâm Hà Nội.", "Standard room 18 sqm, 1 double bed. Fully equipped for a comfortable stay in central Hanoi."))
                        .areaSqm(18.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-5628a09639c9b86e9ada793c2e991bad.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6621eaa09edf5d05634c59e1173ab71c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-millennium-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("800000"))
                                        .referencePricePerNight(new BigDecimal("1142857"))
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
                                        .id("rt-millennium-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("920000"))
                                        .referencePricePerNight(new BigDecimal("1314286"))
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
                        .id("rt-millennium-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 22 m², 2 giường đơn.", "Superior room 22 sqm, 2 single beds."))
                        .description(of("Phòng Superior 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa trung tâm Hà Nội.", "Superior room 22 sqm, 2 single beds. Fully equipped for a comfortable stay in central Hanoi."))
                        .areaSqm(22.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-95dbbd24d6582ce02455b0333ef0eb0b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-b65459b970e99b4cf608196ab3562779.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-millennium-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("950000"))
                                        .referencePricePerNight(new BigDecimal("1357143"))
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
                                        .id("rt-millennium-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1092500"))
                                        .referencePricePerNight(new BigDecimal("1560714"))
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
                        .id("rt-millennium-deluxe-double")
                        .name(of("Deluxe Double", "Deluxe Double"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen.", "Deluxe room 26 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe 26 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa trung tâm Hà Nội.", "Deluxe room 26 sqm, 1 queen bed. Fully equipped for a comfortable stay in central Hanoi."))
                        .areaSqm(26.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-9625baa0b3dd986058fbf06cabc10ee4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-7f60a1fcd98444fc76c7b65c334fbbc2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "BLACKOUT_CURTAINS",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-millennium-deluxe-double-prepaid-nonref")
                                        .name(of("Deluxe Double - Không gồm bữa sáng", "Deluxe Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1150000"))
                                        .referencePricePerNight(new BigDecimal("1642857"))
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
                                        .id("rt-millennium-deluxe-double-breakfast-flex")
                                        .name(of("Deluxe Double - Bao gồm bữa sáng", "Deluxe Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1322500"))
                                        .referencePricePerNight(new BigDecimal("1889286"))
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
                        .id("rt-millennium-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 32 m², 1 giường Queen + 1 giường đơn.", "Family room 32 sqm, 1 queen + 1 single bed."))
                        .description(of("Phòng Gia đình 32 m², 1 giường Queen + 1 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa trung tâm Hà Nội.", "Family room 32 sqm, 1 queen + 1 single bed. Fully equipped for a comfortable stay in central Hanoi."))
                        .areaSqm(32.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(4)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-8c2bbfeccf0a352659b6339a01f6ed0c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-6e699d671d88b8722f0890e56a1627b0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20016481-217cc7d442824d832aff1cf44c5cf8e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                "BLACKOUT_CURTAINS",
                                "SEATING_AREA"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-millennium-family-room-prepaid-nonref")
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
                                        .id("rt-millennium-family-room-breakfast-flex")
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
                nearby("ho-hoan-kiem", of("Hồ Hoàn Kiếm", "Hoan Kiem Lake"), of("Địa điểm lân cận", "Nearby places"), 700.0),
                nearby("pho-co-ha-noi", of("Phố cổ Hà Nội", "Old Quarter"), of("Địa điểm lân cận", "Nearby places"), 500.0),
                nearby("nha-tho-lon", of("Nhà thờ Lớn", "St Joseph's Cathedral"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("van-mieu-quoc-tu-giam", of("Văn Miếu - Quốc Tử Giám", "Temple of Literature"), of("Địa điểm lân cận", "Nearby places"), 1800.0),
                nearby("lang-chu-tich-ho-chi-minh", of("Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("chua-mot-cot", of("Chùa Một Cột", "One Pillar Pagoda"), of("Địa điểm lân cận", "Nearby places"), 2600.0),
                nearby("hoang-thanh-thang-long", of("Hoàng thành Thăng Long", "Imperial Citadel of Thang Long"), of("Địa điểm lân cận", "Nearby places"), 2100.0),
                nearby("nha-hat-lon-ha-noi", of("Nhà hát Lớn Hà Nội", "Hanoi Opera House"), of("Trung tâm giải trí", "Entertainment hub"), 1500.0),
                nearby("ho-tay", of("Hồ Tây", "West Lake"), of("Địa điểm lân cận", "Nearby places"), 3800.0),
                nearby("chua-tran-quoc", of("Chùa Trấn Quốc", "Tran Quoc Pagoda"), of("Địa điểm lân cận", "Nearby places"), 4000.0),
                nearby("cau-long-bien", of("Cầu Long Biên", "Long Bien Bridge"), of("Địa điểm lân cận", "Nearby places"), 1900.0),
                nearby("pho-di-bo-ho-guom", of("Phố đi bộ Hồ Gươm", "Hoan Kiem Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 800.0),
                nearby("cho-dong-xuan", of("Chợ Đồng Xuân", "Dong Xuan Market"), of("Địa điểm lân cận", "Nearby places"), 1400.0),
                nearby("bao-tang-dan-toc-hoc", of("Bảo tàng Dân tộc học", "Museum of Ethnology"), of("Khác", "Other"), 6500.0),
                nearby("san-bay-noi-bai", of("Sân bay Nội Bài", "Noi Bai Airport"), of("Trung tâm giao thông", "Transport hub"), 25000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nha hang, le tan 24h, may lanh, WiFi mien phi, thang may", "Restaurant, 24-hour front desk, air conditioning, free WiFi, elevator"))
                                .distanceToCityCenterKm(0.7)
                                .popularAreaSummary(of("Pho co Ha Noi, Ho Hoan Kiem, Nha tho Lon", "Old Quarter, Hoan Kiem Lake, St Joseph's Cathedral"))
                                .totalRooms(30)
                                .totalFloors(9)
                                .otherHighlightFacilities(of("Dua don san bay, cho thue xe dap/xe hoi, giat ui, thu doi ngoai te, ho tro dat tour.", "Airport transfer, bicycle/car rental, laundry, currency exchange, tour desk."))
                                .interestingPlacesSummary(of("Pho co Ha Noi, Van Mieu - Quoc Tu Giam, Hoang thanh Thang Long", "Old Quarter, Temple of Literature, Imperial Citadel of Thang Long"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thoi gian nhan phong/tra phong", "Check-in/check-out time"))
                                                                .content(of("Gio nhan phong: tu 14:00. Gio tra phong: truoc 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.LATE_CHECKOUT)
                                                                .title(of("Tra phong tre", "Late check-out"))
                                                                .content(of("Ho tro tra phong tre tuy tinh trang phong trong; vui long lien he truoc.", "Late check-out is subject to availability; please contact in advance."))
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
                                                                .title(of("Tre em & gia dinh", "Children & family"))
                                                                .content(of("Khach san than thien voi gia dinh, co phong Gia dinh va dich vu giu tre (thu phi).", "The hotel is family-friendly with Family rooms and babysitting (surcharge)."))
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
                faq(of("Những tiện ích tại Millennium Hanoi Hotel?", "What amenities does Millennium Hanoi Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, thang máy, WiFi, giặt ủi, thu đổi ngoại tệ và đưa đón sân bay (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, elevator, WiFi, laundry, currency exchange and airport transfer (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Millennium Hanoi Hotel?", "What are the check-in and check-out times at Millennium Hanoi Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Millennium Hanoi Hotel có phục vụ ăn sáng không?", "Does Millennium Hanoi Hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn Millennium Hanoi Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Millennium Hanoi Hotel have?"),
                        of("Millennium Hanoi Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Millennium Hanoi Hotel is a 3-star hotel.")),
                faq(of("Millennium Hanoi Hotel có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does Millennium Hanoi Hotel have?"),
                        of("Khách sạn có tổng cộng 9 tầng với 30 phòng.", "The hotel has a total of 9 floors with 30 rooms.")),
                faq(of("Millennium Hanoi Hotel có cung cấp dịch vụ trả phòng trễ không?", "Does Millennium Hanoi Hotel offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Millennium Hanoi Hotel có giá phòng là bao nhiêu?", "How much does Millennium Hanoi Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 800.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 800,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có nằm gần phố cổ và Hồ Hoàn Kiếm không?", "Is the hotel near the Old Quarter and Hoan Kiem Lake?"),
                        of("Có, khách sạn nằm tại phố Hàng Bông, quận Hoàn Kiếm, chỉ cách Hồ Hoàn Kiếm và khu phố cổ vài phút đi bộ.", "Yes, the hotel is on Hang Bong Street in Hoan Kiem District, just a few minutes' walk from Hoan Kiem Lake and the Old Quarter.")),
                faq(of("Millennium Hanoi Hotel có dịch vụ đưa đón sân bay không?", "Does Millennium Hanoi Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí). Sân bay quốc tế Nội Bài cách khoảng 25 km.", "Yes, the hotel offers airport transfer (surcharge). Noi Bai International Airport is about 25 km away.")),
                faq(of("Khách sạn có cho thuê xe đạp hoặc xe hơi không?", "Does the hotel offer bicycle or car rental?"),
                        of("Có, khách sạn cung cấp dịch vụ cho thuê xe đạp và cho thuê xe hơi, thuận tiện để khám phá Hà Nội.", "Yes, the hotel offers bicycle hire and car hire, convenient for exploring Hanoi.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ha-noi")
                                .cityName(of("Ha Noi", "Hanoi"))
                                .districtName(of("Hoan Kiem", "Hoan Kiem"))
                                .wardName(of("Cua Nam", "Cua Nam"))
                                .addressLine(of("246B Hàng Bông, Cửa  Nam, Quận Hoàn Kiếm, Hà Nội, Việt Nam, 100000", "246B Hang Bong , Cua Nam Ward, Hoan Kiem District, Hanoi, Vietnam, 100000"))
                                .location(new double[] { 105.84286944937537, 21.02809458055101 })
                                .name(of("Millennium Hanoi Hotel", "Millennium Hanoi Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khach san 3 sao tai pho co Hoan Kiem, trung tam Ha Noi.", "3-star hotel in the Hoan Kiem Old Quarter, central Hanoi."))
                                .description(of("Millennium Hanoi Hotel la khach san 3 sao tai 246B Hang Bong, quan Hoan Kiem, ngay trung tam pho co Ha Noi, mang den phong nghi tien nghi, dich vu chu dao va vi tri thuan tien de kham pha thu do.", "Millennium Hanoi Hotel is a 3-star hotel at 246B Hang Bong, Hoan Kiem District, in the heart of Hanoi's Old Quarter, offering comfortable rooms, attentive service and a convenient base to explore the capital."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("800000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1142857"))
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
