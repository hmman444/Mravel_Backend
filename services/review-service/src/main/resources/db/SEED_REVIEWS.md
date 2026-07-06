# Seed Review demo bằng SQL trực tiếp (review-service)

> Quy trình tạo hàng loạt `reviews` + `review_aspects` demo cho PLACE/HOTEL/RESTAURANT bằng
> INSERT SQL thẳng vào DB production, không đi qua API/UI. Dùng khi cần nhiều review cho
> demo báo cáo khóa luận mà tạo lịch trình + review thủ công qua UI quá chậm.

---

## 0. Bối cảnh

- Web đã deploy production. Review chỉ hiện nút bấm khi user "đã trải nghiệm" dịch vụ
  (`canReview` gate qua itinerary quá khứ) → seed thẳng DB để bỏ qua bước tạo lịch trình
  thủ công cho từng user.
- DB: MySQL `mravel_review` (review-service), 2 bảng liên quan: `reviews`, `review_aspects`.

---

## 1. Schema cần nhớ

| Bảng | Cột đáng chú ý | Ghi chú |
|---|---|---|
| `reviews` | `id` AUTO_INCREMENT | **Bỏ hẳn cột `id`** trong INSERT, để MySQL tự tăng (`GenerationType.IDENTITY`) |
| | `user_id`, `target_id` (string = Mongo ObjectId), `target_type` (`PLACE`/`HOTEL`/`RESTAURANT`) | |
| | `rating`, `content` | |
| | `created_at` NOT NULL | **Không có DB default** — cột này được set ở tầng code Java (`@PrePersist`), nên khi insert bằng SQL thô **phải tự đưa giá trị**, dùng `NOW(6)` |
| | `updated_at` | nullable, có thể bỏ qua |
| | Unique `(user_id, target_type, target_id)` | 1 user chỉ review được 1 lần / 1 target — kiểm tra kỹ khi seed nhiều batch để không trùng |
| `review_aspects` | `id` AUTO_INCREMENT | bỏ cột `id` như trên |
| | `review_id` FK, `aspect_definition_id` FK | id aspect tra trong `seed_aspect_definitions.sql` |
| | `comment` NOT NULL, `created_at` NOT NULL (không default) | dùng `NOW(6)` |
| | Unique `(review_id, aspect_definition_id)` | 1 review không lặp aspect |

**Trick gắn `review_id` mà không cần biết trước id:** chạy `SET @rid := LAST_INSERT_ID();`
ngay sau câu INSERT vào `reviews`, rồi dùng `@rid` cho các dòng `review_aspects` theo sau
(trong cùng session/transaction).

---

## 2. Nguồn data target (Mongo → JSON)

- Export collection Mongo catalog liên quan ra JSON, để cùng thư mục này:
  `mravel_catalog.places.json`, `mravel_catalog.hotels.json`, `mravel_catalog.restaurants.json`.
- Lấy `_id.$oid` → `target_id`; `name.vi` → chèn vào nội dung review cho tự nhiên.
- Với PLACE: mỗi document có `kind` = `DESTINATION` hoặc `POI`, POI có `parentSlug` trỏ về
  DESTINATION cha → group theo `parentSlug` (hoặc `slug` nếu chính nó là DESTINATION) để
  tách seed theo khu vực (1 file/khu vực), tránh 1 file dài hàng nghìn dòng.
- HOTEL/RESTAURANT thường có sẵn field vùng/tỉnh riêng (kiểm tra field thực tế trong JSON
  export trước khi group, có thể không giống cấu trúc `parentSlug` của PLACE).

---

## 3. Quy tắc phân bổ review

- Cố định 1 danh sách `user_id` dùng để seed (ví dụ đợt PLACE: 13 user
  `5,7,9,10,11,12,14,15,16,17,18,19,20`).
- Mỗi target được **toàn bộ** danh sách user review 1 lần → số review/target = số user.
- Tỉ lệ rating (N=13): **8×5 sao, 4×4 sao, 1×3 sao**. Nếu N khác thì giữ tỉ lệ tương đương
  (~60% 5 sao / ~30% 4 sao / ~10% 3 sao).
- Xoay vòng (rotate) mảng rating theo index của target để không phải lúc nào cũng cùng 1 user
  bị gán 3 sao trên mọi target (tránh lộ pattern giả).
- Aspect gắn theo category (tra id trong `seed_aspect_definitions.sql`: PLACE 17-22,
  HOTEL 1-8, RESTAURANT 9-16). Review 4-5 sao gắn 3 aspect (khen); review 3 sao gắn 2 aspect
  (1 trung tính + 1 chê nhẹ) để có vẻ thật.

---

## 4. Sinh file SQL bằng script Node (không viết tay)

Số lượng review lớn (N target × M user) nên dùng script Node đọc JSON export, group theo
khu vực/danh mục, rồi xoay vòng qua bộ **phrase pool** (câu mẫu theo mức sao + theo aspect)
để nội dung tự nhiên, đỡ lặp y hệt giữa các target.

- Script mẫu: `gen_reviews_place.js` (cùng thư mục) — đã dùng để sinh toàn bộ seed PLACE.
- Khi làm HOTEL/RESTAURANT: copy file này, đổi:
  - `SRC` → trỏ đúng file JSON export (hotels/restaurants)
  - `ASPECTS` → map code→id đúng theo category (xem `seed_aspect_definitions.sql`)
  - `MAIN_TEMPLATES`, `ASPECT_COMMENTS` → viết lại câu mẫu phù hợp ngữ cảnh (khách sạn/nhà hàng)
  - logic group theo khu vực (field group key có thể khác `parentSlug` của PLACE)
