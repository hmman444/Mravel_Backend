-- Seed demo bookings HOTEL cho khu vuc: ho-chi-minh-city
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Meraki Boutique Hotel (6a1f856a5ccffa0ac26a6431) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-17316796', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 2932500, NULL, 2932500, 2932500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-03-05', '2026-03-08', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-superior-double', 'Superior Double', 'rt-meraki-superior-double-breakfast-flex', 'Superior Double - Bao gồm bữa sáng', 1, 3, 977500, 2932500, '2026-02-13 00:00:00', '2026-02-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2932500, 'VND', 'MOMO', 'BK-17316796-26694B', 'MOMOC65EA8EA95', '2026-02-13 02:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1DE7C481', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 3450000, NULL, 3450000, 3450000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-24 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-24 00:00:00', '2026-01-24 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-02-13', '2026-02-16', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-deluxe-double', 'Deluxe Double', 'rt-meraki-deluxe-double-breakfast-flex', 'Deluxe Double - Bao gồm bữa sáng', 1, 3, 1150000, 3450000, '2026-01-24 00:00:00', '2026-01-24 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3450000, 'VND', 'MOMO', 'BK-1DE7C481-01DF3F', 'MOMO61951A78D9', '2026-01-24 01:00:00', NULL, NULL, '2026-01-24 00:00:00', '2026-01-24 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F7214A20', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 1495000, NULL, 1495000, 1495000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-03 00:00:00', '2026-02-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-02-07', '2026-02-08', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-family-room', 'Family Room', 'rt-meraki-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 1, 1495000, 1495000, '2026-02-03 00:00:00', '2026-02-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1495000, 'VND', 'MOMO', 'BK-F7214A20-8E6B60', 'MOMO30C64C05AC', '2026-02-03 01:00:00', NULL, NULL, '2026-02-03 00:00:00', '2026-02-03 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-FFF1596B', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 6440000, NULL, 6440000, 6440000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-02-07', '2026-02-11', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-standard-double', 'Standard Double', 'rt-meraki-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 2, 4, 805000, 6440000, '2026-01-12 00:00:00', '2026-01-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6440000, 'VND', 'MOMO', 'BK-FFF1596B-ED657C', 'MOMO2BE5A0BA61', '2026-01-12 03:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9958171D', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 2932500, NULL, 2932500, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-02 01:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-02-17', '2026-02-20', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-superior-double', 'Superior Double', 'rt-meraki-superior-double-breakfast-flex', 'Superior Double - Bao gồm bữa sáng', 1, 3, 977500, 2932500, '2026-02-02 00:00:00', '2026-02-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 2932500, 'VND', 'MOMO', 'BK-9958171D-0DB2A9', NULL, NULL, NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2AA42340', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 4600000, NULL, 4600000, 4600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856a5ccffa0ac26a6431', 'meraki-boutique-hotel', 'Meraki Boutique Hotel', '2026-04-09', '2026-04-13', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-meraki-deluxe-double', 'Deluxe Double', 'rt-meraki-deluxe-double-breakfast-flex', 'Deluxe Double - Bao gồm bữa sáng', 1, 4, 1150000, 4600000, '2026-03-20 00:00:00', '2026-03-20 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4600000, 'VND', 'MOMO', 'BK-2AA42340-EC875C', 'MOMO79859E4C6A', '2026-03-20 03:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 03:00:00');

