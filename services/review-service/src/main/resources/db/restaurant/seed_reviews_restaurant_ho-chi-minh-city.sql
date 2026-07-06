-- Seed demo reviews RESTAURANT cho khu vuc: ho-chi-minh-city
-- So review/restaurant linh hoat (8-13), phan bo sao theo priceLevel (thay the vi khong co starRating)
-- Aspect id theo seed_aspect_definitions.sql (RESTAURANT: 9 FOOD_QUALITY,10 SERVICE,11 AMBIANCE,12 PRICE,13 HYGIENE,14 PORTION_SIZE,15 WAIT_TIME,16 LOCATION)
USE mravel_review;
SET NAMES utf8mb4;

-- ==== Tân Hải Vân - Phan Văn Trị (6a2d41c1cbb65b24e710164f) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 3, 'Mình thấy Tân Hải Vân - Phan Văn Trị ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 4, 'Tân Hải Vân - Phan Văn Trị ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Tân Hải Vân - Phan Văn Trị, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 4, 'Tân Hải Vân - Phan Văn Trị khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 4, 'Nhìn chung ở Tân Hải Vân - Phan Văn Trị ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 5, 'Tân Hải Vân - Phan Văn Trị thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 5, 'Bữa ăn tại Tân Hải Vân - Phan Văn Trị vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 5, 'Tân Hải Vân - Phan Văn Trị xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 2, 'Tân Hải Vân - Phan Văn Trị có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 3, 'Tân Hải Vân - Phan Văn Trị tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41c1cbb65b24e710164f', 'RESTAURANT', 3, 'Trải nghiệm ở Tân Hải Vân - Phan Văn Trị bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu (6a2d41c0cbb65b24e710164e) | priceLevel=EXPENSIVE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 4, 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Rất hài lòng với Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Bữa ăn tại Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 3, 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 3, 'Trải nghiệm ở Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 4, 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41c0cbb65b24e710164e', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

