-- Seed demo bookings RESTAURANT cho khu vuc: ha-noi
-- 5-8 booking/restaurant, ngay dat ban rai trong 1-6 thang qua, thanh toan (dat coc) qua MOMO_WALLET
-- payOption luon DEPOSIT (dung flow that: totalAmount == depositAmount == amountPayable)
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Lẩu Nấm Thảo Quý - Lê Đức Thọ (6a45938dc6a3905f886ca637) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0D030C377A', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-22 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-22 00:00:00', '2026-05-22 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-06-01', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-tq', 'Bàn 4 người', 1, 4, 0, 0, '2026-05-22 00:00:00', '2026-05-22 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-0D030C377A-6BF554', 'MOMO6A7F424564', '2026-05-22 03:00:00', NULL, NULL, '2026-05-22 00:00:00', '2026-05-22 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3A3CA265AB', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-19 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-19 00:00:00', '2026-05-19 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-05-26', '18:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-tq', 'Bàn 6 người', 1, 6, 150000, 150000, '2026-05-19 00:00:00', '2026-05-19 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-3A3CA265AB-8086B2', 'MOMO03E32648B0', '2026-05-19 03:00:00', NULL, NULL, '2026-05-19 00:00:00', '2026-05-19 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-44644C38DC', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-13 03:00:00', '2026-03-13 12:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-03-25', '18:30:00', 12, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20-tq', 'Phòng riêng 20 khách', 1, 20, 800000, 800000, '2026-03-13 00:00:00', '2026-03-13 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 800000, 'VND', 'MOMO', 'RB-44644C38DC-5B1F49', 'MOMO729C3B7038', '2026-03-13 03:00:00', 800000, '2026-03-13 12:00:00', '2026-03-13 00:00:00', '2026-03-13 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B9D1DC3614', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-11 16:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-11 00:00:00', '2026-01-11 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-01-16', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-tq', 'Bàn 4 người', 1, 4, 0, 0, '2026-01-11 00:00:00', '2026-01-11 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-B9D1DC3614-6C2CE5', NULL, NULL, NULL, NULL, '2026-01-11 00:00:00', '2026-01-11 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-35EE224B7D', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-01-12', '19:30:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-tq', 'Bàn 6 người', 1, 6, 150000, 150000, '2026-01-08 00:00:00', '2026-01-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-35EE224B7D-8241D7', 'MOMO937F859351', '2026-01-08 01:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B23A131E0E', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1600000, 1600000, 1600000, 1600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-02 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-01-12', '20:00:00', 20, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20-tq', 'Phòng riêng 20 khách', 2, 20, 800000, 1600000, '2026-01-02 00:00:00', '2026-01-02 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1600000, 'VND', 'MOMO', 'RB-B23A131E0E-49E5E3', 'MOMO8141257F9D', '2026-01-02 03:00:00', NULL, NULL, '2026-01-02 00:00:00', '2026-01-02 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A5731FD3A7', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-28 00:00:00', '2026-04-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-05-07', '11:00:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-tq', 'Bàn 4 người', 2, 4, 0, 0, '2026-04-28 00:00:00', '2026-04-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-A5731FD3A7-6F5938', 'MOMO9531020C69', '2026-04-28 01:00:00', NULL, NULL, '2026-04-28 00:00:00', '2026-04-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-23E37782B3', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-13 00:00:00', '2026-05-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938dc6a3905f886ca637', 'lau-nam-thao-quy-le-duc-tho', 'Lẩu Nấm Thảo Quý - Lê Đức Thọ', '2026-05-22', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-tq', 'Bàn 6 người', 1, 6, 150000, 150000, '2026-05-13 00:00:00', '2026-05-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-23E37782B3-3CC926', 'MOMO39881325E1', '2026-05-13 01:00:00', NULL, NULL, '2026-05-13 00:00:00', '2026-05-13 01:00:00');

-- ==== WuLong Hotpot - Nguyễn Khánh Toàn (6a45938ec6a3905f886ca63a) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8042313F17', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-26 01:00:00', '2026-05-26 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-26 00:00:00', '2026-05-26 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938ec6a3905f886ca63a', 'wu-long-hotpot-nguyen-khanh-toan', 'WuLong Hotpot - Nguyễn Khánh Toàn', '2026-06-06', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 500000, 500000, '2026-05-26 00:00:00', '2026-05-26 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 500000, 'VND', 'MOMO', 'RB-8042313F17-EAA769', 'MOMO1FF9947525', '2026-05-26 01:00:00', 500000, '2026-05-26 11:00:00', '2026-05-26 00:00:00', '2026-05-26 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E9500E7933', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-15 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-15 00:00:00', '2026-05-15 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938ec6a3905f886ca63a', 'wu-long-hotpot-nguyen-khanh-toan', 'WuLong Hotpot - Nguyễn Khánh Toàn', '2026-05-24', '18:30:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 2000000, 2000000, '2026-05-15 00:00:00', '2026-05-15 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-E9500E7933-ADA108', 'MOMO03FB0C022D', '2026-05-15 03:00:00', NULL, NULL, '2026-05-15 00:00:00', '2026-05-15 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9891398E78', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-07 19:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 19:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938ec6a3905f886ca63a', 'wu-long-hotpot-nguyen-khanh-toan', 'WuLong Hotpot - Nguyễn Khánh Toàn', '2026-02-08', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4 người', 1, 4, 200000, 200000, '2026-02-07 00:00:00', '2026-02-07 19:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-9891398E78-CA2F46', NULL, NULL, NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 19:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-79F9678E23', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-02 03:00:00', '2026-03-02 13:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-02 00:00:00', '2026-03-02 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938ec6a3905f886ca63a', 'wu-long-hotpot-nguyen-khanh-toan', 'WuLong Hotpot - Nguyễn Khánh Toàn', '2026-03-13', '19:30:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 500000, 500000, '2026-03-02 00:00:00', '2026-03-02 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 500000, 'VND', 'MOMO', 'RB-79F9678E23-59F3DC', 'MOMOB7B8F45615', '2026-03-02 03:00:00', 500000, '2026-03-02 13:00:00', '2026-03-02 00:00:00', '2026-03-02 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FE7045105E', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 4000000, 4000000, 4000000, 4000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-22 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-22 00:00:00', '2026-01-22 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938ec6a3905f886ca63a', 'wu-long-hotpot-nguyen-khanh-toan', 'WuLong Hotpot - Nguyễn Khánh Toàn', '2026-02-04', '20:00:00', 19, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 2000000, 4000000, '2026-01-22 00:00:00', '2026-01-22 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4000000, 'VND', 'MOMO', 'RB-FE7045105E-593399', 'MOMO29C5AA0463', '2026-01-22 04:00:00', NULL, NULL, '2026-01-22 00:00:00', '2026-01-22 04:00:00');

