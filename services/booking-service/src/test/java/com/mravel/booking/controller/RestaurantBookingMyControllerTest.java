package com.mravel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.RestaurantBooking;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.service.RestaurantBookingService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantBookingMyController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestaurantBookingMyControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean RestaurantBookingRepository repo;
    @MockBean RestaurantBookingService service;

    private static final Long USER_ID = 10L;
    private static final String BASE = "/api/booking/restaurants";

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

    private RestaurantBooking buildRestaurant(String code, Long userId) {
        return RestaurantBooking.builder()
                .code(code)
                .userId(userId)
                .restaurantId("rest-1")
                .restaurantName("Test Restaurant")
                .restaurantSlug("test-restaurant")
                .contactName("Tran B")
                .contactPhone("0907654321")
                .contactEmail("b@test.com")
                .status(BookingBase.BookingStatus.PAID)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(400000))
                .depositAmount(BigDecimal.valueOf(200000))
                .amountPayable(BigDecimal.valueOf(200000))
                .amountPaid(BigDecimal.valueOf(200000))
                .reservationDate(LocalDate.now().plusDays(1))
                .people(4)
                .build();
    }

    // ── GET /api/booking/restaurants/my ───────────────────────────────────────

    @Test
    void my_returnsEmptyList() throws Exception {
        when(repo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of());

        mvc.perform(get(BASE + "/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void my_returnsList() throws Exception {
        when(repo.findByUserIdOrderByCreatedAtDesc(USER_ID))
                .thenReturn(List.of(buildRestaurant("RB-001", USER_ID)));

        mvc.perform(get(BASE + "/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].code").value("RB-001"));
    }

    // ── GET /api/booking/restaurants/{code} ───────────────────────────────────

    @Test
    void detail_found_sameOwner_returnsDetail() throws Exception {
        RestaurantBooking rb = buildRestaurant("RB-001", USER_ID);
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(rb));

        mvc.perform(get(BASE + "/RB-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    @Test
    void detail_notFound_returns400() throws Exception {
        when(repo.findWithTablesByCode("RB-999")).thenReturn(Optional.empty());

        mvc.perform(get(BASE + "/RB-999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Không tìm thấy booking"));
    }

    @Test
    void detail_wrongOwner_returns409() throws Exception {
        RestaurantBooking rb = buildRestaurant("RB-001", 99L);
        when(repo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(rb));

        mvc.perform(get(BASE + "/RB-001"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Bạn không có quyền xem booking này"));
    }

    // ── POST /api/booking/restaurants/claim ───────────────────────────────────

    @Test
    void claim_returnsCount() throws Exception {
        when(service.claimGuestRestaurantBookingsToUser(any(), eq(USER_ID))).thenReturn(2);

        mvc.perform(post(BASE + "/claim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(2));
    }

    // ── POST /api/booking/restaurants/lookup ──────────────────────────────────

    @Test
    void lookup_notOwner_returns409() throws Exception {
        RestaurantBooking rb = buildRestaurant("RB-001", 99L);
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(rb));

        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\",\"phoneLast4\":\"4321\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Booking này không thuộc tài khoản của bạn"));
    }

    @Test
    void lookup_success_returnsSummary() throws Exception {
        RestaurantBooking rb = buildRestaurant("RB-001", USER_ID);
        when(repo.findByCode("RB-001")).thenReturn(Optional.of(rb));

        mvc.perform(post(BASE + "/lookup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingCode\":\"RB-001\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    // ── POST /api/booking/restaurants/{code}/resume-payment ───────────────────

    @Test
    void resumePayment_success() throws Exception {
        var dto = new ResumePaymentDTO("RB-001", "https://pay.vnpay.vn/abc", 900L);
        when(service.resumeRestaurantPaymentForOwner(eq("RB-001"), eq(USER_ID), isNull(), any()))
                .thenReturn(dto);

        mvc.perform(post(BASE + "/RB-001/resume-payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookingCode").value("RB-001"));
    }

    // ── POST /api/booking/restaurants/{code}/cancel ───────────────────────────

    @Test
    void cancel_success_returnsCancelled() throws Exception {
        RestaurantBooking rb = buildRestaurant("RB-001", USER_ID);
        rb.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(service.cancelRestaurantBooking(eq("RB-001"), eq(USER_ID), isNull(), eq("Bận")))
                .thenReturn(rb);

        mvc.perform(post(BASE + "/RB-001/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Bận\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
