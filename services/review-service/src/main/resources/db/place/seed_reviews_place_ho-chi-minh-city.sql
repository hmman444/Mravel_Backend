-- Seed demo reviews PLACE cho khu vực: ho-chi-minh-city
-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)
-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)
SET NAMES utf8mb4;

-- ==== TP. Hồ Chí Minh (6a1ba807396a1b2be58bfe5d) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Trải nghiệm tại TP. Hồ Chí Minh tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Không thể chê điểm nào ở TP. Hồ Chí Minh, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'TP. Hồ Chí Minh đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Chuyến đi tới TP. Hồ Chí Minh là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Rất hài lòng khi đến TP. Hồ Chí Minh, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'TP. Hồ Chí Minh thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại TP. Hồ Chí Minh.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba807396a1b2be58bfe5d', 'PLACE', 4, 'TP. Hồ Chí Minh đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba807396a1b2be58bfe5d', 'PLACE', 4, 'Trải nghiệm ổn tại TP. Hồ Chí Minh, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba807396a1b2be58bfe5d', 'PLACE', 4, 'TP. Hồ Chí Minh khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba807396a1b2be58bfe5d', 'PLACE', 4, 'Nhìn chung trải nghiệm ở TP. Hồ Chí Minh tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba807396a1b2be58bfe5d', 'PLACE', 3, 'TP. Hồ Chí Minh cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba807396a1b2be58bfe5d', 'PLACE', 5, 'Rất hài lòng khi đến TP. Hồ Chí Minh, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Nhà thờ Đức Bà Sài Gòn (6a1ba7f7396a1b2be58bfdef) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Không thể chê điểm nào ở Nhà thờ Đức Bà Sài Gòn, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Nhà thờ Đức Bà Sài Gòn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Chuyến đi tới Nhà thờ Đức Bà Sài Gòn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Rất hài lòng khi đến Nhà thờ Đức Bà Sài Gòn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Nhà thờ Đức Bà Sài Gòn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Nhà thờ Đức Bà Sài Gòn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 4, 'Nhà thờ Đức Bà Sài Gòn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 4, 'Trải nghiệm ổn tại Nhà thờ Đức Bà Sài Gòn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 4, 'Nhà thờ Đức Bà Sài Gòn khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Nhà thờ Đức Bà Sài Gòn tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 3, 'Nhà thờ Đức Bà Sài Gòn cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Rất hài lòng khi đến Nhà thờ Đức Bà Sài Gòn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f7396a1b2be58bfdef', 'PLACE', 5, 'Nhà thờ Đức Bà Sài Gòn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Chợ Bến Thành (6a1ba7f7396a1b2be58bfdf1) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Chợ Bến Thành đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Chuyến đi tới Chợ Bến Thành là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Rất hài lòng khi đến Chợ Bến Thành, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Chợ Bến Thành thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Chợ Bến Thành.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 4, 'Chợ Bến Thành đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 4, 'Trải nghiệm ổn tại Chợ Bến Thành, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 4, 'Chợ Bến Thành khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Chợ Bến Thành tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 3, 'Chợ Bến Thành cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Rất hài lòng khi đến Chợ Bến Thành, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Chợ Bến Thành thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f7396a1b2be58bfdf1', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Chợ Bến Thành.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Phố đi bộ Nguyễn Huệ (6a1ba7f8396a1b2be58bfdf2) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Chuyến đi tới Phố đi bộ Nguyễn Huệ là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Rất hài lòng khi đến Phố đi bộ Nguyễn Huệ, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Phố đi bộ Nguyễn Huệ thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Phố đi bộ Nguyễn Huệ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 4, 'Phố đi bộ Nguyễn Huệ đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 4, 'Trải nghiệm ổn tại Phố đi bộ Nguyễn Huệ, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 4, 'Phố đi bộ Nguyễn Huệ khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Phố đi bộ Nguyễn Huệ tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 3, 'Phố đi bộ Nguyễn Huệ cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Rất hài lòng khi đến Phố đi bộ Nguyễn Huệ, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Phố đi bộ Nguyễn Huệ thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Phố đi bộ Nguyễn Huệ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f8396a1b2be58bfdf2', 'PLACE', 5, 'Phố đi bộ Nguyễn Huệ xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Bưu điện Trung tâm Sài Gòn (6a1ba7f7396a1b2be58bfdf0) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Rất hài lòng khi đến Bưu điện Trung tâm Sài Gòn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Bưu điện Trung tâm Sài Gòn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bưu điện Trung tâm Sài Gòn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 4, 'Bưu điện Trung tâm Sài Gòn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 4, 'Trải nghiệm ổn tại Bưu điện Trung tâm Sài Gòn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 4, 'Bưu điện Trung tâm Sài Gòn khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Bưu điện Trung tâm Sài Gòn tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 3, 'Bưu điện Trung tâm Sài Gòn cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Rất hài lòng khi đến Bưu điện Trung tâm Sài Gòn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Bưu điện Trung tâm Sài Gòn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bưu điện Trung tâm Sài Gòn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Bưu điện Trung tâm Sài Gòn xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f7396a1b2be58bfdf0', 'PLACE', 5, 'Trải nghiệm tại Bưu điện Trung tâm Sài Gòn tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Bảo tàng Chứng tích Chiến tranh (6a1ba7f8396a1b2be58bfdf4) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Bảo tàng Chứng tích Chiến tranh thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bảo tàng Chứng tích Chiến tranh.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 4, 'Bảo tàng Chứng tích Chiến tranh đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 4, 'Trải nghiệm ổn tại Bảo tàng Chứng tích Chiến tranh, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 4, 'Bảo tàng Chứng tích Chiến tranh khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Bảo tàng Chứng tích Chiến tranh tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 3, 'Bảo tàng Chứng tích Chiến tranh cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Rất hài lòng khi đến Bảo tàng Chứng tích Chiến tranh, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Bảo tàng Chứng tích Chiến tranh thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bảo tàng Chứng tích Chiến tranh.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Bảo tàng Chứng tích Chiến tranh xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Trải nghiệm tại Bảo tàng Chứng tích Chiến tranh tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f8396a1b2be58bfdf4', 'PLACE', 5, 'Không thể chê điểm nào ở Bảo tàng Chứng tích Chiến tranh, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Dinh Độc Lập (Hội trường Thống Nhất) (6a1ba7f8396a1b2be58bfdf3) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Dinh Độc Lập (Hội trường Thống Nhất).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 4, 'Dinh Độc Lập (Hội trường Thống Nhất) đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 4, 'Trải nghiệm ổn tại Dinh Độc Lập (Hội trường Thống Nhất), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 4, 'Dinh Độc Lập (Hội trường Thống Nhất) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Dinh Độc Lập (Hội trường Thống Nhất) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 3, 'Dinh Độc Lập (Hội trường Thống Nhất) cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Rất hài lòng khi đến Dinh Độc Lập (Hội trường Thống Nhất), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Dinh Độc Lập (Hội trường Thống Nhất) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Dinh Độc Lập (Hội trường Thống Nhất).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Dinh Độc Lập (Hội trường Thống Nhất) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Trải nghiệm tại Dinh Độc Lập (Hội trường Thống Nhất) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Không thể chê điểm nào ở Dinh Độc Lập (Hội trường Thống Nhất), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f8396a1b2be58bfdf3', 'PLACE', 5, 'Dinh Độc Lập (Hội trường Thống Nhất) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Nhà hát TP. Hồ Chí Minh (Municipal Theatre) (6a1ba7f8396a1b2be58bfdf5) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 4, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 4, 'Trải nghiệm ổn tại Nhà hát TP. Hồ Chí Minh (Municipal Theatre), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 4, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Nhà hát TP. Hồ Chí Minh (Municipal Theatre) tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 3, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Rất hài lòng khi đến Nhà hát TP. Hồ Chí Minh (Municipal Theatre), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Nhà hát TP. Hồ Chí Minh (Municipal Theatre).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Trải nghiệm tại Nhà hát TP. Hồ Chí Minh (Municipal Theatre) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Không thể chê điểm nào ở Nhà hát TP. Hồ Chí Minh (Municipal Theatre), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Nhà hát TP. Hồ Chí Minh (Municipal Theatre) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f8396a1b2be58bfdf5', 'PLACE', 5, 'Chuyến đi tới Nhà hát TP. Hồ Chí Minh (Municipal Theatre) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Bitexco Financial Tower – Saigon Skydeck (6a1ba7f8396a1b2be58bfdf6) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 4, 'Trải nghiệm ổn tại Bitexco Financial Tower – Saigon Skydeck, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 4, 'Bitexco Financial Tower – Saigon Skydeck khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Bitexco Financial Tower – Saigon Skydeck tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 3, 'Bitexco Financial Tower – Saigon Skydeck cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Rất hài lòng khi đến Bitexco Financial Tower – Saigon Skydeck, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Bitexco Financial Tower – Saigon Skydeck thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bitexco Financial Tower – Saigon Skydeck.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Bitexco Financial Tower – Saigon Skydeck xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Trải nghiệm tại Bitexco Financial Tower – Saigon Skydeck tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Không thể chê điểm nào ở Bitexco Financial Tower – Saigon Skydeck, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Bitexco Financial Tower – Saigon Skydeck đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 5, 'Chuyến đi tới Bitexco Financial Tower – Saigon Skydeck là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f8396a1b2be58bfdf6', 'PLACE', 4, 'Trải nghiệm ổn tại Bitexco Financial Tower – Saigon Skydeck, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));
