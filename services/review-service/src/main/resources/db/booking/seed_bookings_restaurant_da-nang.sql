-- Seed demo bookings RESTAURANT cho khu vuc: da-nang
-- 5-8 booking/restaurant, ngay dat ban rai trong 1-6 thang qua, thanh toan (dat coc) qua MOMO_WALLET
-- payOption luon DEPOSIT (dung flow that: totalAmount == depositAmount == amountPayable)
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Queen Palace Karaoke - Đà Nẵng (6a47ac1693a0c40dc01724de) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-47C41B5395', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-04-26', '20:00:00', 17, 240, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-vip-30', 'Phòng VIP (12-30 người)', 1, 30, 1000000, 1000000, '2026-04-16 00:00:00', '2026-04-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-47C41B5395-833498', 'MOMOE14C9A685C', '2026-04-16 04:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-313E1C5EF1', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-12 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-03-18', '11:00:00', 5, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-mini-6', 'Phòng Mini (2-6 người)', 1, 6, 200000, 200000, '2026-03-12 00:00:00', '2026-03-12 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-313E1C5EF1-8839F7', 'MOMO8D27BA772C', '2026-03-12 04:00:00', NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1123C5332D', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-20 00:00:00', '2026-01-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-01-31', '11:30:00', 12, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-std-12', 'Phòng Standard (6-12 người)', 2, 12, 400000, 800000, '2026-01-20 00:00:00', '2026-01-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-1123C5332D-D05205', 'MOMO4C35A0E0CF', '2026-01-20 01:00:00', NULL, NULL, '2026-01-20 00:00:00', '2026-01-20 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-654DDF280A', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-26 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-26 00:00:00', '2026-05-26 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-06-07', '12:00:00', 16, 240, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-vip-30', 'Phòng VIP (12-30 người)', 1, 30, 1000000, 1000000, '2026-05-26 00:00:00', '2026-05-26 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-654DDF280A-0B2B79', 'MOMO28C30469EE', '2026-05-26 04:00:00', NULL, NULL, '2026-05-26 00:00:00', '2026-05-26 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6B71790352', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-27 00:00:00', '2026-02-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-03-04', '12:30:00', 2, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-mini-6', 'Phòng Mini (2-6 người)', 1, 6, 200000, 200000, '2026-02-27 00:00:00', '2026-02-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-6B71790352-9A2AD2', 'MOMOE35BB8C9AB', '2026-02-27 02:00:00', NULL, NULL, '2026-02-27 00:00:00', '2026-02-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D572C56088', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 400000, 400000, 400000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-12 06:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 06:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-03-19', '18:00:00', 9, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-std-12', 'Phòng Standard (6-12 người)', 1, 12, 400000, 400000, '2026-03-12 00:00:00', '2026-03-12 06:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 400000, 'VND', 'MOMO', 'RB-D572C56088-E8374E', NULL, NULL, NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 06:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-374B65BECE', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-16 04:00:00', '2026-04-16 02:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1693a0c40dc01724de', 'queen-palace-karaoke-da-nang', 'Queen Palace Karaoke - Đà Nẵng', '2026-04-26', '18:30:00', 20, 240, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'kr-vip-30', 'Phòng VIP (12-30 người)', 1, 30, 1000000, 1000000, '2026-04-16 00:00:00', '2026-04-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-374B65BECE-59E8DA', 'MOMO467F11465B', '2026-04-16 04:00:00', 1000000, '2026-04-16 02:00:00', '2026-04-16 00:00:00', '2026-04-16 02:00:00');

-- ==== Butcher Steak & BBQ Lê Quang Đạo (6a47ac1893a0c40dc01724df) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38FD5BE077', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-01-29', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor-steak', 'Bàn đôi trong nhà — phong cách steakhouse', 1, 2, 200000, 200000, '2026-01-17 00:00:00', '2026-01-17 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-38FD5BE077-BE290E', 'MOMO2E2D53472A', '2026-01-17 01:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1A92EAA2C8', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-25 01:00:00', '2026-04-25 04:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-25 00:00:00', '2026-04-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-05-03', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-group', 'Bàn nhóm 6 người', 1, 6, 300000, 300000, '2026-04-25 00:00:00', '2026-04-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-1A92EAA2C8-2B3970', 'MOMOE84E1796A5', '2026-04-25 01:00:00', 300000, '2026-04-25 04:00:00', '2026-04-25 00:00:00', '2026-04-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AD54624D1B', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-08 01:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-02-12', '12:00:00', 32, 150, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-private-steak', 'Phòng riêng VIP (tối đa 40 khách)', 2, 40, 500000, 1000000, '2026-02-08 00:00:00', '2026-02-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-AD54624D1B-C543F7', NULL, NULL, NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CC8F1984D7', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-01-16', '12:30:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor-steak', 'Bàn đôi trong nhà — phong cách steakhouse', 2, 2, 200000, 400000, '2026-01-12 00:00:00', '2026-01-12 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-CC8F1984D7-557CFE', 'MOMO2DF4C6B9DA', '2026-01-12 04:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0DE7657266', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-05 00:00:00', '2026-04-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-04-07', '18:00:00', 6, 120, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-group', 'Bàn nhóm 6 người', 2, 6, 300000, 600000, '2026-04-05 00:00:00', '2026-04-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-0DE7657266-871139', 'MOMOA8D6265C98', '2026-04-05 01:00:00', NULL, NULL, '2026-04-05 00:00:00', '2026-04-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D067B73102', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-05-29', '18:30:00', 16, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-private-steak', 'Phòng riêng VIP (tối đa 40 khách)', 1, 40, 500000, 500000, '2026-05-18 00:00:00', '2026-05-18 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-D067B73102-B2A8EC', 'MOMO0196E98CF3', '2026-05-18 04:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9724BF6787', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-01 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-01 00:00:00', '2026-06-01 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-06-04', '19:00:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor-steak', 'Bàn đôi trong nhà — phong cách steakhouse', 2, 2, 200000, 400000, '2026-06-01 00:00:00', '2026-06-01 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-9724BF6787-803340', 'MOMOD41CA07016', '2026-06-01 01:00:00', NULL, NULL, '2026-06-01 00:00:00', '2026-06-01 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-51F20B3544', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1893a0c40dc01724df', 'butcher-steak-bbq-le-quang-dao-da-nang', 'Butcher Steak & BBQ Lê Quang Đạo', '2026-01-15', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-group', 'Bàn nhóm 6 người', 1, 6, 300000, 300000, '2026-01-12 00:00:00', '2026-01-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-51F20B3544-7740B4', 'MOMO667ABAFCFC', '2026-01-12 02:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 02:00:00');

-- ==== Bistecca Đà Nẵng (6a47ac1993a0c40dc01724e0) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4D2F8D5EA2', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-15 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-15 00:00:00', '2026-02-15 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1993a0c40dc01724e0', 'bistecca-da-nang', 'Bistecca Đà Nẵng', '2026-02-22', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách', 1, 4, 300000, 300000, '2026-02-15 00:00:00', '2026-02-15 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-4D2F8D5EA2-27DDBB', 'MOMO00803955E4', '2026-02-15 01:00:00', NULL, NULL, '2026-02-15 00:00:00', '2026-02-15 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8B632A385F', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-21 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-21 00:00:00', '2026-05-21 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1993a0c40dc01724e0', 'bistecca-da-nang', 'Bistecca Đà Nẵng', '2026-05-27', '12:00:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-outdoor-view', 'Bàn ngoài trời view sông Hàn', 1, 8, 500000, 500000, '2026-05-21 00:00:00', '2026-05-21 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-8B632A385F-86C6B1', 'MOMO6357845ED4', '2026-05-21 04:00:00', NULL, NULL, '2026-05-21 00:00:00', '2026-05-21 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-449226FEBA', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-29 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-29 00:00:00', '2026-03-29 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1993a0c40dc01724e0', 'bistecca-da-nang', 'Bistecca Đà Nẵng', '2026-04-11', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-standard', 'Bàn đôi trong nhà', 1, 2, 200000, 200000, '2026-03-29 00:00:00', '2026-03-29 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-449226FEBA-BA2C65', 'MOMO008D87276C', '2026-03-29 01:00:00', NULL, NULL, '2026-03-29 00:00:00', '2026-03-29 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FB38AA0694', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1993a0c40dc01724e0', 'bistecca-da-nang', 'Bistecca Đà Nẵng', '2026-03-10', '18:00:00', 2, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách', 1, 4, 300000, 300000, '2026-03-08 00:00:00', '2026-03-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-FB38AA0694-88C70B', 'MOMOE7657B2AE6', '2026-03-08 03:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AB99B00B1C', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-24 09:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-24 00:00:00', '2026-02-24 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1993a0c40dc01724e0', 'bistecca-da-nang', 'Bistecca Đà Nẵng', '2026-03-06', '18:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-outdoor-view', 'Bàn ngoài trời view sông Hàn', 1, 8, 500000, 500000, '2026-02-24 00:00:00', '2026-02-24 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-AB99B00B1C-D382E5', NULL, NULL, NULL, NULL, '2026-02-24 00:00:00', '2026-02-24 09:00:00');

