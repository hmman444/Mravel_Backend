-- Seed demo reviews PLACE cho khu vực: da-nang
-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)
-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)
SET NAMES utf8mb4;

-- ==== Đà Nẵng (6a45938dc6a3905f886ca638) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Đà Nẵng xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Trải nghiệm tại Đà Nẵng tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Không thể chê điểm nào ở Đà Nẵng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a45938dc6a3905f886ca638', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Đà Nẵng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a45938dc6a3905f886ca638', 'PLACE', 4, 'Đà Nẵng đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a45938dc6a3905f886ca638', 'PLACE', 4, 'Trải nghiệm ổn tại Đà Nẵng, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45938dc6a3905f886ca638', 'PLACE', 4, 'Đà Nẵng khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45938dc6a3905f886ca638', 'PLACE', 3, 'Trải nghiệm ở Đà Nẵng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Đà Nẵng xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Trải nghiệm tại Đà Nẵng tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Không thể chê điểm nào ở Đà Nẵng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Đà Nẵng đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a45938dc6a3905f886ca638', 'PLACE', 5, 'Chuyến đi tới Đà Nẵng là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Bãi biển Mỹ Khê (6a1ba7f6396a1b2be58bfde7) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Trải nghiệm tại Bãi biển Mỹ Khê tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Không thể chê điểm nào ở Bãi biển Mỹ Khê, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Bãi biển Mỹ Khê, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 4, 'Bãi biển Mỹ Khê đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 4, 'Trải nghiệm ổn tại Bãi biển Mỹ Khê, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 4, 'Bãi biển Mỹ Khê khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 3, 'Trải nghiệm ở Bãi biển Mỹ Khê bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Bãi biển Mỹ Khê xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Trải nghiệm tại Bãi biển Mỹ Khê tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Không thể chê điểm nào ở Bãi biển Mỹ Khê, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Bãi biển Mỹ Khê đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Chuyến đi tới Bãi biển Mỹ Khê là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfde7', 'PLACE', 5, 'Rất hài lòng khi đến Bãi biển Mỹ Khê, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Cầu Rồng (6a1ba7f6396a1b2be58bfdea) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Không thể chê điểm nào ở Cầu Rồng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Cầu Rồng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 4, 'Cầu Rồng đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 4, 'Trải nghiệm ổn tại Cầu Rồng, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 4, 'Cầu Rồng khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 3, 'Trải nghiệm ở Cầu Rồng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Cầu Rồng xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Trải nghiệm tại Cầu Rồng tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Không thể chê điểm nào ở Cầu Rồng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Cầu Rồng đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Chuyến đi tới Cầu Rồng là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Rất hài lòng khi đến Cầu Rồng, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfdea', 'PLACE', 5, 'Cầu Rồng thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Bán đảo Sơn Trà (6a1ba7f6396a1b2be58bfdeb) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Bán đảo Sơn Trà, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 4, 'Bán đảo Sơn Trà đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 4, 'Trải nghiệm ổn tại Bán đảo Sơn Trà, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 4, 'Bán đảo Sơn Trà khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 3, 'Trải nghiệm ở Bán đảo Sơn Trà bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Bán đảo Sơn Trà xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Trải nghiệm tại Bán đảo Sơn Trà tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Không thể chê điểm nào ở Bán đảo Sơn Trà, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Bán đảo Sơn Trà đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Chuyến đi tới Bán đảo Sơn Trà là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Rất hài lòng khi đến Bán đảo Sơn Trà, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Bán đảo Sơn Trà thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfdeb', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bán đảo Sơn Trà.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Chợ Hàn (6a1ba7f6396a1b2be58bfdec) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 4, 'Chợ Hàn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 4, 'Trải nghiệm ổn tại Chợ Hàn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 4, 'Chợ Hàn khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 3, 'Trải nghiệm ở Chợ Hàn bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Chợ Hàn xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Trải nghiệm tại Chợ Hàn tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Không thể chê điểm nào ở Chợ Hàn, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Chợ Hàn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Chuyến đi tới Chợ Hàn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Rất hài lòng khi đến Chợ Hàn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Chợ Hàn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Chợ Hàn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfdec', 'PLACE', 4, 'Chợ Hàn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Ngũ Hành Sơn (6a1ba7f6396a1b2be58bfde9) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 4, 'Trải nghiệm ổn tại Ngũ Hành Sơn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 4, 'Ngũ Hành Sơn khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 3, 'Trải nghiệm ở Ngũ Hành Sơn bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Ngũ Hành Sơn xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Trải nghiệm tại Ngũ Hành Sơn tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Không thể chê điểm nào ở Ngũ Hành Sơn, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Ngũ Hành Sơn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Chuyến đi tới Ngũ Hành Sơn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Rất hài lòng khi đến Ngũ Hành Sơn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Ngũ Hành Sơn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Ngũ Hành Sơn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 4, 'Ngũ Hành Sơn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfde9', 'PLACE', 4, 'Trải nghiệm ổn tại Ngũ Hành Sơn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Bảo tàng Điêu khắc Chăm (6a1ba7f7396a1b2be58bfdee) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 4, 'Bảo tàng Điêu khắc Chăm khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 3, 'Trải nghiệm ở Bảo tàng Điêu khắc Chăm bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Bảo tàng Điêu khắc Chăm xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Trải nghiệm tại Bảo tàng Điêu khắc Chăm tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Không thể chê điểm nào ở Bảo tàng Điêu khắc Chăm, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Bảo tàng Điêu khắc Chăm đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Chuyến đi tới Bảo tàng Điêu khắc Chăm là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Rất hài lòng khi đến Bảo tàng Điêu khắc Chăm, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Bảo tàng Điêu khắc Chăm thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bảo tàng Điêu khắc Chăm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 4, 'Bảo tàng Điêu khắc Chăm đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 4, 'Trải nghiệm ổn tại Bảo tàng Điêu khắc Chăm, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f7396a1b2be58bfdee', 'PLACE', 4, 'Bảo tàng Điêu khắc Chăm khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Asia Park – Sun Wheel (6a1ba7f7396a1b2be58bfded) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f7396a1b2be58bfded', 'PLACE', 3, 'Trải nghiệm ở Asia Park – Sun Wheel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Asia Park – Sun Wheel xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Trải nghiệm tại Asia Park – Sun Wheel tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Không thể chê điểm nào ở Asia Park – Sun Wheel, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Asia Park – Sun Wheel đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Chuyến đi tới Asia Park – Sun Wheel là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Rất hài lòng khi đến Asia Park – Sun Wheel, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Asia Park – Sun Wheel thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f7396a1b2be58bfded', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Asia Park – Sun Wheel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f7396a1b2be58bfded', 'PLACE', 4, 'Asia Park – Sun Wheel đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f7396a1b2be58bfded', 'PLACE', 4, 'Trải nghiệm ổn tại Asia Park – Sun Wheel, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f7396a1b2be58bfded', 'PLACE', 4, 'Asia Park – Sun Wheel khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f7396a1b2be58bfded', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Asia Park – Sun Wheel tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Bà Nà Hills & Cầu Vàng (6a1ba7f6396a1b2be58bfde8) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Bà Nà Hills & Cầu Vàng xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Trải nghiệm tại Bà Nà Hills & Cầu Vàng tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Không thể chê điểm nào ở Bà Nà Hills & Cầu Vàng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Bà Nà Hills & Cầu Vàng đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Chuyến đi tới Bà Nà Hills & Cầu Vàng là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Rất hài lòng khi đến Bà Nà Hills & Cầu Vàng, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Bà Nà Hills & Cầu Vàng thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bà Nà Hills & Cầu Vàng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 4, 'Bà Nà Hills & Cầu Vàng đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 4, 'Trải nghiệm ổn tại Bà Nà Hills & Cầu Vàng, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 4, 'Bà Nà Hills & Cầu Vàng khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 4, 'Nhìn chung trải nghiệm ở Bà Nà Hills & Cầu Vàng tốt, tuy còn một vài bất tiện nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f6396a1b2be58bfde8', 'PLACE', 3, 'Bà Nà Hills & Cầu Vàng cũng đáng xem một lần nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));
