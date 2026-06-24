package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.BookingPartnerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerStatsController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerStatsControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean BookingPartnerClient bookingClient;

    private static final String BEARER = "Bearer test-token";
    private static final String BASE = "/api/partner/stats";

    private ResponseEntity<ApiResponse<?>> ok() {
        ApiResponse<?> body = ApiResponse.success("OK", null);
        return ResponseEntity.ok(body);
    }

    // ── GET /api/partner/stats/status ────────────────────────────────────────

    @Test
    void statsByStatus_success_forwardsResponse() throws Exception {
        when(bookingClient.statsByStatus(any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/status").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void statsByStatus_withDateRange_forwardsResponse() throws Exception {
        when(bookingClient.statsByStatus(any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/status")
                        .header("Authorization", BEARER)
                        .param("from", "2026-01-01")
                        .param("to", "2026-06-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── GET /api/partner/stats/revenue ───────────────────────────────────────

    @Test
    void revenue_success_forwardsResponse() throws Exception {
        when(bookingClient.revenue(any(), any(), any(), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/revenue").header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void revenue_withGroupParam_forwardsResponse() throws Exception {
        when(bookingClient.revenue(any(), any(), eq("month"), anyString())).thenReturn(ok());

        mvc.perform(get(BASE + "/revenue")
                        .header("Authorization", BEARER)
                        .param("from", "2026-01-01")
                        .param("to", "2026-06-30")
                        .param("group", "month"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
