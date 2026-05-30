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
import com.mravel.common.i18n.LocaleConstants;

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
                                        a(now, "WIFI_FREE", "WiFi miễn phí", "Free WiFi", AmenityScope.HOTEL, AmenityGroup.INTERNET,
                                                        AmenitySection.INTERNET, true, 10,
                                                        "lucide:wifi",
                                                        List.of("wifi_free", "free_wifi", "public_wifi",
                                                                        "wifi (miễn phí)", "wifi")),

                                        a(now, "PARKING", "Chỗ đậu xe", "Parking", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, true, 20,
                                                        "lucide:parking-square",
                                                        List.of("parking", "public_parking", "secure_parking",
                                                                        "secured_parking", "bãi đậu xe")),

                                        a(now, "RESTAURANT", "Nhà hàng", "Restaurant", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, true, 30,
                                                        "lucide:utensils-crossed",
                                                        List.of("restaurant", "public_restaurant")),

                                        a(now, "FRONT_DESK_24H", "Lễ tân 24h", "24-hour front desk", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, true, 40,
                                                        "lucide:bell",
                                                        List.of("front_desk_24h", "24h_front_desk", "reception_24h",
                                                                        "lễ tân 24 giờ")),

                                        // Services
                                        a(now, "WELCOME_DRINK", "Thức uống chào mừng miễn phí", "Free welcome drink", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 110,
                                                        "lucide:glass-water", List.of("welcome_drink")),

                                        a(now, "CONCIERGE", "Dịch vụ concierge/hỗ trợ khách", "Concierge service", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 120,
                                                        "lucide:concierge-bell", List.of("concierge")),

                                        a(now, "CURRENCY_EXCHANGE", "Dịch vụ thu đổi ngoại tệ", "Currency exchange", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 130,
                                                        "lucide:coins", List.of("currency_exchange")),

                                        a(now, "DOORMAN", "Người gác cửa", "Doorman", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 140,
                                                        "lucide:user-round", List.of("doorman")),

                                        a(now, "EARLY_CHECKIN", "Nhận phòng sớm", "Early check-in", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 150,
                                                        "lucide:log-in", List.of("early_checkin")),

                                        a(now, "EXPRESS_CHECKIN", "Dịch vụ nhận phòng cấp tốc", "Express check-in", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 160,
                                                        "lucide:zap", List.of("express_checkin")),

                                        a(now, "EXPRESS_CHECKOUT", "Dịch vụ trả phòng cấp tốc", "Express check-out", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 170,
                                                        "lucide:log-out", List.of("express_checkout")),

                                        a(now, "LATE_CHECKOUT", "Trả phòng trễ", "Late check-out", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 180,
                                                        "lucide:clock", List.of("late_checkout")),

                                        a(now, "LAUNDRY", "Dịch vụ giặt ủi", "Laundry service", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 190,
                                                        "mdi:washing-machine", List.of("laundry", "nearby_laundry")),

                                        a(now, "ROOM_SERVICE_LIMITED", "Dịch vụ phòng (có giới hạn thời gian)", "Room service (limited hours)",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK,
                                                        false, 200,
                                                        "mdi:room-service",
                                                        List.of("room_service_limited", "room_service")),

                                        a(now, "LUGGAGE_STORAGE", "Dịch vụ lưu trữ/bảo quản hành lý", "Luggage storage",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 210,
                                                        "lucide:luggage", List.of("luggage_storage")),

                                        a(now, "MULTILINGUAL_STAFF", "Đội ngũ nhân viên đa ngôn ngữ", "Multilingual staff",
                                                        AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false, 220,
                                                        "lucide:languages", List.of("multilingual_staff")),

                                        a(now, "TOUR_DESK", "Dịch vụ hỗ trợ đặt tour", "Tour desk", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 230,
                                                        "lucide:map",
                                                        List.of("tour_desk", "tour_assistance", "tour_assist",
                                                                        "tour_support")),

                                        a(now, "SECURITY_24H", "Bảo vệ 24 giờ", "24-hour security", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.BASIC_FACILITIES, false, 240,
                                                        "lucide:shield-check", List.of("security_24h")),

                                        // Transport
                                        a(now, "AIRPORT_TRANSFER", "Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false,
                                                        310,
                                                        "lucide:plane", List.of("airport_transfer", "airport_shuttle")),

                                        a(now, "TRAIN_STATION_PICKUP", "Đón khách tại ga tàu", "Train station pickup", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false,
                                                        320,
                                                        "lucide:train-front", List.of("train_station_pickup")),

                                        a(now, "BICYCLE_RENTAL", "Dịch vụ cho thuê xe đạp", "Bicycle rental", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.TRANSPORT, false, 330,
                                                        "lucide:bike", List.of("bicycle_rental")),

                                        a(now, "BICYCLE_STORAGE", "Giữ xe đạp", "Bicycle storage", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 340,
                                                        "lucide:bike", List.of("bicycle_storage")),

                                        a(now, "GARAGE", "Nhà chứa xe", "Garage", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 350,
                                                        "lucide:warehouse", List.of("garage")),

                                        // Food
                                        a(now, "BREAKFAST", "Bữa sáng", "Breakfast", AmenityScope.HOTEL, AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.FOOD_AND_DRINK, false, 410,
                                                        "lucide:coffee", List.of("breakfast", "breakfast_paid")),

                                        // Entertainment / public
                                        a(now, "GARDEN", "Vườn hoa", "Garden", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 510,
                                                        "lucide:flower-2", List.of("garden")),

                                        a(now, "DART", "Phi tiêu", "Darts", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 520,
                                                        "mdi:target", List.of("dart")),

                                        a(now, "TERRACE", "Sân thượng/sân hiên", "Terrace", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 530,
                                                        "lucide:landmark", List.of("terrace")),

                                        a(now, "FAMILY_ROOM", "Phòng gia đình", "Family room", AmenityScope.HOTEL, AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 610,
                                                        "lucide:users", List.of("family_room", "family_rooms")),

                                        a(now, "NON_SMOKING_ROOM", "Phòng không hút thuốc", "Non-smoking room", AmenityScope.HOTEL,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 620,
                                                        "mdi:smoking-off",
                                                        List.of("non_smoking_room", "non_smoking_rooms")),

                                        a(now, "SMOKING_AREA", "Khu vực hút thuốc", "Smoking area", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 630,
                                                        "mdi:smoking", List.of("smoking_area")),

                                        // Nearby
                                        a(now, "ATM", "Máy ATM/Ngân hàng", "ATM/Bank", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 710,
                                                        "lucide:landmark", List.of("atm")),

                                        a(now, "BEAUTY_SALON", "Thẩm mỹ viện", "Beauty salon", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER, false, 720,
                                                        "mdi:storefront-outline", List.of("beauty_salon")),

                                        a(now, "HAIR_SALON", "Hiệu làm tóc", "Hair salon", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 730,
                                                        "mdi:content-cut", List.of("hair_salon", "salon_nearby")),

                                        a(now, "SHOP", "Cửa hàng", "Shop", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER, false,
                                                        740,
                                                        "lucide:shopping-bag", List.of("shop", "gift_shop", "grocery")),

                                        a(now, "SUPERMARKET", "Siêu thị", "Supermarket", AmenityScope.HOTEL, AmenityGroup.NEARBY,
                                                        AmenitySection.OTHER,
                                                        false, 750,
                                                        "lucide:shopping-cart",
                                                        List.of("supermarket", "supermarket_nearby")),

                                        // Highlight / Entertainment / Food & Drink — added to cover legacy hotel seed
                                        a(now, "OUTDOOR_POOL", "Hồ bơi ngoài trời", "Outdoor pool", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.HIGHLIGHT_FEATURES, true, 760,
                                                        "lucide:waves",
                                                        List.of("outdoor_pool", "swimming_pool", "pool")),

                                        a(now, "BAR", "Quầy bar", "Bar", AmenityScope.HOTEL, AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, false, 770,
                                                        "lucide:wine",
                                                        List.of("bar")),

                                        a(now, "COFFEE_SHOP", "Tiệm cà phê", "Coffee shop", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, false, 780,
                                                        "lucide:coffee",
                                                        List.of("coffee_shop", "lobby_coffee_tea")),

                                        a(now, "SPA", "Dịch vụ spa", "Spa service", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.ENTERTAINMENT, false, 790,
                                                        "lucide:flower",
                                                        List.of("spa")),

                                        a(now, "MASSAGE", "Mát-xa", "Massage", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.ENTERTAINMENT, false, 800,
                                                        "lucide:hand",
                                                        List.of("massage")),

                                        a(now, "KIDS_PLAYGROUND", "Khu vui chơi trẻ em", "Kids playground", AmenityScope.HOTEL,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 810,
                                                        "lucide:baby",
                                                        List.of("kids_playground", "playground")),

                                        a(now, "BABYSITTING", "Dịch vụ giữ trẻ", "Babysitting service", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.OTHER, false, 820,
                                                        "lucide:baby",
                                                        List.of("babysitting")),

                                        a(now, "CAR_RENTAL", "Cho thuê xe hơi", "Car rental", AmenityScope.HOTEL,
                                                        AmenityGroup.HOTEL_SERVICE,
                                                        AmenitySection.TRANSPORT, false, 830,
                                                        "lucide:car",
                                                        List.of("car_rental"))));

                        // =========================
                        // ROOM SCOPE
                        // =========================
                        seeds.addAll(List.of(
                                        a(now, "AC", "Máy lạnh", "Air conditioning", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, true,
                                                        10,
                                                        "mdi:air-conditioner", List.of("ac", "air_conditioning")),

                                        a(now, "HOT_WATER", "Nước nóng", "Hot water", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        true, 20,
                                                        "mdi:water-boiler", List.of("hot_water")),

                                        a(now, "SHOWER", "Vòi tắm đứng", "Shower", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        true, 30,
                                                        "mdi:shower", List.of("shower")),

                                        a(now, "BALCONY", "Ban công / sân hiên", "Balcony", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, true, 40,
                                                        "mdi:balcony", List.of("balcony")),

                                        a(now, "SEATING_AREA", "Khu vực chờ", "Seating area", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 60,
                                                        "lucide:sofa", List.of("seating_area")),

                                        a(now, "NON_SMOKING", "Phòng cấm hút thuốc", "Non-smoking", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BASIC_FACILITIES, false, 70,
                                                        "mdi:smoking-off", List.of("non_smoking")),

                                        // Room facilities
                                        a(now, "TV", "TV", "TV", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 110,
                                                        "lucide:tv", List.of("tv", "cable_tv", "tv màn hình phẳng")),

                                        a(now, "DESK", "Bàn làm việc", "Work desk", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES,
                                                        false, 120,
                                                        "lucide:monitor", List.of("desk", "work_desk")),

                                        a(now, "BLACKOUT_CURTAINS", "Rèm cản sáng", "Blackout curtains", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 130,
                                                        "mdi:curtains",
                                                        List.of("blackout_curtains", "BLACKOUT_CURTAINS")),

                                        a(now, "BOTTLED_WATER", "Nước đóng chai miễn phí", "Free bottled water", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 140,
                                                        "mdi:bottle-soda-outline", List.of("bottled_water")),

                                        a(now, "MINIBAR", "Quầy bar mini", "Minibar", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 150,
                                                        "mdi:glass-cocktail", List.of("minibar")),

                                        a(now, "COFFEE_TEA_MAKER", "Máy pha cà phê / trà", "Coffee/tea maker", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 160,
                                                        "lucide:coffee", List.of("coffee_tea_maker")),

                                        a(now, "FAN", "Quạt", "Fan", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false,
                                                        170,
                                                        "mdi:fan", List.of("fan")),

                                        a(now, "MICROWAVE", "Lò vi sóng", "Microwave", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 180,
                                                        "mdi:microwave", List.of("microwave")),

                                        a(now, "SAFE", "Két an toàn", "Safe", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES,
                                                        false, 190,
                                                        "mdi:safe", List.of("safe", "in_room_safe")),

                                        a(now, "SLIPPERS", "Dép đi trong phòng", "Slippers", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.ROOM_FACILITIES, false, 200,
                                                        "mdi:shoe-formal", List.of("slippers")),

                                        // Bathroom
                                        a(now, "HAIR_DRYER", "Máy sấy tóc", "Hair dryer", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        false, 210,
                                                        "mdi:hair-dryer", List.of("hair_dryer")),

                                        a(now, "BATH_AMENITIES", "Bộ vệ sinh cá nhân", "Toiletries", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 220,
                                                        "mdi:toothbrush", List.of("bath_amenities", "toiletries")),

                                        a(now, "BATHROBE", "Áo choàng tắm", "Bathrobe", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM,
                                                        false, 230,
                                                        "mdi:hanger", List.of("bathrobe")),

                                        a(now, "PRIVATE_BATHROOM", "Phòng tắm riêng", "Private bathroom", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 240,
                                                        "mdi:shower-head", List.of("private_bathroom")),

                                        a(now, "BATHTUB", "Bồn tắm", "Bathtub", AmenityScope.ROOM, AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false,
                                                        250,
                                                        "mdi:bathtub", List.of("bathtub")),

                                        a(now, "SHOWER_OR_BATHTUB", "Vòi sen hoặc bồn tắm", "Shower or bathtub", AmenityScope.ROOM,
                                                        AmenityGroup.ROOM,
                                                        AmenitySection.BATHROOM, false, 260,
                                                        "mdi:shower", List.of("shower_or_bathtub"))));

                        // =========================
                        // RESTAURANT SCOPE
                        // =========================
                        seeds.addAll(List.of(
                                        a(now, "WIFI", "WiFi miễn phí", "Free WiFi", AmenityScope.RESTAURANT, AmenityGroup.INTERNET,
                                                        AmenitySection.INTERNET, true, 10,
                                                        "lucide:wifi",
                                                        List.of("wifi", "wifi_free", "free_wifi", "public_wifi")),

                                        a(now, "AIR_CONDITIONING", "Có máy lạnh", "Air conditioning", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, true, 20,
                                                        "mdi:air-conditioner",
                                                        List.of("ac", "air_conditioning", "air-conditioner")),

                                        a(now, "OUTDOOR_TABLE", "Có bàn ngoài trời", "Outdoor seating", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 30,
                                                        "mdi:table-chair",
                                                        List.of("outdoor_table", "outdoor_seating", "outdoor")),

                                        a(now, "SMOKING_AREA", "Khu vực hút thuốc", "Smoking area", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 40,
                                                        "mdi:smoking", List.of("smoking_area", "smoking")),

                                        a(now, "KIDS_CHAIR", "Ghế trẻ em", "High chair", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, false, 50,
                                                        "mdi:baby-face-outline",
                                                        List.of("kids_chair", "child_chair", "baby_chair")),

                                        a(now, "FREE_CAR_PARKING", "Đỗ xe ô tô miễn phí", "Free car parking", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 60,
                                                        "lucide:parking-square",
                                                        List.of("free_car_parking", "car_parking_free", "car_parking")),

                                        a(now, "FREE_MOTORBIKE_PARKING", "Đỗ xe máy miễn phí", "Free motorbike parking", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.TRANSPORT, false, 70,
                                                        "mdi:motorbike",
                                                        List.of("free_motorbike_parking", "motorbike_parking_free",
                                                                        "motorbike_parking")),

                                        a(now, "VISA_MASTER", "Thanh toán thẻ Visa/Master", "Visa/Mastercard accepted", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.BASIC_FACILITIES, false, 80,
                                                        "lucide:credit-card",
                                                        List.of("visa_master", "visa", "mastercard", "card_payment")),

                                        a(now, "VAT_INVOICE", "Xuất hoá đơn VAT", "VAT invoice", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.OTHER, false, 90,
                                                        "mdi:receipt-text-outline",
                                                        List.of("vat_invoice", "invoice_vat", "vat")),

                                        a(now, "SET_LUNCH", "Có set lunch / set menu", "Set lunch / set menu", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.FOOD_AND_DRINK, false, 100,
                                                        "mdi:silverware-fork-knife", List.of("set_lunch", "set_menu")),

                                        a(now, "EVENT_DECOR", "Hỗ trợ trang trí sự kiện", "Event decoration", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 110,
                                                        "mdi:party-popper",
                                                        List.of("event_decor", "decor", "decoration")),

                                        a(now, "MC_SERVICE", "Có MC / hỗ trợ dẫn chương trình", "MC service", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 120,
                                                        "mdi:microphone-variant", List.of("mc_service", "mc")),

                                        a(now, "SOUND_LIGHT", "Âm thanh / ánh sáng", "Sound & lighting", AmenityScope.RESTAURANT,
                                                        AmenityGroup.PUBLIC_AREA,
                                                        AmenitySection.ENTERTAINMENT, false, 130,
                                                        "mdi:speaker-multiple",
                                                        List.of("sound_light", "sound", "light", "karaoke_sound")),

                                        a(now, "PROJECTOR", "Máy chiếu", "Projector", AmenityScope.RESTAURANT,
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
                        String enName,
                        AmenityScope scope,
                        AmenityGroup group,
                        AmenitySection section,
                        boolean highlight,
                        Integer sortOrder,
                        String icon,
                        List<String> aliases) {
                return AmenityCatalogDoc.builder()
                                .code(code)
                                .name(buildName(name, enName))
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

        private static Map<String, String> buildName(String viName, String enName) {
                if (viName == null && enName == null)
                        return null;
                Map<String, String> name = new LinkedHashMap<>();
                if (viName != null)
                        name.put(LocaleConstants.VI, viName);
                if (enName != null)
                        name.put(LocaleConstants.EN, enName);
                return name;
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