-- ==== Phố Biển - Nguyễn Tất Thành (6a47ac1b93a0c40dc01724e1) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9A24646EBD', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-04-27', '12:00:00', 30, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng tiệc riêng 40 khách', 1, 40, 1000000, 1000000, '2026-04-17 00:00:00', '2026-04-17 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-9A24646EBD-893682', 'MOMO64FC285F96', '2026-04-17 02:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-80E3C5C624', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-02-20', '12:30:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 2, 4, 0, 0, '2026-02-13 00:00:00', '2026-02-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-80E3C5C624-0A7292', 'MOMO1AA442DB0B', '2026-02-13 03:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F6EBDC0F65', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-26 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-26 00:00:00', '2026-03-26 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-04-06', '18:00:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 ghế', 1, 8, 200000, 200000, '2026-03-26 00:00:00', '2026-03-26 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F6EBDC0F65-7D385D', 'MOMO5D3B0A32C4', '2026-03-26 04:00:00', NULL, NULL, '2026-03-26 00:00:00', '2026-03-26 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-068509C61D', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-11 00:00:00', '2026-01-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-01-17', '18:30:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng tiệc riêng 40 khách', 1, 40, 1000000, 1000000, '2026-01-11 00:00:00', '2026-01-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-068509C61D-7AD0EC', 'MOMO6B1977A327', '2026-01-11 01:00:00', NULL, NULL, '2026-01-11 00:00:00', '2026-01-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E8D376089B', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-05 12:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-03-11', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-03-05 00:00:00', '2026-03-05 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-E8D376089B-5E5854', NULL, NULL, NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D8AE8F51EB', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac1b93a0c40dc01724e1', 'nha-hang-pho-bien-nguyen-tat-thanh', 'Phố Biển - Nguyễn Tất Thành', '2026-02-16', '19:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 ghế', 1, 8, 200000, 200000, '2026-02-08 00:00:00', '2026-02-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-D8AE8F51EB-08133A', 'MOMO5D08D8C0B2', '2026-02-08 02:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 02:00:00');

-- ==== Buffet Hải Sản Mr Mộc Đà Nẵng (6a47ac2093a0c40dc01724e2) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-344B8CE3BE', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-01-14', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-standard', 'Bàn tiêu chuẩn 6 người', 1, 6, 200000, 200000, '2026-01-03 00:00:00', '2026-01-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-344B8CE3BE-6CC979', 'MOMO77FF5680B7', '2026-01-03 01:00:00', NULL, NULL, '2026-01-03 00:00:00', '2026-01-03 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-81E055F4A3', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-01 02:00:00', '2026-04-01 18:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-01 00:00:00', '2026-04-01 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-04-03', '18:00:00', 9, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-group', 'Bàn nhóm lớn 10 người', 1, 10, 300000, 300000, '2026-04-01 00:00:00', '2026-04-01 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-81E055F4A3-1298BA', 'MOMO6A11EA2B7B', '2026-04-01 02:00:00', 300000, '2026-04-01 18:00:00', '2026-04-01 00:00:00', '2026-04-01 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E6D69056B8', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-06-03', '18:30:00', 49, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-private', 'Phòng riêng VIP (tối đa 50 khách)', 1, 50, 1000000, 1000000, '2026-05-25 00:00:00', '2026-05-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-E6D69056B8-69AC6C', 'MOMO66F246A653', '2026-05-25 01:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A7F106F28E', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-07 03:00:00', '2026-03-07 04:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-07 00:00:00', '2026-03-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-03-14', '19:00:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-standard', 'Bàn tiêu chuẩn 6 người', 2, 6, 200000, 400000, '2026-03-07 00:00:00', '2026-03-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 400000, 'VND', 'MOMO', 'RB-A7F106F28E-99A03F', 'MOMOB07410C2CD', '2026-03-07 03:00:00', 400000, '2026-03-07 04:00:00', '2026-03-07 00:00:00', '2026-03-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-76F10710DA', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-02-11', '19:30:00', 10, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-group', 'Bàn nhóm lớn 10 người', 1, 10, 300000, 300000, '2026-02-08 00:00:00', '2026-02-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-76F10710DA-073DC2', 'MOMO780517D21A', '2026-02-08 01:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-FB159331CC', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-30 00:00:00', '2026-04-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-05-12', '20:00:00', 30, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-private', 'Phòng riêng VIP (tối đa 50 khách)', 1, 50, 1000000, 1000000, '2026-04-30 00:00:00', '2026-04-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-FB159331CC-001EFB', 'MOMO3E1E4C1B90', '2026-04-30 04:00:00', NULL, NULL, '2026-04-30 00:00:00', '2026-04-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1F6CE8F71F', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-02 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2093a0c40dc01724e2', 'buffet-hai-san-mr-moc-da-nang', 'Buffet Hải Sản Mr Mộc Đà Nẵng', '2026-01-12', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-standard', 'Bàn tiêu chuẩn 6 người', 1, 6, 200000, 200000, '2026-01-02 00:00:00', '2026-01-02 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-1F6CE8F71F-385C2F', 'MOMO24967189D6', '2026-01-02 03:00:00', NULL, NULL, '2026-01-02 00:00:00', '2026-01-02 03:00:00');

-- ==== Nhà hàng Gang Yu Hotpot Đà Nẵng (6a47ac2393a0c40dc01724e3) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0DCFC8D227', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-29 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-02-11', '18:00:00', 8, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-tb-8-vip', 'Bàn VIP 8 người tầng 2', 1, 8, 300000, 300000, '2026-01-29 00:00:00', '2026-01-29 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-0DCFC8D227-83A236', 'MOMOF47129750A', '2026-01-29 02:00:00', NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-409B9DD64D', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-03-06', '18:30:00', 24, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-pr-30', 'Phòng riêng 30 khách tầng 3', 1, 30, 1500000, 1500000, '2026-02-25 00:00:00', '2026-02-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-409B9DD64D-FD0413', 'MOMO792804AAD4', '2026-02-25 01:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-99499A6DC7', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-07 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-07 00:00:00', '2026-01-07 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-01-16', '19:00:00', 5, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-tb-4', 'Bàn buffet 4 người', 2, 4, 100000, 200000, '2026-01-07 00:00:00', '2026-01-07 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-99499A6DC7-D457CC', 'MOMO281B0C64CC', '2026-01-07 03:00:00', NULL, NULL, '2026-01-07 00:00:00', '2026-01-07 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-69A5A1D183', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-03-28', '19:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-tb-8-vip', 'Bàn VIP 8 người tầng 2', 1, 8, 300000, 300000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-69A5A1D183-CBA75B', 'MOMOB4AD5C716A', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B413F54A23', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-19 00:00:00', '2026-01-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-01-30', '20:00:00', 10, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-pr-30', 'Phòng riêng 30 khách tầng 3', 1, 30, 1500000, 1500000, '2026-01-19 00:00:00', '2026-01-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-B413F54A23-253C41', 'MOMO2A5EC7C843', '2026-01-19 02:00:00', NULL, NULL, '2026-01-19 00:00:00', '2026-01-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F0363F2EB3', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-02-03', '11:00:00', 2, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-tb-4', 'Bàn buffet 4 người', 2, 4, 100000, 200000, '2026-02-01 00:00:00', '2026-02-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F0363F2EB3-5C0935', 'MOMO2AD61C9966', '2026-02-01 02:00:00', NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BF7B57E025', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-02-14', '11:30:00', 8, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-tb-8-vip', 'Bàn VIP 8 người tầng 2', 1, 8, 300000, 300000, '2026-02-07 00:00:00', '2026-02-07 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-BF7B57E025-6D75CF', 'MOMO7C545319B6', '2026-02-07 01:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F417FD6F0D', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2393a0c40dc01724e3', 'gang-yu-hotpot-yen-bai-da-nang', 'Nhà hàng Gang Yu Hotpot Đà Nẵng', '2026-02-08', '12:00:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'gy-pr-30', 'Phòng riêng 30 khách tầng 3', 1, 30, 1500000, 1500000, '2026-02-07 00:00:00', '2026-02-07 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-F417FD6F0D-ACC14D', 'MOMO8AF4F5E0AA', '2026-02-07 01:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 01:00:00');

