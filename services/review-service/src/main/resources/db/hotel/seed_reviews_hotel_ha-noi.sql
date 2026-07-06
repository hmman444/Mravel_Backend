-- Seed demo reviews HOTEL cho khu vuc: ha-noi
-- So review/hotel linh hoat (8-13), phan bo sao theo starRating that cua hotel
-- Aspect id theo seed_aspect_definitions.sql (HOTEL: 1 CLEANLINESS,2 ROOM_QUALITY,3 STAFF_SERVICE,4 LOCATION,5 VALUE_FOR_MONEY,6 AMENITIES,7 BREAKFAST,8 WIFI)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== MAY Hotel & Apartment (6a1f88172bd108416c46476b) | starRating=2 | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f88172bd108416c46476b', 'HOTEL', 5, 'MAY Hotel & Apartment xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f88172bd108416c46476b', 'HOTEL', 5, 'Trải nghiệm tại MAY Hotel & Apartment rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f88172bd108416c46476b', 'HOTEL', 2, 'MAY Hotel & Apartment chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6)),
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f88172bd108416c46476b', 'HOTEL', 2, 'Trải nghiệm ở MAY Hotel & Apartment không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6)),
(@rid, 3, 'Nhân viên phục vụ thiếu nhiệt tình, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f88172bd108416c46476b', 'HOTEL', 3, 'MAY Hotel & Apartment cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f88172bd108416c46476b', 'HOTEL', 3, 'Mình thấy MAY Hotel & Apartment ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f88172bd108416c46476b', 'HOTEL', 3, 'MAY Hotel & Apartment tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f88172bd108416c46476b', 'HOTEL', 4, 'Trải nghiệm tốt tại MAY Hotel & Apartment, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f88172bd108416c46476b', 'HOTEL', 4, 'MAY Hotel & Apartment khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f88172bd108416c46476b', 'HOTEL', 4, 'Nhìn chung ở MAY Hotel & Apartment thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f88172bd108416c46476b', 'HOTEL', 4, 'MAY Hotel & Apartment đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f88172bd108416c46476b', 'HOTEL', 5, 'MAY Hotel & Apartment đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

-- ==== Ha Noi Le Grand Hotel (6a1f88162bd108416c46476a) | starRating=3 | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a1f88162bd108416c46476a', 'HOTEL', 5, 'Trải nghiệm tại Ha Noi Le Grand Hotel rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f88162bd108416c46476a', 'HOTEL', 5, 'Không có gì để chê ở Ha Noi Le Grand Hotel, quá hài lòng với chuyến đi này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f88162bd108416c46476a', 'HOTEL', 2, 'Trải nghiệm ở Ha Noi Le Grand Hotel không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí không thuận tiện lắm, khá xa các điểm tham quan.', NOW(6)),
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f88162bd108416c46476a', 'HOTEL', 3, 'Ha Noi Le Grand Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả ở mức bình thường so với chất lượng phòng.', NOW(6)),
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f88162bd108416c46476a', 'HOTEL', 3, 'Mình thấy Ha Noi Le Grand Hotel ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f88162bd108416c46476a', 'HOTEL', 3, 'Ha Noi Le Grand Hotel tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f88162bd108416c46476a', 'HOTEL', 4, 'Trải nghiệm tốt tại Ha Noi Le Grand Hotel, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f88162bd108416c46476a', 'HOTEL', 4, 'Ha Noi Le Grand Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f88162bd108416c46476a', 'HOTEL', 4, 'Nhìn chung ở Ha Noi Le Grand Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f88162bd108416c46476a', 'HOTEL', 4, 'Ha Noi Le Grand Hotel đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f88162bd108416c46476a', 'HOTEL', 5, 'Ha Noi Le Grand Hotel đẹp và tiện nghi hơn cả hình ảnh trên web, rất đáng tiền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f88162bd108416c46476a', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Ha Noi Le Grand Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f88162bd108416c46476a', 'HOTEL', 5, 'Rất hài lòng với Ha Noi Le Grand Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

-- ==== Millennium Hanoi Hotel (6a1f881c2bd108416c46476d) | starRating=3 | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a1f881c2bd108416c46476d', 'HOTEL', 4, 'Millennium Hanoi Hotel khá tốt, phòng sạch sẽ, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f881c2bd108416c46476d', 'HOTEL', 4, 'Nhìn chung ở Millennium Hanoi Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6)),
(@rid, 7, 'Bữa sáng khá ổn, món ăn không quá đa dạng.', NOW(6)),
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f881c2bd108416c46476d', 'HOTEL', 5, 'Một trong những nơi lưu trú tốt nhất mình từng ở, cảm ơn Millennium Hanoi Hotel.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f881c2bd108416c46476d', 'HOTEL', 5, 'Rất hài lòng với Millennium Hanoi Hotel, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f881c2bd108416c46476d', 'HOTEL', 2, 'Trải nghiệm ở Millennium Hanoi Hotel không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng chưa được vệ sinh kỹ, còn bụi và vết bẩn ở vài chỗ.', NOW(6)),
(@rid, 2, 'Phòng khá nhỏ và nội thất xuống cấp, cần nâng cấp.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f881c2bd108416c46476d', 'HOTEL', 3, 'Trải nghiệm ở Millennium Hanoi Hotel bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f881c2bd108416c46476d', 'HOTEL', 3, 'Millennium Hanoi Hotel cũng tạm được cho một chuyến ngắn ngày nhưng không có gì đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6)),
(@rid, 4, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f881c2bd108416c46476d', 'HOTEL', 4, 'Nhìn chung ở Millennium Hanoi Hotel thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));

