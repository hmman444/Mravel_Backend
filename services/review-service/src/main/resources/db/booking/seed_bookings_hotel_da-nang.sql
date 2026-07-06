-- Seed demo bookings HOTEL cho khu vuc: da-nang
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Muong Thanh Grand Da Nang Hotel (6a1f90660c291e093e9e1167) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DF318FAE', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 1000000, NULL, 1000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-04-27 09:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-04-27 00:00:00', '2026-04-27 09:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90660c291e093e9e1167', 'muong-thanh-grand-da-nang-hotel', 'Muong Thanh Grand Da Nang Hotel', '2026-05-05', '2026-05-06', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-mt-dn-standard-double', 'Standard Double', 'rt-mt-dn-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 1, 1000000, 1000000, '2026-04-27 00:00:00', '2026-04-27 09:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1000000, 'VND', 'MOMO', 'BK-DF318FAE-59F501', NULL, NULL, NULL, NULL, '2026-04-27 00:00:00', '2026-04-27 09:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B439ACAC', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 2600000, NULL, 2600000, 2600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90660c291e093e9e1167', 'muong-thanh-grand-da-nang-hotel', 'Muong Thanh Grand Da Nang Hotel', '2026-03-30', '2026-04-01', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-mt-dn-superior-twin', 'Superior Twin', 'rt-mt-dn-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 2, 1300000, 2600000, '2026-03-21 00:00:00', '2026-03-21 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2600000, 'VND', 'MOMO', 'BK-B439ACAC-40F3AF', 'MOMO9EEAD83DFC', '2026-03-21 02:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-5DEEC8E6', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 13600000, NULL, 13600000, 13600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-28 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-28 00:00:00', '2026-03-28 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90660c291e093e9e1167', 'muong-thanh-grand-da-nang-hotel', 'Muong Thanh Grand Da Nang Hotel', '2026-04-21', '2026-04-25', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-mt-dn-deluxe-city-view', 'Deluxe City View', 'rt-mt-dn-deluxe-city-view-prepaid-nonref', 'Deluxe City View - Không gồm bữa sáng', 2, 4, 1700000, 13600000, '2026-03-28 00:00:00', '2026-03-28 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 13600000, 'VND', 'MOMO', 'BK-5DEEC8E6-29F43F', 'MOMOF2C0F9923D', '2026-03-28 05:00:00', NULL, NULL, '2026-03-28 00:00:00', '2026-03-28 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-246CC749', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 8400000, NULL, 8400000, 8400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-05 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-05 00:00:00', '2026-05-05 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90660c291e093e9e1167', 'muong-thanh-grand-da-nang-hotel', 'Muong Thanh Grand Da Nang Hotel', '2026-05-22', '2026-05-26', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-mt-dn-deluxe-sea-view', 'Deluxe Sea View', 'rt-mt-dn-deluxe-sea-view-prepaid-nonref', 'Deluxe Sea View - Không gồm bữa sáng', 1, 4, 2100000, 8400000, '2026-05-05 00:00:00', '2026-05-05 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 8400000, 'VND', 'MOMO', 'BK-246CC749-2090A1', 'MOMOC1AFB2008A', '2026-05-05 01:00:00', NULL, NULL, '2026-05-05 00:00:00', '2026-05-05 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-898875B2', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 8400000, NULL, 8400000, 8400000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-01-17 02:00:00', '2026-01-17 07:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-01-17 00:00:00', '2026-01-17 07:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90660c291e093e9e1167', 'muong-thanh-grand-da-nang-hotel', 'Muong Thanh Grand Da Nang Hotel', '2026-01-23', '2026-01-26', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-mt-dn-family-suite', 'Family Suite', 'rt-mt-dn-family-suite-prepaid-nonref', 'Family Suite - Không gồm bữa sáng', 1, 3, 2800000, 8400000, '2026-01-17 00:00:00', '2026-01-17 07:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 8400000, 'VND', 'MOMO', 'BK-898875B2-E84FCD', 'MOMO669C79C9B9', '2026-01-17 02:00:00', 8400000, '2026-01-17 07:00:00', '2026-01-17 00:00:00', '2026-01-17 07:00:00');

