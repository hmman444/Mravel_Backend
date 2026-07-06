-- Seed demo bookings RESTAURANT cho khu vuc: ho-chi-minh-city
-- 5-8 booking/restaurant, ngay dat ban rai trong 1-6 thang qua, thanh toan (dat coc) qua MOMO_WALLET
-- payOption luon DEPOSIT (dung flow that: totalAmount == depositAmount == amountPayable)
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Tân Hải Vân - Phan Văn Trị (6a2d41c1cbb65b24e710164f) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D087AB9A6E', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-06-04', '12:30:00', 5, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-05-25 00:00:00', '2026-05-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-D087AB9A6E-5C803D', 'MOMO27DB323E20', '2026-05-25 03:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-18BF8AF9D8', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-23 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-23 00:00:00', '2026-04-23 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-04-25', '18:00:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-12', 'Phòng riêng 12 khách', 1, 12, 500000, 500000, '2026-04-23 00:00:00', '2026-04-23 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-18BF8AF9D8-39D424', 'MOMO6145F9E16B', '2026-04-23 04:00:00', NULL, NULL, '2026-04-23 00:00:00', '2026-04-23 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B6E379E98C', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-04 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-02-17', '18:30:00', 16, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-02-04 00:00:00', '2026-02-04 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-B6E379E98C-F90DD4', 'MOMO8BFBE7789B', '2026-02-04 02:00:00', NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-052B7FFBFD', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-05 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-05 00:00:00', '2026-04-05 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-04-16', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-04-05 00:00:00', '2026-04-05 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-052B7FFBFD-CF73B3', 'MOMO534B04CDB6', '2026-04-05 03:00:00', NULL, NULL, '2026-04-05 00:00:00', '2026-04-05 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CB2D79093A', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-14 08:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 08:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-04-23', '19:30:00', 8, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-12', 'Phòng riêng 12 khách', 1, 12, 500000, 500000, '2026-04-14 00:00:00', '2026-04-14 08:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-CB2D79093A-BD13F7', NULL, NULL, NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 08:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4FD3D04AB7', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-14 00:00:00', '2026-03-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-03-16', '20:00:00', 21, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-03-14 00:00:00', '2026-03-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-4FD3D04AB7-262011', 'MOMOF7734064FF', '2026-03-14 02:00:00', NULL, NULL, '2026-03-14 00:00:00', '2026-03-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F92E9B5CC7', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-04-09', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-03-27 00:00:00', '2026-03-27 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F92E9B5CC7-B1855B', 'MOMO3E5B3737C4', '2026-03-27 03:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2AF2059253', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-15 15:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 15:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e710164f', 'tan-hai-van-phan-van-tri', 'Tân Hải Vân - Phan Văn Trị', '2026-01-25', '11:30:00', 10, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-12', 'Phòng riêng 12 khách', 1, 12, 500000, 500000, '2026-01-15 00:00:00', '2026-01-15 15:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-2AF2059253-3F1DE8', NULL, NULL, NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 15:00:00');

-- ==== Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu (6a2d41c0cbb65b24e710164e) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-935AE27067', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-29 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c0cbb65b24e710164e', 'hoang-yen-buffet-premier-nguyen-dinh-chieu', 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu', '2026-01-30', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn 8 VIP', 1, 8, 500000, 500000, '2026-01-29 00:00:00', '2026-01-29 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-935AE27067-505C01', 'MOMOB8B2DD3E70', '2026-01-29 04:00:00', NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-16B42A7BA0', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c0cbb65b24e710164e', 'hoang-yen-buffet-premier-nguyen-dinh-chieu', 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu', '2026-03-30', '18:30:00', 27, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1500000, 1500000, '2026-03-27 00:00:00', '2026-03-27 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-16B42A7BA0-1BB920', 'MOMO6EC4C161A7', '2026-03-27 04:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1AFC133E03', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-28 00:00:00', '2026-01-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c0cbb65b24e710164e', 'hoang-yen-buffet-premier-nguyen-dinh-chieu', 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu', '2026-01-31', '19:00:00', 2, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-28 00:00:00', '2026-01-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-1AFC133E03-3F3DBC', 'MOMOA7D55F0F14', '2026-01-28 03:00:00', NULL, NULL, '2026-01-28 00:00:00', '2026-01-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-51448F4C15', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-22 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-22 00:00:00', '2026-05-22 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c0cbb65b24e710164e', 'hoang-yen-buffet-premier-nguyen-dinh-chieu', 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu', '2026-05-25', '19:30:00', 8, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn 8 VIP', 1, 8, 500000, 500000, '2026-05-22 00:00:00', '2026-05-22 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-51448F4C15-82E427', 'MOMO35FE7A040C', '2026-05-22 03:00:00', NULL, NULL, '2026-05-22 00:00:00', '2026-05-22 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-392A5BA845', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-29 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-29 00:00:00', '2026-04-29 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c0cbb65b24e710164e', 'hoang-yen-buffet-premier-nguyen-dinh-chieu', 'Hoàng Yến Buffet Premier - Nguyễn Đình Chiểu', '2026-05-07', '20:00:00', 38, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1500000, 1500000, '2026-04-29 00:00:00', '2026-04-29 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-392A5BA845-7E3E82', 'MOMO7CB70AE533', '2026-04-29 03:00:00', NULL, NULL, '2026-04-29 00:00:00', '2026-04-29 03:00:00');

-- ==== Reski Hotpot - Nguyễn Thị Thập (6a2d41c1cbb65b24e7101650) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C2B81C4E34', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-04-10', '18:30:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-04-06 00:00:00', '2026-04-06 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-C2B81C4E34-714E73', 'MOMO67D9381684', '2026-04-06 04:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7285537501', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-29 04:00:00', '2026-01-29 18:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-01-31', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-29 00:00:00', '2026-01-29 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-7285537501-F7A2FA', 'MOMOF52CF1E3BE', '2026-01-29 04:00:00', 100000, '2026-01-29 18:00:00', '2026-01-29 00:00:00', '2026-01-29 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B303B8AD14', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-05-01', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-20 00:00:00', '2026-04-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-B303B8AD14-2F4889', 'MOMO23C22D8A35', '2026-04-20 02:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-809229E33E', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-01-16', '20:00:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-14 00:00:00', '2026-01-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-809229E33E-98C69A', 'MOMO2BD90D391B', '2026-01-14 02:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EE377FA992', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-23 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-23 00:00:00', '2026-03-23 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-03-26', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-23 00:00:00', '2026-03-23 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-EE377FA992-D02E68', 'MOMO20D70E3BC4', '2026-03-23 04:00:00', NULL, NULL, '2026-03-23 00:00:00', '2026-03-23 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0EF042232A', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-01 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c1cbb65b24e7101650', 'reski-hotpot-nguyen-thi-thap', 'Reski Hotpot - Nguyễn Thị Thập', '2026-02-06', '11:30:00', 3, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 2, 4, 200000, 400000, '2026-02-01 00:00:00', '2026-02-01 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-0EF042232A-F42994', 'MOMO8748A5547A', '2026-02-01 01:00:00', NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 01:00:00');

-- ==== Buffet Gánh Quê Hương - Phạm Ngũ Lão (6a2d41c2cbb65b24e7101651) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C8CFE81A79', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-02-22', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-13 00:00:00', '2026-02-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-C8CFE81A79-48A35D', 'MOMO7D357D6326', '2026-02-13 02:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38E440C3D9', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-06-05', '19:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-outdoor', 'Bàn 8 sân thượng', 1, 8, 400000, 400000, '2026-05-25 00:00:00', '2026-05-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-38E440C3D9-3FD368', 'MOMO7030A93AA1', '2026-05-25 03:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C93797290E', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-02-26', '20:00:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-02-23 00:00:00', '2026-02-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-C93797290E-E3EB62', 'MOMO8EAE8B3CF7', '2026-02-23 01:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CB66D1FD0C', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-22 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-22 00:00:00', '2026-02-22 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-02-24', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-22 00:00:00', '2026-02-22 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-CB66D1FD0C-EFFE71', 'MOMO3531E4062E', '2026-02-22 01:00:00', NULL, NULL, '2026-02-22 00:00:00', '2026-02-22 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F816EC661E', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-20 00:00:00', '2026-02-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-02-26', '11:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-outdoor', 'Bàn 8 sân thượng', 1, 8, 400000, 400000, '2026-02-20 00:00:00', '2026-02-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-F816EC661E-BE18D7', 'MOMO5E9577A0C1', '2026-02-20 02:00:00', NULL, NULL, '2026-02-20 00:00:00', '2026-02-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-520FF0288B', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-11 00:00:00', '2026-04-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-04-13', '12:00:00', 22, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-04-11 00:00:00', '2026-04-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-520FF0288B-24329C', 'MOMO745D67CC88', '2026-04-11 03:00:00', NULL, NULL, '2026-04-11 00:00:00', '2026-04-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C8F20C9B59', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-16 00:00:00', '2026-03-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41c2cbb65b24e7101651', 'buffet-ganh-que-huong', 'Buffet Gánh Quê Hương - Phạm Ngũ Lão', '2026-03-24', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-16 00:00:00', '2026-03-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-C8F20C9B59-87EE7F', 'MOMO73F00794D4', '2026-03-16 02:00:00', NULL, NULL, '2026-03-16 00:00:00', '2026-03-16 02:00:00');

