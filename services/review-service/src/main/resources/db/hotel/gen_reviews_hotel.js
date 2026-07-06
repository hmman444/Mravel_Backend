const fs = require('fs');
const path = require('path');

const SRC = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/mravel_catalog.hotels.json';
const OUT_DIR = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/hotel';

const USERS = [5, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20];

const ASPECTS = {
  CLEANLINESS: 1,
  ROOM_QUALITY: 2,
  STAFF_SERVICE: 3,
  LOCATION: 4,
  VALUE_FOR_MONEY: 5,
  AMENITIES: 6,
  BREAKFAST: 7,
  WIFI: 8,
};

// Phan bo trong so rating theo starRating that cua hotel -> khach san sao cao
// van co the bi che nhe, khach san sao thap van co the duoc khen, nhung thien
// ve dung phan bo thuc te hon la deu tam tap.
const RATING_PROFILE = {
  5: { 5: 8, 4: 3, 3: 1 },
  4: { 5: 6, 4: 4, 3: 2 },
  3: { 5: 4, 4: 4, 3: 3, 2: 1 },
  2: { 5: 3, 4: 4, 3: 3, 2: 2 },
};

const MAIN_TEMPLATES = {
  5: [
    '{name} thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.',
    'Kỳ nghỉ tại {name} vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.',
    '{name} xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.',
    'Trải nghiệm tại {name} rất đáng nhớ, sẽ giới thiệu cho bạn bè.',
    'Không có gì để chê ở {name}, quá hài lòng với chuyến đi này.',
    '{name} đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.',
    'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn {name}.',
    'Rất hài lòng với {name}, phòng thoáng mát và dịch vụ chuyên nghiệp.',
  ],
  4: [
    '{name} khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.',
    'Nhìn chung ở {name} thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.',
    '{name} đáng ở, dù không phải mọi thứ đều hoàn hảo.',
    'Khá hài lòng với kỳ nghỉ tại {name}, sẽ cân nhắc quay lại.',
    '{name} ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.',
    'Trải nghiệm tốt tại {name}, phù hợp cho chuyến đi ngắn ngày.',
  ],
  3: [
    '{name} tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.',
    'Trải nghiệm ở {name} bình thường, có vài điểm cần cải thiện.',
    '{name} cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.',
    'Mình thấy {name} ở mức tạm được, không tệ nhưng cũng không xuất sắc.',
  ],
  2: [
    '{name} chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.',
    'Trải nghiệm ở {name} không được như mong đợi, hơi thất vọng.',
    '{name} có một số vấn đề về chất lượng phòng và dịch vụ, cần khắc phục.',
  ],
};