-- ==== Reski Hotpot - Nguyễn Thị Thập (6a2d41c1cbb65b24e7101650) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Reski Hotpot - Nguyễn Thị Thập, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 4, 'Reski Hotpot - Nguyễn Thị Thập khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 4, 'Nhìn chung ở Reski Hotpot - Nguyễn Thị Thập ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 5, 'Reski Hotpot - Nguyễn Thị Thập thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 5, 'Bữa ăn tại Reski Hotpot - Nguyễn Thị Thập vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 5, 'Reski Hotpot - Nguyễn Thị Thập xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Reski Hotpot - Nguyễn Thị Thập rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 5, 'Không có gì để chê ở Reski Hotpot - Nguyễn Thị Thập, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 2, 'Trải nghiệm ở Reski Hotpot - Nguyễn Thị Thập không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 3, 'Reski Hotpot - Nguyễn Thị Thập cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 3, 'Mình thấy Reski Hotpot - Nguyễn Thị Thập ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 3, 'Reski Hotpot - Nguyễn Thị Thập tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41c1cbb65b24e7101650', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Reski Hotpot - Nguyễn Thị Thập, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Buffet Gánh Quê Hương - Phạm Ngũ Lão (6a2d41c2cbb65b24e7101651) | priceLevel=EXPENSIVE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Buffet Gánh Quê Hương - Phạm Ngũ Lão.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 5, 'Rất hài lòng với Buffet Gánh Quê Hương - Phạm Ngũ Lão, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 3, 'Buffet Gánh Quê Hương - Phạm Ngũ Lão tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Buffet Gánh Quê Hương - Phạm Ngũ Lão, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 4, 'Buffet Gánh Quê Hương - Phạm Ngũ Lão ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Buffet Gánh Quê Hương - Phạm Ngũ Lão, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 5, 'Không có gì để chê ở Buffet Gánh Quê Hương - Phạm Ngũ Lão, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41c2cbb65b24e7101651', 'RESTAURANT', 5, 'Buffet Gánh Quê Hương - Phạm Ngũ Lão ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Mo Mo Paradise - Nguyễn Thị Minh Khai (6a2d41d4cbb65b24e7101655) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 5, 'Rất hài lòng với Mo Mo Paradise - Nguyễn Thị Minh Khai, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 5, 'Mo Mo Paradise - Nguyễn Thị Minh Khai thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 3, 'Trải nghiệm ở Mo Mo Paradise - Nguyễn Thị Minh Khai bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 3, 'Mo Mo Paradise - Nguyễn Thị Minh Khai cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Mo Mo Paradise - Nguyễn Thị Minh Khai, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 4, 'Mo Mo Paradise - Nguyễn Thị Minh Khai khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 4, 'Nhìn chung ở Mo Mo Paradise - Nguyễn Thị Minh Khai ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Mo Mo Paradise - Nguyễn Thị Minh Khai.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d4cbb65b24e7101655', 'RESTAURANT', 5, 'Rất hài lòng với Mo Mo Paradise - Nguyễn Thị Minh Khai, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Buffet Chay - Khách Sạn Viễn Đông (6a2d41d2cbb65b24e7101653) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 5, 'Buffet Chay - Khách Sạn Viễn Đông thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 5, 'Bữa ăn tại Buffet Chay - Khách Sạn Viễn Đông vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 2, 'Trải nghiệm ở Buffet Chay - Khách Sạn Viễn Đông không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 3, 'Mình thấy Buffet Chay - Khách Sạn Viễn Đông ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 3, 'Buffet Chay - Khách Sạn Viễn Đông tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 3, 'Trải nghiệm ở Buffet Chay - Khách Sạn Viễn Đông bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 4, 'Buffet Chay - Khách Sạn Viễn Đông đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Buffet Chay - Khách Sạn Viễn Đông, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 4, 'Buffet Chay - Khách Sạn Viễn Đông ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41d2cbb65b24e7101653', 'RESTAURANT', 5, 'Bữa ăn tại Buffet Chay - Khách Sạn Viễn Đông vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Reski Hotpot - Tên Lửa (6a2d41d4cbb65b24e7101656) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 5, 'Bữa ăn tại Reski Hotpot - Tên Lửa vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 5, 'Reski Hotpot - Tên Lửa xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 2, 'Reski Hotpot - Tên Lửa có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6)),
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 3, 'Reski Hotpot - Tên Lửa tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 3, 'Trải nghiệm ở Reski Hotpot - Tên Lửa bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 3, 'Reski Hotpot - Tên Lửa cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Reski Hotpot - Tên Lửa, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 4, 'Reski Hotpot - Tên Lửa ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Reski Hotpot - Tên Lửa, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 4, 'Reski Hotpot - Tên Lửa khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41d4cbb65b24e7101656', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Reski Hotpot - Tên Lửa rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Chay Garden Võ Văn Tần (6a2d41d3cbb65b24e7101654) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 5, 'Chay Garden Võ Văn Tần xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Chay Garden Võ Văn Tần rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 2, 'Chay Garden Võ Văn Tần chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 3, 'Trải nghiệm ở Chay Garden Võ Văn Tần bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 3, 'Chay Garden Võ Văn Tần cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 3, 'Mình thấy Chay Garden Võ Văn Tần ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 4, 'Chay Garden Võ Văn Tần ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Chay Garden Võ Văn Tần, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 4, 'Chay Garden Võ Văn Tần khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 4, 'Nhìn chung ở Chay Garden Võ Văn Tần ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 5, 'Không có gì để chê ở Chay Garden Võ Văn Tần, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41d3cbb65b24e7101654', 'RESTAURANT', 5, 'Chay Garden Võ Văn Tần ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Tám Riêu - Phan Xích Long (6a2d51aa9f177b313658bee3) | priceLevel=CHEAP | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Tám Riêu - Phan Xích Long rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 5, 'Không có gì để chê ở Tám Riêu - Phan Xích Long, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 2, 'Trải nghiệm ở Tám Riêu - Phan Xích Long không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 2, 'Tám Riêu - Phan Xích Long có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 3, 'Mình thấy Tám Riêu - Phan Xích Long ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 3, 'Tám Riêu - Phan Xích Long tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 3, 'Trải nghiệm ở Tám Riêu - Phan Xích Long bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 4, 'Tám Riêu - Phan Xích Long khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 4, 'Nhìn chung ở Tám Riêu - Phan Xích Long ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 4, 'Tám Riêu - Phan Xích Long đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Tám Riêu - Phan Xích Long, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Tám Riêu - Phan Xích Long.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51aa9f177b313658bee3', 'RESTAURANT', 5, 'Rất hài lòng với Tám Riêu - Phan Xích Long, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai (6a2d41d5cbb65b24e7101657) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 4, 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 4, 'Nhìn chung ở Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 5, 'Rất hài lòng với Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 2, 'Trải nghiệm ở Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 3, 'Trải nghiệm ở Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 3, 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d41d5cbb65b24e7101657', 'RESTAURANT', 4, 'Nhìn chung ở Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật (6a2d51aa9f177b313658bee4) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 4, 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 5, 'Rất hài lòng với Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 5, 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 5, 'Bữa ăn tại Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 2, 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 3, 'Mình thấy Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 3, 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51aa9f177b313658bee4', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

