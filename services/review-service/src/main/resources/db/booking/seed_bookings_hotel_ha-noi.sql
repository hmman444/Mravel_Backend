-- Seed demo bookings HOTEL cho khu vuc: ha-noi
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== MAY Hotel & Apartment (6a1f88172bd108416c46476b) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-84FB4B79', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 1650000, NULL, 1650000, 1650000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-10 02:00:00', '2026-03-11 00:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-03-10 00:00:00', '2026-03-11 00:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-03-23', '2026-03-26', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-standard-double', 'Standard Double', 'rt-may-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 3, 550000, 1650000, '2026-03-10 00:00:00', '2026-03-11 00:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1650000, 'VND', 'MOMO', 'BK-84FB4B79-1BBABD', 'MOMO28080B73FA', '2026-03-10 02:00:00', 1650000, '2026-03-11 00:00:00', '2026-03-10 00:00:00', '2026-03-11 00:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-BC45B182', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 680000, NULL, 680000, 680000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-14 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-14 00:00:00', '2026-04-14 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-05-11', '2026-05-12', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-superior-twin', 'Superior Twin', 'rt-may-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 1, 680000, 680000, '2026-04-14 00:00:00', '2026-04-14 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 680000, 'VND', 'MOMO', 'BK-BC45B182-21E000', 'MOMOEEA4D00F57', '2026-04-14 05:00:00', NULL, NULL, '2026-04-14 00:00:00', '2026-04-14 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2035A2C4', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 820000, NULL, 820000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-07 11:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 11:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-03-06', '2026-03-07', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-deluxe-queen', 'Deluxe Queen', 'rt-may-deluxe-queen-prepaid-nonref', 'Deluxe Queen - Không gồm bữa sáng', 1, 1, 820000, 820000, '2026-02-07 00:00:00', '2026-02-07 11:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 820000, 'VND', 'MOMO', 'BK-2035A2C4-9CCA2F', NULL, NULL, NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 11:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-391BBDBB', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 2940000, NULL, 2940000, 2940000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-02 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-02 00:00:00', '2026-04-02 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-04-05', '2026-04-08', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-studio-apartment', 'Căn hộ Studio', 'rt-may-studio-apartment-prepaid-nonref', 'Căn hộ Studio - Không gồm bữa sáng', 1, 3, 980000, 2940000, '2026-04-02 00:00:00', '2026-04-02 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2940000, 'VND', 'MOMO', 'BK-391BBDBB-92B5ED', 'MOMO49CD94E92A', '2026-04-02 03:00:00', NULL, NULL, '2026-04-02 00:00:00', '2026-04-02 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A6F85B1B', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 5000000, NULL, 5000000, 5000000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-04 01:00:00', '2026-03-05 00:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-03-04 00:00:00', '2026-03-05 00:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-03-13', '2026-03-17', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-one-bedroom-apartment', 'Căn hộ 1 phòng ngủ', 'rt-may-one-bedroom-apartment-prepaid-nonref', 'Căn hộ 1 phòng ngủ - Không gồm bữa sáng', 1, 4, 1250000, 5000000, '2026-03-04 00:00:00', '2026-03-05 00:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 5000000, 'VND', 'MOMO', 'BK-A6F85B1B-A57D34', 'MOMOF605CF35DA', '2026-03-04 01:00:00', 5000000, '2026-03-05 00:00:00', '2026-03-04 00:00:00', '2026-03-05 00:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-62100F14', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1100000, NULL, 1100000, 1100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-29 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-29 00:00:00', '2026-03-29 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-04-18', '2026-04-20', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-standard-double', 'Standard Double', 'rt-may-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 2, 550000, 1100000, '2026-03-29 00:00:00', '2026-03-29 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1100000, 'VND', 'MOMO', 'BK-62100F14-B4B1A6', 'MOMO2FB2555BFD', '2026-03-29 03:00:00', NULL, NULL, '2026-03-29 00:00:00', '2026-03-29 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D83D3C71', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 2720000, NULL, 2720000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-22 22:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-03-22 00:00:00', '2026-03-22 22:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88172bd108416c46476b', 'may-hotel-apartment', 'MAY Hotel & Apartment', '2026-04-12', '2026-04-16', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-may-superior-twin', 'Superior Twin', 'rt-may-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 4, 680000, 2720000, '2026-03-22 00:00:00', '2026-03-22 22:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 2720000, 'VND', 'MOMO', 'BK-D83D3C71-27BA76', NULL, NULL, NULL, NULL, '2026-03-22 00:00:00', '2026-03-22 22:00:00');

