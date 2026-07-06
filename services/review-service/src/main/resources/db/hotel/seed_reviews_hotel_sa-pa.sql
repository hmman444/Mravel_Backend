-- Seed demo reviews HOTEL cho khu vuc: sa-pa
-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel
-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== The Gate Boutique Hotel Sapa (6a1f95d5b0f2cc2834a9446e) | starRating=3 | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 5, 'Kỳ nghỉ tại The Gate Boutique Hotel Sapa vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 5, 'The Gate Boutique Hotel Sapa xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 2, 'The Gate Boutique Hotel Sapa chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6)),
(@rid, 7, 'Bữa sáng khá nghèo nàn, chất lượng chưa tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 3, 'The Gate Boutique Hotel Sapa tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 3, 'Trải nghiệm ở The Gate Boutique Hotel Sapa bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6)),
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 4, 'The Gate Boutique Hotel Sapa khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 4, 'Nhìn chung ở The Gate Boutique Hotel Sapa thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 4, 'The Gate Boutique Hotel Sapa đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f95d5b0f2cc2834a9446e', 'HOTEL', 5, 'Kỳ nghỉ tại The Gate Boutique Hotel Sapa vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

-- ==== Sapa Clover Hotel (6a1f95d8b0f2cc2834a94470) | starRating=3 | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 4, 'Sapa Clover Hotel đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 5, 'Trải nghiệm tại Sapa Clover Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 5, 'Không có gì để chê ở Sapa Clover Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 5, 'Sapa Clover Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 2, 'Sapa Clover Hotel chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên phục vụ thiếu nhiệt tình, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí không thuận tiện lắm, khá xa các điểm tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 3, 'Mình thấy Sapa Clover Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 3, 'Sapa Clover Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 3, 'Trải nghiệm ở Sapa Clover Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 4, 'Sapa Clover Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f95d8b0f2cc2834a94470', 'HOTEL', 4, 'Trải nghiệm tốt tại Sapa Clover Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

-- ==== Nomadtrails Boutique Hotel (6a1f95ddb0f2cc2834a94471) | starRating=3 | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Nomadtrails Boutique Hotel, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 4, 'Nomadtrails Boutique Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 4, 'Trải nghiệm tốt tại Nomadtrails Boutique Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Nomadtrails Boutique Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 5, 'Rất hài lòng với Nomadtrails Boutique Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 5, 'Nomadtrails Boutique Hotel thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 2, 'Nomadtrails Boutique Hotel chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi rất yếu và hay bị mất kết nối, ảnh hưởng công việc.', NOW(6)),
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 3, 'Nomadtrails Boutique Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6)),
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 3, 'Mình thấy Nomadtrails Boutique Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 3, 'Nomadtrails Boutique Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f95ddb0f2cc2834a94471', 'HOTEL', 4, 'Nhìn chung ở Nomadtrails Boutique Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

-- ==== Misty Hostel Sapa (6a1f95d8b0f2cc2834a9446f) | starRating=2 | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 3, 'Misty Hostel Sapa tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 4, 'Trải nghiệm tốt tại Misty Hostel Sapa, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 4, 'Misty Hostel Sapa khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 4, 'Nhìn chung ở Misty Hostel Sapa thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 4, 'Misty Hostel Sapa đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 5, 'Kỳ nghỉ tại Misty Hostel Sapa vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 5, 'Misty Hostel Sapa xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 5, 'Trải nghiệm tại Misty Hostel Sapa rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 2, 'Misty Hostel Sapa chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 2, 'Trải nghiệm ở Misty Hostel Sapa không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6)),
(@rid, 7, 'Bữa sáng khá nghèo nàn, chất lượng chưa tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 3, 'Misty Hostel Sapa cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f95d8b0f2cc2834a9446f', 'HOTEL', 3, 'Mình thấy Misty Hostel Sapa ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6)),
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6));

-- ==== DeLaSol Sapa Hotel (6a1f95f3b0f2cc2834a94472) | starRating=4 | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 4, 'Trải nghiệm tốt tại DeLaSol Sapa Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 4, 'DeLaSol Sapa Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 4, 'Nhìn chung ở DeLaSol Sapa Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'DeLaSol Sapa Hotel thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'Kỳ nghỉ tại DeLaSol Sapa Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'DeLaSol Sapa Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'Trải nghiệm tại DeLaSol Sapa Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'Không có gì để chê ở DeLaSol Sapa Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'DeLaSol Sapa Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn DeLaSol Sapa Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 3, 'Mình thấy DeLaSol Sapa Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 3, 'DeLaSol Sapa Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f95f3b0f2cc2834a94472', 'HOTEL', 4, 'Trải nghiệm tốt tại DeLaSol Sapa Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));