-- ==== Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ (6a45938fc6a3905f886ca63b) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FCCE59206E', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-02 02:00:00', '2026-05-02 20:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 20:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-05-08', '18:30:00', 10, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-10-ldkb', 'Phòng riêng 10 khách', 1, 10, 300000, 300000, '2026-05-02 00:00:00', '2026-05-02 20:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-FCCE59206E-1C3DBF', 'MOMOA19F6AFA24', '2026-05-02 02:00:00', 300000, '2026-05-02 20:00:00', '2026-05-02 00:00:00', '2026-05-02 20:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38BA1D1DFD', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-15 04:00:00', '2026-04-15 02:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-04-28', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-ldkb', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-04-15 00:00:00', '2026-04-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 0, 'VND', 'MOMO', 'RB-38BA1D1DFD-80CC49', 'MOMO8D022C96B0', '2026-04-15 04:00:00', 0, '2026-04-15 02:00:00', '2026-04-15 00:00:00', '2026-04-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-228D88811E', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-04-04', '19:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-outdoor-ldkb', 'Bàn ngoài trời 6 người', 1, 6, 0, 0, '2026-03-28 00:00:00', '2026-03-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-228D88811E-371871', 'MOMO65CC4D3AEF', '2026-03-28 03:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CCD2A5E699', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-03-29', '20:00:00', 10, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-10-ldkb', 'Phòng riêng 10 khách', 1, 10, 300000, 300000, '2026-03-20 00:00:00', '2026-03-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-CCD2A5E699-1A7300', 'MOMO1BBD283D77', '2026-03-20 02:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-62AB31D6E3', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-05-27', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-ldkb', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-05-20 00:00:00', '2026-05-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-62AB31D6E3-AB7770', 'MOMO994B9A6697', '2026-05-20 04:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A536E65ABB', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45938fc6a3905f886ca63b', 'lau-de-kho-thu-beo-lang-ha', 'Nhà hàng Lẩu Dê Khô Thu Béo - Láng Hạ', '2026-01-28', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-outdoor-ldkb', 'Bàn ngoài trời 6 người', 1, 6, 0, 0, '2026-01-15 00:00:00', '2026-01-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-A536E65ABB-800A1C', 'MOMO4F861ACAFE', '2026-01-15 04:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 04:00:00');

-- ==== Nhắng Nướng - Đại Cồ Việt (6a459390c6a3905f886ca63c) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3B0888DAB0', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-03 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-03 00:00:00', '2026-04-03 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-04-13', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4 người', 1, 4, 0, 0, '2026-04-03 00:00:00', '2026-04-03 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-3B0888DAB0-1BFD09', 'MOMO2B09D5EF5D', '2026-04-03 02:00:00', NULL, NULL, '2026-04-03 00:00:00', '2026-04-03 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C2CD40D374', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-15 00:00:00', '2026-05-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-05-23', '19:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8 người', 1, 8, 0, 0, '2026-05-15 00:00:00', '2026-05-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-C2CD40D374-349777', 'MOMO61237A217C', '2026-05-15 02:00:00', NULL, NULL, '2026-05-15 00:00:00', '2026-05-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3132FFE305', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-06 02:00:00', '2026-02-06 09:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-06 00:00:00', '2026-02-06 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-02-08', '20:00:00', 22, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-45', 'Phòng riêng 20-45 khách', 2, 45, 500000, 1000000, '2026-02-06 00:00:00', '2026-02-06 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-3132FFE305-431C4D', 'MOMOD70A1B1015', '2026-02-06 02:00:00', 1000000, '2026-02-06 09:00:00', '2026-02-06 00:00:00', '2026-02-06 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E24615FBA3', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-02-07', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4 người', 1, 4, 0, 0, '2026-01-27 00:00:00', '2026-01-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-E24615FBA3-07C32A', 'MOMOE443B9F974', '2026-01-27 02:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5767D816FB', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-04 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-02-12', '11:30:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8 người', 2, 8, 0, 0, '2026-02-04 00:00:00', '2026-02-04 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-5767D816FB-1BCD8A', 'MOMOCC5E4FFA0E', '2026-02-04 01:00:00', NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0D8D77FFE3', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-03-08', '12:00:00', 28, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-45', 'Phòng riêng 20-45 khách', 1, 45, 500000, 500000, '2026-03-03 00:00:00', '2026-03-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-0D8D77FFE3-C73EEA', 'MOMOC105D4B0CC', '2026-03-03 03:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7B8A02233E', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63c', 'nhang-nuong-dai-co-viet', 'Nhắng Nướng - Đại Cồ Việt', '2026-01-16', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4 người', 1, 4, 0, 0, '2026-01-14 00:00:00', '2026-01-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-7B8A02233E-F761F1', 'MOMOBA223E73C7', '2026-01-14 02:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 02:00:00');

-- ==== Fu Rong Hua Đinh Tiên Hoàng (6a459390c6a3905f886ca63d) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4E3D57694C', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-02-25', '19:30:00', 9, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn đại tiệc 10 người', 1, 10, 700000, 700000, '2026-02-23 00:00:00', '2026-02-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-4E3D57694C-B31819', 'MOMOE1B2EA6A33', '2026-02-23 01:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D2206308DA', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-03-07', '20:00:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-vip-30', 'Phòng VIP 10-30 khách', 1, 30, 2000000, 2000000, '2026-03-05 00:00:00', '2026-03-05 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-D2206308DA-DB3368', 'MOMO9C0674014E', '2026-03-05 04:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E341052DF4', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-04-28', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-std', 'Bàn tròn tiêu chuẩn 6 người', 1, 6, 300000, 300000, '2026-04-17 00:00:00', '2026-04-17 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-E341052DF4-EF9CA1', 'MOMO801E89F5CC', '2026-04-17 01:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F2C5DE0776', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-27 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-27 00:00:00', '2026-02-27 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-03-09', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn đại tiệc 10 người', 1, 10, 700000, 700000, '2026-02-27 00:00:00', '2026-02-27 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-F2C5DE0776-79BA84', 'MOMO4E3BD59205', '2026-02-27 01:00:00', NULL, NULL, '2026-02-27 00:00:00', '2026-02-27 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C0CFAC2ECF', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-18 00:00:00', '2026-04-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-04-29', '12:00:00', 26, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-vip-30', 'Phòng VIP 10-30 khách', 1, 30, 2000000, 2000000, '2026-04-18 00:00:00', '2026-04-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-C0CFAC2ECF-FE2967', 'MOMO8F10872A98', '2026-04-18 03:00:00', NULL, NULL, '2026-04-18 00:00:00', '2026-04-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3AAE10C090', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-15 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-15 00:00:00', '2026-05-15 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-05-26', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-std', 'Bàn tròn tiêu chuẩn 6 người', 1, 6, 300000, 300000, '2026-05-15 00:00:00', '2026-05-15 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-3AAE10C090-4564D1', 'MOMO8762540BCA', '2026-05-15 03:00:00', NULL, NULL, '2026-05-15 00:00:00', '2026-05-15 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-12F83DB9D4', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-04-22', '18:00:00', 10, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn đại tiệc 10 người', 1, 10, 700000, 700000, '2026-04-09 00:00:00', '2026-04-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-12F83DB9D4-1C57C4', 'MOMOC5326040BE', '2026-04-09 01:00:00', NULL, NULL, '2026-04-09 00:00:00', '2026-04-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AAD0617F3F', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-06 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-06 00:00:00', '2026-01-06 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a459390c6a3905f886ca63d', 'fu-rong-hua-dinh-tien-hoang', 'Fu Rong Hua Đinh Tiên Hoàng', '2026-01-09', '18:30:00', 21, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-vip-30', 'Phòng VIP 10-30 khách', 1, 30, 2000000, 2000000, '2026-01-06 00:00:00', '2026-01-06 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-AAD0617F3F-1B6C8B', 'MOMOF027C65875', '2026-01-06 01:00:00', NULL, NULL, '2026-01-06 00:00:00', '2026-01-06 01:00:00');