-- ==== Hotpot Đồng Quê 3 Premium - Quang Trung (6a2d51af9f177b313658bee6) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51af9f177b313658bee6', 'RESTAURANT', 4, 'Hotpot Đồng Quê 3 Premium - Quang Trung đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51af9f177b313658bee6', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Hotpot Đồng Quê 3 Premium - Quang Trung, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51af9f177b313658bee6', 'RESTAURANT', 4, 'Hotpot Đồng Quê 3 Premium - Quang Trung ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51af9f177b313658bee6', 'RESTAURANT', 5, 'Bữa ăn tại Hotpot Đồng Quê 3 Premium - Quang Trung vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51af9f177b313658bee6', 'RESTAURANT', 5, 'Hotpot Đồng Quê 3 Premium - Quang Trung xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51af9f177b313658bee6', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Hotpot Đồng Quê 3 Premium - Quang Trung rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51af9f177b313658bee6', 'RESTAURANT', 2, 'Hotpot Đồng Quê 3 Premium - Quang Trung có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51af9f177b313658bee6', 'RESTAURANT', 3, 'Trải nghiệm ở Hotpot Đồng Quê 3 Premium - Quang Trung bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51af9f177b313658bee6', 'RESTAURANT', 3, 'Hotpot Đồng Quê 3 Premium - Quang Trung cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51af9f177b313658bee6', 'RESTAURANT', 3, 'Mình thấy Hotpot Đồng Quê 3 Premium - Quang Trung ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Ngõ 8 Võ Văn Tần (6a2d51af9f177b313658bee7) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51af9f177b313658bee7', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Ngõ 8 Võ Văn Tần, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51af9f177b313658bee7', 'RESTAURANT', 4, 'Ngõ 8 Võ Văn Tần ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51af9f177b313658bee7', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Ngõ 8 Võ Văn Tần, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51af9f177b313658bee7', 'RESTAURANT', 4, 'Ngõ 8 Võ Văn Tần khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51af9f177b313658bee7', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Ngõ 8 Võ Văn Tần rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51af9f177b313658bee7', 'RESTAURANT', 5, 'Không có gì để chê ở Ngõ 8 Võ Văn Tần, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51af9f177b313658bee7', 'RESTAURANT', 5, 'Ngõ 8 Võ Văn Tần ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51af9f177b313658bee7', 'RESTAURANT', 2, 'Trải nghiệm ở Ngõ 8 Võ Văn Tần không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51af9f177b313658bee7', 'RESTAURANT', 3, 'Mình thấy Ngõ 8 Võ Văn Tần ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51af9f177b313658bee7', 'RESTAURANT', 3, 'Ngõ 8 Võ Văn Tần tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51af9f177b313658bee7', 'RESTAURANT', 3, 'Trải nghiệm ở Ngõ 8 Võ Văn Tần bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Tiệm Lẩu 8 Cá - Linh Đông (6a2d51af9f177b313658bee8) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51af9f177b313658bee8', 'RESTAURANT', 4, 'Tiệm Lẩu 8 Cá - Linh Đông ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51af9f177b313658bee8', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Tiệm Lẩu 8 Cá - Linh Đông, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51af9f177b313658bee8', 'RESTAURANT', 4, 'Tiệm Lẩu 8 Cá - Linh Đông khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51af9f177b313658bee8', 'RESTAURANT', 4, 'Nhìn chung ở Tiệm Lẩu 8 Cá - Linh Đông ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51af9f177b313658bee8', 'RESTAURANT', 5, 'Không có gì để chê ở Tiệm Lẩu 8 Cá - Linh Đông, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51af9f177b313658bee8', 'RESTAURANT', 5, 'Tiệm Lẩu 8 Cá - Linh Đông ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51af9f177b313658bee8', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Tiệm Lẩu 8 Cá - Linh Đông.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51af9f177b313658bee8', 'RESTAURANT', 5, 'Rất hài lòng với Tiệm Lẩu 8 Cá - Linh Đông, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51af9f177b313658bee8', 'RESTAURANT', 2, 'Tiệm Lẩu 8 Cá - Linh Đông chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51af9f177b313658bee8', 'RESTAURANT', 3, 'Trải nghiệm ở Tiệm Lẩu 8 Cá - Linh Đông bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51af9f177b313658bee8', 'RESTAURANT', 3, 'Tiệm Lẩu 8 Cá - Linh Đông cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51af9f177b313658bee8', 'RESTAURANT', 3, 'Mình thấy Tiệm Lẩu 8 Cá - Linh Đông ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Bò Ú Plus - Lâm Văn Bền (6a2d51ab9f177b313658bee5) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Bò Ú Plus - Lâm Văn Bền, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 4, 'Bò Ú Plus - Lâm Văn Bền khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 4, 'Nhìn chung ở Bò Ú Plus - Lâm Văn Bền ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 4, 'Bò Ú Plus - Lâm Văn Bền đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 5, 'Bò Ú Plus - Lâm Văn Bền ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Bò Ú Plus - Lâm Văn Bền.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 5, 'Rất hài lòng với Bò Ú Plus - Lâm Văn Bền, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 5, 'Bò Ú Plus - Lâm Văn Bền thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 5, 'Bữa ăn tại Bò Ú Plus - Lâm Văn Bền vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 2, 'Bò Ú Plus - Lâm Văn Bền có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 3, 'Mình thấy Bò Ú Plus - Lâm Văn Bền ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 3, 'Bò Ú Plus - Lâm Văn Bền tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51ab9f177b313658bee5', 'RESTAURANT', 3, 'Trải nghiệm ở Bò Ú Plus - Lâm Văn Bền bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão (6a2d51b29f177b313658beea) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51b29f177b313658beea', 'RESTAURANT', 3, 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51b29f177b313658beea', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b29f177b313658beea', 'RESTAURANT', 4, 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51b29f177b313658beea', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b29f177b313658beea', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b29f177b313658beea', 'RESTAURANT', 5, 'Rất hài lòng với Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b29f177b313658beea', 'RESTAURANT', 2, 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51b29f177b313658beea', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