-- ==== Lotus Village Hotel Hà Nội (6a1f88182bd108416c46476c) | starRating=3 | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1f88182bd108416c46476c', 'HOTEL', 4, 'Nhìn chung ở Lotus Village Hotel Hà Nội thoải mái, tuy còn một vài bất tiện nhỏ về dịch vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f88182bd108416c46476c', 'HOTEL', 4, 'Lotus Village Hotel Hà Nội đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6)),
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f88182bd108416c46476c', 'HOTEL', 5, 'Rất hài lòng với Lotus Village Hotel Hà Nội, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6)),
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f88182bd108416c46476c', 'HOTEL', 5, 'Lotus Village Hotel Hà Nội thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên nhiệt tình, hỗ trợ nhanh chóng mọi yêu cầu.', NOW(6)),
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f88182bd108416c46476c', 'HOTEL', 5, 'Kỳ nghỉ tại Lotus Village Hotel Hà Nội vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f88182bd108416c46476c', 'HOTEL', 2, 'Lotus Village Hotel Hà Nội chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 6, 'Tiện nghi khá hạn chế, thiếu nhiều dịch vụ cần thiết.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f88182bd108416c46476c', 'HOTEL', 3, 'Mình thấy Lotus Village Hotel Hà Nội ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Tiện nghi ở mức cơ bản, chưa có gì nổi bật.', NOW(6)),
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f88182bd108416c46476c', 'HOTEL', 3, 'Lotus Village Hotel Hà Nội tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng đơn giản, ít lựa chọn món.', NOW(6)),
(@rid, 8, 'Wifi khá yếu, thỉnh thoảng bị rớt mạng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f88182bd108416c46476c', 'HOTEL', 4, 'Khá hài lòng với kỳ nghỉ tại Lotus Village Hotel Hà Nội, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Wifi dùng tạm ổn, tốc độ trung bình.', NOW(6)),
(@rid, 1, 'Phòng khá sạch, chỉ có vài góc nhỏ chưa được chú ý kỹ.', NOW(6)),
(@rid, 2, 'Phòng khá thoải mái, chỉ hơi nhỏ so với hình trên web.', NOW(6));

-- ==== Nature Hotel - Lac Long Quan (6a1f881d2bd108416c46476e) | starRating=4 | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1f881d2bd108416c46476e', 'HOTEL', 4, 'Nature Hotel - Lac Long Quan đáng ở, dù không phải mọi thứ đều hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1f881d2bd108416c46476e', 'HOTEL', 5, 'Rất hài lòng với Nature Hotel - Lac Long Quan, phòng thoáng mát và dịch vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Đi bộ ra các điểm tham quan chính rất gần, quá tiện.', NOW(6)),
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1f881d2bd108416c46476e', 'HOTEL', 5, 'Nature Hotel - Lac Long Quan thực sự tuyệt vời, phòng ốc sạch đẹp và nhân viên phục vụ rất chu đáo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 5, 'Giá cả rất hợp lý so với chất lượng nhận được.', NOW(6)),
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1f881d2bd108416c46476e', 'HOTEL', 5, 'Kỳ nghỉ tại Nature Hotel - Lac Long Quan vượt xa mong đợi, chắc chắn sẽ quay lại lần sau.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 6, 'Đầy đủ tiện ích cần thiết cho kỳ nghỉ thoải mái.', NOW(6)),
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1f881d2bd108416c46476e', 'HOTEL', 5, 'Nature Hotel - Lac Long Quan xứng đáng 5 sao, mọi dịch vụ đều chỉn chu từ lúc nhận phòng đến khi trả phòng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 7, 'Bữa sáng phong phú, nhiều món ngon và tươi.', NOW(6)),
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1f881d2bd108416c46476e', 'HOTEL', 5, 'Trải nghiệm tại Nature Hotel - Lac Long Quan rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 8, 'Kết nối wifi tốt, tốc độ nhanh không bị giật lag.', NOW(6)),
(@rid, 1, 'Phòng ốc sạch sẽ tinh tươm, được dọn dẹp kỹ mỗi ngày.', NOW(6)),
(@rid, 2, 'Thiết kế phòng đẹp, view nhìn ra ngoài rất thích.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1f881d2bd108416c46476e', 'HOTEL', 3, 'Nature Hotel - Lac Long Quan tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 1, 'Vệ sinh ở mức chấp nhận được, chưa thực sự kỹ.', NOW(6)),
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1f881d2bd108416c46476e', 'HOTEL', 3, 'Trải nghiệm ở Nature Hotel - Lac Long Quan bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 2, 'Phòng ở mức tạm, một số thiết bị đã cũ.', NOW(6)),
(@rid, 3, 'Thái độ nhân viên bình thường, chưa thực sự chủ động hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1f881d2bd108416c46476e', 'HOTEL', 4, 'Nature Hotel - Lac Long Quan ổn, chỉ hơi tiếc vì một số hạn chế nhỏ về tiện nghi.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 3, 'Nhân viên khá thân thiện, đôi lúc phản hồi hơi chậm.', NOW(6)),
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a1f881d2bd108416c46476e', 'HOTEL', 4, 'Trải nghiệm tốt tại Nature Hotel - Lac Long Quan, phù hợp cho chuyến đi ngắn ngày.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 4, 'Vị trí ổn, đi taxi/grab ra trung tâm cũng nhanh.', NOW(6)),
(@rid, 5, 'Giá hơi cao nhưng chất lượng tương xứng.', NOW(6)),
(@rid, 6, 'Cơ bản đầy đủ, một số tiện ích chưa hoạt động tốt.', NOW(6));