-- ==== Nhà Hàng Lẩu Hơi Cosmos Giảng Võ (6a45939ec6a3905f886ca63e) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EE6BE228B3', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-22 05:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-22 00:00:00', '2026-03-22 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939ec6a3905f886ca63e', 'lau-hoi-cosmos-giang-vo', 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ', '2026-04-01', '20:00:00', 42, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng tối đa 50 khách', 1, 50, 2000000, 2000000, '2026-03-22 00:00:00', '2026-03-22 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 2000000, 'VND', 'MOMO', 'RB-EE6BE228B3-E0DA89', NULL, NULL, NULL, NULL, '2026-03-22 00:00:00', '2026-03-22 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-66C8E43122', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-07 00:00:00', '2026-05-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939ec6a3905f886ca63e', 'lau-hoi-cosmos-giang-vo', 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ', '2026-05-08', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 200000, 200000, '2026-05-07 00:00:00', '2026-05-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-66C8E43122-3A111A', 'MOMOBE988001EF', '2026-05-07 02:00:00', NULL, NULL, '2026-05-07 00:00:00', '2026-05-07 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2A88583539', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-30 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-30 00:00:00', '2026-04-30 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939ec6a3905f886ca63e', 'lau-hoi-cosmos-giang-vo', 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ', '2026-05-02', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn VIP 6 ghế', 1, 6, 400000, 400000, '2026-04-30 00:00:00', '2026-04-30 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-2A88583539-7F00E6', 'MOMO2E36D7BB80', '2026-04-30 01:00:00', NULL, NULL, '2026-04-30 00:00:00', '2026-04-30 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D3FC8706FA', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939ec6a3905f886ca63e', 'lau-hoi-cosmos-giang-vo', 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ', '2026-02-17', '12:00:00', 43, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng tối đa 50 khách', 1, 50, 2000000, 2000000, '2026-02-11 00:00:00', '2026-02-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-D3FC8706FA-B42EE4', 'MOMOCE4C8CD0CC', '2026-02-11 03:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CD306A69F1', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-12 00:00:00', '2026-04-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939ec6a3905f886ca63e', 'lau-hoi-cosmos-giang-vo', 'Nhà Hàng Lẩu Hơi Cosmos Giảng Võ', '2026-04-15', '12:30:00', 3, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 2, 4, 200000, 400000, '2026-04-12 00:00:00', '2026-04-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-CD306A69F1-949335', 'MOMOE7242D5E7B', '2026-04-12 01:00:00', NULL, NULL, '2026-04-12 00:00:00', '2026-04-12 01:00:00');

-- ==== Lẩu Bò Triều Châu Số 1 Phan Chu Trinh (6a45939fc6a3905f886ca63f) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-61B027C94D', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-19 12:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-19 00:00:00', '2026-04-19 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-04-27', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 200000, 200000, '2026-04-19 00:00:00', '2026-04-19 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-61B027C94D-06A6FD', NULL, NULL, NULL, NULL, '2026-04-19 00:00:00', '2026-04-19 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FD11FF0816', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-12 02:00:00', '2026-05-12 08:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-12 00:00:00', '2026-05-12 08:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-05-24', '11:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 500000, 500000, '2026-05-12 00:00:00', '2026-05-12 08:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 500000, 'VND', 'MOMO', 'RB-FD11FF0816-22269F', 'MOMO35A3AC7672', '2026-05-12 02:00:00', 500000, '2026-05-12 08:00:00', '2026-05-12 00:00:00', '2026-05-12 08:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AE29752143', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-04 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-04 00:00:00', '2026-05-04 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-05-14', '12:00:00', 18, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng tối đa 50 khách', 1, 50, 2000000, 2000000, '2026-05-04 00:00:00', '2026-05-04 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-AE29752143-09B096', 'MOMO8A7DEE35AA', '2026-05-04 02:00:00', NULL, NULL, '2026-05-04 00:00:00', '2026-05-04 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B16F98CA55', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-16 00:00:00', '2026-05-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-05-28', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 200000, 200000, '2026-05-16 00:00:00', '2026-05-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-B16F98CA55-0D0A28', 'MOMO7D2EBC4CE8', '2026-05-16 04:00:00', NULL, NULL, '2026-05-16 00:00:00', '2026-05-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-906D7397D5', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-04 10:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-02-05', '18:00:00', 6, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 2, 8, 500000, 1000000, '2026-02-04 00:00:00', '2026-02-04 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-906D7397D5-6371DB', NULL, NULL, NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A6F953D376', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-13 00:00:00', '2026-05-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a45939fc6a3905f886ca63f', 'lau-bo-trieu-chau-phan-chu-trinh', 'Lẩu Bò Triều Châu Số 1 Phan Chu Trinh', '2026-05-23', '18:30:00', 31, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng tối đa 50 khách', 1, 50, 2000000, 2000000, '2026-05-13 00:00:00', '2026-05-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-A6F953D376-022E20', 'MOMOFF5D8AA158', '2026-05-13 01:00:00', NULL, NULL, '2026-05-13 00:00:00', '2026-05-13 01:00:00');

-- ==== King BBQ Buffet Royal City (6a4593a0c6a3905f886ca640) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CBA0C70651', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-01-20', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 500000, 500000, '2026-01-08 00:00:00', '2026-01-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-CBA0C70651-8300D8', 'MOMODB977531B7', '2026-01-08 02:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4B175D64A7', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-28 00:00:00', '2026-05-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-06-03', '12:00:00', 34, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50-vip', 'Phòng riêng VIP 50 khách', 1, 50, 3000000, 3000000, '2026-05-28 00:00:00', '2026-05-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-4B175D64A7-695E22', 'MOMO250F1F2CC4', '2026-05-28 01:00:00', NULL, NULL, '2026-05-28 00:00:00', '2026-05-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FB0AAB9561', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-25 07:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-25 00:00:00', '2026-01-25 07:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-02-06', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn thường 4 ghế', 1, 4, 200000, 200000, '2026-01-25 00:00:00', '2026-01-25 07:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-FB0AAB9561-08C0F1', NULL, NULL, NULL, NULL, '2026-01-25 00:00:00', '2026-01-25 07:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6748959C4D', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-02-26', '18:00:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 500000, 500000, '2026-02-25 00:00:00', '2026-02-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-6748959C4D-A4D0C1', 'MOMO7A3EE9B794', '2026-02-25 01:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CF16214253', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-02-19', '18:30:00', 46, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50-vip', 'Phòng riêng VIP 50 khách', 1, 50, 3000000, 3000000, '2026-02-16 00:00:00', '2026-02-16 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-CF16214253-3AE3C8', 'MOMO8A9E92768A', '2026-02-16 01:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-03723660C2', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-04 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-04 00:00:00', '2026-03-04 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-03-10', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn thường 4 ghế', 1, 4, 200000, 200000, '2026-03-04 00:00:00', '2026-03-04 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-03723660C2-B0BFC7', 'MOMO8F7C466DE2', '2026-03-04 03:00:00', NULL, NULL, '2026-03-04 00:00:00', '2026-03-04 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D853005711', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca640', 'king-bbq-buffet-royal-city', 'King BBQ Buffet Royal City', '2026-04-08', '19:30:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 2, 8, 500000, 1000000, '2026-03-28 00:00:00', '2026-03-28 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-D853005711-6F9CBA', 'MOMOBD977A2515', '2026-03-28 02:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 02:00:00');