const ASPECT_COMMENTS = {
  CLEANLINESS: {
    5: ['Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', 'Vệ sinh rất tốt, drap giường và khăn tắm luôn thơm tho.'],
    4: ['Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', 'Vệ sinh ổn, dọn phòng đúng giờ.'],
    3: ['Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.'],
    2: ['Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.'],
  },
  ROOM_QUALITY: {
    5: ['Phòng rộng rãi, nội thất hiện đại và đầy đủ tiện nghi.', 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.'],
    4: ['Phòng ổn, nội thất hơi cũ nhưng vẫn đầy đủ chức năng.', 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.'],
    3: ['Phòng ở mức tạm, một số thiết bị đã cũ.'],
    2: ['Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.'],
  },
  STAFF_SERVICE: {
    5: ['Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', 'Lễ tân thân thiện, check-in/check-out rất nhanh gọn.'],
    4: ['Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', 'Dịch vụ ổn, thái độ nhân viên dễ chịu.'],
    3: ['Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.'],
    2: ['Nhân viên phục vụ thiếu nhiệt tình, phản hồi chậm khi cần hỗ trợ.'],
  },
  LOCATION: {
    5: ['Vị trí cực kỳ thuận tiện, gần trung tâm và nhiều địa điểm ăn uống.', 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.'],
    4: ['Vị trí khá thuận tiện, chỉ hơi xa trung tâm một chút.', 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.'],
    3: ['Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.'],
    2: ['Vị trí không thuận tiện lắm, khá xa các điểm tham quan.'],
  },
  VALUE_FOR_MONEY: {
    5: ['Giá cả rất hợp lý so với chất lượng nhận được.', 'Xứng đáng từng đồng, quá đáng tiền.'],
    4: ['Giá hơi cao nhưng chất lượng tương xứng.', 'Mức giá tạm ổn so với dịch vụ nhận được.'],
    3: ['Giá cả ở mức bình thường so với chất lượng phòng.'],
    2: ['Giá không tương xứng với chất lượng thực tế nhận được.'],
  },
  AMENITIES: {
    5: ['Tiện nghi đầy đủ, hồ bơi và phòng gym rất chất lượng.', 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.'],
    4: ['Tiện nghi khá đầy đủ, chỉ thiếu vài dịch vụ nhỏ.', 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.'],
    3: ['Tiện nghi ở mức cơ bản, chưa có gì nổi bật.'],
    2: ['Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.'],
  },
  BREAKFAST: {
    5: ['Bữa sáng phong phú, nhiều món ngon và tươi.', 'Buffet sáng đa dạng, chất lượng món ăn rất tốt.'],
    4: ['Bữa sáng khá ổn, món ăn không quá đa dạng.', 'Bữa sáng tạm ổn, hợp khẩu vị.'],
    3: ['Bữa sáng đơn giản, ít lựa chọn món.'],
    2: ['Bữa sáng khá nghèo nàn, chất lượng chưa tốt.'],
  },
  WIFI: {
    5: ['Wifi mạnh và ổn định xuyên suốt thời gian lưu trú.', 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.'],
    4: ['Wifi ổn định ở khu vực sảnh, trong phòng đôi lúc hơi yếu.', 'Wifi dùng tạm ổn, tốc độ trung bình.'],
    3: ['Wifi khá yếu, thỉnh thoảng bị rớt mạng.'],
    2: ['Wifi rất yếu và hay bị mất kết nối, ảnh hưởng công việc.'],
  },
};

function pick(arr, idx) {
  return arr[idx % arr.length];
}

function rotate(arr, n) {
  const k = ((n % arr.length) + arr.length) % arr.length;
  return arr.slice(k).concat(arr.slice(0, k));
}

function sqlEscape(s) {
  return s.replace(/'/g, "''");
}

// So review linh hoat 8-13 (khong co dinh 13 nhu PLACE) de tranh moi hotel
// deu bang diem nhau y het.
function reviewCountFor(hotelIdx) {
  return 8 + (hotelIdx % 6); // 8,9,10,11,12,13,8,9,...
}

// Sinh mang rating co do dai `count` dua theo trong so RATING_PROFILE cua
// starRating gan nhat, roi rotate de thu tu khac nhau giua cac hotel.
function buildRatingPool(starRating, count, hotelIdx) {
  const profile = RATING_PROFILE[starRating] || RATING_PROFILE[3];
  const totalWeight = Object.values(profile).reduce((a, b) => a + b, 0);
  const pool = [];
  Object.entries(profile).forEach(([rating, weight]) => {
    const n = Math.round((weight / totalWeight) * count);
    for (let i = 0; i < n; i++) pool.push(Number(rating));
  });
  while (pool.length < count) pool.push(5);
  while (pool.length > count) pool.pop();
  return rotate(pool, hotelIdx);
}

function pickAspects(hotelIdx, userIdx, count) {
  const keys = Object.keys(ASPECTS);
  const start = (hotelIdx * 3 + userIdx) % keys.length;
  return rotate(keys, start).slice(0, count);
}

const data = JSON.parse(fs.readFileSync(SRC, 'utf8'));

const groups = {};
data.forEach((h) => {
  const key = h.destinationSlug;
  if (!groups[key]) groups[key] = [];
  groups[key].push(h);
});

let globalHotelIdx = 0;

Object.entries(groups).forEach(([destSlug, hotels]) => {
  const lines = [];
  lines.push(`-- Seed demo reviews HOTEL cho khu vuc: ${destSlug}`);
  lines.push('-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel');
  lines.push('-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)');
  lines.push('USE mravel_review;');
  lines.push('SET NAMES utf8mb4;');
  lines.push('');

  hotels.forEach((hotel) => {
    const name = hotel.name.vi;
    const targetId = hotel._id.$oid;
    const count = reviewCountFor(globalHotelIdx);
    const ratings = buildRatingPool(hotel.starRating, count, globalHotelIdx);
    const reviewers = rotate(USERS, globalHotelIdx).slice(0, count);

    lines.push(`-- ==== ${name} (${targetId}) | starRating=${hotel.starRating} | ${count} reviews ====`);

    reviewers.forEach((userId, userIdx) => {
      const rating = ratings[userIdx];
      const content = sqlEscape(pick(MAIN_TEMPLATES[rating], globalHotelIdx + userIdx).replace('{name}', name));
      const aspectCount = rating >= 4 ? 3 : 2;
      const aspectKeys = pickAspects(globalHotelIdx, userIdx, aspectCount);

      lines.push(`INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)`);
      lines.push(`VALUES (${userId}, '${targetId}', 'HOTEL', ${rating}, '${content}', NOW(6));`);
      lines.push('SET @rid := LAST_INSERT_ID();');
      const aspectRows = aspectKeys.map((key, i) => {
        const comment = sqlEscape(pick(ASPECT_COMMENTS[key][rating], globalHotelIdx + userIdx + i));
        return `(@rid, ${ASPECTS[key]}, '${comment}', NOW(6))`;
      });
      lines.push(`INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES`);
      lines.push(aspectRows.join(',\n') + ';');
      lines.push('');
    });

    globalHotelIdx += 1;
  });

  const outPath = path.join(OUT_DIR, `seed_reviews_hotel_${destSlug}.sql`);
  fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
  console.log('wrote', outPath, hotels.length, 'hotels');
});
