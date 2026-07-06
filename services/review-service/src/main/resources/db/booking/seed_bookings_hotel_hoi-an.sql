-- Seed demo bookings HOTEL cho khu vuc: hoi-an
-- 5-8 booking/hotel, ngay check-in rai trong 1-6 thang qua, thanh toan FULL qua MOMO_WALLET
-- Status: da so CONFIRMED+SUCCESS, mot so CANCELLED_BY_GUEST+REFUNDED/FAILED
USE mravel_booking;
SET NAMES utf8mb4;

-- ==== Hoa Thu Homestay (6a1ba806396a1b2be58bfe56) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6738B42C', 5, 'Nguyễn Minh Như', '0901234501', NULL, 'FULL', 469929, NULL, 469929, 469929, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-22 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-22 00:00:00', '2025-12-22 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe56', 'hoa-thu-homestay', 'Hoa Thu Homestay', '2026-01-12', '2026-01-13', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hoa-thu-family', 'Family', 'rp-hoa-thu-family-nonref-prepaid', 'Family - Không gồm bữa sáng', 1, 1, 469929, 469929, '2025-12-22 00:00:00', '2025-12-22 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 469929, 'VND', 'MOMO', 'BK-6738B42C-248C1E', 'MOMO35484F3E32', '2025-12-22 03:00:00', NULL, NULL, '2025-12-22 00:00:00', '2025-12-22 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-ED025601', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 1468524, NULL, 1468524, 1468524, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-27 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-27 00:00:00', '2026-01-27 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe56', 'hoa-thu-homestay', 'Hoa Thu Homestay', '2026-02-25', '2026-02-28', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hoa-thu-family-with-balcony', 'Family With Balcony', 'rp-hoa-thu-family-balcony-room-only', 'Family With Balcony - Không gồm bữa sáng', 1, 3, 489508, 1468524, '2026-01-27 00:00:00', '2026-01-27 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1468524, 'VND', 'MOMO', 'BK-ED025601-E53A50', 'MOMO567FBB5522', '2026-01-27 04:00:00', NULL, NULL, '2026-01-27 00:00:00', '2026-01-27 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-E18AEFD9', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 2819574, NULL, 2819574, 2819574, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-20 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-20 00:00:00', '2026-02-20 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe56', 'hoa-thu-homestay', 'Hoa Thu Homestay', '2026-03-17', '2026-03-20', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hoa-thu-family', 'Family', 'rp-hoa-thu-family-nonref-prepaid', 'Family - Không gồm bữa sáng', 2, 3, 469929, 2819574, '2026-02-20 00:00:00', '2026-02-20 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2819574, 'VND', 'MOMO', 'BK-E18AEFD9-8D6AB8', 'MOMOF8D085B8CF', '2026-02-20 05:00:00', NULL, NULL, '2026-02-20 00:00:00', '2026-02-20 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-DA099BF4', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 979016, NULL, 979016, 979016, 'VND', 'CONFIRMED', 'SUCCESS', '2026-06-03 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-06-03 00:00:00', '2026-06-03 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe56', 'hoa-thu-homestay', 'Hoa Thu Homestay', '2026-06-06', '2026-06-08', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hoa-thu-family-with-balcony', 'Family With Balcony', 'rp-hoa-thu-family-balcony-room-only', 'Family With Balcony - Không gồm bữa sáng', 1, 2, 489508, 979016, '2026-06-03 00:00:00', '2026-06-03 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 979016, 'VND', 'MOMO', 'BK-DA099BF4-3DDBAF', 'MOMOAC9D2F8926', '2026-06-03 05:00:00', NULL, NULL, '2026-06-03 00:00:00', '2026-06-03 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9CD38782', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 1409787, NULL, 1409787, 1409787, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-30 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-30 00:00:00', '2025-12-30 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe56', 'hoa-thu-homestay', 'Hoa Thu Homestay', '2026-01-15', '2026-01-18', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-hoa-thu-family', 'Family', 'rp-hoa-thu-family-nonref-prepaid', 'Family - Không gồm bữa sáng', 1, 3, 469929, 1409787, '2025-12-30 00:00:00', '2025-12-30 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1409787, 'VND', 'MOMO', 'BK-9CD38782-267B55', 'MOMO7720201EF0', '2025-12-30 02:00:00', NULL, NULL, '2025-12-30 00:00:00', '2025-12-30 02:00:00');

