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
import static com.mravel.catalog.utils.place.Localized.vi;
import static com.mravel.catalog.utils.place.Localized.of;

@Configuration
@Profile("seed")
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
        .name(of("Hồ Hoàn Kiếm & Đền Ngọc Sơn", "Hoan Kiem Lake & Ngoc Son Temple"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Trái tim thủ đô, tháp Rùa giữa hồ, cầu Thê Húc đỏ rực.", "The heart of the capital, with Turtle Tower amid the lake and the bright red The Huc Bridge."))
        .description(of("Dạo quanh hồ, qua cầu Thê Húc thăm Đền Ngọc Sơn; sáng sớm người dân tập thể dục, tối cuối tuần phố đi bộ nhộn nhịp.", "Stroll around the lake and cross The Huc Bridge to visit Ngoc Son Temple; locals exercise in the early morning, and the weekend walking street buzzes at night."))
        .addressLine(of("Đinh Tiên Hoàng, Hoàn Kiếm, Hà Nội", "Dinh Tien Hoang, Hoan Kiem, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8525, 21.0288})
        .vibes(List.of("cultural", "peaceful"))
        .atmosphere(of("urban-lakeside", "urban-lakeside"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography", "relax"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(90)
        .romanticScore(80)
        .localExperienceScore(90)
        .images(withCover(
            img("https://statics.vinpearl.com/thap-rua-ho-guom-3_1691857546.jpg", "Hồ Gươm – tháp Rùa", "Hoan Kiem Lake – Turtle Tower", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/cau-the-huc-03-1696869213.jpeg", "Cầu Thê Húc", "The Huc Bridge", false, 1),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg/500px-C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg", "Đền Ngọc Sơn", "Ngoc Son Temple", false, 2),
            img("https://statics.vinpearl.com/pho-di-bo-ha-noi-1_1685972192.jpg", "Phố đi bộ", "Walking street", false, 3),
            img("https://www.ductronghotel.com/UploadFile/Article/dao-quanh-ho-guom-sang-som1.jpg", "Sáng sớm bên hồ", "Early morning by the lake", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/mo-mang-binh-yen-top-5-dia-diem-ngam-hoang-hon-ha-noi-4-1639723337.jpeg", "Hoàng hôn lãng mạn", "Romantic sunset", false, 5)
        ))
        .content(List.of(
            heading("Hồ Gươm – biểu tượng nghìn năm", "Hoan Kiem Lake – a thousand-year-old symbol"),
            quote("""
                    Sớm hồng qua mặt nước,
                    tháp Rùa gọi sương bay;
                    Thê Húc ôm tia nắng,
                    Ngọc Sơn lặng chùa cây.
                """, """
                    Rosy dawn across the water,
                    the Turtle Tower calls the drifting mist;
                    The Huc embraces the sunbeams,
                    Ngoc Son rests amid the trees.
                """),
            paragraph("Trái tim của Hà Nội gói trong một vòng hồ nhỏ: tháp Rùa trầm mặc giữa làn nước xanh, cầu Thê Húc đỏ như tia bình minh dẫn vào Đền Ngọc Sơn. Sáng sớm (05:30–07:00) là lúc lung linh và yên ả nhất: người dân tập dưỡng sinh, vài tiếng đàn bầu, mùi hoa sữa/hoa lộc vừng theo mùa. Chiều muộn và tối cuối tuần, khu vực phố đi bộ quanh hồ náo nhiệt hơn nhưng ánh đèn phản chiếu lên mặt nước rất đẹp.", "The heart of Hanoi is wrapped within a small ring of lake: the contemplative Turtle Tower amid the green water, and The Huc Bridge, red as a ray of dawn, leading into Ngoc Son Temple. Early morning (05:30–07:00) is the most magical and peaceful time: locals practice qigong, a few notes of the monochord drift by, and the seasonal scent of milk flowers and barringtonia fills the air. In the late afternoon and on weekend nights, the walking-street area around the lake gets livelier, with lights beautifully reflected on the water."),
            heading("Lối tham quan gợi ý 60–90 phút", "Suggested 60–90 minute walking route"),
            paragraph("Bắt đầu từ phố Đinh Tiên Hoàng → dạo ven hồ ngắm tháp Rùa → sang cầu Thê Húc vào Đền Ngọc Sơn (đi chậm để cảm nhận nhịp gỗ dưới chân) → tham quan đền, đài Nghiên, tháp Bút, khám phá các hiện vật rùa hồ Gươm trưng bày → trở ra vòng tiếp hồ qua Lê Thái Tổ – Hàng Khay để khép cung đường.", "Start from Dinh Tien Hoang street → stroll along the lakeshore to admire the Turtle Tower → cross The Huc Bridge into Ngoc Son Temple (walk slowly to feel the rhythm of the wooden planks underfoot) → tour the temple, the Inkstone Pavilion and the Writing Brush Tower, and explore the displayed Hoan Kiem turtle specimens → head back to continue around the lake via Le Thai To – Hang Khay to close the loop."),
            heading("Đền Ngọc Sơn – nghi lễ và ứng xử", "Ngoc Son Temple – rituals and etiquette"),
            paragraph("Không gian thờ tự yêu cầu trang phục lịch sự, nói nhỏ, không chụp ảnh ở khu vực có biển cấm. Qua điện thờ, dành ít phút đọc bảng giới thiệu tích xưa (gươm báu – trả gươm) để hiểu hơn câu chuyện gắn với hồ. Vào giờ cao điểm, nhớ đi theo lối một chiều, nhường đường ở nhịp cầu hẹp.", "The place of worship calls for decent attire, a quiet voice, and no photography in areas marked off-limits. As you pass the shrine, take a few minutes to read the panels about the old legend (the magic sword being returned) to better understand the story tied to the lake. During peak hours, remember to follow the one-way path and yield at the narrow bridge spans."),
            imageBlock(img("https://statics.vinpearl.com/cau-the-huc%20(4)_1676479579.jpg", "Cầu Thê Húc rực rỡ", "The radiant The Huc Bridge", false, 0)),
            heading("Mẹo chụp ảnh & khung giờ đẹp", "Photography tips & golden hours"),
            paragraph("Bình minh: đứng ở bờ phía Hàng Khay để bắt được mặt trời lên sau lưng cầu Thê Húc. Chiều: chọn bờ Đinh Tiên Hoàng để lấy tháp Rùa giữa nền trời ấm. Tránh dùng flash làm lóa mắt người khác; nếu chụp dài phơi, bệ lan can thấp đủ đặt mini-tripod (giữ an toàn, không chắn lối đi).", "Sunrise: stand on the Hang Khay side to catch the sun rising behind The Huc Bridge. Afternoon: choose the Dinh Tien Hoang side to frame the Turtle Tower against a warm sky. Avoid using flash that dazzles others; for long exposures, the low railing ledge is wide enough for a mini-tripod (stay safe and do not block the path)."),
            heading("Phố đi bộ cuối tuần & trải nghiệm quanh hồ", "Weekend walking street & experiences around the lake"),
            paragraph("Tối thứ Sáu–Chủ nhật, các tuyến đường quanh hồ cấm xe, mở không gian biểu diễn nghệ thuật đường phố, trò chơi dân gian. Bạn có thể mua nước mát, kem, ngồi ghế đá nghe nhạc hoặc xem múa rối nước ở gần đó (xem lịch suất diễn trước). Tránh xả rác, giữ lối đi cho trẻ em và người già.", "From Friday to Sunday nights, the roads around the lake close to traffic, opening up spaces for street art performances and folk games. You can buy cool drinks and ice cream, sit on a stone bench to enjoy the music, or watch water puppetry nearby (check the show schedule in advance). Avoid littering and keep the paths clear for children and the elderly."),
            gallery(List.of(
                img("https://statics.vinpearl.com/thap-rua-ho-guom-3_1691857546.jpg", "Tháp Rùa giữa hồ", "Turtle Tower amid the lake", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/cau-the-huc-03-1696869213.jpeg", "Cầu Thê Húc đỏ rực", "The bright red The Huc Bridge", false, 1),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Dc_S%C6%A1n.jpg/500px-C%E1%BB%95ng_%C4%91%E1%BB%81n_Ng%E1%BB%8Cc_S%C6%A1n.jpg", "Cổng đền Ngọc Sơn", "Gate of Ngoc Son Temple", false, 2)
            )),
            heading("An toàn & lưu ý nhỏ", "Safety & small notes"),
            paragraph("Đường ven hồ nhiều đoạn lát đá có thể trơn khi mưa; giữ tốc độ chậm, cẩn thận ở mép nước. Buổi tối đông người, đeo ba lô phía trước, giữ tư trang gọn. Nếu dẫn trẻ nhỏ, luôn nắm tay tại khu lan can thấp. Tôn trọng người tập thể dục buổi sớm – tránh đứng chắn đường chạy.", "Many stone-paved sections along the lake can be slippery when wet; keep a slow pace and be careful at the water's edge. It gets crowded at night, so wear your backpack in front and keep your belongings tidy. If you bring small children, always hold their hands near the low railing areas. Respect the early-morning exercisers – avoid blocking the running path."),
            quote("“Một vòng hồ, một nhịp thở – chạm được nhịp tim Hà Nội.”", "“One loop of the lake, one breath – and you touch the heartbeat of Hanoi.”"),
            divider(),
            mapBlock(new double[]{105.8525, 21.0288})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên & Hồ", "Nature & Lake")).slug("thien-nhien-ho").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-giao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng thủ đô", "Capital icon")).slug("bieu-tuong-thu-do").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Phố đi bộ", "Walking street")).slug("pho-di-bo").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 2) Phố cổ Hà Nội (36 phố phường) */
  private static PlaceDoc poiPhoCoHaNoi() {
    String slug = "pho-co-ha-noi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Phố cổ Hà Nội (36 phố phường)", "Hanoi Old Quarter (36 Streets)"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Mạng lưới phố cổ buôn bán sầm uất, nhà ống, mái ngói rêu phong.", "A bustling network of old trading streets with tube houses and moss-covered tiled roofs."))
        .description(of("Khám phá Hàng Đào, Hàng Ngang, Hàng Mã…; thưởng thức phở, bún chả, cà phê trứng; tối có phố bia Tạ Hiện nhộn nhịp.", "Explore Hang Dao, Hang Ngang, Hang Ma...; savor pho, bun cha and egg coffee; at night the lively Ta Hien beer street comes alive."))
        .addressLine(of("P. Hàng Gai, Hoàn Kiếm, Hà Nội", "Hang Gai St., Hoan Kiem, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8480, 21.0350})
        .vibes(List.of("cultural", "urban", "lively"))
        .atmosphere(of("historic-street", "historic-street"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "food", "photography", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(85)
        .romanticScore(65)
        .localExperienceScore(95)
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2017/pho-co-Ha-Noi-1.jpg", "Một góc phố cổ", "A corner of the Old Quarter", true, 0),
            img("https://hnm.1cdn.vn/2023/07/02/nha-ong.jpg", "Nhà ống mái ngói", "Tiled-roof tube houses", false, 1),
            img("https://ntthnue.edu.vn/uploads/Images/2021/02/036.jpg", "Gánh hàng rong", "Street vendor's carrying pole", false, 2),
            img("https://tour.dulichvietnam.com.vn/uploads/image/pho-bia-ta-hien/Anh-1-dirtytreehugger75(1).png", "Phố Tạ Hiện về đêm", "Ta Hien street at night", false, 3),
            img("https://statics.vinpearl.com/cafe-pho-co-ha-noi-1_1680926451.jpg", "Cà phê phố cổ", "Old Quarter coffee", false, 4),
            img("https://danviet.ex-cdn.com/files/f1/upload/3-2016/images/2016-08-04/147028444782323-ngo-nho-2.jpg", "Con ngõ nhỏ", "A small alley", false, 5)
        ))
        .content(List.of(
            heading("Phố cổ – nhịp sống chậm", "The Old Quarter – a slow rhythm of life"),
            quote("""
                    Mái ngói ôm mùi nắng,
                    ngõ hẹp giữ lời rao;
                    bước chậm qua tên phố,
                    thời gian khẽ cúi chào.
                """, """
                    Tiled roofs embrace the scent of sun,
                    narrow alleys hold the vendors' calls;
                    step slowly past the street names,
                    and time gently bows in greeting.
                """),
            paragraph("Khu 36 phố phường là mạng lưới phố nhỏ đan dày, nhà ống mái ngói, biển hiệu san sát. Mỗi con phố xưa ứng với một nghề: Hàng Đào, Hàng Ngang, Hàng Mã… Đi bộ là cách hay nhất để cảm phố: giày mềm, balo gọn, sẵn sàng rẽ vào những ngõ hẹp bất chợt mở ra sân nhỏ rợp bóng cây.", "The 36 Streets area is a dense network of small streets, tube houses with tiled roofs, and signboards packed side by side. Each old street once corresponded to a trade: Hang Dao, Hang Ngang, Hang Ma... Walking is the best way to feel the streets: soft shoes, a light backpack, and a readiness to turn into narrow alleys that suddenly open onto small courtyards shaded by trees."),
            heading("Lối dạo gợi ý 90–120 phút", "Suggested 90–120 minute stroll"),
            paragraph("Xuất phát Hàng Gai → vòng sang Hàng Đào – Đồng Xuân (chợ, hàng vải) → lách qua Hàng Chiếu – Ô Quan Chưởng (cửa ô cổ) → nghỉ chân cà phê ở mái nhà nhìn xuống phố → chiều tối ghé Tạ Hiện nếu thích không khí sôi động. Giữ nhịp chậm, quan sát mặt tiền nhà ống với cửa bức bàn, ban công gỗ và giàn hoa giấy.", "Start at Hang Gai → loop over to Hang Dao – Dong Xuan (the market and fabric shops) → slip through Hang Chieu – O Quan Chuong (the ancient city gate) → take a coffee break on a rooftop overlooking the street → in the early evening drop by Ta Hien if you enjoy a lively atmosphere. Keep a slow pace and observe the tube-house facades with their folding-panel doors, wooden balconies and bougainvillea trellises."),
            heading("Ăn vặt & món nên thử", "Snacks & dishes to try"),
            paragraph("Buổi sáng hợp phở, bún thang; trưa có bún chả, bún mọc; giữa buổi gọi chén chè, cốm xào, bánh rán; cà phê trứng cho buổi chiều. Chọn quán đông nhưng xoay bàn nhanh, nhìn cách bếp vận hành gọn gàng và dụng cụ sạch. Gọi khẩu phần vừa để thử được nhiều món, hạn chế bỏ thừa.", "Mornings suit pho and bun thang; lunch offers bun cha and bun moc; mid-day calls for a bowl of che, stir-fried young rice, or fried dough cakes; egg coffee for the afternoon. Choose eateries that are busy but turn tables quickly, and watch for a tidy kitchen and clean utensils. Order modest portions so you can try more dishes and minimize waste."),
            heading("Cà phê – ban công – ngắm phố", "Coffee – balconies – street watching"),
            paragraph("Nhiều quán cà phê trong hẻm nhỏ có lối leo cầu thang gỗ lên ban công; từ đó nhìn xuống dòng người, theo dải xe đạp – xích lô – người bán hàng rong len qua khung nhà cổ. Tránh nói to, kéo ghế mạnh; tắt flash khi chụp để không làm phiền bàn bên.", "Many cafes in small alleys have wooden staircases up to a balcony; from there you can look down on the flow of people, following the trail of bicycles, cyclos and street vendors weaving past the old houses. Avoid speaking loudly or dragging chairs roughly; turn off your flash when shooting so as not to disturb the next table."),
            imageBlock(img("https://static.vinwonders.com/production/pho-ta-hien-9.jpg", "Phố bia Tạ Hiện", "Ta Hien beer street", false, 0)),
            heading("Tạ Hiện về đêm & ứng xử văn minh", "Ta Hien at night & civilized behavior"),
            paragraph("Sau 19:00, phố bia Tạ Hiện rộn ràng: ghế thấp sát lòng đường, nhạc sôi động. Ngồi gọn bên trong vạch kẻ, không chắn lối đi; giữ đồ cá nhân phía trước; nói chuyện vui nhưng tôn trọng cư dân khu phố. Nếu không hợp nơi ồn, chọn các ngõ lân cận yên tĩnh hơn.", "After 19:00, Ta Hien beer street buzzes: low stools right at the roadside and upbeat music. Sit neatly within the marked lines without blocking the path; keep your belongings in front of you; have fun chatting but respect the local residents. If the noise is not for you, choose quieter nearby alleys."),
            heading("Mua sắm & quà mang về", "Shopping & souvenirs"),
            paragraph("Hỏi giá trước, so sánh 2–3 quầy; quần áo, vải, đồ thủ công nhỏ gọn dễ mang. Với hàng làm tay, hỏi chất liệu, cách bảo quản; giữ hóa đơn/biên nhận nếu cần đổi. Tránh mua san hô, động vật hoang dã hoặc đồ nghi ngờ nguồn gốc.", "Ask for prices first and compare 2–3 stalls; clothes, fabrics and small handicrafts are easy to carry. For handmade goods, ask about the material and care; keep the bill or receipt in case you need to exchange. Avoid buying coral, wildlife products or items of dubious origin."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & small tips"),
            paragraph("Sáng sớm bắt ánh xiên qua mái ngói; trưa nắng dùng bóng râm hiên nhà để cân sáng; chiều muộn “giờ xanh” phản chiếu trên ô cửa kính. Ở ngõ hẹp, chờ khoảnh khắc gánh hàng đi ngang để có điểm nhấn chuyển động. Không chụp quá gần người bán khi chưa xin phép.", "Catch the slanting light over the tiled roofs at dawn; at sunny noon use the shade of the eaves to balance the exposure; in the late afternoon the \"blue hour\" reflects on the windowpanes. In narrow alleys, wait for the moment a vendor's pole passes by to add a touch of motion. Do not shoot too close to vendors without asking permission."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2017/pho-co-Ha-Noi-1.jpg", "Một góc phố cổ", "A corner of the Old Quarter", false, 0),
                img("https://static.vinwonders.com/production/pho-ta-hien-9.jpg", "Phố bia Tạ Hiện", "Ta Hien beer street", false, 1),
                img("https://statics.vinpearl.com/cafe-pho-co-ha-noi-1_1680926451.jpg", "Cà phê phố cổ", "Old Quarter coffee", false, 2)
            )),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Đường nhỏ, xe máy qua lại sát người đi bộ – đi sát lề; ví, điện thoại để ngăn trước. Tránh đứng giữa lòng phố để chụp ảnh; trời mưa gạch đá trơn, đi chậm và bám tay vịn ở bậc thềm. Tôn trọng cư dân: không nói lớn khuya, không xả rác, không vẽ bậy lên tường nhà cổ.", "The streets are narrow and motorbikes pass close to pedestrians – walk near the curb; keep wallet and phone in front pockets. Avoid standing in the middle of the street to take photos; when it rains the stones get slippery, so walk slowly and hold the handrail on steps. Respect the residents: no loud talking late at night, no littering, and no graffiti on the old house walls."),
            quote("“Đi chậm để nghe phố kể chuyện.”", "“Walk slowly to hear the streets tell their stories.”"),
            divider(),
            mapBlock(new double[]{105.8480, 21.0350})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Phố cổ & Mua sắm", "Old Quarter & Shopping")).slug("pho-co-mua-sam").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("36 phố phường", "36 Streets")).slug("36-pho-phuong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Ẩm thực đường phố", "Street food")).slug("am-thuc-duong-pho").type(TagType.CUISINE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 3) Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình */
  private static PlaceDoc poiLangChuTichHoChiMinh() {
    String slug = "lang-chu-tich-ho-chi-minh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình", "Ho Chi Minh Mausoleum & Ba Dinh Square"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Công trình lịch sử – chính trị trọng điểm của Việt Nam.", "A key historical and political landmark of Vietnam."))
        .description(of("Tham quan lăng, bảo tàng Hồ Chí Minh, chùa Một Cột; lưu ý giờ mở cửa và quy định trang phục trang nghiêm.", "Visit the mausoleum, the Ho Chi Minh Museum and the One Pillar Pagoda; note the opening hours and the solemn dress code."))
        .addressLine(of("2 Hùng Vương, Ba Đình, Hà Nội", "2 Hung Vuong, Ba Dinh, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8348, 21.0379})
        .vibes(List.of("cultural", "peaceful"))
        .atmosphere(of("historic-memorial", "historic-memorial"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "explore"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(70)
        .romanticScore(40)
        .localExperienceScore(80)
        .images(withCover(
            img("https://statics.vinpearl.com/lang-chu-tich-ho-chi-minh-2_1685366073.jpg", "Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum", true, 0),
            img("https://media.thuonghieucongluan.vn/uploads/2025/06/23/z6732094622988-a29e6ed51530ae58aeb5931f22ce800f-1750613478.jpg", "Quảng trường Ba Đình", "Ba Dinh Square", false, 1),
            img("https://static.vinwonders.com/production/bao-tang-ho-chi-minh-banner.jpeg", "Bảo tàng Hồ Chí Minh", "Ho Chi Minh Museum", false, 2),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/chua-mot-cot/chua-mot-cot-thumb.jpg", "Chùa Một Cột", "One Pillar Pagoda", false, 3),
            img("https://cdn.tienphong.vn/images/a7a4eb175a75567c9a7ae09768d7094862013c5781a55280ecb18b6bbfcbb27a7140483d8dfcf1daedf9b58c60c1b7d9f55f4d4cf291e10c38303c5c973ba32b/a2-1122.jpg", "Hàng gác danh dự", "Honor guard", false, 4),
            img("https://baovinhlong.com.vn/file/e7837c027f6ecd14017ffa4e5f2a0e34/dataimages/202001/original/images2262083_Anh_trang_13.jpg", "Khu vực cảnh quan", "The landscaped grounds", false, 5)
        ))
        .content(List.of(
            heading("Ba Đình – nơi ghi dấu lịch sử", "Ba Dinh – a place that marks history"),
            quote("""
                    Sớm rơi lên thảm cỏ,
                    đá xám giữ thời gian;
                    bước nhẹ qua trang sử,
                    tiếng kèn gọi nắng ngan.
                """, """
                    Morning falls upon the lawn,
                    gray stone keeps the time;
                    step lightly through the pages of history,
                    the bugle calls the spreading sun.
                """),
            paragraph("Quần thể Ba Đình gồm Lăng Chủ tịch Hồ Chí Minh, Quảng trường Ba Đình, Bảo tàng Hồ Chí Minh và chùa Một Cột. Không gian trang nghiêm, hàng cây – thảm cỏ được chăm sóc kỹ; nên đến buổi sáng để tránh nắng gắt và chủ động thời gian xếp hàng.", "The Ba Dinh complex comprises the Ho Chi Minh Mausoleum, Ba Dinh Square, the Ho Chi Minh Museum and the One Pillar Pagoda. The space is solemn, with carefully tended rows of trees and lawns; it is best to arrive in the morning to avoid the harsh sun and to allow time for queuing."),
            heading("Trang phục & quy định cơ bản", "Dress code & basic rules"),
            paragraph("Ăn mặc lịch sự (quần dài, váy quá gối, áo có tay), bỏ mũ, không hút thuốc, không ồn ào. Một số đồ vật có thể phải gửi trước khi vào khu vực trang nghiêm; đi theo hàng, không chen lấn, không chụp ảnh ở khu vực có biển cấm. Luôn tuân thủ hướng dẫn của lực lượng làm nhiệm vụ.", "Dress decently (long trousers, skirts below the knee, sleeved tops), remove your hat, do not smoke, and stay quiet. Some items may have to be checked in before entering the solemn area; follow the line, do not push, and do not take photos where signs prohibit it. Always follow the instructions of the personnel on duty."),
            heading("Lộ trình gợi ý 90–120 phút", "Suggested 90–120 minute itinerary"),
            paragraph("Từ Quảng trường Ba Đình chiêm ngưỡng toàn cảnh → theo hướng dẫn vào viếng Lăng (xếp hàng trật tự, giữ im lặng) → tham quan Bảo tàng Hồ Chí Minh để hiểu hệ thống về cuộc đời và sự nghiệp → đi bộ sang chùa Một Cột, dừng đôi phút ngắm kiến trúc trên hồ sen → quay lại Quảng trường, dạo quanh thảm cỏ và các trục đường bao.", "From Ba Dinh Square, take in the whole panorama → follow the guidance to pay respects inside the Mausoleum (queue in an orderly manner and keep silent) → tour the Ho Chi Minh Museum for a systematic understanding of his life and career → walk over to the One Pillar Pagoda and pause a moment to admire the architecture above the lotus pond → return to the square and stroll around the lawns and the surrounding boulevards."),
            imageBlock(img("https://kientrucvadoisong.net/Upload/News/chuyen-cao-oc-%E2%80%9Cde%E2%80%9D-quang-truong-ba-dinh.jpg", "Không gian Ba Đình", "The Ba Dinh space", false, 0)),
            heading("Bảo tàng & cách xem trưng bày", "The museum & how to view the exhibits"),
            paragraph("Ở Bảo tàng, đi theo tuyến từ tổng quan đến chuyên đề; đọc tóm tắt tại đầu mỗi phòng để nắm mạch nội dung, sau đó tập trung vào các hiện vật – tư liệu gốc. Ghi chú nhanh những mốc năm quan trọng để đối chiếu khi xem tiếp ngoài trời.", "In the museum, follow the route from the overview to the thematic sections; read the summary at the entrance of each room to grasp the narrative, then focus on the original artifacts and documents. Jot down the important milestone years to cross-reference as you continue viewing outdoors."),
            heading("Chùa Một Cột – điểm dừng tĩnh tại", "One Pillar Pagoda – a tranquil stop"),
            paragraph("Công trình biểu tượng với kiến trúc gỗ đặt trên trụ đá giữa hồ sen. Khi vào khuôn viên chùa: đi nhẹ, nói khẽ, tránh dựa – chạm vào lan can gỗ; xếp hàng khi đông, giữ lối đi chung.", "An iconic structure with wooden architecture set on a stone pillar in the middle of a lotus pond. When entering the pagoda grounds: walk softly, speak quietly, and avoid leaning on or touching the wooden railing; queue when crowded and keep the shared path clear."),
            heading("Góc chụp & ứng xử khi chụp ảnh", "Photo angles & photography etiquette"),
            paragraph("Toàn cảnh Lăng đẹp nhất từ trục trung tâm Quảng trường; góc chéo 30–45° giúp khối kiến trúc nổi hơn. Ở khu vực có quy định, vui lòng không chụp hoặc tắt tiếng máy ảnh/điện thoại. Không trèo qua bồn cỏ, không đứng chắn hàng.", "The Mausoleum looks best from the central axis of the square; a 30–45° diagonal angle makes the architectural mass stand out more. In restricted areas, please refrain from shooting or mute your camera/phone. Do not step over the lawn beds and do not block the line."),
            gallery(List.of(
                img("https://statics.vinpearl.com/lang-chu-tich-ho-chi-minh-2_1685366073.jpg", "Lăng Chủ tịch Hồ Chí Minh", "Ho Chi Minh Mausoleum", false, 0),
                img("https://media.thuonghieucongluan.vn/uploads/2025/06/23/z6732094622988-a29e6ed51530ae58aeb5931f22ce800f-1750613478.jpg", "Quảng trường Ba Đình", "Ba Dinh Square", false, 1),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/chua-mot-cot/chua-mot-cot-thumb.jpg", "Chùa Một Cột", "One Pillar Pagoda", false, 2)
            )),
            heading("An toàn & lưu ý nhỏ", "Safety & small notes"),
            paragraph("Giữ tư trang gọn gàng, đi giày thấp để di chuyển nhiều; thời tiết nắng nên mang nước và mũ (cởi mũ khi vào khu trang nghiêm). Một số thời điểm có nghi lễ đổi gác – đứng sau vạch, quan sát trật tự. Không xả rác, không ngồi hay bước lên thảm cỏ.", "Keep your belongings tidy and wear low shoes for the long walk; the weather is sunny, so bring water and a hat (remove the hat in the solemn area). At certain times there is a changing-of-the-guard ceremony – stand behind the line and watch in an orderly way. Do not litter, sit on, or step on the lawns."),
            quote("“Trang nghiêm, yên tĩnh – lịch sử hiện diện.”", "“Solemn and quiet – history is present.”"),
            divider(),
            mapBlock(new double[]{105.8348, 21.0379})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-giao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Di sản quốc gia", "National heritage")).slug("di-san-quoc-gia").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Giáo dục lịch sử", "Historical education")).slug("giao-duc-lich-su").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tham quan có hướng dẫn", "Guided tour")).slug("tham-quan-co-huong-dan").type(TagType.SERVICE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 4) Văn Miếu – Quốc Tử Giám */
  private static PlaceDoc poiVanMieuQuocTuGiam() {
    String slug = "van-mieu-quoc-tu-giam";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Văn Miếu – Quốc Tử Giám", "Temple of Literature – Imperial Academy"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Trường đại học đầu tiên của Việt Nam, biểu tượng hiếu học.", "Vietnam's first university and a symbol of the love of learning."))
        .description(of("Khu di tích với kiến trúc cổ kính, bia tiến sĩ, hồ Văn; không gian xanh giữa lòng thủ đô.", "A relic site with ancient architecture, doctoral steles and the Van Lake; a green space in the heart of the capital."))
        .addressLine(of("58 Quốc Tử Giám, Đống Đa, Hà Nội", "58 Quoc Tu Giam, Dong Da, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8356, 21.0279})
        .vibes(List.of("educational", "cultural", "peaceful"))
        .atmosphere(of("scholarly-garden", "scholarly-garden"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(85)
        .romanticScore(60)
        .localExperienceScore(85)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/khue-van-cac-02-1697245075.jpeg", "Khuê Văn Các", "Khue Van Pavilion", true, 0),
            img("https://static.vinwonders.com/production/van-mieu-quoc-tu-giam-3.jpgg", "Sân vườn cổ kính", "Ancient courtyard garden", false, 1),
            img("https://scov.gov.vn/upload/2005660/20210923/9a74b1772a6733d123f3875cb1ab38fb20190201%5C130936_DS_BiaTienSi.jpg", "Bia tiến sĩ", "Doctoral steles", false, 2),
            img("https://cdn.nhandan.vn/images/7f491ee6c6b660425d7c9ab03f1ec4476b41c4d12c4dd5b820657f70b2dbe1883fd1dae55d20024dbfd7d558efa95d485cfacd5ee898d181029010948d9846a0/hn2-1626710744002.jpg", "Hồ Văn", "Van Lake", false, 3),
            img("https://titangroup.vn/wp-content/uploads/cong-tam-quan-02.jpg", "Cổng tam quan", "Triple gate", false, 4),
            img("https://www.vietfuntravel.com.vn/image/data/Ha-Noi/Quoc-Tu-Giam/tho-khong-tu.jpg", "Kiến trúc gỗ đỏ", "Red wooden architecture", false, 5)
        ))
        .content(List.of(
            heading("Văn Miếu – nét đẹp hiếu học", "Temple of Literature – the beauty of scholarship"),
            quote("""
                    Gió lùa qua Khuê Văn,
                    bóng chữ rơi hiên gạch;
                    tiếng lá chạm bia xưa,
                    nếp học còn nghiêm mạch.
                """, """
                    Wind drifts through Khue Van Pavilion,
                    the shadows of words fall on the brick eaves;
                    leaves brush against the ancient steles,
                    the discipline of learning still runs strong.
                """),
            paragraph("Văn Miếu – Quốc Tử Giám là quần thể di tích gắn với truyền thống hiếu học của Thăng Long. Không gian cổ kính với cổng tam quan, hồ Văn, Khuê Văn Các, sân vườn tĩnh lặng và hệ thống bia tiến sĩ khắc danh hiền tài. Đi chậm để nghe tiếng thời gian trên mái ngói rêu, mùi gỗ sậm và bóng cây cổ thụ rợp mát.", "The Temple of Literature – Imperial Academy is a relic complex tied to Thang Long's tradition of revering learning. The ancient space includes the triple gate, Van Lake, Khue Van Pavilion, quiet courtyard gardens, and the system of doctoral steles inscribed with the names of talented scholars. Walk slowly to hear the sound of time on the mossy tiled roofs, the scent of dark wood, and the cool shade of the old trees."),
            heading("Lộ trình tham quan gợi ý (60–90 phút)", "Suggested tour route (60–90 minutes)"),
            paragraph("Từ cổng tam quan vào Giếng Thiên Quang → qua Khuê Văn Các (đi bên hành lang, không chen giữa) → đến khu bia tiến sĩ, đọc kỹ giới thiệu về khoa thi – niên hiệu → tiến vào Đại Thành Môn, điện Đại Bái, nhà Thái Học để hiểu lịch sử giáo dục triều Lý – Trần – Lê → ra hồ Văn kết thúc.", "From the triple gate enter to the Thien Quang Well → pass Khue Van Pavilion (walk along the side corridor, do not crowd the center) → reach the doctoral steles area and read carefully about the examinations and reign titles → proceed into Dai Thanh Gate, the Dai Bai hall and the Thai Hoc house to understand the education history of the Ly – Tran – Le dynasties → finish at Van Lake."),
            imageBlock(img("https://cdnphoto.dantri.com.vn/YcGYcXfv_vwmAHSSJI0hoKfrV7o=/thumb_w/1920/2022/02/19/2-1645206718840.jpeg", "Khuê Văn Các", "Khue Van Pavilion", false, 0)),
            heading("Bia tiến sĩ & quy tắc ứng xử", "Doctoral steles & code of conduct"),
            paragraph("Hệ thống bia đặt trên lưng rùa là di sản quý – tuyệt đối không sờ, không chà tay lên đầu rùa; không tựa, ngồi, đặt đồ lên bệ bia. Giữ khoảng cách khi đông người, đọc bảng giới thiệu để hiểu tên khoa, niên hiệu, danh tính và quan niệm trọng dụng hiền tài.", "The steles mounted on the backs of stone tortoises are a precious heritage – absolutely do not touch or rub the tortoises' heads, and do not lean on, sit on, or place items on the stele pedestals. Keep your distance when it is crowded, and read the panels to understand the examination names, reign titles, identities, and the philosophy of valuing talent."),
            heading("Khung giờ & mùa đẹp", "Best times & seasons"),
            paragraph("Sáng sớm trời dịu, ít người, ánh xiên đẹp; mùa xuân có nhiều đoàn chụp kỷ yếu, lễ dâng bút – cầu may; mùa thu nắng mềm, bóng cây vàng lá rất thơ. Những ngày lễ – cao điểm thi cử sẽ đông, nên đến sớm hoặc đi muộn hơn để giữ trải nghiệm thanh tĩnh.", "Early mornings are mild with few people and lovely slanting light; spring brings many yearbook photo groups and brush-offering, luck-praying ceremonies; autumn has soft sunshine and poetic golden-leaved tree shade. Holidays and exam-season peaks get crowded, so arrive early or later to preserve a serene experience."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & small tips"),
            paragraph("Đứng chéo 30–45° so với Khuê Văn Các để thấy rõ cấu trúc gỗ và họa tiết; ở Giếng Thiên Quang, lùi vài nhịp lấy cả khung phản chiếu; trong điện thờ, tắt flash, giữ tiếng máy ảnh nhỏ. Tôn trọng không gian thờ tự – không tạo dáng phô trương, không đứng chắn lối.", "Stand at a 30–45° diagonal to Khue Van Pavilion to clearly capture its wooden structure and motifs; at the Thien Quang Well, step back a few paces to include the whole reflection; inside the shrine, turn off the flash and keep camera sounds low. Respect the place of worship – do not strike flashy poses or block the path."),
            heading("Học sinh – sinh viên & hoạt động văn hóa", "Students & cultural activities"),
            paragraph("Nhiều đoàn học sinh – sinh viên đến dâng hương, chụp ảnh lưu niệm, xin chữ đầu năm. Khi gặp đoàn nghi lễ, nhường lối, giữ trật tự; nếu muốn xin chữ, xếp hàng theo hướng dẫn và bảo quản cẩn thận giấy mực.", "Many groups of students come to offer incense, take souvenir photos and request calligraphy at the start of the year. When you encounter a ceremonial group, yield the path and keep order; if you wish to request calligraphy, queue as instructed and carefully preserve the paper and ink."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Trang phục lịch sự, nói khẽ, đi nhẹ; giày đế êm để đi sân lát gạch. Tránh sờ vào đồ gỗ sơn son thếp vàng; không xả rác, không vẽ bậy lên tường – cột. Trời mưa nền gạch trơn, đi chậm dọc hành lang.", "Dress decently, speak softly and walk lightly; wear comfortable-soled shoes for the brick-paved courtyards. Avoid touching the red-and-gold lacquered woodwork; do not litter or write on walls and columns. When it rains the brick floor is slippery, so walk slowly along the corridors."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/khue-van-cac-02-1697245075.jpeg", "Khuê Văn Các", "Khue Van Pavilion", false, 0),
                img("https://scov.gov.vn/upload/2005660/20210923/9a74b1772a6733d123f3875cb1ab38fb20190201%5C130936_DS_BiaTienSi.jpg", "Bia tiến sĩ", "Doctoral steles", false, 1),
                img("https://cdn.nhandan.vn/images/7f491ee6c6b660425d7c9ab03f1ec4476b41c4d12c4dd5b820657f70b2dbe1883fd1dae55d20024dbfd7d558efa95d485cfacd5ee898d181029010948d9846a0/hn2-1626710744002.jpg", "Hồ Văn", "Van Lake", false, 2)
            )),
            quote("“Nơi tôn vinh trí tuệ ngàn năm.”", "“A place that honors a thousand years of intellect.”"),
            divider(),
            mapBlock(new double[]{105.8356, 21.0279})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng hiếu học", "Symbol of learning")).slug("bieu-tuong-hieu-hoc").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Di sản quốc gia", "National heritage")).slug("di-san-quoc-gia").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 5) Hồ Tây & Chùa Trấn Quốc */
  private static PlaceDoc poiHoTayTranQuoc() {
    String slug = "ho-tay-chua-tran-quoc";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Hồ Tây & Chùa Trấn Quốc", "West Lake & Tran Quoc Pagoda"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Hồ lớn nhất Hà Nội; hoàng hôn đẹp, có chùa Trấn Quốc cổ kính bên bờ.", "Hanoi's largest lake with beautiful sunsets and the ancient Tran Quoc Pagoda on its shore."))
        .description(of("Đạp xe ven hồ, ngắm hoàng hôn; ghé chùa Trấn Quốc – ngôi chùa cổ nhất Thăng Long.", "Cycle along the lakeshore and watch the sunset; visit Tran Quoc Pagoda – the oldest pagoda in Thang Long."))
        .addressLine(of("Thanh Niên, Tây Hồ, Hà Nội", "Thanh Nien, Tay Ho, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8360, 21.0479})
        .vibes(List.of("relaxing", "peaceful", "romantic"))
        .atmosphere(of("lakeside-spiritual", "lakeside-spiritual"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography", "culture"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(90)
        .romanticScore(90)
        .localExperienceScore(75)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/buc-tranh-hoang-hon-ho-tay-moi-buoi-chieu-ta-dep-me-man-long-nguoi-01-1664336840.jpg", "Hồ Tây chiều tà", "West Lake at dusk", true, 0),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc", "Tran Quoc Pagoda", false, 1),
            img("https://resource.kinhtedothi.vn/2023/05/24/z4369045697220-36cc4bb9c8cc1daad3bb2277ca8088ba.jpg", "Lối đi ven hồ", "Lakeside walkway", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/ngam-hoang-hon-ho-tay-thuong-thuc-mon-qua-thien-nhien-cuoi-ngay-04-1650338508.jpg", "Hoàng hôn rực rỡ", "Brilliant sunset", false, 3),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/12/chua-tran-quoc-2.jpg", "Tháp nhiều tầng", "Multi-tiered tower", false, 4),
            img("https://statics.vinpearl.com/ca-phe-ho-tay-so-1_1681481823.jpg", "Quán cà phê view hồ", "Lake-view coffee shop", false, 5)
        ))
        .content(List.of(
            heading("Hồ Tây – góc nên thơ của thủ đô", "West Lake – a poetic corner of the capital"),
            quote("""
                    Gió chở mùi hương nước,
                    nắng rót xuống bờ xa;
                    chuông chùa lên nhịp lặng,
                    mặt hồ ủ hoàng hôn.
                """, """
                    The wind carries the scent of the water,
                    sunlight pours onto the far shore;
                    the temple bell rises in a quiet rhythm,
                    the lake's surface broods the sunset.
                """),
            paragraph("Vòng hồ rộng thoáng, đường cây rợp mát là nơi lý tưởng để dạo bộ, đạp xe hay ngồi quán ven nước ngắm trời chuyển sắc. Khung giờ 16:30–18:00 đẹp nhất: mây nhuộm vàng, mặt nước thay màu liên tục; tối đến, gió mát và phố đèn hắt xuống tạo mặt gương lấp lánh.", "The wide, open lakeshore and the cool tree-lined roads make it ideal for strolling, cycling, or sitting at a waterside cafe to watch the sky change color. The 16:30–18:00 window is the most beautiful: clouds tinged gold and the water shifting hue continuously; at night, the cool breeze and the street lights cast down create a sparkling mirror."),
            heading("Đạp xe – đi bộ ven hồ", "Cycling – walking along the lake"),
            paragraph("Cung dạo gợi ý: xuất phát từ đường Thanh Niên → men theo ven hồ qua các đoạn có vỉa hè rộng → dừng ở bến gió để chụp phản chiếu → ghé quán cà phê view hồ nghỉ 15–20 phút rồi vòng về. Đi giày đế êm, mang áo khoác mỏng khi trời nổi gió; đoạn gần bờ nước đôi khi trơn sau mưa, đi chậm giữ khoảng cách.", "Suggested route: start from Thanh Nien street → follow the lakeshore along sections with wide sidewalks → stop at a breezy spot to photograph the reflections → drop by a lake-view cafe to rest for 15–20 minutes, then loop back. Wear comfortable-soled shoes and bring a light jacket when it gets windy; sections near the water can be slippery after rain, so walk slowly and keep your distance."),
            heading("Chùa Trấn Quốc – cổ tự bên hồ", "Tran Quoc Pagoda – an ancient temple by the lake"),
            paragraph("Chùa Trấn Quốc nằm ngay dải đất hẹp giữa Hồ Tây và Trúc Bạch, nổi bật với tháp nhiều tầng đỏ thẫm. Khi vào khuôn viên chùa: mặc trang phục lịch sự, đi nhẹ nói khẽ, không xếp hàng chen lấn ở các lối hẹp; tắt flash khi chụp, tránh đứng chắn nhịp đi của người lễ Phật.", "Tran Quoc Pagoda sits on the narrow strip of land between West Lake and Truc Bach Lake, standing out with its deep-red multi-tiered tower. When entering the pagoda grounds: dress decently, walk softly and speak quietly, and do not push in the narrow passages; turn off your flash when shooting and avoid blocking the way of worshippers."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc", "Tran Quoc Pagoda", false, 0)),
            heading("Góc chụp & khung giờ vàng", "Photo angles & golden hours"),
            paragraph("Hoàng hôn: đứng trên đường Thanh Niên để lấy trọn tháp chùa với nền trời đổi sắc. Cận cảnh: chụp tháp nhiều tầng từ góc chéo 30–45° để thấy rõ cấu trúc và hàng tượng ni nhỏ trong hốc. Ở ven hồ, đặt đường chân trời ở 1/3 khung hình, tận dụng bóng cây làm đường dẫn. Luôn giữ an toàn, không với người – thiết bị quá mép nước.", "Sunset: stand on Thanh Nien street to frame the full pagoda tower against the color-shifting sky. Close-up: shoot the multi-tiered tower from a 30–45° diagonal to clearly see its structure and the rows of small Buddha statues in the niches. At the lakeside, place the horizon at one-third of the frame and use the tree shade as a leading line. Always stay safe and do not reach yourself or your gear beyond the water's edge."),
            heading("Café – trải nghiệm ven hồ", "Cafe – the lakeside experience"),
            paragraph("Dọc bờ có nhiều quán cà phê, trà chanh, kem; chọn quán có ban công/hiên rộng để ngắm trọn mặt hồ. Gọi phần đồ uống vừa phải, giữ bàn ghế gọn, không xả rác; khi đông, gửi xe đúng nơi quy định để tránh chắn lối đi bộ.", "Along the shore there are many cafes serving coffee, lemon tea and ice cream; choose one with a balcony or wide veranda to take in the whole lake. Order a modest amount of drinks, keep your table and chairs tidy, and do not litter; when it is crowded, park in the designated areas to avoid blocking the walkway."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/buc-tranh-hoang-hon-ho-tay-moi-buoi-chieu-ta-dep-me-man-long-nguoi-01-1664336840.jpg", "Hoàng hôn Hồ Tây", "West Lake sunset", false, 0),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Tran%20Quoc%20Pagoda/tran-quoc-pagoda-thumb.jpg", "Chùa Trấn Quốc bên hồ", "Tran Quoc Pagoda by the lake", false, 1),
                img("https://statics.vinpearl.com/ca-phe-ho-tay-so-1_1681481823.jpg", "Café ven hồ", "Lakeside cafe", false, 2)
            )),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Buổi chiều muộn có gió mạnh – giữ mũ, kính và thiết bị chụp ảnh cẩn thận; một số đoạn vỉa hè hẹp, ưu tiên người đi bộ khác và không đứng chắn lối. Tối cuối tuần thường đông, đeo balo phía trước khi chụp ảnh giữa đám đông. Tôn trọng không gian tôn nghiêm trong chùa, không nói lớn và không tựa vào lan can tháp.", "Late afternoons can be windy – keep your hat, glasses and camera gear secure; some sidewalk sections are narrow, so give way to other pedestrians and do not block the path. Weekend nights are usually crowded, so wear your backpack in front when taking photos in the crowd. Respect the sacred space inside the pagoda, do not talk loudly, and do not lean on the tower railing."),
            quote("“Hoàng hôn đẹp nhất thành phố.”", "“The most beautiful sunset in the city.”"),
            divider(),
            mapBlock(new double[]{105.8360, 21.0479})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên & Hồ", "Nature & Lake")).slug("thien-nhien-ho").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-giao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Ngắm hoàng hôn", "Sunset watching")).slug("ngam-hoang-hon").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đạp xe ven hồ", "Lakeside cycling")).slug("dap-xe-ven-ho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 6) Cầu Long Biên */
  private static PlaceDoc poiCauLongBien() {
    String slug = "cau-long-bien";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Cầu Long Biên", "Long Bien Bridge"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Cây cầu lịch sử bắc qua sông Hồng, kiến trúc Pháp đầu thế kỷ 20.", "A historic bridge over the Red River, built in early 20th-century French style."))
        .description(of("Đi bộ trên cầu, chụp ảnh tàu qua; sáng sớm có chợ Long Biên sầm uất dưới chân cầu.", "Walk across the bridge and photograph passing trains; in the early morning the bustling Long Bien market gathers beneath it."))
        .addressLine(of("Phúc Tân, Hoàn Kiếm – Ngọc Lâm, Long Biên, Hà Nội", "Phuc Tan, Hoan Kiem – Ngoc Lam, Long Bien, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8570, 21.0440})
        .vibes(List.of("cultural", "urban"))
        .atmosphere(of("historic-bridge", "historic-bridge"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("photography", "culture", "explore"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(75)
        .photographyScore(90)
        .romanticScore(70)
        .localExperienceScore(80)
        .images(withCover(
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/cau-long-bien-tren-cao.jpg", "Cầu Long Biên nhìn từ trên cao", "Long Bien Bridge from above", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/15_zing.jpg", "Mặt cầu sắt", "The steel bridge deck", false, 1),
            img("https://img.cand.com.vn/resize/800x800/NewFiles/Images/2024/09/10/tau_qua_cau_Long_Bien-1725933482770.jpeg", "Tàu qua cầu", "A train crossing the bridge", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/cho-long-bien-ha-noi-01-1700963622.jpeg", "Chợ Long Biên", "Long Bien market", false, 3),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Long_Bien_Bridge_Sunset.jpg/1200px-Long_Bien_Bridge_Sunset.jpg", "Chiều trên sông Hồng", "Afternoon over the Red River", false, 4),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MB/Long%20Bien%20Bridge/long-bien-bridge-thumb.jpg", "Góc ảnh hoài cổ", "A nostalgic photo angle", false, 5)
        ))
        .content(List.of(
            heading("Long Biên – chứng nhân thời gian", "Long Bien – a witness of time"),
            quote("""
                    Nhịp sắt hong chiều gió,
                    sông đỏ chảy mênh mang;
                    tiếng tàu quen nhịp thở,
                    phố cũ tựa vai ngang.
                """, """
                    Steel spans dry in the evening wind,
                    the red river flows on and on;
                    the train's sound, a familiar breath,
                    the old town leans shoulder to shoulder.
                """),
            paragraph("Cầu Long Biên do người Pháp xây đầu thế kỷ 20, bắc qua sông Hồng với kết cấu dàn thép đặc trưng. Đi bộ trên làn dành cho người đi bộ sẽ thấy rõ mối nối, đinh tán và lớp thời gian phủ rêu gỉ. Không khí trên cầu đổi theo giờ: sớm trong, trưa gắt nắng, chiều gió thổi mạnh từ sông.", "Long Bien Bridge was built by the French in the early 20th century, spanning the Red River with its distinctive steel-truss structure. Walking along the pedestrian lane, you clearly see the joints, the rivets and the layers of time covered in moss and rust. The air on the bridge changes by the hour: clear in the morning, harshly sunny at noon, and windy from the river in the afternoon."),
            heading("Lối dạo gợi ý 45–75 phút", "Suggested 45–75 minute stroll"),
            paragraph("Bắt đầu từ phía Phúc Tân → lên lối bộ hành sát lan can → đi chậm qua vài nhịp cầu để ngắm bãi giữa sông Hồng và phố cũ phía xa → dừng ở khoảng trống an toàn để nghỉ, chụp ảnh → nếu còn thời gian, sang phía Ngọc Lâm rồi vòng lại. Giữ lối cho xe máy, tránh đứng dàn hàng ngang.", "Start from the Phuc Tan side → step onto the pedestrian lane beside the railing → walk slowly across a few bridge spans to view the islet in the Red River and the old town in the distance → stop at a safe open spot to rest and take photos → if you have time, cross over to the Ngoc Lam side and loop back. Keep the lane clear for motorbikes and avoid standing side by side across it."),
            heading("Chụp ảnh & khoảnh khắc tàu qua", "Photography & the moment a train passes"),
            paragraph("Khung giờ đẹp: bình minh (ánh xiên dịu, sương nhẹ trên sông) và hoàng hôn (màu trời ấm). Khi chụp tàu, đứng trong phạm vi cho phép, giữ khoảng cách an toàn với ray; không ngồi – đứng trên đường ray, không vươn người/thiết bị qua lan can. Ống góc rộng cho cảnh toàn; ống trung cho chi tiết đinh tán, nhịp dàn thép.", "Best times: sunrise (gentle slanting light, light mist over the river) and sunset (warm sky tones). When photographing trains, stand within the permitted area and keep a safe distance from the rails; do not sit or stand on the tracks, and do not lean yourself or your gear over the railing. Use a wide-angle lens for the full scene and a medium lens for details of rivets and steel-truss spans."),
            imageBlock(img("https://cdn.tienphong.vn/images/814b5533c866dc3540018a126103e93541d1f826619b0ec86a8ba3921ccb0fb44047a1e2f8ae6b9fbda62cb532f924a6/tp-6-8804.jpg", "Khoảnh khắc tàu qua", "The moment a train passes", false, 0)),
            heading("Chợ Long Biên buổi sớm", "Long Bien market at dawn"),
            paragraph("Dưới chân cầu phía bờ Phúc Tân là chợ đầu mối nhộn nhịp tầm 3:00–6:00 sáng. Nếu muốn ghi lại không khí chợ: mang giày kín, bảo quản tư trang, xin phép khi chụp cận cảnh; đi gọn một bên lối, tránh chắn đường vận chuyển hàng.", "Beneath the bridge on the Phuc Tan side is a wholesale market bustling around 3:00–6:00 in the morning. If you want to capture the market atmosphere: wear closed shoes, keep your belongings safe, and ask permission for close-ups; stay neatly to one side and avoid blocking the goods-transport routes."),
            heading("Góc ngắm sông Hồng & bãi giữa", "Views of the Red River & the islet"),
            paragraph("Từ vài nhịp giữa cầu có thể phóng tầm mắt về bãi bồi, vườn chuối, lều nhỏ của người làm vườn. Khi gió lớn, hạn chế đặt đồ lên lan can; nếu muốn phơi dài để bắt vệt xe trên cầu, dùng mini-tripod và bám chặt tay vịn.", "From a few mid-bridge spans you can gaze out at the alluvial flats, banana gardens and the small huts of the gardeners. When it is windy, avoid placing items on the railing; if you want a long exposure to catch the light trails of vehicles on the bridge, use a mini-tripod and hold the handrail firmly."),
            gallery(List.of(
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/08/cau-long-bien-tren-cao.jpg", "Cầu Long Biên từ trên cao", "Long Bien Bridge from above", false, 0),
                img("https://img.cand.com.vn/resize/800x800/NewFiles/Images/2024/09/10/tau_qua_cau_Long_Bien-1725933482770.jpeg", "Khoảnh khắc tàu qua", "The moment a train passes", false, 1),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Long_Bien_Bridge_Sunset.jpg/1200px-Long_Bien_Bridge_Sunset.jpg", "Chiều trên sông Hồng", "Afternoon over the Red River", false, 2)
            )),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Luôn đi trong dải bộ hành, quan sát xe máy đi sát; không trèo qua lan can, không ngồi trên ray/khớp nối. Mặt cầu có chỗ khấp khểnh – đi chậm, tránh giày gót cao. Trời mưa gió lớn hoặc sấm sét thì không nên lên cầu. Không xả rác, không khắc vẽ lên dầm thép – đây là di sản đang được bảo tồn.", "Always stay within the pedestrian lane and watch for motorbikes passing close; do not climb over the railing or sit on the rails/joints. The bridge deck has uneven spots – walk slowly and avoid high heels. Do not go onto the bridge in heavy rain, strong wind or thunderstorms. Do not litter or carve on the steel girders – this is a heritage site under preservation."),
            quote("“Gỉ sét kể chuyện, sông Hồng chảy mãi.”", "“The rust tells the story; the Red River flows forever.”"),
            divider(),
            mapBlock(new double[]{105.8570, 21.0440})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng thủ đô", "Capital icon")).slug("bieu-tuong-thu-do").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Nhiếp ảnh", "Photography")).slug("nhiep-anh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 7) Nhà tù Hoả Lò (Maison Centrale) */
  private static PlaceDoc poiNhaTuyHoaLo() {
    String slug = "nha-tu-hoa-lo";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Nhà tù Hoả Lò (Maison Centrale)", "Hoa Lo Prison (Maison Centrale)"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Di tích lịch sử đặc biệt, kể câu chuyện đấu tranh kiên cường.", "A special historical site that tells a story of indomitable struggle."))
        .description(of("Khu trưng bày tái hiện thời kỳ nhà tù thuộc địa; nên tham quan có hướng dẫn để hiểu sâu ngữ cảnh lịch sử.", "The exhibition recreates the colonial-era prison; a guided tour is recommended to understand the historical context deeply."))
        .addressLine(of("1 Hoả Lò, Hoàn Kiếm, Hà Nội", "1 Hoa Lo, Hoan Kiem, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8462, 21.0250})
        .vibes(List.of("educational", "cultural"))
        .atmosphere(of("museum-indoor", "museum-indoor"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(60)
        .romanticScore(20)
        .localExperienceScore(90)
        .images(withCover(
            img("http://thquangtrung.hoankiem.edu.vn/upload/29321/fck/files/hl1.jpg", "Cổng Nhà tù Hoả Lò", "Gate of Hoa Lo Prison", true, 0),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/2007.nha-tu-hoa-lo-tour-du-khach_1.jpg", "Không gian trưng bày", "Exhibition space", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tu-hoa-lo-di-tich-lich-su-noi-tieng-tai-ha-noi-03-1639725599.png", "Phòng giam tái hiện", "Recreated prison cell", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tu-hoa-lo-di-tich-lich-su-noi-tieng-tai-ha-noi-06-1639725600.png", "Tư liệu lịch sử", "Historical documents", false, 3),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo2.jpg", "Hành lang di tích", "Relic corridor", false, 4),
            img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo3.jpeg", "Khách tham quan", "Visitors", false, 5)
        ))
        .content(List.of(
            heading("Hoả Lò – ký ức không quên", "Hoa Lo – an unforgettable memory"),
            quote("""
                    Tường đá sâu hơi thở,
                    then sắt kể đêm dài;
                    bước nhẹ qua ô cửa,
                    nhớ để quý ngày mai.
                """, """
                    Stone walls breathe deep,
                    iron bolts recount the long nights;
                    step softly past the doorways,
                    remember, to cherish tomorrow.
                """),
            paragraph("Nhà tù Hoả Lò là di tích đặc biệt gắn với giai đoạn thuộc địa và những câu chuyện đấu tranh kiên cường. Không gian trưng bày tái hiện buồng giam, phòng biệt lập, hiện vật và tư liệu gốc; ánh sáng chủ ý thấp để gợi không khí nặng nề – hãy đi chậm, đọc kỹ bảng thuyết minh để hiểu bối cảnh lịch sử.", "Hoa Lo Prison is a special relic tied to the colonial period and stories of indomitable struggle. The exhibition recreates the prison cells, the solitary confinement rooms, and the original artifacts and documents; the lighting is intentionally dim to evoke a heavy atmosphere – walk slowly and read the interpretive panels carefully to understand the historical context."),
            heading("Cách tham quan gợi ý (60–90 phút)", "Suggested way to visit (60–90 minutes)"),
            paragraph("Bắt đầu từ khu giới thiệu tổng quan → đi qua dãy buồng giam tập thể để cảm nhận không gian chật hẹp → phòng biệt giam (xà lim) với kỷ luật khắc nghiệt → khu trưng bày tư liệu, bản án, đồ dùng tù nhân → kết thúc ở không gian tri ân, nơi nhắc nhớ giá trị hoà bình. Ở mỗi điểm dừng, dành 2–3 phút đối chiếu chú thích – mốc thời gian.", "Start at the overview area → walk through the rows of communal cells to feel the cramped space → the solitary confinement cells with their harsh discipline → the exhibition of documents, verdicts and prisoners' belongings → end at the commemorative space that recalls the value of peace. At each stop, spend 2–3 minutes cross-checking the captions and timeline."),
            heading("Những điểm nên dừng lâu", "Spots worth lingering at"),
            paragraph("Mô hình – ảnh tư liệu về điều kiện giam giữ; hiện vật như cùm sắt, xiềng, bát – thìa; câu chuyện vượt ngục và hoạt động bí mật trong tù. Chú ý cách trưng bày sử dụng âm thanh – ánh sáng để tái hiện tâm trạng; đọc kỹ trích dẫn thư từ để thấy chiều sâu nhân bản giữa khắc nghiệt.", "Models and archival photos of the detention conditions; artifacts such as iron shackles, chains and bowls and spoons; the stories of prison breaks and secret activities inside. Notice how the display uses sound and light to recreate the mood; read the quoted letters carefully to feel the human depth amid the harshness."),
            heading("Thuyết minh & trải nghiệm học tập", "Narration & learning experience"),
            paragraph("Nên sử dụng thuyết minh viên/ audio guide để kết nối mạch sự kiện, thuật ngữ và nhân vật. Ghi chú nhanh các mốc năm, tên địa danh để tra cứu thêm sau buổi tham quan. Với nhóm học sinh – sinh viên, phân công người đọc – người chụp lại bảng thông tin (ở khu cho phép) để tổng hợp tài liệu.", "Use a guide or audio guide to connect the flow of events, terms and figures. Quickly jot down the milestone years and place names for further research after the visit. For groups of students, assign some to read and some to photograph the information panels (in permitted areas) to compile material."),
            imageBlock(img("https://media.vov.vn/sites/default/files/styles/large_watermark/public/2023-07/trung-bay-thuong-binh-liet-si-nha-tu-hoa-lo_3.jpg", "Không gian trưng bày", "Exhibition space", false, 0)),
            heading("Quy định chụp ảnh & ứng xử", "Photography rules & etiquette"),
            paragraph("Một số khu vực hạn chế quay/chụp – tôn trọng biển báo. Tắt flash, không đặt máy/ba lô lên hiện vật hay bệ trưng bày; đi nhẹ, nói khẽ, không đùa cợt trong không gian tưởng niệm. Nếu đông khách, xếp hàng theo lối một chiều, tránh đứng chắn bảng chú thích.", "Some areas restrict filming/photography – respect the signs. Turn off the flash and do not place cameras or backpacks on the artifacts or display pedestals; walk softly, speak quietly, and do not joke around in the memorial space. If crowded, follow the one-way line and avoid blocking the caption panels."),
            heading("Khả năng tiếp cận & lưu ý an toàn", "Accessibility & safety notes"),
            paragraph("Lối đi trong nhà bằng phẳng, tuy có đoạn hẹp – đi sát phải để nhường lối. Mang giày đế êm, áo khoác mỏng vì điều hoà có thể lạnh. Giữ tư trang gọn gàng; trẻ nhỏ nên đi kèm người lớn và được giải thích phù hợp lứa tuổi.", "The indoor paths are flat, though some sections are narrow – keep to the right to give way. Wear comfortable-soled shoes and a light jacket as the air conditioning can be cold. Keep your belongings tidy; small children should be accompanied by adults and given age-appropriate explanations."),
            gallery(List.of(
                img("http://thquangtrung.hoankiem.edu.vn/upload/29321/fck/files/hl1.jpg", "Cổng Nhà tù Hoả Lò", "Gate of Hoa Lo Prison", false, 0),
                img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/2007.nha-tu-hoa-lo-tour-du-khach_1.jpg", "Không gian trưng bày", "Exhibition space", false, 1),
                img("https://images.vietnamtourism.gov.vn/vn//images/2022/thang_7/1207.nha-tu-hoa-lo2.jpg", "Hành lang di tích", "Relic corridor", false, 2)
            )),
            heading("Tưởng niệm & thông điệp", "Commemoration & message"),
            paragraph("Mục đích của chuyến tham quan không chỉ là nhìn lại quá khứ mà còn để trân trọng hiện tại: hoà bình, nhân phẩm và tự do. Hãy rời khu trưng bày với một khoảnh lặng – dành phút tưởng nhớ những người đã hy sinh.", "The purpose of the visit is not only to look back at the past but also to cherish the present: peace, dignity and freedom. Leave the exhibition with a moment of silence – take a minute to remember those who sacrificed."),
            quote("“Nhớ để trân trọng hoà bình.”", "“Remember, so as to cherish peace.”"),
            divider(),
            mapBlock(new double[]{105.8462, 21.0250})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Giáo dục lịch sử", "Historical education")).slug("giao-duc-lich-su").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tham quan có hướng dẫn", "Guided tour")).slug("tham-quan-co-huong-dan").type(TagType.SERVICE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 8) Hoàng thành Thăng Long (Imperial Citadel) */
  private static PlaceDoc poiHoangThanhThangLong() {
    String slug = "hoang-thanh-thang-long";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Hoàng thành Thăng Long (Imperial Citadel)", "Imperial Citadel of Thang Long"))
        .slug(slug)
        .parentSlug("ha-noi")
        .ancestors(List.of("ha-noi"))
        .childrenCount(0)
        .shortDescription(of("Di sản văn hoá thế giới UNESCO, trung tâm quyền lực kinh thành xưa.", "A UNESCO World Cultural Heritage site and the power center of the ancient citadel."))
        .description(of("Tham quan Đoan Môn, Kỳ Đài, nền điện Kính Thiên, khu khảo cổ 18 Hoàng Diệu; không gian xanh rộng rãi.", "Visit Doan Mon, the Flag Tower, the Kinh Thien Palace foundation and the 18 Hoang Dieu archaeological site; spacious green grounds."))
        .addressLine(of("19C Hoàng Diệu, Ba Đình, Hà Nội", "19C Hoang Dieu, Ba Dinh, Hanoi"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8350, 21.0360})
        .vibes(List.of("educational", "cultural", "peaceful"))
        .atmosphere(of("imperial-heritage", "imperial-heritage"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(85)
        .romanticScore(55)
        .localExperienceScore(90)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-1-1732809742.jpg", "Đoan Môn – Hoàng thành", "Doan Mon – the Imperial Citadel", true, 0),
            img("https://statics.vinpearl.com/cua-bac-3_1674836261.jpg", "Cửa Bắc", "North Gate", false, 1),
            img("https://statics.vinpearl.com/cua-bac-4_1674836249.jpg", "Di tích còn sót lại", "Surviving relics", false, 2),
            img("https://hoangthanhthanglong.vn/wp-content/uploads/2013/08/doanmon1.jpg", "Không gian xanh", "Green grounds", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-4-1732809743.jpg", "Di tích cổ kính", "Ancient relics", false, 4),
            img("https://disantrangan.vn/wp-content/uploads/2021/02/hinh-anh-hoang-thanh-thang-long-6.jpg", "Góc chụp toàn cảnh", "A panoramic photo angle", false, 5)
        ))
        .content(List.of(
            heading("Hoàng thành – di sản giữa lòng đô thị", "The Imperial Citadel – heritage in the heart of the city"),
            quote("""
                    Gạch xưa phai dấu bụi,
                    cổng cổ lọc heo may;
                    bước nhẹ qua trăm thế,
                    lịch sử tự mở tay.
                """, """
                    Old bricks fade beneath the dust,
                    ancient gates filter the autumn breeze;
                    step softly across a hundred ages,
                    and history opens its own hand.
                """),
            paragraph("Quần thể Hoàng thành Thăng Long là Di sản văn hoá thế giới UNESCO – trung tâm quyền lực kinh thành xưa, nơi các triều đại để lại lớp lớp dấu tích. Không gian rộng, nhiều mảng xanh và công trình qua các thời kỳ; đi chậm để nhận ra sự giao thoa giữa thành lũy – cổng môn – nền điện và các hố khai quật khảo cổ.", "The Imperial Citadel of Thang Long is a UNESCO World Cultural Heritage site – the power center of the ancient citadel, where successive dynasties left layer upon layer of traces. The space is wide, with many green patches and structures from different eras; walk slowly to perceive the interplay between the ramparts, the gates, the palace foundations and the archaeological excavation pits."),
            heading("Lộ trình tham quan gợi ý (90–120 phút)", "Suggested tour route (90–120 minutes)"),
            paragraph("Từ cổng Đoan Môn vào trục chính → dừng ở nền điện Kính Thiên đọc bảng giới thiệu về không gian triều nghi → sang Cột Cờ (Kỳ Đài) ngắm toàn cảnh trục thành cũ → tiếp tục qua Cửa Bắc – nơi lưu dấu lịch sử hiện đại → kết thúc bằng khu khảo cổ 18 Hoàng Diệu để thấy các lớp nền kiến trúc qua nhiều thế kỷ.", "From the Doan Mon gate enter the main axis → stop at the Kinh Thien Palace foundation to read the panel about the ceremonial space → move to the Flag Tower to take in the panorama of the old citadel axis → continue past the North Gate – which bears the marks of modern history → finish at the 18 Hoang Dieu archaeological site to see the architectural foundation layers across many centuries."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-14-1732809749.jpg", "Đoan Môn cổ kính", "The ancient Doan Mon", false, 0)),
            heading("Những điểm nên dừng lâu", "Spots worth lingering at"),
            paragraph("Đoan Môn: cổng chính với kết cấu vòm và gác lầu – quan sát dấu tích tu bổ ở mạch gạch. Nền điện Kính Thiên: đọc kỹ sơ đồ mặt bằng để hình dung trục nghi lễ và bậc đá rồng. Cửa Bắc: xem mảng tường thành và tư liệu lịch sử. Khu 18 Hoàng Diệu: chú ý lớp gạch, chân tường, cống thoát nước cổ – mỗi lớp là một mốc thời gian.", "Doan Mon: the main gate with its arched structure and upper pavilion – observe the restoration traces in the brick joints. The Kinh Thien Palace foundation: read the floor plan carefully to envision the ceremonial axis and the dragon stone steps. North Gate: view the rampart section and historical documents. The 18 Hoang Dieu site: note the brick layers, wall bases and ancient drainage channels – each layer is a milestone in time."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & small tips"),
            paragraph("Đoan Môn đẹp ở góc chéo 30–45° để thấy rõ tầng mái – cửa vòm; ở nền Kính Thiên, lùi vài nhịp đặt bậc rồng làm đường dẫn; trong khu khảo cổ, chụp từ mép lối đi để giữ an toàn và không vượt rào bảo vệ. Sáng sớm và cuối chiều cho ánh xiên ấm, bóng cây đổ dài rất “điện ảnh”.", "Doan Mon looks best at a 30–45° diagonal to clearly show the roof tiers and the arched gate; at the Kinh Thien foundation, step back a few paces and use the dragon steps as a leading line; in the archaeological area, shoot from the edge of the path to stay safe and not cross the protective barrier. Early morning and late afternoon give warm slanting light and long tree shadows that look very \"cinematic\"."),
            heading("Trưng bày – tư liệu", "Exhibits – documents"),
            paragraph("Các nhà trưng bày trong khuôn viên cung cấp bản đồ, mô hình và hiện vật khai quật. Hãy đọc bảng tóm lược theo mốc triều đại để xâu chuỗi: Đại La – Lý – Trần – Lê – Nguyễn – thời cận/hiện đại. Ghi chú nhanh tên cổng, trục đường xưa (Đoan Môn, Kính Thiên, Cửa Bắc…) để đối chiếu khi ra ngoài sân.", "The exhibition halls within the grounds provide maps, models and excavated artifacts. Read the summary panels by dynasty to connect the thread: Dai La – Ly – Tran – Le – Nguyen – the modern era. Quickly jot down the names of the gates and old axes (Doan Mon, Kinh Thien, North Gate...) to cross-reference when you go back out to the courtyard."),
            heading("Quy định & lưu ý", "Rules & notes"),
            paragraph("Giữ trật tự, không leo trèo lên nền di tích, không bước qua dây – rào bảo vệ; không chạm vào hiện vật, không xả rác. Trời nắng mang mũ, nước; đi giày đế êm vì di chuyển nhiều. Khi chụp ảnh, tắt flash trong nhà trưng bày và không dùng drone ở khu vực cấm.", "Keep order, do not climb on the relic foundations, and do not step over the protective ropes and barriers; do not touch the artifacts and do not litter. When it is sunny, bring a hat and water; wear comfortable-soled shoes as there is much walking. When photographing, turn off the flash inside the exhibition halls and do not use a drone in restricted areas."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/doan-mon-1-1732809742.jpg", "Đoan Môn – cửa chính", "Doan Mon – the main gate", false, 0),
                img("https://statics.vinpearl.com/cua-bac-3_1674836261.jpg", "Cửa Bắc lịch sử", "The historic North Gate", false, 1),
                img("https://disantrangan.vn/wp-content/uploads/2021/02/hinh-anh-hoang-thanh-thang-long-6.jpg", "Khu di tích – mảng xanh", "The relic site – green patches", false, 2)
            )),
            quote("“Di sản sống giữa nhịp đô thị.”", "“A living heritage amid the rhythm of the city.”"),
            divider(),
            mapBlock(new double[]{105.8350, 21.0360})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Di sản UNESCO", "UNESCO heritage")).slug("di-san-unesco").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Khảo cổ", "Archaeology")).slug("khao-co").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }
}
