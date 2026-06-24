package com.mravel.booking.controller;

import com.mravel.booking.client.AuthValidateClient;
import com.mravel.booking.service.AdminBookingStatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mravel.booking.dto.AdminBookingStatsDtos.BookingStats;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminBookingStatsController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminBookingStatsControllerTest {

    @Autowired MockMvc mvc;
    @MockBean AuthValidateClient authValidateClient;
    @MockBean AdminBookingStatsService statsService;

    private static final String URL = "/api/booking/admin/dashboard";
    private static final String ADMIN_BEARER = "Bearer admin-jwt";

    // ── GET /api/booking/admin/dashboard ─────────────────────────────────────

    @Test
    void stats_adminUser_returns200() throws Exception {
        var stats = new BookingStats(5L, 30L,
                BigDecimal.valueOf(1000000), BigDecimal.valueOf(8000000),
                5.0, 2.0, 3.0, 2L, List.of(), List.of(), List.of());

        when(authValidateClient.requireAdminId(ADMIN_BEARER)).thenReturn(1L);
        when(statsService.getStats("weekly")).thenReturn(stats);

        mvc.perform(get(URL).param("range", "weekly")
                        .header("Authorization", ADMIN_BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.bookingsToday").value(5))
                .andExpect(jsonPath("$.data.bookings7d").value(30));
    }

    @Test
    void stats_defaultRange_usesWeekly() throws Exception {
        var stats = new BookingStats(1L, 7L,
                BigDecimal.ZERO, BigDecimal.ZERO, 0.0, 0.0, 0.0, 0L, List.of(), List.of(), List.of());

        when(authValidateClient.requireAdminId(ADMIN_BEARER)).thenReturn(1L);
        when(statsService.getStats("weekly")).thenReturn(stats);

        mvc.perform(get(URL).header("Authorization", ADMIN_BEARER))
                .andExpect(status().isOk());

        verify(statsService).getStats("weekly");
    }

    @Test
    void stats_notAdmin_returns401() throws Exception {
        doThrow(new SecurityException("Unauthorized")).when(authValidateClient)
                .requireAdminId("Bearer bad-jwt");

        mvc.perform(get(URL).header("Authorization", "Bearer bad-jwt"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false));

        verify(statsService, never()).getStats(anyString());
    }

    @Test
    void stats_missingAuthHeader_returns500() throws Exception {
        mvc.perform(get(URL))
                .andExpect(status().isInternalServerError());
    }
}
