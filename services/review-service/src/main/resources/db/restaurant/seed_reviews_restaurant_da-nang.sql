-- Seed demo reviews RESTAURANT cho khu vuc: da-nang
-- So review/restaurant linh hoat (8-13), phan bo sao theo priceLevel (thay the vi khong co starRating)
-- Aspect id theo seed_aspect_definitions.sql (RESTAURANT: 9 FOOD_QUALITY,10 SERVICE,11 AMBIANCE,12 PRICE,13 HYGIENE,14 PORTION_SIZE,15 WAIT_TIME,16 LOCATION)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Queen Palace Karaoke - Đà Nẵng (6a47ac1693a0c40dc01724de) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 3, 'Queen Palace Karaoke - Đà Nẵng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 3, 'Mình thấy Queen Palace Karaoke - Đà Nẵng ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 4, 'Queen Palace Karaoke - Đà Nẵng ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Queen Palace Karaoke - Đà Nẵng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 4, 'Queen Palace Karaoke - Đà Nẵng khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Queen Palace Karaoke - Đà Nẵng rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 5, 'Không có gì để chê ở Queen Palace Karaoke - Đà Nẵng, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 5, 'Queen Palace Karaoke - Đà Nẵng ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 2, 'Trải nghiệm ở Queen Palace Karaoke - Đà Nẵng không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac1693a0c40dc01724de', 'RESTAURANT', 3, 'Mình thấy Queen Palace Karaoke - Đà Nẵng ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Butcher Steak & BBQ Lê Quang Đạo (6a47ac1893a0c40dc01724df) | priceLevel=EXPENSIVE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 5, 'Rất hài lòng với Butcher Steak & BBQ Lê Quang Đạo, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 5, 'Butcher Steak & BBQ Lê Quang Đạo thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 5, 'Bữa ăn tại Butcher Steak & BBQ Lê Quang Đạo vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 3, 'Butcher Steak & BBQ Lê Quang Đạo cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 3, 'Mình thấy Butcher Steak & BBQ Lê Quang Đạo ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 4, 'Butcher Steak & BBQ Lê Quang Đạo đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Butcher Steak & BBQ Lê Quang Đạo, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 4, 'Butcher Steak & BBQ Lê Quang Đạo ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Butcher Steak & BBQ Lê Quang Đạo, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 5, 'Butcher Steak & BBQ Lê Quang Đạo thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac1893a0c40dc01724df', 'RESTAURANT', 5, 'Bữa ăn tại Butcher Steak & BBQ Lê Quang Đạo vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== Bistecca Đà Nẵng (6a47ac1993a0c40dc01724e0) | priceLevel=EXPENSIVE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 4, 'Bistecca Đà Nẵng ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Bistecca Đà Nẵng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Bistecca Đà Nẵng xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Bistecca Đà Nẵng rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Không có gì để chê ở Bistecca Đà Nẵng, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Bistecca Đà Nẵng ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Bistecca Đà Nẵng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 5, 'Rất hài lòng với Bistecca Đà Nẵng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 3, 'Bistecca Đà Nẵng tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 3, 'Trải nghiệm ở Bistecca Đà Nẵng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 4, 'Bistecca Đà Nẵng đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac1993a0c40dc01724e0', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Bistecca Đà Nẵng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Phố Biển - Nguyễn Tất Thành (6a47ac1b93a0c40dc01724e1) | priceLevel=CHEAP | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 2, 'Phố Biển - Nguyễn Tất Thành có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 2, 'Phố Biển - Nguyễn Tất Thành chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 3, 'Mình thấy Phố Biển - Nguyễn Tất Thành ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 3, 'Phố Biển - Nguyễn Tất Thành tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 3, 'Trải nghiệm ở Phố Biển - Nguyễn Tất Thành bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 4, 'Phố Biển - Nguyễn Tất Thành ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Phố Biển - Nguyễn Tất Thành, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 4, 'Phố Biển - Nguyễn Tất Thành khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 4, 'Nhìn chung ở Phố Biển - Nguyễn Tất Thành ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 5, 'Phố Biển - Nguyễn Tất Thành xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Phố Biển - Nguyễn Tất Thành rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 5, 'Không có gì để chê ở Phố Biển - Nguyễn Tất Thành, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac1b93a0c40dc01724e1', 'RESTAURANT', 5, 'Phố Biển - Nguyễn Tất Thành ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== Buffet Hải Sản Mr Mộc Đà Nẵng (6a47ac2093a0c40dc01724e2) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 3, 'Buffet Hải Sản Mr Mộc Đà Nẵng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 4, 'Nhìn chung ở Buffet Hải Sản Mr Mộc Đà Nẵng ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 4, 'Buffet Hải Sản Mr Mộc Đà Nẵng đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Buffet Hải Sản Mr Mộc Đà Nẵng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Buffet Hải Sản Mr Mộc Đà Nẵng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 5, 'Rất hài lòng với Buffet Hải Sản Mr Mộc Đà Nẵng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 2, 'Buffet Hải Sản Mr Mộc Đà Nẵng chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac2093a0c40dc01724e2', 'RESTAURANT', 3, 'Trải nghiệm ở Buffet Hải Sản Mr Mộc Đà Nẵng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Nhà hàng Gang Yu Hotpot Đà Nẵng (6a47ac2393a0c40dc01724e3) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Gang Yu Hotpot Đà Nẵng ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 4, 'Nhà hàng Gang Yu Hotpot Đà Nẵng đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 5, 'Nhà hàng Gang Yu Hotpot Đà Nẵng ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng Gang Yu Hotpot Đà Nẵng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Gang Yu Hotpot Đà Nẵng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 2, 'Nhà hàng Gang Yu Hotpot Đà Nẵng chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6)),
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Gang Yu Hotpot Đà Nẵng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 3, 'Nhà hàng Gang Yu Hotpot Đà Nẵng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac2393a0c40dc01724e3', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Gang Yu Hotpot Đà Nẵng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== Butcher Grilled Hoàng Kế Viêm (6a47ac2693a0c40dc01724e4) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 5, 'Không có gì để chê ở Butcher Grilled Hoàng Kế Viêm, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 5, 'Butcher Grilled Hoàng Kế Viêm ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 2, 'Trải nghiệm ở Butcher Grilled Hoàng Kế Viêm không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6)),
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 3, 'Mình thấy Butcher Grilled Hoàng Kế Viêm ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 3, 'Butcher Grilled Hoàng Kế Viêm tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 3, 'Trải nghiệm ở Butcher Grilled Hoàng Kế Viêm bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 4, 'Butcher Grilled Hoàng Kế Viêm đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Butcher Grilled Hoàng Kế Viêm, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 4, 'Butcher Grilled Hoàng Kế Viêm ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac2693a0c40dc01724e4', 'RESTAURANT', 5, 'Butcher Grilled Hoàng Kế Viêm ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại (6a47ac2a93a0c40dc01724e5) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 3, 'Trải nghiệm ở Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 4, 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 4, 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 4, 'Nhìn chung ở Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 5, 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 5, 'Không có gì để chê ở Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 2, 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 3, 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac2a93a0c40dc01724e5', 'RESTAURANT', 3, 'Mình thấy Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