-- ==== Hoi An Reverie Villas (6a1ba808396a1b2be58bfe60) | 6 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-0090C12F', 7, 'Lý Ngọc Anh Thư', '0901234507', NULL, 'FULL', 1675872, NULL, 1675872, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-05-10 15:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-05-10 00:00:00', '2026-05-10 15:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-05-24', '2026-05-28', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'superior-king-room', 'Superior King Room', 'superior-king-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 1, 4, 418968, 1675872, '2026-05-10 00:00:00', '2026-05-10 15:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1675872, 'VND', 'MOMO', 'BK-0090C12F-DE88E2', NULL, NULL, NULL, NULL, '2026-05-10 00:00:00', '2026-05-10 15:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-AB1BE2AF', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 5408496, NULL, 5408496, 5408496, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-02-02 02:00:00', '2026-02-02 01:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-02-02 00:00:00', '2026-02-02 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-02-28', '2026-03-03', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'quadruple-family-suite-pool-view', 'Quadruple Family Suite, Pool View', 'quadruple-family-suite-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 2, 3, 901416, 5408496, '2026-02-02 00:00:00', '2026-02-02 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 5408496, 'VND', 'MOMO', 'BK-AB1BE2AF-795483', 'MOMODB1C0875A8', '2026-02-02 02:00:00', 5408496, '2026-02-02 01:00:00', '2026-02-02 00:00:00', '2026-02-02 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-899DDA36', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 3073360, NULL, 3073360, 3073360, 'VND', 'CANCELLED_BY_GUEST', 'REFUNDED', '2026-03-09 02:00:00', '2026-03-09 10:00:00', 'Khách đổi lịch trình, hủy trước ngày nhận phòng.', 1, 'MOMO_WALLET', '2026-03-09 00:00:00', '2026-03-09 10:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-03-24', '2026-03-28', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'deluxe-double-garden-view', 'Deluxe Double Room, Garden View', 'deluxe-double-garden-view-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 2, 4, 384170, 3073360, '2026-03-09 00:00:00', '2026-03-09 10:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'REFUNDED', 3073360, 'VND', 'MOMO', 'BK-899DDA36-C023A0', 'MOMO55A4E6604B', '2026-03-09 02:00:00', 3073360, '2026-03-09 10:00:00', '2026-03-09 00:00:00', '2026-03-09 10:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-57DED8DE', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 1256904, NULL, 1256904, 1256904, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-03-15', '2026-03-18', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'superior-king-room', 'Superior King Room', 'superior-king-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 1, 3, 418968, 1256904, '2026-03-03 00:00:00', '2026-03-03 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1256904, 'VND', 'MOMO', 'BK-57DED8DE-D64B52', 'MOMOA5FC2CEF85', '2026-03-03 02:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-D43A183D', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 7211328, NULL, 7211328, 7211328, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-12 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-12 00:00:00', '2026-01-12 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-01-18', '2026-01-22', 4, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'quadruple-family-suite-pool-view', 'Quadruple Family Suite, Pool View', 'quadruple-family-suite-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 2, 4, 901416, 7211328, '2026-01-12 00:00:00', '2026-01-12 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 7211328, 'VND', 'MOMO', 'BK-D43A183D-E3E74C', 'MOMO6142B2B868', '2026-01-12 03:00:00', NULL, NULL, '2026-01-12 00:00:00', '2026-01-12 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-84D40C03', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 1536680, NULL, 1536680, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-09 18:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 18:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba808396a1b2be58bfe60', 'hoi-an-reverie-villas', 'Hoi An Reverie Villas', '2026-03-07', '2026-03-11', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'deluxe-double-garden-view', 'Deluxe Double Room, Garden View', 'deluxe-double-garden-view-standard-pay-at-hotel', 'Standard Rate - Pay at Hotel', 1, 4, 384170, 1536680, '2026-02-09 00:00:00', '2026-02-09 18:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 1536680, 'VND', 'MOMO', 'BK-84D40C03-A44C88', NULL, NULL, NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 18:00:00');