-- ==== Ha Noi Le Grand Hotel (6a1f88162bd108416c46476a) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F5C483A3', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 9315000, NULL, 9315000, 9315000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-11 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-11 00:00:00', '2026-04-11 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-05-02', '2026-05-05', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-family-room', 'Family Room', 'rt-hnlegrand-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 2, 3, 1552500, 9315000, '2026-04-11 00:00:00', '2026-04-11 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 9315000, 'VND', 'MOMO', 'BK-F5C483A3-83656C', 'MOMO4CADBF50DC', '2026-04-11 02:00:00', NULL, NULL, '2026-04-11 00:00:00', '2026-04-11 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-BB173757', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 805000, NULL, 805000, 805000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-24 00:00:00', '2025-12-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-01-19', '2026-01-20', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-standard-double', 'Standard Double', 'rt-hnlegrand-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 1, 805000, 805000, '2025-12-24 00:00:00', '2025-12-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 805000, 'VND', 'MOMO', 'BK-BB173757-8A0E9D', 'MOMOA7014A75AF', '2025-12-24 01:00:00', NULL, NULL, '2025-12-24 00:00:00', '2025-12-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6380611C', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 3910000, NULL, 3910000, 3910000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-25 00:00:00', '2025-12-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-01-22', '2026-01-26', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-superior-twin', 'Superior Twin', 'rt-hnlegrand-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 4, 977500, 3910000, '2025-12-25 00:00:00', '2025-12-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3910000, 'VND', 'MOMO', 'BK-6380611C-713122', 'MOMO6495D7BC1D', '2025-12-25 03:00:00', NULL, NULL, '2025-12-25 00:00:00', '2025-12-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6B8EA6F3', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 3622500, NULL, 3622500, 3622500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-07 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-07 00:00:00', '2026-02-07 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-02-28', '2026-03-03', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-deluxe-queen', 'Deluxe Queen', 'rt-hnlegrand-deluxe-queen-breakfast-flex', 'Deluxe Queen - Bao gồm bữa sáng', 1, 3, 1207500, 3622500, '2026-02-07 00:00:00', '2026-02-07 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3622500, 'VND', 'MOMO', 'BK-6B8EA6F3-981716', 'MOMOA2EC47C395', '2026-02-07 05:00:00', NULL, NULL, '2026-02-07 00:00:00', '2026-02-07 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-3A3C0EB3', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1552500, NULL, 1552500, 1552500, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-31 04:00:00', '2026-01-31 07:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 07:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-02-04', '2026-02-05', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-family-room', 'Family Room', 'rt-hnlegrand-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 1, 1552500, 1552500, '2026-01-31 00:00:00', '2026-01-31 07:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1552500, 'VND', 'MOMO', 'BK-3A3C0EB3-C3D3CD', 'MOMOB93F2D78CE', '2026-01-31 04:00:00', 1552500, '2026-01-31 07:00:00', '2026-01-31 00:00:00', '2026-01-31 07:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AB508E35', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 1610000, NULL, 1610000, 1610000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-02-01', '2026-02-03', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-standard-double', 'Standard Double', 'rt-hnlegrand-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 2, 805000, 1610000, '2026-01-26 00:00:00', '2026-01-26 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1610000, 'VND', 'MOMO', 'BK-AB508E35-302AD1', 'MOMOD1CC0068D8', '2026-01-26 04:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B8EAC0E3', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 5865000, NULL, 5865000, 5865000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-10 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-10 00:00:00', '2026-04-10 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-04-30', '2026-05-03', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-superior-twin', 'Superior Twin', 'rt-hnlegrand-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 2, 3, 977500, 5865000, '2026-04-10 00:00:00', '2026-04-10 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5865000, 'VND', 'MOMO', 'BK-B8EAC0E3-72F364', 'MOMO9700C020AA', '2026-04-10 05:00:00', NULL, NULL, '2026-04-10 00:00:00', '2026-04-10 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-55C3E432', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 4830000, NULL, 4830000, 4830000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-17 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88162bd108416c46476a', 'ha-noi-le-grand-hotel', 'Ha Noi Le Grand Hotel', '2026-04-20', '2026-04-24', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hnlegrand-deluxe-queen', 'Deluxe Queen', 'rt-hnlegrand-deluxe-queen-breakfast-flex', 'Deluxe Queen - Bao gồm bữa sáng', 1, 4, 1207500, 4830000, '2026-04-17 00:00:00', '2026-04-17 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4830000, 'VND', 'MOMO', 'BK-55C3E432-07FB17', 'MOMO64CB771912', '2026-04-17 02:00:00', NULL, NULL, '2026-04-17 00:00:00', '2026-04-17 02:00:00');