-- ==== Cơm Niêu 3 Cá Bống Nguyễn Tri Phương (6a47ac2f93a0c40dc01724e6) | priceLevel=CHEAP | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Cơm Niêu 3 Cá Bống Nguyễn Tri Phương.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 5, 'Rất hài lòng với Cơm Niêu 3 Cá Bống Nguyễn Tri Phương, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 2, 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 2, 'Trải nghiệm ở Cơm Niêu 3 Cá Bống Nguyễn Tri Phương không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6)),
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 3, 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 3, 'Mình thấy Cơm Niêu 3 Cá Bống Nguyễn Tri Phương ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 3, 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Cơm Niêu 3 Cá Bống Nguyễn Tri Phương, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 4, 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 4, 'Nhìn chung ở Cơm Niêu 3 Cá Bống Nguyễn Tri Phương ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 4, 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac2f93a0c40dc01724e6', 'RESTAURANT', 5, 'Bữa ăn tại Cơm Niêu 3 Cá Bống Nguyễn Tri Phương vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Công Viên – Nhà Hàng Cá Voi (6a47ac3293a0c40dc01724e7) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Công Viên – Nhà Hàng Cá Voi, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 4, 'Công Viên – Nhà Hàng Cá Voi khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 5, 'Bữa ăn tại Công Viên – Nhà Hàng Cá Voi vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 5, 'Công Viên – Nhà Hàng Cá Voi xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Công Viên – Nhà Hàng Cá Voi rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 5, 'Không có gì để chê ở Công Viên – Nhà Hàng Cá Voi, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 5, 'Công Viên – Nhà Hàng Cá Voi ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 2, 'Công Viên – Nhà Hàng Cá Voi chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 3, 'Mình thấy Công Viên – Nhà Hàng Cá Voi ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 3, 'Công Viên – Nhà Hàng Cá Voi tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 3, 'Trải nghiệm ở Công Viên – Nhà Hàng Cá Voi bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 4, 'Công Viên – Nhà Hàng Cá Voi ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3293a0c40dc01724e7', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Công Viên – Nhà Hàng Cá Voi, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== Cơm Niêu Nhà Đỏ 2 (6a47ac3593a0c40dc01724e9) | priceLevel=CHEAP | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 2, 'Cơm Niêu Nhà Đỏ 2 chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 3, 'Trải nghiệm ở Cơm Niêu Nhà Đỏ 2 bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 3, 'Cơm Niêu Nhà Đỏ 2 cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Cơm Niêu Nhà Đỏ 2, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 4, 'Cơm Niêu Nhà Đỏ 2 ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Cơm Niêu Nhà Đỏ 2, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Cơm Niêu Nhà Đỏ 2.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3593a0c40dc01724e9', 'RESTAURANT', 5, 'Rất hài lòng với Cơm Niêu Nhà Đỏ 2, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du (6a47ac3693a0c40dc01724ea) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 3, 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 4, 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 5, 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3693a0c40dc01724ea', 'RESTAURANT', 2, 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

