package com.mravel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.service.HotelBookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingMyController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingMyControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean HotelBookingRepository repo;
    @MockBean HotelBookingService service;

    private static final Long USER_ID = 10L;

    @BeforeEach
    void setupJwt() {
        var jwt = Jwt.withTokenValue("tok")
                .header("alg", "HS256")
                .claim("id", USER_ID)
                .build();
        SecurityContextHolder.getContext().setAuthentication(
                new JwtAuthenticationToken(jwt, List.of()));
    }

    @AfterEach
    void clearSecurity() {
        SecurityContextHolder.clearContext();
    }

    private HotelBooking buildHotel(String code, Long userId) {
        return HotelBooking.builder()
                .code(code)
                .userId(userId)
                .hotelId("hotel-1")
                .hotelName("Test Hotel")
                .hotelSlug("test-hotel")
                .contactName("Nguyen Van A")
                .contactPhone("0901234567")
                .contactEmail("a@test.com")
                .status(BookingBase.BookingStatus.PAID)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(2400000))
                .depositAmount(BigDecimal.valueOf(720000))
                .amountPayable(BigDecimal.valueOf(720000))
                .amountPaid(BigDecimal.valueOf(720000))
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .nights(2)
                .roomsCount(1)
                .build();
    }

    // ── GET /api/booking/my ──────────────────────────────────────────────────

    @Test
    void my_withValidJwt_returnsEmptyList() throws Exception {
        when(repo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of());

        mvc.perform(get("/api/booking/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void my_withBookings_returnsList() throws Exception {
        when(repo.findByUserIdOrderByCreatedAtDesc(USER_ID))
                .thenReturn(List.of(buildHotel("BK-001", USER_ID)));

        mvc.perform(get("/api/booking/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].code").value("BK-001"));
    }

    // ── GET /api/booking/bookings/{code} ─────────────────────────────────────

    @Test
    void detail_found_sameOwner_returnsDetail() throws Exception {
        HotelBooking h = buildHotel("BK-001", USER_ID);
        when(repo.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/bookings/BK-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.code").value("BK-001"));
    }

    @Test
    void detail_notFound_returns400() throws Exception {
        when(repo.findWithRoomsByCode("BK-999")).thenReturn(Optional.empty());

        mvc.perform(get("/api/booking/bookings/BK-999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy booking"));
    }

    @Test
    void detail_wrongOwner_returns409() throws Exception {
        HotelBooking h = buildHotel("BK-001", 99L); // different userId
        when(repo.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/bookings/BK-001"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Bạn không có quyền xem booking này"));
    }

    // ── POST /api/booking/bookings/claim ──────────────────────────────────────

    @Test
    void claim_returnsClaimed() throws Exception {
        when(service.claimGuestBookingsToUser(any(), eq(USER_ID))).thenReturn(3);

        mvc.perform(post("/api/booking/bookings/claim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(3));
    }

    // ── POST /api/booking/bookings/{code}/resume-payment ─────────────────────

    @Test
    void resumePayment_success_returnsPayUrl() throws Exception {
        var dto = new ResumePaymentDTO("BK-001", "https://pay.momo.vn/abc", 1200L);
        when(service.resumeHotelPaymentForOwner(eq("BK-001"), eq(USER_ID), isNull(), any()))
                .thenReturn(dto);

        mvc.perform(post("/api/booking/bookings/BK-001/resume-payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookingCode").value("BK-001"))
                .andExpect(jsonPath("$.data.payUrl").value("https://pay.momo.vn/abc"))
                .andExpect(jsonPath("$.data.expiresInSeconds").value(1200));
    }

    // ── POST /api/booking/bookings/{code}/cancel ──────────────────────────────

    @Test
    void cancelMyHotel_success_returnsCancelledBooking() throws Exception {
        HotelBooking h = buildHotel("BK-001", USER_ID);
        h.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(service.cancelHotelBooking(eq("BK-001"), eq(USER_ID), isNull(), eq("Đổi lịch")))
                .thenReturn(h);

        mvc.perform(post("/api/booking/bookings/BK-001/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Đổi lịch\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void cancelMyHotel_noBody_stillWorks() throws Exception {
        HotelBooking h = buildHotel("BK-001", USER_ID);
        h.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(service.cancelHotelBooking(eq("BK-001"), eq(USER_ID), isNull(), isNull()))
                .thenReturn(h);

        mvc.perform(post("/api/booking/bookings/BK-001/cancel"))
                .andExpect(status().isOk());
    }
}
