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
public class SeedNhaTrangPois {

  @Bean
  CommandLineRunner seedNhaTrangPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedNhaTrangPois] start");

      List<PlaceDoc> pois = List.of(
          poiThapTramHuong(),
          poiChuaLongSon(),
          poiBaiDaiCamRanh(),
          poiDocLet(),
          poiDiepSon(),
          poiBaHoWaterfalls(),
          poiHonKhoiSaltField(),
          poiNinhVanBay()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedNhaTrangPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedNhaTrangPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedNhaTrangPois] done, inserted = " + inserted);
    };
  }

  // ===================== 8 POI FOR NHA TRANG =====================

  /** 1) Tháp Trầm Hương (Quảng trường 2/4) */
  private static PlaceDoc poiThapTramHuong() {
    String slug = "thap-tram-huong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Tháp Trầm Hương (Quảng trường 2/4)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Biểu tượng ven biển Nha Trang, điểm check-in trên đường Trần Phú.")
        .description("Công trình nghệ thuật ngay quảng trường 2/4, nhìn thẳng ra biển Trần Phú. Buổi tối tháp lên đèn, xung quanh nhiều hoạt động đường phố.")
        .addressLine("Quảng trường 2/4, Trần Phú, Nha Trang, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.1962, 12.2365})
        .images(withCover(
            img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp Trầm Hương nhìn ra biển", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/thap-tram-huong-truoc-khi-duoc-thay-ao-moi-1687939423.jpeg", "Góc gần tháp", false, 1),
            img("https://static.vinwonders.com/2022/06/quang-truong-2-4-nha-trang-2-1.jpg", "Quảng trường 2/4", false, 2),
            img("https://statics.vinpearl.com/thap-tram-huong-nha-trang_1753687142.jpg", "Tháp lên đèn", false, 3),
            img("https://static.vinwonders.com/2022/05/duong-tran-phu-nha-trang-thumb.jpg", "View biển Trần Phú", false, 4),
            img("https://52hz.vn/wp-content/uploads/2023/02/35-thap-tram-huong-ve-dem.jpg", "Hoàng hôn", false, 5)
        ))
        .content(List.of(
            heading("Tháp Trầm Hương – biểu tượng biển Nha Trang"),
            quote("""
                    Gió biển hong màu tháp,
                    sóng chải tóc bờ cát;
                    bước chân qua quảng trường,
                    hoàng hôn rót từng hát.
                """),
            paragraph("Nằm giữa quảng trường 2/4 trên trục Trần Phú, Tháp Trầm Hương là điểm hẹn dễ tìm nhất khi dạo biển Nha Trang. Ban ngày, tháp nổi bật trên nền trời xanh; về tối, hệ đèn lên màu khiến toàn khối kiến trúc như một búp trầm phát sáng giữa quảng trường đông vui."),
            paragraph("Tên gọi ‘Trầm Hương’ gợi nhắc đặc sản trầm – hương xứ Khánh Hòa. Hình khối tháp gồm nhiều lớp cánh uốn, tạo cảm giác như nụ hương đang hé nở hướng ra biển. Không gian mở 360° xung quanh giúp bạn dễ bố trí khung hình, tổ chức hoạt động sự kiện hoặc đơn giản là ngồi nghỉ chân nghe sóng."),

            heading("Thời điểm & cách trải nghiệm"),
            paragraph("Khung giờ lý tưởng: 16:30–18:30. Lên ảnh đẹp cả khi trời xanh lẫn ‘giờ xanh’ trước khi tối hẳn. Nếu thích không khí sôi động, ở lại đến 19:00–20:00 khi quảng trường đông người và đèn xe kéo vệt trên đường Trần Phú."),
            paragraph("Di chuyển: gửi xe máy tại bãi gần quảng trường, đi bộ vòng quanh tháp một vòng để tìm góc ưng ý; sau đó băng qua lối sang vỉa hè biển để hít gió. Mang theo nước uống nhẹ, khăn giấy, và một chiếc áo mỏng vì gió biển có thể lạnh sau hoàng hôn."),

            imageBlock(img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp Trầm Hương nhìn ra biển", false, 0)),

            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Chính diện từ mép biển: lấy trọn khối tháp và đường chân trời – phù hợp ảnh kỷ niệm ‘điểm danh’."),
            paragraph("Góc chéo 30–45° từ phía bắc quảng trường: nhấn các lớp ‘cánh’ của tháp, ảnh có chiều sâu hơn."),
            paragraph("Ảnh đêm: tắt flash, áp ống sát lan can để giảm phản chiếu; nếu có tripod mini, đặt ở mép bồn cây, chụp kéo sáng 0.5–1s để bắt vệt xe trên Trần Phú."),
            paragraph("Selfie nhóm: đứng lùi 6–8m, máy hơi hạ thấp để tháp ‘cao’ hơn trong khung; chia nhóm theo tầng cao–thấp để không che mặt nhau."),

            heading("Kết hợp dạo biển Trần Phú"),
            paragraph("Từ tháp, đi bộ dọc vỉa hè về hướng nam (bãi tắm trung tâm) để ngắm phố lên đèn, hoặc rẽ về phía chợ đêm nếu đang mở. Nếu có thời gian, đặt lịch ghé Tháp Bà Ponagar/tắm bùn vào buổi sáng hôm sau để cân bằng nhịp tham quan – dạo biển."),

            heading("Ăn uống quanh quảng trường"),
            paragraph("Đồ uống nhanh: nước dừa, nước mía, sữa chua dầm trái cây từ các kiosque dọc vỉa hè."),
            paragraph("Ăn vặt: bánh tráng nướng, xiên nướng; gọi phần nhỏ để vừa ăn vừa dạo. Lưu ý xả rác đúng nơi quy định vì khu vực nhiều gió."),
            paragraph("Quán café/nhìn biển: chọn quán có tầng lầu gần mặt tiền Trần Phú để ngắm thẳng ra tháp và bờ biển."),

            heading("Bối cảnh & kiến trúc"),
            paragraph("Công trình có tính biểu trưng cao, phối hợp mảng khối uốn cong gợi hình nụ hương. Màu sắc ‘hơi hồng cam’ nổi rõ trên nền cát và nước biển xanh, dễ nhận diện từ xa. Quảng trường xung quanh là không gian mở phục vụ sự kiện cộng đồng, lễ hội biển theo mùa."),

            heading("Thời tiết & mùa đẹp"),
            paragraph("Mùa nắng (thường từ 3–9): trời trong, biển xanh, hoàng hôn rõ màu. Mang kem chống nắng và nón rộng vành."),
            paragraph("Mùa mưa: tháp vẫn chụp được ở tông trầm – phản chiếu mặt nền ướt khá thú vị. Ưu tiên trang phục tối giản, giày chống trượt."),

            heading("Lịch trình gợi ý 60–90 phút"),
            paragraph("• 0’–10’: gửi xe, đi bộ tiếp cận từ mép biển để có khung toàn cảnh tháp + đường chân trời."),
            paragraph("• 10’–30’: di chuyển sang góc chéo 30–45° để chụp ‘cánh’ tháp, thử khung dọc/khung ngang."),
            paragraph("• 30’–60’: qua vỉa hè biển, nghỉ chân, chụp hoàng hôn và thời khắc bật đèn."),
            paragraph("• 60’–90’: mua đồ uống nhẹ/kẹo kéo, dạo tiếp dọc Trần Phú hoặc sang chợ đêm (nếu mở)."),

            heading("Ứng xử & lưu ý"),
            paragraph("Không leo trèo lên bồn hoa/lan can; giữ lối đi thông thoáng để không chắn người khác chụp ảnh."),
            paragraph("Qua đường tại vị trí có vạch, chú ý tín hiệu vì lưu lượng xe lớn vào buổi tối."),
            paragraph("Buổi tối đông người – gọn đồ cá nhân, tránh đặt túi xách xuống nền khi mải chụp ảnh."),

            heading("Tiện ích & tiếp cận"),
            paragraph("Khu vực có ghế nghỉ rải rác, vỉa hè tương đối phẳng – hỗ trợ xe nôi/xe lăn ở nhiều đoạn (một số mép vỉa có dốc thoải). Nhà vệ sinh công cộng gần quảng trường có thể thay đổi theo thời điểm – nên hỏi bảo vệ/kiosque lân cận."),

            heading("Gợi ý mở rộng bán kính 2km"),
            paragraph("• Bãi biển trung tâm Nha Trang: tắm biển sáng sớm hoặc chạy bộ dọc vỉa hè."),
            paragraph("• Chợ đêm Nha Trang (tuỳ ngày mở): ăn vặt, mua quà nhỏ."),
            paragraph("• Cầu Trần Phú: khung nhìn sông–biển khi lên đèn, phù hợp ảnh cityscape."),

            gallery(List.of(
                img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp nhìn ra biển", false, 0),
                img("https://statics.vinpearl.com/thap-tram-huong-nha-trang_1753687142.jpg", "Tháp lên đèn", false, 1),
                img("https://static.vinwonders.com/2022/05/duong-tran-phu-nha-trang-thumb.jpg", "Đường Trần Phú ven biển", false, 2)
            )),

            quote("“Đi Nha Trang, cứ ra quảng trường – tháp sẽ chỉ bạn hướng về biển.”"),
            divider(),
            mapBlock(new double[]{109.1962, 12.2365})
        ))
        .build();
  }

  /** 2) Chùa Long Sơn (Tượng Phật Trắng) */
  private static PlaceDoc poiChuaLongSon() {
    String slug = "chua-long-son";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Chùa Long Sơn (Tượng Phật Trắng)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Ngôi chùa nổi tiếng với tượng Phật Trắng trên đồi, nhìn ra thành phố.")
        .description("Leo bậc thang lên khu tượng Phật Trắng, phóng tầm mắt toàn cảnh Nha Trang. Không gian thanh tịnh, thích hợp buổi sáng mát.")
        .addressLine("20 Đường 23/10, Phương Sơn, Nha Trang")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.1787, 12.2486})
        .images(withCover(
            img("https://cdn2.tuoitre.vn/thumb_w/1200/471584752817336320/2024/11/30/z6082977223128f21e0a8b82824cf52ecdf04be26c86ac-173293337597782060114.jpg", "Tượng Phật Trắng – Long Sơn", true, 0),
            img("https://static.vinwonders.com/2022/04/chua-long-son-3.jpg", "Cổng tam quan", false, 1),
            img("https://static.vinwonders.com/2022/04/chua-long-son-10.jpg", "Tượng Phật Tổ niết bàn", false, 2),
            img("https://static.vinwonders.com/2022/04/chua-long-son-8.jpg", "Không gian bên trong chùa", false, 3),
            img("https://static.vinwonders.com/2022/04/chua-long-son-5.jpg", "Không gian sân chùa", false, 4),
            img("https://static.vinwonders.com/2022/04/chua-long-son-11.jpg", "Tượng Phật cận cảnh", false, 5)
        ))
        .content(List.of(
            heading("Long Sơn – điểm tĩnh giữa lòng phố biển"),
            quote("""
                    Chuông ngân qua tán lá,
                    gió trắng rửa ưu phiền;
                    bậc đá nâng từng bước,
                    lòng nhẹ tựa mây miền.
                """),
            paragraph("Tựa lưng đồi Trại Thủy, chùa Long Sơn nổi tiếng với tượng Phật Trắng (Phật Thích Ca Mâu Ni ngồi thiền) uy nghi hướng về thành phố. Không gian rợp bóng cây, khói trầm phảng phất; sáng sớm hoặc sau 16:30 là lúc mát, ít đông, hợp tản bộ và chiêm bái."),

            heading("Lịch sử & kiến trúc khái quát"),
            paragraph("Chùa gắn với dòng Lâm Tế, nhiều lần trùng tu sau biến cố thiên tai – hỏa hoạn. Bố cục trải theo sườn đồi: cổng tam quan, sân – chánh điện, các cấp bậc thang dẫn lên khu tượng Phật và đài chuông. Màu ngói sẫm và các hoạ tiết rồng, mây thanh nhã tạo cảm giác cổ kính, khoáng đạt."),
            paragraph("Tượng Phật Trắng đặt trên bệ sen lớn, xung quanh có phù điêu về thập thiện – nhắc nhở du khách giữ tâm tĩnh, hành xử ôn hòa khi tham quan."),

            heading("Đường lên tượng Phật Trắng"),
            paragraph("Từ cổng tam quan, đi theo lối bậc đá. Dọc đường có các điểm dừng: tháp chuông, tượng Phật nhập Niết Bàn, bia khắc kinh. Đi chậm, bám tay vịn ở đoạn dốc; nhường lối tại chỗ hẹp. Lên tới khu tượng, tầm nhìn mở: phố xá, sông Cái, xa hơn là đường bờ biển cong ôm thành phố."),
            imageBlock(img("https://static.vinwonders.com/2022/04/chua-long-son-14.jpg", "Tháp chuông Đại Hồng Chung", false, 0)),

            heading("Không gian chánh điện & ứng xử"),
            paragraph("Trang phục kín đáo (che vai, gối); hạ giọng nói, tắt chuông điện thoại. Tránh chụp ảnh khi có nghi lễ; nếu cần chụp, tắt flash và đứng gọn một bên. Không đặt chân lên bệ, không tự ý gõ chuông, đánh mõ. Quyên góp nên bỏ vào thùng công đức, không đưa trực tiếp cho người hành lễ."),

            heading("Thời điểm tham quan gợi ý"),
            paragraph("• 06:00–08:00: nắng xiên nhẹ, không khí trong, thuận ảnh kiến trúc + cây xanh."),
            paragraph("• 16:30–18:00: bóng râm nhiều, lên khu tượng nhìn ‘giờ xanh’ trước hoàng hôn; thành phố bắt đầu lên đèn rất đẹp."),
            paragraph("• Ngày rằm/lễ lớn: có thể đông, bãi xe kín – cân nhắc đi sớm hơn 1–2 giờ."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Chân tượng: đứng chéo 30–45° để thấy rõ nếp áo và bệ sen; đưa đường chân trời thấp để tượng ‘vươn cao’."),
            paragraph("• Trên bậc thang: chụp ngược lên khung lá – bậc – tượng; mở khẩu vừa phải (f/4–5.6) để tách chủ thể."),
            paragraph("• Toàn cảnh: áp ống sát tay vịn giảm phản xạ; giờ ‘xanh’ cho bầu trời mịn, nếu có tripod mini, chụp 1–2s bắt ánh đèn phố."),
            paragraph("• Chân dung tĩnh: tận dụng mảng tường trắng – xám của chánh điện làm nền đơn sắc, tránh backlight gắt."),

            heading("Trải nghiệm tĩnh tâm"),
            paragraph("Thử ngồi vài phút ở bậc cao, hít sâu – thở chậm, lắng nghe tiếng chuông vọng qua tán lá. Nếu có duyên, dự một thời tụng ngắn (đứng phía sau, giữ yên lặng). Đọc nhanh nội dung các bia khắc dọc lối đi như một ‘đường thiền’ nho nhỏ."),

            heading("An toàn & lưu ý"),
            paragraph("Mang giày đế bám (bậc đá có thể trơn khi mưa). Mang nước nhỏ, nón/áo mỏng; không xả rác, không khắc vẽ lên đá. Tôn trọng không gian tu tập: không nói to, không đùa giỡn, không bay flycam khi chưa xin phép."),

            heading("Lịch trình gợi ý 60–90 phút"),
            paragraph("• 0’–10’: gửi xe, vào cổng, dạo sân – chụp tam quan."),
            paragraph("• 10’–30’: thăm chánh điện, nghỉ ngắn dưới bóng cây."),
            paragraph("• 30’–60’: leo bậc thang, dừng ở tháp chuông – tượng Niết Bàn."),
            paragraph("• 60’–90’: lên khu tượng Phật Trắng, chụp toàn cảnh thành phố, ngồi tĩnh tâm 5–10 phút."),

            heading("Kết hợp điểm gần kề (bán kính 2–3km)"),
            paragraph("• Tháp Bà Ponagar: kiến trúc Chăm đặc trưng – đi sáng sớm để mát."),
            paragraph("• Chợ Xóm Mới / chợ Đầm (tùy thời điểm): ẩm thực, trái cây địa phương."),
            paragraph("• Cầu Hà Ra – sông Cái: khung thành phố nhìn dọc sông lúc chiều muộn."),

            heading("Thông tin thực tế"),
            paragraph("Bãi gửi xe thường ở chân đồi gần cổng; có bảng chỉ dẫn lối lên. Nhà vệ sinh nằm phía sân dưới và một số điểm trên đường bậc (có thể thay đổi theo thời điểm). Hỏi thêm bảo vệ/chư tăng nếu cần hỗ trợ."),

            gallery(List.of(
                img("https://cdn2.tuoitre.vn/thumb_w/1200/471584752817336320/2024/11/30/z6082977223128f21e0a8b82824cf52ecdf04be26c86ac-173293337597782060114.jpg", "Tượng Phật Trắng trên đồi", false, 0),
                img("https://static.vinwonders.com/2022/04/chua-long-son-3.jpg", "Cổng tam quan", false, 1),
                img("https://static.vinwonders.com/2022/04/chua-long-son-8.jpg", "Không gian chánh điện", false, 2)
            )),
            quote("“Lên vài bậc thang, xuống một nhịp lòng – Long Sơn cho ta bình yên.”"),
            divider(),
            mapBlock(new double[]{109.1787, 12.2486})
        ))
        .build();
  }

  /** 3) Bãi Dài (Cam Ranh) */
  private static PlaceDoc poiBaiDaiCamRanh() {
    String slug = "bai-dai-cam-ranh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Bãi Dài (Cam Ranh)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Dải cát mịn dài bất tận, nước trong xanh – điểm tắm biển đẹp gần Nha Trang.")
        .description("Thuộc vịnh Cam Ranh, cách trung tâm ~25–30km. Biển thoải, sóng dịu; nhiều resort mới, quán ven biển chill.")
        .addressLine("Nguyễn Tất Thành, Cam Lâm – Cam Ranh, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2150, 11.9980})
        .images(withCover(
            img("https://statics.vinpearl.com/bai-dai-cam-lam-04_1664546932.jpg", "Bãi Dài xanh ngắt", true, 0),
            img("https://statics.vinpearl.com/bai-dai-cam-lam-06_1664546916.jpg", "Đường ven biển", false, 1),
            img("https://statics.vinpearl.com/bai-dai-cam-lam-05_1664546924.jpg", "Khu vui chơi Sealife", false, 2),
            img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/29_zing.jpeg", "Góc chụp từ trên cao", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/bai-dai-nha-trang-co-mot-thien-duong-binh-di-noi-ha-gioi-voi-bien-xanh-cat-trang-quanh-nam-song-xo-bo-5-1622489585.jpg", "Chòi nghỉ ven biển", false, 4),
            img("https://duan-sungroup.com/wp-content/uploads/2022/10/hoang-hon-tho-mong-tai-bai-dai.jpg", "Hoàng hôn Bãi Dài", false, 5)
        ))
        .content(List.of(
            heading("Bãi Dài – đường chân trời màu ngọc"),
            quote("""
                    Trưa rải tơ nắng mỏng,
                    gió gỡ sóng lên bờ;
                    cát trắng ngân theo bước,
                    chân trời kéo dài hơn.
                """),
            paragraph("Thuộc vịnh Cam Ranh và cách trung tâm Nha Trang khoảng 25–30km, Bãi Dài nổi tiếng với mặt nước trong, dải cát mịn và độ thoai thoải lý tưởng để tắm biển – đi dạo. Nhịp điệu ở đây mộc mạc, chủ yếu là tiếng gió và sóng, điểm xuyết những chòi nghỉ, quán hải sản ven bờ và vài khu vui chơi – resort mới."),

            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• 06:00–09:00: biển êm, nắng xiên nhẹ, hợp chạy bộ – bơi gần bờ, săn nước trong."),
            paragraph("• 16:30–18:30: ‘giờ vàng’ và ‘giờ xanh’ liên tiếp, ánh chiều phản chiếu lên mặt nước rất đẹp, phù hợp ngồi chill dưới hàng dừa và chụp hoàng hôn."),
            paragraph("• Ngày gió nhỏ (swell thấp): thử SUP/board gần bờ; ngày sóng lớn: ưu tiên đi dạo – chụp ảnh, hạn chế bơi xa."),

            imageBlock(img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/17_zing.jpeg", "Trải dài cát trắng", false, 0)),

            heading("Gợi ý hoạt động"),
            paragraph("• Tắm biển nông – an toàn: chọn khu có cờ báo hiệu/cứu hộ; với trẻ nhỏ, luôn giữ trong tầm tay và dùng áo phao."),
            paragraph("• SUP/chèo ván: đi sát bờ, quan sát hướng gió; cố định dây buộc chân (leash) và không rời ván khi có sóng đẩy."),
            paragraph("• Dạo bộ – nhặt ‘vân sóng’: chụp macro vệt bọt/hoa văn cát; chạy nhẹ dọc bờ 1–2km để cảm nhận độ thoai thoải của bãi."),
            paragraph("• Chill quán ven biển: gọi nước dừa/món nướng đơn giản, ngồi ngắm mặt trời hạ qua rặng dừa."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Toàn cảnh: đặt đường chân trời ở 1/3 khung để ‘ăn’ nhiều trời – mây; chọn tiêu cự 24–35mm cho ảnh rộng vừa phải, ít méo."),
            paragraph("• Tiền cảnh cát: dùng dấu chân/vân sóng dẫn mắt; khép khẩu f/8–f/11 để nét từ gần tới xa."),
            paragraph("• Hoàng hôn: đứng lệch 30–45° so với bờ để bắt vệt sóng xéo; đợi khoảnh khắc sóng rút để lộ cát ướt phản gương."),
            paragraph("• Người & biển: tránh đặt chủ thể quá sát nền trời; hạ thấp máy để mặt biển ‘dày’ hơn, giảm hiện tượng ‘đầu người chạm đường chân trời’."),

            heading("Ăn uống & lưu ý thực tế"),
            paragraph("• Hải sản: hỏi rõ giá theo kg – cách chế biến; gọi phần vừa để thử nhiều món (tôm, mực, nghêu, sò)."),
            paragraph("• Đồ mang theo: kem chống nắng, nón, khăn mỏng, nước uống; túi rác nhỏ tự gom bỏ đúng nơi."),
            paragraph("• Sứa & nắng gắt: ngày nắng cao, có thể gặp sứa nhỏ; nếu châm nhẹ, rửa bằng nước biển (không nước ngọt ngay), theo dõi phản ứng da."),

            heading("An toàn bờ biển"),
            paragraph("Bãi thoai thoải nhưng vẫn có dòng chảy rút (rip) theo thời điểm. Dấu hiệu: mặt nước ‘bằng phẳng’ khác lạ giữa vùng sóng, bọt ít và có cảm giác kéo ra ngoài. Nếu bị cuốn: bình tĩnh, bơi chéo song song bờ 10–20m để thoát dòng rồi mới bơi vào; kêu gọi cứu hộ khi cần."),
            paragraph("Không để đồ quý trên bãi khi xuống nước; giữ khoảng cách an toàn với ca nô/khu vui chơi có phao kéo."),

            heading("Lịch trình gợi ý 2–3 giờ"),
            paragraph("• 0’–20’: gửi xe, khởi động, đi bộ tìm góc chụp toàn cảnh."),
            paragraph("• 20’–70’: tắm biển/SUP gần bờ (ngày sóng nhỏ) hoặc dạo – chụp macro vân sóng (ngày sóng lớn)."),
            paragraph("• 70’–110’: ghé quán ven biển, gọi hải sản nhẹ/nước dừa, ngồi ngắm chiều."),
            paragraph("• 110’–150’: chụp hoàng hôn, bắt vệt sóng, thu dọn rác, rời bãi khi trời chạng vạng."),

            heading("Kết hợp điểm lân cận (cung Nguyễn Tất Thành)"),
            paragraph("• Sealife/khu trò chơi trên biển: thử nhà phao/chụp ảnh màu sắc cho nhóm gia đình."),
            paragraph("• Các viewpoint đường ven biển: dừng ngắn ở vài điểm cao nhìn toàn vịnh Cam Ranh."),
            paragraph("• Các resort/khu café ven bờ: chọn quán có sân trước nhìn thẳng ra biển để ngắm ‘giờ xanh’ sau hoàng hôn."),

            gallery(List.of(
                img("https://statics.vinpearl.com/bai-dai-cam-lam-04_1664546932.jpg", "Biển xanh – cát trắng", false, 0),
                img("https://statics.vinpearl.com/bai-dai-cam-lam-06_1664546916.jpg", "Cung đường ven biển", false, 1),
                img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/29_zing.jpeg", "Góc flycam Bãi Dài", false, 2)
            )),
            quote("“Đường chân trời ở Bãi Dài dường như… dài thêm một chút.”"),
            divider(),
            mapBlock(new double[]{109.2150, 11.9980})
        ))
        .build();
  }

  /** 4) Dốc Lết (Ninh Hòa) */
  private static PlaceDoc poiDocLet() {
    String slug = "doc-let-beach";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Biển Dốc Lết (Ninh Hòa)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Bãi biển cát trắng, nước nông trong, thích hợp gia đình.")
        .description("Cách Nha Trang ~50km về phía Bắc, nổi tiếng bãi nông, vệt cát trắng mịn, hàng dương râm mát.")
        .addressLine("Ninh Hải, Ninh Hòa, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2320, 12.5820})
        .images(withCover(
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang.jpg", "Bờ biển Dốc Lết", true, 0),
            img("https://static.vinwonders.com/2022/04/mQs99b7G-doc-let-nha-trang-6.jpg", "Hàng dừa – hàng dương", false, 1),
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-7.jpg", "Địa điểm check-in", false, 2),
            img("https://static.vinwonders.com/production/ghe-tham-lang-chai-ninh-thuy.jpg", "Thuyền thúng ven bờ", false, 3),
            img("https://static.vinwonders.com/production/ruong-muoi-hon-khoi.jpg", "Bình minh vàng rượi", false, 4),
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-9.jpg", "Chiều yên ả", false, 5)
        ))
        .content(List.of(
            heading("Dốc Lết – êm đềm phía Bắc vịnh"),
            quote("""
                    Cát trắng ru bàn chân,
                    sóng vỗ nhịp rất khẽ;
                    gió thổi mùi mặn trong,
                    một ngày trôi êm ả.
                """),
            paragraph("Cách trung tâm Nha Trang khoảng 50km về phía Bắc, Dốc Lết nổi tiếng với bãi thoai thoải, nước nông trong và dải cát mịn kéo dài dưới tán dương rì rào. Không gian yên ả, hợp gia đình có trẻ nhỏ, nhóm bạn thích tắm biển nhẹ nhàng – đi dạo – picnic."),

            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• 06:00–09:00: trời trong, nước mát, thích hợp bơi nhẹ – chạy bộ dọc bờ."),
            paragraph("• 16:30–18:00: ‘giờ vàng’ dịu nắng, đón hoàng hôn, chụp ảnh phản gương trên cát ướt."),
            paragraph("• Giữa trưa: ưu tiên trú bóng râm hàng dương, bổ sung nước – khoáng, hạn chế tắm lâu ngoài nắng gắt."),

            imageBlock(img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-2.jpg", "Nước nông trong vắt", false, 0)),

            heading("Gợi ý cho gia đình"),
            paragraph("• Trẻ nhỏ: áo bơi tay dài, nón, kính bơi; chỉ bơi khu nước nông ngang gối – hông, luôn trong tầm tay người lớn."),
            paragraph("• Nhịp hoạt động: chia phiên 20–30 phút (tắm) ↔ 10–15 phút (nghỉ – uống nước) để tránh kiệt sức, say nắng."),
            paragraph("• Picnic nhẹ: bánh mì, trái cây đã gọt, túi rác riêng để thu gom trước khi rời bãi."),

            heading("Gợi ý hoạt động"),
            paragraph("• Tắm biển – đi dạo: cảm nhận độ thoai thoải hiếm có, dễ chịu cho mọi lứa tuổi."),
            paragraph("• Chèo SUP gần bờ (ngày sóng nhỏ): dùng leash buộc chân, không đi quá xa các phao giới hạn."),
            paragraph("• Ngắm bình minh/hoàng hôn: chọn đoạn bờ thông thoáng, đợi sóng rút để mặt cát lấp lánh phản gương."),

            heading("Ăn uống & dịch vụ"),
            paragraph("Các hàng quán ven biển phục vụ hải sản đơn giản (tôm, mực, nghêu…). Hỏi rõ giá theo kg – cách chế biến; gọi phần vừa để thử nhiều món. Dịch vụ ghế – dù – tắm nước ngọt có tại nhiều điểm dọc bãi; nên hỏi giá trước khi dùng."),

            heading("Điểm gần kề để kết hợp"),
            paragraph("• Làng chài Ninh Thủy: ngắm thúng chài, đời sống ngư dân; mua hải sản tươi theo thời điểm."),
            paragraph("• Ruộng muối Hòn Khói: đi sớm ngắm bình minh vàng rượi trên ô muối, chụp ảnh lao động mùa vụ."),
            paragraph("Hai điểm đều thuận đường đi – về, giúp lịch trình đa dạng hơn trong nửa ngày."),

            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("• Toàn cảnh: đặt đường chân trời ở 1/3 khung để ‘ăn’ nhiều trời xanh – mây."),
            paragraph("• Tiền cảnh: dùng hàng dương, thuyền thúng, vân sóng làm dẫn mắt; khép khẩu f/8–f/11 để ảnh nét đều."),
            paragraph("• Hoàng hôn: đứng chếch 30–45° so với bờ, chụp liên tiếp khi sóng rút để bắt vệt bọt trắng."),

            heading("An toàn & ứng xử"),
            paragraph("Bãi nông nhưng đôi lúc có dòng chảy ngược (rip). Dấu hiệu: mặt nước phẳng khác thường giữa vùng sóng, bọt ít, cảm giác kéo ra xa. Nếu gặp: bình tĩnh, bơi song song bờ 10–20m để thoát dòng rồi mới bơi vào. Không để đồ quý trên bãi; không xả rác, hạn chế loa công suất lớn."),

            heading("Lịch trình gợi ý 2–3 giờ"),
            paragraph("• 0’–20’: gửi xe, khởi động, đi dọc bờ tìm góc chụp."),
            paragraph("• 20’–70’: tắm biển/SUP gần bờ (ngày sóng nhỏ) hoặc đi dạo – chụp vân sóng (ngày sóng lớn)."),
            paragraph("• 70’–110’: ghé quán ven biển, gọi hải sản nhẹ – nước dừa, nghỉ ngơi."),
            paragraph("• 110’–150’: chụp hoàng hôn, thu gom rác, rửa nước ngọt và rời bãi."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/04/doc-let-nha-trang.jpg", "Bờ biển Dốc Lết", false, 0),
                img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-2.jpg", "Làn nước xanh mát", false, 1),
                img("https://static.vinwonders.com/production/ghe-tham-lang-chai-ninh-thuy.jpg", "Thúng chài ven bờ", false, 2)
            )),
            quote("“Sóng nhỏ, cát mịn – Dốc Lết hợp cho một ngày của gia đình.”"),
            divider(),
            mapBlock(new double[]{109.2320, 12.5820})
        ))
        .build();
  }

  /** 5) Đảo Điệp Sơn (Con đường trên biển) */
  private static PlaceDoc poiDiepSon() {
    String slug = "diep-son-island";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Đảo Điệp Sơn (Con đường trên biển)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Dải cát nổi nối 3 đảo nhỏ, check-in “con đường trên biển” độc đáo.")
        .description("Thuộc vịnh Vân Phong, nước xanh trong. Lưu ý thủy triều; nên đi theo tour/cano địa phương.")
        .addressLine("Vịnh Vân Phong, Vạn Ninh, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2350, 12.6350})
        .images(withCover(
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Con-duong-giua-bien.png", "Con đường trên biển – Điệp Sơn", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/dao-diep-son.png", "Cát trắng nổi giữa biển", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Dao-Diep-Son-nuoc-xanh.png", "Nước xanh ngọc", false, 2),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Toan-canh-Dao-Diep-Son.png", "View flycam", false, 3),
            img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/cheo-thuyen-kayak-diep-son.png", "Thuyền địa phương", false, 4),
            img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/thu-gian-tam-bien.jpg", "Góc chụp “đi trên biển”", false, 5)
        ))
        .content(List.of(
            heading("Điệp Sơn – dải cát giữa vịnh"),
            quote("""
                    Nước rút lộ đường mỏng,
                    cát nối những đảo xa;
                    bước chạm làn xanh ngọc,
                    biển mở cửa cho ta.
                """),
            paragraph("Nằm trong vịnh Vân Phong thuộc Vạn Ninh, Điệp Sơn nổi tiếng bởi dải cát nổi uốn lượn nối liền 3 đảo nhỏ, tạo nên trải nghiệm ‘đi trên biển’ độc đáo hiếm nơi có. Những ngày trời trong, làn nước xanh ngọc nhìn thấy đáy, gió nhẹ, sóng êm – rất hợp cho một buổi tham quan nửa ngày kết hợp tắm biển và chụp ảnh."),

            heading("Thủy triều & thời điểm đẹp"),
            paragraph("Trải nghiệm ‘con đường trên biển’ phụ thuộc mạnh vào mực nước. Lý tưởng là khung giờ nước rút (thường vào buổi sáng muộn hoặc chiều muộn tùy ngày). Khi triều lên, dải cát có thể chìm 10–50cm; vẫn có thể đi nhưng nên mặc áo phao và đi thành hàng theo hướng dẫn viên (HDV)."),
            paragraph("Gợi ý khung giờ: 07:30–10:00 hoặc 15:30–17:30 để có ánh sáng êm, dễ chụp và đỡ nắng. Tránh giữa trưa nắng gắt, đặc biệt vào mùa khô."),

            heading("Cách đi & lịch trình nửa ngày"),
            paragraph("Xuất phát từ đất liền bằng cano/thuyền địa phương; nên đi tour nhỏ 6–12 người để linh hoạt thời gian theo bảng triều. Lộ trình gợi ý: bến cano → đảo A (khởi điểm dải cát) → đi bộ dọc ‘con đường trên biển’ → tắm biển nhẹ gần bờ → nghỉ chân, thưởng thức hải sản đơn giản → di chuyển ngắm vịnh bằng cano → quay về bờ."),
            imageBlock(img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/kham-pha-cuoc-song-tren-dao-diep-son.jpg", "Đời sống bình dị trên đảo Điệp Sơn", false, 0)),

            heading("An toàn trên biển"),
            paragraph("• Luôn mang dép/giày nước để tránh vỏ sò, đá vôi sắc. Không chạy nhảy, không dẫm lên rạn/san hô non."),
            paragraph("• Nếu nước vượt mắt cá chân, mặc áo phao; trẻ nhỏ phải có người lớn nắm tay. Tránh đứng chắn luồng cano và tuân thủ hướng dẫn của HDV."),
            paragraph("• Trời chuyển mây đen – gió mạnh: dừng di chuyển trên dải cát, quay về điểm cao/đảo gần nhất theo hiệu lệnh."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Từ cao: lên gò/điểm cao trên đảo để lấy trọn đường cát cong và hai mảng nước xanh hai bên."),
            paragraph("• Trên dải cát: đi lệch 30–45° so với trục đường để thấy rõ độ cong; đợi sóng rút để bọt trắng vẽ tiền cảnh."),
            paragraph("• Màu nước: mặc trang phục tông trắng/nhạt để nổi bật trên nền xanh; khép khẩu f/8–f/11 cho ảnh phong cảnh nét đều."),
            paragraph("• Flycam: chỉ bay khi thời tiết tốt, giữ khoảng cách an toàn khỏi đám đông – hỏi HDV/khu vực cho phép trước khi cất cánh."),

            heading("Ăn uống & dịch vụ"),
            paragraph("Hải sản địa phương theo mùa (tôm – mực – ốc…) chế biến đơn giản; hỏi rõ giá theo kg/cách chế biến trước khi gọi. Nước uống mang thêm từ bờ; hạn chế mang ly nhựa dùng một lần. Một số tour có kèm bữa trưa nhẹ trên đảo hoặc tại nhà bè – kiểm tra trước khi đặt."),

            heading("Ứng xử & bền vững"),
            paragraph("Không bẻ san hô, không nhặt vỏ còn sinh vật bên trong; không xả rác, gom rác mang về bờ. Dùng kem chống nắng ‘reef-safe’ nếu có. Tôn trọng sinh hoạt ngư dân, không tự ý vào khu vực cấm/tư gia."),

            heading("Lịch trình gợi ý 3–4 giờ"),
            paragraph("• 0’–30’: di chuyển cano ra đảo, nghe HDV phổ biến an toàn – lịch triều."),
            paragraph("• 30’–90’: dạo ‘con đường trên biển’, chụp ảnh các góc cong – toàn cảnh."),
            paragraph("• 90’–150’: tắm biển nhẹ gần bờ, nghỉ chân – dùng bữa hải sản đơn giản."),
            paragraph("• 150’–210’: đi cano vòng vịnh ngắm cảnh, về lại bờ khi trời chuyển ‘giờ xanh’."),

            heading("Lưu ý thực tế"),
            paragraph("Mang mũ rộng vành, áo khoác mỏng chống nắng, nước uống, túi chống nước cho điện thoại/máy ảnh. Sóng – gió thay đổi nhanh theo mùa; luôn kiểm tra dự báo thời tiết, lịch triều trước ngày đi và tuân thủ chỉ dẫn của HDV."),

            gallery(List.of(
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Con-duong-giua-bien.png", "Con đường giữa biển", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Dao-Diep-Son-nuoc-xanh.png", "Nước xanh ngọc", false, 1),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Toan-canh-Dao-Diep-Son.png", "Toàn cảnh vịnh Vân Phong", false, 2)
            )),
            quote("“Khi thủy triều rút, biển mở ra lối đi cho kẻ mộng mơ.”"),
            divider(),
            mapBlock(new double[]{109.2350, 12.6350})
        ))
        .build();
  }

  /** 6) Suối Ba Hồ (Ba Hồ Waterfalls) */
  private static PlaceDoc poiBaHoWaterfalls() {
    String slug = "ba-ho-waterfalls";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Suối Ba Hồ (Ba Hồ Waterfalls)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Tổ hợp 3 hồ – thác giữa rừng, trek nhẹ, tắm suối mát lạnh.")
        .description("Cách trung tâm ~25km về Bắc. Đường mòn có biển chỉ dẫn; nên mang giày bám, cẩn thận đoạn đá ướt.")
        .addressLine("Ninh Ích, Ninh Hòa, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2160, 12.4170})
        .images(withCover(
            img("https://statics.vinpearl.com/ba-ho-nha-trang-7_1628260967.jpg", "Hồ – thác giữa rừng", true, 0),
            img("https://static.vinwonders.com/production/suoi-ba-ho-1-1.jpg", "Làn nước xanh trong", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-14-1-e1534474709261.jpg", "Đường mòn treo biển", false, 2),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-11-e1534474677178.jpg", "Nhảy đá – tắm suối", false, 3),
            img("https://static.vinwonders.com/2022/03/suoi-ba-ho-6.jpg", "Các trò chơi vui nhộn", false, 4),
            img("https://static.vinwonders.com/2022/03/suoi-ba-ho-5.jpg", "Không khí mát lành", false, 5)
        ))
        .content(List.of(
            heading("Ba Hồ – mát rượi giữa thiên nhiên"),
            quote("""
                    Nắng rơi qua tán lá,
                    đá thở giữa làn trong;
                    nước hát theo triền suối,
                    mồ hôi hoá mây bồng.
                """),
            paragraph("Cách trung tâm Nha Trang khoảng 25km về phía Bắc, Suối Ba Hồ là tổ hợp 3 hồ – 3 bậc thác ẩn giữa rừng với màu nước xanh mát quanh năm. Lối mòn có biển chỉ dẫn rõ, thỉnh thoảng băng qua những phiến đá phủ rêu nên đi chậm, bám tay vịn. Phần thưởng là hồ nước trong veo, bóng cây rừng mát rượi và tiếng thác như một bản nhạc nền tự nhiên."),

            heading("Thời điểm & nhịp trải nghiệm"),
            paragraph("• 07:00–10:00: không khí mát, nắng xiên qua tán rừng—dễ chịu để trek và chụp ảnh luồng sáng."),
            paragraph("• 15:30–17:30: bóng râm nhiều, nhiệt độ hạ, hợp tắm suối – ngồi thiền nghe tiếng nước."),
            paragraph("• Tránh đi ngay sau mưa lớn: nước đổ mạnh, đá trơn; ưu tiên ngày khô ráo để bám đá tốt hơn."),

            heading("Tuyến đường 3 hồ"),
            paragraph("• Hồ 1: gần nhất, đường dễ, mặt nước rộng—phù hợp dừng chân, ngâm mình, chụp toàn cảnh thác."),
            paragraph("• Hồ 2: đoạn đá lớn hơn, cần bước chắc—đổi lại vắng hơn, nước sâu hơn, khung xanh đẹp."),
            paragraph("• Hồ 3: cảnh hoang sơ, lối đi có mỏm đá hẹp; chỉ nên đi khi thời tiết khô, còn đủ thể lực, luôn theo mũi tên chỉ dẫn."),
            imageBlock(img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-15-e1534474585258.jpg", "Hồ số 1 – điểm dừng phổ biến", false, 0)),

            heading("Chuẩn bị & an toàn"),
            paragraph("• Giày đế bám chống trượt, dép/giày nước để xuống hồ; balo gọn, túi khô cho điện thoại/giấy tờ."),
            paragraph("• 1–2 chai nước nhỏ, vài gói năng lượng; xịt chống côn trùng, khăn mỏng."),
            paragraph("• Không nhảy từ vách cao nếu chưa kiểm tra độ sâu/vật cản; quan sát dòng chảy trước khi xuống nước."),
            paragraph("• Đi theo nhóm 2+ người; thông báo thời gian dự kiến quay lại; luôn giữ trẻ nhỏ trong tầm mắt."),

            heading("Chơi gì cho ‘đã’"),
            paragraph("• Tắm suối: chọn vũng nước tĩnh, không bơi sát mép đổ; giữ khoảng cách an toàn với người khác."),
            paragraph("• ‘Chill’ theo rừng: ngồi thiền 5–10 phút ở phiến đá khô, nghe tiếng lá – tiếng nước làm ‘metronome’."),
            paragraph("• Picnic nhẹ: chọn bờ đá phẳng, trải khăn nhỏ; ăn xong gom rác vào túi mang về cổng."),
            paragraph("• Khu trò chơi (nếu có mở): chỉ tham gia tại khu vực cho phép và dùng dây đai/bảo hộ theo hướng dẫn."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Mềm dòng thác: đặt tốc độ 1/8–1/4s, tựa máy lên tảng đá/balo làm ‘chân máy’; khép khẩu f/8–f/11."),
            paragraph("• Tia nắng xuyên tán: đi buổi sáng để bắt ‘khói nước’ lung linh khi hạt nước gặp nắng xiên."),
            paragraph("• Bố cục tầng: đứng chếch 30–45° để thấy rõ tầng thác phía sau và mảng xanh bao quanh."),
            paragraph("• Chân dung rừng: nền tường đá/tán lá tối—đo sáng vào da, tránh ngược sáng gắt giữa trưa."),

            heading("Lịch trình gợi ý 2–3 giờ"),
            paragraph("• 0’–20’: gửi xe, khởi động nhẹ, vào lối mòn theo bảng chỉ dẫn."),
            paragraph("• 20’–45’: tới Hồ 1—nghỉ, ngâm chân, chụp toàn cảnh."),
            paragraph("• 45’–90’: đi tiếp tới Hồ 2—tắm suối, chụp close-up dòng chảy."),
            paragraph("• 90’–150’: cân nhắc lên Hồ 3 (nếu thời tiết khô và còn sức); quay đầu về trước khi trời chuyển."),

            heading("Giữ rừng xanh"),
            paragraph("Không bẻ cành, không khắc vẽ lên đá; đi trên lối mòn để tránh dẫm nát rễ cây. Tắt loa công suất lớn, nhường âm thanh cho rừng. Rác mang vào—rác mang ra; chai nhựa, khăn giấy, vỏ snack… bỏ lại là ‘vết xước’ rất lâu mờ."),

            heading("Điểm gần kề để kết hợp"),
            paragraph("Có thể nối cung với Dốc Lết (tắm biển chiều mát) hoặc ghé Ninh Hòa thưởng thức ẩm thực địa phương trước khi quay lại Nha Trang."),

            gallery(List.of(
                img("https://statics.vinpearl.com/ba-ho-nha-trang-7_1628260967.jpg", "Hồ – thác giữa rừng", false, 0),
                img("https://static.vinwonders.com/production/suoi-ba-ho-1-1.jpg", "Màu nước xanh mát", false, 1),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-11-e1534474677178.jpg", "Đường mòn phiêu lưu", false, 2)
            )),
            quote("“Đi chậm từng bước đá, nghe rừng kể chuyện nước.”"),
            divider(),
            mapBlock(new double[]{109.2160, 12.4170})
        ))
        .build();
  }

  /** 7) Đồng muối Hòn Khói (Salt Fields) */
  private static PlaceDoc poiHonKhoiSaltField() {
    String slug = "hon-khoi-salt-field";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Đồng muối Hòn Khói (Ninh Hòa)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Cánh đồng muối nổi tiếng với khung cảnh lao động bình minh.")
        .description("Khoảng cách ~40–50km từ Nha Trang. Đẹp nhất lúc sớm khi người dân thu hoạch muối.")
        .addressLine("Ninh Hòa, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2270, 12.5530})
        .images(withCover(
            img("https://static.vinwonders.com/2022/04/hon-khoi.jpg", "Đồng muối Hòn Khói bình minh", true, 0),
            img("https://static.vinwonders.com/2022/04/mKrOrEC5-hon-khoi-5.jpg", "Gánh muối trên đồng", false, 1),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/3_20250812211322.jpg", "Đồng muối mênh mông", false, 2),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/18_20250812204404.jpg", "Hạt muối trắng", false, 3),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/4_20250812204544.jpg", "Cánh đồng muối bao la", false, 4),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/1_20250812204405.jpg", "Phản chiếu trời trong ô muối", false, 5)
        ))
        .content(List.of(
            heading("Hòn Khói – khúc ca bình minh trên muối"),
            quote("""
                    Trời đỏ dần qua sương,
                    muối sáng như lời hát;
                    vai gánh nghiêng đường dài,
                    hạt trắng đẫm nắng mật.
                """),
            paragraph("Cánh đồng muối Hòn Khói mang vẻ đẹp rất ‘động’: lúc rạng đông, mặt nước trong ô ruộng như tấm gương khổng lồ phản chiếu bầu trời; khi nắng cao, các đụn muối trắng nổi bật trên nền xanh biển xa. Nhịp lao động bắt đầu sớm tinh mơ – cào, gom, gánh – vẽ nên những đường cong mềm trên nền trời hồng."),

            heading("Thời điểm & cách đi"),
            paragraph("• Từ Nha Trang → Ninh Hòa: quãng 40–50km. Nên có mặt trước bình minh 20–30 phút để săn ‘giờ xanh’ rồi chuyển sang ‘giờ vàng’."),
            paragraph("• 05:00–07:00: lúc đẹp nhất để thấy người làm muối đang thu hoạch, ánh sáng mềm, phản chiếu rõ. Sau 08:00 nắng mạnh, mặt ruộng phản quang dễ chói mắt, ảnh gắt."),
            paragraph("• Đi xe máy/ô tô tự túc: gửi xe ở khu vực cho phép và đi bộ theo lối bờ ô muối; tôn trọng biển báo, không tự ý vào khu vực cấm."),

            imageBlock(img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/2_20250812204404.jpg", "Màu trời phản chiếu trên ô muối", false, 0)),

            heading("Ứng xử tại đồng muối"),
            paragraph("• Giữ khoảng cách an toàn, nhường lối cho xe cào và người gánh; không đặt chân máy giữa đường đi."),
            paragraph("• Xin phép trước khi chụp cận cảnh chân dung; hạn chế chụp quá sát gây mất tập trung khi họ đang vác nặng."),
            paragraph("• Không bước xuống ô muối/đụn muối; một dấu chân cũng có thể làm hỏng bề mặt kết tinh."),
            paragraph("• Nếu mua nước/đồ ăn ở quán nhỏ gần đó, hãy trả giá hợp lý, coi như lời cảm ơn người địa phương."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Toàn cảnh: dùng ‘đường gánh’ làm leading line hướng về mặt trời; hạ máy thấp để mặt nước nhân đôi bầu trời."),
            paragraph("• Phản chiếu: đứng sát mép ô muối, canh gió nhẹ để mặt nước phẳng; khép khẩu f/8–f/11 cho ảnh nét đều."),
            paragraph("• Chân dung lao động: đứng chếch 30–45° so với hướng di chuyển để thấy dáng người cân đối với quang gánh; tắt flash, ưu tiên ánh sáng tự nhiên."),
            paragraph("• Chống chói: đội nón, đeo kính râm; với máy ảnh, cân nhắc gắn filter GND/PL để kiểm soát bầu trời và phản xạ."),

            heading("Câu chuyện mùa muối (tham khảo)"),
            paragraph("Mùa nắng là thời điểm sản lượng tốt: nắng đều, gió nhẹ giúp muối kết tinh nhanh. Trước mỗi mùa, người làm muối tu sửa bờ ô, đắp nền phẳng; trong mùa, nhịp việc sớm – trưa – chiều khác nhau. Khi tới tham quan, bạn như đi qua ‘xưởng ngoài trời’ – hãy luôn tôn trọng nhịp độ đó."),

            heading("Chuẩn bị & an toàn"),
            paragraph("• Trang phục: áo dài tay, nón rộng vành, giày/dep bám tốt (bờ ô muối trơn)."),
            paragraph("• Vật dụng: nước uống, khăn giấy, kem chống nắng; túi rác nhỏ để tự gom rác."),
            paragraph("• Thiết bị bay: chỉ bay flycam khi gió nhỏ và khu vực cho phép; giữ khoảng cách, không bay thấp trên đầu người gánh."),

            heading("Lịch trình gợi ý 90–120 phút"),
            paragraph("• 0’–20’: đến nơi trước bình minh, khảo sát lối đi, chọn vị trí phản chiếu."),
            paragraph("• 20’–60’: chụp toàn cảnh mặt trời lên + phản chiếu trên ô muối."),
            paragraph("• 60’–90’: chuyển sang chụp cận cảnh người gánh, chi tiết hạt muối – quang gánh (xin phép trước)."),
            paragraph("• 90’–120’: nghỉ ngắn, mua nước ủng hộ quán nhỏ, xem lại ảnh – rời điểm khi nắng trở nên gắt."),

            heading("Kết hợp điểm gần kề"),
            paragraph("Có thể ghé Biển Dốc Lết (cách không xa) để tắm biển buổi sáng muộn, hoặc vòng về làng chài Ninh Thủy ngắm thúng chài – ăn hải sản tươi theo thời điểm."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/04/hon-khoi.jpg", "Bình minh trên đồng muối", false, 0),
                img("https://static.vinwonders.com/2022/04/mKrOrEC5-hon-khoi-5.jpg", "Gánh muối trên nền trời hồng", false, 1),
                img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/3_20250812211322.jpg", "Ô muối phản chiếu trời", false, 2)
            )),

            quote("“Hạt muối trắng – mồ hôi trong veo của người làm đồng.”"),
            divider(),
            mapBlock(new double[]{109.2270, 12.5530})
        ))
        .build();
  }

  /** 8) Vịnh Ninh Vân (Ninh Vân Bay) */
  private static PlaceDoc poiNinhVanBay() {
    String slug = "ninh-van-bay";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Vịnh Ninh Vân (Ninh Vân Bay)")
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription("Vịnh yên tĩnh, resort ẩn mình giữa rừng – đá – biển.")
        .description("Cách trung tâm ~20km đường chim bay (thường đi thuyền). Nước trong, bờ đá đẹp; phù hợp nghỉ dưỡng tĩnh.")
        .addressLine("Ninh Vân, Ninh Hòa, Khánh Hòa")
        .countryCode("VN").provinceName("Khánh Hòa")
        .location(new double[]{109.2790, 12.4500})
        .images(withCover(
            img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-4.jpg", "Vịnh Ninh Vân tĩnh lặng", true, 0),
            img("https://static.vinwonders.com/production/vinh-ninh-van-cach-nha-trang-60-km.jpg", "Bờ đá – rừng – biển", false, 1),
            img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-7-1.jpg", "Bến thuyền vào vịnh", false, 2),
            img("https://statics.vinpearl.com/ninh-van-du-lich-Nha-Trang_1671162085.jpg", "Nước xanh trong vắt", false, 3),
            img("https://static.vinwonders.com/production/cam-trai-tai-vinh-ninh-van.jpg", "Không gian riêng tư", false, 4),
            img("https://static.vinwonders.com/2022/12/ninh-van-du-lich-Nha-Trang-7.jpg", "Bình minh ở vịnh", false, 5)
        ))
        .content(List.of(
            heading("Ninh Vân – trú ẩn giữa thiên nhiên"),
            quote("""
                    Gió đi qua triền đá,
                    sóng ru lưng rừng xanh;
                    một bờ vịnh lặng tiếng,
                    lòng theo nước yên lành.
                """),
            paragraph("Cách trung tâm Nha Trang một quãng thuyền ngắn, vịnh Ninh Vân ẩn mình giữa rừng – đá – biển. Làn nước trong, bờ đá độc bản và nhịp sống chậm tạo cảm giác “trốn phố” đúng nghĩa. Đây là lựa chọn lý tưởng cho chuyến nghỉ dưỡng tĩnh: đọc sách trên deck gỗ, nghe sóng thay tiếng xe, để gió biển hong khô mọi ồn ã."),

            heading("Thời điểm & cách di chuyển"),
            paragraph("Phần lớn điểm lưu trú/tour có thuyền đưa – đón theo khung giờ cố định từ đất liền. Ngày biển êm, hành trình rất thư thái (10–20 phút). Mang balo gọn, giày bám tốt nếu dự tính leo ghềnh đá ngắm cảnh. Khung giờ đẹp: bình minh (05:30–07:00) và ‘giờ vàng’ 16:30–18:00."),

            imageBlock(img("https://statics.vinpearl.com/vinh-ninh-van-3_1625620560.jpg", "Deck gỗ nhìn thẳng ra vịnh", false, 0)),

            heading("Gợi ý trải nghiệm"),
            paragraph("• Ngồi thiền/thở sâu trên deck gỗ vào sáng sớm, nghe sóng vỗ đều như metronome tự nhiên."),
            paragraph("• Kayak/SUP dọc bờ những ngày gió nhẹ; tạm neo ở dải cát nhỏ kẹp giữa bãi đá để tắm biển."),
            paragraph("• Trek ngắn 20–40 phút lên điểm cao vừa phải phía sau khu lưu trú để thu toàn cảnh ‘rừng – đá – biển’."),
            paragraph("• Tối giảm đèn, ngắm bầu trời sao; thưởng thức hải sản đơn giản tại khu nghỉ/nhà bè (tuỳ dịch vụ)."),

            heading("Góc chụp & mẹo ảnh"),
            paragraph("• Toàn cảnh: đứng trên sườn đá sau khu lưu trú để ‘ăn’ đủ ba lớp rừng – đá – biển; đặt đường chân trời ở 1/3 khung."),
            paragraph("• Tiền cảnh sóng – đá: chụp chếch 30–45° để thấy vệt bọt kéo dài khi sóng rút; khép khẩu f/8–f/11 cho ảnh nét đều."),
            paragraph("• Bình minh: đo sáng vào vùng trời, hạ EV 0.3–0.7 để giữ màu; lấy phản chiếu nước làm lớp dẫn."),
            paragraph("• Chân dung tĩnh: nền tường đá/ghềnh rêu xám xanh giúp tôn da, tránh backlight gắt giữa trưa."),

            heading("Ăn uống & nhịp sinh hoạt"),
            paragraph("Ẩm thực thiên về hải sản tươi theo ngày (tôm, mực, cá theo mùa) chế biến đơn giản. Đặt bữa trước khung giờ đông để bếp chuẩn bị kịp. Ban ngày ưu tiên nước điện giải, trái cây; chiều tối nhâm nhi nhẹ để còn thời gian ngắm trời sao – nghe sóng."),

            heading("An toàn & lưu ý nhỏ"),
            paragraph("Địa hình đá có thể trơn khi ẩm; đi chậm, tránh dép trơn trượt. Không leo lên mỏm nhô sát mép khi gió mạnh. Nếu chèo kayak/SUP: luôn mang áo phao, buộc leash, không đi xa ngoài vùng quan sát của bãi. Ban đêm hạn chế ra ghềnh đá do tầm nhìn kém."),

            heading("Bền vững & tôn trọng không gian"),
            paragraph("Không bẻ san hô, không nhặt vỏ còn sinh vật; dùng kem chống nắng ‘reef-safe’ nếu tắm/chèo. Không bật loa lớn—giữ nhịp tĩnh vốn là tinh thần của Ninh Vân. Nếu chỉ tham quan mà không lưu trú, tôn trọng khu vực riêng tư của resort, đi đúng lối cho phép."),

            heading("Lịch trình gợi ý 1 ngày ‘chậm’"),
            paragraph("• Sáng: thuyền vào vịnh → cà phê/thiền nhẹ trên deck → kayak vòng bờ 40–60’ → tắm biển."),
            paragraph("• Trưa: nghỉ trưa trong phòng/ghế dài dưới bóng cây, đọc sách."),
            paragraph("• Chiều: trek ngắn lên viewpoint → chụp ‘giờ vàng’ trên ghềnh đá."),
            paragraph("• Tối: bữa hải sản vừa đủ, tắt bớt đèn để ngắm sao, nghe sóng trước khi ngủ."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-4.jpg", "Vịnh tĩnh lặng nhìn từ bờ đá", false, 0),
                img("https://statics.vinpearl.com/ninh-van-du-lich-Nha-Trang_1671162085.jpg", "Nước xanh ôm mô đá", false, 1),
                img("https://static.vinwonders.com/2022/12/ninh-van-du-lich-Nha-Trang-7.jpg", "Bình minh ở vịnh", false, 2)
            )),

            quote("“Ninh Vân là cách tự thưởng – cho tâm trí có khoảng lặng.”"),
            divider(),
            mapBlock(new double[]{109.2790, 12.4500})
        ))
        .build();
  }
}