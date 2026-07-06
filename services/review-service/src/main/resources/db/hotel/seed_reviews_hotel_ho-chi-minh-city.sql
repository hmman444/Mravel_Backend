-- Seed demo reviews HOTEL cho khu vuc: ho-chi-minh-city
-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel
-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Meraki Boutique Hotel (6a1f856a5ccffa0ac26a6431) | starRating=3 | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 4, 'Trải nghiệm tốt tại Meraki Boutique Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 4, 'Meraki Boutique Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 4, 'Nhìn chung ở Meraki Boutique Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 5, 'Meraki Boutique Hotel thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 5, 'Kỳ nghỉ tại Meraki Boutique Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 5, 'Meraki Boutique Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 5, 'Trải nghiệm tại Meraki Boutique Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 5, 'Không có gì để chê ở Meraki Boutique Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 2, 'Trải nghiệm ở Meraki Boutique Hotel không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi rất yếu và hay bị mất kết nối, ảnh hưởng công việc.', NOW(6)),
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 3, 'Meraki Boutique Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6)),
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 3, 'Mình thấy Meraki Boutique Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 3, 'Meraki Boutique Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f856a5ccffa0ac26a6431', 'HOTEL', 4, 'Trải nghiệm tốt tại Meraki Boutique Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

-- ==== Cozrum Homes - Selina Residence (6a1f856c5ccffa0ac26a6432) | starRating=2 | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Cozrum Homes - Selina Residence.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 5, 'Rất hài lòng với Cozrum Homes - Selina Residence, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 2, 'Cozrum Homes - Selina Residence có một số vấn đề về chất lượng phòng và dịch vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 3, 'Trải nghiệm ở Cozrum Homes - Selina Residence bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 3, 'Cozrum Homes - Selina Residence cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 4, 'Trải nghiệm tốt tại Cozrum Homes - Selina Residence, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 4, 'Cozrum Homes - Selina Residence khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f856c5ccffa0ac26a6432', 'HOTEL', 4, 'Nhìn chung ở Cozrum Homes - Selina Residence thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

-- ==== Vy Vy Airport Hotel (6a1f856d5ccffa0ac26a6433) | starRating=3 | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 5, 'Rất hài lòng với Vy Vy Airport Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 5, 'Vy Vy Airport Hotel thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 2, 'Vy Vy Airport Hotel chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi rất yếu và hay bị mất kết nối, ảnh hưởng công việc.', NOW(6)),
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 3, 'Vy Vy Airport Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6)),
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 3, 'Mình thấy Vy Vy Airport Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 4, 'Vy Vy Airport Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 4, 'Nhìn chung ở Vy Vy Airport Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 4, 'Vy Vy Airport Hotel đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f856d5ccffa0ac26a6433', 'HOTEL', 5, 'Rất hài lòng với Vy Vy Airport Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

-- ==== The Passion Lux Airport Hotel Apartment - Free Airport Transfer (6a1f85715ccffa0ac26a6435) | starRating=3 | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f85715ccffa0ac26a6435', 'HOTEL', 5, 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f85715ccffa0ac26a6435', 'HOTEL', 5, 'Kỳ nghỉ tại The Passion Lux Airport Hotel Apartment - Free Airport Transfer vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f85715ccffa0ac26a6435', 'HOTEL', 2, 'Trải nghiệm ở The Passion Lux Airport Hotel Apartment - Free Airport Transfer không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên phục vụ thiếu nhiệt tình, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí không thuận tiện lắm, khá xa các điểm tham quan.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f85715ccffa0ac26a6435', 'HOTEL', 3, 'Mình thấy The Passion Lux Airport Hotel Apartment - Free Airport Transfer ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f85715ccffa0ac26a6435', 'HOTEL', 3, 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f85715ccffa0ac26a6435', 'HOTEL', 3, 'Trải nghiệm ở The Passion Lux Airport Hotel Apartment - Free Airport Transfer bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f85715ccffa0ac26a6435', 'HOTEL', 4, 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f85715ccffa0ac26a6435', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại The Passion Lux Airport Hotel Apartment - Free Airport Transfer, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f85715ccffa0ac26a6435', 'HOTEL', 4, 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f85715ccffa0ac26a6435', 'HOTEL', 5, 'Kỳ nghỉ tại The Passion Lux Airport Hotel Apartment - Free Airport Transfer vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

-- ==== Sunshine Airport Hotel (6a1f856e5ccffa0ac26a6434) | starRating=4 | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 5, 'Kỳ nghỉ tại Sunshine Airport Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 5, 'Sunshine Airport Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 3, 'Mình thấy Sunshine Airport Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 3, 'Sunshine Airport Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 4, 'Nhìn chung ở Sunshine Airport Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 4, 'Sunshine Airport Hotel đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Sunshine Airport Hotel, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 4, 'Sunshine Airport Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 5, 'Kỳ nghỉ tại Sunshine Airport Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 5, 'Sunshine Airport Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f856e5ccffa0ac26a6434', 'HOTEL', 5, 'Trải nghiệm tại Sunshine Airport Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));