-- ==== Butcher Grilled Hoàng Kế Viêm (6a47ac2693a0c40dc01724e4) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CF55509842', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2693a0c40dc01724e4', 'butcher-grilled-hoang-ke-viem-da-nang', 'Butcher Grilled Hoàng Kế Viêm', '2026-04-12', '18:30:00', 28, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-private-room', 'Phòng riêng VIP (tối đa 30 khách)', 1, 30, 300000, 300000, '2026-04-06 00:00:00', '2026-04-06 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-CF55509842-28D5C7', 'MOMO231BECFDAC', '2026-04-06 02:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7419A535F7', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2693a0c40dc01724e4', 'butcher-grilled-hoang-ke-viem-da-nang', 'Butcher Grilled Hoàng Kế Viêm', '2026-01-23', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor', 'Bàn đôi trong nhà có điều hòa', 1, 2, 100000, 100000, '2026-01-14 00:00:00', '2026-01-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-7419A535F7-20A5F5', 'MOMOAFE86F3CEC', '2026-01-14 02:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6167DDC7A4', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-03 03:00:00', '2026-01-03 16:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2693a0c40dc01724e4', 'butcher-grilled-hoang-ke-viem-da-nang', 'Butcher Grilled Hoàng Kế Viêm', '2026-01-14', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 người tiêu chuẩn', 1, 4, 200000, 200000, '2026-01-03 00:00:00', '2026-01-03 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-6167DDC7A4-28FD75', 'MOMOFBE71415C3', '2026-01-03 03:00:00', 200000, '2026-01-03 16:00:00', '2026-01-03 00:00:00', '2026-01-03 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A8E9149A30', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-30 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-30 00:00:00', '2026-04-30 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2693a0c40dc01724e4', 'butcher-grilled-hoang-ke-viem-da-nang', 'Butcher Grilled Hoàng Kế Viêm', '2026-05-12', '20:00:00', 30, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-private-room', 'Phòng riêng VIP (tối đa 30 khách)', 1, 30, 300000, 300000, '2026-04-30 00:00:00', '2026-04-30 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-A8E9149A30-406B83', 'MOMO7B067CEE44', '2026-04-30 03:00:00', NULL, NULL, '2026-04-30 00:00:00', '2026-04-30 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EFEA78EB12', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-11 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-11 00:00:00', '2026-04-11 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2693a0c40dc01724e4', 'butcher-grilled-hoang-ke-viem-da-nang', 'Butcher Grilled Hoàng Kế Viêm', '2026-04-23', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor', 'Bàn đôi trong nhà có điều hòa', 1, 2, 100000, 100000, '2026-04-11 00:00:00', '2026-04-11 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-EFEA78EB12-B9FB4C', 'MOMO3E9826DB4D', '2026-04-11 04:00:00', NULL, NULL, '2026-04-11 00:00:00', '2026-04-11 04:00:00');

-- ==== Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại (6a47ac2a93a0c40dc01724e5) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-ECDDA58236', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-03-17', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-03-08 00:00:00', '2026-03-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-ECDDA58236-002025', 'MOMO548606346F', '2026-03-08 02:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EA10A6230E', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-08 00:00:00', '2026-03-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-03-20', '19:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 ghế', 1, 8, 150000, 150000, '2026-03-08 00:00:00', '2026-03-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-EA10A6230E-545582', 'MOMOBED5BF6837', '2026-03-08 03:00:00', NULL, NULL, '2026-03-08 00:00:00', '2026-03-08 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-32C7C1927C', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 800000, 800000, 800000, 800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-02-21', '20:00:00', 19, 180, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 2, 30, 400000, 800000, '2026-02-13 00:00:00', '2026-02-13 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 800000, 'VND', 'MOMO', 'RB-32C7C1927C-C413B1', 'MOMO741FCD9A1C', '2026-02-13 04:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0A4B3EB3DD', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-22 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-22 00:00:00', '2026-03-22 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-03-26', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-03-22 00:00:00', '2026-03-22 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-0A4B3EB3DD-65155C', 'MOMOCEE5AD78C9', '2026-03-22 04:00:00', NULL, NULL, '2026-03-22 00:00:00', '2026-03-22 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EC4F8FA105', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-24 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-24 00:00:00', '2026-05-24 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-06-02', '11:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 ghế', 1, 8, 150000, 150000, '2026-05-24 00:00:00', '2026-05-24 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-EC4F8FA105-C2E202', 'MOMO38AC9D3D41', '2026-05-24 04:00:00', NULL, NULL, '2026-05-24 00:00:00', '2026-05-24 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-44F7ABBC1E', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-14 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2a93a0c40dc01724e5', 'am-thuc-hoang-tin-nguyen-van-thoai-da-nang', 'Ẩm Thực Hoàng Tín - Nguyễn Văn Thoại', '2026-02-27', '12:00:00', 22, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 400000, 400000, '2026-02-14 00:00:00', '2026-02-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-44F7ABBC1E-04E5F3', 'MOMO9B524028CE', '2026-02-14 04:00:00', NULL, NULL, '2026-02-14 00:00:00', '2026-02-14 04:00:00');

-- ==== Cơm Niêu 3 Cá Bống Nguyễn Tri Phương (6a47ac2f93a0c40dc01724e6) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8FD5EACA99', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-03-16', '19:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-03-05 00:00:00', '2026-03-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-8FD5EACA99-D10C2E', 'MOMO949764BFDC', '2026-03-05 02:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3B950369C9', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-03-09', '20:00:00', 12, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn nhóm 10 người', 1, 10, 150000, 150000, '2026-03-03 00:00:00', '2026-03-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-3B950369C9-0415C2', 'MOMO536C29EF23', '2026-03-03 03:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4C47E10DE7', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-09 00:00:00', '2026-03-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-03-18', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-03-09 00:00:00', '2026-03-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-4C47E10DE7-269DE2', 'MOMO0B290871A5', '2026-03-09 01:00:00', NULL, NULL, '2026-03-09 00:00:00', '2026-03-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-745574ECE9', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-05 00:00:00', '2026-01-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-01-12', '11:30:00', 12, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn nhóm 10 người', 1, 10, 150000, 150000, '2026-01-05 00:00:00', '2026-01-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-745574ECE9-0D3946', 'MOMO787091FF3E', '2026-01-05 01:00:00', NULL, NULL, '2026-01-05 00:00:00', '2026-01-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-19E9D9288F', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-03-22', '12:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-03-12 00:00:00', '2026-03-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-19E9D9288F-840ECB', 'MOMO63E8AB8483', '2026-03-12 03:00:00', NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2818693201', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-01-23', '12:30:00', 9, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-grp', 'Bàn nhóm 10 người', 1, 10, 150000, 150000, '2026-01-15 00:00:00', '2026-01-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-2818693201-CDFE05', 'MOMOADC091E705', '2026-01-15 02:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A47F637DFF', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-28 02:00:00', '2026-05-28 10:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-28 00:00:00', '2026-05-28 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac2f93a0c40dc01724e6', 'com-nieu-3-ca-bong-nguyen-tri-phuong', 'Cơm Niêu 3 Cá Bống Nguyễn Tri Phương', '2026-05-31', '18:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-05-28 00:00:00', '2026-05-28 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 0, 'VND', 'MOMO', 'RB-A47F637DFF-D0171C', 'MOMOC4D00DE8F9', '2026-05-28 02:00:00', 0, '2026-05-28 10:00:00', '2026-05-28 00:00:00', '2026-05-28 10:00:00');