-- ==== Mo Mo Paradise - Nguyễn Thị Minh Khai (6a2d41d4cbb65b24e7101655) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F1F6A4A27C', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-30 00:00:00', '2026-01-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-02-07', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-30 00:00:00', '2026-01-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F1F6A4A27C-A0B22B', 'MOMODD1C50C7DE', '2026-01-30 04:00:00', NULL, NULL, '2026-01-30 00:00:00', '2026-01-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7E834DF0C9', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-06 10:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-04-11', '20:00:00', 17, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-04-06 00:00:00', '2026-04-06 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-7E834DF0C9-CC84F8', NULL, NULL, NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6A7E0ABDEC', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-04-15', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-04-13 00:00:00', '2026-04-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-6A7E0ABDEC-A0F1AE', 'MOMO11F8B723AB', '2026-04-13 03:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-544DA7C3B2', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-24 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-24 00:00:00', '2026-02-24 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-03-09', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-24 00:00:00', '2026-02-24 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-544DA7C3B2-5184B6', 'MOMOBAC02A1D1D', '2026-02-24 04:00:00', NULL, NULL, '2026-02-24 00:00:00', '2026-02-24 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-26861EA832', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-01 00:00:00', '2026-01-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-01-11', '12:00:00', 17, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-01 00:00:00', '2026-01-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-26861EA832-E40041', 'MOMO108214D0BD', '2026-01-01 02:00:00', NULL, NULL, '2026-01-01 00:00:00', '2026-01-01 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-72EA57A0FC', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-24 04:00:00', '2026-03-24 20:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-24 00:00:00', '2026-03-24 20:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-03-27', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-24 00:00:00', '2026-03-24 20:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-72EA57A0FC-94B118', 'MOMO2D6A480980', '2026-03-24 04:00:00', 100000, '2026-03-24 20:00:00', '2026-03-24 00:00:00', '2026-03-24 20:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4F1CBF6683', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-01 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-01 00:00:00', '2026-03-01 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-03-03', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-01 00:00:00', '2026-03-01 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-4F1CBF6683-9B9A9B', 'MOMOCDADC56637', '2026-03-01 04:00:00', NULL, NULL, '2026-03-01 00:00:00', '2026-03-01 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FC048556F6', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-30 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-30 00:00:00', '2026-01-30 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101655', 'mo-mo-paradise-nguyen-thi-minh-khai', 'Mo Mo Paradise - Nguyễn Thị Minh Khai', '2026-02-07', '18:30:00', 17, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-30 00:00:00', '2026-01-30 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-FC048556F6-F190BD', 'MOMOD605D01F1D', '2026-01-30 02:00:00', NULL, NULL, '2026-01-30 00:00:00', '2026-01-30 02:00:00');

-- ==== Buffet Chay - Khách Sạn Viễn Đông (6a2d41d2cbb65b24e7101653) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-605E912915', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-06 00:00:00', '2026-03-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d2cbb65b24e7101653', 'buffet-chay-vien-dong', 'Buffet Chay - Khách Sạn Viễn Đông', '2026-03-14', '20:00:00', 40, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-06 00:00:00', '2026-03-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-605E912915-CF6657', 'MOMOFF3C7B4F4B', '2026-03-06 03:00:00', NULL, NULL, '2026-03-06 00:00:00', '2026-03-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5905C1C7E8', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d2cbb65b24e7101653', 'buffet-chay-vien-dong', 'Buffet Chay - Khách Sạn Viễn Đông', '2026-03-10', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-02-28 00:00:00', '2026-02-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-5905C1C7E8-D0FAA4', 'MOMO37A801084B', '2026-02-28 01:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1F29D861ED', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-31 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-31 00:00:00', '2026-05-31 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d2cbb65b24e7101653', 'buffet-chay-vien-dong', 'Buffet Chay - Khách Sạn Viễn Đông', '2026-06-03', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn 8 VIP', 1, 8, 300000, 300000, '2026-05-31 00:00:00', '2026-05-31 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-1F29D861ED-F8C1FB', 'MOMO4D28B2A78A', '2026-05-31 03:00:00', NULL, NULL, '2026-05-31 00:00:00', '2026-05-31 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D0CCBC20CA', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-02 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d2cbb65b24e7101653', 'buffet-chay-vien-dong', 'Buffet Chay - Khách Sạn Viễn Đông', '2026-01-12', '12:00:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-02 00:00:00', '2026-01-02 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-D0CCBC20CA-D4D67B', 'MOMO09FFAE8AA5', '2026-01-02 02:00:00', NULL, NULL, '2026-01-02 00:00:00', '2026-01-02 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F6BB0333FB', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d2cbb65b24e7101653', 'buffet-chay-vien-dong', 'Buffet Chay - Khách Sạn Viễn Đông', '2026-03-14', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-03-05 00:00:00', '2026-03-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-F6BB0333FB-DE9955', 'MOMO297C8F2FD9', '2026-03-05 02:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 02:00:00');

-- ==== Reski Hotpot - Tên Lửa (6a2d41d4cbb65b24e7101656) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D9DD1AED07', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-20 02:00:00', '2026-03-20 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-04-02', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-20 00:00:00', '2026-03-20 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-D9DD1AED07-454648', 'MOMO33FE8765CD', '2026-03-20 02:00:00', 100000, '2026-03-20 11:00:00', '2026-03-20 00:00:00', '2026-03-20 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4CFC09946D', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-14 01:00:00', '2026-05-14 16:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-14 00:00:00', '2026-05-14 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-05-24', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-14 00:00:00', '2026-05-14 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-4CFC09946D-0810D1', 'MOMO8EECFBFCEA', '2026-05-14 01:00:00', 200000, '2026-05-14 16:00:00', '2026-05-14 00:00:00', '2026-05-14 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7363BBF106', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-25 04:00:00', '2026-05-25 12:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-06-07', '12:00:00', 12, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-05-25 00:00:00', '2026-05-25 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-7363BBF106-C5EDE1', 'MOMOEF9FC7480B', '2026-05-25 04:00:00', 1000000, '2026-05-25 12:00:00', '2026-05-25 00:00:00', '2026-05-25 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D0066A7D56', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-06 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-06 00:00:00', '2026-02-06 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-02-19', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-06 00:00:00', '2026-02-06 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-D0066A7D56-15DC9A', 'MOMOEF7F0D11CE', '2026-02-06 01:00:00', NULL, NULL, '2026-02-06 00:00:00', '2026-02-06 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-008C70A899', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-07 03:00:00', '2026-05-07 06:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-07 00:00:00', '2026-05-07 06:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-05-11', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-07 00:00:00', '2026-05-07 06:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-008C70A899-CE5626', 'MOMO34810E4F53', '2026-05-07 03:00:00', 200000, '2026-05-07 06:00:00', '2026-05-07 00:00:00', '2026-05-07 06:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-029C558540', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d4cbb65b24e7101656', 'reski-hotpot-ten-lua', 'Reski Hotpot - Tên Lửa', '2026-03-19', '18:30:00', 14, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-07 00:00:00', '2026-03-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-029C558540-292DB2', 'MOMO9483A3309F', '2026-03-07 02:00:00', NULL, NULL, '2026-03-07 00:00:00', '2026-03-07 02:00:00');

-- ==== Chay Garden Võ Văn Tần (6a2d41d3cbb65b24e7101654) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-87F37DB373', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-04-26', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-garden', 'Bàn sân vườn 8 khách', 1, 8, 200000, 200000, '2026-04-20 00:00:00', '2026-04-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-87F37DB373-12E74F', 'MOMOB4E7A32622', '2026-04-20 02:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EEDA900BBA', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-03-16', '12:00:00', 18, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 700000, 700000, '2026-03-13 00:00:00', '2026-03-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-EEDA900BBA-2FC7F4', 'MOMOD4FDE249E8', '2026-03-13 03:00:00', NULL, NULL, '2026-03-13 00:00:00', '2026-03-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-624BCDA43C', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-18 00:00:00', '2026-01-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-01-27', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-01-18 00:00:00', '2026-01-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-624BCDA43C-0D10B6', 'MOMO17B277CCC4', '2026-01-18 01:00:00', NULL, NULL, '2026-01-18 00:00:00', '2026-01-18 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-05466770C8', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-03-09', '18:00:00', 7, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-garden', 'Bàn sân vườn 8 khách', 1, 8, 200000, 200000, '2026-02-25 00:00:00', '2026-02-25 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-05466770C8-ACEBF7', 'MOMO0FD7A5F5F3', '2026-02-25 02:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B43FCE2A77', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-03-10', '18:30:00', 14, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 700000, 700000, '2026-03-08 00:00:00', '2026-03-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-B43FCE2A77-8454BB', 'MOMO763847BC7A', '2026-03-08 01:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-418A5C2481', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-09 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-01-13', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-01-09 00:00:00', '2026-01-09 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-418A5C2481-9DB1C2', 'MOMOE01469C820', '2026-01-09 02:00:00', NULL, NULL, '2026-01-09 00:00:00', '2026-01-09 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C7FC30C8E8', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-23 17:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-23 00:00:00', '2026-03-23 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d3cbb65b24e7101654', 'chay-garden-vo-van-tan', 'Chay Garden Võ Văn Tần', '2026-03-24', '19:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-garden', 'Bàn sân vườn 8 khách', 1, 8, 200000, 200000, '2026-03-23 00:00:00', '2026-03-23 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-C7FC30C8E8-DBFFED', NULL, NULL, NULL, NULL, '2026-03-23 00:00:00', '2026-03-23 17:00:00');

-- ==== Tám Riêu - Phan Xích Long (6a2d51aa9f177b313658bee3) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A01068A8A0', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-04 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-04 00:00:00', '2026-01-04 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-01-12', '12:00:00', 33, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-01-04 00:00:00', '2026-01-04 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-A01068A8A0-92B888', 'MOMO1BC642F0BB', '2026-01-04 02:00:00', NULL, NULL, '2026-01-04 00:00:00', '2026-01-04 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D28DBC47E7', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-02-23', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-16 00:00:00', '2026-02-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-D28DBC47E7-55F465', 'MOMO6F0A580D04', '2026-02-16 04:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5B150E445A', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-02-20', '18:00:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-02-08 00:00:00', '2026-02-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-5B150E445A-E8EB8D', 'MOMO5EC02B3268', '2026-02-08 03:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CF44FA29D1', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-18 00:00:00', '2026-02-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-03-01', '18:30:00', 31, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-02-18 00:00:00', '2026-02-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-CF44FA29D1-875CB2', 'MOMO19EC8DDC3A', '2026-02-18 03:00:00', NULL, NULL, '2026-02-18 00:00:00', '2026-02-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E82DD0F15A', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-01-11', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-08 00:00:00', '2026-01-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-E82DD0F15A-54D666', 'MOMOC927276E30', '2026-01-08 03:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-ED7E801220', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-05-18', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-05-10 00:00:00', '2026-05-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-ED7E801220-415012', 'MOMO092626EA42', '2026-05-10 01:00:00', NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2CD4D4E4A3', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-13 00:00:00', '2026-05-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-05-22', '20:00:00', 30, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-05-13 00:00:00', '2026-05-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-2CD4D4E4A3-520585', 'MOMO509B333356', '2026-05-13 01:00:00', NULL, NULL, '2026-05-13 00:00:00', '2026-05-13 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-693E3E9AA5', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-21 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-21 00:00:00', '2026-02-21 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee3', 'tam-rieu-phan-xich-long', 'Tám Riêu - Phan Xích Long', '2026-02-23', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-21 00:00:00', '2026-02-21 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-693E3E9AA5-E97016', 'MOMO78F084D74A', '2026-02-21 04:00:00', NULL, NULL, '2026-02-21 00:00:00', '2026-02-21 04:00:00');

