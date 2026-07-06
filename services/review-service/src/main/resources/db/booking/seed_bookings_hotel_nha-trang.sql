-- Seed demo bookings HOTEL cho khu vuc: nha-trang
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Aaron Hotel (6a1f8b88ac4325285e73be0c) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-89D1BE56', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1610000, NULL, 1610000, 1610000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-11 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-11 00:00:00', '2026-05-11 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-05-18', '2026-05-19', 1, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-superior-double', 'Superior Double', 'rt-aaron-superior-double-breakfast-flex', 'Superior Double - Bao gồm bữa sáng', 2, 1, 805000, 1610000, '2026-05-11 00:00:00', '2026-05-11 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1610000, 'VND', 'MOMO', 'BK-89D1BE56-A492F2', 'MOMO9A86B4E865', '2026-05-11 01:00:00', NULL, NULL, '2026-05-11 00:00:00', '2026-05-11 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A8134786', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 3312000, NULL, 3312000, 3312000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-04-08', '2026-04-12', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-superior-twin', 'Superior Twin', 'rt-aaron-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 4, 828000, 3312000, '2026-03-30 00:00:00', '2026-03-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3312000, 'VND', 'MOMO', 'BK-A8134786-4811D7', 'MOMO02D4E4C9C2', '2026-03-30 04:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-911C654C', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 4508000, NULL, 4508000, 4508000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-29 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-29 00:00:00', '2026-01-29 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-02-01', '2026-02-05', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-deluxe-sea-view', 'Deluxe Sea View', 'rt-aaron-deluxe-sea-view-breakfast-flex', 'Deluxe Sea View - Bao gồm bữa sáng', 1, 4, 1127000, 4508000, '2026-01-29 00:00:00', '2026-01-29 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 4508000, 'VND', 'MOMO', 'BK-911C654C-5D150F', 'MOMOF6ECC32880', '2026-01-29 05:00:00', NULL, NULL, '2026-01-29 00:00:00', '2026-01-29 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D39F8E6A', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3174000, NULL, 3174000, 3174000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-30 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-30 00:00:00', '2026-04-30 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-05-14', '2026-05-17', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-deluxe-city-view', 'Deluxe City View', 'rt-aaron-deluxe-city-view-breakfast-flex', 'Deluxe City View - Bao gồm bữa sáng', 1, 3, 1058000, 3174000, '2026-04-30 00:00:00', '2026-04-30 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3174000, 'VND', 'MOMO', 'BK-D39F8E6A-AE9DA1', 'MOMO551E3CE08A', '2026-04-30 01:00:00', NULL, NULL, '2026-04-30 00:00:00', '2026-04-30 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7CDE749F', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 6670000, NULL, 6670000, 6670000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-23 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-23 00:00:00', '2026-02-23 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-03-13', '2026-03-15', 2, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-family', 'Family Suite', 'rt-aaron-family-breakfast-flex', 'Family Suite - Bao gồm bữa sáng', 2, 2, 1667500, 6670000, '2026-02-23 00:00:00', '2026-02-23 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6670000, 'VND', 'MOMO', 'BK-7CDE749F-253267', 'MOMO588CB4F228', '2026-02-23 05:00:00', NULL, NULL, '2026-02-23 00:00:00', '2026-02-23 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-32D17E93', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 805000, NULL, 805000, 805000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-09 00:00:00', '2026-01-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-01-27', '2026-01-28', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-superior-double', 'Superior Double', 'rt-aaron-superior-double-breakfast-flex', 'Superior Double - Bao gồm bữa sáng', 1, 1, 805000, 805000, '2026-01-09 00:00:00', '2026-01-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 805000, 'VND', 'MOMO', 'BK-32D17E93-FA69BE', 'MOMOFD5A156838', '2026-01-09 04:00:00', NULL, NULL, '2026-01-09 00:00:00', '2026-01-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A10F679A', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 2484000, NULL, 2484000, 2484000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-28 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-28 00:00:00', '2026-02-28 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-03-20', '2026-03-23', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-superior-twin', 'Superior Twin', 'rt-aaron-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 3, 828000, 2484000, '2026-02-28 00:00:00', '2026-02-28 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2484000, 'VND', 'MOMO', 'BK-A10F679A-424826', 'MOMO695EBD6DB2', '2026-02-28 03:00:00', NULL, NULL, '2026-02-28 00:00:00', '2026-02-28 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-CEE1DCC0', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 2254000, NULL, 2254000, 2254000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-20 00:00:00', '2025-12-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b88ac4325285e73be0c', 'aaron-hotel', 'Aaron Hotel', '2026-01-17', '2026-01-19', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-aaron-deluxe-sea-view', 'Deluxe Sea View', 'rt-aaron-deluxe-sea-view-breakfast-flex', 'Deluxe Sea View - Bao gồm bữa sáng', 1, 2, 1127000, 2254000, '2025-12-20 00:00:00', '2025-12-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2254000, 'VND', 'MOMO', 'BK-CEE1DCC0-AB0739', 'MOMOCAF71AEC8E', '2025-12-20 04:00:00', NULL, NULL, '2025-12-20 00:00:00', '2025-12-20 04:00:00');