-- ==== Bespoke Villa Hoi An (6a1ba806396a1b2be58bfe57) | 7 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-6EFE336B', 9, 'Trần Gia Bảo', '0901234509', NULL, 'FULL', 379932, NULL, 379932, 379932, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-11 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-11 00:00:00', '2026-04-11 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-04-15', '2026-04-16', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-deluxe-twin', 'Deluxe Twin', 'rp-bespoke-deluxe-twin-room-only-nonref-1', 'Deluxe Twin Room - Room Only', 1, 1, 379932, 379932, '2026-04-11 00:00:00', '2026-04-11 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 379932, 'VND', 'MOMO', 'BK-6EFE336B-F7647A', 'MOMOC1BB750998', '2026-04-11 05:00:00', NULL, NULL, '2026-04-11 00:00:00', '2026-04-11 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1DFD46F0', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 2543788, NULL, 2543788, 2543788, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-17 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-17 00:00:00', '2026-05-17 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-06-02', '2026-06-06', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-family', 'Family', 'rp-bespoke-family-room-only-nonref', 'Family Room - Room Only', 1, 4, 635947, 2543788, '2026-05-17 00:00:00', '2026-05-17 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2543788, 'VND', 'MOMO', 'BK-1DFD46F0-366EEE', 'MOMO95AF9E3FD0', '2026-05-17 04:00:00', NULL, NULL, '2026-05-17 00:00:00', '2026-05-17 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9FDCFCF5', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 379932, NULL, 379932, 379932, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-09 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-09 00:00:00', '2026-04-09 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-05-06', '2026-05-07', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-deluxe-twin', 'Deluxe Twin', 'rp-bespoke-deluxe-twin-room-only-nonref-1', 'Deluxe Twin Room - Room Only', 1, 1, 379932, 379932, '2026-04-09 00:00:00', '2026-04-09 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 379932, 'VND', 'MOMO', 'BK-9FDCFCF5-1744B9', 'MOMO0437056D07', '2026-04-09 03:00:00', NULL, NULL, '2026-04-09 00:00:00', '2026-04-09 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-49D4F7A9', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 3815682, NULL, 3815682, 3815682, 'VND', 'CONFIRMED', 'SUCCESS', '2026-03-03 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-03-03 00:00:00', '2026-03-03 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-03-06', '2026-03-09', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-family', 'Family', 'rp-bespoke-family-room-only-nonref', 'Family Room - Room Only', 2, 3, 635947, 3815682, '2026-03-03 00:00:00', '2026-03-03 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3815682, 'VND', 'MOMO', 'BK-49D4F7A9-178F84', 'MOMO5ED8D016C2', '2026-03-03 05:00:00', NULL, NULL, '2026-03-03 00:00:00', '2026-03-03 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7E873542', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 1519728, NULL, 1519728, 1519728, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-09 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-09 00:00:00', '2026-05-09 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-05-26', '2026-05-30', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-deluxe-twin', 'Deluxe Twin', 'rp-bespoke-deluxe-twin-room-only-nonref-1', 'Deluxe Twin Room - Room Only', 1, 4, 379932, 1519728, '2026-05-09 00:00:00', '2026-05-09 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1519728, 'VND', 'MOMO', 'BK-7E873542-B370D0', 'MOMOB17E466F8F', '2026-05-09 04:00:00', NULL, NULL, '2026-05-09 00:00:00', '2026-05-09 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-944103F5', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 635947, NULL, 635947, 635947, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-06 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-06 00:00:00', '2026-04-06 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-04-24', '2026-04-25', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-family', 'Family', 'rp-bespoke-family-room-only-nonref', 'Family Room - Room Only', 1, 1, 635947, 635947, '2026-04-06 00:00:00', '2026-04-06 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 635947, 'VND', 'MOMO', 'BK-944103F5-F0818B', 'MOMO81D390E2E1', '2026-04-06 05:00:00', NULL, NULL, '2026-04-06 00:00:00', '2026-04-06 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-754FCB70', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 759864, NULL, 759864, 759864, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-04 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-04 00:00:00', '2026-02-04 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1ba806396a1b2be58bfe57', 'bespoke-villa-hoi-an', 'Bespoke Villa Hoi An', '2026-02-24', '2026-02-26', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-bespoke-deluxe-twin', 'Deluxe Twin', 'rp-bespoke-deluxe-twin-room-only-nonref-1', 'Deluxe Twin Room - Room Only', 1, 2, 379932, 759864, '2026-02-04 00:00:00', '2026-02-04 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 759864, 'VND', 'MOMO', 'BK-754FCB70-650477', 'MOMOF05BD4285B', '2026-02-04 05:00:00', NULL, NULL, '2026-02-04 00:00:00', '2026-02-04 05:00:00');

