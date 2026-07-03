package com.mravel.catalog.bootstrap.place;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.doc.PlaceDoc.BestSeason;
import com.mravel.catalog.model.doc.PlaceDoc.BestVisitTime;
import com.mravel.catalog.model.doc.PlaceDoc.CrowdLevel;
import com.mravel.catalog.model.doc.PlaceDoc.NoiseLevel;
import com.mravel.catalog.model.doc.PlaceDoc.VenueContext;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.TagType;
import com.mravel.catalog.repository.PlaceDocRepository;

import static com.mravel.catalog.utils.place.ContentBlocks.*;
import static com.mravel.catalog.utils.place.Images.*;
import static com.mravel.catalog.utils.place.Localized.of;

@Configuration
@Profile("seed")
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
        .name(of("Chùa Cầu (Japanese Covered Bridge)", "Japanese Covered Bridge (Chua Cau)"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Biểu tượng của Hội An với cây cầu gỗ cổ bắc qua lạch nhỏ.", "The icon of Hoi An, an ancient wooden bridge spanning a small canal."))
        .description(of("Chùa Cầu được người Nhật xây dựng vào đầu thế kỷ 17, là điểm check-in không thể bỏ qua, "
            + "đặc biệt rực rỡ khi lên đèn về đêm.",
            "Built by the Japanese in the early 17th century, the Covered Bridge is a must-visit landmark, "
            + "especially dazzling when lit up at night."))
        .addressLine(of("186 Trần Phú, Minh An, Hội An, Quảng Nam", "186 Tran Phu, Minh An, Hoi An, Quang Nam"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3201, 15.8789})
        .vibes(List.of("cultural", "romantic", "peaceful"))
        .atmosphere(of("heritage-colonial", "heritage-colonial"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(95)
        .romanticScore(90)
        .localExperienceScore(80)
        .images(withCover(
            img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu Hội An", "The Covered Bridge in Hoi An", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-4-1722841902.jpg", "Cận cảnh cầu gỗ", "Close-up of the wooden bridge", false, 1),
            img("https://cdn.thehappystay.vn/upload/2024/08/25/mceu_48402689611724577059109.jpg", "Chùa Cầu lên đèn", "The Covered Bridge lit up at night", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-9-1722845764.jpg", "Lối dẫn vào cầu", "The walkway leading to the bridge", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-10-1722841902.jpg", "Phố cổ gần Chùa Cầu", "Old town near the Covered Bridge", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-2-1722842709.jpg", "Góc chụp từ trên cao", "A view from above", false, 5)
        ))
        .content(List.of(
            heading("Chùa Cầu – biểu tượng vượt thời gian", "The Covered Bridge - a timeless icon"),
            quote("""
                    Mái ngói ôm hơi phố,
                    gỗ cũ kể trăng rằm;
                    bước nhẹ qua lưng sóng,
                    ký ức bắc ngang tâm.
                """,
                """
                    Tiled roof embracing the town's breath,
                    old timber recounting the full moon;
                    step softly over the waves,
                    memory bridging across the heart.
                """),
            paragraph("Chùa Cầu (còn gọi Cầu Nhật Bản) là công trình gỗ mái ngói độc đáo, xuất hiện từ đầu thế kỷ XVII khi thương nhân Nhật – Hoa – Việt giao thương sầm uất ở Hội An. Cầu vừa là lối qua lạch nhỏ, vừa là một “ngôi chùa” nhỏ trú bóng giữa phố: mái cong thấp, kèo gỗ đen, lan can hẹp – mọi chi tiết đều thấm vẻ thời gian.",
                "The Covered Bridge (also called the Japanese Bridge) is a unique tiled-roof wooden structure dating from the early 17th century, when Japanese, Chinese and Vietnamese merchants traded busily in Hoi An. It is both a passage over a small canal and a tiny “temple” sheltering amid the street: low curved roof, dark timber trusses, narrow railings - every detail steeped in the patina of time."),
            heading("Lịch sử & kiến trúc", "History & architecture"),
            paragraph("Cấu trúc gỗ liên kết mộng – chốt, mái ngói âm dương, mặt cầu hẹp đủ hai người tránh nhau. Hãy để ý đầu đao, con giống trang trí và các nhịp xà ngang; những vết mòn trên mặt ván là “dấu chân lịch sử”. Phía hai đầu cầu nối với tuyến phố cổ – khi đông, đi theo một chiều để tránh ùn.",
                "The timber structure is joined by mortise and tenon, with yin-yang roof tiles and a deck just wide enough for two people to pass. Notice the roof finials, decorative figurines and the cross beams; the worn marks on the planks are “footprints of history”. Both ends of the bridge connect to the old streets - when crowded, keep to one direction to avoid congestion."),
            imageBlock(img("https://cdn.hoabinhevents.com/hbt/wp-content/uploads/2025/08/11150947/Chua-cau-hoi-an-01.jpg", "Hình ảnh Chùa Cầu được in trên tờ 20.000 đồng", "The Covered Bridge printed on the 20,000 dong banknote", false, 0)),
            heading("Thời điểm ghé thăm", "When to visit"),
            paragraph("Sáng sớm 06:00–07:00: phố còn thưa, ánh xiên nhẹ, ảnh trong và tĩnh. Chiều – hoàng hôn 16:30–18:30: đèn lồng lên, bóng nước hắt ánh vàng rất thơ; đây là khung giờ đông nhất – chờ nhịp thưa người để bấm máy.",
                "Early morning 06:00-07:00: the streets are still quiet, the light is soft and the photos are clear and calm. Late afternoon to sunset 16:30-18:30: lanterns turn on and golden reflections shimmer on the water; this is the busiest time - wait for a gap in the crowd to take your shot."),
            heading("Góc chụp & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Chính diện: lùi về bên kia lạch để lấy trọn mái cong và bóng phản chiếu. Góc chéo 30–45°: nhấn đường cong mái, thấy rõ cấu kiện gỗ. Cận chi tiết: zoom hoa văn đầu đao, mối nối gỗ. Đêm: tắt flash, áp ống vào sát lan can để giảm phản chiếu; tránh đứng chắn luồng người qua lại.",
                "Head-on: step back to the far side of the canal to capture the full curved roof and its reflection. Diagonal 30-45 degrees: emphasizes the roof curve and reveals the timber framework. Close-up: zoom in on the finial motifs and wooden joints. At night: turn off the flash and press the lens close to the railing to reduce reflections; avoid blocking the flow of people."),
            heading("Phố cổ quanh Chùa Cầu", "The old town around the bridge"),
            paragraph("Từ cầu, rẽ Trần Phú – Nguyễn Thái Học – Bạch Đằng để bắt trọn sắc đèn lồng, mái ngói rêu và các nhà cổ. Chọn quán nước nhỏ nhìn ra lạch để nghỉ 10–15 phút rồi tiếp tục vòng; mua hàng thủ công nhớ hỏi chất liệu, cách bảo quản và xin hóa đơn.",
                "From the bridge, turn onto Tran Phu - Nguyen Thai Hoc - Bach Dang to soak in the lantern colors, mossy tiled roofs and ancient houses. Pick a small cafe overlooking the canal to rest for 10-15 minutes before continuing; when buying handicrafts, ask about the material, how to care for it and request a receipt."),
            heading("Ứng xử & bảo tồn", "Etiquette & conservation"),
            paragraph("Đi chậm, nói nhỏ, không trèo – ngồi lên lan can, không khắc vẽ lên gỗ. Giữ hàng lối một chiều khi cao điểm; nếu chụp chân dung gần, xin phép trước. Mang theo túi rác nhỏ, không xả rác xuống lạch.",
                "Walk slowly, speak quietly, do not climb on or sit on the railings, and do not carve or draw on the wood. Keep to one direction during peak hours; if taking close portraits, ask permission first. Carry a small trash bag and do not litter into the canal."),
            gallery(List.of(
                img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu biểu tượng", "The iconic Covered Bridge", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/chua-cau-hoi-an-bieu-tuong-kien-truc-pho-hoi-4-1722841902.jpg", "Cận cảnh cầu gỗ", "Close-up of the wooden bridge", false, 1),
                img("https://cdn.thehappystay.vn/upload/2024/08/25/mceu_48402689611724577059109.jpg", "Chùa Cầu lên đèn", "The Covered Bridge lit up at night", false, 2)
            )),
            heading("Lộ trình gợi ý 45–75 phút", "Suggested 45-75 minute route"),
            paragraph("Đứng đối diện chụp toàn cảnh → qua cầu, quan sát cấu kiện gỗ – mái ngói → dạo Trần Phú – Nguyễn Thái Học → vòng Bạch Đằng ngắm lạch và đèn lồng → quay lại cầu lúc lên đèn để chụp khung đêm.",
                "Stand opposite for a full-scene shot, cross the bridge to observe the timber framework and tiled roof, stroll along Tran Phu - Nguyen Thai Hoc, loop around Bach Dang to admire the canal and lanterns, then return to the bridge at dusk for a night shot."),
            quote("“Một cây cầu nhỏ nối liền ký ức lớn của phố Hội.”", "“A small bridge connecting the great memories of Hoi An.”"),
            divider(),
            mapBlock(new double[]{108.3201, 15.8789})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng Hội An", "Icon of Hoi An")).slug("bieu-tuong-hoi-an").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Chùa Cầu Nhật Bản", "Japanese Covered Bridge")).slug("chua-cau-nhat-ban").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 2) Khu Phố cổ Hội An (khu đi bộ) */
  private static PlaceDoc poiPhoCoHoiAn() {
    String slug = "pho-co-hoi-an";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Phố cổ Hội An (Khu đi bộ)", "Hoi An Ancient Town (Walking Area)"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Khu phố cổ UNESCO, kiến trúc gỗ vàng, đèn lồng rực rỡ mỗi tối.", "A UNESCO old town with golden wooden architecture and dazzling lanterns every evening."))
        .description(of("Dạo bộ qua các tuyến Trần Phú, Nguyễn Thái Học, Bạch Đằng; ghé các hội quán, "
            + "nhà cổ và cửa hàng thủ công truyền thống.",
            "Stroll along Tran Phu, Nguyen Thai Hoc and Bach Dang streets; visit assembly halls, "
            + "ancient houses and traditional handicraft shops."))
        .addressLine(of("P. Minh An, Hội An, Quảng Nam", "Minh An Ward, Hoi An, Quang Nam"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3268, 15.8770})
        .vibes(List.of("cultural", "romantic", "lively"))
        .atmosphere(of("heritage-street", "heritage-street"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "food", "photography", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(95)
        .romanticScore(90)
        .localExperienceScore(85)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ về đêm", "The old town at night", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn/images/2016/anhInternet/00HoiAn-ReneLeen-1.jpg", "Dãy nhà vàng đặc trưng", "The characteristic row of golden houses", false, 1),
            img("https://statics.vinpearl.com/pho-den-long-hoi-an-01_1628228734.jpg", "Gian hàng đèn lồng", "A lantern stall", false, 2),
            img("https://afamilycdn.com/k:thumb_w/600/KIgpmlAko7AE3tMxv4rcRxIxxjDEe6/Image/2015/04/Giang%20Pham-Hoi%20An%20trong%20ngo%20hem-1-fa7a4/cuoc-song-hoi-an-trong-ngo-hem.jpg", "Ngõ nhỏ bình yên", "A peaceful little alley", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-co-phung-hung-hoi-an-net-dong-xua-cu-ben-trong-pho-co-hoai-niem-2-1617759105.jpeg", "Nhà gỗ cổ", "An ancient wooden house", false, 4),
            img("https://moodhoian.vn/storage/photos/shares/H%E1%BB%99i%20An%20-%20%E2%80%9CTh%C3%A0nh%20ph%E1%BB%91%20c%E1%BB%A7a%20nh%E1%BB%AFng%20danh%20hi%E1%BB%87u%E2%80%9D%20hi%E1%BA%BFu%20kh%C3%A1ch%20b%E1%BA%ADc%20nh%E1%BA%A5t%20th%E1%BA%BF%20gi%E1%BB%9Bi/1-nu-cuoi-reatimes-1524481488.jpeg", "Người dân hiền hoà", "The gentle local people", false, 5)
        ))
        .content(List.of(
            heading("Phố cổ – bước qua miền ký ức", "The old town - stepping through a land of memory"),
            quote("""
                    Mái ngói nghiêng màu nắng,
                    tường vàng ủ mùi vôi;
                    đèn lồng rung nhịp gió,
                    phố kể chuyện xa xôi.
                """,
                """
                    Tiled roofs tilted in sunlight,
                    yellow walls steeped in the scent of lime;
                    lanterns swaying to the wind,
                    the street recounting far-off tales.
                """),
            paragraph("Khu phố cổ Hội An là di sản UNESCO lưu giữ lớp lớp dấu tích giao thương Đông – Tây. Những ngôi nhà gỗ vàng, cửa lá sách, hiên sâu và sân trong tạo nên nhịp sống chậm rãi rất riêng. Khung giờ 16:30–20:30 đẹp nhất: nắng cuối ngày làm tường vàng rực lên, rồi đèn lồng bật sáng phủ một lớp ánh lung linh trên mặt phố và bờ sông.",
                "Hoi An Ancient Town is a UNESCO heritage site preserving layers of East-West trade history. The golden wooden houses, louvered shutters, deep verandas and inner courtyards create a uniquely slow pace of life. The best time is 16:30-20:30: the late-day sun makes the yellow walls glow, then the lanterns light up, casting a shimmering layer over the streets and riverbank."),
            heading("Lối dạo gợi ý 90–120 phút", "Suggested 90-120 minute walk"),
            paragraph("Bắt đầu từ Trần Phú (nhiều hội quán – chùa – nhà cổ) → rẽ Nguyễn Thái Học ngắm các cửa hiệu thủ công → vòng Bạch Đằng men sông Hoài để đón đèn lên; thỉnh thoảng rẽ vào những ngõ nhỏ giao cắt để thấy sân trong và giếng trời – đặc sản kiến trúc phố Hội. Đi bộ một chiều, giữ nhịp chậm để quan sát mặt tiền gỗ, đầu kèo, phù điêu.",
                "Start on Tran Phu (many assembly halls, temples and ancient houses), turn onto Nguyen Thai Hoc to admire the handicraft shops, then loop around Bach Dang along the Hoai River to catch the lanterns lighting up; occasionally duck into the small cross alleys to see inner courtyards and skylights - a specialty of Hoi An architecture. Walk in one direction and keep a slow pace to observe the wooden facades, truss ends and reliefs."),
            heading("Nhà cổ – hội quán & cách tham quan", "Ancient houses & assembly halls - how to visit"),
            paragraph("Với nhà cổ/hội quán, ưu tiên mua vé tham quan và đi đúng luồng chỉ dẫn. Đọc bảng tóm lược ở lối vào để nắm bố cục (tiền đường – chính điện – hậu điện/sân trong), sau đó chú ý các chi tiết: cấu kiện gỗ, vì kèo chồng rường, gạch bát, họa tiết tứ quý. Giữ giọng nói nhỏ, không tựa – chạm lên đồ gỗ sơn son thếp vàng.",
                "For ancient houses and assembly halls, buy a sightseeing ticket and follow the guided flow. Read the summary board at the entrance to grasp the layout (front hall - main hall - rear hall/inner courtyard), then pay attention to the details: timber framing, stacked-beam trusses, terracotta floor tiles and the four-season motifs. Keep your voice low and do not lean on or touch the gilded lacquered woodwork."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Pho-den-long-Hoi-An-Chiem-nguong-ve-dep-lung-linh-day-lang-man-01-1618322258.jpg", "Phố cổ lung linh", "The shimmering old town", false, 0)),
            heading("Đèn lồng & góc chụp ảnh", "Lanterns & photo angles"),
            paragraph("Giờ ‘xanh’ trước khi trời tối giúp ảnh trong và cân sáng tốt; khi đèn lồng bật, tránh dùng flash để giữ màu đèn tự nhiên. Góc chính diện ở các giao lộ cho chiều sâu dãy nhà; góc chéo 30–45° nhấn mái ngói – hiên sâu. Ở Bạch Đằng, lùi về sát sông để bắt được phản chiếu đèn lồng trên mặt nước.",
                "The “blue hour” before dark gives clear, well-balanced photos; once the lanterns are on, avoid the flash to keep their natural color. A head-on angle at intersections gives depth to the rows of houses; a 30-45 degree diagonal emphasizes the tiled roofs and deep verandas. On Bach Dang, step back close to the river to capture the lanterns reflected on the water."),
            heading("Ăn vặt & quà mang về", "Snacks & souvenirs"),
            paragraph("Thử cao lầu, mì Quảng, bánh bèo – bánh vac, chè bắp Cẩm Nam; gọi suất nhỏ để trải nghiệm nhiều món. Mua đèn lồng gấp, đồ gỗ, lụa, gốm – hỏi rõ chất liệu, kích thước và cách gấp/đóng gói; xin hóa đơn để đổi trả khi cần. Tránh mua san hô hay sản phẩm từ động vật hoang dã.",
                "Try cao lau, mi Quang, banh beo and banh vac, and Cam Nam corn sweet soup; order small portions to sample many dishes. Buy foldable lanterns, woodwork, silk and ceramics - ask about the material, size and how to fold/pack them; request a receipt in case of returns. Avoid buying coral or products made from wildlife."),
            heading("Ứng xử & lưu ý", "Etiquette & notes"),
            paragraph("Khu đi bộ có đoạn đông – đi sát lề, nhường lối khi chụp ảnh; không xả rác, không viết/khắc lên tường nhà cổ. Trời mưa, gạch lát có thể trơn – giày đế bám sẽ an toàn hơn. Xin phép trước khi chụp chân dung người dân/tiểu thương; tôn trọng không gian tín ngưỡng trong các hội quán.",
                "The walking area gets crowded in places - keep to the side and give way when others are taking photos; do not litter or write/carve on the walls of ancient houses. When it rains the paving can be slippery - shoes with good grip are safer. Ask permission before taking portraits of locals or vendors; respect the spiritual spaces inside the assembly halls."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ rực rỡ khi lên đèn", "The old town glowing when the lanterns light up", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn/images/2016/anhInternet/00HoiAn-ReneLeen-1.jpg", "Dãy nhà vàng đặc trưng", "The characteristic row of golden houses", false, 1),
                img("https://statics.vinpearl.com/pho-den-long-hoi-an-01_1628228734.jpg", "Gian hàng đèn lồng", "A lantern stall", false, 2)
            )),
            heading("Lộ trình ngắn 45–60 phút (nếu gấp)", "Short 45-60 minute route (if pressed for time)"),
            paragraph("Trần Phú → Chùa Cầu (chụp toàn cảnh) → Nguyễn Thái Học (cửa hiệu – đèn lồng) → Bạch Đằng (men sông ngắm đèn) rồi kết thúc tại bến thuyền.",
                "Tran Phu, the Covered Bridge (full-scene shot), Nguyen Thai Hoc (shops and lanterns), Bach Dang (along the river admiring the lanterns), then finish at the boat pier."),
            quote("“Đi chậm một nhịp, Hội An sẽ kể bạn nghe.”", "“Slow down a beat, and Hoi An will tell you its story.”"),
            divider(),
            mapBlock(new double[]{108.3268, 15.8770})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Phố cổ", "Ancient town")).slug("pho-co").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Di sản UNESCO", "UNESCO heritage")).slug("di-san-unesco").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đèn lồng Hội An", "Hoi An lanterns")).slug("den-long-hoi-an").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 3) Sông Hoài – Thuyền & Đèn lồng */
  private static PlaceDoc poiSongHoaiThuyenDenLong() {
    String slug = "song-hoai-thuyen-den-long";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Sông Hoài – Thuyền & Đèn lồng", "Hoai River - Boats & Lanterns"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Trải nghiệm ngồi thuyền thả hoa đăng trên sông Hoài về đêm.", "A nighttime boat ride releasing floating lanterns on the Hoai River."))
        .description(of("Một trong những hoạt động lãng mạn nhất ở Hội An; thuyền dạo nhẹ theo dòng, "
            + "phố lồng đèn phản chiếu lung linh trên mặt nước.",
            "One of the most romantic activities in Hoi An; the boat drifts gently downstream while "
            + "the lantern-lit town shimmers in reflection on the water."))
        .addressLine(of("Bờ sông Bạch Đằng, Minh An, Hội An", "Bach Dang riverside, Minh An, Hoi An"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3205, 15.8765})
        .vibes(List.of("romantic", "peaceful", "cultural"))
        .atmosphere(of("riverside-night", "riverside-night"))
        .travelStyle(List.of("casual", "photography"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography", "culture"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.EVENING, BestVisitTime.NIGHT))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(45)
        .photographyScore(90)
        .romanticScore(95)
        .localExperienceScore(80)
        .images(withCover(
            img("https://eholiday.vn/wp-content/uploads/2023/03/tha-den-hoa-dang-tren-song-Hoai.jpeg", "Thuyền trên sông Hoài", "Boats on the Hoai River", true, 0),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-4.jpg", "Thả hoa đăng", "Releasing floating lanterns", false, 1),
            img("https://eholiday.vn/wp-content/uploads/2023/02/Song-Hoai-Hoi-An.jpg", "Bến thuyền", "The boat pier", false, 2),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-5.jpg", "Ánh đèn lồng soi nước", "Lantern light reflected on the water", false, 3),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-6.jpg", "Đêm Hội An", "A Hoi An night", false, 4),
            img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-8.jpg", "Khung cảnh yên bình", "A peaceful scene", false, 5)
        ))
        .content(List.of(
            heading("Sông Hoài – khoảnh khắc nên thơ", "The Hoai River - a poetic moment"),
            quote("""
                    Nước ôm chân phố cổ,
                    đèn lấp lánh như sao;
                    thuyền khua vòng rất khẽ,
                    đêm trôi chậm qua nhau.
                """,
                """
                    Water embracing the old town's feet,
                    lanterns twinkling like stars;
                    the boat stirs a gentle circle,
                    night drifting slowly past us.
                """),
            paragraph("Dọc bờ Bạch Đằng, đèn lồng phủ sắc xuống mặt sông Hoài, tạo nên khung cảnh lãng mạn bậc nhất phố Hội. Một vòng thuyền ngắn 15–20 phút là vừa: đủ để hít mùi gỗ ẩm, nghe tiếng chèo chạm nước, nhìn những căn nhà vàng in bóng bên triền sông và dõi theo ánh đèn lồng thả trôi.",
                "Along the Bach Dang bank, lanterns cast their colors onto the Hoai River, creating the most romantic scene in Hoi An. A short 15-20 minute boat loop is just right: enough to breathe in the damp wood, hear the oars touching the water, watch the golden houses reflected on the riverside and follow the floating lanterns drifting by."),
            heading("Thời điểm & nhịp thuyền", "Timing & boat pace"),
            paragraph("Đẹp nhất 18:30–20:00 khi phố vừa lên đèn mà chưa quá đông. Nếu thích yên ả, đi sớm hơn một chút hoặc muộn sau 21:00. Lên thuyền nhớ ngồi cân về giữa, giữ nhịp nhỏ khi di chuyển; trẻ em ngồi trong lòng người lớn.",
                "Best from 18:30-20:00 when the town has just lit up but is not yet too crowded. If you prefer calm, go a little earlier or later after 21:00. When boarding, sit balanced toward the center and move gently; children should sit in an adult's lap."),
            heading("Cách trải nghiệm gợi ý (15–20 phút)", "Suggested experience (15-20 minutes)"),
            paragraph("Xuống thuyền ở bến gần cầu An Hội → xuôi nhẹ qua đoạn phố sáng đèn → dừng ngắn giữa dòng để ngắm phản chiếu → thả một – hai hoa đăng (nếu muốn) rồi vòng về bờ đối diện. Trao đổi trước tuyến đi – thời lượng với người lái để tránh lỡ kế hoạch.",
                "Board at the pier near An Hoi Bridge, drift gently past the lantern-lit stretch, pause briefly midstream to admire the reflections, release one or two floating lanterns (if you wish), then loop back to the opposite bank. Agree on the route and duration with the boatman beforehand to avoid disrupting your plans."),
            imageBlock(img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-11.jpg", "Người dân thân thiện", "Friendly locals", false, 0)),
            heading("Ứng xử & an toàn trên thuyền", "Etiquette & safety on the boat"),
            paragraph("Lên xuống theo hướng dẫn, bước từng người; không nghiêng hẳn về một phía để chụp ảnh. Đặt túi/điện thoại vào túi chống nước hoặc đeo dây tay; không thò tay quá thấp gần mặt nước khi thuyền đang giao nhau. Tôn trọng khoảng cách với thuyền khác, không bật nhạc lớn.",
                "Board and disembark following instructions, one person at a time; do not lean heavily to one side for photos. Put bags and phones in a waterproof pouch or use a wrist strap; do not reach too low near the water when boats are passing each other. Keep a respectful distance from other boats and do not play loud music."),
            heading("Chụp ảnh & mẹo nhỏ", "Photography & tips"),
            paragraph("Tắt flash để giữ màu đèn lồng tự nhiên; tăng ISO vừa đủ, giữ tay chắc hoặc tựa vào mạn thuyền. Góc ngược sáng từ lòng sông lên phố giúp khung hình có chiều sâu; khi thuyền dừng, chụp chéo 30–45° để gom cả dải đèn lồng và bóng nước.",
                "Turn off the flash to keep the natural lantern color; raise the ISO just enough and hold your hands steady or rest them on the gunwale. A backlit angle from the river toward the town gives the frame depth; when the boat stops, shoot at a 30-45 degree diagonal to capture both the row of lanterns and their reflection."),
            heading("Hoa đăng & môi trường", "Floating lanterns & the environment"),
            paragraph("Nếu thả hoa đăng, ưu tiên loại có chất liệu dễ phân huỷ; thả ít nhưng có ý nghĩa. Không xả rác hay vứt bao bì xuống sông; khi lên bờ, mang theo túi nhỏ để gom rác cá nhân.",
                "If you release floating lanterns, choose ones made of biodegradable materials; release few but meaningful ones. Do not litter or throw packaging into the river; when ashore, carry a small bag to collect your own trash."),
            gallery(List.of(
                img("https://eholiday.vn/wp-content/uploads/2023/03/tha-den-hoa-dang-tren-song-Hoai.jpeg", "Thả hoa đăng trên sông", "Releasing floating lanterns on the river", false, 0),
                img("https://static.vinwonders.com/production/Le-hoi-den-long-hoi-an-5.jpg", "Ánh đèn phản chiếu mặt nước", "Lantern light reflected on the water", false, 1),
                img("https://eholiday.vn/wp-content/uploads/2023/02/Song-Hoai-Hoi-An.jpg", "Bến thuyền Sông Hoài", "The Hoai River boat pier", false, 2)
            )),
            quote("“Một chiếc thuyền nhỏ, một vốc ánh sáng – đủ nên thơ cho cả đêm.”", "“A small boat and a handful of light - poetry enough for a whole night.”"),
            divider(),
            mapBlock(new double[]{108.3205, 15.8765})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Sông & Bến", "River & pier")).slug("song-ben").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Thả hoa đăng", "Releasing floating lanterns")).slug("tha-hoa-dang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đèn lồng phố Hội", "Hoi An lanterns")).slug("den-long-pho-hoi").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Lãng mạn", "Romantic")).slug("lang-man").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 4) Biển An Bàng */
  private static PlaceDoc poiBienAnBang() {
    String slug = "bien-an-bang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Biển An Bàng", "An Bang Beach"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Bãi biển yên bình cách phố cổ ~4–5km, cát trắng, quán ven biển đẹp.", "A peaceful beach about 4-5km from the old town, with white sand and lovely seaside cafes."))
        .description(of("Phù hợp tắm biển, ngắm bình minh; dọc biển có nhiều quán hải sản, bar nhỏ chill buổi tối.", "Great for swimming and watching the sunrise; along the shore are many seafood restaurants and small chill-out bars in the evening."))
        .addressLine(of("Đ. Hai Bà Trưng kéo dài, Cẩm An, Hội An", "Hai Ba Trung extended road, Cam An, Hoi An"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3534, 15.9110})
        .vibes(List.of("relaxing", "nature", "peaceful"))
        .atmosphere(of("coastal-open", "coastal-open"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(85)
        .romanticScore(80)
        .localExperienceScore(65)
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhInternet/BienAnBang.jpg", "Bãi biển An Bàng", "An Bang Beach", true, 0),
            img("https://statics.vinpearl.com/Anh%202%20bien%20An%20Bang_1627534981.jpg", "Nắng vàng cát trắng", "Golden sun on white sand", false, 1),
            img("https://statics.vinpearl.com/Anh%201%20bien%20An%20Bang_1627534950.jpg", "Hàng dừa ven biển", "A row of coconut palms by the sea", false, 2),
            img("https://statics.vinpearl.com/tu-a-z-kinh-nghiem-du-lich-bien-an-bang-di-lai-an-o-vui-choi-202107261126582841_1690003128.jpeg", "Quán ven biển", "A seaside cafe", false, 3),
            img("https://statics.vinpearl.com/zyro-image_1690003729.jpeg", "Hoàng hôn", "Sunset", false, 4),
            img("https://statics.vinpearl.com/bi%CC%80nh%20minh_1690003590.jpeg", "Bình minh trên biển An Bàng", "Sunrise over An Bang Beach", false, 5)
        ))
        .content(List.of(
            heading("An Bàng – bình yên trước đại dương", "An Bang - serenity before the ocean"),
            quote("""
                    Gió gỡ mùi muối mỏng,
                    nắng rải hạt lên cát;
                    sóng thở đều bờ vắng,
                    lòng chậm lại – an yên.
                """,
                """
                    The wind unravels a thin scent of salt,
                    sunlight scatters grains over the sand;
                    waves breathe evenly on the empty shore,
                    the heart slows down - at peace.
                """),
            paragraph("Bãi biển An Bàng cách khu phố cổ khoảng 4–5km, bờ cát dài thoai thoải với hàng dừa rợp bóng và nhiều quán ven biển. Sáng sớm là thời khắc trong nhất: nước êm, nắng dịu, người thưa; chiều muộn thì lý tưởng để ngắm hoàng hôn và chill ở các quán bar nhỏ.",
                "An Bang Beach is about 4-5km from the old town, with a long gently sloping shore of shady coconut palms and many seaside cafes. Early morning is the clearest moment: calm water, soft sun and few people; late afternoon is ideal for watching the sunset and chilling at the small bars."),
            heading("Thời điểm & trải nghiệm gợi ý", "Timing & suggested experiences"),
            paragraph("05:30–08:30: tắm biển – đi dạo – chạy bộ; 16:30–18:30: ngắm hoàng hôn, ngồi quán nhìn sóng. Nếu đi cùng trẻ nhỏ, chọn đoạn có cờ/cứu hộ; thủy triều – sóng có thể thay đổi theo ngày, nên quan sát bảng thông báo tại bãi.",
                "05:30-08:30: swim, stroll and jog; 16:30-18:30: watch the sunset and sit at a cafe gazing at the waves. If traveling with small children, choose a stretch with flags and lifeguards; tides and waves can change daily, so check the notice board at the beach."),
            imageBlock(img("https://statics.vinpearl.com/Bo%CC%82%CC%81n%20%C4%90a%20Ba%CC%89ng%20A%CC%89nh%20%C4%90a%CC%81m%20cu%CC%9Bo%CC%9B%CC%81i%20Khung%20Tranh%20in%20va%CC%89i_1690003456.jpg", "Các dịch vụ giải trí tại bãi biển An Bàng", "Recreational services at An Bang Beach", false, 0)),
            heading("Ăn uống & quán ven biển", "Food & seaside cafes"),
            paragraph("Dọc bờ có nhiều quán hải sản và café nhìn thẳng ra biển. Gọi phần vừa phải để thử được nhiều món (mực nướng, nghêu hấp, gỏi cá…), hỏi giá theo kg và cách chế biến trước khi gọi. Buổi tối một số quán bật nhạc nhẹ, phù hợp ngắm gió và trò chuyện.",
                "Along the shore are many seafood restaurants and cafes facing straight out to sea. Order moderate portions to sample many dishes (grilled squid, steamed clams, fish salad...), and ask about the price per kg and the cooking method before ordering. In the evening some places play soft music, perfect for enjoying the breeze and chatting."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Bình minh: đặt đường chân trời ở 1/3 khung để ăn nhiều trời. Hoàng hôn: đứng chéo 30–45° theo hàng dừa để tạo chiều sâu. Khi chụp người ở bờ nước, đợi sóng rút để lấy bọt trắng làm tiền cảnh. Tránh dựng tripod ở lối đi chung.",
                "Sunrise: place the horizon at one third of the frame to capture more sky. Sunset: stand at a 30-45 degree diagonal along the coconut palms to create depth. When photographing people at the waterline, wait for the wave to recede to use the white foam as a foreground. Avoid setting up a tripod in shared walkways."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Tôn trọng biển báo và cờ hiệu cứu hộ; không bơi xa khi sóng lớn hoặc sau bữa ăn no. Thoa kem chống nắng, mang nước uống và túi chống nước cho điện thoại. Giữ vệ sinh chung: không xả rác, nhặt rác nhỏ quanh chỗ ngồi trước khi rời đi.",
                "Respect the signs and lifeguard flags; do not swim far out when the waves are big or after a heavy meal. Apply sunscreen and bring drinking water and a waterproof pouch for your phone. Keep the area clean: do not litter, and pick up small bits of trash around your spot before leaving."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhInternet/BienAnBang.jpg", "Bờ biển An Bàng", "The An Bang shore", false, 0),
                img("https://statics.vinpearl.com/Anh%201%20bien%20An%20Bang_1627534950.jpg", "Hàng dừa ven biển", "A row of coconut palms by the sea", false, 1),
                img("https://statics.vinpearl.com/zyro-image_1690003729.jpeg", "Hoàng hôn trên biển", "Sunset over the sea", false, 2)
            )),
            quote("“Cần một nơi để thở chậm? Ra An Bàng nghe sóng kể chuyện.”", "“Need a place to breathe slowly? Head to An Bang and listen to the waves tell their story.”"),
            divider(),
            mapBlock(new double[]{108.3534, 15.9110})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển", "Beach")).slug("bai-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biển bình yên", "Peaceful beach")).slug("bien-binh-yen").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Ngắm hoàng hôn", "Sunset watching")).slug("ngam-hoang-hon").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 5) Cù Lao Chàm (Cham Islands) */
  private static PlaceDoc poiCuLaoCham() {
    String slug = "cu-lao-cham";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Cù Lao Chàm (Cham Islands)", "Cham Islands (Cu Lao Cham)"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Khu dự trữ sinh quyển thế giới, biển xanh – san hô đẹp, lặn ngắm tuyệt vời.", "A world biosphere reserve with blue sea and beautiful coral, perfect for snorkeling."))
        .description(of("Có nhiều bãi tắm như Bãi Làng, Bãi Chồng; trải nghiệm cano, snorkeling, hải sản tươi.", "There are many beaches such as Bai Lang and Bai Chong; enjoy speedboat rides, snorkeling and fresh seafood."))
        .addressLine(of("Tân Hiệp, Hội An, Quảng Nam", "Tan Hiep, Hoi An, Quang Nam"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.4880, 15.9480})
        .vibes(List.of("nature", "peaceful", "diverse"))
        .atmosphere(of("island-coastal", "island-coastal"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "relax", "photography"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(300)
        .photographyScore(90)
        .romanticScore(80)
        .localExperienceScore(85)
        .images(withCover(
            img("https://hoangphuan.com/wp-content/uploads/2024/07/kinh-nghiem-du-lich-cu-lao-cham-avata.jpg", "Toàn cảnh Cù Lao Chàm", "A panorama of Cu Lao Cham", true, 0),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/imagesbaoquangnamvn-storage-newsportal-2023-2-23-139110-tnb-57047-12.jpg", "Bãi biển nước trong", "A beach with clear water", false, 1),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/Cham-Island-Hoi-An-Hoi-An-Private-Taxi.jpg", "Tàu cano", "A speedboat", false, 2),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/trai-nghiem-lan-bien-ngam-san-ho-o-dao-Cu-Lao-Cham-ivivu-03.jpg", "San hô rực rỡ", "Vibrant coral", false, 3),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/optimizebai-huong-cu-lao-cham-11.jpg", "Làng chài Bình yên", "A peaceful fishing village", false, 4),
            img("https://hoangphuan.com/wp-content/uploads/2024/07/Hai-san-tuoi-song-Cu-Lao-Cham1-e1652015737390.jpg", "Hải sản tươi", "Fresh seafood", false, 5)
        ))
        .content(List.of(
            heading("Cù Lao Chàm – thiên đường biển gần Hội An", "Cu Lao Cham - a sea paradise near Hoi An"),
            quote("""
                    Gió mang hương muối biếc,
                    sóng vẽ nét san hô;
                    nắng rót vàng lên cát,
                    lòng mở rộng như hồ.
                """,
                """
                    The wind carries the scent of azure salt,
                    waves trace the lines of coral;
                    sunlight pours gold onto the sand,
                    the heart opens wide like a lake.
                """),
            paragraph("Cù Lao Chàm là Khu dự trữ sinh quyển thế giới, nổi tiếng nước trong, bãi cát mịn và rạn san hô phong phú. Từ Cửa Đại đi cano khoảng 20–25 phút là tới; khuyến nghị mang kem chống nắng thân thiện rạn san hô, túi chống nước cho điện thoại và một chai nước nhỏ.",
                "Cu Lao Cham is a world biosphere reserve, famous for its clear water, fine sandy beaches and rich coral reefs. From Cua Dai it is about a 20-25 minute speedboat ride; bring reef-safe sunscreen, a waterproof pouch for your phone and a small bottle of water."),
            heading("Thời điểm & điều kiện biển", "Timing & sea conditions"),
            paragraph("Mùa biển êm thường từ khoảng 3–8; ngày gió mạnh/biển động, tour có thể điều chỉnh hoặc huỷ vì an toàn. Buổi sáng 8:00–11:00 nước thường trong hơn; thủy triều lên xuống ảnh hưởng độ sâu điểm lặn – nghe hướng dẫn của HDV để chọn bãi phù hợp.",
                "The calm-sea season is usually around months 3-8; on windy or rough days, tours may be adjusted or cancelled for safety. The water is usually clearer in the morning 8:00-11:00; tides affect the depth of dive spots - follow the guide's advice to choose a suitable beach."),
            heading("Lịch trình gợi ý nửa ngày (4–5 giờ)", "Suggested half-day itinerary (4-5 hours)"),
            paragraph("Xuất phát Cửa Đại → Bãi Làng (tham quan giếng cổ, chợ nhỏ) → cano qua Bãi Chồng/Bãi Xếp để snorkeling (30–45 phút) → tắm biển tự do → ăn trưa hải sản đơn giản → về lại bờ đầu giờ chiều. Nếu đi cả ngày, thêm thời gian dạo Bãi Hương và nghỉ café nhìn vịnh.",
                "Depart from Cua Dai, visit Bai Lang (the ancient well and small market), take a speedboat to Bai Chong/Bai Xep for snorkeling (30-45 minutes), free swimming, a simple seafood lunch, then return to shore in the early afternoon. For a full day, add time to stroll around Bai Huong and relax at a cafe overlooking the bay."),
            imageBlock(img("https://qta.org.vn/wp-content/uploads/2024/05/di-bo-duoi-bien-cu-lao-cham-3.jpg", "Nước trong thấy đáy", "Water so clear you can see the bottom", false, 0)),
            heading("Snorkeling & an toàn trên biển", "Snorkeling & safety at sea"),
            paragraph("Luôn mặc áo phao khi xuống nước; đeo kính – ống thở đúng cỡ, thử trước trên bờ. Không đứng/trèo lên san hô, không chạm tay vào sinh vật biển; giữ khoảng cách với tàu/cano. Nếu không biết bơi, đi kèm HDV, bám phao kéo; tránh ăn quá no trước khi xuống nước.",
                "Always wear a life vest in the water; use a properly sized mask and snorkel and test them on shore first. Do not stand or climb on coral or touch marine life; keep your distance from boats and speedboats. If you cannot swim, stay with the guide and hold the tow float; avoid eating too much before getting in the water."),
            heading("Ăn uống & trải nghiệm làng biển", "Food & seaside village experience"),
            paragraph("Bãi Làng/Bãi Hương có quán bình dân: mực nướng, ốc vú nàng, cá nướng, rau rừng. Hỏi giá theo phần/kg và cách chế biến trước khi gọi. Dạo làng chài, chào hỏi người dân, mua ít đặc sản khô (mực một nắng, bánh ít lá gai) làm quà – xin hoá đơn nếu cần.",
                "Bai Lang/Bai Huong have simple eateries: grilled squid, vu nang sea snails, grilled fish and wild greens. Ask about the price per portion/kg and the cooking method before ordering. Stroll the fishing village, greet the locals and buy a few dried specialties (one-sun-dried squid, banh it la gai) as gifts - request a receipt if needed."),
            heading("Bảo tồn & ứng xử với môi trường", "Conservation & environmental etiquette"),
            paragraph("Dùng kem chống nắng reef-safe, không xả rác; gom rác cá nhân mang về bờ. Không bẻ, nhặt san hô/ốc; hạn chế túi nylon trên thuyền. Chỉ thả neo ở vị trí cho phép – tuân thủ khu vực bảo vệ rạn.",
                "Use reef-safe sunscreen and do not litter; collect your own trash and take it back to shore. Do not break off or collect coral or shells; minimize plastic bags on the boat. Only drop anchor in permitted spots - respect the reef protection zones."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Chụp trên cano: ngồi mũi thuyền lấy đường chân trời 1/3 khung. Dưới nước: áp ống gần mặt nước để bắt dải san hô và tia nắng xiên; giữ máy ổn định, chụp liên tiếp để chọn khung rõ. Trên bờ: đứng chéo 30–45° theo hàng dừa ở Bãi Chồng để tạo chiều sâu.",
                "On the speedboat: sit at the bow and place the horizon at one third of the frame. Underwater: hold the lens close to the surface to capture the coral and slanting sunbeams; keep the camera steady and shoot in bursts to pick a sharp frame. On shore: stand at a 30-45 degree diagonal along the coconut palms at Bai Chong to create depth."),
            gallery(List.of(
                img("https://hoangphuan.com/wp-content/uploads/2024/07/kinh-nghiem-du-lich-cu-lao-cham-avata.jpg", "Toàn cảnh Cù Lao Chàm", "A panorama of Cu Lao Cham", false, 0),
                img("https://hoangphuan.com/wp-content/uploads/2024/07/trai-nghiem-lan-bien-ngam-san-ho-o-dao-Cu-Lao-Cham-ivivu-03.jpg", "Snorkeling ngắm san hô", "Snorkeling to view the coral", false, 1),
                img("https://hoangphuan.com/wp-content/uploads/2024/07/Hai-san-tuoi-song-Cu-Lao-Cham1-e1652015737390.jpg", "Hải sản tươi rói", "Fresh seafood", false, 2)
            )),
            quote("“Nước xanh, cát trắng, san hô sát mắt – thiên nhiên ở khoảng cách một sải tay.”", "“Blue water, white sand, coral right before your eyes - nature within arm's reach.”"),
            divider(),
            mapBlock(new double[]{108.4880, 15.9480})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển & Đảo", "Beach & island")).slug("bai-bien-dao").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Khu dự trữ sinh quyển", "Biosphere reserve")).slug("khu-du-tru-sinh-quyen").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Lặn ngắm san hô", "Coral snorkeling")).slug("lan-ngam-san-ho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.MODERATE)
        .build();
  }

  /** 6) Làng gốm Thanh Hà */
  private static PlaceDoc poiLangGomThanhHa() {
    String slug = "lang-gom-thanh-ha";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Làng gốm Thanh Hà", "Thanh Ha Pottery Village"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Làng nghề truyền thống bên sông Thu Bồn, nổi tiếng gốm đất nung.", "A traditional craft village by the Thu Bon River, famous for terracotta pottery."))
        .description(of("Trải nghiệm nặn gốm bằng tay, tham quan công viên Đất Nung và mua quà thủ công địa phương.", "Try shaping pottery by hand, visit the Terracotta Park and buy local handicrafts."))
        .addressLine(of("Phạm Phán, Thanh Hà, Hội An", "Pham Phan, Thanh Ha, Hoi An"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.2987, 15.8773})
        .vibes(List.of("cultural", "peaceful", "educational"))
        .atmosphere(of("craft-village", "craft-village"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "explore"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(75)
        .romanticScore(55)
        .localExperienceScore(90)
        .images(withCover(
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-5.png", "Làng gốm Thanh Hà", "Thanh Ha Pottery Village", true, 0),
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-4.png", "Nặn gốm thủ công", "Shaping pottery by hand", false, 1),
            img("https://danangbest.com/upload_content/lang-gom-thanh-ha-6.png", "Sản phẩm gốm", "Pottery products", false, 2),
            img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-01.jpg", "Công viên Đất Nung", "The Terracotta Park", false, 3),
            img("https://images.vietnamtourism.gov.vn/vn//images/2024/thang_10/0110.gom_thanh_ha_1.jpg", "Lò nung truyền thống", "A traditional kiln", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-06.jpg", "Không gian làng nghề", "The craft village setting", false, 5)
        ))
        .content(List.of(
            heading("Thanh Hà – giữ lửa nghề gốm", "Thanh Ha - keeping the pottery craft alive"),
            quote("""
                    Đất nâu qua tay ấm,
                    vòng xoay hát nhịp tròn;
                    gió hun mùi rơm rạ,
                    lửa thở hóa linh hồn.
                """,
                """
                    Brown clay through warm hands,
                    the wheel sings a round rhythm;
                    the wind smokes the scent of straw,
                    fire breathes it into a soul.
                """),
            paragraph("Làng gốm Thanh Hà nằm ven sông Thu Bồn, nổi tiếng với dòng gốm đất nung mộc mạc, bền chắc. Không gian làng nghề yên ả, lò nung xen vườn nhà; khách có thể ghé từng hộ gia đình xem nghệ nhân chuốt gốm, thử tay nghề và mua đồ thủ công về làm quà.",
                "Thanh Ha Pottery Village sits by the Thu Bon River, famous for its rustic, durable terracotta. The craft village is tranquil, with kilns nestled among home gardens; visitors can drop by each household to watch artisans throw pottery, try their own hand and buy handicrafts as gifts."),
            heading("Lịch sử & chất liệu", "History & materials"),
            paragraph("Gốm Thanh Hà dùng đất địa phương, pha – lọc – ủ kỹ để đạt độ dẻo, sau đó chuốt trên bàn xoay, phơi nắng rồi vào lò nung. Sắc gốm đỏ – nâu là màu của đất và lửa, không tráng men cầu kỳ nhưng bề mặt có độ ‘thở’, hợp khí hậu miền Trung.",
                "Thanh Ha pottery uses local clay, carefully mixed, filtered and aged for plasticity, then thrown on the wheel, sun-dried and fired in the kiln. The red-brown color comes from the earth and fire; it is not elaborately glazed but the surface “breathes”, suiting the central Vietnam climate."),
            heading("Trải nghiệm nặn gốm", "The pottery-making experience"),
            paragraph("Người lớn và trẻ em đều có thể thử: làm ướt tay, giữ nhịp đều, đỡ thành cho khỏi sụm. Nếu mới làm lần đầu, chọn chén/ly nhỏ để cảm nhận nhịp bàn xoay. Thành phẩm có thể nung – nhận sau hoặc mang về làm kỷ niệm (tuỳ cơ sở).",
                "Both adults and children can try: wet your hands, keep a steady rhythm and support the walls so they do not collapse. If it is your first time, choose a small bowl or cup to feel the rhythm of the wheel. The finished piece can be fired and collected later, or taken home as a keepsake (depending on the workshop)."),
            imageBlock(img("https://images.vietnamtourism.gov.vn/vn//images/2018/images1468333TNB66701.jpg", "Trải nghiệm nặn gốm", "The pottery-making experience", false, 0)),
            heading("Công viên Đất Nung & lò truyền thống", "The Terracotta Park & traditional kilns"),
            paragraph("Ghé Công viên Đất Nung để xem mô hình – sắp đặt từ gốm, chụp ảnh giữa không gian ‘đất – gió – nắng’. Ở khu lò truyền thống, quan sát quy trình xếp gốm – nhóm lửa; giữ khoảng cách an toàn, tránh lại gần khi đang nung.",
                "Visit the Terracotta Park to see pottery models and installations and take photos amid the “earth - wind - sun” setting. In the traditional kiln area, observe the process of stacking the pottery and lighting the fire; keep a safe distance and avoid getting close while it is firing."),
            heading("Mua quà & vận chuyển", "Buying gifts & shipping"),
            paragraph("Chọn món vừa tay (cốc/chậu/đèn gốm), kiểm tra vết nứt lông tơ, gõ nhẹ nghe tiếng ‘đanh’. Xin bọc chống sốc hai lớp, ghi chú ‘fragile’ nếu gửi xe/ship. Tránh món quá mỏng nếu di chuyển xa.",
                "Choose handy items (cups, pots, ceramic lamps), check for hairline cracks and tap lightly to hear a “crisp” ring. Ask for double-layer shockproof wrapping and mark it “fragile” if shipping or sending by coach. Avoid items that are too thin if traveling far."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Trong nhà: đứng chếch 30–45° để bắt nhịp tay nghệ nhân và vòng xoay. Ngoài sân: chụp hàng gốm đang phơi theo đường chéo khung hình cho chiều sâu. Không dùng flash sát mặt người, xin phép trước khi chụp cận.",
                "Indoors: stand at a 30-45 degree angle to capture the artisan's hand movements and the spinning wheel. In the yard: shoot the rows of drying pottery along the frame's diagonal for depth. Do not use the flash close to people's faces, and ask permission before taking close-ups."),
            heading("Ứng xử & bảo tồn", "Etiquette & conservation"),
            paragraph("Đi nhẹ tay với đồ trưng bày, không tự ý chạm sản phẩm chưa khô. Hỗ trợ làng nghề bằng cách mua sản phẩm chính chủ, hạn chế mặc cả quá sâu. Giữ vệ sinh chung, không vứt rác vào khu phơi – khu lò.",
                "Handle the displays gently and do not touch pieces that are not yet dry. Support the village by buying directly from the makers and avoid haggling too hard. Keep the area clean and do not throw trash into the drying or kiln areas."),
            gallery(List.of(
                img("https://danangbest.com/upload_content/lang-gom-thanh-ha-4.png", "Trải nghiệm nặn gốm", "The pottery-making experience", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn//images/2024/thang_10/0110.gom_thanh_ha_1.jpg", "Lò nung truyền thống", "A traditional kiln", false, 1),
                img("https://danangfantasticity.com/wp-content/uploads/2018/08/festival-gom-thanh-ha-hoi-an-2018-06.jpg", "Không gian làng nghề", "The craft village setting", false, 2)
            )),
            quote("“Đất hoá hình, lửa hoá hồn – gốm giữ câu chuyện của bàn tay.”", "“Earth takes shape, fire gives it a soul - pottery keeps the story of the hands.”"),
            divider(),
            mapBlock(new double[]{108.2987, 15.8773})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Làng nghề thủ công", "Handicraft village")).slug("lang-nghe-thu-cong").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Gốm đất nung", "Terracotta pottery")).slug("gom-dat-nung").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Nặn gốm thủ công", "Hand pottery making")).slug("nan-gom-thu-cong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 7) Làng rau Trà Quế */
  private static PlaceDoc poiLangRauTraQue() {
    String slug = "lang-rau-tra-que";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Làng rau Trà Quế", "Tra Que Vegetable Village"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Làng rau sạch nổi tiếng, trải nghiệm làm nông và nấu ăn.", "A famous organic vegetable village offering farming and cooking experiences."))
        .description(of("Tham gia trồng rau, tưới nước bằng gióng tre; lớp học nấu ăn món địa phương như cao lầu, mì Quảng.", "Join in planting vegetables and watering with bamboo shoulder poles; take a cooking class for local dishes such as cao lau and mi Quang."))
        .addressLine(of("Cẩm Hà, Hội An", "Cam Ha, Hoi An"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3493, 15.9148})
        .vibes(List.of("nature", "peaceful", "educational"))
        .atmosphere(of("farm-village", "farm-village"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "food", "explore"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(70)
        .romanticScore(55)
        .localExperienceScore(95)
        .images(withCover(
            img("https://static.vinwonders.com/production/lang-rau-tra-que-0-2-1.jpg", "Cánh đồng rau Trà Quế", "The Tra Que vegetable fields", true, 0),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/rau-tra-que-24-5587.jpg", "Tưới rau bằng gióng", "Watering vegetables with a shoulder pole", false, 1),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/xong-dat-34-6772.jpg", "Trải nghiệm làm nông", "The farming experience", false, 2),
            img("https://hotelroyalhoian.vn/wp-content/uploads/2025/02/tra-que-vegetable-village-5.jpg", "Lớp học nấu ăn", "A cooking class", false, 3),
            img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/z3316610404490-d605b6f4d7386609d70abf0cc2535851-94.jpg", "Không khí trong lành", "Fresh air", false, 4),
            img("https://anabasrestaurant.vn/wp-content/uploads/2024/09/TraQue-vegetable-village-hoian-1.jpg", "Đường làng yên ả", "A peaceful village lane", false, 5)
        ))
        .content(List.of(
            heading("Trà Quế – vị xanh của Hội An", "Tra Que - the green flavor of Hoi An"),
            quote("""
                    Sáng hong mùi đất ấm,
                    gió đọng giọt trên vai;
                    tay tưới qua gióng tre,
                    lá reo thành tiếng cười.
                """,
                """
                    Morning warms the scent of soil,
                    the breeze settles a drop on the shoulder;
                    hands water through a bamboo pole,
                    the leaves rustle into laughter.
                """),
            paragraph("Làng rau Trà Quế nổi tiếng với nguồn rau thơm sạch nhờ đất pha cát và phân bón hữu cơ từ rong nước. Buổi sáng sớm là lúc đẹp nhất: sương còn đọng trên lá, nắng nhẹ, người làng ra đồng tưới tưới – xới xới tạo nên nhịp sống mộc mạc. Đây cũng là nơi lý tưởng để trải nghiệm làm nông và tham gia lớp nấu ăn món địa phương.",
                "Tra Que Vegetable Village is famous for its clean aromatic herbs, thanks to sandy soil and organic fertilizer from water algae. Early morning is the most beautiful time: dew still on the leaves, soft sunlight, and villagers in the fields watering and tilling, creating a rustic rhythm of life. It is also an ideal place to experience farming and join a local cooking class."),
            heading("Trải nghiệm làm nông (60–90 phút)", "The farming experience (60-90 minutes)"),
            paragraph("Mặc áo nhẹ, mang giày thể thao – nón – kem chống nắng. Theo hướng dẫn viên làng: xới đất, lên luống, gieo hạt/đi cấy rau non, tưới bằng đôi gióng tre gánh nước từ giếng làng. Giữ khoảng cách với luống non, bước trên lối giữa để không dập lá.",
                "Wear light clothing and bring sneakers, a hat and sunscreen. Following the village guide: till the soil, form the beds, sow seeds or transplant young greens, and water using a pair of bamboo shoulder poles carrying water from the village well. Keep your distance from the young beds and walk along the middle paths so as not to crush the leaves."),
            imageBlock(img("https://media.thuonghieucongluan.vn/uploads/2019_12_25/1-1577229016.jpg", "Thửa ruộng xanh mướt", "A lush green field", false, 0)),
            heading("Lớp nấu ăn & món địa phương", "Cooking class & local dishes"),
            paragraph("Sau giờ đồng, bạn có thể vào bếp học cuốn ram, trộn cao lầu/mì Quảng với rau thơm Trà Quế. Chú ý rửa tay kỹ sau khi làm vườn, ghi chép tỷ lệ gia vị – nước mắm – rau sống để về nhà làm lại. Nếu ăn chay, báo trước để điều chỉnh nguyên liệu.",
                "After working the fields, you can head to the kitchen to learn how to roll ram (spring rolls) and toss cao lau/mi Quang with Tra Que herbs. Be sure to wash your hands thoroughly after gardening, and note down the ratios of seasoning, fish sauce and fresh herbs so you can recreate them at home. If you are vegetarian, let them know in advance to adjust the ingredients."),
            heading("Ứng xử & an toàn", "Etiquette & safety"),
            paragraph("Không bứt lá ăn thử trên luống; không dùng dép bẩn bước vào khu gieo hạt. Trẻ nhỏ cần đi kèm người lớn khi tưới bằng gióng. Sau trải nghiệm, rửa chân tay sạch để tránh kích ứng da do đất – nước.",
                "Do not pick leaves to taste from the beds and do not step into the sowing area with dirty sandals. Small children must be accompanied by an adult when watering with the poles. After the experience, wash your hands and feet to avoid skin irritation from the soil and water."),
            heading("Mua rau – gia vị mang về", "Buying herbs & seasonings to take home"),
            paragraph("Có thể mua bó rau thơm, hạt giống, mắm/ruốc làm gia vị. Chọn bó tươi, lá đứng; xin bọc giấy/kraft thay nilon; nếu vận chuyển xa, bọc thêm khăn ẩm mỏng để giữ độ tươi.",
                "You can buy bunches of herbs, seeds and fish sauce/shrimp paste as seasonings. Choose fresh bunches with upright leaves; ask for paper/kraft wrapping instead of plastic; if traveling far, add a thin damp cloth to keep them fresh."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Đứng chéo 30–45° theo luống để tạo chiều sâu; chụp thấp ngang tầm lá để thấy bokeh giọt nước. Tránh đứng chắn lối tưới của người dân; xin phép trước khi chụp chân dung.",
                "Stand at a 30-45 degree diagonal along the beds to create depth; shoot low at leaf level to capture the bokeh of water droplets. Avoid blocking the villagers' watering path; ask permission before taking portraits."),
            heading("Bền vững & tôn trọng làng nghề", "Sustainability & respecting the village"),
            paragraph("Hạn chế dùng chai nhựa một lần; không xả rác ra mương tưới. Mua sản phẩm đúng cơ sở làng để ủng hộ sinh kế địa phương; trả giá nhẹ nhàng và tôn trọng công sức người làm vườn.",
                "Limit single-use plastic bottles and do not litter into the irrigation ditches. Buy products directly from the village to support local livelihoods; bargain gently and respect the gardeners' effort."),
            gallery(List.of(
                img("https://static.vinwonders.com/production/lang-rau-tra-que-0-2-1.jpg", "Cánh đồng rau mơn mởn", "Lush vegetable fields", false, 0),
                img("https://thanhnien.mediacdn.vn/Uploaded/cuongnv/2022_04_05/rau-tra-que-24-5587.jpg", "Tưới rau bằng gióng", "Watering vegetables with a shoulder pole", false, 1),
                img("https://hotelroyalhoian.vn/wp-content/uploads/2025/02/tra-que-vegetable-village-5.jpg", "Lớp học nấu ăn tại làng", "A cooking class in the village", false, 2)
            )),
            quote("“Vị xanh của Hội An bắt đầu từ những luống rau này.”", "“The green flavor of Hoi An begins in these vegetable beds.”"),
            divider(),
            mapBlock(new double[]{108.3493, 15.9148})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Làng nông nghiệp", "Farming village")).slug("lang-nong-nghiep").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Rau sạch Trà Quế", "Tra Que organic herbs")).slug("rau-sach-tra-que").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Lớp học nấu ăn", "Cooking class")).slug("lop-hoc-nau-an").type(TagType.SERVICE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 8) Nhà cổ Tấn Ký */
  private static PlaceDoc poiNhaCoTanKy() {
    String slug = "nha-co-tan-ky";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Nhà cổ Tấn Ký", "Tan Ky Ancient House"))
        .slug(slug)
        .parentSlug("hoi-an")
        .ancestors(List.of("hoi-an"))
        .childrenCount(0)
        .shortDescription(of("Ngôi nhà gỗ hơn 200 năm tuổi, kết hợp kiến trúc Hoa – Nhật – Việt.", "A wooden house over 200 years old, blending Chinese, Japanese and Vietnamese architecture."))
        .description(of("Một trong những nhà cổ nổi tiếng nhất ở phố cổ; nội thất gỗ, sân trong và các hoạ tiết tinh xảo.", "One of the most famous ancient houses in the old town; wooden interiors, an inner courtyard and exquisite motifs."))
        .addressLine(of("101 Nguyễn Thái Học, Minh An, Hội An", "101 Nguyen Thai Hoc, Minh An, Hoi An"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3223, 15.8778})
        .vibes(List.of("cultural", "peaceful", "educational"))
        .atmosphere(of("heritage-indoor", "heritage-indoor"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(45)
        .photographyScore(85)
        .romanticScore(70)
        .localExperienceScore(85)
        .images(withCover(
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-1.jpg", "Mặt tiền nhà cổ Tấn Ký", "The facade of Tan Ky Ancient House", true, 0),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-15-1.jpg", "Sân trong nhà cổ", "The inner courtyard of the ancient house", false, 1),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", "Carved wooden columns and trusses", false, 2),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-6.jpg", "Không gian cổ kính", "An antique space", false, 3),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/nha-co-tan-ky-hoi-an.jpg", "Vật dụng cổ", "Antique objects", false, 4),
            img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-13.jpg", "Du khách tham quan", "Visitors touring the house", false, 5)
        ))
        .content(List.of(
            heading("Tấn Ký – dấu ấn giao thoa văn hoá", "Tan Ky - a mark of cultural fusion"),
            quote("""
                    Nhà gỗ thơm mùi nhựa,
                    mái ngói thở rêu phong;
                    bước chậm qua sân sáng,
                    xưa nay hoá chung phòng.
                """,
                """
                    The wooden house fragrant with resin,
                    tiled roof breathing moss;
                    step slowly through the bright courtyard,
                    past and present become one room.
                """),
            paragraph("Nhà cổ Tấn Ký hơn 200 năm tuổi là một trong những ngôi nhà tiêu biểu của phố Hội, kết hợp tinh tế ba dòng ảnh hưởng: kết cấu gỗ Việt, chi tiết trang trí Hoa và bố cục không gian – tỉ lệ gọn kiểu Nhật. Mặt tiền hẹp, chiều sâu lớn, đi qua là chuỗi không gian ‘tiền đường – sân trong – hậu tẩm’ tạo thông gió, lấy sáng tự nhiên giữa khí hậu miền Trung.",
                "Over 200 years old, Tan Ky Ancient House is one of the iconic homes of Hoi An, delicately blending three influences: Vietnamese timber structure, Chinese decorative details and a Japanese-style compact spatial layout. The facade is narrow but the depth is great; passing through, you find a sequence of spaces - front hall, inner courtyard and rear chamber - providing ventilation and natural light in the central Vietnam climate."),
            heading("Kiến trúc & chi tiết nên ngắm kỹ", "Architecture & details worth a close look"),
            paragraph("Quan sát hệ vì kèo chồng rường, các con tiện, hoạ tiết dơi – thọ – phúc chạm khắc trên kèo cột. Sân trong (thiên tĩnh) là ‘lá phổi’ của ngôi nhà: vừa lấy sáng, vừa điều tiết ẩm. Nền gạch bát, gỗ đen bóng do chà dầu nhiều năm; vài vết mòn trên bậc cấp là ‘dấu chân’ của thời gian.",
                "Observe the stacked-beam truss system, the turned balusters and the bat, longevity and fortune motifs carved on the trusses and columns. The inner courtyard (the “skylight”) is the “lungs” of the house: it brings in light and regulates humidity. The terracotta floor and the glossy black wood come from years of oiling; the worn marks on the steps are the “footprints” of time."),
            heading("Cách tham quan hiệu quả (30–45 phút)", "How to visit efficiently (30-45 minutes)"),
            paragraph("Bắt đầu từ tiền đường để nghe giới thiệu lịch sử gia tộc → vào gian chính xem vì kèo – án thờ → dừng ở sân trong để cảm nhận thông gió – ánh sáng → ra hậu tẩm xem vật dụng mưu sinh. Nên mua vé liên hợp các điểm nhà cổ/hội quán để tiết kiệm và đi theo một mạch câu chuyện.",
                "Start at the front hall to hear about the family's history, enter the main hall to see the trusses and the altar, pause in the inner courtyard to feel the ventilation and light, then move to the rear chamber to see the household trade objects. Buy a combined ticket for the ancient houses/assembly halls to save money and follow a continuous story."),
            imageBlock(img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", "Carved wooden columns and trusses", false, 0)),
            heading("Ứng xử & bảo tồn", "Etiquette & conservation"),
            paragraph("Đi nhẹ, nói nhỏ; không sờ vào bề mặt gỗ sơn son thếp vàng; không tựa lưng vào án thờ/vách gỗ. Chụp ảnh tắt flash để tránh làm chói bề mặt sơn và gây khó chịu cho người khác. Tôn trọng lối đi hẹp, nhường đường ở bậc cấp.",
                "Walk softly and speak quietly; do not touch the gilded lacquered woodwork; do not lean against the altar or wooden partitions. Turn off the flash when taking photos to avoid glare on the lacquer and disturbing others. Respect the narrow passages and give way on the steps."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Đứng chéo 30–45° ở gian chính để thấy chiều sâu dãy cột; ở sân trong, hạ thấp máy ngang thắt lưng để bắt ánh nắng đổ qua mái; chụp cận hoạ tiết chạm, lấy nét vào mép chạm nổi. Nếu đông khách, chờ nhịp trống 3–5 giây ở lối chuyển tiếp giữa các gian.",
                "Stand at a 30-45 degree diagonal in the main hall to capture the depth of the column rows; in the inner courtyard, lower the camera to waist level to catch the sunlight pouring through the roof; for close-ups of the carvings, focus on the raised edges. If it is crowded, wait for a 3-5 second gap at the transition between rooms."),
            heading("Mua quà & lưu niệm", "Gifts & souvenirs"),
            paragraph("Một số gian giới thiệu đồ gỗ, tranh khắc, thư pháp. Ưu tiên sản phẩm thủ công nhỏ gọn (con dấu, sổ tay bìa gỗ), xin gói chống ẩm; hạn chế mặc cả quá sâu để ủng hộ gìn giữ di sản sống.",
                "Some rooms display woodwork, engravings and calligraphy. Favor small handmade items (seals, wooden-cover notebooks) and ask for moisture-proof wrapping; avoid haggling too hard to help preserve this living heritage."),
            gallery(List.of(
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-1.jpg", "Mặt tiền nhà cổ", "The facade of the ancient house", false, 0),
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-5-1.jpg", "Cột kèo gỗ chạm trổ", "Carved wooden columns and trusses", false, 1),
                img("https://daivietourist.vn/wp-content/uploads/2025/05/Nha-co-tan-ky-hoi-an-15-1.jpg", "Sân trong thoáng đãng", "The airy inner courtyard", false, 2)
            )),
            quote("“Một ngôi nhà kể ba câu chuyện: Hoa – Nhật – Việt.”", "“One house telling three stories: Chinese, Japanese and Vietnamese.”"),
            divider(),
            mapBlock(new double[]{108.3223, 15.8778})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Nhà cổ", "Ancient house")).slug("nha-co").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Hơn 200 năm tuổi", "Over 200 years old")).slug("hon-200-nam-tuoi").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Kiến trúc Hoa – Nhật – Việt", "Chinese-Japanese-Vietnamese architecture")).slug("kien-truc-hoa-nhat-viet").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }
}