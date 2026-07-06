-- Seed demo reviews RESTAURANT cho khu vuc: ha-noi
-- So review/restaurant linh hoat (8-13), phan bo sao theo priceLevel (thay the vi khong co starRating)
-- Aspect id theo seed_aspect_definitions.sql (RESTAURANT: 9 FOOD_QUALITY,10 SERVICE,11 AMBIANCE,12 PRICE,13 HYGIENE,14 PORTION_SIZE,15 WAIT_TIME,16 LOCATION)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Lẩu Nấm Thảo Quý - Lê Đức Thọ (6a45938dc6a3905f886ca637) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a45938dc6a3905f886ca637', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Nấm Thảo Quý - Lê Đức Thọ, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45938dc6a3905f886ca637', 'RESTAURANT', 4, 'Lẩu Nấm Thảo Quý - Lê Đức Thọ ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a45938dc6a3905f886ca637', 'RESTAURANT', 5, 'Bữa ăn tại Lẩu Nấm Thảo Quý - Lê Đức Thọ vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a45938dc6a3905f886ca637', 'RESTAURANT', 5, 'Lẩu Nấm Thảo Quý - Lê Đức Thọ xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a45938dc6a3905f886ca637', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lẩu Nấm Thảo Quý - Lê Đức Thọ rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a45938dc6a3905f886ca637', 'RESTAURANT', 2, 'Lẩu Nấm Thảo Quý - Lê Đức Thọ có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45938dc6a3905f886ca637', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Nấm Thảo Quý - Lê Đức Thọ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45938dc6a3905f886ca637', 'RESTAURANT', 3, 'Lẩu Nấm Thảo Quý - Lê Đức Thọ cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45938dc6a3905f886ca637', 'RESTAURANT', 3, 'Mình thấy Lẩu Nấm Thảo Quý - Lê Đức Thọ ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45938dc6a3905f886ca637', 'RESTAURANT', 4, 'Lẩu Nấm Thảo Quý - Lê Đức Thọ khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45938dc6a3905f886ca637', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Nấm Thảo Quý - Lê Đức Thọ ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

