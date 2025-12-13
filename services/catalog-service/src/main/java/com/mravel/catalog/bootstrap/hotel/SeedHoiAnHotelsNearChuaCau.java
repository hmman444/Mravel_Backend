// src/main/java/com/mravel/catalog/bootstrap/hotel/SeedHoiAnHotelsNearChuaCau.java
package com.mravel.catalog.bootstrap.hotel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.HotelDoc.Amenity;
import com.mravel.catalog.model.doc.HotelDoc.AmenityGroup;
import com.mravel.catalog.model.doc.HotelDoc.AmenitySection;
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
    //                          1) BESPOKE VILLA HOI AN
    // =====================================================================

    private static HotelDoc hotelBespokeVillaHoiAn() {
        String slug = "bespoke-villa-hoi-an";

        // --------- Ảnh chính ----------
        List<Image> images = List.of(
            img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-a0dcfa844ef3fa9dca7471bb2a425c06.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-332,pr-true,q-80,w-480", "Phòng giường King sáng sủa với cửa kính lớn", true, 0),
            img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-3a3abd8330cf1e249dea561d8afab0a3.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666", "Khu vườn và sân hiên xanh mát", false, 1),
            img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-01c14097304a34bfe357d690e62625ba.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666", "Mặt tiền Bespoke Villa Hoi An", false, 2),
            img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-29097353e5227135e44e14f76bb9b765.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666", "Phòng Family rộng rãi cho gia đình", false, 3),
            img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9205b8c583ff7e638002fc244035b6cb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,fo-auto,h-162,pr-true,q-80,w-234.66666666666666", "Phòng Family rộng rãi cho gia đình", false, 4),
            img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-1d698d9a708e69b7e1d4bec1de436d42.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Phương tiện di chuyển có sẵn", false, 5),
            img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9ed917f9c71a6ba21d2f9e708c3ce9fb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Góc chill ngoài trời", false, 6),
            img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-27e485ffe9e0f97b558e9c84e9cf32da.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Góc nhìn từ trên cao", false, 7),
            img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-e6f552f3e3cd0e7c5192b3514ce58f69.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Villa nhìn từ phía trước", false, 8),
            img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-1f31fd16c1b22521286f593103dc359a.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Khoảng trống rộng cho các cuộc tụ tập", false, 9),
            img("https://ik.imagekit.io/tvlk/generic-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20050293-036b9553a89058fe1280ed9f521ae373.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-360,pr-true,q-40,w-640", "Không gian ban công buổi tối thơ mộng", false, 10)
        );

        // --------- Content (Overview + Story) ----------
        List<ContentBlock> content = List.of(
            // ===== OVERVIEW =====
            paragraph(ContentSection.OVERVIEW, "Bespoke Villa Hoi An là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại phường Tân An. Đây là lựa chọn phù hợp cho du khách muốn tìm một nơi lưu trú yên bình nhưng vẫn thuận tiện để di chuyển vào trung tâm phố cổ Hội An."),
            heading(ContentSection.OVERVIEW, "Vị trí"),
            paragraph(ContentSection.OVERVIEW, "Khi lưu trú tại nơi nghỉ thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Bespoke Villa Hoi An mang đến không gian lưu trú làm hài lòng quý khách, vừa hiện đại vừa gần gũi với không khí cổ kính của phố Hội."),
            paragraph(ContentSection.OVERVIEW, "Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Bespoke Villa Hoi An sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ thân thiện."),
            paragraph(ContentSection.OVERVIEW, "Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Bespoke Villa Hoi An, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân."),
            paragraph(ContentSection.OVERVIEW, "Du lịch một mình cũng không hề kém phần thú vị và Bespoke Villa Hoi An là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú."),
            paragraph(ContentSection.OVERVIEW, "Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi nơi nghỉ cung cấp sẽ khiến quý khách khó có thể phàn nàn trong suốt kỳ lưu trú. Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào."),
            paragraph(ContentSection.OVERVIEW, "Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Bespoke Villa Hoi An chỉ dành riêng cho quý khách. Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn."),
            paragraph(ContentSection.OVERVIEW, "Bespoke Villa Hoi An là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú. Với những tiện nghi sẵn có, nơi đây thực sự là một điểm dừng chân lý tưởng cho hành trình khám phá Hội An."),
            mapBlock(ContentSection.OVERVIEW, new double[]{108.3205, 15.8765}),

            // ===== STORY =====

            // 1. Giới thiệu & lịch sử hình thành
            heading(ContentSection.STORY, "Lịch sử hình thành Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Biệt thự Bespoke Villa Hoi An là một điểm đến lý tưởng cho những ai đang tìm kiếm sự yên bình và sang trọng tại thành phố cổ Hội An. Tọa lạc tại một vị trí đắc địa, biệt thự, khách sạn Hội An này mang đến cho du khách không gian nghỉ dưỡng hoàn hảo giữa thiên nhiên tươi đẹp và kiến trúc tinh tế, với thiết kế hiện đại kết hợp hài hòa cùng nét truyền thống."),
            paragraph(ContentSection.STORY, "Biệt thự được xây dựng vào năm 2021 và được nâng cấp toàn diện vào năm 2024, trang bị đầy đủ tiện nghi cao cấp từ khu vực sân vườn thoáng đãng đến các phòng nghỉ rộng rãi với nội thất thanh lịch. Mỗi góc nhỏ trong biệt thự đều được chăm chút tỉ mỉ, tạo cảm giác ấm cúng và thoải mái như ở nhà, cùng đội ngũ nhân viên nhiệt tình và chuyên nghiệp."),
            paragraph(ContentSection.STORY, "Từ Bespoke Villa Hoi An, du khách có thể dễ dàng di chuyển đến các điểm tham quan nổi tiếng như Phố cổ Hội An, chợ đêm Hội An, Bảo tàng Lịch sử và Nghệ thuật,... Đây là lựa chọn hoàn hảo cho kỳ nghỉ dưỡng của gia đình hoặc nhóm bạn bè, nơi bạn có thể tận hưởng những khoảnh khắc thư giãn và đáng nhớ, trốn khỏi sự ồn ào của đô thị và hòa mình vào không gian yên bình của Hội An."),

            // 2. Kiến trúc & phong cách
            heading(ContentSection.STORY, "Đặc trưng kiến trúc và phong cách Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Hình ảnh Bespoke Villa Hoi An hiện lên là một biệt thự 3 tầng hiện đại, là sự kết hợp giữa phong cách hiện đại và nét truyền thống địa phương. Với thiết kế thanh lịch, sử dụng các vật liệu tự nhiên như gỗ và đá, biệt thự tạo nên không gian ấm cúng và gần gũi, cùng tông màu trung tính như trắng, be và nâu mang lại cảm giác thư giãn cho du khách."),
            paragraph(ContentSection.STORY, "Không gian nội thất được thiết kế tinh tế với các phòng nghỉ rộng rãi, trang bị đầy đủ tiện nghi hiện đại. Mỗi phòng đều có cửa sổ lớn hoặc ban công, cho phép ánh sáng tự nhiên tràn ngập và cung cấp tầm nhìn đẹp ra khu vườn hoặc cảnh quan xung quanh. Khu vực sân vườn và sân hiên được chăm sóc kỹ lưỡng, tạo không gian xanh mát và yên bình, lý tưởng cho việc thư giãn hoặc tổ chức các hoạt động ngoài trời."),

            // 3. Điểm nổi bật
            heading(ContentSection.STORY, "Điểm nổi bật của Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Vị trí đắc địa: Tọa lạc gần trung tâm phố cổ Hội An, Bespoke Villa Hoi An không chỉ sở hữu vị trí thuận lợi mà còn là điểm dừng chân lý tưởng cho những ai muốn khám phá nét văn hóa và lịch sử độc đáo của vùng đất này. Chỉ cần vài phút di chuyển, bạn có thể ghé thăm Chùa Cầu Hội An, Bảo tàng Lịch sử và Nghệ thuật, Đình Cẩm Phô hay Chùa Chúc Thánh."),
            paragraph(ContentSection.STORY, "Tiện nghi đẳng cấp: Khi đến với Bespoke Villa Hoi An, sự tận tâm trong từng chi tiết dịch vụ sẽ khiến bạn cảm thấy hài lòng. Không chỉ sở hữu những phòng nghỉ sang trọng, đầy đủ tiện nghi hiện đại, Bespoke Villa Hoi An còn mang đến một loạt dịch vụ đa dạng và không gian sân vườn tươi mát, là nơi lưu giữ những kỷ niệm đáng nhớ."),
            paragraph(ContentSection.STORY, "Giá cả phải chăng: Bespoke Villa Hoi An là một lựa chọn tuyệt vời cho những ai tìm kiếm kỳ nghỉ thoải mái mà vẫn tiết kiệm chi phí. Nằm trên con đường Lê Hồng Phong nhộn nhịp, khách sạn vừa thuận tiện di chuyển đến các điểm du lịch, vừa giúp giảm chi phí đi lại. Với dịch vụ tận tâm và giá cả hợp lý, nơi đây mang lại trải nghiệm thư thái như ở nhà."),

            // 4. Các hạng phòng
            heading(ContentSection.STORY, "Các hạng phòng của Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Thông tin hạng phòng được cung cấp là chính xác tại thời điểm viết, vui lòng tham khảo Traveloka để biết giá phòng mới nhất."),
            paragraph(ContentSection.STORY, "Bespoke Villa Hoi An sở hữu tổng cộng 10 phòng đạt chuẩn 3.0 sao với giá phòng hợp lý, dao động khoảng từ 380.000 VND đến 617.000 VND/đêm trên Traveloka, gồm hai hạng phòng chính: Deluxe Double và Family."),

            heading(ContentSection.STORY, "Deluxe Double"),
            paragraph(ContentSection.STORY, "Diện tích: 30 m², sức chứa: 2 người lớn, giường ngủ: 1 giường cỡ King, tầm nhìn: thành phố, phù hợp với khách lẻ, cặp đôi hoặc gia đình nhỏ, giá phòng chỉ từ khoảng 380.000 VND/phòng/đêm."),
            paragraph(ContentSection.STORY, "Phòng Deluxe Double tại Bespoke Villa Hoi An là sự kết hợp hoàn hảo giữa nét sang trọng hiện đại và tinh thần hoài cổ của phố Hội. Với giường cỡ King rộng rãi, không gian màu sắc trung tính như trắng, be và nâu gỗ, căn phòng mang đến cảm giác thư thái, ấm cúng và thanh lịch cho du khách."),
            paragraph(ContentSection.STORY, "Trần nhà cao và cửa sổ lớn giúp căn phòng luôn thoáng đãng, đón ánh sáng tự nhiên và mở ra khung cảnh thành phố Hội An hiền hòa. Không gian được bày trí tối giản nhưng tinh tế với giường êm ái, đèn ngủ dịu nhẹ và bàn làm việc nhỏ gọn, mang lại sự tiện nghi tối đa và bầu không khí nhẹ nhàng, đầy cảm hứng."),

            heading(ContentSection.STORY, "Family"),
            paragraph(ContentSection.STORY, "Diện tích: 40 m², sức chứa: 4 người lớn, giường ngủ: 1 giường cỡ King và 2 giường đơn, tầm nhìn: hướng thành phố, phù hợp cho gia đình hoặc nhóm bạn, giá phòng chỉ từ khoảng 617.000 VND/phòng/đêm."),
            paragraph(ContentSection.STORY, "Phòng Family tại Bespoke Villa Hoi An là sự kết hợp giữa không gian rộng rãi, tiện nghi và cảm giác ấm cúng, mang đến nơi nghỉ lý tưởng cho gia đình hoặc nhóm bạn muốn tận hưởng kỳ nghỉ bên nhau. Bố cục giường King và 2 giường đơn được sắp xếp khoa học, vừa đảm bảo riêng tư vừa tạo sự gắn kết."),
            paragraph(ContentSection.STORY, "Tông màu nhẹ nhàng, thanh lịch cùng những điểm nhấn gỗ trầm ấm giúp căn phòng trở nên gần gũi và thư giãn. Cửa kính lớn mở ra khung cảnh thành phố yên bình, ánh sáng tự nhiên tràn ngập không gian. Bàn làm việc gọn gàng, ghế thư giãn cạnh cửa sổ và hệ thống đèn ngủ dịu nhẹ mang lại cảm giác dễ chịu, giúp bạn tận hưởng trọn vẹn từng khoảnh khắc nghỉ dưỡng."),

            // 5. Tiện ích & dịch vụ
            heading(ContentSection.STORY, "Tiện ích và dịch vụ tại Bespoke Villa Hoi An"),
            heading(ContentSection.STORY, "Nhà hàng"),
            paragraph(ContentSection.STORY, "Bespoke Villa Hoi An là một biệt thự, khách sạn Hội An có phục vụ bữa sáng được rất nhiều du khách yêu thích. Nhà hàng mang phong cách kết hợp giữa nét truyền thống đặc trưng của Hội An và sự hiện đại tinh tế, không gian mở, thoáng đãng, vừa là nơi thưởng thức món ăn ngon vừa là nơi thư giãn, trò chuyện và lưu giữ những khoảnh khắc đáng nhớ bên gia đình, bạn bè."),

            // 6. Vị trí & cách di chuyển
            heading(ContentSection.STORY, "Vị trí và cách di chuyển đến Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Vị trí: 153 Lê Hồng Phong, Hội An, Tỉnh Quảng Nam, Việt Nam, mã vùng 560000."),
            heading(ContentSection.STORY, "Di chuyển bằng máy bay"),
            paragraph(ContentSection.STORY, "Bạn có thể đặt vé máy bay đến sân bay quốc tế Đà Nẵng, sau đó di chuyển về Hội An bằng taxi, xe máy hoặc dịch vụ đưa đón sân bay. Đây là cách di chuyển nhanh và thuận tiện nhất để đến Bespoke Villa Hoi An."),
            heading(ContentSection.STORY, "Di chuyển bằng xe khách"),
            paragraph(ContentSection.STORY, "Nếu muốn tiết kiệm chi phí, bạn có thể đi xe khách đến Đà Nẵng, sau đó bắt xe tuyến Đà Nẵng – Hội An hoặc các phương tiện khác để đến trung tâm Hội An và tiếp tục di chuyển đến Bespoke Villa Hoi An."),
            heading(ContentSection.STORY, "Di chuyển bằng phương tiện cá nhân"),
            paragraph(ContentSection.STORY, "Hội An cách thành phố Hồ Chí Minh hơn 766 km, phù hợp với những ai đam mê khám phá bằng phương tiện cá nhân. Bạn có thể chủ động dừng chân, chiêm ngưỡng cảnh đẹp dọc đường đi, nhưng hãy chuẩn bị kỹ lưỡng cung đường, thời gian nghỉ ngơi và đảm bảo an toàn trong suốt hành trình."),

            // 7. Các địa điểm vui chơi gần
            heading(ContentSection.STORY, "Các địa điểm vui chơi gần Bespoke Villa Hoi An"),
            paragraph(ContentSection.STORY, "Hội An là một trong những điểm đến hấp dẫn nhất Việt Nam với vẻ đẹp cổ kính cùng giá trị văn hóa, lịch sử độc đáo. Từ Bespoke Villa Hoi An, bạn có thể dễ dàng khám phá những show diễn, tour tham quan và khu vui chơi nổi bật dành cho mọi lứa tuổi."),

            heading(ContentSection.STORY, "Show Ký Ức Hội An"),
            paragraph(ContentSection.STORY, "Giá vé tham khảo từ khoảng 180.000 VND/khách. Đây là chương trình biểu diễn nghệ thuật đặc sắc kết hợp âm nhạc, múa và ánh sáng, tái hiện sống động lịch sử và văn hóa Hội An qua từng phân đoạn sân khấu công phu, mang lại trải nghiệm rất ấn tượng cho du khách."),

            heading(ContentSection.STORY, "Tour Đà Nẵng – Hội An 1 ngày"),
            paragraph(ContentSection.STORY, "Giá vé tham khảo từ khoảng 1.337.349 VND/khách. Tour thường kết hợp tham quan Ngũ Hành Sơn, Bãi biển Mỹ Khê và Phố cổ Hội An, giúp bạn vừa khám phá thiên nhiên, vừa tìm hiểu thêm về lịch sử, văn hóa và ẩm thực đặc trưng của miền Trung."),

            heading(ContentSection.STORY, "VinWonders Nam Hội An"),
            paragraph(ContentSection.STORY, "Giá vé tham khảo từ khoảng 270.000 VND/khách. Đây là công viên chủ đề kết hợp giữa giải trí hiện đại và không gian tái hiện văn hóa truyền thống, với nhiều khu vui chơi, trò mạo hiểm, khu check-in, khu dành cho gia đình và trẻ em, phù hợp cho cả nhóm bạn và gia đình có trẻ nhỏ."),

            // 8. Hướng dẫn đặt phòng
            heading(ContentSection.STORY, "Hướng dẫn đặt phòng Bespoke Villa Hoi An"),
            heading(ContentSection.STORY, "Vì sao bạn nên đặt phòng cùng Traveloka?"),
            paragraph(ContentSection.STORY, "Traveloka là nền tảng du lịch hàng đầu Đông Nam Á, cung cấp đầy đủ dịch vụ từ vé máy bay, khách sạn đến vé vui chơi, với hơn 1 triệu lựa chọn khách sạn trên toàn cầu. Bạn có thể dễ dàng so sánh giá, đặt phòng nhanh chóng, quản lý giao dịch linh hoạt, thanh toán an toàn với nhiều hình thức và được hỗ trợ bởi đội ngũ chăm sóc khách hàng chuyên nghiệp."),
            paragraph(ContentSection.STORY, "Để đặt phòng Bespoke Villa Hoi An trên Traveloka, bạn chỉ cần: bước 1, truy cập website hoặc ứng dụng Traveloka; bước 2, tại thanh tìm kiếm, nhập Bespoke Villa Hoi An, chọn thời gian nhận/trả phòng và số lượng phòng, sau đó chọn hạng phòng phù hợp và tiến hành thanh toán; bước 3, sau khi hoàn tất đặt phòng, bạn sẽ nhận được xác nhận qua ứng dụng và email trong khoảng vài phút."),

            // 9. Review từ khách hàng
            heading(ContentSection.STORY, "Review Bespoke Villa Hoi An từ khách hàng"),
            paragraph(ContentSection.STORY, "Khách lưu trú tại Bespoke Villa Hoi An thường để lại đánh giá rất tích cực, phản ánh mức độ hài lòng cao với chất lượng phòng ở, dịch vụ và sự thân thiện của nhân viên."),
            paragraph(ContentSection.STORY, "Nhận xét khách hàng 1 (Bùi, 10/10): Khách sạn đẹp, nhân viên tốt, rất hài lòng với trải nghiệm nghỉ dưỡng tại đây."),
            paragraph(ContentSection.STORY, "Nhận xét khách hàng 2 (Angelica P. R., 9.4/10): Nhân viên cực kỳ thân thiện, luôn hỗ trợ giải đáp thắc mắc về Hội An, tạo cảm giác gần gũi và thoải mái, là nơi mà khách muốn quay lại và giới thiệu cho bạn bè."),
            paragraph(ContentSection.STORY, "Nhận xét khách hàng 3 (Nguyễn N. A., 8/10): Chủ nhà thân thiện, phòng và phòng tắm sạch sẽ, tổng thể trải nghiệm dễ chịu."),
            paragraph(ContentSection.STORY, "Bespoke Villa Hoi An là điểm dừng chân phù hợp cho những du khách yêu thích sự tiện nghi, sang trọng nhưng vẫn gần gũi và thư thái khi ghé thăm thành phố Hội An. Những trải nghiệm tuyệt vời tại đây đang chờ bạn khám phá.")
        );

        // --------- Tiện ích cấp khách sạn (Hotel amenities) ----------
        List<Amenity> hotelAmenities = List.of(
            // Tiện nghi chung
            amenity("ac", "Máy lạnh", AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
            amenity("restaurant", "Nhà hàng", AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
            amenity("front_desk_24h", "Lễ tân 24h", AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
            amenity("parking", "Chỗ đậu xe", AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, true),
            amenity("wifi_free", "WiFi (miễn phí)", AmenityGroup.INTERNET, AmenitySection.INTERNET, true),

            // Dịch vụ khách sạn
            amenity("welcome_drink", "Thức uống chào mừng miễn phí", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("concierge", "Dịch vụ concierge/hỗ trợ khách", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("currency_exchange", "Dịch vụ thu đổi ngoại tệ", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("doorman", "Người gác cửa", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("early_checkin", "Nhận phòng sớm", AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
            amenity("express_checkin", "Dịch vụ nhận phòng cấp tốc", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("express_checkout", "Dịch vụ trả phòng cấp tốc", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("late_checkout", "Trả phòng trễ", AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
            amenity("laundry", "Dịch vụ giặt ủi", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("room_service_limited", "Dịch vụ phòng (có giới hạn thời gian)", AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
            amenity("luggage_storage", "Dịch vụ lưu trữ/bảo quản hành lý", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("multilingual_staff", "Đội ngũ nhân viên đa ngôn ngữ", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
            amenity("tour_desk", "Dịch vụ hỗ trợ đặt tour", AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),

            // Tiện ích lân cận
            amenity("atm", "Máy ATM/Ngân hàng", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("beauty_salon", "Thẩm mỹ viện", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("gift_shop", "Cửa hàng quà tặng", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("grocery", "Cửa hàng thực phẩm", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("hair_salon", "Hiệu làm tóc", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("nearby_laundry", "Giặt ủi (gần đó)", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("shop", "Cửa hàng", AmenityGroup.NEARBY, AmenitySection.OTHER, false),
            amenity("supermarket", "Siêu thị", AmenityGroup.NEARBY, AmenitySection.OTHER, false),

            // Tiện nghi công cộng
            amenity("public_parking", "Bãi đậu xe", AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
            amenity("lobby_coffee_tea", "Cà phê/trà tại sảnh", AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
            amenity("public_restaurant", "Nhà hàng (khu vực chung)", AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
            amenity("public_wifi", "WiFi tại khu vực chung", AmenityGroup.INTERNET, AmenitySection.INTERNET, false),

            // Tiện nghi chung khác
            amenity("family_room", "Phòng gia đình", AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, false),
            amenity("non_smoking_room", "Phòng không hút thuốc", AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, false),
            amenity("smoking_area", "Khu vực hút thuốc", AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
            amenity("terrace", "Sân thượng/sân hiên", AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),

            // Vận chuyển / đưa đón
            amenity("airport_transfer", "Đưa đón sân bay (thu phí)", AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
            amenity("bicycle_rental", "Dịch vụ cho thuê xe đạp", AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
            amenity("garage", "Nhà chứa xe", AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
            amenity("secure_parking", "Bãi đậu xe an ninh", AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),

            // Ẩm thực
            amenity("breakfast", "Bữa sáng", AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),

            // Hoạt động / giải trí
            amenity("garden", "Vườn hoa", AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, false),
            amenity("dart", "Phi tiêu", AmenityGroup.PUBLIC_AREA, AmenitySection.ENTERTAINMENT, false)
        );

        // --------- Room types & rate plans ----------
        // Deluxe Twin
        RoomType deluxeTwin = RoomType.builder()
            .id("rt-bespoke-deluxe-twin")
            .name("Deluxe Twin")
            .shortDescription("Phòng Deluxe Twin 30 m², 2 giường đơn, phù hợp 2 khách.")
            .description("""
                Phòng Deluxe Twin mang đến không gian sáng sủa với 2 giường đơn, phù hợp cho bạn bè hoặc đồng nghiệp
                đi cùng. Tông màu trung tính, nội thất tối giản nhưng tiện nghi đầy đủ giúp kỳ nghỉ của bạn thoải mái.
            """)
            .areaSqm(30.0)
            .bedType(BedType.TWIN)
            .bedsCount(2)
            .bedLayoutDescription("2 giường đơn (Twin)")
            .bedOptions(List.of(
                BedOption.builder().type(BedType.TWIN).count(2).build()
            ))
            .maxAdults(2)
            .maxChildren(0)
            .maxGuests(2)
            .totalRooms(30)
            .images(List.of(
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-40b386dd4aa1a67d9ab61b08f2138609.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Phòng Deluxe Twin với 2 giường đơn", true, 0),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-6b87c5b998f53106be04311d5d0d91ca.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Không gian rộng rãi", false, 1),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-62d9a62c2d1e0a499b6f1fb8e7e4c8c4.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Không gian thoáng đãng", false, 2),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-2ef2ea5c29bf2c6afdd4ebc6f543b2cc.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Nhà vệ sinh sạch sẽ", false, 3)
            ))
            .amenities(List.of(
                roomAmenity("ac", "Máy lạnh", AmenitySection.HIGHLIGHT_FEATURES),
                roomAmenity("hot_water", "Nước nóng", AmenitySection.HIGHLIGHT_FEATURES),
                roomAmenity("shower", "Vòi tắm đứng", AmenitySection.BATHROOM),
                roomAmenity("seating_area", "Khu vực chờ", AmenitySection.BASIC_FACILITIES),
                roomAmenity("non_smoking", "Phòng cấm hút thuốc", AmenitySection.BASIC_FACILITIES),
                roomAmenity("tv", "TV màn hình phẳng", AmenitySection.ROOM_FACILITIES),
                roomAmenity("desk", "Bàn làm việc", AmenitySection.ROOM_FACILITIES),
                roomAmenity("blackout_curtains", "Rèm cản sáng", AmenitySection.ROOM_FACILITIES),
                roomAmenity("bottled_water", "Nước đóng chai miễn phí", AmenitySection.ROOM_FACILITIES),
                roomAmenity("hair_dryer", "Máy sấy tóc", AmenitySection.BATHROOM),
                roomAmenity("bath_amenities", "Bộ vệ sinh cá nhân", AmenitySection.BATHROOM),
                roomAmenity("bathrobe", "Áo choàng tắm", AmenitySection.BATHROOM)
            ))
            .ratePlans(List.of(
                // Plan 1: Deluxe Twin Room - Room Only, không hoàn tiền
                RatePlan.builder()
                    .id("rp-bespoke-deluxe-twin-room-only-nonref-1")
                    .name("Deluxe Twin Room - Room Only")
                    .boardType(BoardType.ROOM_ONLY)
                    .paymentType(PaymentType.PREPAID)
                    .refundable(false)
                    .cancellationPolicy("Không được hoàn tiền.")
                    // Giá đã bao gồm thuế và phí
                    .pricePerNight(new BigDecimal("379932"))      // giảm còn (bao gồm thuế/phí)
                    .referencePricePerNight(new BigDecimal("434308")) // giá gốc (bao gồm thuế/phí)
                    .discountPercent(13) // xấp xỉ
                    .taxPercent(new BigDecimal("8"))
                    .serviceFeePercent(new BigDecimal("7"))
                    .priceIncludesTax(true)
                    .priceIncludesServiceFee(true)
                    .lengthOfStayDiscounts(List.of())
                    .promoLabel("Giá tốt cho phòng đôi")
                    .showLowAvailability(true)
                    .build(),
                // Plan 2: Deluxe Twin Room - Room Only, giá cao hơn
                RatePlan.builder()
                    .id("rp-bespoke-deluxe-twin-room-only-nonref-2")
                    .name("Deluxe Twin Room - Room Only")
                    .boardType(BoardType.BREAKFAST_INCLUDED)
                    .paymentType(PaymentType.PAY_AT_HOTEL)
                    .refundable(false)
                    .cancellationPolicy("Không được hoàn tiền.")
                    .pricePerNight(new BigDecimal("434308"))      // giảm còn (bao gồm thuế/phí)
                    .referencePricePerNight(new BigDecimal("579077")) // giá gốc (bao gồm thuế/phí)
                    .discountPercent(25) // xấp xỉ
                    .taxPercent(new BigDecimal("8"))
                    .serviceFeePercent(new BigDecimal("7"))
                    .priceIncludesTax(true)
                    .priceIncludesServiceFee(true)
                    .lengthOfStayDiscounts(List.of())
                    .promoLabel("Bửa sáng ngon miệng cho 2 người")
                    .showLowAvailability(true)
                    .build()
            ))
            .build();

        // Family
        RoomType family = RoomType.builder()
            .id("rt-bespoke-family")
            .name("Family")
            .shortDescription("Phòng Family 40 m², 1 giường King + 2 giường đơn, tối đa 4 khách.")
            .description("""
                Phòng Family rộng rãi, thích hợp cho gia đình hoặc nhóm bạn. Một giường King và hai giường đơn 
                được bố trí khoa học, đảm bảo vừa riêng tư vừa gắn kết. Cửa kính lớn, ban công/sân hiên và ánh sáng 
                tự nhiên tràn ngập giúp không gian luôn thoáng đãng và ấm cúng.
            """)
            .areaSqm(40.0)
            .bedType(BedType.MULTIPLE)
            .bedsCount(3)
            .bedLayoutDescription("1 giường king và 2 giường đơn")
            .bedOptions(List.of(
                BedOption.builder().type(BedType.KING).count(1).build(),
                BedOption.builder().type(BedType.SINGLE).count(2).build()
            ))
            .maxAdults(4)
            .maxChildren(0)
            .maxGuests(4)
            .totalRooms(25)
            .images(List.of(
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-a0dcfa844ef3fa9dca7471bb2a425c06.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Phòng Family với giường King và giường đơn", true, 0),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-baa1ba82fcc6360f9cf43d7b89d8ca4f.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Không gian phòng Family hướng thành phố", false, 1),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-29097353e5227135e44e14f76bb9b765.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Không gian phòng Family", false, 2),
                img("https://ik.imagekit.io/tvlk/generic-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/67788415-9205b8c583ff7e638002fc244035b6cb.jpeg?_src=imagekit&tr=dpr-2,c-at_max,f-jpg,h-460,pr-true,q-40,w-724", "Không gian phòng Family", false, 3)
            ))
            .amenities(List.of(
                roomAmenity("ac", "Máy lạnh", AmenitySection.HIGHLIGHT_FEATURES),
                roomAmenity("balcony", "Ban công / sân hiên", AmenitySection.HIGHLIGHT_FEATURES),
                roomAmenity("hot_water", "Nước nóng", AmenitySection.HIGHLIGHT_FEATURES),
                roomAmenity("seating_area", "Khu vực chờ", AmenitySection.BASIC_FACILITIES),
                roomAmenity("non_smoking", "Phòng cấm hút thuốc", AmenitySection.BASIC_FACILITIES),
                roomAmenity("coffee_tea_maker", "Máy pha cà phê / trà", AmenitySection.ROOM_FACILITIES),
                roomAmenity("tv", "TV màn hình phẳng", AmenitySection.ROOM_FACILITIES),
                roomAmenity("desk", "Bàn làm việc", AmenitySection.ROOM_FACILITIES),
                roomAmenity("blackout_curtains", "Rèm cản sáng", AmenitySection.ROOM_FACILITIES),
                roomAmenity("bottled_water", "Nước đóng chai miễn phí", AmenitySection.ROOM_FACILITIES),
                roomAmenity("hair_dryer", "Máy sấy tóc", AmenitySection.BATHROOM),
                roomAmenity("bath_amenities", "Bộ vệ sinh cá nhân", AmenitySection.BATHROOM),
                roomAmenity("bathrobe", "Áo choàng tắm", AmenitySection.BATHROOM)
            ))
            .ratePlans(List.of(
                // Plan 1: Family Room - Room Only, không hoàn tiền
                RatePlan.builder()
                    .id("rp-bespoke-family-room-only-nonref")
                    .name("Family Room - Room Only")
                    .boardType(BoardType.ROOM_ONLY)
                    .paymentType(PaymentType.PREPAID)
                    .refundable(false)
                    .cancellationPolicy("Không được hoàn tiền.")
                    .pricePerNight(new BigDecimal("635947"))          // giảm còn (bao gồm thuế/phí)
                    .referencePricePerNight(new BigDecimal("726963")) // giá gốc (bao gồm thuế/phí)
                    .discountPercent(13)
                    .taxPercent(new BigDecimal("8"))
                    .serviceFeePercent(new BigDecimal("7"))
                    .priceIncludesTax(true)
                    .priceIncludesServiceFee(true)
                    .lengthOfStayDiscounts(List.of())
                    .promoLabel("Giá tốt cho phòng Family")
                    .showLowAvailability(true) // còn 2 phòng
                    .build(),
                // Plan 2: Family Room - Room Only, thanh toán tại KS, có hủy theo chính sách
                RatePlan.builder()
                    .id("rp-bespoke-family-room-only-pay-hotel")
                    .name("Family Room - Room Only")
                    .boardType(BoardType.BREAKFAST_INCLUDED)
                    .paymentType(PaymentType.PAY_AT_HOTEL)
                    .refundable(true)
                    .cancellationPolicy("Áp dụng chính sách hủy phòng, vui lòng xem chi tiết khi đặt.")
                    .pricePerNight(new BigDecimal("726963"))          // giảm còn (bao gồm thuế/phí)
                    .referencePricePerNight(new BigDecimal("969284")) // giá gốc (bao gồm thuế/phí)
                    .discountPercent(25)
                    .taxPercent(new BigDecimal("8"))
                    .serviceFeePercent(new BigDecimal("7"))
                    .priceIncludesTax(true)
                    .priceIncludesServiceFee(true)
                    .lengthOfStayDiscounts(List.of())
                    .promoLabel("Bửa sáng ngon miệng cho cả gia đình")
                    .showLowAvailability(true)
                    .build()
            ))
            .build();

        // --------- Nearby places ----------
        List<NearbyPlace> nearby = List.of(
            // Địa điểm lân cận
            nearby("chua-chuc-thanh", "Chùa Chúc Thánh", "Địa điểm lân cận", 590.0),
            nearby("chua-phuoc-lam", "Chùa Phước Lâm", "Địa điểm lân cận", 653.0),
            nearby("ancient-house", "Ancient House", "Địa điểm lân cận", 752.0),
            nearby("dinh-cam-pho", "Đình Cẩm Phô", "Địa điểm lân cận", 984.0),
            nearby("quang-truong-song-hoai", "Quảng trường Sông Hoài", "Địa điểm lân cận", 1090.0),
            nearby("hoi-quan-quang-dong", "Hội quán Quảng Đông - Chùa Quảng Triệu", "Địa điểm lân cận", 1140.0),
            nearby("nha-co-phung-hung", "Nhà cổ Phùng Hưng", "Địa điểm lân cận", 1170.0),
            nearby("chua-cau-hoi-an", "Chùa Cầu Hội An", "Địa điểm lân cận", 1170.0),
            nearby("bao-tang-sa-huynh", "Bảo tàng Văn hóa Sa Huỳnh", "Địa điểm lân cận", 1210.0),
            nearby("old-town-lantern-boat", "Old Town Lantern Boat", "Địa điểm lân cận", 1230.0),
            nearby("huynh-tu-duong", "Huỳnh Từ Đường", "Địa điểm lân cận", 1250.0),
            nearby("duc-an-house", "Duc An House", "Địa điểm lân cận", 1250.0),
            nearby("nha-tan-ky", "Nhà Tân Kỳ", "Địa điểm lân cận", 1310.0),
            nearby("bao-tang-lich-su-nghe-thuat", "Bảo tàng Lịch sử và Nghệ thuật", "Địa điểm lân cận", 1330.0),
            nearby("bao-tang-gom-su-hoi-an", "Bảo tàng gốm sứ mậu dịch Hội An", "Địa điểm lân cận", 1350.0),

            // Trung tâm giao thông
            nearby("old-town-lantern-boat-transport", "Old Town Lantern Boat", "Trung tâm giao thông", 1230.0),
            nearby("hoi-an-boat-harbor", "Hoi An Boat Harbor", "Trung tâm giao thông", 1560.0),

            // Trung tâm giải trí
            nearby("cho-dem-hoi-an", "Chợ đêm Hội An", "Trung tâm giải trí", 1430.0),
            nearby("chua-cau-hoi-an-entertainment", "Chùa Cầu Hội An", "Trung tâm giải trí", 1170.0),
            nearby("ky-uc-hoi-an", "Ký Ức Hội An", "Trung tâm giải trí", 2370.0),
            nearby("dao-ky-uc-hoi-an", "Đảo Ký ức Hội An", "Trung tâm giải trí", 2380.0),
            nearby("quang-truong-song-hoai-entertainment", "Quảng trường Sông Hoài", "Trung tâm giải trí", 1090.0),

            // Khác
            nearby("quan-cong-mieu", "Quan Công Miếu", "Khác", 1450.0),
            nearby("om-factory-yoga-hoian", "Om Factory Yoga Hoi An", "Khác", 2530.0)
        );

        // --------- General info ----------
        GeneralInfo generalInfo = GeneralInfo.builder()
            .mainFacilitiesSummary("Máy lạnh, Nhà hàng, Lễ tân 24h, Chỗ đậu xe, WiFi")
            .distanceToCityCenterKm(1.23)
            .popularAreaSummary("Old Town Lantern Boat, Chợ đêm Hội An, Quan Công Miếu")
            .totalRooms(10)
            .totalFloors(3)
            .otherHighlightFacilities("Thức uống chào mừng miễn phí, dịch vụ concierge, thu đổi ngoại tệ, check-in sớm.")
            .interestingPlacesSummary("Chùa Chúc Thánh, Chùa Phước Lâm, Ancient House")
            .build();

        // --------- Policy ----------
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
                    .section(PolicySection.REQUIRED_DOCUMENTS)
                    .title("Giấy tờ bắt buộc")
                    .content("Khi nhận phòng, bạn cần cung cấp CMND/CCCD, hộ chiếu hoặc giấy tờ tùy thân hợp lệ. Các giấy tờ cần thiết có thể ở dạng bản mềm.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.GENERAL_CHECKIN_GUIDE)
                    .title("Hướng dẫn nhận phòng chung")
                    .content("""
                        Bạn sẽ được chào đón bởi đội ngũ nhân viên 24/24. Họ sẽ hỗ trợ chi tiết thủ tục nhận phòng 
                        và cung cấp thông tin hữu ích về Hội An. Bạn có thể đặt tour, mua vé show thông qua lễ tân. 
                        Vui lòng liên hệ hotline +84766553232 hoặc email: bespokevillahoian@gmail.com trước khi đến nếu cần.
                    """)
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.MIN_AGE)
                    .title("Chính sách về độ tuổi tối thiểu")
                    .content("Độ tuổi tối thiểu để nhận phòng là 18 tuổi. Khách nhỏ tuổi phải có người lớn đi cùng khi nhận phòng.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.EARLY_CHECKIN)
                    .title("Nhận phòng sớm")
                    .content("Bạn có thể yêu cầu nhận phòng sớm hơn giờ quy định tùy tình trạng phòng. Vui lòng liên hệ cơ sở lưu trú để xác nhận.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.LATE_CHECKOUT)
                    .title("Trả phòng trễ")
                    .content("Bạn có thể yêu cầu trả phòng trễ và có thể áp dụng phụ phí. Vui lòng liên hệ cơ sở lưu trú khi có nhu cầu.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.SMOKING)
                    .title("Hút thuốc")
                    .content("Chỉ được phép hút thuốc trong khu vực được chỉ định. Phòng không hút thuốc được áp dụng phí nếu hút trong phòng.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.PETS)
                    .title("Thú cưng")
                    .content("Không được mang theo thú cưng.")
                    .build(),
                PolicyItem.builder()
                    .section(PolicySection.AIRPORT_TRANSFER)
                    .title("Đưa đón sân bay")
                    .content("Có dịch vụ đưa đón sân bay với phí khoảng VND 270.000/người. Vui lòng liên hệ trước để được sắp xếp.")
                    .build()
            ))
            .build();

        // --------- Review stats ----------
        ReviewStats reviewStats = ReviewStats.builder()
            .cleanlinessScore(8.8)
            .roomAmenitiesScore(8.9)
            .foodScore(8.5)
            .locationScore(8.9)
            .serviceScore(8.9)
            .keywords(List.of(
                keyword("distance_to_center", "Khoảng cách đến trung tâm", 11),
                keyword("friendly_staff", "Nhân viên thân thiện", 11),
                keyword("room_space", "Không gian phòng", 10),
                keyword("access", "Đường đi", 10),
                keyword("surrounding_area", "Khu vực xung quanh", 8),
                keyword("family_friendly", "Hợp cho gia đình", 8),
                keyword("checkin_checkout", "Giờ nhận/trả phòng", 8),
                keyword("bathroom", "Phòng tắm", 7),
                keyword("wifi", "WiFi", 6)
            ))
            .build();

        // --------- Tax config & filter facets ----------
        TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
            .defaultVatPercent(new BigDecimal("8"))
            .defaultServiceFeePercent(new BigDecimal("7"))
            .showPricePreTax(false)
            .build();

        HotelFilterFacets filterFacets = HotelFilterFacets.builder()
            .hasFreeCancellation(true)   // có plan Family pay at hotel, refundable
            .hasPayAtHotel(true)
            .hasBreakfastIncluded(false) // chưa seed plan có bữa sáng, có thể thêm sau
            .build();

        // --------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
            .partnerId("partner-bespoke-villa-hoi-an")
            .partnerName("Bespoke Villa Hoi An")
            .partnerEmail("bespokevillahoian@gmail.com")
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
                "Những tiện ích tại Bespoke Villa Hoi An?",
                "Những tiện ích tại Bespoke Villa Hoi An bao gồm Máy lạnh, Nhà hàng, Lễ tân 24h, Chỗ đậu xe, WiFi (một số dịch vụ sẽ yêu cầu trả thêm phí)."
            ),
            faq(
                "Bespoke Villa Hoi An có mức giá là bao nhiêu?",
                "Phòng tại Bespoke Villa Hoi An có giá từ khoảng 424.958 VND. Vui lòng xem trang khuyến mãi khách sạn để có thêm ưu đãi."
            ),
            faq(
                "Thời gian nhận phòng và trả phòng của Bespoke Villa Hoi An?",
                "Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00."
            ),
            faq(
                "Bespoke Villa Hoi An có phục vụ ăn sáng không?",
                "Có, Bespoke Villa Hoi An có cung cấp bữa sáng. Tuy nhiên bạn cần chọn loại phòng bao gồm ăn sáng hoặc trả thêm phụ phí nếu đặt phòng không kèm bữa sáng."
            ),
            faq(
                "Khách sạn Bespoke Villa Hoi An đạt tiêu chuẩn mấy sao?",
                "Bespoke Villa Hoi An là khách sạn đạt tiêu chuẩn 3.0 sao."
            ),
            faq(
                "Bespoke Villa Hoi An có bao nhiêu tầng lầu?",
                "Bespoke Villa Hoi An có tổng cộng 3 tầng."
            ),
            faq(
                "Tổng số phòng hiện có tại Bespoke Villa Hoi An là bao nhiêu?",
                "Tổng số phòng tại Bespoke Villa Hoi An là 10 phòng."
            ),
            faq(
                "Bespoke Villa Hoi An có chỗ đậu xe không?",
                "Có, Bespoke Villa Hoi An có chỗ đậu xe. Vui lòng liên hệ trực tiếp khách sạn để xác nhận thông tin chi tiết."
            ),
            faq(
                "Bespoke Villa Hoi An có hỗ trợ nhận phòng sớm không?",
                "Có, khách sạn hỗ trợ nhận phòng sớm tùy tình trạng phòng. Vui lòng liên hệ khách sạn để sắp xếp."
            ),
            faq(
                "Bespoke Villa Hoi An có cung cấp dịch vụ trả phòng trễ không?",
                "Có, khách sạn hỗ trợ trả phòng trễ tùy tình trạng phòng. Vui lòng liên hệ khách sạn để sắp xếp."
            ),
            faq(
                "Bespoke Villa Hoi An có dịch vụ đưa đón sân bay không?",
                "Có, Bespoke Villa Hoi An cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ khách sạn để biết thêm chi tiết."
            ),
            faq(
                "Đánh giá của khách lưu trú trước đây về Bespoke Villa Hoi An như thế nào?",
                "Bespoke Villa Hoi An được đánh giá cao về Tiện nghi phòng, Vị trí, Dịch vụ và Tiện ích theo nhận xét của khách lưu trú trên Traveloka."
            ),
            faq(
                "Những địa điểm nổi bật nào nằm gần khách sạn Bespoke Villa Hoi An?",
                "Một số địa điểm gần khách sạn: Chùa Chúc Thánh, Chùa Phước Lâm, Ancient House, Đình Cẩm Phô, Quảng trường Sông Hoài."
            ),
            faq(
                "Những loại phòng đang khả dụng tại Bespoke Villa Hoi An?",
                "Những loại phòng đang khả dụng: Deluxe Twin, Family."
            ),
            faq(
                "Phòng tại Bespoke Villa Hoi An được trang bị những tiện nghi gì?",
                "Hầu hết các phòng đều có vòi tắm đứng, khu vực chờ, nước nóng, máy lạnh, ban công/sân hiên cùng nhiều tiện nghi khác."
            ),
            faq(
                "Mỗi phòng tại Bespoke Villa Hoi An có thể lưu trú tối đa bao nhiêu khách?",
                "Tối đa 4 khách/phòng, tùy từng hạng phòng. Sức chứa cụ thể được ghi rõ khi đặt phòng."
            )
        );

        // --------- Build hotel doc cuối cùng ----------
        return HotelDoc.builder()
            .active(true)
            .destinationSlug("hoi-an")
            .cityName("Hội An")
            .districtName("Tân An")
            .wardName(null)
            .addressLine("153 Lê Hồng Phong, Tân An, Hội An, Quảng Nam, Việt Nam")
            .location(new double[]{108.3205, 15.8765})
            .name("Bespoke Villa Hoi An")
            .slug(slug)
            .hotelType(HotelType.VILLA)
            .starRating(3)
            .shortDescription("Biệt thự 3 sao yên bình tại Tân An, gần khu vui chơi giải trí và phố cổ Hội An.")
            .description("Bespoke Villa Hoi An là một điểm đến lý tưởng cho những ai đang tìm kiếm sự yên bình và sang trọng tại thành phố cổ Hội An. Biệt thự 3 tầng hiện đại, nội thất tinh tế, sân vườn thoáng đãng cùng dịch vụ thân thiện giúp du khách có kỳ nghỉ thư giãn và đáng nhớ.")
            .phone("+84766553232")
            .email("bespokevillahoian@gmail.com")
            .website(null)
            .defaultCheckInTime(LocalTime.of(14, 0))
            .defaultCheckOutTime(LocalTime.of(12, 0))
            .hasOnlineCheckin(true)
            .avgRating(8.9)
            .reviewsCount(102)
            .ratingLabel("Rất tốt")
            .minNightlyPrice(new BigDecimal("379932"))  // min giá/đêm (bao gồm thuế/phí)
            .currencyCode("VND")
            .referenceNightlyPrice(new BigDecimal("450000"))
            .discountPercent(15)
            .taxConfig(taxConfig)
            .filterFacets(filterFacets)
            .images(images)
            .content(content)
            .amenities(hotelAmenities)
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