-- ==== Orchide'es Ocean Hotel (6a1f906d0c291e093e9e1169) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7FDBE2C1', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 3910000, NULL, 3910000, 3910000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2025-12-25 01:00:00', '2025-12-25 04:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2025-12-25 00:00:00', '2025-12-25 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-01-14', '2026-01-18', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-superior-twin', 'Superior Twin', 'rt-orchidees-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 4, 977500, 3910000, '2025-12-25 00:00:00', '2025-12-25 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 3910000, 'VND', 'MOMO', 'BK-7FDBE2C1-31F835', 'MOMO20FD2B05A9', '2025-12-25 01:00:00', 3910000, '2025-12-25 04:00:00', '2025-12-25 00:00:00', '2025-12-25 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A5346B75', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 1207500, NULL, 1207500, 1207500, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-15 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-15 00:00:00', '2025-12-15 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-01-10', '2026-01-11', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-deluxe-queen', 'Deluxe Queen', 'rt-orchidees-deluxe-queen-breakfast-flex', 'Deluxe Queen - Bao gồm bữa sáng', 1, 1, 1207500, 1207500, '2025-12-15 00:00:00', '2025-12-15 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1207500, 'VND', 'MOMO', 'BK-A5346B75-285C9C', 'MOMO3757CEB42A', '2025-12-15 04:00:00', NULL, NULL, '2025-12-15 00:00:00', '2025-12-15 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6DC3E6BD', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 3105000, NULL, 3105000, 3105000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-13 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-13 00:00:00', '2026-04-13 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-05-04', '2026-05-06', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-deluxe-sea-view', 'Deluxe Sea View', 'rt-orchidees-deluxe-sea-view-breakfast-flex', 'Deluxe Sea View - Bao gồm bữa sáng', 1, 2, 1552500, 3105000, '2026-04-13 00:00:00', '2026-04-13 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3105000, 'VND', 'MOMO', 'BK-6DC3E6BD-98BACE', 'MOMO49C56CF08C', '2026-04-13 02:00:00', NULL, NULL, '2026-04-13 00:00:00', '2026-04-13 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-FAEFA9E8', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 1725000, NULL, 1725000, 1725000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-13 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-01-30', '2026-01-31', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-family-room', 'Family Room', 'rt-orchidees-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 1, 1725000, 1725000, '2026-01-13 00:00:00', '2026-01-13 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1725000, 'VND', 'MOMO', 'BK-FAEFA9E8-EA5C20', 'MOMOE10F6D0764', '2026-01-13 05:00:00', NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-91757F96', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 2415000, NULL, 2415000, 2415000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-21 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-21 00:00:00', '2026-04-21 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-05-15', '2026-05-18', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-standard-double', 'Standard Double', 'rt-orchidees-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 3, 805000, 2415000, '2026-04-21 00:00:00', '2026-04-21 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2415000, 'VND', 'MOMO', 'BK-91757F96-5D3A8A', 'MOMOB6724EC447', '2026-04-21 05:00:00', NULL, NULL, '2026-04-21 00:00:00', '2026-04-21 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-8664F51D', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 7820000, NULL, 7820000, 7820000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-14 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-14 00:00:00', '2026-01-14 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906d0c291e093e9e1169', 'orchide-es-ocean-hotel', 'Orchide''es Ocean Hotel', '2026-02-02', '2026-02-06', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-orchidees-superior-twin', 'Superior Twin', 'rt-orchidees-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 2, 4, 977500, 7820000, '2026-01-14 00:00:00', '2026-01-14 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 7820000, 'VND', 'MOMO', 'BK-8664F51D-A0DBCB', 'MOMO406B2D4D2A', '2026-01-14 05:00:00', NULL, NULL, '2026-01-14 00:00:00', '2026-01-14 05:00:00');