-- ==== Nhà hàng Yuhua - Lê Văn Sỹ (6a2d51b29f177b313658beeb) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51b29f177b313658beeb', 'RESTAURANT', 3, 'Mình thấy Nhà hàng Yuhua - Lê Văn Sỹ ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b29f177b313658beeb', 'RESTAURANT', 3, 'Nhà hàng Yuhua - Lê Văn Sỹ tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51b29f177b313658beeb', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Nhà hàng Yuhua - Lê Văn Sỹ, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b29f177b313658beeb', 'RESTAURANT', 4, 'Nhà hàng Yuhua - Lê Văn Sỹ ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b29f177b313658beeb', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Nhà hàng Yuhua - Lê Văn Sỹ, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b29f177b313658beeb', 'RESTAURANT', 5, 'Nhà hàng Yuhua - Lê Văn Sỹ thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51b29f177b313658beeb', 'RESTAURANT', 5, 'Bữa ăn tại Nhà hàng Yuhua - Lê Văn Sỹ vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51b29f177b313658beeb', 'RESTAURANT', 5, 'Nhà hàng Yuhua - Lê Văn Sỹ xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51b29f177b313658beeb', 'RESTAURANT', 2, 'Nhà hàng Yuhua - Lê Văn Sỹ chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

-- ==== Yakimono - Phan Xích Long (6a2d51b49f177b313658beec) | priceLevel=EXPENSIVE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b49f177b313658beec', 'RESTAURANT', 3, 'Yakimono - Phan Xích Long tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51b49f177b313658beec', 'RESTAURANT', 3, 'Trải nghiệm ở Yakimono - Phan Xích Long bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b49f177b313658beec', 'RESTAURANT', 4, 'Yakimono - Phan Xích Long ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b49f177b313658beec', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Yakimono - Phan Xích Long, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b49f177b313658beec', 'RESTAURANT', 4, 'Yakimono - Phan Xích Long khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51b49f177b313658beec', 'RESTAURANT', 5, 'Bữa ăn tại Yakimono - Phan Xích Long vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51b49f177b313658beec', 'RESTAURANT', 5, 'Yakimono - Phan Xích Long xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51b49f177b313658beec', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Yakimono - Phan Xích Long rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51b49f177b313658beec', 'RESTAURANT', 5, 'Không có gì để chê ở Yakimono - Phan Xích Long, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51b49f177b313658beec', 'RESTAURANT', 5, 'Yakimono - Phan Xích Long ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Tasaki BBQ - Vạn Hạnh Mall (6a2d41d0cbb65b24e7101652) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 5, 'Tasaki BBQ - Vạn Hạnh Mall ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 2, 'Trải nghiệm ở Tasaki BBQ - Vạn Hạnh Mall không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 3, 'Mình thấy Tasaki BBQ - Vạn Hạnh Mall ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 3, 'Tasaki BBQ - Vạn Hạnh Mall tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 3, 'Trải nghiệm ở Tasaki BBQ - Vạn Hạnh Mall bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 4, 'Tasaki BBQ - Vạn Hạnh Mall đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Tasaki BBQ - Vạn Hạnh Mall, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 4, 'Tasaki BBQ - Vạn Hạnh Mall ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Tasaki BBQ - Vạn Hạnh Mall, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Tasaki BBQ - Vạn Hạnh Mall.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d41d0cbb65b24e7101652', 'RESTAURANT', 5, 'Rất hài lòng với Tasaki BBQ - Vạn Hạnh Mall, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 (6a2d51b59f177b313658beee) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b59f177b313658beee', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b59f177b313658beee', 'RESTAURANT', 5, 'Rất hài lòng với Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b59f177b313658beee', 'RESTAURANT', 2, 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51b59f177b313658beee', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51b59f177b313658beee', 'RESTAURANT', 3, 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51b59f177b313658beee', 'RESTAURANT', 3, 'Mình thấy Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51b59f177b313658beee', 'RESTAURANT', 4, 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51b59f177b313658beee', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51b59f177b313658beee', 'RESTAURANT', 4, 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51b59f177b313658beee', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51b59f177b313658beee', 'RESTAURANT', 5, 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b59f177b313658beee', 'RESTAURANT', 5, 'Bữa ăn tại Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Happy Lamb Hotpot - Trần Hưng Đạo (6a2d51a99f177b313658bee2) | priceLevel=EXPENSIVE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Rất hài lòng với Happy Lamb Hotpot - Trần Hưng Đạo, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Happy Lamb Hotpot - Trần Hưng Đạo thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Bữa ăn tại Happy Lamb Hotpot - Trần Hưng Đạo vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51a99f177b313658bee2', 'RESTAURANT', 3, 'Happy Lamb Hotpot - Trần Hưng Đạo cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51a99f177b313658bee2', 'RESTAURANT', 3, 'Mình thấy Happy Lamb Hotpot - Trần Hưng Đạo ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51a99f177b313658bee2', 'RESTAURANT', 4, 'Happy Lamb Hotpot - Trần Hưng Đạo ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51a99f177b313658bee2', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Happy Lamb Hotpot - Trần Hưng Đạo, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51a99f177b313658bee2', 'RESTAURANT', 4, 'Happy Lamb Hotpot - Trần Hưng Đạo khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51a99f177b313658bee2', 'RESTAURANT', 4, 'Nhìn chung ở Happy Lamb Hotpot - Trần Hưng Đạo ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Happy Lamb Hotpot - Trần Hưng Đạo thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Bữa ăn tại Happy Lamb Hotpot - Trần Hưng Đạo vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Happy Lamb Hotpot - Trần Hưng Đạo xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51a99f177b313658bee2', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Happy Lamb Hotpot - Trần Hưng Đạo rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai (6a2d5b5881491622bb578b77) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b5881491622bb578b77', 'RESTAURANT', 2, 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b5881491622bb578b77', 'RESTAURANT', 3, 'Trải nghiệm ở Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b5881491622bb578b77', 'RESTAURANT', 3, 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b5881491622bb578b77', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b5881491622bb578b77', 'RESTAURANT', 4, 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b5881491622bb578b77', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b5881491622bb578b77', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b5881491622bb578b77', 'RESTAURANT', 5, 'Rất hài lòng với Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