-- ==== Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai (6a2d41d5cbb65b24e7101657) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A62AE89144', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d5cbb65b24e7101657', 'chay-pham-nghiem-trai', 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai', '2026-02-11', '12:30:00', 3, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 2, 4, 100000, 200000, '2026-02-10 00:00:00', '2026-02-10 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-A62AE89144-B0C01F', 'MOMO2102598253', '2026-02-10 03:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-118E595AC0', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d5cbb65b24e7101657', 'chay-pham-nghiem-trai', 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai', '2026-06-02', '18:00:00', 7, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn 8 VIP', 1, 8, 300000, 300000, '2026-05-20 00:00:00', '2026-05-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-118E595AC0-6AB98E', 'MOMO34C23B558F', '2026-05-20 04:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-72720B1EC3', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-15 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-15 00:00:00', '2026-05-15 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d5cbb65b24e7101657', 'chay-pham-nghiem-trai', 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai', '2026-05-23', '18:30:00', 19, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 800000, 800000, '2026-05-15 00:00:00', '2026-05-15 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-72720B1EC3-DD5286', 'MOMO421C66B985', '2026-05-15 03:00:00', NULL, NULL, '2026-05-15 00:00:00', '2026-05-15 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2453B7C862', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-02 00:00:00', '2026-03-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d5cbb65b24e7101657', 'chay-pham-nghiem-trai', 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai', '2026-03-04', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-03-02 00:00:00', '2026-03-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-2453B7C862-A3CB15', 'MOMOB63A4B01B8', '2026-03-02 04:00:00', NULL, NULL, '2026-03-02 00:00:00', '2026-03-02 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-91E7E56D51', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-01 00:00:00', '2026-03-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d5cbb65b24e7101657', 'chay-pham-nghiem-trai', 'Chay Phạm Nghiêm Trai - Nguyễn Đăng Giai', '2026-03-07', '19:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn 8 VIP', 1, 8, 300000, 300000, '2026-03-01 00:00:00', '2026-03-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-91E7E56D51-71B13E', 'MOMO6BCC4BB6FE', '2026-03-01 02:00:00', NULL, NULL, '2026-03-01 00:00:00', '2026-03-01 02:00:00');

-- ==== Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật (6a2d51aa9f177b313658bee4) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5110C0BCCC', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-17 03:00:00', '2026-02-17 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-17 00:00:00', '2026-02-17 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-02-28', '18:00:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 150000, 150000, '2026-02-17 00:00:00', '2026-02-17 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 150000, 'VND', 'MOMO', 'RB-5110C0BCCC-472CF6', 'MOMO38E82A4DDE', '2026-02-17 03:00:00', 150000, '2026-02-17 11:00:00', '2026-02-17 00:00:00', '2026-02-17 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4E619E4C50', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-19 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-19 00:00:00', '2026-04-19 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-04-23', '18:30:00', 16, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 800000, 800000, '2026-04-19 00:00:00', '2026-04-19 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-4E619E4C50-8A6848', 'MOMODC13719661', '2026-04-19 04:00:00', NULL, NULL, '2026-04-19 00:00:00', '2026-04-19 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F91B540F69', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-03-27', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-F91B540F69-67A02F', 'MOMO32CACFF90F', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CEB6BD9546', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-02-20', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 150000, 150000, '2026-02-09 00:00:00', '2026-02-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-CEB6BD9546-7E7338', 'MOMOB4B6E6DA49', '2026-02-09 04:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-320489CBC4', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-04-10', '20:00:00', 16, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 800000, 800000, '2026-04-06 00:00:00', '2026-04-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-320489CBC4-478570', 'MOMO8ECA126A19', '2026-04-06 03:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AA09E0B316', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-23 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-23 00:00:00', '2026-04-23 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51aa9f177b313658bee4', 'lau-ga-ot-hiem-109-nguyen-thien-thuat', 'Lẩu Gà Ớt Hiểm 109 - Nguyễn Thiện Thuật', '2026-04-27', '11:00:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-04-23 00:00:00', '2026-04-23 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-AA09E0B316-F6264E', 'MOMO137DDB66CC', '2026-04-23 03:00:00', NULL, NULL, '2026-04-23 00:00:00', '2026-04-23 03:00:00');

-- ==== Hotpot Đồng Quê 3 Premium - Quang Trung (6a2d51af9f177b313658bee6) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F41CD3DEB9', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-13 03:00:00', '2026-01-13 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-01-16', '18:30:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-01-13 00:00:00', '2026-01-13 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'RB-F41CD3DEB9-33DD63', 'MOMO1EFAD1DBC3', '2026-01-13 03:00:00', 1500000, '2026-01-13 11:00:00', '2026-01-13 00:00:00', '2026-01-13 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5E49FA0FA2', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-04 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-02-11', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-02-04 00:00:00', '2026-02-04 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-5E49FA0FA2-898A15', 'MOMO4556A685DF', '2026-02-04 03:00:00', NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EA5FFECBE2', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-01-22', '19:30:00', 10, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-8', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-01-15 00:00:00', '2026-01-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-EA5FFECBE2-477E1A', 'MOMOA85AC4FBF8', '2026-01-15 02:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5754858A7D', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-24 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-24 00:00:00', '2026-05-24 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-05-26', '20:00:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-05-24 00:00:00', '2026-05-24 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-5754858A7D-D01090', 'MOMO3178B8D16F', '2026-05-24 03:00:00', NULL, NULL, '2026-05-24 00:00:00', '2026-05-24 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D72526338E', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-05-25', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-05-18 00:00:00', '2026-05-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-D72526338E-74F8A6', 'MOMOCDCCCCE8B2', '2026-05-18 01:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2E30E90A40', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-02-27', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-8', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-02-23 00:00:00', '2026-02-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-2E30E90A40-F701C3', 'MOMO42EFEA41F9', '2026-02-23 01:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EA9DE3CB93', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-09 15:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 15:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee6', 'hotpot-dong-que-3-premium-quang-trung', 'Hotpot Đồng Quê 3 Premium - Quang Trung', '2026-01-16', '12:00:00', 22, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-01-09 00:00:00', '2026-01-09 15:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1500000, 'VND', 'MOMO', 'RB-EA9DE3CB93-C9F047', NULL, NULL, NULL, NULL, '2026-01-09 00:00:00', '2026-01-09 15:00:00');

-- ==== Ngõ 8 Võ Văn Tần (6a2d51af9f177b313658bee7) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F1CAF1B674', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-03 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-03 00:00:00', '2026-02-03 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-02-08', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-03 00:00:00', '2026-02-03 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-F1CAF1B674-4509CD', 'MOMOF13F787B10', '2026-02-03 02:00:00', NULL, NULL, '2026-02-03 00:00:00', '2026-02-03 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DD97FDB51B', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-05-29', '19:30:00', 5, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 2, 6, 200000, 400000, '2026-05-25 00:00:00', '2026-05-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-DD97FDB51B-A9F303', 'MOMOBA6E268246', '2026-05-25 03:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F8F321755C', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-03-31', '20:00:00', 17, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-03-27 00:00:00', '2026-03-27 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-F8F321755C-052111', 'MOMOE063E0E18D', '2026-03-27 03:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9AA21955A2', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-03-04', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-28 00:00:00', '2026-02-28 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-9AA21955A2-1E0A11', 'MOMO5CADF585C7', '2026-02-28 04:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D490716C42', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-30 10:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-30 00:00:00', '2026-01-30 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-02-04', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-01-30 00:00:00', '2026-01-30 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-D490716C42-E04C49', NULL, NULL, NULL, NULL, '2026-01-30 00:00:00', '2026-01-30 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D2E4D58871', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-26 00:00:00', '2026-04-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-04-28', '12:00:00', 16, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 2, 25, 1000000, 2000000, '2026-04-26 00:00:00', '2026-04-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-D2E4D58871-2DCE80', 'MOMOA0F127DB26', '2026-04-26 02:00:00', NULL, NULL, '2026-04-26 00:00:00', '2026-04-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-04FF84723F', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-06 00:00:00', '2026-02-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-02-16', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-06 00:00:00', '2026-02-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-04FF84723F-064675', 'MOMOAD12A69908', '2026-02-06 03:00:00', NULL, NULL, '2026-02-06 00:00:00', '2026-02-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-03B19DD2B3', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-14 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-14 00:00:00', '2026-05-14 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee7', 'ngo-8-vo-van-tan', 'Ngõ 8 Võ Văn Tần', '2026-05-20', '18:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6', 'Bàn 6', 1, 6, 200000, 200000, '2026-05-14 00:00:00', '2026-05-14 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-03B19DD2B3-40DF97', 'MOMOD75D8C3B4B', '2026-05-14 03:00:00', NULL, NULL, '2026-05-14 00:00:00', '2026-05-14 03:00:00');

