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
public class SeedHnLeGrand {

        @Bean
        CommandLineRunner seedHnLeGrandRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHnLeGrand] start");
                        HotelDoc doc = hotelHnLeGrand();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHnLeGrand] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHnLeGrand] done");
                };
        }

        // =====================================================================
        // HA NOI LE GRAND HOTEL
        // (source: Traveloka hotelId 9000001072104; phòng được dựng thêm)
        // =====================================================================
        private static HotelDoc hotelHnLeGrand() {
                String slug = "ha-noi-le-grand-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-326893946a4dfa8dbc7b7773b17503cb.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-9708a0480139c9b61cd04955d18b7cd4.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-45d2373d5ab3dd59958c69a879278863.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh chức năng", "Functional hall", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-2c94fec4eac488ca17be47f417889035.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-e14ea88bd84dde542379e49b3edc602b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-499a69f9f68034a3de4ca1581fd20569.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-e38e1497550b67392053d8e2b3c68ecc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-e1e983820823fa9b841492d3fc52b513.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-11ce923a0e86cd1087f4216d6cf78a2e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-ea781c4a03791bc585f4e307435d707d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-68973b0c96c3893205b2532abfc9f3bd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-531ed005c3b030ba087efea7f9ab3a78.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-79d084dd845b85fc97c4f82736f56a56.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-978e789b47bb2d1ad91b0096829443ca.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-47aef4bad05b996308f97ed5d804a8b1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-d53972fe67068e652fc32242d315ed0b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-c963d177fa269bf387774eb8bac4c827.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-21ea2458205c4b29597e68f820447de5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-77d6b0c12618b8cdca0c857223447ea7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-2aa0a399befefdf1e5b4c3d1b01cf213.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-6bb35a5e447d48a9b44d022603c3b206.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-dbdbf9faac0f4ca321df413339c2bfff.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-867d8e54638490c9e94e50d96b8c789b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-a6e14473a06a3c74fb74bcdb75d1deb0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-26d33b5d47f79d3cf863dd6e161a78bf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-a44db59e5652f757e3de8770a1f5e2cb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-fcc21831cf003e22e18f95ce01f66677.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-dc3158b69b4d090ad0d77ab9c13af7f1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-3af82eeb76c8cf3a12dd14a8e9f6eec9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-599a2b874e7a07b46e66adf6f54a235c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 29),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-24827f6fe7d1633a57e4640aee8bf170.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 30),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-b3ee4fc91045557c03d83d9c6591fd5f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 31),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-cbdbe840c9358850f61ef29272f8f125.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 32),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-3c1f77e68cc1b77bd9863a19c4f02250.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 33),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-2e1c30876d68a8314c47df959551c233.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 34));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 105.8653857, 21.0118758 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Ha Noi Le Grand Hotel", "About Ha Noi Le Grand Hotel")),

                paragraph(ContentSection.STORY,
                                of("Ha Noi Le Grand Hotel là khách sạn 3 sao tọa lạc tại số 3 B9 Đầm Trấu, phường Bạch Đằng, quận Hai Bà Trưng, Hà Nội. Với 46 phòng trải trên 7 tầng, khách sạn mang đến không gian nghỉ ngơi ấm cúng, sạch sẽ và tiện nghi, phù hợp cho cả khách công tác lẫn du khách khám phá Thủ đô.", "Ha Noi Le Grand Hotel is a 3-star hotel located at 3 B9 Dam Trau, Bach Dang Ward, Hai Ba Trung District, Hanoi. With 46 rooms across 7 floors, the hotel offers a cozy, clean and well-equipped place to stay, ideal for both business travelers and tourists exploring the capital.")),

                heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Family, đáp ứng nhu cầu của khách lẻ, cặp đôi và gia đình. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, phòng tắm riêng với vòi sen và đồ dùng cá nhân cùng Wi-Fi miễn phí.", "The hotel offers a variety of room categories from Standard, Superior and Deluxe to Family rooms, meeting the needs of solo travelers, couples and families. Each room is equipped with air conditioning, a minibar, a work desk, a hair dryer, a private bathroom with shower and toiletries, plus free Wi-Fi.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn phục vụ bữa sáng cùng các món ăn Việt Nam và quốc tế trong không gian ấm cúng. Du khách cũng dễ dàng tìm thấy nhiều quán ăn, quán cà phê và nhà hàng đặc sản Hà Nội ngay quanh khu vực Đầm Trấu và Bạch Đằng.", "The hotel serves breakfast along with Vietnamese and international dishes in a cozy setting. Guests can also easily find many eateries, cafes and Hanoi specialty restaurants right around the Dam Trau and Bach Dang area.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Ha Noi Le Grand Hotel có lễ tân 24 giờ, dịch vụ dọn phòng, giặt ủi, giữ hành lý, thang máy và chỗ đậu xe. Khách sạn còn hỗ trợ đặt tour, đưa đón sân bay (thu phí) cùng các cửa hàng tiện lợi và máy ATM gần kề.", "Ha Noi Le Grand Hotel features a 24-hour front desk, housekeeping, laundry, luggage storage, an elevator and parking. The hotel also assists with tour booking, airport transfer (surcharge), and offers nearby convenience shops and an ATM.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan", "Location & attractions")),

                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách chỉ mất ít phút di chuyển để đến Hồ Hoàn Kiếm, Phố cổ Hà Nội, Nhà hát Lớn và Cầu Long Biên. Khu vực Hai Bà Trưng cũng kết nối thuận tiện đến Văn Miếu - Quốc Tử Giám và khu lăng Bác.", "From the hotel, it takes just a few minutes to reach Hoan Kiem Lake, the Old Quarter, the Opera House and Long Bien Bridge. The Hai Ba Trung area also conveniently connects to the Temple of Literature and the Ho Chi Minh Mausoleum complex.")),

                heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn nằm gần ga Hà Nội và các trục đường chính, thuận tiện cho việc đi taxi, xe công nghệ hay xe buýt. Sân bay quốc tế Nội Bài cách khoảng 26 km, có thể đặt dịch vụ đưa đón của khách sạn để di chuyển thuận lợi.", "The hotel sits near Hanoi Station and major roads, convenient for taxis, ride-hailing or buses. Noi Bai International Airport is about 26 km away, and guests can book the hotel's transfer service for an easy trip.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Quanh khách sạn là khu dân cư yên tĩnh ven sông Hồng với chợ Đầm Trấu, các quán ăn địa phương và cửa hàng tiện lợi. Chỉ một quãng ngắn là tới khu phố cổ sầm uất với chợ Đồng Xuân và phố đi bộ Hồ Gươm vào cuối tuần.", "Around the hotel is a quiet riverside residential area along the Red River with Dam Trau Market, local eateries and convenience stores. A short distance away is the bustling Old Quarter with Dong Xuan Market and the Hoan Kiem walking street on weekends.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Ha Noi Le Grand Hotel", "Why choose Ha Noi Le Grand Hotel")),

                paragraph(ContentSection.STORY,
                                of("Chọn Ha Noi Le Grand Hotel, du khách có được vị trí trung tâm, giá hợp lý, phòng sạch sẽ tiện nghi cùng dịch vụ thân thiện. Đây là lựa chọn lý tưởng để vừa nghỉ ngơi thoải mái vừa dễ dàng khám phá những điểm đến nổi bật của Hà Nội.", "Choosing Ha Noi Le Grand Hotel gives travelers a central location, reasonable prices, clean and comfortable rooms and friendly service. It is an ideal choice to rest comfortably while easily exploring Hanoi's highlights.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("atm", of("Máy ATM", "ATM"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe", "Car rental"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng nhanh", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng nhanh", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("wifi", of("WiFi tại khu vực chung", "WiFi in public areas"), AmenityGroup.INTERNET, AmenitySection.INTERNET, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-hnlegrand-standard-double")
                        .name(of("Standard Double", "Standard Double"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi.", "Standard room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái tại Hà Nội.", "Standard room 18 sqm, 1 double bed. Fully equipped for a comfortable stay in Hanoi."))
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-499a69f9f68034a3de4ca1581fd20569.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-e38e1497550b67392053d8e2b3c68ecc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-hnlegrand-standard-double-prepaid-nonref")
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
                                        .id("rt-hnlegrand-standard-double-breakfast-flex")
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
                        .id("rt-hnlegrand-superior-twin")
                        .name(of("Superior Twin", "Superior Twin"))
                        .shortDescription(of("Phòng Superior 22 m², 2 giường đơn.", "Superior room 22 sqm, 2 single beds."))
                        .description(of("Phòng Superior 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái tại Hà Nội.", "Superior room 22 sqm, 2 single beds. Fully equipped for a comfortable stay in Hanoi."))
                        .areaSqm(22.0)
                        .bedType(BedType.TWIN)
                        .bedsCount(2)
                        .bedLayoutDescription(of("2 giường đơn", "2 single beds"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(12)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-e1e983820823fa9b841492d3fc52b513.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-11ce923a0e86cd1087f4216d6cf78a2e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-hnlegrand-superior-twin-prepaid-nonref")
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
                                        .id("rt-hnlegrand-superior-twin-breakfast-flex")
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
                        .id("rt-hnlegrand-deluxe-queen")
                        .name(of("Deluxe Queen", "Deluxe Queen"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen.", "Deluxe room 26 sqm, 1 queen bed."))
                        .description(of("Phòng Deluxe 26 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái tại Hà Nội.", "Deluxe room 26 sqm, 1 queen bed. Fully equipped for a comfortable stay in Hanoi."))
                        .areaSqm(26.0)
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-ea781c4a03791bc585f4e307435d707d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20061391-68973b0c96c3893205b2532abfc9f3bd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-hnlegrand-deluxe-queen-prepaid-nonref")
                                        .name(of("Deluxe Queen - Không gồm bữa sáng", "Deluxe Queen - Room only"))
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
                                        .id("rt-hnlegrand-deluxe-queen-breakfast-flex")
                                        .name(of("Deluxe Queen - Bao gồm bữa sáng", "Deluxe Queen - Breakfast included"))
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
                        .id("rt-hnlegrand-family-room")
                        .name(of("Family Room", "Family Room"))
                        .shortDescription(of("Phòng Family 32 m², 1 giường Queen + 1 giường đơn, cho 4 khách.", "Family room 32 sqm, 1 queen + 1 single bed, for 4 guests."))
                        .description(of("Phòng Family 32 m², 1 giường Queen + 1 giường đơn, cho 4 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái tại Hà Nội.", "Family room 32 sqm, 1 queen + 1 single bed, for 4 guests. Fully equipped for a comfortable stay in Hanoi."))
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
                        .totalRooms(10)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-531ed005c3b030ba087efea7f9ab3a78.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-79d084dd845b85fc97c4f82736f56a56.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67843880-978e789b47bb2d1ad91b0096829443ca.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2)))
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
                                        .id("rt-hnlegrand-family-room-prepaid-nonref")
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
                                        .id("rt-hnlegrand-family-room-breakfast-flex")
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
                nearby("ho-hoan-kiem", of("Hồ Hoàn Kiếm", "Hoan Kiem Lake"), of("Địa điểm lân cận", "Nearby places"), 2800.0),
                nearby("pho-co-ha-noi", of("Phố cổ Hà Nội", "Hanoi Old Quarter"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
                nearby("nha-hat-lon-ha-noi", of("Nhà hát Lớn Hà Nội", "Hanoi Opera House"), of("Địa điểm lân cận", "Nearby places"), 2200.0),
                nearby("cau-long-bien", of("Cầu Long Biên", "Long Bien Bridge"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
                nearby("nha-tho-lon-ha-noi", of("Nhà thờ Lớn Hà Nội", "St Joseph Cathedral"), of("Địa điểm lân cận", "Nearby places"), 3200.0),
                nearby("cho-dong-xuan", of("Chợ Đồng Xuân", "Dong Xuan Market"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
                nearby("pho-di-bo-ho-guom", of("Phố đi bộ Hồ Gươm", "Hoan Kiem Walking Street"), of("Trung tâm giải trí", "Entertainment hub"), 2900.0),
                nearby("van-mieu-quoc-tu-giam", of("Văn Miếu - Quốc Tử Giám", "Temple of Literature"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
                nearby("hoang-thanh-thang-long", of("Hoàng thành Thăng Long", "Imperial Citadel of Thang Long"), of("Địa điểm lân cận", "Nearby places"), 4800.0),
                nearby("lang-chu-tich-ho-chi-minh", of("Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum"), of("Địa điểm lân cận", "Nearby places"), 5200.0),
                nearby("chua-mot-cot", of("Chùa Một Cột", "One Pillar Pagoda"), of("Địa điểm lân cận", "Nearby places"), 5300.0),
                nearby("ho-tay", of("Hồ Tây", "West Lake"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("chua-tran-quoc", of("Chùa Trấn Quốc", "Tran Quoc Pagoda"), of("Địa điểm lân cận", "Nearby places"), 6200.0),
                nearby("bao-tang-dan-toc-hoc", of("Bảo tàng Dân tộc học", "Vietnam Museum of Ethnology"), of("Khác", "Other"), 8500.0),
                nearby("san-bay-noi-bai", of("Sân bay quốc tế Nội Bài", "Noi Bai International Airport"), of("Trung tâm giao thông", "Transport hub"), 26000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy, WiFi miễn phí", "Air conditioning, 24-hour front desk, parking, elevator, free WiFi"))
                                .distanceToCityCenterKm(2.8)
                                .popularAreaSummary(of("Phố cổ Hà Nội, Hồ Hoàn Kiếm, Cầu Long Biên", "Hanoi Old Quarter, Hoan Kiem Lake, Long Bien Bridge"))
                                .totalRooms(46)
                                .totalFloors(7)
                                .otherHighlightFacilities(of("Đưa đón sân bay, hỗ trợ đặt tour, dịch vụ giặt ủi, giữ hành lý.", "Airport transfer, tour booking, laundry service, luggage storage."))
                                .interestingPlacesSummary(of("Phố cổ Hà Nội, Văn Miếu - Quốc Tử Giám, Nhà hát Lớn Hà Nội", "Hanoi Old Quarter, Temple of Literature, Hanoi Opera House"))
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
                                                                .title(of("Chính sách trẻ em", "Children policy"))
                                                                .content(of("Khách sạn có phòng Family phù hợp cho gia đình; vui lòng liên hệ về chính sách trẻ em và giường phụ.", "The hotel has Family rooms suitable for families; please contact regarding children policy and extra beds."))
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
                faq(of("Những tiện ích tại Ha Noi Le Grand Hotel?", "What amenities does Ha Noi Le Grand Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy, dịch vụ dọn phòng và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a 24-hour front desk, parking, an elevator, housekeeping and WiFi (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Ha Noi Le Grand Hotel?", "What are the check-in and check-out times at Ha Noi Le Grand Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Ha Noi Le Grand Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Ha Noi Le Grand Hotel have?"),
                        of("Ha Noi Le Grand Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Ha Noi Le Grand Hotel is a 3-star hotel.")),
                faq(of("Ha Noi Le Grand Hotel có bao nhiêu tầng?", "How many floors does Ha Noi Le Grand Hotel have?"),
                        of("Khách sạn có tổng cộng 7 tầng.", "The hotel has a total of 7 floors.")),
                faq(of("Tổng số phòng tại Ha Noi Le Grand Hotel là bao nhiêu?", "How many rooms does Ha Noi Le Grand Hotel have?"),
                        of("Khách sạn có tổng cộng 46 phòng.", "The hotel has a total of 46 rooms.")),
                faq(of("Ha Noi Le Grand Hotel có chỗ đậu xe không?", "Does Ha Noi Le Grand Hotel have parking?"),
                        of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
                faq(of("Ha Noi Le Grand Hotel có dịch vụ đưa đón sân bay không?", "Does Ha Noi Le Grand Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel offers airport transfer on request (surcharge). Please contact in advance to arrange.")),
                faq(of("Ha Noi Le Grand Hotel có mức giá là bao nhiêu?", "How much does Ha Noi Le Grand Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có phục vụ bữa sáng không?", "Does the hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn có gần trung tâm và phố cổ không?", "Is the hotel close to the city center and Old Quarter?"),
                        of("Có, khách sạn nằm tại quận Hai Bà Trưng, chỉ vài phút di chuyển đến Hồ Hoàn Kiếm, Phố cổ và Nhà hát Lớn Hà Nội.", "Yes, the hotel is in Hai Ba Trung District, just a few minutes from Hoan Kiem Lake, the Old Quarter and the Hanoi Opera House.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ha-noi")
                                .cityName(of("Hà Nội", "Hanoi"))
                                .districtName(of("Hai Bà Trưng", "Hai Ba Trung"))
                                .wardName(of("Bạch Đằng", "Bach Dang"))
                                .addressLine(of("3 B9 Đầm Trấu, Bạch Đằng, Quận Hai Bà Trưng, Hà Nội, Việt Nam, 11600", "3 B9 Dam Trau , Bach Dang Ward, Hai Ba Trung District, Hanoi, Vietnam, 11600"))
                                .location(new double[] { 105.8653857, 21.0118758 })
                                .name(of("Ha Noi Le Grand Hotel", "Ha Noi Le Grand Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao tại quận Hai Bà Trưng, gần Hồ Hoàn Kiếm và phố cổ Hà Nội.", "3-star hotel in Hai Ba Trung District, near Hoan Kiem Lake and Hanoi Old Quarter."))
                                .description(of("Ha Noi Le Grand Hotel là khách sạn 3 sao với 46 phòng tiện nghi, vị trí trung tâm tại quận Hai Bà Trưng, thuận tiện khám phá Hồ Hoàn Kiếm, phố cổ và các điểm tham quan nổi bật của Thủ đô.", "Ha Noi Le Grand Hotel is a 3-star hotel with 46 well-equipped rooms in a central location in Hai Ba Trung District, convenient for exploring Hoan Kiem Lake, the Old Quarter and the capital's notable attractions."))
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
