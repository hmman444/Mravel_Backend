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
public class SeedHoiAnPois {

  @Bean
  CommandLineRunner seedHoiAnPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedHoiAnPois] start");

      List<PlaceDoc> pois = List.of(
          poiChuaCau(),
          poiPhoCoHoiAn(),
          poiSongHoaiThuyenDenLong(),
          poiBienAnBang(),
          poiCuLaoCham(),
          poiLangGomThanhHa(),
          poiLangRauTraQue(),
          poiNhaCoTanKy()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedHoiAnPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedHoiAnPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedHoiAnPois] done, inserted = " + inserted);
    };
  }

  // ===================== POI FOR HOI AN =====================

  /** 1) Chùa Cầu (Japanese Covered Bridge) */
  private static PlaceDoc poiChuaCau() {
    String slug = "chua-cau-hoi-an";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Chùa Cầu (Japanese Covered Bridge)")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Biểu tượng của Hội An với cây cầu gỗ cổ bắc qua lạch nhỏ.")
        .description("Chùa Cầu được người Nhật xây dựng vào đầu thế kỷ 17, là điểm check-in không thể bỏ qua, "
            + "đặc biệt rực rỡ khi lên đèn về đêm.")
        .addressLine("186 Trần Phú, Minh An, Hội An, Quảng Nam")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3201, 15.8789})
        .images(withCover(
            img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu Hội An", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-4-1722841902.jpg", "Cận cảnh cầu gỗ", false, 1),
            img("https://cdn.thehappystay.vn/upload/2024/08/25/mceu_48402689611724577059109.jpg", "Chùa Cầu lên đèn", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-9-1722845764.jpg", "Lối dẫn vào cầu", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-10-1722841902.jpg", "Phố cổ gần Chùa Cầu", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-2-1722842709.jpg", "Góc chụp từ trên cao", false, 5)
        ))
        .content(List.of(
            heading("Chùa Cầu – biểu tượng vượt thời gian"),
            quote("""
                    Mái ngói ôm hơi phố,
                    gỗ cũ kể trăng rằm;
                    bước nhẹ qua lưng sóng,
                    ký ức bắc ngang tâm.
                """),
            paragraph("Chùa Cầu (còn gọi Cầu Nhật Bản) là công trình gỗ mái ngói độc đáo, xuất hiện từ đầu thế kỷ XVII khi thương nhân Nhật – Hoa – Việt giao thương sầm uất ở Hội An. Cầu vừa là lối qua lạch nhỏ, vừa là một “ngôi chùa” nhỏ trú bóng giữa phố: mái cong thấp, kèo gỗ đen, lan can hẹp – mọi chi tiết đều thấm vẻ thời gian."),
            heading("Lịch sử & kiến trúc"),
            paragraph("Cấu trúc gỗ liên kết mộng – chốt, mái ngói âm dương, mặt cầu hẹp đủ hai người tránh nhau. Hãy để ý đầu đao, con giống trang trí và các nhịp xà ngang; những vết mòn trên mặt ván là “dấu chân lịch sử”. Phía hai đầu cầu nối với tuyến phố cổ – khi đông, đi theo một chiều để tránh ùn."),
            imageBlock(img("https://cdn.hoabinhevents.com/hbt/wp-content/uploads/2025/08/11150947/Chua-cau-hoi-an-01.jpg", "Hình ảnh Chùa Cầu được in trên tờ 20.000 đồng", false, 0)),
            heading("Thời điểm ghé thăm"),
            paragraph("Sáng sớm 06:00–07:00: phố còn thưa, ánh xiên nhẹ, ảnh trong và tĩnh. Chiều – hoàng hôn 16:30–18:30: đèn lồng lên, bóng nước hắt ánh vàng rất thơ; đây là khung giờ đông nhất – chờ nhịp thưa người để bấm máy."),
            heading("Góc chụp & mẹo nhỏ"),
            paragraph("Chính diện: lùi về bên kia lạch để lấy trọn mái cong và bóng phản chiếu. Góc chéo 30–45°: nhấn đường cong mái, thấy rõ cấu kiện gỗ. Cận chi tiết: zoom hoa văn đầu đao, mối nối gỗ. Đêm: tắt flash, áp ống vào sát lan can để giảm phản chiếu; tránh đứng chắn luồng người qua lại."),
            heading("Phố cổ quanh Chùa Cầu"),
            paragraph("Từ cầu, rẽ Trần Phú – Nguyễn Thái Học – Bạch Đằng để bắt trọn sắc đèn lồng, mái ngói rêu và các nhà cổ. Chọn quán nước nhỏ nhìn ra lạch để nghỉ 10–15 phút rồi tiếp tục vòng; mua hàng thủ công nhớ hỏi chất liệu, cách bảo quản và xin hóa đơn."),
            heading("Ứng xử & bảo tồn"),
            paragraph("Đi chậm, nói nhỏ, không trèo – ngồi lên lan can, không khắc vẽ lên gỗ. Giữ hàng lối một chiều khi cao điểm; nếu chụp chân dung gần, xin phép trước. Mang theo túi rác nhỏ, không xả rác xuống lạch."),
            gallery(List.of(
                img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu biểu tượng", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-4-1722841902.jpg", "Cận cảnh cầu gỗ", false, 1),
                img("https://cdn.thehappystay.vn/upload/2024/08/25/mceu_48402689611724577059109.jpg", "Chùa Cầu lên đèn", false, 2)
            )),
            heading("Lộ trình gợi ý 45–75 phút"),
            paragraph("Đứng đối diện chụp toàn cảnh → qua cầu, quan sát cấu kiện gỗ – mái ngói → dạo Trần Phú – Nguyễn Thái Học → vòng Bạch Đằng ngắm lạch và đèn lồng → quay lại cầu lúc lên đèn để chụp khung đêm."),
            quote("“Một cây cầu nhỏ nối liền ký ức lớn của phố Hội.”"),
            divider(),
            mapBlock(new double[]{108.3201, 15.8789})
        ))
        .build();
  }

  /** 2) Khu Phố cổ Hội An (khu đi bộ) */
  private static PlaceDoc poiPhoCoHoiAn() {
    String slug = "pho-co-hoi-an";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Phố cổ Hội An (Khu đi bộ)")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Khu phố cổ UNESCO, kiến trúc gỗ vàng, đèn lồng rực rỡ mỗi tối.")
        .description("Dạo bộ qua các tuyến Trần Phú, Nguyễn Thái Học, Bạch Đằng; ghé các hội quán, "
            + "nhà cổ và cửa hàng thủ công truyền thống.")
        .addressLine("P. Minh An, Hội An, Quảng Nam")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3268, 15.8770})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ về đêm", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn/images/2016/anhInternet/00HoiAn-ReneLeen-1.jpg", "Dãy nhà vàng đặc trưng", false, 1),
            img("https://statics.vinpearl.com/pho-den-long-hoi-an-01_1628228734.jpg", "Gian hàng đèn lồng", false, 2),
            img("https://afamilycdn.com/k:thumb_w/600/KIgpmlAko7AE3tMxv4rcRxIxxjDEe6/Image/2015/04/Giang%20Pham-Hoi%20An%20trong%20ngo%20hem-1-fa7a4/cuoc-song-hoi-an-trong-ngo-hem.jpg", "Ngõ nhỏ bình yên", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-co-phung-hung-hoi-an-net-dong-xua-cu-ben-trong-pho-co-hoai-niem-2-1617759105.jpeg", "Nhà gỗ cổ", false, 4),
            img("https://moodhoian.vn/storage/photos/shares/H%E1%BB%99i%20An%20-%20%E2%80%9CTh%C3%A0nh%20ph%E1%BB%91%20c%E1%BB%A7a%20nh%E1%BB%AFng%20danh%20hi%E1%BB%87u%E2%80%9D%20hi%E1%BA%BFu%20kh%C3%A1ch%20b%E1%BA%ADc%20nh%E1%BA%A5t%20th%E1%BA%BF%20gi%E1%BB%9Bi/1-nu-cuoi-reatimes-1524481488.jpeg", "Người dân hiền hoà", false, 5)
        ))
        .content(List.of(
            heading("Phố cổ – bước qua miền ký ức"),
            quote("""
                    Mái ngói nghiêng màu nắng,
                    tường vàng ủ mùi vôi;
                    đèn lồng rung nhịp gió,
                    phố kể chuyện xa xôi.
                """),
            paragraph("Khu phố cổ Hội An là di sản UNESCO lưu giữ lớp lớp dấu tích giao thương Đông – Tây. Những ngôi nhà gỗ vàng, cửa lá sách, hiên sâu và sân trong tạo nên nhịp sống chậm rãi rất riêng. Khung giờ 16:30–20:30 đẹp nhất: nắng cuối ngày làm tường vàng rực lên, rồi đèn lồng bật sáng phủ một lớp ánh lung linh trên mặt phố và bờ sông."),
            heading("Lối dạo gợi ý 90–120 phút"),
            paragraph("Bắt đầu từ Trần Phú (nhiều hội quán – chùa – nhà cổ) → rẽ Nguyễn Thái Học ngắm các cửa hiệu thủ công → vòng Bạch Đằng men sông Hoài để đón đèn lên; thỉnh thoảng rẽ vào những ngõ nhỏ giao cắt để thấy sân trong và giếng trời – đặc sản kiến trúc phố Hội. Đi bộ một chiều, giữ nhịp chậm để quan sát mặt tiền gỗ, đầu kèo, phù điêu."),
            heading("Nhà cổ – hội quán & cách tham quan"),
            paragraph("Với nhà cổ/hội quán, ưu tiên mua vé tham quan và đi đúng luồng chỉ dẫn. Đọc bảng tóm lược ở lối vào để nắm bố cục (tiền đường – chính điện – hậu điện/sân trong), sau đó chú ý các chi tiết: cấu kiện gỗ, vì kèo chồng rường, gạch bát, họa tiết tứ quý. Giữ giọng nói nhỏ, không tựa – chạm lên đồ gỗ sơn son thếp vàng."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Pho-den-long-Hoi-An-Chiem-nguong-ve-dep-lung-linh-day-lang-man-01-1618322258.jpg", "Phố cổ lung linh", false, 0)),
            heading("Đèn lồng & góc chụp ảnh"),
            paragraph("Giờ ‘xanh’ trước khi trời tối giúp ảnh trong và cân sáng tốt; khi đèn lồng bật, tránh dùng flash để giữ màu đèn tự nhiên. Góc chính diện ở các giao lộ cho chiều sâu dãy nhà; góc chéo 30–45° nhấn mái ngói – hiên sâu. Ở Bạch Đằng, lùi về sát sông để bắt được phản chiếu đèn lồng trên mặt nước."),
            heading("Ăn vặt & quà mang về"),
            paragraph("Thử cao lầu, mì Quảng, bánh bèo – bánh vac, chè bắp Cẩm Nam; gọi suất nhỏ để trải nghiệm nhiều món. Mua đèn lồng gấp, đồ gỗ, lụa, gốm – hỏi rõ chất liệu, kích thước và cách gấp/đóng gói; xin hóa đơn để đổi trả khi cần. Tránh mua san hô hay sản phẩm từ động vật hoang dã."),
            heading("Ứng xử & lưu ý"),
            paragraph("Khu đi bộ có đoạn đông – đi sát lề, nhường lối khi chụp ảnh; không xả rác, không viết/khắc lên tường nhà cổ. Trời mưa, gạch lát có thể trơn – giày đế bám sẽ an toàn hơn. Xin phép trước khi chụp chân dung người dân/tiểu thương; tôn trọng không gian tín ngưỡng trong các hội quán."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ rực rỡ khi lên đèn", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn/images/2016/anhInternet/00HoiAn-ReneLeen-1.jpg", "Dãy nhà vàng đặc trưng", false, 1),
                img("https://statics.vinpearl.com/pho-den-long-hoi-an-01_1628228734.jpg", "Gian hàng đèn lồng", false, 2)
            )),
            heading("Lộ trình ngắn 45–60 phút (nếu gấp)"),
            paragraph("Trần Phú → Chùa Cầu (chụp toàn cảnh) → Nguyễn Thái Học (cửa hiệu – đèn lồng) → Bạch Đằng (men sông ngắm đèn) rồi kết thúc tại bến thuyền."),
            quote("“Đi chậm một nhịp, Hội An sẽ kể bạn nghe.”"),
            divider(),
            mapBlock(new double[]{108.3268, 15.8770})
        ))
        .build();
  }

  /** 3) Sông Hoài – Thuyền & Đèn lồng */
  private static PlaceDoc poiSongHoaiThuyenDenLong() {
    String slug = "song-hoai-thuyen-den-long";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Sông Hoài – Thuyền & Đèn lồng")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Trải nghiệm ngồi thuyền thả hoa đăng trên sông Hoài về đêm.")
        .description("Một trong những hoạt động lãng mạn nhất ở Hội An; thuyền dạo nhẹ theo dòng, "
            + "phố lồng đèn phản chiếu lung linh trên mặt nước.")
        .addressLine("Bờ sông Bạch Đằng, Minh An, Hội An")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3205, 15.8765})
        .images(withCover(
            img("https://eholiday.vn/wp-content/uploads/2023/03/tha-den-hoa-dang-tren-song-Hoai.jpeg", "Thuyền trên sông Hoài", true, 0),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-4.jpg", "Thả hoa đăng", false, 1),
            img("https://eholiday.vn/wp-content/uploads/2023/02/Song-Hoai-Hoi-An.jpg", "Bến thuyền", false, 2),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-5.jpg", "Ánh đèn lồng soi nước", false, 3),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-6.jpg", "Đêm Hội An", false, 4),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-8.jpg", "Khung cảnh yên bình", false, 5)
        ))
        .content(List.of(
            heading("Sông Hoài – khoảnh khắc nên thơ"),
            quote("""
                    Nước ôm chân phố cổ,
                    đèn lấp lánh như sao;
                    thuyền khua vòng rất khẽ,
                    đêm trôi chậm qua nhau.
                """),
            paragraph("Dọc bờ Bạch Đằng, đèn lồng phủ sắc xuống mặt sông Hoài, tạo nên khung cảnh lãng mạn bậc nhất phố Hội. Một vòng thuyền ngắn 15–20 phút là vừa: đủ để hít mùi gỗ ẩm, nghe tiếng chèo chạm nước, nhìn những căn nhà vàng in bóng bên triền sông và dõi theo ánh đèn lồng thả trôi."),
            heading("Thời điểm & nhịp thuyền"),
            paragraph("Đẹp nhất 18:30–20:00 khi phố vừa lên đèn mà chưa quá đông. Nếu thích yên ả, đi sớm hơn một chút hoặc muộn sau 21:00. Lên thuyền nhớ ngồi cân về giữa, giữ nhịp nhỏ khi di chuyển; trẻ em ngồi trong lòng người lớn."),
            heading("Cách trải nghiệm gợi ý (15–20 phút)"),
            paragraph("Xuống thuyền ở bến gần cầu An Hội → xuôi nhẹ qua đoạn phố sáng đèn → dừng ngắn giữa dòng để ngắm phản chiếu → thả một – hai hoa đăng (nếu muốn) rồi vòng về bờ đối diện. Trao đổi trước tuyến đi – thời lượng với người lái để tránh lỡ kế hoạch."),
            imageBlock(img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-11.jpg", "Người dân thân thiện", false, 0)),
            heading("Ứng xử & an toàn trên thuyền"),
            paragraph("Lên xuống theo hướng dẫn, bước từng người; không nghiêng hẳn về một phía để chụp ảnh. Đặt túi/điện thoại vào túi chống nước hoặc đeo dây tay; không thò tay quá thấp gần mặt nước khi thuyền đang giao nhau. Tôn trọng khoảng cách với thuyền khác, không bật nhạc lớn."),
            heading("Chụp ảnh & mẹo nhỏ"),
            paragraph("Tắt flash để giữ màu đèn lồng tự nhiên; tăng ISO vừa đủ, giữ tay chắc hoặc tựa vào mạn thuyền. Góc ngược sáng từ lòng sông lên phố giúp khung hình có chiều sâu; khi thuyền dừng, chụp chéo 30–45° để gom cả dải đèn lồng và bóng nước."),
            heading("Hoa đăng & môi trường"),
            paragraph("Nếu thả hoa đăng, ưu tiên loại có chất liệu dễ phân huỷ; thả ít nhưng có ý nghĩa. Không xả rác hay vứt bao bì xuống sông; khi lên bờ, mang theo túi nhỏ để gom rác cá nhân."),
            gallery(List.of(
                img("https://eholiday.vn/wp-content/uploads/2023/03/tha-den-hoa-dang-tren-song-Hoai.jpeg", "Thả hoa đăng trên sông", false, 0),
                img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-5.jpg", "Ánh đèn phản chiếu mặt nước", false, 1),
                img("https://eholiday.vn/wp-content/uploads/2023/02/Song-Hoai-Hoi-An.jpg", "Bến thuyền Sông Hoài", false, 2)
            )),
            quote("“Một chiếc thuyền nhỏ, một vốc ánh sáng – đủ nên thơ cho cả đêm.”"),
            divider(),
            mapBlock(new double[]{108.3205, 15.8765})
        ))
        .build();
  }

  /** 4) Biển An Bàng */
  private static PlaceDoc poiBienAnBang() {
    String slug = "bien-an-bang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Biển An Bàng")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Bãi biển yên bình cách phố cổ ~4–5km, cát trắng, quán ven biển đẹp.")
        .description("Phù hợp tắm biển, ngắm bình minh; dọc biển có nhiều quán hải sản, bar nhỏ chill buổi tối.")
        .addressLine("Đ. Hai Bà Trưng kéo dài, Cẩm An, Hội An")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3534, 15.9110})
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhInternet/BienAnBang.jpg", "Bãi biển An Bàng", true, 0),
            img("https://statics.vinpearl.com/Anh%202%20bien%20An%20Bang_1627534981.jpg", "Nắng vàng cát trắng", false, 1),
            img("https://statics.vinpearl.com/Anh%201%20bien%20An%20Bang_1627534950.jpg", "Hàng dừa ven biển", false, 2),
            img("https://statics.vinpearl.com/tu-a-z-kinh-nghiem-du-lich-bien-an-bang-di-lai-an-o-vui-choi-202107261126582841_1690003128.jpeg", "Quán ven biển", false, 3),
            img("https://statics.vinpearl.com/zyro-image_1690003729.jpeg", "Hoàng hôn", false, 4),
            img("https://statics.vinpearl.com/bi%CC%80nh%20minh_1690003590.jpeg", "Bình minh trên biển An Bàng", false, 5)
        ))
        .content(List.of(
            heading("An Bàng – bình yên trước đại dương"),
            quote("""
                    Gió gỡ mùi muối mỏng,
                    nắng rải hạt lên cát;
                    sóng thở đều bờ vắng,
                    lòng chậm lại – an yên.
                """),
            paragraph("Bãi biển An Bàng cách khu phố cổ khoảng 4–5km, bờ cát dài thoai thoải với hàng dừa rợp bóng và nhiều quán ven biển. Sáng sớm là thời khắc trong nhất: nước êm, nắng dịu, người thưa; chiều muộn thì lý tưởng để ngắm hoàng hôn và chill ở các quán bar nhỏ."),
            heading("Thời điểm & trải nghiệm gợi ý"),
            paragraph("05:30–08:30: tắm biển – đi dạo – chạy bộ; 16:30–18:30: ngắm hoàng hôn, ngồi quán nhìn sóng. Nếu đi cùng trẻ nhỏ, chọn đoạn có cờ/cứu hộ; thủy triều – sóng có thể thay đổi theo ngày, nên quan sát bảng thông báo tại bãi."),
            imageBlock(img("https://statics.vinpearl.com/Bo%CC%82%CC%81n%20%C4%90a%20Ba%CC%89ng%20A%CC%89nh%20%C4%90a%CC%81m%20cu%CC%9Bo%CC%9B%CC%81i%20Khung%20Tranh%20in%20va%CC%89i_1690003456.jpg", "Các dịch vụ giải trí tại bãi biển An Bàng", false, 0)),
            heading("Ăn uống & quán ven biển"),
            paragraph("Dọc bờ có nhiều quán hải sản và café nhìn thẳng ra biển. Gọi phần vừa phải để thử được nhiều món (mực nướng, nghêu hấp, gỏi cá…), hỏi giá theo kg và cách chế biến trước khi gọi. Buổi tối một số quán bật nhạc nhẹ, phù hợp ngắm gió và trò chuyện."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Bình minh: đặt đường chân trời ở 1/3 khung để ăn nhiều trời. Hoàng hôn: đứng chéo 30–45° theo hàng dừa để tạo chiều sâu. Khi chụp người ở bờ nước, đợi sóng rút để lấy bọt trắng làm tiền cảnh. Tránh dựng tripod ở lối đi chung."),
            heading("An toàn & lưu ý"),
            paragraph("Tôn trọng biển báo và cờ hiệu cứu hộ; không bơi xa khi sóng lớn hoặc sau bữa ăn no. Thoa kem chống nắng, mang nước uống và túi chống nước cho điện thoại. Giữ vệ sinh chung: không xả rác, nhặt rác nhỏ quanh chỗ ngồi trước khi rời đi."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhInternet/BienAnBang.jpg", "Bờ biển An Bàng", false, 0),
                img("https://statics.vinpearl.com/Anh%201%20bien%20An%20Bang_1627534950.jpg", "Hàng dừa ven biển", false, 1),
                img("https://statics.vinpearl.com/zyro-image_1690003729.jpeg", "Hoàng hôn trên biển", false, 2)
            )),
            quote("“Cần một nơi để thở chậm? Ra An Bàng nghe sóng kể chuyện.”"),
            divider(),
            mapBlock(new double[]{108.3534, 15.9110})
        ))
        .build();
  }

  /** 5) Cù Lao Chàm (Cham Islands) */
  private static PlaceDoc poiCuLaoCham() {
    String slug = "cu-lao-cham";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Cù Lao Chàm (Cham Islands)")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Khu dự trữ sinh quyển thế giới, biển xanh – san hô đẹp, lặn ngắm tuyệt vời.")
        .description("Có nhiều bãi tắm như Bãi Làng, Bãi Chồng; trải nghiệm cano, snorkeling, hải sản tươi.")
        .addressLine("Tân Hiệp, Hội An, Quảng Nam")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.4880, 15.9480})
        .images(withCover(
            img("https://hoangphuan.com/wp-content/uploads/2024/07/kinh-nghiem-du-lich-cu-lao-cham-avata.jpg", "Toàn cảnh Cù Lao Chàm", true, 0),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/imagesbaoquangnamvn-storage-newsportal-2023-2-23-139110-tnb-57047-12.jpg", "Bãi biển nước trong", false, 1),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/Cham-Island-Hoi-An-Hoi-An-Private-Taxi.jpg", "Tàu cano", false, 2),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/trai-nghiem-lan-bien-ngam-san-ho-o-dao-Cu-Lao-Cham-ivivu-03.jpg", "San hô rực rỡ", false, 3),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/optimizebai-huong-cu-lao-cham-11.jpg", "Làng chài Bình yên", false, 4),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/Hai-san-tuoi-song-Cu-Lao-Cham1-e1652015737390.jpg", "Hải sản tươi", false, 5)
        ))
        .content(List.of(
            heading("Cù Lao Chàm – thiên đường biển gần Hội An"),
            quote("""
                    Gió mang hương muối biếc,
                    sóng vẽ nét san hô;
                    nắng rót vàng lên cát,
                    lòng mở rộng như hồ.
                """),
            paragraph("Cù Lao Chàm là Khu dự trữ sinh quyển thế giới, nổi tiếng nước trong, bãi cát mịn và rạn san hô phong phú. Từ Cửa Đại đi cano khoảng 20–25 phút là tới; khuyến nghị mang kem chống nắng thân thiện rạn san hô, túi chống nước cho điện thoại và một chai nước nhỏ."),
            heading("Thời điểm & điều kiện biển"),
            paragraph("Mùa biển êm thường từ khoảng 3–8; ngày gió mạnh/biển động, tour có thể điều chỉnh hoặc huỷ vì an toàn. Buổi sáng 8:00–11:00 nước thường trong hơn; thủy triều lên xuống ảnh hưởng độ sâu điểm lặn – nghe hướng dẫn của HDV để chọn bãi phù hợp."),
            heading("Lịch trình gợi ý nửa ngày (4–5 giờ)"),
            paragraph("Xuất phát Cửa Đại → Bãi Làng (tham quan giếng cổ, chợ nhỏ) → cano qua Bãi Chồng/Bãi Xếp để snorkeling (30–45 phút) → tắm biển tự do → ăn trưa hải sản đơn giản → về lại bờ đầu giờ chiều. Nếu đi cả ngày, thêm thời gian dạo Bãi Hương và nghỉ café nhìn vịnh."),
            imageBlock(img("https://qta.org.vn/wp-content/uploads/2024/05/di-bo-duoi-bien-cu-lao-cham-3.jpg", "Nước trong thấy đáy", false, 0)),
            heading("Snorkeling & an toàn trên biển"),
            paragraph("Luôn mặc áo phao khi xuống nước; đeo kính – ống thở đúng cỡ, thử trước trên bờ. Không đứng/trèo lên san hô, không chạm tay vào sinh vật biển; giữ khoảng cách với tàu/cano. Nếu không biết bơi, đi kèm HDV, bám phao kéo; tránh ăn quá no trước khi xuống nước."),
            heading("Ăn uống & trải nghiệm làng biển"),
            paragraph("Bãi Làng/Bãi Hương có quán bình dân: mực nướng, ốc vú nàng, cá nướng, rau rừng. Hỏi giá theo phần/kg và cách chế biến trước khi gọi. Dạo làng chài, chào hỏi người dân, mua ít đặc sản khô (mực một nắng, bánh ít lá gai) làm quà – xin hoá đơn nếu cần."),
            heading("Bảo tồn & ứng xử với môi trường"),
            paragraph("Dùng kem chống nắng reef-safe, không xả rác; gom rác cá nhân mang về bờ. Không bẻ, nhặt san hô/ốc; hạn chế túi nylon trên thuyền. Chỉ thả neo ở vị trí cho phép – tuân thủ khu vực bảo vệ rạn."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Chụp trên cano: ngồi mũi thuyền lấy đường chân trời 1/3 khung. Dưới nước: áp ống gần mặt nước để bắt dải san hô và tia nắng xiên; giữ máy ổn định, chụp liên tiếp để chọn khung rõ. Trên bờ: đứng chéo 30–45° theo hàng dừa ở Bãi Chồng để tạo chiều sâu."),
            gallery(List.of(
                img("https://hoangphuan.com/wp-content/uploads/2024/07/kinh-nghiem-du-lich-cu-lao-cham-avata.jpg", "Toàn cảnh Cù Lao Chàm", false, 0),
                img("https://hoangphuan.com/wp-content/uploads/2024/07/trai-nghiem-lan-bien-ngam-san-ho-o-dao-Cu-Lao-Cham-ivivu-03.jpg", "Snorkeling ngắm san hô", false, 1),
                img("https://hoangphuan.com/wp-content/uploads/2024/07/Hai-san-tuoi-song-Cu-Lao-Cham1-e1652015737390.jpg", "Hải sản tươi rói", false, 2)
            )),
            quote("“Nước xanh, cát trắng, san hô sát mắt – thiên nhiên ở khoảng cách một sải tay.”"),
            divider(),
            mapBlock(new double[]{108.4880, 15.9480})
        ))
        .build();
  }

  /** 6) Làng gốm Thanh Hà */
  private static PlaceDoc poiLangGomThanhHa() {
    String slug = "lang-gom-thanh-ha";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Làng gốm Thanh Hà")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Làng nghề truyền thống bên sông Thu Bồn, nổi tiếng gốm đất nung.")
        .description("Trải nghiệm nặn gốm bằng tay, tham quan công viên Đất Nung và mua quà thủ công địa phương.")
        .addressLine("Phạm Phán, Thanh Hà, Hội An")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.2987, 15.8773})
        .images(withCover(
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-5.png", "Làng gốm Thanh Hà", true, 0),
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-4.png", "Nặn gốm thủ công", false, 1),
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-6.png", "Sản phẩm gốm", false, 2),
            img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-01.jpg", "Công viên Đất Nung", false, 3),
            img("https://images.vietnamtourism.gov.vn/vn//images/2024/thang_10/0110.gom_thanh_ha_1.jpg", "Lò nung truyền thống", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-06.jpg", "Không gian làng nghề", false, 5)
        ))
        .content(List.of(
            heading("Thanh Hà – giữ lửa nghề gốm"),
            quote("""
                    Đất nâu qua tay ấm,
                    vòng xoay hát nhịp tròn;
                    gió hun mùi rơm rạ,
                    lửa thở hóa linh hồn.
                """),
            paragraph("Làng gốm Thanh Hà nằm ven sông Thu Bồn, nổi tiếng với dòng gốm đất nung mộc mạc, bền chắc. Không gian làng nghề yên ả, lò nung xen vườn nhà; khách có thể ghé từng hộ gia đình xem nghệ nhân chuốt gốm, thử tay nghề và mua đồ thủ công về làm quà."),
            heading("Lịch sử & chất liệu"),
            paragraph("Gốm Thanh Hà dùng đất địa phương, pha – lọc – ủ kỹ để đạt độ dẻo, sau đó chuốt trên bàn xoay, phơi nắng rồi vào lò nung. Sắc gốm đỏ – nâu là màu của đất và lửa, không tráng men cầu kỳ nhưng bề mặt có độ ‘thở’, hợp khí hậu miền Trung."),
            heading("Trải nghiệm nặn gốm"),
            paragraph("Người lớn và trẻ em đều có thể thử: làm ướt tay, giữ nhịp đều, đỡ thành cho khỏi sụm. Nếu mới làm lần đầu, chọn chén/ly nhỏ để cảm nhận nhịp bàn xoay. Thành phẩm có thể nung – nhận sau hoặc mang về làm kỷ niệm (tuỳ cơ sở)."),
            imageBlock(img("https://images.vietnamtourism.gov.vn/vn//images/2018/images1468333TNB66701.jpg", "Trải nghiệm nặn gốm", false, 0)),
            heading("Công viên Đất Nung & lò truyền thống"),
            paragraph("Ghé Công viên Đất Nung để xem mô hình – sắp đặt từ gốm, chụp ảnh giữa không gian ‘đất – gió – nắng’. Ở khu lò truyền thống, quan sát quy trình xếp gốm – nhóm lửa; giữ khoảng cách an toàn, tránh lại gần khi đang nung."),
            heading("Mua quà & vận chuyển"),
            paragraph("Chọn món vừa tay (cốc/chậu/đèn gốm), kiểm tra vết nứt lông tơ, gõ nhẹ nghe tiếng ‘đanh’. Xin bọc chống sốc hai lớp, ghi chú ‘fragile’ nếu gửi xe/ship. Tránh món quá mỏng nếu di chuyển xa."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Trong nhà: đứng chếch 30–45° để bắt nhịp tay nghệ nhân và vòng xoay. Ngoài sân: chụp hàng gốm đang phơi theo đường chéo khung hình cho chiều sâu. Không dùng flash sát mặt người, xin phép trước khi chụp cận."),
            heading("Ứng xử & bảo tồn"),
            paragraph("Đi nhẹ tay với đồ trưng bày, không tự ý chạm sản phẩm chưa khô. Hỗ trợ làng nghề bằng cách mua sản phẩm chính chủ, hạn chế mặc cả quá sâu. Giữ vệ sinh chung, không vứt rác vào khu phơi – khu lò."),
            gallery(List.of(
                img("https://danangbest.com/upload_content/lang-gom-thanh-ha-4.png", "Trải nghiệm nặn gốm", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn//images/2024/thang_10/0110.gom_thanh_ha_1.jpg", "Lò nung truyền thống", false, 1),
                img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-06.jpg", "Không gian làng nghề", false, 2)
            )),
            quote("“Đất hoá hình, lửa hoá hồn – gốm giữ câu chuyện của bàn tay.”"),
            divider(),
            mapBlock(new double[]{108.2987, 15.8773})
        ))
        .build();
  }

  /** 7) Làng rau Trà Quế */
  private static PlaceDoc poiLangRauTraQue() {
    String slug = "lang-rau-tra-que";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Làng rau Trà Quế")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Làng rau sạch nổi tiếng, trải nghiệm làm nông và nấu ăn.")
        .description("Tham gia trồng rau, tưới nước bằng gióng tre; lớp học nấu ăn món địa phương như cao lầu, mì Quảng.")
        .addressLine("Cẩm Hà, Hội An")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3493, 15.9148})
        .images(withCover(
            img("https://static.vinwonders.com/production/lang-rau-tra-que-0-2-1.jpg", "Cánh đồng rau Trà Quế", true, 0),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/rau-tra-que-24-5587.jpg", "Tưới rau bằng gióng", false, 1),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/xong-dat-34-6772.jpg", "Trải nghiệm làm nông", false, 2),
            img("https://hotelroyalhoian.vn/wp-content/uploads/2025/02/tra-que-vegetable-village-5.jpg", "Lớp học nấu ăn", false, 3),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/z3316610404490-d605b6f4d7386609d70abf0cc2535851-94.jpg", "Không khí trong lành", false, 4),
            img("https://anabasrestaurant.vn/wp-content/uploads/2024/09/TraQue-vegetable-village-hoian-1.jpg", "Đường làng yên ả", false, 5)
        ))
        .content(List.of(
            heading("Trà Quế – vị xanh của Hội An"),
            quote("""
                    Sáng hong mùi đất ấm,
                    gió đọng giọt trên vai;
                    tay tưới qua gióng tre,
                    lá reo thành tiếng cười.
                """),
            paragraph("Làng rau Trà Quế nổi tiếng với nguồn rau thơm sạch nhờ đất pha cát và phân bón hữu cơ từ rong nước. Buổi sáng sớm là lúc đẹp nhất: sương còn đọng trên lá, nắng nhẹ, người làng ra đồng tưới tưới – xới xới tạo nên nhịp sống mộc mạc. Đây cũng là nơi lý tưởng để trải nghiệm làm nông và tham gia lớp nấu ăn món địa phương."),
            heading("Trải nghiệm làm nông (60–90 phút)"),
            paragraph("Mặc áo nhẹ, mang giày thể thao – nón – kem chống nắng. Theo hướng dẫn viên làng: xới đất, lên luống, gieo hạt/đi cấy rau non, tưới bằng đôi gióng tre gánh nước từ giếng làng. Giữ khoảng cách với luống non, bước trên lối giữa để không dập lá."),
            imageBlock(img("https://media.thuonghieucongluan.vn/uploads/2019_12_25/1-1577229016.jpg", "Thửa ruộng xanh mướt", false, 0)),
            heading("Lớp nấu ăn & món địa phương"),
            paragraph("Sau giờ đồng, bạn có thể vào bếp học cuốn ram, trộn cao lầu/mì Quảng với rau thơm Trà Quế. Chú ý rửa tay kỹ sau khi làm vườn, ghi chép tỷ lệ gia vị – nước mắm – rau sống để về nhà làm lại. Nếu ăn chay, báo trước để điều chỉnh nguyên liệu."),
            heading("Ứng xử & an toàn"),
            paragraph("Không bứt lá ăn thử trên luống; không dùng dép bẩn bước vào khu gieo hạt. Trẻ nhỏ cần đi kèm người lớn khi tưới bằng gióng. Sau trải nghiệm, rửa chân tay sạch để tránh kích ứng da do đất – nước."),
            heading("Mua rau – gia vị mang về"),
            paragraph("Có thể mua bó rau thơm, hạt giống, mắm/ruốc làm gia vị. Chọn bó tươi, lá đứng; xin bọc giấy/kraft thay nilon; nếu vận chuyển xa, bọc thêm khăn ẩm mỏng để giữ độ tươi."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Đứng chéo 30–45° theo luống để tạo chiều sâu; chụp thấp ngang tầm lá để thấy bokeh giọt nước. Tránh đứng chắn lối tưới của người dân; xin phép trước khi chụp chân dung."),
            heading("Bền vững & tôn trọng làng nghề"),
            paragraph("Hạn chế dùng chai nhựa một lần; không xả rác ra mương tưới. Mua sản phẩm đúng cơ sở làng để ủng hộ sinh kế địa phương; trả giá nhẹ nhàng và tôn trọng công sức người làm vườn."),
            gallery(List.of(
                img("https://static.vinwonders.com/production/lang-rau-tra-que-0-2-1.jpg", "Cánh đồng rau mơn mởn", false, 0),
                img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/rau-tra-que-24-5587.jpg", "Tưới rau bằng gióng", false, 1),
                img("https://hotelroyalhoian.vn/wp-content/uploads/2025/02/tra-que-vegetable-village-5.jpg", "Lớp học nấu ăn tại làng", false, 2)
            )),
            quote("“Vị xanh của Hội An bắt đầu từ những luống rau này.”"),
            divider(),
            mapBlock(new double[]{108.3493, 15.9148})
        ))
        .build();
  }

  /** 8) Nhà cổ Tấn Ký */
  private static PlaceDoc poiNhaCoTanKy() {
    String slug = "nha-co-tan-ky";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name("Nhà cổ Tấn Ký")
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription("Ngôi nhà gỗ hơn 200 năm tuổi, kết hợp kiến trúc Hoa – Nhật – Việt.")
        .description("Một trong những nhà cổ nổi tiếng nhất ở phố cổ; nội thất gỗ, sân trong và các hoạ tiết tinh xảo.")
        .addressLine("101 Nguyễn Thái Học, Minh An, Hội An")
        .countryCode("VN").provinceName("Quảng Nam")
        .location(new double[]{108.3223, 15.8778})
        .images(withCover(
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-1.jpg", "Mặt tiền nhà cổ Tấn Ký", true, 0),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-15-1.jpg", "Sân trong nhà cổ", false, 1),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", false, 2),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-6.jpg", "Không gian cổ kính", false, 3),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/nha-co-tan-ky-hoi-an.jpg", "Vật dụng cổ", false, 4),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-13.jpg", "Du khách tham quan", false, 5)
        ))
        .content(List.of(
            heading("Tấn Ký – dấu ấn giao thoa văn hoá"),
            quote("""
                    Nhà gỗ thơm mùi nhựa,
                    mái ngói thở rêu phong;
                    bước chậm qua sân sáng,
                    xưa nay hoá chung phòng.
                """),
            paragraph("Nhà cổ Tấn Ký hơn 200 năm tuổi là một trong những ngôi nhà tiêu biểu của phố Hội, kết hợp tinh tế ba dòng ảnh hưởng: kết cấu gỗ Việt, chi tiết trang trí Hoa và bố cục không gian – tỉ lệ gọn kiểu Nhật. Mặt tiền hẹp, chiều sâu lớn, đi qua là chuỗi không gian ‘tiền đường – sân trong – hậu tẩm’ tạo thông gió, lấy sáng tự nhiên giữa khí hậu miền Trung."),
            heading("Kiến trúc & chi tiết nên ngắm kỹ"),
            paragraph("Quan sát hệ vì kèo chồng rường, các con tiện, hoạ tiết dơi – thọ – phúc chạm khắc trên kèo cột. Sân trong (thiên tĩnh) là ‘lá phổi’ của ngôi nhà: vừa lấy sáng, vừa điều tiết ẩm. Nền gạch bát, gỗ đen bóng do chà dầu nhiều năm; vài vết mòn trên bậc cấp là ‘dấu chân’ của thời gian."),
            heading("Cách tham quan hiệu quả (30–45 phút)"),
            paragraph("Bắt đầu từ tiền đường để nghe giới thiệu lịch sử gia tộc → vào gian chính xem vì kèo – án thờ → dừng ở sân trong để cảm nhận thông gió – ánh sáng → ra hậu tẩm xem vật dụng mưu sinh. Nên mua vé liên hợp các điểm nhà cổ/hội quán để tiết kiệm và đi theo một mạch câu chuyện."),
            imageBlock(img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", false, 0)),
            heading("Ứng xử & bảo tồn"),
            paragraph("Đi nhẹ, nói nhỏ; không sờ vào bề mặt gỗ sơn son thếp vàng; không tựa lưng vào án thờ/vách gỗ. Chụp ảnh tắt flash để tránh làm chói bề mặt sơn và gây khó chịu cho người khác. Tôn trọng lối đi hẹp, nhường đường ở bậc cấp."),
            heading("Góc chụp ảnh & mẹo nhỏ"),
            paragraph("Đứng chéo 30–45° ở gian chính để thấy chiều sâu dãy cột; ở sân trong, hạ thấp máy ngang thắt lưng để bắt ánh nắng đổ qua mái; chụp cận hoạ tiết chạm, lấy nét vào mép chạm nổi. Nếu đông khách, chờ nhịp trống 3–5 giây ở lối chuyển tiếp giữa các gian."),
            heading("Mua quà & lưu niệm"),
            paragraph("Một số gian giới thiệu đồ gỗ, tranh khắc, thư pháp. Ưu tiên sản phẩm thủ công nhỏ gọn (con dấu, sổ tay bìa gỗ), xin gói chống ẩm; hạn chế mặc cả quá sâu để ủng hộ gìn giữ di sản sống."),
            gallery(List.of(
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-1.jpg", "Mặt tiền nhà cổ", false, 0),
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", false, 1),
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-15-1.jpg", "Sân trong thoáng đãng", false, 2)
            )),
            quote("“Một ngôi nhà kể ba câu chuyện: Hoa – Nhật – Việt.”"),
            divider(),
            mapBlock(new double[]{108.3223, 15.8778})
        ))
        .build();
  }
}