-- ==== Công Viên – Nhà Hàng Cá Voi (6a47ac3293a0c40dc01724e7) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9C3C32C3C9', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-04 20:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 20:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-02-13', '20:00:00', 48, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-pr-50', 'Phòng riêng 50 khách', 1, 50, 2000000, 2000000, '2026-02-04 00:00:00', '2026-02-04 20:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 2000000, 'VND', 'MOMO', 'RB-9C3C32C3C9-85421A', NULL, NULL, NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 20:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0867090A68', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-10 00:00:00', '2026-04-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-04-12', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-tb-4', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-04-10 00:00:00', '2026-04-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-0867090A68-0BA541', 'MOMO01E4B8965A', '2026-04-10 01:00:00', NULL, NULL, '2026-04-10 00:00:00', '2026-04-10 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-10BCCAE04C', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-09 15:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 15:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-04-13', '11:30:00', 10, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-tb-10-vip', 'Bàn VIP 10 người', 1, 10, 500000, 500000, '2026-04-09 00:00:00', '2026-04-09 15:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-10BCCAE04C-BC1F53', NULL, NULL, NULL, NULL, '2026-04-09 00:00:00', '2026-04-09 15:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8789DF65BD', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-05-02', '12:00:00', 32, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-pr-50', 'Phòng riêng 50 khách', 1, 50, 2000000, 2000000, '2026-04-20 00:00:00', '2026-04-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-8789DF65BD-F4C538', 'MOMOD5316E9924', '2026-04-20 01:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B9491C0303', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-28 00:00:00', '2026-01-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-02-01', '12:30:00', 5, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-tb-4', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-01-28 00:00:00', '2026-01-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-B9491C0303-C80EAE', 'MOMO0D2A783180', '2026-01-28 01:00:00', NULL, NULL, '2026-01-28 00:00:00', '2026-01-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-663B7B9B3B', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-28 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-28 00:00:00', '2026-05-28 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-06-04', '18:00:00', 12, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-tb-10-vip', 'Bàn VIP 10 người', 1, 10, 500000, 500000, '2026-05-28 00:00:00', '2026-05-28 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-663B7B9B3B-838FF8', 'MOMO4487868EC2', '2026-05-28 02:00:00', NULL, NULL, '2026-05-28 00:00:00', '2026-05-28 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A1E8C750AF', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-30 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-30 00:00:00', '2026-05-30 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-06-02', '18:30:00', 34, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-pr-50', 'Phòng riêng 50 khách', 1, 50, 2000000, 2000000, '2026-05-30 00:00:00', '2026-05-30 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-A1E8C750AF-C577B6', 'MOMO931C132F3B', '2026-05-30 03:00:00', NULL, NULL, '2026-05-30 00:00:00', '2026-05-30 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-751CFC05D7', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-28 00:00:00', '2026-01-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3293a0c40dc01724e7', 'cong-vien-nha-hang-ca-voi-vo-nguyen-giap', 'Công Viên – Nhà Hàng Cá Voi', '2026-02-09', '19:00:00', 5, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'cv-tb-4', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-01-28 00:00:00', '2026-01-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-751CFC05D7-BB0D51', 'MOMO57DBD80614', '2026-01-28 01:00:00', NULL, NULL, '2026-01-28 00:00:00', '2026-01-28 01:00:00');

-- ==== Cơm Niêu Nhà Đỏ 2 (6a47ac3593a0c40dc01724e9) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EE23729205', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-17 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-17 00:00:00', '2026-03-17 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e9', 'com-nieu-nha-do-2-nguyen-tri-phuong', 'Cơm Niêu Nhà Đỏ 2', '2026-03-21', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-03-17 00:00:00', '2026-03-17 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-EE23729205-1B31FA', 'MOMO7DED70C6F0', '2026-03-17 01:00:00', NULL, NULL, '2026-03-17 00:00:00', '2026-03-17 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BCCB1BDA85', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-13 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e9', 'com-nieu-nha-do-2-nguyen-tri-phuong', 'Cơm Niêu Nhà Đỏ 2', '2026-03-24', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 người', 1, 8, 100000, 100000, '2026-03-13 00:00:00', '2026-03-13 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-BCCB1BDA85-8A724E', 'MOMOC533C499FE', '2026-03-13 04:00:00', NULL, NULL, '2026-03-13 00:00:00', '2026-03-13 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-27710E2B4A', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-05 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-05 00:00:00', '2026-01-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e9', 'com-nieu-nha-do-2-nguyen-tri-phuong', 'Cơm Niêu Nhà Đỏ 2', '2026-01-13', '12:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-01-05 00:00:00', '2026-01-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-27710E2B4A-05E792', 'MOMO6D46425732', '2026-01-05 02:00:00', NULL, NULL, '2026-01-05 00:00:00', '2026-01-05 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F11D862171', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e9', 'com-nieu-nha-do-2-nguyen-tri-phuong', 'Cơm Niêu Nhà Đỏ 2', '2026-04-19', '12:30:00', 9, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-grp', 'Bàn nhóm 8 người', 1, 8, 100000, 100000, '2026-04-17 00:00:00', '2026-04-17 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-F11D862171-E7C194', 'MOMOFF66EC4FDD', '2026-04-17 02:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3CA84F943A', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e9', 'com-nieu-nha-do-2-nguyen-tri-phuong', 'Cơm Niêu Nhà Đỏ 2', '2026-02-22', '18:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 0, 0, '2026-02-13 00:00:00', '2026-02-13 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-3CA84F943A-45FABC', 'MOMO8D82C9644C', '2026-02-13 04:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 04:00:00');

-- ==== Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du (6a47ac3693a0c40dc01724ea) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B60ECEE852', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-11 00:00:00', '2026-05-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-05-23', '11:30:00', 3, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-grp', 'Bàn nhóm 4 ghế', 2, 4, 0, 0, '2026-05-11 00:00:00', '2026-05-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-B60ECEE852-ACC442', 'MOMO6DA4E5FAD1', '2026-05-11 01:00:00', NULL, NULL, '2026-05-11 00:00:00', '2026-05-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CC72A87F32', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-12 00:00:00', '2026-04-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-04-22', '12:00:00', 6, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-15', 'Phòng riêng 15 khách', 1, 15, 500000, 500000, '2026-04-12 00:00:00', '2026-04-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-CC72A87F32-92B17C', 'MOMOB18794D7EA', '2026-04-12 01:00:00', NULL, NULL, '2026-04-12 00:00:00', '2026-04-12 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-63ACD813D8', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-06-02 02:00:00', '2026-06-02 19:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-06-02 00:00:00', '2026-06-02 19:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-06-06', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-06-02 00:00:00', '2026-06-02 19:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 0, 'VND', 'MOMO', 'RB-63ACD813D8-108B1F', 'MOMO753FFF3E60', '2026-06-02 02:00:00', 0, '2026-06-02 19:00:00', '2026-06-02 00:00:00', '2026-06-02 19:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-88BC3C1CBF', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-09 04:00:00', '2026-01-09 01:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-01-10', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-grp', 'Bàn nhóm 4 ghế', 1, 4, 0, 0, '2026-01-09 00:00:00', '2026-01-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 0, 'VND', 'MOMO', 'RB-88BC3C1CBF-03B5FA', 'MOMO309CF38C3F', '2026-01-09 04:00:00', 0, '2026-01-09 01:00:00', '2026-01-09 00:00:00', '2026-01-09 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-687EA62404', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-01 04:00:00', '2026-04-01 09:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-01 00:00:00', '2026-04-01 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-04-04', '18:30:00', 9, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-15', 'Phòng riêng 15 khách', 1, 15, 500000, 500000, '2026-04-01 00:00:00', '2026-04-01 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 500000, 'VND', 'MOMO', 'RB-687EA62404-552DF7', 'MOMODD32C12CC0', '2026-04-01 04:00:00', 500000, '2026-04-01 09:00:00', '2026-04-01 00:00:00', '2026-04-01 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0623AF0576', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-06-05 09:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-06-05 00:00:00', '2026-06-05 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3693a0c40dc01724ea', 'akataiyo-mat-troi-do-nguyen-du', 'Nhà hàng Akataiyo Mặt Trời Đỏ - Nguyễn Du', '2026-06-06', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-06-05 00:00:00', '2026-06-05 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 0, 'VND', 'MOMO', 'RB-0623AF0576-5C1112', NULL, NULL, NULL, NULL, '2026-06-05 00:00:00', '2026-06-05 09:00:00');

-- ==== Ngon Phố Đà - Homey Authentic Vietnamese Cuisine (6a47ac3793a0c40dc01724eb) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-7A1EE9ECB6', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-01-21', '12:00:00', 11, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-private-room', 'Phòng riêng VIP tầng 2', 1, 20, 500000, 500000, '2026-01-08 00:00:00', '2026-01-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-7A1EE9ECB6-164308', 'MOMO7F53B97333', '2026-01-08 01:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DC5AC05832', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-30 00:00:00', '2026-05-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-06-05', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn thường 4 khách', 1, 4, 100000, 100000, '2026-05-30 00:00:00', '2026-05-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-DC5AC05832-98F698', 'MOMO324EA58491', '2026-05-30 04:00:00', NULL, NULL, '2026-05-30 00:00:00', '2026-05-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-DB92248F6B', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-05-23', '18:00:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-family', 'Bàn gia đình 8 khách', 2, 8, 200000, 400000, '2026-05-18 00:00:00', '2026-05-18 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-DB92248F6B-AF9B41', 'MOMOA502B463AE', '2026-05-18 04:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A67772984A', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-11 13:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-11 00:00:00', '2026-04-11 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-04-17', '18:30:00', 42, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-private-room', 'Phòng riêng VIP tầng 2', 1, 20, 500000, 500000, '2026-04-11 00:00:00', '2026-04-11 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-A67772984A-E307E4', NULL, NULL, NULL, NULL, '2026-04-11 00:00:00', '2026-04-11 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AFA0053874', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-01-09', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn thường 4 khách', 1, 4, 100000, 100000, '2026-01-08 00:00:00', '2026-01-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-AFA0053874-16D86A', 'MOMOB47EABD5A6', '2026-01-08 01:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8A625D160F', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-08 00:00:00', '2026-04-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-04-10', '19:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-family', 'Bàn gia đình 8 khách', 1, 8, 200000, 200000, '2026-04-08 00:00:00', '2026-04-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-8A625D160F-574FB5', 'MOMOAD2A57D17D', '2026-04-08 01:00:00', NULL, NULL, '2026-04-08 00:00:00', '2026-04-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8DFD84482B', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-09 03:00:00', '2026-04-09 03:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3793a0c40dc01724eb', 'ngon-pho-da-vo-nguyen-giap-da-nang', 'Ngon Phố Đà - Homey Authentic Vietnamese Cuisine', '2026-04-10', '20:00:00', 57, 150, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-private-room', 'Phòng riêng VIP tầng 2', 2, 20, 500000, 1000000, '2026-04-09 00:00:00', '2026-04-09 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-8DFD84482B-2504CD', 'MOMO6903830B5D', '2026-04-09 03:00:00', 1000000, '2026-04-09 03:00:00', '2026-04-09 00:00:00', '2026-04-09 03:00:00');