-- ==== Lộc-Ally Restaurant - Cát Linh (6a4593a0c6a3905f886ca641) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-32BC0AD76F', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 6000000, 6000000, 6000000, 6000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-04 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-04 00:00:00', '2026-05-04 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-05-17', '12:00:00', 37, 180, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 2, 50, 3000000, 6000000, '2026-05-04 00:00:00', '2026-05-04 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6000000, 'VND', 'MOMO', 'RB-32BC0AD76F-E947CF', 'MOMOEB35A797B0', '2026-05-04 04:00:00', NULL, NULL, '2026-05-04 00:00:00', '2026-05-04 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A12BEBD6B2', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 350000, 350000, 350000, 350000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-09 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-09 00:00:00', '2026-03-09 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-03-16', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách', 1, 4, 350000, 350000, '2026-03-09 00:00:00', '2026-03-09 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 350000, 'VND', 'MOMO', 'RB-A12BEBD6B2-023E2F', 'MOMOE69971C8EE', '2026-03-09 03:00:00', NULL, NULL, '2026-03-09 00:00:00', '2026-03-09 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8AD7F7C6F3', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-01-18', '18:00:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 khách', 1, 8, 700000, 700000, '2026-01-17 00:00:00', '2026-01-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-8AD7F7C6F3-661B39', 'MOMO5F044C5EB4', '2026-01-17 04:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B2E7DFDA2C', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-21 00:00:00', '2026-01-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-02-02', '18:30:00', 19, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 1, 50, 3000000, 3000000, '2026-01-21 00:00:00', '2026-01-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-B2E7DFDA2C-677A74', 'MOMO0DF4E1D7A7', '2026-01-21 01:00:00', NULL, NULL, '2026-01-21 00:00:00', '2026-01-21 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-937FDE35B0', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 350000, 350000, 350000, 350000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-20 01:00:00', '2026-04-20 03:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-05-01', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách', 1, 4, 350000, 350000, '2026-04-20 00:00:00', '2026-04-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 350000, 'VND', 'MOMO', 'RB-937FDE35B0-2192C4', 'MOMOE92C486079', '2026-04-20 01:00:00', 350000, '2026-04-20 03:00:00', '2026-04-20 00:00:00', '2026-04-20 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F37AFD9C71', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-08 00:00:00', '2026-04-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-04-17', '19:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 khách', 1, 8, 700000, 700000, '2026-04-08 00:00:00', '2026-04-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-F37AFD9C71-3DC49D', 'MOMO872EE9619A', '2026-04-08 01:00:00', NULL, NULL, '2026-04-08 00:00:00', '2026-04-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A9AC66EB18', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 6000000, 6000000, 6000000, 6000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-04-02', '20:00:00', 39, 180, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 2, 50, 3000000, 6000000, '2026-03-28 00:00:00', '2026-03-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6000000, 'VND', 'MOMO', 'RB-A9AC66EB18-7D9F97', 'MOMO2AACD393CF', '2026-03-28 01:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DCACEC010C', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 350000, 350000, 350000, 350000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-05 04:00:00', '2026-05-05 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-05 00:00:00', '2026-05-05 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a0c6a3905f886ca641', 'loc-ally-restaurant-cat-linh', 'Lộc-Ally Restaurant - Cát Linh', '2026-05-14', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách', 1, 4, 350000, 350000, '2026-05-05 00:00:00', '2026-05-05 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 350000, 'VND', 'MOMO', 'RB-DCACEC010C-5A366A', 'MOMO52426CC8C7', '2026-05-05 04:00:00', 350000, '2026-05-05 11:00:00', '2026-05-05 00:00:00', '2026-05-05 11:00:00');

-- ==== Nhà hàng MALA buffet - Đường Láng (6a4593a1c6a3905f886ca642) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C6FA2202FE', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-11 00:00:00', '2026-01-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca642', 'mala-buffet-duong-lang', 'Nhà hàng MALA buffet - Đường Láng', '2026-01-20', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn 4 người', 1, 4, 150000, 150000, '2026-01-11 00:00:00', '2026-01-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-C6FA2202FE-6A415D', 'MOMO271F961486', '2026-01-11 03:00:00', NULL, NULL, '2026-01-11 00:00:00', '2026-01-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-493C4A060F', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca642', 'mala-buffet-duong-lang', 'Nhà hàng MALA buffet - Đường Láng', '2026-04-17', '18:00:00', 5, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn 8 người', 2, 8, 300000, 600000, '2026-04-13 00:00:00', '2026-04-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-493C4A060F-0B3320', 'MOMOA068664ECC', '2026-04-13 01:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-36EF306BB3', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca642', 'mala-buffet-duong-lang', 'Nhà hàng MALA buffet - Đường Láng', '2026-06-01', '18:30:00', 11, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-05-25 00:00:00', '2026-05-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-36EF306BB3-29C1BD', 'MOMO1CBA2B9E01', '2026-05-25 04:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F03456692D', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-12 00:00:00', '2026-05-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca642', 'mala-buffet-duong-lang', 'Nhà hàng MALA buffet - Đường Láng', '2026-05-25', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn 4 người', 1, 4, 150000, 150000, '2026-05-12 00:00:00', '2026-05-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-F03456692D-98EEDA', 'MOMOCFF2DF3A56', '2026-05-12 01:00:00', NULL, NULL, '2026-05-12 00:00:00', '2026-05-12 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C124944B76', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 600000, 600000, 600000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-13 09:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca642', 'mala-buffet-duong-lang', 'Nhà hàng MALA buffet - Đường Láng', '2026-01-14', '19:30:00', 6, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn 8 người', 2, 8, 300000, 600000, '2026-01-13 00:00:00', '2026-01-13 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 600000, 'VND', 'MOMO', 'RB-C124944B76-7C43AD', NULL, NULL, NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 09:00:00');

-- ==== SHACHA NIÚ Lương Định Của (6a4593a1c6a3905f886ca643) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-432877E408', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-02-19', '18:00:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 500000, 500000, '2026-02-13 00:00:00', '2026-02-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-432877E408-2289CD', 'MOMO29BCD3DE3E', '2026-02-13 03:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A6E84FAD5C', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-23 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-23 00:00:00', '2026-03-23 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-03-29', '18:30:00', 25, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-03-23 00:00:00', '2026-03-23 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-A6E84FAD5C-E843B2', 'MOMO228AE7CD8C', '2026-03-23 02:00:00', NULL, NULL, '2026-03-23 00:00:00', '2026-03-23 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E58D28C609', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-22 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-22 00:00:00', '2026-03-22 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-03-27', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn thường 4 người', 1, 4, 200000, 200000, '2026-03-22 00:00:00', '2026-03-22 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-E58D28C609-852BF0', 'MOMO672C10C3BD', '2026-03-22 01:00:00', NULL, NULL, '2026-03-22 00:00:00', '2026-03-22 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AE9012A14B', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-01-24', '19:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 500000, 500000, '2026-01-17 00:00:00', '2026-01-17 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-AE9012A14B-D466F6', 'MOMOAE2CA67C4A', '2026-01-17 03:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-ED890FF0FA', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-27 00:00:00', '2026-05-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-06-03', '20:00:00', 26, 150, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 1500000, 3000000, '2026-05-27 00:00:00', '2026-05-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-ED890FF0FA-5A9A99', 'MOMO88048C41E3', '2026-05-27 02:00:00', NULL, NULL, '2026-05-27 00:00:00', '2026-05-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0E23697CDE', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-25 14:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 14:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a1c6a3905f886ca643', 'shacha-niu-luong-dinh-cua', 'SHACHA NIÚ Lương Định Của', '2026-03-09', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn thường 4 người', 1, 4, 200000, 200000, '2026-02-25 00:00:00', '2026-02-25 14:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-0E23697CDE-317FBF', NULL, NULL, NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 14:00:00');

