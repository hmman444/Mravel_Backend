-- Seed demo reviews PLACE cho khu vực: sa-pa
-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)
-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)
SET NAMES utf8mb4;

-- ==== Sa Pa (6a1ba807396a1b2be58bfe5e) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba807396a1b2be58bfe5e', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Sa Pa, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba807396a1b2be58bfe5e', 'PLACE', 4, 'Sa Pa đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba807396a1b2be58bfe5e', 'PLACE', 4, 'Trải nghiệm ổn tại Sa Pa, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba807396a1b2be58bfe5e', 'PLACE', 3, 'Sa Pa tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Sa Pa đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Chuyến đi tới Sa Pa là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Rất hài lòng khi đến Sa Pa, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Sa Pa thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Sa Pa.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Sa Pa xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Trải nghiệm tại Sa Pa tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba807396a1b2be58bfe5e', 'PLACE', 5, 'Không thể chê điểm nào ở Sa Pa, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba807396a1b2be58bfe5e', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Sa Pa, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Bản Cát Cát (6a1ba7f4396a1b2be58bfde0) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 4, 'Bản Cát Cát đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 4, 'Trải nghiệm ổn tại Bản Cát Cát, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 3, 'Bản Cát Cát tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Bản Cát Cát đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Chuyến đi tới Bản Cát Cát là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Rất hài lòng khi đến Bản Cát Cát, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Bản Cát Cát thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bản Cát Cát.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Bản Cát Cát xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Trải nghiệm tại Bản Cát Cát tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 5, 'Không thể chê điểm nào ở Bản Cát Cát, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Bản Cát Cát, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfde0', 'PLACE', 4, 'Bản Cát Cát đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Núi Hàm Rồng (6a1ba7f4396a1b2be58bfde1) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 4, 'Trải nghiệm ổn tại Núi Hàm Rồng, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 3, 'Núi Hàm Rồng tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Núi Hàm Rồng đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Chuyến đi tới Núi Hàm Rồng là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Rất hài lòng khi đến Núi Hàm Rồng, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Núi Hàm Rồng thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Núi Hàm Rồng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Núi Hàm Rồng xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Trải nghiệm tại Núi Hàm Rồng tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 5, 'Không thể chê điểm nào ở Núi Hàm Rồng, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Núi Hàm Rồng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 4, 'Núi Hàm Rồng đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfde1', 'PLACE', 4, 'Trải nghiệm ổn tại Núi Hàm Rồng, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

-- ==== Fansipan – Nóc nhà Đông Dương (6a1ba7f4396a1b2be58bfddf) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 3, 'Fansipan – Nóc nhà Đông Dương tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Fansipan – Nóc nhà Đông Dương đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Chuyến đi tới Fansipan – Nóc nhà Đông Dương là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Rất hài lòng khi đến Fansipan – Nóc nhà Đông Dương, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Fansipan – Nóc nhà Đông Dương thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Fansipan – Nóc nhà Đông Dương.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Fansipan – Nóc nhà Đông Dương xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Trải nghiệm tại Fansipan – Nóc nhà Đông Dương tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 5, 'Không thể chê điểm nào ở Fansipan – Nóc nhà Đông Dương, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Fansipan – Nóc nhà Đông Dương, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 4, 'Fansipan – Nóc nhà Đông Dương đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 4, 'Trải nghiệm ổn tại Fansipan – Nóc nhà Đông Dương, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfddf', 'PLACE', 4, 'Fansipan – Nóc nhà Đông Dương khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

-- ==== Bản Tả Phìn (6a1ba7f5396a1b2be58bfde2) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Bản Tả Phìn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Chuyến đi tới Bản Tả Phìn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Rất hài lòng khi đến Bản Tả Phìn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Bản Tả Phìn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Bản Tả Phìn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Bản Tả Phìn xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Trải nghiệm tại Bản Tả Phìn tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 5, 'Không thể chê điểm nào ở Bản Tả Phìn, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Bản Tả Phìn, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 4, 'Bản Tả Phìn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 4, 'Trải nghiệm ổn tại Bản Tả Phìn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 4, 'Bản Tả Phìn khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f5396a1b2be58bfde2', 'PLACE', 3, 'Trải nghiệm ở Bản Tả Phìn bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

