// src/main/java/com/mravel/catalog/bootstrap/amenity/SeedAmenityCatalog.java
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
                        List<AmenityCatalogDoc> seeds = new ArrayList<>();

                        // =========================
                        // HOTEL SCOPE
                        // =========================
                        seeds.addAll(List.of(
                                        a(now, "WIFI_FREE", "WiFi miễn phí", AmenityScope.HOTEL, AmenityGroup.INTERNET,
                                                        AmenitySection.INTERNET, true, 10,
                                                        "lucide:wifi",
                                                        List.of("wifi_free", "free_wifi", "public_wifi",
                                                                        "wifi (miễn phí)", "wifi")),

                                        a(now, "PARKING", "Chỗ đậu xe", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, true, 20,
                                                        "lucide:parking-square",
                                                        List.of("parking", "public_parking", "secure_parking",
                                                                        "secured_parking", "bãi đậu xe")),

                                        a(now, "RESTAURANT", "Nhà hàng", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, true, 30,
                                                        "lucide:utensils-crossed",
                                                        List.of("restaurant", "public_restaurant")),

                                        a(now, "FRONT_DESK_24H", "Lễ tân 24h", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, true, 40,
                                                        "lucide:bell",
                                                        List.of("front_desk_24h", "24h_front_desk", "reception_24h",
                                                                        "lễ tân 24 giờ")),

                                        // Services
                                        a(now, "WELCOME_DRINK", "Thức uống chào mừng miễn phí", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 110,
                                                        "lucide:glass-water", List.of("welcome_drink")),

                                        a(now, "CONCIERGE", "Dịch vụ concierge/hỗ trợ khách", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 120,
                                                        "lucide:concierge-bell", List.of("concierge")),

                                        a(now, "CURRENCY_EXCHANGE", "Dịch vụ thu đổi ngoại tệ", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 130,
                                                        "lucide:coins", List.of("currency_exchange")),

                                        a(now, "DOORMAN", "Người gác cửa", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 140,
                                                        "lucide:user-round", List.of("doorman")),

                                        a(now, "EARLY_CHECKIN", "Nhận phòng sớm", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 150,
                                                        "lucide:log-in", List.of("early_checkin")),

                                        a(now, "EXPRESS_CHECKIN", "Dịch vụ nhận phòng cấp tốc", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 160,
                                                        "lucide:zap", List.of("express_checkin")),

                                        a(now, "EXPRESS_CHECKOUT", "Dịch vụ trả phòng cấp tốc", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 170,
                                                        "lucide:log-out", List.of("express_checkout")),

                                        a(now, "LATE_CHECKOUT", "Trả phòng trễ", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 180,
                                                        "lucide:clock", List.of("late_checkout")),

                                        a(now, "LAUNDRY", "Dịch vụ giặt ủi", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 190,
                                                        "mdi:washing-machine", List.of("laundry", "nearby_laundry")),

                                        a(now, "ROOM_SERVICE_LIMITED", "Dịch vụ phòng (có giới hạn thời gian)",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK,
                                                        false, 200,
                                                        "mdi:room-service",
                                                        List.of("room_service_limited", "room_service")),

                                        a(now, "LUGGAGE_STORAGE", "Dịch vụ lưu trữ/bảo quản hành lý",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 210,
                                                        "lucide:luggage", List.of("luggage_storage")),

                                        a(now, "MULTILINGUAL_STAFF", "Đội ngũ nhân viên đa ngôn ngữ",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 220,
                                                        "lucide:languages", List.of("multilingual_staff")),

                                        a(now, "TOUR_DESK", "Dịch vụ hỗ trợ đặt tour", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 230,
                                                        "lucide:map",
                                                        List.of("tour_desk", "tour_assistance", "tour_assist",
                                                                        "tour_support")),

                                        a(now, "SECURITY_24H", "Bảo vệ 24 giờ", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 240,
                                                        "lucide:shield-check", List.of("security_24h")),

                                        // Transport
                                        a(now, "AIRPORT_TRANSFER", "Đưa đón sân bay (thu phí)", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false,
                                                        310,
                                                        "lucide:plane", List.of("airport_transfer", "airport_shuttle")),

                                        a(now, "TRAIN_STATION_PICKUP", "Đón khách tại ga tàu", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false,
                                                        320,
                                                        "lucide:train-front", List.of("train_station_pickup")),

                                        a(now, "BICYCLE_RENTAL", "Dịch vụ cho thuê xe đạp", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.TRANSPORT, false, 330,
                                                        "lucide:bike", List.of("bicycle_rental")),

                                        a(now, "BICYCLE_STORAGE", "Giữ xe đạp", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 340,
                                                        "lucide:bike", List.of("bicycle_storage")),

                                        a(now, "GARAGE", "Nhà chứa xe", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 350,
                                                        "lucide:warehouse", List.of("garage")),

                                        // Food
                                        a(now, "BREAKFAST", "Bữa sáng", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.FOOD_AND_DRINK, false, 410,
                                                        "lucide:coffee", List.of("breakfast", "breakfast_paid")),

                                        // Entertainment / public
                                        a(now, "GARDEN", "Vườn hoa", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 510,
                                                        "lucide:flower-2", List.of("garden")),

                                        a(now, "DART", "Phi tiêu", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 520,
                                                        "mdi:target", List.of("dart")),

                                        a(now, "TERRACE", "Sân thượng/sân hiên", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 530,
                                                        "lucide:landmark", List.of("terrace")),

                                        a(now, "FAMILY_ROOM", "Phòng gia đình", AmenityScope.HOTEL, AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 610,
                                                        "lucide:users", List.of("family_room", "family_rooms")),

                                        a(now, "NON_SMOKING_ROOM", "Phòng không hút thuốc", AmenityScope.HOTEL,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 620,
                                                        "mdi:smoking-off",
                                                        List.of("non_smoking_room", "non_smoking_rooms")),

                                        a(now, "SMOKING_AREA", "Khu vực hút thuốc", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 630,
                                                        "mdi:smoking", List.of("smoking_area")),

                                        // Nearby
                                        a(now, "ATM", "Máy ATM/Ngân hàng", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 710,
                                                        "lucide:landmark", List.of("atm")),

                                        a(now, "BEAUTY_SALON", "Thẩm mỹ viện", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER, false, 720,
                                                        "mdi:storefront-outline", List.of("beauty_salon")),

                                        a(now, "HAIR_SALON", "Hiệu làm tóc", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 730,
                                                        "mdi:content-cut", List.of("hair_salon", "salon_nearby")),

                                        a(now, "SHOP", "Cửa hàng", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER, false,
                                                        740,
                                                        "lucide:shopping-bag", List.of("shop", "gift_shop", "grocery")),

                                        a(now, "SUPERMARKET", "Siêu thị", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 750,
                                                        "lucide:shopping-cart",
                                                        List.of("supermarket", "supermarket_nearby"))));

                        // =========================
                        // ROOM SCOPE
                        // =========================
                        seeds.addAll(List.of(
                                        a(now, "AC", "Máy lạnh", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, true,
                                                        10,
                                                        "mdi:air-conditioner", List.of("ac", "air_conditioning")),

                                        a(now, "HOT_WATER", "Nước nóng", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        true, 20,
                                                        "mdi:water-boiler", List.of("hot_water")),

                                        a(now, "SHOWER", "Vòi tắm đứng", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        true, 30,
                                                        "mdi:shower", List.of("shower")),

                                        a(now, "BALCONY", "Ban công / sân hiên", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, true, 40,
                                                        "mdi:balcony", List.of("balcony")),

                                        a(now, "SEATING_AREA", "Khu vực chờ", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 60,
                                                        "lucide:sofa", List.of("seating_area")),

                                        a(now, "NON_SMOKING", "Phòng cấm hút thuốc", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 70,
                                                        "mdi:smoking-off", List.of("non_smoking")),

                                        // Room facilities
                                        a(now, "TV", "TV", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 110,
                                                        "lucide:tv", List.of("tv", "cable_tv", "tv màn hình phẳng")),

                                        a(now, "DESK", "Bàn làm việc", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES,
                                                        false, 120,
                                                        "lucide:monitor", List.of("desk", "work_desk")),

                                        a(now, "BLACKOUT_CURTAINS", "Rèm cản sáng", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 130,
                                                        "mdi:curtains",
                                                        List.of("blackout_curtains", "BLACKOUT_CURTAINS")),

                                        a(now, "BOTTLED_WATER", "Nước đóng chai miễn phí", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 140,
                                                        "mdi:bottle-soda-outline", List.of("bottled_water")),

                                        a(now, "MINIBAR", "Quầy bar mini", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 150,
                                                        "mdi:glass-cocktail", List.of("minibar")),

                                        a(now, "COFFEE_TEA_MAKER", "Máy pha cà phê / trà", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 160,
                                                        "lucide:coffee", List.of("coffee_tea_maker")),

                                        a(now, "FAN", "Quạt", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false,
                                                        170,
                                                        "mdi:fan", List.of("fan")),

                                        a(now, "MICROWAVE", "Lò vi sóng", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 180,
                                                        "mdi:microwave", List.of("microwave")),

                                        a(now, "SAFE", "Két an toàn", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES,
                                                        false, 190,
                                                        "mdi:safe", List.of("safe", "in_room_safe")),

                                        a(now, "SLIPPERS", "Dép đi trong phòng", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 200,
                                                        "mdi:shoe-formal", List.of("slippers")),

                                        // Bathroom
                                        a(now, "HAIR_DRYER", "Máy sấy tóc", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        false, 210,
                                                        "mdi:hair-dryer", List.of("hair_dryer")),

                                        a(now, "BATH_AMENITIES", "Bộ vệ sinh cá nhân", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 220,
                                                        "mdi:toothbrush", List.of("bath_amenities", "toiletries")),

                                        a(now, "BATHROBE", "Áo choàng tắm", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        false, 230,
                                                        "mdi:hanger", List.of("bathrobe")),

                                        a(now, "PRIVATE_BATHROOM", "Phòng tắm riêng", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 240,
                                                        "mdi:shower-head", List.of("private_bathroom")),

                                        a(now, "BATHTUB", "Bồn tắm", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false,
                                                        250,
                                                        "mdi:bathtub", List.of("bathtub")),

                                        a(now, "SHOWER_OR_BATHTUB", "Vòi sen hoặc bồn tắm", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 260,
                                                        "mdi:shower", List.of("shower_or_bathtub"))));

                        // =========================
                        // RESTAURANT SCOPE
                        // =========================
                        seeds.addAll(List.of(
                                        a(now, "WIFI", "WiFi miễn phí", AmenityScope.RESTAURANT, AmenityGroup.INTERNET,
                                                        AmenitySection.INTERNET, true, 10,
                                                        "lucide:wifi",
                                                        List.of("wifi", "wifi_free", "free_wifi", "public_wifi")),

                                        a(now, "AIR_CONDITIONING", "Có máy lạnh", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, true, 20,
                                                        "mdi:air-conditioner",
                                                        List.of("ac", "air_conditioning", "air-conditioner")),

                                        a(now, "OUTDOOR_TABLE", "Có bàn ngoài trời", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 30,
                                                        "mdi:table-chair",
                                                        List.of("outdoor_table", "outdoor_seating", "outdoor")),

                                        a(now, "SMOKING_AREA", "Khu vực hút thuốc", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 40,
                                                        "mdi:smoking", List.of("smoking_area", "smoking")),

                                        a(now, "KIDS_CHAIR", "Ghế trẻ em", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, false, 50,
                                                        "mdi:baby-face-outline",
                                                        List.of("kids_chair", "child_chair", "baby_chair")),

                                        a(now, "FREE_CAR_PARKING", "Đỗ xe ô tô miễn phí", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 60,
                                                        "lucide:parking-square",
                                                        List.of("free_car_parking", "car_parking_free", "car_parking")),

                                        a(now, "FREE_MOTORBIKE_PARKING", "Đỗ xe máy miễn phí", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 70,
                                                        "mdi:motorbike",
                                                        List.of("free_motorbike_parking", "motorbike_parking_free",
                                                                        "motorbike_parking")),

                                        a(now, "VISA_MASTER", "Thanh toán thẻ Visa/Master", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, false, 80,
                                                        "lucide:credit-card",
                                                        List.of("visa_master", "visa", "mastercard", "card_payment")),

                                        a(now, "VAT_INVOICE", "Xuất hoá đơn VAT", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 90,
                                                        "mdi:receipt-text-outline",
                                                        List.of("vat_invoice", "invoice_vat", "vat")),

                                        a(now, "SET_LUNCH", "Có set lunch / set menu", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, false, 100,
                                                        "mdi:silverware-fork-knife", List.of("set_lunch", "set_menu")),

                                        a(now, "EVENT_DECOR", "Hỗ trợ trang trí sự kiện", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 110,
                                                        "mdi:party-popper",
                                                        List.of("event_decor", "decor", "decoration")),

                                        a(now, "MC_SERVICE", "Có MC / hỗ trợ dẫn chương trình", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 120,
                                                        "mdi:microphone-variant", List.of("mc_service", "mc")),

                                        a(now, "SOUND_LIGHT", "Âm thanh / ánh sáng", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 130,
                                                        "mdi:speaker-multiple",
                                                        List.of("sound_light", "sound", "light", "karaoke_sound")),

                                        a(now, "PROJECTOR", "Máy chiếu", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 140,
                                                        "mdi:projector", List.of("projector", "beamer"))));

                        // =========================
                        // Upsert by (code, scope)
                        // =========================
                        int upserted = 0;

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

                c = c.replace('-', '_');
                return c.toUpperCase(Locale.ROOT);
        }
}