-- ==== Nature Resort Hoi An (6a1f71df960a174b45835a01) | 8 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-061D031A', 10, 'Phạm Thu Hà', '0901234510', NULL, 'FULL', 2477205, NULL, 2477205, 2477205, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-29 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-29 00:00:00', '2026-04-29 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-05-28', '2026-05-31', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-family-triple-courtyard-view', 'Family Triple Courtyard View', 'rt-nature-family-triple-courtyard-view-payathotel-flex', 'Family Triple Courtyard View - Không gồm bữa sáng', 1, 3, 825735, 2477205, '2026-04-29 00:00:00', '2026-04-29 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2477205, 'VND', 'MOMO', 'BK-061D031A-93E06C', 'MOMOA6B79824D6', '2026-04-29 03:00:00', NULL, NULL, '2026-04-29 00:00:00', '2026-04-29 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-654303C7', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 3137205, NULL, 3137205, 3137205, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-31 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-31 00:00:00', '2025-12-31 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-01-27', '2026-01-30', 3, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-family-quadruple-courtyard-view', 'Family Quadruple Courtyard View', 'rt-nature-family-quadruple-courtyard-view-payathotel-flex', 'Family Quadruple Courtyard View - Không gồm bữa sáng', 1, 3, 1045735, 3137205, '2025-12-31 00:00:00', '2025-12-31 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3137205, 'VND', 'MOMO', 'BK-654303C7-6DBD11', 'MOMOADA5DDAB15', '2025-12-31 02:00:00', NULL, NULL, '2025-12-31 00:00:00', '2025-12-31 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-63EB80F3', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 506735, NULL, 506735, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-01-04 16:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-01-04 00:00:00', '2026-01-04 16:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-02-02', '2026-02-03', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-standard-double-courtyard-view', 'Standard Double Courtyard View', 'rt-nature-standard-double-courtyard-view-payathotel-flex', 'Standard Double Courtyard View - Không gồm bữa sáng', 1, 1, 506735, 506735, '2026-01-04 00:00:00', '2026-01-04 16:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 506735, 'VND', 'MOMO', 'BK-63EB80F3-3E5B34', NULL, NULL, NULL, NULL, '2026-01-04 00:00:00', '2026-01-04 16:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1C41F6ED', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 1233470, NULL, 1233470, 1233470, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-18 04:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-18 00:00:00', '2026-02-18 04:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-03-17', '2026-03-19', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-deluxe-double-courtyard-view', 'Deluxe Double Courtyard View', 'rt-nature-deluxe-double-courtyard-view-payathotel-flex', 'Deluxe Double Courtyard View - Không gồm bữa sáng', 1, 2, 616735, 1233470, '2026-02-18 00:00:00', '2026-02-18 04:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1233470, 'VND', 'MOMO', 'BK-1C41F6ED-51AEDA', 'MOMO0B8279D688', '2026-02-18 04:00:00', NULL, NULL, '2026-02-18 00:00:00', '2026-02-18 04:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-7769C3FB', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 1200470, NULL, 1200470, 1200470, 'VND', 'CONFIRMED', 'SUCCESS', '2026-01-13 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-01-13 00:00:00', '2026-01-13 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-01-18', '2026-01-20', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-deluxe-twin-courtyard-view', 'Deluxe Twin Courtyard View', 'rt-nature-deluxe-twin-courtyard-view-payathotel-flex', 'Deluxe Twin Courtyard View - Không gồm bữa sáng', 1, 2, 600235, 1200470, '2026-01-13 00:00:00', '2026-01-13 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 1200470, 'VND', 'MOMO', 'BK-7769C3FB-9905DF', 'MOMOF064274391', '2026-01-13 05:00:00', NULL, NULL, '2026-01-13 00:00:00', '2026-01-13 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-9381FBC2', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 825735, NULL, 825735, 825735, 'VND', 'CONFIRMED', 'SUCCESS', '2026-04-10 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-04-10 00:00:00', '2026-04-10 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-04-15', '2026-04-16', 1, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-family-triple-courtyard-view', 'Family Triple Courtyard View', 'rt-nature-family-triple-courtyard-view-payathotel-flex', 'Family Triple Courtyard View - Không gồm bữa sáng', 1, 1, 825735, 825735, '2026-04-10 00:00:00', '2026-04-10 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 825735, 'VND', 'MOMO', 'BK-9381FBC2-725A44', 'MOMO7876FF9E3F', '2026-04-10 01:00:00', NULL, NULL, '2026-04-10 00:00:00', '2026-04-10 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-3CC47CA7', 17, 'Ngô Thị Lan', '0901234517', NULL, 'FULL', 2091470, NULL, 2091470, 2091470, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-12 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-12 00:00:00', '2026-02-12 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-03-11', '2026-03-12', 1, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-family-quadruple-courtyard-view', 'Family Quadruple Courtyard View', 'rt-nature-family-quadruple-courtyard-view-payathotel-flex', 'Family Quadruple Courtyard View - Không gồm bữa sáng', 2, 1, 1045735, 2091470, '2026-02-12 00:00:00', '2026-02-12 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2091470, 'VND', 'MOMO', 'BK-3CC47CA7-6587F2', 'MOMOA88799D6BA', '2026-02-12 02:00:00', NULL, NULL, '2026-02-12 00:00:00', '2026-02-12 02:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-50502454', 18, 'Lê Đức Minh', '0901234518', NULL, 'FULL', 2026940, NULL, 2026940, 2026940, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-09 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f71df960a174b45835a01', 'nature-resort-hoi-an', 'Nature Resort Hoi An', '2026-03-08', '2026-03-12', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-nature-standard-double-courtyard-view', 'Standard Double Courtyard View', 'rt-nature-standard-double-courtyard-view-payathotel-flex', 'Standard Double Courtyard View - Không gồm bữa sáng', 1, 4, 506735, 2026940, '2026-02-09 00:00:00', '2026-02-09 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 2026940, 'VND', 'MOMO', 'BK-50502454-95FABD', 'MOMO79C337D707', '2026-02-09 01:00:00', NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 01:00:00');

