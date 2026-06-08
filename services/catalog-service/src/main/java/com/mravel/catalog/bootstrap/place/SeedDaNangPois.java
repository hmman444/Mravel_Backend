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
public class SeedDaNangPois {

  @Bean
  CommandLineRunner seedDaNangPoisRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedDaNangPois] start");

      // 8 POI thuộc Đà Nẵng
      List<PlaceDoc> pois = List.of(
          poiMyKhe(),
          poiBaNaHills(),
          poiNguHanhSon(),
          poiCauRong(),
          poiSonTra(),
          poiChoHan(),
          poiAsiaPark(),
          poiBaoTangCham()
      );

      int inserted = 0;
      for (PlaceDoc p : pois) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedDaNangPois] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedDaNangPois] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedDaNangPois] done, inserted = " + inserted);
    };
  }

  // ===================== POI FOR DA NANG =====================

  /** 1) Bãi biển Mỹ Khê */
  private static PlaceDoc poiMyKhe() {
    String slug = "bai-bien-my-khe";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bãi biển Mỹ Khê", "My Khe Beach"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Bãi biển đẹp hàng đầu châu Á, nước trong xanh, cát mịn.", "One of Asia's finest beaches with clear blue water and soft sand."))
        .description(of("Mỹ Khê nổi tiếng với dải bờ biển dài, hoạt động tắm biển, lướt ván, dù lượn. "
            + "Sáng sớm và chiều muộn là thời khắc đẹp nhất để dạo biển và chụp ảnh.",
            "My Khe is famous for its long shoreline, swimming, surfing and paragliding. "
            + "Early morning and late afternoon are the best times for a beach stroll and photos."))
        .addressLine(of("Võ Nguyên Giáp, Sơn Trà, Đà Nẵng, Việt Nam", "Vo Nguyen Giap, Son Tra, Da Nang, Vietnam"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2479, 16.0603}) // [lon, lat]
        .vibes(List.of("relaxing", "nature"))
        .atmosphere(of("coastal-open", "coastal-open"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(85)
        .romanticScore(70)
        .localExperienceScore(60)
        .images(withCover(
            img("https://alanidananghotel.com/uploads/images/bai-bien-da-nang-dep-nhat-hanh-tinh-1%20(1).jpg", "Biển Mỹ Khê", "My Khe Beach", true, 0),
            img("https://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy3/image00103072017024441.jpg", "Cát trắng mịn", "Soft white sand", false, 1),
            img("https://r2.nucuoimekong.com/wp-content/uploads/bai-bien-my-khe.jpg", "Tắm biển Mỹ Khê", "Swimming at My Khe", false, 2),
            img("https://viettourist.vn/wp-content/uploads/2017/04/3-diem-den-da-nang-304-157.jpg", "Toàn cảnh bãi biển", "Beach panorama", false, 3),
            img("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_863/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/c19hhp0awkm0cmzztj2k/L%E1%BB%9Bph%E1%BB%8Dcl%C6%B0%E1%BB%9Bts%C3%B3ngc%E1%BB%A7aSurfShack%E1%BB%9F%C4%90%C3%A0N%E1%BA%B5ng.jpg", "Lướt ván", "Surfing", false, 4),
            img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/the-thao-bien-my-khe.png", "Hoạt động biển", "Beach activities", false, 5)
        ))
        .content(List.of(
            heading("Mỹ Khê – bãi biển biểu tượng của Đà Nẵng", "My Khe - Da Nang's iconic beach"),
            quote("""
                Sớm mai sóng vỗ nhẹ,
                nắng rót mật chân trời;
                cát mịn in dấu bước, biển xanh mở khoảng trời.
                """,
                """
                Gentle waves at dawn,
                honeyed sunlight on the horizon;
                soft sand keeps each footprint, blue sea opens the sky.
                """),
            paragraph("Từ trung tâm thành phố chỉ vài phút di chuyển, Mỹ Khê phù hợp cho tắm biển, chạy bộ, đạp xe dọc bờ và ngắm bình minh – hoàng hôn; dọc tuyến Võ Nguyên Giáp là chuỗi quán hải sản và quán cà phê nhìn thẳng ra biển, thuận tiện kết hợp thư giãn sau khi tắm.",
                "Just minutes from the city center, My Khe is ideal for swimming, jogging and cycling along the shore and watching sunrise and sunset; Vo Nguyen Giap street is lined with seafood restaurants and cafes facing the sea, perfect for relaxing after a swim."),
            heading("Thời điểm đẹp & thủy triều", "Best time & tides"),
            paragraph("Khung giờ lý tưởng thường là 05:30–08:30 khi nắng còn dịu, nước trong và ít gió; buổi chiều 16:30–18:00 thích hợp dạo biển, chụp ảnh hoàng hôn; những ngày gió to, sóng lớn hoặc có cảnh báo dòng chảy xa bờ (rip current) nên hạn chế xuống nước và ưu tiên các hoạt động trên bãi.",
                "The ideal window is usually 05:30-08:30 when the sun is mild, the water clear and the wind light; the afternoon 16:30-18:00 suits beach walks and sunset photos; on windy days with big waves or rip-current warnings, limit time in the water and favor activities on the beach."),
            heading("Hoạt động biển", "Beach activities"),
            paragraph("Bạn có thể bơi gần bờ, chơi SUP/kayak, lướt ván (theo mùa gió), dù lượn hoặc đơn giản là đi chân trần trên cát; nếu thuê dịch vụ, hãy kiểm tra áo phao, dây an toàn và hướng dẫn viên; nhớ khởi động kỹ trước khi xuống nước để hạn chế chuột rút.",
                "You can swim near the shore, try SUP/kayak, surf (seasonal winds), paraglide or simply walk barefoot on the sand; if renting services, check life jackets, safety lines and guides; remember to warm up well before entering the water to avoid cramps."),
            heading("Dịch vụ & tiện ích", "Services & amenities"),
            paragraph("Các lối xuống biển có bãi gửi xe, vòi tắm tráng, phòng thay đồ; khu vực chính thường có lực lượng cứu hộ trực ca theo giờ; dọc bãi là hàng quán phục vụ nước mát, dừa tươi, hải sản; hỏi giá rõ ràng trước khi sử dụng dịch vụ để trải nghiệm thoải mái.",
                "The beach entrances have parking, rinse showers and changing rooms; the main area usually has lifeguards on shift; stalls along the beach serve cold drinks, fresh coconut and seafood; ask prices clearly before using any service for a comfortable experience."),
            imageBlock(img("https://static.vinwonders.com/2022/04/bai-bien-my-khe-da-nang-2.jpg", "Bãi biển Mỹ Khê buổi sớm", "My Khe Beach in the early morning", false, 0)),
            gallery(List.of(
                img("https://danangfantasticity.com/wp-content/uploads/2024/03/chiem-nguong-bien-my-khe-1-trong-10-bai-bien-dep-nhat-chau-a-01.jpg", "Sóng nhẹ", "Gentle waves", false, 0),
                img("https://r2.nucuoimekong.com/wp-content/uploads/bien-my-khe-o-dau.jpg", "Check-in bãi biển", "Beach check-in", false, 0),
                img("https://cuongdulich.com/assets/posts/1574066363-du-lich-bien-My-Khe-17.jpg", "Bờ biển dài", "Long shoreline", false, 0)
            )),
            heading("Lịch trình gợi ý 1 buổi", "Suggested half-day itinerary"),
            paragraph("Bình minh: ra bãi lúc 05:30, khởi động – tắm biển 30–45 phút, dạo dọc bờ chụp ảnh, lên tắm tráng rồi ăn sáng hải sản/miến cá gần bãi; hoàng hôn: tắm nhẹ hoặc đi dạo, chụp nắng cuối ngày, sau đó ngồi cà phê sát biển ngắm phố lên đèn.",
                "Sunrise: reach the beach at 05:30, warm up and swim for 30-45 minutes, stroll along the shore for photos, rinse off and have a seafood/fish noodle breakfast nearby; sunset: a light swim or walk, capture the day's last light, then sit at a seaside cafe and watch the city lights come on."),
            infoBox("Mẹo nhỏ: Dùng kem chống nắng, mang nón rộng vành – kính mát, uống đủ nước; dùng túi chống nước cho điện thoại khi xuống nước; tránh mang theo đồ quý giá khi tắm.",
                "Tip: Use sunscreen, bring a wide-brimmed hat and sunglasses, stay hydrated; use a waterproof pouch for your phone when entering the water; avoid bringing valuables while swimming."),
            heading("Lưu ý an toàn", "Safety notes"),
            paragraph("Quan sát cờ cảnh báo: cờ đỏ – không xuống biển, cờ vàng – thận trọng; không bơi xa phao giới hạn, không xuống nước sau khi uống rượu bia; mùa sứa có thể xuất hiện – nếu châm chích, lên bờ rửa nước biển sạch vùng da, theo dõi phản ứng và liên hệ cứu hộ khi cần.",
                "Watch the warning flags: red - no swimming, yellow - caution; do not swim beyond the marker buoys, do not enter the water after drinking alcohol; jellyfish may appear in season - if stung, get ashore, rinse the area with clean seawater, monitor the reaction and contact lifeguards if needed."),
            quote("“Cát trắng, nắng vàng, biển xanh – đủ đầy cho một ngày thư giãn.”",
                "\"White sand, golden sun, blue sea - all you need for a day of relaxation.\""),
            divider(),
            mapBlock(new double[]{108.2479, 16.0603})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển", "Beach")).slug("bai-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Top bãi biển châu Á", "Top Asian beach")).slug("top-bai-bien-chau-a").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thể thao nước", "Water sports")).slug("the-thao-nuoc").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 2) Bà Nà Hills & Cầu Vàng */
  private static PlaceDoc poiBaNaHills() {
    String slug = "ba-na-hills-cau-vang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bà Nà Hills & Cầu Vàng", "Ba Na Hills & Golden Bridge"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Khu du lịch trên núi với khí hậu mát mẻ, nổi bật Cầu Vàng độc đáo.", "A mountain resort with a cool climate, highlighted by the unique Golden Bridge."))
        .description(of("Bà Nà Hills mang phong cách châu Âu, có làng Pháp, hầm rượu, vườn hoa Le Jardin, "
            + "trò chơi giải trí và đặc biệt là Cầu Vàng ‘bàn tay’ nổi tiếng.",
            "Ba Na Hills has a European style with a French Village, a wine cellar, Le Jardin flower garden, "
            + "amusement rides and especially the famous 'giant hands' Golden Bridge."))
        .addressLine(of("Thôn An Sơn, Hòa Ninh, Hòa Vang, Đà Nẵng", "An Son Village, Hoa Ninh, Hoa Vang, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{107.9963, 15.9996})
        .vibes(List.of("vibrant", "diverse"))
        .atmosphere(of("mountain-resort", "mountain-resort"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(480)
        .photographyScore(95)
        .romanticScore(85)
        .localExperienceScore(40)
        .images(withCover(
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng – Bà Nà Hills", "Golden Bridge - Ba Na Hills", true, 0),
            img("https://motogo.vn/wp-content/uploads/2019/11/11-toan-canh-ba-na-1554385320042306734627.jpg", "Toàn cảnh Bà Nà", "Ba Na panorama", false, 1),
            img("https://digiticket.vn/blog/wp-content/uploads/2022/03/lang-phap-ba-na-hills-1.png", "Làng Pháp", "French Village", false, 2),
            img("https://nhuminhplazahotel.com/wp-content/uploads/2023/08/cach-tao-dang-chup-anh-o-cau-vang-1.jpg", "Check-in Cầu Vàng", "Golden Bridge check-in", false, 3),
            img("https://thanhnien.mediacdn.vn/Uploaded/2014/Pictures20132/Thanh/1/CaptreoBaNa.jpg", "Cáp treo Bà Nà", "Ba Na cable car", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2025/01/kham-pha-le-hoi-hoa-ba-na-2025-03.jpg", "Vườn hoa", "Flower garden", false, 5)
        ))
        .content(List.of(
            heading("Bà Nà Hills – bốn mùa trong một ngày", "Ba Na Hills - four seasons in one day"),
            quote("""
                Mây ôm vai đỉnh núi,
                gió hát khúc bình yên;
                nắm tay qua Cầu Vàng, trời gần hơn một chút.
                """,
                """
                Clouds embrace the mountain peaks,
                wind sings a peaceful tune;
                cross the Golden Bridge hand in hand, the sky feels a little closer.
                """),
            paragraph("Tọa lạc trên dãy Trường Sơn cách trung tâm Đà Nẵng khoảng 30 km, Bà Nà Hills gây ấn tượng bởi khí hậu mát mẻ, cảnh quan đổi sắc theo giờ và quần thể kiến trúc lấy cảm hứng châu Âu; trải nghiệm đặc trưng là đi cáp treo xuyên tầng mây, dạo bộ giữa những quảng trường lát đá, ghé vườn hoa, hầm rượu, bảo tàng sáp và không thể thiếu khoảnh khắc check-in trên Cầu Vàng nổi danh.",
                "Set on the Truong Son range about 30 km from central Da Nang, Ba Na Hills impresses with its cool climate, scenery that shifts hue by the hour and a European-inspired architectural complex; signature experiences include a cable car ride through the clouds, strolls across stone-paved squares, visits to the flower garden, wine cellar and wax museum, and of course a check-in on the famous Golden Bridge."),
            paragraph("Để có trải nghiệm thoải mái, bạn nên xuất phát sớm từ thành phố, đến ga cáp trước giờ mở cửa một chút để hạn chế xếp hàng; lên cao nhiệt độ giảm nhanh, trời có thể nắng rực rồi mù sương chỉ trong vài chục phút nên hãy mặc lớp áo mỏng dễ tháo, mang theo áo mưa gọn nhẹ và che phủ cho máy ảnh, điện thoại khi băng qua những đoạn sương ẩm.",
                "For a comfortable trip, set off early from the city and arrive at the cable car station just before opening to avoid queues; the temperature drops quickly at altitude and the weather can switch from bright sun to fog within tens of minutes, so wear easily removable light layers, bring a compact raincoat and cover your camera and phone when passing through damp, foggy stretches."),
            imageBlock(img("https://cdnphoto.dantri.com.vn/XCxAd-hWfz0Kj0RrTDGY3filKsY=/thumb_w/1360/2023/08/24/caudocx-1692878010777.jpeg", "Cầu Vàng biểu tượng", "The iconic Golden Bridge", false, 0)),
            heading("Cầu Vàng & những góc sống ảo", "Golden Bridge & photo spots"),
            paragraph("Cầu Vàng nằm ở độ cao hơn 1.400 m, nổi bật với hai “bàn tay” đá nâng nhịp cầu cong mềm giữa thung mây; khung giờ đẹp thường là sáng sớm hoặc chiều muộn khi nắng xiên và khách chưa quá đông, bạn có thể đi vòng một lượt để chọn góc nhìn về phía rừng núi hoặc hướng thung lũng; khi có sương dày, ảnh sẽ mang sắc huyền ảo, còn lúc trời trong vắt thì màu xanh của núi rừng nổi bật hẳn lên.",
                "The Golden Bridge sits above 1,400 m, distinguished by two stone 'hands' that lift the gently curving span amid a valley of clouds; the best times are early morning or late afternoon when the light slants and crowds are thinner - walk a full loop to choose a view toward the forested mountains or the valley; in thick fog the photos take on a dreamy quality, while on clear days the green of the mountains stands out vividly."),
            heading("Làng Pháp, vườn hoa & hầm rượu", "French Village, gardens & wine cellar"),
            paragraph("Khu làng Pháp tái hiện quảng trường, tháp chuông, mái ngói đá và các con dốc lát đá mang âm hưởng Trung cổ, phù hợp tản bộ chậm rãi và ghé những ban công nhìn xuống thung lũng; Le Jardin d’Amour gồm nhiều khu vườn theo chủ đề với hoa theo mùa, kết hợp những mảng tường cây và tượng trang trí tạo chiều sâu cho khung hình; hầm rượu Debay làm mát tự nhiên, lý tưởng để dừng chân, đọc vài bảng tư liệu rồi tiếp tục hành trình lên khu đỉnh.",
                "The French Village recreates squares, a bell tower, stone-tiled roofs and paved slopes with a medieval feel, ideal for a slow walk and pausing at balconies overlooking the valley; Le Jardin d'Amour comprises several themed gardens with seasonal flowers, plus living walls and decorative statues that add depth to your shots; the naturally cool Debay wine cellar is a perfect place to rest, read a few information panels and then continue up to the summit area."),
            heading("Cáp treo & thời tiết", "Cable car & weather"),
            paragraph("Hệ thống cáp treo đưa bạn băng qua rừng nguyên sinh, thác nước và mây tầng; khi cabin vào vùng gió, cảm giác rung nhẹ là bình thường nhưng hãy hạn chế đứng lên thay đổi vị trí đột ngột; trên đỉnh thường mát hơn trung tâm thành phố từ 6–10°C, độ ẩm cao khiến mặt đá trơn, vì vậy giày đế bám và áo khoác mỏng rất hữu ích; mùa khô nắng gắt nhưng tia UV cao, vẫn cần kem chống nắng và mũ rộng vành.",
                "The cable car carries you over primary forest, waterfalls and layered clouds; light swaying when the cabin enters windy zones is normal, but avoid standing up to change positions abruptly; the summit is usually 6-10°C cooler than the city center, and high humidity makes stone surfaces slippery, so grippy shoes and a light jacket are very useful; the dry season is sunny but UV is high, so still bring sunscreen and a wide-brimmed hat."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng giữa mây", "Golden Bridge amid clouds", false, 0)),
            gallery(List.of(
                img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng giữa mây", "Golden Bridge amid clouds", false, 0),
                img("https://digiticket.vn/blog/wp-content/uploads/2022/03/lang-phap-ba-na-hills-1.png", "Làng Pháp châu Âu", "European French Village", false, 1),
                img("https://thanhnien.mediacdn.vn/Uploaded/2014/Pictures20132/Thanh/1/CaptreoBaNa.jpg", "Tuyến cáp treo", "Cable car route", false, 2)
            )),
            heading("Vui chơi & lịch sự kiện", "Entertainment & event schedule"),
            paragraph("Khu giải trí trong nhà có nhiều trò phù hợp gia đình, từ nhà gương, trượt ống, game tương tác đến khu trẻ em; quảng trường thường xuyên có màn trình diễn đường phố, carnival, hoặc lễ hội hoa theo mùa ở Le Jardin; lịch sự kiện thay đổi theo thời điểm nên bạn có thể tham khảo bảng thông tin tại cổng khu hoặc ngay tại quảng trường trung tâm để sắp xếp lộ trình hợp lý.",
                "The indoor entertainment area has many family-friendly attractions, from a mirror maze, tube slides and interactive games to a kids' zone; the squares often host street performances, carnivals or seasonal flower festivals at Le Jardin; the event schedule changes over time, so check the information boards at the area's gate or in the central square to plan your route well."),
            heading("Lịch trình gợi ý 1 ngày", "Suggested one-day itinerary"),
            paragraph("Sáng: đến ga sớm, đi cáp lên khu Cầu Vàng khi nắng còn dịu để chụp ảnh và dạo bộ qua rìa rừng; giữa sáng: tham quan vườn hoa Le Jardin, hầm rượu Debay, dừng café ngắm thung lũng; trưa: dùng bữa tại khu làng Pháp hoặc khu ẩm thực gần quảng trường; chiều: ghé bảo tàng sáp, vui chơi trong nhà khi trời nắng gắt hoặc mưa bất chợt; cuối chiều: quay lại khu Cầu Vàng hoặc điểm nhìn thoáng để săn ánh hoàng hôn trước khi xuống cáp.",
                "Morning: arrive early, ride the cable car up to the Golden Bridge while the sun is mild for photos and a walk along the forest edge; mid-morning: visit Le Jardin garden and the Debay wine cellar, stop for coffee overlooking the valley; noon: have lunch at the French Village or a food court near the square; afternoon: visit the wax museum and enjoy indoor activities during harsh sun or sudden rain; late afternoon: return to the Golden Bridge or an open viewpoint to catch the sunset before riding the cable car down."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Đi lại trên các bậc đá hãy bám tay vịn, tránh chạy nhảy khi mặt đá ẩm; giữ khoảng cách khi chụp ảnh ở mép lan can; mang theo ít nhất một chai nước nhỏ, tận dụng các khu nghỉ giữa tuyến để điều chỉnh nhịp di chuyển cho trẻ em và người lớn tuổi; khi sương quá dày hoặc mưa lớn, ưu tiên các điểm trong nhà rồi quay lại khu ngoài trời khi thời tiết ổn hơn.",
                "Hold the handrails on stone steps and avoid running on damp surfaces; keep a safe distance when taking photos near railing edges; carry at least a small bottle of water and use the rest stops along the route to pace children and older travelers; in very thick fog or heavy rain, favor indoor spots and return outdoors when the weather improves."),
            infoBox("Mẹo nhỏ: Mang áo khoác mỏng, kem chống nắng, nón và giày đế bám; kiểm tra dự báo thời tiết trước khi đi và xuất phát sớm để tận dụng khung giờ vắng; sạc đầy pin máy ảnh/điện thoại và chuẩn bị túi chống ẩm cho thiết bị.",
                "Tip: Bring a light jacket, sunscreen, a hat and grippy shoes; check the weather forecast before going and set off early to enjoy the quieter hours; fully charge your camera/phone and prepare a moisture-proof bag for your devices."),
            imageBlock(img("https://nhuminhplazahotel.com/wp-content/uploads/2023/08/cach-tao-dang-chup-anh-o-cau-vang-1.jpg", "Check-in Cầu Vàng", "Golden Bridge check-in", false, 0)),
            quote("“Lên Bà Nà một ngày, đổi bốn mùa thời tiết.”",
                "\"A day on Ba Na, four seasons of weather.\""),
            divider(),
            mapBlock(new double[]{107.9963, 15.9996})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Khu du lịch nghỉ dưỡng", "Resort destination")).slug("khu-du-lich-nghi-duong").build(),
            PlaceDoc.CategoryMini.builder().name(of("Giải trí", "Entertainment")).slug("giai-tri").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Cầu Vàng biểu tượng", "Iconic Golden Bridge")).slug("cau-vang-bieu-tuong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Cáp treo", "Cable car")).slug("cap-treo").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.EXPENSIVE)
        .build();
  }

  /** 3) Ngũ Hành Sơn (Marble Mountains) */
  private static PlaceDoc poiNguHanhSon() {
    String slug = "ngu-hanh-son";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Ngũ Hành Sơn", "Marble Mountains"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Cụm núi đá vôi gần biển với hang động, chùa chiền và làng đá mỹ nghệ.", "A cluster of limestone mountains near the sea with caves, temples and a stone-carving village."))
        .description(of("Gồm 5 ngọn núi Kim, Mộc, Thủy, Hỏa, Thổ. Nổi bật động Huyền Không, chùa Linh Ứng Non Nước. "
            + "Có làng nghề đá mỹ nghệ truyền thống dưới chân núi.",
            "Comprising five peaks named after Metal, Wood, Water, Fire and Earth. Highlights include Huyen Khong Cave and Linh Ung Non Nuoc Pagoda. "
            + "A traditional stone-carving craft village lies at the foot of the mountains."))
        .addressLine(of("81 Huyền Trân Công Chúa, Ngũ Hành Sơn, Đà Nẵng", "81 Huyen Tran Cong Chua, Ngu Hanh Son, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2639, 16.0031})
        .vibes(List.of("cultural", "peaceful"))
        .atmosphere(of("spiritual-nature", "spiritual-nature"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(120)
        .photographyScore(80)
        .romanticScore(65)
        .localExperienceScore(80)
        .images(withCover(
            img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Toàn cảnh Ngũ Hành Sơn", "Marble Mountains panorama", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/da-den-da-nang-thi-phai-oanh-tac-5-ngon-nui-o-ngu-hanh-son-1636215280.jpg", "Các ngọn núi", "The peaks", false, 1),
            img("http://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy4/lac-vao-tien-canh-noi-dong-huyen-khong-trong-day-ngu-hanh-son-241-09053814112017035012.jpg", "Động Huyền Không", "Huyen Khong Cave", false, 2),
            img("https://vietnamtravelcar.com.vn/wp-content/uploads/2025/04/chua-non-nuoc-da-nang-4.png", "Chùa Linh Ứng Non Nước", "Linh Ung Non Nuoc Pagoda", false, 3),
            img("https://www.phanthien.com/tin-tuc/images/tin-tuc-gioi-thieu/lang-da-non-nuoc-3.jpg", "Làng đá Non Nước", "Non Nuoc stone village", false, 4),
            img("https://cdn.tgdd.vn/Files/2021/07/13/1367741/kham-pha-ngu-hanh-son-thang-canh-noi-tieng-tai-da-nang-202202171027387108.jpg", "Lối lên núi", "Path up the mountain", false, 5)
        ))
        .content(List.of(
            heading("Ngũ Hành Sơn – linh sơn gần biển", "Marble Mountains - sacred peaks by the sea"),
            quote("""
                Sóng ru chân núi biếc,
                đá giữ tiếng chuông ngân;
                lối động vờn tia nắng, khói trầm quyện bước chân.
                """,
                """
                Waves lull the foot of the green peaks,
                stone keeps the ringing of the bell;
                cave paths play with sunbeams, incense smoke trails each step.
                """),
            paragraph("Cụm Ngũ Hành Sơn gồm năm ngọn Kim, Mộc, Thủy, Hỏa, Thổ trồi lên giữa dải cát ven biển, tạo nên một quần thể vừa hùng vĩ vừa gần gũi; chỉ cách trung tâm Đà Nẵng không xa, nơi đây thích hợp cho chuyến leo nhẹ kết hợp tham quan hang động, chùa chiền và chiêm nghiệm vẻ đẹp đá – gió – biển giao hòa.",
                "The Marble Mountains comprise five peaks - Metal, Wood, Water, Fire and Earth - rising from the coastal sand strip to form a complex that is both majestic and intimate; not far from central Da Nang, it suits a light climb combined with visits to caves and temples and contemplating the harmony of stone, wind and sea."),
            heading("Khám phá tuyến Thủy Sơn", "Exploring the Thuy Son route"),
            paragraph("Thủy Sơn là ngọn lớn nhất và dễ tham quan nhất với hệ thống bậc đá, thang máy và các lối mòn dẫn lên nhiều đài vọng cảnh; từ đây bạn có thể nhìn dọc bờ biển Võ Nguyên Giáp, thấy rõ các dải cát cong ôm sóng và khu dân cư bên dưới; bề mặt đá ẩm rêu dễ trơn nên hãy đi giày đế bám, mang theo chai nước nhỏ và giữ nhịp di chuyển ổn định.",
                "Thuy Son is the largest and most accessible peak, with stone steps, an elevator and trails leading up to several viewpoints; from here you can look along Vo Nguyen Giap beach, clearly seeing the curving sand strips hugging the waves and the residential area below; the moss-damp stone surfaces are slippery, so wear grippy shoes, carry a small bottle of water and keep a steady pace."),
            imageBlock(img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/ngu-hanh-son-da-nang2.jpg", "Ngũ Hành Sơn nhìn từ trên cao", "Marble Mountains from above", false, 0)),
            heading("Động Huyền Không & chùa Linh Ứng Non Nước", "Huyen Khong Cave & Linh Ung Non Nuoc Pagoda"),
            paragraph("Động Huyền Không nổi bật bởi giếng trời phóng tia sáng xiên vào lòng hang, tạo hiệu ứng khói trầm bảng lảng rất đặc trưng; khi mắt quen tối bạn sẽ thấy các vách đá mang vân tự nhiên như những nếp gấp thời gian; gần đó là chùa Linh Ứng Non Nước trầm mặc, chuông ngân lan qua thung đá; khi vào không gian thờ tự nên ăn mặc lịch sự, giữ im lặng và đi theo lối chỉ dẫn.",
                "Huyen Khong Cave is famous for a skylight that casts slanting beams into the cavern, creating a distinctive hazy effect with incense smoke; once your eyes adjust to the dark you will see rock walls bearing natural veins like folds of time; nearby is the quiet Linh Ung Non Nuoc Pagoda, its bell ringing across the stone valley; when entering the place of worship, dress modestly, stay silent and follow the marked path."),
            gallery(List.of(
                img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Toàn cảnh Ngũ Hành Sơn", "Marble Mountains panorama", false, 0),
                img("http://media.baobinhphuoc.com.vn/Content/UploadFiles/EditorFiles/images/2017/Quy4/lac-vao-tien-canh-noi-dong-huyen-khong-trong-day-ngu-hanh-son-241-09053814112017035012.jpg", "Động Huyền Không", "Huyen Khong Cave", false, 1),
                img("https://www.phanthien.com/tin-tuc/images/tin-tuc-gioi-thieu/lang-da-non-nuoc-3.jpg", "Làng đá Non Nước", "Non Nuoc stone village", false, 2)
            )),
            heading("Làng đá Non Nước & trải nghiệm thủ công", "Non Nuoc stone village & craft experience"),
            paragraph("Dưới chân núi là làng nghề đá Non Nước với lịch sử lâu đời; bạn có thể quan sát nghệ nhân đục đẽo, mài giũa từng chi tiết tượng, bình, đồ trang sức; nếu mua làm quà hãy ưu tiên kích thước vừa phải để tiện mang theo, kiểm tra cạnh mài nhẵn, độ cân đối và hỏi rõ cách đóng gói vận chuyển; nhiều cơ sở có dịch vụ in khắc tên lên mặt đá trong ngày.",
                "At the foot of the mountains lies the long-established Non Nuoc stone-carving village; you can watch artisans chisel and polish every detail of statues, vases and jewelry; if buying gifts, favor moderate sizes that are easy to carry, check that edges are smoothly ground and the piece is balanced, and ask clearly about packing and shipping; many workshops offer same-day name engraving on the stone."),
            heading("Lịch trình gợi ý 1/2 ngày", "Suggested half-day itinerary"),
            paragraph("Sáng đi sớm để tránh nắng: mua vé, lên Thủy Sơn theo lối bậc đá hoặc thang máy, dừng ở các vọng đài chụp ảnh toàn cảnh biển, vào động Huyền Không và các am nhỏ; giữa buổi ghé chùa Linh Ứng Non Nước, nghỉ ở sân chùa; cuối buổi xuống chân núi tham quan làng đá và chọn vài món lưu niệm trước khi trở lại trung tâm.",
                "Go early in the morning to avoid the heat: buy tickets, climb Thuy Son by stone steps or elevator, stop at the viewpoints for panoramic sea photos, enter Huyen Khong Cave and the small shrines; mid-session, visit Linh Ung Non Nuoc Pagoda and rest in the temple yard; at the end, descend to the foot of the mountain to visit the stone village and pick a few souvenirs before returning to the center."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Mặt đá có thể ẩm trơn sau mưa, đi chậm bám tay vịn; mang theo nón, kem chống nắng và một chai nước nhỏ; trong hang tối nên chú ý bậc lên xuống và nhường lối ở các đoạn hẹp; tôn trọng không gian tôn giáo, không chạm khắc lên đá hay để lại rác; nếu đi cùng trẻ nhỏ, luôn nắm tay tại khu vực bậc cao và lan can thấp.",
                "Stone surfaces can be damp and slippery after rain, so walk slowly and hold the handrails; bring a hat, sunscreen and a small bottle of water; in the dark caves watch the steps and give way at narrow sections; respect the religious space, do not carve on the rock or leave litter; if traveling with small children, always hold hands at high steps and low railings."),
            quote("“Núi gần biển, chùa trong đá – linh khí hội tụ.”",
                "\"Mountains by the sea, temples within stone - where sacred energy gathers.\""),
            divider(),
            mapBlock(new double[]{108.2639, 16.0031})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-giao").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Ngũ Hành Sơn", "Marble Mountains")).slug("ngu-hanh-son").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Làng nghề thủ công", "Craft village")).slug("lang-nghe-thu-cong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 4) Cầu Rồng & phun lửa/cứa nước */
  private static PlaceDoc poiCauRong() {
    String slug = "cau-rong";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Cầu Rồng", "Dragon Bridge"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Biểu tượng Đà Nẵng bắc qua sông Hàn, trình diễn phun lửa – phun nước cuối tuần.", "A Da Nang landmark spanning the Han River, with weekend fire and water shows."))
        .description(of("Cầu Rồng nổi bật với kiến trúc rồng vàng uốn lượn. Thường phun lửa/nước vào tối thứ 7, CN, "
            + "thu hút đông đảo du khách dọc bờ sông Hàn.",
            "The Dragon Bridge stands out with its winding golden dragon design. It usually breathes fire and water on Saturday and Sunday nights, "
            + "drawing large crowds along the banks of the Han River."))
        .addressLine(of("Đường Võ Văn Kiệt – Nguyễn Văn Linh, Hải Châu, Đà Nẵng", "Vo Van Kiet - Nguyen Van Linh street, Hai Chau, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2269, 16.0613})
        .vibes(List.of("vibrant", "urban"))
        .atmosphere(of("urban-waterfront", "urban-waterfront"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("photography", "culture"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.EVENING, BestVisitTime.NIGHT))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(45)
        .photographyScore(90)
        .romanticScore(80)
        .localExperienceScore(70)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng về đêm", "Dragon Bridge at night", true, 0),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/06/07/17/03/w1230/cau1749269023_3019.jpg", "Phun lửa", "Fire breathing", false, 1),
            img("https://www.vietfuntravel.com.vn/image/data/Da-Nang/cau-rong-da-nang/Lich-phun-lua-cau-rong-cau-rong-phun-lua-luc-may-gio-h4.jpg", "Phun nước", "Water spraying", false, 2),
            img("https://cdn.vntour.com.vn/storage/media/img/2019/07/29/ket-cau-cau-rong_1564395041.jpg", "Cận cảnh kiến trúc", "Close-up of the architecture", false, 3),
            img("https://danangfantasticity.com/wp-content/uploads/2018/10/cau-rong-top-20-cay-cau-ky-quai-nhat-the-gioi-theo-boredom-therapy-02.jpg", "View sông Hàn", "Han River view", false, 4),
            img("https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/12_2019/cau-rong-da-nang-2.jpg", "Check-in cuối tuần", "Weekend check-in", false, 5)
        ))
        .content(List.of(
            heading("Cầu Rồng – biểu tượng hiện đại trên sông Hàn", "Dragon Bridge - a modern icon on the Han River"),
            quote("""
                    Rồng vươn qua bến gió,
                    lửa vẽ nét đêm vàng;
                    sương nước chao mặt sóng,
                    tiếng người vỡ thành trang.
                """,
                """
                    The dragon reaches across the windy quay,
                    fire paints the golden night;
                    water mist sways on the waves,
                    the crowd's voices spill into pages.
                """),
            paragraph("Cây cầu mang dáng rồng uốn lượn này nối bờ Đông – bờ Tây, ban ngày sắc vàng nổi bật giữa trời xanh, ban đêm chuyển mình dưới dải LED rực rỡ; cuối tuần, màn trình diễn phun lửa – phun nước biến bờ sông Hàn thành sân khấu mở, kéo người dân và du khách tụ về tạo nên không khí rất “Đà Nẵng”: trẻ trung, trật tự, thân thiện.",
                "This winding dragon-shaped bridge connects the east and west banks; by day its gold stands out against the blue sky, by night it transforms under brilliant LED lights; on weekends the fire and water show turns the Han River banks into an open stage, drawing locals and visitors together to create a very 'Da Nang' vibe: youthful, orderly and friendly."),
            heading("Kinh nghiệm xem phun lửa – phun nước", "Tips for watching the fire and water show"),
            paragraph("Thời điểm trình diễn thường rơi vào tối thứ Bảy và Chủ nhật, khung giờ khoảng 21:00 (có thể thay đổi theo thông báo của thành phố); nên đến sớm 20–30 phút để chọn vị trí đứng; mặt gió và góc gần đầu rồng sẽ xem rõ hơn nhưng dễ ướt khi phun nước, hãy bọc máy ảnh/điện thoại bằng túi chống nước mỏng; nếu đi cùng trẻ nhỏ, đứng lùi vài mét để tránh hơi nóng lúc phun lửa.",
                "The show usually takes place on Saturday and Sunday nights around 21:00 (subject to change per city announcements); arrive 20-30 minutes early to choose a spot; the windward side and angles near the dragon's head offer the best view but get wet during the water spray, so wrap your camera/phone in a thin waterproof pouch; if traveling with small children, stand back a few meters to avoid the heat during the fire breathing."),
            paragraph("Các điểm đứng phổ biến: bờ Đông dọc đường Trần Hưng Đạo (gần cầu Tình Yêu – Cá chép hóa Rồng) cho khung hình “đầu rồng – phố đèn”; bờ Tây trên đường Bạch Đằng cho góc rộng ôm trọn cầu và sông; với người thích phơi sáng, có thể chọn xa hơn về phía cầu sông Hàn để gom ánh đèn thành vệt mượt.",
                "Popular viewing spots: the east bank along Tran Hung Dao street (near the Love Bridge and the Carp-turning-into-Dragon statue) for a 'dragon head and city lights' frame; the west bank on Bach Dang street for a wide angle embracing the whole bridge and river; for long-exposure lovers, choose a spot farther toward the Han River Bridge to gather the lights into smooth streaks."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng rực rỡ về đêm", "The brilliant Dragon Bridge at night", false, 0)),
            heading("Góc chụp ảnh & mẹo nhỏ", "Photo angles & tips"),
            paragraph("Khung hình cận: lấy nét ở đầu rồng, canh lúc tia lửa bung mạnh hoặc tia nước rơi thành màn sương; khung hình rộng: đặt đường chân trời ở 1/3, để cầu kéo mắt theo chiều ngang; có thể khai thác phản chiếu trên mặt nước hoặc các vũng nước ven bờ sau mưa; thời gian “giờ xanh” trước 19:00–19:15 (mùa hè) cho bầu trời chuyển sắc rất đẹp.",
                "Close-up frame: focus on the dragon's head and time the moment the flames burst or the water falls into a misty veil; wide frame: place the horizon on the lower third and let the bridge lead the eye horizontally; you can use reflections on the river or roadside puddles after rain; the 'blue hour' before 19:00-19:15 (summer) gives a beautifully shifting sky."),
            paragraph("Thiết bị: điện thoại bật Night mode, giữ máy vững; máy ảnh nên mang tripod nhỏ, tốc 1/10–1/2s cho vệt nước mềm; luôn chú ý an toàn người và thiết bị trước đám đông, tránh đứng sát mép kè.",
                "Gear: enable Night mode on your phone and hold it steady; for a camera, bring a small tripod and use 1/10-1/2s for soft water trails; always mind your safety and gear in the crowd and avoid standing right at the embankment edge."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Ánh sáng phủ vàng thân rồng", "Light bathes the dragon's body in gold", false, 0),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/06/07/17/03/w1230/cau1749269023_3019.jpg", "Khoảnh khắc phun lửa", "The fire-breathing moment", false, 1),
                img("https://www.vietfuntravel.com.vn/image/data/Da-Nang/cau-rong-da-nang/Lich-phun-lua-cau-rong-cau-rong-phun-lua-luc-may-gio-h4.jpg", "Màn phun nước làm mát đêm hè", "The water show cooling a summer night", false, 2)
            )),
            heading("Đi bộ bờ Đông – bờ Tây & điểm ghé gần kề", "Walking the east and west banks & nearby stops"),
            paragraph("Bờ Đông (Trần Hưng Đạo) có lối dạo bộ, tượng Cá chép hóa Rồng, chợ đêm Sơn Trà; bờ Tây (Bạch Đằng) là tuyến phố đi bộ kết hợp công viên APEC, nhiều quán cà phê và góc ngắm sông; bạn có thể bắt đầu từ công viên APEC, men theo Bạch Đằng lên chân cầu, qua cầu sang bờ Đông, dừng ở cầu Tình Yêu và quay lại theo đường Võ Văn Kiệt.",
                "The east bank (Tran Hung Dao) has a promenade, the Carp-turning-into-Dragon statue and the Son Tra night market; the west bank (Bach Dang) is a pedestrian street with APEC Park, many cafes and river-view spots; you can start at APEC Park, follow Bach Dang up to the bridge foot, cross to the east bank, stop at the Love Bridge and return via Vo Van Kiet street."),
            heading("Giao thông & lưu ý an toàn", "Traffic & safety notes"),
            paragraph("Vào khung giờ trình diễn, thường có hạn chế/điều tiết giao thông trên cầu; tuân thủ hướng dẫn của lực lượng chức năng, không tụ tập giữa lòng đường; giữ gìn trật tự, không xả rác, không trèo qua lan can; nếu trời mưa gió, chất lượng màn trình diễn có thể rút gọn hoặc hoãn – bạn nên linh hoạt kế hoạch.",
                "During show hours, traffic on the bridge is usually restricted or regulated; follow the instructions of officials and do not gather in the middle of the road; keep order, do not litter and do not climb over the railings; in wind and rain the show may be shortened or postponed, so keep your plans flexible."),
            quote("“Đến Đà Nẵng, ngắm Rồng thở lửa – để thấy một thành phố đang thở cùng sông gió.”",
                "\"Come to Da Nang and watch the dragon breathe fire - to see a city breathing with its river and wind.\""),
            divider(),
            mapBlock(new double[]{108.2269, 16.0613})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Biểu tượng thành phố", "City landmark")).slug("bieu-tuong-thanh-pho").build(),
            PlaceDoc.CategoryMini.builder().name(of("Giải trí", "Entertainment")).slug("giai-tri").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Phun lửa cuối tuần", "Weekend fire show")).slug("phun-lua-cuoi-tuan").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đời sống về đêm", "Nightlife")).slug("doi-song-ve-dem").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 5) Bán đảo Sơn Trà – Đỉnh Bàn Cờ & Chùa Linh Ứng */
  private static PlaceDoc poiSonTra() {
    String slug = "ban-dao-son-tra";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bán đảo Sơn Trà", "Son Tra Peninsula"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Lá phổi xanh của Đà Nẵng, có Đỉnh Bàn Cờ, Chùa Linh Ứng, đường ven biển đẹp.", "Da Nang's green lung, with Ban Co Peak, Linh Ung Pagoda and a scenic coastal road."))
        .description(of("Sơn Trà phù hợp chạy xe ngắm cảnh, săn mây ở Đỉnh Bàn Cờ, thăm tượng Quan Âm ở chùa Linh Ứng "
            + "và khám phá các bãi biển hoang sơ như Bãi Bụt, Bãi Đá Đen.",
            "Son Tra is great for scenic drives, cloud hunting at Ban Co Peak, visiting the Lady Buddha statue at Linh Ung Pagoda "
            + "and exploring pristine beaches such as Bai But and Bai Da Den."))
        .addressLine(of("Thọ Quang, Sơn Trà, Đà Nẵng", "Tho Quang, Son Tra, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2752, 16.1146})
        .vibes(List.of("nature", "peaceful"))
        .atmosphere(of("coastal-forest", "coastal-forest"))
        .travelStyle(List.of("casual", "photography", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "relax", "photography"))
        .crowdLevel(CrowdLevel.MEDIUM)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.OUTDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(180)
        .photographyScore(90)
        .romanticScore(75)
        .localExperienceScore(70)
        .images(withCover(
            img("https://images.vietnamtourism.gov.vn/vn//images/2021/Thang_5/son_tra.jpeg", "Toàn cảnh Sơn Trà", "Son Tra panorama", true, 0),
            img("https://statics.vinpearl.com/dinh-ban-co-2_1629274112.jpg", "Đỉnh Bàn Cờ", "Ban Co Peak", false, 1),
            img("https://tptravel.com.vn/mediacenter/media/images/1850/news/ava/s900_0/top-30-dia-diem-khong-the-bo-qua-khi-du-lich-da-nang-chua-linh-ung-1525752347.png", "Chùa Linh Ứng", "Linh Ung Pagoda", false, 2),
            img("https://cdn.nhandan.vn/images/7e998c4a71a4f777f488783c8e3c221553e525b23293623350984c0effb056d2f5d1850352abbb9f167445a5076ae533e667d376bf869970b83bd2a9ea12e0ea/ndo_br_2-copy.jpg", "Cung đường ven biển", "Coastal road", false, 3),
            img("https://tour.dulichvietnam.com.vn/uploads/image/du-lich-da-nang/du-lich-bai-but-da-nang.jpg", "Bãi Bụt", "Bai But beach", false, 4),
            img("https://nld.mediacdn.vn/291774122806476800/2024/2/7/cay-da-2-17073189149831055179085.jpg", "Rừng già Sơn Trà", "Son Tra old-growth forest", false, 5)
        ))
        .content(List.of(
            heading("Sơn Trà – góc nhìn biển và rừng", "Son Tra - a view of sea and forest"),
            quote("""
                    Mây quấn đầu non biếc,
                    sóng vỗ mặt kè xa;
                    một bước nghiêng vào gió,
                    rừng chạm tiếng hải hà.
                """,
                """
                    Clouds wrap the green peak,
                    waves lap the distant embankment;
                    one step leaning into the wind,
                    the forest touches the voice of the sea.
                """),
            paragraph("Bán đảo Sơn Trà là “lá phổi xanh” của thành phố: một bên rừng nguyên sinh, một bên biển xanh ôm theo cung đường uốn lượn. Đường rộng, dốc nối dốc, nhiều cua gắt; trước khi đi nên kiểm tra phanh, lốp, đổ đủ xăng và xem dự báo thời tiết. Tránh chạy nhanh ở các khúc có cát/đá rơi, giữ khoảng cách với xe trước và nhường đường cho phương tiện leo dốc.",
                "Son Tra Peninsula is the city's 'green lung': primary forest on one side, blue sea hugging the winding road on the other. The road is wide with slope after slope and many sharp bends; before going, check your brakes and tires, fill up enough fuel and check the weather forecast. Avoid speeding on stretches with fallen sand or rocks, keep distance from the vehicle ahead and give way to vehicles climbing uphill."),
            heading("Đỉnh Bàn Cờ – săn mây, đón gió", "Ban Co Peak - chasing clouds, catching the wind"),
            paragraph("Đỉnh Bàn Cờ là điểm cao dễ tiếp cận để ngắm toàn cảnh vịnh Đà Nẵng. Đẹp nhất là bình minh (mùa khô 05:15–06:00) khi tầng mây mỏng lẫn ánh hồng; giữa trưa thường oi và chói, ảnh dễ gắt. Mang áo gió mỏng, nước uống nhỏ gọn, giày đế bám; trời mưa sương dày, tầm nhìn kém thì cân nhắc không lên.",
                "Ban Co Peak is an easily accessible high point for a panoramic view of Da Nang Bay. It is best at sunrise (dry season 05:15-06:00) when thin clouds mingle with a pink glow; midday is often muggy and harsh, with overly contrasty photos. Bring a light windbreaker, a small water bottle and grippy shoes; in rain or thick fog with poor visibility, consider not going up."),
            imageBlock(img("https://static.vinwonders.com/2022/06/dinh-Ban-Co-thumb.jpg", "Đỉnh Bàn Cờ săn mây", "Cloud hunting at Ban Co Peak", false, 0)),
            heading("Chùa Linh Ứng – tượng Quan Âm nhìn biển", "Linh Ung Pagoda - the Lady Buddha facing the sea"),
            paragraph("Khu chùa Linh Ứng tọa lạc trên triền núi nhìn thẳng ra biển, nổi bật với tượng Quan Âm cao sừng sững. Khi vào không gian thờ tự, giữ trang phục lịch sự, đi nhẹ nói khẽ, không leo trèo lên bậc/lan can để chụp ảnh. Khu vực gửi xe có người trông, nên khóa cổ và lấy vé rõ ràng.",
                "Linh Ung Pagoda sits on a mountainside facing the sea, distinguished by the towering Lady Buddha statue. When entering the place of worship, dress modestly, walk softly and speak quietly, and do not climb the steps or railings for photos. The parking area is attended, so use the steering lock and keep your ticket clearly."),
            heading("Bãi đẹp & dừng chân", "Lovely beaches & rest stops"),
            paragraph("Bãi Bụt gần chùa, nước trong, lặng gió thì thích hợp tắm và chèo SUP; Bãi Đá Đen xa và hoang sơ hơn, mặt đá trơn cần giày đế bám, tuyệt đối không nhảy từ mỏm cao. Vào mùa gió chướng, sóng mạnh và dòng chảy xiết – theo dõi khuyến cáo an toàn trước khi xuống nước.",
                "Bai But is near the pagoda with clear water; when calm it is great for swimming and SUP; Bai Da Den is farther and more pristine, with slippery rocks that require grippy shoes - never jump from high ledges. In the monsoon-wind season the waves are strong and currents swift - check safety advisories before entering the water."),
            heading("Voọc chà vá chân nâu & quy tắc ứng xử", "Red-shanked douc langurs & rules of conduct"),
            paragraph("Sơn Trà là nhà của voọc chà vá chân nâu – “nữ hoàng linh trưởng”. Nếu gặp đàn voọc, giữ khoảng cách, tắt máy ngắm yên lặng, không cho ăn hay dùng đèn flash; rác mang vào – mang ra, tránh mở nhạc lớn. Việc cho động vật hoang dã ăn sẽ làm chúng lệ thuộc và gây rủi ro sức khỏe.",
                "Son Tra is home to the red-shanked douc langur - the 'queen of primates'. If you meet a troop, keep your distance, turn off the engine and watch quietly, do not feed them or use flash; pack out what you bring in and avoid playing loud music. Feeding wild animals makes them dependent and poses health risks."),
            gallery(List.of(
                img("https://images.vietnamtourism.gov.vn/vn//images/2021/Thang_5/son_tra.jpeg", "Toàn cảnh Sơn Trà", "Son Tra panorama", false, 0),
                img("https://statics.vinpearl.com/dinh-ban-co-2_1629274112.jpg", "Đỉnh Bàn Cờ săn mây", "Cloud hunting at Ban Co Peak", false, 1),
                img("https://tptravel.com.vn/mediacenter/media/images/1850/news/ava/s900_0/top-30-dia-diem-khong-the-bo-qua-khi-du-lich-da-nang-chua-linh-ung-1525752347.png", "Chùa Linh Ứng", "Linh Ung Pagoda", false, 2)
            )),
            heading("Cung đường ven biển & góc chụp", "Coastal road & photo spots"),
            paragraph("Từ đường Hoàng Sa ôm sát mép nước đến dốc lên rừng là chuỗi góc chụp “biển – đường – vệt xe” rất đã. Khung giờ vàng 16:30–17:30 cho ánh xiên ấm; đặt đường chân trời ở 1/3 khung hình, tận dụng lan can/kè đá làm đường dẫn; sau mưa, phản chiếu trên vũng nước giúp ảnh nổi khối.",
                "From Hoang Sa road hugging the water's edge to the slope up into the forest is a string of satisfying 'sea - road - vehicle trail' photo angles. The golden hour 16:30-17:30 gives warm, slanting light; place the horizon on the lower third, use the railing or stone embankment as a leading line; after rain, reflections in puddles add depth."),
            heading("Gợi ý lộ trình 1/2 ngày", "Suggested half-day route"),
            paragraph("Xuất phát trung tâm → đường Võ Nguyên Giáp – Hoàng Sa → dừng Chùa Linh Ứng vãn cảnh → tiếp tục lên Đỉnh Bàn Cờ săn mây, nghỉ ngắn → xuống Bãi Bụt tắm biển/ăn nhẹ → men cung đường ven biển ngắm hoàng hôn rồi quay về. Nếu có thêm thời gian, vòng tiếp qua cây đa di sản và các vọng cảnh dọc tuyến.",
                "Depart the center -> Vo Nguyen Giap - Hoang Sa road -> stop at Linh Ung Pagoda to enjoy the scenery -> continue up to Ban Co Peak to chase clouds, with a short rest -> head down to Bai But for a swim/snack -> follow the coastal road to watch the sunset, then return. With extra time, continue past the heritage banyan tree and the viewpoints along the route."),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Đường núi nhiều khúc khuỷu, luôn chạy số phù hợp, không thả dốc dài; trời mưa sương dễ trơn – giảm tốc và bật đèn xe; sóng/giật cảnh báo đỏ thì không xuống nước; hạn chế dùng drone gần khu bảo tồn; giữ gìn vệ sinh, tuyệt đối không xả rác hay bẻ cành. Sóng điện thoại có nơi yếu – báo trước lịch trình với bạn đồng hành.",
                "The mountain road is winding, so always use an appropriate gear and do not coast down long slopes; rain and fog make it slippery - slow down and turn on your lights; do not enter the water under red wave/rip warnings; limit drone use near the conservation area; keep it clean and never litter or break branches. Phone signal is weak in places - share your itinerary with your companions in advance."),
            quote("“Một bên rừng, một bên biển – đứng ở Sơn Trà như đứng giữa màu xanh của thành phố.”",
                "\"Forest on one side, sea on the other - standing on Son Tra is like standing in the green heart of the city.\""),
            divider(),
            mapBlock(new double[]{108.2752, 16.1146})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thiên nhiên", "Nature")).slug("thien-nhien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Công trình tôn giáo", "Religious site")).slug("cong-giao").build(),
            PlaceDoc.CategoryMini.builder().name(of("Bãi biển", "Beach")).slug("bai-bien").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Đỉnh Bàn Cờ săn mây", "Cloud hunting at Ban Co Peak")).slug("dinh-ban-co-san-may").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Bảo tồn thiên nhiên", "Nature conservation")).slug("bao-ton-thien-nhien").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .build();
  }

  /** 6) Chợ Hàn */
  private static PlaceDoc poiChoHan() {
    String slug = "cho-han";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Chợ Hàn", "Han Market"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Khu chợ lâu đời giữa trung tâm, nổi tiếng đặc sản Đà Nẵng.", "A long-established downtown market famous for Da Nang specialties."))
        .description(of("Bạn có thể tìm thấy nhiều đặc sản như mực khô, tré, bò khô, bánh khô mè… cùng quần áo, "
            + "đồ lưu niệm. Thương lượng giá nhẹ nhàng để có trải nghiệm vui.",
            "You can find many specialties such as dried squid, tre, dried beef and sesame crackers, along with clothes "
            + "and souvenirs. Bargain gently for a fun experience."))
        .addressLine(of("119 Trần Phú, Hải Châu 1, Hải Châu, Đà Nẵng", "119 Tran Phu, Hai Chau 1, Hai Chau, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2236, 16.0682})
        .vibes(List.of("lively", "diverse"))
        .atmosphere(of("urban-market", "urban-market"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("food", "explore"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(60)
        .photographyScore(60)
        .romanticScore(30)
        .localExperienceScore(90)
        .images(withCover(
            img("https://danangfantasticity.com/wp-content/uploads/2025/04/trinh-cho-han-thuong-hieu-ca-phe-da-nang-giua-long-cho-truyen-thong-06.jpg", "Cổng chợ Hàn", "Han Market gate", true, 0),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/27/w1230/cho1753090033_0256.jpg", "Không gian bên trong", "Interior space", false, 1),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/39/w1230/cho1753090781_5709.jpg", "Quầy đặc sản", "Specialty stall", false, 2),
            img("https://statics.vinpearl.com/cho-han-da-nang-16_1637569200.jpg", "Khu bán đồ khô", "Dried goods area", false, 3),
            img("https://static.vinwonders.com/2022/03/cho-han-da-nang-10.jpg", "Quầy hải sản", "Seafood stall", false, 4),
            img("https://images2.thanhnien.vn/528068263637045248/2023/5/11/anh-cho-han-dong-kin-du-khach-han-quoc-3-1683792617237973849935.jpg", "Khách tham quan", "Visitors", false, 5)
        ))
        .content(List.of(
            heading("Chợ Hàn – góc đặc sản giữa lòng Đà Nẵng", "Han Market - a corner of specialties in the heart of Da Nang"),
            quote("""
                    Sáng mở hàng rộn rã,
                    mùi nắng ươm hương mè;
                    bước chợ nghe tiếng gọi,
                    tay gói chút mang về.
                """,
                """
                    A bustling morning as stalls open,
                    sunlight ripens the scent of sesame;
                    walking the market amid the calls,
                    hands wrap a little to take home.
                """),
            paragraph("Nằm giữa trung tâm, chợ Hàn gói trọn nhịp sống địa phương: lối đi ken quầy, giọng rao lanh lảnh, sắc màu đặc sản bày ngay ngắn. Đi sớm buổi sáng không khí mát, hàng hóa mới; trưa đông khách dễ mệt và khó thử hàng. Mang theo túi vải hoặc balo trống để xếp đồ gọn, nhớ chuẩn bị tiền lẻ.",
                "Set in the city center, Han Market captures the rhythm of local life: aisles packed with stalls, ringing vendor calls and neatly arranged colorful specialties. Going early in the morning means cool air and fresh goods; midday crowds can be tiring and make sampling hard. Bring a cloth bag or empty backpack to pack items neatly, and remember to bring small change."),
            heading("Khu hàng khô & đặc sản mang về", "Dried goods & specialties to take home"),
            paragraph("Các quầy mực khô, cá rim, bò khô, tré, bánh khô mè… thường cho thử/đóng gói hút chân không. Trước khi mua: hỏi rõ nguồn gốc, hạn dùng, trọng lượng tịnh; so sánh 2–3 quầy gần nhau về độ dày lát, độ khô, mùi. Hút chân không giúp giữ vị và tiện xếp vali; với hàng có nước sốt, yêu cầu chống rò bằng lớp bọc kép.",
                "Stalls selling dried squid, braised fish, dried beef, tre and sesame crackers usually offer samples and vacuum packing. Before buying: ask clearly about origin, expiry and net weight; compare 2-3 nearby stalls on slice thickness, dryness and smell. Vacuum packing preserves flavor and packs neatly into luggage; for saucy items, ask for double wrapping to prevent leaks."),
            heading("Mẹo trả giá & ứng xử", "Bargaining tips & etiquette"),
            paragraph("Giá thường có biên độ thương lượng nhẹ, nhất là khi mua nhiều: mỉm cười hỏi giá, xin “giá tốt khi lấy 2–3 phần”, tránh mặc cả quá sâu làm mất vui. Khi thử món, lấy lượng nhỏ, không làm rơi vãi; nếu không mua, cảm ơn nhẹ nhàng rồi di chuyển.",
                "Prices usually have a small margin for bargaining, especially when buying in quantity: smile and ask the price, request a 'better price for 2-3 portions', and avoid haggling too hard which spoils the mood. When sampling, take a small amount and do not spill; if you decide not to buy, thank them politely and move on."),
            heading("Hải sản – quần áo – quà lưu niệm", "Seafood - clothes - souvenirs"),
            paragraph("Khu hải sản tươi/khô tỏa mùi mạnh; chọn quầy sạch, sàn khô, bao tay gọn gàng. Khu quần áo – vải vóc thuận tiện tìm đồ mỏng nhẹ đi biển; kiểm tra đường may, bảng size và chính sách đổi. Quà lưu niệm như nam châm, móc khóa, đồ có hình cầu Rồng/biển Đà Nẵng thích hợp làm quà số lượng lớn.",
                "The fresh/dried seafood area has a strong smell; choose stalls that are clean, with dry floors and tidy gloves. The clothing and fabric area is handy for light beach wear; check the stitching, size charts and exchange policy. Souvenirs such as magnets, keychains and items featuring the Dragon Bridge or Da Nang beach are good for buying gifts in bulk."),
            heading("Đóng gói – vận chuyển", "Packing - shipping"),
            paragraph("Hỏi gian hàng về combo quà tặng/giỏ quà, cân nặng sau hút chân không và kích thước để sắp vali. Nếu gửi xa: nhờ bọc thêm lớp xốp/bong bóng, dán nhãn “hàng khô” và tờ hướng dẫn bảo quản. Tránh mua quá tay đồ nặng mùi nếu ở chung ký túc/xe khách.",
                "Ask the stalls about gift combos/hampers, the weight after vacuum packing and dimensions for packing your suitcase. If shipping far: ask for extra foam/bubble wrap, label it 'dried goods' and include a storage instruction note. Avoid overbuying strong-smelling items if you share a dorm or coach."),
            heading("Ăn nhẹ trong chợ", "Snacking in the market"),
            paragraph("Khu ẩm thực có mì Quảng, bún chả cá, bánh tráng cuốn thịt heo, chè… Chọn quầy đông nhưng xoay vòng nhanh, gọi suất nhỏ để thử được nhiều món. Nhớ lau tay trước khi ăn, giữ bàn ghế gọn sạch.",
                "The food area has mi Quang, fish cake noodle soup, pork rice-paper rolls and sweet soups. Choose busy stalls with quick turnover and order small portions to try many dishes. Remember to clean your hands before eating and keep the tables and chairs tidy."),
            heading("Thanh toán & lưu ý an toàn", "Payment & safety notes"),
            paragraph("Đa phần nhận tiền mặt; nhiều quầy hỗ trợ chuyển khoản/QR. Giữ ví – điện thoại ở ngăn trước, đeo balo ra phía trước khi đông khách. Kiểm hàng và hóa đơn trước khi rời quầy; với đồ khô, xin tờ giấy ghi rõ loại hàng, hạn dùng để tiện theo dõi.",
                "Most stalls take cash; many also accept bank transfer/QR. Keep your wallet and phone in a front pocket and wear your backpack on the front when it is crowded. Check the goods and receipt before leaving the stall; for dried goods, ask for a note specifying the item type and expiry for easy tracking."),
            imageBlock(img("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2023/3/15/1158152/Nang-Cap-Cho-Han-7.jpg", "Lối vào chợ Hàn", "Han Market entrance", false, 0)),
            gallery(List.of(
                img("https://danangfantasticity.com/wp-content/uploads/2025/04/trinh-cho-han-thuong-hieu-ca-phe-da-nang-giua-long-cho-truyen-thong-06.jpg", "Cổng chợ Hàn", "Han Market gate", false, 0),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/21/22/39/w1230/cho1753090781_5709.jpg", "Quầy đặc sản khô", "Dried specialty stall", false, 1),
                img("https://statics.vinpearl.com/cho-han-da-nang-16_1637569200.jpg", "Không gian mua sắm", "Shopping space", false, 2)
            )),
            quote("“Thích đặc sản Đà Nẵng, ghé chợ Hàn.”",
                "\"Craving Da Nang specialties? Drop by Han Market.\""),
            divider(),
            mapBlock(new double[]{108.2236, 16.0682})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Chợ & Mua sắm", "Market & shopping")).slug("cho-va-mua-sam").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Đặc sản Đà Nẵng", "Da Nang specialties")).slug("dac-san-da-nang").type(TagType.CUISINE).build(),
            PlaceDoc.TagMini.builder().name(of("Chợ địa phương", "Local market")).slug("cho-dia-phuong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  /** 7) Asia Park – Sun Wheel */
  private static PlaceDoc poiAsiaPark() {
    String slug = "asia-park-sun-wheel";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Asia Park – Sun Wheel", "Asia Park - Sun Wheel"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Công viên giải trí với vòng quay Sun Wheel nổi bật về đêm.", "An amusement park with the Sun Wheel Ferris wheel that shines at night."))
        .description(of("Asia Park có các trò chơi giải trí, khu ẩm thực phong cách châu Á. "
            + "Sun Wheel là điểm check-in rực rỡ khi lên đèn.",
            "Asia Park offers amusement rides and an Asian-style food area. "
            + "The Sun Wheel is a dazzling check-in spot once the lights come on."))
        .addressLine(of("1 Phan Đăng Lưu, Hòa Cường Bắc, Hải Châu, Đà Nẵng", "1 Phan Dang Luu, Hoa Cuong Bac, Hai Chau, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2227, 16.0470})
        .vibes(List.of("vibrant", "lively"))
        .atmosphere(of("urban-entertainment", "urban-entertainment"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("couple", "family", "group"))
        .visitPurpose(List.of("explore", "relax"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.EVENING, BestVisitTime.NIGHT))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(150)
        .photographyScore(80)
        .romanticScore(60)
        .localExperienceScore(50)
        .images(withCover(
            img("https://asiapark.sunworld.vn/wp-content/uploads/2018/07/DSC_7105.jpg", "Sun Wheel – Asia Park", "Sun Wheel - Asia Park", true, 0),
            img("https://www.vecaptreobanahills.com/wp-content/uploads/2023/09/cong-vien-chau-a.png", "Công viên về đêm", "The park at night", false, 1),
            img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/16/20/19/w1230/con1752650354_8476.jpeg", "Khu trò chơi", "Ride area", false, 2),
            img("http://www.dulichhoanggia.com.vn/Uploads/652.jpg", "Không gian check-in", "Check-in space", false, 3),
            img("https://www.kkday.com/vi/blog/wp-content/uploads/Am-thuc-hap-dan-tai-Nha-hang-T-3135-3587-1608799898.jpg", "Ẩm thực châu Á", "Asian cuisine", false, 4),
            img("https://duan-sungroup.com/wp-content/uploads/2022/11/cong-vien-chau-a-asia-park-duoc-cai-tao-vao-nam-2020.jpg", "Khuôn viên rộng", "Spacious grounds", false, 5)
        ))
        .content(List.of(
            heading("Asia Park – sắc màu giải trí giữa lòng thành phố", "Asia Park - colorful entertainment in the heart of the city"),
            quote("""
                    Đêm nghiêng vòng ánh sáng,
                    phố hát dưới chân đu;
                    gió chở tiếng reo vỡ,
                    ngôi sao đậu trên khu.
                """,
                """
                    Night tilts a wheel of light,
                    the city sings beneath the rides;
                    the wind carries bursts of cheer,
                    a star alights over the park.
                """),
            paragraph("Asia Park là tổ hợp vui chơi – ẩm thực mang phong cách châu Á ngay trung tâm Đà Nẵng; khi phố lên đèn, Sun Wheel bừng sáng thành điểm nhấn nhìn thấy từ xa. Không gian rộng, nhiều khu chủ đề, phù hợp đi nhóm bạn hoặc gia đình; nếu muốn chụp hình với vòng quay, hãy đến sớm trước giờ cao điểm để có khung hình ít người.",
                "Asia Park is an Asian-style entertainment and dining complex right in central Da Nang; when the city lights up, the Sun Wheel glows as a highlight visible from afar. The space is large with many themed areas, suitable for groups of friends or families; if you want photos with the wheel, come early before peak hours for a less crowded frame."),
            heading("Sun Wheel – thời điểm & mẹo trải nghiệm", "Sun Wheel - timing & experience tips"),
            paragraph("Khung giờ lý tưởng: 18:30–20:00 khi bầu trời còn hơi xanh, đèn vừa bật cho ảnh có độ chuyển sắc; sau 20:00 thường đông hơn. Mỗi vòng quay khoảng vài vòng chậm, đủ ngắm toàn cảnh sông Hàn – trung tâm thành phố. Nếu sợ độ cao, chọn cabin gần cửa để cảm thấy yên tâm hơn; hạn chế đứng dồn về một phía khi cabin đang lên cao.",
                "The ideal window is 18:30-20:00 when the sky is still slightly blue and the lights have just come on for a color-shifting shot; after 20:00 it is usually busier. Each ride is a few slow rotations, enough to take in the panorama of the Han River and city center. If you fear heights, choose a cabin near the door to feel more at ease; avoid crowding to one side as the cabin rises."),
            imageBlock(img("https://www.kkday.com/vi/blog/wp-content/uploads/kinh-nghiem-di-vong-quay-sunwheel-3.jpg", "Sun Wheel tỏa sáng", "The Sun Wheel shining", false, 0)),
            heading("Vé vào cửa & xếp hàng", "Admission tickets & queuing"),
            paragraph("Quầy vé và cổng soát đặt ngay mặt tiền công viên; có nhiều loại vé theo khu trò chơi. Cuối tuần, hàng đợi Sun Wheel có thể kéo dài: nên vào cổng sớm, ưu tiên đi vòng quay trước rồi mới dạo khu trò chơi. Giữ vé/QR đến khi ra khu, vì có thể cần quét lại ở vài điểm.",
                "The ticket booth and check gate are at the park's front; there are several ticket types by ride area. On weekends the Sun Wheel queue can be long: enter early and ride the wheel first before wandering the ride areas. Keep your ticket/QR until you leave, as you may need to scan again at a few points."),
            heading("Khu trò chơi & mức độ phù hợp", "Ride areas & suitability"),
            paragraph("Khu ngoài trời có các trò vận động – cảm giác mạnh, xen kẽ khu trẻ em. Hãy đọc bảng chiều cao tối thiểu và khuyến cáo sức khỏe trước khi tham gia; người say xe/nôn nao nên bắt đầu bằng trò nhẹ (vòng quay mini, tàu lượn cỡ nhỏ) rồi tăng dần cường độ. Tránh chơi sau khi ăn no.",
                "The outdoor area has active and thrill rides interspersed with a kids' zone. Read the minimum-height boards and health advisories before joining; those prone to motion sickness should start with gentle rides (the mini wheel, a small roller coaster) and gradually increase intensity. Avoid riding right after a heavy meal."),
            heading("Ẩm thực phong cách châu Á", "Asian-style cuisine"),
            paragraph("Khu ẩm thực quy tụ món quen miệng như mì, cơm, xiên nướng, trà sữa; chia món theo suất nhỏ để “ăn dạo” nhiều quầy. Giữ vệ sinh chung: bỏ rác đúng nơi, lau bàn sau khi dùng; nếu đi nhóm, có thể cắt cử người giữ chỗ – người khác xếp hàng mua để tiết kiệm thời gian.",
                "The food area gathers familiar dishes such as noodles, rice, grilled skewers and milk tea; split dishes into small portions to 'graze' across many stalls. Keep things clean: dispose of trash properly and wipe the table after use; if in a group, assign someone to hold seats while others queue to save time."),
            heading("Góc chụp ảnh & gợi ý khung hình", "Photo spots & frame suggestions"),
            paragraph("Khung cận: dùng ống kính góc rộng/điện thoại đứng sát chân vòng quay để lấy hiệu ứng “tỏa nan”. Khung xa: đứng bên bờ sông phía đường 2/9 để gom cả Sun Wheel và dải phản chiếu nước. Giờ vàng (17:15–17:45) cho nền trời ấm; sau 19:00, giảm tốc 1/4–1s (có tripod) để bắt vệt đèn quay. Tránh bật flash hướng thẳng vào người lạ.",
                "Close-up: use a wide-angle lens/phone standing at the foot of the wheel for a 'spoke-burst' effect. Wide shot: stand on the riverbank by 2/9 street to capture both the Sun Wheel and its reflection on the water. The golden hour (17:15-17:45) gives a warm sky; after 19:00, slow the shutter to 1/4-1s (with a tripod) to catch the spinning light trails. Avoid flashing directly at strangers."),
            gallery(List.of(
                img("https://asiapark.sunworld.vn/wp-content/uploads/2018/07/DSC_7105.jpg", "Sun Wheel tỏa sáng", "The Sun Wheel shining", false, 0),
                img("https://www.vecaptreobanahills.com/wp-content/uploads/2023/09/cong-vien-chau-a.png", "Công viên về đêm", "The park at night", false, 1),
                img("https://fvgtravel.com.vn/uploads/up/root/editor/2025/07/16/20/19/w1230/con1752650354_8476.jpeg", "Khu trò chơi sôi động", "The lively ride area", false, 2)
            )),
            heading("An toàn & lưu ý", "Safety & notes"),
            paragraph("Tuân thủ hướng dẫn của nhân viên tại từng trò, cất đồ rơi rớt (điện thoại, ví, kính) trước khi lên máy; trẻ nhỏ luôn đi kèm người lớn. Trời mưa to/gió lớn có thể tạm dừng vận hành một số trò – linh hoạt sắp xếp lộ trình. Uống đủ nước, mang áo khoác mỏng khi ngồi cabin có điều hòa.",
                "Follow the staff's instructions at each ride and stow loose items (phone, wallet, glasses) before boarding; small children must always be accompanied by an adult. Heavy rain or strong wind may temporarily halt some rides - keep your route flexible. Stay hydrated and bring a light jacket when sitting in an air-conditioned cabin."),
            heading("Lộ trình gợi ý buổi tối (≈2–3 giờ)", "Suggested evening route (about 2-3 hours)"),
            paragraph("18:30 vào cổng → 18:45–19:15 đi Sun Wheel ngắm toàn cảnh → 19:20–20:00 dạo khu trò chơi/ảnh đêm → 20:00–20:30 ăn nhẹ khu ẩm thực → 20:30 chụp thêm vài khung hình với vòng quay rồi ra về.",
                "18:30 enter the gate -> 18:45-19:15 ride the Sun Wheel for the panorama -> 19:20-20:00 wander the ride area/night photos -> 20:00-20:30 a snack at the food area -> 20:30 take a few more shots with the wheel, then head home."),
            quote("“Đêm Đà Nẵng rực rỡ nhất từ vòng quay Sun Wheel.”",
                "\"Da Nang's nights are at their most dazzling from the Sun Wheel.\""),
            divider(),
            mapBlock(new double[]{108.2227, 16.0470})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Giải trí", "Entertainment")).slug("giai-tri").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Vòng quay Sun Wheel", "Sun Wheel Ferris wheel")).slug("vong-quay-sun-wheel").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đời sống về đêm", "Nightlife")).slug("doi-song-ve-dem").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.MODERATE)
        .build();
  }

  /** 8) Bảo tàng Điêu khắc Chăm */
  private static PlaceDoc poiBaoTangCham() {
    String slug = "bao-tang-dieu-khac-cham";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.POI)
        .name(of("Bảo tàng Điêu khắc Chăm", "Museum of Cham Sculpture"))
        .slug(slug)
        .parentSlug("da-nang")
        .ancestors(List.of("da-nang"))
        .childrenCount(0)
        .shortDescription(of("Bảo tàng Chăm lớn nhất thế giới, lưu trữ nhiều hiện vật quý.", "The world's largest Cham museum, holding many precious artifacts."))
        .description(of("Trưng bày hàng nghìn hiện vật văn hóa Chăm-pa với nhiều phong cách nghệ thuật khác nhau. "
            + "Không gian yên tĩnh, thích hợp khám phá lịch sử – nghệ thuật miền Trung.",
            "It displays thousands of Champa cultural artifacts across many artistic styles. "
            + "The quiet space is ideal for exploring the history and art of central Vietnam."))
        .addressLine(of("02 2 Tháng 9, Hải Châu, Đà Nẵng", "02 2 Thang 9, Hai Chau, Da Nang"))
        .countryCode("VN").provinceName(of("Đà Nẵng", "Da Nang"))
        .location(new double[]{108.2208, 16.0617})
        .vibes(List.of("educational", "cultural"))
        .atmosphere(of("museum-indoor", "museum-indoor"))
        .travelStyle(List.of("casual", "exploration"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("culture"))
        .crowdLevel(CrowdLevel.LOW)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.INDOOR)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(90)
        .photographyScore(70)
        .romanticScore(40)
        .localExperienceScore(85)
        .images(withCover(
            img("https://www.bonboncar.vn/blog/content/images/size/w2000/format/webp/2025/07/A-nh-chu-p-Ma-n-hi-nh-2025-07-15-lu-c-10.09.38.png", "Mặt tiền bảo tàng Chăm", "Cham Museum facade", true, 0),
            img("https://danangfantasticity.com/wp-content/uploads/2020/07/phong-my-son-bao-tang-dieu-khac-cham-danang-fantasticity-com.jpg", "Không gian trưng bày", "Exhibition space", false, 1),
            img("https://digiticket.vn/blog/wp-content/uploads/2021/05/bao-tang-cham-1.jpg", "Tác phẩm điêu khắc", "Sculpture works", false, 2),
            img("https://digiticket.vn/blog/wp-content/uploads/2021/05/phong-dong-duong-bao-tang-dieu-khac-cham-danang-fantasticity-com-03.jpg", "Hiện vật Chăm-pa", "Champa artifacts", false, 3),
            img("https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/11_2021/du-lich-da-nang-susu.na29.jpg", "Hành lang bảo tàng", "Museum corridor", false, 4),
            img("https://danangfantasticity.com/wp-content/uploads/2020/07/tong-quan-bao-tang-dieu-khac-cham-da-nang-03.jpg", "Khách tham quan", "Visitors", false, 5)
        ))
        .content(List.of(
            heading("Bảo tàng Điêu khắc Chăm – kho tàng nghệ thuật Champa", "Museum of Cham Sculpture - a treasury of Champa art"),
            quote("""
                    Gạch nung ôm hơi nắng,
                    sa thạch kể nghìn năm;
                    bước chậm nghe tay đá
                    hóa tiếng nói Chiêm Thành.
                """,
                """
                    Fired brick holds the warmth of sun,
                    sandstone tells a thousand years;
                    walk slowly and hear the stone hands
                    become the voice of Champa.
                """),
            paragraph("Nằm ở vị trí thuận tiện gần trung tâm, bảo tàng quy tụ bộ sưu tập điêu khắc Chăm-pa phong phú với nhiều phong cách nghệ thuật khác nhau. Không gian trưng bày tĩnh lặng, ánh sáng ấm vừa đủ để đọc chú thích, phù hợp cho hành trình chậm rãi tìm hiểu lịch sử – mỹ thuật miền Trung.",
                "Conveniently located near the center, the museum gathers a rich collection of Champa sculpture across many artistic styles. The exhibition space is quiet, with warm light just enough to read the captions, suiting a slow journey through the history and fine arts of central Vietnam."),
            heading("Cách tham quan gợi ý (60–90 phút)", "Suggested visit plan (60-90 minutes)"),
            paragraph("Bắt đầu ở khu tổng quan để nắm mốc thời gian, sau đó đi theo các phòng theo vùng/phong cách: Mỹ Sơn – Trà Kiệu – Đồng Dương – Tháp Mẫm. Ở mỗi phòng, đọc kỹ bảng chú thích về chất liệu (sa thạch, gạch nung), niên đại và công năng của tượng/ phù điêu. Thuê thuyết minh hoặc audio guide sẽ giúp kết nối các mạch chuyện và biểu tượng tôn giáo – đời sống.",
                "Start at the overview area to grasp the timeline, then move through the rooms by region/style: My Son - Tra Kieu - Dong Duong - Thap Mam. In each room, read the captions carefully on material (sandstone, fired brick), dating and the function of the statues/reliefs. Hiring a guide or audio guide helps connect the storylines and the religious and everyday symbols."),
            heading("Những hiện vật tiêu biểu nên dừng lâu", "Highlight artifacts worth lingering over"),
            paragraph("Tượng thần Shiva – bò Nandin, Linga–Yoni, Apsara múa, Garuda, các phù điêu trưng bày cảnh lễ hội và thần thoại… Hãy quan sát kỹ đường chạm, nếp vải, hoa văn lặp lại theo nhịp, và cách nghệ nhân xử lý khối rỗng–đặc để tạo chuyển động. So sánh giữa các phong cách (như Mỹ Sơn – mềm mại, Đồng Dương – mạnh mẽ khúc chiết) để thấy tiến hóa thẩm mỹ qua thế kỷ.",
                "Statues of the god Shiva and the bull Nandin, Linga-Yoni, dancing Apsaras, Garuda, and reliefs depicting festival and mythological scenes... Observe closely the carving lines, fabric folds, rhythmically repeating patterns and how artisans handled solid and hollow volumes to create movement. Compare the styles (such as My Son - graceful, Dong Duong - powerful and angular) to see the aesthetic evolution across the centuries."),
            heading("Mẹo chụp ảnh & ứng xử trong không gian trưng bày", "Photo tips & etiquette in the exhibition space"),
            paragraph("Ánh sáng trong nhà mang tông vàng; đứng chéo 45° so với nguồn sáng để khối nổi rõ, tránh bóng đổ mạnh. Tắt flash, không chạm vào hiện vật dù chỉ “chạm nhẹ”; đi lại nhỏ tiếng để giữ không gian tôn trọng; balo nên đeo trước ở khu đông để tránh quệt vào bệ tượng. Nếu đi cùng trẻ nhỏ, giới thiệu ngắn về câu chuyện trên phù điêu để bé hứng thú và tránh sờ vào hiện vật.",
                "The indoor light has a golden tone; stand at 45 degrees to the light source to make volumes stand out and avoid harsh shadows. Turn off the flash and do not touch the artifacts even with a 'light touch'; move quietly to keep the space respectful; wear your backpack on the front in crowded areas to avoid brushing the statue bases. If with small children, give a brief introduction to the story on the relief to keep them interested and away from touching the artifacts."),
            imageBlock(img("https://tourbanahills.vn/wp-content/uploads/2023/02/bao-tang-dieu-khac-cham5.jpg.jpg", "Lối vào bảo tàng", "Museum entrance", false, 0)),
            heading("Tiện ích – dịch vụ", "Amenities - services"),
            paragraph("Quầy vé – thông tin đặt ngay sảnh; có dịch vụ gửi đồ, thuyết minh viên/ audio guide tại chỗ vào một số khung giờ. Sách ảnh – postcard tại cửa hàng lưu niệm giúp bạn mang về tư liệu học tập. Trang phục gọn gàng, giày đế êm sẽ giúp đi lại nhiều phòng đỡ mỏi.",
                "The ticket and information desk is in the lobby; there are cloakroom services and on-site guides/audio guides at certain hours. Photo books and postcards at the souvenir shop let you take home study material. Tidy clothing and cushioned shoes make walking through many rooms less tiring."),
            gallery(List.of(
                img("https://www.bonboncar.vn/blog/content/images/size/w2000/format/webp/2025/07/A-nh-chu-p-Ma-n-hi-nh-2025-07-15-lu-c-10.09.38.png", "Mặt tiền bảo tàng", "Museum facade", false, 0),
                img("https://danangfantasticity.com/wp-content/uploads/2020/07/phong-my-son-bao-tang-dieu-khac-cham-danang-fantasticity-com.jpg", "Không gian trưng bày", "Exhibition space", false, 1),
                img("https://digiticket.vn/blog/wp-content/uploads/2021/05/bao-tang-cham-1.jpg", "Tác phẩm điêu khắc Champa", "Champa sculpture works", false, 2)
            )),
            heading("Bảo tồn & lưu ý", "Conservation & notes"),
            paragraph("Tôn trọng ranh giới bệ trưng bày, không tựa – kê đồ lên hiện vật; không vẽ bậy hay dán sticker; rác mang đến hãy mang đi. Một số khu có điều hòa mát sâu – nên mang áo khoác mỏng nếu nhạy nhiệt.",
                "Respect the boundaries of the display bases, do not lean on or place items on the artifacts; no graffiti or stickers; pack out any trash you bring in. Some areas are strongly air-conditioned - bring a light jacket if you are sensitive to cold."),
            quote("“Kho tàng Champa giữa lòng thành phố trẻ.”",
                "\"A treasury of Champa in the heart of a young city.\""),
            divider(),
            mapBlock(new double[]{108.2208, 16.0617})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Bảo tàng", "Museum")).slug("bao-tang").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historical site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Bảo tàng Chăm", "Cham Museum")).slug("bao-tang-cham").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Giáo dục lịch sử", "History education")).slug("giao-duc-lich-su").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tham quan có hướng dẫn", "Guided tour")).slug("tham-quan-co-huong-dan").type(TagType.SERVICE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }
}