-- ==== Golden Hotel Nha Trang (6a1f8b8bac4325285e73be0e) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D4AB6B92', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 1950000, NULL, 1950000, 1950000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-25 00:00:00', '2026-02-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8bac4325285e73be0e', 'golden-hotel-nha-trang', 'Golden Hotel Nha Trang', '2026-03-21', '2026-03-24', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntgolden-standard-double', 'Standard Double', 'rt-ntgolden-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 3, 650000, 1950000, '2026-02-25 00:00:00', '2026-02-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1950000, 'VND', 'MOMO', 'BK-D4AB6B92-8DE0BC', 'MOMOB26C423C9C', '2026-02-25 01:00:00', NULL, NULL, '2026-02-25 00:00:00', '2026-02-25 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-73F6C9E4', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 1560000, NULL, 1560000, 1560000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-20 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-20 00:00:00', '2026-01-20 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8bac4325285e73be0e', 'golden-hotel-nha-trang', 'Golden Hotel Nha Trang', '2026-02-17', '2026-02-19', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntgolden-superior-twin', 'Superior Twin', 'rt-ntgolden-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 2, 780000, 1560000, '2026-01-20 00:00:00', '2026-01-20 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1560000, 'VND', 'MOMO', 'BK-73F6C9E4-137F25', 'MOMO327D526D16', '2026-01-20 02:00:00', NULL, NULL, '2026-01-20 00:00:00', '2026-01-20 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6DC8F993', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3920000, NULL, 3920000, 3920000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-21 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-21 00:00:00', '2026-05-21 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8bac4325285e73be0e', 'golden-hotel-nha-trang', 'Golden Hotel Nha Trang', '2026-05-25', '2026-05-29', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntgolden-deluxe-sea-view', 'Deluxe Sea View', 'rt-ntgolden-deluxe-sea-view-prepaid-nonref', 'Deluxe Sea View - Không gồm bữa sáng', 1, 4, 980000, 3920000, '2026-05-21 00:00:00', '2026-05-21 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3920000, 'VND', 'MOMO', 'BK-6DC8F993-1369C1', 'MOMO79D59FA6EA', '2026-05-21 05:00:00', NULL, NULL, '2026-05-21 00:00:00', '2026-05-21 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-07C6E40C', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 2700000, NULL, 2700000, 2700000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-20 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-20 00:00:00', '2025-12-20 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8bac4325285e73be0e', 'golden-hotel-nha-trang', 'Golden Hotel Nha Trang', '2026-01-12', '2026-01-14', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntgolden-family-suite', 'Family Suite', 'rt-ntgolden-family-suite-prepaid-nonref', 'Family Suite - Không gồm bữa sáng', 1, 2, 1350000, 2700000, '2025-12-20 00:00:00', '2025-12-20 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2700000, 'VND', 'MOMO', 'BK-07C6E40C-DD2C4F', 'MOMO456D3A12EE', '2025-12-20 01:00:00', NULL, NULL, '2025-12-20 00:00:00', '2025-12-20 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2A79109E', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 650000, NULL, 650000, 650000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8bac4325285e73be0e', 'golden-hotel-nha-trang', 'Golden Hotel Nha Trang', '2026-04-13', '2026-04-14', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntgolden-standard-double', 'Standard Double', 'rt-ntgolden-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 1, 650000, 650000, '2026-03-25 00:00:00', '2026-03-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 650000, 'VND', 'MOMO', 'BK-2A79109E-CCF763', 'MOMOE889664752', '2026-03-25 03:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 03:00:00');

