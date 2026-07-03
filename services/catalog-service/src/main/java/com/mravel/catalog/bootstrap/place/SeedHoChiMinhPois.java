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
        .name(of("Nhà thờ Đức Bà Sài Gòn", "Saigon Notre-Dame Cathedral"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Thánh đường kiểu Roman – Gothic biểu tượng của Sài Gòn.", "An iconic Romanesque–Gothic cathedral of Saigon."))
        .description(of("Công trình Pháp cuối thế kỷ 19 với gạch đỏ Marseille, mái vòm cao; quảng trường bồ câu và tượng Đức Mẹ phía trước.", "A late-19th-century French building with red Marseille bricks and lofty vaults; a pigeon-filled square and a statue of Our Lady stand in front."))
        .addressLine(of("01 Công xã Paris, Bến Nghé, Quận 1, TP. HCM", "01 Cong xa Paris, Ben Nghe, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.7009, 10.7798})
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-1-1736393709.jpg", "Mặt tiền Nhà thờ Đức Bà", "Facade of Notre-Dame Cathedral", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-tu-tren-cao-1692570417.jpg", "Quảng trường phía trước", "The square out front", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/thanh-duong-nha-tho-duc-ba-sai-gon-1692570430.jpg", "Không gian nội thất", "Interior space", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-tu-sua-1692570417.jpg", "Góc nhìn bên hông", "Side view", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-5-1733709376.jpg", "Nhà thờ về đêm", "The cathedral at night", false, 4),
            img("https://booking.muongthanh.com/upload_images/images/nha-tho-duc-ba-buu-dien-trung-tam-sai-gon.jpg", "Kề bên Bưu điện", "Next to the Central Post Office", false, 5)
        ))
        .content(List.of(
            heading("Nhà thờ Đức Bà – biểu tượng Pháp thuộc", "Notre-Dame Cathedral – a colonial French icon"),
            quote("""
                    Gạch đỏ ôm tia nắng,
                    chuông ngân giữa phố đông;
                    vòm cao nâng tiếng thở,
                    lá me rơi thành sông.
                """, "Red bricks embrace the sunlight,\nbells ring across the busy street;\nhigh vaults lift each breath,\ntamarind leaves fall like a river."),
            paragraph("Thánh đường cuối thế kỷ 19 do người Pháp xây theo phong cách Roman – Gothic, nổi bật với gạch đỏ Marseille, hàng cột cao vút và các cửa sổ hoa hồng. Quảng trường phía trước là điểm tụ họp quen thuộc, nơi đàn bồ câu sà xuống giữa nhịp xe và bóng hàng me cổ thụ.", "Built by the French in the late 19th century in Romanesque–Gothic style, the cathedral stands out with red Marseille bricks, soaring columns and rose windows. The square in front is a familiar gathering spot where flocks of pigeons swoop down amid the traffic and the shade of old tamarind trees."),
            heading("Cách tham quan gợi ý (45–75 phút)", "Suggested visit (45–75 minutes)"),
            paragraph("Đến buổi sáng để bắt ánh xiên qua mặt đứng gạch → dạo vòng quanh để quan sát các nhịp vòm, cửa sổ kính màu và các mảng tu bổ → nếu mở cửa, vào khu nội thất đi nhẹ nói khẽ, dành vài phút nhìn lên các vòm cuốn và lối ánh sáng → băng qua đường thăm Bưu điện Trung tâm Sài Gòn với mái vòm sắt – kính cổ điển.", "Come in the morning to catch the light slanting across the brick facade → walk around to take in the arched bays, stained-glass windows and restoration patches → if it is open, step inside, tread softly and speak quietly, spending a few minutes looking up at the vaults and the play of light → cross the street to visit the Saigon Central Post Office with its classic iron-and-glass roof."),
            imageBlock(img("https://phuot3mien.com/wp-content/uploads/2024/06/nha-tho-duc-ba.jpg", "Façade gạch đỏ đặc trưng", "Signature red-brick facade", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý", "Architecture & notable details"),
            paragraph("Mặt đứng đối xứng, hai tháp chuông vươn cao (khi tu bổ có thể che lưới), gạch không trát giúp lộ màu thời gian; bên trong là dãy cột đá, vòm cuốn và các ô cửa kính màu kể tích Thánh. Quan sát mối nối, phào chỉ, hoa văn trên đỉnh cột để thấy sự giao thoa Roman – Gothic trong khí hậu nhiệt đới.", "The facade is symmetrical, with two tall bell towers (sometimes netted during restoration); the unplastered bricks reveal the colour of time, while inside stand rows of stone columns, arched vaults and stained-glass windows depicting biblical scenes. Look at the joints, mouldings and column-capital motifs to see the blend of Romanesque and Gothic in a tropical climate."),
            heading("Ứng xử trong không gian thờ tự", "Etiquette in a place of worship"),
            paragraph("Trang phục lịch sự, nói khẽ, tắt chuông điện thoại; không chụp ảnh khu vực có biển cấm, không dùng flash hướng thẳng vào bài trí thờ tự; đi theo lối chỉ dẫn, không chạm tay vào hiện vật. Trong giờ lễ, ưu tiên không gian cho giáo dân, đứng ngoài quan sát nếu không tham dự.", "Dress modestly, speak quietly and silence your phone; do not photograph areas marked off-limits and avoid pointing a flash at the altar; follow the marked route and do not touch the artefacts. During Mass, give the space to the congregation and observe from outside if you are not attending."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & tips"),
            paragraph("Sáng sớm: đứng chéo 30–45° để bắt khối vòm và bóng cây me; chính diện: lùi về phía quảng trường để lấy trọn hai tháp và tượng Đức Mẹ; chiều muộn: khai thác phản chiếu trên kính Bưu điện phía đối diện. Tránh đứng giữa dòng xe để chụp, giữ an toàn ở vạch qua đường.", "Early morning: stand at a 30–45° angle to capture the vaulted mass and the tamarind shade; head-on: step back toward the square to fit in both towers and the statue of Our Lady; late afternoon: use the reflections on the Post Office glass across the street. Do not stand amid the traffic to shoot, and stay safe at the crosswalk."),
            heading("Kết hợp tham quan lân cận", "Combine with nearby sights"),
            paragraph("Từ Nhà thờ băng qua thăm Bưu điện Trung tâm (kiến trúc Pháp – Á độc đáo), đi bộ 5–10 phút đến phố sách Nguyễn Văn Bình hoặc Dinh Độc Lập. Khu vực nhiều quán cà phê bệt – giữ vệ sinh, không xả rác nơi công cộng.", "From the cathedral, cross over to the Central Post Office (a distinctive Franco-Asian design), then walk 5–10 minutes to Nguyen Van Binh Book Street or the Independence Palace. The area has many sidewalk cafes – keep it clean and do not litter in public."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-1-1736393709.jpg", "Mặt tiền gạch đỏ", "Red-brick facade", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/thanh-duong-nha-tho-duc-ba-sai-gon-1692570430.jpg", "Không gian nội thất", "Interior space", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-5-1733709376.jpg", "Nhà thờ về đêm", "The cathedral at night", false, 2)
            )),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Khu trung tâm đông phương tiện: băng qua đường đúng vạch, quan sát kỹ; giữ tư trang khi chụp ảnh ở quảng trường; trong thời gian tu bổ có thể có rào chắn – tôn trọng lối đi và hướng dẫn tại chỗ.", "The downtown area is busy with traffic: cross at the crosswalk and look carefully; keep an eye on your belongings while shooting in the square; during restoration there may be barriers – respect the marked paths and on-site instructions."),
            quote("“Gạch đỏ lưu dấu thời gian, chuông ngân giữa lòng đô thị.”", "“Red bricks keep the mark of time, bells ringing in the heart of the city.”"),
            divider(),
            mapBlock(new double[]{106.7009, 10.7798})
        ))
        .vibes(List.of("cultural", "peaceful"))
        .atmosphere(of("heritage-colonial", "heritage-colonial"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(90)
        .romanticScore(70)
        .localExperienceScore(75)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious building")).slug("cong-giao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng thành phố", "City landmark")).slug("bieu-tuong-thanh-pho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 2) Bưu điện Trung tâm Sài Gòn */
  private static PlaceDoc poiBuuDienTrungTam() {
    String slug = "buu-dien-trung-tam-sai-gon";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bưu điện Trung tâm Sài Gòn", "Saigon Central Post Office"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Công trình kiến trúc Pháp do Gustave Eiffel tham gia thiết kế.", "A French-era building reputedly designed with the involvement of Gustave Eiffel."))
        .description(of("Trần vòm cao, bản đồ Sài Gòn xưa trên tường; nơi gửi bưu thiếp, mua tem và quà lưu niệm đặc trưng.", "High vaulted ceilings and old maps of Saigon on the walls; a place to send postcards, buy stamps and pick up distinctive souvenirs."))
        .addressLine(of("02 Công xã Paris, Bến Nghé, Quận 1, TP. HCM", "02 Cong xa Paris, Ben Nghe, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.7019, 10.7793})
        .images(withCover(
            img("https://images2.thanhnien.vn/528068263637045248/2023/6/5/hcm-1685938434919901822107.jpg", "Mặt tiền Bưu điện Trung tâm", "Facade of the Central Post Office", true, 0),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kien-truc-buu-dien-trung-tam-sai-gon-3.jpg", "Nội thất trần vòm", "Vaulted-ceiling interior", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/buu-dien-thanh-pho-ban-dem-1692393906.jpg", "Bưu điện về đêm", "The post office at night", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/quay-gui-thu-buu-dien-thanh-pho-1692393928.jpg", "Quầy dịch vụ", "Service counter", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/buu-dien-thanh-pho-1-1736393709.jpg", "Góc chụp toàn cảnh", "Panoramic view", false, 4),
            img("https://kenh14cdn.com/k:thumb_w/600/semDvPi1aN9DfITbkDIeRqM1dwMsLc/Image/2015/ngay-7/1-b12be/buu-dien-trung-tam-lac-long-giua-khong-gian-sai-gon-vi-mau-vang-ruc.jpg", "Đối diện Nhà thờ Đức Bà", "Opposite Notre-Dame Cathedral", false, 5)
        ))
        .content(List.of(
            heading("Bưu điện – hoài niệm Sài Gòn xưa", "The Post Office – nostalgia for old Saigon"),
            quote("""
                    Vòm vàng ôm tiếng bước,
                    kim đồng hồ chạm trưa;
                    tem nhỏ mang lời phố,
                    thư bay qua nắng xưa.
                """, "Golden vaults embrace the footsteps,\nthe clock hands touch noon;\ntiny stamps carry the city's words,\nletters fly through the old sunlight."),
            paragraph("Bưu điện Trung tâm là một trong những công trình Pháp đẹp nhất ở Sài Gòn: mặt tiền màu vàng – trắng cổ điển, đồng hồ trung tâm, hàng cửa vòm đối xứng. Bước vào sảnh là cảm giác “time travel”: trần vòm cao, quầy gỗ, sàn gạch hoa và tiếng gọi số dịch vụ vang nhẹ.", "The Central Post Office is one of the finest French buildings in Saigon: a classic yellow-and-white facade, a central clock and rows of symmetrical arched doors. Stepping into the hall feels like time travel: high vaulted ceilings, wooden counters, patterned tile floors and the soft calling of service numbers."),
            heading("Cách tham quan gợi ý (45–75 phút)", "Suggested visit (45–75 minutes)"),
            paragraph("Đứng ngoài quảng trường quan sát mặt đứng và đồng hồ → vào sảnh chính, ngẩng nhìn kết cấu vòm thép, quầy gỗ → dừng lâu ở hai tấm bản đồ vẽ tay hai bên (Sài Gòn – Chợ Lớn xưa; tuyến viễn thông Đông Dương) → ghé quầy tem – bưu thiếp để gửi vài dòng cho chính mình hoặc người thân.", "Stand in the square to take in the facade and the clock → enter the main hall and look up at the steel-vault structure and wooden counters → linger at the two hand-painted maps on either side (old Saigon – Cho Lon; the telecommunication lines of Indochina) → drop by the stamp-and-postcard counter to send a few lines to yourself or loved ones."),
            imageBlock(img("https://static.vinwonders.com/production/buu-dien-trung-tam-sai-gon-5.jpg", "Không gian nội thất", "Interior space", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý", "Architecture & notable details"),
            paragraph("Hệ vòm thép – kính lấy sáng dọc trục chính, nhịp cột đều và mảng trang trí kiểu cổ điển; quầy gỗ chạy dài hai bên tạo nhịp điệu thị giác. Hãy để ý các họa tiết đúc trên đầu cột, biển chỉ dẫn chữ men xanh, ô cửa bán nguyệt và những chi tiết đồng cũ ‘kể chuyện’ qua lớp thời gian.", "A steel-and-glass vault brings light along the main axis, with evenly spaced columns and classical decorative panels; long wooden counters on both sides create a visual rhythm. Notice the cast motifs on the column capitals, the blue-enamel signage, the semicircular windows and the old brass details that 'tell stories' through layers of time."),
            heading("Gửi thư – chơi tem & quà lưu niệm", "Sending mail – stamps & souvenirs"),
            paragraph("Mua bưu thiếp chủ đề Sài Gòn, viết vài dòng rồi dán tem và gửi ngay tại quầy – một trải nghiệm rất “đúng chất bưu điện”. Khu lưu niệm có tem chủ đề, con dấu kỷ niệm, sổ tay, bút máy… Phù hợp làm quà nhỏ gọn; nhớ giữ hóa đơn nếu cần đổi.", "Buy a Saigon-themed postcard, write a few lines, then stick on a stamp and send it right at the counter – a truly 'post-office' experience. The souvenir corner offers themed stamps, commemorative seals, notebooks, fountain pens and more, perfect as compact gifts; keep your receipt in case you need an exchange."),
            heading("Góc chụp ảnh & ứng xử", "Photo spots & etiquette"),
            paragraph("Chụp chính diện từ ngoài quảng trường để lấy trọn mặt tiền; vào trong, đứng ở trục giữa, lùi vài nhịp để vòm nối vòm tạo chiều sâu. Tránh dùng flash, không đứng chắn lối quầy dịch vụ, giữ trật tự để tôn trọng không gian làm việc.", "Shoot head-on from the square to fit the whole facade; inside, stand on the central axis and step back a few bays so the vaults line up to create depth. Avoid using a flash, do not block the service counters and keep order out of respect for the working space."),
            gallery(List.of(
                img("https://images2.thanhnien.vn/528068263637045248/2023/6/5/hcm-1685938434919901822107.jpg", "Mặt tiền bưu điện", "Post office facade", false, 0),
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kien-truc-buu-dien-trung-tam-sai-gon-3.jpg", "Trần vòm & quầy gỗ", "Vaulted ceiling & wooden counters", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/quay-gui-thu-buu-dien-thanh-pho-1692393928.jpg", "Quầy gửi thư", "Mailing counter", false, 2)
            )),
            heading("Kết hợp tham quan lân cận", "Combine with nearby sights"),
            paragraph("Băng qua đường là Nhà thờ Đức Bà; cách vài trăm mét có phố sách Nguyễn Văn Bình. Có thể kết hợp một vòng bộ tam: Nhà thờ – Bưu điện – phố sách, rồi nghỉ cà phê dưới hàng me.", "Across the street is Notre-Dame Cathedral, and a few hundred metres away lies Nguyen Van Binh Book Street. You can combine a walking trio – cathedral, post office, book street – then rest at a cafe under the tamarind trees."),
            quote("“Bước vào là chạm ngay hơi thở Sài Gòn xưa.”", "“Step inside and you instantly touch the breath of old Saigon.”"),
            divider(),
            mapBlock(new double[]{106.7019, 10.7793})
        ))
        .vibes(List.of("cultural", "peaceful"))
        .atmosphere(of("heritage-colonial", "heritage-colonial"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(85)
        .romanticScore(65)
        .localExperienceScore(70)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng thành phố", "City landmark")).slug("bieu-tuong-thanh-pho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Trong nhà", "Indoor")).slug("trong-nha").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 3) Chợ Bến Thành */
  private static PlaceDoc poiChoBenThanh() {
    String slug = "cho-ben-thanh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Chợ Bến Thành", "Ben Thanh Market"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Khu chợ biểu tượng với tháp đồng hồ, đặc sản – lưu niệm phong phú.", "An iconic market with a clock tower and a rich array of local specialties and souvenirs."))
        .description(of("Sầm uất từ sáng đến tối; có khu ẩm thực trong chợ và phố ẩm thực đêm xung quanh.", "Bustling from morning to night, with a food court inside and a night-food street around it."))
        .addressLine(of("Lê Lợi – Phan Bội Châu – Phan Chu Trinh, Quận 1, TP. HCM", "Le Loi – Phan Boi Chau – Phan Chu Trinh, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.6983, 10.7725})
        .images(withCover(
            img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Mặt tiền Chợ Bến Thành", "Facade of Ben Thanh Market", true, 0),
            img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Cho-Ben-Thanh-4.jpg", "Không khí tấp nập", "Bustling atmosphere", false, 1),
            img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/A1da8f00bea27dfc24b3.jpg", "Bên trong chợ", "Inside the market", false, 2),
            img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-12-1736393709.jpg", "Đêm Bến Thành", "Ben Thanh at night", false, 3),
            img("", "Tháp đồng hồ", "The clock tower", false, 4),
            img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/Ed1de73ada9819c64089.jpg", "Du khách tham quan", "Visitors browsing", false, 5)
        ))
        .content(List.of(
            heading("Bến Thành – chợ của người Sài Gòn", "Ben Thanh – the market of Saigon's people"),
            quote("""
                    Tiếng rao hong nắng sớm,
                    mùi quế quện tiêu gừng;
                    bước chợ quen nhịp thở,
                    tay gói chút phố phường.
                """, "Vendors' calls warm in the early sun,\nthe scent of cinnamon blends with pepper and ginger;\nstrolling the market in a familiar rhythm,\nhands wrap up a piece of the city."),
            paragraph("Chợ Bến Thành là biểu tượng nhộn nhịp bậc nhất của Sài Gòn: từ tháp đồng hồ mặt tiền đến mê cung gian hàng bên trong. Đi sớm buổi sáng để thoáng và hàng mới, trưa – chiều khách đông, tối quanh chợ bật đèn thành tuyến ẩm thực đêm sầm uất. Mang balo gọn và một ít tiền lẻ để mua nhanh – gọn.", "Ben Thanh Market is one of the most bustling icons of Saigon, from the clock tower out front to the maze of stalls inside. Go early in the morning for fresh goods and breathing room; midday to afternoon is crowded, and at night the surrounding streets light up into a lively night-food strip. Bring a compact backpack and some small change for quick, easy shopping."),
            heading("Khu hàng khô – đặc sản – quà mang về", "Dried goods – specialties – take-home gifts"),
            paragraph("Mực khô, cá khô, bò khô, trái cây sấy, cà phê, trà… thường có mẫu thử và nhận hút chân không. Trước khi mua, kiểm tra hạn dùng – trọng lượng tịnh – độ kín gói; so sánh 2–3 quầy về độ thơm – độ khô – độ dày lát. Với hàng có nước sốt, yêu cầu bọc kép để tránh rò rỉ trong vali.", "Dried squid, dried fish, beef jerky, dried fruit, coffee, tea and more often come with samples and vacuum-sealing. Before buying, check the expiry date, net weight and packaging seal; compare two or three stalls for aroma, dryness and slice thickness. For sauced items, ask for double wrapping to avoid leaks in your suitcase."),
            heading("Mẹo trả giá & ứng xử văn minh", "Bargaining tips & courteous behaviour"),
            paragraph("Giá có biên độ thương lượng nhẹ, nhất là khi mua nhiều. Hãy mỉm cười hỏi giá, xin “giá tốt nếu lấy 2–3 phần”, tránh mặc cả quá sâu. Khi không mua, cảm ơn và di chuyển sang quầy khác. Không sờ – mở sản phẩm khi chưa xin phép; xếp hàng gọn ở lối hẹp.", "Prices have some room to negotiate, especially when buying in quantity. Smile and ask the price, request 'a good price for 2–3 portions', and avoid haggling too hard. If you do not buy, say thanks and move to another stall. Do not touch or open products without asking; queue neatly in narrow aisles."),
            heading("Ẩm thực trong chợ & phố đêm quanh khu vực", "Food inside the market & the surrounding night street"),
            paragraph("Khu ẩm thực trong chợ có cơm tấm, bún mắm, bún bò, chè… Chọn quầy xoay vòng nhanh, dụng cụ sạch, gọi suất nhỏ để thử nhiều món. Buổi tối, dọc các tuyến Phan Bội Châu – Phan Chu Trinh có nhiều quầy nướng – hải sản – sinh tố; ngồi gọn trong vạch, không chắn lối đi.", "The market food court offers broken rice, bun mam, bun bo, sweet soups and more. Pick stalls with quick turnover and clean utensils, and order small portions to try many dishes. At night, along Phan Boi Chau and Phan Chu Trinh there are many grill, seafood and smoothie stalls; sit within the marked space and do not block the walkway."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-khu-an-uong-1693128182.jpg", "Quầy hàng trong chợ", "Stalls inside the market", false, 0)),
            heading("Góc chụp ảnh & khung giờ đẹp", "Photo spots & best times"),
            paragraph("Sáng sớm: chụp chính diện tháp đồng hồ khi nắng xiên; trưa: dùng hiên che để cân sáng, lấy chiều sâu dãy quầy; tối: bắt ánh đèn và bảng hiệu, đứng chéo 30–45° để thấy nhịp vòm cửa. Tránh đứng giữa dòng xe trước cổng, giữ an toàn khi băng qua đường.", "Early morning: shoot the clock tower head-on as the light slants in; midday: use the awnings to balance exposure and capture the depth of the stall rows; evening: catch the lights and signs, standing at a 30–45° angle to see the rhythm of the arched doors. Do not stand amid the traffic in front of the gate, and stay safe when crossing the street."),
            heading("Thanh toán & lưu ý an toàn", "Payment & safety notes"),
            paragraph("Nhiều quầy nhận tiền mặt và chuyển khoản; kiểm hóa đơn – hàng hóa trước khi rời quầy. Giữ ví – điện thoại ở ngăn trước, đeo balo phía trước lúc đông. Với hàng dễ mùi (mắm, khô), xin bọc kín và dán nhãn để thuận tiện vận chuyển.", "Many stalls accept both cash and bank transfers; check your receipt and goods before leaving. Keep your wallet and phone in front pockets and wear your backpack on your front when it is crowded. For strong-smelling items (fish sauce, dried goods), ask for airtight wrapping and labels for easier transport."),
            gallery(List.of(
                img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Mặt tiền tháp đồng hồ", "Clock-tower facade", false, 0),
                img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/7/10/1066504/A1da8f00bea27dfc24b3.jpg", "Bên trong chợ", "Inside the market", false, 1),
                img("https://mia.vn/media/uploads/blog-du-lich/cho-ben-thanh-12-1736393709.jpg", "Đêm Bến Thành", "Ben Thanh at night", false, 2)
            )),
            quote("“Muốn thấy nhịp sống Sài Gòn – vào chợ.”", "“To feel the pulse of Saigon, step into the market.”"),
            divider(),
            mapBlock(new double[]{106.6983, 10.7725})
        ))
        .vibes(List.of("lively", "diverse"))
        .atmosphere(of("urban-market", "urban-market"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("food", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(65)
        .romanticScore(30)
        .localExperienceScore(95)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Chợ & mua sắm", "Markets & shopping")).slug("cho-va-mua-sam").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Chợ địa phương", "Local market")).slug("cho-dia-phuong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Ẩm thực đường phố", "Street food")).slug("am-thuc-duong-pho").type(TagType.CUISINE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 4) Phố đi bộ Nguyễn Huệ */
  private static PlaceDoc poiPhoDiBoNguyenHue() {
    String slug = "pho-di-bo-nguyen-hue";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Trục quảng trường trung tâm, check-in hè phố, toà nhà cổ – hiện đại hoà quyện.", "A central plaza promenade for street photos, where old and modern buildings blend."))
        .description(of("Buổi tối nhộn nhịp nhạc nước, nghệ sĩ đường phố; hàng quán – café apartment dọc hai bên.", "Lively at night with musical fountains and street performers; shops and cafe apartments line both sides."))
        .addressLine(of("Nguyễn Huệ, Bến Nghé, Quận 1, TP. HCM", "Nguyen Hue, Ben Nghe, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.7045, 10.7733})
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street", true, 0),
            img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue3.jpg", "Quảng trường rộng", "Spacious plaza", false, 1),
            img("https://static.vinwonders.com/production/pho-di-bo-nguyen-hue-o-dau-banner-1.jpg", "Đường hoa dịp Tết", "Flower street during Tet", false, 2),
            img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue6.jpg", "UBND TP và quảng trường", "City Hall and the plaza", false, 3),
            img("https://images2.thanhnien.vn/thumb_w/640/528068263637045248/2023/12/31/h5-17040045604281668860224.jpg", "Nguyễn Huệ về đêm", "Nguyen Hue at night", false, 4),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MN/the%20cafe%20apartments/thumbs.jpg", "Café apartment", "Cafe apartment", false, 5)
        ))
        .content(List.of(
            heading("Nguyễn Huệ – nhịp sống trẻ của Sài Gòn", "Nguyen Hue – the youthful pulse of Saigon"),
            quote("""
                    Gió mang mùi phố mới,
                    đèn rót xuống vai người;
                    nhạc nước reo nhịp bước,
                    tiếng cười chạy ngang trời.
                """, "The wind carries the scent of a new street,\nlights pour down on people's shoulders;\nthe musical fountain keeps the rhythm,\nlaughter runs across the sky."),
            paragraph("Trục quảng trường trung tâm nối từ UBND TP đến bến Bạch Đằng, buổi tối rộn ràng nhất với nhạc nước, nghệ sĩ đường phố và dòng người tản bộ. Hai bên là dãy cao ốc, toà nhà cổ và các căn hộ café (The Café Apartments) cho view nhìn xuống quảng trường rất “đã”.", "The central plaza axis runs from City Hall to Bach Dang Wharf and is liveliest at night with musical fountains, street performers and crowds strolling. Both sides are lined with high-rises, old buildings and cafe apartments (The Cafe Apartments) offering a great view down over the plaza."),
            heading("Thời điểm & cách đi", "Timing & how to get there"),
            paragraph("Đẹp nhất: 18:30–21:30 (thứ Sáu–Chủ nhật). Gửi xe tại hầm phố đi bộ hoặc các TTTM lân cận rồi đi bộ; mang giày thoải mái, chai nước nhỏ. Ngày Tết, tuyến này thành “Đường hoa” – đông hơn bình thường, nên đi sớm hoặc trễ để dễ chụp hình.", "Best from 18:30–21:30 (Friday–Sunday). Park in the walking-street basement or nearby malls and walk; wear comfortable shoes and bring a small water bottle. During Tet this stretch becomes the 'Flower Street' and is busier than usual, so go early or late for easier photos."),
            imageBlock(img("https://images2.thanhnien.vn/thumb_w/640/528068263637045248/2023/12/31/h5-17040045604281668860224.jpg", "Nguyễn Huệ về đêm", "Nguyen Hue at night", false, 0)),
            heading("Điểm check-in gợi ý", "Suggested photo spots"),
            paragraph("Đầu phía UBND TP: khung chính diện trục quảng trường – kiến trúc Pháp cổ. Giữa trục: khu nhạc nước và ghế nghỉ. Cuối trục về phía sông: bến Bạch Đằng, gió mát – thuận ngắm skyline. The Café Apartments: chọn tầng trung để có góc nhìn nghiêng xuống dòng người.", "City Hall end: a head-on frame of the plaza axis with old French architecture. Mid-axis: the musical fountain and rest benches. River end: Bach Dang Wharf, cool breezes and good skyline views. The Cafe Apartments: pick a mid-level floor for an angled view down over the crowds."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & tips"),
            paragraph("Chính diện từ giữa trục để lấy chiều sâu; chụp chéo 30–45° để gom tiền cảnh – nền nhà cổ. Ảnh đêm: giảm tốc 1/5–1s (nếu có tripod mini) bắt vệt đèn xe; tránh dùng flash vào người biểu diễn. Khi chụp đông người, đứng gọn bên vạch, không chắn lối.", "Head-on from the centre of the axis for depth; shoot at a 30–45° angle to combine the foreground with the old-building backdrop. Night shots: slow the shutter to 1/5–1s (with a mini tripod) to catch light trails; avoid flashing performers. In crowds, stand neatly to the side and do not block the path."),
            heading("Ăn uống & dạo quanh", "Eating & wandering nearby"),
            paragraph("Hai bên là dãy quán nước, kem, trà chanh; trong các hẻm nhỏ có nhiều hàng ăn. Gần đó có phố sách Nguyễn Văn Bình (≈600m) và Nhà thờ Đức Bà – Bưu điện để kết hợp đi bộ một vòng.", "Both sides have rows of drink stalls, ice cream and lemon tea, while the small alleys hide many eateries. Nearby are Nguyen Van Binh Book Street (about 600m) and Notre-Dame Cathedral and the Post Office for a combined walking loop."),
            heading("Ứng xử & an toàn", "Etiquette & safety"),
            paragraph("Không xả rác, không trèo lên bồn hoa/đài phun; giữ ví – điện thoại gọn khi đông. Tôn trọng khoảng cách với người biểu diễn, chụp ảnh xin phép khi cần; gửi xe đúng nơi quy định.", "Do not litter or climb on the flower beds or fountains; keep your wallet and phone secure in crowds. Respect performers' space, ask before photographing when needed, and park only in designated areas."),
            gallery(List.of(
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Trục quảng trường trung tâm", "Central plaza axis", false, 0),
                img("https://ashui.com/wp-content/uploads/2014/10/duongNguyenHue3.jpg", "Không gian mở rộng rãi", "Spacious open space", false, 1),
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TA/MN/the%20cafe%20apartments/thumbs.jpg", "The Café Apartments", "The Cafe Apartments", false, 2)
            )),
            quote("“Cuối tuần – nhạc, đèn và năng lượng.”", "“Weekends – music, lights and energy.”"),
            divider(),
            mapBlock(new double[]{106.7045, 10.7733})
        ))
        .vibes(List.of("vibrant", "urban", "lively"))
        .atmosphere(of("urban-boulevard", "urban-boulevard"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.EVENING, BestVisitTime.NIGHT))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(85)
        .romanticScore(75)
        .localExperienceScore(80)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Phố đi bộ", "Walking street")).slug("pho-di-bo").build(),
            PlaceDoc.CategoryMini.builder().name(of("Giải trí", "Entertainment")).slug("giai-tri").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Đời sống về đêm", "Nightlife")).slug("doi-song-ve-dem").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 5) Dinh Độc Lập (Hội trường Thống Nhất) */
  private static PlaceDoc poiDinhDocLap() {
    String slug = "dinh-doc-lap";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Dinh Độc Lập (Hội trường Thống Nhất)", "Independence Palace (Reunification Hall)"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Công trình lịch sử – kiến trúc hiện đại, chứng nhân thời khắc 30/4/1975.", "A historic, modernist building and a witness to the moment of 30 April 1975."))
        .description(of("Tham quan phòng khánh tiết, hầm chỉ huy, sân đỗ trực thăng; khuôn viên cây xanh giữa trung tâm.", "Tour the reception halls, command bunker and helipad; a green campus in the city centre."))
        .addressLine(of("135 Nam Kỳ Khởi Nghĩa, Bến Thành, Quận 1, TP. HCM", "135 Nam Ky Khoi Nghia, Ben Thanh, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.6955, 10.7769})
        .images(withCover(
            img("https://hn.ss.bfcplatform.vn/tckt/2020/04/20A04027-01-768x512.jpg", "Mặt tiền Dinh Độc Lập", "Facade of Independence Palace", true, 0),
            img("https://photo.znews.vn/w860/Uploaded/lerl/2014_04_30/NHH_5511.jpg", "Sân thượng – bãi đỗ trực thăng", "Rooftop helipad", false, 1),
            img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-redz.jpg", "Phòng họp/hầm chỉ huy", "Meeting room / command bunker", false, 2),
            img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-OAbU.jpg", "Nội thất khánh tiết", "Ceremonial interior", false, 3),
            img("https://phuot3mien.com/wp-content/uploads/2021/04/dinh-doc-lap-24.jpg", "Khuôn viên xanh", "Green grounds", false, 4),
            img("https://vietnamtourism.vn//imguploads/tourist/2014/TPHoChiMinh/DinhDocLap/30Dthongnhat01.jpg", "Cổng Dinh", "The palace gate", false, 5)
        ))
        .content(List.of(
            heading("Dinh Độc Lập – dấu ấn lịch sử hiện đại", "Independence Palace – a modern historical landmark"),
            quote("""
                    Hành lang nghiêng bóng nắng,
                    trang sử mở trong tay;
                    bước qua hầm chỉ huy,
                    nghe thời khắc còn lay.
                """, "Corridors lean with sunlight,\na page of history opens in your hands;\nstepping through the command bunker,\nyou still feel that moment stir."),
            paragraph("Dinh Độc Lập là biểu tượng kết hợp giữa lịch sử và kiến trúc hiện đại nhiệt đới: mặt đứng thoáng với lam bê tông, sân vườn rộng và trục nghi lễ trang trọng. Đây cũng là nơi ghi dấu thời khắc 30/4/1975 – xe tăng tiến qua cổng, mở ra chương mới của đất nước.", "Independence Palace is a symbol that blends history with tropical modernist architecture: an airy facade of concrete sun-breakers, broad gardens and a formal ceremonial axis. It is also where the moment of 30 April 1975 is marked – tanks rolling through the gate, opening a new chapter for the country."),
            heading("Lộ trình tham quan gợi ý (60–90 phút)", "Suggested route (60–90 minutes)"),
            paragraph("Mua vé tại cổng → vào khối nhà chính thăm phòng khánh tiết, phòng họp nội các, phòng tiếp khách quốc lễ → lên sân thượng xem bãi đỗ trực thăng → xuống tầng hầm chỉ huy (phòng thông tin, bản đồ) → dạo khuôn viên cây xanh trước khi ra cổng. Có audio guide đa ngôn ngữ, nên dùng để theo mạch câu chuyện.", "Buy a ticket at the gate → enter the main building to see the reception hall, cabinet meeting room and state guest rooms → go up to the rooftop helipad → descend to the command bunker (communications room, maps) → stroll the green grounds before leaving. A multilingual audio guide is available and worth using to follow the storyline."),
            imageBlock(img("https://statics.vinpearl.com/dinh-doc-lap-8_1624805286.jpg", "Biểu tượng 30/4", "Symbol of 30 April", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý", "Architecture & notable details"),
            paragraph("Mặt tiền có hệ lam đan dày giúp chống nắng, thông gió tự nhiên; nội thất khánh tiết dùng gỗ, thảm, đèn chùm, bài trí theo nghi lễ. Ở hầm chỉ huy, quan sát máy điện đàm, bản đồ tác chiến, phòng trú ẩn – mỗi hiện vật đều đi kèm chú thích rõ ràng.", "The facade has a dense lattice of sun-breakers for shading and natural ventilation; the ceremonial interiors use wood, carpets and chandeliers arranged for state occasions. In the command bunker, observe the radios, battle maps and shelter rooms – each artefact comes with clear captions."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & tips"),
            paragraph("Chính diện từ thảm cỏ để lấy trọn mặt đứng và cờ; từ tầng cao, chụp chéo 30–45° cho chiều sâu các lam che nắng; sân thượng có trực thăng – giữ khoảng cách an toàn, không tựa lan can. Ánh sáng đẹp: 8:00–10:00 và 15:30–17:30.", "Head-on from the lawn to capture the whole facade and flag; from upper floors, shoot at a 30–45° angle for depth across the sun-breakers; the rooftop has a helicopter – keep a safe distance and do not lean on the railing. Best light: 8:00–10:00 and 15:30–17:30."),
            heading("Ứng xử & an toàn", "Etiquette & safety"),
            paragraph("Giữ trật tự trong phòng trưng bày, không chạm vào hiện vật; tắt flash ở khu vực có biển báo; đi theo lối chỉ dẫn một chiều tại hầm. Khuôn viên rộng – mang nước nhỏ, giày đế êm; giữ vé đến cuối lộ trình.", "Keep order in the exhibition rooms and do not touch the artefacts; turn off your flash where signs require; follow the one-way route in the bunker. The grounds are large – bring some water and comfortable shoes, and keep your ticket until the end of the tour."),
            gallery(List.of(
                img("https://hn.ss.bfcplatform.vn/tckt/2020/04/20A04027-01-768x512.jpg", "Mặt tiền dinh", "Palace facade", false, 0),
                img("https://phuot3mien.com/wp-content/uploads/2024/04/ghe-dinh-doc-lap-nhan-dip-30-4-minh-hieu-duoc-nhieu-ve-lich-su-kien-truc-dep-khoi-ban-OAbU.jpg", "Phòng khánh tiết", "Reception hall", false, 1),
                img("https://photo.znews.vn/w860/Uploaded/lerl/2014_04_30/NHH_5511.jpg", "Sân đỗ trực thăng", "Helipad", false, 2)
            )),
            quote("“Một chương lịch sử hiện diện trong từng căn phòng.”", "“A chapter of history present in every room.”"),
            divider(),
            mapBlock(new double[]{106.6955, 10.7769})
        ))
        .vibes(List.of("educational", "cultural"))
        .atmosphere(of("historic-memorial", "historic-memorial"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "explore"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(75)
        .romanticScore(35)
        .localExperienceScore(85)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Tham quan có hướng dẫn", "Guided tour")).slug("tham-quan-co-huong-dan").type(TagType.SERVICE).build(),
            PlaceDoc.TagMini.builder().name(of("Giáo dục lịch sử", "Historical education")).slug("giao-duc-lich-su").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 6) Bảo tàng Chứng tích Chiến tranh */
  private static PlaceDoc poiBaoTangChungTichChienTranh() {
    String slug = "bao-tang-chung-tich-chien-tranh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Bảo tàng nổi tiếng quốc tế về hậu quả chiến tranh và khát vọng hoà bình.", "An internationally renowned museum on the consequences of war and the longing for peace."))
        .description(of("Trưng bày ảnh tư liệu, hiện vật, xe – máy bay ngoài trời; nội dung nặng cảm xúc, nên cân nhắc khi đi cùng trẻ nhỏ.", "Displays of documentary photos, artefacts and outdoor vehicles and aircraft; the content is emotionally heavy, so consider this when bringing young children."))
        .addressLine(of("28 Võ Văn Tần, Phường 6, Quận 3, TP. HCM", "28 Vo Van Tan, Ward 6, District 3, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.6950, 10.7798})
        .images(withCover(
            img("https://cdn.xanhsm.com/2024/11/291a4f41-bao-tang-chung-tich-chien-tranh-1.jpg", "Mặt tiền bảo tàng", "Museum facade", true, 0),
            img("https://hoabinhphuquoc.com.vn/files/files/Tin%20tuc/bang-tang-chung-tich-chien-tranh-3.jpg", "Khu trưng bày ngoài trời", "Outdoor exhibition area", false, 1),
            img("https://cdn.xanhsm.com/2024/11/4b49f4cf-bao-tang-chung-tich-chien-tranh-7.jpg", "Không gian triển lãm", "Exhibition space", false, 2),
            img("https://cdn.xanhsm.com/2024/11/4c12aacc-bao-tang-chung-tich-chien-tranh-6.jpg", "Hiện vật chiến tranh", "War artefacts", false, 3),
            img("https://resource.kinhtedothi.vn/resources2025/1/users/63/z6550087015601-28ffa7e693cd7254a26003f3c63c1a63-1745827081.jpg", "Sân trước bảo tàng", "Forecourt of the museum", false, 4),
            img("https://www.vietfuntravel.com.vn/image/data/Blog/cam-nang/tham-quan-bao-tang-chung-tich-chien-tranh/tham-quan-bao-tang-chung-tich-chien-tranh-2.jpg", "Du khách tham quan", "Visitors touring", false, 5)
        ))
        .content(List.of(
            heading("Chứng tích Chiến tranh – để nhớ và để hiểu", "War Remnants – to remember and to understand"),
            quote("""
                    Tường ảnh rưng màu khói,
                    chữ cũ kể đêm dài;
                    bước chậm qua từng khung,
                    nhớ để quý ngày mai.
                """, "Walls of photos shimmer the colour of smoke,\nold words tell of long nights;\nstepping slowly past each frame,\nwe remember so as to cherish tomorrow."),
            paragraph("Bảo tàng Chứng tích Chiến tranh là điểm đến giàu giá trị giáo dục về hậu quả chiến tranh và khát vọng hoà bình. Không gian trưng bày theo mạch thời gian – chủ đề, từ ảnh tư liệu, hiện vật cá nhân đến khu triển lãm ngoài trời với xe – máy bay. Nội dung nặng cảm xúc, đặc biệt ở các phòng về nạn nhân chiến tranh, nên chuẩn bị tâm lý và cân nhắc khi đi cùng trẻ nhỏ.", "The War Remnants Museum is a destination of great educational value about the consequences of war and the longing for peace. The displays follow a chronological and thematic flow, from documentary photos and personal artefacts to an outdoor area with vehicles and aircraft. The content is emotionally heavy, especially in the rooms about war victims, so prepare yourself and think carefully before bringing young children."),
            heading("Cách tham quan gợi ý (60–120 phút)", "Suggested visit (60–120 minutes)"),
            paragraph("Đi tuyến từ tổng quan → phòng ảnh phóng sự quốc tế → phòng chuyên đề (chất độc hoá học, bom mìn, tù binh…) → khu hiện vật ngoài trời (xe tăng, máy bay, trực thăng) → không gian tưởng niệm. Ở mỗi phòng, dành 5–10 phút đọc bảng tóm tắt bối cảnh trước khi xem ảnh/hiện vật để tránh bị ngợp thông tin.", "Follow the route from the overview → the international photojournalism room → thematic rooms (chemical agents, mines and bombs, prisoners of war and so on) → the outdoor artefacts (tanks, aircraft, helicopters) → the memorial space. In each room, spend 5–10 minutes reading the context summary before viewing the photos and artefacts to avoid being overwhelmed."),
            heading("Những điểm nên dừng lâu", "Spots worth lingering at"),
            paragraph("Các bộ ảnh phóng sự đoạt giải quốc tế; thư – nhật ký – đồ dùng cá nhân của nạn nhân và phóng viên chiến trường; bản đồ – số liệu tổng hợp giúp đặt sự kiện vào bối cảnh. Hãy đối chiếu ngày tháng, địa danh và nguồn tư liệu được ghi rõ dưới mỗi ảnh để hiểu chuẩn xác.", "The internationally award-winning photojournalism series; letters, diaries and personal belongings of victims and war correspondents; maps and aggregated figures that place events in context. Cross-check the dates, place names and sources noted under each photo to understand them accurately."),
            heading("Ứng xử trong không gian trưng bày", "Etiquette in the exhibition space"),
            paragraph("Giữ thái độ trang nghiêm; tắt flash, không chạm vào hiện vật, không nói lớn – đùa cợt, nhất là tại khu tưởng niệm. Một số khu vực hạn chế quay/chụp, vui lòng tôn trọng biển báo. Nếu đi theo nhóm, đứng gọn một bên khi thảo luận để không chắn lối người khác.", "Keep a solemn attitude; turn off your flash, do not touch the artefacts, and do not speak loudly or joke, especially in the memorial area. Some areas restrict filming and photography – please respect the signs. If you are in a group, stand neatly to one side when discussing so as not to block others."),
            imageBlock(img("https://resource.kinhtedothi.vn/resources2025/1/users/63/z6550122078373-21c550297942586975206d2ede2ecb53-1745827587.jpg", "Xe tăng ngoài trời", "Outdoor tank", false, 0)),
            heading("Khu trưng bày ngoài trời", "Outdoor exhibition area"),
            paragraph("Sân ngoài trời tập trung xe tăng, máy bay, trực thăng, bom – đạn đã tháo ngòi. Đọc bảng thông tin đi kèm để biết xuất xứ – niên đại – vai trò chiến thuật. Giữ khoảng cách an toàn, không trèo lên hiện vật; trẻ nhỏ cần có người lớn kèm sát.", "The outdoor yard gathers tanks, aircraft, helicopters and defused bombs and shells. Read the accompanying labels to learn their origin, era and tactical role. Keep a safe distance and do not climb on the artefacts; young children need close adult supervision."),
            heading("Gợi ý cho gia đình & nhóm học tập", "Tips for families & study groups"),
            paragraph("Với trẻ em, chọn vài phòng nhẹ hơn trước, giải thích khái niệm hoà bình – nhân ái và chỉ xem nhanh các khu ảnh nhạy cảm. Nhóm sinh viên có thể phân công: người ghi chú mốc thời gian, người chụp lại (khu cho phép) bảng nội dung, người tổng hợp câu hỏi để thảo luận sau buổi tham quan.", "For children, start with a few gentler rooms, explain the ideas of peace and compassion, and pass quickly through the sensitive photo areas. Student groups can divide tasks: one notes the timeline, one photographs (where allowed) the information boards, and one compiles questions to discuss after the visit."),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo spots & tips"),
            paragraph("Trong nhà: đứng chéo 30–45° so với nguồn sáng để giảm phản chiếu lên kính; tuyệt đối tắt flash. Ngoài trời: chọn buổi sáng/chiều muộn để ánh sáng mềm, chụp cận chi tiết số hiệu – phù hiệu trên thân xe/máy bay để làm chú thích ảnh.", "Indoors: stand at a 30–45° angle to the light source to reduce glare on glass; never use a flash. Outdoors: choose morning or late afternoon for soft light, and shoot close-ups of the serial numbers and insignia on the vehicles and aircraft for photo captions."),
            heading("Khả năng tiếp cận & lưu ý an toàn", "Accessibility & safety notes"),
            paragraph("Lối đi bằng phẳng, có thang/cầu thang ở vài khu; mang giày đế êm. Một số phòng điều hoà mát sâu – nên mang áo khoác mỏng. Giữ tư trang gọn gàng ở khu đông; tôn trọng hàng lối khi cao điểm khách tham quan.", "The paths are flat, with stairs in a few areas; wear comfortable shoes. Some rooms are heavily air-conditioned – bring a light jacket. Keep your belongings tidy in crowded areas and respect the queue at peak times."),
            gallery(List.of(
                img("https://cdn.xanhsm.com/2024/11/291a4f41-bao-tang-chung-tich-chien-tranh-1.jpg", "Mặt tiền bảo tàng", "Museum facade", false, 0),
                img("https://hoabinhphuquoc.com.vn/files/files/Tin%20tuc/bang-tang-chung-tich-chien-tranh-3.jpg", "Khu trưng bày ngoài trời", "Outdoor exhibition area", false, 1),
                img("https://cdn.xanhsm.com/2024/11/4b49f4cf-bao-tang-chung-tich-chien-tranh-7.jpg", "Không gian triển lãm", "Exhibition space", false, 2)
            )),
            quote("“Xem để hiểu, để trân trọng hoà bình.”", "“See it to understand, and to cherish peace.”"),
            divider(),
            mapBlock(new double[]{106.6950, 10.7798})
        ))
        .vibes(List.of("educational", "cultural"))
        .atmosphere(of("museum-indoor", "museum-indoor"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "group"))
        .visitPurpose(List.of("culture"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(false)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(65)
        .romanticScore(20)
        .localExperienceScore(90)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Giáo dục lịch sử", "Historical education")).slug("giao-duc-lich-su").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Trưng bày ảnh tư liệu", "Documentary photo display")).slug("trung-bay-anh-tu-lieu").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 7) Nhà hát TP. HCM (Opera House / Municipal Theatre) */
  private static PlaceDoc poiNhaHatThanhPho() {
    String slug = "nha-hat-thanh-pho-ho-chi-minh";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Nhà hát TP. Hồ Chí Minh (Municipal Theatre)", "Ho Chi Minh City Opera House (Municipal Theatre)"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Kiến trúc tân cổ điển Pháp, không gian biểu diễn nghệ thuật giữa trung tâm.", "French neoclassical architecture and a performing-arts venue in the city centre."))
        .description(of("Check-in bậc thềm trước giờ diễn; xung quanh là khách sạn – TTTM sang trọng và phố Nguyễn Huệ kế cận.", "Take photos on the steps before showtime; surrounded by luxury hotels and malls, with Nguyen Hue Street nearby."))
        .addressLine(of("07 Công Trường Lam Sơn, Bến Nghé, Quận 1, TP. HCM", "07 Lam Son Square, Ben Nghe, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.7040, 10.7763})
        .images(withCover(
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-1.jpg", "Mặt tiền Nhà hát Thành phố", "Facade of the Opera House", true, 0),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-2.jpg", "Nội thất khán phòng", "Auditorium interior", false, 1),
            img("https://dulich3mien.vn/wp-content/uploads/2021/12/hinh-anh-nha-hat-thanh-pho-1-1.jpg", "Nhà hát về đêm", "The opera house at night", false, 2),
            img("https://artlive.vn/wp-content/uploads/2024/01/nhtp-2048x1361.jpg", "Chi tiết phù điêu", "Relief detail", false, 3),
            img("https://ghiensaigon.com/wp-content/uploads/2025/05/cong-truong-lam-son-750x375.jpg", "Quảng trường Lam Sơn", "Lam Son Square", false, 4),
            img("https://static.vinwonders.com/production/nha-hat-thanh-pho-banner-min.jpg", "Góc chụp toàn cảnh", "Panoramic view", false, 5)
        ))
        .content(List.of(
            heading("Nhà hát Thành phố – nét duyên cổ điển", "The Opera House – classical charm"),
            quote("""
                    Vòm cong ôm nhịp phố,
                    phù điêu chạm mùa xưa;
                    đèn lên như tiếng nhạc,
                    sân khấu mở trang thơ.
                """, "Curved arches embrace the street's rhythm,\nreliefs carve the seasons of old;\nthe lights come on like music,\nthe stage opens a page of poetry."),
            paragraph("Công trình tân cổ điển Pháp giữa trung tâm Quận 1, mặt đứng duyên dáng với hàng cột, vòm cong, phù điêu và bậc thềm rộng – nơi người Sài Gòn hay ngồi chuyện trò trước giờ diễn. Buổi tối đèn vàng bật lên làm nổi mảng phù điêu và chi tiết trang trí, tạo cảm giác như một góc Paris giữa đô thị hiện đại.", "A French neoclassical building in the heart of District 1, with a graceful facade of columns, curved arches, reliefs and broad steps – where Saigon's people often sit and chat before showtime. At night the golden lights come on and highlight the reliefs and decorative details, giving the feeling of a corner of Paris amid the modern city."),
            heading("Thời điểm & cách trải nghiệm", "Timing & how to experience it"),
            paragraph("Nếu có dịp, thử một đêm xem diễn (AO Show, nhạc giao hưởng, múa đương đại…) để cảm nhận âm thanh trong khán phòng. Không xem show thì ghé lúc hoàng hôn hoặc sau 19:00 để chụp ánh đèn. Khu vực xung quanh nhiều TTTM/khách sạn, gửi xe hầm gần đó rồi đi bộ ra quảng trường Lam Sơn.", "If you have the chance, try an evening performance (the AO Show, a symphony concert, contemporary dance and so on) to feel the acoustics in the hall. If not seeing a show, visit at sunset or after 19:00 to photograph the lights. The surrounding area has many malls and hotels; park in a nearby basement and walk out to Lam Son Square."),
            imageBlock(img("https://statics.vinpearl.com/nha-hat-thanh-pho-9_1629694049.jpg", "Ánh đèn sân khấu", "Stage lights", false, 0)),
            heading("Kiến trúc & chi tiết đáng chú ý", "Architecture & notable details"),
            paragraph("Mặt tiền đối xứng, bậc thềm cao, vòm trung tâm và hai khối cánh tạo nhịp điệu; phù điêu trang trí theo mô-típ cổ điển; cửa sổ bán nguyệt, lan can sắt uốn; bên trong là khán phòng hình móng ngựa, trần cao, âm học tốt. Đứng gần để quan sát hoa văn, phào chỉ và các mảng tu bổ theo thời gian.", "A symmetrical facade with high steps, a central arch and two wings create rhythm; the reliefs follow classical motifs; semicircular windows and wrought-iron railings; inside is a horseshoe-shaped auditorium with a high ceiling and good acoustics. Stand close to observe the patterns, mouldings and restoration patches over time."),
            heading("Check-in bậc thềm & mẹo chụp ảnh", "Photos on the steps & shooting tips"),
            paragraph("Chính diện: lùi về giữa quảng trường Lam Sơn để lấy trọn mặt đứng và bậc thềm. Góc chéo 30–45°: nhấn khối vòm và chiều sâu các cánh nhà. Ảnh đêm: giảm tốc 1/5–1s (nếu có tripod mini) để bắt vệt đèn xe; tránh dùng flash thẳng vào người khác. Khi đông, đứng gọn sát mép bồn cây, không chắn lối lên xuống bậc thềm.", "Head-on: step back to the centre of Lam Son Square to capture the whole facade and steps. At a 30–45° angle: emphasise the arched mass and the depth of the wings. Night shots: slow the shutter to 1/5–1s (with a mini tripod) to catch light trails; avoid flashing other people. When crowded, stand neatly along the edge of the planters and do not block the steps."),
            heading("Kết hợp dạo quanh", "Combine with a stroll"),
            paragraph("Từ Nhà hát, đi bộ vài phút ra phố đi bộ Nguyễn Huệ (ngắm nhạc nước – skyline), hoặc ghé Vincom Đồng Khởi, khách sạn lịch sử, các quán cà phê view phố. Có thể xếp lịch: chiều tối dạo Nguyễn Huệ → chụp Nhà hát lên đèn → xem show → ăn nhẹ khu Đồng Khởi.", "From the opera house, walk a few minutes to Nguyen Hue Walking Street (for the musical fountain and skyline), or drop by Vincom Dong Khoi, the historic hotels and street-view cafes. You can plan: stroll Nguyen Hue in the evening → photograph the lit-up opera house → see a show → a light meal in the Dong Khoi area."),
            heading("Ứng xử & lưu ý", "Etiquette & notes"),
            paragraph("Trang phục lịch sự nếu vào xem diễn; giữ trật tự, tắt chuông điện thoại trong khán phòng. Ngoài trời, không leo, ngồi trên lan can; không xả rác ở quảng trường. Khu vực đông khách – giữ tư trang khi chụp ảnh.", "Dress smartly if attending a performance; keep quiet and silence your phone in the auditorium. Outside, do not climb or sit on the railings, and do not litter in the square. The area is busy – keep an eye on your belongings while taking photos."),
            gallery(List.of(
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-1.jpg", "Mặt tiền tân cổ điển", "Neoclassical facade", false, 0),
                img("https://www.vietfuntravel.com.vn/image/data/sai-gon/tham-quan-nha-hat-tp-hcm-2.jpg", "Nội thất khán phòng", "Auditorium interior", false, 1),
                img("https://dulich3mien.vn/wp-content/uploads/2021/12/hinh-anh-nha-hat-thanh-pho-1-1.jpg", "Nhà hát về đêm", "The opera house at night", false, 2)
            )),
            quote("“Một góc Paris giữa trung tâm Sài Gòn.”", "“A corner of Paris in the heart of Saigon.”"),
            divider(),
            mapBlock(new double[]{106.7040, 10.7763})
        ))
        .vibes(List.of("cultural", "romantic"))
        .atmosphere(of("heritage-colonial", "heritage-colonial"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography", "relax"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(90)
        .romanticScore(85)
        .localExperienceScore(65)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Văn hoá", "Culture")).slug("van-hoa").build(),
            PlaceDoc.CategoryMini.builder().name(of("Giải trí", "Entertainment")).slug("giai-tri").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Biểu tượng thành phố", "City landmark")).slug("bieu-tuong-thanh-pho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Nghệ thuật biểu diễn", "Performing arts")).slug("nghe-thuat-bieu-dien").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 8) Bitexco Financial Tower – Saigon Skydeck */
  private static PlaceDoc poiBitexcoSkydeck() {
    String slug = "bitexco-saigon-skydeck";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bitexco Financial Tower – Saigon Skydeck", "Bitexco Financial Tower – Saigon Skydeck"))
        .slug(slug)
        .parentSlug("ho-chi-minh-city")
        .ancestors(List.of("ho-chi-minh-city"))
        .childrenCount(0)
        .shortDescription(of("Toà tháp biểu tượng với đài quan sát Skydeck ngắm toàn cảnh Sài Gòn.", "An iconic tower with the Skydeck observation deck for panoramic views of Saigon."))
        .description(of("Lên đài quan sát, ngắm hoàng hôn và đêm thành phố; trung tâm thương mại – café lounge trong toà nhà.", "Go up to the observation deck for sunset and night views; the building has a mall and cafe lounge."))
        .addressLine(of("02 Hải Triều, Bến Nghé, Quận 1, TP. HCM", "02 Hai Trieu, Ben Nghe, District 1, Ho Chi Minh City"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.7058, 10.7719})
        .images(withCover(
            img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2013_05_02/120512kpbitexco04_de1c3.jpg", "Bitexco nhìn từ bờ sông", "Bitexco seen from the riverbank", true, 0),
            img("https://travelwithminh.com/wp-content/uploads/2025/06/sky-dechk-89-melbourne-australia-87-950x500.jpg", "View từ Skydeck", "View from the Skydeck", false, 1),
            img("https://cdn.xanhsm.com/2024/12/4e7794d1-toa-nha-tai-chinh-bitexco-5.jpg", "Sàn trực thăng biểu tượng", "Iconic helipad", false, 2),
            img("https://static.tuoitre.vn/tto/i/s626/2015/04/25/41B6gnc8.jpg", "Tháp về đêm", "The tower at night", false, 3),
            img("https://images.vietnamtourism.gov.vn/en///images/2015/thap-Bitexco.jpg", "Toàn cảnh tòa nhà Bitexco", "Full view of the Bitexco tower", false, 4),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitexco_Financial_Tower_20022012_cropped.JPG/500px-Bitexco_Financial_Tower_20022012_cropped.JPG", "Tháp tài chính Bitexco", "Bitexco Financial Tower", false, 5)
        ))
        .content(List.of(
            heading("Bitexco – góc nhìn 360° Sài Gòn", "Bitexco – a 360° view of Saigon"),
            quote("""
                    Gió quét qua tầng mây,
                    phố thắp nghìn ô sáng;
                    sông uốn mình dưới tháp,
                    hoàng hôn rót miên man.
                """, "The wind sweeps through the clouds,\nthe city lights up a thousand windows;\nthe river curves beneath the tower,\nthe sunset pours on endlessly."),
            paragraph("Bitexco Financial Tower là một trong những biểu tượng đường chân trời của trung tâm Sài Gòn, nổi bật với sàn trực thăng vươn ra như một cánh hoa. Lên Saigon Skydeck, bạn có thể quét trọn 360°: sông Sài Gòn uốn lượn, những trục đại lộ rực sáng và mảng xanh xen giữa các khối nhà.", "Bitexco Financial Tower is one of the skyline icons of central Saigon, distinguished by a helipad that juts out like a petal. Up at the Saigon Skydeck, you can sweep a full 360°: the winding Saigon River, the brightly lit boulevards and patches of green between the buildings."),
            heading("Khung giờ & trải nghiệm lý tưởng", "Ideal timing & experience"),
            paragraph("Khuyến nghị 16:30–18:30: lên khi trời còn xanh để chụp cảnh trong vắt, ở lại đón hoàng hôn và khoảnh khắc ‘thành phố lên đèn’. Vào các ngày nắng trong, tầm nhìn xa giúp thấy rõ cả bến Bạch Đằng, phố đi bộ Nguyễn Huệ và các toà tháp lân cận.", "Recommended 16:30–18:30: go up while the sky is still blue for crystal-clear shots, then stay for the sunset and the moment 'the city lights up'. On clear, sunny days the long-range view reveals Bach Dang Wharf, Nguyen Hue Walking Street and the neighbouring towers."),
            heading("Mẹo ngắm & chụp từ Skydeck", "Tips for viewing & shooting from the Skydeck"),
            paragraph("Đi vòng theo kim đồng hồ để không bỏ lỡ các hướng chính: Đông (sông & cảng), Nam (khu Thủ Thiêm – bán đảo), Tây (Quận 1 – Bến Thành), Bắc (các trục hiên đại). Đặt ống kính/điện thoại áp gần kính để giảm phản xạ; nếu cần, dùng mũ/áo tối màu che bớt ánh đèn sau lưng.", "Walk clockwise so you do not miss the main directions: East (the river and port), South (the Thu Thiem peninsula), West (District 1 and Ben Thanh), North (the modern axes). Press your lens or phone close to the glass to reduce reflections; if needed, use a dark hat or jacket to block the lights behind you."),
            imageBlock(img("https://congvientaodan.com/wp-content/uploads/2024/10/bitexco-financial-tower-skydeck-4.jpg", "Ngắm toàn cảnh từ Skydeck", "Panoramic view from the Skydeck", false, 0)),
            heading("Café – lounge & tiện ích trong toà nhà", "Cafe, lounge & amenities in the building"),
            paragraph("Sau khi ngắm cảnh, bạn có thể ghé café/lounge trong toà tháp để nghỉ chân, thưởng thức đồ uống nhìn xuống dòng xe. Khu mua sắm tầng thấp có quầy lưu niệm, thuận tiện mua một chiếc postcard/nam châm ‘skyline’ làm kỷ niệm.", "After taking in the views, you can stop by the cafe or lounge in the tower to rest and enjoy a drink overlooking the traffic. The lower-floor mall has souvenir counters, handy for buying a 'skyline' postcard or magnet as a keepsake."),
            heading("Góc chụp Bitexco từ mặt đất", "Shooting Bitexco from the ground"),
            paragraph("Muốn chụp ngoại thất tháp: đứng bên bờ sông đoạn bến Bạch Đằng để lấy trọn khối tháp và mặt nước; hoặc ở giao Hải Triều – Hồ Tùng Mậu để nhấn khối ‘cánh hoa’ (sàn trực thăng) vươn ra. Ảnh đêm đẹp nhất khi trời còn ‘giờ xanh’, đèn tháp bắt đầu bật.", "To photograph the tower's exterior: stand by the river along Bach Dang Wharf to capture the whole tower and the water; or at the Hai Trieu – Ho Tung Mau junction to emphasise the 'petal' (helipad) jutting out. Night shots are best during the 'blue hour' as the tower lights begin to come on."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Tuân thủ nội quy Skydeck: không tựa người quá sát kính, không dùng chân máy lớn gây vướng lối (nếu cần, dùng tripod mini); giữ vé đến khi ra. Thang máy lên tầng cao có thể thay đổi theo khung giờ – theo biển chỉ dẫn và hướng dẫn của nhân viên.", "Follow the Skydeck rules: do not lean too close to the glass and do not use large tripods that block the way (use a mini tripod if needed); keep your ticket until you leave. The high-floor elevators may change by time slot – follow the signs and staff instructions."),
            gallery(List.of(
                img("https://image.vovworld.vn/w730/uploaded/vovworld/ujwyqdxwp/2013_05_02/120512kpbitexco04_de1c3.jpg", "Bitexco bên sông Sài Gòn", "Bitexco by the Saigon River", false, 0),
                img("https://static.tuoitre.vn/tto/i/s626/2015/04/25/41B6gnc8.jpg", "Tháp về đêm", "The tower at night", false, 1),
                img("https://images.vietnamtourism.gov.vn/en///images/2015/thap-Bitexco.jpg", "Toàn cảnh toà tháp", "Full view of the tower", false, 2)
            )),
            quote("“360° Sài Gòn – đẹp nhất lúc hoàng hôn.”", "“A 360° Saigon – most beautiful at sunset.”"),
            divider(),
            mapBlock(new double[]{106.7058, 10.7719})
        ))
        .vibes(List.of("vibrant", "urban"))
        .atmosphere(of("urban-skyscraper", "urban-skyscraper"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("photography", "relax"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(95)
        .romanticScore(80)
        .localExperienceScore(55)
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Đài quan sát", "Observation deck")).slug("dai-quan-sat").build(),
            PlaceDoc.CategoryMini.builder().name(of("Kiến trúc", "Architecture")).slug("kien-truc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Tầm nhìn toàn cảnh", "Panoramic view")).slug("tam-nhin-toan-canh").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Trong nhà", "Indoor")).slug("trong-nha").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.MODERATE)
        .build();
  }
}