// src/main/java/com/mravel/catalog/bootstrap/hotel/SeedHoiAnReverieVillas.java
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
import com.mravel.catalog.model.doc.HotelDoc.BookingConfig;
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
import com.mravel.catalog.model.doc.HotelDoc.ReviewKeywordStat;
import com.mravel.catalog.model.doc.HotelDoc.RoomType;
import com.mravel.catalog.model.doc.HotelDoc.TaxAndFeeConfig;
import com.mravel.catalog.repository.HotelDocRepository;
import static com.mravel.catalog.utils.hotel.HotelImages.*;
import static com.mravel.catalog.utils.hotel.HotelContentBlocks.*;
import static com.mravel.catalog.utils.hotel.HotelSeedHelpers.*;
import static com.mravel.catalog.utils.hotel.Localized.vi;
import static com.mravel.catalog.utils.hotel.Localized.of;

@Configuration
@Profile("seed")
public class SeedHoiAnReverieVillas {

        @Bean
        CommandLineRunner seedHoiAnReverieVillasRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHoiAnReverieVillas] start");

                        HotelDoc hotel = hotelHoiAnReverieVillas();

                        repo.findBySlugAndActiveTrue(hotel.getSlug())
                                        .ifPresent(existing -> hotel.setId(existing.getId()));

                        repo.save(hotel);
                        System.out.println(">>> [SeedHoiAnReverieVillas] upsert: " + hotel.getSlug());

                        System.out.println(">>> [SeedHoiAnReverieVillas] done");
                };
        }

        // =====================================================================
        // 1) HOI AN REVERIE VILLAS
        // =====================================================================

        private static HotelDoc hotelHoiAnReverieVillas() {
                String slug = "hoi-an-reverie-villas";

                // --------- Ảnh chính ----------
                // Chưa có: danh sách URL ảnh gallery từ Traveloka (ảnh cover + các ảnh khác)
                List<Image> images = List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-371d6776f5e4772bfc6e873f39f7feb2.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng ngủ giường King tại Hoi An Reverie Villas",
                                                "King-bed bedroom at Hoi An Reverie Villas", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x680-FIT_AND_TRIM-f62d0313fcb71f300e606725957ca3ff.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vườn và sân hiên xanh mát tại Hoi An Reverie Villas",
                                                "Lush garden and terrace at Hoi An Reverie Villas", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-9f263d4cbe9f8dd2f66e669ccc6d25d7.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Mặt tiền khu lưu trú Hoi An Reverie Villas",
                                                "Facade of Hoi An Reverie Villas", false, 2),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-15c938e5649f2fd8c537225dd6ad91ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng gia đình rộng rãi tại Hoi An Reverie Villas",
                                                "Spacious family room at Hoi An Reverie Villas", false, 3),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x683-FIT_AND_TRIM-72a9aaecd28173cf211267f7e94c905f.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Không gian phòng gia đình (góc nhìn khác)",
                                                "Family room space (alternate view)", false, 4),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x683-FIT_AND_TRIM-0bd3fbedd7c1d13269a51e2148fe2578.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực/tiện ích di chuyển (xe/đưa đón) tại Hoi An Reverie Villas",
                                                "Transport area/service (car/shuttle) at Hoi An Reverie Villas",
                                                false, 5),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-dbfbfa101b4a8045f97aa1e3ed4147d3.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Góc ngồi thư giãn ngoài trời",
                                                "Outdoor relaxation seating area", false, 6),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-7edee55e6501d033826ecbad666aea1c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Toàn cảnh Hoi An Reverie Villas (góc nhìn trên cao)",
                                                "Panoramic view of Hoi An Reverie Villas (aerial view)", false, 7),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-0ef174f942c5232f343d7fcecf28950e.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu lưu trú nhìn từ phía trước",
                                                "The property seen from the front", false, 8),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-4839a0ff5539b2c48d1f0d2ef6d17b02.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực sinh hoạt chung / không gian tụ tập",
                                                "Common living area / gathering space", false, 9),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-53e14ba4d52911992f7b1090f08cf55c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Ban công/không gian ngoài trời buổi tối",
                                                "Balcony/outdoor space in the evening", false, 10));

                // --------- Content (Overview + Story) ----------
                // Ảnh bạn gửi có modal "Giới thiệu" dạng đoạn văn. Mình seed vào OVERVIEW.
                // Chưa có: STORY dài (nếu bạn gửi thêm ảnh/story thì mình seed tiếp)
                List<ContentBlock> content = List.of(
                                paragraph(ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas là một villa khách sạn 2 sao tọa lạc tại 257 Hai Mươi Tám Tháng Ba, Hội An, Tỉnh Quảng Nam, Thành phố Đà Nẵng, Việt Nam, 560000, Hội An. Với thiết kế thanh lịch và không gian gần gũi với thiên nhiên, Hoi An Reverie Villas mang đến cho du khách một trải nghiệm nghỉ dưỡng yên bình và thư giãn giữa lòng phố cổ. Tọa lạc tại vị trí thuận tiện, gần các điểm tham quan nổi tiếng, Hoi An Reverie Villas là lựa chọn lý tưởng cho cả du khách thích khám phá và những ai muốn tận hưởng sự yên tĩnh. Sự kết hợp giữa không gian mở và dịch vụ chu đáo tại Hoi An Reverie Villas hứa hẹn mang lại cho du khách một kỳ nghỉ đáng nhớ.", "Hoi An Reverie Villas is a 2-star villa hotel located at 257 Hai Muoi Tam Thang Ba, Hoi An, Quang Nam Province, Da Nang City, Vietnam, 560000, Hoi An. With its elegant design and spaces close to nature, Hoi An Reverie Villas offers travelers a peaceful and relaxing retreat in the heart of the ancient town. Conveniently located near famous attractions, Hoi An Reverie Villas is an ideal choice both for travelers who love to explore and for those who want to enjoy the quiet. The combination of open spaces and attentive service at Hoi An Reverie Villas promises guests a memorable stay.")),
                                heading(ContentSection.OVERVIEW,
                                                of("Những loại phòng đang khả dụng tại Hoi An Reverie Villas", "Room types available at Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas cung cấp nhiều loại phòng với thiết kế tinh tế và đầy đủ tiện nghi để đáp ứng nhu cầu đa dạng của du khách:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Deluxe Double With Garden View: Phòng giường đôi với tầm nhìn hướng ra vườn, mang đến không gian xanh mát và thoáng đãng.\r\n"
                                                                + //
                                                                "Deluxe Twin With Garden View: Phòng có hai giường đơn và tầm nhìn ra vườn, phù hợp cho bạn bè hoặc đồng nghiệp chia sẻ không gian nghỉ ngơi.\r\n"
                                                                + //
                                                                "Double With Pool View: Phòng giường đôi với tầm nhìn hướng hồ bơi, giúp du khách tận hưởng khung cảnh thư giãn.\r\n"
                                                                + //
                                                                "Superior King: Phòng Superior với giường cỡ king, thiết kế rộng rãi và tiện nghi hiện đại.\r\n"
                                                                + //
                                                                "Superior Double Garden View: Phòng giường đôi với tầm nhìn ra vườn, mang đến không gian thoải mái và yên tĩnh.\r\n"
                                                                + //
                                                                "Grand Deluxe Double With River View: Phòng Grand Deluxe với tầm nhìn hướng sông, tạo nên không gian lãng mạn và thư thái.\r\n"
                                                                + //
                                                                "Deluxe Double Pool View: Phòng Deluxe giường đôi với tầm nhìn ra hồ bơi, thích hợp cho những ai muốn thư giãn bên làn nước trong xanh.\r\n"
                                                                + //
                                                                "Quadruple Family Suite: Phòng suite dành cho gia đình với không gian rộng rãi và tiện nghi cao cấp.\r\n"
                                                                + //
                                                                "Deluxe Double River View: Phòng Deluxe giường đôi với tầm nhìn ra sông, tạo nên không gian nghỉ ngơi lý tưởng.\r\n"
                                                                + //
                                                                "Family Suite: Phòng gia đình rộng rãi với nhiều giường và không gian sinh hoạt thoải mái, phù hợp cho các gia đình lớn.",
                                                                "Hoi An Reverie Villas offers a wide range of room types with refined designs and full amenities to meet the diverse needs of travelers:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Deluxe Double With Garden View: A double room with a garden view, offering a cool, green and airy space.\r\n"
                                                                + //
                                                                "Deluxe Twin With Garden View: A room with two single beds and a garden view, ideal for friends or colleagues sharing a place to rest.\r\n"
                                                                + //
                                                                "Double With Pool View: A double room with a pool view, letting guests enjoy a relaxing scenery.\r\n"
                                                                + //
                                                                "Superior King: A Superior room with a king-size bed, a spacious layout and modern amenities.\r\n"
                                                                + //
                                                                "Superior Double Garden View: A double room with a garden view, offering a comfortable and quiet space.\r\n"
                                                                + //
                                                                "Grand Deluxe Double With River View: A Grand Deluxe room with a river view, creating a romantic and relaxing space.\r\n"
                                                                + //
                                                                "Deluxe Double Pool View: A Deluxe double room with a pool view, perfect for those who want to relax beside the clear water.\r\n"
                                                                + //
                                                                "Quadruple Family Suite: A family suite with a spacious layout and premium amenities.\r\n"
                                                                + //
                                                                "Deluxe Double River View: A Deluxe double room with a river view, creating an ideal place to rest.\r\n"
                                                                + //
                                                                "Family Suite: A spacious family room with multiple beds and a comfortable living space, suitable for large families.")),
                                heading(ContentSection.OVERVIEW, of("Các điểm tham quan gần Hoi An Reverie Villas", "Attractions near Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas có vị trí thuận lợi, giúp du khách dễ dàng di chuyển đến các điểm tham quan nổi tiếng:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Old Town Lantern Boat: Cách khách sạn khoảng 2.13 km, nơi du khách có thể trải nghiệm thả đèn lồng và tham quan phố cổ trên thuyền.\r\n"
                                                                + //
                                                                "Chợ đêm Hội An: Cách Hoi An Reverie Villas 2.23 km, là địa điểm lý tưởng để khám phá ẩm thực đường phố, mua sắm và tận hưởng không khí sôi động về đêm.\r\n"
                                                                + //
                                                                "Quan Cong Temple: Nằm cách Hoi An Reverie Villas 2.74 km, là một trong những ngôi đền nổi tiếng với kiến trúc độc đáo và lịch sử lâu đời.",
                                                                "Hoi An Reverie Villas enjoys a convenient location, making it easy for travelers to reach famous attractions:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Old Town Lantern Boat: About 2.13 km from the hotel, where travelers can experience releasing lanterns and touring the ancient town by boat.\r\n"
                                                                + //
                                                                "Hoi An Night Market: 2.23 km from Hoi An Reverie Villas, an ideal spot to discover street food, go shopping and enjoy the lively nighttime atmosphere.\r\n"
                                                                + //
                                                                "Quan Cong Temple: 2.74 km from Hoi An Reverie Villas, one of the famous temples known for its unique architecture and long history.")),
                                heading(ContentSection.OVERVIEW, of("Những tiện nghi nổi bật tại Hoi An Reverie Villas", "Standout amenities at Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas mang đến cho du khách nhiều tiện nghi để kỳ nghỉ trở nên thoải mái và dễ chịu:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Máy lạnh: Đảm bảo không gian mát mẻ và thoải mái trong mọi thời tiết.\r\n"
                                                                + //
                                                                "Nhà hàng: Phục vụ các món ăn địa phương và quốc tế, mang đến trải nghiệm ẩm thực đa dạng và chất lượng.\r\n"
                                                                + //
                                                                "Hồ bơi: Hồ bơi ngoài trời là nơi lý tưởng để thư giãn và tận hưởng làn nước trong xanh.\r\n"
                                                                + //
                                                                "Chỗ đậu xe: Dịch vụ đậu xe riêng thuận tiện cho khách di chuyển bằng phương tiện cá nhân.\r\n"
                                                                + //
                                                                "Wi-Fi miễn phí: Phủ sóng toàn bộ khu vực, giúp du khách dễ dàng kết nối internet để làm việc hoặc giải trí.",
                                                                "Hoi An Reverie Villas offers travelers a range of amenities to make the stay comfortable and pleasant:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Air conditioning: Ensures a cool and comfortable space in any weather.\r\n"
                                                                + //
                                                                "Restaurant: Serves local and international dishes, offering a diverse and quality dining experience.\r\n"
                                                                + //
                                                                "Swimming pool: The outdoor pool is an ideal place to relax and enjoy the clear water.\r\n"
                                                                + //
                                                                "Parking: A private parking service convenient for guests traveling by their own vehicle.\r\n"
                                                                + //
                                                                "Free Wi-Fi: Covers the entire property, helping travelers easily get online to work or relax.")),
                                heading(ContentSection.OVERVIEW, of("Phương tiện công cộng gần Hoi An Reverie Villas", "Public transport near Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas nằm gần các tuyến đường lớn, giúp du khách dễ dàng tiếp cận các phương tiện công cộng như xe buýt và taxi. Dịch vụ xe ôm công nghệ cũng có sẵn để du khách di chuyển thuận tiện đến các điểm tham quan khác trong thành phố.", "Hoi An Reverie Villas is located near major roads, making it easy for travelers to access public transport such as buses and taxis. Ride-hailing motorbike services are also available so guests can conveniently travel to other attractions in the city.")),
                                heading(ContentSection.OVERVIEW, of("Dịch vụ tại Hoi An Reverie Villas", "Services at Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Đội ngũ nhân viên tại Hoi An Reverie Villas luôn sẵn sàng hỗ trợ du khách với mọi nhu cầu. Dịch vụ lễ tân 24/7 đảm bảo sự phục vụ tận tâm từ lúc nhận phòng đến khi trả phòng. Dịch vụ dọn phòng hàng ngày giúp không gian luôn sạch sẽ và ngăn nắp. Khách sạn cũng cung cấp các dịch vụ khác như giặt ủi và bảo quản hành lý, rất hữu ích cho những khách hàng có nhu cầu lưu trú dài ngày.", "The staff at Hoi An Reverie Villas are always ready to assist travelers with every need. The 24/7 front desk service ensures dedicated care from check-in to check-out. Daily housekeeping keeps the space clean and tidy. The hotel also offers other services such as laundry and luggage storage, which are very helpful for guests planning a longer stay.")),
                                heading(ContentSection.OVERVIEW,
                                                of("Đánh giá của khách hàng trên Traveloka về Hoi An Reverie Villas", "Customer reviews on Traveloka about Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Trên Traveloka, Hoi An Reverie Villas nhận được nhiều đánh giá tích cực nhờ vào vị trí thuận tiện, dịch vụ tận tâm và không gian thoáng mát. Các phòng như Deluxe Double With Garden View và Grand Deluxe Double With River View được khách hàng yêu thích vì tầm nhìn đẹp và thiết kế tinh tế. Đội ngũ nhân viên chuyên nghiệp và thân thiện cũng là yếu tố giúp khách sạn nhận được sự hài lòng từ du khách.", "On Traveloka, Hoi An Reverie Villas has received many positive reviews thanks to its convenient location, dedicated service and airy spaces. Rooms such as the Deluxe Double With Garden View and the Grand Deluxe Double With River View are favorites among guests for their beautiful views and refined design. The professional and friendly staff is also a factor that earns the hotel guest satisfaction.")),
                                heading(ContentSection.OVERVIEW, of("Khu vực xung quanh Hoi An Reverie Villas", "The area around Hoi An Reverie Villas")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Khu vực xung quanh Hoi An Reverie Villas có nhiều nhà hàng, quán cà phê và cửa hàng tiện lợi, đáp ứng nhu cầu sinh hoạt và ăn uống của du khách. Chợ đêm Hội An và Quan Cong Temple là những địa điểm không thể bỏ qua để trải nghiệm văn hóa và lịch sử của thành phố.", "The area around Hoi An Reverie Villas has many restaurants, cafes and convenience stores that meet travelers' daily and dining needs. The Hoi An Night Market and Quan Cong Temple are must-visit places to experience the culture and history of the city.")),
                                heading(ContentSection.OVERVIEW,
                                                of("Tại sao lựa chọn Hoi An Reverie Villas là một lựa chọn tuyệt vời?", "Why is Hoi An Reverie Villas a great choice?")),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                of("Hoi An Reverie Villas là lựa chọn tuyệt vời cho những ai muốn trải nghiệm kỳ nghỉ tại Hội An với sự tiện nghi và thoải mái. Với các loại phòng từ Superior King đến Family Suite, khách sạn đáp ứng tốt nhu cầu của mọi đối tượng du khách.\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Đội ngũ nhân viên tận tâm, dịch vụ chuyên nghiệp và các tiện ích như Wi-Fi miễn phí, nhà hàng và hồ bơi là điểm cộng lớn, mang lại sự hài lòng và trải nghiệm nghỉ dưỡng tuyệt vời cho khách hàng. Với vị trí gần các điểm tham quan nổi bật, Hoi An Reverie Villas cam kết mang lại cho du khách một kỳ nghỉ trọn vẹn và đáng nhớ tại Hội An.",
                                                                "Hoi An Reverie Villas is a great choice for anyone who wants to experience a stay in Hoi An with convenience and comfort. With room types ranging from the Superior King to the Family Suite, the hotel meets the needs of every type of traveler well.\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "The dedicated staff, professional service and amenities such as free Wi-Fi, a restaurant and a swimming pool are major pluses, bringing guests satisfaction and a wonderful retreat experience. With its location near prominent attractions, Hoi An Reverie Villas is committed to giving travelers a complete and memorable holiday in Hoi An."))

                );

                // --------- Tiện ích cấp khách sạn (Hotel amenities) ----------
                // Chỉ giữ các code thuộc HOTEL scope canonical (xem SeedAmenityCatalog).
                // Các code ROOM scope (TV, MINIBAR, SHOWER, BATHTUB...) đã chuyển vào RoomType.amenityCodes.
                List<AmenityCatalogDoc> hotelAmenities = List.of(

                                // ===== HIGHLIGHT FEATURES =====
                                amenity("wifi_free", of("WiFi miễn phí", "Free WiFi"), AmenityGroup.INTERNET,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("outdoor_pool", of("Hồ bơi ngoài trời", "Outdoor pool"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("airport_transfer", of("Đưa đón sân bay", "Airport transfer"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),

                                // ===== BASIC FACILITIES =====
                                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("family_room", of("Phòng gia đình", "Family room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("terrace", of("Sân thượng / Sân hiên", "Terrace / Patio"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.BASIC_FACILITIES, false),

                                // ===== FOOD & DRINK =====
                                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, true),
                                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, true),
                                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, false),
                                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, false),
                                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, false),

                                // ===== TRANSPORT =====
                                amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("car_rental", of("Cho thuê xe hơi", "Car rental"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),

                                // ===== ENTERTAINMENT =====
                                amenity("spa", of("Dịch vụ spa", "Spa service"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.ENTERTAINMENT, false),
                                amenity("massage", of("Mát-xa", "Massage"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.ENTERTAINMENT, false),
                                amenity("garden", of("Vườn hoa", "Garden"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false),
                                amenity("kids_playground", of("Khu vui chơi trẻ em", "Kids' playground"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false),

                                // ===== NEARBY =====
                                amenity("supermarket", of("Siêu thị gần đó", "Nearby supermarket"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("beauty_salon", of("Salon tóc gần đó", "Nearby hair salon"), AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),

                                // ===== OTHER =====
                                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("tour_desk", of("Hỗ trợ đặt tour", "Tour desk"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("babysitting", of("Giữ trẻ", "Babysitting"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false));

                // --------- Room types & rate plans ----------
                RoomType deluxeDoubleGardenView = RoomType.builder()
                                .id("deluxe-double-garden-view")
                                .name(of("Deluxe Double Room, Garden View", "Deluxe Double Room, Garden View"))
                                .shortDescription(of("Phòng giường đôi cao cấp với ban công nhìn ra vườn", "Premium double room with a garden-view balcony"))
                                .description(of("""
                                                Phòng Deluxe Double rộng rãi với ban công riêng và tầm nhìn ra khu vườn xanh mát.
                                                Phòng được trang bị giường đôi cỡ lớn, cửa kính lớn đón ánh sáng tự nhiên,
                                                phù hợp cho cặp đôi hoặc du khách tìm không gian yên tĩnh.
                                                """, """
                                                A spacious Deluxe Double room with a private balcony overlooking the lush green garden.
                                                The room features a large double bed and floor-to-ceiling windows that bring in natural light,
                                                ideal for couples or travelers seeking a quiet space.
                                                """))
                                .areaSqm(35.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(1)
                                .bedLayoutDescription(of("1 giường đôi lớn", "1 large double bed"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                                .maxAdults(2)
                                .maxChildren(1)
                                .maxGuests(2)
                                .totalRooms(3)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-6b87c5b998f53106be04311d5d0d91ca.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Deluxe Twin với 2 giường đơn",
                                                                "Deluxe Twin Room with 2 single beds", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/0200bf7d_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian thoáng đãng",
                                                                "Airy, open space", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/08e183e4_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng ốc rộng rãi",
                                                                "Spacious room", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/227be54a_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà hướng ánh sáng",
                                                                "Light-filled room", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/3bcd26c3_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Giường rộng êm ái",
                                                                "Wide, comfortable bed", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/50e42bac_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Cửa sổ thoáng đẹp",
                                                                "Bright, airy windows", false, 5),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/8623d7d1_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà vệ sinh sạch sẽ",
                                                                "Clean bathroom", false, 6)))
                                .amenityCodes(List.of(
                                                "AC",
                                                "TV",
                                                "MINIBAR",
                                                "DESK",
                                                "BLACKOUT_CURTAINS",
                                                "PRIVATE_BATHROOM",
                                                "SHOWER_OR_BATHTUB",
                                                "HAIR_DRYER"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí (giống ảnh)
                                                RatePlan.builder()
                                                                .id("deluxe-double-garden-view-standard-prepaid")
                                                                .name(of("Standard Rate", "Standard Rate"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không hoàn tiền", "Non-refundable"))
                                                                .pricePerNight(new BigDecimal("285588"))
                                                                .referencePricePerNight(new BigDecimal("334061"))
                                                                .discountPercent(15)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel(of("Giá tốt", "Great deal"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("deluxe-double-garden-view-standard-pay-at-hotel")
                                                                .name(of("Standard Rate - Pay at Hotel", "Standard Rate - Pay at Hotel"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng của khách sạn", "The hotel's cancellation policy applies"))
                                                                .pricePerNight(new BigDecimal("384170")) // 334061 *
                                                                                                         // 1.15
                                                                .referencePricePerNight(new BigDecimal("514557")) // 447441
                                                                                                                  // *
                                                                                                                  // 1.15
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .promoLabel(of("Thanh toán tại khách sạn", "Pay at hotel"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                RoomType superiorKingRoom = RoomType.builder()
                                .id("superior-king-room")
                                .name(of("Superior King Room", "Superior King Room"))
                                .shortDescription(of("Phòng giường King tiện nghi, không gian ấm cúng", "Comfortable King-bed room with a cozy ambiance"))
                                .description(of("""
                                                Phòng Superior King với giường cỡ lớn, thiết kế hiện đại và đầy đủ tiện nghi cơ bản.
                                                Phù hợp cho cặp đôi hoặc khách công tác cần không gian nghỉ ngơi thoải mái.
                                                """, """
                                                A Superior King room with a large King-size bed, modern design and full basic amenities.
                                                Ideal for couples or business travelers seeking a comfortable place to rest.
                                                """))
                                .areaSqm(32.0)
                                .bedType(BedType.KING)
                                .bedsCount(1)
                                .bedLayoutDescription(of("1 giường King", "1 King bed"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.KING).count(1).build()))
                                .maxAdults(2)
                                .maxChildren(1)
                                .maxGuests(2)
                                .totalRooms(4) // Chỉ còn 4 phòng
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02204120009klrwpi1F49_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Superior King Room với 1 giường đôi",
                                                                "Superior King Room with 1 double bed", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02234120009klrv7b06F8_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian rộng rãi",
                                                                "Spacious space", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02238120009klp1rbD8DF_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà vệ sinh sạch sẽ",
                                                                "Clean bathroom", false, 2)))
                                .amenityCodes(List.of(
                                                "AC",
                                                "TV",
                                                "SLIPPERS",
                                                "SAFE"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("superior-king-standard-prepaid")
                                                                .name(of("Standard Rate", "Standard Rate"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không hoàn tiền", "Non-refundable"))
                                                                .pricePerNight(new BigDecimal("315429"))
                                                                .referencePricePerNight(new BigDecimal("420572"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel(of("Giá tốt", "Great deal"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("superior-king-standard-pay-at-hotel")
                                                                .name(of("Standard Rate - Pay at Hotel", "Standard Rate - Pay at Hotel"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng", "The cancellation policy applies"))
                                                                .pricePerNight(new BigDecimal("418968")) // 364320 *
                                                                                                         // 1.15
                                                                .referencePricePerNight(new BigDecimal("558624")) // 485760
                                                                                                                  // *
                                                                                                                  // 1.15
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .promoLabel(of("Thanh toán tại khách sạn", "Pay at hotel"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                RoomType quadrupleFamilySuitePoolView = RoomType.builder()
                                .id("quadruple-family-suite-pool-view")
                                .name(of("Quadruple Family Suite, Pool View", "Quadruple Family Suite, Pool View"))
                                .shortDescription(of("Suite gia đình rộng rãi, 2 giường đôi, nhìn ra hồ bơi", "Spacious family suite with 2 double beds and a pool view"))
                                .description(of("""
                                                Phòng Suite dành cho gia đình với 2 giường đôi lớn, không gian rộng rãi,
                                                phù hợp cho nhóm bạn hoặc gia đình. Phòng có tầm nhìn ra hồ bơi và đầy đủ tiện nghi cao cấp.
                                                """, """
                                                A family suite with 2 large double beds and a spacious layout,
                                                ideal for groups of friends or families. The room offers a pool view and full premium amenities.
                                                """))
                                .areaSqm(32.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription(of("2 giường đôi lớn", "2 large double beds"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(2)
                                .maxGuests(4)
                                .totalRooms(3)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-fc794bd77592e69e4bd103e30052f029.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Quadruple Family Suite Pool View với 2 giường đơn",
                                                                "Quadruple Family Suite, Pool View with 2 single beds",
                                                                true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-0ef174f942c5232f343d7fcecf28950e.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian rộng rãi",
                                                                "Spacious space", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-46c942937b0a4df235cbc2888e0606c3.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian thoáng đãng",
                                                                "Airy, open space", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-ba7e97cc93069e00ec0e1b000b26d88c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng ốc sạch sẽ",
                                                                "Clean room", false, 3)))
                                .amenityCodes(List.of(
                                                "AC",
                                                "TV",
                                                "DESK",
                                                "PRIVATE_BATHROOM",
                                                "BATHTUB"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("quadruple-family-suite-standard-prepaid")
                                                                .name(of("Standard Rate", "Standard Rate"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không hoàn tiền", "Non-refundable"))
                                                                .pricePerNight(new BigDecimal("670105"))
                                                                .referencePricePerNight(new BigDecimal("783840"))
                                                                .discountPercent(15)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel(of("Giá tốt", "Great deal"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("quadruple-family-suite-standard-pay-at-hotel")
                                                                .name(of("Standard Rate - Pay at Hotel", "Standard Rate - Pay at Hotel"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng", "The cancellation policy applies"))
                                                                .pricePerNight(new BigDecimal("901416")) // 783840 *
                                                                                                         // 1.15
                                                                .referencePricePerNight(new BigDecimal("1201888")) // 1045120
                                                                                                                   // *
                                                                                                                   // 1.15
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .promoLabel(of("Thanh toán tại khách sạn", "Pay at hotel"))
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                // --------- Nearby places ----------
                List<NearbyPlace> nearby = List.of(
                                // Địa điểm lân cận
                                nearby("lang-gom-thanh-ha", of("Làng gốm Thanh Hà", "Thanh Ha Pottery Village"), of("Địa điểm lân cận", "Nearby places"), 565.0),
                                nearby("cong-vien-dat-nung-thanh-ha", of("Công viên Đất nung Thanh Hà", "Thanh Ha Terracotta Park"), of("Địa điểm lân cận", "Nearby places"),
                                                634.0),
                                nearby("huynh-tu-duong", of("Huỳnh Từ Đường", "Huynh Family Temple"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
                                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Địa điểm lân cận", "Nearby places"), 1890.0),
                                nearby("dinh-cam-pho", of("Đình Cẩm Phô", "Cam Pho Communal House"), of("Địa điểm lân cận", "Nearby places"), 1970.0),
                                nearby("old-town-lantern-boat", of("Old Town Lantern Boat", "Old Town Lantern Boat"), of("Địa điểm lân cận", "Nearby places"), 2130.0),
                                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Hoi An Japanese Covered Bridge"), of("Địa điểm lân cận", "Nearby places"), 2170.0),
                                nearby("nha-co-phung-hung", of("Nhà cổ Phùng Hưng", "Phung Hung Ancient House"), of("Địa điểm lân cận", "Nearby places"), 2170.0),
                                nearby("hoi-quan-quang-dong-chua-quang-trieu", of("Hội quán Quảng Đông - Chùa Quảng Triệu", "Cantonese Assembly Hall - Quang Trieu Temple"),
                                                of("Địa điểm lân cận", "Nearby places"), 2200.0),
                                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Địa điểm lân cận", "Nearby places"), 2230.0),
                                nearby("ancient-house", of("Ancient House", "Ancient House"), of("Địa điểm lân cận", "Nearby places"), 2240.0),
                                nearby("bao-tang-van-hoa-sa-huynh", of("Bảo tàng Văn hóa Sa Huỳnh", "Sa Huynh Culture Museum"), of("Địa điểm lân cận", "Nearby places"),
                                                2250.0),
                                nearby("my-grandmas-home-cooking", of("My Grandma's Home Cooking", "My Grandma's Home Cooking"), of("Địa điểm lân cận", "Nearby places"),
                                                2310.0),
                                nearby("duc-an-house", of("Duc An House", "Duc An House"), of("Địa điểm lân cận", "Nearby places"), 2330.0),
                                nearby("nha-tan-ky", of("Nhà Tân Kỳ", "Tan Ky Ancient House"), of("Địa điểm lân cận", "Nearby places"), 2350.0),

                                // Trung tâm giao thông
                                nearby("old-town-lantern-boat-transport", of("Old Town Lantern Boat", "Old Town Lantern Boat"),
                                                of("Trung tâm giao thông", "Transport hub"), 2130.0),
                                nearby("hoi-an-boat-harbor", of("Hoi An Boat Harbor", "Hoi An Boat Harbor"), of("Trung tâm giao thông", "Transport hub"), 2730.0),

                                // Trung tâm giải trí
                                nearby("cho-dem-hoi-an-entertainment", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Trung tâm giải trí", "Entertainment hub"), 2230.0),
                                nearby("chua-cau-hoi-an-entertainment", of("Chùa Cầu Hội An", "Hoi An Japanese Covered Bridge"), of("Trung tâm giải trí", "Entertainment hub"),
                                                2170.0),
                                nearby("quang-truong-song-hoai-entertainment", of("Quảng trường Sông Hoài", "Hoai River Square"),
                                                of("Trung tâm giải trí", "Entertainment hub"), 1890.0),
                                nearby("bao-tang-van-hoa-dan-gian", of("Bảo tàng Văn hóa dân gian", "Museum of Folk Culture"), of("Trung tâm giải trí", "Entertainment hub"),
                                                2560.0),
                                nearby("quan-cong-mieu-entertainment", of("Quan Công Miếu", "Quan Cong Temple"), of("Trung tâm giải trí", "Entertainment hub"), 2740.0),

                                // Khác
                                nearby("quan-cong-mieu", of("Quan Công Miếu", "Quan Cong Temple"), of("Khác", "Other"), 2740.0),
                                nearby("chua-chuc-thanh", of("Chùa Chúc Thánh", "Chuc Thanh Pagoda"), of("Khác", "Other"), 2480.0),
                                nearby("chua-phuoc-lam", of("Chùa Phước Lâm", "Phuoc Lam Pagoda"), of("Khác", "Other"), 2390.0),
                                nearby("dinh-cam-pho-other", of("Đình Cẩm Phô", "Cam Pho Communal House"), of("Khác", "Other"), 1970.0),
                                nearby("my-grandmas-home-cooking-other", of("My Grandma's Home Cooking", "My Grandma's Home Cooking"), of("Khác", "Other"), 2310.0));

                // --------- General info ----------
                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, Nhà hàng, Hồ bơi, Chỗ đậu xe, WiFi", "Air conditioning, Restaurant, Swimming pool, Parking, WiFi"))
                                .distanceToCityCenterKm(2.13)
                                .popularAreaSummary(of("Old Town Lantern Boat, Chợ đêm Hội An, Quan Công Miếu", "Old Town Lantern Boat, Hoi An Night Market, Quan Cong Temple"))
                                .totalRooms(23)
                                .totalFloors(3)
                                .otherHighlightFacilities(of("Bữa sáng với thực đơn gọi món, Quầy bar", "Breakfast with an a la carte menu, Bar"))
                                .interestingPlacesSummary(of("Làng gốm Thanh Hà, Công viên Đất nung Thanh Hà, Huỳnh Từ Đường", "Thanh Ha Pottery Village, Thanh Ha Terracotta Park, Huynh Family Temple"))
                                .build();
                // --------- Policy ----------
                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0)) // 14:00
                                .checkOutUntil(LocalTime.of(12, 0)) // 12:00
                                .items(List.of(

                                                // CHECK-IN / CHECK-OUT
                                                PolicyItem.builder()
                                                                .section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng / trả phòng", "Check-in / check-out times"))
                                                                .content(of("Giờ nhận phòng: 14:00 – 22:00. Giờ trả phòng: 01:00 – 12:00.", "Check-in: 14:00 – 22:00. Check-out: 01:00 – 12:00."))
                                                                .build(),

                                                // HƯỚNG DẪN NHẬN PHÒNG CHUNG
                                                PolicyItem.builder()
                                                                .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                                                                .title(of("Hướng dẫn nhận phòng chung", "General check-in guidelines"))
                                                                .content(of("""
                                                                                                Trẻ em 0–6 tuổi: 1 trẻ được ở miễn phí nếu dùng chung giường.
                                                                                                Trẻ thứ 2 dưới 6 tuổi: phụ thu 50.000 VND/trẻ, dùng chung giường.
                                                                                                Trẻ 6–12 tuổi: phụ thu 80.000 VND/trẻ, dùng chung giường.
                                                                                                Từ 12 tuổi trở lên: tính như người lớn, phụ thu 100.000 VND/người, dùng chung giường.
                                                                                                """
                                                                                                .trim(), """
                                                                                                Children aged 0–6: 1 child stays free of charge when sharing an existing bed.
                                                                                                A second child under 6: surcharge of VND 50,000/child, sharing an existing bed.
                                                                                                Children aged 6–12: surcharge of VND 80,000/child, sharing an existing bed.
                                                                                                Aged 12 and above: charged as an adult, surcharge of VND 100,000/person, sharing an existing bed.
                                                                                                """
                                                                                                .trim()))
                                                                .build(),

                                                // NHẬN PHÒNG SỚM
                                                PolicyItem.builder()
                                                                .section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhận phòng sớm", "Early check-in"))
                                                                .content(of("Nhận phòng sớm có thể được hỗ trợ tùy tình trạng phòng và cần thỏa thuận trực tiếp với nơi lưu trú.", "Early check-in may be available subject to room availability and must be arranged directly with the property."))
                                                                .build(),

                                                // TRẢ PHÒNG MUỘN
                                                PolicyItem.builder()
                                                                .section(PolicySection.LATE_CHECKOUT)
                                                                .title(of("Trả phòng muộn", "Late check-out"))
                                                                .content(of("Trả phòng muộn có thể được hỗ trợ tùy tình trạng phòng và cần thỏa thuận trực tiếp với nơi lưu trú.", "Late check-out may be available subject to room availability and must be arranged directly with the property."))
                                                                .build(),

                                                // ĐỘ TUỔI TỐI THIỂU
                                                PolicyItem.builder()
                                                                .section(PolicySection.MIN_AGE)
                                                                .title(of("Độ tuổi tối thiểu nhận phòng", "Minimum check-in age"))
                                                                .content(of("Không quy định độ tuổi tối thiểu khi nhận phòng.", "There is no minimum age requirement for check-in."))
                                                                .build(),

                                                // KHÁC
                                                PolicyItem.builder()
                                                                .section(PolicySection.OTHER)
                                                                .title(of("Bữa sáng bổ sung", "Additional breakfast"))
                                                                .content(of("Cơ sở lưu trú không phục vụ bữa sáng bổ sung miễn phí.", "The property does not provide complimentary additional breakfast."))
                                                                .build()))
                                .build();

                // --------- Review stats ----------
                // Chưa có: breakdown theo hạng mục (cleanliness, service...) + keywords
                ReviewStats reviewStats = ReviewStats.builder()
                                .cleanlinessScore(null)
                                .roomAmenitiesScore(null)
                                .foodScore(null)
                                .locationScore(null)
                                .serviceScore(null)
                                .keywords(List.of(
                                // Chưa có
                                ))
                                .build();

                // --------- Tax config & booking config ----------
                // UI đang hiển thị “chưa bao gồm thuế và phí” => ưu tiên showPricePreTax = true
                TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
                                // Chưa có: VAT/service fee thực tế
                                .defaultVatPercent(new BigDecimal("8"))
                                .defaultServiceFeePercent(new BigDecimal("7"))
                                .showPricePreTax(false)
                                .build();

                BookingConfig bookingConfig = BookingConfig.builder()
                                .allowFullPayment(true)
                                .allowDeposit(true)
                                .depositPercent(null) // Chưa có
                                .freeCancelMinutes(null) // Chưa có
                                .build();

                HotelFilterFacets filterFacets = HotelFilterFacets.builder()
                                .hasFreeCancellation(true) // có option Pay at hotel + áp dụng chính sách hủy
                                .hasPayAtHotel(true)
                                .hasBreakfastIncluded(false) // ảnh hiển thị “Không gồm bữa sáng”
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

                // --------- FAQs ----------
                // Chưa có: FAQ từ Traveloka cho khách sạn này
                List<FaqItem> faqs = List.of(
                                faq(
                                                of("Những tiện ích tại Hoi An Reverie Villas?", "What amenities are available at Hoi An Reverie Villas?"),
                                                of("Những tiện ích tại Hoi An Reverie Villas bao gồm Máy lạnh, Nhà hàng, Hồ bơi, Chỗ đậu xe và WiFi. Một số dịch vụ có thể yêu cầu phụ phí.", "Amenities at Hoi An Reverie Villas include air conditioning, a restaurant, a swimming pool, parking and WiFi. Some services may incur a surcharge.")),
                                faq(
                                                of("Hoi An Reverie Villas có mức giá là bao nhiêu?", "How much does Hoi An Reverie Villas cost?"),
                                                of("Phòng tại Hoi An Reverie Villas có giá từ khoảng 329.854 VND. Giá có thể thay đổi tùy theo thời điểm và loại phòng bạn chọn.", "Rooms at Hoi An Reverie Villas start from around VND 329,854. Prices may vary depending on the date and the room type you choose.")),
                                faq(
                                                of("Thời gian nhận phòng và trả phòng của Hoi An Reverie Villas?", "What are the check-in and check-out times at Hoi An Reverie Villas?"),
                                                of("Thời gian nhận phòng tại Hoi An Reverie Villas là từ 14:00 – 22:00 và thời gian trả phòng từ 01:00 – 12:00.", "Check-in at Hoi An Reverie Villas is from 14:00 – 22:00 and check-out is from 01:00 – 12:00.")),
                                faq(
                                                of("Hoi An Reverie Villas có phục vụ ăn sáng không?", "Does Hoi An Reverie Villas serve breakfast?"),
                                                of("Có, Hoi An Reverie Villas có cung cấp bữa sáng. Tuy nhiên, bạn cần chọn loại phòng có bao gồm bữa sáng. Nếu không, có thể phát sinh phụ phí.", "Yes, Hoi An Reverie Villas offers breakfast. However, you need to choose a room type that includes breakfast; otherwise, a surcharge may apply.")),
                                faq(
                                                of("Khách sạn Hoi An Reverie Villas đạt tiêu chuẩn mấy sao?", "What star rating does Hoi An Reverie Villas have?"),
                                                of("Hoi An Reverie Villas là khách sạn đạt tiêu chuẩn 2.5 sao.", "Hoi An Reverie Villas is a 2.5-star hotel.")),
                                faq(
                                                of("Hoi An Reverie Villas có bao nhiêu tầng lầu?", "How many floors does Hoi An Reverie Villas have?"),
                                                of("Hoi An Reverie Villas có tổng cộng 3 tầng.", "Hoi An Reverie Villas has a total of 3 floors.")),
                                faq(
                                                of("Tổng số phòng hiện có tại Hoi An Reverie Villas là bao nhiêu?", "What is the total number of rooms at Hoi An Reverie Villas?"),
                                                of("Tổng số phòng tại Hoi An Reverie Villas là 23 phòng.", "Hoi An Reverie Villas has a total of 23 rooms.")),
                                faq(
                                                of("Hoi An Reverie Villas có chỗ đậu xe không?", "Does Hoi An Reverie Villas have parking?"),
                                                of("Có, Hoi An Reverie Villas có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, Hoi An Reverie Villas offers parking. Please contact the hotel directly to confirm the details.")),
                                faq(
                                                of("Hoi An Reverie Villas có hồ bơi không?", "Does Hoi An Reverie Villas have a swimming pool?"),
                                                of("Có, Hoi An Reverie Villas có hồ bơi phục vụ khách lưu trú.", "Yes, Hoi An Reverie Villas has a swimming pool for guests.")),
                                faq(
                                                of("Hoi An Reverie Villas có hỗ trợ nhận phòng sớm không?", "Does Hoi An Reverie Villas offer early check-in?"),
                                                of("Có, Hoi An Reverie Villas hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, Hoi An Reverie Villas offers early check-in subject to room availability. Please contact the hotel directly to arrange it.")),
                                faq(
                                                of("Hoi An Reverie Villas có dịch vụ đưa đón sân bay không?", "Does Hoi An Reverie Villas offer airport transfers?"),
                                                of("Có, Hoi An Reverie Villas cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ trực tiếp với khách sạn để biết thêm chi tiết.", "Yes, Hoi An Reverie Villas offers airport transfers on request. Please contact the hotel directly for more details.")),
                                faq(
                                                of("Đánh giá của khách lưu trú trước đây về Hoi An Reverie Villas như thế nào?", "What did previous guests think of Hoi An Reverie Villas?"),
                                                of("Hoi An Reverie Villas được đánh giá cao về Vệ sinh, Tiện nghi phòng, Dịch vụ và Tiện ích, theo nhận xét từ các khách hàng xác thực trên Traveloka.", "Hoi An Reverie Villas is highly rated for Cleanliness, Room Amenities, Service and Facilities, according to reviews from verified guests on Traveloka.")),
                                faq(
                                                of("Những địa điểm nổi bật nào nằm gần khách sạn Hoi An Reverie Villas?", "What notable attractions are near Hoi An Reverie Villas?"),
                                                of("Một số địa điểm nổi bật gần khách sạn bao gồm Làng gốm Thanh Hà, Công viên Đất nung Thanh Hà, Huỳnh Từ Đường, Quảng trường Sông Hoài và Đình Cẩm Phô.", "Some notable attractions near the hotel include Thanh Ha Pottery Village, Thanh Ha Terracotta Park, Huynh Family Temple, Hoai River Square and Cam Pho Communal House.")),
                                faq(
                                                of("Những loại phòng đang khả dụng tại Hoi An Reverie Villas?", "What room types are available at Hoi An Reverie Villas?"),
                                                of("Các loại phòng đang khả dụng tại Hoi An Reverie Villas bao gồm Deluxe Double Room Garden View, Superior King Room và Quadruple Family Suite Pool View.", "The room types available at Hoi An Reverie Villas include Deluxe Double Room Garden View, Superior King Room and Quadruple Family Suite Pool View.")),
                                faq(
                                                of("Phòng tại Hoi An Reverie Villas được trang bị những tiện nghi gì?", "What amenities are the rooms at Hoi An Reverie Villas equipped with?"),
                                                of("Hầu hết các phòng được trang bị Máy lạnh, Bồn tắm hoặc vòi sen, Khu vực chờ, Rèm chắn sáng, Nước đóng chai miễn phí cùng nhiều tiện nghi khác. Tiện nghi có thể khác nhau tùy theo loại phòng.", "Most rooms are equipped with air conditioning, a bathtub or shower, a seating area, blackout curtains, complimentary bottled water and many other amenities. Amenities may vary by room type.")),
                                faq(
                                                of("Mỗi phòng tại Hoi An Reverie Villas có thể lưu trú tối đa bao nhiêu khách?", "What is the maximum number of guests per room at Hoi An Reverie Villas?"),
                                                of("Hoi An Reverie Villas có thể chứa tối đa 4 khách trong một phòng, tùy thuộc vào loại phòng bạn đặt. Sức chứa cụ thể sẽ được hiển thị tại thời điểm đặt phòng.", "Hoi An Reverie Villas can accommodate up to 4 guests per room, depending on the room type you book. The exact capacity will be shown at the time of booking.")));

                // --------- Build hotel doc ----------
                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName(of("Hội An", "Hoi An"))
                                .districtName(of("Thanh Hà", "Thanh Ha"))
                                .wardName(null)
                                .addressLine(of("257 Hai Mươi Tám Tháng Ba, Hội An, Quảng Nam, Việt Nam", "257 Hai Muoi Tam Thang Ba, Hoi An, Quang Nam, Vietnam"))
                                // Chưa có: tọa độ (lon, lat) chính xác
                                .location(new double[] { 108.3055, 15.8802 })
                                .name(of("Hoi An Reverie Villas", "Hoi An Reverie Villas"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3) // ảnh hiển thị 3 sao
                                .shortDescription(of("Khách sạn tại Thanh Hà, Hội An – phù hợp lưu trú tiết kiệm, gần các điểm tham quan.", "A hotel in Thanh Ha, Hoi An - ideal for a budget-friendly stay, close to the attractions."))
                                .description(of("Hoi An Reverie Villas là cơ sở lưu trú tại Thanh Hà, Hội An. "
                                                                + "Phù hợp cho du khách muốn ở yên bình nhưng vẫn thuận tiện di chuyển đến các điểm tham quan.",
                                                "Hoi An Reverie Villas is a property in Thanh Ha, Hoi An. "
                                                                + "Ideal for travelers who want a peaceful stay while remaining conveniently close to the attractions."))
                                .phone(null) // Chưa có
                                .email(null) // Chưa có
                                .website(null) // Chưa có
                                .defaultCheckInTime(null) // Chưa có
                                .defaultCheckOutTime(null) // Chưa có
                                .hasOnlineCheckin(false) // Chưa có: thông tin check-in online

                                .avgRating(8.2)
                                .reviewsCount(357)
                                .ratingLabel(of("Rất tốt", "Very good"))

                                .minNightlyPrice(new BigDecimal("285588"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("394608"))
                                .discountPercent(28)

                                .taxConfig(taxConfig)
                                .bookingConfig(bookingConfig)
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
                                .roomTypes(List.of(deluxeDoubleGardenView, superiorKingRoom,
                                                quadrupleFamilySuitePoolView))
                                .nearbyPlaces(nearby)

                                .policy(policy)
                                .generalInfo(generalInfo)
                                .faqs(faqs)
                                .reviewStats(reviewStats)

                                .publisher(publisher)
                                .moderation(moderation)
                                .build();
        }

        // keyword helper (nếu cần cho ReviewStats)
        @SuppressWarnings("unused")
        private static ReviewKeywordStat keyword(String code, String label, Integer count) {
                return ReviewKeywordStat.builder()
                                .code(code)
                                .label(vi(label))
                                .count(count)
                                .build();
        }
}
