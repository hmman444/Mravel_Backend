-- Seed demo reviews PLACE cho khu vực: nha-trang
-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)
-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)
SET NAMES utf8mb4;

-- ==== Nha Trang (6a1ba808396a1b2be58bfe5f) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Nha Trang đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Chuyến đi tới Nha Trang là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba808396a1b2be58bfe5f', 'PLACE', 4, 'Trải nghiệm ổn tại Nha Trang, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba808396a1b2be58bfe5f', 'PLACE', 4, 'Nha Trang khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba808396a1b2be58bfe5f', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Nha Trang tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba808396a1b2be58bfe5f', 'PLACE', 4, 'Nha Trang đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba808396a1b2be58bfe5f', 'PLACE', 3, 'Mình thấy Nha Trang ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Không thể chê điểm nào ở Nha Trang, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Nha Trang đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Chuyến đi tới Nha Trang là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Rất hài lòng khi đến Nha Trang, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Nha Trang thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba808396a1b2be58bfe5f', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Nha Trang.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Tháp Trầm Hương (Quảng trường 2/4) (6a1ba7fa396a1b2be58bfdff) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Chuyến đi tới Tháp Trầm Hương (Quảng trường 2/4) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 4, 'Trải nghiệm ổn tại Tháp Trầm Hương (Quảng trường 2/4), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 4, 'Tháp Trầm Hương (Quảng trường 2/4) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Tháp Trầm Hương (Quảng trường 2/4) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 4, 'Tháp Trầm Hương (Quảng trường 2/4) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 3, 'Mình thấy Tháp Trầm Hương (Quảng trường 2/4) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Không thể chê điểm nào ở Tháp Trầm Hương (Quảng trường 2/4), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Tháp Trầm Hương (Quảng trường 2/4) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Chuyến đi tới Tháp Trầm Hương (Quảng trường 2/4) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Rất hài lòng khi đến Tháp Trầm Hương (Quảng trường 2/4), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Tháp Trầm Hương (Quảng trường 2/4) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Tháp Trầm Hương (Quảng trường 2/4).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fa396a1b2be58bfdff', 'PLACE', 5, 'Tháp Trầm Hương (Quảng trường 2/4) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Đảo Điệp Sơn (Con đường trên biển) (6a1ba7fa396a1b2be58bfe03) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 4, 'Trải nghiệm ổn tại Đảo Điệp Sơn (Con đường trên biển), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 4, 'Đảo Điệp Sơn (Con đường trên biển) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Đảo Điệp Sơn (Con đường trên biển) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 4, 'Đảo Điệp Sơn (Con đường trên biển) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 3, 'Mình thấy Đảo Điệp Sơn (Con đường trên biển) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Không thể chê điểm nào ở Đảo Điệp Sơn (Con đường trên biển), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Đảo Điệp Sơn (Con đường trên biển) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Chuyến đi tới Đảo Điệp Sơn (Con đường trên biển) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Rất hài lòng khi đến Đảo Điệp Sơn (Con đường trên biển), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Đảo Điệp Sơn (Con đường trên biển) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Đảo Điệp Sơn (Con đường trên biển).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Đảo Điệp Sơn (Con đường trên biển) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fa396a1b2be58bfe03', 'PLACE', 5, 'Trải nghiệm tại Đảo Điệp Sơn (Con đường trên biển) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Suối Ba Hồ (Ba Hồ Waterfalls) (6a1ba7fb396a1b2be58bfe04) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 4, 'Suối Ba Hồ (Ba Hồ Waterfalls) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Suối Ba Hồ (Ba Hồ Waterfalls) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 4, 'Suối Ba Hồ (Ba Hồ Waterfalls) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 3, 'Mình thấy Suối Ba Hồ (Ba Hồ Waterfalls) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Không thể chê điểm nào ở Suối Ba Hồ (Ba Hồ Waterfalls), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Suối Ba Hồ (Ba Hồ Waterfalls) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Chuyến đi tới Suối Ba Hồ (Ba Hồ Waterfalls) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Rất hài lòng khi đến Suối Ba Hồ (Ba Hồ Waterfalls), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Suối Ba Hồ (Ba Hồ Waterfalls) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Suối Ba Hồ (Ba Hồ Waterfalls).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Suối Ba Hồ (Ba Hồ Waterfalls) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 5, 'Trải nghiệm tại Suối Ba Hồ (Ba Hồ Waterfalls) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fb396a1b2be58bfe04', 'PLACE', 4, 'Suối Ba Hồ (Ba Hồ Waterfalls) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Đồng muối Hòn Khói (Ninh Hòa) (6a1ba7fb396a1b2be58bfe05) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Đồng muối Hòn Khói (Ninh Hòa) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 4, 'Đồng muối Hòn Khói (Ninh Hòa) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 3, 'Mình thấy Đồng muối Hòn Khói (Ninh Hòa) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Không thể chê điểm nào ở Đồng muối Hòn Khói (Ninh Hòa), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Đồng muối Hòn Khói (Ninh Hòa) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Chuyến đi tới Đồng muối Hòn Khói (Ninh Hòa) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Rất hài lòng khi đến Đồng muối Hòn Khói (Ninh Hòa), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Đồng muối Hòn Khói (Ninh Hòa) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Đồng muối Hòn Khói (Ninh Hòa).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Đồng muối Hòn Khói (Ninh Hòa) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 5, 'Trải nghiệm tại Đồng muối Hòn Khói (Ninh Hòa) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 4, 'Đồng muối Hòn Khói (Ninh Hòa) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fb396a1b2be58bfe05', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Đồng muối Hòn Khói (Ninh Hòa) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Vịnh Ninh Vân (Ninh Vân Bay) (6a1ba7fb396a1b2be58bfe06) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 4, 'Vịnh Ninh Vân (Ninh Vân Bay) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 3, 'Mình thấy Vịnh Ninh Vân (Ninh Vân Bay) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Không thể chê điểm nào ở Vịnh Ninh Vân (Ninh Vân Bay), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Vịnh Ninh Vân (Ninh Vân Bay) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Chuyến đi tới Vịnh Ninh Vân (Ninh Vân Bay) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Rất hài lòng khi đến Vịnh Ninh Vân (Ninh Vân Bay), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Vịnh Ninh Vân (Ninh Vân Bay) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Vịnh Ninh Vân (Ninh Vân Bay).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Vịnh Ninh Vân (Ninh Vân Bay) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 5, 'Trải nghiệm tại Vịnh Ninh Vân (Ninh Vân Bay) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 4, 'Vịnh Ninh Vân (Ninh Vân Bay) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Vịnh Ninh Vân (Ninh Vân Bay) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fb396a1b2be58bfe06', 'PLACE', 4, 'Vịnh Ninh Vân (Ninh Vân Bay) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Biển Dốc Lết (Ninh Hòa) (6a1ba7fa396a1b2be58bfe02) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 3, 'Mình thấy Biển Dốc Lết (Ninh Hòa) ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Không thể chê điểm nào ở Biển Dốc Lết (Ninh Hòa), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Biển Dốc Lết (Ninh Hòa) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Chuyến đi tới Biển Dốc Lết (Ninh Hòa) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Rất hài lòng khi đến Biển Dốc Lết (Ninh Hòa), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Biển Dốc Lết (Ninh Hòa) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Biển Dốc Lết (Ninh Hòa).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Biển Dốc Lết (Ninh Hòa) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 5, 'Trải nghiệm tại Biển Dốc Lết (Ninh Hòa) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 4, 'Biển Dốc Lết (Ninh Hòa) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Biển Dốc Lết (Ninh Hòa) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 4, 'Biển Dốc Lết (Ninh Hòa) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fa396a1b2be58bfe02', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Biển Dốc Lết (Ninh Hòa), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Đường chân trời Bãi Dài (Cam Ranh) (6a1ba7fa396a1b2be58bfe01) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Không thể chê điểm nào ở Đường chân trời Bãi Dài (Cam Ranh), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Đường chân trời Bãi Dài (Cam Ranh) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Chuyến đi tới Đường chân trời Bãi Dài (Cam Ranh) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Rất hài lòng khi đến Đường chân trời Bãi Dài (Cam Ranh), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Đường chân trời Bãi Dài (Cam Ranh) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Đường chân trời Bãi Dài (Cam Ranh).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Đường chân trời Bãi Dài (Cam Ranh) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 5, 'Trải nghiệm tại Đường chân trời Bãi Dài (Cam Ranh) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 4, 'Đường chân trời Bãi Dài (Cam Ranh) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Đường chân trời Bãi Dài (Cam Ranh) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 4, 'Đường chân trời Bãi Dài (Cam Ranh) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Đường chân trời Bãi Dài (Cam Ranh), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fa396a1b2be58bfe01', 'PLACE', 3, 'Đường chân trời Bãi Dài (Cam Ranh) tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

-- ==== Chùa Long Sơn (Tượng Phật Trắng) (6a1ba7fa396a1b2be58bfe00) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Chùa Long Sơn (Tượng Phật Trắng) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Chuyến đi tới Chùa Long Sơn (Tượng Phật Trắng) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Rất hài lòng khi đến Chùa Long Sơn (Tượng Phật Trắng), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Chùa Long Sơn (Tượng Phật Trắng) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Chùa Long Sơn (Tượng Phật Trắng).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Chùa Long Sơn (Tượng Phật Trắng) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Trải nghiệm tại Chùa Long Sơn (Tượng Phật Trắng) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 4, 'Chùa Long Sơn (Tượng Phật Trắng) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Chùa Long Sơn (Tượng Phật Trắng) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 4, 'Chùa Long Sơn (Tượng Phật Trắng) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Chùa Long Sơn (Tượng Phật Trắng), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 3, 'Chùa Long Sơn (Tượng Phật Trắng) tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7fa396a1b2be58bfe00', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Chùa Long Sơn (Tượng Phật Trắng).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));