-- ==== La Beach Hotel (6a1f906a0c291e093e9e1168) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-A22A0CBB', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 8400000, NULL, 8400000, 8400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-03 00:00:00', '2026-06-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-06-07', '2026-06-11', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-deluxe-city-view', 'Deluxe City View', 'rt-labeach-deluxe-city-view-prepaid-nonref', 'Deluxe City View - Không gồm bữa sáng', 2, 4, 1050000, 8400000, '2026-06-03 00:00:00', '2026-06-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 8400000, 'VND', 'MOMO', 'BK-A22A0CBB-C02164', 'MOMO4A1B238813', '2026-06-03 03:00:00', NULL, NULL, '2026-06-03 00:00:00', '2026-06-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-61A49A37', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 1350000, NULL, 1350000, 1350000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-17 00:00:00', '2026-05-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-06-05', '2026-06-06', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-family', 'Family Room', 'rt-labeach-family-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 1, 1350000, 1350000, '2026-05-17 00:00:00', '2026-05-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1350000, 'VND', 'MOMO', 'BK-61A49A37-EF2FA6', 'MOMOA113ED9EFB', '2026-05-17 04:00:00', NULL, NULL, '2026-05-17 00:00:00', '2026-05-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-34AF5610', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 700000, NULL, 700000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2025-12-29 05:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2025-12-29 00:00:00', '2025-12-29 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-01-11', '2026-01-12', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-standard-double', 'Standard Double', 'rt-labeach-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 1, 700000, 700000, '2025-12-29 00:00:00', '2025-12-29 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 700000, 'VND', 'MOMO', 'BK-34AF5610-4A72EA', NULL, NULL, NULL, NULL, '2025-12-29 00:00:00', '2025-12-29 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AFDEB550', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 3400000, NULL, 3400000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-14 02:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-03-09', '2026-03-13', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-superior-twin', 'Superior Twin', 'rt-labeach-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 4, 850000, 3400000, '2026-02-14 00:00:00', '2026-02-14 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 3400000, 'VND', 'MOMO', 'BK-AFDEB550-E836A9', NULL, NULL, NULL, NULL, '2026-02-14 00:00:00', '2026-02-14 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B6BF4751', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 3150000, NULL, 3150000, 3150000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-15 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-15 00:00:00', '2026-02-15 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-02-24', '2026-02-27', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-deluxe-city-view', 'Deluxe City View', 'rt-labeach-deluxe-city-view-prepaid-nonref', 'Deluxe City View - Không gồm bữa sáng', 1, 3, 1050000, 3150000, '2026-02-15 00:00:00', '2026-02-15 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3150000, 'VND', 'MOMO', 'BK-B6BF4751-541410', 'MOMO22D4BB3BDF', '2026-02-15 03:00:00', NULL, NULL, '2026-02-15 00:00:00', '2026-02-15 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D2F011B8', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 2700000, NULL, 2700000, 2700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-02-06', '2026-02-08', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-family', 'Family Room', 'rt-labeach-family-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 2, 1350000, 2700000, '2026-01-27 00:00:00', '2026-01-27 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2700000, 'VND', 'MOMO', 'BK-D2F011B8-DE5F11', 'MOMO63DEE7D285', '2026-01-27 02:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-B771D49E', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 2800000, NULL, 2800000, 2800000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-05 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-05 00:00:00', '2026-05-05 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906a0c291e093e9e1168', 'la-beach-hotel', 'La Beach Hotel', '2026-05-08', '2026-05-12', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-labeach-standard-double', 'Standard Double', 'rt-labeach-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 4, 700000, 2800000, '2026-05-05 00:00:00', '2026-05-05 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2800000, 'VND', 'MOMO', 'BK-B771D49E-065066', 'MOMOB9901A8144', '2026-05-05 03:00:00', NULL, NULL, '2026-05-05 00:00:00', '2026-05-05 03:00:00');

