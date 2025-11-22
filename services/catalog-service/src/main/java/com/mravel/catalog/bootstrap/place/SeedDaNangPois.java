package com.mravel.catalog.bootstrap.place;

import static com.mravel.catalog.utils.place.ContentBlocks.*;
import static com.mravel.catalog.utils.place.Images.*;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepository;

@Configuration
public class SeedDaNangPois {

  @Bean
  CommandLineRunner seedDaNangPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedDaNangPois] start");

      // 8 POI thuộc Đà Nẵng
      List<PlaceDoc> pois = List.of(
          poiMyKhe(),
          poiBaNaHills(),
          poiNguHanhSon(),
          poiCauRong(),
          poiSonTra(),
          poiChoHan(),
          poiAsiaPark(),
          poiBaoTangCham()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedDaNangPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedDaNangPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedDaNangPois] done, inserted = " + inserted);
    };
  }

  // ===================== POI FOR DA NANG =====================

  /** 1) Bãi biển Mỹ Khê */
  private static PlaceDoc poiMyKhe() {
    String slug = "bai-bien-my-khe";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bãi biển Mỹ Khê")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Bãi biển đẹp hàng đầu châu Á, nước trong xanh, cát mịn.")
        .description("Mỹ Khê nổi tiếng với dải bờ biển dài, hoạt động tắm biển, lướt ván, dù lượn. "
            + "Sáng sớm và chiều muộn là thời khắc đẹp nhất để dạo biển và chụp ảnh.")
        .addressLine("Võ Nguyên Giáp, Sơn Trà, Đà Nẵng, Việt Nam")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2479, 16.0603}) // [lon, lat]
        .images(withCover(
            img("https://alanidananghotel.com/uploads/images/bai-bien-da-nang-dep-nhat-hanh-tinh-1%20(1).jpg", "Biển Mỹ Khê", true, 0),
            img("https://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy3/image00103072017024441.jpg", "Cát trắng mịn", false, 1),
            img("https://r2.nucuoimekong.com/wp-content/uploads/bai-bien-my-khe.jpg", "Tắm biển Mỹ Khê", false, 2),
            img("https://viettourist.vn/wp-content/uploads/2017/04/3-diem-den-da-nang-304-157.jpg", "Toàn cảnh bãi biển", false, 3),
            img("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_863/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/c19hhp0awkm0cmzztj2k/L%E1%BB%9Bph%E1%BB%8Dcl%C6%B0%E1%BB%9Bts%C3%B3ngc%E1%BB%A7aSurfShack%E1%BB%9F%C4%90%C3%A0N%E1%BA%B5ng.jpg", "Lướt ván", false, 4),
            img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/the-thao-bien-my-khe.png", "Hoạt động biển", false, 5)
        ))
        .content(List.of(
            heading("Mỹ Khê – bãi biển biểu tượng của Đà Nẵng"),
            quote("""
                Sớm mai sóng vỗ nhẹ,
                nắng rót mật chân trời;
                cát mịn in dấu bước, biển xanh mở khoảng trời.
                """),
            paragraph("Từ trung tâm thành phố chỉ vài phút di chuyển, Mỹ Khê phù hợp cho tắm biển, chạy bộ, đạp xe dọc bờ và ngắm bình minh – hoàng hôn; dọc tuyến Võ Nguyên Giáp là chuỗi quán hải sản và quán cà phê nhìn thẳng ra biển, thuận tiện kết hợp thư giãn sau khi tắm."),
            heading("Thời điểm đẹp & thủy triều"),
            paragraph("Khung giờ lý tưởng thường là 05:30–08:30 khi nắng còn dịu, nước trong và ít gió; buổi chiều 16:30–18:00 thích hợp dạo biển, chụp ảnh hoàng hôn; những ngày gió to, sóng lớn hoặc có cảnh báo dòng chảy xa bờ (rip current) nên hạn chế xuống nước và ưu tiên các hoạt động trên bãi."),
            heading("Hoạt động biển"),
            paragraph("Bạn có thể bơi gần bờ, chơi SUP/kayak, lướt ván (theo mùa gió), dù lượn hoặc đơn giản là đi chân trần trên cát; nếu thuê dịch vụ, hãy kiểm tra áo phao, dây an toàn và hướng dẫn viên; nhớ khởi động kỹ trước khi xuống nước để hạn chế chuột rút."),
            heading("Dịch vụ & tiện ích"),
            paragraph("Các lối xuống biển có bãi gửi xe, vòi tắm tráng, phòng thay đồ; khu vực chính thường có lực lượng cứu hộ trực ca theo giờ; dọc bãi là hàng quán phục vụ nước mát, dừa tươi, hải sản; hỏi giá rõ ràng trước khi sử dụng dịch vụ để trải nghiệm thoải mái."),
            imageBlock(img("https://static.vinwonders.com/2022/04/bai-bien-my-khe-da-nang-2.jpg", "Bãi biển Mỹ Khê buổi sớm", false, 0)),
            gallery(List.of(
                img("https://danangfantasticity.com/wp-content/uploads/2024/03/chiem-nguong-bien-my-khe-1-trong-10-bai-bien-dep-nhat-chau-a-01.jpg", "Sóng nhẹ", false, 0),
                img("https://r2.nucuoimekong.com/wp-content/uploads/bien-my-khe-o-dau.jpg", "Check-in bãi biển", false, 0),
                img("https://cuongdulich.com/assets/posts/1574066363-du-lich-bien-My-Khe-17.jpg", "Bờ biển dài", false, 0)
            )),
            heading("Lịch trình gợi ý 1 buổi"),
            paragraph("Bình minh: ra bãi lúc 05:30, khởi động – tắm biển 30–45 phút, dạo dọc bờ chụp ảnh, lên tắm tráng rồi ăn sáng hải sản/miến cá gần bãi; hoàng hôn: tắm nhẹ hoặc đi dạo, chụp nắng cuối ngày, sau đó ngồi cà phê sát biển ngắm phố lên đèn."),
            infoBox("Mẹo nhỏ: Dùng kem chống nắng, mang nón rộng vành – kính mát, uống đủ nước; dùng túi chống nước cho điện thoại khi xuống nước; tránh mang theo đồ quý giá khi tắm."),
            heading("Lưu ý an toàn"),
            paragraph("Quan sát cờ cảnh báo: cờ đỏ – không xuống biển, cờ vàng – thận trọng; không bơi xa phao giới hạn, không xuống nước sau khi uống rượu bia; mùa sứa có thể xuất hiện – nếu châm chích, lên bờ rửa nước biển sạch vùng da, theo dõi phản ứng và liên hệ cứu hộ khi cần."),
            quote("“Cát trắng, nắng vàng, biển xanh – đủ đầy cho một ngày thư giãn.”"),
            divider(),
            mapBlock(new double[]{108.2479, 16.0603})
        ))
        .build();
  }

  /** 2) Bà Nà Hills & Cầu Vàng */
  private static PlaceDoc poiBaNaHills() {
    String slug = "ba-na-hills-cau-vang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bà Nà Hills & Cầu Vàng")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Khu du lịch trên núi với khí hậu mát mẻ, nổi bật Cầu Vàng độc đáo.")
        .description("Bà Nà Hills mang phong cách châu Âu, có làng Pháp, hầm rượu, vườn hoa Le Jardin, "
            + "trò chơi giải trí và đặc biệt là Cầu Vàng ‘bàn tay’ nổi tiếng.")
        .addressLine("Thôn An Sơn, Hòa Ninh, Hòa Vang, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{107.9963, 15.9996})
        .images(withCover(
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng – Bà Nà Hills", true, 0),
            img("https://motogo.vn/wp-content/uploads/2019/11/11-toan-canh-ba-na-1554385320042306734627.jpg", "Toàn cảnh Bà Nà", false, 1),
            img("https://digiticket.vn/blog/wp-content/uploads/2022/03/lang-phap-ba-na-hills-1.png", "Làng Pháp", false, 2),
            img("https://nhuminhplazahotel.com/wp-content/uploads/2023/08/cach-tao-dang-chup-anh-o-cau-vang-1.jpg", "Check-in Cầu Vàng", false, 3),
            img("https://thanhnien.mediacdn.vn/Uploaded/2014/Pictures20132/Thanh/1/CaptreoBaNa.jpg", "Cáp treo Bà Nà", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2025/01/kham-pha-le-hoi-hoa-ba-na-2025-03.jpg", "Vườn hoa", false, 5)
        ))
        .content(List.of(
            heading("Bà Nà Hills – bốn mùa trong một ngày"),
            quote("""
                Mây ôm vai đỉnh núi,
                gió hát khúc bình yên;
                nắm tay qua Cầu Vàng, trời gần hơn một chút.
                """),
            paragraph("Tọa lạc trên dãy Trường Sơn cách trung tâm Đà Nẵng khoảng 30 km, Bà Nà Hills gây ấn tượng bởi khí hậu mát mẻ, cảnh quan đổi sắc theo giờ và quần thể kiến trúc lấy cảm hứng châu Âu; trải nghiệm đặc trưng là đi cáp treo xuyên tầng mây, dạo bộ giữa những quảng trường lát đá, ghé vườn hoa, hầm rượu, bảo tàng sáp và không thể thiếu khoảnh khắc check-in trên Cầu Vàng nổi danh."),
            paragraph("Để có trải nghiệm thoải mái, bạn nên xuất phát sớm từ thành phố, đến ga cáp trước giờ mở cửa một chút để hạn chế xếp hàng; lên cao nhiệt độ giảm nhanh, trời có thể nắng rực rồi mù sương chỉ trong vài chục phút nên hãy mặc lớp áo mỏng dễ tháo, mang theo áo mưa gọn nhẹ và che phủ cho máy ảnh, điện thoại khi băng qua những đoạn sương ẩm."),
            imageBlock(img("https://cdnphoto.dantri.com.vn/XCxAd-hWfz0Kj0RrTDGY3filKsY=/thumb_w/1360/2023/08/24/caudocx-1692878010777.jpeg", "Cầu Vàng biểu tượng", false, 0)),
            heading("Cầu Vàng & những góc sống ảo"),
            paragraph("Cầu Vàng nằm ở độ cao hơn 1.400 m, nổi bật với hai “bàn tay” đá nâng nhịp cầu cong mềm giữa thung mây; khung giờ đẹp thường là sáng sớm hoặc chiều muộn khi nắng xiên và khách chưa quá đông, bạn có thể đi vòng một lượt để chọn góc nhìn về phía rừng núi hoặc hướng thung lũng; khi có sương dày, ảnh sẽ mang sắc huyền ảo, còn lúc trời trong vắt thì màu xanh của núi rừng nổi bật hẳn lên."),
            heading("Làng Pháp, vườn hoa & hầm rượu"),
            paragraph("Khu làng Pháp tái hiện quảng trường, tháp chuông, mái ngói đá và các con dốc lát đá mang âm hưởng Trung cổ, phù hợp tản bộ chậm rãi và ghé những ban công nhìn xuống thung lũng; Le Jardin d’Amour gồm nhiều khu vườn theo chủ đề với hoa theo mùa, kết hợp những mảng tường cây và tượng trang trí tạo chiều sâu cho khung hình; hầm rượu Debay làm mát tự nhiên, lý tưởng để dừng chân, đọc vài bảng tư liệu rồi tiếp tục hành trình lên khu đỉnh."),
            heading("Cáp treo & thời tiết"),
            paragraph("Hệ thống cáp treo đưa bạn băng qua rừng nguyên sinh, thác nước và mây tầng; khi cabin vào vùng gió, cảm giác rung nhẹ là bình thường nhưng hãy hạn chế đứng lên thay đổi vị trí đột ngột; trên đỉnh thường mát hơn trung tâm thành phố từ 6–10°C, độ ẩm cao khiến mặt đá trơn, vì vậy giày đế bám và áo khoác mỏng rất hữu ích; mùa khô nắng gắt nhưng tia UV cao, vẫn cần kem chống nắng và mũ rộng vành."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng giữa mây", false, 0)),
            gallery(List.of(
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng giữa mây", false, 0),
                img("https://digiticket.vn/blog/wp-content/uploads/2022/03/lang-phap-ba-na-hills-1.png", "Làng Pháp châu Âu", false, 1),
                img("https://thanhnien.mediacdn.vn/Uploaded/2014/Pictures20132/Thanh/1/CaptreoBaNa.jpg", "Tuyến cáp treo", false, 2)
            )),
            heading("Vui chơi & lịch sự kiện"),
            paragraph("Khu giải trí trong nhà có nhiều trò phù hợp gia đình, từ nhà gương, trượt ống, game tương tác đến khu trẻ em; quảng trường thường xuyên có màn trình diễn đường phố, carnival, hoặc lễ hội hoa theo mùa ở Le Jardin; lịch sự kiện thay đổi theo thời điểm nên bạn có thể tham khảo bảng thông tin tại cổng khu hoặc ngay tại quảng trường trung tâm để sắp xếp lộ trình hợp lý."),
            heading("Lịch trình gợi ý 1 ngày"),
            paragraph("Sáng: đến ga sớm, đi cáp lên khu Cầu Vàng khi nắng còn dịu để chụp ảnh và dạo bộ qua rìa rừng; giữa sáng: tham quan vườn hoa Le Jardin, hầm rượu Debay, dừng café ngắm thung lũng; trưa: dùng bữa tại khu làng Pháp hoặc khu ẩm thực gần quảng trường; chiều: ghé bảo tàng sáp, vui chơi trong nhà khi trời nắng gắt hoặc mưa bất chợt; cuối chiều: quay lại khu Cầu Vàng hoặc điểm nhìn thoáng để săn ánh hoàng hôn trước khi xuống cáp."),
            heading("An toàn & lưu ý"),
            paragraph("Đi lại trên các bậc đá hãy bám tay vịn, tránh chạy nhảy khi mặt đá ẩm; giữ khoảng cách khi chụp ảnh ở mép lan can; mang theo ít nhất một chai nước nhỏ, tận dụng các khu nghỉ giữa tuyến để điều chỉnh nhịp di chuyển cho trẻ em và người lớn tuổi; khi sương quá dày hoặc mưa lớn, ưu tiên các điểm trong nhà rồi quay lại khu ngoài trời khi thời tiết ổn hơn."),
            infoBox("Mẹo nhỏ: Mang áo khoác mỏng, kem chống nắng, nón và giày đế bám; kiểm tra dự báo thời tiết trước khi đi và xuất phát sớm để tận dụng khung giờ vắng; sạc đầy pin máy ảnh/điện thoại và chuẩn bị túi chống ẩm cho thiết bị."),
            imageBlock(img("https://nhuminhplazahotel.com/wp-content/uploads/2023/08/cach-tao-dang-chup-anh-o-cau-vang-1.jpg", "Check-in Cầu Vàng", false, 0)),
            quote("“Lên Bà Nà một ngày, đổi bốn mùa thời tiết.”"),
            divider(),
            mapBlock(new double[]{107.9963, 15.9996})
        ))
        .build();
  }

  /** 3) Ngũ Hành Sơn (Marble Mountains) */
  private static PlaceDoc poiNguHanhSon() {
    String slug = "ngu-hanh-son";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Ngũ Hành Sơn")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Cụm núi đá vôi gần biển với hang động, chùa chiền và làng đá mỹ nghệ.")
        .description("Gồm 5 ngọn núi Kim, Mộc, Thủy, Hỏa, Thổ. Nổi bật động Huyền Không, chùa Linh Ứng Non Nước. "
            + "Có làng nghề đá mỹ nghệ truyền thống dưới chân núi.")
        .addressLine("81 Huyền Trân Công Chúa, Ngũ Hành Sơn, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2639, 16.0031})
        .images(withCover(
            img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Toàn cảnh Ngũ Hành Sơn", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/da-den-da-nang-thi-phai-oanh-tac-5-ngon-nui-o-ngu-hanh-son-1636215280.jpg", "Các ngọn núi", false, 1),
            img("http://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy4/lac-vao-tien-canh-noi-dong-huyen-khong-trong-day-ngu-hanh-son-241-09053814112017035012.jpg", "Động Huyền Không", false, 2),
            img("https://vietnamtravelcar.com.vn/wp-content/uploads/2025/04/chua-non-nuoc-da-nang-4.png", "Chùa Linh Ứng Non Nước", false, 3),
            img("https://www.phanthien.com/tin-tuc/images/tin-tuc-gioi-thieu/lang-da-non-nuoc-3.jpg", "Làng đá Non Nước", false, 4),
            img("https://cdn.tgdd.vn/Files/2021/07/13/1367741/kham-pha-ngu-hanh-son-thang-canh-noi-tieng-tai-da-nang-202202171027387108.jpg", "Lối lên núi", false, 5)
        ))
        .content(List.of(
            heading("Ngũ Hành Sơn – linh sơn gần biển"),
            quote("""
                Sóng ru chân núi biếc,
                đá giữ tiếng chuông ngân;
                lối động vờn tia nắng, khói trầm quyện bước chân.
                """),
            paragraph("Cụm Ngũ Hành Sơn gồm năm ngọn Kim, Mộc, Thủy, Hỏa, Thổ trồi lên giữa dải cát ven biển, tạo nên một quần thể vừa hùng vĩ vừa gần gũi; chỉ cách trung tâm Đà Nẵng không xa, nơi đây thích hợp cho chuyến leo nhẹ kết hợp tham quan hang động, chùa chiền và chiêm nghiệm vẻ đẹp đá – gió – biển giao hòa."),
            heading("Khám phá tuyến Thủy Sơn"),
            paragraph("Thủy Sơn là ngọn lớn nhất và dễ tham quan nhất với hệ thống bậc đá, thang máy và các lối mòn dẫn lên nhiều đài vọng cảnh; từ đây bạn có thể nhìn dọc bờ biển Võ Nguyên Giáp, thấy rõ các dải cát cong ôm sóng và khu dân cư bên dưới; bề mặt đá ẩm rêu dễ trơn nên hãy đi giày đế bám, mang theo chai nước nhỏ và giữ nhịp di chuyển ổn định."),
            imageBlock(img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/ngu-hanh-son-da-nang2.jpg", "Ngũ Hành Sơn nhìn từ trên cao", false, 0)),
            heading("Động Huyền Không & chùa Linh Ứng Non Nước"),
            paragraph("Động Huyền Không nổi bật bởi giếng trời phóng tia sáng xiên vào lòng hang, tạo hiệu ứng khói trầm bảng lảng rất đặc trưng; khi mắt quen tối bạn sẽ thấy các vách đá mang vân tự nhiên như những nếp gấp thời gian; gần đó là chùa Linh Ứng Non Nước trầm mặc, chuông ngân lan qua thung đá; khi vào không gian thờ tự nên ăn mặc lịch sự, giữ im lặng và đi theo lối chỉ dẫn."),
            gallery(List.of(
                img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Toàn cảnh Ngũ Hành Sơn", false, 0),
                img("http://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy4/lac-vao-tien-canh-noi-dong-huyen-khong-trong-day-ngu-hanh-son-241-09053814112017035012.jpg", "Động Huyền Không", false, 1),
                img("https://www.phanthien.com/tin-tuc/images/tin-tuc-gioi-thieu/lang-da-non-nuoc-3.jpg", "Làng đá Non Nước", false, 2)
            )),
            heading("Làng đá Non Nước & trải nghiệm thủ công"),
            paragraph("Dưới chân núi là làng nghề đá Non Nước với lịch sử lâu đời; bạn có thể quan sát nghệ nhân đục đẽo, mài giũa từng chi tiết tượng, bình, đồ trang sức; nếu mua làm quà hãy ưu tiên kích thước vừa phải để tiện mang theo, kiểm tra cạnh mài nhẵn, độ cân đối và hỏi rõ cách đóng gói vận chuyển; nhiều cơ sở có dịch vụ in khắc tên lên mặt đá trong ngày."),
            heading("Lịch trình gợi ý 1/2 ngày"),
            paragraph("Sáng đi sớm để tránh nắng: mua vé, lên Thủy Sơn theo lối bậc đá hoặc thang máy, dừng ở các vọng đài chụp ảnh toàn cảnh biển, vào động Huyền Không và các am nhỏ; giữa buổi ghé chùa Linh Ứng Non Nước, nghỉ ở sân chùa; cuối buổi xuống chân núi tham quan làng đá và chọn vài món lưu niệm trước khi trở lại trung tâm."),
            heading("An toàn & lưu ý"),
            paragraph("Mặt đá có thể ẩm trơn sau mưa, đi chậm bám tay vịn; mang theo nón, kem chống nắng và một chai nước nhỏ; trong hang tối nên chú ý bậc lên xuống và nhường lối ở các đoạn hẹp; tôn trọng không gian tôn giáo, không chạm khắc lên đá hay để lại rác; nếu đi cùng trẻ nhỏ, luôn nắm tay tại khu vực bậc cao và lan can thấp."),
            quote("“Núi gần biển, chùa trong đá – linh khí hội tụ.”"),
            divider(),
            mapBlock(new double[]{108.2639, 16.0031})
        ))
        .build();
  }

  /** 4) Cầu Rồng & phun lửa/cứa nước */
  private static PlaceDoc poiCauRong() {
    String slug = "cau-rong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Cầu Rồng")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Biểu tượng Đà Nẵng bắc qua sông Hàn, trình diễn phun lửa – phun nước cuối tuần.")
        .description("Cầu Rồng nổi bật với kiến trúc rồng vàng uốn lượn. Thường phun lửa/nước vào tối thứ 7, CN, "
            + "thu hút đông đảo du khách dọc bờ sông Hàn.")
        .addressLine("Đường Võ Văn Kiệt – Nguyễn Văn Linh, Hải Châu, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2269, 16.0613})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng về đêm", true, 0),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/06/07/17/03/w1230/cau1749269023_3019.jpg", "Phun lửa", false, 1),
            img("https://www.vietfuntravel.com.vn/image/data/Da-Nang/cau-rong-da-nang/Lich-phun-lua-cau-rong-cau-rong-phun-lua-luc-may-gio-h4.jpg", "Phun nước", false, 2),
            img("https://cdn.vntour.com.vn/storage/media/img/2019/07/29/ket-cau-cau-rong_1564395041.jpg", "Cận cảnh kiến trúc", false, 3),
            img("https://danangfantasticity.com/wp-content/uploads/2018/10/cau-rong-top-20-cay-cau-ky-quai-nhat-the-gioi-theo-boredom-therapy-02.jpg", "View sông Hàn", false, 4),
            img("https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/12_2019/cau-rong-da-nang-2.jpg", "Check-in cuối tuần", false, 5)
        ))
        .content(List.of(
            heading("Cầu Rồng – biểu tượng hiện đại trên sông Hàn"),
            quote("""
                    Rồng vươn qua bến gió,
                    lửa vẽ nét đêm vàng;
                    sương nước chao mặt sóng,
                    tiếng người vỡ thành trang.
                """),
            paragraph("Cây cầu mang dáng rồng uốn lượn này nối bờ Đông – bờ Tây, ban ngày sắc vàng nổi bật giữa trời xanh, ban đêm chuyển mình dưới dải LED rực rỡ; cuối tuần, màn trình diễn phun lửa – phun nước biến bờ sông Hàn thành sân khấu mở, kéo người dân và du khách tụ về tạo nên không khí rất “Đà Nẵng”: trẻ trung, trật tự, thân thiện."),
            heading("Kinh nghiệm xem phun lửa – phun nước"),
            paragraph("Thời điểm trình diễn thường rơi vào tối thứ Bảy và Chủ nhật, khung giờ khoảng 21:00 (có thể thay đổi theo thông báo của thành phố); nên đến sớm 20–30 phút để chọn vị trí đứng; mặt gió và góc gần đầu rồng sẽ xem rõ hơn nhưng dễ ướt khi phun nước, hãy bọc máy ảnh/điện thoại bằng túi chống nước mỏng; nếu đi cùng trẻ nhỏ, đứng lùi vài mét để tránh hơi nóng lúc phun lửa."),
            paragraph("Các điểm đứng phổ biến: bờ Đông dọc đường Trần Hưng Đạo (gần cầu Tình Yêu – Cá chép hóa Rồng) cho khung hình “đầu rồng – phố đèn”; bờ Tây trên đường Bạch Đằng cho góc rộng ôm trọn cầu và sông; với người thích phơi sáng, có thể chọn xa hơn về phía cầu sông Hàn để gom ánh đèn thành vệt mượt."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng rực rỡ về đêm", false, 0)),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Khung hình cận: lấy nét ở đầu rồng, canh lúc tia lửa bung mạnh hoặc tia nước rơi thành màn sương; khung hình rộng: đặt đường chân trời ở 1/3, để cầu kéo mắt theo chiều ngang; có thể khai thác phản chiếu trên mặt nước hoặc các vũng nước ven bờ sau mưa; thời gian “giờ xanh” trước 19:00–19:15 (mùa hè) cho bầu trời chuyển sắc rất đẹp."),
            paragraph("Thiết bị: điện thoại bật Night mode, giữ máy vững; máy ảnh nên mang tripod nhỏ, tốc 1/10–1/2s cho vệt nước mềm; luôn chú ý an toàn người và thiết bị trước đám đông, tránh đứng sát mép kè."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Ánh sáng phủ vàng thân rồng", false, 0),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/06/07/17/03/w1230/cau1749269023_3019.jpg", "Khoảnh khắc phun lửa", false, 1),
                img("https://www.vietfuntravel.com.vn/image/data/Da-Nang/cau-rong-da-nang/Lich-phun-lua-cau-rong-cau-rong-phun-lua-luc-may-gio-h4.jpg", "Màn phun nước làm mát đêm hè", false, 2)
            )),
            heading("Đi bộ bờ Đông – bờ Tây & điểm ghé gần kề"),
            paragraph("Bờ Đông (Trần Hưng Đạo) có lối dạo bộ, tượng Cá chép hóa Rồng, chợ đêm Sơn Trà; bờ Tây (Bạch Đằng) là tuyến phố đi bộ kết hợp công viên APEC, nhiều quán cà phê và góc ngắm sông; bạn có thể bắt đầu từ công viên APEC, men theo Bạch Đằng lên chân cầu, qua cầu sang bờ Đông, dừng ở cầu Tình Yêu và quay lại theo đường Võ Văn Kiệt."),
            heading("Giao thông & lưu ý an toàn"),
            paragraph("Vào khung giờ trình diễn, thường có hạn chế/điều tiết giao thông trên cầu; tuân thủ hướng dẫn của lực lượng chức năng, không tụ tập giữa lòng đường; giữ gìn trật tự, không xả rác, không trèo qua lan can; nếu trời mưa gió, chất lượng màn trình diễn có thể rút gọn hoặc hoãn – bạn nên linh hoạt kế hoạch."),
            quote("“Đến Đà Nẵng, ngắm Rồng thở lửa – để thấy một thành phố đang thở cùng sông gió.”"),
            divider(),
            mapBlock(new double[]{108.2269, 16.0613})
        ))
        .build();
  }

  /** 5) Bán đảo Sơn Trà – Đỉnh Bàn Cờ & Chùa Linh Ứng */
  private static PlaceDoc poiSonTra() {
    String slug = "ban-dao-son-tra";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bán đảo Sơn Trà")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Lá phổi xanh của Đà Nẵng, có Đỉnh Bàn Cờ, Chùa Linh Ứng, đường ven biển đẹp.")
        .description("Sơn Trà phù hợp chạy xe ngắm cảnh, săn mây ở Đỉnh Bàn Cờ, thăm tượng Quan Âm ở chùa Linh Ứng "
            + "và khám phá các bãi biển hoang sơ như Bãi Bụt, Bãi Đá Đen.")
        .addressLine("Thọ Quang, Sơn Trà, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2752, 16.1146})
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2021/Thang_5/son_tra.jpeg", "Toàn cảnh Sơn Trà", true, 0),
            img("https://statics.vinpearl.com/dinh-ban-co-2_1629274112.jpg", "Đỉnh Bàn Cờ", false, 1),
            img("https://tptravel.com.vn/mediacenter/media/images/1850/news/ava/s900_0/top-30-dia-diem-khong-the-bo-qua-khi-du-lich-da-nang-chua-linh-ung-1525752347.png", "Chùa Linh Ứng", false, 2),
            img("https://cdn.nhandan.vn/images/7e998c4a71a4f777f488783c8e3c221553e525b23293623350984c0effb056d2f5d1850352abbb9f167445a5076ae533e667d376bf869970b83bd2a9ea12e0ea/ndo_br_2-copy.jpg", "Cung đường ven biển", false, 3),
            img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/du-lich-bai-but-da-nang.jpg", "Bãi Bụt", false, 4),
            img("https://nld.mediacdn.vn/291774122806476800/2024/2/7/cay-da-2-17073189149831055179085.jpg", "Rừng già Sơn Trà", false, 5)
        ))
        .content(List.of(
            heading("Sơn Trà – góc nhìn biển và rừng"),
            quote("""
                    Mây quấn đầu non biếc,
                    sóng vỗ mặt kè xa;
                    một bước nghiêng vào gió,
                    rừng chạm tiếng hải hà.
                """),
            paragraph("Bán đảo Sơn Trà là “lá phổi xanh” của thành phố: một bên rừng nguyên sinh, một bên biển xanh ôm theo cung đường uốn lượn. Đường rộng, dốc nối dốc, nhiều cua gắt; trước khi đi nên kiểm tra phanh, lốp, đổ đủ xăng và xem dự báo thời tiết. Tránh chạy nhanh ở các khúc có cát/đá rơi, giữ khoảng cách với xe trước và nhường đường cho phương tiện leo dốc."),
            heading("Đỉnh Bàn Cờ – săn mây, đón gió"),
            paragraph("Đỉnh Bàn Cờ là điểm cao dễ tiếp cận để ngắm toàn cảnh vịnh Đà Nẵng. Đẹp nhất là bình minh (mùa khô 05:15–06:00) khi tầng mây mỏng lẫn ánh hồng; giữa trưa thường oi và chói, ảnh dễ gắt. Mang áo gió mỏng, nước uống nhỏ gọn, giày đế bám; trời mưa sương dày, tầm nhìn kém thì cân nhắc không lên."),
            imageBlock(img("https://static.vinwonders.com/2022/06/dinh-Ban-Co-thumb.jpg", "Đỉnh Bàn Cờ săn mây", false, 0)),
            heading("Chùa Linh Ứng – tượng Quan Âm nhìn biển"),
            paragraph("Khu chùa Linh Ứng tọa lạc trên triền núi nhìn thẳng ra biển, nổi bật với tượng Quan Âm cao sừng sững. Khi vào không gian thờ tự, giữ trang phục lịch sự, đi nhẹ nói khẽ, không leo trèo lên bậc/lan can để chụp ảnh. Khu vực gửi xe có người trông, nên khóa cổ và lấy vé rõ ràng."),
            heading("Bãi đẹp & dừng chân"),
            paragraph("Bãi Bụt gần chùa, nước trong, lặng gió thì thích hợp tắm và chèo SUP; Bãi Đá Đen xa và hoang sơ hơn, mặt đá trơn cần giày đế bám, tuyệt đối không nhảy từ mỏm cao. Vào mùa gió chướng, sóng mạnh và dòng chảy xiết – theo dõi khuyến cáo an toàn trước khi xuống nước."),
            heading("Voọc chà vá chân nâu & quy tắc ứng xử"),
            paragraph("Sơn Trà là nhà của voọc chà vá chân nâu – “nữ hoàng linh trưởng”. Nếu gặp đàn voọc, giữ khoảng cách, tắt máy ngắm yên lặng, không cho ăn hay dùng đèn flash; rác mang vào – mang ra, tránh mở nhạc lớn. Việc cho động vật hoang dã ăn sẽ làm chúng lệ thuộc và gây rủi ro sức khỏe."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2021/Thang_5/son_tra.jpeg", "Toàn cảnh Sơn Trà", false, 0),
                img("https://statics.vinpearl.com/dinh-ban-co-2_1629274112.jpg", "Đỉnh Bàn Cờ săn mây", false, 1),
                img("https://tptravel.com.vn/mediacenter/media/images/1850/news/ava/s900_0/top-30-dia-diem-khong-the-bo-qua-khi-du-lich-da-nang-chua-linh-ung-1525752347.png", "Chùa Linh Ứng", false, 2)
            )),
            heading("Cung đường ven biển & góc chụp"),
            paragraph("Từ đường Hoàng Sa ôm sát mép nước đến dốc lên rừng là chuỗi góc chụp “biển – đường – vệt xe” rất đã. Khung giờ vàng 16:30–17:30 cho ánh xiên ấm; đặt đường chân trời ở 1/3 khung hình, tận dụng lan can/kè đá làm đường dẫn; sau mưa, phản chiếu trên vũng nước giúp ảnh nổi khối."),
            heading("Gợi ý lộ trình 1/2 ngày"),
            paragraph("Xuất phát trung tâm → đường Võ Nguyên Giáp – Hoàng Sa → dừng Chùa Linh Ứng vãn cảnh → tiếp tục lên Đỉnh Bàn Cờ săn mây, nghỉ ngắn → xuống Bãi Bụt tắm biển/ăn nhẹ → men cung đường ven biển ngắm hoàng hôn rồi quay về. Nếu có thêm thời gian, vòng tiếp qua cây đa di sản và các vọng cảnh dọc tuyến."),
            heading("An toàn & lưu ý"),
            paragraph("Đường núi nhiều khúc khuỷu, luôn chạy số phù hợp, không thả dốc dài; trời mưa sương dễ trơn – giảm tốc và bật đèn xe; sóng/giật cảnh báo đỏ thì không xuống nước; hạn chế dùng drone gần khu bảo tồn; giữ gìn vệ sinh, tuyệt đối không xả rác hay bẻ cành. Sóng điện thoại có nơi yếu – báo trước lịch trình với bạn đồng hành."),
            quote("“Một bên rừng, một bên biển – đứng ở Sơn Trà như đứng giữa màu xanh của thành phố.”"),
            divider(),
            mapBlock(new double[]{108.2752, 16.1146})
        ))
        .build();
  }

  /** 6) Chợ Hàn */
  private static PlaceDoc poiChoHan() {
    String slug = "cho-han";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Chợ Hàn")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Khu chợ lâu đời giữa trung tâm, nổi tiếng đặc sản Đà Nẵng.")
        .description("Bạn có thể tìm thấy nhiều đặc sản như mực khô, tré, bò khô, bánh khô mè… cùng quần áo, "
            + "đồ lưu niệm. Thương lượng giá nhẹ nhàng để có trải nghiệm vui.")
        .addressLine("119 Trần Phú, Hải Châu 1, Hải Châu, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2236, 16.0682})
        .images(withCover(
            img("https://danangfantasticity.com/wp-content/uploads/2025/04/trinh-cho-han-thuong-hieu-ca-phe-da-nang-giua-long-cho-truyen-thong-06.jpg", "Cổng chợ Hàn", true, 0),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/27/w1230/cho1753090033_0256.jpg", "Không gian bên trong", false, 1),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/39/w1230/cho1753090781_5709.jpg", "Quầy đặc sản", false, 2),
            img("https://statics.vinpearl.com/cho-han-da-nang-16_1637569200.jpg", "Khu bán đồ khô", false, 3),
            img("https://static.vinwonders.com/2022/03/cho-han-da-nang-10.jpg", "Quầy hải sản", false, 4),
            img("https://images2.thanhnien.vn/528068263637045248/2023/5/11/anh-cho-han-dong-kin-du-khach-han-quoc-3-1683792617237973849935.jpg", "Khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Chợ Hàn – góc đặc sản giữa lòng Đà Nẵng"),
            quote("""
                    Sáng mở hàng rộn rã,
                    mùi nắng ươm hương mè;
                    bước chợ nghe tiếng gọi,
                    tay gói chút mang về.
                """),
            paragraph("Nằm giữa trung tâm, chợ Hàn gói trọn nhịp sống địa phương: lối đi ken quầy, giọng rao lanh lảnh, sắc màu đặc sản bày ngay ngắn. Đi sớm buổi sáng không khí mát, hàng hóa mới; trưa đông khách dễ mệt và khó thử hàng. Mang theo túi vải hoặc balo trống để xếp đồ gọn, nhớ chuẩn bị tiền lẻ."),
            heading("Khu hàng khô & đặc sản mang về"),
            paragraph("Các quầy mực khô, cá rim, bò khô, tré, bánh khô mè… thường cho thử/đóng gói hút chân không. Trước khi mua: hỏi rõ nguồn gốc, hạn dùng, trọng lượng tịnh; so sánh 2–3 quầy gần nhau về độ dày lát, độ khô, mùi. Hút chân không giúp giữ vị và tiện xếp vali; với hàng có nước sốt, yêu cầu chống rò bằng lớp bọc kép."),
            heading("Mẹo trả giá & ứng xử"),
            paragraph("Giá thường có biên độ thương lượng nhẹ, nhất là khi mua nhiều: mỉm cười hỏi giá, xin “giá tốt khi lấy 2–3 phần”, tránh mặc cả quá sâu làm mất vui. Khi thử món, lấy lượng nhỏ, không làm rơi vãi; nếu không mua, cảm ơn nhẹ nhàng rồi di chuyển."),
            heading("Hải sản – quần áo – quà lưu niệm"),
            paragraph("Khu hải sản tươi/khô tỏa mùi mạnh; chọn quầy sạch, sàn khô, bao tay gọn gàng. Khu quần áo – vải vóc thuận tiện tìm đồ mỏng nhẹ đi biển; kiểm tra đường may, bảng size và chính sách đổi. Quà lưu niệm như nam châm, móc khóa, đồ có hình cầu Rồng/biển Đà Nẵng thích hợp làm quà số lượng lớn."),
            heading("Đóng gói – vận chuyển"),
            paragraph("Hỏi gian hàng về combo quà tặng/giỏ quà, cân nặng sau hút chân không và kích thước để sắp vali. Nếu gửi xa: nhờ bọc thêm lớp xốp/bong bóng, dán nhãn “hàng khô” và tờ hướng dẫn bảo quản. Tránh mua quá tay đồ nặng mùi nếu ở chung ký túc/xe khách."),
            heading("Ăn nhẹ trong chợ"),
            paragraph("Khu ẩm thực có mì Quảng, bún chả cá, bánh tráng cuốn thịt heo, chè… Chọn quầy đông nhưng xoay vòng nhanh, gọi suất nhỏ để thử được nhiều món. Nhớ lau tay trước khi ăn, giữ bàn ghế gọn sạch."),
            heading("Thanh toán & lưu ý an toàn"),
            paragraph("Đa phần nhận tiền mặt; nhiều quầy hỗ trợ chuyển khoản/QR. Giữ ví – điện thoại ở ngăn trước, đeo balo ra phía trước khi đông khách. Kiểm hàng và hóa đơn trước khi rời quầy; với đồ khô, xin tờ giấy ghi rõ loại hàng, hạn dùng để tiện theo dõi."),
            imageBlock(img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2023/3/15/1158152/Nang-Cap-Cho-Han-7.jpg", "Lối vào chợ Hàn", false, 0)),
            gallery(List.of(
                img("https://danangfantasticity.com/wp-content/uploads/2025/04/trinh-cho-han-thuong-hieu-ca-phe-da-nang-giua-long-cho-truyen-thong-06.jpg", "Cổng chợ Hàn", false, 0),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/39/w1230/cho1753090781_5709.jpg", "Quầy đặc sản khô", false, 1),
                img("https://statics.vinpearl.com/cho-han-da-nang-16_1637569200.jpg", "Không gian mua sắm", false, 2)
            )),
            quote("“Thích đặc sản Đà Nẵng, ghé chợ Hàn.”"),
            divider(),
            mapBlock(new double[]{108.2236, 16.0682})
        ))
        .build();
  }

  /** 7) Asia Park – Sun Wheel */
  private static PlaceDoc poiAsiaPark() {
    String slug = "asia-park-sun-wheel";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Asia Park – Sun Wheel")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Công viên giải trí với vòng quay Sun Wheel nổi bật về đêm.")
        .description("Asia Park có các trò chơi giải trí, khu ẩm thực phong cách châu Á. "
            + "Sun Wheel là điểm check-in rực rỡ khi lên đèn.")
        .addressLine("1 Phan Đăng Lưu, Hòa Cường Bắc, Hải Châu, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2227, 16.0470})
        .images(withCover(
            img("https://asiapark.sunworld.vn/wp-content/uploads/2018/07/DSC_7105.jpg", "Sun Wheel – Asia Park", true, 0),
            img("https://www.vecaptreobanahills.com/wp-content/uploads/2023/09/cong-vien-chau-a.png", "Công viên về đêm", false, 1),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/16/20/19/w1230/con1752650354_8476.jpeg", "Khu trò chơi", false, 2),
            img("http://www.dulichhoanggia.com.vn/Uploads/652.jpg", "Không gian check-in", false, 3),
            img("https://www.kkday.com/vi/blog/wp-content/uploads/Am-thuc-hap-dan-tai-Nha-hang-T-3135-3587-1608799898.jpg", "Ẩm thực châu Á", false, 4),
            img("https://duan-sungroup.com/wp-content/uploads/2022/11/cong-vien-chau-a-asia-park-duoc-cai-tao-vao-nam-2020.jpg", "Khuôn viên rộng", false, 5)
        ))
        .content(List.of(
            heading("Asia Park – sắc màu giải trí giữa lòng thành phố"),
            quote("""
                    Đêm nghiêng vòng ánh sáng,
                    phố hát dưới chân đu;
                    gió chở tiếng reo vỡ,
                    ngôi sao đậu trên khu.
                """),
            paragraph("Asia Park là tổ hợp vui chơi – ẩm thực mang phong cách châu Á ngay trung tâm Đà Nẵng; khi phố lên đèn, Sun Wheel bừng sáng thành điểm nhấn nhìn thấy từ xa. Không gian rộng, nhiều khu chủ đề, phù hợp đi nhóm bạn hoặc gia đình; nếu muốn chụp hình với vòng quay, hãy đến sớm trước giờ cao điểm để có khung hình ít người."),
            heading("Sun Wheel – thời điểm & mẹo trải nghiệm"),
            paragraph("Khung giờ lý tưởng: 18:30–20:00 khi bầu trời còn hơi xanh, đèn vừa bật cho ảnh có độ chuyển sắc; sau 20:00 thường đông hơn. Mỗi vòng quay khoảng vài vòng chậm, đủ ngắm toàn cảnh sông Hàn – trung tâm thành phố. Nếu sợ độ cao, chọn cabin gần cửa để cảm thấy yên tâm hơn; hạn chế đứng dồn về một phía khi cabin đang lên cao."),
            imageBlock(img("https://www.kkday.com/vi/blog/wp-content/uploads/kinh-nghiem-di-vong-quay-sunwheel-3.jpg", "Sun Wheel tỏa sáng", false, 0)),
            heading("Vé vào cửa & xếp hàng"),
            paragraph("Quầy vé và cổng soát đặt ngay mặt tiền công viên; có nhiều loại vé theo khu trò chơi. Cuối tuần, hàng đợi Sun Wheel có thể kéo dài: nên vào cổng sớm, ưu tiên đi vòng quay trước rồi mới dạo khu trò chơi. Giữ vé/QR đến khi ra khu, vì có thể cần quét lại ở vài điểm."),
            heading("Khu trò chơi & mức độ phù hợp"),
            paragraph("Khu ngoài trời có các trò vận động – cảm giác mạnh, xen kẽ khu trẻ em. Hãy đọc bảng chiều cao tối thiểu và khuyến cáo sức khỏe trước khi tham gia; người say xe/nôn nao nên bắt đầu bằng trò nhẹ (vòng quay mini, tàu lượn cỡ nhỏ) rồi tăng dần cường độ. Tránh chơi sau khi ăn no."),
            heading("Ẩm thực phong cách châu Á"),
            paragraph("Khu ẩm thực quy tụ món quen miệng như mì, cơm, xiên nướng, trà sữa; chia món theo suất nhỏ để “ăn dạo” nhiều quầy. Giữ vệ sinh chung: bỏ rác đúng nơi, lau bàn sau khi dùng; nếu đi nhóm, có thể cắt cử người giữ chỗ – người khác xếp hàng mua để tiết kiệm thời gian."),
            heading("Góc chụp ảnh & gợi ý khung hình"),
            paragraph("Khung cận: dùng ống kính góc rộng/điện thoại đứng sát chân vòng quay để lấy hiệu ứng “tỏa nan”. Khung xa: đứng bên bờ sông phía đường 2/9 để gom cả Sun Wheel và dải phản chiếu nước. Giờ vàng (17:15–17:45) cho nền trời ấm; sau 19:00, giảm tốc 1/4–1s (có tripod) để bắt vệt đèn quay. Tránh bật flash hướng thẳng vào người lạ."),
            gallery(List.of(
                img("https://asiapark.sunworld.vn/wp-content/uploads/2018/07/DSC_7105.jpg", "Sun Wheel tỏa sáng", false, 0),
                img("https://www.vecaptreobanahills.com/wp-content/uploads/2023/09/cong-vien-chau-a.png", "Công viên về đêm", false, 1),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/16/20/19/w1230/con1752650354_8476.jpeg", "Khu trò chơi sôi động", false, 2)
            )),
            heading("An toàn & lưu ý"),
            paragraph("Tuân thủ hướng dẫn của nhân viên tại từng trò, cất đồ rơi rớt (điện thoại, ví, kính) trước khi lên máy; trẻ nhỏ luôn đi kèm người lớn. Trời mưa to/gió lớn có thể tạm dừng vận hành một số trò – linh hoạt sắp xếp lộ trình. Uống đủ nước, mang áo khoác mỏng khi ngồi cabin có điều hòa."),
            heading("Lộ trình gợi ý buổi tối (≈2–3 giờ)"),
            paragraph("18:30 vào cổng → 18:45–19:15 đi Sun Wheel ngắm toàn cảnh → 19:20–20:00 dạo khu trò chơi/ảnh đêm → 20:00–20:30 ăn nhẹ khu ẩm thực → 20:30 chụp thêm vài khung hình với vòng quay rồi ra về."),
            quote("“Đêm Đà Nẵng rực rỡ nhất từ vòng quay Sun Wheel.”"),
            divider(),
            mapBlock(new double[]{108.2227, 16.0470})
        ))
        .build();
  }

  /** 8) Bảo tàng Điêu khắc Chăm */
  private static PlaceDoc poiBaoTangCham() {
    String slug = "bao-tang-dieu-khac-cham";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bảo tàng Điêu khắc Chăm")
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription("Bảo tàng Chăm lớn nhất thế giới, lưu trữ nhiều hiện vật quý.")
        .description("Trưng bày hàng nghìn hiện vật văn hóa Chăm-pa với nhiều phong cách nghệ thuật khác nhau. "
            + "Không gian yên tĩnh, thích hợp khám phá lịch sử – nghệ thuật miền Trung.")
        .addressLine("02 2 Tháng 9, Hải Châu, Đà Nẵng")
        .countryCode("VN").provinceName("Đà Nẵng")
        .location(new double[]{108.2208, 16.0617})
        .images(withCover(
            img("https://www.bonboncar.vn/blog/content/images/size/w2000/format/webp/2025/07/A-nh-chu-p-Ma-n-hi-nh-2025-07-15-lu-c-10.09.38.png", "Mặt tiền bảo tàng Chăm", true, 0),
            img("https://danangfantasticity.com/wp-content/uploads/2020/07/phong-my-son-bao-tang-dieu-khac-cham-danang-fantasticity-com.jpg", "Không gian trưng bày", false, 1),
            img("https://digiticket.vn/blog/wp-content/uploads/2021/05/bao-tang-cham-1.jpg", "Tác phẩm điêu khắc", false, 2),
            img("https://digiticket.vn/blog/wp-content/uploads/2021/05/phong-dong-duong-bao-tang-dieu-khac-cham-danang-fantasticity-com-03.jpg", "Hiện vật Chăm-pa", false, 3),
            img("https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/11_2021/du-lich-da-nang-susu.na29.jpg", "Hành lang bảo tàng", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2020/07/tong-quan-bao-tang-dieu-khac-cham-da-nang-03.jpg", "Khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Bảo tàng Điêu khắc Chăm – kho tàng nghệ thuật Champa"),
            quote("""
                    Gạch nung ôm hơi nắng,
                    sa thạch kể nghìn năm;
                    bước chậm nghe tay đá
                    hóa tiếng nói Chiêm Thành.
                """),
            paragraph("Nằm ở vị trí thuận tiện gần trung tâm, bảo tàng quy tụ bộ sưu tập điêu khắc Chăm-pa phong phú với nhiều phong cách nghệ thuật khác nhau. Không gian trưng bày tĩnh lặng, ánh sáng ấm vừa đủ để đọc chú thích, phù hợp cho hành trình chậm rãi tìm hiểu lịch sử – mỹ thuật miền Trung."),
            heading("Cách tham quan gợi ý (60–90 phút)"),
            paragraph("Bắt đầu ở khu tổng quan để nắm mốc thời gian, sau đó đi theo các phòng theo vùng/phong cách: Mỹ Sơn – Trà Kiệu – Đồng Dương – Tháp Mẫm. Ở mỗi phòng, đọc kỹ bảng chú thích về chất liệu (sa thạch, gạch nung), niên đại và công năng của tượng/ phù điêu. Thuê thuyết minh hoặc audio guide sẽ giúp kết nối các mạch chuyện và biểu tượng tôn giáo – đời sống."),
            heading("Những hiện vật tiêu biểu nên dừng lâu"),
            paragraph("Tượng thần Shiva – bò Nandin, Linga–Yoni, Apsara múa, Garuda, các phù điêu trưng bày cảnh lễ hội và thần thoại… Hãy quan sát kỹ đường chạm, nếp vải, hoa văn lặp lại theo nhịp, và cách nghệ nhân xử lý khối rỗng–đặc để tạo chuyển động. So sánh giữa các phong cách (như Mỹ Sơn – mềm mại, Đồng Dương – mạnh mẽ khúc chiết) để thấy tiến hóa thẩm mỹ qua thế kỷ."),
            heading("Mẹo chụp ảnh & ứng xử trong không gian trưng bày"),
            paragraph("Ánh sáng trong nhà mang tông vàng; đứng chéo 45° so với nguồn sáng để khối nổi rõ, tránh bóng đổ mạnh. Tắt flash, không chạm vào hiện vật dù chỉ “chạm nhẹ”; đi lại nhỏ tiếng để giữ không gian tôn trọng; balo nên đeo trước ở khu đông để tránh quệt vào bệ tượng. Nếu đi cùng trẻ nhỏ, giới thiệu ngắn về câu chuyện trên phù điêu để bé hứng thú và tránh sờ vào hiện vật."),
            imageBlock(img("https://tourbanahills.vn/wp-content/uploads/2023/02/bao-tang-dieu-khac-cham5.jpg.jpg", "Lối vào bảo tàng", false, 0)),
            heading("Tiện ích – dịch vụ"),
            paragraph("Quầy vé – thông tin đặt ngay sảnh; có dịch vụ gửi đồ, thuyết minh viên/ audio guide tại chỗ vào một số khung giờ. Sách ảnh – postcard tại cửa hàng lưu niệm giúp bạn mang về tư liệu học tập. Trang phục gọn gàng, giày đế êm sẽ giúp đi lại nhiều phòng đỡ mỏi."),
            gallery(List.of(
                img("https://www.bonboncar.vn/blog/content/images/size/w2000/format/webp/2025/07/A-nh-chu-p-Ma-n-hi-nh-2025-07-15-lu-c-10.09.38.png", "Mặt tiền bảo tàng", false, 0),
                img("https://danangfantasticity.com/wp-content/uploads/2020/07/phong-my-son-bao-tang-dieu-khac-cham-danang-fantasticity-com.jpg", "Không gian trưng bày", false, 1),
                img("https://digiticket.vn/blog/wp-content/uploads/2021/05/bao-tang-cham-1.jpg", "Tác phẩm điêu khắc Champa", false, 2)
            )),
            heading("Bảo tồn & lưu ý"),
            paragraph("Tôn trọng ranh giới bệ trưng bày, không tựa – kê đồ lên hiện vật; không vẽ bậy hay dán sticker; rác mang đến hãy mang đi. Một số khu có điều hòa mát sâu – nên mang áo khoác mỏng nếu nhạy nhiệt."),
            quote("“Kho tàng Champa giữa lòng thành phố trẻ.”"),
            divider(),
            mapBlock(new double[]{108.2208, 16.0617})
        ))
        .build();
  }
}