-- ==== Panorama Nha Trang Sanvilla (6a1f8b8cac4325285e73be0f) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-4C2E13C8', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 2932500, NULL, 2932500, 2932500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-26 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-26 00:00:00', '2026-03-26 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-04-07', '2026-04-10', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-studio-sea-view', 'Studio Sea View', 'rt-ntpanorama-studio-sea-view-breakfast-flex', 'Studio Sea View - Bao gồm bữa sáng', 1, 3, 977500, 2932500, '2026-03-26 00:00:00', '2026-03-26 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2932500, 'VND', 'MOMO', 'BK-4C2E13C8-FC9D38', 'MOMO15CB6F9546', '2026-03-26 01:00:00', NULL, NULL, '2026-03-26 00:00:00', '2026-03-26 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-68667FA6', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 2645000, NULL, 2645000, 2645000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-04-07', '2026-04-09', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-one-bedroom-sea-view', 'One-Bedroom Apartment Sea View', 'rt-ntpanorama-one-bedroom-sea-view-breakfast-flex', 'One-Bedroom Apartment Sea View - Bao gồm bữa sáng', 1, 2, 1322500, 2645000, '2026-03-21 00:00:00', '2026-03-21 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2645000, 'VND', 'MOMO', 'BK-68667FA6-85163C', 'MOMO68C598FE2D', '2026-03-21 01:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-566612A4', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 11385000, NULL, 11385000, 11385000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-20 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-20 00:00:00', '2026-02-20 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-02-24', '2026-02-27', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-two-bedroom-family', 'Two-Bedroom Family Apartment', 'rt-ntpanorama-two-bedroom-family-breakfast-flex', 'Two-Bedroom Family Apartment - Bao gồm bữa sáng', 2, 3, 1897500, 11385000, '2026-02-20 00:00:00', '2026-02-20 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 11385000, 'VND', 'MOMO', 'BK-566612A4-72BBF4', 'MOMO7225D09371', '2026-02-20 04:00:00', NULL, NULL, '2026-02-20 00:00:00', '2026-02-20 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-C95C7243', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 747500, NULL, 747500, 747500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-30 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-30 00:00:00', '2026-03-30 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-04-26', '2026-04-27', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-studio-city-view', 'Studio City View', 'rt-ntpanorama-studio-city-view-breakfast-flex', 'Studio City View - Bao gồm bữa sáng', 1, 1, 747500, 747500, '2026-03-30 00:00:00', '2026-03-30 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 747500, 'VND', 'MOMO', 'BK-C95C7243-E7458F', 'MOMO28A0150294', '2026-03-30 04:00:00', NULL, NULL, '2026-03-30 00:00:00', '2026-03-30 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-FF057985', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 2932500, NULL, 2932500, 2932500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-19 00:00:00', '2026-03-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-03-26', '2026-03-29', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-studio-sea-view', 'Studio Sea View', 'rt-ntpanorama-studio-sea-view-breakfast-flex', 'Studio Sea View - Bao gồm bữa sáng', 1, 3, 977500, 2932500, '2026-03-19 00:00:00', '2026-03-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2932500, 'VND', 'MOMO', 'BK-FF057985-1252DC', 'MOMODE0626D83F', '2026-03-19 02:00:00', NULL, NULL, '2026-03-19 00:00:00', '2026-03-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-3E5DBF36', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 3967500, NULL, 3967500, 3967500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-25 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-25 00:00:00', '2026-03-25 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8cac4325285e73be0f', 'panorama-nha-trang-sanvilla', 'Panorama Nha Trang Sanvilla', '2026-04-08', '2026-04-11', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntpanorama-one-bedroom-sea-view', 'One-Bedroom Apartment Sea View', 'rt-ntpanorama-one-bedroom-sea-view-breakfast-flex', 'One-Bedroom Apartment Sea View - Bao gồm bữa sáng', 1, 3, 1322500, 3967500, '2026-03-25 00:00:00', '2026-03-25 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3967500, 'VND', 'MOMO', 'BK-3E5DBF36-89EE0E', 'MOMOB6781A2A31', '2026-03-25 01:00:00', NULL, NULL, '2026-03-25 00:00:00', '2026-03-25 01:00:00');