-- ==== DOM - The Wine Bistro - Phan Bội Châu (6a47ac3893a0c40dc01724ec) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BDA1D5DCC0', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-02 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-02 00:00:00', '2026-06-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-06-03', '12:30:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor', 'Bàn đôi trong nhà', 2, 2, 100000, 200000, '2026-06-02 00:00:00', '2026-06-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-BDA1D5DCC0-230B95', 'MOMO258CA26781', '2026-06-02 01:00:00', NULL, NULL, '2026-06-02 00:00:00', '2026-06-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-209D305196', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 400000, 400000, 400000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-25 18:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-02-28', '18:00:00', 3, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-indoor', 'Bàn 4 khách trong nhà', 2, 4, 200000, 400000, '2026-02-25 00:00:00', '2026-02-25 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 400000, 'VND', 'MOMO', 'RB-209D305196-B2FB95', NULL, NULL, NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-54C4A57D8F', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-20 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-20 00:00:00', '2026-04-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-04-29', '18:30:00', 2, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn ngoài trời có mái che', 1, 4, 200000, 200000, '2026-04-20 00:00:00', '2026-04-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-54C4A57D8F-B11E0E', 'MOMO911EF2E63D', '2026-04-20 03:00:00', NULL, NULL, '2026-04-20 00:00:00', '2026-04-20 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A730CA62FA', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-12 00:00:00', '2026-02-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-02-17', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor', 'Bàn đôi trong nhà', 1, 2, 100000, 100000, '2026-02-12 00:00:00', '2026-02-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-A730CA62FA-2A9B42', 'MOMO8FCA478D98', '2026-02-12 03:00:00', NULL, NULL, '2026-02-12 00:00:00', '2026-02-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-13B539A08C', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-07 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-07 00:00:00', '2026-04-07 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-04-08', '19:30:00', 2, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-indoor', 'Bàn 4 khách trong nhà', 1, 4, 200000, 200000, '2026-04-07 00:00:00', '2026-04-07 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-13B539A08C-B1FC97', 'MOMO0FB09BF221', '2026-04-07 04:00:00', NULL, NULL, '2026-04-07 00:00:00', '2026-04-07 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F085A51939', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-29 01:00:00', '2026-03-29 13:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-03-29 00:00:00', '2026-03-29 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-04-09', '20:00:00', 2, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn ngoài trời có mái che', 1, 4, 200000, 200000, '2026-03-29 00:00:00', '2026-03-29 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-F085A51939-F86A3E', 'MOMOFCEFA3E724', '2026-03-29 01:00:00', 200000, '2026-03-29 13:00:00', '2026-03-29 00:00:00', '2026-03-29 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-402948F63F', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-01-15', '11:00:00', 1, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-indoor', 'Bàn đôi trong nhà', 2, 2, 100000, 200000, '2026-01-12 00:00:00', '2026-01-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-402948F63F-DC0AB5', 'MOMO10E0CFCEEB', '2026-01-12 02:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0AA486AC75', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-18 00:00:00', '2026-04-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3893a0c40dc01724ec', 'dom-the-wine-bistro-phan-boi-chau', 'DOM - The Wine Bistro - Phan Bội Châu', '2026-04-30', '11:30:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-indoor', 'Bàn 4 khách trong nhà', 1, 4, 200000, 200000, '2026-04-18 00:00:00', '2026-04-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-0AA486AC75-C7D603', 'MOMO35F25F6B1C', '2026-04-18 01:00:00', NULL, NULL, '2026-04-18 00:00:00', '2026-04-18 01:00:00');

-- ==== Thai Market - Trần Quốc Toản (6a47ac3993a0c40dc01724ed) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C33C9BE56E', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3993a0c40dc01724ed', 'thai-market-tran-quoc-toan-da-nang', 'Thai Market - Trần Quốc Toản', '2026-01-13', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 200000, 200000, '2026-01-12 00:00:00', '2026-01-12 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-C33C9BE56E-3311B8', 'MOMOFCF63EF8A3', '2026-01-12 04:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D04E04922A', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-24 00:00:00', '2026-04-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3993a0c40dc01724ed', 'thai-market-tran-quoc-toan-da-nang', 'Thai Market - Trần Quốc Toản', '2026-04-30', '18:30:00', 30, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng tiệc riêng 40 khách', 1, 40, 600000, 600000, '2026-04-24 00:00:00', '2026-04-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-D04E04922A-41C5B4', 'MOMOC598E77DD7', '2026-04-24 01:00:00', NULL, NULL, '2026-04-24 00:00:00', '2026-04-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-AF6B141D10', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3993a0c40dc01724ed', 'thai-market-tran-quoc-toan-da-nang', 'Thai Market - Trần Quốc Toản', '2026-01-13', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-01-08 00:00:00', '2026-01-08 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-AF6B141D10-170332', 'MOMOB8D8C0B66E', '2026-01-08 03:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-12C8214889', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-23 12:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-03-23 00:00:00', '2026-03-23 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3993a0c40dc01724ed', 'thai-market-tran-quoc-toan-da-nang', 'Thai Market - Trần Quốc Toản', '2026-04-02', '19:30:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 200000, 200000, '2026-03-23 00:00:00', '2026-03-23 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-12C8214889-142D1F', NULL, NULL, NULL, NULL, '2026-03-23 00:00:00', '2026-03-23 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A79C6565AA', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3993a0c40dc01724ed', 'thai-market-tran-quoc-toan-da-nang', 'Thai Market - Trần Quốc Toản', '2026-05-22', '20:00:00', 24, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng tiệc riêng 40 khách', 1, 40, 600000, 600000, '2026-05-20 00:00:00', '2026-05-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-A79C6565AA-61E585', 'MOMO39C44B0B9E', '2026-05-20 01:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 01:00:00');

-- ==== Thai Market Restaurant - 183 Nguyễn Văn Thoại (6a47ac3a93a0c40dc01724ee) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B7CAF9D7C6', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-07 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-07 00:00:00', '2026-04-07 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-04-20', '18:30:00', 19, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 500000, 500000, '2026-04-07 00:00:00', '2026-04-07 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-B7CAF9D7C6-B994BE', 'MOMO171242F1F0', '2026-04-07 01:00:00', NULL, NULL, '2026-04-07 00:00:00', '2026-04-07 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-131C6ADDE2', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-02-21', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-02-08 00:00:00', '2026-02-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-131C6ADDE2-82686C', 'MOMO71A370C63B', '2026-02-08 02:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-09469C37D4', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-17 00:00:00', '2026-02-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-03-02', '19:30:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn VIP 6 ghế', 1, 6, 200000, 200000, '2026-02-17 00:00:00', '2026-02-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-09469C37D4-69ADF2', 'MOMO3BC85AF4D3', '2026-02-17 04:00:00', NULL, NULL, '2026-02-17 00:00:00', '2026-02-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C0F7CABB93', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-04-10', '20:00:00', 25, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-25', 'Phòng riêng 25 khách', 1, 25, 500000, 500000, '2026-03-28 00:00:00', '2026-03-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-C0F7CABB93-B01A03', 'MOMO1F481F0CC2', '2026-03-28 03:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-38B6E0C37D', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-18 00:00:00', '2026-03-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-03-23', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-03-18 00:00:00', '2026-03-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-38B6E0C37D-FAE184', 'MOMO222E70344D', '2026-03-18 03:00:00', NULL, NULL, '2026-03-18 00:00:00', '2026-03-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F7AD031E1C', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-19 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-19 00:00:00', '2026-02-19 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3a93a0c40dc01724ee', 'thai-market-183-nguyen-van-thoai-da-nang', 'Thai Market Restaurant - 183 Nguyễn Văn Thoại', '2026-03-04', '11:30:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn VIP 6 ghế', 1, 6, 200000, 200000, '2026-02-19 00:00:00', '2026-02-19 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-F7AD031E1C-B2B806', 'MOMO698FBC714B', '2026-02-19 01:00:00', NULL, NULL, '2026-02-19 00:00:00', '2026-02-19 01:00:00');

