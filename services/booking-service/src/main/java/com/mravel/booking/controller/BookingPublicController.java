// src/main/java/com/mravel/booking/controller/BookingPublicController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.BookingPublicDtos.BookingLookupRequest;
import com.mravel.booking.dto.BookingPublicDtos.HotelBookingSummaryDTO;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingDetailDTO;
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.BookingBase.BookingStatus;
import com.mravel.booking.model.BookingBase.PaymentStatus;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.payment.MomoGatewayClient;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.service.HotelBookingMapper;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.service.HotelBookingSummaryMapper;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mravel.booking.service.HotelBookingSummaryMapper.toSummary;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import java.time.Duration;

@RestController
@RequestMapping("/api/booking/public")
@RequiredArgsConstructor
public class BookingPublicController {

    private final HotelBookingRepository hotelBookingRepository;
    private final HotelBookingService hotelBookingService;
    private final MomoGatewayClient momoPaymentService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<HotelBookingSummaryDTO>>> myBookings(
            @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
    ) {
        if (sid == null || sid.isBlank()) {
            return ResponseEntity.ok(ApiResponse.success("OK", List.of()));
        }

        List<HotelBookingSummaryDTO> items = hotelBookingRepository
                .findByGuestSessionIdOrderByCreatedAtDesc(sid)
                .stream()
                .map(HotelBookingSummaryMapper::toSummary)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("OK", items));
    }

    @GetMapping("/my/{code}")
    public ResponseEntity<ApiResponse<HotelBookingDetailDTO>> myDetail(
            @PathVariable String code,
            @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
    ) {
        if (sid == null || sid.isBlank()) {
            throw new IllegalStateException("Thiếu guest session");
        }

        HotelBooking b = hotelBookingRepository.findWithRoomsByCode(code.trim())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // chỉ guest mới được xem ở public
        if (b.getUserId() != null) {
            throw new IllegalStateException("Booking này thuộc tài khoản");
        }
        if (b.getGuestSessionId() == null || !sid.equals(b.getGuestSessionId())) {
            throw new IllegalStateException("Không có quyền xem booking này");
        }

        return ResponseEntity.ok(ApiResponse.success("OK", HotelBookingMapper.toDetailDTO(b)));
    }

    @PostMapping("/my/{code}/resume-payment")
    public ResponseEntity<ApiResponse<ResumePaymentDTO>> resumeMy(
        @PathVariable String code,
        @CookieValue(name = GuestSessionCookie.COOKIE_NAME, required = false) String sid
    ) {
        var dto = hotelBookingService.resumeHotelPaymentForOwner(code, null, sid);
        return ResponseEntity.ok(ApiResponse.success("OK", dto));
    }

    @PostMapping("/lookup")
    public ResponseEntity<ApiResponse<HotelBookingSummaryDTO>> lookup(@RequestBody BookingLookupRequest body) {
        if (body == null || body.bookingCode() == null || body.bookingCode().isBlank()) {
            throw new IllegalArgumentException("Thiếu bookingCode");
        }
        if (body.phoneLast4() == null || body.phoneLast4().isBlank() || body.phoneLast4().trim().length() != 4) {
            throw new IllegalArgumentException("Thiếu phoneLast4 (4 số cuối)");
        }

        HotelBooking booking = hotelBookingRepository.findByCode(body.bookingCode().trim())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        String last4 = last4Digits(booking.getContactPhone());
        if (!body.phoneLast4().trim().equals(last4)) {
            throw new IllegalStateException("Sai 4 số cuối SĐT");
        }

        if (body.email() != null && !body.email().isBlank()) {
            String bEmail = booking.getContactEmail();
            if (bEmail == null || !bEmail.equalsIgnoreCase(body.email().trim())) {
                throw new IllegalStateException("Email không khớp");
            }
        }

        return ResponseEntity.ok(ApiResponse.success("OK", toSummary(booking)));
    }

    @PostMapping("/clear")
    public ResponseEntity<ApiResponse<Void>> clearDevice(HttpServletResponse resp) {
        GuestSessionCookie.clear(resp);
        return ResponseEntity.ok(ApiResponse.success("Đã xoá dữ liệu đơn trên thiết bị này", null));
    }

    private static String last4Digits(String phone) {
        if (phone == null) return "";
        String digits = phone.replaceAll("\\D", "");
        if (digits.length() <= 4) return digits;
        return digits.substring(digits.length() - 4);
    }

    @PostMapping("/lookup/detail")
    public ResponseEntity<ApiResponse<HotelBookingDetailDTO>> lookupDetail(
        @RequestBody BookingLookupRequest body
    ) {
        if (body == null || body.bookingCode() == null || body.bookingCode().isBlank()) {
            throw new IllegalArgumentException("Thiếu bookingCode");
        }
        if (body.phoneLast4() == null || body.phoneLast4().trim().length() != 4) {
            throw new IllegalArgumentException("Thiếu phoneLast4 (4 số cuối)");
        }

        HotelBooking b = hotelBookingRepository.findWithRoomsByCode(body.bookingCode().trim())
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

        return ResponseEntity.ok(ApiResponse.success("OK", HotelBookingMapper.toDetailDTO(b)));
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

        HotelBooking b = hotelBookingRepository.findByCode(body.bookingCode().trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // ✅ Nếu bạn muốn LOOKUP RESUME chỉ dành cho GUEST thì đặt đúng ở đây:
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

        // ✅ chỉ cho resume khi còn pending
        if (b.getStatus() != BookingStatus.PENDING_PAYMENT || b.getPaymentStatus() != PaymentStatus.PENDING) {
            throw new IllegalStateException("Đơn này không ở trạng thái chờ thanh toán");
        }

        // ✅ trong 30 phút
        Instant deadline = b.getCreatedAt().plus(30, ChronoUnit.MINUTES);
        Instant now = Instant.now();
        if (now.isAfter(deadline)) {
            throw new IllegalStateException("Đơn đã quá hạn thanh toán");
        }
        long expiresIn = Duration.between(now, deadline).getSeconds();

        // ✅ tạo payUrl mới (requestId sẽ unique)
        String orderInfo = "Thanh toan dat phong " + (b.getHotelName() != null ? b.getHotelName() : b.getCode());
        String attemptId = b.getCode() + "-" + java.util.UUID.randomUUID().toString()
            .replace("-", "").substring(0, 6).toUpperCase();

        String payUrl = momoPaymentService.createPayment(attemptId, b.getAmountPayable(), orderInfo);

        b.setPendingPaymentUrl(payUrl);
        b.setPendingPaymentOrderId(attemptId);
        hotelBookingRepository.save(b);

        return ResponseEntity.ok(ApiResponse.success("OK", new ResumePaymentDTO(b.getCode(), payUrl, expiresIn)));

    }
}