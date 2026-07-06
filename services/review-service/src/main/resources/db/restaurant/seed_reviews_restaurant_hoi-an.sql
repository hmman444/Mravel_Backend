-- Seed demo reviews RESTAURANT cho khu vuc: hoi-an
-- So review/restaurant linh hoat (8-13), phan bo sao theo priceLevel (thay the vi khong co starRating)
-- Aspect id theo seed_aspect_definitions.sql (RESTAURANT: 9 FOOD_QUALITY,10 SERVICE,11 AMBIANCE,12 PRICE,13 HYGIENE,14 PORTION_SIZE,15 WAIT_TIME,16 LOCATION)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Red Bean Hoi An - Hùng Vương (6a1ba807396a1b2be58bfe58) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 2, 'Red Bean Hoi An - Hùng Vương chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 3, 'Trải nghiệm ở Red Bean Hoi An - Hùng Vương bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 3, 'Red Bean Hoi An - Hùng Vương cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Red Bean Hoi An - Hùng Vương, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 4, 'Red Bean Hoi An - Hùng Vương ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Red Bean Hoi An - Hùng Vương, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Red Bean Hoi An - Hùng Vương.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba807396a1b2be58bfe58', 'RESTAURANT', 5, 'Rất hài lòng với Red Bean Hoi An - Hùng Vương, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== The Temple Restaurant & Lounge - Hùng Vương (6a1ba807396a1b2be58bfe59) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 3, 'Trải nghiệm ở The Temple Restaurant & Lounge - Hùng Vương bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 4, 'The Temple Restaurant & Lounge - Hùng Vương đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại The Temple Restaurant & Lounge - Hùng Vương, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 4, 'The Temple Restaurant & Lounge - Hùng Vương ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 5, 'The Temple Restaurant & Lounge - Hùng Vương ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn The Temple Restaurant & Lounge - Hùng Vương.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 5, 'Rất hài lòng với The Temple Restaurant & Lounge - Hùng Vương, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 5, 'The Temple Restaurant & Lounge - Hùng Vương thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a1ba807396a1b2be58bfe59', 'RESTAURANT', 3, 'Trải nghiệm ở The Temple Restaurant & Lounge - Hùng Vương bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An (6a47ac3593a0c40dc01724e8) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 3, 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 3, 'Mình thấy Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 4, 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 4, 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 5, 'Rất hài lòng với Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 5, 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 5, 'Bữa ăn tại Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 2, 'Trải nghiệm ở Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6)),
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a47ac3593a0c40dc01724e8', 'RESTAURANT', 3, 'Mình thấy Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));