-- ==== Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng (6a4593a2c6a3905f886ca644) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A72775B880', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-04 02:00:00', '2026-04-04 03:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-04 00:00:00', '2026-04-04 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-04-12', '18:30:00', 15, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 1500000, 3000000, '2026-04-04 00:00:00', '2026-04-04 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 3000000, 'VND', 'MOMO', 'RB-A72775B880-E06C92', 'MOMO6FE8D61EC0', '2026-04-04 02:00:00', 3000000, '2026-04-04 03:00:00', '2026-04-04 00:00:00', '2026-04-04 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FA01A0E66F', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-08 00:00:00', '2026-05-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-05-20', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-05-08 00:00:00', '2026-05-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-FA01A0E66F-9E602C', 'MOMOBF7429FDD8', '2026-05-08 02:00:00', NULL, NULL, '2026-05-08 00:00:00', '2026-05-08 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CFD4D328C9', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-02 00:00:00', '2026-03-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-03-07', '19:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 500000, 500000, '2026-03-02 00:00:00', '2026-03-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-CFD4D328C9-91F4BA', 'MOMOC8F45F3A45', '2026-03-02 04:00:00', NULL, NULL, '2026-03-02 00:00:00', '2026-03-02 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B138FE50F1', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-02-14', '20:00:00', 19, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-02-11 00:00:00', '2026-02-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-B138FE50F1-DFA6D8', 'MOMO3CA1A3D30D', '2026-02-11 03:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-419BDD338D', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-11 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-11 00:00:00', '2026-03-11 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-03-18', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-03-11 00:00:00', '2026-03-11 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-419BDD338D-2409B8', 'MOMO71EE585C36', '2026-03-11 04:00:00', NULL, NULL, '2026-03-11 00:00:00', '2026-03-11 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-323ADC6F0C', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-03 03:00:00', '2026-03-03 18:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-03-05', '11:30:00', 5, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 2, 8, 500000, 1000000, '2026-03-03 00:00:00', '2026-03-03 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-323ADC6F0C-91A905', 'MOMO2C9B9E8B95', '2026-03-03 03:00:00', 1000000, '2026-03-03 18:00:00', '2026-03-03 00:00:00', '2026-03-03 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6BBD4F9D71', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a2c6a3905f886ca644', 'mr-big-beef-bo-nuong-tang-nguyen-hong', 'Mr.Big Beef Bò Nướng Tảng - Nguyên Hồng', '2026-01-22', '12:00:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-01-16 00:00:00', '2026-01-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-6BBD4F9D71-3F2E4B', 'MOMO2E76547A38', '2026-01-16 04:00:00', NULL, NULL, '2026-01-16 00:00:00', '2026-01-16 04:00:00');

-- ==== Lẩu Đức Trọc - Dịch Vọng Hậu (6a4593a5c6a3905f886ca645) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7321F193A4', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-02-16', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-ldt', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-02-14 00:00:00', '2026-02-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-7321F193A4-3DC27B', 'MOMOBBA18B230E', '2026-02-14 02:00:00', NULL, NULL, '2026-02-14 00:00:00', '2026-02-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-797F7D44EB', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-27 00:00:00', '2026-05-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-06-06', '19:30:00', 6, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-ldt', 'Bàn 8 người', 2, 8, 200000, 400000, '2026-05-27 00:00:00', '2026-05-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-797F7D44EB-BEDBF6', 'MOMO479E7988EF', '2026-05-27 02:00:00', NULL, NULL, '2026-05-27 00:00:00', '2026-05-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C5749A934A', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-16 00:00:00', '2026-05-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-05-29', '20:00:00', 25, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50-ldt', 'Phòng riêng 50 khách', 1, 50, 1000000, 1000000, '2026-05-16 00:00:00', '2026-05-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-C5749A934A-C54825', 'MOMOE0B53874A7', '2026-05-16 03:00:00', NULL, NULL, '2026-05-16 00:00:00', '2026-05-16 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A63C4319FC', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-02-22', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-ldt', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-02-09 00:00:00', '2026-02-09 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-A63C4319FC-187F03', 'MOMOF91A7B6C90', '2026-02-09 02:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-49A4BC4B54', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-05-23', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-ldt', 'Bàn 8 người', 1, 8, 200000, 200000, '2026-05-18 00:00:00', '2026-05-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-49A4BC4B54-F4B5B8', 'MOMOE788D48773', '2026-05-18 01:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8C2CBC1CCF', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-04-22', '12:00:00', 49, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50-ldt', 'Phòng riêng 50 khách', 1, 50, 1000000, 1000000, '2026-04-13 00:00:00', '2026-04-13 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-8C2CBC1CCF-671A86', 'MOMOE044BFDC0B', '2026-04-13 04:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-315D5E7BF4', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-02-21', '12:30:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-ldt', 'Bàn thường 4 người', 2, 4, 0, 0, '2026-02-16 00:00:00', '2026-02-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-315D5E7BF4-A95887', 'MOMOADCEAD6275', '2026-02-16 03:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4E0B86CCAE', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-03 11:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca645', 'lau-duc-troc-dich-vong-hau', 'Lẩu Đức Trọc - Dịch Vọng Hậu', '2026-03-06', '18:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-ldt', 'Bàn 8 người', 1, 8, 200000, 200000, '2026-03-03 00:00:00', '2026-03-03 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-4E0B86CCAE-97C633', NULL, NULL, NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 11:00:00');

-- ==== Buffet Nướng Yakimono - GO! Thăng Long (6a4593a5c6a3905f886ca646) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A3960B1A54', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-12 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca646', 'buffet-nuong-yakimono-go-thang-long', 'Buffet Nướng Yakimono - GO! Thăng Long', '2026-03-20', '19:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-03-12 00:00:00', '2026-03-12 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-A3960B1A54-3CFCB1', 'MOMO01AB59B96F', '2026-03-12 04:00:00', NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1F8D4B5C8A', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca646', 'buffet-nuong-yakimono-go-thang-long', 'Buffet Nướng Yakimono - GO! Thăng Long', '2026-02-17', '20:00:00', 20, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-02-16 00:00:00', '2026-02-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-1F8D4B5C8A-57C4F1', 'MOMO1479282118', '2026-02-16 02:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-17E5AFFF03', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-14 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca646', 'buffet-nuong-yakimono-go-thang-long', 'Buffet Nướng Yakimono - GO! Thăng Long', '2026-04-24', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-04-14 00:00:00', '2026-04-14 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-17E5AFFF03-960D46', 'MOMOB5607E6D85', '2026-04-14 03:00:00', NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E3CDA5192A', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-18 00:00:00', '2026-03-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca646', 'buffet-nuong-yakimono-go-thang-long', 'Buffet Nướng Yakimono - GO! Thăng Long', '2026-03-21', '11:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-03-18 00:00:00', '2026-03-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-E3CDA5192A-D5E517', 'MOMO600C613D76', '2026-03-18 03:00:00', NULL, NULL, '2026-03-18 00:00:00', '2026-03-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-49299BC5F6', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a5c6a3905f886ca646', 'buffet-nuong-yakimono-go-thang-long', 'Buffet Nướng Yakimono - GO! Thăng Long', '2026-04-27', '12:00:00', 23, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-04-16 00:00:00', '2026-04-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-49299BC5F6-380D65', 'MOMO8BFDCD1D23', '2026-04-16 03:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 03:00:00');