- Chạy: `node gen_reviews_hotel.js` (hoặc tên tương ứng) → tự ghi ra
  `seed_reviews_<category>_<khu-vuc>.sql` trong thư mục `db/`.

---

## 5. Cách import vào production

1. Mở connection production trong MySQL Workbench qua **SSH tunnel** (tab SSH khi tạo
   connection: host `103.163.215.17`, MySQL port `3306` trên chính server đó).
2. Mở file `.sql` cần chạy trong 1 Query tab → chạy nguyên file (icon tia sét / execute all).
3. **Không dùng** Table Data Export/Import wizard cho text tiếng Việt có dấu trên Windows
   → bug encoding `cp1252` khiến export CSV crash (`UnicodeEncodeError`).
4. **Không dùng** thao tác Copy Row (grid) → Paste Row (grid khác) → Apply → Workbench tự
   sinh sai literal (`b'...'` áp cho mọi cột kể cả text/enum) gây lỗi cú pháp SQL 1064.
   → Luôn ưu tiên chạy thẳng file `.sql` trong Query tab.
5. **Luôn có `USE mravel_review;`** ở đầu file (hoặc double-click schema `mravel_review`
   trong sidebar SCHEMAS để set active trước khi chạy) — nếu không sẽ gặp lỗi
   `Error Code: 1046. No database selected`.

---

## 6. Tổ chức thư mục

Mỗi category có 1 thư mục riêng trong `db/`, chứa: script generator (`gen_reviews_<category>.js`)
+ các file `seed_reviews_<category>_<khu-vuc>.sql` đã sinh ra:

- `db/place/` — PLACE (script `gen_reviews_place.js`)
- `db/hotel/` — HOTEL (script `gen_reviews_hotel.js`)
- `db/restaurant/` — RESTAURANT (script `gen_reviews_restaurant.js`)

File JSON export Mongo (`mravel_catalog.*.json`) và `seed_aspect_definitions.sql` vẫn để ở
`db/` gốc vì dùng chung.

---

## 7. Trạng thái đã seed

- **PLACE** — xong (`db/place/`), 6 file theo khu vực, mỗi khu vực = DESTINATION cha + POI
  con, **13 review/target cố định** (8×5 sao, 4×4 sao, 1×3 sao, xoay vòng theo index target):
  `seed_reviews_place_ha-noi.sql`, `..._sa-pa.sql`, `..._da-nang.sql`,
  `..._ho-chi-minh-city.sql`, `..._hoi-an.sql`, `..._nha-trang.sql`.
- **HOTEL** — xong (`db/hotel/`), 6 file theo `destinationSlug` (5 hotel/khu vực, 30 hotel
  tổng). Khác PLACE: **số review/hotel linh hoạt 8-13** (`reviewCountFor` trong script) và
  **tỉ lệ sao bám theo `starRating` thật của hotel** (`RATING_PROFILE`) thay vì cố định
  8-4-1 cho mọi hotel — hotel 2-3 sao có nhiều review 3-4 sao và vài review 2 sao hơn,
  hotel 4-5 sao thiên về 5 sao — tránh hiệu ứng "hotel nào cũng bằng điểm nhau".
- **RESTAURANT** — xong (`db/restaurant/`), 4 file theo `destinationSlug` (chỉ 4 khu vực có
  data: hoi-an 3, ho-chi-minh-city 36, ha-noi 23, da-nang 25 — không có nha-trang/sa-pa).
  Giống HOTEL: số review linh hoạt 8-13. Restaurant không có `starRating` nên dùng
  `priceLevel` (LUXURY/EXPENSIVE/MODERATE/CHEAP) làm tín hiệu thay thế cho tỉ lệ sao.

---

## 8. Sync ngược catalog-service (avgRating/reviewsCount/topAspects) — ĐÃ XONG

Seed thẳng SQL vào MySQL của review-service **không** kích hoạt
`ReviewService.syncRatingToCatalog()` (chỉ chạy khi tạo/sửa/xoá review qua API — xem
[ReviewService.java:323-343](../../../../../../review-service/src/main/java/com/mravel/review/service/ReviewService.java)).
Hệ quả: field `avgRating`/`reviewsCount`/`reviewStats.keywords` trong document Mongo của
catalog-service (và Elasticsearch index) vẫn giữ giá trị mặc định (0) cho toàn bộ
PLACE/HOTEL/RESTAURANT đã seed — làm hotel/restaurant detail page (khối
`HotelMainInfoPanel`/tương đương) và card list `/hotels`, `/restaurants`, `/places` hiển thị
sai (0.0 sao) dù khối `ReviewSummary` (đọc trực tiếp review-service) vẫn đúng.

**Đã fix (2026-07-07):** chạy 2 câu SQL tổng hợp trực tiếp trong container
`mravel-mysql` trên server (qua `docker exec`, lấy credential từ `/opt/mravel/.env`) để tính
`avgRating`, `reviewsCount`, top 5 `aspect_definition_id` mỗi target (đúng logic
`syncRatingToCatalog`), rồi gọi `PUT https://mravel.info.vn/api/catalog/{hotels|restaurants|places}/{id}/rating-sync`
(endpoint public, `permitAll`) cho toàn bộ **171 target** (30 hotel + 87 restaurant + 54 place).
Kết quả 171/171 thành công — đã verify lại qua API thật (`GET /api/catalog/hotels/{slug}`)
cho vài mẫu, `avgRating`/`reviewsCount`/`keywords` hiển thị đúng.

Lưu ý cho lần seed sau: **luôn phải chạy lại bước sync này** sau khi insert SQL trực tiếp,
vì nó không tự động như khi tạo review qua API thật.