-- ==== Tiệm Lẩu 8 Cá - Linh Đông (6a2d51af9f177b313658bee8) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4774972305', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee8', 'tiem-lau-8-ca-linh-dong', 'Tiệm Lẩu 8 Cá - Linh Đông', '2026-03-28', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-21 00:00:00', '2026-03-21 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-4774972305-1ADF93', 'MOMO71B4E10482', '2026-03-21 03:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A4F00147C1', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-10 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee8', 'tiem-lau-8-ca-linh-dong', 'Tiệm Lẩu 8 Cá - Linh Đông', '2026-05-11', '20:00:00', 28, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-05-10 00:00:00', '2026-05-10 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-A4F00147C1-EE4FE0', 'MOMO5091C3560A', '2026-05-10 02:00:00', NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E92B65DE9D', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee8', 'tiem-lau-8-ca-linh-dong', 'Tiệm Lẩu 8 Cá - Linh Đông', '2026-01-29', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-16 00:00:00', '2026-01-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-E92B65DE9D-9CB040', 'MOMO6856CF19B5', '2026-01-16 03:00:00', NULL, NULL, '2026-01-16 00:00:00', '2026-01-16 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3234744009', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-29 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-29 00:00:00', '2026-05-29 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee8', 'tiem-lau-8-ca-linh-dong', 'Tiệm Lẩu 8 Cá - Linh Đông', '2026-06-07', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-29 00:00:00', '2026-05-29 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-3234744009-DB85B7', 'MOMO3363A6C8B1', '2026-05-29 04:00:00', NULL, NULL, '2026-05-29 00:00:00', '2026-05-29 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FAC7F24DED', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-21 00:00:00', '2026-01-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51af9f177b313658bee8', 'tiem-lau-8-ca-linh-dong', 'Tiệm Lẩu 8 Cá - Linh Đông', '2026-01-24', '12:00:00', 16, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-01-21 00:00:00', '2026-01-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-FAC7F24DED-F7C029', 'MOMOEFBDF6A567', '2026-01-21 01:00:00', NULL, NULL, '2026-01-21 00:00:00', '2026-01-21 01:00:00');

-- ==== Bò Ú Plus - Lâm Văn Bền (6a2d51ab9f177b313658bee5) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-42B3B21544', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-03-07', '20:00:00', 26, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-02-28 00:00:00', '2026-02-28 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-42B3B21544-D2DAB1', 'MOMOD02FC6AFBB', '2026-02-28 04:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-350E31C5B6', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-21 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-21 00:00:00', '2026-05-21 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-05-25', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-21 00:00:00', '2026-05-21 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-350E31C5B6-6C9B1D', 'MOMO3524CE2A93', '2026-05-21 02:00:00', NULL, NULL, '2026-05-21 00:00:00', '2026-05-21 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0EF7D7BCA4', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-23 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-23 00:00:00', '2026-05-23 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-06-01', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-23 00:00:00', '2026-05-23 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-0EF7D7BCA4-776CD1', 'MOMO2EA1DD45CC', '2026-05-23 02:00:00', NULL, NULL, '2026-05-23 00:00:00', '2026-05-23 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-089F159016', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-10 02:00:00', '2026-03-10 05:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-10 00:00:00', '2026-03-10 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-03-11', '12:00:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-03-10 00:00:00', '2026-03-10 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'RB-089F159016-C69999', 'MOMOE7B29CD145', '2026-03-10 02:00:00', 1500000, '2026-03-10 05:00:00', '2026-03-10 00:00:00', '2026-03-10 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-79328CC17F', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-10 07:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-10 00:00:00', '2026-04-10 07:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-04-16', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-04-10 00:00:00', '2026-04-10 07:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 100000, 'VND', 'MOMO', 'RB-79328CC17F-2214CF', NULL, NULL, NULL, NULL, '2026-04-10 00:00:00', '2026-04-10 07:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-57E406FFF5', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-10 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-10 00:00:00', '2026-01-10 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51ab9f177b313658bee5', 'bo-u-plus-lam-van-ben', 'Bò Ú Plus - Lâm Văn Bền', '2026-01-20', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-10 00:00:00', '2026-01-10 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-57E406FFF5-18AE9A', 'MOMOBE40CE8EDB', '2026-01-10 02:00:00', NULL, NULL, '2026-01-10 00:00:00', '2026-01-10 02:00:00');

-- ==== Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão (6a2d51b29f177b313658beea) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-526FC7F3FB', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-03 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-03 00:00:00', '2026-04-03 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-04-15', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-04-03 00:00:00', '2026-04-03 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-526FC7F3FB-F6DC35', 'MOMO6370671517', '2026-04-03 04:00:00', NULL, NULL, '2026-04-03 00:00:00', '2026-04-03 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0D23AE4C4D', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-04-17', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-09 00:00:00', '2026-04-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-0D23AE4C4D-3FD07F', 'MOMO95F1180AF8', '2026-04-09 01:00:00', NULL, NULL, '2026-04-09 00:00:00', '2026-04-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9247EBE508', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1600000, 1600000, 1600000, 1600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-03-02', '12:00:00', 17, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 2, 25, 800000, 1600000, '2026-02-25 00:00:00', '2026-02-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1600000, 'VND', 'MOMO', 'RB-9247EBE508-77DF30', 'MOMO2EE87AFCC2', '2026-02-25 01:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BC8924F275', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-02-13', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-10 00:00:00', '2026-02-10 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-BC8924F275-B3AA5C', 'MOMOFCBBA61AF8', '2026-02-10 02:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A29E751E2C', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-03-24', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-21 00:00:00', '2026-03-21 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-A29E751E2C-1F89C7', 'MOMO5C780D5EAF', '2026-03-21 04:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0DCA86B772', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 800000, 800000, 800000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-14 01:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-04-16', '18:30:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 800000, 800000, '2026-04-14 00:00:00', '2026-04-14 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 800000, 'VND', 'MOMO', 'RB-0DCA86B772-A050B2', NULL, NULL, NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E50C8C30E1', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-10 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beea', 'lau-ga-ot-hiem-109-pham-ngu-lao', 'Lẩu Gà Ớt Hiểm 109 - Phạm Ngũ Lão', '2026-05-12', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-10 00:00:00', '2026-05-10 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-E50C8C30E1-224AF9', 'MOMOA4DED65F86', '2026-05-10 04:00:00', NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 04:00:00');

-- ==== Nhà hàng Yuhua - Lê Văn Sỹ (6a2d51b29f177b313658beeb) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9311688C6F', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-09 00:00:00', '2026-05-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-05-13', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-09 00:00:00', '2026-05-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-9311688C6F-E90DDC', 'MOMOE7F062088A', '2026-05-09 01:00:00', NULL, NULL, '2026-05-09 00:00:00', '2026-05-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-34DF27D0C8', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-30 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-30 00:00:00', '2026-01-30 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-02-04', '12:00:00', 12, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 2, 20, 1000000, 2000000, '2026-01-30 00:00:00', '2026-01-30 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-34DF27D0C8-B32F87', 'MOMO2CAEE4620D', '2026-01-30 01:00:00', NULL, NULL, '2026-01-30 00:00:00', '2026-01-30 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AE5EFF850B', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-03-29', '12:30:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-03-28 00:00:00', '2026-03-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-AE5EFF850B-BC63AD', 'MOMO670ED2F295', '2026-03-28 01:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3F1BC63BC2', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-03-14', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-05 00:00:00', '2026-03-05 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-3F1BC63BC2-7E88EB', 'MOMOF7707BC466', '2026-03-05 03:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3A010D1FBA', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-03-05', '18:30:00', 27, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-03 00:00:00', '2026-03-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-3A010D1FBA-36D375', 'MOMODF5B38F255', '2026-03-03 03:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-246CE3F4B2', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-21 00:00:00', '2026-01-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-01-27', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-21 00:00:00', '2026-01-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-246CE3F4B2-09365E', 'MOMO0A81A0267E', '2026-01-21 01:00:00', NULL, NULL, '2026-01-21 00:00:00', '2026-01-21 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-122F215138', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-19 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-19 00:00:00', '2026-05-19 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-05-31', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-19 00:00:00', '2026-05-19 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-122F215138-CDEBF8', 'MOMO1EE2603D01', '2026-05-19 01:00:00', NULL, NULL, '2026-05-19 00:00:00', '2026-05-19 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-603C54202E', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b29f177b313658beeb', 'yuhua-le-van-sy', 'Nhà hàng Yuhua - Lê Văn Sỹ', '2026-02-08', '20:00:00', 13, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-27 00:00:00', '2026-01-27 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-603C54202E-8DB006', 'MOMO5FF3ADFC57', '2026-01-27 01:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 01:00:00');

-- ==== Yakimono - Phan Xích Long (6a2d51b49f177b313658beec) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DF997A7185', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-31 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-31 00:00:00', '2026-05-31 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b49f177b313658beec', 'yakimono-phan-xich-long', 'Yakimono - Phan Xích Long', '2026-06-03', '12:00:00', 13, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-16', 'Phòng riêng 16 khách', 1, 16, 1000000, 1000000, '2026-05-31 00:00:00', '2026-05-31 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-DF997A7185-C1E9DA', 'MOMOC49EFB23F4', '2026-05-31 03:00:00', NULL, NULL, '2026-05-31 00:00:00', '2026-05-31 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-92DBACD61E', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b49f177b313658beec', 'yakimono-phan-xich-long', 'Yakimono - Phan Xích Long', '2026-03-13', '12:30:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-03-05 00:00:00', '2026-03-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-92DBACD61E-BA38E7', 'MOMO9FE40F2F10', '2026-03-05 01:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5E7356163A', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-06 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-06 00:00:00', '2026-03-06 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b49f177b313658beec', 'yakimono-phan-xich-long', 'Yakimono - Phan Xích Long', '2026-03-09', '18:00:00', 4, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 2, 4, 200000, 400000, '2026-03-06 00:00:00', '2026-03-06 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-5E7356163A-A2FB7C', 'MOMO50019D183C', '2026-03-06 04:00:00', NULL, NULL, '2026-03-06 00:00:00', '2026-03-06 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4F5F90E832', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-03 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b49f177b313658beec', 'yakimono-phan-xich-long', 'Yakimono - Phan Xích Long', '2026-01-14', '18:30:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-16', 'Phòng riêng 16 khách', 1, 16, 1000000, 1000000, '2026-01-03 00:00:00', '2026-01-03 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-4F5F90E832-2CD707', 'MOMOF50F1D8C17', '2026-01-03 04:00:00', NULL, NULL, '2026-01-03 00:00:00', '2026-01-03 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-66C95070EF', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b49f177b313658beec', 'yakimono-phan-xich-long', 'Yakimono - Phan Xích Long', '2026-04-03', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-25 00:00:00', '2026-03-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-66C95070EF-1D4421', 'MOMOB4E8D4CD02', '2026-03-25 04:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 04:00:00');

