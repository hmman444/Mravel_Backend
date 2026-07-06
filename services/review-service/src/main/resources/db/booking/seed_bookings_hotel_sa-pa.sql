-- Seed demo bookings HOTEL cho khu vuc: sa-pa
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== The Gate Boutique Hotel Sapa (6a1f95d5b0f2cc2834a9446e) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-35EC8BDB', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 805000, NULL, 805000, 805000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-01-30', '2026-01-31', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-standard-double', 'Standard Double', 'rt-spgate-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 1, 805000, 805000, '2026-01-14 00:00:00', '2026-01-14 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 805000, 'VND', 'MOMO', 'BK-35EC8BDB-39CB5D', 'MOMOD175BCFA88', '2026-01-14 01:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-BC4FD6E7', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 943000, NULL, 943000, 943000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-11 00:00:00', '2026-02-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-02-18', '2026-02-19', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-superior-twin', 'Superior Twin', 'rt-spgate-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 1, 943000, 943000, '2026-02-11 00:00:00', '2026-02-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 943000, 'VND', 'MOMO', 'BK-BC4FD6E7-C7A1BC', 'MOMOAFA363D6E9', '2026-02-11 01:00:00', NULL, NULL, '2026-02-11 00:00:00', '2026-02-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-4268F8EF', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 3381000, NULL, 3381000, 3381000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-15 04:00:00', '2026-04-15 03:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-05-03', '2026-05-06', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-deluxe-double', 'Deluxe Double', 'rt-spgate-deluxe-double-breakfast-flex', 'Deluxe Double - Bao gồm bữa sáng', 1, 3, 1127000, 3381000, '2026-04-15 00:00:00', '2026-04-15 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 3381000, 'VND', 'MOMO', 'BK-4268F8EF-28A707', 'MOMO5068BA63DD', '2026-04-15 04:00:00', 3381000, '2026-04-15 03:00:00', '2026-04-15 00:00:00', '2026-04-15 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-29D144D2', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 3967500, NULL, 3967500, 3967500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-01 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-01 00:00:00', '2026-03-01 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-03-07', '2026-03-10', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-deluxe-mountain-view', 'Deluxe Mountain View', 'rt-spgate-deluxe-mountain-view-breakfast-flex', 'Deluxe Mountain View - Bao gồm bữa sáng', 1, 3, 1322500, 3967500, '2026-03-01 00:00:00', '2026-03-01 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3967500, 'VND', 'MOMO', 'BK-29D144D2-C8DBFA', 'MOMO3C0BB66C1F', '2026-03-01 04:00:00', NULL, NULL, '2026-03-01 00:00:00', '2026-03-01 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9AA97231', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 3174000, NULL, 3174000, 3174000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-05-07', '2026-05-09', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-family-room', 'Family Room', 'rt-spgate-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1587000, 3174000, '2026-04-16 00:00:00', '2026-04-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3174000, 'VND', 'MOMO', 'BK-9AA97231-75AF15', 'MOMO8C359E64BF', '2026-04-16 02:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-89F81822', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 3220000, NULL, 3220000, 3220000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-07 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-07 00:00:00', '2026-01-07 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d5b0f2cc2834a9446e', 'the-gate-boutique-hotel-sapa', 'The Gate Boutique Hotel Sapa', '2026-01-13', '2026-01-17', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spgate-standard-double', 'Standard Double', 'rt-spgate-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 4, 805000, 3220000, '2026-01-07 00:00:00', '2026-01-07 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3220000, 'VND', 'MOMO', 'BK-89F81822-AE6DC3', 'MOMO23636CDA1C', '2026-01-07 03:00:00', NULL, NULL, '2026-01-07 00:00:00', '2026-01-07 03:00:00');

