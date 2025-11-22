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
public class SeedHoChiMinhPois {

  @Bean
  CommandLineRunner seedHoChiMinhPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedHoChiMinhPois] start");

      List<PlaceDoc> pois = List.of(
          poiNhaThoDucBa(),
          poiBuuDienTrungTam(),
          poiChoBenThanh(),
          poiPhoDiBoNguyenHue(),
          poiDinhDocLap(),
          poiBaoTangChungTichChienTranh(),
          poiNhaHatThanhPho(),
          poiBitexcoSkydeck()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedHoChiMinhPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedHoChiMinhPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedHoChiMinhPois] done, inserted = " + inserted);
    };
  }

  // ===================== 8 POI FOR HCMC =====================

  /** 1) Nhà thờ Đức Bà Sài Gòn */
  private static PlaceDoc poiNhaThoDucBa() {
    String slug = "nha-tho-duc-ba-sai-gon";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Nhà thờ Đức Bà Sài Gòn")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Thánh đường kiểu Roman – Gothic biểu tượng của Sài Gòn.")
        .description("Công trình Pháp cuối thế kỷ 19 với gạch đỏ Marseille, mái vòm cao; quảng trường bồ câu và tượng Đức Mẹ phía trước.")
        .addressLine("01 Công xã Paris, Bến Nghé, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.7009, 10.7798})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-1-1736393709.jpg", "Mặt tiền Nhà thờ Đức Bà", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-tu-tren-cao-1692570417.jpg", "Quảng trường phía trước", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/thanh-duong-nha-tho-duc-ba-sai-gon-1692570430.jpg", "Không gian nội thất", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-tu-sua-1692570417.jpg", "Góc nhìn bên hông", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-5-1733709376.jpg", "Nhà thờ về đêm", false, 4),
            img("https://booking.muongthanh.com/upload_images/images/nha-tho-duc-ba-buu-dien-trung-tam-sai-gon.jpg", "Kề bên Bưu điện", false, 5)
        ))
        .content(List.of(
            heading("Nhà thờ Đức Bà – biểu tượng Pháp thuộc"),
            quote("""
                    Gạch đỏ ôm tia nắng,
                    chuông ngân giữa phố đông;
                    vòm cao nâng tiếng thở,
                    lá me rơi thành sông.
                """),
            paragraph("Thánh đường cuối thế kỷ 19 do người Pháp xây theo phong cách Roman – Gothic, nổi bật với gạch đỏ Marseille, hàng cột cao vút và các cửa sổ hoa hồng. Quảng trường phía trước là điểm tụ họp quen thuộc, nơi đàn bồ câu sà xuống giữa nhịp xe và bóng hàng me cổ thụ."),
            heading("Cách tham quan gợi ý (45–75 phút)"),
            paragraph("Đến buổi sáng để bắt ánh xiên qua mặt đứng gạch → dạo vòng quanh để quan sát các nhịp vòm, cửa sổ kính màu và các mảng tu bổ → nếu mở cửa, vào khu nội thất đi nhẹ nói khẽ, dành vài phút nhìn lên các vòm cuốn và lối ánh sáng → băng qua đường thăm Bưu điện Trung tâm Sài Gòn với mái vòm sắt – kính cổ điển."),
            imageBlock(img("https://phuot3mien.com/wp-content/uploads/2024/06/nha-tho-duc-ba.jpg", "Façade gạch đỏ đặc trưng", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý"),
            paragraph("Mặt đứng đối xứng, hai tháp chuông vươn cao (khi tu bổ có thể che lưới), gạch không trát giúp lộ màu thời gian; bên trong là dãy cột đá, vòm cuốn và các ô cửa kính màu kể tích Thánh. Quan sát mối nối, phào chỉ, hoa văn trên đỉnh cột để thấy sự giao thoa Roman – Gothic trong khí hậu nhiệt đới."),
            heading("Ứng xử trong không gian thờ tự"),
            paragraph("Trang phục lịch sự, nói khẽ, tắt chuông điện thoại; không chụp ảnh khu vực có biển cấm, không dùng flash hướng thẳng vào bài trí thờ tự; đi theo lối chỉ dẫn, không chạm tay vào hiện vật. Trong giờ lễ, ưu tiên không gian cho giáo dân, đứng ngoài quan sát nếu không tham dự."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Sáng sớm: đứng chéo 30–45° để bắt khối vòm và bóng cây me; chính diện: lùi về phía quảng trường để lấy trọn hai tháp và tượng Đức Mẹ; chiều muộn: khai thác phản chiếu trên kính Bưu điện phía đối diện. Tránh đứng giữa dòng xe để chụp, giữ an toàn ở vạch qua đường."),
            heading("Kết hợp tham quan lân cận"),
            paragraph("Từ Nhà thờ băng qua thăm Bưu điện Trung tâm (kiến trúc Pháp – Á độc đáo), đi bộ 5–10 phút đến phố sách Nguyễn Văn Bình hoặc Dinh Độc Lập. Khu vực nhiều quán cà phê bệt – giữ vệ sinh, không xả rác nơi công cộng."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-1-1736393709.jpg", "Mặt tiền gạch đỏ", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/thanh-duong-nha-tho-duc-ba-sai-gon-1692570430.jpg", "Không gian nội thất", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-5-1733709376.jpg", "Nhà thờ về đêm", false, 2)
            )),
            heading("An toàn & lưu ý"),
            paragraph("Khu trung tâm đông phương tiện: băng qua đường đúng vạch, quan sát kỹ; giữ tư trang khi chụp ảnh ở quảng trường; trong thời gian tu bổ có thể có rào chắn – tôn trọng lối đi và hướng dẫn tại chỗ."),
            quote("“Gạch đỏ lưu dấu thời gian, chuông ngân giữa lòng đô thị.”"),
            divider(),
            mapBlock(new double[]{106.7009, 10.7798})
        ))
        .build();
  }

  /** 2) Bưu điện Trung tâm Sài Gòn */
  private static PlaceDoc poiBuuDienTrungTam() {
    String slug = "buu-dien-trung-tam-sai-gon";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bưu điện Trung tâm Sài Gòn")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Công trình kiến trúc Pháp do Gustave Eiffel tham gia thiết kế.")
        .description("Trần vòm cao, bản đồ Sài Gòn xưa trên tường; nơi gửi bưu thiếp, mua tem và quà lưu niệm đặc trưng.")
        .addressLine("02 Công xã Paris, Bến Nghé, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.7019, 10.7793})
        .images(withCover(
            img("https://images2.thanhnien.vn/528068263637045248/2023/6/5/hcm-1685938434919901822107.jpg", "Mặt tiền Bưu điện Trung tâm", true, 0),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kien-truc-buu-dien-trung-tam-sai-gon-3.jpg", "Nội thất trần vòm", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/buu-dien-thanh-pho-ban-dem-1692393906.jpg", "Bưu điện về đêm", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/quay-gui-thu-buu-dien-thanh-pho-1692393928.jpg", "Quầy dịch vụ", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/buu-dien-thanh-pho-1-1736393709.jpg", "Góc chụp toàn cảnh", false, 4),
            img("https://kenh14cdn.com/k:thumb_w/600/semDvPi1aN9DfITbkDIeRqM1dwMsLc/Image/2015/ngay-7/1-b12be/buu-dien-trung-tam-lac-long-giua-khong-gian-sai-gon-vi-mau-vang-ruc.jpg", "Đối diện Nhà thờ Đức Bà", false, 5)
        ))
        .content(List.of(
            heading("Bưu điện – hoài niệm Sài Gòn xưa"),
            quote("""
                    Vòm vàng ôm tiếng bước,
                    kim đồng hồ chạm trưa;
                    tem nhỏ mang lời phố,
                    thư bay qua nắng xưa.
                """),
            paragraph("Bưu điện Trung tâm là một trong những công trình Pháp đẹp nhất ở Sài Gòn: mặt tiền màu vàng – trắng cổ điển, đồng hồ trung tâm, hàng cửa vòm đối xứng. Bước vào sảnh là cảm giác “time travel”: trần vòm cao, quầy gỗ, sàn gạch hoa và tiếng gọi số dịch vụ vang nhẹ."),
            heading("Cách tham quan gợi ý (45–75 phút)"),
            paragraph("Đứng ngoài quảng trường quan sát mặt đứng và đồng hồ → vào sảnh chính, ngẩng nhìn kết cấu vòm thép, quầy gỗ → dừng lâu ở hai tấm bản đồ vẽ tay hai bên (Sài Gòn – Chợ Lớn xưa; tuyến viễn thông Đông Dương) → ghé quầy tem – bưu thiếp để gửi vài dòng cho chính mình hoặc người thân."),
            imageBlock(img("https://static.vinwonders.com/production/buu-dien-trung-tam-sai-gon-5.jpg", "Không gian nội thất", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý"),
            paragraph("Hệ vòm thép – kính lấy sáng dọc trục chính, nhịp cột đều và mảng trang trí kiểu cổ điển; quầy gỗ chạy dài hai bên tạo nhịp điệu thị giác. Hãy để ý các họa tiết đúc trên đầu cột, biển chỉ dẫn chữ men xanh, ô cửa bán nguyệt và những chi tiết đồng cũ ‘kể chuyện’ qua lớp thời gian."),
            heading("Gửi thư – chơi tem & quà lưu niệm"),
            paragraph("Mua bưu thiếp chủ đề Sài Gòn, viết vài dòng rồi dán tem và gửi ngay tại quầy – một trải nghiệm rất “đúng chất bưu điện”. Khu lưu niệm có tem chủ đề, con dấu kỷ niệm, sổ tay, bút máy… Phù hợp làm quà nhỏ gọn; nhớ giữ hóa đơn nếu cần đổi."),
            heading("Góc chụp ảnh & ứng xử"),
            paragraph("Chụp chính diện từ ngoài quảng trường để lấy trọn mặt tiền; vào trong, đứng ở trục giữa, lùi vài nhịp để vòm nối vòm tạo chiều sâu. Tránh dùng flash, không đứng chắn lối quầy dịch vụ, giữ trật tự để tôn trọng không gian làm việc."),
            gallery(List.of(
                img("https://images2.thanhnien.vn/528068263637045248/2023/6/5/hcm-1685938434919901822107.jpg", "Mặt tiền bưu điện", false, 0),
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kien-truc-buu-dien-trung-tam-sai-gon-3.jpg", "Trần vòm & quầy gỗ", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/quay-gui-thu-buu-dien-thanh-pho-1692393928.jpg", "Quầy gửi thư", false, 2)
            )),
            heading("Kết hợp tham quan lân cận"),
            paragraph("Băng qua đường là Nhà thờ Đức Bà; cách vài trăm mét có phố sách Nguyễn Văn Bình. Có thể kết hợp một vòng bộ tam: Nhà thờ – Bưu điện – phố sách, rồi nghỉ cà phê dưới hàng me."),
            quote("“Bước vào là chạm ngay hơi thở Sài Gòn xưa.”"),
            divider(),
            mapBlock(new double[]{106.7019, 10.7793})
        ))
        .build();
  }

  /** 3) Chợ Bến Thành */
  private static PlaceDoc poiChoBenThanh() {
    String slug = "cho-ben-thanh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Chợ Bến Thành")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Khu chợ biểu tượng với tháp đồng hồ, đặc sản – lưu niệm phong phú.")
        .description("Sầm uất từ sáng đến tối; có khu ẩm thực trong chợ và phố ẩm thực đêm xung quanh.")
        .addressLine("Lê Lợi – Phan Bội Châu – Phan Chu Trinh, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.6983, 10.7725})
        .images(withCover(
            img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Mặt tiền Chợ Bến Thành", true, 0),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Cho-Ben-Thanh-4.jpg", "Không khí tấp nập", false, 1),
            img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/A1da8f00bea27dfc24b3.jpg", "Bên trong chợ", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-12-1736393709.jpg", "Đêm Bến Thành", false, 3),
            img("", "Tháp đồng hồ", false, 4),
            img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/Ed1de73ada9819c64089.jpg", "Du khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Bến Thành – chợ của người Sài Gòn"),
            quote("""
                    Tiếng rao hong nắng sớm,
                    mùi quế quện tiêu gừng;
                    bước chợ quen nhịp thở,
                    tay gói chút phố phường.
                """),
            paragraph("Chợ Bến Thành là biểu tượng nhộn nhịp bậc nhất của Sài Gòn: từ tháp đồng hồ mặt tiền đến mê cung gian hàng bên trong. Đi sớm buổi sáng để thoáng và hàng mới, trưa – chiều khách đông, tối quanh chợ bật đèn thành tuyến ẩm thực đêm sầm uất. Mang balo gọn và một ít tiền lẻ để mua nhanh – gọn."),
            heading("Khu hàng khô – đặc sản – quà mang về"),
            paragraph("Mực khô, cá khô, bò khô, trái cây sấy, cà phê, trà… thường có mẫu thử và nhận hút chân không. Trước khi mua, kiểm tra hạn dùng – trọng lượng tịnh – độ kín gói; so sánh 2–3 quầy về độ thơm – độ khô – độ dày lát. Với hàng có nước sốt, yêu cầu bọc kép để tránh rò rỉ trong vali."),
            heading("Mẹo trả giá & ứng xử văn minh"),
            paragraph("Giá có biên độ thương lượng nhẹ, nhất là khi mua nhiều. Hãy mỉm cười hỏi giá, xin “giá tốt nếu lấy 2–3 phần”, tránh mặc cả quá sâu. Khi không mua, cảm ơn và di chuyển sang quầy khác. Không sờ – mở sản phẩm khi chưa xin phép; xếp hàng gọn ở lối hẹp."),
            heading("Ẩm thực trong chợ & phố đêm quanh khu vực"),
            paragraph("Khu ẩm thực trong chợ có cơm tấm, bún mắm, bún bò, chè… Chọn quầy xoay vòng nhanh, dụng cụ sạch, gọi suất nhỏ để thử nhiều món. Buổi tối, dọc các tuyến Phan Bội Châu – Phan Chu Trinh có nhiều quầy nướng – hải sản – sinh tố; ngồi gọn trong vạch, không chắn lối đi."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-khu-an-uong-1693128182.jpg", "Quầy hàng trong chợ", false, 0)),
            heading("Góc chụp ảnh & khung giờ đẹp"),
            paragraph("Sáng sớm: chụp chính diện tháp đồng hồ khi nắng xiên; trưa: dùng hiên che để cân sáng, lấy chiều sâu dãy quầy; tối: bắt ánh đèn và bảng hiệu, đứng chéo 30–45° để thấy nhịp vòm cửa. Tránh đứng giữa dòng xe trước cổng, giữ an toàn khi băng qua đường."),
            heading("Thanh toán & lưu ý an toàn"),
            paragraph("Nhiều quầy nhận tiền mặt và chuyển khoản; kiểm hóa đơn – hàng hóa trước khi rời quầy. Giữ ví – điện thoại ở ngăn trước, đeo balo phía trước lúc đông. Với hàng dễ mùi (mắm, khô), xin bọc kín và dán nhãn để thuận tiện vận chuyển."),
            gallery(List.of(
                img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Mặt tiền tháp đồng hồ", false, 0),
                img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/A1da8f00bea27dfc24b3.jpg", "Bên trong chợ", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-12-1736393709.jpg", "Đêm Bến Thành", false, 2)
            )),
            quote("“Muốn thấy nhịp sống Sài Gòn – vào chợ.”"),
            divider(),
            mapBlock(new double[]{106.6983, 10.7725})
        ))
        .build();
  }

  /** 4) Phố đi bộ Nguyễn Huệ */
  private static PlaceDoc poiPhoDiBoNguyenHue() {
    String slug = "pho-di-bo-nguyen-hue";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Phố đi bộ Nguyễn Huệ")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Trục quảng trường trung tâm, check-in hè phố, toà nhà cổ – hiện đại hoà quyện.")
        .description("Buổi tối nhộn nhịp nhạc nước, nghệ sĩ đường phố; hàng quán – café apartment dọc hai bên.")
        .addressLine("Nguyễn Huệ, Bến Nghé, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.7045, 10.7733})
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Phố đi bộ Nguyễn Huệ", true, 0),
            img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue3.jpg", "Quảng trường rộng", false, 1),
            img("https://static.vinwonders.com/production/pho-di-bo-nguyen-hue-o-dau-banner-1.jpg", "Đường hoa dịp Tết", false, 2),
            img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue6.jpg", "UBND TP và quảng trường", false, 3),
            img("https://images2.thanhnien.vn/thumb_w/640/528068263637045248/2023/12/31/h5-17040045604281668860224.jpg", "Nguyễn Huệ về đêm", false, 4),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MN/the%20cafe%20apartments/thumbs.jpg", "Café apartment", false, 5)
        ))
        .content(List.of(
            heading("Nguyễn Huệ – nhịp sống trẻ của Sài Gòn"),
            quote("""
                    Gió mang mùi phố mới,
                    đèn rót xuống vai người;
                    nhạc nước reo nhịp bước,
                    tiếng cười chạy ngang trời.
                """),
            paragraph("Trục quảng trường trung tâm nối từ UBND TP đến bến Bạch Đằng, buổi tối rộn ràng nhất với nhạc nước, nghệ sĩ đường phố và dòng người tản bộ. Hai bên là dãy cao ốc, toà nhà cổ và các căn hộ café (The Café Apartments) cho view nhìn xuống quảng trường rất “đã”."),
            heading("Thời điểm & cách đi"),
            paragraph("Đẹp nhất: 18:30–21:30 (thứ Sáu–Chủ nhật). Gửi xe tại hầm phố đi bộ hoặc các TTTM lân cận rồi đi bộ; mang giày thoải mái, chai nước nhỏ. Ngày Tết, tuyến này thành “Đường hoa” – đông hơn bình thường, nên đi sớm hoặc trễ để dễ chụp hình."),
            imageBlock(img("https://images2.thanhnien.vn/thumb_w/640/528068263637045248/2023/12/31/h5-17040045604281668860224.jpg", "Nguyễn Huệ về đêm", false, 0)),
            heading("Điểm check-in gợi ý"),
            paragraph("Đầu phía UBND TP: khung chính diện trục quảng trường – kiến trúc Pháp cổ. Giữa trục: khu nhạc nước và ghế nghỉ. Cuối trục về phía sông: bến Bạch Đằng, gió mát – thuận ngắm skyline. The Café Apartments: chọn tầng trung để có góc nhìn nghiêng xuống dòng người."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Chính diện từ giữa trục để lấy chiều sâu; chụp chéo 30–45° để gom tiền cảnh – nền nhà cổ. Ảnh đêm: giảm tốc 1/5–1s (nếu có tripod mini) bắt vệt đèn xe; tránh dùng flash vào người biểu diễn. Khi chụp đông người, đứng gọn bên vạch, không chắn lối."),
            heading("Ăn uống & dạo quanh"),
            paragraph("Hai bên là dãy quán nước, kem, trà chanh; trong các hẻm nhỏ có nhiều hàng ăn. Gần đó có phố sách Nguyễn Văn Bình (≈600m) và Nhà thờ Đức Bà – Bưu điện để kết hợp đi bộ một vòng."),
            heading("Ứng xử & an toàn"),
            paragraph("Không xả rác, không trèo lên bồn hoa/đài phun; giữ ví – điện thoại gọn khi đông. Tôn trọng khoảng cách với người biểu diễn, chụp ảnh xin phép khi cần; gửi xe đúng nơi quy định."),
            gallery(List.of(
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Trục quảng trường trung tâm", false, 0),
                img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue3.jpg", "Không gian mở rộng rãi", false, 1),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MN/the%20cafe%20apartments/thumbs.jpg", "The Café Apartments", false, 2)
            )),
            quote("“Cuối tuần – nhạc, đèn và năng lượng.”"),
            divider(),
            mapBlock(new double[]{106.7045, 10.7733})
        ))
        .build();
  }

  /** 5) Dinh Độc Lập (Hội trường Thống Nhất) */
  private static PlaceDoc poiDinhDocLap() {
    String slug = "dinh-doc-lap";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Dinh Độc Lập (Hội trường Thống Nhất)")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Công trình lịch sử – kiến trúc hiện đại, chứng nhân thời khắc 30/4/1975.")
        .description("Tham quan phòng khánh tiết, hầm chỉ huy, sân đỗ trực thăng; khuôn viên cây xanh giữa trung tâm.")
        .addressLine("135 Nam Kỳ Khởi Nghĩa, Bến Thành, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.6955, 10.7769})
        .images(withCover(
            img("https://hn.ss.bfcplatform.vn/tckt/2020/04/20A04027-01-768x512.jpg", "Mặt tiền Dinh Độc Lập", true, 0),
            img("https://photo.znews.vn/w860/Uploaded/lerl/2014_04_30/NHH_5511.jpg", "Sân thượng – bãi đỗ trực thăng", false, 1),
            img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-redz.jpg", "Phòng họp/hầm chỉ huy", false, 2),
            img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-OAbU.jpg", "Nội thất khánh tiết", false, 3),
            img("https://phuot3mien.com/wp-content/uploads/2021/04/dinh-doc-lap-24.jpg", "Khuôn viên xanh", false, 4),
            img("https://vietnamtourism.vn//imguploads/tourist/2014/TPHoChiMinh/DinhDocLap/30Dthongnhat01.jpg", "Cổng Dinh", false, 5)
        ))
        .content(List.of(
            heading("Dinh Độc Lập – dấu ấn lịch sử hiện đại"),
            quote("""
                    Hành lang nghiêng bóng nắng,
                    trang sử mở trong tay;
                    bước qua hầm chỉ huy,
                    nghe thời khắc còn lay.
                """),
            paragraph("Dinh Độc Lập là biểu tượng kết hợp giữa lịch sử và kiến trúc hiện đại nhiệt đới: mặt đứng thoáng với lam bê tông, sân vườn rộng và trục nghi lễ trang trọng. Đây cũng là nơi ghi dấu thời khắc 30/4/1975 – xe tăng tiến qua cổng, mở ra chương mới của đất nước."),
            heading("Lộ trình tham quan gợi ý (60–90 phút)"),
            paragraph("Mua vé tại cổng → vào khối nhà chính thăm phòng khánh tiết, phòng họp nội các, phòng tiếp khách quốc lễ → lên sân thượng xem bãi đỗ trực thăng → xuống tầng hầm chỉ huy (phòng thông tin, bản đồ) → dạo khuôn viên cây xanh trước khi ra cổng. Có audio guide đa ngôn ngữ, nên dùng để theo mạch câu chuyện."),
            imageBlock(img("https://statics.vinpearl.com/dinh-doc-lap-8_1624805286.jpg", "Biểu tượng 30/4", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý"),
            paragraph("Mặt tiền có hệ lam đan dày giúp chống nắng, thông gió tự nhiên; nội thất khánh tiết dùng gỗ, thảm, đèn chùm, bài trí theo nghi lễ. Ở hầm chỉ huy, quan sát máy điện đàm, bản đồ tác chiến, phòng trú ẩn – mỗi hiện vật đều đi kèm chú thích rõ ràng."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Chính diện từ thảm cỏ để lấy trọn mặt đứng và cờ; từ tầng cao, chụp chéo 30–45° cho chiều sâu các lam che nắng; sân thượng có trực thăng – giữ khoảng cách an toàn, không tựa lan can. Ánh sáng đẹp: 8:00–10:00 và 15:30–17:30."),
            heading("Ứng xử & an toàn"),
            paragraph("Giữ trật tự trong phòng trưng bày, không chạm vào hiện vật; tắt flash ở khu vực có biển báo; đi theo lối chỉ dẫn một chiều tại hầm. Khuôn viên rộng – mang nước nhỏ, giày đế êm; giữ vé đến cuối lộ trình."),
            gallery(List.of(
                img("https://hn.ss.bfcplatform.vn/tckt/2020/04/20A04027-01-768x512.jpg", "Mặt tiền dinh", false, 0),
                img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-OAbU.jpg", "Phòng khánh tiết", false, 1),
                img("https://photo.znews.vn/w860/Uploaded/lerl/2014_04_30/NHH_5511.jpg", "Sân đỗ trực thăng", false, 2)
            )),
            quote("“Một chương lịch sử hiện diện trong từng căn phòng.”"),
            divider(),
            mapBlock(new double[]{106.6955, 10.7769})
        ))
        .build();
  }

  /** 6) Bảo tàng Chứng tích Chiến tranh */
  private static PlaceDoc poiBaoTangChungTichChienTranh() {
    String slug = "bao-tang-chung-tich-chien-tranh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bảo tàng Chứng tích Chiến tranh")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Bảo tàng nổi tiếng quốc tế về hậu quả chiến tranh và khát vọng hoà bình.")
        .description("Trưng bày ảnh tư liệu, hiện vật, xe – máy bay ngoài trời; nội dung nặng cảm xúc, nên cân nhắc khi đi cùng trẻ nhỏ.")
        .addressLine("28 Võ Văn Tần, Phường 6, Quận 3, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.6950, 10.7798})
        .images(withCover(
            img("https://cdn.xanhsm.com/2024/11/291a4f41-bao-tang-chung-tich-chien-tranh-1.jpg", "Mặt tiền bảo tàng", true, 0),
            img("https://hoabinhphuquoc.com.vn/files/files/Tin%20tuc/bang-tang-chung-tich-chien-tranh-3.jpg", "Khu trưng bày ngoài trời", false, 1),
            img("https://cdn.xanhsm.com/2024/11/4b49f4cf-bao-tang-chung-tich-chien-tranh-7.jpg", "Không gian triển lãm", false, 2),
            img("https://cdn.xanhsm.com/2024/11/4c12aacc-bao-tang-chung-tich-chien-tranh-6.jpg", "Hiện vật chiến tranh", false, 3),
            img("https://resource.kinhtedothi.vn/resources2025/1/users/63/z6550087015601-28ffa7e693cd7254a26003f3c63c1a63-1745827081.jpg", "Sân trước bảo tàng", false, 4),
            img("https://www.vietfuntravel.com.vn/image/data/Blog/cam-nang/tham-quan-bao-tang-chung-tich-chien-tranh/tham-quan-bao-tang-chung-tich-chien-tranh-2.jpg", "Du khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Chứng tích Chiến tranh – để nhớ và để hiểu"),
            quote("""
                    Tường ảnh rưng màu khói,
                    chữ cũ kể đêm dài;
                    bước chậm qua từng khung,
                    nhớ để quý ngày mai.
                """),
            paragraph("Bảo tàng Chứng tích Chiến tranh là điểm đến giàu giá trị giáo dục về hậu quả chiến tranh và khát vọng hoà bình. Không gian trưng bày theo mạch thời gian – chủ đề, từ ảnh tư liệu, hiện vật cá nhân đến khu triển lãm ngoài trời với xe – máy bay. Nội dung nặng cảm xúc, đặc biệt ở các phòng về nạn nhân chiến tranh, nên chuẩn bị tâm lý và cân nhắc khi đi cùng trẻ nhỏ."),
            heading("Cách tham quan gợi ý (60–120 phút)"),
            paragraph("Đi tuyến từ tổng quan → phòng ảnh phóng sự quốc tế → phòng chuyên đề (chất độc hoá học, bom mìn, tù binh…) → khu hiện vật ngoài trời (xe tăng, máy bay, trực thăng) → không gian tưởng niệm. Ở mỗi phòng, dành 5–10 phút đọc bảng tóm tắt bối cảnh trước khi xem ảnh/hiện vật để tránh bị ngợp thông tin."),
            heading("Những điểm nên dừng lâu"),
            paragraph("Các bộ ảnh phóng sự đoạt giải quốc tế; thư – nhật ký – đồ dùng cá nhân của nạn nhân và phóng viên chiến trường; bản đồ – số liệu tổng hợp giúp đặt sự kiện vào bối cảnh. Hãy đối chiếu ngày tháng, địa danh và nguồn tư liệu được ghi rõ dưới mỗi ảnh để hiểu chuẩn xác."),
            heading("Ứng xử trong không gian trưng bày"),
            paragraph("Giữ thái độ trang nghiêm; tắt flash, không chạm vào hiện vật, không nói lớn – đùa cợt, nhất là tại khu tưởng niệm. Một số khu vực hạn chế quay/chụp, vui lòng tôn trọng biển báo. Nếu đi theo nhóm, đứng gọn một bên khi thảo luận để không chắn lối người khác."),
            imageBlock(img("https://resource.kinhtedothi.vn/resources2025/1/users/63/z6550122078373-21c550297942586975206d2ede2ecb53-1745827587.jpg", "Xe tăng ngoài trời", false, 0)),
            heading("Khu trưng bày ngoài trời"),
            paragraph("Sân ngoài trời tập trung xe tăng, máy bay, trực thăng, bom – đạn đã tháo ngòi. Đọc bảng thông tin đi kèm để biết xuất xứ – niên đại – vai trò chiến thuật. Giữ khoảng cách an toàn, không trèo lên hiện vật; trẻ nhỏ cần có người lớn kèm sát."),
            heading("Gợi ý cho gia đình & nhóm học tập"),
            paragraph("Với trẻ em, chọn vài phòng nhẹ hơn trước, giải thích khái niệm hoà bình – nhân ái và chỉ xem nhanh các khu ảnh nhạy cảm. Nhóm sinh viên có thể phân công: người ghi chú mốc thời gian, người chụp lại (khu cho phép) bảng nội dung, người tổng hợp câu hỏi để thảo luận sau buổi tham quan."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Trong nhà: đứng chéo 30–45° so với nguồn sáng để giảm phản chiếu lên kính; tuyệt đối tắt flash. Ngoài trời: chọn buổi sáng/chiều muộn để ánh sáng mềm, chụp cận chi tiết số hiệu – phù hiệu trên thân xe/máy bay để làm chú thích ảnh."),
            heading("Khả năng tiếp cận & lưu ý an toàn"),
            paragraph("Lối đi bằng phẳng, có thang/cầu thang ở vài khu; mang giày đế êm. Một số phòng điều hoà mát sâu – nên mang áo khoác mỏng. Giữ tư trang gọn gàng ở khu đông; tôn trọng hàng lối khi cao điểm khách tham quan."),
            gallery(List.of(
                img("https://cdn.xanhsm.com/2024/11/291a4f41-bao-tang-chung-tich-chien-tranh-1.jpg", "Mặt tiền bảo tàng", false, 0),
                img("https://hoabinhphuquoc.com.vn/files/files/Tin%20tuc/bang-tang-chung-tich-chien-tranh-3.jpg", "Khu trưng bày ngoài trời", false, 1),
                img("https://cdn.xanhsm.com/2024/11/4b49f4cf-bao-tang-chung-tich-chien-tranh-7.jpg", "Không gian triển lãm", false, 2)
            )),
            quote("“Xem để hiểu, để trân trọng hoà bình.”"),
            divider(),
            mapBlock(new double[]{106.6950, 10.7798})
        ))
        .build();
  }

  /** 7) Nhà hát TP. HCM (Opera House / Municipal Theatre) */
  private static PlaceDoc poiNhaHatThanhPho() {
    String slug = "nha-hat-thanh-pho-ho-chi-minh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Nhà hát TP. Hồ Chí Minh (Municipal Theatre)")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Kiến trúc tân cổ điển Pháp, không gian biểu diễn nghệ thuật giữa trung tâm.")
        .description("Check-in bậc thềm trước giờ diễn; xung quanh là khách sạn – TTTM sang trọng và phố Nguyễn Huệ kế cận.")
        .addressLine("07 Công Trường Lam Sơn, Bến Nghé, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.7040, 10.7763})
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-1.jpg", "Mặt tiền Nhà hát Thành phố", true, 0),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-2.jpg", "Nội thất khán phòng", false, 1),
            img("https://dulich3mien.vn/wp-content/uploads/2021/12/hinh-anh-nha-hat-thanh-pho-1-1.jpg", "Nhà hát về đêm", false, 2),
            img("https://artlive.vn/wp-content/uploads/2024/01/nhtp-2048x1361.jpg", "Chi tiết phù điêu", false, 3),
            img("https://ghiensaigon.com/wp-content/uploads/2025/05/cong-truong-lam-son-750x375.jpg", "Quảng trường Lam Sơn", false, 4),
            img("https://static.vinwonders.com/production/nha-hat-thanh-pho-banner-min.jpg", "Góc chụp toàn cảnh", false, 5)
        ))
        .content(List.of(
            heading("Nhà hát Thành phố – nét duyên cổ điển"),
            quote("""
                    Vòm cong ôm nhịp phố,
                    phù điêu chạm mùa xưa;
                    đèn lên như tiếng nhạc,
                    sân khấu mở trang thơ.
                """),
            paragraph("Công trình tân cổ điển Pháp giữa trung tâm Quận 1, mặt đứng duyên dáng với hàng cột, vòm cong, phù điêu và bậc thềm rộng – nơi người Sài Gòn hay ngồi chuyện trò trước giờ diễn. Buổi tối đèn vàng bật lên làm nổi mảng phù điêu và chi tiết trang trí, tạo cảm giác như một góc Paris giữa đô thị hiện đại."),
            heading("Thời điểm & cách trải nghiệm"),
            paragraph("Nếu có dịp, thử một đêm xem diễn (AO Show, nhạc giao hưởng, múa đương đại…) để cảm nhận âm thanh trong khán phòng. Không xem show thì ghé lúc hoàng hôn hoặc sau 19:00 để chụp ánh đèn. Khu vực xung quanh nhiều TTTM/khách sạn, gửi xe hầm gần đó rồi đi bộ ra quảng trường Lam Sơn."),
            imageBlock(img("https://statics.vinpearl.com/nha-hat-thanh-pho-9_1629694049.jpg", "Ánh đèn sân khấu", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý"),
            paragraph("Mặt tiền đối xứng, bậc thềm cao, vòm trung tâm và hai khối cánh tạo nhịp điệu; phù điêu trang trí theo mô-típ cổ điển; cửa sổ bán nguyệt, lan can sắt uốn; bên trong là khán phòng hình móng ngựa, trần cao, âm học tốt. Đứng gần để quan sát hoa văn, phào chỉ và các mảng tu bổ theo thời gian."),
            heading("Check-in bậc thềm & mẹo chụp ảnh"),
            paragraph("Chính diện: lùi về giữa quảng trường Lam Sơn để lấy trọn mặt đứng và bậc thềm. Góc chéo 30–45°: nhấn khối vòm và chiều sâu các cánh nhà. Ảnh đêm: giảm tốc 1/5–1s (nếu có tripod mini) để bắt vệt đèn xe; tránh dùng flash thẳng vào người khác. Khi đông, đứng gọn sát mép bồn cây, không chắn lối lên xuống bậc thềm."),
            heading("Kết hợp dạo quanh"),
            paragraph("Từ Nhà hát, đi bộ vài phút ra phố đi bộ Nguyễn Huệ (ngắm nhạc nước – skyline), hoặc ghé Vincom Đồng Khởi, khách sạn lịch sử, các quán cà phê view phố. Có thể xếp lịch: chiều tối dạo Nguyễn Huệ → chụp Nhà hát lên đèn → xem show → ăn nhẹ khu Đồng Khởi."),
            heading("Ứng xử & lưu ý"),
            paragraph("Trang phục lịch sự nếu vào xem diễn; giữ trật tự, tắt chuông điện thoại trong khán phòng. Ngoài trời, không leo, ngồi trên lan can; không xả rác ở quảng trường. Khu vực đông khách – giữ tư trang khi chụp ảnh."),
            gallery(List.of(
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-1.jpg", "Mặt tiền tân cổ điển", false, 0),
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-2.jpg", "Nội thất khán phòng", false, 1),
                img("https://dulich3mien.vn/wp-content/uploads/2021/12/hinh-anh-nha-hat-thanh-pho-1-1.jpg", "Nhà hát về đêm", false, 2)
            )),
            quote("“Một góc Paris giữa trung tâm Sài Gòn.”"),
            divider(),
            mapBlock(new double[]{106.7040, 10.7763})
        ))
        .build();
  }

  /** 8) Bitexco Financial Tower – Saigon Skydeck */
  private static PlaceDoc poiBitexcoSkydeck() {
    String slug = "bitexco-saigon-skydeck";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bitexco Financial Tower – Saigon Skydeck")
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription("Toà tháp biểu tượng với đài quan sát Skydeck ngắm toàn cảnh Sài Gòn.")
        .description("Lên đài quan sát, ngắm hoàng hôn và đêm thành phố; trung tâm thương mại – café lounge trong toà nhà.")
        .addressLine("02 Hải Triều, Bến Nghé, Quận 1, TP. HCM")
        .countryCode("VN").provinceName("TP. Hồ Chí Minh")
        .location(new double[]{106.7058, 10.7719})
        .images(withCover(
            img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2013_05_02/120512kpbitexco04_de1c3.jpg", "Bitexco nhìn từ bờ sông", true, 0),
            img("https://travelwithminh.com/wp-content/uploads/2025/06/sky-dechk-89-melbourne-australia-87-950x500.jpg", "View từ Skydeck", false, 1),
            img("https://cdn.xanhsm.com/2024/12/4e7794d1-toa-nha-tai-chinh-bitexco-5.jpg", "Sàn trực thăng biểu tượng", false, 2),
            img("https://static.tuoitre.vn/tto/i/s626/2015/04/25/41B6gnc8.jpg", "Tháp về đêm", false, 3),
            img("https://images.vietnamtourism.gov.vn/en///images/2015/thap-Bitexco.jpg", "Toàn cảnh tòa nhà Bitexco", false, 4),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitexco_Financial_Tower_20022012_cropped.JPG/500px-Bitexco_Financial_Tower_20022012_cropped.JPG", "Tháp tài chính Bitexco", false, 5)
        ))
        .content(List.of(
            heading("Bitexco – góc nhìn 360° Sài Gòn"),
            quote("""
                    Gió quét qua tầng mây,
                    phố thắp nghìn ô sáng;
                    sông uốn mình dưới tháp,
                    hoàng hôn rót miên man.
                """),
            paragraph("Bitexco Financial Tower là một trong những biểu tượng đường chân trời của trung tâm Sài Gòn, nổi bật với sàn trực thăng vươn ra như một cánh hoa. Lên Saigon Skydeck, bạn có thể quét trọn 360°: sông Sài Gòn uốn lượn, những trục đại lộ rực sáng và mảng xanh xen giữa các khối nhà."),
            heading("Khung giờ & trải nghiệm lý tưởng"),
            paragraph("Khuyến nghị 16:30–18:30: lên khi trời còn xanh để chụp cảnh trong vắt, ở lại đón hoàng hôn và khoảnh khắc ‘thành phố lên đèn’. Vào các ngày nắng trong, tầm nhìn xa giúp thấy rõ cả bến Bạch Đằng, phố đi bộ Nguyễn Huệ và các toà tháp lân cận."),
            heading("Mẹo ngắm & chụp từ Skydeck"),
            paragraph("Đi vòng theo kim đồng hồ để không bỏ lỡ các hướng chính: Đông (sông & cảng), Nam (khu Thủ Thiêm – bán đảo), Tây (Quận 1 – Bến Thành), Bắc (các trục hiên đại). Đặt ống kính/điện thoại áp gần kính để giảm phản xạ; nếu cần, dùng mũ/áo tối màu che bớt ánh đèn sau lưng."),
            imageBlock(img("https://congvientaodan.com/wp-content/uploads/2024/10/bitexco-financial-tower-skydeck-4.jpg", "Ngắm toàn cảnh từ Skydeck", false, 0)),
            heading("Café – lounge & tiện ích trong toà nhà"),
            paragraph("Sau khi ngắm cảnh, bạn có thể ghé café/lounge trong toà tháp để nghỉ chân, thưởng thức đồ uống nhìn xuống dòng xe. Khu mua sắm tầng thấp có quầy lưu niệm, thuận tiện mua một chiếc postcard/nam châm ‘skyline’ làm kỷ niệm."),
            heading("Góc chụp Bitexco từ mặt đất"),
            paragraph("Muốn chụp ngoại thất tháp: đứng bên bờ sông đoạn bến Bạch Đằng để lấy trọn khối tháp và mặt nước; hoặc ở giao Hải Triều – Hồ Tùng Mậu để nhấn khối ‘cánh hoa’ (sàn trực thăng) vươn ra. Ảnh đêm đẹp nhất khi trời còn ‘giờ xanh’, đèn tháp bắt đầu bật."),
            heading("An toàn & lưu ý"),
            paragraph("Tuân thủ nội quy Skydeck: không tựa người quá sát kính, không dùng chân máy lớn gây vướng lối (nếu cần, dùng tripod mini); giữ vé đến khi ra. Thang máy lên tầng cao có thể thay đổi theo khung giờ – theo biển chỉ dẫn và hướng dẫn của nhân viên."),
            gallery(List.of(
                img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2013_05_02/120512kpbitexco04_de1c3.jpg", "Bitexco bên sông Sài Gòn", false, 0),
                img("https://static.tuoitre.vn/tto/i/s626/2015/04/25/41B6gnc8.jpg", "Tháp về đêm", false, 1),
                img("https://images.vietnamtourism.gov.vn/en///images/2015/thap-Bitexco.jpg", "Toàn cảnh toà tháp", false, 2)
            )),
            quote("“360° Sài Gòn – đẹp nhất lúc hoàng hôn.”"),
            divider(),
            mapBlock(new double[]{106.7058, 10.7719})
        ))
        .build();
  }
}