-- ==== Davue Hotel Da Nang (6a1f90740c291e093e9e116b) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6360E7C5', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 3220000, NULL, 3220000, 3220000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-03 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-03 00:00:00', '2026-01-03 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-01-09', '2026-01-11', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-family-room', 'Family Room', 'rt-davue-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1610000, 3220000, '2026-01-03 00:00:00', '2026-01-03 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3220000, 'VND', 'MOMO', 'BK-6360E7C5-18FE06', 'MOMO40495C3F30', '2026-01-03 03:00:00', NULL, NULL, '2026-01-03 00:00:00', '2026-01-03 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-013F649F', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 4830000, NULL, 4830000, 4830000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-14 03:00:00', '2026-02-14 14:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-02-14 00:00:00', '2026-02-14 14:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-02-22', '2026-02-25', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-standard-double', 'Standard Double', 'rt-davue-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 2, 3, 805000, 4830000, '2026-02-14 00:00:00', '2026-02-14 14:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 4830000, 'VND', 'MOMO', 'BK-013F649F-4B3944', 'MOMO398FE858D4', '2026-02-14 03:00:00', 4830000, '2026-02-14 14:00:00', '2026-02-14 00:00:00', '2026-02-14 14:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2F7B2DA7', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 2932500, NULL, 2932500, 2932500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-02-16', '2026-02-19', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-superior-twin', 'Superior Twin', 'rt-davue-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 1, 3, 977500, 2932500, '2026-01-27 00:00:00', '2026-01-27 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2932500, 'VND', 'MOMO', 'BK-2F7B2DA7-AACCFE', 'MOMO9EA39E7E96', '2026-01-27 04:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-4D034F58', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 1207500, NULL, 1207500, 1207500, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-31 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-31 00:00:00', '2026-01-31 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-02-08', '2026-02-09', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-deluxe-double', 'Deluxe Double', 'rt-davue-deluxe-double-breakfast-flex', 'Deluxe Double - Bao gồm bữa sáng', 1, 1, 1207500, 1207500, '2026-01-31 00:00:00', '2026-01-31 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1207500, 'VND', 'MOMO', 'BK-4D034F58-FD80CF', 'MOMO22461A1A3D', '2026-01-31 01:00:00', NULL, NULL, '2026-01-31 00:00:00', '2026-01-31 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-31E6F0FC', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 3220000, NULL, 3220000, 3220000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-19 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-19 00:00:00', '2026-04-19 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-04-25', '2026-04-27', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-family-room', 'Family Room', 'rt-davue-family-room-breakfast-flex', 'Family Room - Bao gồm bữa sáng', 1, 2, 1610000, 3220000, '2026-04-19 00:00:00', '2026-04-19 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3220000, 'VND', 'MOMO', 'BK-31E6F0FC-780D83', 'MOMO18E47DFFDC', '2026-04-19 03:00:00', NULL, NULL, '2026-04-19 00:00:00', '2026-04-19 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-88B54304', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1610000, NULL, 1610000, 1610000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-21 04:00:00', '2026-02-21 19:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-02-21 00:00:00', '2026-02-21 19:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-03-21', '2026-03-23', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-standard-double', 'Standard Double', 'rt-davue-standard-double-breakfast-flex', 'Standard Double - Bao gồm bữa sáng', 1, 2, 805000, 1610000, '2026-02-21 00:00:00', '2026-02-21 19:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1610000, 'VND', 'MOMO', 'BK-88B54304-B56953', 'MOMO62AE7D660F', '2026-02-21 04:00:00', 1610000, '2026-02-21 19:00:00', '2026-02-21 00:00:00', '2026-02-21 19:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9C0E7F86', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 1955000, NULL, 1955000, 1955000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-22 05:00:00', '2026-02-23 00:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-02-22 00:00:00', '2026-02-23 00:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-02-26', '2026-02-27', 1, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-superior-twin', 'Superior Twin', 'rt-davue-superior-twin-breakfast-flex', 'Superior Twin - Bao gồm bữa sáng', 2, 1, 977500, 1955000, '2026-02-22 00:00:00', '2026-02-23 00:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 1955000, 'VND', 'MOMO', 'BK-9C0E7F86-429638', 'MOMO7D57D0EEF7', '2026-02-22 05:00:00', 1955000, '2026-02-23 00:00:00', '2026-02-22 00:00:00', '2026-02-23 00:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-8BD2F995', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 2415000, NULL, 2415000, 2415000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-13 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-13 00:00:00', '2026-05-13 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f90740c291e093e9e116b', 'davue-hotel-da-nang', 'Davue Hotel Da Nang', '2026-05-24', '2026-05-26', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-davue-deluxe-double', 'Deluxe Double', 'rt-davue-deluxe-double-breakfast-flex', 'Deluxe Double - Bao gồm bữa sáng', 1, 2, 1207500, 2415000, '2026-05-13 00:00:00', '2026-05-13 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2415000, 'VND', 'MOMO', 'BK-8BD2F995-90706E', 'MOMOE3F9C28604', '2026-05-13 01:00:00', NULL, NULL, '2026-05-13 00:00:00', '2026-05-13 01:00:00');

