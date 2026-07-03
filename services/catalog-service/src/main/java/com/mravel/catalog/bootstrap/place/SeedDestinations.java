package com.mravel.catalog.bootstrap.place;

import java.util.ArrayList;
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
public class SeedDestinations {

  @Bean
  CommandLineRunner seedDestinationsRunner(PlaceDocRepository repo) {
    return args -> {
      System.out.println(">>> [SeedDestinations] start");

      List<PlaceDoc> seeds = new ArrayList<>();
      seeds.add(daNang());
      seeds.add(hoiAn());
      seeds.add(haNoi());
      seeds.add(hoChiMinh());
      seeds.add(sapa());
      seeds.add(nhaTrang());

      int inserted = 0;
      for (PlaceDoc p : seeds) {
        if (!repo.existsBySlug(p.getSlug())) {
          repo.save(p);
          inserted++;
          System.out.println(">>> [SeedDestinations] inserted: " + p.getSlug());
        } else {
          System.out.println(">>> [SeedDestinations] skip (exists): " + p.getSlug());
        }
      }
      System.out.println(">>> [SeedDestinations] done, inserted = " + inserted);
    };
  }

  // ===================== DESTINATIONS =====================

  private static PlaceDoc daNang() {
    String slug = "da-nang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .venueType(null)
        .parentId(null)
        .parentSlug(null)
        .ancestors(List.of())
        .childrenCount(0)
        .name(of("Đà Nẵng", "Da Nang"))
        .slug(slug)
        .shortDescription(of("Thành phố đáng sống bên sông Hàn, trung tâm của miền Trung Việt Nam.", "A livable city on the Han River, the hub of Central Vietnam."))
        .description(of("Đà Nẵng nổi tiếng với bãi biển Mỹ Khê, Bà Nà Hills, Ngũ Hành Sơn và những cây cầu biểu tượng. " +
            "Ẩm thực phong phú với mì Quảng, bún chả cá, hải sản tươi sống. Khí hậu ôn hòa, giao thông thuận tiện.",
            "Da Nang is famous for My Khe Beach, Ba Na Hills, the Marble Mountains and its iconic bridges. " +
            "It boasts rich cuisine with Quang noodles, fish-cake noodle soup and fresh seafood. The climate is mild and getting around is easy."))
        .phone(null).email(null).website(null)
        .addressLine(of("TP. Đà Nẵng, Việt Nam", "Da Nang City, Vietnam"))
        .countryCode("VN")
        .provinceCode(null)
        .districtCode(null)
        .wardCode(null)
        .provinceName(of("Đà Nẵng", "Da Nang"))
        .districtName(null)
        .wardName(null)
        .location(new double[]{108.2208, 16.0678}) // [lon, lat]
        .priceLevel(null)
        .minPrice(null).maxPrice(null)
        .avgRating(0.0).reviewsCount(0)
        .vibes(List.of("relaxing", "vibrant", "diverse"))
        .atmosphere(of("coastal-urban", "coastal-urban"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "culture", "food", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(4320)
        .photographyScore(85)
        .romanticScore(70)
        .localExperienceScore(75)
        .images(withCover(
            img("https://cdn-media.sforum.vn/storage/app/media/ctvseo_MH/%E1%BA%A3nh%20%C4%91%E1%BA%B9p%20%C4%91%C3%A0%20n%E1%BA%B5ng/anh-dep-da-nang-thumb.jpg", "Toàn cảnh thành phố", "City panorama", true, 0),
            img("https://kyuchoian.com/userfiles/image/du-ch-da-nang/2023/cau-rong/tren-cao.jpg", "Cầu Rồng nhìn từ sông Hàn", "The Dragon Bridge seen from the Han River", false, 1),
            img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463042242_bien-my-khe-2.jpg", "Bãi biển Mỹ Khê buổi sớm", "My Khe Beach in the early morning", false, 2),
            img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Bà Nà Hills – Cầu Vàng", "Ba Na Hills - Golden Bridge", false, 3),
            img("https://www.huongnghiepaau.com/wp-content/uploads/2020/10/mi-quang-mien-trung.jpg", "Ẩm thực mì Quảng", "Quang noodles cuisine", false, 4),
            img("https://static.vinwonders.com/2022/03/ngu-hanh-son-thumb.jpg", "Ngũ Hành Sơn", "The Marble Mountains", false, 5)
        ))
        .openHours(List.of())
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Điểm đến biển", "Beach destination")).slug("diem-den-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Thành phố", "City")).slug("thanh-pho").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Cầu Rồng – Cầu Vàng", "Dragon Bridge - Golden Bridge")).slug("cau-rong-cau-vang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Bãi biển Mỹ Khê", "My Khe Beach")).slug("bai-bien-my-khe").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.FREE)
        .content(List.of(
            heading("Đà Nẵng – điểm hẹn của biển và những cây cầu", "Da Nang - a rendezvous of the sea and bridges"),
            quote("""
                Gió mằn mặn gọi tên bờ cát,
                đêm sông Hàn thắp sáng bóng cây cầu;
                người lữ khách đến rồi thương, đi rồi nhớ.
                """,
                """
                The salty wind calls out to the sandy shore,
                the Han River night lights up the bridges' silhouettes;
                travelers come to love it, and leave to miss it.
                """),
            paragraph("Nằm giữa Huế và Hội An, Đà Nẵng là cửa ngõ của dải đất miền Trung với nhịp sống hiện đại nhưng vẫn giữ được nét hiền hòa của biển và sông. Thành phố nổi tiếng với hệ thống cầu bắc qua sông Hàn, bãi biển Mỹ Khê cát trắng và cụm danh thắng Ngũ Hành Sơn. Nhiều khách gọi Đà Nẵng là “thành phố đáng sống” không chỉ vì hạ tầng sạch, mà còn bởi nhịp sống vừa phải, con người thân thiện và ẩm thực phong phú, hợp túi tiền cho cả du lịch tự túc lẫn gia đình.",
                "Lying between Hue and Hoi An, Da Nang is the gateway to Central Vietnam, with a modern pace of life that still keeps the gentle charm of the sea and the river. The city is famous for its system of bridges spanning the Han River, the white sands of My Khe Beach and the Marble Mountains scenic cluster. Many visitors call Da Nang a “livable city” not only for its clean infrastructure, but also for its easy pace, friendly people and rich, affordable cuisine suited to both independent and family travel."),
            paragraph("Nhịp sống ở Đà Nẵng dễ chịu: sáng sớm xuống biển tắm, trưa tránh nắng trong các quán café mát rượi, chiều muộn dạo sông Hàn đón gió và tối thưởng thức hải sản. Các tuyến đường thẳng, rộng, ít kẹt xe giúp việc di chuyển thuận tiện, ngay cả vào mùa cao điểm. Từ đây, bạn cũng dễ dàng kết nối “tam giác di sản” Huế – Đà Nẵng – Hội An chỉ trong vài ngày.",
                "Life in Da Nang is pleasant: an early-morning swim at the beach, a midday escape from the sun in cool cafes, a late-afternoon stroll along the Han River to catch the breeze, and seafood in the evening. The straight, wide roads with little traffic make getting around easy, even in peak season. From here you can also easily connect the “heritage triangle” of Hue - Da Nang - Hoi An in just a few days."),
            imageBlock(img("https://www.vietnamairlines.com/~/media/SEO-images/2025%20SEO/Traffic%20TV/cau-vang-da-nang/cau-vang-da-nang-thumb.jpg", "Cầu Vàng – biểu tượng du lịch Đà Nẵng", "Golden Bridge - an icon of Da Nang tourism", false, 0)),
            paragraph("Về khí hậu, Đà Nẵng có hai mùa rõ rệt: mùa khô (khoảng tháng 3–8) và mùa mưa (tháng 9–12). Mùa khô là thời điểm lý tưởng cho các hoạt động biển, lặn ngắm san hô và dã ngoại. Mùa mưa mát mẻ hơn, phù hợp cho trải nghiệm ẩm thực, café, bảo tàng Chăm hay những ngày “chạy trốn nắng” ở Bà Nà Hills. Nếu đi gia đình, 3–4 ngày là vừa đủ để cảm nhận nhịp điệu thành phố.",
                "As for the climate, Da Nang has two distinct seasons: the dry season (around March-August) and the rainy season (September-December). The dry season is ideal for beach activities, coral snorkeling and picnics. The rainy season is cooler, suited to enjoying cuisine, cafes, the Cham Museum or days “escaping the sun” at Ba Na Hills. For a family trip, 3-4 days is just enough to feel the rhythm of the city."),
            paragraph("Dù đi mùa nào, bạn cũng nên theo dõi dự báo thời tiết cho những hoạt động ngoài trời. Đặc biệt, mùa mưa đôi khi có áp thấp hoặc bão, lịch trình nên linh hoạt và luôn có phương án dự phòng: thay bãi biển bằng bảo tàng, thay leo núi bằng trải nghiệm ẩm thực. Kem chống nắng, mũ nón, áo khoác mỏng và dép đi biển là “bộ tứ” không nên thiếu trong balo.",
                "Whatever season you go, keep an eye on the weather forecast for outdoor activities. In particular, the rainy season sometimes brings tropical depressions or storms, so your itinerary should be flexible and always have a backup plan: swap the beach for a museum, swap mountain climbing for a food experience. Sunscreen, a hat, a light jacket and beach sandals are the “essential four” you should not leave out of your backpack."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/kinh-nghiem-check-in-cau-rong-da-nang-1-trong-20-cay-cau-an-tuong-nhat-the-gioi-01-1636442674.jpeg", "Cầu Rồng phun lửa", "The Dragon Bridge breathing fire", false, 0),
                img("https://static.vinwonders.com/2022/04/bai-bien-my-khe-da-nang-2.jpg", "Biển Mỹ Khê", "My Khe Beach", false, 0),
                img("https://mia.vn/media/uploads/blog-du-lich/da-den-da-nang-thi-phai-oanh-tac-5-ngon-nui-o-ngu-hanh-son-1636215280.jpg", "Ngũ Hành Sơn", "The Marble Mountains", false, 0)
            )),
            heading("Di chuyển & ở đâu?", "Getting around & where to stay"),
            paragraph("Sân bay quốc tế Đà Nẵng nằm ngay trung tâm, cách bờ biển chỉ 10–15 phút đi xe. Từ sân bay, bạn có thể gọi taxi, xe công nghệ hoặc thuê xe máy để chủ động. Khu vực dọc đường Võ Nguyên Giáp – Trường Sa tập trung nhiều khách sạn, resort sát biển, trong khi khu trung tâm quanh sông Hàn tiện cho việc đi ăn uống, café và dạo đêm. Mùa cao điểm, nên đặt phòng trước 2–3 tuần để có mức giá dễ chịu và view đẹp.",
                "Da Nang International Airport sits right in the center, just a 10-15 minute drive from the coast. From the airport you can take a taxi, a ride-hailing car or rent a motorbike for flexibility. The area along Vo Nguyen Giap - Truong Sa streets is dotted with beachfront hotels and resorts, while the downtown around the Han River is convenient for dining, cafes and night strolls. In peak season, book your room 2-3 weeks ahead for a reasonable price and a good view."),
            paragraph("Với ngân sách vừa phải, homestay và boutique hotel là lựa chọn hay: phòng sạch, bữa sáng cơ bản, không gian ấm cúng, nhiều nơi còn cho mượn xe đạp. Nếu đi nhóm bạn, thuê căn hộ (apartment) giúp tối ưu chi phí, có bếp để tự chế biến hải sản mua ở chợ. Gia đình có trẻ nhỏ sẽ thích resort có hồ bơi, bãi biển riêng, câu lạc bộ trẻ em và dịch vụ đưa đón bài bản.",
                "On a modest budget, homestays and boutique hotels are great choices: clean rooms, a basic breakfast, a cozy atmosphere, and many even lend bicycles. For a group of friends, renting an apartment optimizes costs and provides a kitchen to cook seafood bought at the market. Families with small children will love resorts with a pool, a private beach, a kids' club and well-organized shuttle services."),
            paragraph("Di chuyển trong thành phố tương đối dễ: đường rộng, biển hiệu rõ ràng. Nếu tự lái xe máy, nhớ đội mũ bảo hiểm, chạy đúng làn, giữ tốc độ vừa phải và quan sát kỹ ở các giao lộ gần cầu. Buổi tối dọc bờ Đông có nhiều hàng quán, bãi đậu xe thuận tiện, thích hợp để dừng chân ngắm biển và nghe sóng.",
                "Getting around the city is relatively easy: wide roads and clear signage. If you ride a motorbike yourself, remember to wear a helmet, stay in your lane, keep a moderate speed and watch carefully at intersections near the bridges. In the evening along the eastern shore there are many eateries and convenient parking lots, perfect for stopping to watch the sea and listen to the waves."),
            heading("Ăn gì ở Đà Nẵng?", "What to eat in Da Nang?"),
            paragraph("Đến Đà Nẵng, đừng bỏ qua mì Quảng, bún chả cá, bánh tráng cuốn thịt heo hai đầu da và hải sản tươi theo mâm. Khu ven biển có nhiều quán bình dân, chọn hải sản theo ký rồi chế biến tại chỗ. Buổi tối đi chợ đêm, bạn có thể thử ốc hút, mít trộn, bánh tráng nướng – vừa rẻ vừa vui. Cà phê Đà Nẵng đa dạng: từ specialty đến quán vỉa hè đúng “vibe” miền Trung.",
                "In Da Nang, do not miss Quang noodles, fish-cake noodle soup, pork rice-paper rolls and platters of fresh seafood. The seaside area has many casual eateries where you pick seafood by the kilo and have it cooked on the spot. At the night market in the evening, you can try sucking snails, jackfruit salad and grilled rice paper - cheap and fun. Da Nang coffee is diverse, from specialty shops to sidewalk stalls with a true Central-Vietnam “vibe.”"),
            paragraph("Nếu thích trải nghiệm địa phương, hãy ghé các chợ như chợ Hàn để thấy nhịp buôn bán sôi động và mua đặc sản về làm quà: mực rim, tré, bánh khô mè, chả bò. Lúc gọi món hải sản, bạn nên hỏi trước về cách chế biến (nướng, hấp, xào me...) và khẩu vị cay – mặn phù hợp với nhóm, để bữa ăn tròn vị hơn.",
                "If you enjoy local experiences, drop by markets like Han Market to feel the lively trade and buy specialties as gifts: dried squid, tre (fermented pork roll), sesame brittle and beef sausage. When ordering seafood, ask in advance about the cooking method (grilled, steamed, stir-fried with tamarind...) and adjust the spicy-salty level to suit your group for a more complete meal."),
            imageBlock(img("https://www.huongnghiepaau.com/wp-content/uploads/2020/10/mi-quang-mien-trung.jpg", "Mì Quảng – món ăn trứ danh", "Quang noodles - a renowned dish", false, 0)),
            heading("Đi đâu chơi?", "Where to go?"),
            paragraph("Buổi sáng sớm, biển Mỹ Khê trong xanh và ít người, phù hợp tắm biển hoặc chạy bộ dọc bờ. Ngũ Hành Sơn chỉ cách trung tâm một đoạn ngắn, có động đá mát lạnh và làng đá Non Nước lâu đời. Buổi chiều, bạn có thể lên bán đảo Sơn Trà: ghé Linh Ứng, ngắm cảnh ở bãi Bụt, bãi Đá Đen và săn hoàng hôn từ những khúc cua ôm vịnh tuyệt đẹp.",
                "Early in the morning, My Khe Beach is clear and uncrowded, perfect for a swim or a run along the shore. The Marble Mountains are just a short way from the center, with cool stone caves and the old Non Nuoc stone-carving village. In the afternoon you can head up the Son Tra Peninsula: visit Linh Ung Pagoda, take in the views at Bai But and Bai Da Den, and chase the sunset from the gorgeous bends hugging the bay."),
            paragraph("Một ngày trọn vẹn ở Bà Nà Hills giúp đổi gió với khí hậu mát mẻ, cảnh quan kiểu Âu và các góc chụp hình nổi tiếng như Cầu Vàng. Tối về lại thành phố, dạo sông Hàn, thưởng thức show phun lửa cầu Rồng cuối tuần, sau đó ghé café hay bar rooftop ngắm phố đêm. Nếu còn thời gian, chạy xe thêm 30–40 phút là tới Hội An để “đổi vibe” cổ kính.",
                "A full day at Ba Na Hills offers a change of scene with cool weather, European-style scenery and famous photo spots like the Golden Bridge. Back in the city in the evening, stroll along the Han River, enjoy the weekend Dragon Bridge fire-breathing show, then drop by a cafe or rooftop bar to watch the city at night. If you have time, a 30-40 minute drive brings you to Hoi An for an ancient-town “change of vibe.”"),
            heading("Lịch trình gợi ý 3 ngày", "Suggested 3-day itinerary"),
            paragraph("Ngày 1: Dạo biển Mỹ Khê, tắm biển sáng sớm; chiều lên Ngũ Hành Sơn thăm động, làng đá Non Nước; tối dạo cầu Rồng – cầu Tình Yêu, ngắm sông Hàn và thưởng thức hải sản ven biển.",
                "Day 1: Stroll My Khe Beach and swim early in the morning; in the afternoon head to the Marble Mountains to visit the caves and Non Nuoc stone village; in the evening walk the Dragon Bridge and Love Bridge, admire the Han River and enjoy seaside seafood."),
            paragraph("Ngày 2: Đi Bà Nà Hills – Cầu Vàng cả ngày; chiều về thành phố nghỉ ngơi; tối café hoặc bar rooftop; nếu đi cùng trẻ nhỏ, có thể chọn khu vui chơi trong nhà để tiết kiệm sức cho các bé.",
                "Day 2: Spend the whole day at Ba Na Hills and the Golden Bridge; return to the city to rest in the afternoon; in the evening visit a cafe or rooftop bar; if traveling with small children, choose an indoor playground to save the kids' energy."),
            paragraph("Ngày 3: Chạy xe ra bán đảo Sơn Trà, dừng Linh Ứng, bãi Bụt – bãi Đá Đen; trưa về trung tâm ăn bún chả cá; chiều mua đặc sản ở chợ Hàn, ghé café rồi rời thành phố.",
                "Day 3: Drive out to the Son Tra Peninsula, stopping at Linh Ung Pagoda, Bai But and Bai Da Den; at noon return downtown for fish-cake noodle soup; in the afternoon buy specialties at Han Market, visit a cafe and then leave the city."),
            infoBox("Mẹo nhỏ: Cầu Rồng phun lửa cuối tuần (thường 21:00); đi sớm 15–20 phút để có chỗ đứng đẹp, tránh đứng quá gần đầu rồng nếu không muốn “rửa tội” bằng nước.",
                "Tip: The Dragon Bridge breathes fire on weekends (usually at 21:00); arrive 15-20 minutes early for a good spot, and avoid standing too close to the dragon's head unless you want a water “baptism.”"),
            divider(),
            heading("Văn hóa & con người", "Culture & people"),
            paragraph("Người Đà Nẵng hiền hòa, chân chất, nói năng nhỏ nhẹ và sẵn sàng chỉ đường khi bạn cần. Các hàng quán địa phương thường phục vụ nhanh, giá niêm yết rõ. Khi mua hải sản hoặc đặc sản, bạn nên hỏi giá trước và cân đo đủ, hóa đơn minh bạch để tránh hiểu lầm không đáng có.",
                "The people of Da Nang are gentle, down-to-earth, soft-spoken and ready to give directions when you need them. Local eateries usually serve quickly with clearly posted prices. When buying seafood or specialties, ask the price first and make sure the weighing is accurate, with a transparent bill to avoid needless misunderstandings."),
            paragraph("Ứng xử văn minh nơi công cộng được đề cao: xếp hàng, giữ vệ sinh bãi biển, không xả rác, không bấm còi inh ỏi khi lưu thông. Những điều nhỏ này góp phần giữ hình ảnh “thành phố đáng sống” và giúp trải nghiệm của bạn lẫn cộng đồng du khách khác trở nên dễ chịu hơn.",
                "Civilized public behavior is valued: queueing, keeping the beach clean, not littering, and not honking loudly in traffic. These small things help preserve the image of a “livable city” and make the experience more pleasant for you and for other travelers."),
            heading("Chi phí & ngân sách", "Costs & budget"),
            paragraph("Mức chi ở Đà Nẵng khá “dễ thở” so với nhiều điểm đến biển khác. Khách sạn tầm trung dao động hợp lý, bữa ăn với hải sản tùy theo loại bạn chọn, còn đồ ăn vặt và café rất phong phú. Nếu đi nhóm 3–4 người, việc chia sẻ chi phí thuê xe, căn hộ hay combo vé sẽ tiết kiệm đáng kể.",
                "Spending in Da Nang is fairly “easy on the wallet” compared with many other beach destinations. Mid-range hotels are reasonably priced, seafood meals vary by what you choose, and snacks and coffee are plentiful. For a group of 3-4 people, sharing the cost of vehicle rental, an apartment or ticket combos saves a considerable amount."),
            paragraph("Để tối ưu, bạn có thể đặt phòng sớm mùa cao điểm, chọn khung giờ ăn không quá “hot” và sử dụng các mã giảm giá dịch vụ vận chuyển/điểm tham quan khi có. Những khoản nhỏ tưởng chừng vụn vặt nhưng cộng lại giúp lịch trình “dễ thở” hơn nhiều.",
                "To optimize, book your room early in peak season, choose dining times that are not too “hot,” and use discount codes for transport services and attractions when available. These seemingly trivial small amounts add up to make your itinerary much “easier on the wallet.”"),
            heading("Thời điểm & lưu ý", "Timing & notes"),
            paragraph("Tháng 3–8 nắng đẹp, biển trong – phù hợp nhất cho trải nghiệm ngoài trời; tháng 9–12 có mưa, thỉnh thoảng bão; nếu đi mùa này nên dự phòng phương án trong nhà: bảo tàng Chăm, café, shopping hoặc “food tour”. Kem chống nắng, áo khoác mỏng và dép đi biển là vật bất ly thân.",
                "March-August has beautiful sun and clear seas - best for outdoor experiences; September-December brings rain and occasional storms; if you travel then, prepare indoor backups: the Cham Museum, cafes, shopping or a “food tour.” Sunscreen, a light jacket and beach sandals are must-have items."),
            paragraph("Khi thuê xe máy, bạn nhớ mang đủ giấy tờ, kiểm tra phanh – đèn – còi trước khi xuất phát. Ở các đoạn đường ven biển buổi tối, gió mạnh có thể khiến bạn “hụt tay lái”, nên đi chậm, giữ khoảng cách an toàn. Nếu đi Bà Nà hoặc Sơn Trà, theo dõi dự báo sương mù để tránh bị hạn chế tầm nhìn.",
                "When renting a motorbike, bring all your documents and check the brakes, lights and horn before setting off. On coastal stretches at night, strong wind can make you “lose your grip,” so ride slowly and keep a safe distance. If heading to Ba Na or Son Tra, watch the fog forecast to avoid limited visibility."),
            mapBlock(new double[]{108.2208, 16.0678}),
            quote("“Thành phố đáng sống” không chỉ là khẩu hiệu – mà là cảm giác bạn mang theo sau chuyến đi.",
                "“A livable city” is not just a slogan - it is the feeling you carry with you after the trip.")
        ))
        .build();
  }

  private static PlaceDoc hoiAn() {
    String slug = "hoi-an";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name(of("Hội An", "Hoi An"))
        .slug(slug)
        .shortDescription(of("Phố cổ di sản UNESCO, lung linh đèn lồng bên sông Hoài.", "A UNESCO heritage ancient town glowing with lanterns by the Hoai River."))
        .description(of("Hội An lưu giữ kiến trúc Á – Âu giao thoa, ẩm thực cao lầu – mì Quảng đặc trưng, " +
                     "biển An Bàng thanh bình và làng nghề truyền thống.",
                     "Hoi An preserves a blend of Asian and European architecture, signature cao lau and Quang noodle cuisine, " +
                     "the peaceful An Bang Beach and traditional craft villages."))
        .addressLine(of("TP. Hội An, Quảng Nam, Việt Nam", "Hoi An City, Quang Nam, Vietnam"))
        .countryCode("VN").provinceName(of("Quảng Nam", "Quang Nam"))
        .location(new double[]{108.3380, 15.8801})
        .vibes(List.of("cultural", "relaxing", "diverse"))
        .atmosphere(of("heritage-village", "heritage-village"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "culture", "food", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(2880)
        .photographyScore(95)
        .romanticScore(90)
        .localExperienceScore(85)
        .images(withCover(
            img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ Hội An về đêm", "Hoi An ancient town at night", true, 0),
            img("https://eholiday.vn/wp-content/uploads/2023/07/du-thuyen-song-hoai-hoi-an.jpg", "Sông Hoài và thuyền hoa đăng", "The Hoai River and lantern boats", false, 1),
            img("https://mia.vn/media/uploads/blog-du-lich/le-hoi-den-long-hoi-an-su-kien-dac-sac-duoc-mong-doi-nhat-o-pho-hoi-1620149223.jpg", "Đèn lồng Hội An", "Hoi An lanterns", false, 2),
            img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu", "The Japanese Covered Bridge", false, 3),
            img("https://mia.vn/media/uploads/blog-du-lich/cao-lau-3-1751684730.jpg", "Cao lầu", "Cao lau noodles", false, 4),
            img("https://hotelroyalhoian.vn/wp-content/uploads/2025/04/an-bang-doi-net-2-1.jpg", "Biển An Bàng", "An Bang Beach", false, 5)
        ))
        .content(List.of(
            heading("Hội An – di sản sống giữa lòng Quảng Nam", "Hoi An - a living heritage in the heart of Quang Nam"),
            quote("""
                Đêm thắp nghìn hoa lửa,
                sông Hoài soi bóng đèn lồng;
                bước chậm qua Chùa Cầu, nghe thời gian hát nhỏ.
                """,
                """
                Night kindles a thousand flowers of fire,
                the Hoai River mirrors the lanterns' glow;
                step slowly across the Bridge Pagoda, hear time softly sing.
                """),
            paragraph("Là thương cảng quốc tế sầm uất từ thế kỷ XVI–XVII, Hội An lưu giữ một tổng thể kiến trúc gỗ hiếm hoi còn nguyên vẹn ở Đông Nam Á. Những ngôi nhà mái ngói rêu phong, mặt tiền hẹp, sân trong thoáng đãng và những dãy cửa gỗ sơn màu trầm tạo nên nhịp điệu thị giác rất riêng. Khi phố lên đèn, sắc đỏ – vàng của đèn lồng soi bóng xuống sông Hoài, khiến cả không gian như chậm lại, mời gọi du khách đi bộ và “thở” cùng nhịp sống cổ phố.",
                "As a bustling international trading port from the 16th-17th centuries, Hoi An preserves a rare ensemble of wooden architecture still intact in Southeast Asia. The moss-covered tiled roofs, narrow facades, airy inner courtyards and rows of dark-painted wooden doors create a distinctive visual rhythm. When the town lights up, the red and gold of the lanterns reflect on the Hoai River, making the whole space seem to slow down, inviting visitors to walk and “breathe” with the rhythm of the old town."),
            paragraph("Trải nghiệm ở Hội An nên bắt đầu từ những bước chân thong thả: dạo qua các con hẻm nhỏ, ghé những hiệu may lâu đời, đứng lặng trước Chùa Cầu để cảm nhận sự giao thoa Nhật – Hoa – Việt. Bạn có thể mua vé tham quan khu phố cổ để vào các điểm di tích chính, mỗi nơi đều kể một câu chuyện về cộng đồng thương nhân, về tín ngưỡng và sinh hoạt mưu sinh đã tạo nên linh hồn của đô thị này.",
                "An experience in Hoi An should begin with leisurely steps: wandering the small alleys, dropping by long-established tailor shops, and standing quietly before the Japanese Covered Bridge to feel the Japanese-Chinese-Vietnamese blend. You can buy a ticket to visit the ancient town's main heritage sites, each telling a story of the merchant community, of beliefs and of the livelihoods that gave this town its soul."),
            imageBlock(img("https://divui.com/blog/wp-content/uploads/2018/08/Hoi-An.jpg", "Phố cổ lung linh", "The shimmering ancient town", false, 0)),
            heading("Lịch sử & kiến trúc", "History & architecture"),
            paragraph("Hội An từng là “điểm rơi” của tơ lụa, gốm sứ, hương liệu trên tuyến hải thương quốc tế. Kiến trúc nhà gỗ kết cấu vì kèo, tường gạch, mái âm dương giúp chống nóng, thoát nước và chịu gió bão tốt. Sự pha trộn tinh tế của họa tiết Trung Hoa, tỷ lệ Nhật Bản và kỹ thuật bản địa tạo nên một thẩm mỹ hài hòa, mộc mạc nhưng bền bỉ qua thời gian. Đi trong phố cổ, bạn sẽ thấy từng khung cửa, lan can, bao lam đều như một “bài thơ” chạm khắc.",
                "Hoi An was once a “drop point” for silk, ceramics and spices on the international maritime trade route. Its wooden houses, with truss frames, brick walls and yin-yang tiled roofs, resist heat, drain water and withstand storms well. The refined blend of Chinese motifs, Japanese proportions and indigenous techniques creates an aesthetic that is harmonious and rustic yet enduring over time. Walking through the old town, you will see every door frame, railing and carved panel like a sculpted “poem.”"),
            paragraph("Chùa Cầu là biểu tượng kiến trúc độc đáo: một cây cầu gỗ có mái che do thương nhân Nhật góp xây, gắn với truyền thuyết trấn yểm “cá chép hóa rồng”. Khu vực quanh đó tập trung nhiều hội quán của người Hoa – nơi cộng đồng từng sinh hoạt, cầu phúc, làm từ thiện; nay là điểm tham quan, trưng bày hiện vật và ghi chép lịch sử cộng đồng cư dân.",
                "The Japanese Covered Bridge is a unique architectural symbol: a roofed wooden bridge built with contributions from Japanese merchants, tied to the legend of warding off a monster as a “carp turning into a dragon.” The surrounding area is home to many Chinese assembly halls - where the community once gathered, prayed for blessings and did charity; today they are sightseeing spots displaying artifacts and recording the community's history."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/pho-co-hoi-an-ve-dem-6-1740477602.jpg", "Phố cổ lên đèn", "The ancient town lit up", false, 0),
                img("https://eholiday.vn/wp-content/uploads/2023/07/du-thuyen-song-hoai-hoi-an.jpg", "Thuyền hoa đăng sông Hoài", "Lantern boats on the Hoai River", false, 1),
                img("https://statics.vinpearl.com/chua-cau-hoi-an-121_1628047904.jpg", "Chùa Cầu biểu tượng", "The iconic Japanese Covered Bridge", false, 2)
            )),
            heading("Trải nghiệm dọc sông Hoài", "Experiences along the Hoai River"),
            paragraph("Chiều xuống, hãy men theo bờ sông Hoài để nghe tiếng chèo khua và lời rao bán đèn hoa đăng. Việc thả một chiếc đèn nhỏ mang ý nghĩa cầu an, đồng thời là khoảnh khắc tĩnh tâm hiếm có giữa chuyến đi. Nếu đi thuyền, bạn nên thỏa thuận giá trước, chọn mặc áo phao và hạn chế đứng lên khi thuyền đang di chuyển để đảm bảo an toàn. Bến thuyền cũng là nơi dễ săn ảnh ngược sáng đẹp mắt.",
                "As evening falls, follow the bank of the Hoai River to hear the splash of oars and the calls of floating-lantern vendors. Releasing a small lantern means praying for peace, and is also a rare moment of calm during the trip. If taking a boat, agree on the price beforehand, wear a life jacket and avoid standing up while the boat is moving to stay safe. The boat dock is also a great place to capture beautiful backlit photos."),
            paragraph("Hai bên bờ sông là chuỗi hàng quán, tiệm quà lưu niệm và cà phê ban công nhìn xuống dòng nước. Bạn có thể dừng chân thưởng thức một tách cà phê hoặc nước thảo mộc mát lạnh, quan sát dòng người qua lại, nghe tiếng gánh hàng, tiếng cười nói của những gia đình địa phương. Sự sống động ấy là “linh khí” khiến Hội An vừa trầm mặc vừa rất… đời thường.",
                "Both riverbanks are lined with eateries, souvenir shops and balcony cafes overlooking the water. You can stop to enjoy a cup of coffee or a cool herbal drink, watch the passersby, and listen to the calls of street vendors and the laughter of local families. That liveliness is the “spirit” that makes Hoi An both contemplative and yet very... everyday."),
            heading("Ăn gì ở Hội An?", "What to eat in Hoi An?"),
            paragraph("Cao lầu là món “bản sắc”: sợi mì khô dai, tóp mỡ giòn, xá xíu, rau sống Trà Quế và nước sốt mằn mặn, ăn kèm bánh đa. Bánh mì Hội An nổi tiếng bởi vỏ giòn ruộm và phần nhân phong phú; mì Quảng lại đậm đà với nước dùng sánh nhẹ, đậu phộng rang và bánh tráng nướng. Bên cạnh đó, cơm gà, hoành thánh, chè bắp Cẩm Nam hay tào phớ lá dứa đều đáng thử.",
                "Cao lau is the “signature” dish: chewy dried noodles, crispy pork cracklings, char siu pork, fresh Tra Que herbs and a savory sauce, served with crispy rice crackers. Hoi An banh mi is famous for its golden, crispy crust and rich fillings; Quang noodles are flavorful with a lightly thickened broth, roasted peanuts and grilled rice paper. Besides these, chicken rice, wontons, Cam Nam corn sweet soup and pandan tofu pudding are all worth trying."),
            paragraph("Buổi tối, các quầy hàng ven phố cổ bán nhiều món ăn vặt giá “mềm”. Bạn có thể gọi theo khẩu vị: ít cay, ít mặn hoặc thêm rau – đa số quán phục vụ linh hoạt. Khi mua đặc sản mang về như đèn lồng, bạn nên chọn loại khung chắc, vải thô bền màu; với đồ ăn khô, nhớ kiểm tra hạn sử dụng và cách bảo quản.",
                "In the evening, the stalls along the old town sell many snacks at “gentle” prices. You can order to your taste: less spicy, less salty or with extra herbs - most stalls are flexible. When buying specialties to take home such as lanterns, choose ones with a sturdy frame and durable, colorfast fabric; for dried foods, remember to check the expiry date and storage instructions."),
            heading("Lễ hội & đêm Hội An", "Festivals & Hoi An nights"),
            paragraph("Vào các đêm rằm, phố cổ tổ chức tắt đèn điện một phần, ưu tiên ánh sáng đèn lồng – không khí cổ xưa trở nên rõ nét hơn. Những hoạt động như hát bài chòi, kéo co, bịt mắt đập nồi… tái hiện sinh hoạt cộng đồng xưa. Đây là thời điểm rất đông khách, nên bạn hãy đến sớm, chọn điểm đứng thoáng, và giữ ý thức bảo vệ không gian chung khi chụp ảnh, quay phim.",
                "On full-moon nights, the old town partly switches off electric lights in favor of lantern light - making the ancient atmosphere even more vivid. Activities such as bai choi singing, tug-of-war and blindfolded pot-smashing re-enact old community life. This is a very crowded time, so arrive early, pick an open spot to stand, and stay mindful of the shared space when taking photos and videos."),
            paragraph("Ngoài đèn lồng, nhiều nhà cổ mở triển lãm ngắn hạn về nhiếp ảnh, sưu tầm đồ xưa hoặc trưng bày nghề thủ công như mộc, gốm, mây tre. Nếu thích không khí tĩnh lặng, hãy rẽ vào các hẻm nhỏ ít người, nơi ánh đèn vàng hắt lên tường vôi cũ và tiếng guốc gõ nền gạch vọng lại nghe rất… Hội An.",
                "Beyond the lanterns, many old houses host short-term exhibitions on photography, antique collections or craft displays such as woodwork, ceramics and rattan-bamboo. If you prefer a quiet atmosphere, turn into the small, less-crowded alleys, where the yellow light falls on old whitewashed walls and the echo of clogs on the brick floor sounds so very... Hoi An."),
            heading("Làng nghề & bãi biển gần phố cổ", "Craft villages & beaches near the old town"),
            paragraph("Rời phố, bạn có thể đạp xe tới làng rau Trà Quế để xem cách trồng rau hữu cơ và thử làm nông trong một buổi; ghé làng gốm Thanh Hà để quan sát nghệ nhân chuốt gốm; hoặc xuống bến thuyền Cẩm Thanh trải nghiệm thúng chai giữa rừng dừa nước – một bức tranh sông nước rất khác chỉ cách phố cổ vài cây số.",
                "Leaving the town, you can cycle to Tra Que vegetable village to see organic farming and try farm work for a session; visit Thanh Ha pottery village to watch artisans shape ceramics; or head to the Cam Thanh boat dock to experience a basket boat amid the water-coconut forest - a very different riverine scene just a few kilometers from the old town."),
            paragraph("Muốn “đổi gió”, biển An Bàng là lựa chọn yên bình: bãi cát thoải, sóng vừa phải, nhiều quán biển thiết kế tối giản, phù hợp nằm đọc sách cả chiều. Sáng sớm hoặc hoàng hôn là thời điểm nước và ánh sáng đẹp nhất để tắm biển, chụp ảnh. Luôn chú ý bảng cảnh báo và không bơi quá xa khu vực có nhân viên cứu hộ.",
                "For a “change of pace,” An Bang Beach is a peaceful choice: a gently sloping sandy shore, moderate waves and many minimalist beach bars, perfect for lying down and reading all afternoon. Early morning or sunset is when the water and light are most beautiful for swimming and photos. Always heed the warning signs and do not swim too far from the area covered by lifeguards."),
            heading("Lịch trình gợi ý 2–3 ngày", "Suggested 2-3 day itinerary"),
            paragraph("Ngày 1: Mua vé tham quan phố cổ, vào các điểm di tích chính (nhà cổ, hội quán, Chùa Cầu); chiều đi thuyền sông Hoài, tối dạo đèn lồng, thưởng thức cao lầu hoặc mì Quảng.",
                "Day 1: Buy a ticket to visit the old town and enter the main heritage sites (old houses, assembly halls, the Japanese Covered Bridge); take a Hoai River boat ride in the afternoon, stroll among the lanterns in the evening and enjoy cao lau or Quang noodles."),
            paragraph("Ngày 2: Đạp xe Trà Quế – Thanh Hà – Cẩm Thanh theo vòng làng nghề, trải nghiệm thúng chai; chiều tắm biển An Bàng, tối quay lại phố cổ thưởng thức bánh mì, cơm gà và cà phê ban công.",
                "Day 2: Cycle the Tra Que - Thanh Ha - Cam Thanh craft-village loop and try a basket boat; swim at An Bang Beach in the afternoon, and in the evening return to the old town to enjoy banh mi, chicken rice and a balcony cafe."),
            paragraph("Ngày 3 (nếu có): Tham gia lớp nấu ăn món địa phương buổi sáng, trưa nghỉ ngơi và mua quà đặc sản; chiều chụp ảnh dọc các hẻm ít người, tối dự đêm rằm (nếu trùng lịch) hoặc xem bài chòi ở quảng trường.",
                "Day 3 (if available): Join a local cooking class in the morning, rest at noon and buy specialty gifts; take photos along the quiet alleys in the afternoon, and in the evening attend the full-moon night (if it coincides) or watch bai choi at the square."),
            infoBox("Mẹo nhỏ: Phố cổ ưu tiên người đi bộ theo khung giờ; bạn hãy gửi xe ở bãi quy định rồi đi bộ để trải nghiệm trọn vẹn. Vào mùa cao điểm, nên đặt trước vé trải nghiệm (thuyền, lớp nấu ăn) và chọn khung giờ sớm để tránh đông.",
                "Tip: The old town prioritizes pedestrians during certain hours; park your vehicle in the designated lot and walk to fully enjoy the experience. In peak season, book experience tickets (boat rides, cooking classes) in advance and choose early time slots to avoid the crowds."),
            heading("Thời điểm & lưu ý", "Timing & notes"),
            paragraph("Hội An nắng đẹp từ khoảng tháng 2–8; tháng 9–12 có thể mưa, thỉnh thoảng ngập cục bộ do triều. Mang theo nón, kem chống nắng, áo khoác mỏng; giày sandal/giày bệt phù hợp đi bộ lâu. Khi chụp ảnh người dân, nên xin phép bằng nụ cười và cái gật đầu – bạn sẽ nhận lại sự thân thiện xứng đáng.",
                "Hoi An has lovely sunshine from around February to August; September-December can be rainy, with occasional local flooding from the tides. Bring a hat, sunscreen and a light jacket; sandals or flats are best for long walks. When photographing locals, ask permission with a smile and a nod - you will receive well-deserved friendliness in return."),
            paragraph("Phố cổ có nhiều cầu gỗ, bậc thềm cao – đi lại chú ý bước chân, đặc biệt buổi tối khi ánh sáng trầm. Ẩm thực đa phần đậm vị; nếu ăn nhạt, bạn có thể nói trước với quán. Hạn chế dùng túi nylon khi mua đồ, tận dụng túi vải cá nhân để góp phần giữ gìn không gian di sản xanh – sạch – đẹp.",
                "The old town has many wooden bridges and high steps - watch your footing, especially in the evening when the light is dim. The cuisine is mostly bold in flavor; if you prefer it light, tell the eatery in advance. Limit plastic bag use when shopping and bring your own cloth bag to help keep the heritage space green, clean and beautiful."),
            quote("“Hội An đẹp nhất là khi bạn đi chậm.”", "“Hoi An is at its most beautiful when you slow down.”"),
            divider(),
            mapBlock(new double[]{108.3380, 15.8801})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Di sản văn hoá", "Cultural heritage")).slug("di-san-van-hoa").build(),
            PlaceDoc.CategoryMini.builder().name(of("Phố cổ", "Ancient town")).slug("pho-co").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Di sản UNESCO", "UNESCO heritage")).slug("di-san-unesco").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Đèn lồng Hội An", "Hoi An lanterns")).slug("den-long-hoi-an").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  private static PlaceDoc haNoi() {
    String slug = "ha-noi";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name(of("Hà Nội", "Hanoi"))
        .slug(slug)
        .shortDescription(of("Thủ đô nghìn năm văn hiến với phố cổ, hồ Hoàn Kiếm và ẩm thực tinh tế.", "A thousand-year-old capital of culture with its Old Quarter, Hoan Kiem Lake and refined cuisine."))
        .description(of("Hà Nội hấp dẫn bởi nhịp sống chậm, di tích lịch sử, mùa thu lá vàng và cà phê vỉa hè.", "Hanoi captivates with its slow pace of life, historic sites, golden autumn leaves and sidewalk coffee."))
        .addressLine(of("Thành phố Hà Nội, Việt Nam", "Hanoi City, Vietnam"))
        .countryCode("VN").provinceName(of("Hà Nội", "Hanoi"))
        .location(new double[]{105.8342, 21.0278})
        .vibes(List.of("urban", "cultural", "vibrant"))
        .atmosphere(of("historic-urban", "historic-urban"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group", "business"))
        .visitPurpose(List.of("explore", "culture", "food", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(4320)
        .photographyScore(80)
        .romanticScore(75)
        .localExperienceScore(90)
        .images(withCover(
            img("https://divui.com/blog/wp-content/uploads/2017/01/hanoi-top-10-attractions.jpg", "Hình ảnh Hà Nội từ trên cao", "Hanoi seen from above", true, 0),
            img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/pho-co-ha-noi-1.jpg", "Phố cổ", "The Old Quarter", false, 1),
            img("https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/2024_1_12_638406880045931692_cach-lam-bun-cha-ha-noi-0.jpg", "Bún chả", "Bun cha", false, 2),
            img("https://dulichnewtour.vn/ckfinder/images/Tours/langbac/lang-bac%20(2).jpg", "Lăng Bác", "Ho Chi Minh Mausoleum", false, 3),
            img("https://statics.vinpearl.com/mua-thu-ha-noi-thumb_1685027028.jpg", "Mùa thu Hà Nội", "Autumn in Hanoi", false, 4),
            img("https://kenh14cdn.com/thumb_w/600/vfLKYK84K2aVSVTBjN2M3SGbL8lqA9/Image/2015/03/DJ7A0875-4f435.jpg", "Cà phê vỉa hè", "Sidewalk coffee", false, 5)
        ))
        .content(List.of(
            heading("Hà Nội – lưu giữ hồn xưa phố cổ", "Hanoi - keeping the old soul of the ancient quarter"),
            quote("""
                Tháng mười heo may thổi,
                lá sấu rơi như lời thì thầm;
                Hà Nội gọi tên ai giữa những con ngõ nhỏ.
                """,
                """
                The October chill wind blows,
                dao leaves fall like whispered words;
                Hanoi calls out a name among the small alleys.
                """),
            paragraph("Hà Nội cuốn hút bởi nhịp sống chậm rãi đan xen giữa hiện đại và cổ kính. 36 phố phường vẫn còn thấp thoáng dáng dấp phường nghề xưa: Hàng Bạc, Hàng Mã, Hàng Thiếc… Mỗi buổi sáng, tiếng rao quen thuộc len qua những con ngõ nhỏ, hương phở nóng bốc khói cạnh ly cà phê đen sóng sánh. Giữa nhịp mới – cũ ấy, người Hà Nội vẫn giữ nếp đi bộ quanh hồ, đọc báo, chuyện trò bên vỉa hè như một thói quen đã ăn vào ký ức tập thể.",
                "Hanoi captivates with a slow pace of life that interweaves the modern and the ancient. The 36 old streets still carry traces of the old craft guilds: Hang Bac, Hang Ma, Hang Thiec... Each morning, familiar street cries drift through the small alleys, the aroma of steaming hot pho beside a glossy glass of black coffee. Amid that new-and-old rhythm, Hanoians keep their habits of walking around the lake, reading the paper and chatting on the sidewalk - a custom etched into the collective memory."),
            paragraph("Bốn mùa rõ rệt tạo nên “gu” thưởng thức riêng: xuân ẩm, hạ rực, thu vàng và đông rét ngọt. Mỗi mùa lại có một palette màu và mùi khác nhau – mùi hoa sữa vào thu, mùi lá khô trên hè phố, mùi cốm mới trong gánh hàng rong. Đến Hà Nội, hãy dành thời gian đi chậm, để ý những chi tiết nhỏ: ô cửa gỗ đã bóng màu thời gian, bức tường rêu, biển số nhà men sứ xanh trắng.",
                "Four distinct seasons create a unique “taste” for enjoyment: humid spring, blazing summer, golden autumn and crisp winter. Each season has its own palette of colors and scents - the smell of milk-flower in autumn, of dry leaves on the sidewalks, of fresh green rice in a vendor's basket. In Hanoi, take time to go slowly and notice the small details: wooden window shutters glossy with age, mossy walls, blue-and-white porcelain house-number plates."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/ho-hoan-kiem-3-1710638969.jpg", "Hồ Gươm", "Hoan Kiem Lake", false, 0)),
            heading("Phố cổ & những khoảng lặng quanh hồ", "The Old Quarter & quiet moments around the lake"),
            paragraph("Hồ Hoàn Kiếm là “trái tim” của thành phố: ban ngày trong veo, đêm lung linh dưới ánh đèn. Từ đây, bạn có thể rẽ vào khu phố cổ để cảm nhận những mặt tiền nhà ống hẹp, sâu, sân nhỏ giữa nhà lấy sáng thông thoáng – kiểu kiến trúc thích nghi với đô thị đông đúc. Quanh hồ là những công trình Pháp cổ: Nhà hát Lớn, Bưu điện, những hàng cây xà cừ, sấu già tạo nên đường chân trời mềm mại, nhiều lớp.",
                "Hoan Kiem Lake is the “heart” of the city: crystal clear by day, shimmering under the lights by night. From here you can turn into the Old Quarter to feel the narrow, deep tube-house facades with small interior courtyards for light and ventilation - architecture adapted to a crowded city. Around the lake stand old French buildings: the Opera House, the Post Office, and rows of mahogany and old dao trees forming a soft, layered skyline."),
            paragraph("Buổi sớm cuối tuần, phố đi bộ quanh hồ mở ra không gian cho nghệ sĩ đường phố, trò chơi dân gian, hoạt động cộng đồng. Ngồi trên ghế đá, nghe chuông chùa Ngọc Sơn vọng lại, bạn sẽ hiểu vì sao người Hà Nội yêu những khoảnh khắc “rảnh rỗi” bên hồ – nơi ai cũng có thể tạm gác nhịp công việc để chậm lại một nhịp thở.",
                "On weekend mornings, the walking street around the lake opens up space for street artists, folk games and community activities. Sitting on a stone bench, hearing the bell of Ngoc Son Temple echo back, you will understand why Hanoians love those “idle” moments by the lake - where anyone can set aside the pace of work to slow down for a breath."),
            gallery(List.of(
                img("https://divui.com/blog/wp-content/uploads/2017/01/hanoi-top-10-attractions.jpg", "Hà Nội nhìn từ trên cao", "Hanoi seen from above", false, 0),
                img("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/pho-co-ha-noi-1.jpg", "Phố cổ 36 phố phường", "The Old Quarter of 36 streets", false, 1),
                img("https://statics.vinpearl.com/mua-thu-ha-noi-thumb_1685027028.jpg", "Mùa thu Hà Nội", "Autumn in Hanoi", false, 2)
            )),
            heading("Lịch sử & di tích", "History & monuments"),
            paragraph("Từ Thăng Long ngàn năm đến Hà Nội hôm nay là hành trình của những lớp trầm tích văn hóa. Khu Thành cổ, Văn Miếu – Quốc Tử Giám, đền Quán Thánh… là những mốc son ghi dấu học thuật và thi cử Nho giáo. Thời cận – hiện đại để lại nhiều công trình Pháp thuộc với phong cách tân cổ điển, art deco, tạo nên diện mạo đô thị thanh lịch. Mỗi di tích là một lát cắt – giúp bạn đọc được lịch sử bằng mắt thường qua chất liệu đá, gỗ, gạch ngói.",
                "From Thang Long of a thousand years to today's Hanoi is a journey through layers of cultural sediment. The Imperial Citadel, the Temple of Literature - Imperial Academy, and Quan Thanh Temple are landmarks marking Confucian scholarship and examinations. The modern era left many French colonial buildings in neoclassical and art deco styles, creating an elegant urban character. Each monument is a slice that lets you read history with the naked eye through stone, wood and brick."),
            paragraph("Lăng Chủ tịch Hồ Chí Minh và Quảng trường Ba Đình là không gian trang nghiêm bậc nhất. Khi ghé thăm, bạn nên lưu ý trang phục kín đáo, giữ trật tự. Khu nhà sàn, ao cá, Phủ Chủ tịch gợi một tinh thần giản dị mà sâu sắc, đối thoại lặng lẽ với khu phố xá sôi động cách đó không xa.",
                "The Ho Chi Minh Mausoleum and Ba Dinh Square are among the most solemn spaces. When visiting, dress modestly and keep order. The stilt house, fish pond and Presidential Palace evoke a simple yet profound spirit, in quiet dialogue with the bustling streets not far away."),
            heading("Ẩm thực Hà Nội – tinh tế trong từng bát – đĩa", "Hanoi cuisine - refinement in every bowl and plate"),
            paragraph("Hà Nội là “đất phở”, nhưng không chỉ có phở. Bún chả thơm lừng mùi than hoa, bún thang trong trẻo mà nhiều tầng vị, bún đậu mắm tôm mộc mạc, chả cá Lã Vọng béo ngậy ăn kèm thì là và lạc rang. Mỗi món có “nghi thức” riêng: cách chần bánh, nêm dấm tỏi, thêm vài cọng hành hoa, một lát quất – những động tác nhỏ nhưng tạo nên bản sắc.",
                "Hanoi is the “land of pho,” but it is not only pho. Bun cha fragrant with charcoal smoke, clear yet multi-layered bun thang, rustic bun dau with shrimp paste, and rich Cha Ca La Vong served with dill and roasted peanuts. Each dish has its own “ritual”: how to blanch the noodles, season with garlic vinegar, add a few sprigs of spring onion and a slice of kumquat - small gestures that create identity."),
            paragraph("Bữa sáng nên nhẹ nhàng với phở, trưa thử bún chả, tối chọn chả cá hoặc lẩu riêu cua bắp bò. Tráng miệng bằng cốm làng Vòng, kem Tràng Tiền, hoặc một cốc sấu dầm mát lạnh ngày hè. Nếu bạn thích trải nghiệm cà phê, đừng bỏ qua cà phê trứng – lớp kem trứng sánh mịn quyện espresso, hay ly nâu đá giản dị ở quán vỉa hè.",
                "Breakfast should be light with pho, lunch can be bun cha, and dinner cha ca or crab-paste hotpot with beef brisket. For dessert, try Vong village green rice, Trang Tien ice cream, or a cool glass of pickled dao in summer. If you enjoy coffee, do not miss egg coffee - a smooth, creamy egg layer blended with espresso - or a simple iced milk coffee at a sidewalk stall."),
            imageBlock(img("https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/2024_1_12_638406880045931692_cach-lam-bun-cha-ha-noi-0.jpg", "Bún chả", "Bun cha", false, 0)),
            heading("Cà phê vỉa hè & văn hóa trò chuyện", "Sidewalk coffee & the culture of conversation"),
            paragraph("Cà phê ở Hà Nội không chỉ để tỉnh táo, mà còn để… trò chuyện. Ghế nhựa thấp, bàn con, ly cà phê đặt sát mép đường nơi người và xe lướt qua – đó là “phòng khách mở” của người Hà Nội. Ở những góc phố quen, bạn có thể dõi theo vòng đời một ngày của thành phố: sáng làm việc, trưa thong thả, chiều bận rộn, đêm lắng lại.",
                "Coffee in Hanoi is not only to stay awake, but also to... chat. Low plastic stools, small tables, a coffee glass set right at the road's edge where people and vehicles glide past - that is the “open living room” of Hanoians. At familiar street corners, you can follow the city's daily cycle: working in the morning, leisurely at noon, busy in the afternoon, settling down at night."),
            paragraph("Thú vị nhất là quan sát cách người ta “đi chậm” giữa một đô thị không ngừng chuyển động. Một cuộc hẹn 15 phút có thể kéo dài cả giờ bởi những câu chuyện không đầu không cuối – nhưng chính sự dư dả thời gian ấy tạo nên chất “đằm” của Hà Nội.",
                "Most interesting is watching how people “go slowly” in a city that never stops moving. A 15-minute meet-up can stretch into an hour over rambling stories with no beginning or end - but it is exactly that abundance of time that creates Hanoi's mellow character."),
            heading("Mùa thu Hà Nội – khi lá vàng phủ lối", "Autumn in Hanoi - when golden leaves cover the path"),
            paragraph("Thu đến, nắng vàng nhẹ như rót mật, gió heo may mỏng và bầu trời xanh cao. Hàng cây thay lá, những con đường Kim Mã, Phan Đình Phùng, Hoàng Diệu trở thành phông nền hoàn hảo cho những khung hình trong trẻo. Mùi hoa sữa có thể gây “tranh cãi”, nhưng với nhiều người, đó là tín hiệu mùa thu không thể lẫn vào đâu.",
                "When autumn comes, the soft golden sun pours like honey, the chill wind is thin and the sky is high and blue. The trees change their leaves, and the streets of Kim Ma, Phan Dinh Phung and Hoang Dieu become perfect backdrops for crisp, clear photos. The scent of milk-flower can be “controversial,” but for many it is an unmistakable signal of autumn."),
            paragraph("Thời điểm đẹp thường rơi vào tháng 10–11: nhiệt độ dễ chịu để dạo bộ cả ngày. Hãy chuẩn bị áo khoác mỏng, giày bệt hoặc sneaker; buổi tối có thể se lạnh, một chiếc khăn mỏng sẽ khiến hành trình êm ái hơn.",
                "The best time usually falls in October-November: pleasant temperatures for walking all day. Prepare a light jacket and flats or sneakers; evenings can be chilly, and a thin scarf will make the journey more comfortable."),
            heading("Lịch trình gợi ý 2–3 ngày", "Suggested 2-3 day itinerary"),
            paragraph("Ngày 1: Hồ Gươm – Đền Ngọc Sơn – phố cổ – Nhà thờ Lớn; trưa phở; chiều Nhà hát Lớn – khu phố Pháp; tối dạo hồ và cà phê trứng; nếu đi cuối tuần, tham gia phố đi bộ để cảm nhận nhịp cộng đồng.",
                "Day 1: Hoan Kiem Lake - Ngoc Son Temple - the Old Quarter - St. Joseph's Cathedral; pho for lunch; the Opera House and French Quarter in the afternoon; a lake stroll and egg coffee in the evening; if it is the weekend, join the walking street to feel the community vibe."),
            paragraph("Ngày 2: Lăng Bác – Khu di tích Phủ Chủ tịch – Chùa Một Cột; trưa bún chả; chiều Văn Miếu – Hoàng thành; tối thưởng thức chả cá hoặc lẩu riêu cua; khu vực Phan Đình Phùng – Hoàng Diệu lý tưởng để đi bộ dưới tán cây cổ thụ.",
                "Day 2: Ho Chi Minh Mausoleum - the Presidential Palace site - One Pillar Pagoda; bun cha for lunch; the Temple of Literature and the Imperial Citadel in the afternoon; enjoy cha ca or crab-paste hotpot in the evening; the Phan Dinh Phung - Hoang Dieu area is ideal for walking under the canopy of old trees."),
            paragraph("Ngày 3 (nếu có): Bảo tàng Mỹ thuật/ Dân tộc học – dạo Tây Hồ – Phủ Tây Hồ – chùa Trấn Quốc; chiều muộn ngắm hoàng hôn bên hồ; tối lang thang quận Tây Hồ hoặc quay về phố cổ ăn vặt, kết thúc bằng ly nâu đá đúng điệu.",
                "Day 3 (if available): the Fine Arts or Ethnology Museum - a stroll around West Lake - Tay Ho Temple - Tran Quoc Pagoda; watch the sunset by the lake in the late afternoon; in the evening wander the West Lake district or return to the Old Quarter for snacks, ending with a proper iced milk coffee."),
            infoBox("Mẹo nhỏ: Buổi sáng đi di tích nên xuất phát sớm để tránh đông; vào Lăng Bác lưu ý trang phục lịch sự. Ẩm thực Hà Nội thiên mặn – thanh; nếu ăn nhạt, bạn có thể nhờ quán nêm nhẹ tay.",
                "Tip: Visit monuments early in the morning to avoid the crowds; dress respectfully when entering the Mausoleum. Hanoi cuisine tends to be savory and clean-tasting; if you prefer it light, you can ask the eatery to season gently."),
            divider(),
            heading("Di chuyển & lưu ý", "Getting around & notes"),
            paragraph("Đường phố Hà Nội nhiều ngõ nhỏ, một số tuyến giờ cao điểm dễ ùn ứ. Khi di chuyển bằng taxi/xe công nghệ, bạn nên chọn điểm đón ở đầu/ngã tư rộng rãi. Đi bộ trong phố cổ cần chú ý vỉa hè hẹp, nhiều đoạn lồi lõm. Luôn mang theo ô gấp hoặc áo mưa mỏng vì mưa bất chợt khá thường gặp.",
                "Hanoi's streets have many small alleys, and some routes get congested at peak hours. When using a taxi or ride-hailing car, choose a pickup point at a wide street entrance or intersection. Walking in the Old Quarter, mind the narrow, uneven sidewalks. Always carry a folding umbrella or a light raincoat, as sudden rain is fairly common."),
            paragraph("Mua hàng ở khu phố cổ đa phần có niêm yết, nhưng bạn vẫn nên hỏi giá trước. Với hàng lưu niệm, hãy ưu tiên sản phẩm thủ công địa phương, kiểm tra chất liệu và đường may. Tôn trọng không gian tôn nghiêm ở đền – chùa; hạn chế nói lớn, giữ trật tự để trải nghiệm của mọi người đều dễ chịu.",
                "Shopping in the Old Quarter mostly has posted prices, but you should still ask first. For souvenirs, favor local handicrafts and check the materials and stitching. Respect the solemn spaces of temples and pagodas; keep your voice down and maintain order so everyone's experience stays pleasant."),
            quote("“Hà Nội – bình yên trong từng ngõ nhỏ.”", "“Hanoi - peace in every small alley.”"),
            mapBlock(new double[]{105.8342, 21.0278})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Thủ đô", "Capital city")).slug("thu-do").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Phố cổ 36 phố phường", "Old Quarter of 36 streets")).slug("pho-co-36-pho-phuong").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Hồ Hoàn Kiếm", "Hoan Kiem Lake")).slug("ho-hoan-kiem").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  private static PlaceDoc hoChiMinh() {
    String slug = "ho-chi-minh-city";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .slug(slug)
        .shortDescription(of("Đô thị sôi động nhất Việt Nam, trung tâm kinh tế – văn hoá phía Nam.", "Vietnam's most dynamic metropolis, the economic and cultural hub of the South."))
        .description(of("Sài Gòn – TP. HCM nổi tiếng với nhịp sống nhanh, ẩm thực đường phố, " +
                     "các công trình Pháp và những con hẻm đầy câu chuyện.",
                     "Saigon - Ho Chi Minh City is famous for its fast pace of life, street food, " +
                     "French colonial buildings and alleys full of stories."))
        .addressLine(of("TP. Hồ Chí Minh, Việt Nam", "Ho Chi Minh City, Vietnam"))
        .countryCode("VN").provinceName(of("TP. Hồ Chí Minh", "Ho Chi Minh City"))
        .location(new double[]{106.6602, 10.7626})
        .vibes(List.of("urban", "vibrant", "diverse"))
        .atmosphere(of("metropolitan", "metropolitan"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group", "business"))
        .visitPurpose(List.of("explore", "culture", "food", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.LOUD)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING, BestVisitTime.NIGHT))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(4320)
        .photographyScore(75)
        .romanticScore(65)
        .localExperienceScore(85)
        .images(withCover(
            img("https://st.ielts-fighter.com/src/ielts-fighter-image/2023/01/09/5e6dbb91-3dab-4500-9a28-b9599aa12949.png", "Thành phố Hồ Chí Minhh nhìn từ trên cao", "Ho Chi Minh City seen from above", true, 0),
            img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-ruc-ro-1692570394.jpg", "Nhà thờ Đức Bà", "Notre-Dame Cathedral", false, 1),
            img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Saigon_Central_Post_Office_2022.jpg/500px-Saigon_Central_Post_Office_2022.jpg", "Bưu điện Thành phố", "The Central Post Office", false, 2),
            img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Chợ Bến Thành", "Ben Thanh Market", false, 3),
            img("https://www.vietfuntravel.com.vn/image/data/sai-gon/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm/kinh-nghiem-di-pho-di-bo-nguyen-hue-quan-1-tphcm-h1.jpg", "Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street", false, 4),
            img("https://mia.vn/media/uploads/blog-du-lich/pho-am-thuc-ho-thi-ky-1699930160.jpeg", "Ẩm thực đường phố", "Street food", false, 5)
        ))
        .content(List.of(
            heading("Sài Gòn – năng động, cởi mở và mến khách", "Saigon - dynamic, open and hospitable"),
            quote("""
                Mưa rào qua rất vội,
                hẻm nhỏ bừng đèn lên;
                ly cà phê còn ấm, câu chuyện dài thêm đêm.
                """,
                """
                The downpour passes in a rush,
                the small alley lights up bright;
                the coffee is still warm, the story stretches into the night.
                """),
            paragraph("Thành phố 24/7 với nhịp sống nhanh, người tứ xứ và năng lượng không ngủ; Sài Gòn quyến rũ bởi sự cởi mở, tính thực dụng dễ thương và một kho ký ức hòa trộn giữa nhà thờ gạch đỏ, bưu điện vàng rực, chợ búa rộn ràng cùng tiếng rao khuya.",
                "A 24/7 city with a fast pace, people from everywhere and sleepless energy; Saigon charms with its openness, its endearing pragmatism and a trove of memories blending the red-brick cathedral, the bright yellow post office, the bustling markets and the late-night street cries."),
            heading("Kiến trúc & dấu ấn lịch sử", "Architecture & historical imprints"),
            paragraph("Quận 1 – 3 là nơi bạn dễ đọc lịch sử đô thị qua các lớp kiến trúc: Nhà thờ Đức Bà gạch Marseille, Bưu điện Thành phố phong cách tân cổ điển, các biệt thự Pháp rải dọc đường cây dầu; xen giữa là những cao ốc kính phản chiếu bầu trời nhiệt đới – một hòa âm nhìn là nhớ.",
                "Districts 1-3 are where you can easily read the city's history through layers of architecture: Notre-Dame Cathedral of Marseille brick, the neoclassical Central Post Office, French villas scattered along tree-lined avenues; interspersed are glass towers reflecting the tropical sky - a harmony that stays in memory at a glance."),
            heading("Ẩm thực đường phố – vị ngon từ vỉa hè", "Street food - flavors from the sidewalk"),
            paragraph("Từ hủ tiếu gõ, cơm tấm sườn bì chả, bún mắm, phá lấu đến bánh mì thịt nướng – mỗi góc phố đều có món “ruột” của dân quanh vùng; buổi tối, các chợ đêm và con hẻm mở hội vị giác với hải sản nướng, ốc cay, chè khúc bạch, sương sáo, sâm bổ lượng.",
                "From hu tieu go, broken rice with ribs and shredded pork skin, bun mam and pha lau to grilled-pork banh mi - every corner has a “favorite” dish of the locals; in the evening, night markets and alleys throw a feast for the taste buds with grilled seafood, spicy snails, almond jelly sweet soup, grass jelly and sam bo luong."),
            heading("Cà phê & văn hóa hẹn hò", "Coffee & dating culture"),
            paragraph("Cà phê bệt ở Nhà thờ Đức Bà – Bưu điện hay quán view phố đi bộ Nguyễn Huệ là nơi người trẻ kể tương lai và người làm ăn chốt hợp đồng; ly bạc xỉu đá, cà phê sữa pha phin nhỏ giọt chậm rãi nhưng cuộc trò chuyện thì luôn nhanh, thẳng và vui.",
                "Sidewalk coffee at Notre-Dame Cathedral and the Post Office, or cafes overlooking Nguyen Hue Walking Street, is where young people talk about the future and businesspeople close deals; the iced bac xiu and phin-dripped milk coffee come slowly, but the conversation is always quick, direct and fun."),
            imageBlock(img("https://ticotravel.com.vn/wp-content/uploads/2022/05/Cho-Ben-Thanh-4.jpg", "Chợ Bến Thành", "Ben Thanh Market", false, 0)),
            heading("Đi đâu chơi?", "Where to go?"),
            paragraph("Ban ngày, dạo Nhà thờ Đức Bà – Bưu điện – phố sách; trưa trốn nắng trong bảo tàng hoặc trung tâm thương mại; chiều muộn ra phố đi bộ Nguyễn Huệ đón gió, tối vòng qua chợ Bến Thành nhộn nhịp; nếu thích “đậm chất Hoa”, ghé Chợ Lớn – Bình Tây – Hải Thượng Lãn Ông để ngắm lồng đèn, thảo dược và nếm sủi cảo.",
                "By day, stroll Notre-Dame Cathedral - the Post Office - the book street; at noon escape the heat in a museum or mall; in the late afternoon head to Nguyen Hue Walking Street for the breeze, and in the evening swing by the bustling Ben Thanh Market; if you like a “strong Chinese flavor,” visit Cho Lon - Binh Tay - Hai Thuong Lan Ong to see lanterns, herbs and taste dumplings."),
            gallery(List.of(
                img("https://mia.vn/media/uploads/blog-du-lich/nha-tho-duc-ba-sai-gon-ruc-ro-1692570394.jpg", "Nhà thờ Đức Bà", "Notre-Dame Cathedral", false, 0),
                img("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Saigon_Central_Post_Office_2022.jpg/500px-Saigon_Central_Post_Office_2022.jpg", "Bưu điện Thành phố", "The Central Post Office", false, 1),
                img("https://static.vinwonders.com/production/cho-ben-thanh-1.jpg", "Chợ Bến Thành", "Ben Thanh Market", false, 2)
            )),
            heading("Nhịp sống đêm & mua sắm", "Nightlife & shopping"),
            paragraph("Đêm đến là giờ đẹp của Sài Gòn: rooftop bar nhìn sông Sài Gòn, chợ đồ cũ – chợ đêm, khu Thảo Điền – Tân Định – Nguyễn Huệ sôi động; mua sắm trải từ boutique bản địa, chợ truyền thống đến mall hiện đại – dễ tìm đủ phong cách trong bán kính vài cây số.",
                "Nightfall is Saigon's finest hour: rooftop bars overlooking the Saigon River, flea markets and night markets, the lively Thao Dien - Tan Dinh - Nguyen Hue areas; shopping ranges from local boutiques and traditional markets to modern malls - it is easy to find every style within a few kilometers."),
            heading("Lịch trình gợi ý 2–3 ngày", "Suggested 2-3 day itinerary"),
            paragraph("Ngày 1: Nhà thờ Đức Bà – Bưu điện – phố sách; trưa cơm tấm; chiều Bến Bạch Đằng – phố đi bộ Nguyễn Huệ; tối chợ Bến Thành và ốc – chè.",
                "Day 1: Notre-Dame Cathedral - the Post Office - the book street; broken rice for lunch; Bach Dang Wharf and Nguyen Hue Walking Street in the afternoon; Ben Thanh Market, snails and sweet soup in the evening."),
            paragraph("Ngày 2: Bảo tàng Chứng tích Chiến tranh hoặc Bảo tàng Mỹ thuật; trưa bánh mì – cà phê; chiều Chợ Lớn – Bình Tây – các hội quán người Hoa; tối về Quận 1 ngắm thành phố từ rooftop.",
                "Day 2: the War Remnants Museum or the Fine Arts Museum; banh mi and coffee for lunch; Cho Lon - Binh Tay - the Chinese assembly halls in the afternoon; back to District 1 in the evening to view the city from a rooftop."),
            paragraph("Ngày 3 (nếu có): Dạo Thảo Cầm Viên hoặc công viên bờ sông; brunch ở Thảo Điền; chiều săn ảnh hẻm tường hồng Tân Định; tối ăn đồ nướng vỉa hè và tản bộ kết màn.",
                "Day 3 (if available): stroll the Zoo and Botanical Gardens or a riverside park; brunch in Thao Dien; chase photos at the pink-walled Tan Dinh alley in the afternoon; end with sidewalk grilled food and a stroll in the evening."),
            heading("Di chuyển & lưu ý", "Getting around & notes"),
            paragraph("Giờ cao điểm dễ kẹt xe; nên canh lịch hẹn trước 15–20 phút và chọn điểm đón trả rộng rãi khi gọi xe công nghệ; mưa nhiệt đới đến nhanh, hãy mang áo mưa mỏng/ô gấp; thanh toán phổ biến bằng tiền mặt và chuyển khoản, hỏi giá trước khi gọi món ở khu du lịch đông khách.",
                "Peak hours bring traffic jams; plan appointments 15-20 minutes early and choose spacious pickup/drop-off points when using ride-hailing; tropical rain comes fast, so bring a light raincoat or folding umbrella; payment is commonly by cash and bank transfer, and ask the price before ordering in crowded tourist areas."),
            infoBox("Mẹo nhỏ: Tránh chen lấn trên phố đi bộ khi có sự kiện; mang giày thoải mái vì bạn sẽ đi bộ khá nhiều qua các điểm tham quan gần nhau; các khu ẩm thực đêm thường mở muộn – sau 19:00 là nhộn nhất.",
                "Tip: Avoid the crush on the walking street during events; wear comfortable shoes since you will walk quite a bit between nearby attractions; night food areas usually open late - they are liveliest after 19:00."),
            quote("“Sài Gòn không ngủ.”", "“Saigon never sleeps.”"),
            divider(),
            mapBlock(new double[]{106.6602, 10.7626})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Đô thị", "Metropolis")).slug("do-thi").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Nhà thờ Đức Bà", "Notre-Dame Cathedral")).slug("nha-tho-duc-ba").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Ẩm thực đường phố", "Street food")).slug("am-thuc-duong-pho").type(TagType.CUISINE).build(),
            PlaceDoc.TagMini.builder().name(of("Thành phố không ngủ", "The city that never sleeps")).slug("thanh-pho-khong-ngu").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  private static PlaceDoc sapa() {
    String slug = "sa-pa";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name(of("Sa Pa", "Sa Pa"))
        .slug(slug)
        .shortDescription(of("Thị trấn trên mây, ruộng bậc thang và đỉnh Fansipan hùng vĩ.", "A town in the clouds, with terraced fields and the majestic Fansipan peak."))
        .description(of("Khí hậu mát mẻ quanh năm, trekking bản làng, săn mây, chụp ảnh ruộng bậc thang mùa lúa chín.", "Cool weather year-round, village trekking, cloud-hunting and photographing terraced fields in the ripe-rice season."))
        .addressLine(of("Thị xã Sa Pa, Lào Cai, Việt Nam", "Sa Pa Town, Lao Cai, Vietnam"))
        .countryCode("VN").provinceName(of("Lào Cai", "Lao Cai"))
        .location(new double[]{103.8440, 22.3350})
        .vibes(List.of("nature", "cultural", "relaxing"))
        .atmosphere(of("mountain-highland", "mountain-highland"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "culture", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.QUIET)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.YEAR_ROUND)
        .visitDurationMinutes(4320)
        .photographyScore(95)
        .romanticScore(80)
        .localExperienceScore(90)
        .images(withCover(
            img("https://res.klook.com/image/upload/fl_lossy.progressive,q_60/Mobile/City/nab4excv9bkndhqnsyvl.jpg", "Thành phố Sa Pa", "Sa Pa town", true, 0),
            img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463729618_ruong-bac-thang-2.jpg", "Ruộng bậc thang Sa Pa", "Sa Pa terraced fields", false, 1),
            img("https://static.vinwonders.com/production/mua-san-may-sa-pa.jpg", "Mây phủ thung lũng", "Clouds covering the valley", false, 2),
            img("https://nhahangchotinhsapa.vn/wp-content/uploads/2023/12/am-thuc-tay-bac.png", "Ẩm thực vùng cao", "Highland cuisine", false, 3),
            img("http://reviewsapa.com/public/library/images/Du%20l%E1%BB%8Bch%20Sapa/du%20l%E1%BB%8Bch%20Sapa%20s%C4%83n%20m%C3%A2y%20v%C3%A0%20ho%C3%A0ng%20h%C3%B4n.jpg", "Hoàng hôn trên núi", "Sunset over the mountains", false, 4),
            img("https://fansipanlegend.sunworld.vn/wp-content/uploads/2020/04/cap-treo-fansipan-legend_result.png", "Cáp treo Fansipan", "The Fansipan cable car", false, 5),
            img("https://halosasapa.com/wp-content/uploads/2023/04/catcat.jpg", "Bản làng", "A mountain village", false, 6)
        ))
        .content(List.of(
            heading("Sa Pa – chạm vào mây trời Tây Bắc", "Sa Pa - touching the clouds of the Northwest"),
            quote("""
                Mây vờn qua đỉnh núi,
                bậc thang ôm trời xa;
                bước chân theo gió lạnh, nghe nắng đọng trên lá.
                """,
                """
                Clouds drift over the mountain peaks,
                terraces embrace the distant sky;
                footsteps follow the cold wind, hearing sunlight rest on the leaves.
                """),
            paragraph("Sa Pa mang sắc thái núi rừng đặc trưng của Tây Bắc: không khí mát lạnh, sương sớm bảng lảng và những thửa ruộng bậc thang nối nhau như sóng. Thị trấn là điểm khởi hành lý tưởng cho chuyến trekking nhẹ, khám phá bản làng và trải nghiệm văn hóa các dân tộc H’Mông, Dao, Tày.",
                "Sa Pa carries the characteristic mountain-forest mood of the Northwest: cool air, hazy morning mist and terraced fields stretching one after another like waves. The town is an ideal starting point for a light trek, exploring villages and experiencing the culture of the H'Mong, Dao and Tay peoples."),
            paragraph("Thời điểm được ưa chuộng: mùa lúa chín khoảng tháng 9–10 khi sườn đồi nhuộm vàng; mùa săn mây từ cuối thu đến cuối xuân khi thung lũng phủ lớp mây bồng bềnh; mùa hoa đỗ quyên trên dãy Hoàng Liên vào khoảng tháng 3–4.",
                "Favored times: the ripe-rice season around September-October when the hillsides turn golden; the cloud-hunting season from late autumn to late spring when the valley is covered in drifting clouds; and the rhododendron season on the Hoang Lien range around March-April."),
            imageBlock(img("https://mia.vn/media/uploads/blog-du-lich/ve-dep-ruc-ro-cua-mua-vang-sapa-dep-nhu-buoc-ra-tu-tranh-ve-2-1663090680.jpg", "Bậc thang mùa vàng", "Terraces in the golden season", false, 0)),
            heading("Trekking & trải nghiệm bản làng", "Trekking & village experiences"),
            paragraph("Các cung dễ tiếp cận cho người mới: Cát Cát – Sin Chải, Y Linh Hồ – Lao Chải – Tả Van dọc thung lũng Mường Hoa; muốn thử thách hơn có thể chọn Tả Phìn hoặc Tả Giàng Phình. Thuê local guide giúp đi đúng lối, hiểu câu chuyện bản địa và chủ động phương án thời tiết.",
                "Beginner-friendly routes: Cat Cat - Sin Chai, and Y Linh Ho - Lao Chai - Ta Van along the Muong Hoa valley; for more of a challenge, choose Ta Phin or Ta Giang Phinh. Hiring a local guide helps you stay on the right path, understand local stories and plan around the weather."),
            paragraph("Khi vào bản, tôn trọng không gian sinh hoạt: xin phép trước khi chụp ảnh, hạn chế xả rác và ưu tiên mua sản phẩm thủ công địa phương như thổ cẩm, bạc chạm. Đường núi có đoạn trơn trượt sau mưa, nên mang giày trekking đế bám và áo mưa mỏng.",
                "When entering a village, respect the living space: ask permission before taking photos, avoid littering and favor buying local handicrafts such as brocade and engraved silver. Mountain paths can be slippery after rain, so wear grippy trekking shoes and bring a light raincoat."),
            gallery(List.of(
                img("https://statics.vntrip.vn/data-v2/data-guide/img_content/1463729618_ruong-bac-thang-2.jpg", "Ruộng bậc thang mùa vàng", "Terraced fields in the golden season", false, 0),
                img("https://static.vinwonders.com/production/mua-san-may-sa-pa.jpg", "Săn mây thung lũng", "Cloud-hunting over the valley", false, 1),
                img("https://fansipanlegend.sunworld.vn/wp-content/uploads/2020/04/cap-treo-fansipan-legend_result.png", "Cáp treo Fansipan", "The Fansipan cable car", false, 2)
            )),
            heading("Fansipan & điểm ngắm cảnh", "Fansipan & scenic viewpoints"),
            paragraph("Đỉnh Fansipan có thể tiếp cận nhanh bằng cáp treo; thời tiết trên cao thay đổi nhanh, trời có thể lạnh sâu dù dưới thị trấn ấm áp, vì vậy chuẩn bị áo ấm, khăn gió, mũ trùm tai. Khung giờ sáng muộn đến trưa thường tầm nhìn đẹp hơn sau khi sương tan.",
                "Fansipan peak can be reached quickly by cable car; the weather up high changes fast and it can be deeply cold even when the town below is warm, so prepare warm clothing, a windproof scarf and an ear-covering hat. Late morning to noon usually offers better visibility after the mist clears."),
            paragraph("Các điểm ngắm hoàng hôn – bình minh: đèo Ô Quy Hồ, thung lũng Mường Hoa, đồi Sun Plaza; ngày nhiều mây có thể đổi sang café view thung lũng để vừa trú gió vừa săn khoảng trời hửng nắng hiếm hoi.",
                "Sunset and sunrise spots: O Quy Ho Pass, the Muong Hoa valley and the Sun Plaza hill; on cloudy days you can switch to a valley-view cafe to shelter from the wind while waiting for a rare break of sunlight."),
            heading("Ẩm thực vùng cao", "Highland cuisine"),
            paragraph("Thử thắng cố, lợn bản nướng, cá suối, gà đồi, cải mèo xào và các món nướng đêm ở khu trung tâm. Đồ uống ấm như trà shan tuyết, rượu táo mèo giúp xua lạnh – uống có chừng mực để giữ sức cho chặng trekking hôm sau.",
                "Try thang co, grilled village pork, stream fish, free-range chicken, stir-fried mustard greens and the night-time grilled dishes in the town center. Warm drinks such as Shan Tuyet tea and son tra apple wine help fend off the cold - drink in moderation to save your strength for the next day's trek."),
            heading("Lịch trình gợi ý 2–3 ngày", "Suggested 2-3 day itinerary"),
            paragraph("Ngày 1: Dạo trung tâm thị trấn – nhà thờ đá – quảng trường; chiều tham quan Cát Cát; tối dạo phố ẩm thực và nghỉ sớm.",
                "Day 1: Stroll the town center - the stone church - the square; visit Cat Cat village in the afternoon; wander the food street in the evening and rest early."),
            paragraph("Ngày 2: Trek Y Linh Hồ – Lao Chải – Tả Van dọc Mường Hoa; chiều về thị trấn tắm lá Dao đỏ; tối thưởng thức đồ nướng, ngắm thị trấn trong sương.",
                "Day 2: Trek Y Linh Ho - Lao Chai - Ta Van along Muong Hoa; return to town in the afternoon for a Red Dao herbal bath; in the evening enjoy grilled food and admire the town in the mist."),
            paragraph("Ngày 3 (nếu có): Cáp treo Fansipan săn mây; trưa quay lại trung tâm mua thổ cẩm – đặc sản; chiều ngắm Ô Quy Hồ rồi rời Sa Pa.",
                "Day 3 (if available): take the Fansipan cable car to hunt clouds; return to the center at noon to buy brocade and specialties; admire O Quy Ho Pass in the afternoon and then leave Sa Pa."),
            infoBox("Mẹo nhỏ: Nhiệt độ ngày đêm chênh lệch lớn – luôn mang áo ấm mỏng trong balo; mưa núi đến nhanh, bọc chống nước cho điện thoại/máy ảnh; đổi tiền lẻ trước khi vào bản để tiện mua hàng và ủng hộ dịch vụ nhỏ lẻ.",
                "Tip: The day-night temperature difference is large - always carry a thin warm jacket in your backpack; mountain rain comes fast, so waterproof your phone and camera; get small change before entering a village to make buying easier and support small vendors."),
            heading("Di chuyển & lưu ý", "Getting around & notes"),
            paragraph("Địa hình dốc, sương mù dày vào tối/đêm – hạn chế tự lái nếu không quen đường núi; chọn xe ôm/bike taxi hoặc taxi địa phương cho các đoạn đèo. Tôn trọng tập quán địa phương, không cho trẻ em tiền kẹo; thay vào đó có thể mua sản phẩm của gia đình các em.",
                "The terrain is steep and the fog is dense in the evening and at night - avoid driving yourself if you are unfamiliar with mountain roads; choose a motorbike taxi or a local taxi for the pass sections. Respect local customs and do not give children money or sweets; instead, you can buy products from their families."),
            imageBlock(img("https://nhahangchotinhsapa.vn/wp-content/uploads/2023/12/am-thuc-tay-bac.png", "Ẩm thực vùng cao", "Highland cuisine", false, 0)),
            quote("“Sa Pa – nơi ta chạm tay vào mây.”", "“Sa Pa - where we touch the clouds.”"),
            divider(),
            mapBlock(new double[]{103.8440, 22.3350})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Miền núi & Cao nguyên", "Mountains & Highlands")).slug("mien-nui-cao-nguyen").build(),
            PlaceDoc.CategoryMini.builder().name(of("Dân tộc thiểu số", "Ethnic minorities")).slug("dan-toc-thieu-so").build(),
            PlaceDoc.CategoryMini.builder().name(of("Trekking", "Trekking")).slug("trekking").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Ruộng bậc thang", "Terraced fields")).slug("ruong-bac-thang").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Fansipan", "Fansipan")).slug("fansipan").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }

  private static PlaceDoc nhaTrang() {
    String slug = "nha-trang";
    return PlaceDoc.builder()
        .active(true)
        .kind(PlaceKind.DESTINATION)
        .name(of("Nha Trang", "Nha Trang"))
        .slug(slug)
        .shortDescription(of("Thiên đường biển Nam Trung Bộ với dải cát trắng và các vịnh đảo xanh.", "A south-central coastal paradise with white sandy shores and blue island bays."))
        .description(of("Tắm biển, lặn ngắm san hô, khám phá Hòn Mun, Hòn Tằm, tháp Bà Ponagar và ẩm thực hải sản.", "Swimming, coral snorkeling, exploring Hon Mun, Hon Tam, the Po Nagar Towers and seafood cuisine."))
        .addressLine(of("TP. Nha Trang, Khánh Hòa, Việt Nam", "Nha Trang City, Khanh Hoa, Vietnam"))
        .countryCode("VN").provinceName(of("Khánh Hòa", "Khanh Hoa"))
        .location(new double[]{109.1967, 12.2388})
        .vibes(List.of("relaxing", "vibrant", "diverse"))
        .atmosphere(of("coastal-resort", "coastal-resort"))
        .travelStyle(List.of("budget", "luxury", "family", "backpacker", "slow-travel"))
        .suitableFor(List.of("solo", "couple", "family", "group"))
        .visitPurpose(List.of("explore", "culture", "food", "relax", "photography"))
        .crowdLevel(CrowdLevel.HIGH)
        .noiseLevel(NoiseLevel.MODERATE)
        .venueContext(VenueContext.MIXED)
        .isFamilyFriendly(true)
        .bestVisitTime(List.of(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING))
        .bestSeason(BestSeason.DRY_SEASON)
        .visitDurationMinutes(4320)
        .photographyScore(85)
        .romanticScore(80)
        .localExperienceScore(70)
        .images(withCover(
            img("https://puolotrip.com/images/news/bien-nha-trang-3-3532.jpg", "Nha Trang", "Nha Trang", true, 0),
            img("https://static.vinwonders.com/2022/03/lan-bien-nha-trang-thumb.jpg", "Lặn ngắm san hô", "Coral snorkeling", false, 1),
            img("https://dulichnhatrang365.vn/wp-content/uploads/2023/06/du-thuyen-1.png", "Hoàng hôn trên vịnh", "Sunset over the bay", false, 2),
            img("https://cdn.nhatrang79.com/uploads/Tour_hon_tam_nha_trang_1_dfae30523d.jpg", "Hòn đảo ngoài khơi", "An offshore island", false, 3),
            img("https://statics.vinpearl.com/cho-hai-san-nha-trang--99_1628839999.jpg", "Hải sản tươi", "Fresh seafood", false, 4),
            img("https://kongforest.com/FileStorage/content/images/thap-ba-ponagar-nha-trang-3.jpeg", "Tháp Bà Ponagar", "Po Nagar Towers", false, 5)
        ))
        .content(List.of(
            heading("Nha Trang – nắng vàng, biển xanh, cát trắng", "Nha Trang - golden sun, blue sea, white sand"),
            quote("""
                Gió thổi nghiêng bờ cát,
                nắng tưới đầy vòm xanh;
                tiếng sóng gọi chân thành, ai đến rồi lưu luyến.
                """,
                """
                The wind tilts across the sandy shore,
                sunlight pours over the blue canopy;
                the waves call out sincerely, and whoever comes lingers in longing.
                """),
            paragraph("Nha Trang là điểm đến biển đặc trưng Nam Trung Bộ với dải cát trắng mịn, nước trong nhìn thấy đáy và chuỗi vịnh – đảo gần bờ; nhịp sống thong thả, dịch vụ đa dạng khiến nơi đây phù hợp cả nghỉ dưỡng gia đình lẫn các trải nghiệm thể thao biển.",
                "Nha Trang is a classic south-central beach destination with fine white sand, water so clear you can see the bottom, and a chain of nearshore bays and islands; its leisurely pace and diverse services make it suitable for both family getaways and water-sports experiences."),
            heading("Các vịnh – đảo & trải nghiệm ngoài khơi", "Bays, islands & offshore experiences"),
            paragraph("Từ trung tâm, bạn dễ dàng ra Hòn Mun, Hòn Tằm, Hòn Một bằng cano/du thuyền để lặn ngắm san hô, chèo SUP, đi bộ dưới đáy biển hoặc đơn giản là bơi và phơi nắng; nước êm vào buổi sáng, gió lên nhẹ tầm trưa – chiều nên lịch trình thường xuất phát sớm để tranh thủ mặt biển đẹp.",
                "From the center, you can easily reach Hon Mun, Hon Tam and Hon Mot by speedboat or yacht to snorkel over coral, paddle a SUP, walk on the seabed or simply swim and sunbathe; the water is calm in the morning and the wind picks up gently around midday and afternoon, so trips usually set off early to make the most of the beautiful sea."),
            paragraph("Khi tham gia tour biển, hãy chọn đơn vị có áo phao, kính – ống thở đạt chuẩn, hướng dẫn an toàn trước khi xuống nước; không chạm, bẻ san hô và giữ khoảng cách với sinh vật biển; mang túi chống nước cho điện thoại – máy ảnh, bôi kem chống nắng thân thiện môi trường nếu có.",
                "When joining a sea tour, choose an operator with life jackets and standard masks and snorkels, and a safety briefing before getting in the water; do not touch or break the coral and keep your distance from marine life; bring a waterproof pouch for your phone and camera, and use reef-friendly sunscreen if available."),
            imageBlock(img("https://dulichnewtour.vn/ckfinder/images/Tours/biennhatrang/bien-nha-trang%20(2).jpg", "Biển trong xanh", "The clear blue sea", false, 0)),
            heading("Văn hóa – di tích", "Culture & monuments"),
            paragraph("Trong phố, tháp Bà Ponagar lưu dấu văn minh Chăm với kiến trúc gạch nung đặc sắc; nhà thờ Núi, viện Hải dương học, bảo tàng Yersin gợi mở một lát cắt lịch sử – khoa học của thành phố biển; xen kẽ là chợ Đầm, chợ Xóm Mới nơi bạn có thể cảm nhận nhịp buôn bán rộn ràng.",
                "In the city, the Po Nagar Towers bear the mark of Cham civilization with their distinctive fired-brick architecture; the Mountain Church, the Oceanographic Institute and the Yersin Museum open a historical and scientific slice of the seaside city; interspersed are Dam Market and Xom Moi Market, where you can feel the lively pulse of trade."),
            gallery(List.of(
                img("https://puolotrip.com/images/news/bien-nha-trang-3-3532.jpg", "Bờ biển Nha Trang", "The Nha Trang coast", false, 0),
                img("https://static.vinwonders.com/2022/03/lan-bien-nha-trang-thumb.jpg", "Lặn ngắm san hô", "Coral snorkeling", false, 1),
                img("https://kongforest.com/FileStorage/content/images/thap-ba-ponagar-nha-trang-3.jpeg", "Tháp Bà Ponagar", "Po Nagar Towers", false, 2)
            )),
            heading("Ẩm thực hải sản & món địa phương", "Seafood & local dishes"),
            paragraph("Hải sản tươi theo ký là ngôi sao: tôm hùm, mực, ốc, cá mú… chế biến nướng, hấp, xào me tùy khẩu vị; món địa phương nên thử có bún chả cá, bún sứa, bánh căn, bánh xèo mực; buổi chiều dọc đường biển nhiều quán view hoàng hôn, lý tưởng để kết thúc một ngày trên vịnh.",
                "Fresh seafood by the kilo is the star: lobster, squid, snails, grouper... grilled, steamed or stir-fried with tamarind to taste; local dishes worth trying include fish-cake noodle soup, jellyfish noodles, mini rice pancakes and squid pancakes; in the afternoon, many spots along the coastal road have sunset views, ideal for ending a day out on the bay."),
            heading("Lịch trình gợi ý 2–3 ngày", "Suggested 2-3 day itinerary"),
            paragraph("Ngày 1: Tắm biển trung tâm – chạy bộ dọc Trần Phú; chiều tham quan tháp Bà Ponagar – nhà thờ Núi; tối thưởng thức hải sản và dạo biển.",
                "Day 1: Swim at the central beach and run along Tran Phu; visit the Po Nagar Towers and the Mountain Church in the afternoon; enjoy seafood and a beach stroll in the evening."),
            paragraph("Ngày 2: Tour đảo Hòn Mun – Hòn Tằm (snorkeling, tắm biển, chèo SUP); chiều spa bùn khoáng; tối đi chợ đêm hoặc ngắm biển đêm.",
                "Day 2: An island tour to Hon Mun and Hon Tam (snorkeling, swimming, SUP paddling); a mineral mud bath spa in the afternoon; visit the night market or watch the sea at night in the evening."),
            paragraph("Ngày 3 (nếu có): Viện Hải dương học – bảo tàng Yersin; trưa nhâm nhi bánh căn – bún sứa; chiều lên đồi ngắm toàn cảnh rồi rời Nha Trang.",
                "Day 3 (if available): the Oceanographic Institute and the Yersin Museum; savor mini rice pancakes and jellyfish noodles at noon; head up a hill for a panoramic view in the afternoon and then leave Nha Trang."),
            heading("Thời điểm & lưu ý", "Timing & notes"),
            paragraph("Biển Nha Trang nhìn chung êm từ khoảng 3–8; các tháng giao mùa có thể có mưa gió – trước khi đi đảo hãy theo dõi dự báo và hướng dẫn của nhà tàu; mang nón rộng vành, áo khoác chống nắng, dép đi biển; luôn hỏi giá rõ ràng trước khi chọn hải sản – dịch vụ nước để tối ưu chi phí.",
                "The Nha Trang sea is generally calm from around March to August; the transitional months can bring rain and wind - before going to the islands, check the forecast and the boat operator's guidance; bring a wide-brimmed hat, a sun-protective jacket and beach sandals; always ask for clear prices before choosing seafood and water services to optimize your costs."),
            infoBox("Mẹo nhỏ: Khung giờ 7:00–10:00 nước trong, nắng chưa gắt rất hợp để lặn ngắm; buổi chiều săn hoàng hôn trên vịnh hoặc dọc bãi Trần Phú cho ánh sáng đẹp nhất.",
                "Tip: The 7:00-10:00 window has clear water and gentle sun, perfect for snorkeling; in the afternoon, chase the sunset over the bay or along Tran Phu Beach for the best light."),
            quote("“Nắng vàng, biển xanh, cát trắng – Nha Trang gọi tên.”", "“Golden sun, blue sea, white sand - Nha Trang calls your name.”"),
            divider(),
            mapBlock(new double[]{109.1967, 12.2388})
        ))
        .categories(List.of(
            PlaceDoc.CategoryMini.builder().name(of("Điểm đến biển", "Beach destination")).slug("diem-den-bien").build(),
            PlaceDoc.CategoryMini.builder().name(of("Ẩm thực", "Cuisine")).slug("am-thuc").build(),
            PlaceDoc.CategoryMini.builder().name(of("Di tích lịch sử", "Historic site")).slug("di-tich-lich-su").build()
        ))
        .tags(List.of(
            PlaceDoc.TagMini.builder().name(of("Lặn ngắm san hô", "Coral snorkeling")).slug("lan-ngam-san-ho").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Tháp Bà Ponagar", "Po Nagar Towers")).slug("thap-ba-ponagar").type(TagType.FEATURE).build(),
            PlaceDoc.TagMini.builder().name(of("Thân thiện gia đình", "Family-friendly")).slug("than-thien-gia-dinh").type(TagType.FEATURE).build()
        ))
        .priceLevel(PriceLevel.CHEAP)
        .build();
  }
}