-- ==== Sapa Clover Hotel (6a1f95d8b0f2cc2834a94470) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-E1A60125', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 1500000, NULL, 1500000, 1500000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-16 04:00:00', '2026-01-16 18:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-01-16 00:00:00', '2026-01-16 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-01-25', '2026-01-27', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-superior-twin', 'Superior Twin', 'rt-spclover-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 2, 750000, 1500000, '2026-01-16 00:00:00', '2026-01-16 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1500000, 'VND', 'MOMO', 'BK-E1A60125-B0E8B6', 'MOMO5C2E5A6614', '2026-01-16 04:00:00', 1500000, '2026-01-16 18:00:00', '2026-01-16 00:00:00', '2026-01-16 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-882602B8', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 1800000, NULL, 1800000, 1800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-01-21', '2026-01-23', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-deluxe-double', 'Deluxe Double', 'rt-spclover-deluxe-double-prepaid-nonref', 'Deluxe Double - Không gồm bữa sáng', 1, 2, 900000, 1800000, '2026-01-14 00:00:00', '2026-01-14 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1800000, 'VND', 'MOMO', 'BK-882602B8-6658DD', 'MOMO9EACBF1E53', '2026-01-14 04:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D0FC7B3C', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1050000, NULL, 1050000, 1050000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-29 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-29 00:00:00', '2025-12-29 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-01-23', '2026-01-24', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-deluxe-mountain-view', 'Deluxe Mountain View', 'rt-spclover-deluxe-mountain-view-prepaid-nonref', 'Deluxe Mountain View - Không gồm bữa sáng', 1, 1, 1050000, 1050000, '2025-12-29 00:00:00', '2025-12-29 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1050000, 'VND', 'MOMO', 'BK-D0FC7B3C-797802', 'MOMOF44FCFCDAA', '2025-12-29 03:00:00', NULL, NULL, '2025-12-29 00:00:00', '2025-12-29 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D41EBBC4', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 5000000, NULL, 5000000, 5000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-02-01', '2026-02-05', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-family-room', 'Family Room', 'rt-spclover-family-room-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 4, 1250000, 5000000, '2026-01-15 00:00:00', '2026-01-15 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5000000, 'VND', 'MOMO', 'BK-D41EBBC4-736A5C', 'MOMOD3890F90A4', '2026-01-15 05:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1FA85C8A', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 2400000, NULL, 2400000, 2400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-01-24', '2026-01-28', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-standard-double', 'Standard Double', 'rt-spclover-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 4, 600000, 2400000, '2026-01-15 00:00:00', '2026-01-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2400000, 'VND', 'MOMO', 'BK-1FA85C8A-189A19', 'MOMO8E24DC1A43', '2026-01-15 04:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7F14F9CC', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 750000, NULL, 750000, 750000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-05 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-05 00:00:00', '2026-05-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-05-10', '2026-05-11', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-superior-twin', 'Superior Twin', 'rt-spclover-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 1, 750000, 750000, '2026-05-05 00:00:00', '2026-05-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 750000, 'VND', 'MOMO', 'BK-7F14F9CC-F465C5', 'MOMO76B047B5B2', '2026-05-05 02:00:00', NULL, NULL, '2026-05-05 00:00:00', '2026-05-05 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D1C942C2', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 900000, NULL, 900000, 900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-19 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-19 00:00:00', '2026-04-19 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a94470', 'sapa-clover-hotel', 'Sapa Clover Hotel', '2026-05-05', '2026-05-06', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spclover-deluxe-double', 'Deluxe Double', 'rt-spclover-deluxe-double-prepaid-nonref', 'Deluxe Double - Không gồm bữa sáng', 1, 1, 900000, 900000, '2026-04-19 00:00:00', '2026-04-19 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 900000, 'VND', 'MOMO', 'BK-D1C942C2-DF24CC', 'MOMO4D61326BBB', '2026-04-19 01:00:00', NULL, NULL, '2026-04-19 00:00:00', '2026-04-19 01:00:00');

