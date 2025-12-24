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
                                                "Mặt tiền Hoa Thu Homestay (ban đêm)", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-74d37d4e17f53feba7551cff6d02bf71.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Sảnh chờ / khu vực tiếp khách", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-065c49820595582c998cdb69c258cc84.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng ngủ (không gian nghỉ ngơi)", false, 2),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-1d6348ddb05dab89abe02caf37805acb.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Phòng tắm (vòi tắm đứng)", false, 3),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-f278967ec074e669396254d9384a4c22.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Không gian chung (khu sinh hoạt)", false, 4),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c086c987dc21336b52a2e34998b30c9c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khung cảnh phố)", false, 5),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-e97ea4bd8a6daadf63052fd961d7c03c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (phố đêm Hội An)", false, 6),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-60e9a96a053d3eb7c3bbd876e47928a8.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (đèn lồng & không khí phố cổ)", false, 7),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-2519182e7fab03b88099ce66340b021d.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khu mua sắm/đi bộ)", false, 8),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-316e6ea68ab6f32d21288f2544ea9a0a.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (góc phố gần homestay)", false, 9),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc6f1979e75ba2139d9423e7c7d538ed.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (khung cảnh về đêm)", false, 10),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c8a3b95dd2fdabc0aed51f7e9a81b7ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-360,pr-true,q-80,w-640",
                                                "Khu vực xung quanh (điểm tham quan lân cận)", false, 11));

                // --------- Content (dựa theo modal “Giới thiệu Hoa Thu Homestay”) ----------
                List<ContentBlock> content = List.of(
                                // Map block: tọa độ xấp xỉ khu Minh An - phố cổ Hội An (bạn gửi toạ độ chuẩn
                                // mình thay)
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.3270, 15.8790 }),

                                // STORY: (từ phần mô tả dài trong trang)
                                heading(ContentSection.STORY, "Mô tả chung về Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "Hoa Thu Homestay là một trong những homestay thú vị nhất tại tỉnh Quảng Nam, Hội An. Nằm ở vị trí 80 Ngô Quyền, Hội An, Tỉnh Quảng Nam, Thành phố Đà Nẵng, Việt Nam, 560000, các du khách đến đây sẽ cảm nhận được sự yên bình và thoải mái từ phong cách thiết kế đơn giản nhưng tinh tế. Được đánh giá với tiêu chuẩn 1.5 sao, Hoa Thu Homestay mang đến cho du khách một trải nghiệm nghỉ dưỡng hòa quyện giữa văn hóa địa phương và mức độ tiện nghi hiện đại."),
                                heading(ContentSection.STORY, "Các loại phòng tại Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "Hoa Thu Homestay cung cấp một loạt các loại phòng phong phú phù hợp với nhiều đối tượng khách hàng khác nhau. Mỗi căn phòng đều được trang bị đầy đủ tiện nghi cần thiết, đảm bảo đem lại sự thoải mái tối đa cho kỳ nghỉ của bạn. Với thiết kế mở và sử dụng nhiều vật liệu tự nhiên, mỗi phòng như một không gian riêng tư để thư giãn sau những ngày dài khám phá."),
                                heading(ContentSection.STORY, "Điểm Tham Quan Gần Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "Khi lưu trú tại Hoa Thu Homestay, bạn dễ dàng tiếp cận các điểm tham quan nổi bật của Quảng Nam và Hội An. Phố cổ Hội An nằm cách đó chỉ khoảng 10 phút lái xe, cho phép bạn khám phá những con phố đầy màu sắc và văn hóa độc đáo. Ngoài ra, chỉ cần một quãng đi bộ ngắn là bạn đã có thể đến bãi biển An Bàng, nơi lý tưởng để tận hưởng cảnh sắc thiên nhiên và không khí biển trong lành."),
                                heading(ContentSection.STORY, "Cơ Sở Vật Chất Nổi Bật Tại Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "Tại Hoa Thu Homestay, du khách sẽ hài lòng với các tiện ích đa dạng. Hồ bơi ngoài trời rộng rãi là điểm nhấn lý tưởng để thư giãn và tận hưởng ánh nắng mặt trời. Không chỉ dừng lại ở đó, khu vực nhà hàng và quầy bar tại chỗ cũng phục vụ nhiều món ăn và đồ uống hấp dẫn, đem lại những trải nghiệm ẩm thực đẳng cấp mà không cần phải đi xa."),
                                heading(ContentSection.STORY, "Phương Tiện Giao Thông Công Cộng Gần Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "Để di chuyển dễ dàng hơn, Hoa Thu Homestay nằm gần ga xe buýt chính của Hội An. Qua đoạn đường bộ khoảng 15 phút hoặc 5 phút đi xe đạp, bạn sẽ đến được khu vực giao thông công cộng trung tâm, tạo điều kiện thuận lợi cho việc khám phá những địa điểm xa hơn trong tỉnh Quảng Nam."),
                                heading(ContentSection.STORY, "Dịch Vụ Tại Hoa Thu Homestay"),
                                paragraph(ContentSection.STORY,
                                                "ệm ẩm thực đẳng cấp mà không cần phải đi xa."),
                                heading(ContentSection.STORY,
                                                "Dịch vụ tại Hoa Thu Homestay luôn được đánh giá cao bởi phong cách phục vụ chuyên nghiệp và tận tình. Từ lễ tân đến nhân viên nhà hàng, tất cả đều sẵn lòng đáp ứng các nhu cầu của khách một cách nhanh chóng và hiệu quả. Các dịch vụ như đặt tour du lịch, cho thuê xe đạp cũng được cung cấp để tạo sự thuận lợi tối đa cho du khách."),
                                paragraph(ContentSection.STORY,
                                                "Theo đánh giá trên Traveloka, , với số điểm người dùng trung bình lên tới . Những nhận xét tích cực xoay quanh không gian thoải mái, vị trí thuận lợi và dịch vụ chăm sóc chu đáo tại Hoa Thu Homestay."),
                                heading(ContentSection.STORY,
                                                "Khu Vực Xung Quanh Hoa Thu Homestay và Quảng Nam, Hội An"),
                                paragraph(ContentSection.STORY,
                                                "Xung quanh Hoa Thu Homestay là một khu vực yên bình với cảnh quan thiên nhiên ấm áp. Ngoài các bảo tàng và di tích lịch sử, du khách còn có thể tham dự các lễ hội và sự kiện truyền thống diễn ra quanh năm. Tỉnh Quảng Nam nổi tiếng không chỉ với bãi biển đẹp mà còn với nền văn hóa phong phú, hấp dẫn qua hàng ngàn năm lịch sử."),
                                heading(ContentSection.STORY,
                                                "Tại Sao Lựa Chọn Hoa Thu Homestay Là Một Quyết Định Tốt"),
                                paragraph(ContentSection.STORY,
                                                "Chọn Hoa Thu Homestay cho kỳ nghỉ ở Hội An bạn không chỉ tận hưởng sự thoải mái mà còn tiết kiệm chi phí với mức giá hợp lý. Vị trí đắc địa cùng các tiện nghi và dịch vụ chất lượng chắc chắn sẽ làm hài lòng ngay cả những du khách khó tính nhất."));

                // --------- Hotel amenities (từ ảnh “Tất cả những tiện ích…”) ----------
                List<AmenityCatalogDoc> hotelAmenities = List.of(
                                // Tiện nghi chung
                                amenity("ac", "Máy lạnh", AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                                amenity("family_room", "Phòng gia đình", AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, true),
                                amenity("non_smoking_room", "Phòng không hút thuốc", AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES,
                                                false),
                                amenity("smoking_area", "Khu vực hút thuốc", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),
                                amenity("terrace", "Sân thượng/sân hiên", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),

                                // Dịch vụ khách sạn
                                amenity("currency_exchange", "Dịch vụ thu đổi ngoại tệ", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("security_24h", "Bảo vệ 24 giờ", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES,
                                                false),
                                amenity("laundry", "Dịch vụ giặt ủi", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER,
                                                false),
                                amenity("tour_desk", "Dịch vụ hỗ trợ đặt Tour", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER,
                                                false),

                                // Đưa đón
                                amenity("airport_transfer", "Đưa đón sân bay (thu phí)", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("train_station_pickup", "Đón khách tại ga tàu", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),

                                // Kết nối mạng
                                amenity("wifi_free", "Wifi (miễn phí)", AmenityGroup.INTERNET, AmenitySection.INTERNET,
                                                true),

                                // Tiện ích lân cận
                                amenity("atm", "Máy ATM/Ngân hàng", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                                amenity("beauty_salon", "Thẩm mỹ viện", AmenityGroup.NEARBY, AmenitySection.OTHER,
                                                false),
                                amenity("hair_salon", "Hiệu làm tóc", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                                amenity("shop", "Cửa hàng", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                                amenity("supermarket", "Siêu thị", AmenityGroup.NEARBY, AmenitySection.OTHER, false),

                                // Vận chuyển
                                amenity("bicycle_rental", "Dịch vụ cho thuê xe đạp", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("bicycle_storage", "Giữ xe đạp", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT, false),

                                // Trẻ em
                                amenity("babysitting", "Dịch vụ giữ trẻ (thu phí)", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER,
                                                false),

                                // Ẩm thực
                                amenity("breakfast_paid", "Bữa sáng (thu phí)", AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, false),

                                // Tiện nghi phòng (tổng quan)
                                amenity("cable_tv", "Truyền hình cáp", AmenityGroup.ROOM,
                                                AmenitySection.ROOM_FACILITIES, false),
                                amenity("minibar", "Minibar", AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
                                amenity("shower", "Phòng tắm vòi sen", AmenityGroup.ROOM, AmenitySection.BATHROOM,
                                                false),
                                amenity("tv", "TV", AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),

                                // Tiện nghi công cộng
                                amenity("lobby_coffee_tea", "Cà phê/trà tại sảnh", AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.FOOD_AND_DRINK, false),
                                amenity("public_wifi", "Wifi tại khu vực chung", AmenityGroup.INTERNET,
                                                AmenitySection.INTERNET,
                                                false));

                // --------- Room types ----------
                // 1) Family (30.0 m² - 4 khách - 2 giường đôi)
                RoomType family = RoomType.builder()
                                .id("rt-hoa-thu-family")
                                .name("Family")
                                .shortDescription("Phòng Family 30.0 m², 2 giường đôi, tối đa 4 khách.")
                                .description(
                                                """
                                                                    Phòng Family phù hợp cho gia đình/nhóm bạn, diện tích 30.0 m² và sức chứa 4 khách.
                                                                    Tiện nghi nổi bật: vòi tắm đứng, nước nóng, máy lạnh; có minibar, TV, quạt, rèm cản sáng và bàn làm việc.
                                                                """)
                                .areaSqm(30.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription("2 giường đôi")
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(0)
                                .maxGuests(4)
                                .totalRooms(10)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-e97ea4bd8a6daadf63052fd961d7c03c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc6f1979e75ba2139d9423e7c7d538ed.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-cc2b932dfbf8e209f49f4cce6a2b2d76.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-6133d325ae6953153aab983ca7dbc7fc.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-9e65785426329bcd2150c470c986898c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67742180-2399x1557-FIT_AND_TRIM-93ec84bfcc51d9617f67a8bb6d2a19b0.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family - Phòng ngủ", false, 5)))
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
                                                                .name("Family - Không gồm bữa sáng")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy("Không được hoàn tiền.")
                                                                .pricePerNight(new BigDecimal("469929"))
                                                                .referencePricePerNight(new BigDecimal("514286"))
                                                                .discountPercent(9)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel("Giá ưu tiên")
                                                                .showLowAvailability(true)
                                                                .build(),

                                                // Plan 2: 514,286 (ref 685,715) - thanh toán tại KS, có chính sách hủy
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-payathotel-flex")
                                                                .name("Family - Không gồm bữa sáng")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy("Áp dụng chính sách hủy phòng.")
                                                                .pricePerNight(new BigDecimal("514286"))
                                                                .referencePricePerNight(new BigDecimal("685715"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel("Ưu đãi phút chót")
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // 2) Family With Balcony (32.0 m² - 4 khách)
                RoomType familyBalcony = RoomType.builder()
                                .id("rt-hoa-thu-family-with-balcony")
                                .name("Family With Balcony")
                                .shortDescription(
                                                "Phòng Family With Balcony 32.0 m², tối đa 4 khách, có ban công/sân hiên.")
                                .description("""
                                                    Phòng Family With Balcony rộng 32.0 m², phù hợp 4 khách.
                                                    Tính năng nổi bật: vòi tắm đứng, ban công/sân hiên, lò vi sóng, nước nóng, máy lạnh.
                                                """)
                                .areaSqm(32.0)
                                .bedType(BedType.DOUBLE)
                                .bedsCount(2)
                                .bedLayoutDescription("2 giường đôi")
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.DOUBLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(0)
                                .maxGuests(4)
                                .totalRooms(10)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-74d37d4e17f53feba7551cff6d02bf71.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c8a3b95dd2fdabc0aed51f7e9a81b7ea.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-c086c987dc21336b52a2e34998b30c9c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-0d53038368afecd29b15da1528b62d3f.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", false, 3),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67742180-2337x1386-FIT_AND_TRIM-d95f2714157142613e1d82f149b18171.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", false, 4),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20015960-eb30b251df537c9809d8549d0b251f06.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Family With Balcony- Phòng ngủ", false, 5)))
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
                                                                .name("Family With Balcony - Không gồm bữa sáng")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy("Không được hoàn tiền.")
                                                                .pricePerNight(new BigDecimal("489508"))
                                                                .referencePricePerNight(new BigDecimal("489508"))
                                                                .discountPercent(0)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel("Giá tốt")
                                                                .showLowAvailability(true)
                                                                .build(),
                                                RatePlan.builder()
                                                                .id("rp-hoa-thu-family-balcony-payathotel-flex")
                                                                .name("Family With Balcony - Không gồm bữa sáng")
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy("Áp dụng chính sách hủy phòng.")
                                                                .pricePerNight(new BigDecimal("535714"))
                                                                .referencePricePerNight(new BigDecimal("714205"))
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(false)
                                                                .priceIncludesServiceFee(false)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel("Ưu đãi phút chót")
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // --------- Nearby places (từ ảnh “Xung quanh…”) ----------
                List<NearbyPlace> nearby = List.of(
                                // Địa điểm lân cận
                                nearby("my-grandmas-home-cooking", "My Grandma's Home Cooking", "Địa điểm lân cận",
                                                36.0),
                                nearby("cho-dem-hoi-an", "Chợ đêm Hội An", "Địa điểm lân cận", 41.0),
                                nearby("old-town-lantern-boat", "Old Town Lantern Boat", "Địa điểm lân cận", 231.0),
                                nearby("nha-tan-ky", "Nhà Tân Kỳ", "Địa điểm lân cận", 243.0),
                                nearby("bao-tang-van-hoa-sa-huynh", "Bảo tàng Văn hóa Sa Huỳnh", "Địa điểm lân cận",
                                                284.0),
                                nearby("chua-cau-hoi-an", "Chùa Cầu Hội An", "Địa điểm lân cận", 295.0),
                                nearby("nha-co-phung-hung", "Nhà cổ Phùng Hưng", "Địa điểm lân cận", 300.0),
                                nearby("duc-an-house", "Duc An House", "Địa điểm lân cận", 306.0),
                                nearby("pho-co-hoi-an", "Phố cổ Hội An", "Địa điểm lân cận", 320.0),
                                nearby("diep-dong-nguyen-house", "Diep Dong Nguyen House", "Địa điểm lân cận", 340.0),
                                nearby("hoi-quan-quang-dong", "Hội quán Quảng Đông - Chùa Quảng Triệu",
                                                "Địa điểm lân cận", 340.0),
                                nearby("bao-tang-van-hoa-dan-gian", "Bảo tàng Văn hóa dân gian", "Địa điểm lân cận",
                                                403.0),
                                nearby("nha-co-quan-thang", "Nhà cổ Quân Thắng", "Địa điểm lân cận", 422.0),
                                nearby("quang-truong-song-hoai", "Quảng trường Sông Hoài", "Địa điểm lân cận", 446.0),
                                nearby("bao-tang-gom-su", "Bảo tàng gốm sứ mậu dịch Hội An", "Địa điểm lân cận", 456.0),

                                // Trung tâm giao thông
                                nearby("hoi-an-boat-harbor", "Hoi An Boat Harbor", "Trung tâm giao thông", 529.0),
                                nearby("old-town-latern-boat", "Old Town Lantern Boat", "Trung tâm giao thông", 231.0),
                                // Trung tâm giải trí
                                nearby("ky-uc-hoi-an", "Ký ức Hội An", "Trung tâm giải trí", 1500.0),
                                nearby("dao-ky-uc-hoi-an", "Đảo ký ức Hội An", "Trung tâm giải trí", 1490.0),
                                nearby("quang-truong-song-hoai", "Quảng trường Sông Hoài", "Trung tâm giải trí", 446.0),
                                nearby("chua-cau-hoi-an", "Chùa Cầu Hội An", "Trung tâm giải trí", 295.0),
                                nearby("cho-dem-hoi-an", "Chợ đêm Hội An", "Trung tâm giải trí", 41.0),

                                // Khác
                                nearby("quan-cong-mieu", "Quan Công Miếu", "Khác", 629.0),
                                nearby("chua-chuc-thanh", "Chùa Chúc Thánh", "Khác", 1600.0),
                                nearby("chua-phuoc-lam", "Chùa Phước Lâm", "Khác", 2020.0),
                                nearby("om-factory-yoga-hoian", "Om Factory Yoga Hoi An", "Khác", 2830.0),
                                nearby("dinh-cam-pho", "Đình Cẩm Phô", "Khác", 487.0));

                // --------- General info ----------
                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary("Máy lạnh, WiFi")
                                .distanceToCityCenterKm(0.30)
                                .popularAreaSummary("Chợ đêm Hội An, Old Town Lantern Boat, Chùa Cầu Hội An")
                                .totalRooms(20)
                                .totalFloors(3)
                                .otherHighlightFacilities(
                                                "Đưa đón sân bay (thu phí), hỗ trợ đặt tour, cho thuê xe đạp.")
                                .interestingPlacesSummary("Chợ đêm Hội An, Chùa Cầu Hội An, Phố cổ Hội An")
                                .build();

                // --------- Policy (chưa có ảnh chính sách, set mặc định giống seed khác)
                // ----------
                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder()
                                                                .section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title("Thời gian nhận phòng/trả phòng")
                                                                .content("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.")
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                                                                .title("Hướng dẫn nhận phòng chung")
                                                                .content(
                                                                                "Vui lòng liên hệ trước nếu bạn cần hỗ trợ nhận phòng muộn hoặc yêu cầu đặc biệt.")
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
                                                keyword("friendly_staff", "Nhân viên thân thiện", 7),
                                                keyword("distance_to_center", "Khoảng cách đến trung tâm", 6),
                                                keyword("surrounding_area", "Khu vực xung quanh", 5),
                                                keyword("family_friendly", "Hợp cho gia đình", 4)))
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
                                .partnerId("18")
                                .partnerName("Nguyễn Thư Anh")
                                .partnerEmail("anhthucute19081998@gmail.com")
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
                                                "Những tiện ích tại Hoa Thu Homestay?",
                                                "Những tiện ích tại Hoa Thu Homestay bao gồm máy lạnh, WiFi. Một số dịch vụ có thể yêu cầu trả thêm phí."),
                                faq(
                                                "Hoa Thu Homestay có mức giá là bao nhiêu?",
                                                "Phòng tại Hoa Thu Homestay có giá từ khoảng 542.768 VND. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi."),
                                faq(
                                                "Thời gian nhận phòng và trả phòng của Hoa Thu Homestay?",
                                                "Thời gian nhận phòng tại Hoa Thu Homestay từ 14:00 – 22:00 và trả phòng trước 07:00 – 12:00."),
                                faq(
                                                "Hoa Thu Homestay có phục vụ ăn sáng không?",
                                                "Có, Hoa Thu Homestay có dịch vụ cung cấp bữa sáng. Tuy nhiên, bạn cần chọn loại phòng có bao gồm bữa sáng. Nếu không, bạn có thể phải trả thêm phí."),
                                faq(
                                                "Khách sạn Hoa Thu Homestay đạt tiêu chuẩn mấy sao?",
                                                "Hoa Thu Homestay là khách sạn đạt tiêu chuẩn 1.5 sao."),
                                faq(
                                                "Hoa Thu Homestay có bao nhiêu tầng lầu?",
                                                "Hoa Thu Homestay có tổng cộng 2 tầng."),
                                faq(
                                                "Tổng số phòng hiện có tại Hoa Thu Homestay là bao nhiêu?",
                                                "Tổng số phòng tại Hoa Thu Homestay là 5 phòng."),
                                faq(
                                                "Đánh giá của khách lưu trú trước đây về Hoa Thu Homestay như thế nào?",
                                                "Hoa Thu Homestay được đánh giá cao về vị trí, vệ sinh và tiện nghi phòng theo nhận xét từ khách hàng đã lưu trú trên Traveloka."),
                                faq(
                                                "Những địa điểm nổi bật nào nằm gần Hoa Thu Homestay?",
                                                "Một số địa điểm nổi bật gần Hoa Thu Homestay bao gồm My Grandma's Home Cooking, Chợ đêm Hội An, Old Town Lantern Boat, Nhà Tấn Ký và Bảo tàng Văn hóa Sa Huỳnh."),
                                faq(
                                                "Những loại phòng đang khả dụng tại Hoa Thu Homestay?",
                                                "Những loại phòng đang khả dụng tại Hoa Thu Homestay bao gồm Family và Family With Balcony."),
                                faq(
                                                "Phòng tại Hoa Thu Homestay được trang bị những tiện nghi gì?",
                                                "Hầu hết các phòng tại Hoa Thu Homestay đều được trang bị vòi tắm đứng, nước nóng, máy lạnh, ban công hoặc sân hiên, lò vi sóng và các tiện nghi cần thiết khác. Tiện nghi có thể khác nhau tùy loại phòng."),
                                faq(
                                                "Mỗi phòng tại Hoa Thu Homestay có thể lưu trú tối đa bao nhiêu khách?",
                                                "Hoa Thu Homestay có thể chứa tối đa 4 người trong một phòng, tùy thuộc vào loại phòng bạn đặt. Sức chứa cụ thể sẽ hiển thị tại thời điểm đặt phòng."));

                // --------- Build ----------
                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName("Hội An")
                                .districtName("Minh An")
                                .wardName(null)
                                .addressLine("80 Ngô Quyền, Minh An, Hội An, Quảng Nam, Việt Nam")
                                .location(new double[] { 108.3270, 15.8790 })
                                .name("Hoa Thu Homestay")
                                .slug(slug)
                                .hotelType(HotelType.HOMESTAY)
                                .starRating(2)
                                .shortDescription("Homestay giá tốt tại Minh An, gần Chợ đêm Hội An và khu phố cổ.")
                                .description(
                                                "Hoa Thu Homestay phù hợp cho du khách muốn lưu trú thoải mái, tiết kiệm và thuận tiện di chuyển trong khu vực phố cổ Hội An. Có WiFi, máy lạnh và các tiện ích cơ bản cho kỳ nghỉ ngắn hoặc dài ngày.")
                                .phone(null)
                                .email(null)
                                .website(null)
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .avgRating(9.1)
                                .reviewsCount(167)
                                .ratingLabel("Xuất sắc")
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