-- ==== Thác Bạc (6a1ba7f5396a1b2be58bfde4) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Chuyến đi tới Thác Bạc là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Rất hài lòng khi đến Thác Bạc, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Thác Bạc thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Thác Bạc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Thác Bạc xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Trải nghiệm tại Thác Bạc tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Không thể chê điểm nào ở Thác Bạc, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Thác Bạc, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 4, 'Thác Bạc đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 4, 'Trải nghiệm ổn tại Thác Bạc, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 4, 'Thác Bạc khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 3, 'Trải nghiệm ở Thác Bạc bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f5396a1b2be58bfde4', 'PLACE', 5, 'Thác Bạc xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Đèo Ô Quy Hồ – Cổng Trời (6a1ba7f5396a1b2be58bfde3) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Rất hài lòng khi đến Đèo Ô Quy Hồ – Cổng Trời, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Đèo Ô Quy Hồ – Cổng Trời thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Đèo Ô Quy Hồ – Cổng Trời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Đèo Ô Quy Hồ – Cổng Trời xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Trải nghiệm tại Đèo Ô Quy Hồ – Cổng Trời tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Không thể chê điểm nào ở Đèo Ô Quy Hồ – Cổng Trời, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Đèo Ô Quy Hồ – Cổng Trời, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 4, 'Đèo Ô Quy Hồ – Cổng Trời đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 4, 'Trải nghiệm ổn tại Đèo Ô Quy Hồ – Cổng Trời, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 4, 'Đèo Ô Quy Hồ – Cổng Trời khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 3, 'Trải nghiệm ở Đèo Ô Quy Hồ – Cổng Trời bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Đèo Ô Quy Hồ – Cổng Trời xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f5396a1b2be58bfde3', 'PLACE', 5, 'Trải nghiệm tại Đèo Ô Quy Hồ – Cổng Trời tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Thác Tình Yêu (6a1ba7f5396a1b2be58bfde5) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Thác Tình Yêu thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Thác Tình Yêu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Thác Tình Yêu xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Trải nghiệm tại Thác Tình Yêu tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Không thể chê điểm nào ở Thác Tình Yêu, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Thác Tình Yêu, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 4, 'Thác Tình Yêu đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 4, 'Trải nghiệm ổn tại Thác Tình Yêu, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 4, 'Thác Tình Yêu khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 3, 'Trải nghiệm ở Thác Tình Yêu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6)),
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Thác Tình Yêu xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Trải nghiệm tại Thác Tình Yêu tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f5396a1b2be58bfde5', 'PLACE', 5, 'Không thể chê điểm nào ở Thác Tình Yêu, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Nhà thờ Đá Sa Pa & Quảng trường (6a1ba7f5396a1b2be58bfde6) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Nhà thờ Đá Sa Pa & Quảng trường.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Nhà thờ Đá Sa Pa & Quảng trường xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Trải nghiệm tại Nhà thờ Đá Sa Pa & Quảng trường tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Không thể chê điểm nào ở Nhà thờ Đá Sa Pa & Quảng trường, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Nhà thờ Đá Sa Pa & Quảng trường, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 4, 'Nhà thờ Đá Sa Pa & Quảng trường đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 4, 'Trải nghiệm ổn tại Nhà thờ Đá Sa Pa & Quảng trường, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 4, 'Nhà thờ Đá Sa Pa & Quảng trường khá đẹp và đáng tham quan, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 3, 'Trải nghiệm ở Nhà thờ Đá Sa Pa & Quảng trường bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6)),
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Nhà thờ Đá Sa Pa & Quảng trường xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Trải nghiệm tại Nhà thờ Đá Sa Pa & Quảng trường tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Không thể chê điểm nào ở Nhà thờ Đá Sa Pa & Quảng trường, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f5396a1b2be58bfde6', 'PLACE', 5, 'Nhà thờ Đá Sa Pa & Quảng trường đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));