-- ==== Thai Market Restaurant - Yên Bái (6a47ac3b93a0c40dc01724ef) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-103349796C', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-04 03:00:00', '2026-01-04 16:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-04 00:00:00', '2026-01-04 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-01-15', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-01-04 00:00:00', '2026-01-04 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 0, 'VND', 'MOMO', 'RB-103349796C-8A707F', 'MOMO07E3FE75E6', '2026-01-04 03:00:00', 0, '2026-01-04 16:00:00', '2026-01-04 00:00:00', '2026-01-04 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-909C36D263', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-04-06', '19:30:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 2, 8, 200000, 400000, '2026-03-27 00:00:00', '2026-03-27 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-909C36D263-62BFC6', 'MOMOE0E7E638C7', '2026-03-27 04:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-35D84113D8', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-03-23', '20:00:00', 16, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 500000, 500000, '2026-03-13 00:00:00', '2026-03-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-35D84113D8-EC0010', 'MOMO697A2DF3DF', '2026-03-13 02:00:00', NULL, NULL, '2026-03-13 00:00:00', '2026-03-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F2F5605F30', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-11 00:00:00', '2026-03-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-03-14', '11:00:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 2, 4, 0, 0, '2026-03-11 00:00:00', '2026-03-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-F2F5605F30-60D892', 'MOMO9A86B02EB9', '2026-03-11 03:00:00', NULL, NULL, '2026-03-11 00:00:00', '2026-03-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E1CF1C900E', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-05-22', '11:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 200000, 200000, '2026-05-18 00:00:00', '2026-05-18 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-E1CF1C900E-EFA2E5', 'MOMOE5C8DC083A', '2026-05-18 04:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-43D1ED1F2B', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-11 00:00:00', '2026-05-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-05-23', '12:00:00', 26, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 500000, 500000, '2026-05-11 00:00:00', '2026-05-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-43D1ED1F2B-BB0203', 'MOMOC2B047A400', '2026-05-11 03:00:00', NULL, NULL, '2026-05-11 00:00:00', '2026-05-11 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D338BEA41C', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-04 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-04 00:00:00', '2026-04-04 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3b93a0c40dc01724ef', 'thai-market-yen-bai-da-nang', 'Thai Market Restaurant - Yên Bái', '2026-04-05', '12:30:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-04-04 00:00:00', '2026-04-04 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-D338BEA41C-0F6E78', 'MOMO57F6F0140D', '2026-04-04 01:00:00', NULL, NULL, '2026-04-04 00:00:00', '2026-04-04 01:00:00');

-- ==== East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng (6a47ac4093a0c40dc01724f0) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-850C594AEB', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-26 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-26 00:00:00', '2026-03-26 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-04-07', '19:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-outdoor', 'Bàn ngoài trời view đường biển 6 người', 1, 6, 200000, 200000, '2026-03-26 00:00:00', '2026-03-26 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-850C594AEB-42E2C7', 'MOMO727FE001B6', '2026-03-26 03:00:00', NULL, NULL, '2026-03-26 00:00:00', '2026-03-26 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A24F8D868F', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-03-30', '20:00:00', 13, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-30-private-vip', 'Phòng VIP riêng 10-30 khách', 1, 30, 500000, 500000, '2026-03-25 00:00:00', '2026-03-25 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-A24F8D868F-0508EF', 'MOMO1DC60F928C', '2026-03-25 02:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-53234C825B', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-02 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-02 00:00:00', '2026-05-02 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-05-06', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-indoor', 'Bàn trong nhà 4 người', 1, 4, 150000, 150000, '2026-05-02 00:00:00', '2026-05-02 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-53234C825B-CB8ED7', 'MOMO69B0237E57', '2026-05-02 03:00:00', NULL, NULL, '2026-05-02 00:00:00', '2026-05-02 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D505675610', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-12 00:00:00', '2026-04-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-04-17', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-outdoor', 'Bàn ngoài trời view đường biển 6 người', 1, 6, 200000, 200000, '2026-04-12 00:00:00', '2026-04-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-D505675610-714154', 'MOMO54A1997E25', '2026-04-12 01:00:00', NULL, NULL, '2026-04-12 00:00:00', '2026-04-12 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-266C4F053C', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-01 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-01 00:00:00', '2026-05-01 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-05-13', '12:00:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-30-private-vip', 'Phòng VIP riêng 10-30 khách', 1, 30, 500000, 500000, '2026-05-01 00:00:00', '2026-05-01 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-266C4F053C-054786', 'MOMOBCD849A54B', '2026-05-01 01:00:00', NULL, NULL, '2026-05-01 00:00:00', '2026-05-01 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B5535156D6', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 150000, 150000, 150000, 150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-16 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-01-23', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-indoor', 'Bàn trong nhà 4 người', 1, 4, 150000, 150000, '2026-01-16 00:00:00', '2026-01-16 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 150000, 'VND', 'MOMO', 'RB-B5535156D6-5DB589', 'MOMO92E32E3513', '2026-01-16 01:00:00', NULL, NULL, '2026-01-16 00:00:00', '2026-01-16 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-888B42156F', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-03-20', '18:00:00', 4, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-outdoor', 'Bàn ngoài trời view đường biển 6 người', 1, 6, 200000, 200000, '2026-03-19 00:00:00', '2026-03-19 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-888B42156F-B4B4A2', 'MOMO5509466BAD', '2026-03-19 01:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2C2993976E', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-05 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-05 00:00:00', '2026-01-05 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4093a0c40dc01724f0', 'east-west-brewing-co-vo-nguyen-giap-da-nang', 'East West Brewing Co. - Võ Nguyên Giáp Đà Nẵng', '2026-01-11', '18:30:00', 17, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-30-private-vip', 'Phòng VIP riêng 10-30 khách', 1, 30, 500000, 500000, '2026-01-05 00:00:00', '2026-01-05 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-2C2993976E-E26F9E', 'MOMOD1CEED5D9A', '2026-01-05 03:00:00', NULL, NULL, '2026-01-05 00:00:00', '2026-01-05 03:00:00');

-- ==== Nhà hàng Cây Dừa 1 Nguyễn Tất Thành (6a47ac4593a0c40dc01724f1) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4D796F9D2A', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-05 00:00:00', '2026-05-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4593a0c40dc01724f1', 'nha-hang-cay-dua-1-nguyen-tat-thanh', 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành', '2026-05-15', '20:00:00', 20, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-05-05 00:00:00', '2026-05-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-4D796F9D2A-6DC1C1', 'MOMOF5A9F529CA', '2026-05-05 01:00:00', NULL, NULL, '2026-05-05 00:00:00', '2026-05-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-57F2A7AACA', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-20 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-20 00:00:00', '2026-01-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4593a0c40dc01724f1', 'nha-hang-cay-dua-1-nguyen-tat-thanh', 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành', '2026-01-30', '11:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-01-20 00:00:00', '2026-01-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-57F2A7AACA-8F2061', 'MOMO274C97C709', '2026-01-20 03:00:00', NULL, NULL, '2026-01-20 00:00:00', '2026-01-20 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5A4BF7A587', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4593a0c40dc01724f1', 'nha-hang-cay-dua-1-nguyen-tat-thanh', 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành', '2026-03-30', '11:30:00', 10, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-vip', 'Bàn VIP 10 người', 1, 10, 500000, 500000, '2026-03-25 00:00:00', '2026-03-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-5A4BF7A587-1C79F4', 'MOMO767E6740A5', '2026-03-25 04:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2183ABB154', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-28 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-28 00:00:00', '2026-05-28 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4593a0c40dc01724f1', 'nha-hang-cay-dua-1-nguyen-tat-thanh', 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành', '2026-05-30', '12:00:00', 39, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-40', 'Phòng riêng 40 khách', 1, 40, 2000000, 2000000, '2026-05-28 00:00:00', '2026-05-28 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-2183ABB154-EEDC7B', 'MOMOEAB2436725', '2026-05-28 02:00:00', NULL, NULL, '2026-05-28 00:00:00', '2026-05-28 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2A04B744D4', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-06 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-06 00:00:00', '2026-03-06 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4593a0c40dc01724f1', 'nha-hang-cay-dua-1-nguyen-tat-thanh', 'Nhà hàng Cây Dừa 1 Nguyễn Tất Thành', '2026-03-18', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 người', 1, 4, 100000, 100000, '2026-03-06 00:00:00', '2026-03-06 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-2A04B744D4-DCB6B9', 'MOMOB57461C44A', '2026-03-06 01:00:00', NULL, NULL, '2026-03-06 00:00:00', '2026-03-06 01:00:00');

