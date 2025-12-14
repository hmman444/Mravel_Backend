package com.mravel.booking.controller;

import com.mravel.booking.dto.BookingPublicDtos.BookingLookupRequest;
import com.mravel.booking.dto.RestaurantBookingDtos.*;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.service.RestaurantBookingMapper;
import com.mravel.booking.service.RestaurantBookingService;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking/restaurants")
@RequiredArgsConstructor
public class RestaurantBookingMyController {

    private final RestaurantBookingRepository repo;
    private final RestaurantBookingService service;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<RestaurantBookingSummaryDTO>>> my(
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = extractUserId(jwt);

        var items = repo.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(RestaurantBookingMapper::toSummary)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("OK", items));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<RestaurantBookingDetailDTO>> detail(
            @PathVariable String code,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = extractUserId(jwt);

        var b = repo.findWithTablesByCode(code.trim())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        if (b.getUserId() == null || !userId.equals(b.getUserId())) {
            throw new IllegalStateException("Bạn không có quyền xem booking này");
        }

        return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toDetailDTO(b)));
    }

    @PostMapping("/claim")
    public ResponseEntity<ApiResponse<Integer>> claim(
            @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = extractUserId(jwt);

        int claimed = service.claimGuestRestaurantBookingsToUser(sid, userId);
        return ResponseEntity.ok(ApiResponse.success("OK", claimed));
    }

    private static Long extractUserId(Jwt jwt) {
        Object rawId = jwt.getClaim("id");
        return (rawId instanceof Number)
                ? ((Number) rawId).longValue()
                : Long.valueOf(String.valueOf(rawId));
    }

    @PostMapping("/lookup")
  public ResponseEntity<ApiResponse<RestaurantBookingSummaryDTO>> lookup(
      @RequestBody BookingLookupRequest body,
      @AuthenticationPrincipal Jwt jwt
  ) {
    Long userId = ((Number) jwt.getClaim("id")).longValue();

    var b = repo.findByCode(body.bookingCode().trim())
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

    // booking phải thuộc user đang login
    if (b.getUserId() == null || !userId.equals(b.getUserId())) {
      throw new IllegalStateException("Booking này không thuộc tài khoản của bạn");
    }

    // vẫn check last4/email nếu bạn muốn
    // ...

    return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toSummary(b)));
  }
}