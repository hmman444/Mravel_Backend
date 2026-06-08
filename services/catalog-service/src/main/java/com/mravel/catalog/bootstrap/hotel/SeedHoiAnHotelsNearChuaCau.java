// src/main/java/com/mravel/catalog/bootstrap/hotel/SeedHoiAnHotelsNearChuaCau.java
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
@Profile("seed")
public class SeedHoiAnHotelsNearChuaCau {

        @Bean
        CommandLineRunner seedHoiAnHotelsRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHoiAnHotelsNearChuaCau] start");

                        HotelDoc bespoke = hotelBespokeVillaHoiAn();

                        repo.findBySlugAndActiveTrue(bespoke.getSlug())
                                        .ifPresent(existing -> bespoke.setId(existing.getId()));

                        repo.save(bespoke);
                        System.out.println(">>> [SeedHoiAnHotelsNearChuaCau] upsert: " + bespoke.getSlug());

                        System.out.println(">>> [SeedHoiAnHotelsNearChuaCau] done");
                };
        }

        // =====================================================================
        // 1) BESPOKE VILLA HOI AN
        // =====================================================================

        private static HotelDoc hotelBespokeVillaHoiAn() {
                String slug = "bespoke-villa-hoi-an";

                // --------- Ảnh chính ----------
                List<Image> images = List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-a0dcfa844ef3fa9dca7471bb2a425c06.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-332,pr-true,q-80,w-480",
                                                "Phòng giường King sáng sủa với cửa kính lớn", "Bright King-bed room with large glass doors", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-3a3abd8330cf1e249dea561d8afab0a3.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666",
                                                "Khu vườn và sân hiên xanh mát", "Lush green garden and terrace", false, 1),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-01c14097304a34bfe357d690e62625ba.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666",
                                                "Mặt tiền Bespoke Villa Hoi An", "Facade of Bespoke Villa Hoi An", false, 2),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-29097353e5227135e44e14f76bb9b765.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666",
                                                "Phòng Family rộng rãi cho gia đình", "Spacious Family room for families", false, 3),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9205b8c583ff7e638002fc244035b6cb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666",
                                                "Phòng Family rộng rãi cho gia đình", "Spacious Family room for families", false, 4),
                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-1d698d9a708e69b7e1d4bec1de436d42.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Phương tiện di chuyển có sẵn", "Transport options available", false, 5),
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9ed917f9c71a6ba21d2f9e708c3ce9fb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Góc chill ngoài trời", "Outdoor chill-out corner", false, 6),
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-27e485ffe9e0f97b558e9c84e9cf32da.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Góc nhìn từ trên cao", "Aerial view", false, 7),
                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-e6f552f3e3cd0e7c5192b3514ce58f69.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Villa nhìn từ phía trước", "Villa seen from the front", false, 8),
                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-1f31fd16c1b22521286f593103dc359a.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Khoảng trống rộng cho các cuộc tụ tập", "Spacious area for gatherings", false, 9),
                                img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-036b9553a89058fe1280ed9f521ae373.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640",
                                                "Không gian ban công buổi tối thơ mộng", "Romantic balcony space in the evening", false, 10));

                // --------- Content (Overview + Story) ----------
                List<ContentBlock> content = List.of(
                                // ===== OVERVIEW =====
                                paragraph(ContentSection.OVERVIEW,
                                                of("Bespoke Villa Hoi An là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại phường Tân An. Đây là lựa chọn phù hợp cho du khách muốn tìm một nơi lưu trú yên bình nhưng vẫn thuận tiện để di chuyển vào trung tâm phố cổ Hội An.",
                                                                "Bespoke Villa Hoi An is a stay located in a secure area in Tan An ward. It is a great choice for travellers looking for a peaceful place to stay that still offers convenient access to the centre of Hoi An Ancient Town.")),
                                heading(ContentSection.OVERVIEW, of("Vị trí", "Location")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Khi lưu trú tại nơi nghỉ thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Bespoke Villa Hoi An mang đến không gian lưu trú làm hài lòng quý khách, vừa hiện đại vừa gần gũi với không khí cổ kính của phố Hội.",
                                                                "When staying at a property, the interiors and architecture are surely two important factors that delight guests. With its unique design, Bespoke Villa Hoi An offers a space that pleases guests, both modern and in harmony with the ancient atmosphere of Hoi An.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Bespoke Villa Hoi An sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ thân thiện.",
                                                                "For travellers seeking a comfortable trip on a budget, Bespoke Villa Hoi An is the perfect place to stay, offering quality amenities and friendly service.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Bespoke Villa Hoi An, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.",
                                                                "Enjoy a fun time with the whole family thanks to a range of entertainment amenities at Bespoke Villa Hoi An, a wonderful stay ideal for every getaway with loved ones.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Du lịch một mình cũng không hề kém phần thú vị và Bespoke Villa Hoi An là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.",
                                                                "Travelling solo is no less enjoyable, and Bespoke Villa Hoi An is the ideal place for those who value privacy during their stay.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi nơi nghỉ cung cấp sẽ khiến quý khách khó có thể phàn nàn trong suốt kỳ lưu trú. Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào.",
                                                                "Excellent service, complete facilities and the amenities on offer will leave you with little to complain about throughout your stay. The 24-hour front desk is always ready to assist you, from check-in and check-out to any request.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Bespoke Villa Hoi An chỉ dành riêng cho quý khách. Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.",
                                                                "Enjoy your favourite dishes with the distinctive culinary style of Bespoke Villa Hoi An, made just for you. WiFi coverage throughout the property's common areas lets you stay connected with family and friends.")),
                                paragraph(ContentSection.OVERVIEW,
                                                of("Bespoke Villa Hoi An là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú. Với những tiện nghi sẵn có, nơi đây thực sự là một điểm dừng chân lý tưởng cho hành trình khám phá Hội An.",
                                                                "According to most guests, Bespoke Villa Hoi An is a property with full amenities and excellent service. With the facilities on offer, it is truly an ideal stop for your journey to explore Hoi An.")),
                                mapBlock(ContentSection.OVERVIEW, new double[] { 108.3205, 15.8765 }),

                                // ===== STORY =====

                                // 1. Giới thiệu & lịch sử hình thành
                                heading(ContentSection.STORY, of("Lịch sử hình thành Bespoke Villa Hoi An", "The history of Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Biệt thự Bespoke Villa Hoi An là một điểm đến lý tưởng cho những ai đang tìm kiếm sự yên bình và sang trọng tại thành phố cổ Hội An. Tọa lạc tại một vị trí đắc địa, biệt thự, khách sạn Hội An này mang đến cho du khách không gian nghỉ dưỡng hoàn hảo giữa thiên nhiên tươi đẹp và kiến trúc tinh tế, với thiết kế hiện đại kết hợp hài hòa cùng nét truyền thống.",
                                                                "Bespoke Villa Hoi An is an ideal destination for those seeking peace and luxury in the ancient town of Hoi An. Set in a prime location, this Hoi An villa and hotel offers guests a perfect retreat amid beautiful nature and refined architecture, with a modern design blended harmoniously with traditional touches.")),
                                paragraph(ContentSection.STORY,
                                                of("Biệt thự được xây dựng vào năm 2021 và được nâng cấp toàn diện vào năm 2024, trang bị đầy đủ tiện nghi cao cấp từ khu vực sân vườn thoáng đãng đến các phòng nghỉ rộng rãi với nội thất thanh lịch. Mỗi góc nhỏ trong biệt thự đều được chăm chút tỉ mỉ, tạo cảm giác ấm cúng và thoải mái như ở nhà, cùng đội ngũ nhân viên nhiệt tình và chuyên nghiệp.",
                                                                "The villa was built in 2021 and fully renovated in 2024, fitted with premium amenities from the airy garden area to the spacious rooms with elegant interiors. Every corner of the villa is meticulously cared for, creating a warm and homely feel, together with an enthusiastic and professional staff.")),
                                paragraph(ContentSection.STORY,
                                                of("Từ Bespoke Villa Hoi An, du khách có thể dễ dàng di chuyển đến các điểm tham quan nổi tiếng như Phố cổ Hội An, chợ đêm Hội An, Bảo tàng Lịch sử và Nghệ thuật,... Đây là lựa chọn hoàn hảo cho kỳ nghỉ dưỡng của gia đình hoặc nhóm bạn bè, nơi bạn có thể tận hưởng những khoảnh khắc thư giãn và đáng nhớ, trốn khỏi sự ồn ào của đô thị và hòa mình vào không gian yên bình của Hội An.",
                                                                "From Bespoke Villa Hoi An, guests can easily reach famous attractions such as Hoi An Ancient Town, Hoi An Night Market, the Museum of History and Culture, and more. It is the perfect choice for a getaway with family or friends, where you can enjoy relaxing and memorable moments, escape the city noise and immerse yourself in the peaceful atmosphere of Hoi An.")),

                                // 2. Kiến trúc & phong cách
                                heading(ContentSection.STORY, of("Đặc trưng kiến trúc và phong cách Bespoke Villa Hoi An", "The architecture and style of Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Hình ảnh Bespoke Villa Hoi An hiện lên là một biệt thự 3 tầng hiện đại, là sự kết hợp giữa phong cách hiện đại và nét truyền thống địa phương. Với thiết kế thanh lịch, sử dụng các vật liệu tự nhiên như gỗ và đá, biệt thự tạo nên không gian ấm cúng và gần gũi, cùng tông màu trung tính như trắng, be và nâu mang lại cảm giác thư giãn cho du khách.",
                                                                "Bespoke Villa Hoi An appears as a modern 3-story villa, a blend of contemporary style and local tradition. With an elegant design using natural materials such as wood and stone, the villa creates a warm and welcoming space, while neutral tones of white, beige and brown bring a relaxing feel to guests.")),
                                paragraph(ContentSection.STORY,
                                                of("Không gian nội thất được thiết kế tinh tế với các phòng nghỉ rộng rãi, trang bị đầy đủ tiện nghi hiện đại. Mỗi phòng đều có cửa sổ lớn hoặc ban công, cho phép ánh sáng tự nhiên tràn ngập và cung cấp tầm nhìn đẹp ra khu vườn hoặc cảnh quan xung quanh. Khu vực sân vườn và sân hiên được chăm sóc kỹ lưỡng, tạo không gian xanh mát và yên bình, lý tưởng cho việc thư giãn hoặc tổ chức các hoạt động ngoài trời.",
                                                                "The interior is tastefully designed with spacious rooms fully equipped with modern amenities. Each room has large windows or a balcony, letting natural light flood in and offering beautiful views of the garden or surrounding scenery. The garden and terrace areas are carefully maintained, creating a green and peaceful space, ideal for relaxing or hosting outdoor activities.")),

                                // 3. Điểm nổi bật
                                heading(ContentSection.STORY, of("Điểm nổi bật của Bespoke Villa Hoi An", "Highlights of Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Vị trí đắc địa: Tọa lạc gần trung tâm phố cổ Hội An, Bespoke Villa Hoi An không chỉ sở hữu vị trí thuận lợi mà còn là điểm dừng chân lý tưởng cho những ai muốn khám phá nét văn hóa và lịch sử độc đáo của vùng đất này. Chỉ cần vài phút di chuyển, bạn có thể ghé thăm Chùa Cầu Hội An, Bảo tàng Lịch sử và Nghệ thuật, Đình Cẩm Phô hay Chùa Chúc Thánh.",
                                                                "Prime location: Set near the centre of Hoi An Ancient Town, Bespoke Villa Hoi An not only enjoys a convenient location but is also an ideal stop for those who want to explore the unique culture and history of this land. Just a few minutes away, you can visit the Japanese Covered Bridge, the Museum of History and Culture, Cam Pho Communal House or Chuc Thanh Pagoda.")),
                                paragraph(ContentSection.STORY,
                                                of("Tiện nghi đẳng cấp: Khi đến với Bespoke Villa Hoi An, sự tận tâm trong từng chi tiết dịch vụ sẽ khiến bạn cảm thấy hài lòng. Không chỉ sở hữu những phòng nghỉ sang trọng, đầy đủ tiện nghi hiện đại, Bespoke Villa Hoi An còn mang đến một loạt dịch vụ đa dạng và không gian sân vườn tươi mát, là nơi lưu giữ những kỷ niệm đáng nhớ.",
                                                                "Premium amenities: At Bespoke Villa Hoi An, the dedication in every detail of service will leave you satisfied. Beyond luxurious rooms fully equipped with modern amenities, Bespoke Villa Hoi An also offers a range of diverse services and a fresh garden space, a place to keep memorable moments.")),
                                paragraph(ContentSection.STORY,
                                                of("Giá cả phải chăng: Bespoke Villa Hoi An là một lựa chọn tuyệt vời cho những ai tìm kiếm kỳ nghỉ thoải mái mà vẫn tiết kiệm chi phí. Nằm trên con đường Lê Hồng Phong nhộn nhịp, khách sạn vừa thuận tiện di chuyển đến các điểm du lịch, vừa giúp giảm chi phí đi lại. Với dịch vụ tận tâm và giá cả hợp lý, nơi đây mang lại trải nghiệm thư thái như ở nhà.",
                                                                "Affordable prices: Bespoke Villa Hoi An is a great choice for those seeking a comfortable holiday while saving money. Located on the bustling Le Hong Phong street, the hotel is both convenient for reaching tourist spots and helps reduce travel costs. With dedicated service and reasonable prices, it offers a relaxing, home-like experience.")),

                                // 4. Các hạng phòng
                                heading(ContentSection.STORY, of("Các hạng phòng của Bespoke Villa Hoi An", "Room types at Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Thông tin hạng phòng được cung cấp là chính xác tại thời điểm viết, vui lòng tham khảo Traveloka để biết giá phòng mới nhất.",
                                                                "The room type information provided is accurate at the time of writing; please refer to Traveloka for the latest room prices.")),
                                paragraph(ContentSection.STORY,
                                                of("Bespoke Villa Hoi An sở hữu tổng cộng 10 phòng đạt chuẩn 3.0 sao với giá phòng hợp lý, dao động khoảng từ 380.000 VND đến 617.000 VND/đêm trên Traveloka, gồm hai hạng phòng chính: Deluxe Double và Family.",
                                                                "Bespoke Villa Hoi An has a total of 10 rooms rated 3.0 stars with reasonable prices, ranging from about VND 380,000 to VND 617,000 per night on Traveloka, including two main room types: Deluxe Double and Family.")),

                                heading(ContentSection.STORY, of("Deluxe Double", "Deluxe Double")),
                                paragraph(ContentSection.STORY,
                                                of("Diện tích: 30 m², sức chứa: 2 người lớn, giường ngủ: 1 giường cỡ King, tầm nhìn: thành phố, phù hợp với khách lẻ, cặp đôi hoặc gia đình nhỏ, giá phòng chỉ từ khoảng 380.000 VND/phòng/đêm.",
                                                                "Area: 30 sqm, capacity: 2 adults, bed: 1 King-size bed, view: city, suitable for solo travellers, couples or small families, with prices from around VND 380,000 per room per night.")),
                                paragraph(ContentSection.STORY,
                                                of("Phòng Deluxe Double tại Bespoke Villa Hoi An là sự kết hợp hoàn hảo giữa nét sang trọng hiện đại và tinh thần hoài cổ của phố Hội. Với giường cỡ King rộng rãi, không gian màu sắc trung tính như trắng, be và nâu gỗ, căn phòng mang đến cảm giác thư thái, ấm cúng và thanh lịch cho du khách.",
                                                                "The Deluxe Double room at Bespoke Villa Hoi An is a perfect blend of modern luxury and the nostalgic spirit of Hoi An. With a spacious King-size bed and neutral tones of white, beige and wood brown, the room offers guests a relaxing, cozy and elegant feel.")),
                                paragraph(ContentSection.STORY,
                                                of("Trần nhà cao và cửa sổ lớn giúp căn phòng luôn thoáng đãng, đón ánh sáng tự nhiên và mở ra khung cảnh thành phố Hội An hiền hòa. Không gian được bày trí tối giản nhưng tinh tế với giường êm ái, đèn ngủ dịu nhẹ và bàn làm việc nhỏ gọn, mang lại sự tiện nghi tối đa và bầu không khí nhẹ nhàng, đầy cảm hứng.",
                                                                "High ceilings and large windows keep the room airy, welcoming natural light and opening onto the gentle scenery of Hoi An. The space is decorated in a minimalist yet refined style with a soft bed, gentle bedside lighting and a compact desk, providing maximum comfort and a light, inspiring atmosphere.")),

                                heading(ContentSection.STORY, of("Family", "Family")),
                                paragraph(ContentSection.STORY,
                                                of("Diện tích: 40 m², sức chứa: 4 người lớn, giường ngủ: 1 giường cỡ King và 2 giường đơn, tầm nhìn: hướng thành phố, phù hợp cho gia đình hoặc nhóm bạn, giá phòng chỉ từ khoảng 617.000 VND/phòng/đêm.",
                                                                "Area: 40 sqm, capacity: 4 adults, beds: 1 King-size bed and 2 single beds, view: city, suitable for families or groups of friends, with prices from around VND 617,000 per room per night.")),
                                paragraph(ContentSection.STORY,
                                                of("Phòng Family tại Bespoke Villa Hoi An là sự kết hợp giữa không gian rộng rãi, tiện nghi và cảm giác ấm cúng, mang đến nơi nghỉ lý tưởng cho gia đình hoặc nhóm bạn muốn tận hưởng kỳ nghỉ bên nhau. Bố cục giường King và 2 giường đơn được sắp xếp khoa học, vừa đảm bảo riêng tư vừa tạo sự gắn kết.",
                                                                "The Family room at Bespoke Villa Hoi An combines spaciousness, comfort and a cozy feel, making it an ideal stay for families or groups of friends who want to enjoy a holiday together. The layout of a King bed and 2 single beds is thoughtfully arranged to ensure both privacy and togetherness.")),
                                paragraph(ContentSection.STORY,
                                                of("Tông màu nhẹ nhàng, thanh lịch cùng những điểm nhấn gỗ trầm ấm giúp căn phòng trở nên gần gũi và thư giãn. Cửa kính lớn mở ra khung cảnh thành phố yên bình, ánh sáng tự nhiên tràn ngập không gian. Bàn làm việc gọn gàng, ghế thư giãn cạnh cửa sổ và hệ thống đèn ngủ dịu nhẹ mang lại cảm giác dễ chịu, giúp bạn tận hưởng trọn vẹn từng khoảnh khắc nghỉ dưỡng.",
                                                                "Soft, elegant tones together with warm wooden accents make the room welcoming and relaxing. Large glass doors open onto a peaceful city view, and natural light fills the space. A tidy desk, a relaxing chair by the window and gentle bedside lighting bring a pleasant feel, helping you fully enjoy every moment of your stay.")),

                                // 5. Tiện ích & dịch vụ
                                heading(ContentSection.STORY, of("Tiện ích và dịch vụ tại Bespoke Villa Hoi An", "Amenities and services at Bespoke Villa Hoi An")),
                                heading(ContentSection.STORY, of("Nhà hàng", "Restaurant")),
                                paragraph(ContentSection.STORY,
                                                of("Bespoke Villa Hoi An là một biệt thự, khách sạn Hội An có phục vụ bữa sáng được rất nhiều du khách yêu thích. Nhà hàng mang phong cách kết hợp giữa nét truyền thống đặc trưng của Hội An và sự hiện đại tinh tế, không gian mở, thoáng đãng, vừa là nơi thưởng thức món ăn ngon vừa là nơi thư giãn, trò chuyện và lưu giữ những khoảnh khắc đáng nhớ bên gia đình, bạn bè.",
                                                                "Bespoke Villa Hoi An is a Hoi An villa and hotel serving breakfast that is loved by many guests. The restaurant blends the distinctive traditional style of Hoi An with refined modernity, in an open and airy space that is both a place to savour delicious food and to relax, chat and keep memorable moments with family and friends.")),

                                // 6. Vị trí & cách di chuyển
                                heading(ContentSection.STORY, of("Vị trí và cách di chuyển đến Bespoke Villa Hoi An", "Location and how to get to Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Vị trí: 153 Lê Hồng Phong, Hội An, Tỉnh Quảng Nam, Việt Nam, mã vùng 560000.",
                                                                "Location: 153 Le Hong Phong, Hoi An, Quang Nam Province, Vietnam, postal code 560000.")),
                                heading(ContentSection.STORY, of("Di chuyển bằng máy bay", "Travelling by plane")),
                                paragraph(ContentSection.STORY,
                                                of("Bạn có thể đặt vé máy bay đến sân bay quốc tế Đà Nẵng, sau đó di chuyển về Hội An bằng taxi, xe máy hoặc dịch vụ đưa đón sân bay. Đây là cách di chuyển nhanh và thuận tiện nhất để đến Bespoke Villa Hoi An.",
                                                                "You can book a flight to Da Nang International Airport, then travel to Hoi An by taxi, motorbike or airport transfer service. This is the fastest and most convenient way to reach Bespoke Villa Hoi An.")),
                                heading(ContentSection.STORY, of("Di chuyển bằng xe khách", "Travelling by coach")),
                                paragraph(ContentSection.STORY,
                                                of("Nếu muốn tiết kiệm chi phí, bạn có thể đi xe khách đến Đà Nẵng, sau đó bắt xe tuyến Đà Nẵng – Hội An hoặc các phương tiện khác để đến trung tâm Hội An và tiếp tục di chuyển đến Bespoke Villa Hoi An.",
                                                                "If you want to save money, you can take a coach to Da Nang, then catch the Da Nang - Hoi An bus or other transport to reach the centre of Hoi An and continue on to Bespoke Villa Hoi An.")),
                                heading(ContentSection.STORY, of("Di chuyển bằng phương tiện cá nhân", "Travelling by personal vehicle")),
                                paragraph(ContentSection.STORY,
                                                of("Hội An cách thành phố Hồ Chí Minh hơn 766 km, phù hợp với những ai đam mê khám phá bằng phương tiện cá nhân. Bạn có thể chủ động dừng chân, chiêm ngưỡng cảnh đẹp dọc đường đi, nhưng hãy chuẩn bị kỹ lưỡng cung đường, thời gian nghỉ ngơi và đảm bảo an toàn trong suốt hành trình.",
                                                                "Hoi An is more than 766 km from Ho Chi Minh City, suitable for those who love exploring by their own vehicle. You can stop at will and admire the scenery along the way, but be sure to carefully plan your route and rest stops and ensure safety throughout the journey.")),

                                // 7. Các địa điểm vui chơi gần
                                heading(ContentSection.STORY, of("Các địa điểm vui chơi gần Bespoke Villa Hoi An", "Attractions near Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Hội An là một trong những điểm đến hấp dẫn nhất Việt Nam với vẻ đẹp cổ kính cùng giá trị văn hóa, lịch sử độc đáo. Từ Bespoke Villa Hoi An, bạn có thể dễ dàng khám phá những show diễn, tour tham quan và khu vui chơi nổi bật dành cho mọi lứa tuổi.",
                                                                "Hoi An is one of the most appealing destinations in Vietnam, with its ancient beauty and unique cultural and historical value. From Bespoke Villa Hoi An, you can easily explore outstanding shows, sightseeing tours and entertainment areas for all ages.")),

                                heading(ContentSection.STORY, of("Show Ký Ức Hội An", "Hoi An Memories Show")),
                                paragraph(ContentSection.STORY,
                                                of("Giá vé tham khảo từ khoảng 180.000 VND/khách. Đây là chương trình biểu diễn nghệ thuật đặc sắc kết hợp âm nhạc, múa và ánh sáng, tái hiện sống động lịch sử và văn hóa Hội An qua từng phân đoạn sân khấu công phu, mang lại trải nghiệm rất ấn tượng cho du khách.",
                                                                "Reference ticket price from around VND 180,000 per guest. This is a remarkable art performance combining music, dance and light, vividly recreating the history and culture of Hoi An through elaborate stage scenes, offering guests a very impressive experience.")),

                                heading(ContentSection.STORY, of("Tour Đà Nẵng – Hội An 1 ngày", "Da Nang - Hoi An 1-day tour")),
                                paragraph(ContentSection.STORY,
                                                of("Giá vé tham khảo từ khoảng 1.337.349 VND/khách. Tour thường kết hợp tham quan Ngũ Hành Sơn, Bãi biển Mỹ Khê và Phố cổ Hội An, giúp bạn vừa khám phá thiên nhiên, vừa tìm hiểu thêm về lịch sử, văn hóa và ẩm thực đặc trưng của miền Trung.",
                                                                "Reference ticket price from around VND 1,337,349 per guest. The tour usually combines visits to the Marble Mountains, My Khe Beach and Hoi An Ancient Town, letting you explore nature while learning more about the history, culture and signature cuisine of Central Vietnam.")),

                                heading(ContentSection.STORY, of("VinWonders Nam Hội An", "VinWonders Nam Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Giá vé tham khảo từ khoảng 270.000 VND/khách. Đây là công viên chủ đề kết hợp giữa giải trí hiện đại và không gian tái hiện văn hóa truyền thống, với nhiều khu vui chơi, trò mạo hiểm, khu check-in, khu dành cho gia đình và trẻ em, phù hợp cho cả nhóm bạn và gia đình có trẻ nhỏ.",
                                                                "Reference ticket price from around VND 270,000 per guest. This is a theme park combining modern entertainment with spaces recreating traditional culture, featuring many play areas, adventure rides, check-in spots and zones for families and children, suitable for groups of friends and families with young kids.")),

                                // 8. Hướng dẫn đặt phòng
                                heading(ContentSection.STORY, of("Hướng dẫn đặt phòng Bespoke Villa Hoi An", "How to book Bespoke Villa Hoi An")),
                                heading(ContentSection.STORY, of("Vì sao bạn nên đặt phòng cùng Traveloka?", "Why should you book with Traveloka?")),
                                paragraph(ContentSection.STORY,
                                                of("Traveloka là nền tảng du lịch hàng đầu Đông Nam Á, cung cấp đầy đủ dịch vụ từ vé máy bay, khách sạn đến vé vui chơi, với hơn 1 triệu lựa chọn khách sạn trên toàn cầu. Bạn có thể dễ dàng so sánh giá, đặt phòng nhanh chóng, quản lý giao dịch linh hoạt, thanh toán an toàn với nhiều hình thức và được hỗ trợ bởi đội ngũ chăm sóc khách hàng chuyên nghiệp.",
                                                                "Traveloka is a leading travel platform in Southeast Asia, offering a full range of services from flights and hotels to attraction tickets, with more than 1 million hotel choices worldwide. You can easily compare prices, book quickly, manage transactions flexibly, pay securely with many methods and be supported by a professional customer care team.")),
                                paragraph(ContentSection.STORY,
                                                of("Để đặt phòng Bespoke Villa Hoi An trên Traveloka, bạn chỉ cần: bước 1, truy cập website hoặc ứng dụng Traveloka; bước 2, tại thanh tìm kiếm, nhập Bespoke Villa Hoi An, chọn thời gian nhận/trả phòng và số lượng phòng, sau đó chọn hạng phòng phù hợp và tiến hành thanh toán; bước 3, sau khi hoàn tất đặt phòng, bạn sẽ nhận được xác nhận qua ứng dụng và email trong khoảng vài phút.",
                                                                "To book Bespoke Villa Hoi An on Traveloka, simply: step 1, visit the Traveloka website or app; step 2, in the search bar, enter Bespoke Villa Hoi An, select your check-in/check-out dates and the number of rooms, then choose a suitable room type and proceed to payment; step 3, once your booking is complete, you will receive a confirmation via the app and email within a few minutes.")),

                                // 9. Review từ khách hàng
                                heading(ContentSection.STORY, of("Review Bespoke Villa Hoi An từ khách hàng", "Guest reviews of Bespoke Villa Hoi An")),
                                paragraph(ContentSection.STORY,
                                                of("Khách lưu trú tại Bespoke Villa Hoi An thường để lại đánh giá rất tích cực, phản ánh mức độ hài lòng cao với chất lượng phòng ở, dịch vụ và sự thân thiện của nhân viên.",
                                                                "Guests staying at Bespoke Villa Hoi An often leave very positive reviews, reflecting high satisfaction with the room quality, service and the friendliness of the staff.")),
                                paragraph(ContentSection.STORY,
                                                of("Nhận xét khách hàng 1 (Bùi, 10/10): Khách sạn đẹp, nhân viên tốt, rất hài lòng với trải nghiệm nghỉ dưỡng tại đây.",
                                                                "Guest review 1 (Bui, 10/10): Beautiful hotel, great staff, very satisfied with the stay experience here.")),
                                paragraph(ContentSection.STORY,
                                                of("Nhận xét khách hàng 2 (Angelica P. R., 9.4/10): Nhân viên cực kỳ thân thiện, luôn hỗ trợ giải đáp thắc mắc về Hội An, tạo cảm giác gần gũi và thoải mái, là nơi mà khách muốn quay lại và giới thiệu cho bạn bè.",
                                                                "Guest review 2 (Angelica P. R., 9.4/10): The staff are extremely friendly and always help answer questions about Hoi An, creating a warm and comfortable feel; it is a place guests want to return to and recommend to friends.")),
                                paragraph(ContentSection.STORY,
                                                of("Nhận xét khách hàng 3 (Nguyễn N. A., 8/10): Chủ nhà thân thiện, phòng và phòng tắm sạch sẽ, tổng thể trải nghiệm dễ chịu.",
                                                                "Guest review 3 (Nguyen N. A., 8/10): Friendly host, clean room and bathroom, an overall pleasant experience.")),
                                paragraph(ContentSection.STORY,
                                                of("Bespoke Villa Hoi An là điểm dừng chân phù hợp cho những du khách yêu thích sự tiện nghi, sang trọng nhưng vẫn gần gũi và thư thái khi ghé thăm thành phố Hội An. Những trải nghiệm tuyệt vời tại đây đang chờ bạn khám phá.",
                                                                "Bespoke Villa Hoi An is an ideal stop for travellers who love comfort and luxury that remain warm and relaxing when visiting Hoi An. Wonderful experiences here are waiting for you to discover.")));

                // --------- Tiện ích cấp khách sạn (Hotel amenities) ----------
                // Chỉ giữ HOTEL scope canonical codes (xem SeedAmenityCatalog).
                List<AmenityCatalogDoc> hotelAmenities = List.of(
                                // Tiện nghi chung
                                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, true),
                                amenity("front_desk_24h", of("Lễ tân 24h", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES, true),
                                amenity("parking", of("Chỗ đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT, true),
                                amenity("wifi_free", of("WiFi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET,
                                                AmenitySection.INTERNET, true),

                                // Dịch vụ khách sạn
                                amenity("welcome_drink", of("Thức uống chào mừng miễn phí", "Free welcome drink"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("concierge", of("Dịch vụ concierge/hỗ trợ khách", "Concierge service"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("currency_exchange", of("Dịch vụ thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("early_checkin", of("Nhận phòng sớm", "Early check-in"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("express_checkin", of("Dịch vụ nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("express_checkout", of("Dịch vụ trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("late_checkout", of("Trả phòng trễ", "Late check-out"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("room_service_limited", of("Dịch vụ phòng (có giới hạn thời gian)", "Room service (limited hours)"),
                                                AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, false),
                                amenity("luggage_storage", of("Dịch vụ lưu trữ/bảo quản hành lý", "Luggage storage"),
                                                AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("multilingual_staff", of("Đội ngũ nhân viên đa ngôn ngữ", "Multilingual staff"),
                                                AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),
                                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour desk"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.OTHER, false),

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

                                // Tiện nghi chung khác
                                amenity("family_room", of("Phòng gia đình", "Family room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM,
                                                AmenitySection.BASIC_FACILITIES, false),
                                amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),
                                amenity("terrace", of("Sân thượng/sân hiên", "Terrace"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.OTHER, false),

                                // Vận chuyển / đưa đón
                                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("bicycle_rental", of("Dịch vụ cho thuê xe đạp", "Bicycle rental"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.TRANSPORT, false),
                                amenity("garage", of("Nhà chứa xe", "Garage"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.TRANSPORT, false),

                                // Ẩm thực
                                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE,
                                                AmenitySection.FOOD_AND_DRINK, false),

                                // Hoạt động / giải trí
                                amenity("garden", of("Vườn hoa", "Garden"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false),
                                amenity("dart", of("Phi tiêu", "Darts"), AmenityGroup.PUBLIC_AREA,
                                                AmenitySection.ENTERTAINMENT, false));

                // --------- Room types & rate plans ----------
                // Deluxe Twin
                RoomType deluxeTwin = RoomType.builder()
                                .id("rt-bespoke-deluxe-twin")
                                .name(of("Deluxe Twin", "Deluxe Twin"))
                                .shortDescription(of("Phòng Deluxe Twin 30 m², 2 giường đơn, phù hợp 2 khách.",
                                                "Deluxe Twin room, 30 sqm, 2 single beds, ideal for 2 guests."))
                                .description(of("""
                                                                    Phòng Deluxe Twin mang đến không gian sáng sủa với 2 giường đơn, phù hợp cho bạn bè hoặc đồng nghiệp
                                                                    đi cùng. Tông màu trung tính, nội thất tối giản nhưng tiện nghi đầy đủ giúp kỳ nghỉ của bạn thoải mái.
                                                                """, """
                                                                    The Deluxe Twin room offers a bright space with 2 single beds, perfect for friends or colleagues
                                                                    travelling together. Neutral tones and minimalist yet fully equipped furnishings make your stay comfortable.
                                                                """))
                                .areaSqm(30.0)
                                .bedType(BedType.TWIN)
                                .bedsCount(2)
                                .bedLayoutDescription(of("2 giường đơn (Twin)", "2 single beds (Twin)"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.TWIN).count(2).build()))
                                .maxAdults(2)
                                .maxChildren(0)
                                .maxGuests(2)
                                .totalRooms(30)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-40b386dd4aa1a67d9ab61b08f2138609.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Deluxe Twin với 2 giường đơn", "Deluxe Twin room with 2 single beds", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-6b87c5b998f53106be04311d5d0d91ca.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian rộng rãi", "Spacious space", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-62d9a62c2d1e0a499b6f1fb8e7e4c8c4.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian thoáng đãng", "Airy space", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-2ef2ea5c29bf2c6afdd4ebc6f543b2cc.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Nhà vệ sinh sạch sẽ", "Clean bathroom", false, 3)))
                                .amenityCodes(List.of(
                                                "AC",
                                                "HOT_WATER",
                                                "SHOWER",
                                                "SEATING_AREA",
                                                "NON_SMOKING",
                                                "TV",
                                                "DESK",
                                                "BLACKOUT_CURTAINS",
                                                "BOTTLED_WATER",
                                                "HAIR_DRYER",
                                                "BATH_AMENITIES",
                                                "BATHROBE"))
                                .ratePlans(List.of(
                                                // Plan 1: Deluxe Twin Room - Room Only, không hoàn tiền
                                                RatePlan.builder()
                                                                .id("rp-bespoke-deluxe-twin-room-only-nonref-1")
                                                                .name(of("Deluxe Twin Room - Room Only", "Deluxe Twin Room - Room Only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                                                // Giá đã bao gồm thuế và phí
                                                                .pricePerNight(new BigDecimal("379932")) // giảm còn
                                                                                                         // (bao gồm
                                                                                                         // thuế/phí)
                                                                .referencePricePerNight(new BigDecimal("434308")) // giá
                                                                                                                  // gốc
                                                                                                                  // (bao
                                                                                                                  // gồm
                                                                                                                  // thuế/phí)
                                                                .discountPercent(13) // xấp xỉ
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Giá tốt cho phòng đôi", "Great deal for double room"))
                                                                .showLowAvailability(true)
                                                                .build(),
                                                // Plan 2: Deluxe Twin Room - Room Only, giá cao hơn
                                                RatePlan.builder()
                                                                .id("rp-bespoke-deluxe-twin-room-only-nonref-2")
                                                                .name(of("Deluxe Twin Room - Room Only", "Deluxe Twin Room - Room Only"))
                                                                .boardType(BoardType.BREAKFAST_INCLUDED)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                                                .pricePerNight(new BigDecimal("434308")) // giảm còn
                                                                                                         // (bao gồm
                                                                                                         // thuế/phí)
                                                                .referencePricePerNight(new BigDecimal("579077")) // giá
                                                                                                                  // gốc
                                                                                                                  // (bao
                                                                                                                  // gồm
                                                                                                                  // thuế/phí)
                                                                .discountPercent(25) // xấp xỉ
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Bửa sáng ngon miệng cho 2 người", "Delicious breakfast for 2"))
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // Family
                RoomType family = RoomType.builder()
                                .id("rt-bespoke-family")
                                .name(of("Family", "Family"))
                                .shortDescription(of("Phòng Family 40 m², 1 giường King + 2 giường đơn, tối đa 4 khách.",
                                                "Family room, 40 sqm, 1 King bed + 2 single beds, up to 4 guests."))
                                .description(of("""
                                                                    Phòng Family rộng rãi, thích hợp cho gia đình hoặc nhóm bạn. Một giường King và hai giường đơn
                                                                    được bố trí khoa học, đảm bảo vừa riêng tư vừa gắn kết. Cửa kính lớn, ban công/sân hiên và ánh sáng
                                                                    tự nhiên tràn ngập giúp không gian luôn thoáng đãng và ấm cúng.
                                                                """, """
                                                                    The spacious Family room suits families or groups of friends. One King bed and two single beds
                                                                    are thoughtfully arranged for both privacy and togetherness. Large glass doors, a balcony/terrace and
                                                                    abundant natural light keep the space airy and cozy.
                                                                """))
                                .areaSqm(40.0)
                                .bedType(BedType.MULTIPLE)
                                .bedsCount(3)
                                .bedLayoutDescription(of("1 giường king và 2 giường đơn", "1 King bed and 2 single beds"))
                                .bedOptions(List.of(
                                                BedOption.builder().type(BedType.KING).count(1).build(),
                                                BedOption.builder().type(BedType.SINGLE).count(2).build()))
                                .maxAdults(4)
                                .maxChildren(0)
                                .maxGuests(4)
                                .totalRooms(25)
                                .images(List.of(
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-a0dcfa844ef3fa9dca7471bb2a425c06.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Phòng Family với giường King và giường đơn", "Family room with King bed and single beds", true, 0),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-baa1ba82fcc6360f9cf43d7b89d8ca4f.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian phòng Family hướng thành phố", "Family room with city view", false, 1),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-29097353e5227135e44e14f76bb9b765.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian phòng Family", "Family room interior", false, 2),
                                                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9205b8c583ff7e638002fc244035b6cb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724",
                                                                "Không gian phòng Family", "Family room interior", false, 3)))
                                .amenityCodes(List.of(
                                                "AC",
                                                "BALCONY",
                                                "HOT_WATER",
                                                "SEATING_AREA",
                                                "NON_SMOKING",
                                                "COFFEE_TEA_MAKER",
                                                "TV",
                                                "DESK",
                                                "BLACKOUT_CURTAINS",
                                                "BOTTLED_WATER",
                                                "HAIR_DRYER",
                                                "BATH_AMENITIES",
                                                "BATHROBE"))
                                .ratePlans(List.of(
                                                // Plan 1: Family Room - Room Only, không hoàn tiền
                                                RatePlan.builder()
                                                                .id("rp-bespoke-family-room-only-nonref")
                                                                .name(of("Family Room - Room Only", "Family Room - Room Only"))
                                                                .boardType(BoardType.ROOM_ONLY)
                                                                .paymentType(PaymentType.PREPAID)
                                                                .refundable(false)
                                                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                                                .pricePerNight(new BigDecimal("635947")) // giảm còn
                                                                                                         // (bao gồm
                                                                                                         // thuế/phí)
                                                                .referencePricePerNight(new BigDecimal("726963")) // giá
                                                                                                                  // gốc
                                                                                                                  // (bao
                                                                                                                  // gồm
                                                                                                                  // thuế/phí)
                                                                .discountPercent(13)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Giá tốt cho phòng Family", "Great deal for Family room"))
                                                                .showLowAvailability(true) // còn 2 phòng
                                                                .build(),
                                                // Plan 2: Family Room - Room Only, thanh toán tại KS, có hủy theo chính
                                                // sách
                                                RatePlan.builder()
                                                                .id("rp-bespoke-family-room-only-pay-hotel")
                                                                .name(of("Family Room - Room Only", "Family Room - Room Only"))
                                                                .boardType(BoardType.BREAKFAST_INCLUDED)
                                                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                                                .refundable(true)
                                                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng, vui lòng xem chi tiết khi đặt.",
                                                                                "Cancellation policy applies, please see details when booking."))
                                                                .pricePerNight(new BigDecimal("726963")) // giảm còn
                                                                                                         // (bao gồm
                                                                                                         // thuế/phí)
                                                                .referencePricePerNight(new BigDecimal("969284")) // giá
                                                                                                                  // gốc
                                                                                                                  // (bao
                                                                                                                  // gồm
                                                                                                                  // thuế/phí)
                                                                .discountPercent(25)
                                                                .taxPercent(new BigDecimal("8"))
                                                                .serviceFeePercent(new BigDecimal("7"))
                                                                .priceIncludesTax(true)
                                                                .priceIncludesServiceFee(true)
                                                                .lengthOfStayDiscounts(List.of())
                                                                .promoLabel(of("Bửa sáng ngon miệng cho cả gia đình", "Delicious breakfast for the whole family"))
                                                                .showLowAvailability(true)
                                                                .build()))
                                .build();

                // --------- Nearby places ----------
                List<NearbyPlace> nearby = List.of(
                                // Địa điểm lân cận
                                nearby("chua-chuc-thanh", of("Chùa Chúc Thánh", "Chuc Thanh Pagoda"), of("Địa điểm lân cận", "Nearby places"), 590.0),
                                nearby("chua-phuoc-lam", of("Chùa Phước Lâm", "Phuoc Lam Pagoda"), of("Địa điểm lân cận", "Nearby places"), 653.0),
                                nearby("ancient-house", of("Ancient House", "Ancient House"), of("Địa điểm lân cận", "Nearby places"), 752.0),
                                nearby("dinh-cam-pho", of("Đình Cẩm Phô", "Cam Pho Communal House"), of("Địa điểm lân cận", "Nearby places"), 984.0),
                                nearby("quang-truong-song-hoai", of("Quảng trường Sông Hoài", "Hoai River Square"), of("Địa điểm lân cận", "Nearby places"), 1090.0),
                                nearby("hoi-quan-quang-dong", of("Hội quán Quảng Đông - Chùa Quảng Triệu", "Cantonese Assembly Hall - Quang Trieu Temple"),
                                                of("Địa điểm lân cận", "Nearby places"), 1140.0),
                                nearby("nha-co-phung-hung", of("Nhà cổ Phùng Hưng", "Phung Hung Ancient House"), of("Địa điểm lân cận", "Nearby places"), 1170.0),
                                nearby("chua-cau-hoi-an", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Địa điểm lân cận", "Nearby places"), 1170.0),
                                nearby("bao-tang-sa-huynh", of("Bảo tàng Văn hóa Sa Huỳnh", "Sa Huynh Culture Museum"), of("Địa điểm lân cận", "Nearby places"), 1210.0),
                                nearby("old-town-lantern-boat", of("Old Town Lantern Boat", "Old Town Lantern Boat"), of("Địa điểm lân cận", "Nearby places"), 1230.0),
                                nearby("huynh-tu-duong", of("Huỳnh Từ Đường", "Huynh Family Chapel"), of("Địa điểm lân cận", "Nearby places"), 1250.0),
                                nearby("duc-an-house", of("Duc An House", "Duc An House"), of("Địa điểm lân cận", "Nearby places"), 1250.0),
                                nearby("nha-tan-ky", of("Nhà Tân Kỳ", "Tan Ky Ancient House"), of("Địa điểm lân cận", "Nearby places"), 1310.0),
                                nearby("bao-tang-lich-su-nghe-thuat", of("Bảo tàng Lịch sử và Nghệ thuật", "Museum of History and Culture"),
                                                of("Địa điểm lân cận", "Nearby places"), 1330.0),
                                nearby("bao-tang-gom-su-hoi-an", of("Bảo tàng gốm sứ mậu dịch Hội An", "Hoi An Trade Ceramics Museum"), of("Địa điểm lân cận", "Nearby places"),
                                                1350.0),

                                // Trung tâm giao thông
                                nearby("old-town-lantern-boat-transport", of("Old Town Lantern Boat", "Old Town Lantern Boat"),
                                                of("Trung tâm giao thông", "Transport hub"), 1230.0),
                                nearby("hoi-an-boat-harbor", of("Hoi An Boat Harbor", "Hoi An Boat Harbor"), of("Trung tâm giao thông", "Transport hub"), 1560.0),

                                // Trung tâm giải trí
                                nearby("cho-dem-hoi-an", of("Chợ đêm Hội An", "Hoi An Night Market"), of("Trung tâm giải trí", "Entertainment hub"), 1430.0),
                                nearby("chua-cau-hoi-an-entertainment", of("Chùa Cầu Hội An", "Japanese Covered Bridge"), of("Trung tâm giải trí", "Entertainment hub"),
                                                1170.0),
                                nearby("ky-uc-hoi-an", of("Ký Ức Hội An", "Hoi An Memories Show"), of("Trung tâm giải trí", "Entertainment hub"), 2370.0),
                                nearby("dao-ky-uc-hoi-an", of("Đảo Ký ức Hội An", "Hoi An Memories Island"), of("Trung tâm giải trí", "Entertainment hub"), 2380.0),
                                nearby("quang-truong-song-hoai-entertainment", of("Quảng trường Sông Hoài", "Hoai River Square"),
                                                of("Trung tâm giải trí", "Entertainment hub"), 1090.0),

                                // Khác
                                nearby("quan-cong-mieu", of("Quan Công Miếu", "Quan Cong Temple"), of("Khác", "Other"), 1450.0),
                                nearby("om-factory-yoga-hoian", of("Om Factory Yoga Hoi An", "Om Factory Yoga Hoi An"), of("Khác", "Other"), 2530.0));

                // --------- General info ----------
                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Máy lạnh, Nhà hàng, Lễ tân 24h, Chỗ đậu xe, WiFi",
                                                "Air conditioning, Restaurant, 24-hour front desk, Parking, WiFi"))
                                .distanceToCityCenterKm(1.23)
                                .popularAreaSummary(of("Old Town Lantern Boat, Chợ đêm Hội An, Quan Công Miếu",
                                                "Old Town Lantern Boat, Hoi An Night Market, Quan Cong Temple"))
                                .totalRooms(10)
                                .totalFloors(3)
                                .otherHighlightFacilities(of("Thức uống chào mừng miễn phí, dịch vụ concierge, thu đổi ngoại tệ, check-in sớm.",
                                                "Free welcome drink, concierge service, currency exchange, early check-in."))
                                .interestingPlacesSummary(of("Chùa Chúc Thánh, Chùa Phước Lâm, Ancient House",
                                                "Chuc Thanh Pagoda, Phuoc Lam Pagoda, Ancient House"))
                                .build();

                // --------- Policy ----------
                HotelPolicy policy = HotelPolicy.builder()
                                .checkInFrom(LocalTime.of(14, 0))
                                .checkOutUntil(LocalTime.of(12, 0))
                                .items(List.of(
                                                PolicyItem.builder()
                                                                .section(PolicySection.CHECKIN_CHECKOUT)
                                                                .title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
                                                                .content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.",
                                                                                "Check-in: from 14:00. Check-out: before 12:00."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.REQUIRED_DOCUMENTS)
                                                                .title(of("Giấy tờ bắt buộc", "Required documents"))
                                                                .content(of("Khi nhận phòng, bạn cần cung cấp CMND/CCCD, hộ chiếu hoặc giấy tờ tùy thân hợp lệ. Các giấy tờ cần thiết có thể ở dạng bản mềm.",
                                                                                "At check-in, you must present a valid ID card, passport or other valid identity document. The required documents may be in digital form."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                                                                .title(of("Hướng dẫn nhận phòng chung", "General check-in guide"))
                                                                .content(of("""
                                                                                                    Bạn sẽ được chào đón bởi đội ngũ nhân viên 24/24. Họ sẽ hỗ trợ chi tiết thủ tục nhận phòng
                                                                                                    và cung cấp thông tin hữu ích về Hội An. Bạn có thể đặt tour, mua vé show thông qua lễ tân.
                                                                                                    Vui lòng liên hệ hotline +84766553232 hoặc email: bespokevillahoian@gmail.com trước khi đến nếu cần.
                                                                                                """, """
                                                                                                    You will be welcomed by our 24/7 staff. They will guide you through the check-in process in detail
                                                                                                    and provide helpful information about Hoi An. You can book tours and buy show tickets at the front desk.
                                                                                                    Please contact the hotline +84766553232 or email: bespokevillahoian@gmail.com before arrival if needed.
                                                                                                """))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.MIN_AGE)
                                                                .title(of("Chính sách về độ tuổi tối thiểu", "Minimum age policy"))
                                                                .content(of("Độ tuổi tối thiểu để nhận phòng là 18 tuổi. Khách nhỏ tuổi phải có người lớn đi cùng khi nhận phòng.",
                                                                                "The minimum age to check in is 18. Underage guests must be accompanied by an adult at check-in."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.EARLY_CHECKIN)
                                                                .title(of("Nhận phòng sớm", "Early check-in"))
                                                                .content(of("Bạn có thể yêu cầu nhận phòng sớm hơn giờ quy định tùy tình trạng phòng. Vui lòng liên hệ cơ sở lưu trú để xác nhận.",
                                                                                "You may request early check-in subject to room availability. Please contact the property to confirm."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.LATE_CHECKOUT)
                                                                .title(of("Trả phòng trễ", "Late check-out"))
                                                                .content(of("Bạn có thể yêu cầu trả phòng trễ và có thể áp dụng phụ phí. Vui lòng liên hệ cơ sở lưu trú khi có nhu cầu.",
                                                                                "You may request late check-out and a surcharge may apply. Please contact the property when needed."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.SMOKING)
                                                                .title(of("Hút thuốc", "Smoking"))
                                                                .content(of("Chỉ được phép hút thuốc trong khu vực được chỉ định. Phòng không hút thuốc được áp dụng phí nếu hút trong phòng.",
                                                                                "Smoking is only allowed in designated areas. A fee applies for smoking in non-smoking rooms."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.PETS)
                                                                .title(of("Thú cưng", "Pets"))
                                                                .content(of("Không được mang theo thú cưng.", "Pets are not allowed."))
                                                                .build(),
                                                PolicyItem.builder()
                                                                .section(PolicySection.AIRPORT_TRANSFER)
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Có dịch vụ đưa đón sân bay với phí khoảng VND 270.000/người. Vui lòng liên hệ trước để được sắp xếp.",
                                                                                "Airport transfer is available for around VND 270,000/person. Please contact in advance to arrange."))
                                                                .build()))
                                .build();

                // --------- Review stats ----------
                ReviewStats reviewStats = ReviewStats.builder()
                                .cleanlinessScore(8.8)
                                .roomAmenitiesScore(8.9)
                                .foodScore(8.5)
                                .locationScore(8.9)
                                .serviceScore(8.9)
                                .keywords(List.of(
                                                keyword("distance_to_center", of("Khoảng cách đến trung tâm", "Distance to center"), 11),
                                                keyword("friendly_staff", of("Nhân viên thân thiện", "Friendly staff"), 11),
                                                keyword("room_space", of("Không gian phòng", "Room space"), 10),
                                                keyword("access", of("Đường đi", "Access"), 10),
                                                keyword("surrounding_area", of("Khu vực xung quanh", "Surrounding area"), 8),
                                                keyword("family_friendly", of("Hợp cho gia đình", "Family-friendly"), 8),
                                                keyword("checkin_checkout", of("Giờ nhận/trả phòng", "Check-in/check-out time"), 8),
                                                keyword("bathroom", of("Phòng tắm", "Bathroom"), 7),
                                                keyword("wifi", of("WiFi", "WiFi"), 6)))
                                .build();

                // --------- Tax config & filter facets ----------
                TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
                                .defaultVatPercent(new BigDecimal("8"))
                                .defaultServiceFeePercent(new BigDecimal("7"))
                                .showPricePreTax(false)
                                .build();

                HotelFilterFacets filterFacets = HotelFilterFacets.builder()
                                .hasFreeCancellation(true) // có plan Family pay at hotel, refundable
                                .hasPayAtHotel(true)
                                .hasBreakfastIncluded(false) // chưa seed plan có bữa sáng, có thể thêm sau
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

                // --------- FAQs (lấy lại từ seed cũ) ----------
                List<FaqItem> faqs = List.of(
                                faq(
                                                of("Những tiện ích tại Bespoke Villa Hoi An?",
                                                                "What amenities are available at Bespoke Villa Hoi An?"),
                                                of("Những tiện ích tại Bespoke Villa Hoi An bao gồm Máy lạnh, Nhà hàng, Lễ tân 24h, Chỗ đậu xe, WiFi (một số dịch vụ sẽ yêu cầu trả thêm phí).",
                                                                "Amenities at Bespoke Villa Hoi An include air conditioning, restaurant, 24-hour front desk, parking and WiFi (some services may incur an additional fee).")),
                                faq(
                                                of("Bespoke Villa Hoi An có mức giá là bao nhiêu?",
                                                                "How much does Bespoke Villa Hoi An cost?"),
                                                of("Phòng tại Bespoke Villa Hoi An có giá từ khoảng 424.958 VND. Vui lòng xem trang khuyến mãi khách sạn để có thêm ưu đãi.",
                                                                "Rooms at Bespoke Villa Hoi An start from around VND 424,958. Please check the hotel promotion page for more deals.")),
                                faq(
                                                of("Thời gian nhận phòng và trả phòng của Bespoke Villa Hoi An?",
                                                                "What are the check-in and check-out times at Bespoke Villa Hoi An?"),
                                                of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.",
                                                                "Check-in: from 14:00. Check-out: before 12:00.")),
                                faq(
                                                of("Bespoke Villa Hoi An có phục vụ ăn sáng không?",
                                                                "Does Bespoke Villa Hoi An serve breakfast?"),
                                                of("Có, Bespoke Villa Hoi An có cung cấp bữa sáng. Tuy nhiên bạn cần chọn loại phòng bao gồm ăn sáng hoặc trả thêm phụ phí nếu đặt phòng không kèm bữa sáng.",
                                                                "Yes, Bespoke Villa Hoi An offers breakfast. However, you need to select a room type that includes breakfast, or pay an additional fee if your booking does not include breakfast.")),
                                faq(
                                                of("Khách sạn Bespoke Villa Hoi An đạt tiêu chuẩn mấy sao?",
                                                                "What star rating does Bespoke Villa Hoi An have?"),
                                                of("Bespoke Villa Hoi An là khách sạn đạt tiêu chuẩn 3.0 sao.",
                                                                "Bespoke Villa Hoi An is a 3.0-star hotel.")),
                                faq(
                                                of("Bespoke Villa Hoi An có bao nhiêu tầng lầu?",
                                                                "How many floors does Bespoke Villa Hoi An have?"),
                                                of("Bespoke Villa Hoi An có tổng cộng 3 tầng.",
                                                                "Bespoke Villa Hoi An has a total of 3 floors.")),
                                faq(
                                                of("Tổng số phòng hiện có tại Bespoke Villa Hoi An là bao nhiêu?",
                                                                "How many rooms does Bespoke Villa Hoi An have in total?"),
                                                of("Tổng số phòng tại Bespoke Villa Hoi An là 10 phòng.",
                                                                "Bespoke Villa Hoi An has a total of 10 rooms.")),
                                faq(
                                                of("Bespoke Villa Hoi An có chỗ đậu xe không?",
                                                                "Does Bespoke Villa Hoi An have parking?"),
                                                of("Có, Bespoke Villa Hoi An có chỗ đậu xe. Vui lòng liên hệ trực tiếp khách sạn để xác nhận thông tin chi tiết.",
                                                                "Yes, Bespoke Villa Hoi An has parking. Please contact the hotel directly to confirm the details.")),
                                faq(
                                                of("Bespoke Villa Hoi An có hỗ trợ nhận phòng sớm không?",
                                                                "Does Bespoke Villa Hoi An offer early check-in?"),
                                                of("Có, khách sạn hỗ trợ nhận phòng sớm tùy tình trạng phòng. Vui lòng liên hệ khách sạn để sắp xếp.",
                                                                "Yes, the hotel offers early check-in subject to room availability. Please contact the hotel to arrange.")),
                                faq(
                                                of("Bespoke Villa Hoi An có cung cấp dịch vụ trả phòng trễ không?",
                                                                "Does Bespoke Villa Hoi An offer late check-out?"),
                                                of("Có, khách sạn hỗ trợ trả phòng trễ tùy tình trạng phòng. Vui lòng liên hệ khách sạn để sắp xếp.",
                                                                "Yes, the hotel offers late check-out subject to room availability. Please contact the hotel to arrange.")),
                                faq(
                                                of("Bespoke Villa Hoi An có dịch vụ đưa đón sân bay không?",
                                                                "Does Bespoke Villa Hoi An offer airport transfer?"),
                                                of("Có, Bespoke Villa Hoi An cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ khách sạn để biết thêm chi tiết.",
                                                                "Yes, Bespoke Villa Hoi An offers airport transfer on request. Please contact the hotel for more details.")),
                                faq(
                                                of("Đánh giá của khách lưu trú trước đây về Bespoke Villa Hoi An như thế nào?",
                                                                "What do previous guests say about Bespoke Villa Hoi An?"),
                                                of("Bespoke Villa Hoi An được đánh giá cao về Tiện nghi phòng, Vị trí, Dịch vụ và Tiện ích theo nhận xét của khách lưu trú trên Traveloka.",
                                                                "Bespoke Villa Hoi An is highly rated for Room amenities, Location, Service and Facilities according to guest reviews on Traveloka.")),
                                faq(
                                                of("Những địa điểm nổi bật nào nằm gần khách sạn Bespoke Villa Hoi An?",
                                                                "What notable attractions are near Bespoke Villa Hoi An?"),
                                                of("Một số địa điểm gần khách sạn: Chùa Chúc Thánh, Chùa Phước Lâm, Ancient House, Đình Cẩm Phô, Quảng trường Sông Hoài.",
                                                                "Some places near the hotel: Chuc Thanh Pagoda, Phuoc Lam Pagoda, Ancient House, Cam Pho Communal House, Hoai River Square.")),
                                faq(
                                                of("Những loại phòng đang khả dụng tại Bespoke Villa Hoi An?",
                                                                "What room types are available at Bespoke Villa Hoi An?"),
                                                of("Những loại phòng đang khả dụng: Deluxe Twin, Family.",
                                                                "Available room types: Deluxe Twin, Family.")),
                                faq(
                                                of("Phòng tại Bespoke Villa Hoi An được trang bị những tiện nghi gì?",
                                                                "What amenities are the rooms at Bespoke Villa Hoi An equipped with?"),
                                                of("Hầu hết các phòng đều có vòi tắm đứng, khu vực chờ, nước nóng, máy lạnh, ban công/sân hiên cùng nhiều tiện nghi khác.",
                                                                "Most rooms have a shower, seating area, hot water, air conditioning, balcony/terrace and many other amenities.")),
                                faq(
                                                of("Mỗi phòng tại Bespoke Villa Hoi An có thể lưu trú tối đa bao nhiêu khách?",
                                                                "How many guests can each room at Bespoke Villa Hoi An accommodate?"),
                                                of("Tối đa 4 khách/phòng, tùy từng hạng phòng. Sức chứa cụ thể được ghi rõ khi đặt phòng.",
                                                                "Up to 4 guests per room, depending on the room type. The specific capacity is shown when booking.")));

                // --------- Build hotel doc cuối cùng ----------
                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("hoi-an")
                                .cityName(of("Hội An", "Hoi An"))
                                .districtName(of("Tân An", "Tan An"))
                                .wardName(null)
                                .addressLine(of("153 Lê Hồng Phong, Tân An, Hội An, Quảng Nam, Việt Nam",
                                                "153 Le Hong Phong, Tan An, Hoi An, Quang Nam, Vietnam"))
                                .location(new double[] { 108.3205, 15.8765 })
                                .name(of("Bespoke Villa Hoi An", "Bespoke Villa Hoi An"))
                                .slug(slug)
                                .hotelType(HotelType.VILLA)
                                .starRating(3)
                                .shortDescription(of("Biệt thự 3 sao yên bình tại Tân An, gần khu vui chơi giải trí và phố cổ Hội An.",
                                                "Peaceful 3-star villa in Tan An, close to entertainment areas and Hoi An Ancient Town."))
                                .description(of("Bespoke Villa Hoi An là một điểm đến lý tưởng cho những ai đang tìm kiếm sự yên bình và sang trọng tại thành phố cổ Hội An. Biệt thự 3 tầng hiện đại, nội thất tinh tế, sân vườn thoáng đãng cùng dịch vụ thân thiện giúp du khách có kỳ nghỉ thư giãn và đáng nhớ.",
                                                "Bespoke Villa Hoi An is an ideal destination for those seeking peace and luxury in the ancient town of Hoi An. The modern 3-story villa, elegant interiors, airy garden and friendly service give guests a relaxing and memorable stay."))
                                .phone("+84766553232")
                                .email("bespokevillahoian@gmail.com")
                                .website(null)
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(true)
                                .avgRating(8.9)
                                .reviewsCount(102)
                                .ratingLabel(of("Rất tốt", "Very good"))
                                .minNightlyPrice(new BigDecimal("379932")) // min giá/đêm (bao gồm thuế/phí)
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("450000"))
                                .discountPercent(15)
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
                                .roomTypes(List.of(deluxeTwin, family))
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