const fs = require('fs');
const path = require('path');

const SRC = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/mravel_catalog.restaurants.json';
const OUT_DIR = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/restaurant';

const USERS = [5, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20];

const ASPECTS = {
  FOOD_QUALITY: 9,
  SERVICE: 10,
  AMBIANCE: 11,
  PRICE: 12,
  HYGIENE: 13,
  PORTION_SIZE: 14,
  WAIT_TIME: 15,
  LOCATION: 16,
};

// Restaurant khong co starRating nhu hotel -> dung priceLevel lam tin hieu
// chat luong thay the (LUXURY/EXPENSIVE thien ve diem cao hon MODERATE/CHEAP).
const PRICE_LEVEL_TIER = {
  LUXURY: 5,
  EXPENSIVE: 4,
  MODERATE: 3,
  CHEAP: 2,
};

const RATING_PROFILE = {
  5: { 5: 8, 4: 3, 3: 1 },
  4: { 5: 6, 4: 4, 3: 2 },
  3: { 5: 4, 4: 4, 3: 3, 2: 1 },
  2: { 5: 3, 4: 4, 3: 3, 2: 2 },
};

const MAIN_TEMPLATES = {
  5: [
    '{name} thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.',
    'Bữa ăn tại {name} vượt xa mong đợi, chắc chắn sẽ quay lại.',
    '{name} xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.',
    'Trải nghiệm ẩm thực ở {name} rất đáng nhớ, sẽ giới thiệu cho bạn bè.',
    'Không có gì để chê ở {name}, quá hài lòng với bữa ăn này.',
    '{name} ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.',
    'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn {name}.',
    'Rất hài lòng với {name}, món ăn tươi ngon và phục vụ chuyên nghiệp.',
  ],
  4: [
    '{name} khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.',
    'Nhìn chung ở {name} ổn, tuy còn một vài bất tiện nhỏ về phục vụ.',
    '{name} đáng thử, dù không phải món nào cũng hoàn hảo.',
    'Khá hài lòng với bữa ăn tại {name}, sẽ cân nhắc quay lại.',
    '{name} ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.',
    'Trải nghiệm tốt tại {name}, phù hợp cho bữa ăn cùng bạn bè.',
  ],
  3: [
    '{name} tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.',
    'Trải nghiệm ở {name} bình thường, có vài điểm cần cải thiện.',
    '{name} cũng tạm được nhưng không có gì quá đặc biệt.',
    'Mình thấy {name} ở mức tạm được, không tệ nhưng cũng không xuất sắc.',
  ],
  2: [
    '{name} chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.',
    'Trải nghiệm ở {name} không được như mong đợi, hơi thất vọng.',
    '{name} có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.',
  ],
};