-- ==== Cozrum Homes - Selina Residence (6a1f856c5ccffa0ac26a6432) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1278391C', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 2550000, NULL, 2550000, 2550000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-06 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-06 00:00:00', '2026-01-06 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-01-14', '2026-01-17', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-one-bedroom', 'Căn hộ 1 Phòng ngủ', 'rt-cozrum-selina-one-bedroom-prepaid-nonref', 'Căn hộ 1 Phòng ngủ - Không gồm bữa sáng', 1, 3, 850000, 2550000, '2026-01-06 00:00:00', '2026-01-06 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2550000, 'VND', 'MOMO', 'BK-1278391C-342728', 'MOMO23DB3C2E05', '2026-01-06 03:00:00', NULL, NULL, '2026-01-06 00:00:00', '2026-01-06 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-78A81E1B', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 4800000, NULL, 4800000, 4800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-15 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-15 00:00:00', '2026-02-15 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-03-08', '2026-03-12', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-two-bedroom', 'Căn hộ 2 Phòng ngủ', 'rt-cozrum-selina-two-bedroom-prepaid-nonref', 'Căn hộ 2 Phòng ngủ - Không gồm bữa sáng', 1, 4, 1200000, 4800000, '2026-02-15 00:00:00', '2026-02-15 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4800000, 'VND', 'MOMO', 'BK-78A81E1B-035A60', 'MOMO91EB6CB32C', '2026-02-15 01:00:00', NULL, NULL, '2026-02-15 00:00:00', '2026-02-15 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9F300407', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 600000, NULL, 600000, 600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-05 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-05 00:00:00', '2026-02-05 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-02-12', '2026-02-13', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-studio', 'Studio', 'rt-cozrum-selina-studio-prepaid-nonref', 'Studio - Không gồm bữa sáng', 1, 1, 600000, 600000, '2026-02-05 00:00:00', '2026-02-05 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 600000, 'VND', 'MOMO', 'BK-9F300407-5A4783', 'MOMOC9E2C356F7', '2026-02-05 05:00:00', NULL, NULL, '2026-02-05 00:00:00', '2026-02-05 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-12570F95', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 2040000, NULL, 2040000, 2040000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-20 00:00:00', '2026-03-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-03-29', '2026-04-01', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-studio-twin', 'Studio Twin', 'rt-cozrum-selina-studio-twin-prepaid-nonref', 'Studio Twin - Không gồm bữa sáng', 1, 3, 680000, 2040000, '2026-03-20 00:00:00', '2026-03-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2040000, 'VND', 'MOMO', 'BK-12570F95-BCA588', 'MOMO6DFCC1762B', '2026-03-20 02:00:00', NULL, NULL, '2026-03-20 00:00:00', '2026-03-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-75A72FF1', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 850000, NULL, 850000, 850000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-27 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-27 00:00:00', '2026-03-27 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-04-03', '2026-04-04', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-one-bedroom', 'Căn hộ 1 Phòng ngủ', 'rt-cozrum-selina-one-bedroom-prepaid-nonref', 'Căn hộ 1 Phòng ngủ - Không gồm bữa sáng', 1, 1, 850000, 850000, '2026-03-27 00:00:00', '2026-03-27 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 850000, 'VND', 'MOMO', 'BK-75A72FF1-BAED16', 'MOMOF2157DC8D1', '2026-03-27 03:00:00', NULL, NULL, '2026-03-27 00:00:00', '2026-03-27 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-21E15AF0', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 2400000, NULL, 2400000, 2400000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-31 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-31 00:00:00', '2025-12-31 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-01-24', '2026-01-26', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-two-bedroom', 'Căn hộ 2 Phòng ngủ', 'rt-cozrum-selina-two-bedroom-prepaid-nonref', 'Căn hộ 2 Phòng ngủ - Không gồm bữa sáng', 1, 2, 1200000, 2400000, '2025-12-31 00:00:00', '2025-12-31 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2400000, 'VND', 'MOMO', 'BK-21E15AF0-9E70AC', 'MOMOE3117421E9', '2025-12-31 03:00:00', NULL, NULL, '2025-12-31 00:00:00', '2025-12-31 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7F38DA7C', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 1200000, NULL, 1200000, 1200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856c5ccffa0ac26a6432', 'cozrum-homes-selina-residence', 'Cozrum Homes - Selina Residence', '2026-04-22', '2026-04-24', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-cozrum-selina-studio', 'Studio', 'rt-cozrum-selina-studio-prepaid-nonref', 'Studio - Không gồm bữa sáng', 1, 2, 600000, 1200000, '2026-03-30 00:00:00', '2026-03-30 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1200000, 'VND', 'MOMO', 'BK-7F38DA7C-4BACE3', 'MOMO0C368A1356', '2026-03-30 05:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 05:00:00');

