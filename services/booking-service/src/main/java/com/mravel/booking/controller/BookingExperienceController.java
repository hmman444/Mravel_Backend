package com.mravel.booking.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.booking.model.BookingBase.BookingStatus;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

/**
 * Endpoint nội bộ cho review-service: kiểm tra user (từ token) đã TRẢI NGHIỆM
 * dịch vụ chưa thông qua BOOKING (đặt phòng/đặt bàn đã qua mốc sử dụng).
 *
 * Rule:
 *  - HOTEL: có booking của user, hotelId == targetId, status ∈ {PAID,CONFIRMED,COMPLETED},
 *           checkInDate < hôm nay (đã qua ít nhất đêm đầu).
 *  - RESTAURANT: tương tự, restaurantId == targetId, reservationDate < hôm nay.
 *  - PLACE: không có booking → false (review place chỉ qua plan).
 * Booking là giao dịch thật (ngày do hệ thống set) nên không lo backdate.
 */
@RestController
@RequestMapping("/api/booking/internal")
@RequiredArgsConstructor
public class BookingExperienceController {

    private static final Set<BookingStatus> USED_STATUSES = Set.of(
            BookingStatus.PAID, BookingStatus.CONFIRMED, BookingStatus.COMPLETED);

    private final HotelBookingRepository hotelRepo;
    private final RestaurantBookingRepository restaurantRepo;

    @GetMapping("/experienced")
    public ApiResponse<Map<String, Object>> experienced(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam String targetType,
            @RequestParam String targetId) {

        Long userId = userId(jwt);
        boolean eligible = userId != null && targetId != null
                && switch (targetType == null ? "" : targetType.toUpperCase()) {
                    case "HOTEL" -> hasUsedHotel(userId, targetId);
                    case "RESTAURANT" -> hasUsedRestaurant(userId, targetId);
                    default -> false;
                };

        Map<String, Object> data = new HashMap<>();
        data.put("eligible", eligible);
        return ApiResponse.success("OK", data);
    }

    private boolean hasUsedHotel(Long userId, String hotelId) {
        LocalDate today = LocalDate.now();
        return hotelRepo.findByUserIdOrderByCreatedAtDesc(userId).stream().anyMatch(b ->
                hotelId.equals(b.getHotelId())
                        && USED_STATUSES.contains(b.getStatus())
                        && b.getCheckInDate() != null
                        && b.getCheckInDate().isBefore(today)); // đã qua đêm đầu
    }

    private boolean hasUsedRestaurant(Long userId, String restaurantId) {
        LocalDate today = LocalDate.now();
        return restaurantRepo.findByUserIdOrderByCreatedAtDesc(userId).stream().anyMatch(b ->
                restaurantId.equals(b.getRestaurantId())
                        && USED_STATUSES.contains(b.getStatus())
                        && b.getReservationDate() != null
                        && b.getReservationDate().isBefore(today)); // hết ngày đặt bàn
    }

    private static Long userId(Jwt jwt) {
        if (jwt == null) return null;
        Object raw = jwt.getClaim("id");
        if (raw instanceof Number n) return n.longValue();
        try {
            return raw == null ? null : Long.valueOf(String.valueOf(raw));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