-- ==== WuLong Hotpot - Nguyễn Khánh Toàn (6a45938ec6a3905f886ca63a) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 4, 'WuLong Hotpot - Nguyễn Khánh Toàn ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 4, 'Trải nghiệm tốt tại WuLong Hotpot - Nguyễn Khánh Toàn, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 4, 'WuLong Hotpot - Nguyễn Khánh Toàn khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 4, 'Nhìn chung ở WuLong Hotpot - Nguyễn Khánh Toàn ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 5, 'Không có gì để chê ở WuLong Hotpot - Nguyễn Khánh Toàn, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 5, 'WuLong Hotpot - Nguyễn Khánh Toàn ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn WuLong Hotpot - Nguyễn Khánh Toàn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 5, 'Rất hài lòng với WuLong Hotpot - Nguyễn Khánh Toàn, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 2, 'WuLong Hotpot - Nguyễn Khánh Toàn chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 3, 'Trải nghiệm ở WuLong Hotpot - Nguyễn Khánh Toàn bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 3, 'WuLong Hotpot - Nguyễn Khánh Toàn cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a45938ec6a3905f886ca63a', 'RESTAURANT', 3, 'Mình thấy WuLong Hotpot - Nguyễn Khánh Toàn ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ (6a45938fc6a3905f886ca63b) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 3, 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 4, 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 4, 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 5, 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 5, 'Bữa ăn tại Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 5, 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 2, 'Trải nghiệm ở Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6)),
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45938fc6a3905f886ca63b', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Nhắng Nướng - Đại Cồ Việt (6a459390c6a3905f886ca63c) | priceLevel=CHEAP | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a459390c6a3905f886ca63c', 'RESTAURANT', 3, 'Nhắng Nướng - Đại Cồ Việt cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a459390c6a3905f886ca63c', 'RESTAURANT', 4, 'Nhìn chung ở Nhắng Nướng - Đại Cồ Việt ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a459390c6a3905f886ca63c', 'RESTAURANT', 4, 'Nhắng Nướng - Đại Cồ Việt đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a459390c6a3905f886ca63c', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhắng Nướng - Đại Cồ Việt, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a459390c6a3905f886ca63c', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhắng Nướng - Đại Cồ Việt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a459390c6a3905f886ca63c', 'RESTAURANT', 5, 'Rất hài lòng với Nhắng Nướng - Đại Cồ Việt, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a459390c6a3905f886ca63c', 'RESTAURANT', 2, 'Nhắng Nướng - Đại Cồ Việt chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a459390c6a3905f886ca63c', 'RESTAURANT', 3, 'Trải nghiệm ở Nhắng Nướng - Đại Cồ Việt bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Fu Rong Hua Đinh Tiên Hoàng (6a459390c6a3905f886ca63d) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a459390c6a3905f886ca63d', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Fu Rong Hua Đinh Tiên Hoàng rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a459390c6a3905f886ca63d', 'RESTAURANT', 5, 'Không có gì để chê ở Fu Rong Hua Đinh Tiên Hoàng, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a459390c6a3905f886ca63d', 'RESTAURANT', 3, 'Trải nghiệm ở Fu Rong Hua Đinh Tiên Hoàng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a459390c6a3905f886ca63d', 'RESTAURANT', 3, 'Fu Rong Hua Đinh Tiên Hoàng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a459390c6a3905f886ca63d', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Fu Rong Hua Đinh Tiên Hoàng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a459390c6a3905f886ca63d', 'RESTAURANT', 4, 'Fu Rong Hua Đinh Tiên Hoàng khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a459390c6a3905f886ca63d', 'RESTAURANT', 4, 'Nhìn chung ở Fu Rong Hua Đinh Tiên Hoàng ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a459390c6a3905f886ca63d', 'RESTAURANT', 5, 'Fu Rong Hua Đinh Tiên Hoàng xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a459390c6a3905f886ca63d', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Fu Rong Hua Đinh Tiên Hoàng rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Nhà Hàng Lẩu Hơi Cosmos Giảng Võ (6a45939ec6a3905f886ca63e) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 4, 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà Hàng Lẩu Hơi Cosmos Giảng Võ, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 4, 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 5, 'Rất hài lòng với Nhà Hàng Lẩu Hơi Cosmos Giảng Võ, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 5, 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 5, 'Bữa ăn tại Nhà Hàng Lẩu Hơi Cosmos Giảng Võ vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 2, 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 3, 'Mình thấy Nhà Hàng Lẩu Hơi Cosmos Giảng Võ ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 3, 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45939ec6a3905f886ca63e', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà Hàng Lẩu Hơi Cosmos Giảng Võ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Lẩu Bò Triều Châu Số 1 Phan Chu Trinh (6a45939fc6a3905f886ca63f) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Bò Triều Châu Số 1 Phan Chu Trinh bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 3, 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 3, 'Mình thấy Lẩu Bò Triều Châu Số 1 Phan Chu Trinh ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 4, 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Bò Triều Châu Số 1 Phan Chu Trinh ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 4, 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Bò Triều Châu Số 1 Phan Chu Trinh, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 5, 'Không có gì để chê ở Lẩu Bò Triều Châu Số 1 Phan Chu Trinh, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 5, 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Bò Triều Châu Số 1 Phan Chu Trinh.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a45939fc6a3905f886ca63f', 'RESTAURANT', 2, 'Trải nghiệm ở Lẩu Bò Triều Châu Số 1 Phan Chu Trinh không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

