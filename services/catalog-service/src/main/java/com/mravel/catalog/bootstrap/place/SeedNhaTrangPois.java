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
        .name(of("Tháp Trầm Hương (Quảng trường 2/4)", "Tram Huong Tower (2/4 Square)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Biểu tượng ven biển Nha Trang, điểm check-in trên đường Trần Phú.", "A seaside icon of Nha Trang, a popular check-in spot on Tran Phu Street."))
        .description(of("Công trình nghệ thuật ngay quảng trường 2/4, nhìn thẳng ra biển Trần Phú. Buổi tối tháp lên đèn, xung quanh nhiều hoạt động đường phố.", "An artistic landmark right on 2/4 Square, facing the Tran Phu seafront. At night the tower lights up, surrounded by lively street activities."))
        .addressLine(of("Quảng trường 2/4, Trần Phú, Nha Trang, Khánh Hòa", "2/4 Square, Tran Phu, Nha Trang, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.1962, 12.2365})
        .vibes(List.of("vibrant", "relaxing", "lively"))
        .atmosphere(of("coastal-plaza", "coastal-plaza"))
        .travelStyle(List.of("casual", "photography"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "photography", "relax"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(60)
        .photographyScore(80)
        .romanticScore(70)
        .localExperienceScore(65)
        .images(withCover(
            img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp Trầm Hương nhìn ra biển", "Tram Huong Tower facing the sea", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/thap-tram-huong-truoc-khi-duoc-thay-ao-moi-1687939423.jpeg", "Góc gần tháp", "A close-up view of the tower", false, 1),
            img("https://static.vinwonders.com/2022/06/quang-truong-2-4-nha-trang-2-1.jpg", "Quảng trường 2/4", "2/4 Square", false, 2),
            img("https://statics.vinpearl.com/thap-tram-huong-nha-trang_1753687142.jpg", "Tháp lên đèn", "The tower lit up", false, 3),
            img("https://static.vinwonders.com/2022/05/duong-tran-phu-nha-trang-thumb.jpg", "View biển Trần Phú", "Tran Phu seafront view", false, 4),
            img("https://52hz.vn/wp-content/uploads/2023/02/35-thap-tram-huong-ve-dem.jpg", "Hoàng hôn", "Sunset", false, 5)
        ))
        .content(List.of(
            heading("Tháp Trầm Hương – biểu tượng biển Nha Trang", "Tram Huong Tower – the icon of the Nha Trang seaside"),
            quote("""
                    Gió biển hong màu tháp,
                    sóng chải tóc bờ cát;
                    bước chân qua quảng trường,
                    hoàng hôn rót từng hát.
                """, """
                    Sea wind warms the tower's hue,
                    waves comb the sandy shore;
                    footsteps cross the square,
                    sunset pours each verse.
                """),
            paragraph("Nằm giữa quảng trường 2/4 trên trục Trần Phú, Tháp Trầm Hương là điểm hẹn dễ tìm nhất khi dạo biển Nha Trang. Ban ngày, tháp nổi bật trên nền trời xanh; về tối, hệ đèn lên màu khiến toàn khối kiến trúc như một búp trầm phát sáng giữa quảng trường đông vui.", "Standing in the middle of 2/4 Square along Tran Phu Street, Tram Huong Tower is the easiest landmark to find when strolling along the Nha Trang seafront. By day, the tower stands out against the blue sky; by night, its colored lights make the whole structure glow like an agarwood bud amid the bustling square."),
            paragraph("Tên gọi ‘Trầm Hương’ gợi nhắc đặc sản trầm – hương xứ Khánh Hòa. Hình khối tháp gồm nhiều lớp cánh uốn, tạo cảm giác như nụ hương đang hé nở hướng ra biển. Không gian mở 360° xung quanh giúp bạn dễ bố trí khung hình, tổ chức hoạt động sự kiện hoặc đơn giản là ngồi nghỉ chân nghe sóng.", "The name 'Tram Huong' (agarwood) evokes Khanh Hoa's famous incense specialty. The tower's form, made of many curved petal-like layers, feels like an incense bud opening toward the sea. The open 360° space around it makes it easy to frame photos, host events, or simply sit and listen to the waves."),

            heading("Thời điểm & cách trải nghiệm", "Timing & how to experience it"),
            paragraph("Khung giờ lý tưởng: 16:30–18:30. Lên ảnh đẹp cả khi trời xanh lẫn ‘giờ xanh’ trước khi tối hẳn. Nếu thích không khí sôi động, ở lại đến 19:00–20:00 khi quảng trường đông người và đèn xe kéo vệt trên đường Trần Phú.", "Ideal window: 16:30–18:30. Photos look great both under a blue sky and during the 'blue hour' before full dark. If you prefer a lively vibe, stay until 19:00–20:00 when the square fills up and car lights streak along Tran Phu Street."),
            paragraph("Di chuyển: gửi xe máy tại bãi gần quảng trường, đi bộ vòng quanh tháp một vòng để tìm góc ưng ý; sau đó băng qua lối sang vỉa hè biển để hít gió. Mang theo nước uống nhẹ, khăn giấy, và một chiếc áo mỏng vì gió biển có thể lạnh sau hoàng hôn.", "Getting around: park your motorbike at a lot near the square, walk a loop around the tower to find your favorite angle, then cross over to the seaside walkway for some fresh air. Bring a light drink, tissues, and a thin jacket, as the sea breeze can get cold after sunset."),

            imageBlock(img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp Trầm Hương nhìn ra biển", "Tram Huong Tower facing the sea", false, 0)),

            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & small tips"),
            paragraph("Chính diện từ mép biển: lấy trọn khối tháp và đường chân trời – phù hợp ảnh kỷ niệm ‘điểm danh’.", "Head-on from the water's edge: capture the whole tower and the horizon – perfect for a 'been-there' souvenir shot."),
            paragraph("Góc chéo 30–45° từ phía bắc quảng trường: nhấn các lớp ‘cánh’ của tháp, ảnh có chiều sâu hơn.", "A 30–45° diagonal from the north side of the square: emphasizes the tower's 'petal' layers, giving the photo more depth."),
            paragraph("Ảnh đêm: tắt flash, áp ống sát lan can để giảm phản chiếu; nếu có tripod mini, đặt ở mép bồn cây, chụp kéo sáng 0.5–1s để bắt vệt xe trên Trần Phú.", "Night shots: turn off the flash, press the lens against the railing to reduce reflections; if you have a mini tripod, set it on the edge of a planter and shoot a 0.5–1s exposure to catch light trails on Tran Phu."),
            paragraph("Selfie nhóm: đứng lùi 6–8m, máy hơi hạ thấp để tháp ‘cao’ hơn trong khung; chia nhóm theo tầng cao–thấp để không che mặt nhau.", "Group selfies: stand back 6–8m and lower the camera slightly so the tower looks 'taller' in the frame; arrange the group in high–low tiers so no one blocks another's face."),

            heading("Kết hợp dạo biển Trần Phú", "Combine with a Tran Phu seaside walk"),
            paragraph("Từ tháp, đi bộ dọc vỉa hè về hướng nam (bãi tắm trung tâm) để ngắm phố lên đèn, hoặc rẽ về phía chợ đêm nếu đang mở. Nếu có thời gian, đặt lịch ghé Tháp Bà Ponagar/tắm bùn vào buổi sáng hôm sau để cân bằng nhịp tham quan – dạo biển.", "From the tower, walk south along the promenade (toward the central beach) to watch the city light up, or head to the night market if it's open. If you have time, plan a visit to Po Nagar Towers / a mud bath the next morning to balance sightseeing with seaside strolls."),

            heading("Ăn uống quanh quảng trường", "Food & drink around the square"),
            paragraph("Đồ uống nhanh: nước dừa, nước mía, sữa chua dầm trái cây từ các kiosque dọc vỉa hè.", "Quick drinks: coconut water, sugarcane juice, and fruit yogurt from the kiosks along the sidewalk."),
            paragraph("Ăn vặt: bánh tráng nướng, xiên nướng; gọi phần nhỏ để vừa ăn vừa dạo. Lưu ý xả rác đúng nơi quy định vì khu vực nhiều gió.", "Snacks: grilled rice paper and skewers; order small portions to eat while you walk. Please dispose of trash properly, as the area is very windy."),
            paragraph("Quán café/nhìn biển: chọn quán có tầng lầu gần mặt tiền Trần Phú để ngắm thẳng ra tháp và bờ biển.", "Cafés with a sea view: choose an upper-floor spot near the Tran Phu frontage for a direct view of the tower and the shoreline."),

            heading("Bối cảnh & kiến trúc", "Context & architecture"),
            paragraph("Công trình có tính biểu trưng cao, phối hợp mảng khối uốn cong gợi hình nụ hương. Màu sắc ‘hơi hồng cam’ nổi rõ trên nền cát và nước biển xanh, dễ nhận diện từ xa. Quảng trường xung quanh là không gian mở phục vụ sự kiện cộng đồng, lễ hội biển theo mùa.", "The structure is highly symbolic, with curved volumes evoking an incense bud. Its pinkish-orange tone stands out against the sand and blue sea, recognizable from afar. The surrounding square is an open space for community events and seasonal sea festivals."),

            heading("Thời tiết & mùa đẹp", "Weather & best season"),
            paragraph("Mùa nắng (thường từ 3–9): trời trong, biển xanh, hoàng hôn rõ màu. Mang kem chống nắng và nón rộng vành.", "Dry season (usually March–September): clear skies, blue sea, vivid sunsets. Bring sunscreen and a wide-brimmed hat."),
            paragraph("Mùa mưa: tháp vẫn chụp được ở tông trầm – phản chiếu mặt nền ướt khá thú vị. Ưu tiên trang phục tối giản, giày chống trượt.", "Rainy season: the tower still photographs well in moody tones – reflections on wet pavement can be quite striking. Opt for minimalist clothing and non-slip shoes."),

            heading("Lịch trình gợi ý 60–90 phút", "Suggested 60–90 minute itinerary"),
            paragraph("• 0’–10’: gửi xe, đi bộ tiếp cận từ mép biển để có khung toàn cảnh tháp + đường chân trời.", "• 0'–10': park, approach from the water's edge for a wide shot of the tower + horizon."),
            paragraph("• 10’–30’: di chuyển sang góc chéo 30–45° để chụp ‘cánh’ tháp, thử khung dọc/khung ngang.", "• 10'–30': move to a 30–45° diagonal to shoot the tower's 'petals', trying both portrait and landscape framing."),
            paragraph("• 30’–60’: qua vỉa hè biển, nghỉ chân, chụp hoàng hôn và thời khắc bật đèn.", "• 30'–60': cross to the seaside walkway, take a break, and shoot the sunset and the moment the lights come on."),
            paragraph("• 60’–90’: mua đồ uống nhẹ/kẹo kéo, dạo tiếp dọc Trần Phú hoặc sang chợ đêm (nếu mở).", "• 60'–90': grab a light drink or pulled candy, keep strolling along Tran Phu, or head to the night market (if open)."),

            heading("Ứng xử & lưu ý", "Etiquette & notes"),
            paragraph("Không leo trèo lên bồn hoa/lan can; giữ lối đi thông thoáng để không chắn người khác chụp ảnh.", "Do not climb on planters or railings; keep walkways clear so you don't block others taking photos."),
            paragraph("Qua đường tại vị trí có vạch, chú ý tín hiệu vì lưu lượng xe lớn vào buổi tối.", "Cross at marked crosswalks and watch the signals, as traffic is heavy in the evening."),
            paragraph("Buổi tối đông người – gọn đồ cá nhân, tránh đặt túi xách xuống nền khi mải chụp ảnh.", "Evenings are crowded – keep your belongings tidy and avoid setting bags on the ground while absorbed in taking photos."),

            heading("Tiện ích & tiếp cận", "Facilities & accessibility"),
            paragraph("Khu vực có ghế nghỉ rải rác, vỉa hè tương đối phẳng – hỗ trợ xe nôi/xe lăn ở nhiều đoạn (một số mép vỉa có dốc thoải). Nhà vệ sinh công cộng gần quảng trường có thể thay đổi theo thời điểm – nên hỏi bảo vệ/kiosque lân cận.", "The area has scattered benches and a fairly flat sidewalk – stroller/wheelchair friendly in many sections (some curbs have gentle ramps). Public restrooms near the square may vary over time – ask a guard or a nearby kiosk."),

            heading("Gợi ý mở rộng bán kính 2km", "Suggestions within a 2km radius"),
            paragraph("• Bãi biển trung tâm Nha Trang: tắm biển sáng sớm hoặc chạy bộ dọc vỉa hè.", "• Nha Trang central beach: an early-morning swim or a jog along the promenade."),
            paragraph("• Chợ đêm Nha Trang (tuỳ ngày mở): ăn vặt, mua quà nhỏ.", "• Nha Trang Night Market (depending on opening days): snacks and small souvenirs."),
            paragraph("• Cầu Trần Phú: khung nhìn sông–biển khi lên đèn, phù hợp ảnh cityscape.", "• Tran Phu Bridge: a river-and-sea view when lit up, great for cityscape shots."),

            gallery(List.of(
                img("https://statics.vinpearl.com/thap-tram-huong_1749637523.jpg", "Tháp nhìn ra biển", "The tower facing the sea", false, 0),
                img("https://statics.vinpearl.com/thap-tram-huong-nha-trang_1753687142.jpg", "Tháp lên đèn", "The tower lit up", false, 1),
                img("https://static.vinwonders.com/2022/05/duong-tran-phu-nha-trang-thumb.jpg", "Đường Trần Phú ven biển", "Tran Phu seaside street", false, 2)
            )),

            quote("“Đi Nha Trang, cứ ra quảng trường – tháp sẽ chỉ bạn hướng về biển.”", "\"In Nha Trang, just head to the square – the tower will point you toward the sea.\""),
            divider(),
            mapBlock(new double[]{109.1962, 12.2365})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Biểu tượng thành phố", "City icon")).slug("bieu-tuong-thanh-pho").build(),
            PlaceDoc.CategoryMini.builder().name(of("Quảng trường", "Square")).slug("quang-truong").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng Nha Trang", "Nha Trang icon")).slug("bieu-tuong-nha-trang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Check-in biển", "Seaside check-in")).slug("check-in-bien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 2) Chùa Long Sơn (Tượng Phật Trắng) */
  private static PlaceDoc poiChuaLongSon() {
    String slug = "chua-long-son";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Chùa Long Sơn (Tượng Phật Trắng)", "Long Son Pagoda (White Buddha Statue)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Ngôi chùa nổi tiếng với tượng Phật Trắng trên đồi, nhìn ra thành phố.", "A famous pagoda with a White Buddha statue on the hill, overlooking the city."))
        .description(of("Leo bậc thang lên khu tượng Phật Trắng, phóng tầm mắt toàn cảnh Nha Trang. Không gian thanh tịnh, thích hợp buổi sáng mát.", "Climb the steps up to the White Buddha statue for a panoramic view of Nha Trang. A serene space, ideal in the cool morning."))
        .addressLine(of("20 Đường 23/10, Phương Sơn, Nha Trang", "20 23/10 Street, Phuong Son, Nha Trang"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.1787, 12.2486})
        .vibes(List.of("peaceful", "cultural", "spiritual"))
        .atmosphere(of("temple-hilltop", "temple-hilltop"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography", "explore"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(80)
        .romanticScore(60)
        .localExperienceScore(75)
        .images(withCover(
            img("https://cdn2.tuoitre.vn/thumb_w/1200/471584752817336320/2024/11/30/z6082977223128f21e0a8b82824cf52ecdf04be26c86ac-173293337597782060114.jpg", "Tượng Phật Trắng – Long Sơn", "White Buddha statue – Long Son", true, 0),
            img("https://static.vinwonders.com/2022/04/chua-long-son-3.jpg", "Cổng tam quan", "The three-entrance gate", false, 1),
            img("https://static.vinwonders.com/2022/04/chua-long-son-10.jpg", "Tượng Phật Tổ niết bàn", "Reclining Buddha statue", false, 2),
            img("https://static.vinwonders.com/2022/04/chua-long-son-8.jpg", "Không gian bên trong chùa", "Inside the pagoda", false, 3),
            img("https://static.vinwonders.com/2022/04/chua-long-son-5.jpg", "Không gian sân chùa", "The pagoda courtyard", false, 4),
            img("https://static.vinwonders.com/2022/04/chua-long-son-11.jpg", "Tượng Phật cận cảnh", "Close-up of the Buddha statue", false, 5)
        ))
        .content(List.of(
            heading("Long Sơn – điểm tĩnh giữa lòng phố biển", "Long Son – a quiet spot in the heart of the coastal city"),
            quote("""
                    Chuông ngân qua tán lá,
                    gió trắng rửa ưu phiền;
                    bậc đá nâng từng bước,
                    lòng nhẹ tựa mây miền.
                """, """
                    Bells ring through the leaves,
                    a white breeze washes away worry;
                    stone steps lift each footfall,
                    the heart grows light as drifting clouds.
                """),
            paragraph("Tựa lưng đồi Trại Thủy, chùa Long Sơn nổi tiếng với tượng Phật Trắng (Phật Thích Ca Mâu Ni ngồi thiền) uy nghi hướng về thành phố. Không gian rợp bóng cây, khói trầm phảng phất; sáng sớm hoặc sau 16:30 là lúc mát, ít đông, hợp tản bộ và chiêm bái.", "Set against Trai Thuy Hill, Long Son Pagoda is famous for its White Buddha statue (Shakyamuni Buddha in meditation) gazing majestically over the city. The space is shaded by trees with a faint scent of incense; early morning or after 16:30 is cool and less crowded, ideal for strolling and worship."),

            heading("Lịch sử & kiến trúc khái quát", "History & architecture overview"),
            paragraph("Chùa gắn với dòng Lâm Tế, nhiều lần trùng tu sau biến cố thiên tai – hỏa hoạn. Bố cục trải theo sườn đồi: cổng tam quan, sân – chánh điện, các cấp bậc thang dẫn lên khu tượng Phật và đài chuông. Màu ngói sẫm và các hoạ tiết rồng, mây thanh nhã tạo cảm giác cổ kính, khoáng đạt.", "The pagoda belongs to the Lam Te (Linji) Zen lineage and has been restored several times after natural disasters and fires. Its layout spreads along the hillside: the three-entrance gate, courtyard and main hall, and flights of steps leading up to the Buddha statue and bell tower. The dark roof tiles and graceful dragon and cloud motifs create an ancient, airy feel."),
            paragraph("Tượng Phật Trắng đặt trên bệ sen lớn, xung quanh có phù điêu về thập thiện – nhắc nhở du khách giữ tâm tĩnh, hành xử ôn hòa khi tham quan.", "The White Buddha statue sits on a large lotus pedestal, surrounded by reliefs of the Ten Virtues – reminding visitors to keep a calm mind and behave gently while visiting."),

            heading("Đường lên tượng Phật Trắng", "The path up to the White Buddha"),
            paragraph("Từ cổng tam quan, đi theo lối bậc đá. Dọc đường có các điểm dừng: tháp chuông, tượng Phật nhập Niết Bàn, bia khắc kinh. Đi chậm, bám tay vịn ở đoạn dốc; nhường lối tại chỗ hẹp. Lên tới khu tượng, tầm nhìn mở: phố xá, sông Cái, xa hơn là đường bờ biển cong ôm thành phố.", "From the three-entrance gate, follow the stone steps. Along the way are stops: the bell tower, the reclining Buddha statue, and sutra-inscribed steles. Walk slowly, hold the railing on steep stretches, and yield in narrow spots. At the statue, the view opens up: the streets, the Cai River, and farther out the curving coastline embracing the city."),
            imageBlock(img("https://static.vinwonders.com/2022/04/chua-long-son-14.jpg", "Tháp chuông Đại Hồng Chung", "The Great Bell tower", false, 0)),

            heading("Không gian chánh điện & ứng xử", "The main hall & etiquette"),
            paragraph("Trang phục kín đáo (che vai, gối); hạ giọng nói, tắt chuông điện thoại. Tránh chụp ảnh khi có nghi lễ; nếu cần chụp, tắt flash và đứng gọn một bên. Không đặt chân lên bệ, không tự ý gõ chuông, đánh mõ. Quyên góp nên bỏ vào thùng công đức, không đưa trực tiếp cho người hành lễ.", "Dress modestly (cover shoulders and knees); lower your voice and silence your phone. Avoid taking photos during ceremonies; if you must, turn off the flash and stand to one side. Do not step on the pedestals or strike the bell or wooden fish on your own. Place donations in the merit box rather than handing them directly to those conducting rites."),

            heading("Thời điểm tham quan gợi ý", "Suggested visiting times"),
            paragraph("• 06:00–08:00: nắng xiên nhẹ, không khí trong, thuận ảnh kiến trúc + cây xanh.", "• 06:00–08:00: gentle slanting light and clear air, good for shots of architecture + greenery."),
            paragraph("• 16:30–18:00: bóng râm nhiều, lên khu tượng nhìn ‘giờ xanh’ trước hoàng hôn; thành phố bắt đầu lên đèn rất đẹp.", "• 16:30–18:00: plenty of shade; go up to the statue for the 'blue hour' before sunset, when the city begins to light up beautifully."),
            paragraph("• Ngày rằm/lễ lớn: có thể đông, bãi xe kín – cân nhắc đi sớm hơn 1–2 giờ.", "• Full-moon days / major festivals: it can be crowded and parking full – consider arriving 1–2 hours earlier."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Chân tượng: đứng chéo 30–45° để thấy rõ nếp áo và bệ sen; đưa đường chân trời thấp để tượng ‘vươn cao’.", "• At the statue's base: stand at a 30–45° angle to show the robe folds and lotus pedestal clearly; keep the horizon low so the statue 'rises tall'."),
            paragraph("• Trên bậc thang: chụp ngược lên khung lá – bậc – tượng; mở khẩu vừa phải (f/4–5.6) để tách chủ thể.", "• On the steps: shoot upward framing leaves – steps – statue; use a moderate aperture (f/4–5.6) to separate the subject."),
            paragraph("• Toàn cảnh: áp ống sát tay vịn giảm phản xạ; giờ ‘xanh’ cho bầu trời mịn, nếu có tripod mini, chụp 1–2s bắt ánh đèn phố.", "• Panorama: press the lens against the railing to cut reflections; the 'blue hour' gives a smooth sky, and with a mini tripod a 1–2s exposure catches the city lights."),
            paragraph("• Chân dung tĩnh: tận dụng mảng tường trắng – xám của chánh điện làm nền đơn sắc, tránh backlight gắt.", "• Still portraits: use the white-gray walls of the main hall as a monochrome backdrop, avoiding harsh backlight."),

            heading("Trải nghiệm tĩnh tâm", "A mindful experience"),
            paragraph("Thử ngồi vài phút ở bậc cao, hít sâu – thở chậm, lắng nghe tiếng chuông vọng qua tán lá. Nếu có duyên, dự một thời tụng ngắn (đứng phía sau, giữ yên lặng). Đọc nhanh nội dung các bia khắc dọc lối đi như một ‘đường thiền’ nho nhỏ.", "Try sitting for a few minutes on the upper steps, breathing deeply and slowly, listening to the bells echo through the leaves. If the chance arises, attend a short chanting session (stand at the back, stay silent). Quickly read the inscribed steles along the path as a little 'meditation walk'."),

            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Mang giày đế bám (bậc đá có thể trơn khi mưa). Mang nước nhỏ, nón/áo mỏng; không xả rác, không khắc vẽ lên đá. Tôn trọng không gian tu tập: không nói to, không đùa giỡn, không bay flycam khi chưa xin phép.", "Wear grippy shoes (stone steps can be slippery when wet). Bring a small bottle of water, a hat or light jacket; don't litter or carve on the stones. Respect the place of practice: no loud talking, no horseplay, and no drone flying without permission."),

            heading("Lịch trình gợi ý 60–90 phút", "Suggested 60–90 minute itinerary"),
            paragraph("• 0’–10’: gửi xe, vào cổng, dạo sân – chụp tam quan.", "• 0'–10': park, enter the gate, stroll the courtyard and photograph the three-entrance gate."),
            paragraph("• 10’–30’: thăm chánh điện, nghỉ ngắn dưới bóng cây.", "• 10'–30': visit the main hall, take a short rest in the shade."),
            paragraph("• 30’–60’: leo bậc thang, dừng ở tháp chuông – tượng Niết Bàn.", "• 30'–60': climb the steps, stopping at the bell tower and reclining Buddha."),
            paragraph("• 60’–90’: lên khu tượng Phật Trắng, chụp toàn cảnh thành phố, ngồi tĩnh tâm 5–10 phút.", "• 60'–90': head up to the White Buddha, photograph the city panorama, and sit quietly for 5–10 minutes."),

            heading("Kết hợp điểm gần kề (bán kính 2–3km)", "Combine with nearby spots (2–3km radius)"),
            paragraph("• Tháp Bà Ponagar: kiến trúc Chăm đặc trưng – đi sáng sớm để mát.", "• Po Nagar Towers: distinctive Cham architecture – go early to stay cool."),
            paragraph("• Chợ Xóm Mới / chợ Đầm (tùy thời điểm): ẩm thực, trái cây địa phương.", "• Xom Moi Market / Dam Market (depending on timing): local food and fruit."),
            paragraph("• Cầu Hà Ra – sông Cái: khung thành phố nhìn dọc sông lúc chiều muộn.", "• Ha Ra Bridge – Cai River: a city view along the river in the late afternoon."),

            heading("Thông tin thực tế", "Practical information"),
            paragraph("Bãi gửi xe thường ở chân đồi gần cổng; có bảng chỉ dẫn lối lên. Nhà vệ sinh nằm phía sân dưới và một số điểm trên đường bậc (có thể thay đổi theo thời điểm). Hỏi thêm bảo vệ/chư tăng nếu cần hỗ trợ.", "Parking is usually at the foot of the hill near the gate, with signs marking the way up. Restrooms are in the lower courtyard and at a few points along the steps (may change over time). Ask a guard or a monk if you need help."),

            gallery(List.of(
                img("https://cdn2.tuoitre.vn/thumb_w/1200/471584752817336320/2024/11/30/z6082977223128f21e0a8b82824cf52ecdf04be26c86ac-173293337597782060114.jpg", "Tượng Phật Trắng trên đồi", "White Buddha statue on the hill", false, 0),
                img("https://static.vinwonders.com/2022/04/chua-long-son-3.jpg", "Cổng tam quan", "The three-entrance gate", false, 1),
                img("https://static.vinwonders.com/2022/04/chua-long-son-8.jpg", "Không gian chánh điện", "The main hall", false, 2)
            )),
            quote("“Lên vài bậc thang, xuống một nhịp lòng – Long Sơn cho ta bình yên.”", "\"Climb a few steps, settle the heart – Long Son gives us peace.\""),
            divider(),
            mapBlock(new double[]{109.1787, 12.2486})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-trinh-ton-giao").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Tượng Phật Trắng", "White Buddha statue")).slug("tuong-phat-trang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("View toàn cảnh Nha Trang", "Nha Trang panorama view")).slug("view-toan-canh-nha-trang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 3) Bai Dai (Cam Ranh) */
  private static PlaceDoc poiBaiDaiCamRanh() {
    String slug = "bai-dai-cam-ranh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Đường chân trời Bãi Dài (Cam Ranh)", "Bai Dai Horizon Beach (Cam Ranh)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Dải cát mịn dài bất tận, nước trong xanh – điểm tắm biển đẹp gần Nha Trang.", "An endless strip of fine sand and clear blue water – a beautiful swimming beach near Nha Trang."))
        .description(of("Thuộc vịnh Cam Ranh, cách trung tâm ~25–30km. Biển thoải, sóng dịu; nhiều resort mới, quán ven biển chill.", "Part of Cam Ranh Bay, about 25–30km from the center. A gently sloping beach with mild waves, many new resorts and chill seaside cafés."))
        .addressLine(of("Nguyễn Tất Thành, Cam Lâm – Cam Ranh, Khánh Hòa", "Nguyen Tat Thanh, Cam Lam – Cam Ranh, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2150, 11.9980})
        .vibes(List.of("relaxing", "nature", "peaceful"))
        .atmosphere(of("coastal-resort", "coastal-resort"))
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
        .photographyScore(90)
        .romanticScore(85)
        .localExperienceScore(60)
        .images(withCover(
            img("https://statics.vinpearl.com/bai-dai-cam-lam-04_1664546932.jpg", "Bãi Dài xanh ngắt", "Deep-blue Bai Dai", true, 0),
            img("https://statics.vinpearl.com/bai-dai-cam-lam-06_1664546916.jpg", "Đường ven biển", "The coastal road", false, 1),
            img("https://statics.vinpearl.com/bai-dai-cam-lam-05_1664546924.jpg", "Khu vui chơi Sealife", "Sealife play area", false, 2),
            img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/29_zing.jpeg", "Góc chụp từ trên cao", "An aerial view", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/bai-dai-nha-trang-co-mot-thien-duong-binh-di-noi-ha-gioi-voi-bien-xanh-cat-trang-quanh-nam-song-xo-bo-5-1622489585.jpg", "Chòi nghỉ ven biển", "Seaside huts", false, 4),
            img("https://duan-sungroup.com/wp-content/uploads/2022/10/hoang-hon-tho-mong-tai-bai-dai.jpg", "Hoàng hôn Bãi Dài", "Sunset at Bai Dai", false, 5)
        ))
        .content(List.of(
            heading("Bãi Dài – đường chân trời màu ngọc", "Bai Dai – a jade-colored horizon"),
            quote("""
                    Trưa rải tơ nắng mỏng,
                    gió gỡ sóng lên bờ;
                    cát trắng ngân theo bước,
                    chân trời kéo dài hơn.
                """, """
                    Noon spreads thin threads of sun,
                    the wind unspools waves to shore;
                    white sand hums with each step,
                    the horizon stretches further on.
                """),
            paragraph("Thuộc vịnh Cam Ranh và cách trung tâm Nha Trang khoảng 25–30km, Bãi Dài nổi tiếng với mặt nước trong, dải cát mịn và độ thoai thoải lý tưởng để tắm biển – đi dạo. Nhịp điệu ở đây mộc mạc, chủ yếu là tiếng gió và sóng, điểm xuyết những chòi nghỉ, quán hải sản ven bờ và vài khu vui chơi – resort mới.", "Part of Cam Ranh Bay and about 25–30km from central Nha Trang, Bai Dai is famous for its clear water, fine sand, and a gentle slope ideal for swimming and strolling. The mood here is rustic, mostly the sound of wind and waves, dotted with rest huts, seaside seafood eateries, and a few new play areas and resorts."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• 06:00–09:00: biển êm, nắng xiên nhẹ, hợp chạy bộ – bơi gần bờ, săn nước trong.", "• 06:00–09:00: calm sea, gentle slanting light, good for jogging, swimming near shore, and chasing clear water."),
            paragraph("• 16:30–18:30: ‘giờ vàng’ và ‘giờ xanh’ liên tiếp, ánh chiều phản chiếu lên mặt nước rất đẹp, phù hợp ngồi chill dưới hàng dừa và chụp hoàng hôn.", "• 16:30–18:30: the 'golden hour' and 'blue hour' in succession, with evening light reflecting beautifully on the water – great for chilling under the palms and shooting the sunset."),
            paragraph("• Ngày gió nhỏ (swell thấp): thử SUP/board gần bờ; ngày sóng lớn: ưu tiên đi dạo – chụp ảnh, hạn chế bơi xa.", "• Calm days (low swell): try SUP/boarding near shore; rough days: focus on strolling and photos, and avoid swimming far out."),

            imageBlock(img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/17_zing.jpeg", "Trải dài cát trắng", "A long stretch of white sand", false, 0)),

            heading("Gợi ý hoạt động", "Activity suggestions"),
            paragraph("• Tắm biển nông – an toàn: chọn khu có cờ báo hiệu/cứu hộ; với trẻ nhỏ, luôn giữ trong tầm tay và dùng áo phao.", "• Safe, shallow swimming: choose an area with warning flags or lifeguards; with young children, always keep them within reach and use life vests."),
            paragraph("• SUP/chèo ván: đi sát bờ, quan sát hướng gió; cố định dây buộc chân (leash) và không rời ván khi có sóng đẩy.", "• SUP/paddleboarding: stay close to shore, watch the wind direction; secure the leash and don't leave the board when waves push in."),
            paragraph("• Dạo bộ – nhặt ‘vân sóng’: chụp macro vệt bọt/hoa văn cát; chạy nhẹ dọc bờ 1–2km để cảm nhận độ thoai thoải của bãi.", "• Strolling – collecting 'wave patterns': shoot macros of foam streaks and sand patterns; jog gently 1–2km along the shore to feel the beach's gentle slope."),
            paragraph("• Chill quán ven biển: gọi nước dừa/món nướng đơn giản, ngồi ngắm mặt trời hạ qua rặng dừa.", "• Chilling at a seaside café: order coconut water or a simple grilled dish, and watch the sun sink behind the palm grove."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Toàn cảnh: đặt đường chân trời ở 1/3 khung để ‘ăn’ nhiều trời – mây; chọn tiêu cự 24–35mm cho ảnh rộng vừa phải, ít méo.", "• Panorama: place the horizon at the lower third to capture more sky and clouds; use 24–35mm for a moderately wide, low-distortion shot."),
            paragraph("• Tiền cảnh cát: dùng dấu chân/vân sóng dẫn mắt; khép khẩu f/8–f/11 để nét từ gần tới xa.", "• Sand foreground: use footprints or wave patterns to lead the eye; stop down to f/8–f/11 for sharpness from near to far."),
            paragraph("• Hoàng hôn: đứng lệch 30–45° so với bờ để bắt vệt sóng xéo; đợi khoảnh khắc sóng rút để lộ cát ướt phản gương.", "• Sunset: stand at a 30–45° angle to the shore to catch diagonal wave streaks; wait for the waves to recede and reveal mirror-like wet sand."),
            paragraph("• Người & biển: tránh đặt chủ thể quá sát nền trời; hạ thấp máy để mặt biển ‘dày’ hơn, giảm hiện tượng ‘đầu người chạm đường chân trời’.", "• People & sea: avoid placing the subject too close to the skyline; lower the camera so the sea looks 'thicker', reducing the 'head touching the horizon' effect."),

            heading("Ăn uống & lưu ý thực tế", "Food & practical notes"),
            paragraph("• Hải sản: hỏi rõ giá theo kg – cách chế biến; gọi phần vừa để thử nhiều món (tôm, mực, nghêu, sò).", "• Seafood: ask clearly about the price per kg and the preparation; order moderate portions to try several dishes (shrimp, squid, clams, scallops)."),
            paragraph("• Đồ mang theo: kem chống nắng, nón, khăn mỏng, nước uống; túi rác nhỏ tự gom bỏ đúng nơi.", "• What to bring: sunscreen, a hat, a thin towel, drinking water; a small trash bag to collect and dispose of waste properly."),
            paragraph("• Sứa & nắng gắt: ngày nắng cao, có thể gặp sứa nhỏ; nếu châm nhẹ, rửa bằng nước biển (không nước ngọt ngay), theo dõi phản ứng da.", "• Jellyfish & harsh sun: on hot days you may encounter small jellyfish; if lightly stung, rinse with seawater (not fresh water right away) and watch for skin reactions."),

            heading("An toàn bờ biển", "Beach safety"),
            paragraph("Bãi thoai thoải nhưng vẫn có dòng chảy rút (rip) theo thời điểm. Dấu hiệu: mặt nước ‘bằng phẳng’ khác lạ giữa vùng sóng, bọt ít và có cảm giác kéo ra ngoài. Nếu bị cuốn: bình tĩnh, bơi chéo song song bờ 10–20m để thoát dòng rồi mới bơi vào; kêu gọi cứu hộ khi cần.", "The beach slopes gently but rip currents can appear at times. Signs: an oddly 'flat' patch of water amid the surf, little foam, and a pulling sensation outward. If caught: stay calm, swim diagonally parallel to shore for 10–20m to escape the current before swimming in; call for a lifeguard if needed."),
            paragraph("Không để đồ quý trên bãi khi xuống nước; giữ khoảng cách an toàn với ca nô/khu vui chơi có phao kéo.", "Don't leave valuables on the beach while in the water; keep a safe distance from boats and tow-tube play areas."),

            heading("Lịch trình gợi ý 2–3 giờ", "Suggested 2–3 hour itinerary"),
            paragraph("• 0’–20’: gửi xe, khởi động, đi bộ tìm góc chụp toàn cảnh.", "• 0'–20': park, warm up, walk to find a panoramic shot."),
            paragraph("• 20’–70’: tắm biển/SUP gần bờ (ngày sóng nhỏ) hoặc dạo – chụp macro vân sóng (ngày sóng lớn).", "• 20'–70': swim/SUP near shore (calm days) or stroll and shoot macros of wave patterns (rough days)."),
            paragraph("• 70’–110’: ghé quán ven biển, gọi hải sản nhẹ/nước dừa, ngồi ngắm chiều.", "• 70'–110': stop at a seaside café, order light seafood or coconut water, and watch the afternoon."),
            paragraph("• 110’–150’: chụp hoàng hôn, bắt vệt sóng, thu dọn rác, rời bãi khi trời chạng vạng.", "• 110'–150': shoot the sunset, catch the wave streaks, clean up trash, and leave the beach at dusk."),

            heading("Kết hợp điểm lân cận (cung Nguyễn Tất Thành)", "Combine with nearby spots (Nguyen Tat Thanh route)"),
            paragraph("• Sealife/khu trò chơi trên biển: thử nhà phao/chụp ảnh màu sắc cho nhóm gia đình.", "• Sealife/sea play area: try the inflatables and colorful photos for family groups."),
            paragraph("• Các viewpoint đường ven biển: dừng ngắn ở vài điểm cao nhìn toàn vịnh Cam Ranh.", "• Coastal-road viewpoints: stop briefly at a few high points overlooking all of Cam Ranh Bay."),
            paragraph("• Các resort/khu café ven bờ: chọn quán có sân trước nhìn thẳng ra biển để ngắm ‘giờ xanh’ sau hoàng hôn.", "• Seaside resorts/cafés: choose a spot with a front terrace facing the sea to watch the 'blue hour' after sunset."),

            gallery(List.of(
                img("https://statics.vinpearl.com/bai-dai-cam-lam-04_1664546932.jpg", "Biển xanh – cát trắng", "Blue sea, white sand", false, 0),
                img("https://statics.vinpearl.com/bai-dai-cam-lam-06_1664546916.jpg", "Cung đường ven biển", "The coastal route", false, 1),
                img("https://photo.znews.vn/w1200/Uploaded/nugzrd/2021_08_05/29_zing.jpeg", "Góc flycam Bãi Dài", "A drone view of Bai Dai", false, 2)
            )),
            quote("“Đường chân trời ở Bãi Dài dường như… dài thêm một chút.”", "\"The horizon at Bai Dai seems to stretch... just a little longer.\""),
            divider(),
            mapBlock(new double[]{109.2150, 11.9980})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển", "Beach")).slug("bai-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Bãi Dài Cát Mịn", "Fine-sand Bai Dai")).slug("bai-dai-cat-min").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Vịnh Cam Ranh", "Cam Ranh Bay")).slug("vinh-cam-ranh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 4) Dốc Lết (Ninh Hòa) */
  private static PlaceDoc poiDocLet() {
    String slug = "doc-let-beach";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Biển Dốc Lết (Ninh Hòa)", "Doc Let Beach (Ninh Hoa)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Bãi biển cát trắng, nước nông trong, thích hợp gia đình.", "A white-sand beach with shallow, clear water – great for families."))
        .description(of("Cách Nha Trang ~50km về phía Bắc, nổi tiếng bãi nông, vệt cát trắng mịn, hàng dương râm mát.", "About 50km north of Nha Trang, famous for its shallow shore, fine white sand, and shady casuarina rows."))
        .addressLine(of("Ninh Hải, Ninh Hòa, Khánh Hòa", "Ninh Hai, Ninh Hoa, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2320, 12.5820})
        .vibes(List.of("relaxing", "nature", "peaceful"))
        .atmosphere(of("coastal-open", "coastal-open"))
        .travelStyle(List.of("casual", "photography"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(80)
        .romanticScore(75)
        .localExperienceScore(70)
        .images(withCover(
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang.jpg", "Bờ biển Dốc Lết", "Doc Let shoreline", true, 0),
            img("https://static.vinwonders.com/2022/04/mQs99b7G-doc-let-nha-trang-6.jpg", "Hàng dừa – hàng dương", "Rows of palms and casuarinas", false, 1),
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-7.jpg", "Địa điểm check-in", "A check-in spot", false, 2),
            img("https://static.vinwonders.com/production/ghe-tham-lang-chai-ninh-thuy.jpg", "Thuyền thúng ven bờ", "Basket boats near the shore", false, 3),
            img("https://static.vinwonders.com/production/ruong-muoi-hon-khoi.jpg", "Bình minh vàng rượi", "A golden sunrise", false, 4),
            img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-9.jpg", "Chiều yên ả", "A peaceful afternoon", false, 5)
        ))
        .content(List.of(
            heading("Dốc Lết – êm đềm phía Bắc vịnh", "Doc Let – serenity at the north of the bay"),
            quote("""
                    Cát trắng ru bàn chân,
                    sóng vỗ nhịp rất khẽ;
                    gió thổi mùi mặn trong,
                    một ngày trôi êm ả.
                """, """
                    White sand cradles bare feet,
                    waves lap a gentle beat;
                    the wind carries a clean salt scent,
                    a day drifts by in peace.
                """),
            paragraph("Cách trung tâm Nha Trang khoảng 50km về phía Bắc, Dốc Lết nổi tiếng với bãi thoai thoải, nước nông trong và dải cát mịn kéo dài dưới tán dương rì rào. Không gian yên ả, hợp gia đình có trẻ nhỏ, nhóm bạn thích tắm biển nhẹ nhàng – đi dạo – picnic.", "About 50km north of central Nha Trang, Doc Let is famous for its gently sloping shore, shallow clear water, and a long stretch of fine sand beneath rustling casuarinas. The calm setting suits families with small children and groups of friends who enjoy easy swimming, strolling, and picnics."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• 06:00–09:00: trời trong, nước mát, thích hợp bơi nhẹ – chạy bộ dọc bờ.", "• 06:00–09:00: clear skies, cool water, good for easy swimming and jogging along the shore."),
            paragraph("• 16:30–18:00: ‘giờ vàng’ dịu nắng, đón hoàng hôn, chụp ảnh phản gương trên cát ướt.", "• 16:30–18:00: the softly lit 'golden hour', for catching the sunset and mirror-like shots on wet sand."),
            paragraph("• Giữa trưa: ưu tiên trú bóng râm hàng dương, bổ sung nước – khoáng, hạn chế tắm lâu ngoài nắng gắt.", "• Midday: take shelter in the casuarina shade, replenish water and minerals, and avoid long swims under the harsh sun."),

            imageBlock(img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-2.jpg", "Nước nông trong vắt", "Crystal-clear shallow water", false, 0)),

            heading("Gợi ý cho gia đình", "Tips for families"),
            paragraph("• Trẻ nhỏ: áo bơi tay dài, nón, kính bơi; chỉ bơi khu nước nông ngang gối – hông, luôn trong tầm tay người lớn.", "• Young children: long-sleeve swimwear, a hat, and goggles; only swim in shallow water up to knee or hip height, always within an adult's reach."),
            paragraph("• Nhịp hoạt động: chia phiên 20–30 phút (tắm) ↔ 10–15 phút (nghỉ – uống nước) để tránh kiệt sức, say nắng.", "• Pacing: alternate 20–30 minutes (swimming) with 10–15 minutes (rest and water) to avoid exhaustion and heatstroke."),
            paragraph("• Picnic nhẹ: bánh mì, trái cây đã gọt, túi rác riêng để thu gom trước khi rời bãi.", "• Light picnic: bread, pre-cut fruit, and a separate trash bag to collect waste before leaving the beach."),

            heading("Gợi ý hoạt động", "Activity suggestions"),
            paragraph("• Tắm biển – đi dạo: cảm nhận độ thoai thoải hiếm có, dễ chịu cho mọi lứa tuổi.", "• Swimming and strolling: feel the rare gentle slope, comfortable for all ages."),
            paragraph("• Chèo SUP gần bờ (ngày sóng nhỏ): dùng leash buộc chân, không đi quá xa các phao giới hạn.", "• SUP paddling near shore (calm days): use an ankle leash and don't go beyond the boundary buoys."),
            paragraph("• Ngắm bình minh/hoàng hôn: chọn đoạn bờ thông thoáng, đợi sóng rút để mặt cát lấp lánh phản gương.", "• Watching sunrise/sunset: pick an open stretch of shore and wait for the waves to recede so the sand glistens like a mirror."),

            heading("Ăn uống & dịch vụ", "Food & services"),
            paragraph("Các hàng quán ven biển phục vụ hải sản đơn giản (tôm, mực, nghêu…). Hỏi rõ giá theo kg – cách chế biến; gọi phần vừa để thử nhiều món. Dịch vụ ghế – dù – tắm nước ngọt có tại nhiều điểm dọc bãi; nên hỏi giá trước khi dùng.", "The seaside eateries serve simple seafood (shrimp, squid, clams…). Ask clearly about the price per kg and preparation; order moderate portions to try several dishes. Chair, umbrella, and freshwater-rinse services are available at many points along the beach; ask the price before using them."),

            heading("Điểm gần kề để kết hợp", "Nearby spots to combine"),
            paragraph("• Làng chài Ninh Thủy: ngắm thúng chài, đời sống ngư dân; mua hải sản tươi theo thời điểm.", "• Ninh Thuy fishing village: watch the basket boats and fishermen's life; buy fresh seafood when available."),
            paragraph("• Ruộng muối Hòn Khói: đi sớm ngắm bình minh vàng rượi trên ô muối, chụp ảnh lao động mùa vụ.", "• Hon Khoi salt fields: go early to see the golden sunrise over the salt ponds and photograph the seasonal labor."),
            paragraph("Hai điểm đều thuận đường đi – về, giúp lịch trình đa dạng hơn trong nửa ngày.", "Both spots are conveniently on the way there and back, adding variety to a half-day itinerary."),

            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & small tips"),
            paragraph("• Toàn cảnh: đặt đường chân trời ở 1/3 khung để ‘ăn’ nhiều trời xanh – mây.", "• Panorama: place the horizon at the lower third to capture more blue sky and clouds."),
            paragraph("• Tiền cảnh: dùng hàng dương, thuyền thúng, vân sóng làm dẫn mắt; khép khẩu f/8–f/11 để ảnh nét đều.", "• Foreground: use casuarinas, basket boats, or wave patterns to lead the eye; stop down to f/8–f/11 for even sharpness."),
            paragraph("• Hoàng hôn: đứng chếch 30–45° so với bờ, chụp liên tiếp khi sóng rút để bắt vệt bọt trắng.", "• Sunset: stand at a 30–45° angle to the shore and shoot in bursts as the waves recede to catch the white foam streaks."),

            heading("An toàn & ứng xử", "Safety & etiquette"),
            paragraph("Bãi nông nhưng đôi lúc có dòng chảy ngược (rip). Dấu hiệu: mặt nước phẳng khác thường giữa vùng sóng, bọt ít, cảm giác kéo ra xa. Nếu gặp: bình tĩnh, bơi song song bờ 10–20m để thoát dòng rồi mới bơi vào. Không để đồ quý trên bãi; không xả rác, hạn chế loa công suất lớn.", "The beach is shallow but rip currents appear at times. Signs: an unusually flat patch of water amid the surf, little foam, a pulling sensation outward. If caught: stay calm, swim parallel to shore for 10–20m to escape the current before swimming in. Don't leave valuables on the beach, don't litter, and keep loud speakers to a minimum."),

            heading("Lịch trình gợi ý 2–3 giờ", "Suggested 2–3 hour itinerary"),
            paragraph("• 0’–20’: gửi xe, khởi động, đi dọc bờ tìm góc chụp.", "• 0'–20': park, warm up, walk along the shore to find a shot."),
            paragraph("• 20’–70’: tắm biển/SUP gần bờ (ngày sóng nhỏ) hoặc đi dạo – chụp vân sóng (ngày sóng lớn).", "• 20'–70': swim/SUP near shore (calm days) or stroll and shoot wave patterns (rough days)."),
            paragraph("• 70’–110’: ghé quán ven biển, gọi hải sản nhẹ – nước dừa, nghỉ ngơi.", "• 70'–110': stop at a seaside café, order light seafood and coconut water, and rest."),
            paragraph("• 110’–150’: chụp hoàng hôn, thu gom rác, rửa nước ngọt và rời bãi.", "• 110'–150': shoot the sunset, collect trash, rinse off with fresh water, and leave the beach."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/04/doc-let-nha-trang.jpg", "Bờ biển Dốc Lết", "Doc Let shoreline", false, 0),
                img("https://static.vinwonders.com/2022/04/doc-let-nha-trang-2.jpg", "Làn nước xanh mát", "Cool blue water", false, 1),
                img("https://static.vinwonders.com/production/ghe-tham-lang-chai-ninh-thuy.jpg", "Thúng chài ven bờ", "Basket boats near the shore", false, 2)
            )),
            quote("“Sóng nhỏ, cát mịn – Dốc Lết hợp cho một ngày của gia đình.”", "\"Small waves, fine sand – Doc Let is perfect for a family day.\""),
            divider(),
            mapBlock(new double[]{109.2320, 12.5820})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển", "Beach")).slug("bai-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biển Nông An Toàn", "Safe shallow beach")).slug("bien-nong-an-toan").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Hàng Dương Râm Mát", "Shady casuarina rows")).slug("hang-duong-ram-mat").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 5) Đảo Điệp Sơn (Con đường trên biển) */
  private static PlaceDoc poiDiepSon() {
    String slug = "diep-son-island";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Đảo Điệp Sơn (Con đường trên biển)", "Diep Son Island (Path Across the Sea)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Dải cát nổi nối 3 đảo nhỏ, check-in “con đường trên biển” độc đáo.", "A sandbar linking three small islands – the unique 'path across the sea' check-in."))
        .description(of("Thuộc vịnh Vân Phong, nước xanh trong. Lưu ý thủy triều; nên đi theo tour/cano địa phương.", "Part of Van Phong Bay, with clear blue water. Mind the tides; go with a local tour or speedboat."))
        .addressLine(of("Vịnh Vân Phong, Vạn Ninh, Khánh Hòa", "Van Phong Bay, Van Ninh, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2350, 12.6350})
        .vibes(List.of("nature", "peaceful", "adventurous"))
        .atmosphere(of("island-coastal", "island-coastal"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("explore", "photography", "relax"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(240)
        .photographyScore(95)
        .romanticScore(80)
        .localExperienceScore(75)
        .images(withCover(
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Con-duong-giua-bien.png", "Con đường trên biển – Điệp Sơn", "The path across the sea – Diep Son", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/dao-diep-son.png", "Cát trắng nổi giữa biển", "White sand rising amid the sea", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Dao-Diep-Son-nuoc-xanh.png", "Nước xanh ngọc", "Jade-green water", false, 2),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Toan-canh-Dao-Diep-Son.png", "View flycam", "A drone view", false, 3),
            img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/cheo-thuyen-kayak-diep-son.png", "Thuyền địa phương", "Local boats", false, 4),
            img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/thu-gian-tam-bien.jpg", "Góc chụp “đi trên biển”", "A 'walking on the sea' photo angle", false, 5)
        ))
        .content(List.of(
            heading("Điệp Sơn – dải cát giữa vịnh", "Diep Son – a sandbar in the bay"),
            quote("""
                    Nước rút lộ đường mỏng,
                    cát nối những đảo xa;
                    bước chạm làn xanh ngọc,
                    biển mở cửa cho ta.
                """, """
                    The tide retreats, baring a thin path,
                    sand linking distant isles;
                    a step touches jade-green water,
                    the sea opens its door for us.
                """),
            paragraph("Nằm trong vịnh Vân Phong thuộc Vạn Ninh, Điệp Sơn nổi tiếng bởi dải cát nổi uốn lượn nối liền 3 đảo nhỏ, tạo nên trải nghiệm ‘đi trên biển’ độc đáo hiếm nơi có. Những ngày trời trong, làn nước xanh ngọc nhìn thấy đáy, gió nhẹ, sóng êm – rất hợp cho một buổi tham quan nửa ngày kết hợp tắm biển và chụp ảnh.", "Located in Van Phong Bay in Van Ninh, Diep Son is famous for a winding sandbar linking three small islands, creating a rare 'walking on the sea' experience. On clear days the jade-green water reveals the bottom, the breeze is light, and the waves are calm – perfect for a half-day visit combining swimming and photography."),

            heading("Thủy triều & thời điểm đẹp", "Tides & the best timing"),
            paragraph("Trải nghiệm ‘con đường trên biển’ phụ thuộc mạnh vào mực nước. Lý tưởng là khung giờ nước rút (thường vào buổi sáng muộn hoặc chiều muộn tùy ngày). Khi triều lên, dải cát có thể chìm 10–50cm; vẫn có thể đi nhưng nên mặc áo phao và đi thành hàng theo hướng dẫn viên (HDV).", "The 'path across the sea' experience depends heavily on the water level. The ideal window is at low tide (usually late morning or late afternoon depending on the day). At high tide the sandbar may be submerged by 10–50cm; you can still walk it, but wear a life vest and proceed single-file following the guide."),
            paragraph("Gợi ý khung giờ: 07:30–10:00 hoặc 15:30–17:30 để có ánh sáng êm, dễ chụp và đỡ nắng. Tránh giữa trưa nắng gắt, đặc biệt vào mùa khô.", "Suggested windows: 07:30–10:00 or 15:30–17:30 for soft light, easier photos, and less sun. Avoid the harsh midday sun, especially in the dry season."),

            heading("Cách đi & lịch trình nửa ngày", "How to go & a half-day itinerary"),
            paragraph("Xuất phát từ đất liền bằng cano/thuyền địa phương; nên đi tour nhỏ 6–12 người để linh hoạt thời gian theo bảng triều. Lộ trình gợi ý: bến cano → đảo A (khởi điểm dải cát) → đi bộ dọc ‘con đường trên biển’ → tắm biển nhẹ gần bờ → nghỉ chân, thưởng thức hải sản đơn giản → di chuyển ngắm vịnh bằng cano → quay về bờ.", "Depart from the mainland by local speedboat or boat; a small tour of 6–12 people is best for flexible timing around the tide table. Suggested route: boat pier → island A (start of the sandbar) → walk along the 'path across the sea' → a light swim near shore → rest and enjoy simple seafood → cruise the bay by speedboat → return to shore."),
            imageBlock(img("https://booking.muongthanh.com/upload_images/images/2024%20-%20Nh/kham-pha-cuoc-song-tren-dao-diep-son.jpg", "Đời sống bình dị trên đảo Điệp Sơn", "Simple life on Diep Son Island", false, 0)),

            heading("An toàn trên biển", "Safety at sea"),
            paragraph("• Luôn mang dép/giày nước để tránh vỏ sò, đá vôi sắc. Không chạy nhảy, không dẫm lên rạn/san hô non.", "• Always wear water shoes to avoid sharp shells and limestone. Don't run or step on reefs or young coral."),
            paragraph("• Nếu nước vượt mắt cá chân, mặc áo phao; trẻ nhỏ phải có người lớn nắm tay. Tránh đứng chắn luồng cano và tuân thủ hướng dẫn của HDV.", "• If the water rises above your ankles, wear a life vest; small children must be held by an adult. Don't block the speedboat lane and follow the guide's instructions."),
            paragraph("• Trời chuyển mây đen – gió mạnh: dừng di chuyển trên dải cát, quay về điểm cao/đảo gần nhất theo hiệu lệnh.", "• If dark clouds gather with strong wind: stop moving along the sandbar and return to the nearest high ground or island on the signal."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Từ cao: lên gò/điểm cao trên đảo để lấy trọn đường cát cong và hai mảng nước xanh hai bên.", "• From above: climb a mound or high point on the island to capture the whole curving sandbar and the blue water on either side."),
            paragraph("• Trên dải cát: đi lệch 30–45° so với trục đường để thấy rõ độ cong; đợi sóng rút để bọt trắng vẽ tiền cảnh.", "• On the sandbar: walk at a 30–45° angle to the path to show its curve clearly; wait for the waves to recede so white foam frames the foreground."),
            paragraph("• Màu nước: mặc trang phục tông trắng/nhạt để nổi bật trên nền xanh; khép khẩu f/8–f/11 cho ảnh phong cảnh nét đều.", "• Water color: wear white or light tones to stand out against the blue; stop down to f/8–f/11 for evenly sharp landscapes."),
            paragraph("• Flycam: chỉ bay khi thời tiết tốt, giữ khoảng cách an toàn khỏi đám đông – hỏi HDV/khu vực cho phép trước khi cất cánh.", "• Drone: fly only in good weather, keep a safe distance from crowds – ask the guide or check whether the area is permitted before takeoff."),

            heading("Ăn uống & dịch vụ", "Food & services"),
            paragraph("Hải sản địa phương theo mùa (tôm – mực – ốc…) chế biến đơn giản; hỏi rõ giá theo kg/cách chế biến trước khi gọi. Nước uống mang thêm từ bờ; hạn chế mang ly nhựa dùng một lần. Một số tour có kèm bữa trưa nhẹ trên đảo hoặc tại nhà bè – kiểm tra trước khi đặt.", "Seasonal local seafood (shrimp, squid, snails…) simply prepared; ask about the price per kg and preparation before ordering. Bring extra drinking water from shore; minimize single-use plastic cups. Some tours include a light lunch on the island or at a floating raft house – check before booking."),

            heading("Ứng xử & bền vững", "Etiquette & sustainability"),
            paragraph("Không bẻ san hô, không nhặt vỏ còn sinh vật bên trong; không xả rác, gom rác mang về bờ. Dùng kem chống nắng ‘reef-safe’ nếu có. Tôn trọng sinh hoạt ngư dân, không tự ý vào khu vực cấm/tư gia.", "Don't break coral or collect shells with creatures still inside; don't litter, and carry your trash back to shore. Use reef-safe sunscreen if possible. Respect the fishermen's daily life and don't enter restricted or private areas."),

            heading("Lịch trình gợi ý 3–4 giờ", "Suggested 3–4 hour itinerary"),
            paragraph("• 0’–30’: di chuyển cano ra đảo, nghe HDV phổ biến an toàn – lịch triều.", "• 0'–30': take the speedboat to the island and hear the guide's safety and tide briefing."),
            paragraph("• 30’–90’: dạo ‘con đường trên biển’, chụp ảnh các góc cong – toàn cảnh.", "• 30'–90': stroll the 'path across the sea' and photograph the curves and panorama."),
            paragraph("• 90’–150’: tắm biển nhẹ gần bờ, nghỉ chân – dùng bữa hải sản đơn giản.", "• 90'–150': a light swim near shore, rest, and a simple seafood meal."),
            paragraph("• 150’–210’: đi cano vòng vịnh ngắm cảnh, về lại bờ khi trời chuyển ‘giờ xanh’.", "• 150'–210': cruise the bay by speedboat, returning to shore as the 'blue hour' sets in."),

            heading("Lưu ý thực tế", "Practical notes"),
            paragraph("Mang mũ rộng vành, áo khoác mỏng chống nắng, nước uống, túi chống nước cho điện thoại/máy ảnh. Sóng – gió thay đổi nhanh theo mùa; luôn kiểm tra dự báo thời tiết, lịch triều trước ngày đi và tuân thủ chỉ dẫn của HDV.", "Bring a wide-brimmed hat, a thin sun jacket, drinking water, and a waterproof pouch for your phone or camera. Waves and wind change quickly by season; always check the weather forecast and tide table before your trip and follow the guide's instructions."),

            gallery(List.of(
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Con-duong-giua-bien.png", "Con đường giữa biển", "The path amid the sea", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Dao-Diep-Son-nuoc-xanh.png", "Nước xanh ngọc", "Jade-green water", false, 1),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/Toan-canh-Dao-Diep-Son.png", "Toàn cảnh vịnh Vân Phong", "Panorama of Van Phong Bay", false, 2)
            )),
            quote("“Khi thủy triều rút, biển mở ra lối đi cho kẻ mộng mơ.”", "\"When the tide goes out, the sea opens a path for dreamers.\""),
            divider(),
            mapBlock(new double[]{109.2350, 12.6350})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển & Đảo", "Beaches & Islands")).slug("bai-bien-dao").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Con đường trên biển", "Path across the sea")).slug("con-duong-tren-bien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Vịnh Vân Phong", "Van Phong Bay")).slug("vinh-van-phong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Phụ thuộc thủy triều", "Tide-dependent")).slug("phu-thuoc-thuy-trieu").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 6) Suoi Ba Ho (Ba Ho Waterfalls) */
  private static PlaceDoc poiBaHoWaterfalls() {
    String slug = "ba-ho-waterfalls";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Suối Ba Hồ (Ba Hồ Waterfalls)", "Ba Ho Stream (Ba Ho Waterfalls)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Tổ hợp 3 hồ – thác giữa rừng, trek nhẹ, tắm suối mát lạnh.", "A trio of pools and waterfalls in the forest – an easy trek and a cool stream swim."))
        .description(of("Cách trung tâm ~25km về Bắc. Đường mòn có biển chỉ dẫn; nên mang giày bám, cẩn thận đoạn đá ướt.", "About 25km north of the center. The trail has signposts; wear grippy shoes and be careful on wet rocks."))
        .addressLine(of("Ninh Ích, Ninh Hòa, Khánh Hòa", "Ninh Ich, Ninh Hoa, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2160, 12.4170})
        .vibes(List.of("nature", "peaceful", "adventurous"))
        .atmosphere(of("rainforest-waterfall", "rainforest-waterfall"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "relax"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(85)
        .romanticScore(70)
        .localExperienceScore(75)
        .images(withCover(
            img("https://statics.vinpearl.com/ba-ho-nha-trang-7_1628260967.jpg", "Hồ – thác giữa rừng", "Pools and waterfalls in the forest", true, 0),
            img("https://static.vinwonders.com/production/suoi-ba-ho-1-1.jpg", "Làn nước xanh trong", "Clear blue water", false, 1),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-14-1-e1534474709261.jpg", "Đường mòn treo biển", "A signposted trail", false, 2),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-11-e1534474677178.jpg", "Nhảy đá – tắm suối", "Rock-hopping and stream swimming", false, 3),
            img("https://static.vinwonders.com/2022/03/suoi-ba-ho-6.jpg", "Các trò chơi vui nhộn", "Fun activities", false, 4),
            img("https://static.vinwonders.com/2022/03/suoi-ba-ho-5.jpg", "Không khí mát lành", "Fresh, cool air", false, 5)
        ))
        .content(List.of(
            heading("Ba Hồ – mát rượi giữa thiên nhiên", "Ba Ho – refreshingly cool in nature"),
            quote("""
                    Nắng rơi qua tán lá,
                    đá thở giữa làn trong;
                    nước hát theo triền suối,
                    mồ hôi hoá mây bồng.
                """, """
                    Sunlight falls through the leaves,
                    rocks breathe in the clear flow;
                    water sings down the stream,
                    sweat turns to drifting cloud.
                """),
            paragraph("Cách trung tâm Nha Trang khoảng 25km về phía Bắc, Suối Ba Hồ là tổ hợp 3 hồ – 3 bậc thác ẩn giữa rừng với màu nước xanh mát quanh năm. Lối mòn có biển chỉ dẫn rõ, thỉnh thoảng băng qua những phiến đá phủ rêu nên đi chậm, bám tay vịn. Phần thưởng là hồ nước trong veo, bóng cây rừng mát rượi và tiếng thác như một bản nhạc nền tự nhiên.", "About 25km north of central Nha Trang, Ba Ho Stream is a trio of pools and three waterfall tiers hidden in the forest, with cool blue water year-round. The trail is clearly signposted, occasionally crossing moss-covered boulders, so walk slowly and hold the railings. The reward is crystal-clear pools, cool forest shade, and the sound of waterfalls as a natural soundtrack."),

            heading("Thời điểm & nhịp trải nghiệm", "Timing & pace of the experience"),
            paragraph("• 07:00–10:00: không khí mát, nắng xiên qua tán rừng—dễ chịu để trek và chụp ảnh luồng sáng.", "• 07:00–10:00: cool air with light slanting through the canopy – pleasant for trekking and shooting light beams."),
            paragraph("• 15:30–17:30: bóng râm nhiều, nhiệt độ hạ, hợp tắm suối – ngồi thiền nghe tiếng nước.", "• 15:30–17:30: plenty of shade and cooler temperatures, good for stream swimming and sitting quietly by the water."),
            paragraph("• Tránh đi ngay sau mưa lớn: nước đổ mạnh, đá trơn; ưu tiên ngày khô ráo để bám đá tốt hơn.", "• Avoid going right after heavy rain: strong currents and slippery rocks; choose dry days for better grip on the rocks."),

            heading("Tuyến đường 3 hồ", "The three-pool route"),
            paragraph("• Hồ 1: gần nhất, đường dễ, mặt nước rộng—phù hợp dừng chân, ngâm mình, chụp toàn cảnh thác.", "• Pool 1: the nearest, with an easy path and a wide surface – good for resting, soaking, and shooting the whole waterfall."),
            paragraph("• Hồ 2: đoạn đá lớn hơn, cần bước chắc—đổi lại vắng hơn, nước sâu hơn, khung xanh đẹp.", "• Pool 2: larger rocks requiring sure footing – in return it's quieter, deeper, with a beautiful green setting."),
            paragraph("• Hồ 3: cảnh hoang sơ, lối đi có mỏm đá hẹp; chỉ nên đi khi thời tiết khô, còn đủ thể lực, luôn theo mũi tên chỉ dẫn.", "• Pool 3: wild scenery with narrow rocky ledges; only go in dry weather with enough energy, always following the directional arrows."),
            imageBlock(img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-15-e1534474585258.jpg", "Hồ số 1 – điểm dừng phổ biến", "Pool 1 – a popular stop", false, 0)),

            heading("Chuẩn bị & an toàn", "Preparation & safety"),
            paragraph("• Giày đế bám chống trượt, dép/giày nước để xuống hồ; balo gọn, túi khô cho điện thoại/giấy tờ.", "• Non-slip grippy shoes, plus water shoes for the pools; a compact backpack and a dry bag for your phone and documents."),
            paragraph("• 1–2 chai nước nhỏ, vài gói năng lượng; xịt chống côn trùng, khăn mỏng.", "• One or two small water bottles, a few energy snacks; insect repellent and a thin towel."),
            paragraph("• Không nhảy từ vách cao nếu chưa kiểm tra độ sâu/vật cản; quan sát dòng chảy trước khi xuống nước.", "• Don't jump from high ledges without checking the depth and obstacles; observe the current before getting in."),
            paragraph("• Đi theo nhóm 2+ người; thông báo thời gian dự kiến quay lại; luôn giữ trẻ nhỏ trong tầm mắt.", "• Go in groups of two or more; tell someone your expected return time; always keep children in sight."),

            heading("Chơi gì cho ‘đã’", "What to do for the full experience"),
            paragraph("• Tắm suối: chọn vũng nước tĩnh, không bơi sát mép đổ; giữ khoảng cách an toàn với người khác.", "• Stream swimming: choose still pools, don't swim near the falls' edge; keep a safe distance from others."),
            paragraph("• ‘Chill’ theo rừng: ngồi thiền 5–10 phút ở phiến đá khô, nghe tiếng lá – tiếng nước làm ‘metronome’.", "• Forest 'chilling': sit quietly for 5–10 minutes on a dry rock, letting the leaves and water be your 'metronome'."),
            paragraph("• Picnic nhẹ: chọn bờ đá phẳng, trải khăn nhỏ; ăn xong gom rác vào túi mang về cổng.", "• Light picnic: pick a flat rocky bank, spread a small mat; after eating, gather trash in a bag and carry it back to the gate."),
            paragraph("• Khu trò chơi (nếu có mở): chỉ tham gia tại khu vực cho phép và dùng dây đai/bảo hộ theo hướng dẫn.", "• Play area (if open): only participate in permitted zones and use harnesses/safety gear as instructed."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Mềm dòng thác: đặt tốc độ 1/8–1/4s, tựa máy lên tảng đá/balo làm ‘chân máy’; khép khẩu f/8–f/11.", "• Silky waterfalls: set the shutter to 1/8–1/4s, rest the camera on a rock or backpack as a 'tripod'; stop down to f/8–f/11."),
            paragraph("• Tia nắng xuyên tán: đi buổi sáng để bắt ‘khói nước’ lung linh khi hạt nước gặp nắng xiên.", "• Sunbeams through the canopy: go in the morning to catch the shimmering 'water mist' when droplets meet the slanting light."),
            paragraph("• Bố cục tầng: đứng chếch 30–45° để thấy rõ tầng thác phía sau và mảng xanh bao quanh.", "• Layered composition: stand at a 30–45° angle to show the waterfall tiers behind and the surrounding greenery."),
            paragraph("• Chân dung rừng: nền tường đá/tán lá tối—đo sáng vào da, tránh ngược sáng gắt giữa trưa.", "• Forest portraits: with dark rock walls or foliage, meter for the skin and avoid harsh backlight at midday."),

            heading("Lịch trình gợi ý 2–3 giờ", "Suggested 2–3 hour itinerary"),
            paragraph("• 0’–20’: gửi xe, khởi động nhẹ, vào lối mòn theo bảng chỉ dẫn.", "• 0'–20': park, warm up lightly, and enter the trail following the signs."),
            paragraph("• 20’–45’: tới Hồ 1—nghỉ, ngâm chân, chụp toàn cảnh.", "• 20'–45': reach Pool 1 – rest, soak your feet, shoot the panorama."),
            paragraph("• 45’–90’: đi tiếp tới Hồ 2—tắm suối, chụp close-up dòng chảy.", "• 45'–90': continue to Pool 2 – swim and shoot close-ups of the flow."),
            paragraph("• 90’–150’: cân nhắc lên Hồ 3 (nếu thời tiết khô và còn sức); quay đầu về trước khi trời chuyển.", "• 90'–150': consider going up to Pool 3 (if dry and you have energy); turn back before the weather changes."),

            heading("Giữ rừng xanh", "Keep the forest green"),
            paragraph("Không bẻ cành, không khắc vẽ lên đá; đi trên lối mòn để tránh dẫm nát rễ cây. Tắt loa công suất lớn, nhường âm thanh cho rừng. Rác mang vào—rác mang ra; chai nhựa, khăn giấy, vỏ snack… bỏ lại là ‘vết xước’ rất lâu mờ.", "Don't break branches or carve on rocks; stay on the trail to avoid trampling roots. Turn off loud speakers and leave the soundscape to the forest. Pack in, pack out; plastic bottles, tissues, and snack wrappers left behind are 'scratches' that take a very long time to fade."),

            heading("Điểm gần kề để kết hợp", "Nearby spots to combine"),
            paragraph("Có thể nối cung với Dốc Lết (tắm biển chiều mát) hoặc ghé Ninh Hòa thưởng thức ẩm thực địa phương trước khi quay lại Nha Trang.", "You can link the trip with Doc Let (a cool afternoon swim) or stop in Ninh Hoa to enjoy local cuisine before returning to Nha Trang."),

            gallery(List.of(
                img("https://statics.vinpearl.com/ba-ho-nha-trang-7_1628260967.jpg", "Hồ – thác giữa rừng", "Pools and waterfalls in the forest", false, 0),
                img("https://static.vinwonders.com/production/suoi-ba-ho-1-1.jpg", "Màu nước xanh mát", "Cool blue water", false, 1),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/08/suoi-ba-ho-11-e1534474677178.jpg", "Đường mòn phiêu lưu", "An adventurous trail", false, 2)
            )),
            quote("“Đi chậm từng bước đá, nghe rừng kể chuyện nước.”", "\"Step slowly from rock to rock, and hear the forest tell its tale of water.\""),
            divider(),
            mapBlock(new double[]{109.2160, 12.4170})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thác nước & Suối", "Waterfalls & Streams")).slug("thac-nuoc-suoi").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Trekking nhẹ", "Light trekking")).slug("trekking-nhe").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tắm suối mát", "Cool stream swim")).slug("tam-suoi-mat").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 7) Đồng muối Hòn Khói (Salt Fields) */
  private static PlaceDoc poiHonKhoiSaltField() {
    String slug = "hon-khoi-salt-field";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Đồng muối Hòn Khói (Ninh Hòa)", "Hon Khoi Salt Fields (Ninh Hoa)"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Cánh đồng muối nổi tiếng với khung cảnh lao động bình minh.", "Salt fields famous for the scenes of dawn labor."))
        .description(of("Khoảng cách ~40–50km từ Nha Trang. Đẹp nhất lúc sớm khi người dân thu hoạch muối.", "About 40–50km from Nha Trang. Most beautiful early in the morning when locals harvest the salt."))
        .addressLine(of("Ninh Hòa, Khánh Hòa", "Ninh Hoa, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2270, 12.5530})
        .vibes(List.of("peaceful", "nature", "cultural"))
        .atmosphere(of("salt-field-dawn", "salt-field-dawn"))
        .travelStyle(List.of("photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("photography", "culture", "explore"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(120)
        .photographyScore(95)
        .romanticScore(65)
        .localExperienceScore(90)
        .images(withCover(
            img("https://static.vinwonders.com/2022/04/hon-khoi.jpg", "Đồng muối Hòn Khói bình minh", "Hon Khoi salt fields at dawn", true, 0),
            img("https://static.vinwonders.com/2022/04/mKrOrEC5-hon-khoi-5.jpg", "Gánh muối trên đồng", "Carrying salt across the field", false, 1),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/3_20250812211322.jpg", "Đồng muối mênh mông", "Vast salt fields", false, 2),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/18_20250812204404.jpg", "Hạt muối trắng", "White salt crystals", false, 3),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/4_20250812204544.jpg", "Cánh đồng muối bao la", "Boundless salt fields", false, 4),
            img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/1_20250812204405.jpg", "Phản chiếu trời trong ô muối", "The sky reflected in the salt ponds", false, 5)
        ))
        .content(List.of(
            heading("Hòn Khói – khúc ca bình minh trên muối", "Hon Khoi – a dawn song over the salt"),
            quote("""
                    Trời đỏ dần qua sương,
                    muối sáng như lời hát;
                    vai gánh nghiêng đường dài,
                    hạt trắng đẫm nắng mật.
                """, """
                    The sky reddens through the mist,
                    salt glows like a song;
                    shoulders tilt down the long path,
                    white grains drenched in honeyed sun.
                """),
            paragraph("Cánh đồng muối Hòn Khói mang vẻ đẹp rất ‘động’: lúc rạng đông, mặt nước trong ô ruộng như tấm gương khổng lồ phản chiếu bầu trời; khi nắng cao, các đụn muối trắng nổi bật trên nền xanh biển xa. Nhịp lao động bắt đầu sớm tinh mơ – cào, gom, gánh – vẽ nên những đường cong mềm trên nền trời hồng.", "The Hon Khoi salt fields have a very 'kinetic' beauty: at dawn, the water in the ponds is like a giant mirror reflecting the sky; when the sun rises higher, white salt mounds stand out against the distant blue sea. The labor begins at first light – raking, gathering, carrying – drawing soft curves against the pink sky."),

            heading("Thời điểm & cách đi", "Timing & how to get there"),
            paragraph("• Từ Nha Trang → Ninh Hòa: quãng 40–50km. Nên có mặt trước bình minh 20–30 phút để săn ‘giờ xanh’ rồi chuyển sang ‘giờ vàng’.", "• From Nha Trang → Ninh Hoa: about 40–50km. Arrive 20–30 minutes before dawn to catch the 'blue hour' and then the 'golden hour'."),
            paragraph("• 05:00–07:00: lúc đẹp nhất để thấy người làm muối đang thu hoạch, ánh sáng mềm, phản chiếu rõ. Sau 08:00 nắng mạnh, mặt ruộng phản quang dễ chói mắt, ảnh gắt.", "• 05:00–07:00: the best time to see the workers harvesting, with soft light and clear reflections. After 08:00 the sun is strong, the fields glare, and photos turn harsh."),
            paragraph("• Đi xe máy/ô tô tự túc: gửi xe ở khu vực cho phép và đi bộ theo lối bờ ô muối; tôn trọng biển báo, không tự ý vào khu vực cấm.", "• Self-driving by motorbike/car: park in permitted areas and walk along the pond embankments; respect the signs and don't enter restricted zones."),

            imageBlock(img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/2_20250812204404.jpg", "Màu trời phản chiếu trên ô muối", "Sky colors reflected on the salt ponds", false, 0)),

            heading("Ứng xử tại đồng muối", "Etiquette at the salt fields"),
            paragraph("• Giữ khoảng cách an toàn, nhường lối cho xe cào và người gánh; không đặt chân máy giữa đường đi.", "• Keep a safe distance and yield to the rake carts and carriers; don't set tripods in the middle of the path."),
            paragraph("• Xin phép trước khi chụp cận cảnh chân dung; hạn chế chụp quá sát gây mất tập trung khi họ đang vác nặng.", "• Ask permission before taking close-up portraits; avoid shooting too close while they carry heavy loads, which could distract them."),
            paragraph("• Không bước xuống ô muối/đụn muối; một dấu chân cũng có thể làm hỏng bề mặt kết tinh.", "• Don't step into the ponds or onto the salt mounds; even one footprint can ruin the crystallized surface."),
            paragraph("• Nếu mua nước/đồ ăn ở quán nhỏ gần đó, hãy trả giá hợp lý, coi như lời cảm ơn người địa phương.", "• If you buy drinks or food at a nearby stall, pay a fair price as a way of thanking the locals."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Toàn cảnh: dùng ‘đường gánh’ làm leading line hướng về mặt trời; hạ máy thấp để mặt nước nhân đôi bầu trời.", "• Panorama: use the 'carrying path' as a leading line toward the sun; lower the camera so the water doubles the sky."),
            paragraph("• Phản chiếu: đứng sát mép ô muối, canh gió nhẹ để mặt nước phẳng; khép khẩu f/8–f/11 cho ảnh nét đều.", "• Reflections: stand at the pond's edge, time a light breeze for a smooth surface; stop down to f/8–f/11 for even sharpness."),
            paragraph("• Chân dung lao động: đứng chếch 30–45° so với hướng di chuyển để thấy dáng người cân đối với quang gánh; tắt flash, ưu tiên ánh sáng tự nhiên.", "• Labor portraits: stand at a 30–45° angle to their direction of movement to show the figure balanced with the carrying pole; turn off the flash and favor natural light."),
            paragraph("• Chống chói: đội nón, đeo kính râm; với máy ảnh, cân nhắc gắn filter GND/PL để kiểm soát bầu trời và phản xạ.", "• Glare control: wear a hat and sunglasses; for the camera, consider a GND/PL filter to control the sky and reflections."),

            heading("Câu chuyện mùa muối (tham khảo)", "The salt season story (for reference)"),
            paragraph("Mùa nắng là thời điểm sản lượng tốt: nắng đều, gió nhẹ giúp muối kết tinh nhanh. Trước mỗi mùa, người làm muối tu sửa bờ ô, đắp nền phẳng; trong mùa, nhịp việc sớm – trưa – chiều khác nhau. Khi tới tham quan, bạn như đi qua ‘xưởng ngoài trời’ – hãy luôn tôn trọng nhịp độ đó.", "The dry season yields the best output: steady sun and light wind help the salt crystallize quickly. Before each season, the salt workers repair the embankments and level the beds; during the season, the work rhythm differs morning, noon, and afternoon. When you visit, it's like walking through an 'open-air workshop' – always respect that rhythm."),

            heading("Chuẩn bị & an toàn", "Preparation & safety"),
            paragraph("• Trang phục: áo dài tay, nón rộng vành, giày/dep bám tốt (bờ ô muối trơn).", "• Clothing: long sleeves, a wide-brimmed hat, and grippy shoes or sandals (the pond banks are slippery)."),
            paragraph("• Vật dụng: nước uống, khăn giấy, kem chống nắng; túi rác nhỏ để tự gom rác.", "• Items: drinking water, tissues, sunscreen; a small trash bag to collect your own waste."),
            paragraph("• Thiết bị bay: chỉ bay flycam khi gió nhỏ và khu vực cho phép; giữ khoảng cách, không bay thấp trên đầu người gánh.", "• Drone: fly only in light wind and permitted areas; keep your distance and don't fly low over the carriers' heads."),

            heading("Lịch trình gợi ý 90–120 phút", "Suggested 90–120 minute itinerary"),
            paragraph("• 0’–20’: đến nơi trước bình minh, khảo sát lối đi, chọn vị trí phản chiếu.", "• 0'–20': arrive before dawn, scout the paths, choose a reflection spot."),
            paragraph("• 20’–60’: chụp toàn cảnh mặt trời lên + phản chiếu trên ô muối.", "• 20'–60': shoot the panorama of the rising sun + reflections on the salt ponds."),
            paragraph("• 60’–90’: chuyển sang chụp cận cảnh người gánh, chi tiết hạt muối – quang gánh (xin phép trước).", "• 60'–90': switch to close-ups of the carriers and details of the salt grains and carrying poles (ask permission first)."),
            paragraph("• 90’–120’: nghỉ ngắn, mua nước ủng hộ quán nhỏ, xem lại ảnh – rời điểm khi nắng trở nên gắt.", "• 90'–120': take a short break, buy a drink to support a small stall, review your photos, and leave as the sun grows harsh."),

            heading("Kết hợp điểm gần kề", "Combine with nearby spots"),
            paragraph("Có thể ghé Biển Dốc Lết (cách không xa) để tắm biển buổi sáng muộn, hoặc vòng về làng chài Ninh Thủy ngắm thúng chài – ăn hải sản tươi theo thời điểm.", "You can stop by Doc Let Beach (not far) for a late-morning swim, or loop back to Ninh Thuy fishing village to see the basket boats and eat fresh seafood when available."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/04/hon-khoi.jpg", "Bình minh trên đồng muối", "Dawn over the salt fields", false, 0),
                img("https://static.vinwonders.com/2022/04/mKrOrEC5-hon-khoi-5.jpg", "Gánh muối trên nền trời hồng", "Carrying salt against a pink sky", false, 1),
                img("https://baokhanhhoa.vn/file/e7837c02857c8ca30185a8c39b582c03/082025/3_20250812211322.jpg", "Ô muối phản chiếu trời", "Salt ponds reflecting the sky", false, 2)
            )),

            quote("“Hạt muối trắng – mồ hôi trong veo của người làm đồng.”", "\"White grains of salt – the clear sweat of the field workers.\""),
            divider(),
            mapBlock(new double[]{109.2270, 12.5530})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Cảnh quan lao động", "Working landscape")).slug("canh-quan-lao-dong").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Đồng muối bình minh", "Dawn salt fields")).slug("dong-muoi-binh-minh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Chụp ảnh lao động", "Labor photography")).slug("chup-anh-lao-dong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Phản chiếu mặt nước", "Water reflections")).slug("phan-chieu-mat-nuoc").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 8) Vịnh Ninh Vân (Ninh Vân Bay) */
  private static PlaceDoc poiNinhVanBay() {
    String slug = "ninh-van-bay";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Vịnh Ninh Vân (Ninh Vân Bay)", "Ninh Van Bay"))
        .slug(slug)
        .parentSlug("nha-trang")
        .ancestors(List.of("nha-trang"))
        .childrenCount(0)
        .shortDescription(of("Vịnh yên tĩnh, resort ẩn mình giữa rừng – đá – biển.", "A quiet bay with resorts hidden among forest, rock, and sea."))
        .description(of("Cách trung tâm ~20km đường chim bay (thường đi thuyền). Nước trong, bờ đá đẹp; phù hợp nghỉ dưỡng tĩnh.", "About 20km as the crow flies (usually reached by boat). Clear water and beautiful rocky shores; ideal for a quiet retreat."))
        .addressLine(of("Ninh Vân, Ninh Hòa, Khánh Hòa", "Ninh Van, Ninh Hoa, Khanh Hoa"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.2790, 12.4500})
        .vibes(List.of("peaceful", "relaxing", "nature"))
        .atmosphere(of("secluded-bay", "secluded-bay"))
        .travelStyle(List.of("luxury", "slow-travel", "photography"))
        .suitableFor(List.of("solo", "couple"))
        .visitPurpose(List.of("relax", "photography"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(480)
        .photographyScore(90)
        .romanticScore(95)
        .localExperienceScore(70)
        .images(withCover(
            img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-4.jpg", "Vịnh Ninh Vân tĩnh lặng", "Tranquil Ninh Van Bay", true, 0),
            img("https://static.vinwonders.com/production/vinh-ninh-van-cach-nha-trang-60-km.jpg", "Bờ đá – rừng – biển", "Rock, forest, and sea", false, 1),
            img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-7-1.jpg", "Bến thuyền vào vịnh", "The boat pier into the bay", false, 2),
            img("https://statics.vinpearl.com/ninh-van-du-lich-Nha-Trang_1671162085.jpg", "Nước xanh trong vắt", "Crystal-clear blue water", false, 3),
            img("https://static.vinwonders.com/production/cam-trai-tai-vinh-ninh-van.jpg", "Không gian riêng tư", "A private space", false, 4),
            img("https://static.vinwonders.com/2022/12/ninh-van-du-lich-Nha-Trang-7.jpg", "Bình minh ở vịnh", "Dawn at the bay", false, 5)
        ))
        .content(List.of(
            heading("Ninh Vân – trú ẩn giữa thiên nhiên", "Ninh Van – a retreat amid nature"),
            quote("""
                    Gió đi qua triền đá,
                    sóng ru lưng rừng xanh;
                    một bờ vịnh lặng tiếng,
                    lòng theo nước yên lành.
                """, """
                    Wind passes over the rocky slope,
                    waves cradle the green forest's back;
                    a silent shore of the bay,
                    the heart drifts calm with the water.
                """),
            paragraph("Cách trung tâm Nha Trang một quãng thuyền ngắn, vịnh Ninh Vân ẩn mình giữa rừng – đá – biển. Làn nước trong, bờ đá độc bản và nhịp sống chậm tạo cảm giác “trốn phố” đúng nghĩa. Đây là lựa chọn lý tưởng cho chuyến nghỉ dưỡng tĩnh: đọc sách trên deck gỗ, nghe sóng thay tiếng xe, để gió biển hong khô mọi ồn ã.", "A short boat ride from central Nha Trang, Ninh Van Bay hides among forest, rock, and sea. The clear water, unique rocky shore, and slow pace create a true 'city escape' feeling. It's an ideal choice for a quiet retreat: reading on a wooden deck, hearing waves instead of traffic, letting the sea breeze dry away all the noise."),

            heading("Thời điểm & cách di chuyển", "Timing & how to get there"),
            paragraph("Phần lớn điểm lưu trú/tour có thuyền đưa – đón theo khung giờ cố định từ đất liền. Ngày biển êm, hành trình rất thư thái (10–20 phút). Mang balo gọn, giày bám tốt nếu dự tính leo ghềnh đá ngắm cảnh. Khung giờ đẹp: bình minh (05:30–07:00) và ‘giờ vàng’ 16:30–18:00.", "Most accommodations and tours run a boat shuttle on fixed schedules from the mainland. On calm days the crossing is very relaxing (10–20 minutes). Bring a compact backpack and grippy shoes if you plan to scramble over the rocks for views. Best windows: dawn (05:30–07:00) and the 'golden hour' 16:30–18:00."),

            imageBlock(img("https://statics.vinpearl.com/vinh-ninh-van-3_1625620560.jpg", "Deck gỗ nhìn thẳng ra vịnh", "A wooden deck facing the bay", false, 0)),

            heading("Gợi ý trải nghiệm", "Experience suggestions"),
            paragraph("• Ngồi thiền/thở sâu trên deck gỗ vào sáng sớm, nghe sóng vỗ đều như metronome tự nhiên.", "• Meditate or breathe deeply on the wooden deck at dawn, listening to the steady waves like a natural metronome."),
            paragraph("• Kayak/SUP dọc bờ những ngày gió nhẹ; tạm neo ở dải cát nhỏ kẹp giữa bãi đá để tắm biển.", "• Kayak/SUP along the shore on light-wind days; anchor briefly at a small sandbar between the rocks for a swim."),
            paragraph("• Trek ngắn 20–40 phút lên điểm cao vừa phải phía sau khu lưu trú để thu toàn cảnh ‘rừng – đá – biển’.", "• A short 20–40 minute trek to a moderate high point behind the accommodation for a panorama of 'forest – rock – sea'."),
            paragraph("• Tối giảm đèn, ngắm bầu trời sao; thưởng thức hải sản đơn giản tại khu nghỉ/nhà bè (tuỳ dịch vụ).", "• At night, dim the lights and watch the starry sky; enjoy simple seafood at the resort or raft house (depending on services)."),

            heading("Góc chụp & mẹo ảnh", "Photo angles & tips"),
            paragraph("• Toàn cảnh: đứng trên sườn đá sau khu lưu trú để ‘ăn’ đủ ba lớp rừng – đá – biển; đặt đường chân trời ở 1/3 khung.", "• Panorama: stand on the rocky slope behind the accommodation to capture all three layers of forest – rock – sea; place the horizon at the lower third."),
            paragraph("• Tiền cảnh sóng – đá: chụp chếch 30–45° để thấy vệt bọt kéo dài khi sóng rút; khép khẩu f/8–f/11 cho ảnh nét đều.", "• Wave-and-rock foreground: shoot at a 30–45° angle to catch the long foam streaks as the waves recede; stop down to f/8–f/11 for even sharpness."),
            paragraph("• Bình minh: đo sáng vào vùng trời, hạ EV 0.3–0.7 để giữ màu; lấy phản chiếu nước làm lớp dẫn.", "• Dawn: meter for the sky, lower EV by 0.3–0.7 to keep the colors; use the water's reflection as a leading layer."),
            paragraph("• Chân dung tĩnh: nền tường đá/ghềnh rêu xám xanh giúp tôn da, tránh backlight gắt giữa trưa.", "• Still portraits: gray-green rock walls and mossy ledges flatter the skin; avoid harsh backlight at midday."),

            heading("Ăn uống & nhịp sinh hoạt", "Food & daily rhythm"),
            paragraph("Ẩm thực thiên về hải sản tươi theo ngày (tôm, mực, cá theo mùa) chế biến đơn giản. Đặt bữa trước khung giờ đông để bếp chuẩn bị kịp. Ban ngày ưu tiên nước điện giải, trái cây; chiều tối nhâm nhi nhẹ để còn thời gian ngắm trời sao – nghe sóng.", "The cuisine leans toward fresh daily seafood (shrimp, squid, seasonal fish) simply prepared. Order meals ahead of peak hours so the kitchen can keep up. By day favor electrolyte drinks and fruit; in the evening keep it light so you have time to watch the stars and listen to the waves."),

            heading("An toàn & lưu ý nhỏ", "Safety & small notes"),
            paragraph("Địa hình đá có thể trơn khi ẩm; đi chậm, tránh dép trơn trượt. Không leo lên mỏm nhô sát mép khi gió mạnh. Nếu chèo kayak/SUP: luôn mang áo phao, buộc leash, không đi xa ngoài vùng quan sát của bãi. Ban đêm hạn chế ra ghềnh đá do tầm nhìn kém.", "The rocky terrain can be slippery when damp; walk slowly and avoid slick sandals. Don't climb onto edge-side outcrops in strong wind. If kayaking/SUP: always wear a life vest, attach the leash, and don't go beyond the beach's line of sight. At night, avoid the rocky ledges due to poor visibility."),

            heading("Bền vững & tôn trọng không gian", "Sustainability & respecting the space"),
            paragraph("Không bẻ san hô, không nhặt vỏ còn sinh vật; dùng kem chống nắng ‘reef-safe’ nếu tắm/chèo. Không bật loa lớn—giữ nhịp tĩnh vốn là tinh thần của Ninh Vân. Nếu chỉ tham quan mà không lưu trú, tôn trọng khu vực riêng tư của resort, đi đúng lối cho phép.", "Don't break coral or collect shells with creatures inside; use reef-safe sunscreen if you swim or paddle. Don't play loud speakers – keep the quiet pace that is the spirit of Ninh Van. If you visit without staying, respect the resort's private areas and stick to permitted paths."),

            heading("Lịch trình gợi ý 1 ngày ‘chậm’", "Suggested 'slow' one-day itinerary"),
            paragraph("• Sáng: thuyền vào vịnh → cà phê/thiền nhẹ trên deck → kayak vòng bờ 40–60’ → tắm biển.", "• Morning: boat into the bay → coffee/light meditation on the deck → kayak around the shore for 40–60' → a swim."),
            paragraph("• Trưa: nghỉ trưa trong phòng/ghế dài dưới bóng cây, đọc sách.", "• Noon: rest in your room or on a lounger in the shade, reading."),
            paragraph("• Chiều: trek ngắn lên viewpoint → chụp ‘giờ vàng’ trên ghềnh đá.", "• Afternoon: a short trek to a viewpoint → shoot the 'golden hour' on the rocky ledges."),
            paragraph("• Tối: bữa hải sản vừa đủ, tắt bớt đèn để ngắm sao, nghe sóng trước khi ngủ.", "• Evening: a modest seafood dinner, dim the lights to watch the stars, and listen to the waves before sleep."),

            gallery(List.of(
                img("https://static.vinwonders.com/2022/12/vinh-ninh-van-nha-trang-4.jpg", "Vịnh tĩnh lặng nhìn từ bờ đá", "The tranquil bay seen from the rocky shore", false, 0),
                img("https://statics.vinpearl.com/ninh-van-du-lich-Nha-Trang_1671162085.jpg", "Nước xanh ôm mô đá", "Blue water embracing the rocks", false, 1),
                img("https://static.vinwonders.com/2022/12/ninh-van-du-lich-Nha-Trang-7.jpg", "Bình minh ở vịnh", "Dawn at the bay", false, 2)
            )),

            quote("“Ninh Vân là cách tự thưởng – cho tâm trí có khoảng lặng.”", "\"Ninh Van is a way to treat yourself – to give the mind a moment of quiet.\""),
            divider(),
            mapBlock(new double[]{109.2790, 12.4500})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Nghỉ dưỡng cao cấp", "Luxury retreat")).slug("nghi-duong-cao-cap").build(),
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển & Đảo", "Beaches & Islands")).slug("bai-bien-dao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Vịnh tĩnh lặng", "Tranquil bay")).slug("vinh-tinh-lang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Trốn phố", "City escape")).slug("tron-pho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Không gian riêng tư", "Private space")).slug("khong-gian-rieng-tu").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.EXPENSIVE)
        .build();
  }
}
