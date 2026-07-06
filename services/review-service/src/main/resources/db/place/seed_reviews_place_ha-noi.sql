-- Seed demo reviews PLACE cho khu vực: ha-noi
-- 13 users review moi place (8x5 sao, 4x4 sao, 1x3 sao)
-- Aspect id theo seed_aspect_definitions.sql (PLACE: 17 SCENERY,18 ACCESSIBILITY,19 FACILITIES,20 CROWDS,21 VALUE,22 SAFETY)
SET NAMES utf8mb4;

-- ==== Hà Nội (6a1ba807396a1b2be58bfe5c) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Hà Nội thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Hà Nội.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Hà Nội xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Trải nghiệm tại Hà Nội tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Không thể chê điểm nào ở Hà Nội, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Hà Nội đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Chuyến đi tới Hà Nội là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba807396a1b2be58bfe5c', 'PLACE', 5, 'Rất hài lòng khi đến Hà Nội, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba807396a1b2be58bfe5c', 'PLACE', 4, 'Hà Nội đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba807396a1b2be58bfe5c', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Hà Nội, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba807396a1b2be58bfe5c', 'PLACE', 4, 'Hà Nội đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba807396a1b2be58bfe5c', 'PLACE', 4, 'Trải nghiệm ổn tại Hà Nội, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba807396a1b2be58bfe5c', 'PLACE', 3, 'Hà Nội tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