-- ==== King BBQ Buffet Royal City (6a4593a0c6a3905f886ca640) | priceLevel=EXPENSIVE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn King BBQ Buffet Royal City.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'Rất hài lòng với King BBQ Buffet Royal City, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 3, 'King BBQ Buffet Royal City tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 3, 'Trải nghiệm ở King BBQ Buffet Royal City bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 4, 'King BBQ Buffet Royal City đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại King BBQ Buffet Royal City, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 4, 'King BBQ Buffet Royal City ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 4, 'Trải nghiệm tốt tại King BBQ Buffet Royal City, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn King BBQ Buffet Royal City.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'Rất hài lòng với King BBQ Buffet Royal City, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'King BBQ Buffet Royal City thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a0c6a3905f886ca640', 'RESTAURANT', 5, 'Bữa ăn tại King BBQ Buffet Royal City vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Lộc-Ally Restaurant - Cát Linh (6a4593a0c6a3905f886ca641) | priceLevel=LUXURY | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Rất hài lòng với Lộc-Ally Restaurant - Cát Linh, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Lộc-Ally Restaurant - Cát Linh thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Bữa ăn tại Lộc-Ally Restaurant - Cát Linh vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Lộc-Ally Restaurant - Cát Linh xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lộc-Ally Restaurant - Cát Linh rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 3, 'Lộc-Ally Restaurant - Cát Linh tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lộc-Ally Restaurant - Cát Linh, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 4, 'Lộc-Ally Restaurant - Cát Linh khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 4, 'Nhìn chung ở Lộc-Ally Restaurant - Cát Linh ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Lộc-Ally Restaurant - Cát Linh thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Bữa ăn tại Lộc-Ally Restaurant - Cát Linh vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Lộc-Ally Restaurant - Cát Linh xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a0c6a3905f886ca641', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lộc-Ally Restaurant - Cát Linh rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Nhà hàng MALA buffet - Đường Láng (6a4593a1c6a3905f886ca642) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 2, 'Nhà hàng MALA buffet - Đường Láng chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng MALA buffet - Đường Láng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 3, 'Nhà hàng MALA buffet - Đường Láng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng MALA buffet - Đường Láng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 4, 'Nhà hàng MALA buffet - Đường Láng ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Nhà hàng MALA buffet - Đường Láng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng MALA buffet - Đường Láng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a1c6a3905f886ca642', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng MALA buffet - Đường Láng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== SHACHA NIÚ Lương Định Của (6a4593a1c6a3905f886ca643) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 4, 'Nhìn chung ở SHACHA NIÚ Lương Định Của ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 5, 'SHACHA NIÚ Lương Định Của xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở SHACHA NIÚ Lương Định Của rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 5, 'Không có gì để chê ở SHACHA NIÚ Lương Định Của, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 5, 'SHACHA NIÚ Lương Định Của ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 3, 'SHACHA NIÚ Lương Định Của cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 3, 'Mình thấy SHACHA NIÚ Lương Định Của ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 4, 'SHACHA NIÚ Lương Định Của đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a1c6a3905f886ca643', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại SHACHA NIÚ Lương Định Của, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng (6a4593a2c6a3905f886ca644) | priceLevel=EXPENSIVE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 3, 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 3, 'Mình thấy Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 4, 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 4, 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 5, 'Rất hài lòng với Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 5, 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 5, 'Bữa ăn tại Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 5, 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a2c6a3905f886ca644', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== Lẩu Đức Trọc - Dịch Vọng Hậu (6a4593a5c6a3905f886ca645) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Đức Trọc - Dịch Vọng Hậu, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 5, 'Không có gì để chê ở Lẩu Đức Trọc - Dịch Vọng Hậu, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 5, 'Lẩu Đức Trọc - Dịch Vọng Hậu ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Đức Trọc - Dịch Vọng Hậu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 2, 'Trải nghiệm ở Lẩu Đức Trọc - Dịch Vọng Hậu không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6)),
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 3, 'Lẩu Đức Trọc - Dịch Vọng Hậu tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Đức Trọc - Dịch Vọng Hậu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 3, 'Lẩu Đức Trọc - Dịch Vọng Hậu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Đức Trọc - Dịch Vọng Hậu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 4, 'Lẩu Đức Trọc - Dịch Vọng Hậu khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a5c6a3905f886ca645', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Đức Trọc - Dịch Vọng Hậu ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Buffet Nướng Yakimono - GO! Thăng Long (6a4593a5c6a3905f886ca646) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 4, 'Buffet Nướng Yakimono - GO! Thăng Long ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Buffet Nướng Yakimono - GO! Thăng Long, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 4, 'Buffet Nướng Yakimono - GO! Thăng Long khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 4, 'Nhìn chung ở Buffet Nướng Yakimono - GO! Thăng Long ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 5, 'Buffet Nướng Yakimono - GO! Thăng Long thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 5, 'Bữa ăn tại Buffet Nướng Yakimono - GO! Thăng Long vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 5, 'Buffet Nướng Yakimono - GO! Thăng Long xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Buffet Nướng Yakimono - GO! Thăng Long rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 2, 'Buffet Nướng Yakimono - GO! Thăng Long chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 3, 'Trải nghiệm ở Buffet Nướng Yakimono - GO! Thăng Long bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 3, 'Buffet Nướng Yakimono - GO! Thăng Long cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a5c6a3905f886ca646', 'RESTAURANT', 3, 'Mình thấy Buffet Nướng Yakimono - GO! Thăng Long ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Lẩu Nấm Gia Khánh Mộ Lao (6a4593a6c6a3905f886ca647) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Nấm Gia Khánh Mộ Lao bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 3, 'Lẩu Nấm Gia Khánh Mộ Lao cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 3, 'Mình thấy Lẩu Nấm Gia Khánh Mộ Lao ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 4, 'Lẩu Nấm Gia Khánh Mộ Lao đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Nấm Gia Khánh Mộ Lao, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 4, 'Lẩu Nấm Gia Khánh Mộ Lao ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Nấm Gia Khánh Mộ Lao, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 5, 'Không có gì để chê ở Lẩu Nấm Gia Khánh Mộ Lao, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 5, 'Lẩu Nấm Gia Khánh Mộ Lao ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Nấm Gia Khánh Mộ Lao.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 5, 'Rất hài lòng với Lẩu Nấm Gia Khánh Mộ Lao, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 5, 'Lẩu Nấm Gia Khánh Mộ Lao thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a6c6a3905f886ca647', 'RESTAURANT', 2, 'Lẩu Nấm Gia Khánh Mộ Lao có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