-- ==== GAON BBQ Nguyễn Trãi (6a2d5b5981491622bb578b78) | priceLevel=EXPENSIVE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b5981491622bb578b78', 'RESTAURANT', 5, 'Bữa ăn tại GAON BBQ Nguyễn Trãi vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b5981491622bb578b78', 'RESTAURANT', 5, 'GAON BBQ Nguyễn Trãi xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b5981491622bb578b78', 'RESTAURANT', 3, 'Mình thấy GAON BBQ Nguyễn Trãi ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b5981491622bb578b78', 'RESTAURANT', 3, 'GAON BBQ Nguyễn Trãi tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b5981491622bb578b78', 'RESTAURANT', 4, 'Trải nghiệm tốt tại GAON BBQ Nguyễn Trãi, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b5981491622bb578b78', 'RESTAURANT', 4, 'GAON BBQ Nguyễn Trãi khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b5981491622bb578b78', 'RESTAURANT', 4, 'Nhìn chung ở GAON BBQ Nguyễn Trãi ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b5981491622bb578b78', 'RESTAURANT', 5, 'GAON BBQ Nguyễn Trãi thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b5981491622bb578b78', 'RESTAURANT', 5, 'Bữa ăn tại GAON BBQ Nguyễn Trãi vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

-- ==== Quán Nướng Yaki - Chế Lan Viên (6a2d5b7a81491622bb578b79) | priceLevel=MODERATE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 4, 'Quán Nướng Yaki - Chế Lan Viên đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Quán Nướng Yaki - Chế Lan Viên rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 5, 'Không có gì để chê ở Quán Nướng Yaki - Chế Lan Viên, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 5, 'Quán Nướng Yaki - Chế Lan Viên ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 2, 'Quán Nướng Yaki - Chế Lan Viên chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 3, 'Mình thấy Quán Nướng Yaki - Chế Lan Viên ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 3, 'Quán Nướng Yaki - Chế Lan Viên tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 3, 'Trải nghiệm ở Quán Nướng Yaki - Chế Lan Viên bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 4, 'Quán Nướng Yaki - Chế Lan Viên ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b7a81491622bb578b79', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Quán Nướng Yaki - Chế Lan Viên, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

-- ==== Galbi House Phan Xích Long (6a2d5b7b81491622bb578b7a) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Galbi House Phan Xích Long, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 4, 'Galbi House Phan Xích Long ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Galbi House Phan Xích Long, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Galbi House Phan Xích Long.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 5, 'Rất hài lòng với Galbi House Phan Xích Long, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 5, 'Galbi House Phan Xích Long thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 2, 'Galbi House Phan Xích Long chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 3, 'Galbi House Phan Xích Long cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 3, 'Mình thấy Galbi House Phan Xích Long ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 3, 'Galbi House Phan Xích Long tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b7b81491622bb578b7a', 'RESTAURANT', 4, 'Nhìn chung ở Galbi House Phan Xích Long ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh (6a2d5b8781491622bb578b7b) | priceLevel=MODERATE | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 4, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 4, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 4, 'Nhìn chung ở Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 5, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 5, 'Bữa ăn tại Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 5, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 2, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 3, 'Trải nghiệm ở Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 3, 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b8781491622bb578b7b', 'RESTAURANT', 3, 'Mình thấy Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

