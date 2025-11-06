package com.mravel.catalog.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepository;

import static com.mravel.catalog.utils.ContentBlocks.*;
import static com.mravel.catalog.utils.Images.*;

@Configuration
public class SeedDestinations {

  @Bean
  CommandLineRunner seedDestinationsRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedDestinations] start");

      List<PlaceDoc> seeds = new ArrayList<>();
      seeds.add(daNang());
      seeds.add(hoiAn());
      seeds.add(haNoi());
      seeds.add(hoChiMinh());
      seeds.add(sapa());
      seeds.add(nhaTrang());

      int inserted = 0;
      for (PlaceDoc p : seeds) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedDestinations] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedDestinations] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedDestinations] done, inserted = " + inserted);
    };
  }

  // ===================== DESTINATIONS =====================

  private static PlaceDoc daNang() {
    String slug = "da-nang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .venueType(null)
        .parentId(null)
        .parentSlug(null)
        .ancestors(List.of())
        .childrenCount(0)
        .name("Đà Nẵng")
        .slug(slug)
        .shortDescription("Thành phố đáng sống bên sông Hàn, trung tâm của miền Trung Việt Nam.")
        .description(
            "Đà Nẵng nổi tiếng với bãi biển Mỹ Khê, Bà Nà Hills, Ngũ Hành Sơn và những cây cầu biểu tượng. " +
            "Ẩm thực phong phú với mì Quảng, bún chả cá, hải sản tươi sống. Khí hậu ôn hòa, giao thông thuận tiện.")
        .phone(null).email(null).website(null)
        .addressLine("TP. Đà Nẵng, Việt Nam")
        .countryCode("VN")
        .provinceCode(null)
        .districtCode(null)
        .wardCode(null)
        .provinceName("Đà Nẵng")
        .districtName(null)
        .wardName(null)
        .location(new double[]{108.2208, 16.0678}) // [lon, lat]
        .priceLevel(null)
        .minPrice(null).maxPrice(null)
        .avgRating(0.0).reviewsCount(0)
        .images(withCover(
            img("https://cdn-media.sforum.vn/storage/app/media/ctvseo_MH/%E1%BA%A3nh%20%C4%91%E1%BA%B9p%20%C4%91%C3%A0%20n%E1%BA%B5ng/anh-dep-da-nang-thumb.jpg", "Toàn cảnh thành phố", true, 0),
            img("https://kyuchoian.com/userfiles/image/du-ch-da-nang/2023/cau-rong/tren-cao.jpg", "Cầu Rồng nhìn từ sông Hàn", false, 1),
            img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463042242_bien-my-khe-2.jpg", "Bãi biển Mỹ Khê buổi sớm", false, 2),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Bà Nà Hills – Cầu Vàng", false, 3),
            img("https://www.huongnghiepaau.com/wp-content/uploads/2020/10/mi-quang-mien-trung.jpg", "Ẩm thực mì Quảng", false, 4),
            img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Ngũ Hành Sơn", false, 5)
        ))
        .openHours(List.of())
        .categories(List.of())
        .tags(List.of())
        .content(List.of(
            heading("Đà Nẵng – điểm hẹn của biển và những cây cầu"),
            quote("""
                Gió mằn mặn gọi tên bờ cát,
                đêm sông Hàn thắp sáng bóng cây cầu;
                người lữ khách đến rồi thương, đi rồi nhớ.
                """),
            paragraph("Nằm giữa Huế và Hội An, Đà Nẵng là cửa ngõ của dải đất miền Trung với nhịp sống hiện đại nhưng vẫn giữ được nét hiền hòa của biển và sông. Thành phố nổi tiếng với hệ thống cầu bắc qua sông Hàn, bãi biển Mỹ Khê cát trắng và cụm danh thắng Ngũ Hành Sơn. Nhiều khách gọi Đà Nẵng là “thành phố đáng sống” không chỉ vì hạ tầng sạch, mà còn bởi nhịp sống vừa phải, con người thân thiện và ẩm thực phong phú, hợp túi tiền cho cả du lịch tự túc lẫn gia đình."),
            paragraph("Nhịp sống ở Đà Nẵng dễ chịu: sáng sớm xuống biển tắm, trưa tránh nắng trong các quán café mát rượi, chiều muộn dạo sông Hàn đón gió và tối thưởng thức hải sản. Các tuyến đường thẳng, rộng, ít kẹt xe giúp việc di chuyển thuận tiện, ngay cả vào mùa cao điểm. Từ đây, bạn cũng dễ dàng kết nối “tam giác di sản” Huế – Đà Nẵng – Hội An chỉ trong vài ngày."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng – biểu tượng du lịch Đà Nẵng", false, 0)),
            paragraph("Về khí hậu, Đà Nẵng có hai mùa rõ rệt: mùa khô (khoảng tháng 3–8) và mùa mưa (tháng 9–12). Mùa khô là thời điểm lý tưởng cho các hoạt động biển, lặn ngắm san hô và dã ngoại. Mùa mưa mát mẻ hơn, phù hợp cho trải nghiệm ẩm thực, café, bảo tàng Chăm hay những ngày “chạy trốn nắng” ở Bà Nà Hills. Nếu đi gia đình, 3–4 ngày là vừa đủ để cảm nhận nhịp điệu thành phố."),
            paragraph("Dù đi mùa nào, bạn cũng nên theo dõi dự báo thời tiết cho những hoạt động ngoài trời. Đặc biệt, mùa mưa đôi khi có áp thấp hoặc bão, lịch trình nên linh hoạt và luôn có phương án dự phòng: thay bãi biển bằng bảo tàng, thay leo núi bằng trải nghiệm ẩm thực. Kem chống nắng, mũ nón, áo khoác mỏng và dép đi biển là “bộ tứ” không nên thiếu trong balo."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng phun lửa", false, 0),
                img("https://static.vinwonders.com/2022/04/bai-bien-my-khe-da-nang-2.jpg", "Biển Mỹ Khê", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/da-den-da-nang-thi-phai-oanh-tac-5-ngon-nui-o-ngu-hanh-son-1636215280.jpg", "Ngũ Hành Sơn", false, 0)
            )),
            heading("Di chuyển & ở đâu?"),
            paragraph("Sân bay quốc tế Đà Nẵng nằm ngay trung tâm, cách bờ biển chỉ 10–15 phút đi xe. Từ sân bay, bạn có thể gọi taxi, xe công nghệ hoặc thuê xe máy để chủ động. Khu vực dọc đường Võ Nguyên Giáp – Trường Sa tập trung nhiều khách sạn, resort sát biển, trong khi khu trung tâm quanh sông Hàn tiện cho việc đi ăn uống, café và dạo đêm. Mùa cao điểm, nên đặt phòng trước 2–3 tuần để có mức giá dễ chịu và view đẹp."),
            paragraph("Với ngân sách vừa phải, homestay và boutique hotel là lựa chọn hay: phòng sạch, bữa sáng cơ bản, không gian ấm cúng, nhiều nơi còn cho mượn xe đạp. Nếu đi nhóm bạn, thuê căn hộ (apartment) giúp tối ưu chi phí, có bếp để tự chế biến hải sản mua ở chợ. Gia đình có trẻ nhỏ sẽ thích resort có hồ bơi, bãi biển riêng, câu lạc bộ trẻ em và dịch vụ đưa đón bài bản."),
            paragraph("Di chuyển trong thành phố tương đối dễ: đường rộng, biển hiệu rõ ràng. Nếu tự lái xe máy, nhớ đội mũ bảo hiểm, chạy đúng làn, giữ tốc độ vừa phải và quan sát kỹ ở các giao lộ gần cầu. Buổi tối dọc bờ Đông có nhiều hàng quán, bãi đậu xe thuận tiện, thích hợp để dừng chân ngắm biển và nghe sóng."),
            heading("Ăn gì ở Đà Nẵng?"),
            paragraph("Đến Đà Nẵng, đừng bỏ qua mì Quảng, bún chả cá, bánh tráng cuốn thịt heo hai đầu da và hải sản tươi theo mâm. Khu ven biển có nhiều quán bình dân, chọn hải sản theo ký rồi chế biến tại chỗ. Buổi tối đi chợ đêm, bạn có thể thử ốc hút, mít trộn, bánh tráng nướng – vừa rẻ vừa vui. Cà phê Đà Nẵng đa dạng: từ specialty đến quán vỉa hè đúng “vibe” miền Trung."),
            paragraph("Nếu thích trải nghiệm địa phương, hãy ghé các chợ như chợ Hàn để thấy nhịp buôn bán sôi động và mua đặc sản về làm quà: mực rim, tré, bánh khô mè, chả bò. Lúc gọi món hải sản, bạn nên hỏi trước về cách chế biến (nướng, hấp, xào me...) và khẩu vị cay – mặn phù hợp với nhóm, để bữa ăn tròn vị hơn."),
            imageBlock(img("https://www.huongnghiepaau.com/wp-content/uploads/2020/10/mi-quang-mien-trung.jpg", "Mì Quảng – món ăn trứ danh", false, 0)),
            heading("Đi đâu chơi?"),
            paragraph("Buổi sáng sớm, biển Mỹ Khê trong xanh và ít người, phù hợp tắm biển hoặc chạy bộ dọc bờ. Ngũ Hành Sơn chỉ cách trung tâm một đoạn ngắn, có động đá mát lạnh và làng đá Non Nước lâu đời. Buổi chiều, bạn có thể lên bán đảo Sơn Trà: ghé Linh Ứng, ngắm cảnh ở bãi Bụt, bãi Đá Đen và săn hoàng hôn từ những khúc cua ôm vịnh tuyệt đẹp."),
            paragraph("Một ngày trọn vẹn ở Bà Nà Hills giúp đổi gió với khí hậu mát mẻ, cảnh quan kiểu Âu và các góc chụp hình nổi tiếng như Cầu Vàng. Tối về lại thành phố, dạo sông Hàn, thưởng thức show phun lửa cầu Rồng cuối tuần, sau đó ghé café hay bar rooftop ngắm phố đêm. Nếu còn thời gian, chạy xe thêm 30–40 phút là tới Hội An để “đổi vibe” cổ kính."),
            heading("Lịch trình gợi ý 3 ngày"),
            paragraph("Ngày 1: Dạo biển Mỹ Khê, tắm biển sáng sớm; chiều lên Ngũ Hành Sơn thăm động, làng đá Non Nước; tối dạo cầu Rồng – cầu Tình Yêu, ngắm sông Hàn và thưởng thức hải sản ven biển."),
            paragraph("Ngày 2: Đi Bà Nà Hills – Cầu Vàng cả ngày; chiều về thành phố nghỉ ngơi; tối café hoặc bar rooftop; nếu đi cùng trẻ nhỏ, có thể chọn khu vui chơi trong nhà để tiết kiệm sức cho các bé."),
            paragraph("Ngày 3: Chạy xe ra bán đảo Sơn Trà, dừng Linh Ứng, bãi Bụt – bãi Đá Đen; trưa về trung tâm ăn bún chả cá; chiều mua đặc sản ở chợ Hàn, ghé café rồi rời thành phố."),
            infoBox("Mẹo nhỏ: Cầu Rồng phun lửa cuối tuần (thường 21:00); đi sớm 15–20 phút để có chỗ đứng đẹp, tránh đứng quá gần đầu rồng nếu không muốn “rửa tội” bằng nước."),
            divider(),
            heading("Văn hóa & con người"),
            paragraph("Người Đà Nẵng hiền hòa, chân chất, nói năng nhỏ nhẹ và sẵn sàng chỉ đường khi bạn cần. Các hàng quán địa phương thường phục vụ nhanh, giá niêm yết rõ. Khi mua hải sản hoặc đặc sản, bạn nên hỏi giá trước và cân đo đủ, hóa đơn minh bạch để tránh hiểu lầm không đáng có."),
            paragraph("Ứng xử văn minh nơi công cộng được đề cao: xếp hàng, giữ vệ sinh bãi biển, không xả rác, không bấm còi inh ỏi khi lưu thông. Những điều nhỏ này góp phần giữ hình ảnh “thành phố đáng sống” và giúp trải nghiệm của bạn lẫn cộng đồng du khách khác trở nên dễ chịu hơn."),
            heading("Chi phí & ngân sách"),
            paragraph("Mức chi ở Đà Nẵng khá “dễ thở” so với nhiều điểm đến biển khác. Khách sạn tầm trung dao động hợp lý, bữa ăn với hải sản tùy theo loại bạn chọn, còn đồ ăn vặt và café rất phong phú. Nếu đi nhóm 3–4 người, việc chia sẻ chi phí thuê xe, căn hộ hay combo vé sẽ tiết kiệm đáng kể."),
            paragraph("Để tối ưu, bạn có thể đặt phòng sớm mùa cao điểm, chọn khung giờ ăn không quá “hot” và sử dụng các mã giảm giá dịch vụ vận chuyển/điểm tham quan khi có. Những khoản nhỏ tưởng chừng vụn vặt nhưng cộng lại giúp lịch trình “dễ thở” hơn nhiều."),
            heading("Thời điểm & lưu ý"),
            paragraph("Tháng 3–8 nắng đẹp, biển trong – phù hợp nhất cho trải nghiệm ngoài trời; tháng 9–12 có mưa, thỉnh thoảng bão; nếu đi mùa này nên dự phòng phương án trong nhà: bảo tàng Chăm, café, shopping hoặc “food tour”. Kem chống nắng, áo khoác mỏng và dép đi biển là vật bất ly thân."),
            paragraph("Khi thuê xe máy, bạn nhớ mang đủ giấy tờ, kiểm tra phanh – đèn – còi trước khi xuất phát. Ở các đoạn đường ven biển buổi tối, gió mạnh có thể khiến bạn “hụt tay lái”, nên đi chậm, giữ khoảng cách an toàn. Nếu đi Bà Nà hoặc Sơn Trà, theo dõi dự báo sương mù để tránh bị hạn chế tầm nhìn."),
            mapBlock(new double[]{108.2208, 16.0678}),
            quote("“Thành phố đáng sống” không chỉ là khẩu hiệu – mà là cảm giác bạn mang theo sau chuyến đi.")
        ))
        .build();
  }

  private static PlaceDoc hoiAn() {
    String slug = "hoi-an";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name("Hội An")
        .slug(slug)
        .shortDescription("Phố cổ di sản UNESCO, lung linh đèn lồng bên sông Hoài.")
        .description("Hội An lưu giữ kiến trúc Á – Âu giao thoa, ẩm thực cao lầu – mì Quảng đặc trưng, " +
                     "biển An Bàng thanh bình và làng nghề truyền thống.")
        .addressLine("TP. Hội An, Quảng Nam, Việt Nam")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3380, 15.8801})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ Hội An về đêm", true, 0),
            img("https://eholiday.vn/wp-content/uploads/2023/07/du-thuyen-song-hoai-hoi-an.jpg", "Sông Hoài và thuyền hoa đăng", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/le-hoi-den-long-hoi-an-su-kien-dac-sac-duoc-mong-doi-nhat-o-pho-hoi-1620149223.jpg", "Đèn lồng Hội An", false, 2),
            img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/cao-lau-3-1751684730.jpg", "Cao lầu", false, 4),
            img("https://hotelroyalhoian.vn/wp-content/uploads/2025/04/an-bang-doi-net-2-1.jpg", "Biển An Bàng", false, 5)
        ))
        .content(List.of(
            heading("Hội An – di sản sống giữa lòng Quảng Nam"),
            quote("""
                Đêm thắp nghìn hoa lửa,
                sông Hoài soi bóng đèn lồng;
                bước chậm qua Chùa Cầu, nghe thời gian hát nhỏ.
                """),
            paragraph("Là thương cảng quốc tế sầm uất từ thế kỷ XVI–XVII, Hội An lưu giữ một tổng thể kiến trúc gỗ hiếm hoi còn nguyên vẹn ở Đông Nam Á. Những ngôi nhà mái ngói rêu phong, mặt tiền hẹp, sân trong thoáng đãng và những dãy cửa gỗ sơn màu trầm tạo nên nhịp điệu thị giác rất riêng. Khi phố lên đèn, sắc đỏ – vàng của đèn lồng soi bóng xuống sông Hoài, khiến cả không gian như chậm lại, mời gọi du khách đi bộ và “thở” cùng nhịp sống cổ phố."),
            paragraph("Trải nghiệm ở Hội An nên bắt đầu từ những bước chân thong thả: dạo qua các con hẻm nhỏ, ghé những hiệu may lâu đời, đứng lặng trước Chùa Cầu để cảm nhận sự giao thoa Nhật – Hoa – Việt. Bạn có thể mua vé tham quan khu phố cổ để vào các điểm di tích chính, mỗi nơi đều kể một câu chuyện về cộng đồng thương nhân, về tín ngưỡng và sinh hoạt mưu sinh đã tạo nên linh hồn của đô thị này."),
            imageBlock(img("https://divui.com/blog/wp-content/uploads/2018/08/Hoi-An.jpg", "Phố cổ lung linh", false, 0)),
            heading("Lịch sử & kiến trúc"),
            paragraph("Hội An từng là “điểm rơi” của tơ lụa, gốm sứ, hương liệu trên tuyến hải thương quốc tế. Kiến trúc nhà gỗ kết cấu vì kèo, tường gạch, mái âm dương giúp chống nóng, thoát nước và chịu gió bão tốt. Sự pha trộn tinh tế của họa tiết Trung Hoa, tỷ lệ Nhật Bản và kỹ thuật bản địa tạo nên một thẩm mỹ hài hòa, mộc mạc nhưng bền bỉ qua thời gian. Đi trong phố cổ, bạn sẽ thấy từng khung cửa, lan can, bao lam đều như một “bài thơ” chạm khắc."),
            paragraph("Chùa Cầu là biểu tượng kiến trúc độc đáo: một cây cầu gỗ có mái che do thương nhân Nhật góp xây, gắn với truyền thuyết trấn yểm “cá chép hóa rồng”. Khu vực quanh đó tập trung nhiều hội quán của người Hoa – nơi cộng đồng từng sinh hoạt, cầu phúc, làm từ thiện; nay là điểm tham quan, trưng bày hiện vật và ghi chép lịch sử cộng đồng cư dân."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ lên đèn", false, 0),
                img("https://eholiday.vn/wp-content/uploads/2023/07/du-thuyen-song-hoai-hoi-an.jpg", "Thuyền hoa đăng sông Hoài", false, 1),
                img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu biểu tượng", false, 2)
            )),
            heading("Trải nghiệm dọc sông Hoài"),
            paragraph("Chiều xuống, hãy men theo bờ sông Hoài để nghe tiếng chèo khua và lời rao bán đèn hoa đăng. Việc thả một chiếc đèn nhỏ mang ý nghĩa cầu an, đồng thời là khoảnh khắc tĩnh tâm hiếm có giữa chuyến đi. Nếu đi thuyền, bạn nên thỏa thuận giá trước, chọn mặc áo phao và hạn chế đứng lên khi thuyền đang di chuyển để đảm bảo an toàn. Bến thuyền cũng là nơi dễ săn ảnh ngược sáng đẹp mắt."),
            paragraph("Hai bên bờ sông là chuỗi hàng quán, tiệm quà lưu niệm và cà phê ban công nhìn xuống dòng nước. Bạn có thể dừng chân thưởng thức một tách cà phê hoặc nước thảo mộc mát lạnh, quan sát dòng người qua lại, nghe tiếng gánh hàng, tiếng cười nói của những gia đình địa phương. Sự sống động ấy là “linh khí” khiến Hội An vừa trầm mặc vừa rất… đời thường."),
            heading("Ăn gì ở Hội An?"),
            paragraph("Cao lầu là món “bản sắc”: sợi mì khô dai, tóp mỡ giòn, xá xíu, rau sống Trà Quế và nước sốt mằn mặn, ăn kèm bánh đa. Bánh mì Hội An nổi tiếng bởi vỏ giòn ruộm và phần nhân phong phú; mì Quảng lại đậm đà với nước dùng sánh nhẹ, đậu phộng rang và bánh tráng nướng. Bên cạnh đó, cơm gà, hoành thánh, chè bắp Cẩm Nam hay tào phớ lá dứa đều đáng thử."),
            paragraph("Buổi tối, các quầy hàng ven phố cổ bán nhiều món ăn vặt giá “mềm”. Bạn có thể gọi theo khẩu vị: ít cay, ít mặn hoặc thêm rau – đa số quán phục vụ linh hoạt. Khi mua đặc sản mang về như đèn lồng, bạn nên chọn loại khung chắc, vải thô bền màu; với đồ ăn khô, nhớ kiểm tra hạn sử dụng và cách bảo quản."),
            heading("Lễ hội & đêm Hội An"),
            paragraph("Vào các đêm rằm, phố cổ tổ chức tắt đèn điện một phần, ưu tiên ánh sáng đèn lồng – không khí cổ xưa trở nên rõ nét hơn. Những hoạt động như hát bài chòi, kéo co, bịt mắt đập nồi… tái hiện sinh hoạt cộng đồng xưa. Đây là thời điểm rất đông khách, nên bạn hãy đến sớm, chọn điểm đứng thoáng, và giữ ý thức bảo vệ không gian chung khi chụp ảnh, quay phim."),
            paragraph("Ngoài đèn lồng, nhiều nhà cổ mở triển lãm ngắn hạn về nhiếp ảnh, sưu tầm đồ xưa hoặc trưng bày nghề thủ công như mộc, gốm, mây tre. Nếu thích không khí tĩnh lặng, hãy rẽ vào các hẻm nhỏ ít người, nơi ánh đèn vàng hắt lên tường vôi cũ và tiếng guốc gõ nền gạch vọng lại nghe rất… Hội An."),
            heading("Làng nghề & bãi biển gần phố cổ"),
            paragraph("Rời phố, bạn có thể đạp xe tới làng rau Trà Quế để xem cách trồng rau hữu cơ và thử làm nông trong một buổi; ghé làng gốm Thanh Hà để quan sát nghệ nhân chuốt gốm; hoặc xuống bến thuyền Cẩm Thanh trải nghiệm thúng chai giữa rừng dừa nước – một bức tranh sông nước rất khác chỉ cách phố cổ vài cây số."),
            paragraph("Muốn “đổi gió”, biển An Bàng là lựa chọn yên bình: bãi cát thoải, sóng vừa phải, nhiều quán biển thiết kế tối giản, phù hợp nằm đọc sách cả chiều. Sáng sớm hoặc hoàng hôn là thời điểm nước và ánh sáng đẹp nhất để tắm biển, chụp ảnh. Luôn chú ý bảng cảnh báo và không bơi quá xa khu vực có nhân viên cứu hộ."),
            heading("Lịch trình gợi ý 2–3 ngày"),
            paragraph("Ngày 1: Mua vé tham quan phố cổ, vào các điểm di tích chính (nhà cổ, hội quán, Chùa Cầu); chiều đi thuyền sông Hoài, tối dạo đèn lồng, thưởng thức cao lầu hoặc mì Quảng."),
            paragraph("Ngày 2: Đạp xe Trà Quế – Thanh Hà – Cẩm Thanh theo vòng làng nghề, trải nghiệm thúng chai; chiều tắm biển An Bàng, tối quay lại phố cổ thưởng thức bánh mì, cơm gà và cà phê ban công."),
            paragraph("Ngày 3 (nếu có): Tham gia lớp nấu ăn món địa phương buổi sáng, trưa nghỉ ngơi và mua quà đặc sản; chiều chụp ảnh dọc các hẻm ít người, tối dự đêm rằm (nếu trùng lịch) hoặc xem bài chòi ở quảng trường."),
            infoBox("Mẹo nhỏ: Phố cổ ưu tiên người đi bộ theo khung giờ; bạn hãy gửi xe ở bãi quy định rồi đi bộ để trải nghiệm trọn vẹn. Vào mùa cao điểm, nên đặt trước vé trải nghiệm (thuyền, lớp nấu ăn) và chọn khung giờ sớm để tránh đông."),
            heading("Thời điểm & lưu ý"),
            paragraph("Hội An nắng đẹp từ khoảng tháng 2–8; tháng 9–12 có thể mưa, thỉnh thoảng ngập cục bộ do triều. Mang theo nón, kem chống nắng, áo khoác mỏng; giày sandal/giày bệt phù hợp đi bộ lâu. Khi chụp ảnh người dân, nên xin phép bằng nụ cười và cái gật đầu – bạn sẽ nhận lại sự thân thiện xứng đáng."),
            paragraph("Phố cổ có nhiều cầu gỗ, bậc thềm cao – đi lại chú ý bước chân, đặc biệt buổi tối khi ánh sáng trầm. Ẩm thực đa phần đậm vị; nếu ăn nhạt, bạn có thể nói trước với quán. Hạn chế dùng túi nylon khi mua đồ, tận dụng túi vải cá nhân để góp phần giữ gìn không gian di sản xanh – sạch – đẹp."),
            quote("“Hội An đẹp nhất là khi bạn đi chậm.”"),
            divider(),
            mapBlock(new double[]{108.3380, 15.8801})
        ))
        .build();
  }

  private static PlaceDoc haNoi() {
    String slug = "ha-noi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name("Hà Nội")
        .slug(slug)
        .shortDescription("Thủ đô nghìn năm văn hiến với phố cổ, hồ Hoàn Kiếm và ẩm thực tinh tế.")
        .description("Hà Nội hấp dẫn bởi nhịp sống chậm, di tích lịch sử, mùa thu lá vàng và cà phê vỉa hè.")
        .addressLine("Thành phố Hà Nội, Việt Nam")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8342, 21.0278})
        .images(withCover(
            img("https://divui.com/blog/wp-content/uploads/2017/01/hanoi-top-10-attractions.jpg", "Hình ảnh Hà Nội từ trên cao", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/pho-co-ha-noi-1.jpg", "Phố cổ", false, 1),
            img("https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/2024_1_12_638406880045931692_cach-lam-bun-cha-ha-noi-0.jpg", "Bún chả", false, 2),
            img("https://dulichnewtour.vn/ckfinder/images/Tours/langbac/lang-bac%20(2).jpg", "Lăng Bác", false, 3),
            img("https://statics.vinpearl.com/mua-thu-ha-noi-thumb_1685027028.jpg", "Mùa thu Hà Nội", false, 4),
            img("https://kenh14cdn.com/thumb_w/600/vfLKYK84K2aVSVTBjN2M3SGbL8lqA9/Image/2015/03/DJ7A0875-4f435.jpg", "Cà phê vỉa hè", false, 5)
        ))
        .content(List.of(
            heading("Hà Nội – lưu giữ hồn xưa phố cổ"),
            quote("""
                Tháng mười heo may thổi,
                lá sấu rơi như lời thì thầm;
                Hà Nội gọi tên ai giữa những con ngõ nhỏ.
                """),
            paragraph("Hà Nội cuốn hút bởi nhịp sống chậm rãi đan xen giữa hiện đại và cổ kính. 36 phố phường vẫn còn thấp thoáng dáng dấp phường nghề xưa: Hàng Bạc, Hàng Mã, Hàng Thiếc… Mỗi buổi sáng, tiếng rao quen thuộc len qua những con ngõ nhỏ, hương phở nóng bốc khói cạnh ly cà phê đen sóng sánh. Giữa nhịp mới – cũ ấy, người Hà Nội vẫn giữ nếp đi bộ quanh hồ, đọc báo, chuyện trò bên vỉa hè như một thói quen đã ăn vào ký ức tập thể."),
            paragraph("Bốn mùa rõ rệt tạo nên “gu” thưởng thức riêng: xuân ẩm, hạ rực, thu vàng và đông rét ngọt. Mỗi mùa lại có một palette màu và mùi khác nhau – mùi hoa sữa vào thu, mùi lá khô trên hè phố, mùi cốm mới trong gánh hàng rong. Đến Hà Nội, hãy dành thời gian đi chậm, để ý những chi tiết nhỏ: ô cửa gỗ đã bóng màu thời gian, bức tường rêu, biển số nhà men sứ xanh trắng."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/ho-hoan-kiem-3-1710638969.jpg", "Hồ Gươm", false, 0)),
            heading("Phố cổ & những khoảng lặng quanh hồ"),
            paragraph("Hồ Hoàn Kiếm là “trái tim” của thành phố: ban ngày trong veo, đêm lung linh dưới ánh đèn. Từ đây, bạn có thể rẽ vào khu phố cổ để cảm nhận những mặt tiền nhà ống hẹp, sâu, sân nhỏ giữa nhà lấy sáng thông thoáng – kiểu kiến trúc thích nghi với đô thị đông đúc. Quanh hồ là những công trình Pháp cổ: Nhà hát Lớn, Bưu điện, những hàng cây xà cừ, sấu già tạo nên đường chân trời mềm mại, nhiều lớp."),
            paragraph("Buổi sớm cuối tuần, phố đi bộ quanh hồ mở ra không gian cho nghệ sĩ đường phố, trò chơi dân gian, hoạt động cộng đồng. Ngồi trên ghế đá, nghe chuông chùa Ngọc Sơn vọng lại, bạn sẽ hiểu vì sao người Hà Nội yêu những khoảnh khắc “rảnh rỗi” bên hồ – nơi ai cũng có thể tạm gác nhịp công việc để chậm lại một nhịp thở."),
            gallery(List.of(
                img("https://divui.com/blog/wp-content/uploads/2017/01/hanoi-top-10-attractions.jpg", "Hà Nội nhìn từ trên cao", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/pho-co-ha-noi-1.jpg", "Phố cổ 36 phố phường", false, 1),
                img("https://statics.vinpearl.com/mua-thu-ha-noi-thumb_1685027028.jpg", "Mùa thu Hà Nội", false, 2)
            )),
            heading("Lịch sử & di tích"),
            paragraph("Từ Thăng Long ngàn năm đến Hà Nội hôm nay là hành trình của những lớp trầm tích văn hóa. Khu Thành cổ, Văn Miếu – Quốc Tử Giám, đền Quán Thánh… là những mốc son ghi dấu học thuật và thi cử Nho giáo. Thời cận – hiện đại để lại nhiều công trình Pháp thuộc với phong cách tân cổ điển, art deco, tạo nên diện mạo đô thị thanh lịch. Mỗi di tích là một lát cắt – giúp bạn đọc được lịch sử bằng mắt thường qua chất liệu đá, gỗ, gạch ngói."),
            paragraph("Lăng Chủ tịch Hồ Chí Minh và Quảng trường Ba Đình là không gian trang nghiêm bậc nhất. Khi ghé thăm, bạn nên lưu ý trang phục kín đáo, giữ trật tự. Khu nhà sàn, ao cá, Phủ Chủ tịch gợi một tinh thần giản dị mà sâu sắc, đối thoại lặng lẽ với khu phố xá sôi động cách đó không xa."),
            heading("Ẩm thực Hà Nội – tinh tế trong từng bát – đĩa"),
            paragraph("Hà Nội là “đất phở”, nhưng không chỉ có phở. Bún chả thơm lừng mùi than hoa, bún thang trong trẻo mà nhiều tầng vị, bún đậu mắm tôm mộc mạc, chả cá Lã Vọng béo ngậy ăn kèm thì là và lạc rang. Mỗi món có “nghi thức” riêng: cách chần bánh, nêm dấm tỏi, thêm vài cọng hành hoa, một lát quất – những động tác nhỏ nhưng tạo nên bản sắc."),
            paragraph("Bữa sáng nên nhẹ nhàng với phở, trưa thử bún chả, tối chọn chả cá hoặc lẩu riêu cua bắp bò. Tráng miệng bằng cốm làng Vòng, kem Tràng Tiền, hoặc một cốc sấu dầm mát lạnh ngày hè. Nếu bạn thích trải nghiệm cà phê, đừng bỏ qua cà phê trứng – lớp kem trứng sánh mịn quyện espresso, hay ly nâu đá giản dị ở quán vỉa hè."),
            imageBlock(img("https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/2024_1_12_638406880045931692_cach-lam-bun-cha-ha-noi-0.jpg", "Bún chả", false, 0)),
            heading("Cà phê vỉa hè & văn hóa trò chuyện"),
            paragraph("Cà phê ở Hà Nội không chỉ để tỉnh táo, mà còn để… trò chuyện. Ghế nhựa thấp, bàn con, ly cà phê đặt sát mép đường nơi người và xe lướt qua – đó là “phòng khách mở” của người Hà Nội. Ở những góc phố quen, bạn có thể dõi theo vòng đời một ngày của thành phố: sáng làm việc, trưa thong thả, chiều bận rộn, đêm lắng lại."),
            paragraph("Thú vị nhất là quan sát cách người ta “đi chậm” giữa một đô thị không ngừng chuyển động. Một cuộc hẹn 15 phút có thể kéo dài cả giờ bởi những câu chuyện không đầu không cuối – nhưng chính sự dư dả thời gian ấy tạo nên chất “đằm” của Hà Nội."),
            heading("Mùa thu Hà Nội – khi lá vàng phủ lối"),
            paragraph("Thu đến, nắng vàng nhẹ như rót mật, gió heo may mỏng và bầu trời xanh cao. Hàng cây thay lá, những con đường Kim Mã, Phan Đình Phùng, Hoàng Diệu trở thành phông nền hoàn hảo cho những khung hình trong trẻo. Mùi hoa sữa có thể gây “tranh cãi”, nhưng với nhiều người, đó là tín hiệu mùa thu không thể lẫn vào đâu."),
            paragraph("Thời điểm đẹp thường rơi vào tháng 10–11: nhiệt độ dễ chịu để dạo bộ cả ngày. Hãy chuẩn bị áo khoác mỏng, giày bệt hoặc sneaker; buổi tối có thể se lạnh, một chiếc khăn mỏng sẽ khiến hành trình êm ái hơn."),
            heading("Lịch trình gợi ý 2–3 ngày"),
            paragraph("Ngày 1: Hồ Gươm – Đền Ngọc Sơn – phố cổ – Nhà thờ Lớn; trưa phở; chiều Nhà hát Lớn – khu phố Pháp; tối dạo hồ và cà phê trứng; nếu đi cuối tuần, tham gia phố đi bộ để cảm nhận nhịp cộng đồng."),
            paragraph("Ngày 2: Lăng Bác – Khu di tích Phủ Chủ tịch – Chùa Một Cột; trưa bún chả; chiều Văn Miếu – Hoàng thành; tối thưởng thức chả cá hoặc lẩu riêu cua; khu vực Phan Đình Phùng – Hoàng Diệu lý tưởng để đi bộ dưới tán cây cổ thụ."),
            paragraph("Ngày 3 (nếu có): Bảo tàng Mỹ thuật/ Dân tộc học – dạo Tây Hồ – Phủ Tây Hồ – chùa Trấn Quốc; chiều muộn ngắm hoàng hôn bên hồ; tối lang thang quận Tây Hồ hoặc quay về phố cổ ăn vặt, kết thúc bằng ly nâu đá đúng điệu."),
            infoBox("Mẹo nhỏ: Buổi sáng đi di tích nên xuất phát sớm để tránh đông; vào Lăng Bác lưu ý trang phục lịch sự. Ẩm thực Hà Nội thiên mặn – thanh; nếu ăn nhạt, bạn có thể nhờ quán nêm nhẹ tay."),
            divider(),
            heading("Di chuyển & lưu ý"),
            paragraph("Đường phố Hà Nội nhiều ngõ nhỏ, một số tuyến giờ cao điểm dễ ùn ứ. Khi di chuyển bằng taxi/xe công nghệ, bạn nên chọn điểm đón ở đầu/ngã tư rộng rãi. Đi bộ trong phố cổ cần chú ý vỉa hè hẹp, nhiều đoạn lồi lõm. Luôn mang theo ô gấp hoặc áo mưa mỏng vì mưa bất chợt khá thường gặp."),
            paragraph("Mua hàng ở khu phố cổ đa phần có niêm yết, nhưng bạn vẫn nên hỏi giá trước. Với hàng lưu niệm, hãy ưu tiên sản phẩm thủ công địa phương, kiểm tra chất liệu và đường may. Tôn trọng không gian tôn nghiêm ở đền – chùa; hạn chế nói lớn, giữ trật tự để trải nghiệm của mọi người đều dễ chịu."),
            quote("“Hà Nội – bình yên trong từng ngõ nhỏ.”"),
            mapBlock(new double[]{105.8342, 21.0278})
        ))
        .build();
  }

  private static PlaceDoc hoChiMinh() {
    String slug = "ho-chi-minh-city";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name("TP. Hồ Chí Minh")
        .slug(slug)
        .shortDescription("Đô thị sôi động nhất Việt Nam, trung tâm kinh tế – văn hoá phía Nam.")
        .description("Sài Gòn – TP. HCM nổi tiếng với nhịp sống nhanh, ẩm thực đường phố, " +
                     "các công trình Pháp và những con hẻm đầy câu chuyện.")
        .addressLine("TP. Hồ Chí Minh, Việt Nam")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.6602, 10.7626})
        .images(withCover(
            img("https://st.ielts-fighter.com/src/ielts-fighter-image/2023/01/09/5e6dbb91-3dab-4500-9a28-b9599aa12949.png", "Thành phố Hồ Chí Minhh nhìn từ trên cao", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-ruc-ro-1692570394.jpg", "Nhà thờ Đức Bà", false, 1),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Saigon_Central_Post_Office_2022.jpg/500px-Saigon_Central_Post_Office_2022.jpg", "Bưu điện Thành phố", false, 2),
            img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Chợ Bến Thành", false, 3),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Phố đi bộ Nguyễn Huệ", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/pho-am-thuc-ho-thi-ky-1699930160.jpeg", "Ẩm thực đường phố", false, 5)
        ))
        .content(List.of(
            heading("Sài Gòn – năng động, cởi mở và mến khách"),
            quote("""
                Mưa rào qua rất vội,
                hẻm nhỏ bừng đèn lên;
                ly cà phê còn ấm, câu chuyện dài thêm đêm.
                """),
            paragraph("Thành phố 24/7 với nhịp sống nhanh, người tứ xứ và năng lượng không ngủ; Sài Gòn quyến rũ bởi sự cởi mở, tính thực dụng dễ thương và một kho ký ức hòa trộn giữa nhà thờ gạch đỏ, bưu điện vàng rực, chợ búa rộn ràng cùng tiếng rao khuya."),
            heading("Kiến trúc & dấu ấn lịch sử"),
            paragraph("Quận 1 – 3 là nơi bạn dễ đọc lịch sử đô thị qua các lớp kiến trúc: Nhà thờ Đức Bà gạch Marseille, Bưu điện Thành phố phong cách tân cổ điển, các biệt thự Pháp rải dọc đường cây dầu; xen giữa là những cao ốc kính phản chiếu bầu trời nhiệt đới – một hòa âm nhìn là nhớ."),
            heading("Ẩm thực đường phố – vị ngon từ vỉa hè"),
            paragraph("Từ hủ tiếu gõ, cơm tấm sườn bì chả, bún mắm, phá lấu đến bánh mì thịt nướng – mỗi góc phố đều có món “ruột” của dân quanh vùng; buổi tối, các chợ đêm và con hẻm mở hội vị giác với hải sản nướng, ốc cay, chè khúc bạch, sương sáo, sâm bổ lượng."),
            heading("Cà phê & văn hóa hẹn hò"),
            paragraph("Cà phê bệt ở Nhà thờ Đức Bà – Bưu điện hay quán view phố đi bộ Nguyễn Huệ là nơi người trẻ kể tương lai và người làm ăn chốt hợp đồng; ly bạc xỉu đá, cà phê sữa pha phin nhỏ giọt chậm rãi nhưng cuộc trò chuyện thì luôn nhanh, thẳng và vui."),
            imageBlock(img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Cho-Ben-Thanh-4.jpg", "Chợ Bến Thành", false, 0)),
            heading("Đi đâu chơi?"),
            paragraph("Ban ngày, dạo Nhà thờ Đức Bà – Bưu điện – phố sách; trưa trốn nắng trong bảo tàng hoặc trung tâm thương mại; chiều muộn ra phố đi bộ Nguyễn Huệ đón gió, tối vòng qua chợ Bến Thành nhộn nhịp; nếu thích “đậm chất Hoa”, ghé Chợ Lớn – Bình Tây – Hải Thượng Lãn Ông để ngắm lồng đèn, thảo dược và nếm sủi cảo."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-ruc-ro-1692570394.jpg", "Nhà thờ Đức Bà", false, 0),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Saigon_Central_Post_Office_2022.jpg/500px-Saigon_Central_Post_Office_2022.jpg", "Bưu điện Thành phố", false, 1),
                img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Chợ Bến Thành", false, 2)
            )),
            heading("Nhịp sống đêm & mua sắm"),
            paragraph("Đêm đến là giờ đẹp của Sài Gòn: rooftop bar nhìn sông Sài Gòn, chợ đồ cũ – chợ đêm, khu Thảo Điền – Tân Định – Nguyễn Huệ sôi động; mua sắm trải từ boutique bản địa, chợ truyền thống đến mall hiện đại – dễ tìm đủ phong cách trong bán kính vài cây số."),
            heading("Lịch trình gợi ý 2–3 ngày"),
            paragraph("Ngày 1: Nhà thờ Đức Bà – Bưu điện – phố sách; trưa cơm tấm; chiều Bến Bạch Đằng – phố đi bộ Nguyễn Huệ; tối chợ Bến Thành và ốc – chè."),
            paragraph("Ngày 2: Bảo tàng Chứng tích Chiến tranh hoặc Bảo tàng Mỹ thuật; trưa bánh mì – cà phê; chiều Chợ Lớn – Bình Tây – các hội quán người Hoa; tối về Quận 1 ngắm thành phố từ rooftop."),
            paragraph("Ngày 3 (nếu có): Dạo Thảo Cầm Viên hoặc công viên bờ sông; brunch ở Thảo Điền; chiều săn ảnh hẻm tường hồng Tân Định; tối ăn đồ nướng vỉa hè và tản bộ kết màn."),
            heading("Di chuyển & lưu ý"),
            paragraph("Giờ cao điểm dễ kẹt xe; nên canh lịch hẹn trước 15–20 phút và chọn điểm đón trả rộng rãi khi gọi xe công nghệ; mưa nhiệt đới đến nhanh, hãy mang áo mưa mỏng/ô gấp; thanh toán phổ biến bằng tiền mặt và chuyển khoản, hỏi giá trước khi gọi món ở khu du lịch đông khách."),
            infoBox("Mẹo nhỏ: Tránh chen lấn trên phố đi bộ khi có sự kiện; mang giày thoải mái vì bạn sẽ đi bộ khá nhiều qua các điểm tham quan gần nhau; các khu ẩm thực đêm thường mở muộn – sau 19:00 là nhộn nhất."),
            quote("“Sài Gòn không ngủ.”"),
            divider(),
            mapBlock(new double[]{106.6602, 10.7626})
        ))
        .build();
  }

  private static PlaceDoc sapa() {
    String slug = "sa-pa";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name("Sa Pa")
        .slug(slug)
        .shortDescription("Thị trấn trên mây, ruộng bậc thang và đỉnh Fansipan hùng vĩ.")
        .description("Khí hậu mát mẻ quanh năm, trekking bản làng, săn mây, chụp ảnh ruộng bậc thang mùa lúa chín.")
        .addressLine("Thị xã Sa Pa, Lào Cai, Việt Nam")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.8440, 22.3350})
        .images(withCover(
            img("https://res.klook.com/image/upload/fl_lossy.progressive,q_60/Mobile/City/nab4excv9bkndhqnsyvl.jpg", "Thành phố Sa Pa", true, 0),
            img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463729618_ruong-bac-thang-2.jpg", "Ruộng bậc thang Sa Pa", false, 1),
            img("https://static.vinwonders.com/production/mua-san-may-sa-pa.jpg", "Mây phủ thung lũng", false, 2),
            img("https://nhahangchotinhsapa.vn/wp-content/uploads/2023/12/am-thuc-tay-bac.png", "Ẩm thực vùng cao", false, 3),
            img("http://reviewsapa.com/public/library/images/Du%20l%E1%BB%8Bch%20Sapa/du%20l%E1%BB%8Bch%20Sapa%20s%C4%83n%20m%C3%A2y%20v%C3%A0%20ho%C3%A0ng%20h%C3%B4n.jpg", "Hoàng hôn trên núi", false, 4),
            img("https://fansipanlegend.sunworld.vn/wp-content/uploads/2020/04/cap-treo-fansipan-legend_result.png", "Cáp treo Fansipan", false, 5),
            img("https://halosasapa.com/wp-content/uploads/2023/04/catcat.jpg", "Bản làng", false, 6)
        ))
        .content(List.of(
            heading("Sa Pa – chạm vào mây trời Tây Bắc"),
            quote("""
                Mây vờn qua đỉnh núi,
                bậc thang ôm trời xa;
                bước chân theo gió lạnh, nghe nắng đọng trên lá.
                """),
            paragraph("Sa Pa mang sắc thái núi rừng đặc trưng của Tây Bắc: không khí mát lạnh, sương sớm bảng lảng và những thửa ruộng bậc thang nối nhau như sóng. Thị trấn là điểm khởi hành lý tưởng cho chuyến trekking nhẹ, khám phá bản làng và trải nghiệm văn hóa các dân tộc H’Mông, Dao, Tày."),
            paragraph("Thời điểm được ưa chuộng: mùa lúa chín khoảng tháng 9–10 khi sườn đồi nhuộm vàng; mùa săn mây từ cuối thu đến cuối xuân khi thung lũng phủ lớp mây bồng bềnh; mùa hoa đỗ quyên trên dãy Hoàng Liên vào khoảng tháng 3–4."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/ve-dep-ruc-ro-cua-mua-vang-sapa-dep-nhu-buoc-ra-tu-tranh-ve-2-1663090680.jpg", "Bậc thang mùa vàng", false, 0)),
            heading("Trekking & trải nghiệm bản làng"),
            paragraph("Các cung dễ tiếp cận cho người mới: Cát Cát – Sin Chải, Y Linh Hồ – Lao Chải – Tả Van dọc thung lũng Mường Hoa; muốn thử thách hơn có thể chọn Tả Phìn hoặc Tả Giàng Phình. Thuê local guide giúp đi đúng lối, hiểu câu chuyện bản địa và chủ động phương án thời tiết."),
            paragraph("Khi vào bản, tôn trọng không gian sinh hoạt: xin phép trước khi chụp ảnh, hạn chế xả rác và ưu tiên mua sản phẩm thủ công địa phương như thổ cẩm, bạc chạm. Đường núi có đoạn trơn trượt sau mưa, nên mang giày trekking đế bám và áo mưa mỏng."),
            gallery(List.of(
                img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463729618_ruong-bac-thang-2.jpg", "Ruộng bậc thang mùa vàng", false, 0),
                img("https://static.vinwonders.com/production/mua-san-may-sa-pa.jpg", "Săn mây thung lũng", false, 1),
                img("https://fansipanlegend.sunworld.vn/wp-content/uploads/2020/04/cap-treo-fansipan-legend_result.png", "Cáp treo Fansipan", false, 2)
            )),
            heading("Fansipan & điểm ngắm cảnh"),
            paragraph("Đỉnh Fansipan có thể tiếp cận nhanh bằng cáp treo; thời tiết trên cao thay đổi nhanh, trời có thể lạnh sâu dù dưới thị trấn ấm áp, vì vậy chuẩn bị áo ấm, khăn gió, mũ trùm tai. Khung giờ sáng muộn đến trưa thường tầm nhìn đẹp hơn sau khi sương tan."),
            paragraph("Các điểm ngắm hoàng hôn – bình minh: đèo Ô Quy Hồ, thung lũng Mường Hoa, đồi Sun Plaza; ngày nhiều mây có thể đổi sang café view thung lũng để vừa trú gió vừa săn khoảng trời hửng nắng hiếm hoi."),
            heading("Ẩm thực vùng cao"),
            paragraph("Thử thắng cố, lợn bản nướng, cá suối, gà đồi, cải mèo xào và các món nướng đêm ở khu trung tâm. Đồ uống ấm như trà shan tuyết, rượu táo mèo giúp xua lạnh – uống có chừng mực để giữ sức cho chặng trekking hôm sau."),
            heading("Lịch trình gợi ý 2–3 ngày"),
            paragraph("Ngày 1: Dạo trung tâm thị trấn – nhà thờ đá – quảng trường; chiều tham quan Cát Cát; tối dạo phố ẩm thực và nghỉ sớm."),
            paragraph("Ngày 2: Trek Y Linh Hồ – Lao Chải – Tả Van dọc Mường Hoa; chiều về thị trấn tắm lá Dao đỏ; tối thưởng thức đồ nướng, ngắm thị trấn trong sương."),
            paragraph("Ngày 3 (nếu có): Cáp treo Fansipan săn mây; trưa quay lại trung tâm mua thổ cẩm – đặc sản; chiều ngắm Ô Quy Hồ rồi rời Sa Pa."),
            infoBox("Mẹo nhỏ: Nhiệt độ ngày đêm chênh lệch lớn – luôn mang áo ấm mỏng trong balo; mưa núi đến nhanh, bọc chống nước cho điện thoại/máy ảnh; đổi tiền lẻ trước khi vào bản để tiện mua hàng và ủng hộ dịch vụ nhỏ lẻ."),
            heading("Di chuyển & lưu ý"),
            paragraph("Địa hình dốc, sương mù dày vào tối/đêm – hạn chế tự lái nếu không quen đường núi; chọn xe ôm/bike taxi hoặc taxi địa phương cho các đoạn đèo. Tôn trọng tập quán địa phương, không cho trẻ em tiền kẹo; thay vào đó có thể mua sản phẩm của gia đình các em."),
            imageBlock(img("https://nhahangchotinhsapa.vn/wp-content/uploads/2023/12/am-thuc-tay-bac.png", "Ẩm thực vùng cao", false, 0)),
            quote("“Sa Pa – nơi ta chạm tay vào mây.”"),
            divider(),
            mapBlock(new double[]{103.8440, 22.3350})
        ))
        .build();
  }

  private static PlaceDoc nhaTrang() {
    String slug = "nha-trang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name("Nha Trang")
        .slug(slug)
        .shortDescription("Thiên đường biển Nam Trung Bộ với dải cát trắng và các vịnh đảo xanh.")
        .description("Tắm biển, lặn ngắm san hô, khám phá Hòn Mun, Hòn Tằm, tháp Bà Ponagar và ẩm thực hải sản.")
        .addressLine("TP. Nha Trang, Khánh Hòa, Việt Nam")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.1967, 12.2388})
        .images(withCover(
            img("https://puolotrip.com/images/news/bien-nha-trang-3-3532.jpg", "Nha Trang", true, 0),
            img("https://static.vinwonders.com/2022/03/lan-bien-nha-trang-thumb.jpg", "Lặn ngắm san hô", false, 1),
            img("https://dulichnhatrang365.vn/wp-content/uploads/2023/06/du-thuyen-1.png", "Hoàng hôn trên vịnh", false, 2),
            img("https://cdn.nhatrang79.com/uploads/Tour_hon_tam_nha_trang_1_dfae30523d.jpg", "Hòn đảo ngoài khơi", false, 3),
            img("https://statics.vinpearl.com/cho-hai-san-nha-trang--99_1628839999.jpg", "Hải sản tươi", false, 4),
            img("https://kongforest.com/FileStorage/content/images/thap-ba-ponagar-nha-trang-3.jpeg", "Tháp Bà Ponagar", false, 5)
        ))
        .content(List.of(
            heading("Nha Trang – nắng vàng, biển xanh, cát trắng"),
            quote("""
                Gió thổi nghiêng bờ cát,
                nắng tưới đầy vòm xanh;
                tiếng sóng gọi chân thành, ai đến rồi lưu luyến.
                """),
            paragraph("Nha Trang là điểm đến biển đặc trưng Nam Trung Bộ với dải cát trắng mịn, nước trong nhìn thấy đáy và chuỗi vịnh – đảo gần bờ; nhịp sống thong thả, dịch vụ đa dạng khiến nơi đây phù hợp cả nghỉ dưỡng gia đình lẫn các trải nghiệm thể thao biển."),
            heading("Các vịnh – đảo & trải nghiệm ngoài khơi"),
            paragraph("Từ trung tâm, bạn dễ dàng ra Hòn Mun, Hòn Tằm, Hòn Một bằng cano/du thuyền để lặn ngắm san hô, chèo SUP, đi bộ dưới đáy biển hoặc đơn giản là bơi và phơi nắng; nước êm vào buổi sáng, gió lên nhẹ tầm trưa – chiều nên lịch trình thường xuất phát sớm để tranh thủ mặt biển đẹp."),
            paragraph("Khi tham gia tour biển, hãy chọn đơn vị có áo phao, kính – ống thở đạt chuẩn, hướng dẫn an toàn trước khi xuống nước; không chạm, bẻ san hô và giữ khoảng cách với sinh vật biển; mang túi chống nước cho điện thoại – máy ảnh, bôi kem chống nắng thân thiện môi trường nếu có."),
            imageBlock(img("https://dulichnewtour.vn/ckfinder/images/Tours/biennhatrang/bien-nha-trang%20(2).jpg", "Biển trong xanh", false, 0)),
            heading("Văn hóa – di tích"),
            paragraph("Trong phố, tháp Bà Ponagar lưu dấu văn minh Chăm với kiến trúc gạch nung đặc sắc; nhà thờ Núi, viện Hải dương học, bảo tàng Yersin gợi mở một lát cắt lịch sử – khoa học của thành phố biển; xen kẽ là chợ Đầm, chợ Xóm Mới nơi bạn có thể cảm nhận nhịp buôn bán rộn ràng."),
            gallery(List.of(
                img("https://puolotrip.com/images/news/bien-nha-trang-3-3532.jpg", "Bờ biển Nha Trang", false, 0),
                img("https://static.vinwonders.com/2022/03/lan-bien-nha-trang-thumb.jpg", "Lặn ngắm san hô", false, 1),
                img("https://kongforest.com/FileStorage/content/images/thap-ba-ponagar-nha-trang-3.jpeg", "Tháp Bà Ponagar", false, 2)
            )),
            heading("Ẩm thực hải sản & món địa phương"),
            paragraph("Hải sản tươi theo ký là ngôi sao: tôm hùm, mực, ốc, cá mú… chế biến nướng, hấp, xào me tùy khẩu vị; món địa phương nên thử có bún chả cá, bún sứa, bánh căn, bánh xèo mực; buổi chiều dọc đường biển nhiều quán view hoàng hôn, lý tưởng để kết thúc một ngày trên vịnh."),
            heading("Lịch trình gợi ý 2–3 ngày"),
            paragraph("Ngày 1: Tắm biển trung tâm – chạy bộ dọc Trần Phú; chiều tham quan tháp Bà Ponagar – nhà thờ Núi; tối thưởng thức hải sản và dạo biển."),
            paragraph("Ngày 2: Tour đảo Hòn Mun – Hòn Tằm (snorkeling, tắm biển, chèo SUP); chiều spa bùn khoáng; tối đi chợ đêm hoặc ngắm biển đêm."),
            paragraph("Ngày 3 (nếu có): Viện Hải dương học – bảo tàng Yersin; trưa nhâm nhi bánh căn – bún sứa; chiều lên đồi ngắm toàn cảnh rồi rời Nha Trang."),
            heading("Thời điểm & lưu ý"),
            paragraph("Biển Nha Trang nhìn chung êm từ khoảng 3–8; các tháng giao mùa có thể có mưa gió – trước khi đi đảo hãy theo dõi dự báo và hướng dẫn của nhà tàu; mang nón rộng vành, áo khoác chống nắng, dép đi biển; luôn hỏi giá rõ ràng trước khi chọn hải sản – dịch vụ nước để tối ưu chi phí."),
            infoBox("Mẹo nhỏ: Khung giờ 7:00–10:00 nước trong, nắng chưa gắt rất hợp để lặn ngắm; buổi chiều săn hoàng hôn trên vịnh hoặc dọc bãi Trần Phú cho ánh sáng đẹp nhất."),
            quote("“Nắng vàng, biển xanh, cát trắng – Nha Trang gọi tên.”"),
            divider(),
            mapBlock(new double[]{109.1967, 12.2388})
        ))
        .build();
  }
}