-- ==== Millennium Hanoi Hotel (6a1f881c2bd108416c46476d) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-45A6227A', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 1600000, NULL, 1600000, 1600000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-01 03:00:00', '2026-01-01 20:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-01-01 00:00:00', '2026-01-01 20:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881c2bd108416c46476d', 'millennium-hanoi-hotel', 'Millennium Hanoi Hotel', '2026-01-26', '2026-01-28', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-millennium-standard-double', 'Standard Double', 'rt-millennium-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 2, 800000, 1600000, '2026-01-01 00:00:00', '2026-01-01 20:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1600000, 'VND', 'MOMO', 'BK-45A6227A-1FA7AA', 'MOMO0F3AE26FFD', '2026-01-01 03:00:00', 1600000, '2026-01-01 20:00:00', '2026-01-01 00:00:00', '2026-01-01 20:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-E726D43D', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 1900000, NULL, 1900000, 1900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-18 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-18 00:00:00', '2026-04-18 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881c2bd108416c46476d', 'millennium-hanoi-hotel', 'Millennium Hanoi Hotel', '2026-05-17', '2026-05-19', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-millennium-superior-twin', 'Superior Twin', 'rt-millennium-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 2, 950000, 1900000, '2026-04-18 00:00:00', '2026-04-18 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1900000, 'VND', 'MOMO', 'BK-E726D43D-DC6BDC', 'MOMOCC51BC5A31', '2026-04-18 02:00:00', NULL, NULL, '2026-04-18 00:00:00', '2026-04-18 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D07223F9', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 3450000, NULL, 3450000, 3450000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-28 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-28 00:00:00', '2026-04-28 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881c2bd108416c46476d', 'millennium-hanoi-hotel', 'Millennium Hanoi Hotel', '2026-05-18', '2026-05-21', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-millennium-deluxe-double', 'Deluxe Double', 'rt-millennium-deluxe-double-prepaid-nonref', 'Deluxe Double - Không gồm bữa sáng', 1, 3, 1150000, 3450000, '2026-04-28 00:00:00', '2026-04-28 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3450000, 'VND', 'MOMO', 'BK-D07223F9-51B554', 'MOMOC57726BFF1', '2026-04-28 01:00:00', NULL, NULL, '2026-04-28 00:00:00', '2026-04-28 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7C0CEE98', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 4500000, NULL, 4500000, 4500000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881c2bd108416c46476d', 'millennium-hanoi-hotel', 'Millennium Hanoi Hotel', '2026-02-10', '2026-02-13', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-millennium-family-room', 'Family Room', 'rt-millennium-family-room-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 3, 1500000, 4500000, '2026-01-26 00:00:00', '2026-01-26 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4500000, 'VND', 'MOMO', 'BK-7C0CEE98-DC5889', 'MOMO4537041987', '2026-01-26 01:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DB0E4DE7', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 1600000, NULL, 1600000, 1600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-18 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-18 00:00:00', '2026-05-18 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881c2bd108416c46476d', 'millennium-hanoi-hotel', 'Millennium Hanoi Hotel', '2026-05-30', '2026-06-01', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-millennium-standard-double', 'Standard Double', 'rt-millennium-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 2, 800000, 1600000, '2026-05-18 00:00:00', '2026-05-18 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1600000, 'VND', 'MOMO', 'BK-DB0E4DE7-48026B', 'MOMO554C2E31A6', '2026-05-18 05:00:00', NULL, NULL, '2026-05-18 00:00:00', '2026-05-18 05:00:00');