-- ==== Lẩu Nấm Gia Khánh Mộ Lao (6a4593a6c6a3905f886ca647) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E29D7C9D75', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-07 04:00:00', '2026-03-07 05:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-03-19', '20:00:00', 30, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng tối đa 40 khách', 1, 40, 1500000, 1500000, '2026-03-07 00:00:00', '2026-03-07 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'RB-E29D7C9D75-7072F0', 'MOMOCDC3BF294F', '2026-03-07 04:00:00', 1500000, '2026-03-07 05:00:00', '2026-03-07 00:00:00', '2026-03-07 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8487AE3BEE', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-02-08', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 150000, 150000, '2026-02-07 00:00:00', '2026-02-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-8487AE3BEE-9B6C91', 'MOMO7A496EB992', '2026-02-07 04:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0F2362696E', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-07 00:00:00', '2026-05-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-05-13', '11:30:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn VIP 6 ghế', 1, 6, 300000, 300000, '2026-05-07 00:00:00', '2026-05-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-0F2362696E-09C18C', 'MOMOA0A4778D97', '2026-05-07 02:00:00', NULL, NULL, '2026-05-07 00:00:00', '2026-05-07 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-746E1B7208', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-09 01:00:00', '2026-04-09 04:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-04-16', '12:00:00', 37, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng tối đa 40 khách', 1, 40, 1500000, 1500000, '2026-04-09 00:00:00', '2026-04-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'RB-746E1B7208-9FC0F9', 'MOMOFC0AAAE30D', '2026-04-09 01:00:00', 1500000, '2026-04-09 04:00:00', '2026-04-09 00:00:00', '2026-04-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CEA352DC91', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-02-22', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 150000, 150000, '2026-02-16 00:00:00', '2026-02-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-CEA352DC91-86A60E', 'MOMOADCC09B672', '2026-02-16 04:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5AEFF28641', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-01 00:00:00', '2026-05-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a6c6a3905f886ca647', 'lau-nam-gia-khanh-mo-lao', 'Lẩu Nấm Gia Khánh Mộ Lao', '2026-05-10', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn VIP 6 ghế', 1, 6, 300000, 300000, '2026-05-01 00:00:00', '2026-05-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-5AEFF28641-AB6C8D', 'MOMO27A7FCC8FC', '2026-05-01 02:00:00', NULL, NULL, '2026-05-01 00:00:00', '2026-05-01 02:00:00');

-- ==== Nhà hàng Wang Wang - Hồ Tùng Mậu (6a4593a7c6a3905f886ca648) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-28F17A3996', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-16 00:00:00', '2026-03-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-03-26', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-4', 'Bàn 4 người', 1, 4, 100000, 100000, '2026-03-16 00:00:00', '2026-03-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-28F17A3996-668018', 'MOMO81EEB15844', '2026-03-16 03:00:00', NULL, NULL, '2026-03-16 00:00:00', '2026-03-16 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8A24A26F64', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-31 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-31 00:00:00', '2025-12-31 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-01-10', '11:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-8', 'Bàn nhóm 8 người', 1, 8, 300000, 300000, '2025-12-31 00:00:00', '2025-12-31 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-8A24A26F64-D3F4E1', 'MOMOEDFA852CB4', '2025-12-31 01:00:00', NULL, NULL, '2025-12-31 00:00:00', '2025-12-31 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-08E037329B', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-01-10', '12:00:00', 10, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-20', 'Khu đoàn 20 người', 1, 20, 500000, 500000, '2026-01-09 00:00:00', '2026-01-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-08E037329B-0949C2', 'MOMO3491361B0F', '2026-01-09 04:00:00', NULL, NULL, '2026-01-09 00:00:00', '2026-01-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2AED38E4AF', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-15 11:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-04-20', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-4', 'Bàn 4 người', 1, 4, 100000, 100000, '2026-04-15 00:00:00', '2026-04-15 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 100000, 'VND', 'MOMO', 'RB-2AED38E4AF-DFF9D6', NULL, NULL, NULL, NULL, '2026-04-15 00:00:00', '2026-04-15 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E3DFFD1575', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-12 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-12 00:00:00', '2026-02-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-02-20', '18:00:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-8', 'Bàn nhóm 8 người', 2, 8, 300000, 600000, '2026-02-12 00:00:00', '2026-02-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-E3DFFD1575-F88535', 'MOMOEE4E379029', '2026-02-12 02:00:00', NULL, NULL, '2026-02-12 00:00:00', '2026-02-12 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6BB39A89E9', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-06 00:00:00', '2026-05-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-05-08', '18:30:00', 18, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-20', 'Khu đoàn 20 người', 1, 20, 500000, 500000, '2026-05-06 00:00:00', '2026-05-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-6BB39A89E9-043F67', 'MOMOF230A39E4E', '2026-05-06 03:00:00', NULL, NULL, '2026-05-06 00:00:00', '2026-05-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-414FB7494A', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-31 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-31 00:00:00', '2026-05-31 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a7c6a3905f886ca648', 'nha-hang-wang-wang-ho-tung-mau', 'Nhà hàng Wang Wang - Hồ Tùng Mậu', '2026-06-03', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tt-4', 'Bàn 4 người', 1, 4, 100000, 100000, '2026-05-31 00:00:00', '2026-05-31 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-414FB7494A-4D38A7', 'MOMO7B4B222D2B', '2026-05-31 01:00:00', NULL, NULL, '2026-05-31 00:00:00', '2026-05-31 01:00:00');

-- ==== Làng Niêu & Nướng Trần Văn Lai (6a4593a8c6a3905f886ca649) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4BB47E8F2C', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-05-06', '11:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 400000, 400000, '2026-05-02 00:00:00', '2026-05-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-4BB47E8F2C-97E74B', 'MOMO243C3C2FB0', '2026-05-02 01:00:00', NULL, NULL, '2026-05-02 00:00:00', '2026-05-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7B09E1E6B8', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-02-13', '12:00:00', 33, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 1, 50, 2000000, 2000000, '2026-02-02 00:00:00', '2026-02-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-7B09E1E6B8-499438', 'MOMOA483E2DCEC', '2026-02-02 01:00:00', NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-93C21C99D0', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-14 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-14 00:00:00', '2026-03-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-03-22', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-03-14 00:00:00', '2026-03-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-93C21C99D0-A8AA06', 'MOMO12F1AB326E', '2026-03-14 04:00:00', NULL, NULL, '2026-03-14 00:00:00', '2026-03-14 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D7ECD74A62', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-02-14', '18:00:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 1, 8, 400000, 400000, '2026-02-11 00:00:00', '2026-02-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-D7ECD74A62-06D17D', 'MOMO4952FC2304', '2026-02-11 03:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-23F96E95EF', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-22 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-22 00:00:00', '2026-05-22 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-05-30', '18:30:00', 34, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 1, 50, 2000000, 2000000, '2026-05-22 00:00:00', '2026-05-22 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-23F96E95EF-572BC4', 'MOMOD1B633BFC2', '2026-05-22 02:00:00', NULL, NULL, '2026-05-22 00:00:00', '2026-05-22 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F6927A5BB8', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-03-27', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-F6927A5BB8-C4823E', 'MOMOB4C2A832B8', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BE3A41045A', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-05-25', '19:30:00', 7, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 người', 2, 8, 400000, 800000, '2026-05-20 00:00:00', '2026-05-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-BE3A41045A-61474E', 'MOMO93575C9AE9', '2026-05-20 03:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D267B6062D', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 4000000, 4000000, 4000000, 4000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-02 03:00:00', '2026-01-02 15:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 15:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a8c6a3905f886ca649', 'lang-nieu-nuong-tran-van-lai', 'Làng Niêu & Nướng Trần Văn Lai', '2026-01-13', '20:00:00', 30, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-50', 'Phòng riêng 50 khách', 2, 50, 2000000, 4000000, '2026-01-02 00:00:00', '2026-01-02 15:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 4000000, 'VND', 'MOMO', 'RB-D267B6062D-35BE34', 'MOMO02C1E7B9E6', '2026-01-02 03:00:00', 4000000, '2026-01-02 15:00:00', '2026-01-02 00:00:00', '2026-01-02 15:00:00');

