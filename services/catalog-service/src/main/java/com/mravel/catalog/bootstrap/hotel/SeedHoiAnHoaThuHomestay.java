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
import com.mravel.catalog.model.doc.HotelDoc.ReviewStats;
import com.mravel.catalog.model.doc.HotelDoc.RoomType;
import com.mravel.catalog.model.doc.HotelDoc.TaxAndFeeConfig;
import com.mravel.catalog.repository.HotelDocRepository;

import static com.mravel.catalog.utils.hotel.HotelContentBlocks.*;
import static com.mravel.catalog.utils.hotel.HotelImages.*;
import static com.mravel.catalog.utils.hotel.HotelSeedHelpers.*;
import static com.mravel.catalog.utils.hotel.Localized.of;
import static com.mravel.catalog.utils.hotel.Localized.vi;

@Configuration
public class SeedHoiAnHoaThuHomestay {

        @Bean
        CommandLineRunner seedHoiAnHoaThuHomestayRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHoiAnHoaThuHomestay] start");

                        HotelDoc doc = hotelHoaThuHomestay();

                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));

                        repo.save(doc);
                        System.out.println(">>> [SeedHoiAnHoaThuHomestay] upsert: " + doc.getSlug());

                        System.out.println(">>> [SeedHoiAnHoaThuHomestay] done");
                };
        }

        // =====================================================================
        // HOA THU HOMESTAY
        // =====================================================================

        private static HotelDoc hotelHoaThuHomestay() {
                String slug = "hoa-thu-homestay";

                // NOTE: Ảnh Traveloka không lấy được URL từ screenshot.
                // Bạn gửi URL ảnh (hoặc list imagekit) mình sẽ thay chuẩn y như Bespoke.
                List<Image> images = List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-622010c1d732322ef275ea1333a8614c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Mặt tiền Hoa Thu Homestay (ban đêm)", "Hoa Thu Homestay facade (at night)", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-74d37d4e17f53feba7551cff6d02bf71.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Sảnh chờ / khu vực tiếp khách", "Lobby / guest reception area", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-065c49820595582c998cdb69c258cc84.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng ngủ (không gian nghỉ ngơi)", "Bedroom (rest area)", false, 2),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-1d6348ddb05dab89abe02caf37805acb.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng tắm (vòi tắm đứng)", "Bathroom (standing shower)", false, 3),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-f278967ec074e669396254d9384a4c22.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Không gian chung (khu sinh hoạt)", "Common area (living space)", false, 4),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c086c987dc21336b52a2e34998b30c9c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khung cảnh phố)", "Surrounding area (street view)", false, 5),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-e97ea4bd8a6daadf63052fd961d7c03c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (phố đêm Hội An)", "Surrounding area (Hoi An at night)", false, 6),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-60e9a96a053d3eb7c3bbd876e47928a8.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (đèn lồng & không khí phố cổ)", "Surrounding area (lanterns & ancient town ambiance)", false, 7),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-2519182e7fab03b88099ce66340b021d.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khu mua sắm/đi bộ)", "Surrounding area (shopping/walking street)", false, 8),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-316e6ea68ab6f32d21288f2544ea9a0a.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (góc phố gần homestay)", "Surrounding area (street corner near the homestay)", false, 9),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc6f1979e75ba2139d9423e7c7d538ed.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khung cảnh về đêm)", "Surrounding area (night scenery)", false, 10),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c8a3b95dd2fdabc0aed51f7e9a81b7ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (điểm tham quan lân cận)", "Surrounding area (nearby attractions)", false, 11));

                // --------- Content (dựa theo modal “Giới thiệu Hoa Thu Homestay”) ----------
                List<ContentBlock> content = List.of(
                                // Map block: tọa độ xấp xỉ khu Minh An - phố cổ Hội An (bạn gửi toạ độ chuẩn
                                // mình thay)
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.3270, 15.8790 }),

                                // STORY: (từ phần mô tả dài trong trang)
                                heading(ContentSection.STORY, of("Mô tả chung về Hoa Thu Homestay", "About Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("Hoa Thu Homestay là một trong những homestay thú vị nhất tại tỉnh Quảng Nam, Hội An. Nằm ở vị trí 80 Ngô Quyền, Hội An, Tỉnh Quảng Nam, Thành phố Đà Nẵng, Việt Nam, 560000, các du khách đến đây sẽ cảm nhận được sự yên bình và thoải mái từ phong cách thiết kế đơn giản nhưng tinh tế. Được đánh giá với tiêu chuẩn 1.5 sao, Hoa Thu Homestay mang đến cho du khách một trải nghiệm nghỉ dưỡng hòa quyện giữa văn hóa địa phương và mức độ tiện nghi hiện đại.",
                                                                "Hoa Thu Homestay is one of the most charming homestays in Quang Nam Province, Hoi An. Located at 80 Ngo Quyen, Hoi An, Quang Nam Province, Da Nang City, Vietnam, 560000, guests will feel the peace and comfort of its simple yet refined design. Rated to a 1.5-star standard, Hoa Thu Homestay offers visitors a stay that blends local culture with modern comfort.")),
                                heading(ContentSection.STORY, of("Các loại phòng tại Hoa Thu Homestay", "Room types at Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("Hoa Thu Homestay cung cấp một loạt các loại phòng phong phú phù hợp với nhiều đối tượng khách hàng khác nhau. Mỗi căn phòng đều được trang bị đầy đủ tiện nghi cần thiết, đảm bảo đem lại sự thoải mái tối đa cho kỳ nghỉ của bạn. Với thiết kế mở và sử dụng nhiều vật liệu tự nhiên, mỗi phòng như một không gian riêng tư để thư giãn sau những ngày dài khám phá.",
                                                                "Hoa Thu Homestay offers a diverse range of room types suited to many different kinds of guests. Each room is fully equipped with the essential amenities to ensure maximum comfort throughout your stay. With an open design and abundant use of natural materials, every room feels like a private space to relax after long days of exploring.")),
                                heading(ContentSection.STORY, of("Điểm Tham Quan Gần Hoa Thu Homestay", "Attractions near Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("Khi lưu trú tại Hoa Thu Homestay, bạn dễ dàng tiếp cận các điểm tham quan nổi bật của Quảng Nam và Hội An. Phố cổ Hội An nằm cách đó chỉ khoảng 10 phút lái xe, cho phép bạn khám phá những con phố đầy màu sắc và văn hóa độc đáo. Ngoài ra, chỉ cần một quãng đi bộ ngắn là bạn đã có thể đến bãi biển An Bàng, nơi lý tưởng để tận hưởng cảnh sắc thiên nhiên và không khí biển trong lành.",
                                                                "Staying at Hoa Thu Homestay gives you easy access to the highlight attractions of Quang Nam and Hoi An. Hoi An Ancient Town is only about a 10-minute drive away, letting you explore its colorful streets and unique culture. In addition, just a short walk brings you to An Bang Beach, an ideal spot to enjoy natural scenery and fresh sea air.")),
                                heading(ContentSection.STORY, of("Cơ Sở Vật Chất Nổi Bật Tại Hoa Thu Homestay", "Standout facilities at Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("Tại Hoa Thu Homestay, du khách sẽ hài lòng với các tiện ích đa dạng. Hồ bơi ngoài trời rộng rãi là điểm nhấn lý tưởng để thư giãn và tận hưởng ánh nắng mặt trời. Không chỉ dừng lại ở đó, khu vực nhà hàng và quầy bar tại chỗ cũng phục vụ nhiều món ăn và đồ uống hấp dẫn, đem lại những trải nghiệm ẩm thực đẳng cấp mà không cần phải đi xa.",
                                                                "At Hoa Thu Homestay, guests will be delighted by a variety of facilities. The spacious outdoor pool is an ideal highlight for relaxing and soaking up the sun. Beyond that, the on-site restaurant and bar also serve a range of appealing dishes and drinks, delivering refined dining experiences without having to go far.")),
                                heading(ContentSection.STORY, of("Phương Tiện Giao Thông Công Cộng Gần Hoa Thu Homestay", "Public transport near Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("Để di chuyển dễ dàng hơn, Hoa Thu Homestay nằm gần ga xe buýt chính của Hội An. Qua đoạn đường bộ khoảng 15 phút hoặc 5 phút đi xe đạp, bạn sẽ đến được khu vực giao thông công cộng trung tâm, tạo điều kiện thuận lợi cho việc khám phá những địa điểm xa hơn trong tỉnh Quảng Nam.",
                                                                "For easier travel, Hoa Thu Homestay is located near Hoi An's main bus station. With about a 15-minute walk or a 5-minute bicycle ride, you can reach the central public transport area, making it convenient to explore more distant destinations across Quang Nam Province.")),
                                heading(ContentSection.STORY, of("Dịch Vụ Tại Hoa Thu Homestay", "Services at Hoa Thu Homestay")),
                                paragraph(ContentSection.STORY,
                                                of("ệm ẩm thực đẳng cấp mà không cần phải đi xa.", "refined dining experiences without having to go far.")),
                                heading(ContentSection.STORY,
                                                of("Dịch vụ tại Hoa Thu Homestay luôn được đánh giá cao bởi phong cách phục vụ chuyên nghiệp và tận tình. Từ lễ tân đến nhân viên nhà hàng, tất cả đều sẵn lòng đáp ứng các nhu cầu của khách một cách nhanh chóng và hiệu quả. Các dịch vụ như đặt tour du lịch, cho thuê xe đạp cũng được cung cấp để tạo sự thuận lợi tối đa cho du khách.",
                                                                "The service at Hoa Thu Homestay is consistently praised for its professional and attentive style. From the front desk to the restaurant staff, everyone is ready to meet guests' needs quickly and effectively. Services such as tour booking and bicycle rental are also offered to provide maximum convenience for guests.")),
                                paragraph(ContentSection.STORY,
                                                of("Theo đánh giá trên Traveloka, , với số điểm người dùng trung bình lên tới . Những nhận xét tích cực xoay quanh không gian thoải mái, vị trí thuận lợi và dịch vụ chăm sóc chu đáo tại Hoa Thu Homestay.",
                                                                "According to reviews on Traveloka, , with an average user score of up to . The positive comments center on the comfortable spaces, convenient location and attentive service at Hoa Thu Homestay.")),
                                heading(ContentSection.STORY,
                                                of("Khu Vực Xung Quanh Hoa Thu Homestay và Quảng Nam, Hội An", "The area around Hoa Thu Homestay and Quang Nam, Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Xung quanh Hoa Thu Homestay là một khu vực yên bình với cảnh quan thiên nhiên ấm áp. Ngoài các bảo tàng và di tích lịch sử, du khách còn có thể tham dự các lễ hội và sự kiện truyền thống diễn ra quanh năm. Tỉnh Quảng Nam nổi tiếng không chỉ với bãi biển đẹp mà còn với nền văn hóa phong phú, hấp dẫn qua hàng ngàn năm lịch sử.",
                                                                "Hoa Thu Homestay is surrounded by a peaceful area with warm natural scenery. Beyond museums and historical sites, visitors can also take part in traditional festivals and events held throughout the year. Quang Nam Province is renowned not only for its beautiful beaches but also for a rich culture that has captivated people across thousands of years of history.")),
                                heading(ContentSection.STORY,
                                                of("Tại Sao Lựa Chọn Hoa Thu Homestay Là Một Quyết Định Tốt", "Why choosing Hoa Thu Homestay is a great decision")),
                                paragraph(ContentSection.STORY,
                                                of("Chọn Hoa Thu Homestay cho kỳ nghỉ ở Hội An bạn không chỉ tận hưởng sự thoải mái mà còn tiết kiệm chi phí với mức giá hợp lý. Vị trí đắc địa cùng các tiện nghi và dịch vụ chất lượng chắc chắn sẽ làm hài lòng ngay cả những du khách khó tính nhất.",
                                                                "Choosing Hoa Thu Homestay for your stay in Hoi An lets you enjoy comfort while also saving money with reasonable rates. Its prime location, together with quality amenities and services, is sure to satisfy even the most demanding travelers.")));

                // --------- Hotel amenities ----------
                // Chỉ giữ HOTEL scope canonical codes; tiện nghi phòng (TV, MINIBAR, SHOWER, AC...)
                // đã được khai báo trong RoomType.amenityCodes của từng phòng.
                List<AmenityCatalogDoc> hotelAmenities = List.of(
                                // Tiện nghi chung
                                amenity("family_room", of("Phòng gia đình", "Family room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, true),
                                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),
                                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),

                                // Dịch vụ khách sạn
                                amenity("currency_exchange", of("Dịch vụ thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("security_24h", of("Bảo vệ 24 giờ", "24-hour security"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt Tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),

                                // Đưa đón
                                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("train_station_pickup", of("Đón khách tại ga tàu", "Train station pickup"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),

                                // Kết nối mạng
                                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET,
                                                AmenitySection.INTERNET, true),

                                // Tiện ích lân cận
                                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Bank"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("beauty_salon", of("Thẩm mỹ viện", "Beauty salon"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),

                                // Vận chuyển
                                amenity("bicycle_rental", of("Dịch vụ cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("bicycle_storage", of("Giữ xe đạp", "Bicycle storage"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT, false),

                                // Trẻ em
                                amenity("babysitting", of("Dịch vụ giữ trẻ (thu phí)", "Babysitting (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),

                                // Ẩm thực
                                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, false));

                // --------- Room types ----------
                // 1) Family (30.0 m² - 4 khách - 2 giường đôi)
                RoomType family = RoomType.builder()
                                .id("rt-hoa-thu-family")
                                .name(of("Family", "Family"))
                                .shortDescription(of("Phòng Family 30.0 m², 2 giường đôi, tối đa 4 khách.", "Family room 30.0 m², 2 double beds, up to 4 guests."))
                                .description(of("""
                                                                    Phòng Family phù hợp cho gia đình/nhóm bạn, diện tích 30.0 m² và sức chứa 4 khách.
                                                                    Tiện nghi nổi bật: vòi tắm đứng, nước nóng, máy lạnh; có minibar, TV, quạt, rèm cản sáng và bàn làm việc.
                                                                """, """
                                                                    The Family room is ideal for families or groups of friends, with an area of 30.0 m² and capacity for 4 guests.
                                                                    Highlight amenities: standing shower, hot water, air conditioning; minibar, TV, fan, blackout curtains and work desk.
                                                                """))
                                .areaSqm(30.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription(of("2 giường đôi", "2 double beds"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(0)
                                .maxGuests(4)
                                .totalRooms(10)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-e97ea4bd8a6daadf63052fd961d7c03c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc6f1979e75ba2139d9423e7c7d538ed.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc2b932dfbf8e209f49f4cce6a2b2d76.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-6133d325ae6953153aab983ca7dbc7fc.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-9e65785426329bcd2150c470c986898c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67742180-2399x1557-FIT_AND_TRIM-93ec84bfcc51d9617f67a8bb6d2a19b0.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", "Family - Bedroom", false, 5)))
                                .amenityCodes(List.of(
                                                "SHOWER",
                                                "HOT_WATER",
                                                "AC",
                                                "MINIBAR",
                                                "TV",
                                                "FAN",
                                                "BLACKOUT_CURTAINS",
                                                "DESK",
                                                "HAIR_DRYER",
                                                "PRIVATE_BATHROOM",
                                                "BATH_AMENITIES",
                                                "BALCONY",
                                                "MICROWAVE"))
                                .ratePlans(List.of(
                                                // Plan 1: 469,929 (ref 514,286) - không hoàn tiền
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-nonref-prepaid")
                                                                .name(of("Family - Không gồm bữa sáng", "Family - Room only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                                                .pricePerNight(new BigDecimal("469929"))
                                                                .referencePricePerNight(new BigDecimal("514286"))
                                                                .discountPercent(9)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Giá ưu tiên", "Priority price"))
                                                                .showLowAvailability(true)
                                                                .build(),

                                                // Plan 2: 514,286 (ref 685,715) - thanh toán tại KS, có chính sách hủy
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-payathotel-flex")
                                                                .name(of("Family - Không gồm bữa sáng", "Family - Room only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                                                .pricePerNight(new BigDecimal("514286"))
                                                                .referencePricePerNight(new BigDecimal("685715"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Ưu đãi phút chót", "Last-minute deal"))
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // 2) Family With Balcony (32.0 m² - 4 khách)
                RoomType familyBalcony = RoomType.builder()
                                .id("rt-hoa-thu-family-with-balcony")
                                .name(of("Family With Balcony", "Family With Balcony"))
                                .shortDescription(of("Phòng Family With Balcony 32.0 m², tối đa 4 khách, có ban công/sân hiên.", "Family With Balcony room 32.0 m², up to 4 guests, with balcony/patio."))
                                .description(of("""
                                                    Phòng Family With Balcony rộng 32.0 m², phù hợp 4 khách.
                                                    Tính năng nổi bật: vòi tắm đứng, ban công/sân hiên, lò vi sóng, nước nóng, máy lạnh.
                                                """, """
                                                    The Family With Balcony room is 32.0 m², suitable for 4 guests.
                                                    Highlight features: standing shower, balcony/patio, microwave, hot water, air conditioning.
                                                """))
                                .areaSqm(32.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription(of("2 giường đôi", "2 double beds"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(0)
                                .maxGuests(4)
                                .totalRooms(10)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-74d37d4e17f53feba7551cff6d02bf71.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c8a3b95dd2fdabc0aed51f7e9a81b7ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c086c987dc21336b52a2e34998b30c9c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-0d53038368afecd29b15da1528b62d3f.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67742180-2337x1386-FIT_AND_TRIM-d95f2714157142613e1d82f149b18171.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-eb30b251df537c9809d8549d0b251f06.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", "Family With Balcony - Bedroom", false, 5)))
                                .amenityCodes(List.of(
                                                "SHOWER",
                                                "BALCONY",
                                                "MICROWAVE",
                                                "HOT_WATER",
                                                "AC",
                                                "MINIBAR",
                                                "TV",
                                                "FAN",
                                                "BLACKOUT_CURTAINS",
                                                "DESK",
                                                "HAIR_DRYER",
                                                "PRIVATE_BATHROOM",
                                                "BATH_AMENITIES"))
                                .ratePlans(List.of(
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-balcony-room-only")
                                                                .name(of("Family With Balcony - Không gồm bữa sáng", "Family With Balcony - Room only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                                                .pricePerNight(new BigDecimal("489508"))
                                                                .referencePricePerNight(new BigDecimal("489508"))
                                                                .discountPercent(0)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Giá tốt", "Great price"))
                                                                .showLowAvailability(true)
                                                                .build(),
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-balcony-payathotel-flex")
                                                                .name(of("Family With Balcony - Không gồm bữa sáng", "Family With Balcony - Room only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                                                .pricePerNight(new BigDecimal("535714"))
                                                                .referencePricePerNight(new BigDecimal("714205"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Ưu đãi phút chót", "Last-minute deal"))
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // --------- Nearby places (từ ảnh “Xung quanh…”) ----------
                List<NearbyPlace> nearby = List.of(
                                // Địa điểm lân cận
                                nearby("my-grandmas-home-cooking", of("My Grandma's Home Cooking", "My Grandma's Home Cooking"), of("Địa điểm lân cận", "Nearby places"),
                                                36.0),
                                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Địa điểm lân cận", "Nearby places"), 41.0),
                                nearby("old-town-lantern-boat", of("Old Town Lantern Boat", "Old Town Lantern Boat"), of("Địa điểm lân cận", "Nearby places"), 231.0),
                                nearby("nha-tan-ky", of("Nhà Tân Kỳ", "Tan Ky Old House"), of("Địa điểm lân cận", "Nearby places"), 243.0),
                                nearby("bao-tang-van-hoa-sa-huynh", of("Bảo tàng Văn hóa Sa Huỳnh", "Sa Huynh Culture Museum"), of("Địa điểm lân cận", "Nearby places"),
                                                284.0),
                                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Địa điểm lân cận", "Nearby places"), 295.0),
                                nearby("nha-co-phung-hung", of("Nhà cổ Phùng Hưng", "Phung Hung Old House"), of("Địa điểm lân cận", "Nearby places"), 300.0),
                                nearby("duc-an-house", of("Duc An House", "Duc An House"), of("Địa điểm lân cận", "Nearby places"), 306.0),
                                nearby("pho-co-hoi-an", of("Phố cổ Hội An", "Hoi An Ancient Town"), of("Địa điểm lân cận", "Nearby places"), 320.0),
                                nearby("diep-dong-nguyen-house", of("Diep Dong Nguyen House", "Diep Dong Nguyen House"), of("Địa điểm lân cận", "Nearby places"), 340.0),
                                nearby("hoi-quan-quang-dong", of("Hội quán Quảng Đông - Chùa Quảng Triệu", "Cantonese Assembly Hall - Quang Trieu Temple"),
                                                of("Địa điểm lân cận", "Nearby places"), 340.0),
                                nearby("bao-tang-van-hoa-dan-gian", of("Bảo tàng Văn hóa dân gian", "Museum of Folk Culture"), of("Địa điểm lân cận", "Nearby places"),
                                                403.0),
                                nearby("nha-co-quan-thang", of("Nhà cổ Quân Thắng", "Quan Thang Old House"), of("Địa điểm lân cận", "Nearby places"), 422.0),
                                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Địa điểm lân cận", "Nearby places"), 446.0),
                                nearby("bao-tang-gom-su", of("Bảo tàng gốm sứ mậu dịch Hội An", "Hoi An Museum of Trade Ceramics"), of("Địa điểm lân cận", "Nearby places"), 456.0),

                                // Trung tâm giao thông
                                nearby("hoi-an-boat-harbor", of("Hoi An Boat Harbor", "Hoi An Boat Harbor"), of("Trung tâm giao thông", "Transport hub"), 529.0),
                                nearby("old-town-latern-boat", of("Old Town Lantern Boat", "Old Town Lantern Boat"), of("Trung tâm giao thông", "Transport hub"), 231.0),
                                // Trung tâm giải trí
                                nearby("ky-uc-hoi-an", of("Ký ức Hội An", "Hoi An Memories"), of("Trung tâm giải trí", "Entertainment hub"), 1500.0),
                                nearby("dao-ky-uc-hoi-an", of("Đảo ký ức Hội An", "Hoi An Memories Island"), of("Trung tâm giải trí", "Entertainment hub"), 1490.0),
                                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Trung tâm giải trí", "Entertainment hub"), 446.0),
                                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Trung tâm giải trí", "Entertainment hub"), 295.0),
                                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Trung tâm giải trí", "Entertainment hub"), 41.0),

                                // Khác
                                nearby("quan-cong-mieu", of("Quan Công Miếu", "Quan Cong Temple"), of("Khác", "Other"), 629.0),
                                nearby("chua-chuc-thanh", of("Chùa Chúc Thánh", "Chuc Thanh Pagoda"), of("Khác", "Other"), 1600.0),
                                nearby("chua-phuoc-lam", of("Chùa Phước Lâm", "Phuoc Lam Pagoda"), of("Khác", "Other"), 2020.0),
                                nearby("om-factory-yoga-hoian", of("Om Factory Yoga Hoi An", "Om Factory Yoga Hoi An"), of("Khác", "Other"), 2830.0),
                                nearby("dinh-cam-pho", of("Đình Cẩm Phô", "Cam Pho Communal House"), of("Khác", "Other"), 487.0));

                // --------- General info ----------
                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, WiFi", "Air conditioning, WiFi"))
                                .distanceToCityCenterKm(0.30)
                                .popularAreaSummary(of("Chợ đêm Hội An, Old Town Lantern Boat, Chùa Cầu Hội An", "Hoi An Night Market, Old Town Lantern Boat, Japanese Covered Bridge"))
                                .totalRooms(20)
                                .totalFloors(3)
                                .otherHighlightFacilities(of("Đưa đón sân bay (thu phí), hỗ trợ đặt tour, cho thuê xe đạp.", "Airport transfer (surcharge), tour booking assistance, bicycle rental."))
                                .interestingPlacesSummary(of("Chợ đêm Hội An, Chùa Cầu Hội An, Phố cổ Hội An", "Hoi An Night Market, Japanese Covered Bridge, Hoi An Ancient Town"))
                                .build();

                // --------- Policy (chưa có ảnh chính sách, set mặc định giống seed khác)
                // ----------
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
                                                                .content(of("Vui lòng liên hệ trước nếu bạn cần hỗ trợ nhận phòng muộn hoặc yêu cầu đặc biệt.", "Please contact us in advance if you need late check-in support or have special requests."))
                                                                .build()))
                                .build();

                // --------- Review stats (từ các tag nhận xét trong ảnh) ----------
                ReviewStats reviewStats = ReviewStats.builder()
                                .cleanlinessScore(9.0)
                                .roomAmenitiesScore(9.0)
                                .foodScore(8.6)
                                .locationScore(9.2)
                                .serviceScore(9.2)
                                .keywords(List.of(
                                                keyword("friendly_staff", of("Nhân viên thân thiện", "Friendly staff"), 7),
                                                keyword("distance_to_center", of("Khoảng cách đến trung tâm", "Distance to center"), 6),
                                                keyword("surrounding_area", of("Khu vực xung quanh", "Surrounding area"), 5),
                                                keyword("family_friendly", of("Hợp cho gia đình", "Family friendly"), 4)))
                                .build();

                // --------- Tax config & filter facets ----------
                TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
                                .defaultVatPercent(new BigDecimal("8"))
                                .defaultServiceFeePercent(new BigDecimal("7"))
                                .showPricePreTax(true)
                                .build();

                HotelFilterFacets filterFacets = HotelFilterFacets.builder()
                                .hasFreeCancellation(false)
                                .hasPayAtHotel(true)
                                .hasBreakfastIncluded(false)
                                .build();

                // --------- Publisher & Moderation ----------
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
                                .rejectionReason(null)
                                .blockedReason(null)
                                .reportCount(0)
                                .lastActionByAdminId("system-seed")
                                .lastActionAt(now)
                                .build();

                // --------- FAQs (chưa có ảnh FAQ => để tối thiểu) ----------
                List<FaqItem> faqs = List.of(
                                faq(
                                                of("Những tiện ích tại Hoa Thu Homestay?", "What amenities does Hoa Thu Homestay offer?"),
                                                of("Những tiện ích tại Hoa Thu Homestay bao gồm máy lạnh, WiFi. Một số dịch vụ có thể yêu cầu trả thêm phí.", "Amenities at Hoa Thu Homestay include air conditioning and WiFi. Some services may require an additional fee.")),
                                faq(
                                                of("Hoa Thu Homestay có mức giá là bao nhiêu?", "How much does Hoa Thu Homestay cost?"),
                                                of("Phòng tại Hoa Thu Homestay có giá từ khoảng 542.768 VND. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at Hoa Thu Homestay start from around 542,768 VND. Prices may vary depending on the date and promotions.")),
                                faq(
                                                of("Thời gian nhận phòng và trả phòng của Hoa Thu Homestay?", "What are the check-in and check-out times at Hoa Thu Homestay?"),
                                                of("Thời gian nhận phòng tại Hoa Thu Homestay từ 14:00 – 22:00 và trả phòng trước 07:00 – 12:00.", "Check-in at Hoa Thu Homestay is from 14:00 to 22:00 and check-out is between 07:00 and 12:00.")),
                                faq(
                                                of("Hoa Thu Homestay có phục vụ ăn sáng không?", "Does Hoa Thu Homestay serve breakfast?"),
                                                of("Có, Hoa Thu Homestay có dịch vụ cung cấp bữa sáng. Tuy nhiên, bạn cần chọn loại phòng có bao gồm bữa sáng. Nếu không, bạn có thể phải trả thêm phí.", "Yes, Hoa Thu Homestay offers breakfast. However, you need to select a room type that includes breakfast; otherwise an additional fee may apply.")),
                                faq(
                                                of("Khách sạn Hoa Thu Homestay đạt tiêu chuẩn mấy sao?", "What star rating does Hoa Thu Homestay have?"),
                                                of("Hoa Thu Homestay là khách sạn đạt tiêu chuẩn 1.5 sao.", "Hoa Thu Homestay is a 1.5-star property.")),
                                faq(
                                                of("Hoa Thu Homestay có bao nhiêu tầng lầu?", "How many floors does Hoa Thu Homestay have?"),
                                                of("Hoa Thu Homestay có tổng cộng 2 tầng.", "Hoa Thu Homestay has a total of 2 floors.")),
                                faq(
                                                of("Tổng số phòng hiện có tại Hoa Thu Homestay là bao nhiêu?", "How many rooms does Hoa Thu Homestay have in total?"),
                                                of("Tổng số phòng tại Hoa Thu Homestay là 5 phòng.", "Hoa Thu Homestay has a total of 5 rooms.")),
                                faq(
                                                of("Đánh giá của khách lưu trú trước đây về Hoa Thu Homestay như thế nào?", "What do previous guests say about Hoa Thu Homestay?"),
                                                of("Hoa Thu Homestay được đánh giá cao về vị trí, vệ sinh và tiện nghi phòng theo nhận xét từ khách hàng đã lưu trú trên Traveloka.", "Hoa Thu Homestay is highly rated for its location, cleanliness and room amenities according to reviews from guests who stayed via Traveloka.")),
                                faq(
                                                of("Những địa điểm nổi bật nào nằm gần Hoa Thu Homestay?", "What notable places are near Hoa Thu Homestay?"),
                                                of("Một số địa điểm nổi bật gần Hoa Thu Homestay bao gồm My Grandma's Home Cooking, Chợ đêm Hội An, Old Town Lantern Boat, Nhà Tấn Ký và Bảo tàng Văn hóa Sa Huỳnh.", "Some notable places near Hoa Thu Homestay include My Grandma's Home Cooking, Hoi An Night Market, Old Town Lantern Boat, Tan Ky Old House and the Sa Huynh Culture Museum.")),
                                faq(
                                                of("Những loại phòng đang khả dụng tại Hoa Thu Homestay?", "What room types are available at Hoa Thu Homestay?"),
                                                of("Những loại phòng đang khả dụng tại Hoa Thu Homestay bao gồm Family và Family With Balcony.", "The available room types at Hoa Thu Homestay are Family and Family With Balcony.")),
                                faq(
                                                of("Phòng tại Hoa Thu Homestay được trang bị những tiện nghi gì?", "What amenities are the rooms at Hoa Thu Homestay equipped with?"),
                                                of("Hầu hết các phòng tại Hoa Thu Homestay đều được trang bị vòi tắm đứng, nước nóng, máy lạnh, ban công hoặc sân hiên, lò vi sóng và các tiện nghi cần thiết khác. Tiện nghi có thể khác nhau tùy loại phòng.", "Most rooms at Hoa Thu Homestay are equipped with a standing shower, hot water, air conditioning, a balcony or patio, a microwave and other essential amenities. Amenities may vary by room type.")),
                                faq(
                                                of("Mỗi phòng tại Hoa Thu Homestay có thể lưu trú tối đa bao nhiêu khách?", "What is the maximum number of guests per room at Hoa Thu Homestay?"),
                                                of("Hoa Thu Homestay có thể chứa tối đa 4 người trong một phòng, tùy thuộc vào loại phòng bạn đặt. Sức chứa cụ thể sẽ hiển thị tại thời điểm đặt phòng.", "Hoa Thu Homestay can accommodate up to 4 guests per room, depending on the room type you book. The specific capacity is shown at the time of booking.")));

                // --------- Build ----------
                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName(of("Hội An", "Hoi An"))
                                .districtName(of("Minh An", "Minh An"))
                                .wardName(null)
                                .addressLine(of("80 Ngô Quyền, Minh An, Hội An, Quảng Nam, Việt Nam", "80 Ngo Quyen, Minh An, Hoi An, Quang Nam, Vietnam"))
                                .location(new double[] { 108.3270, 15.8790 })
                                .name(of("Hoa Thu Homestay", "Hoa Thu Homestay"))
                                .slug(slug)
                                .hotelType(HotelType.HOMESTAY)
                                .starRating(2)
                                .shortDescription(of("Homestay giá tốt tại Minh An, gần Chợ đêm Hội An và khu phố cổ.", "Great-value homestay in Minh An, near Hoi An Night Market and the ancient town."))
                                .description(of("Hoa Thu Homestay phù hợp cho du khách muốn lưu trú thoải mái, tiết kiệm và thuận tiện di chuyển trong khu vực phố cổ Hội An. Có WiFi, máy lạnh và các tiện ích cơ bản cho kỳ nghỉ ngắn hoặc dài ngày.", "Hoa Thu Homestay is ideal for travelers seeking a comfortable, affordable stay with easy access around the Hoi An ancient town area. It offers WiFi, air conditioning and basic amenities for short or long stays."))
                                .phone(null)
                                .email(null)
                                .website(null)
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .avgRating(9.1)
                                .reviewsCount(167)
                                .ratingLabel(of("Xuất sắc", "Excellent"))
                                .minNightlyPrice(new BigDecimal("469929"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("514286"))
                                .discountPercent(9)
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
                                .roomTypes(List.of(family, familyBalcony))
                                .nearbyPlaces(nearby)
                                .policy(policy)
                                .generalInfo(generalInfo)
                                .faqs(faqs)
                                .reviewStats(reviewStats)
                                .publisher(publisher)
                                .moderation(moderation)
                                .build();
        }
}