-- ==== Lẩu Nướng Mini Candy - Nguyễn Văn Đậu (6a2d51c09f177b313658beef) | priceLevel=CHEAP | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d51c09f177b313658beef', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51c09f177b313658beef', 'RESTAURANT', 3, 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51c09f177b313658beef', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51c09f177b313658beef', 'RESTAURANT', 4, 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51c09f177b313658beef', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Nướng Mini Candy - Nguyễn Văn Đậu, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51c09f177b313658beef', 'RESTAURANT', 4, 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51c09f177b313658beef', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51c09f177b313658beef', 'RESTAURANT', 5, 'Không có gì để chê ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51c09f177b313658beef', 'RESTAURANT', 5, 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51c09f177b313658beef', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Lẩu Nướng Mini Candy - Nguyễn Văn Đậu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51c09f177b313658beef', 'RESTAURANT', 2, 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6)),
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d51c09f177b313658beef', 'RESTAURANT', 2, 'Trải nghiệm ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d51c09f177b313658beef', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Nướng Mini Candy - Nguyễn Văn Đậu bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

-- ==== Ghiền BBQ Nướng & Lẩu - Lê Cơ (6a2d51b09f177b313658bee9) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d51b09f177b313658bee9', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Ghiền BBQ Nướng & Lẩu - Lê Cơ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51b09f177b313658bee9', 'RESTAURANT', 5, 'Rất hài lòng với Ghiền BBQ Nướng & Lẩu - Lê Cơ, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51b09f177b313658bee9', 'RESTAURANT', 2, 'Ghiền BBQ Nướng & Lẩu - Lê Cơ có một số vấn đề về chất lượng món ăn và phục vụ, cần khắc phục.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6)),
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b09f177b313658bee9', 'RESTAURANT', 3, 'Trải nghiệm ở Ghiền BBQ Nướng & Lẩu - Lê Cơ bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51b09f177b313658bee9', 'RESTAURANT', 3, 'Ghiền BBQ Nướng & Lẩu - Lê Cơ cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b09f177b313658bee9', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Ghiền BBQ Nướng & Lẩu - Lê Cơ, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b09f177b313658bee9', 'RESTAURANT', 4, 'Ghiền BBQ Nướng & Lẩu - Lê Cơ khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b09f177b313658bee9', 'RESTAURANT', 4, 'Nhìn chung ở Ghiền BBQ Nướng & Lẩu - Lê Cơ ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

-- ==== Lẩu Dê 404 - Dương Đức Hiền (6a2d51b59f177b313658beed) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d51b59f177b313658beed', 'RESTAURANT', 4, 'Nhìn chung ở Lẩu Dê 404 - Dương Đức Hiền ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d51b59f177b313658beed', 'RESTAURANT', 4, 'Lẩu Dê 404 - Dương Đức Hiền đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d51b59f177b313658beed', 'RESTAURANT', 5, 'Bữa ăn tại Lẩu Dê 404 - Dương Đức Hiền vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d51b59f177b313658beed', 'RESTAURANT', 5, 'Lẩu Dê 404 - Dương Đức Hiền xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d51b59f177b313658beed', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Lẩu Dê 404 - Dương Đức Hiền rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d51b59f177b313658beed', 'RESTAURANT', 2, 'Lẩu Dê 404 - Dương Đức Hiền chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá chật và ồn, chưa thoải mái lắm.', NOW(6)),
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d51b59f177b313658beed', 'RESTAURANT', 3, 'Trải nghiệm ở Lẩu Dê 404 - Dương Đức Hiền bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6)),
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d51b59f177b313658beed', 'RESTAURANT', 3, 'Lẩu Dê 404 - Dương Đức Hiền cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d51b59f177b313658beed', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Lẩu Dê 404 - Dương Đức Hiền, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

-- ==== Buffet King BBQ Vincom Thủ Đức (6a2d5b8e81491622bb578b7d) | priceLevel=EXPENSIVE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 4, 'Buffet King BBQ Vincom Thủ Đức đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Buffet King BBQ Vincom Thủ Đức, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 4, 'Buffet King BBQ Vincom Thủ Đức ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Buffet King BBQ Vincom Thủ Đức rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 5, 'Không có gì để chê ở Buffet King BBQ Vincom Thủ Đức, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 5, 'Buffet King BBQ Vincom Thủ Đức ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Buffet King BBQ Vincom Thủ Đức.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 5, 'Rất hài lòng với Buffet King BBQ Vincom Thủ Đức, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 3, 'Buffet King BBQ Vincom Thủ Đức tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b8e81491622bb578b7d', 'RESTAURANT', 3, 'Trải nghiệm ở Buffet King BBQ Vincom Thủ Đức bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

