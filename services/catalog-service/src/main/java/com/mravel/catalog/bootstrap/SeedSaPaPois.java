package com.mravel.catalog.bootstrap;

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
public class SeedSaPaPois {

  @Bean
  CommandLineRunner seedSaPaPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedSaPaPois] start");

      List<PlaceDoc> pois = List.of(
          poiFansipan(),
          poiBanCatCat(),
          poiNuiHamRong(),
          poiBanTaPhin(),
          poiDeoOQuyHo(),
          poiThacBac(),
          poiThacTinhYeu(),
          poiNhaThoDaSaPa()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedSaPaPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedSaPaPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedSaPaPois] done, inserted = " + inserted);
    };
  }

  // ===================== 8 POI FOR SA PA =====================

  /** 1) Fansipan – “Nóc nhà Đông Dương” */
  private static PlaceDoc poiFansipan() {
    String slug = "fansipan-noc-nha-dong-duong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Fansipan – Nóc nhà Đông Dương")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Đỉnh cao 3.143m với quần thể tâm linh và đài quan sát mây.")
        .description("Di chuyển bằng cáp treo, tàu leo núi; check-in cột mốc 3.143m, ngắm biển mây – bình minh ngoạn mục.")
        .addressLine("Sun World Fansipan Legend, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.7760, 22.3030})
        .images(withCover(
            img("https://media-cdn-v2.laodong.vn/storage/newsportal/2025/9/3/1568073/Duong-Len-Dinh-Fansi.jpg", "Cột mốc 3.143m", true, 0),
            img("https://cdn.nhandan.vn/images/d4247fbcbdde5a8f0a3e16787e1dd4454ff8e4f24f92f2adad15397f1c9349bd5398c8f078ed4c4cc554429a0933b26e847bfbc2c0e9abf15c290f80b1d1c6f7/f5-9772.jpg", "Biển mây Fansipan", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/cap-treo-fansipan-legend-2.jpg", "Cáp treo Fansipan", false, 2),
            img("https://cdn.nhandan.vn/images/a66ff20f6c1e5a179e007244f7bfd5c84ff8e4f24f92f2adad15397f1c9349bd1447082c7013e8e6b7357523e21ff1eed99086a63e4d59c6eaccd470b4d0a45ac2381094306107c33e4c998a37d06158/quan-the-tam-linh-9055.jpg", "Quần thể tâm linh", false, 3),
            img("https://umove.com.vn/data/media/503/images/Wanderlust-Tips-man-nhung-dia-diem-ngam-binh-minh-tuyet-dep-2.jpg", "Bình minh trên đỉnh", false, 4),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-thang-da.jpg", "Cầu thang đá trong mây", false, 5)
        ))
        .content(List.of(
            heading("Fansipan – chinh phục nóc nhà Đông Dương"),
            quote("""
                    Gió cuốn mây qua đỉnh,
                    nắng rót vàng lên vai;
                    một bậc rồi thêm bậc,
                    trời gần hơn mỗi ngày.
                """),
            paragraph("Ở cao độ 3.143m, Fansipan là nơi bạn chạm tay vào biển mây của dãy Hoàng Liên. Quần thể tâm linh, cột mốc đỉnh, các sân vọng cảnh và bậc đá len giữa mây tạo nên hành trình vừa thiêng liêng vừa mãn nhãn. Trên đỉnh gió mạnh, chênh nhiệt 6–12°C so với thị trấn Sa Pa—hãy mặc nhiều lớp, áo gió chống nước, găng/mũ mỏng và mang theo nước ấm."),

            heading("Đi lại & nhịp tham quan"),
            paragraph("• Ga dưới Sun World → cáp treo (khoảng 15–20 phút) → ga trên → tàu leo núi/đi bậc đá đoạn cuối lên cột mốc 3.143m."),
            paragraph("• Dự trù 2–3 giờ cho toàn hành trình (di chuyển + chụp ảnh + nghỉ). Khởi hành buổi sáng để gặp trời quang, gió ổn định hơn."),
            paragraph("• Người dễ say độ cao: ăn nhẹ trước khi đi, ngậm kẹo gừng; hít sâu, bước chậm ở đoạn bậc đá cuối."),

            heading("Mẹo ‘săn mây’ & thời tiết"),
            paragraph("• Sau đợt mưa đêm, sáng sớm thường có biển mây dày; gió đổi hướng có thể ‘xẻ mây’ trong 10–20 phút—kiên nhẫn chờ ở sân vọng cảnh."),
            paragraph("• Mùa khô, ngày quang cho tầm nhìn xa (thấy rõ thung lũng Mường Hoa – Ô Quy Hồ). Mùa lạnh có thể gặp sương muối—đi giày bám tốt."),
            paragraph("• Tránh đứng ở mép đón gió trực diện; nép vào lan can, đợi gió lặng để chụp bầu trời sắc nét."),

            imageBlock(img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/cap-treo-fansipan-legend-2.jpg", "Cabin lướt giữa tầng mây", false, 0)),

            heading("Quần thể tâm linh & ứng xử"),
            paragraph("Khi lên khu tượng – chánh điện: trang phục kín đáo, nói nhỏ, nhường lối trên bậc hẹp. Không leo trèo lên bệ/lan can để ‘lấy góc’. Nếu có nghi lễ, hạn chế chụp cận; tắt flash, đứng gọn một bên."),

            heading("An toàn trên cao"),
            paragraph("• Bậc đá ẩm dễ trơn: bám tay vịn, bước ngắn đều; tránh chạy khi thấy mây ùa đến che khuất tầm nhìn."),
            paragraph("• Trẻ nhỏ luôn trong tầm tay, người lớn tuổi nghỉ theo chu kỳ 10–15 phút. Không dùng ô khi gió mạnh—đội mũ, áo có mũ trùm."),
            paragraph("• Nếu chóng mặt do gió lạnh: vào khu trú gió (hành lang/sảnh chờ), uống nước ấm, hít thở chậm 2–3 phút."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Cột mốc 3.143m: đứng chếch 30° để thấy cả mốc và nền mây; đặt người ở 1/3 khung cho ảnh có chiều sâu."),
            paragraph("• Biển mây: đo sáng vùng trời, giảm EV 0.3–0.7 để giữ texture mây; đợi mây xẻ để lộ các lớp núi tầng tầng."),
            paragraph("• Bậc thang/lan can: chụp từ thấp lên, thêm người trong khung để lấy tỷ lệ; tránh khung giờ đông bằng cách đi sớm."),
            paragraph("• Ăn gian ‘trời xanh’: lúc ‘giờ xanh’ sau hoàng hôn/bình minh, bầu trời mịn—ảnh cityscape núi mây rất ‘ăn màu’."),

            heading("Lịch trình gợi ý 2–3 giờ"),
            paragraph("• 0’–20’: ga dưới → cáp treo; tranh thủ chụp dãy Hoàng Liên qua kính (áp ống sát kính giảm phản xạ)."),
            paragraph("• 20’–40’: ga trên → dạo quần thể tâm linh, làm nóng người trước khi leo bậc cuối."),
            paragraph("• 40’–70’: leo bậc đá lên cột mốc; xếp hàng trật tự, chụp 2–3 kiểu đặc trưng."),
            paragraph("• 70’–120’: vòng qua các sân vọng cảnh ‘săn mây’; nghỉ 5’ mỗi điểm, uống nước ấm rồi quay ga về."),

            heading("Kết hợp trải nghiệm quanh Sa Pa"),
            paragraph("Có thể nối cung Ô Quy Hồ (săn mây – đèo), thung lũng Mường Hoa (ruộng bậc thang), hoặc bản Cát Cát chiều muộn để chụp tông ‘giờ vàng’. Hạn chế lịch quá dày trong một ngày có gió lạnh."),

            gallery(List.of(
                img("https://media-cdn-v2.laodong.vn/storage/newsportal/2025/9/3/1568073/Duong-Len-Dinh-Fansi.jpg", "Cột mốc 3.143m giữa mây", false, 0),
                img("https://cdn.nhandan.vn/images/d4247fbcbdde5a8f0a3e16787e1dd4454ff8e4f24f92f2adad15397f1c9349bd5398c8f078ed4c4cc554429a0933b26e847bfbc2c0e9abf15c290f80b1d1c6f7/f5-9772.jpg", "Sóng mây cuộn trên dãy Hoàng Liên", false, 1),
                img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-thang-da.jpg", "Bậc đá len giữa mây", false, 2)
            )),

            quote("“Một bước lên mây, một tầm nhìn trọn núi.”"),
            divider(),
            mapBlock(new double[]{103.7760, 22.3030})
        ))
        .build();
  }

  /** 2) Bản Cát Cát */
  private static PlaceDoc poiBanCatCat() {
    String slug = "ban-cat-cat";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bản Cát Cát")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Bản làng Mông với guồng nước, thác nhỏ và nếp nhà gỗ đặc trưng.")
        .description("Tản bộ con dốc, thuê trang phục dân tộc, chụp ảnh cầu, guồng nước; thử đặc sản thắng cố, thịt xiên nướng.")
        .addressLine("San Sả Hồ, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.8460, 22.3280})
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sa-pa/ban-cat-cat/gia-ve-vao-tham-quan-ban-cat-cat-1.jpg", "Cổng vào bản Cát Cát", true, 0),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-5.jpg", "Guồng nước bản", false, 1),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-treo-ban-cat-cat.jpg", "Cầu gỗ giữa ruộng", false, 2),
            img("https://dulichvietnam.com.vn/kinh-nghiem/wp-content/uploads/2021/12/checkin-ban-cat-cat-1.jpg", "Trang phục dân tộc", false, 3),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/cat-cat-village/cat-cat-village-thumb.jpg", "Lối dốc quanh co", false, 4),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-1.jpg", "Thác Cát Cát", false, 5)
        ))
       .content(List.of(
            heading("Cát Cát – bản làng giữa thung lũng"),
            quote("""
                    Sương vờn mái gỗ nâu,
                    guồng quay lời suối hát;
                    bước nhỏ qua cầu tre,
                    hoa lan nghiêng bên bậc.
                """),
            paragraph("Cách trung tâm Sa Pa chỉ vài cây số, Cát Cát lọt thỏm giữa thung lũng Mường Hoa, nổi tiếng với nếp nhà gỗ của người Mông, guồng nước, thác nhỏ và các khung dệt thổ cẩm. Lối đi lát bậc dốc liên tục, hai bên là quầy hàng bản địa rực rỡ sắc vải. Trời ẩm dễ trơn—hãy mang giày đế bám và áo mưa mỏng khi có sương."),
            
            heading("Vài nét văn hoá bản Mông"),
            paragraph("Bản Cát Cát là nơi người Mông sinh sống lâu đời, giữ nghề dệt lanh, rèn bạc, đan mây tre. Hoa văn trên vải chàm tượng trưng cho núi, mây, ruộng bậc thang—mỗi hoạ tiết đều có câu chuyện. Khi ghé khu trình diễn, bạn có thể quan sát các công đoạn nhuộm chàm, sáp ong vẽ sáp—rửa sáp để lộ hoạ tiết."),
            
            heading("Lộ trình gợi ý 1–2 giờ"),
            paragraph("• Cổng vé → dốc lát đá xuống thung lũng (dừng chụp ‘view’ ruộng bậc thang)."),
            paragraph("• Khu guồng nước → cầu gỗ/ cầu tre → thác Cát Cát (nghỉ chân, chụp ảnh nước đổ)."),
            paragraph("• Khu trình diễn nghề thủ công → leo vòng một chiều trở lại (các quầy thổ cẩm, đồ nướng)."),
            imageBlock(img("https://ecotour.com.vn/wp-content/uploads/2025/04/check-in-cau-treo-cat-cat.jpg", "Cầu gỗ check-in", false, 0)),
            
            heading("Thời điểm & nhịp tham quan"),
            paragraph("• Sáng 07:30–10:00: sương mỏng, ánh xiên đẹp, ít đông—dễ chụp cầu, guồng."),
            paragraph("• Chiều 15:30–17:30: ‘giờ vàng’ nhuộm thung lũng; có thể đông hơn—đi sớm chút để giữ nhịp chậm."),
            paragraph("• Mùa lúa (tuỳ năm, thường 9–10): ruộng bậc thang vàng, ảnh rất ‘ăn màu’. Mùa sương lạnh cần thêm áo gió mỏng."),
            
            heading("Trải nghiệm & ứng xử"),
            paragraph("• Thuê trang phục dân tộc để chụp ảnh: hỏi rõ giá/giờ trả; giữ gìn trang phục, tránh ngồi lên lan can mỏng."),
            paragraph("• Tôn trọng đời sống bản địa: xin phép trước khi chụp cận chân dung, đặc biệt là trẻ em; hạn chế đưa tiền trực tiếp cho trẻ—có thể mua món nhỏ từ quầy người lớn để ủng hộ."),
            paragraph("• Không chen lấn trên cầu; nhường lối tại bậc hẹp; không “bẻ cảnh” (dẫm xuống ruộng bậc thang/hoa dại)."),
            
            heading("Ăn uống & đặc sản"),
            paragraph("• Đồ nướng bản: thịt xiên, ngô nếp, cơm lam, trứng nướng—ăn nóng rất ấm bụng ngày lạnh."),
            paragraph("• Thắng cố: mùi vị đậm, không hợp tất cả khẩu vị—nên thử phần nhỏ trước."),
            paragraph("• Mang chai nước 300–500ml và kẹo gừng cho đoạn dốc dài; nhớ gom rác mang ra khỏi bản."),
            
            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Guồng nước: đứng chéo 30–45° để thấy vòng quay + dòng suối tạo đường dẫn vào khung."),
            paragraph("• Cầu gỗ: chờ lúc vắng, đặt chủ thể ở 1/3 khung để lộ đường cong cầu và ruộng sau lưng."),
            paragraph("• Thác Cát Cát: muốn ‘mềm nước’ đặt tốc độ 1/10–1/4s; tựa máy vào lan can/bờ đá để chống rung; khép khẩu f/8–f/11."),
            paragraph("• Chân dung với nhà gỗ: chọn nền tường gỗ tối—đo sáng vào da, tránh ngược sáng gắt giữa trưa."),
            
            heading("An toàn & môi trường"),
            paragraph("Đường dốc ẩm dễ trơn: đi bước ngắn, bám tay vịn; tránh dép trơn. Không cho thú nuôi ăn ngẫu nhiên. Ruộng bậc thang là sinh kế—đừng bước xuống bờ ruộng. Giữ âm lượng nhỏ, không bật loa lớn."),
            
            heading("Lịch trình gợi ý (mẫu)"),
            paragraph("• 0’–15’: mua vé, khởi động, chụp view thung lũng đầu tuyến."),
            paragraph("• 15’–45’: guồng nước → cầu gỗ (set chính, chụp đủ góc dọc/ngang)."),
            paragraph("• 45’–70’: thác Cát Cát (ảnh nước chảy chậm), nghỉ nhẹ."),
            paragraph("• 70’–100’: khu nghề thủ công, mua một món nhỏ ủng hộ, quay vòng lên dốc về lại cổng."),
            
            gallery(List.of(
                img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-5.jpg", "Guồng nước bản", false, 0),
                img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-treo-ban-cat-cat.jpg", "Cầu gỗ qua ruộng", false, 1),
                img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-1.jpg", "Thác Cát Cát", false, 2)
            )),
            quote("“Đi thật chậm để nghe tiếng nước và nếp sống nơi thung lũng.”"),
            divider(),
            mapBlock(new double[]{103.8460, 22.3280})
        ))
        .build();
  }

  /** 3) Núi Hàm Rồng */
  private static PlaceDoc poiNuiHamRong() {
    String slug = "nui-ham-rong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Núi Hàm Rồng")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Khu du lịch cảnh quan ngay trung tâm thị trấn, view toàn Sa Pa.")
        .description("Đường đá bậc dễ đi; vườn lan, sân mây, cổng trời. Ngắm thị trấn và dãy Hoàng Liên Sơn từ trên cao.")
        .addressLine("Đường Hàm Rồng, TT. Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.8490, 22.3340})
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/Sapamay1.jpg", "Sân mây Hàm Rồng", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhTITC/Anhdanglai/57Donglongquang.jpg", "Lối bậc đá", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/vuon-hoa-ham-rong-sapa-4.jpg", "Vườn hoa – vườn lan", false, 2),
            img("https://www.kynghidongduong.vn/userfiles/images/tour-trong-nuoc/sapa/nui-ham-rong-view-thi-tran-sapa-du-lich-sapa-kynghidongduong-000.jpg", "View thị trấn Sa Pa", false, 3),
            img("https://static.vinwonders.com/production/vi-tri-nui-ham-rong.jpg", "Đỉnh đá độc đáo", false, 4),
            img("https://static.vinwonders.com/production/san-may-nui-ham-rong.jpg", "Mây phủ núi", false, 5)
        ))
       .content(List.of(
            heading("Hàm Rồng – vườn cảnh giữa mây"),
            quote("""
                    Sương quàng vai đá xám,
                    gió chải tóc rừng xanh;
                    một lối bậc lên đỉnh,
                    thị trấn nhỏ trong lành.
                """),
            paragraph("Ngay phía sau nhà thờ đá Sa Pa là lối vào khu du lịch Núi Hàm Rồng – quần thể cảnh quan kết hợp vườn hoa, vườn lan, sân mây và các điểm vọng cảnh nhìn trọn thị trấn cùng dãy Hoàng Liên. Đường bậc đá dễ đi, cây xanh che mát; sáng sớm trời quang là lúc ‘săn mây’ và đón nắng đẹp nhất."),

            heading("Thời điểm & tuyến tham quan"),
            paragraph("• 07:30–10:00: sương mỏng, nắng xiên, ít đông – lý tưởng cho ảnh vườn hoa và view thị trấn."),
            paragraph("• 15:30–17:30: ‘giờ vàng’ nhuộm ấm các mỏm đá; lưu ý quay xuống sớm khi sương kéo đến."),
            paragraph("• Tuyến chuẩn (1–2 giờ): cổng → vườn hoa → vườn lan → Sân Mây → Cổng Trời → điểm ngắm toàn cảnh Sa Pa (vòng một chiều, nhiều ghế nghỉ)."),

            imageBlock(img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhTITC/Anhdanglai/57Donglongquang.jpg", "Lối bậc đá giữa vườn xanh", false, 0)),

            heading("Góc ngắm & mẹo ảnh"),
            paragraph("• Sân Mây: dùng viền mỏm đá làm tiền cảnh, đặt đường chân trời ở 1/3 khung để bầu trời ‘sâu’ hơn."),
            paragraph("• Vườn lan – vườn hoa: đứng chếch 30–45° tạo nhiều lớp nền; khép khẩu f/5.6–f/8 để giữ nét cụm hoa gần."),
            paragraph("• View thị trấn: chờ khoảnh khắc mây tách; đo sáng vào vùng chân trời để giữ chi tiết mái nhà và thung lũng."),
            paragraph("• Mỏm đá đặc trưng: chụp từ thấp lên để khối đá ‘vươn’ hơn; luôn đứng trong vùng an toàn, không vượt lan can."),

            heading("Thực vật & địa chất (nhìn nhanh)"),
            paragraph("Khu vực là nền đá phong hoá tạo nên các khối ‘hàm rồng’ lạ mắt, xen giữa thảm thực vật á nhiệt đới. Mùa xuân – đầu hè vườn hoa rực rỡ; ngày ẩm dễ xuất hiện rêu/moss phủ đá—đẹp nhưng trơn, nên mang giày đế bám."),

            heading("Chuẩn bị & lưu ý"),
            paragraph("• Trang phục: áo khoác mỏng chống gió, giày bám tốt cho bậc đá ẩm; đem áo mưa mỏng nếu trời có sương."),
            paragraph("• Ứng xử: không bẻ hoa, không leo qua rào; nhường lối tại bậc hẹp; giữ yên lặng ở khu nhìn vọng cảnh."),
            paragraph("• Thời tiết đổi nhanh: nếu sương dày, chờ 5–10 phút ở điểm trú gió—gió đổi hướng thường ‘xé’ mây mở tầm nhìn."),

            heading("Lịch trình gợi ý 60–90 phút"),
            paragraph("• 0’–15’: qua vườn hoa – vườn lan, chụp các cụm hoa/đường bậc."),
            paragraph("• 15’–40’: lên Sân Mây, chụp toàn cảnh – thử khung dọc/ngang."),
            paragraph("• 40’–70’: tiếp Cổng Trời – điểm nhìn đỉnh; nghỉ 5’ uống nước."),
            paragraph("• 70’–90’: quay tuyến vòng xuống, dừng thêm 1–2 điểm mỏm đá an toàn để chụp ‘giờ vàng’ (nếu đi buổi chiều)."),

            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/Sapamay1.jpg", "Sân mây Hàm Rồng", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/vuon-hoa-ham-rong-sapa-4.jpg", "Vườn hoa – vườn lan", false, 1),
                img("https://www.kynghidongduong.vn/userfiles/images/tour-trong-nuoc/sapa/nui-ham-rong-view-thi-tran-sapa-du-lich-sapa-kynghidongduong-000.jpg", "View toàn thị trấn Sa Pa", false, 2)
            )),

            quote("“Ở Hàm Rồng, thị trấn như lọt thỏm giữa đại ngàn.”"),
            divider(),
            mapBlock(new double[]{103.8490, 22.3340})
        ))
        .build();
  }

  /** 4) Bản Tả Phìn */
  private static PlaceDoc poiBanTaPhin() {
    String slug = "ban-ta-phin";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bản Tả Phìn")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Bản Dao đỏ – H’Mông với nghề thổ cẩm, tắm lá thuốc nổi tiếng.")
        .description("Trải nghiệm nhuộm vải, thêu thổ cẩm, tắm lá Dao đỏ; ghé tu viện cổ Tả Phìn chụp ảnh gạch đá rêu phong.")
        .addressLine("Tả Phìn, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.9000, 22.3790})
        .images(withCover(
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/ban-ta-phin-sapa.jpg", "Khung cảnh bản Tả Phìn", true, 0),
            img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2015_01_16/taphin.jpg", "Người Dao đỏ", false, 1),
            img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc-8.jpg", "Thổ cẩm Tả Phìn", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn", false, 3),
            img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc.jpg", "Đồi ruộng bậc thang", false, 4),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/du-lich-ban-ta-phin-sapa.jpg", "Con đường làng", false, 5)
        ))
        .content(List.of(
            heading("Tả Phìn – sắc đỏ nơi sườn núi"),
            quote("""
                    Khăn đỏ nghiêng bên suối,
                    khói bếp gọi chiều về;
                    mũi chỉ chầm chậm múa,
                    hương lá ấm chân đê.
                """),
            paragraph("Nằm chếch về phía Đông Bắc thị trấn Sa Pa, Tả Phìn là nơi đồng bào Dao đỏ và H’Mông sinh sống, nổi tiếng với thổ cẩm và tắm lá thuốc. Con đường làng uốn quanh ruộng bậc thang dẫn tới tu viện cổ phủ rêu, nơi gạch đá kể những lớp thời gian. Không gian yên bình, thích hợp một buổi tham quan chậm rãi để cảm nhận nhịp sống miền sơn cước."),

            heading("Thời điểm & nhịp tham quan"),
            paragraph("• Sáng 08:00–10:30: sương tan, nắng xiên nhẹ—đi làng, xem dệt, chụp ruộng bậc thang."),
            paragraph("• Chiều 15:30–17:30: ‘giờ vàng’ nhuộm đỏ khăn vấn – mái gỗ; thuận ghé tu viện cổ và tắm lá thuốc."),
            paragraph("• Mùa lúa (tuỳ năm, thường 9–10): bậc thang óng vàng rất ‘ăn ảnh’. Mùa lạnh cần áo gió/áo mưa mỏng vì sương ẩm."),

            heading("Trải nghiệm văn hoá & nghề"),
            paragraph("• Thổ cẩm Dao đỏ: xem nhuộm chàm, vẽ sáp ong, thêu tay; thử vài mũi thêu cơ bản dưới hướng dẫn của các bà, các chị."),
            paragraph("• Rèn bạc/H’Mông: quan sát chạm khắc họa tiết; hỏi trước khi chạm vào đồ nghề."),
            paragraph("• Ủng hộ bền vững: hỏi giá rõ ràng, mua món nhỏ ‘thật sự dùng được’ thay vì trả giá quá sâu."),

            heading("Tắm lá Dao đỏ"),
            paragraph("Bài thuốc gồm nhiều loại lá rừng phơi khô, nấu thành nước ấm. Chọn cơ sở uy tín, yêu cầu nhiệt độ vừa phải. Người huyết áp thấp/người mới thử lần đầu nên ngâm 10–15 phút, nghỉ giữa chừng nếu chóng mặt; uống nước ấm trước và sau khi tắm."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn phủ rêu", false, 0)),

            heading("Tu viện cổ Tả Phìn"),
            paragraph("Di tích bằng đá gạch rêu phong giữa đồi, khung cửa vòm và bức tường vỡ tạo texture cổ điển. Nền gạch có chỗ bong tróc—đi nhẹ, không leo lên đỉnh tường. Giữ khoảng cách an toàn khi chụp ảnh vào ngày ẩm trơn."),

            heading("Ứng xử & chụp ảnh"),
            paragraph("• Xin phép trước khi chụp chân dung, đặc biệt là người lớn tuổi và trẻ nhỏ; hạn chế đưa tiền trực tiếp cho trẻ em."),
            paragraph("• Không đứng lên bờ ruộng bậc thang; luôn chừa lối cho người bản địa đi lại."),
            paragraph("• Trang phục thuê: thử vừa cỡ, giữ gìn, trả đúng giờ; không mặc đè lên đồ đang trưng bày nếu chưa được phép."),

            heading("Góc chụp gợi ý & mẹo ảnh"),
            paragraph("• Con đường làng: đứng chếch 30° để thấy nhịp uốn theo ruộng; đặt người ở 1/3 khung để tạo chiều sâu."),
            paragraph("• Thổ cẩm: lấy tay thợ làm tiền cảnh, hậu cảnh là dải khăn/áo treo; khép khẩu f/5.6–f/8 giữ nét họa tiết."),
            paragraph("• Tu viện cổ: canh nắng xiên chiều để nổi vân rêu; tránh leo cao—dùng góc thấp để khối tường ‘vươn’ hơn."),
            paragraph("• Chân dung khăn đỏ: nền gỗ tối/tường đá xám giúp tôn màu khăn; đo sáng vào da để giữ sắc đỏ trung thực."),

            heading("Ăn uống & đặc sản"),
            paragraph("Thử thắng cố (mùi vị đậm—nên dùng phần nhỏ), thịt lợn cắp nách nướng, cơm lam, rượu thóc ấm người. Mang thêm chai nước 300–500ml cho quãng đi bộ; giữ rác trong túi riêng mang ra khỏi bản."),

            heading("An toàn & lưu ý nhỏ"),
            paragraph("Đường làng có đoạn dốc – ẩm: giày đế bám, bước ngắn; trời mưa tránh dép trơn. Tôn trọng không gian thờ tự/nhà riêng; không bật loa lớn. Nếu đi xe máy, gửi ở điểm cho phép và khóa kỹ."),

            heading("Lịch trình gợi ý 2–3 giờ"),
            paragraph("• 0’–20’: vào bản, chụp cảnh đường làng – ruộng bậc thang."),
            paragraph("• 20’–70’: xem dệt, thêu; trải nghiệm vài mũi thêu, mua món nhỏ ủng hộ."),
            paragraph("• 70’–110’: qua tu viện cổ chụp ảnh rêu phong – cửa vòm."),
            paragraph("• 110’–150’: tắm lá Dao đỏ, nghỉ ấm người, trở về thị trấn."),

            gallery(List.of(
                img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2015_01_16/taphin.jpg", "Người Dao đỏ Tả Phìn", false, 0),
                img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc-8.jpg", "Thổ cẩm thủ công", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn", false, 2)
            )),

            quote("“Sắc đỏ khăn vấn, hương lá thuốc – ký ức khó quên của một bản làng.”"),
            divider(),
            mapBlock(new double[]{103.9000, 22.3790})
        ))
        .build();
  }

  /** 5) Đèo Ô Quy Hồ – Cổng Trời */
  private static PlaceDoc poiDeoOQuyHo() {
    String slug = "deo-o-quy-ho-cong-troi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Đèo Ô Quy Hồ – Cổng Trời")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Một trong tứ đại đỉnh đèo, cảnh mây trời hùng vĩ.")
        .description("Cung đường uốn lượn qua dãy Hoàng Liên; săn mây, ngắm núi Fansipan từ các điểm dừng chân an toàn.")
        .addressLine("QL4D, Ô Quy Hồ, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.7780, 22.3650})
        .images(withCover(
            img("https://static.vinwonders.com/production/deo-o-quy-ho-topbanner.jpg", "Đèo Ô Quy Hồ nhìn từ trên cao", true, 0),
            img("https://static.vinwonders.com/production/ten-goi-deo-o-quy-ho.jpg", "Làn mây qua đèo", false, 1),
            img("https://static.vinwonders.com/production/phuot-deo-o-quy-ho.jpg", "Khúc cua tay áo", false, 2),
            img("https://static.vinwonders.com/production/khu-du-lich-cong-troi-o-quy-ho.jpg", "Điểm dừng ngắm cảnh", false, 3),
            img("https://static.vinwonders.com/production/doi-che-o-quy-ho.jpg", "Núi rừng trùng điệp", false, 4),
            img("https://static.vinwonders.com/production/checkin-cay-co-don-deo-o-quy-ho.jpg", "Hoàng hôn trên đèo", false, 5)
        ))
       .content(List.of(
            heading("Ô Quy Hồ – cung đường trong mây"),
            quote("""
                    Đường quấn lưng núi biếc,
                    mây rót xuống vai đèo;
                    gió khía thành vệt trắng,
                    chân trời gần hơn nhiều.
                """),
            paragraph("Ô Quy Hồ là một trong ‘tứ đại đỉnh đèo’ miền Bắc, vắt qua sườn Hoàng Liên Sơn nối Lào Cai với Lai Châu. Cung đường uốn lượn liên tiếp, mở ra lớp lớp sườn núi, biển mây và – những ngày quang – cả bóng Fansipan mờ xa. Thời tiết đổi rất nhanh: một khúc nắng, một khúc sương—hãy chủ động canh giờ và điểm dừng an toàn."),

            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• Sáng muộn 09:00–11:00: mây bồng bềnh, gió vừa; ảnh rõ từng lớp núi ‘xếp tầng’."),
            paragraph("• Xế chiều 15:30–17:30: ‘giờ vàng’ nhuộm ấm triền núi; lý tưởng săn hoàng hôn (chủ động giờ quay về trước khi sương xuống)."),
            paragraph("• Mùa khô (thường 10–4): tầm nhìn xa, ít mưa; mùa mưa (5–9): mây – sương dày, cảnh huyền ảo nhưng đường dễ trơn—chạy cẩn thận."),

            heading("Lộ trình gợi ý từ Sa Pa"),
            paragraph("• Thị trấn Sa Pa → QL4D hướng Ô Quy Hồ → dừng ở khu ‘Cổng Trời’ (điểm ngắm có bãi đỗ) → tiếp tục men các điểm vọng cảnh có lan can/hộ lan. Tránh dừng ngay khúc cua tay áo, chỉ đỗ nơi có khoảng trống và tầm nhìn thoáng."),
            imageBlock(img("https://static.vinwonders.com/production/khu-du-lich-cong-troi-o-quy-ho.jpg", "Cổng Trời – điểm dừng ngắm cảnh có lan can", false, 0)),

            heading("Các điểm dừng tham khảo (an toàn trước hết)"),
            paragraph("• Cổng Trời Ô Quy Hồ: view ‘đường viền’ sát mây, nhìn về dải núi Hoàng Liên."),
            paragraph("• Một số bãi nhìn về Lai Châu: ít xe hơn, thấy thung lũng xa, hợp chụp pano."),
            paragraph("• Khúc cua chữ S (chỉ dừng khi có ‘bờm’ rộng và tín hiệu an toàn): khung đường ôm sườn núi rất ‘ăn ảnh’."),

            heading("An toàn khi đổ đèo"),
            paragraph("• Trước khi đi: kiểm tra phanh, lốp, đèn; mặc áo phản quang/áo tối màu có viền phản quang."),
            paragraph("• Khi xuống dốc: về số thấp, phanh động cơ; phanh nhịp ngắn—không rà phanh liên tục để tránh nóng má."),
            paragraph("• Trời mưa/sương: bật đèn chiếu gần + đèn sương mù (nếu có); giữ khoảng cách, tốc độ ổn định; tránh quay đầu gấp."),
            paragraph("• Dừng – đỗ: luôn chọn điểm có bờ chắn/lan can, đỗ gọn trong lề; quan sát xe hai chiều trước khi bước qua hộ lan."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Toàn cảnh đèo: đặt đường hộ lan/triền cỏ làm tiền cảnh tạo chiều sâu; đường chân trời ở 1/3 khung."),
            paragraph("• Biển mây: đo sáng vào bầu trời, bù phơi sáng −0.3 đến −1 EV để giữ texture mây; chờ gió ‘xé mây’ lộ lớp núi."),
            paragraph("• Hoàng hôn: đứng chếch 30–45° so với mặt trời để bắt tia xiên; đợi khoảnh khắc mây lướt qua rìa núi."),
            paragraph("• Pano dãy Hoàng Liên: chụp liên tiếp 5–7 khung chồng 30%—ghép sau sẽ có ảnh độ phân giải cao."),

            heading("Chuẩn bị cá nhân"),
            paragraph("Áo gió/áo mưa mỏng, găng tay, khăn ống (gió lạnh đổi nhanh). Nước ấm/đồ uống nóng nhỏ; khi lạnh tay, nghỉ 3–5 phút ở điểm trú gió rồi hẵng chạy tiếp. Giày đế bám nếu leo lên mỏm đá ‘check-in’—chỉ vào vùng đã rào an toàn."),

            heading("Lịch trình gợi ý 90–150 phút"),
            paragraph("• 0’–25’: rời Sa Pa theo QL4D, quan sát đường – làm quen nhịp cua."),
            paragraph("• 25’–60’: dừng Cổng Trời → chụp toàn cảnh, pano lớp núi."),
            paragraph("• 60’–90’: di chuyển thêm 1–2 điểm vọng cảnh có bãi đỗ—chụp ‘đường ôm núi’."),
            paragraph("• 90’–150’: nếu săn hoàng hôn, canh ánh vàng → quay đầu sớm khi sương kéo; chạy chậm, đèn đủ."),

            heading("Ứng xử & môi trường"),
            paragraph("Không bày biện rác/đốt lửa ở điểm nhìn; không vượt lan can ‘lấy góc mạo hiểm’. Giữ âm lượng nhỏ, nhường lối cho xe đường dài; tôn trọng người bán hàng địa phương—mua nước/đồ ăn nhẹ coi như lời cảm ơn."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/deo-o-quy-ho-topbanner.jpg", "Đèo nhìn từ trên cao", false, 0),
                img("https://static.vinwonders.com/production/ten-goi-deo-o-quy-ho.jpg", "Mây vờn qua đỉnh đèo", false, 1),
                img("https://static.vinwonders.com/production/checkin-cay-co-don-deo-o-quy-ho.jpg", "Khoảnh khắc hoàng hôn", false, 2)
            )),

            quote("“Ô Quy Hồ – nơi con đường trở thành một đường viền của bầu trời.”"),
            divider(),
            mapBlock(new double[]{103.7780, 22.3650})
        ))
        .build();
  }

  /** 6) Thác Bạc */
  private static PlaceDoc poiThacBac() {
    String slug = "thac-bac";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Thác Bạc")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Thác nước lớn, tung bọt trắng xoá bên QL4D, đường đi thuận tiện.")
        .description("Bậc thang gỗ – cầu nhỏ dẫn lên các tầng thác; kết hợp tham quan hợp lý với Đèo Ô Quy Hồ và Thác Tình Yêu.")
        .addressLine("QL4D, San Sả Hồ, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.7700, 22.3640})
        .images(withCover(
            img("https://static.vinwonders.com/production/thac-bac-sa-pa-banner.jpg", "Toàn cảnh Thác Bạc", true, 0),
            img("https://static.vinwonders.com/production/du-lich-thac-bac-sa-pa.jpg", "Lối bậc lên thác", false, 1),
            img("https://static.vinwonders.com/production/check-in-thac-bac.jpg", "Làn nước trắng xoá", false, 2),
            img("https://static.vinwonders.com/production/thoi-diem-ly-tuong-den-thac-bac-o-sa-pa.jpg", "Cầu gỗ qua suối", false, 3),
            img("https://static.vinwonders.com/production/cung-duong-dan-den-thac-bac.jpg", "Rừng xanh quanh thác", false, 4),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/04/Thac-bac-sapa-4.jpg", "Góc chụp gần chân thác", false, 5)
        ))
        .content(List.of(
            heading("Thác Bạc – mát lạnh giữa núi rừng"),
            quote("""
                    Mây vờn theo vệt trắng,
                    nước rót tiếng rì rầm;
                    bậc gỗ ôm triền đá,
                    rừng thở nhịp dịu êm.
                """),
            paragraph("Nằm ngay bên QL4D cách thị trấn Sa Pa không xa, Thác Bạc đổ bậc cao, tung bọt trắng xoá quanh năm. Lối tham quan là hệ bậc gỗ – cầu nhỏ bám sườn, đưa bạn lên các tầng nhìn cận thác mà vẫn an toàn. Hơi nước dày, không khí mát rượi kể cả vào trưa hè—rất hợp dừng chân kết hợp với Đèo Ô Quy Hồ và Thác Tình Yêu cùng cung đường."),

            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• 08:00–10:00: ánh sáng xiên dịu, ít đông, dễ chụp texture dòng nước."),
            paragraph("• 15:30–17:00: ‘giờ vàng’ phủ ấm rừng thông; lưu ý quay về trước khi sương kéo xuống QL4D."),
            paragraph("• Mùa mưa (5–9): nước mạnh, cảnh hùng vĩ hơn nhưng lối đi có thể trơn—đi chậm, bám tay vịn."),

            heading("Tuyến tham quan gợi ý 60–90 phút"),
            paragraph("• 0’–10’: gửi xe, khởi động, chuẩn bị áo mưa mỏng/áo khoác vì hơi nước."),
            paragraph("• 10’–35’: theo bậc gỗ lên điểm nhìn tầng giữa—chụp toàn cảnh ‘vệt bạc’ rót xuống khe."),
            paragraph("• 35’–60’: men cầu gỗ sang bờ đối diện—chụp cận cảnh tia nước và bụi nước lấp lánh."),
            paragraph("• 60’–90’: quay vòng xuống lối dưới, dừng ở chân thác chụp close-up bọt nước; nghỉ nhẹ rồi rời điểm."),

            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Tan-mat-chiem-nguong-ve-dep-cua-Thac-Bac-Cay-vi-cam-cua-nui-rung-Sapa-05-1621358699.jpg", "Dòng thác cận cảnh", false, 0)),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Mềm dòng nước: đặt tốc độ 1/10–1/4s, tựa máy vào lan can/bờ đá, khép khẩu f/8–f/11."),
            paragraph("• Hạt nước ‘lấp lánh’: tăng tốc 1/250–1/500s khi nắng chiếu xiên để đóng băng bọt nước."),
            paragraph("• Lấy lớp: dùng thân cây, lan can gỗ làm tiền cảnh để tạo chiều sâu; đặt người ở 1/3 khung cho ảnh có tỷ lệ."),
            paragraph("• Chống mờ kính: lau ống kính định kỳ vì hơi nước; mang khăn microfiber nhỏ trong túi áo mưa."),

            heading("Kết hợp cung Ô Quy Hồ – Thác Tình Yêu"),
            paragraph("Thác Bạc nằm cùng trục QL4D với Cổng Trời Ô Quy Hồ và lối vào Thác Tình Yêu. Lịch gợi ý: sáng leo Thác Bạc → xế trưa ăn nhẹ → chiều sớm qua Thác Tình Yêu (đi bộ rừng mát) → săn hoàng hôn ở Cổng Trời; luôn trừ thời gian quay lại trước sương tối."),

            heading("An toàn & chuẩn bị"),
            paragraph("• Giày đế bám, áo mưa mỏng/áo khoác chống nước; tránh dép trơn vì bậc gỗ – đá ẩm."),
            paragraph("• Không leo qua lan can ‘lấy góc’; trẻ nhỏ luôn trong tầm tay; không thò tay ra sát mép đổ."),
            paragraph("• Trời mưa to: hạn chế lên tầng cao, ưu tiên đứng ở platform có che; khi sấm chớp, tạm xuống khu trú an toàn."),

            heading("Dịch vụ nhỏ & lưu ý môi trường"),
            paragraph("Có quầy nước/đồ nướng đơn giản gần cổng—giá tuỳ thời điểm. Mang theo chai nước nhỏ, giữ rác trong túi riêng và mang ra ngoài điểm tập kết. Giữ âm lượng nhỏ để tiếng thác và rừng ‘làm nhạc nền’."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/du-lich-thac-bac-sa-pa.jpg", "Lối bậc lên thác", false, 0),
                img("https://static.vinwonders.com/production/check-in-thac-bac.jpg", "Dòng nước trắng xoá", false, 1),
                img("https://static.vinwonders.com/production/thoi-diem-ly-tuong-den-thac-bac-o-sa-pa.jpg", "Cầu gỗ qua suối", false, 2)
            )),

            quote("“Tiếng nước rơi như một bản nhạc mát lành giữa rừng.”"),
            divider(),
            mapBlock(new double[]{103.7700, 22.3640})
        ))
        .build();
  }

  /** 7) Thác Tình Yêu */
  private static PlaceDoc poiThacTinhYeu() {
    String slug = "thac-tinh-yeu";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Thác Tình Yêu")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Con thác đẹp lãng mạn trong rừng quốc gia Hoàng Liên.")
        .description("Đi bộ rừng 15–20 phút đến thác; nước trong xanh, nhiều góc chụp; kết hợp Ghềnh Vàng – Suối Vàng gần đó.")
        .addressLine("Trạm Tôn, Hoàng Liên, Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.7880, 22.3450})
        .images(withCover(
            img("https://static.vinwonders.com/production/gioi-thieu-thac-tinh-yeu-o-sa-pa.jpg", "Thác Tình Yêu mềm mại", true, 0),
            img("https://static.vinwonders.com/production/ve-dep-thac-tinh-yeu.jpg", "Lối mòn vào thác", false, 1),
            img("https://static.vinwonders.com/production/gia-ve-tham-quan-thac.jpg", "Suối trong rừng", false, 2),
            img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Chân thác", false, 3),
            img("https://static.vinwonders.com/production/du-day-vuot-thac-o-sa-pa.jpg", "Mùa mưa nước mạnh", false, 4),
            img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Không gian xanh mát", false, 5)
        ))
        .content(List.of(
            heading("Thác Tình Yêu – lối nhỏ nên thơ"),
            quote("""
                    Rêu ôm bờ đá cũ,
                    suối hát khúc trong ngần;
                    bước chân qua tán lá,
                    gặp thác trắng mơ tần.
                """),
            paragraph("Nằm trong rừng quốc gia Hoàng Liên, Thác Tình Yêu là một dải lụa trắng mềm đổ giữa nền xanh rêu và gỗ ẩm. Lối mòn đất – đá phủ lá, đi 15–20 phút là tới chân thác. Không khí mát lạnh, tiếng suối rì rầm—rất hợp một buổi dạo rừng nhẹ, chụp ảnh và ngồi nghe rừng thở."),

            heading("Thời điểm & nhịp tham quan"),
            paragraph("• 08:00–10:00: nắng xiên xuyên tán, tia sáng loé trên bụi nước—ảnh trong và dịu."),
            paragraph("• 15:30–17:00: ‘giờ vàng’ nhuộm ấm rêu đá; chủ động quay về trước khi sương lạnh kéo tới."),
            paragraph("• Mùa mưa (5–9): nước mạnh, hùng vĩ nhưng lối đá trơn—đi chậm, bám tay vịn. Mùa khô (10–4): nước vừa, đi rừng dễ chịu, ảnh chi tiết rêu đẹp."),

            heading("Lối mòn vào thác (15–20 phút)"),
            paragraph("Từ cổng kiểm soát Trạm Tôn, đi theo biển chỉ dẫn. Đường đất – đá đan xen, có vài bậc gỗ/cầu nhỏ qua suối. Giữ nhịp bước ngắn, tránh giẫm lên rễ trồi; mưa ẩm thì ưu tiên giày bám tốt."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Check-in-Suoi-vang-thac-tinh-yeu-cuc-hot-o-sapa-8-1620310017.jpg", "Dòng thác trắng xoá giữa rêu đá", false, 0)),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• ‘Mềm nước’: đặt tốc 1/10–1/4s, tựa máy vào lan can/đá, khép khẩu f/8–f/11 để giữ nét toàn khung."),
            paragraph("• ‘Hạt nước bay’: tăng tốc 1/250–1/500s khi nắng xiên để đóng băng tia nước lấp lánh."),
            paragraph("• Bố cục tầng: đứng chếch 30–45° để thấy cả thác và suối tiền cảnh; thêm một người ở 1/3 khung để tạo tỷ lệ."),
            paragraph("• Chống mờ ống kính: mang khăn microfiber nhỏ; hơi nước đọng rất nhanh khi áp sát thác."),

            heading("Kết hợp Ghềnh Vàng – Suối Vàng"),
            paragraph("Sau khi ngắm thác, men suối về phía các ghềnh đá vàng rêu để chụp chi tiết dòng chảy, phản chiếu trời qua tán rừng. Nước nông ven bờ có thể ngâm chân ngắn—tránh ra giữa dòng và không đứng trên phiến đá rêu trơn."),

            heading("An toàn & chuẩn bị"),
            paragraph("• Giày đế bám, áo mưa mỏng/áo khoác nhẹ; balo gọn, túi khô cho điện thoại."),
            paragraph("• Không leo lên mỏm đá sát mép đổ; mưa lớn/sấm chớp thì tạm dừng, lùi về điểm trú an toàn."),
            paragraph("• Đi 2+ người; trẻ nhỏ luôn trong tầm tay. Mang theo 300–500ml nước và ít kẹo gừng."),

            heading("Ứng xử & môi trường"),
            paragraph("Không xả rác; đi trên lối mòn để tránh dẫm nát rễ cây. Không bật loa lớn—hãy để tiếng suối làm ‘nhạc nền’. Không viết/khắc lên đá, không bẻ rêu/nhặt sinh vật trong suối."),

            heading("Lịch trình gợi ý 60–90 phút"),
            paragraph("• 0’–20’: từ cổng Trạm Tôn đi bộ theo lối mòn, dừng 1–2 điểm cầu gỗ chụp suối."),
            paragraph("• 20’–50’: tới chân thác—chụp toàn cảnh, close-up tia nước; nghỉ nhẹ."),
            paragraph("• 50’–75’: men sang Ghềnh Vàng – Suối Vàng, chụp rêu – phản chiếu; quay lối cũ ra ngoài."),
            paragraph("• 75’–90’: về lại bãi xe/điểm tập kết, khởi hành tiếp cung QL4D (kết hợp Thác Bạc/Ô Quy Hồ nếu còn thời gian)."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/gioi-thieu-thac-tinh-yeu-o-sa-pa.jpg", "Thác Tình Yêu mềm mại", false, 0),
                img("https://static.vinwonders.com/production/ve-dep-thac-tinh-yeu.jpg", "Lối mòn trong rừng", false, 1),
                img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Ghềnh – Suối Vàng", false, 2)
            )),
            quote("“Đường vào thác là đường vào một câu chuyện đẹp.”"),
            divider(),
            mapBlock(new double[]{103.7880, 22.3450})
        ))
        .build();
  }

  /** 8) Nhà thờ Đá Sa Pa & Quảng trường trung tâm */
  private static PlaceDoc poiNhaThoDaSaPa() {
    String slug = "nha-tho-da-sa-pa";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Nhà thờ Đá Sa Pa & Quảng trường")
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription("Biểu tượng trung tâm thị trấn, kiến trúc đá tạc, chợ phiên cuối tuần.")
        .description("Check-in nhà thờ đá, quảng trường sôi động; tối cuối tuần có chợ phiên – biểu diễn văn nghệ dân tộc.")
        .addressLine("Trung tâm thị trấn Sa Pa, Lào Cai")
        .countryCode("VN").provinceName("Lào Cai")
        .location(new double[]{103.8430, 22.3350})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-01-1621312140.jpg", "Nhà thờ Đá Sa Pa", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-04-1621312140.jpg", "Quảng trường trung tâm", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-sapa-1753930409.jpg", "Tối cuối tuần nhộn nhịp", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-sapa-1-1753930408.jpg", "Không gian bên trong", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-09-1621312141.jpg", "Góc ảnh cổ kính", false, 4),
            img("https://static.vinwonders.com/production/gioi-thieu-nha-tho-da-sa-pa.jpg", "Không gian lễ hội", false, 5)
        ))
        .content(List.of(
            heading("Nhà thờ Đá – điểm hẹn của Sa Pa"),
            quote("""
                    Chuông ngân qua màn sương,
                    đá cổ kể chuyện phố;
                    quảng trường vào nhịp chợ,
                    tiếng khèn gọi đêm nâu.
                """),
            paragraph("Nằm ở trái tim thị trấn, Nhà thờ Đá Sa Pa với kiến trúc đá tạc cổ kính nhìn thẳng ra quảng trường trung tâm—nơi diễn ra các hoạt động văn hoá, chợ phiên cuối tuần và những cuộc hẹn ‘gặp nhau ở nhà thờ’. Vị trí thuận tiện, xung quanh là hàng quán, café và các tuyến đi bộ ngắn."),
            
            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• Sáng 07:00–09:00: ánh sáng xiên dịu, ít người—đẹp để chụp kiến trúc và sương mỏng."),
            paragraph("• Chiều 16:30–18:30: ‘giờ vàng’ nhuộm ấm mặt đá, sau đó quảng trường đông vui hơn."),
            paragraph("• Tối cuối tuần: chợ phiên, biểu diễn văn nghệ dân tộc—đông đúc, sôi động; nhớ mang áo ấm vì nhiệt độ hạ nhanh."),

            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-sapa-3-1753930767.jpg", "Góc check-in trước tiền sảnh đá", false, 0)),

            heading("Kiến trúc & không gian"),
            paragraph("Khối nhà thờ đá tạc với vòm cửa, tháp chuông mảnh và các ô cửa kính màu tạo nên sắc thái cổ điển giữa sương núi. Bề mặt đá giàu ‘texture’—rất ‘ăn ảnh’ khi nắng xiên sáng/chiều. Bên trong là không gian tĩnh; trang phục nghiêm túc, nói nhỏ nếu ghé thăm."),

            heading("Quảng trường trung tâm"),
            paragraph("Quảng trường là ‘phòng khách’ của thị trấn: điểm hẹn tour, nơi diễn ra hội chợ phiên, lễ hội theo mùa và các tiết mục khèn, múa xoè vào dịp cuối tuần. Vòng quanh quảng trường là dải café/đồ nướng—tiện nghỉ chân ngắm dòng người và đồi núi phía xa."),

            heading("Trải nghiệm gợi ý"),
            paragraph("• Đi bộ vòng quảng trường, dừng nghe nhạc cụ dân tộc—ủng hộ nghệ sĩ bằng cách mua đồ lưu niệm nhỏ."),
            paragraph("• Ghé bên trong nhà thờ vào giờ mở cửa (khi cho phép), giữ yên tĩnh; tránh chụp flash."),
            paragraph("• Tối cuối tuần: hòa vào chợ phiên, thử mặc áo choàng/khăn choàng thổ cẩm để chụp ảnh kỷ niệm."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Mặt tiền nhà thờ: chụp chếch 30° để thấy tháp chuông và khối vòm; đặt đường cong quảng trường làm tiền cảnh."),
            paragraph("• Ban đêm: đo sáng vào mặt đá, hạ EV 0.3–0.7 để giữ chi tiết; canh đèn quảng trường ‘vẽ’ viền khối kiến trúc."),
            paragraph("• Ảnh người: nền tường đá xám giúp tôn màu trang phục; tránh đứng sát đèn nền gây ‘cháy’ viền."),
            
            heading("Ứng xử & lưu ý"),
            paragraph("Tôn trọng không gian tôn giáo: không nói to, không leo lên bậc/lan can để ‘lấy góc’. Ở quảng trường, tránh chắn lối di chuyển; cuối tuần đông—giữ tư trang gọn và không xả rác. Trời lạnh mang áo ấm/áo mưa mỏng vì sương xuống nhanh."),

            heading("Ăn uống quanh khu vực"),
            paragraph("Vành đai quảng trường có đồ nướng Sa Pa (thịt xiên, cơm lam, trứng, ngô nếp), café nhìn thẳng nhà thờ—hợp dừng chân 30–45 phút. Hỏi giá trước, chọn quán đông người địa phương để có trải nghiệm ổn định."),

            heading("Lịch trình gợi ý 45–90 phút"),
            paragraph("• 0’–15’: chụp mặt tiền nhà thờ lúc ‘giờ vàng’/sáng sớm."),
            paragraph("• 15’–40’: đi vòng quảng trường—ảnh phố núi, dãy núi nền xa."),
            paragraph("• 40’–70’: café/đồ nướng ngắm phố; nếu cuối tuần, xem tiết mục dân tộc."),
            paragraph("• 70’–90’: chụp đêm (tuỳ thời gian), rồi tản bộ về khách sạn."),

            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-01-1621312140.jpg", "Nhà thờ Đá Sa Pa", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-04-1621312140.jpg", "Quảng trường trung tâm", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-sapa-1753930409.jpg", "Không khí tối cuối tuần", false, 2)
            )),

            quote("“Hẹn nhau ở nhà thờ đá – câu mở đầu cho nhiều cuộc dạo chơi Sa Pa.”"),
            divider(),
            mapBlock(new double[]{103.8430, 22.3350})
        ))
        .build();
  }
}