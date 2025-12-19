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

@Configuration
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
                                                "Phòng ngủ giường King tại Hoi An Reverie Villas", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x680-FIT_AND_TRIM-f62d0313fcb71f300e606725957ca3ff.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vườn và sân hiên xanh mát tại Hoi An Reverie Villas", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-9f263d4cbe9f8dd2f66e669ccc6d25d7.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Mặt tiền khu lưu trú Hoi An Reverie Villas", false, 2),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-15c938e5649f2fd8c537225dd6ad91ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng gia đình rộng rãi tại Hoi An Reverie Villas", false, 3),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x683-FIT_AND_TRIM-72a9aaecd28173cf211267f7e94c905f.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Không gian phòng gia đình (góc nhìn khác)", false, 4),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-1024x683-FIT_AND_TRIM-0bd3fbedd7c1d13269a51e2148fe2578.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực/tiện ích di chuyển (xe/đưa đón) tại Hoi An Reverie Villas",
                                                false, 5),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-dbfbfa101b4a8045f97aa1e3ed4147d3.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Góc ngồi thư giãn ngoài trời", false, 6),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-7edee55e6501d033826ecbad666aea1c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Toàn cảnh Hoi An Reverie Villas (góc nhìn trên cao)", false, 7),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-0ef174f942c5232f343d7fcecf28950e.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu lưu trú nhìn từ phía trước", false, 8),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-4839a0ff5539b2c48d1f0d2ef6d17b02.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực sinh hoạt chung / không gian tụ tập", false, 9),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-53e14ba4d52911992f7b1090f08cf55c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Ban công/không gian ngoài trời buổi tối", false, 10));

                // --------- Content (Overview + Story) ----------
                // Ảnh bạn gửi có modal "Giới thiệu" dạng đoạn văn. Mình seed vào OVERVIEW.
                // Chưa có: STORY dài (nếu bạn gửi thêm ảnh/story thì mình seed tiếp)
                List<ContentBlock> content = List.of(
                                paragraph(ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas là một villa khách sạn 2 sao tọa lạc tại 257 Hai Mươi Tám Tháng Ba, Hội An, Tỉnh Quảng Nam, Thành phố Đà Nẵng, Việt Nam, 560000, Hội An. Với thiết kế thanh lịch và không gian gần gũi với thiên nhiên, Hoi An Reverie Villas mang đến cho du khách một trải nghiệm nghỉ dưỡng yên bình và thư giãn giữa lòng phố cổ. Tọa lạc tại vị trí thuận tiện, gần các điểm tham quan nổi tiếng, Hoi An Reverie Villas là lựa chọn lý tưởng cho cả du khách thích khám phá và những ai muốn tận hưởng sự yên tĩnh. Sự kết hợp giữa không gian mở và dịch vụ chu đáo tại Hoi An Reverie Villas hứa hẹn mang lại cho du khách một kỳ nghỉ đáng nhớ."),
                                heading(ContentSection.OVERVIEW,
                                                "Những loại phòng đang khả dụng tại Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas cung cấp nhiều loại phòng với thiết kế tinh tế và đầy đủ tiện nghi để đáp ứng nhu cầu đa dạng của du khách:\r\n"
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
                                                                "Family Suite: Phòng gia đình rộng rãi với nhiều giường và không gian sinh hoạt thoải mái, phù hợp cho các gia đình lớn."),
                                heading(ContentSection.OVERVIEW, "Các điểm tham quan gần Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas có vị trí thuận lợi, giúp du khách dễ dàng di chuyển đến các điểm tham quan nổi tiếng:\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Old Town Lantern Boat: Cách khách sạn khoảng 2.13 km, nơi du khách có thể trải nghiệm thả đèn lồng và tham quan phố cổ trên thuyền.\r\n"
                                                                + //
                                                                "Chợ đêm Hội An: Cách Hoi An Reverie Villas 2.23 km, là địa điểm lý tưởng để khám phá ẩm thực đường phố, mua sắm và tận hưởng không khí sôi động về đêm.\r\n"
                                                                + //
                                                                "Quan Cong Temple: Nằm cách Hoi An Reverie Villas 2.74 km, là một trong những ngôi đền nổi tiếng với kiến trúc độc đáo và lịch sử lâu đời."),
                                heading(ContentSection.OVERVIEW, "Những tiện nghi nổi bật tại Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas mang đến cho du khách nhiều tiện nghi để kỳ nghỉ trở nên thoải mái và dễ chịu:\r\n"
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
                                                                "Wi-Fi miễn phí: Phủ sóng toàn bộ khu vực, giúp du khách dễ dàng kết nối internet để làm việc hoặc giải trí."),
                                heading(ContentSection.OVERVIEW, "Phương tiện công cộng gần Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas nằm gần các tuyến đường lớn, giúp du khách dễ dàng tiếp cận các phương tiện công cộng như xe buýt và taxi. Dịch vụ xe ôm công nghệ cũng có sẵn để du khách di chuyển thuận tiện đến các điểm tham quan khác trong thành phố."),
                                heading(ContentSection.OVERVIEW, "Dịch vụ tại Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Đội ngũ nhân viên tại Hoi An Reverie Villas luôn sẵn sàng hỗ trợ du khách với mọi nhu cầu. Dịch vụ lễ tân 24/7 đảm bảo sự phục vụ tận tâm từ lúc nhận phòng đến khi trả phòng. Dịch vụ dọn phòng hàng ngày giúp không gian luôn sạch sẽ và ngăn nắp. Khách sạn cũng cung cấp các dịch vụ khác như giặt ủi và bảo quản hành lý, rất hữu ích cho những khách hàng có nhu cầu lưu trú dài ngày."),
                                heading(ContentSection.OVERVIEW,
                                                "Đánh giá của khách hàng trên Traveloka về Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Trên Traveloka, Hoi An Reverie Villas nhận được nhiều đánh giá tích cực nhờ vào vị trí thuận tiện, dịch vụ tận tâm và không gian thoáng mát. Các phòng như Deluxe Double With Garden View và Grand Deluxe Double With River View được khách hàng yêu thích vì tầm nhìn đẹp và thiết kế tinh tế. Đội ngũ nhân viên chuyên nghiệp và thân thiện cũng là yếu tố giúp khách sạn nhận được sự hài lòng từ du khách."),
                                heading(ContentSection.OVERVIEW, "Khu vực xung quanh Hoi An Reverie Villas"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Khu vực xung quanh Hoi An Reverie Villas có nhiều nhà hàng, quán cà phê và cửa hàng tiện lợi, đáp ứng nhu cầu sinh hoạt và ăn uống của du khách. Chợ đêm Hội An và Quan Cong Temple là những địa điểm không thể bỏ qua để trải nghiệm văn hóa và lịch sử của thành phố."),
                                heading(ContentSection.OVERVIEW,
                                                "Tại sao lựa chọn Hoi An Reverie Villas là một lựa chọn tuyệt vời?"),
                                paragraph(
                                                ContentSection.OVERVIEW,
                                                "Hoi An Reverie Villas là lựa chọn tuyệt vời cho những ai muốn trải nghiệm kỳ nghỉ tại Hội An với sự tiện nghi và thoải mái. Với các loại phòng từ Superior King đến Family Suite, khách sạn đáp ứng tốt nhu cầu của mọi đối tượng du khách.\r\n"
                                                                + //
                                                                "\r\n" + //
                                                                "Đội ngũ nhân viên tận tâm, dịch vụ chuyên nghiệp và các tiện ích như Wi-Fi miễn phí, nhà hàng và hồ bơi là điểm cộng lớn, mang lại sự hài lòng và trải nghiệm nghỉ dưỡng tuyệt vời cho khách hàng. Với vị trí gần các điểm tham quan nổi bật, Hoi An Reverie Villas cam kết mang lại cho du khách một kỳ nghỉ trọn vẹn và đáng nhớ tại Hội An.")

                );

                // --------- Tiện ích cấp khách sạn (Hotel amenities) ----------
                // Từ ảnh: tiện ích chính có Máy lạnh, WiFi, Nhà hàng, Hồ bơi, Chỗ đậu xe
                List<AmenityCatalogDoc> hotelAmenities = List.of(

                                // ===== HIGHLIGHT FEATURES (tính năng nổi bật) =====
                                amenity("free_wifi", "WiFi miễn phí", AmenityGroup.INTERNET,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("outdoor_pool", "Hồ bơi ngoài trời", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("air_conditioning", "Máy lạnh", AmenityGroup.ROOM,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("parking", "Bãi đậu xe", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),
                                amenity("airport_shuttle", "Đưa đón sân bay", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.HIGHLIGHT_FEATURES, true),

                                // ===== BASIC FACILITIES (tiện nghi cơ bản) =====
                                amenity("24h_front_desk", "Lễ tân 24 giờ", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES,
                                                false),
                                amenity("family_rooms", "Phòng gia đình", AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("non_smoking_rooms", "Phòng không hút thuốc", AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("smoking_area", "Khu vực hút thuốc", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.BASIC_FACILITIES,
                                                false),
                                amenity("terrace", "Sân thượng / Sân hiên", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.BASIC_FACILITIES,
                                                false),

                                // ===== ROOM FACILITIES (tiện nghi phòng) =====
                                amenity("tv", "TV", AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                                amenity("minibar", "Minibar", AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                                amenity("work_desk", "Bàn làm việc", AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES,
                                                false),
                                amenity("in_room_safe", "Két an toàn trong phòng", AmenityGroup.ROOM,
                                                AmenitySection.ROOM_FACILITIES,
                                                false),
                                amenity("blackout_curtains", "Rèm cản sáng", AmenityGroup.ROOM,
                                                AmenitySection.ROOM_FACILITIES, false),

                                // ===== BATHROOM (phòng tắm) =====
                                amenity("shower", "Vòi sen", AmenityGroup.ROOM, AmenitySection.BATHROOM, false),
                                amenity("bathtub", "Bồn tắm", AmenityGroup.ROOM, AmenitySection.BATHROOM, false),
                                amenity("hair_dryer", "Máy sấy tóc", AmenityGroup.ROOM, AmenitySection.BATHROOM, false),
                                amenity("towels", "Khăn tắm", AmenityGroup.ROOM, AmenitySection.BATHROOM, false),
                                amenity("toiletries", "Đồ vệ sinh cá nhân", AmenityGroup.ROOM, AmenitySection.BATHROOM,
                                                false),

                                // ===== FOOD & DRINK (ẩm thực) =====
                                amenity("restaurant", "Nhà hàng", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, true),
                                amenity("breakfast", "Bữa sáng", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, true),
                                amenity("bar", "Quầy bar", AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK,
                                                false),
                                amenity("coffee_shop", "Tiệm cà phê", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, false),
                                amenity("room_service", "Dịch vụ phòng", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK,
                                                false),

                                // ===== TRANSPORT (đưa đón / di chuyển) =====
                                amenity("bicycle_rental", "Cho thuê xe đạp", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT,
                                                false),
                                amenity("car_rental", "Cho thuê xe hơi", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("valet_parking", "Bãi đậu xe có người phục vụ", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT, false),
                                amenity("secured_parking", "Bãi đậu xe an ninh", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT,
                                                false),

                                // ===== INTERNET =====
                                amenity("internet_access", "Điểm truy cập Internet", AmenityGroup.INTERNET,
                                                AmenitySection.INTERNET,
                                                false),

                                // ===== ENTERTAINMENT (giải trí) =====
                                amenity("spa", "Dịch vụ spa", AmenityGroup.HOTEL_SERVICE, AmenitySection.ENTERTAINMENT,
                                                false),
                                amenity("massage", "Mát-xa", AmenityGroup.HOTEL_SERVICE, AmenitySection.ENTERTAINMENT,
                                                false),
                                amenity("garden", "Vườn hoa", AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT,
                                                false),
                                amenity("kids_playground", "Khu vui chơi trẻ em", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false),
                                amenity("tv_room", "Phòng xem TV", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false),

                                // ===== NEARBY =====
                                amenity("supermarket_nearby", "Siêu thị gần đó", AmenityGroup.NEARBY,
                                                AmenitySection.OTHER, false),
                                amenity("salon_nearby", "Salon tóc gần đó", AmenityGroup.NEARBY, AmenitySection.OTHER,
                                                false),

                                // ===== OTHER =====
                                amenity("luggage_storage", "Giữ hành lý", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("tour_assistance", "Hỗ trợ đặt tour", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("babysitting", "Giữ trẻ", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER,
                                                false));

                // --------- Room types & rate plans ----------
                RoomType deluxeDoubleGardenView = RoomType.builder()
                                .id("deluxe-double-garden-view")
                                .name("Deluxe Double Room, Garden View")
                                .shortDescription("Phòng giường đôi cao cấp với ban công nhìn ra vườn")
                                .description("""
                                                Phòng Deluxe Double rộng rãi với ban công riêng và tầm nhìn ra khu vườn xanh mát.
                                                Phòng được trang bị giường đôi cỡ lớn, cửa kính lớn đón ánh sáng tự nhiên,
                                                phù hợp cho cặp đôi hoặc du khách tìm không gian yên tĩnh.
                                                """)
                                .areaSqm(35.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(1)
                                .bedLayoutDescription("1 giường đôi lớn")
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                                .maxAdults(2)
                                .maxChildren(1)
                                .maxGuests(2)
                                .totalRooms(3)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-6b87c5b998f53106be04311d5d0d91ca.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Deluxe Twin với 2 giường đơn", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/0200bf7d_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian thoáng đãng", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/08e183e4_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng ốc rộng rãi", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/227be54a_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà hướng ánh sáng", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/3bcd26c3_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Giường rộng êm ái", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/50e42bac_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Cửa sổ thoáng đẹp", false, 5),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/23000000/22450000/22442300/22442222/8623d7d1_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà vệ sinh sạch sẽ", false, 6)))
                                .amenityCodes(List.of(
                                                "AIR_CONDITIONING",
                                                "FREE_WIFI",
                                                "TV",
                                                "MINIBAR",
                                                "WORK_DESK",
                                                "BLACKOUT_CURTAINS",
                                                "PRIVATE_BATHROOM",
                                                "SHOWER_OR_BATHTUB",
                                                "HAIR_DRYER"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí (giống ảnh)
                                                RatePlan.builder()
                                                                .id("deluxe-double-garden-view-standard-prepaid")
                                                                .name("Standard Rate")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy("Không hoàn tiền")
                                                                .pricePerNight(new BigDecimal("285588"))
                                                                .referencePricePerNight(new BigDecimal("334061"))
                                                                .discountPercent(15)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel("Giá tốt")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("deluxe-double-garden-view-standard-pay-at-hotel")
                                                                .name("Standard Rate - Pay at Hotel")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(
                                                                                "Áp dụng chính sách hủy phòng của khách sạn")
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
                                                                .promoLabel("Thanh toán tại khách sạn")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                RoomType superiorKingRoom = RoomType.builder()
                                .id("superior-king-room")
                                .name("Superior King Room")
                                .shortDescription("Phòng giường King tiện nghi, không gian ấm cúng")
                                .description("""
                                                Phòng Superior King với giường cỡ lớn, thiết kế hiện đại và đầy đủ tiện nghi cơ bản.
                                                Phù hợp cho cặp đôi hoặc khách công tác cần không gian nghỉ ngơi thoải mái.
                                                """)
                                .areaSqm(32.0)
                                .bedType(BedType.KING)
                                .bedsCount(1)
                                .bedLayoutDescription("1 giường King")
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.KING).count(1).build()))
                                .maxAdults(2)
                                .maxChildren(1)
                                .maxGuests(2)
                                .totalRooms(4) // Chỉ còn 4 phòng
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02204120009klrwpi1F49_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Superior King Room với 1 giường đôi", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02234120009klrv7b06F8_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian rộng rãi", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/oJLNzNs71wS3RVcWVniLgofXtaluprJ7ristt-jspoM=/images//02238120009klp1rbD8DF_R5_Mtrip.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà vệ sinh sạch sẽ", false, 2)))
                                .amenityCodes(List.of(
                                                "AIR_CONDITIONING",
                                                "FREE_WIFI",
                                                "TV",
                                                "SLIPPERS",
                                                "SAFE"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("superior-king-standard-prepaid")
                                                                .name("Standard Rate")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy("Không hoàn tiền")
                                                                .pricePerNight(new BigDecimal("315429"))
                                                                .referencePricePerNight(new BigDecimal("420572"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel("Giá tốt")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("superior-king-standard-pay-at-hotel")
                                                                .name("Standard Rate - Pay at Hotel")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy("Áp dụng chính sách hủy phòng")
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
                                                                .promoLabel("Thanh toán tại khách sạn")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                RoomType quadrupleFamilySuitePoolView = RoomType.builder()
                                .id("quadruple-family-suite-pool-view")
                                .name("Quadruple Family Suite, Pool View")
                                .shortDescription("Suite gia đình rộng rãi, 2 giường đôi, nhìn ra hồ bơi")
                                .description("""
                                                Phòng Suite dành cho gia đình với 2 giường đôi lớn, không gian rộng rãi,
                                                phù hợp cho nhóm bạn hoặc gia đình. Phòng có tầm nhìn ra hồ bơi và đầy đủ tiện nghi cao cấp.
                                                """)
                                .areaSqm(32.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription("2 giường đôi lớn")
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(2)
                                .maxGuests(4)
                                .totalRooms(3)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-fc794bd77592e69e4bd103e30052f029.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Quadruple Family Suite Pool View với 2 giường đơn",
                                                                true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-0ef174f942c5232f343d7fcecf28950e.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian rộng rãi", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-46c942937b0a4df235cbc2888e0606c3.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian thoáng đãng", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029306-ba7e97cc93069e00ec0e1b000b26d88c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng ốc sạch sẽ", false, 3)))
                                .amenityCodes(List.of(
                                                "AIR_CONDITIONING",
                                                "FREE_WIFI",
                                                "TV",
                                                "WORK_DESK",
                                                "PRIVATE_BATHROOM",
                                                "BATHTUB"))
                                .ratePlans(List.of(
                                                // Plan 1: CHƯA bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("quadruple-family-suite-standard-prepaid")
                                                                .name("Standard Rate")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy("Không hoàn tiền")
                                                                .pricePerNight(new BigDecimal("670105"))
                                                                .referencePricePerNight(new BigDecimal("783840"))
                                                                .discountPercent(15)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .promoLabel("Giá tốt")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build(),

                                                // Plan 2: ĐÃ bao gồm thuế & phí
                                                RatePlan.builder()
                                                                .id("quadruple-family-suite-standard-pay-at-hotel")
                                                                .name("Standard Rate - Pay at Hotel")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy("Áp dụng chính sách hủy phòng")
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
                                                                .promoLabel("Thanh toán tại khách sạn")
                                                                .showLowAvailability(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .build()))
                                .build();

                // --------- Nearby places ----------
                List<NearbyPlace> nearby = List.of(
                                // Địa điểm lân cận
                                nearby("lang-gom-thanh-ha", "Làng gốm Thanh Hà", "Địa điểm lân cận", 565.0),
                                nearby("cong-vien-dat-nung-thanh-ha", "Công viên Đất nung Thanh Hà", "Địa điểm lân cận",
                                                634.0),
                                nearby("huynh-tu-duong", "Huỳnh Từ Đường", "Địa điểm lân cận", 1500.0),
                                nearby("quang-truong-song-hoai", "Quảng trường Sông Hoài", "Địa điểm lân cận", 1890.0),
                                nearby("dinh-cam-pho", "Đình Cẩm Phô", "Địa điểm lân cận", 1970.0),
                                nearby("old-town-lantern-boat", "Old Town Lantern Boat", "Địa điểm lân cận", 2130.0),
                                nearby("chua-cau-hoi-an", "Chùa Cầu Hội An", "Địa điểm lân cận", 2170.0),
                                nearby("nha-co-phung-hung", "Nhà cổ Phùng Hưng", "Địa điểm lân cận", 2170.0),
                                nearby("hoi-quan-quang-dong-chua-quang-trieu", "Hội quán Quảng Đông - Chùa Quảng Triệu",
                                                "Địa điểm lân cận", 2200.0),
                                nearby("cho-dem-hoi-an", "Chợ đêm Hội An", "Địa điểm lân cận", 2230.0),
                                nearby("ancient-house", "Ancient House", "Địa điểm lân cận", 2240.0),
                                nearby("bao-tang-van-hoa-sa-huynh", "Bảo tàng Văn hóa Sa Huỳnh", "Địa điểm lân cận",
                                                2250.0),
                                nearby("my-grandmas-home-cooking", "My Grandma's Home Cooking", "Địa điểm lân cận",
                                                2310.0),
                                nearby("duc-an-house", "Duc An House", "Địa điểm lân cận", 2330.0),
                                nearby("nha-tan-ky", "Nhà Tân Kỳ", "Địa điểm lân cận", 2350.0),

                                // Trung tâm giao thông
                                nearby("old-town-lantern-boat-transport", "Old Town Lantern Boat",
                                                "Trung tâm giao thông", 2130.0),
                                nearby("hoi-an-boat-harbor", "Hoi An Boat Harbor", "Trung tâm giao thông", 2730.0),

                                // Trung tâm giải trí
                                nearby("cho-dem-hoi-an-entertainment", "Chợ đêm Hội An", "Trung tâm giải trí", 2230.0),
                                nearby("chua-cau-hoi-an-entertainment", "Chùa Cầu Hội An", "Trung tâm giải trí",
                                                2170.0),
                                nearby("quang-truong-song-hoai-entertainment", "Quảng trường Sông Hoài",
                                                "Trung tâm giải trí", 1890.0),
                                nearby("bao-tang-van-hoa-dan-gian", "Bảo tàng Văn hóa dân gian", "Trung tâm giải trí",
                                                2560.0),
                                nearby("quan-cong-mieu-entertainment", "Quan Công Miếu", "Trung tâm giải trí", 2740.0),

                                // Khác
                                nearby("quan-cong-mieu", "Quan Công Miếu", "Khác", 2740.0),
                                nearby("chua-chuc-thanh", "Chùa Chúc Thánh", "Khác", 2480.0),
                                nearby("chua-phuoc-lam", "Chùa Phước Lâm", "Khác", 2390.0),
                                nearby("dinh-cam-pho-other", "Đình Cẩm Phô", "Khác", 1970.0),
                                nearby("my-grandmas-home-cooking-other", "My Grandma's Home Cooking", "Khác", 2310.0));

                // --------- General info ----------
                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary("Máy lạnh, Nhà hàng, Hồ bơi, Chỗ đậu xe, WiFi")
                                .distanceToCityCenterKm(2.13)
                                .popularAreaSummary("Old Town Lantern Boat, Chợ đêm Hội An, Quan Công Miếu")
                                .totalRooms(23)
                                .totalFloors(3)
                                .otherHighlightFacilities("Bữa sáng với thực đơn gọi món, Quầy bar")
                                .interestingPlacesSummary(
                                                "Làng gốm Thanh Hà, Công viên Đất nung Thanh Hà, Huỳnh Từ Đường")
                                .build();
                // --------- Policy ----------
                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0)) // 14:00
                                .checkOutUntil(LocalTime.of(12, 0)) // 12:00
                                .items(List.of(

                                                // CHECK-IN / CHECK-OUT
                                                PolicyItem.builder()
                                                                .section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title("Thời gian nhận phòng / trả phòng")
                                                                .content("Giờ nhận phòng: 14:00 – 22:00. Giờ trả phòng: 01:00 – 12:00.")
                                                                .build(),

                                                // HƯỚNG DẪN NHẬN PHÒNG CHUNG
                                                PolicyItem.builder()
                                                                .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                                                                .title("Hướng dẫn nhận phòng chung")
                                                                .content(
                                                                                """
                                                                                                Trẻ em 0–6 tuổi: 1 trẻ được ở miễn phí nếu dùng chung giường.
                                                                                                Trẻ thứ 2 dưới 6 tuổi: phụ thu 50.000 VND/trẻ, dùng chung giường.
                                                                                                Trẻ 6–12 tuổi: phụ thu 80.000 VND/trẻ, dùng chung giường.
                                                                                                Từ 12 tuổi trở lên: tính như người lớn, phụ thu 100.000 VND/người, dùng chung giường.
                                                                                                """
                                                                                                .trim())
                                                                .build(),

                                                // NHẬN PHÒNG SỚM
                                                PolicyItem.builder()
                                                                .section(PolicySection.EARLY_CHECKIN)
                                                                .title("Nhận phòng sớm")
                                                                .content(
                                                                                "Nhận phòng sớm có thể được hỗ trợ tùy tình trạng phòng và cần thỏa thuận trực tiếp với nơi lưu trú.")
                                                                .build(),

                                                // TRẢ PHÒNG MUỘN
                                                PolicyItem.builder()
                                                                .section(PolicySection.LATE_CHECKOUT)
                                                                .title("Trả phòng muộn")
                                                                .content(
                                                                                "Trả phòng muộn có thể được hỗ trợ tùy tình trạng phòng và cần thỏa thuận trực tiếp với nơi lưu trú.")
                                                                .build(),

                                                // ĐỘ TUỔI TỐI THIỂU
                                                PolicyItem.builder()
                                                                .section(PolicySection.MIN_AGE)
                                                                .title("Độ tuổi tối thiểu nhận phòng")
                                                                .content("Không quy định độ tuổi tối thiểu khi nhận phòng.")
                                                                .build(),

                                                // KHÁC
                                                PolicyItem.builder()
                                                                .section(PolicySection.OTHER)
                                                                .title("Bữa sáng bổ sung")
                                                                .content("Cơ sở lưu trú không phục vụ bữa sáng bổ sung miễn phí.")
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
                                                "Những tiện ích tại Hoi An Reverie Villas?",
                                                "Những tiện ích tại Hoi An Reverie Villas bao gồm Máy lạnh, Nhà hàng, Hồ bơi, Chỗ đậu xe và WiFi. Một số dịch vụ có thể yêu cầu phụ phí."),
                                faq(
                                                "Hoi An Reverie Villas có mức giá là bao nhiêu?",
                                                "Phòng tại Hoi An Reverie Villas có giá từ khoảng 329.854 VND. Giá có thể thay đổi tùy theo thời điểm và loại phòng bạn chọn."),
                                faq(
                                                "Thời gian nhận phòng và trả phòng của Hoi An Reverie Villas?",
                                                "Thời gian nhận phòng tại Hoi An Reverie Villas là từ 14:00 – 22:00 và thời gian trả phòng từ 01:00 – 12:00."),
                                faq(
                                                "Hoi An Reverie Villas có phục vụ ăn sáng không?",
                                                "Có, Hoi An Reverie Villas có cung cấp bữa sáng. Tuy nhiên, bạn cần chọn loại phòng có bao gồm bữa sáng. Nếu không, có thể phát sinh phụ phí."),
                                faq(
                                                "Khách sạn Hoi An Reverie Villas đạt tiêu chuẩn mấy sao?",
                                                "Hoi An Reverie Villas là khách sạn đạt tiêu chuẩn 2.5 sao."),
                                faq(
                                                "Hoi An Reverie Villas có bao nhiêu tầng lầu?",
                                                "Hoi An Reverie Villas có tổng cộng 3 tầng."),
                                faq(
                                                "Tổng số phòng hiện có tại Hoi An Reverie Villas là bao nhiêu?",
                                                "Tổng số phòng tại Hoi An Reverie Villas là 23 phòng."),
                                faq(
                                                "Hoi An Reverie Villas có chỗ đậu xe không?",
                                                "Có, Hoi An Reverie Villas có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết."),
                                faq(
                                                "Hoi An Reverie Villas có hồ bơi không?",
                                                "Có, Hoi An Reverie Villas có hồ bơi phục vụ khách lưu trú."),
                                faq(
                                                "Hoi An Reverie Villas có hỗ trợ nhận phòng sớm không?",
                                                "Có, Hoi An Reverie Villas hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp."),
                                faq(
                                                "Hoi An Reverie Villas có dịch vụ đưa đón sân bay không?",
                                                "Có, Hoi An Reverie Villas cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ trực tiếp với khách sạn để biết thêm chi tiết."),
                                faq(
                                                "Đánh giá của khách lưu trú trước đây về Hoi An Reverie Villas như thế nào?",
                                                "Hoi An Reverie Villas được đánh giá cao về Vệ sinh, Tiện nghi phòng, Dịch vụ và Tiện ích, theo nhận xét từ các khách hàng xác thực trên Traveloka."),
                                faq(
                                                "Những địa điểm nổi bật nào nằm gần khách sạn Hoi An Reverie Villas?",
                                                "Một số địa điểm nổi bật gần khách sạn bao gồm Làng gốm Thanh Hà, Công viên Đất nung Thanh Hà, Huỳnh Từ Đường, Quảng trường Sông Hoài và Đình Cẩm Phô."),
                                faq(
                                                "Những loại phòng đang khả dụng tại Hoi An Reverie Villas?",
                                                "Các loại phòng đang khả dụng tại Hoi An Reverie Villas bao gồm Deluxe Double Room Garden View, Superior King Room và Quadruple Family Suite Pool View."),
                                faq(
                                                "Phòng tại Hoi An Reverie Villas được trang bị những tiện nghi gì?",
                                                "Hầu hết các phòng được trang bị Máy lạnh, Bồn tắm hoặc vòi sen, Khu vực chờ, Rèm chắn sáng, Nước đóng chai miễn phí cùng nhiều tiện nghi khác. Tiện nghi có thể khác nhau tùy theo loại phòng."),
                                faq(
                                                "Mỗi phòng tại Hoi An Reverie Villas có thể lưu trú tối đa bao nhiêu khách?",
                                                "Hoi An Reverie Villas có thể chứa tối đa 4 khách trong một phòng, tùy thuộc vào loại phòng bạn đặt. Sức chứa cụ thể sẽ được hiển thị tại thời điểm đặt phòng."));

                // --------- Build hotel doc ----------
                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName("Hội An")
                                .districtName("Thanh Hà")
                                .wardName(null)
                                .addressLine("257 Hai Mươi Tám Tháng Ba, Hội An, Quảng Nam, Việt Nam")
                                // Chưa có: tọa độ (lon, lat) chính xác
                                .location(new double[] { 108.3055, 15.8802 })
                                .name("Hoi An Reverie Villas")
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3) // ảnh hiển thị 3 sao
                                .shortDescription(
                                                "Khách sạn tại Thanh Hà, Hội An – phù hợp lưu trú tiết kiệm, gần các điểm tham quan.")
                                .description(
                                                "Hoi An Reverie Villas là cơ sở lưu trú tại Thanh Hà, Hội An. "
                                                                + "Phù hợp cho du khách muốn ở yên bình nhưng vẫn thuận tiện di chuyển đến các điểm tham quan.")
                                .phone(null) // Chưa có
                                .email(null) // Chưa có
                                .website(null) // Chưa có
                                .defaultCheckInTime(null) // Chưa có
                                .defaultCheckOutTime(null) // Chưa có
                                .hasOnlineCheckin(false) // Chưa có: thông tin check-in online

                                .avgRating(8.2)
                                .reviewsCount(357)
                                .ratingLabel("Rất tốt")

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
                                .label(label)
                                .count(count)
                                .build();
        }
}