-- ==== Nomadtrails Boutique Hotel (6a1f95ddb0f2cc2834a94471) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AD1D0602', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 1322500, NULL, 1322500, 1322500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-22 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-22 00:00:00', '2026-04-22 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-05-02', '2026-05-03', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-deluxe-mountain-view', 'Deluxe Mountain View', 'rt-spnomadtrails-deluxe-mountain-view-breakfast-flex', 'Deluxe Mountain View - Bao gồm bữa sáng', 1, 1, 1322500, 1322500, '2026-04-22 00:00:00', '2026-04-22 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1322500, 'VND', 'MOMO', 'BK-AD1D0602-ED0759', 'MOMOFEFFCF06C7', '2026-04-22 02:00:00', NULL, NULL, '2026-04-22 00:00:00', '2026-04-22 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-5BAE157F', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 10120000, NULL, 10120000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2025-12-20 17:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2025-12-20 00:00:00', '2025-12-20 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-01-14', '2026-01-18', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-deluxe-twin', 'Deluxe Twin', 'rt-spnomadtrails-deluxe-twin-breakfast-flex', 'Deluxe Twin - Bao gồm bữa sáng', 2, 4, 1265000, 10120000, '2025-12-20 00:00:00', '2025-12-20 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 10120000, 'VND', 'MOMO', 'BK-5BAE157F-A04679', NULL, NULL, NULL, NULL, '2025-12-20 00:00:00', '2025-12-20 17:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-456BFB76', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 3450000, NULL, 3450000, 3450000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-04 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-04 00:00:00', '2026-05-04 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-05-31', '2026-06-02', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-family-room', 'Family Room', 'rt-spnomadtrails-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1725000, 3450000, '2026-05-04 00:00:00', '2026-05-04 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3450000, 'VND', 'MOMO', 'BK-456BFB76-D54677', 'MOMO2F403F7736', '2026-05-04 04:00:00', NULL, NULL, '2026-05-04 00:00:00', '2026-05-04 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B48531D7', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 2415000, NULL, 2415000, 2415000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-13 00:00:00', '2026-03-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-04-07', '2026-04-10', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-standard-double', 'Standard Double', 'rt-spnomadtrails-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 3, 805000, 2415000, '2026-03-13 00:00:00', '2026-03-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2415000, 'VND', 'MOMO', 'BK-B48531D7-C698A6', 'MOMO1BD228362A', '2026-03-13 02:00:00', NULL, NULL, '2026-03-13 00:00:00', '2026-03-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1645101F', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3105000, NULL, 3105000, 3105000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-04-06', '2026-04-09', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-superior-double', 'Superior Double', 'rt-spnomadtrails-superior-double-breakfast-flex', 'Superior Double - Bao gồm bữa sáng', 1, 3, 1035000, 3105000, '2026-03-27 00:00:00', '2026-03-27 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3105000, 'VND', 'MOMO', 'BK-1645101F-8615D6', 'MOMOCE487AEDE5', '2026-03-27 05:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B2EB2588', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 3967500, NULL, 3967500, 3967500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-05 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-05 00:00:00', '2026-01-05 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-01-15', '2026-01-18', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-deluxe-mountain-view', 'Deluxe Mountain View', 'rt-spnomadtrails-deluxe-mountain-view-breakfast-flex', 'Deluxe Mountain View - Bao gồm bữa sáng', 1, 3, 1322500, 3967500, '2026-01-05 00:00:00', '2026-01-05 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3967500, 'VND', 'MOMO', 'BK-B2EB2588-45705D', 'MOMO8A31AF8FA9', '2026-01-05 02:00:00', NULL, NULL, '2026-01-05 00:00:00', '2026-01-05 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-760213CF', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 5060000, NULL, 5060000, 5060000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-15 01:00:00', '2026-01-15 17:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 17:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-01-27', '2026-01-31', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-deluxe-twin', 'Deluxe Twin', 'rt-spnomadtrails-deluxe-twin-breakfast-flex', 'Deluxe Twin - Bao gồm bữa sáng', 1, 4, 1265000, 5060000, '2026-01-15 00:00:00', '2026-01-15 17:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 5060000, 'VND', 'MOMO', 'BK-760213CF-6A4201', 'MOMO9E8BFB1067', '2026-01-15 01:00:00', 5060000, '2026-01-15 17:00:00', '2026-01-15 00:00:00', '2026-01-15 17:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-0178C8A5', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 3450000, NULL, 3450000, 3450000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95ddb0f2cc2834a94471', 'nomadtrails-boutique-hotel', 'Nomadtrails Boutique Hotel', '2026-03-11', '2026-03-13', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-spnomadtrails-family-room', 'Family Room', 'rt-spnomadtrails-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1725000, 3450000, '2026-02-16 00:00:00', '2026-02-16 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3450000, 'VND', 'MOMO', 'BK-0178C8A5-C07A2D', 'MOMO082EE8BD42', '2026-02-16 05:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 05:00:00');