-- ==== Tasaki BBQ - Vạn Hạnh Mall (6a2d41d0cbb65b24e7101652) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4329F26686', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-02-05', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-02 00:00:00', '2026-02-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-4329F26686-18453B', 'MOMO5FCFDDD73E', '2026-02-02 01:00:00', NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-31A69686B6', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-05 00:00:00', '2026-04-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-04-14', '18:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn 6 VIP', 1, 6, 400000, 400000, '2026-04-05 00:00:00', '2026-04-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-31A69686B6-3D1733', 'MOMO0A1554BA84', '2026-04-05 01:00:00', NULL, NULL, '2026-04-05 00:00:00', '2026-04-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0A60F8913C', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-01-22', '18:30:00', 28, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-13 00:00:00', '2026-01-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-0A60F8913C-869065', 'MOMO85084CF9E0', '2026-01-13 02:00:00', NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-91D0110DB8', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-04 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-04 00:00:00', '2026-01-04 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-01-11', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-04 00:00:00', '2026-01-04 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-91D0110DB8-89FA28', 'MOMOC5078B847D', '2026-01-04 02:00:00', NULL, NULL, '2026-01-04 00:00:00', '2026-01-04 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FDB737815A', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-04-12', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-30 00:00:00', '2026-03-30 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-FDB737815A-AA66D4', 'MOMO0BFF52BA0F', '2026-03-30 02:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BF09CD929C', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-02 00:00:00', '2026-04-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d41d0cbb65b24e7101652', 'tasaki-van-hanh-mall', 'Tasaki BBQ - Vạn Hạnh Mall', '2026-04-05', '20:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn 6 VIP', 1, 6, 400000, 400000, '2026-04-02 00:00:00', '2026-04-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-BF09CD929C-2113D9', 'MOMO85A29FA619', '2026-04-02 04:00:00', NULL, NULL, '2026-04-02 00:00:00', '2026-04-02 04:00:00');

-- ==== Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2 (6a2d51b59f177b313658beee) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8464ED6A3E', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-02-04', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8 ngoài trời', 1, 8, 200000, 200000, '2026-01-26 00:00:00', '2026-01-26 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-8464ED6A3E-314829', 'MOMO8F805A4086', '2026-01-26 03:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5864C2ECD9', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-03 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-01-14', '18:30:00', 21, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-01-03 00:00:00', '2026-01-03 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-5864C2ECD9-225CC5', 'MOMO43B3A99FEE', '2026-01-03 02:00:00', NULL, NULL, '2026-01-03 00:00:00', '2026-01-03 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-45B06A3A79', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-09 00:00:00', '2026-05-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-05-17', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-05-09 00:00:00', '2026-05-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-45B06A3A79-0D77EB', 'MOMO135938342E', '2026-05-09 01:00:00', NULL, NULL, '2026-05-09 00:00:00', '2026-05-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F5A60B4C1D', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-07 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-03-11', '19:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8 ngoài trời', 1, 8, 200000, 200000, '2026-03-07 00:00:00', '2026-03-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F5A60B4C1D-1D8DE3', 'MOMOEBF5F81FF1', '2026-03-07 04:00:00', NULL, NULL, '2026-03-07 00:00:00', '2026-03-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6C1530FD5E', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-02-09', '20:00:00', 24, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-01-31 00:00:00', '2026-01-31 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-6C1530FD5E-A6D672', 'MOMO00BD2F5A6C', '2026-01-31 01:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3A593C7619', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-06-07', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-05-25 00:00:00', '2026-05-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-3A593C7619-AA895F', 'MOMOA7C2C6BA78', '2026-05-25 01:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A526075191', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beee', 'lau-ga-ot-hiem-109-hoang-dieu-2', 'Lẩu Gà Ớt Hiểm 109 - Hoàng Diệu 2', '2026-03-14', '11:30:00', 9, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8 ngoài trời', 2, 8, 200000, 400000, '2026-03-08 00:00:00', '2026-03-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-A526075191-B77C23', 'MOMOB5CB31BE1E', '2026-03-08 03:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 03:00:00');

-- ==== Happy Lamb Hotpot - Trần Hưng Đạo (6a2d51a99f177b313658bee2) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D9A425CE50', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 4000000, 4000000, 4000000, 4000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-02-09', '18:30:00', 31, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 2, 40, 2000000, 4000000, '2026-02-07 00:00:00', '2026-02-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4000000, 'VND', 'MOMO', 'RB-D9A425CE50-387E7F', 'MOMO180F2C8136', '2026-02-07 02:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-373313D445', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-01-21', '19:00:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 200000, 400000, '2026-01-17 00:00:00', '2026-01-17 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-373313D445-89B1CA', 'MOMOB05788563D', '2026-01-17 03:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A842C20895', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-29 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-29 00:00:00', '2026-03-29 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-04-08', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 400000, 400000, '2026-03-29 00:00:00', '2026-03-29 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-A842C20895-5F72E2', 'MOMO7A34A080A4', '2026-03-29 04:00:00', NULL, NULL, '2026-03-29 00:00:00', '2026-03-29 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9623E18B13', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-26 09:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-26 00:00:00', '2026-04-26 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-04-28', '20:00:00', 17, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-04-26 00:00:00', '2026-04-26 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 2000000, 'VND', 'MOMO', 'RB-9623E18B13-D2073C', NULL, NULL, NULL, NULL, '2026-04-26 00:00:00', '2026-04-26 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4857E92F79', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-25 01:00:00', '2026-05-25 05:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-06-03', '11:00:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 200000, 400000, '2026-05-25 00:00:00', '2026-05-25 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 400000, 'VND', 'MOMO', 'RB-4857E92F79-BB2278', 'MOMO17403A3659', '2026-05-25 01:00:00', 400000, '2026-05-25 05:00:00', '2026-05-25 00:00:00', '2026-05-25 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CEDE94933F', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-27 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-27 00:00:00', '2026-02-27 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-02-28', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 400000, 400000, '2026-02-27 00:00:00', '2026-02-27 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-CEDE94933F-0975DB', 'MOMOBA71108DCB', '2026-02-27 03:00:00', NULL, NULL, '2026-02-27 00:00:00', '2026-02-27 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F71A804789', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-12 00:00:00', '2026-05-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-05-21', '12:00:00', 16, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-05-12 00:00:00', '2026-05-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-F71A804789-30F835', 'MOMO692CE6F389', '2026-05-12 03:00:00', NULL, NULL, '2026-05-12 00:00:00', '2026-05-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4012080E9A', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51a99f177b313658bee2', 'happy-lamb-hotpot-tran-hung-dao', 'Happy Lamb Hotpot - Trần Hưng Đạo', '2026-03-22', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 200000, 200000, '2026-03-20 00:00:00', '2026-03-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-4012080E9A-138BDE', 'MOMO69C138A39B', '2026-03-20 04:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 04:00:00');

-- ==== Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai (6a2d5b5881491622bb578b77) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6378D7AF38', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5881491622bb578b77', 'gyu-shige-nguyen-thi-minh-khai', 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai', '2026-03-14', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-12 00:00:00', '2026-03-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-6378D7AF38-853138', 'MOMOAAD96169E6', '2026-03-12 03:00:00', NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F38D5EBB36', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5881491622bb578b77', 'gyu-shige-nguyen-thi-minh-khai', 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai', '2026-02-14', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-11 00:00:00', '2026-02-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F38D5EBB36-A6CE4F', 'MOMOF4AAEB958B', '2026-02-11 03:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-79E19EC930', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-21 04:00:00', '2026-02-21 14:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-21 00:00:00', '2026-02-21 14:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5881491622bb578b77', 'gyu-shige-nguyen-thi-minh-khai', 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai', '2026-02-27', '20:00:00', 12, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-02-21 00:00:00', '2026-02-21 14:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-79E19EC930-7A8B13', 'MOMO2B709D0A4F', '2026-02-21 04:00:00', 1000000, '2026-02-21 14:00:00', '2026-02-21 00:00:00', '2026-02-21 14:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B7981F1C3E', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5881491622bb578b77', 'gyu-shige-nguyen-thi-minh-khai', 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai', '2026-02-26', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-14 00:00:00', '2026-02-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-B7981F1C3E-FE2875', 'MOMO1301EBA69A', '2026-02-14 02:00:00', NULL, NULL, '2026-02-14 00:00:00', '2026-02-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-73570E0C5C', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-03 08:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 08:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5881491622bb578b77', 'gyu-shige-nguyen-thi-minh-khai', 'Gyu Shige Ngưu Phồn - Nguyễn Thị Minh Khai', '2026-03-07', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-03 00:00:00', '2026-03-03 08:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-73570E0C5C-42E6FE', NULL, NULL, NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 08:00:00');

-- ==== GAON BBQ Nguyễn Trãi (6a2d5b5981491622bb578b78) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3F73847530', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-23 00:00:00', '2026-01-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-01-30', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 300000, 300000, '2026-01-23 00:00:00', '2026-01-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-3F73847530-1F27FC', 'MOMO29BD9BCE60', '2026-01-23 01:00:00', NULL, NULL, '2026-01-23 00:00:00', '2026-01-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-084AA2EBCB', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-04-25', '20:00:00', 31, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-04-17 00:00:00', '2026-04-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-084AA2EBCB-464AA6', 'MOMO6D7534AF39', '2026-04-17 04:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-63AFB9E90F', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-06 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-06 00:00:00', '2026-01-06 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-01-19', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 150000, 150000, '2026-01-06 00:00:00', '2026-01-06 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-63AFB9E90F-7BD07D', 'MOMOD182E39D7C', '2026-01-06 04:00:00', NULL, NULL, '2026-01-06 00:00:00', '2026-01-06 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F6C574F0D5', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-02-18', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 300000, 300000, '2026-02-11 00:00:00', '2026-02-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-F6C574F0D5-B3D4CA', 'MOMO11B5A6EECA', '2026-02-11 01:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-37E3F62E9B', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-29 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-29 00:00:00', '2026-03-29 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-04-11', '12:00:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-03-29 00:00:00', '2026-03-29 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-37E3F62E9B-461D3D', 'MOMOB9DD49090B', '2026-03-29 01:00:00', NULL, NULL, '2026-03-29 00:00:00', '2026-03-29 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5654003D44', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-24 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-24 00:00:00', '2026-01-24 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b5981491622bb578b78', 'gaon-bbq-nguyen-trai', 'GAON BBQ Nguyễn Trãi', '2026-02-06', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 150000, 150000, '2026-01-24 00:00:00', '2026-01-24 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-5654003D44-2DEB79', 'MOMO2B9DA1D734', '2026-01-24 02:00:00', NULL, NULL, '2026-01-24 00:00:00', '2026-01-24 02:00:00');