-- ==== Wyndham Hoi An Royal Beachfront Resort & Villas (6a1f7edd72e88e21a6c43c9c) | 5 bookings ====
INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-C9D305A4', 11, 'Đặng Quốc Huy', '0901234511', NULL, 'FULL', 26000000, NULL, 26000000, NULL, 'VND', 'CANCELLED_BY_GUEST', 'FAILED', NULL, '2026-02-09 08:00:00', 'Khách không hoàn tất thanh toán trong thời hạn giữ chỗ.', 1, 'MOMO_WALLET', '2026-02-09 00:00:00', '2026-02-09 08:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f7edd72e88e21a6c43c9c', 'wyndham-hoi-an-royal-beachfront-resort-villas', 'Wyndham Hoi An Royal Beachfront Resort & Villas', '2026-02-15', '2026-02-19', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-wyndham-two-bedroom-pool-villa', 'Two-Bedroom Pool Villa', 'rt-wyndham-two-bedroom-pool-villa-prepaid-nonref', 'Two-Bedroom Pool Villa - Không gồm bữa sáng', 1, 4, 6500000, 26000000, '2026-02-09 00:00:00', '2026-02-09 08:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'FAILED', 26000000, 'VND', 'MOMO', 'BK-C9D305A4-970046', NULL, NULL, NULL, NULL, '2026-02-09 00:00:00', '2026-02-09 08:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-8F70EC3E', 12, 'Vũ Thị Mai', '0901234512', NULL, 'FULL', 11100000, NULL, 11100000, 11100000, 'VND', 'CONFIRMED', 'SUCCESS', '2025-12-25 03:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2025-12-25 00:00:00', '2025-12-25 03:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f7edd72e88e21a6c43c9c', 'wyndham-hoi-an-royal-beachfront-resort-villas', 'Wyndham Hoi An Royal Beachfront Resort & Villas', '2026-01-14', '2026-01-17', 3, 2);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-wyndham-deluxe-king-ocean-view', 'Deluxe King Ocean View', 'rt-wyndham-deluxe-king-ocean-view-prepaid-nonref', 'Deluxe King Ocean View - Không gồm bữa sáng', 2, 3, 1850000, 11100000, '2025-12-25 00:00:00', '2025-12-25 03:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 11100000, 'VND', 'MOMO', 'BK-8F70EC3E-0F538D', 'MOMO0AFB8350B5', '2025-12-25 03:00:00', NULL, NULL, '2025-12-25 00:00:00', '2025-12-25 03:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-66E5762E', 14, 'Hoàng Anh Tuấn', '0901234514', NULL, 'FULL', 3700000, NULL, 3700000, 3700000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-08 01:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-08 00:00:00', '2026-02-08 01:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f7edd72e88e21a6c43c9c', 'wyndham-hoi-an-royal-beachfront-resort-villas', 'Wyndham Hoi An Royal Beachfront Resort & Villas', '2026-02-26', '2026-02-28', 2, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-wyndham-deluxe-twin-ocean-view', 'Deluxe Twin Ocean View', 'rt-wyndham-deluxe-twin-ocean-view-prepaid-nonref', 'Deluxe Twin Ocean View - Không gồm bữa sáng', 1, 2, 1850000, 3700000, '2026-02-08 00:00:00', '2026-02-08 01:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 3700000, 'VND', 'MOMO', 'BK-66E5762E-3D9354', 'MOMO2512A2A303', '2026-02-08 01:00:00', NULL, NULL, '2026-02-08 00:00:00', '2026-02-08 01:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1799CCF7', 15, 'Bùi Thanh Trúc', '0901234515', NULL, 'FULL', 10400000, NULL, 10400000, 10400000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-02-13 05:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-02-13 00:00:00', '2026-02-13 05:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f7edd72e88e21a6c43c9c', 'wyndham-hoi-an-royal-beachfront-resort-villas', 'Wyndham Hoi An Royal Beachfront Resort & Villas', '2026-02-27', '2026-03-03', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-wyndham-grand-deluxe-family', 'Grand Deluxe Family', 'rt-wyndham-grand-deluxe-family-prepaid-nonref', 'Grand Deluxe Family - Không gồm bữa sáng', 1, 4, 2600000, 10400000, '2026-02-13 00:00:00', '2026-02-13 05:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 10400000, 'VND', 'MOMO', 'BK-1799CCF7-014FC0', 'MOMO8AD91E62CA', '2026-02-13 05:00:00', NULL, NULL, '2026-02-13 00:00:00', '2026-02-13 05:00:00');