-- ==== Nhà hàng Wang Wang - Hồ Tùng Mậu (6a4593a7c6a3905f886ca648) | priceLevel=CHEAP | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng Wang Wang - Hồ Tùng Mậu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Wang Wang - Hồ Tùng Mậu, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 2, 'Nhà hàng Wang Wang - Hồ Tùng Mậu có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Wang Wang - Hồ Tùng Mậu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 3, 'Nhà hàng Wang Wang - Hồ Tùng Mậu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Nhà hàng Wang Wang - Hồ Tùng Mậu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 4, 'Nhà hàng Wang Wang - Hồ Tùng Mậu khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a7c6a3905f886ca648', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Wang Wang - Hồ Tùng Mậu ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== Làng Niêu & Nướng Trần Văn Lai (6a4593a8c6a3905f886ca649) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 3, 'Mình thấy Làng Niêu & Nướng Trần Văn Lai ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 3, 'Làng Niêu & Nướng Trần Văn Lai tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Làng Niêu & Nướng Trần Văn Lai, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 4, 'Làng Niêu & Nướng Trần Văn Lai ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Làng Niêu & Nướng Trần Văn Lai, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 5, 'Không có gì để chê ở Làng Niêu & Nướng Trần Văn Lai, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 5, 'Làng Niêu & Nướng Trần Văn Lai ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Làng Niêu & Nướng Trần Văn Lai.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a8c6a3905f886ca649', 'RESTAURANT', 2, 'Làng Niêu & Nướng Trần Văn Lai chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6)),
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6));