const ASPECT_COMMENTS = {
  FOOD_QUALITY: {
    5: ['Món ăn tươi ngon, chế biến rất khéo và đậm đà.', 'Hương vị món ăn xuất sắc, đúng chuẩn đặc sản.'],
    4: ['Món ăn khá ngon, một vài món chưa thực sự nổi bật.', 'Chất lượng món ăn ổn, hợp khẩu vị số đông.'],
    3: ['Món ăn ở mức tạm, chưa có gì đặc sắc.'],
    2: ['Chất lượng món ăn chưa tốt, một số món khá nhạt.'],
  },
  SERVICE: {
    5: ['Nhân viên phục vụ nhanh nhẹn và chu đáo.', 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.'],
    4: ['Phục vụ khá tốt, đôi lúc hơi chậm vào giờ đông khách.', 'Nhân viên thân thiện, hỗ trợ nhiệt tình.'],
    3: ['Phục vụ ở mức bình thường, chưa thực sự chủ động.'],
    2: ['Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.'],
  },
  AMBIANCE: {
    5: ['Không gian đẹp và thoáng, rất hợp để chụp ảnh.', 'Không khí ấm cúng, decor tinh tế và dễ chịu.'],
    4: ['Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', 'Không khí dễ chịu, phù hợp cho nhóm bạn/gia đình.'],
    3: ['Không gian bình thường, không có gì nổi bật.'],
    2: ['Không gian khá chật và ồn, chưa thoải mái lắm.'],
  },
  PRICE: {
    5: ['Giá cả rất hợp lý so với chất lượng món ăn.', 'Xứng đáng từng đồng, quá đáng tiền.'],
    4: ['Giá hơi cao nhưng chất lượng tương xứng.', 'Mức giá tạm ổn so với khẩu phần nhận được.'],
    3: ['Giá cả ở mức bình thường so với chất lượng món.'],
    2: ['Giá không tương xứng với chất lượng thực tế nhận được.'],
  },
  HYGIENE: {
    5: ['Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', 'Không gian sạch sẽ, yên tâm khi dùng bữa.'],
    4: ['Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', 'Vệ sinh chấp nhận được, bàn ghế được lau dọn thường xuyên.'],
    3: ['Vệ sinh ở mức tạm, chưa thực sự kỹ.'],
    2: ['Vệ sinh chưa tốt, cần cải thiện thêm.'],
  },
  PORTION_SIZE: {
    5: ['Khẩu phần đầy đặn, ăn no và đúng giá tiền.', 'Phần ăn hào phóng, rất đáng để quay lại.'],
    4: ['Khẩu phần ổn, vừa đủ cho một bữa ăn bình thường.', 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.'],
    3: ['Khẩu phần hơi ít so với mong đợi.'],
    2: ['Khẩu phần khá ít so với mức giá phải trả.'],
  },
  WAIT_TIME: {
    5: ['Món lên nhanh, không phải chờ đợi lâu.', 'Tốc độ phục vụ rất nhanh dù đông khách.'],
    4: ['Thời gian chờ món ở mức chấp nhận được.', 'Chờ hơi lâu vào giờ cao điểm nhưng vẫn ổn.'],
    3: ['Thời gian chờ món khá lâu, cần cải thiện.'],
    2: ['Chờ món quá lâu, ảnh hưởng trải nghiệm chung.'],
  },
  LOCATION: {
    5: ['Vị trí thuận tiện, dễ tìm và có chỗ để xe.', 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.'],
    4: ['Vị trí khá thuận tiện, chỉ hơi khó tìm chỗ đậu xe.', 'Vị trí ổn, đi lại khá dễ dàng.'],
    3: ['Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.'],
    2: ['Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.'],
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

function reviewCountFor(idx) {
  return 8 + (idx % 6); // 8,9,10,11,12,13,8,9,...
}

function buildRatingPool(tier, count, idx) {
  const profile = RATING_PROFILE[tier] || RATING_PROFILE[3];
  const totalWeight = Object.values(profile).reduce((a, b) => a + b, 0);
  const pool = [];
  Object.entries(profile).forEach(([rating, weight]) => {
    const n = Math.round((weight / totalWeight) * count);
    for (let i = 0; i < n; i++) pool.push(Number(rating));
  });
  while (pool.length < count) pool.push(5);
  while (pool.length > count) pool.pop();
  return rotate(pool, idx);
}

function pickAspects(idx, userIdx, count) {
  const keys = Object.keys(ASPECTS);
  const start = (idx * 3 + userIdx) % keys.length;
  return rotate(keys, start).slice(0, count);
}

const data = JSON.parse(fs.readFileSync(SRC, 'utf8'));

const groups = {};
data.forEach((r) => {
  const key = r.destinationSlug;
  if (!groups[key]) groups[key] = [];
  groups[key].push(r);
});

let globalIdx = 0;

Object.entries(groups).forEach(([destSlug, restaurants]) => {
  const lines = [];
  lines.push(`-- Seed demo reviews RESTAURANT cho khu vuc: ${destSlug}`);
  lines.push('-- So review/restaurant linh hoat (8-13), phan bo sao theo priceLevel (thay the vi khong co starRating)');
  lines.push('-- Aspect id theo seed_aspect_definitions.sql (RESTAURANT: 9 FOOD_QUALITY,10 SERVICE,11 AMBIANCE,12 PRICE,13 HYGIENE,14 PORTION_SIZE,15 WAIT_TIME,16 LOCATION)');
  lines.push('USE mravel_review;');
  lines.push('SET NAMES utf8mb4;');
  lines.push('');

  restaurants.forEach((restaurant) => {
    const name = restaurant.name.vi;
    const targetId = restaurant._id.$oid;
    const tier = PRICE_LEVEL_TIER[restaurant.priceLevel] || 3;
    const count = reviewCountFor(globalIdx);
    const ratings = buildRatingPool(tier, count, globalIdx);
    const reviewers = rotate(USERS, globalIdx).slice(0, count);

    lines.push(`-- ==== ${name} (${targetId}) | priceLevel=${restaurant.priceLevel} | ${count} reviews ====`);

    reviewers.forEach((userId, userIdx) => {
      const rating = ratings[userIdx];
      const content = sqlEscape(pick(MAIN_TEMPLATES[rating], globalIdx + userIdx).replace('{name}', name));
      const aspectCount = rating >= 4 ? 3 : 2;
      const aspectKeys = pickAspects(globalIdx, userIdx, aspectCount);

      lines.push(`INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)`);
      lines.push(`VALUES (${userId}, '${targetId}', 'RESTAURANT', ${rating}, '${content}', NOW(6));`);
      lines.push('SET @rid := LAST_INSERT_ID();');
      const aspectRows = aspectKeys.map((key, i) => {
        const comment = sqlEscape(pick(ASPECT_COMMENTS[key][rating], globalIdx + userIdx + i));
        return `(@rid, ${ASPECTS[key]}, '${comment}', NOW(6))`;
      });
      lines.push(`INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES`);
      lines.push(aspectRows.join(',\n') + ';');
      lines.push('');
    });

    globalIdx += 1;
  });

  const outPath = path.join(OUT_DIR, `seed_reviews_restaurant_${destSlug}.sql`);
  fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
  console.log('wrote', outPath, restaurants.length, 'restaurants');
});