-- ==== Ngon Phố Đà - Homey Authentic Vietnamese Cuisine (6a47ac3793a0c40dc01724eb) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 4, 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Ngon Phố Đà - Homey Authentic Vietnamese Cuisine, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 4, 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 5, 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Ngon Phố Đà - Homey Authentic Vietnamese Cuisine.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 5, 'Rất hài lòng với Ngon Phố Đà - Homey Authentic Vietnamese Cuisine, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 2, 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 3, 'Trải nghiệm ở Ngon Phố Đà - Homey Authentic Vietnamese Cuisine bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 3, 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3793a0c40dc01724eb', 'RESTAURANT', 3, 'Mình thấy Ngon Phố Đà - Homey Authentic Vietnamese Cuisine ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== DOM - The Wine Bistro - Phan Bội Châu (6a47ac3893a0c40dc01724ec) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở DOM - The Wine Bistro - Phan Bội Châu rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 5, 'Không có gì để chê ở DOM - The Wine Bistro - Phan Bội Châu, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 2, 'DOM - The Wine Bistro - Phan Bội Châu có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 3, 'DOM - The Wine Bistro - Phan Bội Châu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 3, 'Mình thấy DOM - The Wine Bistro - Phan Bội Châu ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 3, 'DOM - The Wine Bistro - Phan Bội Châu tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại DOM - The Wine Bistro - Phan Bội Châu, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 4, 'DOM - The Wine Bistro - Phan Bội Châu ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 4, 'Trải nghiệm tốt tại DOM - The Wine Bistro - Phan Bội Châu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 4, 'DOM - The Wine Bistro - Phan Bội Châu khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3893a0c40dc01724ec', 'RESTAURANT', 5, 'DOM - The Wine Bistro - Phan Bội Châu ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