-- ==== Brilliant Bay Nha Trang Hotel (6a1f8b8fac4325285e73be10) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-BB5A4214', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3900000, NULL, 3900000, 3900000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-07 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-07 00:00:00', '2026-05-07 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-05-22', '2026-05-25', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-deluxe-sea-view', 'Deluxe Sea View', 'rt-ntbb-deluxe-sea-view-prepaid-nonref', 'Deluxe Sea View - Không gồm bữa sáng', 1, 3, 1300000, 3900000, '2026-05-07 00:00:00', '2026-05-07 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3900000, 'VND', 'MOMO', 'BK-BB5A4214-F69B71', 'MOMO3735E1D85F', '2026-05-07 05:00:00', NULL, NULL, '2026-05-07 00:00:00', '2026-05-07 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9E0AD050', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 3600000, NULL, 3600000, 3600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-01 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-01 00:00:00', '2026-06-01 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-06-04', '2026-06-05', 1, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-family-suite', 'Family Suite', 'rt-ntbb-family-suite-prepaid-nonref', 'Family Suite - Không gồm bữa sáng', 2, 1, 1800000, 3600000, '2026-06-01 00:00:00', '2026-06-01 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3600000, 'VND', 'MOMO', 'BK-9E0AD050-261C86', 'MOMOF526B9CC6A', '2026-06-01 02:00:00', NULL, NULL, '2026-06-01 00:00:00', '2026-06-01 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B327B6B5', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 2700000, NULL, 2700000, 2700000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-25 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-25 00:00:00', '2025-12-25 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-01-13', '2026-01-16', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-superior-double', 'Superior Double', 'rt-ntbb-superior-double-prepaid-nonref', 'Superior Double - Không gồm bữa sáng', 1, 3, 900000, 2700000, '2025-12-25 00:00:00', '2025-12-25 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2700000, 'VND', 'MOMO', 'BK-B327B6B5-B00519', 'MOMO5EC931C47A', '2025-12-25 02:00:00', NULL, NULL, '2025-12-25 00:00:00', '2025-12-25 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-C531C528', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 950000, NULL, 950000, 950000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-22 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-22 00:00:00', '2025-12-22 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-01-12', '2026-01-13', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-superior-twin', 'Superior Twin', 'rt-ntbb-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 1, 950000, 950000, '2025-12-22 00:00:00', '2025-12-22 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 950000, 'VND', 'MOMO', 'BK-C531C528-198ACF', 'MOMO1017CF1D9A', '2025-12-22 03:00:00', NULL, NULL, '2025-12-22 00:00:00', '2025-12-22 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7B54ADE4', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 5200000, NULL, 5200000, 5200000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-01 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-01 00:00:00', '2026-04-01 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-04-21', '2026-04-25', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-deluxe-sea-view', 'Deluxe Sea View', 'rt-ntbb-deluxe-sea-view-prepaid-nonref', 'Deluxe Sea View - Không gồm bữa sáng', 1, 4, 1300000, 5200000, '2026-04-01 00:00:00', '2026-04-01 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5200000, 'VND', 'MOMO', 'BK-7B54ADE4-824167', 'MOMO1C76FC42A4', '2026-04-01 05:00:00', NULL, NULL, '2026-04-01 00:00:00', '2026-04-01 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-48823559', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 14400000, NULL, 14400000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-02 16:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-02-23', '2026-02-27', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-family-suite', 'Family Suite', 'rt-ntbb-family-suite-prepaid-nonref', 'Family Suite - Không gồm bữa sáng', 2, 4, 1800000, 14400000, '2026-02-02 00:00:00', '2026-02-02 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 14400000, 'VND', 'MOMO', 'BK-48823559-AFFEE9', NULL, NULL, NULL, NULL, '2026-02-02 00:00:00', '2026-02-02 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D6CF56B5', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 1800000, NULL, 1800000, 1800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-01 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-01 00:00:00', '2026-04-01 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8fac4325285e73be10', 'brilliant-bay-nha-trang-hotel', 'Brilliant Bay Nha Trang Hotel', '2026-04-06', '2026-04-08', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntbb-superior-double', 'Superior Double', 'rt-ntbb-superior-double-prepaid-nonref', 'Superior Double - Không gồm bữa sáng', 1, 2, 900000, 1800000, '2026-04-01 00:00:00', '2026-04-01 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1800000, 'VND', 'MOMO', 'BK-D6CF56B5-D74DE7', 'MOMO847860D570', '2026-04-01 04:00:00', NULL, NULL, '2026-04-01 00:00:00', '2026-04-01 04:00:00');