-- ==== Ba Gác - Vietnamese Grill & Beer - Quang Trung (6a2d5b8981491622bb578b7c) | priceLevel=MODERATE | 11 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 2, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Giá không tương xứng với chất lượng thực tế nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh chưa tốt, cần cải thiện thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 3, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 3, 'Mình thấy Ba Gác - Vietnamese Grill & Beer - Quang Trung ở mức tạm được, không tệ nhưng cũng không xuất sắc.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 3, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 4, 'Nhìn chung ở Ba Gác - Vietnamese Grill & Beer - Quang Trung ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 4, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Ba Gác - Vietnamese Grill & Beer - Quang Trung, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 4, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 5, 'Bữa ăn tại Ba Gác - Vietnamese Grill & Beer - Quang Trung vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 5, 'Ba Gác - Vietnamese Grill & Beer - Quang Trung xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b8981491622bb578b7c', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Ba Gác - Vietnamese Grill & Beer - Quang Trung rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa (6a2d5b8f81491622bb578b7e) | priceLevel=LUXURY | 12 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 3, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 4, 'Nhìn chung ở CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 4, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa thực sự xuất sắc, món ăn ngon và không gian rất dễ chịu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'Bữa ăn tại CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa vượt xa mong đợi, chắc chắn sẽ quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'Không có gì để chê ở CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b8f81491622bb578b7e', 'RESTAURANT', 5, 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

-- ==== Quán Nướng Yaki - Tân Sơn Nhì (6a2d5b9c81491622bb578b82) | priceLevel=MODERATE | 13 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (17, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Quán Nướng Yaki - Tân Sơn Nhì rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 5, 'Không có gì để chê ở Quán Nướng Yaki - Tân Sơn Nhì, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 5, 'Quán Nướng Yaki - Tân Sơn Nhì ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Quán Nướng Yaki - Tân Sơn Nhì.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 2, 'Quán Nướng Yaki - Tân Sơn Nhì chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần khá ít so với mức giá phải trả.', NOW(6)),
(@rid, 15, 'Chờ món quá lâu, ảnh hưởng trải nghiệm chung.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 3, 'Quán Nướng Yaki - Tân Sơn Nhì tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6)),
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 3, 'Trải nghiệm ở Quán Nướng Yaki - Tân Sơn Nhì bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 3, 'Quán Nướng Yaki - Tân Sơn Nhì cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 4, 'Nhìn chung ở Quán Nướng Yaki - Tân Sơn Nhì ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 4, 'Quán Nướng Yaki - Tân Sơn Nhì đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Quán Nướng Yaki - Tân Sơn Nhì, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 4, 'Quán Nướng Yaki - Tân Sơn Nhì ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b9c81491622bb578b82', 'RESTAURANT', 5, 'Rất hài lòng với Quán Nướng Yaki - Tân Sơn Nhì, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

-- ==== Mabu-KKO Chi - Thái Văn Lung (6a2d5b9b81491622bb578b81) | priceLevel=MODERATE | 8 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (18, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 4, 'Mabu-KKO Chi - Thái Văn Lung khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 4, 'Nhìn chung ở Mabu-KKO Chi - Thái Văn Lung ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6)),
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Mabu-KKO Chi - Thái Văn Lung.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 5, 'Rất hài lòng với Mabu-KKO Chi - Thái Văn Lung, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6)),
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 2, 'Trải nghiệm ở Mabu-KKO Chi - Thái Văn Lung không được như mong đợi, hơi thất vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6)),
(@rid, 10, 'Phục vụ thiếu chuyên nghiệp, phản hồi chậm khi cần hỗ trợ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 3, 'Trải nghiệm ở Mabu-KKO Chi - Thái Văn Lung bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6)),
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 3, 'Mabu-KKO Chi - Thái Văn Lung cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian bình thường, không có gì nổi bật.', NOW(6)),
(@rid, 12, 'Giá cả ở mức bình thường so với chất lượng món.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b9b81491622bb578b81', 'RESTAURANT', 4, 'Nhìn chung ở Mabu-KKO Chi - Thái Văn Lung ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

-- ==== Tiệm Thân Nướng - Liên Phường (6a2d5b9381491622bb578b7f) | priceLevel=MODERATE | 9 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (19, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 3, 'Trải nghiệm ở Tiệm Thân Nướng - Liên Phường bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí hơi xa trung tâm, phải di chuyển khá nhiều.', NOW(6)),
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 3, 'Tiệm Thân Nướng - Liên Phường cũng tạm được nhưng không có gì quá đặc biệt.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn ở mức tạm, chưa có gì đặc sắc.', NOW(6)),
(@rid, 10, 'Phục vụ ở mức bình thường, chưa thực sự chủ động.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 4, 'Khá hài lòng với bữa ăn tại Tiệm Thân Nướng - Liên Phường, sẽ cân nhắc quay lại.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 4, 'Tiệm Thân Nướng - Liên Phường ổn, chỉ hơi tiếc vì một số món chưa thực sự ấn tượng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6)),
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 4, 'Trải nghiệm tốt tại Tiệm Thân Nướng - Liên Phường, phù hợp cho bữa ăn cùng bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Mức giá tạm ổn so với khẩu phần nhận được.', NOW(6)),
(@rid, 13, 'Vệ sinh khá ổn, chỉ vài chi tiết nhỏ cần chú ý thêm.', NOW(6)),
(@rid, 14, 'Phần ăn tạm ổn, không quá nhiều nhưng đủ no.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 5, 'Tiệm Thân Nướng - Liên Phường xứng đáng 5 sao, từ món ăn đến phục vụ đều chỉn chu.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 5, 'Trải nghiệm ẩm thực ở Tiệm Thân Nướng - Liên Phường rất đáng nhớ, sẽ giới thiệu cho bạn bè.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6)),
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 5, 'Không có gì để chê ở Tiệm Thân Nướng - Liên Phường, quá hài lòng với bữa ăn này.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Món lên nhanh, không phải chờ đợi lâu.', NOW(6)),
(@rid, 16, 'Vị trí đẹp, gần trung tâm nên rất tiện di chuyển.', NOW(6)),
(@rid, 9, 'Món ăn tươi ngon, chế biến rất khéo và đậm đà.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b9381491622bb578b7f', 'RESTAURANT', 2, 'Tiệm Thân Nướng - Liên Phường chưa đáp ứng được kỳ vọng, khá nhiều điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí không thuận tiện lắm, khó tìm chỗ đậu xe.', NOW(6)),
(@rid, 9, 'Chất lượng món ăn chưa tốt, một số món khá nhạt.', NOW(6));