-- ==== Lotus Village Hotel Hà Nội (6a1f88182bd108416c46476c) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DFB3E84F', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 3588000, NULL, 3588000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-19 18:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-04-17', '2026-04-21', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-superior-twin', 'Superior Twin', 'rt-lotus-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 4, 897000, 3588000, '2026-03-19 00:00:00', '2026-03-19 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 3588000, 'VND', 'MOMO', 'BK-DFB3E84F-EB983D', NULL, NULL, NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 18:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7960DBED', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 4370000, NULL, 4370000, 4370000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-10 00:00:00', '2026-01-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-01-27', '2026-01-31', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-deluxe-queen', 'Deluxe Queen', 'rt-lotus-deluxe-queen-breakfast-flex', 'Deluxe Queen - Bao gồm bữa sáng', 1, 4, 1092500, 4370000, '2026-01-10 00:00:00', '2026-01-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4370000, 'VND', 'MOMO', 'BK-7960DBED-09CF7A', 'MOMO49AC54A9B0', '2026-01-10 01:00:00', NULL, NULL, '2026-01-10 00:00:00', '2026-01-10 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1BE4C91E', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 5750000, NULL, 5750000, 5750000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-27 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-27 00:00:00', '2026-05-27 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-06-06', '2026-06-10', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-family-room', 'Family Room', 'rt-lotus-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 4, 1437500, 5750000, '2026-05-27 00:00:00', '2026-05-27 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5750000, 'VND', 'MOMO', 'BK-1BE4C91E-611AEB', 'MOMOB9CD400B0F', '2026-05-27 04:00:00', NULL, NULL, '2026-05-27 00:00:00', '2026-05-27 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AACC3576', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 1495000, NULL, 1495000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-01 13:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-01 00:00:00', '2026-02-01 13:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-02-11', '2026-02-13', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-standard-double', 'Standard Double', 'rt-lotus-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 2, 747500, 1495000, '2026-02-01 00:00:00', '2026-02-01 13:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1495000, 'VND', 'MOMO', 'BK-AACC3576-271A72', NULL, NULL, NULL, NULL, '2026-02-01 00:00:00', '2026-02-01 13:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-20193187', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 2691000, NULL, 2691000, 2691000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-03-20', '2026-03-23', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-superior-twin', 'Superior Twin', 'rt-lotus-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 3, 897000, 2691000, '2026-02-23 00:00:00', '2026-02-23 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2691000, 'VND', 'MOMO', 'BK-20193187-025873', 'MOMOBAD277F267', '2026-02-23 01:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-CDFCBAA0', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 1092500, NULL, 1092500, 1092500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-15 00:00:00', '2026-04-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f88182bd108416c46476c', 'lotus-village-hotel-hanoi', 'Lotus Village Hotel Hà Nội', '2026-04-26', '2026-04-27', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lotus-deluxe-queen', 'Deluxe Queen', 'rt-lotus-deluxe-queen-breakfast-flex', 'Deluxe Queen - Bao gồm bữa sáng', 1, 1, 1092500, 1092500, '2026-04-15 00:00:00', '2026-04-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1092500, 'VND', 'MOMO', 'BK-CDFCBAA0-711FBB', 'MOMOD03008151C', '2026-04-15 02:00:00', NULL, NULL, '2026-04-15 00:00:00', '2026-04-15 02:00:00');