-- ==== Country BBQ & Beer Trần Bạch Đằng (6a47ac4693a0c40dc01724f2) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-30C13C9BC1', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-03-24', '11:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 người ngoài trời', 1, 4, 100000, 100000, '2026-03-21 00:00:00', '2026-03-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-30C13C9BC1-A9B0C8', 'MOMO77ADC76BB5', '2026-03-21 01:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-18E3437AC0', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-29 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-01-31', '11:30:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn dài nhóm 8 người', 1, 8, 200000, 200000, '2026-01-29 00:00:00', '2026-01-29 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-18E3437AC0-65DDDD', 'MOMO48095EAC76', '2026-01-29 04:00:00', NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-B5E951B0FF', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-18 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-18 00:00:00', '2026-01-18 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-01-20', '12:00:00', 24, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-20-party', 'Khu tiệc nhóm lớn từ 15-30 khách', 1, 30, 500000, 500000, '2026-01-18 00:00:00', '2026-01-18 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-B5E951B0FF-7B79F8', 'MOMO7960F70664', '2026-01-18 02:00:00', NULL, NULL, '2026-01-18 00:00:00', '2026-01-18 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-48B1727A10', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-18 00:00:00', '2026-03-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-03-21', '12:30:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 người ngoài trời', 1, 4, 100000, 100000, '2026-03-18 00:00:00', '2026-03-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-48B1727A10-4A19E6', 'MOMO80BEF70D7A', '2026-03-18 03:00:00', NULL, NULL, '2026-03-18 00:00:00', '2026-03-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-136D1E12A6', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-30 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-30 00:00:00', '2026-05-30 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-06-04', '18:00:00', 7, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn dài nhóm 8 người', 1, 8, 200000, 200000, '2026-05-30 00:00:00', '2026-05-30 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-136D1E12A6-565104', 'MOMO5F587C89BC', '2026-05-30 01:00:00', NULL, NULL, '2026-05-30 00:00:00', '2026-05-30 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4CB162B0D6', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-16 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-16 00:00:00', '2026-05-16 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac4693a0c40dc01724f2', 'country-bbq-beer-tran-bach-dang-da-nang', 'Country BBQ & Beer Trần Bạch Đằng', '2026-05-19', '18:30:00', 15, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-20-party', 'Khu tiệc nhóm lớn từ 15-30 khách', 1, 30, 500000, 500000, '2026-05-16 00:00:00', '2026-05-16 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-4CB162B0D6-FBA57F', 'MOMOBBF0ECB712', '2026-05-16 03:00:00', NULL, NULL, '2026-05-16 00:00:00', '2026-05-16 03:00:00');

-- ==== Sushi World - Phan Bội Châu (6a47ac5d93a0c40dc01724f3) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BD30E23B5D', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-04 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-02-13', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn nhóm 8 khách', 1, 8, 400000, 400000, '2026-02-04 00:00:00', '2026-02-04 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-BD30E23B5D-2E36E5', 'MOMO80D8182215', '2026-02-04 03:00:00', NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BD86FD19A0', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 3000000, 3000000, 3000000, 3000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-24 00:00:00', '2026-03-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-04-01', '12:00:00', 24, 150, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30-private', 'Phòng riêng 30 khách', 2, 30, 1500000, 3000000, '2026-03-24 00:00:00', '2026-03-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3000000, 'VND', 'MOMO', 'RB-BD86FD19A0-EDBCA8', 'MOMOF9B3C5B6DB', '2026-03-24 01:00:00', NULL, NULL, '2026-03-24 00:00:00', '2026-03-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6312EA2808', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-02-09', '12:30:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách tiêu chuẩn', 2, 4, 200000, 400000, '2026-01-27 00:00:00', '2026-01-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-6312EA2808-5D857B', 'MOMO5C3F7409F4', '2026-01-27 02:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8803F3680C', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-01 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-02-04', '18:00:00', 7, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn nhóm 8 khách', 1, 8, 400000, 400000, '2026-02-01 00:00:00', '2026-02-01 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-8803F3680C-B87693', 'MOMOCC0C412933', '2026-02-01 04:00:00', NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C89D5131F3', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1500000, 1500000, 1500000, 1500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-04-08', '18:30:00', 13, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30-private', 'Phòng riêng 30 khách', 1, 30, 1500000, 1500000, '2026-04-06 00:00:00', '2026-04-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1500000, 'VND', 'MOMO', 'RB-C89D5131F3-BA302E', 'MOMO46F6D09E81', '2026-04-06 03:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2FAF6ABBE2', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-02-11', '19:00:00', 3, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-standard', 'Bàn 4 khách tiêu chuẩn', 2, 4, 200000, 400000, '2026-02-09 00:00:00', '2026-02-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-2FAF6ABBE2-A1FAAB', 'MOMO67573EB14F', '2026-02-09 04:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-3D493F5ADB', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac5d93a0c40dc01724f3', 'sushi-world-phan-boi-chau-da-nang', 'Sushi World - Phan Bội Châu', '2026-02-17', '19:30:00', 6, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-group', 'Bàn nhóm 8 khách', 1, 8, 400000, 400000, '2026-02-09 00:00:00', '2026-02-09 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-3D493F5ADB-4D71C3', 'MOMO1F6E12A877', '2026-02-09 03:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 03:00:00');

-- ==== Nhà hàng Zzuggubbong – Nguyễn Hữu Thông (6a47ac6093a0c40dc01724f4) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2AB9137E2C', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 500000, 500000, 500000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-17 17:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-01-29', '12:00:00', 14, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-pr-20', 'Phòng riêng 20 khách', 1, 20, 500000, 500000, '2026-01-17 00:00:00', '2026-01-17 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 500000, 'VND', 'MOMO', 'RB-2AB9137E2C-3E04AB', NULL, NULL, NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 17:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-77673D4D5E', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 80000, 80000, 80000, 80000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-01-31', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-tb-4', 'Bàn nướng 4 người', 1, 4, 80000, 80000, '2026-01-26 00:00:00', '2026-01-26 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 80000, 'VND', 'MOMO', 'RB-77673D4D5E-7391C6', 'MOMO9682DC9729', '2026-01-26 03:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A46E414D25', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-19 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-19 00:00:00', '2026-05-19 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-05-26', '18:00:00', 9, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-tb-8', 'Bàn nhóm 8 người', 1, 8, 200000, 200000, '2026-05-19 00:00:00', '2026-05-19 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-A46E414D25-096A37', 'MOMO77573C6D7E', '2026-05-19 04:00:00', NULL, NULL, '2026-05-19 00:00:00', '2026-05-19 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2DD389FAAB', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-02-25', '18:30:00', 14, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-pr-20', 'Phòng riêng 20 khách', 1, 20, 500000, 500000, '2026-02-16 00:00:00', '2026-02-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-2DD389FAAB-0B07F3', 'MOMOEE4191BFC2', '2026-02-16 04:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-A763B7F100', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 80000, 80000, 80000, 80000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-28 00:00:00', '2026-04-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-05-06', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-tb-4', 'Bàn nướng 4 người', 1, 4, 80000, 80000, '2026-04-28 00:00:00', '2026-04-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 80000, 'VND', 'MOMO', 'RB-A763B7F100-8291EA', 'MOMOBC2333CFD4', '2026-04-28 01:00:00', NULL, NULL, '2026-04-28 00:00:00', '2026-04-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5F9C6907F2', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 400000, 400000, 400000, 400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-26 00:00:00', '2026-04-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-04-28', '19:30:00', 8, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-tb-8', 'Bàn nhóm 8 người', 2, 8, 200000, 400000, '2026-04-26 00:00:00', '2026-04-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 400000, 'VND', 'MOMO', 'RB-5F9C6907F2-607108', 'MOMOED93293DE4', '2026-04-26 02:00:00', NULL, NULL, '2026-04-26 00:00:00', '2026-04-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5A5ADA42E9', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-26 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-26 00:00:00', '2026-05-26 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-06-01', '20:00:00', 17, 180, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-pr-20', 'Phòng riêng 20 khách', 1, 20, 500000, 500000, '2026-05-26 00:00:00', '2026-05-26 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-5A5ADA42E9-4854AB', 'MOMOCC397F3E72', '2026-05-26 03:00:00', NULL, NULL, '2026-05-26 00:00:00', '2026-05-26 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-374A2306B6', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 160000, 160000, 160000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-05-12 02:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-05-12 00:00:00', '2026-05-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6093a0c40dc01724f4', 'zzuggubbong-nguyen-huu-thong-da-nang', 'Nhà hàng Zzuggubbong – Nguyễn Hữu Thông', '2026-05-14', '11:00:00', 4, 60, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'zzug-tb-4', 'Bàn nướng 4 người', 2, 4, 80000, 160000, '2026-05-12 00:00:00', '2026-05-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 160000, 'VND', 'MOMO', 'RB-374A2306B6-49067D', NULL, NULL, NULL, NULL, '2026-05-12 00:00:00', '2026-05-12 02:00:00');

