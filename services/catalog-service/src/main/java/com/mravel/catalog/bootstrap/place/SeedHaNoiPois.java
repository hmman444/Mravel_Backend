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
public class SeedHaNoiPois {

  @Bean
  CommandLineRunner seedHaNoiPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedHaNoiPois] start");

      List<PlaceDoc> pois = List.of(
          poiHoHoanKiem(),
          poiPhoCoHaNoi(),
          poiLangChuTichHoChiMinh(),
          poiVanMieuQuocTuGiam(),
          poiHoTayTranQuoc(),
          poiCauLongBien(),
          poiNhaTuyHoaLo(),
          poiHoangThanhThangLong()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedHaNoiPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedHaNoiPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedHaNoiPois] done, inserted = " + inserted);
    };
  }

  // ===================== 8 POI FOR HÀ NỘI =====================

  /** 1) Hồ Hoàn Kiếm & Đền Ngọc Sơn */
  private static PlaceDoc poiHoHoanKiem() {
    String slug = "ho-hoan-kiem-den-ngoc-son";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Hồ Hoàn Kiếm & Đền Ngọc Sơn")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Trái tim thủ đô, tháp Rùa giữa hồ, cầu Thê Húc đỏ rực.")
        .description("Dạo quanh hồ, qua cầu Thê Húc thăm Đền Ngọc Sơn; sáng sớm người dân tập thể dục, tối cuối tuần phố đi bộ nhộn nhịp.")
        .addressLine("Đinh Tiên Hoàng, Hoàn Kiếm, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8525, 21.0288})
        .images(withCover(
            img("https://statics.vinpearl.com/thap-rua-ho-guom-3_1691857546.jpg", "Hồ Gươm – tháp Rùa", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/cau-the-huc-03-1696869213.jpeg", "Cầu Thê Húc", false, 1),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg/500px-C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg", "Đền Ngọc Sơn", false, 2),
            img("https://statics.vinpearl.com/pho-di-bo-ha-noi-1_1685972192.jpg", "Phố đi bộ", false, 3),
            img("https://www.ductronghotel.com/UploadFile/Article/dao-quanh-ho-guom-sang-som1.jpg", "Sáng sớm bên hồ", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/mo-mang-binh-yen-top-5-dia-diem-ngam-hoang-hon-ha-noi-4-1639723337.jpeg", "Hoàng hôn lãng mạn", false, 5)
        ))
        .content(List.of(
            heading("Hồ Gươm – biểu tượng nghìn năm"),
            quote("""
                    Sớm hồng qua mặt nước,
                    tháp Rùa gọi sương bay;
                    Thê Húc ôm tia nắng,
                    Ngọc Sơn lặng chùa cây.
                """),
            paragraph("Trái tim của Hà Nội gói trong một vòng hồ nhỏ: tháp Rùa trầm mặc giữa làn nước xanh, cầu Thê Húc đỏ như tia bình minh dẫn vào Đền Ngọc Sơn. Sáng sớm (05:30–07:00) là lúc lung linh và yên ả nhất: người dân tập dưỡng sinh, vài tiếng đàn bầu, mùi hoa sữa/hoa lộc vừng theo mùa. Chiều muộn và tối cuối tuần, khu vực phố đi bộ quanh hồ náo nhiệt hơn nhưng ánh đèn phản chiếu lên mặt nước rất đẹp."),
            heading("Lối tham quan gợi ý 60–90 phút"),
            paragraph("Bắt đầu từ phố Đinh Tiên Hoàng → dạo ven hồ ngắm tháp Rùa → sang cầu Thê Húc vào Đền Ngọc Sơn (đi chậm để cảm nhận nhịp gỗ dưới chân) → tham quan đền, đài Nghiên, tháp Bút, khám phá các hiện vật rùa hồ Gươm trưng bày → trở ra vòng tiếp hồ qua Lê Thái Tổ – Hàng Khay để khép cung đường."),
            heading("Đền Ngọc Sơn – nghi lễ và ứng xử"),
            paragraph("Không gian thờ tự yêu cầu trang phục lịch sự, nói nhỏ, không chụp ảnh ở khu vực có biển cấm. Qua điện thờ, dành ít phút đọc bảng giới thiệu tích xưa (gươm báu – trả gươm) để hiểu hơn câu chuyện gắn với hồ. Vào giờ cao điểm, nhớ đi theo lối một chiều, nhường đường ở nhịp cầu hẹp."),
            imageBlock(img("https://statics.vinpearl.com/cau-the-huc%20(4)_1676479579.jpg", "Cầu Thê Húc rực rỡ", false, 0)),
            heading("Mẹo chụp ảnh & khung giờ đẹp"),
            paragraph("Bình minh: đứng ở bờ phía Hàng Khay để bắt được mặt trời lên sau lưng cầu Thê Húc. Chiều: chọn bờ Đinh Tiên Hoàng để lấy tháp Rùa giữa nền trời ấm. Tránh dùng flash làm lóa mắt người khác; nếu chụp dài phơi, bệ lan can thấp đủ đặt mini-tripod (giữ an toàn, không chắn lối đi)."),
            heading("Phố đi bộ cuối tuần & trải nghiệm quanh hồ"),
            paragraph("Tối thứ Sáu–Chủ nhật, các tuyến đường quanh hồ cấm xe, mở không gian biểu diễn nghệ thuật đường phố, trò chơi dân gian. Bạn có thể mua nước mát, kem, ngồi ghế đá nghe nhạc hoặc xem múa rối nước ở gần đó (xem lịch suất diễn trước). Tránh xả rác, giữ lối đi cho trẻ em và người già."),
            gallery(List.of(
                img("https://statics.vinpearl.com/thap-rua-ho-guom-3_1691857546.jpg", "Tháp Rùa giữa hồ", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/cau-the-huc-03-1696869213.jpeg", "Cầu Thê Húc đỏ rực", false, 1),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg/500px-C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Cc_S%C6%A1n.jpg", "Cổng đền Ngọc Sơn", false, 2)
            )),
            heading("An toàn & lưu ý nhỏ"),
            paragraph("Đường ven hồ nhiều đoạn lát đá có thể trơn khi mưa; giữ tốc độ chậm, cẩn thận ở mép nước. Buổi tối đông người, đeo ba lô phía trước, giữ tư trang gọn. Nếu dẫn trẻ nhỏ, luôn nắm tay tại khu lan can thấp. Tôn trọng người tập thể dục buổi sớm – tránh đứng chắn đường chạy."),
            quote("“Một vòng hồ, một nhịp thở – chạm được nhịp tim Hà Nội.”"),
            divider(),
            mapBlock(new double[]{105.8525, 21.0288})
        ))
        .build();
  }

  /** 2) Phố cổ Hà Nội (36 phố phường) */
  private static PlaceDoc poiPhoCoHaNoi() {
    String slug = "pho-co-ha-noi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Phố cổ Hà Nội (36 phố phường)")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Mạng lưới phố cổ buôn bán sầm uất, nhà ống, mái ngói rêu phong.")
        .description("Khám phá Hàng Đào, Hàng Ngang, Hàng Mã…; thưởng thức phở, bún chả, cà phê trứng; tối có phố bia Tạ Hiện nhộn nhịp.")
        .addressLine("P. Hàng Gai, Hoàn Kiếm, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8480, 21.0350})
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2017/pho-co-Ha-Noi-1.jpg", "Một góc phố cổ", true, 0),
            img("https://hnm.1cdn.vn/2023/07/02/nha-ong.jpg", "Nhà ống mái ngói", false, 1),
            img("https://ntthnue.edu.vn/uploads/Images/2021/02/036.jpg", "Gánh hàng rong", false, 2),
            img("https://tour.dulichvietnam.com.vn/uploads/image/pho-bia-ta-hien/Anh-1-dirtytreehugger75(1).png", "Phố Tạ Hiện về đêm", false, 3),
            img("https://statics.vinpearl.com/cafe-pho-co-ha-noi-1_1680926451.jpg", "Cà phê phố cổ", false, 4),
            img("https://danviet.ex-cdn.com/files/f1/upload/3-2016/images/2016-08-04/147028444782323-ngo-nho-2.jpg", "Con ngõ nhỏ", false, 5)
        ))
        .content(List.of(
            heading("Phố cổ – nhịp sống chậm"),
            quote("""
                    Mái ngói ôm mùi nắng,
                    ngõ hẹp giữ lời rao;
                    bước chậm qua tên phố,
                    thời gian khẽ cúi chào.
                """),
            paragraph("Khu 36 phố phường là mạng lưới phố nhỏ đan dày, nhà ống mái ngói, biển hiệu san sát. Mỗi con phố xưa ứng với một nghề: Hàng Đào, Hàng Ngang, Hàng Mã… Đi bộ là cách hay nhất để cảm phố: giày mềm, balo gọn, sẵn sàng rẽ vào những ngõ hẹp bất chợt mở ra sân nhỏ rợp bóng cây."),
            heading("Lối dạo gợi ý 90–120 phút"),
            paragraph("Xuất phát Hàng Gai → vòng sang Hàng Đào – Đồng Xuân (chợ, hàng vải) → lách qua Hàng Chiếu – Ô Quan Chưởng (cửa ô cổ) → nghỉ chân cà phê ở mái nhà nhìn xuống phố → chiều tối ghé Tạ Hiện nếu thích không khí sôi động. Giữ nhịp chậm, quan sát mặt tiền nhà ống với cửa bức bàn, ban công gỗ và giàn hoa giấy."),
            heading("Ăn vặt & món nên thử"),
            paragraph("Buổi sáng hợp phở, bún thang; trưa có bún chả, bún mọc; giữa buổi gọi chén chè, cốm xào, bánh rán; cà phê trứng cho buổi chiều. Chọn quán đông nhưng xoay bàn nhanh, nhìn cách bếp vận hành gọn gàng và dụng cụ sạch. Gọi khẩu phần vừa để thử được nhiều món, hạn chế bỏ thừa."),
            heading("Cà phê – ban công – ngắm phố"),
            paragraph("Nhiều quán cà phê trong hẻm nhỏ có lối leo cầu thang gỗ lên ban công; từ đó nhìn xuống dòng người, theo dải xe đạp – xích lô – người bán hàng rong len qua khung nhà cổ. Tránh nói to, kéo ghế mạnh; tắt flash khi chụp để không làm phiền bàn bên."),
            imageBlock(img("https://static.vinwonders.com/production/pho-ta-hien-9.jpg", "Phố bia Tạ Hiện", false, 0)),
            heading("Tạ Hiện về đêm & ứng xử văn minh"),
            paragraph("Sau 19:00, phố bia Tạ Hiện rộn ràng: ghế thấp sát lòng đường, nhạc sôi động. Ngồi gọn bên trong vạch kẻ, không chắn lối đi; giữ đồ cá nhân phía trước; nói chuyện vui nhưng tôn trọng cư dân khu phố. Nếu không hợp nơi ồn, chọn các ngõ lân cận yên tĩnh hơn."),
            heading("Mua sắm & quà mang về"),
            paragraph("Hỏi giá trước, so sánh 2–3 quầy; quần áo, vải, đồ thủ công nhỏ gọn dễ mang. Với hàng làm tay, hỏi chất liệu, cách bảo quản; giữ hóa đơn/biên nhận nếu cần đổi. Tránh mua san hô, động vật hoang dã hoặc đồ nghi ngờ nguồn gốc."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Sáng sớm bắt ánh xiên qua mái ngói; trưa nắng dùng bóng râm hiên nhà để cân sáng; chiều muộn “giờ xanh” phản chiếu trên ô cửa kính. Ở ngõ hẹp, chờ khoảnh khắc gánh hàng đi ngang để có điểm nhấn chuyển động. Không chụp quá gần người bán khi chưa xin phép."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2017/pho-co-Ha-Noi-1.jpg", "Một góc phố cổ", false, 0),
                img("https://static.vinwonders.com/production/pho-ta-hien-9.jpg", "Phố bia Tạ Hiện", false, 1),
                img("https://statics.vinpearl.com/cafe-pho-co-ha-noi-1_1680926451.jpg", "Cà phê phố cổ", false, 2)
            )),
            heading("An toàn & lưu ý"),
            paragraph("Đường nhỏ, xe máy qua lại sát người đi bộ – đi sát lề; ví, điện thoại để ngăn trước. Tránh đứng giữa lòng phố để chụp ảnh; trời mưa gạch đá trơn, đi chậm và bám tay vịn ở bậc thềm. Tôn trọng cư dân: không nói lớn khuya, không xả rác, không vẽ bậy lên tường nhà cổ."),
            quote("“Đi chậm để nghe phố kể chuyện.”"),
            divider(),
            mapBlock(new double[]{105.8480, 21.0350})
        ))
        .build();
  }

  /** 3) Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình */
  private static PlaceDoc poiLangChuTichHoChiMinh() {
    String slug = "lang-chu-tich-ho-chi-minh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Công trình lịch sử – chính trị trọng điểm của Việt Nam.")
        .description("Tham quan lăng, bảo tàng Hồ Chí Minh, chùa Một Cột; lưu ý giờ mở cửa và quy định trang phục trang nghiêm.")
        .addressLine("2 Hùng Vương, Ba Đình, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8348, 21.0379})
        .images(withCover(
            img("https://statics.vinpearl.com/lang-chu-tich-ho-chi-minh-2_1685366073.jpg", "Lăng Chủ tịch Hồ Chí Minh", true, 0),
            img("https://media.thuonghieucongluan.vn/uploads/2025/06/23/z6732094622988-a29e6ed51530ae58aeb5931f22ce800f-1750613478.jpg", "Quảng trường Ba Đình", false, 1),
            img("https://static.vinwonders.com/production/bao-tang-ho-chi-minh-banner.jpeg", "Bảo tàng Hồ Chí Minh", false, 2),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/chua-mot-cot/chua-mot-cot-thumb.jpg", "Chùa Một Cột", false, 3),
            img("https://cdn.tienphong.vn/images/a7a4eb175a75567c9a7ae09768d7094862013c5781a55280ecb18b6bbfcbb27a7140483d8dfcf1daedf9b58c60c1b7d9f55f4d4cf291e10c38303c5c973ba32b/a2-1122.jpg", "Hàng gác danh dự", false, 4),
            img("https://baovinhlong.com.vn/file/e7837c027f6ecd14017ffa4e5f2a0e34/dataimages/202001/original/images2262083_Anh_trang_13.jpg", "Khu vực cảnh quan", false, 5)
        ))
        .content(List.of(
            heading("Ba Đình – nơi ghi dấu lịch sử"),
            quote("""
                    Sớm rơi lên thảm cỏ,
                    đá xám giữ thời gian;
                    bước nhẹ qua trang sử,
                    tiếng kèn gọi nắng ngan.
                """),
            paragraph("Quần thể Ba Đình gồm Lăng Chủ tịch Hồ Chí Minh, Quảng trường Ba Đình, Bảo tàng Hồ Chí Minh và chùa Một Cột. Không gian trang nghiêm, hàng cây – thảm cỏ được chăm sóc kỹ; nên đến buổi sáng để tránh nắng gắt và chủ động thời gian xếp hàng."),
            heading("Trang phục & quy định cơ bản"),
            paragraph("Ăn mặc lịch sự (quần dài, váy quá gối, áo có tay), bỏ mũ, không hút thuốc, không ồn ào. Một số đồ vật có thể phải gửi trước khi vào khu vực trang nghiêm; đi theo hàng, không chen lấn, không chụp ảnh ở khu vực có biển cấm. Luôn tuân thủ hướng dẫn của lực lượng làm nhiệm vụ."),
            heading("Lộ trình gợi ý 90–120 phút"),
            paragraph("Từ Quảng trường Ba Đình chiêm ngưỡng toàn cảnh → theo hướng dẫn vào viếng Lăng (xếp hàng trật tự, giữ im lặng) → tham quan Bảo tàng Hồ Chí Minh để hiểu hệ thống về cuộc đời và sự nghiệp → đi bộ sang chùa Một Cột, dừng đôi phút ngắm kiến trúc trên hồ sen → quay lại Quảng trường, dạo quanh thảm cỏ và các trục đường bao."),
            imageBlock(img("https://kientrucvadoisong.net/Upload/News/chuyen-cao-oc-%E2%80%9Cde%E2%80%9D-quang-truong-ba-dinh.jpg", "Không gian Ba Đình", false, 0)),
            heading("Bảo tàng & cách xem trưng bày"),
            paragraph("Ở Bảo tàng, đi theo tuyến từ tổng quan đến chuyên đề; đọc tóm tắt tại đầu mỗi phòng để nắm mạch nội dung, sau đó tập trung vào các hiện vật – tư liệu gốc. Ghi chú nhanh những mốc năm quan trọng để đối chiếu khi xem tiếp ngoài trời."),
            heading("Chùa Một Cột – điểm dừng tĩnh tại"),
            paragraph("Công trình biểu tượng với kiến trúc gỗ đặt trên trụ đá giữa hồ sen. Khi vào khuôn viên chùa: đi nhẹ, nói khẽ, tránh dựa – chạm vào lan can gỗ; xếp hàng khi đông, giữ lối đi chung."),
            heading("Góc chụp & ứng xử khi chụp ảnh"),
            paragraph("Toàn cảnh Lăng đẹp nhất từ trục trung tâm Quảng trường; góc chéo 30–45° giúp khối kiến trúc nổi hơn. Ở khu vực có quy định, vui lòng không chụp hoặc tắt tiếng máy ảnh/điện thoại. Không trèo qua bồn cỏ, không đứng chắn hàng."),
            gallery(List.of(
                img("https://statics.vinpearl.com/lang-chu-tich-ho-chi-minh-2_1685366073.jpg", "Lăng Chủ tịch Hồ Chí Minh", false, 0),
                img("https://media.thuonghieucongluan.vn/uploads/2025/06/23/z6732094622988-a29e6ed51530ae58aeb5931f22ce800f-1750613478.jpg", "Quảng trường Ba Đình", false, 1),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/chua-mot-cot/chua-mot-cot-thumb.jpg", "Chùa Một Cột", false, 2)
            )),
            heading("An toàn & lưu ý nhỏ"),
            paragraph("Giữ tư trang gọn gàng, đi giày thấp để di chuyển nhiều; thời tiết nắng nên mang nước và mũ (cởi mũ khi vào khu trang nghiêm). Một số thời điểm có nghi lễ đổi gác – đứng sau vạch, quan sát trật tự. Không xả rác, không ngồi hay bước lên thảm cỏ."),
            quote("“Trang nghiêm, yên tĩnh – lịch sử hiện diện.”"),
            divider(),
            mapBlock(new double[]{105.8348, 21.0379})
        ))
        .build();
  }

  /** 4) Văn Miếu – Quốc Tử Giám */
  private static PlaceDoc poiVanMieuQuocTuGiam() {
    String slug = "van-mieu-quoc-tu-giam";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Văn Miếu – Quốc Tử Giám")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Trường đại học đầu tiên của Việt Nam, biểu tượng hiếu học.")
        .description("Khu di tích với kiến trúc cổ kính, bia tiến sĩ, hồ Văn; không gian xanh giữa lòng thủ đô.")
        .addressLine("58 Quốc Tử Giám, Đống Đa, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8356, 21.0279})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/khue-van-cac-02-1697245075.jpeg", "Khuê Văn Các", true, 0),
            img("https://static.vinwonders.com/production/van-mieu-quoc-tu-giam-3.jpgg", "Sân vườn cổ kính", false, 1),
            img("https://scov.gov.vn/upload/2005660/20210923/9a74b1772a6733d123f3875cb1ab38fb20190201%5C130936_DS_BiaTienSi.jpg", "Bia tiến sĩ", false, 2),
            img("https://cdn.nhandan.vn/images/7f491ee6c6b660425d7c9ab03f1ec4476b41c4d12c4dd5b820657f70b2dbe1883fd1dae55d20024dbfd7d558efa95d485cfacd5ee898d181029010948d9846a0/hn2-1626710744002.jpg", "Hồ Văn", false, 3),
            img("https://titangroup.vn/wp-content/uploads/cong-tam-quan-02.jpg", "Cổng tam quan", false, 4),
            img("https://www.vietfuntravel.com.vn/image/data/Ha-Noi/Quoc-Tu-Giam/tho-khong-tu.jpg", "Kiến trúc gỗ đỏ", false, 5)
        ))
        .content(List.of(
            heading("Văn Miếu – nét đẹp hiếu học"),
            quote("""
                    Gió lùa qua Khuê Văn,
                    bóng chữ rơi hiên gạch;
                    tiếng lá chạm bia xưa,
                    nếp học còn nghiêm mạch.
                """),
            paragraph("Văn Miếu – Quốc Tử Giám là quần thể di tích gắn với truyền thống hiếu học của Thăng Long. Không gian cổ kính với cổng tam quan, hồ Văn, Khuê Văn Các, sân vườn tĩnh lặng và hệ thống bia tiến sĩ khắc danh hiền tài. Đi chậm để nghe tiếng thời gian trên mái ngói rêu, mùi gỗ sậm và bóng cây cổ thụ rợp mát."),
            heading("Lộ trình tham quan gợi ý (60–90 phút)"),
            paragraph("Từ cổng tam quan vào Giếng Thiên Quang → qua Khuê Văn Các (đi bên hành lang, không chen giữa) → đến khu bia tiến sĩ, đọc kỹ giới thiệu về khoa thi – niên hiệu → tiến vào Đại Thành Môn, điện Đại Bái, nhà Thái Học để hiểu lịch sử giáo dục triều Lý – Trần – Lê → ra hồ Văn kết thúc."),
            imageBlock(img("https://cdnphoto.dantri.com.vn/YcGYcXfv_vwmAHSSJI0hoKfrV7o=/thumb_w/1920/2022/02/19/2-1645206718840.jpeg", "Khuê Văn Các", false, 0)),
            heading("Bia tiến sĩ & quy tắc ứng xử"),
            paragraph("Hệ thống bia đặt trên lưng rùa là di sản quý – tuyệt đối không sờ, không chà tay lên đầu rùa; không tựa, ngồi, đặt đồ lên bệ bia. Giữ khoảng cách khi đông người, đọc bảng giới thiệu để hiểu tên khoa, niên hiệu, danh tính và quan niệm trọng dụng hiền tài."),
            heading("Khung giờ & mùa đẹp"),
            paragraph("Sáng sớm trời dịu, ít người, ánh xiên đẹp; mùa xuân có nhiều đoàn chụp kỷ yếu, lễ dâng bút – cầu may; mùa thu nắng mềm, bóng cây vàng lá rất thơ. Những ngày lễ – cao điểm thi cử sẽ đông, nên đến sớm hoặc đi muộn hơn để giữ trải nghiệm thanh tĩnh."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Đứng chéo 30–45° so với Khuê Văn Các để thấy rõ cấu trúc gỗ và họa tiết; ở Giếng Thiên Quang, lùi vài nhịp lấy cả khung phản chiếu; trong điện thờ, tắt flash, giữ tiếng máy ảnh nhỏ. Tôn trọng không gian thờ tự – không tạo dáng phô trương, không đứng chắn lối."),
            heading("Học sinh – sinh viên & hoạt động văn hóa"),
            paragraph("Nhiều đoàn học sinh – sinh viên đến dâng hương, chụp ảnh lưu niệm, xin chữ đầu năm. Khi gặp đoàn nghi lễ, nhường lối, giữ trật tự; nếu muốn xin chữ, xếp hàng theo hướng dẫn và bảo quản cẩn thận giấy mực."),
            heading("An toàn & lưu ý"),
            paragraph("Trang phục lịch sự, nói khẽ, đi nhẹ; giày đế êm để đi sân lát gạch. Tránh sờ vào đồ gỗ sơn son thếp vàng; không xả rác, không vẽ bậy lên tường – cột. Trời mưa nền gạch trơn, đi chậm dọc hành lang."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/khue-van-cac-02-1697245075.jpeg", "Khuê Văn Các", false, 0),
                img("https://scov.gov.vn/upload/2005660/20210923/9a74b1772a6733d123f3875cb1ab38fb20190201%5C130936_DS_BiaTienSi.jpg", "Bia tiến sĩ", false, 1),
                img("https://cdn.nhandan.vn/images/7f491ee6c6b660425d7c9ab03f1ec4476b41c4d12c4dd5b820657f70b2dbe1883fd1dae55d20024dbfd7d558efa95d485cfacd5ee898d181029010948d9846a0/hn2-1626710744002.jpg", "Hồ Văn", false, 2)
            )),
            quote("“Nơi tôn vinh trí tuệ ngàn năm.”"),
            divider(),
            mapBlock(new double[]{105.8356, 21.0279})
        ))
        .build();
  }

  /** 5) Hồ Tây & Chùa Trấn Quốc */
  private static PlaceDoc poiHoTayTranQuoc() {
    String slug = "ho-tay-chua-tran-quoc";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Hồ Tây & Chùa Trấn Quốc")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Hồ lớn nhất Hà Nội; hoàng hôn đẹp, có chùa Trấn Quốc cổ kính bên bờ.")
        .description("Đạp xe ven hồ, ngắm hoàng hôn; ghé chùa Trấn Quốc – ngôi chùa cổ nhất Thăng Long.")
        .addressLine("Thanh Niên, Tây Hồ, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8360, 21.0479})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/buc-tranh-hoang-hon-ho-tay-moi-buoi-chieu-ta-dep-me-man-long-nguoi-01-1664336840.jpg", "Hồ Tây chiều tà", true, 0),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc", false, 1),
            img("https://resource.kinhtedothi.vn/2023/05/24/z4369045697220-36cc4bb9c8cc1daad3bb2277ca8088ba.jpg", "Lối đi ven hồ", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/ngam-hoang-hon-ho-tay-thuong-thuc-mon-qua-thien-nhien-cuoi-ngay-04-1650338508.jpg", "Hoàng hôn rực rỡ", false, 3),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/12/chua-tran-quoc-2.jpg", "Tháp nhiều tầng", false, 4),
            img("https://statics.vinpearl.com/ca-phe-ho-tay-so-1_1681481823.jpg", "Quán cà phê view hồ", false, 5)
        ))
        .content(List.of(
            heading("Hồ Tây – góc nên thơ của thủ đô"),
            quote("""
                    Gió chở mùi hương nước,
                    nắng rót xuống bờ xa;
                    chuông chùa lên nhịp lặng,
                    mặt hồ ủ hoàng hôn.
                """),
            paragraph("Vòng hồ rộng thoáng, đường cây rợp mát là nơi lý tưởng để dạo bộ, đạp xe hay ngồi quán ven nước ngắm trời chuyển sắc. Khung giờ 16:30–18:00 đẹp nhất: mây nhuộm vàng, mặt nước thay màu liên tục; tối đến, gió mát và phố đèn hắt xuống tạo mặt gương lấp lánh."),
            heading("Đạp xe – đi bộ ven hồ"),
            paragraph("Cung dạo gợi ý: xuất phát từ đường Thanh Niên → men theo ven hồ qua các đoạn có vỉa hè rộng → dừng ở bến gió để chụp phản chiếu → ghé quán cà phê view hồ nghỉ 15–20 phút rồi vòng về. Đi giày đế êm, mang áo khoác mỏng khi trời nổi gió; đoạn gần bờ nước đôi khi trơn sau mưa, đi chậm giữ khoảng cách."),
            heading("Chùa Trấn Quốc – cổ tự bên hồ"),
            paragraph("Chùa Trấn Quốc nằm ngay dải đất hẹp giữa Hồ Tây và Trúc Bạch, nổi bật với tháp nhiều tầng đỏ thẫm. Khi vào khuôn viên chùa: mặc trang phục lịch sự, đi nhẹ nói khẽ, không xếp hàng chen lấn ở các lối hẹp; tắt flash khi chụp, tránh đứng chắn nhịp đi của người lễ Phật."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc", false, 0)),
            heading("Góc chụp & khung giờ vàng"),
            paragraph("Hoàng hôn: đứng trên đường Thanh Niên để lấy trọn tháp chùa với nền trời đổi sắc. Cận cảnh: chụp tháp nhiều tầng từ góc chéo 30–45° để thấy rõ cấu trúc và hàng tượng ni nhỏ trong hốc. Ở ven hồ, đặt đường chân trời ở 1/3 khung hình, tận dụng bóng cây làm đường dẫn. Luôn giữ an toàn, không với người – thiết bị quá mép nước."),
            heading("Café – trải nghiệm ven hồ"),
            paragraph("Dọc bờ có nhiều quán cà phê, trà chanh, kem; chọn quán có ban công/hiên rộng để ngắm trọn mặt hồ. Gọi phần đồ uống vừa phải, giữ bàn ghế gọn, không xả rác; khi đông, gửi xe đúng nơi quy định để tránh chắn lối đi bộ."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/buc-tranh-hoang-hon-ho-tay-moi-buoi-chieu-ta-dep-me-man-long-nguoi-01-1664336840.jpg", "Hoàng hôn Hồ Tây", false, 0),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc bên hồ", false, 1),
                img("https://statics.vinpearl.com/ca-phe-ho-tay-so-1_1681481823.jpg", "Café ven hồ", false, 2)
            )),
            heading("An toàn & lưu ý"),
            paragraph("Buổi chiều muộn có gió mạnh – giữ mũ, kính và thiết bị chụp ảnh cẩn thận; một số đoạn vỉa hè hẹp, ưu tiên người đi bộ khác và không đứng chắn lối. Tối cuối tuần thường đông, đeo balo phía trước khi chụp ảnh giữa đám đông. Tôn trọng không gian tôn nghiêm trong chùa, không nói lớn và không tựa vào lan can tháp."),
            quote("“Hoàng hôn đẹp nhất thành phố.”"),
            divider(),
            mapBlock(new double[]{105.8360, 21.0479})
        ))
        .build();
  }

  /** 6) Cầu Long Biên */
  private static PlaceDoc poiCauLongBien() {
    String slug = "cau-long-bien";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Cầu Long Biên")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Cây cầu lịch sử bắc qua sông Hồng, kiến trúc Pháp đầu thế kỷ 20.")
        .description("Đi bộ trên cầu, chụp ảnh tàu qua; sáng sớm có chợ Long Biên sầm uất dưới chân cầu.")
        .addressLine("Phúc Tân, Hoàn Kiếm – Ngọc Lâm, Long Biên, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8570, 21.0440})
        .images(withCover(
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/cau-long-bien-tren-cao.jpg", "Cầu Long Biên nhìn từ trên cao", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/15_zing.jpg", "Mặt cầu sắt", false, 1),
            img("https://img.cand.com.vn/resize/800x800/NewFiles/Images/2024/09/10/tau_qua_cau_Long_Bien-1725933482770.jpeg", "Tàu qua cầu", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/cho-long-bien-ha-noi-01-1700963622.jpeg", "Chợ Long Biên", false, 3),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Long_Bien_Bridge_Sunset.jpg/1200px-Long_Bien_Bridge_Sunset.jpg", "Chiều trên sông Hồng", false, 4),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Long%20Bien%20Bridge/long-bien-bridge-thumb.jpg", "Góc ảnh hoài cổ", false, 5)
        ))
        .content(List.of(
            heading("Long Biên – chứng nhân thời gian"),
            quote("""
                    Nhịp sắt hong chiều gió,
                    sông đỏ chảy mênh mang;
                    tiếng tàu quen nhịp thở,
                    phố cũ tựa vai ngang.
                """),
            paragraph("Cầu Long Biên do người Pháp xây đầu thế kỷ 20, bắc qua sông Hồng với kết cấu dàn thép đặc trưng. Đi bộ trên làn dành cho người đi bộ sẽ thấy rõ mối nối, đinh tán và lớp thời gian phủ rêu gỉ. Không khí trên cầu đổi theo giờ: sớm trong, trưa gắt nắng, chiều gió thổi mạnh từ sông."),
            heading("Lối dạo gợi ý 45–75 phút"),
            paragraph("Bắt đầu từ phía Phúc Tân → lên lối bộ hành sát lan can → đi chậm qua vài nhịp cầu để ngắm bãi giữa sông Hồng và phố cũ phía xa → dừng ở khoảng trống an toàn để nghỉ, chụp ảnh → nếu còn thời gian, sang phía Ngọc Lâm rồi vòng lại. Giữ lối cho xe máy, tránh đứng dàn hàng ngang."),
            heading("Chụp ảnh & khoảnh khắc tàu qua"),
            paragraph("Khung giờ đẹp: bình minh (ánh xiên dịu, sương nhẹ trên sông) và hoàng hôn (màu trời ấm). Khi chụp tàu, đứng trong phạm vi cho phép, giữ khoảng cách an toàn với ray; không ngồi – đứng trên đường ray, không vươn người/thiết bị qua lan can. Ống góc rộng cho cảnh toàn; ống trung cho chi tiết đinh tán, nhịp dàn thép."),
            imageBlock(img("https://cdn.tienphong.vn/images/814b5533c866dc3540018a126103e93541d1f826619b0ec86a8ba3921ccb0fb44047a1e2f8ae6b9fbda62cb532f924a6/tp-6-8804.jpg", "Khoảnh khắc tàu qua", false, 0)),
            heading("Chợ Long Biên buổi sớm"),
            paragraph("Dưới chân cầu phía bờ Phúc Tân là chợ đầu mối nhộn nhịp tầm 3:00–6:00 sáng. Nếu muốn ghi lại không khí chợ: mang giày kín, bảo quản tư trang, xin phép khi chụp cận cảnh; đi gọn một bên lối, tránh chắn đường vận chuyển hàng."),
            heading("Góc ngắm sông Hồng & bãi giữa"),
            paragraph("Từ vài nhịp giữa cầu có thể phóng tầm mắt về bãi bồi, vườn chuối, lều nhỏ của người làm vườn. Khi gió lớn, hạn chế đặt đồ lên lan can; nếu muốn phơi dài để bắt vệt xe trên cầu, dùng mini-tripod và bám chặt tay vịn."),
            gallery(List.of(
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/cau-long-bien-tren-cao.jpg", "Cầu Long Biên từ trên cao", false, 0),
                img("https://img.cand.com.vn/resize/800x800/NewFiles/Images/2024/09/10/tau_qua_cau_Long_Bien-1725933482770.jpeg", "Khoảnh khắc tàu qua", false, 1),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Long_Bien_Bridge_Sunset.jpg/1200px-Long_Bien_Bridge_Sunset.jpg", "Chiều trên sông Hồng", false, 2)
            )),
            heading("An toàn & lưu ý"),
            paragraph("Luôn đi trong dải bộ hành, quan sát xe máy đi sát; không trèo qua lan can, không ngồi trên ray/khớp nối. Mặt cầu có chỗ khấp khểnh – đi chậm, tránh giày gót cao. Trời mưa gió lớn hoặc sấm sét thì không nên lên cầu. Không xả rác, không khắc vẽ lên dầm thép – đây là di sản đang được bảo tồn."),
            quote("“Gỉ sét kể chuyện, sông Hồng chảy mãi.”"),
            divider(),
            mapBlock(new double[]{105.8570, 21.0440})
        ))
        .build();
  }

  /** 7) Nhà tù Hoả Lò (Maison Centrale) */
  private static PlaceDoc poiNhaTuyHoaLo() {
    String slug = "nha-tu-hoa-lo";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Nhà tù Hoả Lò (Maison Centrale)")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Di tích lịch sử đặc biệt, kể câu chuyện đấu tranh kiên cường.")
        .description("Khu trưng bày tái hiện thời kỳ nhà tù thuộc địa; nên tham quan có hướng dẫn để hiểu sâu ngữ cảnh lịch sử.")
        .addressLine("1 Hoả Lò, Hoàn Kiếm, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8462, 21.0250})
        .images(withCover(
            img("http://thquangtrung.hoankiem.edu.vn/upload/29321/fck/files/hl1.jpg", "Cổng Nhà tù Hoả Lò", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/2007.nha-tu-hoa-lo-tour-du-khach_1.jpg", "Không gian trưng bày", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tu-hoa-lo-di-tich-lich-su-noi-tieng-tai-ha-noi-03-1639725599.png", "Phòng giam tái hiện", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tu-hoa-lo-di-tich-lich-su-noi-tieng-tai-ha-noi-06-1639725600.png", "Tư liệu lịch sử", false, 3),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo2.jpg", "Hành lang di tích", false, 4),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo3.jpeg", "Khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Hoả Lò – ký ức không quên"),
            quote("""
                    Tường đá sâu hơi thở,
                    then sắt kể đêm dài;
                    bước nhẹ qua ô cửa,
                    nhớ để quý ngày mai.
                """),
            paragraph("Nhà tù Hoả Lò là di tích đặc biệt gắn với giai đoạn thuộc địa và những câu chuyện đấu tranh kiên cường. Không gian trưng bày tái hiện buồng giam, phòng biệt lập, hiện vật và tư liệu gốc; ánh sáng chủ ý thấp để gợi không khí nặng nề – hãy đi chậm, đọc kỹ bảng thuyết minh để hiểu bối cảnh lịch sử."),
            heading("Cách tham quan gợi ý (60–90 phút)"),
            paragraph("Bắt đầu từ khu giới thiệu tổng quan → đi qua dãy buồng giam tập thể để cảm nhận không gian chật hẹp → phòng biệt giam (xà lim) với kỷ luật khắc nghiệt → khu trưng bày tư liệu, bản án, đồ dùng tù nhân → kết thúc ở không gian tri ân, nơi nhắc nhớ giá trị hoà bình. Ở mỗi điểm dừng, dành 2–3 phút đối chiếu chú thích – mốc thời gian."),
            heading("Những điểm nên dừng lâu"),
            paragraph("Mô hình – ảnh tư liệu về điều kiện giam giữ; hiện vật như cùm sắt, xiềng, bát – thìa; câu chuyện vượt ngục và hoạt động bí mật trong tù. Chú ý cách trưng bày sử dụng âm thanh – ánh sáng để tái hiện tâm trạng; đọc kỹ trích dẫn thư từ để thấy chiều sâu nhân bản giữa khắc nghiệt."),
            heading("Thuyết minh & trải nghiệm học tập"),
            paragraph("Nên sử dụng thuyết minh viên/ audio guide để kết nối mạch sự kiện, thuật ngữ và nhân vật. Ghi chú nhanh các mốc năm, tên địa danh để tra cứu thêm sau buổi tham quan. Với nhóm học sinh – sinh viên, phân công người đọc – người chụp lại bảng thông tin (ở khu cho phép) để tổng hợp tài liệu."),
            imageBlock(img("https://media.vov.vn/sites/default/files/styles/large_watermark/public/2023-07/trung-bay-thuong-binh-liet-si-nha-tu-hoa-lo_3.jpg", "Không gian trưng bày", false, 0)),
            heading("Quy định chụp ảnh & ứng xử"),
            paragraph("Một số khu vực hạn chế quay/chụp – tôn trọng biển báo. Tắt flash, không đặt máy/ba lô lên hiện vật hay bệ trưng bày; đi nhẹ, nói khẽ, không đùa cợt trong không gian tưởng niệm. Nếu đông khách, xếp hàng theo lối một chiều, tránh đứng chắn bảng chú thích."),
            heading("Khả năng tiếp cận & lưu ý an toàn"),
            paragraph("Lối đi trong nhà bằng phẳng, tuy có đoạn hẹp – đi sát phải để nhường lối. Mang giày đế êm, áo khoác mỏng vì điều hoà có thể lạnh. Giữ tư trang gọn gàng; trẻ nhỏ nên đi kèm người lớn và được giải thích phù hợp lứa tuổi."),
            gallery(List.of(
                img("http://thquangtrung.hoankiem.edu.vn/upload/29321/fck/files/hl1.jpg", "Cổng Nhà tù Hoả Lò", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/2007.nha-tu-hoa-lo-tour-du-khach_1.jpg", "Không gian trưng bày", false, 1),
                img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo2.jpg", "Hành lang di tích", false, 2)
            )),
            heading("Tưởng niệm & thông điệp"),
            paragraph("Mục đích của chuyến tham quan không chỉ là nhìn lại quá khứ mà còn để trân trọng hiện tại: hoà bình, nhân phẩm và tự do. Hãy rời khu trưng bày với một khoảnh lặng – dành phút tưởng nhớ những người đã hy sinh."),
            quote("“Nhớ để trân trọng hoà bình.”"),
            divider(),
            mapBlock(new double[]{105.8462, 21.0250})
        ))
        .build();
  }

  /** 8) Hoàng thành Thăng Long (Imperial Citadel) */
  private static PlaceDoc poiHoangThanhThangLong() {
    String slug = "hoang-thanh-thang-long";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Hoàng thành Thăng Long (Imperial Citadel)")
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription("Di sản văn hoá thế giới UNESCO, trung tâm quyền lực kinh thành xưa.")
        .description("Tham quan Đoan Môn, Kỳ Đài, nền điện Kính Thiên, khu khảo cổ 18 Hoàng Diệu; không gian xanh rộng rãi.")
        .addressLine("19C Hoàng Diệu, Ba Đình, Hà Nội")
        .countryCode("VN").provinceName("Hà Nội")
        .location(new double[]{105.8350, 21.0360})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-1-1732809742.jpg", "Đoan Môn – Hoàng thành", true, 0),
            img("https://statics.vinpearl.com/cua-bac-3_1674836261.jpg", "Cửa Bắc", false, 1),
            img("https://statics.vinpearl.com/cua-bac-4_1674836249.jpg", "Di tích còn sót lại", false, 2),
            img("https://hoangthanhthanglong.vn/wp-content/uploads/2013/08/doanmon1.jpg", "Không gian xanh", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-4-1732809743.jpg", "Di tích cổ kính", false, 4),
            img("https://disantrangan.vn/wp-content/uploads/2021/02/hinh-anh-hoang-thanh-thang-long-6.jpg", "Góc chụp toàn cảnh", false, 5)
        ))
        .content(List.of(
            heading("Hoàng thành – di sản giữa lòng đô thị"),
            quote("""
                    Gạch xưa phai dấu bụi,
                    cổng cổ lọc heo may;
                    bước nhẹ qua trăm thế,
                    lịch sử tự mở tay.
                """),
            paragraph("Quần thể Hoàng thành Thăng Long là Di sản văn hoá thế giới UNESCO – trung tâm quyền lực kinh thành xưa, nơi các triều đại để lại lớp lớp dấu tích. Không gian rộng, nhiều mảng xanh và công trình qua các thời kỳ; đi chậm để nhận ra sự giao thoa giữa thành lũy – cổng môn – nền điện và các hố khai quật khảo cổ."),
            heading("Lộ trình tham quan gợi ý (90–120 phút)"),
            paragraph("Từ cổng Đoan Môn vào trục chính → dừng ở nền điện Kính Thiên đọc bảng giới thiệu về không gian triều nghi → sang Cột Cờ (Kỳ Đài) ngắm toàn cảnh trục thành cũ → tiếp tục qua Cửa Bắc – nơi lưu dấu lịch sử hiện đại → kết thúc bằng khu khảo cổ 18 Hoàng Diệu để thấy các lớp nền kiến trúc qua nhiều thế kỷ."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-14-1732809749.jpg", "Đoan Môn cổ kính", false, 0)),
            heading("Những điểm nên dừng lâu"),
            paragraph("Đoan Môn: cổng chính với kết cấu vòm và gác lầu – quan sát dấu tích tu bổ ở mạch gạch. Nền điện Kính Thiên: đọc kỹ sơ đồ mặt bằng để hình dung trục nghi lễ và bậc đá rồng. Cửa Bắc: xem mảng tường thành và tư liệu lịch sử. Khu 18 Hoàng Diệu: chú ý lớp gạch, chân tường, cống thoát nước cổ – mỗi lớp là một mốc thời gian."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Đoan Môn đẹp ở góc chéo 30–45° để thấy rõ tầng mái – cửa vòm; ở nền Kính Thiên, lùi vài nhịp đặt bậc rồng làm đường dẫn; trong khu khảo cổ, chụp từ mép lối đi để giữ an toàn và không vượt rào bảo vệ. Sáng sớm và cuối chiều cho ánh xiên ấm, bóng cây đổ dài rất “điện ảnh”."),
            heading("Trưng bày – tư liệu"),
            paragraph("Các nhà trưng bày trong khuôn viên cung cấp bản đồ, mô hình và hiện vật khai quật. Hãy đọc bảng tóm lược theo mốc triều đại để xâu chuỗi: Đại La – Lý – Trần – Lê – Nguyễn – thời cận/hiện đại. Ghi chú nhanh tên cổng, trục đường xưa (Đoan Môn, Kính Thiên, Cửa Bắc…) để đối chiếu khi ra ngoài sân."),
            heading("Quy định & lưu ý"),
            paragraph("Giữ trật tự, không leo trèo lên nền di tích, không bước qua dây – rào bảo vệ; không chạm vào hiện vật, không xả rác. Trời nắng mang mũ, nước; đi giày đế êm vì di chuyển nhiều. Khi chụp ảnh, tắt flash trong nhà trưng bày và không dùng drone ở khu vực cấm."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-1-1732809742.jpg", "Đoan Môn – cửa chính", false, 0),
                img("https://statics.vinpearl.com/cua-bac-3_1674836261.jpg", "Cửa Bắc lịch sử", false, 1),
                img("https://disantrangan.vn/wp-content/uploads/2021/02/hinh-anh-hoang-thanh-thang-long-6.jpg", "Khu di tích – mảng xanh", false, 2)
            )),
            quote("“Di sản sống giữa nhịp đô thị.”"),
            divider(),
            mapBlock(new double[]{105.8350, 21.0360})
        ))
        .build();
  }
}