-- ==== Vy Vy Airport Hotel (6a1f856d5ccffa0ac26a6433) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-0D6225B7', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 4830000, NULL, 4830000, 4830000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-05-12', '2026-05-15', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-family-twin', 'Phòng Family', 'rt-vyvy-family-twin-breakfast-flex', 'Phòng Family - Bao gồm bữa sáng', 1, 3, 1610000, 4830000, '2026-04-13 00:00:00', '2026-04-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4830000, 'VND', 'MOMO', 'BK-0D6225B7-779199', 'MOMOCA77F6B8E7', '2026-04-13 02:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7E8A72D5', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 862500, NULL, 862500, 862500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-03-07', '2026-03-08', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-standard-double', 'Phòng Standard', 'rt-vyvy-standard-double-breakfast-flex', 'Phòng Standard - Bao gồm bữa sáng', 1, 1, 862500, 862500, '2026-02-10 00:00:00', '2026-02-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 862500, 'VND', 'MOMO', 'BK-7E8A72D5-A90DDF', 'MOMOE0B0DBDFE6', '2026-02-10 01:00:00', NULL, NULL, '2026-02-10 00:00:00', '2026-02-10 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-557BA56F', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 1035000, NULL, 1035000, 1035000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-20 00:00:00', '2026-05-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-05-30', '2026-05-31', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-superior-double', 'Phòng Superior', 'rt-vyvy-superior-double-breakfast-flex', 'Phòng Superior - Bao gồm bữa sáng', 1, 1, 1035000, 1035000, '2026-05-20 00:00:00', '2026-05-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1035000, 'VND', 'MOMO', 'BK-557BA56F-C28A69', 'MOMO1D870649D7', '2026-05-20 04:00:00', NULL, NULL, '2026-05-20 00:00:00', '2026-05-20 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-949F12BE', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 10120000, NULL, 10120000, 10120000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-02 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-02 00:00:00', '2026-01-02 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-01-23', '2026-01-27', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-deluxe-king', 'Phòng Deluxe', 'rt-vyvy-deluxe-king-breakfast-flex', 'Phòng Deluxe - Bao gồm bữa sáng', 2, 4, 1265000, 10120000, '2026-01-02 00:00:00', '2026-01-02 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 10120000, 'VND', 'MOMO', 'BK-949F12BE-1E2DA2', 'MOMO528DC5667E', '2026-01-02 02:00:00', NULL, NULL, '2026-01-02 00:00:00', '2026-01-02 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DABF573D', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 1610000, NULL, 1610000, 1610000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-18 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-18 00:00:00', '2026-03-18 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-04-08', '2026-04-09', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-family-twin', 'Phòng Family', 'rt-vyvy-family-twin-breakfast-flex', 'Phòng Family - Bao gồm bữa sáng', 1, 1, 1610000, 1610000, '2026-03-18 00:00:00', '2026-03-18 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1610000, 'VND', 'MOMO', 'BK-DABF573D-44BFA3', 'MOMO30EC6B3025', '2026-03-18 03:00:00', NULL, NULL, '2026-03-18 00:00:00', '2026-03-18 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-FFDAF240', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 6900000, NULL, 6900000, 6900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-16 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-16 00:00:00', '2026-02-16 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-02-22', '2026-02-26', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-standard-double', 'Phòng Standard', 'rt-vyvy-standard-double-breakfast-flex', 'Phòng Standard - Bao gồm bữa sáng', 2, 4, 862500, 6900000, '2026-02-16 00:00:00', '2026-02-16 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6900000, 'VND', 'MOMO', 'BK-FFDAF240-E689AE', 'MOMO7C28A6F737', '2026-02-16 04:00:00', NULL, NULL, '2026-02-16 00:00:00', '2026-02-16 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-35FCC4D2', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 3105000, NULL, 3105000, 3105000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-16 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-16 00:00:00', '2026-04-16 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-04-30', '2026-05-03', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-superior-double', 'Phòng Superior', 'rt-vyvy-superior-double-breakfast-flex', 'Phòng Superior - Bao gồm bữa sáng', 1, 3, 1035000, 3105000, '2026-04-16 00:00:00', '2026-04-16 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3105000, 'VND', 'MOMO', 'BK-35FCC4D2-C1E99A', 'MOMO306DCE3A20', '2026-04-16 02:00:00', NULL, NULL, '2026-04-16 00:00:00', '2026-04-16 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7600FC4F', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 5060000, NULL, 5060000, 5060000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-12 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-12 00:00:00', '2026-02-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856d5ccffa0ac26a6433', 'vy-vy-airport-hotel', 'Vy Vy Airport Hotel', '2026-03-07', '2026-03-09', 2, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-vyvy-deluxe-king', 'Phòng Deluxe', 'rt-vyvy-deluxe-king-breakfast-flex', 'Phòng Deluxe - Bao gồm bữa sáng', 2, 2, 1265000, 5060000, '2026-02-12 00:00:00', '2026-02-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5060000, 'VND', 'MOMO', 'BK-7600FC4F-B11A6B', 'MOMO3145260D36', '2026-02-12 02:00:00', NULL, NULL, '2026-02-12 00:00:00', '2026-02-12 02:00:00');