-- ==== Văn Miếu – Quốc Tử Giám (6a1ba7f3396a1b2be58bfdda) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Văn Miếu – Quốc Tử Giám.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Văn Miếu – Quốc Tử Giám xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Trải nghiệm tại Văn Miếu – Quốc Tử Giám tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Không thể chê điểm nào ở Văn Miếu – Quốc Tử Giám, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Văn Miếu – Quốc Tử Giám đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Chuyến đi tới Văn Miếu – Quốc Tử Giám là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Rất hài lòng khi đến Văn Miếu – Quốc Tử Giám, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 4, 'Văn Miếu – Quốc Tử Giám đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Văn Miếu – Quốc Tử Giám, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 4, 'Văn Miếu – Quốc Tử Giám đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 4, 'Trải nghiệm ổn tại Văn Miếu – Quốc Tử Giám, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 3, 'Văn Miếu – Quốc Tử Giám tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f3396a1b2be58bfdda', 'PLACE', 5, 'Văn Miếu – Quốc Tử Giám đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Hồ Tây & Chùa Trấn Quốc (6a1ba7f3396a1b2be58bfddb) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Hồ Tây & Chùa Trấn Quốc xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Trải nghiệm tại Hồ Tây & Chùa Trấn Quốc tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Không thể chê điểm nào ở Hồ Tây & Chùa Trấn Quốc, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Hồ Tây & Chùa Trấn Quốc đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Chuyến đi tới Hồ Tây & Chùa Trấn Quốc là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Rất hài lòng khi đến Hồ Tây & Chùa Trấn Quốc, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 4, 'Hồ Tây & Chùa Trấn Quốc đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Hồ Tây & Chùa Trấn Quốc, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 4, 'Hồ Tây & Chùa Trấn Quốc đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 4, 'Trải nghiệm ổn tại Hồ Tây & Chùa Trấn Quốc, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 3, 'Hồ Tây & Chùa Trấn Quốc tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Hồ Tây & Chùa Trấn Quốc đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f3396a1b2be58bfddb', 'PLACE', 5, 'Chuyến đi tới Hồ Tây & Chùa Trấn Quốc là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Cầu Long Biên (6a1ba7f4396a1b2be58bfddc) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Trải nghiệm tại Cầu Long Biên tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Không thể chê điểm nào ở Cầu Long Biên, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Cầu Long Biên đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Chuyến đi tới Cầu Long Biên là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Rất hài lòng khi đến Cầu Long Biên, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 4, 'Cầu Long Biên đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Cầu Long Biên, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 4, 'Cầu Long Biên đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 4, 'Trải nghiệm ổn tại Cầu Long Biên, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 3, 'Cầu Long Biên tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Cầu Long Biên đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Chuyến đi tới Cầu Long Biên là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfddc', 'PLACE', 5, 'Rất hài lòng khi đến Cầu Long Biên, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Hồ Hoàn Kiếm & Đền Ngọc Sơn (6a1ba7f3396a1b2be58bfdd7) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Không thể chê điểm nào ở Hồ Hoàn Kiếm & Đền Ngọc Sơn, quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Chuyến đi tới Hồ Hoàn Kiếm & Đền Ngọc Sơn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Rất hài lòng khi đến Hồ Hoàn Kiếm & Đền Ngọc Sơn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 4, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Hồ Hoàn Kiếm & Đền Ngọc Sơn, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 4, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 4, 'Trải nghiệm ổn tại Hồ Hoàn Kiếm & Đền Ngọc Sơn, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 3, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Chuyến đi tới Hồ Hoàn Kiếm & Đền Ngọc Sơn là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Rất hài lòng khi đến Hồ Hoàn Kiếm & Đền Ngọc Sơn, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f3396a1b2be58bfdd7', 'PLACE', 5, 'Hồ Hoàn Kiếm & Đền Ngọc Sơn thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Hoàng thành Thăng Long (Imperial Citadel) (6a1ba7f4396a1b2be58bfdde) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Hoàng thành Thăng Long (Imperial Citadel) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Chuyến đi tới Hoàng thành Thăng Long (Imperial Citadel) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Rất hài lòng khi đến Hoàng thành Thăng Long (Imperial Citadel), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 4, 'Hoàng thành Thăng Long (Imperial Citadel) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Hoàng thành Thăng Long (Imperial Citadel), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 4, 'Hoàng thành Thăng Long (Imperial Citadel) đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 4, 'Trải nghiệm ổn tại Hoàng thành Thăng Long (Imperial Citadel), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 3, 'Hoàng thành Thăng Long (Imperial Citadel) tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Hoàng thành Thăng Long (Imperial Citadel) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Chuyến đi tới Hoàng thành Thăng Long (Imperial Citadel) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Rất hài lòng khi đến Hoàng thành Thăng Long (Imperial Citadel), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Hoàng thành Thăng Long (Imperial Citadel) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfdde', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Hoàng thành Thăng Long (Imperial Citadel).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Nhà tù Hoả Lò (Maison Centrale) (6a1ba7f4396a1b2be58bfddd) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Chuyến đi tới Nhà tù Hoả Lò (Maison Centrale) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Rất hài lòng khi đến Nhà tù Hoả Lò (Maison Centrale), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 4, 'Nhà tù Hoả Lò (Maison Centrale) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Nhà tù Hoả Lò (Maison Centrale), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 4, 'Nhà tù Hoả Lò (Maison Centrale) đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 4, 'Trải nghiệm ổn tại Nhà tù Hoả Lò (Maison Centrale), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 3, 'Nhà tù Hoả Lò (Maison Centrale) tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh cũng đẹp nhưng không có gì quá khác biệt so với hình dung.', NOW(6)),
(@rid, 18, 'Đường đi khá xa trung tâm, tốn khá nhiều thời gian di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Nhà tù Hoả Lò (Maison Centrale) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Chuyến đi tới Nhà tù Hoả Lò (Maison Centrale) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Rất hài lòng khi đến Nhà tù Hoả Lò (Maison Centrale), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Nhà tù Hoả Lò (Maison Centrale) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Nhà tù Hoả Lò (Maison Centrale).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f4396a1b2be58bfddd', 'PLACE', 5, 'Nhà tù Hoả Lò (Maison Centrale) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

-- ==== Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình (6a1ba7f3396a1b2be58bfdd9) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Rất hài lòng khi đến Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 4, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6)),
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 4, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 4, 'Trải nghiệm ổn tại Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình, phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 3, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất còn khá đơn sơ, chưa đáp ứng đủ lượng khách.', NOW(6)),
(@rid, 20, 'Lượng khách quá đông, chen chúc khá mệt khi tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Chuyến đi tới Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Rất hài lòng khi đến Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình, không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f3396a1b2be58bfdd9', 'PLACE', 5, 'Trải nghiệm tại Lăng Chủ tịch Hồ Chí Minh & Quảng trường Ba Đình tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

-- ==== Phố cổ Hà Nội (36 phố phường) (6a1ba7f3396a1b2be58bfdd8) ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 4, 'Phố cổ Hà Nội (36 phố phường) đáng để ghé một lần, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh khá đẹp nhưng vào giờ đông khách hơi khó chụp ảnh ưng ý.', NOW(6)),
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 4, 'Khá hài lòng với chuyến tham quan Phố cổ Hà Nội (36 phố phường), sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Có đoạn đường hơi xóc, đi xe máy cần cẩn thận.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 4, 'Phố cổ Hà Nội (36 phố phường) đẹp và đáng nhớ, chỉ hơi tiếc vì một số hạn chế nhỏ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất tạm ổn, một vài khu vực cần nâng cấp thêm.', NOW(6)),
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 4, 'Trải nghiệm ổn tại Phố cổ Hà Nội (36 phố phường), phù hợp cho một chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Vào giờ cao điểm hơi đông nhưng vẫn chấp nhận được.', NOW(6)),
(@rid, 21, 'Giá vé hơi cao nhưng vẫn chấp nhận được so với trải nghiệm.', NOW(6)),
(@rid, 22, 'Nhìn chung an toàn, chỉ cần chú ý tư trang cá nhân.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 3, 'Phố cổ Hà Nội (36 phố phường) tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé khá cao so với những gì nhận lại được.', NOW(6)),
(@rid, 22, 'Khu vực khá đông nên cần chú ý tư trang, dễ bị móc túi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Phố cổ Hà Nội (36 phố phường) đẹp hơn cả trong ảnh, đi cùng gia đình rất thích hợp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Chuyến đi tới Phố cổ Hà Nội (36 phố phường) là một trong những kỷ niệm đẹp nhất của mình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Rất hài lòng khi đến Phố cổ Hà Nội (36 phố phường), không gian và trải nghiệm đều trên cả tuyệt vời.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Phố cổ Hà Nội (36 phố phường) thực sự vượt ngoài mong đợi, cảnh đẹp và trải nghiệm rất đáng nhớ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6)),
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Một trong những điểm đến ấn tượng nhất mình từng ghé, chắc chắn sẽ quay lại Phố cổ Hà Nội (36 phố phường).', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 20, 'Đi vào buổi sáng sớm nên khá vắng, thoải mái tham quan.', NOW(6)),
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Phố cổ Hà Nội (36 phố phường) xứng đáng 5 sao, mọi thứ đều chỉn chu và đáng giá từng phút tham quan.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 21, 'Giá vé rất hợp lý so với trải nghiệm nhận được.', NOW(6)),
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Trải nghiệm tại Phố cổ Hà Nội (36 phố phường) tuyệt vời, sẽ giới thiệu cho bạn bè và gia đình.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 22, 'Cảm thấy khá an toàn khi tham quan, kể cả buổi tối.', NOW(6)),
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1ba7f3396a1b2be58bfdd8', 'PLACE', 5, 'Không thể chê điểm nào ở Phố cổ Hà Nội (36 phố phường), quá xứng đáng để ghé thăm ít nhất một lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 17, 'Cảnh quan ở đây đẹp mê hồn, chụp ảnh góc nào cũng ưng.', NOW(6)),
(@rid, 18, 'Di chuyển đến đây khá dễ dàng, có chỗ gửi xe thuận tiện.', NOW(6)),
(@rid, 19, 'Cơ sở vật chất đầy đủ, khu vệ sinh và bãi gửi xe sạch sẽ.', NOW(6));