-- ==== Thai Market - Trần Quốc Toản (6a47ac3993a0c40dc01724ed) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 4, 'Thai Market - Trần Quốc Toản ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Thai Market - Trần Quốc Toản, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 4, 'Thai Market - Trần Quốc Toản khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 4, 'Nhìn chung ở Thai Market - Trần Quốc Toản ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 5, 'Thai Market - Trần Quốc Toản thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 5, 'Bữa ăn tại Thai Market - Trần Quốc Toản vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 5, 'Thai Market - Trần Quốc Toản xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Thai Market - Trần Quốc Toản rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 2, 'Thai Market - Trần Quốc Toản chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 3, 'Trải nghiệm ở Thai Market - Trần Quốc Toản bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 3, 'Thai Market - Trần Quốc Toản cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3993a0c40dc01724ed', 'RESTAURANT', 3, 'Mình thấy Thai Market - Trần Quốc Toản ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Thai Market Restaurant - 183 Nguyễn Văn Thoại (6a47ac3a93a0c40dc01724ee) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 5, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 2, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 3, 'Mình thấy Thai Market Restaurant - 183 Nguyễn Văn Thoại ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 3, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 3, 'Trải nghiệm ở Thai Market Restaurant - 183 Nguyễn Văn Thoại bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 4, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Thai Market Restaurant - 183 Nguyễn Văn Thoại, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 4, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 4, 'Nhìn chung ở Thai Market Restaurant - 183 Nguyễn Văn Thoại ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Thai Market Restaurant - 183 Nguyễn Văn Thoại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 5, 'Rất hài lòng với Thai Market Restaurant - 183 Nguyễn Văn Thoại, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 5, 'Thai Market Restaurant - 183 Nguyễn Văn Thoại thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3a93a0c40dc01724ee', 'RESTAURANT', 5, 'Bữa ăn tại Thai Market Restaurant - 183 Nguyễn Văn Thoại vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

