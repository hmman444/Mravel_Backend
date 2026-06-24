package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.BookingPartnerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerBookingControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean BookingPartnerClient bookingClient;

    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/partner/bookings";

    private ResponseEntity<ApiResponse<?>> ok() {
        ApiResponse<?> body = ApiResponse.success("OK", null);
        return ResponseEntity.ok(body);
    }

    // ── GET /api/partner/bookings/hotels ─────────────────────────────────────

    @Test
    void listHotelBookings_success_forwardsResponse() throws Exception {
        when(bookingClient.listHotelBookings(any(), any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/hotels").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── GET /api/partner/bookings/hotels/{bookingCode} ────────────────────────

    @Test
    void hotelDetail_success_forwardsResponse() throws Exception {
        when(bookingClient.getHotelBookingDetail(eq("BK-001"), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/hotels/BK-001").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/bookings/hotels/{bookingCode}/cancel ────────────────

    @Test
    void cancelHotel_success_forwardsResponse() throws Exception {
        when(bookingClient.cancelHotelBooking(eq("BK-001"), any(), anyString())).thenReturn(ok());

        mvc.perform(post(BASE + "/hotels/BK-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Hết phòng\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void cancelHotel_missingReason_returns400() throws Exception {
        mvc.perform(post(BASE + "/hotels/BK-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    // ── GET /api/partner/bookings/restaurants ─────────────────────────────────

    @Test
    void listRestaurantBookings_success_forwardsResponse() throws Exception {
        when(bookingClient.listRestaurantBookings(any(), any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/restaurants").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── GET /api/partner/bookings/restaurants/{bookingCode} ───────────────────

    @Test
    void restaurantDetail_success_forwardsResponse() throws Exception {
        when(bookingClient.getRestaurantBookingDetail(eq("RB-001"), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/restaurants/RB-001").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /api/partner/bookings/restaurants/{bookingCode}/cancel ───────────

    @Test
    void cancelRestaurant_success_forwardsResponse() throws Exception {
        when(bookingClient.cancelRestaurantBooking(eq("RB-001"), any(), anyString())).thenReturn(ok());

        mvc.perform(post(BASE + "/restaurants/RB-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Hết bàn\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void cancelRestaurant_missingReason_returns400() throws Exception {
        mvc.perform(post(BASE + "/restaurants/RB-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}
