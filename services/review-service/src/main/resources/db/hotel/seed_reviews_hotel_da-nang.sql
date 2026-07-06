-- Seed demo reviews HOTEL cho khu vuc: da-nang
-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel
-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Muong Thanh Grand Da Nang Hotel (6a1f90660c291e093e9e1167) | starRating=4 | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f90660c291e093e9e1167', 'HOTEL', 3, 'Muong Thanh Grand Da Nang Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f90660c291e093e9e1167', 'HOTEL', 3, 'Trải nghiệm ở Muong Thanh Grand Da Nang Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f90660c291e093e9e1167', 'HOTEL', 4, 'Muong Thanh Grand Da Nang Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f90660c291e093e9e1167', 'HOTEL', 4, 'Trải nghiệm tốt tại Muong Thanh Grand Da Nang Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f90660c291e093e9e1167', 'HOTEL', 4, 'Muong Thanh Grand Da Nang Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f90660c291e093e9e1167', 'HOTEL', 5, 'Kỳ nghỉ tại Muong Thanh Grand Da Nang Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f90660c291e093e9e1167', 'HOTEL', 5, 'Muong Thanh Grand Da Nang Hotel xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f90660c291e093e9e1167', 'HOTEL', 5, 'Trải nghiệm tại Muong Thanh Grand Da Nang Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f90660c291e093e9e1167', 'HOTEL', 5, 'Không có gì để chê ở Muong Thanh Grand Da Nang Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f90660c291e093e9e1167', 'HOTEL', 5, 'Muong Thanh Grand Da Nang Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

-- ==== Orchide'es Ocean Hotel (6a1f906d0c291e093e9e1169) | starRating=3 | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f906d0c291e093e9e1169', 'HOTEL', 5, 'Orchide''es Ocean Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f906d0c291e093e9e1169', 'HOTEL', 2, 'Trải nghiệm ở Orchide''es Ocean Hotel không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6)),
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f906d0c291e093e9e1169', 'HOTEL', 3, 'Mình thấy Orchide''es Ocean Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f906d0c291e093e9e1169', 'HOTEL', 3, 'Orchide''es Ocean Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f906d0c291e093e9e1169', 'HOTEL', 3, 'Trải nghiệm ở Orchide''es Ocean Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f906d0c291e093e9e1169', 'HOTEL', 4, 'Orchide''es Ocean Hotel đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f906d0c291e093e9e1169', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Orchide''es Ocean Hotel, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f906d0c291e093e9e1169', 'HOTEL', 4, 'Orchide''es Ocean Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f906d0c291e093e9e1169', 'HOTEL', 4, 'Trải nghiệm tốt tại Orchide''es Ocean Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f906d0c291e093e9e1169', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Orchide''es Ocean Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f906d0c291e093e9e1169', 'HOTEL', 5, 'Rất hài lòng với Orchide''es Ocean Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

-- ==== La Beach Hotel (6a1f906a0c291e093e9e1168) | starRating=3 | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f906a0c291e093e9e1168', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn La Beach Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f906a0c291e093e9e1168', 'HOTEL', 5, 'Rất hài lòng với La Beach Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f906a0c291e093e9e1168', 'HOTEL', 2, 'La Beach Hotel chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f906a0c291e093e9e1168', 'HOTEL', 3, 'Trải nghiệm ở La Beach Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f906a0c291e093e9e1168', 'HOTEL', 3, 'La Beach Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f906a0c291e093e9e1168', 'HOTEL', 3, 'Mình thấy La Beach Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6)),
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f906a0c291e093e9e1168', 'HOTEL', 4, 'La Beach Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f906a0c291e093e9e1168', 'HOTEL', 4, 'Trải nghiệm tốt tại La Beach Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f906a0c291e093e9e1168', 'HOTEL', 4, 'La Beach Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f906a0c291e093e9e1168', 'HOTEL', 4, 'Nhìn chung ở La Beach Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f906a0c291e093e9e1168', 'HOTEL', 5, 'La Beach Hotel thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f906a0c291e093e9e1168', 'HOTEL', 5, 'Kỳ nghỉ tại La Beach Hotel vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

-- ==== Davue Hotel Da Nang (6a1f90740c291e093e9e116b) | starRating=4 | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Rất hài lòng với Davue Hotel Da Nang, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Davue Hotel Da Nang thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Kỳ nghỉ tại Davue Hotel Da Nang vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f90740c291e093e9e116b', 'HOTEL', 3, 'Davue Hotel Da Nang cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6)),
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f90740c291e093e9e116b', 'HOTEL', 3, 'Mình thấy Davue Hotel Da Nang ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f90740c291e093e9e116b', 'HOTEL', 4, 'Davue Hotel Da Nang ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f90740c291e093e9e116b', 'HOTEL', 4, 'Trải nghiệm tốt tại Davue Hotel Da Nang, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f90740c291e093e9e116b', 'HOTEL', 4, 'Davue Hotel Da Nang khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f90740c291e093e9e116b', 'HOTEL', 4, 'Nhìn chung ở Davue Hotel Da Nang thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Davue Hotel Da Nang thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Kỳ nghỉ tại Davue Hotel Da Nang vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Davue Hotel Da Nang xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f90740c291e093e9e116b', 'HOTEL', 5, 'Trải nghiệm tại Davue Hotel Da Nang rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

-- ==== Lychee Hotel (6a1f906e0c291e093e9e116a) | starRating=3 | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f906e0c291e093e9e116a', 'HOTEL', 2, 'Lychee Hotel chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6)),
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f906e0c291e093e9e116a', 'HOTEL', 3, 'Trải nghiệm ở Lychee Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f906e0c291e093e9e116a', 'HOTEL', 3, 'Lychee Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f906e0c291e093e9e116a', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Lychee Hotel, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f906e0c291e093e9e116a', 'HOTEL', 4, 'Lychee Hotel ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f906e0c291e093e9e116a', 'HOTEL', 4, 'Trải nghiệm tốt tại Lychee Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f906e0c291e093e9e116a', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Lychee Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f906e0c291e093e9e116a', 'HOTEL', 5, 'Rất hài lòng với Lychee Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));