-- ==== Lẩu Nấm Gia Khánh - Đền Lừ (6a4593a9c6a3905f886ca64a) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1336BB0681', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a9c6a3905f886ca64a', 'lau-nam-gia-khanh-den-lu', 'Lẩu Nấm Gia Khánh - Đền Lừ', '2026-02-13', '12:00:00', 36, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30-gk', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-02-07 00:00:00', '2026-02-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-1336BB0681-27E57A', 'MOMO681D07588E', '2026-02-07 04:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A3A352AB11', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-15 17:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-15 00:00:00', '2026-03-15 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a9c6a3905f886ca64a', 'lau-nam-gia-khanh-den-lu', 'Lẩu Nấm Gia Khánh - Đền Lừ', '2026-03-23', '12:30:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-gk', 'Bàn 4 người', 2, 4, 0, 0, '2026-03-15 00:00:00', '2026-03-15 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-A3A352AB11-1109B8', NULL, NULL, NULL, NULL, '2026-03-15 00:00:00', '2026-03-15 17:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E029915442', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-07 00:00:00', '2026-05-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a9c6a3905f886ca64a', 'lau-nam-gia-khanh-den-lu', 'Lẩu Nấm Gia Khánh - Đền Lừ', '2026-05-12', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-gk', 'Bàn 8 người', 1, 8, 200000, 200000, '2026-05-07 00:00:00', '2026-05-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-E029915442-C69733', 'MOMOF46EAD5F7E', '2026-05-07 02:00:00', NULL, NULL, '2026-05-07 00:00:00', '2026-05-07 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9858FA1595', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a9c6a3905f886ca64a', 'lau-nam-gia-khanh-den-lu', 'Lẩu Nấm Gia Khánh - Đền Lừ', '2026-04-19', '18:30:00', 39, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30-gk', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-04-16 00:00:00', '2026-04-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-9858FA1595-581A4B', 'MOMO3277537ABA', '2026-04-16 03:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-83FF643413', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-21 16:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-21 00:00:00', '2026-02-21 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593a9c6a3905f886ca64a', 'lau-nam-gia-khanh-den-lu', 'Lẩu Nấm Gia Khánh - Đền Lừ', '2026-02-28', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-gk', 'Bàn 4 người', 1, 4, 0, 0, '2026-02-21 00:00:00', '2026-02-21 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-83FF643413-FB3709', NULL, NULL, NULL, NULL, '2026-02-21 00:00:00', '2026-02-21 16:00:00');

-- ==== Hi-Food BBQ - Vinhomes Smart City (6a4593aac6a3905f886ca64b) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8645AE3B45', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-06 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-06 00:00:00', '2026-05-06 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-05-19', '12:30:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-lawn', 'Bàn 4 trên sân cỏ', 2, 4, 100000, 200000, '2026-05-06 00:00:00', '2026-05-06 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-8645AE3B45-CB3D60', 'MOMOEA577DDE55', '2026-05-06 01:00:00', NULL, NULL, '2026-05-06 00:00:00', '2026-05-06 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AE7B60077A', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-04-22', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-lake', 'Bàn 8 view hồ', 1, 8, 200000, 200000, '2026-04-20 00:00:00', '2026-04-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-AE7B60077A-C3D884', 'MOMOB7FCDA22F5', '2026-04-20 01:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38235DA3BD', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-05-01', '18:30:00', 13, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-20-event', 'Khu vực sự kiện 20+ khách', 1, 20, 500000, 500000, '2026-04-20 00:00:00', '2026-04-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-38235DA3BD-E89229', 'MOMOB63C4893AE', '2026-04-20 02:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3ACAF5C92A', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-24 00:00:00', '2026-03-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-04-06', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-lawn', 'Bàn 4 trên sân cỏ', 1, 4, 100000, 100000, '2026-03-24 00:00:00', '2026-03-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-3ACAF5C92A-7CD2AD', 'MOMO7B4C45748E', '2026-03-24 01:00:00', NULL, NULL, '2026-03-24 00:00:00', '2026-03-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8C73F76A42', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-03-06', '19:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-lake', 'Bàn 8 view hồ', 1, 8, 200000, 200000, '2026-02-28 00:00:00', '2026-02-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-8C73F76A42-883756', 'MOMOBD1D1AF59A', '2026-02-28 01:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0F1FDF0BDC', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-23 00:00:00', '2026-04-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64b', 'hi-food-bbq-vinhomes-smart-city', 'Hi-Food BBQ - Vinhomes Smart City', '2026-05-01', '20:00:00', 33, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-20-event', 'Khu vực sự kiện 20+ khách', 1, 20, 500000, 500000, '2026-04-23 00:00:00', '2026-04-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-0F1FDF0BDC-841F1E', 'MOMO777683B6AB', '2026-04-23 01:00:00', NULL, NULL, '2026-04-23 00:00:00', '2026-04-23 01:00:00');

-- ==== Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu (6a4593aac6a3905f886ca64c) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8445A22296', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-26 00:00:00', '2026-03-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-03-31', '18:00:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 người', 1, 8, 400000, 400000, '2026-03-26 00:00:00', '2026-03-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-8445A22296-603592', 'MOMO9258B29AC0', '2026-03-26 02:00:00', NULL, NULL, '2026-03-26 00:00:00', '2026-03-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C0993E92CB', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 700000, 700000, 700000, 700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-03-09', '18:30:00', 8, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-10', 'Bàn VIP 10 người', 1, 10, 700000, 700000, '2026-02-25 00:00:00', '2026-02-25 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 700000, 'VND', 'MOMO', 'RB-C0993E92CB-58AB25', 'MOMOBDF5C0428A', '2026-02-25 02:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F40F8C7579', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-08 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-08 00:00:00', '2026-05-08 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-05-10', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn tiêu chuẩn 4 người', 1, 4, 200000, 200000, '2026-05-08 00:00:00', '2026-05-08 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F40F8C7579-E010B4', 'MOMO372FD7F3F5', '2026-05-08 04:00:00', NULL, NULL, '2026-05-08 00:00:00', '2026-05-08 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8FF086D205', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-24 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-24 00:00:00', '2026-05-24 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-05-30', '19:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 người', 1, 8, 400000, 400000, '2026-05-24 00:00:00', '2026-05-24 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-8FF086D205-EE6368', 'MOMO711E680887', '2026-05-24 04:00:00', NULL, NULL, '2026-05-24 00:00:00', '2026-05-24 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AD2E85FAA6', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1400000, 1400000, 1400000, 1400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-22 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-22 00:00:00', '2026-05-22 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-05-28', '20:00:00', 8, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-10', 'Bàn VIP 10 người', 2, 10, 700000, 1400000, '2026-05-22 00:00:00', '2026-05-22 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1400000, 'VND', 'MOMO', 'RB-AD2E85FAA6-F04255', 'MOMOC086F4C4BE', '2026-05-22 02:00:00', NULL, NULL, '2026-05-22 00:00:00', '2026-05-22 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F245AC7AC0', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-21 00:00:00', '2026-02-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-02-26', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn tiêu chuẩn 4 người', 1, 4, 200000, 200000, '2026-02-21 00:00:00', '2026-02-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F245AC7AC0-15B147', 'MOMOD30AF8BC4F', '2026-02-21 01:00:00', NULL, NULL, '2026-02-21 00:00:00', '2026-02-21 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7AD9440379', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aac6a3905f886ca64c', 'vica-hotpot-ho-tung-mau', 'Buffet Lẩu Vica Hotpot - Hồ Tùng Mậu', '2026-05-11', '11:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 người', 1, 8, 400000, 400000, '2026-05-10 00:00:00', '2026-05-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-7AD9440379-88C6B6', 'MOMOC846419CAE', '2026-05-10 01:00:00', NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 01:00:00');