-- ==== Misty Hostel Sapa (6a1f95d8b0f2cc2834a9446f) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DF07204D', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 2720000, NULL, 2720000, 2720000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a9446f', 'misty-hostel-sapa', 'Misty Hostel Sapa', '2026-04-10', '2026-04-12', 2, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-misty-deluxe-mountain-view', 'Phòng Deluxe Hướng Núi', 'rt-misty-deluxe-mountain-view-prepaid-nonref', 'Phòng Deluxe Hướng Núi - Không gồm bữa sáng', 2, 2, 680000, 2720000, '2026-04-06 00:00:00', '2026-04-06 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2720000, 'VND', 'MOMO', 'BK-DF07204D-FCE4A9', 'MOMO562F4285C5', '2026-04-06 04:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-836745B6', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 820000, NULL, 820000, 820000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-24 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-24 00:00:00', '2026-03-24 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a9446f', 'misty-hostel-sapa', 'Misty Hostel Sapa', '2026-04-01', '2026-04-02', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-misty-family', 'Phòng Gia Đình', 'rt-misty-family-prepaid-nonref', 'Phòng Gia Đình - Không gồm bữa sáng', 1, 1, 820000, 820000, '2026-03-24 00:00:00', '2026-03-24 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 820000, 'VND', 'MOMO', 'BK-836745B6-0CED5C', 'MOMO7CD7BD0509', '2026-03-24 05:00:00', NULL, NULL, '2026-03-24 00:00:00', '2026-03-24 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-FA18D152', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 250000, NULL, 250000, 250000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a9446f', 'misty-hostel-sapa', 'Misty Hostel Sapa', '2026-05-02', '2026-05-03', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-misty-dorm-bed', 'Giường tầng phòng tập thể', 'rt-misty-dorm-bed-prepaid-nonref', 'Giường tầng phòng tập thể - Không gồm bữa sáng', 1, 1, 250000, 250000, '2026-04-15 00:00:00', '2026-04-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 250000, 'VND', 'MOMO', 'BK-FA18D152-58A1FA', 'MOMOF1118CA113', '2026-04-15 02:00:00', NULL, NULL, '2026-04-15 00:00:00', '2026-04-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6F88D7A7', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 1680000, NULL, 1680000, 1680000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a9446f', 'misty-hostel-sapa', 'Misty Hostel Sapa', '2026-03-01', '2026-03-05', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-misty-standard-double', 'Phòng Standard Giường Đôi', 'rt-misty-standard-double-prepaid-nonref', 'Phòng Standard Giường Đôi - Không gồm bữa sáng', 1, 4, 420000, 1680000, '2026-02-10 00:00:00', '2026-02-10 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1680000, 'VND', 'MOMO', 'BK-6F88D7A7-1C0A4D', 'MOMO2DF5A1F864', '2026-02-10 02:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-5B4A372A', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 1040000, NULL, 1040000, 1040000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-04 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-04 00:00:00', '2026-03-04 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95d8b0f2cc2834a9446f', 'misty-hostel-sapa', 'Misty Hostel Sapa', '2026-03-07', '2026-03-09', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-misty-superior-twin', 'Phòng Superior 2 Giường Đơn', 'rt-misty-superior-twin-prepaid-nonref', 'Phòng Superior 2 Giường Đơn - Không gồm bữa sáng', 1, 2, 520000, 1040000, '2026-03-04 00:00:00', '2026-03-04 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1040000, 'VND', 'MOMO', 'BK-5B4A372A-C848D2', 'MOMO2231BE189D', '2026-03-04 04:00:00', NULL, NULL, '2026-03-04 00:00:00', '2026-03-04 04:00:00');