-- ==== Quán Nướng Yaki - Chế Lan Viên (6a2d5b7a81491622bb578b79) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C9AF481DA6', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-14 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-14 00:00:00', '2026-03-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-03-20', '20:00:00', 13, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-14 00:00:00', '2026-03-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-C9AF481DA6-DE3966', 'MOMO6E1C0D59FB', '2026-03-14 04:00:00', NULL, NULL, '2026-03-14 00:00:00', '2026-03-14 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C41C7D5FBA', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-06 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-06 00:00:00', '2026-05-06 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-05-15', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-06 00:00:00', '2026-05-06 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-C41C7D5FBA-0E307B', 'MOMOD362D6BF03', '2026-05-06 04:00:00', NULL, NULL, '2026-05-06 00:00:00', '2026-05-06 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-070A8C3AFA', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-25 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-25 00:00:00', '2026-04-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-04-29', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-25 00:00:00', '2026-04-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-070A8C3AFA-CE4C0C', 'MOMO38986ABA80', '2026-04-25 04:00:00', NULL, NULL, '2026-04-25 00:00:00', '2026-04-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A6A94AE2E5', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-02 00:00:00', '2026-03-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-03-10', '12:00:00', 19, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-02 00:00:00', '2026-03-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-A6A94AE2E5-B64B88', 'MOMO6ACE6EC78E', '2026-03-02 01:00:00', NULL, NULL, '2026-03-02 00:00:00', '2026-03-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C49F65E752', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-17 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-17 00:00:00', '2026-05-17 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-05-24', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-17 00:00:00', '2026-05-17 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-C49F65E752-1EA28D', 'MOMO40AE0A2376', '2026-05-17 01:00:00', NULL, NULL, '2026-05-17 00:00:00', '2026-05-17 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-794BCF732D', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-03-28', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-25 00:00:00', '2026-03-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-794BCF732D-7D3E9C', 'MOMOCB5A19EC4B', '2026-03-25 03:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6608A9EC1A', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-11 00:00:00', '2026-03-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7a81491622bb578b79', 'quan-nuong-yaki-che-lan-vien', 'Quán Nướng Yaki - Chế Lan Viên', '2026-03-12', '18:30:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-11 00:00:00', '2026-03-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-6608A9EC1A-9EB03A', 'MOMO2307D490BA', '2026-03-11 03:00:00', NULL, NULL, '2026-03-11 00:00:00', '2026-03-11 03:00:00');

-- ==== Galbi House Phan Xích Long (6a2d5b7b81491622bb578b7a) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-893F117981', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-06-03 01:00:00', '2026-06-03 10:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-06-03 00:00:00', '2026-06-03 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-06-07', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-06-03 00:00:00', '2026-06-03 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-893F117981-989A10', 'MOMO1F26B29053', '2026-06-03 01:00:00', 100000, '2026-06-03 10:00:00', '2026-06-03 00:00:00', '2026-06-03 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DE217CC1BE', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-26 00:00:00', '2026-02-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-03-02', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-26 00:00:00', '2026-02-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-DE217CC1BE-6CA845', 'MOMOB9AE79B270', '2026-02-26 02:00:00', NULL, NULL, '2026-02-26 00:00:00', '2026-02-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-05CBEDBE78', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-03-11', '12:00:00', 22, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-03-03 00:00:00', '2026-03-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-05CBEDBE78-DE0895', 'MOMO825EF873EF', '2026-03-03 03:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EBF4D3FA19', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-03-17', '12:30:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-03-08 00:00:00', '2026-03-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-EBF4D3FA19-90C2CA', 'MOMO7F1DE09F66', '2026-03-08 01:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C3F8B48A6D', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-11 03:00:00', '2026-05-11 16:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-11 00:00:00', '2026-05-11 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-05-23', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-11 00:00:00', '2026-05-11 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-C3F8B48A6D-F9D3C5', 'MOMO41C67EFB93', '2026-05-11 03:00:00', 200000, '2026-05-11 16:00:00', '2026-05-11 00:00:00', '2026-05-11 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1D3F5C6F92', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-21 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-21 00:00:00', '2026-05-21 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-05-30', '18:30:00', 34, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-05-21 00:00:00', '2026-05-21 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-1D3F5C6F92-A5FD44', 'MOMO2DD8B89250', '2026-05-21 02:00:00', NULL, NULL, '2026-05-21 00:00:00', '2026-05-21 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-328908E70D', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-11 00:00:00', '2026-01-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-01-23', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-11 00:00:00', '2026-01-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-328908E70D-58249E', 'MOMOD6DC9DAB29', '2026-01-11 01:00:00', NULL, NULL, '2026-01-11 00:00:00', '2026-01-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-61BAF23706', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-23 00:00:00', '2026-01-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b7b81491622bb578b7a', 'galbi-house-phan-xich-long', 'Galbi House Phan Xích Long', '2026-01-28', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-23 00:00:00', '2026-01-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-61BAF23706-96D6CF', 'MOMO6F9A2E11C8', '2026-01-23 01:00:00', NULL, NULL, '2026-01-23 00:00:00', '2026-01-23 01:00:00');

-- ==== Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh (6a2d5b8781491622bb578b7b) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-10F144FFB7', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-28 00:00:00', '2026-05-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8781491622bb578b7b', 'yaki-yaki-yo-tay-thanh', 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh', '2026-06-03', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-28 00:00:00', '2026-05-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-10F144FFB7-F62EEF', 'MOMOF417377553', '2026-05-28 03:00:00', NULL, NULL, '2026-05-28 00:00:00', '2026-05-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-15329A4DAF', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-18 00:00:00', '2026-01-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8781491622bb578b7b', 'yaki-yaki-yo-tay-thanh', 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh', '2026-01-28', '12:00:00', 28, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 1000000, 2000000, '2026-01-18 00:00:00', '2026-01-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-15329A4DAF-0F169A', 'MOMO7B68DEF516', '2026-01-18 03:00:00', NULL, NULL, '2026-01-18 00:00:00', '2026-01-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AD8EA04455', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-26 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-26 00:00:00', '2026-02-26 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8781491622bb578b7b', 'yaki-yaki-yo-tay-thanh', 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh', '2026-02-28', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-26 00:00:00', '2026-02-26 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-AD8EA04455-4451C0', 'MOMOED3B1B3B41', '2026-02-26 04:00:00', NULL, NULL, '2026-02-26 00:00:00', '2026-02-26 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CE08EB8860', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8781491622bb578b7b', 'yaki-yaki-yo-tay-thanh', 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh', '2026-02-10', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-02 00:00:00', '2026-02-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-CE08EB8860-E5AB2B', 'MOMO5F9563C62D', '2026-02-02 01:00:00', NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C337553150', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8781491622bb578b7b', 'yaki-yaki-yo-tay-thanh', 'Yaki Yaki Yo - Buffet nướng lẩu băng chuyền - Tây Thạnh', '2026-01-10', '18:30:00', 21, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-01-02 00:00:00', '2026-01-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-C337553150-EAB5E7', 'MOMOC9693E89EB', '2026-01-02 04:00:00', NULL, NULL, '2026-01-02 00:00:00', '2026-01-02 04:00:00');

-- ==== Lẩu Nướng Mini Candy - Nguyễn Văn Đậu (6a2d51c09f177b313658beef) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F3F6502712', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1400000, 1400000, 1400000, 1400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-12 00:00:00', '2026-02-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-02-20', '12:00:00', 10, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-24', 'Phòng riêng 24 khách', 2, 24, 700000, 1400000, '2026-02-12 00:00:00', '2026-02-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1400000, 'VND', 'MOMO', 'RB-F3F6502712-5FAFF0', 'MOMO666EE082F0', '2026-02-12 03:00:00', NULL, NULL, '2026-02-12 00:00:00', '2026-02-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2AF75DA192', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-14 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-04-24', '12:30:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-04-14 00:00:00', '2026-04-14 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-2AF75DA192-5A062E', 'MOMOF6B412EC98', '2026-04-14 01:00:00', NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-483F516C74', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-29 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-02-04', '18:00:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 150000, 150000, '2026-01-29 00:00:00', '2026-01-29 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-483F516C74-E954EE', 'MOMO1B49267FCC', '2026-01-29 02:00:00', NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B193787928', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-07 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-03-13', '18:30:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-24', 'Phòng riêng 24 khách', 1, 24, 700000, 700000, '2026-03-07 00:00:00', '2026-03-07 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-B193787928-7268DC', 'MOMO1D509CFC8F', '2026-03-07 03:00:00', NULL, NULL, '2026-03-07 00:00:00', '2026-03-07 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-021B3467D6', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-21 08:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 08:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-04-01', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-21 00:00:00', '2026-03-21 08:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 100000, 'VND', 'MOMO', 'RB-021B3467D6-753C27', NULL, NULL, NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 08:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0AB1C8121B', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-22 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-22 00:00:00', '2026-02-22 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51c09f177b313658beef', 'lau-nuong-mini-candy-nguyen-van-dau', 'Lẩu Nướng Mini Candy - Nguyễn Văn Đậu', '2026-03-04', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 150000, 150000, '2026-02-22 00:00:00', '2026-02-22 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-0AB1C8121B-83CD85', 'MOMO9016040A09', '2026-02-22 01:00:00', NULL, NULL, '2026-02-22 00:00:00', '2026-02-22 01:00:00');