-- ==== Lychee Hotel (6a1f906e0c291e093e9e116a) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2843EA9F', 19, 'Phan Thị Ngọc', '0901234519', NULL, 'FULL', 1300000, NULL, 1300000, 1300000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-21 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-21 00:00:00', '2026-03-21 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906e0c291e093e9e116a', 'lychee-hotel', 'Lychee Hotel', '2026-04-15', '2026-04-16', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lychee-family-room', 'Family Room', 'rt-lychee-family-room-prepaid-nonref', 'Family Room - Không gồm bữa sáng', 1, 1, 1300000, 1300000, '2026-03-21 00:00:00', '2026-03-21 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1300000, 'VND', 'MOMO', 'BK-2843EA9F-4861D7', 'MOMO31B4123065', '2026-03-21 05:00:00', NULL, NULL, '2026-03-21 00:00:00', '2026-03-21 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-15C25851', 20, 'Trịnh Văn Phát', '0901234520', NULL, 'FULL', 2400000, NULL, 2400000, 2400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-22 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-22 00:00:00', '2026-02-22 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906e0c291e093e9e116a', 'lychee-hotel', 'Lychee Hotel', '2026-03-12', '2026-03-16', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lychee-standard-double', 'Standard Double', 'rt-lychee-standard-double-prepaid-nonref', 'Standard Double - Không gồm bữa sáng', 1, 4, 600000, 2400000, '2026-02-22 00:00:00', '2026-02-22 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2400000, 'VND', 'MOMO', 'BK-15C25851-62EA87', 'MOMOEEEF87BB1D', '2026-02-22 05:00:00', NULL, NULL, '2026-02-22 00:00:00', '2026-02-22 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-2CEDB5DD', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 2160000, NULL, 2160000, 2160000, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-10 01:00:00', '2026-02-10 06:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-02-10 00:00:00', '2026-02-10 06:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906e0c291e093e9e116a', 'lychee-hotel', 'Lychee Hotel', '2026-02-24', '2026-02-27', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lychee-superior-twin', 'Superior Twin', 'rt-lychee-superior-twin-prepaid-nonref', 'Superior Twin - Không gồm bữa sáng', 1, 3, 720000, 2160000, '2026-02-10 00:00:00', '2026-02-10 06:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 2160000, 'VND', 'MOMO', 'BK-2CEDB5DD-F2FEDC', 'MOMOAF91BCCE1A', '2026-02-10 01:00:00', 2160000, '2026-02-10 06:00:00', '2026-02-10 00:00:00', '2026-02-10 06:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D608CC8F', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 3600000, NULL, 3600000, 3600000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-15 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-15 00:00:00', '2026-03-15 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906e0c291e093e9e116a', 'lychee-hotel', 'Lychee Hotel', '2026-03-20', '2026-03-22', 2, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lychee-deluxe-queen', 'Deluxe Queen', 'rt-lychee-deluxe-queen-prepaid-nonref', 'Deluxe Queen - Không gồm bữa sáng', 2, 2, 900000, 3600000, '2026-03-15 00:00:00', '2026-03-15 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3600000, 'VND', 'MOMO', 'BK-D608CC8F-C57CB1', 'MOMO803F098C70', '2026-03-15 02:00:00', NULL, NULL, '2026-03-15 00:00:00', '2026-03-15 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-CAF97F59', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 1050000, NULL, 1050000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2025-12-28 06:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2025-12-28 00:00:00', '2025-12-28 06:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f906e0c291e093e9e116a', 'lychee-hotel', 'Lychee Hotel', '2026-01-09', '2026-01-10', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-lychee-deluxe-city-view', 'Deluxe City View', 'rt-lychee-deluxe-city-view-prepaid-nonref', 'Deluxe City View - Không gồm bữa sáng', 1, 1, 1050000, 1050000, '2025-12-28 00:00:00', '2025-12-28 06:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1050000, 'VND', 'MOMO', 'BK-CAF97F59-842BFA', NULL, NULL, NULL, NULL, '2025-12-28 00:00:00', '2025-12-28 06:00:00');