-- ==== DeLaSol Sapa Hotel (6a1f95f3b0f2cc2834a94472) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D5091F5E', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 7590000, NULL, 7590000, 7590000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-08 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-08 00:00:00', '2026-01-08 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-01-16', '2026-01-19', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-family-suite', 'Family Suite', 'rt-delasol-family-suite-payhotel-flex', 'Family Suite - Bao gồm bữa sáng', 1, 3, 2530000, 7590000, '2026-01-08 00:00:00', '2026-01-08 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 7590000, 'VND', 'MOMO', 'BK-D5091F5E-80592A', 'MOMOD274189488', '2026-01-08 02:00:00', NULL, NULL, '2026-01-08 00:00:00', '2026-01-08 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-BDEBAD02', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 6900000, NULL, 6900000, 6900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-14 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-02-17', '2026-02-20', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-standard-double', 'Standard Double', 'rt-delasol-standard-double-payhotel-flex', 'Standard Double - Bao gồm bữa sáng', 2, 3, 1150000, 6900000, '2026-02-14 00:00:00', '2026-02-14 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6900000, 'VND', 'MOMO', 'BK-BDEBAD02-CFC21B', 'MOMOF88C01D252', '2026-02-14 03:00:00', NULL, NULL, '2026-02-14 00:00:00', '2026-02-14 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AD3744A9', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 5750000, NULL, 5750000, 5750000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-12 00:00:00', '2026-05-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-06-05', '2026-06-09', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-superior-double', 'Superior Double', 'rt-delasol-superior-double-payhotel-flex', 'Superior Double - Bao gồm bữa sáng', 1, 4, 1437500, 5750000, '2026-05-12 00:00:00', '2026-05-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5750000, 'VND', 'MOMO', 'BK-AD3744A9-18E4C4', 'MOMO20CB34B732', '2026-05-12 03:00:00', NULL, NULL, '2026-05-12 00:00:00', '2026-05-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F9F350EE', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 1840000, NULL, 1840000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-09 06:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 06:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-02-07', '2026-02-08', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-deluxe-mountain-view', 'Deluxe Mountain View', 'rt-delasol-deluxe-mountain-view-payhotel-flex', 'Deluxe Mountain View - Bao gồm bữa sáng', 1, 1, 1840000, 1840000, '2026-01-09 00:00:00', '2026-01-09 06:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1840000, 'VND', 'MOMO', 'BK-F9F350EE-C71EE2', NULL, NULL, NULL, NULL, '2026-01-09 00:00:00', '2026-01-09 06:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-CA4C5830', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 7360000, NULL, 7360000, 7360000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-02-05', '2026-02-09', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-deluxe-twin', 'Deluxe Twin', 'rt-delasol-deluxe-twin-payhotel-flex', 'Deluxe Twin - Không gồm bữa sáng (linh hoạt)', 1, 4, 1840000, 7360000, '2026-01-17 00:00:00', '2026-01-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 7360000, 'VND', 'MOMO', 'BK-CA4C5830-F093A5', 'MOMO661603CB0D', '2026-01-17 04:00:00', NULL, NULL, '2026-01-17 00:00:00', '2026-01-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-139D7804', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 2530000, NULL, 2530000, 2530000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-01 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f95f3b0f2cc2834a94472', 'delasol-sapa-hotel', 'DeLaSol Sapa Hotel', '2026-02-26', '2026-02-27', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-delasol-family-suite', 'Family Suite', 'rt-delasol-family-suite-payhotel-flex', 'Family Suite - Bao gồm bữa sáng', 1, 1, 2530000, 2530000, '2026-02-01 00:00:00', '2026-02-01 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2530000, 'VND', 'MOMO', 'BK-139D7804-5237B1', 'MOMOEFE2BFD9BA', '2026-02-01 03:00:00', NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 03:00:00');
