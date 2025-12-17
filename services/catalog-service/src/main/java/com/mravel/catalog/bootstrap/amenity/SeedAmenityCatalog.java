package com.mravel.catalog.bootstrap.amenity;

import java.time.Instant;
import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.enums.AmenitySection;
import com.mravel.catalog.repository.AmenityCatalogRepository;

@Configuration
public class SeedAmenityCatalog {

    @Bean
    CommandLineRunner seedAmenityCatalogRunner(AmenityCatalogRepository repo) {
        return args -> {
            System.out.println(">>> [SeedAmenityCatalog] start");

            Instant now = Instant.now();

            // 1) Tập rules chuẩn hoá code & alias
            // - Code trong DB: UPPER_SNAKE
            // - Alias: giữ dạng cũ (ac, wifi_free, air_conditioning, 24h_front_desk...) để
            // map
            List<AmenityCatalogDoc> seeds = new ArrayList<>();

            // =========================
            // HOTEL SCOPE (amenities hiển thị ở hotel detail)
            // =========================
            seeds.addAll(List.of(
                    // Highlight / “nổi bật” (nếu muốn) — vẫn scope HOTEL vì hiển thị ở trang KS
                    a(now, "WIFI_FREE", "WiFi miễn phí", AmenityScope.HOTEL, AmenityGroup.INTERNET,
                            AmenitySection.INTERNET, true, 10,
                            "wifi", List.of("wifi_free", "free_wifi", "public_wifi", "wifi (miễn phí)", "wifi")),
                    a(now, "PARKING", "Chỗ đậu xe", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.TRANSPORT, true, 20,
                            "parking",
                            List.of("parking", "public_parking", "secure_parking", "secured_parking", "bãi đậu xe")),
                    a(now, "RESTAURANT", "Nhà hàng", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.FOOD_AND_DRINK, true, 30,
                            "restaurant", List.of("restaurant", "public_restaurant")),
                    a(now, "FRONT_DESK_24H", "Lễ tân 24h", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.BASIC_FACILITIES, true, 40,
                            "front-desk",
                            List.of("front_desk_24h", "24h_front_desk", "reception_24h", "lễ tân 24 giờ")),

                    // Dịch vụ khách sạn
                    a(now, "WELCOME_DRINK", "Thức uống chào mừng miễn phí", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 110,
                            "welcome-drink", List.of("welcome_drink")),
                    a(now, "CONCIERGE", "Dịch vụ concierge/hỗ trợ khách", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 120,
                            "concierge", List.of("concierge")),
                    a(now, "CURRENCY_EXCHANGE", "Dịch vụ thu đổi ngoại tệ", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 130,
                            "currency-exchange", List.of("currency_exchange")),
                    a(now, "DOORMAN", "Người gác cửa", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.OTHER, false, 140,
                            "doorman", List.of("doorman")),
                    a(now, "EARLY_CHECKIN", "Nhận phòng sớm", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.BASIC_FACILITIES, false, 150,
                            "early-checkin", List.of("early_checkin")),
                    a(now, "EXPRESS_CHECKIN", "Dịch vụ nhận phòng cấp tốc", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 160,
                            "express-checkin", List.of("express_checkin")),
                    a(now, "EXPRESS_CHECKOUT", "Dịch vụ trả phòng cấp tốc", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 170,
                            "express-checkout", List.of("express_checkout")),
                    a(now, "LATE_CHECKOUT", "Trả phòng trễ", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.BASIC_FACILITIES, false, 180,
                            "late-checkout", List.of("late_checkout")),
                    a(now, "LAUNDRY", "Dịch vụ giặt ủi", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.OTHER, false, 190,
                            "laundry", List.of("laundry", "nearby_laundry")),
                    a(now, "ROOM_SERVICE_LIMITED", "Dịch vụ phòng (có giới hạn thời gian)", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false, 200,
                            "room-service", List.of("room_service_limited", "room_service")),
                    a(now, "LUGGAGE_STORAGE", "Dịch vụ lưu trữ/bảo quản hành lý", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 210,
                            "luggage", List.of("luggage_storage")),
                    a(now, "MULTILINGUAL_STAFF", "Đội ngũ nhân viên đa ngôn ngữ", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 220,
                            "staff", List.of("multilingual_staff")),
                    a(now, "TOUR_DESK", "Dịch vụ hỗ trợ đặt tour", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.OTHER, false, 230,
                            "tour", List.of("tour_desk", "tour_assistance", "tour_assist", "tour_support")),
                    a(now, "SECURITY_24H", "Bảo vệ 24 giờ", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.BASIC_FACILITIES, false, 240,
                            "security", List.of("security_24h")),

                    // Transport / đưa đón
                    a(now, "AIRPORT_TRANSFER", "Đưa đón sân bay (thu phí)", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false, 310,
                            "airport-shuttle", List.of("airport_transfer", "airport_shuttle")),
                    a(now, "TRAIN_STATION_PICKUP", "Đón khách tại ga tàu", AmenityScope.HOTEL,
                            AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false, 320,
                            "train", List.of("train_station_pickup")),
                    a(now, "BICYCLE_RENTAL", "Dịch vụ cho thuê xe đạp", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.TRANSPORT, false, 330,
                            "bicycle", List.of("bicycle_rental")),
                    a(now, "BICYCLE_STORAGE", "Giữ xe đạp", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.TRANSPORT, false, 340,
                            "bicycle-storage", List.of("bicycle_storage")),
                    a(now, "GARAGE", "Nhà chứa xe", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.TRANSPORT, false, 350,
                            "garage", List.of("garage")),

                    // Food & drink
                    a(now, "BREAKFAST", "Bữa sáng", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                            AmenitySection.FOOD_AND_DRINK, false, 410,
                            "breakfast", List.of("breakfast", "breakfast_paid")),

                    // Public / giải trí
                    a(now, "GARDEN", "Vườn hoa", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 510,
                            "garden", List.of("garden")),
                    a(now, "DART", "Phi tiêu", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 520,
                            "dart", List.of("dart")),
                    a(now, "TERRACE", "Sân thượng/sân hiên", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.OTHER, false, 530,
                            "terrace", List.of("terrace")),

                    // Room-type at hotel level (nhiều seed của bạn đang gắn ở hotel amenities)
                    a(now, "FAMILY_ROOM", "Phòng gia đình", AmenityScope.HOTEL, AmenityGroup.ROOM,
                            AmenitySection.BASIC_FACILITIES, false, 610,
                            "family", List.of("family_room", "family_rooms")),
                    a(now, "NON_SMOKING_ROOM", "Phòng không hút thuốc", AmenityScope.HOTEL, AmenityGroup.ROOM,
                            AmenitySection.BASIC_FACILITIES, false, 620,
                            "non-smoking", List.of("non_smoking_room", "non_smoking_rooms")),
                    a(now, "SMOKING_AREA", "Khu vực hút thuốc", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.OTHER, false, 630,
                            "smoking", List.of("smoking_area")),

                    // Nearby
                    a(now, "ATM", "Máy ATM/Ngân hàng", AmenityScope.HOTEL, AmenityGroup.NEARBY, AmenitySection.OTHER,
                            false, 710,
                            "atm", List.of("atm")),
                    a(now, "BEAUTY_SALON", "Thẩm mỹ viện", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                            AmenitySection.OTHER, false, 720,
                            "salon", List.of("beauty_salon")),
                    a(now, "HAIR_SALON", "Hiệu làm tóc", AmenityScope.HOTEL, AmenityGroup.NEARBY, AmenitySection.OTHER,
                            false, 730,
                            "hair-salon", List.of("hair_salon", "salon_nearby")),
                    a(now, "SHOP", "Cửa hàng", AmenityScope.HOTEL, AmenityGroup.NEARBY, AmenitySection.OTHER, false,
                            740,
                            "shop", List.of("shop", "gift_shop", "grocery")),
                    a(now, "SUPERMARKET", "Siêu thị", AmenityScope.HOTEL, AmenityGroup.NEARBY, AmenitySection.OTHER,
                            false, 750,
                            "supermarket", List.of("supermarket", "supermarket_nearby"))));

            // =========================
            // ROOM SCOPE (amenities hiển thị trong room type)
            // =========================
            seeds.addAll(List.of(
                    // Highlight (room)
                    a(now, "AC", "Máy lạnh", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, true,
                            10,
                            "air-conditioner", List.of("ac", "air_conditioning")),
                    a(now, "HOT_WATER", "Nước nóng", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.BATHROOM,
                            true, 20,
                            "hot-water", List.of("hot_water")),
                    a(now, "SHOWER", "Vòi tắm đứng", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.BATHROOM,
                            true, 30,
                            "shower", List.of("shower")),
                    a(now, "BALCONY", "Ban công / sân hiên", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, true, 40,
                            "balcony", List.of("balcony")),
                    a(now, "SEATING_AREA", "Khu vực chờ", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.BASIC_FACILITIES, false, 60,
                            "sofa", List.of("seating_area")),
                    a(now, "NON_SMOKING", "Phòng cấm hút thuốc", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.BASIC_FACILITIES, false, 70,
                            "non-smoking", List.of("non_smoking")),

                    // Room facilities
                    a(now, "TV", "TV", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false, 110,
                            "tv", List.of("tv", "cable_tv", "tv màn hình phẳng")),
                    a(now, "DESK", "Bàn làm việc", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES,
                            false, 120,
                            "desk", List.of("desk", "work_desk")),
                    a(now, "BLACKOUT_CURTAINS", "Rèm cản sáng", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 130,
                            "curtains", List.of("blackout_curtains", "BLACKOUT_CURTAINS")),
                    a(now, "BOTTLED_WATER", "Nước đóng chai miễn phí", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 140,
                            "water", List.of("bottled_water")),
                    a(now, "MINIBAR", "Quầy bar mini", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 150,
                            "minibar", List.of("minibar")),
                    a(now, "COFFEE_TEA_MAKER", "Máy pha cà phê / trà", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 160,
                            "coffee", List.of("coffee_tea_maker")),
                    a(now, "FAN", "Quạt", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false,
                            170,
                            "fan", List.of("fan")),
                    a(now, "MICROWAVE", "Lò vi sóng", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 180,
                            "microwave", List.of("microwave")),
                    a(now, "SAFE", "Két an toàn", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES,
                            false, 190,
                            "safe", List.of("safe", "in_room_safe")),
                    a(now, "SLIPPERS", "Dép đi trong phòng", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.ROOM_FACILITIES, false, 200,
                            "slippers", List.of("slippers")),

                    // Bathroom
                    a(now, "HAIR_DRYER", "Máy sấy tóc", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.BATHROOM,
                            false, 210,
                            "hair-dryer", List.of("hair_dryer")),
                    a(now, "BATH_AMENITIES", "Bộ vệ sinh cá nhân", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.BATHROOM, false, 220,
                            "toiletries", List.of("bath_amenities", "toiletries")),
                    a(now, "BATHROBE", "Áo choàng tắm", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.BATHROOM,
                            false, 230,
                            "bathrobe", List.of("bathrobe")),
                    a(now, "PRIVATE_BATHROOM", "Phòng tắm riêng", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.BATHROOM, false, 240,
                            "bathroom", List.of("private_bathroom")),
                    a(now, "BATHTUB", "Bồn tắm", AmenityScope.ROOM, AmenityGroup.ROOM, AmenitySection.BATHROOM, false,
                            250,
                            "bathtub", List.of("bathtub")),
                    a(now, "SHOWER_OR_BATHTUB", "Vòi sen hoặc bồn tắm", AmenityScope.ROOM, AmenityGroup.ROOM,
                            AmenitySection.BATHROOM, false, 260,
                            "shower-bathtub", List.of("shower_or_bathtub"))));

            // =========================
            // RESTAURANT SCOPE (amenities hiển thị ở restaurant detail)
            // =========================
            seeds.addAll(List.of(
                    // Internet
                    a(now, "WIFI", "WiFi miễn phí", AmenityScope.RESTAURANT, AmenityGroup.INTERNET,
                            AmenitySection.INTERNET, true, 10,
                            "wifi", List.of("wifi", "wifi_free", "free_wifi", "public_wifi")),

                    // Điều hoà
                    a(now, "AIR_CONDITIONING", "Có máy lạnh", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.BASIC_FACILITIES, true, 20,
                            "air-conditioner", List.of("ac", "air_conditioning", "air-conditioner")),

                    // Chỗ ngồi ngoài trời
                    a(now, "OUTDOOR_TABLE", "Có bàn ngoài trời", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 30,
                            "outdoor", List.of("outdoor_table", "outdoor_seating", "outdoor")),

                    // Hút thuốc
                    a(now, "SMOKING_AREA", "Khu vực hút thuốc", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.OTHER, false, 40,
                            "smoking", List.of("smoking_area", "smoking")),

                    // Trẻ em
                    a(now, "KIDS_CHAIR", "Ghế trẻ em", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.BASIC_FACILITIES, false, 50,
                            "kids", List.of("kids_chair", "child_chair", "baby_chair")),

                    // Đỗ xe
                    a(now, "FREE_CAR_PARKING", "Đỗ xe ô tô miễn phí", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.TRANSPORT, false, 60,
                            "parking", List.of("free_car_parking", "car_parking_free", "car_parking")),

                    a(now, "FREE_MOTORBIKE_PARKING", "Đỗ xe máy miễn phí", AmenityScope.RESTAURANT,
                            AmenityGroup.PUBLIC_AREA,
                            AmenitySection.TRANSPORT, false, 70,
                            "motorbike",
                            List.of("free_motorbike_parking", "motorbike_parking_free", "motorbike_parking")),

                    // Thanh toán
                    a(now, "VISA_MASTER", "Thanh toán thẻ Visa/Master", AmenityScope.RESTAURANT,
                            AmenityGroup.PUBLIC_AREA,
                            AmenitySection.BASIC_FACILITIES, false, 80,
                            "card", List.of("visa_master", "visa", "mastercard", "card_payment")),

                    // Hoá đơn
                    a(now, "VAT_INVOICE", "Xuất hoá đơn VAT", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.OTHER, false, 90,
                            "vat", List.of("vat_invoice", "invoice_vat", "vat")),

                    // Dịch vụ sự kiện / set menu
                    a(now, "SET_LUNCH", "Có set lunch / set menu", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.FOOD_AND_DRINK, false, 100,
                            "set-menu", List.of("set_lunch", "set_menu")),

                    a(now, "EVENT_DECOR", "Hỗ trợ trang trí sự kiện", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 110,
                            "decor", List.of("event_decor", "decor", "decoration")),

                    a(now, "MC_SERVICE", "Có MC / hỗ trợ dẫn chương trình", AmenityScope.RESTAURANT,
                            AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 120,
                            "mc", List.of("mc_service", "mc")),

                    // Âm thanh - ánh sáng / trình chiếu
                    a(now, "SOUND_LIGHT", "Âm thanh / ánh sáng", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 130,
                            "sound-light", List.of("sound_light", "sound", "light", "karaoke_sound")),

                    a(now, "PROJECTOR", "Máy chiếu", AmenityScope.RESTAURANT, AmenityGroup.PUBLIC_AREA,
                            AmenitySection.ENTERTAINMENT, false, 140,
                            "projector", List.of("projector", "beamer"))));

            // =========================
            // 2) Chuẩn hoá & upsert theo (code, scope)
            // =========================
            int upserted = 0;

            // dedupe ngay trong seed theo key (code, scope)
            Map<String, AmenityCatalogDoc> dedup = new LinkedHashMap<>();
            for (AmenityCatalogDoc it : seeds) {
                String code = normCode(it.getCode());
                AmenityScope scope = it.getScope();
                String key = code + "::" + scope.name();

                it.setCode(code);
                if (it.getAliases() != null) {
                    it.setAliases(it.getAliases().stream()
                            .filter(Objects::nonNull)
                            .map(String::trim)
                            .filter(s -> !s.isBlank())
                            .distinct()
                            .toList());
                }
                dedup.put(key, it);
            }

            for (AmenityCatalogDoc it : dedup.values()) {
                repo.findByCodeAndScope(it.getCode(), it.getScope())
                        .ifPresent(existing -> {
                            it.setId(existing.getId());
                            // giữ createdAt cũ nếu đã tồn tại
                            it.setCreatedAt(existing.getCreatedAt());
                        });

                if (it.getCreatedAt() == null)
                    it.setCreatedAt(now);
                it.setUpdatedAt(now);

                repo.save(it);
                upserted++;
            }

            System.out.println(">>> [SeedAmenityCatalog] upserted: " + upserted);
            System.out.println(">>> [SeedAmenityCatalog] done");
        };
    }

    // ===== helper: tạo doc =====
    private static AmenityCatalogDoc a(
            Instant now,
            String code,
            String name,
            AmenityScope scope,
            AmenityGroup group,
            AmenitySection section,
            boolean highlight,
            Integer sortOrder,
            String icon,
            List<String> aliases) {
        return AmenityCatalogDoc.builder()
                .code(code)
                .name(name)
                .scope(scope)
                .group(group)
                .section(section)
                .icon(icon)
                .description(null)
                .aliases(aliases == null ? List.of() : aliases)
                .sortOrder(sortOrder)
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private static String normCode(String code) {
        if (code == null)
            return null;
        String c = code.trim();
        if (c.isEmpty())
            return null;

        // hỗ trợ input kiểu "free_wifi", "air_conditioning", "24h_front_desk"
        c = c.replace('-', '_');

        // nếu có kí tự lạ, bạn có thể mở rộng thêm rule
        return c.toUpperCase(Locale.ROOT);
    }
}
