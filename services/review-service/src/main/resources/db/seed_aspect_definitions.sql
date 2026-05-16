-- Seed data for review_aspect_definitions
-- Run ONCE after service starts (Hibernate ddl-auto: update creates tables)
-- IMPORTANT: Must use --default-character-set=utf8mb4 flag:
--   mysql --default-character-set=utf8mb4 -u root -p mravel_review < seed_aspect_definitions.sql

SET NAMES utf8mb4;

-- HOTEL aspects
INSERT INTO review_aspect_definitions (code, category, label_vi, label_en, icon, sort_order, active) VALUES
('CLEANLINESS',    'HOTEL', 'Vệ sinh',             'Cleanliness',     'sparkles',     1, true),
('ROOM_QUALITY',   'HOTEL', 'Chất lượng phòng',    'Room quality',    'bed',          2, true),
('STAFF_SERVICE',  'HOTEL', 'Dịch vụ nhân viên',   'Staff service',   'concierge',    3, true),
('LOCATION',       'HOTEL', 'Vị trí',              'Location',        'map-pin',      4, true),
('VALUE_FOR_MONEY','HOTEL', 'Giá trị tiền bỏ ra',  'Value for money', 'wallet',       5, true),
('AMENITIES',      'HOTEL', 'Tiện nghi',            'Amenities',       'tv',           6, true),
('BREAKFAST',      'HOTEL', 'Bữa sáng',             'Breakfast',       'coffee',       7, true),
('WIFI',           'HOTEL', 'Wifi',                 'WiFi',            'wifi',         8, true);

-- RESTAURANT aspects
INSERT INTO review_aspect_definitions (code, category, label_vi, label_en, icon, sort_order, active) VALUES
('FOOD_QUALITY',  'RESTAURANT', 'Chất lượng đồ ăn',  'Food quality',  'utensils',   1, true),
('SERVICE',       'RESTAURANT', 'Dịch vụ',           'Service',       'concierge',  2, true),
('AMBIANCE',      'RESTAURANT', 'Không khí quán',    'Ambiance',      'lamp',       3, true),
('PRICE',         'RESTAURANT', 'Giá cả',            'Price',         'wallet',     4, true),
('HYGIENE',       'RESTAURANT', 'Vệ sinh',           'Hygiene',       'sparkles',   5, true),
('PORTION_SIZE',  'RESTAURANT', 'Khẩu phần',         'Portion size',  'scale',      6, true),
('WAIT_TIME',     'RESTAURANT', 'Thời gian chờ',     'Wait time',     'clock',      7, true),
('LOCATION',      'RESTAURANT', 'Vị trí',            'Location',      'map-pin',    8, true);

-- PLACE aspects
INSERT INTO review_aspect_definitions (code, category, label_vi, label_en, icon, sort_order, active) VALUES
('SCENERY',       'PLACE', 'Cảnh quan',             'Scenery',        'mountain',    1, true),
('ACCESSIBILITY', 'PLACE', 'Đường đi / Phương tiện','Accessibility',  'road',        2, true),
('FACILITIES',    'PLACE', 'Cơ sở vật chất',        'Facilities',     'building',    3, true),
('CROWDS',        'PLACE', 'Lượng khách',            'Crowds',         'users',       4, true),
('VALUE',         'PLACE', 'Giá vé',                 'Entry price',    'ticket',      5, true),
('SAFETY',        'PLACE', 'An toàn',                'Safety',         'shield',      6, true);