-- ==== Lẩu Nướng GangBuk - Trường Chinh (6a4593acc6a3905f886ca64d) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7E746639AE', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-03-30', '18:30:00', 33, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40-private', 'Phòng riêng 40 khách', 2, 40, 1500000, 3000000, '2026-03-28 00:00:00', '2026-03-28 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-7E746639AE-83B202', 'MOMO92505BED24', '2026-03-28 04:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E49A9F06FD', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-04-18', '19:00:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 ghế tiêu chuẩn', 2, 4, 150000, 300000, '2026-04-09 00:00:00', '2026-04-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-E49A9F06FD-D3BB32', 'MOMO765EE80DE5', '2026-04-09 04:00:00', NULL, NULL, '2026-04-09 00:00:00', '2026-04-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-86604E3F4F', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-05-13', '19:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-05-02 00:00:00', '2026-05-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-86604E3F4F-0944C4', 'MOMO405BFF9565', '2026-05-02 01:00:00', NULL, NULL, '2026-05-02 00:00:00', '2026-05-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3E122778C4', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-01 00:00:00', '2026-04-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-04-05', '20:00:00', 15, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40-private', 'Phòng riêng 40 khách', 1, 40, 1500000, 1500000, '2026-04-01 00:00:00', '2026-04-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-3E122778C4-A8D53D', 'MOMOBF1F89DFA8', '2026-04-01 02:00:00', NULL, NULL, '2026-04-01 00:00:00', '2026-04-01 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0702F2BD97', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-31 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-31 00:00:00', '2025-12-31 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-01-09', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 ghế tiêu chuẩn', 1, 4, 150000, 150000, '2025-12-31 00:00:00', '2025-12-31 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-0702F2BD97-A5506A', 'MOMO3A3F63D91F', '2025-12-31 04:00:00', NULL, NULL, '2025-12-31 00:00:00', '2025-12-31 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7B4020EC52', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-02-28', '11:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 300000, 300000, '2026-02-23 00:00:00', '2026-02-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-7B4020EC52-27B52E', 'MOMOCFDF83EDF9', '2026-02-23 01:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-131AADBFBE', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-11 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-11 00:00:00', '2026-05-11 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-05-23', '12:00:00', 32, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40-private', 'Phòng riêng 40 khách', 2, 40, 1500000, 3000000, '2026-05-11 00:00:00', '2026-05-11 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-131AADBFBE-55F04D', 'MOMO3293A55D53', '2026-05-11 04:00:00', NULL, NULL, '2026-05-11 00:00:00', '2026-05-11 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-36A66CDC70', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593acc6a3905f886ca64d', 'lau-nuong-gangbuk-truong-chinh', 'Lẩu Nướng GangBuk - Trường Chinh', '2026-03-31', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 ghế tiêu chuẩn', 1, 4, 150000, 150000, '2026-03-30 00:00:00', '2026-03-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-36A66CDC70-09F49F', 'MOMO22967C0A50', '2026-03-30 04:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 04:00:00');

-- ==== Vườn Nướng BBQ - Vinhomes Smart City (6a4593adc6a3905f886ca64e) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8B00D0D148', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-09 00:00:00', '2026-03-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593adc6a3905f886ca64e', 'vuon-nuong-bbq-vinhomes-smart-city', 'Vườn Nướng BBQ - Vinhomes Smart City', '2026-03-17', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn 4 ngoài trời', 1, 4, 150000, 150000, '2026-03-09 00:00:00', '2026-03-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-8B00D0D148-D9618E', 'MOMODB38854209', '2026-03-09 04:00:00', NULL, NULL, '2026-03-09 00:00:00', '2026-03-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-54F680BAAE', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-18 04:00:00', '2026-04-18 12:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-18 00:00:00', '2026-04-18 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593adc6a3905f886ca64e', 'vuon-nuong-bbq-vinhomes-smart-city', 'Vườn Nướng BBQ - Vinhomes Smart City', '2026-04-26', '19:30:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-garden', 'Bàn 8 trong vườn', 1, 8, 300000, 300000, '2026-04-18 00:00:00', '2026-04-18 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-54F680BAAE-4ED307', 'MOMOC8C8D17822', '2026-04-18 04:00:00', 300000, '2026-04-18 12:00:00', '2026-04-18 00:00:00', '2026-04-18 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A0172ECA36', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-01 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593adc6a3905f886ca64e', 'vuon-nuong-bbq-vinhomes-smart-city', 'Vườn Nướng BBQ - Vinhomes Smart City', '2026-02-10', '20:00:00', 18, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 1000000, 1000000, '2026-02-01 00:00:00', '2026-02-01 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-A0172ECA36-0729D9', 'MOMO161E001042', '2026-02-01 03:00:00', NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7508237288', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-03 00:00:00', '2026-06-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593adc6a3905f886ca64e', 'vuon-nuong-bbq-vinhomes-smart-city', 'Vườn Nướng BBQ - Vinhomes Smart City', '2026-06-07', '11:00:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn 4 ngoài trời', 2, 4, 150000, 300000, '2026-06-03 00:00:00', '2026-06-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-7508237288-2A406C', 'MOMOBC7CF3690A', '2026-06-03 01:00:00', NULL, NULL, '2026-06-03 00:00:00', '2026-06-03 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A332F2557F', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-22 03:00:00', '2026-04-22 09:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-22 00:00:00', '2026-04-22 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593adc6a3905f886ca64e', 'vuon-nuong-bbq-vinhomes-smart-city', 'Vườn Nướng BBQ - Vinhomes Smart City', '2026-04-29', '11:30:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-garden', 'Bàn 8 trong vườn', 2, 8, 300000, 600000, '2026-04-22 00:00:00', '2026-04-22 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 600000, 'VND', 'MOMO', 'RB-A332F2557F-35340D', 'MOMO96A7EC3439', '2026-04-22 03:00:00', 600000, '2026-04-22 09:00:00', '2026-04-22 00:00:00', '2026-04-22 09:00:00');

-- ==== Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn (6a4593aec6a3905f886ca64f) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-949E2410C3', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-04-24', '19:30:00', 4, 75, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 0, 0, '2026-04-15 00:00:00', '2026-04-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-949E2410C3-B1C694', 'MOMO79382498E6', '2026-04-15 04:00:00', NULL, NULL, '2026-04-15 00:00:00', '2026-04-15 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-85F64F4D90', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-01-19', '20:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 1, 8, 0, 0, '2026-01-12 00:00:00', '2026-01-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-85F64F4D90-C4E7E8', 'MOMOF9F7770AFD', '2026-01-12 01:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B8C573D32D', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-03-09', '11:00:00', 1, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 0, 0, '2026-03-05 00:00:00', '2026-03-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-B8C573D32D-0D297C', 'MOMO712D02C3CA', '2026-03-05 01:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F8D9A718B5', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-04-19', '11:30:00', 3, 75, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 0, 0, '2026-04-16 00:00:00', '2026-04-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-F8D9A718B5-99C355', 'MOMO33740C1ACC', '2026-04-16 02:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B0A3DF40FD', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-29 19:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-29 00:00:00', '2026-04-29 19:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-05-04', '12:00:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8', 'Bàn 8', 1, 8, 0, 0, '2026-04-29 00:00:00', '2026-04-29 19:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-B0A3DF40FD-63EE12', NULL, NULL, NULL, NULL, '2026-04-29 00:00:00', '2026-04-29 19:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-348C240E3D', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-07 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-07 00:00:00', '2026-04-07 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a4593aec6a3905f886ca64f', 'nem-nuong-xuan-dan-phu-doan', 'Nhà hàng Nem Nướng Xuân Dần - Phủ Doãn', '2026-04-13', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 0, 0, '2026-04-07 00:00:00', '2026-04-07 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-348C240E3D-A7F3E8', 'MOMOB14E07998D', '2026-04-07 02:00:00', NULL, NULL, '2026-04-07 00:00:00', '2026-04-07 02:00:00');
