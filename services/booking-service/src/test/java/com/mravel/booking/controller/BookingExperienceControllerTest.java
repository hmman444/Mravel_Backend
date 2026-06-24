package com.mravel.booking.controller;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.model.RestaurantBooking;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingExperienceController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingExperienceControllerTest {

    @Autowired MockMvc mvc;
    @MockBean HotelBookingRepository hotelRepo;
    @MockBean RestaurantBookingRepository restaurantRepo;

    private static final Long USER_ID = 10L;
    private static final String URL = "/api/booking/internal/experienced";

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

    private HotelBooking buildHotel(String hotelId, BookingBase.BookingStatus status, LocalDate checkIn) {
        return HotelBooking.builder()
                .code("BK-001")
                .userId(USER_ID)
                .hotelId(hotelId)
                .hotelName("Test Hotel")
                .hotelSlug("test-hotel")
                .contactName("Nguyen Van A")
                .contactPhone("0901234567")
                .status(status)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(2400000))
                .depositAmount(BigDecimal.valueOf(720000))
                .amountPayable(BigDecimal.valueOf(720000))
                .checkInDate(checkIn)
                .checkOutDate(checkIn.plusDays(2))
                .nights(2).roomsCount(1)
                .build();
    }

    private RestaurantBooking buildRestaurant(String restaurantId, BookingBase.BookingStatus status, LocalDate reservationDate) {
        return RestaurantBooking.builder()
                .code("RB-001")
                .userId(USER_ID)
                .restaurantId(restaurantId)
                .restaurantName("Test Restaurant")
                .restaurantSlug("test-restaurant")
                .contactName("Tran B")
                .contactPhone("0907654321")
                .status(status)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(400000))
                .depositAmount(BigDecimal.valueOf(200000))
                .amountPayable(BigDecimal.valueOf(200000))
                .reservationDate(reservationDate)
                .people(4)
                .build();
    }

    // ── HOTEL ─────────────────────────────────────────────────────────────────

    @Test
    void experienced_hotel_hasUsedBooking_returnsEligibleTrue() throws Exception {
        HotelBooking h = buildHotel("hotel-1", BookingBase.BookingStatus.COMPLETED,
                LocalDate.now().minusDays(2));
        when(hotelRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(h));

        mvc.perform(get(URL)
                        .param("targetType", "HOTEL")
                        .param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(true));
    }

    @Test
    void experienced_hotel_checkInFuture_returnsEligibleFalse() throws Exception {
        HotelBooking h = buildHotel("hotel-1", BookingBase.BookingStatus.PAID,
                LocalDate.now().plusDays(2));
        when(hotelRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(h));

        mvc.perform(get(URL)
                        .param("targetType", "HOTEL")
                        .param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }

    @Test
    void experienced_hotel_wrongHotelId_returnsEligibleFalse() throws Exception {
        HotelBooking h = buildHotel("hotel-OTHER", BookingBase.BookingStatus.COMPLETED,
                LocalDate.now().minusDays(2));
        when(hotelRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(h));

        mvc.perform(get(URL)
                        .param("targetType", "HOTEL")
                        .param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }

    @Test
    void experienced_hotel_cancelledStatus_returnsEligibleFalse() throws Exception {
        HotelBooking h = buildHotel("hotel-1", BookingBase.BookingStatus.CANCELLED,
                LocalDate.now().minusDays(2));
        when(hotelRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(h));

        mvc.perform(get(URL)
                        .param("targetType", "HOTEL")
                        .param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }

    // ── RESTAURANT ────────────────────────────────────────────────────────────

    @Test
    void experienced_restaurant_hasUsedBooking_returnsEligibleTrue() throws Exception {
        RestaurantBooking r = buildRestaurant("rest-1", BookingBase.BookingStatus.COMPLETED,
                LocalDate.now().minusDays(1));
        when(restaurantRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(r));

        mvc.perform(get(URL)
                        .param("targetType", "RESTAURANT")
                        .param("targetId", "rest-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(true));
    }

    @Test
    void experienced_restaurant_reservationFuture_returnsEligibleFalse() throws Exception {
        RestaurantBooking r = buildRestaurant("rest-1", BookingBase.BookingStatus.PAID,
                LocalDate.now().plusDays(1));
        when(restaurantRepo.findByUserIdOrderByCreatedAtDesc(USER_ID)).thenReturn(List.of(r));

        mvc.perform(get(URL)
                        .param("targetType", "RESTAURANT")
                        .param("targetId", "rest-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }

    // ── PLACE (unsupported) ───────────────────────────────────────────────────

    @Test
    void experienced_placeType_returnsEligibleFalse() throws Exception {
        mvc.perform(get(URL)
                        .param("targetType", "PLACE")
                        .param("targetId", "place-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }

    // ── No JWT ────────────────────────────────────────────────────────────────

    @Test
    void experienced_noJwt_returnsEligibleFalse() throws Exception {
        SecurityContextHolder.clearContext();

        mvc.perform(get(URL)
                        .param("targetType", "HOTEL")
                        .param("targetId", "hotel-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.eligible").value(false));
    }
}