-- ==== The Passion Lux Airport Hotel Apartment - Free Airport Transfer (6a1f85715ccffa0ac26a6435) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-05B09575', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 6400000, NULL, 6400000, 6400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-03 00:00:00', '2026-05-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f85715ccffa0ac26a6435', 'the-passion-lux-airport-hotel-apartment-free-airport-transfer', 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer', '2026-05-27', '2026-05-31', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-passionlux-standard-double', 'Standard Double', 'rt-passionlux-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 2, 4, 800000, 6400000, '2026-05-03 00:00:00', '2026-05-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6400000, 'VND', 'MOMO', 'BK-05B09575-C726C0', 'MOMOA9526E7544', '2026-05-03 03:00:00', NULL, NULL, '2026-05-03 00:00:00', '2026-05-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9F51EC18', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 950000, NULL, 950000, 950000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-18 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-18 00:00:00', '2026-02-18 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f85715ccffa0ac26a6435', 'the-passion-lux-airport-hotel-apartment-free-airport-transfer', 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer', '2026-03-17', '2026-03-18', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-passionlux-superior-twin', 'Superior Twin', 'rt-passionlux-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 1, 950000, 950000, '2026-02-18 00:00:00', '2026-02-18 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 950000, 'VND', 'MOMO', 'BK-9F51EC18-D0DCCB', 'MOMO4A4508DCAD', '2026-02-18 01:00:00', NULL, NULL, '2026-02-18 00:00:00', '2026-02-18 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-4C26B6C1', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 9200000, NULL, 9200000, 9200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-13 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f85715ccffa0ac26a6435', 'the-passion-lux-airport-hotel-apartment-free-airport-transfer', 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer', '2026-01-26', '2026-01-30', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-passionlux-deluxe-queen', 'Deluxe Queen', 'rt-passionlux-deluxe-queen-prepaid-nonref', 'Deluxe Queen - Không gồm bữa sáng', 2, 4, 1150000, 9200000, '2026-01-13 00:00:00', '2026-01-13 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 9200000, 'VND', 'MOMO', 'BK-4C26B6C1-49D38C', 'MOMO07EA0492E9', '2026-01-13 03:00:00', NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-954B3C27', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 2900000, NULL, 2900000, 2900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f85715ccffa0ac26a6435', 'the-passion-lux-airport-hotel-apartment-free-airport-transfer', 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer', '2026-04-01', '2026-04-03', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-passionlux-family-suite', 'Family Suite', 'rt-passionlux-family-suite-prepaid-nonref', 'Family Suite - Không gồm bữa sáng', 1, 2, 1450000, 2900000, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2900000, 'VND', 'MOMO', 'BK-954B3C27-E110DA', 'MOMODB6E5CD775', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-CF94B60F', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 2400000, NULL, 2400000, 2400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-06 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-06 00:00:00', '2026-03-06 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f85715ccffa0ac26a6435', 'the-passion-lux-airport-hotel-apartment-free-airport-transfer', 'The Passion Lux Airport Hotel Apartment - Free Airport Transfer', '2026-04-01', '2026-04-04', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-passionlux-standard-double', 'Standard Double', 'rt-passionlux-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 3, 800000, 2400000, '2026-03-06 00:00:00', '2026-03-06 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2400000, 'VND', 'MOMO', 'BK-CF94B60F-6D941C', 'MOMO18D3DBD5B1', '2026-03-06 01:00:00', NULL, NULL, '2026-03-06 00:00:00', '2026-03-06 01:00:00');