-- ==== Nature Hotel - Lac Long Quan (6a1f881d2bd108416c46476e) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-E071477B', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 12800000, NULL, 12800000, 12800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-09 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-09 00:00:00', '2026-03-09 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-04-06', '2026-04-10', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-deluxe-queen-lakeview', 'Deluxe Queen Lake View', 'rt-hn-nature-llq-deluxe-queen-lakeview-prepaid-nonref', 'Deluxe Queen Lake View - Không gồm bữa sáng', 2, 4, 1600000, 12800000, '2026-03-09 00:00:00', '2026-03-09 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 12800000, 'VND', 'MOMO', 'BK-E071477B-74F2A4', 'MOMO11A9BA0073', '2026-03-09 05:00:00', NULL, NULL, '2026-03-09 00:00:00', '2026-03-09 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-55C6C83E', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 4200000, NULL, 4200000, 4200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-03-05', '2026-03-07', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-family-room', 'Family Room', 'rt-hn-nature-llq-family-room-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 2, 2100000, 4200000, '2026-02-10 00:00:00', '2026-02-10 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4200000, 'VND', 'MOMO', 'BK-55C6C83E-5C7DE5', 'MOMOAB24F3507E', '2026-02-10 04:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6E14C849', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 2000000, NULL, 2000000, 2000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-07 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-07 00:00:00', '2026-01-07 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-02-02', '2026-02-04', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-standard-double', 'Standard Double', 'rt-hn-nature-llq-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 2, 1000000, 2000000, '2026-01-07 00:00:00', '2026-01-07 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2000000, 'VND', 'MOMO', 'BK-6E14C849-14AD95', 'MOMO09AB7B3E58', '2026-01-07 05:00:00', NULL, NULL, '2026-01-07 00:00:00', '2026-01-07 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D44C6F66', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 1250000, NULL, 1250000, 1250000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-15 00:00:00', '2026-01-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-01-25', '2026-01-26', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-superior-twin', 'Superior Twin', 'rt-hn-nature-llq-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 1, 1250000, 1250000, '2026-01-15 00:00:00', '2026-01-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1250000, 'VND', 'MOMO', 'BK-D44C6F66-B6141C', 'MOMO0F51EC276A', '2026-01-15 04:00:00', NULL, NULL, '2026-01-15 00:00:00', '2026-01-15 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A8E12E42', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3200000, NULL, 3200000, 3200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-02 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-02-09', '2026-02-11', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-deluxe-queen-lakeview', 'Deluxe Queen Lake View', 'rt-hn-nature-llq-deluxe-queen-lakeview-prepaid-nonref', 'Deluxe Queen Lake View - Không gồm bữa sáng', 1, 2, 1600000, 3200000, '2026-02-02 00:00:00', '2026-02-02 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3200000, 'VND', 'MOMO', 'BK-A8E12E42-E704BA', 'MOMOCEE1EF6FE0', '2026-02-02 04:00:00', NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D98F20C6', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 2100000, NULL, 2100000, 2100000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-04-02', '2026-04-03', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-family-room', 'Family Room', 'rt-hn-nature-llq-family-room-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 1, 2100000, 2100000, '2026-03-28 00:00:00', '2026-03-28 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2100000, 'VND', 'MOMO', 'BK-D98F20C6-9A2D77', 'MOMO48ACD1550D', '2026-03-28 04:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-4A6C4A74', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 4000000, NULL, 4000000, 4000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-11 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-11 00:00:00', '2026-01-11 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f881d2bd108416c46476e', 'nature-hotel-lac-long-quan', 'Nature Hotel - Lac Long Quan', '2026-01-15', '2026-01-19', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hn-nature-llq-standard-double', 'Standard Double', 'rt-hn-nature-llq-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 4, 1000000, 4000000, '2026-01-11 00:00:00', '2026-01-11 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4000000, 'VND', 'MOMO', 'BK-4A6C4A74-A745B7', 'MOMO813388C132', '2026-01-11 03:00:00', NULL, NULL, '2026-01-11 00:00:00', '2026-01-11 03:00:00');