-- ==== Yakimono - Vincom Plaza 3/2 (6a2d5b9b81491622bb578b80) | priceLevel=EXPENSIVE | 10 reviews ====
INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (20, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Yakimono - Vincom Plaza 3/2.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (5, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 5, 'Rất hài lòng với Yakimono - Vincom Plaza 3/2, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (7, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 3, 'Yakimono - Vincom Plaza 3/2 tạm ổn nhưng chưa thực sự ấn tượng như mình kỳ vọng.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 13, 'Vệ sinh ở mức tạm, chưa thực sự kỹ.', NOW(6)),
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (9, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 3, 'Trải nghiệm ở Yakimono - Vincom Plaza 3/2 bình thường, có vài điểm cần cải thiện.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 14, 'Khẩu phần hơi ít so với mong đợi.', NOW(6)),
(@rid, 15, 'Thời gian chờ món khá lâu, cần cải thiện.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (10, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 4, 'Yakimono - Vincom Plaza 3/2 khá ngon, không gian ổn, chỉ có vài điểm nhỏ có thể cải thiện thêm.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 15, 'Thời gian chờ món ở mức chấp nhận được.', NOW(6)),
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (11, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 4, 'Nhìn chung ở Yakimono - Vincom Plaza 3/2 ổn, tuy còn một vài bất tiện nhỏ về phục vụ.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 16, 'Vị trí ổn, đi lại khá dễ dàng.', NOW(6)),
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (12, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 4, 'Yakimono - Vincom Plaza 3/2 đáng thử, dù không phải món nào cũng hoàn hảo.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 9, 'Món ăn khá ngon, một vài món chưa thực sự nổi bật.', NOW(6)),
(@rid, 10, 'Nhân viên thân thiện, hỗ trợ nhiệt tình.', NOW(6)),
(@rid, 11, 'Không gian khá ổn, chỉ hơi ồn vào giờ cao điểm.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (14, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 5, 'Yakimono - Vincom Plaza 3/2 ngon hơn cả mong đợi, rất đáng để quay lại nhiều lần.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 10, 'Phục vụ chuyên nghiệp, luôn hỏi thăm khách trong suốt bữa ăn.', NOW(6)),
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (15, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 5, 'Một trong những quán ăn ngon nhất mình từng thử, cảm ơn Yakimono - Vincom Plaza 3/2.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 11, 'Không gian đẹp và thoáng, rất hợp để chụp ảnh.', NOW(6)),
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6));

INSERT INTO reviews (user_id, target_id, target_type, rating, content, created_at)
VALUES (16, '6a2d5b9b81491622bb578b80', 'RESTAURANT', 5, 'Rất hài lòng với Yakimono - Vincom Plaza 3/2, món ăn tươi ngon và phục vụ chuyên nghiệp.', NOW(6));
SET @rid := LAST_INSERT_ID();
INSERT INTO review_aspects (review_id, aspect_definition_id, comment, created_at) VALUES
(@rid, 12, 'Xứng đáng từng đồng, quá đáng tiền.', NOW(6)),
(@rid, 13, 'Vệ sinh rất tốt, khu bếp và bàn ăn luôn sạch sẽ.', NOW(6)),
(@rid, 14, 'Phần ăn hào phóng, rất đáng để quay lại.', NOW(6));