-- ==== Ghiền BBQ Nướng & Lẩu - Lê Cơ (6a2d51b09f177b313658bee9) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A2A6A16C97', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-02-12', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-08 00:00:00', '2026-02-08 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-A2A6A16C97-D902A0', 'MOMODBDF2599B7', '2026-02-08 04:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2B8659EBEE', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-16 00:00:00', '2026-05-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-05-27', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-05-16 00:00:00', '2026-05-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-2B8659EBEE-E1E66C', 'MOMO062DEB99F7', '2026-05-16 04:00:00', NULL, NULL, '2026-05-16 00:00:00', '2026-05-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4DAB5F1E51', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-25 17:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-25 00:00:00', '2026-01-25 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-02-02', '18:30:00', 18, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-01-25 00:00:00', '2026-01-25 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-4DAB5F1E51-D390FF', NULL, NULL, NULL, NULL, '2026-01-25 00:00:00', '2026-01-25 17:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A0C88B7632', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-17 00:00:00', '2026-02-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-02-22', '19:00:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-02-17 00:00:00', '2026-02-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-A0C88B7632-CB6116', 'MOMOFF76020CE4', '2026-02-17 04:00:00', NULL, NULL, '2026-02-17 00:00:00', '2026-02-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7373A62330', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-02-13', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-10 00:00:00', '2026-02-10 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-7373A62330-F0C530', 'MOMO04FD1A6C7C', '2026-02-10 04:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-76EB3D8967', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-07 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-07 00:00:00', '2026-04-07 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-04-08', '20:00:00', 36, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-04-07 00:00:00', '2026-04-07 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-76EB3D8967-C65090', 'MOMO36C223B1B7', '2026-04-07 01:00:00', NULL, NULL, '2026-04-07 00:00:00', '2026-04-07 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D9B244CAFA', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-02 03:00:00', '2026-05-02 01:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b09f177b313658bee9', 'ghien-bbq-nuong-lau-le-co', 'Ghiền BBQ Nướng & Lẩu - Lê Cơ', '2026-05-04', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-02 00:00:00', '2026-05-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-D9B244CAFA-B3AD16', 'MOMO298D049BCC', '2026-05-02 03:00:00', 100000, '2026-05-02 01:00:00', '2026-05-02 00:00:00', '2026-05-02 01:00:00');

-- ==== Lẩu Dê 404 - Dương Đức Hiền (6a2d51b59f177b313658beed) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1843B25F37', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-03-30', '18:00:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 2, 8, 200000, 400000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-1843B25F37-86B60E', 'MOMO1D54C0A809', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6B19A57673', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-28 03:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-28 00:00:00', '2026-04-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-05-02', '18:30:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-04-28 00:00:00', '2026-04-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-6B19A57673-512DA4', NULL, NULL, NULL, NULL, '2026-04-28 00:00:00', '2026-04-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-81EF3ECB78', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-11 00:00:00', '2026-03-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-03-17', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-03-11 00:00:00', '2026-03-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-81EF3ECB78-1B7C9E', 'MOMO531A6856FD', '2026-03-11 01:00:00', NULL, NULL, '2026-03-11 00:00:00', '2026-03-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-91E2E9DF20', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-02 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-02 00:00:00', '2026-04-02 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-04-03', '19:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 1, 8, 200000, 200000, '2026-04-02 00:00:00', '2026-04-02 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-91E2E9DF20-B9A303', 'MOMO8E9E256EC7', '2026-04-02 03:00:00', NULL, NULL, '2026-04-02 00:00:00', '2026-04-02 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6FFA9F3BB9', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-29 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-29 00:00:00', '2026-05-29 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-06-01', '20:00:00', 30, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-05-29 00:00:00', '2026-05-29 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-6FFA9F3BB9-BD512E', 'MOMO488F10F7BE', '2026-05-29 02:00:00', NULL, NULL, '2026-05-29 00:00:00', '2026-05-29 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-65935F7A90', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-05 04:00:00', '2026-02-05 02:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-05 00:00:00', '2026-02-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-02-11', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-02-05 00:00:00', '2026-02-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-65935F7A90-714225', 'MOMO47E6D3EFB6', '2026-02-05 04:00:00', 100000, '2026-02-05 02:00:00', '2026-02-05 00:00:00', '2026-02-05 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B80D28A7D7', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-01-16', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 1, 8, 200000, 200000, '2026-01-14 00:00:00', '2026-01-14 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-B80D28A7D7-8C6093', 'MOMOB111F00589', '2026-01-14 01:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-89D3E5EAD0', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-07 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d51b59f177b313658beed', 'lau-de-404-duong-duc-hien', 'Lẩu Dê 404 - Dương Đức Hiền', '2026-03-10', '12:00:00', 33, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1000000, 1000000, '2026-03-07 00:00:00', '2026-03-07 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-89D3E5EAD0-9CCBF0', 'MOMO064D3A3D84', '2026-03-07 01:00:00', NULL, NULL, '2026-03-07 00:00:00', '2026-03-07 01:00:00');

-- ==== Buffet King BBQ Vincom Thủ Đức (6a2d5b8e81491622bb578b7d) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BD14CEDA0D', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-14 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8e81491622bb578b7d', 'buffet-king-bbq-vincom-thu-duc', 'Buffet King BBQ Vincom Thủ Đức', '2026-04-27', '18:30:00', 27, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-04-14 00:00:00', '2026-04-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-BD14CEDA0D-9CE0F7', 'MOMO6178973C22', '2026-04-14 04:00:00', NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C2761E42C8', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-20 00:00:00', '2026-01-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8e81491622bb578b7d', 'buffet-king-bbq-vincom-thu-duc', 'Buffet King BBQ Vincom Thủ Đức', '2026-01-31', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-01-20 00:00:00', '2026-01-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-C2761E42C8-C5AB3A', 'MOMO26D242C04F', '2026-01-20 02:00:00', NULL, NULL, '2026-01-20 00:00:00', '2026-01-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-679C8C3D67', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8e81491622bb578b7d', 'buffet-king-bbq-vincom-thu-duc', 'Buffet King BBQ Vincom Thủ Đức', '2026-02-07', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-31 00:00:00', '2026-01-31 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-679C8C3D67-7F092B', 'MOMOB96B9F0927', '2026-01-31 01:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3EA7945393', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-01 04:00:00', '2026-05-01 18:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-01 00:00:00', '2026-05-01 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8e81491622bb578b7d', 'buffet-king-bbq-vincom-thu-duc', 'Buffet King BBQ Vincom Thủ Đức', '2026-05-04', '20:00:00', 39, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-05-01 00:00:00', '2026-05-01 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'RB-3EA7945393-4C8033', 'MOMO9ADDC0A483', '2026-05-01 04:00:00', 1500000, '2026-05-01 18:00:00', '2026-05-01 00:00:00', '2026-05-01 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E3FA224F1A', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-24 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-24 00:00:00', '2026-02-24 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8e81491622bb578b7d', 'buffet-king-bbq-vincom-thu-duc', 'Buffet King BBQ Vincom Thủ Đức', '2026-03-02', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-24 00:00:00', '2026-02-24 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-E3FA224F1A-EA1CEB', 'MOMOC466CF4346', '2026-02-24 03:00:00', NULL, NULL, '2026-02-24 00:00:00', '2026-02-24 03:00:00');

-- ==== Ba Gác - Vietnamese Grill & Beer - Quang Trung (6a2d5b8981491622bb578b7c) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1979FC21D2', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-03-01', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-02-28 00:00:00', '2026-02-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-1979FC21D2-58E291', 'MOMO615F489EBD', '2026-02-28 01:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6CF882D458', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-15 00:00:00', '2026-03-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-03-18', '19:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 1, 8, 200000, 200000, '2026-03-15 00:00:00', '2026-03-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-6CF882D458-A6ADFB', 'MOMOF53DFCACFE', '2026-03-15 02:00:00', NULL, NULL, '2026-03-15 00:00:00', '2026-03-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C9A6624973', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-02-18', '20:00:00', 24, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-02-13 00:00:00', '2026-02-13 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-C9A6624973-AEB823', 'MOMO9EDE4D8E40', '2026-02-13 04:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C22ADCE585', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-01-29', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 100000, 100000, '2026-01-16 00:00:00', '2026-01-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-C22ADCE585-3EECF4', 'MOMO641B0857CB', '2026-01-16 02:00:00', NULL, NULL, '2026-01-16 00:00:00', '2026-01-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CD5614BB15', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-22 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-22 00:00:00', '2026-04-22 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-05-05', '11:30:00', 6, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 2, 8, 200000, 400000, '2026-04-22 00:00:00', '2026-04-22 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-CD5614BB15-2B9EAB', 'MOMOD94442280F', '2026-04-22 02:00:00', NULL, NULL, '2026-04-22 00:00:00', '2026-04-22 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B0075E1408', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8981491622bb578b7c', 'ba-gac-quang-trung', 'Ba Gác - Vietnamese Grill & Beer - Quang Trung', '2026-03-22', '12:00:00', 34, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-03-19 00:00:00', '2026-03-19 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-B0075E1408-57511A', 'MOMODD9621BD6B', '2026-03-19 03:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 03:00:00');

