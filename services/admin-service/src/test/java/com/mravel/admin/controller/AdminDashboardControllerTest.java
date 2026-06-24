package com.mravel.admin.controller;

import com.mravel.admin.dto.dashboard.AdminDashboardDtos.*;
import com.mravel.admin.service.AdminDashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminDashboardController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminDashboardControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean AdminDashboardService dashboardService;

    private static final String AUTH = "Bearer test-token";

    private DashboardResponse emptyDashboard(String range) {
        Overview overview = new Overview(
                0, null, 0, 0, 0, 0, 0,
                BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, 0);
        return new DashboardResponse(range, overview, List.of(), List.of(), List.of(), List.of(), List.of(), List.of());
    }

    @Test
    void dashboard_defaultRangeWeekly() throws Exception {
        when(dashboardService.build(eq("weekly"), eq("test-token")))
                .thenReturn(emptyDashboard("weekly"));

        mvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.range").value("weekly"));

        verify(dashboardService).build(eq("weekly"), eq("test-token"));
    }

    @Test
    void dashboard_customRangeMonthly() throws Exception {
        when(dashboardService.build(eq("monthly"), eq("test-token")))
                .thenReturn(emptyDashboard("monthly"));

        mvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", AUTH)
                        .param("range", "monthly"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.range").value("monthly"));

        verify(dashboardService).build(eq("monthly"), eq("test-token"));
    }

    @Test
    void dashboard_bearerPrefixStripped() throws Exception {
        when(dashboardService.build(anyString(), eq("my-jwt")))
                .thenReturn(emptyDashboard("weekly"));

        mvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", "Bearer my-jwt"))
                .andExpect(status().isOk());

        verify(dashboardService).build(eq("weekly"), eq("my-jwt"));
    }

    @Test
    void dashboard_responseWrappedInApiResponse() throws Exception {
        Overview overview = new Overview(
                100, null, 10, 30, 3, 5, 37,
                BigDecimal.valueOf(1_500_000), BigDecimal.valueOf(28_000_000),
                2.5, 1.0, 0.5);
        DashboardResponse data = new DashboardResponse(
                "weekly", overview, List.of(), List.of(), List.of(), List.of(), List.of(), List.of());

        when(dashboardService.build(eq("weekly"), anyString())).thenReturn(data);

        mvc.perform(get("/api/admin/dashboard").header("Authorization", AUTH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.overview.totalUsers").value(100))
                .andExpect(jsonPath("$.data.overview.partners").value(10));
    }
}
