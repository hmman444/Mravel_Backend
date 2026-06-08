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
public class SeedDnMuongThanh {

        @Bean
        CommandLineRunner seedDnMuongThanhRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedDnMuongThanh] start");
                        HotelDoc doc = hotelMuongThanh();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedDnMuongThanh] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedDnMuongThanh] done");
                };
        }

        // =====================================================================
        // MUONG THANH GRAND DA NANG HOTEL
        // (source: Traveloka hotelId 1000000441652; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelMuongThanh() {
                String slug = "muong-thanh-grand-da-nang-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-1798x1419-FIT_AND_TRIM-8b863abd52df5a14e92409e000613d7d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-800x518-FIT_AND_TRIM-519e495dd0a4b9e1c786d6cbf2bdb205.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-2576x1720-FIT_AND_TRIM-a72e082023dc595c59f17189543b0ec6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-1600x1200-FIT_AND_TRIM-182c852590a7f311db4f32141ba50302.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-1600x1200-FIT_AND_TRIM-1f16fe78f46034e17ec99d741e337596.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-1600x1200-FIT_AND_TRIM-e1e5a89d7f444b136abd5e75f77108e5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-b6a3bbcd510a5a34cc79812067c1fce4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-48299eb34034ca4e8d7c776b2d400af7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-2fccffcc011e6c84b807eb20f9307526.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-909c265930ecddad747864c4093b1d6d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-82876f2cbed6b6ddf8ec11eede201401.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-9914b50859feb9e3163cf32c9ebce851.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-b2af97c086198df771266fda017375b8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-8bf26310cda7c5ab3e39ef1e42ce29f4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-aa06cc299c8ee03a76384cdce82432b3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-101851defb22c1bc36b837a308d9366c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-05e955c2fc5571b4ed5ecef0351e1762.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-ed4d99aaf03280c540f2a17db0e876fd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-0ee4981bb2b452dc0429594c1dc0eefc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-498bae9bb952631a27fc09a2b5b005d6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-ba4bc88761ee7614fdc415f8108b7845.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-e37460ba8d5d61e781376d61b7f1e3d6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-872979fd09ca8435056b75657f164e45.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-ed64f2cd990e367f349f76bab3ce2aed.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-8d8a9cd4626fee7f764173722511d6f1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-c8b6a470a74f7a23eb7579bc344052c4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-0c235a67874dd2c0bd0a713fefadc46c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-05ba0bb471358596f87e2291dc70c2b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-ba984bb9eacca045eb8d37a90f8e0718.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Nhà hàng", "Restaurant", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-9aa0a6fee7c12f03fd914c16e08729a4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 29));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 108.23319, 16.064815 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Muong Thanh Grand Da Nang Hotel", "About Muong Thanh Grand Da Nang Hotel")),

                paragraph(ContentSection.STORY,
                                of("Muong Thanh Grand Da Nang Hotel là khách sạn 4 sao tọa lạc tại 962 Ngô Quyền, quận Sơn Trà, Đà Nẵng – chỉ vài phút tới bãi biển Mỹ Khê và trung tâm thành phố. Với 380 phòng trải trên 26 tầng, khách sạn mang đến không gian nghỉ dưỡng hiện đại, tiện nghi đầy đủ, phù hợp cho cả khách du lịch lẫn khách công tác.", "Muong Thanh Grand Da Nang Hotel is a 4-star hotel located at 962 Ngo Quyen, Son Tra District, Da Nang – just minutes from My Khe Beach and the city center. With 380 rooms across 26 floors, the hotel offers modern, fully equipped accommodation suitable for both leisure and business travelers.")),

                heading(ContentSection.STORY, of("Các hạng phòng & tiện nghi", "Room categories & amenities")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình và phòng hướng biển/hướng thành phố. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, phòng tắm riêng với nước nóng, máy sấy tóc và Wi-Fi miễn phí, đảm bảo sự thoải mái cho mọi du khách.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to Family rooms and sea-view/city-view rooms. Each room is equipped with air conditioning, a minibar, a work desk, a private bathroom with hot water, a hair dryer and free Wi-Fi, ensuring comfort for every guest.")),

                heading(ContentSection.STORY, of("Ẩm thực đa dạng", "Diverse dining")),

                paragraph(ContentSection.STORY,
                                of("Hệ thống nhà hàng phục vụ ẩm thực Á – Âu phong phú, từ bữa sáng buffet thịnh soạn đến thực đơn gọi món cho bữa trưa và bữa tối. Du khách có thể thưởng thức cà phê tại sảnh, nhâm nhi đồ uống tại quầy bar hoặc khám phá đặc sản hải sản tươi ngon của Đà Nẵng ngay gần khách sạn.", "The hotel's restaurants serve a rich variety of Asian and European cuisine, from a hearty breakfast buffet to à la carte menus for lunch and dinner. Guests can enjoy lobby coffee, sip drinks at the bar, or explore Da Nang's fresh seafood specialties right near the hotel.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Muong Thanh Grand Da Nang Hotel sở hữu hồ bơi, trung tâm thể dục, spa và dịch vụ massage giúp du khách thư giãn sau ngày dài. Lễ tân 24 giờ, dịch vụ phòng, giặt ủi, giữ hành lý, đưa đón sân bay và cho thuê xe đáp ứng mọi nhu cầu trong suốt kỳ lưu trú.", "Muong Thanh Grand Da Nang Hotel features a swimming pool, a fitness center, a spa and massage services to help guests relax after a long day. A 24-hour front desk, room service, laundry, luggage storage, airport transfer and car rental meet every need throughout the stay.")),

                heading(ContentSection.STORY, of("Vị trí & bãi biển", "Location & beach")),

                paragraph(ContentSection.STORY,
                                of("Nằm ngay trục đường Ngô Quyền sầm uất bên bờ Đông sông Hàn, khách sạn chỉ cách bãi biển Mỹ Khê vài phút đi bộ và rất gần Cầu Rồng, Cầu Sông Hàn. Đây là vị trí lý tưởng để vừa tận hưởng biển xanh cát trắng vừa dễ dàng khám phá nhịp sống về đêm của Đà Nẵng.", "Set on the bustling Ngo Quyen road on the east bank of the Han River, the hotel is just a few minutes' walk from My Khe Beach and very close to the Dragon Bridge and Han River Bridge. It is an ideal base to enjoy the blue sea and white sand while easily exploring Da Nang's nightlife.")),

                heading(ContentSection.STORY, of("Di chuyển thuận tiện", "Convenient transportation")),

                paragraph(ContentSection.STORY,
                                of("Sân bay quốc tế Đà Nẵng chỉ cách khách sạn khoảng 6 km, tương đương 15 phút lái xe. Khách sạn hỗ trợ dịch vụ đưa đón sân bay theo yêu cầu và cho thuê xe, giúp du khách dễ dàng tới các điểm tham quan như Ngũ Hành Sơn, Bán đảo Sơn Trà hay Bà Nà Hills.", "Da Nang International Airport is only about 6 km away, roughly a 15-minute drive. The hotel offers airport transfer on request and car rental, making it easy for guests to reach attractions such as the Marble Mountains, Son Tra Peninsula or Ba Na Hills.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Xung quanh khách sạn là nhiều nhà hàng hải sản, quán cà phê ven biển, chợ đêm Sơn Trà cùng các trung tâm mua sắm như Vincom Plaza. Chợ Hàn, Chợ Cồn và Bảo tàng Điêu khắc Chăm cũng nằm trong bán kính di chuyển ngắn, mang đến trải nghiệm ẩm thực và văn hóa phong phú.", "The hotel is surrounded by numerous seafood restaurants, seaside cafes, the Son Tra night market and shopping centers such as Vincom Plaza. Han Market, Con Market and the Cham Sculpture Museum are also within a short distance, offering rich culinary and cultural experiences.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Muong Thanh Grand Da Nang Hotel", "Why choose Muong Thanh Grand Da Nang Hotel")),

                paragraph(ContentSection.STORY,
                                of("Chọn Muong Thanh Grand Da Nang Hotel, du khách có được vị trí trung tâm sát biển, hệ thống tiện ích đầy đủ gồm hồ bơi, spa, nhà hàng và dịch vụ 4 sao chu đáo với mức giá hợp lý. Đây là lựa chọn lý tưởng cho kỳ nghỉ biển, chuyến công tác hay hành trình khám phá Đà Nẵng cùng gia đình.", "Choosing Muong Thanh Grand Da Nang Hotel gives travelers a central beachfront location, a full set of facilities including a pool, spa and restaurant, plus attentive 4-star service at a reasonable price. It is an ideal choice for a beach holiday, a business trip or a family adventure in Da Nang.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("outdoor_pool", of("Hồ bơi", "Swimming pool"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("spa", of("Dịch vụ spa", "Spa"), AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, false),
                amenity("massage", of("Dịch vụ massage", "Massage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("terrace", of("Sân thượng/sân hiên", "Terrace/patio"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-mt-dn-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 24 m² với 1 giường đôi, tiện nghi cơ bản đầy đủ.", "Standard room 24 sqm with 1 double bed and full basic amenities."))
                        .description(of("Phòng Standard 24 m² với 1 giường đôi, tiện nghi cơ bản đầy đủ. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Standard room 24 sqm with 1 double bed and full basic amenities. Fully equipped for a comfortable stay."))
                        .areaSqm(24.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(90)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-9ab66e959d14da30ad48126fd8243694.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-c7e41320ae72940291dc4b8dabd5f08c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-mt-dn-standard-double-prepaid-nonref")
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
                                        .id("rt-mt-dn-standard-double-breakfast-flex")
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
                        .id("rt-mt-dn-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 28 m² với 2 giường đơn, rộng rãi và thoáng sáng.", "Superior room 28 sqm with 2 single beds, spacious and bright."))
                        .description(of("Phòng Superior 28 m² với 2 giường đơn, rộng rãi và thoáng sáng. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Superior room 28 sqm with 2 single beds, spacious and bright. Fully equipped for a comfortable stay."))
                        .areaSqm(28.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(80)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-dd40d8baa4f6d107bc4b7a879744eb69.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-f920cde027864e06f7886e2be349df9f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-mt-dn-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
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
                                        .id("rt-mt-dn-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
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
                        .build(),
                RoomType.builder()
                        .id("rt-mt-dn-deluxe-city-view")
                        .name(of("Deluxe City View", "Deluxe City View"))
                        .shortDescription(of("Phòng Deluxe 32 m² hướng thành phố, 1 giường Queen, có ban công.", "Deluxe room 32 sqm with city view, 1 queen bed and a balcony."))
                        .description(of("Phòng Deluxe 32 m² hướng thành phố, 1 giường Queen, có ban công. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 32 sqm with city view, 1 queen bed and a balcony. Fully equipped for a comfortable stay."))
                        .areaSqm(32.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(70)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-4242274933dbda29fb81fb8b89617f6b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-822aea64c629faf339be9fd17e3c7f93.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-mt-dn-deluxe-city-view-prepaid-nonref")
                                        .name(of("Deluxe City View - Không gồm bữa sáng", "Deluxe City View - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1700000"))
                                        .referencePricePerNight(new BigDecimal("2428571"))
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
                                        .id("rt-mt-dn-deluxe-city-view-breakfast-flex")
                                        .name(of("Deluxe City View - Bao gồm bữa sáng", "Deluxe City View - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1955000"))
                                        .referencePricePerNight(new BigDecimal("2792857"))
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
                        .id("rt-mt-dn-deluxe-sea-view")
                        .name(of("Deluxe Sea View", "Deluxe Sea View"))
                        .shortDescription(of("Phòng Deluxe 34 m² hướng biển, 1 giường King, ban công ngắm biển.", "Deluxe room 34 sqm with sea view, 1 king bed and a sea-view balcony."))
                        .description(of("Phòng Deluxe 34 m² hướng biển, 1 giường King, ban công ngắm biển. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Deluxe room 34 sqm with sea view, 1 king bed and a sea-view balcony. Fully equipped for a comfortable stay."))
                        .areaSqm(34.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(60)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-9d22d6acc9c2c97e2349d46c100462fb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-6f2b620109de7e424db1da50e46938c3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-mt-dn-deluxe-sea-view-prepaid-nonref")
                                        .name(of("Deluxe Sea View - Không gồm bữa sáng", "Deluxe Sea View - Room only"))
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
                                        .id("rt-mt-dn-deluxe-sea-view-breakfast-flex")
                                        .name(of("Deluxe Sea View - Bao gồm bữa sáng", "Deluxe Sea View - Breakfast included"))
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
                        .build(),
                RoomType.builder()
                        .id("rt-mt-dn-family-suite")
                        .name(of("Family Suite", "Family Suite"))
                        .shortDescription(of("Phòng Gia đình 45 m², 1 giường King và 1 giường đơn, cho tối đa 4 khách.", "Family suite 45 sqm with 1 king and 1 single bed, for up to 4 guests."))
                        .description(of("Phòng Gia đình 45 m², 1 giường King và 1 giường đơn, cho tối đa 4 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "Family suite 45 sqm with 1 king and 1 single bed, for up to 4 guests. Fully equipped for a comfortable stay."))
                        .areaSqm(45.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường King và 1 giường đơn", "1 king bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(40)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-f3db8395172f4be9173edcffea9f5e18.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10019241-9ab66e959d14da30ad48126fd8243694.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-mt-dn-family-suite-prepaid-nonref")
                                        .name(of("Family Suite - Không gồm bữa sáng", "Family Suite - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("2800000"))
                                        .referencePricePerNight(new BigDecimal("4000000"))
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
                                        .id("rt-mt-dn-family-suite-breakfast-flex")
                                        .name(of("Family Suite - Bao gồm bữa sáng", "Family Suite - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("3220000"))
                                        .referencePricePerNight(new BigDecimal("4600000"))
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
                nearby("bai-bien-my-an", of("Bãi biển Mỹ An", "My An Beach"), of("Bãi biển", "Beach"), 2500.0),
                nearby("cau-rong-da-nang", of("Cầu Rồng", "Dragon Bridge"), of("Địa điểm lân cận", "Nearby places"), 2000.0),
                nearby("cau-song-han", of("Cầu sông Hàn", "Han River Bridge"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("cau-tinh-yeu-da-nang", of("Cầu Tình Yêu", "Love Bridge"), of("Địa điểm lân cận", "Nearby places"), 2300.0),
                nearby("cho-han", of("Chợ Hàn", "Han Market"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
                nearby("cho-con", of("Chợ Cồn", "Con Market"), of("Địa điểm lân cận", "Nearby places"), 4000.0),
                nearby("bao-tang-dieu-khac-cham", of("Bảo tàng Điêu khắc Chăm", "Cham Sculpture Museum"), of("Khác", "Other"), 3200.0),
                nearby("ban-dao-son-tra", of("Bán đảo Sơn Trà", "Son Tra Peninsula"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("chua-linh-ung", of("Chùa Linh Ứng", "Linh Ung Pagoda"), of("Khác", "Other"), 10000.0),
                nearby("dinh-ban-co", of("Đỉnh Bàn Cờ", "Ban Co Peak"), of("Khác", "Other"), 12000.0),
                nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Khác", "Other"), 7000.0),
                nearby("cong-vien-chau-a", of("Công viên Châu Á (Asia Park)", "Sun World Asia Park"), of("Trung tâm giải trí", "Entertainment hub"), 6000.0),
                nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 6000.0),
                nearby("ba-na-hills", of("Khu du lịch Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 30000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Hồ bơi, spa, nhà hàng, máy lạnh, lễ tân 24h, WiFi miễn phí", "Pool, spa, restaurant, air conditioning, 24-hour front desk, free WiFi"))
                                .distanceToCityCenterKm(2.5)
                                .popularAreaSummary(of("Bãi biển Mỹ Khê, Cầu Rồng, Sông Hàn", "My Khe Beach, Dragon Bridge, Han River"))
                                .totalRooms(380)
                                .totalFloors(26)
                                .otherHighlightFacilities(of("Hồ bơi, spa, massage, trung tâm thể dục, đưa đón sân bay, cho thuê xe.", "Pool, spa, massage, fitness center, airport transfer, car rental."))
                                .interestingPlacesSummary(of("Bãi biển Mỹ Khê, Bán đảo Sơn Trà, Ngũ Hành Sơn", "My Khe Beach, Son Tra Peninsula, Marble Mountains"))
                                .build();

                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(15, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 15:00. Giờ trả phòng: trước 12:00.", "Check-in: from 15:00. Check-out: before 12:00."))
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
                                                PolicyItem.builder().section(PolicySection.MIN_AGE)
                                                                .title(of("Trẻ em & gia đình", "Children & family"))
                                                                .content(of("Khách sạn thân thiện với gia đình: có phòng Gia đình, hồ bơi trẻ em và dịch vụ phù hợp cho trẻ nhỏ.", "The hotel is family-friendly with Family rooms, a children's pool and child-friendly services."))
                                                                .build(),
                                                PolicyItem.builder().section(PolicySection.OTHER)
                                                                .title(of("Bãi đậu xe", "Parking"))
                                                                .content(of("Khách sạn có bãi đậu xe cho khách lưu trú; vui lòng liên hệ để biết chi tiết về phí (nếu có).", "The hotel has parking for guests; please contact for details on fees (if any)."))
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
                faq(of("Những tiện ích tại Muong Thanh Grand Da Nang Hotel?", "What amenities does Muong Thanh Grand Da Nang Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, hồ bơi, lễ tân 24h, chỗ đậu xe, thang máy, spa và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, swimming pool, 24-hour front desk, parking, elevator, spa and WiFi (some services may require an additional fee).")),
                faq(of("Muong Thanh Grand Da Nang Hotel có mức giá là bao nhiêu?", "How much does Muong Thanh Grand Da Nang Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 1.000.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 1,000,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Thời gian nhận phòng và trả phòng?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 15:00 và trả phòng trước 12:00.", "Check-in is from 15:00 and check-out is before 12:00.")),
                faq(of("Muong Thanh Grand Da Nang Hotel có phục vụ ăn sáng không?", "Does the hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn đạt tiêu chuẩn mấy sao?", "What star rating does the hotel have?"),
                        of("Muong Thanh Grand Da Nang Hotel là khách sạn đạt tiêu chuẩn 4 sao.", "Muong Thanh Grand Da Nang Hotel is a 4-star hotel.")),
                faq(of("Khách sạn có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does the hotel have?"),
                        of("Khách sạn có tổng cộng 26 tầng với 380 phòng.", "The hotel has a total of 26 floors with 380 rooms.")),
                faq(of("Muong Thanh Grand Da Nang Hotel có bãi đậu xe không?", "Does the hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe cho khách lưu trú. Vui lòng liên hệ trực tiếp để biết chi tiết về phí (nếu có).", "Yes, the hotel provides parking for guests. Please contact the hotel directly for details on fees (if any).")),
                faq(of("Khách sạn có hồ bơi không?", "Does the hotel have a pool?"),
                        of("Có, khách sạn có hồ bơi phục vụ khách lưu trú, kèm hồ bơi trẻ em.", "Yes, the hotel has a swimming pool for guests, along with a children's pool.")),
                faq(of("Muong Thanh Grand Da Nang Hotel có dịch vụ đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí); sân bay Đà Nẵng cách khoảng 15 phút lái xe.", "Yes, the hotel offers airport transfer on request (surcharge); Da Nang Airport is about a 15-minute drive away.")),
                faq(of("Gần Muong Thanh Grand Da Nang Hotel có nhà hàng, khu vực ăn uống nào không?", "Are there restaurants or dining areas near the hotel?"),
                        of("Gần khách sạn có nhiều nhà hàng hải sản, quán ăn Việt Nam, nhà hàng quốc tế, quán cà phê ven biển và chợ đêm Sơn Trà với đa dạng món ăn đường phố.", "Near the hotel there are many seafood restaurants, Vietnamese eateries, international restaurants, seaside cafes and the Son Tra night market with a variety of street food.")),
                faq(of("Xung quanh Muong Thanh Grand Da Nang Hotel có trung tâm mua sắm nào không?", "Are there shopping centers near the hotel?"),
                        of("Có, gần khách sạn có Vincom Plaza Đà Nẵng, Chợ Hàn, Chợ Cồn và Helio Center, thuận tiện cho việc mua sắm và trải nghiệm văn hóa địa phương.", "Yes, near the hotel are Vincom Plaza Da Nang, Han Market, Con Market and Helio Center, convenient for shopping and experiencing local culture.")),
                faq(of("Những địa điểm du lịch gần Muong Thanh Grand Da Nang Hotel là những địa điểm nào?", "What tourist attractions are near the hotel?"),
                        of("Khách sạn gần Bãi biển Mỹ Khê, Cầu Rồng, Cầu Sông Hàn, Bán đảo Sơn Trà, Chùa Linh Ứng, Ngũ Hành Sơn và Bảo tàng Điêu khắc Chăm.", "The hotel is near My Khe Beach, the Dragon Bridge, the Han River Bridge, Son Tra Peninsula, Linh Ung Pagoda, the Marble Mountains and the Cham Sculpture Museum.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("da-nang")
                                .cityName(of("Đà Nẵng", "Da Nang"))
                                .districtName(of("Sơn Trà", "Son Tra"))
                                .addressLine(of("962 Ngô Quyền, Sơn Trà, Đà Nẵng, Thành phố Đà Nẵng, Việt Nam", "962 Ngo Quyen, Son Tra District, Da Nang, Da Nang City, Vietnam"))
                                .location(new double[] { 108.23319, 16.064815 })
                                .name(of("Muong Thanh Grand Da Nang Hotel", "Muong Thanh Grand Da Nang Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(4)
                                .shortDescription(of("Khách sạn 4 sao tại Sơn Trà, Đà Nẵng, gần bãi biển Mỹ Khê và Cầu Rồng.", "4-star hotel in Son Tra, Da Nang, near My Khe Beach and the Dragon Bridge."))
                                .description(of("Muong Thanh Grand Da Nang Hotel là khách sạn 4 sao với 380 phòng trên 26 tầng, sở hữu hồ bơi, spa, nhà hàng và vị trí trung tâm sát biển tại quận Sơn Trà, Đà Nẵng.", "Muong Thanh Grand Da Nang Hotel is a 4-star hotel with 380 rooms across 26 floors, featuring a pool, spa, restaurant and a central beachfront location in Son Tra District, Da Nang."))
                                .defaultCheckInTime(LocalTime.of(15, 0))
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