INSERT INTO bookings (code, user_id, contact_name, contact_phone, contact_email, pay_option, total_amount, deposit_amount, amount_payable, amount_paid, currency_code, status, payment_status, paid_at, cancelled_at, cancel_reason, inventory_deducted, active_payment_method, created_at, updated_at)
VALUES ('BK-1309BEB4', 16, 'Đỗ Văn Kiên', '0901234516', NULL, 'FULL', 14000000, NULL, 14000000, 14000000, 'VND', 'CONFIRMED', 'SUCCESS', '2026-05-19 02:00:00', NULL, NULL, 1, 'MOMO_WALLET', '2026-05-19 00:00:00', '2026-05-19 02:00:00');
SET @bid := LAST_INSERT_ID();
INSERT INTO hotel_bookings (id, hotel_id, hotel_slug, hotel_name, check_in_date, check_out_date, nights, rooms_count)
VALUES (@bid, '6a1f7edd72e88e21a6c43c9c', 'wyndham-hoi-an-royal-beachfront-resort-villas', 'Wyndham Hoi An Royal Beachfront Resort & Villas', '2026-06-01', '2026-06-05', 4, 1);
INSERT INTO booking_rooms (hotel_booking_id, room_type_id, room_type_name, rate_plan_id, rate_plan_name, quantity, nights, price_per_night, total_amount, created_at, updated_at)
VALUES (@bid, 'rt-wyndham-one-bedroom-suite-ocean-view', 'One-Bedroom Suite Ocean View', 'rt-wyndham-one-bedroom-suite-ocean-view-prepaid-nonref', 'One-Bedroom Suite Ocean View - Không gồm bữa sáng', 1, 4, 3500000, 14000000, '2026-05-19 00:00:00', '2026-05-19 02:00:00');
INSERT INTO payments (booking_id, method, status, amount, currency_code, provider, provider_request_id, provider_transaction_id, paid_at, refunded_amount, refunded_at, created_at, updated_at)
VALUES (@bid, 'MOMO_WALLET', 'SUCCESS', 14000000, 'VND', 'MOMO', 'BK-1309BEB4-873639', 'MOMOC5832A945C', '2026-05-19 02:00:00', NULL, NULL, '2026-05-19 00:00:00', '2026-05-19 02:00:00');