-- ==== Sun City Hotel Nha Trang (6a1f8b8aac4325285e73be0d) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-0FE7853E', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 3105000, NULL, 3105000, 3105000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-19 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-19 00:00:00', '2026-02-19 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-03-16', '2026-03-18', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-family', 'Family Room', 'rt-ntsuncity-family-pay-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1552500, 3105000, '2026-02-19 00:00:00', '2026-02-19 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3105000, 'VND', 'MOMO', 'BK-0FE7853E-80829E', 'MOMOD366115D2C', '2026-02-19 04:00:00', NULL, NULL, '2026-02-19 00:00:00', '2026-02-19 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2310E6F3', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 2242500, NULL, 2242500, 2242500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-03 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-01-18', '2026-01-21', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-standard-double', 'Standard Double', 'rt-ntsuncity-standard-double-pay-flex', 'Standard Double - Thanh toán tại khách sạn', 1, 3, 747500, 2242500, '2026-01-03 00:00:00', '2026-01-03 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2242500, 'VND', 'MOMO', 'BK-2310E6F3-E72FE7', 'MOMO0B8F1C43B7', '2026-01-03 01:00:00', NULL, NULL, '2026-01-03 00:00:00', '2026-01-03 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F0259E1D', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 3588000, NULL, 3588000, 3588000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-26 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-26 00:00:00', '2026-03-26 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-04-22', '2026-04-26', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-superior-twin', 'Superior Twin', 'rt-ntsuncity-superior-twin-pay-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 4, 897000, 3588000, '2026-03-26 00:00:00', '2026-03-26 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3588000, 'VND', 'MOMO', 'BK-F0259E1D-20DA5E', 'MOMO4A84201096', '2026-03-26 03:00:00', NULL, NULL, '2026-03-26 00:00:00', '2026-03-26 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F6602221', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 8740000, NULL, 8740000, 8740000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-19 00:00:00', '2026-02-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-03-04', '2026-03-08', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-deluxe-queen', 'Deluxe Queen', 'rt-ntsuncity-deluxe-queen-pay-flex', 'Deluxe Queen - Bao gồm bữa sáng', 2, 4, 1092500, 8740000, '2026-02-19 00:00:00', '2026-02-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 8740000, 'VND', 'MOMO', 'BK-F6602221-4475C1', 'MOMO5CEFA41FE8', '2026-02-19 02:00:00', NULL, NULL, '2026-02-19 00:00:00', '2026-02-19 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-F6F9A885', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 5290000, NULL, 5290000, 5290000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-05 00:00:00', '2026-02-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-02-15', '2026-02-19', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-deluxe-sea-view', 'Deluxe Sea View', 'rt-ntsuncity-deluxe-sea-view-pay-flex', 'Deluxe Sea View - Bao gồm bữa sáng', 1, 4, 1322500, 5290000, '2026-02-05 00:00:00', '2026-02-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 5290000, 'VND', 'MOMO', 'BK-F6F9A885-FC5BB2', 'MOMOA46A27FF58', '2026-02-05 01:00:00', NULL, NULL, '2026-02-05 00:00:00', '2026-02-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DB8327C4', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 6210000, NULL, 6210000, 6210000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-26 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-26 00:00:00', '2026-02-26 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-03-21', '2026-03-25', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-family', 'Family Room', 'rt-ntsuncity-family-pay-flex', 'Family Room - Bao gồm bữa sáng', 1, 4, 1552500, 6210000, '2026-02-26 00:00:00', '2026-02-26 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 6210000, 'VND', 'MOMO', 'BK-DB8327C4-2DD2BF', 'MOMO6AFBC144B8', '2026-02-26 02:00:00', NULL, NULL, '2026-02-26 00:00:00', '2026-02-26 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A32C8FA0', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 2990000, NULL, 2990000, 2990000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-05-15 03:00:00', '2026-05-15 09:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-05-15 00:00:00', '2026-05-15 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-05-21', '2026-05-23', 2, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-standard-double', 'Standard Double', 'rt-ntsuncity-standard-double-pay-flex', 'Standard Double - Thanh toán tại khách sạn', 2, 2, 747500, 2990000, '2026-05-15 00:00:00', '2026-05-15 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 2990000, 'VND', 'MOMO', 'BK-A32C8FA0-08CC5C', 'MOMO8F1C77E90A', '2026-05-15 03:00:00', 2990000, '2026-05-15 09:00:00', '2026-05-15 00:00:00', '2026-05-15 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-19AC67EE', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 897000, NULL, 897000, 897000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-19 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-19 00:00:00', '2026-05-19 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f8b8aac4325285e73be0d', 'sun-city-hotel-nha-trang', 'Sun City Hotel Nha Trang', '2026-05-24', '2026-05-25', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-ntsuncity-superior-twin', 'Superior Twin', 'rt-ntsuncity-superior-twin-pay-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 1, 897000, 897000, '2026-05-19 00:00:00', '2026-05-19 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 897000, 'VND', 'MOMO', 'BK-19AC67EE-4F57C0', 'MOMOF832FF984C', '2026-05-19 05:00:00', NULL, NULL, '2026-05-19 00:00:00', '2026-05-19 05:00:00');
