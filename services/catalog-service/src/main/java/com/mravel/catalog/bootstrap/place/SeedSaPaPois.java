package com.mravel.catalog.bootstrap.place;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.TagType;
import com.mravel.catalog.model.doc.PlaceDoc.BestSeason;
import com.mravel.catalog.model.doc.PlaceDoc.BestVisitTime;
import com.mravel.catalog.model.doc.PlaceDoc.CrowdLevel;
import com.mravel.catalog.model.doc.PlaceDoc.NoiseLevel;
import com.mravel.catalog.model.doc.PlaceDoc.VenueContext;
import com.mravel.catalog.repository.PlaceDocRepository;

import static com.mravel.catalog.utils.place.ContentBlocks.*;
import static com.mravel.catalog.utils.place.Images.*;
import static com.mravel.catalog.utils.place.Localized.of;

@Configuration
@Profile("seed")
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
        .name(of("Fansipan – Nóc nhà Đông Dương", "Fansipan – The Roof of Indochina"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Đỉnh cao 3.143m với quần thể tâm linh và đài quan sát mây.", "The 3,143m peak with a spiritual complex and a cloud-watching deck."))
        .description(of("Di chuyển bằng cáp treo, tàu leo núi; check-in cột mốc 3.143m, ngắm biển mây – bình minh ngoạn mục.", "Travel by cable car and mountain train; check in at the 3,143m marker and admire the sea of clouds and a spectacular sunrise."))
        .addressLine(of("Sun World Fansipan Legend, Sa Pa, Lào Cai", "Sun World Fansipan Legend, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.7760, 22.3030})
        .vibes(List.of("adventurous", "spiritual", "awe-inspiring"))
        .atmosphere(of("mountain-summit", "mountain-summit"))
        .travelStyle(List.of("adventure", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("explore", "photography", "culture"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(95)
        .romanticScore(70)
        .localExperienceScore(75)
        .images(withCover(
            img("https://media-cdn-v2.laodong.vn/storage/newsportal/2025/9/3/1568073/Duong-Len-Dinh-Fansi.jpg", "Cột mốc 3.143m", "The 3,143m summit marker", true, 0),
            img("https://cdn.nhandan.vn/images/d4247fbcbdde5a8f0a3e16787e1dd4454ff8e4f24f92f2adad15397f1c9349bd5398c8f078ed4c4cc554429a0933b26e847bfbc2c0e9abf15c290f80b1d1c6f7/f5-9772.jpg", "Biển mây Fansipan", "Sea of clouds at Fansipan", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/cap-treo-fansipan-legend-2.jpg", "Cáp treo Fansipan", "Fansipan cable car", false, 2),
            img("https://cdn.nhandan.vn/images/a66ff20f6c1e5a179e007244f7bfd5c84ff8e4f24f92f2adad15397f1c9349bd1447082c7013e8e6b7357523e21ff1eed99086a63e4d59c6eaccd470b4d0a45ac2381094306107c33e4c998a37d06158/quan-the-tam-linh-9055.jpg", "Quần thể tâm linh", "Spiritual complex", false, 3),
            img("https://umove.com.vn/data/media/503/images/Wanderlust-Tips-man-nhung-dia-diem-ngam-binh-minh-tuyet-dep-2.jpg", "Bình minh trên đỉnh", "Sunrise on the summit", false, 4),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-thang-da.jpg", "Cầu thang đá trong mây", "Stone stairway in the clouds", false, 5)
        ))
        .content(List.of(
            heading("Fansipan – chinh phục nóc nhà Đông Dương", "Fansipan – conquering the roof of Indochina"),
            quote("""
                    Gió cuốn mây qua đỉnh,
                    nắng rót vàng lên vai;
                    một bậc rồi thêm bậc,
                    trời gần hơn mỗi ngày.
                """,
                """
                    Wind sweeps clouds across the peak,
                    sunlight pours gold on the shoulders;
                    one step and then another,
                    the sky draws nearer each day.
                """),
            paragraph("Ở cao độ 3.143m, Fansipan là nơi bạn chạm tay vào biển mây của dãy Hoàng Liên. Quần thể tâm linh, cột mốc đỉnh, các sân vọng cảnh và bậc đá len giữa mây tạo nên hành trình vừa thiêng liêng vừa mãn nhãn. Trên đỉnh gió mạnh, chênh nhiệt 6–12°C so với thị trấn Sa Pa—hãy mặc nhiều lớp, áo gió chống nước, găng/mũ mỏng và mang theo nước ấm.",
                "At an elevation of 3,143m, Fansipan is where you can reach out to the sea of clouds over the Hoang Lien range. The spiritual complex, the summit marker, the viewing decks and the stone steps weaving through the clouds make for a journey that is both sacred and breathtaking. The wind is strong at the top, with temperatures 6–12°C lower than in Sa Pa town—dress in layers, wear a waterproof windbreaker, light gloves and a hat, and bring warm water."),

            heading("Đi lại & nhịp tham quan", "Getting around & visiting pace"),
            paragraph("• Ga dưới Sun World → cáp treo (khoảng 15–20 phút) → ga trên → tàu leo núi/đi bậc đá đoạn cuối lên cột mốc 3.143m.",
                "• Sun World lower station → cable car (about 15–20 minutes) → upper station → mountain train or stone steps for the final stretch up to the 3,143m marker."),
            paragraph("• Dự trù 2–3 giờ cho toàn hành trình (di chuyển + chụp ảnh + nghỉ). Khởi hành buổi sáng để gặp trời quang, gió ổn định hơn.",
                "• Allow 2–3 hours for the whole trip (travel + photos + rest). Set out in the morning for clearer skies and steadier wind."),
            paragraph("• Người dễ say độ cao: ăn nhẹ trước khi đi, ngậm kẹo gừng; hít sâu, bước chậm ở đoạn bậc đá cuối.",
                "• If you are prone to altitude sickness: have a light snack beforehand and suck on ginger candy; breathe deeply and walk slowly on the final stone steps."),

            heading("Mẹo ‘săn mây’ & thời tiết", "Cloud-hunting tips & weather"),
            paragraph("• Sau đợt mưa đêm, sáng sớm thường có biển mây dày; gió đổi hướng có thể ‘xẻ mây’ trong 10–20 phút—kiên nhẫn chờ ở sân vọng cảnh.",
                "• After overnight rain, early mornings often bring a thick sea of clouds; shifting wind can ‘split the clouds’ within 10–20 minutes—wait patiently at the viewing deck."),
            paragraph("• Mùa khô, ngày quang cho tầm nhìn xa (thấy rõ thung lũng Mường Hoa – Ô Quy Hồ). Mùa lạnh có thể gặp sương muối—đi giày bám tốt.",
                "• In the dry season, clear days offer long views (you can clearly see the Muong Hoa valley – O Quy Ho). In the cold season you may encounter frost—wear shoes with good grip."),
            paragraph("• Tránh đứng ở mép đón gió trực diện; nép vào lan can, đợi gió lặng để chụp bầu trời sắc nét.",
                "• Avoid standing at the edge facing the wind head-on; stay close to the railing and wait for the wind to calm before shooting a crisp sky."),

            imageBlock(img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/cap-treo-fansipan-legend-2.jpg", "Cabin lướt giữa tầng mây", "A cabin gliding through the clouds", false, 0)),

            heading("Quần thể tâm linh & ứng xử", "The spiritual complex & etiquette"),
            paragraph("Khi lên khu tượng – chánh điện: trang phục kín đáo, nói nhỏ, nhường lối trên bậc hẹp. Không leo trèo lên bệ/lan can để ‘lấy góc’. Nếu có nghi lễ, hạn chế chụp cận; tắt flash, đứng gọn một bên.",
                "When visiting the statue area and main hall: dress modestly, speak softly, and yield the way on narrow steps. Do not climb onto pedestals or railings to ‘get the shot’. If a ceremony is taking place, avoid close-up photos; turn off the flash and stand neatly to one side."),

            heading("An toàn trên cao", "Safety at altitude"),
            paragraph("• Bậc đá ẩm dễ trơn: bám tay vịn, bước ngắn đều; tránh chạy khi thấy mây ùa đến che khuất tầm nhìn.",
                "• Damp stone steps can be slippery: hold the handrail and take short, even steps; do not run when clouds roll in and block your view."),
            paragraph("• Trẻ nhỏ luôn trong tầm tay, người lớn tuổi nghỉ theo chu kỳ 10–15 phút. Không dùng ô khi gió mạnh—đội mũ, áo có mũ trùm.",
                "• Keep small children within reach and let older travelers rest every 10–15 minutes. Do not use umbrellas in strong wind—wear a hat or a hooded jacket instead."),
            paragraph("• Nếu chóng mặt do gió lạnh: vào khu trú gió (hành lang/sảnh chờ), uống nước ấm, hít thở chậm 2–3 phút.",
                "• If you feel dizzy from the cold wind: move to a sheltered area (a corridor or waiting hall), drink warm water, and breathe slowly for 2–3 minutes."),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• Cột mốc 3.143m: đứng chếch 30° để thấy cả mốc và nền mây; đặt người ở 1/3 khung cho ảnh có chiều sâu.",
                "• The 3,143m marker: stand at a 30° angle to capture both the marker and the cloud backdrop; place the subject on a third of the frame for depth."),
            paragraph("• Biển mây: đo sáng vùng trời, giảm EV 0.3–0.7 để giữ texture mây; đợi mây xẻ để lộ các lớp núi tầng tầng.",
                "• Sea of clouds: meter for the sky and reduce EV by 0.3–0.7 to keep the cloud texture; wait for the clouds to part and reveal the layered ridges."),
            paragraph("• Bậc thang/lan can: chụp từ thấp lên, thêm người trong khung để lấy tỷ lệ; tránh khung giờ đông bằng cách đi sớm.",
                "• Stairs/railings: shoot from low up, add a person in the frame for scale; avoid the crowds by going early."),
            paragraph("• Ăn gian ‘trời xanh’: lúc ‘giờ xanh’ sau hoàng hôn/bình minh, bầu trời mịn—ảnh cityscape núi mây rất ‘ăn màu’.",
                "• ‘Cheat’ a blue sky: during the ‘blue hour’ after sunset or before sunrise, the sky is smooth—mountain-and-cloud scenes are very photogenic."),

            heading("Lịch trình gợi ý 2–3 giờ", "Suggested 2–3 hour itinerary"),
            paragraph("• 0’–20’: ga dưới → cáp treo; tranh thủ chụp dãy Hoàng Liên qua kính (áp ống sát kính giảm phản xạ).",
                "• 0’–20’: lower station → cable car; take the chance to shoot the Hoang Lien range through the glass (press the lens against the glass to reduce reflections)."),
            paragraph("• 20’–40’: ga trên → dạo quần thể tâm linh, làm nóng người trước khi leo bậc cuối.",
                "• 20’–40’: upper station → stroll the spiritual complex and warm up before the final climb."),
            paragraph("• 40’–70’: leo bậc đá lên cột mốc; xếp hàng trật tự, chụp 2–3 kiểu đặc trưng.",
                "• 40’–70’: climb the stone steps to the marker; queue in an orderly way and take 2–3 signature shots."),
            paragraph("• 70’–120’: vòng qua các sân vọng cảnh ‘săn mây’; nghỉ 5’ mỗi điểm, uống nước ấm rồi quay ga về.",
                "• 70’–120’: tour the viewing decks to ‘hunt for clouds’; rest 5’ at each spot, drink warm water, then head back to the station."),

            heading("Kết hợp trải nghiệm quanh Sa Pa", "Combining experiences around Sa Pa"),
            paragraph("Có thể nối cung Ô Quy Hồ (săn mây – đèo), thung lũng Mường Hoa (ruộng bậc thang), hoặc bản Cát Cát chiều muộn để chụp tông ‘giờ vàng’. Hạn chế lịch quá dày trong một ngày có gió lạnh.",
                "You can chain the O Quy Ho route (cloud hunting – the pass), the Muong Hoa valley (terraced fields), or Cat Cat village in the late afternoon for ‘golden hour’ tones. Avoid an overpacked schedule on a cold, windy day."),

            gallery(List.of(
                img("https://media-cdn-v2.laodong.vn/storage/newsportal/2025/9/3/1568073/Duong-Len-Dinh-Fansi.jpg", "Cột mốc 3.143m giữa mây", "The 3,143m marker amid the clouds", false, 0),
                img("https://cdn.nhandan.vn/images/d4247fbcbdde5a8f0a3e16787e1dd4454ff8e4f24f92f2adad15397f1c9349bd5398c8f078ed4c4cc554429a0933b26e847bfbc2c0e9abf15c290f80b1d1c6f7/f5-9772.jpg", "Sóng mây cuộn trên dãy Hoàng Liên", "Waves of clouds rolling over the Hoang Lien range", false, 1),
                img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-thang-da.jpg", "Bậc đá len giữa mây", "Stone steps weaving through the clouds", false, 2)
            )),

            quote("“Một bước lên mây, một tầm nhìn trọn núi.”", "“One step into the clouds, one view of the whole mountain range.”"),
            divider(),
            mapBlock(new double[]{103.7760, 22.3030})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Đỉnh núi & Chinh phục", "Mountain peaks & Conquest")).slug("dinh-nui-chinh-phuc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Biểu tượng vùng miền", "Regional icon")).slug("bieu-tuong-vung-mien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Nóc nhà Đông Dương", "Roof of Indochina")).slug("noc-nha-dong-duong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Biển mây Hoàng Liên", "Hoang Lien sea of clouds")).slug("bien-may-hoang-lien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Cáp treo Fansipan", "Fansipan cable car")).slug("cap-treo-fansipan").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.MODERATE)
        .build();
  }

  /** 2) Bản Cát Cát */
  private static PlaceDoc poiBanCatCat() {
    String slug = "ban-cat-cat";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bản Cát Cát", "Cat Cat Village"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Bản làng Mông với guồng nước, thác nhỏ và nếp nhà gỗ đặc trưng.", "A Hmong village with waterwheels, a small waterfall and distinctive wooden houses."))
        .description(of("Tản bộ con dốc, thuê trang phục dân tộc, chụp ảnh cầu, guồng nước; thử đặc sản thắng cố, thịt xiên nướng.", "Stroll the slopes, rent ethnic costumes, photograph the bridge and waterwheels; try local specialties like thang co and grilled meat skewers."))
        .addressLine(of("San Sả Hồ, Sa Pa, Lào Cai", "San Sa Ho, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.8460, 22.3280})
        .vibes(List.of("cultural", "peaceful", "adventurous"))
        .atmosphere(of("highland-village", "highland-village"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(120)
        .photographyScore(85)
        .romanticScore(75)
        .localExperienceScore(90)
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sa-pa/ban-cat-cat/gia-ve-vao-tham-quan-ban-cat-cat-1.jpg", "Cổng vào bản Cát Cát", "Entrance gate to Cat Cat village", true, 0),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-5.jpg", "Guồng nước bản", "Village waterwheel", false, 1),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-treo-ban-cat-cat.jpg", "Cầu gỗ giữa ruộng", "Wooden bridge amid the fields", false, 2),
            img("https://dulichvietnam.com.vn/kinh-nghiem/wp-content/uploads/2021/12/checkin-ban-cat-cat-1.jpg", "Trang phục dân tộc", "Ethnic costumes", false, 3),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/cat-cat-village/cat-cat-village-thumb.jpg", "Lối dốc quanh co", "Winding sloped path", false, 4),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-1.jpg", "Thác Cát Cát", "Cat Cat waterfall", false, 5)
        ))
       .content(List.of(
            heading("Cát Cát – bản làng giữa thung lũng", "Cat Cat – a village in the valley"),
            quote("""
                    Sương vờn mái gỗ nâu,
                    guồng quay lời suối hát;
                    bước nhỏ qua cầu tre,
                    hoa lan nghiêng bên bậc.
                """,
                """
                    Mist drifts over brown wooden roofs,
                    the wheel turns to the stream's song;
                    small steps across the bamboo bridge,
                    orchids lean beside the stairs.
                """),
            paragraph("Cách trung tâm Sa Pa chỉ vài cây số, Cát Cát lọt thỏm giữa thung lũng Mường Hoa, nổi tiếng với nếp nhà gỗ của người Mông, guồng nước, thác nhỏ và các khung dệt thổ cẩm. Lối đi lát bậc dốc liên tục, hai bên là quầy hàng bản địa rực rỡ sắc vải. Trời ẩm dễ trơn—hãy mang giày đế bám và áo mưa mỏng khi có sương.",
                "Just a few kilometers from central Sa Pa, Cat Cat nestles in the Muong Hoa valley, famous for the Hmong's wooden houses, waterwheels, a small waterfall and brocade looms. The path is a continuous run of sloped steps, lined on both sides by local stalls bright with fabrics. Damp weather can be slippery—wear shoes with good grip and a light raincoat when it's misty."),

            heading("Vài nét văn hoá bản Mông", "A few notes on Hmong village culture"),
            paragraph("Bản Cát Cát là nơi người Mông sinh sống lâu đời, giữ nghề dệt lanh, rèn bạc, đan mây tre. Hoa văn trên vải chàm tượng trưng cho núi, mây, ruộng bậc thang—mỗi hoạ tiết đều có câu chuyện. Khi ghé khu trình diễn, bạn có thể quan sát các công đoạn nhuộm chàm, sáp ong vẽ sáp—rửa sáp để lộ hoạ tiết.",
                "Cat Cat has long been home to the Hmong, who keep their crafts of linen weaving, silver forging and bamboo and rattan weaving. The patterns on indigo cloth represent mountains, clouds and terraced fields—each motif carries a story. At the demonstration area, you can watch the stages of indigo dyeing and beeswax batik—washing off the wax to reveal the pattern."),

            heading("Lộ trình gợi ý 1–2 giờ", "Suggested 1–2 hour route"),
            paragraph("• Cổng vé → dốc lát đá xuống thung lũng (dừng chụp ‘view’ ruộng bậc thang).",
                "• Ticket gate → stone-paved slope down into the valley (stop to shoot the terraced-field ‘view’)."),
            paragraph("• Khu guồng nước → cầu gỗ/ cầu tre → thác Cát Cát (nghỉ chân, chụp ảnh nước đổ).",
                "• Waterwheel area → wooden/bamboo bridge → Cat Cat waterfall (take a break, photograph the falling water)."),
            paragraph("• Khu trình diễn nghề thủ công → leo vòng một chiều trở lại (các quầy thổ cẩm, đồ nướng).",
                "• Handicraft demonstration area → loop back up one way (brocade stalls and grilled food)."),
            imageBlock(img("https://ecotour.com.vn/wp-content/uploads/2025/04/check-in-cau-treo-cat-cat.jpg", "Cầu gỗ check-in", "Wooden check-in bridge", false, 0)),

            heading("Thời điểm & nhịp tham quan", "Timing & visiting pace"),
            paragraph("• Sáng 07:30–10:00: sương mỏng, ánh xiên đẹp, ít đông—dễ chụp cầu, guồng.",
                "• Morning 07:30–10:00: thin mist, lovely slanting light, fewer crowds—easy to shoot the bridge and waterwheel."),
            paragraph("• Chiều 15:30–17:30: ‘giờ vàng’ nhuộm thung lũng; có thể đông hơn—đi sớm chút để giữ nhịp chậm.",
                "• Afternoon 15:30–17:30: ‘golden hour’ bathes the valley; it can be busier—arrive a little early to keep a slow pace."),
            paragraph("• Mùa lúa (tuỳ năm, thường 9–10): ruộng bậc thang vàng, ảnh rất ‘ăn màu’. Mùa sương lạnh cần thêm áo gió mỏng.",
                "• Rice season (depending on the year, usually Sept–Oct): the terraces turn golden and very photogenic. In the cold, misty season bring a light windbreaker."),

            heading("Trải nghiệm & ứng xử", "Experiences & etiquette"),
            paragraph("• Thuê trang phục dân tộc để chụp ảnh: hỏi rõ giá/giờ trả; giữ gìn trang phục, tránh ngồi lên lan can mỏng.",
                "• Renting ethnic costumes for photos: ask clearly about the price and return time; take care of the outfit and avoid sitting on flimsy railings."),
            paragraph("• Tôn trọng đời sống bản địa: xin phép trước khi chụp cận chân dung, đặc biệt là trẻ em; hạn chế đưa tiền trực tiếp cho trẻ—có thể mua món nhỏ từ quầy người lớn để ủng hộ.",
                "• Respect local life: ask permission before close-up portraits, especially of children; avoid giving money directly to kids—buy a small item from an adult's stall to show support instead."),
            paragraph("• Không chen lấn trên cầu; nhường lối tại bậc hẹp; không “bẻ cảnh” (dẫm xuống ruộng bậc thang/hoa dại).",
                "• Don't jostle on the bridge; yield the way on narrow steps; don't ‘ruin the scenery’ (stepping into terraced fields or onto wildflowers)."),

            heading("Ăn uống & đặc sản", "Food & specialties"),
            paragraph("• Đồ nướng bản: thịt xiên, ngô nếp, cơm lam, trứng nướng—ăn nóng rất ấm bụng ngày lạnh.",
                "• Village grills: meat skewers, sticky corn, bamboo-tube rice, grilled eggs—warming when eaten hot on a cold day."),
            paragraph("• Thắng cố: mùi vị đậm, không hợp tất cả khẩu vị—nên thử phần nhỏ trước.",
                "• Thang co: strong-flavored and not to everyone's taste—try a small portion first."),
            paragraph("• Mang chai nước 300–500ml và kẹo gừng cho đoạn dốc dài; nhớ gom rác mang ra khỏi bản.",
                "• Bring a 300–500ml water bottle and ginger candy for the long slope; remember to carry your trash out of the village."),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• Guồng nước: đứng chéo 30–45° để thấy vòng quay + dòng suối tạo đường dẫn vào khung.",
                "• Waterwheel: stand at a 30–45° angle to capture the turning wheel and the stream as a leading line into the frame."),
            paragraph("• Cầu gỗ: chờ lúc vắng, đặt chủ thể ở 1/3 khung để lộ đường cong cầu và ruộng sau lưng.",
                "• Wooden bridge: wait for a quiet moment and place the subject on a third of the frame to reveal the bridge's curve and the fields behind."),
            paragraph("• Thác Cát Cát: muốn ‘mềm nước’ đặt tốc độ 1/10–1/4s; tựa máy vào lan can/bờ đá để chống rung; khép khẩu f/8–f/11.",
                "• Cat Cat waterfall: for ‘silky water’ set a speed of 1/10–1/4s; brace the camera on a railing or rock to avoid shake; stop down to f/8–f/11."),
            paragraph("• Chân dung với nhà gỗ: chọn nền tường gỗ tối—đo sáng vào da, tránh ngược sáng gắt giữa trưa.",
                "• Portraits with wooden houses: choose a dark wooden wall as the backdrop—meter for the skin and avoid harsh backlight at midday."),

            heading("An toàn & môi trường", "Safety & environment"),
            paragraph("Đường dốc ẩm dễ trơn: đi bước ngắn, bám tay vịn; tránh dép trơn. Không cho thú nuôi ăn ngẫu nhiên. Ruộng bậc thang là sinh kế—đừng bước xuống bờ ruộng. Giữ âm lượng nhỏ, không bật loa lớn.",
                "Damp slopes are slippery: take short steps, hold the handrail and avoid smooth sandals. Don't feed the livestock at random. The terraced fields are a livelihood—don't step onto the field banks. Keep your voice down and don't play loud speakers."),

            heading("Lịch trình gợi ý (mẫu)", "Suggested itinerary (sample)"),
            paragraph("• 0’–15’: mua vé, khởi động, chụp view thung lũng đầu tuyến.",
                "• 0’–15’: buy tickets, warm up, shoot the valley view at the start of the route."),
            paragraph("• 15’–45’: guồng nước → cầu gỗ (set chính, chụp đủ góc dọc/ngang).",
                "• 15’–45’: waterwheel → wooden bridge (main set, shoot both vertical and horizontal angles)."),
            paragraph("• 45’–70’: thác Cát Cát (ảnh nước chảy chậm), nghỉ nhẹ.",
                "• 45’–70’: Cat Cat waterfall (slow-water shots), light rest."),
            paragraph("• 70’–100’: khu nghề thủ công, mua một món nhỏ ủng hộ, quay vòng lên dốc về lại cổng.",
                "• 70’–100’: handicraft area, buy a small item in support, then loop back up the slope to the gate."),

            gallery(List.of(
                img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-5.jpg", "Guồng nước bản", "Village waterwheel", false, 0),
                img("https://ecotour.com.vn/wp-content/uploads/2025/04/cau-treo-ban-cat-cat.jpg", "Cầu gỗ qua ruộng", "Wooden bridge over the fields", false, 1),
                img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Ban-Cat-Cat-1.jpg", "Thác Cát Cát", "Cat Cat waterfall", false, 2)
            )),
            quote("“Đi thật chậm để nghe tiếng nước và nếp sống nơi thung lũng.”", "“Walk slowly to hear the water and the rhythm of life in the valley.”"),
            divider(),
            mapBlock(new double[]{103.8460, 22.3280})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bản làng dân tộc", "Ethnic village")).slug("ban-lang-dan-toc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hóa & Thủ công", "Culture & Handicraft")).slug("van-hoa-thu-cong").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Bản Mông Cát Cát", "Cat Cat Hmong village")).slug("ban-mong-cat-cat").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Guồng nước thung lũng", "Valley waterwheel")).slug("guong-nuoc-thung-lung").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 3) Núi Hàm Rồng */
  private static PlaceDoc poiNuiHamRong() {
    String slug = "nui-ham-rong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Núi Hàm Rồng", "Ham Rong Mountain"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Khu du lịch cảnh quan ngay trung tâm thị trấn, view toàn Sa Pa.", "A scenic park right in the town center with views over all of Sa Pa."))
        .description(of("Đường đá bậc dễ đi; vườn lan, sân mây, cổng trời. Ngắm thị trấn và dãy Hoàng Liên Sơn từ trên cao.", "Easy stone steps; orchid gardens, a cloud yard and a heaven's gate. Admire the town and the Hoang Lien Son range from above."))
        .addressLine(of("Đường Hàm Rồng, TT. Sa Pa, Lào Cai", "Ham Rong Street, Sa Pa town, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.8490, 22.3340})
        .vibes(List.of("peaceful", "nature", "awe-inspiring"))
        .atmosphere(of("mountain-garden", "mountain-garden"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "photography", "relax"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(90)
        .photographyScore(85)
        .romanticScore(70)
        .localExperienceScore(70)
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/Sapamay1.jpg", "Sân mây Hàm Rồng", "Ham Rong cloud yard", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhTITC/Anhdanglai/57Donglongquang.jpg", "Lối bậc đá", "Stone-step path", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/vuon-hoa-ham-rong-sapa-4.jpg", "Vườn hoa – vườn lan", "Flower garden – orchid garden", false, 2),
            img("https://www.kynghidongduong.vn/userfiles/images/tour-trong-nuoc/sapa/nui-ham-rong-view-thi-tran-sapa-du-lich-sapa-kynghidongduong-000.jpg", "View thị trấn Sa Pa", "View of Sa Pa town", false, 3),
            img("https://static.vinwonders.com/production/vi-tri-nui-ham-rong.jpg", "Đỉnh đá độc đáo", "Unique rocky peak", false, 4),
            img("https://static.vinwonders.com/production/san-may-nui-ham-rong.jpg", "Mây phủ núi", "Clouds blanketing the mountain", false, 5)
        ))
       .content(List.of(
            heading("Hàm Rồng – vườn cảnh giữa mây", "Ham Rong – a landscaped garden in the clouds"),
            quote("""
                    Sương quàng vai đá xám,
                    gió chải tóc rừng xanh;
                    một lối bậc lên đỉnh,
                    thị trấn nhỏ trong lành.
                """,
                """
                    Mist drapes the gray rocks' shoulders,
                    wind combs the green forest's hair;
                    a stairway leads to the summit,
                    the little town fresh and clear.
                """),
            paragraph("Ngay phía sau nhà thờ đá Sa Pa là lối vào khu du lịch Núi Hàm Rồng – quần thể cảnh quan kết hợp vườn hoa, vườn lan, sân mây và các điểm vọng cảnh nhìn trọn thị trấn cùng dãy Hoàng Liên. Đường bậc đá dễ đi, cây xanh che mát; sáng sớm trời quang là lúc 'săn mây' và đón nắng đẹp nhất.",
                "Right behind Sa Pa's stone church is the entrance to the Ham Rong Mountain park—a landscaped complex combining flower gardens, an orchid garden, a cloud yard and viewpoints overlooking the whole town and the Hoang Lien range. The stone steps are easy to walk and shaded by greenery; clear early mornings are best for ‘cloud hunting’ and catching the loveliest light."),

            heading("Thời điểm & tuyến tham quan", "Timing & tour route"),
            paragraph("• 07:30–10:00: sương mỏng, nắng xiên, ít đông – lý tưởng cho ảnh vườn hoa và view thị trấn.",
                "• 07:30–10:00: thin mist, slanting light, fewer crowds – ideal for flower-garden shots and town views."),
            paragraph("• 15:30–17:30: ‘giờ vàng’ nhuộm ấm các mỏm đá; lưu ý quay xuống sớm khi sương kéo đến.",
                "• 15:30–17:30: ‘golden hour’ warms the rock outcrops; head down early when the mist rolls in."),
            paragraph("• Tuyến chuẩn (1–2 giờ): cổng → vườn hoa → vườn lan → Sân Mây → Cổng Trời → điểm ngắm toàn cảnh Sa Pa (vòng một chiều, nhiều ghế nghỉ).",
                "• Standard route (1–2 hours): gate → flower garden → orchid garden → Cloud Yard → Heaven's Gate → the Sa Pa panorama viewpoint (a one-way loop with plenty of benches)."),

            imageBlock(img("https://images.vietnamtourism.gov.vn/vn//images/2016/anhTITC/Anhdanglai/57Donglongquang.jpg", "Lối bậc đá giữa vườn xanh", "Stone steps amid the green garden", false, 0)),

            heading("Góc ngắm & mẹo ảnh", "Viewpoints & shooting tips"),
            paragraph("• Sân Mây: dùng viền mỏm đá làm tiền cảnh, đặt đường chân trời ở 1/3 khung để bầu trời ‘sâu’ hơn.",
                "• Cloud Yard: use the edge of a rock outcrop as the foreground and place the horizon on a third of the frame to make the sky feel ‘deeper’."),
            paragraph("• Vườn lan – vườn hoa: đứng chếch 30–45° tạo nhiều lớp nền; khép khẩu f/5.6–f/8 để giữ nét cụm hoa gần.",
                "• Orchid and flower gardens: stand at a 30–45° angle to create layered backgrounds; stop down to f/5.6–f/8 to keep the nearby blooms sharp."),
            paragraph("• View thị trấn: chờ khoảnh khắc mây tách; đo sáng vào vùng chân trời để giữ chi tiết mái nhà và thung lũng.",
                "• Town view: wait for the moment the clouds part; meter for the horizon to preserve detail in the rooftops and valley."),
            paragraph("• Mỏm đá đặc trưng: chụp từ thấp lên để khối đá ‘vươn’ hơn; luôn đứng trong vùng an toàn, không vượt lan can.",
                "• Signature rock outcrops: shoot from low up so the rock mass ‘reaches’ taller; always stay within the safe zone and don't cross the railing."),

            heading("Thực vật & địa chất (nhìn nhanh)", "Flora & geology (a quick look)"),
            paragraph("Khu vực là nền đá phong hoá tạo nên các khối ‘hàm rồng’ lạ mắt, xen giữa thảm thực vật á nhiệt đới. Mùa xuân – đầu hè vườn hoa rực rỡ; ngày ẩm dễ xuất hiện rêu/moss phủ đá—đẹp nhưng trơn, nên mang giày đế bám.",
                "The area sits on weathered rock that forms the striking ‘dragon's jaw’ formations amid subtropical vegetation. In spring and early summer the gardens are vivid; on damp days moss tends to cover the rocks—beautiful but slippery, so wear shoes with good grip."),

            heading("Chuẩn bị & lưu ý", "Preparation & notes"),
            paragraph("• Trang phục: áo khoác mỏng chống gió, giày bám tốt cho bậc đá ẩm; đem áo mưa mỏng nếu trời có sương.",
                "• Clothing: a light windbreaker, shoes with good grip for the damp stone steps; bring a thin raincoat if it's misty."),
            paragraph("• Ứng xử: không bẻ hoa, không leo qua rào; nhường lối tại bậc hẹp; giữ yên lặng ở khu nhìn vọng cảnh.",
                "• Etiquette: don't pick the flowers or climb over fences; yield the way on narrow steps; keep quiet at the viewpoints."),
            paragraph("• Thời tiết đổi nhanh: nếu sương dày, chờ 5–10 phút ở điểm trú gió—gió đổi hướng thường ‘xé’ mây mở tầm nhìn.",
                "• Weather changes fast: if the mist is thick, wait 5–10 minutes in a sheltered spot—shifting wind often ‘tears’ the clouds open and reveals the view."),

            heading("Lịch trình gợi ý 60–90 phút", "Suggested 60–90 minute itinerary"),
            paragraph("• 0’–15’: qua vườn hoa – vườn lan, chụp các cụm hoa/đường bậc.",
                "• 0’–15’: through the flower and orchid gardens, shoot the flower clusters and the stairways."),
            paragraph("• 15’–40’: lên Sân Mây, chụp toàn cảnh – thử khung dọc/ngang.",
                "• 15’–40’: up to the Cloud Yard, shoot the panorama – try both vertical and horizontal framing."),
            paragraph("• 40’–70’: tiếp Cổng Trời – điểm nhìn đỉnh; nghỉ 5’ uống nước.",
                "• 40’–70’: continue to Heaven's Gate – the summit viewpoint; rest 5’ and drink water."),
            paragraph("• 70’–90’: quay tuyến vòng xuống, dừng thêm 1–2 điểm mỏm đá an toàn để chụp ‘giờ vàng’ (nếu đi buổi chiều).",
                "• 70’–90’: take the loop back down, stopping at 1–2 safe rock outcrops for ‘golden hour’ shots (if visiting in the afternoon)."),

            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/Sapamay1.jpg", "Sân mây Hàm Rồng", "Ham Rong cloud yard", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2019/12/vuon-hoa-ham-rong-sapa-4.jpg", "Vườn hoa – vườn lan", "Flower garden – orchid garden", false, 1),
                img("https://www.kynghidongduong.vn/userfiles/images/tour-trong-nuoc/sapa/nui-ham-rong-view-thi-tran-sapa-du-lich-sapa-kynghidongduong-000.jpg", "View toàn thị trấn Sa Pa", "Panoramic view of Sa Pa town", false, 2)
            )),

            quote("“Ở Hàm Rồng, thị trấn như lọt thỏm giữa đại ngàn.”", "“At Ham Rong, the town seems to nestle in the heart of the great forest.”"),
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
        .name(of("Bản Tả Phìn", "Ta Phin Village"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Bản Dao đỏ – H’Mông với nghề thổ cẩm, tắm lá thuốc nổi tiếng.", "A Red Dao – Hmong village famous for brocade crafts and herbal leaf baths."))
        .description(of("Trải nghiệm nhuộm vải, thêu thổ cẩm, tắm lá Dao đỏ; ghé tu viện cổ Tả Phìn chụp ảnh gạch đá rêu phong.", "Experience cloth dyeing, brocade embroidery and a Red Dao herbal bath; visit the ancient Ta Phin monastery to photograph its moss-covered brick and stone."))
        .addressLine(of("Tả Phìn, Sa Pa, Lào Cai", "Ta Phin, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.9000, 22.3790})
        .images(withCover(
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/ban-ta-phin-sapa.jpg", "Khung cảnh bản Tả Phìn", "Scenery of Ta Phin village", true, 0),
            img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2015_01_16/taphin.jpg", "Người Dao đỏ", "The Red Dao people", false, 1),
            img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc-8.jpg", "Thổ cẩm Tả Phìn", "Ta Phin brocade", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn", "Ancient Ta Phin monastery", false, 3),
            img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc.jpg", "Đồi ruộng bậc thang", "Terraced-field hills", false, 4),
            img("https://ecotour.com.vn/wp-content/uploads/2025/04/du-lich-ban-ta-phin-sapa.jpg", "Con đường làng", "The village road", false, 5)
        ))
        .content(List.of(
            heading("Tả Phìn – sắc đỏ nơi sườn núi", "Ta Phin – a touch of red on the mountainside"),
            quote("""
                    Khăn đỏ nghiêng bên suối,
                    khói bếp gọi chiều về;
                    mũi chỉ chầm chậm múa,
                    hương lá ấm chân đê.
                """,
                """
                    Red headscarves lean by the stream,
                    kitchen smoke calls the evening home;
                    the needle slowly dances,
                    the herbs' scent warms the dike.
                """),
            paragraph("Nằm chếch về phía Đông Bắc thị trấn Sa Pa, Tả Phìn là nơi đồng bào Dao đỏ và H’Mông sinh sống, nổi tiếng với thổ cẩm và tắm lá thuốc. Con đường làng uốn quanh ruộng bậc thang dẫn tới tu viện cổ phủ rêu, nơi gạch đá kể những lớp thời gian. Không gian yên bình, thích hợp một buổi tham quan chậm rãi để cảm nhận nhịp sống miền sơn cước.",
                "Lying to the northeast of Sa Pa town, Ta Phin is home to the Red Dao and Hmong people, famous for brocade and herbal leaf baths. The village road winds around terraced fields to a moss-covered ancient monastery, where the brick and stone tell of layered time. The setting is peaceful, well suited to a slow visit to feel the rhythm of mountain life."),

            heading("Thời điểm & nhịp tham quan", "Timing & visiting pace"),
            paragraph("• Sáng 08:00–10:30: sương tan, nắng xiên nhẹ—đi làng, xem dệt, chụp ruộng bậc thang.",
                "• Morning 08:00–10:30: the mist clears and the light slants gently—walk the village, watch the weaving, shoot the terraced fields."),
            paragraph("• Chiều 15:30–17:30: ‘giờ vàng’ nhuộm đỏ khăn vấn – mái gỗ; thuận ghé tu viện cổ và tắm lá thuốc.",
                "• Afternoon 15:30–17:30: ‘golden hour’ deepens the red headscarves and wooden roofs; a good time to visit the ancient monastery and take an herbal bath."),
            paragraph("• Mùa lúa (tuỳ năm, thường 9–10): bậc thang óng vàng rất ‘ăn ảnh’. Mùa lạnh cần áo gió/áo mưa mỏng vì sương ẩm.",
                "• Rice season (depending on the year, usually Sept–Oct): the golden terraces are very photogenic. In the cold season bring a windbreaker or light raincoat because of the damp mist."),

            heading("Trải nghiệm văn hoá & nghề", "Cultural & craft experiences"),
            paragraph("• Thổ cẩm Dao đỏ: xem nhuộm chàm, vẽ sáp ong, thêu tay; thử vài mũi thêu cơ bản dưới hướng dẫn của các bà, các chị.",
                "• Red Dao brocade: watch indigo dyeing, beeswax batik and hand embroidery; try a few basic stitches under the guidance of the local women."),
            paragraph("• Rèn bạc/H’Mông: quan sát chạm khắc họa tiết; hỏi trước khi chạm vào đồ nghề.",
                "• Hmong silver forging: watch the engraving of motifs; ask before touching the tools."),
            paragraph("• Ủng hộ bền vững: hỏi giá rõ ràng, mua món nhỏ ‘thật sự dùng được’ thay vì trả giá quá sâu.",
                "• Support sustainably: ask prices clearly and buy a small, genuinely useful item rather than bargaining too hard."),

            heading("Tắm lá Dao đỏ", "Red Dao herbal bath"),
            paragraph("Bài thuốc gồm nhiều loại lá rừng phơi khô, nấu thành nước ấm. Chọn cơ sở uy tín, yêu cầu nhiệt độ vừa phải. Người huyết áp thấp/người mới thử lần đầu nên ngâm 10–15 phút, nghỉ giữa chừng nếu chóng mặt; uống nước ấm trước và sau khi tắm.",
                "The remedy uses many kinds of dried forest leaves boiled into warm water. Choose a reputable place and ask for a moderate temperature. People with low blood pressure or first-timers should soak for 10–15 minutes, taking a break if dizzy; drink warm water before and after the bath."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn phủ rêu", "The moss-covered ancient Ta Phin monastery", false, 0)),

            heading("Tu viện cổ Tả Phìn", "The ancient Ta Phin monastery"),
            paragraph("Di tích bằng đá gạch rêu phong giữa đồi, khung cửa vòm và bức tường vỡ tạo texture cổ điển. Nền gạch có chỗ bong tróc—đi nhẹ, không leo lên đỉnh tường. Giữ khoảng cách an toàn khi chụp ảnh vào ngày ẩm trơn.",
                "A moss-covered brick-and-stone relic on the hill, its arched doorways and broken walls create a classic texture. The brick floor is flaking in places—tread lightly and don't climb to the top of the walls. Keep a safe distance when shooting on damp, slippery days."),

            heading("Ứng xử & chụp ảnh", "Etiquette & photography"),
            paragraph("• Xin phép trước khi chụp chân dung, đặc biệt là người lớn tuổi và trẻ nhỏ; hạn chế đưa tiền trực tiếp cho trẻ em.",
                "• Ask permission before taking portraits, especially of the elderly and children; avoid giving money directly to kids."),
            paragraph("• Không đứng lên bờ ruộng bậc thang; luôn chừa lối cho người bản địa đi lại.",
                "• Don't stand on the terraced-field banks; always leave room for locals to pass."),
            paragraph("• Trang phục thuê: thử vừa cỡ, giữ gìn, trả đúng giờ; không mặc đè lên đồ đang trưng bày nếu chưa được phép.",
                "• Rented costumes: try the right size, take care of them and return them on time; don't put them on over items on display without permission."),

            heading("Góc chụp gợi ý & mẹo ảnh", "Suggested photo spots & tips"),
            paragraph("• Con đường làng: đứng chếch 30° để thấy nhịp uốn theo ruộng; đặt người ở 1/3 khung để tạo chiều sâu.",
                "• The village road: stand at a 30° angle to catch its curves following the fields; place a person on a third of the frame for depth."),
            paragraph("• Thổ cẩm: lấy tay thợ làm tiền cảnh, hậu cảnh là dải khăn/áo treo; khép khẩu f/5.6–f/8 giữ nét họa tiết.",
                "• Brocade: use the artisan's hands as the foreground with hanging scarves and garments behind; stop down to f/5.6–f/8 to keep the patterns sharp."),
            paragraph("• Tu viện cổ: canh nắng xiên chiều để nổi vân rêu; tránh leo cao—dùng góc thấp để khối tường ‘vươn’ hơn.",
                "• The ancient monastery: time it for the afternoon's slanting light to bring out the moss texture; avoid climbing high—use a low angle so the walls ‘rise’ taller."),
            paragraph("• Chân dung khăn đỏ: nền gỗ tối/tường đá xám giúp tôn màu khăn; đo sáng vào da để giữ sắc đỏ trung thực.",
                "• Red-headscarf portraits: a dark wooden or gray stone backdrop sets off the scarf's color; meter for the skin to keep the red true."),

            heading("Ăn uống & đặc sản", "Food & specialties"),
            paragraph("Thử thắng cố (mùi vị đậm—nên dùng phần nhỏ), thịt lợn cắp nách nướng, cơm lam, rượu thóc ấm người. Mang thêm chai nước 300–500ml cho quãng đi bộ; giữ rác trong túi riêng mang ra khỏi bản.",
                "Try thang co (strongly flavored—have a small portion), grilled ‘armpit’ pork, bamboo-tube rice and warming rice wine. Bring a 300–500ml water bottle for the walk; keep trash in a separate bag and carry it out of the village."),

            heading("An toàn & lưu ý nhỏ", "Safety & small notes"),
            paragraph("Đường làng có đoạn dốc – ẩm: giày đế bám, bước ngắn; trời mưa tránh dép trơn. Tôn trọng không gian thờ tự/nhà riêng; không bật loa lớn. Nếu đi xe máy, gửi ở điểm cho phép và khóa kỹ.",
                "The village road has steep, damp sections: wear shoes with good grip and take short steps; avoid smooth sandals in the rain. Respect places of worship and private homes; don't play loud speakers. If riding a motorbike, park in a permitted spot and lock it carefully."),

            heading("Lịch trình gợi ý 2–3 giờ", "Suggested 2–3 hour itinerary"),
            paragraph("• 0’–20’: vào bản, chụp cảnh đường làng – ruộng bậc thang.",
                "• 0’–20’: enter the village, shoot the village road and terraced fields."),
            paragraph("• 20’–70’: xem dệt, thêu; trải nghiệm vài mũi thêu, mua món nhỏ ủng hộ.",
                "• 20’–70’: watch the weaving and embroidery; try a few stitches and buy a small item in support."),
            paragraph("• 70’–110’: qua tu viện cổ chụp ảnh rêu phong – cửa vòm.",
                "• 70’–110’: visit the ancient monastery to photograph the moss and the arched doorways."),
            paragraph("• 110’–150’: tắm lá Dao đỏ, nghỉ ấm người, trở về thị trấn.",
                "• 110’–150’: take a Red Dao herbal bath, rest and warm up, then return to town."),

            gallery(List.of(
                img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2015_01_16/taphin.jpg", "Người Dao đỏ Tả Phìn", "The Red Dao of Ta Phin", false, 0),
                img("https://eholiday.vn/wp-content/uploads/2021/07/ban-ta-phin-ban-lang-hoang-so-ma-dam-da-ban-sac-dan-toc-8.jpg", "Thổ cẩm thủ công", "Handmade brocade", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/tu-vien-co-ta-phin-sapa-diem-den-dep-ma-mi-day-huyen-bi-4-1621333777.jpg", "Tu viện cổ Tả Phìn", "Ancient Ta Phin monastery", false, 2)
            )),

            quote("“Sắc đỏ khăn vấn, hương lá thuốc – ký ức khó quên của một bản làng.”", "“The red of the headscarves, the scent of herbal leaves – an unforgettable memory of a village.”"),
            divider(),
            mapBlock(new double[]{103.9000, 22.3790})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bản làng dân tộc", "Ethnic village")).slug("ban-lang-dan-toc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hóa & Thủ công", "Culture & Handicraft")).slug("van-hoa-thu-cong").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Người Dao đỏ Tả Phìn", "Ta Phin Red Dao people")).slug("nguoi-dao-do-ta-phin").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tắm lá thuốc", "Herbal leaf bath")).slug("tam-la-thuoc").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tu viện cổ rêu phong", "Moss-covered ancient monastery")).slug("tu-vien-co-reu-phong").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 5) Đèo Ô Quy Hồ – Cổng Trời */
  private static PlaceDoc poiDeoOQuyHo() {
    String slug = "deo-o-quy-ho-cong-troi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Đèo Ô Quy Hồ – Cổng Trời", "O Quy Ho Pass – Heaven's Gate"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Một trong tứ đại đỉnh đèo, cảnh mây trời hùng vĩ.", "One of the four great mountain passes, with majestic sky-and-cloud scenery."))
        .description(of("Cung đường uốn lượn qua dãy Hoàng Liên; săn mây, ngắm núi Fansipan từ các điểm dừng chân an toàn.", "A winding road across the Hoang Lien range; hunt for clouds and view Fansipan from safe rest stops."))
        .addressLine(of("QL4D, Ô Quy Hồ, Sa Pa, Lào Cai", "National Highway 4D, O Quy Ho, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.7780, 22.3650})
        .vibes(List.of("adventurous", "awe-inspiring", "nature"))
        .atmosphere(of("mountain-pass", "mountain-pass"))
        .travelStyle(List.of("adventure", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("explore", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(150)
        .photographyScore(95)
        .romanticScore(80)
        .localExperienceScore(70)
        .images(withCover(
            img("https://static.vinwonders.com/production/deo-o-quy-ho-topbanner.jpg", "Đèo Ô Quy Hồ nhìn từ trên cao", "O Quy Ho Pass seen from above", true, 0),
            img("https://static.vinwonders.com/production/ten-goi-deo-o-quy-ho.jpg", "Làn mây qua đèo", "Clouds drifting over the pass", false, 1),
            img("https://static.vinwonders.com/production/phuot-deo-o-quy-ho.jpg", "Khúc cua tay áo", "A hairpin bend", false, 2),
            img("https://static.vinwonders.com/production/khu-du-lich-cong-troi-o-quy-ho.jpg", "Điểm dừng ngắm cảnh", "Scenic rest stop", false, 3),
            img("https://static.vinwonders.com/production/doi-che-o-quy-ho.jpg", "Núi rừng trùng điệp", "Endless mountains and forests", false, 4),
            img("https://static.vinwonders.com/production/checkin-cay-co-don-deo-o-quy-ho.jpg", "Hoàng hôn trên đèo", "Sunset over the pass", false, 5)
        ))
       .content(List.of(
            heading("Ô Quy Hồ – cung đường trong mây", "O Quy Ho – a road in the clouds"),
            quote("""
                    Đường quấn lưng núi biếc,
                    mây rót xuống vai đèo;
                    gió khía thành vệt trắng,
                    chân trời gần hơn nhiều.
                """,
                """
                    The road wraps the blue mountain's back,
                    clouds pour onto the pass's shoulders;
                    wind carves them into white streaks,
                    the horizon draws much nearer.
                """),
            paragraph("Ô Quy Hồ là một trong ‘tứ đại đỉnh đèo’ miền Bắc, vắt qua sườn Hoàng Liên Sơn nối Lào Cai với Lai Châu. Cung đường uốn lượn liên tiếp, mở ra lớp lớp sườn núi, biển mây và – những ngày quang – cả bóng Fansipan mờ xa. Thời tiết đổi rất nhanh: một khúc nắng, một khúc sương—hãy chủ động canh giờ và điểm dừng an toàn.",
                "O Quy Ho is one of the North's ‘four great passes’, draped across the Hoang Lien Son slopes linking Lao Cai with Lai Chau. The road winds on and on, opening onto layer upon layer of ridges, a sea of clouds and—on clear days—even the distant silhouette of Fansipan. The weather changes very fast: sunny on one stretch, misty on the next—plan your timing and safe stops in advance."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• Sáng muộn 09:00–11:00: mây bồng bềnh, gió vừa; ảnh rõ từng lớp núi ‘xếp tầng’.",
                "• Late morning 09:00–11:00: drifting clouds, moderate wind; photos clearly show the ‘stacked’ layers of mountains."),
            paragraph("• Xế chiều 15:30–17:30: ‘giờ vàng’ nhuộm ấm triền núi; lý tưởng săn hoàng hôn (chủ động giờ quay về trước khi sương xuống).",
                "• Late afternoon 15:30–17:30: ‘golden hour’ warms the mountainsides; ideal for chasing the sunset (plan your return before the mist falls)."),
            paragraph("• Mùa khô (thường 10–4): tầm nhìn xa, ít mưa; mùa mưa (5–9): mây – sương dày, cảnh huyền ảo nhưng đường dễ trơn—chạy cẩn thận.",
                "• Dry season (usually Oct–Apr): long views, little rain; rainy season (May–Sept): thick clouds and mist, magical scenery but slippery roads—ride carefully."),

            heading("Lộ trình gợi ý từ Sa Pa", "Suggested route from Sa Pa"),
            paragraph("• Thị trấn Sa Pa → QL4D hướng Ô Quy Hồ → dừng ở khu ‘Cổng Trời’ (điểm ngắm có bãi đỗ) → tiếp tục men các điểm vọng cảnh có lan can/hộ lan. Tránh dừng ngay khúc cua tay áo, chỉ đỗ nơi có khoảng trống và tầm nhìn thoáng.",
                "• Sa Pa town → National Highway 4D toward O Quy Ho → stop at the ‘Heaven's Gate’ area (a viewpoint with parking) → continue along the viewpoints that have railings/guardrails. Avoid stopping right on the hairpin bends; park only where there is open space and clear visibility."),
            imageBlock(img("https://static.vinwonders.com/production/khu-du-lich-cong-troi-o-quy-ho.jpg", "Cổng Trời – điểm dừng ngắm cảnh có lan can", "Heaven's Gate – a railed scenic rest stop", false, 0)),

            heading("Các điểm dừng tham khảo (an toàn trước hết)", "Reference stops (safety first)"),
            paragraph("• Cổng Trời Ô Quy Hồ: view ‘đường viền’ sát mây, nhìn về dải núi Hoàng Liên.",
                "• O Quy Ho Heaven's Gate: an ‘outline’ view close to the clouds, looking toward the Hoang Lien range."),
            paragraph("• Một số bãi nhìn về Lai Châu: ít xe hơn, thấy thung lũng xa, hợp chụp pano.",
                "• Some pullouts facing Lai Chau: less traffic, distant valley views, good for panoramas."),
            paragraph("• Khúc cua chữ S (chỉ dừng khi có ‘bờm’ rộng và tín hiệu an toàn): khung đường ôm sườn núi rất ‘ăn ảnh’.",
                "• The S-shaped bends (stop only where there is a wide shoulder and it is safe): the road hugging the mountainside is very photogenic."),

            heading("An toàn khi đổ đèo", "Safety on the descent"),
            paragraph("• Trước khi đi: kiểm tra phanh, lốp, đèn; mặc áo phản quang/áo tối màu có viền phản quang.",
                "• Before setting off: check the brakes, tires and lights; wear a reflective vest or a dark jacket with reflective trim."),
            paragraph("• Khi xuống dốc: về số thấp, phanh động cơ; phanh nhịp ngắn—không rà phanh liên tục để tránh nóng má.",
                "• On the descent: use a low gear and engine braking; brake in short pulses—don't ride the brakes continuously to avoid overheating the pads."),
            paragraph("• Trời mưa/sương: bật đèn chiếu gần + đèn sương mù (nếu có); giữ khoảng cách, tốc độ ổn định; tránh quay đầu gấp.",
                "• In rain or mist: turn on the low beam plus fog lights (if available); keep your distance and a steady speed; avoid sharp U-turns."),
            paragraph("• Dừng – đỗ: luôn chọn điểm có bờ chắn/lan can, đỗ gọn trong lề; quan sát xe hai chiều trước khi bước qua hộ lan.",
                "• Stopping and parking: always choose a spot with a barrier or railing, park neatly on the verge; check traffic in both directions before stepping past the guardrail."),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• Toàn cảnh đèo: đặt đường hộ lan/triền cỏ làm tiền cảnh tạo chiều sâu; đường chân trời ở 1/3 khung.",
                "• Pass panorama: use the guardrail line or grassy slope as a foreground for depth; put the horizon on a third of the frame."),
            paragraph("• Biển mây: đo sáng vào bầu trời, bù phơi sáng −0.3 đến −1 EV để giữ texture mây; chờ gió ‘xé mây’ lộ lớp núi.",
                "• Sea of clouds: meter for the sky and apply −0.3 to −1 EV of compensation to keep the cloud texture; wait for the wind to ‘tear the clouds’ and reveal the ridges."),
            paragraph("• Hoàng hôn: đứng chếch 30–45° so với mặt trời để bắt tia xiên; đợi khoảnh khắc mây lướt qua rìa núi.",
                "• Sunset: stand at a 30–45° angle to the sun to catch the slanting rays; wait for the moment clouds skim the mountain's edge."),
            paragraph("• Pano dãy Hoàng Liên: chụp liên tiếp 5–7 khung chồng 30%—ghép sau sẽ có ảnh độ phân giải cao.",
                "• Hoang Lien panorama: shoot 5–7 frames in a row with 30% overlap—stitched later, they make a high-resolution image."),

            heading("Chuẩn bị cá nhân", "Personal preparation"),
            paragraph("Áo gió/áo mưa mỏng, găng tay, khăn ống (gió lạnh đổi nhanh). Nước ấm/đồ uống nóng nhỏ; khi lạnh tay, nghỉ 3–5 phút ở điểm trú gió rồi hẵng chạy tiếp. Giày đế bám nếu leo lên mỏm đá ‘check-in’—chỉ vào vùng đã rào an toàn.",
                "A windbreaker or light raincoat, gloves and a neck gaiter (the cold wind shifts quickly). Warm water or a small hot drink; when your hands get cold, rest 3–5 minutes in a sheltered spot before riding on. Wear shoes with good grip if climbing onto a rock outcrop to ‘check in’—and only enter the fenced safe areas."),

            heading("Lịch trình gợi ý 90–150 phút", "Suggested 90–150 minute itinerary"),
            paragraph("• 0’–25’: rời Sa Pa theo QL4D, quan sát đường – làm quen nhịp cua.",
                "• 0’–25’: leave Sa Pa on National Highway 4D, watch the road and get used to the rhythm of the bends."),
            paragraph("• 25’–60’: dừng Cổng Trời → chụp toàn cảnh, pano lớp núi.",
                "• 25’–60’: stop at Heaven's Gate → shoot the panorama and the layered ridges."),
            paragraph("• 60’–90’: di chuyển thêm 1–2 điểm vọng cảnh có bãi đỗ—chụp ‘đường ôm núi’.",
                "• 60’–90’: move on to 1–2 more viewpoints with parking—shoot the ‘road hugging the mountain’."),
            paragraph("• 90’–150’: nếu săn hoàng hôn, canh ánh vàng → quay đầu sớm khi sương kéo; chạy chậm, đèn đủ.",
                "• 90’–150’: if chasing the sunset, watch for the golden light → turn back early when the mist comes in; ride slowly with full lights."),

            heading("Ứng xử & môi trường", "Etiquette & environment"),
            paragraph("Không bày biện rác/đốt lửa ở điểm nhìn; không vượt lan can ‘lấy góc mạo hiểm’. Giữ âm lượng nhỏ, nhường lối cho xe đường dài; tôn trọng người bán hàng địa phương—mua nước/đồ ăn nhẹ coi như lời cảm ơn.",
                "Don't leave trash or light fires at the viewpoints; don't cross the railing for a ‘risky angle’. Keep your voice down and yield to long-distance vehicles; respect the local vendors—buying water or a snack is a nice way to say thanks."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/deo-o-quy-ho-topbanner.jpg", "Đèo nhìn từ trên cao", "The pass seen from above", false, 0),
                img("https://static.vinwonders.com/production/ten-goi-deo-o-quy-ho.jpg", "Mây vờn qua đỉnh đèo", "Clouds drifting over the pass's crest", false, 1),
                img("https://static.vinwonders.com/production/checkin-cay-co-don-deo-o-quy-ho.jpg", "Khoảnh khắc hoàng hôn", "A sunset moment", false, 2)
            )),

            quote("“Ô Quy Hồ – nơi con đường trở thành một đường viền của bầu trời.”", "“O Quy Ho – where the road becomes an outline of the sky.”"),
            divider(),
            mapBlock(new double[]{103.7780, 22.3650})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Đèo núi & Cung đường", "Mountain passes & Roads")).slug("deo-nui-cung-duong").build(),
            PlaceDoc.CategoryMini.builder().name(of("Cảnh quan thiên nhiên", "Natural landscapes")).slug("canh-quan-thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Tứ đại đỉnh đèo", "Four great passes")).slug("tu-dai-dinh-deo").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Biển mây Hoàng Liên", "Hoang Lien sea of clouds")).slug("bien-may-hoang-lien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Cổng Trời Ô Quy Hồ", "O Quy Ho Heaven's Gate")).slug("cong-troi-o-quy-ho").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 6) Thác Bạc */
  private static PlaceDoc poiThacBac() {
    String slug = "thac-bac";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Thác Bạc", "Silver Waterfall"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Thác nước lớn, tung bọt trắng xoá bên QL4D, đường đi thuận tiện.", "A large waterfall throwing up white spray beside National Highway 4D, with easy access."))
        .description(of("Bậc thang gỗ – cầu nhỏ dẫn lên các tầng thác; kết hợp tham quan hợp lý với Đèo Ô Quy Hồ và Thác Tình Yêu.", "Wooden steps and small bridges lead up the waterfall's tiers; conveniently combined with O Quy Ho Pass and Love Waterfall."))
        .addressLine(of("QL4D, San Sả Hồ, Sa Pa, Lào Cai", "National Highway 4D, San Sa Ho, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.7700, 22.3640})
        .vibes(List.of("nature", "adventurous", "peaceful"))
        .atmosphere(of("rainforest-waterfall", "rainforest-waterfall"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "photography", "relax"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(85)
        .romanticScore(75)
        .localExperienceScore(65)
        .images(withCover(
            img("https://static.vinwonders.com/production/thac-bac-sa-pa-banner.jpg", "Toàn cảnh Thác Bạc", "Panorama of Silver Waterfall", true, 0),
            img("https://static.vinwonders.com/production/du-lich-thac-bac-sa-pa.jpg", "Lối bậc lên thác", "Steps up to the waterfall", false, 1),
            img("https://static.vinwonders.com/production/check-in-thac-bac.jpg", "Làn nước trắng xoá", "Sheets of white water", false, 2),
            img("https://static.vinwonders.com/production/thoi-diem-ly-tuong-den-thac-bac-o-sa-pa.jpg", "Cầu gỗ qua suối", "Wooden bridge over the stream", false, 3),
            img("https://static.vinwonders.com/production/cung-duong-dan-den-thac-bac.jpg", "Rừng xanh quanh thác", "Green forest around the waterfall", false, 4),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/04/Thac-bac-sapa-4.jpg", "Góc chụp gần chân thác", "A shot near the base of the falls", false, 5)
        ))
        .content(List.of(
            heading("Thác Bạc – mát lạnh giữa núi rừng", "Silver Waterfall – cool and crisp in the mountains"),
            quote("""
                    Mây vờn theo vệt trắng,
                    nước rót tiếng rì rầm;
                    bậc gỗ ôm triền đá,
                    rừng thở nhịp dịu êm.
                """,
                """
                    Clouds drift along the white streak,
                    water pours with a murmuring sound;
                    wooden steps hug the rocky slope,
                    the forest breathes a gentle rhythm.
                """),
            paragraph("Nằm ngay bên QL4D cách thị trấn Sa Pa không xa, Thác Bạc đổ bậc cao, tung bọt trắng xoá quanh năm. Lối tham quan là hệ bậc gỗ – cầu nhỏ bám sườn, đưa bạn lên các tầng nhìn cận thác mà vẫn an toàn. Hơi nước dày, không khí mát rượi kể cả vào trưa hè—rất hợp dừng chân kết hợp với Đèo Ô Quy Hồ và Thác Tình Yêu cùng cung đường.",
                "Right beside National Highway 4D not far from Sa Pa town, Silver Waterfall drops from a great height, throwing up white spray all year round. The visiting route is a system of wooden steps and small bridges clinging to the slope, taking you up to tiers with close-up yet safe views of the falls. The mist is thick and the air cool even at the height of summer—a perfect stop to combine with O Quy Ho Pass and Love Waterfall along the same route."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• 08:00–10:00: ánh sáng xiên dịu, ít đông, dễ chụp texture dòng nước.",
                "• 08:00–10:00: gentle slanting light, fewer crowds, easy to capture the texture of the water."),
            paragraph("• 15:30–17:00: ‘giờ vàng’ phủ ấm rừng thông; lưu ý quay về trước khi sương kéo xuống QL4D.",
                "• 15:30–17:00: ‘golden hour’ warms the pine forest; head back before the mist settles over National Highway 4D."),
            paragraph("• Mùa mưa (5–9): nước mạnh, cảnh hùng vĩ hơn nhưng lối đi có thể trơn—đi chậm, bám tay vịn.",
                "• Rainy season (May–Sept): stronger flow and grander scenery, but the path can be slippery—walk slowly and hold the handrail."),

            heading("Tuyến tham quan gợi ý 60–90 phút", "Suggested 60–90 minute tour route"),
            paragraph("• 0’–10’: gửi xe, khởi động, chuẩn bị áo mưa mỏng/áo khoác vì hơi nước.",
                "• 0’–10’: park, warm up, and ready a thin raincoat or jacket because of the spray."),
            paragraph("• 10’–35’: theo bậc gỗ lên điểm nhìn tầng giữa—chụp toàn cảnh ‘vệt bạc’ rót xuống khe.",
                "• 10’–35’: follow the wooden steps to the middle-tier viewpoint—shoot the panorama of the ‘silver streak’ pouring into the ravine."),
            paragraph("• 35’–60’: men cầu gỗ sang bờ đối diện—chụp cận cảnh tia nước và bụi nước lấp lánh.",
                "• 35’–60’: cross the wooden bridge to the opposite bank—shoot close-ups of the jets of water and the glittering spray."),
            paragraph("• 60’–90’: quay vòng xuống lối dưới, dừng ở chân thác chụp close-up bọt nước; nghỉ nhẹ rồi rời điểm.",
                "• 60’–90’: loop back down the lower path, stop at the base for close-ups of the foam; take a light rest, then leave."),

            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Tan-mat-chiem-nguong-ve-dep-cua-Thac-Bac-Cay-vi-cam-cua-nui-rung-Sapa-05-1621358699.jpg", "Dòng thác cận cảnh", "A close-up of the falls", false, 0)),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• Mềm dòng nước: đặt tốc độ 1/10–1/4s, tựa máy vào lan can/bờ đá, khép khẩu f/8–f/11.",
                "• Silky water: set a speed of 1/10–1/4s, brace the camera on a railing or rock, and stop down to f/8–f/11."),
            paragraph("• Hạt nước ‘lấp lánh’: tăng tốc 1/250–1/500s khi nắng chiếu xiên để đóng băng bọt nước.",
                "• ‘Sparkling’ droplets: raise the speed to 1/250–1/500s when the sunlight slants in to freeze the spray."),
            paragraph("• Lấy lớp: dùng thân cây, lan can gỗ làm tiền cảnh để tạo chiều sâu; đặt người ở 1/3 khung cho ảnh có tỷ lệ.",
                "• Build layers: use tree trunks or the wooden railing as a foreground for depth; place a person on a third of the frame for scale."),
            paragraph("• Chống mờ kính: lau ống kính định kỳ vì hơi nước; mang khăn microfiber nhỏ trong túi áo mưa.",
                "• Prevent a foggy lens: wipe the lens regularly because of the mist; keep a small microfiber cloth in your raincoat pocket."),

            heading("Kết hợp cung Ô Quy Hồ – Thác Tình Yêu", "Combining the O Quy Ho – Love Waterfall route"),
            paragraph("Thác Bạc nằm cùng trục QL4D với Cổng Trời Ô Quy Hồ và lối vào Thác Tình Yêu. Lịch gợi ý: sáng leo Thác Bạc → xế trưa ăn nhẹ → chiều sớm qua Thác Tình Yêu (đi bộ rừng mát) → săn hoàng hôn ở Cổng Trời; luôn trừ thời gian quay lại trước sương tối.",
                "Silver Waterfall sits on the same National Highway 4D axis as O Quy Ho Heaven's Gate and the entrance to Love Waterfall. Suggested schedule: climb Silver Waterfall in the morning → a light lunch around noon → Love Waterfall in the early afternoon (a cool forest walk) → chase the sunset at Heaven's Gate; always allow time to return before the evening mist."),

            heading("An toàn & chuẩn bị", "Safety & preparation"),
            paragraph("• Giày đế bám, áo mưa mỏng/áo khoác chống nước; tránh dép trơn vì bậc gỗ – đá ẩm.",
                "• Shoes with good grip, a thin raincoat or waterproof jacket; avoid smooth sandals because of the damp wooden steps and rocks."),
            paragraph("• Không leo qua lan can ‘lấy góc’; trẻ nhỏ luôn trong tầm tay; không thò tay ra sát mép đổ.",
                "• Don't climb over the railing to ‘get the shot’; keep small children within reach; don't reach out near the edge of the falls."),
            paragraph("• Trời mưa to: hạn chế lên tầng cao, ưu tiên đứng ở platform có che; khi sấm chớp, tạm xuống khu trú an toàn.",
                "• In heavy rain: avoid the higher tiers and stay on covered platforms; during thunder and lightning, move down to a safe shelter."),

            heading("Dịch vụ nhỏ & lưu ý môi trường", "Small services & environmental notes"),
            paragraph("Có quầy nước/đồ nướng đơn giản gần cổng—giá tuỳ thời điểm. Mang theo chai nước nhỏ, giữ rác trong túi riêng và mang ra ngoài điểm tập kết. Giữ âm lượng nhỏ để tiếng thác và rừng ‘làm nhạc nền’.",
                "There are simple drink and grill stalls near the gate—prices vary by season. Bring a small water bottle, keep your trash in a separate bag and carry it out to the collection point. Keep your voice down and let the sound of the falls and forest be the ‘soundtrack’."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/du-lich-thac-bac-sa-pa.jpg", "Lối bậc lên thác", "Steps up to the waterfall", false, 0),
                img("https://static.vinwonders.com/production/check-in-thac-bac.jpg", "Dòng nước trắng xoá", "White rushing water", false, 1),
                img("https://static.vinwonders.com/production/thoi-diem-ly-tuong-den-thac-bac-o-sa-pa.jpg", "Cầu gỗ qua suối", "Wooden bridge over the stream", false, 2)
            )),

            quote("“Tiếng nước rơi như một bản nhạc mát lành giữa rừng.”", "“The sound of falling water is like a cool melody in the forest.”"),
            divider(),
            mapBlock(new double[]{103.7700, 22.3640})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thác nước & Suối", "Waterfalls & Streams")).slug("thac-nuoc-suoi").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Thác bạc Sa Pa", "Sa Pa Silver Waterfall")).slug("thac-bac-sa-pa").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Bậc gỗ leo thác", "Wooden steps up the falls")).slug("bac-go-leo-thac").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 7) Thác Tình Yêu */
  private static PlaceDoc poiThacTinhYeu() {
    String slug = "thac-tinh-yeu";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Thác Tình Yêu", "Love Waterfall"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Con thác đẹp lãng mạn trong rừng quốc gia Hoàng Liên.", "A beautifully romantic waterfall in Hoang Lien National Park."))
        .description(of("Đi bộ rừng 15–20 phút đến thác; nước trong xanh, nhiều góc chụp; kết hợp Ghềnh Vàng – Suối Vàng gần đó.", "A 15–20 minute forest walk to the falls; clear blue water and many photo angles; combine with the nearby Golden Rapids – Golden Stream."))
        .addressLine(of("Trạm Tôn, Hoàng Liên, Sa Pa, Lào Cai", "Tram Ton, Hoang Lien, Sa Pa, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.7880, 22.3450})
        .vibes(List.of("romantic", "nature", "peaceful"))
        .atmosphere(of("forest-waterfall", "forest-waterfall"))
        .travelStyle(List.of("casual", "photography", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("relax", "photography", "explore"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(90)
        .photographyScore(90)
        .romanticScore(90)
        .localExperienceScore(70)
        .images(withCover(
            img("https://static.vinwonders.com/production/gioi-thieu-thac-tinh-yeu-o-sa-pa.jpg", "Thác Tình Yêu mềm mại", "The gentle Love Waterfall", true, 0),
            img("https://static.vinwonders.com/production/ve-dep-thac-tinh-yeu.jpg", "Lối mòn vào thác", "The trail to the waterfall", false, 1),
            img("https://static.vinwonders.com/production/gia-ve-tham-quan-thac.jpg", "Suối trong rừng", "A stream in the forest", false, 2),
            img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Chân thác", "The base of the falls", false, 3),
            img("https://static.vinwonders.com/production/du-day-vuot-thac-o-sa-pa.jpg", "Mùa mưa nước mạnh", "Strong flow in the rainy season", false, 4),
            img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Không gian xanh mát", "A cool, green setting", false, 5)
        ))
        .content(List.of(
            heading("Thác Tình Yêu – lối nhỏ nên thơ", "Love Waterfall – a poetic little trail"),
            quote("""
                    Rêu ôm bờ đá cũ,
                    suối hát khúc trong ngần;
                    bước chân qua tán lá,
                    gặp thác trắng mơ tần.
                """,
                """
                    Moss embraces the old stone bank,
                    the stream sings a crystal tune;
                    steps pass beneath the leaves,
                    and meet a dreamy white waterfall.
                """),
            paragraph("Nằm trong rừng quốc gia Hoàng Liên, Thác Tình Yêu là một dải lụa trắng mềm đổ giữa nền xanh rêu và gỗ ẩm. Lối mòn đất – đá phủ lá, đi 15–20 phút là tới chân thác. Không khí mát lạnh, tiếng suối rì rầm—rất hợp một buổi dạo rừng nhẹ, chụp ảnh và ngồi nghe rừng thở.",
                "Set in Hoang Lien National Park, Love Waterfall is a soft white ribbon falling against a backdrop of green moss and damp wood. The earthen, rocky, leaf-strewn trail takes 15–20 minutes to reach the base of the falls. The air is cool and crisp and the stream murmurs—perfect for a gentle forest walk, taking photos, and sitting to listen to the forest breathe."),

            heading("Thời điểm & nhịp tham quan", "Timing & visiting pace"),
            paragraph("• 08:00–10:00: nắng xiên xuyên tán, tia sáng loé trên bụi nước—ảnh trong và dịu.",
                "• 08:00–10:00: light slants through the canopy and flares on the spray—clear, soft photos."),
            paragraph("• 15:30–17:00: ‘giờ vàng’ nhuộm ấm rêu đá; chủ động quay về trước khi sương lạnh kéo tới.",
                "• 15:30–17:00: ‘golden hour’ warms the mossy rocks; head back before the cold mist rolls in."),
            paragraph("• Mùa mưa (5–9): nước mạnh, hùng vĩ nhưng lối đá trơn—đi chậm, bám tay vịn. Mùa khô (10–4): nước vừa, đi rừng dễ chịu, ảnh chi tiết rêu đẹp.",
                "• Rainy season (May–Sept): strong, majestic flow but slippery rocky paths—walk slowly and hold the handrail. Dry season (Oct–Apr): moderate flow, pleasant forest walking, lovely detailed moss shots."),

            heading("Lối mòn vào thác (15–20 phút)", "The trail to the falls (15–20 minutes)"),
            paragraph("Từ cổng kiểm soát Trạm Tôn, đi theo biển chỉ dẫn. Đường đất – đá đan xen, có vài bậc gỗ/cầu nhỏ qua suối. Giữ nhịp bước ngắn, tránh giẫm lên rễ trồi; mưa ẩm thì ưu tiên giày bám tốt.",
                "From the Tram Ton control gate, follow the signs. The path mixes earth and stone, with a few wooden steps and small bridges over the stream. Keep your steps short and avoid stepping on raised roots; in wet weather, favor shoes with good grip."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/Check-in-Suoi-vang-thac-tinh-yeu-cuc-hot-o-sapa-8-1620310017.jpg", "Dòng thác trắng xoá giữa rêu đá", "The white waterfall among mossy rocks", false, 0)),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• ‘Mềm nước’: đặt tốc 1/10–1/4s, tựa máy vào lan can/đá, khép khẩu f/8–f/11 để giữ nét toàn khung.",
                "• ‘Silky water’: set a speed of 1/10–1/4s, brace the camera on a railing or rock, and stop down to f/8–f/11 to keep the whole frame sharp."),
            paragraph("• ‘Hạt nước bay’: tăng tốc 1/250–1/500s khi nắng xiên để đóng băng tia nước lấp lánh.",
                "• ‘Flying droplets’: raise the speed to 1/250–1/500s when the light slants in to freeze the glittering jets of water."),
            paragraph("• Bố cục tầng: đứng chếch 30–45° để thấy cả thác và suối tiền cảnh; thêm một người ở 1/3 khung để tạo tỷ lệ.",
                "• Layered composition: stand at a 30–45° angle to capture both the falls and the stream foreground; add a person on a third of the frame for scale."),
            paragraph("• Chống mờ ống kính: mang khăn microfiber nhỏ; hơi nước đọng rất nhanh khi áp sát thác.",
                "• Prevent a foggy lens: bring a small microfiber cloth; mist settles very quickly when you get close to the falls."),

            heading("Kết hợp Ghềnh Vàng – Suối Vàng", "Combining the Golden Rapids – Golden Stream"),
            paragraph("Sau khi ngắm thác, men suối về phía các ghềnh đá vàng rêu để chụp chi tiết dòng chảy, phản chiếu trời qua tán rừng. Nước nông ven bờ có thể ngâm chân ngắn—tránh ra giữa dòng và không đứng trên phiến đá rêu trơn.",
                "After viewing the falls, follow the stream toward the mossy golden rapids to photograph the flow and the sky reflected through the canopy. The shallow water near the bank is fine for a quick foot soak—avoid the middle of the stream and don't stand on slippery mossy slabs."),

            heading("An toàn & chuẩn bị", "Safety & preparation"),
            paragraph("• Giày đế bám, áo mưa mỏng/áo khoác nhẹ; balo gọn, túi khô cho điện thoại.",
                "• Shoes with good grip, a thin raincoat or light jacket; a compact backpack and a dry bag for your phone."),
            paragraph("• Không leo lên mỏm đá sát mép đổ; mưa lớn/sấm chớp thì tạm dừng, lùi về điểm trú an toàn.",
                "• Don't climb onto rocks near the edge of the falls; in heavy rain or thunder, pause and retreat to a safe shelter."),
            paragraph("• Đi 2+ người; trẻ nhỏ luôn trong tầm tay. Mang theo 300–500ml nước và ít kẹo gừng.",
                "• Go in a group of two or more; keep small children within reach. Bring 300–500ml of water and some ginger candy."),

            heading("Ứng xử & môi trường", "Etiquette & environment"),
            paragraph("Không xả rác; đi trên lối mòn để tránh dẫm nát rễ cây. Không bật loa lớn—hãy để tiếng suối làm ‘nhạc nền’. Không viết/khắc lên đá, không bẻ rêu/nhặt sinh vật trong suối.",
                "Don't litter; stay on the trail to avoid trampling roots. Don't play loud speakers—let the stream be the ‘soundtrack’. Don't write or carve on the rocks, and don't pull off moss or pick up creatures from the stream."),

            heading("Lịch trình gợi ý 60–90 phút", "Suggested 60–90 minute itinerary"),
            paragraph("• 0’–20’: từ cổng Trạm Tôn đi bộ theo lối mòn, dừng 1–2 điểm cầu gỗ chụp suối.",
                "• 0’–20’: walk the trail from the Tram Ton gate, stopping at 1–2 wooden bridges to shoot the stream."),
            paragraph("• 20’–50’: tới chân thác—chụp toàn cảnh, close-up tia nước; nghỉ nhẹ.",
                "• 20’–50’: reach the base of the falls—shoot the panorama and close-ups of the water jets; take a light rest."),
            paragraph("• 50’–75’: men sang Ghềnh Vàng – Suối Vàng, chụp rêu – phản chiếu; quay lối cũ ra ngoài.",
                "• 50’–75’: head over to the Golden Rapids – Golden Stream, shoot the moss and reflections; return the way you came."),
            paragraph("• 75’–90’: về lại bãi xe/điểm tập kết, khởi hành tiếp cung QL4D (kết hợp Thác Bạc/Ô Quy Hồ nếu còn thời gian).",
                "• 75’–90’: return to the parking lot or meeting point, then continue along the National Highway 4D route (combining Silver Waterfall / O Quy Ho if time allows)."),

            gallery(List.of(
                img("https://static.vinwonders.com/production/gioi-thieu-thac-tinh-yeu-o-sa-pa.jpg", "Thác Tình Yêu mềm mại", "The gentle Love Waterfall", false, 0),
                img("https://static.vinwonders.com/production/ve-dep-thac-tinh-yeu.jpg", "Lối mòn trong rừng", "The trail through the forest", false, 1),
                img("https://static.vinwonders.com/production/suoi-vang-sa-pa.jpg", "Ghềnh – Suối Vàng", "The Golden Rapids – Golden Stream", false, 2)
            )),
            quote("“Đường vào thác là đường vào một câu chuyện đẹp.”", "“The path to the falls is the path into a beautiful story.”"),
            divider(),
            mapBlock(new double[]{103.7880, 22.3450})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thác nước & Suối", "Waterfalls & Streams")).slug("thac-nuoc-suoi").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Lăng mạn giữa rừng", "Romance in the forest")).slug("lang-man-giua-rung").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Rừng quốc gia Hoàng Liên", "Hoang Lien National Park")).slug("rung-quoc-gia-hoang-lien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Ghềnh Vàng Suối Vàng", "Golden Rapids Golden Stream")).slug("ghenh-vang-suoi-vang").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 8) Nhà thờ Đá Sa Pa & Quảng trường trung tâm */
  private static PlaceDoc poiNhaThoDaSaPa() {
    String slug = "nha-tho-da-sa-pa";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Nhà thờ Đá Sa Pa & Quảng trường", "Sa Pa Stone Church & Square"))
        .slug(slug)
        .parentSlug("sa-pa")
        .ancestors(List.of("sa-pa"))
        .childrenCount(0)
        .shortDescription(of("Biểu tượng trung tâm thị trấn, kiến trúc đá tạc, chợ phiên cuối tuần.", "The town center's icon, with carved-stone architecture and a weekend market."))
        .description(of("Check-in nhà thờ đá, quảng trường sôi động; tối cuối tuần có chợ phiên – biểu diễn văn nghệ dân tộc.", "Check in at the stone church and the lively square; weekend evenings bring a market and ethnic performances."))
        .addressLine(of("Trung tâm thị trấn Sa Pa, Lào Cai", "Central Sa Pa town, Lao Cai"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.8430, 22.3350})
        .vibes(List.of("cultural", "lively", "romantic"))
        .atmosphere(of("town-square-heritage", "town-square-heritage"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(85)
        .romanticScore(80)
        .localExperienceScore(80)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-01-1621312140.jpg", "Nhà thờ Đá Sa Pa", "Sa Pa Stone Church", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-04-1621312140.jpg", "Quảng trường trung tâm", "The central square", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-sapa-1753930409.jpg", "Tối cuối tuần nhộn nhịp", "A bustling weekend evening", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-sapa-1-1753930408.jpg", "Không gian bên trong", "The interior space", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-09-1621312141.jpg", "Góc ảnh cổ kính", "An antique-looking angle", false, 4),
            img("https://static.vinwonders.com/production/gioi-thieu-nha-tho-da-sa-pa.jpg", "Không gian lễ hội", "A festive atmosphere", false, 5)
        ))
        .content(List.of(
            heading("Nhà thờ Đá – điểm hẹn của Sa Pa", "The Stone Church – Sa Pa's meeting point"),
            quote("""
                    Chuông ngân qua màn sương,
                    đá cổ kể chuyện phố;
                    quảng trường vào nhịp chợ,
                    tiếng khèn gọi đêm nâu.
                """,
                """
                    Bells ring through the curtain of mist,
                    ancient stone tells the town's story;
                    the square falls into market rhythm,
                    the khen pipe calls the brown night.
                """),
            paragraph("Nằm ở trái tim thị trấn, Nhà thờ Đá Sa Pa với kiến trúc đá tạc cổ kính nhìn thẳng ra quảng trường trung tâm—nơi diễn ra các hoạt động văn hoá, chợ phiên cuối tuần và những cuộc hẹn ‘gặp nhau ở nhà thờ’. Vị trí thuận tiện, xung quanh là hàng quán, café và các tuyến đi bộ ngắn.",
                "In the heart of town, Sa Pa Stone Church, with its ancient carved-stone architecture, faces directly onto the central square—home to cultural activities, the weekend market and the classic ‘meet me at the church’ rendezvous. Conveniently located, it is surrounded by shops, cafés and short walking routes."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• Sáng 07:00–09:00: ánh sáng xiên dịu, ít người—đẹp để chụp kiến trúc và sương mỏng.",
                "• Morning 07:00–09:00: gentle slanting light, few people—lovely for shooting the architecture and the thin mist."),
            paragraph("• Chiều 16:30–18:30: ‘giờ vàng’ nhuộm ấm mặt đá, sau đó quảng trường đông vui hơn.",
                "• Afternoon 16:30–18:30: ‘golden hour’ warms the stone facade, after which the square grows livelier."),
            paragraph("• Tối cuối tuần: chợ phiên, biểu diễn văn nghệ dân tộc—đông đúc, sôi động; nhớ mang áo ấm vì nhiệt độ hạ nhanh.",
                "• Weekend evenings: the market and ethnic performances—crowded and lively; bring warm clothing as the temperature drops quickly."),

            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-sapa-3-1753930767.jpg", "Góc check-in trước tiền sảnh đá", "A check-in spot before the stone portico", false, 0)),

            heading("Kiến trúc & không gian", "Architecture & space"),
            paragraph("Khối nhà thờ đá tạc với vòm cửa, tháp chuông mảnh và các ô cửa kính màu tạo nên sắc thái cổ điển giữa sương núi. Bề mặt đá giàu ‘texture’—rất ‘ăn ảnh’ khi nắng xiên sáng/chiều. Bên trong là không gian tĩnh; trang phục nghiêm túc, nói nhỏ nếu ghé thăm.",
                "The carved-stone church, with its arched doorways, slender bell tower and stained-glass windows, creates a classic mood amid the mountain mist. The stone surface is rich in ‘texture’—very photogenic in the slanting light of morning or afternoon. Inside is a quiet space; dress respectfully and speak softly if you visit."),

            heading("Quảng trường trung tâm", "The central square"),
            paragraph("Quảng trường là ‘phòng khách’ của thị trấn: điểm hẹn tour, nơi diễn ra hội chợ phiên, lễ hội theo mùa và các tiết mục khèn, múa xoè vào dịp cuối tuần. Vòng quanh quảng trường là dải café/đồ nướng—tiện nghỉ chân ngắm dòng người và đồi núi phía xa.",
                "The square is the town's ‘living room’: a tour meeting point and the setting for the market, seasonal festivals and weekend khen-pipe and xoe-dance performances. Around it runs a strip of cafés and grills—handy for a rest while watching the crowds and the distant hills."),

            heading("Trải nghiệm gợi ý", "Suggested experiences"),
            paragraph("• Đi bộ vòng quảng trường, dừng nghe nhạc cụ dân tộc—ủng hộ nghệ sĩ bằng cách mua đồ lưu niệm nhỏ.",
                "• Walk around the square, stop to listen to traditional instruments—support the performers by buying a small souvenir."),
            paragraph("• Ghé bên trong nhà thờ vào giờ mở cửa (khi cho phép), giữ yên tĩnh; tránh chụp flash.",
                "• Step inside the church during opening hours (when permitted), keep quiet, and avoid using the flash."),
            paragraph("• Tối cuối tuần: hòa vào chợ phiên, thử mặc áo choàng/khăn choàng thổ cẩm để chụp ảnh kỷ niệm.",
                "• Weekend evenings: join the market and try on a brocade cloak or shawl for souvenir photos."),

            heading("Góc chụp & mẹo ảnh", "Photo spots & shooting tips"),
            paragraph("• Mặt tiền nhà thờ: chụp chếch 30° để thấy tháp chuông và khối vòm; đặt đường cong quảng trường làm tiền cảnh.",
                "• Church facade: shoot at a 30° angle to capture the bell tower and the arches; use the square's curve as the foreground."),
            paragraph("• Ban đêm: đo sáng vào mặt đá, hạ EV 0.3–0.7 để giữ chi tiết; canh đèn quảng trường ‘vẽ’ viền khối kiến trúc.",
                "• At night: meter for the stone facade and lower EV by 0.3–0.7 to keep detail; let the square's lights ‘draw’ the outline of the architecture."),
            paragraph("• Ảnh người: nền tường đá xám giúp tôn màu trang phục; tránh đứng sát đèn nền gây ‘cháy’ viền.",
                "• Portraits: the gray stone wall sets off clothing colors; avoid standing close to a background light that ‘blows out’ the edges."),

            heading("Ứng xử & lưu ý", "Etiquette & notes"),
            paragraph("Tôn trọng không gian tôn giáo: không nói to, không leo lên bậc/lan can để ‘lấy góc’. Ở quảng trường, tránh chắn lối di chuyển; cuối tuần đông—giữ tư trang gọn và không xả rác. Trời lạnh mang áo ấm/áo mưa mỏng vì sương xuống nhanh.",
                "Respect the religious space: don't speak loudly or climb onto steps or railings to ‘get the shot’. In the square, don't block the walkways; weekends are crowded—keep your belongings tidy and don't litter. In cold weather bring warm clothing or a light raincoat, as the mist falls quickly."),

            heading("Ăn uống quanh khu vực", "Food around the area"),
            paragraph("Vành đai quảng trường có đồ nướng Sa Pa (thịt xiên, cơm lam, trứng, ngô nếp), café nhìn thẳng nhà thờ—hợp dừng chân 30–45 phút. Hỏi giá trước, chọn quán đông người địa phương để có trải nghiệm ổn định.",
                "The ring around the square has Sa Pa grills (meat skewers, bamboo-tube rice, eggs, sticky corn) and cafés facing the church—good for a 30–45 minute stop. Ask prices first and choose places busy with locals for a reliable experience."),

            heading("Lịch trình gợi ý 45–90 phút", "Suggested 45–90 minute itinerary"),
            paragraph("• 0’–15’: chụp mặt tiền nhà thờ lúc ‘giờ vàng’/sáng sớm.",
                "• 0’–15’: shoot the church facade during ‘golden hour’ or early morning."),
            paragraph("• 15’–40’: đi vòng quảng trường—ảnh phố núi, dãy núi nền xa.",
                "• 15’–40’: walk around the square—shots of the mountain town and the distant ranges."),
            paragraph("• 40’–70’: café/đồ nướng ngắm phố; nếu cuối tuần, xem tiết mục dân tộc.",
                "• 40’–70’: café or grilled food while watching the street; on weekends, catch the ethnic performances."),
            paragraph("• 70’–90’: chụp đêm (tuỳ thời gian), rồi tản bộ về khách sạn.",
                "• 70’–90’: night photography (time permitting), then stroll back to your hotel."),

            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-01-1621312140.jpg", "Nhà thờ Đá Sa Pa", "Sa Pa Stone Church", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-da-co-tuyet-sac-kien-truc-giua-dai-ngan-tay-bac-04-1621312140.jpg", "Quảng trường trung tâm", "The central square", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-sapa-1753930409.jpg", "Không khí tối cuối tuần", "The weekend-evening atmosphere", false, 2)
            )),

            quote("“Hẹn nhau ở nhà thờ đá – câu mở đầu cho nhiều cuộc dạo chơi Sa Pa.”", "“Meet me at the stone church – the opening line for many a Sa Pa outing.”"),
            divider(),
            mapBlock(new double[]{103.8430, 22.3350})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-trinh-ton-giao").build(),
            PlaceDoc.CategoryMini.builder().name(of("Biểu tượng thị trấn", "Town icon")).slug("bieu-tuong-thi-tran").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Nhà thờ đá cổ kính", "Ancient stone church")).slug("nha-tho-da-co-kinh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Chợ phiên cuối tuần", "Weekend market")).slug("cho-phien-cuoi-tuan").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Quảng trường trung tâm", "Central square")).slug("quang-truong-trung-tam").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }
}