-- ==== Sunshine Airport Hotel (6a1f856e5ccffa0ac26a6434) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D839B0A2', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 3105000, NULL, 3105000, 3105000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-02-01', '2026-02-03', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-superior-twin', 'Superior Twin', 'rt-sunshine-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 2, 1552500, 3105000, '2026-01-13 00:00:00', '2026-01-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3105000, 'VND', 'MOMO', 'BK-D839B0A2-4DC5B6', 'MOMOA13D6F1D19', '2026-01-13 01:00:00', NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-82C162D5', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 3795000, NULL, 3795000, 3795000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-26 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-26 00:00:00', '2026-01-26 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-02-09', '2026-02-11', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-deluxe-king', 'Deluxe King', 'rt-sunshine-deluxe-king-breakfast-flex', 'Deluxe King - Bao gồm bữa sáng', 1, 2, 1897500, 3795000, '2026-01-26 00:00:00', '2026-01-26 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3795000, 'VND', 'MOMO', 'BK-82C162D5-C302F0', 'MOMOF05CD582E6', '2026-01-26 05:00:00', NULL, NULL, '2026-01-26 00:00:00', '2026-01-26 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B6811214', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 2357500, NULL, 2357500, 2357500, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-04-17 04:00:00', '2026-04-17 21:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-04-17 00:00:00', '2026-04-17 21:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-05-03', '2026-05-04', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-family-room', 'Family Room', 'rt-sunshine-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 1, 2357500, 2357500, '2026-04-17 00:00:00', '2026-04-17 21:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 2357500, 'VND', 'MOMO', 'BK-B6811214-9C4EFF', 'MOMO9576B2B033', '2026-04-17 04:00:00', 2357500, '2026-04-17 21:00:00', '2026-04-17 00:00:00', '2026-04-17 21:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-E255D594', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 1265000, NULL, 1265000, 1265000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-04-05', '2026-04-06', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-standard-double', 'Standard Double', 'rt-sunshine-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 1, 1265000, 1265000, '2026-03-30 00:00:00', '2026-03-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1265000, 'VND', 'MOMO', 'BK-E255D594-6C6A58', 'MOMO61DC70D9C2', '2026-03-30 04:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-56C0FEB7', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 1552500, NULL, 1552500, 1552500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-12 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-12 00:00:00', '2026-03-12 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-03-30', '2026-03-31', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-superior-twin', 'Superior Twin', 'rt-sunshine-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 1, 1552500, 1552500, '2026-03-12 00:00:00', '2026-03-12 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1552500, 'VND', 'MOMO', 'BK-56C0FEB7-4B5D73', 'MOMOAF826EE633', '2026-03-12 01:00:00', NULL, NULL, '2026-03-12 00:00:00', '2026-03-12 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-39E258B9', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 7590000, NULL, 7590000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-03-11 18:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-03-11 00:00:00', '2026-03-11 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f856e5ccffa0ac26a6434', 'sunshine-airport-hotel', 'Sunshine Airport Hotel', '2026-03-18', '2026-03-22', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-sunshine-deluxe-king', 'Deluxe King', 'rt-sunshine-deluxe-king-breakfast-flex', 'Deluxe King - Bao gồm bữa sáng', 1, 4, 1897500, 7590000, '2026-03-11 00:00:00', '2026-03-11 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 7590000, 'VND', 'MOMO', 'BK-39E258B9-A5CB39', NULL, NULL, NULL, NULL, '2026-03-11 00:00:00', '2026-03-11 18:00:00');
