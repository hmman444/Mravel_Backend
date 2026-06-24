package com.mravel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.service.PaymentAttemptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingPublicController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingPublicControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean HotelBookingRepository hotelBookingRepository;
    @MockBean HotelBookingService hotelBookingService;
    @MockBean PaymentAttemptService paymentAttemptService;

    private HotelBooking buildGuest(String code, String guestSid) {
        return HotelBooking.builder()
                .code(code)
                .userId(null)
                .guestSessionId(guestSid)
                .hotelId("hotel-1").hotelName("Test Hotel").hotelSlug("test-hotel")
                .contactName("Guest").contactPhone("0901234567")
                .status(BookingBase.BookingStatus.PENDING_PAYMENT)
                .paymentStatus(BookingBase.PaymentStatus.PENDING)
                .totalAmount(BigDecimal.valueOf(2400000))
                .depositAmount(BigDecimal.valueOf(720000))
                .amountPayable(BigDecimal.valueOf(720000))
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .nights(2).roomsCount(1)
                .build();
    }

    // ── GET /api/booking/public/my ───────────────────────────────────────────

    @Test
    void myBookings_noSid_returnsEmpty() throws Exception {
        mvc.perform(get("/api/booking/public/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void myBookings_withSid_returnsList() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findByGuestSessionIdOrderByCreatedAtDesc("sid-abc"))
                .thenReturn(List.of(h));

        mvc.perform(get("/api/booking/public/my")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].code").value("BK-001"));
    }

    // ── GET /api/booking/public/my/{code} ────────────────────────────────────

    @Test
    void myDetail_noSid_returns409() throws Exception {
        mvc.perform(get("/api/booking/public/my/BK-001"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Thiếu guest session"));
    }

    @Test
    void myDetail_notFound_returns400() throws Exception {
        when(hotelBookingRepository.findWithRoomsByCode("BK-999")).thenReturn(Optional.empty());

        mvc.perform(get("/api/booking/public/my/BK-999")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy booking"));
    }

    @Test
    void myDetail_belongsToAccount_returns409() throws Exception {
        HotelBooking h = HotelBooking.builder()
                .code("BK-001").userId(5L).guestSessionId(null)
                .hotelId("hotel-1").hotelName("Test Hotel").hotelSlug("test-hotel")
                .contactName("User").contactPhone("0901234567")
                .status(BookingBase.BookingStatus.PAID)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.ONE).depositAmount(BigDecimal.ZERO)
                .amountPayable(BigDecimal.ONE)
                .build();
        when(hotelBookingRepository.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/public/my/BK-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này thuộc tài khoản"));
    }

    @Test
    void myDetail_wrongSession_returns409() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-other");
        when(hotelBookingRepository.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/public/my/BK-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Không có quyền xem booking này"));
    }

    @Test
    void myDetail_correctSession_returnsDetail() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/public/my/BK-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("BK-001"));
    }

    // ── POST /api/booking/public/lookup ──────────────────────────────────────

    @Test
    void lookup_missingBookingCode_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    @Test
    void lookup_missingPhone_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu phoneLast4 (4 số cuối)"));
    }

    @Test
    void lookup_phoneNot4Digits_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"12\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void lookup_notFound_returns400() throws Exception {
        when(hotelBookingRepository.findByCode("BK-999")).thenReturn(Optional.empty());

        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-999\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy booking"));
    }

    @Test
    void lookup_wrongPhone_returns409() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"9999\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Sai 4 số cuối SĐT"));
    }

    @Test
    void lookup_wrongEmail_returns409() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\",\"email\":\"wrong@test.com\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email không khớp"));
    }

    @Test
    void lookup_success_returnsSummary() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.code").value("BK-001"));
    }

    // ── POST /api/booking/public/clear ───────────────────────────────────────

    @Test
    void clear_deletesGuestCookie() throws Exception {
        mvc.perform(post("/api/booking/public/clear"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã xoá dữ liệu đơn trên thiết bị này"));
    }

    // ── POST /api/booking/public/my/{code}/resume-payment ────────────────────

    @Test
    void resumeMy_success_returnsPayUrl() throws Exception {
        var dto = new com.mravel.booking.dto.ResumePaymentDTO("BK-001", "https://pay.momo.vn/abc", 900L);
        when(hotelBookingService.resumeHotelPaymentForOwner(eq("BK-001"), isNull(), eq("sid-abc"), isNull()))
                .thenReturn(dto);

        mvc.perform(post("/api/booking/public/my/BK-001/resume-payment")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookingCode").value("BK-001"));
    }

    // ── POST /api/booking/public/lookup/detail ────────────────────────────────

    @Test
    void lookupDetail_success_returnsDetail() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        when(hotelBookingRepository.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("BK-001"));
    }

    @Test
    void lookupDetail_missingCode_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    // ── POST /api/booking/public/lookup/resume ────────────────────────────────

    @Test
    void lookupResume_missingCode_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    @Test
    void lookupResume_belongsToAccount_returns409() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        h.setUserId(5L);
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này thuộc tài khoản"));
    }

    // ── POST /api/booking/public/lookup/cancel ────────────────────────────────

    @Test
    void lookupCancel_success_returnsDetail() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        HotelBooking cancelled = buildGuest("BK-001", "sid-abc");
        cancelled.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));
        when(hotelBookingService.cancelHotelBookingByLookup("BK-001", null)).thenReturn(cancelled);

        mvc.perform(post("/api/booking/public/lookup/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void lookupCancel_missingCode_returns400() throws Exception {
        mvc.perform(post("/api/booking/public/lookup/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    @Test
    void lookupCancel_belongsToAccount_returns409() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        h.setUserId(5L);
        when(hotelBookingRepository.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/public/lookup/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"BK-001\",\"phoneLast4\":\"4567\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này thuộc tài khoản"));
    }

    // ── POST /api/booking/public/my/{code}/cancel ─────────────────────────────

    @Test
    void cancelGuestHotel_noSid_returns409() throws Exception {
        mvc.perform(post("/api/booking/public/my/BK-001/cancel"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Thiếu guest session"));
    }

    @Test
    void cancelGuestHotel_success() throws Exception {
        HotelBooking h = buildGuest("BK-001", "sid-abc");
        h.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(hotelBookingService.cancelHotelBooking(eq("BK-001"), isNull(), eq("sid-abc"), any()))
                .thenReturn(h);

        mvc.perform(post("/api/booking/public/my/BK-001/cancel")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
