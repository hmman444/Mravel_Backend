-- Seed demo bookings RESTAURANT cho khu vuc: hoi-an
-- 5-8 booking/restaurant, ngay dat ban rai trong 1-6 thang qua, thanh toan (dat coc) qua MOMO_WALLET
-- payOption luon DEPOSIT (dung flow that: totalAmount == depositAmount == amountPayable)
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Red Bean Hoi An - Hùng Vương (6a1ba807396a1b2be58bfe58) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0158DAC428', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-27 03:00:00', '2026-05-27 12:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-05-27 00:00:00', '2026-05-27 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe58', 'red-bean-hoi-an-hung-vuong', 'Red Bean Hoi An - Hùng Vương', '2026-05-28', '11:00:00', 1, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 1, 2, 100000, 100000, '2026-05-27 00:00:00', '2026-05-27 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 100000, 'VND', 'MOMO', 'RB-0158DAC428-004E5B', 'MOMO1A6A51B615', '2026-05-27 03:00:00', 100000, '2026-05-27 12:00:00', '2026-05-27 00:00:00', '2026-05-27 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-8136124E12', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 200000, 200000, 200000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-14 04:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe58', 'red-bean-hoi-an-hung-vuong', 'Red Bean Hoi An - Hùng Vương', '2026-04-24', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 200000, 200000, '2026-04-14 00:00:00', '2026-04-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 200000, 'VND', 'MOMO', 'RB-8136124E12-14EFC2', NULL, NULL, NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-2BDF56BEAB', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 500000, 500000, 500000, 500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe58', 'red-bean-hoi-an-hung-vuong', 'Red Bean Hoi An - Hùng Vương', '2026-02-11', '12:00:00', 5, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn 6 VIP', 1, 6, 500000, 500000, '2026-02-09 00:00:00', '2026-02-09 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 500000, 'VND', 'MOMO', 'RB-2BDF56BEAB-611C1E', 'MOMOE8C8D4A34A', '2026-02-09 03:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-527552AC5D', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-10 12:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-01-10 00:00:00', '2026-01-10 12:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe58', 'red-bean-hoi-an-hung-vuong', 'Red Bean Hoi An - Hùng Vương', '2026-01-22', '12:30:00', 9, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'pr-10', 'Phòng riêng 10 khách', 1, 10, 1000000, 1000000, '2026-01-10 00:00:00', '2026-01-10 12:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'RB-527552AC5D-0183D9', NULL, NULL, NULL, NULL, '2026-01-10 00:00:00', '2026-01-10 12:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EB110C2526', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 200000, 200000, 200000, 200000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-14 04:00:00', '2026-01-14 11:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe58', 'red-bean-hoi-an-hung-vuong', 'Red Bean Hoi An - Hùng Vương', '2026-01-26', '18:00:00', 2, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2', 'Bàn 2', 2, 2, 100000, 200000, '2026-01-14 00:00:00', '2026-01-14 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 200000, 'VND', 'MOMO', 'RB-EB110C2526-9C2DC6', 'MOMOFE9D03AC28', '2026-01-14 04:00:00', 200000, '2026-01-14 11:00:00', '2026-01-14 00:00:00', '2026-01-14 11:00:00');

-- ==== The Temple Restaurant & Lounge - Hùng Vương (6a1ba807396a1b2be58bfe59) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E69BABB4AC', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'DEPOSIT', 250000, 250000, 250000, 250000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-14 01:00:00', '2026-02-14 05:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-02-24', '11:30:00', 3, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 250000, 250000, '2026-02-14 00:00:00', '2026-02-14 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 250000, 'VND', 'MOMO', 'RB-E69BABB4AC-6556E9', 'MOMO56BAEF21A2', '2026-02-14 01:00:00', 250000, '2026-02-14 05:00:00', '2026-02-14 00:00:00', '2026-02-14 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-1B69F49321', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 250000, 250000, 250000, 250000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-26 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-26 00:00:00', '2026-05-26 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-06-03', '12:00:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn 4 (Ngoài trời)', 1, 4, 250000, 250000, '2026-05-26 00:00:00', '2026-05-26 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 250000, 'VND', 'MOMO', 'RB-1B69F49321-02DCEE', 'MOMO4E65FAA065', '2026-05-26 01:00:00', NULL, NULL, '2026-05-26 00:00:00', '2026-05-26 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-D478C5EBF9', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 600000, 600000, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-05-31', '12:30:00', 5, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-6-vip', 'Bàn 6 VIP', 1, 6, 600000, 600000, '2026-05-20 00:00:00', '2026-05-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'RB-D478C5EBF9-AA4F23', 'MOMOE9237B6B60', '2026-05-20 04:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-5B27B239FA', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-01-18', '18:00:00', 1, 90, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-2-romantic', 'Bàn 2 (Romantic)', 2, 2, 150000, 300000, '2026-01-17 00:00:00', '2026-01-17 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-5B27B239FA-D10A49', 'MOMOCE9BA9A92C', '2026-01-17 01:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6185F83F7A', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 250000, 250000, 250000, 250000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-25 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-25 00:00:00', '2026-05-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-05-26', '18:30:00', 4, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4', 'Bàn 4', 1, 4, 250000, 250000, '2026-05-25 00:00:00', '2026-05-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 250000, 'VND', 'MOMO', 'RB-6185F83F7A-26C92E', 'MOMOE15F688393', '2026-05-25 04:00:00', NULL, NULL, '2026-05-25 00:00:00', '2026-05-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-E278B3D7A8', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 250000, 250000, 250000, 250000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a1ba807396a1b2be58bfe59', 'the-temple-restaurant-lounge-hung-vuong', 'The Temple Restaurant & Lounge - Hùng Vương', '2026-03-12', '19:00:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-outdoor', 'Bàn 4 (Ngoài trời)', 1, 4, 250000, 250000, '2026-03-03 00:00:00', '2026-03-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 250000, 'VND', 'MOMO', 'RB-E278B3D7A8-92C5AE', 'MOMO8539725D0B', '2026-03-03 03:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 03:00:00');

