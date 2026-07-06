const fs = require('fs');
const path = require('path');

const SRC = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db/mravel_catalog.places.json';
const OUT_DIR = 'e:/TLCN/Mravel_Backend/services/review-service/src/main/resources/db';

const USERS = [5, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20];
// 8x5 sao, 4x4 sao, 1x3 sao = 13
const RATE_TEMPLATE = [5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 3];

const ASPECTS = {
  SCENERY: 17,
  ACCESSIBILITY: 18,
  FACILITIES: 19,
  CROWDS: 20,
  VALUE: 21,
  SAFETY: 22,
};

const MAIN_TEMPLATES = {
  5: [
    '{name} thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.',
    'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại {name}.',
    '{name} xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.',
    'Trải nghiệm tại {name} tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.',
    'Không thể chê điểm nào ở {name}, quá xứng đáng để ghé thăm ít nhất một lần.',
    '{name} đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.',
    'Chuyến đi tới {name} là một trong những kỷ niệm đẹp nhất của mình.',
    'Rất hài lòng khi đến {name}, không gian và trải nghiệm đều trên cả tuyệt vời.',
  ],
  4: [
    '{name} khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.',
    'Nhìn chung trải nghiệm ở {name} tốt, tuy còn một vài bất tiện nhỏ.',
    '{name} đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.',
    'Khá hài lòng với chuyến tham quan {name}, sẽ cân nhắc quay lại.',
    '{name} đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.',
    'Trải nghiệm ổn tại {name}, phù hợp cho một chuyến đi ngắn ngày.',
  ],
  3: [
    '{name} tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.',
    'Trải nghiệm ở {name} bình thường, có vài điểm cần cải thiện.',
    '{name} cũng đáng xem một lần nhưng không có gì quá đặc biệt.',
    'Mình thấy {name} ở mức tạm được, không tệ nhưng cũng không xuất sắc.',
  ],
};

const ASPECT_COMMENTS = {
  SCENERY: {
    5: ['Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', 'View tuyệt đẹp, nhất là vào lúc sáng sớm hoặc hoàng hôn.'],
    4: ['Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', 'View ổn, có điều thời tiết hôm mình đi hơi âm u.'],
    3: ['Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.'],
  },
  ACCESSIBILITY: {
    5: ['Đường đi thuận tiện, biển chỉ dẫn rõ ràng dễ tìm.', 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.'],
    4: ['Đường vào hơi khó tìm lúc đầu nhưng chỉ dẫn khá rõ.', 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.'],
    3: ['Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.'],
  },
  FACILITIES: {
    5: ['Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', 'Có đầy đủ bảng thông tin và dịch vụ hỗ trợ khách tham quan.'],
    4: ['Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', 'Dịch vụ hỗ trợ khá ổn nhưng số lượng nhà vệ sinh còn ít.'],
    3: ['Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.'],
  },
  CROWDS: {
    5: ['Không gian rộng nên dù đông khách vẫn không thấy ngột ngạt.', 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.'],
    4: ['Cuối tuần khá đông khách, phải chờ một chút để chụp ảnh.', 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.'],
    3: ['Lượng khách quá đông, chen chúc khá mệt khi tham quan.'],
  },
  VALUE: {
    5: ['Giá vé rất hợp lý so với trải nghiệm nhận được.', 'Xứng đáng từng đồng bỏ ra, quá đáng tiền.'],
    4: ['Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', 'Mức giá tạm ổn, phù hợp với chất lượng dịch vụ.'],
    3: ['Giá vé khá cao so với những gì nhận lại được.'],
  },
  SAFETY: {
    5: ['An ninh tốt, có bảo vệ và camera giám sát khắp nơi.', 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.'],
    4: ['An ninh ổn nhưng buổi tối đèn hơi thiếu sáng.', 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.'],
    3: ['Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.'],
  },
};

function pick(arr, idx) {
  return arr[idx % arr.length];
}

function rotate(arr, n) {
  const k = n % arr.length;
  return arr.slice(k).concat(arr.slice(0, k));
}

function pickAspects(placeIdx, userIdx, count) {
  const keys = Object.keys(ASPECTS);
  const start = (placeIdx * 3 + userIdx) % keys.length;
  const rotated = rotate(keys, start);
  return rotated.slice(0, count);
}

function sqlEscape(s) {
  return s.replace(/'/g, "''");
}

const data = JSON.parse(fs.readFileSync(SRC, 'utf8'));

const groups = {};
data.forEach((p) => {
  const key = p.kind === 'DESTINATION' ? p.slug : p.parentSlug;
  if (!groups[key]) groups[key] = [];
  groups[key].push(p);
});

// order: destination doc first, then POIs by original order
Object.keys(groups).forEach((key) => {
  groups[key].sort((a, b) => (a.kind === 'DESTINATION' ? -1 : b.kind === 'DESTINATION' ? 1 : 0));
});

let globalPlaceIdx = 0;

Object.entries(groups).forEach(([destSlug, places]) => {
  const lines = [];
  lines.push(`-- Seed demo reviews PLACE cho khu vực: ${destSlug}`);
  lines.push('-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)');
  lines.push('-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)');
  lines.push('SET NAMES utf8mb4;');
  lines.push('');

  places.forEach((place) => {
    const name = place.name.vi;
    const targetId = place._id.$oid;
    const ratings = rotate(RATE_TEMPLATE, globalPlaceIdx);

    lines.push(`-- ==== ${name} (${targetId}) ====`);

    USERS.forEach((userId, userIdx) => {
      const rating = ratings[userIdx];
      const content = sqlEscape(pick(MAIN_TEMPLATES[rating], globalPlaceIdx + userIdx).replace('{name}', name));
      const aspectCount = rating >= 4 ? 3 : 2;
      const aspectKeys = pickAspects(globalPlaceIdx, userIdx, aspectCount);

      lines.push(`INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)`);
      lines.push(`VALUES (${userId}, '${targetId}', 'PLACE', ${rating}, '${content}', NOW(6));`);
      lines.push('SET @rid := LAST_INSERT_ID();');
      const aspectRows = aspectKeys.map((key, i) => {
        const comment = sqlEscape(pick(ASPECT_COMMENTS[key][rating], globalPlaceIdx + userIdx + i));
        return `(@rid, ${ASPECTS[key]}, '${comment}', NOW(6))`;
      });
      lines.push(`INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES`);
      lines.push(aspectRows.join(',\n') + ';');
      lines.push('');
    });

    globalPlaceIdx += 1;
  });

  const outPath = path.join(OUT_DIR, `seed_reviews_place_${destSlug}.sql`);
  fs.writeFileSync(outPath, lines.join('\n'), 'utf8');
  console.log('wrote', outPath, places.length, 'places');
});