-- ==== CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa (6a2d5b8f81491622bb578b7e) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E064EC2EE5', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-06 03:00:00', '2026-02-06 13:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-06 00:00:00', '2026-02-06 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-02-16', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 300000, 300000, '2026-02-06 00:00:00', '2026-02-06 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-E064EC2EE5-13160A', 'MOMO2D919BFED1', '2026-02-06 03:00:00', 300000, '2026-02-06 13:00:00', '2026-02-06 00:00:00', '2026-02-06 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6748C37F20', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-01 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-01 00:00:00', '2026-03-01 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-03-13', '20:00:00', 21, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-03-01 00:00:00', '2026-03-01 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-6748C37F20-1A8510', 'MOMO7C59CFE8D1', '2026-03-01 01:00:00', NULL, NULL, '2026-03-01 00:00:00', '2026-03-01 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-06B2F4AB33', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-19 03:00:00', '2026-03-19 02:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-03-25', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 200000, 200000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-06B2F4AB33-7A23E0', 'MOMOD2B4F9DEF2', '2026-03-19 03:00:00', 200000, '2026-03-19 02:00:00', '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-87977D8C38', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-01-28', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 300000, 300000, '2026-01-16 00:00:00', '2026-01-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-87977D8C38-80FCFC', 'MOMOB918245F47', '2026-01-16 02:00:00', NULL, NULL, '2026-01-16 00:00:00', '2026-01-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-00F15A6233', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-23 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-23 00:00:00', '2026-05-23 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-05-27', '12:00:00', 38, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-05-23 00:00:00', '2026-05-23 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-00F15A6233-D67C76', 'MOMO14A950654A', '2026-05-23 02:00:00', NULL, NULL, '2026-05-23 00:00:00', '2026-05-23 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38F9F957A4', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-21 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-21 00:00:00', '2026-05-21 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-06-03', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 200000, 200000, '2026-05-21 00:00:00', '2026-05-21 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-38F9F957A4-C91E10', 'MOMOB03306F545', '2026-05-21 04:00:00', NULL, NULL, '2026-05-21 00:00:00', '2026-05-21 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3A1D227470', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-20 00:00:00', '2026-02-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b8f81491622bb578b7e', 'coco-grill-nam-ky-khoi-nghia', 'CoCo Grill Saigon - Nam Kỳ Khởi Nghĩa', '2026-02-23', '18:00:00', 3, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 2, 4, 300000, 600000, '2026-02-20 00:00:00', '2026-02-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-3A1D227470-312E9F', 'MOMO153A85B591', '2026-02-20 02:00:00', NULL, NULL, '2026-02-20 00:00:00', '2026-02-20 02:00:00');

-- ==== Quán Nướng Yaki - Tân Sơn Nhì (6a2d5b9c81491622bb578b82) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-36025BEE3B', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-22 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-22 00:00:00', '2026-01-22 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-01-25', '20:00:00', 14, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-22 00:00:00', '2026-01-22 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-36025BEE3B-F9E781', 'MOMO5D61A3E6FA', '2026-01-22 04:00:00', NULL, NULL, '2026-01-22 00:00:00', '2026-01-22 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-817F578C0F', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-03-11', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-03-07 00:00:00', '2026-03-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-817F578C0F-DB9A41', 'MOMO2EDD56E5CA', '2026-03-07 02:00:00', NULL, NULL, '2026-03-07 00:00:00', '2026-03-07 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-58FB8B6E0B', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-04-28', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-16 00:00:00', '2026-04-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-58FB8B6E0B-A13B8A', 'MOMOC7294FC82E', '2026-04-16 02:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-84B821D508', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-04 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-04 00:00:00', '2026-03-04 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-03-09', '12:00:00', 12, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-03-04 00:00:00', '2026-03-04 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-84B821D508-490E7B', 'MOMO96D95C80A1', '2026-03-04 03:00:00', NULL, NULL, '2026-03-04 00:00:00', '2026-03-04 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8E05A76982', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-04-22', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-04-17 00:00:00', '2026-04-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-8E05A76982-9EC528', 'MOMOE009FAA244', '2026-04-17 04:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B6FC15E5AE', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-03-30', '18:00:00', 3, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 2, 4, 200000, 400000, '2026-03-25 00:00:00', '2026-03-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-B6FC15E5AE-FC1E53', 'MOMO1E10DD0344', '2026-03-25 01:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-81167EDD14', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-02-13', '18:30:00', 22, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 1000000, 1000000, '2026-01-31 00:00:00', '2026-01-31 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-81167EDD14-889920', 'MOMO5B3BE83BA4', '2026-01-31 03:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-838763C3E7', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9c81491622bb578b82', 'quan-nuong-yaki-tan-son-nhi', 'Quán Nướng Yaki - Tân Sơn Nhì', '2026-05-26', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-20 00:00:00', '2026-05-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-838763C3E7-DAB271', 'MOMOC12A445268', '2026-05-20 02:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 02:00:00');

-- ==== Mabu-KKO Chi - Thái Văn Lung (6a2d5b9b81491622bb578b81) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A223E7D14B', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b81', 'mabu-kko-chi-thai-van-lung', 'Mabu-KKO Chi - Thái Văn Lung', '2026-03-09', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn nướng 2', 1, 2, 100000, 100000, '2026-03-05 00:00:00', '2026-03-05 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-A223E7D14B-6699D8', 'MOMO986302F58C', '2026-03-05 04:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9638510FC8', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-06 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-06 00:00:00', '2026-03-06 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b81', 'mabu-kko-chi-thai-van-lung', 'Mabu-KKO Chi - Thái Văn Lung', '2026-03-10', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn nướng 4', 1, 4, 200000, 200000, '2026-03-06 00:00:00', '2026-03-06 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-9638510FC8-FA85ED', 'MOMO32077F6545', '2026-03-06 02:00:00', NULL, NULL, '2026-03-06 00:00:00', '2026-03-06 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7A9A669949', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-24 00:00:00', '2026-04-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b81', 'mabu-kko-chi-thai-van-lung', 'Mabu-KKO Chi - Thái Văn Lung', '2026-05-04', '12:00:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-24', 'Phòng riêng 24 khách', 1, 24, 1000000, 1000000, '2026-04-24 00:00:00', '2026-04-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-7A9A669949-881E48', 'MOMOAD7D249EF0', '2026-04-24 01:00:00', NULL, NULL, '2026-04-24 00:00:00', '2026-04-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-06A8BE7E57', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-07 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-07 00:00:00', '2026-01-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b81', 'mabu-kko-chi-thai-van-lung', 'Mabu-KKO Chi - Thái Văn Lung', '2026-01-11', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn nướng 2', 1, 2, 100000, 100000, '2026-01-07 00:00:00', '2026-01-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-06A8BE7E57-F3B4B7', 'MOMOF2F2F9EE6A', '2026-01-07 04:00:00', NULL, NULL, '2026-01-07 00:00:00', '2026-01-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7FEE32CDA9', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b81', 'mabu-kko-chi-thai-van-lung', 'Mabu-KKO Chi - Thái Văn Lung', '2026-04-18', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn nướng 4', 1, 4, 200000, 200000, '2026-04-13 00:00:00', '2026-04-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-7FEE32CDA9-9EF61A', 'MOMO53386FE9B8', '2026-04-13 02:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 02:00:00');

-- ==== Tiệm Thân Nướng - Liên Phường (6a2d5b9381491622bb578b7f) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-916EFA20CA', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-14 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-14 00:00:00', '2026-03-14 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-03-27', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-03-14 00:00:00', '2026-03-14 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-916EFA20CA-2E7412', 'MOMO85596DA58B', '2026-03-14 03:00:00', NULL, NULL, '2026-03-14 00:00:00', '2026-03-14 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D201BC9D14', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-10 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-10 00:00:00', '2026-03-10 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-03-21', '12:00:00', 38, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-03-10 00:00:00', '2026-03-10 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-D201BC9D14-35D738', 'MOMOD3C881DD36', '2026-03-10 04:00:00', NULL, NULL, '2026-03-10 00:00:00', '2026-03-10 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-17E8834466', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-01 01:00:00', '2026-02-01 12:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-02-03', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-01 00:00:00', '2026-02-01 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-17E8834466-76AF37', 'MOMOEDE2E56F15', '2026-02-01 01:00:00', 100000, '2026-02-01 12:00:00', '2026-02-01 00:00:00', '2026-02-01 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E23D363145', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-04 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-04 00:00:00', '2026-04-04 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-04-07', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-04 00:00:00', '2026-04-04 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-E23D363145-541623', 'MOMO298C5646D0', '2026-04-04 01:00:00', NULL, NULL, '2026-04-04 00:00:00', '2026-04-04 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AFA793EEBC', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-18 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-18 00:00:00', '2026-02-18 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-03-03', '18:30:00', 19, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 1000000, 1000000, '2026-02-18 00:00:00', '2026-02-18 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-AFA793EEBC-A6657D', 'MOMO5BD2882150', '2026-02-18 04:00:00', NULL, NULL, '2026-02-18 00:00:00', '2026-02-18 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6EFDCE07A8', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-10 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9381491622bb578b7f', 'tiem-than-nuong-lien-phuong', 'Tiệm Thân Nướng - Liên Phường', '2026-05-14', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-10 00:00:00', '2026-05-10 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-6EFDCE07A8-149F0F', 'MOMO50A94D00DD', '2026-05-10 02:00:00', NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 02:00:00');

-- ==== Yakimono - Vincom Plaza 3/2 (6a2d5b9b81491622bb578b80) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BD7B887CF1', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-29 09:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-02-05', '12:00:00', 26, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 1500000, 3000000, '2026-01-29 00:00:00', '2026-01-29 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 3000000, 'VND', 'MOMO', 'RB-BD7B887CF1-D8CBA2', NULL, NULL, NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DEC0F0FD89', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-02-20', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-02-13 00:00:00', '2026-02-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-DEC0F0FD89-123D98', 'MOMO31192E5FE6', '2026-02-13 03:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-14136D6615', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-02-05', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-01-31 00:00:00', '2026-01-31 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-14136D6615-252CD8', 'MOMOC9B1D5D0B7', '2026-01-31 03:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C00892C6D1', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-03-28', '18:30:00', 40, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 1500000, 3000000, '2026-03-27 00:00:00', '2026-03-27 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-C00892C6D1-0C72AE', 'MOMO2ED243CF1C', '2026-03-27 03:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E7C26B3CDB', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-05-03', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-02 00:00:00', '2026-05-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-E7C26B3CDB-36A09E', 'MOMO3751A8A9D2', '2026-05-02 04:00:00', NULL, NULL, '2026-05-02 00:00:00', '2026-05-02 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BB025FF1F3', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-18 00:00:00', '2026-02-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-02-26', '19:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-02-18 00:00:00', '2026-02-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-BB025FF1F3-637CA4', 'MOMO275B96416B', '2026-02-18 01:00:00', NULL, NULL, '2026-02-18 00:00:00', '2026-02-18 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-77F3E34752', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a2d5b9b81491622bb578b80', 'yakimono-vincom-plaza-3-2', 'Yakimono - Vincom Plaza 3/2', '2026-04-10', '20:00:00', 27, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-03-30 00:00:00', '2026-03-30 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-77F3E34752-897B6B', 'MOMOC2293465AC', '2026-03-30 02:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 02:00:00');