-- ==== Lẩu Nấm Gia Khánh - Đền Lừ (6a4593a9c6a3905f886ca64a) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 4, 'Lẩu Nấm Gia Khánh - Đền Lừ đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 5, 'Bữa ăn tại Lẩu Nấm Gia Khánh - Đền Lừ vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 5, 'Lẩu Nấm Gia Khánh - Đền Lừ xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lẩu Nấm Gia Khánh - Đền Lừ rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 2, 'Lẩu Nấm Gia Khánh - Đền Lừ chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Nấm Gia Khánh - Đền Lừ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 3, 'Lẩu Nấm Gia Khánh - Đền Lừ cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 3, 'Mình thấy Lẩu Nấm Gia Khánh - Đền Lừ ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 4, 'Lẩu Nấm Gia Khánh - Đền Lừ ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593a9c6a3905f886ca64a', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Nấm Gia Khánh - Đền Lừ, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== Hi-Food BBQ - Vinhomes Smart City (6a4593aac6a3905f886ca64b) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 3, 'Trải nghiệm ở Hi-Food BBQ - Vinhomes Smart City bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 3, 'Hi-Food BBQ - Vinhomes Smart City cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Hi-Food BBQ - Vinhomes Smart City, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 4, 'Hi-Food BBQ - Vinhomes Smart City khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 4, 'Nhìn chung ở Hi-Food BBQ - Vinhomes Smart City ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 4, 'Hi-Food BBQ - Vinhomes Smart City đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 5, 'Rất hài lòng với Hi-Food BBQ - Vinhomes Smart City, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 5, 'Hi-Food BBQ - Vinhomes Smart City thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 5, 'Bữa ăn tại Hi-Food BBQ - Vinhomes Smart City vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 2, 'Hi-Food BBQ - Vinhomes Smart City chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593aac6a3905f886ca64b', 'RESTAURANT', 3, 'Mình thấy Hi-Food BBQ - Vinhomes Smart City ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu (6a4593aac6a3905f886ca64c) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 5, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 2, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 3, 'Trải nghiệm ở Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 3, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 3, 'Mình thấy Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 4, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 4, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 4, 'Nhìn chung ở Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 5, 'Không có gì để chê ở Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593aac6a3905f886ca64c', 'RESTAURANT', 5, 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Lẩu Nướng GangBuk - Trường Chinh (6a4593acc6a3905f886ca64d) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Nướng GangBuk - Trường Chinh, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 5, 'Không có gì để chê ở Lẩu Nướng GangBuk - Trường Chinh, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 5, 'Lẩu Nướng GangBuk - Trường Chinh ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Nướng GangBuk - Trường Chinh.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 5, 'Rất hài lòng với Lẩu Nướng GangBuk - Trường Chinh, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 5, 'Lẩu Nướng GangBuk - Trường Chinh thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 2, 'Lẩu Nướng GangBuk - Trường Chinh có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 3, 'Lẩu Nướng GangBuk - Trường Chinh cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 3, 'Mình thấy Lẩu Nướng GangBuk - Trường Chinh ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 3, 'Lẩu Nướng GangBuk - Trường Chinh tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Nướng GangBuk - Trường Chinh, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 4, 'Lẩu Nướng GangBuk - Trường Chinh ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a4593acc6a3905f886ca64d', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Nướng GangBuk - Trường Chinh, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

-- ==== Vườn Nướng BBQ - Vinhomes Smart City (6a4593adc6a3905f886ca64e) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 4, 'Vườn Nướng BBQ - Vinhomes Smart City khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 4, 'Nhìn chung ở Vườn Nướng BBQ - Vinhomes Smart City ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Vườn Nướng BBQ - Vinhomes Smart City.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 5, 'Rất hài lòng với Vườn Nướng BBQ - Vinhomes Smart City, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 2, 'Trải nghiệm ở Vườn Nướng BBQ - Vinhomes Smart City không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 3, 'Trải nghiệm ở Vườn Nướng BBQ - Vinhomes Smart City bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 3, 'Vườn Nướng BBQ - Vinhomes Smart City cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593adc6a3905f886ca64e', 'RESTAURANT', 4, 'Nhìn chung ở Vườn Nướng BBQ - Vinhomes Smart City ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn (6a4593aec6a3905f886ca64f) | priceLevel=CHEAP | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 5, 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 2, 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 2, 'Trải nghiệm ở Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 3, 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 4, 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a4593aec6a3905f886ca64f', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));