-- ==== Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An (6a47ac3593a0c40dc01724e8) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-EB6339B792', 9, 'Trần Gia Bảo', '0901234509', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-30 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-30 00:00:00', '2026-05-30 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-06-01', '12:00:00', 34, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-100-ballroom', 'Ballroom 80-100 khách', 1, 100, 1000000, 1000000, '2026-05-30 00:00:00', '2026-05-30 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1000000, 'VND', 'MOMO', 'RB-EB6339B792-50D86E', 'MOMO853953CE60', '2026-05-30 02:00:00', NULL, NULL, '2026-05-30 00:00:00', '2026-05-30 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-BCDF8E4C16', 10, 'Phạm Thu Hà', '0901234510', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-14 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-04-16', '12:30:00', 4, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-floor1', 'Bàn 4 người tầng 1', 1, 4, 100000, 100000, '2026-04-14 00:00:00', '2026-04-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-BCDF8E4C16-152AC5', 'MOMO5BF6444319', '2026-04-14 02:00:00', NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-9EFD2E5C9F', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'DEPOSIT', 300000, 300000, 300000, 300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-01-27', '18:00:00', 8, 90, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-vip-room', 'Phòng VIP 10 người', 1, 10, 300000, 300000, '2026-01-26 00:00:00', '2026-01-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 300000, 'VND', 'MOMO', 'RB-9EFD2E5C9F-4C35AD', 'MOMOEA2FD1AF02', '2026-01-26 02:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-0FCA11F72D', 12, 'Vũ Thị Mai', '0901234512', NULL, 'DEPOSIT', 2000000, 2000000, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-04-02', '18:30:00', 53, 180, 2);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-100-ballroom', 'Ballroom 80-100 khách', 2, 100, 1000000, 2000000, '2026-03-20 00:00:00', '2026-03-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'RB-0FCA11F72D-1F9B04', 'MOMO592E28931B', '2026-03-20 03:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-6527CD1721', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'DEPOSIT', 100000, 100000, 100000, 100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-23 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-23 00:00:00', '2026-04-23 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-05-01', '19:00:00', 3, 60, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-4-floor1', 'Bàn 4 người tầng 1', 1, 4, 100000, 100000, '2026-04-23 00:00:00', '2026-04-23 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 100000, 'VND', 'MOMO', 'RB-6527CD1721-331626', 'MOMOE9C71644F7', '2026-04-23 04:00:00', NULL, NULL, '2026-04-23 00:00:00', '2026-04-23 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-C14B5BAB0A', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'DEPOSIT', 300000, 300000, 300000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-23 13:00:00', 'Khách không hoàn tất đặt cọc trong thời hạn giữ bàn.', 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-03-03', '19:30:00', 7, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-10-vip-room', 'Phòng VIP 10 người', 1, 10, 300000, 300000, '2026-02-23 00:00:00', '2026-02-23 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 300000, 'VND', 'MOMO', 'RB-C14B5BAB0A-CBDFD1', NULL, NULL, NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('RB-314C20453D', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'DEPOSIT', 1000000, 1000000, 1000000, 1000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-06 03:00:00', '2026-01-06 02:00:00', 'Khách đổi kế hoạch, hủy trước giờ đặt bàn.', 1, 'MOMO_WALLET', '2026-01-06 00:00:00', '2026-01-06 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO restaurant_bookings (id, restaurant_id, restaurant_slug, restaurant_name, reservation_date, reservation_time, people, duration_minutes, tables_count)
VALUES (@bid, '6a47ac3593a0c40dc01724e8', 'ngon-pho-hoi-authentic-vietnamese-cuisine-hoi-an', 'Ngon Phố Hội - Ẩm thực Việt chính hiệu Hội An', '2026-01-09', '20:00:00', 38, 120, 1);
INSERT INTO booking_tables (restaurant_booking_id, table_type_id, table_type_name, quantity, seats, deposit_price, total_deposit, created_at, updated_at)
VALUES (@bid, 'tb-100-ballroom', 'Ballroom 80-100 khách', 1, 100, 1000000, 1000000, '2026-01-06 00:00:00', '2026-01-06 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1000000, 'VND', 'MOMO', 'RB-314C20453D-EF5CF2', 'MOMO211ECDF833', '2026-01-06 03:00:00', 1000000, '2026-01-06 02:00:00', '2026-01-06 00:00:00', '2026-01-06 02:00:00');
