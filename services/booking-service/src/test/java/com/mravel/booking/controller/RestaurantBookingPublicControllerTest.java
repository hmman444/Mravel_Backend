package com.mravel.booking.controller;

import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.RestaurantBooking;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.service.PaymentAttemptService;
import com.mravel.booking.service.RestaurantBookingService;
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

@WebMvcTest(RestaurantBookingPublicController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestaurantBookingPublicControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestaurantBookingRepository repo;
    @MockBean RestaurantBookingService service;
    @MockBean PaymentAttemptService paymentAttemptService;

    private static final String BASE = "/api/booking/public/restaurants";

    private RestaurantBooking buildGuest(String code, String sid) {
        return RestaurantBooking.builder()
                .code(code)
                .guestSessionId(sid)
                .restaurantId("rest-1")
                .restaurantName("Nhà hàng XYZ")
                .restaurantSlug("nha-hang-xyz")
                .contactName("Guest")
                .contactPhone("0907654321")
                .contactEmail("guest@test.com")
                .status(BookingBase.BookingStatus.PENDING_PAYMENT)
                .paymentStatus(BookingBase.PaymentStatus.PENDING)
                .totalAmount(BigDecimal.valueOf(400000))
                .depositAmount(BigDecimal.valueOf(200000))
                .amountPayable(BigDecimal.valueOf(200000))
                .reservationDate(LocalDate.now().plusDays(1))
                .people(4)
                .build();
    }

    // ── GET /api/booking/public/restaurants/my ────────────────────────────────

    @Test
    void my_noSid_returnsEmpty() throws Exception {
        mvc.perform(get(BASE + "/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void my_withSid_returnsList() throws Exception {
        when(repo.findByGuestSessionIdOrderByCreatedAtDesc("sid-abc"))
                .thenReturn(List.of(buildGuest("RB-001", "sid-abc")));

        mvc.perform(get(BASE + "/my")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].code").value("RB-001"));
    }

    // ── GET /api/booking/public/restaurants/my/{code} ─────────────────────────

    @Test
    void myDetail_noSid_returns409() throws Exception {
        mvc.perform(get(BASE + "/my/RB-001"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Thiếu guest session"));
    }

    @Test
    void myDetail_notFound_returns400() throws Exception {
        when(repo.findWithTablesByCode("RB-999")).thenReturn(Optional.empty());

        mvc.perform(get(BASE + "/my/RB-999")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy booking"));
    }

    @Test
    void myDetail_belongsToAccount_returns409() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", null);
        r.setUserId(5L);
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(get(BASE + "/my/RB-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này thuộc tài khoản"));
    }

    @Test
    void myDetail_wrongSession_returns409() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-other");
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(get(BASE + "/my/RB-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Không có quyền xem booking này"));
    }

    @Test
    void myDetail_correctSession_returnsDetail() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(get(BASE + "/my/RB-001")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    // ── POST /api/booking/public/restaurants/lookup ───────────────────────────

    @Test
    void lookup_missingCode_returns400() throws Exception {
        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    @Test
    void lookup_missingPhone_returns400() throws Exception {
        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu phoneLast4 (4 số cuối)"));
    }

    @Test
    void lookup_wrongPhone_returns409() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"9999\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Sai 4 số cuối SĐT"));
    }

    @Test
    void lookup_success_returnsSummary() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    // ── POST /api/booking/public/restaurants/my/{code}/resume-payment ─────────

    @Test
    void resumeMy_success_returnsPayUrl() throws Exception {
        var dto = new ResumePaymentDTO("RB-001", "https://pay.vnpay.vn/abc", 900L);
        when(service.resumeRestaurantPaymentForOwner(eq("RB-001"), isNull(), eq("sid-abc"), isNull()))
                .thenReturn(dto);

        mvc.perform(post(BASE + "/my/RB-001/resume-payment")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookingCode").value("RB-001"));
    }

    // ── POST /api/booking/public/restaurants/lookup/detail ───────────────────

    @Test
    void lookupDetail_success_returnsDetail() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(post(BASE + "/lookup/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    @Test
    void lookupDetail_missingCode_returns400() throws Exception {
        mvc.perform(post(BASE + "/lookup/detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    // ── POST /api/booking/public/restaurants/lookup/resume ────────────────────

    @Test
    void lookupResume_missingCode_returns400() throws Exception {
        mvc.perform(post(BASE + "/lookup/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    @Test
    void lookupResume_belongsToAccount_returns409() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        r.setUserId(5L);
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(post(BASE + "/lookup/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này thuộc tài khoản"));
    }

    // ── POST /api/booking/public/restaurants/lookup/cancel ────────────────────

    @Test
    void lookupCancel_success_returnsDetail() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        RestaurantBooking cancelled = buildGuest("RB-001", "sid-abc");
        cancelled.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(r));
        when(service.cancelRestaurantBookingByLookup("RB-001", null)).thenReturn(cancelled);

        mvc.perform(post(BASE + "/lookup/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void lookupCancel_missingCode_returns400() throws Exception {
        mvc.perform(post(BASE + "/lookup/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Thiếu bookingCode"));
    }

    // ── POST /api/booking/public/restaurants/my/{code}/cancel ─────────────────

    @Test
    void cancelGuestRestaurant_noSid_returns409() throws Exception {
        mvc.perform(post(BASE + "/my/RB-001/cancel"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Thiếu guest session"));
    }

    @Test
    void cancelGuestRestaurant_success() throws Exception {
        RestaurantBooking r = buildGuest("RB-001", "sid-abc");
        r.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(service.cancelRestaurantBooking(eq("RB-001"), isNull(), eq("sid-abc"), any()))
                .thenReturn(r);

        mvc.perform(post(BASE + "/my/RB-001/cancel")
                        .cookie(new jakarta.servlet.http.Cookie("mravel_guest_sid", "sid-abc")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