-- ==== Thai Market Restaurant - Yên Bái (6a47ac3b93a0c40dc01724ef) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Thai Market Restaurant - Yên Bái.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 5, 'Rất hài lòng với Thai Market Restaurant - Yên Bái, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 2, 'Thai Market Restaurant - Yên Bái có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 3, 'Trải nghiệm ở Thai Market Restaurant - Yên Bái bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 3, 'Thai Market Restaurant - Yên Bái cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Thai Market Restaurant - Yên Bái, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 4, 'Thai Market Restaurant - Yên Bái khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3b93a0c40dc01724ef', 'RESTAURANT', 4, 'Nhìn chung ở Thai Market Restaurant - Yên Bái ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng (6a47ac4093a0c40dc01724f0) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 5, 'Rất hài lòng với East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 5, 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 3, 'Trải nghiệm ở East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 3, 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 4, 'Trải nghiệm tốt tại East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 4, 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 4, 'Nhìn chung ở East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac4093a0c40dc01724f0', 'RESTAURANT', 5, 'Rất hài lòng với East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Nhà hàng Cây Dừa 1 Nguyễn Tất Thành (6a47ac4593a0c40dc01724f1) | priceLevel=CHEAP | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 2, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 2, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 3, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 3, 'Mình thấy Nhà hàng Cây Dừa 1 Nguyễn Tất Thành ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 3, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Cây Dừa 1 Nguyễn Tất Thành ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 4, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Cây Dừa 1 Nguyễn Tất Thành, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 5, 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac4593a0c40dc01724f1', 'RESTAURANT', 5, 'Bữa ăn tại Nhà hàng Cây Dừa 1 Nguyễn Tất Thành vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Country BBQ & Beer Trần Bạch Đằng (6a47ac4693a0c40dc01724f2) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Country BBQ & Beer Trần Bạch Đằng, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 4, 'Country BBQ & Beer Trần Bạch Đằng ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Country BBQ & Beer Trần Bạch Đằng, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 4, 'Country BBQ & Beer Trần Bạch Đằng khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 5, 'Country BBQ & Beer Trần Bạch Đằng ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Country BBQ & Beer Trần Bạch Đằng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 5, 'Rất hài lòng với Country BBQ & Beer Trần Bạch Đằng, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 2, 'Trải nghiệm ở Country BBQ & Beer Trần Bạch Đằng không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 3, 'Trải nghiệm ở Country BBQ & Beer Trần Bạch Đằng bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 3, 'Country BBQ & Beer Trần Bạch Đằng cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac4693a0c40dc01724f2', 'RESTAURANT', 3, 'Mình thấy Country BBQ & Beer Trần Bạch Đằng ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Sushi World - Phan Bội Châu (6a47ac5d93a0c40dc01724f3) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 5, 'Sushi World - Phan Bội Châu xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Sushi World - Phan Bội Châu rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 2, 'Sushi World - Phan Bội Châu chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 3, 'Trải nghiệm ở Sushi World - Phan Bội Châu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 3, 'Sushi World - Phan Bội Châu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 3, 'Mình thấy Sushi World - Phan Bội Châu ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 4, 'Sushi World - Phan Bội Châu ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Sushi World - Phan Bội Châu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 4, 'Sushi World - Phan Bội Châu khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 4, 'Nhìn chung ở Sushi World - Phan Bội Châu ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 5, 'Không có gì để chê ở Sushi World - Phan Bội Châu, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac5d93a0c40dc01724f3', 'RESTAURANT', 5, 'Sushi World - Phan Bội Châu ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Nhà hàng Zzuggubbong – Nguyễn Hữu Thông (6a47ac6093a0c40dc01724f4) | priceLevel=CHEAP | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Nhà hàng Zzuggubbong – Nguyễn Hữu Thông, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 4, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Zzuggubbong – Nguyễn Hữu Thông ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 4, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Zzuggubbong – Nguyễn Hữu Thông, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 5, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 5, 'Bữa ăn tại Nhà hàng Zzuggubbong – Nguyễn Hữu Thông vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 5, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 2, 'Trải nghiệm ở Nhà hàng Zzuggubbong – Nguyễn Hữu Thông không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 2, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Zzuggubbong – Nguyễn Hữu Thông bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 3, 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac6093a0c40dc01724f4', 'RESTAURANT', 3, 'Mình thấy Nhà hàng Zzuggubbong – Nguyễn Hữu Thông ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo (6a47ac6193a0c40dc01724f5) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 4, 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 5, 'Rất hài lòng với Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 2, 'Trải nghiệm ở Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 3, 'Trải nghiệm ở Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 3, 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac6193a0c40dc01724f5', 'RESTAURANT', 4, 'Nhìn chung ở Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Thai Market Bình Minh 5 (6a47ac6293a0c40dc01724f6) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 4, 'Nhìn chung ở Thai Market Bình Minh 5 ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 4, 'Thai Market Bình Minh 5 đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 5, 'Rất hài lòng với Thai Market Bình Minh 5, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 5, 'Thai Market Bình Minh 5 thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 5, 'Bữa ăn tại Thai Market Bình Minh 5 vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 2, 'Thai Market Bình Minh 5 chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 3, 'Mình thấy Thai Market Bình Minh 5 ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 3, 'Thai Market Bình Minh 5 tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac6293a0c40dc01724f6', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Thai Market Bình Minh 5, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

-- ==== The Holiday Beach Club & Dining (6a47ac6693a0c40dc01724f7) | priceLevel=CHEAP | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 4, 'The Holiday Beach Club & Dining đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại The Holiday Beach Club & Dining, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 5, 'The Holiday Beach Club & Dining thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 5, 'Bữa ăn tại The Holiday Beach Club & Dining vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 2, 'The Holiday Beach Club & Dining chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6)),
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 2, 'Trải nghiệm ở The Holiday Beach Club & Dining không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 3, 'The Holiday Beach Club & Dining tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 3, 'Trải nghiệm ở The Holiday Beach Club & Dining bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 3, 'The Holiday Beach Club & Dining cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac6693a0c40dc01724f7', 'RESTAURANT', 4, 'Trải nghiệm tốt tại The Holiday Beach Club & Dining, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));
