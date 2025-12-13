// src/main/java/com/mravel/booking/controller/BookingMyController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.BookingPublicDtos.HotelBookingSummaryDTO;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingDetailDTO;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.service.HotelBookingMapper;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.service.HotelBookingSummaryMapper;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingMyController {

  private final HotelBookingRepository repo;
  private final HotelBookingService service;

  @GetMapping("/my")
  public ResponseEntity<ApiResponse<List<HotelBookingSummaryDTO>>> my(
      @AuthenticationPrincipal Jwt jwt
  ) {
    Object rawId = jwt.getClaim("id");
    Long userId = (rawId instanceof Number) ? ((Number) rawId).longValue() : Long.valueOf(String.valueOf(rawId));

    var items = repo.findByUserIdOrderByCreatedAtDesc(userId)
        .stream()
        .map(HotelBookingSummaryMapper::toSummary)
        .toList();

    return ResponseEntity.ok(ApiResponse.success("OK", items));
  }

  @GetMapping("/bookings/{code}")
  public ResponseEntity<ApiResponse<HotelBookingDetailDTO>> detail(
      @PathVariable String code,
      @AuthenticationPrincipal Jwt jwt
  ) {
    Object rawId = jwt.getClaim("id");
    Long userId = (rawId instanceof Number) ? ((Number) rawId).longValue() : Long.valueOf(String.valueOf(rawId));

    var b = repo.findWithRoomsByCode(code.trim())
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

    if (b.getUserId() == null || !userId.equals(b.getUserId())) {
      throw new IllegalStateException("Bạn không có quyền xem booking này");
    }

    return ResponseEntity.ok(ApiResponse.success("OK", HotelBookingMapper.toDetailDTO(b)));
  }

  @PostMapping("/bookings/claim")
  public ResponseEntity<ApiResponse<Integer>> claim(
      @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid,
      @AuthenticationPrincipal Jwt jwt
  ) {
    Object rawId = jwt.getClaim("id");
    Long userId = (rawId instanceof Number) ? ((Number) rawId).longValue() : Long.valueOf(String.valueOf(rawId));

    int claimed = service.claimGuestBookingsToUser(sid, userId);
    return ResponseEntity.ok(ApiResponse.success("OK", claimed));
  }
}