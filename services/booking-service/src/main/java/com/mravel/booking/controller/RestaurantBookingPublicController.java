// src/main/java/com/mravel/booking/controller/RestaurantBookingPublicController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.BookingPublicDtos.BookingLookupRequest;
import com.mravel.booking.dto.RestaurantBookingDtos.*; // SummaryDTO, DetailDTO...
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.payment.MomoGatewayClient;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.service.RestaurantBookingMapper;
import com.mravel.booking.service.RestaurantBookingService;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/booking/public/restaurants")
@RequiredArgsConstructor
public class RestaurantBookingPublicController {

  private final RestaurantBookingRepository repo;
  private final RestaurantBookingService service;
  private final MomoGatewayClient momoGateway;

  @GetMapping("/my")
  public ResponseEntity<ApiResponse<List<RestaurantBookingSummaryDTO>>> my(
      @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
  ) {
    if (sid == null || sid.isBlank()) {
      return ResponseEntity.ok(ApiResponse.success("OK", List.of()));
    }

    var items = repo.findByGuestSessionIdOrderByCreatedAtDesc(sid)
        .stream()
        .map(RestaurantBookingMapper::toSummary)
        .toList();

    return ResponseEntity.ok(ApiResponse.success("OK", items));
  }

  @GetMapping("/my/{code}")
    public ResponseEntity<ApiResponse<RestaurantBookingDetailDTO>> myDetail(
        @PathVariable String code,
        @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
    ) {
    if (sid == null || sid.isBlank()) throw new IllegalStateException("Thiếu guest session");

    var b = repo.findWithTablesByCode(code.trim())
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

    if (b.getUserId() != null) throw new IllegalStateException("Booking này thuộc tài khoản");
    if (b.getGuestSessionId() == null || !sid.equals(b.getGuestSessionId()))
        throw new IllegalStateException("Không có quyền xem booking này");

    return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toDetailDTO(b)));
    }

  @PostMapping("/lookup")
    public ResponseEntity<ApiResponse<RestaurantBookingSummaryDTO>> lookup(
        @RequestBody com.mravel.booking.dto.BookingPublicDtos.BookingLookupRequest body
    ) {
    if (body == null || body.bookingCode() == null || body.bookingCode().isBlank()) {
        throw new IllegalArgumentException("Thiếu bookingCode");
    }
    if (body.phoneLast4() == null || body.phoneLast4().isBlank() || body.phoneLast4().trim().length() != 4) {
        throw new IllegalArgumentException("Thiếu phoneLast4 (4 số cuối)");
    }

    var b = repo.findByCode(body.bookingCode().trim())
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

    String last4 = last4Digits(b.getContactPhone());
    if (!body.phoneLast4().trim().equals(last4)) {
        throw new IllegalStateException("Sai 4 số cuối SĐT");
    }

    if (body.email() != null && !body.email().isBlank()) {
        String bEmail = b.getContactEmail();
        if (bEmail == null || !bEmail.equalsIgnoreCase(body.email().trim())) {
        throw new IllegalStateException("Email không khớp");
        }
    }

    return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toSummary(b)));
    }

    private static String last4Digits(String phone) {
    if (phone == null) return "";
    String digits = phone.replaceAll("\\D", "");
    if (digits.length() <= 4) return digits;
    return digits.substring(digits.length() - 4);
    }

    @PostMapping("/lookup/detail")
    public ResponseEntity<ApiResponse<RestaurantBookingDetailDTO>> lookupDetail(
        @RequestBody com.mravel.booking.dto.BookingPublicDtos.BookingLookupRequest body
    ) {
        if (body == null || body.bookingCode() == null || body.bookingCode().isBlank()) {
            throw new IllegalArgumentException("Thiếu bookingCode");
        }
        if (body.phoneLast4() == null || body.phoneLast4().trim().length() != 4) {
            throw new IllegalArgumentException("Thiếu phoneLast4 (4 số cuối)");
        }

        var b = repo.findWithTablesByCode(body.bookingCode().trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        String last4 = last4Digits(b.getContactPhone());
        if (!body.phoneLast4().trim().equals(last4)) {
            throw new IllegalStateException("Sai 4 số cuối SĐT");
        }

        if (body.email() != null && !body.email().isBlank()) {
            String bEmail = b.getContactEmail();
            if (bEmail == null || !bEmail.equalsIgnoreCase(body.email().trim())) {
            throw new IllegalStateException("Email không khớp");
            }
        }

        return ResponseEntity.ok(ApiResponse.success("OK", RestaurantBookingMapper.toDetailDTO(b)));
    }

    
    @PostMapping("/my/{code}/resume-payment")
    public ResponseEntity<ApiResponse<ResumePaymentDTO>> resumeMy(
        @PathVariable String code,
        @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
    ) {
        var dto = service.resumeRestaurantPaymentForOwner(code, null, sid);
        return ResponseEntity.ok(ApiResponse.success("OK", dto));
    }

    @PostMapping("/lookup/resume")
    public ResponseEntity<ApiResponse<ResumePaymentDTO>> lookupResume(
        @RequestBody BookingLookupRequest body
    ) {
        if (body == null || body.bookingCode() == null || body.bookingCode().isBlank()) {
            throw new IllegalArgumentException("Thiếu bookingCode");
        }
        if (body.phoneLast4() == null || body.phoneLast4().trim().length() != 4) {
            throw new IllegalArgumentException("Thiếu phoneLast4 (4 số cuối)");
        }

        var b = repo.findByCode(body.bookingCode().trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // ✅ Option A: lookup resume chỉ cho GUEST
        if (b.getUserId() != null) {
            throw new IllegalStateException("Booking này thuộc tài khoản");
        }

        String last4 = last4Digits(b.getContactPhone());
        if (!body.phoneLast4().trim().equals(last4)) {
            throw new IllegalStateException("Sai 4 số cuối SĐT");
        }

        if (body.email() != null && !body.email().isBlank()) {
            String bEmail = b.getContactEmail();
            if (bEmail == null || !bEmail.equalsIgnoreCase(body.email().trim())) {
                throw new IllegalStateException("Email không khớp");
            }
        }

        // ✅ pending
        if (b.getStatus() != BookingBase.BookingStatus.PENDING_PAYMENT
            || b.getPaymentStatus() != BookingBase.PaymentStatus.PENDING) {
            throw new IllegalStateException("Đơn này không ở trạng thái chờ thanh toán");
        }

        // ✅ trong 30 phút
        if (b.getCreatedAt() == null) throw new IllegalStateException("Booking thiếu createdAt");
        Instant deadline = b.getCreatedAt().plus(30, ChronoUnit.MINUTES);
        Instant now = Instant.now();
        if (now.isAfter(deadline)) throw new IllegalStateException("Đơn đã quá hạn thanh toán");
        long expiresIn = Duration.between(now, deadline).getSeconds();

        String orderInfo = "Dat ban " + (b.getRestaurantName() != null ? b.getRestaurantName() : b.getCode());
        String attemptId = b.getCode() + "-" + java.util.UUID.randomUUID().toString()
            .replace("-", "").substring(0, 6).toUpperCase();

        String payUrl = momoGateway.createPayment(attemptId, b.getAmountPayable(), orderInfo);

        b.setPendingPaymentUrl(payUrl);
        b.setPendingPaymentOrderId(attemptId);
        repo.save(b);

        return ResponseEntity.ok(ApiResponse.success("OK", new ResumePaymentDTO(b.getCode(), payUrl, expiresIn)));
    }
}