-- ==== Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo (6a47ac6193a0c40dc01724f5) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-07078808F9', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-14 00:00:00', '2026-05-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6193a0c40dc01724f5', 'de-nhat-de-tran-hung-dao-da-nang', 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo', '2026-05-21', '12:30:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-05-14 00:00:00', '2026-05-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-07078808F9-7A4642', 'MOMO40B6D0BEAD', '2026-05-14 02:00:00', NULL, NULL, '2026-05-14 00:00:00', '2026-05-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-4E5E7F85F8', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-21 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-21 00:00:00', '2026-04-21 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6193a0c40dc01724f5', 'de-nhat-de-tran-hung-dao-da-nang', 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo', '2026-05-02', '18:00:00', 6, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 200000, 200000, '2026-04-21 00:00:00', '2026-04-21 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 200000, 'VND', 'MOMO', 'RB-4E5E7F85F8-0FC640', 'MOMO71B80DB78C', '2026-04-21 03:00:00', NULL, NULL, '2026-04-21 00:00:00', '2026-04-21 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EADA041DCD', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-05 00:00:00', '2026-03-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6193a0c40dc01724f5', 'de-nhat-de-tran-hung-dao-da-nang', 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo', '2026-03-18', '18:30:00', 27, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-30', 'Phòng riêng 30 khách', 1, 30, 500000, 500000, '2026-03-05 00:00:00', '2026-03-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-EADA041DCD-A2A0DD', 'MOMOD15A854EED', '2026-03-05 01:00:00', NULL, NULL, '2026-03-05 00:00:00', '2026-03-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EE887B9ADF', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6193a0c40dc01724f5', 'de-nhat-de-tran-hung-dao-da-nang', 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo', '2026-02-06', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-std', 'Bàn thường 4 ghế', 1, 4, 0, 0, '2026-01-31 00:00:00', '2026-01-31 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-EE887B9ADF-62B397', 'MOMO003E98456E', '2026-01-31 04:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-209D35FA03', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-10 03:00:00', '2026-05-10 05:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6193a0c40dc01724f5', 'de-nhat-de-tran-hung-dao-da-nang', 'Nhà hàng Đệ Nhất Dê - Trần Hưng Đạo', '2026-05-13', '19:30:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-8-vip', 'Bàn VIP 8 ghế', 1, 8, 200000, 200000, '2026-05-10 00:00:00', '2026-05-10 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-209D35FA03-94CC27', 'MOMOC0B12B5621', '2026-05-10 03:00:00', 200000, '2026-05-10 05:00:00', '2026-05-10 00:00:00', '2026-05-10 05:00:00');

-- ==== Thai Market Bình Minh 5 (6a47ac6293a0c40dc01724f6) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-CFC4338333', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-03 00:00:00', '2026-04-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-04-16', '18:00:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-grp', 'Bàn nhóm 4 ghế', 1, 4, 0, 0, '2026-04-03 00:00:00', '2026-04-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-CFC4338333-5A4BE3', 'MOMO76BB8BA161', '2026-04-03 01:00:00', NULL, NULL, '2026-04-03 00:00:00', '2026-04-03 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-F37EF9170B', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-15 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-15 00:00:00', '2026-02-15 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-02-16', '18:30:00', 20, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 500000, 500000, '2026-02-15 00:00:00', '2026-02-15 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-F37EF9170B-2ED70F', 'MOMOA972CA6CA8', '2026-02-15 01:00:00', NULL, NULL, '2026-02-15 00:00:00', '2026-02-15 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-70024DA202', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-03-29', '19:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-03-27 00:00:00', '2026-03-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-70024DA202-312874', 'MOMO80E96B94EB', '2026-03-27 02:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-529A3DF966', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-03-09', '19:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-grp', 'Bàn nhóm 4 ghế', 1, 4, 0, 0, '2026-02-25 00:00:00', '2026-02-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-529A3DF966-F1356C', 'MOMO68CA6F6625', '2026-02-25 01:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9365397B49', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-03-20', '20:00:00', 10, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-20', 'Phòng riêng 20 khách', 1, 20, 500000, 500000, '2026-03-13 00:00:00', '2026-03-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-9365397B49-6B7D16', 'MOMO35E06F0726', '2026-03-13 02:00:00', NULL, NULL, '2026-03-13 00:00:00', '2026-03-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C40F4BCBC3', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 0, 0, 0, 0, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-28 00:00:00', '2026-01-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6293a0c40dc01724f6', 'thai-market-binh-minh-5-da-nang', 'Thai Market Bình Minh 5', '2026-02-10', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-std', 'Bàn đôi 2 ghế', 1, 2, 0, 0, '2026-01-28 00:00:00', '2026-01-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 0, 'VND', 'MOMO', 'RB-C40F4BCBC3-7BDA93', 'MOMODA5501D22F', '2026-01-28 03:00:00', NULL, NULL, '2026-01-28 00:00:00', '2026-01-28 03:00:00');

-- ==== The Holiday Beach Club & Dining (6a47ac6693a0c40dc01724f7) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5489463842', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-04-01', '18:30:00', 13, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-booth', 'VIP Booth ngoai troi san khau', 1, 12, 300000, 300000, '2026-03-20 00:00:00', '2026-03-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-5489463842-5064F0', 'MOMOF4BEE9C54C', '2026-03-20 01:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-690F102EA1', 17, 'Ngô Thị Lan', '0901234517', NULL, 'DEPOSIT', 50000, 50000, 50000, 50000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-15 00:00:00', '2026-03-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-03-20', '19:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-couple', 'Ban doi ngoai troi view bien', 1, 2, 50000, 50000, '2026-03-15 00:00:00', '2026-03-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 50000, 'VND', 'MOMO', 'RB-690F102EA1-5029C3', 'MOMO2AC98C63CD', '2026-03-15 04:00:00', NULL, NULL, '2026-03-15 00:00:00', '2026-03-15 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9F33FB22A7', 18, 'Lê Đức Minh', '0901234518', NULL, 'DEPOSIT', 100000, 100000, 100000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-24 10:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-24 00:00:00', '2026-02-24 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-03-06', '19:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-group', 'Ban nhom 6 nguoi', 1, 6, 100000, 100000, '2026-02-24 00:00:00', '2026-02-24 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 100000, 'VND', 'MOMO', 'RB-9F33FB22A7-E59DC0', NULL, NULL, NULL, NULL, '2026-02-24 00:00:00', '2026-02-24 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-339629142C', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-06-01 02:00:00', '2026-06-01 01:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-06-01 00:00:00', '2026-06-01 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-06-04', '20:00:00', 15, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-booth', 'VIP Booth ngoai troi san khau', 1, 12, 300000, 300000, '2026-06-01 00:00:00', '2026-06-01 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 300000, 'VND', 'MOMO', 'RB-339629142C-5439B4', 'MOMO9D937A86F5', '2026-06-01 02:00:00', 300000, '2026-06-01 01:00:00', '2026-06-01 00:00:00', '2026-06-01 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5A02AA8C9E', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'DEPOSIT', 50000, 50000, 50000, 50000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-05-28', '11:00:00', 2, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-couple', 'Ban doi ngoai troi view bien', 1, 2, 50000, 50000, '2026-05-25 00:00:00', '2026-05-25 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 50000, 'VND', 'MOMO', 'RB-5A02AA8C9E-B0E80F', 'MOMO2B1C5F2FDE', '2026-05-25 02:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D4FF156BB7', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-15 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-15 00:00:00', '2026-02-15 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-02-23', '11:30:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-group', 'Ban nhom 6 nguoi', 1, 6, 100000, 100000, '2026-02-15 00:00:00', '2026-02-15 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-D4FF156BB7-9D8AAA', 'MOMO4E4A10D878', '2026-02-15 01:00:00', NULL, NULL, '2026-02-15 00:00:00', '2026-02-15 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-ED28733758', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac6693a0c40dc01724f7', 'holiday-beach-club-dining-vo-nguyen-giap-da-nang', 'The Holiday Beach Club & Dining', '2026-03-14', '12:00:00', 15, 150, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-vip-booth', 'VIP Booth ngoai troi san khau', 1, 12, 300000, 300000, '2026-03-03 00:00:00', '2026-03-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-ED28733758-BFE22A', 'MOMO37B4A705A8', '2026-03-03 01:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 01:00:00');
