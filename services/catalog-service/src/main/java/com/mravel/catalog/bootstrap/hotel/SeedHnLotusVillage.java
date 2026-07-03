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
public class SeedHnLotusVillage {

        @Bean
        CommandLineRunner seedHnLotusVillageRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHnLotusVillage] start");
                        HotelDoc doc = hotelLotusVillage();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHnLotusVillage] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHnLotusVillage] done");
                };
        }

        // =====================================================================
        // LOTUS VILLAGE HOTEL HANOI
        // (source: Traveloka hotelId 1000000709562; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelLotusVillage() {
                String slug = "lotus-village-hotel-hanoi";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-3f45488c62201fbbbd6f7348b04cc60e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-c6e7649afd7056188fd37ab7163b962f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-57844237b4ff91c4b99786f5df5743bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-21d3c2b6efe6fd8eea11802d91f90383.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-663f6f2d1de495ce984de2ca8f5a127a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-b1e77f838e8a4e4fc93a42425a81ebb3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-7d5750e06f968169bdbd4275eb06e7e8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-d5a6cdd274a04bf115fb73ef5d1b5321.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-0a3ba7802bfbfc215778c720ac436f65.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-36d4321bf9530fa74ad61e961c472460.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-8a4e8a35b56795a27af765786faef5a6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-e47ae50c59cd501eb26910eac465d187.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-4b5dca0ba48f96d6d62c98b97905c210.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-13e42dd9dc5c88cdc96b2154fd512658.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-b674c6f2171709675498d24d00e01a30.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-335a4fe73a278852399f8d318dbe0887.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-f08e14deee7e2160fdc69165b42c53aa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-ff2c914c4cbffec8397c40b83550eec1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-375b6495c543095ba1e43ef45e7a29b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-79957a31caa0489b7be0ca41c918c877.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-8562fc909c99444cd58843b85dbcb30b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-2e2cc0c394426ac94220c8c00f8791ce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-f3b07f7f1a3d4af97dacee328e89dfa4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-840db5d19961c699754d9ef6b97097a3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-501f7f2abfb0970949a4bf5fbfbec1bf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-a047da1a252781e5bb47627dec4cf6f3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-8b55db4514e32829c10bffbbd6b7c80a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-64f5b3bfc3ed9e4a94c62274d10dc06e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-3c886b2a804795a257a5c24017ba5803.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-8c2e4ca842ad633840b7454d5c3c84f0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 29),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-3986261bb0370f30cb49de03ab50b3ce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng tắm", "Bathroom", false, 30),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-60dd10a96bf405d9ebebfc38327de0e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 31),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-008eea2470b898eb38d0cd8b0866164f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Cảnh quan", "View", false, 32),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-2fa44b98e3e07ebf630a14a9da3393e0.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 33));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 105.8486219, 21.0339571 }),
                heading(ContentSection.STORY, of("Giới thiệu chung về Lotus Village Hotel Hà Nội", "About Lotus Village Hotel Hanoi")),
                paragraph(ContentSection.STORY,
                        of("Lotus Village Hotel Hà Nội là khách sạn 3 sao tọa lạc tại số 68 Hàng Bồ, ngay trong lòng Phố cổ thuộc quận Hoàn Kiếm. Với 18 phòng nghỉ trải trên 4 tầng cùng phong cách ấm cúng, khách sạn là lựa chọn lý tưởng cho du khách muốn khám phá khu phố cổ sầm uất và Hồ Hoàn Kiếm chỉ trong vài phút đi bộ.", "Lotus Village Hotel Hanoi is a 3-star hotel located at 68 Hang Bo, right in the heart of the Old Quarter in Hoan Kiem District. With 18 rooms across 4 floors and a cozy style, the hotel is an ideal choice for travelers wishing to explore the bustling old streets and Hoan Kiem Lake just a few minutes' walk away.")),
                heading(ContentSection.STORY, of("Các loại phòng & tiện nghi", "Room types & amenities")),
                paragraph(ContentSection.STORY,
                        of("Khách sạn cung cấp các hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình, phù hợp cho cả khách lẻ lẫn gia đình. Mỗi phòng được trang bị máy lạnh, minibar, máy sấy tóc, phòng tắm vòi sen, nước nóng và Wi-Fi miễn phí, mang lại sự thoải mái sau một ngày dạo bước khám phá Hà Nội.", "The hotel offers room categories from Standard, Superior and Deluxe to Family rooms, suitable for both solo travelers and families. Each room is equipped with air conditioning, a minibar, a hair dryer, a shower, hot water and free Wi-Fi, providing comfort after a day of exploring Hanoi.")),
                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
                paragraph(ContentSection.STORY,
                        of("Khách sạn phục vụ bữa sáng cùng quầy cà phê ấm cúng ngay tại sảnh. Nằm giữa Phố cổ, du khách còn dễ dàng thưởng thức tinh hoa ẩm thực đường phố Hà Nội như phở, bún chả, cà phê trứng tại vô số quán ăn nổi tiếng chỉ cách vài bước chân.", "The hotel serves breakfast along with a cozy coffee corner right in the lobby. Set in the Old Quarter, guests can easily savor the essence of Hanoi street food such as pho, bun cha and egg coffee at countless famous eateries just steps away.")),
                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
                paragraph(ContentSection.STORY,
                        of("Lễ tân 24 giờ luôn sẵn sàng hỗ trợ đặt tour, giữ hành lý, giặt ủi, thu đổi ngoại tệ và đưa đón sân bay theo yêu cầu. Khách sạn cũng hỗ trợ nhận phòng sớm và trả phòng muộn tùy tình trạng phòng trống, giúp lịch trình của du khách thêm linh hoạt.", "The 24-hour front desk is always ready to assist with tour bookings, luggage storage, laundry, currency exchange and airport transfers on request. The hotel also supports early check-in and late check-out subject to availability, making travelers' schedules more flexible.")),
                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),
                paragraph(ContentSection.STORY,
                        of("Từ khách sạn, du khách chỉ mất vài phút đi bộ để đến Hồ Hoàn Kiếm, đền Ngọc Sơn, chợ Đồng Xuân và Nhà thờ Lớn. Các điểm đến nổi tiếng như Văn Miếu - Quốc Tử Giám, Hoàng thành Thăng Long và Nhà hát Lớn Hà Nội đều nằm trong tầm di chuyển ngắn.", "From the hotel, travelers are just a few minutes' walk from Hoan Kiem Lake, Ngoc Son Temple, Dong Xuan Market and St Joseph's Cathedral. Famous destinations such as the Temple of Literature, the Imperial Citadel of Thang Long and the Hanoi Opera House are all within a short trip.")),
                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
                paragraph(ContentSection.STORY,
                        of("Phố đi bộ quanh Hồ Gươm cuối tuần rất gần khách sạn, thuận tiện cho việc tản bộ và mua sắm. Sân bay quốc tế Nội Bài cách khoảng 26 km; khách sạn có thể thu xếp dịch vụ đưa đón sân bay và thuê xe để du khách di chuyển dễ dàng.", "The weekend walking street around Hoan Kiem Lake is very close to the hotel, convenient for strolling and shopping. Noi Bai International Airport is about 26 km away; the hotel can arrange airport transfers and car rental for easy travel.")),
                heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),
                paragraph(ContentSection.STORY,
                        of("Hàng Bồ nằm giữa khu 36 phố phường với những con phố nghề truyền thống, cửa hàng lưu niệm, tiệm vàng và quán cà phê đặc trưng. Đây là khu vực nhộn nhịp cả ngày lẫn đêm, mang đến trải nghiệm văn hóa Hà Nội chân thực ngay trước cửa khách sạn.", "Hang Bo lies amid the 36 ancient streets with traditional craft lanes, souvenir shops, jewelers and characterful cafes. This is a lively area day and night, offering an authentic Hanoi cultural experience right at the hotel's doorstep.")),
                heading(ContentSection.STORY, of("Vì sao nên chọn Lotus Village Hotel Hà Nội", "Why choose Lotus Village Hotel Hanoi")),
                paragraph(ContentSection.STORY,
                        of("Chọn Lotus Village Hotel Hà Nội, du khách có được vị trí trung tâm Phố cổ tuyệt vời, phòng nghỉ ấm cúng với mức giá hợp lý và dịch vụ tận tâm từ lễ tân 24 giờ. Đây là điểm dừng chân lý tưởng để khám phá trọn vẹn vẻ đẹp lịch sử và nhịp sống sôi động của thủ đô.", "Choosing Lotus Village Hotel Hanoi gives travelers a superb central Old Quarter location, cozy rooms at a reasonable price and attentive service from the 24-hour front desk. It is an ideal base to fully explore the historic beauty and vibrant life of the capital.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng nhanh", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng nhanh", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM / banking"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("wifi", of("Wifi tại khu vực chung", "WiFi in public area"), AmenityGroup.INTERNET, AmenitySection.INTERNET, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-lotus-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi.", "Standard room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa lòng Phố cổ.", "Standard room 18 sqm, 1 double bed. Fully equipped with amenities for a comfortable stay in the heart of the Old Quarter."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-c6e7649afd7056188fd37ab7163b962f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-57844237b4ff91c4b99786f5df5743bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lotus-standard-double-prepaid-nonref")
                                        .name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("650000"))
                                        .referencePricePerNight(new BigDecimal("928571"))
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
                                        .id("rt-lotus-standard-double-breakfast-flex")
                                        .name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("747500"))
                                        .referencePricePerNight(new BigDecimal("1067857"))
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
                        .id("rt-lotus-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 22 m², 2 giường đơn.", "Superior room 22 sqm, 2 single beds."))
                        .description(of("Phòng Superior 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa lòng Phố cổ.", "Superior room 22 sqm, 2 single beds. Fully equipped with amenities for a comfortable stay in the heart of the Old Quarter."))
                        .areaSqm(22.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-21d3c2b6efe6fd8eea11802d91f90383.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-d5a6cdd274a04bf115fb73ef5d1b5321.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lotus-superior-twin-prepaid-nonref")
                                        .name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("780000"))
                                        .referencePricePerNight(new BigDecimal("1114286"))
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
                                        .id("rt-lotus-superior-twin-breakfast-flex")
                                        .name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("897000"))
                                        .referencePricePerNight(new BigDecimal("1281429"))
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
                        .id("rt-lotus-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen.", "Deluxe room 26 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe 26 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa lòng Phố cổ.", "Deluxe room 26 sqm, 1 queen bed. Fully equipped with amenities for a comfortable stay in the heart of the Old Quarter."))
                        .areaSqm(26.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(4)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-36d4321bf9530fa74ad61e961c472460.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-335a4fe73a278852399f8d318dbe0887.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-lotus-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
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
                                        .id("rt-lotus-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
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
                        .id("rt-lotus-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Gia đình 32 m², 1 giường Queen + 1 giường đơn, cho 4 khách.", "Family room 32 sqm, 1 queen + 1 single bed, for 4 guests."))
                        .description(of("Phòng Gia đình 32 m², 1 giường Queen + 1 giường đơn, cho 4 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái giữa lòng Phố cổ.", "Family room 32 sqm, 1 queen + 1 single bed, for 4 guests. Fully equipped with amenities for a comfortable stay in the heart of the Old Quarter."))
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
                        .totalRooms(2)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-375b6495c543095ba1e43ef45e7a29b4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20029620-79957a31caa0489b7be0ca41c918c877.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "SEATING_AREA",
                                "BLACKOUT_CURTAINS"))
                        .ratePlans(List.of(
                                RatePlan.builder()
                                        .id("rt-lotus-family-room-prepaid-nonref")
                                        .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
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
                                        .id("rt-lotus-family-room-breakfast-flex")
                                        .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
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
                nearby("ho-hoan-kiem", of("Hồ Hoàn Kiếm", "Hoan Kiem Lake"), of("Địa điểm lân cận", "Nearby places"), 350.0),
                nearby("pho-co-ha-noi", of("Phố cổ Hà Nội", "Hanoi Old Quarter"), of("Địa điểm lân cận", "Nearby places"), 200.0),
                nearby("cho-dong-xuan", of("Chợ Đồng Xuân", "Dong Xuan Market"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("nha-tho-lon-ha-noi", of("Nhà thờ Lớn Hà Nội", "St Joseph's Cathedral"), of("Địa điểm lân cận", "Nearby places"), 700.0),
                nearby("pho-di-bo-ho-guom", of("Phố đi bộ Hồ Gươm", "Hoan Kiem Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 400.0),
                nearby("nha-hat-lon-ha-noi", of("Nhà hát Lớn Hà Nội", "Hanoi Opera House"), of("Địa điểm lân cận", "Nearby places"), 1300.0),
                nearby("cau-long-bien", of("Cầu Long Biên", "Long Bien Bridge"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
                nearby("hoang-thanh-thang-long", of("Hoàng thành Thăng Long", "Imperial Citadel of Thang Long"), of("Địa điểm lân cận", "Nearby places"), 2200.0),
                nearby("van-mieu-quoc-tu-giam", of("Văn Miếu - Quốc Tử Giám", "Temple of Literature"), of("Địa điểm lân cận", "Nearby places"), 2800.0),
                nearby("lang-chu-tich-ho-chi-minh", of("Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
                nearby("chua-mot-cot", of("Chùa Một Cột", "One Pillar Pagoda"), of("Địa điểm lân cận", "Nearby places"), 3100.0),
                nearby("ho-tay", of("Hồ Tây", "West Lake"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("chua-tran-quoc", of("Chùa Trấn Quốc", "Tran Quoc Pagoda"), of("Địa điểm lân cận", "Nearby places"), 3800.0),
                nearby("bao-tang-dan-toc-hoc", of("Bảo tàng Dân tộc học", "Museum of Ethnology"), of("Khác", "Other"), 7000.0),
                nearby("san-bay-noi-bai", of("Sân bay quốc tế Nội Bài", "Noi Bai International Airport"), of("Trung tâm giao thông", "Transport hub"), 26000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi miễn phí, đưa đón sân bay", "Air conditioning, 24-hour front desk, parking, free WiFi, airport transfer"))
                                .distanceToCityCenterKm(0.4)
                                .popularAreaSummary(of("Phố cổ Hà Nội, Hồ Hoàn Kiếm, Chợ Đồng Xuân", "Hanoi Old Quarter, Hoan Kiem Lake, Dong Xuan Market"))
                                .totalRooms(18)
                                .totalFloors(4)
                                .otherHighlightFacilities(of("Đưa đón sân bay, đặt tour, giặt ủi, giữ hành lý, thu đổi ngoại tệ.", "Airport transfer, tour desk, laundry, luggage storage, currency exchange."))
                                .interestingPlacesSummary(of("Phố cổ Hà Nội, Hồ Hoàn Kiếm, Nhà thờ Lớn", "Hanoi Old Quarter, Hoan Kiem Lake, St Joseph's Cathedral"))
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
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí) theo yêu cầu.", "The hotel offers airport transfer (surcharge) on request."))
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
                faq(of("Những tiện ích tại Lotus Village Hotel Hà Nội?", "What amenities does Lotus Village Hotel Hanoi offer?"),
                        of("Các tiện ích bao gồm máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi, dịch vụ giặt ủi, giữ hành lý và đưa đón sân bay (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, 24-hour front desk, parking, WiFi, laundry, luggage storage and airport transfer (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Lotus Village Hotel Hà Nội?", "What are the check-in and check-out times?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Lotus Village Hotel Hà Nội đạt tiêu chuẩn mấy sao?", "What star rating does Lotus Village Hotel Hanoi have?"),
                        of("Lotus Village Hotel Hà Nội là khách sạn đạt tiêu chuẩn 3 sao.", "Lotus Village Hotel Hanoi is a 3-star hotel.")),
                faq(of("Lotus Village Hotel Hà Nội có bao nhiêu tầng?", "How many floors does the hotel have?"),
                        of("Khách sạn có tổng cộng 4 tầng.", "The hotel has a total of 4 floors.")),
                faq(of("Tổng số phòng hiện có tại khách sạn là bao nhiêu?", "How many rooms does the hotel have in total?"),
                        of("Khách sạn có tổng cộng 18 phòng.", "The hotel has a total of 18 rooms.")),
                faq(of("Lotus Village Hotel Hà Nội có chỗ đậu xe không?", "Does the hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
                faq(of("Khách sạn có hỗ trợ nhận phòng sớm không?", "Does the hotel support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact directly to arrange.")),
                faq(of("Khách sạn có cung cấp dịch vụ trả phòng trễ không?", "Does the hotel offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact directly to arrange.")),
                faq(of("Lotus Village Hotel Hà Nội có dịch vụ đưa đón sân bay không?", "Does the hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay (thu phí) theo yêu cầu; sân bay Nội Bài cách khoảng 26 km.", "Yes, the hotel offers airport transfer (surcharge) on request; Noi Bai Airport is about 26 km away.")),
                faq(of("Lotus Village Hotel Hà Nội có mức giá là bao nhiêu?", "How much does Lotus Village Hotel Hanoi cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 650.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 650,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có phục vụ bữa sáng không?", "Does the hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ha-noi")
                                .cityName(of("Hà Nội", "Hanoi"))
                                .districtName(of("Hoàn Kiếm", "Hoan Kiem"))
                                .wardName(of("Hàng Bồ", "Hang Bo"))
                                .addressLine(of("68 Hàng Bồ, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội, Việt Nam, 100000", "68 Hang Bo, Hang Bo Ward, Hoan Kiem District, Hanoi, Vietnam, 100000"))
                                .location(new double[] { 105.8486219, 21.0339571 })
                                .name(of("Lotus Village Hotel Hà Nội", "Lotus Village Hotel Hanoi"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao ấm cúng tại Phố cổ Hà Nội, sát Hồ Hoàn Kiếm.", "Cozy 3-star hotel in Hanoi Old Quarter, next to Hoan Kiem Lake."))
                                .description(of("Lotus Village Hotel Hà Nội là khách sạn 3 sao 18 phòng tại số 68 Hàng Bồ, ngay trung tâm Phố cổ Hoàn Kiếm, chỉ vài phút đi bộ đến Hồ Hoàn Kiếm và chợ Đồng Xuân.", "Lotus Village Hotel Hanoi is an 18-room 3-star hotel at 68 Hang Bo in the heart of the Hoan Kiem Old Quarter, just a few minutes' walk from Hoan Kiem Lake and Dong Xuan Market."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("650000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("928571"))
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
