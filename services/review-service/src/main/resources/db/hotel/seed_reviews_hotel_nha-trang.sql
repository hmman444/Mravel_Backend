-- Seed demo reviews HOTEL cho khu vuc: nha-trang
-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel
-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Aaron Hotel (6a1f8b88ac4325285e73be0c) | starRating=4 | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f8b88ac4325285e73be0c', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Aaron Hotel, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f8b88ac4325285e73be0c', 'HOTEL', 4, 'Aaron Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f8b88ac4325285e73be0c', 'HOTEL', 5, 'Kỳ nghỉ tại Aaron Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f8b88ac4325285e73be0c', 'HOTEL', 5, 'Aaron Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f8b88ac4325285e73be0c', 'HOTEL', 5, 'Trải nghiệm tại Aaron Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f8b88ac4325285e73be0c', 'HOTEL', 5, 'Không có gì để chê ở Aaron Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f8b88ac4325285e73be0c', 'HOTEL', 5, 'Aaron Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f8b88ac4325285e73be0c', 'HOTEL', 3, 'Aaron Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f8b88ac4325285e73be0c', 'HOTEL', 3, 'Mình thấy Aaron Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f8b88ac4325285e73be0c', 'HOTEL', 4, 'Aaron Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f8b88ac4325285e73be0c', 'HOTEL', 4, 'Nhìn chung ở Aaron Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

-- ==== Golden Hotel Nha Trang (6a1f8b8bac4325285e73be0e) | starRating=3 | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f8b8bac4325285e73be0e', 'HOTEL', 4, 'Golden Hotel Nha Trang ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f8b8bac4325285e73be0e', 'HOTEL', 4, 'Trải nghiệm tốt tại Golden Hotel Nha Trang, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f8b8bac4325285e73be0e', 'HOTEL', 4, 'Golden Hotel Nha Trang khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f8b8bac4325285e73be0e', 'HOTEL', 4, 'Nhìn chung ở Golden Hotel Nha Trang thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f8b8bac4325285e73be0e', 'HOTEL', 5, 'Không có gì để chê ở Golden Hotel Nha Trang, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f8b8bac4325285e73be0e', 'HOTEL', 5, 'Golden Hotel Nha Trang đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f8b8bac4325285e73be0e', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Golden Hotel Nha Trang.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f8b8bac4325285e73be0e', 'HOTEL', 5, 'Rất hài lòng với Golden Hotel Nha Trang, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f8b8bac4325285e73be0e', 'HOTEL', 2, 'Golden Hotel Nha Trang chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6)),
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f8b8bac4325285e73be0e', 'HOTEL', 3, 'Trải nghiệm ở Golden Hotel Nha Trang bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f8b8bac4325285e73be0e', 'HOTEL', 3, 'Golden Hotel Nha Trang cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f8b8bac4325285e73be0e', 'HOTEL', 3, 'Mình thấy Golden Hotel Nha Trang ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6));

-- ==== Panorama Nha Trang Sanvilla (6a1f8b8cac4325285e73be0f) | starRating=3 | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f8b8cac4325285e73be0f', 'HOTEL', 4, 'Trải nghiệm tốt tại Panorama Nha Trang Sanvilla, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f8b8cac4325285e73be0f', 'HOTEL', 4, 'Panorama Nha Trang Sanvilla khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f8b8cac4325285e73be0f', 'HOTEL', 4, 'Nhìn chung ở Panorama Nha Trang Sanvilla thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f8b8cac4325285e73be0f', 'HOTEL', 4, 'Panorama Nha Trang Sanvilla đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f8b8cac4325285e73be0f', 'HOTEL', 5, 'Panorama Nha Trang Sanvilla đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f8b8cac4325285e73be0f', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Panorama Nha Trang Sanvilla.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f8b8cac4325285e73be0f', 'HOTEL', 5, 'Rất hài lòng với Panorama Nha Trang Sanvilla, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f8b8cac4325285e73be0f', 'HOTEL', 5, 'Panorama Nha Trang Sanvilla thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f8b8cac4325285e73be0f', 'HOTEL', 5, 'Kỳ nghỉ tại Panorama Nha Trang Sanvilla vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f8b8cac4325285e73be0f', 'HOTEL', 2, 'Panorama Nha Trang Sanvilla có một số vấn đề về chất lượng phòng và dịch vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f8b8cac4325285e73be0f', 'HOTEL', 3, 'Mình thấy Panorama Nha Trang Sanvilla ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f8b8cac4325285e73be0f', 'HOTEL', 3, 'Panorama Nha Trang Sanvilla tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f8b8cac4325285e73be0f', 'HOTEL', 3, 'Trải nghiệm ở Panorama Nha Trang Sanvilla bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6)),
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6));

-- ==== Brilliant Bay Nha Trang Hotel (6a1f8b8fac4325285e73be10) | starRating=4 | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f8b8fac4325285e73be10', 'HOTEL', 4, 'Brilliant Bay Nha Trang Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f8b8fac4325285e73be10', 'HOTEL', 4, 'Nhìn chung ở Brilliant Bay Nha Trang Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f8b8fac4325285e73be10', 'HOTEL', 5, 'Không có gì để chê ở Brilliant Bay Nha Trang Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f8b8fac4325285e73be10', 'HOTEL', 5, 'Brilliant Bay Nha Trang Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f8b8fac4325285e73be10', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Brilliant Bay Nha Trang Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f8b8fac4325285e73be10', 'HOTEL', 5, 'Rất hài lòng với Brilliant Bay Nha Trang Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f8b8fac4325285e73be10', 'HOTEL', 3, 'Brilliant Bay Nha Trang Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f8b8fac4325285e73be10', 'HOTEL', 4, 'Nhìn chung ở Brilliant Bay Nha Trang Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

-- ==== Sun City Hotel Nha Trang (6a1f8b8aac4325285e73be0d) | starRating=3 | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f8b8aac4325285e73be0d', 'HOTEL', 3, 'Mình thấy Sun City Hotel Nha Trang ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f8b8aac4325285e73be0d', 'HOTEL', 3, 'Sun City Hotel Nha Trang tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f8b8aac4325285e73be0d', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Sun City Hotel Nha Trang, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f8b8aac4325285e73be0d', 'HOTEL', 4, 'Sun City Hotel Nha Trang ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f8b8aac4325285e73be0d', 'HOTEL', 4, 'Trải nghiệm tốt tại Sun City Hotel Nha Trang, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f8b8aac4325285e73be0d', 'HOTEL', 5, 'Sun City Hotel Nha Trang thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f8b8aac4325285e73be0d', 'HOTEL', 5, 'Kỳ nghỉ tại Sun City Hotel Nha Trang vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f8b8aac4325285e73be0d', 'HOTEL', 5, 'Sun City Hotel Nha Trang xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f8b8aac4325285e73be0d', 'HOTEL', 2, 'Sun City Hotel Nha Trang chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6)),
(@rid, 3, 'Nhân viên phục vụ thiếu nhiệt tình, phản hồi chậm khi cần hỗ trợ.', NOW(6));
